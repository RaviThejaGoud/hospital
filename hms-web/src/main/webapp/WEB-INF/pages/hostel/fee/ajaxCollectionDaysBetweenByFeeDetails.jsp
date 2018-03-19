<%@ include file="/common/taglibs.jsp"%>
<s:if test="%{hostelTermsList != null && !hostelTermsList.isEmpty()}">
<div class="grid_3">
	<form
		action="${pageContext.request.contextPath}/reports/ajaxHostelDaysBetweenTermsFeePaidDetails.do?pdfId=pdf"
		id="addHostelTermsList" method="post"
		onsubmit="javascript:return dateFieldErrors();">
		<div class="grid_5 ">
		<strong> Days Between Report </strong>
		</div>
		<div class="grid_5 checkbox">
			<div class="grid_3">
				<div class="grid_3">
					<label>
						<span class="required">*</span>From Date:
					</label>
				</div>
				<div class="grid_3">
					<sj:datepicker id="startDate" name="startDate"
						cssStyle="width:116px;" cssClass="text small"
						yearRange="%{tempString}"  changeMonth="true"
						changeYear="true" readonly="true" />
				</div>
			</div>
			<div class="grid_3">
				<div class="grid_3">
					<label>
						<span class="required">*</span>To Date:
					</label>
				</div>
				<div class="grid_3">
					<sj:datepicker id="endDate" name="endDate" cssStyle="width:116px;"
						cssClass="text small" yearRange="%{tempString}"
						required="true" changeMonth="true" 
						readonly="true" minDate="%{numberOfDays}" />
				</div>
			</div>
		</div>
		<div class="grid_3">
			<div class="grid_3">
				&nbsp;
			</div>
			<input type="submit" value="Generate" class="submit small" />
		</div>
	</form>
	</div>
</s:if>
<s:else>
	You have not created any terms. 
</s:else>

<script type="text/javascript">
function dateFieldErrors() {
	var startDateTrim = '';
	var endDateTrim = '';
	var startDate = $('#startDate').val();
	var endDate = $('#endDate').val();
	var startDateTrim = $.trim(startDate);
	var endDateTrim = $.trim(endDate);
	if (startDateTrim == ''){
		alert("Please select from date.");
		return false;
	} 
	else if (endDateTrim == '') {
		alert("Please select to date.");
		return false;
	} 
	else {
		return true;
	}
}
</script>
<style type="text/css">
.ui-datepicker-trigger {
	margin: 0px;
	padding: 0px;
}
</style>