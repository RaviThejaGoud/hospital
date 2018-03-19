<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Admin---><s:property value="plTitle"/> In/Out
				</div>
			</div>
			<div class="portlet-body">
				<div id="mainContentDiv" class="tab-content">
					<s:form action="ajaxVisitorInOutReports" theme="simple" cssClass="form-horizontal"
						namespace="/reports" onsubmit="return generatevisitorsIds();"
						id="classAndCommunity" method="post">
						<s:hidden name="anyId" value="pdf"></s:hidden>
						<s:hidden name="plTitle"></s:hidden>
							<h4 class="pageTitle bold"> Days between report </h4>
							<div class="form-body">
								<div class="form-group">
									<label class="col-md-2 control-label">
										<span class="required">*</span>From Date :
									</label>
									<div class="col-md-3">
										<div data-date-end-date="+0d" data-date-format="mm/dd/yyyy" class="input-group input-medium date date-picker">
											<input type="text" readonly="readonly" class="form-control"
												id="startDate" name="startDate"
												onchange="checkStartTimeEndTImeValidation(this);">
											<span class="input-group-btn">
												<button type="button" class="btn default">
													<i class="fa fa-calendar"></i>
												</button> </span>
										</div>
										<span class="help-block">(MM/DD/YYYY)</span>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-2 control-label">
										<span class="required">*</span>To Date :
									</label>
									<div class="col-md-3">
										<div data-date-end-date="+0d" data-date-format="mm/dd/yyyy" class="input-group input-medium date date-picker">
											<input type="text" readonly="readonly" class="form-control"
												id="endDate" name="endDate"
												onchange="checkStartTimeEndTImeValidation(this);">
											<span class="input-group-btn">
												<button type="button" class="btn default">
													<i class="fa fa-calendar"></i>
												</button> </span>
										</div>
										<span class="help-block">(MM/DD/YYYY)</span>
									</div>
								</div>
								<div class="form-actions fluid">
									<div class="col-md-offset-2 col-md-12">
										<s:submit type="submit small" value="Generate Pdf"
											cssClass="submitBt btn blue" title="generate report"
											cssStyle="cursor:pointer">
										</s:submit>
									</div>
								</div>
							</div>
						<s:else>
							<div class="alert alert-info">
								You have not created any terms.
							</div>
						</s:else>
					</s:form>
				</div>
			</div>
		</div>
	</div>
</div>
<script Language="Javascript1.2" type="text/javascript">
FormComponents.init();	
changePageTitle('Student In/Out');
function checkStartTimeEndTImeValidation(event,data){
		var startDate = $('#startDate').val();
		var endDate = $('#endDate').val();
		if (isNonEmpty(startDate) || isNonEmpty(endDate)) {
			startDate = Date.parse(startDate);
			endDate = Date.parse(endDate);
			if (endDate < startDate) {
				alert("Your end date is more than your start date.");
				$('#endDate').val("");
			}
		}
	}
	function generatevisitorsIds() {
	var startDateTrim = '';
	var endDateTrim = '';
	var startDate = $('#startDate').val();
	var endDate = $('#endDate').val();
	var startDateTrim = $.trim(startDate);
	var endDateTrim = $.trim(endDate);
	if (startDateTrim == ''){
		alert("Please select From Date.");
		return false;
	} 
	else if (endDateTrim == '') {
		alert("Please select To Date.");
		return false;
	} 
	else {
		return true;
	}
}
</script>		