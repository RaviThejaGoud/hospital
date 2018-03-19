/**
 * 
 */
package com.urt.util.properties;


import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.urt.service.mail.EmailDataObject;

/**
 * @author Sreeram
 *
 */
public class PropertiesUtil {
	
	private static final Log log = LogFactory.getLog(PropertiesUtil.class);
	
	private PropertiesConfiguration config;
	

	/**
	 * 
	 */
	public PropertiesUtil() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		PropertiesUtil pu = new PropertiesUtil();
		pu.loadProperties("D:/workspace/urtUtils/src/com/urt/util/properties/ApplicationResources.properties");
		EmailDataObject ed =pu.getEmailDataObject("addRide");
		log.debug(ed.toString());
		log.debug(pu.getValue("webapp.name"));
		pu.setValue("webapp.name","Hello world");
		log.debug(pu.getValue("webapp.name"));
		//pu.saveProperties();
	}
	
	public boolean loadProperties(String fileName) { 
		try {
				setConfig(new PropertiesConfiguration(fileName));
		} 
		catch (ConfigurationException ioe) {
			ioe.printStackTrace();
			return false;
		} 
		return true;
	}
	
	public String getValue(String key)
	{
		return getConfig().getString(key);
	}

	public PropertiesConfiguration getConfig() {
		return config;
	}

	public void setConfig(PropertiesConfiguration config) {
		this.config = config;
	}
	public void setValue(String key,String value)
	{
		getConfig().setProperty(key, value);
	}
	
	public void saveProperties()
	{
		try {
			getConfig().save();
		} catch (ConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public EmailDataObject getEmailDataObject(String key)
	{
		EmailDataObject emailDataObject= new EmailDataObject();
		emailDataObject.setSubject(getConfig().getString(key + ".subject"));
		emailDataObject.setBody(getConfig().getString(key + ".body"));
		emailDataObject.setFromAddress(getConfig().getString("fromAddress"));
		emailDataObject.setFooter(getConfig().getString(key + ".footer"));
		emailDataObject.setCommonFooter(getConfig().getString("common.footer"));
		return emailDataObject;
	}
	public void saveEmailDataObject(EmailDataObject emailDataObject,String key)
	{
		setValue(key + ".subject",emailDataObject.getSubject());
		setValue(key + ".body",emailDataObject.getBody());
		setValue(key + ".footer",emailDataObject.getFooter());
		setValue("fromAddress",emailDataObject.getFromAddress());
		setValue("common.footer",emailDataObject.getCommonFooter());
		saveProperties();
	}
	
	public void saveProperty(String key, String value)
	{
		setValue(key,value);
		saveProperties();
	}
	public void getProperty(String key)
	{
		getValue(key);
     }
}
