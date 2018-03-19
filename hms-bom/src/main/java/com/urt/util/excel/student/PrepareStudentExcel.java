/**
 * 
 */
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
import org.apache.poi.hssf.usermodel.DVConstraint;
import org.apache.poi.hssf.usermodel.HSSFDataValidation;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ComparisonOperator;
import org.apache.poi.ss.usermodel.ConditionalFormattingRule;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.PatternFormatting;
import org.apache.poi.ss.usermodel.PrintSetup;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.SheetConditionalFormatting;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellRangeAddressList;

import com.churchgroup.util.object.ObjectFunctions;
import com.urt.persistence.model.common.State;
import com.urt.persistence.model.exam.MotherTongue;
import com.urt.persistence.model.study.SchoolCategory;
import com.urt.persistence.model.study.ViewClassSectionDetails;
import com.urt.util.common.RayGunException;

public class PrepareStudentExcel {
	
	private static final Log log = LogFactory.getLog(PrepareStudentExcel.class);

    /** Always make sure the positions and query matches. Correct the Class and
     * Section positions. This is used to create a drop down list for classes
     * and sections */

    private static final String[] titles = { "Student Id", "Admission Number", "Roll Number","First Name", "Last Name", "Gender",
    	"Class & Section", "Date Of Joining (DD-MMM-YYYY)", "Date Of Birth (DD-MMM-YYYY)", "Admitted Class Name", "Father Name",
	    "Father Occupation", "Mother Name", "Mother Occupation", "Parent Email", "Home Number", "Mobile Number",
	    "Street Name", "City", "State", "Postal Code", "Religion", "Community", "Caste Name", "Nationality",
	    "Ration Card Number", "Community Number", "SSLC Number", "TMR Number", "Personal Identification 1",
	    "Personal Identification 2", "Father Annual Income", "Mother Tongue", "Blood Group", "Transport Mode",
	    "Family Doctor", "Preffered Hospital", "Height", "Vision(Left)","Vision(Right)", "Weight", "Teeth", "Oral Hygiene",
	    "Student Relieving Date","Register Number","Scholarship Information","Physically Handicapped", "Tc Applied Date (DD-MMM-YYYY)",
	    "Tc Issued Date (DD-MMM-YYYY)", "Category","Place Of Birth","Last School Name", "Student E-Mail","Student Mobile No","Residence Type","Is New Student (Y/N)"};

     private static final Integer[] colwidths = { 7, 12,12, 25, 7, 8, 16,15, 15, 15, 25, 20, 20, 20, 20, 15, 15, 25, 15,
	    15, 15, 10, 10, 10, 10, 15, 15, 15, 15, 40, 40, 13, 10, 10, 8, 13, 15, 8, 8, 8, 8, 8, 15, 15, 18,18,18,18,18,18,18,25,25,20,20};

    public static final String query = "select studentId, admissionNumber,rollNumber,firstName,lastName,gender,"
	    + "CONCAT(IF(className IS NULL, '', className),IF(section IS NULL || section <=> '','',CONCAT('-', section))) as classNameAndSection,dateOfJoining," 
	    + "dateOfBirth,classJoined,fatherName,fatherOccupation,motherName,"
	    + "motherOccupation,parentEmail,phoneNumber,mobileNumber,streetName,city,stateName,postalCode,"
	    + "religion,castName,subCastName,nationality,rationCardNumber,communityNumber,sslcNumber,tmrNumber,"
	    + "identification1,identification2,annualIncome,motherToung,bloodGroup,transportMode,familyDoctor,"
	    + "prefferedHospital,height,visionL,visionR,weight,teeth,oralHygiene,relievingDate,registerNumber,scholarShipInfo,phId,tcAppliedDate," 
	    + "tcIssuedDate,categoryName,placeOfBirth,lastSchool,studentEmail,studentMobile,hostelMode,joinedThroughAdmissions from vw_studentDetails ";

    private int cend = 0;
    private int send = 0;
    private int stateEnd = 0;
    private int motherToungEnd = 0;
    private int schoolCategoryEnd = 0;
    

    public int getSend() {
		return send;
	}

	public void setSend(int send) {
		this.send = send;
	}

	private Map<String, CellStyle> styles;
    private Workbook wb;
    private String[] transportModes = {"O", "P", "T" };
    private String[] genders = {"M", "F"};
    private String[] phIds = {"Yes", "No"};
    private String[] hostelModes = {"D", "H"};
    private String[] isNewStudent = {"Y", "N"};
    private String[] bloodGroups = {"a+", "a-", "a1+", "a1-", "a1b+", "a1b-", "a2+", "a2-", "a2b+", "a2b-", "b+", "b-", "ab+","ab-","o+", "o-"};
    
    public PrepareStudentExcel() {
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

    private DataValidation createDropDownList(int srow, int erow,int cpos,String listFormula,String promptBoxTitle,String promptBoxText,String errorBoxTitle,String errorBoxText){
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
    }
    
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

    public void finalPrep(String sheetname,List<Object[]> students) {

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
	    sheet.addValidationData(createDropDownList(2, lastNumber,32,"ClassSections!$E$5:$E$" + motherToungEnd,"Mother Tongue", "Select Mother Tongue from dropdown!","Invalid Mother Tongue", "Please select the Mother Tongue from the dropdown!"));
	    sheet.addValidationData(createDropDownListFromExplicitList(2, lastNumber,34,transportModes,"Transport Mode", "Select Transport Mode from dropdown!\n(For 'Own' : 'O', 'Private' : 'P', 'School Transport' : 'T')","Invalid Transport Mode", "Please select the transport mode from the dropdown!"));
	    sheet.addValidationData(createDropDownListFromExplicitList(2, lastNumber,53,hostelModes,"Residence Type", "Select Residence Type from dropdown!\n(For 'Day Scholar' : 'D', 'Hostler' : 'H')","Invalid Residence Type", "Please select the Residence Type from the dropdown!"));
	    sheet.addValidationData(createDropDownListFromExplicitList(2, lastNumber,54,isNewStudent,"Is New Student", "Select Student Type from dropdown!\n(For 'New Student' : 'Y', 'Old Student' : 'N')","Invalid Student Type", "Please select the Student Type from the dropdown!"));
	    sheet.addValidationData(createDropDownListFromExplicitList(2, lastNumber,5,genders,"Gender", "Select Gender from dropdown!\n(For 'Male' : 'M', 'Female' : 'F')","Invalid Gender", "Please select the gender from the dropdown!"));
	    sheet.addValidationData(createDropDownListFromExplicitList(2, lastNumber,45,phIds,"Physically Handicapped", "Select Physically Handicapped from dropdown!\n(For 'True' : 'Yes', 'False' : 'No')","Invalid Physically Handicapped", "Please select the phId from the dropdown!"));		
	    sheet.addValidationData(createDropDownListFromExplicitList(2, lastNumber,33,bloodGroups,"Blood Group", "Select bloodGroups  from dropdown!\n(For 'a+' : 'a+', 'a-' : 'a-', 'a1+' : 'a1+' , 'a1-' : 'a1-' ,'a1b+' : 'a1b+' ,'a1b-' : 'a1b-' ,'a2+' : 'a2+' ,'a2-' : 'a2-' ,'a2b+' : 'a2b+' ,'a2b-' : 'a2b-' ,'ab+' : 'ab+' ,'b+' : 'b+' ,'b-' : 'b-' ,'o+' : 'o+')",
	    		"Invalid Blood Group", "Please select the bloodGroups from the dropdown!"));
	    sheet.addValidationData(createDropDownList(2, lastNumber,48,"ClassSections!$I$5:$I$" + schoolCategoryEnd,"Category", "Select SchoolCategory from dropdown!","Invalid SchoolCategory", "Please select the Category from the dropdown!"));
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
    }

    private void createSheetHeader(Row headerRow) {
	try {
	    headerRow.setHeightInPoints(40);
	    Cell headerCell;
	    for (int i = 0; i < titles.length; i++) {
	    	if(i == 1 || i == 3 || i == 6){
	    		headerCell = headerRow.createCell(i);
	    		headerCell.setCellValue(titles[i]);
	    		headerCell.setCellStyle(styles.get("headers"));
		    }else{
				headerCell = headerRow.createCell(i);
				headerCell.setCellValue(titles[i]);
				headerCell.setCellStyle(styles.get("header"));
		    }	
	    }
	} catch (Exception ex) {
	    ex.printStackTrace();
	}

    }

    public void createStudentSheet(String sheetName, List<Object[]> students,String sheetTitleDesc) {

	try {

	    HSSFSheet sheet = (HSSFSheet) wb.createSheet(sheetName);
	    sheet.createFreezePane(5, 2);
	    log.debug("sheet name : " + sheetName);
	    PrintSetup printSetup = sheet.getPrintSetup();
	    printSetup.setLandscape(true);
	    sheet.setFitToPage(true);
	    sheet.setHorizontallyCenter(true);
	    int rownum = 0;
	    createSheetTitle(sheet.createRow(rownum++), sheetTitleDesc);
	    createSheetHeader(sheet.createRow(rownum++));
	    sheet.addMergedRegion(CellRangeAddress.valueOf("$A$1:$AQ$1"));
	    sheet.setAutoFilter(new CellRangeAddress(rownum - 1, rownum - 1, 0, titles.length - 1));

	    Row row;
	    if (!ObjectFunctions.isNullOrEmpty(students)) {
		for (Object[] obj : students) {
		    row = sheet.createRow(rownum++);
		    writeStudentRow(obj, row);
		}
	    }
	    // finally set column widths, the width is measured in units of
	    // 1/256th of a character width
	    //Hide the student id because some this id is not required to show the user 
	    sheet.setColumnHidden(0, true);
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

    private void writeStudentRow(Object[] object, Row row) {

	try {
	    int colnum = 0;
	    Cell cell;
	    if (!ObjectFunctions.isNullOrEmpty(object)) {
		for (Object obj : object) {
		    cell = row.createCell(colnum);
		    cell.setCellStyle(styles.get("string"));

		    if (ObjectFunctions.isNullOrEmpty(obj)) {
			cell.setCellValue("");
		    } else if (obj instanceof String || obj instanceof BigInteger || obj instanceof Integer) {
			cell.setCellValue(obj.toString());
		    } else if (obj instanceof Date || obj instanceof Timestamp) {
			cell.setCellValue((Timestamp) obj);
			cell.setCellStyle(styles.get("date"));
		    } else if (obj instanceof Double) {
			cell.setCellValue((Double) obj);
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

    public void createClassSectionSheet(String sheetName, List<ViewClassSectionDetails> clazzSection,
	    List<State> states,List<MotherTongue> motherTongues,long academicYear, long custId,List<SchoolCategory> schoolCategory) {
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
	 // Category Details
	    if (ObjectFunctions.isNotNullOrEmpty(schoolCategory)) {
	    	rownum=2;
		    row = sheet.getRow(rownum);
		    if(ObjectFunctions.isNullOrEmpty(row)){
		    	row = sheet.createRow(rownum);
		    }
		    createSheetTitle(row, "SchoolCategory Names");
		    row.setHeightInPoints(40);
		    cellnum=7;
		    row = sheet.getRow(++rownum);
		    if(ObjectFunctions.isNullOrEmpty(row)){
		    	row = sheet.createRow(rownum);
		    }
		    headerCell = row.createCell(cellnum++);
		    headerCell.setCellValue("SchoolCategory Id");
		    headerCell.setCellStyle(styles.get("header"));
		    headerCell = row.createCell(cellnum++);
		    headerCell.setCellValue("SchoolCategory Name");
		    headerCell.setCellStyle(styles.get("header"));
		    rownum++;
			for (SchoolCategory obj : schoolCategory) {
			    cellnum = 7;
			    row = sheet.getRow(rownum);
			    if(ObjectFunctions.isNullOrEmpty(row)){
			    	row = sheet.createRow(rownum);
			    }
			    cell = row.createCell(cellnum++);
			    cell.setCellValue(obj.getId());
			    cell = row.createCell(cellnum++);
			    cell.setCellValue(obj.getCategoryName());
			    rownum++;
			}
	    }
	    schoolCategoryEnd = rownum;
	} catch (Exception ex) {
		ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	}
    }

    /** Create a library of cell styles */
    public static Map<String, CellStyle> createStyles(Workbook wbl) {

	Map<String, CellStyle> styles = new HashMap<String, CellStyle>();
	try {
	    CellStyle style;
	    Font titleFont = wbl.createFont();
	    titleFont.setFontHeightInPoints((short) 18);
	    titleFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
	    style = wbl.createCellStyle();
	    style.setAlignment(CellStyle.ALIGN_CENTER);
	    style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
	    style.setWrapText(true);
	    style.setFont(titleFont);
	    styles.put("title", style);
	    
	    Font titleHeaderFont = wbl.createFont();
	    titleHeaderFont.setFontHeightInPoints((short) 12);
	    titleHeaderFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
	    style = wbl.createCellStyle();
	    style.setAlignment(CellStyle.ALIGN_CENTER);
	    style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
	    style.setWrapText(true);
	    style.setFont(titleHeaderFont);
	    styles.put("titleHeader", style);


	    Font monthFont = wbl.createFont();
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
	    styles.put("header", style);
	    
	  //mandatory Columns to Student sheet
	    Font mandatoryColumnsFont = wbl.createFont();
	    mandatoryColumnsFont.setFontHeightInPoints((short) 11);
	    mandatoryColumnsFont.setColor(IndexedColors.RED.getIndex());
	    style = wbl.createCellStyle();
	    style.setAlignment(CellStyle.ALIGN_CENTER);
	    style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
	    style.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
	    style.setFillPattern(CellStyle.SOLID_FOREGROUND);
	    style.setFont(mandatoryColumnsFont);
	    style.setWrapText(true);
	    style.setRightBorderColor(IndexedColors.WHITE.getIndex());
	    style.setBorderRight(CellStyle.BORDER_THIN);
	    style.setLeftBorderColor(IndexedColors.WHITE.getIndex());
	    style.setBorderLeft(CellStyle.BORDER_THIN);
	    style.setTopBorderColor(IndexedColors.WHITE.getIndex());
	    style.setBorderTop(CellStyle.BORDER_THIN);
	    style.setBottomBorderColor(IndexedColors.WHITE.getIndex());
	    style.setBorderBottom(CellStyle.BORDER_THIN);
	    styles.put("headers", style);

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
	    
	    titleFont = wbl.createFont();
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
	    styles.put("headerInfo", style);
	    
	    style = wbl.createCellStyle();
	    style.setRotation((short) 90);
	    style.setAlignment(CellStyle.ALIGN_CENTER);
	    style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
	    styles.put("textRotation",style);

	} catch (Exception ex) {
	    ex.printStackTrace();
	}

	return styles;
    }
}
