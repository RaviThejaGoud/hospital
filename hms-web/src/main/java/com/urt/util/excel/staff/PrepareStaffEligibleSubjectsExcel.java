/**
 * 
 */
package com.urt.util.excel.staff;

/**
 * @author sreeram
 */

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.apache.poi.ss.usermodel.PrintSetup;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.SheetConditionalFormatting;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellRangeAddressList;

import com.churchgroup.util.object.ObjectFunctions;
import com.urt.persistence.model.study.StudyClass;
import com.urt.persistence.model.study.StudySubject;
import com.urt.persistence.model.study.ViewStaffPersonAccountDetails;
import com.urt.webapp.action.jrexception.JRExceptionClient;

public class PrepareStaffEligibleSubjectsExcel {

	//private static final String[] titles = {"Class & Sections"};
	// private static final Integer[] colwidths = {16};
	 
    private int cend = 0;
    private int clcend = 0;
    private int cfcend = 0;
    private int cpos = 0;
  //  private int send = 0;
    private int spos = 0;
    private int cstp = 1;
    /*private int cpos = 5;
    private int spos = 6;
    private String refSheet = "";
    */

    private Map<String, CellStyle> styles;
    private Workbook wb;
    
    public PrepareStaffEligibleSubjectsExcel() {
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
    public int getCend() {
		return cend;
	}

	public void setCend(int cend) {
		this.cend = cend;
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


    private void createSheetHeader(Row headerRow,List<StudySubject> subjectsList,HSSFSheet sheet) {
	try {
	    headerRow.setHeightInPoints(40);
	    Cell headerCell;
	    int j = 1;
	    if(sheet.getSheetName().equalsIgnoreCase("Class-Subject")){
		  headerCell = headerRow.createCell(0);
		  headerCell.setCellValue("Class & Section");
		  headerCell.setCellStyle(styles.get("header"));
	    } 
	    if(sheet.getSheetName().equalsIgnoreCase("Class-Subject-Teacher")){
	    	 j = 3;
			  headerCell = headerRow.createCell(0);
			  headerCell.setCellValue("Staff Name");
			  headerCell.setCellStyle(styles.get("header"));
			  headerCell = headerRow.createCell(1);
			  headerCell.setCellValue("Class & Section"); 
			  headerCell.setCellStyle(styles.get("header"));
			  headerCell = headerRow.createCell(2);
			  headerCell.setCellValue("ClassTeacher");
			  headerCell.setCellStyle(styles.get("header"));
		    }
		  for(StudySubject subject : subjectsList){
				headerCell = headerRow.createCell(j);
				headerCell.setCellValue(subject.getName());
				headerCell.setCellStyle(styles.get("header"));
				j++;
	    }
	
	
	} catch (Exception ex) {
	    ex.printStackTrace();
	    JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
	}

    }

   

    public void createStaffConfigurationsSheet(String sheetName,long academicYear, long custId,List<StudySubject> subectsList,List<StudyClass> classesList,List<ViewStaffPersonAccountDetails> staffsList) {
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
    	   
    	    Cell headerCell;
    	    int cellnum = 0;
    	    
    	    // Subject Details
    	    if (ObjectFunctions.isNotNullOrEmpty(subectsList)) {
    	    	rownum=3;
    		    row = sheet.getRow(rownum);
    		    if(ObjectFunctions.isNullOrEmpty(row)){
    		    	row = sheet.createRow(rownum);
    		    }
    		    cellnum=6;
    		    headerCell = row.createCell(cellnum++);
    		    headerCell.setCellValue("Subject Name");
    		    headerCell.setCellStyle(styles.get("header"));
    		    headerCell = row.createCell(cellnum++);
    		    headerCell.setCellValue("Subject Id");
    		    headerCell.setCellStyle(styles.get("header"));
    		    headerCell = row.createCell(cellnum++);
    		    headerCell.setCellValue("Class Name");
    		    headerCell.setCellStyle(styles.get("header"));
    		    headerCell = row.createCell(cellnum++);
    		    headerCell.setCellValue("Class Id");
    		    headerCell.setCellStyle(styles.get("header"));
    		    headerCell = row.createCell(cellnum++);
    		    headerCell.setCellValue("Staff Name");
    		    headerCell.setCellStyle(styles.get("header"));
    		    headerCell = row.createCell(cellnum++);
    		    headerCell.setCellValue("Staff Id");
    		    headerCell.setCellStyle(styles.get("header"));
    		   
    		    rownum++;
    		  
    			for (StudySubject obj : subectsList) {
    			    row = sheet.getRow(rownum);
    			    cellnum = 6;
    			    if(ObjectFunctions.isNullOrEmpty(row)){
    			    	row = sheet.createRow(rownum);
    			    }
    			    cell = row.createCell(cellnum++);
    			    cell.setCellValue(obj.getName());
    			    cell = row.createCell(cellnum++);
    			    cell.setCellValue(obj.getId());
    			    rownum++;
    			}
    			row = sheet.getRow(rownum);
    		    if(ObjectFunctions.isNullOrEmpty(row)){
    		    	row = sheet.createRow(rownum);
    		    }
    			cell = row.createCell(6);
    			cell.setCellValue("Other");
    			cell = row.createCell(7);
    			cell.setCellValue(0);
    		    rownum++;
    			rownum=4;
    			for (StudyClass obj1 : classesList) {
    			    row = sheet.getRow(rownum);
    			    cellnum = 8;
    			    if(ObjectFunctions.isNullOrEmpty(row)){
    			    	row = sheet.createRow(rownum);
    			    }
    			    cell = row.createCell(cellnum++);
    			    if(!ObjectFunctions.isNullOrEmpty(obj1.getSection()))
    			    	cell.setCellValue(obj1.getClassName()+" - "+obj1.getSection());
    			    else
    			    	cell.setCellValue(obj1.getClassName());
    			    cell = row.createCell(cellnum++);
    			    cell.setCellValue(obj1.getId());
    			    rownum++;
    			}
    			clcend = rownum;
    			rownum=4;
    			for (ViewStaffPersonAccountDetails obj2 : staffsList) {
    			    row = sheet.getRow(rownum);
    			    cellnum = 10;
    			    if(ObjectFunctions.isNullOrEmpty(row)){
    			    	row = sheet.createRow(rownum);
    			    }
    			    cell = row.createCell(cellnum++);
    			    cell.setCellValue(obj2.getFullName());
    			    cell = row.createCell(cellnum++);
    			    cell.setCellValue(obj2.getStaffId());
    			    rownum++;
    			}
    		cfcend = rownum;
    	    }
    	    cend = rownum;
    	   
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
    public void finalPreperation(String sheetname,List<StudyClass> classList,List<ViewStaffPersonAccountDetails> staffList) {

    	wb.setSheetOrder(sheetname, wb.getNumberOfSheets() - 1);
    	
    	Sheet sheet;
    	int nofsheets = wb.getNumberOfSheets();
    //	int j=1;
    	for (int i = 0; i < nofsheets; i++) {
    	    sheet = wb.getSheetAt(i);
    	    int lastNumber = 0;
    	    if(!ObjectFunctions.isNullOrEmpty(classList))
	    	  lastNumber = (classList.size()*staffList.size());
    	    else
    	    	lastNumber = 10000;
    	    String sheetName = wb.getSheetName(i);
    	    if(sheetName.equals("Class-Subject")){
    	    	sheet.addValidationData(createClassDropDownList(3, lastNumber));
    	    }
    	    if(sheetName.equals("Class-Subject-Teacher")){
	    	    sheet.addValidationData(createStaffsDropDownList(3, lastNumber));
	    	    sheet.addValidationData(createClassSectionDropDownList(3, lastNumber));
    	    }
    	    SheetConditionalFormatting sheetCF = sheet.getSheetConditionalFormatting();
    	    ConditionalFormattingRule rule1 = sheetCF.createConditionalFormattingRule(ComparisonOperator.GT, "0");
    	 //   PatternFormatting fill1 = rule1.createPatternFormatting();
    	    CellRangeAddress[] regions = { CellRangeAddress.valueOf("A2:A" + lastNumber) };
    	    sheetCF.addConditionalFormatting(regions, rule1);
    	}
    	
    	if(wb.getSheetIndex("Configurations") != -1)
        	wb.setSheetHidden(wb.getSheetIndex("Configurations"), 1);
    		wb.setActiveSheet(0);
        }
    
    private DataValidation createClassDropDownList(int srow, int erow) {
    	DataValidation dataValidation = null;
    	try {
    	    CellRangeAddressList addressList = new CellRangeAddressList(srow, erow, cpos, cpos);
    	    DVConstraint dvConstraint = DVConstraint.createFormulaListConstraint("Configurations!$I$5:$I$" + clcend);
    	    dataValidation = new HSSFDataValidation(addressList, dvConstraint);
    	    dataValidation.setSuppressDropDownArrow(false);
    	    dataValidation.createPromptBox("Class", "Select from dropdown!");
    	    dataValidation.setShowPromptBox(true);
    	    dataValidation.setErrorStyle(DataValidation.ErrorStyle.STOP);
    	    dataValidation.createErrorBox("Invalid Class", "Please select the Class from the dropdown!");
    	} catch (Exception ex) {
    	    ex.printStackTrace();
    	    JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
    	}
    	return dataValidation;
     }
    private DataValidation createClassSectionDropDownList(int srow, int erow) {
    	DataValidation dataValidation = null;
    	try {
    	    CellRangeAddressList addressList = new CellRangeAddressList(srow, erow, cstp, cstp);
    	    DVConstraint dvConstraint = DVConstraint.createFormulaListConstraint("Configurations!$I$5:$I$" + clcend);
    	    dataValidation = new HSSFDataValidation(addressList, dvConstraint);
    	    dataValidation.setSuppressDropDownArrow(false);
    	    dataValidation.createPromptBox("Class", "Select from dropdown!");
    	    dataValidation.setShowPromptBox(true);
    	    dataValidation.setErrorStyle(DataValidation.ErrorStyle.STOP);
    	    dataValidation.createErrorBox("Invalid Class", "Please select the Class from the dropdown!");
    	} catch (Exception ex) {
    	    ex.printStackTrace();
    	    JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
    	}
    	return dataValidation;
     }
   private DataValidation createStaffsDropDownList(int srow, int erow) {
    	DataValidation dataValidation = null;
    	try {
    	    CellRangeAddressList addressList = new CellRangeAddressList(srow, erow, spos, spos);
    	    DVConstraint dvConstraint = DVConstraint.createFormulaListConstraint("Configurations!$K$5:$K$" + cfcend);
    	    dataValidation = new HSSFDataValidation(addressList, dvConstraint);
    	    dataValidation.setSuppressDropDownArrow(false);
    	    dataValidation.createPromptBox("Staff", "Select from dropdown!");
    	    dataValidation.setShowPromptBox(true);
    	    dataValidation.setErrorStyle(DataValidation.ErrorStyle.STOP);
    	    dataValidation.createErrorBox("Invalid Staff", "Please select the staffs from the dropdown!");
    	} catch (Exception ex) {
    	    ex.printStackTrace();
    	    JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
    	}
    	return dataValidation;
     }

    
    
    public void createStaffEligibleSubjectsSheet(String sheetName, String sheetTitleDesc,List<StudySubject> subjectsList,List<ViewStaffPersonAccountDetails> staffsList) {
    	try {
    		HSSFSheet sheet = (HSSFSheet) wb.createSheet(sheetName);
    	    sheet.createFreezePane(5, 3);
    	    int rownum = 0;
    	    createSheetTitle(sheet.createRow(rownum++), sheetTitleDesc);
    	    if(sheetName.equalsIgnoreCase("Class-Subject")){
    	    	Row row=sheet.createRow(1);
    			row.setHeightInPoints(25);
    			sheet.addMergedRegion(CellRangeAddress.valueOf("$A$2:$R$2"));
    			Cell cell = row.createCell(0);
    			cell.setCellValue("Note :-\n1) For eligible subject add 'Y'. 2) For non eligible subject add 'N'. ");
    			cell.setCellStyle(styles.get("subtitle"));
    	    	 createSheetHeader(sheet.createRow(2),subjectsList,sheet);
    	    }
    	    if(sheetName.equalsIgnoreCase("Class-Subject-Teacher")){
    	    	Row row=sheet.createRow(1);
    			row.setHeightInPoints(25);
    			sheet.addMergedRegion(CellRangeAddress.valueOf("$A$2:$R$2"));
    			Cell cell = row.createCell(0);
    			cell.setCellValue("Note :-\n1) For eligible subject add 'Y'. 2) For teaching subject add 'T'. 3) For non eligible and non teaching subject add 'N'. ");
    			cell.setCellStyle(styles.get("subtitle"));
      	    	createSheetHeader(sheet.createRow(2),subjectsList,sheet);
       	    }
    	    PrintSetup printSetup = sheet.getPrintSetup();
    	    printSetup.setLandscape(true);
    	    sheet.setFitToPage(true);
    	    sheet.setHorizontallyCenter(true);
    	    sheet.addMergedRegion(CellRangeAddress.valueOf("$A$1:$R$1"));
    	    sheet.setColumnWidth(0, 30 * 256); // 30 characters wide
    	} catch (Exception ex) {
    	    ex.printStackTrace();
    	    JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
    	}
        }

}
