/**
 * 
 */
package com.urt.util.http;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.FileRequestEntity;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.churchgroup.util.object.ObjectFunctions;
import com.churchgroup.util.string.StringFunctions;
import com.service.mail.EmailBean;

/**
 * @author Sreeram
 *
 */
public class URTHttpClient {

	protected transient final Log log = LogFactory.getLog(URTHttpClient.class);
    static final int    LOGON_PORT = 80;
    private static final String SMTP_HOST_NAME = "smtp.sendgrid.net";
    private static final String SMTP_AUTH_USER = "servers@uroomtech.com";
    private static final String SMTP_AUTH_PWD  = "UrT4770";
    //private static final String SMTP_AUTH_PWD  = "uroomtech123";
    
    private GetMethod authGetMethod;
    private PostMethod authPostMethod;
    private HttpClient httpClient;
    private Properties props;
    private Multipart multipart;
    private BodyPart bodyPart;	
    private  Authenticator auth;
    private String smtpHostName;
    private String smtpAuthUser;
    private String smtpAuthPwd;
    
    public String getSmtpHostName(){
    		return this.smtpHostName;
	}
	
	public void setSmtpHostName(String smtpHostName) {
		this.smtpHostName = smtpHostName;
	}

	public String getSmtpAuthUser(){
			return this.smtpAuthUser;
	}
	
	public void setSmtpAuthUser(String smtpAuthUser) {
		this.smtpAuthUser = smtpAuthUser;
	}

	public String getSmtpAuthPwd(){
			return this.smtpAuthPwd;
	}
	public void setSmtpAuthPwd(String smtpAuthPwd) {
		this.smtpAuthPwd = smtpAuthPwd;
	}
	/**
	 * @return the props
	 */
	public Properties getProps() {
		if (ObjectFunctions.isNullOrEmpty(this.props)) {
			this.props = new Properties();
		}
		return props;
	}

	/**
	 * @param props the props to set
	 */
	public void setProps(Properties props) {
		this.props = props;
	}

	/**
	 * @return the multipart
	 */
	public Multipart getMultipart() {
		if (ObjectFunctions.isNullOrEmpty(this.multipart)) {
			this.multipart = new MimeMultipart();
		}
		return multipart;
	}

	/**
	 * @param multipart the multipart to set
	 */
	public void setMultipart(Multipart multipart) {
		this.multipart = multipart;
	}

	/**
	 * @return the bodyPart
	 */
	public BodyPart getBodyPart() {
		if (ObjectFunctions.isNullOrEmpty(this.bodyPart)) {
			this.bodyPart = new MimeBodyPart();
		}
		return bodyPart;
	}

	/**
	 * @param bodyPart the bodyPart to set
	 */
	public void setBodyPart(BodyPart bodyPart) {
		this.bodyPart = bodyPart;
	}

	/**
	 * @return the auth
	 */
	public Authenticator getAuth() {
		if (ObjectFunctions.isNullOrEmpty(this.auth)) {
			this.auth = new SMTPAuthenticator();
		}
		return auth;
	}

	/**
	 * @param auth the auth to set
	 */
	public void setAuth(Authenticator auth) {
		this.auth = auth;
	}
	
	/**
	 * 
	 */
	public URTHttpClient() {
		 super();
		 /*this.smtpHostName = ServletActionContext.getServletContext().getInitParameter("SMTP_HOST_NAME");
		 this.smtpAuthUser = ServletActionContext.getServletContext().getInitParameter("SMTP_AUTH_USER");
		 this.smtpAuthPwd = ServletActionContext.getServletContext().getInitParameter("SMTP_AUTH_PWD");*/
	}

	public boolean postXml()
	{
			File input = new File("C:/Program Files/Apache Software Foundation/Tomcat 6.0/webapps/rides/userfiles/yahooMapRss.xml");
	        // Request content will be retrieved directly
	        // from the input stream
	        RequestEntity entity = new FileRequestEntity(input, "text/xml; charset=ISO-8859-1");
	        getAuthPostMethod().setRequestEntity(entity);
	        // Execute request
	        try {
	            int result = getHttpClient().executeMethod(getAuthPostMethod());
	            // Display status code
	            log.debug("Response status code: " + result);
	            // Display response
	            log.debug("Response body: ");
	            log.debug(getAuthPostMethod().getResponseBodyAsString());
	        }
	        catch(Exception ex)
	        {
	        	ex.printStackTrace();
				log.error("Something happened badly...." + ex.getMessage());
				return false;
	        }
	        finally {
	            // Release current connection to the connection pool once you are done
	            getAuthPostMethod().releaseConnection();
	        }
		
		return true;
	}
	
	public boolean postNameValuePair(String fromAddress, String[] toAddress, String subject, String body, boolean blind)
	{
		if (log.isDebugEnabled()) {
            log.debug("Entering 'postNameValuePair' method");
        }
		// Prepare login parameters
		try
		{
	        getProps().put("mail.transport.protocol", "smtp");
	        getProps().put("mail.smtp.host", SMTP_HOST_NAME);
	        getProps().put("mail.smtp.auth", "true");
	        
	        Session mailSession = Session.getInstance(getProps(), getAuth());
	        
	        Transport transport = mailSession.getTransport();
	        
	        MimeMessage message = new MimeMessage(mailSession);
	 
			getBodyPart().setContent(body, "text/html");
			getMultipart().addBodyPart(getBodyPart());
			
			message.setSubject(subject);
			message.setContent(getMultipart());
	        message.setFrom(new InternetAddress(fromAddress));
	        
	        if(!ObjectFunctions.isNullOrEmpty(toAddress)){
        	   String toAddressArray[]=toAddress;
        	   List<String> toAddressList = Arrays.asList(toAddressArray); 
        	   for (String toAddr : toAddressList)  
	           {
        		   if(!StringFunctions.isNullOrEmpty(toAddr)){
        			   message.addRecipient(Message.RecipientType.BCC, new InternetAddress(toAddr));
        			   log.debug("---toAddr----"+toAddr);
        		   }
	           }
	        }
	        transport.connect();
			if(!ObjectFunctions.isNullOrEmpty(message.getRecipients(Message.RecipientType.BCC))){
			   transport.sendMessage(message,message.getRecipients(Message.RecipientType.BCC));
			}
	        transport.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			log.error("Something happened badly...." + ex.getMessage());
			return false;
		}
		return true;
	}
	public boolean postNameValuePair(String fromAddress, String[] toAddress, String subject, String body,long custId,String sender,String fromWhom, boolean blind)
	{
		if (log.isDebugEnabled()) {
            log.debug("Entering 'postNameValuePair' method");
        }
		// Prepare login parameters
		try
		{
	        getProps().put("mail.transport.protocol", "smtp");
	        getProps().put("mail.smtp.host", SMTP_HOST_NAME);
	        getProps().put("mail.smtp.auth", "true");
	        
	        Session mailSession = Session.getInstance(getProps(), getAuth());
	        
	        Transport transport = mailSession.getTransport();
	        
	        MimeMessage message = new MimeMessage(mailSession);
	 
			getBodyPart().setContent(body, "text/html");
			getMultipart().addBodyPart(getBodyPart());
			
			message.setSubject(subject);
			message.setContent(getMultipart());
	        message.setFrom(new InternetAddress(fromAddress));
	        
	        if(!ObjectFunctions.isNullOrEmpty(toAddress)){
        	   String toAddressArray[]=toAddress;
        	   List<String> toAddressList = Arrays.asList(toAddressArray); 
        	   for (String toAddr : toAddressList)  
	           {
        		   if(!StringFunctions.isNullOrEmpty(toAddr)){
        			   message.addRecipient(Message.RecipientType.BCC, new InternetAddress(toAddr));
        			   log.debug("---toAddr----"+toAddr);
        		   }
	           }
	        }
	        transport.connect();
			if(!ObjectFunctions.isNullOrEmpty(message.getRecipients(Message.RecipientType.BCC))){
			   transport.sendMessage(message,message.getRecipients(Message.RecipientType.BCC));
			}
	        transport.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			log.error("Something happened badly...." + ex.getMessage());
			return false;
		}
		return true;
	}
	public boolean invokeSite()
	{
		if (log.isDebugEnabled()) {
            log.debug("Entering 'invokeSite' method");
        }
		// Prepare login parameters
		try
		{
			getAuthPostMethod().setRequestBody(new NameValuePair[] {});
	        getHttpClient().executeMethod(getAuthPostMethod());
	        log.error("Posted successfully no issue....");
	        int statuscode = getAuthPostMethod().getStatusCode();
	        log.error("Status :" + statuscode);
          //  Header header = getAuthPostMethod().getResponseHeader("status");
          //  if (header != null) {
          //      String newuri = header.getValue();
           //     log.debug("Redirect target: " + newuri); 
           // }
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			log.error("Something happened badly...." + ex.getMessage());
			return false;
		}
		finally
		{
			log.error("Releasing the connection....");
			getAuthPostMethod().releaseConnection();
		}
		return true;
	}

	/**
	 * @return the authGetMethod
	 */
	public GetMethod getAuthGetMethod() {
		if(ObjectFunctions.isNullOrEmpty(this.authGetMethod))
		{
				this.authGetMethod=new GetMethod(SMTP_HOST_NAME);
		}
		return this.authGetMethod;
	}

	/**
	 * @param authGetMethod the authGetMethod to set
	 */
	public void setAuthGetMethod(GetMethod authGetMethod) {
		this.authGetMethod = authGetMethod;
	}

	/**
	 * @return the authPostMethod
	 */
	public PostMethod getAuthPostMethod() {
		if(ObjectFunctions.isNullOrEmpty(this.authPostMethod))
		{
				this.authPostMethod=new PostMethod(SMTP_HOST_NAME);
		}
		return this.authPostMethod;
	}

	/**
	 * @param authPostMethod the authPostMethod to set
	 */
	public void setAuthPostMethod(PostMethod authPostMethod) {
		this.authPostMethod = authPostMethod;
	}

	/**
	 * @return the httpClient
	 */
	public HttpClient getHttpClient() {
		if(ObjectFunctions.isNullOrEmpty(this.httpClient))
		{
			this.httpClient=new HttpClient();
		}
		return httpClient;
	}

	/**
	 * @param httpClient the httpClient to set
	 */
	public void setHttpClient(HttpClient httpClient) {
		this.httpClient = httpClient;
	}
	public static void main(String[] args) throws Exception 
	{
		URTHttpClient urtHttpClient=new URTHttpClient();
		String[] toAddress = new String[2];
		toAddress[0]="ramarao@uroomtech.com";
		toAddress[1]="ramprasad@uroomtech.com";
		if(urtHttpClient.postNameValuePair("sreeram@uroomtech.com", toAddress, "Testing the http client fucntionality", "Hello this is a test from http Client",Boolean.TRUE))
		{
			System.err.println("Hurray success....");
		}
	}
	public boolean postNameValuePair(EmailBean emailBean){
		if (log.isDebugEnabled()) {
            log.debug("Entering 'postNameValuePair' method");
        }
		// Prepare login parameters
		try
		{
			if(!ObjectFunctions.isNullOrEmpty(emailBean)){
				
				getProps().put("mail.transport.protocol", "smtp");
				getProps().put("mail.smtp.host", SMTP_HOST_NAME);
				getProps().put("mail.smtp.auth", "true");
				getProps().put("mail.smtp.starttls.enable", "true");
		        
		        Session mailSession = Session.getInstance(getProps(), getAuth());
				
		        Transport transport = mailSession.getTransport();
		        
		        MimeMessage message = new MimeMessage(mailSession);
		 
				getBodyPart().setContent(emailBean.getBody(), "text/html");
				getMultipart().addBodyPart(getBodyPart());
				
				message.setSubject(emailBean.getSubject());
				message.setContent(getMultipart());
		        message.setFrom(new InternetAddress(emailBean.getFromAddress()));
		        
		        if(!ObjectFunctions.isNullOrEmpty(emailBean.getToAddress())){
	        	   String toAddressArray[]=emailBean.getToAddress();
	        	   List<String> toAddressList = Arrays.asList(toAddressArray); 
	        	   if(ObjectFunctions.isNotNullOrEmpty(toAddressList)){
		        	   for (String toAddr : toAddressList)  
			           {  
		        		 if(!StringFunctions.isNullOrEmpty(toAddr)){
		        			 message.addRecipient(Message.RecipientType.BCC, new InternetAddress(toAddr));
		        			 log.debug("---toAddr----"+toAddr);
		        		 }
			           }
	        	   }
		        }
				if(!ObjectFunctions.isNullOrEmpty(emailBean.getCcAddress())){
				    String ccAddressArray[]=emailBean.getCcAddress(); 
					List<String> ccAddressList = Arrays.asList(ccAddressArray);  
					if(ObjectFunctions.isNotNullOrEmpty(ccAddressList)){
						for (String ccAddr : ccAddressList)  
				        {  
							if(!StringFunctions.isNullOrEmpty(ccAddr)){
								message.addRecipient(Message.RecipientType.CC, new InternetAddress(ccAddr));
								log.debug("---ccAddr----"+ccAddr);
							}
				        }
					}
				}
				if(!ObjectFunctions.isNullOrEmpty(emailBean.getBccAddress())){
				    String bccAddressArray[]=emailBean.getBccAddress();
				    List<String> bccAddressList = Arrays.asList(bccAddressArray);  
				    if(ObjectFunctions.isNotNullOrEmpty(bccAddressList)){
					    for (String bccAddr : bccAddressList)  
					    {
					    	if(!StringFunctions.isNullOrEmpty(bccAddr)){
						    	message.addRecipient(Message.RecipientType.BCC, new InternetAddress(bccAddr));
						    	log.debug("---bccAddr----"+bccAddr);
					    	}
					    }
				    }
				}
				transport.connect();
				if(!ObjectFunctions.isNullOrEmpty(message.getRecipients(Message.RecipientType.TO))){
				   transport.sendMessage(message,message.getRecipients(Message.RecipientType.TO));
				}
				if(!ObjectFunctions.isNullOrEmpty(message.getRecipients(Message.RecipientType.CC))){
				   transport.sendMessage(message,message.getRecipients(Message.RecipientType.CC));
				}
				if(!ObjectFunctions.isNullOrEmpty(message.getRecipients(Message.RecipientType.BCC))){
				   transport.sendMessage(message,message.getRecipients(Message.RecipientType.BCC));
				}
		        transport.close();
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			log.error("Something happened badly...." + ex.getMessage());
			return false;
		}
		emailBean=null;
		return true;		
	}
	 private class SMTPAuthenticator extends javax.mail.Authenticator {
	        public PasswordAuthentication getPasswordAuthentication() {
	           String username = SMTP_AUTH_USER;
	           String password = SMTP_AUTH_PWD;
	           return new PasswordAuthentication(username, password);
	        }
	 }
}
