package com.hyniva.sms.ws.vo.account;

import java.util.ArrayList;
import java.util.List;

import com.churchgroup.util.object.ObjectFunctions;
import com.hyniva.sms.ws.vo.base.SMSBaseVO;

public class FinancialParticaularAssociationMainVO extends SMSBaseVO{	
	
	
    List<FinancialParticaularAssociationVO> financialParticaularAssociationVOs;

	public List<FinancialParticaularAssociationVO> getFinancialParticaularAssociationVOs() {
		if(ObjectFunctions.isNullOrEmpty(this.financialParticaularAssociationVOs))
		{
			this.financialParticaularAssociationVOs = new ArrayList<FinancialParticaularAssociationVO>(); 
		}
		return financialParticaularAssociationVOs;
	}

	public void setFinancialParticaularAssociationVOs(
			List<FinancialParticaularAssociationVO> financialParticaularAssociationVOs) {
		this.financialParticaularAssociationVOs = financialParticaularAssociationVOs;
	}
    
    
	
}

