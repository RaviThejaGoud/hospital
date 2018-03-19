/**
 * 
 */
package com.urt.util.pdf;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author sreeram
 */


public class StaffPDFUtil {

	/**
     * Transient log to prevent session synchronization issues - children can use instance for logging.
     */
    protected transient final Log log = LogFactory.getLog(getClass());
	
	public String getMimeType() {
		return "application/vnd.ms-excel"; //$NON-NLS-1$
	}
	
	/*public PDFGenerator createStaffPayrollPDF(Staff staff, ViewStaffPersonAccountDetails vwStaffAccount, PDFGenerator pDFGenerator, String fontPath) {
		
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDoViewStaffPayroll' method");
		}
		try 
		{
			FontFactory.register(fontPath);
	    	FontFactory.register(fontPath);
	    	DecimalFormat decimalFormat=new DecimalFormat("0.00");
	    	
            PdfPTable mainTable = null;
            PdfPTable defaultersHeaderReport = null;
            PdfPTable finalHeaderReport = null;
            
            PdfPTable allowanceHeaderReport = null;
            PdfPTable deductionrsHeaderReport = null;

            PdfPTable headerReport = null;
            
			double  totalAllowance = 0.0;
			double  totalDeduction = 0.0;
			double  totalGrossSalary = 0.0;
			double  totalNetSalary = 0.0;
			
			//double basicSalPercentage = 50.0;
			StringBuffer stringBuffer = null;
			ViewStaffPayrollDetails viewStaffPayrollDetails = null;
			
			Payroll payroll = new Payroll();
			payroll.setCustId(getUserCustId());
			payroll.setGeneratedDate(new Date());
			
			String monthName = new SimpleDateFormat("MMM").format(new Date());
			int monthNum = Integer.valueOf(new SimpleDateFormat("MM").format(new Date()));
			SimpleDateFormat simpleDateformat = new SimpleDateFormat("yyyy");
			int year = Integer.valueOf(simpleDateformat.format(new Date()));
			
			log.debug("Month Name : " + monthName); 
			
			payroll.setMonth(monthNum);
			payroll.setYear(year);
			
			if(!ObjectFunctions.isNullOrEmpty(vwStaffAccount))
			{
				vwStaffAccount.setPayrollEffectiveFrom("1-"+monthNum+"-"+year);
				
				mainTable = new PdfPTable(1);
				mainTable.setWidthPercentage(100);
				mainTable.setSplitLate(false);
				mainTable.setSpacingAfter(0);
				mainTable.getDefaultCell().setBorder(Rectangle.BOX);
				defaultersHeaderReport = new PdfPTable(6);
				defaultersHeaderReport.setWidthPercentage(100);
				headerReport = new PdfPTable(2);
				headerReport.setWidthPercentage(100);
				
				allowanceHeaderReport = new PdfPTable(3);
				deductionrsHeaderReport = new PdfPTable(3);
				allowanceHeaderReport.setWidthPercentage(50);
				deductionrsHeaderReport.setWidthPercentage(50);
                
				sqlQuery = "staffId = " + staffId + " and custId = " + getUserCustId() + " ORDER BY id DESC LIMIT 1";
				log.debug("sqlQuery :: " + sqlQuery); 
				List<Salary> salaryList = adminManager.getAll(Salary.class,sqlQuery);
				if(!ObjectFunctions.isNullOrEmpty(salaryList))
				{
					setSalary((Salary)salaryList.get(0));
					
					Double salary = getSalary().getSalary();
					
					Double basicSalary = 0.0;
					
					sqlQuery = "SELECT *  FROM payrollSettings  o1 WHERE o1.staffId = "+ staffId+ " and o1.payrollType ='BS'  and o1.custId = "+ getUserCustId() + " and o1.payrollSettingStatus = 'A' and o1.lastUpdatedDate ORDER BY o1.lastUpdatedDate";
					
					log.debug(sqlQuery);
					List<Object[]> payrollsettingsBasicList = adminManager.getAll(sqlQuery);
					//List<ViewStaffPayrollDetails> viewStaffPayrollDetailsList = adminManager.getAll(ViewStaffPayrollDetails.class, sqlQuery);
					if(!ObjectFunctions.isNullOrEmpty(payrollsettingsBasicList))
					{
						for(Object[] payrollsettingsObj : payrollsettingsBasicList)
						{
							//local table structure and server table structure are diffirent so getting wrong values,we have do like this.
							PayrollSettings payrollSettings = (PayrollSettings) adminManager.get(PayrollSettings.class, Long.valueOf(payrollsettingsObj[0].toString()));
							//PayrollSettings payrollSettings = new PayrollSettings(payrollsettingsObj);
							if(!ObjectFunctions.isNullOrEmpty(payrollSettings))
							{
								basicSalary = payrollSettings.getBasicSalary();
							}
						}
					}
					
					//if(!ObjectFunctions.isNullOrEmpty(salary))
					if(basicSalary > 0)
					{
						payroll.setSalaryId(getSalary().getId());
						//Double basicSalary = salary*(basicSalPercentage/100);
						
						payroll.setBasicPay(basicSalary);
						
						log.debug("salary :: " + salary);
						
						log.debug("basicSalary :: " + basicSalary); 
						
						vwStaffAccount.setBasicSalary(basicSalary);
						
						//for gettings staff Statutory Values
						//sqlQuery = "SELECT *  FROM staffStatutory  o1 WHERE statutoryNo IS NOT NULL and o1.staffId = "+ staffId+ " and o1.custId = "+ getUserCustId() + " and  o1.dateofJoin  = ( SELECT max(o2.dateofJoin ) FROM staffStatutory  o2  WHERE o1.statutoryName = o2.statutoryName ) ORDER BY o1.dateofJoin";
						
						sqlQuery = "staffId = " + staffId + " and custId = " + getUserCustId() + " ORDER BY id DESC LIMIT 1";
						
						log.debug("sqlQuery :: " + sqlQuery); 
						List<StaffStatutory> staffStatutoryList = adminManager.getAll(StaffStatutory.class,sqlQuery);
						if(!ObjectFunctions.isNullOrEmpty(staffStatutoryList))
						{
							StaffStatutory staffStatutory = staffStatutoryList.get(0);
							if(!ObjectFunctions.isNullOrEmpty(staffStatutory))
							{
								if(!StringFunctions.isNullOrEmpty(staffStatutory.getEsiNo())) 
								{
									double percentage = staffStatutory.getEsiPercentage();
									double percentageValue = basicSalary*(percentage/100);
									staffStatutory.setEsiPercentageValue(percentageValue);
									totalDeduction = totalDeduction + percentageValue;
								}
								if(!StringFunctions.isNullOrEmpty(staffStatutory.getPfNo()))
								{
									double percentage = staffStatutory.getPfPercentage();
									double percentageValue = basicSalary*(percentage/100);
									staffStatutory.setPfPercentageValue(percentageValue);
									totalDeduction = totalDeduction + percentageValue;
								}
								
								setStaffStatutory(staffStatutory);
								payroll.setStaffStatutoryId(staffStatutory.getId());
							}
						}
						
						List schoolHolidaysList = adminManager.geAllSchoolHolidaysListByAcademicYearId(getUserCustId(),getUserAcademicYearId());
						if(!ObjectFunctions.isNullOrEmpty(schoolHolidaysList))
						{
							setObjectList(schoolHolidaysList);
						}
						setLeaveManagement(adminManager.getLeaveManagementByRoleName(vwStaffAccount.getRoleId(), getUserCustId(),getUserAcademicYearId()));
						
						staffLeavesManagement(vwStaffAccount.getAccountId());
						
						String casualLeavesStr = getEventId(); 
						String sickLeavesStr = getBedId();
						double paidLeaves = getPayLeaves();
						
						double paidLeavesAmount = paidLeaves * 200;
						
						LeavesAccount leavesAccount= new LeavesAccount();
						leavesAccount.setCasualLeaves(Double.valueOf(casualLeavesStr));
						leavesAccount.setSickLeaves(Double.valueOf(sickLeavesStr));
						leavesAccount.setPaidLeaves(Double.valueOf(paidLeaves));
						leavesAccount.setPaidLeavesAmount(paidLeaves * 200);
						leavesAccount.setCustId(getUserCustId());
						leavesAccount.setMonth(monthNum);
						leavesAccount.setYear(year);
						leavesAccount.setLastAccessDate(new Date());
						leavesAccount.setLastUpdatedDate(new Date());
						leavesAccount.setCreatedById(getUser().getId());
						leavesAccount.setLastUpdatedById(getUser().getId());
						
						//staff.addstaffleavesAccountDetails(leavesAccount);
						
						leavesAccount.setStaff(staff);
						
						leavesAccount = (LeavesAccount)adminManager.saveOrUpdateObject(leavesAccount);
						
						log.debug(leavesAccount); 
						
						defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithCenterAlignHeadings(" ",6, fontPath));
						defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithCenterAlignHeadings("PaySlip For April",6, fontPath));
						defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithCenterAlignHeadings(vwStaffAccount.getPersonFullName(),6, fontPath));
						defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithLeftAlign(" ",6, fontPath));
						defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithLeftAlign("Employee Number",1, fontPath));
						defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithLeftAlign(":  "+vwStaffAccount.getAccountId(),2, fontPath));
						defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithLeftAlign("Income Tax Number(PAN)",1, fontPath));
						defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithLeftAlign(":  "+vwStaffAccount.getPanNumber(),2, fontPath));
						defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithLeftAlign("Function",1, fontPath));
						defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithLeftAlign(":  "+vwStaffAccount.getRoleDescription(),2, fontPath));
						if(!ObjectFunctions.isNullOrEmpty(getStaffStatutory()))
						{
							if(!ObjectFunctions.isNullOrEmpty(getStaffStatutory().getPfNo()))
							{
								defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithLeftAlign("PF Account Number",1, fontPath));
								defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithLeftAlign(":  "+getStaffStatutory().getPfNo(),2, fontPath));
							}
						}
						
						defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithLeftAlign("Designation", 1, fontPath));
						defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithLeftAlign(":  "+vwStaffAccount.getDesignation(),2, fontPath));
						
						if(!ObjectFunctions.isNullOrEmpty(getStaffStatutory()))
						{
							if(!ObjectFunctions.isNullOrEmpty(getStaffStatutory().getEsiNo()))
							{
								defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithLeftAlign("ESI Number",1, fontPath));
								defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithLeftAlign(":  "+getStaffStatutory().getEsiNo(),2, fontPath));
							}
						}
						defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithLeftAlign("Location",1, fontPath));
						defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithLeftAlign(":  "+vwStaffAccount.getCity(),2, fontPath));
						//defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithLeftAlign("PassPort Details",1, fontPath));
						//defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithLeftAlign(":  "+"sdfgsdfg89dfsg9df9g",2, fontPath));
						defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithLeftAlign("Bank Details",1, fontPath));
						defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithLeftAlign(":  "+vwStaffAccount.getBankName()+","+vwStaffAccount.getBankBranchName()+","+vwStaffAccount.getBankAccountNumber(),2, fontPath));
						//defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithLeftAlign("Visa Expiry Date",1, fontPath));
						//defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithLeftAlign(":  "+"22/09/2029",2, fontPath));
						defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithLeftAlign("Date Of Joining",1, fontPath));
						defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithLeftAlign(":  "+"22-07-2012",2, fontPath));
						defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithLeftAlign(" ",3, fontPath));
						defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithLeftAlign(" ",6, fontPath));
						defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithLeftAlignJasperNoColor("Leave Balance",2, fontPath));
						defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithLeftAlignJasperNoColor("Value",1, fontPath));
						defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithLeftAlign("  ",3, fontPath));
						defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithLeftAlignJasperNoColor("CL",2, fontPath));
						defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithLeftAlignJasperNoColor(" "+ getEventId(),1, fontPath));
						defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithLeftAlign("  ",3, fontPath));
						defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithLeftAlignJasperNoColor("SL",2, fontPath));
						defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithLeftAlignJasperNoColor(" "+ getBedId(),1, fontPath));
						defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithLeftAlign("  ",3, fontPath));
						defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithLeftAlignJasperNoColor("PL",2, fontPath));
						defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithLeftAlignJasperNoColor(" "+ getPayLeaves(),1, fontPath));
						defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithLeftAlign("  ",3, fontPath));
						
						allowanceHeaderReport.addCell(PDFGenerator.getPdfCellHeaderCellJasperWithBorder("Earnings ","#000", fontPath));
						allowanceHeaderReport.addCell(PDFGenerator.getPdfCellHeaderCellJasperWithBorder("Amount ","#000", fontPath));
						allowanceHeaderReport.addCell(PDFGenerator.getPdfCellHeaderCellJasperWithBorder("Gross Salary  ","#000", fontPath));
						
						deductionrsHeaderReport.addCell(PDFGenerator.getPdfCellHeaderCellJasperWithBorder("Deductions","#000", fontPath));
						deductionrsHeaderReport.addCell(PDFGenerator.getPdfCellHeaderCellJasperWithBorder("Amount","#000", fontPath));
						deductionrsHeaderReport.addCell(PDFGenerator.getPdfCellHeaderCellJasperWithBorder("Gross Salary  ","#000", fontPath));
						
						allowanceHeaderReport.addCell(PDFGenerator.getPdfCellHeaderCellJasperHorizontalClass(" Basic Salary","#000", fontPath));
						allowanceHeaderReport.addCell(PDFGenerator.getPdfCellHeaderCellJasperHorizontalClass(" "+vwStaffAccount.getBasicSalary(),"#000", fontPath));
						allowanceHeaderReport.addCell(PDFGenerator.getPdfCellHeaderCellJasperHorizontalClass(" "+vwStaffAccount.getBasicSalary(),"#000", fontPath));
						
						deductionrsHeaderReport.addCell(PDFGenerator.getPdfCellHeaderCellJasperHorizontalClass(" ESI","#000", fontPath));
						if(!ObjectFunctions.isNullOrEmpty(getStaffStatutory()))
						{
							deductionrsHeaderReport.addCell(PDFGenerator.getPdfCellHeaderCellJasperHorizontalClass(""+getStaffStatutory().getEsiPercentageValue(),"#000", fontPath));
							deductionrsHeaderReport.addCell(PDFGenerator.getPdfCellHeaderCellJasperHorizontalClass(""+getStaffStatutory().getEsiPercentageValue(),"#000", fontPath));
						}
						else
						{
							deductionrsHeaderReport.addCell(PDFGenerator.getPdfCellHeaderCellJasperHorizontalClass("0.0","#000", fontPath));
							deductionrsHeaderReport.addCell(PDFGenerator.getPdfCellHeaderCellJasperHorizontalClass("0.0","#000", fontPath));
						}
						
						deductionrsHeaderReport.addCell(PDFGenerator.getPdfCellHeaderCellJasperHorizontalClass(" PF ","#000", fontPath));
						if(!ObjectFunctions.isNullOrEmpty(getStaffStatutory()))
						{
							deductionrsHeaderReport.addCell(PDFGenerator.getPdfCellHeaderCellJasperHorizontalClass(" "+getStaffStatutory().getPfPercentageValue(),"#000", fontPath));
							deductionrsHeaderReport.addCell(PDFGenerator.getPdfCellHeaderCellJasperHorizontalClass(" "+getStaffStatutory().getPfPercentageValue(),"#000", fontPath));
						}
						else
						{
							deductionrsHeaderReport.addCell(PDFGenerator.getPdfCellHeaderCellJasperHorizontalClass("0.0","#000", fontPath));
							deductionrsHeaderReport.addCell(PDFGenerator.getPdfCellHeaderCellJasperHorizontalClass("0.0","#000", fontPath));
						}
						
						//for gettings staff Payroll Settings Values
						//sqlQuery = "SELECT *  FROM payrollSettings  o1 WHERE o1.staffId = "+ staffId+ " and o1.custId = "+ getUserCustId() + " and o1.payrollSettingStatus = '"+ Constants.ACTIVE_STATUS + "' and o1.lastUpdatedDate  = ( SELECT max(o2.lastUpdatedDate ) FROM payrollSettings  o2  WHERE o1.payrollName = o2.payrollName ) ORDER BY o1.lastUpdatedDate";
						
						 sqlQuery = "SELECT *  FROM payrollSettings  o1 WHERE o1.staffId = "+ staffId+ " and o1.custId = "+ getUserCustId() + " and o1.payrollSettingStatus = 'A' and o1.lastUpdatedDate ORDER BY o1.lastUpdatedDate";
						
						log.debug(sqlQuery);
						List<Object[]> payrollsettingsList = adminManager.getAll(sqlQuery);
						//List<ViewStaffPayrollDetails> viewStaffPayrollDetailsList = adminManager.getAll(ViewStaffPayrollDetails.class, sqlQuery);
						if(!ObjectFunctions.isNullOrEmpty(payrollsettingsList))
						{
							List<ViewStaffPayrollDetails>  viewStaffPayrollDetailsAllowanceList = new ArrayList<ViewStaffPayrollDetails>();
							List<ViewStaffPayrollDetails>  viewStaffPayrollDetailsDeductionList = new ArrayList<ViewStaffPayrollDetails>();
							stringBuffer = new StringBuffer();
							stringBuffer.append("(");
							for(Object[] payrollsettings : payrollsettingsList)
							{
								stringBuffer.append(payrollsettings[0].toString()+",");
								//sqlQuery = " payrollSettingsId = "+ Long.valueOf(payrollsettings[0].toString())+" and custId = " + getUserCustId();
								viewStaffPayrollDetails = (ViewStaffPayrollDetails)adminManager.get(ViewStaffPayrollDetails.class,Long.valueOf(payrollsettings[0].toString()), "payrollSettingsId"); 
								
								if(!ObjectFunctions.isNullOrEmpty(viewStaffPayrollDetails))
								{
									if(!"BS".equalsIgnoreCase(viewStaffPayrollDetails.getPayrollType()))
									{
										log.debug(viewStaffPayrollDetails.getPayrollName());
										
										viewStaffPayrollDetails.setStaffPayrollDesc(viewStaffPayrollDetails.getPayrollName());
										if("AW".equalsIgnoreCase(viewStaffPayrollDetails.getPayrollType()))
										{
											double percentage = viewStaffPayrollDetails.getPercentage();
											double percentageValue = basicSalary*(percentage/100);
											
											String formate = decimalFormat.format(percentageValue); 
											percentageValue = Double.valueOf(formate.trim()).doubleValue();
											viewStaffPayrollDetails.setPercentageValue(percentageValue);
											totalAllowance = totalAllowance + percentageValue;
											viewStaffPayrollDetails.setPayHeadType(viewStaffPayrollDetails.getPayrollTypeDesc()+ " For Employee");
											viewStaffPayrollDetailsAllowanceList.add(viewStaffPayrollDetails);
										}
										else if("DN".equalsIgnoreCase(viewStaffPayrollDetails.getPayrollType()))
										{
											double percentage = viewStaffPayrollDetails.getPercentage();
											double percentageValue = basicSalary*(percentage/100);
											String formate = decimalFormat.format(percentageValue); 
											percentageValue = Double.valueOf(formate.trim()).doubleValue();
											viewStaffPayrollDetails.setPercentageValue(percentageValue);
											totalDeduction = totalDeduction + percentageValue;
											viewStaffPayrollDetails.setPayHeadType(viewStaffPayrollDetails.getPayrollTypeDesc()+ " For Employee"  );
											viewStaffPayrollDetailsDeductionList.add(viewStaffPayrollDetails);
										}
										viewStaffPayrollDetails.setComputedOn("Basic Pay");
									}
									
								}
								viewStaffPayrollDetails=null;
							}
							setObjectList(viewStaffPayrollDetailsAllowanceList);
							setTempList(viewStaffPayrollDetailsDeductionList);
							//List<ViewStaffPayrollDetails>  viewStaffPayrollDetailsList= getObjectList();
							if(!ObjectFunctions.isNullOrEmpty(viewStaffPayrollDetailsAllowanceList))
							{
								for(ViewStaffPayrollDetails viewStaffPayrollDetails1 : viewStaffPayrollDetailsAllowanceList) 
								{
									if(!ObjectFunctions.isNullOrEmpty(viewStaffPayrollDetails1))
									{
										allowanceHeaderReport.addCell(PDFGenerator.getPdfCellHeaderCellJasperHorizontalClass(" "+viewStaffPayrollDetails1.getStaffPayrollDesc(),"#000", fontPath));
										allowanceHeaderReport.addCell(PDFGenerator.getPdfCellHeaderCellJasperHorizontalClass(" "+viewStaffPayrollDetails1.getPercentageValue(),"#000", fontPath));
										allowanceHeaderReport.addCell(PDFGenerator.getPdfCellHeaderCellJasperHorizontalClass(" "+viewStaffPayrollDetails1.getPercentageValue(),"#000", fontPath));
									}
									viewStaffPayrollDetails1=null;
								}
								viewStaffPayrollDetailsAllowanceList=null;
							}
							if(!ObjectFunctions.isNullOrEmpty(viewStaffPayrollDetailsDeductionList))
							{
								for(ViewStaffPayrollDetails viewStaffPayrollDetails1 : viewStaffPayrollDetailsDeductionList) 
								{
									if(!ObjectFunctions.isNullOrEmpty(viewStaffPayrollDetails1))
									{
										deductionrsHeaderReport.addCell(PDFGenerator.getPdfCellHeaderCellJasperHorizontalClass(" "+viewStaffPayrollDetails1.getStaffPayrollDesc(),"#000", fontPath));
										deductionrsHeaderReport.addCell(PDFGenerator.getPdfCellHeaderCellJasperHorizontalClass(" "+viewStaffPayrollDetails1.getPercentageValue(),"#000", fontPath));
										deductionrsHeaderReport.addCell(PDFGenerator.getPdfCellHeaderCellJasperHorizontalClass(" "+viewStaffPayrollDetails1.getPercentageValue(),"#000", fontPath));
									}
									viewStaffPayrollDetails1=null;
								}
								viewStaffPayrollDetailsDeductionList=null;
							}
							stringBuffer.append("0)");
							payroll.setPayrollSettingsIds(stringBuffer.toString());
							payrollsettingsList=null;
						}
						
						//staff  StaffStatutory Details
						sqlQuery = "staffId = " + staffId + " and custId = " + getUserCustId() + " and loanStatus = 'Active'";
						log.debug(sqlQuery);
						List<StaffLoanDetails> staffLoanDetailsList = adminManager.getAll(StaffLoanDetails.class,sqlQuery);
						if(!ObjectFunctions.isNullOrEmpty(staffLoanDetailsList))
						{
							stringBuffer = new StringBuffer();
							stringBuffer.append("(");
							for(StaffLoanDetails staffLoanDetails : staffLoanDetailsList)
							{
								List<LoanEmiDetails> loanEmiDetailsList = staffLoanDetails.getLoanEmiDetails();
								if(!ObjectFunctions.isNullOrEmpty(loanEmiDetailsList))
								{
									double remainingAmountt = staffLoanDetails.getInstallmentAmount();
									double loanAmount = staffLoanDetails.getLoanAmount();
									for(LoanEmiDetails loanEmiDetails : loanEmiDetailsList) 
									{
										remainingAmountt = loanAmount - remainingAmountt;
										if("Pending".equalsIgnoreCase(loanEmiDetails.getEmiStatus()))
										{
											stringBuffer.append(loanEmiDetails.getId()+",");
											
											loanEmiDetails.setEmiStatus("Closed");
											loanEmiDetails.setPaidDate(new Date());
											loanEmiDetails.setPaidAmount(staffLoanDetails.getInstallmentAmount());
											loanEmiDetails.setRemainingAmount(remainingAmountt);
											
											loanEmiDetails.setLastAccessDate(new Date());
											loanEmiDetails.setLastUpdatedDate(new Date());
											loanEmiDetails.setLastUpdatedById(getUser().getId());
											
											if(staffLoanDetails.getInstallments() == loanEmiDetails.getInstallmentNumber())
											{
												staffLoanDetails.setLoanStatus("Closed");
												staffLoanDetails.setLastAccessDate(new Date());
												staffLoanDetails.setLastUpdatedDate(new Date());
												staffLoanDetails.setLastUpdatedById(getUser().getId());
												
												adminManager.save(staffLoanDetails);
											}
											
											//double percentage = staffStatutory1.getPercentage();
											double installmentAmount = staffLoanDetails.getInstallmentAmount();
											log.debug(installmentAmount);
											//viewStaffPayrollDetails.setPercentageValue(installmentAmount);
											
											totalDeduction = totalDeduction + installmentAmount;
											//viewStaffPayrollDetails.setPayHeadType("Loan Emai "+ loanEmiDetails.getInstallmentNumber());
											adminManager.save(loanEmiDetails);
											
											deductionrsHeaderReport.addCell(PDFGenerator.getPdfCellHeaderCellJasperHorizontalClass(" "+staffLoanDetails.getLoanDescription()+"Emi " +loanEmiDetails.getInstallmentNumber() ,"#000", fontPath));
											deductionrsHeaderReport.addCell(PDFGenerator.getPdfCellHeaderCellJasperHorizontalClass(" "+staffLoanDetails.getInstallmentAmount(),"#000", fontPath));
											deductionrsHeaderReport.addCell(PDFGenerator.getPdfCellHeaderCellJasperHorizontalClass(" "+staffLoanDetails.getInstallmentAmount(),"#000", fontPath));
											
											break;
										}
									}
								}
								
								staffLoanDetails=null;
							}
							stringBuffer.append("0)");
							payroll.setStaffLoanEmiIds(stringBuffer.toString());
							staffStatutoryList=null;
						}
						//calculating gross salary
						totalAllowance = basicSalary + totalAllowance;
						totalGrossSalary = totalAllowance;
						
						vwStaffAccount.setTotalAllowance(totalGrossSalary);
						
						double totalYearlyBasicSalary = totalGrossSalary*12;
						double taxAmountPerYear = 0.0;
						double taxAmountPerMonth = 0.0;
						double taxPercentage = 0.0;
						
						String currentFinancialYear = getCurrentFinancialYear();
						
						sqlQuery = "financialYear = '"+ currentFinancialYear +"' and gender = '" + vwStaffAccount.getGender() + "' and status = 'Y'";
						log.debug(sqlQuery);
						List<IncomeTaxRates> incomeTaxRatesList = adminManager.getAll(IncomeTaxRates.class,sqlQuery);
						if(!ObjectFunctions.isNullOrEmpty(incomeTaxRatesList))
						{
							String formate = decimalFormat.format(totalYearlyBasicSalary); 
							totalYearlyBasicSalary = Double.valueOf(formate.trim()).doubleValue();
							
							int size = incomeTaxRatesList.size();
							int i = 0;
							for(IncomeTaxRates incomeTaxRates : incomeTaxRatesList)
							{
								i++;
								if(totalYearlyBasicSalary > incomeTaxRates.getNetAmount1() &&  totalYearlyBasicSalary <= incomeTaxRates.getNetAmount2())
								{
									 if(i != 1)
									 {
										 taxAmountPerYear = totalYearlyBasicSalary *(incomeTaxRates.getTaxPercentage()/100);
										 taxPercentage= incomeTaxRates.getTaxPercentage();
									 }
								}
								else if(i == size && totalYearlyBasicSalary > incomeTaxRates.getNetAmount1())
								{
									taxAmountPerYear = totalYearlyBasicSalary *(incomeTaxRates.getTaxPercentage()/100);
								}
								log.debug("i :: " + i);
							}
						}
						TaxAccount taxAccount = new TaxAccount();
						
						taxAccount.setTaxAmountPerYear(taxAmountPerYear);
						if(taxAmountPerYear >0)
						{
							taxAmountPerMonth = taxAmountPerYear/12;
						}
						taxAccount.setTaxAmountPerMonth(taxAmountPerMonth);
						taxAccount.setNetAmount(totalYearlyBasicSalary);
						taxAccount.setTaxPercentage(taxPercentage);
						taxAccount.setYear(year);
						taxAccount.setMonth(monthNum);
						taxAccount.setCustId(getUserCustId());
						taxAccount.setLastAccessDate(new Date());
						taxAccount.setLastUpdatedDate(new Date());
						taxAccount.setCreatedById(getUser().getId());
						taxAccount.setLastUpdatedById(getUser().getId());
						taxAccount.setStaff(staff);
						
						//adminManager.save(taxAccount);
						taxAccount = (TaxAccount)adminManager.saveOrUpdateObject(taxAccount);
						payroll.setTaxAccount(taxAccount);
						
						totalDeduction = totalDeduction + taxAccount.getTaxAmountPerMonth();
						
						deductionrsHeaderReport.addCell(PDFGenerator.getPdfCellHeaderCellJasperHorizontalClass("Income Tax "  ,"#000", fontPath));
						deductionrsHeaderReport.addCell(PDFGenerator.getPdfCellHeaderCellJasperHorizontalClass(" "+taxAccount.getTaxAmountPerMonth(),"#000", fontPath));
						deductionrsHeaderReport.addCell(PDFGenerator.getPdfCellHeaderCellJasperHorizontalClass(" "+taxAccount.getTaxAmountPerMonth(),"#000", fontPath));
						
						totalNetSalary = totalGrossSalary - totalDeduction;
						
						vwStaffAccount.setTotalDeduction(totalDeduction);
						
						vwStaffAccount.setNetPay(totalGrossSalary);
						payroll.setNetPay(totalGrossSalary);
						
						finalHeaderReport = new PdfPTable(6);
						finalHeaderReport.setWidthPercentage(100);
						
						finalHeaderReport.addCell(PDFGenerator.getPdfCellHeaderCellJasperClass("Total Earnings ","#000", fontPath));
						finalHeaderReport.addCell(PDFGenerator.getPdfCellHeaderCellJasperClass(" "+totalAllowance,"#000", fontPath));
						finalHeaderReport.addCell(PDFGenerator.getPdfCellHeaderCellJasperClass(" "+totalAllowance,"#000", fontPath));
						 
						finalHeaderReport.addCell(PDFGenerator.getPdfCellHeaderCellJasperClass("Total Deductions  ","#000", fontPath));
						finalHeaderReport.addCell(PDFGenerator.getPdfCellHeaderCellJasperClass(" "+totalDeduction,"#000", fontPath));
						finalHeaderReport.addCell(PDFGenerator.getPdfCellHeaderCellJasperClass(" "+totalDeduction,"#000", fontPath)); 
						 
						finalHeaderReport.addCell(PDFGenerator.getPdfCellHeaderCellJasperClass(" ","#000", fontPath));
						finalHeaderReport.addCell(PDFGenerator.getPdfCellHeaderCellJasperClass(" ","#000", fontPath));
						finalHeaderReport.addCell(PDFGenerator.getPdfCellHeaderCellJasperClass(" ","#000", fontPath));
						finalHeaderReport.addCell(PDFGenerator.getPdfCellHeaderCellJasperClass("Net pay ","#000", fontPath));
						finalHeaderReport.addCell(PDFGenerator.getPdfCellHeaderCellJasperClass(" "+totalGrossSalary,"#000", fontPath));
						finalHeaderReport.addCell(PDFGenerator.getPdfCellHeaderCellJasperClass(" "+totalGrossSalary,"#000", fontPath));
						 
					}
					
					headerReport.addCell(allowanceHeaderReport);
					headerReport.addCell(deductionrsHeaderReport);
					
					mainTable.addCell(defaultersHeaderReport);
					setViewStaffPersonAccountDetails(vwStaffAccount);
					
					 mainTable.addCell(headerReport);
					 mainTable.addCell(finalHeaderReport);
					
					pDFGenerator.getDocument().add(mainTable);
					pDFGenerator.getDocument().newPage();
	                 mainTable = null;

					
	                 payroll.setStaff(staff);
	                 adminManager.save(payroll);
	                 payroll=null;
	                 return pDFGenerator;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return null;
	}*/
}
