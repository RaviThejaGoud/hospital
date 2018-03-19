package com.urt.webapp.action.schollfee;

import java.net.InetAddress;
import java.util.Date;
import java.util.List;

import com.churchgroup.common.constants.Constants;
import com.churchgroup.util.date.DateFormatter;
import com.churchgroup.util.object.ObjectFunctions;
import com.spring.context.SpringContextAware;
import com.urt.json.JSONArray;
import com.urt.json.JSONObject;
import com.urt.persistence.model.common.AcademicYear;
import com.urt.persistence.model.common.BankMaster;
import com.urt.persistence.model.customer.Fee;
import com.urt.persistence.model.fee.ExcessPayment;
import com.urt.persistence.model.study.Student;
import com.urt.persistence.model.study.StudentFeePaidDetails;
import com.urt.persistence.model.study.StudentPayment;
import com.urt.persistence.model.study.ViewStudentFeePaymentDetails;
import com.urt.service.manager.interfaces.admin.AdminManager;
import com.urt.util.common.RayGunException;

public class SchoolFeeUtil {
	private StudentPayment studentPayment;
	
	
	
	/**
	 * @return the studentPayment
	 */
	public StudentPayment getStudentPayment() {
		return studentPayment;
	}

	/**
	 * @param studentPayment the studentPayment to set
	 */
	public void setStudentPayment(StudentPayment studentPayment) {
		this.studentPayment = studentPayment;
	}

	AdminManager adminManager = (AdminManager)SpringContextAware.getBean("adminManager");
	
	public void makeStudentFeePayment(String jsonData,long createdUserId,String paymentType,String useExcessPayment,String futureFeePaymentStatus,long classId,long classSectionId){
		try{
			if(!ObjectFunctions.isNullOrEmpty(getStudentPayment()) && !ObjectFunctions.isNullOrEmpty(getStudentPayment().getAcademicYear()) 
					&& !ObjectFunctions.isNullOrEmpty(getStudentPayment().getStudent())){
				String ipAddress = InetAddress.getLocalHost().getHostAddress();
				JSONArray feeJSONArray=new JSONArray(jsonData);
				JSONObject feeJson=null;
				Long termId = null;
				Long feeTypeId = null;
				Double totalAmount = null;
				Double payableAmount = null;
				Double discountAmount = null;
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
				AcademicYear academicYear = getStudentPayment().getAcademicYear();
				Student student = getStudentPayment().getStudent();
				StudentFeePaidDetails studentFee = null;
				StudentPayment payment = null;
				if(getStudentPayment().getId() !=0){
					payment = (StudentPayment)adminManager.get(StudentPayment.class, getStudentPayment().getId());
					
				}
				else
					payment =  new StudentPayment();
				payment.setAcademicYear(academicYear);
				payment.setCreatedById(createdUserId);
				payment.setCustId(academicYear.getCustId());
				payment.setInvoiceNumber(getStudentPayment().getInvoiceNumber());
				payment.setDeleteStatus(Constants.NO_STRING);
				payment.setIpAddress(ipAddress);
				payment.setLastUpdatedById(createdUserId);
				payment.setPaymentDate(getStudentPayment().getPaymentDate());
				payment.setPaymentType(getStudentPayment().getPaymentType());
				payment.setDiscountDesc(getStudentPayment().getDiscountDesc());
				if(!ObjectFunctions.isNullOrEmpty(getStudentPayment().getFineAmount()))
				payment.setFineAmount(getStudentPayment().getFineAmount());
				else
				payment.setFineAmount(0d);
				
				if("Y".equalsIgnoreCase(futureFeePaymentStatus))
					student.setFutureAcademicClassSecId(classSectionId);
				payment.setStudent(student);
				Date createdDate = DateFormatter.parseString(DateFormatter.YYYY_MM_DD_PATTERN, DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, new Date()));
				if (!("Enter Bank Branch Name").equalsIgnoreCase(getStudentPayment().getBranchName())) {
					payment.setBranchName(getStudentPayment().getBranchName());
				}
				if (!("DD Number").equalsIgnoreCase(getStudentPayment().getDdNumber())) {
					payment.setDdNumber(getStudentPayment().getDdNumber());
					payment.setDdDrawnDate(createdDate);
				} else {
					if (!getStudentPayment().getChequeNumber().equalsIgnoreCase("Number")) {
						payment.setChequeNumber(getStudentPayment().getChequeNumber());
						payment.setChequeIssuedDate(createdDate);
					}
				}
				BankMaster bankMaster = (BankMaster) adminManager.get(BankMaster.class, getStudentPayment().getBankMaster().getId());
				payment.setBankMaster(bankMaster);
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
						/*if("T".equalsIgnoreCase(paymentType)){
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
									studentFee = new StudentFeePaidDetails();
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
										adminManager.updateStudentPartialFeePaymentStatus(student.getId(), feeTermPartclr.getFeeId(),0);
									}else
										studentFee.setPaymentStatus("N");
									studentFee.setCommittedFeeStatus("N");
									 This condition for existing object adding more fee fee type records for committed fee scenario in first time 
									if(getStudentPayment().getId() !=0){
										studentFee.setStudentPaymentId(payment.getId());
										adminManager.save(studentFee);
									}else
									payment.addStudentFeeDetails(studentFee);
								}
							}
						}else{*/
							if("Y".equalsIgnoreCase(futureFeePaymentStatus)){
								nonFeePaidDetails = adminManager.getStudentNonPaidFutureClassFeePaymentDetailsByFeeType(student.getId(),academicYear.getId(),termId,feeTypeId,classId,classSectionId);
							}else{
								nonFeePaidDetails = adminManager.getStudentNonPaidClassFeePaymentDetails(student.getId(),academicYear.getId(),termId,feeTypeId);
							}
							if(!ObjectFunctions.isNullOrEmpty(nonFeePaidDetails)){
								fee = (Fee)adminManager.get(Fee.class,nonFeePaidDetails.getFeeId());
								studentFee = new StudentFeePaidDetails();
								studentFee.setCreatedById(createdUserId);
								studentFee.setCustId(academicYear.getCustId());
								studentFee.setFee(fee);
								studentFee.setLastUpdatedById(createdUserId);
								studentFee.setStudentId(student.getId());
								studentFee.setDeleteStatus(Constants.NO_STRING);
								studentFee.setDiscountAmount(discountAmount);
								studentFee.setPaymentAmount(payableAmount);
								studentFee.setFutureFeeStatus(futureFeePaymentStatus);
								if(nonFeePaidDetails.getPayableAmount() == studentFee.getPaymentAmount() + studentFee.getDiscountAmount()){
									studentFee.setPaymentStatus("P");
									adminManager.updateStudentPartialFeePaymentStatus(student.getId(), nonFeePaidDetails.getFeeId(),0);
								}else
									studentFee.setPaymentStatus("N");
								studentFee.setCommittedFeeStatus("N");
								/*This condition for existing object adding more fee fee type records for committed fee scenario in first time */
								if(getStudentPayment().getId() !=0){
									studentFee.setStudentPaymentId(payment.getId());
									adminManager.save(studentFee);
								}else
								payment.addStudentFeeDetails(studentFee);
							}
						//}
				     }
					feeJson=null;
			     }
				payment.setDiscountAmount(invoiceTotalDiscountAmt);
				payment.setPaidAmount(getStudentPayment().getPaidAmount());
				payment = (StudentPayment)adminManager.saveOrUpdateObject(payment);
				
				
				if(getStudentPayment().getExcessAmount() !=0 ){
					createExcessPayment(student.getAccount().getId(),getStudentPayment().getExcessAmount(),payment.getId(),0);
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
				payment = null;
				bankMaster = null;
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
}
