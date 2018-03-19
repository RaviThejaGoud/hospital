<%@ include file="/common/taglibs.jsp"%>
<s:if test="%{staffSyllabusPlanner.id !=0}">
<div data-width="760" class="modal fade modal-overflow in" id="responsive3" style="display: block; width: 845px; margin-left: -379px; margin-top: 200px;" aria-hidden="false">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
			<h4 class="modal-title bold pageTitle">Update Subject Planner</h4>
		</div>
		<div class="modal-body">
	</s:if>
<s:form action="ajaxAddSubPlanner" id="createStudentPlannerInfo"
	cssClass="form-horizontal" method="post" theme="simple" namespace="/staff">
	<s:hidden name="tempId2" id="subPlannerId"
		value="%{staffSyllabusPlanner.id}"></s:hidden>
	<div class="form-body">
		<s:if test="%{staffSyllabusPlanner.id !=0}">
			<s:hidden name="staffSyllabusPlanner.studySubjectId" id="studySubjectId"></s:hidden>
			<s:hidden name="staffSyllabusPlanner.studyClassId" id="studyClassId"></s:hidden>
			<s:hidden name="selectedId" id="staffId"></s:hidden>
		</s:if>
		<s:if test="%{staffSyllabusPlanner==null}">
			<div id="classSubjectDiv" style="display: none;">
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4">
								<span class="required">*</span>Select Class :
							</label>
							<div class="col-md-5">
								<s:select list="studyClassList" id="className"
									cssClass="required form-control input-medium"
									name="staffSyllabusPlanner.studyClassId" listKey="id"
									listValue="classAndSection" headerKey=""
									headerValue="- Select Class -" theme="simple"
									onchange="javascript:getClassSubjects(this.value);">
								</s:select>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div id="subjectsDiv" style="display: none">
							<jsp:include
								page="/WEB-INF/pages/staff/class/ajaxGetClassSubjectForPlanner.jsp" /></div>
					</div>
				</div>
			</div>
		</s:if>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4">
						<span class="required">*</span>Unit Name :
					</label>
					<div class="col-md-5">
						<sj:textfield name="staffSyllabusPlanner.unitName" id="unitName"
							cssClass="required form-control input-medium" maxlength="60"></sj:textfield>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4">
						<span class="required">*</span>Chapter Name :
					</label>
					<div class="col-md-5">
						<sj:textfield name="staffSyllabusPlanner.chapterName"
							id="chapterName" cssClass="required form-control input-medium"
							maxlength="60"></sj:textfield>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4">
						<span class="required">*</span>Topic Name :
					</label>
					<div class="col-md-5">
						<sj:textfield name="staffSyllabusPlanner.topicName" id="topicName"
							cssClass="required form-control input-medium" maxlength="60"></sj:textfield>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4">
						Periods Taken :
					</label>
					<div class="col-md-5">
						<sj:textfield name="staffSyllabusPlanner.periodsTaken"
							id="periodsTaken" cssClass="form-control input-medium numeric"
							maxlength="2"></sj:textfield>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4">
						Periods Required :
					</label>
					<div class="col-md-5">
						<sj:textfield name="staffSyllabusPlanner.periodsRequired"
							id="periodsRequired" cssClass="form-control input-medium numeric"
							maxlength="2"></sj:textfield>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4">
						<span class="required">*</span>Start Date :
					</label>
					<div class="col-md-5">
						<div data-date-start-date="+1d" data-date-format="mm/dd/yyyy" 
						class="input-group input-medium date date-picker">
							<input type="text" id="startDate" readonly="readonly"
								name="staffSyllabusPlanner.startDate" onchange="checkStartTimeEndTImeValidation();"
								value='<s:property value="staffSyllabusPlanner.startDateStr"/>'
								class="required form-control"/>
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
						<span class="required">*</span>Complete Date :
					</label>
					<div class="col-md-5">
						<div data-date-start-date="+1d" data-date-format="mm/dd/yyyy"
							class="input-group input-medium date date-picker">
							<input type="text" id="completeDate" readonly="readonly" onchange="checkStartTimeEndTImeValidation();"
								name="staffSyllabusPlanner.completedDate"
								value='<s:property value="staffSyllabusPlanner.completeDateStr"/>'
								class="required form-control" />
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
		<div class="form-actions fluid">
			<div class="col-md-offset-2 col-md-6">
				<s:if test="%{staffSyllabusPlanner.id !=0}">
					<sj:submit targets="subjectPlannerViewDiv" value="Submit"
					cssClass="submitBt btn blue" indicator="indicator"
					 id="submitButton2" validate="true"
					formIds="createStudentPlannerInfo" 
					onBeforeTopics="addStudentClassPlanner" />
					<button type="button" data-dismiss="modal" class="btn default">
						Cancel
					</button>
				</s:if>
				<s:else>
					<sj:submit targets="mainContentDiv" value="Submit"
					cssClass="submitBt btn blue" indicator="indicator"
					 id="submitButton2" validate="true"
					formIds="createStudentPlannerInfo" 
					onBeforeTopics="addStudentClassPlanner" />
					<s:url id="doAddSubPlannerCancel"
						action="ajaxViewSubPlannerDetails" includeParams="all"
						escapeAmp="false"></s:url>
					<sj:a href="%{doAddSubPlannerCancel}" cssClass="btn default"
						targets="mainContentDiv" button="false">Cancel</sj:a>
				</s:else>
			</div>
		</div>
	</div>
</s:form>
<s:if test="%{staffSyllabusPlanner.id !=0}">
		</div>
		</div>
		</s:if>
<script type="text/javascript">
$(document).ready(function() {
$('.numeric').numeric();
		$('.alphabet').alpha( {
			allow : "a-z,A-Z.?/~!@#)() "
		});
		$('.numericDot').numeric( {
			allow : "."
		});
FormComponents.init();
var plannerId = $("#subPlannerId").val();
if(plannerId>0){
	changePageTitle('Edit Subject Planner');
}else{
	changePageTitle('Create Subject Planner');
}
	var plannerId = $("#plannerId").val();
	if (plannerId > 0) {
		$('div#classSubjectDiv').empty();

	} else {
		$('div#classSubjectDiv').show();
	}
});

$.subscribe('addStudentClassPlanner',function(event, data) {
	 $('button.close').click();
	});

function getClassSubjects(selectBox) {
	var organizationId = $("select#className").val();
	var url = jQuery.url.getChatURL("/staff/ajaxGetClassPlannerSubject.do");
	if (organizationId.length == 0) {
		alert("!Oops select Class");
	} else {
		$("#subjectsDiv")
				.html(
						'<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		var pars = "student.categoryId=" + organizationId;
		$.ajax( {
			url : url,
			cache : false,
			data : pars,
			success : function(html) {
				$("#subjectsDiv").html(html);
				document.getElementById('subjectsDiv').style.display = "block";
			}
		});
	}
}
function checkStartTimeEndTImeValidation() {
	var startDate = $('#startDate').val();
	var completeDate = $('#completeDate').val();
	if (isNonEmpty(startDate) && isNonEmpty(completeDate)) {
		if (completeDate < startDate) {
			alert("Your complete Date is equal or more than your start date.");
			$('#completeDate').val("");
		}
	}
}
</script>