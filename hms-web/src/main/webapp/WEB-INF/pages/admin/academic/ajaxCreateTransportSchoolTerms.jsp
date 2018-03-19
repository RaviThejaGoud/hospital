<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/common/jquery.alphanumeric.js">
</script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/jQuery/jquerySession.js">
</script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/subscription/textcounter.js">
</script>
<div class="grid_14">
	<s:form action="ajaxAddTransportSchoolTerms" id="addSchoolterms" method="post"
		theme="css_xhtml">
		<s:hidden name="schoolTermId" value="%{transportSchoolTerms.id}"></s:hidden>
		<s:hidden name="tempString"></s:hidden>
		<h1>
			Create Term
		</h1>
		<p>	The parameters from Term definition are used to remind parents or
			close the term.</p>
		<fieldset>
			<div class="grid_13">
				<s:if test="%{schoolTermsList != null && !schoolTermsList.isEmpty()}">
					<div class="grid_10">
						<b>Clone Term & Fee Particulars from:</b>
					</div>
					<div class="grid_13">
						<s:iterator value="schoolTermsList">
							<div class="termDates"> 
							   <span id="termStartDateSpan" class="<s:property value='termFromDateStr'/>"></span>
							    <span id="termEndDateSpan" class="<s:property value='termToDateStr'/>"></span>
							</div>
							<div class="grid_3">
								<input type="radio" name="termDetails_"
									value='<s:property value="id"/>' />
								<s:property value="termName" />
							</div>
						</s:iterator>
					</div>
					<div class="grid_13">
						<span> (By selecting any of the above terms, this term
							would get all the defined particulars, fees and applicable
							classes) </span>
					</div>
				</s:if>
			</div>
			<div class="grid_13">
				<div class="grid_6">
					<div>
						<label class="grid_3">
							<span class="required">*</span>Term Type:
						</label>
					</div>
					<div class="grid_4">
						<sj:textfield name="transportSchoolTerms.termName" required="true"
							id="termName" cssClass="required textfield" maxlength="60"></sj:textfield>
					</div>
				</div>
				<div class="grid_7">
					<sj:textfield size="5" name="transportSchoolTerms.noOfDays" maxlength="2"
						label="Reminder Before No. of Days" cssClass="numeric textfield"
						labelposition="top"></sj:textfield>
					<span class="hintMessage">(Reminder for SMS/E-mail before due date)</span>
				</div>
			</div>
			<div class="grid_13">
				<div class="grid_6">
					<sj:datepicker id="fromDate" name="transportSchoolTerms.fromDate"
						readonly="true" label="From Date" required="true" onchange="javascript:verifyDate(this.value,this);"
						changeMonth="true" changeYear="true" cssClass="textfield required" />
				</div>
				<div class="grid_6">
					<sj:datepicker id="toDate" name="transportSchoolTerms.toDate" onchange="javascript:verifyDate(this.value,this);"
						readonly="true" label="To Date" required="true" changeMonth="true"
						changeYear="true" cssClass="textfield required" />
				</div>
				<div class="grid_6">
					<sj:datepicker id="dueDate" name="transportSchoolTerms.dueDate"
						readonly="true" label="Due Date" required="true" onchange="javascript:verifyDate(this.value,this);"
						changeMonth="true" changeYear="true" cssClass="textfield required" />
				</div>
			</div>
			<div class="grid_13">
				<sj:textarea rows="3" cols="30" name="transportSchoolTerms.mailContentDesc"
					label="Content for Email reminder" cssClass="textSmall"
					value="Please pay <Term Name> fee as soon as possible."></sj:textarea>
				<span class="hintMessage">(Do not remove <strong>< ></strong>type
					variables)</span>
			</div>
			<div class="grid_13">
				<sj:textarea rows="2" cols="30" name="transportSchoolTerms.mobileContentDesc"
					cssClass="textSmall word_count" requiredposition="left"
					label="Content for SMS reminder"
					value="<Term Name> fee <amount> due on <Date>, please ignore if already paid. <School Name>"></sj:textarea>
				<span class="hintMessage">(Do not remove <strong>< ></strong>type
					variables) <div class="counter"></div></span>
				
			</div>
			<div class="grid_13">
				<label>
					Select Applicable Classes :
				</label>
				<s:if
					test="%{schoolTermsList != null && !schoolTermsList.isEmpty()}">
					<div class="grid_4">
						<div class="grid_2">
							<input type="radio" value="ToALL" onclick="frequencyChangeClass('ToALL')" name="classBelongs" checked>
							To All
						</div>
						<div class="grid_2">
							<input type="radio" value="Class" onclick="frequencyChangeClass('Class')" name="classBelongs">
							Classes
						</div>
					</div>
					<div align="left" id="clickClass" class="grid_5">
						<div id="checkBoxFieldErrors"></div>
							<h3>
								Class With Students:
							</h3>
							<s:checkboxlist name="chkBoxSelectedIds" list="classList"
								listKey="id" listValue="className" theme="ems"
								cssClass="small" />
								<s:if test='%{classNameList.size >0}'>
							<h3>
								Class With Out Students:
							</h3>
							<s:checkboxlist name="chkBoxNotStudents" list="classNameList"
								listKey="id" listValue="className" theme="ems"
								cssClass="small" disabled="true"/>
								</s:if>
						<!--<s:checkboxlist name="chkBoxSelectedIds" id="chkBoxClassIds"
							list="classList" listKey="id" listValue="className"  theme="ems"/>
					--></div>
				</s:if>
				<s:else>
					<div>
						If you want to applicable this term fees at least add the fee one
						term.
					</div>
				</s:else>
			</div>
			<div class="grid_13">
				<s:url id="doCancelSchoolTerms" action="ajaxViewSelectedTransportFeeSettings"
					includeParams="all" escapeAmp="false">
				</s:url>
				<sj:a href="%{doCancelSchoolTerms}" cssClass="cancelButton"
					indicator="indicator" targets="feeSettingsContent">Cancel</sj:a>
				<sj:submit   targets="feeSettingsContent" value="Submit" indicator="indicator"
					formIds="addSchoolterms" cssClass="submit small"
					validate="true"/>
			</div>
		</fieldset>
	</s:form>
</div>
<script type="text/javascript">
$(document).ready(function() {
	changePageTitle("Create School Term");
	$('.numeric').numeric();
	$('.blockHeader h2').html('Manage Academics');
});
$('.numeric').numeric();
$.subscribe('doInitAddFee', function(event, data) {
	if ($('#' + data.id).is(":hidden")) {
		$('#' + data.id).show();
	} else {
		$('#' + data.id).hide();
	}
});
$(document).ready(function() {
		frequencyChangeClass('ToALL');
		$("#termName").autoCheck("${pageContext.request.contextPath}/admin/ajaxCheckTransportFeeTurmType.do",{
										minChars : 3,
										min : "no"
									});
				});
/*function CancelFeeSettings() {
	$('.cancelButton').targets('');
}*/
function frequencyChangeClass(clickButton) {
	var frequency = clickButton;
	if (frequency == 'ToALL') {
		$("[name='chkBoxSelectedIds']").attr('checked','checked');
		//$("#clickClass").hide();
	} else {
		if (frequency == 'Class') {
			$("[name='chkBoxSelectedIds']").removeAttr('checked');	
			//$("#clickClass").show();
		}
	}
}
function verifyDate(date,event) {
	var startDate = $("#fromDate").val();
	var endDate = $("#toDate").val();
	var dueDate = $("#dueDate").val();
	if(isNonEmpty(startDate) && isNonEmpty(endDate)){
		if (Date.parse(endDate) < Date.parse(startDate)) {
			alert("To Date should be greater than or equal to From Date.");
			event.value="";
		}
	}
	$('div.termDates').each(function() {
		 var termStartDateSpan=$(this).children($('span#termStartDateSpan')).attr("class");
	     var termEndDateSpan=$(this).children($('span#termStartDateSpan')).next($('span#termEndDateSpan')).attr("class");
	     //alert("termStartDateSpan="+termStartDateSpan+"&termEndDateSpan="+termEndDateSpan);
	     var termStartDate = new Date(termStartDateSpan);
	     var termEndDate = new Date(termEndDateSpan);
         var selectedStartDate = new Date(startDate);
         var selectedEndDate = new Date(endDate);
         var selectedDueDate = new Date(dueDate);
	    if(selectedStartDate <termEndDate){
				alert("From "+termStartDateSpan+ " To "+termEndDateSpan+ " This dates already terms created .Please select the different dates.");
				$('input#fromDate').val('');
			    return false;
		}
		else if(selectedStartDate > selectedDueDate){
				alert("Due Date should be greater than or equal to From Date.");
				$('input#dueDate').val('');
			    return false;
		}else{
		 return true;
		}
	 });
}
</script>