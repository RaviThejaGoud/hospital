package com.urt.webapp.action.schollfee;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.InetAddress;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFFormulaEvaluator;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import com.churchgroup.common.constants.Constants;
import com.churchgroup.util.date.DateFormatter;
import com.churchgroup.util.date.DateFunctions;
import com.churchgroup.util.date.DateUtil;
import com.churchgroup.util.object.ObjectFunctions;
import com.churchgroup.util.pdf.PDFGenerator;
import com.churchgroup.util.pdf.PdfHeaderFooterMarkJasper;
import com.churchgroup.util.string.StringFunctions;
import com.churchgroup.util.string.StringUtil;
import com.hyniva.common.cache.SMSLookUpDataCache;
import com.hyniva.sms.ws.vo.SchoolCategoryVO;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.urt.exception.base.URTUniversalException;
import com.urt.json.JSONArray;
import com.urt.json.JSONObject;
import com.urt.persistence.model.account.ViewPartucularToAccount;
import com.urt.persistence.model.common.AcademicYear;
import com.urt.persistence.model.common.AdmissionSettings;
import com.urt.persistence.model.common.BankMaster;
import com.urt.persistence.model.common.Country;
import com.urt.persistence.model.common.Messages;
import com.urt.persistence.model.customer.Customer;
import com.urt.persistence.model.customer.Fee;
import com.urt.persistence.model.customer.FeeType;
import com.urt.persistence.model.customer.FineFee;
import com.urt.persistence.model.customer.SMSServiceProviders;
import com.urt.persistence.model.customer.SchoolTerms;
import com.urt.persistence.model.fee.ChallanaPayment;
import com.urt.persistence.model.fee.SchoolFeeSetting;
import com.urt.persistence.model.fee.StudentFeeConcession;
import com.urt.persistence.model.fee.StudentFeeRefund;
import com.urt.persistence.model.fee.StudentTransportMonthFeeDetails;
import com.urt.persistence.model.fee.ViewStudentFeeChallanaDetails;
import com.urt.persistence.model.fee.ViewStudentFeeRefundDetails;
import com.urt.persistence.model.fee.ViewStudentsConcessionClassFeeDetails;
import com.urt.persistence.model.fee.ViewStudentsConcessionTransportFeeDetails;
import com.urt.persistence.model.study.ClassName;
import com.urt.persistence.model.study.InvoiceLogs;
import com.urt.persistence.model.study.SchoolCategory;
import com.urt.persistence.model.study.Student;
import com.urt.persistence.model.study.StudentFeePaidDetails;
import com.urt.persistence.model.study.StudentPayment;
import com.urt.persistence.model.study.StudentPocketMoney;
import com.urt.persistence.model.study.StudyClass;
import com.urt.persistence.model.study.ViewClassFeeDetails;
import com.urt.persistence.model.study.ViewClassFeeType;
import com.urt.persistence.model.study.ViewOnlineApplicationStudentClassFees;
import com.urt.persistence.model.study.ViewStudentClassDetails;
import com.urt.persistence.model.study.ViewStudentFeePaymentDetails;
import com.urt.persistence.model.study.ViewStudentFineFeeDetails;
import com.urt.persistence.model.study.ViewStudentPersonAccountDetails;
import com.urt.persistence.model.study.ViewStudentTransportFeePaymentDetails;
import com.urt.persistence.model.transport.Route;
import com.urt.persistence.model.transport.RouteBoardingPoints;
import com.urt.persistence.model.user.OnlineApplicationDetails;
import com.urt.persistence.model.user.User;
import com.urt.util.common.RayGunException;
import com.urt.util.excel.student.PrepareStudentInvoiceExcel;
import com.urt.webapp.action.base.BaseAction;
import com.urt.webapp.action.jrexception.JRExceptionClient;

@Namespace("/schoolfee")
@Actions( {
	@Action(value = "manageAcademics", results = {@Result(location = "academic/academicLeftNav.jsp", name = "success") })
	
	
		})
public class SchoolFeeAction extends BaseAction {

	private static final long serialVersionUID = -1646390427462403153L;
	
	protected FeeType feeType;
	protected ViewStudentFineFeeDetails fineFeeDetails;
	protected FineFee fineFee;
	protected Map<Long, StringBuffer> termWiseMesgDetails;
	protected SchoolCategoryVO schoolCategoryVO;
	
	public Map<Long, StringBuffer> getTermWiseMesgDetails() {
		if(ObjectFunctions.isNullOrEmpty(this.termWiseMesgDetails)){
			this.termWiseMesgDetails=new HashMap<Long, StringBuffer>();
		}
		return termWiseMesgDetails;
	}
	public void setTermWiseMesgDetails(Map<Long, StringBuffer> termWiseMesgDetails) {
		this.termWiseMesgDetails = termWiseMesgDetails;
	}
	public ViewStudentFineFeeDetails getFineFeeDetails() {
		return fineFeeDetails;
	}
	public void setFineFeeDetails(ViewStudentFineFeeDetails fineFeeDetails) {
		this.fineFeeDetails = fineFeeDetails;
	}
	public FineFee getFineFee() {
		return fineFee;
	}
	public void setFineFee(FineFee fineFee) {
		this.fineFee = fineFee;
	}
	public FeeType getFeeType() {
		return feeType;
	}
	public void setFeeType(FeeType feeType) {
		this.feeType = feeType;
	}
	
	public SchoolCategoryVO getSchoolCategoryVO() {
		return schoolCategoryVO;
	}
	public void setSchoolCategoryVO(SchoolCategoryVO schoolCategoryVO) {
		this.schoolCategoryVO = schoolCategoryVO;
	}
	@Actions( {
		@Action(value = "ajaxSchoolFeeSetting", results = { @Result(location = "ajaxSchoolFeeSettings.jsp", name = "success") }),
		@Action(value = "ajaxViewSelectedFeeSettings", results = { @Result(location = "ajaxViewSchoolFeeSettings.jsp", name = "success") }) })
		public String ajaxSchoolFeeSetting() throws URTUniversalException {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxSchoolFeeSetting' method");
			}
			try {
				if (getUserAcademicYearId() > 0) {
					if (StringFunctions.isNullOrEmpty(getTempString()) || "categories".equalsIgnoreCase(getTempString())) {
						setTempString("categories");
						setSchoolCategoriesList(adminManager.getAllByCustId("SchoolCategory", getUserCustId(),0));
					} else if ("feeParticulars".equalsIgnoreCase(getTempString())) {
						prepareSchoolFeeSettingList();
						setFeeTypeList(adminManager.getAll(FeeType.class, "custId=" + getUserCustId() + " and academicYearId="+ getUserAcademicYearId() + " and feeSettingId="+ getTempId()));
					} else if ("feeTerms".equalsIgnoreCase(getTempString())){
						prepareSchoolFeeSettingList();
						setSchoolTermsList(adminManager.getAll(SchoolTerms.class,"custId=" + getUserCustId()+ " and academicYearId="+ getUserAcademicYearId() +" and feeSettingId="+getTempId()));
					}
				}
			} catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return SUCCESS;
		}
	@Actions( { @Action(value = "ajaxSchoolFeeSettingParticular", results = { @Result(location = "ajaxViewFeeSettingParticularList.jsp", name = "success") }) })
	public String ajaxSchoolFeeSettingParticular() throws URTUniversalException {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxSchoolFeeSettingParticular' method");
		}
		try {
			StringBuffer stringBuffer=new StringBuffer();
			if(!ObjectFunctions.isNullOrEmpty(getSession().getAttribute("admissionAcademicYearId"))){
				setUsrChgedAcademicId((Long)getSession().getAttribute("admissionAcademicYearId"));
				setAnyId("admissionParticulars");
				stringBuffer.append("custId="+ getUserCustId() + " and academicYearId="+ getUsrChgedAcademicId() + " and feeSettingId="+ getTempId());
				if("Y".equalsIgnoreCase(getCustomerByCustId().getCommittedFeeStatus())){
					setCustomer(getCustomerByCustId());
					setTempBoolean(adminManager.committedFeeStatusEnableOrDisable(getUserCustId(),getUsrChgedAcademicId()));
					/* Below query to validate priority position in front end. */
					List<Object[]> feePriorityIds = adminManager.getAll("select priorityPosition from feeType f where f.custId="+getUserCustId()+" and f.academicYearId="+getUsrChgedAcademicId()+" and f.committedFeeStatus='"+Constants.YES_STRING+"' and feeSettingId not in ("+getTempId()+")");
					if(!ObjectFunctions.isNullOrEmpty(feePriorityIds))
						setTempString(feePriorityIds.toString());
					else
						setTempString("[]");
				}
			}
			else if(getUserAcademicYearId()>0)
			{
				stringBuffer.append("custId="+ getUserCustId() + " and academicYearId="+ getUserAcademicYearId() + " and feeSettingId="+ getTempId());
			}
			setFeeTypeList(adminManager.getAll(FeeType.class, stringBuffer.toString()));
			stringBuffer=null;
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}

		return SUCCESS;
	}
	@Actions( { @Action(value = "ajaxSchoolFeeSettingTerms", results = { @Result(location = "ajaxViewFeeSettingTermsList.jsp", name = "success") }),
		 @Action(value = "ajaxSchoolFeeSettingCreateTerms", results = { @Result(location = "ajaxFeeSettingAvailableTerms.jsp", name = "success") }) })
	public String ajaxSchoolFeeSettingTerms() throws URTUniversalException {
	
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxSchoolFeeSettingTerms' method");
		}
		try {
			StringBuffer stringBuffer=new StringBuffer();
			if(!ObjectFunctions.isNullOrEmpty(getSession().getAttribute("admissionAcademicYearId"))){
				setUsrChgedAcademicId((Long)getSession().getAttribute("admissionAcademicYearId"));
				setAnyId("admissionTerms");
				stringBuffer.append("custId=" + getUserCustId()+ " and academicYearId="+ getUsrChgedAcademicId()+" and feeSettingId="+getTempId());
			}
			else if(getUserAcademicYearId()>0)
			{
				stringBuffer.append("custId=" + getUserCustId()+ " and academicYearId="+ getUserAcademicYearId()+" and feeSettingId="+getTempId());
			}
			setSchoolTermsList(adminManager.getAll(SchoolTerms.class,stringBuffer.toString()));
			setSchoolFeeSetting((SchoolFeeSetting)adminManager.get(SchoolFeeSetting.class, getTempId()));
			stringBuffer=null;
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
	
		return SUCCESS;
	}

	@Actions( { @Action(value = "ajaxDoCreateCategory", results = { @Result(location = "ajaxDoCreateCategory.jsp", name = "success") }) })
	public String ajaxDoCreateCategory() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDoCreateCategory' method");
		}
		try {
			if (!ObjectFunctions.isNullOrEmpty(getSchoolCategory()) && getSchoolCategory().getId() > 0)
				setSchoolCategory((SchoolCategory) adminManager.get(SchoolCategory.class, getSchoolCategory().getId()));
			else {
				setSchoolCategory(null);
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}

	@Actions( { @Action(value = "ajaxAddSchoolCategory", results = { @Result(location = "ajaxViewSchoolFeeSettings.jsp", name = "success"),
																	@Result(location = "../admin/admission/ajaxAdmissionSettingsStep3.jsp", name = "admissionSettings") }) })
	public String ajaxAddSchoolCategory() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxAddSchoolCategory' method");
		}
		try {
			String categoryName = getSchoolCategory().getCategoryName().trim();
			SchoolCategory schoolCategory = (SchoolCategory) adminManager.get(SchoolCategory.class, getSchoolCategory().getId());
			if (!ObjectFunctions.isNullOrEmpty(schoolCategory)) {
				schoolCategory.setLastUpdatedById(getUser().getId());
			} else {
				schoolCategory = (SchoolCategory) adminManager.get(SchoolCategory.class, "CategoryName='" + categoryName+ "' and custId=" + getUserCustId());
				if (ObjectFunctions.isNullOrEmpty(schoolCategory)) {
					schoolCategory = new SchoolCategory();
					schoolCategory.setCreatedById(getUser().getId());
				} else {
					super.addActionError(getSchoolCategory().getCategoryName()+ " Category is already exist. Please try with other name");
					categoryName = null;
					ajaxSchoolFeeSetting();
					return SUCCESS;
				}
			}
			schoolCategory.setCustId(getUserCustId());
			schoolCategory.setCategoryName(categoryName);
			schoolCategory.setLastUpdatedDate(new Date());
			schoolCategory.setLastAccessDate(new Date());
			adminManager.save(schoolCategory);
			if (getSchoolCategory().getId() == 0)
				super.addActionMessage(getSchoolCategory().getCategoryName()+ " Category created successfully.");
			else
				super.addActionMessage(getSchoolCategory().getCategoryName()+ " Category updated successfully.");
			categoryName = null;
			ajaxSchoolFeeSetting();
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}

	@Actions( { @Action(value = "ajaxDoCreateParticular", results = { @Result(location = "ajaxCreateFeeParticular.jsp", name = "success") }) })
	public String ajaxDoCreateParticular() throws URTUniversalException {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDoClassSubjectViewSyllabus' method");
		}
		try {
			prepareSchoolFeeSettingList();
			if (!ObjectFunctions.isNullOrEmpty(getFeeType())) {
				/* This below condition for fee particular priority setting disable or enable. If any student have committed fee records. */
				setCustomer(getCustomerByCustId());
				if(Constants.YES_STRING.equalsIgnoreCase(getCustomerByCustId().getCommittedFeeStatus()))
					setTempBoolean(adminManager.committedFeeStatusEnableOrDisable(getUserCustId(),getUserAcademicYearId()));
				/*@Ganesh - Below condition we will check for to restricting fee setting type. If we already assign fee for respective particular we should not change fee setting type. */
				setClassFeeCountList(adminManager.getAll(Fee.class, "custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and feeTypeId="+getFeeType().getId()));
				setFeeType((FeeType) adminManager.get(FeeType.class,getFeeType().getId()));
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}

	@Actions( { @Action(value = "ajaxRemoveSchoolCategory", results = { @Result(location = "ajaxViewSchoolFeeSettings.jsp", name = "success"),
																		@Result(location = "../admin/admission/ajaxAdmissionSettingsStep3.jsp", name = "admissionSettings") }) })
	public String removeSchoolCategory() {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'removeSchoolCategory' method");
		}
		try {
			String targetValue = getParamValue("targetString");
			setTempString("categories");
			if (getSchoolCategory().getId() != 0) {
				SchoolCategory schoolCategory=(SchoolCategory) adminManager.get(SchoolCategory.class, getSchoolCategory().getId());
				if (!ObjectFunctions.isNullOrEmpty(schoolCategory)) {
					setStudentsList(adminManager.getAll(Student.class," custId=" + getUserCustId() + " and categoryId=" + schoolCategory.getId()));
					setSchoolFeeList(adminManager.getAll(Fee.class, " custId="+ getUserCustId() + " and categoryId=" + schoolCategory.getId()));
					if (ObjectFunctions.isNullOrEmpty(getSchoolFeeList()) && ObjectFunctions.isNullOrEmpty(getStudentsList())) {
						adminManager.remove(SchoolCategory.class, schoolCategory.getId());
						super.addActionMessage(schoolCategory.getCategoryName() + " category deleted successfully.");
						if (!StringFunctions.isNullOrEmpty(targetValue)) {
							if ("creatAdmissionConDetails".equalsIgnoreCase(targetValue)) {
								setTempString(targetValue);
								ajaxGetSettingsSteps();
								return "admissionSettings";
							}
						}
						return SUCCESS;
					} else {
						super.addActionError(schoolCategory.getCategoryName()+ " category contains students or fees.You can't remove this category.");
						if (!StringFunctions.isNullOrEmpty(targetValue)) {
							if ("creatAdmissionConDetails".equalsIgnoreCase(targetValue)) {
								setTempString(targetValue);
								ajaxGetSettingsSteps();
								return "admissionSettings";
							}
						}
						return SUCCESS;
					}
				}
				schoolCategory=null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ajaxSchoolFeeSetting();
		}
		return SUCCESS;
	}
	@Actions( { @Action(value = "ajaxAddSchoolFeeSettings", results = { @Result(location = "ajaxViewSchoolFeeSettings.jsp", name = "success")}) })
	public String ajaxAddSchoolFeeSettings() throws URTUniversalException {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxAddSchoolFeeSettings' method");
		}
		try {
			FeeType feeType = null;
			FeeType checkFeeType = null;
			int position = 0;
			SchoolFeeSetting feeSetting = (SchoolFeeSetting) adminManager.get(SchoolFeeSetting.class, getTempId());
			checkFeeType = (FeeType) adminManager.get(FeeType.class,"feeType='" + getFeeType().getFeeType().trim()+ "' and custId=" + getUserCustId()+ " and academicYearId=" + getUserAcademicYearId());
			if (getFeeType().getId() != 0) {
				feeType = (FeeType) adminManager.get(FeeType.class, getFeeType().getId());
				if (!ObjectFunctions.isNullOrEmpty(feeType)) {
					feeType.setLastUpdatedById(getUser().getId());
					feeType.setFeeType(getFeeType().getFeeType().trim());
					feeType.setFeeSettingId(feeSetting.getId());
					if(!ObjectFunctions.isNullOrEmpty(getFeeType().getCommittedFeeStatus())){
						if("N".equalsIgnoreCase(getFeeType().getCommittedFeeStatus()))
							feeType.setPriorityPosition(Long.valueOf(position));
						else
							feeType.setPriorityPosition(getFeeType().getPriorityPosition());
						feeType.setCommittedFeeStatus(getFeeType().getCommittedFeeStatus());
					}
					adminManager.save(feeType);
					if (ObjectFunctions.isNullOrEmpty(checkFeeType) || getFeeType().getFeeType().trim().equalsIgnoreCase(feeType.getFeeType())) {
						super.addActionMessage(getFeeType().getFeeType()+ " updated successfully.");
					}else {
						super.addActionError(getFeeType().getFeeType()+ " already added.");
					}
				}
			} else {
				if (ObjectFunctions.isNullOrEmpty(checkFeeType)) {
					FeeType feeTypes = new FeeType();
					feeTypes.setCreatedById(getUser().getId());
					feeTypes.setCustId(getUserCustId());
					feeTypes.setFeeType(getFeeType().getFeeType().trim());
					feeTypes.setAcademicYear(getCurrentAcademicYear());
					feeTypes.setFeeSettingId(feeSetting.getId());
					//feeSetting.addFeeTypeDetails(feeTypes);
					if(!ObjectFunctions.isNullOrEmpty(getFeeType().getCommittedFeeStatus())){
						if("N".equalsIgnoreCase(getFeeType().getCommittedFeeStatus()))
							feeTypes.setPriorityPosition(Long.valueOf(position));
						else
							feeTypes.setPriorityPosition(getFeeType().getPriorityPosition());
							feeTypes.setCommittedFeeStatus(getFeeType().getCommittedFeeStatus());
					}
					adminManager.save(feeTypes);
					super.addActionMessage(getFeeType().getFeeType()+ " added successfully.");
					feeTypes = null;
				} else {
					super.addActionError(getFeeType().getFeeType()+ " already added.");
				}
			}
			setTempString("feeParticulars");
			ajaxSchoolFeeSetting();
			feeSetting=null;
			academicYear=null;
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}

		return SUCCESS;
	}
	@Actions( { @Action(value = "ajaxRemoveFeeParticular", results = { @Result(location = "ajaxViewSchoolFeeSettings.jsp", name = "success"),
																	   @Result(location = "ajaxViewFeeSettingParticularList.jsp", name = "admissionFeeTypeSettings") }) })
	public String removeFeeParticular() {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxRemoveFeeParticular' method");
		}
		try {
			if (!ObjectFunctions.isNullOrEmpty(getSession().getAttribute("admissionAcademicYearId"))) {
				setAcademicYearId(((Long) getSession().getAttribute("admissionAcademicYearId")));
			}else {
				setAcademicYearId(getUserAcademicYearId());
			}
			setCustomer(getCustomerByCustId());
			setTempBoolean(adminManager.committedFeeStatusEnableOrDisable(getUserCustId(),getUsrChgedAcademicId()));
			String targetValue = getParamValue("targetString");
			setTempString("feeParticulars");
			if (getFeeType().getId() != 0) {
				setFeeType((FeeType) adminManager.get(FeeType.class,getFeeType().getId()));
				if (!ObjectFunctions.isNullOrEmpty(getFeeType())) {
					setSchoolFeeList(adminManager.getAll(Fee.class, " custId="+ getUserCustId() + " and feeTypeId="+ getFeeType().getId()));
					if (ObjectFunctions.isNullOrEmpty(getSchoolFeeList())) {
						getFeeType().setAcademicYear(null);
						adminManager.remove(FeeType.class, getFeeType().getId());
						super.addActionMessage(getFeeType().getFeeType()+ " particular deleted successfully.");
						if (!StringFunctions.isNullOrEmpty(targetValue)) {
							if ("creatAdmissionParticulars".equalsIgnoreCase(targetValue)) {
								targetStringRemoveParticular(targetValue,getAcademicYearId());
								return "admissionFeeTypeSettings";
							}
						}
						return SUCCESS;
					} else {
						super.addActionError(getFeeType().getFeeType()+ " particular contains fees.You can't remove this particular.");
						if (!StringFunctions.isNullOrEmpty(targetValue)) {
							if ("creatAdmissionParticulars".equalsIgnoreCase(targetValue)) {
								targetStringRemoveParticular(targetValue,getAcademicYearId());
								return "admissionFeeTypeSettings";
							}
						}
						return SUCCESS;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ajaxGetSettingsSteps();
			ajaxSchoolFeeSettingParticular();
			ajaxSchoolFeeSetting();
		}
		return SUCCESS;
	}
	@Actions({ @Action(value = "ajaxGetSettingsSteps", results = { @Result(location = "../admin/admission/ajaxAdmissionSettingsStep3.jsp", name = "success") }) })
	public String ajaxGetSettingsSteps() {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxGetSettingsSteps' method");
			}
			try {
				setSchoolCategoriesList(adminManager.getAllByCustId("SchoolCategory", getUserCustId(),0));
			} catch (Exception ex) {
	    		ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
	    	return SUCCESS;
	}
	@Actions({@Action(value = "ajaxGetFeeParticularSettingsSteps", results = { @Result(location = "../admin/admission/ajaxAdmissionSettingsStep4.jsp", name = "success"),
			 																   @Result(location = "../admin/admission/ajaxAdmissionSettingsStep3.jsp", name = "categoryNameExist")})})
	public String ajaxGetFeeParticularSettingsSteps()
			throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxGetFeeParticularSettingsSteps' method");
		}
		try {
			if (!ObjectFunctions.isNullOrEmpty(getSession().getAttribute("admissionAcademicYearId"))) {
				long admissionAcademicYearId = (Long) getSession().getAttribute("admissionAcademicYearId");
				if (StringFunctions.isNotNullOrEmpty(getTempString())) {
					JSONArray classAdmissionsJSONArray = new JSONArray(getTempString());
					JSONObject admissionsJson = null;
					long categoryId = 0;
					SchoolCategory schoolCategory = null;
					SchoolCategory newCategoryObj = null;
					String categoryName = null;
					boolean categoryExist = false;
					String cNames[] = null;
					for (int i = 0; i < classAdmissionsJSONArray.length(); i++) {
						admissionsJson = classAdmissionsJSONArray.getJSONObject(i);
						if (!ObjectFunctions.isNullOrEmpty(admissionsJson)) {
							categoryName = (String) admissionsJson.get("categoryName");
							if (StringFunctions.isNotNullOrEmpty(categoryName)) {
								cNames = categoryName.split(",");
								if (!ObjectFunctions.isNullOrEmpty(cNames)) {
									for (String catName : cNames) {
										if (StringFunctions.isNotNullOrEmpty(catName)) {
											if (StringFunctions.isNotNullOrEmpty((String) admissionsJson.get("categoryId"))&& Long.valueOf((String) admissionsJson.get("categoryId")) > 0) {
												categoryId = Long.valueOf((String) admissionsJson.get("categoryId"));
											}
											StringBuffer queryString = new StringBuffer();
											if (categoryId != 0) {
												queryString.append("id="+ categoryId);
											} else {
												queryString.append("categoryName='"+ catName.trim()+ "' and custId="+ getUserCustId());
											}
											schoolCategory = (SchoolCategory) adminManager.get(SchoolCategory.class,queryString.toString());

											if (ObjectFunctions.isNullOrEmpty(schoolCategory)) {
												schoolCategory = new SchoolCategory();
												schoolCategory.setCreatedById(getUser().getId());
											}else if (categoryId==0){
												if(ObjectFunctions.isNullOrEmpty(schoolCategory)){
													schoolCategory = new SchoolCategory();
													schoolCategory.setCreatedById(getUser().getId());
													schoolCategory.setLastUpdatedDate(new Date());
												}else{
													categoryExist = true;
													continue;
												}
											}
											else if (!ObjectFunctions.isNullOrEmpty(schoolCategory)){
												newCategoryObj = (SchoolCategory) adminManager.get(SchoolCategory.class,"categoryName='"+ catName.trim()+ "' and id not in ("+schoolCategory.getId()+") and custId="+ getUserCustId());
												if (ObjectFunctions.isNullOrEmpty(newCategoryObj)) {
													schoolCategory.setLastUpdatedById(getUser().getId());
													schoolCategory.setLastUpdatedDate(new Date());
												} else {
													categoryExist = true;
													continue;
												}
											}
											else if (!catName.trim().equalsIgnoreCase(schoolCategory.getCategoryName())) {
												newCategoryObj = (SchoolCategory) adminManager.get(SchoolCategory.class,"categoryName='"+ catName.trim()+ "' and custId="+ getUserCustId());
												if (ObjectFunctions.isNullOrEmpty(newCategoryObj)) {
													schoolCategory = new SchoolCategory();
													schoolCategory.setCreatedById(getUser().getId());
												} else{
													categoryExist = true; 
													continue;
												}
											}else{
												schoolCategory.setLastUpdatedDate(new Date());
												schoolCategory.setLastUpdatedById(getUser().getId());
											}
											schoolCategory.setCustId(getUserCustId());
											schoolCategory.setCategoryName(catName.trim());
											schoolCategory.setLastAccessDate(new Date());
											adminManager.save(schoolCategory);
										}
										categoryId = 0;
									}
								}
							}
						}
					}
					if (categoryExist) {
						ajaxGetSettingsSteps();
						super.addActionError("Category Name already taken!!!.Please change category name.");
						return "categoryNameExist";
					} else {
						AcademicYear admissionsAcademicYear = (AcademicYear) adminManager.get(AcademicYear.class, "id="+ admissionAcademicYearId+ " and custId=" + getUserCustId());
						boolean status=generateFeeTypes(admissionsAcademicYear, getUser().getId(), getCurrentAcademicYear());
						ajaxGetAdmissionsFeeParticular();
						admissionsAcademicYear = null;
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	
	@Actions({@Action(value = "ajaxGetAdmissionsFeeParticular", results = { @Result(location = "../admin/admission/ajaxAdmissionSettingsStep4.jsp", name = "success")})})
	public String ajaxGetAdmissionsFeeParticular() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxGetAdmissionsFeeParticular' method");
		}
		try {
			if(!ObjectFunctions.isNullOrEmpty(getSession().getAttribute("admissionAcademicYearId"))){
				long admissionAcademicYearId = (Long)getSession().getAttribute("admissionAcademicYearId");
				prepareSchoolFeeSettingList();
				setFeeTypeList(adminManager.getAll(FeeType.class, "custId="+getUserCustId()+" and academicYearId="+admissionAcademicYearId+" and feeSettingId="+getTempId()));
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}

	@Actions( { @Action(value = "ajaxGetAdmissionFeeTerms", results = { @Result(location = "../admin/admission/ajaxAdmissionSettingsStep5.jsp", name = "success"),
																		@Result(location = "../admin/admission/ajaxAdmissionSettingsStep4.jsp", name = "particularNameExist") }) })
	public String ajaxGetAdmissionFeeTerms() {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxGetAdmissionFeeTerms' method");
		}
		try {
			if (!ObjectFunctions.isNullOrEmpty(getSession().getAttribute("admissionAcademicYearId"))) {
				long admissionAcademicYearId = (Long) getSession().getAttribute("admissionAcademicYearId");
				AcademicYear admissionAcademicYear = (AcademicYear) adminManager.get(AcademicYear.class, "id="+ admissionAcademicYearId);
				SchoolFeeSetting schoolFeeSetting=(SchoolFeeSetting) adminManager.get(SchoolFeeSetting.class, getTempId());
				if (StringFunctions.isNotNullOrEmpty(getTempString())) {
					JSONArray classAdmissionsJSONArray = new JSONArray(getTempString());
					JSONObject admissionsJson = null;
					long particularId = 0;
					FeeType feeType = null;
					FeeType newFeeTypeObj = null;
					String particularName = null;
					boolean particularExist = false;
					String committedFeeStatus = null;
					long priorityPosition =0;
					String pName[] = null;
					for (int i = 0; i < classAdmissionsJSONArray.length(); i++) {
						admissionsJson = classAdmissionsJSONArray.getJSONObject(i);
						if (!ObjectFunctions.isNullOrEmpty(admissionsJson)) {
							particularName = (String) admissionsJson.get("particularName");
							if (StringFunctions.isNotNullOrEmpty(particularName)) {
								pName = particularName.split(",");
								if (!ObjectFunctions.isNullOrEmpty(pName)) {
									for (String parName : pName) {
										if (StringFunctions.isNotNullOrEmpty(parName)) {
											if (StringFunctions.isNotNullOrEmpty((String) admissionsJson.get("particularId"))&& Long.valueOf((String) admissionsJson.get("particularId")) > 0) {
												particularId = Long.valueOf((String) admissionsJson.get("particularId"));
											}
											committedFeeStatus = (String) admissionsJson.get("committedStatus");
											priorityPosition = admissionsJson.getLong("priority");
											StringBuffer queryString = new StringBuffer();
											if (particularId != 0) {
												queryString.append("id="+ particularId);
											} else {
												queryString.append("feeType='"+ parName.trim()+ "' and custId="+ getUserCustId()+ " and academicYearId="+ admissionAcademicYearId+ " and feeSettingId="+ schoolFeeSetting.getId());
											}
											feeType = (FeeType) adminManager.get(FeeType.class,queryString.toString());

											if (ObjectFunctions.isNullOrEmpty(feeType)) {
												feeType = new FeeType();
												feeType.setCreatedById(getUser().getId());
												feeType.setLastUpdatedDate(new Date());
											}else if (particularId==0){
												if(ObjectFunctions.isNullOrEmpty(feeType)){
													feeType = new FeeType();
													feeType.setCreatedById(getUser().getId());
													feeType.setLastUpdatedDate(new Date());
												}else{
													particularExist = true;
													continue;
												}
											}
											else if (!ObjectFunctions.isNullOrEmpty(feeType)){
												newFeeTypeObj = (FeeType) adminManager.get(FeeType.class,"feeType='"+ parName.trim()+ "' and id not in ("+feeType.getId()+") and custId="+ getUserCustId()+ " and academicYearId="+ admissionAcademicYearId+ " and feeSettingId="+ schoolFeeSetting.getId());
												if (ObjectFunctions.isNullOrEmpty(newFeeTypeObj)) {
													feeType.setLastUpdatedById(getUser().getId());
													feeType.setLastUpdatedDate(new Date());
												} else {
													particularExist = true;
													continue;
												}
											}
											else if (!parName.trim().equalsIgnoreCase(feeType.getFeeType())) {
												newFeeTypeObj = (FeeType) adminManager.get(FeeType.class,"feeType='"+ parName.trim()+ "' and custId="+ getUserCustId()+ " and academicYearId="+ admissionAcademicYearId+ " and feeSettingId="+ schoolFeeSetting.getId());
												if (ObjectFunctions.isNullOrEmpty(newFeeTypeObj)) {
													feeType = new FeeType();
													feeType.setCreatedById(getUser().getId());
													feeType.setLastUpdatedDate(new Date());
												} else {
													particularExist = true;
													continue;
												}
											}else{
												feeType.setLastUpdatedById(getUser().getId());
												feeType.setLastUpdatedDate(new Date());
											}
											feeType.setFeeType(parName.trim());
											feeType.setCustId(getUserCustId());
											feeType.setAcademicYear(admissionAcademicYear);
											feeType.setLastAccessDate(new Date());
											feeType.setFeeSettingId(schoolFeeSetting.getId());
											feeType.setCommittedFeeStatus(committedFeeStatus);
											feeType.setPriorityPosition(priorityPosition);
											adminManager.save(feeType);
										}
									}
								}
							}
						}
						committedFeeStatus=null;
						priorityPosition=0;
						particularId = 0;
					}
					if (particularExist) {
						setFeeTypeList(adminManager.getAll(FeeType.class,"custId=" + getUserCustId()+ " and academicYearId="+ admissionAcademicYearId+" and feeSettingId="+schoolFeeSetting.getId()));
						super.addActionError("Particular already taken!!!.Please change particular name.");
						ajaxGetAdmissionsFeeParticular();
						return "particularNameExist";
					} else {
						boolean status=generateFeeTerms(admissionAcademicYear, getUser().getId(), getCurrentAcademicYear());
						ajaxViewAdmissionFeeTerms();
						ajaxGetAdmissionsFeeParticular();
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	@Actions({ @Action(value = "ajaxViewAdmissionFeeTerms", results = { @Result(location = "../admin/admission/ajaxAdmissionSettingsStep5.jsp", name = "success")}) })
	public String ajaxViewAdmissionFeeTerms() {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxViewAdmissionFeeTerms' method");
			}
			try {
				if(!ObjectFunctions.isNullOrEmpty(getSession().getAttribute("admissionAcademicYearId"))){
					long admissionAcademicYearId = (Long)getSession().getAttribute("admissionAcademicYearId");
					prepareSchoolFeeSettingList();
					setSchoolTermsList(adminManager.getAll(SchoolTerms.class, "custId="+getUserCustId()+" and academicYearId="+admissionAcademicYearId+" and feeSettingId="+getTempId()));
					if(ObjectFunctions.isNotNullOrEmpty(getSchoolTermsList()))
						Collections.sort(getSchoolTermsList());
				}
			} catch (Exception ex) {
	    		ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
	    	return SUCCESS;
	}
	@Actions({ @Action(value = "ajaxGetFeeTypes", results = { @Result(location = "../admin/admission/ajaxAdmissionSettingsStep5.jsp", name = "success") }) })
	public String ajaxGetFeeTypes() {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxGetFeeTypes' method");
			}
			try {
				long academicYearId=0;
				if(!ObjectFunctions.isNullOrEmpty(getSession().getAttribute("admissionAcademicYearId"))){
					academicYearId = (Long)getSession().getAttribute("admissionAcademicYearId");
				}else {
					academicYearId=getUserAcademicYearId();
				}
				prepareSchoolFeeSettingList();
				setSchoolTermsList(adminManager.getAll(SchoolTerms.class, "custId="+getUserCustId()+" and academicYearId="+academicYearId));
				if(ObjectFunctions.isNotNullOrEmpty(getSchoolTermsList()))
					Collections.sort(getSchoolTermsList());
			} catch (Exception ex) {
	    		ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
	    	return SUCCESS;
	}
	@Actions( { @Action(value = "ajaxDoCreateSchoolTerms", results = { @Result(location = "ajaxCreateSchoolTerms.jsp", name = "success") }),
				@Action(value = "ajaxDoAdmissionCreateSchoolTerms", results = { @Result(location = "ajaxCreateSchoolTerms.jsp", name = "success") })})
		public String ajaxDoCreateSchoolTerms() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDoCreateSchoolTerms' method");
		}
		try {
			long academicYearId=0;
			if(!ObjectFunctions.isNullOrEmpty(getSession().getAttribute("admissionAcademicYearId"))){
				academicYearId=((Long)getSession().getAttribute("admissionAcademicYearId"));
				setAnyId(getAnyTitle());
			}else {
				academicYearId=getUserAcademicYearId();
			}
			if(academicYearId > 0){
				loadAcademicYearStartDateAndDates(academicYearId);
				prepareSchoolFeeSettingList();
				setSchoolTermsList(adminManager.getAll(SchoolTerms.class, "custId="+getUserCustId()+" and academicYearId="+ academicYearId+" and feeSettingId="+getTempId()));
				List<Fee> classFeeList=adminManager.getAll(Fee.class, "custId="+getUserCustId()+" and academicYearId="+academicYearId);
				if(!ObjectFunctions.isNullOrEmpty(classFeeList)){
					setClassList(adminManager.getAll(ClassName.class, "custId="+getUserCustId()+" and academicYearId="+academicYearId));
					if(!ObjectFunctions.isNullOrEmpty(getClassList()))
						Collections.sort(getClassList());
				}
				classFeeList=null;
			}
			setSchoolTerms(null);
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
		}

	@Actions( {
			@Action(value = "ajaxAddSchoolTerms", results = { @Result(location = "ajaxViewSchoolFeeSettings.jsp", name = "success") }),
			@Action(value = "ajaxAddAdmissionSchoolTerms", results = { @Result(location = "../admin/admission/ajaxAdmissionSettingsStep5.jsp", name = "success") }) })
	public String ajaxAddSchoolTerms() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxAddSchoolTerms' method");
		}
		try {
			SchoolTerms schoolTermNames = null;
			long academicYearId = 0;
			if (!ObjectFunctions.isNullOrEmpty(getSession().getAttribute("admissionAcademicYearId"))) {
				academicYearId = (Long) getSession().getAttribute("admissionAcademicYearId");
			} else {
				academicYearId = getUserAcademicYearId();
			}
			SchoolFeeSetting schoolFeeSetting = (SchoolFeeSetting) adminManager.get(SchoolFeeSetting.class, getTempId());
			if (academicYearId > 0) {
				if (StringFunctions.isNotNullOrEmpty(getSchoolTerms().getTermName().trim())) {
					schoolTermNames = (SchoolTerms) adminManager.get(SchoolTerms.class, "termName='"+ getSchoolTerms().getTermName().trim()+ "' and academicYearId=" + academicYearId+ " and custId=" + getUserCustId()+ " and feeSettingId="+ schoolFeeSetting.getId());
					if (!ObjectFunctions.isNullOrEmpty(schoolTermNames)) {
						super.addActionError(getSchoolTerms().getTermName().trim()+ " already added for this academic year.");
						setSchoolTermsList(adminManager.getAll(SchoolTerms.class, "custId=" + getUserCustId()+ " and academicYearId="+ academicYearId + " and feeSettingId="+ schoolFeeSetting.getId()));
						return SUCCESS;
					}
				}
				String termId = getParamValue("termDetails_");
				Customer customer = getCustomerByCustId();
				AcademicYear academicYear = (AcademicYear) adminManager.get(AcademicYear.class, academicYearId);
				String fromMonthName = new SimpleDateFormat("MMMM").format(getSchoolTerms().getFromDate());
				String toMonthName = new SimpleDateFormat("MMMM").format(getSchoolTerms().getToDate());
				SchoolTerms schoolTerms = new SchoolTerms();
				schoolTerms.setTermName(getSchoolTerms().getTermName().trim());
				schoolTerms.setFromDate(getSchoolTerms().getFromDate());
				schoolTerms.setToDate(getSchoolTerms().getToDate());
				schoolTerms.setDueDate1(getSchoolTerms().getDueDate1());
				schoolTerms.setDueDate2(getSchoolTerms().getDueDate2());
				schoolTerms.setFromMonthName(fromMonthName);
				schoolTerms.setToMonthName(toMonthName);
				schoolTerms.setAcademicYear(academicYear);
				schoolTerms.setCustomer(customer);
				schoolTerms.setDueDate(getSchoolTerms().getDueDate());
				schoolTerms.setNoOfDays(getSchoolTerms().getNoOfDays());
				//schoolTerms.setMailContentDesc(getSchoolTerms().getMailContentDesc());
				//schoolTerms.setMobileContentDesc(getSchoolTerms().getMobileContentDesc());
				schoolTerms.setApplToNewStuds(getSchoolTerms().isApplToNewStuds());
				schoolTerms.setCreatedById(getUser().getId());
				schoolTerms.setLastAccessDate(new Date());
				schoolTerms.setLastUpdatedDate(new Date());
				schoolTerms.setCreatedDate(new Date());
				schoolTerms.setFeeSetting(schoolFeeSetting);
				SchoolTerms schoolTermsObj = adminManager.saveSchoolTerms(schoolTerms);
				setEmpId(getTempString());
				if (Constants.TRANSPORT_FEES.equalsIgnoreCase(schoolFeeSetting.getSettingName())) {
					if (getCurrentAcademicYear().isTransportFeeByBoardingPoint()) {
						List<RouteBoardingPoints> routeBoardingPointsList = adminManager.getAll(RouteBoardingPoints.class, " custId=" + getUserCustId()+ " and academicYearId="+ getUserAcademicYearId()+" and boardingPointFeeAmount !=0 ");
						if (!ObjectFunctions.isNullOrEmpty(routeBoardingPointsList)) {
							for(RouteBoardingPoints boardingPoints : routeBoardingPointsList){
								ajaxCreateSchoolTermBoardingPointWiseFee(schoolTermsObj, boardingPoints,schoolFeeSetting,academicYear,customer);
							}
						}
					} else {
						if (!ObjectFunctions.isNullOrEmpty(getChkBoxSelectedIds()) && !StringFunctions.isNullOrEmpty(termId)) {
							ajaxCreateSchoolTermFee(schoolTermsObj,Long.valueOf(termId));
						}
					}
				} else {
					if (!ObjectFunctions.isNullOrEmpty(getChkBoxSelectedIds()) && !StringFunctions.isNullOrEmpty(termId)) {
						ajaxCreateSchoolTermFee(schoolTermsObj,Long.valueOf(termId));
					}
				}
				super.addActionMessage("School term created successfully.");
				setSchoolTermsList(adminManager.getAll(SchoolTerms.class,"custId=" + getUserCustId() + " and academicYearId="+ academicYearId + " and feeSettingId="+ schoolFeeSetting.getId()));
				setTempId(schoolFeeSetting.getId());
				prepareSchoolFeeSettingList();
				 customer=null;
				 academicYear=null;
			}
			 setTempString(getEmpId());
			 ajaxSchoolFeeSetting(); 
			
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}

	public void ajaxCreateSchoolTermFee(SchoolTerms schoolTermsObj,long termId) throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxCreateSchoolTermFee' method");
		}
		try {
			List<SchoolCategory> schoolCategoryList = adminManager.getAllByCustId("SchoolCategory", getUserCustId(),0);
			if (!ObjectFunctions.isNullOrEmpty(schoolCategoryList)) {
				prepareCategoryIds(schoolCategoryList);
				List<Fee> classFeeList = adminManager.getAllFeeByClasIdAndCustIdAndTermId("Fee",getChkBoxSelectedIds(), getUserCustId(),getUserAcademicYearId(), termId,getTempString());
				if (!ObjectFunctions.isNullOrEmpty(classFeeList)) {
					for (Fee fee : classFeeList) {
						Fee newFee = new Fee();
						newFee.setSchoolTerms(schoolTermsObj);
						newFee.setAcademicYear(fee.getAcademicYear());
						newFee.setClassName(fee.getClassName());
						newFee.setFeeType(fee.getFeeType());
						newFee.setCustomer(getCustomerByCustId());
						newFee.setFeeAmount(fee.getFeeAmount());
						newFee.setCategoryId(fee.getCategoryId());
						newFee.setCreatedById(getUser().getId());
						newFee.setCreatedDate(new Date());
						newFee.setLastUpdatedById(getUser().getId());
						newFee.setLastUpdatedDate(new Date());
						newFee.setLastAccessDate(new Date());
						adminManager.save(newFee);
						fee = null;
					}
				}
				classFeeList = null;
				schoolCategoryList = null;
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
	}
	@Actions( { @Action(value = "ajaxEditManageSchooTerms", results = { @Result(location = "ajaxEditSchoolTerms.jsp", name = "success") }) })
	public String ajaxEditManageSchooTerms() throws URTUniversalException {
	if (log.isDebugEnabled()) {
		log.debug("Entering 'ajaxEditManageSchooTerms' method");
	}
	try {
			setTempString(getAnyTitle());
			loadAcademicYearStartDateAndDates(getUserAcademicYearId());
			 prepareSchoolFeeSettingList();
			setSchoolTerms((SchoolTerms)adminManager.get(SchoolTerms.class, Long.valueOf(getParamValue("schoolTermId"))));
			/*@Ganesh - Below condition we will check for to restricting fee setting type. If we already assign fee for respective term we should not change fee setting type. */
			setClassFeeCountList(adminManager.getAll(Fee.class, "custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and schoolTermId="+getParamValue("schoolTermId")));
			setSchoolFeeSetting((SchoolFeeSetting)adminManager.get(SchoolFeeSetting.class, getTempId()));
			setSchoolTermsList(adminManager.getAll(SchoolTerms.class,"custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and feeSettingId="+getTempId()));
			List<Fee> classFeeList=adminManager.getAll(Fee.class, "custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId());
			if(!ObjectFunctions.isNullOrEmpty(classFeeList)){
				setClassList(adminManager.getAll(ClassName.class,  "custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()));
			}
			classFeeList=null;
	} catch (Exception ex) {
		ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	}
	return SUCCESS;
	}
	@Actions( { @Action(value = "ajaxEditSchoolTerms", results = { @Result(location = "ajaxViewSchoolFeeSettings.jsp", name = "success") }),
		@Action(value = "ajaxEditAdmissionSchoolTerms", results = { @Result(location = "../admin/admission/ajaxAdmissionSettingsStep5.jsp", name = "success") })})
	public String ajaxEditSchoolTerms() throws URTUniversalException {
	if (log.isDebugEnabled()) {
		log.debug("Entering 'ajaxEditSchoolTerms' method");
	}
	try {
		SchoolTerms schoolTerms=null;
		SchoolFeeSetting feeSetting=null;
		if(getSchoolTerms().getId() != 0 && getAcademicYearId() > 0){
		 SchoolTerms schoolTermNames = (SchoolTerms)adminManager.get(SchoolTerms.class,"termName='"+getSchoolTerms().getTermName().trim()+"' and academicYearId="+getAcademicYearId()+" and custId="+getUserCustId()+" and id !="+getSchoolTerms().getId()+" and feeSettingId="+getTempId());
		if(!ObjectFunctions.isNullOrEmpty(schoolTermNames)){
			super.addActionError(getSchoolTerms().getTermName().trim()+" already added for this academic year.");
			setSchoolTermsList(adminManager.getAll(SchoolTerms.class, "custId="+getUserCustId()+" and academicYearId="+getAcademicYearId()));
			ajaxSchoolFeeSetting();
			return SUCCESS;
		}
		schoolTerms=(SchoolTerms)adminManager.get(SchoolTerms.class, getSchoolTerms().getId());
		feeSetting=(SchoolFeeSetting)adminManager.get(SchoolFeeSetting.class, getTempId());
		if(!ObjectFunctions.isNullOrEmpty(schoolTerms)){
			String fromMonthName=new SimpleDateFormat("MMMM").format(getSchoolTerms().getFromDate());
			String toMonthName=new SimpleDateFormat("MMMM").format(getSchoolTerms().getToDate());
			schoolTerms.setTermName(getSchoolTerms().getTermName());
			schoolTerms.setFromDate(getSchoolTerms().getFromDate());
			schoolTerms.setToDate(getSchoolTerms().getToDate());
			schoolTerms.setDueDate1(getSchoolTerms().getDueDate1());
			schoolTerms.setDueDate2(getSchoolTerms().getDueDate2());
			schoolTerms.setFromMonthName(fromMonthName);
			schoolTerms.setToMonthName(toMonthName);
			schoolTerms.setDueDate(getSchoolTerms().getDueDate());
			schoolTerms.setNoOfDays(getSchoolTerms().getNoOfDays());
			//schoolTerms.setMailContentDesc(getSchoolTerms().getMailContentDesc());
			//schoolTerms.setMobileContentDesc(getSchoolTerms().getMobileContentDesc());
			schoolTerms.setLastUpdatedById(getUser().getId());
			schoolTerms.setLastUpdatedDate(new Date());
			schoolTerms.setLastAccessDate(new Date());
			schoolTerms.setFeeSetting(feeSetting);
			schoolTerms.setApplToNewStuds(getSchoolTerms().isApplToNewStuds());
			adminManager.save(schoolTerms);
		}
		super.addActionMessage("School terms updated successfully.");
		}
		//ajaxSchoolFeeSetting();
		setSchoolTermsList(adminManager.getAll(SchoolTerms.class, "custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and feeSettingId="+getTempId()));		
		prepareSchoolFeeSettingList();		
		schoolTerms=null;
	} catch (Exception ex) {
		ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	}
	return SUCCESS;
	}
	@Actions( { @Action(value = "ajaxRemoveFeeTerms", results = { @Result(location = "ajaxViewSchoolFeeSettings.jsp", name = "success") }),
		@Action(value = "ajaxRemoveFeeTermsForAdmissons", results = { @Result(location = "../admin/admission/ajaxAdmissionSettingsStep5.jsp", name = "success") })
		})
	public String removeFeeTerms() {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxRemoveFeeTerms' method");
		}
		try {
			//This methos is calling from two pages ajaxAdmissionSettingsStep5.jsp, ajaxViewSchoolFeeSettings.jsp
			setTempString("feeTerms");
			if (getSchoolTerms().getId() != 0 && getAcademicYearId() > 0) {
				SchoolTerms schoolTerms=(SchoolTerms) adminManager.get(SchoolTerms.class, getSchoolTerms().getId());
				if (!ObjectFunctions.isNullOrEmpty(schoolTerms)) {
					List<Fee> schoolFeeList=adminManager.getAll(Fee.class, " custId="+ getUserCustId() + " and academicYearId="+ getAcademicYearId() + " and schoolTermId="+ schoolTerms.getId()+" and feeAmount !=0 ");
					if (ObjectFunctions.isNullOrEmpty(schoolFeeList)) {
						List<Fee> classFee=adminManager.getAll(Fee.class, " custId="+getUserCustId()+" and academicYearId="+getAcademicYearId()+" and schoolTermId="+schoolTerms.getId()+" and feeAmount = 0 ");
						if(!ObjectFunctions.isNullOrEmpty(classFee)){
							for(Fee fee:classFee){
								fee.setAcademicYear(null);
								fee.setClassName(null);
								fee.setFeeType(null);
								fee.setSchoolTerms(null);
								adminManager.remove(Fee.class, fee.getId());
							}
						}
						schoolTerms.setAcademicYear(null);
						adminManager.remove(SchoolTerms.class,schoolTerms.getId());
						super.addActionMessage(schoolTerms.getTermName()+ " term deleted successfully.");
						return SUCCESS;
					} else {
						super.addActionError(schoolTerms.getTermName()+ " term contains fees.You can't remove this term.");
						return SUCCESS;
					}
				}
				schoolTerms=null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			prepareSchoolFeeSettingList();
			setSchoolTermsList(adminManager.getAll(SchoolTerms.class, "custId="+getUserCustId()+" and academicYearId="+getAcademicYearId()+" and feeSettingId="+getTempId()));
		}
		return SUCCESS;
	}
	@Actions( { 
		@Action(value = "adminAllSchoolFeeSettings", results = { @Result(location = "ajaxGetSchoolFeeSettings.jsp", name = "success") }),
		@Action(value = "ajaxDoFeeCategory", results = { @Result(location = "ajaxViewCategoryList.jsp", name = "success") }),
		@Action(value = "ajaxDoGetClassFee", results = { @Result(location = "ajaxViewCategoryList.jsp", name = "success") }) })
	public String ajaxDoFeeCategory() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDoFeeCategory' method");
		}
		try {
			prepareSchoolFeeSettingList();
			setSchoolCategoriesList(adminManager.getAllByCustId("SchoolCategory", getUserCustId(),0));
			if(!ObjectFunctions.isNullOrEmpty(getSchoolCategoriesList())){
				SchoolCategory category = (SchoolCategory) getSchoolCategoriesList().get(0);
				if(getTempId2()==0)
				setTempId2(category.getId());
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}

	@Actions( {
			@Action(value = "ajaxAdminGetSchoolFee", results = { @Result(location = "ajaxGetAllSchoolFee.jsp", name = "success") }),
			@Action(value = "ajaxAdminGetCategoryFee", results = { @Result(location = "ajaxGetAllSchoolFee.jsp", name = "success") }),
			@Action(value = "ajaxBackAdminGetSchoolFee", results = { @Result(location = "ajaxGetSchoolFeeSettings.jsp", name = "success") }) })
	public String ajaxAdminAllSchoolFeeSettings() throws URTUniversalException {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxAdminAllSchoolFeeSettings' method");
		}
		try {
			setClassList(new ArrayList<ClassName>());
			if (!ObjectFunctions.isNullOrEmpty(getSession().getAttribute("admissionAcademicYearId"))) {
				setAcademicYearId((Long) getSession().getAttribute("admissionAcademicYearId"));
				setTempString("admissionClassFee");
			} else {
				setAcademicYearId(getUserAcademicYearId());
			}
			setAcademicYear((AcademicYear)adminManager.get(AcademicYear.class, getAcademicYearId()));
			setSchoolFeeSetting((SchoolFeeSetting) adminManager.get(SchoolFeeSetting.class, getTempId()));
			if (getAcademicYearId() > 0) {
				setFeeTypeList(adminManager.getFeeTypeListByAcademicYearId("FeeType", getUserCustId(), getAcademicYearId(),getSchoolFeeSetting().getId()));
				if (!StringFunctions.isNullOrEmpty(getAnyId())) {
					setSelectedId(getAnyId());
					setSchoolTermsList(adminManager.getSchoolTermsByDuedate("SchoolTerms", getUserCustId(), getAcademicYearId(),getAnyId(), getSchoolFeeSetting().getId()));
				} else {
					setSchoolTermsList(adminManager.getSchoolTermsOrderByDuedate("SchoolTerms",getUserCustId(), getAcademicYearId(),getSchoolFeeSetting().getId()));
				}
				if (!ObjectFunctions.isNullOrEmpty(getSchoolTermsList())) {
					/*if (StringFunctions.isNotNullOrEmpty(getAnyId())) {
						setTempId1(5);
					} else
						setTempId1(getSchoolTermsList().size());
					if (getSchoolTermsList().size() >= 4) {
						setSchoolTermsList(getSchoolTermsList().subList(0, 4));
					}*/
					setSchoolCategory((SchoolCategory) adminManager.get(SchoolCategory.class, getTempId2()));
					if (!ObjectFunctions.isNullOrEmpty(getSchoolCategory())) {
						if (Constants.TRANSPORT_FEES.equalsIgnoreCase(getSchoolFeeSetting().getSettingName())) {
							if (getCurrentAcademicYear().isTransportFeeByBoardingPoint()) {
								ajaxGetSchoolTermBoardingPointWiseFee();
							} else {
								ajaxGetSchoolTermClassWiseFee();
							}
						} else {
							ajaxGetSchoolTermClassWiseFee();
						}
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	@Actions( { @Action(value = "ajaxDoAddClassFee", results = { @Result(location = "ajaxCreateAndEditClassWiseFee.jsp", name = "success") }) })
	public String ajaxDoAddClassFee() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDoAddClassFee' method");
		}
		try {
			long academicYearId=0;
			if (!ObjectFunctions.isNullOrEmpty(getSession().getAttribute("admissionAcademicYearId"))) {
				academicYearId=(Long) getSession().getAttribute("admissionAcademicYearId");
			}else {
				academicYearId=getUserAcademicYearId();
			}
			setClassList(adminManager.getAll(ClassName.class, "custId="+getUserCustId()+" and academicYearId="+academicYearId));
			if (!ObjectFunctions.isNullOrEmpty(getClassList()))
				Collections.sort(getClassList());
			prepareSchoolFeeSettingList();
			setTempList1(adminManager.getAll(SchoolCategory.class, "custId="+getUserCustId()));
		}catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	@Actions( { @Action(value = "ajaxDoSelectAddClassFee", results = { @Result(location = "ajaxSchoolFee.jsp", name = "success") }) })
	public String ajaxDoSelectAddClassFee() throws URTUniversalException {
	if (log.isDebugEnabled()) {
		log.debug("Entering 'ajaxDoSelectAddClassFee' method");
	}
	try {
		long academicYearId = 0;
		setTempList(null);
		//getTempList().add(getAnyId());
		if (!ObjectFunctions.isNullOrEmpty(getSession().getAttribute("admissionAcademicYearId"))) {
			academicYearId=(Long) getSession().getAttribute("admissionAcademicYearId");
		}else {
			academicYearId=getUserAcademicYearId();
		}
		setAcademicYear((AcademicYear)adminManager.get(AcademicYear.class, academicYearId));
		//setSchoolTermchkBoxSelectedIds(getTempList());
		if (!StringFunctions.isNullOrEmpty(getClassId())) {
			setClassName((ClassName) adminManager.get(ClassName.class, Long.valueOf(getClassId())));
		}
		setSchoolFeeSetting((SchoolFeeSetting) adminManager.get(SchoolFeeSetting.class, getTempId()));
		if (!StringFunctions.isNullOrEmpty(getAnyTitle())) {
			setSchoolTermsList(adminManager.getSchoolTermsByDuedate("SchoolTerms", getUserCustId(),academicYearId, getAnyTitle(),getSchoolFeeSetting().getId()));
		} else {
			setSchoolTermsList(adminManager.getSchoolTermsOrderByDuedate("SchoolTerms", getUserCustId(),academicYearId, getSchoolFeeSetting().getId()));
		}
		/*if (!ObjectFunctions.isNullOrEmpty(getSchoolTermsList())) {
			if (getSchoolTermsList().size() >= 4) {
				setSchoolTermsList(getSchoolTermsList().subList(0, 4));
			}
		}*/
		setClassFeeTypeList(adminManager.getAll(ViewClassFeeType.class,"classId=" + getClassId() + " and custId="+ getUserCustId() + " and academicYearId="+ academicYearId + " and feeSettingId="+ getTempId()));
		ViewStudentFeePaymentDetails viewStudentFeePaymentDetails = (ViewStudentFeePaymentDetails) adminManager.get(ViewStudentFeePaymentDetails.class, " custId="+ getUserCustId() + " and paymentStatus='P'"+ " and classId=" + Long.valueOf(getClassId())+ " and academicYearId=" + academicYearId+ " and categoryId=" + getAnyId());
		if (ObjectFunctions.isNullOrEmpty(viewStudentFeePaymentDetails)){
			List<SchoolCategory> schoolCategoryList = adminManager.getAll(SchoolCategory.class, "custId="+getUserCustId()+" and id not in ("+getAnyId()+")");
			if(!ObjectFunctions.isNullOrEmpty(schoolCategoryList)){
				for(SchoolCategory schoolCategory : schoolCategoryList){
					viewStudentFeePaymentDetails = (ViewStudentFeePaymentDetails) adminManager.get(ViewStudentFeePaymentDetails.class, " custId="+ getUserCustId() + " and paymentStatus='P'"+ " and classId=" + Long.valueOf(getClassId())+ " and academicYearId=" + academicYearId+ " and categoryId=" + schoolCategory.getId());
					if (ObjectFunctions.isNullOrEmpty(viewStudentFeePaymentDetails))
						getSchoolCategoriesList().add(schoolCategory);
					else
						getTempList().add(schoolCategory);
				}
			}
		} 
			//setSchoolCategoriesList(adminManager.getAll(SchoolCategory.class, "custId="+getUserCustId()+" and id not in ("+getAnyId()+")"));
		log.debug(getSchoolCategoriesList().size());
		List<ClassName> prekgActiveClassNames = adminManager.getClassesByClassIdsAndAdmissionStatus(getUserCustId(),academicYearId,null,getClassId(),false);
		setClassList(new ArrayList<ClassName>());
		for (ClassName className : prekgActiveClassNames) {
			setViewStudentFeeDetails((ViewStudentFeePaymentDetails) adminManager.get(ViewStudentFeePaymentDetails.class, "custId="+ getUserCustId() + " and academicYearId="+ academicYearId+ " and studentpaymentId!=0" + " and classId="+ className.getId()+ " and feeSettingId="+getTempId()+" and categoryId="+getAnyId()+" and description is null"));
			if (!ObjectFunctions.isNullOrEmpty(getViewStudentFeeDetails())) {
				getTempList2().add(className);
			} else {
				getClassList().add(className);
			}
		}
	} catch (Exception ex) {
		ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	}
	return SUCCESS;
	}
	@Actions( { @Action(value = "ajaxEditClassFeeDetails", results = { @Result(type = "json", name = "success", params = {"includeProperties", "classFeeList.*" }) }) })
	public String ajaxEditClassFeeDetails() throws URTUniversalException {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxEditClassFeeDetails' method");
		}
		try {
			long academicYearId=0;
			if(!ObjectFunctions.isNullOrEmpty(getSession().getAttribute("admissionAcademicYearId"))){
				academicYearId=(Long)getSession().getAttribute("admissionAcademicYearId");
			}else {
				academicYearId=getUserAcademicYearId();
			}
			if (!StringFunctions.isNullOrEmpty(classId)) {
				List<Fee> classFeeList=adminManager.getAllFeeByClasIdAndCustId("Fee",getUserCustId(), Long.valueOf(getParamValue("classId")),academicYearId, Long.valueOf(getParamValue("categoryId")));
				if (ObjectFunctions.isNotNullOrEmpty(classFeeList)) {
					JSONArray res = new JSONArray();
					JSONObject j;
					for (Fee fee : classFeeList) {
						List<StudentFeePaidDetails> studentPaymentList=adminManager.getAll(StudentFeePaidDetails.class, " feeId ="+fee.getId()+" and custId="+getUserCustId()+" and deleteStatus='"+Constants.NO_STRING+"'");
						j = new JSONObject();
						j.put("feeId", fee.getId());
						j.put("feeTypeId", fee.getFeeType().getId());
						j.put("termId", fee.getSchoolTerms().getId());
						j.put("amount", fee.getFeeAmount());
						j.put("studentPaymentId", studentPaymentList.size());
						res.put(j);
						studentPaymentList=null;
					}
					j = new JSONObject();
					j.put("data", res);
					// getResponse().setHeader("X-JSON", res.toString());
					getResponse().getOutputStream().print(j.toString());
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
	@Actions( { @Action(value = "ajaxAddSchoolFee", results = { @Result(location = "ajaxViewCategoryList.jsp", name = "success") }) })
	public String addSchoolFee() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxAddSchoolFee' method");
		}
		try {
			if(!StringFunctions.isNullOrEmpty(getTempString())){
			JSONObject formData = new JSONObject(getTempString());
			JSONArray classFeeTypesJsonArray = (JSONArray) formData.get("data");
			JSONArray classIds = (JSONArray) formData.get("chkBoxSelectedIds");
			JSONArray categoryIds = (JSONArray) formData.get("schoolTermchkBoxSelectedIds");
			long feeTypeId = 0;
			long schoolTermId = 0;
			long feeId = 0;
			long feeAmount = 0;
			JSONObject examScheduleJson = null;
		//	JSONObject categoryIdJson = null;
		//	JSONObject classIdJson = null;
			ClassName className = null;
			String classId = null;
			String categotyId = null;
			FeeType feeType = null;
			SchoolTerms schoolTerms = null;
			AcademicYear academicYear = null;
			int add = 0;
			int update = 0;
			if(!ObjectFunctions.isNullOrEmpty(getSession().getAttribute("admissionAcademicYearId"))){
				long admissionAcademicYearId = (Long)getSession().getAttribute("admissionAcademicYearId");
				academicYear = (AcademicYear)adminManager.get(AcademicYear.class, "custId="+getUserCustId()+" and id="+admissionAcademicYearId);
			}else {
				academicYear = getCurrentAcademicYear();
			}
			for (int j = 0; j < categoryIds.length(); j++) {
				categotyId = (String) categoryIds.get(j);
				if (!StringFunctions.isNullOrEmpty(categotyId)) {
					//SchoolCategory schoolCatecory = (SchoolCategory) adminManager.get(SchoolCategory.class, Long.valueOf(categotyId));
						for (int k = 0; k < classIds.length(); k++) {
							classId = (String) classIds.get(k);
							if (!StringFunctions.isNullOrEmpty(classId)) {
								className = (ClassName) adminManager.get(ClassName.class, Long.valueOf(classId));
								if (!ObjectFunctions.isNullOrEmpty(className)) {
									for (int i = 0; i < classFeeTypesJsonArray.length(); i++) {
										examScheduleJson = classFeeTypesJsonArray.getJSONObject(i);
										if (!ObjectFunctions.isNullOrEmpty(examScheduleJson)) {
											if (!ObjectFunctions.isNullOrEmpty(examScheduleJson.get("feeId"))) {
												feeId = Long.valueOf((String) examScheduleJson.get("feeId"));
											}
											if (!ObjectFunctions.isNullOrEmpty(examScheduleJson.get("feeTypeId"))) {
												feeTypeId = Long.valueOf((String) examScheduleJson.get("feeTypeId"));
											}
											if (!ObjectFunctions.isNullOrEmpty(examScheduleJson.get("schoolTermId"))) {
												schoolTermId = Long.valueOf((String) examScheduleJson.get("schoolTermId"));
											}
											if (!ObjectFunctions.isNullOrEmpty(examScheduleJson.get("feeAmount"))) {
												feeAmount = Long.valueOf((String) examScheduleJson.get("feeAmount"));
											}
											
											Fee newFee = (Fee)adminManager.getFeeAndFeeTypeByClassId("Fee",Long.valueOf(classId),Long.valueOf(feeTypeId),getUserCustId(),academicYear.getId(),schoolTermId,Long.valueOf(categotyId));
											if (!ObjectFunctions.isNullOrEmpty(newFee)) {
												/*if (feeAmount != 0) {
													newFee.setFeeAmount(feeAmount);
												} else {
													newFee.setFeeAmount(feeAmount);
													adminManager.updateStudentPaymentStatus(className.getId());
												}*/
												log.debug("feeAmount :"+feeAmount+"newFee.getFeeAmount() :"+newFee.getFeeAmount());
												/*if(newFee.getFeeAmount() >0 && feeAmount !=0)
													adminManager.updateStudentPaymentStatus(Long.valueOf(classId),Long.valueOf(categotyId));*/
												/* Ganesh : Here we will create fee for one class for two particulars and we paid one particular for one student and We are going to remove
												 * fee for remaining fee particualr in this case I need to change the student fee paid status as "F" because that student already paid amount for remaining fee
												 * particular and in this scenario I am going to remove fee record which we assign "0" for respective fee type */
												if (feeAmount != 0) {
													newFee.setFeeAmount(feeAmount);
													newFee.setLastUpdatedById(getUser().getId());
													newFee.setLastUpdatedDate(new Date());
													newFee.setLastAccessDate(new Date());
													newFee.setStatus(Constants.SCHOOL_MODULE);
													setTempId(newFee.getSchoolTerms().getFeeSetting().getId());
													adminManager.saveOrUpdateObject(newFee);
												}else{
													adminManager.removeClassFee(newFee,className);
												}
												
												newFee=null;
												update += 1;
											} else {
												if (feeAmount != 0) {
												feeType = (FeeType) adminManager.get(FeeType.class,feeTypeId);
												schoolTerms = (SchoolTerms) adminManager.get(SchoolTerms.class,schoolTermId);
												newFee = new Fee();
													newFee.setCustomer(getCustomerByCustId());
													newFee.setFeeType(feeType);
													newFee.setFeeAmount(feeAmount);
													newFee.setClassName(className);
													newFee.setAcademicYear(academicYear);
													newFee.setSchoolTerms(schoolTerms);
													newFee.setCategoryId(Long.valueOf(categotyId));
													newFee.setCreatedById(getUser().getId());
													newFee.setLastUpdatedDate(new Date());
													newFee.setLastAccessDate(new Date());
													newFee.setCreatedDate(new Date());
													adminManager.saveOrUpdateObject(newFee);
													setTempId(schoolTerms.getFeeSetting().getId());
													adminManager.updateStudentPaymentStatusForSchoolFee(Long.valueOf(classId),Long.valueOf(categotyId));
													feeAmount = 0;
												feeType = null;
												schoolTerms = null;
												newFee=null;
												add += 1;
												
												}
											}
											
											feeTypeId = 0;
											schoolTermId = 0;
										}
									}
									className = null;
								}
								//Sending Fee notification to mobile
								schoolFeeManager.sendNotificationForFee(Long.valueOf(classId), Long.valueOf(categotyId), getTempId());
								classId = null;
							}
						}
						//schoolCatecory = null;
						
					categotyId = null;
				}
			}
			super.addActionMessage("Fee settings updated successfully.");
			/*if(add > 0)
				adminManager.sendNotificationToAndroidMobileUsers(getUserCustId(),"Fee added"); //To add notification for mobile app.
			else if(update > 0)	
				adminManager.sendNotificationToAndroidMobileUsers(getUserCustId(),"Fee updated"); //To add notification for mobile app.
*/			
			//ajaxAdminAllSchoolFeeSettings();
		 }
	  }
		catch (Exception ex) {
		  ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	   }
		finally{
			ajaxDoFeeCategory();
		}
		return SUCCESS;
	}
	@Actions({
		@Action(value = "adminGetSchoolFee", results = { @Result(location = "ajaxGetSchoolFeeHome.jsp", name = "success") }),
		@Action(value = "ajaxDoAdminSchoolFee", results = { @Result(location = "ajaxGetSchoolFeeHome.jsp", name = "success") }),
		@Action(value = "ajaxSearchStudentsForMakePayment", results = { @Result(location = "searchStudentDetails.jsp", name = "success") })
	})
	public String adminGetSchoolFee() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'adminGetSchoolFee' method");
		}
		try {
			setCustomer(getCustomerByCustId());
			setStudyClassList(adminManager.GetAllStudyClasses(getUserCustId(), getUserAcademicYearId(),null));
			checkPreviousAcademicYearPensingStudentFee();
			if(!ObjectFunctions.isNullOrEmpty(getSession().getAttribute("showTaskReminder")))
				getTaskReminderToUserLogin();
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}

	@Action(value = "ajaxPayStudentPaymentFee", results = {@Result(location = "searchStudentDetails.jsp", name = "success"),
													@Result(location = "../admin/admission/admittedStudentHome.jsp", name = "admissionStudentInvoice"),
													@Result(location = "ajaxViewPreviousYearPendingFeeDetails.jsp", name = "previousStudentInvoice")  })
	public String ajaxPayStudentPaymentFee() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'editStudentPaymentTypeFee' method");
		}
		StudentPayment studentPayment=null;
		Date paymentDate =null;
		AcademicYear academicYear = null;
		Customer customer = null;
		Student student = null;
		
		try {
			long feeAcademicYearId = 0;
			//getAnyId() - json data, getAnyTitle() - payment type, getSelectedId() - useExcessPayment
			if(StringFunctions.isNotNullOrEmpty(getStudentNumber()) && StringFunctions.isNotNullOrEmpty(getAnyId()) && StringFunctions.isNotNullOrEmpty(getPaymentType())){
				if (getTempId1() != getUserAcademicYearId() && getTempId1() != 0) {
					feeAcademicYearId = getTempId1();
					setAcademicYearId(feeAcademicYearId);
				} else {
					feeAcademicYearId = getUserAcademicYearId();
				}
				academicYear = (AcademicYear) adminManager.get(AcademicYear.class,feeAcademicYearId);
				customer = getCustomerByCustId();
				if(!ObjectFunctions.isNullOrEmpty(academicYear)){
					student = (Student)adminManager.get(Student.class, "custId="+getUserCustId()+" and academicYearId="+feeAcademicYearId+" and id="+Long.valueOf(getStudentNumber()));
					if(!ObjectFunctions.isNullOrEmpty(student)){
						prepareStudentSchoolFeeSettingList(student);
						paymentDate = DateFormatter.parseString(DateFormatter.YYYY_MM_DD_PATTERN, DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN,getStudentPayment().getPaymentDate()));
						
						setTodayDate(DateFormatter.formatDate(DateFormatter.ddMMMyyyy_PATTERN1, paymentDate));//For printing the receipt
						/*@Ganesh If we are using above method I am not able to pay student payment because while do promotion process few student details status changed to status as N. So I am not able to get those students that's why I removed status in where condition */
							setFeePaymentType("current");
							getStudentPayment().setAcademicYear(academicYear);
							getStudentPayment().setStudent(student);
							setEmpId(String.valueOf(getStudentPayment().getPaidAmount())); // Here we are getting paid Amount to show in words.
								getStudentPayment().setAcademicYear(academicYear);
								getStudentPayment().setStudent(student);
								setEmpId(String.valueOf(getStudentPayment().getPaidAmount()));
								if("CL".equalsIgnoreCase(getStudentPayment().getPaymentType())) // CL Means Challan generation.
									getStudentPayment().setInvoiceString(getParamValue("challanaNumber"));
								//Siva 22/11/2017- setting total due amount for saving total balance amount
								if(!ObjectFunctions.isNullOrEmpty(getPaymentAmount()) && getPaymentAmount() > 0)
								{
									getStudentPayment().setTotalDueAmount(getPaymentAmount());
								}
								//end
								studentPayment=schoolFeeManager.makeStudentFeePayment(getAnyId(), getUser().getId(),getPaymentType(),getSelectedId(),"N",0,0,getStudentPayment());
								if("Y".equalsIgnoreCase(customer.getAccountModuleUsing()))
									if(!ObjectFunctions.isNullOrEmpty(studentPayment))
										schoolFeeManager.chashBookPaymentEntry(studentPayment);
								
							//Sending Fee Payment notification to mobile
							if(!ObjectFunctions.isNullOrEmpty(student.getAccount().getStudentParent()))
							adminManager.sendNotificationForFeePayment(student.getCustId(), student.getId(),ObjectFunctions.isNullOrEmpty(student.getAccount().getStudentParent().getId())?0:Long.valueOf(student.getAccount().getStudentParent().getId()),"P");
							setStudentNumber(getStudentNumber());//For printing the receipt
							if(!"C".equalsIgnoreCase(studentPayment.getDeleteStatus())){
								if("Pay".equalsIgnoreCase(getPlSubTopic())){
									ViewStudentClassDetails viewStudentClassDetails = (ViewStudentClassDetails)adminManager.get(ViewStudentClassDetails.class,"studId="+getStudentNumber()+" and custId="+getUserCustId()+" and studDiscontinueDesc is null");
									if(!ObjectFunctions.isNullOrEmpty(viewStudentClassDetails)){
										if(!ObjectFunctions.isNullOrEmpty(academicYear.getReceiptGenerationType()) && academicYear.getReceiptGenerationType().equalsIgnoreCase("A")){
											setStudentPaymentList(adminManager.getAll(ViewStudentFeePaymentDetails.class, " studentId="+viewStudentClassDetails.getStudId()+" and deleteStatus='"+Constants.NO_STRING+"' and invoiceNumber="+String.valueOf(getStudentPayment().getInvoiceNumber())+" and paymentCommitFeeStatus='"+Constants.NO_STRING+"' order by schoolTermId,feeTypeId"));
											setStudentTransportTermsList(adminManager.getAll(ViewStudentTransportFeePaymentDetails.class, " studentId="+viewStudentClassDetails.getStudId()+" and deleteStatus='"+Constants.NO_STRING+"' and invoiceNumber="+String.valueOf(getStudentPayment().getInvoiceNumber())+" and paymentCommitFeeStatus='"+Constants.NO_STRING+"' order by schoolTermId,feeTypeId"));
										} else {
											setStudentPaymentList(adminManager.getAll(ViewStudentFeePaymentDetails.class, " studentId="+viewStudentClassDetails.getStudId()+" and deleteStatus='"+Constants.NO_STRING+"' and invoiceString='"+String.valueOf(getStudentPayment().getInvoiceString())+"' and paymentCommitFeeStatus='"+Constants.NO_STRING+"' order by schoolTermId,feeTypeId"));
											setStudentTransportTermsList(adminManager.getAll(ViewStudentTransportFeePaymentDetails.class, " studentId="+viewStudentClassDetails.getStudId()+" and deleteStatus='"+Constants.NO_STRING+"' and invoiceString='"+String.valueOf(getStudentPayment().getInvoiceString())+"' and paymentCommitFeeStatus='"+Constants.NO_STRING+"' order by schoolTermId,feeTypeId"));
										}
										ajaxGenerateFeeReceiptPDFReport(viewStudentClassDetails,getStudentPaymentList(),getTotalAmount(),studentPayment,getTempString(),customer,academicYear,student,getStudentTransportTermsList());
									}
								}
							}
							
							if(!"CL".equalsIgnoreCase(getStudentPayment().getPaymentType())){
								setQuizId(getStudentPayment().getInvoiceNumber());//For printing the receipt
								setAlertSendType(getStudentPayment().getInvoiceString());  // Using alertSendType when u select Fee Generation type Is manual show the receipt Number in pdf format
								super.addActionMessage("Payment made successfully.");
							}else{
								setQuizId(Long.valueOf(getParamValue("challanaNumber")));//For printing the receipt
								super.addActionMessage("Challana generated successfully.");
							}
					}
				}
				
			}
			if ("viewAdmittedStudents".equalsIgnoreCase(getDescription())) {
				getSmsCount();
				getAdmissionsOnlineApplicationDetails();
				setAcademicYear(academicYear);
				setTempId2(getTempId1());
				return "admissionStudentInvoice";
			}else if ("prevStudInvPaymt".equalsIgnoreCase(getDescription())) {
				setAcademicYearId(feeAcademicYearId);
				ajaxViewPreviousPendingFeeDetails();
				return "previousStudentInvoice";
			}
		} catch (Exception ex) {
			log.error("Entering into 'catch block':" + ex.getMessage());
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		} finally {
			if (!"prevStudInvPaymt".equalsIgnoreCase(getDescription()))
				adminGetSchoolFee();
			studentPayment=null;
			paymentDate=null;
			academicYear = null;
			customer = null;
			student = null;
		}
		return SUCCESS;
	}
	
	@Action(value = "ajaxStudentFutureAcademicPaymentFee", results = {@Result(location = "searchStudentDetails.jsp", name = "success") })
	public String ajaxStudentFutureAcademicPaymentFee() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxStudentFutureAcademicPaymentFee' method");
		}
		try {
			//getAnyId() - json data, getAnyTitle() - payment type, getSelectedId() - useExcessPayment
			StudentPayment studentPayment=null;
			if(StringFunctions.isNotNullOrEmpty(getStudentNumber()) && StringFunctions.isNotNullOrEmpty(getAnyId())
					&& StringFunctions.isNotNullOrEmpty(getAnyTitle()) && getTempId1() > 0 && StringFunctions.isNotNullOrEmpty(getClassSectionId())){
				long feeAcademicYearId = getTempId1();
				Date paymentDate = DateFormatter.parseString(DateFormatter.YYYY_MM_DD_PATTERN, DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN,getStudentPayment().getPaymentDate()));
				AcademicYear academicYear = (AcademicYear) adminManager.get(AcademicYear.class,feeAcademicYearId);
				Student student = null;
				if(!ObjectFunctions.isNullOrEmpty(academicYear)){
					setFeePaymentType("future");
					student = (Student)adminManager.get(Student.class, "id="+getStudentNumber()+" and status='Y'");
					if(!ObjectFunctions.isNullOrEmpty(student)){
						prepareStudentSchoolFeeSettingList(student);
						String[] classIds= getClassSectionId().split(":");
						setEmpId(String.valueOf(getStudentPayment().getPaidAmount())); // Here we are getting paid Amount to show in words.
						
						/*SchoolFeeUtil feeUtil = new SchoolFeeUtil();
						getStudentPayment().setAcademicYear(academicYear);
						getStudentPayment().setStudent(student);
						feeUtil.setStudentPayment(getStudentPayment());
						feeUtil.makeStudentFeePayment(getAnyId(), getUser().getId(),getAnyTitle(),getSelectedId(),"Y",Long.valueOf(classIds[1]),Long.valueOf(classIds[0]));*/
						
						
						getStudentPayment().setAcademicYear(academicYear);
						getStudentPayment().setStudent(student);
						// feeUtil.setStudentPayment(getStudentPayment());
						setEmpId(String.valueOf(getStudentPayment().getPaidAmount()));
						studentPayment=schoolFeeManager.makeStudentFeePayment(getAnyId(), getUser().getId(),getAnyTitle(),getSelectedId(),"Y",Long.valueOf(classIds[1]),Long.valueOf(classIds[0]),getStudentPayment());
						if("Y".equalsIgnoreCase(getCustomerByCustId().getAccountModuleUsing()))
							if(!ObjectFunctions.isNullOrEmpty(studentPayment))
								schoolFeeManager.chashBookPaymentEntry(studentPayment);
						
						
						setAnyId(String.valueOf(student.getId()));//For printing the receipt
						if("Pay".equalsIgnoreCase(getPlSubTopic())){
							ViewStudentClassDetails viewStudentClassDetails = (ViewStudentClassDetails)adminManager.get(ViewStudentClassDetails.class,"studId="+getStudentNumber()+" and custId="+getUserCustId()+" and studDiscontinueDesc is null");
							if(!ObjectFunctions.isNullOrEmpty(viewStudentClassDetails)){
								Object[] paidAmount=null;
								setAcademicYearId(getTempId1());
								if(!ObjectFunctions.isNullOrEmpty(academicYear.getReceiptGenerationType()) && academicYear.getReceiptGenerationType().equalsIgnoreCase("A")){
								setStudentPaymentList(adminManager.getFutureAcademicYearStudentFeeReceipts(Long.valueOf(getStudentNumber()),getTempId1(),String.valueOf(studentPayment.getInvoiceNumber())));
								paidAmount=adminManager.get("select sp.paidAmount,sp.discountAmount,IFNULL(ep.excessAmount,0) as excessAmount,sp.id,sp.discountDesc,sum(uep.excessAmount) as usedExcessAmount,sp.desktopReceiptNumber  from studentPayment sp LEFT JOIN excessPayment ep on (sp.id=ep.paymentId) LEFT JOIN excessPayment uep on (sp.id=uep.usedPaymentId) where sp.custId="+getUserCustId()+" and sp.invoiceNumber="+String.valueOf(studentPayment.getInvoiceNumber())+" and sp.studentId="+Long.valueOf(getStudentNumber()));
								}else{
									setStudentPaymentList(adminManager.getFutureAcademicYearStudentFeeReceipts(Long.valueOf(getStudentNumber()),getTempId1(),String.valueOf(studentPayment.getInvoiceString())));
									paidAmount=adminManager.get("select sp.paidAmount,sp.discountAmount,IFNULL(ep.excessAmount,0) as excessAmount,sp.id,sp.discountDesc,sum(uep.excessAmount) as usedExcessAmount,sp.invoiceString  from studentPayment sp LEFT JOIN excessPayment ep on (sp.id=ep.paymentId) LEFT JOIN excessPayment uep on (sp.id=uep.usedPaymentId) where sp.custId="+getUserCustId()+" and sp.invoiceString='"+String.valueOf(studentPayment.getInvoiceString())+"' and sp.studentId="+Long.valueOf(getStudentNumber()));
								}
								if(!ObjectFunctions.isNullOrEmpty(paidAmount)){
									if (Double.valueOf(paidAmount[1].toString()) != 0)
										setThirtyTotalAmount(Double.valueOf(paidAmount[1].toString()));
									if (Double.valueOf(paidAmount[2].toString()) != 0)
										setWishTitle(paidAmount[2].toString());
									setTotalAmount(Double.valueOf(paidAmount[0].toString()));
									//setFourteenTotalAmount(Double.valueOf(paidAmount[0].toString())+Double.valueOf(paidAmount[1].toString()));
									if(!ObjectFunctions.isNullOrEmpty(paidAmount[4]))
										setQueryString(paidAmount[4].toString()); //this is stored to discount reason desc
									if(!ObjectFunctions.isNullOrEmpty(paidAmount[5])){
										if(Double.valueOf(paidAmount[5].toString()) !=0)
											setDescription(paidAmount[5].toString());
									}
									// if user use desk top payment we have to show desktop invoice number
									if(!ObjectFunctions.isNullOrEmpty(paidAmount[6])){
										setAnyId(paidAmount[6].toString());
									}
								}
								if(!ObjectFunctions.isNullOrEmpty(academicYear.getReceiptGenerationType()) && academicYear.getReceiptGenerationType().equalsIgnoreCase("A"))
									ajaxGenerateFeeReceiptPDFReport(viewStudentClassDetails,getStudentPaymentList(),getTotalAmount(),studentPayment,getTempString(),getCustomerByCustId(),academicYear,student,null);
								else
									ajaxGenerateFeeReceiptPDFReport(viewStudentClassDetails,getStudentPaymentList(),getTotalAmount(),studentPayment,getTempString(),getCustomerByCustId(),academicYear,student,null);
							}
						}
						super.addActionMessage("Payment made successfully.");
					}
				}
				academicYear = null;
				student = null;
				//studentId-anyid,ctrDateStr-todayDate,invoiceNumber-tempId,academicYearId-tempId1,feePaymentType,paidAmount-totalAmount,tempString,invoiceString-alertSendType
				setTempId2(studentPayment.getInvoiceNumber());//For printing the receipt
				setAlertSendType(String.valueOf(studentPayment.getInvoiceString()));  // Using alertSendType when u select Fee Generation type Is manual show the receipt Number in pdf format
				setTodayDate(DateFormatter.formatDate(DateFormatter.ddMMMyyyy_PATTERN1, paymentDate));//For printing the receipt
				studentPayment=null;
			}
		} catch (Exception ex) {
			log.error("Entering into 'catch block':" + ex.getMessage());
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		} finally {
			adminGetSchoolFee();
		}
		return SUCCESS;
	}	
	/*@Actions( { @Action(value = "ajaxPayStudentPaymentFee", results = { @Result(location = "searchStudentDetails.jsp", name = "success"),
																		@Result(location = "../admin/admission/admissionHome.jsp", name = "admissionStudentInvoice") }) })
	public String ajaxPayStudentPaymentFee() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'editStudentPaymentTypeFee' method");
		}
		try {
			if (!StringFunctions.isNullOrEmpty(getParamValue("invoiceNumber"))) {
				ajaxDeleteInvoicePayment();
			}
			long generateInvoiceNumber = 0;
			double totalTermDescount = 0;
			double paymentAfterDiscount = 0;
			double discountAmount = 0;
			double termDescount = 0;
			double feeAmount = 0;
			double paidAmount = 0;
			double discAmt = 0;
			long feeAcademicYearId =0;
			long lastInvoiceNumber = 0;
			StudentPayment payment = null;
			List<Fee> schoolFeeTermList = null;
			StudentFeePaidDetails feePaidDetails = null;
			StringBuffer queryString=null;
			Date paymentDate = null;
			if (getTempId1() != 0) {
				feeAcademicYearId = getTempId1();
			} else {
				feeAcademicYearId = getUserAcademicYearId();
			}
			// String receiptNumber=getParamValue("receiptNumber");
			//Customer customer = (Customer) adminManager.get(Customer.class, getUserCustId());
			Date createdDate = DateFormatter.parseString( DateFormatter.YYYY_MM_DD_PATTERN, DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, new Date()));
			paymentDate = DateFormatter.parseString(DateFormatter.YYYY_MM_DD_PATTERN, DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN,getStudentPayment().getPaymentDate()));
			setAcademicYear((AcademicYear) adminManager.get(AcademicYear.class,feeAcademicYearId));
 			if (!ObjectFunctions.isNullOrEmpty(getStudentNumber())) {
				setStudent(adminManager.getStudentByCustIdAndStudentIdAndStatus(Long.valueOf(getStudentNumber()),getUserCustId(), Constants.YES_STRING,feeAcademicYearId));
				// Here we are checking invoice number for the payment was new  payment are modification.If modification the invoice number  we are not allow the generate the invoice number. 
				String ipAddress = InetAddress.getLocalHost().getHostAddress();
				if (StringFunctions.isNullOrEmpty(getParamValue("invoiceNumber"))) {
					payment = new StudentPayment();
					payment.setCreatedById(getUser().getId());
					payment.setCreatedDate(new Date());
					payment.setLastUpdatedDate(new Date());
					payment.setLastUpdatedById(getUser().getId());
					if (getStudentPayment().getInvoiceNumber() != 0) {
						generateInvoiceNumber = getStudentPayment().getInvoiceNumber();
					} else {
						Customer customer = getCustomerByCustId();
						if(customer.isAcademicWiseFeeReceipt())
							lastInvoiceNumber = adminManager.getInvoiceNumberByCustId("studentPayment",getUserCustId(),getAcademicYear().getId());
						else
							lastInvoiceNumber = adminManager.getInvoiceNumberByCustId("studentPayment",getUserCustId(),0);			
						if (lastInvoiceNumber == 0) {
							if (customer.getStartInvoiceNumber() != 0) {
								lastInvoiceNumber = customer.getStartInvoiceNumber();
							} else {
								generateInvoiceNumber = 1;
							}
							generateInvoiceNumber = lastInvoiceNumber + 1;
						} else if (lastInvoiceNumber != 0) {
							generateInvoiceNumber = lastInvoiceNumber + 1;
						}
					}
				} else {
					generateInvoiceNumber = Long.valueOf(getParamValue("invoiceNumber"));
					payment = (StudentPayment) adminManager.get(StudentPayment.class, " custId=" + getUserCustId()+ " and invoiceNumber="+ generateInvoiceNumber+ " and academicYearId="+ feeAcademicYearId);
					payment.setLastUpdatedDate(new Date());
					payment.setLastUpdatedById(getUser().getId());

					InvoiceLogs invoiceLogs = (InvoiceLogs) adminManager.get(InvoiceLogs.class, "invoiceNumber="+ generateInvoiceNumber + " and custId="+ getUserCustId() + " and academicYearId="+ feeAcademicYearId);
					if (!ObjectFunctions.isNullOrEmpty(invoiceLogs)) {
						invoiceLogs.setLastModifyAmount(Double.valueOf(getParamValue("totalAmount")));
						adminManager.save(invoiceLogs);
					}
				}
				if (!StringFunctions.isNullOrEmpty(ipAddress))
					payment.setIpAddress(ipAddress);
				
				if (!ObjectFunctions.isNullOrEmpty(getStudent())) {
					if (!ObjectFunctions.isNullOrEmpty(getAnyId())) {

						String termTotalAmountStr = getParamValue("paymentAmount");

						double termTotalAmount = Double.valueOf(termTotalAmountStr);
						if (getStudentPayment().getDiscountAmount()!=0) {
							totalTermDescount = getStudentPayment().getDiscountAmount();
						}
						if (getStudentPayment().getPaidAmount()!=0) {
							paymentAfterDiscount = getStudentPayment().getPaidAmount();
						}
						if (totalTermDescount == 0 && (termTotalAmount > paymentAfterDiscount)) {
							termTotalAmount = paymentAfterDiscount;
						} else if (termTotalAmount > paymentAfterDiscount && totalTermDescount > 0) {
							termTotalAmount = paymentAfterDiscount;// -termDescount
						}
						queryString=new StringBuffer();
						queryString.append(" schoolTermId in " + getAnyId() + " and (classId=" + getStudent().getClassNameClassId().getId());
						if(getCurrentAcademicYear().isTransportFeeByBoardingPoint()){
							if(!ObjectFunctions.isNullOrEmpty(getStudent()) && !ObjectFunctions.isNullOrEmpty(getStudent().getRouteBoardingPoints()) && getStudent().getRouteBoardingPoints().getId()!=0){
								queryString.append(" or routeBoardingPointId ="+getStudent().getRouteBoardingPoints().getId());
							}
						}
						queryString.append(") and categoryId=" + getStudent().getCategoryId()+" order by schoolTermId");
						schoolFeeTermList = adminManager.getAll(Fee.class, queryString.toString());
						BankMaster bankMaster = (BankMaster) adminManager.get(BankMaster.class, getStudentPayment().getBankMaster().getId());
						if (!ObjectFunctions.isNullOrEmpty(schoolFeeTermList)) {
							payment.setStudent(getStudent());
							payment.setAcademicYear(getAcademicYear());
							payment.setCustId(getUserCustId());
							payment.setPaymentType(getStudentPayment().getPaymentType());
							payment.setInvoiceNumber(generateInvoiceNumber);
							payment.setBankMaster(bankMaster);
							payment.setPaidAmount(getStudentPayment().getPaidAmount());
							payment.setDiscountAmount(getStudentPayment().getDiscountAmount());
							payment.setPaymentDate(paymentDate);

							if (!("Enter Bank Branch Name").equalsIgnoreCase(getStudentPayment().getBranchName())) {
								payment.setBranchName(getStudentPayment().getBranchName());
							}
							if (!("DD Number").equalsIgnoreCase(getStudentPayment().getDdNumber())) {
								payment.setDdNumber(getStudentPayment().getDdNumber());
								payment.setDdDrawnDate(createdDate);
							} else {
								if (!getStudentPayment().getChequeNumber().equalsIgnoreCase("Number")) {
									payment.setChequeNumber(getStudentPayment().getChequeNumber());
									payment.setChequeIssuedDate(createdDate);
								}
							}
							Set paymentSet = new HashSet();
							int count = 0;
							List<SchoolTerms> schoolTermIdList = adminManager.getAll(SchoolTerms.class, " id in "+ getAnyId());
							if (!ObjectFunctions.isNullOrEmpty(schoolFeeTermList)) {
								for (Fee feeDetails : schoolFeeTermList) {
									StudentFeePaidDetails feeParticularPaidDetails=(StudentFeePaidDetails)adminManager.get(StudentFeePaidDetails.class, " feeId="+feeDetails.getId()+"  and studentId="+getStudent().getId()+" and paymentStatus='"+Constants.PAYMENT_STATUS+"'");
									if(ObjectFunctions.isNullOrEmpty(feeParticularPaidDetails)){
										if (totalTermDescount != 0) {
											if (feeDetails.getSchoolTerms().getId() != getTempId()) {
												if (!ObjectFunctions.isNullOrEmpty(schoolTermIdList)) {
													DecimalFormat twoDForm = new DecimalFormat("#");
													count++;
													if (count == schoolTermIdList.size()) {
														termDescount = totalTermDescount- discAmt;
													} else {
														termDescount = Long.valueOf(twoDForm.format(totalTermDescount/ schoolTermIdList.size()));
														discAmt += termDescount;
													}
												}
												
											}
											if (discountAmount != 0) {
												termDescount = (discountAmount + termDescount);
												discountAmount = 0;
											}
										}
										feePaidDetails = new StudentFeePaidDetails();
										feePaidDetails.setCreatedById(getUser().getId());
										feePaidDetails.setLastUpdatedDate(new Date());
										feePaidDetails.setLastUpdatedById(getUser().getId());
										feePaidDetails.setDeleteStatus(true);
										feePaidDetails.setCustId(getUserCustId());
										feePaidDetails.setFee(feeDetails);
										feePaidDetails.setStudentId(getStudent().getId());

										Object[] partialPaidAmount = adminManager.get("select sum(paymentAmount),sum(discountAmount) from studentFeePaidDetails where studentId="+ getStudent().getId()+ " and feeId="+ feeDetails.getId()+ " and paymentStatus='"+ Constants.NO_STRING + "'");
										if (!ObjectFunctions.isNullOrEmpty(partialPaidAmount) && !ObjectFunctions.isNullOrEmpty(partialPaidAmount[0]) && Double.valueOf(partialPaidAmount[0].toString()) != 0) {

											paidAmount += Double.valueOf(partialPaidAmount[0].toString());
											discountAmount += Double.valueOf(partialPaidAmount[1].toString());
											double remaingAmount = feeDetails.getFeeAmount()- paidAmount;
											if (termTotalAmount > remaingAmount) {
												if (feeDetails.getFeeAmount() == (remaingAmount+ paidAmount + discountAmount)) {
													saveStudentFeePendingDetails(feeDetails.getId(),getStudent());
												}
											} else {
												if (feeDetails.getFeeAmount() == (termTotalAmount+ paidAmount + termDescount)) {
													saveStudentFeePendingDetails(feeDetails.getId(),getStudent());
												}
											}
											feeAmount = feeDetails.getFeeAmount()- (paidAmount + discountAmount);
											discountAmount = 0;
										} else {
											feeAmount = feeDetails.getFeeAmount();
										}
										if (termTotalAmount != 0) {
											if ((feeAmount > termTotalAmount)) {
												if (feeAmount > termDescount) {
													if (feeAmount < termDescount) {
														feePaidDetails.setPaymentAmount(termDescount - feeAmount);
														termTotalAmount = feeAmount - (termDescount - termTotalAmount);
													} else if ((termTotalAmount + termDescount) >= feeAmount) {//
														if (feeAmount == (feeAmount - termDescount) + termDescount) {
															feePaidDetails.setPaymentAmount(feeAmount - termDescount);
															feePaidDetails.setDiscountAmount(termDescount);
															feePaidDetails.setPaymentStatus(Constants.PAYMENT_STATUS);
															termTotalAmount = feeAmount - (feeAmount - termDescount);
															discountAmount = 0;
														} else {
															feePaidDetails.setPaymentAmount(termTotalAmount - termDescount);
															payment.setDiscountAmount(termDescount);
															termTotalAmount = 0;
															if (!ObjectFunctions.isNullOrEmpty(feePaidDetails)) {
																// payment.addStudentFeeDetails(feePaidDetails);
																paymentSet.add(feePaidDetails);
															}
															break;
														}
														termDescount = 0;
													} else if (feeDetails.getFeeAmount() == (termTotalAmount - termDescount) + termDescount) {
														feePaidDetails.setPaymentAmount(termTotalAmount - termDescount);
														termTotalAmount = 0;
													} else {
														feePaidDetails.setPaymentStatus(Constants.NO_STRING);
														feePaidDetails.setPaymentAmount(termTotalAmount - termDescount);
														feePaidDetails.setDiscountAmount(termDescount);
														termTotalAmount = 0;
														if (!ObjectFunctions.isNullOrEmpty(feePaidDetails)) {
															// payment.addStudentFeeDetails(feePaidDetails);
															paymentSet.add(feePaidDetails);
														}
														break;
													}
												} else {
													// payment.setPaidAmount(0);
													feePaidDetails.setPaymentAmount(0);
													if (termDescount > feeAmount) {
														feePaidDetails.setDiscountAmount(feeAmount);// payment.getDiscountAmount()+
														termDescount = (termDescount - feeAmount);
													} else {
														feePaidDetails.setDiscountAmount((feeAmount - termDescount));// payment.getDiscountAmount()+
														termDescount = (feeAmount - termDescount);
													}
													feePaidDetails.setPaymentStatus(Constants.PAYMENT_STATUS);
												}
												if (!ObjectFunctions.isNullOrEmpty(feePaidDetails)) {
													// payment.addStudentFeeDetails(feePaidDetails);
													paymentSet.add(feePaidDetails);
												}
												discountAmount = termDescount;
											} else if (termTotalAmount >= feeAmount) {
												if (feeAmount > termDescount) {
													termTotalAmount = termTotalAmount - (feeAmount - termDescount);
													if (feeDetails.getFeeAmount() == (paidAmount + (feeAmount - termDescount) + termDescount)) {
														feePaidDetails.setPaymentStatus(Constants.PAYMENT_STATUS);
													} else {
														feePaidDetails.setPaymentStatus(Constants.NO_STRING);
													}
													feePaidDetails.setPaymentAmount(feeAmount - termDescount);
													feePaidDetails.setDiscountAmount(termDescount);
													termDescount = 0;
													discountAmount = 0;
													paidAmount = 0;
												} else {
													if (termDescount > feeAmount) {
														discountAmount = (termDescount - feeAmount);
														feePaidDetails.setDiscountAmount(feeAmount);
														
														termDescount=0;
													} else {
														feePaidDetails.setDiscountAmount(termDescount);
														termDescount = (feeAmount - termDescount);
														termDescount = 0;
													}
													feePaidDetails.setPaymentAmount(0);
													feePaidDetails.setPaymentStatus(Constants.PAYMENT_STATUS);
												}
												if (!ObjectFunctions.isNullOrEmpty(feePaidDetails)) {
													// payment.addStudentFeeDetails(feePaidDetails);
													paymentSet.add(feePaidDetails);
												}
											}
										}
										setTempId(feeDetails.getSchoolTerms().getId());
									}
									feeParticularPaidDetails=null;
									SchoolFeeSetting feeSetting=(SchoolFeeSetting)adminManager.get(SchoolFeeSetting.class, feeDetails.getFeeType().getFeeSettingId());
									if(!ObjectFunctions.isNullOrEmpty(feeSetting)){
										if(Constants.TRANSPORT_FEES.equalsIgnoreCase(feeSetting.getSettingName())){
											ajaxCreateStudentMonthlyTransportFeeDetails(feeDetails);
										}
									}
								}
							}
						payment.setStudentFeePaidDetails(paymentSet);
							adminManager.save(payment);
							super.addActionMessage("Payment made successfully.");
						}
					}
				}
			}
			setTempId(generateInvoiceNumber);
			setTodayDate(DateFormatter.formatDate(DateFormatter.ddMMMyyyy_PATTERN1, paymentDate));
			setAnyId(String.valueOf(getStudent().getId()));
			if (StringFunctions.isNotNullOrEmpty(getDescription())) {
				if (getDescription().equalsIgnoreCase("viewAdmittedStudents")) {
					getAdmissionsOnlineApplicationDetails();
					return "admissionStudentInvoice";
				}
			}
		} catch (Exception ex) {
			log.error("Entering into 'catch block':" + ex.getMessage());
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		} finally {
			adminGetSchoolFee();
		}
		return SUCCESS;
	}*/
	@Actions( { @Action(value = "ajaxDeleteInvoicePayment", results = { @Result(location = "deleteInvoice/ajaxViewModifyInvoiceMembers.jsp", name = "success") }) })
	public String ajaxDeleteInvoicePayment() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDeleteInvoicePayment' method");
		}
		try {
			if (!StringFunctions.isNullOrEmpty(getParamValue("invoiceNumber"))) {
				Student student = (Student) adminManager.get(Student.class,Long.valueOf(getStudentNumber()));
				if (!ObjectFunctions.isNullOrEmpty(student)) {
					StudentPayment studentPayment = (StudentPayment) adminManager.get(StudentPayment.class,"invoiceNumber="+ Long.valueOf(getParamValue("invoiceNumber"))+ " and custId=" + getUserCustId()+ " and academicYearId="+ getUserAcademicYearId()+ " and studentId="+ student.getId());
					if (!ObjectFunctions.isNullOrEmpty(studentPayment)) {
						InvoiceLogs invoiceLogs = null;
						if ("Modify".equalsIgnoreCase(anyTitle)) {
							adminManager.remove("studentFeePaidDetails","studentPaymentId="+ studentPayment.getId());
						} else {
							for (StudentFeePaidDetails studentFeePaidDetails : studentPayment.getStudentFeePaidDetails()) {
								studentFeePaidDetails.setDeleteStatus(Constants.YES_STRING);
								adminManager.save(studentFeePaidDetails);
							}
							studentPayment.setDeleteStatus(Constants.YES_STRING);
							adminManager.save(studentPayment);
						}
						invoiceLogs = new InvoiceLogs();
						invoiceLogs.setInvoiceNumber(Long.valueOf(getParamValue("invoiceNumber")));
						invoiceLogs.setAcademicYear(getCurrentAcademicYear());
						invoiceLogs.setCustId(getUserCustId());
						invoiceLogs.setStudent(student);
						invoiceLogs.setCreatedById(getUser().getId());
						invoiceLogs.setDescription(getDescription());
						invoiceLogs.setPaymentAmount(studentPayment.getPaidAmount());
						invoiceLogs.setLastModifyAmount(studentPayment.getPaidAmount());
						invoiceLogs.setDiscountAmount(studentPayment.getDiscountAmount());
						invoiceLogs.setRoleDescription(getUser().getUserRoleDescription());
						invoiceLogs.setPaymentDate(new Date());
						invoiceLogs.setModuleType(Constants.SCHOOL_MODULE);
						if ("Modify".equalsIgnoreCase(anyTitle)) {
							invoiceLogs.setStatus(Constants.MODIFY_STATUS);
						} else {
							invoiceLogs.setStatus(Constants.DELETE_STATUS);
						}
						adminManager.save(invoiceLogs);
						if ("".equalsIgnoreCase(anyTitle)) {
							super.addActionMessage("Payment invoice deleted successfully.");
						}
						studentPayment=null;
					}
					student=null;
				}
				ajaxViewInvoiceModifyers();
			}
		} catch (Exception ex) {
			log.error("Entering into 'catch block':" + ex.getMessage());
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	@Actions( { @Action(value = "ajaxViewInvoiceModifyers", results = { @Result(location = "deleteInvoice/ajaxViewModifyInvoiceMembers.jsp", name = "success") }) })
	public String ajaxViewInvoiceModifyers() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxViewInvoiceModifyers' method");
		}
		try {
			String todayDate = DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, new Date());
			setTodayDate(DateFormatter.formatDate(DateFormatter.ddMMMyyyy_PATTERN1, new Date()));
			setStudentPaymentList(adminManager.getAll("select invoiceNumber,firstName,lastName,rollNumber,paidAmount,paymentDate,studentId,lastUpdatedDate,feeSettingId from vw_studentFeePaymentDetails where paymentDate like '%"+ todayDate+ "%'  and deleteStatus='"+ Constants.NO_STRING+ "' and custId="+ getUserCustId() + " and academicYearId="+getUserAcademicYearId()+" and description is null group by invoiceNumber"));
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	@Actions( { @Action(value = "ajaxSaveModifyInvoice", results = { @Result(location = "deleteInvoice/ajaxModifyInvoicePayment.jsp", name = "success"),
																	 @Result(location = "deleteInvoice/ajaxViewModifyInvoiceMembers.jsp", name = "passwordNotFound") }) })
	public String ajaxSaveModifyInvoice() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxSaveModifyInvoice' method");
		}
		try {
			setTodayDate(DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, new Date()));
			Customer customer = (Customer) adminManager.get(Customer.class,"modifyInvoicePassword='" + getCustomer().getModifyInvoicePassword() + "' ");
			if (ObjectFunctions.isNullOrEmpty(customer)) {
				super.addActionError("Modify password incorrect.");
				ajaxViewInvoiceModifyers();
				return "passwordNotFound";
			} else {
				setStudent(adminManager.getStudentByCustIdAndStudentIdAndStatus(Long.valueOf(getParamValue("studentId")),getUserCustId(), Constants.YES_STRING,getUserAcademicYearId()));
				setClassFeeList(adminManager.getClassFeeTermsByStudentId("ViewStudentClassFeePaymentDetails", getStudent().getId(), getUserCustId(), getUserAcademicYearId(), getStudent().getClassNameClassId().getId(), getStudent().getCategoryId(), getTempId()));
				setSchoolFeeList(adminManager.getAll(ViewStudentFeePaymentDetails.class, " studentId="+ getStudent().getId() + " and custId="+ getUserCustId() + " and deleteStatus='"+ Constants.NO_STRING+ "' and academicYearId=" + getUserAcademicYearId()+ " group by invoiceNumber"));
				if (!ObjectFunctions.isNullOrEmpty(getStudent())) {
					setObjectList((List<BankMaster>) (SMSLookUpDataCache.lookUpDataMap.get(Constants.BANK_LIST)));
					setAnyTitle(getParamValue("type")); // Here type is modify or delete status.
					setSelectedId(getParamValue("invoiceNumber"));
					setCurrentMonth(getParamValue("lastUpdatedDateStr"));
					Object[] invoicePaymentDetails = adminManager.get("select sum(paymentAmount),paidAmount,sum(discountAmount),paymentType,chequeNumber,branchName,ddNumber,bankId from vw_studentFeePaymentDetails where invoiceNumber="+ getSelectedId()+ " and custId=" + getUserCustId());
					if (!ObjectFunctions.isNullOrEmpty(invoicePaymentDetails)) {
						setTotalAmount(Double.valueOf(invoicePaymentDetails[0].toString()));
						setPaymentAmount(Double.valueOf(invoicePaymentDetails[1].toString())+ Double.valueOf(invoicePaymentDetails[2].toString()));
						setDiscountAmount(Double.valueOf(invoicePaymentDetails[2].toString()));
						setPaymentType(invoicePaymentDetails[3].toString());
						if ("D".equalsIgnoreCase(getPaymentType())) {
							setFeeDDNumber(invoicePaymentDetails[6].toString());
							setBankName(invoicePaymentDetails[5].toString());
							setBankId(Long.valueOf(invoicePaymentDetails[7].toString()));
						} else if ("CH".equalsIgnoreCase(getPaymentType())) {
							setChequeNumber(invoicePaymentDetails[4].toString());
							setBankName(invoicePaymentDetails[5].toString());
							setBankId(Long.valueOf(invoicePaymentDetails[7].toString()));
						} else {
							setPaymentType(invoicePaymentDetails[3].toString());
						}
					}
				}
			}
			customer=null;
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}

	@Actions( { @Action(value = "ajaxSaveDeleteInvoice", results = { @Result(location = "deleteInvoice/ajaxDeleteInvoicePayment.jsp", name = "success"),
																	 @Result(location = "deleteInvoice/ajaxViewModifyInvoiceMembers.jsp", name = "passwordNotFound") }) })
	public String ajaxSaveDeleteInvoice() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxSaveDeleteInvoice' method");
		}
		try {
			Customer customer = (Customer) adminManager.get(Customer.class,"deleteInvoicePassword='"+ getCustomer().getDeleteInvoicePassword() + "' ");
			if (ObjectFunctions.isNullOrEmpty(customer)) {
				super.addActionError("Delete password incorrect.");
				ajaxViewInvoiceModifyers();
				return "passwordNotFound";
			} else {
				setStudent(adminManager.getStudentByCustIdAndStudentIdAndStatus(Long.valueOf(getParamValue("studentId")),getUserCustId(), Constants.YES_STRING,getUserAcademicYearId()));
				if (!ObjectFunctions.isNullOrEmpty(getStudent())) {
					setClassFeeList(adminManager.getAll(ViewStudentFeePaymentDetails.class,"studentId="+ getStudent().getId()+ " and custId="+ getUserCustId()+ " and academicYearId="+ getUserAcademicYearId()+ " and invoiceNumber="+ Long.valueOf(getParamValue("invoiceNumber"))+ " order by schoolTermId"));
					setStudentPayment((StudentPayment) adminManager.get(StudentPayment.class," custId="+ getUserCustId()+ " and studentId="+ getStudent().getId()+ " and invoiceNumber="+ Long.valueOf(getParamValue("invoiceNumber"))+ " and academicYearId="+ getUserAcademicYearId()));
				}
			}
			customer=null;
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	@Actions( { @Action(value = "ajaxDoModifyInvoice", results = { @Result(location = "deleteInvoice/ajaxDoModifyInvoice.jsp", name = "success") }) })
	public String ajaxDoModifyInvoice() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDoModifyInvoice' method");
		}
		try {
			setAnyId(getParamValue("studentId"));
			setSelectedId(getParamValue("invoiceNumber"));
			setTempId(Long.valueOf(getParamValue("feeSettingId")));
			Date date = DateFormatter.parseString(DateFormatter.YYYYMMDDHHMMSSN_3_PATTERN,getParamValue("lastUpdatedDate"));
			SimpleDateFormat lastUpdatedStr = new SimpleDateFormat(DateFormatter.YYYY_MM_DD_PATTERN);
			setSelectedDate(lastUpdatedStr.format(date));
			log.debug("last date" + getSelectedDate());
			if (!StringFunctions.isNullOrEmpty(getParamValue("type"))) {
				setTempString(getParamValue("type"));
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	@Actions( { @Action(value = "ajaxDoDeleteInvoice", results = { @Result(location = "deleteInvoice/ajaxDeleteInvoicePayment.jsp", name = "success") }) })
	public String ajaxDoDeleteInvoice() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDoDeleteInvoice' method");
		}
		try {
			
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	
	@Action(value = "ajaxSearchStudentByCriteria", results = { @Result(location = "ajaxViewStudentList.jsp", name = "success") })
	public String ajaxSearchStudentByCriteria() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'searchStudentByCriteria' method");
		}
		try {
			getSmsCount();
			prepareStudentsDetailsList();
			/*if(getUserAcademicYearId()>0){
				if(!ObjectFunctions.isNullOrEmpty(getClassId()))
				{
					prepareStudentsFeeList(getStudentsList(),getUserAcademicYearId(),Long.valueOf(getClassId()));
				}
				else
				{
					prepareStudentsFeeListBysearch(getStudentsList(),getUserAcademicYearId());
				}
			}*/
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	

	@Actions({
			@Action(value = "ajaxFeeSettingInvoice", results = { @Result(location = "ajaxFeeSettingStudentInvoiceList.jsp", name = "success"),
																 @Result(location = "searchStudentDetails.jsp", name = "configureError") }),
			@Action(value = "ajaxManageAdmissionStudentFee", results = { @Result(location = "admission/manageAdmissionStudentInvoicePayment.jsp", name = "success"),
																		 @Result(location = "admission/ajaxPendingAdmissionDetails.jsp", name = "configureError") })
	})
	public String ajaxFeeSettingStudentInvoice() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxFeeSettingInvoice' method");
		}
		try {
			ajaxFeeSettingInvoice();
			long classId=0;
			long classSectionId=0;
			double committedFee = 0;
			long academicYearId=0;
			long categoryId = 0;
			if(!ObjectFunctions.isNullOrEmpty(getStudent())){
				classId = getStudent().getClassNameId();
				classSectionId = getStudent().getClassSectionId();
				committedFee = getStudent().getCommittedFee();
				categoryId = getStudent().getCategoryId();
				academicYearId = getStudent().getAcademicYearId();
				}else if (!ObjectFunctions.isNullOrEmpty(getOnlineApplicationDetails())) {
				classId = getOnlineApplicationDetails().getClassId().getId();
				StudyClass studyClass = (StudyClass)adminManager.get(StudyClass.class, "classNameClassId="+classId);
				if(!ObjectFunctions.isNullOrEmpty(studyClass))
					classSectionId = studyClass.getId();
				committedFee = getOnlineApplicationDetails().getCommittedFee();
				categoryId = getOnlineApplicationDetails().getCategoryId();
				academicYearId = getOnlineApplicationDetails().getAcademicYear().getId();
			}
			addCommittedFeeActionMessages(studentManager.validateCommittedFeeConstrains(getUserCustId(),academicYearId,committedFee,classId,classSectionId,categoryId,getTempString()));
			if(!ObjectFunctions.isNullOrEmpty(getCollectionAndFeeDuesList())){
				if(!ObjectFunctions.isNullOrEmpty(getStudent())){
					adminGetSchoolFee();
				}else{
					getAdmissionsOnlineApplicationDetails();
				}
				return "configureError";
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
		}
		return SUCCESS;
	}
	@Actions( {
			@Action(value = "ajaxManageAdmissionStudentFee", results = { @Result(location = "admission/manageAdmissionStudentInvoicePayment.jsp", name = "success") }),
			@Action(value = "ajaxViewFeeSettingInvoice", results = { @Result(location = "ajaxViewFeeSettingStudentInvoiceList.jsp", name = "success") })
	})
	public String ajaxViewFeeSettingInvoice() throws URTUniversalException {
	
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxViewFeeSettingInvoice' method");
		}
		try {
			ajaxFeeSettingInvoice();
			getExcessAmountDetails(getStudent());
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	@Action(value = "ajaxDoFutureAcademicYearStudentFeePayment", results = { @Result(location = "manageFutureAcademicStudentInvoicePayment.jsp", name = "success") })
	public String ajaxDoFutureAcademicYearStudentFeePayment() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDoFutureAcademicYearStudentFeePayment' method");
		}
		try {
			if(StringFunctions.isNotNullOrEmpty(getStudentNumber()) && !"0".equalsIgnoreCase(getStudentNumber())){
				AcademicYear academicYear = getCurrentAcademicYear();
				if(!ObjectFunctions.isNullOrEmpty(academicYear)){
					String[] academicYrs = academicYear.getAcademicYear().split("-");
					if(!ObjectFunctions.isNullOrEmpty(academicYrs)){
						StringBuffer query = new StringBuffer("academicYear='").append(Long.valueOf(academicYrs[0])+1).append("-").append(Long.valueOf(academicYrs[1])+1)
						.append("' and custId=").append(getUserCustId());
						setAcademicYear((AcademicYear)adminManager.get(AcademicYear.class, query.toString()));
						if(!ObjectFunctions.isNullOrEmpty(getAcademicYear())){
							setStudyClassList(adminManager.getAll(StudyClass.class,"custId="+getUserCustId()+" and academicYearId="+getAcademicYear().getId()));
							if(ObjectFunctions.isNotNullOrEmpty(getStudyClassList()))
								Collections.sort(getStudyClassList());
							setStudent((Student)adminManager.get(Student.class,"custId="+getUserCustId()+" and academicYearId="+academicYear.getId()+" and id="+getStudentNumber()));
							if(!ObjectFunctions.isNullOrEmpty(getStudent()) && !ObjectFunctions.isNullOrEmpty(getStudent().getFutureAcademicClassSecId())){
								Object[] obj = adminManager.get("select group_concat(CONVERT(id,CHAR),':',CONVERT(classNameClassId,CHAR)),className from studyClass where id ="+getStudent().getFutureAcademicClassSecId());
								if(!ObjectFunctions.isNullOrEmpty(obj) && !ObjectFunctions.isNullOrEmpty(obj[0]))
									setClassSectionId(obj[0].toString());
							}
							prepareStudentSchoolFeeSettingList(getStudent());
						}
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	
	@Actions( { @Action(value = "ajaxViewStudentFeePaymentDetails", results = { @Result(location = "ajaxViewStudentFeePaymentDetails.jsp", name = "success") }),
			    @Action(value = "ajaxViewFeeDetails", results = {@Result(location = "../admin/academic/tcGeneration/ajaxViewPendingFee.jsp", name = "success") })
	})
	public String ajaxViewStudentFeePaymentDetails() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxViewStudentFeePaymentDetails' method");
		}
		try {
			StringBuffer query = null;
			ViewStudentFeePaymentDetails studentTermFee =null;
			query = new StringBuffer("id=").append(getStudent().getId()).append(" and custId=").append(getUserCustId()).append(" and description is null");
			setStudent((Student)adminManager.get(Student.class,query.toString()));
			if (!ObjectFunctions.isNullOrEmpty(getStudent())) {
				if(StringFunctions.isNullOrEmpty(getTempString())){
					setTempList(adminManager.getAllStudentTermsWiseClassFeePaymentDetails(getStudent().getId(),getStudent().getAcademicYearId(),getAnyId()));
					if(ObjectFunctions.isNotNullOrEmpty(getTempList())){
						studentTermFee = (ViewStudentFeePaymentDetails)getTempList().get(0);
						SchoolCategoryVO categoryVO = new SchoolCategoryVO();
						categoryVO.setCategoryName(studentTermFee.getCategoryName());
						setSchoolCategoryVO(categoryVO);
						for(Object obj : getTempList()){
							studentTermFee = (ViewStudentFeePaymentDetails)obj;
							studentTermFee.setPendingStudentList(adminManager.getAllStudenClassFeePaymentDetails(getStudent().getId(),getStudent().getAcademicYearId(),studentTermFee.getSchoolTermId()));
						}
					}
					//Siva: this below code is to show the transport fee paid and not paid based on student boarding point id
					if(getAnyId().contains("3")){
						setStudentTransportTermsList(adminManager.getAllStudentTermsWiseTransportFeeViewPaymentDetails(getStudent().getId(),getStudent().getAcademicYearId(),3));
						if(ObjectFunctions.isNotNullOrEmpty(getStudentTransportTermsList())){
							for(Object obj : getStudentTransportTermsList()){
								ViewStudentTransportFeePaymentDetails studentTransportTermFee = (ViewStudentTransportFeePaymentDetails)obj;
								studentTransportTermFee.setStudentTransportFeeList(adminManager.getAllStudenTransportFeePaymentDetails(getStudent().getId(),getStudent().getAcademicYearId(),studentTransportTermFee.getSchoolTermId()));
							}
						}
					}
					
				}else{
					StudyClass studyClass = (StudyClass)adminManager.get(StudyClass.class,"id="+getTempString());
					setTempList(adminManager.getAllStudentTermsWiseFutureClassFeePaymentDetails(getStudent().getId(),studyClass.getAcademicYearId(),getAnyId(),studyClass.getClassId(),studyClass.getId()));
					if(ObjectFunctions.isNotNullOrEmpty(getTempList())){
						for(Object obj : getTempList()){
							studentTermFee = (ViewStudentFeePaymentDetails)obj;
							studentTermFee.setPendingStudentList(adminManager.getAllStudentFutureClassFeePaymentDetails(getStudent().getId(),studyClass.getAcademicYearId(),studentTermFee.getSchoolTermId(),studyClass.getClassId(),studyClass.getId()));
						}
					}
				}
				StudentFeeConcession studentFeeConcession =(StudentFeeConcession)adminManager.get(StudentFeeConcession.class, "custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and studentId="+getStudent().getId()+" and concessionAmount!=0");
				if(!ObjectFunctions.isNullOrEmpty(studentFeeConcession)){
					setTempBoolean(true);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	
	@Actions( { @Action(value = "ajaxLoadStudentForInvoice", results = { @Result(location = "ajaxStudentInvoicePayment.jsp", name = "success"),
																		 @Result(location = "searchStudentDetails.jsp", name = "configureError")}) })
	public String findStudentForInvoice() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxLoadStudentForInvoice' method");
		}
		try {
			StringBuffer query = null;
			/*String paymentStatus = getParamValue("paymentStatus");
			setTempString(paymentStatus);*/
			Object[] excessAmt = null;
			double studTransportFee = 0;
			setAcademicYear(getCurrentAcademicYear());
			setTodayDate(DateFormatter.formatDate(DateFormatter.MMDDCCYY_PATTERN, new Date()));
			setObjectList((List<BankMaster>) (SMSLookUpDataCache.lookUpDataMap.get(Constants.BANK_LIST)));
			query = new StringBuffer("id=").append(getStudent().getId()).append(" and custId=").append(getUserCustId())/*.append(" and academicYearId=")
			.append(getAcademicYearId())*/.append(" and description is null");
			setStudent((Student)adminManager.get(Student.class,query.toString()));
			setCustomer(getCustomerByCustId());// For knowing term wise or particular wise payment
			/* 1. The below code for who are have committed fee configured invidual.
			 * 2. We will add payment details for committed for when student do first time payment. 
			 * 3. Because we have to track for which particular we are adding payment details for committed fee. 
			 * 4. This below code should be call one time process. 
			 * 5. Once student pay the first payment we have to add below invoice details to when student pay first payment.
			 * 6. else condition will come when first time student click one pay and he did not make any payment. We should not call again 'entryStudentCommittedPayment' service. It is one time process.
			 * 7. Student will pay option we will check else condition for committed invoice number to update with first invoice details. */
			StudentPayment commitStudInvoiceNumber = null;
				/*if(getStudent().getCommittedFee() !=0){
					Object[] congifuredFee = adminManager.get("select id,IFNULL(SUM(feeAmount),0) as feeAmount from Fee where custId="+getUserCustId()+" and classId="+getStudent().getClassNameId()+" and academicYearId="+getStudent().getAcademicYearId()+" and categoryId="+getStudent().getCategoryId());
					Object[] committedPaidAmount =adminManager.get("select id,IFNULL(sum(paymentAmount),0) as paymentAmount,studentId from vw_studentFeePaymentDetails where studentId="+getStudent().getId()+" and classId="+getStudent().getClassNameId()+" and paymentCommitFeeStatus='Y'");
					double pendingAmount=0;
					if(!ObjectFunctions.isNullOrEmpty(committedPaidAmount)){
						if(Double.valueOf(congifuredFee[1].toString()) > getStudent().getCommittedFee())
							pendingAmount=Double.valueOf(congifuredFee[1].toString())-getStudent().getCommittedFee();
						if(pendingAmount > Double.valueOf(committedPaidAmount[1].toString())){
							String ipAddress = InetAddress.getLocalHost().getHostAddress();
							commitStudInvoiceNumber = adminManager.entryStudentCommittedPayment(getCustomerByCustId(),getStudent(),pendingAmount,getUser().getId(),ipAddress);
						}else{
							StudentFeePaidDetails feePaidDetails = (StudentFeePaidDetails)adminManager.get(StudentFeePaidDetails.class, "custId="+getUserCustId()+" and studentId="+getStudent().getId()+" and committedFeeStatus='"+Constants.NO_STRING+"' ");
							if(ObjectFunctions.isNullOrEmpty(feePaidDetails)){
								StudentFeePaidDetails paidDetails = (StudentFeePaidDetails)adminManager.get(StudentFeePaidDetails.class, "custId="+getUserCustId()+" and studentId="+getStudent().getId()+" and committedFeeStatus='"+Constants.YES_STRING+"' ");
								if(!ObjectFunctions.isNullOrEmpty(paidDetails))
									commitStudInvoiceNumber = (StudentPayment)adminManager.get(StudentPayment.class,"custId="+getUserCustId()+" and studentId="+getStudent().getId()+" and id="+paidDetails.getStudentPaymentId());
								feePaidDetails =null;
							}
						}
					}
				}*/
			if (!ObjectFunctions.isNullOrEmpty(getStudent())/* || getQuizId() > 0*/) {
				
				if("Y".equalsIgnoreCase(getCustomer().getAccountModuleUsing())){
					setTempId1(adminManager.getAll(ViewPartucularToAccount.class, " custId="+getUserCustId()+" and academicYearId="+getStudent().getAcademicYearId()+" and finAccountId=0").size());
				}
				/* Below condition we need to check if the user using challana generation.*/
				if("Y".equalsIgnoreCase(getCustomer().getChalanaGenerationStatus())){
					List<Object[]> challanaList = adminManager.getAll("select challanaNumber,paidAmount from challanaPayment where custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and studentId="+getStudent().getId()+" and deleteStatus='N'");
					if(!ObjectFunctions.isNullOrEmpty(challanaList)){
						int count=1;
						StringBuilder challanaNumbers=new StringBuilder();
						for(Object[] challanaPayment : challanaList){
							challanaNumbers.append(challanaPayment[0].toString());
							if(count != challanaList.size())
								challanaNumbers.append(",");
							count++;
						}
						setTempString(challanaNumbers.toString());
					}
				}
				
				query = new StringBuffer("select SUM(excessAmount),status from excessPayment where accountId=").append(getStudent().getAccount().getId()).append(" and status='N'");
				excessAmt = adminManager.get(query.toString());
				StudentFeeConcession studentFeeConcession =(StudentFeeConcession)adminManager.get(StudentFeeConcession.class, "custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and studentId="+getStudent().getId()+" and concessionAmount!=0");
				if(!ObjectFunctions.isNullOrEmpty(studentFeeConcession)){
					setTempBoolean(true);
				}
				if(!ObjectFunctions.isNullOrEmpty(excessAmt) && !ObjectFunctions.isNullOrEmpty(excessAmt[0])){
					setBalance(excessAmt[0].toString());
				}
				
				long receiptNumber= 0;
				long challanaNumber= 0;
				if(!ObjectFunctions.isNullOrEmpty(commitStudInvoiceNumber)){
					setTempId2(commitStudInvoiceNumber.getInvoiceNumber());//committed student invoice number
					setNumberOfDays((int)commitStudInvoiceNumber.getId());// committed student fee payment id
					setTempId(Long.valueOf(commitStudInvoiceNumber.getInvoiceNumber()));
					commitStudInvoiceNumber=null;
				}else{
					if(getCustomer().isAcademicWiseFeeReceipt())
						receiptNumber = adminManager.getInvoiceNumberByCustId("studentPayment", getUserCustId(),getAcademicYearId()); 
					else
						receiptNumber = adminManager.getInvoiceNumberByCustId("studentPayment", getUserCustId(),0);
					if (receiptNumber == 0 && getCustomer().getStartInvoiceNumber() != 0) {
						setTempId(getCustomer().getStartInvoiceNumber());
					}else{
						setTempId(receiptNumber+1);
					}
				}
				if("Y".equalsIgnoreCase(getCustomer().getChalanaGenerationStatus())){
					challanaNumber = adminManager.getInvoiceNumberByCustId("challanaPayment", getUserCustId(),0);
					if(challanaNumber>0)
						setTempId2(challanaNumber+1);
					else
						setTempId2(1);
				}
				if(StringFunctions.isNotNullOrEmpty(getEmpId()) && "futureYearPayment".equalsIgnoreCase(getWishTitle())){
					setTempList(adminManager.getStudentFutureAcademicTermsWiseNonPaidClassFeePaymentDetails(getStudent().getId(),getAcademicYearId(),getAnyId(),Long.valueOf(getClassId()),Long.valueOf(getEmpId())));
					if(ObjectFunctions.isNotNullOrEmpty(getTempList())){
						for(Object obj : getTempList()){
							ViewStudentFeePaymentDetails studentTermFee = (ViewStudentFeePaymentDetails)obj;
							studentTermFee.setPendingStudentList(adminManager.getStudentFutureClassFeeDetails(getStudent().getId(), getAcademicYearId(), studentTermFee.getSchoolTermId(),studentTermFee.getClassId(), studentTermFee.getClassSectionId()));
						}
					}
					
				}else{
					setTempList(adminManager.getAllStudentTermsWiseNonPaidClassFeePaymentDetails(getStudent().getId(),getStudent().getAcademicYearId(),getAnyId()));
					if(ObjectFunctions.isNotNullOrEmpty(getTempList())){
						for(Object obj : getTempList()){
							ViewStudentFeePaymentDetails studentTermFee = (ViewStudentFeePaymentDetails)obj;
							studentTermFee.setPendingStudentList(adminManager.getStudentClassFeeDetails(getStudent().getId(),getStudent().getAcademicYearId(),studentTermFee.getSchoolTermId()));
						}
					}
					//Siva: this below code is to show the transport fee based on student boarding point id
					if(getAnyId().contains("3")){
						setStudentTransportTermsList(adminManager.getAllStudentTermsWiseTransportFeePaymentDetails(getStudent().getId(),getStudent().getAcademicYearId(),3));
						if(ObjectFunctions.isNotNullOrEmpty(getStudentTransportTermsList())){
							for(Object obj : getStudentTransportTermsList()){
								ViewStudentTransportFeePaymentDetails studentTermFee = (ViewStudentTransportFeePaymentDetails)obj;
								studentTermFee.setStudentTransportFeeList(adminManager.getStudentTransportFeeDetails(getStudent().getId(),getStudent().getAcademicYearId(),studentTermFee.getSchoolTermId()));
							}
						}
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}

	@Actions({ @Action(value = "ajaxLoadAdmissionStudentForInvoice", results = { @Result(location = "ajaxStudentInvoicePayment.jsp", name = "success")
	/*  @Result(location = "admission/ajaxStudentInvoicePayment.jsp", name = "success") */}) })
	public String ajaxLoadAdmissionStudentForInvoice()
			throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxLoadAdmissionStudentForInvoice' method");
		}
		try {
			setAcademicYear(getCurrentAcademicYear());
			setTodayDate(DateFormatter.formatDate(DateFormatter.MMDDCCYY_PATTERN, new Date()));
			setCustomer(getCustomerByCustId());
			setObjectList((List<BankMaster>) (SMSLookUpDataCache.lookUpDataMap.get(Constants.BANK_LIST)));
			setOnlineApplicationDetails((OnlineApplicationDetails) adminManager.get(OnlineApplicationDetails.class,getOnlineApplicationDetails().getId()));
			setAdmissionSettings((AdmissionSettings) adminManager.get(AdmissionSettings.class, "custId=" + getUserCustId()+ " and status='"+Constants.YES_STRING+"'"));
			// Need to remove following line once we updated categoryid for applications having no category.
			SchoolCategory schoolCategory = (SchoolCategory) adminManager.get(SchoolCategory.class, " custId=" + getUserCustId()+ " and categoryName='"+ Constants.GENERAL_CATEGORY + "'");
			if (!ObjectFunctions.isNullOrEmpty(getOnlineApplicationDetails())) 
			{
				StudyClass studyClass = (StudyClass) adminManager.get(StudyClass.class, "classNameClassId="+ getOnlineApplicationDetails().getClassId().getId());
				long receiptNumber = 0;
				if (getCustomer().isAcademicWiseFeeReceipt())
					receiptNumber = adminManager.getInvoiceNumberByCustId("studentPayment", getUserCustId(),getOnlineApplicationDetails().getAcademicYear().getId());
				else
					receiptNumber = adminManager.getInvoiceNumberByCustId("studentPayment", getUserCustId(), 0);
				if (receiptNumber == 0 && getCustomer().getStartInvoiceNumber() != 0) {
					setTempId(getCustomer().getStartInvoiceNumber());
				} else {
					setTempId(receiptNumber + 1);
				}
				StringBuffer feeIds = null;
				long partialFeeId = 0;
				double partialFeeAmount = 0; 
				if (getOnlineApplicationDetails().getCommittedFee() != 0) {
					Object[] congifuredFee = adminManager.get("select id,IFNULL(SUM(feeAmount),0) as feeAmount from Fee where custId="+ getUserCustId()+ " and classId="+ studyClass.getClassNameClassId().getId()+ " and academicYearId="+ getOnlineApplicationDetails().getAcademicYear().getId()+ " and categoryId="+ getOnlineApplicationDetails().getCategoryId());
					double allowedCommittedFee = (Double.valueOf(congifuredFee[1].toString()) - getOnlineApplicationDetails().getCommittedFee());
					if(allowedCommittedFee > 0){
						double paidAmount = 0;
						boolean processContinue = true;
						feeIds = new StringBuffer().append("(");
						List<ViewClassFeeDetails> commitedClassFee = adminManager.getAll(ViewClassFeeDetails.class,"custId="+ getUserCustId()+ " and classSectionId="+ studyClass.getId()+ " and categoryId="+ getOnlineApplicationDetails().getCategoryId()+ " and committedFeeStatus='Y' order by priorityPosition,dueDate");
						if (!ObjectFunctions.isNullOrEmpty(commitedClassFee)) {
							for (ViewClassFeeDetails feeDetails : commitedClassFee) {
								if (processContinue == false)
									continue;
								if (feeDetails.getFeeAmount() >= allowedCommittedFee && allowedCommittedFee != 0) {
									paidAmount = allowedCommittedFee;
									if(feeDetails.getFeeAmount() == paidAmount)
										feeIds.append(feeDetails.getFeeId() + ",");
									else{
										partialFeeId = feeDetails.getFeeId();
										partialFeeAmount = paidAmount;
									}
									processContinue = false;
									allowedCommittedFee = 0;
								} else {
									paidAmount = feeDetails.getFeeAmount();
									feeIds.append(feeDetails.getFeeId() + ",");
								}
								allowedCommittedFee = (allowedCommittedFee - feeDetails.getFeeAmount());
								feeDetails = null;
							}
							feeIds.append("0)");
						}
					}else
						feeIds = new StringBuffer().append("(0)");
					
				} else
					feeIds = new StringBuffer().append("(0)");
				List<ViewOnlineApplicationStudentClassFees> termParticularFeeList = null;
				if (getOnlineApplicationDetails().getCategoryId() == 0)
					setTempList(adminManager.getTermsWiseNonPaidClassFeePaymentDetailsForAdmissions(getOnlineApplicationDetails().getClassId().getId(),getOnlineApplicationDetails().getAcademicYear().getId(),getAnyId(),schoolCategory.getId(),feeIds.toString(),partialFeeId,partialFeeAmount));
				else
					setTempList(adminManager.getTermsWiseNonPaidClassFeePaymentDetailsForAdmissions(getOnlineApplicationDetails().getClassId().getId(),getOnlineApplicationDetails().getAcademicYear().getId(),getAnyId(),getOnlineApplicationDetails().getCategoryId(),feeIds.toString(),partialFeeId,partialFeeAmount));
				if (ObjectFunctions.isNotNullOrEmpty(getTempList())) {
					for (Iterator<ViewOnlineApplicationStudentClassFees> onlineApplicationStudentClassFees = getTempList().iterator(); onlineApplicationStudentClassFees.hasNext();) {
						ViewOnlineApplicationStudentClassFees studentTermFee = onlineApplicationStudentClassFees.next();
						if (getOnlineApplicationDetails().getCategoryId() == 0)
							termParticularFeeList = adminManager.getNonPaidClassFeePaymentDetailsForAdmissions(getOnlineApplicationDetails().getClassId().getId(),getOnlineApplicationDetails().getAcademicYear().getId(),studentTermFee.getSchoolTermId(),schoolCategory.getId(),feeIds.toString(),partialFeeId,partialFeeAmount);
						else
							termParticularFeeList = adminManager.getNonPaidClassFeePaymentDetailsForAdmissions(getOnlineApplicationDetails().getClassId().getId(),getOnlineApplicationDetails().getAcademicYear().getId(),studentTermFee.getSchoolTermId(),getOnlineApplicationDetails().getCategoryId(),feeIds.toString(),partialFeeId,partialFeeAmount);
						if (!ObjectFunctions.isNullOrEmpty(termParticularFeeList))
							studentTermFee.setPendingStudentList(termParticularFeeList);
						else
							onlineApplicationStudentClassFees.remove();
					}
				}
				if(!ObjectFunctions.isNullOrEmpty(studyClass) && !ObjectFunctions.isNullOrEmpty(studyClass.getClassNameClassId()))
				setAdmissionNumber(admissionNumberGenerationBySetting(getOnlineApplicationDetails(), studyClass.getClassNameClassId().getId()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	public String targetStringRemoveParticular(String targetValue,long academicYearId){
		//setTempString(targetValue);
		prepareSchoolFeeSettingList();
		setFeeTypeList(adminManager.getAll(FeeType.class, "custId="+ getUserCustId()+ " and academicYearId="+ academicYearId + " and feeSettingId="+ getTempId()));
		return "admissionFeeTypeSettings";
	}
	
	@Actions( { @Action(value = "ajaxStudentDownloadReceipt", results = { @Result(location = "admission/ajaxStudentFeeDetails.jsp", name = "success") }) })
	public String ajaxStudentDownloadReceipt() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxStudentDownloadReceipt' method");
		}
		try {
			AcademicYear academicYear = null;
			/*@Ganesh - Below condition we are checking for when we download past year invoice number. We need to check previous year receipt invoice generation type that why we are checking below way.*/
			if(getUserAcademicYearId() !=getTempId2())
				academicYear = (AcademicYear)accountManager.get(AcademicYear.class, getTempId2());
			else
				academicYear = getCurrentAcademicYear();
			StringBuffer query = new StringBuffer(" studentId="+getAnyId()+" and custId="+getUserCustId()+" and deleteStatus='"+Constants.NO_STRING+"' and academicYearId="+ getTempId2());
			if(!ObjectFunctions.isNullOrEmpty(academicYear.getReceiptGenerationType()) && academicYear.getReceiptGenerationType().equalsIgnoreCase("A"))
				query.append(" and invoiceNumber !=0  group by invoiceNumber");
			else
				query.append(" and invoiceString is not null  group by  invoiceString");
			List<StudentPayment> studentPaymentList = adminManager.getAll(StudentPayment.class, query.toString());
			if(!ObjectFunctions.isNullOrEmpty(studentPaymentList)){
				for( StudentPayment studentPayment : studentPaymentList)
				{
					Object[] excessAmt = adminManager.get("select sum(excessAmount),status from excessPayment where paymentId="+studentPayment.getId());
    				if(!ObjectFunctions.isNullOrEmpty(excessAmt) && !ObjectFunctions.isNullOrEmpty(excessAmt[0])){
    					setTempString2("Y");
    					studentPayment.setExcessAmount(Double.valueOf(excessAmt[0].toString()));
    				}
    				
    				Object[] excesUsedsAmt = adminManager.get("select sum(excessAmount),status from excessPayment where usedPaymentId="+studentPayment.getId());
    				if(!ObjectFunctions.isNullOrEmpty(excesUsedsAmt) && !ObjectFunctions.isNullOrEmpty(excesUsedsAmt[0])){
    					studentPayment.setUsedExcessAmount((Double.valueOf(excesUsedsAmt[0].toString()) - studentPayment.getExcessAmount()));
						//viewStudentFeePaymentDetails.setExcessAmount(0.0);
    				}
					getSchoolFeeList().add(studentPayment);
					studentPayment=null;
				}
				studentPaymentList=null;
			}
			query=null;
			//setTempList(adminManager.getAll(ViewStudentFineFeeDetails.class, "custId="+ getUserCustId() + " and studentId=" + getAnyId()+" group by studentId,invoiceNumber"));
			setTempList(accountManager.getOtherFeePaymentDetails(getUserCustId(),getUserAcademicYearId(),Long.valueOf(getAnyId())));
			/*@Ganesh - Above in one scenario we are getting taking previous year object but here we need to check current academic year object that's why we are getting current academic year when we get previous year object previously. */
			if(getUserAcademicYearId() !=getTempId2())
				academicYear = getCurrentAcademicYear();
			if(!ObjectFunctions.isNullOrEmpty(academicYear)){
				String[] academicYrs = academicYear.getAcademicYear().split("-");
				if(!ObjectFunctions.isNullOrEmpty(academicYrs)){
					query = new StringBuffer("academicYear='").append(Long.valueOf(academicYrs[0])+1).append("-").append(Long.valueOf(academicYrs[1])+1)
					.append("' and custId=").append(getUserCustId());
					setAcademicYear((AcademicYear)adminManager.get(AcademicYear.class, query.toString()));
					if(!ObjectFunctions.isNullOrEmpty(getAcademicYear())){
						setAcdmcYearRange(String.valueOf(getAcademicYear().getId()));
						setObjectList(adminManager.getFutureAcademicYearStudentFeeReceipts(Long.valueOf(getAnyId()), getAcademicYear().getId(),null));
					}
				}
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}finally{
			academicYear=null;
		}
		return SUCCESS;
	}
	@Actions( { @Action(value = "ajaxViewStudentPendingFeeList", results = { @Result(type = "json", name = "success", params = {"includeProperties", "payType" }) })

	})
	public String ajaxViewStudentPendingFeeList() throws URTUniversalException {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxViewStudentPendingFeeList' method");
		}
		try {
			ClassName className = null;
			setTodayDate(DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, new Date()));
			if (!ObjectFunctions.isNullOrEmpty(getUserCustId()) && getUserAcademicYearId() > 0) {
				JSONObject ja = new JSONObject();
				JSONArray classCategories = null;
				ja.put("title", "Finance Report");
				JSONArray totalSeriesArray = new JSONArray();
				//JSONObject jo = new JSONObject();
				StringBuffer buffer = new StringBuffer();
				if (!ObjectFunctions.isNullOrEmpty(getCustomerByCustId())) {
					buffer.append("(1,2");
					if(getCustomerByCustId().isTransportModuleStatus()){
						buffer.append(",3");
					}
					buffer.append(")");
				}
				if (getTempId1() != 0) {
					className = (ClassName) adminManager.get(ClassName.class, getTempId1());
					setSchoolTermsList(adminManager.getAll(SchoolTerms.class," custId=" + getUserCustId()+ " and academicYearId="+ getUserAcademicYearId()+ " and feeSettingId in "+ buffer.toString()));
					if (!ObjectFunctions.isNullOrEmpty(getSchoolTermsList())) {
						for (Object obj : getSchoolTermsList()) {
							SchoolTerms schoolTerms = (SchoolTerms) obj;
							JSONArray notPaidArray = new JSONArray();
							JSONArray discountArray = new JSONArray();
							JSONObject paidObject = new JSONObject();
							JSONObject notPaidObject = new JSONObject();
							JSONObject discountObject = new JSONObject();
							classCategories = new JSONArray();
						//	jo = new JSONObject();
							JSONArray paidArray = new JSONArray();
							if (!ObjectFunctions.isNullOrEmpty(className)) {
								classCategories.put(className.getClassName());
								//SchoolFeeSetting feeSetting=(SchoolFeeSetting)adminManager.get(SchoolFeeSetting.class, schoolTerms.getFeeSettingId());
								
								long totalAmount = adminManager.getFeeTotalAmountByCustId(getUserCustId(), schoolTerms.getId(),getUserAcademicYearId(),className.getId());
								if(totalAmount>0){
									long totalPaidAmount = adminManager.getTotalStudentsPaidAmount(getUserCustId(), schoolTerms.getId(),getUserAcademicYearId(), "",className.getId());
									long totalDiscountAmount = adminManager.getDiscountTotalByCustId(getUserCustId(), schoolTerms.getId(),getUserAcademicYearId(),Constants.PAYMENT_STATUS,className.getId());
								//	long paidAmount = totalPaidAmount- totalDiscountAmount;
									paidArray.put(totalPaidAmount);
									long notPaidAmount = totalAmount - totalPaidAmount + totalDiscountAmount;
									notPaidArray.put(notPaidAmount);
									discountArray.put(totalDiscountAmount);
									/*log.debug("schoolTerms.getId()--->"+ schoolTerms.getTermName() + "class"+ className.getDescription());
									log.debug("totalAmount--->"+ totalAmount);
									log.debug("totalPaidAmount--->"+ totalPaidAmount);
									log.debug("totalAmount-totalPaidAmount--->"+ String.valueOf(totalAmount- totalPaidAmount));
									log.debug("totalDiscountAmount----->"+ totalDiscountAmount);*/
									//if(notPaidAmount!=0){
										paidObject.put("name", schoolTerms.getTermName()+ " Paid Amount");
										paidObject.put("data", paidArray);
										paidObject.put("stack", schoolTerms.getTermName());
										totalSeriesArray.put(paidObject);
										notPaidObject.put("name", schoolTerms.getTermName()+ " Pending Amount");
										notPaidObject.put("data", notPaidArray);
										notPaidObject.put("stack", schoolTerms.getTermName());
										totalSeriesArray.put(notPaidObject);
										discountObject.put("name", schoolTerms.getTermName()+ " Discount Amount");
										discountObject.put("data", discountArray);
										discountObject.put("stack", schoolTerms.getTermName());
										totalSeriesArray.put(discountObject);
									//}
									//	paidAmount = 0;
										totalDiscountAmount = 0;
										totalPaidAmount = 0;
										totalAmount = 0;
										notPaidAmount = 0;
								}
							}
						}
					}
				}
				className = null;
				ja.put("series", totalSeriesArray);
				ja.put("categories", classCategories);
				getResponse().getOutputStream().print(ja.toString());
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
	@Actions( { @Action(value = "ajaxViewPaymentDefaulters", results = { @Result(location = "ajaxFeeClassList.jsp", name = "success") }) })
	public String ajaxViewPaymentDefaulters()
			throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxViewPaymentDefaulters' method");
		}
		try {
			viewDefaultersFeeTermsAndClass();			
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}

		return SUCCESS;
	}

	@Actions({ @Action(value = "ajaxGetClassPayentDefaulters", results = { @Result(location = "ajaxViewStudentPendingFeeList.jsp", name = "success") }) })
	public String ajaxGetClassPayentDefaulters() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxGetClassPayentDefaulters' method");
		}
		try {
			List<SchoolTerms> schoolTermsList = null;
			List<Object[]> studentTransportList = null;
			List<Fee> classFeeList = null;
			Date newDate = new Date();
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String today = formatter.format(newDate);
			double termTotalStudentsAmount = 0;
			double termStudentsPaidAmount = 0;
			double termStudentsUnPaidAmount = 0;
			double termStudentsUnDiscountAmount = 0;
			long studsCount = 0;
			if (!ObjectFunctions.isNullOrEmpty(getUserCustId()) && !ObjectFunctions.isNullOrEmpty(getClassId())) {
				if (getUser().isTransportFinance() || getUser().isSchoolTransport()) {
					studsCount = adminManager.getCount("student","custId="+ getUserCustId()+ " and academicYearId="+ getUserAcademicYearId()+ " and studentId in (select id from student where custId="+ getUserCustId()+ " and academicYearId="+ getUserAcademicYearId()+ " and classNameClassId in ("+ getClassId()+ ") and description is null ) ");
				} else
					studsCount = adminManager.getCount("student", " custId="+ getUserCustId() + " and academicYearId="+ getUserAcademicYearId()+ "  and classNameClassId in (" + getClassId()+ ") and description is null");
				if (studsCount > 0) {
					setTempId(studsCount);
					schoolTermsList = adminManager.getAll(SchoolTerms.class," custId=" + getUserCustId()+ " and academicYearId="+ getUserAcademicYearId() + " and  id in "+ getTempString() + "  and dueDate <'"+ today + " 00:00:00' order by dueDate");
					if (!ObjectFunctions.isNullOrEmpty(schoolTermsList)) {
						AcademicYear academicYear = getCurrentAcademicYear();
						for (SchoolTerms schoolTerms : schoolTermsList) {
							if (!ObjectFunctions.isNullOrEmpty(schoolTerms)) {
								if (Constants.TRANSPORT_FEES.equalsIgnoreCase(schoolTerms.getFeeSetting().getSettingName()))
									studentTransportList = adminManager.getAll("select * from studentTransportDetails where custId="+ getUserCustId()+ " and academicYearId="+ getUserAcademicYearId()+ " and studentId in (select id from student where custId="+ getUserCustId()+ " and academicYearId="+ getUserAcademicYearId()+ " and classNameClassId in ("+ getClassId()+ ") and description is null ) ");
								else
									classFeeList = adminManager.getAll(Fee.class," custId="+ getUserCustId()+ " and academicYearId="+ getUserAcademicYearId()+ "  and (classId in ("+ getClassId()+ ") or routeBoardingPointId!=0) and schoolTermId="+ schoolTerms.getId());
								
								if (!ObjectFunctions.isNullOrEmpty(classFeeList) || !ObjectFunctions.isNullOrEmpty(studentTransportList)) {

									doGetclassWiseDefaultstudentsFeeList(schoolTerms, today, academicYear);// ,studTermWiseMesgDetails

									termTotalStudentsAmount += getThirtyTotalAmount();
									termStudentsPaidAmount += getThirtyto60totalAmount();
									termStudentsUnPaidAmount += getPaymentAmount();
									termStudentsUnDiscountAmount += getFourteenTotalAmount();
								}
								studentTransportList=null;
								classFeeList=null;
							}
						}
						getSession().setAttribute("studentsFeeTypeList",getStudentsFeeTypeList());
						schoolTermsList=null;
						academicYear=null;
						newDate=null;
						formatter=null;
					}
				}
			}
			setThirtyTotalAmount(termTotalStudentsAmount);// Total Amount
			setTotalAmount(termStudentsUnPaidAmount);// Total Due Amount
			setThirtyto60totalAmount(termStudentsPaidAmount);// Total Paid Amount
			setFourteenTotalAmount(termStudentsUnDiscountAmount);// Total Discount Amount
		} catch (Exception ex) {
			log.error("Entering into 'catch block':" + ex.getMessage());
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
		}
		return SUCCESS;
	}
	public void prepareRouteBoardingPointIds(List<RouteBoardingPoints> routeBoardingPointsList){
		try {
			StringBuffer routeBoardingPointIds = new StringBuffer();
			routeBoardingPointIds.append("(");
			if(!ObjectFunctions.isNullOrEmpty(routeBoardingPointsList)){
				for(RouteBoardingPoints boardingPoints:routeBoardingPointsList){
					routeBoardingPointIds.append(boardingPoints.getId());
					routeBoardingPointIds.append(",");
				}
			}
			routeBoardingPointIds.append("0)");
			setTempString(routeBoardingPointIds.toString());
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
	}

	public void ajaxGetSchoolTermBoardingPointWiseFee()
			throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log
					.debug("Entering 'ajaxCreateSchoolTermBoardingPointWiseFee' method");
		}
		try {
			ClassName name = null;
			StudyClass studyClass = null;
			StringBuffer queryString = null;
			//long studentCount = 0;
			double classTotalAmount = 0;
			List<Route> routeList = adminManager.getAll(Route.class, "custId="+ getUserCustId() + " and academicYearId="+ getUserAcademicYearId()+" and status='"+Constants.YES_STRING+"'");
			if (!ObjectFunctions.isNullOrEmpty(routeList)) {
				for (Route route : routeList) {
					name = new ClassName();
					double totalRouteAmount = 0;
					// prepareRouteBoardingPointIds(route.getRouteBoardingPointList());
					log.debug("Route name : " + route.getRouteName()+ "route boarding point ids->"+ route.getRouteBoardingPointIds() + " TIme :"+ new Date());
					setSchoolCategory((SchoolCategory)adminManager.get(SchoolCategory.class, " custId="+getUserCustId()+" and categoryName='"+Constants.GENERAL_CATEGORY+"'"));
					if (!ObjectFunctions.isNullOrEmpty(getSchoolTermsList())) {
						float classTotalTermsAmount=0;
						for (SchoolTerms schoolTerms : getSchoolTermsList()) {
							//Object[] totalTermAmount = adminManager.get("select sum(feeAmount),schooltermId from Fee where custId="+ getUserCustId()+ " and academicYearId="+ getUserAcademicYearId()+ " and schoolTermId="+ schoolTerms.getId()+ " and categoryId="+ getSchoolCategory().getId()+ " and routeBoardingPointId in "+ route.getRouteBoardingPointIds()+ " and classId is null");
							//log.debug("boarding point total amount : "+ totalTermAmount[0].toString());
							studyClass = new StudyClass();
							if (!ObjectFunctions.isNullOrEmpty(schoolTerms)) {
								if (route.getRouteBoardingPointAmounts()>0) {
									totalRouteAmount = route.getRouteBoardingPointAmounts();
									studyClass.setAvailableSeates(getSchoolCategory().getId());
									studyClass.setId(route.getId());
									studyClass.setDescription(String.valueOf(totalRouteAmount));
								} else {
									studyClass.setAvailableSeates(getSchoolCategory().getId());
									studyClass.setId(route.getId());
									studyClass.setDescription("0.0");
								}
								/*queryString = new StringBuffer();

								queryString.append(" custId=" + getUserCustId()+ " and academicYearId="+ getAcademicYearId()+ " and categoryId="+ getSchoolCategory().getId()+ " and status='"+ Constants.YES_STRING + "' ");
								if (schoolTerms.isApplToNewStuds()&& "Transport Fee".equalsIgnoreCase(getSchoolFeeSetting().getSettingName())) {
									queryString.append(" and transportMode='"+ Constants.TRANSPORT_STATUS+ "' and vehicleAcademicDetailsId !=0 and joinedThroughAdmissions='"+ Constants.YES_STRING+ "' and boardingPointId in "+ route.getRouteBoardingPointIds());
									} else if (Constants.TRANSPORT_FEES.equalsIgnoreCase(getSchoolFeeSetting().getSettingName())) {
									queryString.append("  and transportMode='"+ Constants.TRANSPORT_STATUS+ "' and vehicleAcademicDetailsId!=0 and boardingPointId in "+ route.getRouteBoardingPointIds());
								}
								queryString.append(" and description is null");
								studentCount = adminManager.getCount("student",queryString.toString());*/
								Object[] transportPickUpFeeAmount = adminManager.get("select IFNULL(SUM(pickupBoardingAmount),0) pickupBoardingAmount,pickupBoardingPointId,studentId from vw_studentTransportFees where custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and schoolTermId="+schoolTerms.getId()+" and description is null and pickupBoardingPointId in "+route.getRouteBoardingPointIds());
								Object[] transportDropFeeAmount = adminManager.get("select IFNULL(SUM(dropBoardingAmount),0) dropBoardingAmount,dropBoardingPointId,studentId from vw_studentTransportFees where custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and schoolTermId="+schoolTerms.getId()+" and description is null and dropBoardingPointId in "+route.getRouteBoardingPointIds());
								
								if(route.getRouteBoardingPointAmounts()>0 && !ObjectFunctions.isNullOrEmpty(transportPickUpFeeAmount) || !ObjectFunctions.isNullOrEmpty(transportDropFeeAmount))
								{
									//classTotalAmount += route.getRouteBoardingPointAmounts()* Float.valueOf(studentCount);
									classTotalAmount += (Double.valueOf(transportPickUpFeeAmount[0].toString())+ Double.valueOf(transportDropFeeAmount[0].toString()));
									name.setSortingOrder((int)(classTotalTermsAmount+=route.getRouteBoardingPointAmounts()));
								}
								transportPickUpFeeAmount=null;
								transportDropFeeAmount=null;
								//totalTermAmount = null;
								totalRouteAmount = 0;
							}
							getObjectList().add(studyClass);
						}
						double classStudentTotalAmount = classTotalAmount;// *Float.valueOf(studentCount);
						int boardingPointPaidAmount =0;
						//int boardingPointPaidAmount = adminManager.getPaidAmountByBoardingPointWise("vw_studentTransportFeeParticularsPayment",getUserCustId(), getAcademicYearId(),getSchoolCategory().getId(), getSchoolFeeSetting().getId(),Constants.TRANSPORT_STATUS, String.valueOf(route.getId()));
						name.setId(route.getId());
						name.setClassName(route.getRouteName());
						name.setDescription(String.valueOf(classStudentTotalAmount));
						if (boardingPointPaidAmount != 0)name.setNoOfSections(boardingPointPaidAmount);
						else {
							name.setNoOfSections(0);
						}
						classStudentTotalAmount = 0;
						getClassList().add(name);
						classTotalAmount = 0;
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
	}

	public void ajaxGetSchoolTermClassWiseFee() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxGetSchoolTermClassWiseFee' method");
		}
		try {
			ClassName name = null;
			StudyClass studyClass = null;
			StringBuffer queryString = null;
			long studentCount = 0;
			double classTotalAmount = 0;
			//String dueDate = null;
			long boardingPointId=0;
			List<ClassName> classNameList =null;
			if("admissionClassFee".equalsIgnoreCase(getTempString())){
				classNameList = adminManager.getAll(ClassName.class, "custId="+getUserCustId()+" and academicYearId="+getAcademicYearId()+" and admissionsOpen='"+Constants.YES_STRING+"' order by sortingOrder");
			}else {
				classNameList = adminManager.getAllClassNames(getUserCustId(), getAcademicYearId());
			}
			
			if (!ObjectFunctions.isNullOrEmpty(classNameList)) {
				for (ClassName className : classNameList) {
					name = new ClassName();
					if (!ObjectFunctions.isNullOrEmpty(className)) {
						if ("Terms Fee".equalsIgnoreCase(getSchoolFeeSetting().getSettingName()) || "Hostel Fee".equalsIgnoreCase(getSchoolFeeSetting().getSettingName())) {
							queryString = new StringBuffer();
							queryString.append(" custId="+ getUserCustId()+ " and academicYearId="+ getAcademicYearId()+ " and classNameClassId="+ className.getId()+ " and categoryId="+ getSchoolCategory().getId()+ " and status='"+ Constants.YES_STRING + "' ");
							if(Constants.HOSTEL_FEE.equalsIgnoreCase(getSchoolFeeSetting().getSettingName())) {
								queryString.append("  and hostelMode ='H' "); //here showing all students hostel fee changed by cvs on 12-3-2014
							}
							queryString.append(" and description is null");
							//log.debug("select count(*) from student where "+queryString.toString()+";");
							studentCount = adminManager.getCount("student", queryString.toString());
						}
						if (!ObjectFunctions.isNullOrEmpty(getSchoolTermsList())) {
							float classTotalTermsAmount=0;
							for (SchoolTerms schoolTerms : getSchoolTermsList()) {
								studyClass = new StudyClass();
								if (!ObjectFunctions.isNullOrEmpty(schoolTerms)) {
									float totalTermAmount = adminManager.getFeeTotalAmountByTerm("Fee",getUserCustId(), className.getId(),getAcademicYearId(),schoolTerms.getId(),getSchoolCategory().getId(),boardingPointId);
									if ("Transport Fee".equalsIgnoreCase(getSchoolFeeSetting().getSettingName()) || "Non Term Fee".equalsIgnoreCase(getSchoolFeeSetting().getSettingName())) {
										queryString = new StringBuffer();
										queryString.append(" custId="+ getUserCustId()+ " and academicYearId="+ getAcademicYearId()+ " and classNameClassId="+ className.getId()+ " and categoryId="+ getSchoolCategory().getId()+ " and status='"+ Constants.YES_STRING + "' ");
										if (schoolTerms.isApplToNewStuds() && "Non Term Fee".equalsIgnoreCase(getSchoolFeeSetting().getSettingName())) {
											queryString.append(" and joinedThroughAdmissions='"+ Constants.YES_STRING+ "'");
										} else if (Constants.TRANSPORT_FEES.equalsIgnoreCase(getSchoolFeeSetting().getSettingName())) {
											queryString.append("  and transportMode='"+ Constants.TRANSPORT_STATUS+ "' and vehicleId!=0");
										}
										queryString.append(" and description is null");
										//log.debug("select count(*) from student where "+queryString.toString()+";");
										studentCount = adminManager.getCount("student", queryString.toString());
									}
									if (totalTermAmount != 0) {
										studyClass.setAvailableSeates(getSchoolCategory().getId());
										studyClass.setId(className.getId());
										studyClass.setDescription(String.valueOf(totalTermAmount));
									} else {
										studyClass.setAvailableSeates(getSchoolCategory().getId());
										studyClass.setId(className.getId());
										studyClass.setDescription("0.0");
									}
									classTotalAmount += totalTermAmount* Float.valueOf(studentCount);
									name.setSortingOrder((int)(classTotalTermsAmount+=totalTermAmount));
									totalTermAmount = 0;
								}
								getObjectList().add(studyClass);
								//dueDate = DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN,schoolTerms.getDueDate());
								queryString = null;
							}
							double classStudentTotalAmount = classTotalAmount;// *Float.valueOf(studentCount);
							int classPaidAmount = adminManager.getPaidAmountByClassId("vw_studentFeePaymentDetails",getUserCustId(), className.getId(),getAcademicYearId(), getTempId2(),getSchoolFeeSetting());
							name.setId(className.getId());
							name.setClassName(className.getClassName());
							name.setDescription(String.valueOf(classStudentTotalAmount));
							if (classPaidAmount != 0)
								name.setNoOfSections(classPaidAmount);
							else {
								name.setNoOfSections(0);
							}
							classStudentTotalAmount = 0;
							getClassList().add(name);
							classTotalAmount = 0;
							classTotalTermsAmount=0;
						}
					}
					/*setTempList(adminManager.getSchoolTermsByDuedate("SchoolTerms", getUserCustId(),getAcademicYearId(), dueDate, getSchoolFeeSetting().getId()));
					if (getSchoolTermsList().size() >= 4) {
						if (getTempList().size() != 0) {
							setAnyId(dueDate);
						}
					}*/
				}
			}
			classNameList = null;
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
	}
	public void ajaxCreateStudentMonthlyTransportFeeDetails(Fee feeDetails) throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxCreateStudentMonthlyTransportFeeDetails' method");
		}
		try {
			int monthCount=DateFunctions.getNumberOfMonths(feeDetails.getSchoolTerms().getFromDate(), feeDetails.getSchoolTerms().getToDate());
			Calendar cal = Calendar.getInstance();
			cal.setTime(feeDetails.getSchoolTerms().getFromDate());
			int monthStartNo = cal.get(Calendar.MONTH);
			double remainingAmt=remainingAmt=feeDetails.getFeeAmount();
			double monthlyAmt=remainingAmt/monthCount;
			StudentTransportMonthFeeDetails transportMonthFeeDetails=null;
			for(int i = 1;i<=monthCount;i++){
				transportMonthFeeDetails=new StudentTransportMonthFeeDetails();
				//double paymentAmt=feePaidDetails.getPaymentAmount(); 
				String monthName=DateFunctions.getMonthFullName(monthStartNo);
				transportMonthFeeDetails.setCustId(getUserCustId());
				transportMonthFeeDetails.setAcademicYear(getCurrentAcademicYear());
				transportMonthFeeDetails.setMonthId(monthStartNo+1);
				transportMonthFeeDetails.setMonthName(monthName);
				transportMonthFeeDetails.setStudentId(getStudent().getId());
				transportMonthFeeDetails.setFee(feeDetails);
				if(remainingAmt >= monthlyAmt){
					log.debug("in side id ->"+remainingAmt);
					transportMonthFeeDetails.setPaymentAmount(monthlyAmt);
					transportMonthFeeDetails.setPaymentStatus(Constants.PAYMENT_STATUS);
				}else {
					log.debug("else id ->"+remainingAmt);
					transportMonthFeeDetails.setPaymentAmount(remainingAmt);
					transportMonthFeeDetails.setPaymentStatus(Constants.NO_STRING);
				}
				remainingAmt=remainingAmt-monthlyAmt;
				log.debug(monthName);
				monthStartNo++;
				if(monthStartNo==12){
					monthStartNo=0;
				}
				adminManager.save(transportMonthFeeDetails);
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
	}

	@Action(value = "ajaxAddOrUpdateFineFeePayment", results = { @Result(location = "ajaxGetSchoolFeeHome.jsp", name = "success") }) 
	public String ajaxAddOrUpdateFineFeePayment() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxAddOrUpdateFineFeePayment' method");
		}
		try {
			JSONObject otherFeeJson = null;
			JSONArray otherFeeJsonArray = null;
			String otherFeeName = null;
			String quantity = null;
			String otherFeeAmount = null;
			FineFee fineFee = null;
			Student student = null;
			long invoiceNumber = 0;
			if (StringFunctions.isNotNullOrEmpty(getTempString()) && StringFunctions.isNotNullOrEmpty(getAnyId())) {
				student = (Student) adminManager.get(Student.class,"id=" + getAnyId());
				String ipAddress = InetAddress.getLocalHost().getHostAddress();
				otherFeeJsonArray = new JSONArray(getTempString());
				AcademicYear academicYear = getCurrentAcademicYear();
				for (int i = 0; i < otherFeeJsonArray.length(); i++) {
					otherFeeJson = otherFeeJsonArray.getJSONObject(i);
					if (!ObjectFunctions.isNullOrEmpty(otherFeeJson)) {
						
						otherFeeName = (String) otherFeeJson.get("otherFeeName");
						quantity = (String) otherFeeJson.get("quantity");
						otherFeeAmount = (String) otherFeeJson.get("otherFeeAmount");
						
						fineFee = new FineFee();
						fineFee.setCreatedById(getUserCustId());
						fineFee.setLastUpdatedById(getUser().getId());
						fineFee.setCreatedDate(new Date());
						fineFee.setAcademicYear(academicYear);
						fineFee.setStudent(student);
						fineFee.setStatus("P");
						fineFee.setDeleteStatus("N");
						
						fineFee.setFineFeeAmount(Double.valueOf(otherFeeAmount));
						//setEmpId(String.valueOf(otherFeeAmount)); // Here we are getting paid Amount to show in words.
						fineFee.setDescription(otherFeeName);
						if(!StringFunctions.isNullOrEmpty(quantity))
							fineFee.setQuantity(Long.valueOf(quantity));
						if(getFineFee().getInvoiceNumber() == 0){
							invoiceNumber = adminManager.getFineFeeMaxInvoiceNumber(getUserCustId(), getUserAcademicYearId());
						}else{
							invoiceNumber = getFineFee().getInvoiceNumber();
						}
						
						fineFee.setInvoiceNumber(invoiceNumber);
						fineFee.setPaymentDate(getFineFee().getPaymentDate());
						fineFee.setCustId(getUserCustId());
						if (!StringFunctions.isNullOrEmpty(ipAddress))
							fineFee.setIpAddress(ipAddress);
						adminManager.save(fineFee);
						if(!"F".equalsIgnoreCase(student.getFeePaidStatus()))
							student.setFeePaidStatus("P");
						adminManager.save(student);
					}
				}
				academicYear=null;
				setObjectList(adminManager.getAll(FineFee.class, "custId="+ getUserCustId() + " and studentId=" + getAnyId()+" and deleteStatus='N' "));
				setTempId(adminManager.getFineFeeMaxInvoiceNumber(getUserCustId(), getUserAcademicYearId()));
				setTempId2(invoiceNumber);
				//setTempString(getFineFee().getPaymentDateStr());
				setPaymentDateStr(getFineFee().getPaymentDateStr());
				super.addActionMessage("You have successfully added other fee.");
				setStudyClassList(adminManager.GetAllStudyClasses( getUserCustId(), getUserAcademicYearId(),null));
				adminGetSchoolFee();
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}

	@Action(value = "ajaxStudentPaymentPdfFineFeeReportPopup", results = { @Result(location = "popupViewStudentFineFeeReciept.jsp", name = "success") })
	public String ajaxStudentPaymentPdfFineFeeReportPopup()
			throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxStudentPaymentPdfFineFeeReportPopup' method");
		}
		try {
			ViewStudentFineFeeDetails fineFee = null;
			String studId = getAnyId();
			setPlTitle(getPlTitle()); // here get the fine amount in words from page
			long invoiceNumber = Long.valueOf(getParamValue("invoiceNumber"));
			setTempString(getParamValue("paymentDateStr"));
			setCustomer(getCustomerByCustId());
			SimpleDateFormat fm = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
	        setReceiptGenarationDate(fm .format(new Date()));
			//Added By Siva for displaying country currency symbol
			if(!ObjectFunctions.isNullOrEmpty(getCustomer().getAddress()) && !ObjectFunctions.isNullOrEmpty(getCustomer().getAddress().getCountryId()))
	        setCountry((Country)adminManager.get(Country.class, getCustomer().getAddress().getCountryId()));
			Double value = 0.0;
			if (!ObjectFunctions.isNullOrEmpty(studId))
			setViewStudentClassDetails((ViewStudentClassDetails)adminManager.get(ViewStudentClassDetails.class,"studId="+studId+" and custId="+getUserCustId()+" and studDiscontinueDesc is null"));
			//setStudent((Student) adminManager.get(Student.class, "id=" + studId+ " and custId=" + getUserCustId()+ "  and description is null"));
			if (!ObjectFunctions.isNullOrEmpty(getViewStudentClassDetails())) 
			{
				if (invoiceNumber > 0) {
					setTempList(adminManager.getAll(ViewStudentFineFeeDetails.class, "custId="+ getUserCustId() + " and studentId="+ getViewStudentClassDetails().getStudId() + " and invoiceNumber=" + invoiceNumber));
				} else {
					setTempList(adminManager.getAll(ViewStudentFineFeeDetails.class, "custId="+ getUserCustId() + " and studentId=" + getViewStudentClassDetails().getStudId()));
				}
				for (Object obj : getTempList()) {
					fineFee = (ViewStudentFineFeeDetails) obj;
					value += fineFee.getFineFeeAmount();
				}
				if(!ObjectFunctions.isNullOrEmpty(fineFee))
				  setFineFeeDetails(fineFee);
				
				setTotalAmount(value);
				
				ajaxGenerateFineFeeReceiptPDFReport(getTempList(),getTotalAmount(),invoiceNumber);
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}

	@Action(value = "ajaxDoFineFeePayment", results = { @Result(location = "ajaxDoFineFee.jsp", name = "success") }) 
	public String ajaxFineFeePayment() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxFineFeePayment' method");
		}
		try {
			if(StringFunctions.isNotNullOrEmpty(getAnyId())){
				//setObjectList(adminManager.getAll(FineFee.class, "custId="+ getUserCustId() + " and studId=" + getAnyId()));
				setObjectList(accountManager.getOtherFeePaymentDetails(getUserCustId(),getUserAcademicYearId(),Long.valueOf(getAnyId())));
				setTempId(adminManager.getFineFeeMaxInvoiceNumber(getUserCustId(), getUserAcademicYearId()));
				AcademicYear academicYear=(AcademicYear) adminManager.get(AcademicYear.class, Long.valueOf(getUserAcademicYearId()));
				setFeeModuleUsege(academicYear.getFeeModuleUsegeBy());
				setTempString(academicYear.getPastYear()+":"+(Long.valueOf(academicYear.getPastYear())+1));
				if(!ObjectFunctions.isNullOrEmpty(getAnyTitle())) // Here  anytitle using show the student Name and className in fin fee popup page
					setAnyTitle(getAnyTitle());
				academicYear=null;
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	
	public String ajaxGenerateFineFeeReceiptPDFReport(List<ViewStudentFineFeeDetails> tempList,double totalAmount,long invoiceNumber) {
		try {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxGenerateFineFeeReceiptPDFReport' method");
			}
		//	String parentEmail = null;
			String fileName = null;
			PDFGenerator pDFGenerator = new PDFGenerator();
			getResponse().setContentType(pDFGenerator.getMimeType());
			pDFGenerator.createDocumentJasper();
			//fileName = "FEE_RECEIPT_"+ invoiceNumber+"_"+DateUtil.getDateTime(DateFormatter.MMDDYYY_PATTERN,new Date());
			fileName = "FINE_FEE_RECEIPT"+ invoiceNumber+".pdf";
			File file = new File(getSession().getServletContext().getRealPath("userfiles/tempFiles/"+fileName));
			
			log.debug("Fee receipt Email path......"+file.getAbsolutePath());

		      if (file.createNewFile()){
		        log.debug("File is created!");
		      }else{
		        log.debug("File already exists.");
		      }
   		 	String pathName = generateUploadFeeReceiptFilepath(getCustomerByCustId());
   		 	File destDir = new File(getSession().getServletContext().getRealPath(pathName+fileName));
			 FileUtils.copyFile(file, destDir);
			 
			pDFGenerator.setPdfWriter(PdfWriter.getInstance(pDFGenerator.getDocument(), new FileOutputStream(file.getAbsolutePath())));
			pDFGenerator.setPdfWriter(PdfWriter.getInstance(pDFGenerator.getDocument(), new FileOutputStream(destDir.getAbsolutePath())));
			PdfHeaderFooterMarkJasper phfmj = new PdfHeaderFooterMarkJasper();
			pDFGenerator.getPdfWriter().setPageEvent(phfmj);
			pDFGenerator.getDocument().open();
			file.deleteOnExit();
			if(!ObjectFunctions.isNullOrEmpty(tempList))
			{
				String fontPath = getSession().getServletContext().getRealPath(getText(Constants.GILITE_FILE_DOCS_DIR)+ "/Droid-Sans/DroidSans-Bold.ttf");
				FontFactory.register(fontPath);
				// creating pDF page event to set header and Footer to document
				PdfPTable mainTable = new PdfPTable(1);
				mainTable.setWidthPercentage(100);
				mainTable.setSplitLate(false);
				mainTable.getDefaultCell().setBorder(Rectangle.BOX);
				int width =3;
				PdfPTable defaultersHeaderReport = new PdfPTable(width);
				defaultersHeaderReport.setWidthPercentage(100);
				PdfPTable headerReport = new PdfPTable(100);
				headerReport.setWidthPercentage(100);
				
				organizationHeaderForPdf(fontPath,headerReport);
				
				headerReport.addCell(PDFGenerator.getPdfCellWithCenterAlignHeadings("FINE FEE RECEIPT ",100, fontPath));
				
				mainTable.addCell(headerReport);
				//Genders 
				for(ViewStudentFineFeeDetails viewStudentFineFeeDetails:tempList)
				{
				//	parentEmail = viewStudentFineFeeDetails.getParentEmail();			
					defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithAlignLeftNoColorWithNoBorderFont(" Student Name : " + viewStudentFineFeeDetails.getStudentFullName(),1,10, fontPath));
					defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithAlignLeftNoColorWithNoBorderFont(" ",1,10, fontPath));
					defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithAlignLeftNoColorWithNoBorderFont("   Date : " + viewStudentFineFeeDetails.getPaymentDateStr(),1,10, fontPath));
					
					defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithAlignLeftNoColorWithNoBorderFont(" Admission No : " + viewStudentFineFeeDetails.getAdmissionNumber(),1,10, fontPath));
					defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithAlignLeftNoColorWithNoBorderFont(" ",1,10, fontPath));
					
					if(!StringFunctions.isNullOrEmpty(viewStudentFineFeeDetails.getMobileNumber()))
					{
						defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithAlignLeftNoColorWithNoBorderFont(" Mobile No : " + viewStudentFineFeeDetails.getMobileNumber(),1,10, fontPath));
					}
					else
						defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithAlignLeftNoColorWithNoBorderFont(" Mobile No : -- ",1,10, fontPath));
					
					
					defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithAlignLeftNoColorWithNoBorderFont("Class / Section : " + viewStudentFineFeeDetails.getClassAndSection(),1,10, fontPath));
					defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithAlignLeftNoColorWithNoBorderFont(" ",1,10, fontPath));
					defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithAlignLeftNoColorWithNoBorderFont("Receipt No :  " + viewStudentFineFeeDetails.getInvoiceNumber(),1,10, fontPath));
					
					defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithAlignLeftNoColorWithNoBorderFont(" ",width,10, fontPath));
					
					defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithLeftAlignJasperNoColor(" S.No",1, fontPath));
					defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithLeftAlignJasperNoColor("Description",1, fontPath));
					defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithLeftAlignJasperNoColor("Amount",1, fontPath));
					
					defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithLeftAlignFontSizeJasperNoColor("1",1, fontPath));
					defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithLeftAlignFontSizeJasperNoColor("" + viewStudentFineFeeDetails.getDescription(),1, fontPath));
					defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithLeftAlignFontSizeJasperNoColor("" + viewStudentFineFeeDetails.getFineFeeAmount(),1, fontPath));
					
					defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithAlignLeftNoColorWithNoBorderFont(" ",width,10, fontPath));
					defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithAlignLeftNoColorWithNoBorderFont(" ",width,10, fontPath));
					
					defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithRightAlignJasperNoColorFont(" Total Amount ",2,10, fontPath));
					defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithLeftAlignJasperNoColor("" + totalAmount,1, fontPath));
					
					defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithRightAlignJasperNoColorFont(" Paid Amount ",2,10, fontPath));
					defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithLeftAlignJasperNoColor("" + viewStudentFineFeeDetails.getFineFeeAmount(),1, fontPath));
				}
				
				defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithCenterAlignHeadings(" ",width, fontPath));
				defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithCenterAlignHeadings(" ",width, fontPath));
				defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithCenterAlignHeadings(" ",width, fontPath));
				defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithCenterAlignHeadingsWithRightAlign("Authorized Signature",width, fontPath));
				defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithCenterAlignHeadings(" ",width, fontPath));
				defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithCenterAlignHeadings(" ",width, fontPath));
				
				/*defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithAlignLeftNoColorWithNoBorderFont("Note: ",1,9, fontPath));
				defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithAlignLeftNoColorWithNoBorderNormalFont("Fees will not be refunded at any circumstances.",width-1,8, fontPath));*/
				
				defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithAlignRightNoColorWithNoBorderFont("Note: ",1,9, fontPath));
				defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithAlignLeftNoColorWithNoBorderNormalFont("Fees will not be refunded at any circumstances.",width-1,8, fontPath));
				mainTable.addCell(defaultersHeaderReport);
				pDFGenerator.getDocument().add(mainTable);
				pDFGenerator.getDocument().close();
				pDFGenerator = null;
				mainTable = null;
				defaultersHeaderReport = null;
				
			}
			phfmj = null;
			
			/*if(!StringFunctions.isNullOrEmpty(parentEmail))
			{
			
				String[] emailAddresses = new String[1];
				String[] attachmentFiles = new String[1];
				attachmentFiles[0] = destDir.getAbsolutePath();
				emailAddresses[0] = parentEmail;
				MailUtil mailUtil=new MailUtil(emailAddresses, "Fine Fee Receipt", "Please find the Fine Fee receipt.", getUser(),attachmentFiles);
				mailUtil.sendEmailToFineFeeReceiptToParent();
				mailUtil=null;
			}*/
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
	
	@Actions( { @Action(value = "ajaxManageStudentpocketMoney", results = { @Result(location = "ajaxManageStudentPocketMoney.jsp", name = "success")}) })
	  public String ajaxManageStudentpocketMoney() throws URTUniversalException {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxManageStudentpocketMoney' method");
			}
			try {
				getSmsCount();
				List<Object[]> studentslist = adminManager.getAll("select fullName,admissionNumber from vw_studentDetails where custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+"  and status='Y' and hostelMode='H' and description is null  ");
				if(!ObjectFunctions.isNullOrEmpty(studentslist)){
					setStudentsList(new ArrayList());
					for (Object[] studentDetails : studentslist) {
						if (!ObjectFunctions.isNullOrEmpty(studentDetails[0]) && !ObjectFunctions.isNullOrEmpty(studentDetails[1])) {
							getStudentsList().add(studentDetails[0].toString()+'('+studentDetails[1].toString()+')');
						}
					}
					Customer customer = getCustomerByCustId();
					if(!ObjectFunctions.isNullOrEmpty(customer)){
						setCustomer(customer);
					}
					customer = null;
				}
			}
			catch(Exception ex)
			{
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return SUCCESS;
		}
	
	@Actions( { @Action(value = "ajaxAddStudentPocketMoney", results = { @Result(location = "ajaxManageStudentPocketMoney.jsp", name = "success")}) })
	  public String ajaxAddstudentPocketMoney() throws URTUniversalException {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxAddstudentPocketMoney' method");
			}
			try {
				if(!ObjectFunctions.isNullOrEmpty(getStudentPocketMoney()) && !StringFunctions.isNullOrEmpty(getParamValue("studNameAndAdmiNum"))){
					StudentPocketMoney pocketMoney = new StudentPocketMoney();
					String studentNameAndAdminNumber = getParamValue("studNameAndAdmiNum").replace("(", ",");
					String[] studetails = studentNameAndAdminNumber.split(",");
					String studentName = studetails[0].toString();
					double totalAvailable = 0;
					double totalDeposit = 0;
					double totalWithDraw = 0;
					String studentAdmissionNumber = studetails[1].toString().replace(")", "");
					if(!StringFunctions.isNullOrEmpty(studentName) && !StringFunctions.isNullOrEmpty(studentAdmissionNumber)){
						User user = (User) adminManager.get(User.class,"custId="+ getUserCustId()+" and admissionNumber='"+studentAdmissionNumber.trim()+"' ");
					if(!ObjectFunctions.isNullOrEmpty(user)){
						Student student = (Student)adminManager.get(Student.class,"accountId="+user.getId()+" and custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+"");
						if(!ObjectFunctions.isNullOrEmpty(student)){
						pocketMoney.setStudent(student);
						pocketMoney.setAdmissionNumber(studentAdmissionNumber.trim());
						pocketMoney.setAcademicYear(student.getAcademicYear());
						pocketMoney.setDepositorName(getStudentPocketMoney().getDepositorName());
						if("D".equalsIgnoreCase(getStudentPocketMoney().getStatus()))
							pocketMoney.setAmount(getStudentPocketMoney().getAmount());
						if("W".equalsIgnoreCase(getStudentPocketMoney().getStatus()))
							pocketMoney.setAmount(Double.valueOf(getAnyId()));
						pocketMoney.setTransactionDate(getStudentPocketMoney().getTransactionDate());
						pocketMoney.setStatus(getStudentPocketMoney().getStatus());
						pocketMoney.setCustId(getUserCustId());
						adminManager.save(pocketMoney);
						if("D".equalsIgnoreCase(getStudentPocketMoney().getStatus())){
							super.addActionMessage("Pocketmoney deposited successfully.");
							 setAnyTitle(getStudentPocketMoney().getStatus());
						}
						if("W".equalsIgnoreCase(getStudentPocketMoney().getStatus())){
							super.addActionMessage("Pocketmoney withdrawn successfully.");
							 setAnyTitle(getStudentPocketMoney().getStatus());
						}
						Customer customer = getCustomerByCustId();
						SMSServiceProviders smsServiceProvider = (SMSServiceProviders)adminManager.getSMSServiceProviderByCustId(customer.getSmsServiceProviderId());
						getSmsCount();
						if(!ObjectFunctions.isNullOrEmpty(customer) && customer.isCheckMobileService() && !StringFunctions.isNullOrEmpty(user.getPerson().getMobileNumber()) && !ObjectFunctions.isNullOrEmpty(user.getPerson().getGender()) && (getSmsAlloted()!=0 && getSmsAlloted() > getSmsCnt())){
							Messages message = new Messages();
			              	StringBuffer msgContent = new StringBuffer();
			              	 Set<String> mobileNumberset = new HashSet<String>();
			              	 log.debug("select (select sum(amount) from  StudentPocketMoney where studentId="+student.getId()+" and status='D' and custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" ) as totalDepositAmount,sum(amount) as totalWthDrawAmnt from StudentPocketMoney where studentId="+student.getId()+" and status='W' and custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+"");
			              	 List<Object[]> pocketMoneyList = adminManager.getAll("select (select sum(amount) from  StudentPocketMoney where studentId="+student.getId()+" and status='D' and custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+") as totalDepositAmount,sum(amount) as totalWthDrawAmnt from StudentPocketMoney where studentId="+student.getId()+" and status='W' and custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" ");
			              	 if(!ObjectFunctions.isNullOrEmpty(pocketMoneyList)){
			              		 for(Object[] obj : pocketMoneyList){
			              			 if(!ObjectFunctions.isNullOrEmpty(obj[0]))
				                    			 totalDeposit = Double.valueOf(obj[0].toString());
			              			 if(!ObjectFunctions.isNullOrEmpty(obj[1]))
				                    			 totalWithDraw = Double.valueOf(obj[1].toString());
			              			if(totalDeposit >= totalWithDraw){
			              				 totalAvailable = totalDeposit - totalWithDraw;
			              			}
			              			 
			              		 }
			              	 }
			              	 if("D".equalsIgnoreCase(getStudentPocketMoney().getStatus())){
			              		 msgContent.append("Dear Parent, You have deposited Rs."+getStudentPocketMoney().getAmount()+" of pocket money for "+studentName+" on "+DateFormatter.formatDate(DateFormatter.MMDDCCYY_PATTERN,getStudentPocketMoney().getTransactionDate())+". Total available balance is Rs "+totalAvailable+". Thank you from ");
			              	 }
			              	if("W".equalsIgnoreCase(getStudentPocketMoney().getStatus())){
			              		msgContent.append("Dear Parent ");
				              	if("M".equalsIgnoreCase(user.getPerson().getGender())){
				              		msgContent.append(" Your Son ");
				              	}
				              	if("F".equalsIgnoreCase(user.getPerson().getGender())){
				              		msgContent.append(" Your Daughter ");
				              	}
				              		msgContent.append(studentName+" has taken Rs "+getAnyId()+" from pocketmoney on "+DateFormatter.formatDate(DateFormatter.MMDDCCYY_PATTERN,getStudentPocketMoney().getTransactionDate())+".Total available balance is Rs "+totalAvailable+".Thank you from ");
			              	}
			              	log.debug(msgContent.toString());
			              	message.setMessageDescription(msgContent.toString());
			              	message.setCreatedById(getUser().getId());
			                message.setAcademicYear(student.getAcademicYear());
			                message.setPurposeType("regd: pocketmoney");
			              	message.setCustomer(customer);
			              	message.setSmsSenderId(customer.getSender());
			              	mobileNumberset = adminManager.addMobileNumbersBasedOnAddressType(customer.getMobileType(),user.getPerson().getMobileNumber(), user.getPerson().getSecondaryMobileNumber(),user.getPerson().getAnotherMobileNumber(),user.getPerson().getAnotherSecondaryMobileNumber(),user.getPerson().getAddressType());
			              	/*if("B".equalsIgnoreCase(customer.getMobileType())){
			              		mobileNumberset.addAll(adminManager.addMobileNumberBasedOnSettings(customer.getMobileType(), user.getPerson().getMobileNumber(), user.getPerson().getSecondaryMobileNumber()));
			              	}else if("P".equalsIgnoreCase(customer.getMobileType())){
			              		mobileNumberset.add(user.getPerson().getMobileNumber());
			              	}else{
			              		if(!ObjectFunctions.isNullOrEmpty(user.getPerson().getSecondaryMobileNumber()))
				              		mobileNumberset.add(user.getPerson().getSecondaryMobileNumber());
			              	}*/
			              	if (ObjectFunctions.isNotNullOrEmpty(mobileNumberset)){
				                //mobileNumberset.add(user.getPerson().getMobileNumber());
				                message = communicationManager.saveMessageDetailsTracking(message,student,null,null);
				                communicationManager.deliverSms(message,mobileNumberset, smsServiceProvider);
				                msgContent = null;
				                mobileNumberset = null;
			              	}
			                message = null;
			                user = null;
			                student = null;
			                customer = null;
							}
						}
					}
				}
			}
				ajaxManageStudentpocketMoney();
			}
			catch(Exception ex){
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return SUCCESS;
		}
	
	
	@Actions( { @Action(value = "ajaxGetStudentTransactionHistory", results = { @Result(location = "ajaxGetStudentTransactionHistory.jsp", name = "success")}) })
	  public String ajaxGetStudentTransactionHistory() throws URTUniversalException {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxGetStudentTransactionHistory' method");
			}
			try {
				if(!StringFunctions.isNullOrEmpty(getParamValue("studentName")) && !StringFunctions.isNullOrEmpty(getParamValue("status"))){
					StringBuffer sqlQuery = null;
					List transactionslist = null;
					String studentNameAndAdminNumber = getParamValue("studentName").replace("(", ",");
					String[] studetails = studentNameAndAdminNumber.split(",");
					String studentAdmissionNumber = studetails[1].toString().replace(")", "");
					if(!ObjectFunctions.isNullOrEmpty(studentAdmissionNumber) && "D".equalsIgnoreCase(getParamValue("status"))){
						sqlQuery = new StringBuffer("select DATE_FORMAT(transactiondate,'%m-%d-%Y') as transactiondate,admissionNumber,amount,(select sum(amount) from StudentPocketMoney where status='D' and admissionNumber='"+studentAdmissionNumber.trim()+"') as totalDepositAmount,depositorName from StudentPocketMoney where custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and admissionNumber='"+studentAdmissionNumber.trim()+"' and status='"+getParamValue("status")+"' ");
						 transactionslist = adminManager.getAll(sqlQuery.toString());
						 setAnyTitle(getParamValue("status"));
					}
					if(!ObjectFunctions.isNullOrEmpty(studentAdmissionNumber) && "W".equalsIgnoreCase(getParamValue("status"))){
						List objectlist = adminManager.getAll(StudentPocketMoney.class,"custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and admissionNumber='"+studentAdmissionNumber.trim()+"' and status='W' ");
						if(ObjectFunctions.isNullOrEmpty(objectlist)){
							sqlQuery = new StringBuffer("select DATE_FORMAT(transactiondate,'%m-%d-%Y') as transactiondate,admissionNumber,amount,(select sum(amount) from StudentPocketMoney where status='D' and admissionNumber='"+studentAdmissionNumber.trim()+"' ) as totalDepositAmount,(select sum(amount) from StudentPocketMoney where status='W' and admissionNumber='"+studentAdmissionNumber.trim()+"') as totalTakenAmount from StudentPocketMoney where custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and admissionNumber='"+studentAdmissionNumber.trim()+"' and status='D' ");
						}
						else if(!ObjectFunctions.isNullOrEmpty(objectlist)){
							sqlQuery = new StringBuffer("select DATE_FORMAT(transactiondate,'%m-%d-%Y') as transactiondate,admissionNumber,amount,(select sum(amount) from StudentPocketMoney where status='D' and admissionNumber='"+studentAdmissionNumber.trim()+"' ) as totalDepositAmount,(select sum(amount) from StudentPocketMoney where status='W' and admissionNumber='"+studentAdmissionNumber.trim()+"') as totalTakenAmount from StudentPocketMoney where custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and admissionNumber='"+studentAdmissionNumber.trim()+"' and status='W' ");
						}
						if(!ObjectFunctions.isNullOrEmpty(sqlQuery))
							transactionslist = adminManager.getAll(sqlQuery.toString());
						objectlist = null;
					}
					if(!ObjectFunctions.isNullOrEmpty(transactionslist)){
						setTempList(transactionslist);
					}
					transactionslist=null;
					sqlQuery = null;
					studetails = null;
					studentAdmissionNumber = null;
				}
			}
			catch(Exception ex){
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return SUCCESS;
		}
	@Actions( { @Action(value = "ajaxDeleteInvoice", results = { @Result(location = "ajaxGetSchoolFeeHome.jsp", name = "success")}) })
	  public String ajaxDeleteInvoice() throws URTUniversalException {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxDeleteInvoice' method");
			}
			try {
				 StudentPayment studentPayment = (StudentPayment)adminManager.get(StudentPayment.class, "custId="+getUserCustId()+" and academicYearId="+getAcademicYearId()+" and studentId="+getStudentNumber()+" and invoiceNumber='"+getTempString()+"' ");
				 if(!ObjectFunctions.isNullOrEmpty(studentPayment)){
					 studentPayment.setLastUpdatedById(getUser().getId());
					 studentPayment.setLastUpdatedDate(new Date());
					 studentPayment.setLastAccessDate(new Date());
					 studentPayment.setDeleteDescription(getStudentPayment().getDeleteDescription());
					 studentPayment.setDeleteStatus(Constants.YES_STRING);
					 for(StudentFeePaidDetails studentFeePaidDetails : studentPayment.getStudentFeePaidDetails()){
						 studentFeePaidDetails.setDeleteStatus(Constants.YES_STRING);
					 }
					 adminManager.save(studentPayment);
					 adminManager.checkStudentFeePaidStatus(getAcademicYearId(), getUserCustId(), studentPayment.getStudent());
					 log.debug("srudent invoice number :"+studentPayment.getInvoiceNumber());
					 super.addActionMessage(studentPayment.getInvoiceNumber()+" deleted succesully.");
					 studentPayment=null;
				 }
			}
			catch(Exception ex)
			{
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			finally {
				 adminGetSchoolFee();
			}
			return SUCCESS;
		}
	@Actions({
		@Action(value = "ajaxDoSchoolFeeConcession", results = { @Result(location = "ajaxSchoolFeeConcessionHome.jsp", name = "success") })
	})
	public String ajaxDoSchoolFeeConcession() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDoSchoolFeeConcession' method");
		}
		try {
			
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}

	@Actions({ @Action(value = "ajaxSearchStudent", results = { @Result(type = "json", name = "success") }) })
	public String ajaxSearchStudent() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxSearchStudent' method");
		}
		try {
			List<ViewStudentPersonAccountDetails> allStudentList = null;
			String searchword = getParamValue("searchword").trim();

			if (!StringFunctions.isNullOrEmpty(searchword)) {
				StringBuffer queryString = new StringBuffer("custId="+ getUserCustId() + " and academicYearId="+ getUserAcademicYearId() + " and accountExpired='"+ Constants.NO_STRING + "' and (firstName like '%"+ searchword + "%' or lastName like '%" + searchword+ "%' or admissionNumber like '%" + searchword+ "%')");// and roleName='ROLE_STUDENT' group by accountId
				allStudentList = adminManager.getAll(ViewStudentPersonAccountDetails.class,queryString.toString());
			
				JSONArray res = new JSONArray();
				JSONObject j;
				j = new JSONObject();
				if (!ObjectFunctions.isNullOrEmpty(allStudentList)) {
					for (ViewStudentPersonAccountDetails users : allStudentList) {
						j = new JSONObject();
						j.put("studentId", users.getStudentId());
						j.put("title",users.getFirstName() + "-"+ users.getAdmissionNumber() + " ( "+ users.getClassAndSection() + " )");
						j.put("role", users.getRoleDescription());
						j.put("userName", users.getUsername());
						res.put(j);
					}
				} else {
					j = new JSONObject();
					j.put("studentId", 0);
					j.put("title", "No Results Found !!");
					j.put("lastName", "");
					j.put("role", "");
					j.put("userName", "");
					res.put(j);
				}
				j = new JSONObject();
				j.put("movies", res);
				getResponse().getOutputStream().print(j.toString());
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
		}
		return null;
	}
	
	@Actions({
		@Action(value = "ajaxDoViewStudentFeeDetails", results = { @Result(location = "ajaxViewStudentFeeDetails.jsp", name = "success") })
	})
	public String ajaxDoViewStudentFeeDetails() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDoViewStudentFeeDetails' method");
		}
		try {
			Student student = (Student)adminManager.get(Student.class, "custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and id="+getStudentNumber()+" and description is null");
			if (!ObjectFunctions.isNullOrEmpty(student)) {
				prepareStudentSchoolFeeSettingList(student);
					setTempList(adminManager.getAllStudentTermsWiseNonPaidClassFeePaymentDetails(student.getId(),student.getAcademicYearId(),getTempString()));
					if(ObjectFunctions.isNotNullOrEmpty(getTempList())){
						for(Object obj : getTempList()){
							ViewStudentFeePaymentDetails studentTermFee = (ViewStudentFeePaymentDetails)obj;
							studentTermFee.setPendingStudentList(adminManager.getStudentClassFeeDetails(student.getId(),student.getAcademicYearId(),studentTermFee.getSchoolTermId()));
						}
					}
					//Siva: this below code is to show the transport fee based on student boarding point id
					if(getTempString().contains("3")){
						setStudentTransportTermsList(adminManager.getAllStudentTermsWiseTransportFeePaymentDetails(student.getId(),student.getAcademicYearId(),3));
						if(ObjectFunctions.isNotNullOrEmpty(getStudentTransportTermsList())){
							for(Object obj : getStudentTransportTermsList()){
								ViewStudentTransportFeePaymentDetails studentTermFee = (ViewStudentTransportFeePaymentDetails)obj;
								studentTermFee.setStudentTransportFeeList(adminManager.getStudentTransportFeeDetails(student.getId(),student.getAcademicYearId(),studentTermFee.getSchoolTermId()));
								studentTermFee=null;
							}
						}
					}
					student=null;
				}
		
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	@Actions( { @Action(value = "ajaxEditStudentFeeConession", results = { @Result(type = "json", name = "success", params = {"includeProperties", "studFeeConcessionList.*" }) }) })
	public String ajaxEditStudentFeeConession() throws URTUniversalException {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxEditStudentFeeConession' method");
		}
		try {
			if (!StringFunctions.isNullOrEmpty(getStudentNumber())&&!"Undefined".equalsIgnoreCase(getStudentNumber())) {
				List<ViewStudentsConcessionClassFeeDetails> studentFeeConcessionList=adminManager.getAll(ViewStudentsConcessionClassFeeDetails.class, "custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and studentId="+getStudentNumber()+" order by schoolTermId");
				List<ViewStudentsConcessionTransportFeeDetails> studentTransportFeeConcessionList=adminManager.getAll(ViewStudentsConcessionTransportFeeDetails.class, "custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and studentId="+getStudentNumber()+" order by schoolTermId");
				if (ObjectFunctions.isNotNullOrEmpty(studentFeeConcessionList) || ObjectFunctions.isNotNullOrEmpty(studentTransportFeeConcessionList)) {
					JSONArray res = new JSONArray();
					JSONObject j;
					if (ObjectFunctions.isNotNullOrEmpty(studentFeeConcessionList)){
						for (ViewStudentsConcessionClassFeeDetails studentFeeConcession : studentFeeConcessionList) {
							List<StudentFeePaidDetails> studentPaymentList=adminManager.getAll(StudentFeePaidDetails.class, " feeId ="+studentFeeConcession.getFeeId()+" and custId="+getUserCustId()+" and deleteStatus='"+Constants.NO_STRING+"' and studentId="+getStudentNumber()+" and committedFeeStatus='"+Constants.NO_STRING+"' ");
							j = new JSONObject();
							j.put("concessionId", studentFeeConcession.getConcessionId());
							j.put("feeId", studentFeeConcession.getFeeId());
							j.put("schoolTermId", studentFeeConcession.getSchoolTermId());
							j.put("amount", studentFeeConcession.getConcessionAmount());
							j.put("feeAmount", studentFeeConcession.getFeeAmount());
							j.put("feeSettingId", studentFeeConcession.getFeeSettingId());
							j.put("studentPaymentId", studentPaymentList.size());
							res.put(j);
							studentPaymentList=null;
							studentFeeConcession=null;
						}
						studentFeeConcessionList=null;
					}
					if (ObjectFunctions.isNotNullOrEmpty(studentTransportFeeConcessionList)){
						for (ViewStudentsConcessionTransportFeeDetails concessionTransportFeeDetails : studentTransportFeeConcessionList) {
							List<StudentFeePaidDetails> studentPaymentList=adminManager.getAll(StudentFeePaidDetails.class, " studTransportDetailsId="+concessionTransportFeeDetails.getTransportFeeId()+" and custId="+getUserCustId()+" and deleteStatus='"+Constants.NO_STRING+"' and studentId="+getStudentNumber()+" and committedFeeStatus='"+Constants.NO_STRING+"' ");
							j = new JSONObject();
							j.put("concessionId", concessionTransportFeeDetails.getConcessionId());
							j.put("feeId", concessionTransportFeeDetails.getTransportFeeId());
							j.put("schoolTermId", concessionTransportFeeDetails.getSchoolTermId());
							j.put("amount", concessionTransportFeeDetails.getConcessionAmount());
							j.put("feeAmount", concessionTransportFeeDetails.getFeeAmount());
							j.put("feeSettingId", 3);
							j.put("studentPaymentId", studentPaymentList.size());
							res.put(j);
							studentPaymentList=null;
							concessionTransportFeeDetails=null;
						}
						studentTransportFeeConcessionList=null;
					}
					j = new JSONObject();
					j.put("data", res);
					getResponse().getOutputStream().print(j.toString());
				}else{
					JSONObject j = new JSONObject();
					getResponse().getOutputStream().print(j.toString());
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}

	@Actions({ @Action(value = "ajaxAddStudentConcessionFee", results = { @Result(location = "ajaxViewStudentFeeDetails.jsp", name = "success") }) })
	public String ajaxAddStudentConcessionFee() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxAddStudentConcessionFee' method");
		}
		try {
			if (!StringFunctions.isNullOrEmpty(getTempString())) {
				long returnCode = adminManager.addStudentFeeConcessionDetails(getTempString(), Long.valueOf(getStudentNumber()),getCurrentAcademicYear(), getUser().getId(),getUserCustId());
				if (returnCode == 1)
					super.addActionMessage("Student fee concession details created sucessfully.");
				else
					super.addActionError("Student fee concession details not created properly.");

				ajaxDoViewStudentFeeDetails();
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
		}
		return SUCCESS;
	}
	@Actions({ @Action(value = "ajaxDoDownloadAndUploadStudentInvoice", results = { @Result(location = "reports/ajaxDoDownloadAndUploadStudentInvoice.jsp", name = "success") }) })
	public String ajaxDoDownloadAndUploadStudentInvoice() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDoDownloadAndUploadStudentInvoice' method");
		}
		try {
			
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
		}
		return SUCCESS;
	}
	@Actions({
   		@Action(value = "ajaxDownloadStudentsInvoiceExcel", results = {})
   		})
   		public void ajaxDownloadStudentsInvoiceExcel() throws URTUniversalException {
   		if (log.isDebugEnabled()) {
   			log.debug("Entering 'ajaxDownloadStudentsInvoiceExcel' method");
   		}
   		try
   		{
				String fileName = "Fee_Payment_Upload_Template_"+ DateUtil.getDateTime(DateFormatter.MMDDYYY_PATTERN,new Date());
				getResponse().setContentType("application/vnd.ms-excel");
				getResponse().setHeader("Content-Disposition","attachment; filename=" + fileName.replace(' ', '_')+ ".xls");
				PrepareStudentInvoiceExcel prepareStudentInvoiceExcel = new PrepareStudentInvoiceExcel();
				
				List<SchoolTerms> schoolTermsList = adminManager.getAll(SchoolTerms.class," custId = "+getUserCustId()+" and academicYearId="+getUserAcademicYearId());
				prepareStudentInvoiceExcel.createStudentInvoiceSheet("InvoiceSettings",schoolTermsList);
				StringBuffer sheetTitleDesc = new StringBuffer();
				sheetTitleDesc.append("School Name : ");
				if (!ObjectFunctions.isNullOrEmpty(getSession().getAttribute("organization"))) {
					sheetTitleDesc.append((String) getSession().getAttribute("organization"));
				}
				sheetTitleDesc.append(", Academic Year : ");
				if (!ObjectFunctions.isNullOrEmpty(getSession().getAttribute("academicYearName"))) {
					sheetTitleDesc.append((String) getSession().getAttribute("academicYearName"));

				}
				prepareStudentInvoiceExcel.createStudentSheet("Student Invoice",sheetTitleDesc.toString());
				prepareStudentInvoiceExcel.finalPrep("InvoiceSettings");
				prepareStudentInvoiceExcel.getWb().write(getResponse().getOutputStream());
		}
   		catch(Exception ex)
   		{
   			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
   		}
   	}

	@Actions({ @Action(value = "ajaxGenerateStudentsInvoiceData", results = { @Result(location = "reports/ajaxDoDownloadAndUploadStudentInvoice.jsp", name = "success"),
																			  @Result(location = "reports/ajaxDoDownloadAndUploadStudentInvoice.jsp", name = "dummyInit") }) })
	public String ajaxGenerateStudentsInvoiceData() {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxGenerateStudentsInvoiceData' method");
		}
		try {

			log.debug("file Type =" + getUploadContentType());
			boolean excelFileType = false;
			excelFileType = validateExcelFileType(getUploadContentType());
			if (excelFileType) {
				log.debug("No file to upload....");
				super.addActionError("File type not matched.");
				return "dummyInit";
			}

			if (getUserAcademicYearId() > 0) {
				String admissionNumber = null;
				String invoiceNumber = null;
				String paymentDateStr = null;
				String termName = null;
				String paidAmount = null;
				String discountAmountStr = null;
				double discountAmount = 0;
				Object[] totalPaidAmount=null;
				Object[] totalTransportPaidAmount=null;
				double totalAmount=0;
				double paymentAmount=0;
				double paymentConcessionAmount=0;
				FileInputStream file = new FileInputStream(getUpload());
				// Get the workbook instance for XLS file
				org.apache.poi.ss.usermodel.Workbook workbook = WorkbookFactory.create(file);
				org.apache.poi.ss.usermodel.Sheet sheet = workbook.getSheetAt(0);

				Row secondRow = sheet.getRow(1);
				if (!ObjectFunctions.isNullOrEmpty(secondRow)) {
					if (!ObjectFunctions.isNullOrEmpty(secondRow.getCell(0)) && !ObjectFunctions.isNullOrEmpty(secondRow.getCell(1))) {
						String fistColumn = secondRow.getCell(0).getStringCellValue();
						String secondColumn = secondRow.getCell(1).getStringCellValue();

						if (!"Admission Number".equalsIgnoreCase(fistColumn.toString()) || !"Invoice Number".equalsIgnoreCase(secondColumn.toString())) {
							log.debug("No file to upload....");
							super.addActionError("File type not matched.");
							return SUCCESS;
						}
						fistColumn = null;
						secondColumn = null;
					} else {
						log.debug("No file to upload....");
						super.addActionError("File type not matched.");
						return SUCCESS;
					}
				} else {
					log.debug("No file to upload....");
					super.addActionError("File type not matched.");
					return SUCCESS;
				}

				Row row = null;
				StringBuffer errorMsg = new StringBuffer();
				// String text = "A";
				Map<String, SchoolTerms> schoolTermsMap = new HashMap<String, SchoolTerms>();
				List<SchoolTerms> termsList = adminManager.getAll(SchoolTerms.class, "custId=" + getUserCustId()+ " and academicYearId="+ getUserAcademicYearId());
				if (!ObjectFunctions.isNullOrEmpty(termsList)) {
					for (SchoolTerms termObj : termsList) {
						schoolTermsMap.put(termObj.getTermName(), termObj);
					}
				}
				DataFormatter objDefaultFormat = new DataFormatter();
				FormulaEvaluator objFormulaEvaluator = new HSSFFormulaEvaluator((HSSFWorkbook) workbook);
				AcademicYear academicYear = (AcademicYear) adminManager.get(AcademicYear.class, getUserAcademicYearId());
				int generatedCount = 0;
				boolean newPayment = true;
				boolean existingPayment = false;
				for (int sheetNum = 0; sheetNum < (workbook.getNumberOfSheets() - 1); sheetNum++) {
					sheet = workbook.getSheetAt(sheetNum);
					log.debug("last row number :" + sheet.getLastRowNum());
					for (int rowNum = 2; rowNum < sheet.getLastRowNum(); rowNum++) {
						row = sheet.getRow(rowNum);
						if (!ObjectFunctions.isNullOrEmpty(row.getCell(0)) && !ObjectFunctions.isNullOrEmpty(row.getCell(1))) {
							if (row.getCell(0).getCellType() != Cell.CELL_TYPE_BLANK) {
								admissionNumber = objDefaultFormat.formatCellValue(row.getCell(0),objFormulaEvaluator);
								invoiceNumber = objDefaultFormat.formatCellValue(row.getCell(1),objFormulaEvaluator);
								paymentDateStr = objDefaultFormat.formatCellValue(row.getCell(2),objFormulaEvaluator);
								termName = objDefaultFormat.formatCellValue(row.getCell(3), objFormulaEvaluator);
								paidAmount = objDefaultFormat.formatCellValue(row.getCell(4), objFormulaEvaluator);
								discountAmountStr = objDefaultFormat.formatCellValue(row.getCell(5),objFormulaEvaluator);
								if (!StringFunctions.isNullOrEmpty(discountAmountStr))
									discountAmount = Double.valueOf(discountAmountStr);
								List<ViewStudentFeePaymentDetails> feeList = null;
								if (!StringFunctions.isNullOrEmpty(admissionNumber) && !StringFunctions.isNullOrEmpty(invoiceNumber) && !ObjectFunctions.isNullOrEmpty(paymentDateStr) && !StringFunctions.isNullOrEmpty(termName) && !StringFunctions.isNullOrEmpty(paidAmount)) {
									DateFormat df = new SimpleDateFormat("dd-MMM-yy");
									Date paymentDate = null;
									try{
										paymentDate = df.parse(paymentDateStr);
										log.debug("paymentDate :" + paymentDate);
									}catch(ParseException pex){
										pex.printStackTrace();
										errorMsg.append("Row number ").append(rowNum).append(" : ").append("Incorrect Payment Date format.").append("<br/>");
									}
									if (StringFunctions.isNotNullOrEmpty(invoiceNumber)) {
										StringBuilder query = new StringBuilder("custId=" + getUserCustId());
										if (academicYear.getReceiptGenerationType().equalsIgnoreCase("A")) {
											if (StringUtils.isNumeric(invoiceNumber)) {
												query.append(" and invoiceNumber='"+ invoiceNumber + "' ");
											} else {
												errorMsg.append("Row number ").append(rowNum).append(" : ").append(invoiceNumber).append(" invoice number allow numaric only ").append("<br/>");
												continue;
											}
										} else
											query.append(" and invoiceString='"+ invoiceNumber + "' ");

										StudentPayment studentPayment = null;

										if (getCustomerByCustId().isAcademicWiseFeeReceipt()) {
											query.append(" and academicYearId="+ getUserAcademicYearId());
										}
										studentPayment = (StudentPayment) adminManager.get(StudentPayment.class,query.toString());
										if (ObjectFunctions.isNullOrEmpty(studentPayment)) {
											studentPayment = new StudentPayment();
											newPayment = false;
											existingPayment = true;
										}
										if(!ObjectFunctions.isNullOrEmpty(paymentDate))
										{
										if (DateFunctions.isFutureDate(paymentDate)) {
											errorMsg.append("Row number ").append(rowNum).append(" : Future payments not allowed. Please check payment date").append("<br/>");
										} else {
											if (newPayment) {
												if (academicYear.getReceiptGenerationType().equalsIgnoreCase("A")) {
													if ((studentPayment.getInvoiceNumber() == Long.valueOf(invoiceNumber)) && studentPayment.getPaymentDateStr().equalsIgnoreCase(DateFormatter.formatDate(DateFormatter.ddMMMyyyy_PATTERN1,paymentDate))) {
														existingPayment = true;
													} else {
														existingPayment = false;
														errorMsg.append("Row number ").append(rowNum).append(" : ").append("Invoice number and payment date not same.").append("<br/>");
													}
												} else {
													if ((studentPayment.getInvoiceString().equalsIgnoreCase(invoiceNumber)) && studentPayment.getPaymentDateStr().equalsIgnoreCase(DateFormatter.formatDate(DateFormatter.ddMMMyyyy_PATTERN1,paymentDate))) {
														existingPayment = true;
													} else {
														existingPayment = false;
														errorMsg.append("Row number ").append(rowNum).append(" : ").append("Invoice number and payment date not same.").append("<br/>");
													}
												}
											}
											if (existingPayment) {
												User user = (User) adminManager.get(User.class,"custId="+ getUserCustId()+ " and admissionNumber='"+ admissionNumber+ "' ");
												if (!ObjectFunctions.isNullOrEmpty(user)) {
													Student student = (Student) adminManager.get(Student.class,"accountId="+ user.getId()+ " and academicYearId="+ getUserAcademicYearId());
													if (!ObjectFunctions.isNullOrEmpty(student) && !ObjectFunctions.isNullOrEmpty(schoolTermsMap.get(termName))) {
														if(Constants.TRANSPORT_FEES.equalsIgnoreCase(schoolTermsMap.get(termName).getFeeSetting().getSettingName()))
															feeList = adminManager.getAllStudentNonPaidClassFeePaymentDetails("vw_studentTransportFeePaymentDetails",student.getId(),getUserAcademicYearId(),schoolTermsMap.get(termName).getId());
														else
															feeList = adminManager.getAllStudentNonPaidClassFeePaymentDetails("vw_studentFeePaymentDetails",student.getId(),getUserAcademicYearId(),schoolTermsMap.get(termName).getId());
														if (!ObjectFunctions.isNullOrEmpty(feeList)) {
															prepareStudentSchoolFeeSettingList(student);
															setFeePaymentType("current");
															studentPayment.setAcademicYear(academicYear);
															studentPayment.setStudent(student);
															// studentPayment.setPaidAmount(Double.valueOf(paidAmount));
															studentPayment.setPaymentDate(paymentDate);
															if (academicYear.getReceiptGenerationType().equalsIgnoreCase("A"))
																studentPayment.setInvoiceNumber(Long.valueOf(invoiceNumber));
															else
																studentPayment.setInvoiceString(invoiceNumber);
															studentPayment.setPaymentType("C");
															setEmpId(String.valueOf(paidAmount)); // Here we are getting paid Amount to show in words.
															StudentPayment payment = schoolFeeManager.generateStudentInvoicetermWise(feeList,getUser().getId(),Double.valueOf(paidAmount),discountAmount,studentPayment);
															StudentPayment updtstudentPayment = (StudentPayment)adminManager.get(StudentPayment.class, payment.getId());
															schoolFeeManager.chashBookPaymentEntry(updtstudentPayment);
															//Siva: This below code is to save term and total balance amounts
															setViewStudentClassDetails((ViewStudentClassDetails)adminManager.get(ViewStudentClassDetails.class,"studId="+student.getId()+" and custId="+getUserCustId()+" and studDiscontinueDesc is null"));
													        if(ObjectFunctions.isNullOrEmpty(getViewStudentClassDetails())){
													        	setViewStudentClassDetails((ViewStudentClassDetails)adminManager.get(ViewStudentClassDetails.class,"studId="+student.getId()+" and custId="+getUserCustId()));
													        }
															
															StringBuilder queryStr = new StringBuilder("select IFNULL(CONVERT(group_concat(schoolTermId), CHAR),0) as schoolTermId  from vw_studentFeePaymentDetails where custId=").append(getUserCustId()).append(" and academicYearId=").append(getViewStudentClassDetails().getAcademicYearId()).append(" and studentId=").append(getViewStudentClassDetails().getStudId()).append(" and feeSettingId !=3");
									    					StringBuilder queryTransportStr = new StringBuilder("select IFNULL(CONVERT(group_concat(schoolTermId), CHAR),0) as schoolTermId  from vw_studentTransportFeePaymentDetails where custId=").append(getUserCustId()).append(" and academicYearId=").append(getViewStudentClassDetails().getAcademicYearId()).append(" and studentId=").append(getViewStudentClassDetails().getStudId());
									    					if (academicYear.getReceiptGenerationType().equalsIgnoreCase("A")){
									    						queryStr.append(" and invoiceNumber=" + updtstudentPayment.getInvoiceNumber());
									    						queryTransportStr.append(" and invoiceNumber=" + updtstudentPayment.getInvoiceNumber());
									    					}else{
									    						queryStr.append(" and invoiceString='"+ updtstudentPayment.getInvoiceString() + "'");
									    						queryTransportStr.append(" and invoiceString='"+ updtstudentPayment.getInvoiceString() + "'");
									    					}
									    					List<String> invoiceTermIds = adminManager.getAll(queryStr.toString());
									    					List<String> invoiceTransportTermIds = adminManager.getAll(queryTransportStr.toString());
									    					if(!ObjectFunctions.isNullOrEmpty(invoiceTransportTermIds))
									    						invoiceTermIds.addAll(invoiceTransportTermIds);
									    					invoiceTransportTermIds=null;
									    					Object[] invoiceTermTotalAmount = adminManager.get("select ifNULL(sum(feeAmount),0) as feeAmount,feeId from vw_studentClassFees where custId="+getUserCustId()+" and academicYearId="+getViewStudentClassDetails().getAcademicYearId()+" and studentId="+getViewStudentClassDetails().getStudId()+" and schoolTermId in ("+StringUtil.convertListToString(invoiceTermIds)+") and description is null");
									    					Object[] invoiceTransTermTotalAmount =adminManager.get("select ifNULL(sum(feeAmount),0) as feeAmount,transportFeeId from vw_studentTransportFees where custId="+getUserCustId()+" and academicYearId="+getViewStudentClassDetails().getAcademicYearId()+" and studentId="+getViewStudentClassDetails().getStudId()+" and schoolTermId in ("+StringUtil.convertListToString(invoiceTermIds)+") and description is null");
									    					if(!ObjectFunctions.isNullOrEmpty(invoiceTermTotalAmount)){
									    						totalAmount=Double.valueOf(invoiceTermTotalAmount[0].toString());
									                      		 if(!ObjectFunctions.isNullOrEmpty(invoiceTransTermTotalAmount)){
									                      			totalAmount +=Double.valueOf(invoiceTransTermTotalAmount[0].toString());
									                      		 }
									    						Object[]  totalTermPaidAmount= adminManager.get("select IFNULL(sum(IFNULL(paymentAmount,0)),0) as paymentAmount,IFNULL(sum(IFNULL(discountAmount,0)),0) as discountAmount,IFNULL(sum(IFNULL(paymentConcessionAmount,0)),0) as paymentConcessionAmount,id,termwiseTotalBalanceAmount,totalBalanceAmount from vw_studentFeePaymentDetails where custId="+getUserCustId()+" and academicYearId="+getViewStudentClassDetails().getAcademicYearId()+" and studentId="+getViewStudentClassDetails().getStudId()+" and schoolTermId in ("+StringUtil.convertListToString(invoiceTermIds)+")  and description is null");
									    						Object[]   totalTransportTermPaidAmount = adminManager.get("select IFNULL(sum(IFNULL(paymentAmount,0)),0) as paymentAmount,IFNULL(sum(IFNULL(discountAmount,0)),0) as discountAmount,IFNULL(sum(IFNULL(paymentConcessionAmount,0)),0) as paymentConcessionAmount,id,termwiseTotalBalanceAmount,totalBalanceAmount from vw_studentTransportFeePaymentDetails where custId="+getUserCustId()+" and academicYearId="+getViewStudentClassDetails().getAcademicYearId()+" and studentId="+getViewStudentClassDetails().getStudId()+" and schoolTermId in ("+StringUtil.convertListToString(invoiceTermIds)+")  and description is null");
									    						paymentAmount=(Double.valueOf(totalTermPaidAmount[0].toString())+Double.valueOf(totalTransportTermPaidAmount[0].toString()));
									    						discountAmount=(Double.valueOf(totalTermPaidAmount[1].toString())+Double.valueOf(totalTransportTermPaidAmount[1].toString()));
									                      		paymentConcessionAmount=(Double.valueOf(totalTermPaidAmount[2].toString())+Double.valueOf(totalTransportTermPaidAmount[2].toString()));
									                      		//setPlSubTopic(String.valueOf(totalAmount-(paymentAmount+discountAmount+paymentConcessionAmount)));
									                      		updtstudentPayment.setTermwiseTotalBalanceAmount(totalAmount-(paymentAmount+discountAmount+paymentConcessionAmount));	
									                      		
									                      		
									                      		String sqlquery="select IFNULL(sum(IFNULL(feeAmount,0)),0) as feeAmount,IFNULL(sum(IFNULL(paymentAmount,0)),0) as paymentAmount,IFNULL(sum(IFNULL(discountAmount,0)),0) as discountAmount,IFNULL(sum(IFNULL(concessionAmount,0)),0) as concessionAmount "
									        							+ "from vw_studentFeeParticularsPayment where custId="+getUserCustId()+" and academicYearId="+getViewStudentClassDetails().getAcademicYearId()+" and studentId="+getViewStudentClassDetails().getStudId()+" and deleteStatus='"+Constants.NO_STRING+"' and description is null and feeSettingId != 3";
									        					String sqlTransQuery="select IFNULL((select SUM(IFNULL(feeAmount,0)) as feeAmount from vw_studentTransportFees where custId="+getUserCustId()+" and studentId="+getViewStudentClassDetails().getStudId()+" and academicYearId="+getAcademicYearId()+"),0) as feeAmount,IFNULL(sum(IFNULL(paymentAmount,0)),0) as paymentAmount,IFNULL(sum(IFNULL(discountAmount,0)),0) as discountAmount,IFNULL((select SUM(IFNULL(concessionAmount,0)) as concessionAmount from vw_studentTransportFees where custId="+getUserCustId()+" and studentId="+getViewStudentClassDetails().getStudId()+" and academicYearId="+getAcademicYearId()+"),0) as concessionAmount "
									        							+ "from vw_studentTransportFeePaymentDetails where custId="+getUserCustId()+" and academicYearId="+getViewStudentClassDetails().getAcademicYearId()+" and studentId="+getViewStudentClassDetails().getStudId()+"  and description is null and deleteStatus='"+Constants.NO_STRING+"' ";
									                       		 
									                       		 
									                       		 totalPaidAmount= adminManager.get(sqlquery);
									                       		 totalTransportPaidAmount = adminManager.get(sqlTransQuery);
									                       		 totalAmount = (Double.valueOf(totalPaidAmount[0].toString())+Double.valueOf(totalTransportPaidAmount[0].toString()));
									                       		 paymentAmount=(Double.valueOf(totalPaidAmount[1].toString())+Double.valueOf(totalTransportPaidAmount[1].toString()));
									                       		 discountAmount=(Double.valueOf(totalPaidAmount[2].toString())+Double.valueOf(totalTransportPaidAmount[2].toString()));
									                       		 paymentConcessionAmount=(Double.valueOf(totalPaidAmount[3].toString())+Double.valueOf(totalTransportPaidAmount[3].toString()));
									                       		 //setTempString3(String.valueOf(totalAmount-(paymentAmount+discountAmount+paymentConcessionAmount)));
									                       		 updtstudentPayment.setTotalBalanceAmount(totalAmount-(paymentAmount+discountAmount+paymentConcessionAmount));
									                       		 updtstudentPayment =(StudentPayment)adminManager.saveOrUpdateObject(updtstudentPayment);
									                       		 updtstudentPayment=null;
									    					}
															//End
															generatedCount++;
															// Sending Fee Payment notification to mobile
															if(!ObjectFunctions.isNullOrEmpty(user.getStudentParent()))
																adminManager.sendNotificationForFeePayment(student.getCustId(),student.getId(),Long.valueOf(user.getStudentParent().getId()),"P");
														} else {
															errorMsg.append("Row number ").append(rowNum).append(" : student already paid fee to this term or not configured fee to this term.").append("<br/>");
															// log.debug("student already paid fee to this term or not conficured fee to this term.");
														}
														student=null;
													} else {
														errorMsg.append("Row number ").append(rowNum).append(" : ").append("Student or Term Name not available.").append("<br/>");
													}
												} else {
													errorMsg.append("Row number ").append(rowNum).append(" : admission number not available.").append("<br/>");
													// log.debug("admission number not available to this user.");
												}
											}

										}
										}
										invoiceNumber = null;
										studentPayment=null;
									}
									admissionNumber = null;
									invoiceNumber = null;
									paymentDateStr = null;
									termName = null;
									paidAmount = null;
									discountAmountStr = null;
									discountAmount = 0;
									paymentDate = null;
								} else {
									errorMsg.append("Row number ").append(rowNum).append(" : All mandatory fields should enter.").append("<br/>");
									log.debug("manadatary fields should be given in excel sheet.");
								}
							} else
								log.debug("Some data you entered wrong please check once.");
						}
						newPayment = true;
						existingPayment = false;
					}
					sheet = null;
				}
				if (generatedCount > 0)
					super.addActionMessage("Payment's uploaded successfully.");

				if (errorMsg.length() > 0) {
					errorMsg.insert(0,"Few student(s) data not valid. Please verify the data row number wise : <br/>");
					super.addActionError(errorMsg.toString());

				}
				errorMsg = null;
			}
		} catch (Exception ex) {
			log.error("Entering into 'catch block':" + ex.getMessage());
			super.addActionError("Student invoice generation failed due to wrong data.");
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
		}
		return SUCCESS;
	}

	@Actions({
			@Action(value = "ajaxGetTodayFeeCollection", results = { @Result(location = "ajaxViewToadyFeePaidDetails.jsp", name = "success") }),
			@Action(value = "ajaxFeeCollectionByDate", results = { @Result(location = "ajaxViewTodayFeeCollections.jsp", name = "success") }) })
	public String ajaxGetToadyFeeDetails() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxGetToadyFeeDetails' method");
		}
		try {
			String todayDate = null;
			if (!ObjectFunctions.isNullOrEmpty(getStartDate())) {
				todayDate = DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, getStartDate());
			} else {
				setAttendanceDate(DateFormatter.getTodayDateStr(DateFormatter.MMDDCCYY_PATTERN));
				todayDate = DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, new Date());
			}
			//setStudentPaymentList(adminManager.getAll("SELECT DATE_FORMAT(paymentDate, '%d-%m-%Y') as paymentDateStr,fullName,concat(if(isnull(className), '', className), if((isnull(section) or (section = '')), '',concat(' - ', section))) AS classAndSection,admissionNumber,paidAmount,discountAmount,DATE_FORMAT(dueDate, '%d-%m-%Y') as dueDateStr,invoiceNumber from vw_studentFeePaymentDetails where paymentDate like '%"+ todayDate+ "%'  and deleteStatus='"+ Constants.NO_STRING+ "' and custId="+ getUserCustId()+ " and academicYearId="+ getUserAcademicYearId()+ "  and paymentCommitFeeStatus='N' and description is null group by invoiceNumber"));
			
			setStudentPaymentList(adminManager.getAll(StudentPayment.class,"custId="+ getUserCustId()+ " and academicYearId="+ getUserAcademicYearId()+ " and paymentDate like '%"+ todayDate+ "%'  and deleteStatus='"+ Constants.NO_STRING+ "' and concessionStatus='N' "));
			Object[] paidAmount=adminManager.get("select id,IFNULL(sum(sp.paidAmount),0) as paymentAmount from studentPayment sp where sp.custId="+getUserCustId()+"  and sp.academicYearId="+getUserAcademicYearId()+" and sp.paymentDate like '%"+ todayDate+ "%' and sp.deleteStatus='N' and sp.concessionStatus='N' ");
		    if(!ObjectFunctions.isNullOrEmpty(paidAmount)){
				setTotalAmount(Double.valueOf(paidAmount[1].toString()));
		    }
			/*Ganesh - Date wise "Other Fee" collection details. */
			setClassFeeCountList(adminManager.getAll("SELECT DATE_FORMAT(paymentDate, '%d-%m-%Y') as paymentDateStr,studentFullName,classAndSection,admissionNumber,sum(fineFeeAmount) as fineFeeAmount,invoiceNumber from vw_studentFineFeeDetails where custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and paymentDate like '%"+ todayDate+ "%' and deleteStatus='N' group by invoiceNumber"));
		    Object[] fineAmount=adminManager.get("select id,IFNULL(sum(fa.fineFeeAmount),0) as fineFeeAmount from fineFee fa where fa.custId="+getUserCustId()+"  and fa.academicYearId="+getUserAcademicYearId()+" and fa.paymentDate like '%"+ todayDate+ "%' and fa.deleteStatus='N' ");
		    if(!ObjectFunctions.isNullOrEmpty(fineAmount)){
				setThirtyTotalAmount(Double.valueOf(fineAmount[1].toString()));
		    }
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
		}
		return SUCCESS;
	}

	@Actions({ @Action(value = "ajaxManageGeneratChallana", results = { @Result(location = "ajaxManageStudentChallanaDetails.jsp", name = "success") }) })
	public String ajaxManageGeneratChallana() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxManageGeneratChallana' method");
		}
		try {
			log.debug("Start "+new Date());
			setObjectList(adminManager.getAll(ViewStudentFeeChallanaDetails.class, "custId="+ getUserCustId() + " and academicYearId="+ getUserAcademicYearId() + " and deleteStatus='C' group by studentPaymentId order by challanaDate DESC"));
			if(getCustomerByCustId().getShowPreviousYearPendingFee().equalsIgnoreCase("Y")){
				checkPreviousAcademicYearPensingStudentFee();
				setAnnualYearlyClassList(adminManager.getAll(ViewStudentFeeChallanaDetails.class, "custId="+ getUserCustId() + " and academicYearId="+ getTempId() + " and deleteStatus='C' group by studentPaymentId order by challanaDate DESC"));
			}
			
			log.debug("End "+new Date());
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
		}
		return SUCCESS;
	}
	
	@Actions({ @Action(value = "ajaxVerifyStudentChallana", results = { @Result(location = "ajaxStudentChallanaInvoicePayment.jsp", name = "success") }) })
	public String ajaxVerifyStudentChallana() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxVerifyStudentChallana' method");
		}
		try {
			//setChallanaPayment((ChallanaPayment)adminManager.get(ChallanaPayment.class, "custId="+ getUserCustId() + " and academicYearId="+ getUserAcademicYearId() + " and studentId="+getStudentNumber()+" and challanaNumber="+getStudentPayment().getInvoiceNumber()));
			if(Long.valueOf(getStudentNumber())>0 && getUserVo().getId()>0){
				setObjectList(adminManager.getAll(ViewStudentFeeChallanaDetails.class, "custId="+ getUserCustId() + " and academicYearId="+ getStudentPayment().getAcademicYear().getId() + " and studentId="+getStudentNumber()+" and challanaNumber="+getStudentPayment().getInvoiceNumber()+" order by feeSettingId,schoolTermId"));
				StringBuffer query = null;
				Object[] excessAmt = null;
				setTodayDate(DateFormatter.formatDate(DateFormatter.MMDDCCYY_PATTERN, new Date()));
					
				query = new StringBuffer("select SUM(excessAmount),status from excessPayment where accountId=").append(getUserVo().getId()).append(" and status='N'");
				excessAmt = adminManager.get(query.toString());
				//StudentFeeConcession studentFeeConcession =(StudentFeeConcession)adminManager.get(StudentFeeConcession.class, "custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and studentId="+getStudent().getId()+" and concessionAmount!=0");
				long studsCount = adminManager.getCount("studentFeeConcession","custId="+getUserCustId()+" and academicYearId="+getStudentPayment().getAcademicYear().getId() +" and studentId="+getStudentNumber()+" and concessionAmount!=0");
				if(studsCount>0){
					setTempBoolean(true);
				}
				if(!ObjectFunctions.isNullOrEmpty(excessAmt) && !ObjectFunctions.isNullOrEmpty(excessAmt[0])){
					setBalance(excessAmt[0].toString());
				}
			}
			
		
			
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
		}
		return SUCCESS;
	}

	@Action(value = "ajaxPayStudentChallanaPaymentFee", results = { @Result(location = "ajaxManageStudentChallanaDetails.jsp", name = "success") })
	public String ajaxPayStudentChallanaPaymentFee() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxPayStudentChallanaPaymentFee' method");
		}
		try {
			StudentPayment studentPayment =null;
			Object[] totalPaidAmount=null;
			Object[] totalTransportPaidAmount=null;
			double totalAmount=0;
			double paymentAmount=0;
			double discountAmount=0;
			double paymentConcessionAmount=0;
			Object[] paidAmount= null;
			if (StringFunctions.isNotNullOrEmpty(getStudentNumber()) && StringFunctions.isNotNullOrEmpty(getAnyId()) && StringFunctions.isNotNullOrEmpty(getPaymentType())) {
				long feeAcademicYearId = 0;
				if (getTempId1() != getUserAcademicYearId() && getTempId1() != 0) {
					feeAcademicYearId = getTempId1();
					setAcademicYearId(feeAcademicYearId);
				} else {
					feeAcademicYearId = getUserAcademicYearId();
				}
				Date paymentDate = DateFormatter.parseString(DateFormatter.YYYY_MM_DD_PATTERN, DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN,new Date()));
				AcademicYear academicYear = (AcademicYear) adminManager.get(AcademicYear.class, feeAcademicYearId);
				Customer customer = getCustomerByCustId();
				Student student = null;
				setTodayDate(DateFormatter.formatDate(DateFormatter.ddMMMyyyy_PATTERN1, paymentDate));// For printing the receipt
				if (!ObjectFunctions.isNullOrEmpty(academicYear)) {
					student = (Student) adminManager.get(Student.class,"custId=" + getUserCustId()+ " and academicYearId="+ feeAcademicYearId + " and id="+ Long.valueOf(getStudentNumber()));
					if (!ObjectFunctions.isNullOrEmpty(student)) {
						ChallanaPayment challanaPayment = (ChallanaPayment) adminManager.get(ChallanaPayment.class,getChallanaPayment().getId());
						if (!ObjectFunctions.isNullOrEmpty(challanaPayment)) {
							studentPayment = (StudentPayment)adminManager.get(StudentPayment.class, getStudentPayment().getId());
							if(!ObjectFunctions.isNullOrEmpty(studentPayment)){
								if(!ObjectFunctions.isNullOrEmpty(studentPayment.getStudentFeePaidDetails())){
									for(StudentFeePaidDetails studentFeePaidDetails : studentPayment.getStudentFeePaidDetails()){
										studentFeePaidDetails.setDeleteStatus(Constants.NO_STRING);
										adminManager.save(studentFeePaidDetails);
										studentFeePaidDetails=null;
									}
								}
								studentPayment.setLastUpdatedDate(new Date());
								studentPayment.setLastUpdatedById(getUser().getId());
								studentPayment.setPaymentDate(DateFormatter.parseString(DateFormatter.YYYY_MM_DD_PATTERN, DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, new Date())));
								studentPayment.setDeleteStatus(Constants.NO_STRING);
								studentPayment =(StudentPayment)adminManager.saveOrUpdateObject(studentPayment);
								
								//Siva: This below code is to save term and total balance amounts
								setViewStudentClassDetails((ViewStudentClassDetails)adminManager.get(ViewStudentClassDetails.class,"studId="+getStudentNumber()+" and custId="+getUserCustId()+" and studDiscontinueDesc is null"));
						        if(ObjectFunctions.isNullOrEmpty(getViewStudentClassDetails())){
						        	setViewStudentClassDetails((ViewStudentClassDetails)adminManager.get(ViewStudentClassDetails.class,"studId="+getStudentNumber()+" and custId="+getUserCustId()));
						        }
								
								StringBuilder queryStr = new StringBuilder("select IFNULL(CONVERT(group_concat(schoolTermId), CHAR),0) as schoolTermId  from vw_studentFeePaymentDetails where custId=").append(getUserCustId()).append(" and academicYearId=").append(getViewStudentClassDetails().getAcademicYearId()).append(" and studentId=").append(getViewStudentClassDetails().getStudId()).append(" and feeSettingId !=3");
		    					StringBuilder queryTransportStr = new StringBuilder("select IFNULL(CONVERT(group_concat(schoolTermId), CHAR),0) as schoolTermId  from vw_studentTransportFeePaymentDetails where custId=").append(getUserCustId()).append(" and academicYearId=").append(getViewStudentClassDetails().getAcademicYearId()).append(" and studentId=").append(getViewStudentClassDetails().getStudId());
		    					if (academicYear.getReceiptGenerationType().equalsIgnoreCase("A")){
		    						queryStr.append(" and invoiceNumber=" + studentPayment.getInvoiceNumber());
		    						queryTransportStr.append(" and invoiceNumber=" + studentPayment.getInvoiceNumber());
		    					}else{
		    						queryStr.append(" and invoiceString='"+ studentPayment.getInvoiceString() + "'");
		    						queryTransportStr.append(" and invoiceString='"+ studentPayment.getInvoiceString() + "'");
		    					}
		    					List<String> invoiceTermIds = adminManager.getAll(queryStr.toString());
		    					List<String> invoiceTransportTermIds = adminManager.getAll(queryTransportStr.toString());
		    					if(!ObjectFunctions.isNullOrEmpty(invoiceTransportTermIds))
		    						invoiceTermIds.addAll(invoiceTransportTermIds);
		    					invoiceTransportTermIds=null;
		    					Object[] invoiceTermTotalAmount = adminManager.get("select ifNULL(sum(feeAmount),0) as feeAmount,feeId from vw_studentClassFees where custId="+getUserCustId()+" and academicYearId="+getViewStudentClassDetails().getAcademicYearId()+" and studentId="+getViewStudentClassDetails().getStudId()+" and schoolTermId in ("+StringUtil.convertListToString(invoiceTermIds)+") and description is null");
		    					Object[] invoiceTransTermTotalAmount =adminManager.get("select ifNULL(sum(feeAmount),0) as feeAmount,transportFeeId from vw_studentTransportFees where custId="+getUserCustId()+" and academicYearId="+getViewStudentClassDetails().getAcademicYearId()+" and studentId="+getViewStudentClassDetails().getStudId()+" and schoolTermId in ("+StringUtil.convertListToString(invoiceTermIds)+") and description is null");
		    					if(!ObjectFunctions.isNullOrEmpty(invoiceTermTotalAmount)){
		    						totalAmount=Double.valueOf(invoiceTermTotalAmount[0].toString());
		                      		 if(!ObjectFunctions.isNullOrEmpty(invoiceTransTermTotalAmount)){
		                      			totalAmount +=Double.valueOf(invoiceTransTermTotalAmount[0].toString());
		                      		 }
		    						Object[]  totalTermPaidAmount= adminManager.get("select IFNULL(sum(IFNULL(paymentAmount,0)),0) as paymentAmount,IFNULL(sum(IFNULL(discountAmount,0)),0) as discountAmount,IFNULL(sum(IFNULL(paymentConcessionAmount,0)),0) as paymentConcessionAmount,id,termwiseTotalBalanceAmount,totalBalanceAmount from vw_studentFeePaymentDetails where custId="+getUserCustId()+" and academicYearId="+getViewStudentClassDetails().getAcademicYearId()+" and studentId="+getViewStudentClassDetails().getStudId()+" and schoolTermId in ("+StringUtil.convertListToString(invoiceTermIds)+")  and description is null");
		    						Object[]   totalTransportTermPaidAmount = adminManager.get("select IFNULL(sum(IFNULL(paymentAmount,0)),0) as paymentAmount,IFNULL(sum(IFNULL(discountAmount,0)),0) as discountAmount,IFNULL(sum(IFNULL(paymentConcessionAmount,0)),0) as paymentConcessionAmount,id,termwiseTotalBalanceAmount,totalBalanceAmount from vw_studentTransportFeePaymentDetails where custId="+getUserCustId()+" and academicYearId="+getViewStudentClassDetails().getAcademicYearId()+" and studentId="+getViewStudentClassDetails().getStudId()+" and schoolTermId in ("+StringUtil.convertListToString(invoiceTermIds)+")  and description is null");
		    						paymentAmount=(Double.valueOf(totalTermPaidAmount[0].toString())+Double.valueOf(totalTransportTermPaidAmount[0].toString()));
		    						discountAmount=(Double.valueOf(totalTermPaidAmount[1].toString())+Double.valueOf(totalTransportTermPaidAmount[1].toString()));
		                      		paymentConcessionAmount=(Double.valueOf(totalTermPaidAmount[2].toString())+Double.valueOf(totalTransportTermPaidAmount[2].toString()));
		                      		//setPlSubTopic(String.valueOf(totalAmount-(paymentAmount+discountAmount+paymentConcessionAmount)));
		                      		studentPayment.setTermwiseTotalBalanceAmount(totalAmount-(paymentAmount+discountAmount+paymentConcessionAmount));	
		                      		
		                      		
		                      		String sqlquery="select IFNULL(sum(IFNULL(feeAmount,0)),0) as feeAmount,IFNULL(sum(IFNULL(paymentAmount,0)),0) as paymentAmount,IFNULL(sum(IFNULL(discountAmount,0)),0) as discountAmount,IFNULL(sum(IFNULL(concessionAmount,0)),0) as concessionAmount "
		        							+ "from vw_studentFeeParticularsPayment where custId="+getUserCustId()+" and academicYearId="+getViewStudentClassDetails().getAcademicYearId()+" and studentId="+getViewStudentClassDetails().getStudId()+" and deleteStatus='"+Constants.NO_STRING+"' and description is null and feeSettingId != 3";
		        					String sqlTransQuery="select IFNULL((select SUM(IFNULL(feeAmount,0)) as feeAmount from vw_studentTransportFees where custId="+getUserCustId()+" and studentId="+getViewStudentClassDetails().getStudId()+" and academicYearId="+getAcademicYearId()+"),0) as feeAmount,IFNULL(sum(IFNULL(paymentAmount,0)),0) as paymentAmount,IFNULL(sum(IFNULL(discountAmount,0)),0) as discountAmount,IFNULL((select SUM(IFNULL(concessionAmount,0)) as concessionAmount from vw_studentTransportFees where custId="+getUserCustId()+" and studentId="+getViewStudentClassDetails().getStudId()+" and academicYearId="+getAcademicYearId()+"),0) as concessionAmount "
		        							+ "from vw_studentTransportFeePaymentDetails where custId="+getUserCustId()+" and academicYearId="+getViewStudentClassDetails().getAcademicYearId()+" and studentId="+getViewStudentClassDetails().getStudId()+"  and description is null and deleteStatus='"+Constants.NO_STRING+"' ";
		                       		 
		                       		 
		                       		 totalPaidAmount= adminManager.get(sqlquery);
		                       		 totalTransportPaidAmount = adminManager.get(sqlTransQuery);
		                       		 totalAmount = (Double.valueOf(totalPaidAmount[0].toString())+Double.valueOf(totalTransportPaidAmount[0].toString()));
		                       		 paymentAmount=(Double.valueOf(totalPaidAmount[1].toString())+Double.valueOf(totalTransportPaidAmount[1].toString()));
		                       		 discountAmount=(Double.valueOf(totalPaidAmount[2].toString())+Double.valueOf(totalTransportPaidAmount[2].toString()));
		                       		 paymentConcessionAmount=(Double.valueOf(totalPaidAmount[3].toString())+Double.valueOf(totalTransportPaidAmount[3].toString()));
		                       		 //setTempString3(String.valueOf(totalAmount-(paymentAmount+discountAmount+paymentConcessionAmount)));
		                       		 studentPayment.setTotalBalanceAmount(totalAmount-(paymentAmount+discountAmount+paymentConcessionAmount));
		                       		studentPayment =(StudentPayment)adminManager.saveOrUpdateObject(studentPayment);
		    					}
								//End
								
								challanaPayment.setLastUpdatedById(getUser().getId());
								challanaPayment.setLastUpdatedDate(new Date());
								challanaPayment.setReceivedDate(new Date());
								challanaPayment.setDeleteStatus(Constants.YES_STRING);
								challanaPayment.setStudentPayment(studentPayment);
								adminManager.save(challanaPayment);
								setEmpId(String.valueOf(studentPayment.getPaidAmount()));
								if("Y".equalsIgnoreCase(customer.getAccountModuleUsing()))
									schoolFeeManager.chashBookPaymentEntry(studentPayment);
							}
							challanaPayment=null;
						}
						// Sending Fee Payment notification to mobile
						if(!ObjectFunctions.isNullOrEmpty(student.getAccount().getStudentParent()))
						adminManager.sendNotificationForFeePayment(student.getCustId(),student.getId(),ObjectFunctions.isNullOrEmpty(student.getAccount().getStudentParent().getId()) ? 0 : Long.valueOf(student.getAccount().getStudentParent().getId()),"P");
						setStudentNumber(getStudentNumber());// For printing the receipt

						if ("Pay".equalsIgnoreCase(getPlSubTopic())) {
							ViewStudentClassDetails viewStudentClassDetails = (ViewStudentClassDetails) adminManager.get(ViewStudentClassDetails.class,"studId="+ getStudentNumber()+ " and custId="+ getUserCustId()+ " and studDiscontinueDesc is null");
							if (!ObjectFunctions.isNullOrEmpty(viewStudentClassDetails)) {
								paidAmount = null;
								if (academicYear.getReceiptGenerationType().equalsIgnoreCase("A")) {
									setStudentPaymentList(adminManager.getAll(ViewStudentFeePaymentDetails.class," studentId="+ viewStudentClassDetails.getStudId()+ " and deleteStatus='"+ Constants.NO_STRING+ "' and invoiceNumber="+ String.valueOf(getStudentPayment().getInvoiceNumber())+ " and paymentCommitFeeStatus='"+ Constants.NO_STRING+ "' order by schoolTermId,feeTypeId"));
									setStudentTransportTermsList(adminManager.getAll(ViewStudentTransportFeePaymentDetails.class," studentId="+ viewStudentClassDetails.getStudId()+ " and deleteStatus='"+ Constants.NO_STRING+ "' and invoiceNumber="+ String.valueOf(getStudentPayment().getInvoiceNumber())+ " and paymentCommitFeeStatus='"+ Constants.NO_STRING+ "' order by schoolTermId,feeTypeId"));
									paidAmount = adminManager.get("select sp.paidAmount,sp.discountAmount,IFNULL(ep.excessAmount,0) as excessAmount,sp.id,sp.discountDesc,sum(uep.excessAmount) as usedExcessAmount,sp.desktopReceiptNumber  from studentPayment sp LEFT JOIN excessPayment ep on (sp.id=ep.paymentId) LEFT JOIN excessPayment uep on (sp.id=uep.usedPaymentId) where sp.custId="+ getUserCustId()+ " and sp.invoiceNumber="+ String.valueOf(getStudentPayment().getInvoiceNumber())+ " and sp.studentId="+ Long.valueOf(getStudentNumber()));
								} else {
									setStudentPaymentList(adminManager.getAll(ViewStudentFeePaymentDetails.class," studentId="+ viewStudentClassDetails.getStudId()+ " and deleteStatus='"+ Constants.NO_STRING+ "' and invoiceString='"+ String.valueOf(getStudentPayment().getInvoiceString())+ "' and paymentCommitFeeStatus='"+ Constants.NO_STRING+ "' order by schoolTermId,feeTypeId"));
									setStudentTransportTermsList(adminManager.getAll(ViewStudentTransportFeePaymentDetails.class," studentId="+ viewStudentClassDetails.getStudId()+ " and deleteStatus='"+ Constants.NO_STRING+ "' and invoiceString='"+ String.valueOf(getStudentPayment().getInvoiceString())+ "' and paymentCommitFeeStatus='"+ Constants.NO_STRING+ "' order by schoolTermId,feeTypeId"));
									paidAmount = adminManager.get("select sp.paidAmount,sp.discountAmount,IFNULL(ep.excessAmount,0) as excessAmount,sp.id,sp.discountDesc,sum(uep.excessAmount) as usedExcessAmount,sp.invoiceString  from studentPayment sp LEFT JOIN excessPayment ep on (sp.id=ep.paymentId) LEFT JOIN excessPayment uep on (sp.id=uep.usedPaymentId) where sp.custId="+ getUserCustId()+ " and sp.invoiceString='"+ String.valueOf(getStudentPayment().getInvoiceString())+ "' and sp.studentId="+ Long.valueOf(getStudentNumber()));
								}
								if (!ObjectFunctions.isNullOrEmpty(paidAmount)) {
									if (Double.valueOf(paidAmount[1].toString()) != 0)
										setThirtyTotalAmount(Double.valueOf(paidAmount[1].toString()));
									if (Double.valueOf(paidAmount[2].toString()) != 0)
										setWishTitle(paidAmount[2].toString());
									setTotalAmount(Double.valueOf(paidAmount[0].toString()));
									if (!ObjectFunctions.isNullOrEmpty(paidAmount[4]))
										setQueryString(paidAmount[4].toString()); // this is stored to discount reason desc
									if (!ObjectFunctions.isNullOrEmpty(paidAmount[5])) {
										if (Double.valueOf(paidAmount[5].toString()) != 0)
											setBalance(paidAmount[5].toString());
									}

									// if user use desk top payment we have to show desktop invoice number
									if (!ObjectFunctions.isNullOrEmpty(paidAmount[6])) {
										setAnyId(paidAmount[6].toString());
									}
								}
								if (academicYear.getReceiptGenerationType().equalsIgnoreCase("A"))
									ajaxGenerateFeeReceiptPDFReport(viewStudentClassDetails,getStudentPaymentList(),getTotalAmount(),studentPayment,getTempString(),customer,academicYear,student,getStudentTransportTermsList());
								else
									ajaxGenerateFeeReceiptPDFReport(viewStudentClassDetails,getStudentPaymentList(),getTotalAmount(),studentPayment,getTempString(),customer,academicYear,student,getStudentTransportTermsList());
								studentPayment=null;
							}
						}
						adminManager.checkStudentFeePaidStatus(academicYear.getId(),getUserCustId(),student);
						super.addActionMessage("Challan verified successfully.");
					}
				}
				customer=null;
				academicYear = null;
				student = null;
				setTempId2(getStudentPayment().getInvoiceNumber());// For printing the receipt
				setAlertSendType(getStudentPayment().getInvoiceString()); // Using alertSendType when u select Fee Generation type Is manual show the receipt Number in pdf format
			}
		} catch (Exception ex) {
			log.error("Entering into 'catch block':" + ex.getMessage());
			ex.printStackTrace(); RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
		} finally {
			ajaxManageGeneratChallana();
		}
		return SUCCESS;
	}

	@Actions({ @Action(value = "ajaxRemoveChallanaDetails", results = { @Result(location = "ajaxManageStudentChallanaDetails.jsp", name = "success") }) })
	public String ajaxRemoveChallanaDetails() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxRemoveChallanaDetails' method");
		}
		try {
			if (getChallanaPayment().getId() > 0) {
				ChallanaPayment challanaPayment = (ChallanaPayment)adminManager.get(ChallanaPayment.class, getChallanaPayment().getId());
				if(!ObjectFunctions.isNullOrEmpty(challanaPayment)){
					int removedCount = adminManager.remove("studentFeePaidDetails", "studentPaymentId="+ getChallanaPayment().getStudentPayment().getId()+" and deleteStatus='C' ");
					if (removedCount > 0) {
						removedCount = adminManager.remove("challanaPayment", "id="+ getChallanaPayment().getId());
						removedCount = adminManager.remove("studentPayment", "id="+ getChallanaPayment().getStudentPayment().getId());
						if (removedCount > 0)
							super.addActionMessage("Challana removed successfully.");
					} else
						super.addActionError("Challana not removed. Please contact administrator.");
					challanaPayment=null;
				}
				
				
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
		} finally {
			ajaxManageGeneratChallana();
		}
		return SUCCESS;
	}
	
	@Actions({ @Action(value = "deleteInvoiceCustomerStudentWiseDetails", results = { @Result(location = "deleteInvoice/deleteInvoiceSuperAdminHome.jsp", name = "success") }) })
	public String deleteInvoiceCustomerStudentWiseDetails() {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'deleteInvoiceCustomerStudentWiseDetails' method");
		}
		try {
			setCustomerList(adminManager.getAll(Customer.class,"status='Y' "));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	@Actions({ @Action(value = "ajaxSearchStudentByAdmissionNumber", results = { @Result(location = "deleteInvoice/ajaxViewDeleteStudentFeeDetails.jsp", name = "success") ,
			 																	@Result(location = "deleteInvoice/ajaxViewDeleteStudentsList.jsp", name = "multipleStudents") 
	}) })
	public String ajaxSearchStudentByAdmissionNumber() {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxSearchStudentByAdmissionNumber' method");
		}
		try {
			if(getTempId()>0){
				AcademicYear academicYear = adminManager.getCurrentAcademicYear(Constants.YES_STRING, getTempId());
				if(!ObjectFunctions.isNullOrEmpty(academicYear)){
					
					List<ViewStudentClassDetails>  viewStudentClassDetailsList = adminManager.getAll(ViewStudentClassDetails.class, "custId="+getTempId()+" and academicYearId="+academicYear.getId()+" and admissionNumber='"+getAnyTitle()+"' and studDiscontinueDesc is null");
					if(!ObjectFunctions.isNullOrEmpty(viewStudentClassDetailsList))
					{
						if(viewStudentClassDetailsList.size() > 1)
						{
							setObjectList(viewStudentClassDetailsList);
							return "multipleStudents";
						}
						else
						{
							setViewStudentClassDetails((ViewStudentClassDetails)viewStudentClassDetailsList.get(0));
							if(!ObjectFunctions.isNullOrEmpty(getViewStudentClassDetails())){
								StringBuffer query = new StringBuffer(" studentId="+getViewStudentClassDetails().getStudId()+" and custId="+getTempId()+" and deleteStatus='"+Constants.NO_STRING+"' and academicYearId="+ academicYear.getId());
								if(!ObjectFunctions.isNullOrEmpty(academicYear.getReceiptGenerationType()) && academicYear.getReceiptGenerationType().equalsIgnoreCase("A"))
									query.append(" and invoiceNumber !=0  group by invoiceNumber");
								else
									query.append(" and invoiceString is not null  group by 0 invoiceString");
								setSchoolFeeList(adminManager.getAll(StudentPayment.class, query.toString()));
								//getSchoolFeeList().addAll(adminManager.getAll(ViewStudentTransportFeePaymentDetails.class, query.toString()));
								
								query=null;
								setTempList(accountManager.getOtherFeePaymentDetails(getTempId(),academicYear.getId(),getViewStudentClassDetails().getStudId()));
							}else{
								super.addActionError("Please search with exact admission number");
							}
						}
					}
					viewStudentClassDetailsList = null;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	@Actions( { @Action(value = "ajaxDeleteInvoicePaymentByMasterAdmin", results = { @Result(location = "deleteInvoice/ajaxDoViewStudentDetails.jsp", name = "success") }) })
	public String ajaxDeleteInvoicePaymentByMasterAdmin() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDeleteInvoicePaymentByMasterAdmin' method");
		}
		try {
			int returnCode=accountManager.deleteInvoicePaymentByMasterAdmin(getDeleteStudentPaymentVo(),getUser().getId(),getTempString1(),Long.valueOf(getTempString2()),Long.valueOf(getStudentNumber()));
			if(returnCode ==1)
				super.addActionMessage("Invoice removed successfully");
			else
				super.addActionError("Invoice not able to remove. Please contact administrator.");
			deleteInvoiceCustomerStudentWiseDetails();
			Student student = (Student) adminManager.get(Student.class,"id="+ Long.valueOf(getStudentNumber()));
			if(!ObjectFunctions.isNullOrEmpty(student)){
				if(!ObjectFunctions.isNullOrEmpty(student.getAccount().getStudentParent()))
				adminManager.sendNotificationForFeePayment(student.getCustId(),student.getId(),ObjectFunctions.isNullOrEmpty(student.getAccount().getStudentParent().getId()) ? 0 : Long.valueOf(student.getAccount().getStudentParent().getId()),"D");
			}
			student = null;
		} catch (Exception ex) {
			log.error("Entering into 'catch block':" + ex.getMessage());
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	@Actions( { @Action(value = "ajaxViewPreviousPendingFeeDetails", results = { @Result(location = "ajaxViewPreviousYearPendingFeeDetails.jsp", name = "success") }) })
	public String ajaxViewPreviousPendingFeeDetails() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxViewPreviousPendingFeeDetails' method");
		}
		try {
			getSmsCount();
			//setStudentsList(adminManager.getAll("select (sum(feeAmount)-(sum(paymentAmount)+sum(discountAmount)+sum(concessionAmount))) as paidAmount,fullName,admissionNumber,className,section,mobileNumber,classId,studentId,feePaidStatus from vw_studentFeePaymentDetails where custId="+getUserCustId()+" and academicYearId="+getAcademicYearId()+" and feePaidStatus in ('N','P') and feeConfigured='Y' and description is null group by studentId order by classSectionId,schoolTermId"));
			StringBuilder query= new StringBuilder("select (sum(SCF.feeAmount)-((SPD.paymentAmount+SUM(IFNULL(SCF.concessionAmount,0))))) as paidAmount,SPD.fullName,SPD.admissionNumber,SPD.className,SPD.section,SPD.mobileNumber,SPD.classId,SPD.studentId,SPD.feePaidStatus from vw_studentClassFees SCF  ");
			query.append("Left JOIN (Select (sum(paymentAmount)+sum(IFNULL(discountAmount,0))) as paymentAmount,fullName,admissionNumber,className,section,mobileNumber,classId,studentId,feePaidStatus");
			query.append(" From vw_studentFeePaymentDetails where custId="+getUserCustId()+" and academicYearId="+getAcademicYearId()+" and feePaidStatus in ('N','P') and feeConfigured='Y' and description is null");
			query.append(" group by studentId order by classSectionId,schoolTermId ) SPD ON SCF.studentId=SPD.studentId");
			query.append(" where SCF.custId="+getUserCustId()+" and SCF.academicYearId="+getAcademicYearId()+" and SCF.feePaidStatus in ('N','P') and SCF.feeConfigured='Y' and SPD.studentId is not null group by SPD.studentId having paidAmount >0");
			log.debug(new Date()+"student size :");
			setStudentsList(adminManager.getAll(query.toString()));
			log.debug(new Date()+"student size :"+getStudentsList().size());
		} catch (Exception ex) {
			log.error("Entering into 'catch block':" + ex.getMessage());
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	public void getExcessAmountDetails(Student student)
	{
		setBalance("0");
		if(!ObjectFunctions.isNullOrEmpty(student))
		{
			StringBuilder query = new StringBuilder("select SUM(excessAmount),status from excessPayment where accountId=").append(student.getAccount().getId()).append(" and status='N'");
			Object[] availableExcessAmt = adminManager.get(query.toString());
			
			if(!ObjectFunctions.isNullOrEmpty(availableExcessAmt) && !ObjectFunctions.isNullOrEmpty(availableExcessAmt[0])){
				setBalance(availableExcessAmt[0].toString());
			}
			
			availableExcessAmt = null;
			
			
			query = new StringBuilder("select usedPaymentId,excessAmount,status from excessPayment where accountId=").append(student.getAccount().getId()).append(" and status='Y'");
			List<Object[]> usedExcessAmt = adminManager.getAll(query.toString());
			if(!ObjectFunctions.isNullOrEmpty(usedExcessAmt))
			{
				double totalUsedAmount = 0.0;
				for(Object[] excessPaymentObj : usedExcessAmt)
				{
					if(!ObjectFunctions.isNullOrEmpty(excessPaymentObj))
					{
						if(Long.valueOf(excessPaymentObj[0].toString()) > 0)
						{
							Object[] studentPaymentObj = adminManager.get("select id,paymentAmount from studentFeePaidDetails where studentPaymentId="+excessPaymentObj[0].toString() + " and studentId="+student.getId());
							if(!ObjectFunctions.isNullOrEmpty(studentPaymentObj))
							{
								if(!ObjectFunctions.isNullOrEmpty(studentPaymentObj[1]))
								{
									if(Double.valueOf(studentPaymentObj[1].toString()) >= Double.valueOf(excessPaymentObj[1].toString()))
									{
										totalUsedAmount = totalUsedAmount + Double.valueOf(excessPaymentObj[1].toString());
									}
									else
									{
										totalUsedAmount = totalUsedAmount + Double.valueOf(studentPaymentObj[1].toString());
									}
								}
							}
							
						}
					}
				}
				setTotalAmount(totalUsedAmount);
			}
		}
	}
	
	@Actions({ @Action(value = "ajaxGetStudentByAdmissionNumberForDeleteInvoice", results = { @Result(location = "deleteInvoice/ajaxViewDeleteStudentFeeDetails.jsp", name = "success") 
	}) })
	public String ajaxGetStudentByAdmissionNumberForDeleteInvoice() 
	{
		if (log.isDebugEnabled()) {
		log.debug("Entering 'ajaxGetStudentByAdmissionNumberForDeleteInvoice' method");
		}
		try {
			if(getTempId()>0)
			{
				AcademicYear academicYear = adminManager.getCurrentAcademicYear(Constants.YES_STRING, getTempId());
				if(!ObjectFunctions.isNullOrEmpty(academicYear)){
					setViewStudentClassDetails((ViewStudentClassDetails)adminManager.get(ViewStudentClassDetails.class, "custId="+getTempId()+" and academicYearId="+getTempId1()+" and studId='"+getAnyId()+"' and studDiscontinueDesc is null"));
					if(!ObjectFunctions.isNullOrEmpty(getViewStudentClassDetails()))
					{
						StringBuffer query = new StringBuffer(" studentId="+getViewStudentClassDetails().getStudId()+" and custId="+getTempId()+" and deleteStatus='"+Constants.NO_STRING+"' and academicYearId="+ getTempId1());
						if(!ObjectFunctions.isNullOrEmpty(academicYear.getReceiptGenerationType()) && academicYear.getReceiptGenerationType().equalsIgnoreCase("A"))
							query.append(" and invoiceNumber !=0  group by invoiceNumber");
						else
							query.append(" and invoiceString is not null  group by 0 invoiceString");
						setSchoolFeeList(adminManager.getAll(ViewStudentFeePaymentDetails.class, query.toString()));
						query=null;
						setTempList(accountManager.getOtherFeePaymentDetails(getTempId(),getTempId1(),getViewStudentClassDetails().getStudId()));
					}else{
					super.addActionError("Please search with exact admission number");
					}
				}
			}
		} catch (Exception e) {
		e.printStackTrace();
		}
		return SUCCESS;
	}
	
	@Actions( { @Action(value = "ajaxPopUpFeeDetails", results = { @Result(location = "ajaxPopUpFeeDetails.jsp", name = "success") }) })
    public String ajaxPopUpFeeDetails() throws URTUniversalException {
		if (log.isDebugEnabled()) {
		    log.debug("Entering 'ajaxPopUpFeeDetails' method");
		}
		try {
			setCustomer(getCustomerByCustId());
			Object[] overAllPaymentDetails = accountManager.getOverAllFeePaymentSummary(getUserCustId(), getUserAcademicYearId());
			getSchoolFeeList().add(overAllPaymentDetails);
		} catch (Exception ex) {
		    ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		    JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		return SUCCESS;
    }
	@Actions({ @Action(value = "ajaxGetDeleteInvoiceDetails", results = { @Result(location = "deleteInvoice/ajaxViewDeleteInvoiceDetails.jsp", name = "success") }) })
	public String ajaxGetDeleteInvoiceDetails() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxGetDeleteInvoiceDetails' method");
		}
		try {
			setObjectList(adminManager.getStudentDeleteInvoiceDetailsById(getUserCustId(),getUserAcademicYearId(),"R")); /*'R'-Regular Fee */
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return SUCCESS;
	}
	@Actions({ @Action(value = "ajaxGetDeleteOtherFeeDetails", results = { @Result(location = "deleteInvoice/ajaxViewDeleteOtherFeeInvoiceDetails.jsp", name = "success") }) })
	public String ajaxGetDeleteOtherFeeDetails() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxGetDeleteOtherFeeDetails' method");
		}
		try {
			setObjectList(adminManager.getStudentDeleteOtherFeeDetailsById(getUserCustId(),getUserAcademicYearId(),"F")); /*'F'-Fine Fee */
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return SUCCESS;
	}
	@Action(value = "ajaxSearchStudentByCriteriaForRefund", results = { @Result(location = "ajaxViewStudentListForRefund.jsp", name = "success") })
	public String ajaxSearchStudentByCriteriaForRefund() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxSearchStudentByCriteriaForRefund' method");
		}
		try {
			prepareStudentsDetailsList();
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	@Actions({
		@Action(value = "ajaxDoSchoolFeeRefund", results = { @Result(location = "ajaxSchoolFeeRefundHome.jsp", name = "success") }),
		@Action(value = "ajaxSearchStudentsForRefund", results = { @Result(location = "searchStudentDetailsForRefund.jsp", name = "success") })
	})
	public String ajaxDoSchoolFeeRefund() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDoSchoolFeeRefund' method");
		}
		try {
			setCustomer(getCustomerByCustId());
			//setStudyClassList(adminManager.GetAllStudyClasses(getUserCustId(), getUserAcademicYearId(),null));
			ajaxViewFeeRefundDetails();
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	@Actions({ @Action(value = "ajaxDoStudentFeeRefund", results = { @Result(location = "ajaxStudentFeeRefund.jsp", name = "success")}) })
	public String ajaxDoStudentFeeRefund() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDoStudentFeeRefund' method");
		}
		try {
			Long studId=Long.valueOf(getParamValue("id"));
			long receiptNumber= 0;
			if(!ObjectFunctions.isNullOrEmpty(studId))
			{
				StringBuffer query = null;
				//query = new StringBuffer("id=").append(studId).append(" and custId=").append(getUserCustId()).append(" and description is null");
				query = new StringBuffer("id=").append(studId).append(" and custId=").append(getUserCustId());
				setStudent((Student)adminManager.get(Student.class,query.toString()));
				if (!ObjectFunctions.isNullOrEmpty(getStudent())) 
				{
					//For Date Restriction
					try{
					 setStartDate(DateFormatter.parseString(DateFormatter.MM_D_YY_PATTERN, DateUtil.getDateTime(DateFormatter.MM_D_YY_PATTERN,getStudent().getLastUpdatedDate())));
					 long difference = getStartDate().getTime() - new Date().getTime();
					 float daysBetween = (difference / (1000*60*60*24));
				     setTempId1((long)daysBetween);
					}catch(Exception e ){e.printStackTrace();}
					setTempList(adminManager.getStudentTermsWisePaidClassFeePaymentDetails(getStudent().getId(),getStudent().getAcademicYearId()));
					setObjectList((List<BankMaster>) (SMSLookUpDataCache.lookUpDataMap.get(Constants.BANK_LIST)));
					if(ObjectFunctions.isNotNullOrEmpty(getTempList())){
						for(Object obj : getTempList()){
							ViewStudentFeePaymentDetails studentTermFee = (ViewStudentFeePaymentDetails)obj;
							studentTermFee.setPendingStudentList(adminManager.getStudenClassFeePaymentDetails(getStudent().getId(),getStudent().getAcademicYearId(),studentTermFee.getSchoolTermId()));
							studentTermFee.getPendingStudentList();
						}
					}
					//Siva: this below code is to show the transport fee paid and not paid based on student boarding point id
					//if(getAnyId().contains("3")){
						setStudentTransportTermsList(adminManager.getAllStudentTermsWiseTransportFeeViewPaymentDetails(getStudent().getId(),getStudent().getAcademicYearId(),3));
						if(ObjectFunctions.isNotNullOrEmpty(getStudentTransportTermsList())){
							for(Object obj : getStudentTransportTermsList()){
								ViewStudentTransportFeePaymentDetails studentTermFee = (ViewStudentTransportFeePaymentDetails)obj;
								studentTermFee.setStudentTransportFeeList(adminManager.getAllStudenTransportFeePaymentDetails(getStudent().getId(),getStudent().getAcademicYearId(),studentTermFee.getSchoolTermId()));
							}
						}
					//}

						if(getCustomer().isAcademicWiseFeeReceipt())
							receiptNumber = adminManager.getInvoiceNumberByCustId("studentPayment", getUserCustId(),getStudent().getAcademicYearId()); 
						else
							receiptNumber = adminManager.getInvoiceNumberByCustId("studentPayment", getUserCustId(),0);
						if (receiptNumber == 0 && getCustomer().getStartInvoiceNumber() != 0) {
							setTempId(getCustomer().getStartInvoiceNumber());
						}else{
							setTempId(receiptNumber+1);
						}
					
				}	
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return SUCCESS;
	}
	@Action(value = "ajaxPayStudentRefundFee", results = {@Result(location = "../admin/student/ajaxViewExpiredStudentsList.jsp", name = "success") })
	public String ajaxPayStudentRefundFee() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxPayStudentRefundFee' method");
		}
		AcademicYear academicYear = null;
		Customer customer = null;
		Date paymentDate = null;
		try {
			long feeAcademicYearId = 0;
			if(StringFunctions.isNotNullOrEmpty(getStudentNumber()) && !ObjectFunctions.isNullOrEmpty(getTotalAmount()) && getTotalAmount() > 0.0 && !ObjectFunctions.isNullOrEmpty(getStudentFeeRefund()))
			{
				getStudentFeeRefund().setTotalFeeAmount(getTotalAmount());
				getStudentFeeRefund().setInvoiceNumber(getTempId());
				if (getTempId1() != getUserAcademicYearId() && getTempId1() != 0) {
					feeAcademicYearId = getTempId1();
					setAcademicYearId(feeAcademicYearId);
				} else {
					feeAcademicYearId = getUserAcademicYearId();
				}
				//paymentDate = DateFormatter.parseString(DateFormatter.YYYY_MM_DD_PATTERN, DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN,getStudentPayment().getPaymentDate()));
				paymentDate = DateFormatter.parseString(DateFormatter.YYYY_MM_DD_PATTERN, DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN,getStartDate()));
				academicYear = (AcademicYear) adminManager.get(AcademicYear.class,feeAcademicYearId);
				customer = getCustomerByCustId();
				if(!ObjectFunctions.isNullOrEmpty(academicYear)){
					student = (Student)adminManager.get(Student.class, "custId="+getUserCustId()+" and academicYearId="+feeAcademicYearId+" and id="+Long.valueOf(getStudentNumber()));
					if(!ObjectFunctions.isNullOrEmpty(student)){
						//prepareStudentSchoolFeeSettingList(student);
						schoolFeeManager.saveStudentFeerefund(getStudentFeeRefund(),student.getId(),getUserCustId(),academicYear,getUser().getId());
						student.setFeeRefundStatus("Y");
						schoolFeeManager.save(student);
						super.addActionMessage("Refund made successfully for '"+student.getStudentName()+" '");
						//For printing the receipt
						setStudentNumber(getStudentNumber());
						setTodayDate(DateFormatter.formatDate(DateFormatter.ddMMMyyyy_PATTERN1, paymentDate));
						setPlTitle(String.valueOf(getStudentFeeRefund().getRefundAmount()));
						student=null;
					}
				}
				
			}
			else{
				super.addActionMessage("There is a problem with Fee refund, please contact with Eazyschool support team");
			}
		} catch (Exception ex) {
			log.error("Entering into 'catch block':" + ex.getMessage());
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		} finally {
			//adminGetSchoolFee();
			setObjectList(studentManager.getAll(ViewStudentPersonAccountDetails.class,"accountExpired='"+Constants.YES_STRING+"' and custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and description is not null"));
			if(ObjectFunctions.isNotNullOrEmpty(getObjectList())) 
				Collections.sort(getObjectList());
			setCustomer(getCustomerByCustId());
			academicYear = null;
			customer = null;
			student = null;
		}
		return SUCCESS;
	}
	
	@Action(value = "ajaxViewFeeRefundDetails", results = {@Result(location = "ajaxViewStudentFeeRefundDetails.jsp", name = "success") })
	public String ajaxViewFeeRefundDetails() throws URTUniversalException {
		try{
			List<ViewStudentFeeRefundDetails> refundStudentList = schoolFeeManager.getFeeRefundStudentsList(getUserCustId(),getUserAcademicYearId());
			if(!ObjectFunctions.isNullOrEmpty(refundStudentList)){
				setStudentsList(refundStudentList);
			}
		}
		catch (Exception ex) {
			log.error("Entering into 'catch block':" + ex.getMessage());
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	
	@Actions( { @Action(value = "ajaxValidateInvoiceNumber", results = { @Result(type = "json", name = "success", params = {"includeProperties", "validateCode" }) }) })
	public String ajaxValidateInvoiceNumber() throws URTUniversalException {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxValidateInvoiceNumber' method");
		}
		try {
			AcademicYear academicYear = (AcademicYear) adminManager.get(AcademicYear.class,getUserAcademicYearId());
			Customer customer = getCustomerByCustId();
			if(!ObjectFunctions.isNullOrEmpty(academicYear)){
				int validateCode;
				if(academicYear.getReceiptGenerationType().equalsIgnoreCase("A"))
					validateCode=schoolFeeManager.validatePaymentInvoiceNumber(customer,academicYear,getStudentPayment().getInvoiceString());
				else
					validateCode=schoolFeeManager.validatePaymentInvoiceNumber(customer,academicYear,getStudentPayment().getInvoiceString());
				JSONObject validateCodeJson=null;
				academicYear=null;
				customer=null;
				validateCodeJson =new JSONObject().put("validateCode",validateCode);
				getResponse().getOutputStream().print(validateCodeJson.toString());
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
	
}