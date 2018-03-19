package com.urt.webapp.action.subscription;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import com.churchgroup.util.object.ObjectFunctions;
import com.churchgroup.util.string.StringFunctions;
import com.urt.exception.base.URTUniversalException;
import com.urt.persistence.model.customer.Customer;
import com.urt.util.email.MailUtil;
import com.urt.webapp.action.base.BaseAction;



/**
 * Action for facilitating Resources Management feature.
 */
@Namespace("/")
@Actions( {
    @Action(value = "ajaxDoViewAdminDetails", results = { @Result(location = "popupUserEnquiry.jsp", name = "success") })

})
//@Validations(requiredStrings = {@RequiredStringValidator(fieldName = "resourceName", type = ValidatorType.FIELD, message = "resource name is required")})
public class LoginAction extends BaseAction {
    @Action(value = "eazyContactUs", results = { @Result(location = "login.jsp", name = "success")})
    public String ajaxEazyContactUs() throws URTUniversalException {
        try {
            if (!StringFunctions.isNullOrEmpty(getContactUs().getCustomerName()) && !StringFunctions.isNullOrEmpty(getContactUs().getCustomerEmail()) && !StringFunctions.isNullOrEmpty(getContactUs().getPhoneNumber()) && !StringFunctions.isNullOrEmpty(getContactUs().getComments()))
            {   
               
                adminManager.save(getContactUs());
                String getProtocol=getRequest().getScheme();
                String getDomain=getRequest().getServerName();
                String getPort=Integer.toString(getRequest().getServerPort());
                String path = getRequest().getContextPath();
                /* For local validation*/
                String urlPath = getProtocol+"://"+getDomain+":"+getPort+path;
                /* For server validation*/
                //String urlPath = getProtocol+"://"+getDomain+path;
                List adminsList=adminManager.getAllAdminsByUrlPath(urlPath);
                if(!ObjectFunctions.isNullOrEmpty(adminsList)){
                    Customer customer  = null;
                    String[] emailAddresses =null;
                    MailUtil mailUtil=null;
                    for(Object obj:adminsList){
                    	customer  = (Customer)obj;
                    	emailAddresses = new String[2];
                    	mailUtil=new MailUtil(emailAddresses,"Information You Requested from Eazy School",customer.getId(),customer.getSender(),getUser().getUserRoleDescription(),"messages@eazyschool.com");
             	        emailAddresses[0]=customer.getCustEmail();
                    	mailUtil.sendMailForContactUs(getContactUs());
                        customer = null;
                        mailUtil=null;
                        emailAddresses=null;
                    }
                }
               
                //super.addActionMessage("You have successfully contacted eazySchool Admin");
               
            }
           
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return SUCCESS;
    }

}