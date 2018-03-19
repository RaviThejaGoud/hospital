package com.urt.service.thread;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.churchgroup.util.object.ObjectFunctions;
import com.churchgroup.util.string.StringFunctions;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.Writer;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code128Writer;
import com.google.zxing.qrcode.QRCodeWriter;
import com.urt.persistence.model.customer.Customer;
import com.urt.service.manager.interfaces.admin.AdminManager;
import com.urt.service.manager.interfaces.user.CommunicationManager;
import com.urt.util.common.RayGunException;
import com.urt.webapp.action.jrexception.JRExceptionClient;

public class GenerateBarCodeForAllCustomersThread  implements Runnable{
	
	private static final Log log = LogFactory.getLog(GenerateBarCodeForAllCustomersThread.class);
	private Thread t;
	
	@Autowired
	public CommunicationManager communicationManager;
	
	@Autowired
	public AdminManager adminManager;
	
	WebApplicationContext wac = null;
	
	
	public GenerateBarCodeForAllCustomersThread() {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		
		 wac = ContextLoader.getCurrentWebApplicationContext();
	}


	@Override
	public void run() 
	{
		try {
			List<Customer> customerList = adminManager.getAll(Customer.class);
			for(Customer customer : customerList)
			{
				List<Object[]> allCustAccountsList = adminManager.getAll("select id,custId from Account where custId="+customer.getId());
				if(!ObjectFunctions.isNullOrEmpty(allCustAccountsList))
				{
					for(Object[] accountObj : allCustAccountsList)
					{
						generateBarcodeForStudent(accountObj[0].toString(), customer.getId());
					}
				}
				allCustAccountsList = null;
			}
			customerList = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void generateBarcodeForStudent(String userId,long custId){
		try{
			if(!ObjectFunctions.isNullOrEmpty(userId)){
				BitMatrix bitMatrix;
				Writer writer = new QRCodeWriter();
				//Barcode barcode=new Barcode();
				 StringBuffer fileName = new StringBuffer("userFiles/");
				 	fileName.append("barcode");
				 	fileName.append("/");
				 	fileName.append(custId);
				 	fileName.append("/");
				    if(StringFunctions.isNotNullOrEmpty(fileName.toString())){
				    	String barcodeNumber = String.format("S"+"%07d", Long.valueOf(userId));
				    	//log.debug(barcodeNumber);
				    	File destFile = new File(wac.getServletContext().getRealPath(fileName.toString()));
				    	if(destFile.mkdirs())
							log.debug(wac.getServletContext().getRealPath(fileName.toString())+" directory created.");
						else
							log.debug(wac.getServletContext().getRealPath(fileName.toString())+" directories Not Created");
				    	
				    	File destFileName = new File(wac.getServletContext().getRealPath(fileName.toString()+barcodeNumber+".png"));
				    	if(!destFileName.exists())
				    	{
				    		bitMatrix = new Code128Writer().encode(barcodeNumber, BarcodeFormat.CODE_128, 150, 80, null);
				    		MatrixToImageWriter.writeToStream(bitMatrix, "png", new FileOutputStream(new File(wac.getServletContext().getRealPath(fileName.toString()+barcodeNumber+".png"))));
							adminManager.updateQuery("update Account set barcode='"+barcodeNumber+"' where id="+userId);
				    	}
					}
			}
		}catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
	}
	 public void start ()
	   {
	      if (t == null)
	      {
	         t = new Thread (this);
	         t.start ();
	      }
	   }
	 
	 
}
