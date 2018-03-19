/**
 * 
 */
package com.urt.util.excel.library;

/**
 * @author Upper Room
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
import org.apache.poi.ss.usermodel.PrintSetup;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.SheetConditionalFormatting;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellRangeAddressList;

import com.churchgroup.util.object.ObjectFunctions;
import com.churchgroup.util.string.StringFunctions;
import com.urt.persistence.model.study.ClassName;
import com.urt.persistence.model.study.StudySubject;
import com.urt.util.common.RayGunException;
import com.urt.webapp.action.jrexception.JRExceptionClient;

public class PrepareLibraryBooksExcel {

	private static final Log log = LogFactory.getLog(PrepareLibraryBooksExcel.class);
    /** Always make sure the positions and query matches.  */

    private static final String[] titles = {"Book Title Id", "Subjects","Classes","Section", "Book Name","Book Code","Book KeyWord", "Author Name", "Publisher Name", "No Of Copies","Cost Of Book", 
    	"Max Allowed to Issue","Max allowed days that student can hold","Reference Books","Date","Year of Publication","Call No.", "Class No.","Edition","Pages","Invoice No","Place","Remarks","Language","Acquisition Number"};

   /* private static final String[] titles = { "Subjects","Classes","Section", "Book Name","Book Code","Book KeyWord", "Author Name", "Publisher Name", "No Of Copies","Cost Of Book", "Reading Books Count",
    	"Issue Books Count","Book IssueDays","Book RequestExpiry Days"};
   */ 
	
	/* private static final String[] titles = { "Date","Acc No.","Title", "Author","Publisher & Place of Publication","Year of Publication", "Price", "Call No.", "Class No.","Remarks", 
	    	"Subject Name"};*/
	 
    private static final Integer[] colwidths = {25, 25, 17, 17, 17, 17,17, 17, 17, 17, 17, 17,17,17,17,17,17,17,17,17,17,17,17,20};
    
    
    /*public static final String query1 = "select name, '',sections,bookName,lableCode,bookKeyWord,"
	    + "author,publisher,noOfCopies," 
	    + "cost,issueBookCount,issueDays,otherSubjects,bookPublishedDate,"
	    + "yearOfPublication,callNo,classNo,billNo,noOfPages,bookEdition,place,bookRemarks,"
	    + "language,acquisitionNumber from bookTitle ";*/
    
    public static final String query = "select bt.id,ss.name, cl.className,bt.sections,bt.bookName,bl.lableCode,bt.bookKeyWord,"
	    + "bt.author,bt.publisher,bt.noOfCopies," 
	    + "bt.cost,bt.issueBookCount,bt.issueDays,bt.otherSubjects,bt.bookPublishedDate,"
	    + "bt.yearOfPublication,bt.callNo,bt.classNo,bt.bookEdition,bt.noOfPages,bt.billNo,bt.place,bt.bookRemarks,"
	    + "bt.language,bt.acquisitionNumber from bookTitle bt LEFT JOIN bookLables  bl on (bt.id = bl.bookTitleId) LEFT JOIN studySubject ss on (bt.subjectId=ss.id) LEFT JOIN class cl ON (cl.id = bt.classId)";
    
    private int cend = 0;
    private int send = 0;
    private int cpos = 2;
    private int spos = 1;
    private Map<String, CellStyle> styles;
    private Workbook wb;
    
    public PrepareLibraryBooksExcel() {
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
    	    DVConstraint dvConstraint = DVConstraint.createFormulaListConstraint("Configurations!$I$5:$I$" + cend);
    	    dataValidation = new HSSFDataValidation(addressList, dvConstraint);
    	    dataValidation.setSuppressDropDownArrow(false);
    	    dataValidation.createPromptBox("Class", "Select from dropdown!");
    	    dataValidation.setShowPromptBox(true);
    	    dataValidation.setErrorStyle(DataValidation.ErrorStyle.STOP);
    	    dataValidation.createErrorBox("Invalid Class", "Please select the Class from the dropdown!");
    	} catch(Exception ex)
		{
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
    	return dataValidation;
     }
    private DataValidation createSubjectsDropDownList(int srow, int erow) {
    	DataValidation dataValidation = null;
    	try {
    	    CellRangeAddressList addressList = new CellRangeAddressList(srow, erow, spos, spos);
    	    DVConstraint dvConstraint = DVConstraint.createFormulaListConstraint("Configurations!$H$5:$H$" + send);
    	    dataValidation = new HSSFDataValidation(addressList, dvConstraint);
    	    dataValidation.setSuppressDropDownArrow(false);          
    	    dataValidation.createPromptBox("Subjects", "Select from dropdown!");
    	    dataValidation.setShowPromptBox(true);
    	    dataValidation.setErrorStyle(DataValidation.ErrorStyle.STOP);
    	    dataValidation.createErrorBox("Invalid Subjects", "Please select the subjects from the dropdown!");
    	}catch(Exception ex)
		{
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
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
	} catch(Exception ex)
	{
		ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
	}
    }
    private void createNotes(Row titleRow, String title) {
    	try {
    	    titleRow.setHeightInPoints(23);
    	    Cell titleCell = titleRow.createCell(0);
    	    titleCell.setCellValue(title);
    	    titleCell.setCellStyle(styles.get("string"));
    	} catch(Exception ex)
    	{
    		ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
    	}
     }

    public void finalPrep(String sheetname) {

	wb.setSheetOrder(sheetname, wb.getNumberOfSheets() - 1);
	
	Sheet sheet;
	int nofsheets = wb.getNumberOfSheets();
	for (int i = 0; i < nofsheets; i++) {
	    sheet = wb.getSheetAt(i);
	    int lastNumber=10000;
	    sheet.addValidationData(createClassDropDownList(2, lastNumber));
	    sheet.addValidationData(createSubjectsDropDownList(2, lastNumber));
	    SheetConditionalFormatting sheetCF = sheet.getSheetConditionalFormatting();
	    ConditionalFormattingRule rule1 = sheetCF.createConditionalFormattingRule(ComparisonOperator.GT, "0");
	//    PatternFormatting fill1 = rule1.createPatternFormatting();
	    //fill1.setFillBackgroundColor(IndexedColors.GREY_40_PERCENT.index);
	    //fill1.setFillPattern(PatternFormatting.SOLID_FOREGROUND);
	    CellRangeAddress[] regions = { CellRangeAddress.valueOf("A2:A" + lastNumber) };
	    sheetCF.addConditionalFormatting(regions, rule1);
	}
	
	if(wb.getSheetIndex("Configurations") != -1)
    	wb.setSheetHidden(wb.getSheetIndex("Configurations"), 1);
		wb.setActiveSheet(0);
    }

    public void finalPreparation(String sheetname) {

    	wb.setSheetOrder(sheetname, wb.getNumberOfSheets() - 1);
    	
    	Sheet sheet;
    	int nofsheets = wb.getNumberOfSheets();
    	for (int i = 0; i < nofsheets; i++) {
    	    sheet = wb.getSheetAt(i);
    	    int lastNumber=10000;
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
    
    private void createSheetHeader(Row headerRow) {
	try {
	    headerRow.setHeightInPoints(40);
	    Cell headerCell;
	    for (int i = 0; i < titles.length; i++) {
	    	if(i ==  4 || i == 24){
	    		headerCell = headerRow.createCell(i);
	    		headerCell.setCellValue(titles[i]);
	    		headerCell.setCellStyle(styles.get("headers"));
	    	}else{
	    		headerCell = headerRow.createCell(i);
	    		headerCell.setCellValue(titles[i]);
	    		headerCell.setCellStyle(styles.get("header"));
	    	}
		
	    }
	} catch(Exception ex){
		ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
	}
    }
    
    public void createLibraryBookSheet(String sheetName,  String sheetTitleDesc,String generateNum) {

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
    	    createNotes(sheet.createRow(rownum++), "Note:Please enter all the mandatory fields.");
    	    createSheetHeader(sheet.createRow(rownum++));
    	    sheet.addMergedRegion(CellRangeAddress.valueOf("$A$1:$L$1"));
    	    sheet.addMergedRegion(CellRangeAddress.valueOf("$A$2:$C$2"));
    	    sheet.setAutoFilter(new CellRangeAddress(rownum - 1, rownum - 1, 0, titles.length - 1));
    	    sheet.setColumnHidden(0, true);
    	//    Row row;
    	    
    	    //Hide the book code  if that generates by system. 
    	    if(!StringFunctions.isNullOrEmpty(generateNum)){
    	    	if(generateNum.toString().equalsIgnoreCase("'AG'")){
    	    		 sheet.setColumnHidden(5, true);	
    	    	}if(generateNum.toString().equalsIgnoreCase("MG")){
   	    		 sheet.setColumnHidden(9, true);	
    	    	}
    		}
    	    
    	  /*  DataValidation dataValidation = null;
    	    CellRangeAddressList addressList = new CellRangeAddressList(2, 10000, 10, 14);
    	    DVConstraint dvConstraint = DVConstraint.createFormulaListConstraint("Configurations!$I$1:$I$" + 14);
    	    dataValidation = new HSSFDataValidation(addressList, dvConstraint);
    	    dataValidation.setSuppressDropDownArrow(false);
    	    dataValidation.createPromptBox("Number", "Enter only Number");
    	    dataValidation.setShowPromptBox(true);
    	    dataValidation.setErrorStyle(DataValidation.ErrorStyle.STOP);
    	    dataValidation.createErrorBox("Invalid", "Please Enter only Number");*/

    	    //sheet.setColumnWidth(0, 30 * 256); // 30 characters wide
    	    for (int i = 0; i < colwidths.length; i++) {
    		sheet.setColumnWidth(i, colwidths[i] * 256);
    	    }
    	} catch(Exception ex){
    		ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
    		JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
    	}
        }
    

    public void createLibraryBookSheet(String sheetName, List<Object[]> libraryDetails, String sheetTitleDesc,String generateNum) {

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
	    createNotes(sheet.createRow(rownum++), "Note:Acquisition Number is mandatory");
	    createSheetHeader(sheet.createRow(rownum++));
	    sheet.addMergedRegion(CellRangeAddress.valueOf("$A$1:$L$1"));
	    sheet.addMergedRegion(CellRangeAddress.valueOf("$A$2:$C$2"));
	    sheet.setAutoFilter(new CellRangeAddress(rownum - 1, rownum - 1, 0, titles.length - 1));
	    sheet.setColumnHidden(0, true);
	    
	    Row row;
	    if (!ObjectFunctions.isNullOrEmpty(libraryDetails)) {
			for (Object[] obj : libraryDetails) {
			    row = sheet.createRow(rownum++);
			    writeStaffRow(obj, row);
			}
	    }
	    
	    //Hide the book code  if that generates by system. 
	    if(!StringFunctions.isNullOrEmpty(generateNum)){
	    	if(generateNum.toString().equalsIgnoreCase("AG")){
	    		 sheet.setColumnHidden(5, true);	
	    	}if(generateNum.toString().equalsIgnoreCase("MG")){
  	    		 sheet.setColumnHidden(9, true);	
   	    	}
		}
	    
	  /*  DataValidation dataValidation = null;
	    CellRangeAddressList addressList = new CellRangeAddressList(2, 10000, 10, 14);
	    DVConstraint dvConstraint = DVConstraint.createFormulaListConstraint("Configurations!$I$1:$I$" + 14);
	    dataValidation = new HSSFDataValidation(addressList, dvConstraint);
	    dataValidation.setSuppressDropDownArrow(false);
	    dataValidation.createPromptBox("Number", "Enter only Number");
	    dataValidation.setShowPromptBox(true);
	    dataValidation.setErrorStyle(DataValidation.ErrorStyle.STOP);
	    dataValidation.createErrorBox("Invalid", "Please Enter only Number");*/

	    //sheet.setColumnWidth(0, 30 * 256); // 30 characters wide
	    for (int i = 0; i < colwidths.length; i++) {
		sheet.setColumnWidth(i, colwidths[i] * 256);
	    }
	} catch(Exception ex){
		ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
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

	} catch(Exception ex){
		ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		
	}
    }

    public void createConfigurationsSheet(String sheetName,long academicYear, long custId,List<StudySubject> subectsList,List<ClassName> classesList) {
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
		    headerCell.setCellValue("Subject Id");
		    headerCell.setCellStyle(styles.get("header"));
		    headerCell = row.createCell(cellnum++);
		    headerCell.setCellValue("Subject Name");
		    headerCell.setCellStyle(styles.get("header"));
		    headerCell = row.createCell(cellnum++);
		    headerCell.setCellValue("Class Name");
		    headerCell.setCellStyle(styles.get("header"));
		    rownum++;
		  
			for (StudySubject obj : subectsList) {
			    row = sheet.getRow(rownum);
			    cellnum = 6;
			    if(ObjectFunctions.isNullOrEmpty(row)){
			    	row = sheet.createRow(rownum);
			    }
			    cell = row.createCell(cellnum++);
			    cell.setCellValue(obj.getId());
			    cell = row.createCell(cellnum++);
			    cell.setCellValue(obj.getName());
			    rownum++;
			}
			row = sheet.getRow(rownum);
		    if(ObjectFunctions.isNullOrEmpty(row)){
		    	row = sheet.createRow(rownum);
		    }
			cell = row.createCell(6);
			cell.setCellValue(0);
			cell = row.createCell(7);
			cell.setCellValue("Other");
		    rownum++;
			send = rownum;
			
			rownum=4;
			for (ClassName obj1 : classesList) {
			    row = sheet.getRow(rownum);
			    cellnum = 8;
			    if(ObjectFunctions.isNullOrEmpty(row)){
			    	row = sheet.createRow(rownum);
			    }
			    cell = row.createCell(cellnum++);
			    cell.setCellValue(obj1.getClassName());
			    rownum++;
			}
	    }
	    cend = rownum;
	   
	}catch(Exception ex){
		ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
	}
    }

    
    public void createAcademicAndCustIdConfigSheet(String sheetName,long academicYear, long custId) {
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
    	        	   
    	} catch(Exception ex){
    		ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
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
	    
	    //mandatory Column to library sheet
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

	} catch(Exception ex){
		ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
	}
	return styles;
    }
}
