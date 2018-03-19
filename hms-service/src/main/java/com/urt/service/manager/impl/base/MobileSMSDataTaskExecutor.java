/********************************************************************
* HYNIVA
* All Rights Reserved 
*
* File: MobileSMSDataTaskExecutor.java
********************************************************************
*
*  Ver   Date            Author                Description
*  ====  ========        ============          ==================
*  0.1   Sep 10, 2015    Sreeram			   Created
/********************************************************************/
package com.urt.service.manager.impl.base;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.churchgroup.common.constants.Constants;
import com.churchgroup.util.object.ObjectFunctions;
import com.churchgroup.util.string.StringFunctions;
import com.thebasics.msgclub.test.SendSmsDemo;
import com.urt.json.JSONArray;
import com.urt.json.JSONException;
import com.urt.json.JSONObject;
import com.urt.persistence.model.common.MessageDetailsTracking;
import com.urt.persistence.model.common.Messages;
import com.urt.service.manager.interfaces.user.UserManager;

@Component
public class MobileSMSDataTaskExecutor {
	
	private static final Log log = LogFactory.getLog(MobileSMSDataTaskExecutor.class);
	/*private class MobileSMSDataTask implements Runnable {

		private Messages message;
	    public MobileSMSDataTask(Messages message ) {
	          this.message =message;
	    }

	    public void run() {
	    	int smscount = 0;
			try {
					 Thread.sleep(1000*60*5);
					 synchronized (this) {
						 if("MsgClub".equalsIgnoreCase(message.getSmsProviders().getServiceProvider())){
							 StringBuilder statusFromMsgClub = new StringBuilder();
							 URLConnection urlConn = null;
							 InputStreamReader inStreamReader = null;
							 try {
								URL url = new URL("http://msg.msgclub.net/rest/services/delivery/pullDeliveryReport?AUTH_KEY=53b5b4f961b86fea1c3aeebbe5dfb6e&requestId="+message.getGuid()+"");
								urlConn = url.openConnection();
								if (urlConn != null)
									urlConn.setReadTimeout(60 * 1000);
								if (urlConn != null && urlConn.getInputStream() != null) {
									inStreamReader = new InputStreamReader(urlConn.getInputStream(),
											Charset.defaultCharset());
									BufferedReader bufferedReader = new BufferedReader(inStreamReader);
									if (bufferedReader != null) {
										int cp;
										while ((cp = bufferedReader.read()) != -1) {
											statusFromMsgClub.append((char) cp);
										}
										bufferedReader.close();
									}
								}
								inStreamReader.close();
							 } catch (Exception e) {
								  e.printStackTrace();
							 } 
							 Map<String, Character> mobileNumberStatusMap = new HashMap<String, Character>();
							 smscount = deliverySMSCountFromMsgClub(statusFromMsgClub.toString(),mobileNumberStatusMap);
							 for(MessageDetailsTracking detailsTracking : message.getMessageDetailsTracking()){
						    	if(mobileNumberStatusMap.containsKey(detailsTracking.getMobileNumber()))
						    		detailsTracking.setDeliveryStatus(mobileNumberStatusMap.get(detailsTracking.getMobileNumber()));
							 }
							 message.setSuccessMobileNos(successMobileNos.toString());
							 message.setFailedMobileNos(failedMobileNos.toString());
						 }else if("ValueFirst".equalsIgnoreCase(message.getSmsProviders().getServiceProvider())){
							//Messages msg = (Messages)MobileSMSDataTaskExecutor.this.userDao.get(Messages.class, "guid='"+message.getGuid()+"'");
							smscount = MobileSMSDataTaskExecutor.this.sendStatusRequest(message,message.getSmsProviders().getUrl());
							message.setSmsCount(smscount);
							message.setDeliveredSmsStatus(true);
							MobileSMSDataTaskExecutor.this.userManager.save(message);
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
							if(!ObjectFunctions.isNullOrEmpty(file)){
								FileWriter fw = new FileWriter(file.getAbsoluteFile());
								BufferedWriter bw = new BufferedWriter(fw);
								bw.write(message.getResString());
								bw.close();
							}
						 }
						 message = null;
					}
					Thread.interrupted();
				}catch (InterruptedException e) {
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	  }*/

	  @Autowired
	  public UserManager userManager;

	  @Autowired
	  private TaskExecutor taskExecutor;
	  
	  public static String USER_NAME = null;
	  public static String PASSWORD = null;
	  public static Map<Integer, String> mobileNumbersMap = null;
	  public static StringBuffer successMobileNos = null;
	  public static StringBuffer failedMobileNos = null;

	public static String sendSMS(Messages message) {
		mobileNumbersMap = new HashMap<Integer, String>();
		try {
			String guid = null;
			if ("MsgClub".equalsIgnoreCase(message.getSmsProviders().getServiceProvider())) {
				log.debug(message);
				SendSmsDemo send = new SendSmsDemo();
				//String sendSmsFromMsgClub = send.sendSmsPost(message.getSmsProviders().getUrl(),"53b5b4f961b86fea1c3aeebbe5dfb6e", message.getMessageDescription(), message.getSmsSenderId(), "1", message.getMobileNumbers(), "english", "", "", "");
				
				String sendSmsFromMsgClub = send.sendSmsPost(message.getSmsProviders().getUrl(),message.getSmsProviders().getProviderKey(), message.getMessageDescription(), message.getSmsSenderId(), "1", message.getMobileNumbers(), "english", "", "", "");
				try {
					JSONObject jsonObj = new JSONObject(sendSmsFromMsgClub);
					guid = jsonObj.getString("response");
					log.debug("MsgClub Guid Response::"+guid);
					log.debug(sendSmsFromMsgClub);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			} 
			else if ("mobicomm".equalsIgnoreCase(message.getSmsProviders().getServiceProvider()))
			{
				try {
					String mainUrl = message.getSmsProviders().getUrl() + "?user="+message.getSmsProviders().getUserName()+"&key="+message.getSmsProviders().getProviderKey()+"&senderid="+message.getSmsSenderId()+"&accusage=1&mobile="+message.getMobileNumbers()+"&message="+URLEncoder.encode(message.getMessageDescription());
					
					URLConnection myURLConnection=null;
                    URL myURL=null;
                    BufferedReader reader=null;
                    
                    try
                    {
	                    //prepare connection
	                    myURL = new URL(mainUrl);
	                    myURLConnection = myURL.openConnection();
	                    myURLConnection.connect();
	                    reader= new BufferedReader(new InputStreamReader(myURLConnection.getInputStream()));
	                    //reading response
	                    String response;
	                    StringBuilder codeMobile = new StringBuilder();
	                    while ((response = reader.readLine()) != null)
	                    {
	                    	//print response
	                    	 log.info(response);
	                    	 if(!StringFunctions.isNullOrEmpty(response))
	                    	 {
	                    		 String res[] = response.split(",");
	 	                    	 codeMobile.append(res[2]+"-"+res[2]+",");
	                    	 }
	                    }
	                    
	                    //finally close connection
	                    reader.close();
	                    return codeMobile.toString();
                    }
                    catch (IOException e)
                    {
                    	e.printStackTrace();
                    	return "0";
                    }
					/*URL url = new URL(urlPath);
					HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
					httpURLConnection.setRequestMethod("GET");
					log.info(httpURLConnection.getResponseCode());*/
					
					/*String encodedUrl = URLEncoder.encode(urlPath, "UTF-8");
					
					log.info(urlPath);
					
					HttpClient client = new HttpClient();
				    GetMethod method = new GetMethod(urlPath);
				    client.executeMethod(method);*/
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			else if ("ValueFirst".equalsIgnoreCase(message.getSmsProviders().getServiceProvider()) || "InternationalValueFirst".equalsIgnoreCase(message.getSmsProviders().getServiceProvider())) 
			{
				/*USER_NAME = "upperroom";
				PASSWORD = "upper123";*/
				USER_NAME = message.getSmsProviders().getUserName();
				PASSWORD = message.getSmsProviders().getPassword();
				
				guid = prepareSmsToValueFirst(message);
				USER_NAME = null;
				PASSWORD = null;
			} 
			
			/*else if ("InternationalValueFirst".equalsIgnoreCase(message.getSmsProviders().getServiceProvider())) {
				USER_NAME = "hynivaxml";
				PASSWORD = "hynivaxml12";
				guid = prepareSmsToValueFirst(message);
				USER_NAME = null;
				PASSWORD = null;
			}*/
			return guid;
		} catch (Exception e) {
			e.getMessage();
		}
		return "";
	}
	
	public static String prepareSmsToValueFirst(Messages message) {
		try {
			HttpClient client = new HttpClient();
			PostMethod method = new PostMethod(message.getSmsProviders().getUrl());
			String dataParameter = getDataParameter(message.getSmsSenderId(),message.getMobileNumbers(),StringFunctions.encodeHTML(message.getMessageDescription().replace("\n", " ")));
			log.debug("After Calling sendSMS.....Success--------");
			method.addParameter("data", dataParameter);
			method.addParameter("action", "send");
			client.executeMethod(method);
			String xmlRes = method.getResponseBodyAsString();
			log.debug("Value First XML response::"+xmlRes);
			String guid = parseXmlAsString(xmlRes, message);
			if (!StringFunctions.isNullOrEmpty(message.getMobileNumbers())) {
				String mobileNumberArray[] = message.getMobileNumbers().split(",");
				for (int i = 0; i < mobileNumberArray.length; i++) {
					mobileNumbersMap.put(i, mobileNumberArray[i]);
				}
			}
			return guid;
		} catch (HttpException e) {
			e.getMessage();
		} catch (IOException e) {
			e.getMessage();
		}
		return null;
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
			    addressTags.append(MessageFormat.format(Constants.ADDRESS_TAG, new Object[] { from, address,new Integer(COUNT) }));
			}
		    }
		    return MessageFormat.format(Constants.DATA_TAG_SEND,new Object[] { USER_NAME, PASSWORD, text, addressTags });
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

	    public int sendStatusRequest(Messages message, String url) {
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
				    	if(mobileNumberStatusMap.containsKey(detailsTracking.getMobileNumber().replaceFirst("\\+91-", "")))
				    		detailsTracking.setDeliveryStatus(mobileNumberStatusMap.get(detailsTracking.getMobileNumber().replaceFirst("\\+91-", "")));
				    	message.addMessageDetails(detailsTracking);
				    }
				    message.setSmsCount(credits);
					message.setDeliveredSmsStatus(true);
				    message.setSuccessMobileNos(successMobileNos.toString());
					message.setFailedMobileNos(failedMobileNos.toString());
					userManager.save(message);
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
				    /*if (!ObjectFunctions.isNullOrEmpty(message.getCustomer())) {
				    	message.setSmsSenderId(message.getCustomer().getSender());
				    }*/
				    if (StringFunctions.isNullOrEmpty(message.getSmsSenderId())) {
				    	message.setSmsSenderId("hyniva");
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
						//taskExecutor.execute(new MobileSMSDataTask(message));
						return message.getGuid();
				    }
				} catch (Exception ex) {
				    ex.getMessage();
				}
				return null;
	    }
	    public static int deliverySMSCountFromMsgClub(String smsRecords,Map<String, Character> mobileNumberStatusMap) {
			int successMsgCount = 0;
			try {
				successMobileNos = new StringBuffer();
				failedMobileNos = new StringBuffer();
				log.debug(smsRecords.toString());
				JSONArray msgResponse=new JSONArray(smsRecords);
				JSONObject msgObject=null;
				for(int i=0;i < msgResponse.length();i++)
				{
					msgObject=msgResponse.getJSONObject(i);
					//String status = msgObject.getString("status");
					String statusCodes = msgObject.getString("statusCode");
					//log.debug(status);
					//log.debug(statusCodes);
					log.debug("reasonCode===" + statusCodes);
					String mobileNumber = msgObject.getString("mobileNumber");
					log.debug(mobileNumber);
					if (statusCodes.equals("4000")){ 
					    successMsgCount++;
					    if(!ObjectFunctions.isNullOrEmpty(mobileNumber)){
				    		String mobileNo = mobileNumber.replaceFirst("91", "");
				    		mobileNumberStatusMap.put(mobileNo, 'S');
				    		successMobileNos.append(mobileNo);
				    		successMobileNos.append(",");
					    }
					}
					else{
						if(!ObjectFunctions.isNullOrEmpty(mobileNumber)){
				    		mobileNumberStatusMap.put(mobileNumber.replace("91", ""), 'F');
				    		log.debug(mobileNumber);
				    		failedMobileNos.append(mobileNumber);
				    		failedMobileNos.append(",");
					    }
					}
				}	
			    return successMsgCount;
			} catch (Exception e) {
			    e.printStackTrace();
			}
			return successMsgCount;
	   }
}