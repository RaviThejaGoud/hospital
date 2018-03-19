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
import com.churchgroup.util.string.StringFunctions;
import com.urt.persistence.model.common.CastSettings;
import com.urt.persistence.model.common.State;
import com.urt.persistence.model.common.SubCastSettings;
import com.urt.persistence.model.exam.MotherTongue;
import com.urt.persistence.model.study.ClassName;
import com.urt.persistence.model.study.SchoolCategory;
import com.urt.webapp.action.jrexception.JRExceptionClient;

public class PrepareStudentAdmissionExcel {
	
	private static final Log log = LogFactory.getLog(PrepareStudentAdmissionExcel.class);

    /** Always make sure the positions and query matches. Correct the Class and
     * Section positions. This is used to create a drop down list for classes
     * and sections */


    
    String[] titles = { "Applicationdetails Id","First Name", "Last Name", "Gender", "Class","Date Of Joining (DD-MMM-YYYY)","Date Of Birth (DD-MMM-YYYY) ", "Admitted Class Name",
    		"Father Name","Father Occupation", "Father Qualification", "Designation","Mother Name", "Mother Qualification", "Parent Email","Home Number", "Mobile Number","Street Name","City",
    		"State","Postal Code","Register Number","Roll Number","Student Unique Number","Aadhaar Card No","Category","Scholarship Information",
    		"Transport Mode","Residence Type","Mother Tongue","Place Of Birth","Religion","Community","Caste Name","Nationality",
    		"Ration Card Number","Community Number","SSLC Number","TMR Number","Last School Name","Student E-Mail","Student Mobile No","Father Annual Income","Blood Group",
    		"Teeth","Vision(Left)","Vision(Right)","Height 1","Weight 1","Oral Hygiene","Family Doctor","Preffered Hospital","Student Disability","Personal Identification 1",
    		"Personal Identification 2","Goals","Strengths","Interests & Hobbies","Responsibilities","Achivements","Transfer Certificate No","Committed Fee","Disability Details","Is Below Poverty Line (BPL)","Is RTE Student (Free Education)"};
    
    
    
    private static final Integer[] colwidths = { 10,15, 15, 15, 15, 20, 20, 15,
    	15, 15, 15, 15, 15, 15, 15, 15, 15, 15,15,
    	15,10,20,20,15,15,20,15,
    	25,15,28,15,15,15,15,15,
    	20,15,15,15,25,25,15,15,10,
    	15,15,15,15,15,15,15,15,15,25,
    	25,10,25,25,25,25,25,15,25,25};

    public static final String query = "select applicationId,firstName,lastName,gender,className,dateOfJoining,dateOfBirth,lastClassAttended,"
    	+"fatherName,occupation,fatherQualification,designation,motherName,motherQualification,parentEmail,phoneNumber,mobileNumber,caddrStreetName,caddrCity,"	
    	+"tAddrStateName,caddrPostalCode,registerNumber,rollNumber,studentUniqueNumber,aadharCardNumber,categoryName,scholarShipInfo,"
    	+"transportMode,hostelMode,motherToung,placeOfBirth,religion,castName,subCastName,nationality,"
    	+"rationCardNumber,communityNumber,sslcNumber,tmrNumber,lastSchool,studentEmail,studentMobile,annualIncome,bloodGroup,"
    	+"teeth,visionL,visionR,height,weight,oralHygiene,familyDoctor,prefferedHospital,IF(phId ='Y','Yes','No'),identification1,"
    	+ "identification2,goals,strengths,interestsAndHobbies,responsibilities,achievements,transferCertificateNo,committedFee,physicallyHandicappedDesc,IF(bplStatus ='Y','Yes','No'),IF(rteStatus ='Y','Yes','No') from vw_onlineApplicationDetails ";

    private int cpos = 4;
  //  private int spos = 4;n
    private int statepos = 19;
    private String refSheet = "";
    private int cend = 0;
    private int send = 0;
    private int stateEnd = 0;
    private int categorypos = 25;
    private int categoryEnd = 0;
    private int motherToungEnd = 0;
    private int motherToungepos = 29;
    private int  castNameEnd = 0;
    private int communityEnd =0;

    private Map<String, CellStyle> styles;
    private Workbook wb;
    private String[] transportModes = {"O", "P", "T" };
    private String[] genders = {"M", "F"};
    private String[] phIds = {"Yes", "No"};
    private String[] hostelModes = {"D", "H"};
    private String[] bloodGroups = {"a+", "a-", "a1+", "a1-", "a1b+", "a1b-", "a2+", "a2-", "a2b+", "a2b-", "b+", "b-", "ab+","o+", "o-"};
    private String[] bplArrayStr = {"Yes", "No"};
    private String[] rteArrayStr = {"Yes", "No"};

    
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


	private DataValidation createClassDropDownList(int srow, int erow) {

	DataValidation dataValidation = null;
	try {

	    CellRangeAddressList addressList = new CellRangeAddressList(srow, erow, cpos, cpos);
	    DVConstraint dvConstraint = DVConstraint.createFormulaListConstraint("ClassSections!$E$5:$E$" + cend);
	    dataValidation = new HSSFDataValidation(addressList, dvConstraint);
	    dataValidation.setSuppressDropDownArrow(false);
	    dataValidation.createPromptBox("Class", "Select from dropdown!");
	    dataValidation.setShowPromptBox(true);
	    dataValidation.setErrorStyle(DataValidation.ErrorStyle.STOP);
	    dataValidation.createErrorBox("Invalid Class", "Please select the class from the dropdown!");
	} catch (Exception ex) {
	    ex.printStackTrace();
	    JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
	}
	return dataValidation;

    }

  /*  private DataValidation createSectionDropDownList(int srow, int erow) {
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
    	    JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
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
    	    JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
    	}
    	return dataValidation;
    }
    private DataValidation createCategoryDropDownList(int srow, int erow) {
    	DataValidation dataValidation = null;
    	try {
    	    CellRangeAddressList addressList = new CellRangeAddressList(srow, erow, categorypos, categorypos);
    	    DVConstraint dvConstraint = DVConstraint.createFormulaListConstraint("ClassSections!$G$5:$G$" + categoryEnd);
    	    dataValidation = new HSSFDataValidation(addressList, dvConstraint);
    	    dataValidation.setSuppressDropDownArrow(false);
    	    dataValidation.createPromptBox("SchoolCategory", "Select Select SchoolCategory from dropdown!");
    	    dataValidation.setShowPromptBox(true);
    	    dataValidation.setErrorStyle(DataValidation.ErrorStyle.STOP);
    	    dataValidation.createErrorBox("Invalid SchoolCategory", "Please select the Select SchoolCategory from the dropdown!");
    	} catch (Exception ex) {
    	    ex.printStackTrace();
    	    JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
    	}
    	return dataValidation;
     }
    private DataValidation createMotherToungeDropDownList(int srow, int erow) {
    	DataValidation dataValidation = null;
    	try {
    	    CellRangeAddressList addressList = new CellRangeAddressList(srow, erow, motherToungepos, motherToungepos);
    	    DVConstraint dvConstraint = DVConstraint.createFormulaListConstraint("ClassSections!$I$5:$I$" + motherToungEnd);
    	    dataValidation = new HSSFDataValidation(addressList, dvConstraint);
    	    dataValidation.setSuppressDropDownArrow(false);
    	    dataValidation.createPromptBox("MotherTounge", "Select Select MotherTounge from dropdown!");
    	    dataValidation.setShowPromptBox(true);
    	    dataValidation.setErrorStyle(DataValidation.ErrorStyle.STOP);
    	    dataValidation.createErrorBox("Invalid MotherTounge", "Please select the Select MotherTounge from the dropdown!");
    	} catch (Exception ex) {
    	    ex.printStackTrace();
    	    JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
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
    	    JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
    	}
    	return dataValidation;
    }
   private DataValidation createphIdsDropDownList(int srow, int erow) {
   	DataValidation dataValidation = null;
   	try {
   	    CellRangeAddressList addressList = new CellRangeAddressList(srow, erow, 52,52);
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
   	 JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
   	}
   	return dataValidation;
   }
   
   private DataValidation createHostelDropDownList(int srow, int erow) {
	   	DataValidation dataValidation = null;
	   	try {
	   	    CellRangeAddressList addressList = new CellRangeAddressList(srow, erow, 28,28);
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
	   	 JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
	   	}
	   	return dataValidation;
	   }
   
    
    private DataValidation createBloodGroupList(int srow, int erow) {
    	DataValidation dataValidation = null;
    	try {
    	    CellRangeAddressList addressList = new CellRangeAddressList(srow, erow, 43, 43);
    	    DVConstraint dvConstraint = DVConstraint.createExplicitListConstraint(bloodGroups);
    	    dataValidation = new HSSFDataValidation(addressList, dvConstraint);
    	    dataValidation.setSuppressDropDownArrow(false);   
    	    dataValidation.createPromptBox("Blood Group", "Select bloodGroups  from dropdown!\n(For 'a+' : 'a+', 'a-' : 'a-', 'a1+' : 'a1+' , 'a1-' : 'a1-' ,'a1b+' : 'a1b+' ,'a1b-' : 'a1b-' ,'a2+' : 'a2+' ,'a2-' : 'a2-' ,'a2b+' : 'a2b+' ,'a2b-' : 'a2b-' ,'ab+' : 'ab+' ,'b+' : 'b+' ,'b-' : 'b-' ,'o+' : 'o+')");
    	    dataValidation.setShowPromptBox(true);
    	    dataValidation.setErrorStyle(DataValidation.ErrorStyle.STOP);
    	    dataValidation.createErrorBox("Invalid Blood Group", "Please select the bloodGroups from the dropdown!");
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

    private DataValidation createcommunityDropDownList(int srow, int erow) {
    	DataValidation dataValidation = null;
    	try {
    	    CellRangeAddressList communityList = new CellRangeAddressList(srow, erow, 32, 32);
    	    DVConstraint dvConstraint = DVConstraint.createFormulaListConstraint("ClassSections!$K$5:$K$" + communityEnd);
    	    dataValidation = new HSSFDataValidation(communityList, dvConstraint);
    	    dataValidation.setSuppressDropDownArrow(false);
    	    dataValidation.createPromptBox("Community", "Select Community from dropdown!");
    	    dataValidation.setShowPromptBox(true);
    	    dataValidation.setErrorStyle(DataValidation.ErrorStyle.STOP);
    	    dataValidation.createErrorBox("Invalid Community", "Please Select Community from the dropdown!");
    	} catch (Exception ex) {
    	    ex.printStackTrace();
    	    JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
    	}
    	return dataValidation;
     }
    
    private DataValidation createCastNameDropDownList(int srow, int erow) {
    	DataValidation dataValidation = null;
    	try {
    	    CellRangeAddressList castList = new CellRangeAddressList(srow, erow, 33, 33);
    	    DVConstraint dvConstraint = DVConstraint.createFormulaListConstraint("ClassSections!$M$5:$M$" + castNameEnd);
    	    dataValidation = new HSSFDataValidation(castList, dvConstraint);
    	    dataValidation.setSuppressDropDownArrow(false);
    	    dataValidation.createPromptBox("Cast", "Select Cast Name from dropdown!");
    	    dataValidation.setShowPromptBox(true);
    	    dataValidation.setErrorStyle(DataValidation.ErrorStyle.STOP);
    	    dataValidation.createErrorBox("Invalid Cast", "Please select Cast Name from the dropdown!");
    	} catch (Exception ex) {
    	    ex.printStackTrace();
    	    JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
    	}
    	return dataValidation;
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
	    sheet.addValidationData(createphIdsDropDownList(2, lastNumber));
	    sheet.addValidationData(createBloodGroupList(2, lastNumber));
	    sheet.addValidationData(createHostelDropDownList(2, lastNumber));
	    sheet.addValidationData(createCategoryDropDownList(2,lastNumber));
	    sheet.addValidationData(createMotherToungeDropDownList(2,lastNumber));
	    sheet.addValidationData(createBPLDropDownList(2, lastNumber));
	    sheet.addValidationData(createRTEDropDownList(2, lastNumber));
	    sheet.addValidationData(createcommunityDropDownList(2, lastNumber));
	    sheet.addValidationData(createCastNameDropDownList(2, lastNumber));
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
	    JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
	}

    }

    public void createStudentSheet(String sheetName, List<Object[]> students,String sheetTitleDesc,String committedFeeStatus) {

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
	    
	    if(!StringFunctions.isNullOrEmpty(committedFeeStatus)){
	    	if(committedFeeStatus.equalsIgnoreCase("N")){
	    		 sheet.setColumnHidden(61, true);	
	    	}
		}

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

    public void createClassSectionSheet(String sheetName, List<ClassName> classList,
	    List<State> states,long academicYear, long custId,String admissionsByFee,String applicationStatus,List<SchoolCategory> schoolCategory,List<MotherTongue> motherTongues,List<CastSettings> communityList,List<SubCastSettings> castNameList) {
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
	    //ClassSection Details
	    if (ObjectFunctions.isNotNullOrEmpty(classList)) {
	    	rownum=2;
		    row = sheet.getRow(rownum);
		    if(ObjectFunctions.isNullOrEmpty(row)){
		    	row = sheet.createRow(rownum);
		    }
		    createSheetTitle(row, "School Name : Class and Section Names");
		    row.setHeightInPoints(40);
		    cellnum=3;
		    row = sheet.getRow(++rownum);
		    if(ObjectFunctions.isNullOrEmpty(row)){
		    	row = sheet.createRow(rownum);
		    }
		    headerCell = row.createCell(cellnum++);
		    headerCell.setCellValue("Class Id");
		    headerCell.setCellStyle(styles.get("header"));
		    headerCell = row.createCell(cellnum++);
		    headerCell.setCellValue("Class Name");
		    headerCell.setCellStyle(styles.get("header"));
		    rownum++;
		    for (ClassName clazz : classList) {
			    cellnum = 3;
			    row = sheet.getRow(rownum);
			    if(ObjectFunctions.isNullOrEmpty(row)){
			    	row = sheet.createRow(rownum);
			    }
			    cell = row.createCell(cellnum++);
			    cell.setCellValue(clazz.getId());
			    cell = row.createCell(cellnum++);
			   	cell.setCellValue(clazz.getClassName());
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
		    cellnum=5;
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
			    cellnum = 5;
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
	    categoryEnd = rownum;
	    // MotherToung Details
	    
	    if (ObjectFunctions.isNotNullOrEmpty(motherTongues)) {
	    	rownum=2;
		    row = sheet.getRow(rownum);
		    if(ObjectFunctions.isNullOrEmpty(row)){
		    	row = sheet.createRow(rownum);
		    }
		    createSheetTitle(row, "MotherToung Names");
		    row.setHeightInPoints(40);
		    cellnum=7;
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
			    cellnum = 7;
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
	    if (ObjectFunctions.isNotNullOrEmpty(communityList)) {
	    	rownum=2;
		    row = sheet.getRow(rownum);
		    if(ObjectFunctions.isNullOrEmpty(row)){
		    	row = sheet.createRow(rownum);
		    }
		    createSheetTitle(row, "community List Names");
		    row.setHeightInPoints(40);
		    cellnum=9;
		    row = sheet.getRow(++rownum);
		    if(ObjectFunctions.isNullOrEmpty(row)){
		    	row = sheet.createRow(rownum);
		    }
		    headerCell = row.createCell(cellnum++);
		    headerCell.setCellValue("community Id");
		    headerCell.setCellStyle(styles.get("header"));
		    headerCell = row.createCell(cellnum++);
		    headerCell.setCellValue("Community Name");
		    headerCell.setCellStyle(styles.get("header"));
		    rownum++;
			for (CastSettings obj : communityList) {
			    cellnum = 9;
			    row = sheet.getRow(rownum);
			    if(ObjectFunctions.isNullOrEmpty(row)){
			    	row = sheet.createRow(rownum);
			    }
			    cell = row.createCell(cellnum++);
			    cell.setCellValue(obj.getId());
			    cell = row.createCell(cellnum++);
			    cell.setCellValue(obj.getCastName());
			    rownum++;
			}
	    }
	    communityEnd = rownum;
	    
	    if (ObjectFunctions.isNotNullOrEmpty(castNameList)) {
	    	rownum=2;
		    row = sheet.getRow(rownum);
		    if(ObjectFunctions.isNullOrEmpty(row)){
		    	row = sheet.createRow(rownum);
		    }
		    createSheetTitle(row, "Cast List Names");
		    row.setHeightInPoints(40);
		    cellnum=11;
		    row = sheet.getRow(++rownum);
		    if(ObjectFunctions.isNullOrEmpty(row)){
		    	row = sheet.createRow(rownum);
		    }
		    headerCell = row.createCell(cellnum++);
		    headerCell.setCellValue("Cast Id");
		    headerCell.setCellStyle(styles.get("header"));
		    headerCell = row.createCell(cellnum++);
		    headerCell.setCellValue("Cast Name");
		    headerCell.setCellStyle(styles.get("header"));
		    rownum++;
			for (SubCastSettings obj : castNameList) {
			    cellnum = 11;
			    row = sheet.getRow(rownum);
			    if(ObjectFunctions.isNullOrEmpty(row)){
			    	row = sheet.createRow(rownum);
			    }
			    cell = row.createCell(cellnum++);
			    cell.setCellValue(obj.getId());
			    cell = row.createCell(cellnum++);
			    cell.setCellValue(obj.getSubCastName());
			    rownum++;
			}
	    }
	    castNameEnd = rownum;
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
	    JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
	}

	return styles;
    }
    private DataValidation createBPLDropDownList(int srow, int erow) {
       	DataValidation dataValidation = null;
       	try {
       	    CellRangeAddressList addressList = new CellRangeAddressList(srow, erow, 63,63);
       	    DVConstraint dvConstraint = DVConstraint.createExplicitListConstraint(bplArrayStr);
       	    dataValidation = new HSSFDataValidation(addressList, dvConstraint);
       	    dataValidation.setSuppressDropDownArrow(false);
       	    dataValidation.createPromptBox("BPL Status", "Select BPL Status from dropdown!\n(For 'True' : 'Yes', 'False' : 'No')");
       	    dataValidation.setShowPromptBox(true);
       	    dataValidation.setErrorStyle(DataValidation.ErrorStyle.STOP);
       	    dataValidation
       		    .createErrorBox("Invalid BPL Status", "Please select the BPL Status from the dropdown!");
       	} catch (Exception ex) {
       	    ex.printStackTrace();
       	 JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
       	}
       	return dataValidation;
       }
    private DataValidation createRTEDropDownList(int srow, int erow) {
       	DataValidation dataValidation = null;
       	try {
       	    CellRangeAddressList addressList = new CellRangeAddressList(srow, erow, 64,64);
       	    DVConstraint dvConstraint = DVConstraint.createExplicitListConstraint(rteArrayStr);
       	    dataValidation = new HSSFDataValidation(addressList, dvConstraint);
       	    dataValidation.setSuppressDropDownArrow(false);
       	    dataValidation.createPromptBox("RTE Status", "Select RTE Status from dropdown!\n(For 'True' : 'Yes', 'False' : 'No')");
       	    dataValidation.setShowPromptBox(true);
       	    dataValidation.setErrorStyle(DataValidation.ErrorStyle.STOP);
       	    dataValidation
       		    .createErrorBox("Invalid RTE Status", "Please select the RTE Status from the dropdown!");
       	} catch (Exception ex) {
       	    ex.printStackTrace();
       	 JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
       	}
       	return dataValidation;
       }
}
