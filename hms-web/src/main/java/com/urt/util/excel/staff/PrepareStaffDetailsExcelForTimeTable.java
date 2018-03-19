/**
 * 
 */
package com.urt.util.excel.staff;

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
import com.urt.persistence.model.exam.WeekDays;
import com.urt.persistence.model.study.StudySubject;
import com.urt.persistence.model.study.ViewStaffPersonAccountDetails;
import com.urt.webapp.action.jrexception.JRExceptionClient;

public class PrepareStaffDetailsExcelForTimeTable {
	
	private static final Log log = LogFactory.getLog(PrepareStaffDetailsExcelForTimeTable.class);

    /** Always make sure the positions and query matches. Correct the Class and
     * Section positions. This is used to create a drop down list for classes
     * and sections */
	
	 
	 private static final String[] titles = {  "Subject Name", "Staff Name", "No of Day1 Periods", "No of Day2 Periods", "No of Day3 Periods", 
		 "No of Day4 Periods", "No of Day5 Periods", "No of Day6 Periods", "Total No of Periods"};
	 
	/*private static final Integer[] colwidths = { 17, 12, 15, 7, 8, 10, 8, 15, 15, 15, 12, 20, 12, 20, 20, 15, 15, 40,
		    15, 15, 10, 7, 10, 10, 10, 15, 15, 15, 15, 40, 40, 10, 10, 8, 8, 10, 25, 8, 8, 8, 8, 8, 15, 13,20};*/
	private static final Integer[] colwidths = { 25, 25, 20, 20, 20, 20, 20, 20, 20};

	 
   // private int subpos = 1;
  //  private int staffpos = 2;
    private String refSheet = "";
    private int cend = 0;
    private int dayend = 0;
    private int staffend=0;
    private int hoursend=0;
    
    

    public String getRefSheet() {
		return refSheet;
	}

	public void setRefSheet(String refSheet) {
		this.refSheet = refSheet;
	}

	public int getHoursend() {
		return hoursend;
	}

	public void setHoursend(int hoursend) {
		this.hoursend = hoursend;
	}
	public int getDayend() {
		return dayend;
	}

	public void setDayend(int dayend) {
		this.dayend = dayend;
	}


	private Map<String, CellStyle> styles;
    private Workbook wb;
    public PrepareStaffDetailsExcelForTimeTable() {
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
    
  
      private void createSheetTitle(Row titleRow, String title) {
    	try {
    	    titleRow.setHeightInPoints(45);
    	    Cell titleCell = titleRow.createCell(0);
    	    titleCell.setCellValue(title);
    	    titleCell.setCellStyle(styles.get("title"));
    	} catch (Exception ex) {
    	    ex.printStackTrace();
    	    JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
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
	    JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
	}

	return styles;
    }
    
          
        private void writeSubjectsRow(Object[] object, Row row) {
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
        			cell.setCellValue((String) obj.toString());
        		    } else if (obj instanceof Date || obj instanceof Timestamp) {
        			cell.setCellValue((Timestamp) obj);
        			cell.setCellStyle(styles.get("date"));
        		    } else if (obj instanceof Double) {
        			cell.setCellValue((Double) obj);
        		    } else {
        			cell.setCellValue(obj.toString());
        		    //cell.setCellValue("");
        		    }
        		  
        		    colnum++;
        		}
        		 row.createCell(8).setCellFormula("SUM(C"+(row.getRowNum()+1)+":H"+(row.getRowNum()+1)+")");
        	    }

        	} catch (Exception ex) {
        	    ex.printStackTrace();
        	    JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
        	}
            }
           
        public void createTimeTableSheet(String sheetName, List<StudySubject> subjects, String sheetTitleDesc) {

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
        	    sheet.addMergedRegion(CellRangeAddress.valueOf("$A$1:$K$1"));
        	    sheet.setAutoFilter(new CellRangeAddress(rownum - 1, rownum - 1, 0, titles.length - 1));
        	    

        	    Row row;
        	   /* if (!ObjectFunctions.isNullOrEmpty(students)) {
        		for (Object[] obj : students) {
        		    row = sheet.createRow(rownum++);
        		    writeStudentRow(obj, row);
        		}
        	    }*/
        	    if (!ObjectFunctions.isNullOrEmpty(subjects)) {
    	    		for (StudySubject obj : subjects) {
    	    		    row = sheet.createRow(rownum++);
    	    		    Object[] objArray = { obj.getName() };
    	    		    //writeSubjectsRow(obj, row);
    	    		    writeSubjectsRow(objArray, row);
    	    		}
        	    }
        	    // finally set column widths, the width is measured in units of
        	    // 1/256th of a character width
        	    sheet.setColumnWidth(0, 30 * 256); // 30 characters wide
        	    for (int i = 0; i < colwidths.length; i++) {
        		sheet.setColumnWidth(i, colwidths[i] * 256);
        	    }
        	} catch (Exception ex) {
        	    ex.printStackTrace();
        	    JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
        	}
            }
        private void createSheetHeader(Row headerRow) {
        	try {
        	    headerRow.setHeightInPoints(40);
        	    Cell headerCell;
        	    for (int i = 0; i < titles.length; i++) {
        		headerCell = headerRow.createCell(i);
        		headerCell.setCellValue(titles[i]);
        		headerCell.setCellStyle(styles.get("header"));
        	    }
        	} catch (Exception ex) {
        	    ex.printStackTrace();
        	    JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
        	}

            }
        
        public void finalPrep(String sheetname) {

        	wb.setSheetOrder(sheetname, wb.getNumberOfSheets() - 1);
        	Sheet sheet;
        	int nofsheets = wb.getNumberOfSheets();
        	for (int i = 0; i < nofsheets-4; i++) {
        	    sheet = wb.getSheetAt(i);
        	    sheet.addValidationData(createSubjectsDropDownList(2, sheet.getLastRowNum()*3));
        	    //sheet.addValidationData(createSectionDropDownList(2, sheet.getLastRowNum()));
        	    //sheet.addValidationData(createTransportModesDropDownList(2, sheet.getLastRowNum()));
        	    //sheet.addValidationData(createGendersDropDownList(2, sheet.getLastRowNum()));
        	    sheet.addValidationData(createAllStaffDropDownList(2, sheet.getLastRowNum()*3));
        	   // sheet.addValidationData(createAllDaysDropDownList(2, sheet.getLastRowNum()*3));
        	    SheetConditionalFormatting sheetCF = sheet.getSheetConditionalFormatting();
        	    ConditionalFormattingRule rule1 = sheetCF.createConditionalFormattingRule(ComparisonOperator.GT, "0");
        	    PatternFormatting fill1 = rule1.createPatternFormatting();
        	    //fill1.setFillBackgroundColor(IndexedColors.GREY_40_PERCENT.index);
        	    fill1.setFillPattern(PatternFormatting.SOLID_FOREGROUND);
        	    CellRangeAddress[] regions = { CellRangeAddress.valueOf("A2:A" + sheet.getLastRowNum()) };
        	    sheetCF.addConditionalFormatting(regions, rule1);
        	}
        	wb.setActiveSheet(0);

            }

        private DataValidation createSubjectsDropDownList(int srow, int erow) {

        	DataValidation dataValidation = null;
        	try {

        	    //CellRangeAddressList addressList = new CellRangeAddressList(srow, erow, cpos, cpos);
        		CellRangeAddressList addressList = new CellRangeAddressList(srow, erow, 0, 0);
        	    DVConstraint dvConstraint = DVConstraint.createFormulaListConstraint("ClassSubjects!$B$5:$B$" + cend);
        	    dataValidation = new HSSFDataValidation(addressList, dvConstraint);
        	    dataValidation.setSuppressDropDownArrow(false);
        	    dataValidation.createPromptBox("Subject", "Select from dropdown!");
        	    dataValidation.setShowPromptBox(true);
        	    dataValidation.setErrorStyle(DataValidation.ErrorStyle.STOP);
        	    dataValidation.createErrorBox("Invalid Class", "Please select the subject from the dropdown!");
        	} catch (Exception ex) {
        	    ex.printStackTrace();
        	    JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
        	}
        	return dataValidation;

            }
        private DataValidation createAllStaffDropDownList(int srow, int erow) {

        	DataValidation dataValidation = null;
        	try {

        	    //CellRangeAddressList addressList = new CellRangeAddressList(srow, erow, cpos, cpos);
        		CellRangeAddressList addressList = new CellRangeAddressList(srow, erow, 1, 1);
        	    DVConstraint dvConstraint = DVConstraint.createFormulaListConstraint("ClassStaff!$B$5:$B$" + staffend);
        	    dataValidation = new HSSFDataValidation(addressList, dvConstraint);
        	    dataValidation.setSuppressDropDownArrow(false);
        	    dataValidation.createPromptBox("Staff", "Select from dropdown!");
        	    dataValidation.setShowPromptBox(true);
        	    dataValidation.setErrorStyle(DataValidation.ErrorStyle.STOP);
        	    dataValidation.createErrorBox("Invalid Class", "Please select the staff from the dropdown!");
        	} catch (Exception ex) {
        	    ex.printStackTrace();
        	    JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
        	}
        	return dataValidation;

            }
        
                   
    public void createClassSubjectsSheet(String sheetName, List<StudySubject> studySubjects ,long academicYear, long custId) {
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

            	    // Cust Id
            	    row = sheet.createRow(rownum++);
            	    cell = row.createCell(0);
            	    cell.setCellValue("Cust Id");
            	    cell = row.createCell(1);
            	    cell.setCellValue(custId);

            	    createSheetTitle(sheet.createRow(rownum++), "Subject Names");
            	    // sheet.addMergedRegion(CellRangeAddress.valueOf("$A$1:$AQ$1"));

            	    // Create Header
            	    row.setHeightInPoints(40);
            	    Cell headerCell;
            	    int cellnum = 0;

            	    row = sheet.createRow(rownum++);
            	    headerCell = row.createCell(cellnum++);
            	    headerCell.setCellValue("Subject Id");
            	    headerCell.setCellStyle(styles.get("header"));
            	    headerCell = row.createCell(cellnum++);
            	    headerCell.setCellValue("Subeject Name");
            	    headerCell.setCellStyle(styles.get("header"));
            	    headerCell = row.createCell(cellnum++);
            	   // headerCell.setCellValue("Section Id");
            	   // headerCell.setCellStyle(styles.get("header"));
            	   // headerCell = row.createCell(cellnum++);
            	    headerCell.setCellValue("Subject Description");
            	    headerCell.setCellStyle(styles.get("header"));

            	    if (ObjectFunctions.isNotNullOrEmpty(studySubjects)) {

            		for (StudySubject obj : studySubjects) {
            		    cellnum = 0;
            		    row = sheet.createRow(rownum++);
            		    cell = row.createCell(cellnum++);
            		    cell.setCellValue(obj.getId());
            		    cell = row.createCell(cellnum++);
            		    cell.setCellValue(obj.getName());
            		   // cell = row.createCell(cellnum++);
            		   // cell.setCellValue(obj.getSubjectNumber());
            		    cell = row.createCell(cellnum++);
            		    cell.setCellValue(obj.getDescription());
            		}
            	    }
            	    cend = rownum;
            	   // send = rownum;
            	    
            	   
            	    
            	} catch (Exception ex) {
            		ex.printStackTrace();
            		JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
            	}
                }
    
    public void createClassStaffSheet(String sheetName, List<ViewStaffPersonAccountDetails> staffList, List<WeekDays> weekDaysList,
    	    long academicYear, long custId) {
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

    	    // Cust Id
    	    row = sheet.createRow(rownum++);
    	    cell = row.createCell(0);
    	    cell.setCellValue("Cust Id");
    	    cell = row.createCell(1);
    	    cell.setCellValue(custId);
   
    	     	    
    	   //Adding Days Names 
    	    
    	    /*createSheetTitle(sheet.createRow(rownum++), "Day Names");
    	    // sheet.addMergedRegion(CellRangeAddress.valueOf("$A$1:$AQ$1"));

    	    // Create Header
    	    row.setHeightInPoints(40);
    	    Cell headerCellDays;
    	    int cellnumDays = 0;

    	    row = sheet.createRow(rownum++);
    	    headerCellDays = row.createCell(cellnumDays++);
    	    headerCellDays.setCellValue("Day Id");
    	    headerCellDays.setCellStyle(styles.get("header"));
    	    headerCellDays = row.createCell(cellnumDays++);
    	    headerCellDays.setCellValue("Day Name");
    	    headerCellDays.setCellStyle(styles.get("header"));
    	    headerCellDays = row.createCell(cellnumDays++);
    	   // headerCell.setCellValue("Section Id");
    	   // headerCell.setCellStyle(styles.get("header"));
    	   // headerCell = row.createCell(cellnum++);
    	   // headerCellDays.setCellValue("Subject Description");
    	   // headerCellDays.setCellStyle(styles.get("header"));

    	    if (ObjectFunctions.isNotNullOrEmpty(weekDaysList)) {

    		for (WeekDays obj : weekDaysList) {
    			cellnumDays = 0;
    		    row = sheet.createRow(rownum++);
    		    cell = row.createCell(cellnumDays++);
    		    cell.setCellValue(obj.getId());
    		    cell = row.createCell(cellnumDays++);
    		    cell.setCellValue(obj.getDayName());
    		   // cell = row.createCell(cellnum++);
    		   // cell.setCellValue(obj.getSubjectNumber());
    		    //cell = row.createCell(cellnumDays++);
    		    //cell.setCellValue(obj.getDescription());
    		}
    	    }
    	    //cend = rownum;
    	    dayend = rownum;
    	    //send = rownum;
*/    	    
    	    //Adding Staff Details
    	    createSheetTitle(sheet.createRow(rownum++), "Staff Names");
    	    // sheet.addMergedRegion(CellRangeAddress.valueOf("$A$1:$AQ$1"));

    	    // Create Header
    	    row.setHeightInPoints(40);
    	    Cell headerCellStaff;
    	    int cellnumStaff = 0;

    	    row = sheet.createRow(rownum++);
    	    headerCellStaff = row.createCell(cellnumStaff++);
    	    headerCellStaff.setCellValue("Staff Id");
    	    headerCellStaff.setCellStyle(styles.get("header"));
    	    headerCellStaff = row.createCell(cellnumStaff++);
    	    headerCellStaff.setCellValue("Staff Name");
    	    headerCellStaff.setCellStyle(styles.get("header"));
    	    headerCellStaff = row.createCell(cellnumStaff++);
    	   // headerCell.setCellValue("Section Id");
    	   // headerCell.setCellStyle(styles.get("header"));
    	   // headerCell = row.createCell(cellnum++);
    	    headerCellStaff.setCellValue("Staff Description");
    	    headerCellStaff.setCellStyle(styles.get("header"));

    	    if (ObjectFunctions.isNotNullOrEmpty(staffList)) {

    		for (ViewStaffPersonAccountDetails obj : staffList) {
    			cellnumStaff = 0;
    		    row = sheet.createRow(rownum++);
    		    cell = row.createCell(cellnumStaff++);
    		    cell.setCellValue(obj.getStaffId());
    		    cell = row.createCell(cellnumStaff++);
    		    cell.setCellValue(obj.getPersonFullName());
    		   // cell = row.createCell(cellnum++);
    		   // cell.setCellValue(obj.getSubjectNumber());
    		    cell = row.createCell(cellnumStaff++);
    		    cell.setCellValue(obj.getDescription());
    		}
    	    }
    	    //cend = rownum;
    	    staffend = rownum;
    	    //send = rownum;
    	    
    	} catch (Exception ex) {
    		ex.printStackTrace();
    		JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
    	}
        }
    
    public void createClassDaysSheet(String sheetName, List<ViewStaffPersonAccountDetails> staffList, List<WeekDays> weekDaysList,
    	    long academicYear, long custId) {
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

    	    // Cust Id
    	    row = sheet.createRow(rownum++);
    	    cell = row.createCell(0);
    	    cell.setCellValue("Cust Id");
    	    cell = row.createCell(1);
    	    cell.setCellValue(custId);
   
    	     	    
    	   //Adding Days Names 
    	    
    	    createSheetTitle(sheet.createRow(rownum++), "Day Names");
    	    // sheet.addMergedRegion(CellRangeAddress.valueOf("$A$1:$AQ$1"));

    	    // Create Header
    	    row.setHeightInPoints(40);
    	    Cell headerCellDays;
    	    int cellnumDays = 0;

    	    row = sheet.createRow(rownum++);
    	    headerCellDays = row.createCell(cellnumDays++);
    	    headerCellDays.setCellValue("Day Id");
    	    headerCellDays.setCellStyle(styles.get("header"));
    	    headerCellDays = row.createCell(cellnumDays++);
    	    headerCellDays.setCellValue("Day Name");
    	    headerCellDays.setCellStyle(styles.get("header"));
    	    headerCellDays = row.createCell(cellnumDays++);
    	  
    	    if (ObjectFunctions.isNotNullOrEmpty(weekDaysList)) {

    		for (WeekDays obj : weekDaysList) {
    			cellnumDays = 0;
    		    row = sheet.createRow(rownum++);
    		    cell = row.createCell(cellnumDays++);
    		    cell.setCellValue(obj.getId());
    		    cell = row.createCell(cellnumDays++);
    		    cell.setCellValue(obj.getDayName());
    		}
    	    }
    	    dayend = rownum;
    	    
    	} catch (Exception ex) {
    		ex.printStackTrace();
    		JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
    	}
        }
    
    public void createClassHoursSheet(String sheetName, List<ViewStaffPersonAccountDetails> staffList, List<WeekDays> weekDaysList,
    	    long academicYear, long custId) {
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

    	    // Cust Id
    	    row = sheet.createRow(rownum++);
    	    cell = row.createCell(0);
    	    cell.setCellValue("Cust Id");
    	    cell = row.createCell(1);
    	    cell.setCellValue(custId);
   
    	    //Adding Hours Names 
    	    
    	    createSheetTitle(sheet.createRow(rownum++), "Hours");
    	    // sheet.addMergedRegion(CellRangeAddress.valueOf("$A$1:$AQ$1"));

    	    // Create Header
    	    row.setHeightInPoints(40);
    	    Cell headerCellHours;
    	    int cellnumHours = 0;

    	    row = sheet.createRow(rownum++);
    	    headerCellHours = row.createCell(cellnumHours++);
    	    headerCellHours.setCellValue("Hour Number");
    	    headerCellHours.setCellStyle(styles.get("header"));
    	    headerCellHours = row.createCell(cellnumHours++);
    	    headerCellHours.setCellValue("Hour Name");
    	    headerCellHours.setCellStyle(styles.get("header"));

    		for (int i=1; i<=9; i++) {
    			cellnumHours = 0;
    		    row = sheet.createRow(rownum++);
    		    if(i==1)
    		    {
    		    	cell = row.createCell(cellnumHours++);
        		    cell.setCellValue(i);
    		    	cell = row.createCell(cellnumHours++);
    		    	cell.setCellValue("9:00-9:40");
    		    }
    		    if(i==2)
    		    {
    		    	cell = row.createCell(cellnumHours++);
        		    cell.setCellValue(i);
    		    	cell = row.createCell(cellnumHours++);
    		    	cell.setCellValue("9:40-10:20");
    		    }
    		    if(i==3)
    		    {
    		    	cell = row.createCell(cellnumHours++);
        		    cell.setCellValue(i);
    		    	cell = row.createCell(cellnumHours++);
    		    	cell.setCellValue("10:45-11-25");
    		    }
    		    if(i==4)
    		    {
    		    	cell = row.createCell(cellnumHours++);
        		    cell.setCellValue(i);
    		    	cell = row.createCell(cellnumHours++);
    		    	cell.setCellValue("11:25-12:05pm");
    		    }
    		    if(i==5)
    		    {
    		    	cell = row.createCell(cellnumHours++);
        		    cell.setCellValue(i);
    		    	cell = row.createCell(cellnumHours++);
    		    	cell.setCellValue("LunchBreak");
    		    }
    		    if(i==6)
    		    {
    		    	cell = row.createCell(cellnumHours++);
        		    cell.setCellValue(i);
    		    	cell = row.createCell(cellnumHours++);
    		    	cell.setCellValue("12:30pm-1:10pm");
    		    }
    		    if(i==7)
    		    {
    		    	cell = row.createCell(cellnumHours++);
        		    cell.setCellValue(i);
    		    	cell = row.createCell(cellnumHours++);
    		    	cell.setCellValue("1:10pm-1:50pm");
    		    }
    		    if(i==8)
    		    {
    		    	cell = row.createCell(cellnumHours++);
        		    cell.setCellValue(i);
    		    	cell = row.createCell(cellnumHours++);
    		    	cell.setCellValue("2:00pm-2:40pm");
    		    }
    		    if(i==9)
    		    {
    		    	cell = row.createCell(cellnumHours++);
        		    cell.setCellValue(i);
    		    	cell = row.createCell(cellnumHours++);
    		    	cell.setCellValue("2:40pm-3:20pm");
    		    }
    		}
    	    hoursend = rownum;
    	    
    	} catch (Exception ex) {
    		ex.printStackTrace();
    		JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
    	}
        }
}
