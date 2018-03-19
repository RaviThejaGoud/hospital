package com.urt.persistence.model.account;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.churchgroup.util.object.ObjectFunctions;
import com.hyniva.sms.ws.vo.account.FinancialAccountCategoryVO;
import com.hyniva.sms.ws.vo.account.FinancialAccountStatementVO;
import com.hyniva.sms.ws.vo.account.FinancialAccountTypeVO;
import com.urt.persistence.model.base.PersistentObject;

@Entity
@Table(name = "finAccountCategory")
public class FinancialAccountCategory extends PersistentObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public long custId;
	public String cartegoryName;
	
	public FinancialAccountStatement financialAccountStatement;
	public FinancialAccountType financialAccountType; 
	
	

	public long getCustId() {
		return custId;
	}

	public void setCustId(long custId) {
		this.custId = custId;
	}
	@Column(name = "cartegoryName", nullable = true, length = 100)
	public String getCartegoryName() {
		return cartegoryName;
	}

	public void setCartegoryName(String cartegoryName) {
		this.cartegoryName = cartegoryName;
	}
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="statementId", insertable=true, updatable=true)
	public FinancialAccountStatement getFinancialAccountStatement() {
		return financialAccountStatement;
	}

	public void setFinancialAccountStatement(
			FinancialAccountStatement financialAccountStatement) {
		this.financialAccountStatement = financialAccountStatement;
	}
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="accountTypeId", insertable=true, updatable=true)
	public FinancialAccountType getFinancialAccountType() {
		return financialAccountType;
	}

	public void setFinancialAccountType(FinancialAccountType financialAccountType) {
		this.financialAccountType = financialAccountType;
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
	
	
	public FinancialAccountCategoryVO copyFromEntityToVo(FinancialAccountCategory financialAccountCategory)
	{
		FinancialAccountCategoryVO financialAccountCategoryVo = new FinancialAccountCategoryVO();
		financialAccountCategoryVo.id = financialAccountCategory.id;
		financialAccountCategoryVo.cartegoryName = financialAccountCategory.cartegoryName;
		financialAccountCategoryVo.custId = financialAccountCategory.custId;
		if(!ObjectFunctions.isNullOrEmpty(financialAccountCategory.getFinancialAccountStatement())){
			FinancialAccountStatementVO financialAccountStatementVO=new FinancialAccountStatementVO();
			financialAccountStatementVO.setStatementName(financialAccountCategory.getFinancialAccountStatement().getStatementName());
			financialAccountStatementVO.setStatmentCode(financialAccountCategory.getFinancialAccountStatement().getStatmentCode());
			financialAccountStatementVO.setId(financialAccountCategory.getFinancialAccountStatement().getId());
			financialAccountCategoryVo.setFinancialAccountStatementVO(financialAccountStatementVO);
			
		}
		if(!ObjectFunctions.isNullOrEmpty(financialAccountCategory.getFinancialAccountType())){
			FinancialAccountTypeVO financialAccountTypevo=new FinancialAccountTypeVO();
			financialAccountTypevo.setAccountCode(financialAccountCategory.getFinancialAccountType().getAccountCode());
			financialAccountTypevo.setAccountType(financialAccountCategory.getFinancialAccountType().getAccountType());
			financialAccountTypevo.setId(financialAccountCategory.getFinancialAccountType().getId());
			financialAccountCategoryVo.setFinancialAccountTypeVO(financialAccountTypevo);
		}
		return financialAccountCategoryVo;
	}
	
	public FinancialAccountCategory copyFromVoToEntity(FinancialAccountCategory financialAccountCategory,FinancialAccountCategoryVO financialAccountCategoryVO)
	{
		if(financialAccountCategory.getId() == 0)
			financialAccountCategory.id = financialAccountCategoryVO.id;
		financialAccountCategory.cartegoryName = financialAccountCategoryVO.cartegoryName;
		financialAccountCategory.custId = financialAccountCategoryVO.custId;
		return financialAccountCategory;
	}
	
	@Transient
	public String getCategoryAndStatementType(){
		StringBuilder str = new StringBuilder();
		str.append(this.getCartegoryName());
		if(!ObjectFunctions.isNullOrEmpty(this.getFinancialAccountStatement())){
			str.append("- ("+this.getFinancialAccountStatement().getStatementName()+")");
		}
		return str.toString();
	}
}
