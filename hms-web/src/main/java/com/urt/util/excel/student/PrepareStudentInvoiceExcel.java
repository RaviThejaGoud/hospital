/**
 * 
 */
package com.urt.util.excel.student;

/**
 * @author sreeram
 */

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
import com.urt.persistence.model.customer.SchoolTerms;
import com.urt.webapp.action.jrexception.JRExceptionClient;

public class PrepareStudentInvoiceExcel {
	
	private static final Log log = LogFactory.getLog(PrepareStudentInvoiceExcel.class);

    /** Always make sure the positions and query matches. Correct the Class and
     * Section positions. This is used to create a drop down list for classes
     * and sections */

    private static final String[] titles = { "Admission Number", "Invoice Number","Payment Date (DD-MMM-YYYY)", "Term Name","Payable Amount","Discount Amount"};

     private static final Integer[] colwidths = {20,20, 35, 20, 20,20,20};


    private int cend = 0;
    private int send = 0;
    private int stateEnd = 0;
    
	private Map<String, CellStyle> styles;
    private Workbook wb;
    
    public PrepareStudentInvoiceExcel() {
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
    public int getSend() {
		return send;
	}

	public void setSend(int send) {
		this.send = send;
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
    	    JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
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
	    JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
	}
    }

    public void finalPrep(String sheetname) {

	wb.setSheetOrder(sheetname, wb.getNumberOfSheets() - 1);
	
	Sheet sheet;
	int nofsheets = wb.getNumberOfSheets();
	for (int i = 0; i < nofsheets; i++) {
	    sheet = wb.getSheetAt(i);
	    int lastNumber=100;
	    sheet.addValidationData(createDropDownList(2, lastNumber, 3, "InvoiceSettings!$B$4:$B$" +stateEnd, "Terms", "Select Term from dropdown!", "Invalid Term", "Please select the term from the dropdown!"));

	    SheetConditionalFormatting sheetCF = sheet.getSheetConditionalFormatting();
	    ConditionalFormattingRule rule1 = sheetCF.createConditionalFormattingRule(ComparisonOperator.GT, "0");
	    PatternFormatting fill1 = rule1.createPatternFormatting();
	    fill1.setFillBackgroundColor(IndexedColors.GREY_40_PERCENT.index);
	    fill1.setFillPattern(PatternFormatting.SOLID_FOREGROUND);
	    CellRangeAddress[] regions = { CellRangeAddress.valueOf("A2:A" + lastNumber) };
	    sheetCF.addConditionalFormatting(regions, rule1);
	}
	if(wb.getSheetIndex("InvoiceSettings") != -1)
    	wb.setSheetHidden(wb.getSheetIndex("InvoiceSettings"), 1);
		wb.setActiveSheet(0);
    }

    private void createSheetHeader(Row headerRow) {
	try {
	    headerRow.setHeightInPoints(40);
	    headerRow.createCell(1).setCellValue("");
	    Cell headerCell;
	    for (int i = 0; i < titles.length; i++) {
	    	headerCell = headerRow.createCell(i);
    		headerCell.setCellType(Cell.CELL_TYPE_STRING);
    		headerCell.setCellValue(titles[i]);
	    	if(i == 0 || i == 1 || i == 2 || i == 3 || i == 4 ){
	    		headerCell.setCellStyle(styles.get("headers"));
		    }else{
				headerCell.setCellStyle(styles.get("header"));
		    }
	    	
	    }
	} catch (Exception ex) {
	    ex.printStackTrace();
	    JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
	}

    }
    

    public void createStudentSheet(String sheetName,String sheetTitleDesc) {

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
	    sheet.addMergedRegion(CellRangeAddress.valueOf("$A$1:$F$1"));
	    sheet.setAutoFilter(new CellRangeAddress(rownum - 1, rownum - 1, 0, titles.length - 1));

	    // finally set column widths, the width is measured in units of
	    // 1/256th of a character width
	    //Hide the student id because some this id is not required to show the user 
	   // sheet.setColumnHidden(0, true);
        //Hide the section column in 6th row because we keep class and section column in one row
	    //sheet.setColumnHidden(6, true);
	    
	    Row row;
		for (int j=0;j<=100;j++) {
		    row = sheet.createRow(rownum++);
		    /*row.createCell(0).setCellType(Cell.CELL_TYPE_STRING);
		    row.createCell(1).setCellType(Cell.CELL_TYPE_STRING);
		    row.createCell(2).setCellType(Cell.CELL_TYPE_STRING);
		    row.createCell(3).setCellType(Cell.CELL_TYPE_STRING);
		    row.createCell(4).setCellType(Cell.CELL_TYPE_STRING);
		    row.createCell(5).setCellType(Cell.CELL_TYPE_STRING);*/
		    writeStudentRow(row);
		}
	    
	    sheet.setColumnWidth(0, 30 * 256); // 30 characters wide
	    for (int i = 0; i < colwidths.length; i++) {
	    	sheet.setColumnWidth(i, colwidths[i] * 256);
	    }
	} catch (Exception ex) {
	    ex.printStackTrace();
	    JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
	}
    }

	private void writeStudentRow(Row row) {
		try {
			Cell cell;
			row.createCell(1).setCellValue("");
			for (int colnum = 0; colnum < 6; colnum++) {
				 cell = row.createCell(colnum);
				 cell.setCellStyle(styles.get("string"));
				 //cell.setCellStyle(styles.get("string"));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
	}

    public void createStudentInvoiceSheet(String sheetName,List<SchoolTerms> schoolTermsList) {
    	try {
    	    HSSFSheet sheet = (HSSFSheet) wb.createSheet(sheetName);
    	    Row row=null;
    	    Cell cell;
    	    int rownum = 0;
    	   
    	    row = sheet.createRow(rownum++);
    	    createSheetTitle(sheet.createRow(rownum++), "Term Names");
    	    row.setHeightInPoints(40);
    	    Cell headerCell;
    	    int cellnum = 0;
    	    
    	    row = sheet.createRow(rownum++);
    	    headerCell = row.createCell(cellnum++);
    	    headerCell.setCellValue("Term Id");
    	    headerCell.setCellStyle(styles.get("header"));
    	    headerCell = row.createCell(cellnum++);
    	    headerCell.setCellValue("Term Name");
    	    headerCell.setCellStyle(styles.get("header"));
    	   
    	    
    	    if (ObjectFunctions.isNotNullOrEmpty(schoolTermsList)) {
    		for (SchoolTerms schoolTerms : schoolTermsList) {
    		    cellnum = 0;
    		    row = sheet.createRow(rownum++);
    		    cell = row.createCell(cellnum++);
    		    cell.setCellValue(schoolTerms.getId());
    		    cell.setCellType(Cell.CELL_TYPE_STRING);
    		    cell = row.createCell(cellnum++);
    		    cell.setCellType(Cell.CELL_TYPE_STRING);
    		    cell.setCellValue(schoolTerms.getTermName());//+" - ("+schoolTerms.getFeeSetting().getSettingName()+")"
    		}
    	    }
    	    stateEnd = rownum;
    	   
    	 
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
        style.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        style.setLocked(true);
        styles.put("readOnlyString", style);

	} catch (Exception ex) {
	    ex.printStackTrace();
	    JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
	}

	return styles;
    }
    public int getCend() {
		return cend;
	}

	public void setCend(int cend) {
		this.cend = cend;
	}

	public int getStateEnd() {
		return stateEnd;
	}

	public void setStateEnd(int stateEnd) {
		this.stateEnd = stateEnd;
	}
}
