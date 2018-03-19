<%@ include file="/common/taglibs.jsp"%>
	<s:form action="ajaxSchoolPaymentReports" id="paymentReports" method="post"
		theme="css_xhtml" namespace="/admin">
		<fieldset>
			<div class="grid_10">
				<label>
					Day Reports:
				</label>
			</div>
			<div class="grid_10">
				<div class="grid_5">
					<sj:datepicker id="date0" name="fromDate" label="From Date" readonly="true" 
						cssClass="required textfield" required="true" changeMonth="true"
						changeYear="true" />
				</div>
				<div class="grid_5">
					<sj:datepicker id="date1" name="toDate" label="To Date"
						cssClass="textfield" changeMonth="true" readonly="true" 
						changeYear="true" />
				</div>
			</div>

			<div class="grid_10 right">
				<div class="grid_6">&nbsp;</div>
				<s:url id="doCancelNewFeeForm" includeParams="all" escapeAmp="false"></s:url>
				<sj:a href="%{doCancelNewFeeForm}" cssClass="cancelButton"
					onCompleteTopics="doDisplayFee" targets="newFeeDetails">Cancel</sj:a>
				<sj:submit   targets="newFeeDetails" value="Submit"
					indicator="indicator" cssClass="submit small" formIds="paymentReports"
					onClickTopics="schoolReportsFormValidation" />
			</div>
		</fieldset>
	</s:form>
<script type="text/javascript">

$(document).ready(function() {
	$.subscribe('schoolReportsFormValidation', function(event, data) {
		if ($('#paymentReports').valid())
			return true;
		else
			return false;
	});
	$('.numeric').numeric();
});
</script>