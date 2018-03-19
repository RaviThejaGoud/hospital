package com.urt.util.xml;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.churchgroup.common.constants.Constants;
import com.churchgroup.util.object.ObjectFunctions;
import com.churchgroup.util.string.StringFunctions;
import com.urt.persistence.model.common.MessageDetailsTracking;
import com.urt.persistence.model.common.Messages;

public class SendSms {
	
	private static final Log log = LogFactory.getLog(SendSms.class);
    public static final String USER_NAME = "upperroom";
    public static final String PASSWORD = "upper123";
    public static Map<Integer, String> mobileNumbersMap = null;
    public static StringBuffer successMobileNos = null;
    public static StringBuffer failedMobileNos = null;

    public static String sendSMS(Messages message) {
    	 mobileNumbersMap = new HashMap<Integer, String>();
	try {
		HttpClient client = new HttpClient();
	    PostMethod method = new PostMethod(message.getSmsProviders().getUrl());
	    String dataParameter = getDataParameter(message.getSmsSenderId(), message.getMobileNumbers(),
		    StringFunctions.encodeHTML(message.getMessageDescription().replace("\n", " ")));
	    log.debug("After Calling sendSMS.....Success--------");
	    //log.debug("Data---"+dataParameter+"------------");
	    method.addParameter("data", dataParameter);
	    method.addParameter("action", "send");
	    client.executeMethod(method);
	    String xmlRes = method.getResponseBodyAsString();
	    //log.debug(xmlRes);
	    String guid = SendSms.parseXmlAsString(xmlRes, message);
	   // log.debug("guid----->" + message.getGuid());
	    if(!StringFunctions.isNullOrEmpty(message.getMobileNumbers()))
	    {
	    	String mobileNumberArray[] = message.getMobileNumbers().split(",");
	    	for(int i=0; i<mobileNumberArray.length;i++)
	    	{
	    		mobileNumbersMap.put(i, mobileNumberArray[i]);
	    	}
	    }
	    return guid;
	} catch (HttpException e) {
	    e.getMessage();
	} catch (IOException e) {
	    e.getMessage();
	}
	return "";
    }

    private static String getDataParameter(String from, String toList, String text) {
	try {
	    StringBuffer addressTags = new StringBuffer();
	    StringTokenizer strToken = new StringTokenizer(toList, ",");
	    int COUNT = 0;
	    if (strToken != null) {
		for (int i = 0; strToken.hasMoreTokens();) {
		    String address = strToken.nextToken();
		    COUNT += i + 1;
		    addressTags.append(MessageFormat.format(Constants.ADDRESS_TAG, new Object[] { from, address,
			    new Integer(COUNT) }));
		}
	    }
	    return MessageFormat.format(Constants.DATA_TAG_SEND,
		    new Object[] { USER_NAME, PASSWORD, text, addressTags });
	} catch (Exception e) {
	    e.getMessage();
	}
	return null;
    }

    public static String parseXmlAsString(String xmlRecords, Messages message) {
	try {
	    DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
	    InputSource is = new InputSource();
	    is.setCharacterStream(new StringReader(xmlRecords));
	    Document doc = db.parse(is);
	    NodeList nodes = doc.getElementsByTagName("GUID");
	    String guid = "";
	    for (int i = 0; i < nodes.getLength(); i++) 
	    {
			Element element = (Element) nodes.item(i);
			log.debug("---guid---" + element.getAttribute("GUID"));
			if (StringFunctions.isNotNullOrEmpty(element.getAttribute("GUID"))) {
			    guid = element.getAttribute("GUID");
			}
	    }
	    return guid;
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return "";
    }

    public static int sendStatusRequest(Messages message, String url) {
	int credits = 0;
	try {
	    HttpClient client = new HttpClient();
	    PostMethod method = new PostMethod(url);
	    String dataParameter = getDataParameter(message.getGuid(), message.getSmsCount());
	    //log.debug(dataParameter); 
	    method.addParameter("data", dataParameter);
	    method.addParameter("action", "status");
	    client.executeMethod(method);
	    String requestStatus = method.getResponseBodyAsString();
	    message.setResString(requestStatus);
	    message.setResString(requestStatus);
	    Map<String, Character> mobileNumberStatusMap = new HashMap<String, Character>();
	    credits = parseXmlAsString1(requestStatus,mobileNumberStatusMap);
	    for(MessageDetailsTracking detailsTracking : message.getMessageDetailsTracking()){
	    	if(mobileNumberStatusMap.containsKey(detailsTracking.getMobileNumber()))
	    		detailsTracking.setDeliveryStatus(mobileNumberStatusMap.get(detailsTracking.getMobileNumber()));
	    }
	    message.setSuccessMobileNos(successMobileNos.toString());
		message.setFailedMobileNos(failedMobileNos.toString());
	} catch (HttpException e) {
	    e.getMessage();
	} catch (IOException e) {
	    e.getMessage();
	}
	return credits;
    }

    public static int parseXmlAsString1(String xmlRecords,Map<String, Character> mobileNumberStatusMap) {
	int successMsgCount = 0;
	try {
		successMobileNos = new StringBuffer();
		failedMobileNos = new StringBuffer();
	    DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
	    InputSource is = new InputSource();
	    is.setCharacterStream(new StringReader(xmlRecords));
	    Document doc = db.parse(is);
	    NodeList nodes = doc.getElementsByTagName("STATUS");
	    log.debug("size of node====" + nodes.getLength());
	    for (int i = 0; i < nodes.getLength(); i++) 
	    {
			Element element = (Element) nodes.item(i);
			// log.debug("---1---"+element.getAttribute("GUID"));
			String reasonCode = element.getAttribute("REASONCODE");
			String seqNos = element.getAttribute("SEQ");
			log.debug("reasonCode===" + reasonCode);
			element.getAttribute("ERR");
			int seqNo = Integer.parseInt(seqNos);
			if (reasonCode.equals("000")) 
			{
			    successMsgCount++;
			    if(!ObjectFunctions.isNullOrEmpty(mobileNumbersMap))
			    {
			    	if(!StringFunctions.isNullOrEmpty(mobileNumbersMap.get(seqNo-1)))
	    			{
			    		String mobileNo = mobileNumbersMap.get(seqNo-1).replaceFirst("\\+91-", "");
			    		mobileNumberStatusMap.put(mobileNo, 'S');
			    		successMobileNos.append(mobileNo.replaceFirst("91", ""));
			    		successMobileNos.append(",");
	    			}
			    }
			}
			else
			{
				if(!ObjectFunctions.isNullOrEmpty(mobileNumbersMap))
			    {
			    	if(!StringFunctions.isNullOrEmpty(mobileNumbersMap.get(seqNo-1)))
	    			{
			    		mobileNumberStatusMap.put(mobileNumbersMap.get(seqNo-1).replace("+91-", ""), 'F');
			    		failedMobileNos.append(mobileNumbersMap.get(seqNo-1).replace("+91-", ""));
			    		failedMobileNos.append(",");
	    			}
			    }
			}
	    }
	    return successMsgCount;
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return successMsgCount;
    }

    private static String getDataParameter(String uuid, int j) {
		StringBuffer addressTags = new StringBuffer();
		for (int i = 0; i < j; i++) {
		    addressTags.append(MessageFormat.format(Constants.STATUS_TAG, new Object[] { new Integer(i + 1) }));
		}
		log.debug("STATUS_TAG----" + addressTags);
		log.debug("DATA_TAG-----" + Constants.DATA_TAG_STATUS_REQ);
		return MessageFormat.format(Constants.DATA_TAG_STATUS_REQ, new Object[] { USER_NAME, PASSWORD, uuid,
			addressTags });
    }

    public static String prepareSendSMS(Messages message) {

	try {
	    if (!ObjectFunctions.isNullOrEmpty(message.getCustomer())) {
		message.setSmsSenderId(message.getCustomer().getSender());
	    }
	    if (StringFunctions.isNullOrEmpty(message.getSmsSenderId())) {
		message.setSmsSenderId("urtedu");
	    }
	    message.setMessageDescription(message.getMessageDescription() + message.getSmsSenderId());
	    //log.debug("smsText----" + message.getMessageDescription());
	    if (!ObjectFunctions.isNullOrEmpty(message.getSmsProviders())
		    && StringFunctions.isNotNullOrEmpty(message.getSmsProviders().getUrl())) {
		message.setGuid(sendSMS(message));
		//message.setSuccessMobileNos(successMobileNos.toString());
		//message.setFailedMobileNos(failedMobileNos.toString());
		File destFile = null;
		File file = null;
		if (StringFunctions.isNotNullOrEmpty(message.getGuid()) && StringFunctions.isNotNullOrEmpty(message.getSmsResXmlLocation())) {
		    destFile = new File(message.getSmsResXmlLocation());
		    if (destFile.mkdirs()) {
			log.debug("Directories Created");
		    } else {
			log.debug("Directories Not Created");
		    }
		    file = new File(message.getSmsResXmlLocation(), message.getGuid() + ".xml");
		}
		SmsDeliveredThread smsDeliveredThread = new SmsDeliveredThread();
		smsDeliveredThread.setHostUrl(message.getSmsProviders().getUrl());
		smsDeliveredThread.setFile(file);
		smsDeliveredThread.setGuid(message.getGuid());
		Thread importThread = new Thread(smsDeliveredThread);
		importThread.start();
		return message.getGuid();
	    }
	} catch (Exception ex) {
	    ex.getMessage();
	}
	return null;
    }
}
