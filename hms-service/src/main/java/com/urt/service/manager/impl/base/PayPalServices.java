package com.urt.service.manager.impl.base;



/**
 * Created by IntelliJ IDEA.
 * User: lhuynh
 * Date: Dec 6, 2007
 * Time: 5:06:52 PM
 * To change this template use File | Settings | File Templates.
 */

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletResponse;

import com.churchgroup.util.date.DateFormatter;
import com.churchgroup.util.object.ObjectFunctions;
import com.urt.persistence.model.subscription.ProductLineItem;

public class PayPalServices  {


    private String gv_APIUserName;
    private String gv_APIPassword;
    private String gv_APISignature;

    private String gv_APIEndpoint;
    private String  gv_BNCode;

    private String  gv_Version;
    private String  gv_nvpHeader;
    private String  gv_ProxyServer;
    private String gv_ProxyServerPort;
    private int gv_Proxy;
    private boolean gv_UseProxy;
    private String PAYPAL_URL;

    public PayPalServices(String environment) {

    //BN Code is only applicable for partners
    gv_BNCode		= "PP-ECWizard";
	// Replace <apiUserName> with your API Username
	// Replace <apiPassword> with your API Password
	// Replace <signature> with your Signature
    boolean bSandbox = true;
    if("Prod".equalsIgnoreCase(environment))
    	bSandbox = false;

    /*
    Servers for NVP API
    Sandbox: https://api-3t.sandbox.paypal.com/nvp
    Live: https://api-3t.paypal.com/nvp
    */

    /*
    Redirect URLs for PayPal Login Screen
    Sandbox: https://www.sandbox.paypal.com/webscr&cmd=_express-checkout&token=XXXX
    Live: https://www.paypal.com/cgi-bin/webscr?cmd=_express-checkout&token=XXXX
    */

    if (bSandbox)
    {
        gv_APIUserName = "sreera_1267251920_biz_api1.uroomtech.com";
        gv_APIPassword  = "1267251926";
        gv_APISignature = "AFcWxV21C7fd0v3bYYYRCpSSRl31Al-qOTWo5Nzv1W1mt6t5YgmQHCHK";
        gv_APIEndpoint = "https://api-3t.sandbox.paypal.com/nvp";
        PAYPAL_URL = "https://www.sandbox.paypal.com/webscr&cmd=_express-checkout&token=";
    }
    else
    {
        gv_APISignature  = "AFcWxV21C7fd0v3bYYYRCpSSRl31AHNRbAbZ6M.ISs5lotjaAAHF44kb";
        gv_APIUserName  = "aivankovich_api1.uroomtech.com";
        gv_APIPassword  = "RG2ANMRV3N4AF5X4";
        gv_APIEndpoint = "https://api-3t.paypal.com/nvp";
        PAYPAL_URL = "https://www.paypal.com/cgi-bin/webscr?cmd=_express-checkout&token=";
    }

    String HTTPREQUEST_PROXYSETTING_SERVER = "";
    String HTTPREQUEST_PROXYSETTING_PORT = "";
    boolean USE_PROXY = false;

    setGv_Version("57.0");

    //WinObjHttp Request proxy settings.
    setGv_ProxyServer(HTTPREQUEST_PROXYSETTING_SERVER);
    setGv_ProxyServerPort(HTTPREQUEST_PROXYSETTING_PORT);
    setGv_Proxy(2);	//'setting for proxy activation
    setGv_UseProxy(USE_PROXY);


}



/*********************************************************************************
  * setExpressCheckout: Function to perform the SetExpressCheckout API call
  *
  * Inputs:
  *		paymentAmount:  	Total value of the shopping cart
  *		currencyCodeType: 	Currency code value the PayPal API
  *		paymentType: 		paymentType has to be one of the following values: Sale or Order or Authorization
  *		returnURL:			the page where buyers return to after they are done with the payment review on PayPal
  *		cancelURL:			the page where buyers return to when they cancel the payment review on PayPal
  *
  * Output: Returns a HashMap object containing the response from the server.
*********************************************************************************/
public HashMap setExpressCheckout(String productNames, String paymentAmount, String returnURL, String cancelURL)
{

	/*
	'------------------------------------
	' The currencyCodeType and paymentType 
	' are set to the selections made on the Integration Assistant 
	'------------------------------------
	*/

String currencyCodeType = "USD";
	String paymentType = "Sale";

    /*
    Construct the parameter string that describes the PayPal payment
    the varialbes were set in the web form, and the resulting string
    is stored in $nvpstr
    */
    String nvpstr = "&L_BILLINGTYPE0=RecurringPayments&L_BILLINGAGREEMENTDESCRIPTION0="+productNames+"&Amt=" + paymentAmount + "&PAYMENTACTION=" + paymentType + "&ReturnUrl=" + URLEncoder.encode( returnURL ) + "&CANCELURL=" + URLEncoder.encode( cancelURL ) + "&CURRENCYCODE=" + currencyCodeType;
    
    /*
    Make the call to PayPal to get the Express Checkout token
    If the API call succeded, then redirect the buyer to PayPal
    to begin to authorize payment.  If an error occured, show the
    resulting errors
    */

    HashMap nvp = httpcall("SetExpressCheckout", nvpstr);
    String strAck = nvp.get("ACK").toString();
    if(strAck !=null && strAck.equalsIgnoreCase("Success"))
    {
        return nvp;
    }

    return null;
}

/*********************************************************************************
  * CallMarkExpressCheckout: Function to perform the SetExpressCheckout API call
  *
  * Inputs:
  *		paymentAmount:  	Total value of the shopping cart
  *		currencyCodeType: 	Currency code value the PayPal API
  *		paymentType: 		paymentType has to be one of the following values: Sale or Order or Authorization
  *		returnURL:			the page where buyers return to after they are done with the payment review on PayPal
  *		cancelURL:			the page where buyers return to when they cancel the payment review on PayPal
  *		shipToName:		the Ship to name entered on the merchant's site
  *		shipToStreet:		the Ship to Street entered on the merchant's site
  *		shipToCity:			the Ship to City entered on the merchant's site
  *		shipToState:		the Ship to State entered on the merchant's site
  *		shipToCountryCode:	the Code for Ship to Country entered on the merchant's site
  *		shipToZip:			the Ship to ZipCode entered on the merchant's site
  *		shipToStreet2:		the Ship to Street2 entered on the merchant's site
  *		phoneNum:			the phoneNum  entered on the merchant's site
  *
  * Output: Returns a HashMap object containing the response from the server.
*********************************************************************************/
public HashMap CallMarkExpressCheckout( String paymentAmount, String returnURL, String cancelURL, String shipToName, String 										shipToStreet, String shipToCity, String shipToState,
                                        String shipToCountryCode, String shipToZip, String shipToStreet2, String phoneNum)
{
	/*
	'------------------------------------
	' The currencyCodeType and paymentType 
	' are set to the selections made on the Integration Assistant 
	'------------------------------------
	*/
	String currencyCodeType = "USD";
	String paymentType = "Sale";

    /*
    Construct the parameter string that describes the PayPal payment
    the varialbes were set in the web form, and the resulting string
    is stored in $nvpstr
    */
    String  nvpstr = "ADDROVERRIDE=1&Amt=" + paymentAmount + "&PAYMENTACTION=" + paymentType;
    nvpstr=nvpstr.concat("&CURRENCYCODE=" + currencyCodeType + "&ReturnUrl=" + URLEncoder.encode( returnURL  ) + "&CANCELURL=" + 	
	URLEncoder.encode( cancelURL ));
	
    nvpstr=nvpstr.concat( "&SHIPTONAME=" + shipToName + "&SHIPTOSTREET=" + shipToStreet + "&SHIPTOSTREET2=" + shipToStreet2);
    nvpstr=nvpstr.concat("&SHIPTOCITY=" + shipToCity + "&SHIPTOSTATE=" + shipToState + "&SHIPTOCOUNTRYCODE=" + shipToCountryCode);
    nvpstr=nvpstr.concat("&SHIPTOZIP=" + shipToZip + "&PHONENUM" + phoneNum);

    /*
    Make the call to PayPal to set the Express Checkout token
    If the API call succeded, then redirect the buyer to PayPal
    to begin to authorize payment.  If an error occured, show the
    resulting errors
    */

    HashMap nvp = httpcall("SetExpressCheckout", nvpstr);
    String strAck = nvp.get("ACK").toString();
    if(strAck !=null && !(strAck.equalsIgnoreCase("Success") || strAck.equalsIgnoreCase("SuccessWithWarning")))
    {
        return nvp;
    }
   	return null;
}


/*********************************************************************************
  * getExpressCheckoutDetails: Function to perform the GetExpressCheckoutDetails API call
  *
  * Inputs:  None
  *
  * Output: Returns a HashMap object containing the response from the server.
*********************************************************************************/
public HashMap getExpressCheckoutDetails( String token)
{
    /*
    Build a second API request to PayPal, using the token as the
    ID to get the details on the payment authorization
    */

    String nvpstr= "&TOKEN=" + token;

   /*
    Make the API call and store the results in an array.  If the
    call was a success, show the authorization details, and provide
    an action to complete the payment.  If failed, show the error
    */

    return httpcall("GetExpressCheckoutDetails", nvpstr);
}

/*********************************************************************************
  * DoExpressCheckoutPayment: Function to perform the DoExpressCheckoutPayment API call
  *
  * Inputs:  None
  *
  * Output: Returns a HashMap object containing the response from the server.
*********************************************************************************/
public HashMap doExpressCheckoutPayment( String token, String payerID, String finalPaymentAmount,String serverName)
{

	/*
	'------------------------------------
	' The currencyCodeType and paymentType 
	' are set to the selections made on the Integration Assistant 
	'------------------------------------
	*/
	String currencyCodeType = "USD";
	String paymentType = "Sale";

  /*
    '----------------------------------------------------------------------------
    '----	Use the values stored in the session from the previous SetEC call
    '----------------------------------------------------------------------------
    */
    String nvpstr  = "&TOKEN=" + token + "&PAYERID=" + payerID + "&PAYMENTACTION=" + paymentType + "&AMT=" + finalPaymentAmount;
	
    nvpstr = nvpstr + "&CURRENCYCODE=" + currencyCodeType + "&IPADDRESS=" + serverName;

 /*
    Make the call to PayPal to finalize payment
    If an error occured, show the resulting errors
  */
    return httpcall("DoExpressCheckoutPayment",nvpstr);
    
}

/*********************************************************************************
 * DoExpressCheckoutPayment: Function to perform the DoExpressCheckoutPayment API call
 *
 * Inputs:  None
 *
 * Output: Returns a HashMap object containing the response from the server.
*********************************************************************************/
public HashMap doExpressCheckoutPayment( String token, String payerID, String finalPaymentAmount, List productLineItems ,String productsAmt,String productsTax,String serverName)
{

	/*
	'------------------------------------
	' The currencyCodeType and paymentType 
	' are set to the selections made on the Integration Assistant 
	'------------------------------------
	*/
	String currencyCodeType = "USD";
	String paymentType = "Sale";

 /*
   '----------------------------------------------------------------------------
   '----	Use the values stored in the session from the previous SetEC call
   '----------------------------------------------------------------------------
   */
   String nvpstr  = "&TOKEN=" + token + "&PAYERID=" + payerID + "&PAYMENTACTION=" + paymentType+"&AMT="+finalPaymentAmount;
	
   nvpstr = nvpstr + "&CURRENCYCODE=" + currencyCodeType + "&IPADDRESS=" + serverName;
   nvpstr = nvpstr + "&ITEMAMT=" + productsAmt + "&TAXAMT=" + productsTax+"&DESC=EMS Products Subscription";
   if(ObjectFunctions.isNotNullOrEmpty(productLineItems)){
	   Iterator objectIterator=productLineItems.iterator();
	   int i=0;
	   for(Iterator productItemIterator=objectIterator;productItemIterator.hasNext();){
			ProductLineItem pli=(ProductLineItem)productItemIterator.next();
			StringBuffer itemBuff=new StringBuffer();
			itemBuff.append("&L_NAME"+i+"=");
			itemBuff.append(pli.getName());
			itemBuff.append("&L_DESC"+i+"=");			
			itemBuff.append(pli.getDescription());
			itemBuff.append("&L_AMT"+i+"=");
			itemBuff.append(pli.getAmount());
			itemBuff.append("&L_NUMBER"+i+"=");
			itemBuff.append(pli.getNumber());
			itemBuff.append("&L_QTY"+i+"=");			
			itemBuff.append(pli.getQuantity());
			itemBuff.append("&L_TAXAMT"+i+"=");
			itemBuff.append(pli.getTaxAmt());
			nvpstr =nvpstr + itemBuff.toString();
			i++;
	   }
   }

/*
   Make the call to PayPal to finalize payment
   If an error occured, show the resulting errors
 */
   return httpcall("DoExpressCheckoutPayment",nvpstr);
   
}


public HashMap createRecurringPaymentsProfile(String token,Date startDate,String productNames,String billingPeriod,String billingFrequency,String payerId, String finalPaymentAmount)
{

    /*
    '------------------------------------
    ' The currencyCodeType and paymentType 
    ' are set to the selections made on the Integration Assistant 
    '------------------------------------
    */
    String currencyCodeType = "USD";
    String paymentType = "Sale";
    String formattedDate=DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_THHMMSS_PATTERN,startDate);
  /*
    '----------------------------------------------------------------------------
    '----   Use the values stored in the session from the previous SetEC call
    '----------------------------------------------------------------------------
    */
    String nvpstr  ="&L_BILLINGTYPE0=RecurringPayments&L_BILLINGAGREEMENTDESCRIPTION0="+productNames+"&DESC="+productNames+"&PROFILESTARTDATE=" + formattedDate + "&BILLINGPERIOD="+ billingPeriod +"&BILLINGFREQUENCY=" +billingFrequency+ "&TOKEN=" + token +  "&PAYMENTACTION=" + paymentType + "&AMT=" + finalPaymentAmount ;
    nvpstr = nvpstr + "&CURRENCYCODE=" + currencyCodeType;

 /*
    Make the call to PayPal to finalize payment
    If an error occured, show the resulting errors
  */
    return httpcall("CreateRecurringPaymentsProfile",nvpstr);
}

public HashMap getRecurringPaymentsProfileDetails(String profileId)
{

  /*
    '----------------------------------------------------------------------------
    '----   Use the values stored in the session from the previous SetEC call
    '----------------------------------------------------------------------------
    */
    String nvpstr  = "&PROFILEID=" + profileId;
    
 /*
    Make the call to PayPal to finalize payment
    If an error occured, show the resulting errors
  */
    return httpcall("GetRecurringPaymentsProfileDetails",nvpstr);
}

public HashMap manageRecurringPaymentsProfileStatus(String profileId)
{

  /*
    '----------------------------------------------------------------------------
    '----   Use the values stored in the session from the previous SetEC call
    '----------------------------------------------------------------------------
    */
    String action="Cancel";
    
    String nvpstr  = "&PROFILEID=" + profileId + "&Action="+ action;
    
 /*
    Make the call to PayPal to finalize payment
    If an error occured, show the resulting errors
  */
    return httpcall("ManageRecurringPaymentsProfileStatus",nvpstr);
    
    
}


public HashMap UpdateRecurringPaymentsProfile(String profileId,String amount)
{

	String currencyCodeType = "USD";
    String nvpstr  = "&PROFILEID=" + profileId + "&Amount="+ amount;
    nvpstr+= "&CURRENCYCODE=" + currencyCodeType;
    
 /*
    Make the call to PayPal to finalize payment
    If an error occured, show the resulting errors
  */
    return httpcall("UpdateRecurringPaymentsProfile",nvpstr);
    
    
}

/*********************************************************************************
  * httpcall: Function to perform the API call to PayPal using API signature
  * 	@ methodName is name of API  method.
  * 	@ nvpStr is nvp string.
  * returns a NVP string containing the response from the server.
*********************************************************************************/
public HashMap httpcall( String methodName, String nvpStr)
{

    String agent = "Mozilla/4.0";
    String respText = "";
    HashMap nvp = null;   //lhuynh not used?

    //deformatNVP( nvpStr );
    String encodedData = "METHOD=" + methodName + "&VERSION=" + gv_Version + "&PWD=" + gv_APIPassword + "&USER=" + gv_APIUserName + "&SIGNATURE=" + gv_APISignature + nvpStr + "&BUTTONSOURCE=" + gv_BNCode;

    try
    {
        URL postURL = new URL(gv_APIEndpoint);
        HttpURLConnection conn = (HttpURLConnection)postURL.openConnection();

        // Set connection parameters. We need to perform input and output,
        // so set both as true.
        conn.setDoInput (true);
        conn.setDoOutput (true);

        // Set the content type we are POSTing. We impersonate it as
        // encoded form data
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("User-Agent", agent);

        //conn.setRequestProperty( "Content-Type", type );
        conn.setRequestProperty("Content-Length", String.valueOf( encodedData.length()));
        conn.setRequestMethod("POST");

        // get the output stream to POST to.
        DataOutputStream output = new DataOutputStream(conn.getOutputStream());
        output.writeBytes(encodedData);
        output.flush();
        output.close ();

        // Read input from the input stream.
     //   DataInputStream  in = new DataInputStream (conn.getInputStream());
        int rc = conn.getResponseCode();
        if ( rc != -1)
        {
            BufferedReader is = new BufferedReader(new InputStreamReader( conn.getInputStream()));
            String _line = null;
            while((_line = is.readLine()) !=null)
            {
                respText = respText + _line;
            }
            nvp = deformatNVP(respText );
        }
        return nvp;
    }
    catch( IOException e )
    {
        // handle the error here
        return null;
    }
}

/*********************************************************************************
  * deformatNVP: Function to break the NVP string into a HashMap
  * 	pPayLoad is the NVP string.
  * returns a HashMap object containing all the name value pairs of the string.
*********************************************************************************/
public HashMap deformatNVP( String pPayload )
{
    HashMap nvp = new HashMap();
    StringTokenizer stTok = new StringTokenizer( pPayload, "&");
    while (stTok.hasMoreTokens())
    {
        StringTokenizer stInternalTokenizer = new StringTokenizer( stTok.nextToken(), "=");
        if (stInternalTokenizer.countTokens() == 2)
        {
            String key = URLDecoder.decode( stInternalTokenizer.nextToken());
            String value = URLDecoder.decode( stInternalTokenizer.nextToken());
            nvp.put( key.toUpperCase(), value );
        }
    }
    return nvp;
}

/*********************************************************************************
  * RedirectURL: Function to redirect the user to the PayPal site
  * 	token is the parameter that was returned by PayPal
  * returns a HashMap object containing all the name value pairs of the string.
*********************************************************************************/
public void RedirectURL( HttpServletResponse response, String token )
{
    String payPalURL = PAYPAL_URL + token;

    //response.sendRedirect( payPalURL );
    response.setStatus(302);
    response.setHeader( "Location", payPalURL );
    response.setHeader( "Connection", "close" );
}



public String getGv_nvpHeader() {
    return gv_nvpHeader;
}



public void setGv_nvpHeader(String gv_nvpHeader) {
    this.gv_nvpHeader = gv_nvpHeader;
}



public String getGv_ProxyServer() {
    return gv_ProxyServer;
}



public void setGv_ProxyServer(String gv_ProxyServer) {
    this.gv_ProxyServer = gv_ProxyServer;
}



public String getGv_ProxyServerPort() {
    return gv_ProxyServerPort;
}



public void setGv_ProxyServerPort(String gv_ProxyServerPort) {
    this.gv_ProxyServerPort = gv_ProxyServerPort;
}



public int getGv_Proxy() {
    return gv_Proxy;
}



public void setGv_Proxy(int gv_Proxy) {
    this.gv_Proxy = gv_Proxy;
}



public boolean isGv_UseProxy() {
    return gv_UseProxy;
}



public void setGv_UseProxy(boolean gv_UseProxy) {
    this.gv_UseProxy = gv_UseProxy;
}



public String getGv_Version() {
    return gv_Version;
}



public void setGv_Version(String gv_Version) {
    this.gv_Version = gv_Version;
}


//end class
}

