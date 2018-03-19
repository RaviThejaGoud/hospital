package com.urt.webapp.action.exam;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

import com.churchgroup.util.object.ObjectFunctions;
import com.churchgroup.util.string.StringFunctions;
import com.spring.context.SpringContextAware;
import com.urt.json.JSONObject;
import com.urt.persistence.model.common.AcademicYear;
import com.urt.persistence.model.customer.Customer;
import com.urt.util.email.MailUtil;
public class ScorecardDeliveredThread  implements Runnable {
	protected transient final Log log = LogFactory.getLog(getClass());
	private Customer customer = null;
	//private String academicYear = null;
	private long classSectionId = 0;
	private String examType = null;
	private String serverFilePath = null;
	private long examTypeId = 0;
	private AcademicYear academicYear = null;
	MailUtil mailUtil= null;
	private List<Object[]> supportTeamEmail;
	
	
	public List<Object[]> getSupportTeamEmail() {
		if(ObjectFunctions.isNullOrEmpty(this.supportTeamEmail))
		{
			this.supportTeamEmail = new ArrayList<Object[]>(); 
		}
		return supportTeamEmail;
	}

	public void setSupportTeamEmail(List<Object[]> supportTeamEmail) {
		this.supportTeamEmail = supportTeamEmail;
	}
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public long getClassSectionId() {
		return classSectionId;
	}

	public void setClassSectionId(long classSectionId) {
		this.classSectionId = classSectionId;
	}

	public String getExamType() {
		return examType;
	}

	public void setExamType(String examType) {
		this.examType = examType;
	}

	public long getExamTypeId() {
		return examTypeId;
	}

	public void setExamTypeId(long examTypeId) {
		this.examTypeId = examTypeId;
	}

	public AcademicYear getAcademicYear() {
		return academicYear;
	}

	public void setAcademicYear(AcademicYear academicYear) {
		this.academicYear = academicYear;
	}
	public String getServerFilePath() {
		return serverFilePath;
	}

	public void setServerFilePath(String serverFilePath) {
		this.serverFilePath = serverFilePath;
	}

	public ScorecardDeliveredThread(Customer customer,AcademicYear academicYear, long classSectionId,String examType,long examTypeId,List<Object[]> supportTeamEmail,String serverFilePath) {
		super();
		this.customer = customer;
		this.academicYear = academicYear;
		this.classSectionId = classSectionId;
		this.examType = examType;
		this.examTypeId = examTypeId;
		this.supportTeamEmail = supportTeamEmail;
		this.serverFilePath=serverFilePath;
	}

	public ScorecardDeliveredThread() {
		
	}
	public void run()
	{
			try {
			//Thread.sleep(1000*60*5);
				synchronized (this) {
					 
					try {
						 
						JSONObject jsonObj= new JSONObject();
						jsonObj.put("filePath",SpringContextAware.getHostUrl()+"/userFiles/ScoreCardTemplates/"+customer.getCustomerShortName()+"/"+academicYear.getAcademicYear()+"/"+examType+"/ClassSection_"+classSectionId+".zip");
						jsonObj.put("custId", customer.getId());
						jsonObj.put("custEmail", customer.getCustEmail());
						jsonObj.put("orgnizationName", customer.getOrganization());
						jsonObj.put("acdYearId", academicYear.getId()); 
						jsonObj.put("studyClassId",classSectionId);
						jsonObj.put("examTypeId",examTypeId);
						jsonObj.put("studentId",0);
						HttpClient httpClient = new DefaultHttpClient();
						HttpContext localContext = new BasicHttpContext(null);	
						HttpGet httpGet = new HttpGet("http://wcf.eazyschool.in/URT_WcfServices.svc/GenerateScoreCard?input="+ URLEncoder.encode(jsonObj.toString(),"UTF-8")); // this URL for server system purpose
						log.debug("am in thered code block..."); 
						
						log.debug("http://wcf.eazyschool.in/URT_WcfServices.svc/GenerateScoreCard?input="+ jsonObj.toString());
						HttpResponse response = httpClient.execute(httpGet,localContext);
					    HttpEntity entity = response.getEntity();
				        String resultStr  = getASCIIContentFromEntity(entity);
						if(!StringFunctions.isNullOrEmpty(resultStr) && response.getStatusLine().getStatusCode() != 200){
							log.debug("WCF Service calling Error with HTTP error code : " + response.getStatusLine().getStatusCode());
		               		sendFailedEmailToSuportTeam("DotNet Server shutdown Or System has lost the internet connection.",customer.getId(),academicYear.getId(),customer);
						}
						response=null;
						httpGet=null;
					   httpClient=null;
					} catch (Exception e) {
						sendFailedEmailToSuportTeam(e.getMessage(),customer.getId(),academicYear.getId(),customer);
						e.printStackTrace();
					}
			    }
			Thread.interrupted();
			}catch (Exception e) {
				sendFailedEmailToSuportTeam(e.getMessage(),customer.getId(),academicYear.getId(),customer);
				e.printStackTrace();
			}
		      
    }
	public void sendFailedEmailToSuportTeam(String exception,long custId,long academicYearId,Customer customer)
	{
		log.debug("calling sendFailedEmailToSuportTeam method");
		String server = null;
		String hostUrl=SpringContextAware.getHostUrl();
		if(hostUrl.contains("dev.")){
			server="D";
		}else if(hostUrl.contains("https://eazyschool.in")){
			server="P";
		}else
			server = "L";
		Set<String> emailIdsSet = new HashSet<String>();
		if(!ObjectFunctions.isNullOrEmpty(supportTeamEmail)){
			String[] emailAdd = null;
			for(Object obj[] : supportTeamEmail){
				if(!ObjectFunctions.isNullOrEmpty(obj[0]))
					emailIdsSet.add(obj[0].toString());
				if(!ObjectFunctions.isNullOrEmpty(emailIdsSet)){
				    int i = 0;
				    emailAdd = new String[emailIdsSet.size()];
					for (Object emailId : emailIdsSet) {
					    if (!ObjectFunctions.isNullOrEmpty(emailId)) {
					    	emailAdd[i] = emailId.toString();
					    	emailId = null;
					    }
					    i++;
					}
				}
			}
		mailUtil = new MailUtil(emailAdd,"Error Occurred in ("+server+") ScoreCard WCF Service for CustId : "+custId+", AcdId : "+academicYearId,0,"EazySchool Team","JAVA && .NET",getContactFromEmail(customer),"ForSupportTeamEmails");
		//mailUtil.sendMailForSupportUrt(String.valueOf(response.getStatusLine().getStatusCode()));
		mailUtil.setContactEmail(customer.getContactEmail());
		mailUtil.setContactPasword(customer.getContactPassword());
		mailUtil.sendMailForSupportUrt(exception,customer.getCustEmail(),customer.getOrganization());
   		mailUtil=null;
		}
	}
	
	  public static String getASCIIContentFromEntity(HttpEntity entity) throws IllegalStateException, IOException {
	      InputStream in = entity.getContent();
	      StringBuffer out = new StringBuffer();
	      int n = 1;
	      while (n>0) {
	          byte[] b = new byte[4096];
	          n =  in.read(b);
	          if (n>0) out.append(new String(b, 0, n));
	      }
	      return out.toString();
	  }
	  public String getContactFromEmail(Customer customer){
			 String contactEmail = null;
			 if(StringFunctions.isNullOrEmpty(customer.getContactEmail()) & StringFunctions.isNullOrEmpty(customer.getContactPassword())){
					return contactEmail = "messages@eazyschool.com";
				}else {
					return  contactEmail = customer.getContactEmail();
				}
		 }
}