<%@ include file="/common/taglibs.jsp"%>
<div class="form-group">
	<label class="control-label col-md-4"><span class="required">*</span>Account
		Category :</label>
	<div class="col-md-6">
		<s:if test="%{tempId >0}">
			<s:select id="cartegoryId" list="tempList" cssClass="form-control required"  listKey="id" listValue="categoryAndStatementType" 
					headerValue="-Select Account Category-" headerKey="" name="financialAccountCategoryVO.id" disabled="true"/>
			<s:hidden name="financialAccountCategoryVO.id"></s:hidden>						
		</s:if>
		<s:else>
			<s:select id="cartegoryId" list="tempList" cssClass="form-control required"  listKey="id" listValue="categoryAndStatementType" 
					headerValue="-Select Account Category-" headerKey="" name="financialAccountCategoryVO.id" />
		</s:else>
		
	</div>
</div>
