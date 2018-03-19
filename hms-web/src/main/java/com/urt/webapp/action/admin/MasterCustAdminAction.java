package com.urt.webapp.action.admin;

import java.io.FileInputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import com.churchgroup.util.object.ObjectFunctions;
import com.urt.util.common.RayGunException;
import com.urt.webapp.action.base.BaseAction;
import com.urt.webapp.action.jrexception.JRExceptionClient;



/**
 * This is mainly used for the import process
 */

@Namespace("/madmin")
public class MasterCustAdminAction extends BaseAction {

	private static final long serialVersionUID = -1646390427462403153L;
	//public AcademicYear academicYear;
	//public long currentUserCustId;
	public String username;
	//public User staffAccount;
	//public Map<String, String>  statelist;
	
	@Actions({
		@Action(value = "ajaxUploadSchoolData", results = { @Result(location = "../admin/ajaxImportCustomerEnrollmentSheet.jsp", name = "success")})
	})
	public String uploadTemplateSheet()
    {
		 if (log.isDebugEnabled()) {
		        log.debug("Entering 'uploadTemplateSheet' method");
		 }
    	 try
	    	{
	 			HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(getUpload()));
	 			org.apache.poi.ss.usermodel.Sheet firstSheet1 = workbook.getSheetAt(0);
				Row secondRow = firstSheet1.getRow(0);
				if(!ObjectFunctions.isNullOrEmpty(secondRow))
				{
					if(!ObjectFunctions.isNullOrEmpty(secondRow.getCell(0)) && !ObjectFunctions.isNullOrEmpty(secondRow.getCell(1)))
					{
						String fistColumn = secondRow.getCell(0).getStringCellValue();
						String secondColumn = secondRow.getCell(1).getStringCellValue();
						
						if(!"Subject Name".equalsIgnoreCase(fistColumn.toString()) || !"Subject Short Name".equalsIgnoreCase(secondColumn.toString()))
						{
							log.debug("No file to upload....");
							super.addActionError("File type not matched.");
							return SUCCESS;
						}
						fistColumn = null;
						secondColumn = null;
					}
					else
					{
						log.debug("No file to upload....");
						super.addActionError("File type not matched.");
						return SUCCESS;
					}
				}
				else
				{
					log.debug("No file to upload....");
					super.addActionError("File type not matched.");
					return SUCCESS;
				}
    		 LoadCustomerEnrollAndStaffSubjectsSheet();
	   	   super.addActionMessage("Processed submitted records, please verify the data in the system. If any of the records not imported, please correct the data and reimport. You no need to remove the processed records from the sheet. System would take care of not loading again.");
	     }
	     catch(Exception ex)
	     {
	    	 log.error("Entering into 'catch block':"+ex.getMessage());
	    	 super.addActionError("System error! Please contact system administrator");
    	 	 ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
    	 	JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
    	 }
        return SUCCESS;
    }

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return the staffAccount
	 *//*
	public User getStaffAccount() {
		return staffAccount;
	}
	*//**
	 * @param staffAccount the staffAccount to set
	 *//*
	public void setStaffAccount(User staffAccount) {
		this.staffAccount = staffAccount;
	} */
}