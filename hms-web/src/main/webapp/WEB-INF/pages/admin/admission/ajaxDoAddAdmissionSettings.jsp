<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/subscription/textcounter.js">
</script>
<div role="progressbar" class="progress progress-striped" id="bar">
	<div class="progress-bar progress-bar-success" style="width: 20%;">
	</div>
</div>
<div>
	<s:if test='%{leaveTypes !=null && !leaveTypes.isEmpty()}'>
		<s:form id="addAdmissionSettings" action="ajaxAddAdmissionSettings"
			method="post" theme="simple" cssClass="form-horizontal"
			namespace="/admin" enctype="multipart/form-data">
			<h4 class="pageTitle bold">
				<s:if test="%{admissionSettings != null}">
					<span class="admissionOpenStatus"
						id="<s:property value='admissionSettings.status'/>"></span>
					<span class="testConductedStatus"
						id="<s:property value='admissionSettings.testConducted'/>"></span>
						Update admission settings
						<s:hidden name="admissionSettings.id"
						value="%{admissionSettings.id}"></s:hidden>
				</s:if>
				<s:else>
						Create admission settings
						<span class='admissionOpenStatus' id='false'></span>
					<span class="testConductedStatus" id="false"></span>
					<s:hidden name="admissionSettings.id" value="0"></s:hidden>
				</s:else>
			</h4>
			<p>
				<span class="label label-danger"> NOTE : </span>&nbsp;Fill up the
				below form with accurate information. This process will open the
				admission from selected academic year.
			</p>
			<div class="form-group">
				<label class="control-label col-md-3">
					<span class="required">*</span>Select Academic Year :
				</label>
				<div class="col-md-3">
					<s:if
						test="%{admissionSettings != null && admissionSettings != empty}">
						<s:select id="academicYear" list="leaveTypes" 
							onchange="loadYearRangeValue();" cssClass="form-control required"
							listKey="key" listValue="value" headerKey=""
							name="admissionSettings.academicYear.academicYear" />
					</s:if>
					<s:else>
						<s:select id="academicYear" list="leaveTypes" 
							onchange="loadYearRangeValue();" cssClass="form-control required"
							listKey="key" listValue="value" headerKey=""
							headerValue="- Select -"
							name="admissionSettings.academicYear.academicYear" />
					</s:else>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-md-3">
					Applications Open :
				</label>
				<div class="col-md-3">
					<input type="checkbox" id="entrTest" 
						name="admissionSettings.status">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-md-3">
					<span class="required">*</span>Start Date :
				</label>
				<div class="col-md-3">
					<div data-date-start-date="+0d" data-date-format="mm/dd/yyyy"
						class="input-group date date-picker">
						<input type="text" id="startDate" readonly=""
							class="form-control required" onchange="displayDate();"
							name="admissionSettings.startDate"
							value='<s:property value="admissionSettings.applicationStartDateStr"/>'>
						<span class="dateInput input-group-btn">
							<button type="button" class="btn default">
								<i class="fa fa-calendar"></i>
							</button> </span>
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-md-3">
					<span class="required">*</span>End Date :
				</label>
				<div class="col-md-3">
					<div data-date-start-date="+0d" data-date-format="mm/dd/yyyy"
						class="input-group date date-picker fdate">
						<input type="text" id="endDate" readonly=""
							class="form-control required" onchange="displayDate();"
							name="admissionSettings.endDate"
							value='<s:property value="admissionSettings.applicationClosedDateStr"/>'>
						<span class="dateInput input-group-btn">
							<button type="button" class="btn default">
								<i class="fa fa-calendar"></i>
							</button> </span>
					</div>
				</div>
			</div>
			<div class="form-group ">
				<label class="control-label col-md-3">
					<span class="required">*</span>Admissions End Date :
				</label>
				<div class="col-md-3">
					<div data-date-start-date="+0d" data-date-format="mm/dd/yyyy"
						class="input-group date date-picker fdate">
						<input type="text" id="admissEndDate" readonly=""
							class="form-control required"
							onchange="verifyDate();displayExamDate();"
							name="admissionSettings.admissionEndDate"
							value='<s:property value="admissionSettings.admissionsEndDateStr"/>'>
						<span class="dateInput input-group-btn">
							<button type="button" class="btn default">
								<i class="fa fa-calendar"></i>
							</button> </span>
					</div>
				</div>
			</div>
			<div id="studeadmissions">
				<div class="form-group">
					<label class="control-label col-md-3">
						<span class="required"></span>Manage Student Admissions By Fee :
					</label>
					<div class="col-md-3">
						<s:if
							test="%{(admissionSettings.academicYear.manageStudentsAdmissionsByFee == null) || (admissionSettings.academicYear.manageStudentsAdmissionsByFee =='')}">
							<s:radio list="#{'Y':'Yes','N':'No'}"
								name="admissionSettings.academicYear.manageStudentsAdmissionsByFee" checked="checked"
								id="studeadmissions"/>
						</s:if>
						<s:else>
							<s:radio list="#{'Y':'Yes','N':'No'}"
								name="admissionSettings.academicYear.manageStudentsAdmissionsByFee" 
								id="studeadmissions" />
						</s:else>
					</div>
				</div>
			</div>
			<div class="form-group ">
				<label class="control-label col-md-3">
					Is entrance Test Applicable :
				</label>
				<div class="col-md-3">
					<input type="checkbox" id="testConduct" onclick="changeQualification();"
						name="admissionSettings.testConducted">
				</div>
			</div>
			<div style="display: none;" id="entranceDate">
				<div class="form-group ">
					<label class="control-label col-md-3">
						<span class="required">*</span>Entrance Exam Date :
					</label>
					<div class="col-md-3">
						<div data-date-start-date="+0d" data-date-format="mm/dd/yyyy"
							class="input-group  date date-picker fdate" id="Edate">
							<input type="text" id="entrExamDate" readonly=""
								class="form-control required"
								onchange="verifyDate();entranceExamDate();"
								name="admissionSettings.entranceDate"
								value='<s:property value="admissionSettings.entranceDateStr"/>'>
							<span class="dateInput input-group-btn">
								<button type="button" class="btn default">
									<i class="fa fa-calendar"></i>
								</button> </span>
						</div>
					</div>
				</div>
				<div class="form-group ">
					<label class="control-label col-md-3">
						<span class="required">*</span>Entrance Exam Max Marks :
					</label>
					<div class="col-md-3">
						<sj:textfield name="admissionSettings.entranceExamTotalMarks"
							id="entranceExamMaxMarks" labelposition="top"
							cssClass="numeric form-control" maxlength="5"
							onblur="examTotalMarksValidate(this);"></sj:textfield>
					</div>
				</div>
				<div class="form-group ">
					<label class="control-label col-md-3">
						<span class="required">*</span>Entrance Exam Pass Marks :
					</label>
					<div class="col-md-3">
						<sj:textfield name="admissionSettings.entranceExamPassMarks"
							id="entreExamPassMarks" labelposition="top"
							cssClass="numeric form-control" maxlength="5"
							onblur="examTotalMarksValidate(this);"></sj:textfield>
					</div>
				</div>
			</div>
			<div class="form-group ">
				<label class="control-label col-md-3">
					Application Fee :
				</label>
				<div class="col-md-3">
					<s:textfield id="applicationFee" cssClass="form-control"
						name="admissionSettings.applicationFee" maxlength="6"
						onkeypress="return onlyNumbers(event);" />
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-md-3">
					Registration Fee :
				</label>
				<div class="col-md-3">
					<s:textfield id="registrationFee" cssClass="form-control"
						name="admissionSettings.registrationFee" maxlength="6" requiredPosition="required"
						onkeypress="return onlyNumbers(event);" />
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-md-3">
					Prospective Fee :
				</label>
				<div class="col-md-3">
					<s:textfield id="prospectiveFee" cssClass="form-control"
						name="admissionSettings.prospectiveFee" maxlength="6"
						onkeypress="return onlyNumbers(event);" />
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-md-3">
					Admission Content :
				</label>
				<div class="col-md-6">
					<s:textarea id="admissionContent"
						cssClass="word_count form-control"
						name="admissionSettings.admissionContent" rows="5" cols="3"
						maxCharsData="1024" />
					<span class="counter"></span>
				</div>
			</div>
			
			
			<div class="form-group">
				<label class="control-label col-md-3">
					Upload Admission Form Template Word Doc :
				</label>
				<div class="col-md-6">
					<s:file name="upload" id="photoURL1" cssClass="fileName"></s:file>
					<!-- <input type='file' name="uploadList[1]"
							class="btn default fileNames " /> -->
				</div>
			</div>
			
			<s:if test='%{admissionSettings.admissionFormTemplatepath != null}'>
			
			<table
			class="table table-striped table-bordered table-hover table-full-width"
			id="sample_2">
			<thead>
				<tr>
					<th>Name</th>
				</tr>
			</thead>
			<tbody>
					<tr>
						<td>
							<a rel="nofollow"  target="_blank;" 
								href='${pageContext.request.contextPath}<s:property value="admissionSettings.admissionFormTemplatepath" />/<s:property value="admissionSettings.admissionFormTemplateFileName" />'>
								<s:property value="admissionSettings.admissionFormTemplateFileName" /> 
							</a>
						</td>
						
					</tr>
			</tbody>
		</table>
		</s:if>
				
			<div class="form-group">
				<label class="control-label col-md-3">
					Upload Admission Form Empty Template pdf :
				</label>
				<div class="col-md-6">
					<%-- <s:file name="upload" id="photoURL1" cssClass="fileName"></s:file> --%>
					<input type='file' name="uploadList[1]"
							class="btn default fileNames " />
				</div>
			</div>
			<s:if test='%{admissionSettings.admissionFormEmptyTemplatepath != null}'>
			
			<table
			class="table table-striped table-bordered table-hover table-full-width"
			id="sample_2">
			<thead>
				<tr>
					<th>Name</th>
				</tr>
			</thead>
			<tbody>
								
					<tr>
						<td>
						<a rel="nofollow"
							href='${pageContext.request.contextPath}<s:property value="admissionSettings.admissionFormEmptyTemplatepath" />/<s:property value="admissionSettings.admissionFormEmptyTemplateFileName" />'>
							<s:property value="admissionSettings.admissionFormEmptyTemplateFileName" /> 
						</a>
						
						</td>
					</tr>
			</tbody>
		</table>
		</s:if>
			
			<div class="form-actions fluid">
				<div class="col-md-offset-3 col-md-9">
					<sj:submit targets="admissionSettingsDiv" value="Next" onBeforeTopics="loadAdmissionSettings"
						cssClass="submitBt btn blue admissionsClass" formIds="addAdmissionSettings"
						indicator="indicator" validate="true" />
					<s:url id="onlineApplication" action="ajaxAdmissionSettingsHome"
						namespace="/admin">
					</s:url>
					<sj:a href="%{onlineApplication}" targets="mainContentDiv"
						cssClass="btn default">
					Cancel</sj:a>
				</div>
			</div>
		</s:form>
	</s:if>
	<s:else>
		<div class="alert alert-info">
			You have already added admission settings for current academic year
			and future academic year.
		</div>
	</s:else>
</div>
<script type="text/javascript">
$(document).ready(function() {
	$.destroyTopic('loadAdmissionSettings');
	$.subscribe('loadAdmissionSettings', function(event, data) {
		//$(".admissionsClass").val("Next").attr('disabled', 'disabled');
	});
FormComponents.init();
changePageTitle('Add Admission Settings');
 $("input:checkbox, input:radio:not('.toggle')").uniform();
	$('.numeric').numeric();
	$('.alphabet').alpha();
	var admissionStatus = $('span.admissionOpenStatus').attr('id');
	if(isNonEmpty(admissionStatus)){
		if(admissionStatus == 'true'){
			$('#entrTest').attr('checked','checked');
			$('#entrTest').parent('span').addClass('checked');
			$('#entrTest').val('true');
		}else{
			$('#entrTest').removeAttr('checked');
			$('#entrTest').val('false');
		}	
	}
	
	$("div input#entrTest").click(function() {
			if($(this).is(':checked')){
				$(this).val('true');
			}else{
				$(this).val('false');
			}				
		});
	
	/*
	//$('input[value='+value+']').click(); or
$('input[value='+value+']').attr("checked",true);
$('input[value='+value+']').parent('span').addClass('checked');
	if (isNonEmpty(transportMode) && 'T' == transportMode) {
		$('#inputboxhideText').show();
	} else {
		$('#inputboxhideText').hide();
	}
	$('input[name="tempString"]').val(transportMode)
}*/
	var testConductedStatus = $('span.testConductedStatus').attr('id');
	if(isNonEmpty(testConductedStatus)){
		if(testConductedStatus == 'true'){
			$('#testConduct').attr('checked','checked');
			$('#testConduct').parent('span').addClass('checked');
			$('#testConduct').val('true');
			
		}else{
			$('#testConduct').removeAttr('checked');
			$('#testConduct').val('false');
		}	
		changeQualification();
	}
	
	$("div input#testConduct").click(function() {
			if($(this).is(':checked')){
				$(this).val('true');
			}else{
				$(this).val('false');
			}				
		});
	
	});

function displayDate(){
	var startDate = $('#startDate').val();
	var endDate = $('#endDate').val();
	if (isNonEmpty(startDate) && isNonEmpty(endDate)) {
		startDate = Date.parse(startDate);
		endDate = Date.parse(endDate);
		if (endDate <= startDate) {
			alert("Your end date should be more than your start date.");
			$('#endDate').val("");
		} else {
			$('#admissEndDate').val("");
			return true;
		}
	}
}

function entranceExamDate(){ 
	var examDate = $('#entrExamDate').val();
	var endDate = $('#endDate').val();
	var admissionEndDate = $('#admissEndDate').val();
	if (isNonEmpty(examDate) && isNonEmpty(admissionEndDate) && isNonEmpty(endDate)) {
		examDate = Date.parse(examDate);
		endDate = Date.parse(endDate);
		admissionEndDate = Date.parse(admissionEndDate);
		if (examDate <= endDate) {
			alert("Your entrance Exam date should be more than your end date.");
			$('#entrExamDate').val("");
		} else if (examDate < admissionEndDate) {
			return true;
		} else if (admissionEndDate <= examDate){
		alert("Your entrance Exam date should be less than your admission end date.");
		$('#entrExamDate').val("");
			return false;
		}
	}
	else{
	return false;
	}
}
function displayExamDate(){ 
	var examDate = $('#entrExamDate').val();
	var admissionEndDate = $('#admissEndDate').val();
	var endDate = $('#endDate').val();
	if (endDate == '') {
		alert("please select endDate.");
		$('#admissEndDate').val("");
		$('#entrExamDate').val("");
	}
	if (isNonEmpty(admissionEndDate) && isNonEmpty(endDate)) {
		examDate = Date.parse(examDate);
		admissionEndDate = Date.parse(admissionEndDate);
		endDate = Date.parse(endDate);
		if (admissionEndDate <=endDate) {
			alert("Your admission end date should be more than your end date.");
			$('#admissEndDate').val("");
			$('#entrExamDate').val("");
		} else {
			$('#entrExamDate').val("");
			return true;
		}
	} else if (examDate == "" && isNonEmpty(admissionEndDate) && isNonEmpty(endDate)) {
		if (admissionEndDate < endDate) {
			alert("Your endDate should be equal or more than your admission end date.");
			$('#admissEndDate').val("");
		}
	} else {
		$('#entrExamDate').val("");
		return true;
	}
}

function examTotalMarksValidate(evt){
	var entranceMaxMarks = $('#entranceExamMaxMarks').val();
		var passMarks = $('#entreExamPassMarks').val();
			if (isNonEmpty(entranceMaxMarks) && isNonEmpty(passMarks)) {
				entranceMaxMarks = Number(entranceMaxMarks);
				passMarks = Number(passMarks);
				if(entranceMaxMarks > 0 && passMarks > 0){
					if (passMarks <= entranceMaxMarks) {
						return true;
					} else {
						$(evt).val("");
							alert("Entrance exam pass marks should be less than entrance exam max marks.");
							return false;
					}
				}else{
					alert("Please enter the marks greater than the zero.");
					$('#entranceExamMaxMarks').val("");
					$('#entreExamPassMarks').val("");
					return false;
				}
			}
		
}
$('input#entrExamDate').change(function(){
    if($(this).val() != ''){
        $(this).parent('div#Edate').find('label.error').remove();
}
});
$('#entreExamPassMarks').focusout(function(){
    if($(this).val() != ''){
        $(this).next('label.error').remove();
}
});
$('#entranceExamMaxMarks').focusout(function(){
    if($(this).val() != ''){
        $(this).next('label.error').remove();
}
});
function changeQualification() {
	if ($('#testConduct').attr('checked')) {
		$('#entrExamDate').addClass('required');
		$('#entreExamPassMarks').addClass('required');
		$('#entranceExamMaxMarks').addClass('required');
		$('#entranceDate').show();
	} else {
		$('#entrExamDate').val('');
		$('#entreExamPassMarks').val('');
		$('#entranceExamMaxMarks').val('');
		$('#entranceDate').val('');
		$('#entrExamDate').removeClass('required');
		$('#entreExamPassMarks').removeClass('required');
		$('#entranceExamMaxMarks').removeClass('required');
		$('#entranceDate').hide();
	}
}
function loadYearRangeValue() {
	var academicYear = $('#academicYear option:selected').text();
	var totalYear = '';
	var toDayDate = new Date();
	var year = toDayDate.getFullYear();
	if (isNonEmpty(academicYear)) {
		if (academicYear != '- Select-') {
			$('#startDate').val('');
			$('#endDate').val('');
			$('#admissEndDate').val('');
			$('#entrExamDate').val('');
			var academicIds = academicYear.split("-");
			if (year == academicIds[0]) {
				totalYear = academicIds[0] + ':' + (Number(academicIds[0]) + 1);
			} else {
				totalYear = (Number(academicIds[0]) - 1) + ':'
						+ (Number(academicIds[0]) + 1);
			}
			$('#startDate').datepicker("option", 'yearRange', totalYear);
			$('#endDate').datepicker("option", 'yearRange', totalYear);
			$('#admissEndDate').datepicker("option", 'yearRange', totalYear);
			$('#entrExamDate').datepicker("option", 'yearRange', totalYear);
		}
	}
}
function verifyDate(date0, date1) {
	var startDate = $('#startDate').val();
	var admissEndDate = $('#admissEndDate').val();
	if (isNonEmpty(admissEndDate) && isNonEmpty(startDate)) {
		startDate = new Date(startDate);
		admissEndDate = new Date(admissEndDate);
		if (admissEndDate <= startDate) {
			alert("Admissions End date should be after Application End date.");
			$('#admissEndDate').val('');
		}
		var totalDays = Number((admissEndDate - startDate)
				/ (1000 * 60 * 60 * 24) + 1);
		if (totalDays >= 365) {
			alert("School working days should be less than 365 days");
			$('#admissEndDate').val('');
		}
		admissEndDate = new Date(admissEndDate);
		admissEndDate.setDate(admissEndDate.getDate() + 1);
		$('#nextYrDate').datepicker("option", 'minDate', admissEndDate);
	}
}
</script>
