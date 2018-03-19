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
import com.urt.persistence.model.common.CastSettings;
import com.urt.persistence.model.common.HouseType;
import com.urt.persistence.model.common.State;
import com.urt.persistence.model.common.SubCastSettings;
import com.urt.persistence.model.exam.MotherTongue;
import com.urt.persistence.model.study.SchoolCategory;
import com.urt.persistence.model.study.ViewClassSectionDetails;
import com.urt.webapp.action.jrexception.JRExceptionClient;

public class PrepareStudentExcel {
	
	private static final Log log = LogFactory.getLog(PrepareStudentExcel.class);

    /** Always make sure the positions and query matches. Correct the Class and
     * Section positions. This is used to create a drop down list for classes
     * and sections */

    private static final String[] titles = { "Student Id", "Admission Number", "Register Number","Roll Number","Student Unique Number","First Name", "Last Name",
    										 " Date Of Birth  (DD-MMM-YYYY)", "Gender","Class & Section","Date Of Joining (DD-MMM-YYYY)", "Is RTE Student",
    										 "Admitted Class Name", "Mode of Transport", "Father Name","Father Occupation", "Mother Name",
    										 "Mother Occupation", "Annual Income(Father)", "Is Below Poverty Line (BPL)", "Is Below Poverty Line Number","Primary Mobile Number", "Secondary Mobile Number",
    										 "Home Number", "Parent Email(Father)", "Address Line 1", "Address Line 2","City",
    										 "District","State", "Postal Code","Student E-Mail","Student Mobile No",
    										 "Enrollment Code","Personal Identification 1", "Personal Identification 2","Mother Tongue",
    										 "Place Of Birth", "Nationality","Religion", "Community","Caste Name",
    										 "Fee Category", "Scholarship Eligibility","Scholarship Amount","Ration Card Number","Aadhaar Card No",
    										 "Is New Student (Y/N)", "Is Hosteler","Blood Group", "Height 1","Height 2",
    										 "Weight 1", "Weight 2","Vision(Right)", "Vision(Left)","Teeth", 
    										 "Student Disability","Disability Details","Father Aadhaar Card No", "No of Dependents", "Mother Aadhaar Card No", 
    										 "Father Qualification", "Name of the Organization(Father)", 
    										 "Designation(Father)", "Self Employed(Father)", "Nature of Business(Father)",  
    										 "Mother Qualification", "Name of the Organization(Mother)",  "Designation(Mother)", "Self Employed(Mother)", 
    										 "Nature of Business(Mother)", "Annual Income(Mother)", "Parent Email(Mother)", "Is Correspondence Address Same As Residential Address", "Correspondence Address Line 1", 
    										 "Correspondence Address Line 2", "Correspondence City", "Correspondence District", "Correspondence State", "Correspondence Postal Code",  
    										 "Previous School Name", "Is ST/SC Community", "Sibling Name1", "Sibling Name2", "Sibling Name3", "Allergies", "Heart Problem", "Diabetes", "Asthma", "Other Medical Condition", "Family Doctor", "Doctor's Contact No",
    										 "Emergency No1", "Emergency No2","House","Student BankId","STS Number"};
    	 

    private static final Integer[] colwidths = { 7, 20,	20,	20, 25,
    											30, 20, 18, 15, 18,
    											18, 20,	25, 20, 30,
    											20, 30, 20, 25, 25,
    											25,30,  20, 30, 30, 
    											30, 20,	20, 20, 20, 
    											30, 30,	20, 30, 30, 
    											20,	20, 20, 20, 20, 
    											20,	20, 25, 20, 25,
    											18,	18,25,18,25,18,
    											20, 22,25,25,20,20,
    											20,20,20,20,20,
    											20,25,20,20,20,
    											20,20,27,27,25,
    											20,20,20,20,20,30,
    											20,20,20,20,20,
    											20,20,20,20,20,
    											20,18,15,15,20,
    											20,20,20,20,20,15,20,20};

     
     public static final String query = "select studentId,admissionNumber,registerNumber,rollNumber,studentUniqueNumber,firstName,lastName,dateOfBirth,gender,"
    		    + "CAST(CONCAT(IF(className IS NULL, '', className),IF(section IS NULL || section <=> '','',CONCAT('-', section))) AS CHAR(60)) as classNameAndSection,dateOfJoining," 
    		    + "rteStatus,classJoined,transportMode,fatherName,fatherOccupation,motherName,motherOccupation,annualIncome,bplStatus,bplNumber,mobileNumber,secondaryMobileNumber,phoneNumber,parentEmail,"
    		    + "addressLine1,addressLine2,city,districtName,stateName,postalCode,studentEmail,studentMobile,enrollmentCode,identification1,identification2,motherToung,placeOfBirth,"
    		    + "nationality,religion,castName,subCastName,categoryName,scholarShipInfo,scholarShipAmount,rationCardNumber,aadharNumber,joinedThroughAdmissions,hostelMode,bloodGroup,"
    		    + "height,height2,weight,weight2,visionR,visionL,teeth,phId,physicallyHandicappedDesc,fatherAadharNumber,noOfDependents,motherAadharNumber, fatherQualification, fatherOrganizationName,"
    		    + "fatherDesignation, fatherSelfEmployed, fatherNatureofBusiness, motherQualification, motherOrganizationName, motherDesignation, motherSelfEmployed, motherNatureofBusiness, motherAnnualIncome,motherEmail,"
    		    + "sameAsResidentialAddress, corAddressLine1, corAddressLine2, corCity, corDistrictName, corStateName, corPostalCode, lastSchool, scstCommunity, siblingName1, siblingName2, siblingName3, allergies, heartProblem, diabetes, asthma, "
    		    + "otherMedicalCondition, familyDoctor, doctorsContactNo, emergencyNo1, emergencyNo2 ,houseType,studentBankId,stsNumber "
    		    + "from vw_importStudentDetails";

     
    private int cend = 0;
    private int send = 0;
    private int stateEnd = 0;
    private int motherToungEnd = 0;
    private int schoolCategoryEnd = 0;
    private int communityEnd = 0;
    private int castNameEnd = 0;
    private int houseTypeEnd = 0;
     // private int BplStatusEnd = 0;
    //private int RteStatusEnd = 0;

    
	private Map<String, CellStyle> styles;
    private Workbook wb;
    private String[] transportModes = {"O", "P", "T" };
    private String[] genders = {"M", "F"};
    private String[] phIds = {"YES", "NO"};
    private String[] hostelModes = {"D", "H"};
    private String[] isNewStudent = {"YES", "NO"};
    private String[] bloodGroups = {"A+", "A-", "A1+", "A1-", "A1B+", "A1B-", "A2+", "A2-", "A2B+", "A2B-", "B+", "B-", "AB+","AB-","O+", "O-"};
    private String[] outSideSchoolDutyArry = {"N", "Y"};
    private String[] addressTypes = {"R", "C"};
    private String[] schoolMess = {"YES", "NO"};
    private String[] PTAStatusArry = {"YES", "NO"};
    private String[] BPLStatusArry = {"YES", "NO"};
    private String[] RTEStatusArry = {"YES", "NO"};
   
    
    
    
    public PrepareStudentExcel() {
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

    private DataValidation createDropDownListFromExplicitList(int srow, int erow,int cpos,String[] listValues,String promptBoxTitle,String promptBoxText,String errorBoxTitle,String errorBoxText) {
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
    	    JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
    	}
    	return dataValidation;
    }
    
    private DataValidation createOutSideSchoolWorkDropDownList(int srow, int erow) {
    	DataValidation dataValidation = null;
    	try {
    	    CellRangeAddressList addressList = new CellRangeAddressList(srow, erow, 57,57);
    	    DVConstraint dvConstraint = DVConstraint.createExplicitListConstraint(outSideSchoolDutyArry);
    	    dataValidation = new HSSFDataValidation(addressList, dvConstraint);
    	    dataValidation.setSuppressDropDownArrow(false);
    	    dataValidation.createPromptBox("outSideSchoolDutyArry", "Select Outside School Work from dropdown!\n(For 'NO' : 'N', 'YES' : 'Y')");
    	    dataValidation.setShowPromptBox(true);
    	    dataValidation.setErrorStyle(DataValidation.ErrorStyle.STOP);
    	    dataValidation.createErrorBox("Invalid Outside School Duty Arry", "Please select the Outside School Duty Arry from the dropdown!");
    	} catch (Exception ex) {
    	    ex.printStackTrace();
    	    JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
    	}
    	return dataValidation;
    }
    private DataValidation createPTAStatusDropDownList(int srow, int erow) {
    	DataValidation dataValidation = null;
    	try {
    	    CellRangeAddressList addressList = new CellRangeAddressList(srow, erow, 76,76);
    	    DVConstraint dvConstraint = DVConstraint.createExplicitListConstraint(PTAStatusArry);
    	    dataValidation = new HSSFDataValidation(addressList, dvConstraint);
    	    dataValidation.setSuppressDropDownArrow(false);
    	    dataValidation.createPromptBox("PTAStatusArry", "Select PTA Status from dropdown!\n(For 'NO' : 'N', 'YES' : 'Y')");
    	    dataValidation.setShowPromptBox(true);
    	    dataValidation.setErrorStyle(DataValidation.ErrorStyle.STOP);
    	    dataValidation.createErrorBox("Invalid PTA Status", "Please select the PTA Status from the dropdown!");
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
	    sheet.addValidationData(createDropDownList(2, lastNumber, 9, "ClassSections!$G$5:$G$" + cend, "Class", "Select from dropdown!", "Invalid Class", "Please select the class from the dropdown!"));
	    sheet.addValidationData(createDropDownList(2, lastNumber,29,"ClassSections!$B$5:$B$" + stateEnd,"States", "Select States from dropdown!","Invalid States", "Please select the states from the dropdown!"));
	    sheet.addValidationData(createDropDownList(2, lastNumber,36,"ClassSections!$E$5:$E$" + motherToungEnd,"Mother Tongue", "Select Mother Tongue from dropdown!","Invalid Mother Tongue", "Please select the Mother Tongue from the dropdown!"));
	    sheet.addValidationData(createDropDownListFromExplicitList(2, lastNumber,13,transportModes,"Transport Mode", "Select Transport Mode from dropdown!\n(For 'Own' : 'O', 'Private' : 'P', 'School Transport' : 'T')","Invalid Transport Mode", "Please select the transport mode from the dropdown!"));
	    sheet.addValidationData(createDropDownListFromExplicitList(2, lastNumber,48,hostelModes,"Is Hosteler", "Select Is Hosteler from dropdown!\n(For 'Day Scholar' : 'D', 'Hostler' : 'H')","Invalid Is Hosteler", "Please select the Is Hosteler from the dropdown!"));
	    sheet.addValidationData(createDropDownListFromExplicitList(2, lastNumber,47,isNewStudent,"Is New Student", "Select Student Type from dropdown!\n(For 'New Student' : 'YES', 'Old Student' : 'NO')","Invalid Student Type", "Please select the Student Type from the dropdown!"));
	    sheet.addValidationData(createDropDownListFromExplicitList(2, lastNumber,8,genders,"Gender", "Select Gender from dropdown!\n(For 'Male' : 'M', 'Female' : 'F')","Invalid Gender", "Please select the gender from the dropdown!"));
	   
	    sheet.addValidationData(createDropDownList(2, lastNumber, 40, "ClassSections!$K$5:$K$" + communityEnd, "Community", "Select from dropdown!", "Invalid Community", "Please select the Community from the dropdown!"));
	    sheet.addValidationData(createDropDownList(2, lastNumber, 41, "ClassSections!$M$5:$M$" + castNameEnd, "Caste Name", "Select from dropdown!", "Invalid Caste Name", "Please select the Caste Name from the dropdown!"));
	    
	    sheet.addValidationData(createDropDownListFromExplicitList(2, lastNumber,43,PTAStatusArry,"Scholarship Eligibility", "Select Scholarship Eligibility from dropdown!\n(For 'YES' : 'Y', 'NO' : 'N')","Invalid Gender", "Please select the gender from the dropdown!"));
	    
	    sheet.addValidationData(createDropDownListFromExplicitList(2, lastNumber,57,phIds,"Physically Handicapped", "Select Physically Handicapped from dropdown!\n(For 'True' : 'YES', 'False' : 'NO')","Invalid Physically Handicapped", "Please select the phId from the dropdown!"));		
	    sheet.addValidationData(createDropDownListFromExplicitList(2, lastNumber,49,bloodGroups,"Blood Group", "Select bloodGroups  from dropdown!\n(For 'A+' : 'A+', 'A-' : 'A-', 'A1+' : 'A1+' , 'A1-' : 'A1-' ,'A1B+' : 'A1B+' ,'A1B-' : 'A1B-' ,'A2+' : 'A2+' ,'A2-' : 'A2-' ,'A2B+' : 'A2B+' ,'A2B-' : 'A2B-' ,'AB+' : 'AB+' ,'B+' : 'B+' ,'B-' : 'B-' ,'O+' : 'O+')",
	    		"Invalid Blood Group", "Please select the bloodGroups from the dropdown!"));
	    sheet.addValidationData(createDropDownList(2, lastNumber,42,"ClassSections!$I$5:$I$" + schoolCategoryEnd,"Category", "Select SchoolCategory from dropdown!","Invalid SchoolCategory", "Please select the Category from the dropdown!"));
	    //sheet.addValidationData(createDropDownListFromExplicitList(2, lastNumber,70,addressTypes,"Communication Type", "Select Communication Type from dropdown!\n(For 'Residential' : 'R', 'Correspondence' : 'C')","Invalid Communication Type", "Please select the communication Type from the dropdown!"));
	    //sheet.addValidationData(createDropDownListFromExplicitList(2, lastNumber,74,schoolMess,"School Mess", "Select School Mess from dropdown!\n(For 'Yes' : 'Y', 'No' : 'N')","Invalid School Mess", "Please select the school Mess from the dropdown!"));


	   // sheet.addValidationData(createDropDownList(2, lastNumber,68,"ClassSections!$G$5:$G$" + cend, "Class", "Select from dropdown!", "Invalid Class", "Please select the class from the dropdown!"));
	    //sheet.addValidationData(createOutSideSchoolWorkDropDownList(2, lastNumber));
	   // sheet.addValidationData(createPTAStatusDropDownList(2, lastNumber));
	    sheet.addValidationData(createBPLStatusDropDownList(2, lastNumber));
	    sheet.addValidationData(createRTEStatusDropDownList(2, lastNumber));
	    
	    sheet.addValidationData(createDropDownList(2, lastNumber,79,"ClassSections!$B$5:$B$" + stateEnd,"States", "Select States from dropdown!","Invalid States", "Please select the states from the dropdown!"));
	    //ScstStatusDropDownList
	    sheet.addValidationData(createYesorNoStatusDropDownList(2, lastNumber, 82, 82, "SC/ST"));
	    //SlefEmployedFatherStatusDropDownList
	    sheet.addValidationData(createYesorNoStatusDropDownList(2, lastNumber, 65, 65, "Self Employed Father"));
	    //SlefEmployedMotherStatusDropDownList
	    sheet.addValidationData(createYesorNoStatusDropDownList(2, lastNumber, 70, 70, "Self Employed Mother"));
	    //AllergiesStatusDropDownList
	    sheet.addValidationData(createYesorNoStatusDropDownList(2, lastNumber, 86, 86, "Allergies"));
	    //HeartProblemStatusDropDownList
	    sheet.addValidationData(createYesorNoStatusDropDownList(2, lastNumber, 87, 87, "Heart Problem"));
	    //DiabetesStatusDropDownList
	    sheet.addValidationData(createYesorNoStatusDropDownList(2, lastNumber, 88, 88, "Diabetes"));
	    //AsthmaStatusDropDownList
	    sheet.addValidationData(createYesorNoStatusDropDownList(2, lastNumber, 89, 89, "Asthma"));
	    //Correspondence Address DropDownList
	    sheet.addValidationData(createYesorNoStatusDropDownList(2, lastNumber, 74, 74, "Correspondence Address"));
	    
	   // sheet.addValidationData(createDropDownList(2, lastNumber,77,"ClassSections!$K$5:$K$" + BplStatusEnd,"BPL Status", "Select BPL Status from dropdown!","Invalid BPL Status", "Please select the BPL Status from the dropdown!"));
	   // sheet.addValidationData(createDropDownList(2, lastNumber,78,"ClassSections!$M$5:$M$" + RteStatusEnd,"RTE Status", "Select RTE Status from dropdown!","Invalid RTE Status", "Please select the RTE Status from the dropdown!"));
	    sheet.addValidationData(createDropDownList(2, lastNumber, 95, "ClassSections!$O$5:$O$" + houseTypeEnd, "House Type", "Select from dropdown!", "Invalid House Type", "Please select the House Type from the dropdown!"));
	   
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
	    headerRow.setHeightInPoints(40);
	    Cell headerCell;
	    for (int i = 0; i < titles.length; i++) {
	    	if(i == 1 || i == 5 || i == 9){
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
    
   /* private void createSheetHeaderForMandory(Row headerRow) {
    	try {
    	    headerRow.setHeightInPoints(40);
    	    Cell headerCell;
    	    for (int i = 0; i < titles.length; i++) {
    		headerCell = headerRow.createCell(i);
    		headerCell.setCellValue(titles[i]);
    		headerCell.setCellStyle(styles.get("headers"));
    	    }
    	} catch (Exception ex) {
    	    ex.printStackTrace();
    	}

        }*/

    public void createStudentSheet(String sheetName, List<Object[]> students,String sheetTitleDesc,String committedFeeStatus) {

	try {

	    HSSFSheet sheet = (HSSFSheet) wb.createSheet(sheetName);
	    sheet.createFreezePane(7, 2);
	    log.debug("sheet name : " + sheetName);
	    PrintSetup printSetup = sheet.getPrintSetup();
	    printSetup.setLandscape(true);
	    sheet.setFitToPage(true);
	    sheet.setHorizontallyCenter(true);
	    int rownum = 0;
	    createSheetTitle(sheet.createRow(rownum++), sheetTitleDesc);
	    createSheetHeader(sheet.createRow(rownum++));
	    sheet.addMergedRegion(CellRangeAddress.valueOf("$A$1:$CR$1"));
	    sheet.setAutoFilter(new CellRangeAddress(rownum - 1, rownum - 1, 0, titles.length - 1));
	    sheet.setColumnHidden(13, true);	
	    /*if(!StringFunctions.isNullOrEmpty(committedFeeStatus)){
	    	if(committedFeeStatus.equalsIgnoreCase("N")){
	    		 sheet.setColumnHidden(58, true);	
	    	}
		}*/
	    
	    Row row;
	    if (!ObjectFunctions.isNullOrEmpty(students)) {
			for (Object[] obj : students) {
			    row = sheet.createRow(rownum++);
			    writeStudentRow(obj, row);
			}
	    }
	    // finally set column widths, the width is measured in units of
	    // 1/256th of a character width
	    //Hide the student id because some this id is not required to show the user 
	    sheet.setColumnHidden(0, true);
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

    public void createClassSectionSheet(String sheetName, List<ViewClassSectionDetails> clazzSection,
	    List<State> states,List<MotherTongue> motherTongues,long academicYear, long custId,List<SchoolCategory> schoolCategory,List<CastSettings> communityList,List<SubCastSettings> castNameList
	    ,List<HouseType> houseTypeList) {
	try {
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
	    
	 // MotherToung Details
	    if (ObjectFunctions.isNotNullOrEmpty(motherTongues)) {
	    	rownum=2;
		    row = sheet.getRow(rownum);
		    if(ObjectFunctions.isNullOrEmpty(row)){
		    	row = sheet.createRow(rownum);
		    }
		    createSheetTitle(row, "MotherToung Names");
		    row.setHeightInPoints(40);
		    cellnum=3;
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
	    
	    //ClassSection Details
	    if (ObjectFunctions.isNotNullOrEmpty(clazzSection)) {
	    	rownum=2;
		    row = sheet.getRow(rownum);
		    if(ObjectFunctions.isNullOrEmpty(row)){
		    	row = sheet.createRow(rownum);
		    }
		    createSheetTitle(row, "School Name : Class and Section Names");
		    row.setHeightInPoints(40);
		    cellnum=5;
		    row = sheet.getRow(++rownum);
		    if(ObjectFunctions.isNullOrEmpty(row)){
		    	row = sheet.createRow(rownum);
		    }
		    headerCell = row.createCell(cellnum++);
		    headerCell.setCellValue("Class Section Id");
		    headerCell.setCellStyle(styles.get("header"));
		    headerCell = row.createCell(cellnum++);
		    headerCell.setCellValue("Class Name & Section");
		    headerCell.setCellStyle(styles.get("header"));
		    rownum++;
			for (ViewClassSectionDetails obj : clazzSection) {
			    cellnum = 5;
			    row = sheet.getRow(rownum);
			    if(ObjectFunctions.isNullOrEmpty(row)){
			    	row = sheet.createRow(rownum);
			    }
			    cell = row.createCell(cellnum++);
			    cell.setCellValue(obj.getClassSectionId());
			    cell = row.createCell(cellnum++);
			    if(!ObjectFunctions.isNullOrEmpty(obj.getSection())){
			    	cell.setCellValue(obj.getClassName()+"-"+obj.getSection());
			    }else {
			    	cell.setCellValue(obj.getClassName());
				}
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
		    cellnum=7;
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
			    cellnum = 7;
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
	    schoolCategoryEnd = rownum;
	    
	    
	    
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
	    
	    if (ObjectFunctions.isNotNullOrEmpty(houseTypeList)) {
	    	rownum=2;
		    row = sheet.getRow(rownum);
		    if(ObjectFunctions.isNullOrEmpty(row)){
		    	row = sheet.createRow(rownum);
		    }
		    createSheetTitle(row, "House Type List");
		    row.setHeightInPoints(40);
		    cellnum=13;
		    row = sheet.getRow(++rownum);
		    if(ObjectFunctions.isNullOrEmpty(row)){
		    	row = sheet.createRow(rownum);
		    }
		    headerCell = row.createCell(cellnum++);
		    headerCell.setCellValue("HouseType Id");
		    headerCell.setCellStyle(styles.get("header"));
		    headerCell = row.createCell(cellnum++);
		    headerCell.setCellValue("HouseType");
		    headerCell.setCellStyle(styles.get("header"));
		    rownum++;
			for (HouseType obj : houseTypeList) {
			    cellnum = 13;
			    row = sheet.getRow(rownum);
			    if(ObjectFunctions.isNullOrEmpty(row)){
			    	row = sheet.createRow(rownum);
			    }
			    cell = row.createCell(cellnum++);
			    cell.setCellValue(obj.getId());
			    cell = row.createCell(cellnum++);
			    cell.setCellValue(obj.getType().trim());
			    rownum++;
			}
	    }
	    houseTypeEnd = rownum;
	
	    
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
	    style.setLocked(false);
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
        
        Font monthFontBold = wbl.createFont();
        monthFontBold.setFontHeightInPoints((short) 11);
        monthFontBold.setColor(IndexedColors.WHITE.getIndex());
        monthFontBold.setBoldweight(Font.BOLDWEIGHT_BOLD);
	    style = wbl.createCellStyle();
	    style.setAlignment(CellStyle.ALIGN_CENTER);
	    style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
	    style.setAlignment(CellStyle.ALIGN_CENTER);
	    style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
	    style.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
	    style.setFillPattern(CellStyle.SOLID_FOREGROUND);
	    style.setFont(monthFontBold);
	    style.setWrapText(true);
	    style.setRightBorderColor(IndexedColors.WHITE.getIndex());
	    style.setBorderRight(CellStyle.BORDER_THIN);
	    style.setLeftBorderColor(IndexedColors.WHITE.getIndex());
	    style.setBorderLeft(CellStyle.BORDER_THIN);
	    style.setTopBorderColor(IndexedColors.WHITE.getIndex());
	    style.setBorderTop(CellStyle.BORDER_THIN);
	    style.setBottomBorderColor(IndexedColors.WHITE.getIndex());
	    style.setBorderBottom(CellStyle.BORDER_THIN);
	    styles.put("headerBold", style);
	    
	    Font totalFontBold = wbl.createFont();
	    totalFontBold.setFontHeightInPoints((short) 10);
	    totalFontBold.setColor(IndexedColors.BLACK.getIndex());
	    totalFontBold.setBoldweight(Font.BOLDWEIGHT_BOLD);
	    style = wbl.createCellStyle();
	    style.setAlignment(CellStyle.ALIGN_CENTER);
	    style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
	    style.setAlignment(CellStyle.ALIGN_CENTER);
	    style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
	    style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
	    style.setFillPattern(CellStyle.SOLID_FOREGROUND);
	    style.setFont(totalFontBold);
	    style.setWrapText(true);
	    style.setRightBorderColor(IndexedColors.BLACK.getIndex());
	    style.setBorderRight(CellStyle.BORDER_THIN);
	    style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
	    style.setBorderLeft(CellStyle.BORDER_THIN);
	    style.setTopBorderColor(IndexedColors.BLACK.getIndex());
	    style.setBorderTop(CellStyle.BORDER_THIN);
	    style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
	    style.setBorderBottom(CellStyle.BORDER_THIN);
	    styles.put("totalBold", style);
	    
	     totalFontBold = wbl.createFont();
	    totalFontBold.setFontHeightInPoints((short) 10);
	    totalFontBold.setColor(IndexedColors.BLACK.getIndex());
	    totalFontBold.setBoldweight(Font.BOLDWEIGHT_BOLD);
	    style = wbl.createCellStyle();
	    style.setAlignment(CellStyle.ALIGN_CENTER);
	    style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
	    style.setAlignment(CellStyle.ALIGN_CENTER);
	    style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
	    style.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
	    style.setFillPattern(CellStyle.SOLID_FOREGROUND);
	    style.setFont(totalFontBold);
	    style.setWrapText(true);
	    style.setRightBorderColor(IndexedColors.BLACK.getIndex());
	    style.setBorderRight(CellStyle.BORDER_THIN);
	    style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
	    style.setBorderLeft(CellStyle.BORDER_THIN);
	    style.setTopBorderColor(IndexedColors.BLACK.getIndex());
	    style.setBorderTop(CellStyle.BORDER_THIN);
	    style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
	    style.setBorderBottom(CellStyle.BORDER_THIN);
	    styles.put("grandTotalBold", style);
	    
	    Font titleHeaderBorderFont = wbl.createFont();
	    titleHeaderBorderFont.setFontHeightInPoints((short) 11);
	    titleHeaderBorderFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
	    style = wbl.createCellStyle();
	    style.setAlignment(CellStyle.ALIGN_CENTER);
	    style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
	    style.setWrapText(true);
	    style.setFont(titleHeaderBorderFont);
	    style.setRightBorderColor(IndexedColors.BLACK.getIndex());
	    style.setBorderRight(CellStyle.BORDER_THIN);
	    style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
	    style.setBorderLeft(CellStyle.BORDER_THIN);
	    style.setTopBorderColor(IndexedColors.BLACK.getIndex());
	    style.setBorderTop(CellStyle.BORDER_THIN);
	    style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
	    style.setBorderBottom(CellStyle.BORDER_THIN);
	    styles.put("titleHeaderBorder", style);

	    Font titleBorderFont = wbl.createFont();
	    titleBorderFont.setFontHeightInPoints((short) 18);
	    titleBorderFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
	    style = wbl.createCellStyle();
	    style.setAlignment(CellStyle.ALIGN_CENTER);
	    style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
	    style.setWrapText(true);
	    style.setFont(titleBorderFont);
	    style.setRightBorderColor(IndexedColors.BLACK.getIndex());
	    style.setBorderRight(CellStyle.BORDER_THIN);
	    style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
	    style.setBorderLeft(CellStyle.BORDER_THIN);
	    style.setTopBorderColor(IndexedColors.BLACK.getIndex());
	    style.setBorderTop(CellStyle.BORDER_THIN);
	    styles.put("titleBorder", style);
	    
	    Font subtitleBorderFont = wbl.createFont();
	    subtitleBorderFont.setFontHeightInPoints((short) 12);
	    subtitleBorderFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
	    style = wbl.createCellStyle();
	    style.setAlignment(CellStyle.ALIGN_CENTER);
	    style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
	    style.setWrapText(true);
	    style.setFont(subtitleBorderFont);
	    style.setRightBorderColor(IndexedColors.BLACK.getIndex());
	    style.setBorderRight(CellStyle.BORDER_THIN);
	    style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
	    style.setBorderLeft(CellStyle.BORDER_THIN);
	    style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
	    style.setBorderBottom(CellStyle.BORDER_THIN);
	    styles.put("subtitleBorder", style);
	    
	

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

	public int getMotherToungEnd() {
		return motherToungEnd;
	}

	public void setMotherToungEnd(int motherToungEnd) {
		this.motherToungEnd = motherToungEnd;
	}

	public int getSchoolCategoryEnd() {
		return schoolCategoryEnd;
	}

	public void setSchoolCategoryEnd(int schoolCategoryEnd) {
		this.schoolCategoryEnd = schoolCategoryEnd;
	}
	
	 private DataValidation createBPLStatusDropDownList(int srow, int erow) {
	    	DataValidation dataValidation = null;
	    	try {
	    	    CellRangeAddressList addressList = new CellRangeAddressList(srow, erow, 19,19);
	    	    DVConstraint dvConstraint = DVConstraint.createExplicitListConstraint(BPLStatusArry);
	    	    dataValidation = new HSSFDataValidation(addressList, dvConstraint);
	    	    dataValidation.setSuppressDropDownArrow(false);
	    	    dataValidation.createPromptBox("BPL Status", "Select BPL Status from dropdown!\n(For 'True' : 'YES', 'False' : 'NO')");
	    	    dataValidation.setShowPromptBox(true);
	    	    dataValidation.setErrorStyle(DataValidation.ErrorStyle.STOP);
	    	    dataValidation.createErrorBox("Invalid BPL Status", "Please select the BPL Status from the dropdown!");
	    	} catch (Exception ex) {
	    	    ex.printStackTrace();
	    	    JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
	    	}
	    	return dataValidation;
	    }
	 
	 private DataValidation createRTEStatusDropDownList(int srow, int erow) {
	    	DataValidation dataValidation = null;
	    	try {
	    	    CellRangeAddressList addressList = new CellRangeAddressList(srow, erow, 11,11);
	    	    DVConstraint dvConstraint = DVConstraint.createExplicitListConstraint(RTEStatusArry);
	    	    dataValidation = new HSSFDataValidation(addressList, dvConstraint);
	    	    dataValidation.setSuppressDropDownArrow(false);
	    	    dataValidation.createPromptBox("RTE Status", "Select RTE Status from dropdown!\n(For 'True' : 'YES', 'False' : 'NO')");
	    	    dataValidation.setShowPromptBox(true);
	    	    dataValidation.setErrorStyle(DataValidation.ErrorStyle.STOP);
	    	    dataValidation.createErrorBox("Invalid RTE Status", "Please select the RTE Status from the dropdown!");
	    	} catch (Exception ex) {
	    	    ex.printStackTrace();
	    	    JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
	    	}
	    	return dataValidation;
	    }
	 
	 private DataValidation createYesorNoStatusDropDownList(int srow, int erow, int scol, int ecol, String colStatus) {
	    	DataValidation dataValidation = null;
	    	try {
	    	    CellRangeAddressList addressList = new CellRangeAddressList(srow, erow, scol,ecol);
	    	    DVConstraint dvConstraint = DVConstraint.createExplicitListConstraint(RTEStatusArry);
	    	    dataValidation = new HSSFDataValidation(addressList, dvConstraint);
	    	    dataValidation.setSuppressDropDownArrow(false);
	    	    dataValidation.createPromptBox(colStatus+" Status", "Select "+colStatus+" Status from dropdown!\n(For 'True' : 'YES', 'False' : 'NO')");
	    	    dataValidation.setShowPromptBox(true);
	    	    dataValidation.setErrorStyle(DataValidation.ErrorStyle.STOP);
	    	    dataValidation.createErrorBox("Invalid "+colStatus+" Status", "Please select the "+colStatus+" Status from the dropdown!");
	    	} catch (Exception ex) {
	    	    ex.printStackTrace();
	    	    JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
	    	}
	    	return dataValidation;
	    }
}
