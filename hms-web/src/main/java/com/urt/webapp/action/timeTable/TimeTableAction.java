package com.urt.webapp.action.timeTable;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.churchgroup.common.constants.Constants;
import com.churchgroup.util.date.DateFormatter;
import com.churchgroup.util.date.DateUtil;
import com.churchgroup.util.object.ConvertUtil;
import com.churchgroup.util.object.ObjectFunctions;
import com.churchgroup.util.string.StringFunctions;
import com.churchgroup.util.string.StringUtil;
import com.urt.exception.base.URTUniversalException;
import com.urt.json.JSONArray;
import com.urt.json.JSONObject;
import com.urt.persistence.model.common.Messages;
import com.urt.persistence.model.customer.Customer;
import com.urt.persistence.model.exam.WeekDays;
import com.urt.persistence.model.manuatimetable.TimeTablePeriod;
import com.urt.persistence.model.manuatimetable.TimeTableSettings;
import com.urt.persistence.model.study.ClassName;
import com.urt.persistence.model.study.Staff;
import com.urt.persistence.model.study.StaffSubstitutionTimeTable;
import com.urt.persistence.model.study.StaffSubstitutionTimeTableComparator;
import com.urt.persistence.model.study.StudyClass;
import com.urt.persistence.model.study.StudySubject;
import com.urt.persistence.model.study.SubstitutionTimeTable;
import com.urt.persistence.model.study.TimetablePeriods;
import com.urt.persistence.model.study.ViewStaffPersonAccountDetails;
import com.urt.util.common.RayGunException;
import com.urt.util.email.MailUtil;
import com.urt.util.excel.staff.PrepareStaffDetailsExcelForTimeTable;
import com.urt.webapp.action.base.BaseAction;
import com.urt.webapp.action.jrexception.JRExceptionClient;

@Namespace("/timeTable")

@Actions( {
@Action(value = "ajaxManageFETTimeTable", results = { @Result(location = "../admin/academic/timeTable/ajaxManageFETTimeTable.jsp", name = "success") })
})
public class TimeTableAction extends BaseAction{
	
	private TimeTablePeriod timeTablePeriod;
	private String changeInTimeTable;
	private HashMap<Long, String> tempHashMap1;
	private TreeMap<String, String> subjectPeriodsMap;
	private long timeTableSettingsId;
	private int attendanceSubmittedCount;
	
	
	public long getTimeTableSettingsId() {
		return timeTableSettingsId;
	}
	public void setTimeTableSettingsId(long timeTableSettingsId) {
		this.timeTableSettingsId = timeTableSettingsId;
	}
	public TimeTablePeriod getTimeTablePeriod() {
		return timeTablePeriod;
	}
	public void setTimeTablePeriod(TimeTablePeriod timeTablePeriod) {
		this.timeTablePeriod = timeTablePeriod;
	}
	public String getChangeInTimeTable() {
		return changeInTimeTable;
	}
	public void setChangeInTimeTable(String changeInTimeTable) {
		this.changeInTimeTable = changeInTimeTable;
	}
	public HashMap<Long, String> getTempHashMap1() {
		if(ObjectFunctions.isNullOrEmpty(this.tempHashMap1)){
			this.tempHashMap1 = new HashMap<Long, String>();
		}
		return tempHashMap1;
	}
	public void setTempHashMap1(HashMap<Long, String> tempHashMap1) {
		this.tempHashMap1 = tempHashMap1;
	}
	public TreeMap<String, String> getSubjectPeriodsMap() {
		if (ObjectFunctions.isNullOrEmpty(this.subjectPeriodsMap)) {
			this.subjectPeriodsMap = new TreeMap<String, String>();
		}
		return subjectPeriodsMap;
	}
	public void setSubjectPeriodsMap(TreeMap<String, String> subjectPeriodsMap) {
		this.subjectPeriodsMap = subjectPeriodsMap;
	}
	public int getAttendanceSubmittedCount() {
		return attendanceSubmittedCount;
	}
	public void setAttendanceSubmittedCount(int attendanceSubmittedCount) {
		this.attendanceSubmittedCount = attendanceSubmittedCount;
	}
	
	@Actions( { @Action(value = "ajaxDownLoadSheetForTimetable", results = {}) })
    public void ajaxDownLoadSheetForTimetable() throws URTUniversalException {
	if (log.isDebugEnabled()) {
	    log.debug("Entering 'ajaxDownLoadSheetForTimetable' method");
	}
	try {
        if (getUserAcademicYearId() != 0) {
            String[] words = { "Own", "Private", "School Tranport" };
            Arrays.asList(words);
            String fileName = "TimeTable_Details_" + DateUtil.getDateTime(DateFormatter.MMDDYYY_PATTERN, new Date());
            getResponse().setContentType("application/vnd.ms-excel");
            getResponse().setHeader("Content-Disposition","attachment; filename=" + fileName.replace(' ', '_') + ".xls");
            PrepareStaffDetailsExcelForTimeTable prepareStaffDetailsExcel = new PrepareStaffDetailsExcelForTimeTable();
            List<StudyClass> studyClasses = adminManager.GetAllStudyClasses( getUserCustId(), getUserAcademicYearId(),null);
          //  List<ViewClassSectionDetails> classSections = adminManager.getAll(ViewClassSectionDetails.class," academicYearId = " + getUserAcademicYearId() + " and custId = " + getUserCustId() + " order by sortingOrder asc");
           // List<State> states = adminManager.getAll(State.class);
            List<StudySubject> studySubjects = adminManager.getAll(StudySubject.class,  " academicYearId = " + getUserAcademicYearId() + " and custId = "+ getUserCustId()+ " order by sortingOrder asc");
            List<ViewStaffPersonAccountDetails> staffList = adminManager.getAll(ViewStaffPersonAccountDetails.class,  " academicYearId = " + getUserAcademicYearId() + " and custId = "+ getUserCustId()+ " and status='"+Constants.YES_STRING+"' and (roleName='"+Constants.SCHOOL_PRINCIPAL+"' || roleName='"+Constants.SCHOOL_HOD+"' ||roleName='"+Constants.SCHOOL_TEACHER+"'  ) ");
            List<WeekDays> weekDaysList = adminManager.getAll(WeekDays.class);
            //Removing Sunday from the list
            weekDaysList.remove(0);
            //prepareStaffDetailsExcel.createClassSectionSheet("ClassSections", classSections,states, getUserAcademicYearId(), getUserCustId());
           /* prepareStaffDetailsExcel.createClassSubjectsSheet("ClassSubjects", studySubjects, getUserAcademicYearId(), getUserCustId());
           */ if (ObjectFunctions.isNotNullOrEmpty(studyClasses)) {
            	StringBuffer sheetTitleDesc = new StringBuffer();
            	sheetTitleDesc.append("School Name : ");
            	if(!ObjectFunctions.isNullOrEmpty(getSession().getAttribute("organization"))){
            		sheetTitleDesc.append((String)getSession().getAttribute("organization"));
            	}
            	sheetTitleDesc.append(", Academic Year : ");
            	if(!ObjectFunctions.isNullOrEmpty(getSession().getAttribute("academicYearName"))){
            		sheetTitleDesc.append((String)getSession().getAttribute("academicYearName"));
            		
            	}
                for (StudyClass studyClass : studyClasses) {
                   // studentDetails = adminManager.getAll(PrepareStudentExcel.query + " where classSectionId="
                    //+ studyClass.getId() + " and description is null order by firstName,lastName");
                    if (!ObjectFunctions.isNullOrEmpty(studySubjects)) {
                    	//prepareStaffDetailsExcel.createTimeTableSheet(studyClass.getClassAndSection(), studySubjects, sheetTitleDesc.toString());
                    	prepareStaffDetailsExcel.createTimeTableSheet(studyClass.getClassName()+" "+studyClass.getSection(), studySubjects, sheetTitleDesc.toString());
                    }
                    studyClass=null;
                }
            }
            prepareStaffDetailsExcel.createClassSubjectsSheet("ClassSubjects", studySubjects, getUserAcademicYearId(), getUserCustId());
            prepareStaffDetailsExcel.createClassStaffSheet("ClassStaff", staffList, weekDaysList, getUserAcademicYearId(), getUserCustId());
            prepareStaffDetailsExcel.createClassDaysSheet("ClassDays", staffList, weekDaysList, getUserAcademicYearId(), getUserCustId());
            prepareStaffDetailsExcel.createClassHoursSheet("ClassHours", staffList, weekDaysList, getUserAcademicYearId(), getUserCustId());
          
            prepareStaffDetailsExcel.finalPrep("ClassSubjects");
            prepareStaffDetailsExcel.getWb().getCreationHelper().createFormulaEvaluator().evaluateAll();
            prepareStaffDetailsExcel.getWb().write(getResponse().getOutputStream());
        }
    } catch (Exception ex) {
	    ex.printStackTrace();
	}
    }
	
	//Sample XML
	@Actions({ @Action(value = "ajaxGenerateXMLForTimeTable", results = { @Result(location = "timeTable/ajaxViewManageTimeTable.jsp", name = "success") }) })
	public String ajaxGenerateXMLForTimeTable() throws URTUniversalException, IOException {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxGenerateXMLForTimeTable' method");
		}
		try {
			//Ref: http://www.mkyong.com/java/how-to-create-xml-file-in-java-dom/
			
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
	 
			Document doc = docBuilder.newDocument();
			
			//Adding fet
			Element rootElement = doc.createElement("fet");
			doc.appendChild(rootElement);
			
			//Adding Institution_Name
			Element institutionName = doc.createElement("Institution_Name");
			institutionName.appendChild(doc.createTextNode("URT"));
			rootElement.appendChild(institutionName);
			
			//Adding Comments
			Element comments = doc.createElement("Comments");
			comments.appendChild(doc.createTextNode("Default comments"));
			rootElement.appendChild(comments);
			
			//Adding Hours_List
			Element hoursList = doc.createElement("Hours_List");
			
			Element number = doc.createElement("Number");
			number.appendChild(doc.createTextNode("1"));
			hoursList.appendChild(number);
			
			Element name = doc.createElement("Name");
			name.appendChild(doc.createTextNode("08:00"));
			hoursList.appendChild(name);
			
			rootElement.appendChild(hoursList);
			
			//Adding Days_List
			Element daysList = doc.createElement("Days_List");
			
			Element daysNumber = doc.createElement("Number");
			daysNumber.appendChild(doc.createTextNode("1"));
			daysList.appendChild(daysNumber);
			
			Element dayName = doc.createElement("Name");
			dayName.appendChild(doc.createTextNode("Monday"));
			daysList.appendChild(dayName);
			
			rootElement.appendChild(daysList);
			
			//Adding Class names, in FET it is Students_List Tag
			Element classList = doc.createElement("Students_List");
			
			Element year = doc.createElement("Year");
			
			Element yearName = doc.createElement("Name");
			yearName.appendChild(doc.createTextNode("2013"));
			year.appendChild(yearName);
			
			Element totalNumberofClasses = doc.createElement("Number_of_Students");
			totalNumberofClasses.appendChild(doc.createTextNode("10"));
			year.appendChild(totalNumberofClasses);
			
			Element group = doc.createElement("Group");
			
			Element className = doc.createElement("Name");
			className.appendChild(doc.createTextNode("LKG"));
			group.appendChild(className);
			
			Element totalNumberofSubClasses = doc.createElement("Number_of_Students");
			totalNumberofSubClasses.appendChild(doc.createTextNode("10"));
			group.appendChild(totalNumberofSubClasses);
			
			Element subgroup = doc.createElement("Subgroup");
			
			Element sectionName = doc.createElement("Name");
			sectionName.appendChild(doc.createTextNode("LKG A"));
			subgroup.appendChild(sectionName);
			
			Element totalNumberofSuperSubClasses = doc.createElement("Number_of_Students");
			totalNumberofSuperSubClasses.appendChild(doc.createTextNode("0"));
			subgroup.appendChild(totalNumberofSuperSubClasses);
			
			
			group.appendChild(subgroup);
			
			year.appendChild(group);
			
			classList.appendChild(year);
			
			rootElement.appendChild(classList);
			
			//Ending Students_List
			
			//Adding Teachers_List
			Element teachersList = doc.createElement("Teachers_List");
			
			Element teacher = doc.createElement("Teacher");
			
			Element teacherName = doc.createElement("Name");
			teacherName.appendChild(doc.createTextNode("Siva"));
			teacher.appendChild(teacherName);
			
			
			teachersList.appendChild(teacher);
			
			rootElement.appendChild(teachersList);
			
			//Adding Subjects_List
			Element subjectsList = doc.createElement("Subjects_List");
			
			Element subject = doc.createElement("Subject");
			
			Element subjectName = doc.createElement("Name");
			subjectName.appendChild(doc.createTextNode("English"));
			subject.appendChild(subjectName);
			
			subjectsList.appendChild(subject);
			
			rootElement.appendChild(subjectsList);
			
			//Adding Activity_Tags_List
			Element activityTagsList = doc.createElement("Activity_Tags_List");
			rootElement.appendChild(activityTagsList);
			
			//Adding Activities_List
			Element activitiesList = doc.createElement("Activities_List");
			
			Element activity = doc.createElement("Activity");
			
			Element activityTeacher = doc.createElement("Teacher");
			activityTeacher.appendChild(doc.createTextNode("Siva"));
			activity.appendChild(activityTeacher);
			
			Element activitySubject = doc.createElement("Subject");
			activitySubject.appendChild(doc.createTextNode("English"));
			activity.appendChild(activitySubject);
			
			Element activityStudents = doc.createElement("Students");
			activityStudents.appendChild(doc.createTextNode("LKG A"));
			activity.appendChild(activityStudents);
			
			Element activityDuration = doc.createElement("Duration");
			activityDuration.appendChild(doc.createTextNode("1"));
			activity.appendChild(activityDuration);
			
			Element activityTotalDuration = doc.createElement("Total_Duration");
			activityTotalDuration.appendChild(doc.createTextNode("1"));
			activity.appendChild(activityTotalDuration);
			
			Element activityId = doc.createElement("Id");
			activityId.appendChild(doc.createTextNode("1"));
			activity.appendChild(activityId);
			
			Element activityGroupId = doc.createElement("Activity_Group_Id");
			activityGroupId.appendChild(doc.createTextNode("1"));
			activity.appendChild(activityGroupId);
			
			Element activityActive = doc.createElement("Active");
			activityActive.appendChild(doc.createTextNode("true"));
			activity.appendChild(activityActive);
			
			Element activityComments = doc.createElement("Comments");
			activityComments.appendChild(doc.createTextNode(""));
			activity.appendChild(activityComments);
			
			activitiesList.appendChild(activity);
			
			rootElement.appendChild(activitiesList);
			
			//Ending Activities_List
			
			//Adding Time_Constraints_List
			Element timeConstraintsList = doc.createElement("Time_Constraints_List");
			
			Element constraintBasicCompulsoryTime = doc.createElement("ConstraintBasicCompulsoryTime");
			
			Element compulsoryTimeWeightPercentage = doc.createElement("Weight_Percentage");
			compulsoryTimeWeightPercentage.appendChild(doc.createTextNode("100"));
			constraintBasicCompulsoryTime.appendChild(compulsoryTimeWeightPercentage);
			
			Element compulsoryTimeActive = doc.createElement("Active");
			compulsoryTimeActive.appendChild(doc.createTextNode("true"));
			constraintBasicCompulsoryTime.appendChild(compulsoryTimeActive);
			
			Element compulsoryTimeComments = doc.createElement("Comments");
			compulsoryTimeComments.appendChild(doc.createTextNode(""));
			constraintBasicCompulsoryTime.appendChild(compulsoryTimeComments);
			
			timeConstraintsList.appendChild(constraintBasicCompulsoryTime);
			
			rootElement.appendChild(timeConstraintsList);
			
			//Adding Space_Constraints_List
			Element spaceConstraintsList = doc.createElement("Space_Constraints_List");
			
			Element constraintBasicCompulsorySpace = doc.createElement("ConstraintBasicCompulsorySpace");
			
			Element compulsorySpaceWeightPercentage = doc.createElement("Weight_Percentage");
			compulsorySpaceWeightPercentage.appendChild(doc.createTextNode("100"));
			constraintBasicCompulsorySpace.appendChild(compulsorySpaceWeightPercentage);
			
			Element compulsorySpaceActive = doc.createElement("Active");
			compulsorySpaceActive.appendChild(doc.createTextNode("true"));
			constraintBasicCompulsorySpace.appendChild(compulsorySpaceActive);
			
			Element compulsorySpaceComments = doc.createElement("Comments");
			compulsorySpaceComments.appendChild(doc.createTextNode(""));
			constraintBasicCompulsorySpace.appendChild(compulsorySpaceComments);
			
			spaceConstraintsList.appendChild(constraintBasicCompulsorySpace);
			
			rootElement.appendChild(spaceConstraintsList);
			
			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File("D:\\timetable.fet"));
	 
			// Output to console for testing
			// StreamResult result = new StreamResult(System.out);
	 
			transformer.transform(source, result);
	 
			//getResponse().setHeader("Content-Disposition","sivafile.xml");
			//OutputStream output=getResponse().getOutputStream();
			
			log.debug("File saved!");
	 
		  } catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		  } catch (TransformerException tfe) {
			tfe.printStackTrace();
		  }
		return SUCCESS;
	}
	//Sample XML
	
	@Actions( { @Action(value = "ajaxUploadTimetableData", results = { @Result(location = "../admin/academic/timeTable/ajaxViewManageTimeTable.jsp", name = "success"), @Result(location = "../admin/academic/timeTable/ajaxViewManageTimeTable.jsp", name = "dummyInit")}) })
    public String ajaxUploadTimetableData() {
   	if (log.isDebugEnabled()) {
   	    log.debug("Entering 'ajaxUploadTimetableData' method");
   	}
   	try {
	   	 boolean excelFileType = false;
		 excelFileType = validateExcelFileType(getUploadContentType());
		 if(excelFileType){
			log.debug("No file to upload....");
			super.addActionError("File type not matched.");
			  return "dummyInit";
		 }
   		/*if (!(Constants.FILE_TYPE_XLS.equalsIgnoreCase(getUploadContentType()) || Constants.FILE_TYPE_APPLICATION_XLS.equalsIgnoreCase(getUploadContentType()))) {
   				log.debug("No file to upload....");
   				super.addActionError("File type not matched.");
   				//ajaxViewManageTimeTable();
   				return "dummyInit";
   		}*/
 		  if (getUserAcademicYearId() > 0) {
	   		  WorkbookSettings ws = new WorkbookSettings();
	   	      ws.setLocale(new Locale("en", "EN"));
	   	      Workbook workbook = Workbook.getWorkbook(getUpload(),ws);
	   	      Sheet sheet=null;
	   	      Cell cell;
	   	      String[] anyIds;
	   	      
	   	    DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
	 
			Document doc = docBuilder.newDocument();
			
			//Adding fet
			Element rootElement = doc.createElement("fet");
			doc.appendChild(rootElement);
			
			//Adding Institution_Name
			Element institutionName = doc.createElement("Institution_Name");
			//institutionName.appendChild(doc.createTextNode("URT"));
			Customer customer = (Customer)adminManager.get(Customer.class,getUserCustId()); 
			//institutionName.appendChild(doc.createTextNode("URT"));
			if(customer.getOrganization() != null && customer.getOrganization() != "")
			{
				institutionName.appendChild(doc.createTextNode(customer.getOrganization()));
			}
			else{
				institutionName.appendChild(doc.createTextNode("URT"));
			}
			rootElement.appendChild(institutionName);
			
			//Adding Comments
			Element comments = doc.createElement("Comments");
			//comments.appendChild(doc.createTextNode("Default comments"));
			if(customer.getOrganization() != null && customer.getOrganization() != "")
			{
				comments.appendChild(doc.createTextNode("Time Table for "+customer.getOrganization()));
			}
			else{
				comments.appendChild(doc.createTextNode("Time Table"));
			}
			rootElement.appendChild(comments);
			
			//Getting Hours Sheet
			sheet = workbook.getSheet(workbook.getNumberOfSheets()-2);
   	    	if("ClassHours".equalsIgnoreCase(sheet.getName()))
   	    	{
   	    		//Adding Hours_List   	    	
   	    		Element hoursList = doc.createElement("Hours_List");
   	    		int rowSize = sheet.getRows();
   	    		//int columnSize = sheet.getColumns();
   	    		
   	    		Element number = doc.createElement("Number");
   				//number.appendChild(doc.createTextNode("1"));
   	    		number.appendChild(doc.createTextNode(String.valueOf(rowSize-4)));
   				hoursList.appendChild(number);
   				
   	    		for(int i=4;i < rowSize; i++)
	   	  	  	{
   	   				cell=sheet.getCell(1,i);
	   	    		String hourName=cell.getContents();
	   				
	   				Element name = doc.createElement("Name");
	   				//name.appendChild(doc.createTextNode("08:00"));
	   				name.appendChild(doc.createTextNode(hourName));
	   				hoursList.appendChild(name);
	   	  	  	}
   	    		rootElement.appendChild(hoursList);
   	    	}
   	    	//Getting Days Sheet
			sheet = workbook.getSheet(workbook.getNumberOfSheets()-3);
    		if("ClassDays".equalsIgnoreCase(sheet.getName()))
   	    	{
    			//Adding Days_List
   				Element daysList = doc.createElement("Days_List");
   				int rowSize = sheet.getRows();
   	    	//	int columnSize = sheet.getColumns();
   	    		
   				Element daysNumber = doc.createElement("Number");
				//daysNumber.appendChild(doc.createTextNode("6"));
   				daysNumber.appendChild(doc.createTextNode(String.valueOf(rowSize-4)));
   				daysList.appendChild(daysNumber);
   				
   				for(int i=4;i < rowSize; i++)
	   	  	  	{
   					cell=sheet.getCell(1,i);
	   	    		String sdayName=cell.getContents();
	   				
	   				Element dayName = doc.createElement("Name");
	   				//dayName.appendChild(doc.createTextNode("Monday"));
	   				dayName.appendChild(doc.createTextNode(sdayName));
	   				daysList.appendChild(dayName);
	   	  	  	}
   				
   				rootElement.appendChild(daysList);
   	    		
   	    	}
			
			
			//Adding Class names, in FET it is Students_List Tag
			Element classList = doc.createElement("Students_List");
			
			Element year = doc.createElement("Year");
			Element yearName = doc.createElement("Name");
			yearName.appendChild(doc.createTextNode("2013"));
			year.appendChild(yearName);
			
			List<ClassName> allClasses = adminManager.getAllClassNames( getUserCustId(),getUserAcademicYearId());
			
			Element totalNumberofClasses = doc.createElement("Number_of_Students");
			//totalNumberofClasses.appendChild(doc.createTextNode("10"));
			totalNumberofClasses.appendChild(doc.createTextNode(String.valueOf(allClasses.size())));
			year.appendChild(totalNumberofClasses);
			if(!ObjectFunctions.isNullOrEmpty(allClasses))
			{
				for (ClassName className : allClasses) 
				{
					Element group = doc.createElement("Group");
					
					Element grpClassName = doc.createElement("Name");
					//grpClassName.appendChild(doc.createTextNode("LKG"));
					grpClassName.appendChild(doc.createTextNode(className.getClassName()));
					group.appendChild(grpClassName);
					
					Element totalNumberofSubClasses = doc.createElement("Number_of_Students");
					//totalNumberofSubClasses.appendChild(doc.createTextNode("10"));
					// JSR TO-DO: Uncomment the following line once method id fixed.
					List<StudyClass> sections = new ArrayList<StudyClass>();
					//= adminManager.getStudyClassesByClassNameClassId(className.getId(), getUserCustId(), getUserAcademicYearId());
					//totalNumberofSubClasses.appendChild(doc.createTextNode(String.valueOf(className.getClassSection().size())));
					totalNumberofSubClasses.appendChild(doc.createTextNode(String.valueOf(sections.size())));
					group.appendChild(totalNumberofSubClasses);
					
					if(!ObjectFunctions.isNullOrEmpty(sections))
					{
						for (StudyClass studyClass : sections) 
						{
							Element subgroup = doc.createElement("Subgroup");
							
							Element sectionName = doc.createElement("Name");
							//sectionName.appendChild(doc.createTextNode("LKG A"));
							sectionName.appendChild(doc.createTextNode(studyClass.getClassName()+" "+studyClass.getSection()));
							subgroup.appendChild(sectionName);
							
							Element totalNumberofSuperSubClasses = doc.createElement("Number_of_Students");
							totalNumberofSuperSubClasses.appendChild(doc.createTextNode("0"));
							subgroup.appendChild(totalNumberofSuperSubClasses);
							
							group.appendChild(subgroup);
						}
					}
					
					year.appendChild(group);
				}
 		  }
			
			classList.appendChild(year);
			
			rootElement.appendChild(classList);
			
			//Ending Students_List
			
			//Adding Teachers_List
			Element teachersList = doc.createElement("Teachers_List");
			
			sheet = workbook.getSheet(workbook.getNumberOfSheets()-4);
   	    	if("ClassStaff".equalsIgnoreCase(sheet.getName()))
   	    	{
   	    		int rowSize = sheet.getRows();
   	    		//int columnSize = sheet.getColumns();
   	    		for(int i=4;i < rowSize; i++)
	   	  	  	{
   	    			cell=sheet.getCell(1,i);
	   	    		String staffName=cell.getContents();
	   	    		
					Element teacher = doc.createElement("Teacher");
					
					Element teacherName = doc.createElement("Name");
					//teacherName.appendChild(doc.createTextNode("Siva"));
					teacherName.appendChild(doc.createTextNode(staffName));
					teacher.appendChild(teacherName);
					
					teachersList.appendChild(teacher);
	   	  	  	}
   	    	}
	   	    	
			rootElement.appendChild(teachersList);
			
			//Adding Subjects_List
			Element subjectsList = doc.createElement("Subjects_List");
			
			sheet = workbook.getSheet(workbook.getNumberOfSheets()-1);
   	    	if("ClassSubjects".equalsIgnoreCase(sheet.getName()))
   	    	{
   	    		int rowSize = sheet.getRows();
   	    		//int columnSize = sheet.getColumns();
   	    		for(int i=4;i < rowSize; i++)
	   	  	  	{
   	    			cell=sheet.getCell(1,i);
	   	    		String studySubjectName=cell.getContents();
	   	    		
	   	    		Element subject = doc.createElement("Subject");
					
	   	    		Element subjectName = doc.createElement("Name");
	   				//subjectName.appendChild(doc.createTextNode("English"));
	   	    		subjectName.appendChild(doc.createTextNode(studySubjectName));
	   				subject.appendChild(subjectName);
	   				
	   				subjectsList.appendChild(subject);
	   	  	  	}
   	    	}
	   	    	
			rootElement.appendChild(subjectsList);
			
			//Adding Time_Constraints_List
			Element timeConstraintsList = doc.createElement("Time_Constraints_List");
			
			//Adding Activity_Tags_List
			Element activityTagsList = doc.createElement("Activity_Tags_List");
			rootElement.appendChild(activityTagsList);
			
			//Adding Activities_List
			Element activitiesList = doc.createElement("Activities_List");  
			int activityCountId=1;
			int randomActivityGroupId=1;
			for(int sheetNum=0; sheetNum < workbook.getNumberOfSheets()-4; sheetNum++)
			{
	   	    	sheet = workbook.getSheet(sheetNum);
	   	    	if(!"ClassStaff".equalsIgnoreCase(sheet.getName()) && !"ClassSubjects".equalsIgnoreCase(sheet.getName()) && !"ClassDays".equalsIgnoreCase(sheet.getName()) && !"ClassHours".equalsIgnoreCase(sheet.getName()))
	   	    	{
	   	    		int rowSize = sheet.getRows();
	   	    	//	int columnSize = sheet.getColumns();
	   	    		for(int i=2;i < rowSize; i++)
		   	  	  	{
	   	    			
	   	    			//Adding constraintMinDaysBetweenActivities
	   	    			Element constraintMinDaysBetweenActivities = doc.createElement("ConstraintMinDaysBetweenActivities");
	   	    			//Ending constraintMinDaysBetweenActivities
	   	    			int noofActivitiesinaDay = 0;
	   	    			int duration1,duration2,duration3,duration4,duration5,duration6=0;
	   	    			int mindayActivies=0;
	   	    			
	   	    			
		   				
	   	    			String subjectName = "";
	   	    			cell=sheet.getCell(0,i);
	   	    			if(cell.getContents() != null && cell.getContents() != "")
	   	    			{
	   	    				subjectName=cell.getContents();
		   	  	  		}
	   	    			
	   	    			String staffName = "";
	   	    			cell=sheet.getCell(1,i);
	   	    			if(cell.getContents() != null && cell.getContents() != "")
	   	    			{
	   	    				staffName=cell.getContents();
	   	    			}
		   	    		
		   	    		cell=sheet.getCell(2,i);
		   	    		if(cell.getContents() != null && cell.getContents() != "")
		   	    		{
			   	    		duration1=Integer.valueOf(cell.getContents());
			   	    		if(duration1 >0)
			   	    		{
			   	    			noofActivitiesinaDay++;
			   	    		}
			   	    		mindayActivies++;
		   	  	  		}
		   	    		
		   	    		cell=sheet.getCell(3,i);
		   	    		if(cell.getContents() != null && cell.getContents() != "")
		   	    		{
			   	    		duration2=Integer.valueOf(cell.getContents());
			   	    		if(duration2 >0)
			   	    		{
			   	    			noofActivitiesinaDay++;
			   	    		}
			   	    		mindayActivies++;
		   	    		}
		   	    		
		   	    		cell=sheet.getCell(4,i);
		   	    		if(cell.getContents() != null && cell.getContents() != "")
		   	    		{
			   	    		duration3=Integer.valueOf(cell.getContents());
			   	    		if(duration3 >0)
			   	    		{
			   	    			noofActivitiesinaDay++;
			   	    		}
			   	    		mindayActivies++;
		   	    		}
		   	    		
		   	    		cell=sheet.getCell(5,i);
		   	    		if(cell.getContents() != null && cell.getContents() != "")
		   	    		{
			   	    		duration4=Integer.valueOf(cell.getContents());
			   	    		if(duration4 >0)
			   	    		{
			   	    			noofActivitiesinaDay++;
			   	    		}
			   	    		mindayActivies++;
		   	    		}
		   	    		
		   	    		cell=sheet.getCell(6,i);
		   	    		if(cell.getContents() != null && cell.getContents() != "")
		   	    		{
			   	    		duration5=Integer.valueOf(cell.getContents());
			   	    		if(duration5 >0)
			   	    		{
			   	    			noofActivitiesinaDay++;
			   	    		}
			   	    		mindayActivies++;
		   	    		}
		   	    		
		   	    		cell=sheet.getCell(7,i);
		   	    		if(cell.getContents() != null && cell.getContents() != "")
		   	    		{
			   	    		duration6=Integer.valueOf(cell.getContents());
			   	    		if(duration6 >0)
			   	    		{
			   	    			noofActivitiesinaDay++;
			   	    		}
			   	    		mindayActivies++;
		   	    		}
		   	    		
		   	    		String totalDuration = "";
		   	    		cell=sheet.getCell(8,i);
		   	    		if(cell.getContents() != null && cell.getContents() != "")
		   	    		{
		   	    			totalDuration=cell.getContents();
		   	    		}
		   	    		
		   	    		int durationPosition =2;
		   	    		
		   	    		if(noofActivitiesinaDay > 0)
		   	    		{
		   	    			//Adding MinDaysBetweenActivities
		   	    			
		   	    			if(mindayActivies > 1)
			   	    		{
			   	    			Element constraintMinDaysBetweenActivitiesWeightPercentage = doc.createElement("Weight_Percentage");
								constraintMinDaysBetweenActivitiesWeightPercentage.appendChild(doc.createTextNode("95"));
								constraintMinDaysBetweenActivities.appendChild(constraintMinDaysBetweenActivitiesWeightPercentage);
				   	    		
								Element constraintMinDaysBetweenActivitiesConsecutiveIfSameDay = doc.createElement("Consecutive_If_Same_Day");
								constraintMinDaysBetweenActivitiesConsecutiveIfSameDay.appendChild(doc.createTextNode("true"));
								constraintMinDaysBetweenActivities.appendChild(constraintMinDaysBetweenActivitiesConsecutiveIfSameDay);
								
								Element numberofActivities = doc.createElement("Number_of_Activities");
								numberofActivities.appendChild(doc.createTextNode(String.valueOf(noofActivitiesinaDay)));
								constraintMinDaysBetweenActivities.appendChild(numberofActivities);
			   	    		}
		   	    			//Ending MinDaysBetweenActivities
		   	    			
		   	    			for(int k=1; k<=noofActivitiesinaDay; k++)
			   	    		{
			   	    			Element activity = doc.createElement("Activity");
			   	    			cell=sheet.getCell(durationPosition,i);
				   	    		//duration1=Integer.valueOf(cell.getContents());
				   	    		
			   	    			if(subjectName != null && subjectName != "" && staffName != null && staffName !="" && cell.getContents() != "")
				   	    		{
					   				
				   	    			Element activityTeacher = doc.createElement("Teacher");
					   				//activityTeacher.appendChild(doc.createTextNode("Siva"));
					   				activityTeacher.appendChild(doc.createTextNode(staffName));
					   				activity.appendChild(activityTeacher);
					   				
					   				
					   	    		
					   				Element activitySubject = doc.createElement("Subject");
					   				//activitySubject.appendChild(doc.createTextNode("English"));
					   				activitySubject.appendChild(doc.createTextNode(subjectName));
					   				activity.appendChild(activitySubject);
					   				
					   				Element activityStudents = doc.createElement("Students");
					   				//activityStudents.appendChild(doc.createTextNode("LKG A"));
					   				activityStudents.appendChild(doc.createTextNode(sheet.getName()));
					   				activity.appendChild(activityStudents);
					   				
					   				Element activityDuration = doc.createElement("Duration");
					   				//activityDuration.appendChild(doc.createTextNode("1"));
					   				activityDuration.appendChild(doc.createTextNode(cell.getContents()));
					   				activity.appendChild(activityDuration);
					   				
					   				Element activityTotalDuration = doc.createElement("Total_Duration");
					   				//activityTotalDuration.appendChild(doc.createTextNode("1"));
					   				activityTotalDuration.appendChild(doc.createTextNode(totalDuration));
					   				activity.appendChild(activityTotalDuration);
					   				
					   				Element activityId = doc.createElement("Id");
					   				//activityId.appendChild(doc.createTextNode("1"));
					   				activityId.appendChild(doc.createTextNode(String.valueOf(activityCountId)));
					   				activity.appendChild(activityId);
					   				
					   				Element activityGroupId = doc.createElement("Activity_Group_Id");
					   				//activityGroupId.appendChild(doc.createTextNode("1")); 
					   				activityGroupId.appendChild(doc.createTextNode(String.valueOf(randomActivityGroupId)));
					   				activity.appendChild(activityGroupId);
					   				
					   				Element activityActive = doc.createElement("Active");
					   				activityActive.appendChild(doc.createTextNode("true"));
					   				activity.appendChild(activityActive);
					   				
					   				Element activityComments = doc.createElement("Comments");
					   				activityComments.appendChild(doc.createTextNode(""));
					   				activity.appendChild(activityComments);
					   				
					   				activitiesList.appendChild(activity);
					   				//Adding ConstraintMinDaysBetweenActivities
					   	    		if(mindayActivies > 1)
					   	    		{
					   	    			Element minActivityId = doc.createElement("Activity_Id");
					   	    			minActivityId.appendChild(doc.createTextNode(String.valueOf(activityCountId)));
										constraintMinDaysBetweenActivities.appendChild(minActivityId);
					   	    		}
					   	    		//Ending ConstraintMinDaysBetweenActivities
					   				
					   				activityCountId++;
					   			}
			   	    			else{
			   	    				k=k-1;
			   	    			}
			   	    			durationPosition++;
			   	    		}
		   	    			
		   	    			//Adding ConstraintMinDaysBetweenActivities
		   	    			if(mindayActivies > 1)
			   	    		{
			   	    			Element minDays = doc.createElement("MinDays");
			   	    			minDays.appendChild(doc.createTextNode("1"));
								constraintMinDaysBetweenActivities.appendChild(minDays);
				   	    		
								Element constraintMinDaysBetweenActivitiesActive = doc.createElement("Active");
								constraintMinDaysBetweenActivitiesActive.appendChild(doc.createTextNode("true"));
								constraintMinDaysBetweenActivities.appendChild(constraintMinDaysBetweenActivitiesActive);
								
								Element constraintMinDaysBetweenActivitiesActiveComments = doc.createElement("Comments");
								constraintMinDaysBetweenActivitiesActiveComments.appendChild(doc.createTextNode(""));
								constraintMinDaysBetweenActivities.appendChild(constraintMinDaysBetweenActivitiesActiveComments);
			   	    		}
			   	    		//Ending ConstraintMinDaysBetweenActivities
		   	  	  		}
		   	    		
		   	    		randomActivityGroupId=randomActivityGroupId+noofActivitiesinaDay;
		   	    		//Adding ConstraintMinDaysBetweenActivities
		   	    		if(mindayActivies > 1)
		   	    		{
		   	    			timeConstraintsList.appendChild(constraintMinDaysBetweenActivities);
		   	    		}
		   	    		//Ending ConstraintMinDaysBetweenActivities
		   	  	  	}
	   	    	}
	   	    	
			}
			
			rootElement.appendChild(activitiesList);
			
			//Ending Activities_List
			
			//Adding Time_Constraints_List
			//Element timeConstraintsList = doc.createElement("Time_Constraints_List");
			
			Element constraintBasicCompulsoryTime = doc.createElement("ConstraintBasicCompulsoryTime");
			
			Element compulsoryTimeWeightPercentage = doc.createElement("Weight_Percentage");
			compulsoryTimeWeightPercentage.appendChild(doc.createTextNode("100"));
			constraintBasicCompulsoryTime.appendChild(compulsoryTimeWeightPercentage);
			
			Element compulsoryTimeActive = doc.createElement("Active");
			compulsoryTimeActive.appendChild(doc.createTextNode("true"));
			constraintBasicCompulsoryTime.appendChild(compulsoryTimeActive);
			
			Element compulsoryTimeComments = doc.createElement("Comments");
			compulsoryTimeComments.appendChild(doc.createTextNode(""));
			constraintBasicCompulsoryTime.appendChild(compulsoryTimeComments);
			
			timeConstraintsList.appendChild(constraintBasicCompulsoryTime);
			
			//Adding break time as lunch break time
			//Getting Days Sheet
			sheet = workbook.getSheet(workbook.getNumberOfSheets()-3);
    		if("ClassDays".equalsIgnoreCase(sheet.getName()))
   	    	{
    			int rowSize = sheet.getRows();
    			
    			Element constraintBreakTimes = doc.createElement("ConstraintBreakTimes");
    			
    			Element constraintBreakTimesWeightPercentage = doc.createElement("Weight_Percentage");
    			constraintBreakTimesWeightPercentage.appendChild(doc.createTextNode("100"));
    			constraintBreakTimes.appendChild(constraintBreakTimesWeightPercentage);
    			
    			Element numberofBreakTimes = doc.createElement("Number_of_Break_Times");
    			//numberofBreakTimes.appendChild(doc.createTextNode("6"));
    			numberofBreakTimes.appendChild(doc.createTextNode(String.valueOf(rowSize-4)));
    			
    			constraintBreakTimes.appendChild(numberofBreakTimes);
    			
    			//Adding Days_List
   				
   	    		
    			for(int i=4;i < rowSize; i++)
	   	  	  	{
   					cell=sheet.getCell(1,i);
	   	    		String sdayName=cell.getContents();
	   				
	   				Element breakTime = doc.createElement("Break_Time");
	   				
	   				Element day = doc.createElement("Day");
	   				day.appendChild(doc.createTextNode(sdayName));
	   				breakTime.appendChild(day);
	   				
	   				Element hour = doc.createElement("Hour");
	   				hour.appendChild(doc.createTextNode("LunchBreak"));
	   				breakTime.appendChild(hour);
	   				
	   				constraintBreakTimes.appendChild(breakTime);
	   				
	   	  	  	}
    			
    			Element constraintBreakTimesActive = doc.createElement("Active");
    			constraintBreakTimesActive.appendChild(doc.createTextNode("true"));
    			constraintBreakTimes.appendChild(constraintBreakTimesActive);
    			
    			Element constraintBreakTimesComments = doc.createElement("Comments");
    			constraintBreakTimesComments.appendChild(doc.createTextNode(""));
    			constraintBreakTimes.appendChild(constraintBreakTimesComments);
   				
    			timeConstraintsList.appendChild(constraintBreakTimes);
   	    	}
    		
    		rootElement.appendChild(timeConstraintsList);
    		
    		//Adding Space_Constraints_List
			Element spaceConstraintsList = doc.createElement("Space_Constraints_List");
			
			Element constraintBasicCompulsorySpace = doc.createElement("ConstraintBasicCompulsorySpace");
			
			Element compulsorySpaceWeightPercentage = doc.createElement("Weight_Percentage");
			compulsorySpaceWeightPercentage.appendChild(doc.createTextNode("100"));
			constraintBasicCompulsorySpace.appendChild(compulsorySpaceWeightPercentage);
			
			Element compulsorySpaceActive = doc.createElement("Active");
			compulsorySpaceActive.appendChild(doc.createTextNode("true"));
			constraintBasicCompulsorySpace.appendChild(compulsorySpaceActive);
			
			Element compulsorySpaceComments = doc.createElement("Comments");
			compulsorySpaceComments.appendChild(doc.createTextNode(""));
			constraintBasicCompulsorySpace.appendChild(compulsorySpaceComments);
			
			spaceConstraintsList.appendChild(constraintBasicCompulsorySpace);
			
			rootElement.appendChild(spaceConstraintsList);
			
			
			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			
			String path = getSession().getServletContext().getRealPath("/userfiles/"+customer.getCustomerShortName()+"timeTable.fet");
			
			//StreamResult result = new StreamResult(new File("D:\\timetable.fet"));
			StreamResult result = new StreamResult(new File(path));
	 
			// Output to console for testing
			// StreamResult result = new StreamResult(System.out);
	 
			transformer.transform(source, result);
			
			getResponse().setContentType("application/fet");
			getResponse().setHeader("Content-Disposition", "attachment;filename="+customer.getCustomerShortName()+"timeTable.fet");
			
			InputStream is = new FileInputStream(new File(path));
		 
			OutputStream os = getResponse().getOutputStream();  
			byte[] b = new byte[1024];  
			int len = 0;  
			try {  
			  while ((len = is.read(b)) != -1) {  
			    os.write(b, 0, len);  
			  }  
			  is.close();  
			  os.close();  
			} finally {  
			  is.close();  
			}  
			log.debug("File saved!");
			super.addActionMessage("Successfully uploaded Timetable.");
  		  }
   	    
   	}catch (Exception e) {
   		e.printStackTrace();
   	}
   	
   	return null;
	}
	@Actions( { @Action(value = "ajaxGenerateFetTimetable", results = { @Result(location = "../admin/academic/timeTable/ajaxManageFETTimeTable.jsp", name = "success"), @Result(location = "../admin/academic/timeTable/ajaxViewManageTimeTable.jsp", name = "dummyInit")}) })
    public String ajaxGenerateFetTimetable() {
	   	if (log.isDebugEnabled()) {
	   	    log.debug("Entering 'ajaxGenerateFetTimetable' method");
	   	}
	   	try {
	   		Customer customer = (Customer)adminManager.get(Customer.class,getUserCustId()); 
	   		if(!ObjectFunctions.isNullOrEmpty(customer)){
		   		Set<String> emailIdsSet = new HashSet<String>();
				boolean smsStatus = false;
				List<Object[]> emailsAndMobileNumbers = adminManager.getAll("select mobileNumber,email from supportTeam where (email!='' AND email is not null) or (mobileNumber!='' AND mobileNumber is not null)");
				if(!ObjectFunctions.isNullOrEmpty(emailsAndMobileNumbers)){
					for(Object obj[] : emailsAndMobileNumbers){
						if(!ObjectFunctions.isNullOrEmpty(obj[0]))
							getMobileNumbersSet().add(obj[0].toString());
						if(!ObjectFunctions.isNullOrEmpty(obj[1]))
							emailIdsSet.add(obj[1].toString());
						if(customer.isCheckMobileService()){
							StringBuffer smsContent= new StringBuffer();
						    smsContent.append("Dear Supporting Team,");
						    smsContent.append(" The following customer is ready to generate the time table. Details are custid = "+getUserCustId()+" and acdyearId = "+getUserAcademicYearId()+". Thank you from "+customer.getCustomerShortName());
							Messages message = new Messages();
							message.setMessageDescription(smsContent.toString());
							message.setStatus("M");
							message.setCreatedById(getUser().getId());
							//smsStatus = communicationManager.deliverSms(message,getMobileNumbersSet(),getUserCustId());
							log.debug(smsStatus);
						}
						if(!ObjectFunctions.isNullOrEmpty(emailIdsSet)){
						    int i = 0;
						    String[] emailAdd = new String[emailIdsSet.size()];
							for (Object emailId : emailIdsSet) {
							    if (!ObjectFunctions.isNullOrEmpty(emailId)) {
								emailAdd[i] = emailId.toString();
								emailId = null;
							    }
							    i++;
							}
							String server = null;
							String hostUrl=getHostUrl();
							log.debug("hostUrl :"+hostUrl);
							if(hostUrl.contains("dev.")){
								server="Dev Server";
							}else if(hostUrl.contains("https://eazyschool.in")){
								server="Prod Server";
							}else
								server = "Local Server";
						    String body = "The following customer is ready to generate the time table. Details are custid = "+getUserCustId()+" and acdyearId = "+getUserAcademicYearId()+". Thank you from "+customer.getCustomerShortName();
							MailUtil mailUtil=new MailUtil(emailAdd, "Regd : Fet Timetable Requested from "+server, body, getUser(),null,"messages@eazyschool.com");
							mailUtil.sendEmailAndSmsForSupportUrt();
							mailUtil=null;
						}
					}
					super.addActionMessage("Thanks for generating timetable. It will take time to generate time table. Our supporting team will call you once your time table ready.");
				}else
				super.addActionError("Please call supporting team for generating timetable.");
	   		}
	   	}catch (Exception e) {
	   		e.printStackTrace();
	   	}
	   	return SUCCESS;
	}
	public String getMimeType()
    {
        return "application/fet"; //$NON-NLS-1$
    }
	
	//this method parse the timetable xml file and save the database
	/*public void  timeTableSubstitution(){

		//http://www.mkyong.com/java/how-to-read-xml-file-in-java-dom-parser/
		
		Map<String,Staff> staffsMap = new HashMap<String, Staff>();
		List<Staff> staffList = adminManager.getAll(Staff.class,"custId="+getUserCustId()+" and academicYearId<="+getUserAcademicYearId()+" and status='"+Constants.YES_STRING+"'");
		if(!ObjectFunctions.isNullOrEmpty(staffList))
		{
			for(Staff staff : staffList)
			{
				staffsMap.put(String.valueOf(staff.getId()), staff);
				staff = null;
			}
		}
		staffList = null;
		
		Map<String,StudySubject> studySubjectsMap = new HashMap<String, StudySubject>();
		List<StudySubject> studySubjectsList = adminManager.GetAllStudySubjects(getUserCustId(),getUserAcademicYearId());
		if(!ObjectFunctions.isNullOrEmpty(studySubjectsList))
		{
			for(StudySubject studySubject : studySubjectsList)
			{
				studySubjectsMap.put(String.valueOf(studySubject.getId()), studySubject);
				studySubject = null;
			}
		}
		studySubjectsList = null;
		
		Map<String,StudyClass> studyClassMap = new HashMap<String, StudyClass>();
		List<StudyClass> classesList = adminManager.GetAllStudyClasses(getUserCustId(), getUserAcademicYearId(),null);
		if(!ObjectFunctions.isNullOrEmpty(classesList))
		{
			for(StudyClass studyClass : classesList)
			{
				studyClassMap.put(String.valueOf(studyClass.getId()), studyClass);
				studyClass = null;
			}
		}
		classesList = null;
		
		Map<String,Integer> weekDaysMap = new HashMap<String, Integer>();
		List<WeekDays> weekDaysList = adminManager.getAll(WeekDays.class);
		
		if(!ObjectFunctions.isNullOrEmpty(weekDaysList))
		{
			for(WeekDays weekDays : weekDaysList)
			{
				weekDaysMap.put(weekDays.getDayName(),(int)weekDays.getId());
				weekDays = null;
			}
		}
		weekDaysList = null;
		
		Map<String,TimetablePeriods> timetablePeriodsMap = new HashMap<String, TimetablePeriods>();
		
		List<TimetablePeriods> timetablePeriodsList = adminManager.getAll(TimetablePeriods.class,"custId="+getUserCustId()+" and status='"+Constants.ACTIVE_STATUS+"'");
		if(!ObjectFunctions.isNullOrEmpty(timetablePeriodsList))
		{
			for(TimetablePeriods timetablePeriods : timetablePeriodsList)
			{
				timetablePeriodsMap.put(String.valueOf(timetablePeriods.getTimePeriod()), timetablePeriods);
				timetablePeriods = null;
			}
		}
		timetablePeriodsList = null;
		
	    try {

		File fXmlFile = new File("C:/Users/URT/Downloads/63 (1)/63/83/xmlfiles/StudyClassWise.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);
				
		//optional, but recommended
		//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
		doc.getDocumentElement().normalize();

		log.debug("Root element :" + doc.getDocumentElement().getNodeName());
				
		NodeList nList = doc.getElementsByTagName("Subgroup");
		
		log.debug("----------------------------");
		
		for (int temp = 0; temp < nList.getLength(); temp++) {

			Node nNode = nList.item(temp);
					
			log.debug("\nCurrent Element :" + nNode.getNodeName());
					
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {

				org.w3c.dom.Element eElement = (org.w3c.dom.Element) nNode;

				log.debug("Subgroup name : " + eElement.getAttribute("name"));
				
				if(!ObjectFunctions.isNullOrEmpty(studyClassMap.get(eElement.getAttribute("name"))))
				{
					
					NodeList DayList =  eElement.getChildNodes();
					
					for (int tempDay = 0; tempDay < DayList.getLength(); tempDay++) {

						Node nNodeDay = DayList.item(tempDay);
								
						log.debug("\nCurrent Element :" + nNodeDay.getNodeName());
								
						if (nNodeDay.getNodeType() == Node.ELEMENT_NODE) {

							org.w3c.dom.Element eElementDay = (org.w3c.dom.Element) nNodeDay;

							log.debug("Day name : " + eElementDay.getAttribute("name"));
							
							NodeList HourList =  eElementDay.getChildNodes();
							
							if(!ObjectFunctions.isNullOrEmpty(HourList))
							{
								for (int tempHour = 0; tempHour < HourList.getLength(); tempHour++) {

									Node nNodeHour = HourList.item(tempHour);
											
									log.debug("\nCurrent Element :" + nNodeHour.getNodeName());
											
									if (nNodeHour.getNodeType() == Node.ELEMENT_NODE) {

										org.w3c.dom.Element eElementHour = (org.w3c.dom.Element) nNodeHour;

										log.debug("Hour name : " + eElementHour.getAttribute("name"));
										
										SubstitutionTimeTable timeTable = new SubstitutionTimeTable();
										timeTable.setAcademicYearId(getUserAcademicYearId());
										timeTable.setCustId(getUserCustId());
										timeTable.setSubject(null);
										//timeTable.setTeachers(null);
										timeTable.setCreatedById(getUser().getId());
										timeTable.setCreatedDate(new Date());
										timeTable.setLastAccessDate(new Date());
										timeTable.setLastUpdatedById(getUser().getId());
										timeTable.setLastUpdatedDate(new Date());
										
										timeTable.setClassSection(studyClassMap.get(eElement.getAttribute("name")));
										timeTable.setDayId(weekDaysMap.get(eElementDay.getAttribute("name").toUpperCase()));
										timeTable.setPeriodName(eElementHour.getAttribute("name"));
										
										if(ObjectFunctions.isNullOrEmpty(timetablePeriodsMap.get(eElementHour.getAttribute("name"))))
										{
											TimetablePeriods timetablePeriods = new TimetablePeriods();
											timetablePeriods.setCustId(getUserCustId());
											timetablePeriods.setTimePeriod(eElementHour.getAttribute("name"));
											timetablePeriods.setStatus(Constants.ACTIVE_STATUS);
											
											timetablePeriods.setCreatedById(getUser().getId());
											timetablePeriods.setCreatedDate(new Date());
											timetablePeriods.setLastAccessDate(new Date());
											timetablePeriods.setLastUpdatedById(getUser().getId());
											timetablePeriods.setLastUpdatedDate(new Date());
											timetablePeriods = (TimetablePeriods)adminManager.save(timetablePeriods);
											
											timetablePeriodsMap.put(eElementHour.getAttribute("name"), timetablePeriods);
											timetablePeriods = null;
										}
										
										timeTable.setTimetablePeriods(timetablePeriodsMap.get(eElementHour.getAttribute("name")));
										
										NodeList teacherSubject =  eElementHour.getChildNodes();
										
										if(!ObjectFunctions.isNullOrEmpty(teacherSubject))
										{
											for (int tempTeachSub = 0; tempTeachSub < teacherSubject.getLength(); tempTeachSub++) {

												Node nNodeTeachSub = teacherSubject.item(tempTeachSub);
												
												if(!ObjectFunctions.isNullOrEmpty(nNodeTeachSub))
												{
													log.debug("\nCurrent Element :" + nNodeTeachSub.getNodeName());
													
													if("Teacher".equalsIgnoreCase(nNodeTeachSub.getNodeName()))
													{
														if (nNodeTeachSub.getNodeType() == Node.ELEMENT_NODE) 
														{

															org.w3c.dom.Element eElementTeachSub = (org.w3c.dom.Element) nNodeTeachSub;

															log.debug("Teach name : " + eElementTeachSub.getAttribute("name"));
															
															timeTable.setStaff(staffsMap.get(eElementTeachSub.getAttribute("name")));

														}
													}
													else if("Subject".equalsIgnoreCase(nNodeTeachSub.getNodeName()))
													{
														if (nNodeTeachSub.getNodeType() == Node.ELEMENT_NODE) 
														{
															org.w3c.dom.Element eElementTeachSub = (org.w3c.dom.Element) nNodeTeachSub;

															log.debug("Subject name : " + eElementTeachSub.getAttribute("name"));
															timeTable.setSubject(studySubjectsMap.get(eElementTeachSub.getAttribute("name")));

														}
													}
												}
											}
										}
										
										adminManager.save(timeTable);
										timeTable = null;
									}
								}
							}
							
						}
					}
				}
				

			}
		}
			
		log.debug("----------------------------");

		
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	  }*/
	
	@Actions( { @Action(value = "ajaxViewTimetable", results = { @Result(location = "../admin/academic/timeTable/ajaxViewTimetable.jsp", name = "success") }) })
	public String ajaxViewTimetable() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxViewTimetable' method");
		}
		try {
			setStudyClassList(adminManager.GetAllStudyClasses(getUserCustId(), getUserAcademicYearId(),null));
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	
	@Actions( { @Action(value = "ajaxViewStudyclassWiseTimetable", results = { @Result(location = "../admin/academic/timeTable/ajaxViewStudyclassWiseTimetable.jsp", name = "success") }),
		 @Action(value = "ajaxViewStaffWiseTimetable", results = { @Result(location = "../admin/academic/timeTable/ajaxViewStaffWiseTimetable.jsp", name = "success") }) 
	})
	public String ajaxViewStudyclassWiseTimetable() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxViewStudyclassWiseTimetable' method");
		}
		try {
			setObjectList(adminManager.getAll(TimetablePeriods.class,"custId="+getUserCustId()+" and status='"+Constants.ACTIVE_STATUS+"'"));
			//setTempList(adminManager.getAll(WeekDays.class));
			
			List<Object[]> workingDays = adminManager.getAll("select id,dayId from workingDays where academicYearId="+getUserAcademicYearId());
			if(ObjectFunctions.isNotNullOrEmpty(workingDays)){
				StringBuffer dayIds = new StringBuffer();
				dayIds.append("(");
				for(Object[] workingDay : workingDays){
					dayIds.append(workingDay[1].toString()+",");
				}
				dayIds.append("0)");
				setTempList(adminManager.getAll(WeekDays.class, "id in "+dayIds.toString()));
				dayIds = null;
				workingDays = null;
			}
			
			
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	
	@Actions( { @Action(value = "ajaxGetStudyClassWiseTimetableData", results = { @Result(type = "json", name = "success", params = {"includeProperties", "timingsList.*" }) }) })
	public String ajaxGetStudyClassWiseTimetableData() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxGetStudyClassWiseTimetableData' method");
		}
		try {
			List<SubstitutionTimeTable> substitutionTimeTableList =null;
			if(!ObjectFunctions.isNullOrEmpty(getStudyClassId()))
			{
				JSONArray res = new JSONArray();
				JSONObject j;
				if(!ObjectFunctions.isNullOrEmpty(getStudyClassId()))
				{
					StringBuffer query = new StringBuffer("custId=").append(getUserCustId()).append(" and academicYearId=").append(getUserAcademicYearId()).append(" and classSectionId=").append(getStudyClassId());
					substitutionTimeTableList=adminManager.getAll(SubstitutionTimeTable.class, query.toString());
					log.debug("select * from SubstitutionTimeTable where " + query.toString());
				}
				if(!ObjectFunctions.isNullOrEmpty(substitutionTimeTableList))
				{
					for(SubstitutionTimeTable substitutionTimeTable : substitutionTimeTableList)
					{
						if(!ObjectFunctions.isNullOrEmpty(substitutionTimeTable)){
							j=new JSONObject();
							if(!ObjectFunctions.isNullOrEmpty(substitutionTimeTable.getStaff()) && !ObjectFunctions.isNullOrEmpty(substitutionTimeTable.getSubject()))
								j.put("INPUTVALUE",substitutionTimeTable.getSubject().getName() + "<br/>" + substitutionTimeTable.getStaff().getFullPersonName());
							else
								j.put("INPUTVALUE","--");
							
							j.put("TIMETABLEPERIODSID",substitutionTimeTable.getTimetablePeriods().getId()); 
						    j.put("WEEKDAYID",substitutionTimeTable.getDayId());
							res.put(j);
						substitutionTimeTable=null;
						}
					}
				}
			
				j = new JSONObject();
				j.put("studentMarksSettingsData", res);
				getResponse().getOutputStream().print(j.toString());
				
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
	
	
	@Actions( { @Action(value = "ajaxDoViewStaffwiseTimetable", results = { @Result(location = "../admin/academic/timeTable/ajaxGetStaffsForStaffwiseTimetable.jsp", name = "success") }) })
	public String ajaxDoViewStaffwiseTimetable() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDoViewStaffwiseTimetable' method");
		}
		try {
			setTeacherList(adminManager.getAllTeacherListByStatus(getUserCustId(),Constants.YES_STRING));
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	
	
	@Actions( { @Action(value = "ajaxGetStaffWiseTimetableData", results = { @Result(type = "json", name = "success", params = {"includeProperties", "timingsList.*" }) }) })
	public String ajaxGetStaffWiseTimetableData() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxGetStaffWiseTimetableData' method");
		}
		try {
			List<SubstitutionTimeTable> substitutionTimeTableList =null;
			if(!ObjectFunctions.isNullOrEmpty(getTempId()))
			{
				JSONArray res = new JSONArray();
				JSONObject j;
				int sNo=0;
				StringBuffer query = new StringBuffer("custId=").append(getUserCustId()).append(" and academicYearId=").append(getUserAcademicYearId()).append(" and staffId=").append(getTempId());
				substitutionTimeTableList=adminManager.getAll(SubstitutionTimeTable.class, query.toString());
				
				log.debug("select * from SubstitutionTimeTable where " + query.toString());
				if(!ObjectFunctions.isNullOrEmpty(substitutionTimeTableList))
				{
					for(SubstitutionTimeTable substitutionTimeTable : substitutionTimeTableList)
					{
						if(!ObjectFunctions.isNullOrEmpty(substitutionTimeTable)){
							j=new JSONObject();
							if(!ObjectFunctions.isNullOrEmpty(substitutionTimeTable.getStaff()) && !ObjectFunctions.isNullOrEmpty(substitutionTimeTable.getSubject()))
								j.put("INPUTVALUE",substitutionTimeTable.getClassSection().getClassAndSection() + "<br/>" + substitutionTimeTable.getSubject().getName());
							else
								j.put("INPUTVALUE","--");
							
							j.put("TIMETABLEPERIODSID",substitutionTimeTable.getTimetablePeriods().getId()); 
						    j.put("WEEKDAYID",substitutionTimeTable.getDayId());
						    
							res.put(j);
							
							 
						
						substitutionTimeTable=null;
						}
					}
				}
			
				j = new JSONObject();
				j.put("studentMarksSettingsData", res);
				getResponse().getOutputStream().print(j.toString());
				
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
	
	
	@Actions( { @Action(value = "ajaxDoSubstitutionTimetable", results = { @Result(location = "../admin/academic/timeTable/ajaxSubstitutionTimetable.jsp", name = "success") }),
		 @Action(value = "ajaxDoViewSubstitutionTimetable", results = { @Result(location = "../admin/academic/timeTable/ajaxDoViewSubstitutionTimetable.jsp", name = "success") }) 
	})
	public String ajaxDoSubstitutionTimetable() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDoSubstitutionTimetable' method");
		}
		try {
			setAttendanceDate(DateFormatter.getTodayDateStr(DateFormatter.YYYY_MM_DD_PATTERN));
			//setTeacherList(adminManager.getAllTeacherListByStatus(getUserCustId(),Constants.YES_STRING));
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	
	@Actions( { @Action(value = "ajaxGetStaffForTimetableSubstitution", results = { @Result(location = "../admin/academic/timeTable/ajaxGetStaffForTimetableSubstitution.jsp", name = "success") }) })
	public String ajaxGetStaffForTimetableSubstitution() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxGetStaffForTimetableSubstitution' method");
		}
		try {
			List<Object[]> staffsList = adminManager.getAll("select accountId,staffName,roleName,staffId from vw_StaffDailyAttendance where custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and attendanceDate ='"+getAttendanceDate()+"' and present='N'");        	
		 	if(!ObjectFunctions.isNullOrEmpty(staffsList))
		 	{
		 		StringBuffer staffIds = new StringBuffer();
		 		for(Object obj[] : staffsList)
		 		{
		 			ViewStaffPersonAccountDetails viewStaffPersonAccountDetails = new ViewStaffPersonAccountDetails();
			 		viewStaffPersonAccountDetails.setAccountId(Long.valueOf(obj[0].toString()));
			 		viewStaffPersonAccountDetails.setFullName(obj[1].toString() + "(" + obj[2].toString() +")");
			 		viewStaffPersonAccountDetails.setRoleDescription(obj[2].toString());
			 		viewStaffPersonAccountDetails.setStaffId(Long.valueOf(obj[3].toString()));
			 		staffIds.append(obj[3].toString() + ",");
			 		getViewStaffPersonAccountDetailsList().add(viewStaffPersonAccountDetails);
		 		}
		 		//staffIds.append("0)");
		 		setAnyTitle(staffIds.toString());
		 	}
		 	staffsList = null;
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	
	@Actions( {
	 @Action(value = "ajaxStaffTimetableForSubstitutionTimetable", results = { @Result(location = "../admin/academic/timeTable/ajaxStaffTimetableForSubstitutionTimetable.jsp", name = "success") }) 
	})
	public String ajaxStaffTimetableForSubstitutionTimetable() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxViewStudyclassWiseTimetable' method");
		}
		try {
			if(!StringFunctions.isNullOrEmpty(getAttendanceDate()))
			{
				Date aDate=DateFormatter.parseString(DateFormatter.YYYY_MM_DD_PATTERN, getAttendanceDate());
				Calendar c = Calendar.getInstance();
				c.setTime(aDate);
				int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
				
				log.debug("dayOfWeek:" + dayOfWeek);
				setObjectList(adminManager.getAll(TimetablePeriods.class,"custId="+getUserCustId()+" and status='"+Constants.ACTIVE_STATUS+"'"));
				setTempList(adminManager.getAll(WeekDays.class, "id=" + dayOfWeek));
				setAnyId(String.valueOf(dayOfWeek));
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	
	
	@Actions( { @Action(value = "ajaxGetStaffWiseTimetableForSubstitution", results = { @Result(type = "json", name = "success", params = {"includeProperties", "timingsList.*" }) }) })
	public String ajaxGetStaffWiseTimetableForSubstitution() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxGetStaffWiseTimetableForSubstitution' method");
		}
		try {
			List<SubstitutionTimeTable> substitutionTimeTableList =null;
			if(!ObjectFunctions.isNullOrEmpty(getTempId()))
			{
				JSONArray res = new JSONArray();
				JSONObject j;
				StringBuffer query = new StringBuffer("custId=").append(getUserCustId()).append(" and academicYearId=").append(getUserAcademicYearId()).append(" and staffId=").append(getTempId()).append(" and dayId=" + getAnyId() + " order by timetablePeriodId" );
				//StringBuffer query = new StringBuffer("custId=").append(getUserCustId()).append(" and academicYearId=").append(getUserAcademicYearId()).append(" and staffId=").append(getTempId());
				substitutionTimeTableList=adminManager.getAll(SubstitutionTimeTable.class, query.toString());
				log.debug("select * from SubstitutionTimeTable where " + query.toString());
				if(!ObjectFunctions.isNullOrEmpty(substitutionTimeTableList))
				{
					for(SubstitutionTimeTable substitutionTimeTable : substitutionTimeTableList)
					{
						if(!ObjectFunctions.isNullOrEmpty(substitutionTimeTable))
						{
							j=new JSONObject();
							if(!ObjectFunctions.isNullOrEmpty(substitutionTimeTable.getStaff()) && !ObjectFunctions.isNullOrEmpty(substitutionTimeTable.getSubject()))
							{
								List<SubstitutionTimeTable> allStaffsList = new ArrayList<SubstitutionTimeTable>();
								
								StringBuffer currentStaffBuffer = new StringBuffer("select staffId from substitutionTimeTable where ");
								
								currentStaffBuffer.append("custId="+ getUserCustId());
								currentStaffBuffer.append(" and academicYearId="+ getUserAcademicYearId());
								currentStaffBuffer.append(" and dayId="+ substitutionTimeTable.getDayId());
								currentStaffBuffer.append(" and timetablePeriodId="+ substitutionTimeTable.getTimetablePeriods().getId());
								
								List<Object[]> currentClassStaffList = adminManager.getAll(currentStaffBuffer.toString() + " and classSectionId=" + substitutionTimeTable.getClassSection().getId());
								//List<Object[]> currentStaffList = adminManager.getAll("select staffId from substitutionTimeTable where custId="+ getUserCustId() + " and academicYearId="+getUserAcademicYearId() + " and classSectionId=" + substitutionTimeTable.getClassSection().getId() + " and dayId ="+ substitutionTimeTable.getDayId() + " and timetablePeriodId=" +substitutionTimeTable.getTimetablePeriods().getId());
								String currentClassStaffIdsString = null;
								if (!ObjectFunctions.isNullOrEmpty(currentClassStaffList)) 
									currentClassStaffIdsString = StringFunctions.convertListToCommaDelimitedString(currentClassStaffList);
								//currentClassStaffIdsString = currentClassStaffIdsString +","+ getAnyTitle() + "0)";
								//currentClassStaffIdsString = currentClassStaffIdsString.replaceAll("null", "0");
								/*String sql = "custId="+ getUserCustId() + " and academicYearId="+getUserAcademicYearId() + " and staffId not in "+currentClassStaffIdsString + " and classSectionId=" + substitutionTimeTable.getClassSection().getId();
								List<SubstitutionTimeTable> remainingStaffList=adminManager.getAll(SubstitutionTimeTable.class, sql);*/

								List<Object[]> otherClassStaffList = adminManager.getAll(currentStaffBuffer.toString());
								//List<Object[]> currentStaffList = adminManager.getAll("select staffId from substitutionTimeTable where custId="+ getUserCustId() + " and academicYearId="+getUserAcademicYearId() + " and classSectionId=" + substitutionTimeTable.getClassSection().getId() + " and dayId ="+ substitutionTimeTable.getDayId() + " and timetablePeriodId=" +substitutionTimeTable.getTimetablePeriods().getId());
								String otherClassStaffIdsString= "(";
								if (!ObjectFunctions.isNullOrEmpty(otherClassStaffList)) 
									otherClassStaffIdsString = otherClassStaffIdsString + StringFunctions.convertListToCommaDelimitedString(otherClassStaffList);
								otherClassStaffIdsString = otherClassStaffIdsString + ","+ currentClassStaffIdsString +","+ getAnyTitle() + "0)";
								
								otherClassStaffIdsString = otherClassStaffIdsString.replaceAll("null", "0");
								
								String sql = "custId="+ getUserCustId() + " and academicYearId="+getUserAcademicYearId() + " and staffId not in "+otherClassStaffIdsString;
								List<SubstitutionTimeTable> remainingAllStaffList=adminManager.getAll(SubstitutionTimeTable.class, sql);
								
								//allStaffsList.addAll(remainingStaffList);
								allStaffsList.addAll(remainingAllStaffList);
								
								//remainingStaffList= null;
								remainingAllStaffList = null;
								
								Set<SubstitutionTimeTable> allStaffsSet = new TreeSet<SubstitutionTimeTable>(new StaffSubstitutionTimeTableComparator()); //this removes all duplicate objects
								
								allStaffsSet.addAll(allStaffsList);
								
								List<SubstitutionTimeTable> remainingSubstitutionTimeTableList = new ArrayList<SubstitutionTimeTable>();
								for(SubstitutionTimeTable substitutionTimeTableObj : allStaffsSet)
								{
									//StaffSubstitutionTimeTable staffSubstitutionTimeTable =(StaffSubstitutionTimeTable) adminManager.get(StaffSubstitutionTimeTable.class,"custId="+getUserCustId()+" and academicYearId ="+getUserAcademicYearId()+ " and substitutionDate = '" +getAttendanceDate()+ "' and substituteStaffId = "+substitutionTimeTableObj.getStaff().getId() + " and classSectionId=" + substitutionTimeTable.getClassSection().getId() + " and subjectId="+substitutionTimeTable.getSubject().getId() + " and dayId="+ substitutionTimeTable.getDayId() + " and timetablePeriodId=" +substitutionTimeTable.getTimetablePeriods().getId());
									StaffSubstitutionTimeTable staffSubstitutionTimeTable =(StaffSubstitutionTimeTable) adminManager.get(StaffSubstitutionTimeTable.class,"custId="+getUserCustId()+" and academicYearId ="+getUserAcademicYearId()+ " and substitutionDate = '" +getAttendanceDate()+ "' and substituteStaffId = "+substitutionTimeTableObj.getStaff().getId() + " and classSectionId !=" + substitutionTimeTable.getClassSection().getId() + " and subjectId!="+substitutionTimeTable.getSubject().getId() + " and dayId="+ substitutionTimeTable.getDayId() + " and timetablePeriodId=" +substitutionTimeTable.getTimetablePeriods().getId());
									if(ObjectFunctions.isNullOrEmpty(staffSubstitutionTimeTable))
									{
										remainingSubstitutionTimeTableList.add(substitutionTimeTableObj);
									}
								}
								
								if(!ObjectFunctions.isNullOrEmpty(remainingSubstitutionTimeTableList))
								{
									StringBuffer buffer = new StringBuffer();
									//buffer.append("<div class='col-md-6'><div class='form-group'><div class='col-md-8'>");
									buffer.append("<select class='form-control input-small required' id='substitutionStaffId"+substitutionTimeTable.getTimetablePeriods().getId() + "' name='"+substitutionTimeTable.getTimetablePeriods().getId()+"'>");
									buffer.append("<option value=''>- Select -</option>");
									for(SubstitutionTimeTable stimtable : remainingSubstitutionTimeTableList)
									{
										String selectedOption = null;
										StaffSubstitutionTimeTable staffSubstitutionTimeTable =(StaffSubstitutionTimeTable) adminManager.get(StaffSubstitutionTimeTable.class,"custId="+getUserCustId()+" and academicYearId ="+getUserAcademicYearId()+ " and substitutionDate = '" +getAttendanceDate()+ "' and actualStaffId = "+getTempId() + " and substituteStaffId=" +stimtable.getStaff().getId() + " and classSectionId=" + substitutionTimeTable.getClassSection().getId() + " and subjectId="+substitutionTimeTable.getSubject().getId() + " and dayId="+ substitutionTimeTable.getDayId() + " and timetablePeriodId=" +substitutionTimeTable.getTimetablePeriods().getId());
										if(!ObjectFunctions.isNullOrEmpty(staffSubstitutionTimeTable))
										{
											selectedOption = "selected=selected";
										}
										//dayId&timetablePeridId&studyClassId&studySubjectId&substituteStaffId
										String generateId= substitutionTimeTable.getDayId() + ":"+ substitutionTimeTable.getTimetablePeriods().getId() + ":" + substitutionTimeTable.getClassSection().getId()+":" + substitutionTimeTable.getSubject().getId()+":" + stimtable.getStaff().getId();
										buffer.append("<option value='"+generateId+"'" +selectedOption+">"+stimtable.getStaff().getFullPersonName()+"</option>");
										stimtable = null;
										staffSubstitutionTimeTable = null;
									}
									buffer.append("</select>");
									//buffer.append("</div></div></div>");
											
									j.put("INPUTVALUE",substitutionTimeTable.getClassSection().getClassAndSection() + "<br/>" + substitutionTimeTable.getSubject().getName() + "<br/>" + buffer.toString() );
								}
								else
									j.put("INPUTVALUE","--");
								
								allStaffsList = null;
								allStaffsSet = null;
								remainingSubstitutionTimeTableList = null;
							}
							else
								j.put("INPUTVALUE","--");
							
							j.put("TIMETABLEPERIODSID",substitutionTimeTable.getTimetablePeriods().getId()); 
						    j.put("WEEKDAYID",substitutionTimeTable.getDayId());
						    
							res.put(j);
						
						substitutionTimeTable=null;
						}
					}
				}
			
				j = new JSONObject();
				j.put("studentMarksSettingsData", res);
				getResponse().getOutputStream().print(j.toString());
				log.debug("Json Array:" + j.toString());
				
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
	
	@Actions( {
		 @Action(value = "ajaxAddSubstitutionTimetable", results = { @Result(location = "../admin/academic/timeTable/ajaxSubstitutionTimetable.jsp", name = "success") }) 
		})
		public String ajaxAddSubstitutionTimetable() throws URTUniversalException {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxAddSubstitutionTimetable' method");
			}
			try {
				
				String actualStaffId = getParamValue("actualStaffId");
				if(!StringFunctions.isNullOrEmpty(getAttendanceDate()) && !StringFunctions.isNullOrEmpty(actualStaffId))
				{
					List<TimetablePeriods> timetablePeriodsList = adminManager.getAll(TimetablePeriods.class,"custId="+getUserCustId()+" and status='"+Constants.ACTIVE_STATUS+"'");
					if(!ObjectFunctions.isNullOrEmpty(timetablePeriodsList))
					{
						Map<String,Staff> staffsMap = new HashMap<String, Staff>();
						List<Staff> staffList = adminManager.getAll(Staff.class,"custId="+getUserCustId()+" and academicYearId<="+getUserAcademicYearId()+" and status='"+Constants.YES_STRING+"'");
						if(!ObjectFunctions.isNullOrEmpty(staffList))
						{
							for(Staff staff : staffList)
							{
								staffsMap.put(String.valueOf(staff.getId()), staff);
								staff = null;
							}
						}
						staffList = null;
						
						Map<String,StudySubject> studySubjectsMap = new HashMap<String, StudySubject>();
						List<StudySubject> studySubjectsList = adminManager.GetAllStudySubjects(getUserCustId(),getUserAcademicYearId());
						if(!ObjectFunctions.isNullOrEmpty(studySubjectsList))
						{
							for(StudySubject studySubject : studySubjectsList)
							{
								studySubjectsMap.put(String.valueOf(studySubject.getId()), studySubject);
								studySubject = null;
							}
						}
						studySubjectsList = null;
						
						Map<String,StudyClass> studyClassMap = new HashMap<String, StudyClass>();
						List<StudyClass> classesList = adminManager.GetAllStudyClasses(getUserCustId(), getUserAcademicYearId(),null);
						if(!ObjectFunctions.isNullOrEmpty(classesList))
						{
							for(StudyClass studyClass : classesList)
							{
								studyClassMap.put(String.valueOf(studyClass.getId()), studyClass);
								studyClass = null;
							}
						}
						classesList = null;
						
						for(TimetablePeriods timetablePeriods : timetablePeriodsList)
						{
							String periodValue = getParamValue(String.valueOf(timetablePeriods.getId()));
							if(!StringFunctions.isNullOrEmpty(periodValue))
							{
								String[] periodValueArr = periodValue.split(":");
								if (periodValueArr.length > 0) {
									String dayId = periodValueArr[0];
									String timetablePeridId = periodValueArr[1];
									String studyClassId = periodValueArr[2];
									String studySubjectId = periodValueArr[3];
									String substituteStaffId = periodValueArr[4];
									
									StaffSubstitutionTimeTable staffSubstitutionTimeTable =(StaffSubstitutionTimeTable) adminManager.get(StaffSubstitutionTimeTable.class,"custId="+getUserCustId()+" and academicYearId ="+getUserAcademicYearId()+ " and substitutionDate = '" +getAttendanceDate()+ "' and actualStaffId = "+actualStaffId + " and classSectionId=" + studyClassId + " and subjectId="+studySubjectId + " and dayId="+ dayId + " and timetablePeriodId=" +timetablePeridId);
									if(ObjectFunctions.isNullOrEmpty(staffSubstitutionTimeTable))
									{
										staffSubstitutionTimeTable = new StaffSubstitutionTimeTable();
										staffSubstitutionTimeTable.setAcademicYearId(getUserAcademicYearId());
										staffSubstitutionTimeTable.setCustId(getUserCustId());
										
										staffSubstitutionTimeTable.setCreatedById(getUser().getId());
										staffSubstitutionTimeTable.setCreatedDate(new Date());
										
										staffSubstitutionTimeTable.setActualStaff(staffsMap.get(actualStaffId));
										staffSubstitutionTimeTable.setClassSection(studyClassMap.get(studyClassId));
										staffSubstitutionTimeTable.setSubject(studySubjectsMap.get(studySubjectId));
										staffSubstitutionTimeTable.setDayId(Integer.valueOf(dayId));
										staffSubstitutionTimeTable.setPeriodName(timetablePeriods.getTimePeriod());
										staffSubstitutionTimeTable.setTimetablePeriods(timetablePeriods);
										staffSubstitutionTimeTable.setSubstitutionDate(getAttendanceDate());
									}
									
									staffSubstitutionTimeTable.setLastAccessDate(new Date());
									staffSubstitutionTimeTable.setLastUpdatedById(getUser().getId());
									staffSubstitutionTimeTable.setLastUpdatedDate(new Date());
									staffSubstitutionTimeTable.setSubstituteStaff(staffsMap.get(substituteStaffId));
									
									adminManager.save(staffSubstitutionTimeTable);
									
									staffSubstitutionTimeTable = null;
								}
							}
							timetablePeriods = null;
						}
						super.addActionMessage("Substitution staff is successfully assigned to the timetable");
						staffsMap = null;
						studySubjectsMap = null;
						studyClassMap = null;
					}
				}
			} catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			finally
			{
				ajaxDoSubstitutionTimetable();
			}
			return SUCCESS;
		}
	
	@Actions( { @Action(value = "ajaxViewSubstitutionStaffTimetable", results = { @Result(location = "../admin/academic/timeTable/ajaxViewSubstitutionTimetable.jsp", name = "success") })})
	public String ajaxViewSubstitutionStaffTimetable() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxViewSubstitutionStaffTimetable' method");
		}
		try {
			if(!StringFunctions.isNullOrEmpty(getAttendanceDate()))
			{
				//setObjectList(adminManager.getAll(StaffSubstitutionTimeTable.class,"custId="+getUserCustId()+" and academicYearId ="+getUserAcademicYearId()+ " and substitutionDate = '" +getAttendanceDate()+ "'"));
				setObjectList(adminManager.getStaffSubstitutionTimeTableListsByAcademicYearIdAndDate(getUserCustId(), getUserAcademicYearId(), getAttendanceDate(), getParamValue("staffId")));
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	// Manual Timetable
	@Actions({ @Action(value = "ajaxDoTimeTableSettingsInformation", results = { @Result(location = "../admin/manualTimeTable/ajaxTimeTableSettingsDetails.jsp", name = "success") }) })
	public String ajaxDoTimeTableSettingsInformation(){
		if (log.isInfoEnabled()) log.info("Entering 'ajaxDoTimeTableSettingsInformation' method");
		try {
			if(!ObjectFunctions.isNullOrEmpty(getUserAcademicYearId())){
				setTimeTableSettings(getTimeTableSettingsByAcademicYearId(getUserAcademicYearId()));
			}
		} catch (Exception ex) {
			ex.printStackTrace();JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		return SUCCESS;
	}
	@Actions({ @Action(value = "ajaxAddTimeTableSettingsDetails", results = { @Result(location = "../admin/manualTimeTable/ajaxTimeTableSettingsDetails.jsp", name = "success") })})
	public String ajaxAddTimeTableSettingsDetails() {
		if (log.isInfoEnabled()) log.info("Entering 'ajaxAddTimeTableSettingsDetails' method");
		try {
			long custId=getUserCustId();
			if(custId>0 && getUserAcademicYearId()>0){
				TimeTableSettings timeTableSettings=null;
				if(getTimeTableSettingsId()>0){
					timeTableSettings=getTimeTableSettingsById(getTimeTableSettingsId());
					timeTableSettings.setLastUpdatedById(getUser().getId());
					timeTableSettings.setLastUpdatedDate(new Date());
				}else{
					timeTableSettings=new TimeTableSettings();
					timeTableSettings.setCreatedById(getUser().getId());
				}
				if(!ObjectFunctions.isNullOrEmpty(getTimeTableSettings())){
					timeTableSettings.setCollegeStartTime(getTimeTableSettings().getCollegeStartTime());
					timeTableSettings.setCollegeEndTime(getTimeTableSettings().getCollegeEndTime());
					timeTableSettings.setNoOfPeriodsPerDay(getTimeTableSettings().getNoOfPeriodsPerDay());
					timeTableSettings.setDurationOfEachPeriod(getTimeTableSettings().getDurationOfEachPeriod());
					int totalPeriods=getTimeTableSettings().getNoOfPeriodsPerDay();
					if(getTimeTableSettings().getLunchBreakAfterNoOfPeriods()>0){
						timeTableSettings.setLunchBreakAfterNoOfPeriods(getTimeTableSettings().getLunchBreakAfterNoOfPeriods());
						timeTableSettings.setDurationOfLunchBreak(getTimeTableSettings().getDurationOfLunchBreak());
						totalPeriods = totalPeriods+1;
					}
					if(getTimeTableSettings().getShortBreakAfterNoOfPeriodsMorningSession()>0){
						timeTableSettings.setShortBreakAfterNoOfPeriodsMorningSession(getTimeTableSettings().getShortBreakAfterNoOfPeriodsMorningSession());
						timeTableSettings.setShortBreakMorningSessionDuration(getTimeTableSettings().getShortBreakMorningSessionDuration());
						totalPeriods = totalPeriods+1;
					}
					if(getTimeTableSettings().getShortBreakAfterNoOfPeriodsAfternoonSession()>0){
						timeTableSettings.setShortBreakAfterNoOfPeriodsAfternoonSession(getTimeTableSettings().getShortBreakAfterNoOfPeriodsAfternoonSession());
						timeTableSettings.setShortBreakAfternoonSessionDuration(getTimeTableSettings().getShortBreakAfternoonSessionDuration());
						totalPeriods = totalPeriods+1;
					}
					timeTableSettings.setAcademicYearId(getUserAcademicYearId());
					timeTableSettings.setCustId(custId);
					TimeTablePeriod timeTablePeriodsObj=null;
					Calendar cal = Calendar.getInstance();
					String[] collegeStartTimeHour=getTimeTableSettings().getCollegeStartTime().split(":");
					String[] collegeStartTimeEndMinute=collegeStartTimeHour[1].split(" ");
	 	        	cal.setTime(new Date());
		        	cal.set(Calendar.HOUR_OF_DAY, Integer.valueOf(collegeStartTimeHour[0]));
		        	cal.set(Calendar.MINUTE, Integer.valueOf(collegeStartTimeEndMinute[0]));
		        	cal.set(Calendar.SECOND, 0);
		        	String startTime=null;
		        	String endTime=null;
		        	int j=1;
		        	Date startTimeDate=null;
		        	Date endTimeDate=null;
					for(int i = 1; i <= totalPeriods ; i++) {
						timeTablePeriodsObj=new TimeTablePeriod();
						timeTablePeriodsObj.setCreatedById(getUser().getId());
						timeTablePeriodsObj.setCreatedDate(new Date());
						if(getTimeTableSettings().getShortBreakAfterNoOfPeriodsMorningSession()>0 && i == getTimeTableSettings().getShortBreakAfterNoOfPeriodsMorningSession()+1){
							timeTablePeriodsObj.setPeriodName(Constants.SHORT_BREAK);
							cal.add(Calendar.MINUTE, getTimeTableSettings().getShortBreakMorningSessionDuration());
							endTime=cal.get(Calendar.HOUR_OF_DAY)+":"+cal.get(Calendar.MINUTE);
						}else if(getTimeTableSettings().getLunchBreakAfterNoOfPeriods()>0 && i == getTimeTableSettings().getLunchBreakAfterNoOfPeriods()+((getTimeTableSettings().getShortBreakAfterNoOfPeriodsMorningSession()>0)?1:0)+1){
							timeTablePeriodsObj.setPeriodName(Constants.LUNCH_BREAK);
							cal.add(Calendar.MINUTE, getTimeTableSettings().getDurationOfLunchBreak());
							endTime=cal.get(Calendar.HOUR_OF_DAY)+":"+cal.get(Calendar.MINUTE);
						}else if(getTimeTableSettings().getShortBreakAfterNoOfPeriodsAfternoonSession()>0 && i == getTimeTableSettings().getShortBreakAfterNoOfPeriodsAfternoonSession()+((getTimeTableSettings().getShortBreakAfterNoOfPeriodsMorningSession()>0)?1:0)+((getTimeTableSettings().getLunchBreakAfterNoOfPeriods()>0)?1:0)+1){
							timeTablePeriodsObj.setPeriodName(Constants.SHORT_BREAK);
							cal.add(Calendar.MINUTE, getTimeTableSettings().getShortBreakAfternoonSessionDuration());
							endTime=cal.get(Calendar.HOUR_OF_DAY)+":"+cal.get(Calendar.MINUTE);
						}else{
							timeTablePeriodsObj.setStatus(true);
							startTime=cal.get(Calendar.HOUR_OF_DAY)+":"+cal.get(Calendar.MINUTE);
							timeTablePeriodsObj.setPeriodName(Constants.PERIOD+" "+j);
							cal.add(Calendar.MINUTE, Integer.valueOf(getTimeTableSettings().getDurationOfEachPeriod()));
							endTime=cal.get(Calendar.HOUR_OF_DAY)+":"+cal.get(Calendar.MINUTE);
							j++;
						}
						startTimeDate=convertStringTimeToDate(startTime);
						timeTablePeriodsObj.setStartTime(startTimeDate);
						endTimeDate=convertStringTimeToDate(endTime);
						timeTablePeriodsObj.setEndTime(endTimeDate);
						timeTablePeriodsObj.setCustId(custId);
						timeTableSettings.addTimeTablePeriod(timeTablePeriodsObj);
						startTime=endTime;
						startTimeDate=null;endTimeDate=null;
						timeTablePeriodsObj=null;
					}startTime=null;endTime=null;totalPeriods=0;
					setTimeTableSettings((TimeTableSettings) adminManager.saveOrUpdateObject(timeTableSettings));
					if(getTimeTableSettingsId()>0){
						super.addActionMessage("Timetable settings updated successfully.");
					}else{
						super.addActionMessage("Timetable settings added successfully.");
					}
				}timeTableSettings=null;
			}
		} catch (Exception ex) {
			ex.printStackTrace();JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		return SUCCESS;
	}
	@Actions({ @Action(value = "ajaxViewTimeTableDetails", results = { @Result(location = "../admin/manualTimeTable/ajaxDoTimeTableSettingsHome.jsp", name = "success") }),
				@Action(value = "ajaxDoGetStudyClassList", results = { @Result(location = "../admin/manualTimeTable/ajaxViewStudyClassList.jsp", name = "success") }) })
	public String ajaxViewTimeTableDetails() {
		if (log.isInfoEnabled()) log.info("Entering 'ajaxViewTimeTableDetails' method");
		try {
			ajaxDoTimeTableSettingsInformation();
			if(!ObjectFunctions.isNullOrEmpty(getUser()) && !ObjectFunctions.isNullOrEmpty(getTimeTableSettings())){
				if(getUser().isSchoolAdmin()){
					setStudyClassList(getStudyClasses(getTimeTableSettings().getAcademicYearId(),0));
				}else if(getUser().isSchoolTeacher()){
					setStudyClassList(getStudyClasses(getTimeTableSettings().getAcademicYearId(),getStaffByUserId().getId())); // This is for staff teaching subject of class individual timetable 
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		return SUCCESS;
	}
	// Subject Details by class selection
	@Actions({ @Action(value = "ajaxAssignSubjectsToPeriodForTimeTable", results = { @Result(location = "../admin/manualTimeTable/ajaxAssignSubjectsToPeriodForTimeTable.jsp", name = "success")}),
			   @Action(value = "ajaxDoViewTimeTableDetails", results = { @Result(location = "../admin/manualTimeTable/ajaxViewTimeTableDetails.jsp", name = "viewSUCCESS")}) })
	public String ajaxAssignSubjectsToPeriodForTimeTable() throws URTUniversalException {
		if (log.isInfoEnabled()) log.info("Entering 'ajaxAssignSubjectsToPeriodForTimeTable' method");
		try{
			String workingDayIds=null;
			long custId=getUserCustId();
			if(!ObjectFunctions.isNullOrEmpty(getUserAcademicYearId()) && StringFunctions.isNotNullOrEmpty(getStudyClassId())){
				setTimeTableDetailsCount(adminManager.getCount("timeTableDetails", "studyClassId="+getStudyClassId()));
				if(StringFunctions.isNotNullOrEmpty(getAnyTitle()) && Constants.YES_STRING.equalsIgnoreCase(getAnyTitle()) && getTimeTableDetailsCount()==0){
					return "viewSUCCESS";
				}
				List<Long> workingDayListIds = adminManager.getAll("select dayId from workingDays where academicYearId="+getUserAcademicYearId());
				if(!ObjectFunctions.isNullOrEmpty(workingDayListIds))
					 workingDayIds = StringFunctions.convertListToCommaDelimitedString(workingDayListIds);
				setWeekDayList(adminManager.getAll(WeekDays.class,"id in ("+workingDayIds+")"));
				workingDayIds=null;workingDayListIds=null;
				TimeTableSettings timeTableSettingsObj=getTimeTableSettingsByAcademicYearId(getUserAcademicYearId());
				// getAnyTitle() -- Used for view timetable
				if(StringFunctions.isNotNullOrEmpty(getAnyTitle())){
					setTimeTablePeriodList(adminManager.getAll("select id,periodName,TIME_FORMAT(startTime, '%h:%i') as startTime,TIME_FORMAT(endTime, '%h:%i') as endTime from timeTablePeriod where timeTableSettingId="+timeTableSettingsObj.getId()));
					List<Object[]> assingedSubjectList =adminManager.getAll("select studySubjectId,studyClassId,subjectName,group_concat(concat(fullName) separator ', ') from vw_staffSubjectsDetails where custId="+custId+" and studyClassId ="+getStudyClassId()+" and academicYearId="+getUserAcademicYearId()+" group by studySubjectId");
					setTempHashMap1(null);
					if(ObjectFunctions.isNotNullOrEmpty(assingedSubjectList)){
						for (Object[] obj1 : assingedSubjectList){ 
							if (!ObjectFunctions.isNullOrEmpty(obj1[0])) {
								getTempHashMap1().put(Long.valueOf(obj1[0].toString()) , obj1[3].toString());
								obj1=null;
							}
						}
					}assingedSubjectList=null;
				}else{
					setTimeTablePeriodList(adminManager.getAll("select id,periodName,TIME_FORMAT(startTime, '%h:%i') as startTime,TIME_FORMAT(endTime, '%h:%i') as endTime from timeTablePeriod where timeTableSettingId="+timeTableSettingsObj.getId()+" and status='"+Constants.YES_STRING+"'"));
				}
				StudyClass studyClass = getStudyClassById(Long.valueOf(getStudyClassId()));//(StudyClass) adminManager.get(StudyClass.class, "id="+getTempId()+" and status='"+Constants.YES_STRING+"'");
				if(!ObjectFunctions.isNullOrEmpty(studyClass) && ObjectFunctions.isNotNullOrEmpty(studyClass.getSubjects())){
					setSubjectsList(ConvertUtil.convertSetToList(studyClass.getSubjects()));
					for(StudySubject obj:studyClass.getSubjects()){
						if(!ObjectFunctions.isNullOrEmpty(obj)){
							if(obj.isLanguage()){
								getObjectList1().add(obj);
							}
							if(Constants.YES_STRING.equalsIgnoreCase(obj.getSubjectType())){
								getObjectList().add(obj);
							}
							obj=null;
						}
					}
					studyClass=null;
				}
				if(StringFunctions.isNotNullOrEmpty(getAnyTitle())){
					return "viewSUCCESS";
				}
			}
		} catch(Exception ex) {
			ex.printStackTrace();JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		return SUCCESS;
	}
	@Actions( {@Action(value = "ajaxSaveSubjectForDayEachPeriod", results = { @Result(location = "../admin/manualTimeTable/ajaxAssignSubjectsToPeriodForTimeTable.jsp", name = "success") }) })
	public String ajaxSaveSubjectForDayEachPeriod() throws URTUniversalException {
		if (log.isInfoEnabled()) log.info("Entering 'ajaxSaveSubjectForDayEachPeriod' method");
		try {
			if(StringFunctions.isNotNullOrEmpty(getStudyClassId())) {
				//prepareTimeTableSubjectPeriods();
				if(StringFunctions.isNotNullOrEmpty(getTempString()) && StringFunctions.isNotNullOrEmpty(getTempString().replace("[", "").replace("]", ""))){
					addActionMessages(adminManager.saveOrUpdateTimetableDetails(getTempString(),Long.valueOf(getStudyClassId()),getChangeInTimeTable(),getUser(),getStartDate()));
					ajaxAssignSubjectsToPeriodForTimeTable();
					//adminManager.sendNotificationForTimetableUpdate("Assign subjects to periods successfully.", getUserCustId());
				}else{
					super.addActionError("Timetable details not added, please contact support team.");
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		//ajaxGetStudyClassList();
		return SUCCESS;
	}
	@Actions( { @Action(value = "ajaxGetSubjectPeriodDayWiseDetails", results = { @Result(type = "json", name = "success", params = {"includeProperties", "timingsList.*" }) })})
	public String ajaxGetSubjectPeriodDayWiseDetails() throws URTUniversalException {
		if (log.isInfoEnabled()) log.info("Entering 'ajaxGetSubjectPeriodDayWiseDetails' method");
		try {
			List<Object[]> timeTableDetailsList = null;
			if(!StringFunctions.isNullOrEmpty(getStudyClassId())){
				timeTableDetailsList = adminManager.getAll("SELECT GROUP_CONCAT(studySubjectId ORDER BY studySubjectId ASC),dayId,timeTablePeriodId,language,GROUP_CONCAT(subjectName ORDER BY subjectName ASC),subjectType FROM vw_timeTable WHERE custId="+getUserCustId()+" and studyClassId="+getStudyClassId()+"  and timeTableDetailsStatus='"+Constants.YES_STRING+"' GROUP BY dayId,timeTablePeriodId,studyClassId ORDER BY subjectName,id");
			}else if(getTempId()>0 && getUser().isSchoolTeacher() || getUser().isOnlySchoolHod()){
				Staff staff = (Staff)adminManager.get(Staff.class,"accountId="+getTempId());
				timeTableDetailsList = adminManager.getAll("SELECT GROUP_CONCAT(studySubjectId ORDER BY studySubjectId ASC),dayId,timeTablePeriodId,language,GROUP_CONCAT(subjectName,'(',classAndSection, ')' ORDER BY subjectName ASC),subjectType FROM vw_timeTableStaffDetails WHERE custId="+getUserCustId()+" and staffId = " + staff.getId()+"  and timeTableDetailsStatus='"+Constants.YES_STRING+"' GROUP BY dayId,timeTablePeriodId,staffId ORDER BY subjectName,id");
			}
			if(ObjectFunctions.isNotNullOrEmpty(timeTableDetailsList)){
				 JSONArray res = new JSONArray();
				 HashMap<String, String> workingPerioads = null;
				 JSONObject j;
				 for (Object[] subj : timeTableDetailsList){
					j=new JSONObject();
					if (!ObjectFunctions.isNullOrEmpty(subj) && !ObjectFunctions.isNullOrEmpty(subj[0]) && !ObjectFunctions.isNullOrEmpty(subj[1])){
						j.put("DAYID",subj[1].toString());
					    j.put("PERIODID",subj[2].toString());
				    	j.put("PRACTICALVAL",subj[3].toString());
				    	//j.put("NOOFPERIODSID",subj[4].toString());
					    if(StringFunctions.isNotNullOrEmpty(getAnyTitle())){
					    	 j.put("INPUTVALUE",subj[4].toString()); // StudySubject Name
					    }else{
					    	j.put("INPUTVALUE",subj[0].toString());// StudySubject Id
					    }
					    j.put("STUDYSUBJECTID", subj[0].toString());
					    j.put("STUDYSUBJECTNAME", subj[4].toString());
					    j.put("SUBJECTTYPE",subj[5].toString());
						res.put(j);
						subj=null;
				    }
				}
				j = new JSONObject();
				j.put("dayPeriodSubjectSettingsData", res);
				getResponse().getOutputStream().print(j.toString());
				res=null;timeTableDetailsList=null;
			} 
		} catch (Exception ex) {
			ex.printStackTrace();JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		return null;
	}
	@Actions({ @Action(value = "ajaxGetLabCountDetailsBySubjectId", results = { @Result(location = "../admin/manualTimeTable/ajaxGetLabCountDetailsBySubjectId.jsp", name = "success") }) })
	public String ajaxGetLabCountDetailsBySubjectId() {
		if (log.isInfoEnabled()) log.info("Entering 'ajaxGetLabCountDetailsBySubjectId' method");
		try {
			if(getStudySubjectId()>0){
				StudySubject studySubjectObj=getStudySubjectById(getStudySubjectId());
				if(!ObjectFunctions.isNullOrEmpty(studySubjectObj)){
					if(studySubjectObj.isLanguage()){
						setAnyId(String.valueOf(1));
						setAnyTitle("0");
					}else if(Constants.YES_STRING.equalsIgnoreCase(studySubjectObj.getSubjectType())){
						setAnyTitle("1");
						setAnyId("0");
					}else{
						setAnyId("0");
						setAnyTitle("0");
					}	
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		return SUCCESS;
	}
	@Actions({ @Action(value = "ajaxGetStudyClassTakeAttendanceOrNot",  results = { @Result(location = "../admin/manualTimetable/ajaxViewAttendanceError.jsp", name = "success") }) })
	public String ajaxGetStudyClassTakeAttendanceOrNot() throws URTUniversalException {
		if (log.isInfoEnabled()) log.info("Entering 'ajaxGetStudyClassTakeAttendanceOrNot' method");
		try {
			String todayDate = DateFormatter.formatDate (DateFormatter.CCYY_MM_DD_PATTERN, getStartDate());
			String todate =  DateFormatter.formatDate (DateFormatter.CCYY_MM_DD_PATTERN, new Date());
			if(!ObjectFunctions.isNullOrEmpty(getStartDate()) && !ObjectFunctions.isNullOrEmpty(getStudyClassId())){
				setAttendanceSubmittedCount(adminManager.getCount("staffDailyAttendanceSubmitTrack", "custId="+getUserCustId()+" and studyClassId="+getStudyClassId()+" and attendanceDate between '"+todayDate+" 00:00:00' and '"+todate+" 00:00:00'"));
			}
		} catch (Exception ex) { 
			ex.printStackTrace();JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		return SUCCESS;
	}
	public List prepareTimeTableSubjectPeriods(){
		if (log.isInfoEnabled()) log.info("Entering 'prepareTimeTableSubjectPeriods' method");
		try{
			if(getUserCustId()>0 && !ObjectFunctions.isNullOrEmpty(getCurrentAcademicYear())){
				TimeTableSettings timeTableSettingsObj=getTimeTableSettingsByAcademicYearId(getUserAcademicYearId());
				if(!ObjectFunctions.isNullOrEmpty(timeTableSettingsObj) && ObjectFunctions.isNotNullOrEmpty(timeTableSettingsObj.getTimeTablePeriodList())){
					String[] periodName=null;
					String suffixVal=null;
					for(TimeTablePeriod obj:timeTableSettingsObj.getTimeTablePeriodList()){
						if(obj.isStatus()){
							getTimeTablePeriodList().add(obj);
							periodName=obj.getPeriodName().split(" ");
							suffixVal=StringUtil.getOrdinalSuffix(Integer.valueOf(periodName[1]));
							getSubjectPeriodsMap().put(String.valueOf(obj.getId()), periodName[1].concat(suffixVal));
							periodName=null;suffixVal=null;
						}
						obj=null;
					}
				}timeTableSettingsObj=null;
				if(!ObjectFunctions.isNullOrEmpty(getTimeTablePeriodList())){
					return getTimeTablePeriodList();
				}
			}
		}catch (Exception ex) {
			ex.printStackTrace();JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}return null;
	}
	/*public String ajaxGetStudyClassList() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxGetStudyClassList' method");
		}
		try {
			List<StudyClass> studyClassList = null;
			if(!StringFunctions.isNullOrEmpty(getClassId())){
				studyClassList = getAllStudyClassesByClassNameId(Long.valueOf(getClassId()));
				if(ObjectFunctions.isNotNullOrEmpty(studyClassList)){
					if(studyClassList.size()>1){
						Object[] classSubject = null;
						StudyClass studyClassExist = null;
						StudyClass studycls = null;
						for(Object obj: studyClassList){
							studycls = (StudyClass)obj;
							classSubject = adminManager.get("select studyClassId,subjectId from ClassSubject where subjectId="+getStudySubject().getId()+" and studyClassId="+studycls.getId());
							if(!ObjectFunctions.isNullOrEmpty(classSubject)){
								studyClassExist = getStudyClassById(Long.valueOf(classSubject[0].toString()));
								getObjectList().add(studyClassExist);
							}else {
								getStudyClassList().add(studycls);
							}
							studyClassExist = null;
							studycls = null;
						}
					}
					else{
						setStudyClass((StudyClass)adminManager.get(StudyClass.class,"classNameClassId="+getClassId()+" and status='"+EcmsConstants.YES_STRING+"'"));
					}
					setAnyId(getClassId());
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		return SUCCESS;
	}*/
	public Staff getStaffByUserId() {
		if (log.isInfoEnabled()) log.info("Entering 'getStaffByUserId' method");
		try{
			if(getUser().getId() > 0 ) {
				return (Staff) adminManager.get(Staff.class, "accountId="+getUser().getId());
			}
		}catch (Exception ex) {
			ex.printStackTrace();JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		return null;
	}
	public List<StudyClass> getStudyClasses(long academicYearId,long staffId){
		if (log.isInfoEnabled()) log.info("Entering 'getStudyClasses' method");
		try {
			List<StudyClass> studyClassList = null;
			if(staffId>0){
				studyClassList = adminManager.getAllHqlQuery("From StudyClass sc where sc.custId="+getUserCustId()+" and sc.academicYear="+academicYearId+" and sc.id in (select ct.studyClass from ClassTeacher ct where ct.staff="+staffId+")");
			}else{
				studyClassList = adminManager.getAll(StudyClass.class, "custId="+getUserCustId()+" and academicYearId="+academicYearId);
			}
			if(!ObjectFunctions.isNullOrEmpty(studyClassList)){
				return studyClassList;
			}
		} catch (Exception ex) {
			ex.printStackTrace();JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
	   	return null;
	}
	public TimeTableSettings getTimeTableSettingsById(long timeTableSettingsId) {
		if (log.isInfoEnabled()) log.info("Entering 'getTimeTableSettingsById' method");
		try{
			if(timeTableSettingsId > 0) {
				return (TimeTableSettings)adminManager.get(TimeTableSettings.class,"id="+timeTableSettingsId);
			}
		}catch (Exception ex) {
			ex.printStackTrace();JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		return null;
	}
	public StudyClass getStudyClassById(long studyClassId) {
		if (log.isInfoEnabled()) log.info("Entering 'getStudyClassById' method");
		try{
			if(studyClassId > 0) {
				return (StudyClass)adminManager.get(StudyClass.class,"id="+studyClassId);
			}
		}catch (Exception ex) {
			ex.printStackTrace();JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		return null;
	}
	public StudySubject getStudySubjectById(long studySubjectId) {
		if (log.isInfoEnabled()) log.info("Entering 'getStudySubjectById' method");
		try{
			if(studySubjectId > 0) {
				return (StudySubject)adminManager.get(StudySubject.class,"id="+studySubjectId);
			}
		}catch (Exception ex) {
			ex.printStackTrace();JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		return null;
	}
	public Date convertStringTimeToDate(String time) {
		if (log.isInfoEnabled()) log.info("Entering 'convertStringTimeToDate' method");
		Date date = null;
		try {
			date = new SimpleDateFormat("HH:mm").parse(time);
		} catch (Exception ex) {
			ex.printStackTrace();JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		return date;
	}
	
}