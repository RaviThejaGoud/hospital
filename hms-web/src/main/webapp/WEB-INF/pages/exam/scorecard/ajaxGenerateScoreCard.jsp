<%@ include file="/common/taglibs.jsp"%>
<jsp:include page="/common/messages.jsp" />
	<s:if test="%{(objectList != null && !objectList.isEmpty())}">
		<div class="form-horizontal">
			<div class="form-group">
				<label class="control-label col-md-2">
					Generate by :
				</label>
				<div class="col-md-8">
					<div class="radio-list">
						<label class="radio-inline">
							<input type="radio" name="SelectType" value="Select Class"
								onclick="manageScoreCardGeneration(this.value);"
								checked="checked" />
							Class & Section &nbsp;
						</label>
						<label class="radio-inline">
							<input type="radio" name="SelectType" value="Search Student"
								onclick="manageScoreCardGeneration(this.value);" />
							Student Name &nbsp;
						</label>
						<label class="radio-inline">
							<input type="radio" name="SelectType"
								value="Search Admission Number"
								onclick="manageScoreCardGeneration(this.value);" />
							Admission Number
						</label>
					</div>
				</div>
			</div>
			<div id="displayScoreCardInfo" style="display: none;">
				<div class="alert alert-info">
					You will get score cards as PDF files in few min's.
				</div>
			</div>
			<div class="searchDiv" id="selectserchClass">
				<s:form id="studentGenerateTcInfoForm" method="post" theme="simple"
					action="ajaxGenerateScoreCard" cssClass="form-horizontal"
					onsubmit="javascript:return validateScoreCardGenerationByClassForm();"
					namespace="/exam">
					<s:hidden name="anyId" id="selectedGeneratedFile" value="G"></s:hidden>
					<s:hidden name="classId" id="selectedClassId"></s:hidden>
					<s:hidden name="examType" id="examType"></s:hidden>
					<s:hidden name="selectedId" id="selectedAdmissionNumbers"></s:hidden>
					<div class="panel-body col-md-12">
						<div class="col-md-1">
							<span class="label label-danger"> NOTE : </span>
						</div>
						<div class="col-md-10">
							<ul>
								<li>
									Before generating scorecard please upload template for that class.
								</li>
								<li>
									After uploading template if you have changed any examtype,
									subject names and subtype names details in system, system cannot
									generate scorecard.
								</li>
								<li>
									Every time you need to regenerate the scorecards if you updated
									any of the students marks.
								</li>
								<li>
									Select exam type,class then click on 'Generate Scorecard' button to generate the scorecard.
								</li>
								<li>
									<b class="text-danger">For first time the designs will be done free of cost, next time onward it will be chargeable.</b>
								</li>
							</ul>
						</div>
					</div>
					<s:hidden name="anyTitle" id="classSecParam"></s:hidden>
					<div class="form-group">
						<label class="col-md-2 control-label">
							<span class="required">*</span>Select Exam Type :
						</label>
						<div class="col-md-4">
							<div class="checkbox-list">
								<s:select name="tempId1" list="objectList"
									listKey="id" listValue="examType" id="examTypeId"
									headerKey="" headerValue="- Select -"
									cssClass="form-control input-medium required"
									onchange="javascript:getStudentDetails(this.value);">
								</s:select>
							</div>
						</div>
					</div>
					<div id="classResultsDiv"  class="col-md-5">
						<%-- <jsp:include
							page="/WEB-INF/pages/admin/common/ajaxStudyClassListForScoreCard.jsp" /> --%>
					</div>
					  	<div id="errorMsg"  style="display: none;"><div class="alert alert-error col-md-5">Before generating scorecard please upload template for that class.</div></div>
					<div id="templateReportCard" style="display: none;"  class="col-md-2">
						<s:submit value="Generate Scorecard" id="generateCC"
							cssClass="submitBt btn btn-sm blue generateScoreCCReport"
							cssStyle="float:none;" />
					</div>
					<div id="divCCReportCard" style="display: none;"  class="col-md-5">
					<div  class="col-md-6">
						<s:submit value="Generate PDF Report" id="downloadCC"
							cssClass="submitBt btn btn-sm  purple generateScoreCCReport"
							cssStyle="float:none;" />
							</div>
						<div id="studentCCReport" class="col-md-4">
							<a id="downloadStudentCC" class="submitBt btn btn-sm  purple"
								style="float: none;">Search Students</a>
						</div>
					</div>
					<div class="spaceDiv">&nbsp;</div>
					 <div class="form-body">
						<div id="isCCReportExists"></div>
						<%-- <div class="form-actions fluid">
							<div class="col-md-offset-2 col-md-1">
								<s:submit value="Generate Scorecard" id="generateCC"
									cssClass="submitBt btn blue generateScoreCCReport"
									cssStyle="float:none;" />
							</div>

						</div> --%>
					</div> 
				</s:form>
			</div>
			<div class="searchDiv" id="searchStudent" style="display: none;">
				<s:form id="searchStudentByName"
					action="ajaxSearchStudentForScoreCard" theme="simple"
					cssClass="form-horizontal" namespace="/exam">
					<s:hidden name="classAndSection" value="%{title}"></s:hidden>
					<div class="panel-body col-md-12">
						<div class="col-md-1">
							<span class="label label-danger"> NOTE : </span>
						</div>
						<div class="col-md-10">
							<ul>
								<li>
									Before downloading individual scorecard you must generate the scorecard for that student class.Otherwise you cannot see the 'Download Scorecard' button for that student
								</li>
							</ul>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-2">
							Search Student :
						</label>
						<div class="col-md-4">
							<div class="input-group">
								<sj:textfield name="anyTitle" id="studName"
									placeholder="Student First or Last Name"
									cssClass="form-control required defaultValue"></sj:textfield>
								<span class="input-group-btn"> <sj:submit
										targets="searchStudentsList" value="Find Student"
										cssClass="btn blue"
										onBeforeTopics="validateStudentNameSearchForm"
										formIds="searchStudentByName" resetForm="true" /> </span>
							</div>
							<span class="hintMessage">(Key at least 3 chars and hit
								'Find Student' to get closer match.)</span>
						</div>
					</div>
				</s:form>
			</div>
			<div class="searchDiv" id="searchAdmissionNumber"
				style="display: none;">
				<s:form id="searchStudentByAdmissionNumber"
					action="ajaxSearchStudentForScoreCard" theme="simple"
					cssClass="form-horizontal" namespace="/exam">
					<s:hidden name="classAndSection" value="%{title}"></s:hidden>
					<div class="panel-body col-md-12">
						<div class="col-md-1">
							<span class="label label-danger"> NOTE : </span>
						</div>
						<div class="col-md-10">
							<ul>
								<li>
									Before downloading individual scorecard you must generate the scorecard for that student class.Otherwise you cannot see the 'Download Scorecard' button for that student
								</li>
							</ul>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-2">
							Search Admission :
						</label>
						<div class="col-md-3">
							<div class="input-group">
								<sj:textfield name="selectedId" id="admissionNumber"
									cssClass="form-control input-medium required defaultValue"
									placeholder="Student Admission Number"></sj:textfield>
								<span class="input-group-btn"> <sj:submit
										targets="searchStudentsList" value="Find Student"
										cssClass="submitBt btn blue" cssStyle="float:none;"
										indicator="indicator"
										onBeforeTopics="searchStudAdmissionNumberForm"
										formIds="searchStudentByAdmissionNumber" /> </span>
							</div>
							<span class="hintMessage">(Type admission number and hit
								'Find Student'.)</span>
						</div>
					</div>
				</s:form>
			</div>
		</div>
	</s:if>
<s:else>
	<div class="alert alert-info">
		Currently there are no marks added for any exam type.
	</div>
</s:else>
<%-- </s:if>
<s:elseif test='%{user.isOnlySchoolAdmin=="Y"}'>
	<div class="alert alert-info">
		You have not uploaded score card template.
		<a href="#" onclick="javascript:manageScoreCardTemplate()"><font color="red"><b>Click
			here </b></font></a> to upload score card template.
		<a href="#" onclick="javascript:viewDefaultScoreLink()"><font color="red"><b>Click here </b></font></a>
		for generating default score card.
	</div>
</s:elseif>
<s:else>
	<div class="alert alert-info">
		You have not uploaded scorecard template. Please contact your admin.
	</div>
</s:else> --%>
<div id="searchStudentsList"></div>
<script type="text/javascript">
var selectedClassId=0;
$(document).ready(function() {
	$.destroyTopic('validateStudentNameSearchForm');
	$.destroyTopic('searchStudAdmissionNumberForm');
		$.subscribe('validateStudentNameSearchForm', function(event, data) {
			var studName = $('#studName').val();
			if (studName == null || studName == ''
					|| studName == 'Student Admission Number'
					|| studName == 'Student First or Last Name') {
				alert("Please enter first name or last name.");
				event.originalEvent.options.submit=false;

			} else if (studName.length < 3) {
				alert("Please enter minimum 3 characters.");
				$('#studName').val('Student First or Last Name');
				event.originalEvent.options.submit=false;
			}
			else{
				$('#searchStudentsList').show();
			}
		});	
		$.subscribe('searchStudAdmissionNumberForm', function(event, data) {
			var admissionNumber = $('#admissionNumber').val();
			if (admissionNumber == null || admissionNumber == ''
					|| admissionNumber == 'Student Admission Number') {
				alert("Please enter student admission number.");
				event.originalEvent.options.submit=false;

			} else if (admissionNumber.length < 3) {
				alert("Please enter minimum 3 characters.");
				$('#admissionNumber').val('Student Admission Number');
				event.originalEvent.options.submit=false;
			}
			else{
				$('#searchStudentsList').show();
			}
		});
});

$('input.generateScoreCCReport').click(function(){
  if($(this).attr('id')=='generateCC'){
    $('input#selectedGeneratedFile').val('G');
    $('div#divCCReportCard').hide();
  }
  else{
    $('input#selectedGeneratedFile').val('D');
    $('input#selectedClassId').val(selectedClassId);
    $("input[name='chkBoxSelectedIds']").each(function(){
	    $(this).parent('span').removeClass('checked');
	    $(this).attr('checked',false);
	});
	 $("input.allInvoices").attr('checked',false);
	 $("input.allInvoices").parent('span').removeClass('checked');
     $('input#selectedAdmissionNumbers').val('');
  }
});

$('a#downloadStudentCC').click(function(){
 $('#isCCReportExists').html('<div align="center" style="padding:30px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
  var searchURL = jQuery.url.getChatURL("/student/ajaxGetCCReportStudents.do");
  var pars = "classId=" + selectedClassId;
  $.ajax( {
			type : "POST",
			url : searchURL,
			data : pars,
			cache : false,
			success : function(html) {
			$('#isCCReportExists').html(html);
			}
		});
});

//$('select#selectedClassName').change(function(){
	function getClassStudentDetails(className){
		$('input#selectedGeneratedFile').val('G');
        var classTeacher = $('#classTeacher').val();
        var classId=0;
        var classNameVal='';
        var examTypeId = $('#examTypeId').val();
        var selectedVal='';
        if(isNonEmpty(className)){
           selectedVal=className.split(":");
           classNameVal=selectedVal[0];
           classId=selectedVal[1];
           classNameVal = classNameVal.replace(/\&/g, '`');
        }
        if(classId > 0){
	        selectedClassId=classId;
	        $('#isCCReportExists').html('<div align="center" style="padding:30px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
			var adminURL = jQuery.url.getChatURL("/admin/ajaxDownloadCCReportPDF.do");
			var pars = "classId=" + classId + "&examTypeId=" + examTypeId+"&plTitle="+classNameVal;
			$.ajax( {
				type : "POST",
				url : adminURL,
				data : pars,
				cache : false,
				success : function(response) {
				$('#isCCReportExists').html('');
				//alert(response);
				if(isNonEmpty(response))
				{
						if(response == "NotGenerate")
						{
							$('div#errorMsg').show();
							$('div#templateReportCard').hide();
						}
						else{
						  $('div#errorMsg').hide();
						  $('div#templateReportCard').show();
						}
						if(response=="true")
						{							
						  $('div#divCCReportCard').show();
						}
						else{
						  $('div#divCCReportCard').hide();
						  //$('div#errorMsg').html('<div class="alert alert-error col-md-5">Please Before generating scorecard please upload template for that class.</div>');
						}
					}	
				}
			});
		}else{
		  $('div#divCCReportCard').empty();
		  $('div#templateReportCard').empty();
		  $('div#isCCReportExists').empty();
		  alert("Please select class.");
		  return false;
		}
//});
}

function validateScoreCardGenerationByClassForm(){
	var className = $('#selectedClassName').val();
	var examType = $('#examTypeId').val();
	var checkedValues=[];
	$("input[name='chkBoxSelectedIds']:checked").each(function(){
	    checkedValues.push($(this).attr('id').replace("payMent_",""));
	});
	if(checkedValues!='')
	{
	  $('input#selectedGeneratedFile').val('D');
      $('input#selectedClassId').val(selectedClassId);
	}else{
	   $('div#generateCCStudentReport').hide();
	}
	$('input#selectedAdmissionNumbers').val(checkedValues);
	if(examType == ''){
		alert('Please select exam type and class.');
		return false;
	}
	if(className == ''){
		alert('Please select class.');
		return false;
	}
	else{
		if($('input#selectedGeneratedFile').val()=="G"){
			$('#displayScoreCardInfo').show();
			setTimeout(function() {$("#displayScoreCardInfo").hide('blind', {}, 500)}, 5000);
		 }
			$("#classSecParam").val($("select[id='selectedClassName'] option:selected").text());	
			$("#examType").val($("select[id='examTypeId'] option:selected").text());	
			return true;
	}
}
function viewDefaultScoreLink(){
	$('a#doProgressReport').click();
}

function manageScoreCardTemplate(){
	$('a#doScoreCard').click();
}
function manageScoreCardGeneration(value) {
	if (isNonEmpty(value)) {
		if ("Select Class" == value) {
			$('#searchAdmissionNumber').hide();
			$('#searchStudentsList').hide();
			$('#searchStudent').hide();
			$('#selectserchClass').show();
		} else if ("Search Student" == value) {
			$('#searchAdmissionNumber').hide();
			$('#searchStudentsList').hide();
			$('#selectserchClass').hide();
			$('#searchStudent').show();
		} else if ("Search Admission Number" == value) {
			$('#selectserchClass').hide();
			$('#searchStudentsList').hide();
			$('#searchStudent').hide();
			$('#searchAdmissionNumber').show();
		}
	}
}

function getStudentDetails(examTypeId) {
	if (isNonEmpty(examTypeId)) {
		var params = "tempId1=" + examTypeId;
		$('#classResultsDiv').html(
						'<div align="center"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		$.ajax( {
			url : jQuery.url.getChatURL("/exam/ajaxScoreCardTemplatesList.do"),
			cache : true,
			data : params,
			success : function(response) {
				$('#classResultsDiv').html(response);
			}
		});
	}
	else
	{
		$('#classResultsDiv').html("");
	  alert("Please select exam type.");
	}
}
</script>
 