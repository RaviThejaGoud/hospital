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

import com.churchgroup.util.object.ObjectFunctions;
import com.churchgroup.util.string.StringFunctions;
import com.urt.webapp.action.jrexception.JRExceptionClient;

public class PrepareStudentNoEmailAndMobileExcel {
	
	private static final Log log = LogFactory.getLog(PrepareStudentNoEmailAndMobileExcel.class);

    /** Always make sure the positions and query matches. Correct the Class and
     * Section positions. This is used to create a drop down list for classes
     * and sections */

    private static final String[] titles = { "Student Id", "Admission Number","First Name", "Last Name", "Father Name", "Mother Name" ,"Mobile Number(Primary)", "Mobile Number(Secondary)", "Student Mobile","Parent Email", "Student Email"};

     private static final Integer[] colwidths = { 25, 25, 25, 25, 25, 25, 25,25, 25, 25, 25 };

    public static final String query = "select studentId,admissionNumber,firstName,lastName,fatherName,motherName,mobileNumber,secondaryMobileNumber,studentMobile,parentEmail,studentEmail from vw_studentDetails ";

    private int cend = 0;
    private int send = 0;
    private int stateEnd = 0;
    
	private Map<String, CellStyle> styles;
    private Workbook wb;
    
    public PrepareStudentNoEmailAndMobileExcel() {
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

    private void createSheetHeader(Row headerRow) {
	try {
	    headerRow.setHeightInPoints(40);
	    Cell headerCell;
	    for (int i = 0; i < titles.length; i++) {
	    	if(i == 1){
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
	    JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
	}

    }

    public void createStudentSheet(String sheetName, List<Object[]> students,String sheetTitleDesc, String tempString) {

		try {
		    HSSFSheet sheet = (HSSFSheet) wb.createSheet(sheetName);
		    sheet.createFreezePane(6, 2);
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
		    if (!ObjectFunctions.isNullOrEmpty(students)) {
				for (Object[] obj : students) {
				    row = sheet.createRow(rownum++);
				    writeStudentRow(obj, row);
				}
		    }else{
		    	 Cell cell;
		    	 row = sheet.createRow(2);
		    	 sheet.addMergedRegion(new CellRangeAddress(2,3,1,11));
		    	 cell = row.createCell(1);
		    	 cell.setCellValue("Currently there are no student's in this class.");
		    }
		    if (!ObjectFunctions.isNullOrEmpty(students)) {
			    if (StringFunctions.isNotNullOrEmpty(tempString)) {
			    	sheet.setColumnHidden(6, true);
			    	sheet.setColumnHidden(7, true);
			    	sheet.setColumnHidden(8, true);
			    	sheet.setColumnHidden(9, true);
			    	sheet.setColumnHidden(10, true);
			    	String[] s = tempString.split(",");
					for (String str : s) {
							if("mobileNumber".equalsIgnoreCase(str))
								 sheet.setColumnHidden(6, false);
							if("secondaryMobileNumber".equalsIgnoreCase(str))
								 sheet.setColumnHidden(7, false);
							if("studentMobile".equalsIgnoreCase(str))
								 sheet.setColumnHidden(8, false);
							if("parentEmail".equalsIgnoreCase(str))
								 sheet.setColumnHidden(9, false);
							if("studentEmail".equalsIgnoreCase(str))
								 sheet.setColumnHidden(10, false);
					}
			    }
		    }else{
		    	sheet.setColumnHidden(6, false);
		    	sheet.setColumnHidden(7, false);
		    	sheet.setColumnHidden(8, false);
		    	sheet.setColumnHidden(9, false);
		    	sheet.setColumnHidden(10, false);
		    }
		    sheet.setColumnHidden(0, true);
		    sheet.setColumnWidth(0, 30 * 256); // 30 characters wide
		    for (int i = 0; i < colwidths.length; i++) {
		    	sheet.setColumnWidth(i, colwidths[i] * 256);
		    }
		} catch (Exception ex) {
		    ex.printStackTrace();
		    JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
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
				cell.setCellValue((String) obj.toString());
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
		    
		    //Mandatory Columns to No Email and Mobile Numbers to sheet
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
