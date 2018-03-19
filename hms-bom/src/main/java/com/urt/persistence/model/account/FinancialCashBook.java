package com.urt.persistence.model.account;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.churchgroup.util.date.DateFormatter;
import com.churchgroup.util.object.ObjectFunctions;
import com.hyniva.sms.ws.vo.cashbook.CashBookVO;
import com.urt.persistence.model.base.PersistentObject;
import com.urt.persistence.model.common.BankMaster;
import com.urt.persistence.model.customer.Customer;
import com.urt.persistence.model.secretary.FinancialYear;

@Entity
@Table(name = "finCashBook")
public class FinancialCashBook extends PersistentObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Customer customer;
	public FinancialYear financialYear;
	public FinancialAccountDetails financialAccountDetails; 
	
	public Date transactionDate;
	public String narration;
	public String vocherNumber;
	public String transactionType;
	public double amount;
	public String entryType;
	/*C - Cash, D - DD , CH - Cheque, BD - Bank Deposit*/
    protected String paymentType;
	protected String branchName;
    protected String chequeNumber;
    protected String ddNumber;
    protected BankMaster bankMaster;
    /*C -  Cash Book, B - Bank Book */
    protected String bookType;
    protected String transactionNumber;
    protected String accountNumber;
    
   
    
    @Column(name = "accountNumber", nullable = true, columnDefinition="varchar(20) default '' ")
	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	@Column(name = "transactionNumber", nullable = true, columnDefinition="varchar(20) default '' ")
    public String getTransactionNumber() {
		return transactionNumber;
	}

	public void setTransactionNumber(String transactionNumber) {
		this.transactionNumber = transactionNumber;
	}

	@Column(name = "bookType", nullable = false, length = 1,columnDefinition="char(1) default 'C'")
	public String getBookType() {
		return bookType;
	}

	public void setBookType(String bookType) {
		this.bookType = bookType;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getChequeNumber() {
		return chequeNumber;
	}

	public void setChequeNumber(String chequeNumber) {
		this.chequeNumber = chequeNumber;
	}

	public String getDdNumber() {
		return ddNumber;
	}

	public void setDdNumber(String ddNumber) {
		this.ddNumber = ddNumber;
	}
	
	@OneToOne
    @JoinColumn(name="bankId", insertable=true, updatable=true)
	public BankMaster getBankMaster() {
		return bankMaster;
	}

	public void setBankMaster(BankMaster bankMaster) {
		this.bankMaster = bankMaster;
	}

	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="custId", insertable=true, updatable=true)
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="financialYearId", insertable=true, updatable=true)
	public FinancialYear getFinancialYear() {
		return financialYear;
	}

	public void setFinancialYear(FinancialYear financialYear) {
		this.financialYear = financialYear;
	}
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="finAccountId", insertable=true, updatable=true)
	public FinancialAccountDetails getFinancialAccountDetails() {
		return financialAccountDetails;
	}

	public void setFinancialAccountDetails(FinancialAccountDetails financialAccountDetails) {
		this.financialAccountDetails = financialAccountDetails;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}
	@Column(name = "narration", nullable = true, length = 250)
	public String getNarration() {
		return narration;
	}

	public void setNarration(String narration) {
		this.narration = narration;
	}
	@Column(name = "vocherNumber", nullable = true, length = 20)
	public String getVocherNumber() {
		return vocherNumber;
	}

	public void setVocherNumber(String vocherNumber) {
		this.vocherNumber = vocherNumber;
	}

	public String getTransactionType() {
		return transactionType;
	}
	@Column(name = "transactionType", nullable = true, length = 1)
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
	/* "M" Manual Cash book Entry, "A" Automatic Entry like fee payment entry*/
	@Column(name = "entryType", nullable = false, length = 1,columnDefinition="char(1) default 'M'")
	public String getEntryType() {
		return entryType;
	}

	public void setEntryType(String entryType) {
		this.entryType = entryType;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	/*@Override
	public int compareTo(Object object) {
		// TODO Auto-generated method stub
		return 0;
	}*/
	public int compareTo(Object object) {
		FinancialCashBook target = (FinancialCashBook) object;
		if(!ObjectFunctions.isNullOrEmpty(target) && !ObjectFunctions.isNullOrEmpty(target.getTransactionDate())){
			if(this.getTransactionDate().compareTo(target.getTransactionDate())>0){
				return 1;
			}else if(this.getTransactionDate().compareTo(target.getTransactionDate())<0){
				return 0;
			}else if(this.getTransactionDate().compareTo(target.getTransactionDate())==0){
				return 0;
			}
		}
		return 0;
	}
	@Transient
	public String getTransactionDateStr()
	{
		if(ObjectFunctions.isNullOrEmpty(this.getTransactionDate()))
		{
			return "";
		}
		return DateFormatter.formatDate(DateFormatter.ddMMMyyyy_PATTERN1, this.getTransactionDate()); 
	}
	
	public FinancialCashBook deepCopyVoToCashBookObj(CashBookVO cashBookVO,Customer customer,FinancialYear financialYear,FinancialAccountDetails accountDetails,FinancialCashBook financialCashBook,BankMaster bankMaster){
		financialCashBook.setFinancialAccountDetails(accountDetails);
		financialCashBook.setTransactionDate(cashBookVO.getTransactionDate());
		financialCashBook.setAmount(cashBookVO.getAmount());
		financialCashBook.setNarration(cashBookVO.getNarration());
		financialCashBook.setTransactionType(cashBookVO.getTransactionType());
		financialCashBook.setVocherNumber(cashBookVO.getVocherNumber());	
		financialCashBook.setCustomer(customer);
		financialCashBook.setFinancialYear(financialYear);
		financialCashBook.setEntryType(cashBookVO.getEntryType());
		financialCashBook.setBookType(cashBookVO.getBookType());
		financialCashBook.setPaymentType(cashBookVO.getPaymentType());
		if (!("Enter Bank Branch Name").equalsIgnoreCase(cashBookVO.getBranchName())) {
			financialCashBook.setBranchName(cashBookVO.getBranchName());
		}
		if (!("DD Number").equalsIgnoreCase(cashBookVO.getDdNumber())) {
			financialCashBook.setDdNumber(cashBookVO.getDdNumber());
		}else if (!cashBookVO.getTransactionNumber().equalsIgnoreCase("Transaction Number")) {
			financialCashBook.setTransactionNumber(cashBookVO.getTransactionNumber());
		}else if (!cashBookVO.getAccountNumber().equalsIgnoreCase("Account Number")) {
			financialCashBook.setAccountNumber(cashBookVO.getAccountNumber());
		}
		else {
			if (!cashBookVO.getChequeNumber().equalsIgnoreCase("Number")) {
				financialCashBook.setChequeNumber(cashBookVO.getChequeNumber());
			}
		}
		if(!ObjectFunctions.isNullOrEmpty(bankMaster))
			financialCashBook.setBankMaster(bankMaster);
		
		return financialCashBook;
	}
	@Transient
	public String getTransactionTypeStr(){
		if("D".equalsIgnoreCase(this.getTransactionType()))
			return "Debit";
		else if("C".equalsIgnoreCase(this.getTransactionType()))
			return "Credit";
		return null;
	}
	@Transient
	public String getAccountNameAndNum(){
		return this.getFinancialAccountDetails().getAccountName();
	}
	@Transient
	public String getTransactionDateSt()
	{
		return DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_HHMMSS_PATTERN, this.getTransactionDate()); 
	}
}
