<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/common/jquery.alphanumeric.js">
</script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/jQuery/jquerySession.js">
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/subscription/textcounter.js"></script>
<div class="grid_14">
	<s:form action="ajaxEditTransportSchoolTerms" id="addSchoolterms" method="post"
		theme="css_xhtml">
		<s:hidden name="schoolTermId" value="%{transportSchoolTerms.id}"></s:hidden>
		<s:hidden name="tempString"/>
		<h1>
			Update Term
		</h1>
		<p>
			You can update term here.
		</p>
		<fieldset>
			<div class="grid_4">
				<sj:textfield name="transportSchoolTerms.termName" required="true"
					label="Term Type" id="termName" cssClass="required textfield"
					maxlength="40"></sj:textfield>
			</div>
			<div class="grid_5">
						<sj:textfield size="5" name="transportSchoolTerms.noOfDays" maxlength="2"
							label="Remind before due date"
							cssClass="numeric textfield" labelposition="top"></sj:textfield>
						<span class="hintMessage">(To send reminder via SMS/E-mail
							before due date)</span>
			</div>
			<div class="grid_13">
				<div class="grid_4">
					<sj:datepicker id="fromDate" name="transportSchoolTerms.fromDate" readonly="true" 
						label="From Date" required="true" cssStyle="width:141px"
						changeMonth="true" changeYear="true" cssClass="textfield required" />
				</div>
				<div class="grid_4">
					<sj:datepicker id="toDate" name="transportSchoolTerms.toDate" readonly="true" 
						label="To Date" required="true" cssStyle="width:141px"
						changeMonth="true" changeYear="true" cssClass="textfield required" />
				</div>
				<div class="grid_4">
					<sj:datepicker id="dueDate" name="transportSchoolTerms.dueDate" readonly="true" 
						label="Due Date" required="true" cssStyle="width:141px"
						changeMonth="true" changeYear="true" cssClass="textfield required" />
				</div>
			</div>
			<div class="grid_13">
				<sj:textarea rows="3" cols="30" name="transportSchoolTerms.mailContentDesc"
					label="Content for Email reminder" cssClass="textSmall"></sj:textarea>
				<span class="hintMessage">(Do not remove <strong>{}</strong>type
					variables)</span>
			</div>
			<div class="grid_13">
				<sj:textarea rows="2" cols="30" name="transportSchoolTerms.mobileContentDesc" cssClass="textSmall word_count" requiredposition="left"
					label="Content for SMS reminder"></sj:textarea>
				<span class="hintMessage">(Do not remove <strong><></strong>type
					variables)</span>
				<div class="counter"></div>
			</div>
			<!--<div class="grid_14">
				<label>
					Select Applicable Classes:
				</label>
				<s:if
					test="%{schoolTermsList != null && !schoolTermsList.isEmpty()}">
					<div class="grid_4">
						<div class="grid_2">
							<input type="radio" value="ToALL" onclick=frequencyChangeClass('ToALL'); name="classBelongs" checked>
							To All
						</div>
						<div class="grid_2">
							<input type="radio" value="Class" onclick=frequencyChangeClass('Class'); name="classBelongs">
							Classes
						</div>
					</div>
					<div align="left" style="display: none;" id="clickClass"
						class="grid_4">
						<div id="checkBoxFieldErrors"></div>
						<s:checkboxlist name="chkBoxSelectedIds" id="chkBoxClassIds"
							list="classList" listKey="id" listValue="className" />
					</div>
				</s:if>
				<s:else>
					<div>
						If you want to applicable this term fees at least add the fee one
						term
					</div>
				</s:else>
			</div>

			-->
			<div class="grid_13">
				<s:url id="doCancelSchoolTerms" action="ajaxViewSelectedTransportFeeSettings"
					includeParams="all" escapeAmp="false"></s:url>
				<sj:a href="%{doCancelSchoolTerms}" cssClass="cancelButton"
					indicator="indicator" targets="feeSettingsContent">Cancel</sj:a>
				<sj:submit   targets="feeSettingsContent" value="Submit" indicator="indicator"
					cssClass="submit small" validate="true" />
			</div>
		</fieldset>
	</s:form>
</div>
<script type="text/javascript">
$('.numeric').numeric();
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