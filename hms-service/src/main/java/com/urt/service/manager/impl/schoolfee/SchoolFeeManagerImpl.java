package com.urt.service.manager.impl.schoolfee;

import java.net.InetAddress;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.churchgroup.common.constants.Constants;
import com.churchgroup.util.date.DateFormatter;
import com.churchgroup.util.object.ObjectFunctions;
import com.churchgroup.util.string.StringFunctions;
import com.churchgroup.util.string.StringUtil;
import com.hyniva.sms.ws.vo.cashbook.CashBookVO;
import com.hyniva.sms.ws.vo.fee.PaymentDetailsVO;
import com.urt.json.JSONArray;
import com.urt.json.JSONObject;
import com.urt.persistence.model.account.FinancialAccountDetails;
import com.urt.persistence.model.account.FinancialCashBook;
import com.urt.persistence.model.account.FinancialParticaularAssociation;
import com.urt.persistence.model.common.AcademicYear;
import com.urt.persistence.model.common.BankMaster;
import com.urt.persistence.model.customer.Customer;
import com.urt.persistence.model.customer.Fee;
import com.urt.persistence.model.fee.ChallanaPayment;
import com.urt.persistence.model.fee.ExcessPayment;
import com.urt.persistence.model.fee.StudentFeeRefund;
import com.urt.persistence.model.fee.ViewStudentFeeRefundDetails;
import com.urt.persistence.model.hostel.StudentOut;
import com.urt.persistence.model.secretary.FinancialYear;
import com.urt.persistence.model.study.ClassName;
import com.urt.persistence.model.study.Student;
import com.urt.persistence.model.study.StudentFeePaidDetails;
import com.urt.persistence.model.study.StudentPayment;
import com.urt.persistence.model.study.ViewStudentFeePaymentDetails;
import com.urt.persistence.model.study.ViewStudentTransportFeePaymentDetails;
import com.urt.persistence.model.transport.StudentTransportDetails;
import com.urt.service.manager.impl.base.UniversalManagerImpl;
import com.urt.service.manager.interfaces.admin.AdminManager;
import com.urt.service.manager.interfaces.schoolfee.SchoolFeeManager;
import com.urt.util.common.RayGunException;

/**
 * Implementation of SchoolFeeManager interface.</p>
 * 
 * <p>
 * <a href="SchoolFeeManagerImpl.java.html"><i>View Source</i></a>
 * </p>
 */

@Component
public class SchoolFeeManagerImpl  extends UniversalManagerImpl implements SchoolFeeManager {
	
	@Autowired
    public AdminManager adminManager;
	
	/*private StudentPayment studentPayment;
	*//**
	 * @return the studentPayment
	 *//*
	public StudentPayment studentPayment {
		return studentPayment;
	}

	*//**
	 * @param studentPayment the studentPayment to set
	 *//*
	public void setStudentPayment(StudentPayment studentPayment) {
		this.studentPayment = studentPayment;
	}*/
	
	public StudentPayment makeStudentFeePayment(String jsonData,long createdUserId,String paymentType,String useExcessPayment,String futureFeePaymentStatus,long classId,long classSectionId,StudentPayment studentPayment){
		try{
			if(!ObjectFunctions.isNullOrEmpty(studentPayment) && !ObjectFunctions.isNullOrEmpty(studentPayment.getAcademicYear()) 
					&& !ObjectFunctions.isNullOrEmpty(studentPayment.getStudent())){
				String ipAddress = InetAddress.getLocalHost().getHostAddress();
				JSONArray feeJSONArray=new JSONArray(jsonData);
				JSONObject feeJson=null;
				Long termId = null;
				Long feeTypeId = null;
				Double payableAmount = null;
				Double discountAmount = null;
				StringBuffer query = null;
				ViewStudentFeePaymentDetails nonFeePaidDetails = null;
				ViewStudentTransportFeePaymentDetails tranportNonFeePaidDetails = null;
				double invoiceTotalDiscountAmt = 0;
				double invoiceTotalAmt = 0;
				long feeSettingId =0;
				AcademicYear academicYear = studentPayment.getAcademicYear();
				Student student = studentPayment.getStudent();
				StudentFeePaidDetails studentFee = null;
				StringBuffer termIds = new StringBuffer();
				StudentPayment payment = new StudentPayment();
				payment.setAcademicYear(academicYear);
				payment.setCreatedById(createdUserId);
				payment.setCustId(academicYear.getCustId());
				if(studentPayment.getAcademicYear().getReceiptGenerationType().equalsIgnoreCase("A"))
					payment.setInvoiceNumber(studentPayment.getInvoiceNumber());
				else
					payment.setInvoiceString(studentPayment.getInvoiceString());
				payment.setIpAddress(ipAddress);
				payment.setLastUpdatedById(createdUserId);
				payment.setPaymentDate(DateFormatter.parseString(DateFormatter.YYYY_MM_DD_PATTERN, DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, studentPayment.getPaymentDate())));
				payment.setPaymentType(studentPayment.getPaymentType());
				payment.setDiscountDesc(studentPayment.getDiscountDesc());
				if("CL".equalsIgnoreCase(studentPayment.getPaymentType()))
					payment.setDeleteStatus("C");
				else
					payment.setDeleteStatus(Constants.NO_STRING);
				payment.setConcessionStatus(Constants.NO_STRING);
				if(!ObjectFunctions.isNullOrEmpty(studentPayment.getFineAmount()))
				payment.setFineAmount(studentPayment.getFineAmount());
				else
				payment.setFineAmount(0d);
				
				if("Y".equalsIgnoreCase(futureFeePaymentStatus))
					student.setFutureAcademicClassSecId(classSectionId);
				student =(Student)this.save(student);
				payment.setStudent(student);
				Date createdDate = DateFormatter.parseString(DateFormatter.YYYY_MM_DD_PATTERN, DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, new Date()));
				if (!("Enter Bank Branch Name").equalsIgnoreCase(studentPayment.getBranchName())) {
					payment.setBranchName(studentPayment.getBranchName());
				}
				if (!("DD Number").equalsIgnoreCase(studentPayment.getDdNumber())) {
					payment.setDdNumber(studentPayment.getDdNumber());
					payment.setDdDrawnDate(createdDate);
				}else if (!studentPayment.getTransactionNumber().equalsIgnoreCase("Transaction Number")) {
					payment.setTransactionNumber(studentPayment.getTransactionNumber());
				}
				else {
					if (!studentPayment.getChequeNumber().equalsIgnoreCase("Number")) {
						payment.setChequeNumber(studentPayment.getChequeNumber());
						payment.setChequeIssuedDate(createdDate);
					}
				}
				if(!ObjectFunctions.isNullOrEmpty(studentPayment.getBankMaster()) && (studentPayment.getBankMaster().getId())>0){
					BankMaster bankMaster = (BankMaster) adminManager.get(BankMaster.class, studentPayment.getBankMaster().getId());
					payment.setBankMaster(bankMaster);
				}
				for(int i=0;i<feeJSONArray.length();i++)
				{
					feeJson=feeJSONArray.getJSONObject(i);
					if(!ObjectFunctions.isNullOrEmpty(feeJson))
					{
						termId = Long.valueOf((String)feeJson.get("termId"));
						termIds.append(termId);
						feeTypeId = Long.valueOf((String)feeJson.get("feeTypeId"));
						if(!ObjectFunctions.isNullOrEmpty(feeJson.get("payableAmount"))){
							if(feeJson.get("payableAmount") instanceof Integer)
								payableAmount = Double.valueOf((Integer)feeJson.get("payableAmount"));
							else if(feeJson.get("payableAmount") instanceof Double)
								payableAmount = Double.valueOf((Double)feeJson.get("payableAmount"));	
							else
								payableAmount = Double.valueOf((String)feeJson.get("payableAmount"));
						}
						if(!ObjectFunctions.isNullOrEmpty(feeJson.get("discountAmount"))){
							if(feeJson.get("discountAmount") instanceof Integer)
								discountAmount = Double.valueOf((Integer)feeJson.get("discountAmount"));
							else if(feeJson.get("discountAmount") instanceof Double)
								discountAmount = Double.valueOf((Double)feeJson.get("discountAmount"));
							else
								discountAmount = Double.valueOf((String)feeJson.get("discountAmount"));
						}
						if(!ObjectFunctions.isNullOrEmpty(feeJson.get("feeSettingId"))){
							feeSettingId = Long.valueOf((String)feeJson.get("feeSettingId"));
						}
						/*if(!ObjectFunctions.isNullOrEmpty(feeJson.get("concessionAmount"))){
							if(feeJson.get("concessionAmount") instanceof Integer)
								concessionAmount = Double.valueOf((Integer)feeJson.get("concessionAmount"));
							else if(feeJson.get("concessionAmount") instanceof Double)
								concessionAmount = Double.valueOf((Double)feeJson.get("concessionAmount"));
							else
								concessionAmount = Double.valueOf((String)feeJson.get("concessionAmount"));
						}*/
						invoiceTotalDiscountAmt+= discountAmount;
						invoiceTotalAmt += payableAmount;
						if(feeSettingId !=3){
							if("Y".equalsIgnoreCase(futureFeePaymentStatus)){
								nonFeePaidDetails = adminManager.getStudentNonPaidFutureClassFeePaymentDetailsByFeeType(student.getId(),academicYear.getId(),termId,feeTypeId,classId,classSectionId);
							}else{
								nonFeePaidDetails = adminManager.getStudentNonPaidClassFeePaymentDetails(student.getId(),academicYear.getId(),termId,feeTypeId);
							}
						}else{
							tranportNonFeePaidDetails= adminManager.getStudentNonPaidTransportFeePaymentDetails(student.getId(),academicYear.getId(),termId,feeTypeId);
						}
						
						if(!ObjectFunctions.isNullOrEmpty(nonFeePaidDetails)){
							studentFee=entryStudentFeePaidDetails(nonFeePaidDetails,null,academicYear,student.getId(),createdUserId,discountAmount,payableAmount,futureFeePaymentStatus,studentPayment.getPaymentType());
							/*This condition for existing object adding more fee fee type records for committed fee scenario in first time */
							if(studentPayment.getId() !=0){
								studentFee.setStudentPaymentId(payment.getId());
								adminManager.save(studentFee);
							}else
							payment.addStudentFeeDetails(studentFee);
							nonFeePaidDetails=null;
						}else if (!ObjectFunctions.isNullOrEmpty(tranportNonFeePaidDetails)){
							studentFee=entryStudentFeePaidDetails(null,tranportNonFeePaidDetails,academicYear,student.getId(),createdUserId,discountAmount,payableAmount,futureFeePaymentStatus,studentPayment.getPaymentType());
							if(studentPayment.getId() !=0){
								studentFee.setStudentPaymentId(payment.getId());
								adminManager.save(studentFee);
							}else
							payment.addStudentFeeDetails(studentFee);
							tranportNonFeePaidDetails=null;
						}
						termIds.append(",");
				     }
					feeJson=null;
			     }
				termIds.append("0");
				payment.setDiscountAmount(invoiceTotalDiscountAmt);
				payment.setPaidAmount(studentPayment.getPaidAmount());
				payment = (StudentPayment)adminManager.save(payment);
				StudentPayment stPayment = (StudentPayment)this.get(StudentPayment.class, payment.getId());
				//Siva this is for saving total termwise balance amount and total due(balance) amount
				try{
					stPayment.setTotalBalanceAmount(studentPayment.getTotalDueAmount()-(invoiceTotalDiscountAmt+studentPayment.getPaidAmount()));
					if(!StringFunctions.isNullOrEmpty(termIds.toString())){
						Object[]  invoiceTermTotalAmount= this.get("SELECT IFNULL(SUM(IFNULL(feeAmount,0)),0) as feeAmount,IFNULL(sum(IFNULL(concessionAmount,0)),0) as concessionAmount from vw_studentClassFees where studentId="+studentPayment.getStudent().getId()+" and schoolTermId in ("+termIds.toString()+")  and description is null");
						Object[] invoiceTransTermTotalAmount =adminManager.get("SELECT ifNULL(SUM(feeAmount),0) as feeAmount,IFNULL(sum(IFNULL(concessionAmount,0)),0) as concessionAmount, transportFeeId from vw_studentTransportFees where studentId="+studentPayment.getStudent().getId()+" and schoolTermId in ("+termIds.toString()+") and description is null");
						if(!ObjectFunctions.isNullOrEmpty(invoiceTermTotalAmount)){
							double totalFeeAmount=Double.valueOf(invoiceTermTotalAmount[0].toString());
							double totalConcessionAmount=Double.valueOf(invoiceTermTotalAmount[1].toString());
	                  		 if(!ObjectFunctions.isNullOrEmpty(invoiceTransTermTotalAmount)){
	                  			totalFeeAmount +=Double.valueOf(invoiceTransTermTotalAmount[0].toString());
	                  			totalConcessionAmount +=Double.valueOf(invoiceTransTermTotalAmount[1].toString());
	                  		 }
	                  		totalFeeAmount = totalFeeAmount-totalConcessionAmount;
							Object[] totalTermPaidAmount= adminManager.get("SELECT IFNULL(SUM(IFNULL(paymentAmount,0)),0) as paymentAmount,IFNULL(sum(IFNULL(discountAmount,0)),0) as discountAmount,IFNULL(sum(IFNULL(paymentConcessionAmount,0)),0) as paymentConcessionAmount,id,termwiseTotalBalanceAmount,totalBalanceAmount from vw_studentFeePaymentDetails where studentId="+studentPayment.getStudent().getId()+" and schoolTermId in ("+termIds.toString()+")  and description is null");
							Object[] totalTransportTermPaidAmount = adminManager.get("SELECT IFNULL(SUM(IFNULL(paymentAmount,0)),0) as paymentAmount,IFNULL(sum(IFNULL(discountAmount,0)),0) as discountAmount,IFNULL(sum(IFNULL(paymentConcessionAmount,0)),0) as paymentConcessionAmount,id,termwiseTotalBalanceAmount,totalBalanceAmount from vw_studentTransportFeePaymentDetails where studentId="+studentPayment.getStudent().getId()+" and schoolTermId in ("+termIds.toString()+")  and description is null");
							double totalpaymentAmount=(Double.valueOf(totalTermPaidAmount[0].toString())+Double.valueOf(totalTransportTermPaidAmount[0].toString()));
							double totaldiscountAmount=(Double.valueOf(totalTermPaidAmount[1].toString())+Double.valueOf(totalTransportTermPaidAmount[1].toString()));
						
						if(totalFeeAmount > 0)
							//payment.setTermwiseTotalBalanceAmount(Double.valueOf(totalTermPaidAmount[0].toString())-(invoiceTotalDiscountAmt+studentPayment.getPaidAmount()));	
							stPayment.setTermwiseTotalBalanceAmount(Double.valueOf(totalFeeAmount)-(totalpaymentAmount+totaldiscountAmount));	
						}
						adminManager.save(stPayment);
					}
				}
				catch(Exception e){
					e.printStackTrace();
				}
				//End
				
				if("CL".equalsIgnoreCase(studentPayment.getPaymentType())){
					if(!ObjectFunctions.isNullOrEmpty(payment)){
						generateChallanDetails(createdUserId,payment,studentPayment);
					}
				}
				
				if(studentPayment.getExcessAmount() !=0 ){
					createExcessPayment(student.getAccount().getId(),studentPayment.getExcessAmount(),payment.getId(),0);
				}
				if("true".equalsIgnoreCase(useExcessPayment)){
					if(invoiceTotalAmt >= payment.getPaidAmount()){
						query = new StringBuffer("accountId=").append(student.getAccount().getId()).append(" and status='N' and paymentId!=").append(payment.getId());
						List<ExcessPayment> excessPayments = adminManager.getAll(ExcessPayment.class,query.toString());
						if(ObjectFunctions.isNotNullOrEmpty(excessPayments)){
							double payableAmt = invoiceTotalAmt -  payment.getPaidAmount();
								for(ExcessPayment excessPay : excessPayments){
									if(payableAmt != 0){
										excessPay.setStatus(true);
										excessPay.setUsedPaymentId(payment.getId());
										if(payableAmt >= excessPay.getExcessAmount()){
											payableAmt = payableAmt - excessPay.getExcessAmount();
											//excessPay.setUsedExcessAmount(excessPay.getExcessAmount());
											adminManager.save(excessPay);
										}else{
											//excessPay.setUsedExcessAmount(payableAmt);
											adminManager.save(excessPay);
											createExcessPayment(student.getAccount().getId(),excessPay.getExcessAmount() - payableAmt,payment.getId(),0);
											payableAmt = 0;
											break;
										}
									}else{
										break;
									}
									excessPay = null;
								}
						}
						excessPayments = null;
					}	
				}
				adminManager.checkStudentFeePaidStatus(academicYear.getId(),academicYear.getCustId(),student);
				//payment = null;
				// bankMaster = null;
				ipAddress = null;
				student = null;
				academicYear = null;
				studentFee = null;
				feeJSONArray = null;
				ipAddress = null;
				feeJson = null;
				nonFeePaidDetails = null;
				return payment;
			}
		}catch (Exception e) {
			e.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(e);raygex=null;
		}
		return null;
	}
	public StudentFeePaidDetails entryStudentFeePaidDetails(ViewStudentFeePaymentDetails nonFeePaidDetails,ViewStudentTransportFeePaymentDetails tranportNonFeePaidDetails,AcademicYear academicYear,long studentId,long createdUserId,double discountAmount,double payableAmount,String futureFeePaymentStatus,String paymentType){
		try {
			double concessionAmount=0;
			Fee fee = null;
			StudentTransportDetails studentTransportDetails=null;
			StudentFeePaidDetails studentFeePaidDetails = null;
			double totalPayableAmount=0;
			if(!ObjectFunctions.isNullOrEmpty(nonFeePaidDetails)){
				fee = (Fee)adminManager.get(Fee.class,nonFeePaidDetails.getFeeId());
				totalPayableAmount=nonFeePaidDetails.getPayableAmount();
				studentFeePaidDetails = (StudentFeePaidDetails)adminManager.get(StudentFeePaidDetails.class, "custId="+academicYear.getCustId()+" and studentId="+studentId+" and feeId="+nonFeePaidDetails.getFeeId()+" and concessionAmount !=0 ");
			}else if(!ObjectFunctions.isNullOrEmpty(tranportNonFeePaidDetails)){
				studentTransportDetails = (StudentTransportDetails)adminManager.get(StudentTransportDetails.class,tranportNonFeePaidDetails.getTransportFeeId());
				totalPayableAmount=tranportNonFeePaidDetails.getPayableAmount();
				studentFeePaidDetails = (StudentFeePaidDetails)adminManager.get(StudentFeePaidDetails.class, "custId="+academicYear.getCustId()+" and studentId="+studentId+" and studTransportDetailsId="+tranportNonFeePaidDetails.getTransportFeeId()+" and concessionAmount !=0 ");
			}
			StudentFeePaidDetails studentFee = new StudentFeePaidDetails();
			studentFee.setCreatedById(createdUserId);
			studentFee.setCustId(academicYear.getCustId());
			if(!ObjectFunctions.isNullOrEmpty(fee))
				studentFee.setFee(fee);
			if(!ObjectFunctions.isNullOrEmpty(studentTransportDetails))
				studentFee.setStudentTransportDetails(studentTransportDetails);
			studentFee.setLastUpdatedById(createdUserId);
			studentFee.setStudentId(studentId);
			if("CL".equalsIgnoreCase(paymentType))
				studentFee.setDeleteStatus("C");
			else
				studentFee.setDeleteStatus(Constants.NO_STRING);
			studentFee.setDiscountAmount(discountAmount);
			studentFee.setPaymentAmount(payableAmount);
			studentFee.setFutureFeeStatus(futureFeePaymentStatus);
			if(!ObjectFunctions.isNullOrEmpty(studentFeePaidDetails)){
				concessionAmount =studentFeePaidDetails.getConcessionAmount();
			}
			
			if(!ObjectFunctions.isNullOrEmpty(nonFeePaidDetails)){
				if(totalPayableAmount == (studentFee.getPaymentAmount() + studentFee.getDiscountAmount()+concessionAmount)){
					studentFee.setPaymentStatus("P");
					adminManager.updateStudentPartialFeePaymentStatus(studentId, nonFeePaidDetails.getFeeId(),0);
				}else
					studentFee.setPaymentStatus("N");
			}else if (!ObjectFunctions.isNullOrEmpty(tranportNonFeePaidDetails)){
				if(totalPayableAmount == (studentFee.getPaymentAmount() + studentFee.getDiscountAmount()+concessionAmount)){
					studentFee.setPaymentStatus("P");
					adminManager.updateStudentPartialFeePaymentStatus(studentId,0, tranportNonFeePaidDetails.getTransportFeeId());
				}else
					studentFee.setPaymentStatus("N");
			}
			studentFee.setCommittedFeeStatus("N");
			return studentFee;
		} catch (Exception e) {
			e.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(e);raygex=null;
		}
		return null;
	}
	public void createExcessPayment(long accountId,double excessAmount,long paymentId,long usedPaymentId)
	{
		try{
			ExcessPayment excessPayment = new ExcessPayment();
			excessPayment.setAccountId(accountId);
			excessPayment.setExcessAmount(excessAmount);
			excessPayment.setPaymentId(paymentId);
			excessPayment.setUsedPaymentId(usedPaymentId);
			adminManager.save(excessPayment);
		}catch(Exception e){
			e.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(e);raygex=null;
		}
	}

	public StudentPayment generateStudentInvoicetermWise(List<ViewStudentFeePaymentDetails> feeList, long createdUserId,double payableAmount, double discountAmount,StudentPayment studentPayment) {
		try {
			if (!ObjectFunctions.isNullOrEmpty(studentPayment) && !ObjectFunctions.isNullOrEmpty(studentPayment.getAcademicYear()) && !ObjectFunctions.isNullOrEmpty(studentPayment.getStudent())) {
				String ipAddress = InetAddress.getLocalHost().getHostAddress();
				Fee fee = null;
				StudentTransportDetails studentTransportDetails=null;
				double dsctPrctg = 0;
				double discAmt = 0;
				Double remainingDiscAmt = null;
				double assignedDiscAmt = 0;
				double invoiceTotalDiscountAmt = 0;
				double invoiceTotalAmt = 0;
				AcademicYear academicYear = studentPayment.getAcademicYear();
				Student student = studentPayment.getStudent();
				StudentFeePaidDetails studentFee = null;
				StudentPayment payment = null;
				if (studentPayment.getId() != 0) 
					payment = studentPayment;
				else
					payment = new StudentPayment();
				payment.setAcademicYear(academicYear);
				payment.setCreatedById(createdUserId);
				payment.setCustId(academicYear.getCustId());
				if(academicYear.getReceiptGenerationType().equalsIgnoreCase("A"))
					payment.setInvoiceNumber(studentPayment.getInvoiceNumber());
				else
					payment.setInvoiceString(String.valueOf(studentPayment.getInvoiceString()));
				payment.setIpAddress(ipAddress);
				payment.setLastUpdatedById(createdUserId);
				payment.setPaymentDate(studentPayment.getPaymentDate());
				payment.setPaymentType(studentPayment.getPaymentType());
				payment.setDiscountDesc(studentPayment.getDiscountDesc());
				payment.setDeleteStatus(Constants.NO_STRING);
				if (!ObjectFunctions.isNullOrEmpty(studentPayment.getFineAmount()))
					payment.setFineAmount(studentPayment.getFineAmount());
				else
					payment.setFineAmount(0d);
				payment.setStudent(student);
				invoiceTotalDiscountAmt += discountAmount;
				invoiceTotalAmt += payableAmount;

				if (ObjectFunctions.isNotNullOrEmpty(feeList)) {
					dsctPrctg = (discountAmount / payableAmount) * 100;
					remainingDiscAmt = discountAmount;
					int partclrCnt = 1;
					assignedDiscAmt = 0;
					for (ViewStudentFeePaymentDetails feeTermPartclr : feeList) {
						if(feeTermPartclr.getFeeSettingId() !=3)
							fee = (Fee) adminManager.get(Fee.class,feeTermPartclr.getFeeId());
						else
							studentTransportDetails = (StudentTransportDetails)adminManager.get(StudentTransportDetails.class, feeTermPartclr.getFeeId());
						studentFee = new StudentFeePaidDetails();
						studentFee.setCreatedById(createdUserId);
						studentFee.setCustId(academicYear.getCustId());
						if(!ObjectFunctions.isNullOrEmpty(fee))
							studentFee.setFee(fee);
						if(!ObjectFunctions.isNullOrEmpty(studentTransportDetails))
							studentFee.setStudentTransportDetails(studentTransportDetails);
						studentFee.setLastUpdatedById(createdUserId);
						studentFee.setStudentId(student.getId());
						studentFee.setDeleteStatus(Constants.NO_STRING);
						// studentFee.setFutureFeeStatus(futureFeePaymentStatus);
						if (!ObjectFunctions.isNullOrEmpty(remainingDiscAmt) && remainingDiscAmt != 0) {
							if (partclrCnt++ == feeList.size()) {
								discAmt = discountAmount - assignedDiscAmt;
							} else {
								discAmt = Math.round((dsctPrctg / 100) * feeTermPartclr.getPayableAmount());
							}
							assignedDiscAmt += discAmt;
							remainingDiscAmt = discountAmount - assignedDiscAmt;
							studentFee.setDiscountAmount(discAmt);
							studentFee.setPaymentAmount(feeTermPartclr.getPayableAmount() - discAmt);
						} else {
							if (payableAmount != 0) {
								studentFee.setDiscountAmount(0);
								if (feeTermPartclr.getPayableAmount() >= payableAmount) {
									studentFee.setPaymentAmount(payableAmount);
									payableAmount = 0d;
								} else {
									studentFee.setPaymentAmount(feeTermPartclr.getPayableAmount());
									payableAmount = payableAmount- feeTermPartclr.getPayableAmount();
								}
							} else {
								break;
							}
						}
						//particularCashBookEntry(fee.getFeeType().getId(),studentPayment,academicYear,payableAmount);
						if (feeTermPartclr.getPayableAmount() == studentFee.getPaymentAmount()+studentFee.getDiscountAmount()) {
							studentFee.setPaymentStatus("P");
							if(feeTermPartclr.getFeeSettingId() !=3)
								adminManager.updateStudentPartialFeePaymentStatus(student.getId(), feeTermPartclr.getFeeId(),0);
							else
								adminManager.updateStudentPartialFeePaymentStatus(student.getId(), 0,feeTermPartclr.getFeeId());
						} else
							studentFee.setPaymentStatus("N");
						studentFee.setCommittedFeeStatus("N");
						/*  This condition for existing object adding more fee fee type records for committed fee scenario in first time */
						if (studentPayment.getId() != 0) {
							//studentFee.setStudentPaymentId(payment.getId());
							payment.addStudentFeeDetails(studentFee);
							//adminManager.saveOrUpdateObject(studentFee);
						} else
							payment.addStudentFeeDetails(studentFee);
					}
				}
				payment.setDiscountAmount((studentPayment.getDiscountAmount()+invoiceTotalDiscountAmt));
				payment.setPaidAmount((studentPayment.getPaidAmount()+invoiceTotalAmt));
				payment.setConcessionStatus(Constants.NO_STRING);
				payment = (StudentPayment) adminManager.saveOrUpdateObject(payment);

				if (studentPayment.getExcessAmount() != 0) {
					createExcessPayment(student.getAccount().getId(),studentPayment.getExcessAmount(), payment.getId(),0);
				}
				adminManager.checkStudentFeePaidStatus(academicYear.getId(),academicYear.getCustId(), student);
				//payment = null;
				ipAddress = null;
				student = null;
				academicYear = null;
				studentFee = null;
				ipAddress = null;
				fee = null;
				feeList = null;
				return payment;
			}
		} catch (Exception e) {
			e.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(e);raygex = null;
		}
		return null;
	}
	
	public void sendNotificationForFee(long classId, long categoryId, long feeSettingId){
		try{
			List<String> classSectionIds =  this.getAll("select GROUP_CONCAT(classSectionId) from vw_classSectionDetails where classId="+classId);
			if(!ObjectFunctions.isNullOrEmpty(classSectionIds))
			{
				Object[] studentAccountIds = this.get("SELECT GROUP_CONCAT(accountId),'' FROM student WHERE status = 'Y' AND classSectionId in ("+StringUtil.convertListToString(classSectionIds)+")");
				if(!ObjectFunctions.isNullOrEmpty(studentAccountIds))
				{
					if(!ObjectFunctions.isNullOrEmpty(studentAccountIds[0]))
					{
						//Object[] parentAccountIds = this.get("SELECT GROUP_CONCAT(DISTINCT(parentId)),'' FROM Account WHERE id IN("+studentAccountIds[0].toString()+")");
						Object[] parentAccountIds = this.get("SELECT GROUP_CONCAT(DISTINCT(parentAccountId)),'' FROM Account a LEFT JOIN studentparent sp ON(sp.studentAccountId = a.id) WHERE id IN("+studentAccountIds[0].toString()+")");
						JSONObject main = new JSONObject();
						JSONObject subVal = new JSONObject();
						main.put("notificationFor", "Class Fee");
						subVal.put("description", "Class Fee created");
						subVal.put("title", "Class Fee created.");
						subVal.put("class", true);
						main.put("information", subVal);
						
						if(!ObjectFunctions.isNullOrEmpty(parentAccountIds)) 
							if(!ObjectFunctions.isNullOrEmpty(parentAccountIds[0]))
								this.sendNotificationToAndroidMobileUsersByUserIds(0,main.toString(),"("+parentAccountIds[0].toString()+")"); //To add notification for mobile app.
						//Need to send notification to Admin
						ClassName className = (ClassName) this.get(ClassName.class, classId);
						if(!ObjectFunctions.isNullOrEmpty(className)){
							Object[] toAccountIds = this.get( "SELECT GROUP_CONCAT(DISTINCT(accountId)),'' FROM vw_staffDetails WHERE roleId IN(1, 12, 32) AND custId = "+className.getCustId());
							if(!ObjectFunctions.isNullOrEmpty(toAccountIds)){
								if(!ObjectFunctions.isNullOrEmpty(toAccountIds)) this.sendNotificationToAndroidMobileUsersByUserIds(0,main.toString(),"("+toAccountIds[0].toString()+")");
							}
						}
						className = null;
						parentAccountIds = null;
					}
				}
				studentAccountIds = null;
			}
			classSectionIds = null;
		}
		catch(Exception e){ e.printStackTrace(); }
	}
	/*public void generateChallanaStudentFeePayment(String jsonData,long createdUserId,String paymentType,String useExcessPayment,String futureFeePaymentStatus,long classId,long classSectionId,StudentPayment studentPayment){
		try{
			if(!ObjectFunctions.isNullOrEmpty(studentPayment) && !ObjectFunctions.isNullOrEmpty(studentPayment.getAcademicYear()) 
					&& !ObjectFunctions.isNullOrEmpty(studentPayment.getStudent())){
				String ipAddress = InetAddress.getLocalHost().getHostAddress();
				JSONArray feeJSONArray=new JSONArray(jsonData);
				JSONObject feeJson=null;
				Long termId = null;
				Long feeTypeId = null;
				Double totalAmount = null;
				Double payableAmount = null;
				Double discountAmount = null;
				double concessionAmount = 0;
				StringBuffer query = null;
				Fee fee = null;
				List<ViewStudentFeePaymentDetails> feeList = null;
				ViewStudentFeePaymentDetails nonFeePaidDetails = null;
				double dsctPrctg = 0;
				double discAmt = 0;
				Double remainingDiscAmt = null;
				double assignedDiscAmt = 0;
				double invoiceTotalDiscountAmt = 0;
				double invoiceTotalAmt = 0;
				AcademicYear academicYear = studentPayment.getAcademicYear();
				Student student = studentPayment.getStudent();
				ChallanaFeePaidDetails studentFee = null;
				ChallanaPayment payment = null;
				if(studentPayment.getId() !=0){
					payment = (ChallanaPayment)adminManager.get(ChallanaPayment.class, studentPayment.getId());
					
				}
				else
					payment =  new ChallanaPayment();
				payment.setAcademicYear(academicYear);
				payment.setCreatedById(createdUserId);
				payment.setCustId(academicYear.getCustId());
				if(studentPayment.getAcademicYear().getReceiptGenerationType().equalsIgnoreCase("A"))
					payment.setChallanaNumber(studentPayment.getInvoiceNumber());
				else
					payment.setChallanaString(studentPayment.getInvoiceString());
				//payment.setIpAddress(ipAddress);
				payment.setLastUpdatedById(createdUserId);
				payment.setChallanaDate(studentPayment.getPaymentDate());
				//payment.setPaymentType(studentPayment.getPaymentType());
				payment.setDiscountDesc(studentPayment.getDiscountDesc());
				payment.setDeleteStatus(Constants.NO_STRING);
				//payment.setConcessionStatus(Constants.NO_STRING);
				if(!ObjectFunctions.isNullOrEmpty(studentPayment.getFineAmount()))
				payment.setFineAmount(studentPayment.getFineAmount());
				else
				payment.setFineAmount(0d);
				
				if("Y".equalsIgnoreCase(futureFeePaymentStatus))
					student.setFutureAcademicClassSecId(classSectionId);
				payment.setStudent(student);
				Date createdDate = DateFormatter.parseString(DateFormatter.YYYY_MM_DD_PATTERN, DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, new Date()));
				if (!("Enter Bank Branch Name").equalsIgnoreCase(studentPayment.getBranchName())) {
					payment.setBranchName(studentPayment.getBranchName());
				}
				if (!("DD Number").equalsIgnoreCase(studentPayment.getDdNumber())) {
					payment.setDdNumber(studentPayment.getDdNumber());
					payment.setDdDrawnDate(createdDate);
				} else {
					if (!studentPayment.getChequeNumber().equalsIgnoreCase("Number")) {
						payment.setChequeNumber(studentPayment.getChequeNumber());
						payment.setChequeIssuedDate(createdDate);
					}
				}
				if(!ObjectFunctions.isNullOrEmpty(studentPayment.getBankMaster().getId()) && (studentPayment.getBankMaster().getId())>0){
					BankMaster bankMaster = (BankMaster) adminManager.get(BankMaster.class, studentPayment.getBankMaster().getId());
					payment.setBankMaster(bankMaster);
				}
				for(int i=0;i<feeJSONArray.length();i++)
				{
					feeJson=feeJSONArray.getJSONObject(i);
					if(!ObjectFunctions.isNullOrEmpty(feeJson))
					{
						termId = Long.valueOf((String)feeJson.get("termId"));
						if("P".equalsIgnoreCase(paymentType))
							feeTypeId = Long.valueOf((String)feeJson.get("feeTypeId"));
						if(!ObjectFunctions.isNullOrEmpty(feeJson.get("totalAmount")))
							totalAmount = Double.valueOf((String)feeJson.get("totalAmount"));
						if(!ObjectFunctions.isNullOrEmpty(feeJson.get("payableAmount"))){
							if(feeJson.get("payableAmount") instanceof Integer)
								payableAmount = Double.valueOf((Integer)feeJson.get("payableAmount"));
							else if(feeJson.get("payableAmount") instanceof Double)
								payableAmount = Double.valueOf((Double)feeJson.get("payableAmount"));	
							else
								payableAmount = Double.valueOf((String)feeJson.get("payableAmount"));
						}
						if(!ObjectFunctions.isNullOrEmpty(feeJson.get("discountAmount"))){
							if(feeJson.get("discountAmount") instanceof Integer)
								discountAmount = Double.valueOf((Integer)feeJson.get("discountAmount"));
							else if(feeJson.get("discountAmount") instanceof Double)
								discountAmount = Double.valueOf((Double)feeJson.get("discountAmount"));
							else
								discountAmount = Double.valueOf((String)feeJson.get("discountAmount"));
						}
						
						invoiceTotalDiscountAmt+= discountAmount;
						invoiceTotalAmt += payableAmount;
						if("T".equalsIgnoreCase(paymentType)){
							if("Y".equalsIgnoreCase(futureFeePaymentStatus)){
								feeList = adminManager.getStudentNonPaidFutureClassFeePaymentDetails(student.getId(), academicYear.getId(), termId, classId, classSectionId);
							}else{
								//Don't change this query
								feeList = adminManager.getAllStudentNonPaidClassFeePaymentDetails(student.getId(), academicYear.getId(), termId);
							}
							if(ObjectFunctions.isNotNullOrEmpty(feeList)){
								dsctPrctg = (discountAmount/totalAmount)*100;
								remainingDiscAmt = discountAmount;
								int partclrCnt = 1;
								assignedDiscAmt = 0;
								for(ViewStudentFeePaymentDetails feeTermPartclr : feeList){
									fee = (Fee)adminManager.get(Fee.class,feeTermPartclr.getFeeId());
									studentFee = new ChallanaFeePaidDetails();
									studentFee.setCreatedById(createdUserId);
									studentFee.setCustId(academicYear.getCustId());
									studentFee.setFee(fee);
									studentFee.setLastUpdatedById(createdUserId);
									studentFee.setStudentId(student.getId());
									studentFee.setDeleteStatus(Constants.NO_STRING);
									studentFee.setFutureFeeStatus(futureFeePaymentStatus);
									if(!ObjectFunctions.isNullOrEmpty(remainingDiscAmt) && remainingDiscAmt !=0 ){
										if(partclrCnt++ == feeList.size()){
											discAmt = discountAmount - assignedDiscAmt;
										}else{
											discAmt =  Math.round((dsctPrctg/100)*feeTermPartclr.getPayableAmount());
										}
										assignedDiscAmt += discAmt;
										remainingDiscAmt = discountAmount - assignedDiscAmt;
										studentFee.setDiscountAmount(discAmt);
										studentFee.setPaymentAmount(feeTermPartclr.getPayableAmount() - discAmt);
									}else{
										if(payableAmount != 0){
											studentFee.setDiscountAmount(0);
											if(feeTermPartclr.getPayableAmount() >= payableAmount){
												studentFee.setPaymentAmount(payableAmount);
												payableAmount = 0d;
											}else{
												studentFee.setPaymentAmount(feeTermPartclr.getPayableAmount());
												payableAmount = payableAmount - feeTermPartclr.getPayableAmount();
											}
										}else{
											break;
										}
									}
									if(feeTermPartclr.getPayableAmount() == studentFee.getPaymentAmount() + studentFee.getDiscountAmount()){
										studentFee.setPaymentStatus("P");
										//adminManager.updateStudentPartialFeePaymentStatus(student.getId(), feeTermPartclr.getFeeId());
									}else
										studentFee.setPaymentStatus("N");
									studentFee.setCommittedFeeStatus("N");
									 This condition for existing object adding more fee fee type records for committed fee scenario in first time 
									if(studentPayment.getId() !=0){
										studentFee.setChallanaPaymentId(payment.getId());
										adminManager.save(studentFee);
									}else
									payment.addChallanaFeeDetails(studentFee);
								}
							}
						}else{
							if("Y".equalsIgnoreCase(futureFeePaymentStatus)){
								nonFeePaidDetails = adminManager.getStudentNonPaidFutureClassFeePaymentDetailsByFeeType(student.getId(),academicYear.getId(),termId,feeTypeId,classId,classSectionId);
							}else{
								nonFeePaidDetails = adminManager.getStudentNonPaidClassFeePaymentDetails(student.getId(),academicYear.getId(),termId,feeTypeId);
							}
							if(!ObjectFunctions.isNullOrEmpty(nonFeePaidDetails)){
								fee = (Fee)adminManager.get(Fee.class,nonFeePaidDetails.getFeeId());
								ChallanaFeePaidDetails studentFeePaidDetails = (ChallanaFeePaidDetails)adminManager.get(ChallanaFeePaidDetails.class, "custId="+academicYear.getCustId()+" and studentId="+student.getId()+" and feeId="+nonFeePaidDetails.getFeeId()+" and concessionAmount !=0 ");
								studentFee = new ChallanaFeePaidDetails();
								studentFee.setCreatedById(createdUserId);
								studentFee.setCustId(academicYear.getCustId());
								studentFee.setFee(fee);
								studentFee.setLastUpdatedById(createdUserId);
								studentFee.setStudentId(student.getId());
								studentFee.setDeleteStatus(Constants.NO_STRING);
								studentFee.setDiscountAmount(discountAmount);
								studentFee.setPaymentAmount(payableAmount);
								studentFee.setFutureFeeStatus(futureFeePaymentStatus);
								if(!ObjectFunctions.isNullOrEmpty(studentFeePaidDetails)){
									concessionAmount =studentFeePaidDetails.getConcessionAmount();
								}
								
								if(nonFeePaidDetails.getPayableAmount() == (studentFee.getPaymentAmount() + studentFee.getDiscountAmount()+concessionAmount)){
									studentFee.setPaymentStatus("P");
									//adminManager.updateStudentPartialFeePaymentStatus(student.getId(), nonFeePaidDetails.getFeeId());
								}else
									studentFee.setPaymentStatus("N");
								studentFee.setCommittedFeeStatus("N");
								This condition for existing object adding more fee fee type records for committed fee scenario in first time 
								if(studentPayment.getId() !=0){
									studentFee.setChallanaPaymentId(payment.getId());
									adminManager.save(studentFee);
								}else
								payment.addChallanaFeeDetails(studentFee);
							}
						}
				     }
					feeJson=null;
			     }
				payment.setDiscountAmount(invoiceTotalDiscountAmt);
				payment.setPaidAmount(studentPayment.getPaidAmount());
				payment = (ChallanaPayment)adminManager.save(payment);
				
				
				if(studentPayment.getExcessAmount() !=0 ){
					createExcessPayment(student.getAccount().getId(),studentPayment.getExcessAmount(),payment.getId(),0);
				}
				if("true".equalsIgnoreCase(useExcessPayment)){
					if(invoiceTotalAmt >= payment.getPaidAmount()){
						query = new StringBuffer("accountId=").append(student.getAccount().getId()).append(" and status='N' and paymentId!=").append(payment.getId());
						List<ExcessPayment> excessPayments = adminManager.getAll(ExcessPayment.class,query.toString());
						if(ObjectFunctions.isNotNullOrEmpty(excessPayments)){
							double payableAmt = invoiceTotalAmt -  payment.getPaidAmount();
								for(ExcessPayment excessPay : excessPayments){
									if(payableAmt != 0){
										excessPay.setStatus(true);
										excessPay.setUsedPaymentId(payment.getId());
										if(payableAmt >= excessPay.getExcessAmount()){
											payableAmt = payableAmt - excessPay.getExcessAmount();
											//excessPay.setUsedExcessAmount(excessPay.getExcessAmount());
											adminManager.save(excessPay);
										}else{
											//excessPay.setUsedExcessAmount(payableAmt);
											adminManager.save(excessPay);
											createExcessPayment(student.getAccount().getId(),excessPay.getExcessAmount() - payableAmt,payment.getId(),0);
											payableAmt = 0;
											break;
										}
									}else{
										break;
									}
									excessPay = null;
								}
						}
						excessPayments = null;
					}	
				}
				//adminManager.checkStudentFeePaidStatus(academicYear.getId(),academicYear.getCustId(),student);
				payment = null;
				// bankMaster = null;
				ipAddress = null;
				student = null;
				academicYear = null;
				studentFee = null;
				feeJSONArray = null;
				ipAddress = null;
				fee = null;
				feeJson = null;
				feeList = null;
				nonFeePaidDetails = null;
			}
		}catch (Exception e) {
			e.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(e);raygex=null;
		}
	}*/
	public void particularCashBookEntry(long feeTypeId,StudentPayment studentPayment,AcademicYear academicYear){
		try {
			FinancialParticaularAssociation particaularAssociation = (FinancialParticaularAssociation)this.get(FinancialParticaularAssociation.class, "paticularId="+feeTypeId);
			if(!ObjectFunctions.isNullOrEmpty(particaularAssociation)){
				FinancialAccountDetails financialAccountDetails =particaularAssociation.getFinancialAccountDetails();
				if("Y".equalsIgnoreCase(financialAccountDetails.getCustomer().getAccountModuleUsing())){
					String paymentDate = DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN,studentPayment.getPaymentDate());
					List<String> feeTypeIds=adminManager.getAll("select IFNULL(CONVERT(group_concat(paticularId), CHAR),0) from finFeeParticularAssociation where finAccountId="+financialAccountDetails.getId());
					List<String> feeIds = adminManager.getAll("select IFNULL(CONVERT(group_concat(id), CHAR),0) from Fee where custId="+academicYear.getCustId()+" and academicYearId="+academicYear.getId()+" and feeTypeId in ("+StringUtil.convertListToString(feeTypeIds)+")");
					Object[] particularAmount = this.get("select IFNULL(SUM(paymentAmount),0) paymentAmount,studentId from studentFeePaidDetails where custId="+academicYear.getCustId()+" and feeId in ("+StringUtil.convertListToString(feeIds)+") and deleteStatus='N' and studentPaymentId in (select id from studentPayment where custId="+academicYear.getCustId()+" and academicYearId="+academicYear.getId()+" and paymentDate='"+paymentDate+" 00:00:00' )");
					if(!ObjectFunctions.isNullOrEmpty(particularAmount)){
						FinancialCashBook financialCashBook = null;
						FinancialYear financialYear = (FinancialYear)this.get(FinancialYear.class, "status='Y' ");
						double totalPartAmount = (Double.valueOf(particularAmount[0].toString()));//+payableAmount
						financialCashBook = (FinancialCashBook)this.get(FinancialCashBook.class, "custId="+academicYear.getCustId()+" and financialYearId="+financialYear.getId()+" and finAccountId="+financialAccountDetails.getId()+" and transactionDate='"+paymentDate+" 00:00:00' and entryType='A' ");
						
						CashBookVO cashBookVO= prepareCashBookVo(studentPayment,totalPartAmount);
						if(ObjectFunctions.isNullOrEmpty(financialCashBook)){
							financialCashBook = new FinancialCashBook();
						}
						if(!ObjectFunctions.isNullOrEmpty(cashBookVO)){
							financialCashBook=financialCashBook.deepCopyVoToCashBookObj(cashBookVO, financialAccountDetails.getCustomer(),financialYear, financialAccountDetails,financialCashBook,null);
							this.saveOrUpdateObject(financialCashBook);
						}
						
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(e);raygex=null;
		}
	}
	
	public void chashBookPaymentEntry(StudentPayment studentPayment){
		try {
			if(!ObjectFunctions.isNullOrEmpty(studentPayment.getStudentFeePaidDetails()))
			{
				for(StudentFeePaidDetails feePaidDetails : studentPayment.getStudentFeePaidDetails()){
					if(!ObjectFunctions.isNullOrEmpty(feePaidDetails.getFee()))
						particularCashBookEntry(feePaidDetails.getFee().getFeeType().getId(), studentPayment, studentPayment.getAcademicYear());
					else if (!ObjectFunctions.isNullOrEmpty(feePaidDetails.getStudentTransportDetails()))
						particularCashBookEntry(feePaidDetails.getStudentTransportDetails().getFeeType().getId(), studentPayment, studentPayment.getAcademicYear());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(e);raygex=null;
		}
	}
	
	public CashBookVO prepareCashBookVo(StudentPayment studentPayment,double totalPartAmount){
		try {
			CashBookVO cashBookVO = new CashBookVO();
			cashBookVO.setTransactionDate(DateFormatter.parseString(DateFormatter.YYYY_MM_DD_PATTERN, DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, studentPayment.getPaymentDate())));
			cashBookVO.setAmount(totalPartAmount);
			cashBookVO.setNarration("Student Payment");
			cashBookVO.setTransactionType("C");
			cashBookVO.setVocherNumber("0");	
			cashBookVO.setEntryType("A");
			if("C".equalsIgnoreCase(studentPayment.getPaymentType()))
				cashBookVO.setBookType("C");
			else{
				cashBookVO.setBookType("B");
				if (!("Enter Bank Branch Name").equalsIgnoreCase(studentPayment.getBranchName())) {
					cashBookVO.setBranchName(studentPayment.getBranchName());
				}else
					cashBookVO.setBranchName("Enter Bank Branch Name");
				if(!StringFunctions.isNullOrEmpty(studentPayment.getDdNumber())){
					if (!("DD Number").equalsIgnoreCase(studentPayment.getDdNumber())) {
						cashBookVO.setDdNumber(studentPayment.getDdNumber());
						//cashBookVO.setDdDrawnDate(createdDate);
					}else
						cashBookVO.setDdNumber("DD Number");
				}
				if(!StringFunctions.isNullOrEmpty(studentPayment.getTransactionNumber())){
					if (!studentPayment.getTransactionNumber().equalsIgnoreCase("Transaction Number")) {
						cashBookVO.setTransactionNumber(studentPayment.getTransactionNumber());
					}else
						cashBookVO.setTransactionNumber("Transaction Number");
				}
				if(!StringFunctions.isNullOrEmpty(studentPayment.getChequeNumber())){
					if (!studentPayment.getChequeNumber().equalsIgnoreCase("Number")) {
						cashBookVO.setChequeNumber(studentPayment.getChequeNumber());
						//cashBookVO.setChequeIssuedDate(createdDate);
					}else
						cashBookVO.setChequeNumber("Number");
				}
				
				if(!ObjectFunctions.isNullOrEmpty(studentPayment.getBankMaster()) && (studentPayment.getBankMaster().getId())>0){
					cashBookVO.setBankId(studentPayment.getBankMaster().getId());
				}
			}
			return cashBookVO;
		} catch (Exception e) {
			e.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(e);raygex=null;
		}
		return null;
	}
	
	public void generateChallanDetails(long createdUserId,StudentPayment payment,StudentPayment studentPayment){
		try {
			ChallanaPayment challanaPayment = new ChallanaPayment();
			challanaPayment.setCreatedDate(new Date());
			challanaPayment.setCreatedById(createdUserId);
			challanaPayment.setLastAccessDate(new Date());
			challanaPayment.setLastUpdatedById(createdUserId);
			challanaPayment.setCustId(payment.getCustId());
			challanaPayment.setAcademicYear(payment.getAcademicYear());
			challanaPayment.setStudent(payment.getStudent());
			challanaPayment.setDeleteStatus(Constants.NO_STRING);
			challanaPayment.setChallanaNumber(Long.valueOf(studentPayment.getInvoiceString()));
			challanaPayment.setStudentPayment(payment);
			challanaPayment.setChallanaDate(payment.getPaymentDate());
			this.save(challanaPayment);
		} catch (Exception e) {
			e.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(e);raygex=null;
		}
	}
	public List<ViewStudentFeeRefundDetails> getFeeRefundStudentsList(long userCustId, long userAcademicYearId){
		try{
			List<ViewStudentFeeRefundDetails> refundsList = this.getAll(ViewStudentFeeRefundDetails.class,"custId="+userCustId+" AND academicYearId="+userAcademicYearId);
			/*StringBuilder query= new StringBuilder("select vs.studentId,vs.admissionNumber,vs.firstName,vs.lastName,vs.className,vs.section,sfr.totalFeeAmount, sfr.refundAmount,sfr.refundDate,sfr.custId,sfr.academicYearId from studentFeeRefund sfr left join vw_studentDetails vs on(vs.studentId=sfr.studentId) where sfr.custId="+userCustId+" and sfr.academicYearId="+userAcademicYearId);
			List objList = this.getAll(query.toString());*/
			if(!ObjectFunctions.isNullOrEmpty(refundsList))
				return refundsList;
		}
		 catch (Exception e) {
				e.printStackTrace();
		}
		return null;
	}
	public void saveStudentFeerefund(StudentFeeRefund studentFeeRefund, long studentId, long userCustId,AcademicYear academicYear, long createdUserId){
		try{
			if(ObjectFunctions.isNullOrEmpty(studentFeeRefund)){
				studentFeeRefund = new StudentFeeRefund();
			}
			if(!ObjectFunctions.isNullOrEmpty(studentFeeRefund.getBankMaster().getId()) && studentFeeRefund.getBankMaster().getId() > 0){
				BankMaster bankMaster = (BankMaster)this.get(BankMaster.class,studentFeeRefund.getBankMaster().getId());
				studentFeeRefund.setBankMaster(bankMaster);
			}else studentFeeRefund.setBankMaster(null);
				studentFeeRefund.setStudentId(studentId);
				studentFeeRefund.setCustId(userCustId);
				studentFeeRefund.setAcademicYear(academicYear);
				studentFeeRefund.setInvoiceString(String.valueOf(studentFeeRefund.getInvoiceNumber()));
				studentFeeRefund.setCreatedById(createdUserId);
				adminManager.save(studentFeeRefund);
		}
		 catch (Exception e) {
				e.printStackTrace();
		}
	}
	
	public int validatePaymentInvoiceNumber(Customer customer,AcademicYear academicYear,String invoiceNumber){
		StudentPayment payment = null;
		try {
			StringBuilder query = new StringBuilder("custId="+customer.getId());
			if(customer.isAcademicWiseFeeReceipt())
				query.append(" and academicYearId=").append(academicYear.getId());
			if(academicYear.getReceiptGenerationType().equalsIgnoreCase("A"))
				query.append(" and invoiceNumber=").append(invoiceNumber);
			else
				query.append(" and invoiceString='").append(invoiceNumber).append("'");
			payment = (StudentPayment)this.get(StudentPayment.class, query.toString());
			if(ObjectFunctions.isNullOrEmpty(payment))
				return 1;
			else
				return 0;
		} catch (Exception e) {
			e.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(e);raygex=null;
			return 0;
		}finally{
			payment = null;
		}
	}
	
}