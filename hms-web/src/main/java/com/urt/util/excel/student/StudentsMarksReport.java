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
import org.apache.poi.ss.util.RegionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.churchgroup.util.object.ObjectFunctions;
import com.churchgroup.util.string.StringFunctions;
import com.urt.persistence.model.exam.ExamTypes;
import com.urt.persistence.model.study.StudyClass;
import com.urt.persistence.model.study.ViewStudentClassDetails;
import com.urt.service.manager.interfaces.admin.AdminManager;
import com.urt.webapp.action.jrexception.JRExceptionClient;

public class StudentsMarksReport {
	
	private static final Log log = LogFactory.getLog(StudentsMarksReport.class);
	@Autowired
	AdminManager adminManager;
	//AdminManager adminManager = (AdminManager)SpringContextAware.getBean("adminManager");
    /*private static final String[] titles = { "Student Id", "Admission Number", "Roll Number","First Name", "Last Name", "Gender",
    	"Class & Section", "Date Of Joining (DD-MMM-YYYY)", "Date Of Birth (DD-MMM-YYYY)", "Admitted Class Name", "Father Name",
	    "Father Occupation", "Mother Name", "Mother Occupation", "Parent Email", "Home Number", "Mobile Number",
	    "Street Name", "City", "State", "Postal Code", "Religion", "Community", "Caste Name", "Nationality",
	    "Ration Card Number", "Community Number", "SSLC Number", "TMR Number", "Personal Identification 1",
	    "Personal Identification 2", "Father Annual Income", "Mother Tongue", "Blood Group", "Transport Mode",
	    "Family Doctor", "Preffered Hospital", "Height", "VisionL", "VisionR", "Weight", "Teeth", "Oral Hygiene","Student Relieving Date","Register Number","Scholarship Information","Physically Handicapped" };*/

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
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
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
	    avgMarks.append(" as avgMarks,sg.gradeName,'' as c1,'' as c2,'' as c3,'' as c4,'COMPLETED' as c5 from vw_studentScoreCardMarksDetails ss LEFT JOIN ");
	    studentsMarksQuery.append(avgMarks).append(avgGradeQuery);
	    //log.debug(studentsMarksQuery.toString());
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
	    JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
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
	    	CellStyle borderStyle = wb.createCellStyle();
			for (ViewStudentClassDetails student : students) {
			    row = sheet.createRow(rowNum++);
			    col = 0;
			    //log.debug(studentsMarksQuery+" where studentId ="+ student.getStudId()+" order by ss.subjectSortingOrder");
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
			    	CellRangeAddress cellRangeAdd =new CellRangeAddress(row.getRowNum(),row.getRowNum()+marks.size()-1,col,col);  
			    	//String callRange = String.valueOf(row.getRowNum()+":"+row.getRowNum()+marks.size()-1);
			    	//CellRangeAddress region = CellRangeAddress.valueOf(callRange);
			    	sheet.addMergedRegion(cellRangeAdd); 
			    	
			    	//sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(),row.getRowNum()+marks.size()-1,col,col));
			    	
			    	cell = row.createCell(col++);
			    	//cell.setCellStyle(prepareMergeAddressBoarder(borderStyle));
			    	cell.setCellValue(sNo++);
			    	 final short borderMediumDashed = CellStyle.BORDER_THICK;
				    	RegionUtil.setBorderBottom( borderMediumDashed,cellRangeAdd, sheet, wb );
					    RegionUtil.setBorderTop( borderMediumDashed,cellRangeAdd, sheet, wb );
					    RegionUtil.setBorderLeft( borderMediumDashed,cellRangeAdd, sheet, wb );
					    RegionUtil.setBorderRight( borderMediumDashed,cellRangeAdd, sheet, wb );
					    RegionUtil.setBottomBorderColor(IndexedColors.BLACK.getIndex(), cellRangeAdd, sheet, wb);
					    RegionUtil.setTopBorderColor(IndexedColors.BLACK.getIndex(), cellRangeAdd, sheet, wb);
					    RegionUtil.setLeftBorderColor(IndexedColors.BLACK.getIndex(), cellRangeAdd, sheet, wb);
					    RegionUtil.setRightBorderColor(IndexedColors.BLACK.getIndex(), cellRangeAdd, sheet, wb);
			    	//prepareMergeAddressBoarder(cellRangeAdd,sheet,wb);
			    	
                    
			    	/*CellRangeAddress cellRangeAdd1 =new CellRangeAddress(row.getRowNum(),row.getRowNum()+marks.size()-1,col,col);  
			    	sheet.addMergedRegion(cellRangeAdd1);*/ 
			    	sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(),row.getRowNum()+marks.size()-1,col,col));
			    	cell = row.createCell(col++);
			    	cell.setCellValue(student.getRegisterNumber());
			    	//prepareMergeAddressBoarder(cellRangeAdd,sheet,wb);
			    	cell.setCellStyle(prepareMergeAddressBoarder(borderStyle));
			    	
			    	sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(),row.getRowNum()+marks.size()-1,col,col));
			    	/*CellRangeAddress cellRangeAdd2 =new CellRangeAddress(row.getRowNum(),row.getRowNum()+marks.size()-1,col,col);  
			    	sheet.addMergedRegion(cellRangeAdd2);*/
			    	cell = row.createCell(col++);
			    	cell.setCellValue(student.getFullName());
			    	cell.setCellStyle(prepareMergeAddressBoarder(borderStyle));
			    	//prepareMergeAddressBoarder(cellRangeAdd,sheet,wb);
			    	
			    	/*CellRangeAddress cellRangeAdd3 =new CellRangeAddress(row.getRowNum(),row.getRowNum()+marks.size()-1,col,col);  
			    	sheet.addMergedRegion(cellRangeAdd3);*/
				    sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(),row.getRowNum()+marks.size()-1,col,col));
				    cell = row.createCell(col++);
				    cell.setCellValue(student.getCastName());
				    cell.setCellStyle(prepareMergeAddressBoarder(borderStyle));
				    //prepareMergeAddressBoarder(cellRangeAdd,sheet,wb);
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
	    JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
	}
    }
    public CellStyle  prepareMergeAddressBoarder(CellStyle borderStyle){
    	borderStyle.setBorderBottom(CellStyle.BORDER_THIN);
    	borderStyle.setBorderLeft(CellStyle.BORDER_THIN);
    	borderStyle.setBorderRight(CellStyle.BORDER_THIN);
    	borderStyle.setBorderTop(CellStyle.BORDER_THIN);
    	return borderStyle;
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
	    JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
	}
    }


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
	    JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
	}

	return styles;
    }

	/*public static String[] getTitles() {
		return titles;
	}*/
}
