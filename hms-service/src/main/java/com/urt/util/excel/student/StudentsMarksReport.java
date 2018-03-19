package com.urt.util.excel.student;

/**
 * @author sreeram
 */

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.PrintSetup;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;

import com.churchgroup.util.object.ObjectFunctions;
import com.churchgroup.util.string.StringFunctions;
import com.urt.persistence.model.exam.ExamTypes;
import com.urt.persistence.model.study.StudyClass;
import com.urt.persistence.model.study.ViewStudentClassDetails;
import com.urt.service.manager.interfaces.admin.AdminManager;

public class StudentsMarksReport {
	
	private static final Log log = LogFactory.getLog(StudentsMarksReport.class);

	@Autowired
    public AdminManager adminManager;
	// AdminManager adminManager = (AdminManager)SpringContextAware.getBean("adminManager");
    private static final String[] titles = { "Student Id", "Admission Number", "Roll Number","First Name", "Last Name", "Gender",
    	"Class & Section", "Date Of Joining (DD-MMM-YYYY)", "Date Of Birth (DD-MMM-YYYY)", "Admitted Class Name", "Father Name",
	    "Father Occupation", "Mother Name", "Mother Occupation", "Parent Email", "Home Number", "Mobile Number",
	    "Street Name", "City", "State", "Postal Code", "Religion", "Community", "Caste Name", "Nationality",
	    "Ration Card Number", "Community Number", "SSLC Number", "TMR Number", "Personal Identification 1",
	    "Personal Identification 2", "Father Annual Income", "Mother Tongue", "Blood Group", "Transport Mode",
	    "Family Doctor", "Preffered Hospital", "Height", "VisionL","VisionR", "Weight", "Teeth", "Oral Hygiene","Student Relieving Date","Register Number","Scholarship Information","Physically Handicapped" };

    private static final Integer[] colwidths = { 7, 12,25, 7, 20};

    /*public static final String query = "select studentId, admissionNumber,rollNumber,firstName,lastName,gender,"
	    + "CONCAT(IF(className IS NULL, '', className),IF(section IS NULL || section <=> '','',CONCAT('-', section))) as classNameAndSection,dateOfJoining,dateOfBirth,classJoined,fatherName,fatherOccupation,motherName,"
	    + "motherOccupation,parentEmail,phoneNumber,mobileNumber,streetName,city,stateName,postalCode,"
	    + "religion,castName,subCastName,nationality,rationCardNumber,communityNumber,sslcNumber,tmrNumber,"
	    + "identification1,identification2,annualIncome,motherToung,bloodGroup,transportMode,familyDoctor,"
	    + "prefferedHospital,height,vision,weight,teeth,oralHygiene,relievingDate,registerNumber,scholarShipInfo,phId  from vw_studentDetails ";*/

    /*private int cend = 0;
    private int send = 0;
    private int stateEnd = 0;
    private int motherToungEnd = 0;*/

    private int rowNum=0;
    private Map<String, CellStyle> styles;
    private Workbook wb;
    private StudyClass classSection;
    private List<ExamTypes> examTypes;
    public StringBuffer studentsMarksQuery = new StringBuffer();
    
    /*private String[] transportModes = {"O", "P", "T" };
    private String[] genders = {"M", "F"};
    private String[] phIds = {"Yes", "No"};
    private String[] bloodGroups = {"a+", "a-", "a1+", "a1-", "a1b+", "a1b-", "a2+", "a2-", "a2b+", "a2b-", "b+", "b-", "ab+","o+", "o-"};*/
    
    /**
	 * @return the classSection
	 */
	public StudyClass getClassSection() {
		return classSection;
	}

	/**
	 * @param classSection the classSection to set
	 */
	public void setClassSection(StudyClass classSection) {
		this.classSection = classSection;
	}

	/**
	 * @return the examTypes
	 */
	public List<ExamTypes> getExamTypes() {
		return examTypes;
	}

	/**
	 * @param examTypes the examTypes to set
	 */
	public void setExamTypes(List<ExamTypes> examTypes) {
		this.examTypes = examTypes;
	}

	public StudentsMarksReport() {
	this.wb = new HSSFWorkbook();
	this.styles = createStyles(wb);
    }

    /** @return the wb */
    public Workbook getWb() {
	return this.wb;
    }

    /** @param wb
     *            the wb to set */
    public void setWb(Workbook wb) {
	this.wb = wb;
    }

    /*private DataValidation createDropDownList(int srow, int erow,int cpos,String listFormula,String promptBoxTitle,String promptBoxText,String errorBoxTitle,String errorBoxText){
    	DataValidation dataValidation = null;
    	try {
    	    CellRangeAddressList addressList = new CellRangeAddressList(srow, erow, cpos, cpos);
    	    DVConstraint dvConstraint = DVConstraint.createFormulaListConstraint(listFormula);
    	    dataValidation = new HSSFDataValidation(addressList, dvConstraint);
    	    dataValidation.setSuppressDropDownArrow(false);
    	    dataValidation.createPromptBox(promptBoxTitle, promptBoxText);
    	    dataValidation.setShowPromptBox(true);
    	    dataValidation.setErrorStyle(DataValidation.ErrorStyle.STOP);
    	    dataValidation.createErrorBox(errorBoxTitle, errorBoxText);
    	} catch (Exception ex) {
    	    ex.printStackTrace();
    	}
    	return dataValidation;
    }

    private DataValidation createDropDownListFromExplicitList(int srow, int erow,int cpos,String[] listValues,String promptBoxTitle,String promptBoxText,String errorBoxTitle,String errorBoxText) {
    	DataValidation dataValidation = null;
    	try {
    	    CellRangeAddressList addressList = new CellRangeAddressList(srow, erow, cpos, cpos);
    	    DVConstraint dvConstraint = DVConstraint.createExplicitListConstraint(listValues);
    	    dataValidation = new HSSFDataValidation(addressList, dvConstraint);
    	    dataValidation.setSuppressDropDownArrow(false);
    	    dataValidation.createPromptBox(promptBoxTitle, promptBoxText);
    	    dataValidation.setShowPromptBox(true);
    	    dataValidation.setErrorStyle(DataValidation.ErrorStyle.STOP);
    	    dataValidation.createErrorBox(errorBoxTitle, errorBoxText);
    	} catch (Exception ex) {
    	    ex.printStackTrace();
    	}
    	return dataValidation;
    }*/
    
    private void createSheetTitle(Row titleRow, String title) {
		try {
		    titleRow.setHeightInPoints(45);
		    Cell titleCell = titleRow.createCell(0);
		    titleCell.setCellValue(title);
		    titleCell.setCellStyle(styles.get("title"));
		} catch (Exception ex) {
		    ex.printStackTrace();
		}
    }

    /*public void finalPrep(String sheetname,List<Object[]> students) {

	wb.setSheetOrder(sheetname, wb.getNumberOfSheets() - 1);
	
	Sheet sheet;
	int nofsheets = wb.getNumberOfSheets();
	for (int i = 0; i < nofsheets; i++) {
	    sheet = wb.getSheetAt(i);
	    int lastNumber=0;
	    if (!ObjectFunctions.isNullOrEmpty(students)) {
	    	lastNumber=sheet.getLastRowNum();
	    }else {
	    	lastNumber=10000;
		}
	    sheet.addValidationData(createDropDownList(2, lastNumber, 6, "ClassSections!$G$5:$G$" + cend, "Class", "Select from dropdown!", "Invalid Class", "Please select the class from the dropdown!"));
	    sheet.addValidationData(createDropDownList(2, lastNumber,19,"ClassSections!$B$5:$B$" + stateEnd,"States", "Select States from dropdown!","Invalid States", "Please select the states from the dropdown!"));
	    sheet.addValidationData(createDropDownList(2, lastNumber,32,"ClassSections!$E$5:$E$" + motherToungEnd,"MotherToungs", "Select MotherToung from dropdown!","Invalid MotherToung", "Please select the MotherToung from the dropdown!"));
	    sheet.addValidationData(createDropDownListFromExplicitList(2, lastNumber,34,transportModes,"Transport Mode", "Select Transport Mode from dropdown!\n(For 'Own' : 'O', 'Private' : 'P', 'School Transport' : 'T')","Invalid Transport Mode", "Please select the transport mode from the dropdown!"));
	    sheet.addValidationData(createDropDownListFromExplicitList(2, lastNumber,5,genders,"Gender", "Select Gender from dropdown!\n(For 'Male' : 'M', 'Female' : 'F')","Invalid Gender", "Please select the gender from the dropdown!"));
	    sheet.addValidationData(createDropDownListFromExplicitList(2, lastNumber,45,phIds,"Physically Handicapped", "Select Physically Handicapped from dropdown!\n(For 'True' : 'Yes', 'False' : 'No')","Invalid Physically Handicapped", "Please select the phId from the dropdown!"));		
	    sheet.addValidationData(createDropDownListFromExplicitList(2, lastNumber,33,bloodGroups,"Blood Group", "Select bloodGroups  from dropdown!\n(For 'a+' : 'a+', 'a-' : 'a-', 'a1+' : 'a1+' , 'a1-' : 'a1-' ,'a1b+' : 'a1b+' ,'a1b-' : 'a1b-' ,'a2+' : 'a2+' ,'a2-' : 'a2-' ,'a2b+' : 'a2b+' ,'a2b-' : 'a2b-' ,'ab+' : 'ab+' ,'b+' : 'b+' ,'b-' : 'b-' ,'o+' : 'o+')",
	    		"Invalid Blood Group", "Please select the bloodGroups from the dropdown!"));
	    SheetConditionalFormatting sheetCF = sheet.getSheetConditionalFormatting();
	    ConditionalFormattingRule rule1 = sheetCF.createConditionalFormattingRule(ComparisonOperator.GT, "0");
	    PatternFormatting fill1 = rule1.createPatternFormatting();
	    fill1.setFillBackgroundColor(IndexedColors.GREY_40_PERCENT.index);
	    fill1.setFillPattern(PatternFormatting.SOLID_FOREGROUND);
	    CellRangeAddress[] regions = { CellRangeAddress.valueOf("A2:A" + lastNumber) };
	    sheetCF.addConditionalFormatting(regions, rule1);
	}
	if(wb.getSheetIndex("ClassSections") != -1)
    	wb.setSheetHidden(wb.getSheetIndex("ClassSections"), 1);
		wb.setActiveSheet(0);
    }*/
    /*select 
    ss.subjectName,
    ss.term5St2,
    ss.term5St3,
    ss.term5Total,
    ss.term5Grade,
    ss.term2St1,
    ss.term2St2,
    ss.term2Total,
    ss.term2Grade,
    ss.term3St1,
    ss.term3St2,
    ss.term3Total,
    ss.term3Grade,
    ROUND((IFNULL(ss.term5Total, 0) + IFNULL(ss.term2Total, 0) + IFNULL(ss.term3Total, 0)) / 3) as avgMarks,
    sg.gradeName
from
    vw_studentScoreCardMarksDetails ss
        LEFT JOIN
    schoolGrades sg ON (sg.academicYearId = ss.academicYearId and ROUND((IFNULL(ss.term1Total, 0) + IFNULL(ss.term2Total, 0) + IFNULL(ss.term3Total, 0)) / 3) >= sg.minMarks and ROUND((IFNULL(ss.term1Total, 0) + IFNULL(ss.term2Total, 0) + IFNULL(ss.term3Total, 0)) / 3) <= sg.maxMarks)
where
    studentId = 36375
order by ss.subjectSortingOrder;*/
    private int createSheetHeader(HSSFSheet sheet,int rownum) {
	try {
		Row header1Row = sheet.createRow(rownum++);
		Row header2Row = sheet.createRow(rownum++);
	    header1Row.setHeightInPoints(50);
	    header2Row.setHeightInPoints(30);
	    double examTotMarks = 0; 
	    Object[] subType = null;
	    Cell headerCell;
	    int i=0;
	    int scheduleExamtypesCount = 0;
	    StringBuffer avgMarks = new StringBuffer();
	    StringBuffer avgGradeQuery = new StringBuffer();
	    headerCell = header1Row.createCell(i);
	    headerCell.setCellValue("SERIAL NO.");
	    headerCell.setCellStyle(styles.get("headerTitleRotation"));
	    sheet.addMergedRegion(new CellRangeAddress(header1Row.getRowNum(),header2Row.getRowNum(),i,i));
	    headerCell = header1Row.createCell(++i);
	    headerCell.setCellValue("REGISTER NO.");
	    headerCell.setCellStyle(styles.get("headerTitleRotation"));
	    sheet.addMergedRegion(new CellRangeAddress(header1Row.getRowNum(),header2Row.getRowNum(),i,i));
	    headerCell = header1Row.createCell(++i);
	    headerCell.setCellValue("NAME");
	    headerCell.setCellStyle(styles.get("headerTitleRotation"));
	    sheet.addMergedRegion(new CellRangeAddress(header1Row.getRowNum(),header2Row.getRowNum(),i,i));
	    headerCell = header1Row.createCell(++i);
	    headerCell.setCellValue("COMMUNITY");
	    headerCell.setCellStyle(styles.get("headerTitleRotation"));
	    sheet.addMergedRegion(new CellRangeAddress(header1Row.getRowNum(),header2Row.getRowNum(),i,i));
	    headerCell = header1Row.createCell(++i);
	    headerCell.setCellValue("SUBJECTS");
	    headerCell.setCellStyle(styles.get("headerTitle"));
	    sheet.addMergedRegion(new CellRangeAddress(header1Row.getRowNum(),header2Row.getRowNum(),i,i));
	    studentsMarksQuery = new StringBuffer();
	    studentsMarksQuery.append("select ss.subjectName,");
	    avgMarks.append("ROUND((");
	    avgGradeQuery.append(" schoolGrades sg ON (sg.academicYearId = ss.academicYearId and ");
	    for (ExamTypes examtype : examTypes) {
	    	if(ObjectFunctions.isNotNullOrEmpty(examtype.getTempList())){
	    		headerCell = header1Row.createCell(++i);
				headerCell.setCellValue(examtype.getExamType());
				headerCell.setCellStyle(styles.get("headerTitle"));
				sheet.addMergedRegion(new CellRangeAddress(header1Row.getRowNum(),header1Row.getRowNum(),i,i+examtype.getTempList().size()+1));
				examTotMarks = 0;
				scheduleExamtypesCount++;
				for(Object subTypeObj : examtype.getTempList()){
					subType = (Object[])subTypeObj;
					if(!ObjectFunctions.isNullOrEmpty(subType[1]) && !ObjectFunctions.isNullOrEmpty(subType[2])){
						headerCell = header2Row.createCell(i++);
						headerCell.setCellValue(subType[1].toString()+"\n"+subType[2].toString());
						headerCell.setCellStyle(styles.get("headerTitle"));
						examTotMarks+=Double.valueOf(subType[2].toString());
					}
					studentsMarksQuery.append("ss.term").append(examtype.getSortingOrder()).append("St").append(subType[3].toString()).append(",");	
					subType = null;
					subTypeObj = null;
				}
				studentsMarksQuery.append("ss.term").append(examtype.getSortingOrder()).append("Total,");
				studentsMarksQuery.append("ss.term").append(examtype.getSortingOrder()).append("Grade,");
				avgMarks.append("IFNULL(ss.term").append(examtype.getSortingOrder()).append("Total, 0)+");
				headerCell = header2Row.createCell(i++);
				headerCell.setCellValue("Total\n"+examTotMarks);
				headerCell.setCellStyle(styles.get("headerTitle"));
				headerCell = header2Row.createCell(i);
				headerCell.setCellValue("Grade");
				headerCell.setCellStyle(styles.get("headerTitle"));
	    	}
	    }
	    avgMarks.deleteCharAt(avgMarks.length()-1);
	    avgMarks.append(") /").append(scheduleExamtypesCount).append(" )");
	    avgGradeQuery.append(avgMarks).append(" >= sg.minMarks and ");
	    avgGradeQuery.append(avgMarks).append("  <= sg.maxMarks )");
	    avgMarks.append(" as avgMarks,sg.gradeName,'','','','','COMPLETED' from vw_studentScoreCardMarksDetails ss LEFT JOIN ");
	    studentsMarksQuery.append(avgMarks).append(avgGradeQuery);
	    log.debug(studentsMarksQuery.toString());
	    headerCell = header1Row.createCell(++i);
	    headerCell.setCellValue("Over All TOTAL");
	    headerCell.setCellStyle(styles.get("headerTitle"));
	    sheet.addMergedRegion(new CellRangeAddress(header1Row.getRowNum(),header2Row.getRowNum(),i,i));
	    headerCell = header1Row.createCell(++i);
	    headerCell.setCellValue("Over All GRADE");
	    headerCell.setCellStyle(styles.get("headerTitle"));
	    sheet.addMergedRegion(new CellRangeAddress(header1Row.getRowNum(),header2Row.getRowNum(),i,i));
	    headerCell = header1Row.createCell(++i);
	    headerCell.setCellValue("P.E.T");
	    headerCell.setCellStyle(styles.get("headerTitle"));
	    sheet.addMergedRegion(new CellRangeAddress(header1Row.getRowNum(),header2Row.getRowNum(),i,i));
	    headerCell = header1Row.createCell(++i);
	    headerCell.setCellValue("E.V.S");
	    headerCell.setCellStyle(styles.get("headerTitle"));
	    sheet.addMergedRegion(new CellRangeAddress(header1Row.getRowNum(),header2Row.getRowNum(),i,i));
	    headerCell = header1Row.createCell(++i);
	    headerCell.setCellValue("ATTENDANCE");
	    headerCell.setCellStyle(styles.get("headerTitle"));
	    sheet.addMergedRegion(new CellRangeAddress(header1Row.getRowNum(),header1Row.getRowNum(),i,i+1));
	    headerCell = header2Row.createCell(i++);
		headerCell.setCellValue("TOTAL PRESENT DAYS");
		headerCell.setCellStyle(styles.get("headerTitle"));
		headerCell = header2Row.createCell(i++);
		headerCell.setCellValue("PERCENTAGE");
		headerCell.setCellStyle(styles.get("headerTitle"));
		headerCell = header1Row.createCell(i);
	    headerCell.setCellValue("REMARKS");
	    headerCell.setCellStyle(styles.get("headerTitle"));
	    sheet.addMergedRegion(new CellRangeAddress(header1Row.getRowNum(),header2Row.getRowNum(),i,i));
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
	return rownum;
    }

    public void createStudentMarksSheet(String sheetName,String sheetTitleDesc,List<ViewStudentClassDetails> students,String roundOffMarks) {

	try {

	    HSSFSheet sheet = (HSSFSheet) wb.createSheet(sheetName);
	    //sheet.createFreezePane(5, 2);
	    log.debug("sheet name : " + sheetName);
	    PrintSetup printSetup = sheet.getPrintSetup();
	    printSetup.setLandscape(true);
	    sheet.setFitToPage(true);
	    sheet.setHorizontallyCenter(true);
	    rowNum = 0;
	    createSheetTitle(sheet.createRow(rowNum++), "CONTINUOUS AND COMPREHENSIVE EVALUATION VI - VII - VIII SCHOLASTIC AREA CUMULATIVE MARKS AND GRADE SCHOLASTIC AREA");
	    createSheetTitle(sheet.createRow(rowNum++), sheetTitleDesc);
	    rowNum = createSheetHeader(sheet,rowNum);
	    sheet.addMergedRegion(CellRangeAddress.valueOf("$A$1:$X$1"));
	    sheet.addMergedRegion(CellRangeAddress.valueOf("$A$2:$X$2"));
	    Row row;
	    int col = 0;
	    Cell cell = null;
	    if (!ObjectFunctions.isNullOrEmpty(students)) {
	    	int sNo =1;
	    	List<Object[]> marks = null;
			for (ViewStudentClassDetails student : students) {
			    row = sheet.createRow(rowNum++);
			    col = 0;
			    marks = adminManager.getAll(studentsMarksQuery+" where studentId ="+ student.getStudId()+" order by ss.subjectSortingOrder");
			    if(ObjectFunctions.isNullOrEmpty(marks)){
			    	cell = row.createCell(col++);
			    	cell.setCellValue(sNo++);
			    	cell.setCellStyle(styles.get("string"));
			    	cell = row.createCell(col++);
			    	cell.setCellValue(student.getRegisterNumber());
			    	cell.setCellStyle(styles.get("string"));
			    	cell = row.createCell(col++);
			    	cell.setCellValue(student.getFullName());
			    	cell.setCellStyle(styles.get("string"));
			    	cell = row.createCell(col++);
			    	cell.setCellValue(student.getCastName());
			    	cell.setCellStyle(styles.get("string"));
			    }else{
			    	sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(),row.getRowNum()+marks.size()-1,col,col));
			    	cell = row.createCell(col++);
			    	cell.setCellValue(sNo++);
			    	cell.setCellStyle(styles.get("string"));
			    	sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(),row.getRowNum()+marks.size()-1,col,col));
			    	cell = row.createCell(col++);
			    	cell.setCellValue(student.getRegisterNumber());
			    	cell.setCellStyle(styles.get("string"));
			    	sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(),row.getRowNum()+marks.size()-1,col,col));
			    	cell = row.createCell(col++);
			    	cell.setCellValue(student.getFullName());
			    	cell.setCellStyle(styles.get("string"));
				    sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(),row.getRowNum()+marks.size()-1,col,col));
				    cell = row.createCell(col++);
				    cell.setCellValue(student.getCastName());
			    	for(Object[] obj : marks){
				    	writeStudentRow(obj, row,roundOffMarks);
				    	row = sheet.createRow(rowNum++);
				    }
			    	rowNum--;
			    }
			}
	    }
	    // finally set column widths, the width is measured in units of
	    // 1/256th of a character width
	    //Hide the student id because some this id is not required to show the user 
	    //sheet.setColumnHidden(0, true);
        //Hide the section column in 6th row because we keep class and section column in one row
	    //sheet.setColumnHidden(6, true);
	    sheet.setColumnWidth(0, 30 * 256); // 30 characters wide
	    for (int i = 0; i < colwidths.length; i++) {
	    	sheet.setColumnWidth(i, colwidths[i] * 256);
	    }
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
    }
    
    private void writeStudentRow(Object[] object, Row row,String roundOffMarks) {

	try {
	    int colnum = 4;
	    Cell cell;
	    if (!ObjectFunctions.isNullOrEmpty(object)) {
		for (Object obj : object) {
		    cell = row.createCell(colnum);
		    cell.setCellStyle(styles.get("marksString"));
		    if (ObjectFunctions.isNullOrEmpty(obj)) {
		    	cell.setCellValue("");
		    } else if (obj instanceof String || obj instanceof BigInteger || obj instanceof Integer) {
		    	if(obj.toString().matches("[0.0-9.0]+") &&  StringFunctions.isNotNullOrEmpty(roundOffMarks)){
		    		cell.setCellValue(Math.round(Double.valueOf(obj.toString())));
		    	}else
		    		cell.setCellValue((String) obj.toString());
		    } else if (obj instanceof Date || obj instanceof Timestamp) {
				cell.setCellValue((Timestamp) obj);
				cell.setCellStyle(styles.get("date"));
		    } else if (obj instanceof Double) {
		    	if(StringFunctions.isNullOrEmpty(roundOffMarks))
		    		cell.setCellValue((Double) obj);
		    	else
		    		cell.setCellValue(Math.round((Double) obj));
		    } else {
		    	cell.setCellValue(obj.toString());
		    }
		    colnum++;
		}
	  }

	} catch (Exception ex) {
	    ex.printStackTrace();
	}
    }

    /*public void createClassSectionSheet(String sheetName, List<ViewClassSectionDetails> clazzSection,
	    List<State> states,List<MotherTongue> motherTongues,long academicYear, long custId) {
	try {
	    HSSFSheet sheet = (HSSFSheet) wb.createSheet(sheetName);
	    Row row;
	    Cell cell;
	    int rownum = 0;
	    // Academic Year
	    row = sheet.createRow(rownum++);
	    cell = row.createCell(0);
	    cell.setCellValue("Academic Year");
	    cell = row.createCell(1);
	    cell.setCellValue(academicYear);

	    // Cust Id
	    row = sheet.createRow(rownum++);
	    cell = row.createCell(0);
	    cell.setCellValue("Cust Id");
	    cell = row.createCell(1);
	    cell.setCellValue(custId);

	    createSheetTitle(sheet.createRow(rownum++), "State Names");
	    row.setHeightInPoints(40);
	    Cell headerCell;
	    int cellnum = 0;
	    
	    row = sheet.createRow(rownum++);
	    headerCell = row.createCell(cellnum++);
	    headerCell.setCellValue("State Id");
	    headerCell.setCellStyle(styles.get("header"));
	    headerCell = row.createCell(cellnum++);
	    headerCell.setCellValue("State Name");
	    headerCell.setCellStyle(styles.get("header"));
	    headerCell = row.createCell(cellnum++);
	    headerCell.setCellValue("State Code");
	    headerCell.setCellStyle(styles.get("header"));
	    
	    if (ObjectFunctions.isNotNullOrEmpty(states)) {
		for (State obj : states) {
		    cellnum = 0;
		    row = sheet.createRow(rownum++);
		    cell = row.createCell(cellnum++);
		    cell.setCellValue(obj.getId());
		    cell = row.createCell(cellnum++);
		    cell.setCellValue(obj.getStateName());
		    cell = row.createCell(cellnum++);
		    cell.setCellValue(obj.getStateCode());
		}
	    }
	    stateEnd = rownum;
	    
	 // MotherToung Details
	    if (ObjectFunctions.isNotNullOrEmpty(motherTongues)) {
	    	rownum=2;
		    row = sheet.getRow(rownum);
		    if(ObjectFunctions.isNullOrEmpty(row)){
		    	row = sheet.createRow(rownum);
		    }
		    createSheetTitle(row, "MotherToung Names");
		    row.setHeightInPoints(40);
		    cellnum=3;
		    row = sheet.getRow(++rownum);
		    if(ObjectFunctions.isNullOrEmpty(row)){
		    	row = sheet.createRow(rownum);
		    }
		    headerCell = row.createCell(cellnum++);
		    headerCell.setCellValue("MotherToung Id");
		    headerCell.setCellStyle(styles.get("header"));
		    headerCell = row.createCell(cellnum++);
		    headerCell.setCellValue("MotherToung Name");
		    headerCell.setCellStyle(styles.get("header"));
		    rownum++;
			for (MotherTongue obj : motherTongues) {
			    cellnum = 3;
			    row = sheet.getRow(rownum);
			    if(ObjectFunctions.isNullOrEmpty(row)){
			    	row = sheet.createRow(rownum);
			    }
			    cell = row.createCell(cellnum++);
			    cell.setCellValue(obj.getId());
			    cell = row.createCell(cellnum++);
			    cell.setCellValue(obj.getName());
			    rownum++;
			}
	    }
	    motherToungEnd = rownum;
	    
	    //ClassSection Details
	    if (ObjectFunctions.isNotNullOrEmpty(clazzSection)) {
	    	rownum=2;
		    row = sheet.getRow(rownum);
		    if(ObjectFunctions.isNullOrEmpty(row)){
		    	row = sheet.createRow(rownum);
		    }
		    createSheetTitle(row, "School Name : Class and Section Names");
		    row.setHeightInPoints(40);
		    cellnum=5;
		    row = sheet.getRow(++rownum);
		    if(ObjectFunctions.isNullOrEmpty(row)){
		    	row = sheet.createRow(rownum);
		    }
		    headerCell = row.createCell(cellnum++);
		    headerCell.setCellValue("Class Section Id");
		    headerCell.setCellStyle(styles.get("header"));
		    headerCell = row.createCell(cellnum++);
		    headerCell.setCellValue("Class Name & Section");
		    headerCell.setCellStyle(styles.get("header"));
		    rownum++;
			for (ViewClassSectionDetails obj : clazzSection) {
			    cellnum = 5;
			    row = sheet.getRow(rownum);
			    if(ObjectFunctions.isNullOrEmpty(row)){
			    	row = sheet.createRow(rownum);
			    }
			    cell = row.createCell(cellnum++);
			    cell.setCellValue(obj.getClassSectionId());
			    cell = row.createCell(cellnum++);
			    if(!ObjectFunctions.isNullOrEmpty(obj.getSection())){
			    	cell.setCellValue(obj.getClassName()+"-"+obj.getSection());
			    }else {
			    	cell.setCellValue(obj.getClassName());
				}
			    rownum++;
			}
	    }
	    cend = rownum;
	    send = rownum;
	} catch (Exception ex) {
	}
    }*/

    /** Create a library of cell styles */
    public static Map<String, CellStyle> createStyles(Workbook wbl) {

	Map<String, CellStyle> styles = new HashMap<String, CellStyle>();
	try {
	    CellStyle style;
	    Font titleFont = wbl.createFont();
	    titleFont.setFontHeightInPoints((short) 12);
	    titleFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
	    style = wbl.createCellStyle();
	    style.setAlignment(CellStyle.ALIGN_CENTER);
	    style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
	    style.setWrapText(true);
	    style.setFont(titleFont);
	    styles.put("title", style);

	  /*  Font monthFont = wbl.createFont();
	    monthFont.setFontHeightInPoints((short) 11);
	    monthFont.setColor(IndexedColors.WHITE.getIndex());
	    style = wbl.createCellStyle();
	    style.setAlignment(CellStyle.ALIGN_CENTER);
	    style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
	    style.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
	    style.setFillPattern(CellStyle.SOLID_FOREGROUND);
	    style.setFont(monthFont);
	    style.setWrapText(true);
	    style.setRightBorderColor(IndexedColors.WHITE.getIndex());
	    style.setBorderRight(CellStyle.BORDER_THIN);
	    style.setLeftBorderColor(IndexedColors.WHITE.getIndex());
	    style.setBorderLeft(CellStyle.BORDER_THIN);
	    style.setTopBorderColor(IndexedColors.WHITE.getIndex());
	    style.setBorderTop(CellStyle.BORDER_THIN);
	    style.setBottomBorderColor(IndexedColors.WHITE.getIndex());
	    style.setBorderBottom(CellStyle.BORDER_THIN);
	    styles.put("header", style);*/

	    style = wbl.createCellStyle();
	    style.setAlignment(CellStyle.ALIGN_CENTER);
	    style.setWrapText(true);
	    style.setBorderRight(CellStyle.BORDER_THIN);
	    style.setRightBorderColor(IndexedColors.BLACK.getIndex());
	    style.setBorderLeft(CellStyle.BORDER_THIN);
	    style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
	    style.setBorderTop(CellStyle.BORDER_THIN);
	    style.setTopBorderColor(IndexedColors.BLACK.getIndex());
	    style.setBorderBottom(CellStyle.BORDER_THIN);
	    style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
	    styles.put("cell", style);

	    style = wbl.createCellStyle();
	    style.setWrapText(true);
	    style.setBorderRight(CellStyle.BORDER_THIN);
	    style.setRightBorderColor(IndexedColors.BLACK.getIndex());
	    style.setBorderLeft(CellStyle.BORDER_THIN);
	    style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
	    style.setBorderTop(CellStyle.BORDER_THIN);
	    style.setTopBorderColor(IndexedColors.BLACK.getIndex());
	    style.setBorderBottom(CellStyle.BORDER_THIN);
	    style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
	    styles.put("string", style);
	    
	    CellStyle style1 = wbl.createCellStyle();
	    style1.cloneStyleFrom(style);
	    style1.setAlignment(CellStyle.ALIGN_CENTER);
	    styles.put("marksString", style1);

	    CreationHelper createHelper = wbl.getCreationHelper();
	    style = wbl.createCellStyle();
	    style.setDataFormat(createHelper.createDataFormat().getFormat("dd-MMM-yyyy"));
	    style.setAlignment(CellStyle.ALIGN_CENTER);
	    style.setWrapText(true);
	    style.setBorderRight(CellStyle.BORDER_THIN);
	    style.setRightBorderColor(IndexedColors.BLACK.getIndex());
	    style.setBorderLeft(CellStyle.BORDER_THIN);
	    style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
	    style.setBorderTop(CellStyle.BORDER_THIN);
	    style.setTopBorderColor(IndexedColors.BLACK.getIndex());
	    style.setBorderBottom(CellStyle.BORDER_THIN);
	    style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
	    styles.put("date", style);
	    
	    titleFont = wbl.createFont();
	    titleFont.setFontHeightInPoints((short) 9);
	    style = wbl.createCellStyle();
	    style.setAlignment(CellStyle.ALIGN_LEFT);
	    style.setVerticalAlignment(CellStyle.ALIGN_LEFT);
	    style.setWrapText(true);
	    style.setFont(titleFont);
	    styles.put("subtitle", style);
	    
	   /* titleFont = wbl.createFont();
	    titleFont.setFontHeightInPoints((short) 11);
	    titleFont.setColor(IndexedColors.WHITE.getIndex());
	    style = wbl.createCellStyle();
	    style.setAlignment(CellStyle.ALIGN_LEFT);
	    style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
	    style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
	    style.setFillPattern(CellStyle.SOLID_FOREGROUND);
	    style.setFont(titleFont);
	    style.setWrapText(true);
		style.setRightBorderColor(IndexedColors.BLACK.getIndex());
	   	style.setBorderRight(CellStyle.BORDER_THIN);
	    style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
	    style.setBorderLeft(CellStyle.BORDER_THIN);
	    style.setTopBorderColor(IndexedColors.BLACK.getIndex());
	    style.setBorderTop(CellStyle.BORDER_THIN);
	    style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
	    style.setBorderBottom(CellStyle.BORDER_THIN);
	    styles.put("headerInfo", style);*/
	    
	    titleFont = wbl.createFont();
	    titleFont.setFontHeightInPoints((short) 11);
	    titleFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
	    style = wbl.createCellStyle();
	    style.setFont(titleFont);
	    style.setWrapText(true);
	    //style.setRotation((short) 90);
	    style.setAlignment(CellStyle.ALIGN_CENTER);
	    style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
	    style.setRightBorderColor(IndexedColors.BLACK.getIndex());
	   	style.setBorderRight(CellStyle.BORDER_THIN);
	    style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
	    style.setBorderLeft(CellStyle.BORDER_THIN);
	    style.setTopBorderColor(IndexedColors.BLACK.getIndex());
	    style.setBorderTop(CellStyle.BORDER_THIN);
	    style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
	    style.setBorderBottom(CellStyle.BORDER_THIN);
	    styles.put("headerTitle",style);
	    style1 = wbl.createCellStyle();
	    style1.cloneStyleFrom(style);
	    style1.setRotation((short) 90);
	    styles.put("headerTitleRotation",style1);
	    
	} catch (Exception ex) {
	    ex.printStackTrace();
	}

	return styles;
    }

	public static String[] getTitles() {
		return titles;
	}
}
