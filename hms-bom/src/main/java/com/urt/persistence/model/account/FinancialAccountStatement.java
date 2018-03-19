package com.urt.persistence.model.account;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.hyniva.sms.ws.vo.account.FinancialAccountStatementVO;
import com.urt.persistence.model.base.PersistentObject;

@Entity
@Table(name = "finAccountStatement")
public class FinancialAccountStatement extends PersistentObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public String statementName;
	public String statmentCode;
	
	//Asserts-AS,Expenduture-EX,income-IN and Libalities-LI
	@Column(name = "statementName", nullable = true, length = 100)
	public String getStatementName() {
		return statementName;
	}
	
	public void setStatementName(String statementName) {
		this.statementName = statementName;
	}
	@Column(name = "statmentCode", nullable = true, length = 2)
	public String getStatmentCode() {
		return statmentCode;
	}

	public void setStatmentCode(String statmentCode) {
		this.statmentCode = statmentCode;
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

	public FinancialAccountStatementVO copyFromEntityToVo(FinancialAccountStatement financialAccountStatement)
	{
		FinancialAccountStatementVO financialAccountStatementVO = new FinancialAccountStatementVO();
		financialAccountStatementVO.id = financialAccountStatement.id;
		financialAccountStatementVO.statementName = financialAccountStatement.statementName;
		financialAccountStatementVO.statmentCode = financialAccountStatement.statmentCode;
		return financialAccountStatementVO;
	}
	
	public FinancialAccountStatement copyFromVoToEntity(FinancialAccountStatement financialAccountStatement,FinancialAccountStatementVO financialAccountStatementVO)
	{
		if(financialAccountStatement.getId() == 0)
			financialAccountStatement.id = financialAccountStatementVO.id;
		financialAccountStatement.statementName = financialAccountStatementVO.statementName;
		financialAccountStatement.statmentCode = financialAccountStatementVO.statmentCode;
		return financialAccountStatement;
	}
}
