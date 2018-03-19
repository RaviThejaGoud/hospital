package com.urt.persistence.model.account;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.hyniva.sms.ws.vo.account.FinancialAccountTypeVO;
import com.urt.persistence.model.base.PersistentObject;

@Entity
@Table(name = "finAccountType")
public class FinancialAccountType extends PersistentObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public String accountType;
	public String accountCode;
	
	
	@Column(name = "accountType", nullable = true, length = 100)
	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	@Column(name = "accountCode", nullable = true, length = 2)
	public String getAccountCode() {
		return accountCode;
	}

	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
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

	@Override
	public int compareTo(Object object) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public FinancialAccountTypeVO copyFromEntityToVo(FinancialAccountType financialAccountType)
	{
		FinancialAccountTypeVO financialAccountTypeVo = new FinancialAccountTypeVO();
		financialAccountTypeVo.id = financialAccountType.id;
		financialAccountTypeVo.accountType = financialAccountType.accountType;
		financialAccountTypeVo.accountCode = financialAccountType.accountCode;
		return financialAccountTypeVo;
	}
	
	public FinancialAccountType copyFromVoToEntity(FinancialAccountType  financialAccountType,FinancialAccountTypeVO financialAccountTypeVO)
	{
		if(financialAccountType.getId() == 0)
			financialAccountType.id = financialAccountTypeVO.id;
		financialAccountType.accountType = financialAccountTypeVO.accountType;
		financialAccountType.accountCode = financialAccountTypeVO.accountCode;
		return financialAccountType;
	}

}
