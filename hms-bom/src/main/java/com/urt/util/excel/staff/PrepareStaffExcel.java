/**
 * 
 */
package com.urt.util.excel.staff;

/**
 * @author sreeram
 */

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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
import com.urt.util.common.RayGunException;

public class PrepareStaffExcel {
	
	private static final Log log = LogFactory.getLog(PrepareStaffExcel.class);

    /** Always make sure the positions and query matches.  */

    private static final String[] titles = { "Staff Id", "Role", "First Name", "Last Name", "Initial","Date Of Birth (DD-MMM-YYYY)", "Gender",
    	"Marital Status","Experience","Date Of Joining (DD-MMM-YYYY)","Qualification","Salary","Email Id","Mobile Number","Address Line","City",
    	"State","Postal Code","Blood Group","PAN Number","GPF No","Office Phone No","Res .phone No","Designation","Religion","MOTHERTOUNG",
    	"Nationality","Community","Caste Name","Bank Name","Bank A/C Number","Bank Branch Name","Family Doctor","Preferred Hospital","Bio Metric Id",
    	"Staff Number","Aadhaar number","Passport Number","Ifsc Code","Employment Type","Staff Unique Number","Outside School Duty"};

    private static final Integer[] colwidths = { 7, 15, 17, 7, 8, 15,8, 15, 15, 15, 13, 15, 25, 20, 30, 15, 15, 13,
	    15, 15, 18, 18, 10, 10, 10, 15, 15, 15, 20, 35, 35, 12, 12, 12, 12, 10, 25, 10,30,15,15};

    public static final String query = "select staffId,roleDescription,firstName,lastName,'',dateOfBirth,gender,maritalStatus,experience,dateofJoining,qualification1,salary,email,mobileNumber,addressLine1," +
    		"city,stateName,postalCode,bloodGroup,panNumber,gpfNumber,officeNumber,phoneNumber,designation,religion,motherTounge,nationality,castName,subCastName,bankName,bankAccountNumber,bankBranchName,familyDoctor,prefferedHospital,bioMetricId," +
    		"staffNumber,aadharNumber,passportNumber,ifscCode,staffTypeStatus,staffUniqueNumber,outSideSchoolDuty from vw_staffDetails ";

    
    private int statepos = 16;
    private int stateEnd = 0;
    private int motherToungpos = 25;
    private int motherToungEnd = 0;
    private int rolesListPos = 1;
    private int rolesListEnd=0;
    private int cend = 0;
    private int send = 0;
    /*private int cpos = 5;
    private int spos = 6;
    private String refSheet = "";
    */
    public int getCend() {
		return cend;
	}

	public void setCend(int cend) {
		this.cend = cend;
	}

	public int getSend() {
		return send;
	}

	public void setSend(int send) {
		this.send = send;
	}


    private Map<String, CellStyle> styles;
	private Workbook wb;
    private String[] genders = {"M", "F"};
    private String[] outSideSchoolDutyArry = {"N", "Y"};
    private String[] bloodGroups = {"A+", "A-", "A1+", "A1-", "A1B+", "A1B-", "A2+", "A2-", "A2B+", "A2B-", "B+", "B-", "AB+","O+", "O-"};
    private String[] maritalStatus = new String[]{"M","UN"};
  //  private String[] dateOfBirth ={"dd/mm/yyyy"};
  //  private String[] dateOfJoining = {"dd/mm/yyyy"};
    private String[] staffTypeStatus = {"Temporary","Permanent"};
    
    public PrepareStaffExcel() {
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


    
    private DataValidation createStatesDropDownList(int srow, int erow) {
    	DataValidation dataValidation = null;
    	try {
    	    CellRangeAddressList addressList = new CellRangeAddressList(srow, erow, statepos, statepos);
    	    DVConstraint dvConstraint = DVConstraint.createFormulaListConstraint("Configurations!$B$4:$B$" + stateEnd);
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
    private DataValidation createMotherToungDropDownList(int srow, int erow) {
    	DataValidation dataValidation = null;
    	try {
    	    CellRangeAddressList addressList = new CellRangeAddressList(srow, erow, motherToungpos, motherToungpos);
    	    DVConstraint dvConstraint = DVConstraint.createFormulaListConstraint("Configurations!$E$5:$E$" + motherToungEnd);
    	    dataValidation = new HSSFDataValidation(addressList, dvConstraint);
    	    dataValidation.setSuppressDropDownArrow(false);
    	    dataValidation.createPromptBox("MotherToungs", "Select MotherToung from dropdown!");
    	    dataValidation.setShowPromptBox(true);
    	    dataValidation.setErrorStyle(DataValidation.ErrorStyle.STOP);
    	    dataValidation.createErrorBox("Invalid MotherToung", "Please select the MotherToung from the dropdown!");
    	} catch (Exception ex) {
    	    ex.printStackTrace();
    	}
    	return dataValidation;
     }
    private DataValidation createRolesDropDownList(int srow, int erow) {
    	DataValidation dataValidation = null;
    	try {
    	    CellRangeAddressList addressList = new CellRangeAddressList(srow, erow, rolesListPos, rolesListPos);
    	    DVConstraint dvConstraint = DVConstraint.createFormulaListConstraint("Configurations!$F$5:$F$" + rolesListEnd);
    	    dataValidation = new HSSFDataValidation(addressList, dvConstraint);
    	    dataValidation.setSuppressDropDownArrow(false);
    	    dataValidation.createPromptBox("Roles", "Select Role from dropdown!");
    	    dataValidation.setShowPromptBox(true);
    	    dataValidation.setErrorStyle(DataValidation.ErrorStyle.STOP);
    	    dataValidation.createErrorBox("Invalid Role", "Please select the role from the dropdown!");
    	} catch (Exception ex) {
    	    ex.printStackTrace();
    	}
    	return dataValidation;
     }
    
    private DataValidation createGendersDropDownList(int srow, int erow) {
    	DataValidation dataValidation = null;
    	try {
    	    CellRangeAddressList addressList = new CellRangeAddressList(srow, erow, 6,6);
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
   private DataValidation createMaritalStatusDropDownList(int srow, int erow) {
   	DataValidation dataValidation = null;
   	try {
   	    CellRangeAddressList addressList = new CellRangeAddressList(srow, erow, 7,7);
   	    DVConstraint dvConstraint = DVConstraint.createExplicitListConstraint(maritalStatus);
   	    dataValidation = new HSSFDataValidation(addressList, dvConstraint);
   	    dataValidation.setSuppressDropDownArrow(false);
   	    dataValidation.createPromptBox("Marital Status", "Select Marital Status from dropdown!\n(For 'Married' : 'M', 'Un-married' : 'UN')");
   	    dataValidation.setShowPromptBox(true);
   	    dataValidation.setErrorStyle(DataValidation.ErrorStyle.STOP);
   	    dataValidation.createErrorBox("Invalid Marital Status", "Please select the Marital Status from the dropdown!");
   	} catch (Exception ex) {
   	    ex.printStackTrace();
   	}
   	return dataValidation;
   }
    private DataValidation createBloodGroupList(int srow, int erow) {
    	DataValidation dataValidation = null;
    	try {
    	    CellRangeAddressList addressList = new CellRangeAddressList(srow, erow, 18, 18);
    	    DVConstraint dvConstraint = DVConstraint.createExplicitListConstraint(bloodGroups);
    	    dataValidation = new HSSFDataValidation(addressList, dvConstraint);
    	    dataValidation.setSuppressDropDownArrow(false);   
    	    dataValidation.createPromptBox("Blood Group", "Select bloodGroups  from dropdown!\n(For 'A+' : 'A+', 'A-' : 'A-', 'A1+' : 'A1+' , 'A1-' : 'A1-' ,'A1B+' : 'A1B+' e.t.c..)");
    	    dataValidation.setShowPromptBox(true);
    	    dataValidation.setErrorStyle(DataValidation.ErrorStyle.STOP);
    	    dataValidation.createErrorBox("Invalid Blood Group", "Please select the bloodGroups from the dropdown!");
    	} catch (Exception ex) {
    	    ex.printStackTrace();
    	}
    	return dataValidation;
    }
    private DataValidation createStaffTypeDropDownList(int srow, int erow) {
    	DataValidation dataValidation = null;
    	try {
    	    CellRangeAddressList addressList = new CellRangeAddressList(srow, erow, 39,39);
    	    DVConstraint dvConstraint = DVConstraint.createExplicitListConstraint(staffTypeStatus);
    	    dataValidation = new HSSFDataValidation(addressList, dvConstraint);
    	    dataValidation.setSuppressDropDownArrow(false);
    	    dataValidation.createPromptBox("Employment Type", "Select Employment Type from dropdown!\n(For 'Temporary' : 'Temporary', 'Permanent' : 'Permanent')");
    	    dataValidation.setShowPromptBox(true);
    	    dataValidation.setErrorStyle(DataValidation.ErrorStyle.STOP);
    	    dataValidation.createErrorBox("Invalid Employment Type", "Please select the Employment Type from the dropdown!");
    	} catch (Exception ex) {
    	    ex.printStackTrace();
    	}
    	return dataValidation;
    }
    
    private DataValidation createOutSideSchoolWorkDropDownList(int srow, int erow) {
    	DataValidation dataValidation = null;
    	try {
    	    CellRangeAddressList addressList = new CellRangeAddressList(srow, erow, 41,41);
    	    DVConstraint dvConstraint = DVConstraint.createExplicitListConstraint(outSideSchoolDutyArry);
    	    dataValidation = new HSSFDataValidation(addressList, dvConstraint);
    	    dataValidation.setSuppressDropDownArrow(false);
    	    dataValidation.createPromptBox("outSideSchoolDutyArry", "Select Out Side School Work from dropdown!\n(For 'No' : 'N', 'Yes' : 'Y')");
    	    dataValidation.setShowPromptBox(true);
    	    dataValidation.setErrorStyle(DataValidation.ErrorStyle.STOP);
    	    dataValidation.createErrorBox("Invalid Out Side School Duty Arry", "Please select the Out Side School Duty Arry from the dropdown!");
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
	    sheet.addValidationData(createRolesDropDownList(2, lastNumber));
	    sheet.addValidationData(createStatesDropDownList(2, lastNumber));
	    sheet.addValidationData(createMotherToungDropDownList(2, lastNumber));
	    sheet.addValidationData(createGendersDropDownList(2, lastNumber));
	    sheet.addValidationData(createMaritalStatusDropDownList(2, lastNumber));
	    sheet.addValidationData(createBloodGroupList(2, lastNumber));
	    sheet.addValidationData(createStaffTypeDropDownList(2, lastNumber));
	    sheet.addValidationData(createOutSideSchoolWorkDropDownList(2, lastNumber));
	    SheetConditionalFormatting sheetCF = sheet.getSheetConditionalFormatting();
	    ConditionalFormattingRule rule1 = sheetCF.createConditionalFormattingRule(ComparisonOperator.GT, "0");
	    PatternFormatting fill1 = rule1.createPatternFormatting();
	    fill1.setFillBackgroundColor(IndexedColors.GREY_40_PERCENT.index);
	    fill1.setFillPattern(PatternFormatting.SOLID_FOREGROUND);
	    CellRangeAddress[] regions = { CellRangeAddress.valueOf("A2:A" + lastNumber) };
	    sheetCF.addConditionalFormatting(regions, rule1);
	}
	if(wb.getSheetIndex("Configurations") != -1)
    	wb.setSheetHidden(wb.getSheetIndex("Configurations"), 1);
		wb.setActiveSheet(0);
    }

    private void createSheetHeader(Row headerRow) {
	try {
	    headerRow.setHeightInPoints(40);
	    Cell headerCell;
	    for (int i = 0; i < titles.length; i++) {
	    	if(i == 1 || i == 2){
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

    public void createStaffSheet(String sheetName, List<Object[]> staffs,String sheetTitleDesc) {

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
	    if (!ObjectFunctions.isNullOrEmpty(staffs)) {
		for (Object[] obj : staffs) {
		    row = sheet.createRow(rownum++);
		    writeStaffRow(obj, row);
		}
	    }
	    // finally set column widths, the width is measured in units of
	    // 1/256th of a character width
	    //Hide the student id because some this id is not required to show the user 
	    sheet.setColumnHidden(0, true);
	    sheet.setColumnHidden(4, true);
	    sheet.setColumnWidth(0, 30 * 256); // 30 characters wide
	    for (int i = 0; i < colwidths.length; i++) {
		sheet.setColumnWidth(i, colwidths[i] * 256);
	    }
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
    }

    private void writeStaffRow(Object[] object, Row row) {

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

    public void createConfigurationsSheet(String sheetName, List<State> states,List<MotherTongue> motherTongues,TreeMap<String,String> staffRoles,long academicYear, long custId) {
	try {
	    //refSheet = sheetName;
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

	    /*createSheetTitle(sheet.createRow(rownum++), "State Names");
	    row.setHeightInPoints(40);*/
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
	    	rownum=3;
		    row = sheet.getRow(rownum);
		    if(ObjectFunctions.isNullOrEmpty(row)){
		    	row = sheet.createRow(rownum);
		    }
		    cellnum=3;
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
	    if(!ObjectFunctions.isNullOrEmpty(staffRoles)){
	    	rownum=3;
		    row = sheet.getRow(rownum);
		    if(ObjectFunctions.isNullOrEmpty(row)){
		    	row = sheet.createRow(rownum);
		    }
		    cellnum=5;
		    headerCell = row.createCell(cellnum++);
		    headerCell.setCellValue("Role Name");
		    headerCell.setCellStyle(styles.get("header"));
		    rownum++;
		    for (Map.Entry<String,String> entry : staffRoles.entrySet()) {
			    cellnum = 5;
			    row = sheet.getRow(rownum);
			    if(ObjectFunctions.isNullOrEmpty(row)){
			    	row = sheet.createRow(rownum);
			    }
			    cell = row.createCell(cellnum++);
			    cell.setCellValue(entry.getValue());
			    rownum++;
			}
	    }
	    rolesListEnd = rownum;
	    cend = rownum;
	    send = rownum;
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
	    
	  //mandatory  columns to Staff Sheet
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
