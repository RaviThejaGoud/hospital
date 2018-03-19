/**
 * 
 */
package com.urt.util.excel.settings;

/**
 * @author Narahari
 */

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFHyperlink;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ComparisonOperator;
import org.apache.poi.ss.usermodel.ConditionalFormattingRule;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.PrintSetup;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.SheetConditionalFormatting;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;

import com.churchgroup.util.object.ObjectFunctions;
import com.urt.webapp.action.jrexception.JRExceptionClient;

public class PrepareSchoolHolidaysExcel {
	
	private static final Log log = LogFactory.getLog(PrepareSchoolHolidaysExcel.class);

    /** Always make sure the positions and query matches.  */

    private static final String[] titles = { "holiday Id", "Description", "Start Date (DD-MMM-YYYY)", "End Date (DD-MMM-YYYY)"};

    private static final Integer[] colwidths = { 7, 25, 25, 25};

    public static final String query = "select id,description,date(startDate),date(endDate) from schoolHolidays";

    
   /* private int statepos = 16;
    private int stateEnd = 0;
    private int rolesListEnd=0;*/
    private int cend = 0;
    private int send = 0;
   // private String[] isSchoolWorkingDay = {"No", "Yes"};
    
    
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
  //  private String[] dateOfBirth ={"dd/mm/yyyy"};
  //  private String[] dateOfJoining = {"dd/mm/yyyy"};
    
    public PrepareSchoolHolidaysExcel() {
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


    
 /*   private DataValidation createStatesDropDownList(int srow, int erow) {
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
    */
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

    public void finalPrep(String sheetname,List<Object[]> students) {

	wb.setSheetOrder(sheetname, wb.getNumberOfSheets() - 1);
	
	Sheet sheet;
	int nofsheets = wb.getNumberOfSheets();
	for (int i = 0; i < nofsheets; i++) {
	    sheet = wb.getSheetAt(i);
	    /*int lastNumber=0;
	    if (!ObjectFunctions.isNullOrEmpty(students)) {
	    	lastNumber=sheet.getLastRowNum();
	    }else {
	    	lastNumber=10000;
		}*/
	    SheetConditionalFormatting sheetCF = sheet.getSheetConditionalFormatting();
	    ConditionalFormattingRule rule1 = sheetCF.createConditionalFormattingRule(ComparisonOperator.GT, "0");
	    
	   // sheet.addValidationData(createDropDownListFromExplicitList(2, lastNumber,4,isSchoolWorkingDay,"IS School Working Day", "Select Is School Working Day from dropdown!\n(For 'No' : 'No', 'Yes' : 'Yes')","Invalid School Working Day", "Please select the School Working Day from the dropdown!"));
	    
	     /*PatternFormatting fill1 = rule1.createPatternFormatting();
	    fill1.setFillBackgroundColor(IndexedColors.GREY_40_PERCENT.index);
	    fill1.setFillPattern(PatternFormatting.SOLID_FOREGROUND);
	    CellRangeAddress[] regions = { CellRangeAddress.valueOf("A2:A" + lastNumber) };
	    sheetCF.addConditionalFormatting(regions, rule1);*/
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
		    	if(i == 1 || i == 2 || i == 3){
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
    /*private DataValidation createDropDownListFromExplicitList(int srow, int erow,int cpos,String[] listValues,String promptBoxTitle,String promptBoxText,String errorBoxTitle,String errorBoxText) {
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

    public void createStaffSheet(String sheetName, List<Object[]> staffs,String sheetTitleDesc) {

	try {

	    HSSFSheet sheet = (HSSFSheet) wb.createSheet(sheetName);
	    //sheet.createFreezePane(5, 2);
	    log.debug("sheet name : " + sheetName);
	    PrintSetup printSetup = sheet.getPrintSetup();
	    printSetup.setLandscape(true);
	    sheet.setFitToPage(true);
	    sheet.setHorizontallyCenter(true);
	    int rownum = 0;
	    createSheetTitle(sheet.createRow(rownum++), sheetTitleDesc);
	    createSheetHeader(sheet.createRow(rownum++));
	    sheet.addMergedRegion(CellRangeAddress.valueOf("$A$1:$E$1"));
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
	    //sheet.setColumnHidden(4, true);
	    sheet.setColumnWidth(0, 30 * 256); // 30 characters wide
	    for (int i = 0; i < colwidths.length; i++) {
		sheet.setColumnWidth(i, colwidths[i] * 256);
	    }
	    if(!ObjectFunctions.isNullOrEmpty(staffs)){
	    	 org.apache.poi.ss.usermodel.Cell cell = null;	
		     CellStyle hlink_style = wb.createCellStyle();
		     Font hlink_font = wb.createFont();
		     hlink_font.setUnderline(Font.U_SINGLE);
		     hlink_font.setColor(IndexedColors.BLUE.getIndex());
		     hlink_style.setAlignment(hlink_style.ALIGN_RIGHT);
		     hlink_style.setFont(hlink_font);
		 	 HSSFHyperlink url_link = new HSSFHyperlink(HSSFHyperlink.LINK_URL);
		 	 url_link.setAddress("http://www.EazySchool.com");
	         cell = sheet.createRow(rownum).createCell(0);
	         sheet.addMergedRegion(new CellRangeAddress(rownum,rownum,0,4));
	         cell.setCellValue("Generated by : www.EazySchool.com");         
	         cell.setHyperlink(url_link);
	         cell.setCellStyle(hlink_style);
	    }
	} catch (Exception ex) {
	    ex.printStackTrace();
	    JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
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
			cell.setCellValue((String) obj.toString());
		    } else if (obj instanceof Date || obj instanceof Timestamp) {
			cell.setCellValue((Date) obj);
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
	    JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
	}
    }

    public void createConfigurationsSheet(String sheetName,long academicYear, long custId) {
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
	    
	    rolesListEnd = rownum;*/
	    cend = rownum;
	    send = rownum;
	} catch (Exception ex) {
		ex.printStackTrace();
		JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
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

	    //mandatory Columns to School Holidays sheet
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
	    JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
	}

	return styles;
    }
}
