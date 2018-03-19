<%@ include file="/common/taglibs.jsp"%>
<div class="row">
<div class="col-md-12">

<div class="portlet box blue">
		<div class="portlet-title">
			<div class="caption"><i class="fa fa-globe"></i>Budget Details</div>
		</div>
		
<div class="portlet-body">
<div class="row profile">
	<div class="col-md-12">
	<s:form action="ajaxApproveORRejectSchoolAnnualBudget" theme="simple" id="addAnnualBudget" method="post" cssClass="form-horizontal" namespace="/admin">
	<s:hidden name="financialYear.id"></s:hidden>
	<s:hidden name="budgetRequest.id" id="budgetRequestId"></s:hidden>
	<s:hidden name="budgetRequest.custId" id="custId"></s:hidden>
	<s:hidden name="tempString" cssClass="tempString"></s:hidden>
	<s:hidden name="anyId" cssClass="anyId"></s:hidden>
			<div class="form-body">
				<div class="form-group">
					<label class="col-md-2 control-label">Select Month :</label>
					<div class="col-md-3">
						<s:select list="weekDaysList" cssClass="form-control" name="budgetRequest.requestedMonth" onchange="javascript:getMonthWiseBudgetDetails(this.value);"></s:select>
					</div>
				</div>
				<div id="viewBudgetDetailsMonthWiseDivId">
				<s:if test='%{user.isSecretaryPA=="Y"}'>
					<jsp:include page="/WEB-INF/pages/admin/secretary/ajaxViewBudgetDetailsMonthWise.jsp" />
				</s:if>
				<s:elseif test='%{user.isSecretary=="Y"}'>
					<jsp:include page="/WEB-INF/pages/admin/secretary/ajaxViewBudgetDetailsMonthWiseBySecretary.jsp" />
				</s:elseif>
				</div>
			</div>
		</s:form>
	  </div>
	</div>
</div>
</div>
</div>
				
				
				<script>

				
function getMonthWiseBudgetDetails(monthId) 
{
	if (isNonEmpty(monthId)) {
		$('#viewBudgetDetailsMonthWiseDivId').html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		$.ajax( {
			url : jQuery.url.getChatURL("/admin/ajaxViewBudgetDetailsMonthWise.do?budgetRequest.requestedMonth=" + monthId),
			cache : true,
			success : function(response) {
				if (isNonEmpty(response)) {
					$('#viewBudgetDetailsMonthWiseDivId').html(response);
				}
			}
		});
	}
}
</script>
	
