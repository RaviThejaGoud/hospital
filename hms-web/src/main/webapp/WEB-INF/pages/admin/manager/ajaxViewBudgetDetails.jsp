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
			<s:form action="ajaxAddSchoolAnnualBudget" theme="simple"
				id="addAnnualBudget" method="post" cssClass="form-horizontal"
				namespace="/admin">
				<s:hidden name="financialYear.id"></s:hidden>
				<s:hidden name="budgetRequest.id" id="budgetRequestId"></s:hidden>
				<s:hidden name="tempString" cssClass="tempString"></s:hidden>
				<div class="row" style="float: right; margin-right: 20px;">
					<a
						href="${pageContext.request.contextPath}/admin/schoolManagerDashboard.do"
						id="secretary" class="btn default"> <i
						class="fa fa-mail-reply"></i> Back To DashBoard
					</a>
				</div>
				<div class="form-body">
					<div class="form-group">
						<label class="col-md-2 control-label">Select Month :</label>
						<div class="col-md-3">
							<s:select list="weekDaysList" cssClass="form-control"
								name="budgetRequest.requestedMonth"
								onchange="javascript:getMonthWiseBudgetDetails(this.value);"></s:select>
						</div>
					</div>
	
					<div id="viewBudgetDetailsMonthWiseDivId">
						<jsp:include
							page="/WEB-INF/pages/admin/manager/ajaxViewBudgetDetailsMonthWise.jsp" />
					</div>
				</div>
			</s:form>
		</div>
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
	
