package com.urt.webapp.action.govt;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import com.churchgroup.common.constants.Constants;
import com.churchgroup.util.date.DateFormatter;
import com.churchgroup.util.date.DateUtil;
import com.churchgroup.util.object.ObjectFunctions;
import com.churchgroup.util.string.StringFunctions;
import com.churchgroup.util.xls.ExcelView;
import com.urt.exception.base.URTUniversalException;
import com.urt.persistence.model.common.AcademicYear;
import com.urt.persistence.model.common.State;
import com.urt.persistence.model.customer.Customer;
import com.urt.util.common.RayGunException;
import com.urt.webapp.action.base.BaseAction;


/**
 * Action for facilitating Resources Management feature.
 */
@Namespace("/govt") 
@Actions( { @Action(value = "govtReports", results = { @Result(location = "../govt/govtReports.jsp", name = "success") }),
	 @Action(value = "ajaxGovtReports", results = { @Result(location = "../govt/govtReports.jsp", name = "success") }) })
//@Validations(requiredStrings = {@RequiredStringValidator(fieldName = "resourceName", type = ValidatorType.FIELD, message = "resource name is required")})
public class GovtReportsAction extends BaseAction {
	
	private static final long serialVersionUID = -1646390427462403153L;

	protected Customer customer;
	
	private List customersList;

	public List getCustomersList() {
		if(ObjectFunctions.isNullOrEmpty(this.customersList)){
			this.customersList=new ArrayList();
		}
		return this.customersList;
	}

	public void setCustomersList(List customersList) {
		this.customersList = customersList;
	}
	
	
	@Actions({
		@Action(value = "ajaxGovtCustomersList", results = { @Result(location = "../govt/ajaxGovtCustomers.jsp", name = "success")})
	})
	public String ajaxGovtCustomersList() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxGovtCustomersList' method");
		}
		try{
			 setCustomersList(adminManager.getAll(Customer.class,"organizationSubTypeId > 0"));
		}
		catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
		
	
	
}