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
import com.urt.persistence.model.study.ClassName;
import com.urt.persistence.model.study.SchoolCategory;
import com.urt.util.common.RayGunException;

public class PrepareStudentAdmissionExcel {
	
	private static final Log log = LogFactory.getLog(PrepareStudentAdmissionExcel.class);

    /** Always make sure the positions and query matches. Correct the Class and
     * Section positions. This is used to create a drop down list for classes
     * and sections */


    
    String[] titles = { "Applicationdetails Id","First Name", "Last Name", "Gender", "Class","Date Of Birth", "Blood Group", "Admitted Class Name",
			"Name Of The Last School", "Father Name","Father Occupation", "Father Qualification", "Designation",
			"Mother Name", "Mother Qualification", "Parent Email","Home Number", "Mobile Number", "Street Name", "City",
			"State", "Postal Code", "Religion", "Community","Caste Name", "Father Annual Income", 
			"Transfer Certificate No", "Transport Mode","Category", "Place Of Birth", "Student E-Mail", "Student Mobile No", "Residence Type" };
    
    
    
    private static final Integer[] colwidths = { 10,15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15,
	    15, 15, 15, 15, 15, 15, 15, 15, 15,15,18,25,25,25};

    public static final String query = "select applicationId,firstName,lastName,gender,className,dateOfBirth,bloodGroup,lastClassAttended,lastSchool,fatherName,occupation,fatherQualification,designation,motherName,"
	    + "motherQualification,parentEmail,phoneNumber,mobileNumber,streetName,city,state,postalCode,skillTypeName,castName,subCastName,annualIncome,transferCertificateNo,transportMode,categoryName,placeOfBirth,studentEmail,studentMobile,hostelMode from vw_onlineApplicationDetails ";

    private int cpos = 4;
    private int statepos = 20;
    private String refSheet = "";
    private int cend = 0;
    private int send = 0;
    private int stateEnd = 0;
    private int categorypos = 28;
    private int categoryEnd = 0;
    
    

    public String getRefSheet() {
		return refSheet;
	}

	public void setRefSheet(String refSheet) {
		this.refSheet = refSheet;
	}

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
    private String[] hostelModes = {"D", "H"};
    private String[] bloodGroups = {"a+", "a-", "a1+", "a1-", "a1b+", "a1b-", "a2+", "a2-", "a2b+", "a2b-", "b+", "b-", "ab+","o+", "o-"};
    
    public PrepareStudentAdmissionExcel() {
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

    private DataValidation createClassDropDownList(int srow, int erow) {

	DataValidation dataValidation = null;
	try {

	    CellRangeAddressList addressList = new CellRangeAddressList(srow, erow, cpos, cpos);
	    DVConstraint dvConstraint = DVConstraint.createFormulaListConstraint("ClassSections!$B$43:$B$" + cend);
	    dataValidation = new HSSFDataValidation(addressList, dvConstraint);
	    dataValidation.setSuppressDropDownArrow(false);
	    dataValidation.createPromptBox("Class", "Select from dropdown!");
	    dataValidation.setShowPromptBox(true);
	    dataValidation.setErrorStyle(DataValidation.ErrorStyle.STOP);
	    dataValidation.createErrorBox("Invalid Class", "Please select the class from the dropdown!");
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
	return dataValidation;

    }

 /*   private DataValidation createSectionDropDownList(int srow, int erow) {
	DataValidation dataValidation = null;
	try {

	    CellRangeAddressList addressList = new CellRangeAddressList(srow, erow, spos, spos);
	    DVConstraint dvConstraint = DVConstraint.createFormulaListConstraint("ClassSections!$D$43:$D$" + send);
	    dataValidation = new HSSFDataValidation(addressList, dvConstraint);
	    dataValidation.setSuppressDropDownArrow(false);
	    dataValidation.createPromptBox("Section", "Select Class Section from dropdown!");
	    dataValidation.setShowPromptBox(true);
	    dataValidation.setErrorStyle(DataValidation.ErrorStyle.STOP);
	    dataValidation.createErrorBox("Invalid Class Section", "Please select the class section from the dropdown!");
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
	return dataValidation;
    }*/
    
    private DataValidation createStatesDropDownList(int srow, int erow) {
    	DataValidation dataValidation = null;
    	try {
    	    CellRangeAddressList addressList = new CellRangeAddressList(srow, erow, statepos, statepos);
    	    DVConstraint dvConstraint = DVConstraint.createFormulaListConstraint("ClassSections!$B$5:$B$" + stateEnd);
    	    dataValidation = new HSSFDataValidation(addressList, dvConstraint);
    	    dataValidation.setSuppressDropDownArrow(false);
    	    dataValidation.createPromptBox("States", "Select States from dropdown!");
    	    dataValidation.setShowPromptBox(true);
    	    dataValidation.setErrorStyle(DataValidation.ErrorStyle.STOP);
    	    dataValidation.createErrorBox("Invalid States", "Please select the states from the dropdown!");
    	} catch (Exception ex) {
    	    ex.printStackTrace();
    	}
    	return dataValidation;
     }

    private DataValidation createTransportModesDropDownList(int srow, int erow) {
    	DataValidation dataValidation = null;
    	try {
    	    CellRangeAddressList addressList = new CellRangeAddressList(srow, erow, 27, 27);
    	    DVConstraint dvConstraint = DVConstraint.createExplicitListConstraint(transportModes);
    	    dataValidation = new HSSFDataValidation(addressList, dvConstraint);
    	    dataValidation.setSuppressDropDownArrow(false);
    	    dataValidation.createPromptBox("Transport Mode", "Select Transport Mode from dropdown!\n(For 'Own' : 'O', 'Private' : 'P', 'School Transport' : 'T')");
    	    dataValidation.setShowPromptBox(true);
    	    dataValidation.setErrorStyle(DataValidation.ErrorStyle.STOP);
    	    dataValidation
    		    .createErrorBox("Invalid Transport Mode", "Please select the transport mode from the dropdown!");
    	} catch (Exception ex) {
    	    ex.printStackTrace();
    	}
    	return dataValidation;
    }
    private DataValidation createCategoryDropDownList(int srow, int erow) {
    	DataValidation dataValidation = null;
    	try {
    	    CellRangeAddressList addressList = new CellRangeAddressList(srow, erow, categorypos, categorypos);
    	    DVConstraint dvConstraint = DVConstraint.createFormulaListConstraint("ClassSections!$B$47:$B$" + categoryEnd);
    	    dataValidation = new HSSFDataValidation(addressList, dvConstraint);
    	    dataValidation.setSuppressDropDownArrow(false);
    	    dataValidation.createPromptBox("SchoolCategory", "Select Select SchoolCategory from dropdown!");
    	    dataValidation.setShowPromptBox(true);
    	    dataValidation.setErrorStyle(DataValidation.ErrorStyle.STOP);
    	    dataValidation.createErrorBox("Invalid SchoolCategory", "Please select the Select SchoolCategory from the dropdown!");
    	} catch (Exception ex) {
    	    ex.printStackTrace();
    	}
    	return dataValidation;
     }
    
   private DataValidation createGendersDropDownList(int srow, int erow) {
    	DataValidation dataValidation = null;
    	try {
    	    CellRangeAddressList addressList = new CellRangeAddressList(srow, erow, 3,3);
    	    DVConstraint dvConstraint = DVConstraint.createExplicitListConstraint(genders);
    	    dataValidation = new HSSFDataValidation(addressList, dvConstraint);
    	    dataValidation.setSuppressDropDownArrow(false);
    	    dataValidation.createPromptBox("Gender", "Select Gender from dropdown!\n(For 'Male' : 'M', 'Female' : 'F')");
    	    dataValidation.setShowPromptBox(true);
    	    dataValidation.setErrorStyle(DataValidation.ErrorStyle.STOP);
    	    dataValidation.createErrorBox("Invalid Gender", "Please select the gender from the dropdown!");
    	} catch (Exception ex) {
    	    ex.printStackTrace();
    	}
    	return dataValidation;
    }
/*   private DataValidation createphIdsDropDownList(int srow, int erow) {
   	DataValidation dataValidation = null;
   	try {
   	    CellRangeAddressList addressList = new CellRangeAddressList(srow, erow, 45,45);
   	    DVConstraint dvConstraint = DVConstraint.createExplicitListConstraint(phIds);
   	    dataValidation = new HSSFDataValidation(addressList, dvConstraint);
   	    dataValidation.setSuppressDropDownArrow(false);
   	    dataValidation.createPromptBox("Physically Handicapped", "Select Physically Handicapped from dropdown!\n(For 'True' : 'Yes', 'False' : 'No')");
   	    dataValidation.setShowPromptBox(true);
   	    dataValidation.setErrorStyle(DataValidation.ErrorStyle.STOP);
   	    dataValidation
   		    .createErrorBox("Invalid Physically Handicapped", "Please select the phId from the dropdown!");
   	} catch (Exception ex) {
   	    ex.printStackTrace();
   	}
   	return dataValidation;
   }*/
   
   private DataValidation createHostelDropDownList(int srow, int erow) {
	   	DataValidation dataValidation = null;
	   	try {
	   	    CellRangeAddressList addressList = new CellRangeAddressList(srow, erow, 32,32);
	   	    DVConstraint dvConstraint = DVConstraint.createExplicitListConstraint(hostelModes);
	   	    dataValidation = new HSSFDataValidation(addressList, dvConstraint);
	   	    dataValidation.setSuppressDropDownArrow(false);
	   	    dataValidation.createPromptBox("Residence Type", "Select Residence Type from dropdown!\n(For 'Day Scholar' : 'D', 'Hostler' : 'H')");
	   	    dataValidation.setShowPromptBox(true);
	   	    dataValidation.setErrorStyle(DataValidation.ErrorStyle.STOP);
	   	    dataValidation
	   		    .createErrorBox("Invalid Residence Type", "Please select the Residence Type from the dropdown!");
	   	} catch (Exception ex) {
	   	    ex.printStackTrace();
	   	}
	   	return dataValidation;
	   }
   
    
    private DataValidation createBloodGroupList(int srow, int erow) {
    	DataValidation dataValidation = null;
    	try {
    	    CellRangeAddressList addressList = new CellRangeAddressList(srow, erow, 6, 6);
    	    DVConstraint dvConstraint = DVConstraint.createExplicitListConstraint(bloodGroups);
    	    dataValidation = new HSSFDataValidation(addressList, dvConstraint);
    	    dataValidation.setSuppressDropDownArrow(false);   
    	    dataValidation.createPromptBox("Blood Group", "Select bloodGroups  from dropdown!\n(For 'a+' : 'a+', 'a-' : 'a-', 'a1+' : 'a1+' , 'a1-' : 'a1-' ,'a1b+' : 'a1b+' ,'a1b-' : 'a1b-' ,'a2+' : 'a2+' ,'a2-' : 'a2-' ,'a2b+' : 'a2b+' ,'a2b-' : 'a2b-' ,'ab+' : 'ab+' ,'b+' : 'b+' ,'b-' : 'b-' ,'o+' : 'o+')");
    	    dataValidation.setShowPromptBox(true);
    	    dataValidation.setErrorStyle(DataValidation.ErrorStyle.STOP);
    	    dataValidation.createErrorBox("Invalid Blood Group", "Please select the bloodGroups from the dropdown!");
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

	wb.setSheetOrder(sheetname, wb.getNumberOfSheets()-1);
	
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
	    sheet.addValidationData(createClassDropDownList(2, lastNumber));
	    //sheet.addValidationData(createSectionDropDownList(2, lastNumber));
	    sheet.addValidationData(createStatesDropDownList(2, lastNumber));
	    sheet.addValidationData(createTransportModesDropDownList(2, lastNumber));
	    sheet.addValidationData(createGendersDropDownList(2, lastNumber));
	    //sheet.addValidationData(createphIdsDropDownList(2, lastNumber));
	    sheet.addValidationData(createBloodGroupList(2, lastNumber));
	    sheet.addValidationData(createHostelDropDownList(2, lastNumber));
	    sheet.addValidationData(createCategoryDropDownList(2,lastNumber));
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
    	    headerRow.setHeightInPoints(27);
    	    Cell headerCell;
    	    for (int i = 0; i < titles.length; i++) {
    	    	if(i == 1 || i == 4){
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
	    //Hide the application id because some this id is not required to show the user 
	    sheet.setColumnHidden(0, true);
	    // finally set column widths, the width is measured in units of
	    // 1/256th of a character width
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

    public void createClassSectionSheet(String sheetName, List<ClassName> classList,
	    List<State> states,long academicYear, long custId,String admissionsByFee,String applicationStatus,List<SchoolCategory> schoolCategory) {
	try {
	    refSheet = sheetName;
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

	    cell = row.createCell(2);
	    cell.setCellValue("Admission By Fee");
	    cell = row.createCell(3);
	    cell.setCellValue(admissionsByFee);
	    
	    cell = row.createCell(4);
	    cell.setCellValue("Applications Status");
	    cell = row.createCell(5);
	    cell.setCellValue(applicationStatus);
	    
	    
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
	    
	    createSheetTitle(sheet.createRow(rownum++), "School Name : Class and Section Names");
	    row = sheet.createRow(rownum++);
	    cellnum=0;
	    headerCell = row.createCell(cellnum++);
	    headerCell.setCellValue("Class Id");
	    headerCell.setCellStyle(styles.get("header"));
	    headerCell = row.createCell(cellnum++);
	    headerCell.setCellValue("Class Name");
	    headerCell.setCellStyle(styles.get("header"));
	    /*headerCell = row.createCell(cellnum++);
	    headerCell.setCellValue("Section Id");
	    headerCell.setCellStyle(styles.get("header"));
	    headerCell = row.createCell(cellnum++);
	    headerCell.setCellValue("Section Name");
	    headerCell.setCellStyle(styles.get("header"));*/

	    if (ObjectFunctions.isNotNullOrEmpty(classList)) {
		for (ClassName clazz : classList) {
		    cellnum = 0;
		    row = sheet.createRow(rownum++);
		    cell = row.createCell(cellnum++);
		    cell.setCellValue(clazz.getId());
		    cell = row.createCell(cellnum++);
		    cell.setCellValue(clazz.getClassName());
		    /*cell = row.createCell(cellnum++);
		    cell.setCellValue(obj.getClassSectionId());
		    cell = row.createCell(cellnum++);
		    cell.setCellValue(obj.getSection());*/
		}
	    }
	    cend = rownum;
	    send = rownum;
	 // Category Details
	    createSheetTitle(sheet.createRow(rownum++), "SchoolCategory Names");
	    row.setHeightInPoints(40);
	    Cell headerCell1;
	    int cellnum1 = 0;
	    row = sheet.createRow(rownum++);
	    headerCell1 = row.createCell(cellnum1++);
	    headerCell1.setCellValue("Category Id");
	    headerCell1.setCellStyle(styles.get("header"));
	    headerCell1 = row.createCell(cellnum1++);
	    headerCell1.setCellValue("Category Name");
	    headerCell1.setCellStyle(styles.get("header"));
	    if (ObjectFunctions.isNotNullOrEmpty(schoolCategory)) {
	    	for (SchoolCategory obj : schoolCategory) {
		    cellnum1 = 0;
		    row = sheet.createRow(rownum++);
		    cell = row.createCell(cellnum1++);
		    cell.setCellValue(obj.getId());
		    cell = row.createCell(cellnum1++);
		    cell.setCellValue(obj.getCategoryName());
		}
	    }
	    categoryEnd = rownum;
	} catch (Exception ex) {
		ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	}
    }

    /** Create a library of cell styles */
    private static Map<String, CellStyle> createStyles(Workbook wbl) {

	Map<String, CellStyle> styles = new HashMap<String, CellStyle>();
	try {
	    CellStyle style;
	    Font titleFont = wbl.createFont();
	    titleFont.setFontHeightInPoints((short) 18);
	    titleFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
	    style = wbl.createCellStyle();
	    style.setAlignment(CellStyle.ALIGN_CENTER);
	    style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
	    style.setFont(titleFont);
	    styles.put("title", style);

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

	    //MandatoryColumn to admission process
	    Font mandatoryColumnFont = wbl.createFont();
	    mandatoryColumnFont.setFontHeightInPoints((short) 11);
	    mandatoryColumnFont.setColor(IndexedColors.RED.getIndex());
	    style = wbl.createCellStyle();
	    style.setAlignment(CellStyle.ALIGN_CENTER);
	    style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
	    style.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
	    style.setFillPattern(CellStyle.SOLID_FOREGROUND);
	    style.setFont(mandatoryColumnFont);
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

	} catch (Exception ex) {
	    ex.printStackTrace();
	}

	return styles;
    }
}
