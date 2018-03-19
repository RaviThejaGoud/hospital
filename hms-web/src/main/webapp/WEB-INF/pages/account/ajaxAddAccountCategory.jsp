<%@ include file="/common/taglibs.jsp"%>
<div class="form-body">
<jsp:include page="/common/messages.jsp" />
	<s:form id="newCategoryId" action="ajaxAddNewCategory" method="post" theme="simple" cssClass="form-horizontal" namespace="/account">
	<s:hidden name="financialAccountCategoryVO.id"  />
			<div class="form-group">
				<label class="control-label col-md-2"><span class="required">*</span>Account Type : </label>
				<div class="col-md-3">
					<s:if test="%{tempList2.size > 0}">
						<s:select id="accountTypeId" list="objectList" cssClass="form-control required"  listKey="id" listValue="accountType" headerKey=""
						name="financialAccountTypeVO.id" onchange="getAccountType(this.value)" disabled="true"/>
						<s:hidden name="financialAccountCategoryVO.financialAccountTypeVO.id"></s:hidden>
					</s:if>
				  	<s:else>
				  		<s:select id="accountTypeId" list="objectList" cssClass="form-control required"  listKey="id" listValue="accountType" headerKey=""
						name="financialAccountCategoryVO.financialAccountTypeVO.id" onchange="getAccountType(this.value)" />
				  	</s:else>
				  	
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-md-2"><span
								class="required">*</span>Category Name :</label>
				<div class="col-md-3">
				 <sj:textfield name="financialAccountCategoryVO.cartegoryName" id="cartegoryName" 
								cssClass="required form-control"></sj:textfield>
				</div>
			</div>
			<div class="form-group" id="statementDiv">
				<label class="control-label col-md-2"><span
								class="required">*</span>Statement Type : </label>
				<div class="col-md-3">
					<s:if test="%{tempList2.size > 0}">
						<s:select id="statmentCodeId" list="tempList"
						cssClass="form-control required" listKey="id" listValue="statementName" headerKey="" headerValue="- Select Statement Type" name="financialAccountCategoryVO.financialAccountStatementVO.id" disabled="true"/>
						<s:hidden name="financialAccountCategoryVO.financialAccountStatementVO.id"></s:hidden>
					</s:if>
					<s:else>
						<s:select id="statmentCodeId" list="tempList"
						cssClass="form-control required" listKey="id" listValue="statementName" headerKey="" headerValue="- Select Statement Type" name="financialAccountCategoryVO.financialAccountStatementVO.id"/>
					</s:else>
				    
				</div>
			</div> 
		   <div class="form-actions fluid">
			<div class="col-md-offset-2 col-md-9">
				<sj:submit value="Submit" cssClass="submitBt btn blue" onBeforeTopics="newCategoryForm" resetForm="true"
					formIds="newCategoryId" targets="accountMasterContentDiv" indicator="indicator" validate="true"/>
					 <s:url id="createAccountMaster" action="ajaxViewCreateAccountMaster" namespace="/account" />
					<sj:a  href="%{createAccountMaster}" targets="mainContentDiv" cssClass="btn default">Cancel</sj:a>
			</div>
		</div>
	</s:form>
 </div>
 <div style="margin-top: 40px;"><jsp:include page="/WEB-INF/pages/account/ajaxViewAccountCategoryDetails.jsp" /></div>
 
<script>
$(document).ready( function() {
	changePageTitle("View & Add Category");
	var editId='<s:property value="financialAccountCategoryVO.financialAccountTypeVO.accountCode"/>';
	if(isNonEmpty(editId)){
		//alert("edit="+editId);
		if(editId=="V"){
			getAccountType(2);
			$('#accountTypeId').val(2);
		}else{
			getAccountType(1);
		}
	}else{
		var type=$('select#accountTypeId').val();
		getAccountType(type);
		//alert("type="+type);
	}
}); 

$.subscribe('newCategoryForm', function(event, data) {
	if ($('#newCategoryId').valid()){
		return true;
	}else{
		event.originalEvent.options.submit=false;
	}
});
function getAccountType(type){
	if(type==1){
		$('#statementDiv').show();
	}else{
		$('#statementDiv').hide();
		$('#statmentCodeId').val('4');
	}
}
  
</script>