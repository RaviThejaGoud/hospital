<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Budget Details
				</div>
			</div>
			<div class="portlet-body">
					<div class="tabbable tabbable-custom tabbable-full-width">
						<ul class="nav nav-tabs">
							
							<li>
								<s:url id="urlBudgetRequestLink" action="ajaxDoBudgetRequestForm"  namespace="/admin"/>
								<sj:a href="%{urlBudgetRequestLink}" targets="budgetDetailsDivId" data-toggle="tab">Budget Request</sj:a>
							</li>
							<li class="active">
								<s:url id="managerBudgetRequestDetails" action="ajaxManagerBudgetRequestDetails"
									namespace="/admin">
								</s:url>
								<sj:a id="managerBudgetRequestDetails"   href="%{managerBudgetRequestDetails}" targets="mainContentDiv"
									data-toggle="tab">View Budget Details</sj:a>
							</li>
						</ul>
				<div id="budgetDetailsDivId" class="tab-content">
					<s:if test='%{objectList != null && !objectList.isEmpty()}'>
							<div class="form-group form-horizontal">
								<label class="control-label col-md-3">
									Financial Year :
								</label>
								<div class="col-md-3">
									<s:select id="financialYearId"
										list="objectList" listKey="id"
										label="Student Name" cssClass="form-control"
										listValue="yearName" name="anyId" theme="simple"
										onchange="javascript:getBudgetDetails(this.value);" />
								</div>
							</div>
					</s:if>
					<s:else><div class="alert alert-info">Please create financial year.</div></s:else>
					<div class="spaceDiv"></div>
					<div class="spaceDiv"></div>
					<div class="spaceDiv"></div>
					<div id="viewManagerBudgetRequestDivId"> 
						<%-- <jsp:include page="/WEB-INF/pages/admin/manager/ajaxViewManagerBudgetRequestsList.jsp" /> --%>
					</div>
				</div>
		</div>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function() {
	changePageTitle("Budget Request");
	getBudgetDetails($('#financialYearId').val());
});
function getBudgetDetails(financialYearId) 
{
	
	if (isNonEmpty(financialYearId)) {
		$('#viewManagerBudgetRequestDivId').html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		$.ajax( {
			url : jQuery.url.getChatURL("/admin/ajaxViewManagerBudgetRequestsList.do?financialYear.id=" + financialYearId),
			cache : true,
			success : function(response) {
				if (isNonEmpty(response)) {
					$('#viewManagerBudgetRequestDivId').html(response);
				}
			}
		});
	}
}

</script>