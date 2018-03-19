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
<div class="grid_14 commonFormTabs">
	<s:form action="ajaxAddHostelTerms" id="addHostelterms" method="post"
		theme="css_xhtml" namespace="/hostel">
		<s:hidden name="hostelTermId" value="%{hostelTerms.id}"></s:hidden>
		<s:hidden name="anyTitle"></s:hidden>
		<h1>
			Create Term
		</h1>
		<p>
			The parameters from Term definition are used to remind parents or
			close the term.
		</p>
		<fieldset>
			<div class="grid_13">
				<s:if
					test="%{hostelTermsList != null && !hostelTermsList.isEmpty()}">
					<div class="grid_10">
						<b>Clone Term & Fee Particulars from:</b>
					</div>
					<div class="grid_13">
						<s:iterator value="hostelTermsList">
							<div class="termDates"> 
							   <span id="termStartDateSpan" class="<s:property value='termFromDateStr'/>"></span>
							    <span id="termEndDateSpan" class="<s:property value='termToDateStr'/>"></span>
							</div>
							<div class="grid_3">
								<input type="radio" name="hostelTermDetails_"
									value='<s:property value="id"/>' />
								<s:property value="hostelTermName" />
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
						<sj:textfield name="hostelTerms.hostelTermName" required="true"
							id="hostelTermName" cssClass="required textfield" maxlength="60"></sj:textfield>
					</div>
				</div>
				<div class="grid_7">
					<sj:textfield size="5" name="hostelTerms.noOfDays" maxlength="2"
						label="Reminder Before No. of Days" cssClass="numeric textfield"
						labelposition="top"></sj:textfield>
					<span class="hintMessage">(Reminder for SMS/E-mail before due date)</span>
				</div>
			</div>
			<div class="grid_13">
				<div class="grid_6">
					<sj:datepicker id="fromDate" name="hostelTerms.fromDate"
						readonly="true" label="From Date" required="true" onchange="javascript:verifyDate(this.value,this);"
						changeMonth="true" changeYear="true" cssClass="textfield required" />
				</div>
				<div class="grid_6">
					<sj:datepicker id="toDate" name="hostelTerms.toDate" onchange="javascript:verifyDate(this.value,this);"
						readonly="true" label="To Date" required="true" changeMonth="true"
						changeYear="true" cssClass="textfield required" />
				</div>
				<div class="grid_6">
					<sj:datepicker id="dueDate" name="hostelTerms.dueDate"
						readonly="true" label="Due Date" required="true" onchange="javascript:verifyDate(this.value,this);"
						changeMonth="true" changeYear="true" cssClass="textfield required" />
				</div>
			</div>
			<div class="grid_13">
				<sj:textarea rows="3" cols="30" name="hostelTerms.mailContentDesc"
					label="Content for Email reminder" cssClass="textSmall"
					value="Please pay <Term Name> fee as soon as possible."></sj:textarea>
				<span class="hintMessage">(Do not remove <strong>< ></strong>type
					variables)</span>
			</div>
			<div class="grid_13">
				<sj:textarea rows="2" cols="30" name="hostelTerms.mobileContentDesc"
					cssClass="textSmall word_count" requiredposition="left"
					label="Content for SMS reminder"
					value="<Term Name> hostel fee <amount> due on <Date>, please ignore if already paid. <Hostel Name>"></sj:textarea>
				<span class="hintMessage">(Do not remove <strong>< ></strong>type
					variables)
					<div class="counter"></div> </span>
			</div>
			<div class="grid_13">
				<label>
					Select Applicable Classes:
				</label>
				<s:if
					test="%{hostelTermsList != null && !hostelTermsList.isEmpty()}">
					<div class="grid_4">
						<div class="grid_2">
							<input type="radio" value="ToALL" onclick=frequencyChangeTermClass('ToALL'); name="classBelongs" checked>
							To All
						</div>
						<div class="grid_2">
							<input type="radio" value="Class" onclick=frequencyChangeTermClass('Class'); name="classBelongs" id="checkClass">
							Classes
						</div>
					</div>
					<div align="left" id="clickClass" class="grid_4">
						<div id="checkBoxFieldErrors"></div>
						<s:checkboxlist name="chkBoxSelectedIds" id="chkBoxClassIds"
							list="classList" listKey="id" listValue="className" theme="ems" />
					</div>
				</s:if>
				<s:else>
					<div>
						If you want to applicable this term fees at least add the fee one
						term.
					</div>
				</s:else>
			</div>
			<div class="grid_13">
				<s:url id="doCancelHostelTerms" action="ajaxLoadManageInfoByFee"
					includeParams="all" escapeAmp="false" namespace="/hostel"></s:url>
				<sj:a href="%{doCancelHostelTerms}" cssClass="cancelButton"
					indicator="indicator" targets="feeSettingContent">Cancel</sj:a>
				<sj:submit   targets="feeSettingContent" value="Submit"
					indicator="indicator" formIds="addHostelterms" resetForm="true"
					cssClass="submit small" validate="true" />
			</div>
		</fieldset>
	</s:form>
</div>
<script type="text/javascript">
$(document).ready(function() {
	changePageTitle("Create Hostel Fee Term");
	$('.numeric').numeric();
	$('.blockHeader h2').html('Manage Academics');
});
$.subscribe('doInitAddFee', function(event, data) {
	if ($('#' + data.id).is(":hidden")) {
		$('#' + data.id).show();
	} else {
		$('#' + data.id).hide();
	}
});
$(document).ready(function() {
		frequencyChangeTermClass('ToALL');
		$("#hostelTermName").autoCheck("${pageContext.request.contextPath}/hostel/ajaxCheckHostelFeeTermType.do",{
										minChars : 3,
										min : "no"
									});
				});
$("input:checkbox[name=chkBoxSelectedIds]").change(function() {
	var levelId = document.getElementsByName("chkBoxSelectedIds");
	var amount = 0;
	for (i = 0; i < levelId.length; i++) {
		if (levelId[i].checked) {
			$("input#checkClass").attr('checked','checked');
		 }
		else{
		    $("input#checkAll").removeAttr("checked");
		}
	}
});
function frequencyChangeTermClass(clickButton) {
	var frequency = clickButton;
	if (frequency == 'ToALL') {
		$("[name='chkBoxSelectedIds']").attr('checked', 'checked');
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