package com.urt.webapp.action.admin;




public class UnUsedAdminMethods {
	
	/*
	 * @Actions( { 
		@Action(value = "ajaxGeneratePayroll", results = { @Result(location = "payroll/ajaxGetPayrollSettingsForStaff.jsp", name = "success") }) 
		
	})
	public String ajaxDoGeneratePayroll() {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDoGeneratePayroll' method");
		}
		try 
		{
			setTempString(getParamValue("staffRoleName")); 
			log.debug(getTempString());
			prepareStaffRolesMap(null);
			List<Staff> staffsList = null;
			String query = null;
			
			String fileName = "Staff_Pay_Roll_Information"+ DateUtil.getDateTime(DateFormatter.MMDDYYY_PATTERN,new Date());
			PDFGenerator pDFGenerator = new PDFGenerator();
	    	getResponse().setContentType(pDFGenerator.getMimeType());
	    	getResponse().setHeader("Content-Disposition","attachment; filename="+fileName.replace(' ','_')+".pdf");
	    	pDFGenerator.createDocumentJasper();
	    	pDFGenerator.setPdfWriter(PdfWriter.getInstance(pDFGenerator.getDocument(),getResponse().getOutputStream()));
	    	PdfHeaderFooterMarkJasper phfmj=new PdfHeaderFooterMarkJasper();
		    pDFGenerator.getPdfWriter().setPageEvent(phfmj); 
		    pDFGenerator.getDocument().open();
		   
		    setpDFGenerator(pDFGenerator);
		    
		    if(!StringFunctions.isNullOrEmpty(getTempString()))
		    {
		    	if("all".equalsIgnoreCase(getTempString()))
				{
					query = " custId="+getUserCustId()+" and status='"+Constants.YES_STRING+"'";
					staffsList =  adminManager.getAll(Staff.class, query);
					//staffsList = adminManager.getStaffsListByRoleAndCustIdAndStatus(" ",getUserCustId(),Constants.YES_STRING);
					log.debug("size :: "+ getStaffsList().size()); 
					
					if(ObjectFunctions.isNotNullOrEmpty(getStaffsList()))
						Collections.sort(getStaffsList());
					for(Staff staff : staffsList)
					{
						if(!ObjectFunctions.isNullOrEmpty(staff))
						{
							setAnyId("GeneratePayroll");
							setStaff(staff);
							//if(68 == staff.getId())
							//{
								
								//Payroll payroll = getPayroll();
								if(!ObjectFunctions.isNullOrEmpty(getStaff()))
								{
									ajaxDoViewStaffPayroll(getStaff());
									//payroll.setStaff(staff);
									//staff.addPayrollDetailsSettings(payroll);
									//adminManager.save(payroll);
								}
							//}
						}
					}
				}
				
				else if(!"all".equalsIgnoreCase(getTempString()))
				{
					query = " roleName in ("+getTempString()+") and custId="+getUserCustId()+" and status='Y'" ;
					//staffsList = adminManager.getAll(Staff.class, query);
					List<ViewStaffPersonAccountDetails> viewStaffDetailsList =  adminManager.getStaffsListByRoleAndCustIdAndStatus("'"+getTempString()+"'",getUserCustId(),Constants.YES_STRING);
					
					if(!ObjectFunctions.isNullOrEmpty(viewStaffDetailsList))
					{
						for(ViewStaffPersonAccountDetails viewStaffDetails : viewStaffDetailsList)
						{
							if(!ObjectFunctions.isNullOrEmpty(viewStaffDetails))
							{
								setAnyId("GeneratePayroll");
								setStaff((Staff)adminManager.get(Staff.class, viewStaffDetails.getStaffId()));
								
								//Payroll payroll = getPayroll();
								if(!ObjectFunctions.isNullOrEmpty(getStaff()))
								{
									ajaxDoViewStaffPayroll(getStaff());
									//payroll.setStaff(staff);
									//getStaff().addPayrollDetailsSettings(payroll);
									//adminManager.save(payroll);
								}
								
							}
						}
					}
					
				}
		    }
			
			
			pDFGenerator = getpDFGenerator();
			 pDFGenerator.getDocument().close();
	            pDFGenerator=null;
			    phfmj=null;
			
			//ajaxdoCreatepayrollSettings();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return null;
	}
	
	
	
	 */
	/*
	 * @Actions( { 
		@Action(value = "ajaxDoViewStaffPayroll", results = { @Result(location = "payroll/ajaxDoViewStaffPayroll.jsp", name = "success") }) 
		
	})
	public String ajaxDoViewStaffPayroll(Staff staff) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDoViewStaffPayroll' method");
		}
		try 
		{
			pDFGenerator = getpDFGenerator();
			String fontPath=getSession().getServletContext().getRealPath(getText(Constants.GILITE_FILE_DOCS_DIR)+"/Droid-Sans/DroidSans-Bold.ttf");
	    	FontFactory.register(fontPath);
	    	DecimalFormat decimalFormat=new DecimalFormat("0.00");
	    	
            PdfPTable mainTable = null;
            PdfPTable defaultersHeaderReport = null;
            PdfPTable finalHeaderReport = null;
            
            PdfPTable allowanceHeaderReport = null;
            PdfPTable deductionrsHeaderReport = null;

            PdfPTable headerReport = null;
            
			String staffId = null;
			
			if(!ObjectFunctions.isNullOrEmpty(staff))
			{
				setStaff(staff);
				staffId = String.valueOf(getStaff().getId());
			}
			
			if(StringFunctions.isNullOrEmpty(staffId))
			{
				staffId = getParamValue("staffId"); 
			}
			
			double  totalAllowance = 0.0;
			double  totalDeduction = 0.0;
			double  totalGrossSalary = 0.0;
			double  totalNetSalary = 0.0;
			
			//double basicSalPercentage = 50.0;
			StringBuffer stringBuffer = null;
			setObjectList(null);
			setTempList(null);
			ViewStaffPayrollDetails viewStaffPayrollDetails = null;
			
			if(!StringFunctions.isNullOrEmpty(staffId))
			{
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
				
				String sqlQuery = " staffId = "+ staffId+" and custId = " + getUserCustId();
				ViewStaffPersonAccountDetails  viewStaffDetails = (ViewStaffPersonAccountDetails)adminManager.get(ViewStaffPersonAccountDetails.class, sqlQuery);
				if(!ObjectFunctions.isNullOrEmpty(viewStaffDetails))
				{
					viewStaffDetails.setPayrollEffectiveFrom("1-"+monthNum+"-"+year);
					
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
							
							viewStaffDetails.setBasicSalary(basicSalary);
							
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
							setLeaveManagement(adminManager.getLeaveManagementByRoleName(viewStaffDetails.getRoleId(), getUserCustId(),getUserAcademicYearId()));
							
							staffLeavesManagement(viewStaffDetails.getAccountId());
							
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
							defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithCenterAlignHeadings(viewStaffDetails.getPersonFullName(),6, fontPath));
							defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithLeftAlign(" ",6, fontPath));
							defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithLeftAlign("Employee Number",1, fontPath));
							defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithLeftAlign(":  "+viewStaffDetails.getAccountId(),2, fontPath));
							defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithLeftAlign("Income Tax Number(PAN)",1, fontPath));
							defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithLeftAlign(":  "+viewStaffDetails.getPanNumber(),2, fontPath));
							defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithLeftAlign("Function",1, fontPath));
							defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithLeftAlign(":  "+viewStaffDetails.getRoleDescription(),2, fontPath));
							if(!ObjectFunctions.isNullOrEmpty(getStaffStatutory()))
							{
								if(!ObjectFunctions.isNullOrEmpty(getStaffStatutory().getPfNo()))
								{
									defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithLeftAlign("PF Account Number",1, fontPath));
									defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithLeftAlign(":  "+getStaffStatutory().getPfNo(),2, fontPath));
								}
							}
							
							defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithLeftAlign("Designation", 1, fontPath));
							defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithLeftAlign(":  "+viewStaffDetails.getDesignation(),2, fontPath));
							
							if(!ObjectFunctions.isNullOrEmpty(getStaffStatutory()))
							{
								if(!ObjectFunctions.isNullOrEmpty(getStaffStatutory().getEsiNo()))
								{
									defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithLeftAlign("ESI Number",1, fontPath));
									defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithLeftAlign(":  "+getStaffStatutory().getEsiNo(),2, fontPath));
								}
							}
							defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithLeftAlign("Location",1, fontPath));
							defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithLeftAlign(":  "+viewStaffDetails.getCity(),2, fontPath));
							//defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithLeftAlign("PassPort Details",1, fontPath));
							//defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithLeftAlign(":  "+"sdfgsdfg89dfsg9df9g",2, fontPath));
							defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithLeftAlign("Bank Details",1, fontPath));
							defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithLeftAlign(":  "+viewStaffDetails.getBankName()+","+viewStaffDetails.getBankBranchName()+","+viewStaffDetails.getBankAccountNumber(),2, fontPath));
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
							allowanceHeaderReport.addCell(PDFGenerator.getPdfCellHeaderCellJasperHorizontalClass(" "+viewStaffDetails.getBasicSalary(),"#000", fontPath));
							allowanceHeaderReport.addCell(PDFGenerator.getPdfCellHeaderCellJasperHorizontalClass(" "+viewStaffDetails.getBasicSalary(),"#000", fontPath));
							
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
							
							viewStaffDetails.setTotalAllowance(totalGrossSalary);
							
							double totalYearlyBasicSalary = totalGrossSalary*12;
							double taxAmountPerYear = 0.0;
							double taxAmountPerMonth = 0.0;
							double taxPercentage = 0.0;
							
							String currentFinancialYear = getCurrentFinancialYear();
							
							sqlQuery = "financialYear = '"+ currentFinancialYear +"' and gender = '" + viewStaffDetails.getGender() + "' and status = 'Y'";
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
							
							viewStaffDetails.setTotalDeduction(totalDeduction);
							
							viewStaffDetails.setNetPay(totalGrossSalary);
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
						setViewStaffPersonAccountDetails(viewStaffDetails);
						
						 mainTable.addCell(headerReport);
						 mainTable.addCell(finalHeaderReport);
						
						pDFGenerator.getDocument().add(mainTable);
						pDFGenerator.getDocument().newPage();
		                 mainTable = null;

						
		                 payroll.setStaff(staff);
		                 adminManager.save(payroll);
		                 payroll=null;
		                 
		                 setpDFGenerator(pDFGenerator);
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return null;
	}
	 */

	/*
	 * 
	public String ajaxViewRecentStaffPayroll(Payroll payroll,String staffId) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDoViewStaffPayroll' method");
		}
		try 
		{
			
			double  totalAllowance = 0.0;
			double  totalDeduction = 0.0;
			double  totalGrossSalary = 0.0;
			StringBuffer stringBuffer = null;
			//ViewStaffPayrollDetails viewStaffPayrollDetails = null;
			
			if(!StringFunctions.isNullOrEmpty(staffId) && !ObjectFunctions.isNullOrEmpty(payroll))
			{
				int monthNum = payroll.getMonth();
				SimpleDateFormat simpleDateformat = new SimpleDateFormat("yyyy");
				int year = payroll.getYear();
				
				String sqlQuery = " staffId = "+ staffId+" and custId = " + getUserCustId();
				ViewStaffPersonAccountDetails  viewStaffDetails = (ViewStaffPersonAccountDetails)adminManager.get(ViewStaffPersonAccountDetails.class, sqlQuery);
				if(!ObjectFunctions.isNullOrEmpty(viewStaffDetails))
				{
					viewStaffDetails.setPayrollEffectiveFrom("1-"+monthNum+"-"+year);
					sqlQuery = "staffId = " + staffId + " and custId = " + getUserCustId() + " ORDER BY id DESC LIMIT 1";
					log.debug("sqlQuery :: " + sqlQuery); 
					Salary salaryObj = (Salary) adminManager.get(Salary.class,payroll.getSalaryId());
					if(!ObjectFunctions.isNullOrEmpty(salaryObj))
					{
						setSalary((Salary)salaryObj);
						Double salary = getSalary().getSalary();
						
						if(!ObjectFunctions.isNullOrEmpty(salary))
						{
							payroll.setSalaryId(getSalary().getId());
							Double basicSalary = payroll.getBasicPay();
							
							viewStaffDetails.setBasicSalary(basicSalary);
							String payrollSettingsIds = payroll.getPayrollSettingsIds();
							
							if(!StringFunctions.isNullOrEmpty(payrollSettingsIds))
							{
								sqlQuery = " payrollSettingsId in  " + payrollSettingsIds + " and custId =" + getUserCustId();
								log.debug(sqlQuery);
								List<ViewStaffPayrollDetails> viewStaffPayrollDetailsList = adminManager.getAll(ViewStaffPayrollDetails.class, sqlQuery);
								if(!ObjectFunctions.isNullOrEmpty(viewStaffPayrollDetailsList))
								{
									for(ViewStaffPayrollDetails viewStaffPayrollDetails : viewStaffPayrollDetailsList)
									{
										//sqlQuery = " payrollSettingsId = "+ Long.valueOf(payrollsettings[0].toString())+" and custId = " + getUserCustId();
										//viewStaffPayrollDetails = (ViewStaffPayrollDetails)adminManager.get(ViewStaffPayrollDetails.class,Long.valueOf(payrollsettingsoneId), "payrollSettingsId"); 
										
										if(!ObjectFunctions.isNullOrEmpty(viewStaffPayrollDetails))
										{
											String payrollTypesId = viewStaffPayrollDetails.getPayrollTypesId();
											
											sqlQuery = " id = "+ Long.valueOf(payrollTypesId)+" and custId = " + getUserCustId();
											log.debug(sqlQuery);
											List<PayrollTypes> payrollTypesList = adminManager.getAll(PayrollTypes.class,sqlQuery);
											if(!ObjectFunctions.isNullOrEmpty(payrollTypesList))
											{
												setPayrollTypes((PayrollTypes)payrollTypesList.get(0));
												log.debug(viewStaffPayrollDetails.getPayrollName());
												log.debug(getPayrollTypes().getPayrollName());
												
												viewStaffPayrollDetails.setStaffPayrollDesc(getPayrollTypes().getPayrollName());
												if("AW".equalsIgnoreCase(getPayrollTypes().getPayrollType()))
												{
													double percentage = viewStaffPayrollDetails.getPercentage();
													double percentageValue = basicSalary*(percentage/100);
													viewStaffPayrollDetails.setPercentageValue(percentageValue);
													totalAllowance = totalAllowance + percentageValue;
													viewStaffPayrollDetails.setPayHeadType(getPayrollTypes().getPayrollTypeDesc()+ " For Employee");
													viewStaffPayrollDetails.setComputedOn("Basic Pay");
													getObjectList().add(viewStaffPayrollDetails);
												}
												else if("DN".equalsIgnoreCase(getPayrollTypes().getPayrollType()))
												{
													double percentage = viewStaffPayrollDetails.getPercentage();
													double percentageValue = basicSalary*(percentage/100);
													viewStaffPayrollDetails.setPercentageValue(percentageValue);
													totalDeduction = totalDeduction + percentageValue;
													viewStaffPayrollDetails.setPayHeadType(getPayrollTypes().getPayrollTypeDesc()+ " For Employee"  );
													viewStaffPayrollDetails.setComputedOn("Basic Pay");
													getTempList().add(viewStaffPayrollDetails);
												}
												//viewStaffPayrollDetails.setComputedOn("Basic Pay");
											}
										}
										viewStaffPayrollDetails=null;
									}
									
									viewStaffPayrollDetailsList=null;
								}
							}
							
							//for gettings staff Statutory Values
							
							long staffStatutoryId = payroll.getStaffStatutoryId();
							
							if(!ObjectFunctions.isNullOrEmpty(staffStatutoryId))
							{
								//sqlQuery = "staffId = " + staffId + " and custId = " + getUserCustId() + " ORDER BY id DESC LIMIT 1";
								
								log.debug("sqlQuery :: " + sqlQuery); 
								StaffStatutory staffStatutory = (StaffStatutory) adminManager.get(StaffStatutory.class,staffStatutoryId);
								if(!ObjectFunctions.isNullOrEmpty(staffStatutory))
								{
									if(!ObjectFunctions.isNullOrEmpty(staffStatutory))
									{
										if(!StringFunctions.isNullOrEmpty(staffStatutory.getEsiNo())) 
										{
											double percentage = staffStatutory.getEsiPercentage();
											double percentageValue = basicSalary*(percentage/100);
											staffStatutory.setEsiPercentageValue(percentageValue);
											totalDeduction = totalDeduction + percentageValue;
											//viewStaffPayrollDetails.setPayHeadType(getPayrollTypes().getPayrollTypeDesc()+ " For Employee");
										}
										if(!StringFunctions.isNullOrEmpty(staffStatutory.getPfNo()))
										{
											double percentage = staffStatutory.getPfPercentage();
											double percentageValue = basicSalary*(percentage/100);
											staffStatutory.setPfPercentageValue(percentageValue);
											totalDeduction = totalDeduction + percentageValue;
											//viewStaffPayrollDetails.setPayHeadType(getPayrollTypes().getPayrollTypeDesc()+ " For Employee"  );
										}
										setStaffStatutory(staffStatutory);
									}
								}
							}
							//staff  Loan Emi Details
							String loanEmiDetailsIds = payroll.getStaffLoanEmiIds();
							
							if(!StringFunctions.isNullOrEmpty(loanEmiDetailsIds))
							{
								sqlQuery = " id in  " + loanEmiDetailsIds + " and custId =" + getUserCustId()+ " and staffId = " + staffId;
								log.debug(sqlQuery);
								List<LoanEmiDetails> loanEmiDetailsList = adminManager.getAll(LoanEmiDetails.class, sqlQuery);
								if(!ObjectFunctions.isNullOrEmpty(loanEmiDetailsList))
								{
									for(LoanEmiDetails loanEmiDetails : loanEmiDetailsList) 
									{
										//double percentage = staffStatutory1.getPercentage();
										double installmentAmount = loanEmiDetails.getInstallmentAmount();
										totalDeduction = totalDeduction + installmentAmount;
									}
									setLoanEmiDetailsList(loanEmiDetailsList);
								}
							}
							
							//
							TaxAccount taxAccount = payroll.getTaxAccount();
							if(!ObjectFunctions.isNullOrEmpty(taxAccount))
							{
								log.debug("taxAccount Id :: " + taxAccount.getId());
								setTaxAccount(taxAccount);
								totalDeduction = totalDeduction + taxAccount.getTaxAmountPerMonth();
							}
							
							totalAllowance = basicSalary + totalAllowance;
							viewStaffDetails.setTotalAllowance(totalAllowance);
							totalGrossSalary = totalAllowance - totalDeduction;
							viewStaffDetails.setNetPay(totalGrossSalary);
							viewStaffDetails.setTotalDeduction(totalDeduction);
						}
					}
					setViewStaffPersonAccountDetails(viewStaffDetails);
				}
				
				if("GeneratePayroll".equalsIgnoreCase(getAnyId()))
				{
					setPayroll(payroll);
				}
				payroll=null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return SUCCESS;
		
	}
	
	@Actions( { 
		@Action(value = "ajaxDoViewOldGeneratedPayroll", results = { @Result(location = "payroll/ajaxDoViewOldGeneratePayroll.jsp", name = "success") })
		
	})
	public String ajaxDoViewOldGeneratedPayroll() {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDoViewOldGeneratedPayroll' method");
		}
		try 
		{
			Customer customer = getCustomerByCustId();
			prepareStaffRolesMap(customer);
			prepareNonTeachingRolesMap(customer);
			//ajaxdoCreatepayrollSettings();
			setStaffRoleName(getTempString());
			if(!StringFunctions.isNullOrEmpty(getTempString()))
			{
				setStaffsList(adminManager.getStaffsListByRoleAndCustIdAndStatus("'"+getTempString()+"'",getUserCustId(),Constants.YES_STRING));
				if(ObjectFunctions.isNotNullOrEmpty(getStaffsList()))
					Collections.sort(getStaffsList());
			}
			customer = null;
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return SUCCESS;
		
	}
	
	@Actions( { 
		@Action(value = "ajaxViewOldStaffPayroll", results = { @Result(location = "payroll/ajaxDoViewStaffPayroll.jsp", name = "success") }) 
		
	})
	public String ajaxViewOldStaffPayroll() {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxViewOldStaffPayroll' method");
		}
		try 
		{
			String staffId = getParamValue("viewStaffPersonAccountDetails.staffId"); 
			String month = getParamValue("month");
			String year = getParamValue("year");
			String staffRoleName = getParamValue("staffRoleName");
			
			String  sqlQuery = "staffId = " + Long.valueOf(staffId) + " and custId = " + getUserCustId() + " and month = " + Integer.valueOf(month) + " and year = " + Integer.valueOf(year) + " ORDER BY generatedDate DESC LIMIT 1"; 
			log.debug(sqlQuery); 
			List<Payroll> payrollList = adminManager.getAll(Payroll.class, sqlQuery);
			if(!ObjectFunctions.isNullOrEmpty(payrollList))
			{
				Payroll payroll = (Payroll)payrollList.get(0);
				if(!ObjectFunctions.isNullOrEmpty(payroll))
				{
					log.debug("payroll Id :: " + payroll.getId()); 
					ajaxViewRecentStaffPayroll(payroll,staffId);
				}
				
			}
			else
			{
				setViewStaffPersonAccountDetails(adminManager.getStaffDetailsByRoleNameAndStaffIdAndCustId(staffRoleName, getUserCustId(), Long.valueOf(staffId), "Y"));
				super.addActionError("No Date Found For that User.");
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return SUCCESS;
		
	}
	
	
	@Actions( { 
		@Action(value = "ajaxDoViewRecentStaffPayroll", results = { @Result(location = "payroll/ajaxDoViewStaffPayroll.jsp", name = "success") }) 
		
	})
	public String ajaxDoViewRecentStaffPayroll() {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDoViewRecentStaffPayroll' method");
		}
		try 
		{
			String staffId = getParamValue("staffId"); 
			if(!StringFunctions.isNullOrEmpty(staffId))
			{
				String  sqlQuery = "staffId = " + Long.valueOf(staffId) + " and custId = " + getUserCustId() + " ORDER BY generatedDate DESC LIMIT 1"; 
				log.debug(sqlQuery); 
				List<Payroll> payrollList = adminManager.getAll(Payroll.class, sqlQuery);
				if(!ObjectFunctions.isNullOrEmpty(payrollList))
				{
					Payroll payroll = (Payroll)payrollList.get(0);
					if(!ObjectFunctions.isNullOrEmpty(payroll))
					{
						log.debug("payroll Id :: " + payroll.getId()); 
						ajaxViewRecentStaffPayroll(payroll,staffId);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return SUCCESS;
	}
	
	@Actions( { 
		@Action(value = "ajaxDoGetPayrollSettings", results = { @Result(location = "payroll/ajaxManagePayrollSettings.jsp", name = "success") }) 
		
	})
	public String ajaxdoCreatepayrollSettings() {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxdoCreatepayrollSettings' method");
		}
		try 
		{
			String staffQuery = " custId = " + getUserCustId();
			List<ViewStaffPersonAccountDetails> viewStaffDetailsList = adminManager.getAll(ViewStaffPersonAccountDetails.class, staffQuery);
			if(!ObjectFunctions.isNullOrEmpty(viewStaffDetailsList))
			{
				for(ViewStaffPersonAccountDetails viewStaffDetails : viewStaffDetailsList)
				{
					if(!ObjectFunctions.isNullOrEmpty(viewStaffDetails))
					{
						String sqlQuery = "staffId = " + viewStaffDetails.getStaffId() + " and custId = " + getUserCustId() + " ORDER BY id DESC LIMIT 1";
						List<Salary> salaryList = adminManager.getAll(Salary.class,sqlQuery);
						if(!ObjectFunctions.isNullOrEmpty(salaryList))
						{
							log.debug("salaryList.get(0) :::"  + salaryList.get(0));
							//viewStaffDetails.setSalary((Salary)salaryList.get(0));//
						}
						getViewStaffPersonAccountDetailsList().add(viewStaffDetails);
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return SUCCESS;
		
	}
	
	
	
	@Actions( { 
		@Action(value = "ajaxDoViewRecentGeneratedPayroll", results = { @Result(location = "payroll/ajaxGetPayrollSettingsForStaff.jsp", name = "success") }),
		@Action(value = "ajaxDoGetLoanDetails", results = { @Result(location = "loan/ajaxManageLoanDetails.jsp", name = "success") }),
		@Action(value = "ajaxDoPayrollSettingDetails", results = { @Result(location = "payroll/ajaxManagePayrollSettings.jsp", name = "success") })
	})
	public String ajaxDoViewRecentGeneratedPayroll() {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDoViewRecentGeneratedPayroll' method");
		}
		try 
		{
			Customer cust = getCustomerByCustId();
			prepareStaffRolesMap(cust);
			prepareNonTeachingRolesMap(cust);
			ajaxdoCreatepayrollSettings();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return SUCCESS;
		
	}
	@Actions( { 
		@Action(value = "ajaxDoGetPayrollTypes", results = { @Result(location = "payroll/ajaxManagePayrollTypes.jsp", name = "success") }),
		@Action(value = "ajaxDoCancelPayrollTypes", results = { @Result(location = "payroll/ajaxGetPayrollTypes.jsp", name = "success") }),
		@Action(value = "payrollProcessForSchool", results = {@Result(location = "payroll/payrollLeftNav.jsp", name = "success") })
	})
	public String ajaxDoGetPayrollTypes() {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDoGetPayrollTypes' method");
		}
		try 
		{
			String sqlQuery = " custId = " + getUserCustId();
			List<PayrollTypes> payrollTypesList = adminManager.getAll(PayrollTypes.class,sqlQuery);
			if(!ObjectFunctions.isNullOrEmpty(payrollTypesList))
			{
				log.debug("payrollTypesList size :::"  + payrollTypesList.size());
				getPayrollTypesList().addAll(payrollTypesList);
			}
			sqlQuery = " defaultStatus ='Y' ";
			List<PayrollTypes> payrollTypesList1 = adminManager.getAll(PayrollTypes.class,sqlQuery);
			if(!ObjectFunctions.isNullOrEmpty(payrollTypesList1))
			{
				log.debug("payrollTypesList size :::"  + payrollTypesList1.size());
				getPayrollTypesList().addAll(payrollTypesList1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return SUCCESS;
		
	}
	@Actions( { 
		@Action(value = "ajaxDoCreatePayrollTypes", results = { @Result(location = "payroll/ajaxDoCreatePayrollTypes.jsp", name = "success") }),
		@Action(value = "ajaxGetLoanDetailsForStaff", results = { @Result(location = "loan/ajaxViewStaffList.jsp", name = "success") })
	})
	public String ajaxdoCreatepayrollTypes() {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxdoCreatepayrollTypes' method");
		}
		try 
		{
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return SUCCESS;
		
	}
	
	@Actions( { @Action(value = "ajaxCreatePayrollTypes", results = { @Result(location = "payroll/ajaxGetPayrollTypes.jsp", name = "success") }) })
	public String ajaxCreatePayrollTypes() {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxCreatePayrollTypes' method");
		}
		try 
		{
			
				PayrollTypes payrollTypes = new PayrollTypes();
					
				payrollTypes.setPayrollName(getPayrollTypes().getPayrollName());
				payrollTypes.setPayrollDescription(getPayrollTypes().getPayrollDescription());
				payrollTypes.setPayrollType(getPayrollTypes().getPayrollType());
				payrollTypes.setCreatedDate(new Date());
				payrollTypes.setCustId(getUserCustId());
				payrollTypes.setLastAccessDate(new Date());
				payrollTypes.setLastUpdatedDate(new Date());
				payrollTypes.setDefaultStatus("N");
				payrollTypes.setCreatedById(getUser().getId());
				payrollTypes.setLastUpdatedById(getUser().getId());
				
				adminManager.save(payrollTypes);
				super.addActionMessage("Payroll Types added successfully.");
			
				ajaxDoGetPayrollTypes();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return SUCCESS;
		
	}
	
	@Actions( { 
		@Action(value = "ajaxDoCreatePayrollSettings", results = { @Result(location = "payroll/ajaxViewStaffList.jsp", name = "success") }),
		@Action(value = "ajaxDoGeneratePayroll", results = { @Result(location = "payroll/ajaxManageGeneratePayroll.jsp", name = "success") }),
		@Action(value = "ajaxDoGetLoanForStaff", results = { @Result(location = "loan/ajaxViewStaffList.jsp", name = "success") })
		
	})
	public String ajaxDoGetPayrollSettings() {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDoGetPayrollSettings' method");
		}
		try 
		{
			Customer customer = getCustomerByCustId();
			prepareStaffRolesMap(customer);
			prepareNonTeachingRolesMap(customer);
			customer = null;
			//ajaxdoCreatepayrollSettings();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return SUCCESS;
		
	}
	
	@Actions( { @Action(value = "ajaxAddPayrollSettingsForStaff", results = { @Result(location = "payroll/ajaxViewStaffListForPayroll.jsp", name = "success") }) })
	public String ajaxAddPayrollSettingsForStaff() {
	        if (log.isDebugEnabled()) {
	            log.debug("Entering 'ajaxCreatePayrollTypes' method");
	        }
	        try
	        {
	           
	                String staffId = getParamValue("tempId");
	                if(!StringFunctions.isNullOrEmpty(staffId))
	                {
	                    String monthName = new SimpleDateFormat("MMM").format(new Date());
	                    int monthNum = Integer.valueOf(new SimpleDateFormat("MM").format(new Date()));
	                    SimpleDateFormat simpleDateformat = new SimpleDateFormat("yyyy");
	                    int year = Integer.valueOf(simpleDateformat.format(new Date()));
	                   
	                    log.debug(" Month Name :: " + monthName);
	                    log.debug(" monthNum :: " + monthNum);
	                    log.debug(" year :: " + year);
	                   
	                    String sqlQuery = " staffId =" + Long.valueOf(staffId)+ " and payrollSettingStatus = '" + Constants.ACTIVE_STATUS+"' and custId = " + getUserCustId() ;
	                    List<PayrollSettings> payrollSettingsList = adminManager.getAll(PayrollSettings.class, sqlQuery);
	                    if(!ObjectFunctions.isNullOrEmpty(payrollSettingsList))
	                    {
	                        for (PayrollSettings payrollSettings : payrollSettingsList)
	                        {
	                            if(!ObjectFunctions.isNullOrEmpty(payrollSettings))
	                            {
	                                payrollSettings.setPayrollSettingStatus(Constants.REJECTED_STATUS);
	                                adminManager.save(payrollSettings);
	                                payrollSettings= null;
	                            }
	                        }
	                    }
	                    String basicSalary = getParamValue("basicSalary");
	                    
	                    List<PayrollTypes> prlList = new ArrayList<PayrollTypes>();
						sqlQuery = " defaultStatus='Y' ";
						log.debug("sqlQuery :: " + sqlQuery); 
						List<PayrollTypes> payrollList1 = adminManager.getAll(PayrollTypes.class,sqlQuery);
						if(!ObjectFunctions.isNullOrEmpty(payrollList1))
						{
							log.debug("payrollList1 Size :::"  + payrollList1.size());
							getPayrollTypesList().addAll(payrollList1);
						}
						sqlQuery = " custId = " + getUserCustId();
						log.debug("sqlQuery :: " + sqlQuery); 
						List<PayrollTypes> payrollList = adminManager.getAll(PayrollTypes.class,sqlQuery);
						if(!ObjectFunctions.isNullOrEmpty(payrollList))
						{
							log.debug("payrollDeductionList Size :::"  + payrollList.size());
							getPayrollTypesList().addAll(payrollList);
							
						}
						//setPayrollTypesList(prlList);
	                    
	                    if(!ObjectFunctions.isNullOrEmpty(getPayrollTypesList()))
	                    {
	                        for (PayrollTypes payrollTypes : getPayrollTypesList())
	                        {
	                            PayrollSettings payrollSettings = new PayrollSettings();
	                            if(!ObjectFunctions.isNullOrEmpty(payrollTypes))
		                        {
		                            String payrollName = getParamValue("payrollName"+payrollTypes.getId());
		                            String percentage = getParamValue("percentage"+payrollTypes.getId());
		                            String taxable = getParamValue("tax"+payrollTypes.getId());
		                            payrollSettings.setPayrollName(payrollName);
	                                payrollSettings.setPayrollDescription(payrollTypes.getPayrollDescription());
	                                payrollSettings.setStaffId(Long.valueOf(staffId));
	                                payrollSettings.setPayrollType(payrollTypes.getPayrollType());
	                                payrollSettings.setPayrollSettingStatus(Constants.ACTIVE_STATUS);
	                                payrollSettings.setCreatedDate(new Date());
	                                payrollSettings.setCustId(getUserCustId());
	                                payrollSettings.setLastAccessDate(new Date());
	                                payrollSettings.setLastUpdatedDate(new Date());
	                                payrollSettings.setCreatedById(getUser().getId());
	                                payrollSettings.setLastUpdatedById(getUser().getId());
	                                payrollSettings.setMonth(monthNum);
	                                payrollSettings.setYear(year);
		                            if(!"BS".equalsIgnoreCase(payrollTypes.getPayrollType()))
		                            {
		                            	if(StringFunctions.isNotNullOrEmpty(percentage))
		                            	{
			                                payrollSettings.setPercentage(Double.valueOf(percentage));
			                                payrollTypes.addPayrollSettings(payrollSettings);
			                               
			                                adminManager.save(payrollTypes);
			                            }
		                            }
		                            else if(!StringFunctions.isNullOrEmpty(basicSalary))
		                            {
		                            	payrollSettings.setBasicSalary(Double.valueOf(basicSalary));
		                            	payrollTypes.addPayrollSettings(payrollSettings);
		                                adminManager.save(payrollTypes);
		                            }
		                        }
	                        }
	                        Staff staff = (Staff)adminManager.getStaffByCustIdAndStaffIdAndStatus(Long.valueOf(staffId), getUserCustId(), Constants.YES_STRING, getUserAcademicYearId());
	                        if(!ObjectFunctions.isNullOrEmpty(staff))
	                        {
	                            staff.setStaffPayrollMonth(String.valueOf(monthNum));
	                            staff.setStaffPayrollYear(String.valueOf(year));
	                            adminManager.save(staff);
	                        }
	                    }
	                    super.addActionMessage("Payroll Settings added successfully.");
	                }
	                ajaxDoGetPayrollSettings();
	           
	                //ajaxDoGetPayrollTypes();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return SUCCESS;
	       
	    }
	 
	 
	@Actions( { @Action(value = "ajaxAddLoanForStaff", results = { @Result(location = "loan/ajaxViewStaffList1.jsp", name = "success") }) })
	public String ajaxAddLoanForStaff() {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxCreatePayrollTypes' method");
			}
			try 
			{
				
					String staffId = getParamValue("tempId");
					StaffLoanDetails staffLoanDetails = new StaffLoanDetails();
					double installmentAmount = 0.0;
					if(!StringFunctions.isNullOrEmpty(staffId))
					{
						Staff staff = (Staff) adminManager.get(Staff.class, Long.valueOf(staffId));
						List<LoanEmiDetails> loanEmiDetailsList = new ArrayList<LoanEmiDetails>();
						if(!ObjectFunctions.isNullOrEmpty(getStaffLoanDetails().getInstallments()))
						{
							LoanEmiDetails loanEmiDetails = null;
							for(int i=1; i<= getStaffLoanDetails().getInstallments(); i++){
								loanEmiDetails = new LoanEmiDetails();
								loanEmiDetails.setCustId(getUserCustId());
								loanEmiDetails.setStaffId(Long.valueOf(staffId));
								loanEmiDetails.setInstallmentNumber(i);
								loanEmiDetails.setEmiStatus("Pending");
								if(!ObjectFunctions.isNullOrEmpty(getStaffLoanDetails().getLoanAmount()))
								{
									installmentAmount = getStaffLoanDetails().getLoanAmount()/getStaffLoanDetails().getInstallments();
									loanEmiDetails.setInstallmentAmount(installmentAmount);
								}
								loanEmiDetailsList.add(loanEmiDetails);
								
							}
						}
							
							staffLoanDetails.addstaffLoanEmiDetails(loanEmiDetailsList);
							staffLoanDetails.setLoanAmount(getStaffLoanDetails().getLoanAmount());
							//staffLoanDetails.setLoanTakenDate(getStaffLoanDetails().getLoanTakenDate());
							staffLoanDetails.setLoanTakenDate(new Date());
							staffLoanDetails.setInstallments(getStaffLoanDetails().getInstallments());
							staffLoanDetails.setLoanDescription(getStaffLoanDetails().getLoanDescription());
							staffLoanDetails.setInstallmentAmount(installmentAmount);
							staffLoanDetails.setCreatedDate(new Date());
							staffLoanDetails.setCustId(getUserCustId());
							staffLoanDetails.setLastAccessDate(new Date());
							staffLoanDetails.setLastUpdatedDate(new Date());
							staffLoanDetails.setCreatedById(getUser().getId());
							staffLoanDetails.setLastUpdatedById(getUser().getId());
							staffLoanDetails.setLoanStatus("Active");
							staff.addstaffLoanDetails(staffLoanDetails);
							
							adminManager.save(staff);
							super.addActionMessage("Loan created successfully.");
					}
					ajaxDoGetPayrollSettings();
				
			} catch (Exception e) {
				e.printStackTrace();
			} 
			return SUCCESS;
			
	}
	
	
	 public String getCurrentFinancialYear()
     {
		 
		 String monthName = new SimpleDateFormat("MMM").format(new Date());
			int monthNum = Integer.valueOf(new SimpleDateFormat("MM").format(new Date()));
			SimpleDateFormat simpleDateformat = new SimpleDateFormat("yyyy");
			int year = Integer.valueOf(simpleDateformat.format(new Date()));
			
         int CurrentYear =year;
         int PreviousYear = year - 1;
         int NextYear = year + 1;
         String PreYear = String.valueOf(PreviousYear);
         String NexYear = String.valueOf(NextYear);
         String CurYear = String.valueOf(CurrentYear);
         String FinYear = null;

         if (monthNum > 3)
             FinYear = CurYear + "-" + NexYear;
         else
             FinYear = PreYear + "-" + CurYear;
         
         log.debug(FinYear);
         return FinYear.trim();
     }
	 */
}