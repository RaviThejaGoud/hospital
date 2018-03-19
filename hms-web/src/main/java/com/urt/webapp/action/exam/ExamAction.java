package com.urt.webapp.action.exam;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.zip.ZipOutputStream;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.servlet.ServletOutputStream;

import jxl.CellView;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper;
import org.springframework.dao.DataAccessException;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.churchgroup.common.constants.Constants;
import com.churchgroup.util.date.DateFormatter;
import com.churchgroup.util.date.DateFunctions;
import com.churchgroup.util.date.DateUtil;
import com.churchgroup.util.object.ConvertUtil;
import com.churchgroup.util.object.ObjectFunctions;
import com.churchgroup.util.pdf.PDFGenerator;
import com.churchgroup.util.string.StringFunctions;
import com.churchgroup.util.xls.ExcelView;
import com.hyniva.sms.ws.vo.exam.QuestionPaperBankVO;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfImportedPage;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfWriter;
import com.spring.context.SpringContextAware;
import com.urt.exception.base.URTUniversalException;
import com.urt.json.JSONArray;
import com.urt.json.JSONObject;
import com.urt.persistence.model.common.AcademicYear;
import com.urt.persistence.model.common.Attachment;
import com.urt.persistence.model.common.HallTicketSettings;
import com.urt.persistence.model.common.StudyMaterialAttachments;
import com.urt.persistence.model.common.UserImage;
import com.urt.persistence.model.customer.Customer;
import com.urt.persistence.model.customer.FeeType;
import com.urt.persistence.model.customer.SchoolTerms;
import com.urt.persistence.model.exam.ActivitiesGrades;
import com.urt.persistence.model.exam.ExamSchedules;
import com.urt.persistence.model.exam.ExamSchedulesComparator;
import com.urt.persistence.model.exam.ExamSchedulesStartDateComparator;
import com.urt.persistence.model.exam.ExamSchedulesSubjectsAndSubtypesWiseComparator;
import com.urt.persistence.model.exam.ExamTypes;
import com.urt.persistence.model.exam.OverAllGrades;
import com.urt.persistence.model.exam.SchoolGrades;
import com.urt.persistence.model.exam.ScoreCardTemplates;
import com.urt.persistence.model.exam.StudentsPromotionReportTemplates;
import com.urt.persistence.model.exam.SubType;
import com.urt.persistence.model.exam.ViewClassExamDetails;
import com.urt.persistence.model.exam.ViewExamSchedule;
import com.urt.persistence.model.exam.ViewExamScheduleDetails;
import com.urt.persistence.model.exam.ViewExamSchedulesComparator;
import com.urt.persistence.model.exam.ViewQuestionBankDetails;
import com.urt.persistence.model.study.ClassAssignment;
import com.urt.persistence.model.study.ClassName;
import com.urt.persistence.model.study.ClassTeacher;
import com.urt.persistence.model.study.InvoiceLogs;
import com.urt.persistence.model.study.Staff;
import com.urt.persistence.model.study.StaffSyllabusPlanner;
import com.urt.persistence.model.study.Student;
import com.urt.persistence.model.study.StudentAcademicPerformance;
import com.urt.persistence.model.study.StudentActivities;
import com.urt.persistence.model.study.StudentActivityTypes;
import com.urt.persistence.model.study.StudentMarks;
import com.urt.persistence.model.study.StudentRollNumberComparator;
import com.urt.persistence.model.study.StudentsAssessments;
import com.urt.persistence.model.study.StudentsHallTicketDetails;
import com.urt.persistence.model.study.StudyClass;
import com.urt.persistence.model.study.StudyMaterials;
import com.urt.persistence.model.study.StudySubject;
import com.urt.persistence.model.study.SubTypeGrades;
import com.urt.persistence.model.study.SyllabusType;
import com.urt.persistence.model.study.ViewClassSectionTeacher;
import com.urt.persistence.model.study.ViewStudentClassDetails;
import com.urt.persistence.model.study.ViewStudentClasswiseAndPersonalInfo;
import com.urt.persistence.model.study.ViewStudentFeePaidAndNotPaidDetails;
import com.urt.persistence.model.study.ViewStudentMarks;
import com.urt.persistence.model.study.ViewStudentMarksDetails;
import com.urt.persistence.model.study.ViewStudentPersonAccountDetails;
import com.urt.persistence.model.study.ViewStudentsLatestExamMarksDetails;
import com.urt.persistence.model.study.ViewStudentsTCDetails;
import com.urt.persistence.model.study.ViewStudyClassMaterials;
import com.urt.persistence.model.study.ViewStudyClassSubjects;
import com.urt.persistence.model.study.ViewStudyClassSubjectsComparator;
import com.urt.service.manager.interfaces.admin.AdminManager;
import com.urt.service.thread.ProcessStudentsSubjectwisePosition;
import com.urt.service.thread.SendMarksToMobileThread;
import com.urt.service.thread.StudentMarksNotificationThread;
import com.urt.util.common.RayGunException;
import com.urt.util.email.MailUtil;
import com.urt.util.excel.student.PrepareStudentExcel;
import com.urt.webapp.action.base.BaseAction;
import com.urt.webapp.action.jrexception.JRExceptionClient;

import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.images.FileImageProvider;
import fr.opensagres.xdocreport.document.images.IImageProvider;
import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.TemplateEngineKind;
import fr.opensagres.xdocreport.template.formatter.FieldsMetadata;
import fr.opensagres.xdocreport.template.formatter.NullImageBehaviour;

@Actions( {
	@Action(value = "ajaxManageStudentExamSchedulesAndResults", results = { @Result(location = "student/ajaxManageStudentExamSchedules.jsp", name = "success") }),
	//@Action(value = "ajaxStaffExamSchedules", results = { @Result(location = "manageStaffExamSchedules.jsp", name = "success") }),
	@Action(value = "ajaxDownloadAndUploadStudentsActivitiesSheet", results = { @Result(location = "admin/ajaxUploadAndDownloadActivitiesSheet.jsp", name = "success") }),
	@Action(value = "ajaxDoUploadStudentsActivitiesMarks", results = { @Result(location = "admin/ajaxUploadActivitiesGradesSheet.jsp", name = "success") }),
	@Action(value = "ajaxDownloadAndUploadMarksSheet", results = { @Result(location = "staff/uploadMarksSheetForStaff.jsp", name = "success") }),
	@Action(value = "ajaxManageStudentMarks", results = { @Result(location = "admin/ajaxManageStudentMarks.jsp", name = "success") }),
	@Action(value = "manageExaminationDetails", results = { @Result(location = "admin/ajaxExaminationHome.jsp", name = "success") })/*,
	@Action(value = "ajaxDoUploadMarksExcelSheetForSendingMarks", results = { @Result(location = "admin/ajaxUploadMarksExcelSheet.jsp", name = "success") })*/
})
@Namespace("/exam")
public class ExamAction extends BaseAction{

	private static final long serialVersionUID = -5489752826342925194L;
//	private static final Object[] Object = null;
	protected StudentActivities studentActivities;
	protected StudentsAssessments studentsAssessments ;
	protected StudentActivityTypes studentActivityTypes;
	protected QuestionPaperBankVO questionPaperBankVO;
	protected List<QuestionPaperBankVO> questionBankListDetails;
	protected String[] montNames = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
	
	
	
	public StudentsAssessments getStudentsAssessments() {
		return studentsAssessments;
	}

	public void setStudentsAssessments(StudentsAssessments studentsAssessments) {
		this.studentsAssessments = studentsAssessments;
	}

	public StudentActivities getStudentActivities() {
		return this.studentActivities;
	}

	public void setStudentActivities(StudentActivities studentActivities) {
		this.studentActivities = studentActivities;
	}
	
	public StudentActivityTypes getStudentActivityTypes() {
		return this.studentActivityTypes;
	}

	public void setStudentActivityTypes(StudentActivityTypes studentActivityTypes) {
		this.studentActivityTypes = studentActivityTypes;
	}
	
	public QuestionPaperBankVO getQuestionPaperBankVO() {
		if(ObjectFunctions.isNullOrEmpty(this.questionPaperBankVO)) {
			this.questionPaperBankVO=new QuestionPaperBankVO() ;
		}
		return questionPaperBankVO;
	}
	public void setQuestionPaperBankVO(QuestionPaperBankVO questionPaperBankVO) {
		this.questionPaperBankVO = questionPaperBankVO;
	}

	public List<QuestionPaperBankVO> getQuestionBankListDetails() {
		if(ObjectFunctions.isNullOrEmpty(this.questionBankListDetails)){
			this.questionBankListDetails=new ArrayList<QuestionPaperBankVO>();
		}
		return questionBankListDetails;
	}

	public void setQuestionBankListDetails(
			List<QuestionPaperBankVO> questionBankListDetails) {
		this.questionBankListDetails = questionBankListDetails;
	}

	@SuppressWarnings("unchecked")
	public void getAllOverAllGrades(){
		StringBuffer query = new StringBuffer("academicYearId=").append(getUserAcademicYearId());
		setOverAllGradesList(adminManager.getAll(OverAllGrades.class,query.toString()));
		if(ObjectFunctions.isNotNullOrEmpty(getOverAllGradesList()))
			Collections.sort(getOverAllGradesList());
		query = null;
	} 
	@SuppressWarnings("unchecked")
	public void getAllSchoolGrades(){
		StringBuffer query = new StringBuffer("academicYearId=").append(getUserAcademicYearId());
		setSchoolGradesList(adminManager.getAll(SchoolGrades.class, query.toString()));
		if(ObjectFunctions.isNotNullOrEmpty(getSchoolGradesList()))
			Collections.sort(getSchoolGradesList());
		query = null;
	}
	@SuppressWarnings("unchecked")
	public void getAllSubtypes(){
 		StringBuffer query = new StringBuffer("academicYearId=").append(getUserAcademicYearId());
		setSubTypesList(adminManager.getAll(SubType.class, query.toString()));
		if(ObjectFunctions.isNotNullOrEmpty(getSubTypesList()))
			Collections.sort(getSubTypesList());
		query = null;
	} 
	@Override
	public String getAutoCheck() {
		return super.autoCheck;
		}
	
	/**Changed by seshu on 29th April. For displaying Overall grades, subject grades, subtypes. ACADEMICS -> Grades & Examtypes.*/
	@Actions( {
		@Action(value = "ajaxManageExamSettings", results = { @Result(location = "ajaxManageExamTypesAndGrades.jsp", name = "success") }),
		@Action(value = "ajaxViewExamTypesAndGrades", results = { @Result(location = "ajaxViewExamTypesAndGrades.jsp", name = "success") })})
		public String ajaxManageExamSettings() throws URTUniversalException {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxManageExamSettings' method");
			}
			try {
				if("overAllGrades".equalsIgnoreCase(getTempString())){
					//setTempString("overAllGrades");
					getAllOverAllGrades();
				}else  if("grades".equalsIgnoreCase(getTempString())){
					getAllSchoolGrades();
				}else if("examTypes".equalsIgnoreCase(getTempString())){
					setExamTypeList(getAllExamTypesByClassId(0,0,getUserCustId(),getUserAcademicYearId()));
				}else if("subTypes".equalsIgnoreCase(getTempString()) || StringFunctions.isNullOrEmpty(getTempString())){
					setTempString("subTypes");
					getAllSubtypes();
				}
				setAcademicYear((AcademicYear) adminManager.get(AcademicYear.class,"status='Y' and custId="+getUserCustId()));
				setExamType(null);
			} catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return SUCCESS;
		}
	/**Changed by seshu on 29th April. For displaying creation and edit overall grades screen.*/
	@Actions( { 
		@Action(value = "ajaxDoAddOverAllGrades", results = { @Result(location = "ajaxCreateOverAllGrades.jsp", name = "success") }) })
	public String ajaxDoAddOverAllGrades() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDoAddOverAllGrades' method");
		}
		try {
			if(getOverAllGrades().getId() > 0)
				setOverAllGrades((OverAllGrades)adminManager.get(OverAllGrades.class,getOverAllGrades().getId()));
			else
				setOverAllGrades(null);
			if("overAllGrades".equalsIgnoreCase(getTempString()) || StringFunctions.isNullOrEmpty(getTempString())){
				setTempString("overAllGrades");
				getAllOverAllGrades();
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	/**Changed by seshu on 29th April. For Storing overall grades.*/
	@Actions( { @Action(value = "ajaxCreateOverAllGrades", results = { @Result(location = "ajaxViewExamTypesAndGrades.jsp", name = "success") }) })
	public String ajaxCreateOverAllGrades() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxCreateOverAllGrades' method");
		}
		try {
			OverAllGrades schoolGrades = null;
			AcademicYear academicYear = getCurrentAcademicYear();
			String gradeName = getOverAllGrades().getGradeName().trim();
			if(!ObjectFunctions.isNullOrEmpty(academicYear) && StringFunctions.isNotNullOrEmpty(gradeName)){
				StringBuffer query = null;
				int count = 0;
				gradeName = getOverAllGrades().getGradeName().trim().toUpperCase();
				if(getOverAllGrades().getId() > 0){
					query = new StringBuffer("gradeName='").append(gradeName).append("' and academicYearId=").append(academicYear.getId()).append(" and id !=").append(getOverAllGrades().getId());
					count = adminManager.getCount("overAllGrades", query.toString());
					if(count > 0)
						super.addActionError(gradeName+" grade already added for this academic year.");
					else
						schoolGrades = (OverAllGrades) adminManager.get(OverAllGrades.class,getOverAllGrades().getId());
				} 
				else{
					query = new StringBuffer("gradeName='").append(gradeName).append("' and academicYearId=").append(getUserAcademicYearId());
					count = adminManager.getCount("overAllGrades", query.toString());
					if(count > 0)
						super.addActionError(gradeName+" grade already added for this academic year.");
					else{
						schoolGrades = new OverAllGrades();
						schoolGrades.setCreatedDate(new Date());
					}
					query = null;
				}
				if(!ObjectFunctions.isNullOrEmpty(schoolGrades)){
					schoolGrades.setCustId(getUserCustId());
					schoolGrades.setAcademicYear(academicYear);
					schoolGrades.setGradeName(gradeName);
					schoolGrades.setMaxMarks(getOverAllGrades().getMaxMarks());
					schoolGrades.setMinMarks(getOverAllGrades().getMinMarks());
					schoolGrades.setDescription(getOverAllGrades().getDescription().trim());
					schoolGrades.setCreatedById(getUser().getId());
					schoolGrades.setLastAccessDate(new Date());
					schoolGrades.setLastUpdatedDate(new Date());
					adminManager.save(schoolGrades);
					if(getOverAllGrades().getId() > 0 )
						super.addActionMessage("Grade updated successfully.");
					else
						super.addActionMessage("Grade added successfully.");
				}
				query = null;
			}
			schoolGrades=null;
			academicYear = null;
			gradeName = null;
			getAllOverAllGrades();
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	/**Changed by seshu on 29th April. For removing overall grades.*/
	@Actions( {
		@Action(value = "ajaxRemoveOverAllGrades", results = { @Result(location = "ajaxViewExamTypesAndGrades.jsp", name = "success") })})
		public String ajaxRemoveOverAllGrades() throws URTUniversalException {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxRemoveOverAllGrades' method");
			}
			try {
				if(getOverAllGrades().getId() > 0){
					adminManager.remove(OverAllGrades.class, getOverAllGrades().getId());
					super.addActionMessage("Over all grade removed successfully.");
				}
				getAllOverAllGrades();
			} catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return SUCCESS;
	}
	/**Changed by seshu on 29th April. For displaying school grades creation or updation screen.*/
	@Actions( { @Action(value = "ajaxDoAddSchoolGrades", results = { @Result(location = "ajaxCreateSchoolGrades.jsp", name = "success") }) })
	public String ajaxDoAddSchoolGrades() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDoAddSchoolGrades' method");
		}
		try {
			if(getSchoolGrades().getId() > 0)
				setSchoolGrades((SchoolGrades) adminManager.get(SchoolGrades.class,getSchoolGrades().getId()));
			else
				setSchoolGrades(null);
			getAllSchoolGrades();
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	/**Changed by seshu on 29th April. For Storing school grades details.*/
	@Actions( { @Action(value = "ajaxCreateSchoolGrades", results = { @Result(location = "ajaxViewExamTypesAndGrades.jsp", name = "success") }) })
	public String ajaxCreateSchoolGrades() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxCreateSchoolGrades' method");
		}
		try {
			SchoolGrades schoolGrades = null;
			AcademicYear academicYear = getCurrentAcademicYear();
			String gradeName = getSchoolGrades().getGradeName().trim();
			if(!ObjectFunctions.isNullOrEmpty(academicYear) && StringFunctions.isNotNullOrEmpty(gradeName)){
				gradeName = gradeName.toUpperCase();
				int count = 0;
				StringBuffer query = null;
				if(getSchoolGrades().getId() > 0){
					query = new StringBuffer("gradeName='").append(gradeName).append("' and academicYearId=").append(academicYear.getId()).append(" and id !=").append(getSchoolGrades().getId());
					count = adminManager.getCount("schoolGrades", query.toString());
					query = null;
					if(count > 0)
						super.addActionError(gradeName+" grade already added for this academic year.");
					else{
						schoolGrades = (SchoolGrades) adminManager.get(SchoolGrades.class, getSchoolGrades().getId());
					}
				} 
				else{ 
					query = new StringBuffer("gradeName='").append(gradeName).append("' and academicYearId=").append(academicYear.getId());
					count = adminManager.getCount("schoolGrades", query.toString());
					query = null;
					if(count > 0)
						super.addActionError(gradeName+" grade already added for this academic year.");
					else{
						schoolGrades = new SchoolGrades();
						schoolGrades.setCreatedDate(new Date());
					}
				}
				if(!ObjectFunctions.isNullOrEmpty(schoolGrades)){
					schoolGrades.setCustId(getUserCustId());
					schoolGrades.setAcademicYear(academicYear);
					schoolGrades.setGradeName(gradeName);
					schoolGrades.setMaxMarks(getSchoolGrades().getMaxMarks());
					schoolGrades.setMinMarks(getSchoolGrades().getMinMarks());
					schoolGrades.setGradePoints(getSchoolGrades().getGradePoints());
					schoolGrades.setLastUpdatedById(getUser().getId());
					schoolGrades.setLastAccessDate(new Date());
					schoolGrades.setLastUpdatedDate(new Date());
					adminManager.save(schoolGrades);
					if(getSchoolGrades().getId() > 0)
						super.addActionMessage("Grade updated successfully.");
					else
						super.addActionMessage("Grade added successfully.");
				}
				query = null;
			}
			schoolGrades=null;
			getAllSchoolGrades();
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	/**Changed by seshu on 29th April. For removing subject grades.*/
	@Actions( {
		@Action(value = "ajaxRemoveSchoolGrades", results = { @Result(location = "ajaxViewExamTypesAndGrades.jsp", name = "success") })})
		public String ajaxRemoveSchoolGrades() throws URTUniversalException {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxRemoveOverAllGrades' method");
			}
			try {
				if(getSchoolGrades().getId() > 0){
					adminManager.remove(SchoolGrades.class, getSchoolGrades().getId());
					super.addActionMessage("Subject grade removed successfully.");
				}
				getAllSchoolGrades();
			} catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return SUCCESS;
	}
/********************************************************************
 * Date              	Name               Description
 * ========          	============       ==================
 * April 29th, 2013     Seshu		       For displaying exam type creation and updation form with all classes.
 * Mat 8th, 2013		Seshu			   Removed class with students and without students method. 
/********************************************************************/	
	@Actions( { @Action(value = "ajaxDoAddExamType", results = { @Result(location = "ajaxAddOrUpdateExamTypes.jsp", name = "success") }) })
	public String ajaxDoAddExamType() throws URTUniversalException {
	if (log.isDebugEnabled()) {
		log.debug("Entering 'ajaxDoAddExamType' method");
	}
	try {
		setChkBoxSelectedIds(null);
		setChkBoxClassSelectedIds(null);
		StringBuffer query =null;
		ExamSchedules examSchedulesObj=null;
		long examTypeId=getExamTypes().getId();
		if(examTypeId > 0){
			setExamTypes((ExamTypes)adminManager.get(ExamTypes.class, examTypeId));
		   if(!ObjectFunctions.isNullOrEmpty(getExamTypes())){
			   if(ObjectFunctions.isNotNullOrEmpty(getExamTypes().getStudyClasses())){
					for (StudyClass studyClass : getExamTypes().getStudyClasses()) {
							getChkBoxSelectedIds().add(String.valueOf(studyClass.getId()));
							studyClass = null;
					}
				}if(ObjectFunctions.isNotNullOrEmpty(getExamTypes().getExamSubTypes())){
					for (SubType examSubType : getExamTypes().getExamSubTypes()) {
						getChkBoxClassSelectedIds().add(String.valueOf(examSubType.getId()));
						examSubType = null;
					}
				}
			}
		}else{
			setChkBoxSelectedIds(null);
			setExamTypes(null);
		}		
		query = new StringBuffer("academicYearId=").append(getUserAcademicYearId()).append(" and custId=").append(getUserCustId());
		Customer customer = getCustomerByCustId();
		if (!ObjectFunctions.isNullOrEmpty(customer) && ObjectFunctions.isNotNullOrEmpty(customer.getSyllabusType()) && customer.getSyllabusType().size() > 0) {
			for(SyllabusType obj:customer.getSyllabusType()){
				getChkBoxSelectedAccountIds().add(String.valueOf(obj.getId()));
			}
			setAllUsersSet(customer.getSyllabusType());
		}customer=null;		
		//setStudyClassList(adminManager.getAll(StudyClass.class,"custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()));
		//setClassList(adminManager.getAllClassNames(getUserCustId(),getUserAcademicYearId()));
		
		List subTypeList= adminManager.getAll(SubType.class,query.toString());
		if(ObjectFunctions.isNotNullOrEmpty(subTypeList)){
			for(Object obj : subTypeList){
				 if(!ObjectFunctions.isNullOrEmpty(obj)){
					 SubType examSubType=  (SubType) obj ;
					if(!ObjectFunctions.isNullOrEmpty(examSubType)){
						if(examTypeId >0){
							query = new StringBuffer("academicYearId=").append(getUserAcademicYearId()).append(" and examTypeId=").append(examTypeId).append(" and subTypeId=").append(examSubType.getId()).append(" and custId=").append(getUserCustId()).append(" group by examTypeId,subTypeId");
							examSchedulesObj= (ExamSchedules) adminManager.get(ExamSchedules.class,query.toString());
							if(!ObjectFunctions.isNullOrEmpty(examSchedulesObj)){
								getTempList().add(examSubType);
								getChkBoxClassSelectedIds().add(String.valueOf(examSubType.getId()));
							}else{
								getObjectList().add(examSubType);
							}
							query=null;
							examSchedulesObj=null;
						}else{
							getObjectList().add(examSubType);
						}
					}
				}
			}
			examTypeId=0;
		}
		query = null;
	} catch (Exception ex) {
		ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	}
	return SUCCESS;
	}
	/**Changed by seshu on 29th April. For creating exam types.*/
	@Actions( { @Action(value = "ajaxCreateSchoolExamTypes", results = { @Result(location = "ajaxViewExamTypesAndGrades.jsp", name = "success") }) })
	public String ajaxCreateSchoolExamTypes() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxCreateSchoolExamTypes' method");
		}
		try {
			AcademicYear academicYear = getCurrentAcademicYear();
			if(StringFunctions.isNotNullOrEmpty(getExamTypes().getSelectedClassIds()) && !ObjectFunctions.isNullOrEmpty(academicYear) || StringFunctions.isNotNullOrEmpty(getExamTypes().getSelectedExamSubTypeIds())){
				StringBuffer selectedClassIds = new StringBuffer(getExamTypes().getSelectedClassIds());
				ExamTypes examType = null; 
				StringBuffer query = new StringBuffer("examType='").append(getExamTypes().getExamType().trim()).append("' and academicYearId=").append(academicYear.getId());
				int count = 0;
				List<String> examScheduleClasses = null;
				if(getExamTypes().getId() > 0){
					query.append(" and id !=").append(getExamTypes().getId());
					count = adminManager.getCount("examTypes", query.toString());
					if(count > 0)
						super.addActionError(getExamTypes().getExamType()+" is already added for this academic year.");
					else{
						examType = (ExamTypes)adminManager.get(ExamTypes.class,getExamTypes().getId());
						List<BigInteger> classSectionIds=adminManager.getRemainingClassIdsByExamTypeIdAndClassIds(getExamTypes().getId(),selectedClassIds.toString());
						if(ObjectFunctions.isNotNullOrEmpty(classSectionIds)){
							String unSelectedClassIds = StringFunctions.convertListToCommaDelimitedString(classSectionIds);
							List<Object[]> scheduleClasses= adminManager.getAllHqlQuery("select schedule.classSection.classNameClassId.id,schedule.classSection.classNameClassId.className from ExamSchedules schedule where schedule.classSection.id in("+unSelectedClassIds+") and schedule.academicYear.id="+academicYear.getId()+" and schedule.examType.id="+getExamTypes().getId()+" group by schedule.classSection.id order by schedule.classSection.classNameClassId.sortingOrder");
							if(ObjectFunctions.isNotNullOrEmpty(scheduleClasses)){
								examScheduleClasses = new ArrayList<String>();
								for(Object[] scheduleClass: scheduleClasses){
									if(!ObjectFunctions.isNullOrEmpty(scheduleClass[0]) && !ObjectFunctions.isNullOrEmpty(scheduleClass[1])){
										selectedClassIds.append(",").append(scheduleClass[0].toString());
										examScheduleClasses.add(scheduleClass[1].toString());
									}
									scheduleClass = null;
								}
							}
							scheduleClasses = null;
						}
						classSectionIds = null;
					}
				}else{
					count = adminManager.getCount("examTypes", query.toString());
					if(count > 0)
						super.addActionError(getExamTypes().getExamType()+" is already added for this academic year.");
					else{
						examType = new ExamTypes();
						examType.setCreatedDate(new Date());
						examType.setCreatedById(getUser().getId());
						Object[] maxSortOrder = adminManager.get("select id,IFNULL(max(sortingOrder),0) from  examTypes where academicYearId="+academicYear.getId());
						if(!ObjectFunctions.isNullOrEmpty(maxSortOrder) && !ObjectFunctions.isNullOrEmpty(maxSortOrder[1])){
							examType.setSortingOrder(Integer.valueOf(maxSortOrder[1].toString())+1);
						}
					}	
				}
				if(!ObjectFunctions.isNullOrEmpty(examType)){
					//List<ClassName> classes = adminManager.getClassesByClassIdsAndAdmissionStatus(getUserCustId(),academicYear.getId(),null,selectedClassIds.toString(),true);
					List<StudyClass> studyClassList = adminManager.getAll(StudyClass.class,"custId="+getUserCustId()+" and academicYearId="+academicYear.getId()+" and id in("+getExamTypes().getSelectedClassIds()+")");
					List<SubType> examSubTypeList = adminManager.getAll(SubType.class,"custId="+getUserCustId()+" and academicYearId="+academicYear.getId()+" and id in("+getExamTypes().getSelectedExamSubTypeIds()+")");
					examType.setCustId(getUserCustId());
					examType.setExamType(getExamTypes().getExamType().trim());
					examType.setMinMarks(getExamTypes().getMinMarks());
					examType.setMaxMarks(getExamTypes().getMaxMarks());
					examType.setAcademicYear(academicYear);
					if(ObjectFunctions.isNullOrEmpty(studyClassList))
						examType.setStudyClasses(null);
					else
						examType.setStudyClasses(ConvertUtil.convertListToSet(studyClassList));
					if(ObjectFunctions.isNullOrEmpty(examSubTypeList))
						examType.setExamSubTypes(null);
					else
						examType.setExamSubTypes(ConvertUtil.convertListToSet(examSubTypeList));
					examType.setMailContentDesc(getExamTypes().getMailContentDesc());
					examType.setMobileContentDesc(getExamTypes().getMobileContentDesc());
					examType.setNoOfDays(getExamTypes().getNoOfDays());
					examType.setLastAccessDate(new Date());
					examType.setLastUpdatedDate(new Date());
					adminManager.save(examType);
					examType = null;
					if(!ObjectFunctions.isNullOrEmpty(examScheduleClasses)){
						if(examScheduleClasses.size() == 1 )
							super.addActionError(StringFunctions.convertListToCommaDelimitedString(examScheduleClasses)+" class has exam schedules. You can't remove this class from "+getExamTypes().getExamType()+" exam type.");
						else
							super.addActionError(StringFunctions.convertListToCommaDelimitedString(examScheduleClasses)+" classes have exam schedules. You can't remove these classes from "+getExamTypes().getExamType()+" exam type.");
					}else if(getExamTypes().getId()!=0) {
						super.addActionMessage("Exam type updated successfully.");
					}else {
						super.addActionMessage("Exam type added successfully.");
					}
				}
				selectedClassIds = null;
				query = null;
				examScheduleClasses = null;
			}
			academicYear = null;
			setExamTypeList(getAllExamTypesByClassId(0,0,getUserCustId(),getUserAcademicYearId()));
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	/**Changed by seshu on 29th April. For removing exam type.*/
	@Actions( {
		@Action(value = "ajaxRemoveExamType", results = { @Result(location = "ajaxViewExamTypesAndGrades.jsp", name = "success") })})
		public String ajaxRemoveExamType() throws URTUniversalException {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxRemoveExamType' method");
			}
			try {
				if(getExamTypes().getId() > 0){
					int schedulesCount = adminManager.getCount("examSchedules", "examTypeId="+getExamTypes().getId());
					if(schedulesCount > 0)
						super.addActionError("Exam schedule is available, cannot delete this exam type");
					else
					{
						ExamTypes examType=(ExamTypes)adminManager.get(ExamTypes.class, getExamTypes().getId());
						examType.setStudyClasses(null);
						adminManager.save(examType);
						examType=null;
						adminManager.remove(ExamTypes.class, getExamTypes().getId());
						super.addActionMessage("Exam type removed successfully.");
					}
				}
				setExamTypeList(getAllExamTypesByClassId(0,0,getUserCustId(),getUserAcademicYearId()));
			} catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return SUCCESS;
	}
	/**Changed by seshu on 29th April. Screen for displaying all exam types for sorting purpose.*/
	@Actions( {
		@Action(value = "ajaxChangeExamTypesOrder", results = { @Result(location = "ajaxChangeExamTypesOrder.jsp", name = "success") })
		})
		public String ajaxChangeExamTypesOrder() throws URTUniversalException {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxChangeExamTypesOrder' method");
			}
			try {
				setExamTypeList(getAllExamTypesByClassId(0,0,getUserCustId(),getUserAcademicYearId()));
			} catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return SUCCESS;
		}
	/**Changed by seshu on 29th April. For updating exam types order.*/
	 @Actions( { @Action(value = "ajaxUpdateExamTypesOrder", results = { @Result(location = "ajaxChangeExamTypesOrder.jsp", name = "success") }) })
		public String ajaxUpdateExamTypesOrder() throws URTUniversalException {
		
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxUpdateExamTypesOrder' method");
		}
		try {
			if(StringFunctions.isNotNullOrEmpty(getAnyTitle())){
				JSONArray examTypeJSONArray=new JSONArray(getAnyTitle());
				JSONObject examTypeJson=null;
				long examTypeId=0;
				long sortingOrder=0;
				for(int i=0;i<examTypeJSONArray.length();i++)
				{
					examTypeJson=examTypeJSONArray.getJSONObject(i);
					if(!ObjectFunctions.isNullOrEmpty(examTypeJson))
					{
						examTypeId = Long.valueOf((String)examTypeJson.get("examTypeId"));
						sortingOrder =Long.valueOf((Integer)examTypeJson.get("sortingOrder"));
						 if(examTypeId > 0 && sortingOrder > 0){
								adminManager.updateExamTypesOrder(examTypeId,sortingOrder);
						 }
				     }
					examTypeJson=null;
			     }
				examTypeJSONArray=null;
				super.addActionMessage("Successfully changed exam types order.");
				//loadStudentsMarksIntoScoreCardGenarationTable(0);
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		finally{
			ajaxChangeExamTypesOrder();
		}
		return SUCCESS;
	}
	 /**Changed by seshu on 29th April. Screen for adding/updating subType.*/
	@Actions( { 
		@Action(value = "ajaxDoAddSubTypesList", results = { @Result(location = "ajaxCreateSubType.jsp", name = "success") })
		})
		public String ajaxDoAddSubTypesList() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDoAddSubTypesList' method");
		}
		try {
			if(getSubType().getId() > 0){
				setSubType((SubType)adminManager.get(SubType.class, getSubType().getId()));
			}else
				setSubType(null);
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
			return SUCCESS;
	} 
	/**Changed by seshu on 29th April. For creating or updating subtypes.*/
	@Actions( { @Action(value = "ajaxCreateOrUpdateSubType", results = { @Result(location = "ajaxViewExamTypesAndGrades.jsp", name = "success") }) })
	public String ajaxCreateOrUpdateSubType() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxCreateOrUpdateSubType' method");
		}
		try {
			AcademicYear academicYear = getCurrentAcademicYear();
			if(!ObjectFunctions.isNullOrEmpty(academicYear)){
				StringBuffer query = new StringBuffer("subTypeName='").append(getSubType().getSubTypeName().trim()).append("' and academicYearId=")
				.append(academicYear.getId());
				int count = 0;
				SubType subType = null;
				if(getSubType().getId() > 0){
					query.append(" and id !=").append(getSubType().getId());
					count = adminManager.getCount("subType", query.toString());
					if(count > 0)
						super.addActionError(getSubType().getSubTypeName().trim()+" exam subtype already added for this academic year.");
					else
						subType = (SubType) adminManager.get(SubType.class, getSubType().getId());
				}else{
					count = adminManager.getCount("subType", query.toString());
					if(count > 0)
						super.addActionError(getSubType().getSubTypeName().trim()+" exam subtype already added for this academic year.");
					else{
						subType =  new SubType();
						subType.setCreatedDate(new Date());
						subType.setCreatedById(getUser().getId());
						Object[] maxSortOrder = adminManager.get("select id,IFNULL(max(sortingOrder),0) from  subType where academicYearId="+academicYear.getId());
						if(!ObjectFunctions.isNullOrEmpty(maxSortOrder) && !ObjectFunctions.isNullOrEmpty(maxSortOrder[1])){
							subType.setSortingOrder(Integer.valueOf(maxSortOrder[1].toString())+1);
						}
					}
				}
				if(!ObjectFunctions.isNullOrEmpty(subType)){
					subType.setAcademicYear(academicYear);
					subType.setCustId(getUserCustId());
					subType.setSchedule(getSubType().isSchedule());
					subType.setSubTypeName(getSubType().getSubTypeName().trim());
					subType.setLastAccessDate(new Date());
					subType.setLastUpdatedDate(new Date());
					adminManager.save(subType);
					if(getSubType().getId() > 0)
						super.addActionMessage("Subtype updated successfully.");
					else
						super.addActionMessage("Subtype added successfully.");
				}
				subType=null;
			}
			academicYear = null;
			getAllSubtypes();
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	/**Changed by seshu on 30th April. Providing screen for changing subtypes order.*/
	 @Actions( {
			@Action(value = "ajaxChangeSubTypesOrder", results = { @Result(location = "ajaxChangeSubTypesOrder.jsp", name = "success") })
		})
	public String ajaxChangeSubTypesOrder() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxChangeSubTypesOrder' method");
		}
		try {
			getAllSubtypes();
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	/**Changed by seshu on 30th April. For updating subtypes order.*/
	 @Actions( { @Action(value = "ajaxUpdateSubTypesOrder", results = { @Result(location = "ajaxChangeSubTypesOrder.jsp", name = "success") }) })
		public String ajaxUpdateSubTypesOrder() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxUpdateSubTypesOrder' method");
		}
		try {
			if(StringFunctions.isNotNullOrEmpty(getAnyTitle())){
				JSONArray subTypeJSONArray=new JSONArray(getAnyTitle());
				JSONObject subTypeJson=null;
				long subTypeId=0;
				long sortingOrder=0;
				for(int i=0;i<subTypeJSONArray.length();i++)
				{
					subTypeJson=subTypeJSONArray.getJSONObject(i);
					if(!ObjectFunctions.isNullOrEmpty(subTypeJson))
					{
						subTypeId = Long.valueOf((String)subTypeJson.get("subTypeId"));
						sortingOrder =Long.valueOf((Integer)subTypeJson.get("sortingOrder"));
						 if(subTypeId > 0 && sortingOrder > 0){
								adminManager.updateSubTypesOrder(subTypeId,sortingOrder);
						 }
				     }
					subTypeJson=null;
			     }
				subTypeJSONArray=null;
				super.addActionMessage("Successfully changed Subtypes order.");
				//loadStudentsMarksIntoScoreCardGenarationTable(0);
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		finally{
			ajaxChangeSubTypesOrder();
		}
		return SUCCESS;
	}
	 /**Changed by seshu on 30th April. For managing sub types grades.*/
	@Actions( { @Action(value = "ajaxManageSubTypeGrades", results = { @Result(location = "ajaxManageSubTypeGrades.jsp", name = "success") }) })
	public String ajaxManageSubTypeGrades() {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxManageSubTypeGrades' method");
		}
		try {
			if(getTempId() > 0 ){
				StringBuffer query = new StringBuffer("academicYearId=").append(getUserAcademicYearId()).append(" and subTypeId=").append(getTempId());
				setObjectList(adminManager.getAll(SubTypeGrades.class,query.toString()));
				query = null;
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
			return SUCCESS;
	}
	/**Changed by seshu 0n 30th April. For adding or updaing subtype grades.*/
	 @Actions( {
			@Action(value = "ajaxAddOrUpdateSubtypeGrades", results = { @Result(location = "ajaxViewExamTypesAndGrades.jsp", name = "success")
					})})
	public String ajaxAddOrUpdateSubtypeGrades() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxAddOrUpdateSubtypeGrades' method");
		}
		try {
			if(StringFunctions.isNotNullOrEmpty(getAnyTitle()) && getTempId() > 0){
				JSONArray classSubTypeGradesJSONArray=new JSONArray(getAnyTitle());
				JSONObject subTypeGradesJson=null;
				long subTypesGradeId=0;
				String gradeName = null;
				String gradePoint = null;
				String minMarks = null;
				String maxMarks= null;
				SubTypeGrades subTypeGrades= null;
				SubType subType = (SubType)adminManager.get(SubType.class,"id="+getTempId());
				if(!ObjectFunctions.isNullOrEmpty(subType)){
					subType.setSubTypeGrades(null);
					for(int i=0;i<classSubTypeGradesJSONArray.length();i++)
					{
						subTypeGradesJson=classSubTypeGradesJSONArray.getJSONObject(i);
						if(!ObjectFunctions.isNullOrEmpty(subTypeGradesJson))
						{
							gradeName = (String) subTypeGradesJson.get("gradeName");
							gradePoint = (String) subTypeGradesJson.get("gradePoint");
							minMarks = (String) subTypeGradesJson.get("minMarks");
							maxMarks= (String) subTypeGradesJson.get("maxMarks");
							if(StringFunctions.isNotNullOrEmpty(gradeName) && StringFunctions.isNotNullOrEmpty(gradePoint)){
								if(StringFunctions.isNotNullOrEmpty((String)subTypeGradesJson.get("subTypeGradeId")) && Long.valueOf((String)subTypeGradesJson.get("subTypeGradeId")) > 0){
									subTypesGradeId = Long.valueOf((String)subTypeGradesJson.get("subTypeGradeId"));
									subTypeGrades =(SubTypeGrades)adminManager.get(SubTypeGrades.class,"id="+subTypesGradeId);
								}else{
									subTypeGrades = new SubTypeGrades();
									subTypeGrades.setCreatedById(getUser().getId());
									subTypeGrades.setCreatedDate(new Date());
								}
								if(!ObjectFunctions.isNullOrEmpty(subTypeGrades)){
									subTypeGrades.setGrade(gradeName);
									subTypeGrades.setGradePoint(Float.valueOf(gradePoint));
									subTypeGrades.setMinMarks(Double.valueOf(minMarks));
									subTypeGrades.setMaxMarks(Double.valueOf(maxMarks));
									subTypeGrades.setCustId(getUserCustId());
									subTypeGrades.setAcademicYearId(getUserAcademicYearId());
									subTypeGrades.setLastAccessDate(new Date());
									subTypeGrades.setLastUpdatedById(getUser().getId());
									subTypeGrades.setLastUpdatedDate(new Date());
									subType.addSubTypeGrades(subTypeGrades);
									subTypeGrades = null;
								}
							}
						}
					}
					adminManager.save(subType);
					super.addActionMessage("You have successfully added subtype grades.");
					getAllSubtypes();
					getTempString();
			  }
			}
			ajaxManageSubTypeGrades();
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	/**Changed by seshu on 30th April. For removing sub type grades.*/ 
	@Actions( {
		@Action(value = "ajaxRemoveSubtypeGrade", results = { @Result(location = "ajaxManageSubTypeGrades.jsp", name = "success") })})
		public String ajaxRemoveSubtypeGrade() throws URTUniversalException {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxRemoveSubtypeGrade' method");
			}
			try {
				if(getTempId1() > 0){
					adminManager.remove(SubTypeGrades.class, getTempId1());
					super.addActionMessage("Subtype grade removed successfully.");
				}
				ajaxManageSubTypeGrades();
			} catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return SUCCESS;
		}
	/**Changed by seshu on 30th April. For removing subtypes.*/
	@Actions( {
		@Action(value = "ajaxRemoveSubType", results = { @Result(location = "ajaxViewExamTypesAndGrades.jsp", name = "success") })})
		public String ajaxRemoveSubType() throws URTUniversalException {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxRemoveSubType' method");
			}
			try {
				if(getSubType().getId() > 0){
					int schedulesCount = adminManager.getCount("examSchedules", "subTypeId="+getSubType().getId());
					int examTypesCount = adminManager.getCount("examTypesAndSubTypes", "subTypeId="+getSubType().getId());
					if(schedulesCount > 0 || examTypesCount > 0){
						if(schedulesCount > 0)
							super.addActionError("Exam schedule is available, cannot delete this sub type.");
						else
							super.addActionError("Exam Types is available, cannot delete this sub type.");
					}else{
						adminManager.remove(SubType.class, getSubType().getId());
						super.addActionMessage("Subtype removed successfully.");
					}
				}
				getAllSubtypes();
			} catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return SUCCESS;
	}
	/********************************************************************
 * Date              	Name               Description
 * ========          	============       ==================
 * April 30th, 2013     Seshu		       For displaying active and archieve examschedules.	
 * May 7th, 2014		Seshu			   Implemented service method
*/
	@Actions( {
		@Action(value = "ajaxDoExamShedules", results = { @Result(location = "academicExamScheduleDetails.jsp", name = "success"),
				                                          @Result(location = "ajaxClassTeacherExamSchedules.jsp", name = "classTeacher")})
		})
	public String ajaxDoExamShedules() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDoExamShedules' method");
		}
		try {
			if(getUserAcademicYearId() > 0){
				if(!getUser().isSchoolPrincipal()){
					setTempBoolean(adminManager.isUserAsClassTeacher(getUser().getId(),0,getUserAcademicYearId()));
				}
				if(isTempBoolean()){
					ajaxClassTeacherExamSchedules();
					return "classTeacher";
				}else{
					setExamScheduleList(adminManager.getUsersStartAndEndDateExamSchedulesDetails(0,getUserAcademicYearId(),getAnyTitle(),false,0,0));
					/*if(ObjectFunctions.isNotNullOrEmpty(getExamScheduleList()))
						Collections.sort(getExamScheduleList());*/
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	/**Changed by seshu on 30th April. For displaying exam schedules based on class and exam type. */
	@Actions({ @Action(value = "ajaxViewExamSchedules", results = { @Result(location = "ajaxViewExamSchedules.jsp", name = "success") }) })
	public String ajaxViewExamSchedules() {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxViewExamSchedules' method");
		}
		try {
			StringBuffer query = new StringBuffer("classSectionId=").append(getClassId()).append(" and examTypeId=").append(getExamType());
			setExamScheduleList(adminManager.getAll(ExamSchedules.class, query.toString()));
			if(ObjectFunctions.isNotNullOrEmpty(getExamScheduleList()))
				//Collections.sort(getExamScheduleList(), new ExamSchedulesSubjectsAndSubtypesWiseComparator());
				Collections.sort(getExamScheduleList());
			query = null;
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	/**Changed by seshu on 30th April. For providing screen for adding exam schedules. */
	@Actions( {
		@Action(value = "ajaxDoAddExamSchedules", results = { @Result(location = "academicExamSchedule.jsp", name = "success") }) })
	public String ajaxDoAddExamSchedules() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDoAddExamSchedules' method");
		}
		try {
			loadAcademicYearStartDateAndDates(getUserAcademicYearId());
			if(getUser().isSchoolTeacher()){
				HashSet<StudyClass> classSections = new HashSet<StudyClass>();
				ClassTeacher classTeacher=staffManager.getClassTeacherByAccountId(getUser().getId(), getUserAcademicYearId());
				if(!ObjectFunctions.isNullOrEmpty(classTeacher)){
					classSections.add(classTeacher.getStudyClass());
				}
				if(getUser().isOnlySchoolHod()){
					if(getUser().getId() > 0 ){
						setStaff(adminManager.getStaffByAcountId(getUser().getId(),Constants.YES_STRING));
					}
					if(!ObjectFunctions.isNullOrEmpty(getStaff().getId())){
						List studyClassesList = getHodStudyClassesList(getStaff().getId(),getUserAcademicYearId());
						if(!ObjectFunctions.isNullOrEmpty(studyClassesList))
						{
							classSections.addAll(studyClassesList);
						}
					}	
				}
				if (ObjectFunctions.isNotNullOrEmpty(classSections)) {
					setStudyClassList(ConvertUtil.convertSetToList(classSections));
					Collections.sort(getStudyClassList());
				}
				classSections = null;
			}else{
				List studyClassList = adminManager.GetAllStudyClasses(getUserCustId(), getUserAcademicYearId(),null);
				if (ObjectFunctions.isNotNullOrEmpty(studyClassList)) {
					setStudyClassList(studyClassList);
				}	
			}
			
			/*ClassTeacher classTeacher=staffManager.getClassTeacherByAccountId(getUser().getId(), getUserAcademicYearId());
			if(!ObjectFunctions.isNullOrEmpty(classTeacher)){
				setStudyClassList(adminManager.getAll(StudyClass.class,"id="+classTeacher.getStudyClassId()));
			}else{
				setStudyClassList(adminManager.GetAllStudyClasses(getUserCustId(), getUserAcademicYearId()));
				}*/
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	 }
/********************************************************************
 * Date              Name               Description
 * ========          ============       ==================
 * April 30, 2013    Seshu		        For displaying class wise exam types.
 * May 14,2013		 Seshu				Added new action for displaying exam types.
/********************************************************************/	
	@Actions( { @Action(value = "ajaxGetClassExamTypes", results = { @Result(location = "ajaxViewClassExamTypes.jsp", name = "success") }),
		/**Calls from Download marks sheet after selecting class.**/
		@Action(value = "ajaxGetClassExamTypesForMarksTemplate", results = { @Result(location = "ajaxViewAllClassExamTypes.jsp", name = "success") }),
		@Action(value = "ajaxGetClassExamTypesForAddMarks", results = { @Result(location = "ajaxGetClassExamTypesForAddMarks.jsp", name = "success") }),
		@Action(value = "ajaxGetClassExamTypesForStudentActivities", results = { @Result(location = "ajaxViewAllClassExamTypes.jsp", name = "success") })
	})
	public String ajaxGetClassExamTypes() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxGetClassExamTypes' method");
		}
		try {
			if(StringFunctions.isNotNullOrEmpty(getClassId())){
				if(StringFunctions.isNotNullOrEmpty(getAnyTitle()))
					setAnyTitle(getAnyTitle());
				if(StringFunctions.isNotNullOrEmpty(getPlTitle()))
					setPlTitle(getPlTitle());
				if(getClassId().contains("_")){
				  setExamTypeList(getAllExamTypesByClassId(0,Long.valueOf(getClassId().split("_")[0]),getUserCustId(),getUserAcademicYearId()));
				}
				else
					setExamTypeList(getAllExamTypesByClassId(0,Long.valueOf(getClassId()),getUserCustId(),getUserAcademicYearId()));	
				setClassId(getClassId());
			  }
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	@Actions( {
		/**Calls from ManageProgressReport -> download students activities template.**/
		@Action(value = "ajaxGetClassCategoriesForStudentActivities", results = { @Result(location = "ajaxViewAllActivityCategories.jsp", name = "success") })
	})
	public String ajaxGetClassCategoriesForStudentActivities() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxGetClassCategoriesForStudentActivities' method");
		}
		try {
			//getClassId() is classNameId
			if(getTempId() > 0){
				/*StringBuffer query = new StringBuffer("SELECT activity FROM StudentActivities activity JOIN activity.studentActivityTypes activityType JOIN activityType.activityTypClasses as clasName WHERE activity.academicYear=").append(getUserAcademicYearId())
				.append(" GROUP BY activity.categoryName");*/
				StringBuffer query = new StringBuffer("SELECT activity FROM StudentActivities activity JOIN activity.studentActivityTypes activityType JOIN activityType.activityTypClasses as classId WHERE activity.custId=").append(getUserCustId()).append(" and activity.academicYear=").append(getUserAcademicYearId())
				.append(" and classId=").append(getTempId()).append(" and activity.categoryName is Not Null and activity.categoryName !='' ").append(" GROUP BY activity.categoryName");
				setObjectList(adminManager.getAllHqlQuery(query.toString()));
				query = null;
				StringBuffer sql = new StringBuffer("custId=").append(getUserCustId()).append(" and academicYearId=").append(getUserAcademicYearId());
				setTempList(adminManager.getAll(StudentsAssessments.class, sql.toString()));
				sql = null;
				StudentAcademicPerformance stuAcademicPer=(StudentAcademicPerformance) adminManager.get(StudentAcademicPerformance.class,"custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and classandsectionId="+getTempId()+" and gradeStatus is not null group by classandsectionId");
				if(!ObjectFunctions.isNullOrEmpty(stuAcademicPer)){
					setTitle(stuAcademicPer.getGradeStatus());
				}
				stuAcademicPer=null;
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	/**Changed by seshu on 1st May. For displaying exam schedules screen based on classSectionId and exam type id.*/
	@Actions({ @Action(value = "ajaxGetExamShedules", results = { @Result(location = "ajaxExamScheduleClassSubects.jsp", name = "success") }),
		@Action(value = "ajaxGetClassExamSyllabus", results = { @Result(location = "ajaxClassSyllabusExamSchedule.jsp", name = "success") })})
	public String ajaxGetExamShedules() {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxGetExamShedules' method");
		}
		try {
			//StudyClass studyClass=null;
			StringBuffer query =null;
			//setTempBoolean(adminManager.isUserAsClassTeacher(getUser().getId(),0,getUserAcademicYearId()));
			if(!StringFunctions.isNullOrEmpty(getExamType()) && !StringFunctions.isNullOrEmpty(getClassId()) && getUserAcademicYearId() > 0){
				setSubjectsList(adminManager.getSubjectsByStudyClassId(Long.valueOf(getClassId().split("_")[0])));
				if(ObjectFunctions.isNullOrEmpty(getSubjectsList())){
					super.addActionError("Currently there are no subjects for the selected class");
					return SUCCESS;
				}
				setClassId(getClassId());
				//studyClass=(StudyClass)adminManager.get(StudyClass.class, " id="+Long.valueOf(getClassId())+" and academicYearId="+getUserAcademicYearId());
				loadAcademicYearStartDateAndDates(getUserAcademicYearId());
				ExamTypes examTypes = (ExamTypes)adminManager.get(ExamTypes.class, Long.valueOf(getExamType()));
				if(!ObjectFunctions.isNullOrEmpty(examTypes)){
					setClassList(adminManager.getAll(ClassName.class, "id in "+examTypes.getClassIds()+" and id <> "+getClassId().split("_")[1]));
				}
					 
				/*if(!ObjectFunctions.isNullOrEmpty(studyClass.getSubjects()))
				{
					setSubjectsList(ConvertUtil.convertSetToList(studyClass.getSubjects()));
				}*/
				if(!ObjectFunctions.isNullOrEmpty(getEndDate()) && !ObjectFunctions.isNullOrEmpty(getToDate())){
					if(DateFunctions.compare2Dates(getToDate(),getEndDate())){
						if("S".equalsIgnoreCase(getEventId()))
						{
							super.addActionError("Current academic year ended.You can't create,edit and delete exam schedules.");
						}
						else
						{
							super.addActionError("Current academic year ended.You can't create,edit and delete exam schedules.");
						}
					}
				}
				if("S".equalsIgnoreCase(getEventId()))
				{
					if(getCustId() <= 0)
				 		   setCustId(getUserCustId());
					query = new StringBuffer("classSectionId=").append(getClassId().split("_")[0]).append(" and eid=").append(getExamType()).append(" and custId=").append(getCustId());
		    		setExamScheduleList(adminManager.getAll(ViewClassExamDetails.class, query.toString()));
		    		if(!ObjectFunctions.isNullOrEmpty(getExamScheduleList()))
						Collections.sort(getExamScheduleList(),new ExamSchedulesComparator());
					}
				else
				{
					setExamScheduleList(adminManager.getExamSchedulesByClassIdAndExamTypeId(Long.valueOf(getClassId().split("_")[0]), Long.valueOf(getExamType())));
					if(!ObjectFunctions.isNullOrEmpty(getExamScheduleList()))
						Collections.sort(getExamScheduleList(),new ViewExamSchedulesComparator());
					} 
				}
				
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	// Need to change some code. If user unselects any subtype from available examschedules we are removing only examschedules.
	//If marks are available for that schedule we need to remove marks also. 
	/**Changed by seshu on 1st May. For creating and updating exam schedules.*/
	@Actions({ @Action(value = "ajaxEditClassExamSchedules", results = { @Result(location = "academicExamSchedule.jsp", name = "success") }) })
	public String ajaxEditClassExamSchedules() {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxEditClassExamScheduless' method");
		}
		try {
			if(StringFunctions.isNotNullOrEmpty(getTempString())){
				JSONArray examSchedulesJsonArray=new JSONArray(getTempString());
    		//	long scheduleId = 0;
				String subjectId =null;
				String examDate =null;
				String startTime =null;
				String endTime =null;
				long subTypeId =0;
				
				JSONArray examSchedulesAndroidArray = new JSONArray();
				JSONObject examSchedulesAndroidObj=null;
				
				JSONObject examScheduleJson=null;
				ExamSchedules examSchedules =null;
				AcademicYear academicYear=null;
				ExamTypes examType=null;
				StudySubject studySubject=null;
				List<ExamSchedules> schedulesList=null;
				Date startDate=null;
				Date endDate=null;
				String maxMarks=null;
				//String showInHT=null;
				SubType subtype = null;
				List<StudyClass> studyClassList=null;
				int count = 0;
				academicYear=getCurrentAcademicYear();
				StringBuffer classSectionIds = new StringBuffer();
				double maxSubMarks=  0;
				String temp = null;
				if(!StringFunctions.isNullOrEmpty(getClassId()) && !ObjectFunctions.isNullOrEmpty(academicYear) && !StringFunctions.isNullOrEmpty(getExamType())){
					studyClassList=new ArrayList<StudyClass>();
					//StudyClass studyClass=(StudyClass)adminManager.get(StudyClass.class, Long.valueOf(getClassId()));
					if("S".equalsIgnoreCase(getParamValue("copyExamSchedules"))){
						if("true".equalsIgnoreCase(getTitle())){
							studyClassList=adminManager.getAll(StudyClass.class," classNameClassId="+getClassId().split("_")[1]);
						}
						else{
							studyClassList=adminManager.getAll(StudyClass.class," id="+getClassId().split("_")[0]);
						}
					}else{
						if(!ObjectFunctions.isNullOrEmpty(getAnyId())){
							studyClassList=adminManager.getAll(StudyClass.class,"custId="+getUserCustId()+" and academicYearId="+academicYear.getId()+" and classNameClassId in"+getAnyId());
						    StudyClass studyClass=(StudyClass)adminManager.get(StudyClass.class, Long.valueOf(getClassId().split("_")[0]));
						    studyClassList.add(studyClass);
						    studyClass=null;
						}
						else
							studyClassList=adminManager.getAll(StudyClass.class,"custId="+getUserCustId()+" and academicYearId="+academicYear.getId()+" and classNameClassId="+getClassId().split("_")[1]);
					}
					//studyClassList.add(studyClass);
					examType=(ExamTypes)adminManager.get(ExamTypes.class, Long.valueOf(getExamType()));
				if(ObjectFunctions.isNotNullOrEmpty(ConvertUtil.convertListToSet(studyClassList)) && !ObjectFunctions.isNullOrEmpty(examType)){
					for(int i=0;i<examSchedulesJsonArray.length();i++)
					{
						examScheduleJson=examSchedulesJsonArray.getJSONObject(i);
						if(!ObjectFunctions.isNullOrEmpty(examScheduleJson))
						{
							//For removing unchecked examSchedules.These values are appended in JSON Array as a last position.   
							if(i == examSchedulesJsonArray.length()-1){
									for(StudyClass classSec:studyClassList){
										classSectionIds.append(classSec.getId()).append(",");
										endDate=adminManager.getMaxExamDateByExamTypeIdAndClassSectionId(examType.getId(),classSec.getId(),"MX");
										startDate=adminManager.getMaxExamDateByExamTypeIdAndClassSectionId(examType.getId(),classSec.getId(),"MI");
										if(!ObjectFunctions.isNullOrEmpty(startDate) && !ObjectFunctions.isNullOrEmpty(endDate)){
											adminManager.updateExamScheduleStartDateAndEndDateByClassSectionIdAndExamTypeId(classSec.getId(),examType.getId(),DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN,startDate),DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN,endDate));
										}
										classSec=null;
									}
									String scheduleIds=(String)examScheduleJson.get("unSelectedSchIds");
									if(StringFunctions.isNotNullOrEmpty(scheduleIds)){
										if(!"(0)".equalsIgnoreCase(scheduleIds)){
											schedulesList=adminManager.getAll(ExamSchedules.class,"id in "+scheduleIds);
											if(ObjectFunctions.isNotNullOrEmpty(schedulesList)){
												classSectionIds.append("0");
												StringBuffer query = null;
												for(ExamSchedules schedule:schedulesList){
													query = new StringBuffer("classSectionId in(").append(classSectionIds).append(") and subTypeId=").append(schedule.getSubType().getId()).append(" and classSubjectId=").append(schedule.getStudySubjectId()).
													append(" and examTypeId=").append(schedule.getExamTypeId());
													adminManager.remove("studentMarks","examScheduleId in(select id from examSchedules WHERE "+query.toString()+")");
													adminManager.remove("examSchedules", query.toString());
													query = null;
												}
												adminManager.remove("studentsScoreCardMarks", "studentId in(select id from student where classSectionId in("+classSectionIds+"))");
											}
										}
									}
								continue;
							}
						//	scheduleId = Long.valueOf((String)examScheduleJson.get("scheduleId"));
							subjectId =(String) examScheduleJson.get("subjectId");
							examDate =(String) examScheduleJson.get("examDate");
							startTime =(String) examScheduleJson.get("startTime");
							endTime =(String) examScheduleJson.get("endTime");
							subTypeId =Long.valueOf((String) examScheduleJson.get("subTypeId"));
							maxMarks = (String) examScheduleJson.get("maxMarks");
							//showInHT = (String) examScheduleJson.get("showHT");
							if(StringFunctions.isNotNullOrEmpty(subjectId) && (StringFunctions.isNotNullOrEmpty(maxMarks) || StringFunctions.isNotNullOrEmpty(examDate))){
								maxSubMarks=Double.valueOf(maxMarks);
								if(maxSubMarks!=0){
									subtype = (SubType)adminManager.get(SubType.class, subTypeId);
									studySubject=(StudySubject)adminManager.get(StudySubject.class, "id="+subjectId);
									if (!ObjectFunctions.isNullOrEmpty(studySubject)) {
										for(StudyClass classSection:studyClassList){
											temp = null;
											count=adminManager.getCount("examSchedules", " classSectionId="+classSection.getId()+" and classSubjectId="+subjectId+" and subTypeId="+subTypeId+" and examTypeId="+examType.getId());
											if(count==0){
												count=0;
												count = adminManager.getCount("ClassSubject", "studyClassId="+classSection.getId()+" and subjectId="+subjectId);
												if(count > 0){
													examSchedules = new ExamSchedules();
													examSchedules.setCreatedDate(new Date());
													examSchedules.setCreatedById(getUser().getId());
													temp = "add";
												}
											}else{
												examSchedules=(ExamSchedules)adminManager.get(ExamSchedules.class, " classSectionId="+classSection.getId()+" and classSubjectId="+subjectId+" and subTypeId="+subTypeId+" and examTypeId="+examType.getId());
												examSchedules.setLastUpdatedById(getUser().getId());
												temp = "update";
											}
											if(!ObjectFunctions.isNullOrEmpty(examSchedules)){
												//String dateReceivedFromUser = "12/13/2012";
												DateFormat userDateFormat = new SimpleDateFormat("MM/dd/yyyy");
												Date date = userDateFormat.parse(examDate);
												examSchedules.setClassSection(classSection);
												examSchedules.setExamType(examType);
												examSchedules.setCustId(getUserCustId());
												examSchedules.setClassSectionSubject(studySubject);
												if(!ObjectFunctions.isNullOrEmpty(examDate))
													examSchedules.setExamDate(DateFormatter.parseString(DateFormatter.YYYY_MM_DD_PATTERN,DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN,date)));
												examSchedules.setStartTime(startTime);
												examSchedules.setEndTime(endTime);
												examSchedules.setAcademicYear(academicYear);
												examSchedules.setScheduled(true);
												examSchedules.setSubType(subtype);
												examSchedules.setCustId(getUserCustId());
												examSchedules.setLastAccessDate(new Date());
												examSchedules.setLastUpdatedDate(new Date());
												examSchedules.setMaxMarks(maxSubMarks);
												//examSchedules.setShowInHT(showInHT);
												examSchedules = (ExamSchedules) adminManager.save(examSchedules);
												
												examSchedulesAndroidObj = adminManager.prepareJsonForExamSchedules(examSchedules);
												if(!ObjectFunctions.isNullOrEmpty(examSchedulesAndroidObj))
												{
													examSchedulesAndroidArray.put(examSchedulesAndroidObj);
												}
											}
											examSchedules = null;
										}
									}
									studySubject=null;
									subtype = null;
								 }
							 }
					     }
				       }
					examType=null;
					String message = null;
					if(temp=="add"){
						message = "Exam schedule details added successfully.";
						super.addActionMessage("Exam schedule details added successfully.");
						//adminManager.sendNotificationToAndroidMobileUsers(getUserCustId(),"Exam schedule added");  //To add notification for mobile app.
					}
					else if(temp=="update"){
						message =  "Exam schedule details updated successfully.";
						super.addActionMessage("Exam schedule details updated successfully.");
						//adminManager.sendNotificationToAndroidMobileUsers(getUserCustId(),"Exam schedule updated");  //To add notification for mobile app.
					}
					
					if (!ObjectFunctions.isNullOrEmpty(examSchedulesAndroidArray)) 
					{
						JSONObject main = new JSONObject();
						JSONObject subVal = new JSONObject();

						main.put("notificationFor", "ExamSchedules");
						
						subVal.put("description", message);
						
						subVal.put("title", message);
						subVal.put("studyClassId", studyClassList.get(0).getId());
						//subVal.put("examSchedulesVOList", examSchedulesAndroidArray);
						
						main.put("information", subVal);
						
						log.debug(main.toString()); 
						
						StringBuffer queryString = new StringBuffer();
						queryString.append("classSectionId=" + Long.valueOf(getClassId().split("_")[0])+ " and custId=" + getUserCustId() + " and academicYearId="+ getUserAcademicYearId() + " and status='"+ Constants.YES_STRING + "' order by firstname ");
						List<ViewStudentClassDetails> viewStudentClassDetailsList =  adminManager.getAll(ViewStudentClassDetails.class, queryString.toString());
						if(!ObjectFunctions.isNullOrEmpty(viewStudentClassDetailsList))
						{
							StringBuffer accountIds = new StringBuffer("(");
							for(ViewStudentClassDetails viewStudentClassDetails : viewStudentClassDetailsList)
							{
								accountIds.append(viewStudentClassDetails.getAccountId());
								if(!StringFunctions.isNullOrEmpty(viewStudentClassDetails.getParentId()))
								{
									accountIds.append("," + viewStudentClassDetails.getParentId());
								}
								accountIds.append(",");
							}
							//Sending notification to staffids
							List<String> staffIds = adminManager.getAll("SELECT GROUP_CONCAT(ct.teacherId) FROM classTeacher ct WHERE ct.studyClassId ="+Long.valueOf(getClassId().split("_")[0]));
							List<String> staffIdAccountIds = adminManager.getAll("SELECT GROUP_CONCAT(DISTINCT(vs.accountId)) FROM vw_staffDetails vs WHERE vs.staffId IN("+staffIds.get(0)+") or roleId IN(8, 12, 31, 35) AND custId = "+getUserCustId());
							if(!ObjectFunctions.isNullOrEmpty(staffIdAccountIds)){
								if(!ObjectFunctions.isNullOrEmpty(staffIdAccountIds.get(0))){
									accountIds.append(staffIdAccountIds.get(0));
									accountIds.append("0)");
								}else{accountIds.append("0)");}
							}else{accountIds.append("0)");}
							adminManager.sendNotificationToAndroidMobileUsersByUserIds(getUserCustId(),main.toString(),accountIds.toString()); //To add notification for mobile app.
						}
					}
				}	
			}else
				super.addActionError("Exam schedule details not added.");
				classSectionIds = null;
			}
			ajaxDoAddExamSchedules();
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
/********************************************************************
 *
 * Date              Name               Description
 * ========          ============       ==================
 * May 1, 2013		 Seshu				Validating whether examschedules have marks or not.
 * 										This method calls when user click on delete option in examschedules.
/********************************************************************/
	@Actions( { @Action(value = "ajaxGetExamscheduleMarks", results = { @Result(type = "json", name = "success",params = {"includeProperties","thresholdMonths,classTeacherStatus"}) }) })
	public String ajaxGetExamscheduleMarks() throws URTUniversalException {
	if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxGetExamscheduleMarks' method");
		}
		try {
			int count = 0;
			StringBuffer query = null;
			JSONObject studMarks =new JSONObject();
			if(StringFunctions.isNotNullOrEmpty(getClassId()) && StringFunctions.isNotNullOrEmpty(getExamType())){
				query = new StringBuffer("select count(*) from StudentMarks sMarks WHERE sMarks.examSchedule.classSection.id=").append(getClassId()).append(" and sMarks.examSchedule.examType.id=").append(getExamType());
				List uploadedMarksCount= adminManager.getAllHqlQuery(query.toString());
				if(ObjectFunctions.isNotNullOrEmpty(uploadedMarksCount)){
					count = Integer.valueOf(uploadedMarksCount.get(0).toString());
				}
				uploadedMarksCount = null;
			}else if(!StringFunctions.isNullOrEmpty(getAnyId())){ //getAnyId() is scheduleId
				query = new StringBuffer("examScheduleId=").append(getAnyId());
				count = adminManager.getCount("studentMarks", query.toString());
				query = null;
			}
			 if(count > 0)
				 studMarks.put("marksAvailable","Student marks created for this examSchedule. Are you sure delete this exam schedule and marks?");
			 getResponse().getOutputStream().print(studMarks.toString());
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
/********************************************************************
 * Date              Name               Description
 * ========          ============       ==================
 * May 1, 2013       Seshu		        Removing exam schedules. If marks available for exam schedules we need to add these marks in excel sheet and
 * 										save this file in userfiles customer directory. 
 * 										Need to implement prepare and store this file.refer ajaxDownloadAndSaveMarkSheet() in BaseAction.java
/********************************************************************/
	@Actions( { @Action(value = "ajaxDeleteExamScheduleMarks", results = { @Result(location = "academicExamSchedule.jsp", name = "success"),
			  @Result(location = "academicExamScheduleDetails.jsp", name = "classWiseSchedule")}) })
	public String ajaxDeleteExamScheduleMarks() throws URTUniversalException {
	if (log.isDebugEnabled()) {
	log.debug("Entering 'ajaxDeleteExamScheduleMarks' method");
	}
	try {
		if(StringFunctions.isNotNullOrEmpty(getClassSectionId()) && StringFunctions.isNotNullOrEmpty(getExamType())){
			adminManager.remove("studentsScoreCardMarks","studentId in(select id from student where classSectionId="+getClassSectionId()+")");
			adminManager.remove("studentMarks", "examScheduleId in(select id from examSchedules where classSectionId="+getClassSectionId()+" and examTypeId="+getExamType()+")");
			adminManager.remove("examSchedules","classSectionId="+getClassSectionId()+" and examTypeId="+getExamType());
			if(StringFunctions.isNotNullOrEmpty(getStudyClass().getClassName()) && StringFunctions.isNotNullOrEmpty(getExamTypes().getExamType()))
				saveExamSchedulesDeleteProcessLogs(getStudyClass().getClassName(),getExamTypes().getExamType());
			super.addActionMessage("Exam Schedules deleted successfully.");
			ajaxDoExamShedules();
			return "classWiseSchedule";
		}else if(!StringFunctions.isNullOrEmpty(getAnyId())){ //here anyId is nothing but sceduleId .
			adminManager.remove("studentMarks", "examScheduleId="+getAnyId());
			//adminManager.remove(ExamSchedules.class, Long.valueOf(getAnyId()));
			ExamSchedules examSchedules = (ExamSchedules)adminManager.get(ExamSchedules.class,Long.valueOf(getAnyId()));
			if(!ObjectFunctions.isNullOrEmpty(examSchedules)){
			//adminManager.remove(ExamSchedules.class, examSchedules.getId());
			int row = adminManager.remove("examSchedules", "id="+examSchedules.getId());
			if(row == 0)
            {
                log.debug("The no of exam schedules rows deleted:"+ row);
            }
			}	
			if(StringFunctions.isNotNullOrEmpty(getStudyClass().getClassName()) && StringFunctions.isNotNullOrEmpty(getExamTypes().getExamType()))
				saveExamSchedulesDeleteProcessLogs(getStudyClass().getClassName(),getExamTypes().getExamType());
			super.addActionMessage("Exam Schedules deleted successfully.");
			ajaxDoAddExamSchedules();
		}
	} catch (Exception ex) {
		ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	}
	return SUCCESS;
	}
	public void saveExamSchedulesDeleteProcessLogs(String classAndSection,String examTypeName){
		InvoiceLogs invoiceLogs=new InvoiceLogs();
		invoiceLogs.setInvoiceNumber(0);
		invoiceLogs.setDescription(classAndSection+"_"+examTypeName);
		invoiceLogs.setCreatedDate(new Date());
		invoiceLogs.setAcademicYear(getCurrentAcademicYear());
		invoiceLogs.setCustId(getUserCustId());
		invoiceLogs.setStatus(Constants.DELETE_EXAMSCHEDULES);
		invoiceLogs.setCreatedById(getUser().getId());
		invoiceLogs.setRoleDescription(getUser().getUserRoleDescription());
		invoiceLogs.setLastUpdatedById(getUser().getId());
		adminManager.save(invoiceLogs);
		invoiceLogs = null;
	}
/********************************************************************
 * Date              Name               Description
 * ========          ============       ==================
 * May 6, 2013       Seshu		        For displaying students upcoming exam schedules.
/********************************************************************/	
	@Actions( {
		@Action(value = "ajaxStudentExamSchedules", results = { @Result(location = "student/ajaxViewStudentExamSchedules.jsp", name = "success")})/*,
		@Action(value = "ajaxGetStudentSchedulesAndResults", results = { @Result(location = "student/ajaxViewStudentExamSchedules.jsp", name = "success") })*/
	})
	public String ajaxStudentExamSchedules() throws URTUniversalException {
	if (log.isDebugEnabled()) {
		log.debug("Entering 'ajaxStudentExamSchedules' method");
	}
	try {
		if(getUser().isParent()){
			StringBuffer query = new StringBuffer();
			//query.append("from Student stud WHERE stud.custId=").append(getUserCustId()).append(" and stud.academicYear=").append(getUserAcademicYearId()).append(" and stud.account.parentId=").append(getUser().getId()).append(" and stud.status='Y'");
			query.append("from Student stud WHERE stud.custId=").append(getUserCustId()).append(" and stud.academicYear=").append(getUserAcademicYearId()).append(" and stud.account.id=").append(getUser().getSelectedStudentId()).append(" and stud.status='Y'");
			setObjectList(adminManager.getAllHqlQuery(query.toString()));
			if(getTempId() > 0){
				studentUpcomingExamSchedules();
			}else if(ObjectFunctions.isNotNullOrEmpty(getObjectList())){
				setTempId(((Student)getObjectList().get(0)).getId());
				studentUpcomingExamSchedules();
			}
			query = null;
		}else
			studentUpcomingExamSchedules();
	} catch (Exception ex) {
		ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	}
		return SUCCESS;
	}
/********************************************************************
 * Date              Name               Description
 * ========          ============       ==================
 * May 8, 2013       Seshu		        For getting upcoming examSchedules list
/********************************************************************/	
   public void studentUpcomingExamSchedules(){
		if(getTempId() > 0)
			setExamScheduleList(adminManager.getUsersStartAndEndDateExamSchedulesDetails(0,0,null,false,0,getTempId()));
		else if(getUserAcademicYearId() > 0)
			setExamScheduleList(adminManager.getUsersStartAndEndDateExamSchedulesDetails(getUser().getId(),getUserAcademicYearId(),null,false,0,0));
		if(ObjectFunctions.isNotNullOrEmpty(getExamScheduleList())){
			ViewExamScheduleDetails examSchedule = (ViewExamScheduleDetails)getExamScheduleList().get(0); 
			setExamType(examSchedule.getExamTypeName());
			setTempId1(examSchedule.getExamTypeId());
			setTempId2(examSchedule.getClassSectionId());//below lines used to examschedule print in student. By cvs 27-05-14
			setTempString(examSchedule.getClassName());
			setCustId(examSchedule.getCustId());
			examSchedule = null;
		}
	}
/********************************************************************
 * Date              Name               Description
 * ========          ============       ==================
 * May 10, 2013      Seshu		        For displaying create and update screen of student activity screen.
/********************************************************************/ 
    @Actions( {
		@Action(value = "ajaxDoAddStudentActivities", results = { @Result(location = "admin/ajaxAddStudentActivities.jsp", name = "success") })
	})
	public String ajaxDoAddStudentActivities() throws URTUniversalException{
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDoAddStudentActivities' method");
		}
		try {
			StringBuffer query = new StringBuffer("custId=").append(getUserCustId()).append(" and academicYearId=").append(getUserAcademicYearId()).append(" and categoryName != ''  group by categoryName");
			setObjectList(adminManager.getAll(StudentActivities.class, query.toString()));
			if(getStudentActivities().getId() > 0){
				setStudentActivities((StudentActivities)adminManager.get(StudentActivities.class,getStudentActivities().getId()));
			}else
				setStudentActivities(null);
			query = null;
		}catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
/********************************************************************
 * Date              Name               Description
 * ========          ============       ==================
 * May 10, 2013      Seshu		        For storing student activities values.
 * Aug 29, 2013      Rama               for stoping duplicate activity names while adding and updating of the activity,and also restricted spaces.
/********************************************************************/
    @Actions( { @Action(value = "ajaxAddStudentActivity",  results = { @Result(location = "admin/ajaxViewStudentActivities.jsp", name = "success" )}) })
	public String ajaxAddStudentActivity() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxAddStudentActivity' method");
		}
		try
		{
			AcademicYear academicYear =getCurrentAcademicYear();
			StudentActivities activityName = null;
			StringBuffer query = null;
			if(academicYear.getId() >0 && !StringFunctions.isNullOrEmpty(getStudentActivities().getActivityName()) && getStudentActivities().getId() ==0) {		
				 query = new StringBuffer(" activityName='").append(getStudentActivities().getActivityName().trim()).append("' and academicYearId=").append(academicYear.getId());
				 activityName =(StudentActivities)adminManager.get(StudentActivities.class,query.toString());
				 log.debug("query:"+query.toString());
				if(ObjectFunctions.isNullOrEmpty(activityName)){
					activityName = new StudentActivities();
					activityName.setCreatedDate(new Date());
					activityName.setCreatedById(getUser().getId());
				}else{
					activityName =null;					
					super.addActionError(" This activity already added for this academicyear.");
				}
			}
			if(getStudentActivities().getId() !=0){
				query = new StringBuffer(" activityName='").append(getStudentActivities().getActivityName().trim()).append("' and academicYearId =").append(academicYear.getId()).append(" and id !=").append(getStudentActivities().getId());
				activityName = (StudentActivities)adminManager.get(StudentActivities.class,query.toString());
				log.debug("query:"+query.toString());
				if(ObjectFunctions.isNullOrEmpty(activityName)){
					activityName = (StudentActivities)adminManager.get(StudentActivities.class,getStudentActivities().getId());
				}
				else {
					activityName = null;
					super.addActionError("This activity already exist please provide another one.");
					
				}
			}			
			
			/*if(getStudentActivities().getId() >0)
				activityName = (StudentActivities)adminManager.get(StudentActivities.class,getStudentActivities().getId());*/
			/*else
				activityName =new StudentActivities();*/
			/*if(StringFunctions.isNullOrEmpty(getStudentActivities().getActivityName()))
				studentActivities.setActivityName("");
			else
				studentActivities.setActivityName(getStudentActivities().getActivityName().toUpperCase());*/
			/*if(StringFunctions.isNullOrEmpty(getStudentActivities().getActivityDescription().toUpperCase()))
				activityName.setActivityDescription("");
			else
				activityName.setActivityDescription(getStudentActivities().getActivityDescription());
			if(StringFunctions.isNullOrEmpty(getStudentActivities().getCategoryName().toUpperCase()))
				activityName.setCategoryName("");
			else
				activityName.setCategoryName(getStudentActivities().getCategoryName().toUpperCase());*/
		if(!ObjectFunctions.isNullOrEmpty(activityName)){			
			activityName.setActivityName(getStudentActivities().getActivityName().toUpperCase().trim());
			activityName.setAcademicYear(academicYear);
			activityName.setCreatedById(getUser().getId());
			activityName.setCreatedDate(new Date());
			activityName.setCustId(getUserCustId());
			activityName.setLastAccessDate(new Date());
			activityName.setLastUpdatedById(getUser().getId());
			activityName.setLastUpdatedDate(new Date());
			if(StringFunctions.isNullOrEmpty(getStudentActivities().getActivityDescription().toUpperCase()))
				activityName.setActivityDescription("");
			else
				activityName.setActivityDescription(getStudentActivities().getActivityDescription());
			if(StringFunctions.isNullOrEmpty(getStudentActivities().getCategoryName().toUpperCase()))
				activityName.setCategoryName("");
			else
				activityName.setCategoryName(getStudentActivities().getCategoryName().toUpperCase());
			adminManager.save(activityName);
			if(getStudentActivities().getId() >0){
				super.addActionMessage("Activity updated successfully");
			}else
				super.addActionMessage("Activity added successfully");
			activityName= null;
			academicYear = null;
			}
			}
		catch(Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		finally{
			ajaxManageStudentActivities();
		}
		return SUCCESS;
	}
/********************************************************************
 * Date              Name               Description
 * ========          ============       ==================
 * May 10, 2013      Seshu		        For validating whether student activity has activity types or not.
/********************************************************************/   
    @Actions( {
 		@Action(value = "ajaxRemoveStudentActivityInfo", results = { @Result(type = "json", name = "success",params = {"includeProperties","objectList.*"}) }) })
 	public String removeStudentActivityInfo() throws URTUniversalException {
 		if (log.isDebugEnabled()) {
 			log.debug("Entering 'ajaxRemoveStudentActivityInfo' method");
 		}
 		try {
 			if(getStudentActivities().getId() > 0){
 				StringBuffer query = new StringBuffer("academicYearId=").append(getUserAcademicYearId()).append(" and studentActivityId=").append(getStudentActivities().getId());
 				int activityTypesCount = adminManager.getCount("studentActivityTypes", query.toString());
 				JSONObject studentActivityTypesJson =new JSONObject().put("studActivities", activityTypesCount+"_"+getStudentActivities().getId());
				getResponse().getOutputStream().print(studentActivityTypesJson.toString());
				studentActivityTypesJson = null;
 			}
 		} catch (Exception ex) {
 			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
 		}
 		return null;
 	}
/********************************************************************
 * Date              Name               Description
 * ========          ============       ==================
 * May 10, 2013      Seshu		        For removing student activity and corresponding activity types information.
/********************************************************************/ 
    @Actions({
		@Action(value = "ajaxRemoveStudentActivity", results = { @Result(location = "admin/ajaxViewStudentActivities.jsp", name = "success" )})
	})
	public String ajaxRemoveStudentActivity() throws URTUniversalException {
	if (log.isDebugEnabled()) {
		log.debug("Entering 'ajaxRemoveStudentActivity' method");
	}
	try{
		if(getStudentActivities().getId() > 0){
			List<BigInteger> activityTypeIdsList = adminManager.getAll("select id from studentActivityTypes WHERE studentActivityId="+getStudentActivities().getId());
			if(ObjectFunctions.isNotNullOrEmpty(activityTypeIdsList)){
				String activityTypeIds = StringFunctions.convertListToCommaDelimitedString(activityTypeIdsList);
				adminManager.remove("studentAcademicPerformance","studentActivityTypeId in("+activityTypeIds+")");
				adminManager.remove("activityTypeClasses", "activityTypeId in ("+activityTypeIds+")");
				activityTypeIdsList = null;
			}
			adminManager.remove("studentActivityTypes", "studentActivityId="+getStudentActivities().getId());
			adminManager.remove(StudentActivities.class, getStudentActivities().getId());
			super.addActionMessage("Activity removed Successfully");
		}
	 }catch(Exception ex)
	 {
		ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	 }
	 finally{
		ajaxManageStudentActivities();
	 }
	 return SUCCESS;
  	}
/********************************************************************
 * Date              Name               Description
 * ========          ============       ==================
 * May 10, 2013      Seshu		        For downloading student activities template.
/********************************************************************/  
    @Actions({
    	@Action(value = "ajaxDownloadStudentsActivitiesMarkSheet", results = {})
    })
    public void ajaxDownloadStudentsActivitiesMarkSheet() throws URTUniversalException {
    if (log.isDebugEnabled()) {
    	log.debug("Entering 'ajaxDownloadStudentsActivitiesMarkSheet' method");
    }
    try
	{
   		if(getUserAcademicYearId() > 0 && StringFunctions.isNotNullOrEmpty(getAnyTitle()) && StringFunctions.isNotNullOrEmpty(getTempString())
   				 && StringFunctions.isNotNullOrEmpty(getClassSectionId()) ){
   			String fileName ;
   			List<Object[]> activityTypes=null;
   				fileName= "StudentsActivitiesTemplate_"+getAnyTitle()+"_"+getTempString();
   			ExcelView excelView = new ExcelView();
   			StringBuffer query = null;
   			getResponse().setContentType(excelView.getMimeType());
   			getResponse().setHeader("Content-Disposition","attachment; filename=" + fileName.replace(' ', '_')+ ".xls");
   			excelView.setWb(Workbook.createWorkbook(getResponse().getOutputStream()));
   			WritableFont font=new WritableFont(WritableFont.ARIAL,10,WritableFont.BOLD,true);
   			WritableCellFormat cellFormat=new WritableCellFormat(font);
   			cellFormat.setBackground(Colour.WHITE);
   			WritableCellFormat cellFormat8=new WritableCellFormat();
			WritableCellFormat cellFormat10=new WritableCellFormat();
			WritableFont boldfont10,boldfont8=null;
        	boldfont10 = new WritableFont(WritableFont.ARIAL, 10,WritableFont.BOLD, true);
        	cellFormat10 = new WritableCellFormat(boldfont10);
			boldfont10.setColour(Colour.WHITE);
			cellFormat10=ExcelView.getUserFormattedCell(boldfont10, Colour.GREEN, false, false, Alignment.CENTRE, VerticalAlignment.CENTRE,Border.NONE,BorderLineStyle.NONE);
			//For School Address
        	boldfont8 = new WritableFont(WritableFont.createFont("droidsans"),8,WritableFont.BOLD);
        	cellFormat8 = new WritableCellFormat(boldfont8);
	    	boldfont8.setColour(Colour.WHITE);
	    	cellFormat8=ExcelView.getUserFormattedCell(boldfont8, Colour.GREEN, false, false, Alignment.CENTRE, VerticalAlignment.CENTRE,Border.NONE,BorderLineStyle.NONE);
	    /*	query = new StringBuffer("select activity.id,activity.activityName From studentActivities activity JOIN studentActivityTypes type on(activity.id=type.studentActivityId) and activity.academicYearId=").append(getUserAcademicYearId())
	    	.append(" and activity.categoryName in (").append(getAnyId()).append(")").append(" group by activity.id");*/
	    		query = new StringBuffer("select activity.id,activity.activityName From studentActivities activity JOIN studentActivityTypes type on(activity.id=type.studentActivityId) JOIN activityTypeClasses activityClass on (activityClass.classId=")
	    		.append(getClassSectionId()).append(" and activityClass.activityTypeId=type.id) and activity.custId=").append(getUserCustId()).append(" and activity.academicYearId=").append(getUserAcademicYearId())
	    	    .append(" and activity.categoryName in (").append(getAnyId()).append(")").append(" group by activity.id");
	    	
	    	List<Object[]> studentActivities = adminManager.getAll(query.toString());
   			//List<StudentActivities> studentActivities= adminManager.getAll(StudentActivities.class, "academicYearId="+getUserAcademicYearId());
	    	StudentAcademicPerformance stuAcademicPer=(StudentAcademicPerformance) adminManager.get(StudentAcademicPerformance.class,"custId="+getUserCustId()+" and classandsectionId="+Long.valueOf(getClassSectionId())+" and academicYearId="+getUserAcademicYearId()+" and gradeStatus is not null group by classandsectionId");
	    	if(ObjectFunctions.isNullOrEmpty(stuAcademicPer) || !ObjectFunctions.isNullOrEmpty(stuAcademicPer) && stuAcademicPer.getGradeStatus().equalsIgnoreCase(getTitle())){
		    	if(ObjectFunctions.isNotNullOrEmpty(studentActivities)){
					AcademicYear academicYear = (AcademicYear)adminManager.get(AcademicYear.class,getUserAcademicYearId());
						excelView.setWorkSheetName(getAnyTitle());
	   		    	excelView.createWorkSheet(0);
	   		    	excelView.setDefaultFormat(excelView.getArial10format());
	   				//Customer customer = (Customer) adminManager.get(Customer.class,getUserCustId());
	   				Customer customer = getCustomerByCustId();
	   				if (!ObjectFunctions.isNullOrEmpty(customer)) {
	   					if (!ObjectFunctions.isNullOrEmpty(customer.getOrganization())) {
	   						excelView.getWs().mergeCells(0, 0, 10, 1);
	   						excelView.getWs().addCell(new Label(0, 0, customer.getOrganization(),cellFormat10));
	   					}
	   					if (StringFunctions.isNotNullOrEmpty(customer.getCustomerFormattedAddress())) {
	   						excelView.getWs().mergeCells(0, 2, 10, 2);
	   						excelView.getWs().addCell(new Label(0, 2, customer.getCustomerFormattedAddress(),cellFormat8));
	   					}
	   					customer = null;
	   				}
	   				excelView.getWs().mergeCells(0, 3, 10, 5);
	   				excelView.getWs().addCell(new Label(0,3, "Note :-\n You shouldn't change green colour fields.", excelView.getUser14format()));
	   				excelView.getWs().addCell(new Label(0,7, "Class", excelView.getUsermore10BoldformatGreenBgClr()));
	   				excelView.getWs().addCell(new Label(1,7, getAnyTitle(), excelView.getUsermore10BoldformatGreenBgClr()));
	   				excelView.getWs().addCell(new Label(2,7, "Exam Type", excelView.getUsermore10BoldformatGreenBgClr()));
	   				excelView.getWs().addCell(new Label(3,7, getClassSectionId(), excelView.getUsermore10BoldformatGreenBgClr()));
	   				excelView.getWs().mergeCells(4, 7, 6, 7);
	   				excelView.getWs().addCell(new Label(4,7, getTempString(), excelView.getUsermore10BoldformatGreenBgClr()));
	   				//for setting cell size
	   				excelView.getWs().setColumnView(0,10);
	   				excelView.getWs().setColumnView(1,20);
	   				excelView.getWs().setColumnView(2,30);
	   				excelView.getWs().addCell(new Label(0,8, "", excelView.getUsermore10BoldformatGreenBgClr()));
	   				excelView.getWs().addCell(new Label(0,9, "", excelView.getUsermore10BoldformatGreenBgClr()));
	   				excelView.getWs().addCell(new Label(0,10, "", excelView.getUsermore10BoldformatGreenBgClr()));
	   				if(getX()>0){
	   					excelView.getWs().addCell(new Label(0,11, "", excelView.getUsermore10BoldformatGreenBgClr()));
	   					excelView.getWs().addCell(new Label(0,12, "Admission Number", excelView.getUsermore10BoldformatGreenBgClr()));
	   					excelView.getWs().addCell(new Label(3,12, getTitle(), excelView.getUsermore10BoldformatGreenBgClr()));
	   				}else{
	   					excelView.getWs().addCell(new Label(0,11, "Admission Number", excelView.getUsermore10BoldformatGreenBgClr()));
	   					excelView.getWs().addCell(new Label(3,11, getTitle(), excelView.getUsermore10BoldformatGreenBgClr()));
	   				}
	   				excelView.getWs().addCell(new Label(1,8, "", excelView.getUsermore10BoldformatGreenBgClr()));
	   				excelView.getWs().addCell(new Label(1,9, "", excelView.getUsermore10BoldformatGreenBgClr()));
	   				excelView.getWs().addCell(new Label(1,10, "", excelView.getUsermore10BoldformatGreenBgClr()));
	   				if(getX()>0){
	   					excelView.getWs().addCell(new Label(1,11, "", excelView.getUsermore10BoldformatGreenBgClr()));
	   					excelView.getWs().addCell(new Label(1,12, "Student Roll No", excelView.getUsermore10BoldformatGreenBgClr()));
	   				}else{
	   					excelView.getWs().addCell(new Label(1,11, "Student Roll No", excelView.getUsermore10BoldformatGreenBgClr()));
	   				}
	   				excelView.getWs().addCell(new Label(2,8, "", excelView.getUsermore10BoldformatGreenBgClr()));
	   				excelView.getWs().addCell(new Label(2,9, "", excelView.getUsermore10BoldformatGreenBgClr()));
	   				excelView.getWs().addCell(new Label(2,10, "", excelView.getUsermore10BoldformatGreenBgClr()));
	   				if(getX()>0){
	   					excelView.getWs().addCell(new Label(2,11, "", excelView.getUsermore10BoldformatGreenBgClr()));
	   					excelView.getWs().addCell(new Label(2,12, "Student Name", excelView.getUsermore10BoldformatGreenBgClr()));
	   				}else{
	   					excelView.getWs().addCell(new Label(2,11, "Student Name", excelView.getUsermore10BoldformatGreenBgClr()));
	   				}
	   				int startMergeCell=4;
	   				int endMergeCell = 0;
	   				excelView.getWs().addCell(new Label(3,8,academicYear.isDispActivityDescField()+"", excelView.getUsermore10BoldformatGreenBgClr()));
						for(Object[] activity : studentActivities){
							if(!ObjectFunctions.isNullOrEmpty(activity[0]) && !ObjectFunctions.isNullOrEmpty(activity[1])){
								/*query = new StringBuffer("select activityType.id,activityType.activityTypeName FROM studentActivityTypes activityType where activityType.custId=").append(getUserCustId())
								.append(" and activityType.studentActivityId=").append(activity[0].toString()).append(" and activityType.academicYearId=").append(getUserAcademicYearId());
							
							*/
								query = new StringBuffer("select activityType.id,activityType.activityTypeName FROM studentActivityTypes activityType JOIN activityTypeClasses activityTypeClasses on (activityType.id=activityTypeClasses.activityTypeId and activityTypeClasses.classId=")
								.append(getClassSectionId()).append(") and activityType.academicYearId=").append(getUserAcademicYearId()).append(" and activityType.custId=").append(getUserCustId())
								.append(" and activityType.studentActivityId=").append(activity[0].toString());
								activityTypes = adminManager.getAll(query.toString());
								if(ObjectFunctions.isNotNullOrEmpty(activityTypes)){
									if(getX()>0){
										if(academicYear.isDispActivityDescField())
											endMergeCell = startMergeCell+activityTypes.size()*getX()*2-1;
										else
											endMergeCell = startMergeCell+activityTypes.size()*getX()-1;
									}else{
										if(academicYear.isDispActivityDescField())
											endMergeCell = startMergeCell+activityTypes.size()*2-1;
										else
											endMergeCell = startMergeCell+activityTypes.size()-1;
									}
									excelView.getWs().mergeCells(startMergeCell, 9, endMergeCell, 9);
			        				excelView.getWs().addCell(new Label(startMergeCell,9,activity[1].toString(), excelView.getUsermore10BoldformatGreenBgClr()));
			        				for(Object[] activityType: activityTypes){
			        					if(getX()>0){
			        						if(academicYear.isDispActivityDescField())
				        						excelView.getWs().mergeCells(startMergeCell, 10, startMergeCell+getX()*2 -1, 10);
				        					//excelView.getWs().mergeCells(startMergeCell, 10, (startMergeCell), 10);
				        					excelView.getWs().addCell(new Label(startMergeCell,10,activityType[1].toString(), excelView.getUsermore10BoldformatGreenBgClr()));
				        					if(academicYear.isDispActivityDescField())
												endMergeCell = startMergeCell+getX()*2-1;
											else
												endMergeCell = startMergeCell+getX()-1;
				        					
				        					if(StringFunctions.isNotNullOrEmpty(getEmpId().toString())){
				        						String stuAssessmentId[]=getEmpId().split(",");
				        						for(String assessmentId:stuAssessmentId){
				        							if(Long.valueOf(assessmentId)!=0){
				        								StudentsAssessments stuAssessment= (StudentsAssessments)adminManager.get(StudentsAssessments.class,"id="+Long.valueOf(assessmentId));
				        								if(!ObjectFunctions.isNullOrEmpty(stuAssessment)){
						        							excelView.getWs().mergeCells(startMergeCell, 11, startMergeCell+1, 11);
						        							excelView.getWs().addCell(new Label(startMergeCell,11,stuAssessment.getAssessmentName().toString(), excelView.getUsermore10BoldformatGreenBgClr()));
						        							excelView.getWs().addCell(new Label(startMergeCell,8, getExamType()+"_"+activityType[0].toString()+"_"+stuAssessment.getId()+"_Grade", excelView.getDefaultFormat()));
								        					if(academicYear.isDispActivityDescField())
								        						excelView.getWs().addCell(new Label(startMergeCell+1,8, getExamType()+"_"+activityType[0].toString()+"_"+stuAssessment.getId()+"_Description", excelView.getDefaultFormat()));
								        					if("P".equalsIgnoreCase(getTitle())){
								        						excelView.getWs().addCell(new Label(startMergeCell,12,"Points", excelView.getUsermore10BoldformatGreenBgClr()));
								        					}else if("M".equalsIgnoreCase(getTitle())){
								        						excelView.getWs().addCell(new Label(startMergeCell,12,"Marks", excelView.getUsermore10BoldformatGreenBgClr()));
								        					}else{
								        						excelView.getWs().addCell(new Label(startMergeCell,12,"Grade", excelView.getUsermore10BoldformatGreenBgClr()));
								        					}
								        					excelView.getWs().setColumnView(startMergeCell+1,20);
								        					if(academicYear.isDispActivityDescField()){
								        						excelView.getWs().addCell(new Label(startMergeCell+1,12,"Description", excelView.getUsermore10BoldformatGreenBgClr()));
								        						startMergeCell += 2;
								        					}else{
								        						startMergeCell++;
								        					}
								        					stuAssessment=null;
						        						}
				        							}assessmentId=null;
				        						}
				        					}
				        					activityType = null;
			        					}else{
			        						if(academicYear.isDispActivityDescField())
				        						excelView.getWs().mergeCells(startMergeCell, 10, startMergeCell+1, 10);
				        					excelView.getWs().addCell(new Label(startMergeCell,10,activityType[1].toString(), excelView.getUsermore10BoldformatGreenBgClr()));
				        					excelView.getWs().addCell(new Label(startMergeCell,8, getExamType()+"_"+activityType[0].toString()+"_Grade", excelView.getDefaultFormat()));
				        					if(academicYear.isDispActivityDescField())
				        						excelView.getWs().addCell(new Label(startMergeCell+1,8, getExamType()+"_"+activityType[0].toString()+"_Description", excelView.getDefaultFormat()));
				        					//excelView.getWs().addCell(new Label(startMergeCell,11,"Grade", excelView.getUsermore10BoldformatGreenBgClr()));
				        					if("P".equalsIgnoreCase(getTitle())){
				        						excelView.getWs().addCell(new Label(startMergeCell,11,"Points", excelView.getUsermore10BoldformatGreenBgClr()));
				        					}else if("M".equalsIgnoreCase(getTitle())){
				        						excelView.getWs().addCell(new Label(startMergeCell,11,"Marks", excelView.getUsermore10BoldformatGreenBgClr()));
				        					}else{
				        						excelView.getWs().addCell(new Label(startMergeCell,11,"Grade", excelView.getUsermore10BoldformatGreenBgClr()));
				        					}
				        					excelView.getWs().setColumnView(startMergeCell+1,20);
				        					if(academicYear.isDispActivityDescField()){
				        						excelView.getWs().addCell(new Label(startMergeCell+1,11,"Description", excelView.getUsermore10BoldformatGreenBgClr()));
				        						startMergeCell += 2;
				        					}else{
				        						startMergeCell++;
				        					}
				        					activityType = null;
			        					}
			        				}
			        				startMergeCell = endMergeCell+1;
								}
							}
							activityTypes = null;
							activity = null;
						}
						CellView cv=new CellView();
	   		        cv.setHidden(true);
	   		        excelView.getWs().setRowView(8, cv);
	   		        cv=new CellView();
	   		        cv.setHidden(true);
	   		        excelView.getWs().setColumnView(3, cv);
	   		      int studentRowSize=0;
		   		     if(getX()>0){
		   		    	studentRowSize=13;
		   		     }else{
		   		    	studentRowSize=12;
		   		     }
	   		        jxl.Cell cell = null;
	   		        List<Object[]> studentActivityMarks = null;
	   		        List<Object[]> studentDetails=null;
	   		       
	   		        //studentDetails=adminManager.getAll("select admissionNumber,studId,fullName,rollNumber from vw_studentClassDetails where academicYearId="+getUserAcademicYearId()+" and classSectionId="+getClassSectionId()+" and accountExpired='N' order by "+getEventId());
	   		     studentDetails=adminManager.getAll("select admissionNumber,studId,fullName,rollNumber from vw_studentClassDetails where academicYearId="+getUserAcademicYearId()+" and classSectionId="+getClassSectionId()+" and description is null order by "+getEventId());
	   		        if(("N".equalsIgnoreCase(getCustomerByCustId().getAlphaNumericRollNumber()) || StringFunctions.isNullOrEmpty(getCustomerByCustId().getAlphaNumericRollNumber())) && "rollNumber".equalsIgnoreCase(getEventId()))
	   		        	Collections.sort(studentDetails,new StudentRollNumberComparator());
	   		       
					if(ObjectFunctions.isNotNullOrEmpty(studentDetails)){
						for(Object[] stud: studentDetails){
							excelView.getWs().addCell(new Label(0,studentRowSize, stud[0].toString(), excelView.getUsermore10BoldformatGreenBgClr()));
							excelView.getWs().addCell(new Label(3,studentRowSize, stud[1].toString(), excelView.getUsermore10BoldformatGreenBgClr()));
	   						excelView.getWs().addCell(new Label(2,studentRowSize, stud[2].toString(), excelView.getUsermore10BoldformatGreenBgClr()));
	   						excelView.getWs().addCell(new Label(1,studentRowSize, stud[3].toString(), excelView.getUsermore10BoldformatGreenBgClr()));
	   						if(getX()>0){
	   							studentActivityMarks = adminManager.getAll("select examTypeId,studentActivityTypeId,grade,description,studentsAssessmentId from  studentAcademicPerformance where studId="+stud[1].toString()+" and examTypeId="+getExamType());
	   						}else{
	   							studentActivityMarks = adminManager.getAll("select examTypeId,studentActivityTypeId,grade,description from  studentAcademicPerformance where studId="+stud[1].toString()+" and examTypeId="+getExamType());
	   						}
	   						if(ObjectFunctions.isNotNullOrEmpty(studentActivityMarks)){
	   							for(Object[] academicPerformance :studentActivityMarks){
	   								if(getX()>0){
	   									cell=excelView.getWs().findCell(academicPerformance[0].toString()+"_"+academicPerformance[1].toString()+"_"+academicPerformance[4].toString()+"_Grade");
	   								}else{
	   									cell=excelView.getWs().findCell(academicPerformance[0].toString()+"_"+academicPerformance[1].toString()+"_Grade");
	   								}
	   								if(!ObjectFunctions.isNullOrEmpty(cell)){
	   										if(!ObjectFunctions.isNullOrEmpty(academicPerformance[2]))
	   											excelView.getWs().addCell(new Label(cell.getColumn(),studentRowSize, academicPerformance[2].toString(), excelView.getDefaultFormat()));
	   								}
	   								if(getX()>0){
	   									cell=excelView.getWs().findCell(academicPerformance[0].toString()+"_"+academicPerformance[1].toString()+"_"+academicPerformance[4].toString()+"_Description");
	   								}else{
	   									cell=excelView.getWs().findCell(academicPerformance[0].toString()+"_"+academicPerformance[1].toString()+"_Description");
	   								}
	   								if(!ObjectFunctions.isNullOrEmpty(cell)){
	   										if(!ObjectFunctions.isNullOrEmpty(academicPerformance[3]))
	   											excelView.getWs().addCell(new Label(cell.getColumn(),studentRowSize, academicPerformance[3].toString(), excelView.getDefaultFormat()));
	   								}
	   								academicPerformance = null;
	   								cell = null;
	   							}
	   						}
	   						studentActivityMarks = null;
	   						studentRowSize++;
	   						stud = null;
							}
						showSchoolUrlInExcelSheetFooter(studentRowSize-1, excelView, startMergeCell-1);
						}else{
							excelView.getWs().mergeCells(4, studentRowSize, 8, studentRowSize);
							excelView.getWs().addCell(new Label(4,studentRowSize, "No students found for this class.", excelView.getDefaultFormat()));
						}
						studentDetails = null;
						studentActivities = null;
					}
	   			if(excelView.getWb().getNumberOfSheets() <= 0){
						if(StringFunctions.isNotNullOrEmpty(getAnyTitle()))
							excelView.setWorkSheetName(getAnyTitle());
						else
							excelView.setWorkSheetName("Class");
				    	excelView.createWorkSheet(0);
				    	excelView.addHeader();
				    	excelView.setDefaultFormat(excelView.getArial10format());
				    	excelView.getWs().mergeCells(0, 10, 7, 10);
						excelView.getWs().addCell(new Label(0,10, "No Activities Found.", excelView.getUser14format()));
				}
	    	}else{
	    		excelView.setWorkSheetName(getAnyTitle());
	    		excelView.createWorkSheet(0);
   		    	excelView.setDefaultFormat(excelView.getArial10format());
   		    	excelView.getWs().mergeCells(0, 3, 10, 5);
   		    	if("P".equalsIgnoreCase(stuAcademicPer.getGradeStatus())){
   		    		excelView.getWs().addCell(new Label(0,3, "Note :-\n You could not download the sheet,Because you already provided the Points information for some of the students in "+getAnyTitle()+". Please select points to enter the data.", excelView.getUser14format()));
   		    	}else if("M".equalsIgnoreCase(stuAcademicPer.getGradeStatus())){
   		    		excelView.getWs().addCell(new Label(0,3, "Note :-\n You could not download the sheet,Because you already provided the  Marks information for some of the students in "+getAnyTitle()+". Please select marks to enter the data.", excelView.getUser14format()));
   		    	}else{
   		    		excelView.getWs().addCell(new Label(0,3, "Note :-\n You could not download the sheet,Because you already provided the Grades information for some of the students in "+getAnyTitle()+". Please select grades to enter the data.", excelView.getUser14format()));
   		    	}
		    }
	    	stuAcademicPer=null;
			excelView.getWb().write();
			excelView.getWb().close();
			cellFormat8=cellFormat10=cellFormat = null;
			boldfont10=boldfont8=font = null;
			excelView = null;
   		}
		}catch(Exception ex)
    	{
    		ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
    	}
    }
/********************************************************************
 * Date              Name               Description
 * ========          ============       ==================
 * May 10, 2013      Seshu		        Displaying classes for generating student activities template
/********************************************************************/  
    @Actions({
      @Action(value = "ajaxDownloadActivitiesTemplate", results = { @Result(location = "admin/ajaxDownloadStudentsActivitiesTemplate.jsp", name = "success") }) })
    	public String ajaxDownloadActivitiesTemplate() throws URTUniversalException {
    	if (log.isDebugEnabled()) {
    		log.debug("Entering 'ajaxDownloadActivitiesTemplate' method");
    	}
    	try
    	{
    		setStudyClassList(adminManager.GetAllStudyClasses(getUserCustId(), getUserAcademicYearId(),null));
    	}
    	catch(Exception ex)
    	{
    		ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
    	}
   		return SUCCESS;
   	}
    /********************************************************************
     * Date              Name               Description
     * ========          ============       ==================
     * May 10, 2013      Seshu		        Displaying classes for generating student activities template
    /********************************************************************/   
    @Actions({
        @Action(value = "ajaxUploadStudentsActivitiesMarksSheet", results = { @Result(location = "admin/ajaxUploadActivitiesGradesSheet.jsp", name = "success"),
                    @Result(location = "admin/ajaxUploadActivitiesGradesSheet.jsp", name = "dummyInit")}) })
            public String ajaxUploadStudentsActivitiesMarksSheet() throws URTUniversalException {
            if (log.isDebugEnabled()) {
                log.debug("Entering 'ajaxUploadStudentsActivitiesMarksSheet' method");
            }
            try
            {
            log.debug("file Type="+getUploadContentType());
            boolean excelFileType = false;
			excelFileType = validateExcelFileType(getUploadContentType());
			if(excelFileType){
				log.debug("No file to upload....");
				super.addActionError("File type not matched.");
				  return "dummyInit";
			}
           /* if(!(Constants.FILE_TYPE_XLS.equalsIgnoreCase(getUploadContentType()) 
            		|| Constants.FILE_TYPE_APPLICATION_XLS.equalsIgnoreCase(getUploadContentType())
                    || Constants.FILE_TYPE_APPLICATION_STREAM_CSV.equalsIgnoreCase(getUploadContentType())
                    || Constants.FILE_TYPE_APPLICATION_MS_EXCEL.equalsIgnoreCase(getUploadContentType())
                    || Constants.FILE_TYPE_APPLICATION_DOWNLOAD.equalsIgnoreCase(getUploadContentType())
                    || Constants.FILE_TYPE_APPLICATION_MSEXCEL.equalsIgnoreCase(getUploadContentType())))
            {
                log.debug("No file to upload....");
                super.addActionError("File type not matched.");
                return "dummyInit";
            }   */ 
                 if (getUserAcademicYearId() > 0) {
                     WorkbookSettings ws = new WorkbookSettings();
                     ws.setLocale(new Locale("en", "EN"));
                     Workbook workbook = Workbook.getWorkbook(getUpload(),ws);
                     Sheet sheet=null;
                     jxl.Cell cell;
                     String studentId,grade,description=null;
                     grade =null;
                     String[] ids = null;
                     String gradeStatus=null;
                     String classAndSectionId=null;
                     String[] rowsSize =null;
                     Student student=null;
                     boolean isMarksUploaded=false;
                     StudentAcademicPerformance academicPerformance = null; 
                     AcademicYear academicYear = getCurrentAcademicYear();
                     StudentActivityTypes activityType = null;
                     ExamTypes examtype = null;
                     StudentsAssessments studAssessments = null;
                     HashMap<String, StudentActivityTypes> activityTypesMap = new HashMap<String, StudentActivityTypes>();
                     HashMap<String, ExamTypes> examTypesMap = new HashMap<String, ExamTypes>();
                     HashMap<String, StudentsAssessments> studentsAssessmentsMap = new HashMap<String, StudentsAssessments>();
                     for(int sheetNum=0; sheetNum < workbook.getNumberOfSheets(); sheetNum++){
                       sheet = workbook.getSheet(sheetNum);
                       int rowSize = sheet.getRows();
                       int columnSize = sheet.getColumns();
                       String dispDescp = sheet.getCell(3,8).getContents();
                       if(StringFunctions.isNullOrEmpty(dispDescp)){
                           super.addActionError("Please download latest template sheet from system and upload.");
                           return SUCCESS;
                       }else{
                         //  StringBuffer sql = new StringBuffer("custId=").append(getUserCustId()).append(" and academicYearId=").append(getUserAcademicYearId());
                         //  List studentAssessments = adminManager.getAll(StudentsAssessments.class, sql.toString());
                           int n=0;
                     	  cell=sheet.getCell(4,8);
                     	 if(StringFunctions.isNotNullOrEmpty(cell.getContents())){
                     		 rowsSize = cell.getContents().split("_");
	                     	 if(rowsSize.length >3){
	                            n=13;
	                     	 	cell=sheet.getCell(3,12);
	                     	 }else{
	                     		 n=12;
	                     	 	cell=sheet.getCell(3,11);}
                     	 }
                     	gradeStatus=cell.getContents();
                     	cell=sheet.getCell(3,7);
                     	classAndSectionId=cell.getContents();
                       for(int i=n;i < rowSize; i++)
                           {
                           cell=sheet.getCell(3,i);
                           studentId=cell.getContents();
                           if(StringFunctions.isNotNullOrEmpty(studentId)){
                               student=adminManager.getStudentByCustIdAndStudentIdAndStatus(Long.valueOf(studentId),getUserCustId(),"",getUserAcademicYearId());
                               if(!ObjectFunctions.isNullOrEmpty(student)){
                                   for(int j=4; j < columnSize ;)
                                   {
                                       cell=sheet.getCell(j,8);                                       
                                       if(StringFunctions.isNotNullOrEmpty(cell.getContents())){
                                           ids=cell.getContents().split("_");
                                           if(!ObjectFunctions.isNullOrEmpty(ids[0]) && !ObjectFunctions.isNullOrEmpty(ids[1])){
                                               if(activityTypesMap.containsKey(ids[1]))
                                                   activityType = activityTypesMap.get(ids[1]);
                                               else{
                                                   activityType = (StudentActivityTypes)adminManager.get(StudentActivityTypes.class,"id="+ids[1]);
                                                   activityTypesMap.put(ids[1], activityType);
                                               }
                                               if(examTypesMap.containsKey(ids[0]))
                                                   examtype = examTypesMap.get(ids[0]);
                                               else{
                                                   examtype = (ExamTypes)adminManager.get(ExamTypes.class, "id="+ids[0]);
                                                   examTypesMap.put(ids[0], examtype);
                                               }
                                               if(rowsSize.length >3){
                                                   if("Grade".equalsIgnoreCase(ids[2])){
                                                       grade=sheet.getCell(j,i).getContents();
                                                   }
                                                   else{
                                                       if(studentsAssessmentsMap.containsKey(ids[2]))
                                                           studAssessments = studentsAssessmentsMap.get(ids[2]);
                                                       else{
                                                           studAssessments = (StudentsAssessments)adminManager.get(StudentsAssessments.class, "id="+ids[2]);
                                                           studentsAssessmentsMap.put(ids[2], studAssessments);
                                                       }
                                                   }
                                                   if(ids.length > 3){
                                                   if("Grade".equalsIgnoreCase(ids[3])){
                                                       grade=sheet.getCell(j,i).getContents();
                                                   }
                                                   if(dispDescp.equalsIgnoreCase("true")){
                                                       cell=sheet.getCell(j+1,8);
                                                       if(StringFunctions.isNotNullOrEmpty(cell.getContents())){
                                                           ids=cell.getContents().split("_");
                                                           if(!ObjectFunctions.isNullOrEmpty(ids[3]) && "Description".equalsIgnoreCase(ids[3])){
                                                               description=sheet.getCell(j+1,i).getContents();
                                                           }
                                                       }
                                                       j+=2;
                                                   }else{
                                                       j++;
                                                   }
                                                   }
                                                   else{
                                                       if(dispDescp.equalsIgnoreCase("true")){
                                                           cell=sheet.getCell(j+1,8);
                                                           if(StringFunctions.isNotNullOrEmpty(cell.getContents())){
                                                               ids=cell.getContents().split("_");
                                                               if(!ObjectFunctions.isNullOrEmpty(ids[2]) && "Description".equalsIgnoreCase(ids[2])){
                                                                   description=sheet.getCell(j+1,i).getContents();
                                                               }
                                                           }
                                                           j+=2;
                                                       }else{
                                                           j++;
                                                       }
                                                   }
                                               }else{
                                                   if("Grade".equalsIgnoreCase(ids[2])){
                                                       grade=sheet.getCell(j,i).getContents();
                                                   }
                                                   if(dispDescp.equalsIgnoreCase("true")){
                                                       cell=sheet.getCell(j+1,8);
                                                       if(StringFunctions.isNotNullOrEmpty(cell.getContents())){
                                                           ids=cell.getContents().split("_");
                                                           if(!ObjectFunctions.isNullOrEmpty(ids[2]) && "Description".equalsIgnoreCase(ids[2])){
                                                               description=sheet.getCell(j+1,i).getContents();
                                                           }
                                                       }
                                                       j+=2;
                                                   }else{
                                                       j++;
                                                   }
                                               }
                                               if(StringFunctions.isNotNullOrEmpty(grade) || StringFunctions.isNotNullOrEmpty(description)){
                                                   if(rowsSize.length >3 && !ObjectFunctions.isNullOrEmpty(studAssessments)){
                                                       academicPerformance = (StudentAcademicPerformance)adminManager.get(StudentAcademicPerformance.class, "custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and studId="+studentId+" and  studentActivityTypeId="+ids[1]+" and examTypeId="+ids[0]+" and studentsAssessmentId="+studAssessments.getId());
                                                   }else{
                                                       academicPerformance = (StudentAcademicPerformance)adminManager.get(StudentAcademicPerformance.class, "custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and studId="+studentId+" and  studentActivityTypeId="+ids[1]+" and examTypeId="+ids[0]);
                                                   }
                                                   if(ObjectFunctions.isNullOrEmpty(academicPerformance)){
                                                       academicPerformance = new StudentAcademicPerformance();
                                                       academicPerformance.setCreatedById(getUser().getId());
                                                       academicPerformance.setCreatedDate(new Date());
                                                       academicPerformance.setExamType(examtype);
                                                       academicPerformance.setStudentActivityType(activityType);
                                                       academicPerformance.setAcademicYear(academicYear);
                                                       academicPerformance.setCustId(getUserCustId());
                                                       if(rowsSize.length >3 && !ObjectFunctions.isNullOrEmpty(studAssessments)){
                                                           academicPerformance.setStudentsAssessmentId(Long.valueOf(studAssessments.getId()));
                                                       }else{
                                                           academicPerformance.setStudentsAssessmentId(0);
                                                       }
                                                       academicPerformance.setStudent(student);
                                                   }
                                                   academicPerformance.setGradeStatus(gradeStatus);
                                                   academicPerformance.setClassAndSectionId(Long.valueOf(classAndSectionId));
                                                   academicPerformance.setDescription(description);
                                                   academicPerformance.setGrade(grade);
                                                   academicPerformance.setLastAccessDate(new Date());
                                                   academicPerformance.setLastUpdatedById(getUser().getId());
                                                   academicPerformance.setLastUpdatedDate(new Date());
                                                   //student.addStudentActivitiesGrades(academicPerformance);
                                                   adminManager.merge(academicPerformance);
                                                   isMarksUploaded=true;
                                                   academicPerformance = null;
                                               }
                                               else{
                                            	Object[] studentActivity = adminManager.get("select id,grade from studentAcademicPerformance where custId="+getUserCustId()+" and studId = "+ student.getId() +" and studentActivityTypeId="+Long.valueOf(ids[1]));
   			   	 								if(!ObjectFunctions.isNullOrEmpty(studentActivity)){
   			   	 									adminManager.remove("studentAcademicPerformance", "id="+studentActivity[0].toString());
   			   	 								}
   			   	 							   studentActivity = null;
                                               }
                                           }else{
                                               j++;
                                           }
                                       }else{
                                           j++;
                                       }
                                       description = null;
                                       grade = null;
                                   }
                                   //adminManager.merge(student);
                               }
                               student = null;
                           }
                           }
                       }
                     }
                     activityTypesMap = null;    
                     examTypesMap=null;
                     if(isMarksUploaded)
                         super.addActionMessage("Students Activities performance uploaded successfully.");
                     else
                       super.addActionError("Student Activities performance not uploaded.");
                }
            }
            catch(Exception ex)
            {
                ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
                JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
            }
            
            return SUCCESS;
        }
/********************************************************************
 * Date              Name               Description
 * ========          ============       ==================
 * May 10, 2013       Seshu		        For managing activities grades
/********************************************************************/	   
   @Actions( { @Action(value = "ajaxManageStudentActivitiesGrades", results = { @Result(location = "admin/ajaxManageActivitiesGrades.jsp", name = "success") }) })
	public String ajaxManageStudentActivitiesGrades() {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxManageStudentActivitiesGrades' method");
		}
		try {
			setObjectList(adminManager.getAll(ActivitiesGrades.class,"academicYearId="+getUserAcademicYearId()));
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
/********************************************************************
 * Date              Name               Description
 * ========          ============       ==================
 * May 10, 2013       Seshu		        For managing activities grades
/********************************************************************/	 
   @Actions( {
		@Action(value = "ajaxAddOrUpdateActivitiesGrades", results = { @Result(location = "admin/ajaxManageActivitiesGrades.jsp", name = "success")
				})})
		public String ajaxAddOrUpdateActivitiesGrades() throws URTUniversalException {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxAddOrUpdateActivitiesGrades' method");
			}
			try {
				if(StringFunctions.isNotNullOrEmpty(getTempString())){
					JSONArray classActivitiesGradesJSONArray=new JSONArray(getTempString());
					JSONObject activitiesGradesJson=null;
					long activitiesGradeId=0;
					String gradeName = null;
					String gradePoint = null;
					ActivitiesGrades activitiesGrades= null;
					AcademicYear academicYear = getCurrentAcademicYear();
					for(int i=0;i<classActivitiesGradesJSONArray.length();i++)
					{
						activitiesGradesJson=classActivitiesGradesJSONArray.getJSONObject(i);
						if(!ObjectFunctions.isNullOrEmpty(activitiesGradesJson))
						{
							gradeName = (String) activitiesGradesJson.get("gradeName");
							gradePoint = (String) activitiesGradesJson.get("gradePoint");
							if(StringFunctions.isNotNullOrEmpty(gradeName) && StringFunctions.isNotNullOrEmpty(gradePoint)){
								if(StringFunctions.isNotNullOrEmpty((String)activitiesGradesJson.get("activitiesGradeId")) && Long.valueOf((String)activitiesGradesJson.get("activitiesGradeId")) > 0){
									activitiesGradeId = Long.valueOf((String)activitiesGradesJson.get("activitiesGradeId"));
									activitiesGrades =(ActivitiesGrades)adminManager.get(ActivitiesGrades.class,"id="+activitiesGradeId);
								}else{
									activitiesGrades = new ActivitiesGrades();
									activitiesGrades.setCreatedById(getUser().getId());
									activitiesGrades.setCreatedDate(new Date());
								}
								if(!ObjectFunctions.isNullOrEmpty(activitiesGrades)){
									activitiesGrades.setGradeName(gradeName);
									activitiesGrades.setGradePoint(Float.valueOf(gradePoint));
									activitiesGrades.setCustId(getUserCustId());
									activitiesGrades.setAcademicYear(academicYear);
									activitiesGrades.setLastAccessDate(new Date());
									activitiesGrades.setLastUpdatedById(getUser().getId());
									activitiesGrades.setLastUpdatedDate(new Date());
									adminManager.save(activitiesGrades);
									activitiesGrades = null;
								}
							}
						}
					}
				}
				ajaxManageStudentActivitiesGrades();
				super.addActionMessage("You have successfully added activities grades.");
			} catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return SUCCESS;
	}
   /********************************************************************
    * Date              Name               Description
    * ========          ============       ==================
    * May 10, 2013      Seshu		        Display all classes for generating students progress report.
   /********************************************************************/	
        @Actions( {
      		@Action(value = "ajaxDoGetStudentProgressReport", results = { @Result(location = "progressReport/ajaxProgressReportStudyClasses.jsp", name = "success") })
      	})
      	public String ajaxDoGetStudentProgressReport() throws URTUniversalException {
      		if (log.isDebugEnabled()) {
      			log.debug("Entering 'ajaxDoGetStudentProgressReport' method");
      		}
      		try {
      			if(getUser().isSchoolTeacher()){
      				HashSet<StudyClass> classSections = new HashSet<StudyClass>();
      				List<StudyClass> studyClassList = adminManager.getStudyClassesForClassTeacherAndAdmin(getUser(),getUserAcademicYearId());
   				if(!ObjectFunctions.isNullOrEmpty(studyClassList)){
   					classSections.addAll(studyClassList);
   				}
   				if(getUser().isOnlySchoolHod())
   				{
   					Object[] staff = adminManager.get("select id,description from staff where accountId="+getUser().getId()+" and  status='Y'");
   					if(!ObjectFunctions.isNullOrEmpty(staff))
   					{
   						if(!ObjectFunctions.isNullOrEmpty(staff[0]))
   						{
   							List studyClassesList =getHodStudyClassesList(Long.valueOf(staff[0].toString()),getUserAcademicYearId());
   							if(!ObjectFunctions.isNullOrEmpty(studyClassesList))
   							{
   								classSections.addAll(studyClassesList);
   							}
   							studyClassesList = null;
   						}	
   					}
   				}
   				if (ObjectFunctions.isNotNullOrEmpty(classSections)) {
   					setStudyClassList(ConvertUtil.convertSetToList(classSections));
   					Collections.sort(getStudyClassList());
   				}
   			classSections = null;
      			}else
      				setStudyClassList(adminManager.GetAllStudyClasses(getUserCustId(), getUserAcademicYearId(),null));
      		} catch (Exception ex) {
      			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
      		}
      		return SUCCESS;
      	}
/********************************************************************
 * Date              Name               Description
 * ========          ============       ==================
 * May 10, 2013      Seshu		        Display marks uploaded exam types.
/********************************************************************/
    @Actions( { @Action(value = "ajaxGetClassExamTypesList", results = { @Result(location = "progressReport/ajaxMarksUploadedExamtypes.jsp", name = "success") }) })
   	public String ajaxGetClassExamTypesList() throws URTUniversalException {
   		if (log.isDebugEnabled()) {
   			log.debug("Entering 'ajaxGetClassExamTypesList' method");
   		}
   		try {
   			if(StringFunctions.isNotNullOrEmpty(getClassId())){
   				setExamTypeList(getMarksUploadedExamTypesByClassId(Long.valueOf(getClassId())));
   			}
   		} catch (Exception ex) {
   			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
   		}
   		return SUCCESS;
   	}  
/********************************************************************
 * Date              Name               Description
 * ========          ============       ==================
 * May 10, 2013       Seshu		        For displaying all activity types of a activity.
/********************************************************************/	
    @Actions({
		@Action(value = "ajaxViewAllStudentActivitiesTypes", results = { @Result(location = "progressReport/ajaxViewAllActivityTypes.jsp", name = "success") }) })
		public String ajaxViewAllStudentActivitiesTypes() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxViewAllStudentActivitiesTypes' method");  
		}
		try
		{
			if(getStudentActivities().getId() > 0) {
				StringBuffer sql = new StringBuffer(" studentActivityId = ").append(getStudentActivities().getId()).append(" and custId=").append(getUserCustId())
				.append(" and academicYearId=").append(getUserAcademicYearId());
				setTempList(adminManager.getAll(StudentActivityTypes.class, sql.toString()));
				sql = null;
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
/********************************************************************
 * Date              Name               Description
 * ========          ============       ==================
 * May 10, 2013       Seshu		        For displaying activity type creation and edit form
/********************************************************************/	
    @Actions({
		@Action(value = "ajaxDoAddNewActivityType", results = { @Result(location = "progressReport/ajaxAddActivityTypes.jsp", name = "success") }) })
		public String doAddNewActivityType() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'doAddNewActivityType' method");
		}
		try
		{
			if(getStudentActivityTypes().getId() > 0 ){
				setStudentActivityTypes((StudentActivityTypes)adminManager.get(StudentActivityTypes.class, getStudentActivityTypes().getId()));
			}else
				setStudentActivityTypes(null);
			setStudyClassList(adminManager.GetAllStudyClasses(getUserCustId(), getUserAcademicYearId(),null));
		}
		catch(Exception ex)
		{
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
/********************************************************************
 * Date              Name               Description
 * ========          ============       ==================
 * May 10, 2013       Seshu		        For adding or updating activity type values
 * Aug 29, 2013       Rama              For stoping duplicate activity types while creating and updating.
/********************************************************************/   
    @Actions({
		@Action(value = "ajaxAddStudentActivityTypes", results = { @Result(location = "progressReport/ajaxViewAllActivityTypes.jsp", name = "success") }) })
		public String addStudentActivityTypes() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'addStudentActivityTypes' method");
		}
		try
		{	
			if(getStudentActivities().getId() > 0 && StringFunctions.isNotNullOrEmpty(getStudentActivityTypes().getActivityTypeName())
					&& StringFunctions.isNotNullOrEmpty(getStudentActivityTypes().getSelectedClassIds())){
				StudentActivityTypes activityTypeName=null;
				StringBuffer query = null;
 				StudentActivities studentActivities=(StudentActivities)libraryManager.get(StudentActivities.class, getStudentActivities().getId());
				if(!ObjectFunctions.isNullOrEmpty(studentActivities)){
					StringBuffer selectedClassIds = new StringBuffer(getStudentActivityTypes().getSelectedClassIds());
						query = new StringBuffer("activityTypeName='").append(getStudentActivityTypes().getActivityTypeName().trim())
						.append("' and custId=").append(getUserCustId()).append(" and academicYearId=")
						.append(getUserAcademicYearId()).append(" and id !=").append(getStudentActivityTypes().getId()).append(" and studentActivityId=").append(getStudentActivities().getId());
						activityTypeName = (StudentActivityTypes)adminManager.get(StudentActivityTypes.class,query.toString());
						query = null;
						if(ObjectFunctions.isNullOrEmpty(activityTypeName)){
							if( getStudentActivityTypes().getId() == 0){
								activityTypeName = new StudentActivityTypes();
						    }
							else{
								 activityTypeName = (StudentActivityTypes)adminManager.get(StudentActivityTypes.class,"id="+getStudentActivityTypes().getId()+" and custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and studentActivityId="+getStudentActivities().getId());
								}
								AcademicYear academicYear = getCurrentAcademicYear();
								activityTypeName.setActivityTypeName(getStudentActivityTypes().getActivityTypeName().trim().toUpperCase());
								activityTypeName.setActivityTypeDescription(getStudentActivityTypes().getActivityTypeDescription());
								activityTypeName.setCustId(getUserCustId());
								activityTypeName.setAcademicYear(academicYear);
								if(!ObjectFunctions.isNullOrEmpty(activityTypeName)){
									List<StudyClass> classNames = adminManager.GetAllStudyClasses(getUserCustId(),getUserAcademicYearId(),"("+selectedClassIds.toString()+")");
									if(ObjectFunctions.isNullOrEmpty(classNames))
										activityTypeName.setActivityTypClasses(null);
									else
										activityTypeName.setActivityTypClasses(ConvertUtil.convertListToSet(classNames));
									studentActivities.addStudentActivityTypes(activityTypeName);
									adminManager.merge(studentActivities);
									activityTypeName = null;
									studentActivities=null;
									if(getStudentActivityTypes().getId()!= 0)
										super.addActionMessage("Activity type updated successfully.");
									else
										super.addActionMessage("Activity type added successfully.");
								} 
								academicYear=null;	
						}else{
							super.addActionError("Activity type name already exist. Please provide another activity.");
						}
						
			    }
			} 
		}
		catch(Exception ex)
		{
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		finally{
			ajaxViewAllStudentActivitiesTypes();
		}
		return SUCCESS;
	}
    /********************************************************************
     * Date              Name               Description
     * ========          ============       ==================
     * May 10, 2013       Seshu		        For validating activity type have marks or not
    /********************************************************************/   
    @Actions( {
		@Action(value = "ajaxRemoveStudentActivityTypeInfo",  results = { @Result(location = "progressReport/ajaxViewAllActivityTypes.jsp", name = "success" )} ) })
	public String removeStudentActivityTypeInfo() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxRemoveStudentActivityTypeInfo' method");
		}
		try {
			if(getStudentActivityTypes().getId() > 0){
				StringBuffer query = new StringBuffer("custId=").append(getUserCustId()).append(" and studentActivityTypeId=").append(getStudentActivityTypes().getId())
				.append(" and academicYearId=").append(getUserAcademicYearId());
				int count = adminManager.getCount("studentAcademicPerformance", query.toString());
				if(count > 0){
					super.addActionError("Activity type assigned to this student.");
					return SUCCESS;
				}else{
					adminManager.remove(StudentActivityTypes.class, getStudentActivityTypes().getId());
					super.addActionMessage("Activity type deleted successfully");
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		finally{
			ajaxViewAllStudentActivitiesTypes();
		}
		return SUCCESS;
	}
/********************************************************************
 * Date              Name               Description
 * ========          ============       ==================
 * May 13, 2013       Seshu		        For removing activity types 
/********************************************************************/ 
    @Actions({
		@Action(value = "ajaxRemoveStudentActivityType", results = { @Result(location = "progressReport/ajaxViewAllActivityTypes.jsp", name = "success" )})
	})
	public String ajaxRemoveStudentActivityType() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxRemoveStudentActivityType' method");
		}
		try
		{
		 if(getStudentActivityTypes().getId() > 0){
			adminManager.remove("studentAcademicPerformance", "studentActivityTypeId="+getStudentActivityTypes().getId());
			adminManager.remove(StudentActivityTypes.class, getStudentActivityTypes().getId());
			super.addActionMessage("Activity type deleted successfully");
			/*StudentActivityTypes activityTypes = (StudentActivityTypes) adminManager.get(StudentActivityTypes.class,getStudentActivityTypes().getId());
			if(!ObjectFunctions.isNullOrEmpty(activityTypes)){
				activityTypes.setActivityTypClasses(null);
				adminManager.remove(StudentActivityTypes.class, getStudentActivityTypes().getId());
			}*/
		 }
		ajaxViewAllStudentActivitiesTypes();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
/********************************************************************
 * Date              Name               Description
 * ========          ============       ==================
 * May 13, 2013       Seshu		        For removing activity types 
/********************************************************************/    
    @Actions( {
		@Action(value = "ajaxRemoveActivitiesGrade", results = { @Result(location = "admin/ajaxManageActivitiesGrades.jsp", name = "success") })})
		public String ajaxRemoveActivitiesGrade() throws URTUniversalException {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxRemoveActivitiesGrade' method");
			}
			try {
				if(getTempId() > 0){
					adminManager.remove(ActivitiesGrades.class, getTempId());
					super.addActionMessage("Activities grade removed successfully.");
				}
				ajaxManageStudentActivitiesGrades();
			} catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return SUCCESS;
	}
    /********************************************************************
     * Date              Name               Description
     * ========          ============       ==================
     * May 10, 2013      Seshu		        For genarating score card.
    /********************************************************************/    
       @Actions( { @Action(value = "generateAllStudentsProgressReport", results = { }) })
    	public String generateAllStudentsProgressReport(){
    		if (log.isDebugEnabled()) {
    	        log.debug("Entering 'generateAllStudentsProgressReport' method");
    	        }
    		try{
    			List<ViewStudentClasswiseAndPersonalInfo> studentClassAndPersonalDetails= null;
    			String studyClassId = getStudyClassId();
    			StudyClass classSection = null;
    			AcademicYear academicYear = null;
    			Map<String,Integer> monthWiseWorkingDays =null;
    			int totalWorkingDays = 0;
    			String organizationName = null;
    			StringBuffer customerAddress = new StringBuffer();
    			String fontPath = null;
    			String folderPath = null;
    			File destFile = null;
    			List<ExamTypes> examTypesList = null;
    			int totalSubTypesCount = 0;
    			List<StudentActivities> studentActivities = null;
    			List<ViewStudentPersonAccountDetails> viewStudentPersonAccountDetailsList = null;
    			Customer customer = getCustomerByCustId();
    			if (StringFunctions.isNotNullOrEmpty(studyClassId) && StringFunctions.isNotNullOrEmpty(getAnyTitle())) 
    			{
    				fontPath=getSession().getServletContext().getRealPath(getText(Constants.GILITE_FILE_DOCS_DIR)+"/Droid-Sans/DroidSans-Bold.ttf");
    				FontFactory.register(fontPath);
    				// Preparing organization name and address
    				if(!ObjectFunctions.isNullOrEmpty(customer)){
    			    	if(StringFunctions.isNotNullOrEmpty(customer.getOrganization()))
    			    		organizationName = customer.getOrganization().trim().toUpperCase();
    			    	    		
    			    	if(!ObjectFunctions.isNullOrEmpty(customer.getAddress())){
    			    		if(StringFunctions.isNotNullOrEmpty(customer.getAddress().getAddressLine1())){
    			    			customerAddress.append(customer.getAddress().getAddressLine1());
    			    		}
    			    		if (!StringFunctions.isNullOrEmpty(customer.getAddress().getStreetName())) {
    	    					if(customerAddress.length() > 0)
    	    						customerAddress.append(", ");
    	    					customerAddress.append(customer.getAddress().getStreetName());
    	    				}
    			    		if (!StringFunctions.isNullOrEmpty(customer.getAddress().getCity()) || 
    			    				!StringFunctions.isNullOrEmpty(customer.getAddress().getPostalCode())) {
    			    			if(customerAddress.length() > 0)
    			    				customerAddress.append("\n");
    			    			if (!StringFunctions.isNullOrEmpty(customer.getAddress().getCity()) && 
    				    				!StringFunctions.isNullOrEmpty(customer.getAddress().getPostalCode())){
    			    				customerAddress.append(customer.getAddress().getCity()+" - "+customer.getAddress().getPostalCode());
    			    			}else if(!StringFunctions.isNullOrEmpty(customer.getAddress().getCity())){
    			    				customerAddress.append(customer.getAddress().getCity());
    			    			}else{
    			    				customerAddress.append(customer.getAddress().getPostalCode());
    			    			}
    	    				}
    			    		if (!StringFunctions.isNullOrEmpty(customer.getContactNumber()) ||
    			    				!StringFunctions.isNullOrEmpty(customer.getMobileNumber())) {
    			    			if(customerAddress.length() > 0)
    			    				customerAddress.append("\n");
    			    			customerAddress.append("Phone : ");
    			    			if (!StringFunctions.isNullOrEmpty(customer.getContactNumber()) &&
    				    				!StringFunctions.isNullOrEmpty(customer.getMobileNumber())){
    			    				customerAddress.append(customer.getContactNumber()+", "+customer.getMobileNumber().replace("+91-", ""));
    			    			}else if(!StringFunctions.isNullOrEmpty(customer.getContactNumber())){
    			    				customerAddress.append(customer.getContactNumber());
    			    			}else{
    			    				customerAddress.append(customer.getMobileNumber().replace("+91-", ""));
    			    			}
    			    		}
    			    	}
    		    	}
    				classSection = (StudyClass)adminManager.get(StudyClass.class,"id="+studyClassId);
    				academicYear = classSection.getAcademicYear();
    				examTypesList=adminManager.getAll(ExamTypes.class, "custId="+getUserCustId()+" and id in "+getAnyTitle());
    				Collections.sort(examTypesList);
    				studentActivities = adminManager.getAllHqlQuery("SELECT activities FROM StudentActivities activities JOIN activities.studentActivityTypes types JOIN types.activityTypClasses classes WHERE  classes.id="+getClassId()+" group by activities.id");
    				//studentActivities = adminManager.getAll(StudentActivities.class, "custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" order by categoryName DESC");
    				totalSubTypesCount = adminManager.getCountForGroupByClause("vw_classExamDetails", "classSectionId="+studyClassId+" and eid in "+getAnyTitle()+" group by eid,subTypeId");
    				if(!ObjectFunctions.isNullOrEmpty(academicYear.getStartDate()) && !ObjectFunctions.isNullOrEmpty(academicYear.getEndDate()) &&
    		    			StringFunctions.isNotNullOrEmpty(academicYear.getManageAttendanceBy()) && "D".equalsIgnoreCase(academicYear.getManageAttendanceBy())){
    					if(DateFunctions.compare2Dates(new Date(),academicYear.getStartDate()) && DateFunctions.compare2Dates(academicYear.getEndDate(),new Date()))
    					{
    						monthWiseWorkingDays = studentManager.getMonthwiseSchoolWorkingDays(getUserCustId(),academicYear.getId(),academicYear.getStartDate(),new Date(),true,"",studyClassId);  //here getClassId() used to academicyear have class wise holiday(CH).
						}else if(new Date().after(academicYear.getEndDate())){
							monthWiseWorkingDays = studentManager.getMonthwiseSchoolWorkingDays(getUserCustId(),academicYear.getId(),academicYear.getStartDate(),academicYear.getEndDate(),true,"", studyClassId);
    					}
    		    		if(!ObjectFunctions.isNullOrEmpty(monthWiseWorkingDays) && !ObjectFunctions.isNullOrEmpty(monthWiseWorkingDays.values())){
    		    			for(Integer totalDays : monthWiseWorkingDays.values()){
    		    				totalWorkingDays+=totalDays;
    		    			}
    		    		}
    				}
   					if(getUserCustId() == 20){//20 Sarojini School
   						studentClassAndPersonalDetails= adminManager.getAll(ViewStudentClasswiseAndPersonalInfo.class, "classSectionId="+studyClassId+" and academicYearId="+getUserAcademicYearId()+"  and studDiscontinueDesc is null");
   						if(ObjectFunctions.isNotNullOrEmpty(studentClassAndPersonalDetails)){
   							Collections.sort(studentClassAndPersonalDetails);
   							generateProgressReportForStateBoardSchool(studyClassId,studentClassAndPersonalDetails,academicYear,totalWorkingDays,customerAddress.toString(),organizationName,fontPath,examTypesList,studentActivities,totalSubTypesCount);
   						}
   					}else{
   				    	viewStudentPersonAccountDetailsList= adminManager.getAll(ViewStudentPersonAccountDetails.class, "classSectionId="+studyClassId+" and academicYearId="+getUserAcademicYearId() +"  and description is null order by gender desc,firstName,lastName");	
   						if(!ObjectFunctions.isNullOrEmpty(customer) && ObjectFunctions.isNotNullOrEmpty(viewStudentPersonAccountDetailsList)){
   							folderPath = getSession().getServletContext().getRealPath("userfiles/"+customer.getCustomerShortName().trim()+"//"+
   					    			academicYear.getAcademicYear().trim()+"//" + "CCE"+"//" + classSection.getClassAndSection().trim());
   					    	destFile = new File(folderPath);
   					    	if(destFile.mkdirs())
   								log.debug("Directories Created");
   							else
   								log.debug("Directories Not Created");
   					    	destFile = null;
   					    	generateProgressReportForCentralBorardSchool(studyClassId,viewStudentPersonAccountDetailsList,academicYear,totalWorkingDays,customerAddress.toString(),organizationName,fontPath,folderPath,examTypesList,studentActivities,totalSubTypesCount);
   						}
    					}
    			}
    			studentClassAndPersonalDetails= null;
    			studyClassId = null;
    			monthWiseWorkingDays =null;
    			
    			classSection = null;
    			academicYear = null;
    		}catch (Exception ex) {
    			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
    		}
    		return null;
      }
     public void generateProgressReportForStateBoardSchool(String classSectionId,List<ViewStudentClasswiseAndPersonalInfo> studentClassAndPersonalDetails,AcademicYear academicYear,
   			int totalWorkingDays,String customerAddress,String organizationName,String fontPath,List<ExamTypes> examTypesList,
   			List<StudentActivities> studentActivities,int totalSubTypesCount){
   		PDFGenerator pDFGenerator = null;
   		Object[] studAttendance = null;
   		PdfPTable pdfContentTable= null;
   		PdfPTable pdfBlockTable = null;
   		int absentiesCount = 0;
   		List<Object[]> subTypes = null;
   		PdfPTable studentMarksTable = null;
   		PdfPTable academicPerformanceTable = null;
   		PdfPTable studentPersonalInfoTable = null;
   		PdfPTable activitiesHeaderTable = null;
   		StringBuffer studentsMarksQuery = new StringBuffer();
   		PdfPTable academicMarksTable = null;
   		List<String> subTypeNames = new ArrayList<String>();
   		HashMap<String, String> subjectsFinalGrades = null;
   		HashMap<String, String> activitiesFinalGrades = null;
   		StringBuffer avgMarksQuery = new StringBuffer();
   		PdfPCell activitiesHeaderTableCell = null;
   		try{
   			if(ObjectFunctions.isNotNullOrEmpty(examTypesList)){
   			pDFGenerator = new PDFGenerator();
   			getResponse().setContentType(pDFGenerator.getMimeType());
   			getResponse().setHeader("Content-Disposition","attachment; filename=studentsCCEReport.pdf");
   			pDFGenerator.createDocumentJasper();
   			pDFGenerator.setPdfWriter(PdfWriter.getInstance(pDFGenerator.getDocument(), getResponse().getOutputStream()));
   			pDFGenerator.getDocument().setPageSize(PageSize.A3.rotate());
   			pDFGenerator.getDocument().setMargins(30, 30, 30, 30);
   			pDFGenerator.getDocument().open();
   			pDFGenerator.getDocument().addAuthor("Hyniva Consulting Services PVT Ltd");
   			pdfBlockTable = new PdfPTable(1);
   			pdfBlockTable.setWidthPercentage(100);
   			pdfBlockTable.getDefaultCell().setBorder(Rectangle.BOX);
   			pdfBlockTable.getDefaultCell().setBorderWidth(2);
   			int sNo = 1;
   			int academicPerformanceTableWidth = examTypesList.size()*2+totalSubTypesCount+3;
   			int headerwidths[] = new int[academicPerformanceTableWidth];
   	       	headerwidths[0]= 4;
   	       	Arrays.fill(headerwidths, 1, academicPerformanceTableWidth, 2);
   	       	//For displaying Term -1,Term -2 
   			studentMarksTable = new PdfPTable(academicPerformanceTableWidth);
   	       	studentMarksTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
   	       	studentMarksTable.setWidthPercentage(100);
   	       	studentMarksTable.setWidths(headerwidths);
   	       	studentMarksTable.addCell(PDFGenerator.getPdfCellAlignCenterWithColspanAndBorder(" ", "#000",fontPath,1));
   	       	//For displaying ActivityTypes headers
   	       	int activitiesTableWidth = examTypesList.size()+1;
   	       	activitiesHeaderTable = new PdfPTable(activitiesTableWidth);
   	       	activitiesHeaderTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
   	       	activitiesHeaderTable.setWidthPercentage(100);
   	       	int activitiesHeaderwidths[] = new int[activitiesTableWidth];
   	       	activitiesHeaderwidths[0]= 3;
   	       	Arrays.fill(activitiesHeaderwidths, 1, activitiesTableWidth, 2);
   	       	activitiesHeaderTable.setWidths(activitiesHeaderwidths);
   	       	activitiesHeaderTable.addCell(PDFGenerator.getPdfCellAlignCenterWithColspanAndBorder("Activities", "#000",fontPath,1));
   	       	//Preparing query for getting student marks based on report.
   	       	/*select ss.subjectName,ss.term1St1,ss.term1St2,ss.term1Total,ss.term1Grade,ss.term2St1,
   	       	ss.term2St2,ss.term2Total,ss.term2Grade,ss.term3St1,ss.term3St2,ss.term3Total,ss.term3Grade,
   	       	ROUND((IFNULL(ss.term1Total,0)+IFNULL(ss.term2Total,0)+IFNULL(ss.term3Total,0))/3) as avgMarks,
   	       	sg.gradeName,sg.gradePoints from vw_studentScoreCardMarksDetails ss 
   	       	LEFT JOIN schoolGrades sg on (sg.academicYearId=ss.academicYearId and 
   	       	ROUND((IFNULL(ss.term1Total,0)+IFNULL(ss.term2Total,0)+IFNULL(ss.term3Total,0))/3) >= sg.minMarks and 
   	       	ROUND((IFNULL(ss.term1Total,0)+IFNULL(ss.term2Total,0)+IFNULL(ss.term3Total,0))/3)<= sg.maxMarks) 
   	       	where studentId=10156 order by ss.subjectSortingOrder;*/
   	       	studentsMarksQuery.append("select ss.subjectName,");
   	       	avgMarksQuery.append("ROUND((");
   			for(ExamTypes etype : examTypesList){
   				if(!ObjectFunctions.isNullOrEmpty(etype)){
   					activitiesHeaderTable.addCell(PDFGenerator.getPdfCellAlignCenterWithColspanAndBorder(etype.getExamType(),"#000",fontPath,1));
   					subTypes=adminManager.getAll("select subTypeId,subTypeName,scheduleMaxMarks,subTypeSortingOrder from vw_classExamDetails where eid="+etype.getId()+" and classSectionId="+classSectionId+" group by subTypeId order by IF(subTypeSortingOrder = 0,subTypeId,subTypeSortingOrder)");
   					if(!ObjectFunctions.isNullOrEmpty(subTypes)){
   						studentMarksTable.addCell(PDFGenerator.getPdfCellAlignCenterWithColspanAndBorder(etype.getExamType(),"#000",fontPath,subTypes.size()+2));
   						for(Object[] subType: subTypes){
   							subTypeNames.add(subType[1].toString()+"\n( "+subType[2].toString()+" )");
   							studentsMarksQuery.append("ss.term"+etype.getSortingOrder()+"St"+subType[3].toString()+",");
   						}
   						subTypeNames.add("Total\n( "+etype.getMaxMarks()+" )");
   						subTypeNames.add("Grade");
   						studentsMarksQuery.append("ss.term"+etype.getSortingOrder()+"Total,");
   						studentsMarksQuery.append("ss.term"+etype.getSortingOrder()+"Grade,");
   						avgMarksQuery.append("IFNULL(ss.term"+etype.getSortingOrder()+"Total,0)+");
   					}
   					subTypes = null;
   				}etype=null;
   			}
   			//Preparing Activities Header table as cell
   			activitiesHeaderTableCell = new PdfPCell(activitiesHeaderTable);
   	       	activitiesHeaderTableCell.setColspan(activitiesHeaderwidths.length);			
   			//studentsMarksQuery.deleteCharAt(studentsMarksQuery.length()-1);
   			avgMarksQuery.deleteCharAt(avgMarksQuery.length()-1);
   			avgMarksQuery.append(")/"+examTypesList.size()+")");
   			studentsMarksQuery.append(avgMarksQuery+" as avgMarks,sg.gradeName,sg.gradePoints");
   			studentsMarksQuery.append(" from vw_studentScoreCardMarksDetails ss");
   			studentsMarksQuery.append(" LEFT JOIN schoolGrades sg on (sg.academicYearId=ss.academicYearId and ");
   			studentsMarksQuery.append(avgMarksQuery+" >= sg.minMarks and ");
   			studentsMarksQuery.append(avgMarksQuery+" <= sg.maxMarks )");
   			//studentMarksTable.completeRow();
   			studentMarksTable.addCell(PDFGenerator.getPdfCellAlignCenterWithColspanAndBorder(" ", "#000",fontPath,1));
   	       	studentMarksTable.addCell(PDFGenerator.getPdfCellAlignCenterWithColspanAndBorder(" ", "#000",fontPath,1));
   	       	studentMarksTable.addCell(PDFGenerator.getPdfCellAlignCenterWithColspanAndBorder("Subjects", "#000",fontPath,1));
   	       	for(String subTypeName : subTypeNames){
   	       		studentMarksTable.addCell(PDFGenerator.getPdfCellAlignCenterWithColspanAndBorder(subTypeName, "#000",fontPath,1));
   	       	}
   	       	studentMarksTable.addCell(PDFGenerator.getPdfCellAlignCenterWithColspanAndBorder("Overall Average Marks", "#000",fontPath,1));
   	       	studentMarksTable.addCell(PDFGenerator.getPdfCellAlignCenterWithColspanAndBorder("Overall Grade", "#000",fontPath,1));
   	       	subTypeNames = null;
   			for(ViewStudentClasswiseAndPersonalInfo personDetails:studentClassAndPersonalDetails){
   			    log.debug("Stude Name: "+personDetails.getPersonFullName());
   			    if(StringFunctions.isNotNullOrEmpty(academicYear.getManageAttendanceBy()) && "M".equalsIgnoreCase(academicYear.getManageAttendanceBy())){
   			    	studAttendance = adminManager.get("select sum(totalWorkingDays),sum(noOfPresentDays) from vw_StudentMonthlyAttendance where classSectionId="+personDetails.getClassSectionId()+" and academicYearId="+personDetails.getAcademicYearId()+" and studentId="+personDetails.getStudId());
   				    if(!ObjectFunctions.isNullOrEmpty(studAttendance) && !ObjectFunctions.isNullOrEmpty(studAttendance[0]) && !ObjectFunctions.isNullOrEmpty(studAttendance[1])){
   				    	personDetails.setPresentDaysCount(studAttendance[1].toString());
   				    	personDetails.setTotalWorkingDaysCount(studAttendance[0].toString());
   				    }
   		    	}else{
   		    		personDetails.setTotalWorkingDaysCount(String.valueOf(totalWorkingDays));
   		    		if(!ObjectFunctions.isNullOrEmpty(academicYear.getStartDate()) && !ObjectFunctions.isNullOrEmpty(academicYear.getEndDate())){
   				    	if(DateFunctions.compare2Dates(new Date(),academicYear.getStartDate()) && DateFunctions.compare2Dates(academicYear.getEndDate(),new Date()))
   						{
   				    		absentiesCount = adminManager.getCount("vw_StudentDailyAttendance", "accountId="+personDetails.getAccountId()+" and academicYearId="+personDetails.getAcademicYearId()+" and present='N' and attendanceDate is not null and attendanceDate <='"+DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, new Date())+"'");
   						}else if(new Date().after(academicYear.getEndDate())){
   							absentiesCount = adminManager.getCount("vw_StudentDailyAttendance", "accountId="+personDetails.getAccountId()+" and academicYearId="+personDetails.getAcademicYearId()+" and present='N' and attendanceDate is not null and attendanceDate <='"+DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, academicYear.getEndDate())+"'");
   						}
   				    }
   		    		personDetails.setPresentDaysCount(String.valueOf(totalWorkingDays-absentiesCount));
   		    	}
   			    pdfContentTable = new PdfPTable(1);
   				pdfContentTable.setWidthPercentage(100);
   				pdfContentTable.setSplitLate(true);
   			    pdfContentTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
   				studentPersonalInfoTable = new PdfPTable(1);
   				studentPersonalInfoTable.setWidthPercentage(100);
   				if (!StringFunctions.isNullOrEmpty(organizationName)) {
   					studentPersonalInfoTable.addCell(PDFGenerator.getPdfCellHeaderRowWithColspanAndFontColorFontSizeAndNOBackGrundColorAndAlignmentAndPaddingJasper(organizationName, 1,fontPath, "#000000", 15,"",Element.ALIGN_CENTER, 2.0f));
   				}
   				if (!StringFunctions.isNullOrEmpty(customerAddress)) {
   					studentPersonalInfoTable.addCell(PDFGenerator.getPdfCellHeaderRowWithColspanAndFontColorFontSizeAndNOBackGrundColorAndAlignmentAndPaddingJasper(customerAddress, 1,fontPath, "#000000",8,"",Element.ALIGN_CENTER, 2.0f));
   				}
   				pdfContentTable.addCell(studentPersonalInfoTable);
   			    generateStudentPersonalInfoForStateBoardSchools(personDetails,pdfContentTable,fontPath,sNo++);
   				if(ObjectFunctions.isNotNullOrEmpty(examTypesList)){
   					subjectsFinalGrades= new LinkedHashMap<String, String>(); 
   					academicPerformanceTable = new PdfPTable(1);
   					academicPerformanceTable.setWidthPercentage(100);
   					academicPerformanceTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
   					academicMarksTable = new PdfPTable(studentMarksTable);
   					academicMarksTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
   					academicMarksTable.setWidthPercentage(100);
   					academicMarksTable.setWidths(headerwidths);
   					generateStudentAcademicPerformanceForStateBoardSchool(personDetails.getStudId(),academicMarksTable,fontPath,studentsMarksQuery.toString(),subjectsFinalGrades);
   					academicPerformanceTable.addCell(academicMarksTable);
   					pdfContentTable.addCell(academicPerformanceTable);
   				}
   				if(ObjectFunctions.isNotNullOrEmpty(studentActivities)){
   					activitiesFinalGrades= new LinkedHashMap<String, String>();
   					academicPerformanceTable = new PdfPTable(1);
   					academicPerformanceTable.setWidthPercentage(100);
   					academicPerformanceTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
   					generateStudentsActivitiesReport(personDetails.getStudId(),studentActivities,academicPerformanceTable,activitiesHeaderwidths,fontPath,activitiesHeaderTableCell,activitiesFinalGrades,personDetails.getClassId());
   					pdfContentTable.addCell(academicPerformanceTable);
   				}
   				if(!ObjectFunctions.isNullOrEmpty(subjectsFinalGrades) || !ObjectFunctions.isNullOrEmpty(activitiesFinalGrades)){
   					academicPerformanceTable = new PdfPTable(2);
   					academicPerformanceTable.setWidthPercentage(50);
   					academicPerformanceTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
   					generateStudentsAcademicPerformanceAnnualGrades(subjectsFinalGrades,activitiesFinalGrades,academicPerformanceTable,fontPath);
   					printAnnualGrades(academicPerformanceTable,fontPath);
   					pdfContentTable.addCell(academicPerformanceTable);
   				}
   				pdfBlockTable.addCell(pdfContentTable);
   				subjectsFinalGrades = null;
   				activitiesFinalGrades = null;
   			}
   			pDFGenerator.getDocument().add(pdfBlockTable);
   			pDFGenerator.getDocument().newPage();
   			pDFGenerator.getDocument().close();
   		}
   		}catch (Exception ex) {
   			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
   			// TODO: handle exception
   		}finally{
   			pdfContentTable= null;
   	    	pDFGenerator = null;
   	    	studentClassAndPersonalDetails = null;
   	    	academicYear = null;
   	    	studAttendance = null;
   	    	examTypesList = null;
   			subTypes = null;
   			studentMarksTable = null;
   			academicPerformanceTable = null;
   			studentPersonalInfoTable = null;
   			studentsMarksQuery = null;
   			studentActivities = null;
   			//activitiesTable = null;
   			academicMarksTable = null;
   			subTypeNames = null;
   			subjectsFinalGrades = null;
   			activitiesFinalGrades = null;
   			avgMarksQuery = null;
   			pdfBlockTable = null;
   			activitiesHeaderTable = null;
   			activitiesHeaderTableCell = null;
   		}
   	}
     
     public void generateStudentPersonalInfoForStateBoardSchools(ViewStudentClasswiseAndPersonalInfo personDetails,PdfPTable mainTable,String fontPath,int sNo){
 		try{
 			float attendancePercentage =  0;
 			if(StringFunctions.isNotNullOrEmpty(personDetails.getPresentDaysCount()) && StringFunctions.isNotNullOrEmpty(personDetails.getTotalWorkingDaysCount()))
 		    	attendancePercentage = (Float.valueOf(personDetails.getPresentDaysCount())/Float.valueOf(personDetails.getTotalWorkingDaysCount()))*100;
 			PdfPTable studentPersonalInfoTable = null;
 			studentPersonalInfoTable = new PdfPTable(5);
 			studentPersonalInfoTable.setWidthPercentage(100);
 			studentPersonalInfoTable.setWidths(new float[]{1.3f,1.8f,4.8f,1.3f,0.8f});
 			studentPersonalInfoTable.addCell(PDFGenerator.getPdfCellHeaderRowWithColspanAndFontColorFontSizeAndNOBackGrundColorAndAlignmentAndPaddingJasper("Class ",1,fontPath,"#000000",12,"",Element.ALIGN_LEFT, 2.0f));
 			studentPersonalInfoTable.addCell(PDFGenerator.getPdfCellHeaderRowWithColspanAndFontColorFontSizeAndNOBackGrundColorAndAlignmentAndPaddingJasper(": "+personDetails.getClassAndSection(),1,fontPath,"#000000",12,"",Element.ALIGN_LEFT, 2.0f));
 			studentPersonalInfoTable.addCell(PDFGenerator.getPdfCellHeaderRowWithColspanAndFontColorFontSizeAndNOBackGrundColorAndAlignmentAndPaddingJasper(" ",1,fontPath,"#000000",12,"",Element.ALIGN_LEFT, 2.0f));
 			studentPersonalInfoTable.addCell(PDFGenerator.getPdfCellHeaderRowWithColspanAndFontColorFontSizeAndNOBackGrundColorAndAlignmentAndPaddingJasper("No.of working days ",1,fontPath,"#000",12,"", Element.ALIGN_LEFT, 2.0f));
 			if(StringFunctions.isNullOrEmpty(personDetails.getTotalWorkingDaysCount()))
 				studentPersonalInfoTable.addCell(PDFGenerator.getPdfCellHeaderRowWithColspanAndFontColorFontSizeAndNOBackGrundColorAndAlignmentAndPaddingJasper(": ",1,fontPath,"#000",12,"", Element.ALIGN_LEFT, 2.0f));
 			else
 				studentPersonalInfoTable.addCell(PDFGenerator.getPdfCellHeaderRowWithColspanAndFontColorFontSizeAndNOBackGrundColorAndAlignmentAndPaddingJasper(": "+personDetails.getTotalWorkingDaysCount(),1,fontPath,"#000",12,"", Element.ALIGN_LEFT, 2.0f));
 			studentPersonalInfoTable.addCell(PDFGenerator.getPdfCellHeaderRowWithColspanAndFontColorFontSizeAndNOBackGrundColorAndAlignmentAndPaddingJasper("S.No ",1,fontPath,"#000",12,"",Element.ALIGN_LEFT, 2.0f));
 			studentPersonalInfoTable.addCell(PDFGenerator.getPdfCellHeaderRowWithColspanAndFontColorFontSizeAndNOBackGrundColorAndAlignmentAndPaddingJasper(": "+(sNo),1,fontPath,"#000",12,"",Element.ALIGN_LEFT, 2.0f));
 			studentPersonalInfoTable.addCell(PDFGenerator.getPdfCellHeaderRowWithColspanAndFontColorFontSizeAndNOBackGrundColorAndAlignmentAndPaddingJasper(" ",1,fontPath,"#000",12,"",Element.ALIGN_LEFT, 2.0f));
 			studentPersonalInfoTable.addCell(PDFGenerator.getPdfCellHeaderRowWithColspanAndFontColorFontSizeAndNOBackGrundColorAndAlignmentAndPaddingJasper("No.of days present ",1,fontPath,"#000",12,"",Element.ALIGN_LEFT, 2.0f));
 			if(StringFunctions.isNullOrEmpty(personDetails.getPresentDaysCount()))
 				studentPersonalInfoTable.addCell(PDFGenerator.getPdfCellHeaderRowWithColspanAndFontColorFontSizeAndNOBackGrundColorAndAlignmentAndPaddingJasper(": ",1,fontPath,"#000",12,"",Element.ALIGN_LEFT, 2.0f));
 			else
 				studentPersonalInfoTable.addCell(PDFGenerator.getPdfCellHeaderRowWithColspanAndFontColorFontSizeAndNOBackGrundColorAndAlignmentAndPaddingJasper(": "+personDetails.getPresentDaysCount(),1,fontPath,"#000",12,"",Element.ALIGN_LEFT, 2.0f));
 			studentPersonalInfoTable.addCell(PDFGenerator.getPdfCellHeaderRowWithColspanAndFontColorFontSizeAndNOBackGrundColorAndAlignmentAndPaddingJasper("Admission No ",1,fontPath,"#000",12,"",Element.ALIGN_LEFT, 2.0f));
 			studentPersonalInfoTable.addCell(PDFGenerator.getPdfCellHeaderRowWithColspanAndFontColorFontSizeAndNOBackGrundColorAndAlignmentAndPaddingJasper(": "+personDetails.getAdmissionNumber(),1,fontPath,"#000",12,"",Element.ALIGN_LEFT, 2.0f));
 			studentPersonalInfoTable.addCell(PDFGenerator.getPdfCellHeaderRowWithColspanAndFontColorFontSizeAndNOBackGrundColorAndAlignmentAndPaddingJasper(" ",1,fontPath,"#000",12,"",Element.ALIGN_LEFT, 2.0f));
 			studentPersonalInfoTable.addCell(PDFGenerator.getPdfCellHeaderRowWithColspanAndFontColorFontSizeAndNOBackGrundColorAndAlignmentAndPaddingJasper("Attendance percentage ",1,fontPath,"#000",12,"",Element.ALIGN_LEFT, 2.0f));
 			studentPersonalInfoTable.addCell(PDFGenerator.getPdfCellHeaderRowWithColspanAndFontColorFontSizeAndNOBackGrundColorAndAlignmentAndPaddingJasper(": "+(attendancePercentage)+"",1,fontPath,"#000",12,"",Element.ALIGN_LEFT, 2.0f));
 			studentPersonalInfoTable.addCell(PDFGenerator.getPdfCellHeaderRowWithColspanAndFontColorFontSizeAndNOBackGrundColorAndAlignmentAndPaddingJasper("Exam Number ",1,fontPath,"#000",12,"",Element.ALIGN_LEFT, 2.0f));
 			if(StringFunctions.isNullOrEmpty(personDetails.getRegisterNumber()))
 				studentPersonalInfoTable.addCell(PDFGenerator.getPdfCellHeaderRowWithColspanAndFontColorFontSizeAndNOBackGrundColorAndAlignmentAndPaddingJasper(": ",1,fontPath,"#000",12,"",Element.ALIGN_LEFT, 2.0f));
 			else
 				studentPersonalInfoTable.addCell(PDFGenerator.getPdfCellHeaderRowWithColspanAndFontColorFontSizeAndNOBackGrundColorAndAlignmentAndPaddingJasper(": "+personDetails.getRegisterNumber(),1,fontPath,"#000",12,"",Element.ALIGN_LEFT, 2.0f));
 			studentPersonalInfoTable.addCell(PDFGenerator.getPdfCellHeaderRowWithColspanAndFontColorFontSizeAndNOBackGrundColorAndAlignmentAndPaddingJasper("CUMULATIVE MARKS & GRADE POINT",1,fontPath,"#000",14,"",Element.ALIGN_CENTER, 2.0f));
 			studentPersonalInfoTable.addCell(PDFGenerator.getPdfCellHeaderRowWithColspanAndFontColorFontSizeAndNOBackGrundColorAndAlignmentAndPaddingJasper("Community ",1,fontPath,"#000",12,"",Element.ALIGN_LEFT, 2.0f));
 			if(StringFunctions.isNullOrEmpty(personDetails.getCastName()))
 				studentPersonalInfoTable.addCell(PDFGenerator.getPdfCellHeaderRowWithColspanAndFontColorFontSizeAndNOBackGrundColorAndAlignmentAndPaddingJasper(": ",1,fontPath,"#000",12,"",Element.ALIGN_LEFT, 2.0f));
 			else
 				studentPersonalInfoTable.addCell(PDFGenerator.getPdfCellHeaderRowWithColspanAndFontColorFontSizeAndNOBackGrundColorAndAlignmentAndPaddingJasper(": "+personDetails.getCastName(),1,fontPath,"#000",12,"",Element.ALIGN_LEFT, 2.0f));
 			studentPersonalInfoTable.addCell(PDFGenerator.getPdfCellHeaderRowWithColspanAndFontColorFontSizeAndNOBackGrundColorAndAlignmentAndPaddingJasper("Name of the Student ",1,fontPath,"#000",12,"",Element.ALIGN_LEFT, 2.0f));
 			studentPersonalInfoTable.addCell(PDFGenerator.getPdfCellHeaderRowWithColspanAndFontColorFontSizeAndNOBackGrundColorAndAlignmentAndPaddingJasper(": "+personDetails.getPersonFullName(),1,fontPath,"#000",12,"",Element.ALIGN_LEFT, 2.0f));
 			studentPersonalInfoTable.addCell(PDFGenerator.getPdfCellHeaderRowWithColspanAndFontColorFontSizeAndNOBackGrundColorAndAlignmentAndPaddingJasper("PART I : Academic Performance - Scholastic Area",1,fontPath,"#000",14,"",Element.ALIGN_CENTER, 2.0f));
 			studentPersonalInfoTable.addCell(PDFGenerator.getPdfCellHeaderRowWithColspanAndFontColorFontSizeAndNOBackGrundColorAndAlignmentAndPaddingJasper("",2,fontPath,"#000",14,"",Element.ALIGN_CENTER, 2.0f));
 			mainTable.addCell(studentPersonalInfoTable);
 		}
 		catch (Exception ex) {
 			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
 		}
 	}
     
     public void generateStudentAcademicPerformanceForStateBoardSchool(long studentId,PdfPTable academicMarksTable,String fontPath,String studentsMarksQuery,HashMap<String,String> subjectsFinalGrades){
 		try{
 			String subjectName = null;
 			List<Object[]> studentMarks= adminManager.getAll(studentsMarksQuery+" where ss.studentId="+studentId+" order by ss.subjectSortingOrder");
 			if(ObjectFunctions.isNotNullOrEmpty(studentMarks)){
 				for(Object[] marks: studentMarks){
 					//log.debug("Row Length : "+marks.length);
 					for(int column = 0; column < marks.length; column++){
 						if(column == 0){
 							subjectName = marks[column].toString();
 							academicMarksTable.addCell(PDFGenerator.getPdfCellHeaderCellJasperClass(marks[column].toString(), "#000",fontPath));
 						}
 						else if(column == (marks.length-1) && subjectsFinalGrades != null){
 							if(!ObjectFunctions.isNullOrEmpty(marks[column]) && StringFunctions.isNotNullOrEmpty(marks[column].toString()))
 								subjectsFinalGrades.put(subjectName, marks[column].toString());
 							else
 								subjectsFinalGrades.put(subjectName, "");
 						}
 						else if(!ObjectFunctions.isNullOrEmpty(marks[column]) && StringFunctions.isNotNullOrEmpty(marks[column].toString()))
 							academicMarksTable.addCell(PDFGenerator.getPdfCellWithCenterAlignJasperNoBorder(marks[column].toString(),fontPath));
 						else{
 							academicMarksTable.addCell(PDFGenerator.getPdfCellWithCenterAlignJasperNoBorder("",fontPath));
 						}
 					}	
 				}
 			}
 			academicMarksTable = null;	
 			subjectName = null;
 			studentMarks = null;
 		}catch (Exception ex) {
 			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
 		}
 	}
     public PdfPTable generateStudentsActivitiesReport(long studentId,List<StudentActivities> studentActivities,PdfPTable activitiesTable,int[] activitiesHeaderwidths,String fontPath,PdfPCell activitiesHeaderTableCell,HashMap<String, String> activitiesFinalGrades,long classId){
	     if (log.isDebugEnabled()) {
	         log.debug("Entering 'generateStudentsActivitiesReport' method");
	     }
	     try{
	    	 int activitiesCount = 2;
	    	 String categoryName = "";
	    	 String activityTypeName= null;
	    	 String selectedActivityTypeName = null;
	    	 PdfPTable activityTypesPerformanceTable = null;
	    	 List<Object[]> activityTypeGrades = null;
	    	 activitiesTable.addCell(PDFGenerator.getPdfCellHeaderRowWithColspanAndFontColorFontSizeAndNOBackGrundColorAndAlignmentAndPaddingJasper("",activitiesHeaderwidths.length,fontPath,"#000000",12,"", Element.ALIGN_CENTER,5.0f));
	    	 for(StudentActivities studentActivity : studentActivities){
	    		 activityTypeGrades = adminManager.getStudentObtainedActivitiesGradesByStudIdAndExamTypeIds(studentId, getAnyTitle(), studentActivity.getId(),classId);
	    		 if(ObjectFunctions.isNotNullOrEmpty(activityTypeGrades)){
	    			 if(StringFunctions.isNotNullOrEmpty(studentActivity.getCategoryName()) && !categoryName.equalsIgnoreCase(studentActivity.getCategoryName())){
	    				 activitiesTable.addCell(PDFGenerator.getPdfCellHeaderRowWithColspanAndFontColorFontSizeAndNOBackGrundColorAndAlignmentAndPaddingJasper("PART "+(activitiesCount++)+": "+studentActivity.getCategoryName().trim(),100,fontPath, "#000000",13,"", Element.ALIGN_LEFT, 5.0f));
		    		 }
	    			 //activitiesTable.addCell(PDFGenerator.getPdfCellHeaderRowWithColspanAndFontColorFontSizeAndNOBackGrundColorAndAlignmentAndPaddingJasper(studentActivity.getActivityName(),activitiesHeaderwidths.length,fontPath,"#000000",12,"", Element.ALIGN_CENTER,2.5f));
		    		 activityTypesPerformanceTable = new PdfPTable(activitiesHeaderwidths.length);
		    		 activityTypesPerformanceTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		    		 activityTypesPerformanceTable.setSplitLate(true);
		    		 activityTypesPerformanceTable.setWidthPercentage(100);
		    		 activityTypesPerformanceTable.setWidths(activitiesHeaderwidths);
		    		 activityTypesPerformanceTable.addCell(PDFGenerator.getPdfCellAlignCenterWithColspanAndBorder(studentActivity.getActivityName(),"#000",fontPath,activitiesHeaderwidths.length));
		    		 activityTypesPerformanceTable.addCell(activitiesHeaderTableCell);
		    		 //activityTypesPerformanceTable.setWidths(activitiesHeaderwidths);
		    		 //activityTypesPerformanceTable.addCell(PDFGenerator.getPdfCellAlignCenterWithColspanAndBorder(studentActivity.getActivityName(),"#000",fontPath,activitiesHeaderwidths.length));
		    		 //activityTypesPerformanceTable.addCell(activitiesHeaderTable);
		    		 for(Object[] activityTypeGrade : activityTypeGrades){
		    			 if(!ObjectFunctions.isNullOrEmpty(activityTypeGrade[1]) && StringFunctions.isNotNullOrEmpty(activityTypeGrade[1].toString())){
		    				 activityTypeName = activityTypeGrade[1].toString().trim();
		    				 if(StringFunctions.isNullOrEmpty(selectedActivityTypeName) || !(activityTypeName).equalsIgnoreCase(selectedActivityTypeName)){
			    				 activityTypesPerformanceTable.addCell(PDFGenerator.getPdfCellHeaderCellJasperClass(activityTypeName,"#000",fontPath));
			    				 if(activitiesFinalGrades != null){
				    				 activitiesFinalGrades.put(activityTypeName,"");
				    				 if(StringFunctions.isNotNullOrEmpty(selectedActivityTypeName)){
						    			 if(StringFunctions.isNotNullOrEmpty(activitiesFinalGrades.get(selectedActivityTypeName)) && !ObjectFunctions.isNullOrEmpty(activitiesFinalGrades)){
				    						 activitiesFinalGrades.put(selectedActivityTypeName,Math.round((Float.valueOf(activitiesFinalGrades.get(selectedActivityTypeName)))/(getAnyTitle().split(",").length-1))+"");
				    					 }
						    		 }
			    				 }
			    			 }
		    				 if(!ObjectFunctions.isNullOrEmpty(activityTypeGrade[0]) && StringFunctions.isNotNullOrEmpty(activityTypeGrade[0].toString())){
		    					 activityTypesPerformanceTable.addCell(PDFGenerator.getPdfCellWithCenterAlignJasperNoBorder(activityTypeGrade[0].toString(),fontPath));
		    				 }else 
		    					 activityTypesPerformanceTable.addCell(PDFGenerator.getPdfCellWithCenterAlignJasperNoBorder("",fontPath));
		    				 if(activitiesFinalGrades == null){
		    					 if(!ObjectFunctions.isNullOrEmpty(activityTypeGrade[3]) && StringFunctions.isNotNullOrEmpty(activityTypeGrade[3].toString()))
		    						 activityTypesPerformanceTable.addCell(PDFGenerator.getPdfCellWithCenterAlignJasperNoBorder(activityTypeGrade[3].toString(),fontPath));
		    					 else
		    						 activityTypesPerformanceTable.addCell(PDFGenerator.getPdfCellWithCenterAlignJasperNoBorder("",fontPath));
		    				 }
		    				 if(!ObjectFunctions.isNullOrEmpty(activityTypeGrade[2]) && StringFunctions.isNotNullOrEmpty(activityTypeGrade[2].toString()) && 
		    						 activitiesFinalGrades != null){
		    					 if(StringFunctions.isNotNullOrEmpty(activitiesFinalGrades.get(activityTypeName))){
		    						 activitiesFinalGrades.put(activityTypeName,Float.valueOf(activitiesFinalGrades.get(activityTypeName))+Float.valueOf(activityTypeGrade[2].toString())+"");
		    					 }else
		    						 activitiesFinalGrades.put(activityTypeName,activityTypeGrade[2].toString());
		    				 }
		    				 selectedActivityTypeName = activityTypeName;
		    			 }
		    		 }
		    		 if(StringFunctions.isNotNullOrEmpty(selectedActivityTypeName) && activitiesFinalGrades != null){
		    			 if(StringFunctions.isNotNullOrEmpty(activitiesFinalGrades.get(selectedActivityTypeName))){
  						 activitiesFinalGrades.put(selectedActivityTypeName,Math.round((Float.valueOf(activitiesFinalGrades.get(selectedActivityTypeName)))/(getAnyTitle().split(",").length-1))+"");
  					 }
		    		 }
		    		if(StringFunctions.isNullOrEmpty(studentActivity.getCategoryName()))
		    			categoryName = "";
		    		else
		    			categoryName = studentActivity.getCategoryName();
	    		 }
	    		 if(StringFunctions.isNotNullOrEmpty(studentActivity.getActivityDescription())){
	    			 activityTypesPerformanceTable.addCell(PDFGenerator.getPdfCellHeaderRowWithColspanAndFontColorFontSizeAndNOBackGrundColorAndAlignmentAndPaddingJasper(studentActivity.getActivityDescription(), activitiesHeaderwidths.length, fontPath, "#000000", 6, "",Element.ALIGN_LEFT, 2f));
	    		 }
	    		 activityTypesPerformanceTable.addCell(PDFGenerator.getPdfCellHeaderRowWithColspanAndFontColorFontSizeAndNOBackGrundColorAndAlignmentAndPaddingJasper("", activitiesHeaderwidths.length, fontPath, "#000000", 10, "",Element.ALIGN_CENTER, 0));
	    		 activitiesTable.addCell(activityTypesPerformanceTable);
	    	  }
	         }catch (Exception ex) {
	             ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	             JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
	         }
	         return null;
	     }
     public void generateStudentsAcademicPerformanceAnnualGrades(HashMap<String, String> subjectsFinalGrades,HashMap<String, String> activitiesFinalGrades,PdfPTable academicPerformanceTable,String fontPath){
		 PdfPTable annualGradesTable = new PdfPTable(2);
		 annualGradesTable.setWidthPercentage(50);
		 annualGradesTable.addCell(PDFGenerator.getPdfCellHeaderRowWithColspanAndFontColorFontSizeAndNOBackGrundColorAndAlignmentAndPaddingJasper("",2,fontPath,"#000000",12,"", Element.ALIGN_CENTER,5.0f));
		 annualGradesTable.addCell(PDFGenerator.getPdfCellHeaderRowWithColspanAndFontColorFontSizeAndNOBackGrundColorAndAlignmentAndPaddingJasper("ANNUAL FINAL GRADE",2,fontPath, "#000000",13,"", Element.ALIGN_CENTER, 5.0f));
		 if(!ObjectFunctions.isNullOrEmpty(subjectsFinalGrades)){
			 annualGradesTable.addCell(PDFGenerator.getPdfCellAlignCenterWithColspanAndBorder("Subjects","#000",fontPath,1));
			 annualGradesTable.addCell(PDFGenerator.getPdfCellAlignCenterWithColspanAndBorder("Grade Point","#000",fontPath,1));
			 for (Map.Entry<String, String> entry : subjectsFinalGrades.entrySet()) {
				 annualGradesTable.addCell(PDFGenerator.getPdfCellHeaderCellJasperClass(entry.getKey(),"#000000",fontPath));
				 annualGradesTable.addCell(PDFGenerator.getPdfCellWithCenterAlignJasperNoBorder(entry.getValue()+"",fontPath));
			 }
		 }
		 if(!ObjectFunctions.isNullOrEmpty(activitiesFinalGrades)){
			 annualGradesTable.addCell(PDFGenerator.getPdfCellAlignCenterWithColspanAndBorder("Activities","#000",fontPath,1));
			 annualGradesTable.addCell(PDFGenerator.getPdfCellAlignCenterWithColspanAndBorder("Grade Point","#000",fontPath,1));
			 for (Map.Entry<String, String> entry : activitiesFinalGrades.entrySet()) {
				 annualGradesTable.addCell(PDFGenerator.getPdfCellHeaderCellJasperClass(entry.getKey(),"#000000",fontPath));
				 annualGradesTable.addCell(PDFGenerator.getPdfCellWithCenterAlignJasperNoBorder(entry.getValue()+"",fontPath));
			 }
		 }
		 academicPerformanceTable.addCell(annualGradesTable);
	}
     public void printAnnualGrades(PdfPTable academicPerformanceTable,String fontPath){
		 PdfPTable annualGradesTable = new PdfPTable(2);
		 annualGradesTable.setWidthPercentage(50);
		 annualGradesTable.addCell(PDFGenerator.getPdfCellHeaderRowWithColspanAndFontColorFontSizeAndNOBackGrundColorAndAlignmentAndPaddingJasper("",2,fontPath,"#000000",12,"", Element.ALIGN_CENTER,5.0f));
		 annualGradesTable.addCell(PDFGenerator.getPdfCellHeaderRowWithColspanAndFontColorFontSizeAndNOBackGrundColorAndAlignmentAndPaddingJasper("INDICATORS FOR PHYSICAL EDUCATION & CO - SCHOLASTIC AREAS",2,fontPath,"#000",13,"",Element.ALIGN_CENTER,5.0f));
		 annualGradesTable.addCell(PDFGenerator.getPdfCellWithCenterAlignJasperNoBorder("A",fontPath));
		 annualGradesTable.addCell(PDFGenerator.getPdfCellWithCenterAlignJasperNoBorder("Excellent",fontPath));
		 annualGradesTable.addCell(PDFGenerator.getPdfCellWithCenterAlignJasperNoBorder("B",fontPath));
		 annualGradesTable.addCell(PDFGenerator.getPdfCellWithCenterAlignJasperNoBorder("Very Good",fontPath));
		 annualGradesTable.addCell(PDFGenerator.getPdfCellWithCenterAlignJasperNoBorder("C",fontPath));
		 annualGradesTable.addCell(PDFGenerator.getPdfCellWithCenterAlignJasperNoBorder("Good",fontPath));
		 annualGradesTable.addCell(PDFGenerator.getPdfCellWithCenterAlignJasperNoBorder("D",fontPath));
		 annualGradesTable.addCell(PDFGenerator.getPdfCellWithCenterAlignJasperNoBorder("Satisfactory",fontPath));
		 annualGradesTable.addCell(PDFGenerator.getPdfCellWithCenterAlignJasperNoBorder("E",fontPath));
		 annualGradesTable.addCell(PDFGenerator.getPdfCellWithCenterAlignJasperNoBorder("Needs Improvement",fontPath));
		 annualGradesTable.addCell(PDFGenerator.getPdfCellHeaderRowWithColspanAndFontColorFontSizeAndNOBackGrundColorAndAlignmentAndPaddingJasper("", 2, fontPath, "#000000", 10, "",Element.ALIGN_CENTER, 0));
		 academicPerformanceTable.addCell(annualGradesTable);
	}
     public void generateProgressReportForCentralBorardSchool(String studyClassId,List<ViewStudentPersonAccountDetails> viewStudentPersonAccountDetailsList,
 			AcademicYear academicYear,int totalWorkingDays,String customerAddress,String organizationName,String fontPath,
 			String folderPath,List<ExamTypes> examTypesList,List<StudentActivities> studentActivities,int totalSubTypesCount){
 		Object[] studAttendance = null;
 		int absentiesCount =0;
 		File sourceFile = null;
 		JasperPrint jrprint=null;
 		JRPdfExporter exporter = null;
 		PDFGenerator pDFGenerator=null;
 		PdfPTable studentMarksTable = null;
 		PdfPTable activitiesHeaderTable = null;
 		PdfPTable academicPerformanceTable = null;
 		PdfPTable academicMarksTable = null;
 		List<Object[]> subTypes = null;
 		int academicPerformanceTableWidth = 0;
 		PdfPTable academicInfoTable = null;
 		PdfPTable academicInfoBlockTable = null;
 		StringBuffer studentsMarksQuery = new StringBuffer();
 		List<String> subTypeNames = new ArrayList<String>();
 		StringBuffer examSubTypeNames = null;
 		StringBuffer subTypeMarksGradesQuery = new StringBuffer(); 
 		FileOutputStream file = null;
 		Document document = null;
 		PdfContentByte cb = null;
 		PdfImportedPage page = null;
 		PdfPTable gradingSystemTable = null;
 		List<OverAllGrades> overAllGrades = null;
 		String tableNameAlias = null;
 		PdfPTable signTable = null;
 		List<String> overAllGradeQueries= new ArrayList<String>();
 		StringBuffer overAllGradeQuery = null;
 		List<Object[]> ovgGradesList = null;
 		PdfPCell activitiesHeaderTableCell = null;
 		if(getUserCustId() == 2)
 	    	sourceFile = new File(getSession().getServletContext().getRealPath("jasper/marks/student3ImagesCBSCdefaultTemplet.jasper"));	
 		else
 			sourceFile = new File(getSession().getServletContext().getRealPath("jasper/marks/studentCBSCdefaultTemplet.jasper"));
 		if(ObjectFunctions.isNotNullOrEmpty(examTypesList)){
 		try{
 			pDFGenerator = new PDFGenerator();
 			getResponse().setContentType(pDFGenerator.getMimeType());
 			getResponse().setHeader("Content-Disposition","attachment; filename=studentsCCEReport.pdf");
 			pDFGenerator.createDocumentJasper();
 			pDFGenerator.setPdfWriter(PdfWriter.getInstance(pDFGenerator.getDocument(), getResponse().getOutputStream()));
 			pDFGenerator.getDocument().setPageSize(PageSize.A4.rotate());
 			pDFGenerator.getDocument().setMargins(50, 50, 50, 50);
 			pDFGenerator.getDocument().open();
 			pDFGenerator.getDocument().addAuthor("Hyniva Consulting Services PVT Ltd");
 			cb = pDFGenerator.getPdfWriter().getDirectContent();
       		academicPerformanceTableWidth = examTypesList.size()*2+totalSubTypesCount+1;
 			overAllGrades = adminManager.getAll(OverAllGrades.class,"academicYearId="+getUserAcademicYearId());
 			if(ObjectFunctions.isNotNullOrEmpty(overAllGrades)){
 				gradingSystemTable = new PdfPTable(2);
 				gradingSystemTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
 				gradingSystemTable.addCell(PDFGenerator.getPdfCellHeaderRowWithColspanAndFontColorFontSizeAndNOBackGrundColorAndAlignmentAndPaddingJasper("Grading System",2,fontPath, "#000000",13,"", Element.ALIGN_LEFT, 5.0f));
 				gradingSystemTable.addCell(PDFGenerator.getPdfCellAlignCenter("Grade Name","#000",fontPath));
 				gradingSystemTable.addCell(PDFGenerator.getPdfCellAlignCenter("Description","#000",fontPath));
 	        	for(OverAllGrades overAllGrade : overAllGrades){
 	        	 gradingSystemTable.addCell(PDFGenerator.getPdfCellAlignCenter(overAllGrade.getGradeName(),"#000",fontPath));
 	        	 gradingSystemTable.addCell(PDFGenerator.getPdfCellAlignCenter(String.valueOf(overAllGrade.getDescription()),"#000",fontPath));
 	        	}
 			}
 	       	//For displaying Term -1,Term -2
 			int headerwidths[] = new int[academicPerformanceTableWidth];
 	       	headerwidths[0]= 4;
 	       	int displayedSubtypesCount = 1;
 	       	//Arrays.fill(headerwidths, 1, (academicPerformanceTableWidth), 2);
 			studentMarksTable = new PdfPTable(academicPerformanceTableWidth);
 	       	studentMarksTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
 	       	studentMarksTable.setWidthPercentage(100);
 	       	studentMarksTable.addCell(PDFGenerator.getPdfCellAlignCenterWithColspanAndBorder(" ", "#000",fontPath,1));
 	       	//For displaying ActivityTypes headers
 	       	int activitiesTableWidth = examTypesList.size()*2+1;
 	       	activitiesHeaderTable = new PdfPTable(activitiesTableWidth);
 	       	activitiesHeaderTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
 	       	activitiesHeaderTable.setWidthPercentage(100);
 	       	activitiesHeaderTable.addCell(PDFGenerator.getPdfCellAlignCenterWithColspanAndBorder("", "#000",fontPath,1));
 	       	/*select ss.subjectName,IFNULL(term2St2Grade.grade,'') as term2St2Grade,IFNULL(term2St3Grade.grade,'') as term2St3Grade,
 	       	  ss.term2Grade,ss.term2GradePoint,IFNULL(term4St2Grade.grade,'') as term4St2Grade,IFNULL(term4St3Grade.grade,'') as term4St3Grade,
 	       	  ss.term4Grade,ss.term4GradePoint from vw_studentScoreCardMarksDetails ss  LEFT JOIN subTypeGrades term2St2Grade on 
 	       	  (term2St2Grade.academicYearId = ss.academicYearId and term2St2Grade.subTypeId = 29 and ROUND(ss.term2St2) >= term2St2Grade.minMarks and ROUND(ss.term2St2) <= term2St2Grade.maxMarks) 
 	       	  LEFT JOIN subTypeGrades term2St3Grade on (term2St3Grade.academicYearId = ss.academicYearId and term2St3Grade.subTypeId = 30 and 
 	       	  ROUND(ss.term2St3) >= term2St3Grade.minMarks and ROUND(ss.term2St3) <= term2St3Grade.maxMarks) LEFT JOIN subTypeGrades term4St2Grade on (term4St2Grade.academicYearId =
 	       	  ss.academicYearId and term4St2Grade.subTypeId = 29 and ROUND(ss.term4St2) >= term4St2Grade.minMarks and ROUND(ss.term4St2) <= term4St2Grade.maxMarks) LEFT JOIN 
 	       	  subTypeGrades term4St3Grade on (term4St3Grade.academicYearId = ss.academicYearId and term4St3Grade.subTypeId = 30 and ROUND(ss.term4St3) >= term4St3Grade.minMarks 
 	       	  and ROUND(ss.term4St3) <= term4St3Grade.maxMarks) where ss.studentId=6055 order by ss.subjectSortingOrder;*/
 	       	studentsMarksQuery.append("select ss.subjectName,");
 	       	for(ExamTypes etype : examTypesList){
 				if(!ObjectFunctions.isNullOrEmpty(etype)){
 					overAllGradeQuery = new StringBuffer();
 					activitiesHeaderTable.addCell(PDFGenerator.getPdfCellAlignCenterWithColspanAndBorder(etype.getExamType(),"#000",fontPath,2));
 					subTypes=adminManager.getAll("select subTypeId,subTypeName,scheduleMaxMarks,subTypeSortingOrder from vw_classExamDetails where eid="+etype.getId()+" and classSectionId="+studyClassId+" group by subTypeId order by IF(subTypeSortingOrder = 0,subTypeId,subTypeSortingOrder)");
 					if(!ObjectFunctions.isNullOrEmpty(subTypes)){
 						studentMarksTable.addCell(PDFGenerator.getPdfCellAlignCenterWithColspanAndBorder(etype.getExamType(),"#000",fontPath,subTypes.size()+2));
 						examSubTypeNames = new StringBuffer();
 						examSubTypeNames.append("( ");
 						overAllGradeQuery.append("select ");
 						for(Object[] subType: subTypes){
 							//Assigning widths to each exam type subtypes 
 							headerwidths[displayedSubtypesCount++] = 2;
 							//For displaying total subtypes of each exam type (FA+SA) in Term - I
 							if(examSubTypeNames.length() > 2)
 								examSubTypeNames.append("+"+subType[1].toString());
 							else						
 								examSubTypeNames.append(subType[1].toString());
 							/*Preparing all subtype name for printing performance table second row like 
 							 Subjects FA SA Overall Grade(FA + SA) Grade Points FA SA ....
 							  */
 							subTypeNames.add(subType[1].toString());
 							tableNameAlias = "term"+etype.getSortingOrder()+"St"+subType[3].toString()+"Grade";
 							studentsMarksQuery.append("IFNULL("+tableNameAlias+".grade,'') as "+tableNameAlias+",");
 							subTypeMarksGradesQuery.append(" LEFT JOIN subTypeGrades ");
 							subTypeMarksGradesQuery.append(tableNameAlias+" on ("+tableNameAlias+".academicYearId = ss.academicYearId and "+
 									tableNameAlias+".subTypeId = "+subType[0].toString()+" and ROUND(ss.term"+etype.getSortingOrder()+"St"+subType[3].toString()+") >= "+
 									tableNameAlias+".minMarks and ROUND(ss.term"+etype.getSortingOrder()+"St"+subType[3].toString()+") <= "+
 									tableNameAlias+".maxMarks)");
 							//For OverAll grade query displaying blank spaces
 							overAllGradeQuery.append("'',");
 						}
 						headerwidths[displayedSubtypesCount++] = 3;
  						headerwidths[displayedSubtypesCount++] = 2;
 						examSubTypeNames.append(" )");
 						/*Preparing all subtype name for printing performance table second row like 
 						 Subjects FA SA Overall Grade(FA + SA) Grade Points FA SA ....
 						  */
 						subTypeNames.add("Overall Grade\n "+examSubTypeNames.toString());
 						subTypeNames.add("Grade points");
 						/*Preparing query for getting each exam type grade and gradepoint like
 						 ss.term1Grade,ss.term1GradePoint,ss.term2Grade,ss.term2GradePoint,
 						 */
 						studentsMarksQuery.append("ss.term"+etype.getSortingOrder()+"Grade,");
 						studentsMarksQuery.append("ss.term"+etype.getSortingOrder()+"GradePoint,");
 						//For 
 						overAllGradeQuery.append("og.gradeName,ssMarks.term"+etype.getSortingOrder()+"GradePoint from overAllGrades as og RIGHT JOIN"+
 							"(select ROUND(AVG(ss.term"+etype.getSortingOrder()+"Total)) as term"+etype.getSortingOrder()+"TotalGrade"+
 							",ROUND(AVG(ss.term"+etype.getSortingOrder()+"GradePoint)) as term"+etype.getSortingOrder()+"GradePoint,ss.academicYearId from vw_studentScoreCardMarksDetails as ss where ss.studentId= $studentId$ ) as ssMarks "+
 							"on (ssMarks.academicYearId=og.academicYearId and ssMarks.term"+etype.getSortingOrder()+"TotalGrade >= "+
 							"og.minMarks and ssMarks.term"+etype.getSortingOrder()+"TotalGrade <= og.maxMarks)");
 						overAllGradeQueries.add(overAllGradeQuery.toString());	
 					}
 					subTypes = null;
 				}etype=null;
 			}
 	       	int activitiesHeaderwidths[] = new int[activitiesTableWidth];
 	       	activitiesHeaderwidths[0] = 4;
 	       	activitiesHeaderTable.addCell(PDFGenerator.getPdfCellAlignCenterWithColspanAndBorder("Activities","#000",fontPath,1));
 			for(int i=1;i < activitiesTableWidth ;i++){
 				if ( i % 2 == 0 ){
 					activitiesHeaderwidths[i]= 4;
 					activitiesHeaderTable.addCell(PDFGenerator.getPdfCellAlignCenterWithColspanAndBorder("DESCRIPTIVE INDICATORS","#000",fontPath,1));
 				}else{
 					activitiesHeaderwidths[i]= 1;
 					activitiesHeaderTable.addCell(PDFGenerator.getPdfCellAlignCenterWithColspanAndBorder("GRADE","#000",fontPath,1));
 				}
 	        }
 	       	activitiesHeaderTable.setWidths(activitiesHeaderwidths);
 	       	activitiesHeaderTableCell = new PdfPCell(activitiesHeaderTable);
 	       	activitiesHeaderTableCell.setColspan(activitiesHeaderwidths.length);
 	       	studentMarksTable.addCell(PDFGenerator.getPdfCellAlignCenterWithColspanAndBorder("Subjects", "#000",fontPath,1));
 	       	for(String subTypeName : subTypeNames){
 	       		studentMarksTable.addCell(PDFGenerator.getPdfCellAlignCenterWithColspanAndBorder(subTypeName, "#000",fontPath,1));
 	       	}
       		studentsMarksQuery.deleteCharAt(studentsMarksQuery.length()-1);
 	       	studentsMarksQuery.append(" from vw_studentScoreCardMarksDetails ss ");
 		    studentsMarksQuery.append(subTypeMarksGradesQuery);
 	       	studentMarksTable.setWidths(headerwidths);
 	       	subTypeNames = null;
 			for(ViewStudentPersonAccountDetails personAccountDetails:viewStudentPersonAccountDetailsList){
 			    log.debug("Stude Name: "+personAccountDetails.getPersonFullName());
 			    if(StringFunctions.isNotNullOrEmpty(academicYear.getManageAttendanceBy()) && "M".equalsIgnoreCase(academicYear.getManageAttendanceBy())){
 			    	studAttendance = adminManager.get("select sum(totalWorkingDays),sum(noOfPresentDays) from vw_StudentMonthlyAttendance where classSectionId="+personAccountDetails.getClassSectionId()+" and academicYearId="+getUsrChgedAcademicId()+" and accountId="+personAccountDetails.getAccountId());
 				    if(!ObjectFunctions.isNullOrEmpty(studAttendance) && !ObjectFunctions.isNullOrEmpty(studAttendance[0]) && !ObjectFunctions.isNullOrEmpty(studAttendance[1])){
 				    	personAccountDetails.setAnyString(studAttendance[1].toString());
 					    personAccountDetails.setSubjectName(studAttendance[0].toString());
 				    }
 		    	}else{
 		    		personAccountDetails.setSubjectName(String.valueOf(totalWorkingDays));
 				    if(!ObjectFunctions.isNullOrEmpty(academicYear.getStartDate()) && !ObjectFunctions.isNullOrEmpty(academicYear.getEndDate())){
 				    	if(DateFunctions.compare2Dates(new Date(),academicYear.getStartDate()) && DateFunctions.compare2Dates(academicYear.getEndDate(),new Date()))
 						{
 				    		log.debug("accountId="+personAccountDetails.getAccountId()+" and academicYearId="+personAccountDetails.getAcademicYearId()+" and present='N' and attendanceDate is not null and attendanceDate <='"+DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, new Date())+"'");
 				    		absentiesCount = adminManager.getCount("vw_StudentDailyAttendance", "accountId="+personAccountDetails.getAccountId()+" and academicYearId="+personAccountDetails.getAcademicYearId()+" and present='N' and attendanceDate is not null and attendanceDate <='"+DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, new Date())+"'");
 						}else if(new Date().after(academicYear.getEndDate())){
 							log.debug("accountId="+personAccountDetails.getAccountId()+" and academicYearId="+personAccountDetails.getAcademicYearId()+" and present='N' and attendanceDate is not null and attendanceDate <='"+DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, academicYear.getEndDate())+"'");
 							absentiesCount = adminManager.getCount("vw_StudentDailyAttendance", "accountId="+personAccountDetails.getAccountId()+" and academicYearId="+personAccountDetails.getAcademicYearId()+" and present='N' and attendanceDate is not null and attendanceDate <='"+DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, academicYear.getEndDate())+"'");
 						}
 				    }
 				    personAccountDetails.setAnyString(String.valueOf(totalWorkingDays-absentiesCount));
 		    	}
 	    		personAccountDetails.setOrganizationName(organizationName);
 	    		personAccountDetails.setAddressLine2(customerAddress.toString());
 			    jrprint=JasperFillManager.fillReport(sourceFile.toString(), null, new JRBeanCollectionDataSource(java.util.Arrays.asList(personAccountDetails)));
 			  List<InputStream> studentsPdfsList = new ArrayList<InputStream>();
 					try
 					{
 						exporter = new JRPdfExporter();
 						exporter.setParameter(JRExporterParameter.JASPER_PRINT, jrprint);
 						exporter.setParameter(JRExporterParameter.OUTPUT_FILE, new File(folderPath,personAccountDetails.getFirstName().trim() + personAccountDetails.getAdmissionNumber().trim()+"-P1.pdf"));
 						exporter.exportReport();
 							file = new FileOutputStream(new File(folderPath,personAccountDetails.getFirstName().trim() + personAccountDetails.getAdmissionNumber().trim()+"-P2.pdf"));
 							document = new Document();
 						    PdfWriter.getInstance(document, file);
 						    document.setPageSize(PageSize.A4.rotate());
 						    document.setMargins(22, 22, 26, 26);
 						    document.open();
 						    academicInfoBlockTable= new PdfPTable(1);
 						    academicInfoBlockTable.setWidthPercentage(100);
 						    academicInfoBlockTable.getDefaultCell().setBorder(Rectangle.BOX);
 						    academicInfoBlockTable.getDefaultCell().setBorderWidth(2);
 						    academicInfoTable = new PdfPTable(1);
 						    academicInfoTable.setWidthPercentage(100);
 						    academicInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
 						    academicInfoTable.setSplitLate(false);
 						    if(ObjectFunctions.isNotNullOrEmpty(examTypesList)){
 								academicPerformanceTable = new PdfPTable(1);
 								academicPerformanceTable.setWidthPercentage(100);
 								academicPerformanceTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
 								academicPerformanceTable.addCell(PDFGenerator.getPdfCellHeaderRowWithColspanAndFontColorFontSizeAndNOBackGrundColorAndAlignmentAndPaddingJasper("PART 1 : ACADEMIC PERFORMANCE",100,fontPath, "#000000",13,"", Element.ALIGN_LEFT, 5.0f));
 								academicMarksTable = new PdfPTable(studentMarksTable);
 								academicMarksTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
 								academicMarksTable.setWidthPercentage(100);
 								academicMarksTable.setWidths(headerwidths);
 								generateStudentAcademicPerformanceForStateBoardSchool(personAccountDetails.getStudentId(),academicMarksTable,fontPath,studentsMarksQuery.toString(),null);
 								//academicMarksTable.addCell(PDFGenerator.getPdfCellHeaderCellJasperClass("Over All Grades", "#000",fontPath));
 								if(ObjectFunctions.isNotNullOrEmpty(overAllGradeQueries)){
 									academicMarksTable.addCell(PDFGenerator.getPdfCellHeaderCellJasperClass("Over All Grades", "#000",fontPath));
 									for(String ovgQuery : overAllGradeQueries){
 										ovgQuery = ovgQuery.replace("$studentId$", personAccountDetails.getStudentId()+"");
 										ovgGradesList = adminManager.getAll(ovgQuery);
 										if(ObjectFunctions.isNotNullOrEmpty(ovgGradesList)){
 											for(Object[] ovgGrade: ovgGradesList){
 												for(int column = 0; column < ovgGrade.length; column++){
 													if(!ObjectFunctions.isNullOrEmpty(ovgGrade[column]) && StringFunctions.isNotNullOrEmpty(ovgGrade[column].toString()))
 														academicMarksTable.addCell(PDFGenerator.getPdfCellWithCenterAlignJasperNoBorder(ovgGrade[column].toString(),fontPath));
 													else{
 														academicMarksTable.addCell(PDFGenerator.getPdfCellWithCenterAlignJasperNoBorder("",fontPath));
 													}
 												}
 											}
 											ovgGradesList = null;
 										}
 										ovgQuery=null;
 									}
 								}
 								academicPerformanceTable.addCell(academicMarksTable);
 								academicInfoTable.addCell(academicPerformanceTable);
 							}
 						    if(ObjectFunctions.isNotNullOrEmpty(studentActivities)){
 								academicPerformanceTable = new PdfPTable(1);
 								academicPerformanceTable.setWidthPercentage(100);
 								academicPerformanceTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
 								generateStudentsActivitiesReport(personAccountDetails.getStudentId(),studentActivities,academicPerformanceTable,activitiesHeaderwidths,fontPath,activitiesHeaderTableCell,null,personAccountDetails.getClassNameClassId());
 								academicInfoTable.addCell(academicPerformanceTable);
 							}
 						    if(!ObjectFunctions.isNullOrEmpty(gradingSystemTable)){
 						    	academicInfoTable.addCell(gradingSystemTable);
 						    }
 						    academicInfoBlockTable.addCell(academicInfoTable);
 						    document.add(academicInfoBlockTable);
 						    signTable = new PdfPTable(3);
 							signTable.setWidthPercentage(100);
 							signTable.addCell(PDFGenerator.getPdfCellHeaderRowWithColspanAndFontColorFontSizeAndNOBackGrundColorAndAlignmentAndPaddingJasper(" ",3,fontPath, "#000000",13,"", Element.ALIGN_LEFT, 40.0f));
 							signTable.addCell(PDFGenerator.getPdfCellHeaderRowWithColspanAndFontColorFontSizeAndNOBackGrundColorAndAlignmentAndPaddingJasper("Parent",1,fontPath, "#000000",13,"", Element.ALIGN_LEFT, 5.0f));
 							signTable.addCell(PDFGenerator.getPdfCellHeaderRowWithColspanAndFontColorFontSizeAndNOBackGrundColorAndAlignmentAndPaddingJasper("Class Teacher",1,fontPath, "#000000",13,"", Element.ALIGN_CENTER, 5.0f));
 							signTable.addCell(PDFGenerator.getPdfCellHeaderRowWithColspanAndFontColorFontSizeAndNOBackGrundColorAndAlignmentAndPaddingJasper("Principal",1,fontPath, "#000000",13,"", Element.ALIGN_RIGHT, 5.0f));
 							document.add(signTable); 
 							exporter=null;
 							file.flush();
 						    document.close();
 						    file.close();
 						    
 							int rotation;
 							//Refer http://www.massapi.com/class/pd/PdfReader.html
 							studentsPdfsList.add(new FileInputStream(new File(folderPath,personAccountDetails.getFirstName().trim() + personAccountDetails.getAdmissionNumber().trim()+"-P1.pdf")));
 						    studentsPdfsList.add(new FileInputStream(new File(folderPath,personAccountDetails.getFirstName().trim() + personAccountDetails.getAdmissionNumber().trim()+"-P2.pdf")));
 							for (InputStream in : studentsPdfsList) {
 								PdfReader reader = new PdfReader(in);
 								for (int i = 1; i <= reader.getNumberOfPages(); i++) {
 									pDFGenerator.getDocument().setPageSize(reader.getPageSizeWithRotation(i));
 									pDFGenerator.getDocument().newPage();
 									// import the page from source pdf
 									page = pDFGenerator.getPdfWriter().getImportedPage(reader, i);
 									// add the page to the destination pdf
 									//cb1.addTemplate(page1, 0, 0);
 									 rotation = reader.getPageRotation(i);
 									    if (rotation == 90 || rotation == 270) {
 									    	cb.addTemplate(page, 0, -1f, 1f, 0, 0, reader.getPageSizeWithRotation(i).getHeight());
 									    }
 									    else {
 									    	cb.addTemplate(page, 1f, 0, 0, 1f, 0, 0);
 									    }
 								}
 								reader.close();
 							}
 						exporter=null;
 						
 					}
 					catch(Exception ex)
 					{
 						ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
 					}finally{
 						cb.closePath();
 						//file.flush();
 						//document.close();
 						//file.close();
 						studentsPdfsList = null;
 					}
 					jrprint=null;
 					personAccountDetails= null;
 		    }
 			pDFGenerator.getDocument().close();
 		}catch (Exception ex) {
 			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
 		}
 		finally{
 			sourceFile = null;
 			academicInfoTable = null;
 			academicInfoBlockTable = null;
 			jrprint=null;
 			exporter = null;
 			pDFGenerator=null;
 			studentMarksTable = null;
 			activitiesHeaderTable = null;
 			academicPerformanceTable = null;
 			academicMarksTable = null;
 			subTypes = null;
 			academicInfoTable = null;
 			academicInfoBlockTable = null;
 			studentsMarksQuery = null;
 			subTypeNames = null;
 			examSubTypeNames = null;
 			subTypeMarksGradesQuery = null; 
 			file = null;
 			document = null;
 			cb = null;
 			page = null;
 			gradingSystemTable = null;
 			overAllGrades = null;
 			tableNameAlias = null;
 			signTable = null;
 			overAllGradeQueries= null;
 			overAllGradeQuery = null;
 			ovgGradesList = null;
 		}
     }
 	}
/********************************************************************
 * Date              Name               Description
 * ========          ============       ==================
 * May 13, 2013      Seshu		        For getting staff handling studyclasses. 
/********************************************************************/	
   @Actions( {
   		@Action(value = "ajaxGetStaffHandleClassSections", results = { @Result(location = "admin/ajaxDownloadMarkSheetTemplate.jsp", name = "success")})
   })     
   public String ajaxGetStaffHandleClassSections() throws URTUniversalException {
	if (log.isDebugEnabled()) {
		log.debug("Entering 'ajaxGetStaffHandleClassSections' method");
	}
	try {
		if(getUser().isSchoolPrincipal())
			ajaxDownloadMarksSheet();
		else{
			allTeachersAndHodStudyClassesList(getUser().getId(),getUserAcademicYearId());
		/*	StringBuffer query = new StringBuffer("FROM StudyClass studyClass WHERE studyClass.id in(")
			.append("SELECT cteacher.studyClass FROM ClassTeacher cteacher WHERE cteacher.staff.account=").append(getUser().getId())
		  	.append(" and cteacher.academicYear=").append(getUserAcademicYearId()).append(" and cteacher.staff.status='Y' group by cteacher.studyClass)");
		  		//setStudyClassList(adminManager.getAllHqlQuery(query.toString()));
			HashSet<StudyClass> classSections = new HashSet<StudyClass>();
		  		List studyClasseList = adminManager.getAllHqlQuery(query.toString());
		  		if(!ObjectFunctions.isNullOrEmpty(studyClasseList)){
		  			classSections.addAll(studyClasseList);
		  		}
		  		if(getUser().isOnlySchoolHod()){
		  			
		  			Object[] staff = adminManager.get("select id,description from staff where accountId="+getUser().getId()+" and  status='Y'");
					if(!ObjectFunctions.isNullOrEmpty(staff))
					{
						if(!ObjectFunctions.isNullOrEmpty(staff[0]))
						{
							List studyClassesList =getHodStudyClassesList(Long.valueOf(staff[0].toString()),getUserAcademicYearId());
							if(!ObjectFunctions.isNullOrEmpty(studyClassesList))
							{
								classSections.addAll(studyClassesList);
							}
							studyClassesList =  null;
						}	
					}
				}
		  		if (ObjectFunctions.isNotNullOrEmpty(classSections)) {
					setStudyClassList(ConvertUtil.convertSetToList(classSections));
					Collections.sort(getStudyClassList());
				}
		  		classSections = null;
	  		if(ObjectFunctions.isNotNullOrEmpty(getStudyClassList()))
		  		Collections.sort(getStudyClassList());
		  	query = null;*/
		}
	}catch (Exception ex) {
		ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	}
	return SUCCESS;
   }
/********************************************************************
 * Date              Name               Description
 * ========          ============       ==================
 * May 14, 2013      Seshu		        For uploading students marks sheet. 
/********************************************************************/	
   @Actions({
		@Action(value = "ajaxUploadMarksSheetForStaff", results = { @Result(location = "staff/uploadMarksSheetForStaff.jsp", name = "success"),
				@Result(location = "staff/uploadMarksSheetForStaff.jsp", name = "dummyInit")}),
		@Action(value = "ajaxUploadMarksSheet", results = { @Result(location = "admin/ajaxUploadMarksSheet.jsp", name = "success"),
				 @Result(location = "admin/ajaxUploadMarksSheet.jsp", name = "dummyInit") })
	})
	public String uploadMarksSheet()
   {
	   
	   try {
		  /* FileInputStream file = new FileInputStream(getUpload());
		    //Get the workbook instance for XLS file
		   org.apache.poi.ss.usermodel.Workbook workbook = WorkbookFactory.create(file);
		   org.apache.poi.ss.usermodel.Sheet sheet = workbook.getSheetAt(0);*/
			   
			
			//return uploadStudentMarks();
		   return uploadStudentMarksNew();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return null;
		
  		
   }
/********************************************************************
 * Date              Name               Description
 * ========          ============       ==================
 * May 14, 2013      Seshu		        For downloading marks sheets for classes by admin. 
/********************************************************************/
  @Actions({
		@Action(value = "ajaxDownloadMarksSheet", results = { @Result(location = "admin/ajaxDownloadMarkSheetTemplate.jsp", name = "success") }),
		@Action(value = "ajaxDoAddMarks", results = { @Result(location = "admin/ajaxDoAddMarks.jsp", name = "success") }),
		
  })
	public String ajaxDownloadMarksSheet() throws URTUniversalException {
	if (log.isDebugEnabled()) {
		log.debug("Entering 'ajaxDownloadMarksSheet' method");
	}
	try
	{
		if(getUser().isSchoolAdmin() || getUser().isSchoolClerk()){
            if(getUserAcademicYearId() > 0)
                setStudyClassList(adminManager.getAll(StudyClass.class, "custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()));
          if(ObjectFunctions.isNotNullOrEmpty(getStudyClassList()))
                Collections.sort(getStudyClassList());
        }else
        allTeachersAndHodStudyClassesList(getUser().getId(),getUserAcademicYearId());
		/*if(getUserAcademicYearId() > 0)
			setStudyClassList(adminManager.getAll(StudyClass.class, "academicYearId="+getUserAcademicYearId()));
		if(ObjectFunctions.isNotNullOrEmpty(getStudyClassList()))
			Collections.sort(getStudyClassList());*/
	}
	catch(Exception ex)
	{
		ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	}
	return SUCCESS;
	}

  /********************************************************************
   * Date              Name               Description
   * ========          ============       ==================
   * May 17, 2013      CVS		      For managing score card generation.
  /********************************************************************/
   @Actions( {
		@Action(value = "ajaxManageScorecardGeneration", results = { @Result(location = "scorecard/ajaxManageScoreCardGeneration.jsp", name = "success")})})
	 public String ajaxManageScorecardGeneration(){
	 if (log.isDebugEnabled()) {
		log.debug("Entering 'ajaxManageScorecardGeneration' method");
	}
	 List<ClassTeacher> teacherSubjetsList = null;
	 Staff staff = null;
	 if (getUserAcademicYearId() > 0) {
		 StringBuffer query1 = new StringBuffer("select examTypeId  FROM vw_studentExamMarks WHERE ");
			query1.append(" custId="+getUserCustId());										 
			query1.append(" and academicYearId=").append(getUserAcademicYearId());
		 if(StringFunctions.isNotNullOrEmpty(getAnyTitle())){
			 setStudyClassList(null);
				if(getUser().getId() > 0 )
				{
					staff =adminManager.getStaffByAcountId(getUser().getId(),Constants.YES_STRING);
					if (!ObjectFunctions.isNullOrEmpty(staff)) {
						if (staff.getId() > 0) {
							if(getUser().isSchoolTeacher())
								teacherSubjetsList = staffManager.getAll(ClassTeacher.class,"teacherId="+staff.getId()+" and academicYearId="+getUserAcademicYearId()+" and custId="+getUserCustId()+" and classTeacher='"+getAnyTitle()+"' group by classTeacher");
							if (!ObjectFunctions.isNullOrEmpty(teacherSubjetsList)) {
								for (ClassTeacher teacher : teacherSubjetsList) {
									if (!ObjectFunctions.isNullOrEmpty(teacher.getStudyClass())) {
										query1.append(" and classSectionId in ("+teacher.getStudyClassId()+")");	
									}
									if(StringFunctions.isNullOrEmpty(teacher.getStudyClass().getSection()))
							    	{
							    		setTitle(teacher.getStudyClass().getClassName());
							    	}else{
							    		setTitle(teacher.getStudyClass().getClassName().trim()+" - "+teacher.getStudyClass().getSection().trim());
							    	}
								}
							}
							if(getUser().isOnlySchoolHod()){
								 StringBuffer sqlQuery = new StringBuffer("select st.id from staffMultipleStudyClasses sm LEFT JOIN studyClass st  on (st.id=sm.studyClassId)") 
							    	.append(" where st.custId=").append(getUserCustId()).append(" and st.academicYearId="+getUserAcademicYearId()).append(" and sm.staffId=").append(staff.getId());
								 List<BigInteger> studyclassesList = adminManager.getAll(sqlQuery.toString());
									if(ObjectFunctions.isNotNullOrEmpty(studyclassesList)){
										String studyclassesIds = StringFunctions.convertListToCommaDelimitedString(studyclassesList);
										query1.append(" and classSectionId in ("+studyclassesIds+")");
									}
							}
							
						}
					}
					staff=null;
					teacherSubjetsList = null;
				}
		 } 
		 setAnyTitle(null);
		 query1.append(" and  examDate is not null group by examTypeId");
		 List<BigInteger> examTypeIdList = adminManager.getAll(query1.toString());
			if(ObjectFunctions.isNotNullOrEmpty(examTypeIdList)){
				String examTypeIdIds = StringFunctions.convertListToCommaDelimitedString(examTypeIdList);
				List<ExamTypes> examTypeIdist =adminManager.getAll(ExamTypes.class, "id in ("+examTypeIdIds+")");
				if(!ObjectFunctions.isNullOrEmpty(examTypeIdist)){
					getObjectList().addAll(examTypeIdist);
				}
		 }
		query1=null;
		}
	return SUCCESS; 
}

   /*@Actions( {
		@Action(value = "ajaxManageScorecardGeneration", results = { @Result(location = "scorecard/ajaxManageScoreCardGeneration.jsp", name = "success")})})
	 public String ajaxManageScorecardGeneration(){
	 if (log.isDebugEnabled()) {
		log.debug("Entering 'ajaxManageScorecardGeneration' method");
	}
	 List<ClassTeacher> teacherSubjetsList = null;
	 Staff staff = null;
	 List<ScoreCardTemplates> scoreCardTemplates =  null;
	 if (getUserAcademicYearId() > 0) {
		 StringBuffer query = null;
		StringBuffer querys = new StringBuffer();
		 HashSet<StudyClass> classSections = new HashSet<StudyClass>();
		 if(StringFunctions.isNotNullOrEmpty(getAnyTitle())){
			 setStudyClassList(null);
				if(getUser().getId() > 0 )
				{
					staff =adminManager.getStaffByAcountId(getUser().getId(),Constants.YES_STRING);
					if (!ObjectFunctions.isNullOrEmpty(staff)) {
						if (staff.getId() > 0) {
							StringBuffer studyClassId = new StringBuffer("(");
							query = new StringBuffer("custId=").append(getUserCustId()).append(" and className in (");
							if(getUser().isSchoolTeacher())
								teacherSubjetsList = staffManager.getAll(ClassTeacher.class,"teacherId="+staff.getId()+" and academicYearId="+getUserAcademicYearId()+" and custId="+getUserCustId()+" and classTeacher='"+getAnyTitle()+"' group by classTeacher");
							if (!ObjectFunctions.isNullOrEmpty(teacherSubjetsList)) {
								for (ClassTeacher teacher : teacherSubjetsList) {
									query.append("'");
									if (!ObjectFunctions.isNullOrEmpty(teacher.getStudyClass())) {
										scoreCardTemplates = adminManager.getAll(ScoreCardTemplates.class, "custId="+ getUserCustId()+" and className='"+teacher.getStudyClass().getClassAndSection()+"'");
										if (!ObjectFunctions.isNullOrEmpty(scoreCardTemplates)) {
	 										for(ScoreCardTemplates template : scoreCardTemplates){
	 											if(!ObjectFunctions.isNullOrEmpty(template)){
	 												studyClassId.append(template.getExamTypeId());
	 												studyClassId.append(",");
	 											}
	 										}
	 										classSections.add(teacher.getStudyClass());
										}
									}
									query.append(teacher.getStudyClass().getClassAndSection()).append("'").append(",");
									if(StringFunctions.isNullOrEmpty(teacher.getStudyClass().getSection()))
							    	{
							    		setTitle(teacher.getStudyClass().getClassName());
							    	}else{
							    		setTitle(teacher.getStudyClass().getClassName().trim()+" - "+teacher.getStudyClass().getSection().trim());
							    	}
								}
							}
							if(getUser().isOnlySchoolHod()){
								 StringBuffer sqlQuery = new StringBuffer("select st.id,st.className,st.section from staffMultipleStudyClasses sm LEFT JOIN studyClass st  on (st.id=sm.studyClassId)") 
							    	.append(" where st.custId=").append(getUserCustId()).append(" and st.academicYearId="+getUserAcademicYearId()).append(" and sm.staffId=").append(staff.getId());
									List<Object[]> studyclassesList=staffManager.getAll(sqlQuery.toString());
								if(!ObjectFunctions.isNullOrEmpty(studyclassesList))
								{
									for(Object[] obj : studyclassesList){
										if(!ObjectFunctions.isNullOrEmpty(obj[2]) || !ObjectFunctions.isNullOrEmpty(obj[1])){
	 										scoreCardTemplates = adminManager.getAll(ScoreCardTemplates.class, "custId="+ getUserCustId()+" and className='"+obj[1].toString()+" - "+obj[2].toString()+"'");
	 										if (!ObjectFunctions.isNullOrEmpty(scoreCardTemplates)) {
		 										for(ScoreCardTemplates templates : scoreCardTemplates){
		 											if(!ObjectFunctions.isNullOrEmpty(templates)){
		 												studyClassId.append(templates.getExamTypeId());
		 												studyClassId.append(",");
		 											}
		 										}
		 										StudyClass st= new StudyClass();
		 					    				st.setId(Long.valueOf(obj[0].toString()));
		 					    				st.setClassName(obj[1].toString());
		 					    				st.setSection(obj[2].toString());
		 					    				classSections.add(st);
	 										}
											query.append("'");
											if(ObjectFunctions.isNullOrEmpty(obj[2])){
												query.append(obj[1].toString());
												setTitle(obj[1].toString());
											}else{
												query.append(obj[1].toString()+" - "+obj[2].toString());
												setTitle(obj[1].toString()+" - "+obj[2].toString());
											}
											query.append("'").append(",");
										}
								  }
									studyclassesList  = null;
								}
							}
							studyClassId.append("0)");
							querys.append(studyClassId);
							log.debug("querys"+querys.toString());
							if(!ObjectFunctions.isNullOrEmpty(query.toString())){
								List<ExamTypes> examTypeList = adminManager.getAll(ExamTypes.class,"id in "+querys.toString()+" and custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId());
								if(!ObjectFunctions.isNullOrEmpty(examTypeList)){
									getObjectList().addAll(examTypeList);
								}
							}
							query.append("0)");
							log.debug("querys"+query.toString());
							if(!ObjectFunctions.isNullOrEmpty(query.toString())){
								setTempId(adminManager.getCount("scoreCardTemplates", query.toString()));
							}
							if (ObjectFunctions.isNotNullOrEmpty(classSections)) {
								setStudyClassList(ConvertUtil.convertSetToList(classSections));
								Collections.sort(getStudyClassList());
							}
							query = null;
							querys= null;
						}
					}
					staff=null;
					teacherSubjetsList = null;
				}
		 }else{
			 scoreCardTemplates = adminManager.getAll(ScoreCardTemplates.class, "custId="+ getUserCustId()+" group by examTypeId");
				if (!ObjectFunctions.isNullOrEmpty(scoreCardTemplates)) {
					for(ScoreCardTemplates template : scoreCardTemplates){
						if(!ObjectFunctions.isNullOrEmpty(template)){
							ExamTypes examType = (ExamTypes) adminManager.get(ExamTypes.class,"id="+template.getExamTypeId()+" and custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId());
							if(!ObjectFunctions.isNullOrEmpty(examType)){
								getObjectList().add(examType);
							}
						}
					}
				} 
				 query = new StringBuffer("custId=").append(getUserCustId());
				setTempId(adminManager.getCount("scoreCardTemplates", query.toString()));
				query = null;
		 }
		 classSections = null;
		}
	return SUCCESS; 
	*/
   /********************************************************************
    * Date              Name               Description
    * ========          ============       ==================
    * May 20, 2013      Seshu		      Displaying screen for uploading score card template.
   /********************************************************************/
   @Actions( {@Action(value = "ajaxDoUploadScoreCardTemplate", results = {@Result(location = "scorecard/ajaxUploadScorecardTemplate.jsp", name = "success") })/*,
 	   @Action(value = "ajaxDoTCTemplateAndBookSettings", results = {@Result(location = "academic/tcGeneration/ajaxAddTcSettings.jsp", name = "success")})*/
 	   })
 	public String ajaxDoUploadScoreCardTemplate() throws URTUniversalException {
 		if (log.isDebugEnabled()) {
 			log.debug("Entering 'ajaxDoUploadScoreCardTemplate' method");
 		}
 		try {
 			//setClassList(adminManager.getAllClassNames( getUserCustId(),getUserAcademicYearId()));
 			 /*StringBuffer query = new StringBuffer("custId=").append(getUserCustId());
 			setObjectList(adminManager.getAll(ScoreCardTemplates.class, query.toString()));
 			query = null;*/
 			/*Ganesh : above code we commentted because we we are showing old academic year tempaltes and while downlaoding we are giving path cusrrent academic year in that case we are getting file not found exception. If they want they can switch the academic year and download old file. If they want to do any modification they will and upload for new acdemic year. */
 			setObjectList(adminManager.getAllScorecardTemplatesByAcademicYearId(getUserCustId(),getUserAcademicYearId()));
 			checkStudyClassHavingStudentsOrNot();
 			//Collections.sort(getStudyClassList());
 			setExamTypeList(getAllExamTypesByClassId(0,0,getUserCustId(),getUserAcademicYearId()));
 			/*if(!ObjectFunctions.isNullOrEmpty(getExamTypeList()))
 			{
 				setExamTypes((ExamTypes)getExamTypeList().get(0));
 			}*/
 		} catch (Exception ex) {
 			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
 		}
 		return SUCCESS;
 	}
  /********************************************************************
     * Date              Name               Description
     * ========          ============       ==================
     * May 21, 2013      Seshu		      For storing score card template in userfiles directory
    /********************************************************************/
    @Actions( {@Action(value = "ajaxUploadScoreCardTemplate", results = {@Result(location = "scorecard/ajaxManageScoreCardGeneration.jsp", name = "success") })})
  	public String ajaxUploadScoreCardTemplate() throws URTUniversalException {
  		if (log.isDebugEnabled()) {
  			log.debug("Entering 'ajaxUploadScoreCardTemplate' method");
  		}
  		try {
  			if (StringFunctions.isNotNullOrEmpty(getAnyId()) && !ObjectFunctions.isNullOrEmpty(getTempString())) {
  				List<String> classNamesList = Arrays.asList(getAnyId().split(","));
  				List<ExamTypes> examTypesList = adminManager.getAll(ExamTypes.class,"id in "+getTempString()+" and custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+"");
  				Customer customer = getCustomerByCustId();
  				if(!ObjectFunctions.isNullOrEmpty(customer) && StringFunctions.isNotNullOrEmpty(customer.getCustomerShortName())){
  					//StringBuffer pathName = null;
  					if (getUserAcademicYearId() != 0) {
  						ScoreCardTemplates scoreCardTemplate = null;
  						StringBuffer query = null;
  					//	File templateFile = null;
  						MultiPartRequestWrapper multiWrapper = (MultiPartRequestWrapper) ServletActionContext.getRequest();
  						if(!ObjectFunctions.isNullOrEmpty(examTypesList) && !ObjectFunctions.isNullOrEmpty(classNamesList))
  						{
  							AcademicYear academicYear = (AcademicYear)adminManager.get(AcademicYear.class,getUserAcademicYearId());
  							int i=0;
  							String filePath = null;
  							Enumeration<String> fileParaNames = multiWrapper.getFileParameterNames();
  							while (fileParaNames.hasMoreElements()) 
  							{
  								String key = fileParaNames.nextElement();
  								File[] fileObject = multiWrapper.getFiles(key);
  								String[] localSysfileNames = multiWrapper.getFileNames(key);
  								setUploadFileName(StringFunctions.stripSymbols(localSysfileNames[0]));
  								//pathName = new StringBuffer("userFiles/ScoreCardTemplates/").append(customer.getCustomerShortName()).append("/"+academicYear.getAcademicYear()).append("/"+obj.getExamType()).append("/"+getUploadFileName());
  								//File destDir = new File(getSession().getServletContext().getRealPath(pathName.toString()));
  								filePath = adminManager.getUploadTemplates(fileObject[0], academicYear.getAcademicYear(), getUploadFileName());
  								
  								//FileUtils.copyFile(fileObject[0], destDir);
  								
  								key = null;
  								fileObject = null;
  								localSysfileNames = null;
  								//destDir = null;
  								//pathName = null;
  							}
  							
							if(StringFunctions.isNotNullOrEmpty(filePath) )
							{
								File scoreCardAcademicfile = null;
								
								URL url = new URL(filePath);
								URLConnection conn = url.openConnection();
								InputStream is = conn.getInputStream();
								scoreCardAcademicfile = File.createTempFile(getUploadFileName(), null);
								scoreCardAcademicfile.deleteOnExit();
								FileUtils.copyInputStreamToFile(is, scoreCardAcademicfile);
								if(!ObjectFunctions.isNullOrEmpty(scoreCardAcademicfile)){
									if(scoreCardAcademicfile.exists()){
										readScoreCardActivitiesConfigScheet(scoreCardAcademicfile);
									}
								}
								
								scoreCardAcademicfile.delete();
							}

  						for(ExamTypes obj : examTypesList){		
	  						for(String className : classNamesList){
	  							query = new StringBuffer("custId=").append(getUserCustId()).append(" and className='").append(className).append("' and examTypeId=").append(obj.getId());
	  							scoreCardTemplate = (ScoreCardTemplates)adminManager.get(ScoreCardTemplates.class, query.toString());
	  							query = null;
	  							if(ObjectFunctions.isNullOrEmpty(scoreCardTemplate)){
	  								scoreCardTemplate = new ScoreCardTemplates();
	  								scoreCardTemplate.setCreatedById(getUser().getId());
	  								scoreCardTemplate.setCreatedDate(new Date());
	  							}
	  							/*else
	  							{
	  								try {
										S3Wrapper s3Wrapper = new S3Wrapper();
										URL url = new URL(scoreCardTemplate.getFilePath());
										s3Wrapper.delete(url);
									} catch (Exception e) {
										e.printStackTrace();
									}
	  							}*/
	  							/*else if(StringFunctions.isNotNullOrEmpty(scoreCardTemplate.getFileName())){
	  								templateFile = new File(getSession().getServletContext().getRealPath(scoreCardTemplate.getFileName()));
	  								FileUtils.deleteQuietly(templateFile);
	  								scoreCardTemplate.setFileName(null);
	  							}else if(StringFunctions.isNotNullOrEmpty(scoreCardTemplate.getDocFileName())){
	  								templateFile = new File(getSession().getServletContext().getRealPath(scoreCardTemplate.getDocFileName()));
	  								FileUtils.deleteQuietly(templateFile);
	  								scoreCardTemplate.setDocFileName(null);
	  							}*/
	  							//scoreCardTemplate.setFileName(null);
	  							//scoreCardTemplate.setDocFileName(null);
	  							
	  							if(getUploadFileName().toString().contains(".docx") || getUploadFileName().toString().contains(".doc")){
  									scoreCardTemplate.setDocFileName(getUploadFileName());
  								}
  								else if(getUploadFileName().toString().contains(".xls") || getUploadFileName().toString().contains(".xlsx")){
  									scoreCardTemplate.setFileName(getUploadFileName());
  								}
	  							
	  							scoreCardTemplate.setFilePath(filePath);
	  							scoreCardTemplate.setCustId(getUserCustId());
	  							scoreCardTemplate.setLastUpdatedById(getUser().getId());
	  							scoreCardTemplate.setLastUpdatedDate(new Date());
	  							scoreCardTemplate.setClassName(className);
	  							scoreCardTemplate.setExamTypeId(obj.getId());
	  							scoreCardTemplate.setExamType(obj.getExamType());
	  							adminManager.save(scoreCardTemplate);
	  							scoreCardTemplate = null;
	  							className = null;
	  							i++;
	  						}
  						}
  						super.addActionMessage("Successfully uploaded score card template.");
  						
  						}
  					//	templateFile = null;
  					}
  					//pathName = null;
  					customer = null;
  				}else{
  					log.error("Customer not found or customer short name not defined.");
  					super.addActionError("Score card template not uploaded.");
  				}
  			}
  		} catch (Exception ex) {
  			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
  		}finally{
  			ajaxManageScorecardGeneration();
  		}
  		return SUCCESS;
  	}

  /********************************************************************
   * Date              Name               Description
   * ========          ============       ==================
   * May 24, 2013      Seshu		      For removing score card template.
   * Aug 20,2013	   Seshu			  Remove file from server if it is not assigned to another class.
  /********************************************************************/	
  @Actions( {@Action(value = "ajaxDeleteScoreCardTemplate", results = {@Result(location = "scorecard/ajaxUploadScorecardTemplate.jsp", name = "success") })})
	public String ajaxDeleteScoreCardTemplate() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDeleteScoreCardTemplate' method");
		}
		try {
			if(getTempId()>0){
				
				ScoreCardTemplates scoreCardTemplate = (ScoreCardTemplates)adminManager.get(ScoreCardTemplates.class, getTempId());
				
				List<ScoreCardTemplates> scoreCardTemplateList = adminManager.getAll(ScoreCardTemplates.class, "custId="+scoreCardTemplate.getCustId()+" and filePath='"+scoreCardTemplate.getFilePath()+"'");
				if (ObjectFunctions.isNullOrEmpty(scoreCardTemplateList)) 
				{/*
					S3Wrapper s3Wrapper = new S3Wrapper();
					URL url = new URL(scoreCardTemplate.getFilePath());
					s3Wrapper.delete(url);
				*/}
				scoreCardTemplateList = null;
				
				
				/*File templateFile= null;
				ScoreCardTemplates scoreCardTemplate = (ScoreCardTemplates)adminManager.get(ScoreCardTemplates.class, getTempId());
				if(!ObjectFunctions.isNullOrEmpty(scoreCardTemplate)){
					if(StringFunctions.isNotNullOrEmpty(scoreCardTemplate.getFileName())){
						int count = adminManager.getCount("scoreCardTemplates", "fileName='"+scoreCardTemplate.getFileName()+"'");
						if(count == 1 ){
							templateFile= new File(getSession().getServletContext().getRealPath(scoreCardTemplate.getFileName()));
							FileUtils.deleteQuietly(templateFile);
						}
					}
				}
				scoreCardTemplate = null;
				templateFile = null;*/
				adminManager.remove(ScoreCardTemplates.class, getTempId());
				super.addActionMessage("Successfully deleted score card template.");
				ajaxDoUploadScoreCardTemplate();
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
  
  /********************************************************************
   * Date              Name               Description
   * ============      =======		    ==================
   * May 27, 2013      Seshu		        For generating score card based on template.
  /********************************************************************/
    @Action(value = "ajaxGenerateScoreCard", results = { @Result(location = "student/ajaxViewStudentExamSchedules.jsp", name = "success")})
  	public void ajaxGenerateScoreCard() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxGenerateScoreCard' method");
		}
		//PdfImportedPage page = null;
		try {
			if(!StringFunctions.isNullOrEmpty(getAnyId())){
			  if("D".equalsIgnoreCase(getAnyId())){
				  ZipOutputStream  zipOutStream = new ZipOutputStream(getResponse().getOutputStream());
				 // StringBuffer filePath = new StringBuffer("userfiles/ccer/"+getCustomerByCustId().getCustomerShortName()+"/").append(getCurrentAcademicYear().getAcademicYear()+"/").append(getClassId());
				  StringBuffer  filePath = new StringBuffer("userfiles/ccer/"+getUserCustId()+"/").append(getUserAcademicYearId()+"/scorecards/").append(getParamValue("tempId1")+"/").append(getClassId().toString());
				  
				  StringBuffer  filePath1 = new StringBuffer("userfiles/tempFiles/"+getUserCustId()+"/").append(getUserAcademicYearId()+"/scorecards/").append(getParamValue("tempId1")+"/").append(getClassId().toString()); 
				  
				  File directoryToZip = new File(getSession().getServletContext().getRealPath(filePath1.toString()));
				  List<File> fileList = new ArrayList<File>();
				 
				  try {
						//File[] files = directoryToZip.listFiles();
						directoryToZip=new File(getSession().getServletContext().getRealPath(filePath1.toString()+"/new"));
						if(!directoryToZip.exists()){
							directoryToZip.mkdirs(); 
						}else{
		    			   if (directoryToZip.isDirectory())
		    			   {
		    				 FileUtils.deleteQuietly(directoryToZip);
		    			   }
			    		   directoryToZip.mkdirs(); 
						}
						File file1=null;
						//String zipFilePath = null;
						File targetFile = null;
						if(!StringFunctions.isNullOrEmpty(getSelectedId()))
						{
							String[] selectedStudents=getSelectedId().split(",");
							if(selectedStudents.length > 0)
							{
								if(selectedStudents.length > 1){
								for(int i=0;i<selectedStudents.length;i++){
									file1 = new File(getSession().getServletContext().getRealPath(filePath+"/"+selectedStudents[i]+".pdf"));
									if(file1.exists())
									{
										fileList.add(file1);
									}
									file1=null;
								 }
		  						for (File file : fileList) {
		  							if (file.isFile()) 
		  							{ 
		  								// we only files, not directories
		  								// we want the zipEntry's path to be a relative path that is relative
		  								// to the directory being zipped, so chop off the rest of the path
		  								//zipFilePath = file.getName();
		  							    targetFile = new File(directoryToZip+"/"+file.getName());
		  								FileUtils.copyFile(file, targetFile);
		  							    log.debug("Writing '" + file.getName() + "' to zip file");
  			  					  }
		  						}
		  						getResponse().setContentType("application/zip");
			  				    getResponse().addHeader("Content-Disposition", "attachment; filename=STUDENT_CC_REPORTS.zip");
			  				    //directory = new File(getSession().getServletContext().getRealPath(directoryToZip.toString()));
			    			   //For generating zip file
			    			    StringFunctions.zipFiles(directoryToZip,zipOutStream);
  				    			  
			  					//StringFunctions.zipFiles(directoryToZip, zos);
							    } 
								else{
									file1 = new File(getSession().getServletContext().getRealPath(filePath+"/"+selectedStudents[0]+".pdf"));
									if(file1.exists())
									{
										fileList.add(file1);
										targetFile = new File(directoryToZip+"/"+file1.getName());
										FileUtils.copyFile(file1, targetFile);
		  								//FileUtils.copyFile(file1, targetFile);
		  							    log.debug("Writing '" + file1.getName() + "' to Pdf file");
		  							    getResponse().setContentType("application/pdf");
	  				  				    getResponse().addHeader("Content-Disposition", "attachment; filename=STUDENT_REPORT.pdf");
			  				  			FileInputStream fileInputStream = new FileInputStream(targetFile);
			  				  			OutputStream responseOutputStream = getResponse().getOutputStream();
			  				  			int bytes;
			  				  			while ((bytes = fileInputStream.read()) != -1) {
			  				  				responseOutputStream.write(bytes);
			  				  			}
			  				  		fileInputStream.close();
			  				  		responseOutputStream.flush();
								}
							}
						 }
						}
					else{
						file1 = new File(getSession().getServletContext().getRealPath(filePath+"/all.pdf"));
						if(file1.exists()){
  							    targetFile = new File(directoryToZip+"/"+file1.getName());
  							    FileUtils.copyFile(file1, targetFile);
  								//FileUtils.copyFile(file1, targetFile);
  							    log.debug("Writing '" + file1.getName() + "' to Pdf file");
  							    getResponse().setContentType("application/pdf");
			  				    getResponse().addHeader("Content-Disposition", "attachment; filename=CLASSWISE_REPORT.pdf");
  				  				FileInputStream fileInputStream = new FileInputStream(targetFile);
	  				  			OutputStream responseOutputStream = getResponse().getOutputStream();
	  				  			int bytes;
	  				  			while ((bytes = fileInputStream.read()) != -1) {
	  				  				responseOutputStream.write(bytes);
	  				  			}
	  				  		fileInputStream.close();
	  				  		responseOutputStream.flush();
						}
					}
					
					getResponse().getOutputStream().flush();
				} catch (IOException ex) {
					ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
				}
			}
			else
			{
				generateScoreCardBase();
			}
	   }
		}catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
	
    }
     
	public void generateScoreCardBase()
    {
    	if (log.isDebugEnabled()) {
			log.debug("Entering 'generateScoreCardBase' method");
		}
    	try {
			ZipOutputStream  zipOutStream = new ZipOutputStream(getResponse().getOutputStream());
			StringBuffer studentIdsBuffer = new StringBuffer();
			File directory = null;
			StringBuffer query = null;
			HashMap<String, List> scoreCardTemplates = new HashMap<String, List>();
			ScoreCardTemplates scoreCardTemplate = null;
			List<ViewStudentPersonAccountDetails> studentsList = null;
			File scoreCardAcademicfile = null;
			HashMap<Long, String> schedulesCellInfo = null;
			HashMap<String, String> activitiesCellInfo =  null;
			HashMap<String, String> studentDetailsCellInfo =  null;
			HashMap<String, String> readSheets = new HashMap<String, String>();
			StringBuffer examTypes = new StringBuffer();
			getResponse().setContentType("application/zip");
			StringBuffer generatedScoreCardsFilePath = new StringBuffer();
			StringBuffer studentsFilePath = null;
			long classSectionId=0;
			String className = null;
			String[] classNameAndClassId =null;
			List templates = null;
			IXDocReport  report = null;
			IContext context = null;
			OutputStream out = null;
			File imageFile = null;
			IImageProvider logo =null;
			Customer customer = getCustomerByCustId();
			String temp = null;
			log.debug("temp string:" + getTempString());
			if(StringFunctions.isNotNullOrEmpty(getTempString())){
			 if(getTempString().split(":").length == 2){
				classNameAndClassId = getTempString().split(":");
				className = classNameAndClassId[0];
				classSectionId = Long.valueOf(classNameAndClassId[1]);
				getResponse().addHeader("Content-Disposition", "attachment; filename="+getAnyTitle().replace(' ', '-')+"_ScoreCards.zip");
				query = new StringBuffer("custId=").append(getUserCustId()).append(" and className='").append(className).append("'").append(" and examTypeId=").append(getTempId1());
				scoreCardTemplate = (ScoreCardTemplates)adminManager.get(ScoreCardTemplates.class, query.toString());
				query = null;
				if(!ObjectFunctions.isNullOrEmpty(scoreCardTemplate))
				{
					if(StringFunctions.isNotNullOrEmpty(scoreCardTemplate.getFileName()) || StringFunctions.isNotNullOrEmpty(scoreCardTemplate.getDocFileName()))
					{
						templates = new ArrayList();
						if(StringFunctions.isNotNullOrEmpty(scoreCardTemplate.getFileName()) )
						{
							URL url = new URL(scoreCardTemplate.getFilePath());
							URLConnection conn = url.openConnection();
							InputStream is = conn.getInputStream();
							
							scoreCardAcademicfile = File.createTempFile(scoreCardTemplate.getFileName(), null);
							scoreCardAcademicfile.deleteOnExit();
							FileUtils.copyInputStreamToFile(is, scoreCardAcademicfile);
							
							//scoreCardAcademicfile = new File(getSession().getServletContext().getRealPath(scoreCardTemplate.getFileName()));
							if(!ObjectFunctions.isNullOrEmpty(scoreCardAcademicfile)){
								if(scoreCardAcademicfile.exists()){
									templates.add(scoreCardAcademicfile);
									schedulesCellInfo = readScoreCardAcademicScheet(scoreCardAcademicfile,classSectionId,readSheets,examTypes);
									String isScholosticRequired = readSheets.get("IsScholosticConfig");
									if(!StringFunctions.isNullOrEmpty(isScholosticRequired)){
										if("Y".equalsIgnoreCase(isScholosticRequired)){
											activitiesCellInfo = readScoreCardScholasticConfigSheet(scoreCardAcademicfile);
										}
									}
									studentDetailsCellInfo = readScoreCardStudentsProfileSheet(scoreCardAcademicfile);
								}
							}
						}
						scoreCardTemplates.put(className, templates);
						templates = null;
					}
					scoreCardTemplate = null;
				}
			  }
			}
			if(!ObjectFunctions.isNullOrEmpty(scoreCardTemplates) && scoreCardTemplates.size() > 0){
				/*@Ganesh as per customer requerment we need to generate score cards for inactive customer also because few school giving scorecard even they inactivete student . If any one adding restricting inactvate student please ask team heads.*/
				query = new StringBuffer("custId=").append(getUserCustId()).append(" and classSectionId=").append(classSectionId).append(" and academicYearId=")
						.append(getUserAcademicYearId()).append(" and description is null");
				
				studentsList = adminManager.getAll(ViewStudentPersonAccountDetails.class ,query.toString());
				 
				if(ObjectFunctions.isNullOrEmpty(studentsList)){
					log.debug("----students are not there---");
					studentsFilePath = new StringBuffer(generatedScoreCardsFilePath).append("readMe.doc");
					adminManager.writeToFile("Students not found.",getSession().getServletContext().getRealPath(studentsFilePath.toString()));
					temp = "noStudents"; 
				}else{
					log.debug("----After got the data for students---"+studentsList.size());
					 
						generatedScoreCardsFilePath = new StringBuffer("userFiles/ScoreCardTemplates/").append(customer.getCustomerShortName()).append("/"+getCurrentAcademicYear().getAcademicYear()).append("/"+getExamType()).append("/ClassSection_").append(classSectionId).append("/");
						File outFile = new File(getSession().getServletContext().getRealPath(generatedScoreCardsFilePath.toString())); 
						if(outFile.exists())
							FileUtils.deleteDirectory(outFile);// Removes existing files
						
						outFile.mkdirs();
						outFile = null;
					 
					AcademicYear academicYear= (AcademicYear)adminManager.get(AcademicYear.class,getUserAcademicYearId());
					if(!ObjectFunctions.isNullOrEmpty(academicYear)){
					 String buffreStr=null;
					 if(!ObjectFunctions.isNullOrEmpty(examTypes)){
						 if(!examTypes.toString().equals("")){
					       buffreStr = examTypes.toString().substring(0, examTypes.toString().length() - 4);
						 }
				     }
					 
					studentIdsBuffer.append("(");
					HashMap<Long,Integer> studentRanking = null;
					double highestMarks=0;
					
					String isRankingRequired = readSheets.get("IsTotalRankRequired");
					if(!StringFunctions.isNullOrEmpty(isRankingRequired)){
						if("Y".equalsIgnoreCase(isRankingRequired)){
							studentRanking = new HashMap<Long,Integer>();
							StringBuffer query2=null; 
							List<Object[]> rankingList=null;
						    double existRankPosition=0;
						    double convertRankPosition=0;
						    int rankPosition=0;
						    
						    query2 = new StringBuffer("select SUM(obtainedMarks+moderationMarks) as totalMarks,rankPosition,IFNULL(studId,0) as studId FROM vw_studentExamMarks WHERE ");
							 query2.append(" custId="+getUserCustId());
							 query2.append(" and academicYearId=").append(getUserAcademicYearId());
							 query2.append( " and classSectionId="+classSectionId);
							 //query2.append(" and studId="+student.getStudId());
							 if(!StringFunctions.isNullOrEmpty(buffreStr)){
							      query2.append(" and ("+buffreStr.toString()+")");
							 }
							 query2.append("  GROUP BY studId ORDER BY totalMarks DESC");							 
							 rankingList = adminManager.getAll(query2.toString());
							//for(ViewStudentsScoreCardProfileDetails student : studentsList){
							if(!ObjectFunctions.isNullOrEmpty(rankingList)){	 
							 for(Object[] obj : rankingList){
								 convertRankPosition=new Double(obj[0].toString());
									 if(convertRankPosition > 0){
										 if(existRankPosition == convertRankPosition){
											 studentRanking.put(Long.valueOf(obj[2].toString()), rankPosition);
											 log.debug(Long.valueOf(obj[2].toString()) + "----Rank Position----"+ rankPosition);
										 }
										 else{
											   if(rankPosition==0){
											    highestMarks=new Double(obj[0].toString());
											    log.debug("----highestMarks in the class----"+ highestMarks);
											   }
											   
											 rankPosition++;
											 studentRanking.put(Long.valueOf(obj[2].toString()), rankPosition);
											 log.debug(Long.valueOf(obj[2].toString()) + "----Rank Position1----"+ rankPosition);
										 }
									 }else{
										 studentRanking.put(Long.valueOf(obj[2].toString()), 0);	
										 log.debug(Long.valueOf(obj[2].toString()) + "----Rank Position2----"+ 0);
									 }
									 existRankPosition= convertRankPosition;
								 }
							}
							query2=null; 
							existRankPosition=0;
							convertRankPosition=0;
							rankPosition=0;
							rankingList=null;
						}
					}
				Map<Integer,String> yearWiseMothNames = ajaxGetAllMonthNamesMonthIdByStartAndEndDate(academicYear.getStartDate(),new Date());
				Map<String,Integer> attendanceListObjMap = new HashMap<String, Integer>();
				HashMap<String,Integer> studentDailyAttendancePresntMap = new HashMap<String, Integer>();
			   	 if(academicYear.getManageAttendanceBy().equalsIgnoreCase("M"))  {
			   		 List<Object[]> attendanceListObj = adminManager.getAll("select max(totalWorkingDays),monthName,classSectionId from vw_StudentMonthlyAttendance where custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and classSectionId="+classSectionId+" and totalWorkingDays is not null group by monthName ");
			            if(!ObjectFunctions.isNullOrEmpty(attendanceListObj))
			            {
			                for(Object[] obj : attendanceListObj)
			                {
			                    if(!ObjectFunctions.isNullOrEmpty(obj))
			                    {
			                        if(!ObjectFunctions.isNullOrEmpty(obj[0]) && !ObjectFunctions.isNullOrEmpty(obj[1]))
			                        {
			                            attendanceListObjMap.put(obj[1].toString().toUpperCase(), Integer.valueOf(obj[0].toString()));
			                        }
			                    }
			                }
			               
			            }
			            attendanceListObj = null;
			        }
			   	 else
				   	{
			   		 	Date openDate = academicYear.getStartDate();
						Date closeDate = academicYear.getEndDate();
						Date todayDate=new Date();
						
			   		  log.debug("Before calling attendance SP");
			   		  List<Object[]> studentPresentCountList = adminManager.getStudentDailyAttendanceSP(getUserCustId(), getUserAcademicYearId(),classSectionId);
			   		  log.debug("After calling attendance SP");  
			   		 		   		  
			   		  	if(DateFunctions.compare2Dates(todayDate,openDate) && DateFunctions.compare2Dates(closeDate,todayDate))
						{
							attendanceListObjMap = studentManager.getMonthwiseSchoolWorkingDays(getUserCustId(),academicYear.getId(),openDate,todayDate,true,"", String.valueOf(classSectionId)); //here getClassId() used to academicyear have class wise holiday(CH).
						}else if(todayDate.after(closeDate)){
							attendanceListObjMap =studentManager.getMonthwiseSchoolWorkingDays(getUserCustId(),academicYear.getId(),openDate,closeDate,true,"", String.valueOf(classSectionId));
						}
						
						 if(!ObjectFunctions.isNullOrEmpty(studentPresentCountList))
                         {
                         	for(Object[] stuPresentObj : studentPresentCountList)
                         	{
                         		long studentId = 0;
                         		String monthNamesp = null;
                         		int studentPresentCount = 0;
                         		if(!ObjectFunctions.isNullOrEmpty(stuPresentObj))
                         		{
                         			if(!ObjectFunctions.isNullOrEmpty(stuPresentObj[0]))
                             		{
                         				studentId = Long.valueOf(stuPresentObj[0].toString());
                             		}
                         			if(!ObjectFunctions.isNullOrEmpty(stuPresentObj[7]))
                             		{
                         				monthNamesp = stuPresentObj[7].toString();
                             		}
                         			if(!ObjectFunctions.isNullOrEmpty(stuPresentObj[5]))
                             		{
                         				studentPresentCount = Integer.parseInt(stuPresentObj[5].toString());
                             		}
                         			
                         			if(studentId > 0 && !StringFunctions.isNullOrEmpty(monthNamesp))
                         				studentDailyAttendancePresntMap.put(studentId+""+monthNamesp.toUpperCase(), studentPresentCount);
                         		}
                         	}
                         }
			    	}
					
				 	HashMap<Long, String> studentAssessmentNameMap = new HashMap<Long, String>();
		    		List<StudentsAssessments> studentsAssessmentsList = studentManager.getAll(StudentsAssessments.class,"custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId());
					if (ObjectFunctions.isNotNullOrEmpty(studentsAssessmentsList)) {
						for (StudentsAssessments studentAssObj : studentsAssessmentsList) {
							studentAssessmentNameMap.put(studentAssObj.getId(),studentAssObj.getAssessmentName());
						}
					}
					// Map<Integer,String> yearWiseMothNames = ajaxGetAllMonthNamesMonthIdByStartAndEndDate(academicYear.getStartDate(),new Date());
					
					   String readColumns =null;
		               String readRows =null;
		               String[] cols={"16","20"};
		               String[] rows={"2","4"};
		               readColumns=readSheets.get("IsImageColumns");
		               readRows=readSheets.get("IsImageRows");
		               if(!StringFunctions.isNullOrEmpty(readColumns)){
		                 cols=readColumns.split(",");
		               }
		               
		               if(!StringFunctions.isNullOrEmpty(readRows)){
			               rows=readRows.split(",");
			            }
					 
		               String readImage=readSheets.get("IsStudentImage");
		               String readScholostic=readSheets.get("IsScholosticConfig");
		               String readAttendance=readSheets.get("IsAttendanceConfig");
		               
					 for(ViewStudentPersonAccountDetails student : studentsList){
						//studentsFilePath = new StringBuffer(generatedScoreCardsFilePath).append(student.getFullName().toString().trim().replaceAll(" ", "")).append("_").append(student.getAdmissionNumber().replaceAll("/", "")).append("/");
						studentsFilePath = new StringBuffer(generatedScoreCardsFilePath);
						File studDir = new File(getSession().getServletContext().getRealPath(studentsFilePath.toString()));
						studDir.mkdirs();
						studentsFilePath = new StringBuffer(studentsFilePath).append(student.getClassAndSection()).append("_").append(student.getFullName().replaceAll(" ", ""))
						.append("_").append(student.getAccountId());
						
						if(!ObjectFunctions.isNullOrEmpty(student.getSection()))
								templates = scoreCardTemplates.get(student.getClassName().concat(" - ")+student.getSection());
						else
							templates = scoreCardTemplates.get(student.getClassName());
						if(ObjectFunctions.isNullOrEmpty(templates)){
							log.debug("Templates are not exist in this path......."+student.getClassName().concat(" - ")+student.getSection());
							adminManager.writeToFile("Score card template not found. Please upload score card template.",getSession().getServletContext().getRealPath(studentsFilePath.append(".doc").toString()));
							temp = "noTemplate";
						}else{
							for(Object template:templates){
								if(template instanceof File)
									scoreCardAcademicfile = (File)template;
								else
									report =(IXDocReport)template;	
							}
							studentIdsBuffer.append(student.getStudentId() + ",");
							if(!ObjectFunctions.isNullOrEmpty(scoreCardAcademicfile)){
								studentsFilePath.append(".xlsx");
								if(!ObjectFunctions.isNullOrEmpty(schedulesCellInfo) || !ObjectFunctions.isNullOrEmpty(activitiesCellInfo) || !ObjectFunctions.isNullOrEmpty(studentDetailsCellInfo)){
									
									  FileInputStream templateFile =null;
									  File studentFile = new File(getSession().getServletContext().getRealPath(studentsFilePath.toString()));
									 // log.debug(studentFile);
									  FileUtils.copyFile(scoreCardAcademicfile, studentFile);
									  
									  templateFile = new FileInputStream(studentFile);
									  XSSFWorkbook workbook = new XSSFWorkbook(templateFile);									 				  
									  
									  if(!StringFunctions.isNullOrEmpty(readImage)){
										  if("Y".equalsIgnoreCase(readImage)){
											  readScoreCardStudentImage(workbook,student,cols,rows);
										   }
										}
									  
									  if(!StringFunctions.isNullOrEmpty(readScholostic)){
										  if("Y".equalsIgnoreCase(readScholostic)){
											  readScoreCardScholosticSheet(workbook,student,studentAssessmentNameMap,activitiesCellInfo);
										   }
										}
									 
									  if(!StringFunctions.isNullOrEmpty(readAttendance)){
										  if("Y".equalsIgnoreCase(readAttendance)){
											  readScoreCardStudentAttendance(workbook,student,yearWiseMothNames,attendanceListObjMap,academicYear,studentDailyAttendancePresntMap);
										   }
										}
									Map<Long,String> stuAndParentIds=new HashMap<Long,String>();
									if(StringFunctions.isNotNullOrEmpty(student.getParentId())){
										stuAndParentIds.put(student.getStudentId() ,  student.getAccountId()+","+student.getParentId().toString());
									}else{
										stuAndParentIds.put(student.getStudentId() ,  String.valueOf(student.getStudentId()));
									}
									processStudentsScoreCards(workbook,student,studentDetailsCellInfo,buffreStr,studentRanking,highestMarks,schedulesCellInfo,stuAndParentIds);
									FileOutputStream outFile1 =new FileOutputStream(studentFile);
									workbook.write(outFile1);
									outFile1.close();
								}
							}
						}
						student = null;
						studentsFilePath = null;
						scoreCardAcademicfile=null;
					}
					 studentIdsBuffer.append(")");
				    }
					studentsList = null;
				}
			}else{
				studentsFilePath = new StringBuffer(generatedScoreCardsFilePath).append("readMe.doc");
				adminManager.writeToFile("Scorecard template not found. Please upload score card template.",getSession().getServletContext().getRealPath(studentsFilePath.toString()));
				temp = "noTemplate";
			}
			
			//log.debug(" generatedScoreCardsFilePath path : " + generatedScoreCardsFilePath.toString());
			if(!StringFunctions.isNullOrEmpty(generatedScoreCardsFilePath.toString()))
			{
				StringBuffer  sfilePath1 = new StringBuffer("userfiles/ccer/"+getUserCustId()+"/").append(getUserAcademicYearId()+"/scorecards/").append(getTempId1()+"/").append(classSectionId); 
				  String serverPath=getSession().getServletContext().getRealPath(sfilePath1.toString());
				  log.debug("server file path..."+serverPath);
				 
				directory = new File(getSession().getServletContext().getRealPath(generatedScoreCardsFilePath.toString()));
				//For generating zip file
				StringFunctions.zipFiles(directory,zipOutStream);
				zipOutStream.close();
				FileOutputStream fileWriter = new FileOutputStream(getSession().getServletContext().getRealPath("userFiles/ScoreCardTemplates/"+customer.getCustomerShortName()+"/"+getCurrentAcademicYear().getAcademicYear()+"/"+getExamType()+"/ClassSection_"+classSectionId+".zip"));
				ZipOutputStream zipout =null;
				try{
					if(!ObjectFunctions.isNullOrEmpty(fileWriter)){
					zipout = new ZipOutputStream(fileWriter);
					StringFunctions.zipFiles(directory,zipout);
					zipout.close();
					fileWriter.close();
					}
				}
				catch(IOException e){
					e.printStackTrace();
				}
				if(!(temp=="noStudents" || temp=="noTemplate"))
					//adminManager.sendNotificationToAndroidMobileUsers(getUserCustId(),"ScoreCard Generated"); //To add notification for mobile app.
				//Sending Notification For Score card
					adminManager.sendNotificationForScoreCardByClassSectionId(classSectionId);
				ScorecardDeliveredThread scorecardDeliveredThread = new ScorecardDeliveredThread();
				scorecardDeliveredThread.setAcademicYear(getCurrentAcademicYear());
				scorecardDeliveredThread.setCustomer(customer);
				scorecardDeliveredThread.setClassSectionId(classSectionId);
				scorecardDeliveredThread.setServerFilePath(serverPath);
				scorecardDeliveredThread.setExamType(getExamType());
				scorecardDeliveredThread.setExamTypeId(getTempId1()); 
				List<Object[]> emailsAndMobileNumbers = adminManager.getAll("select email,id from supportTeam where (email!='' AND email is not null)");
				scorecardDeliveredThread.setSupportTeamEmail(emailsAndMobileNumbers);
				Thread importThread = new Thread(scorecardDeliveredThread);
				importThread.start();
				zipout=null;
				zipOutStream = null;
				query = null;
				customer = null;
				serverPath=null;
				sfilePath1=null;
				generatedScoreCardsFilePath = null;
				scoreCardTemplates = null;
				scoreCardAcademicfile = null;
				activitiesCellInfo =  null;
				schedulesCellInfo = null;
      }
		} catch (Exception e) {
			e.printStackTrace();
		}
  
    }
  /********************************************************************
   * Date              Name               Description
   * ============      =======		    ==================
   * May 27, 2013      Seshu		    It readins score card template.
  /********************************************************************/
  public HashMap<Long, String> readScoreCardAcademicScheet(File file,long classSectionId, HashMap<String, String> readSheets,StringBuffer examTypes){
	  HashMap<Long, String> schedulesCellInfo = new HashMap<Long, String>();
	  StringBuffer scheduleInfo = null;
	    if (log.isDebugEnabled()) {
			log.debug("Entering 'readScoreCardAcademicScheet first' method");
		}
	  try{
		  FileInputStream templateFile= new FileInputStream(file);
           
		  XSSFWorkbook workbook = new XSSFWorkbook(templateFile);
		  XSSFSheet sheet = workbook.getSheet("AcademicConfig");
		  String examTypeName1=null;
		  if(!ObjectFunctions.isNullOrEmpty(sheet)){
		  //Cell cell =  sheet.getRow(0).getCell(1);
		  Cell cell =  null;
		  //String className = null;
		  String examTypeName = null;
		  String subTypeName = null;
		  String scheduleCellInfo = null;
		  int startRow = 0;
		  int endRow = 0;
		  int startColumn = 0;
		  int endColumn = 0;
		  int examTypeNamesRow = 0;
		  int subTypeNamesRow = 0;
		  cell =sheet.getRow(0).getCell(3);
		  if(!ObjectFunctions.isNullOrEmpty(cell))
		   	startRow =  (int)cell.getNumericCellValue();
		  cell =sheet.getRow(0).getCell(5);
		  if(!ObjectFunctions.isNullOrEmpty(cell))
		   	endRow =  (int)cell.getNumericCellValue();
		  cell =sheet.getRow(0).getCell(7);
		  if(!ObjectFunctions.isNullOrEmpty(cell))
			  startColumn = (int)cell.getNumericCellValue();
		  cell =sheet.getRow(0).getCell(9);
		  if(!ObjectFunctions.isNullOrEmpty(cell))
			  endColumn= (int)cell.getNumericCellValue();
		  cell =sheet.getRow(0).getCell(11);
		  if(!ObjectFunctions.isNullOrEmpty(cell))
		   	examTypeNamesRow =  (int)cell.getNumericCellValue();
		  cell =sheet.getRow(0).getCell(13);
		  if(!ObjectFunctions.isNullOrEmpty(cell))
		   	subTypeNamesRow =  (int)cell.getNumericCellValue();
		  
		  
		  Cell subTypeCell=null;
		  Cell isConfigSheet=null;
		  Cell isConfigFlag=null;
		 
		  
		  if(startRow > 0 && endRow > 0 && examTypeNamesRow > 0 && subTypeNamesRow > 0 && startColumn > 0
				  && endColumn > 0){
			  
			  HashMap<String, String> subjectTopperMarks = new HashMap<String, String>();
		      HashMap<String, String> classTopperMarks = new HashMap<String, String>();
		    	      
		      StringBuffer query = new StringBuffer();
		      Row subTypeRow = sheet.getRow(subTypeNamesRow);
	    	  Row examTypesRow = sheet.getRow(examTypeNamesRow);
	    	  Row examSchedulesRow = null;
	    	  String subjectName = null;
	    	  query=new StringBuffer();
	    	  String[] markValue=null;
	    	 
	    	  Double obtained=null;
	    	  Double moderated=null;
	    	  new HashMap<String, String>();
	    	  int j=0;
	    	  
	          if(!ObjectFunctions.isNullOrEmpty(getExamType())){
		    		sheet.getRow(0).createCell(15).setCellValue(getExamType());
		    	}
			  for(int i=0; i<endColumn;i++)
			  {
				 if(!ObjectFunctions.isNullOrEmpty(sheet.getRow(2)))
				  cell =sheet.getRow(2).getCell(i);
				  subTypeCell=sheet.getRow(3).getCell(i);
				  isConfigSheet=sheet.getRow(1).getCell(j);
				  isConfigFlag=sheet.getRow(1).getCell(j+1);
				  if(!ObjectFunctions.isNullOrEmpty(isConfigSheet) && !ObjectFunctions.isNullOrEmpty(isConfigFlag)){
					  if(!ObjectFunctions.isNullOrEmpty(isConfigSheet.getStringCellValue()) && !ObjectFunctions.isNullOrEmpty(isConfigFlag.getStringCellValue())){
						  readSheets.put(isConfigSheet.getStringCellValue(),isConfigFlag.getStringCellValue());
					  }
				  }
				  
				   if(!ObjectFunctions.isNullOrEmpty(cell) && !ObjectFunctions.isNullOrEmpty(subTypeCell)){ 
					   examTypeName1=cell.getStringCellValue();
					   if(!StringFunctions.isNullOrEmpty(examTypeName1))
					    {
						    examTypes.append("examType='"+cell.getStringCellValue().trim()+"' OR ");
						    log.debug("----Exam Type Name---"+cell.getStringCellValue().trim());
						    query = new StringBuffer("select CONCAT(subjectName,'_',examType) as subjExamType,subTypeName,scheduleId,maxMarks,Max(obtainedMarks+moderationMarks) as subjectHighestMarks,examType,subjectName,present FROM vw_studentExamMarks WHERE ");
							query.append(" custId="+getUserCustId());
							query.append( " and classSectionId="+classSectionId);
							query.append(" and examType='"+examTypeName1.trim()+"'");
							query.append(" and subTypeName='"+subTypeCell.getStringCellValue().trim()+"'");
						    query.append(" and academicYearId=").append(getUserAcademicYearId()).append(" group by subjectName,subTypeName,examType");
						    List<Object[]> subjectHighestMarks = adminManager.getAll(query.toString());
						    if(!ObjectFunctions.isNullOrEmpty(subjectHighestMarks)){
						    	for(Object[] subjectHighest : subjectHighestMarks){
						    		if(!ObjectFunctions.isNullOrEmpty(subjectHighest)){
						    		  obtained = new Double(subjectHighest[3].toString());
		    						  moderated =new Double(subjectHighest[4].toString());
						    		  subjectTopperMarks.put(subjectHighest[0].toString().trim().toLowerCase()+"_"+subjectHighest[1].toString().trim().toLowerCase(), String.valueOf(obtained)+"_"+String.valueOf(moderated)+"_"+subjectHighest[2].toString());
						    		}
						    	}
						    }
						    
						    if(!ObjectFunctions.isNullOrEmpty(subTypeRow) && !ObjectFunctions.isNullOrEmpty(examTypesRow)){
				    			for(int currentRow = startRow;currentRow <= endRow; currentRow++){
				    				examSchedulesRow = sheet.getRow(currentRow);
				    				if(!ObjectFunctions.isNullOrEmpty(examSchedulesRow)){
				    				if(!ObjectFunctions.isNullOrEmpty(examSchedulesRow.getCell(startColumn -1)))
				    				subjectName = examSchedulesRow.getCell(startColumn -1).getStringCellValue();
					    			if(StringFunctions.isNotNullOrEmpty(subjectName)){
					    				for(int currentColumn = startColumn;currentColumn <= endColumn; currentColumn+=5)
					    				{
					    					cell = examTypesRow.getCell(currentColumn);
					    					if(ObjectFunctions.isNullOrEmpty(cell))
					    						examTypeName = null;
					    					else
					    						examTypeName = cell.getStringCellValue();
					    					cell = subTypeRow.getCell(currentColumn);
					    					if(ObjectFunctions.isNullOrEmpty(cell))
					    						subTypeName = null;
					    					else    							
					    						subTypeName = cell.getStringCellValue();
					    					if(StringFunctions.isNotNullOrEmpty(examTypeName) && StringFunctions.isNotNullOrEmpty(subTypeName)){
					    						scheduleInfo = new StringBuffer(subjectName.trim().toLowerCase()).append("_").append(examTypeName.trim().toLowerCase()).append("_").append(subTypeName.trim().toLowerCase());
					    						scheduleCellInfo = subjectTopperMarks.get(scheduleInfo.toString());
					    						if(!StringFunctions.isNullOrEmpty(scheduleCellInfo)){
					    							query = new StringBuffer().append(examSchedulesRow.getRowNum()).append(":").append(currentColumn).append("_").append(examSchedulesRow.getRowNum())
					    							.append(":").append(currentColumn+3);
					    							 markValue=scheduleCellInfo.split("_");
						    					   
					    							obtained = new Double(markValue[0].toString()); // total Marks
						    						moderated =new Double(markValue[1].toString());  // classTopper Marks
					    							 
					    							examSchedulesRow.createCell(currentColumn+1).setCellValue(obtained);
					    							
					    							examSchedulesRow.createCell(currentColumn+2).setCellValue(moderated);

					    							schedulesCellInfo.put(Long.valueOf(markValue[2].toString()), query.toString());
					    							//log.debug("This is Subject Name : "+markValue[2].toString());
					    						}
					    						scheduleCellInfo = null;
					    						scheduleInfo = null;
					    					}
					    				}
					    				subjectName = null;
					    			}
				    			  }
				    			}
				    		}
						    subjectHighestMarks=null;
						    query=null;
						    
						   String classTopMarks = readSheets.get("IsClassTopperMarks");
						    if("Y".equalsIgnoreCase(classTopMarks)){
						    	if (log.isDebugEnabled()) {
									log.debug("Entering 'IsClassTopperMarks' Condition");
								}
						    query = new StringBuffer("select CONCAT(subjectName,'_',examType) as subjExamType,subTypeName,scheduleId,maxMarks,obtainedMarks,moderationMarks,examType,subjectName,present FROM vw_studentExamMarks WHERE ");
							query.append(" rankPosition=1 and custId="+getUserCustId());
							query.append( " and classSectionId="+classSectionId);
							query.append(" and examType='"+examTypeName1+"'");
						    query.append(" and academicYearId=").append(getUserAcademicYearId()).append(" group by subjectName,subTypeName,examType");
						    
						    double totalMarks=0D;
						    List<Object[]> firstRankerMarks = adminManager.getAll(query.toString());
						    if(!ObjectFunctions.isNullOrEmpty(firstRankerMarks)){
							    	for(Object[] firstHighest : firstRankerMarks){
							    		if(!ObjectFunctions.isNullOrEmpty(firstHighest)){
							    			 obtained = new Double(firstHighest[4].toString());
							    			 moderated = new Double(firstHighest[5].toString());
							    			 totalMarks = obtained+moderated;
							    		     log.debug("Value = " + totalMarks);
							    			 classTopperMarks.put(firstHighest[0].toString().trim().toLowerCase()+"_"+firstHighest[1].toString().trim().toLowerCase(),String.valueOf(totalMarks));
							    		}
							    	}
						    }
						    
						    if(!ObjectFunctions.isNullOrEmpty(subTypeRow) && !ObjectFunctions.isNullOrEmpty(examTypesRow) && !ObjectFunctions.isNullOrEmpty(examSchedulesRow)){
				    			for(int currentRow = startRow;currentRow <= endRow; currentRow++){
				    				examSchedulesRow = sheet.getRow(currentRow);
				    				if(!ObjectFunctions.isNullOrEmpty(examSchedulesRow)){
			    					if(!ObjectFunctions.isNullOrEmpty(examSchedulesRow.getCell(startColumn -1)))
			    					{
					    			    subjectName = examSchedulesRow.getCell(startColumn -1).getStringCellValue();
						    			if(StringFunctions.isNotNullOrEmpty(subjectName)){
						    				for(int currentColumn = startColumn;currentColumn <= endColumn; currentColumn+=5)
						    				{
						    					cell = examTypesRow.getCell(currentColumn);
						    					if(ObjectFunctions.isNullOrEmpty(cell))
						    						examTypeName = null;
						    					else
						    						examTypeName = cell.getStringCellValue();
						    					cell = subTypeRow.getCell(currentColumn);
						    					if(ObjectFunctions.isNullOrEmpty(cell))
						    						subTypeName = null;
						    					else    							
						    						subTypeName = cell.getStringCellValue();
						    					if(StringFunctions.isNotNullOrEmpty(examTypeName) && StringFunctions.isNotNullOrEmpty(subTypeName)){
						    						scheduleInfo = new StringBuffer(subjectName.trim().toLowerCase()).append("_").append(examTypeName.trim().toLowerCase()).append("_").append(subTypeName.trim().toLowerCase());
						    						scheduleCellInfo = classTopperMarks.get(scheduleInfo.toString());
						    						if(!StringFunctions.isNullOrEmpty(scheduleCellInfo)){
						    							//highestMark=Integer.parseInt(scheduleCellInfo);
						    							 obtained = new Double(scheduleCellInfo.toString());
						    							if(!StringFunctions.isNullOrEmpty(scheduleCellInfo))
						    								examSchedulesRow.createCell(currentColumn+4).setCellValue(obtained);
						    							else
						    								examSchedulesRow.createCell(currentColumn+4).setCellValue(0);
						    						}
						    						scheduleCellInfo = null;
						    						scheduleInfo = null;
						    					}
						    				}
						    				subjectName = null;
						    			}
				    				}
				    			  }
				    			}
				    			 firstRankerMarks=null;
				    		  }
						    }
						    
					     query=null;
					  }
				    }
				   j=j+2;
			     }
	    		subTypeRow = null;
	    		examTypesRow = null;
	    		examSchedulesRow = null;
	    		query=null;
		    }
		  }
		  
		  if(StringFunctions.isNullOrEmpty(examTypeName1))
			 examTypes=null;
			  
		  templateFile.close();
		  FileOutputStream outFile =new FileOutputStream(file);
		  workbook.write(outFile);
		  outFile.close();
		  
		  workbook = null;
		 
	  }catch (Exception ex) {
		  ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		  JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
	  }
	  
	  return schedulesCellInfo;
  }

public HashMap<String, String> readScoreCardScholasticConfigSheet(File file){
	  HashMap<String, String> activitiesCellInfo = null;
	  try{
		  if (log.isDebugEnabled()) {
				log.debug("Entering 'readScoreCardScholasticConfigSheet' methos");
			}
		  FileInputStream templateFile= new FileInputStream(file);
		  XSSFWorkbook workbook = new XSSFWorkbook(templateFile);
		  XSSFSheet sheet = workbook.getSheet("ScholasticConfig");
		  if(!ObjectFunctions.isNullOrEmpty(sheet)){
			  activitiesCellInfo = new HashMap<String, String>();
			  String activityName = null;
			  String activityTypeName = null;
			  Row activityTypesRow = null;
			  String examTypeName = null;
			  String studentAssessmentName = null;
			  String activityDesc = null;
			  StringBuffer scheduleKey = null;
			  int startRow = 0;
			  int endRow =0;
			  int startColumn = 0;
			  int endColumn = 0;
			  int examTypeNamesRow = 0;
			  int activityInfoRow = 0;
			  int activityNameRow = 0;
			  int activityTypeNameRow=0;
			  int studentAssessmentNameRow=0;
			  
			  Cell cell =  sheet.getRow(0).getCell(1);
			  if(!ObjectFunctions.isNullOrEmpty(cell))
			   	startRow =  (int)cell.getNumericCellValue();
			  cell =  sheet.getRow(0).getCell(3);
			  if(!ObjectFunctions.isNullOrEmpty(cell))
			   	endRow =  (int)cell.getNumericCellValue();
			  cell =  sheet.getRow(0).getCell(5);
			  if(!ObjectFunctions.isNullOrEmpty(cell))
			  	startColumn =  (int)cell.getNumericCellValue();
			  cell =  sheet.getRow(0).getCell(7);
			  if(!ObjectFunctions.isNullOrEmpty(cell))
			   	endColumn =  (int)cell.getNumericCellValue();
			  cell =  sheet.getRow(0).getCell(9);
			  if(!ObjectFunctions.isNullOrEmpty(cell))
			   	examTypeNamesRow =  (int)cell.getNumericCellValue();
			  cell =  sheet.getRow(0).getCell(11);
			  if(!ObjectFunctions.isNullOrEmpty(cell))
				  activityInfoRow =  (int)cell.getNumericCellValue();
			  cell =  sheet.getRow(0).getCell(13);
			  if(!ObjectFunctions.isNullOrEmpty(cell))
				  activityNameRow =  (int)cell.getNumericCellValue();
			  cell =  sheet.getRow(0).getCell(15);
			  if(!ObjectFunctions.isNullOrEmpty(cell))
				  activityTypeNameRow =  (int)cell.getNumericCellValue();
			  cell =  sheet.getRow(0).getCell(17);
			  if(!ObjectFunctions.isNullOrEmpty(cell))
				  studentAssessmentNameRow =  (int)cell.getNumericCellValue();
			  else
				  studentAssessmentNameRow=0;
			  StringBuffer sql = new StringBuffer("custId=").append(getUserCustId()).append(" and academicYearId=").append(getUserAcademicYearId());
			  List stuAssessmentList=adminManager.getAll(StudentsAssessments.class, sql.toString());
			  sql = null;
			  if(startRow > 0 && endRow > 0 && examTypeNamesRow > 0 && activityInfoRow > 0){
			    	Row activityRow = sheet.getRow(activityInfoRow);
		    		Row examTypesRow = sheet.getRow(examTypeNamesRow);
		    		Row studentAssessmentsRow = sheet.getRow(studentAssessmentNameRow);
			    	for( int currentRow = startRow;currentRow <= endRow; currentRow++){
			    		activityTypesRow = sheet.getRow(currentRow);
			    		if(!ObjectFunctions.isNullOrEmpty(activityTypesRow)){
			    			if(!ObjectFunctions.isNullOrEmpty(activityTypesRow.getCell(activityNameRow)))
			    			activityName = activityTypesRow.getCell(activityNameRow).getStringCellValue();
			    			if(!ObjectFunctions.isNullOrEmpty(activityTypesRow.getCell(activityTypeNameRow)))
				    		activityTypeName = activityTypesRow.getCell(activityTypeNameRow).getStringCellValue();
				    		if(StringFunctions.isNotNullOrEmpty(activityName) && StringFunctions.isNotNullOrEmpty(activityTypeName)){
				    			for(int column = startColumn;column <= endColumn; column++){
				    				cell = examTypesRow.getCell(column);
			    					if(ObjectFunctions.isNullOrEmpty(cell))
			    						examTypeName = null;
			    					else
			    						examTypeName = cell.getStringCellValue();
			    					if(studentAssessmentNameRow > 0){
			    						if(stuAssessmentList.size()>0){
				    						cell = studentAssessmentsRow.getCell(column);
					    					if(ObjectFunctions.isNullOrEmpty(cell))
					    						studentAssessmentName = null;
					    					else
					    						studentAssessmentName = cell.getStringCellValue();
				    					}
			    					}
			    					cell = activityRow.getCell(column);
			    					if(ObjectFunctions.isNullOrEmpty(cell))
			    						activityDesc = null;
			    					else    							
			    						activityDesc = cell.getStringCellValue();
			    					if(StringFunctions.isNotNullOrEmpty(examTypeName) && StringFunctions.isNotNullOrEmpty(activityDesc)){
			    						if(StringFunctions.isNotNullOrEmpty(studentAssessmentName)){
			    							scheduleKey = new StringBuffer(activityName.toLowerCase().trim()).append("_").append(activityTypeName.toLowerCase().trim()).append("_").append(examTypeName.trim()).append("_").append(studentAssessmentName.trim()).append("_").append(activityDesc.trim());
			    						}else{
			    							scheduleKey = new StringBuffer(activityName.toLowerCase().trim()).append("_").append(activityTypeName.toLowerCase().trim()).append("_").append(examTypeName.trim()).append("_").append(activityDesc.trim());
			    						}
			    						activitiesCellInfo.put(scheduleKey.toString().toLowerCase(), activityTypesRow.getRowNum()+":"+column);
			    						scheduleKey = null;
			    					}
			    					cell = null;
				    			}
				    		}
			    		}
			    		activityTypesRow = null;
			    	}
			    	activityRow = null;
			    	examTypesRow = null;
			    }
			    stuAssessmentList=null;
			    activityName = null;
		  }
		  templateFile.close();
		  FileOutputStream outFile =new FileOutputStream(file);
		  workbook.write(outFile);
		  outFile.close();
		  templateFile = null;
	  }catch (Exception ex) {
		  ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		  JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
	}
	  return activitiesCellInfo;
  } 

private static final int IMG_WIDTH = 200;
private static final int IMG_HEIGHT = 150;
//http://www.mkyong.com/java/how-to-resize-an-image-in-java/
//http://www.journaldev.com/615/java-image-resize-program-using-graphics2d-example
private static BufferedImage resizeImage(BufferedImage originalImage, int type){
	BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
	Graphics2D g = resizedImage.createGraphics();
	g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
	g.dispose();

	return resizedImage;
   }

private static BufferedImage resizeImageWithHint(BufferedImage originalImage, int type)
{
	 
	BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
	Graphics2D g = resizedImage.createGraphics();
	g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
	g.dispose();	
	g.setComposite(AlphaComposite.Src);
 
	g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	g.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
	g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,	RenderingHints.VALUE_ANTIALIAS_ON);
	
 
	return resizedImage;
    }	

  public void processStudentsScoreCards(XSSFWorkbook workbook,ViewStudentPersonAccountDetails student,
		  HashMap<String, String> studentDetailsCellInfo,String examTypes,HashMap<Long, Integer> studentRanking,double highestMarks,HashMap<Long, String> schedulesCellInfo,Map<Long,String> stuAndParentIds){
	  if (log.isDebugEnabled()) {
			log.debug("Entering 'processStudentsScoreCards' method");
		}
	  try{
		  String cellInfo = null;
		  int row = 0;
		  int column =0;
		  Object fieldValue = null;
		  String[] cellPositions = null;
		  XSSFSheet sheet = null;
		  Cell cell=null;
		  
		StringBuffer query=null; 
	    query = new StringBuffer("select scheduleId,rankPosition,subjectPosition,(obtainedMarks+moderationMarks) as obtainedMarks,examType,subjectName,present,studId,examTypeId FROM vw_studentExamMarks WHERE ");
		query.append(" custId="+getUserCustId());
		query.append(" and academicYearId=").append(getUserAcademicYearId());
		query.append( " and classSectionId="+student.getClassSectionId());
	    	    
	    if(!StringFunctions.isNullOrEmpty(examTypes)){
	      query.append(" and ("+examTypes+")");
	    }
	    query.append(" and studId="+student.getStudentId());
	   // log.debug("Query:-"+query.toString());
	    List<Object[]> studentMarks = adminManager.getAll(query.toString());
		
		  if(ObjectFunctions.isNotNullOrEmpty(studentMarks)){
			  if(!ObjectFunctions.isNullOrEmpty(schedulesCellInfo)){
				  sheet = workbook.getSheet("AcademicConfig");
				  int k=0;
				  StringBuffer stuAndParentId=null;
				  boolean isTrue=true;
				  for(Object[] marks : studentMarks){
					  stuAndParentId= new StringBuffer();
					  if(stuAndParentIds.containsKey(Long.valueOf(marks[7].toString())) && isTrue){
						  isTrue=false;
						  stuAndParentId.append(stuAndParentIds.get(Long.valueOf(marks[7].toString())));
						  stuAndParentId.append(",0");
						//  log.debug("getEmpId().toString():-"+getEmpId().toString());
						  //adminManager.scoreCardGeneratonNotification(stuAndParentId.toString(),student.getClassSectionId(),student.getPersonFirstLastNameOnly(),Long.valueOf(marks[8].toString()),marks[4].toString(),getUserAcademicYearId(),getUserCustId(),student.getAccountId());
						  //Sending mobile Notification for Score card generation
						  //Commented by Siva for not calling for some reasons
						  //adminManager.sendNotificationForScoreCard(student.getStudentId(), student.getParentId(),student.getClassSectionId(),student.getPersonFirstLastNameOnly(),Long.valueOf(marks[8].toString()),marks[4].toString(),getUserAcademicYearId(),getUserCustId(),student.getAccountId());
						  
					  }
					  cellInfo = schedulesCellInfo.get(Long.valueOf(marks[0].toString()));
		    			if(StringFunctions.isNotNullOrEmpty(cellInfo)){
		    				cellPositions=cellInfo.split("_");
		    				if(k==0){
		    					sheet.getRow(0).createCell(16).setCellValue("studentRank");
		    					if(!ObjectFunctions.isNullOrEmpty(studentRanking)){
		    					  sheet.getRow(0).createCell(17).setCellValue(studentRanking.get(student.getStudentId()));
		    					 // log.debug("-studentId---"+student.getStudId()+"---Ranking----"+studentRanking.get(student.getStudId()));
		    					}
		    					sheet.getRow(0).createCell(18).setCellValue("classHighestMarks");
		    					sheet.getRow(0).createCell(19).setCellValue(highestMarks);
		    				}
		    				if(!ObjectFunctions.isNullOrEmpty(cellPositions) && cellPositions.length == 2){
			    				row = Integer.valueOf(cellPositions[0].split(":")[0]);
			    				column = Integer.valueOf(cellPositions[0].split(":")[1]);
			    				if("Y".equalsIgnoreCase(marks[6].toString()))
			    					sheet.getRow(row).createCell(column).setCellValue(new Double(marks[3].toString()));
			    				else 
			    					sheet.getRow(row).createCell(column).setCellValue("A");
			    				
			    				row = Integer.valueOf(cellPositions[1].split(":")[0]);
			    				column = Integer.valueOf(cellPositions[1].split(":")[1]);
			    				sheet.getRow(row).createCell(column).setCellValue(Integer.parseInt(marks[2].toString()));
		    				}
		    			 k++;
		    			}
		    			marks = null;
			    	}
		    	}
			  studentMarks=null;
		   }
		  if(!ObjectFunctions.isNullOrEmpty(studentDetailsCellInfo)){
			  sheet = workbook.getSheet("ProfileConfig");
			  log.debug("Entering 'ProfileConfigRead' Condition");
			  if(!ObjectFunctions.isNullOrEmpty(sheet)){
				  for (Map.Entry<String, String> entry : studentDetailsCellInfo.entrySet()) {
					  try {
					      fieldValue = student.getClass().getDeclaredMethod(entry.getKey(), null).invoke(student, null);
				    	  cellInfo = entry.getValue();
				    		 if(StringFunctions.isNotNullOrEmpty(cellInfo)){
				    		  row = Integer.valueOf(cellInfo.split(":")[0]);
				    		  column = Integer.valueOf(cellInfo.split(":")[1]);
				    		  cell = sheet.getRow(row).createCell(column);
							  if (ObjectFunctions.isNullOrEmpty(fieldValue)) {
								cell.setCellValue("");
							  } else if (fieldValue instanceof String) {
								cell.setCellValue(fieldValue.toString());
							  } else if (fieldValue instanceof Date || fieldValue instanceof Timestamp) {
								cell.setCellValue((Timestamp) fieldValue);
							  } else if (fieldValue instanceof Double) {
								cell.setCellValue((Double) fieldValue);
							  } else if( fieldValue instanceof Integer ){
								  cell.setCellValue((Integer)fieldValue);
							  } else if(fieldValue instanceof Long){
								  cell.setCellValue((Long)fieldValue);
							  }	else{
								cell.setCellValue(fieldValue.toString());
							  }
				    	  }
					  } catch (NoSuchMethodException x) {
						    x.printStackTrace();
					  } catch (IllegalAccessException x) {
						    x.printStackTrace();
					  } catch (InvocationTargetException x) {
						    x.printStackTrace();
					  }
				  }
			  }
		  }
		//workbook.getCTWorkbook().getSheets().getSheetArray(sheetIndex).setName(sheetname);  
  		//workbook.getCreationHelper().createFormulaEvaluator().evaluateAll();
  		if(workbook.getSheetIndex("ScholasticConfig") != -1)
  			workbook.setSheetHidden(workbook.getSheetIndex("ScholasticConfig"), 2);
  		if(workbook.getSheetIndex("AcademicConfig") != -1)
  			workbook.setSheetHidden(workbook.getSheetIndex("AcademicConfig"), 2);
  		if(workbook.getSheetIndex("ProfileConfig") != -1)
  			workbook.setSheetHidden(workbook.getSheetIndex("ProfileConfig"), 2);
  		if(workbook.getSheetIndex("AttendanceConfig") != -1)
  			workbook.setSheetHidden(workbook.getSheetIndex("AttendanceConfig"), 2);
  		if(workbook.getSheetIndex("ActivitiesConfig") != -1)
  			workbook.setSheetHidden(workbook.getSheetIndex("ActivitiesConfig"), 2);
  		workbook.getCreationHelper().createFormulaEvaluator().evaluateAll();
		studentMarks = null;
		workbook = null;
        query=null;
        
	  }catch (Exception ex) {
		  ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		  JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
	  }
  }
  
  public void readScoreCardStudentAttendance(XSSFWorkbook workbook,ViewStudentPersonAccountDetails student,Map<Integer,String> yearWiseMothNames,Map<String,Integer> attendanceListObjMap,AcademicYear academicYear,HashMap<String,Integer> studentDailyAttendancePresntMap) throws URTUniversalException {
	  if (log.isDebugEnabled()) {
			log.debug("Entering 'readScoreCardStudentAttendance' method");
		}
	  try{
			  XSSFSheet sheet = null;
			  sheet = workbook.getSheet("AttendanceConfig");
			  if(!ObjectFunctions.isNullOrEmpty(sheet)){
				  int startRow = 0;
				  long absentCount= 0;
				  long absentCount1= 0;
				  Cell cell=null;
				  int startColumn = 0;
				  cell =  sheet.getRow(0).getCell(1);
				  if(!ObjectFunctions.isNullOrEmpty(cell))
				   	startRow =  (int)cell.getNumericCellValue();
				  //cell =  sheet.getRow(0).getCell(3);
				  cell =  sheet.getRow(0).getCell(5);
				  if(!ObjectFunctions.isNullOrEmpty(cell))
				  startColumn =  (int)cell.getNumericCellValue();
				  cell =  sheet.getRow(0).getCell(7);
		        	if(!ObjectFunctions.isNullOrEmpty(yearWiseMothNames)){
		        		int monthId =0;
		        		String monthName=null;
		        	//for (String monthName : montNames) 
		        	//{
		        		int monthTotalDays = 0;
		 	        	long presentCount = 0;
						 String academicYearStatus=academicYear.getManageAttendanceBy();
						 int i =0;
							for (Map.Entry<Integer, String> entry : yearWiseMothNames.entrySet()) {
	                            monthName = entry.getValue();
	                            monthId = getMonthNumberFromMonthName(monthName);
	                            monthTotalDays=0;
							    if(!StringFunctions.isNullOrEmpty(monthName)){
						    	 	if(!ObjectFunctions.isNullOrEmpty(attendanceListObjMap.get(monthName.toUpperCase()))) 
		                                 monthTotalDays = attendanceListObjMap.get(monthName.toUpperCase());
		                                 
		                             sheet.getRow(startRow).createCell(startColumn).setCellValue(monthTotalDays);
		                             sheet.getRow(startRow-1).createCell(startColumn).setCellValue(monthName);
										
							    	if("D".equalsIgnoreCase(academicYearStatus)){
							    	    /*absentCount = adminManager.getCount("vw_StudentDailyAttendance", "custId=" +getUserCustId() + " and studentId="+student.getStudentId()+" and monthName='" + monthName+ "'  and present='"+ "N'");
							    	    presentCount = monthTotalDays - absentCount;*/
							    		
							    		if(!ObjectFunctions.isNullOrEmpty(studentDailyAttendancePresntMap.get(student.getStudentId()+""+monthName.toUpperCase())))
							    			presentCount = studentDailyAttendancePresntMap.get(student.getStudentId()+""+monthName.toUpperCase());
							    		else
							    			presentCount = monthTotalDays;
									}else{
										presentCount= staffManager.getStudentsMonthlyAttendanceByClassSectionIdAndMonthId(Long.valueOf(student.getClassSectionId()),monthId,getUserAcademicYearId(),getUserCustId(),student.getStudentId());
									}
							    	sheet.getRow(startRow+1).createCell(startColumn).setCellValue(presentCount);
							    }
							    startColumn++;
							    /*if(i<=5)
							    	monthTotalDays1=monthTotalDays1+monthTotalDays;
							    else
							    	monthTotalDays2=monthTotalDays2+monthTotalDays;*/
							    
							    log.debug("Months Name :"+monthName+"---total-"+monthTotalDays+"---i Value-----"+i);
							    i++;
							}
							/*if("D".equalsIgnoreCase(academicYearStatus)){
					    	    absentCount = adminManager.getCount("studentDailyAttendance", "custId=" +getUserCustId() + " and academicYearId="+getUserAcademicYearId()+" and studentId="+student.getStudentId()+ " and present='N' and (attendanceDate between '2014-04-01 00:00:00' and '2014-09-30 00:00:00')");
					    	    log.debug("Term 1 Attendance :"+monthTotalDays1+"---------"+absentCount);
					    	    sheet.getRow(9).createCell(4).setCellValue(monthTotalDays1-absentCount);
					    	    absentCount1 = adminManager.getCount("studentDailyAttendance", "custId=" +getUserCustId() + " and academicYearId="+getUserAcademicYearId()+" and studentId="+student.getStudentId()+ " and present='N' and (attendanceDate between '2014-10-01 00:00:00' and '2015-03-30 00:00:00')");
					    	    log.debug("Term 2 Attendance :"+monthTotalDays2+"------"+absentCount1);
					    	    sheet.getRow(9).createCell(6).setCellValue(monthTotalDays2-absentCount1);
							}*/
		    		  //}	
		        	}
		        	startRow = 0;
					startColumn = 0;
					sheet=null;
			  }  
			}
	  catch (Exception ex) {
		  ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		  JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
	  }
  }
  public void readScoreCardStudentImage(XSSFWorkbook workbook,ViewStudentPersonAccountDetails student,String[] cols,String[] rows) throws URTUniversalException {
	  if (log.isDebugEnabled()) {
			log.debug("Entering 'readScoreCardStudentImage' method");
		   }
			  try {
				  String newImage = null;
				  InputStream inputStream = null;
				  XSSFSheet sheet = null;
				  sheet = workbook.getSheet("Academic Performance");
				  
				  newImage = "resize_"+student.getImageName();
				  
				if(!ObjectFunctions.isNullOrEmpty(student.getImagePath()))
				{
					try {
						File oldFile = adminManager.loadImageFromURL(student.getHrefOriginalAttachmentFilePath());
						File newFile = File.createTempFile(""+Math.random(), ".jpg");
							
						  if(oldFile.exists())
						  {
							 try 
							 {
								BufferedImage originalImage = ImageIO.read(oldFile);
								int type = originalImage.getType() == 0? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
								 
								BufferedImage resizeImageJpg = resizeImage(originalImage, type);
								ImageIO.write(resizeImageJpg, "jpg", newFile);
								
								inputStream = new FileInputStream(newFile);
							 } catch (Exception e) {
								//e.printStackTrace();
								 try {
									Iterator readers = ImageIO.getImageReadersByFormatName("JPEG");
									    ImageReader reader = null;
									    while(readers.hasNext()) {
									        reader = (ImageReader)readers.next();
									        if(reader.canReadRaster()) {
									            break;
									        }
									    }
									    //Stream the image file (the original CMYK image)
									    ImageInputStream input =  ImageIO.createImageInputStream(oldFile); 
									    reader.setInput(input); 
									    //Read the image raster
									    Raster raster = reader.readRaster(0, null); 
									    //Create a new RGB image
									    BufferedImage bi = new BufferedImage(raster.getWidth(), raster.getHeight(), 
									    BufferedImage.TYPE_4BYTE_ABGR); 
									    //Fill the new image with the old raster
									    try {
											bi.getRaster().setRect(raster);
										} catch (Exception e1) {
											e1.printStackTrace();
										}
									    
									    int type = bi.getType() == 0? BufferedImage.TYPE_INT_ARGB : bi.getType();
										 //student.setImageName("resize_"+student.getImageName());
										 if(!newFile.exists()){
											FileUtils.copyFile(oldFile, newFile);
											 BufferedImage resizeImageJpg = resizeImage(bi, type);
											 ImageIO.write(resizeImageJpg, "jpg", newFile);
										 }
										inputStream = new FileInputStream(student.appendImageSchoolIdCardAttachmentFilePath(newImage));
								} catch (Exception e1) {
									e1.printStackTrace();
									inputStream = new FileInputStream(getSession().getServletContext().getRealPath(student.getImageNotFoundFile()));
								}
							  }
						 }
						 else
							inputStream = new FileInputStream(getSession().getServletContext().getRealPath(student.getImageNotFoundFile())); //new FileInputStream(student.getSchoolIdCardAttachmentFilePath());
						oldFile.deleteOnExit();
						newFile.deleteOnExit();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				else
		      		inputStream = new FileInputStream(getSession().getServletContext().getRealPath(student.getImageNotFoundFile())); //new FileInputStream(student.getSchoolIdCardAttachmentFilePath());
				
	               //Get the contents of an InputStream as a byte[].
	               byte[] bytes = IOUtils.toByteArray(inputStream);

	               log.debug("Student Name:" + student.getFullName() + "  ** Image size--------"+bytes.length);
	               //Adds a picture to the workbook
	               int pictureIdx = workbook.addPicture(bytes, org.apache.poi.ss.usermodel.Workbook.PICTURE_TYPE_PNG);
	               //close the input stream
	               inputStream.close();
	             
	               //Returns an object that handles instantiating concrete classes
	               CreationHelper helper = workbook.getCreationHelper();
	             
	               //Creates the top-level drawing patriarch.
	               Drawing drawing = sheet.createDrawingPatriarch();
	               //Create an anchor that is attached to the worksheet
	               ClientAnchor anchor = helper.createClientAnchor();
	               
	               int imageSize=bytes.length;
	               anchor.setAnchorType(2);
	               if(!ObjectFunctions.isNullOrEmpty(cols) && !ObjectFunctions.isNullOrEmpty(rows)){
	            	   anchor.setCol1(Integer.parseInt(cols[0].toString()));
	                   anchor.setRow1(Integer.parseInt(rows[0].toString()));
	                   anchor.setCol2(Integer.parseInt(cols[1].toString()));
	                   anchor.setRow2(Integer.parseInt(rows[1].toString()));
	               } 
	               //Creates a picture
	               Picture pict = drawing.createPicture(anchor, pictureIdx);
	               //Reset the image to the original size
	               //pict.resize();
	               if(imageSize >= 15037){
	            	   pict.resize(0.82);
	               }else{
	            	   pict.resize(0.75);
	               }
	             
	            sheet=null;
	            newImage=null;
	        } catch (Exception ex) {
	  		  ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	  		  JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
	  	  }
	  }
  
  public void readScoreCardScholosticSheet(XSSFWorkbook workbook,ViewStudentPersonAccountDetails student,HashMap<Long, String>studentAssessmentNameMap,HashMap<String, String> activitiesCellInfo) throws URTUniversalException {
	  if (log.isDebugEnabled()) {
			log.debug("Entering 'readScoreCardScholosticSheet' method");
		}
	  try{
				log.debug("Entering 'IsScholosticConfig' Condition");
					StringBuffer scheduleKey = null;
					StringBuffer query=null;
					XSSFSheet sheet = null;
					 int row = 0;
					 int column =0;
					 String cellInfo = null;
					
					query=new StringBuffer("custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()).append(" and classAndSectionId=").append(student.getClassSectionId()).append(" and studId="+student.getStudentId());
					List<StudentAcademicPerformance> studentAcademicPerformance = adminManager.getAll(StudentAcademicPerformance.class, query.toString());
					query=null;
					if(ObjectFunctions.isNotNullOrEmpty(studentAcademicPerformance) && !ObjectFunctions.isNullOrEmpty(activitiesCellInfo)){
			    		sheet = workbook.getSheet("ScholasticConfig");
			    		String stuAssessmentName = null;
			    		for(StudentAcademicPerformance academicPerformace : studentAcademicPerformance){
			    			if(!ObjectFunctions.isNullOrEmpty(academicPerformace.getStudentActivityType())){
			    				if(!ObjectFunctions.isNullOrEmpty(studentAssessmentNameMap))
			    				stuAssessmentName=studentAssessmentNameMap.get(academicPerformace.getStudentsAssessmentId());
			    				if(!StringFunctions.isNullOrEmpty(stuAssessmentName)){
			    					scheduleKey = new StringBuffer(academicPerformace.getStudentActivityType().getStudentActivityName().toLowerCase().trim()).append("_").append(academicPerformace.getStudentActivityType().getActivityTypeName().toLowerCase().trim()).append("_").append(academicPerformace.getExamType().getExamType().trim()).append("_").append(stuAssessmentName.trim()).append("_grade");
			    				}else if("G".equalsIgnoreCase(academicPerformace.getGradeStatus())){
			    					scheduleKey = new StringBuffer(academicPerformace.getStudentActivityType().getStudentActivityName().toLowerCase().trim()).append("_").append(academicPerformace.getStudentActivityType().getActivityTypeName().toLowerCase().trim()).append("_").append(academicPerformace.getExamType().getExamType().trim()).append("_grade");
			    				}else if("P".equalsIgnoreCase(academicPerformace.getGradeStatus())){
			    					scheduleKey = new StringBuffer(academicPerformace.getStudentActivityType().getStudentActivityName().toLowerCase().trim()).append("_").append(academicPerformace.getStudentActivityType().getActivityTypeName().toLowerCase().trim()).append("_").append(academicPerformace.getExamType().getExamType().trim()).append("_Points");
			    				}
			    				cellInfo = activitiesCellInfo.get(scheduleKey.toString().toLowerCase());
					    		if(StringFunctions.isNotNullOrEmpty(cellInfo)){
					    			row = Integer.valueOf(cellInfo.split(":")[0]);
					    			column = Integer.valueOf(cellInfo.split(":")[1]);
					    			if("G".equalsIgnoreCase(academicPerformace.getGradeStatus())){
					    				sheet.getRow(row).createCell(column++).setCellValue(academicPerformace.getGrade());
					    			}else{
					    				sheet.getRow(row).createCell(column++).setCellValue(new Double(academicPerformace.getGrade()));
					    			}
					    			sheet.getRow(row).createCell(column).setCellValue(academicPerformace.getDescription());
					    		}
					    	 
			    			}stuAssessmentName=null;
			    			academicPerformace = null;
			    			scheduleKey=null;
			    		}
			    	}
					studentAcademicPerformance = null;
					scheduleKey=null;
					sheet=null;
				   log.debug("End 'IsScholosticConfig' Condition");
		  }
	  catch (Exception ex) {
		  ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		  JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
	    }
	  }
  
	/**Added by Prasad. For providing screen for adding exam schedules syllabus. */
  @Actions( {
		@Action(value = "ajaxDoAddExamSyllabus", results = { @Result(location = "academicExamScheduleSyllabus.jsp", name = "success") }) })
	public String ajaxDoAddExamSyllabus() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDoAddExamSyllabus' method");
		}
		try {
			List<String> scheduledStudyClass = null;
			if(getUser().isSchoolTeacher()){
				HashSet<StudyClass> classSections = new HashSet<StudyClass>();
				ClassTeacher classTeacher=staffManager.getClassTeacherByAccountId(getUser().getId(), getUserAcademicYearId());
				if(!ObjectFunctions.isNullOrEmpty(classTeacher)){
					classSections.add(classTeacher.getStudyClass());
					//setStudyClassList(adminManager.getAll(StudyClass.class,"id="+classTeacher.getStudyClassId()));
				}
				if(getUser().isOnlySchoolHod()){
					if(getUser().getId() > 0 )
					{
						setStaff(adminManager.getStaffByAcountId(getUser().getId(),Constants.YES_STRING));
					}
					List studyClassesList = getHodStudyClassesList(getStaff().getId(),getUserAcademicYearId());
					if(!ObjectFunctions.isNullOrEmpty(studyClassesList))
					{
						classSections.addAll(studyClassesList);
					}
				}
				if (ObjectFunctions.isNotNullOrEmpty(classSections)) {
					setStudyClassList(ConvertUtil.convertSetToList(classSections));
					Collections.sort(getStudyClassList());
				}
				classSections = null;
			}else{
				scheduledStudyClass = adminManager.getAll("select classSectionId from examSchedules where custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and scheduled='Y' GROUP BY classSectionId");
				String StudyClassIds = null;	
				StudyClassIds = scheduledStudyClass.toString().replace("[", "").replace("]", "");
				List studyClassList = adminManager.GetAllStudyClasses(getUserCustId(), getUserAcademicYearId(),StudyClassIds);
				if (ObjectFunctions.isNotNullOrEmpty(studyClassList)) {
					setStudyClassList(studyClassList);
				}
				//setStudyClassList(adminManager.GetAllStudyClasses(getUserCustId(), getUserAcademicYearId()));
			}			
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	 }
	/**Changed by seshu on 1st May. For displaying exam schedules screen based on classSectionId and exam type id.*/
	@Actions({ @Action(value = "ajaxGetClassExamShedules", results = { @Result(location = "ajaxClassSyllabusExamSchedule.jsp", name = "success") }) })
	public String ajaxGetClassExamShedules() {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxGetClassExamShedules' method");
		}
		try {
			if(!StringFunctions.isNullOrEmpty(getExamType()) && !StringFunctions.isNullOrEmpty(getClassId()) && getUserAcademicYearId() > 0){
				loadAcademicYearStartDateAndDates(getUserAcademicYearId());
				if(!ObjectFunctions.isNullOrEmpty(getEndDate()) && !ObjectFunctions.isNullOrEmpty(getToDate())){
					if(DateFunctions.compare2Dates(getToDate(),getEndDate())){
						super.addActionError("Current academic year ended.You can't create,edit and delete exam schedules.");
					}
				}
				setExamScheduleList(adminManager.getAll(ViewClassExamDetails.class,"classId="+Long.valueOf(getClassId())+" and eid="+ Long.valueOf(getExamType())));
			 
			 if(!ObjectFunctions.isNullOrEmpty(getExamScheduleList()))
				Collections.sort(getExamScheduleList(),new ViewExamSchedulesComparator());
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	// Need to change some code. If user unselects any subtype from available examschedules we are removing only examschedules.
	//If marks are available for that schedule we need to remove marks also. 
	/**Changed by seshu on 1st May. For creating and updating exam schedules.*/
	@Actions({ @Action(value = "ajaxEditClassExamSyllabus", results = { @Result(location = "academicExamScheduleSyllabus.jsp", name = "success") }) })
	public String ajaxEditClassExamSyllabus() {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxEditClassExamSyllabus' method");
		}
		try {
			if(StringFunctions.isNotNullOrEmpty(getTempString())){
				JSONArray examSchedulesJsonArray=new JSONArray(getTempString());
    			long scheduleId = 0;
				String subjectId =null;
				String syllabus=null;
				String subTypeId = null;
				JSONObject examScheduleJson=null;
				ExamSchedules examSchedules =null;
				ExamTypes examType = null;
				List<StudyClass> studyClassList=null;
				if(!StringFunctions.isNullOrEmpty(getClassId())  && !StringFunctions.isNullOrEmpty(getExamType()) && StringFunctions.isNotNullOrEmpty(getTitle())){
					//StudyClass studyClass=(StudyClass)adminManager.get(StudyClass.class, Long.valueOf(getClassId()));
					if("true".equalsIgnoreCase(getTitle())){
						studyClassList=adminManager.getAll(StudyClass.class," classNameClassId="+getClassId().split("_")[1]);
					}
					/*else{
						StudyClass studyClass=(StudyClass)adminManager.get(StudyClass.class, Long.valueOf(getClassId().split("_")[0]));
						studyClassList.add(studyClass);
					}*/
					examType=(ExamTypes)adminManager.get(ExamTypes.class, Long.valueOf(getExamType()));
				if(!ObjectFunctions.isNullOrEmpty(examType)){
					for(int i=0;i<examSchedulesJsonArray.length();i++){
						examScheduleJson=examSchedulesJsonArray.getJSONObject(i);
						if(!ObjectFunctions.isNullOrEmpty(examScheduleJson)){
							syllabus =(String)examScheduleJson.get("syllabus");
							subTypeId=(String)examScheduleJson.get("subTypeId");
							subjectId = (String)examScheduleJson.get("subjectId");
							if(getTitle().equalsIgnoreCase("true")) {
								for(StudyClass classSection:studyClassList){
									examSchedules=(ExamSchedules)adminManager.get(ExamSchedules.class, " classSectionId="+classSection.getId()+" and classSubjectId="+subjectId+" and subTypeId="+subTypeId+" and examTypeId="+examType.getId());
									if(!ObjectFunctions.isNullOrEmpty(examSchedules)){
										examSchedules.setLastUpdatedById(getUser().getId());
											examSchedules.setSyllabus(syllabus);  
											examSchedules.setCustId(getUserCustId()); 
											examSchedules.setLastAccessDate(new Date());
											examSchedules.setLastUpdatedDate(new Date());
											adminManager.save(examSchedules);
										}
									examSchedules = null;
							} 
							}else{
							scheduleId = Long.valueOf((String)examScheduleJson.get("scheduleId"));
							if(StringFunctions.isNotNullOrEmpty(String.valueOf(scheduleId))){
								examSchedules=(ExamSchedules)adminManager.get(ExamSchedules.class, scheduleId);
								if(!ObjectFunctions.isNullOrEmpty(examSchedules)){
									examSchedules.setLastUpdatedById(getUser().getId());
									examSchedules.setSyllabus(syllabus);  
									examSchedules.setCustId(getUserCustId()); 
									examSchedules.setLastAccessDate(new Date());
									examSchedules.setLastUpdatedDate(new Date());
									adminManager.save(examSchedules);
								}
								examSchedules = null; 
							 }
							}
					     }
				       }
					examType=null;
					super.addActionMessage("Exam syllabus is added successfully.");
				}	
			}else
				super.addActionError("Exam syllabus is not added."); 
			}
			ajaxDoAddExamSchedules();
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
  public HashMap<String, String> readScoreCardStudentsProfileSheet(File file){
	  HashMap<String, String> studentsProfileCellInfo = null;
	  try{
		  FileInputStream templateFile= new FileInputStream(file);
		  XSSFWorkbook workbook = new XSSFWorkbook(templateFile);
		  XSSFSheet sheet = workbook.getSheet("ProfileConfig");
		  if(!ObjectFunctions.isNullOrEmpty(sheet)){
			  studentsProfileCellInfo = new HashMap<String, String>();
			  String studentFieldName = null;
			  Row stdentFieldsRow = null;
			  int startRow = 0;
			  int endRow =0;
			  int fieldValuesColumn = 0;
			  Cell cell =  sheet.getRow(0).getCell(1);
			  if(!ObjectFunctions.isNullOrEmpty(cell))
			   	startRow =  (int)cell.getNumericCellValue();
			  cell =  sheet.getRow(0).getCell(3);
			  if(!ObjectFunctions.isNullOrEmpty(cell))
			   	endRow =  (int)cell.getNumericCellValue();
			  cell =  sheet.getRow(0).getCell(5);
			  if(!ObjectFunctions.isNullOrEmpty(cell))
				  fieldValuesColumn =  (int)cell.getNumericCellValue();
			  if(startRow > 0 && endRow > 0 && fieldValuesColumn > 0){
			    	for( int currentRow = startRow;currentRow <= endRow; currentRow++){
			    		stdentFieldsRow = sheet.getRow(currentRow);
			    		if(!ObjectFunctions.isNullOrEmpty(stdentFieldsRow)){
			    		if(!ObjectFunctions.isNullOrEmpty(stdentFieldsRow.getCell(fieldValuesColumn -1))){
			    			studentFieldName = stdentFieldsRow.getCell(fieldValuesColumn -1).getStringCellValue();
			    		}
			    		if(StringFunctions.isNotNullOrEmpty(studentFieldName)){
    						studentsProfileCellInfo.put(studentFieldName.trim(), stdentFieldsRow.getRowNum()+":"+fieldValuesColumn);
			    		 }
			    		}
			    		stdentFieldsRow = null;
			    	}
			    }
			  studentFieldName = null;
		  }
		  templateFile.close();
		  FileOutputStream outFile =new FileOutputStream(file);
		  workbook.write(outFile);
		  outFile.close();
		  templateFile = null;
	  }catch (Exception ex) {
		  ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		  JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
	}
	  return studentsProfileCellInfo;
  } 

  /********************************************************************
    * Date              Name               Description
    * ========          ============       ==================
    * July 25, 2013     Seshu		      For downloading students marksheet template.
   /********************************************************************/	
   	@Actions({
   		@Action(value = "ajaxDownloadExamsMarkSheet", results = {})
   		})
   		public void ajaxDownloadExamsMarkSheet() throws URTUniversalException {
   		if (log.isDebugEnabled()) {
   			log.debug("Entering 'ajaxDownloadExamsMarkSheet' method");
   		}
   		try
   		{
 			if(getUserAcademicYearId() > 0 && !StringFunctions.isNullOrEmpty(getSelectedId())){
 				String fileName ;
 				StringBuffer query = null;
				Customer customer = getCustomerByCustId();
 				if(StringFunctions.isNullOrEmpty(getAnyTitle()) && StringFunctions.isNullOrEmpty(getTempString()))
 					fileName= "importStudentsMarksTemplate";
 				else
 					fileName = "importStudentsMarksTemplate_"+getAnyTitle()+"_"+getTempString();
 				getResponse().setContentType("application/vnd.ms-excel");
 				getResponse().setHeader("Content-Disposition","attachment; filename=" + fileName.replace(' ', '_')+ ".xls");
 				HSSFWorkbook wb = new HSSFWorkbook();
 				HSSFSheet sheet = null;
 				Map<String, CellStyle> styles = PrepareStudentExcel.createStyles(wb);
 				List<ViewStudentClassDetails> classStudentsList =null;
   				List<ViewExamSchedule> examScheduleDetails=null;
   				Row row = null;
   				Cell cell= null;
   				Integer subCol= null;
   				Integer col=null;
   				List<ViewStudentMarks> studentMarks = null;
   				DecimalFormat df = new DecimalFormat("#.##");
   				if(StringFunctions.isNullOrEmpty(getAnyId()))
   					setAnyId(getExamType());
   				if(StringFunctions.isNotNullOrEmpty(getAnyId())){
   					long examTypeId=Long.valueOf(getAnyId());
   	    			long classSectionId= Long.valueOf(getClassId());
   	    			if(!ObjectFunctions.isNullOrEmpty(getUser().getId()) && StringFunctions.isNotNullOrEmpty(getTempString()))
   	    			{
   	    				if(getUserCustId() == 2)
       						classStudentsList=adminManager.getAllStudentDetailsByGender(getUserCustId(),getUserAcademicYearId(),classSectionId);
       					else{
       							query = new StringBuffer("custId=").append(getUserCustId()).append(" and academicYearId=").append(getUserAcademicYearId())
       		       						.append(" and classSectionId=").append(classSectionId).append(" and description is null").append(" order by "+getPlTitle());
       							//and status='Y' -- If we put status Y condition we can not get students list when we switch to old academic year. Because some times user traying to generate old academic year score care report and updateing marks also.
       						classStudentsList=adminManager.getAll(ViewStudentClassDetails.class, query.toString());
       						if(("N".equalsIgnoreCase(customer.getAlphaNumericRollNumber()) || StringFunctions.isNullOrEmpty(customer.getAlphaNumericRollNumber())) && "rollNumber".equalsIgnoreCase(getPlTitle()))
       							Collections.sort(classStudentsList,new StudentRollNumberComparator());
       						query = null;
       					}
       					boolean isClassTeacher = false;
       					Object[] hodClassTeacher  = null;
       					//boolean isHodClassTeacher = false;
       					if(!getUser().isSchoolAdmin()){
       						isClassTeacher = adminManager.isUserAsClassTeacher(getUser().getId(),classSectionId, getUserAcademicYearId());
       						if(getUser().isOnlySchoolHod() || getUser().isAdminCoordinator()){
       							setStaff(adminManager.getStaffByAcountId(getUser().getId(), Constants.YES_STRING));
       							hodClassTeacher  = adminManager.get("select staffId,studyClassId from staffMultipleStudyClasses where staffId="+getStaff().getId()+" and studyClassId="+classSectionId);
       						}
       					}
       					if(getUser().isSchoolAdmin() || getUser().isSchoolClerk() || isClassTeacher || !ObjectFunctions.isNullOrEmpty(hodClassTeacher)){
       						query = new StringBuffer("FROM ViewExamSchedule WHERE classSectionId=").append(classSectionId)
       						.append(" and examTypeId=").append(examTypeId).append(" and academicYearId=").append(getUserAcademicYearId()).append(" and scheduleId<>0").append(" order by subjectSortingOrder,subTypeSortingOrder");
       						examScheduleDetails=adminManager.getAllHqlQuery(query.toString());
       					}else{
       						List<Long>  staffSubjectIds = adminManager.getAll("select studySubjectId from classTeacher where custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and studyClassId="+classSectionId+" and teacherId in (select id from staff where accountId="+getUser().getId()+" and status='"+Constants.YES_STRING+"')");
       						if(!ObjectFunctions.isNullOrEmpty(staffSubjectIds)){
       							query = new StringBuffer("FROM ViewExamSchedule WHERE classSectionId=").append(classSectionId)
	   							.append(" and examTypeId=").append(examTypeId)
	   							.append(" and subjectId in ").append("("+StringFunctions.convertListToCommaDelimitedString(staffSubjectIds)+")").append(" and academicYearId=").append(getUserAcademicYearId()).append(" and scheduleId<>0").append(" order by subjectSortingOrder,subTypeSortingOrder");
	   							examScheduleDetails = adminManager.getAllHqlQuery(query.toString());
       						}
       					}
       					query = null;
       					if(!ObjectFunctions.isNullOrEmpty(examScheduleDetails) && !ObjectFunctions.isNullOrEmpty(classStudentsList)){
   							Collections.sort(examScheduleDetails, new ExamSchedulesSubjectsAndSubtypesWiseComparator());
   							sheet = wb.createSheet(getAnyTitle());
   							int studentsSize = classStudentsList.size();
   							sheet.createFreezePane(4, 6);
   							createExamSchedulesHeader(sheet,styles);
   							sheet.setColumnHidden(3, true);
   							
   							
   							HashMap<String,List<ViewExamSchedule>> subjectMap=new LinkedHashMap<String,List<ViewExamSchedule>>();
   							StringBuilder subjIds = new StringBuilder();
   							HashMap<String,Integer> examSchedulesCells = createExamScheulesSubjectsHeader(wb,sheet,styles,examScheduleDetails,studentsSize,subjectMap,classSectionId,subjIds,examTypeId); 							
   							log.debug("subjIds..."+subjIds);
   							List<ViewExamSchedule> examSheduleList=null;
   							
   							if(!ObjectFunctions.isNullOrEmpty(examSchedulesCells)){
   								
   								List<ViewStudyClassSubjects> optionalSubjectList = adminManager.getAll(ViewStudyClassSubjects.class, "custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and studyClassId="+classSectionId+" and subjectType='"+Constants.YES_STRING+"' ");
   								Map<Long, ViewStudyClassSubjects> optionalSubjMap = new HashMap<Long, ViewStudyClassSubjects>();
   	   							if(!ObjectFunctions.isNullOrEmpty(optionalSubjectList)){
   	   								for(ViewStudyClassSubjects optionalClassSubjects : optionalSubjectList){
   	   									optionalSubjMap.put(optionalClassSubjects.getSubjectId(), optionalClassSubjects);
   	   								}
   	   							}
   	   							 
   								int rowNum = 6;
   								log.debug(" classStudentsList start:"+new Date());
   								for(ViewStudentClassDetails studentDetails:classStudentsList){
   									int p=4;
   									double studentTotalMarks = 0;
   									double studentSubMarks = 0;
   									if(!ObjectFunctions.isNullOrEmpty(studentDetails)){
   										row = sheet.createRow(rowNum);
   										row.setRowStyle(styles.get("string"));
   										cell=row.createCell(0);
   										cell.setCellValue(studentDetails.getRollNumber() + (StringFunctions.isNullOrEmpty(studentDetails.getRegisterNumber()) ? "" : "/ "+studentDetails.getRegisterNumber()));
   										cell.setCellStyle(styles.get("headerInfo"));
   										cell=row.createCell(1);
   										cell.setCellValue(studentDetails.getAdmissionNumber());
   										cell.setCellStyle(styles.get("headerInfo"));
   										cell=row.createCell(2);
   										cell.setCellValue(studentDetails.getPersonFullName());
   										cell.setCellStyle(styles.get("headerInfo"));
   										cell=row.createCell(3);
   										cell.setCellValue(studentDetails.getStudId());
   										cell.setCellStyle(styles.get("headerInfo"));
   										if("M".equalsIgnoreCase(getSelectedId())){
   											if(ObjectFunctions.isNotNullOrEmpty(optionalSubjectList)){
   												if(optionalSubjectList.size() >1){
   													studentMarks = adminManager.getStudentMarksByStudentIdAndExamtypeId(studentDetails.getStudId(),examTypeId,getUserCustId(),getUserAcademicYearId());
	   													if(!ObjectFunctions.isNullOrEmpty(studentMarks)){
	   														log.debug("full name :"+studentDetails.getFullName());
	   														boolean isOptionalEntry=true;
	   														for(ViewStudentMarks marks: studentMarks){
	   															col = examSchedulesCells.get(marks.getExamTypeId()+"_"+marks.getScheduleId());
	   															//Siva: Removed the examtype id from sheet
	   															//col = examSchedulesCells.get(marks.getScheduleId());
	   															if(!ObjectFunctions.isNullOrEmpty(col)){
	   																cell = row.createCell(col);
	   																if(marks.isPresent()){
	   																	cell.setCellValue(marks.getObtainedMarks());
	   																	if(Constants.YES_STRING.equalsIgnoreCase(marks.getSubjectType())){
	   																		for(ViewStudyClassSubjects studyClassSubjects : optionalSubjectList){
	   																			examSheduleList= subjectMap.get(studyClassSubjects.getSubjectName());
	   																			if(!ObjectFunctions.isNullOrEmpty(examSheduleList)){
	   																				for(ViewExamSchedule subjectSchedules : examSheduleList) {
	   																					subCol = examSchedulesCells.get(subjectSchedules.getExamTypeIdAndScheduleId());
		   					   															if(!ObjectFunctions.isNullOrEmpty(subCol)){
				   					   														if(!ObjectFunctions.isNullOrEmpty(cell)){
					   					   														if(studentDetails.getOptionalSubjectId() == 0){
					   					   															cell = row.createCell(col);
			   					   																	cell.setCellStyle(styles.get("string"));
			   					   																	cell.setCellValue(marks.getObtainedMarks());
			   					   																}else if (studyClassSubjects.getSubjectId() == marks.getSubjectId()){
				   					   																if((marks.getScheduleId()==subjectSchedules.getScheduleId()) && (studentDetails.getOptionalSubjectId() == studyClassSubjects.getSubjectId())){
				   					   																	cell = row.createCell(subCol);
			   					   																		cell.setCellStyle(styles.get("string"));
			   					   																		cell.setCellValue(marks.getObtainedMarks());
				   					   																}else if (studentDetails.getOptionalSubjectId() != studyClassSubjects.getSubjectId()){
				   					   																	cell = row.createCell(subCol);
				   					   																	cell.setCellStyle(styles.get("readOnlyString"));
				   					   																}
			   					   																}else{
			   					   																	if(subCol!=col){
			   					   																		if(studentDetails.getOptionalSubjectId() != studyClassSubjects.getSubjectId()){
				   					   																		cell = row.createCell(subCol);
				   					   																		cell.setCellStyle(styles.get("readOnlyString"));
			   					   																		}
			   					   																		
			   					   																	}
			   					   																	
			   					   																}
					   					   												  }
		   					   															}
		   					   														subjectSchedules=null;
		   					   													  }
	   																				examSheduleList=null;
	   																			}
	   																			studyClassSubjects=null;
	   					   													}
	   																		isOptionalEntry=false;
	   																	}
	   																}else
	   																     cell.setCellValue("A");
	   																
	   																cell = null;
	   															}
	   															//Siva: Commenting this because no one is going to use moderation marks
	   															/*query = new StringBuffer(marks.getExamTypeId()+"_"+marks.getScheduleId()).append("_MOD");
	   															col = examSchedulesCells.get(query.toString());
	   															if(!ObjectFunctions.isNullOrEmpty(col)){
	   																 row.createCell(Integer.valueOf(col)).setCellValue(marks.getModerationMarks());
	   															}
	   															marks=null;*/
	   														}
														if (isOptionalEntry) {
															for (ViewStudyClassSubjects studyClassSubjects : optionalSubjectList) {
																examSheduleList = subjectMap.get(studyClassSubjects.getSubjectName());
																if (!ObjectFunctions.isNullOrEmpty(examSheduleList)) {
																	for (ViewExamSchedule subjectSchedules : examSheduleList) {
																		col = examSchedulesCells.get(subjectSchedules.getExamTypeIdAndScheduleId());
																		if (!ObjectFunctions.isNullOrEmpty(col)) {
																			cell = row.createCell(col);
																			if (studentDetails.getOptionalSubjectId() == 0) {
																				cell.setCellStyle(styles.get("string"));
																			} else if (studyClassSubjects.getSubjectId() == studentDetails.getOptionalSubjectId()) {
																				cell.setCellStyle(styles.get("string"));
																			} else
																				cell.setCellStyle(styles.get("readOnlyString"));
																		}
																		subjectSchedules=null;
																	}
																	examSheduleList=null;
																}
																studyClassSubjects=null;
															}
														}
	   													studentMarks=null;
	   												}else{
	   													for(ViewStudyClassSubjects studyClassSubjects : optionalSubjectList){
	   														examSheduleList= subjectMap.get(studyClassSubjects.getSubjectName());
																if(!ObjectFunctions.isNullOrEmpty(examSheduleList)){
																	for(ViewExamSchedule subjectSchedules : examSheduleList) {
			   															col = examSchedulesCells.get(subjectSchedules.getExamTypeIdAndScheduleId());
			   															if(!ObjectFunctions.isNullOrEmpty(col)){
			   																cell = row.createCell(col);
			   																if(studentDetails.getOptionalSubjectId() == 0)
			   																	cell.setCellStyle(styles.get("string"));
			   																else if (studyClassSubjects.getSubjectId() == studentDetails.getOptionalSubjectId())
			   																	cell.setCellStyle(styles.get("string"));
			   																else
			   																	cell.setCellStyle(styles.get("readOnlyString"));
			   															}
			   													     }
																}
	   														
	   													}
	   												}
   												}else
   													withoutOptionalSubject(studentDetails,examTypeId,cell,row,examSchedulesCells,styles);
   											}else
   												withoutOptionalSubject(studentDetails,examTypeId,cell,row,examSchedulesCells,styles);
										}
   										if("MG".equalsIgnoreCase(getSelectedId())){
   											StringBuffer scheduleIds = null;
   											Object[] subjectTotalMarks = null;
   											long optionalSubjId =0;
   												List<Object[]> classsubjectIdsList = adminManager.getAll(" select classSubjectId,SUM(maxMarks) from examSchedules where classSectionId="+classSectionId+" and examTypeId="+examTypeId+" and scheduled='"+Constants.YES_STRING +"' and custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" group by classSubjectId");
   												if(!ObjectFunctions.isNullOrEmpty(classsubjectIdsList)){
   													log.debug("full name :"+studentDetails.getFullName()+" - "+studentDetails.getOptionalSubjectId());
													for (Object[] obj : classsubjectIdsList) {
														subjectTotalMarks = preparedSubjectExamShedulesIds(studentDetails,obj, examTypeId,classSectionId);
														ViewStudyClassSubjects optionalSubjects = optionalSubjMap.get(Long.valueOf(obj[0].toString()));
														if (!ObjectFunctions.isNullOrEmpty(optionalSubjects)) {
															//log.debug(optionalSubjects.getSubjectName()+" - "+optionalSubjects.getSubjectType());
															if (Constants.YES_STRING.equalsIgnoreCase(optionalSubjects.getSubjectType())) {
																optionalSubjId = optionalSubjMap.get(Long.valueOf(obj[0].toString())).getSubjectId();
																if (studentDetails.getOptionalSubjectId() == 0) {
																	studentSubMarks = withOptionalGradeWiseSubject(studentDetails,examTypeId,cell,row,styles,p,obj,classSectionId,subjectTotalMarks,examSchedulesCells);
																} else if (studentDetails.getOptionalSubjectId() == optionalSubjMap.get(Long.valueOf(obj[0].toString())).getSubjectId()) {
																	studentSubMarks = withOptionalGradeWiseSubject(studentDetails,examTypeId,cell,row,styles,p,obj,classSectionId,subjectTotalMarks,examSchedulesCells);
																} else {
																	studentSubMarks = withoutOptionalGradeWiseSubject(studentDetails,examTypeId,cell,row,styles,p,obj,classSectionId,subjectTotalMarks,examSchedulesCells);
																}
															}else
																studentSubMarks = withOptionalGradeWiseSubject(studentDetails,examTypeId,cell, row,styles, p, obj,classSectionId,subjectTotalMarks,examSchedulesCells);
														} else
															studentSubMarks = withOptionalGradeWiseSubject(studentDetails,examTypeId,cell, row,styles, p, obj,classSectionId,subjectTotalMarks,examSchedulesCells);
	
														studentTotalMarks = studentTotalMarks+ Double.valueOf(df.format(studentSubMarks));
														p = p + 3;
														optionalSubjId = 0;
														obj=null;
													}  					
   												if(studentTotalMarks >0){
												cell = row.createCell(p);
												cell.setCellValue("'"+studentTotalMarks);
												Object[] studentOverAllGrade = adminManager.get("select description,gradeName from overAllGrades where custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and minMarks <=("+studentTotalMarks+")  and   maxMarks >=("+studentTotalMarks+") ");
												if(!ObjectFunctions.isNullOrEmpty(studentOverAllGrade)){
													cell = row.createCell(p+1);
													cell.setCellValue(studentOverAllGrade[1].toString());
													}
												}
											}
   										}
   										studentDetails=null;
   									}
   									rowNum++;
   								}
   								log.debug(" classStudentsList End :"+new Date());
   							}
   							examSchedulesCells=null;
   							//generateStudentMarksSheetByStudyClassAndSubjects(classStudentsList,getAnyTitle(),examScheduleDetails,excelView,examTypeId,getTempString(),cellFormat8,cellFormat10);
   							sheet.setDefaultColumnWidth(20);
   							sheet.setColumnWidth(0,(27 * 256));
   							sheet.setColumnWidth(1,(20 * 256));
   							sheet.setColumnWidth(3,(30 * 256));
   						}
   	    			}
   				}
   				if(wb.getNumberOfSheets() <= 0){
   					if(StringFunctions.isNotNullOrEmpty(getAnyTitle()))
   						sheet = wb.createSheet(getAnyTitle());
   					else
   						sheet = wb.createSheet("Class");
   					sheet.addMergedRegion(CellRangeAddress.valueOf("$A$11:$I$11"));
   			    	if(!ObjectFunctions.isNullOrEmpty(classStudentsList)){
   			    		sheet.createRow(10).createCell(0).setCellValue("No Exam Schedules found for this class.");
   			    	}else{
   			    		sheet.createRow(10).createCell(0).setCellValue("No students available for this class.");
   			    	}
   				}
   				wb.getSheetAt(wb.getActiveSheetIndex()).protectSheet("");
   				wb.getCreationHelper().createFormulaEvaluator().evaluateAll();
   				wb.write(getResponse().getOutputStream());
 			}

   			
   		}
   		catch(Exception ex)
   		{
   			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
   		}
   	}

   	public Object[] preparedSubjectExamShedulesIds(ViewStudentClassDetails studentDetails, Object[] obj,long examTypeId, long classSectionId) {
		try {
			StringBuffer scheduleIds = null;
			Object[] subjectTotalMarks = null;
			//List<ViewExamSchedule> scheduleIdList = adminManager.getAll(ViewExamSchedule.class,"custId=" + getUserCustId() + " and academicYearId="+ getUserAcademicYearId() + " and subjectId ="+ Long.valueOf(obj[0].toString())+ " and scheduleId='" + Constants.YES_STRING+ "' and examTypeId=" + examTypeId+ " and  classSectionId=" + classSectionId + " ");
			//List<ExamSchedules> scheduleIdList = adminManager.getAll(ExamSchedules.class,"custId=" + getUserCustId() + " and academicYearId="+ getUserAcademicYearId() + " and classSubjectId ="+ Long.valueOf(obj[0].toString())+ " and scheduled='" + Constants.YES_STRING+ "' and examTypeId=" + examTypeId+ " and  classSectionId=" + classSectionId + " ");
			List<Object[]> schedules =adminManager.getAll("select scheduleId,examTypeId from vw_examSchedule where custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and subjectId ="+ Long.valueOf(obj[0].toString())+ " and scheduled='" + Constants.YES_STRING+ "' and examTypeId=" + examTypeId+ " and  classSectionId=" + classSectionId + " ");
			if (!ObjectFunctions.isNullOrEmpty(schedules)) {
				scheduleIds = new StringBuffer();
				for (Object[] obj1 : schedules) {
					//log.debug(obj1.getSubjectName()+" - "+obj1.getSubjectType());
					scheduleIds.append(obj1[0]);
					scheduleIds.append(",");
				}
				scheduleIds.append("0");
				schedules=null;
				if (!ObjectFunctions.isNullOrEmpty(scheduleIds)) {
					subjectTotalMarks = adminManager.get("select SUM(obtainedMarks),SUM(moderationMarks),present,examScheduleId from studentMarks  where studId="+ studentDetails.getStudId()+ " and examScheduleId in("+ scheduleIds+ ") ");
					return subjectTotalMarks;
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
		}
		return null;
	}
   	

	public double withOptionalGradeWiseSubject(ViewStudentClassDetails studentDetails, long examTypeId, Cell cell,Row row, Map<String, CellStyle> styles, int p, Object[] obj,long classSectionId, Object[] subjectTotalMarks,HashMap<String,Integer> examSchedulesCells) {
		try {
			double studentSubMarks = 0;
			DecimalFormat df = new DecimalFormat("#.##");
			if(!ObjectFunctions.isNullOrEmpty(examSchedulesCells) && !ObjectFunctions.isNullOrEmpty(obj[0].toString()))
			p=examSchedulesCells.get(obj[0].toString());
			log.debug("p ="+p);
			if (!ObjectFunctions.isNullOrEmpty(subjectTotalMarks)) {
				if (!ObjectFunctions.isNullOrEmpty(subjectTotalMarks[0]) && !ObjectFunctions.isNullOrEmpty(obj[1]))
					studentSubMarks = ((Double.valueOf(subjectTotalMarks[0].toString()) / Double.valueOf(obj[1].toString())) * 100) * 50 / 100;
				if (!ObjectFunctions.isNullOrEmpty(subjectTotalMarks[2]) && !ObjectFunctions.isNullOrEmpty(subjectTotalMarks[0])) {
					cell = row.createCell(p);
					cell.setCellStyle(styles.get("string"));
					cell.setCellValue(""+ Double.valueOf(df.format(studentSubMarks)));
					Object[] gradeAndGradePoint = adminManager.get("select gradePoints,gradeName from schoolGrades where custId="+ getUserCustId()+ " and academicYearId="+ getUserAcademicYearId()+ " and  minMarks <=("+ studentSubMarks+ ")  and   maxMarks >=("+ studentSubMarks+ ") ");
					if (!ObjectFunctions.isNullOrEmpty(gradeAndGradePoint)) {
						cell = row.createCell(p + 1);
						cell.setCellStyle(styles.get("string"));
						cell.setCellValue(gradeAndGradePoint[0].toString());
						cell = row.createCell(p + 2);
						cell.setCellStyle(styles.get("string"));
						cell.setCellValue(gradeAndGradePoint[1].toString());
						return studentSubMarks;
					}
				} else {
					cell = row.createCell(p);
					cell.setCellStyle(styles.get("string"));
					//cell.setCellValue(""+ Double.valueOf(df.format(studentSubMarks)));
					cell = row.createCell(p + 1);
					cell.setCellStyle(styles.get("string"));
					cell = row.createCell(p + 2);
					cell.setCellStyle(styles.get("string"));
					return studentSubMarks;

				}

			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
		}
		return 0;
	}

	public double withoutOptionalGradeWiseSubject(ViewStudentClassDetails studentDetails, long examTypeId, Cell cell,Row row, Map<String, CellStyle> styles, int p, Object[] obj,long classSectionId, Object[] subjectTotalMarks,HashMap<String,Integer> examSchedulesCells) {
		try {
			double studentSubMarks = 0;
			DecimalFormat df = new DecimalFormat("#.##");
			p=examSchedulesCells.get(obj[0].toString());
			log.debug("p ="+p);
			if (!ObjectFunctions.isNullOrEmpty(subjectTotalMarks)) {
				if (!ObjectFunctions.isNullOrEmpty(subjectTotalMarks[0]) && !ObjectFunctions.isNullOrEmpty(obj[1]))
					studentSubMarks = ((Double.valueOf(subjectTotalMarks[0].toString()) / Double.valueOf(obj[1].toString())) * 100) * 50 / 100;
				if (!ObjectFunctions.isNullOrEmpty(subjectTotalMarks[2]) && !ObjectFunctions.isNullOrEmpty(subjectTotalMarks[0])) {
					cell = row.createCell(p);
					cell.setCellStyle(styles.get("readOnlyString"));
					cell.setCellValue(""+ Double.valueOf(df.format(studentSubMarks)));
					Object[] gradeAndGradePoint = adminManager.get("select gradePoints,gradeName from schoolGrades where custId="+ getUserCustId()+ " and academicYearId="+ getUserAcademicYearId()+ " and  minMarks <=("+ studentSubMarks+ ")  and   maxMarks >=("+ studentSubMarks+ ") ");
					if (!ObjectFunctions.isNullOrEmpty(gradeAndGradePoint)) {
						cell = row.createCell(p + 1);
						cell.setCellStyle(styles.get("readOnlyString"));
						cell.setCellValue(gradeAndGradePoint[0].toString());
						cell = row.createCell(p + 2);
						cell.setCellStyle(styles.get("readOnlyString"));
						cell.setCellValue(gradeAndGradePoint[1].toString());
						return studentSubMarks;
					}
				}else{
					cell = row.createCell(p);
					cell.setCellStyle(styles.get("readOnlyString"));
					//cell.setCellValue(""+ Double.valueOf(df.format(studentSubMarks)));
					cell = row.createCell(p + 1);
					cell.setCellStyle(styles.get("readOnlyString"));
					cell = row.createCell(p + 2);
					cell.setCellStyle(styles.get("readOnlyString"));
					return studentSubMarks;
				}

			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
		}
		return 0;
	}
	public void withoutOptionalSubject(ViewStudentClassDetails studentDetails,long examTypeId,Cell cell,Row row,HashMap<String,Integer>examSchedulesCells,Map<String, CellStyle> styles){
		Integer col=null;
		//List<StudentMarks> studentMarks = adminManager.getStudentMarksByStudentIdAndExamtypeId(studentDetails.getStudId(),examTypeId);
		List<ViewStudentMarks> studentMarks = adminManager.getStudentMarksByStudentIdAndExamtypeId(studentDetails.getStudId(),examTypeId,getUserCustId(),getUserAcademicYearId());
		
			if(!ObjectFunctions.isNullOrEmpty(studentMarks)){
				log.debug("full name :"+studentDetails.getFullName());
				for(ViewStudentMarks marks: studentMarks){
					col = examSchedulesCells.get(marks.getExamTypeId()+"_"+marks.getScheduleId());
					//Siva: Removed the examtype id from sheet
					//col = examSchedulesCells.get(marks.getScheduleId());
					if(!ObjectFunctions.isNullOrEmpty(col)){
						cell = row.createCell(col);
						if(marks.isPresent()){
							cell.setCellValue(marks.getObtainedMarks());
							if(Constants.YES_STRING.equalsIgnoreCase(marks.getSubjectType())){
								log.debug(marks.getSubjectId() +"="+ studentDetails.getOptionalSubjectId());
								if(marks.getSubjectId() == studentDetails.getOptionalSubjectId()){
									cell.setCellStyle(styles.get("readOnlyString"));
								}else{
									cell.setCellStyle(styles.get("string"));
								}
							}else{
								cell.setCellStyle(styles.get("string"));
							}
						}else
						     cell.setCellValue("A");
						
						cell.setCellStyle(styles.get("string"));	
						cell = null;
					}/*else
						cell.setCellStyle(styles.get("string"));*/
					//Siva: Commenting this because no one is going to use moderation marks	and adding single examtypeId for the sheet
					/*StringBuffer query = new StringBuffer(marks.getExamTypeId()+"_"+marks.getScheduleId()).append("_MOD");
					col = examSchedulesCells.get(query.toString());
					if(!ObjectFunctions.isNullOrEmpty(col)){
						 row.createCell(Integer.valueOf(col)).setCellValue(marks.getModerationMarks());
					}*/
					marks=null;
				}
				studentMarks=null;
		}
	}
  	public void createExamSchedulesHeader(HSSFSheet sheet,Map<String, CellStyle> styles){
  		Customer customer = getCustomerByCustId();
  		Row row = null;
		Cell cell = null;
		String custAddress = null;
		StringBuffer query = null;
			if (!ObjectFunctions.isNullOrEmpty(customer)) {
				row=sheet.createRow(0);
				custAddress = customer.getOrganizationFullAddress();
				if (StringFunctions.isNotNullOrEmpty(customer.getOrganization()) || StringFunctions.isNotNullOrEmpty(custAddress)) {
					row.setHeightInPoints(50);
					sheet.addMergedRegion(CellRangeAddress.valueOf("$A$1:N$1"));
					if(StringFunctions.isNotNullOrEmpty(customer.getOrganization()) && StringFunctions.isNotNullOrEmpty(custAddress))
						query = new StringBuffer(customer.getOrganization()).append("\n").append(custAddress);
					else if(StringFunctions.isNotNullOrEmpty(customer.getOrganization()))
						query = new StringBuffer(customer.getOrganization());
					else
						query = new StringBuffer(custAddress);
					cell = row.createCell(0);
					cell.setCellValue(query.toString());
					cell.setCellStyle(styles.get("title"));
				}
				customer = null;
			}
			row=sheet.createRow(1);
			row.setHeightInPoints(25);
			sheet.addMergedRegion(CellRangeAddress.valueOf("$A$2:N$2"));
			cell = row.createCell(0);
			query = new StringBuffer("Class Name : ").append(getAnyTitle()).append(" , Exam Type : ").append(getTempString());
			if("MG".equalsIgnoreCase(getSelectedId())){
				query.append(" , Results");
			}
			cell.setCellValue(query.toString());
			cell.setCellStyle(styles.get("title"));
			row=sheet.createRow(2);
			row.setHeightInPoints(60);
			sheet.addMergedRegion(CellRangeAddress.valueOf("$A$3:N$3"));
			cell = row.createCell(0);
			if("M".equalsIgnoreCase(getSelectedId())){
				cell.setCellValue("Note :-\n1) You shouldn't change green colour fields. \n2) If any student is absent for particular subject add 'A' for that subject. \n3) Disabled or yellow colored cells are optional subjects selected according to students.You can enter marks only for optional subjects remaining optional subjects are disabled. \n4) If a student doesn't have any Selected optional subjects all the optional subjects are enabled and you can enter marks only for any one of the optional subjects.");
				cell.setCellStyle(styles.get("subtitle"));
			}
		styles = null;
		row = null;
		cell = null;
		custAddress = null;
		query = null;
  	}
  	public HashMap<String,Integer> createExamScheulesSubjectsHeader(HSSFWorkbook wb,HSSFSheet sheet,Map<String, CellStyle> styles,List<ViewExamSchedule> examScheduleDetails,int students,HashMap<String,List<ViewExamSchedule>> subjectMap,long classSectionId,StringBuilder subjIds,long examTypeId){
		StringBuffer query = null;
		StringBuffer scheduleSubType = null;
		List sechuduleObjects= null;
		Row scheduleIdsRow = null;
		Row scheduleSubjectNameRow = null;
		Row row = null;
		HashMap<String,Integer> examSchedulesCells = null;
		for(ViewExamSchedule schedule:examScheduleDetails){				
			if(!ObjectFunctions.isNullOrEmpty(schedule))
    		{   
				sechuduleObjects=new ArrayList();
				if(subjectMap.containsKey(schedule.getSubjectName())){
					sechuduleObjects=subjectMap.get(schedule.getSubjectName());
					sechuduleObjects.add(schedule);
					subjectMap.put(schedule.getSubjectName(),sechuduleObjects);
				}else{						
					sechuduleObjects.add(schedule);
					subjectMap.put(schedule.getSubjectName(),sechuduleObjects);
				}
				subjIds.append(schedule.getSubjectId());
				sechuduleObjects=null;
    		}
			schedule = null;
		}
		int count = 0;
		// For preparing Shedules header.
		if(!ObjectFunctions.isNullOrEmpty(subjectMap)){
			scheduleIdsRow = sheet.createRow(3);
			Cell cell1 = scheduleIdsRow.createCell(0);
			scheduleIdsRow.setZeroHeight(true);
			cell1.setCellValue(classSectionId);
			
			//Siva: fro examtypeId
			Cell examTypeCell = scheduleIdsRow.createCell(1);
			scheduleIdsRow.setZeroHeight(true);
			examTypeCell.setCellValue(examTypeId);
			
			scheduleSubjectNameRow = sheet.createRow(4);
			scheduleSubjectNameRow.setHeightInPoints(20);
			row = sheet.createRow(5);
			row.setHeightInPoints(30);
			sheet.setColumnWidth(1, 25);
			Cell cell = row.createCell(0);
			cell.setCellValue("Roll Number / Reg Number");
			sheet.autoSizeColumn(30);
			cell.setCellStyle(styles.get("headerInfo"));			
			cell = row.createCell(1);
			cell.setCellValue("Admission Number");
			cell.setCellStyle(styles.get("headerInfo"));
			cell = row.createCell(2);
			cell.setCellValue("Student Name");
			cell.setCellStyle(styles.get("headerInfo"));
			int colnum = 4;
			List<ViewExamSchedule> subjectSchedules = null;
			String subject = null;
			ViewExamSchedule examSchedule = null;
			int subjSubtypesCount = 0;
			//StringBuffer schedulePosition = null;
			examSchedulesCells = new HashMap<String,Integer>();
			int k=4;
			log.debug(subjectMap.size());
			int columnNum = subjectMap.size();
		    for(Map.Entry<String, List<ViewExamSchedule>>	entity : subjectMap.entrySet()) {
		        subject=entity.getKey();
		        subjectSchedules=entity.getValue();
		        int subjectCount = subjectSchedules.size()*1;
		        if(StringFunctions.isNotNullOrEmpty(subject) && ObjectFunctions.isNotNullOrEmpty(subjectSchedules)){
		        	subjSubtypesCount = subjectSchedules.size();
		        	cell = scheduleSubjectNameRow.createCell(colnum);
		        	cell.setCellValue(subject);
		        	cell.setCellStyle(styles.get("headerInfo"));
		        	examSchedule = subjectSchedules.get(0);
		        	if("M".equalsIgnoreCase(getSelectedId())){
			        	if(!ObjectFunctions.isNullOrEmpty(examSchedule) && examSchedule.isHigherStandard())
			        		sheet.addMergedRegion(new CellRangeAddress(scheduleSubjectNameRow.getRowNum(),scheduleSubjectNameRow.getRowNum(),colnum,colnum+subjSubtypesCount));
			        	else
			        		sheet.addMergedRegion(new CellRangeAddress(scheduleSubjectNameRow.getRowNum(),scheduleSubjectNameRow.getRowNum(),colnum,colnum+subjSubtypesCount-1));
			        	for(ViewExamSchedule schedule : subjectSchedules){
			        		log.debug("Subtype:" + schedule.getSubTypeName());
			        		query = new StringBuffer().append(schedule.getExamTypeId()).append("_").append(schedule.getScheduleId());
			        		//Siva: Adding examtype as global level
			        		//query = new StringBuffer().append(schedule.getScheduleId());
			        		scheduleIdsRow.createCell(colnum).setCellValue(query.toString());
			        		examSchedulesCells.put(query.toString(), colnum);
			        		scheduleSubType = new StringBuffer(schedule.getSubTypeName())
			    			.append("\n(Max Marks: ").append(schedule.getScheduleMaxMarks()).append(")");
			        		cell = row.createCell(colnum++);
			        		cell.setCellValue(scheduleSubType.toString());
			        		cell.setCellStyle(styles.get("headerInfo"));
			        	}
		        	}
		        if("MG".equalsIgnoreCase(getSelectedId())){
	         		sheet.addMergedRegion(new CellRangeAddress(scheduleSubjectNameRow.getRowNum(),scheduleSubjectNameRow.getRowNum(),colnum,colnum+2));
	         		for(ViewExamSchedule schedule : subjectSchedules){
		        		query = new StringBuffer().append(schedule.getSubjectId());
		        		scheduleIdsRow.createCell(k).setCellValue(query.toString());
		        		examSchedulesCells.put(query.toString(), k);
		        		k=k+3;
		         	}
	         		cell = row.createCell(colnum++);
	        		cell.setCellValue("Marks");
	        		cell.setCellStyle(styles.get("headerInfo"));
	        		cell = row.createCell(colnum++);
	        		cell.setCellValue("Grade Point");
	        		cell.setCellStyle(styles.get("headerInfo"));
	        		cell = row.createCell(colnum++);
	        		cell.setCellValue("Grade");
	        		cell.setCellStyle(styles.get("headerInfo"));
				}
		       //Siva: Commenting this because no one is going to use moderation marks	and adding single examtypeId for the sheet 	        	
		        /*if(!ObjectFunctions.isNullOrEmpty(examSchedule) && examSchedule.isHigherStandard()){
		        		query = new StringBuffer().append(examSchedule.getExamTypeId()).append("_").append(examSchedule.getScheduleId()).append("_MOD");
		        		scheduleIdsRow.createCell(colnum).setCellValue(query.toString());
		        		examSchedulesCells.put(query.toString(),colnum);
		        		cell = row.createCell(colnum++);
		        		cell.setCellValue("MOD");
		        		cell.setCellStyle(styles.get("headerInfo"));
		        	}*/
		        	count += subjectCount;
			        log.debug(count);
		        }
		    }	
		    if("MG".equalsIgnoreCase(getSelectedId())){
         		sheet.addMergedRegion(new CellRangeAddress(scheduleSubjectNameRow.getRowNum(),scheduleSubjectNameRow.getRowNum()+1,colnum,colnum));
			    cell = scheduleSubjectNameRow.createCell(colnum++);
	        	cell.setCellValue("Total Marks");
	        	cell.setCellStyle(styles.get("headerInfo"));
	        	sheet.addMergedRegion(new CellRangeAddress(scheduleSubjectNameRow.getRowNum(),scheduleSubjectNameRow.getRowNum()+1,colnum,colnum));
	        	cell = scheduleSubjectNameRow.createCell(colnum++);
	        	cell.setCellValue("OverAll Grade");
	        	cell.setCellStyle(styles.get("headerInfo"));
			 //   schedulePosition = null;
		    }
		    //showSchoolUrlInPOIExcelSheetFooter(null, wb, sheet, students+6, count+3);
		}
		
		scheduleIdsRow = null;
		row = null;
		query = null;
		scheduleSubType = null;
		sechuduleObjects= null;
		subjectMap=null;
		return examSchedulesCells;
  	}
/********************************************************************
 * Date              Name               Description
 * ========          ============       ==================
 * July 26, 2013     Seshu		        For uploading students marks sheet.
/********************************************************************/	
  	public String uploadStudentMarks(){
		 if (log.isDebugEnabled()) {
		        log.debug("Entering 'uploadMarksSheet' method");
		 }
  	 try{
  		log.debug("Start **********:" + DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_HHMMSS_PATTERN, new Date()));
  		 log.debug("file Type ="+getUploadContentType());
  		 boolean excelFileType = false;
		 excelFileType = validateExcelFileType(getUploadContentType());
		 if(excelFileType){
			log.debug("No file to upload....");
			super.addActionError("File type not matched.");
			return "dummyInit";
		 }
  		/*if(!(Constants.FILE_TYPE_XLS.equalsIgnoreCase(getUploadContentType())
    			|| Constants.FILE_TYPE_APPLICATION_XLS.equalsIgnoreCase(getUploadContentType())
    			|| Constants.FILE_TYPE_APPLICATION_STREAM_CSV.equalsIgnoreCase(getUploadContentType())
    			|| Constants.FILE_TYPE_APPLICATION_DOWNLOAD.equalsIgnoreCase(getUploadContentType())
    			|| Constants.FILE_TYPE_APPLICATION_MS_EXCEL.equalsIgnoreCase(getUploadContentType())    			
				|| Constants.FILE_TYPE_APPLICATION_MSEXCEL.equalsIgnoreCase(getUploadContentType())))
		  	{
			  log.debug("No file to upload....");
			  super.addActionError("File type not matched.");
			  return "dummyInit";
		  	}*/	
  		 if(getUserAcademicYearId() > 0){
  			long studentId = 0;
  			FileInputStream file = new FileInputStream(getUpload());
  		    //Get the workbook instance for XLS file
  		   org.apache.poi.ss.usermodel.Workbook workbook = WorkbookFactory.create(file);
  		   org.apache.poi.ss.usermodel.Sheet sheet = workbook.getSheetAt(0);
  		   
  		   /*Row secondRow = sheet.getRow(5);
			if(!ObjectFunctions.isNullOrEmpty(secondRow))
			{
				if(!ObjectFunctions.isNullOrEmpty(secondRow.getCell(0)) && !ObjectFunctions.isNullOrEmpty(secondRow.getCell(1)))
				{
					String fistColumn = secondRow.getCell(0).getStringCellValue();
					String secondColumn = secondRow.getCell(1).getStringCellValue();
					
					if(!"Roll Number".equalsIgnoreCase(fistColumn.toString()) || !"Admission Number".equalsIgnoreCase(secondColumn.toString()))
					{
						log.debug("No file to upload....");
						super.addActionError("File type not matched.");
						return "dummyInit";
					}
					fistColumn = null;
					secondColumn = null;
				}
				else
				{
					log.debug("No file to upload....");
					super.addActionError("File type not matched.");
					return "dummyInit";
				}
			}
			else
			{
				log.debug("No file to upload....");
				super.addActionError("File type not matched.");
				return "dummyInit";
			}*/
			
			
  		    Row row = null;
  		    int colmnSize = 0;
  		    String ids,scheduleId = null;
  		    String[] anyIds = null;
  		    Cell cell = null;
  		    double obtainedMarks = 0;
  		    long examScheduleId = 0;
  		    Row scheduleIdsRow = null;
  		  //  long examTypeId=395;
  		   Student student = null;
  		   StringBuffer errorMsg = new StringBuffer();
  		   String isPresent="P";
  		   //String text = "A"; 
  		   List<Student> studentList=null;
  		   List<ViewStudentMarks> studentMarksList=null; 
  		    HashMap<Integer, String> showingResult = new HashMap<Integer, String>();
  		    HashMap<Long, Student> studentsList = new HashMap<Long,Student>();
		   
		    HashMap<Long, ExamSchedules> schedulesMap = new HashMap<Long, ExamSchedules>();
		    
		    ExamSchedules examSchedules=null;
		   
		    boolean isFirstRow=true;
		    
  		    for(int sheetNum=0; sheetNum < workbook.getNumberOfSheets(); sheetNum++){
  		    	sheet = workbook.getSheetAt(sheetNum);
  		    	scheduleIdsRow= sheet.getRow(3);
  		    	 
  		    	Integer classSectionId=0;
  		    	if(!ObjectFunctions.isNullOrEmpty(scheduleIdsRow)){
  		    		if(!ObjectFunctions.isNullOrEmpty(scheduleIdsRow.getCell(0))){
  		  		    	if(scheduleIdsRow.getCell(0).getCellType() == Cell.CELL_TYPE_NUMERIC){    	
  		  		    		if(!ObjectFunctions.isNullOrEmpty(scheduleIdsRow.getCell(0).getNumericCellValue()))
  		  		    			classSectionId=Double.valueOf(scheduleIdsRow.getCell(0).getNumericCellValue()).intValue();
  		  		    	 }
  	  		    	}
  		    	}
  		    	
  		    	log.debug("ClassSectionId Is Reading ....."+classSectionId);
  		    	StringBuffer query = new StringBuffer("custId=").append(getUserCustId()).append(" and academicYearId=").append(getUserAcademicYearId()).append(" and classSectionId=").append(classSectionId).append(" and description is null");
  			    studentList=adminManager.getAll(Student.class, query.toString());
  			   
	  			query=null;
	  			   
  			   if(!ObjectFunctions.isNullOrEmpty(studentList)){
  	 		  		for(Student studentObj: studentList){
  	 		  		   studentsList.put(studentObj.getId(),studentObj); 		  		
  	 		  		}
  	 		  	}
  		    	
  		    	for(int rowNum=6;rowNum<sheet.getPhysicalNumberOfRows();rowNum++){
  		    		row = sheet.getRow(rowNum);
  		    		if(!ObjectFunctions.isNullOrEmpty(row) && !ObjectFunctions.isNullOrEmpty(row.getCell(3))){
  		    		 if(row.getCell(3).getCellType() == Cell.CELL_TYPE_NUMERIC){    			   		    			
  		    			
	  		    		studentId = Double.valueOf(row.getCell(3).getNumericCellValue()).intValue();
	  		    		if(studentId > 0){
		   	    				if(!ObjectFunctions.isNullOrEmpty(studentsList))
		   	    				student=studentsList.get(studentId);
				   	    		if(!ObjectFunctions.isNullOrEmpty(student)){
				   	    			student.setPopUpDisplay(Constants.ACTIVE_STATUS);
				   	    			colmnSize = row.getLastCellNum();
				   	    			 for(int colNum=4; colNum < colmnSize ;colNum++)
				   	    			 {
				   	    				cell = row.getCell(colNum); 
				   	    				if(ObjectFunctions.isNullOrEmpty(cell) && !ObjectFunctions.isNullOrEmpty(scheduleIdsRow)){
				   	    					ids = scheduleIdsRow.getCell(colNum).getStringCellValue();
					   	    				 if(StringFunctions.isNotNullOrEmpty(ids)){	
				   	    					Object[] maxSortOrder = adminManager.get("select id,obtainedMarks from studentMarks where studId = "+ student.getId() +" and examScheduleId="+Long.valueOf(ids.split("_")[1]));
			   	 								if(!ObjectFunctions.isNullOrEmpty(maxSortOrder)){
			   	 									adminManager.remove("studentMarks", "id="+maxSortOrder[0].toString());
			   	 								}
			   	 								maxSortOrder = null;
					   	    				 }		
				   	    				}
				   	    				
				   	    				if(!ObjectFunctions.isNullOrEmpty(cell) && !ObjectFunctions.isNullOrEmpty(scheduleIdsRow) && !ObjectFunctions.isNullOrEmpty(scheduleIdsRow.getCell(colNum))){
				   	    					ids = scheduleIdsRow.getCell(colNum).getStringCellValue();
				   	    				 if(StringFunctions.isNotNullOrEmpty(ids)){
				   	    				 if(cell.getCellType() != Cell.CELL_TYPE_BLANK || cell.getStringCellValue()=="" || cell==null){
						   	    				anyIds=ids.split("_");
					   	    					if(!ObjectFunctions.isNullOrEmpty(anyIds)){
					   	    						examScheduleId=Long.valueOf(anyIds[1]);
					   	    						if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){
					   	    							obtainedMarks = cell.getNumericCellValue();
					   	    							isPresent="P";
					   	    						}/*else if(cell.getCellType() != Cell.CELL_TYPE_NUMERIC){
					   	    							obtainedMarks = Double.valueOf(String.valueOf("A"));
					   	    						}*/
					   	    						/*else if(cell.getCellType() == Cell.CELL_TYPE_BLANK || cell.getStringCellValue()=="" || cell==null)
					   	    						{
					   	    							isPresent="A"; 
					   	    						}*/
					   	    						else{
					   	    							obtainedMarks = 0;
					   	    							isPresent="A";
					   	    							//obtainedMarks = Double.valueOf(String.valueOf("A"));
					   	    						}
					   	    						//if(examTypeId == 0)
														//examTypeId = Long.valueOf(anyIds[0]);
													
													if(isFirstRow){
													  examSchedules= (ExamSchedules) adminManager.get(ExamSchedules.class,"id=" +examScheduleId+" and custId = "+ student.getCustId() +" and academicYearId="+student.getAcademicYearId());
													  schedulesMap.put(examScheduleId, examSchedules);													  
													}
													//log.debug("This is Exam Action for uploading marks sheet ....."+new Date());
						   	    					student=adminManager.upadateStudentMarksFromSMSAppAndWeb(student,examScheduleId,obtainedMarks, getUser().getId(),anyIds,isPresent,showingResult,schedulesMap);
						   	    					if(StringFunctions.isNotNullOrEmpty(student.getErrorMsg()))
						   	    						errorMsg.append(student.getErrorMsg()+",");
						   	    					continue;
												}
									   	    }				   	    				 
				   	    				  }
				   	    				
						   	    		}
					   	    		}	
				   	    			 adminManager.saveStudentDetails(student);
						   	      }
		   	    			  }
	  		    		     isFirstRow=false;
	  		    			}else{
		  		    			log.debug("Uploaded wrong file..");
		  						super.addActionError("You have uploaded wrong file. Please download latest sheet from system and upload.");
		  						return SUCCESS;
			  		    	}
  		    		       }
	   	    			}
    					sheet = null;
   	    			}
  		    log.debug("Few student(s) data not valid. Please verify the data:" + StringFunctions.removeDuplicateWords(errorMsg.toString(), ","));
  		  
  		    if(errorMsg.length() > 0)
  		    {
  		    	//super.addActionError(StringFunctions.removeDuplicateWords(errorMsg.toString(), ",") + " Few student(s) data not valid. Please verify the data.");
  		    	errorMsg.insert(0,"Few student(s) data not valid. Please verify the data:");
 		    	super.addActionError(StringFunctions.removeDuplicateWords(errorMsg.toString(), ","));
  		    }
  		  
  		  if(!ObjectFunctions.isNullOrEmpty(showingResult)){
   			 if(showingResult.get(0).equalsIgnoreCase("true")){
   				 	//Sending Notification to mobile for student marks addition
   			//Sending Notification to mobile for student marks addition
   				StudentMarksNotificationThread R1 = new StudentMarksNotificationThread(getUserCustId());
   			    R1.start();
			    	//adminManager.sendNotificationForStudentMarks(getUserCustId());
 		   	    	super.addActionMessage("Student Marks submitted successfully.");
 		   	    /*if(student.getClassSectionId() !=0 ){
	   	    		loadStudentsMarksIntoScoreCardGenarationTable(student.getClassSectionId());
	   	    		calculateStudentsSubjectPoistionByExamScheule(student.getClassSectionId(),examTypeId);
	   	    	}*/
   			 }	
   			else
 		   	  	super.addActionError("Student marks are not submitted due to invalid marks .Please recheck and submit again");
   		  }
 		  	else
 		   	  	super.addActionError("Student marks are not submitted due to invalid marks .Please recheck and submit again");
 	       }
  		log.debug("End **********:" + DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_HHMMSS_PATTERN, new Date()));
  		 }
	     catch(Exception ex)
	     {
	    	 log.error("Entering into 'catch block':"+ex.getMessage());
	    	 super.addActionError("Student marks are not submitted due to invalid marks .Please recheck and submit again");
  	 	 ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
  	 	JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
  	 }
      return SUCCESS;
  
	}
  	public void calculateStudentsSubjectPoistionByExamScheule(long classSectionId,long examTypeId){
		try{
			 ThreadPoolTaskExecutor taskExecutor = (ThreadPoolTaskExecutor) SpringContextAware.getBean("studentMarksTaskExecutor");
			 ProcessStudentsSubjectwisePosition processStudentMarks = new ProcessStudentsSubjectwisePosition();
			 processStudentMarks.setClassSectionId(classSectionId);
			 processStudentMarks.setExamTypeId(examTypeId);
			 taskExecutor.execute(processStudentMarks);
		}catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
	}
/********************************************************************
 * Date              Name               Description
 * ========          ============       ==================
 * Aug 13, 2013      Seshu		      	We can create and deleting template for promotion report and for genrating report based on templates.
/********************************************************************/
  	  @Actions( {
  			@Action(value = "ajaxManagePromotionReports", results = { @Result(location = "scorecard/ajaxManageStudentsPromotionReport.jsp", name = "success")})
  		})
  		 public String ajaxManagePromotionReports(){
  		 if (log.isDebugEnabled()) {
  			log.debug("Entering 'ajaxManagePromotionReports' method");
  		}
  		 if (getUserAcademicYearId() > 0) {
  				setStudyClassList(adminManager.GetAllStudyClasses(getUserCustId(), getUserAcademicYearId(),null));
  				StringBuffer query = new StringBuffer("custId=").append(getUserCustId());
  				setTempId(adminManager.getCount("studentsPromotionReportTemplates", query.toString()));
  				query = null;
  			}
  		return SUCCESS; 
  	  }
/********************************************************************
 * Date              Name               Description
 * ========          ============       ==================
 * Aug 13, 2013      Seshu		      	We are providing screen for uploading promotion report template and also displaying uploaded template details.
/********************************************************************/
  	  @Actions( {@Action(value = "ajaxDoUploadPromotionReportTemplate", results = {@Result(location = "scorecard/ajaxUploadPromotionReportTemplate.jsp", name = "success") })
  		   })
  		public String ajaxDoUploadPromotionReportTemplate() throws URTUniversalException {
  			if (log.isDebugEnabled()) {
  				log.debug("Entering 'ajaxDoUploadPromotionReportTemplate' method");
  			}
  			try {
  				setClassList(adminManager.getAllClassNames( getUserCustId(),getUserAcademicYearId()));
  				StringBuffer query = null;
  				query = new StringBuffer("custId=").append(getUserCustId());
  				setObjectList(adminManager.getAll(StudentsPromotionReportTemplates.class, query.toString()));
  				query = null;
  			} catch (Exception ex) {
  				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
  			}
  			return SUCCESS;
  		}
  	  /********************************************************************
  	   * Date              Name               Description
  	   * ========          ============       ==================
  	   * May 21, 2013      Seshu		      For storing score card template in userfiles directory
  	  /********************************************************************/
  	  @Actions( {@Action(value = "ajaxUploadPromotionReportTemplate", results = {@Result(location = "scorecard/ajaxManageStudentsPromotionReport.jsp", name = "success") })})
  		public String ajaxUploadPromotionReportTemplate() throws URTUniversalException {
  			if (log.isDebugEnabled()) {
  				log.debug("Entering 'ajaxUploadPromotionReportTemplate' method");
  			}
  			try {
  				if (StringFunctions.isNotNullOrEmpty(getAnyId())) {
  					List<String> classNamesList = Arrays.asList(getAnyId().split(","));
  					Customer customer = getCustomerByCustId();
  					if(!ObjectFunctions.isNullOrEmpty(customer) && StringFunctions.isNotNullOrEmpty(customer.getCustomerShortName())){
  						StringBuffer pathName = null;
  						if (getUserAcademicYearId() != 0) {
  							StudentsPromotionReportTemplates promotionReportTemplate = null;
  							StringBuffer query = null;
  						//	File templateFile = null;
  							MultiPartRequestWrapper multiWrapper = (MultiPartRequestWrapper) ServletActionContext.getRequest();
  							for(String className : classNamesList){
  								query = new StringBuffer("custId=").append(getUserCustId()).append(" and className='").append(className).append("'");
  								promotionReportTemplate = (StudentsPromotionReportTemplates)adminManager.get(StudentsPromotionReportTemplates.class, query.toString());
  								query = null;
  								if(ObjectFunctions.isNullOrEmpty(promotionReportTemplate)){
  									promotionReportTemplate = new StudentsPromotionReportTemplates();
  									promotionReportTemplate.setCreatedById(getUser().getId());
  									promotionReportTemplate.setCreatedDate(new Date());
  								}
  								promotionReportTemplate.setFileName(null);
  								Enumeration<String> fileParaNames = multiWrapper.getFileParameterNames();
  								while (fileParaNames.hasMoreElements()) {
  									String key = fileParaNames.nextElement();
  									File[] fileObject = multiWrapper.getFiles(key);
  									String[] localSysfileNames = multiWrapper.getFileNames(key);
  									setUploadFileName(StringFunctions.stripSymbols(localSysfileNames[0]));
  									pathName = new StringBuffer("userFiles/PromotioReportTemplates/").append(customer.getCustomerShortName()).append("/")
  									.append(getUploadFileName());
  									File destDir = new File(getSession().getServletContext().getRealPath(pathName.toString()));
  									if(pathName.toString().contains(".xls") || pathName.toString().contains(".xlsx")){
  										promotionReportTemplate.setFileName(pathName.toString());
  									}
  									FileUtils.copyFile(fileObject[0], destDir);
  									key = null;
  									fileObject = null;
  									localSysfileNames = null;
  									destDir = null;
  									pathName = null;
  								}
  								promotionReportTemplate.setCustId(getUserCustId());
  								promotionReportTemplate.setLastUpdatedById(getUser().getId());
  								promotionReportTemplate.setLastUpdatedDate(new Date());
  								promotionReportTemplate.setClassName(className);
  								adminManager.save(promotionReportTemplate);
  								promotionReportTemplate = null;
  								className = null;
  							}
  							super.addActionMessage("Successfully uploaded promotion report template.");
  						//	templateFile = null;
  						}
  						pathName = null;
  						customer = null;
  					}else{
  						log.error("Customer not found or customer short name not defined.");
  						super.addActionError("Promotion report template not uploaded.");
  					}
  				}
  			} catch (Exception ex) {
  				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
  			}finally{
  				ajaxManagePromotionReports();
  			}
  			return SUCCESS;
  		}
/********************************************************************
 * Date              Name               Description
 * ========          ============       ==================
 * Aug 13, 2013      Seshu		      	For deleting promotion templates
/********************************************************************/	
  @Actions( {@Action(value = "ajaxDeletePromotionReportTemplate", results = {@Result(location = "scorecard/ajaxUploadPromotionReportTemplate.jsp", name = "success") })})
	public String ajaxDeletePromotionReportTemplate() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDeletePromotionReportTemplate' method");
		}
		try {
			if(getTempId()>0){
				File templateFile= null;
				int count = 0;
				StudentsPromotionReportTemplates template = (StudentsPromotionReportTemplates)adminManager.get(StudentsPromotionReportTemplates.class, getTempId());
				if(!ObjectFunctions.isNullOrEmpty(template)){
					if(StringFunctions.isNotNullOrEmpty(template.getFileName())){
						count = adminManager.getCount("studentsPromotionReportTemplates", "fileName='"+template.getFileName()+"'");
						if(count == 1 ){
							templateFile= new File(getSession().getServletContext().getRealPath(template.getFileName()));
							FileUtils.deleteQuietly(templateFile);
						}
					}
				}
				template = null;
				templateFile = null;
				adminManager.remove(StudentsPromotionReportTemplates.class, getTempId());
				super.addActionMessage("Successfully deleted promotion report template.");
				ajaxDoUploadPromotionReportTemplate();
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
  /********************************************************************
	 * Date              Name               Description
	 * ============      =======		    ==================
	 * Aug 6, 2013       Seshu		        For generating students promotion report.
	/********************************************************************/
	  @Actions( {
			@Action(value = "ajaxGenerateStudentsPromotionReport", results = { @Result(location = "student/ajaxViewStudentExamSchedules.jsp", name = "success")})
		})
		public void ajaxGenerateStudentsPromotionReport() throws URTUniversalException {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxGenerateStudentsPromotionReport' method");
			}
			try {
				String fileName = "Students_Marks_"+ DateUtil.getDateTime(DateFormatter.MMDDYYY_PATTERN,new Date());
				getResponse().setContentType("application/vnd.ms-excel");
				getResponse().setHeader("Content-Disposition","attachment; filename=" + fileName.replace(' ', '_')+ ".xls");
				if(StringFunctions.isNotNullOrEmpty(getTempString()) && getTempString().split(":").length == 2){
					StringBuffer query = null;
					List<ViewStudentsTCDetails> studentsList = null;
					File scoreCardAcademicfile = null;
					Object fieldValue = null;
					String[] classNameAndClassId = null; 
					HashMap<Long, Integer> schedulesCellInfo = null;
					HashMap<String,Integer> moderationCellInfo = null;
					HashMap<Integer, String> studentDetailsCellInfo =  null;
					HashMap<Integer, String> formulasCellInfo =  null;
					StringBuffer scheduleInfo = null;
					int marksWritableStartRow =0;
					boolean displayAbsenties = false;
					boolean displayModerationMarks = false;
					Cell cell = null;
					XSSFWorkbook workbook = null;
					XSSFSheet sheet = null;
					classNameAndClassId = getTempString().split(":");
					String className = classNameAndClassId[0];
					long classSectionId = Long.valueOf(classNameAndClassId[1]);
					query = new StringBuffer("custId=").append(getUserCustId()).append(" and className='").append(className).append("'");
					StudentsPromotionReportTemplates studentsPromotionReport =(StudentsPromotionReportTemplates)adminManager.get(StudentsPromotionReportTemplates.class,query.toString());
					if(!ObjectFunctions.isNullOrEmpty(studentsPromotionReport) && StringFunctions.isNotNullOrEmpty(studentsPromotionReport.getFileName())){
						scoreCardAcademicfile = new File(getSession().getServletContext().getRealPath(studentsPromotionReport.getFileName()));
						if(!ObjectFunctions.isNullOrEmpty(scoreCardAcademicfile)){
							if(scoreCardAcademicfile.exists()){
							 FileInputStream templateFile= new FileInputStream(scoreCardAcademicfile);
							  workbook = new XSSFWorkbook(templateFile);
							  sheet = workbook.getSheet("Config");
							  Map<String, CellStyle> styles = PrepareStudentExcel.createStyles(workbook);
							  if(!ObjectFunctions.isNullOrEmpty(sheet)){
								  Row subjectNamesRow = null;
								  Row examTypeNameRow = null;
								  Row subTypeNamesRow = null;
								  Row profileDetailsRow = null;
								  Row formulaDetailsRow = null;
								  Row classDetailsRow = null;
								  ViewClassSectionTeacher classSection = null;
								  String subjectName = null;
								  String examTypeName= null;
								  String subTypeName = null;
								  Long examScheduleId = null;
								  String profileDetailProperty = null;
								  String formula = null;
								  int examTypesRow = 0;
								  int subjectsRow = 0;
								  int subTypesRow = 0;
								  int personalDetailsRow = 0;
								  int formulasRow = 0;
								  int classDetailsRowValue =0;
								  int endColumn =0 ;
								  cell = sheet.getRow(0).getCell(1);
								  if(!ObjectFunctions.isNullOrEmpty(cell))
									  examTypesRow = (int)cell.getNumericCellValue();
								  cell = sheet.getRow(0).getCell(3);
								  if(!ObjectFunctions.isNullOrEmpty(cell))
									  subjectsRow = (int)cell.getNumericCellValue();
								  cell = sheet.getRow(0).getCell(5);
								  if(!ObjectFunctions.isNullOrEmpty(cell))
									  subTypesRow = (int)cell.getNumericCellValue();
								  cell = sheet.getRow(0).getCell(7);
								  if(!ObjectFunctions.isNullOrEmpty(cell))
									  personalDetailsRow = (int)cell.getNumericCellValue();
								  cell = sheet.getRow(0).getCell(9);
								  if(!ObjectFunctions.isNullOrEmpty(cell))
									  formulasRow = (int)cell.getNumericCellValue();
								  cell = sheet.getRow(0).getCell(11);
								  if(!ObjectFunctions.isNullOrEmpty(cell))
									  endColumn = (int)cell.getNumericCellValue();
								  cell = sheet.getRow(0).getCell(13);
								  if(!ObjectFunctions.isNullOrEmpty(cell))
									  marksWritableStartRow = (int)cell.getNumericCellValue();
								  cell = sheet.getRow(0).getCell(15);
								  if(!ObjectFunctions.isNullOrEmpty(cell))
									  displayAbsenties = cell.getBooleanCellValue();
								  cell = sheet.getRow(0).getCell(17);
								  if(!ObjectFunctions.isNullOrEmpty(cell))
									  displayModerationMarks = cell.getBooleanCellValue();
								  cell = sheet.getRow(0).getCell(19);
								  if(!ObjectFunctions.isNullOrEmpty(cell) && cell.getCellType() == Cell.CELL_TYPE_NUMERIC){
									  classDetailsRowValue = (int)cell.getNumericCellValue();
									  query = new StringBuffer("classSectionId=").append(classSectionId)
									  .append(" and academicYearId=").append(getUserAcademicYearId()).append(" order by classTeacher DESC limit 1");
									  List<ViewClassSectionTeacher> classSections = adminManager.getAllClassSectionTeachersDetails(query.toString());
									  if(ObjectFunctions.isNotNullOrEmpty(classSections)){
										  classSection = classSections.get(0);
									  }
								  }
								  query = new StringBuffer("select CONCAT(name,'_',examType),subTypeName,scheduleId FROM vw_examScheduleDetailsWithTopperMarks WHERE classSectionId=").append(classSectionId);
								  query.append(" and academicYearId=").append(getUserAcademicYearId()).append(" order by sortingOrder,examTypeSortingOrder,subTypeSortingOrder");
								  List<Object[]> examSchedules = adminManager.getAll(query.toString());
								  if(ObjectFunctions.isNotNullOrEmpty(examSchedules)){
									  HashMap<String, Long> examScheudlesDetails=new HashMap<String, Long>();
									  schedulesCellInfo = new HashMap<Long, Integer>();
									  studentDetailsCellInfo = new HashMap<Integer, String>();
									  moderationCellInfo = new HashMap<String, Integer>();
									  formulasCellInfo = new LinkedHashMap<Integer, String>();
									  for(Object[] schedule : examSchedules){
						    			if(!ObjectFunctions.isNullOrEmpty(schedule[0]) && !ObjectFunctions.isNullOrEmpty(schedule[1]) && !ObjectFunctions.isNullOrEmpty(schedule[2])){
						    				scheduleInfo = new StringBuffer(schedule[0].toString()).append("_").append(schedule[1].toString());
						    				log.debug("Keys : "+scheduleInfo.toString().toLowerCase());
						    				examScheudlesDetails.put(scheduleInfo.toString().toLowerCase(),Long.valueOf(schedule[2].toString()));
						    				scheduleInfo = null;
						    			}
						    			schedule = null;
									  }
									  for(int column = 0 ; column <= endColumn; column++ ){
										  subjectNamesRow = sheet.getRow(subjectsRow);
										  examTypeNameRow = sheet.getRow(examTypesRow);
										  subTypeNamesRow = sheet.getRow(subTypesRow);
										  if(!ObjectFunctions.isNullOrEmpty(subjectNamesRow.getCell(column)) && !ObjectFunctions.isNullOrEmpty(examTypeNameRow.getCell(column))
												  && !ObjectFunctions.isNullOrEmpty(subTypeNamesRow.getCell(column))){
											  subjectName = subjectNamesRow.getCell(column).getStringCellValue();
											  examTypeName= examTypeNameRow.getCell(column).getStringCellValue();
											  subTypeName = subTypeNamesRow.getCell(column).getStringCellValue();
											  if(StringFunctions.isNotNullOrEmpty(subjectName) && StringFunctions.isNotNullOrEmpty(examTypeName) &&
													  StringFunctions.isNotNullOrEmpty(subTypeName)){
												  if(displayModerationMarks && "MODMarks".equalsIgnoreCase(subTypeName)){
													  scheduleInfo = new StringBuffer(subjectName).append("_").append(examTypeName);
													  moderationCellInfo.put(scheduleInfo.toString().trim().toLowerCase(), column);
												  }else{
													  scheduleInfo = new StringBuffer(subjectName).append("_").append(examTypeName).append("_").append(subTypeName);
													  log.debug("Find Schedule : "+scheduleInfo.toString().toLowerCase());
													  examScheduleId = examScheudlesDetails.get(scheduleInfo.toString().toLowerCase());
													  if(!ObjectFunctions.isNullOrEmpty(examScheduleId)){
														  schedulesCellInfo.put(examScheduleId, column);
													  }
												  }
											  }
										  }
										  profileDetailsRow = sheet.getRow(personalDetailsRow);
										  if(!ObjectFunctions.isNullOrEmpty(profileDetailsRow)){
											  if(!ObjectFunctions.isNullOrEmpty(profileDetailsRow.getCell(column))){
												  profileDetailProperty = profileDetailsRow.getCell(column).getStringCellValue();
												  if(StringFunctions.isNotNullOrEmpty(profileDetailProperty))
													  studentDetailsCellInfo.put(column, profileDetailProperty);
											  }
										  }
										  formulaDetailsRow = sheet.getRow(formulasRow);
										  if(!ObjectFunctions.isNullOrEmpty(formulaDetailsRow)){
											  if(!ObjectFunctions.isNullOrEmpty(formulaDetailsRow.getCell(column))){
												  formula = formulaDetailsRow.getCell(column).getStringCellValue();
												  if(StringFunctions.isNotNullOrEmpty(formula)){
													  log.debug("Setting Formula Column : "+column);
													  log.debug("Setting Formula : "+formula);
													  formulasCellInfo.put(column, formula);
												  }
											  }
										  }
										  classDetailsRow = sheet.getRow(classDetailsRowValue);
										  if(!ObjectFunctions.isNullOrEmpty(classSection) && !ObjectFunctions.isNullOrEmpty(classDetailsRow)){
											  cell = classDetailsRow.getCell(column);
											  if(!ObjectFunctions.isNullOrEmpty(cell) && cell.getCellType() != Cell.CELL_TYPE_BLANK){
												  try {
			  									      fieldValue = classSection.getClass().getDeclaredMethod(cell.getStringCellValue(), null).invoke(classSection, null);
			  								    		  cell.setCellStyle(styles.get("string"));
			  											  if (ObjectFunctions.isNullOrEmpty(fieldValue)) {
			  												cell.setCellValue("");
			  											  } else if (fieldValue instanceof String) {
			  												cell.setCellValue(fieldValue.toString());
			  											  } else if (fieldValue instanceof Date || fieldValue instanceof Timestamp) {
			  												cell.setCellStyle(styles.get("date"));
			  												cell.setCellValue((Timestamp) fieldValue);
			  											  } else if (fieldValue instanceof Double) {
			  												cell.setCellStyle(styles.get("cell"));
			  												cell.setCellValue((Double) fieldValue);
			  											  }else if( fieldValue instanceof Integer ){
			  												  cell.setCellStyle(styles.get("cell"));
			  												  cell.setCellValue((Integer)fieldValue);
			  											  } else if(fieldValue instanceof Long){
			  												  cell.setCellStyle(styles.get("cell"));
			  												  cell.setCellValue((Long)fieldValue);
			  											  }	else{
			  												cell.setCellValue(fieldValue.toString());
			  											  }
			  									  } catch (NoSuchMethodException x) {
			  										    x.printStackTrace();
			  									  } catch (IllegalAccessException x) {
			  										    x.printStackTrace();
			  									  } catch (InvocationTargetException x) {
			  										    x.printStackTrace();
			  									  }
											  }
										  }
									  }
									  examScheudlesDetails = null;
								  }
								  cell = null;
								  subjectNamesRow = null;
								  examTypeNameRow = null;
								  subTypeNamesRow = null;
								  profileDetailsRow = null;
								  formulaDetailsRow = null;
								  classDetailsRow = null;
								  subjectName = null;
								  examTypeName= null;
								  subTypeName = null;
								  examScheduleId = null;
								  examScheduleId = null;
								  profileDetailProperty = null;
								  formula = null;
								  classSection = null;
							  }
							  sheet = workbook.getSheet("Sheet1");
							  if(!ObjectFunctions.isNullOrEmpty(sheet) && marksWritableStartRow >0){
									 query = new StringBuffer("classSectionId=").append(classSectionId).append(" and academicYearId=").append(getUserAcademicYearId())
									 .append(" and studDiscontinueDesc is null");
					  				 studentsList = adminManager.getAll(ViewStudentsTCDetails.class ,query.toString());
					  				 if(ObjectFunctions.isNotNullOrEmpty(studentsList)){
					  					Collections.sort(studentsList);
					  					AcademicYear academicYear= (AcademicYear)adminManager.get(AcademicYear.class,getUserAcademicYearId());
					  					String endDate = null;
					  					int totalWorkingDays = 0;
					  					Object[] studAtt = null;
					  					Row studentsMarksRow = null;
					  					Integer column =null;
					  					List<StudentMarks> studentMarks = null;
			  							String cellInfo = null;
					  					if(!ObjectFunctions.isNullOrEmpty(academicYear)){
					  						if(DateFunctions.compare2Dates(new Date(),academicYear.getStartDate()) && DateFunctions.compare2Dates(academicYear.getEndDate(),new Date()))
					  							endDate = DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, new Date());
					  	    				else if(new Date().after(academicYear.getEndDate()))
					  	    					endDate = DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, academicYear.getEndDate());
					  					if("D".equalsIgnoreCase(academicYear.getManageAttendanceBy())){
					  						totalWorkingDays = adminManager.fetchTotalWorkingDays(academicYear.getId(),endDate);
					  					}
					  					for(ViewStudentsTCDetails student : studentsList){
					  						if("D".equalsIgnoreCase(academicYear.getManageAttendanceBy())){
				  								student.setTotalWorkingDaysCount(totalWorkingDays);
				  								student.setPresentDaysCount(totalWorkingDays - adminManager.fetchStudentAbsentiesCount(student.getStudId(),endDate));
				  							}else{
				  								studAtt = adminManager.get("select IFNULL(sum(totalWorkingDays),0) as totalWorkingDays,IFNULL(sum(noOfPresentDays),0) as noOfPresentDays from studentMonthlyAttendance where studentId="+student.getStudId());
				  								if(!ObjectFunctions.isNullOrEmpty(studAtt)){
				  									if(!ObjectFunctions.isNullOrEmpty(studAtt[0]) && !ObjectFunctions.isNullOrEmpty(studAtt[1])){
				  										student.setTotalWorkingDaysCount(((Double)studAtt[0]).intValue());
				  										student.setPresentDaysCount(((Double)studAtt[1]).intValue());
				  									}
				  								}
				  							}
					  						studentsMarksRow = sheet.createRow(marksWritableStartRow);
					  						query = new StringBuffer("studId=").append(student.getStudId());
					  						studentMarks = adminManager.getAll(StudentMarks.class, query.toString());
					  						  if(ObjectFunctions.isNotNullOrEmpty(studentMarks) && !ObjectFunctions.isNullOrEmpty(schedulesCellInfo)){
				  								  for(StudentMarks marks : studentMarks){
				  									  column = schedulesCellInfo.get(marks.getScheduleId());
				  									  if(!ObjectFunctions.isNullOrEmpty(column) && column > 0){
				  										  cell = studentsMarksRow.createCell(column);
				  										  cell.setCellStyle(styles.get("cell"));
				  										  if(displayAbsenties){
				  											  if(marks.isPresent()){
				  												  if(displayModerationMarks)
				  													  cell.setCellValue(marks.getObtainedMarks());
				  												  else
				  													  cell.setCellValue(marks.getObtainedAndModerationMarks());
				  											  }else
				  												cell.setCellValue("AB");
				  										  }else
				  											cell.setCellValue(marks.getObtainedAndModerationMarks());
				  										  if(displayModerationMarks && !ObjectFunctions.isNullOrEmpty(moderationCellInfo) && marks.getModerationMarks() > 0){
				  											scheduleInfo = new StringBuffer(marks.getSubjectName()).append("_").append(marks.getExamTypeName());
				  											column = moderationCellInfo.get(scheduleInfo.toString().trim().toLowerCase());
				  											if(!ObjectFunctions.isNullOrEmpty(column)){
				  												cell = studentsMarksRow.createCell(column);
				  												cell.setCellStyle(styles.get("cell"));
				  												cell.setCellValue(marks.getModerationMarks());
				  											}
				  										  }
				  									  }
				  						    			marks = null;
				  								  }
					  						   }
					  						  if(!ObjectFunctions.isNullOrEmpty(studentDetailsCellInfo)){
				  								  for (Map.Entry<Integer, String> entry : studentDetailsCellInfo.entrySet()) {
				  									  try {
				  									      fieldValue = student.getClass().getDeclaredMethod(entry.getValue(), null).invoke(student, null);
				  								    	  cellInfo = entry.getValue();
				  								    	  if(StringFunctions.isNotNullOrEmpty(cellInfo)){
				  								    		  cell = studentsMarksRow.createCell(entry.getKey());
				  								    		  cell.setCellStyle(styles.get("string"));
				  											  if (ObjectFunctions.isNullOrEmpty(fieldValue)) {
				  												cell.setCellValue("");
				  											  } else if (fieldValue instanceof String) {
				  												cell.setCellValue(fieldValue.toString());
				  											  } else if (fieldValue instanceof Date || fieldValue instanceof Timestamp) {
				  												cell.setCellStyle(styles.get("date"));
				  												cell.setCellValue((Timestamp) fieldValue);
				  											  } else if (fieldValue instanceof Double) {
				  												cell.setCellStyle(styles.get("cell"));
				  												cell.setCellValue((Double) fieldValue);
				  											  }else if( fieldValue instanceof Integer ){
				  												  cell.setCellStyle(styles.get("cell"));
				  												  cell.setCellValue((Integer)fieldValue);
				  											  } else if(fieldValue instanceof Long){
				  												  cell.setCellStyle(styles.get("cell"));
				  												  cell.setCellValue((Long)fieldValue);
				  											  }	else{
				  												cell.setCellValue(fieldValue.toString());
				  											  }
				  								    	  }
				  									  } catch (NoSuchMethodException x) {
				  										    x.printStackTrace();
				  									  } catch (IllegalAccessException x) {
				  										    x.printStackTrace();
				  									  } catch (InvocationTargetException x) {
				  										    x.printStackTrace();
				  									  }
				  								  }
					  						  }
					  						  if(!ObjectFunctions.isNullOrEmpty(formulasCellInfo)){
					  							 for (Map.Entry<Integer, String> entry : formulasCellInfo.entrySet()) {
					  								 fileName = entry.getValue();
					  								 fileName = fileName.replaceAll("--rowNum--", String.valueOf(studentsMarksRow.getRowNum()+1));
					  								 cell = studentsMarksRow.createCell(entry.getKey());
					  								 cell.setCellStyle(styles.get("cell"));
					  								 cell.setCellFormula(fileName);
					  							 }
					  						  }
					  						marksWritableStartRow++;
					  					}
			  						}
					  					academicYear = null;
					  					endDate = null;
						  				studAtt = null;
						  				studentsMarksRow = null;
						  				column =null;
						  				studentMarks = null;
			  							cellInfo = null;
			  					}
							 }
							  templateFile.close();
							  workbook.getCreationHelper().createFormulaEvaluator().evaluateAll();
			  				  	if(workbook.getSheetIndex("Config") != -1)
			  				  		workbook.setSheetHidden(workbook.getSheetIndex("Config"), 2);
							  templateFile = null;
							  styles = null;
						}
					}
						studentsPromotionReport = null;
						scoreCardAcademicfile = null;
					}else{
						// No template Found.
						workbook = new XSSFWorkbook();
						sheet = workbook.createSheet();
						sheet.createRow(1).createCell(0).setCellValue("Promotion Report Template not found. Please add template.");
					}
			  		//FileOutputStream outFile =new FileOutputStream(scoreCardAcademicfile);
				   if(!ObjectFunctions.isNullOrEmpty(workbook)){
					   workbook.write(getResponse().getOutputStream());
				    }
					workbook = null;
					sheet = null;
					query = null;
					studentsList = null;
					scoreCardAcademicfile = null;
					schedulesCellInfo = null;
					moderationCellInfo = null;
					studentDetailsCellInfo =  null;
					formulasCellInfo =  null;
					schedulesCellInfo = null;
					cell = null;
					fieldValue = null;
				}
			}catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
		}
  /********************************************************************
   * Date              Name               Description
   * ========          ============       ==================
   * August 19, 2013   Seshu		      Display all student activities.
  /********************************************************************/   
  @Actions({
 		@Action(value = "ajaxManageStudentActivities", results = { @Result(location = "admin/ajaxManageStudentActivities.jsp", name = "success" )}),
 		@Action(value = "ajaxViewStudentActivities", results = { @Result(location = "admin/ajaxViewStudentActivities.jsp", name = "success" )})
 	})
 	public String ajaxManageStudentActivities() throws URTUniversalException {
 		if (log.isDebugEnabled()) {
 			log.debug("Entering 'ajaxManageStudentActivities' method");
 		}
 		try
 		{
 			StringBuffer sql = new StringBuffer("custId=").append(getUserCustId()).append(" and academicYearId=").append(getUserAcademicYearId());
 			setObjectList(adminManager.getAll(StudentActivities.class, sql.toString()));
 			sql = null;
 		}
 		catch(Exception ex)
 		{
 			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
 		}
 		return SUCCESS;
 	}
  /********************************************************************
   * Date              Name               Description
   * ========          ============       ==================
   * April 26, 2013	   Venkatesh		  Removed PrepareAcademicYearId and used getUserAcademicYearId()	
   * August 20, 2013   Seshu		      Changed method from AdminAction to ExamAction. Changed method name 'ajaxDoSendStudentMarksToMobile' to ajaxDoViewClassesHaveMarks
  /********************************************************************/   
  @Actions( { @Action(value = "ajaxDoViewClassesHaveMarks", results = { @Result(location = "admin/ajaxDoSendStudentMarksToMobile.jsp", name = "success") }),
	  		  @Action(value = "ajaxDoViewClassesTeacherHaveMarks", results = { @Result(location = "admin/ajaxDoSendStudentMarksToMobile.jsp", name = "success") })})
   public String ajaxDoViewClassesHaveMarks() {
	   if (log.isDebugEnabled()) {
		   log.debug("Entering 'ajaxDoViewClassesHaveMarks' method");
	   }
	   try {
		   getSmsCount();
		   if(getUser().isOnlySchoolHod()){
			   HashSet<StudyClass> classSections = new HashSet<StudyClass>();
				  Staff staff = (Staff)adminManager.get(Staff.class,"accountId="+getUser().getId());
				  if(!ObjectFunctions.isNullOrEmpty(staff)){
					 /* List<ClassTeacher> teachersList = adminManager.getAll(ClassTeacher.class,"teacherId="+staff.getId()+" and custId="+getUserCustId()+" and academicyearId="+getUserAcademicYearId()+" and classTeacher='Y'");
					  if(!ObjectFunctions.isNullOrEmpty(teachersList)){
						  for (ClassTeacher teacher : teachersList){
							  classSections.add(teacher.getStudyClass());
							  //String studyId = String.valueOf(teacher.getStudyClass().getId());
							  //log.debug();
							  setTempId(teacher.getStudyClass().getId());
						  }
					  }*/
					  List studyClassesList = getHodStudyClassesList(staff.getId(),getUserAcademicYearId());
						if(!ObjectFunctions.isNullOrEmpty(studyClassesList))
						{
							classSections.addAll(studyClassesList);
						}
						if (ObjectFunctions.isNotNullOrEmpty(classSections)) {
							setStudyClassList(ConvertUtil.convertSetToList(classSections));
							Collections.sort(getStudyClassList());
						}
						studyClassesList = null;
						ajaxDoGetExamTypesForThisClass();
				  }
				 
			}else if(getUser().isAdminCoordinator()){
				   HashSet<StudyClass> classSections = new HashSet<StudyClass>();
					  Staff staff = (Staff)adminManager.get(Staff.class,"accountId="+getUser().getId());
					  if(!ObjectFunctions.isNullOrEmpty(staff)){
						  List studyClassesList = getAdminCoordinatorStudyClassesList(staff.getId(),getUserAcademicYearId());
							if(!ObjectFunctions.isNullOrEmpty(studyClassesList))
							{
								classSections.addAll(studyClassesList);
							}
							if (ObjectFunctions.isNotNullOrEmpty(classSections)) {
								setStudyClassList(ConvertUtil.convertSetToList(classSections));
								Collections.sort(getStudyClassList());
							}
							studyClassesList = null;
							ajaxDoGetExamTypesForThisClass();
					  }
				}
		   else if(getUser().isSchoolTeacher()){
			   ClassTeacher classTeacher = staffManager.getClassTeacherByAccountId(getUser().getId(), getUserAcademicYearId());
			   if(!ObjectFunctions.isNullOrEmpty(classTeacher)){
				   setStudyClass(classTeacher.getStudyClass());
				   setTempId(getStudyClass().getId());
				   ajaxDoGetExamTypesForThisClass();
			   }
		 }
		 else {
			  /* StringBuffer query = new StringBuffer("custId=").append(getUserCustId()).append(" and academicYearId=").append(getUserAcademicYearId()).append(" order by sortingOrder");
			   setClassList(adminManager.getAll(ClassName.class, query.toString()));
			   ClassName className = (ClassName) getClassList().get(0);*/
			   checkStudyClassHavingStudentsOrNot();
			   StudyClass studyClass = getStudyClassList().get(0);
			   setTempId(studyClass.getId());  
		   }
		   
	   } catch (Exception ex) {
		   ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		   JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
	   } 
	   return SUCCESS;
   }
  /********************************************************************
   * Date              Name               Description
   * ========          ============       ==================
   * August 20, 2013   Seshu		      Changed method from AdminAction to ExamAction.
  /********************************************************************/   
  @Actions( { @Action(value = "ajaxDoGetExamTypesForThisClass", results = { @Result(location = "admin/ajaxDoViewClassesAndExamTypes.jsp", name = "success") }) })
  public String ajaxDoGetExamTypesForThisClass() {
	   if (log.isDebugEnabled()) {
		   log.debug("Entering 'ajaxDoGetExamTypesForThisClass' method");
	   }
	   try {
		   setOverAllGradesList(adminManager.getAll(OverAllGrades.class,"custId=" + getUserCustId()+" and academicYearId="+ getUserAcademicYearId()));
		   if(ObjectFunctions.isNullOrEmpty(getOverAllGradesList())){
			   setAnyTitle("OverAllGrades");
		   }
		   if(getTempId() != 0){
			   List<ViewStudentMarksDetails> studentMarksDetails=adminManager.getStudentMarksDetailsByClassIdAndAcademicYearIdAndCustId(getUserCustId(),getUserAcademicYearId(),getTempId());
				  if(ObjectFunctions.isNotNullOrEmpty(studentMarksDetails))
				   setViewStudentMarksDetailsList(studentMarksDetails);
			   /*String clause ="custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and classSectionId="+getTempId()+" and totalSubjectMarksObtained!=0.0 group by examTypeId";
			   setViewStudentMarksDetailsList(adminManager.getAll(ViewStudentMarksDetails.class,clause));*/
		   }
	   } catch (Exception ex) {
		   ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		   JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
	   } 
	   return SUCCESS;
  }
  /********************************************************************
   * Date              Name               Description
   * ========          ============       ==================
   * May 05, 2013      Balu		      	  Modified by Balu
   * April 20. 2013    Seshu			  This methos moved form AdminAction to ExamAction
  /********************************************************************/   
	@Override
	@Actions( { @Action(value = "ajaxDoSendMarksToMobile", results = { @Result(location = "admin/ajaxDoSendStudentMarksToMobile.jsp", name = "success") }) })
	public String ajaxDoSendMarksToMobile() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDoSendMarksToMobile' method");
		}
		try {
			if(!ObjectFunctions.isNullOrEmpty(getAnyId())){
				log.debug("selected Ids" +getAnyId());
				HashMap keys = new HashMap();
				keys.put("custId", getUserCustId());
				keys.put("classId",getTempId());
				keys.put("examTypeId",getTempId1());
				keys.put("userId",getUser().getId());
				keys.put("gradeType", getSelectedId());
				
				SendMarksToMobileThread R1 = new SendMarksToMobileThread(keys, getCurrentAcademicYear(), getAnyId());
				 R1.start();
				//String msg = communicationManager.sendMarksToUsers(keys, getCurrentAcademicYear(),getAnyId());
				//super.addActionMessage(msg);
						
				super.addActionMessage("Marks has been delivered successfully");
				ajaxDoViewClassesHaveMarks();
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
/********************************************************************
 * Date              Name               Description
 * ========          ============       ==================
 * Oct 14, 2013      Seshu		      	For displaying student latest two examtypes marks through charts
/********************************************************************/   
	@Actions( { @Action(value = "ajaxGetStudentPerformance", results = { @Result(type = "json", name = "success", params = {
			"includeProperties", "myStudents" }) }) })
 	public String ajaxGetStudentPerformance() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxGetStudentPerformance' method");
		}
		try {
			long start = System.currentTimeMillis();
			if(getStudent().getId() > 0){
				List<StudentMarks> latestUploadedExamTypes= adminManager.getLatestUploadedStudentsMarks(getStudent().getClassSection().getId(), getUserAcademicYearId(), 2);
				if(ObjectFunctions.isNotNullOrEmpty(latestUploadedExamTypes)){
					List<ViewStudyClassSubjects> classSubjects=adminManager.getAllStudyClassSubjects("studyClassId="+getStudent().getClassSection().getId()+" and custId="+getUserCustId());
					if(ObjectFunctions.isNotNullOrEmpty(classSubjects)){
    					Collections.sort(classSubjects,new ViewStudyClassSubjectsComparator());  
						genearateJsonDataForStudentTermWiseMarks(latestUploadedExamTypes,classSubjects);
					}
					classSubjects=null;
				}
				latestUploadedExamTypes = null;
			}
			long end = System.currentTimeMillis();
			log.debug("Method execution time : "+((end -start)/1000)+" Seconds");
		}
	 catch (Exception ex) {
		ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	}
		return null;
	}
	
/********************************************************************
 * Date              Name               Description
 * ========          ============       ==================
 * Oct 14, 2013      Seshu		      	For displaying student latest two examtypes marks through charts for student or parent.
/********************************************************************/   
	@Actions({ @Action(value = "ajaxTermsWiseStudentMarksComparison", results = { })})
	public String ajaxTermsWiseStudentMarksComparison() {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxTermsWiseStudentMarksComparison' method");
		}
		try {
			if(getTempId() > 0){
				setStudent((Student)adminManager.get(Student.class,getTempId()));
			}else{
				setStudent(studentManager.getStudentByAccountId(getUser().getId(), getUserAcademicYearId(), getUserCustId()));
			}
			if(!ObjectFunctions.isNullOrEmpty(student)){
				List<StudentMarks> latestStudentMarks= adminManager.getLatestUploadedStudentsMarks(getStudent().getClassSection().getId(), getStudent().getAcademicYearId(), 0);
				if(ObjectFunctions.isNotNullOrEmpty(latestStudentMarks)){
					List<ViewStudyClassSubjects> classSubjects=adminManager.getAllStudyClassSubjects("studyClassId="+getStudent().getClassSection().getId()+" and custId="+getStudent().getCustId());
					if(ObjectFunctions.isNotNullOrEmpty(classSubjects)){
						Collections.sort(classSubjects,new ViewStudyClassSubjectsComparator());  
						genearateJsonDataForStudentTermWiseMarks(latestStudentMarks,classSubjects);
					}
					latestStudentMarks=null;
				}
			}
			setStudent(null);
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	 }
/********************************************************************
 * Date              Name               Description
 * ========          ============       ==================
 * Oct 14, 2013      Seshu		      	For displaying student academic performance through charts.
/********************************************************************/   
	 public void genearateJsonDataForStudentTermWiseMarks(List<StudentMarks> latestStudentMarks,List<ViewStudyClassSubjects> classSubjects){
			try{
				JSONObject ja = new JSONObject();
				JSONArray catSubjects=null;
				StringBuffer subTypeTool=null;
			//	JSONObject subTypeToolTipObj=null;
				List<StudentMarks> subTypes=null;
				JSONArray termArray=null;
				JSONObject termObj =null;
				StringBuffer query = null;
				float totalMarks=0;
				float percentage=0;
				float subMaxMarks;
				float moderationMarks=0;
				List<StudentMarks> studentMarks=null;
				ja.put("title", "Student Marks Comparison");
				JSONArray totalSeriesArray=new JSONArray();
				for(StudentMarks latestMarkstypes:latestStudentMarks){
						catSubjects=new JSONArray();
						termObj = new JSONObject();
						termArray=new JSONArray();
						Collections.sort(classSubjects);
						for(ViewStudyClassSubjects subject:classSubjects){
							  if(!ObjectFunctions.isNullOrEmpty(subject)){
								  query = new StringBuffer("SELECT marks FROM StudentMarks marks JOIN marks.examSchedule schedule WHERE marks.student=").append(getStudent().getId())
								  .append(" and schedule.examType=").append(latestMarkstypes.getExamTypeId()).append(" and schedule.classSectionSubject=").append(subject.getSubjectId()).append(" group by schedule.subType");
								  subTypes=adminManager.getAllHqlQuery(query.toString());
								catSubjects.put(subject.getSubjectName());
								if(ObjectFunctions.isNotNullOrEmpty(subTypes)){
									totalMarks=0;
									moderationMarks=0;
									subTypeTool=new StringBuffer();
									subMaxMarks=0;
									for(StudentMarks examDetails:subTypes){
										subMaxMarks+=examDetails.getSubTypeMaxMarks();
										query = new StringBuffer("SELECT marks FROM StudentMarks marks JOIN marks.examSchedule schedule WHERE marks.student=").append(getStudent().getId())
										  .append(" and schedule.examType=").append(latestMarkstypes.getExamTypeId()).append(" and schedule.subType=").append(examDetails.getSubTypeId())
										  .append(" and schedule.classSectionSubject=").append(subject.getSubjectId());
										studentMarks=adminManager.getAllHqlQuery(query.toString());
										if(!ObjectFunctions.isNullOrEmpty(studentMarks)){
											for(StudentMarks marks : studentMarks){
												if(marks.isPresent()){
													totalMarks+=marks.getObtainedMarks()+marks.getModerationMarks();
													subTypeTool.append("<b>"+examDetails.getSubTypeName()+" : </b>"+marks.getObtainedMarks()+"<br/>");
													moderationMarks+=marks.getModerationMarks();
												}else
													subTypeTool.append("<b>"+examDetails.getSubTypeName()+" : </b> Absent"+"<br/>");
											}
										}
									}
									percentage=(totalMarks/subMaxMarks)*100;
									if(moderationMarks > 0)
										subTypeTool.append("<b>Moderation Marks : </b>"+moderationMarks+"<br/>");
									subTypeTool.append("<b>MaxMarks : </b>"+subMaxMarks);
									subTypeTool.append("<br/>"+"<b>Total : </b>"+totalMarks);
									subTypeTool.append("<br/>"+"<b>Percentage : </b>"+roundTwoDecimals(percentage));
									termArray.put(roundTwoDecimals(percentage));
									ja.put(latestMarkstypes.getExamTypeName()+subject.getSubjectName(),subTypeTool.toString());
								}else{
									termArray.put(0);
									
								}
							  }
							}
							termObj.put("name",latestMarkstypes.getExamTypeName());
							termObj.put("data",termArray);
							totalSeriesArray.put(termObj);
				}
				ja.put("series", totalSeriesArray);
				ja.put("categories", catSubjects);
				log.debug("ja:"+ja.toString());
			    getResponse().getOutputStream().print(ja.toString());
			    query = null;
			}catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
		}
/********************************************************************
 * Date              Name               Description
 * ========          ============       ==================
 * Oct 14, 2013      Seshu		      	For displaying recently uploaded student marks.
/********************************************************************/   
	@Actions({
			@Action(value = "ajaxGetLatestStudentMarks", results = {  @Result(type = "json", name = "success", params = {"includeProperties","wishDescription" }) })
		})
			public String ajaxGetLatestStudentMarks() throws URTUniversalException {
			
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxGetLatestStudentMarks' method");
			}
			try
			{
				Student student = null;
				StringBuffer query = null;
				if(getTempId() > 0){
					student = (Student)adminManager.get(Student.class, getTempId());
				}else{
					if(getUserAcademicYearId() > 0 && getUser().getId() > 0){
						student = (Student)studentManager.get(Student.class,"accountId="+getUser().getId()+" and academicYearId="+getUserAcademicYearId()+" and custId="+getUserCustId()+" and description is null");
					}
				}
					if(!ObjectFunctions.isNullOrEmpty(student)){
						long classSectionId=student.getClassSectionId();
						long custId=student.getCustId();
						long studId = student.getId();
						student = null;
						List<StudentMarks> latestStudentMarks=adminManager.getLatestUploadedExamTypesByStudentId(studId, 1);
						//List<ViewStudentMarksDetails> latestStudentMarks=adminManager.getAll(ViewStudentMarksDetails.class,"studId="+studId+" and  examDate is not null group by examType order by lastUpdatedDate DESC");
						
						if(ObjectFunctions.isNotNullOrEmpty(latestStudentMarks)){
							StudentMarks studentMarksExamType=latestStudentMarks.get(0);
							List<ViewStudyClassSubjects> classSubjects=adminManager.getAllStudyClassSubjects("studyClassId="+classSectionId+" and custId="+custId);
							Collections.sort(classSubjects,new ViewStudyClassSubjectsComparator());
							if(ObjectFunctions.isNotNullOrEmpty(classSubjects)){
								query = new StringBuffer("SELECT marks FROM StudentMarks marks JOIN marks.examSchedule es WHERE  marks.student=").append(studId)
								.append(" and es.examType=").append(studentMarksExamType.getExamTypeId()).append("  group by es.subType");
								List<StudentMarks> subTypes=adminManager.getAllHqlQuery(query.toString());
								//List<ViewStudentMarksDetails> subTypes=adminManager.getAll(ViewStudentMarksDetails.class,"studId="+studId+" and examTypeId="+studentMarksExamType.getExamTypeId()+" group by subTypeId");
								if(ObjectFunctions.isNotNullOrEmpty(subTypes)){
									List<StudentMarks> studentMarks=null;
									JSONObject ja = new JSONObject();
									JSONArray catSubjects=null;
									JSONArray termArray=null;
									JSONObject termObj =null;
									JSONArray totalSeriesArray=new JSONArray();
									ja.put("title", studentMarksExamType.getExamTypeName()+" Marks");
									ja.put("subtitle", "Recently conducted exam marks.");
									ja.put("max", studentMarksExamType.getExamSchedule().getExamTypeMaxMarks());
									for(StudentMarks examDetails:subTypes){
										termObj = new JSONObject();
										catSubjects=new JSONArray();
										termArray=new JSONArray();
										for(ViewStudyClassSubjects subject:classSubjects){
												catSubjects.put(subject.getSubjectName());
												query = new StringBuffer("SELECT marks FROM StudentMarks marks JOIN marks.examSchedule schedule WHERE marks.student=").append(studId)
												  .append(" and schedule.examType=").append(studentMarksExamType.getExamTypeId()).append(" and schedule.subType=").append(examDetails.getSubTypeId())
												  .append(" and schedule.classSectionSubject=").append(subject.getSubjectId());
												studentMarks=adminManager.getAllHqlQuery(query.toString());
												//studentMarks=adminManager.getStudentMarksByStudIdAndExamTypeIdAndSubTypeIdAndSubjectId(studId,Long.valueOf(studentMarksExamType.getExamTypeId()),examDetails.getSubTypeId(),subject.getSubjectId());
												if(!ObjectFunctions.isNullOrEmpty(studentMarks)){
													for(StudentMarks marks : studentMarks){
														if(marks.isPresent()){
															termArray.put(marks.getObtainedMarks()+marks.getModerationMarks());
														}else
															termArray.put(0);
													}
												}else{
													termArray.put(0);
												}
												studentMarks=null;
										 }
										termObj.put("name",examDetails.getSubTypeName());
										termObj.put("data",termArray);
										totalSeriesArray.put(termObj);
									}
									ja.put("series", totalSeriesArray);
									ja.put("categories", catSubjects);
									log.debug("ja:"+ja.toString());
								    getResponse().getOutputStream().print(ja.toString());
								}
							}
						}
					}
			}
			catch(Exception ex)
			{
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return null;
		}

	/********************************************************************
		 * Date              Name               Description
		 * ============      =======		    ==================
		 * Dec 11, 2013      Seshu		    	Validating whether system generated students certificates.
		/********************************************************************/
		@Actions( { @Action(value = "ajaxSearchStudentForScoreCard", results = { @Result(location = "scorecard/ajaxViewScoreCardSearchStudentList.jsp", name = "success") }) })
		public String ajaxSearchStudentForScoreCard()  throws URTUniversalException {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxSearchStudentForScoreCard' method");
			}
			try
			{
				StringBuffer queryString= new StringBuffer("custId=").append(getUserCustId()).append(" and academicYearId=").append(getUserAcademicYearId());
				if(StringFunctions.isNotNullOrEmpty(getAnyTitle()) || StringFunctions.isNotNullOrEmpty(getSelectedId())){
					if(StringFunctions.isNotNullOrEmpty(getAnyTitle())){
						if(StringFunctions.isNotNullOrEmpty(getParamValue("classAndSection"))){
							queryString.append(" and (firstName like '%"+ getAnyTitle() + "%' or lastName like '%"+ getAnyTitle()+ "%') and classNameAndSection='"+getParamValue("classAndSection")+"'");
						}else{
							queryString.append(" and (firstName like '%"+ getAnyTitle() + "%' or lastName like '%"+ getAnyTitle()+ "%') ");
						}
					}else if(StringFunctions.isNotNullOrEmpty(getSelectedId())){
						if(StringFunctions.isNotNullOrEmpty(getParamValue("classAndSection"))){
							queryString.append(" and (admissionNumber like '%"+ getSelectedId()+ "%') and classNameAndSection='"+getParamValue("classAndSection")+"'");
						}else{
							queryString.append(" and (admissionNumber like '%"+ getSelectedId()+ "%') ");
						}
					}
					queryString.append(" and description is null").append(" order by sortingOrder,section");
					setViewStudentPersonAccountDetailsList(adminManager.getAll(ViewStudentPersonAccountDetails.class,queryString.toString()));
				}
				if(ObjectFunctions.isNotNullOrEmpty(getViewStudentPersonAccountDetailsList())){
					ViewStudentPersonAccountDetails tempObj=null;
					for(ViewStudentPersonAccountDetails stud : getViewStudentPersonAccountDetailsList()){
						List<ExamTypes> examTypesList = null;
						StudyClass studyClass = (StudyClass)adminManager.get(StudyClass.class, "id="+stud.getClassSectionId()+" and custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId());
						if(!ObjectFunctions.isNullOrEmpty(studyClass))
							examTypesList = ConvertUtil.convertSetToList(studyClass.getExamTypes());
								
						/*if(!ObjectFunctions.isNullOrEmpty(studyClass) && !ObjectFunctions.isNullOrEmpty(studyClass.getClassNameClassId()) 
								&& ObjectFunctions.isNotNullOrEmpty(studyClass.getClassNameClassId().getExamTypes()))
							examTypesList = ConvertUtil.convertSetToList(studyClass.getClassNameClassId().getExamTypes());*/
						studyClass = null;
						if(!ObjectFunctions.isNullOrEmpty(examTypesList)){
						if(examTypesList.size() >0){
							HashMap<String,String>  scorecardfileDetails = null;
							//scorecardfileDetails =  validateStudentScoreCardFileExistOrNot(stud.getFullName(),stud.getAdmissionNumber(),stud.getClassSectionId(),stud.getCustomerShortName(),examTypesList);
								scorecardfileDetails =  validateStudentScoreCardFileExistOrNot(stud.getClassSectionId(),examTypesList,stud.getAccountId());
								  if(!ObjectFunctions.isNullOrEmpty(scorecardfileDetails)){
										stud.setPresent(true);
										for(Map.Entry<String,String> fileDetails : scorecardfileDetails.entrySet()){
											tempObj=new ViewStudentPersonAccountDetails();
											tempObj.setAnyString(fileDetails.getValue());
											tempObj.setTypeOfExam(fileDetails.getKey());
											tempObj.setStudentId(stud.getStudentId());
											getTempList().add(tempObj);
											tempObj = null;
										}
										//getTempList().addAll(getObjectList());
										//setTempList(getObjectList());
									scorecardfileDetails = null;
								}
						}
						}else{
							HashMap<Boolean,String>  fileDetails = null;
							 fileDetails =  validateStudentFileExistOrNot(stud.getFullName(),stud.getAdmissionNumber(),stud.getClassSectionId(),stud.getCustomerShortName());
							 if(!ObjectFunctions.isNullOrEmpty( fileDetails)){
								for(Map.Entry<Boolean,String> fileDetail : fileDetails.entrySet()){
										stud.setAnyString(fileDetail.getValue());
										stud.setPresent(fileDetail.getKey());
										fileDetail = null;
									}
									fileDetails = null;
								}
						}
					}
				}
			}
			catch(Exception ex)
			{
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return SUCCESS;
		}
	//This is for sending SMS from excel sheet. 
	/*@Action(value = "ajaxUploadMarksSheetForSendingMarksToParents", results = { @Result(location = "admin/ajaxUploadMarksExcelSheet.jsp", name = "success") })
	public String ajaxUploadMarksSheetForSendingMarksToParents(){
		try{
  			FileInputStream file = new FileInputStream(getUpload());
  		    XSSFWorkbook workbook = new XSSFWorkbook(file);
  		    XSSFSheet sheet = null;
  		    Row row = null;
  		    int colmnSize = 0;
  		    Cell cell = null;
  		    StringBuffer smsContent = null;
  		    Row subjectsWithTotalMarksRow = null;
  		    StringBuffer marksCont = new StringBuffer();
  		    Messages message =null;
  		    Set<String> mobileNumber = null; 
  		    Customer customer = getCustomerByCustId();
  		    if(!ObjectFunctions.isNullOrEmpty(customer) && customer.isCheckMobileService()){
  		    for(int sheetNum=0; sheetNum < workbook.getNumberOfSheets(); sheetNum++){
  		    	sheet = workbook.getSheetAt(sheetNum);
  		    	subjectsWithTotalMarksRow= sheet.getRow(3);
  		    	if(!ObjectFunctions.isNullOrEmpty(subjectsWithTotalMarksRow)){
  		    	for(int rowNum=4;rowNum<6;rowNum++){
  		    		row = sheet.getRow(rowNum);
  		    		if(!ObjectFunctions.isNullOrEmpty(row.getCell(1)) && !ObjectFunctions.isNullOrEmpty(row.getCell(2))){
  		    			colmnSize = row.getLastCellNum();
	   	    			 for(int colNum=1; colNum < colmnSize ;colNum++)
	   	    			 {
	   	    				 cell = row.getCell(colNum); 
	   	    				 if(!ObjectFunctions.isNullOrEmpty(cell) && cell.getCellType() != HSSFCell.CELL_TYPE_BLANK){
	   	    					 if(colNum == 1){
	   	    						marksCont = new StringBuffer();
	   	    						marksCont.append(cell.getStringCellValue()).append(", marks are ");
	   	    					 }else if(colNum == 2){
	   	    						mobileNumber = new HashSet<String>();
	   	    						cell.setCellType(Cell.CELL_TYPE_STRING);
	   	    						mobileNumber.add(cell.getStringCellValue());
	   	    					 }else if(!ObjectFunctions.isNullOrEmpty(marksCont)){
	   	    						marksCont.append(subjectsWithTotalMarksRow.getCell(colNum).getStringCellValue()).append("=");
	   	    						if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
	   	    							marksCont.append(cell.getNumericCellValue()).append(", ");
		   	    					 }else if(cell.getCellType() == HSSFCell.CELL_TYPE_STRING){
		   	    						marksCont.append(cell.getStringCellValue()).append(", "); 
		   	    					 }else if(cell.getCellType() == HSSFCell.CELL_TYPE_FORMULA){
		   	    						 switch (cell.getCachedFormulaResultType()) {
			   	    			            case HSSFCell.CELL_TYPE_NUMERIC:
			   	    			            	marksCont.append(cell.getNumericCellValue()); 
			   	    			            	break;
			   	    			            case HSSFCell.CELL_TYPE_STRING: 
			   	    			            	marksCont.append(cell.getStringCellValue().replaceAll("'", ""));
			   	    			            	break;
			   	    			        }
		   	    						 if(colNum == (colmnSize-1))
		   	    							marksCont.append(". ");
		   	    						 else
		   	    							marksCont.append(", ");
		   	    					 }  
	   	    					 }
	   	    				 }
	   	    			 }
	   	    			 if(!ObjectFunctions.isNullOrEmpty(marksCont) && !ObjectFunctions.isNullOrEmpty(mobileNumber)){
	   	    				marksCont.append("From exam department Thank you Principal from ");
	   	    				//log.debug(marksCont.toString().replace("\n", ""));
	   	    				message = new Messages();
	   	    				message.setCustomer(customer);
	    					message.setMessageDescription("Dear Parents, IST PREBOARD Examination results are published for "+marksCont.toString().replace("\n", ""));
	   	    				deliverSms(message, mobileNumber);
	   	    				message =null;
	   	    			 }
						
  		    		}
  		    		mobileNumber = null;
  		    		marksCont = null;
	   	    		}
  		    	}
    				sheet = null;
   	    	}
  		    }	
		}catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}*/
	public HashSet<String> monthsBetweenTwoDates(Date startDate, Date endDate) {
		 HashSet<String> aListMonth = new HashSet<String>();
		 List<Date> dates = new ArrayList<Date>();
    //	DateFormat formatter ; 
    	//formatter = new SimpleDateFormat("dd/MM/yyyy");
    	long interval = 24*1000 * 60 * 60; // 1 hour in millis
    	long endTime =endDate.getTime() ; // create your endtime here, possibly using Calendar or Date
    	long curTime = startDate.getTime();
    	while (curTime <= endTime) {
    	    dates.add(new Date(curTime));
    	    curTime += interval;
    	}
    	for(int i=0;i<dates.size();i++){
    	    Date lDate =dates.get(i);
    	 //   String ds = formatter.format(lDate);  
    	    long value=lDate.getMonth() ;
    		aListMonth.add(String.valueOf(value));
    	}
    	return aListMonth;
	 }
	@Actions( {
		@Action(value = "ajaxStaffExamSchedules", results = { @Result(location = "manageStaffExamSchedules.jsp", name = "success") }),
		@Action(value = "ajaxClassTeacherExamSchedules", results = { @Result(location = "ajaxClassTeacherExamSchedules.jsp", name = "success") }) })
	public String ajaxClassTeacherExamSchedules()  throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxStaffExamSchedules' method");
		}
		try {
			List examschedulesList = null;
			HashSet<StudyClass> classSections = new HashSet<StudyClass>();
			if(getUser().isSchoolPrincipal() || getUser().isSchoolDirector()){
				examschedulesList = adminManager.getUsersStartAndEndDateExamSchedulesDetails(0,getUserAcademicYearId(),getAnyTitle(),false,0,0);
				if(!ObjectFunctions.isNullOrEmpty(examschedulesList)){
					classSections.addAll(examschedulesList);
				}
				if(ObjectFunctions.isNotNullOrEmpty(classSections)){
					setExamScheduleList(ConvertUtil.convertSetToList(classSections));
					//Collections.sort(getExamScheduleList());
				}
			}else{
				 examschedulesList=adminManager.getUsersStartAndEndDateExamSchedulesDetails(getUser().getId(),getUserAcademicYearId(),getAnyTitle(),true,0,0);
				 if(!ObjectFunctions.isNullOrEmpty(examschedulesList)){
						classSections.addAll(examschedulesList);
					}
				 if(getUser().isOnlySchoolHod()){
					 if(getUser().getId() > 0 )
						{
							setStaff(adminManager.getStaffByAcountId(getUser().getId(),Constants.YES_STRING));
						}
					 StringBuffer sqlQuery = new StringBuffer("select st.id,st.className from staffMultipleStudyClasses sm LEFT JOIN studyClass st  on (st.id=sm.studyClassId)") 
				    	.append(" where st.custId=").append(getUserCustId()).append(" and st.academicYearId="+getUserAcademicYearId()).append(" and sm.staffId=").append(getStaff().getId());
						List<Object[]> studyclassesList=staffManager.getAll(sqlQuery.toString());
					if(!ObjectFunctions.isNullOrEmpty(studyclassesList))
					{
						StringBuffer classSectionId = new StringBuffer();
						classSectionId.append("(");
						for(Object[] obj : studyclassesList){
							classSectionId.append(obj[0]);
							classSectionId.append(",");
						}
						classSectionId.append("0)");
						StringBuffer query = null;
						query = new StringBuffer("classSectionId in").append(classSectionId.toString());
						if("completed".equalsIgnoreCase(getAnyTitle()))
							query.append(" and endDate < '").append(DateFormatter.formatDate(DateFormatter.CCYY_MM_DD_PATTERN, new Date()));
						else
							query.append(" and endDate >= '").append(DateFormatter.formatDate(DateFormatter.CCYY_MM_DD_PATTERN, new Date()));
						query.append("' group by classSectionId,examTypeId");
						examschedulesList = adminManager.getAll(ViewExamScheduleDetails.class,query.toString());
						classSections.addAll(examschedulesList);
						classSectionId = null;
						 query = null;
					}
				}
				if (ObjectFunctions.isNotNullOrEmpty(classSections)) {
					setExamScheduleList(ConvertUtil.convertSetToList(classSections));
					Collections.sort(getExamScheduleList(),new ExamSchedulesStartDateComparator());
				}
			}
			setTempBoolean(adminManager.isUserAsClassTeacher(getUser().getId(),0,getUserAcademicYearId()));
			ajaxGetStaffStudyClasses();
			examschedulesList = null;
			classSections = null;
		}
		
		/*try
		{
			setTempBoolean(adminManager.isUserAsClassTeacher(getUser().getId(),0,getUserAcademicYearId()));
			ajaxGetStaffStudyClasses();
		}*/
		catch(Exception ex)
		{
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	    @Actions( { @Action(value = "ajaxAddStudentAssessment",  results = { @Result(location = "admin/ajaxViewStudentAssessments.jsp", name = "success" )}) })
		public String ajaxAddStudentAssessment() throws URTUniversalException {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxAddStudentActivity' method");
			}
			try
			{
				AcademicYear academicYear = getCurrentAcademicYear();
				StudentsAssessments assessmnetName = null;
				StringBuffer query = null;
				if(academicYear.getId() >0 && !StringFunctions.isNullOrEmpty(getStudentsAssessments().getAssessmentName()) && getStudentsAssessments().getId() ==0 ){		
					 query = new StringBuffer(" assessmentName='").append(getStudentsAssessments().getAssessmentName().trim()).append("' and custId=").append(getUserCustId()).append(" and academicYearId=").append(academicYear.getId());
					 assessmnetName =(StudentsAssessments)adminManager.get(StudentsAssessments.class,query.toString());
					 log.debug("query:"+query.toString());
					if(ObjectFunctions.isNullOrEmpty(assessmnetName)){
						assessmnetName = new StudentsAssessments();
						assessmnetName.setCreatedDate(new Date());
						assessmnetName.setCreatedById(getUser().getId());
					}else{
						assessmnetName =null;					
						super.addActionError(" This assessmnet already added for this academicyear.");
					}
				}
				if(getStudentsAssessments().getId() !=0){
					query = new StringBuffer(" assessmentName='").append(getStudentsAssessments().getAssessmentName().trim()).append("' and academicYearId =").append(academicYear.getId()).append(" and id !=").append(getStudentsAssessments().getId());
					assessmnetName = (StudentsAssessments)adminManager.get(StudentsAssessments.class,query.toString());
					log.debug("query:"+query.toString());
					if(ObjectFunctions.isNullOrEmpty(assessmnetName)){
						assessmnetName = (StudentsAssessments)adminManager.get(StudentsAssessments.class,getStudentsAssessments().getId());
					}
					else {
						assessmnetName = null;
						super.addActionError("This assissment already exist please provide another one.");
						
					}
				}						
			if(!ObjectFunctions.isNullOrEmpty(assessmnetName)){			
				assessmnetName.setAssessmentName(getStudentsAssessments().getAssessmentName().toUpperCase().trim());
				assessmnetName.setAcademicYear(academicYear);
				assessmnetName.setCreatedById(getUser().getId());
				assessmnetName.setCreatedDate(new Date());
				assessmnetName.setCustId(getUserCustId());
				assessmnetName.setLastAccessDate(new Date());
				assessmnetName.setLastUpdatedById(getUser().getId());
				assessmnetName.setLastUpdatedDate(new Date());
				if(StringFunctions.isNullOrEmpty(getStudentsAssessments().getAssessmentDescription().toUpperCase()))
					assessmnetName.setAssessmentDescription("");
				else
					assessmnetName.setAssessmentDescription(getStudentsAssessments().getAssessmentDescription());
				adminManager.save(assessmnetName);
				if(getStudentsAssessments().getId() >0){
					super.addActionMessage("Assessment updated successfully");
				}else
					super.addActionMessage("Assessment added successfully");
				assessmnetName= null;
				academicYear = null;
				}
				}
			catch(Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			finally{
				ajaxManageStudentAssessments();
			}
			return SUCCESS;
		}



	@Actions({
			@Action(value = "ajaxManageStudentAssessments", results = { @Result(location = "admin/ajaxViewStudentAssessments.jsp", name = "success" )})
	  })
		public String ajaxManageStudentAssessments() throws URTUniversalException {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxManageStudentAssessments' method");
			}
			try
			{
				StringBuffer sql = new StringBuffer("custId=").append(getUserCustId()).append(" and academicYearId=").append(getUserAcademicYearId());
				setObjectList(adminManager.getAll(StudentsAssessments.class, sql.toString()));
				sql = null;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return SUCCESS;
		}

	    @Actions( {
			@Action(value = "ajaxDoAddStudentAssessments", results = { @Result(location = "admin/ajaxDoAddStudentAssessments.jsp", name = "success") })
		})
		public String ajaxDoAddStudentAssessments() throws URTUniversalException{
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxDoAddStudentAssessments' method");
			}
			try {
				if(getStudentsAssessments().getId() > 0){
					setStudentsAssessments((StudentsAssessments)adminManager.get(StudentsAssessments.class,getStudentsAssessments().getId()));
				}else
					setStudentsAssessments(null);
			}catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return SUCCESS;
		}
	    @Actions( {
			@Action(value = "ajaxRemoveStudentAssessmentInfo", results = { @Result(location = "admin/ajaxViewStudentAssessments.jsp", name = "success") })
		})
		public String ajaxRemoveStudentAssessmentInfo() throws URTUniversalException{
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxRemoveStudentAssessmentInfo' method");
			}
			try {
				if(getStudentsAssessments().getId() > 0){
					StudentAcademicPerformance stuAcademicPer=(StudentAcademicPerformance) adminManager.get(StudentAcademicPerformance.class,"custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and studentsAssessmentId="+getStudentsAssessments().getId());
					if(!ObjectFunctions.isNullOrEmpty(stuAcademicPer)){
						super.addActionMessage("You can't able to remove, already marks assigned for "+getAnyTitle()+". ");
					}else{
						adminManager.remove(StudentsAssessments.class, getStudentsAssessments().getId());
						super.addActionMessage("Successfully removed Student Assessment details.");
					}
					ajaxManageStudentAssessments();
				}
			}catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return SUCCESS;
		}
	    
	    @Action(value = "ajaxGenerateScoreCardsEmailToParents", results = { @Result(location = "../admin/reports/ajaxViewCCReportStudents.jsp", name = "success")})
		public String ajaxGenerateScoreCardsEmailToParents() throws URTUniversalException {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxGenerateScoreCardsEmailToParents' method");
			}
			try {
			  setCustomer(getCustomerByCustId());
			  StringBuffer filePath = new StringBuffer("userfiles/ccer/"+getUserCustId()+"/").append(getUserAcademicYearId()+"/scorecards/").append(getParamValue("tempId")+"/").append(getClassId().toString()); 
					  //new StringBuffer("userfiles/ccer/"+getCustomerByCustId().getCustomerShortName()+"/").append(getCurrentAcademicYear().getAcademicYear()+"/").append(getClassId());
				boolean isSendEmail=false;
			  if(!StringFunctions.isNullOrEmpty(getSelectedId()))
				{
					String[] selectedStudents=getSelectedId().split(",");
					if(selectedStudents.length > 0)
					{
						if("true".equalsIgnoreCase(getParamValue("sendEmail")))
						{
							if(selectedStudents.length > 0)
							{
								for(int i=0;i<selectedStudents.length;i++){
									File file = new File(getSession().getServletContext().getRealPath(filePath+"/"+selectedStudents[i]+".pdf"));
									if(file.exists())
									{
										isSendEmail = sendCCReportEmailtoParent(file,selectedStudents[i]);
									}
									file=null;
								 }
							}
						}
					}
				}
			  if(isSendEmail)
				 super.addActionMessage("Email has been sent.");
			  else
				  super.addActionError("Email has not been sent.");
			  
			} catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			prepareStudentsDetailsList();
			return SUCCESS;
	  }
    public boolean sendCCReportEmailtoParent(File file,String accountId)
    {
    	if (log.isDebugEnabled()) {
			log.debug("Entering 'sendCCReportEmailtoParent' method");
		}
    	boolean sendEmailStatus=false;
	  	try {
	  		Object[] studentDetails = adminManager.get("select accountId,parentEmail,fullName from vw_studentClassDetails where custId="+getUserCustId() +" and academicYearId = " + getUserAcademicYearId() +" and accountId = " + accountId);
	  		if(!ObjectFunctions.isNullOrEmpty(studentDetails))
	  		{
	  			if(!ObjectFunctions.isNullOrEmpty(studentDetails[1]))
	  			{
	  				String[] emailAddresses = new String[1];
	  				String[] attachmentFiles = new String[1];
	  				attachmentFiles[0] = file.getAbsolutePath();
	  				emailAddresses[0] = studentDetails[1].toString();
	  				String body = "Please find your children "+studentDetails[2]+" score card report from the attachment.";
	  				MailUtil mailUtil=new MailUtil(emailAddresses, "Score Card Report", body,getUser() ,attachmentFiles,getContactFromEmail(getCustomerByCustId()));
	  				mailUtil.setContactEmail(getCustomerByCustId().getContactEmail());
	  				mailUtil.setContactPasword(getCustomerByCustId().getContactPassword());
	  				sendEmailStatus = mailUtil.sendEmailToFeeReceiptToParent(adminManager.getContactFromEmail(getCustomerByCustId()));
	  				mailUtil=null;
	  			}
	  		}
	  		studentDetails=null;
	  	} catch (DataAccessException ex) {
	  		ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	  	}
	  	return sendEmailStatus;
	  }
    @Actions( {@Action(value = "ajaxScoreCardTemplatesList", results = {@Result(location = "../admin/common/ajaxStudyClassListForScoreCard.jsp", name = "success") })
	   })
	public String ajaxScoreCardTemplatesList() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxScoreCardTemplatesList' method");
		}
		try {
			if(!ObjectFunctions.isNullOrEmpty(getTempId1())){//Here tempId1 is examTypeId
				HashSet<StudyClass> classSections = new HashSet<StudyClass>();
				StringBuffer  query2 = new StringBuffer("select classSectionId  FROM vw_studentExamMarks WHERE ");
				 query2.append(" custId="+getUserCustId());
				 query2.append(" and academicYearId=").append(getUserAcademicYearId());
				 query2.append(" and examTypeId="+getTempId1());
				 query2.append(" and  examDate is not null group by classSectionId");							 
				 List<BigInteger> classSectionIdList = adminManager.getAll(query2.toString());
					if(ObjectFunctions.isNotNullOrEmpty(classSectionIdList)){
						String classSectionIds = StringFunctions.convertListToCommaDelimitedString(classSectionIdList);
						 List<StudyClass> stdClassList =adminManager.getAll(StudyClass.class, "id in ("+classSectionIds+")");
						for(StudyClass stdClassObj1 : stdClassList){
							if(getUser().isOnlySchoolTeacher() || getUser().isOnlySchoolHod()){
								 List<ClassTeacher> teacherSubjetsList = null;
								Staff staff =adminManager.getStaffByAcountId(getUser().getId(),Constants.YES_STRING);
								if(getUser().isOnlySchoolTeacher())
									teacherSubjetsList = staffManager.getAll(ClassTeacher.class,"teacherId="+staff.getId()+" and academicYearId="+getUserAcademicYearId()+" and custId="+getUserCustId()+" and classTeacher='Y' and studyClassId="+stdClassObj1.getId());
	 							else
	 	 							teacherSubjetsList = staffManager.getAll(ClassTeacher.class,"teacherId="+staff.getId()+" and academicYearId="+getUserAcademicYearId()+" and custId="+getUserCustId()+" and studyClassId="+stdClassObj1.getId());
	 							if (!ObjectFunctions.isNullOrEmpty(teacherSubjetsList)) {
	 								for(ClassTeacher teacher : teacherSubjetsList){
	 									 if(teacher.getStudyClass().getFilledSeats() > 0)	
	 										classSections.add(teacher.getStudyClass());
	 								}
	 							}
	 							if(getUser().isOnlySchoolHod()){
	 								List studyClassesList = getHodStudyClassesList(staff.getId(),getUserAcademicYearId());
	 								if(!ObjectFunctions.isNullOrEmpty(studyClassesList))
	 								{
	 									classSections.addAll(studyClassesList);
	 								}
	 								studyClassesList = null;
	 							}
							}else{
							 if(stdClassObj1.getFilledSeats() > 0)	
							  classSections.add(stdClassObj1);
							}
							//}
						 //}
						}
						classSectionIds = null;
					}
					if (ObjectFunctions.isNotNullOrEmpty(classSectionIdList)) {
						setStudyClassList(ConvertUtil.convertSetToList(classSections));
					}
					if(!ObjectFunctions.isNullOrEmpty(getStudyClassList()))
						Collections.sort(getStudyClassList());
				}
			
			
			/*HashSet<StudyClass> classSections = new HashSet<StudyClass>();
			if(!ObjectFunctions.isNullOrEmpty(getTempId1())){//Here tempId1 is examTypeId
				 List<ScoreCardTemplates> scoreCardTemplates =   adminManager.getAll(ScoreCardTemplates.class, "custId="+ getUserCustId()+" and examTypeId="+getTempId1());
				if (!ObjectFunctions.isNullOrEmpty(scoreCardTemplates)) {
					StringBuffer query1 = null;
					for(ScoreCardTemplates template : scoreCardTemplates){
						String[] className = template.getClassName().split("-"); 
						if(!StringFunctions.isNullOrEmpty(className[0]))
							 query1 = new StringBuffer("custId=").append(getUserCustId()).append(" and className='").append(className[0].toString().trim()).append("'");
						if(className.length == 2)
						 if(!StringFunctions.isNullOrEmpty(className[1]))
							query1.append(" and section='").append(className[1].toString().trim()).append("'");
							query1.append(" and academicYearId=").append(getUserAcademicYearId()).append("");
							
						List<StudyClass> stdClassList =adminManager.getAll(StudyClass.class,query1.toString());
						for(StudyClass stdClassObj1 : stdClassList){
							if(getUser().isOnlySchoolTeacher() || getUser().isOnlySchoolHod()){
								 List<ClassTeacher> teacherSubjetsList = null;
								Staff staff =adminManager.getStaffByAcountId(getUser().getId(),Constants.YES_STRING);
								if(getUser().isOnlySchoolTeacher())
									teacherSubjetsList = staffManager.getAll(ClassTeacher.class,"teacherId="+staff.getId()+" and academicYearId="+getUserAcademicYearId()+" and custId="+getUserCustId()+" and classTeacher='Y' and studyClassId="+stdClassObj1.getId());
	 							else
	 	 							teacherSubjetsList = staffManager.getAll(ClassTeacher.class,"teacherId="+staff.getId()+" and academicYearId="+getUserAcademicYearId()+" and custId="+getUserCustId()+" and studyClassId="+stdClassObj1.getId());
	 							if (!ObjectFunctions.isNullOrEmpty(teacherSubjetsList)) {
	 								for(ClassTeacher teacher : teacherSubjetsList){
	 									 if(teacher.getStudyClass().getFilledSeats() > 0)	
	 										classSections.add(teacher.getStudyClass());
	 								}
	 							}
	 							if(getUser().isOnlySchoolHod()){
	 								List studyClassesList = getHodStudyClassesList(staff.getId(),getUserAcademicYearId());
	 								if(!ObjectFunctions.isNullOrEmpty(studyClassesList))
	 								{
	 									classSections.addAll(studyClassesList);
	 								}
	 								studyClassesList = null;
	 							}
							}else{
							//classAvailableExamTypes=adminManager.getExamTypeIdsByClassIds(stdClassObj1.getClassNameClassId().getId());
							//if(!ObjectFunctions.isNullOrEmpty(classAvailableExamTypes)){
							//if(classAvailableExamTypes.size()> 0){
							 if(stdClassObj1.getFilledSeats() > 0)	
							  classSections.add(stdClassObj1);
							}
							//}
						 //}
						}
						if (ObjectFunctions.isNotNullOrEmpty(stdClassList)) {
							setStudyClassList(ConvertUtil.convertSetToList(classSections));
						}
					}
					if(!ObjectFunctions.isNullOrEmpty(getStudyClassList()))
						Collections.sort(getStudyClassList());
					query1 = null;
				}
				scoreCardTemplates = null;
			}
			classSections = null;*/
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
    public HashMap<String, String> readScoreCardActivitiesConfigScheet(File file){
  	  HashMap<String, String> studentsProfileCellInfo = null;
  	  try{
  		  FileInputStream templateFile= new FileInputStream(file);
  		  XSSFWorkbook workbook = new XSSFWorkbook(templateFile);
  		  XSSFSheet sheet = workbook.getSheet("ActivitiesConfig");
  		StringBuffer query = null;
  		  if(!ObjectFunctions.isNullOrEmpty(sheet)){
  			  studentsProfileCellInfo = new HashMap<String, String>();
  			//  String studentFieldName = null;
  			  Row activitiesFieldsRow = null;
  			  Cell cell = null;
  			  int startRow = 0;
  			  int endRow =0;
  			if(!ObjectFunctions.isNullOrEmpty(sheet.getRow(0))){
  				if(!ObjectFunctions.isNullOrEmpty(sheet.getRow(0).getCell(1))){
  					cell =  sheet.getRow(0).getCell(1);
      			  if(!ObjectFunctions.isNullOrEmpty(cell))
      			   	startRow =  (int)cell.getNumericCellValue();
  				}
  			}
  			if(!ObjectFunctions.isNullOrEmpty(sheet.getRow(0))){
  				if(!ObjectFunctions.isNullOrEmpty(sheet.getRow(0).getCell(1))){
  	  				cell =  sheet.getRow(0).getCell(3);
  	    			  if(!ObjectFunctions.isNullOrEmpty(cell))
  	    			   	endRow =  (int)cell.getNumericCellValue();
  	  			}
  			}
  			//startRow = startRow -1;
  			//endRow = endRow - 1;
  			  if(startRow > 0 && endRow > 0){
  			    	for( int currentRow = startRow;currentRow <= endRow; currentRow++)
  			    	{
  			    		int fieldValuesColumn = 0;
  			    		activitiesFieldsRow = sheet.getRow(currentRow);
  			    		String activityName = null;
  			    		String categoryName = null;
  			    		
  			    		log.debug("currentRow:" + currentRow);
  			    		if(!ObjectFunctions.isNullOrEmpty(activitiesFieldsRow))
  			    		{
  			    			if(!ObjectFunctions.isNullOrEmpty(activitiesFieldsRow.getCell(fieldValuesColumn)))
  	  			    			categoryName = activitiesFieldsRow.getCell(fieldValuesColumn).getStringCellValue().toUpperCase();
  	  			    		
  	  			    		//log.debug("categoryName:" + categoryName);
  	  			    		fieldValuesColumn ++;
  	  			    		if(!ObjectFunctions.isNullOrEmpty(activitiesFieldsRow.getCell(fieldValuesColumn)))
  		  			    	{
  	  			    			activityName = activitiesFieldsRow.getCell(fieldValuesColumn).getStringCellValue();
  		  			    	}
  	  			    		//log.debug("activityName:" + activityName);
  	  			    		fieldValuesColumn ++;
  	  			    		if(StringFunctions.isNotNullOrEmpty(activityName))
  	  			    		{
  	  			    			String activityDesc = null;
  	  			    			if(!ObjectFunctions.isNullOrEmpty(activitiesFieldsRow.getCell(fieldValuesColumn)))
  	  	  			    		{
  	  			    				activityDesc = activitiesFieldsRow.getCell(fieldValuesColumn).getStringCellValue();
  	  	  			    		}
  	  			    			StudentActivities studentActivities = null;
  								query = new StringBuffer(" activityName='").append(activityName.replaceAll("'","''").trim()).append("' and academicYearId=").append(getCurrentAcademicYear().getId());
  								studentActivities =(StudentActivities)adminManager.get(StudentActivities.class,query.toString());
  								//log.debug("query:"+query.toString());
  								if(ObjectFunctions.isNullOrEmpty(studentActivities)){
  									studentActivities = new StudentActivities();
  									studentActivities.setCreatedDate(new Date());
  									studentActivities.setCreatedById(getUser().getId());
  								}
  	  			    			if(!ObjectFunctions.isNullOrEmpty(studentActivities))
  	  			    			{			
  	  			    				studentActivities.setActivityName(activityName.toUpperCase().trim());
  	  			    				studentActivities.setAcademicYear(getCurrentAcademicYear());
  	  			    				studentActivities.setCreatedById(getUser().getId());
  	  			    				studentActivities.setCreatedDate(new Date());
  	  			    				studentActivities.setCustId(getUserCustId());
  	  			    				studentActivities.setLastAccessDate(new Date());
  	  			    				studentActivities.setLastUpdatedById(getUser().getId());
  	  			    				studentActivities.setLastUpdatedDate(new Date());
  		    						if(StringFunctions.isNullOrEmpty(activityDesc))
  		    							studentActivities.setActivityDescription("");
  		    						else
  		    							studentActivities.setActivityDescription(activityDesc.toUpperCase());
  		    						studentActivities.setCategoryName(categoryName);
  		    						setStudentActivities((StudentActivities) adminManager.saveOrUpdateObject(studentActivities));
  		    					}
  	  			    		}
  	  			    		fieldValuesColumn ++;
  	  			    		String subActivityName = null;
  	  			    		if(!ObjectFunctions.isNullOrEmpty(activitiesFieldsRow.getCell(fieldValuesColumn)))
  		  			    	{
  	  			    			subActivityName = activitiesFieldsRow.getCell(fieldValuesColumn).getStringCellValue();
	  	  			    		log.debug("subActivityName:" + subActivityName);
	  	  			    		fieldValuesColumn ++;
	  	  			    		String subActivityDesc = null;
	  	  			    		if(!ObjectFunctions.isNullOrEmpty(activitiesFieldsRow.getCell(fieldValuesColumn)))
	  		  			    	{
	  	  			    			subActivityDesc = activitiesFieldsRow.getCell(fieldValuesColumn).getStringCellValue();
	  		  			    	}
	  	  			    		StudentActivities studentActivities= getStudentActivities();
	  	  			    		StudentActivityTypes activityTypeName = null;
	  	  			    		if(!ObjectFunctions.isNullOrEmpty(studentActivities))
	  	  			    		{
	  	  			    			query = new StringBuffer("activityTypeName='").append(subActivityName.replaceAll("'","''").trim()).append("' and academicYearId=")
	  			    							.append(getCurrentAcademicYear().getId()).append(" and custId=").append(getUserCustId()).append(" and studentActivityId=").append(studentActivities.getId());
	  						    	activityTypeName = (StudentActivityTypes)adminManager.get(StudentActivityTypes.class,query.toString());
	  								query = null;
	  	  			    		}
	  	  			    		
	  							if(ObjectFunctions.isNullOrEmpty(activityTypeName)){
	  								activityTypeName = new StudentActivityTypes();
	  								activityTypeName.setCreatedDate(new Date());
	  								activityTypeName.setCreatedById(getUser().getId());
	  							}
	  	  			    		if(!ObjectFunctions.isNullOrEmpty(activityTypeName))
	  	  			    		{
	  	  			    			fieldValuesColumn++;
	  								activityTypeName.setActivityTypeName(subActivityName.trim().toUpperCase());
	  								activityTypeName.setActivityTypeDescription(subActivityDesc);
	  								activityTypeName.setCustId(getUserCustId());
	  								activityTypeName.setLastAccessDate(new Date());
	  								activityTypeName.setAcademicYear(getCurrentAcademicYear());
	  								Set<StudyClass> classNamesSet =  new HashSet<StudyClass>();
	  								
	  								if(!ObjectFunctions.isNullOrEmpty(activityTypeName.getActivityTypClasses()))
		  	  			    		{
	  									classNamesSet.addAll(activityTypeName.getActivityTypClasses());
		  	  			    		}
	  								if(!ObjectFunctions.isNullOrEmpty(activitiesFieldsRow.getCell(fieldValuesColumn)))
		  		  			    	{
	  									String subActivityClasses = activitiesFieldsRow.getCell(fieldValuesColumn).getStringCellValue();
		  		  			    		if(!StringFunctions.isNullOrEmpty(subActivityClasses))
		  		  			    		{
		  		  			    			String classNames[] = subActivityClasses.split(",");
		  		  			    			for(String classNameStr:classNames)
		  		  			    			{
		  		  			    				StudyClass studyClassName = null;
		  		  			    				String clasNameArray[] = classNameStr.split("-");
		  		  			    				if(clasNameArray.length > 1)
		  		  			    					studyClassName = adminManager.getclassByClassAndsection(clasNameArray[0].trim(),clasNameArray[1].trim(),getUserCustId(),getUserAcademicYearId());
		  		  			    				else
		  		  			    					studyClassName = adminManager.getclassByClassAndsection(clasNameArray[0].trim(),"",getUserCustId(),getUserAcademicYearId());
		  		  			    				
		  	        							if(!ObjectFunctions.isNullOrEmpty(studyClassName))
		  	        	  			    		{
		  	        								classNamesSet.add(studyClassName);
		  	        	  			    		}
		  	        						}
		  		  			    		}
		  		  			    	}
	  								if(ObjectFunctions.isNullOrEmpty(classNamesSet))
	  									activityTypeName.setActivityTypClasses(null);
	  								else
	  									activityTypeName.getActivityTypClasses().addAll(classNamesSet);
	  								if(!ObjectFunctions.isNullOrEmpty(studentActivities))
	  								{
	  									studentActivities.addStudentActivityTypes(activityTypeName);
		  								//adminManager.save(studentActivities);
	  									try{ 
	  										adminManager.merge(studentActivities);
	  									}
	  									catch(Exception e){
	  										e.printStackTrace();
	  									}
	  								}
	  								activityTypeName = null;
	  								studentActivities = null;
	  							  }
  		  			    	}
  			    		}
  			    		activitiesFieldsRow = null;
  			    	}
  			    }
  			//  studentFieldName = null;
  		  }
  		  templateFile.close();
  		  FileOutputStream outFile =new FileOutputStream(file);
  		  workbook.write(outFile);
  		  outFile.close();
  		  templateFile = null;
  	  }catch (Exception ex) {
  		  ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
  		  JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
  	}
  	  return studentsProfileCellInfo;
    } 
    @Actions( { @Action(value = "ajaxCheckExamSubTypeAvailableOrNot", results = { @Result(type = "json", name = "success", params = { "includeProperties", "autoCheck" }) }) })
	public String ajaxCheckExamSubTypeAvailableOrNot() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxCheckExamSubTypeAvailableOrNot' method");
		}
		try {
			String examSubTypeName = getParamValue("keyWord");
			if (StringFunctions.isNotNullOrEmpty(examSubTypeName)) {
				List examSubTypeList = adminManager.getAll(SubType.class," custId="+getUserCustId()+" and academicYearId="+ getUserAcademicYearId() + " and subTypeName like '"+examSubTypeName.trim()+"'");
				if (examSubTypeList.size() > 0) {
				    setAutoCheck("1");
				} else {
				    setAutoCheck("0");
				}
			}
		} catch (Exception ex) {
			log.error("Entering into 'catch block':" + ex.getMessage());
			ex.printStackTrace();
		}
		return SUCCESS;
	}
    @Actions( { @Action(value = "ajaxCheckExamTypeAvailableOrNot", results = { @Result(type = "json", name = "success", params = { "includeProperties", "autoCheck" }) }) })
	public String ajaxCheckExamTypeAvailableOrNot() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxCheckExamSubTypeAvailableOrNot' method");
		}
		try {
			String examType = getParamValue("keyWord");
			if (StringFunctions.isNotNullOrEmpty(examType)) {
				List examTypeList = adminManager.getAll(ExamTypes.class," custId="+getUserCustId()+" and academicYearId="+ getUserAcademicYearId() + " and examType like '%"+examType.trim()+"%'");
				if (examTypeList.size() > 0) {
				    setAutoCheck("1");
				} else {
				    setAutoCheck("0");
				}
			}
		} catch (Exception ex) {
			log.error("Entering into 'catch block':" + ex.getMessage());
			ex.printStackTrace();
		}
		return SUCCESS;
	}
    
    public CellStyle getCellStyle(XSSFWorkbook workbook,int fontSize,boolean isBold,boolean isMergeStyle)
    {
    	CellStyle cellStyle = workbook.createCellStyle();
    	
    	cellStyle.setWrapText(true);
    	if(!isMergeStyle)
    	{
    		 cellStyle.setBorderBottom(CellStyle.BORDER_THIN);
             cellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
             cellStyle.setBorderLeft(CellStyle.BORDER_THIN);
             cellStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
             cellStyle.setBorderRight(CellStyle.BORDER_THIN);
             cellStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
             cellStyle.setBorderTop(CellStyle.BORDER_THIN);
             cellStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
             cellStyle.setAlignment(CellStyle.ALIGN_LEFT);
    	}
    	else
    		cellStyle.setAlignment(CellStyle.ALIGN_LEFT);
    	
         XSSFFont font = workbook.createFont();
         font.setFontName(XSSFFont.DEFAULT_FONT_NAME);
         if(fontSize > 0)
         {
        	 font.setFontHeightInPoints((short)fontSize);
         }
         else
        	 font.setFontHeightInPoints((short)9);
         if(isBold)
         {
        	 font.setBold(true);
         }
         
         font.setColor(IndexedColors.BLACK.getIndex());
         cellStyle.setFont(font);
         
         return cellStyle;
    }
    @Actions( { @Action(value = "generateProgressReportByClassWise", results = { }) })
    public String generateProgressReportByClassWise(){
        if (log.isDebugEnabled()) {
            log.debug("Entering 'generateProgressReportByClassWise' method");
            }
        try{
        	 log.debug("Start Time");
            XSSFCell  cell ;
            List<ExamTypes> examTypesList=adminManager.getAll(ExamTypes.class, "custId="+getUserCustId()+" and id in "+getAnyTitle());
            if(ObjectFunctions.isNotNullOrEmpty(examTypesList))
            {
            	 AcademicYear year=getCurrentAcademicYear();
            	 List<Object[]> studentDetails = adminManager.getAll("select admissionNumber,fullName,studId,className,section from vw_studentClassDetails where custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and classSectionId="+getStudyClassId()+" and accountExpired='N' order by gender desc,fullName");
                 if(ObjectFunctions.isNotNullOrEmpty(studentDetails) && !StringFunctions.isNullOrEmpty(getAnyTitle()))
                 {
                     ZipOutputStream zipOutStream = new ZipOutputStream(getResponse().getOutputStream());
                     getResponse().setContentType("application/zip");
                     getResponse().addHeader("Content-Disposition", "attachment; filename=DefaultProgressReport.zip");
                     String fromDate = DateFormatter.formatDate(DateFormatter.CCYY_MM_DD_PATTERN,getParamValue("startDate"));
                     String toDate = DateFormatter.formatDate(DateFormatter.CCYY_MM_DD_PATTERN, getParamValue("endDate"));
                     File file = new File(getSession().getServletContext().getRealPath("userfiles/DefaultProgressReports/GEETAMREPORT.xlsx"));
                     if(file.exists())
                     {
                        Customer customer  = getCustomerByCustId();
                        StringBuffer studentsFilePath = null;
                        List<Object[]>  mraksAndSubjects = null;
                         XSSFWorkbook workbook = null;
                          File studentFile = null;
                          FileInputStream reportFile = new FileInputStream(file);
                          StringBuffer  generatedScoreCardsFilePath = new StringBuffer("userfiles/DefaultProgressReports/").append(customer.getCustomerShortName()).append("/"+year.getAcademicYear()).append("/ClassSection_").append(getStudyClassId()).append("/");
                          File outFile = new File(getSession().getServletContext().getRealPath(generatedScoreCardsFilePath.toString()));
                          if(outFile.exists())
                              outFile.deleteOnExit();

                          outFile.mkdirs();
                          outFile = null;   
                        //List<Object[]> dates = adminManager.getAll("select DATE_FORMAT(examDate,'%m-%d-%Y'),scheduleId from vw_studentExamMarks where  custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and classSectionId="+getStudyClassId()+" and examTypeId in"+getAnyTitle()+" and ( date(examDate) between '"+fromDate+"' and '"+toDate+"') group by examDate");
                        if(!ObjectFunctions.isNullOrEmpty(year)) 
                        {
                        	HashMap<String,String> studentDailyAttendancePresntMap = new HashMap<String, String>();
                        	Map<Integer,String> yearWiseMothNames = ajaxGetAllMonthNamesMonthIdByStartAndEndDate(year.getStartDate(),new Date());
                        	Map<String,Integer> attendanceListObjMap = new HashMap<String, Integer>();
                        	 if(year.getManageAttendanceBy().equalsIgnoreCase("M"))
                             {
                        		 List<Object[]> attendanceListObj = adminManager.getAll("select max(totalWorkingDays),monthName,classSectionId from vw_StudentMonthlyAttendance where custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and classSectionId="+getStudyClassId()+" and totalWorkingDays is not null group by monthName ");
                                 if(!ObjectFunctions.isNullOrEmpty(attendanceListObj))
                                 {
                                     for(Object[] obj : attendanceListObj)
                                     {
                                         if(!ObjectFunctions.isNullOrEmpty(obj))
                                         {
                                             if(!ObjectFunctions.isNullOrEmpty(obj[0]) && !ObjectFunctions.isNullOrEmpty(obj[1]))
                                             {
                                                 attendanceListObjMap.put(obj[1].toString().toUpperCase(), Integer.valueOf(obj[0].toString()));
                                             }
                                         }
                                     }
                                    
                                 }
                                 attendanceListObj = null;
                             }
                        	 else
         				   	 {
                        		Date openDate = year.getStartDate();
         						Date closeDate = year.getEndDate();
         						Date todayDate=new Date();
         						
                        		 //Map<String,Integer> attendanceListObjMap =null;
     							if(DateFunctions.compare2Dates(todayDate,openDate) && DateFunctions.compare2Dates(closeDate,todayDate))
     							{
     								attendanceListObjMap = studentManager.getMonthwiseSchoolWorkingDays(getUserCustId(),year.getId(),openDate,todayDate,true,"", String.valueOf(getStudyClassId())); //here getClassId() used to academicyear have class wise holiday(CH).
     							}else if(todayDate.after(closeDate)){
     								attendanceListObjMap =studentManager.getMonthwiseSchoolWorkingDays(getUserCustId(),year.getId(),openDate,closeDate,true,"", String.valueOf(getStudyClassId()));
     							}
         			   		  	List<Object[]> studentPresentCountList = adminManager.getStudentDailyAttendanceSP(getUserCustId(), getUserAcademicYearId(),Long.valueOf(getStudyClassId()));
         			   		   
         						 if(!ObjectFunctions.isNullOrEmpty(studentPresentCountList))
                                 {
                                 	for(Object[] stuPresentObj : studentPresentCountList)
                                 	{
                                 		long studentId = 0;
                                 		String monthNamesp = null;
                                 		String studentPresentCount = "0";
                                 		String studentAbsentCount = "0";
                                 		if(!ObjectFunctions.isNullOrEmpty(stuPresentObj))
                                 		{
                                 			if(!ObjectFunctions.isNullOrEmpty(stuPresentObj[0]))
                                     		{
                                 				studentId = Long.valueOf(stuPresentObj[0].toString());
                                     		}
                                 			if(!ObjectFunctions.isNullOrEmpty(stuPresentObj[7]))
                                     		{
                                 				monthNamesp = stuPresentObj[7].toString();
                                     		}
                                 			if(!ObjectFunctions.isNullOrEmpty(stuPresentObj[5]))
                                     		{
                                 				studentPresentCount = stuPresentObj[5].toString();
                                     		}
                                 			if(!ObjectFunctions.isNullOrEmpty(stuPresentObj[4]))
                                     		{
                                 				studentAbsentCount = stuPresentObj[4].toString();
                                     		}
                                 			
                                 			if(studentId > 0 && !StringFunctions.isNullOrEmpty(monthNamesp))
                                 				studentDailyAttendancePresntMap.put(studentId+""+monthNamesp.toUpperCase(), studentPresentCount+":"+studentAbsentCount);
                                 			
                                 			log.debug(studentId+"-"+monthNamesp.toUpperCase() +" ******* " + studentPresentCount+":"+studentAbsentCount); 
                                 		}
                                 		stuPresentObj=null;
                                 	}
                                 }
         			    	}
                        HashMap<Long,List<Object[]>> classExamDetailsMap = new HashMap<Long,List<Object[]>> ();
                    	  for(ExamTypes examType : examTypesList)
                          {
                              List<Object[]> subjectInfoList = adminManager.getAll("select name,subTypeName,maxMarks,classSubjectId,scheduleId,examDate from vw_classExamDetails where custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+"  and classSectionId="+getStudyClassId()+" and eid ="+examType.getId()+" ");
                              if(!ObjectFunctions.isNullOrEmpty(subjectInfoList))
                              {
                            	  classExamDetailsMap.put(examType.getId(), subjectInfoList);
                              }
                              subjectInfoList = null;
                          }
                        for(Object[] stud: studentDetails)
                        {
                            int k = 0;
                            if(!ObjectFunctions.isNullOrEmpty(stud))
                            {
                               
	                            reportFile = new FileInputStream(file);
	                            workbook = new XSSFWorkbook(reportFile);
	                            XSSFSheet sheet = workbook.getSheet("GEETAMREPORT");
	                                	                               
	                            studentsFilePath = new StringBuffer(generatedScoreCardsFilePath).append(stud[3].toString()+"-"+stud[4].toString()).append("_").append(stud[1].toString().trim().replaceAll(" ", "")).append("_").append(stud[0].toString().replaceAll("/", ""));
	                            studentsFilePath.append(".xls");
	                            studentFile = new File(getSession().getServletContext().getRealPath(studentsFilePath.toString()));
	                            FileUtils.copyFile(file, studentFile);
	                           // CellStyle cellStyle = workbook.createCellStyle();
	                            CellStyle cellStyle = cellStyle = getCellStyle(workbook,9,false,false);
	                            CellStyle cellStyleWithBold10Font   = getCellStyle(workbook,9,true,false);
                                 
                                CellStyle mergStyles12Header = getCellStyle(workbook,12,true,true);
                                CellStyle mergStyles = getCellStyle(workbook,10,true,true);
                                
                                XSSFFont headFont=workbook.createFont();
                                CellStyle hedStyles = workbook.createCellStyle();
                                headFont.setUnderline(Font.U_SINGLE);
                                hedStyles.setFont(headFont);
                                XSSFRow rowNum = sheet.createRow(k);
                                rowNum.setHeightInPoints(30);
                                cell = rowNum.createCell(0);   
                                cell.setCellValue(customer.getOrganization().toUpperCase()+"\n"+"   PROGRESS REPORT");
                                sheet.addMergedRegion(CellRangeAddress.valueOf("$A$"+(k+1)+":$M$"+(k+1)+""));
                                mergStyles12Header.setAlignment(CellStyle.ALIGN_CENTER);
                                cell.setCellStyle(mergStyles12Header);
                                
                                int cellNum = 0;
                                HashMap<String, Integer> subjectMap = new HashMap<String, Integer>();
                                rowNum =  sheet.createRow(k+2);
                                cell = rowNum.createCell(0);
                                cell.setCellValue("Admission Number : "+stud[0].toString()+",  Student Name : "+stud[1].toString()+",  ClassAndSection : "+stud[3].toString()+"-"+stud[4].toString());
                                sheet.addMergedRegion(CellRangeAddress.valueOf("$A$"+(k+3)+":$T$"+(k+3)+""));
                                cell.setCellStyle(hedStyles);
                                k= rowNum.getRowNum();
                                for(ExamTypes examType : examTypesList)
                                {
                                	List<Object[]> subjectInfo = null;
                                	if(!ObjectFunctions.isNullOrEmpty(classExamDetailsMap))
                                	{
                                		if(!ObjectFunctions.isNullOrEmpty(classExamDetailsMap.get(examType.getId())))
                                    	{
                                			subjectInfo = classExamDetailsMap.get(examType.getId());
                                    	}
                                	}
                                   // List<Object[]> subjectInfo = adminManager.getAll("select name,subTypeName,maxMarks,classSubjectId,scheduleId,examDate from vw_classExamDetails where custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+"  and classSectionId="+getStudyClassId()+" and eid ="+examType.getId()+" ");
                                    if(!ObjectFunctions.isNullOrEmpty(subjectInfo)){
	                                    rowNum =  sheet.createRow(k+1);
	                                    rowNum.setHeightInPoints(25);
	                                     cell = rowNum.createCell(0);
	                                     cell.setCellValue(examType.getExamType().toUpperCase()+" EXAMINATION MARKS");
	                                     cell.setCellStyle(mergStyles);
	                                     sheet.addMergedRegion(CellRangeAddress.valueOf("$A$"+(k+2)+":$T$"+(k+2)+""));
	                                    rowNum =  sheet.createRow(k+2);
	                                    k= rowNum.getRowNum();
	                                    cellNum = 0;
	                                    int totalMarks = 0;
	                                     for(Object[] obj: subjectInfo){
	                                         cell = rowNum.createCell(cellNum);
	                                         rowNum.setHeightInPoints(50);
	                                         if(cellNum == 0){
	                                             cell.setCellValue("Date");
	                                             cell.setCellStyle(cellStyleWithBold10Font);
	                                             cell = rowNum.createCell(cellNum+1);
	                                             cellNum = cellNum+1;
	                                         }
	                                         subjectMap.put(obj[4].toString(), cellNum);
	                                         cell.setCellValue(obj[0].toString()+" "+obj[1].toString()+" "+obj[2].toString());   
	                                         totalMarks = totalMarks +Integer.valueOf(obj[2].toString());
	                                         cell.setCellStyle(cellStyleWithBold10Font);
	                                         cellNum++;
	                                     }
	                                     cell = rowNum.createCell(cellNum);
	                                     cell.setCellValue("Total  "+totalMarks);       
	                                     cell.setCellStyle(cellStyleWithBold10Font);
                                        }
                                    	//log.debug("select DATE_FORMAT(examDate,'%m-%d-%Y'),subjectId,subjectName,subTypeName,maxMarks,obtainedMarks,scheduleId,studId from vw_studentExamMarks where  custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and classSectionId="+getStudyClassId()+" and examTypeId ="+examType.getId()+"  and studId="+stud[2].toString()+"  and ( date(examDate) between '"+fromDate+"' and '"+toDate+"') group by examDate,scheduleId");
                                         mraksAndSubjects = adminManager.getAll("select DATE_FORMAT(examDate,'%m-%d-%Y'),subjectId,subjectName,subTypeName,maxMarks,obtainedMarks,scheduleId,studId from vw_studentExamMarks where  custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and classSectionId="+getStudyClassId()+" and examTypeId ="+examType.getId()+"  and studId="+stud[2].toString()+"  and ( date(examDate) between '"+fromDate+"' and '"+toDate+"') group by examDate,scheduleId");
                                        // log.debug("*******");
                                         cellNum = 0;
                                         k = k+1;
                                         if(!ObjectFunctions.isNullOrEmpty(mraksAndSubjects)){
                                        	 String examDate = null;
                                             double markstotal =0;
                                             rowNum =  sheet.createRow(k);
                                             for(Object[] obj2: mraksAndSubjects){
                                                  cellNum =  subjectMap.get(obj2[6].toString());
                                                  if(!StringFunctions.isNullOrEmpty(examDate)){
                                                     if(!examDate.equalsIgnoreCase(obj2[0].toString())){
                                                         k++;
                                                         rowNum =  sheet.createRow(k);
                                                         cell = rowNum.createCell(0);
                                                         cell.setCellValue(obj2[0].toString());
                                                         markstotal =0;
                                                     }
                                                 }
                                                 else{
                                                         cell = rowNum.createCell(0);
                                                         cell.setCellValue(obj2[0].toString());
                                                     }
                                                     cell.setCellStyle(cellStyle);
                                                  for(int r=1;r<=subjectInfo.size();r++){
                                                         if(cellNum == r){
                                                             //obj2[5].toString();
                                                             cell = rowNum.createCell(r);
                                                             cell.setCellValue(obj2[5].toString());   
                                                             markstotal = markstotal+ Double.valueOf(obj2[5].toString());
                                                         }else{
                                                             cell = rowNum.getCell(r);
                                                             if(ObjectFunctions.isNullOrEmpty(cell)){
                                                                 cell = rowNum.createCell(r);
                                                                 cell.setCellValue(" ");   
                                                             }
                                                         }
                                                         sheet.setColumnWidth(r, 6 * 256);
                                                         cell.setCellStyle(cellStyle);
                                                     }
                                                       cell = rowNum.createCell(subjectInfo.size()+1);
                                                     cell.setCellValue(markstotal);   
                                                     sheet.setColumnWidth(subjectInfo.size()+1, 6 * 256);
                                                     cell.setCellStyle(cellStyle);
                                                     examDate = obj2[0].toString();                                           
                                             }
                                            mraksAndSubjects = null;
                                        }
                                       else{
                                          rowNum =  sheet.createRow(k);
                                          for(int r=0;r<=subjectInfo.size()+1;r++){
                                                 cell = rowNum.createCell(r);
                                                 cell.setCellValue("");   
                                             cell.setCellStyle(cellStyle);
                                             sheet.setColumnWidth(r, 6 * 256);
                                         }
                                       }
                                	}
                                     k=k+1;   
                                     rowNum = sheet.createRow(k+4);
                                     cell = rowNum.createCell(0);
                                     cell.setCellValue("");
                                     k=k+1;
                                     rowNum = sheet.createRow(k);
                                     rowNum.setHeightInPoints(15);
                                     cell = rowNum.createCell(0);
                                     cell.setCellValue("ATTENDANCE");
                                     cell.setCellStyle(mergStyles);
                                     sheet.addMergedRegion(CellRangeAddress.valueOf("$A$"+(k+1)+":$T$"+(k+1)+""));
                                    String[] attenHeader = new String[]{"MONTH","WORKING DAYS","PRESENT","ABSENT"};
                                    k=k+1;
                                    rowNum = sheet.createRow(k);
                                     rowNum.setHeightInPoints(45);
                                    cellNum = 0;
                                    for(String head: attenHeader){
                                        cell = rowNum.createCell(cellNum);
                                        cell.setCellValue(head);
                                        cell.setCellStyle(cellStyleWithBold10Font);
                                        cellNum++;
                                    }
                                    k=k+1;                                
                                    cellNum = 0;
                                       
                                    for (Map.Entry<Integer, String> entry : yearWiseMothNames.entrySet()) {
                                    	
                                    	long absentCount= 0;                                    
                                        int monthTotalDays = 0;
                                        int monthId = entry.getKey();
                                        long presentCount = 0;
                                        
                                        String monthNameli = entry.getValue();
                                        rowNum = sheet.createRow(k);
                                        rowNum.setHeightInPoints(18);
                                   	 	cell = rowNum.createCell(cellNum);
                                        cell.setCellValue(monthNameli.substring(0, 3));
                                        cell.setCellStyle(cellStyleWithBold10Font);
                                        cell = rowNum.createCell(cellNum+1);
                                      
                                        if(!ObjectFunctions.isNullOrEmpty(attendanceListObjMap.get(monthNameli.toUpperCase())))
                                        {
                                            cell.setCellValue(attendanceListObjMap.get(monthNameli.toUpperCase()));
                                            monthTotalDays = attendanceListObjMap.get(monthNameli.toUpperCase());
                                        }
                                        else
                                            cell.setCellValue("0");
                                     
                                       cell.setCellStyle(cellStyle);
                                       if(year.getManageAttendanceBy().equalsIgnoreCase("D"))
                                       {
                                           
                                    	   if(!ObjectFunctions.isNullOrEmpty(studentDailyAttendancePresntMap.get(stud[2].toString()+""+monthNameli.toUpperCase())))
                                           {
                                    		   String[] presentAndAbsentCoun = studentDailyAttendancePresntMap.get(stud[2].toString()+""+monthNameli.toUpperCase()).split(":");
                                    		   if(!ObjectFunctions.isNullOrEmpty(presentAndAbsentCoun))
                                    		   {
                                    			   presentCount= Long.valueOf(presentAndAbsentCoun[0]);
                                    			   absentCount= Long.valueOf(presentAndAbsentCoun[1]);
                                    		   }
                                           }
                                    	   if(presentCount <= 0)
                                    		   presentCount = monthTotalDays;
                                    	   
                                           cell = rowNum.createCell(cellNum+2);
                                           cell.setCellValue(presentCount);
                                            cell.setCellStyle(cellStyle);
                                           cell = rowNum.createCell(cellNum+3);
                                           cell.setCellValue(absentCount);
                                            cell.setCellStyle(cellStyle);
                                       }else{
                                           presentCount= staffManager.getStudentsMonthlyAttendanceByClassSectionIdAndMonthId(Long.valueOf(getStudyClassId()),monthId,getUserAcademicYearId(),getUserCustId(),Long.valueOf(stud[2].toString()));
                                           absentCount = monthTotalDays - presentCount;
                                           cell = rowNum.createCell(cellNum+2);
                                           cell.setCellValue(presentCount);
                                           cell.setCellStyle(cellStyle);
                                           cell = rowNum.createCell(cellNum+3);
                                           cell.setCellValue(absentCount);
                                            cell.setCellStyle(cellStyle);
                                       }
                                       k++;
                                    }
                                    rowNum = sheet.createRow(k+1);
                                    rowNum.setHeightInPoints(28);
                                    cell = rowNum.createCell(0);
                                    cell.setCellValue("NOTE : Dear Parent,you are requested to meet the principal to discuss your wards progress in academic. Thank you." );
                                    sheet.addMergedRegion(CellRangeAddress.valueOf("$A$"+(k+2)+":$M$"+(k+2)+""));
                                    cell.setCellStyle(mergStyles);
                                    rowNum = sheet.createRow(k+2);
                                    rowNum.setHeightInPoints(28);
                                    cell = rowNum.createCell(0);
                                    cell.setCellValue("Student Sign:                    "+"Parent Sign:                  "+"Principal:                       "+"C.E.O :         " );
                                    sheet.addMergedRegion(CellRangeAddress.valueOf("$A$"+(k+3)+":$T$"+(k+3)+""));
                             k=rowNum.getRowNum()+1;
                            }
                            if(!ObjectFunctions.isNullOrEmpty(studentFile)){
                                reportFile.close();
                                if(studentFile.exists())
                                {
                                	FileOutputStream outFile1 =new FileOutputStream(studentFile);
                                    workbook.write(outFile1);
                                    outFile1.close();
                                    studentFile = null;
                                }
                            }
                            workbook = null;
                        }
                     classExamDetailsMap = null;     
                 }else{
                     studentsFilePath = new StringBuffer(generatedScoreCardsFilePath).append("readMe.doc");
                     adminManager.writeToFile("Currently there are no exams found for selected dates.",getSession().getServletContext().getRealPath(studentsFilePath.toString()));
                 }
                if(!StringFunctions.isNullOrEmpty(generatedScoreCardsFilePath.toString())){
                    File directory = new File(getSession().getServletContext().getRealPath(generatedScoreCardsFilePath.toString()));
                    StringFunctions.zipFiles(directory,zipOutStream);
                    FileOutputStream fileWriter = new FileOutputStream(getSession().getServletContext().getRealPath("userfiles/DefaultProgressReports/"+customer.getCustomerShortName()+"/"+getCurrentAcademicYear().getAcademicYear()+"/ClassSection_"+getStudyClassId()+".zip"));
                    ZipOutputStream zipout =null;
                    zipout = new ZipOutputStream(fileWriter);
                    StringFunctions.zipFiles(directory,zipout);
                    zipout.close();
                    zipout=null;
                }
               }
             }
            }
            examTypesList = null;
           
        }catch (Exception ex) {
            ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
            JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
        }
        log.debug("End Time");
        return null;
        }
    @Actions( { @Action(value = "ajaxGetStudentList", results = { @Result(location = "../exam/scorecard/ajaxStudentsList.jsp", name = "success") }) })
	public String ajaxGetStudentList() throws URTUniversalException {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxGetStudentList' method");
		}
		try {
			log.debug(getClassId());
			StringBuffer queryString = new StringBuffer();
			queryString.append("classSectionId=" + Long.valueOf(getClassId())+ " and custId=" + getUserCustId() + " and academicYearId="+ getUserAcademicYearId() + " and status='"+ Constants.YES_STRING + "' order by firstname ");
			setStudentsList(adminManager.getAll(ViewStudentClassDetails.class, queryString.toString()));
			//setSelectedId(getSelectedId()); //selectedId used when login class teacher set value in class wise sms 
			//ajaxDoClassWideMessages();
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}

		return SUCCESS;
	}
    @Actions({ @Action(value = "ajaxDownloadScoreCardTemplate", results = {}) })
	public String ajaxDownloadScoreCardTemplate() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDownloadScoreCardTemplate' method");
		}
		try {
			File templateFile = null;
			//StringBuffer tcCustomerPath = null;
			if (getTempId() != 0) {
				AcademicYear academicYear = (AcademicYear)adminManager.get(AcademicYear.class,getUserAcademicYearId());
				ScoreCardTemplates scoreCardTemplates = (ScoreCardTemplates) adminManager.get(ScoreCardTemplates.class, "id=" + getTempId()+ "'");
				//Customer customer = getCustomerByCustId();
				//String className = scoreCardTemplates.getClassName().toString();
				String examType = scoreCardTemplates.getExamType().toString();				
				if (!ObjectFunctions.isNullOrEmpty(scoreCardTemplates)) {
					//tcCustomerPath = new StringBuffer("userFiles/ScoreCardTemplates");
					//tcCustomerPath.append("/").append(customer.getCustomerShortName()).append("/"+ academicYear.getAcademicYear()).append("/" + scoreCardTemplates.getExamType());
					if (StringFunctions.isNotNullOrEmpty(scoreCardTemplates.getTemplateFileName())) 
					{
						//templateFile = new File(getSession().getServletContext().getRealPath(scoreCardTemplates.getFileName()));
						InputStream is = null;
						try{
							URL url = new URL(scoreCardTemplates.getFilePath());
							URLConnection conn = url.openConnection();
							conn.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
							is = conn.getInputStream();
							
							templateFile = File.createTempFile(scoreCardTemplates.getFileName(), null);
							//templateFile.delete();
							FileUtils.copyInputStreamToFile(is, templateFile);
						}
						catch(IOException e){
							e.printStackTrace();
						}
						finally {
							if( is != null)	is.close();
						}
						
						if (!StringFunctions.isNullOrEmpty(templateFile.toString())) {
							getResponse().setContentType("application/octet-stream");
							getResponse().addHeader("Content-Disposition","attachment; filename="+ scoreCardTemplates.getTemplateFileName().replaceAll(" ", "_"));
							ServletOutputStream out = getResponse().getOutputStream();
							DataInputStream in = new DataInputStream(new FileInputStream(templateFile));
							byte[] bbuf = new byte[1024];
							int length = 0;
							while ((in != null) && ((length = in.read(bbuf)) != -1)) {
								out.write(bbuf, 0, length);
							}
							in.close();
							out.flush();
							out.close();
						}
					}
				}
			}
			super.addActionMessage("Template downloaded successfully.");
		} catch (Exception ex) {
			ex.printStackTrace();
			RayGunException raygex = new RayGunException();
			raygex.sendRayGunException(ex);
			raygex = null;
		}
		return null;
	}
    @Actions( {
		@Action(value = "ajaxGetExamTypesByClassId", results = { @Result(location = "ajaxExamTypesByClassId.jsp", name = "success") }),
		@Action(value = "ajaxGetExamTypesByClassNameClassId", results = { @Result(location = "ajaxExamTypesByClassIdMoreDetails.jsp", name = "success") })
		})
		public String ajaxGetExamTypesByClassId() throws URTUniversalException {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxGetExamTypesByClassId' method");
			}
			try {
				List<ViewStudentsLatestExamMarksDetails> studentLastestMarksClassesList = null;
				setViewStudentsLatestExamMarksDetailsList(null);
					if(!StringFunctions.isNullOrEmpty(getClassId()))
						studentLastestMarksClassesList = adminManager.getAll(ViewStudentsLatestExamMarksDetails.class,"custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+"  and classNameClassId="+Long.valueOf(getClassId())+"  group by examTypeId order by examStartDate desc");
					else if(getTempId2() > 0)
						studentLastestMarksClassesList = adminManager.getAll(ViewStudentsLatestExamMarksDetails.class,"custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+"  and classSectionId="+getTempId2()+"  group by examTypeId order by examStartDate desc");
					if(!ObjectFunctions.isNullOrEmpty(studentLastestMarksClassesList))
		 			{
						setViewStudentsLatestExamMarksDetailsList(studentLastestMarksClassesList);
						ViewStudentsLatestExamMarksDetails examTypeId = (ViewStudentsLatestExamMarksDetails)getViewStudentsLatestExamMarksDetailsList().get(0);
						if(!ObjectFunctions.isNullOrEmpty(examTypeId))
							setTempId1(examTypeId.getExamTypeId());
							examTypeId = null;
		 			}
			} catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return SUCCESS;
		}
   
    @Actions({
		@Action(value = "ajaxGetPassAndFailPersentByClassIdGraph", results = {  @Result(type = "json", name = "success", params = {"includeProperties","wishDescription" }) })
	})
		public String ajaxGetPassAndFailPersentByClassIdGraph() throws URTUniversalException {
		
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxGetPassAndFailPersentByClassIdGraph' method");
		}
		try
		{
			List<Object[]> studentMarksPersentage = null;
			if(getTempId() > 0 && getTempId1() > 0) {
			    studentMarksPersentage = adminManager.getAll("select passCount,failCount,classAndSection from vw_calculatePassAndFailCountStudents where custId="+getUserCustId()+" and academicyearId="+getUserAcademicYearId()+" and classNameClassId="+getTempId()+" and examTypeId="+getTempId1());
			}
			if(!StringFunctions.isNullOrEmpty(getClassSectionId()) && getTempId1() > 0) {
				studentMarksPersentage = adminManager.getAll("select passCount,failCount,subjectName from vw_calculatePassAndFailCountStudentsBySubjects where custId="+getUserCustId()+" and academicyearId="+getUserAcademicYearId()+" and classSectionId="+getClassSectionId()+" and examTypeId="+getTempId1());
			}
			if(ObjectFunctions.isNotNullOrEmpty(studentMarksPersentage)){
				JSONObject ja = new JSONObject();
				JSONArray subjectNameOrClassName=new JSONArray();
				double passPercentage=0;
				double failPercentage=0;
				JSONArray totalSeriesArray=new JSONArray();
				JSONObject seriesObj = new JSONObject();
				JSONObject j;
				StringBuffer query = null;
				for(Object[] marksPersentage : studentMarksPersentage){
					if(!ObjectFunctions.isNullOrEmpty(marksPersentage[0]) && !ObjectFunctions.isNullOrEmpty(marksPersentage[1])){
						double passPersent = 0.0;
						double failPersent = 0.0;
						int passCount=Integer.valueOf(marksPersentage[0].toString());
						int failCount=Integer.valueOf(marksPersentage[1].toString());
						int total=passCount+failCount;
						if(passCount>0)
							passPersent =(double)(passCount*100)/total;
						if(failCount>0)
							failPersent = (double)(failCount*100)/total;
						j=new JSONObject();
						subjectNameOrClassName.put(marksPersentage[2].toString());
						passPercentage=roundTwoDecimals(passPersent);
						failPercentage=roundTwoDecimals(failPersent);
						}
			    }
				seriesObj.put("name", "Pass Percentage");
				seriesObj.put("y", passPercentage);
				totalSeriesArray.put(seriesObj);
				seriesObj=new JSONObject();
				seriesObj.put("name", "Fail Percentage");
				seriesObj.put("y", failPercentage);
				totalSeriesArray.put(seriesObj);
				ja.put("series", totalSeriesArray);
				JSONArray out = new JSONArray();
				JSONObject obj = new JSONObject();
				obj.put("name", "Result");
				obj.put("colorByPoint", true);
				obj.put("data", totalSeriesArray);
				out.put(obj);
				//ja.put("categories", subjectNameOrClassName);
				getResponse().getOutputStream().print(out.toString());
				log.debug(out.toString());
				totalSeriesArray=null;
				subjectNameOrClassName=null;
				failPercentage=0;
				passPercentage=0;
				studentMarksPersentage=null;
			}
		}catch(Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
    @Actions( { 
		@Action(value = "ajaxDoCreateExamTypesAlongWithClassesAndExamSubTypes", results = { @Result(location = "ajaxViewExamTypesAndGrades.jsp", name = "success") }) })
	public String ajaxDoCreateExamTypesAlongWithClassesAndExamSubTypes() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDoCreateExamTypesAlongWithClassesAndExamSubTypes' method");
		}
		try {
			boolean isTrue=false;
        	AcademicYear academicYear = getCurrentAcademicYear();
        	if(!ObjectFunctions.isNullOrEmpty(academicYear)){
        		if(!academicYear.getIsDefaultExamTypeStatus().equalsIgnoreCase("Y")){
	        		Customer custObj= (Customer) adminManager.get(Customer.class,"id="+getUserCustId()+" and status='Y'");
	        		if(!ObjectFunctions.isNullOrEmpty(custObj)){
	        			ExamTypes examType=null;
	        			SubType subType=null;
	        			List<SubType> examSubTypeList =null;
	        			String checkStateOrCentral="CBSE";
	        			if(StringFunctions.isNotNullOrEmpty(custObj.getCustomerShortName())){
	        				StringBuffer query = new StringBuffer("academicYearId=").append(academicYear.getId());
	        				if(ObjectFunctions.isNotNullOrEmpty(custObj.getSyllabusType())){
	        					if(custObj.getSyllabusType().size() ==1 ){
		        					for(Object obj:custObj.getSyllabusType()){
		        						SyllabusType syllabusTypeObj=(SyllabusType)obj;
		        						if(!ObjectFunctions.isNullOrEmpty(syllabusTypeObj)){
		        							if("SBSE".equalsIgnoreCase(syllabusTypeObj.getSyllabusTypeName())){
		        								query.append(" and custId="+custObj.getId()+" and subTypeName='");
		        								query.append("Theory");
		        								query.append("'");
		        							}else{
		        								query.append(" and custId="+custObj.getId()+"");
		        							}
		        							checkStateOrCentral=syllabusTypeObj.getSyllabusTypeName();
		        						}
		        					}
	        					}else{
	        						query.append(" and custId="+custObj.getId()+"");
	        					}
	        					List<ExamTypes> examTypeList=null;
	        					if(checkStateOrCentral.equalsIgnoreCase("CBSE")){
	        						examTypeList=adminManager.getAll(ExamTypes.class,"custId="+Long.valueOf(1)+" and examTypeStateId="+0);
	        					}else{
	        						examTypeList=adminManager.getAll(ExamTypes.class,"custId="+Long.valueOf(1)+" and examTypeStateId="+custObj.getStateId());
	        					}
	        					if(ObjectFunctions.isNotNullOrEmpty(examTypeList)){
	        						examSubTypeList=adminManager.getAll(SubType.class,"custId="+Long.valueOf(1));
	        						if(ObjectFunctions.isNotNullOrEmpty(examSubTypeList)){
	        							for(SubType examSubObj:examSubTypeList){
	        								if(!ObjectFunctions.isNullOrEmpty(examSubObj)){
	        									subType=(SubType) adminManager.get(SubType.class,"custId="+custObj.getId()+" and subTypeName='"+examSubObj.getSubTypeName().trim()+"' and academicYearId="+academicYear.getId()) ;
	        									if(ObjectFunctions.isNullOrEmpty(subType)){
	        										subType=new SubType();
	        										subType.setSubTypeName(examSubObj.getSubTypeName());
	        									}else{
	        										log.debug(""+examSubObj.getSubTypeName()+" is already added for this academic year.");
	        										continue;
	        									}
	        									subType.setCustId(custObj.getId());
	        									subType.setAcademicYear(academicYear);
	        									subType.setCreatedDate(new Date());
	        									subType.setCreatedById(getUser().getId());
	        									subType.setLastAccessDate(new Date());
	        									subType.setLastUpdatedDate(new Date());
	        									adminManager.save(subType);
	        									subType=null;
	        								}examSubObj=null;
	        							}
	        						}
	        						examSubTypeList =adminManager.getAll(SubType.class, query.toString());
	            					List<StudyClass> studyClassesList = adminManager.getAll(StudyClass.class,"custId="+custObj.getId()+" and academicYearId="+academicYear.getId());
	            					for(ExamTypes examObj:examTypeList){
	        							if(!ObjectFunctions.isNullOrEmpty(examObj)){
	        								examType=(ExamTypes) adminManager.get(ExamTypes.class,"custId="+custObj.getId()+" and examType='"+examObj.getExamType().trim()+"' and academicYearId="+academicYear.getId()) ;
	        								if(ObjectFunctions.isNullOrEmpty(examType)){
	        									examType = new ExamTypes();
		        								examType.setCreatedDate(new Date());
		        								examType.setCreatedById(getUser().getId());
		        								examType.setExamType(examObj.getExamType().trim());
		        								Object[] maxSortOrder = adminManager.get("select id,IFNULL(max(sortingOrder),0) from  examTypes where academicYearId="+academicYear.getId());
		        								if(!ObjectFunctions.isNullOrEmpty(maxSortOrder) && !ObjectFunctions.isNullOrEmpty(maxSortOrder[1])){
		        									examType.setSortingOrder(Integer.valueOf(maxSortOrder[1].toString())+1);
		        								}
	        								}else{
	        									log.debug(""+examType.getExamType()+" is already added for this academic year.");
		        								continue;
	        								}
	        							}	        							
	        							examType.setCustId(getUserCustId());
	        							examType.setAcademicYear(academicYear);
	        							if(ObjectFunctions.isNullOrEmpty(studyClassesList))
	        								examType.setStudyClasses(null);
	        							else
	        								examType.setStudyClasses(ConvertUtil.convertListToSet(studyClassesList));
	        							if(ObjectFunctions.isNullOrEmpty(examSubTypeList))
	        								examType.setExamSubTypes(null);
	        							else
	        								examType.setExamSubTypes(ConvertUtil.convertListToSet(examSubTypeList));
	        							examType.setLastAccessDate(new Date());
	        							examType.setLastUpdatedDate(new Date());
	        							examType.setMaxMarks("0");
	        							examType.setMinMarks("0");	        							
	        							adminManager.save(examType);
	        							examType = null;
	        							isTrue=true;
	        						}
	        					}
	        				}
	        			}else{
	        				log.debug("Customer Short Name Not Available..." + custObj.getCustomerShortName());
	        			}						
	        		}custObj=null;
        		}else{
        			super.addActionError("Already added default records for this academic year.");
        		}
        	}
        	if(isTrue){
        		academicYear.setIsDefaultExamTypeStatus("Y");
        		adminManager.save(academicYear);
        		super.addActionMessage("Created examtypes successfully along with studyClasses and exam sub types.");
        	}
        	setTempString("examTypes");
        	ajaxManageExamSettings();
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
    @Actions( { 
		@Action(value = "ajaxGetStudyClassListBySyllabusType", results = { @Result(location = "ajaxDisplayStudyClassDetails.jsp", name = "success") }) })
	public String ajaxGetStudyClassListBySyllabusType() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxGetStudyClassListBySyllabusType' method");
		}
		try {
			
			long examTypeId=getTempId1();
			if(examTypeId > 0){
				setExamTypes((ExamTypes)adminManager.get(ExamTypes.class, examTypeId));
				/*Here I am bringing all student marks assigned classes because while iterating all classes and calling db I am getting peformance issue that's why I implement below code.*/
				List<ViewStudentMarks> marksUploadOrNOtLIst=adminManager.getAll(ViewStudentMarks.class,"examTypeId="+examTypeId+" GROUP BY classSectionId");
				HashMap<Long, ViewStudentMarks> examIdAndCsIdMap = new HashMap<Long,ViewStudentMarks>();
				if(ObjectFunctions.isNotNullOrEmpty(marksUploadOrNOtLIst)){
					for(ViewStudentMarks viewStudentMarks: marksUploadOrNOtLIst){
						examIdAndCsIdMap.put(viewStudentMarks.getClassSectionId(),viewStudentMarks);
					}
				}
				if(!ObjectFunctions.isNullOrEmpty(getExamTypes())){
						if(StringFunctions.isNotNullOrEmpty(getChkBoxSelectedAccountIds().toString().replaceAll("[\\[\\](){}]",""))){
							setStudyClassList(adminManager.getAll(StudyClass.class,"custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and id not in "+getExamTypes().getClassSectionIds()+" and syllabusTypeId in ("+getChkBoxSelectedAccountIds().toString().replaceAll("[\\[\\](){}]","")+")"));
						}
					   /*below method check the classSection have a marks we are disabled in that classSection in view*/
					   if(ObjectFunctions.isNotNullOrEmpty(getExamTypes().getStudyClasses())){ 
							for (StudyClass studyClass : getExamTypes().getStudyClasses()) {
								//log.debug(studyClass.getId());
									if(!ObjectFunctions.isNullOrEmpty(examIdAndCsIdMap.get(studyClass.getId()))){
										getTempList1().add(studyClass);
									}else{
										getTempList2().add(studyClass);
									}
								getChkBoxSelectedIds().add(String.valueOf(studyClass.getId()));
							}
						}
					   marksUploadOrNOtLIst=null;
					   examIdAndCsIdMap=null;
					}
			}else{
				if(StringFunctions.isNotNullOrEmpty(getChkBoxSelectedAccountIds().toString().replaceAll("[\\[\\](){}]",""))){
					setStudyClassList(adminManager.getAll(StudyClass.class,"custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and syllabusTypeId in ("+getChkBoxSelectedAccountIds().toString().replaceAll("[\\[\\](){}]","")+")"));
				}
			}
			//updateStudyClassIdInsteadOfClassId();
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
    @Actions({
		@Action(value = "ajaxGetPassAndFailPersentByClassIdGraphs", results = {  @Result(type = "json", name = "success", params = {"includeProperties","wishDescription" }) })
	})
		public String ajaxGetPassAndFailPersentByClassIdGraphs() throws URTUniversalException {
		
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxGetPassAndFailPersentByClassIdGraphs' method");
		}
		try
		{
			List<Object[]> studentMarksPersentage = null;
			if (getAcademicYearId() > 0) {
				Object[] examTypeId  = null;
				if (getAcademicYearId() == getCurrentAcademicYear().getId()) {
				 examTypeId = staffManager.get("select examTypeId,id from examSchedules where custId="+getUserCustId()+" and academicyearId="+getAcademicYearId()+" and endDate>='"+DateFormatter.formatDate(DateFormatter.CCYY_MM_DD_PATTERN, new Date())+"' order by startDate ASC LIMIT 1");
				}else
					 examTypeId = staffManager.get("select examTypeId,id from examSchedules where custId="+getUserCustId()+" and academicyearId="+getAcademicYearId()+" and endDate>='"+DateFormatter.formatDate(DateFormatter.CCYY_MM_DD_PATTERN, getCurrentAcademicYear().getEndDate())+"' order by startDate ASC LIMIT 1");
				if(!ObjectFunctions.isNullOrEmpty(examTypeId)){
				    studentMarksPersentage = adminManager.getAll("select passCount,failCount,classAndSection from vw_calculatePassAndFailCountStudents where custId="+getUserCustId()+" and academicyearId="+getAcademicYearId()+" and examTypeId="+Long.valueOf(examTypeId[0].toString())+" ");
				}
			}
			if(ObjectFunctions.isNotNullOrEmpty(studentMarksPersentage)){
				JSONObject ja = new JSONObject();
				JSONArray subjectNameOrClassName=new JSONArray();
				JSONArray passPercentage=new JSONArray();
				JSONArray failPercentage=new JSONArray();
				JSONArray totalSeriesArray=new JSONArray();
				JSONObject seriesObj = new JSONObject();
				JSONObject j;
				StringBuffer query = null;
				for(Object[] marksPersentage : studentMarksPersentage){
					if(!ObjectFunctions.isNullOrEmpty(marksPersentage[0]) && !ObjectFunctions.isNullOrEmpty(marksPersentage[1])){
						double passPersent = 0.0;
						double failPersent = 0.0;
						int passCount=Integer.valueOf(marksPersentage[0].toString());
						int failCount=Integer.valueOf(marksPersentage[1].toString());
						int total=passCount+failCount;
						if(passCount>0)
							passPersent =(double)(passCount*100)/total;
						if(failCount>0)
							failPersent = (double)(failCount*100)/total;
						j=new JSONObject();
						subjectNameOrClassName.put(marksPersentage[2].toString());
						passPercentage.put(roundTwoDecimals(passPersent));
						failPercentage.put(roundTwoDecimals(failPersent));
						}
			    }
				seriesObj.put("name", "Pass percentage");
				seriesObj.put("data", passPercentage);
				totalSeriesArray.put(seriesObj);
				seriesObj=new JSONObject();
				seriesObj.put("name", "Fail Percentage");
				seriesObj.put("data", failPercentage);
				totalSeriesArray.put(seriesObj);
				ja.put("series", totalSeriesArray);
				ja.put("categories", subjectNameOrClassName);
				getResponse().getOutputStream().print(ja.toString());
				log.debug(ja.toString());
				totalSeriesArray=null;
				subjectNameOrClassName=null;
				failPercentage=null;
				passPercentage=null;
				studentMarksPersentage=null;
			}
		}catch(Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
    @Actions( { 
		@Action(value = "ajaxGetSubTypeByExamTypeId", results = { @Result(location = "ajaxSubTypesByEamytpeId.jsp", name = "success") })
    })
	public String ajaxGetSubTypeByExamTypeId() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxGetSubTypeByExamTypeId' method");
		}
		try {
			if(StringFunctions.isNotNullOrEmpty(getChkBoxSelectedAccountIds().toString().replaceAll("[\\[\\](){}]",""))){ 
				String examTypeIds= getChkBoxSelectedAccountIds().toString().replaceAll("[\\[\\](){}]","");
				//heare payment_SequanceId() AS id used to not geting error for unkonw column 'id' 
				setSubTypesList(adminManager.getAll("select st.id, st.subTypeName, es.examTypeId, payment_SequanceId() AS psId FROM examSchedules es left join studyClass sc ON (sc.id = es.classSectionId) left join class cs ON (sc.classNameClassId = cs.id)  left join studySubject ss ON (ss.id = es.classSubjectId) left join examTypes et ON (et.id = es.examTypeId) left join subType st ON (st.id = es.subTypeId) WHERE es.classSectionId="+studyClassId+" and  et.id in ("+examTypeIds+")  group by st.id"));
				//setSubTypesList(adminManager.getAll("select st.id, st.subTypeName, es.examTypeId, payment_SequanceId() AS id FROM examTypes et LEFT JOIN examTypesAndSubTypes es ON (es.examTypeId = et.id) LEFT JOIN  subType st ON (st.id = es.subTypeId) WHERE et.id in ("+examTypeIds+") group by st.id")); 
			}
			} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
    
    @Actions( { 
		@Action(value = "ajaxSubTypesByEamytpeIdForAddMarks", results = { @Result(location = "ajaxSubTypesByEamytpeIdForAddMarks.jsp", name = "success") })
		
    })
    public String ajaxSubTypesByEamytpeIdForAddMarks() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxGetSubTypeByExamTypeId' method");
		}
		try {
			if(StringFunctions.isNotNullOrEmpty(getAnyId())){ 
				ExamTypes examTypes = (ExamTypes) adminManager.get(ExamTypes.class,"custId="+getUserCustId() + " and id="+getAnyId());
				if(!ObjectFunctions.isNullOrEmpty(examTypes))
				{
					if(ObjectFunctions.isNotNullOrEmpty(examTypes.getExamSubTypes())){
						setDisplayAttendanceSet(examTypes.getExamSubTypes());
					}
				}
			}
			} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
    
	@Actions({
		@Action(value = "ajaxDoGetStudentsAddMarks", results = { @Result(location = "ajaxAddMarks.jsp", name = "success") })
   		})
   		public String ajaxDoGetStudentsAddMarks() throws URTUniversalException {
   		if (log.isDebugEnabled()) {
   			log.debug("Entering 'ajaxDoGetStudentsAddMarks' method");
   		}
   		try
   		{
			String fileName ;
			StringBuffer query = null;
			
			List<ViewStudentClassDetails> classStudentsList =null;
			List<ExamSchedules> examScheduleDetails=null;
			Row row = null;
			Cell cell= null;
			Integer col=null;
			List<StudentMarks> studentMarks = null;
			DecimalFormat df = new DecimalFormat("#.##");
			if(StringFunctions.isNotNullOrEmpty(getAnyId()))
			{
				long examTypeId=Long.valueOf(getAnyId());
    			long classSectionId= Long.valueOf(getClassId());
    			if(!ObjectFunctions.isNullOrEmpty(getUser().getId()))
    			{
    				query = new StringBuffer("custId=").append(getUserCustId()).append(" and academicYearId=").append(getUserAcademicYearId())
	       						.append(" and classSectionId=").append(classSectionId).append(" and description is null");
						//and status='Y' -- If we put status Y condition we can not get students list when we switch to old academic year. Because some times user traying to generate old academic year score care report and updateing marks also.
					classStudentsList=adminManager.getAll(ViewStudentClassDetails.class, query.toString());
					query = null;
   					boolean isClassTeacher = false;
   					Object[] hodClassTeacher  = null;
   					//boolean isHodClassTeacher = false;
   					
   					query = new StringBuffer("FROM ViewExamSchedule WHERE classSectionId=").append(classSectionId);
   					
   					if(!getUser().isSchoolAdmin())
   					{
   						setStaff(adminManager.getStaffByAcountId(getUser().getId(), Constants.YES_STRING));
   						
   						isClassTeacher = adminManager.isUserAsClassTeacher(getUser().getId(),classSectionId, getUserAcademicYearId());
   						if(getUser().isOnlySchoolHod())
   						{
   							
   							hodClassTeacher  = adminManager.get("select staffId,studyClassId from staffMultipleStudyClasses where staffId="+getStaff().getId()+" and studyClassId="+classSectionId);
   						}
   						if(!isClassTeacher)
   						{
   							/*StringBuilder studySubjectsIs = new StringBuilder("(");
   							List<ClassTeacher> classTeacherList = staffManager.getAll(ClassTeacher.class,"studyClassId="+classSectionId+" and custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and teacherId="+getStaff().getId()+ " group by studySubjectId ");
   							if(ObjectFunctions.isNotNullOrEmpty(classTeacherList))
   							{
   								StringBuffer subjectIds = new StringBuffer();
   								for(ClassTeacher classTeacher1 : classTeacherList)
   								{
   									studySubjectsIs.append(classTeacher1.getStudySubjectId()+",");
   								}
   								
   								studySubjectsIs.append("0)");
   								
   								
   							}
   							classTeacherList = null;*/
   							
   							List<Long>  staffSubjectIds = adminManager.getAll("select studySubjectId from classTeacher where studyClassId="+classSectionId+" and custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and teacherId="+getStaff().getId()+ " group by studySubjectId");
   							if(!ObjectFunctions.isNullOrEmpty(staffSubjectIds)){
   								query.append(" and subjectId in (" + StringFunctions.convertListToCommaDelimitedString(staffSubjectIds)).append(")");
   							}
   						}
   					}
   					
						
   					query.append(" and examTypeId=").append(examTypeId).append(" and subTypeId=").append(getParamValue("subtypeId")).append(" and academicYearId=").append(getUserAcademicYearId()).append(" and scheduleId <>0").append(" order by subjectSortingOrder");
       				examScheduleDetails=adminManager.getAllHqlQuery(query.toString());
   					query = null;
   					if(!ObjectFunctions.isNullOrEmpty(examScheduleDetails) && !ObjectFunctions.isNullOrEmpty(classStudentsList)){
						Collections.sort(examScheduleDetails, new ExamSchedulesSubjectsAndSubtypesWiseComparator());
						setExamScheduleList(examScheduleDetails);
						setObjectList(classStudentsList);
					}
   					examScheduleDetails = null;
   					classStudentsList = null;
    			}
			}
   		}
   		catch(Exception ex)
   		{
   			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
   		}
   		return SUCCESS;
   	}
	
	@Actions( { @Action(value = "ajaxGetStudentMarksList", results = { @Result(type = "json", name = "success", params = {"includeProperties", "timingsList.*" }) }) })
	public String ajaxGetStudentMarksList() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxGetStudentMarksList' method");
		}
		try {
			List<ViewStudentClassDetails> classStudentsList =null;
			/*if(!ObjectFunctions.isNullOrEmpty(getStudyClassId()))
			{*/
				JSONArray res = new JSONArray();
				JSONObject j;
				int sNo=0;
				StringBuffer query = null;
				//Here tempId is studentId, we are fetching this in student login 
				if(getTempId1() > 0 ){
					query = new StringBuffer("custId=").append(getUserCustId()).append(" and academicYearId=").append(getUserAcademicYearId())
	   						.append(" and studId=").append(getTempId1()).append(" and description is null");
				}else{
					query = new StringBuffer("custId=").append(getUserCustId()).append(" and academicYearId=").append(getUserAcademicYearId())
   						.append(" and classSectionId=").append(getStudyClassId()).append(" and description is null");
				}
			classStudentsList=adminManager.getAll(ViewStudentClassDetails.class, query.toString());
			//setStudySubjectList(adminManager.getAll(ViewStudyClassSubjects.class, "custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and studyClassId="+getClassSectionId()+" and subjectType='"+Constants.YES_STRING+"' "));
			if(!ObjectFunctions.isNullOrEmpty(classStudentsList))
			{
				for(ViewStudentClassDetails studentDetails:classStudentsList)
				{
					List<ViewStudentMarks> studentMarks = null;
					//Here tempId is studentId, we are fetching this in student login
					if(getTempId1() > 0 ){
						studentMarks = studentManager.getAllMarksByStudId(getTempId1(),getUserAcademicYearId());
					}
					else{
						studentMarks = adminManager.getStudentMarksByStudentIdAndExamtypeIdAndSubTypeId(studentDetails.getStudId(),
								!ObjectFunctions.isNullOrEmpty(getParamValue("examTypeId"))?Long.valueOf(getParamValue("examTypeId")):0,
								!ObjectFunctions.isNullOrEmpty(getParamValue("subtypeId"))?Long.valueOf(getParamValue("subtypeId")):0);
					}
					if(!ObjectFunctions.isNullOrEmpty(studentMarks)){
						for(ViewStudentMarks marks: studentMarks){
							if(!ObjectFunctions.isNullOrEmpty(marks))
							{
								j=new JSONObject();
							    if(subjectId!=String.valueOf(marks.getSubjectId())){
							    	sNo=0;
							    }
							if(marks.isPresent())
								j.put("INPUTVALUE",marks.getObtainedMarks());
							else
								j.put("INPUTVALUE","A");
							
							j.put("STUDENTID",marks.getStudId()); 
						    j.put("SUBJECTID",String.valueOf(marks.getSubjectId()));
						    j.put("EXAMSCHEDULEID",marks.getScheduleId());
						    j.put("EXAM_TYPE_ID",marks.getExamTypeId());
						    j.put("EXAM_SUB_TYPE_ID",marks.getSubTypeId());
						    if(marks.getSubjectId() == studentDetails.getOptionalSubjectId())
						    	j.put("optionalSubj",true);
						    else
						    	j.put("optionalSubj",false);
						    
						    log.debug("STUDENTID:" + marks.getStudId() + "    SUBJECTID:"+String.valueOf(marks.getSubjectId()) + "   INPUTVALUE:"+marks.getObtainedMarks()); 
							subjectId= String.valueOf(marks.getSubjectId());
							sNo++;
							res.put(j);
							}
							 
						}
						studentMarks=null;
					}
				}
			}
			j = new JSONObject();
			j.put("studentMarksSettingsData", res);
			getResponse().getOutputStream().print(j.toString());
				
			//}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
	
	@Actions( { @Action(value = "ajaxAddStudentMarks", results = { @Result(location = "admin/ajaxDoAddMarks.jsp", name = "success") }) })
	public String ajaxAddStudentMarks() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxAddStudentMarks' method");
		}
		try {
			
			log.debug("Start **********:" + DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_HHMMSS_PATTERN, new Date()));
			if(!StringFunctions.isNullOrEmpty(getTempString())){
			JSONObject formData = new JSONObject(getTempString());
			JSONArray classFeeTypesJsonArray = (JSONArray) formData.get("data");
			long subjectId = 0;
			long studentId = 0;
			long examScheduleId = 0;
			double studentObtainedMarks = 0;
			JSONObject examScheduleJson = null;
		//	JSONObject categoryIdJson = null;
		//	JSONObject classIdJson = null;
			ClassName className = null;
			String categotyId = null;
			FeeType feeType = null;
			SchoolTerms schoolTerms = null;
			AcademicYear academicYear = null;
			int add = 0;
			int update = 0;
			List<String> classAndExamType = new ArrayList<String>();
			HashMap<Integer, String> response = new HashMap<Integer, String>();
			//Pattern pattern = Pattern.compile(".*[^0-9].*");
			Pattern pattern = Pattern.compile("T(-?[0-9]*)");
			
            HashMap<Long, ExamSchedules> schedulesMap = new HashMap<Long, ExamSchedules>();
		    
		    ExamSchedules examSchedules=null;
		    
		    Map<Long,Student> studentMap = new HashMap<Long, Student>();
			
			if(!ObjectFunctions.isNullOrEmpty(classFeeTypesJsonArray))
			{
				String[] anyIds = new String[1];
				anyIds[0] = getParamValue("examType");
				for (int i = 0; i < classFeeTypesJsonArray.length(); i++) 
				{
					String isPresent ="P";
					examScheduleJson = classFeeTypesJsonArray.getJSONObject(i);
					if (!ObjectFunctions.isNullOrEmpty(examScheduleJson)) {
						if (!ObjectFunctions.isNullOrEmpty(examScheduleJson.get("EXAMSCHEDULEID"))) {
							examScheduleId = Long.valueOf((String) examScheduleJson.get("EXAMSCHEDULEID"));
						}
						if (!ObjectFunctions.isNullOrEmpty(examScheduleJson.get("SUBJECTID"))) {
							subjectId = Long.valueOf((String) examScheduleJson.get("SUBJECTID"));
						}
						if (!ObjectFunctions.isNullOrEmpty(examScheduleJson.get("STUDENTID"))) {
							studentId = Long.valueOf((String) examScheduleJson.get("STUDENTID"));
						}
						//Student student =(Student) adminManager.get(Student.class,"id = "+ studentId);
						
						Student student = null;
						if(!ObjectFunctions.isNullOrEmpty(studentMap.get(studentId)))
							student = studentMap.get(studentId);
						else
						{
							student =(Student) adminManager.get(Student.class,"id = "+ studentId);
							studentMap.put(studentId,student);
						}
						if (!ObjectFunctions.isNullOrEmpty(examScheduleJson.get("STUDENTMARKS"))) 
						{
							//http://javarevisited.blogspot.in/2012/10/regular-expression-example-in-java-to-check-String-number.html
							if(!pattern.matcher((String) examScheduleJson.get("STUDENTMARKS")).matches() && !"A".equalsIgnoreCase((String) examScheduleJson.get("STUDENTMARKS"))){
								String obtMarks = (String) examScheduleJson.get("STUDENTMARKS");
								String[] obtMarksArray  = obtMarks.split("\\.");
								if(obtMarksArray.length > 2)
								{
									obtMarks = obtMarksArray[0]+"."+obtMarksArray[1];
								}
								studentObtainedMarks = Double.valueOf(obtMarks);
							}
							else
							{
								isPresent ="A";
							}
							
							if(!ObjectFunctions.isNullOrEmpty(student))
							{
								student = adminManager.upadateStudentMarksFromSMSAppAndWeb(student, examScheduleId, studentObtainedMarks, getUser().getId(), anyIds, isPresent, response,schedulesMap);
								student.setPopUpDisplay(Constants.ACTIVE_STATUS);
								if(response.get(0).equalsIgnoreCase("true"))
									if(!classAndExamType.contains(student.getClassSectionId()+"_"+response.get(1)))
										classAndExamType.add(student.getClassSectionId()+"_"+response.get(1));
								
								student=adminManager.saveStudentDetails(student);
							}
							student = null;
						}
						else
						{
							//List<Integer[]> marksId = adminManager.getMarksIdByStudentIdAndExamScheduleId(student.getId(),examScheduleId);
							//if(!ObjectFunctions.isNullOrEmpty(marksId)){	
								Object[] maxSortOrder = adminManager.get("select id,obtainedMarks from studentMarks where studId = "+ student.getId() +" and examScheduleId="+examScheduleId);
								
								//StudentMarks studentMarks= (StudentMarks)adminManager.get(StudentMarks.class,Long.valueOf(marksId.toString().replaceAll("\\[|\\]|,|\\s", "")));
								if(!ObjectFunctions.isNullOrEmpty(maxSortOrder)){
									adminManager.remove("studentMarks", "id="+maxSortOrder[0].toString());
								}
								maxSortOrder = null;
							//}
							//marksId = null;
						}
					}
					studentObtainedMarks = 0;
				}
			}
			studentMap = null;
			/*if(classAndExamType.size()>0){
				for(String item : classAndExamType)
					calculateStudentsSubjectPoistionByExamScheule(Long.valueOf(item.split("_")[0]), Long.valueOf(item.split("_")[1]));
				
				//result.put("status", "success");
				//result.put("message", "");
			}else{
				//result.put("status", "fail");
				//result.put("message", "There was an error while submitting student marks.");
			}*/
			classAndExamType = null;
			//Sending Notification to mobile for student marks addition
	    	//adminManager.sendNotificationForStudentMarks(getUserCustId());
			
			//Sending Notification to mobile for student marks addition //EAZ-2269
			StudentMarksNotificationThread R1 = new StudentMarksNotificationThread(getUserCustId());
		    R1.start();
			super.addActionMessage("Student marks updated successfully.");
			
			log.debug("End **********:" + DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_HHMMSS_PATTERN, new Date()));
		 }
	  }
		catch (Exception ex) {
		  ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	   }
		finally
		{
			setClassId(null);
			ajaxDownloadMarksSheet();
		}
		return SUCCESS;
	}
	 @Actions({ @Action(value = "ajaxDownloadStudentScorecardFromUserFiles", results = {}) })
		public String ajaxDownloadStudentScorecardFromUserFiles() throws URTUniversalException {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxDownloadStudentScorecardFromUserFiles' method");
			}
			try {
				String fileName = getTempString();
				FileInputStream fileInputStream = null;
				File file = new File(getSession().getServletContext().getRealPath(fileName));
				log.debug(file.getPath());
				getResponse().setContentType("application/pdf");
				getResponse().addHeader("Content-Disposition", "attachment; filename=STUDENT_REPORT.pdf");
				fileInputStream = new FileInputStream(file);
				OutputStream responseOutputStream = getResponse().getOutputStream();
				int bytes;
	  			while ((bytes = fileInputStream.read()) != -1) {
	  				responseOutputStream.write(bytes);
	  			}
	  			fileInputStream.close();
		  		responseOutputStream.flush();
			} catch (Exception ex) {
				ex.printStackTrace();
				RayGunException raygex = new RayGunException();
				raygex.sendRayGunException(ex);
				raygex = null;
			}
			return null;
		}
	 
	 @Actions( { @Action(value = "ajaxDoGetStudentsForThisClassExamTypes", results = { @Result(location = "admin/classWithExamTypeStudentsList.jsp", name = "success") }) })
	  public String ajaxDoGetStudentsForThisClassExamTypes() {
		   if (log.isDebugEnabled()) {
			   log.debug("Entering 'ajaxDoGetStudentsForThisClassExamTypes' method");
		   }
		   try {
			   if(!ObjectFunctions.isNullOrEmpty(getTempId()) && !ObjectFunctions.isNullOrEmpty(getTempString())){
				   
				   Customer customer = (Customer) adminManager.get(Customer.class,"id="+getUserCustId());
				   StringBuffer query = new StringBuffer("select p.firstName,p.lastName,s.accountId,a.admissionNumber,sm.studId,");
					if("B".equalsIgnoreCase(customer.getMobileType())){
						query.append(" p.mobileNumber,p.secondaryMobileNumber,p.anotherMobileNumber,p.anotherSecondaryMobileNumber");
					}else if("P".equalsIgnoreCase(customer.getMobileType()))
						query.append(" p.mobileNumber,p.anotherMobileNumber");
					else{
						query.append(" p.secondaryMobileNumber,p.anotherSecondaryMobileNumber");
					}
					query.append(" from studentMarks sm JOIN  student s ON (sm.studId=s.id and s.description is null and s.status='Y') JOIN Account a ON (a.id=s.accountId) ");
					query.append(" JOIN Person p ON (a.personId=p.id) JOIN examSchedules es ON (es.classSectionId = s.classSectionId and es.custId=s.custId and es.academicYearId=s.academicYearId)");
					query.append(" JOIN examTypes et ON (et.id=es.examTypeId)");
					query.append(" JOIN studySubject ss ON (ss.id = es.classSubjectId)");
					query.append(" JOIN subType st ON (st.id = es.subTypeId)");
					query.append(" JOIN studyClass sc ON (sc.id = s.classSectionId) where s.classSectionId="+getTempId()+" and  es.examTypeId="+getTempString());
					query.append(" group by sm.studId order by sm.studId desc ");
							//+ "vw_studentClassDetails vsc on (vsm.studid=vsc.studId and vsm.custId=vsc.custId ) where vsm.custId="+getUserCustId()+" and vsm.academicYearId="+getUserAcademicYearId()+" and vsm.classSectionId="+getTempId()+" and vsm.examTypeId="+getTempString()+" and vsm.status='Y' and vsm.studDiscontinueDesc is null group by vsc.studId order by vsc.studId desc");
					log.debug(query.toString());
					List<Object[]> studentsList = adminManager.getAll(query.toString());
					setStudentsList(studentsList);
					log.debug("Size of the student list " +studentsList.size());
					setAvailableSMSCount(adminManager.getAvailableSmsCount(getUserCustId(), getUserAcademicYearId()));
			   }
			  
		   } catch (Exception ex) {
			   ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			   JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		   } 
		   return SUCCESS;
	  }
	 
	 @Actions( {@Action(value = "ajaxDoGetHallTicketGeneration", results = {@Result(location = "../admin/academic/hallticket/ajaxHallTickertGenerationSettings.jsp", name = "success") })})
		public String ajaxDoGetHallTicketGeneration() throws URTUniversalException {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxDoGetHallTicketGeneration' method");
			}
			try {
				if (getUserAcademicYearId() > 0) {
					setClassList(adminManager.getAllClassNames( getUserCustId(),getUserAcademicYearId()));
					StringBuffer query = new StringBuffer("custId=").append(getUserCustId());
					setObjectList(adminManager.getAll(HallTicketSettings.class, query.toString()));
					query = null;
				}
			} catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
				JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
			}
			return SUCCESS;
		}
	 
	 @Actions( {@Action(value = "ajaxHallTicketSettings", results = {@Result(location = "../admin/academic/hallticket/ajaxHallTicketUploadSettings.jsp", name = "success") }),
		   @Action(value = "ajaxDoHallTicketTemplateAndBookSettings", results = {@Result(location = "../admin/academic/hallticket/ajaxAddHallTicketSettings.jsp", name = "success")})
		   })
		public String ajaxHallTicketSettings() throws URTUniversalException {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxHallTicketSettings' method");
			}
			try {
				setClassList(adminManager.getAllClassNames( getUserCustId(),getUserAcademicYearId()));
				StringBuffer query = new StringBuffer("custId=").append(getUserCustId());
				if("templateSettings".equalsIgnoreCase(getTempString()))
					setObjectList(adminManager.getAll(HallTicketSettings.class,query.toString()));
				query = null;
			} catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
				JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
			}
			return SUCCESS;
		}
	 @Actions( {@Action(value = "ajaxDeleteHTTemplate", results = {@Result(location = "../admin/academic/hallticket/ajaxHallTicketUploadSettings.jsp", name = "success") })})
		public String ajaxDeleteHTTemplate() throws URTUniversalException {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxDeleteHTTemplate' method");
			}
			try {
				if(getTempId()>0){
					File templateFile= null;
					StringBuffer htCustomerPath = null;
					HallTicketSettings htSetting = (HallTicketSettings)adminManager.get(HallTicketSettings.class, getTempId());
					//Customer customer = getCustomerByCustId();
					if(!ObjectFunctions.isNullOrEmpty(htSetting))
					{
						/*S3Wrapper s3Wrapper = new S3Wrapper();
						URL url = new URL(htSetting.getFilePath());
						s3Wrapper.delete(url);*/
						
						/*htCustomerPath = new StringBuffer("userfiles/HTTemplate/").append(customer.getCustomerShortName());
						if(StringFunctions.isNotNullOrEmpty(htSetting.getFileName())){
							templateFile= new File(getSession().getServletContext().getRealPath(new StringBuffer(htCustomerPath).append("/").append(htSetting.getFileName()).toString()));
							FileUtils.deleteQuietly(templateFile);
						}*/
					}
					htSetting = null;
					templateFile = null;
					htCustomerPath = null;
					adminManager.remove(HallTicketSettings.class, getTempId());
					super.addActionMessage("Successfully deleted hallTicket Settings.");
					ajaxHallTicketSettings();
				}
			} catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
				JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
			}
			return SUCCESS;
		}
	 @Actions( {@Action(value = "ajaxAddHallTicketSettings", results = {@Result(location = "../admin/academic/hallticket/ajaxHallTicketUploadSettings.jsp", name = "success") })})
	 public String hallTicketSettings() throws URTUniversalException {
	 	if (log.isDebugEnabled()) {
	 		log.debug("Entering 'hallTicketSettings' method");
	 	}
	 	try {
	 		if (StringFunctions.isNotNullOrEmpty(getAnyId())) {
	 			HallTicketSettings htSettings = null;
	 			List<String> classNamesList = Arrays.asList(getAnyId().split(","));  
	 			Customer customer = (Customer)adminManager.get(Customer.class, getUserCustId());
	 			if(!ObjectFunctions.isNullOrEmpty(customer) && StringFunctions.isNotNullOrEmpty(customer.getCustomerShortName())){
	 				//StringBuffer pathName = new StringBuffer("userFiles/HTTemplate/").append(customer.getCustomerShortName()).append("/");
	 				if (getUserAcademicYearId() != 0) 
	 				{
	 					AcademicYear academicYear = getCurrentAcademicYear();
	 					String filePath = null;
	 					MultiPartRequestWrapper multiWrapper = (MultiPartRequestWrapper) ServletActionContext.getRequest();
	 					Enumeration fileParaNames = multiWrapper.getFileParameterNames();
 						while (fileParaNames.hasMoreElements()) {
 							String key = (String) fileParaNames.nextElement();
 							File[] fileObject = multiWrapper.getFiles(key);
 							String[] localSysfileNames = multiWrapper.getFileNames(key);
 							setUploadFileName(StringFunctions.stripSymbols(localSysfileNames[0]));
 							filePath = adminManager.getUploadTemplates(fileObject[0], academicYear.getAcademicYear(), getUploadFileName());
 							//File destDir = new File(getSession().getServletContext().getRealPath(pathName+getUploadFileName()));
 							//FileUtils.copyFile(fileObject[0], destDir);
 						}
 						
	 					for(String className : classNamesList){
	 						htSettings = (HallTicketSettings)adminManager.get(HallTicketSettings.class, "custId="+getUserCustId()+" and classNames='"+className+"'");
	 						if(ObjectFunctions.isNullOrEmpty(htSettings))
	 							htSettings = new HallTicketSettings();
	 						else
	 						{
	 							List<HallTicketSettings> htcSettingsList = adminManager.getAll(HallTicketSettings.class, "custId="+getUserCustId()+" and filePath='"+htSettings.getFilePath()+"'");
	 							if(ObjectFunctions.isNullOrEmpty(htcSettingsList)){
	 							/*S3Wrapper s3Wrapper = new S3Wrapper();
	 							URL url = new URL(htSettings.getFilePath());
	 							s3Wrapper.delete(url);*/
	 							}
	 						}
	 						
	 						htSettings.setFileName(getUploadFileName());
	 						htSettings.setFilePath(filePath);
	 						htSettings.setCustId(getUserCustId());
	 						htSettings.setCreatedById(getUser().getId());
	 						htSettings.setLastUpdatedById(getUser().getId());
	 						htSettings.setClassNames(className);
	 						adminManager.save(htSettings);
	 					}
	 					super.addActionMessage("Successfully created HallTicket settings.");
	 				}
	 				//pathName = null;
	 			}
	 			customer = null;
	 			htSettings = null;
	 		}
	 	} catch (Exception ex) {
	 		ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	 		JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
	 	}finally{
	 		ajaxHallTicketSettings();
	 	}
	 	return SUCCESS;
	 }	
	 
	 @Actions({		
			@Action(value = "ajaxGenerateHallTicket", results = { @Result(location = "academic/hallticket/ajaxDoHallTicketGeneration.jsp", name = "success"),
																   @Result(location = "jasper/marks/hallTicketGenerator.jasper",type="jasper", name = "hallTicketGenerator",params = {"dataSource", "tempList", "format", "PDF"}),
																   @Result(location = "jasper/fee/errorMessageTemplet.jasper", type = "jasper", name = "errorMessage", params = {"dataSource", "alertSendType", "format", "PDF" })}) })
		public String ajaxGenerateHallTicket() throws URTUniversalException {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxGenerateHallTicket' method");
			}
			if(getUserCustId()==26){
				ajaxGenerateHallTicketReport(); // this method used show 8 students hollTickets show in page useing jsper report.
				return "hallTicketGenerator";
			}else{
//			HallTicketSettings htSettings = null;
			StringBuffer query = null;
			InputStream templateFile = null;
			IXDocReport report = null;
			IContext context = null;
			StringBuffer studentDOCXFilePath = null;
			List<StudentsHallTicketDetails> vwStudentList = null;
			OutputStream out = null;
			File directory = null;
			List<String> educationTypeSubjects = null;
			HashMap<Long, String> educationSubjectsMap = new HashMap<Long, String>();
			List<HallTicketSettings> htSettingsList = null;
			HashMap<String, IXDocReport> htSettingsMap = new HashMap<String, IXDocReport>();
			StringBuffer studentsListQuery = null;
			List viewClassExamDetails = null;
			StringBuffer examScheduleQuery = null;
			try {
				Customer customer = getCustomerByCustId();
				if (!ObjectFunctions.isNullOrEmpty(customer) && StringFunctions.isNotNullOrEmpty(customer.getCustomerShortName())) {
					ZipOutputStream zipOutStream = new ZipOutputStream(getResponse().getOutputStream());
					getResponse().setContentType("application/zip");
					if (StringFunctions.isNullOrEmpty(getTempString()))
						getResponse().addHeader("Content-Disposition","attachment; filename=HallTicket.zip");
					else
						getResponse().addHeader("Content-Disposition","attachment; filename="+ getTempString().replace(' ', '_')+ "_HALLTICKET.zip");
					StringBuffer htTemplateFilePath = new StringBuffer("userFiles/HTTemplate/").append(customer.getCustomerShortName()).append("/");
					StringBuffer generatedHTsFilePath = new StringBuffer(htTemplateFilePath).append("temp/");
					File outFile = new File(getSession().getServletContext().getRealPath(generatedHTsFilePath.toString()));
					if(outFile.exists())
					FileUtils.deleteDirectory(outFile);// Removes existing files
					outFile.mkdirs(); // If directories are not available it creates directories
					// For generating HallTicket for classwise
					if (StringFunctions.isNotNullOrEmpty(getClassId()) && StringFunctions.isNotNullOrEmpty(getTempString()) && StringFunctions.isNotNullOrEmpty(getStudentNumber())) {
						query = new StringBuffer("custId=").append(getUserCustId()).append(" and classNames='").append(getTempString()).append("'");
						studentsListQuery = new StringBuffer("custId=").append(getUserCustId()).append(" and academicYearId=").append(getUserAcademicYearId()).append(" and classId=").append(getClassId()).append(" and studId in").append(getStudentNumber());
						log.debug("classId=" + getClassId());

					}
					/*
					  else//For generating HallTickets by search form
					  if(StringFunctions.isNotNullOrEmpty(getStudentNumber()) && StringFunctions.isNotNullOrEmpty(getTempString())){ 
					  query =newStringBuffer("classNames in(").append(getTempString()).append(") and custId=").append(getUserCustId()); studentsListQuery= newStringBuffer("custId=").append(getUserCustId()).append(" and academicYearId=").append(getUserAcademicYearId()).append(" and studId in").append(getStudentNumber()); }
					 */
					if (!ObjectFunctions.isNullOrEmpty(query)) 
					{
						directory = Files.createTempDirectory("HallTicket").toFile();
						 //FileUtils.deleteDirectory(directory);
						 //directory.mkdir();
						 
						htSettingsList = adminManager.getAll(HallTicketSettings.class, query.toString());
						if (!ObjectFunctions.isNullOrEmpty(htSettingsList)) {
							for (HallTicketSettings htSetting : htSettingsList) {
								if (!ObjectFunctions.isNullOrEmpty(htSetting) && StringFunctions.isNotNullOrEmpty(htSetting.getFileName())) 
								{
									
									URL url = new URL(htSetting.getFilePath());
									URLConnection conn = url.openConnection();
									InputStream is = conn.getInputStream();
									
									String ext = FilenameUtils.getExtension(htSetting.getFileName());
									//File file = File.createTempFile(htSetting.getFileName(), "",directory);
									File file = new File(directory,htSetting.getFileName());
									
									/*directory = File.createTempFile(htSetting.getFileName(), null);
									directory.deleteOnExit();*/
									FileUtils.copyInputStreamToFile(is, file);
									
									//directory=new File(getSession().getServletContext().getRealPath(htTemplateFilePath.append(htSetting.getFileName()).toString()));
									if(file.exists())
									{
									templateFile = new FileInputStream(file);
									if (!ObjectFunctions.isNullOrEmpty(templateFile)) {
										report = XDocReportRegistry.getRegistry().loadReport(templateFile,TemplateEngineKind.Velocity);
										FieldsMetadata metadata = new FieldsMetadata();
										metadata.addFieldAsList("examDates.name");
										metadata.addFieldAsList("examDates.examDateAndTime");
										metadata.addFieldAsList("examDates.examDateStr");
										metadata.addFieldAsList("examDates.examStartTime");
										metadata.addFieldAsList("examDates.subTypeName");
										metadata.addFieldAsList("examDates.todayDateString");
										metadata.addFieldAsList("examDates.examStimeAndETime");
										metadata.addFieldAsImage("logo");
										metadata.addFieldAsImage("logo1");
										metadata.setUseImageSize(true);
										metadata.addFieldAsImage("fileImageNotExistsAndRemoveImageTemplate","logo",NullImageBehaviour.RemoveImageTemplate);
										metadata.addFieldAsImage("fileImageNotExistsAndRemoveImageTemplate","logo1",NullImageBehaviour.RemoveImageTemplate);
										report.setFieldsMetadata(metadata);
										htSettingsMap.put(htSetting.getClassNames().trim(), report);
									}
								  }
								}
							}
						}
					}
					if (!ObjectFunctions.isNullOrEmpty(studentsListQuery)){
						studentsListQuery.append(" group by studId");
						log.debug(studentsListQuery.toString());
						vwStudentList = adminManager.getAll(StudentsHallTicketDetails.class, studentsListQuery.toString());
					}
					if (ObjectFunctions.isNullOrEmpty(vwStudentList) || ObjectFunctions.isNullOrEmpty(htSettingsMap)) {
						if (ObjectFunctions.isNullOrEmpty(htSettingsMap))
							adminManager.writeToFile("HallTicket settings are not available. Please add HallTicket settings.",getSession().getServletContext().getRealPath(new StringBuffer(generatedHTsFilePath).append("readMe.doc").toString()));
						if (ObjectFunctions.isNullOrEmpty(vwStudentList))
							adminManager.writeToFile("Students are not avilable for generating HallTicket.",
									getSession().getServletContext().getRealPath(new StringBuffer(generatedHTsFilePath).append("readMe.doc").toString()));
					} else {
						Collections.sort(vwStudentList);
						educationTypeSubjects = adminManager.getAll("select CAST(CONCAT(studyClassId,':',group_concat(CONVERT(subjectName, CHAR) order by sortingOrder)) AS CHAR) from vw_studyClassSubjectDetails where academicYearId="+ getUserAcademicYearId()+ " and educationType is not null and language='N' group by studyClassId");
						if (ObjectFunctions.isNotNullOrEmpty(educationTypeSubjects)) {
							for (String educationSubtyp : educationTypeSubjects) {
								educationSubjectsMap.put(Long.valueOf(educationSubtyp.split(":")[0]),educationSubtyp.split(":")[1]);
							}
						}
						try {
							ViewStudentPersonAccountDetails studentDetails = null;
							for (StudentsHallTicketDetails viewStudentPersonAccountDetails : vwStudentList) {
								log.debug("classSectionId="+ viewStudentPersonAccountDetails.getClassSectionId());
								examScheduleQuery = new StringBuffer();
								examScheduleQuery.append(" custId="+ viewStudentPersonAccountDetails.getCustId()+ " and classSectionId="+ viewStudentPersonAccountDetails.getClassSectionId() + "");
								if (getTempId() > 0)
									examScheduleQuery.append(" and eid="+ getTempId());
								if(!StringFunctions.isNullOrEmpty(getTempString3()))
									examScheduleQuery.append(" and scheduleId in ").append(getTempString3());
								examScheduleQuery.append(" order by examDate");
								log.debug(examScheduleQuery.toString());
								viewClassExamDetails = adminManager.getAll(ViewClassExamDetails.class,examScheduleQuery.toString());
								if (!ObjectFunctions.isNullOrEmpty(viewClassExamDetails)) {
									report = htSettingsMap.get(viewStudentPersonAccountDetails.getClassName().trim());
									if (ObjectFunctions.isNullOrEmpty(report)) {
										studentDOCXFilePath = new StringBuffer(generatedHTsFilePath).append("readMe").append(viewStudentPersonAccountDetails.getClassNameAndSection()).append("_").append(viewStudentPersonAccountDetails.getPersonFullName()).append("_").append(viewStudentPersonAccountDetails.getAdmissionNumber().replaceAll("/", "")).append(".doc");
										if (ObjectFunctions.isNullOrEmpty(report))
											adminManager.writeToFile("Please create HallTicket settings.",getSession().getServletContext().getRealPath(studentDOCXFilePath.toString()));
										continue;
									} else {
										studentDOCXFilePath = new StringBuffer(generatedHTsFilePath).append("HallTicket_").append(viewStudentPersonAccountDetails.getClassNameAndSection()).append("_").append(viewStudentPersonAccountDetails.getPersonFullName()).append("_").append(viewStudentPersonAccountDetails.getAdmissionNumber().replaceAll("/", "")).append(".doc");
										try {
											studentDetails = new ViewStudentPersonAccountDetails();
											// Student Name
											studentDetails.setFirstName(viewStudentPersonAccountDetails.getPersonFirstLastNameOnly());
											// Class and Section
											studentDetails.setClassNameAndSection(viewStudentPersonAccountDetails.getClassNameAndSection());
											// Register Number
											studentDetails.setRegisterNumber(viewStudentPersonAccountDetails.getRegisterNumber());
											// Organization
											studentDetails.setOrganization(viewStudentPersonAccountDetails.getOrganization());
											// Duration
											studentDetails.setDescription(viewStudentPersonAccountDetails.getExamDateAndTime());
											// Exam Type
											studentDetails.setTodayDateDOB(viewStudentPersonAccountDetails.getExamType());
											// Exam Type
											
											studentDetails.setFatherName(StringFunctions.isNullOrEmpty(viewStudentPersonAccountDetails.getFatherName())? "": viewStudentPersonAccountDetails.getFatherName());
											studentDetails.setMotherName(StringFunctions.isNullOrEmpty(viewStudentPersonAccountDetails.getMotherName())? "": viewStudentPersonAccountDetails.getMotherName());
											// Address
											if ("NULL".equalsIgnoreCase(viewStudentPersonAccountDetails.getOrganizationCustAddressLineCityAndPostalCode()))studentDetails.setAddressLine1("");
											else
												studentDetails.setAddressLine1(viewStudentPersonAccountDetails.getOrganizationCustAddressLineCityAndPostalCode());
											// DateOfBirth
											studentDetails.setDateOfBirth(viewStudentPersonAccountDetails.getDateOfBirth());
											// HallTicket Number
											studentDetails.setRollNumber(viewStudentPersonAccountDetails.getRollNumber());
											studentDetails.setAdmissionNumber(viewStudentPersonAccountDetails.getAdmissionNumber());
											
											studentDetails.setClassNameAndSection(viewStudentPersonAccountDetails.getClassNameAndSection());
											studentDetails.setTypeOfExam(viewStudentPersonAccountDetails.getExamTypeAndSubTypeName());
											context = report.createContext();
											if (StringFunctions.isNotNullOrEmpty(viewStudentPersonAccountDetails.getSchoolIdCardAttachmentFilePath())) 
											{
												//File imageFile = new File(viewStudentPersonAccountDetails.getSchoolIdCardAttachmentFilePath());
												try {
													File imageFile = adminManager.loadImageFromURL(viewStudentPersonAccountDetails.getSchoolIdCardAttachmentFilePath());
													if (!ObjectFunctions.isNullOrEmpty(imageFile)&& imageFile.exists()) {
														IImageProvider logo = new FileImageProvider(imageFile);
														if (!ObjectFunctions.isNullOrEmpty(logo))context.put("logo", logo);
														//imageFile.delete();
													}
												} catch (Exception e) {
													e.printStackTrace();
												}
											}
											
											try {
												File imageFile1 = null;
												if (StringFunctions.isNotNullOrEmpty(customer.getCustomerFilePathLogo())) {
													imageFile1 = new File(customer.getCustomerFilePathLogo());
												} else {
													imageFile1 = new File(UserImage.getImageNotFoundFile());
												}
												if (!ObjectFunctions.isNullOrEmpty(imageFile1) && imageFile1.exists()) {
													IImageProvider logo1 = new FileImageProvider(imageFile1);
													if (!ObjectFunctions.isNullOrEmpty(logo1))
														context.put("logo1", logo1);
													imageFile1.delete();
												}
											} catch (Exception e) {
												e.printStackTrace();
											}
											
											context.put("student", studentDetails);
											context.put("examDates",viewClassExamDetails);
											out = new FileOutputStream(new File(getSession().getServletContext().getRealPath(studentDOCXFilePath.toString())));
											report.process(context, out);
										} catch (Exception ex) {
											ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
											JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
										} finally {
											out.close();
										}
										studentDOCXFilePath = null;
										viewStudentPersonAccountDetails = null;
									}
								}
							}
						} catch (Exception ex) {
							ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
							JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
						}
					}
					if(!ObjectFunctions.isNullOrEmpty(directory))
					FileUtils.deleteDirectory(directory);
					// For generating zip file
					File zipDirectory = new File(getSession().getServletContext().getRealPath(generatedHTsFilePath.toString()));
					StringFunctions.zipFiles(zipDirectory, zipOutStream);
					FileUtils.deleteDirectory(zipDirectory);
					zipOutStream = null;
					customer = null;
					htTemplateFilePath = null;
					generatedHTsFilePath = null;
				}
			} catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
				JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
			} finally {
				studentsListQuery = null;
				htSettingsList = null;
				htSettingsMap = null;
				educationSubjectsMap = null;
				educationTypeSubjects = null;
				directory = null;
			//	htSettings = null;
				query = null;
				templateFile = null;
				report = null;
				context = null;
				studentDOCXFilePath = null;
				vwStudentList = null;
			} 
			}
			return null;
		}
	 
	 public String ajaxGenerateHallTicketReport() throws URTUniversalException {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxGenerateHallTicket1' method");
			}
			try
			{
				if(StringFunctions.isNotNullOrEmpty(getClassId()) && StringFunctions.isNotNullOrEmpty(getStudentNumber())){
					AcademicYear academicYear=(AcademicYear)adminManager.get(AcademicYear.class, getUserAcademicYearId());
					setTempString(academicYear.getAcademicYear());
					setTempList(adminManager.getAll(StudentsHallTicketDetails.class,"custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and classId ="+getClassId()+" and studId in"+getStudentNumber()+"  group by studId"));
					academicYear=null;
				}
				if(ObjectFunctions.isNullOrEmpty(getTempList())){
					setAlertSendType("Currently there are no students");
					getResponse().setHeader("Content-Disposition","attachment; filename=studentshollTicketsReport"+ StringFunctions.getReplaceAll(DateFormatter.getTodayDateStr(DateFormatter.DD_MM_YYYY_HHMMSS_PATTERN)," ", "-") + ".pdf");
					return "errorMessage";
				}
				 getResponse().setHeader("Content-Disposition","attachment; filename=studentshollTicketsReport"+ StringFunctions.getReplaceAll(DateFormatter.getTodayDateStr(DateFormatter.DD_MM_YYYY_HHMMSS_PATTERN)," ", "-") + ".pdf");
			}
			catch(Exception ex)
			{
				log.error("Entering into 'catch block':"+ex.getMessage());
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
				JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
			}		
			return "hallTicketGenerator";
		}
	 @Actions( {
			@Action(value = "ajaxGetStudentPendingDues", results = {@Result(location = "../admin/academic/hallticket/ajaxViewStudentPendingDues.jsp", name = "success") })  })
	public String ajaxGetStudentPendingDues() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxGetStudentPendingDues' method");
		}
		try {
			if(StringFunctions.isNotNullOrEmpty(getClassId())){
				setTempString(getTempString().replace("$", "&").trim());
				ClassName className= (ClassName)adminManager.get(ClassName.class, "id="+getClassId());
				if(!ObjectFunctions.isNullOrEmpty(className))
				{
					HallTicketSettings hallTicketSettings = (HallTicketSettings)adminManager.get(HallTicketSettings.class,"custId="+getUserCustId()+" and classNames='"+className.getClassName()+"'");
					if(!ObjectFunctions.isNullOrEmpty(hallTicketSettings))
					{
						setStudyClassList(adminManager.getAll(StudyClass.class, "custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and classNameClassId="+getClassId()));
					}
					hallTicketSettings = null;
				}
				className = null;
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		return SUCCESS;
	}
	 @Actions( {
			@Action(value = "ajaxGetStudentExamPendingDues", results = {@Result(location = "../admin/academic/hallticket/ajaxViewHallticketStudents.jsp", name = "success") }) })
	public String ajaxGetStudentExamPendingDues() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxGetStudentExamPendingDues' method");
		}
		try {
			// For getting className we can use tempString variable.
			List<ViewStudentFeePaidAndNotPaidDetails> studentsList = null;
			Date newDate = new Date();
			StringBuffer sqlQuery = null;
			setTempId(getTempId());
			ClassName className = null;
			if(StringFunctions.isNotNullOrEmpty(getStudyClassId()) || StringFunctions.isNotNullOrEmpty(getClassId())){
				sqlQuery = new StringBuffer("select  vf.className,vf.section,vf.admissionNumber,vf.firstName,vf.lastName,vf.paymentStatus," +
						"vf.studId,vf.classId,vf.feeId,vf.studDiscontinueDesc from (select  vsf.className,vsf.section,vsf.admissionNumber," +
						"vsf.firstName,vsf.lastName,vsf.paymentStatus,vsf.studId,vsf.classId,vsf.feeId,vsf.registerNumber,vsf.studDiscontinueDesc " +
						"from vw_studyCertificateForStudentFeePaidAndNotPaidDetails as vsf ").append(" where vsf.custId=").append(getUserCustId())
						.append(" and (vsf.feeType!='N' or vsf.feeType is NULL)");
				if (StringFunctions.isNotNullOrEmpty(getClassId())) {
					className = (ClassName) adminManager.get(ClassName.class, Long.valueOf(getClassId()));
					if (!ObjectFunctions.isNullOrEmpty(className)) {
						if(!StringFunctions.isNullOrEmpty(getTempString2()))
							sqlQuery.append(" and vsf.classSectionId in ").append(getTempString2());
						sqlQuery.append(" and (vsf.feeType!='N' or vsf.feeType is NULL)").append(" and vsf.classId =").append(getClassId())
								.append(" and academicYearId=").append(getUserAcademicYearId()).append(" and vsf.studDiscontinueDesc is NULL")
								.append(" order by vsf.paymentStatus) as vf group by vf.studId order by IF((registerNumber IS NULL or registerNumber = ''),firstName,registerNumber)");
						log.debug(sqlQuery.toString());
						studentsList = adminManager.getAll(sqlQuery.toString());
						if(!StringFunctions.isNullOrEmpty(getPlTitle())){
							if ("HT".equalsIgnoreCase(getPlTitle().trim())) {
								setHallTicketSettings(((HallTicketSettings) adminManager.get(HallTicketSettings.class, "custId="+ getUserCustId() + " and classNames='"+ className.getClassName()+ "'")));
								if (!ObjectFunctions.isNullOrEmpty(getHallTicketSettings())) {
									setViewClassExamDetails(adminManager.getAll(ViewClassExamDetails.class, "custId="+ getUserCustId() + " and academicYearId="+ getUserAcademicYearId() + " and classId="+ getClassId() + "  group by examType"));//and examDate>='" + today+ " 00:00:00'
									if(!ObjectFunctions.isNullOrEmpty(getViewClassExamDetails())){
										if(getTempId() ==0)
											setTempId(getViewClassExamDetails().get(0).getEid());
											ExamTypes examTypes= (ExamTypes)adminManager.get(ExamTypes.class, getTempId());
											setObjectList(adminManager.getAllSectionsExamSchedulesDetails(getUserCustId(),getUserAcademicYearId(),getTempString2(),examTypes.getId(),getTempString1()));
										setSubTypesList(ConvertUtil.convertSetToList(examTypes.getExamSubTypes()));
										setStudentsList(studentsList);
									}
									
								} else {
									super.addActionError("Hallticket template is not uploaded. Please upload the file and generate the hallticket.");
								}
							}  
						}	
					}
				}
				return SUCCESS;
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		return null;
	}
	 @Actions( {
			@Action(value = "ajaxGetSubtypeSubjectDetails", results = {@Result(location = "../admin/academic/hallticket/ajaxViewSubtypeSubjects.jsp", name = "success") }) })
	public String ajaxGetSubtypeSubjectDetails() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxGetSubtypeSubjectDetails' method");
		}
		try {
			// For getting className we can use tempString variable.
			ClassName className = null;
			if(StringFunctions.isNotNullOrEmpty(getClassId())){
				className = (ClassName) adminManager.get(ClassName.class, Long.valueOf(getClassId()));
				if (!ObjectFunctions.isNullOrEmpty(className)) {
					if(!StringFunctions.isNullOrEmpty(getTempString2())){
						if(getTempId() ==0)
							setTempId(getViewClassExamDetails().get(0).getEid());
							ExamTypes examTypes= (ExamTypes)adminManager.get(ExamTypes.class, getTempId());
							setObjectList(adminManager.getAllSectionsExamSchedulesDetails(getUserCustId(),getUserAcademicYearId(),getTempString2(),examTypes.getId(),getTempString1()));
					}
				}
			return SUCCESS;
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		return null;
	} 

	public String uploadStudentMarksNew() {
		if (log.isInfoEnabled())
			log.debug("Entering 'uploadMarksSheetNew' method");
		try {
			log.debug("Start **********:"+ DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_HHMMSS_PATTERN,new Date()));
			log.debug("file Type =" + getUploadContentType());
			boolean excelFileType = false;
			excelFileType = validateExcelFileType(getUploadContentType());
			if (excelFileType) {
				log.debug("No file to upload....");
				super.addActionError("File type not matched.");
				return "dummyInit";
			}

			if (getUserAcademicYearId() > 0) {
				long studentId = 0;
				FileInputStream file = new FileInputStream(getUpload());
				// Get the workbook instance for XLS file
				org.apache.poi.ss.usermodel.Workbook workbook = WorkbookFactory.create(file);
				org.apache.poi.ss.usermodel.Sheet sheet = workbook.getSheetAt(0);

				Row row = null;
				int colmnSize = 0;
				String ids, scheduleId = null;
				String[] anyIds = null;
				Cell cell = null;
				double obtainedMarks = 0;
				long examScheduleId = 0;
				Row scheduleIdsRow = null;
				Student student = null;
				StringBuffer errorMsg = new StringBuffer();
				String isPresent = "P";
				List<Student> studentList = null;
				List<ViewStudentMarks> studentMarksList = null;
				HashMap<Integer, String> showingResult = new HashMap<Integer, String>();
				HashMap<Long, Student> studentsList = new HashMap<Long, Student>();

				HashMap<Long, ExamSchedules> schedulesMap = new HashMap<Long, ExamSchedules>();

				for (int sheetNum = 0; sheetNum < workbook.getNumberOfSheets(); sheetNum++) {
					sheet = workbook.getSheetAt(sheetNum);
					scheduleIdsRow = sheet.getRow(3);

					Integer classSectionId = 0;
					long examTypeId = 0;
					if (!ObjectFunctions.isNullOrEmpty(scheduleIdsRow)) {
						if (!ObjectFunctions.isNullOrEmpty(scheduleIdsRow.getCell(0))) {
							if (scheduleIdsRow.getCell(0).getCellType() == Cell.CELL_TYPE_NUMERIC) {
								if (!ObjectFunctions.isNullOrEmpty(scheduleIdsRow.getCell(0).getNumericCellValue()))
									classSectionId = Double.valueOf(scheduleIdsRow.getCell(0).getNumericCellValue()).intValue();
							}
						}
						if (!ObjectFunctions.isNullOrEmpty(scheduleIdsRow.getCell(1))) {
						// Siva: Reading examtypeId value
							if (scheduleIdsRow.getCell(1).getCellType() == Cell.CELL_TYPE_NUMERIC) {
								if (!ObjectFunctions.isNullOrEmpty(scheduleIdsRow.getCell(1).getNumericCellValue()))
									examTypeId = Double.valueOf(scheduleIdsRow.getCell(1).getNumericCellValue()).longValue();
							}
						}
					}

					log.debug("ClassSectionId Is Reading ....."+ classSectionId);
					StringBuffer query = new StringBuffer("custId=").append(getUserCustId()).append(" and academicYearId=").append(getUserAcademicYearId()).append(" and classSectionId=").append(classSectionId).append(" and description is null");
					studentList = adminManager.getAll(Student.class,query.toString());
					query = null;

					if (!ObjectFunctions.isNullOrEmpty(studentList)) {
						for (Student studentObj : studentList) {
							studentsList.put(studentObj.getId(), studentObj);
						}
					}

					// Removing existing obtained marks
					if (classSectionId > 0 && examTypeId > 0)
						adminManager.remove("studentMarks","examScheduleId in (select id from examSchedules where classSectionId="+ classSectionId + " AND examTypeId="+ examTypeId + ")");
					// End
					List<ExamSchedules> schedules = adminManager.getAll(ExamSchedules.class, "classSectionId="+ classSectionId + " AND examTypeId="+ examTypeId);
					if (!ObjectFunctions.isNullOrEmpty(schedules)) {
						for (ExamSchedules examSchedules : schedules) {
							schedulesMap.put(examSchedules.getId(),examSchedules);
							examSchedules = null;
						}
					}
					schedules = null;
					for (int rowNum = 6; rowNum < sheet.getPhysicalNumberOfRows(); rowNum++) {
						row = sheet.getRow(rowNum);
						if (!ObjectFunctions.isNullOrEmpty(row) && !ObjectFunctions.isNullOrEmpty(row.getCell(3))) {
							if (row.getCell(3).getCellType() == Cell.CELL_TYPE_NUMERIC) {

								studentId = Double.valueOf(row.getCell(3).getNumericCellValue()).intValue();
								if (studentId > 0) {
									if (!ObjectFunctions.isNullOrEmpty(studentsList) && !ObjectFunctions.isNullOrEmpty(studentsList.get(studentId)))
										student = studentsList.get(studentId);
									if (!ObjectFunctions.isNullOrEmpty(student)) 
									{
										student.setPopUpDisplay(Constants.ACTIVE_STATUS);
										colmnSize = row.getLastCellNum();
										for (int colNum = 4; colNum < colmnSize; colNum++) {
											cell = row.getCell(colNum);
											if (!ObjectFunctions.isNullOrEmpty(cell) && !ObjectFunctions.isNullOrEmpty(scheduleIdsRow) && !ObjectFunctions.isNullOrEmpty(scheduleIdsRow.getCell(colNum))) {
												ids = scheduleIdsRow.getCell(colNum).getStringCellValue();
												if (StringFunctions.isNotNullOrEmpty(ids)) {
													if (cell.getCellType() != Cell.CELL_TYPE_BLANK || cell.getStringCellValue() == "" || cell == null) {
														anyIds = ids.split("_");
														if (!ObjectFunctions.isNullOrEmpty(anyIds) && !ObjectFunctions.isNullOrEmpty(anyIds.length > 1) && !ObjectFunctions.isNullOrEmpty(anyIds[1])) {
															examScheduleId = Long.valueOf(anyIds[1]);
															if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
																obtainedMarks = cell.getNumericCellValue();
																isPresent = "P";
															} else {
																obtainedMarks = 0;
																isPresent = "A";
															}
	
															student = adminManager.upadateStudentMarksFromSMSAppAndWebNew(student,examScheduleId,obtainedMarks,getUser().getId(),examTypeId,isPresent,showingResult,schedulesMap);
															if (StringFunctions.isNotNullOrEmpty(student.getErrorMsg()))
																errorMsg.append(student.getErrorMsg()+ ",");
														}
													}
												}
											}
										}
										adminManager.saveStudentDetails(student);
									}
								} else {
									log.debug("Uploaded wrong file..");
									super.addActionError("You have uploaded wrong file. Please download latest sheet from system and upload.");
									return SUCCESS;
								}
							}
						}
					}
					sheet = null;
					log.debug("Few student(s) data not valid. Please verify the data:"+ StringFunctions.removeDuplicateWords(errorMsg.toString(), ","));

					if (errorMsg.length() > 0) {
						errorMsg.insert(0,"Few student(s) data not valid. Please verify the data:");
						super.addActionError(StringFunctions.removeDuplicateWords(errorMsg.toString(), ","));
					}

					if (!ObjectFunctions.isNullOrEmpty(showingResult)) {
						if (showingResult.get(0).equalsIgnoreCase("true")) {
							// Sending Notification to mobile for student marks addition Sending Notification to mobile for student marks addition
							StudentMarksNotificationThread R1 = new StudentMarksNotificationThread(getUserCustId());
							R1.start();
							// adminManager.sendNotificationForStudentMarks(getUserCustId());
							super.addActionMessage("Studentmarks submitted successfully.");

						} else
							super.addActionError("Student marks are not submitted due to invalid marks .Please recheck and submit again");
					} else
						super.addActionError("Student marks are not submitted due to invalid marks .Please recheck and submit again");
				}
				log.debug("End **********:"+ DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_HHMMSS_PATTERN,new Date()));
			}
		} catch (Exception ex) {
			log.error("Entering into 'catch block':" + ex.getMessage());
			super.addActionError("Student marks are not submitted due to invalid marks .Please recheck and submit again");
			ex.printStackTrace();
			RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		return SUCCESS;

	}
	 @Actions( { @Action(value = "ajaxExamTypeDetails", results = { @Result(location = "ajaxAllExamTypeList.jsp", name = "success") }) })
		public String ajaxExamTypeDetails() {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxExamTypeDetails' method");
			}
			try {
				SimpleDateFormat dateformat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				ExamSchedules examSchedule = null;
				examSchedule = (ExamSchedules) adminManager.get(ExamSchedules.class,"classSectionId="+getStudyClassId()+" and examTypeId="+getTempId()+" order by id desc");
				if(!ObjectFunctions.isNullOrEmpty(examSchedule))
				setTempString(dateformat.format(examSchedule.getStartDate())); // Temptring AS examStartDate
				Date date= new Date();
				setTodayDate(dateformat.format(date));
				examSchedule = null;
			  } catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
				JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
			}
			return SUCCESS;
		}
	 
	  @Actions({@Action(value = "ajaxStudentPerformance", results = { @Result(location = "../admin/performance/ajaxDoStudentPerformance.jsp", name = "success") })})
	  public String ajaxStudentPerformance() throws URTUniversalException {
		  if (log.isDebugEnabled()) {
			  log.debug("Entering 'ajaxStudentPerformance' method");
		  }
		  try
		  {
			  if(getUserAcademicYearId() > 0)
				  setStudyClassList(adminManager.getAll(StudyClass.class, "custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()));
			  if(ObjectFunctions.isNotNullOrEmpty(getStudyClassList()))
				  Collections.sort(getStudyClassList());
		  }
		  catch(Exception ex)
		  {
			  ex.printStackTrace();
		  }
		  return SUCCESS;
	  }
	  /**
	   * 
	   * @return
	   * @throws URTUniversalException
	   */
	  @Actions({@Action(value = "ajaxGetStudentsByClass", results = { @Result(location = "../admin/performance/ajaxDoStudentName.jsp", name = "success") })})
	  public String ajaxGetStudentsByClass() throws URTUniversalException {
		  if (log.isDebugEnabled()) {
			  log.debug("Entering 'ajaxGetStudentsByClass' method");
		  }
		  try
		  {
			  if(!ObjectFunctions.isNullOrEmpty(getClassId())){
				  StringBuffer queryString = new StringBuffer();
				  queryString.append("classSectionId=" + Long.valueOf(getClassId())+ " and custId=" + getUserCustId() + " and academicYearId="+ getUserAcademicYearId() +
						  " and status='"+ Constants.YES_STRING + "' order by firstname ");
				  setStudentsList(adminManager.getAll(ViewStudentClassDetails.class, queryString.toString()));

			  }  

		  }
		  catch(Exception ex)
		  {
			  ex.printStackTrace();
		  }
		  return SUCCESS;
	  }
	  /**
	   * 
	   * @return
	   * @throws URTUniversalException
	   */
	  @Actions({@Action(value = "ajaxGetClassExamTypesDetails", results = { @Result(location = "../admin/performance/ajaxDoExamTypes.jsp", name = "success") })})
	  public String ajaxGetClassExamTypesDetails() throws URTUniversalException {
		  if (log.isDebugEnabled()) {
			  log.debug("Entering 'ajaxGetClassExamTypesDetails' method");
		  }
		  try
		  {
			  setStudyClassSubjectsList(null); 
			  setExamTypeList(getMarksUploadedExamTypesByClassId(Long.valueOf(getClassId())));	   

		  }
		  catch(Exception ex)
		  {
			  ex.printStackTrace();
		  }
		  return SUCCESS;
	  }
	  
	  /**
	   * 
	   * @return
	   * @throws URTUniversalException
	   */
	  @Actions({@Action(value = "ajaxGetClassSubjectDetails", results = { @Result(location = "../admin/performance/ajaxDoExamTypes.jsp", name = "success") }) })
	  public String ajaxGetClassSubjectDetails() throws URTUniversalException {
		  if (log.isDebugEnabled()) {
			  log.debug("Entering 'ajaxGetClassSubjectDetails' method");
		  }
		  try
		  {
			  setExamTypeList(null);
			  setStudyClassSubjectsList(adminManager.getAll(ViewStudyClassSubjects.class, "custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and studyClassId="+getClassId()));
		  }
		  catch(Exception ex)
		  {
			  ex.printStackTrace();
		  }
		  return SUCCESS;
	  }
	  /**
	   * 
	   * @return
	   * @throws URTUniversalException
	   */
	  @Actions({@Action(value = "ajaxStudentPerformanceChart", results = {  @Result(type = "json", name = "success", params = {"includeProperties","wishDescription" }) })})
	  public String ajaxStudentPerformanceChart() throws URTUniversalException {

		  if (log.isDebugEnabled()) {
			  log.debug("Entering 'ajaxStudentPerformanceChart' method");
		  }
		  try
		  {
			  String reportType = getReportType();
			  JSONObject ja = new JSONObject();
			  JSONArray examTypeName=new JSONArray();
			  JSONArray subjectName=new JSONArray();
			  JSONArray marksObtained=new JSONArray();
			  JSONArray totalMarks=new JSONArray();
			  JSONArray totalSeriesArray=new JSONArray();
			  JSONObject seriesObj = new JSONObject();
			  JSONArray allSeriesArray=new JSONArray();

			  StringBuffer queryBuff=new StringBuffer();

			  if("E".equalsIgnoreCase(reportType)){// Exam type compaision
				  queryBuff.append("select examTypeId,examType,SUM(totalSubjectMarksObtained)  as examTotalObtainedMarks,SUM(subTypeMaxMarks)  as examTotalMarks from vw_studentPerformance where custId=");
				  queryBuff.append(custId);	
				  queryBuff.append(getUserCustId());
				  queryBuff.append(" and classSectionId =");
				  queryBuff.append(getClassId());
				  queryBuff.append(" and studId =");
				  queryBuff.append(getStudentNumber());
				  queryBuff.append(" and examTypeId in");
				  queryBuff.append(getSelectedId());
				  queryBuff.append(" group by examTypeId order by examTypeId asc");
				  List<Object[]> resultList=adminManager.getAll(queryBuff.toString());
				  if (!ObjectFunctions.isNullOrEmpty(resultList)) {
					  for(Object[] obj:resultList){
						  examTypeName.put(obj[1].toString());
						  marksObtained.put(Double.parseDouble(obj[2].toString()));
						  totalMarks.put(Double.parseDouble(obj[3].toString()));
					  }
				  }
				  seriesObj.put("name", "Obtained Marks");
				  seriesObj.put("data", marksObtained);
				  totalSeriesArray.put(seriesObj);
				  seriesObj=new JSONObject();
				  seriesObj.put("name", "Total Marks");
				  seriesObj.put("data", totalMarks);
				  totalSeriesArray.put(seriesObj);
				  ja.put("series", totalSeriesArray);
				  ja.put("categories", examTypeName);
				  getResponse().getOutputStream().print(ja.toString());
				  log.debug(ja.toString());
			  }else if("S".equalsIgnoreCase(reportType)){// Subject wise compaision

				  List<StudentMarks> latestStudentMarks= adminManager.getLatestUploadedMarksForStudent(Long.parseLong(getClassId()), getUserAcademicYearId(), 0,Long.parseLong(getStudentNumber()));
				  if(ObjectFunctions.isNotNullOrEmpty(latestStudentMarks)){
					  List<ViewStudyClassSubjects> classSubjects=adminManager.getAllStudyClassSubjects("studyClassId="+Long.parseLong(getClassId())+" and custId="+getUserCustId()+" and subjectId in "+getSelectedId() );
					  if(ObjectFunctions.isNotNullOrEmpty(classSubjects)){
						  Collections.sort(classSubjects,new ViewStudyClassSubjectsComparator());  
						  ja = new JSONObject();
						  JSONArray catSubjects=null;
						  StringBuffer subTypeTool=null;
						  List<StudentMarks> subTypes=null;
						  JSONArray termArray=null;
						  JSONObject termObj =null;
						  StringBuffer query = null;
						  float totalsubjectMarks=0;
						  float percentage=0;
						  float subMaxMarks;
						  float moderationMarks=0;
						  List<StudentMarks> studentMarks=null;
						  ja.put("title", "Student Marks Comparison");
						  totalSeriesArray=new JSONArray();
						  for(StudentMarks latestMarkstypes:latestStudentMarks){
							  catSubjects=new JSONArray();
							  termObj = new JSONObject();
							  termArray=new JSONArray();
							  Collections.sort(classSubjects);
							  for(ViewStudyClassSubjects subject:classSubjects){
								  if(!ObjectFunctions.isNullOrEmpty(subject)){
									  query = new StringBuffer("SELECT marks FROM StudentMarks marks JOIN marks.examSchedule schedule WHERE marks.student=").append(getStudentNumber())
											  .append(" and schedule.examType=").append(latestMarkstypes.getExamTypeId()).append(" and schedule.classSectionSubject=").append(subject.getSubjectId()).append(" group by schedule.subType");
									  subTypes=adminManager.getAllHqlQuery(query.toString());
									  catSubjects.put(subject.getSubjectName());
									  if(ObjectFunctions.isNotNullOrEmpty(subTypes)){
										  totalsubjectMarks=0;
										  moderationMarks=0;
										  subTypeTool=new StringBuffer();
										  subMaxMarks=0;
										  for(StudentMarks examDetails:subTypes){
											  subMaxMarks+=examDetails.getSubTypeMaxMarks();
											  query = new StringBuffer("SELECT marks FROM StudentMarks marks JOIN marks.examSchedule schedule WHERE marks.student=").append(getStudentNumber())
													  .append(" and schedule.examType=").append(examDetails.getExamTypeId()).append(" and schedule.subType=").append(examDetails.getSubTypeId())
													  .append(" and schedule.classSectionSubject=").append(subject.getSubjectId());
											  studentMarks=adminManager.getAllHqlQuery(query.toString());
											  if(!ObjectFunctions.isNullOrEmpty(studentMarks)){
												  for(StudentMarks marks : studentMarks){
													  if(marks.getId() == examDetails.getId()){
														  if(marks.isPresent()){
															  totalsubjectMarks+=marks.getObtainedMarks()+marks.getModerationMarks();
															  subTypeTool.append("<b>"+examDetails.getSubTypeName()+" : </b>"+marks.getObtainedMarks()+"<br/>");
															  moderationMarks+=marks.getModerationMarks();
														  }else{
															  subTypeTool.append("<b>"+examDetails.getSubTypeName()+" : </b> Absent"+"<br/>");
														  }
														  break;
													  }
												  }
											  }
										  }
										  percentage=(totalsubjectMarks/subMaxMarks)*100;
										  if(moderationMarks > 0)
											  subTypeTool.append("<b>Moderation Marks : </b>"+moderationMarks+"<br/>");
										  subTypeTool.append("<b>MaxMarks : </b>"+subMaxMarks);
										  subTypeTool.append("<br/>"+"<b>Percentage : </b>"+roundTwoDecimals(percentage));
										  termArray.put(roundTwoDecimals(totalsubjectMarks));
										  ja.put(latestMarkstypes.getExamTypeName()+subject.getSubjectName(),subTypeTool.toString());
									  }else{
										  termArray.put(0);

									  }
								  }
							  }
							  termObj.put("name",latestMarkstypes.getExamTypeName());
							  termObj.put("data",termArray);
							  totalSeriesArray.put(termObj);
						  }
						  ja.put("series", totalSeriesArray);
						  ja.put("categories", catSubjects);
						  log.debug("ja:"+ja.toString());
						  getResponse().getOutputStream().print(ja.toString());
						  query = null;

					  }
					  latestStudentMarks=null;
				  }
			  }

		  }catch(Exception ex) {
			  ex.printStackTrace();
		  }
		  return null;
	  }
	  @Actions({ @Action(value = "ajaxViewQuestionBankMaterialList", results = { @Result(location = "questionBank/ajaxViewQuestionBankMaterials.jsp", name = "success") })
		})
	  public String viewQuestionBankMaterialList() throws URTUniversalException {
		  if (log.isDebugEnabled()) {
			  log.debug("Entering 'ajaxViewQuestionBankMaterialList' method");
		  }
		  try {
			  if(getUser().isSchoolStudent()){
				  Student student = (Student)studentManager.get(Student.class,"accountId="+getUser().getId()+" and academicYearId="+getUserAcademicYearId()+" ");
				  if(!ObjectFunctions.isNullOrEmpty(student)){
					  setStudyClassList(adminManager.GetAllStudyClasses(getUserCustId(),getUserAcademicYearId(), String.valueOf(student.getClassSectionId())));
				  }
			  }else if("ROLE_TEACHER".equalsIgnoreCase(getUser().getUserRole()) ){
				  Staff staff = adminManager.getStaffByAcountId(getUser().getId(),Constants.YES_STRING);
				  if (!ObjectFunctions.isNullOrEmpty(staff)){
					  StringBuffer query =  new StringBuffer("select studyClassId from classTeacher ").append(" where custId=").append(getUserCustId()).append(" and academicYearId=").append(getUserAcademicYearId()).append(" and teacherId=").append(staff.getId()).append(" group by studyClassId");
					  List<BigInteger> studyclassIds = adminManager.getAll(query.toString());
					  query = null;
					  String classIdsString=null;
					  if (ObjectFunctions.isNotNullOrEmpty(studyclassIds)) 
						  classIdsString = StringFunctions.convertListToCommaDelimitedString(studyclassIds);
					  else
						  classIdsString="0";
					  HashSet<StudyClass> classSections = new HashSet<StudyClass>();
					  List<StudyClass> studyClasseList = adminManager.getAll(StudyClass.class, "id in("+classIdsString+")");
					  if(!ObjectFunctions.isNullOrEmpty(studyClasseList)){
						  classSections.addAll(studyClasseList);
					  }
					  if (ObjectFunctions.isNotNullOrEmpty(classSections)) {
						  setStudyClassList(ConvertUtil.convertSetToList(classSections));
						  Collections.sort(getStudyClassList());
					  }
					  classSections = null;
					  studyClasseList = null;
				  }
				  staff=null;
			  }else if(!"ROLE_ADMIN".equalsIgnoreCase(getUser().getUserRole()) && !"ROLE_ADMINOFFICER".equalsIgnoreCase(getUser().getUserRole()) && !"ROLE_TEACHER".equalsIgnoreCase(getUser().getUserRole())){
				  List<BigInteger> studyClassIdsList = null;
				  Object[] staff = adminManager.get("select id,description from staff where accountId="+getUser().getId()+" and  status='Y'");
				  Long staffId = null;
				  if(!ObjectFunctions.isNullOrEmpty(staff))
				  {
					  if(!ObjectFunctions.isNullOrEmpty(staff[0]))
					  {
						  staffId = Long.valueOf(staff[0].toString());
					  }
				  }
				  if(!ObjectFunctions.isNullOrEmpty(staffId)){
					  StringBuffer query = new StringBuffer("select studyClassId from classTeacher where teacherId in(").append(staffId+")");
					  List<BigInteger> studyClassIds = adminManager.getAll(query.toString());
					  StringBuffer sqlQuery = new StringBuffer("select st.id  from staffMultipleStudyClasses sm LEFT JOIN studyClass st  on (st.id=sm.studyClassId)") 
					  .append(" where st.custId=").append(getUserCustId()).append(" and st.academicYearId="+getUserAcademicYearId()).append(" and sm.staffId=").append(staffId);
	
					  List<BigInteger> coOrdinatorStudyClassIds = adminManager.getAll(sqlQuery.toString());
					  if (ObjectFunctions.isNotNullOrEmpty(studyClassIds) && ObjectFunctions.isNotNullOrEmpty(coOrdinatorStudyClassIds)) {
						  for(BigInteger obj:coOrdinatorStudyClassIds){
							  if(!studyClassIds.contains(obj)){
								  studyClassIds.add(obj);
							  }
						  }
						  studyClassIdsList = studyClassIds;
					  }else if (ObjectFunctions.isNotNullOrEmpty(studyClassIds)) {
						  studyClassIdsList = studyClassIds;
					  }else if (ObjectFunctions.isNotNullOrEmpty(coOrdinatorStudyClassIds)) {
						  studyClassIdsList = coOrdinatorStudyClassIds;
					  } 
					  if (ObjectFunctions.isNotNullOrEmpty(studyClassIdsList)) 
					  {
						  String classSectionIdsString = StringFunctions.convertListToCommaDelimitedString(studyClassIdsList);
						  List<StudyClass> studyClassList = adminManager.GetAllStudyClasses(getUserCustId(),getUserAcademicYearId(),classSectionIdsString);
						  if (ObjectFunctions.isNotNullOrEmpty(studyClassList)) 
						  {
							  for (StudyClass studyClass : studyClassList) 
							  {
								  getStudyClassList().add(studyClass);
							  }
						  }
					  }
				  }
			  }else
				  setStudyClassList(adminManager.GetAllStudyClasses(getUserCustId(),getUserAcademicYearId(), null));
				
		  } catch (Exception ex) {
			  ex.printStackTrace();
		  }
		  return SUCCESS;
	  }		
	  
	  @Actions({@Action(value = "ajaxDoAddQuestionBankMaterial",results ={@Result(location = "questionBank/ajaxDoAddQuestionBankMaterials.jsp",name = "success") })})
	public String ajaxDoAddQuestionBankMaterial()  throws URTUniversalException {
		  if(log.isDebugEnabled()){
			  log.debug("Entering 'ajaxDoAddQuestionBankMaterial' method");
		  }
		  try{	
			  if ("ROLE_ADMIN".equalsIgnoreCase(getUser().getUserRole()) || "ROLE_ADMINOFFICER".equalsIgnoreCase(getUser().getUserRole())) {
				  setStudyClassList(adminManager.getAll(StudyClass.class, "custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()));
			  }else if("ROLE_TEACHER".equalsIgnoreCase(getUser().getUserRole()) ){
				  Staff staff = adminManager.getStaffByAcountId(getUser().getId(),Constants.YES_STRING);
				  if (!ObjectFunctions.isNullOrEmpty(staff)){
					  StringBuffer query =  new StringBuffer("select studyClassId from classTeacher ").append(" where custId=").append(getUserCustId()).append(" and academicYearId=").append(getUserAcademicYearId()).append(" and teacherId=").append(staff.getId()).append(" group by studyClassId");
					  List<BigInteger> studyclassIds = adminManager.getAll(query.toString());
					  query = null;
					  String classIdsString=null;
					  if (ObjectFunctions.isNotNullOrEmpty(studyclassIds)) 
						  classIdsString = StringFunctions.convertListToCommaDelimitedString(studyclassIds);
					  else
						  classIdsString="0";
					  HashSet<StudyClass> classSections = new HashSet<StudyClass>();
					  List<StudyClass> studyClasseList = adminManager.getAll(StudyClass.class, "id in("+classIdsString+")");
					  if(!ObjectFunctions.isNullOrEmpty(studyClasseList)){
						  classSections.addAll(studyClasseList);
					  }
					  if (ObjectFunctions.isNotNullOrEmpty(classSections)) {
						  setStudyClassList(ConvertUtil.convertSetToList(classSections));
						  Collections.sort(getStudyClassList());
					  }
					  classSections = null;
					  studyClasseList = null;
				  }
				  staff=null;
			  }else if ("ROLE_ADMIN_COORDINATOR".equalsIgnoreCase(getUser().getUserRole())) {
				  Staff staff = (Staff)adminManager.get(Staff.class,"accountId="+getUser().getId());
				  if(!ObjectFunctions.isNullOrEmpty(staff))
					  setStudyClassList(getAdminCoordinatorStudyClassesList(staff.getId(),getUserAcademicYearId()));
			  }
			  else{
				  allTeachersAndHodStudyClassesList(getUser().getId(),getUserAcademicYearId());
			  }
		  } catch (Exception ex) {
			  ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			  JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		  }
		  return SUCCESS;
	  }
	  @Actions( { @Action(value = "ajaxGetAllStudySubjects", results = { @Result(location = "questionBank/ajaxGetSubjectList.jsp", name = "success"),
																		 @Result(location = "questionBank/ajaxViewQuestionBankMaterialSubjects.jsp", name = "subjectView")})})
	  public String ajaxGetAllStudySubjects() throws URTUniversalException {
		  if (log.isInfoEnabled()) {
			  log.info("Entering 'ajaxGetAllStudySubjects' method");
		  }
		  try {
			  if(!StringFunctions.isNullOrEmpty(getClassSectionNameId()) && !(getTempId1() > 0)){
				  if("ROLE_ADMIN".equalsIgnoreCase(getUser().getUserRole()) || "ROLE_ADMINOFFICER".equalsIgnoreCase(getUser().getUserRole())){
					  setStudyClassSubjectDetails(adminManager.getAll(ViewStudyClassSubjects.class, "custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and studyClassId="+getClassSectionNameId()));
				  }
				  else if("ROLE_TEACHER".equalsIgnoreCase(getUser().getUserRole()) )
				  {
					  StringBuffer querys = null;
					  StringBuffer subjectIdsString = new StringBuffer();
					  Staff staff = adminManager.getStaffByAcountId(getUser().getId(),Constants.YES_STRING);
					  if (!ObjectFunctions.isNullOrEmpty(staff)){
						  querys = new StringBuffer("select studyClassId,id from classTeacher where custId=").append(getUserCustId()).append(" and academicYearId=").append(getUserAcademicYearId()).append(" and teacherId=").append(staff.getId()).append(" and classTeacher='Y'");
						  Object[] studyClassIds = adminManager.get(querys.toString());
						  if(!ObjectFunctions.isNullOrEmpty(studyClassIds) && !ObjectFunctions.isNullOrEmpty(studyClassIds[0])){
							  StringBuffer qury = new StringBuffer("select subjectId,classSectionId from vw_classSubjectsSettings ").append(" where custId=").append(getUserCustId()).append(" and academicYearId=").append(getUserAcademicYearId()).append(" and classSectionId=").append(studyClassIds[0].toString());
							  List<Object[]> studySubjectIdsList = adminManager.getAll(qury.toString());
							  if(!ObjectFunctions.isNullOrEmpty(studySubjectIdsList)){
								  for(Object[] obj :studySubjectIdsList){
									  if (!ObjectFunctions.isNullOrEmpty(obj[0])) {
										  subjectIdsString.append(obj[0].toString()).append(",");
									  }
								  }
							  }
							  log.debug(subjectIdsString.toString());
							  studySubjectIdsList = null;
						  }
						  StringBuffer query =  new StringBuffer("select studySubjectId,teacherId from classTeacher ").append(" where custId=").append(getUserCustId()).append(" and academicYearId=").append(getUserAcademicYearId()).append(" and teacherId=").append(staff.getId()).append(" and classTeacher='N'").append(" group by studySubjectId");
						  List<Object[]> studySubjectIdsList = adminManager.getAll(query.toString());
						  if(!ObjectFunctions.isNullOrEmpty(studySubjectIdsList)){
							  for(Object[] obj :studySubjectIdsList){
								  if (!ObjectFunctions.isNullOrEmpty(obj[0])) {
									  subjectIdsString.append(obj[0].toString()).append(",");
								  }
							  }
						  }
						  subjectIdsString.append("0");
						  log.debug(subjectIdsString.toString());
						  setStudyClassSubjectDetails(adminManager.getAll(ViewStudyClassSubjects.class, "custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and studyClassId="+getClassSectionNameId()+" and subjectId in("+subjectIdsString+")"));
					  }
					  staff=null;
				  }else if ("ROLE_ADMIN_COORDINATOR".equalsIgnoreCase(getUser().getUserRole())) {
					  List<BigInteger> subjectIds = null;
					  String subjectIdsString ;
					  Staff staff = (Staff)adminManager.get(Staff.class,"accountId="+getUser().getId());
					  if(!ObjectFunctions.isNullOrEmpty(staff)){
						  List<StudyClass> studayClassList = getAdminCoordinatorStudyClassesList(staff.getId(),getUserAcademicYearId());
						  StringBuffer classIds = new StringBuffer();
						  for(StudyClass obj:studayClassList){
							  classIds.append(obj.getId()).append(",");
						  }
						  classIds.append("0");
						  subjectIds = adminManager.getAll("select subjectId from vw_classSubjectsSettings where custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+
								  " and classSectionId in ("+classIds.append(")"));
						  if (ObjectFunctions.isNotNullOrEmpty(subjectIds)) {
							  subjectIdsString = StringFunctions.convertListToCommaDelimitedString(subjectIds);
							  if(!ObjectFunctions.isNullOrEmpty(subjectIdsString))
								  setStudyClassSubjectDetails(adminManager.getAll(ViewStudyClassSubjects.class, "custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and studyClassId="+getClassSectionNameId()+" and subjectId in("+subjectIdsString+")"));
						  }
					  }
				  }else{
					  List<BigInteger> subjectIds = null;
					  String subjectIdsString=null;
					  Object[] StudyClassIds = adminManager.get("select studyClassId,studySubjectId from vw_staffSubjectsDetails where custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and accountId="+getUser().getId()+" and classTeacher='Y' and studyClassId="+getClassSectionNameId()+" group by studyClassId");
					  if(!ObjectFunctions.isNullOrEmpty(StudyClassIds)){
						  if(!ObjectFunctions.isNullOrEmpty(StudyClassIds) && !ObjectFunctions.isNullOrEmpty(StudyClassIds[0].toString()) && !ObjectFunctions.isNullOrEmpty(StudyClassIds[1].toString())){
							  subjectIds = adminManager.getAll("select subjectId from vw_classSubjectsSettings where custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and classSectionId="+StudyClassIds[0].toString());
							  if (ObjectFunctions.isNotNullOrEmpty(subjectIds)) {
								  subjectIdsString = StringFunctions.convertListToCommaDelimitedString(subjectIds);
								  if(!ObjectFunctions.isNullOrEmpty(subjectIdsString))
									  setStudyClassSubjectDetails(adminManager.getAll(ViewStudyClassSubjects.class, "custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and studyClassId="+getClassSectionNameId()+" and subjectId in("+subjectIdsString+")"));
							  }
						  }
						  StudyClassIds = null;
					  }else{
						  subjectIds = adminManager.getAll("select studySubjectId from vw_staffSubjectsDetails where custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and accountId="+getUser().getId()+" and studyClassId="+getClassSectionNameId()+" and classTeacher='N' group by studySubjectId");
						  if (ObjectFunctions.isNotNullOrEmpty(subjectIds)) {
							  subjectIdsString = StringFunctions.convertListToCommaDelimitedString(subjectIds);
							  if(!ObjectFunctions.isNullOrEmpty(subjectIdsString))
								  setStudyClassSubjectDetails(adminManager.getAll(ViewStudyClassSubjects.class, "custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and studyClassId="+getClassSectionNameId()+" and subjectId in("+subjectIdsString+")"));
						  }
					  }
				  }
			  }else{
				  List<ViewQuestionBankDetails> questionBankList = null;
				  HashSet<ViewQuestionBankDetails> classSections = new HashSet<ViewQuestionBankDetails>();
				  if("ROLE_ADMIN".equalsIgnoreCase(getUser().getUserRole()) || "ROLE_ADMINOFFICER".equalsIgnoreCase(getUser().getUserRole()) /*|| "ROLE_PRINCIPAL".equalsIgnoreCase(getUser().getUserRole()) || "ROLE_VICEPRINCIPAL".equalsIgnoreCase(getUser().getUserRole()) || "ROLE_ADMINOFFICER".equalsIgnoreCase(getUser().getUserRole())*/){
					  questionBankList = adminManager.getAll(ViewQuestionBankDetails.class,"custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and classSectionId="+getClassSectionNameId()+" group by subjectId");
				  }else if ("ROLE_ADMIN_COORDINATOR".equalsIgnoreCase(getUser().getUserRole())) {
					  List<BigInteger> subjectIds = null;
					  String subjectIdsString ;
					  Staff staff = (Staff)adminManager.get(Staff.class,"accountId="+getUser().getId());
					  if(!ObjectFunctions.isNullOrEmpty(staff)){
						  List<StudyClass> studayClassList = getAdminCoordinatorStudyClassesList(staff.getId(),getUserAcademicYearId());
						  StringBuffer classIds = new StringBuffer();
						  for(StudyClass obj:studayClassList){
							  classIds.append(obj.getId()).append(",");
						  }
						  classIds.append("0");
						  subjectIds = adminManager.getAll("select subjectId from vw_classSubjectsSettings where custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+
								  " and classSectionId in ("+classIds.append(")"));
						  if (ObjectFunctions.isNotNullOrEmpty(subjectIds)) {
							  subjectIdsString = StringFunctions.convertListToCommaDelimitedString(subjectIds);
							  if(!ObjectFunctions.isNullOrEmpty(subjectIdsString))
								  questionBankList = adminManager.getAll(ViewQuestionBankDetails.class,"custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and classSectionId="+getClassSectionNameId()+" and subjectId in ("+subjectIdsString.toString()+") group by subjectId");
						  }
					  }
				  }else if(getUser().isSchoolStudent()){
					  questionBankList = adminManager.getAll(ViewQuestionBankDetails.class,"custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and classSectionId="+getClassSectionNameId()+" group by subjectId");
				  }else{
					  List<BigInteger> subjectIds = null;
					  String subjectIdsString=null;
					  Object[] StudyClassIds = adminManager.get("select studyClassId,studySubjectId from vw_staffSubjectsDetails where custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and accountId="+getUser().getId()+" and classTeacher='Y' and studyClassId="+getClassSectionNameId()+" group by studyClassId");
					  if(!ObjectFunctions.isNullOrEmpty(StudyClassIds)){
						  if(!ObjectFunctions.isNullOrEmpty(StudyClassIds) && !ObjectFunctions.isNullOrEmpty(StudyClassIds[0].toString()) && !ObjectFunctions.isNullOrEmpty(StudyClassIds[1].toString())){
							  subjectIds = adminManager.getAll("select subjectId from vw_classSubjectsSettings where custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and classSectionId="+StudyClassIds[0].toString());
							  if (ObjectFunctions.isNotNullOrEmpty(subjectIds)) {
								  subjectIdsString = StringFunctions.convertListToCommaDelimitedString(subjectIds);
								  if(!ObjectFunctions.isNullOrEmpty(subjectIdsString))
									  questionBankList = adminManager.getAll(ViewQuestionBankDetails.class,"custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and classSectionId="+getClassSectionNameId()+" and subjectId in ("+subjectIdsString.toString()+") group by subjectId");
							  }
						  }
						  StudyClassIds = null;
					  }else{
						  subjectIds = adminManager.getAll("select studySubjectId from vw_staffSubjectsDetails where custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and accountId="+getUser().getId()+" and studyClassId="+getClassSectionNameId()+" and classTeacher='N' group by studySubjectId");
						  if (ObjectFunctions.isNotNullOrEmpty(subjectIds)) {
							  subjectIdsString = StringFunctions.convertListToCommaDelimitedString(subjectIds);
							  if(!ObjectFunctions.isNullOrEmpty(subjectIdsString))
								  questionBankList = adminManager.getAll(ViewQuestionBankDetails.class,"custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and classSectionId="+getClassSectionNameId()+" and subjectId in ("+subjectIdsString.toString()+") group by subjectId");
						  }
					  }
				  }
				  setQuestionBankDetails(questionBankList);
				  return "subjectView";
			  }
		  }
		  catch (Exception ex) {
			  ex.printStackTrace();
		  }
		  return SUCCESS;
	  }
	  @Actions({@Action(value = "ajaxGetAllLessonsBySubjects",results ={@Result(location = "questionBank/ajaxGetLessonList.jsp",name = "success") })})
	  public String ajaxGetAllLessonsBySubjects()  throws URTUniversalException {
		  if(log.isDebugEnabled()){
			  log.debug("Entering 'ajaxGetAllLessonsBySubjects' method");
		  }
		  try{	
			  if(!StringFunctions.isNullOrEmpty(getStudyClassSubjectId())){
				  if("ROLE_ADMIN".equalsIgnoreCase(getUser().getUserRole()) || "ROLE_ADMINOFFICER".equalsIgnoreCase(getUser().getUserRole()) /*|| "ROLE_PRINCIPAL".equalsIgnoreCase(getUser().getUserRole()) || "ROLE_VICEPRINCIPAL".equalsIgnoreCase(getUser().getUserRole())*/){
					  setSyllabusPlannerList(adminManager.getAll(StaffSyllabusPlanner.class, "custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and studySubjectId="+getStudyClassSubjectId()+" and studyClassId="+getClassSectionNameId()));
				  }
				  else{
					  Staff staff = adminManager.getStaffByAcountId(getUser().getId(),Constants.YES_STRING);
					  setSyllabusPlannerList(adminManager.getAll(StaffSyllabusPlanner.class, "custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and studySubjectId="+getStudyClassSubjectId()+" and staffId="+staff.getId()+" and studyClassId="+getClassSectionNameId()));
				  }
				  setQuestionPaperBankVO(adminManager.getQuestionBankByLesson(getUserCustId(),getUserAcademicYearId(),getClassSectionNameId(),getStudyClassSubjectId(),getLessonId(),getQuestionBankId()));
			  }
				
		  } catch (Exception ex) {
			  ex.printStackTrace();
		  }
		  return SUCCESS;
	  }
	  
	  @Actions({@Action(value = "ajaxAddQuestionBankMaterials",results ={@Result(location = "questionBank/ajaxViewQuestionBankMaterials.jsp",name = "success") })})
	  public String ajaxAddQuestionBankMaterials()  throws URTUniversalException {
		  if(log.isDebugEnabled()){
			  log.debug("Entering 'ajaxAddQuestionBankMaterials' method");
		  }
		  try{
			  int returnCode = 0;
			  AcademicYear academicYear = (AcademicYear) adminManager.get(AcademicYear.class, getUserAcademicYearId());
			  if(!ObjectFunctions.isNullOrEmpty(getQuestionPaperBankVO())){
				  returnCode = adminManager.addOrUpdateQuestionPaperBank(getQuestionPaperBankVO(),getUser(),academicYear);
			  }
			  if(returnCode == 0)
				  super.addActionError("Question Bank not added. Please contact administrator");
			  else if(returnCode == 1)
				  super.addActionMessage("Question Bank updated successfully");
			  else if(returnCode == 2)
				  super.addActionMessage("Question Bank created successfully");
		  } catch (Exception ex) {
			  ex.printStackTrace();
		  }
		  ajaxDoAddQuestionBankMaterial() ;
		  return SUCCESS;
	  }
	  @Actions( {@Action(value = "ajaxViewUploadQuestionBankDetailsInfo", results = { @Result(location = "questionBank/ajaxViewQuestionBankDetails.jsp", name = "success") }) })
	  public String ajaxViewUploadStudyMaterialsInfo() throws URTUniversalException {
		  if (log.isDebugEnabled()) {
			  log.debug("Entering 'ajaxViewUploadQuestionBankDetailsInfo' method");
		  }
		  try {
			  setQuestionBankListDetails(adminManager.getQuestionBankList(getUserCustId(), getUserAcademicYearId() ,getClassSectionId() ,getSubjectId()));
		  } catch (Exception ex) {
			  ex.printStackTrace();
		  }
		  return SUCCESS;
	  }
	  @Actions( { @Action(value = "ajaxDownloadQuestionBankDocs", results = {}) })
	  public String ajaxDownloadQuestionBankDocs() {
		  if (log.isDebugEnabled()) {
			  log.debug("Entering 'ajaxDownloadQuestionBankDocs' method");
		  }
		  try {
			if(!StringFunctions.isNullOrEmpty(getClassSectionId()) && !StringFunctions.isNullOrEmpty(getSubjectId())){
				String materialId = getParamValue("materialId");
				Attachment questionBankAttachment = (Attachment) adminManager.get(Attachment.class,"id="+materialId);
				if(!ObjectFunctions.isNullOrEmpty(questionBankAttachment)){
					if(StringFunctions.isNotNullOrEmpty(questionBankAttachment.getFileName()) && StringFunctions.isNotNullOrEmpty(questionBankAttachment.getFilePath())){
						URL url = new URL(questionBankAttachment.getFilePath());
						URLConnection conn = url.openConnection();
						String type = conn.getContentType(); 
						getResponse().setContentType(type);
						getResponse().addHeader("Content-Disposition", "attachment; filename="+questionBankAttachment.getFileName().replace(" ", ""));
						ServletOutputStream out = getResponse().getOutputStream();
						InputStream is = conn.getInputStream();
						File tempFile = File.createTempFile(""+questionBankAttachment.getFileName(),".doc");
						tempFile.deleteOnExit();
						FileUtils.copyInputStreamToFile(is, tempFile);
						DataInputStream in = new DataInputStream(new FileInputStream(tempFile));
						byte[] bbuf = new byte[1024];
						int length = 0;
						while ((in != null) && ((length = in.read(bbuf)) != -1)) {
							out.write(bbuf, 0, length);
						}
						tempFile.delete();
						in.close();
						out.flush();
						out.close();
					}
				}
				questionBankAttachment = null;
			}
		  } catch (Exception ex) {
			  ex.printStackTrace();
		  }
		  return null;
	  }
	  @Actions( { @Action(value="ajaxRemoveQuestionBank", results = { @Result( location= "questionBank/ajaxViewQuestionBankMaterials.jsp" )  } ) })
	  public String ajaxRemoveQuestionBank() throws URTUniversalException{
		  if (log.isDebugEnabled()) {
			  log.debug("Entering 'ajaxRemoveQuestionBank' method");
		  }
		  try {
			  if(getQuestionPaperBankVO().getId()>0){
				  int returnCode=adminManager.removeQuestionBank(getQuestionPaperBankVO().getId());
				  if(returnCode == 1){
					  super.addActionMessage("Question bank removed successfully.");
				  }
				  else if(returnCode==0)
					  super.addActionError("Question bank not added. Please contact administrator");
			  }
		  } catch (Exception ex) {
			  ex.printStackTrace();
		  }finally{
			  viewQuestionBankMaterialList();
		  }
		  return SUCCESS;
	  }
	  @Actions( { @Action(value = "ajaxDeleteQuestionBankAttachment", results = { @Result(location = "questionBank/ajaxDoAddQuestionBankMaterials.jsp", name = "success") }) })
	  public String ajaxDeleteQuestionBankAttachment() throws URTUniversalException {
		  if (log.isInfoEnabled()) {
			  log.info("Entering 'ajaxDeleteQuestionBankAttachment' method");
		  }
		try{
			if(getQuestionPaperBankVO().getId()>0 && getQuestionPaperBankVO().getAttachmentVOs().get(0).getId()>0){
				int returnCode=adminManager.removeQuestionBankAttachment(getQuestionPaperBankVO().getId(),getQuestionPaperBankVO().getAttachmentVOs().get(0).getId());
				if(returnCode == 1){
					super.addActionMessage("Attachment removed successfully.");
				}
				else if(returnCode==0){
					super.addActionError("Attachment not added. Please contact administrator");
				}
				ajaxDoAddQuestionBankMaterial();
			}
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		return SUCCESS;
	  }
}