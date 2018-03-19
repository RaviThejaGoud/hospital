<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/common/jquery.alphanumeric.js">
</script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/jQuery/jquerySession.js">
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/subscription/textcounter.js"></script>
<div class="grid_14">
	<s:form action="ajaxEditHostelTerms" id="addHostelterms" method="post"
		theme="css_xhtml" namespace="/hostel">
		<s:hidden name="hostelTermId" value="%{hostelTerms.id}"></s:hidden>
		<s:hidden name="anyTitle"/>
		<h1>
			Update Term
		</h1>
		<p>
			You can update term here.
		</p>
		<fieldset>
			<div class="grid_4">
				<sj:textfield name="hostelTerms.hostelTermName" required="true"
					label="Term Type" id="hostelTermName" cssClass="required textfield"
					maxlength="40"></sj:textfield>
			</div>
			<div class="grid_5">
						<sj:textfield size="5" name="hostelTerms.noOfDays" maxlength="2"
							label="Remind before due date"
							cssClass="numeric textfield" labelposition="top"></sj:textfield>
						<span class="hintMessage">(To send reminder via SMS/E-mail
							before due date)</span>
			</div>
			<div class="grid_13">
				<div class="grid_4">
					<sj:datepicker id="fromDate" name="hostelTerms.fromDate" readonly="true" 
						label="From Date" required="true" cssStyle="width:141px"
						changeMonth="true" changeYear="true" cssClass="textfield required" />
				</div>
				<div class="grid_4">
					<sj:datepicker id="toDate" name="hostelTerms.toDate" readonly="true" 
						label="To Date" required="true" cssStyle="width:141px"
						changeMonth="true" changeYear="true" cssClass="textfield required" />
				</div>
				<div class="grid_4">
					<sj:datepicker id="dueDate" name="hostelTerms.dueDate" readonly="true" 
						label="Due Date" required="true" cssStyle="width:141px"
						changeMonth="true" changeYear="true" cssClass="textfield required" />
				</div>
			</div>
			<div class="grid_13">
				<sj:textarea rows="3" cols="30" name="hostelTerms.mailContentDesc"
					label="Content for Email reminder" cssClass="textSmall"></sj:textarea>
				<span class="hintMessage">(Do not remove <strong>{}</strong>type
					variables)</span>
			</div>
			<div class="grid_13">
				<sj:textarea rows="2" cols="30" name="hostelTerms.mobileContentDesc" cssClass="textSmall word_count" requiredposition="left"
					label="Content for SMS reminder"></sj:textarea>
				<span class="hintMessage">(Do not remove <strong><></strong>type
					variables)</span>
				<div class="counter"></div>
			</div>
			<div class="grid_13">
				<s:url id="doCancelHostelTerms" action="ajaxLoadManageInfoByFee"
					includeParams="all" escapeAmp="false" namespace="/hostel"></s:url>
				<sj:a href="%{doCancelHostelTerms}" cssClass="cancelButton"
					indicator="indicator" targets="feeSettingContent">Cancel</sj:a>
				<sj:submit   targets="feeSettingContent" value="Submit" indicator="indicator"
					cssClass="submit small" validate="true" />
			</div>
		</fieldset>
	</s:form>
</div>
<script type="text/javascript">
$.subscribe('doInitAddFee', function(event, data) {
	if ($('#' + data.id).is(":hidden")) {
		$('#' + data.id).show();
	} else {
		$('#' + data.id).hide();
	}
});

function frequencyChangeClass(clickButton) {
	var frequency = clickButton;
	if (frequency == 'ToALL') {
		$("#clickClass").hide();
	} else {
		if (frequency == 'Class') {
			$("#clickClass").show();
		}
	}
}
</script>
<style type="text/css">
	.ui-datepicker-trigger {
	    margin: 0;
	    padding: 0;
	}
</style>