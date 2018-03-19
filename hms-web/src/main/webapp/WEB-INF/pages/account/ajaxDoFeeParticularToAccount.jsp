<%@ include file="/common/taglibs.jsp"%>
<div id="particularToAccount">
<jsp:include page="/common/messages.jsp" />
<s:if test="%{objectList != null && !objectList.isEmpty()}">
<s:form id="feeParticularToAccount" action="ajaxAddFeeParticularToAccount" method="post" theme="simple" cssClass="form-horizontal" namespace="/account">
	<s:hidden name="tempString" cssClass='tempString' />
	<div class="row">
		<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4"> 
						<b>Fee Type</b>
					</label>
					<div class="col-md-5">
						<label class="control-label col-md-4"><b>Account</b></label>
					</div>
					</div>
				<s:iterator value="objectList">
				<div class="form-group">
					<label class="control-label col-md-4"> 
						<span id="fpaId<s:property value='%{feeTypeId}'/>" class="<s:property value='%{fpaId}'/>"> </span>
						<s:hidden name="feeTypeId" value="%{feeTypeId}"></s:hidden>
						<s:property value="feeType" /> :
					</label>
					<div class="col-md-5">
						<s:select list="financialAccountDetailsList" listKey="id" listValue="accountName"
							id="accountId%{feeTypeId}" name="finAccountId" theme="simple"
							cssClass="form-control input-medium" headerKey="0"
							headerValue="-Select Account-" />
					</div>
					</div>
				</s:iterator>
			</div>
		</div>
		
	<div class="form-actions fluid">
		<div class="col-md-offset-2 col-md-9">
			<sj:submit value="Submit" cssClass="submitBt btn blue" onBeforeTopics="feeParticularToAccountValidation" id="submitButtonMainContent"
				resetForm="true" formIds="feeParticularToAccount" targets="particularToAccount" indicator="indicator" validate="true" />
			<s:url id="createAccountMaster" action="ajaxViewCashBookDetails" namespace="/account" />
			<sj:a href="%{createAccountMaster}" targets="mainContentDiv" cssClass="btn default">Cancel</sj:a>
		</div>
	</div>
</s:form>
</s:if>
<s:else>
	Particulars not availabe please create new particulars.	
</s:else> 
</div>
<script type="text/javascript">
changePageTitle("Associate Prticular To Account");
$.subscribe('feeParticularToAccountValidation',function(event, data) {
	var fieldErrorString = '';
	var feeTypeId = '';
	var jsonObj = [];
	var objIds;
	var allids;
	var feeTypeAccountId;
	$('input[name="feeTypeId"]').each(function() {
		feeTypeId = $(this).val();
		if (feeTypeId > 0) {
			var accountId = $('#accountId'+feeTypeId).val();
			feeTypeAccountId = $('#fpaId'+feeTypeId).attr('class');
			if (accountId > 0) {
				jsonObj.push( {
						"feeTypeAccountId" :feeTypeAccountId,
						"feeTypeId" : feeTypeId,
						"accountId" : accountId
				});
			}else {
				jsonObj.push( {
					"feeTypeAccountId" : feeTypeAccountId,
					"feeTypeId" : feeTypeId,
					"accountId" : accountId
				});
			}
		}
		
		$('#submitButtonMainContent').val('Saving...');
	});
		var jsono = { "data" : jsonObj
		}
		if (jsonObj.length > 0) {
			//alert("jsono "+JSON.stringify(jsono))
			$('input[name=tempString]').val(JSON.stringify(jsono));
		} else {
			event.originalEvent.options.submit = false;
			alert('Please assign particular to account.');
		}
	});
</script>