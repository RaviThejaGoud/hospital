<%@ include file="/common/taglibs.jsp"%>
<span id="startDateSpan" class="<s:property value='startDate'/>"></span>
<span id="endDateSpan" class="<s:property value='endDate'/>"></span>
<span id="toDateSpan" class="<s:property value='toDate'/>"></span>
<span id="admissionAcademicyear"
	class="<s:property value='#session.admissionAcademicYearId'/>"></span>
<span id="usrAcadmicId" class="<s:property value='userAcademicYearId'/>"></span>
<s:if test='%{tempString == "admissionTerms"}'>
	<s:form action="ajaxEditAdmissionSchoolTerms" id="addSchoolterms"
		cssClass="form-horizontal" method="post" theme="simple"
		namespace="/schoolfee">
		<s:hidden name="schoolTerms.id" value="%{schoolTerms.id}"></s:hidden>
		<s:hidden name="academicYearId" value="%{schoolTerms.academicYear.id}"></s:hidden>
		<s:hidden name="tempString" />
		<h4 class="pageTitle bold">
			Update Term
		</h4>
		<span class="label label-danger"> NOTE :</span>&nbsp;
						You can update term here.
					<div class="row">
			<div class="col-md-10">
				<div class="form-group">
					<label class="control-label col-md-3">
						<span class="required"> * </span> Select Fee Setting Type :

					</label>
					<div class="col-md-9">
						<s:if test="%{classFeeCountList != null && !classFeeCountList.isEmpty()}">
							<s:select list="objectList" listKey="id" listValue="settingName" id="settingFeeTerm" name="tempId" theme="simple" headerKey="null" disabled="true"
								cssClass="required form-control input-medium" onchange="javascript:getAjaxGetFeeSettingTerms(this.value);" headerValue="-Select Fee Setting Type-" />
							<s:hidden name="tempId" value="%{schoolTerms.feeSettingId}"></s:hidden>
						</s:if>
						<s:else>
							<s:select list="objectList" listKey="id" listValue="settingName" id="settingFeeTerm" name="tempId" theme="simple" headerKey="null"
								cssClass="required form-control input-medium" onchange="javascript:getAjaxGetFeeSettingTerms(this.value);" headerValue="-Select Fee Setting Type-" />
						</s:else>
					</div>
				</div>
			</div>
		</div>
		<div class="col-md-12">
			<div class="form-group">
				<div id="allFeeSettingTerms"></div>
			</div>
		</div>
		<%-- <div class="row">
			<s:if test='%{schoolFeeSetting.settingType}'>
				<div class="form-group">
					<label class="col-md-4 control-label">
						Is this term applicable for new students :
					</label>
					<p class="form-control-static">
						<s:checkbox name="schoolTerms.applToNewStuds" />
					</p>
				</div>
			</s:if>
		</div> --%>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-3">
						<span class="required">*</span>Term Type :
					</label>
					<div class="col-md-5">
						<sj:textfield name="schoolTerms.termName" id="termName"
							cssClass="required form-control input-medium" maxlength="40" />
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-3">
					Reminder Before No. of Days :
					</label>
					<div class="col-md-5">
						<sj:textfield size="5" name="schoolTerms.noOfDays" maxlength="2"
							cssClass="form-control input-medium" />
						<%-- <span class="hintMessage">(To send reminder via SMS/E-mail
							before due date)</span>--%>
					</div>
				</div>
			</div>
		</div>

		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-3">
						<span class="required">*</span>From Date :
					</label>
					<div class="col-md-5">
						<div data-date-start-date="+0d" data-date-format="mm/dd/yyyy"
							class="input-group input-medium date date-picker">
							<input id="fromDate" name="schoolTerms.fromDate"
								value='<s:property value="schoolTerms.termFromDateStr"/>'
								onchange="javascript:verifyDate(this.value,this);" type="text"
								 class="required form-control">
							<span class="dateInput input-group-btn">
								<button type="button" class="btn default">
									<i class="fa fa-calendar"></i>
								</button> </span>
						</div>
						<span class="help-block">(MM/DD/YYYY)</span>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-3">
						<span class="required">*</span>To Date :
					</label>
					<div class="col-md-5">
						<div data-date-start-date="+0d" data-date-format="mm/dd/yyyy"
							class="input-group input-medium date date-picker">
							<input id="toDate" name="schoolTerms.toDate"
								value='<s:property value="schoolTerms.termToDateStr"/>'
								 onchange="javascript:verifyDate(this.value,this);"
								type="text" class="required form-control">
							<span class="dateInput input-group-btn">
								<button type="button" class="btn default">
									<i class="fa fa-calendar"></i>
								</button> </span>
						</div>
						<span class="help-block">(MM/DD/YYYY)</span>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-3">
						<span class="required">*</span>Due Date :
					</label>
					<div class="col-md-5">
						<div data-date-start-date="+0d" data-date-format="mm/dd/yyyy"
							class="input-group input-medium date date-picker">
							<input id="dueDate" name="schoolTerms.dueDate"
								value='<s:property value="schoolTerms.termDueDateStr"/>'
								 onchange="javascript:verifyDate(this.value,this);"
								type="text" class="required form-control">
							<span class="dateInput input-group-btn">
								<button type="button" class="btn default">
									<i class="fa fa-calendar"></i>
								</button> </span>
						</div>
						<span class="help-block">(MM/DD/YYYY)</span>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-3">
						Due Date1 :
					</label>
					<div class="col-md-5">
						<div data-date-start-date="+0d" data-date-format="mm/dd/yyyy"
							class="input-group input-medium date date-picker">
							<input id="dueDate1" name="schoolTerms.dueDate1"
								value='<s:property value="schoolTerms.termDueDate1Str"/>'
								 onchange="javascript:verifyDate(this.value,this);"
								type="text" class="form-control">
							<span class="input-group-btn">
								<button type="button" class="btn default">
									<i class="fa fa-calendar"></i>
								</button> </span>
						</div>
						<span class="help-block">(MM/DD/YYYY)</span>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-3">
						Due Date2 :
					</label>
					<div class="col-md-5">
						<div data-date-start-date="+0d" data-date-format="mm/dd/yyyy"
							class="input-group input-medium date date-picker">
							<input id="dueDate2" name="schoolTerms.dueDate2"
								value='<s:property value="schoolTerms.termDueDate2Str"/>'
								 onchange="javascript:verifyDate(this.value,this);"
								type="text" class="form-control">
							<span class="input-group-btn">
								<button type="button" class="btn default">
									<i class="fa fa-calendar"></i>
								</button> </span>
						</div>
						<span class="help-block">(MM/DD/YYYY)</span>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-9">
				<div class="form-group">
					<label class="control-label col-md-3">
						Content for Email reminder :
					</label>
					<div class="col-md-9">
						<sj:textarea rows="3" cols="30" name="schoolTerms.mailContentDesc"
							readonly="true" cssClass="form-control"
							value="This is just a friendly reminder that your children <Children Name>  <Term Name > Fee Due date is <Date>."></sj:textarea>
						<span class="hintMessage">(Do not remove <strong><></strong>type
							variables)</span>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-9">
				<div class="form-group">
					<label class="control-label col-md-3">
						Content for SMS reminder :
					</label>
					<div class="col-md-9">
						<sj:textarea rows="2" cols="30"
							name="schoolTerms.mobileContentDesc" readonly="true"
							cssClass="form-control" 
							value="Dear Parent, This is friendly reminder your chaild <Children Name> <Class Name> <Term Name> fee of Rs.<Amount>/- due on <Date>, please ignore if you already paid. <School Name>"></sj:textarea>
						<span class="hintMessage">(Do not remove <strong><></strong>type
							variables)</span>
						<div class="counter"></div>
					</div>
				</div>
			</div>
		</div>
		<div class="form-actions fluid">
			<div class="col-md-7">
				<div class="col-md-offset-4 col-md-9">
					<sj:submit targets="feeSettingsContent" value="Submit"
						indicator="indicator" cssClass="submitBt btn blue" validate="true" />
					<s:url id="doCancelSchoolTerms" action="ajaxGetFeeTypes"
						includeParams="all" escapeAmp="false" namespace="/schoolfee">
					</s:url>
					<sj:a href="%{doCancelSchoolTerms}" cssClass="btn default"
						targets="feeSettingsContent">Cancel </sj:a>
				</div>
			</div>
		</div>
	</s:form>
</s:if>
<s:else>
	<s:form action="ajaxEditSchoolTerms" id="addSchoolterms" method="post"
		cssClass="form-horizontal" theme="simple" namespace="/schoolfee">
		<s:hidden name="schoolTerms.id" value="%{schoolTerms.id}"></s:hidden>
		<s:hidden name="academicYearId" value="%{schoolTerms.academicYear.id}"></s:hidden>
		<s:hidden name="tempString" value="feeTerms" />
		<div class="form-body">
			<h4 class="pageTitle bold">
				Update Term
			</h4>
			<span class="label label-danger"> NOTE : </span>&nbsp;You can update term here.
			<div class="spaceDiv"></div>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-5">
							<span class="required"> * </span>Select Fee Setting Type :
						</label>
						<div class="col-md-6">
							<s:if test="%{classFeeCountList != null && !classFeeCountList.isEmpty()}">
								<s:select list="objectList" listKey="id" listValue="settingName" id="settingFeeTerm" name="tempId" theme="simple" headerKey="null" disabled="true"
									cssClass="required form-control input-medium" onchange="javascript:getAjaxGetFeeSettingTerms(this.value);" headerValue="-Select Fee Setting Type-">
								</s:select>
								<s:hidden name="tempId" value="%{schoolTerms.feeSettingId}"></s:hidden>
							</s:if>
							<s:else>
								<s:select list="objectList" listKey="id" listValue="settingName" id="settingFeeTerm" name="tempId" theme="simple" headerKey="null" 
									cssClass="required form-control input-medium" onchange="javascript:getAjaxGetFeeSettingTerms(this.value);" headerValue="-Select Fee Setting Type-">
								</s:select>
							</s:else>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-12">
				<div class="form-group">
					<div id="allFeeSettingTerms"></div>
				</div>
			</div>
			<%-- <s:if test="%{schoolFeeSetting.settingType}">
				<div class="form-group">
					<label class="col-md-4 control-label">
						Is this term applicable for new students dfdffd:
					</label>
					<p class="form-control-static">
						<s:checkbox name="schoolTerms.applToNewStuds" />
					</p>
				</div>
			</s:if> --%>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4">
							<span class="required">*</span>Term Type :
						</label>
						<div class="col-md-5">
							<sj:textfield name="schoolTerms.termName" id="termName"
								cssClass="required form-control input-medium" maxlength="40" />
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4">
						Reminder Before No. of Days :
						</label>
						 <div class="col-md-5">
							<sj:textfield name="schoolTerms.noOfDays" id="subNum"
								maxlength="2" size="5"
								cssClass="numeric form-control input-medium" />
							<span class="help-block">(Reminder for SMS/E-mail before due date)</span>
							
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4">
							<span class="required">*</span>From Date :
						</label>
						<div class="col-md-5">
							<div data-date-start-date="+0d" data-date-format="mm/dd/yyyy"
								class="input-group input-medium date date-picker">
								<input id="fromDate" name="schoolTerms.fromDate" 
									value='<s:property value="schoolTerms.termFromDateStr"/>'
									onchange="javascript:verifyDate(this.value,this);" type="text"
									class="required form-control">
								<span class="dateInput input-group-btn">
									<button type="button" class="btn default">
										<i class="fa fa-calendar"></i>
									</button> </span>
							</div>
							<span class="help-block">(MM/DD/YYYY)</span>
							<!--<sj:datepicker id="fromDate" name="schoolTerms.fromDate" readonly="true"  onchange="javascript:verifyDate(this.value,this);"
						label="From Date" required="true" cssStyle="width:141px"
						changeMonth="true" changeYear="true" cssClass="required form-control input-medium" />
							-->
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4">
							<span class="required">*</span>To Date :
						</label>
						<div class="col-md-5">
							<div data-date-start-date="+0d" data-date-format="mm/dd/yyyy"
								class="input-group input-medium date date-picker">
								<input id="toDate" name="schoolTerms.toDate"
									value='<s:property value="schoolTerms.termToDateStr"/>'
									 onchange="javascript:verifyDate(this.value,this);"
									type="text" class="required form-control">
								<span class="dateInput input-group-btn">
									<button type="button" class="btn default">
										<i class="fa fa-calendar"></i>
									</button> </span>
							</div>
							<span class="help-block">(MM/DD/YYYY)</span>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4">
							<span class="required">*</span>Due Date :
						</label>
						<div class="col-md-5">
							<div data-date-start-date="+0d" data-date-format="mm/dd/yyyy"
								class="input-group input-medium date date-picker">
								<input id="dueDate" name="schoolTerms.dueDate" 
									value='<s:property value="schoolTerms.termDueDateStr"/>'
									 onchange="javascript:verifyDate(this.value,this);"
									type="text" class="required form-control input-medium" />
								<span class="dateInput input-group-btn">
									<button type="button" class="btn default">
										<i class="fa fa-calendar"></i>
									</button> </span>
							</div>
							<span class="help-block">(MM/DD/YYYY)</span>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4">
							Due Date1 :
						</label>
						<div class="col-md-5">
							<div data-date-start-date="+0d" data-date-format="mm/dd/yyyy"
								class="input-group input-medium date date-picker">
								<input id="dueDate1" name="schoolTerms.dueDate1"
									value='<s:property value="schoolTerms.termDueDate1Str"/>'
									 onchange="javascript:verifyDate(this.value,this);"
									type="text" class="form-control">
								<span class="input-group-btn">
									<button type="button" class="btn default">
										<i class="fa fa-calendar"></i>
									</button> </span>
							</div>
							<span class="help-block">(MM/DD/YYYY)</span>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4">
							Due Date2 :
						</label>
						<div class="col-md-5">
							<div data-date-start-date="+0d" data-date-format="mm/dd/yyyy"
								class="input-group input-medium date date-picker">
								<input id="dueDate2" name="schoolTerms.dueDate2"
									value='<s:property value="schoolTerms.termDueDate2Str"/>'
									 onchange="javascript:verifyDate(this.value,this);"
									type="text" class="form-control">
								<span class="input-group-btn">
									<button type="button" class="btn default">
										<i class="fa fa-calendar"></i>
									</button> </span>
							</div>
							<span class="help-block">(MM/DD/YYYY)</span>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4">
							Content for Email reminder :
						</label>
						<div class="col-md-6">
							<sj:textarea rows="3" cols="30"
								name="schoolTerms.mailContentDesc" readonly="true"
								label="Content for Email reminder" cssClass="form-control"
								value="This is just a friendly reminder that your children <Children Name>  <Term Name > Fee Due date is <Date>."></sj:textarea>
							<span class="hintMessage">(Do not remove <strong><></strong>type
								variables)</span>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4">
							Content for SMS reminder :
						</label>
						<div class="col-md-6">
							<sj:textarea rows="2" cols="30"
								name="schoolTerms.mobileContentDesc" readonly="true"
								cssClass="form-control"
								value="Dear Parent, This is friendly reminder your chaild <Children Name> <Class Name> <Term Name> fee of Rs.<Amount>/- due on <Date>, please ignore if you already paid. <School Name>"></sj:textarea>
							<span class="hintMessage">(Do not remove <strong><></strong>type
								variables)</span>
							<div class="counter"></div>
						</div>
					</div>
				</div>
			</div>
			<div class="form-actions fluid">
				<div class="col-md-7">
					<div class="col-md-offset-4 col-md-9">
						<sj:submit targets="feeSettingsContent" value="Submit"
							cssClass="submitBt btn blue" validate="true" />
						<s:url id="doCancelSchoolTerms"
							action="ajaxViewSelectedFeeSettings" includeParams="all"
							escapeAmp="false"></s:url>
						<sj:a href="%{doCancelSchoolTerms}" cssClass="btn default"
							targets="feeSettingsContent">Cancel</sj:a>
					</div>
				</div>
			</div>
		</div>
	</s:form>
</s:else>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/ems.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	$("input:checkbox, input:radio:not('.toggle')").uniform();
	$('.numeric').numeric();
	var settingFeeTermId = $('#settingFeeTerm').val();
	if (isNonEmpty(settingFeeTermId)) {
		getAjaxGetFeeSettingTerms(settingFeeTermId);
	}
	var terms='<s:property value="tempString"/>';
	if ( terms == 'admissionTerms') {
		 FormComponents.init();
	}
	else{
		var startDate = $('span#startDateSpan').attr("class");
		var endDate = $('span#endDateSpan').attr("class");
		dateRestrictionWithinAcademicYear(startDate,endDate);
        FormComponents.init();
	}
	/* var startDate = $('span#startDateSpan').attr("class");
	var endDate = $('span#endDateSpan').attr("class");
	var toDayDate = $('span#toDateSpan').attr("class");
	var admissAcadYear = $('span#admissionAcademicyear').attr("class");
	var usrAcademicYear = $('span#usrAcadmicId').attr('class');
	if (isNonEmpty(startDate) && isNonEmpty(endDate) && isNonEmpty(toDayDate)) {
		if (isNonEmpty(admissAcadYear) && isNonEmpty(usrAcademicYear)) {
			if (admissAcadYear == usrAcademicYear) {
				assignDatesRange(toDayDate, startDate, endDate);
			} else {
				$('#fromDate').datepicker("option", 'minDate', startDate);
				$('#toDate').datepicker("option", 'minDate', startDate);
				$('#dueDate').datepicker("option", 'minDate', startDate);
				$('#dueDate1').datepicker("option", 'minDate', startDate);
				$('#dueDate2').datepicker("option", 'minDate', startDate);

			}
		} else {
			assignDatesRange(toDayDate, startDate, endDate);
		}
	} */
});
function verifyDate(date) {
	var startDate = $("#fromDate").val();
	var endDate = $("#toDate").val();
	var dueDate = $("#dueDate").val();
	var dueDate1 = $("#dueDate1").val();
	var dueDate2 = $("#dueDate2").val();
	if (startDate == "") {
		alert("Please select From Date first.");
		$('input#toDate').val('');
		$('input#dueDate').val('');
		$('input#dueDate1').val('');
		$('input#dueDate2').val('');
		return false;
	}
	if (isNonEmpty(startDate) && isNonEmpty(endDate)) {
		if (Date.parse(endDate) < Date.parse(startDate)) {
			alert("To Date should be greater than or equal to From Date.");
			$("#toDate").val('');
			return false;
		}
	}
	/* $('div.termDates') .each( function() {
						var termStartDateSpan = $(this).children(
								$('span#termStartDateSpan')).attr("class");
						var termEndDateSpan = $(this).children(
								$('span#termStartDateSpan')).next(
								$('span#termEndDateSpan')).attr("class");
						//alert("termStartDateSpan="+termStartDateSpan+"&termEndDateSpan="+termEndDateSpan);
						var termStartDate = new Date(termStartDateSpan);
						var termEndDate = new Date(termEndDateSpan);
						var selectedStartDate = new Date(startDate);
						var selectedEndDate = new Date(endDate);
						var selectedDueDate = new Date(dueDate);
						var selectedDueDate1 = new Date(dueDate1);
						var selectedDueDate2 = new Date(dueDate2);
						//alert("termStartDate="+termStartDate+"&termEndDate="+termEndDate);
						if (selectedStartDate < termEndDate
								&& selectedEndDate >= termStartDate) {
							alert("From "
									+ termStartDateSpan
									+ " To "
									+ termEndDateSpan
									+ " This dates already terms created .Please select the different dates.");
							$('input#toDate').val('');
							return false;
						}
						if (selectedStartDate >= termStartDate
								&& selectedStartDate <= termEndDate) {
							alert("From "
									+ termStartDateSpan
									+ " To "
									+ termEndDateSpan
									+ " This dates already terms created .Please select the different dates.");
							$('input#fromDate').val('');
							return false;
						} else if ((selectedEndDate >= termStartDate && selectedEndDate <= termEndDate)) {
							alert("From "
									+ termStartDateSpan
									+ " To "
									+ termEndDateSpan
									+ " This dates already terms created .Please select the different dates.");
							$('input#toDate').val('');
							return false;
						} else if (selectedStartDate > selectedDueDate) {
							alert("Due Date should be greater than or equal to From Date.");
							$('input#dueDate').val('');
							return false;
						} else if (selectedDueDate >= selectedDueDate1) {
							alert("Due Date1 should be greater than to Due Date.");
							$('input#dueDate1').val('');
							return false;
						} else if (selectedDueDate1 >= selectedDueDate2) {
							alert("Due Date2 should be greater than to Due Date1.");
							$('input#dueDate2').val('');
							return false;
						} else {
							return true;
						}
					}); */
}
/* function assignDatesRange(toDayDate, startDate, endDate) {
	if ((Date.parse(toDayDate) >= Date.parse(startDate) && Date
			.parse(toDayDate) <= Date.parse(endDate))
			|| (Date.parse(toDayDate) < Date.parse(startDate))) {
		$('#fromDate').datepicker("option", 'minDate', startDate);
		$('#fromDate').datepicker("option", 'maxDate', endDate);
		$('#toDate').datepicker("option", 'minDate', startDate);
		$('#toDate').datepicker("option", 'maxDate', endDate);
		$('#dueDate').datepicker("option", 'minDate', startDate);
		$('#dueDate').datepicker("option", 'maxDate', endDate);
		$('#dueDate1').datepicker("option", 'minDate', startDate);
		$('#dueDate1').datepicker("option", 'maxDate', endDate);
		$('#dueDate2').datepicker("option", 'minDate', startDate);
		$('#dueDate2').datepicker("option", 'maxDate', endDate);
	} else if (Date.parse(toDayDate) > Date.parse(endDate)) {
		$(".date_picker").datepicker('disable');
	}
} */

function getAjaxGetFeeSettingTerms(settingFeeTermId) {
	var tempId = null;
	var tempString = "feeTerms";
	if (isNonEmpty(settingFeeTermId)) {
		var pars = "tempId=" + settingFeeTermId + "&tempString=" + tempString;
		$("#allFeeSettingTerms")
				.html(
						'<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		var url = jQuery.url
				.getChatURL("/schoolfee/ajaxSchoolFeeSettingCreateTerms.do");
		$.ajax( {
			url : url,
			cache : false,
			data : pars,
			success : function(html) {
				$("#allFeeSettingTerms").html(html);
			}
		});
	}
}
</script>
