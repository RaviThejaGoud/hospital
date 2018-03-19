package com.urt.persistence.model.customer;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.churchgroup.util.date.DateFormatter;
import com.churchgroup.util.object.ObjectFunctions;
import com.urt.persistence.model.base.PersistentObject;

@Entity
@Table(name = "voucher")
public class Voucher extends PersistentObject {
	private long custId;
	private long financialYearId;
	private Date voucherDate;
    private String voucherNo;    
    private long fromAccountId;
    private double totalAmount; 
    protected List<VoucherDetails> voucherDetailsList;
   // private String status= "A"; //status=I(inactive) status=A(Active)  
    
    
    
   /*	public String getStatus() {
   		return status;
   	}

   	public void setStatus(String status) {
   		this.status = status;
   	}*/
    
	public long getFromAccountId() {
		return fromAccountId;
	}

	public void setFromAccountId(long fromAccountId) {
		this.fromAccountId = fromAccountId;
	}

	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
 	@JoinColumn(name="voucherId") 
	public List<VoucherDetails> getVoucherDetailsList() {
		return voucherDetailsList;
	}

	public void setVoucherDetailsList(List<VoucherDetails> voucherDetailsList) {
		this.voucherDetailsList = voucherDetailsList;
	}
	
	public void addvoucherDetailsList(VoucherDetails voucherDetails) {
		if(ObjectFunctions.isNullOrEmpty(this.getVoucherDetailsList())){
			this.voucherDetailsList=new ArrayList<VoucherDetails>();
		}
		this.voucherDetailsList.add(voucherDetails);
	}

	public long getCustId() {
		return custId;
	}

	public void setCustId(long custId) {
		this.custId = custId;
	}

	public long getFinancialYearId() {
		return financialYearId;
	}

	public void setFinancialYearId(long financialYearId) {
		this.financialYearId = financialYearId;
	}

	public Date getVoucherDate() {
		return voucherDate;
	}

	public void setVoucherDate(Date voucherDate) {
		this.voucherDate = voucherDate;
	}

	public String getVoucherNo() {
		return voucherNo;
	}

	public void setVoucherNo(String voucherNo) {
		this.voucherNo = voucherNo;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	@Transient
	public String getVoucherDateStr() {
		return DateFormatter.formatDate(DateFormatter.ddMMMyyyy_PATTERN1, this.getVoucherDate());
	}
 
	@Override
	public int compareTo(Object object) {
		// TODO Auto-generated method stub
		return 0;
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

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "";
	}
}
