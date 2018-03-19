package com.urt.util.email;

import java.io.File;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.churchgroup.util.object.ObjectFunctions;
import com.churchgroup.util.string.StringFunctions;
import com.service.mail.EmailBean;

/** Class for sending e-mail messages based on Velocity templates
 * or with attachments.
 * <p>
 * <a href="MailsSender.java.html"><i>View Source</i></a>
 * </p>
 * 
 * @author Sreeramulu J */
public class MailSenderManager {
    protected static final Log log = LogFactory.getLog(MailSenderManager.class);
    private JavaMailSenderImpl mailSender;
    public String contactEmail;
    public String contactPassword;
    
    
    public String getContactEmail() {
		return contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	public String getContactPassword() {
		return contactPassword;
	}

	public void setContactPassword(String contactPassword) {
		this.contactPassword = contactPassword;
	}
	
	public void setMailSender(JavaMailSenderImpl mailSender) {
		this.mailSender = mailSender;
    }

	
	public MailSenderManager(String contactEmail, String contactPassword) {
		super();
		this.contactEmail = contactEmail;
		this.contactPassword = contactPassword;
	}
	
	

	public MailSenderManager() {
		super();
		// TODO Auto-generated constructor stub
	}

	/** Send a simple message with pre-populated values.
     * 
     * @param msg */
    public void send(SimpleMailMessage msg) {
	try {
	    mailSender.send(msg);
	} catch (MailException ex) {
	    // log it and go on
	    log.error(ex.getMessage());
	}
    }

    /** Convenience method for sending messages with attachments.
     * 
     * @param emailAddresses
     * @param resource
     * @param bodyText
     * @param subject
     * @param attachmentName
     * @throws MessagingException
     * @author Ben Gill */
    public void sendMessage(String[] emailAddresses, ClassPathResource resource, String bodyText, String subject,
	    String attachmentName) throws MessagingException {
	MimeMessage message = ((JavaMailSenderImpl) mailSender).createMimeMessage();

	// use the true flag to indicate you need a multipart message
	MimeMessageHelper helper = new MimeMessageHelper(message, true);

	helper.setTo(emailAddresses);
	helper.setText(bodyText);
	helper.setSubject(subject);

	helper.addAttachment(attachmentName, resource);

	((JavaMailSenderImpl) mailSender).send(message);
    }

    /** A convenience method for sending html emails.
     * 
     * @param fromAddress
     * @param emailAddresses
     * @param subject
     * @param bodyText
     * @param blind
     *            set to <code>true</code> to add recipients to the bcc field.
     * @throws MessagingException */
    public void sendHtmlMail(String fromAddress, String[] emailAddresses, String subject, String bodyText, boolean blind)
	    throws MessagingException {

	try {
	    MimeMessage message = getMailSender().createMimeMessage();

	    // use the true flag to indicate you need a multipart message
	    MimeMessageHelper helper = new MimeMessageHelper(message, true);
	    if (!ObjectFunctions.isNullOrEmpty(emailAddresses)) {
		log.debug("Email Address : " + emailAddresses.toString());
		if (blind) {
		    helper.setBcc(emailAddresses);
		} else
		    helper.setTo(emailAddresses);
	    } else {
		log.debug("CC List not found...");
	    }
	    log.debug("Email fromAddress : " + fromAddress);
	    helper.setFrom(fromAddress);
	    helper.setSubject(subject);
	    message.setContent(bodyText, "text/html");
	    log.debug(" E-Mail Body: " + bodyText);
	    getMailSender().send(message);
	} catch (MailException me) {
	    me.getMessage();
	    me.printStackTrace();
	}

    }

    public int sendHtmlMail(EmailBean emailBean) throws MessagingException {
	try {
	    MimeMessage message = getMailSender().createMimeMessage();
	    // use the true flag to indicate you need a multipart message
	    MimeMessageHelper helper = new MimeMessageHelper(message, true);
	    // if (!ObjectFunctions.isNullOrEmpty(emailBean.getToAddress())) {  //For add recipients in To address
	    if (!ObjectFunctions.isNullOrEmpty(emailBean.getBccAddress())) { //For add recipients in to the bcc address 
			if (emailBean.getBlind()) {
				 helper.setTo(emailBean.getBccAddress());  //For add recipients into the to address 
			    //helper.setBcc(emailBean.getToAddress());  //For add recipients into Bcc address
			}else{
				if(!ObjectFunctions.isNullOrEmpty(emailBean.getBody()))
				helper.setBcc(emailBean.getBccAddress());//For add recipients into the bcc address 
				 //helper.setTo(emailBean.getToAddress()); //For add recipients in To address
			}
	    }else{
	    	if (!ObjectFunctions.isNullOrEmpty(emailBean.getToAddress())){
	    		if (emailBean.getBlind()) {
					 helper.setBcc(emailBean.getToAddress());  //For add recipients into the to address 
				}else{
					helper.setTo(emailBean.getToAddress());//For add recipients into the bcc address 
				}
	    	}
	    	log.debug("CC List not found...");
	    }
	    helper.setFrom(emailBean.getFromAddress());
	    helper.setSubject(emailBean.getSubject());
	    if(!ObjectFunctions.isNullOrEmpty(emailBean.getAttachmentFiles()))
	    {
	    	String[] attachmentFiles = emailBean.getAttachmentFiles();
	    	FileSystemResource file =  null;
	    	for(int i=0;i<attachmentFiles.length;i++)
	    	{
	    		if(!StringFunctions.isNullOrEmpty(attachmentFiles[i]))
		    	{
	    			//log.debug(attachmentFiles[i]);
	    			file = new FileSystemResource(new File(attachmentFiles[i]));
			    	helper.addAttachment(file.getFilename(), file);
		    	}
	    	}
	    }
	    helper.setText(emailBean.getBody(), true);
	   //message.setContent(emailBean.getBody(), "text/html");
	    getMailSender().send(message);
	} catch (MailException me) {
	    me.getMessage();
	    me.printStackTrace();
	    String message = me.getMessage();
		if(message.contains("javax.mail.AuthenticationFailedException"))
			return 1;
		else if (message.contains("Connection timed out")) {
			return 2;
		}
		else
			return 3;
			
	}
	return 0;
    }

    public void sendHtmlMail(String fromAddress, String[] emailAddresses, String subject, String bodyText, String[] ccAddress, boolean blind) throws MessagingException {
		try {
			MimeMessage message = getMailSender().createMimeMessage();
			// use the true flag to indicate you need a multipart message
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			if (!ObjectFunctions.isNullOrEmpty(emailAddresses)) {
				log.debug("Email Address : " + emailAddresses.toString());
				if (blind) {
					helper.setBcc(emailAddresses);
				} else
					helper.setTo(emailAddresses);
			} else {
				log.debug("CC List not found...");
			}

			log.debug("Email fromAddress : " + fromAddress);
			helper.setFrom(fromAddress);
			if (!ObjectFunctions.isNullOrEmpty(ccAddress)) {
				helper.setCc(ccAddress);
			}
			helper.setSubject(subject);
			message.setContent(bodyText, "text/html");
			log.debug(" E-Mail Body: " + bodyText);
			getMailSender().send(message);
		} catch (MailException me) {
			me.getMessage();
			me.printStackTrace();
		}

    }

    /** A convenience method for sending html emails.
     * 
     * @param fromAddress
     * @param emailAddresses
     * @param subject
     * @param xt
     * @param blind
     *            set to <code>true</code> to add recipients to the bcc field.
     * @throws MessagingException */
    public void sendHtmlMailWithCC(String fromAddress, String[] emailAddresses, String ccAddresses, String subject, StringBuffer xt, boolean blind) throws MessagingException {
		try {
			MimeMessage message = getMailSender().createMimeMessage();
			// use the true flag to indicate you need a multipart message
			MimeMessageHelper helper = new MimeMessageHelper(message, true);

			helper.setFrom(fromAddress);
			if (!ObjectFunctions.isNullOrEmpty(emailAddresses)) {
				log.debug("Email Address : " + emailAddresses.toString());
				if (blind) {
					helper.setBcc(emailAddresses);
				} else
					helper.setTo(emailAddresses);
			} else {
				log.debug("CC List not found...");
			}
			if (!StringFunctions.isNullOrEmpty(ccAddresses)) {
				ccAddresses = StringFunctions.stripSymbols(ccAddresses, "'`");
				helper.setCc(ccAddresses);
			}
			helper.setSubject(subject);
			message.setContent(xt.toString(), "text/html");
			log.debug(" E-Mail Body: " + xt.toString());
			getMailSender().send(message);
		} catch (MailException me) {
			me.getMessage();
			me.printStackTrace();
		}
    }

    /** @return the mailSender */
	public JavaMailSenderImpl getMailSender() {

		if (this.mailSender == null) {
			this.mailSender = new JavaMailSenderImpl();
		}
		// log.debug(this.contactEmail+"-"+this.contactPassword);
		contactEmail = this.getContactEmail();
		contactPassword = this.getContactPassword();

		if (StringFunctions.isNullOrEmpty(contactEmail) & StringFunctions.isNullOrEmpty(contactPassword)) {
			contactEmail = "messages@eazyschool.com";
			contactPassword = "9JsPrs7CT89";
		}
		int port = 0;
		String host = null;
		String[] emailArry = contactEmail.split("@");
		String[] domineArry = (emailArry[1].toString()).split("\\.");
		String provider = domineArry[0].toString().toLowerCase();

		if ("gmail".equalsIgnoreCase(provider.toLowerCase())) {
			port = 587;
			host = "smtp.gmail.com";
		} else if ("yahoo".equalsIgnoreCase(provider.toLowerCase())) {
			port = 587;
			host = "smtp.mail.yahoo.com";
		} else if ("hotmail".equalsIgnoreCase(provider.toLowerCase())) {
			port = 25;
			host = "smtp.live.com";
		} else if ("live".equalsIgnoreCase(provider.toLowerCase())) {
			port = 25;
			host = "smtp.live.com";
		}/*
		 * else if ("hyniva".equalsIgnoreCase(provider.toLowerCase())) { port =
		 * 587; host = "smtp.cloudzimail.com"; }
		 */else if ("rediffmail".equalsIgnoreCase(provider.toLowerCase())) {
			port = 25;
			host = "smtp.rediffmail.com";
		} else {
			port = 587;
			host = "smtp.gmail.com";
		}
		mailSender.setPort(port);
		mailSender.setHost(host);
		mailSender.setUsername(contactEmail);
		mailSender.setPassword(contactPassword);
		mailSender.setProtocol("smtp");

		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		// props.put("mail.smtp.socketFactory.port", port);

		if ("rediffmail".equalsIgnoreCase(provider.toLowerCase())) {// "hyniva".equalsIgnoreCase(provider.toLowerCase())
			props.put("mail.smtp.starttls.enable", "false"); // hyniva
			props.put("mail.smtp.starttls.required", "false");
		} else {
			props.put("mail.smtp.starttls.enable", "true"); // hotmail
			props.put("mail.smtp.starttls.required", "true");
		}
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", port);
		props.put("mail.smtp.starttls.enable", "true");

		mailSender.setJavaMailProperties(props);
		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(contactEmail,
								contactPassword);
					}
				});
		mailSender.setSession(session);
		session = null;
		return mailSender;
	}

}