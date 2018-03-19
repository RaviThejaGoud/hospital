<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Class Assignment Information
				</div>
			</div>
			<div class="portlet-body">
				<div class="tabbable tabbable-custom tabbable-full-width">
					<ul class="nav nav-tabs">
						<s:if test='%{user.isOnlySchoolAdmin == "Y" || user.isSchoolPrincipal=="Y" || user.isOnlySchoolHod=="Y" || user.isOnlySchoolTeacher=="Y" || user.isSchoolDirector == "Y"}'>
							<s:if test='%{#session.previousYear == "N"}'>
								<li>
									<s:url id="urlAddAssignment" action="ajaxDoAddAssignment"
										includeParams="all" escapeAmp="false" namespace="/admin">
										<s:param name="classAssignment.id" value="0"></s:param>
									</s:url>
									<sj:a  href="%{urlAddAssignment}" targets="addClassAssignmentDiv" data-toggle="tab" >Add Assignment</sj:a>
								</li>
							</s:if>
						</s:if>
						<li class="active">
							<s:url id="urlClassAssignment"
								action="ajaxViewAllClassAssignment" namespace="/admin" />
							<sj:a href="%{urlClassAssignment}" targets="mainContentDiv"
								data-toggle="tab">
							View Assignment</sj:a>
						</li>
					</ul>
					<div id="addClassAssignmentDiv" class="tab-content">
					
					<jsp:include page="/common/messages.jsp" />
					
						<s:if
							test="%{studyClassList != null && !studyClassList.isEmpty()}">
							<s:form id="selectStudentForm" action="#" theme="simple"
								cssClass="form-horizontal">
								<!--<div class="row">
									<div class="col-md-6" id="assignmentDateSlectedClassStudents">
										<div class="form-group">
											<label class="col-md-6 control-label">
												<span class="required">*</span>Assignment Date :
											</label>
											<div class="col-md-5">
												<div data-date-start-date="+0d"
													data-date-format="mm/dd/yyyy"
													class="input-group input-medium date date-picker">
													<input type="text" id="attendanceDate"
														name="attendanceDate" readonly="readonly"
														value='<s:property value="attendanceDate"/>'
														class="form-control">
													<span class="input-group-btn">
														<button type="button" class="btn default">
															<i class="fa fa-calendar"></i>
														</button> </span>
												</div>
											</div>
										</div>
									</div>
								</div>
								-->
								<div>
									<div id="getSelectedClassStudents">
									   <%-- <div class="col-md-4">
										<div class="form-group">
											<label class="control-label">
												<span class="required">*</span>Assignment Date :
											</label>
												<div data-date-start-date="+0d"
													data-date-format="yyyy-mm-dd"
													class="input-group input-medium date date-picker">
													<input type="text" id="attendanceDate"
														name="attendanceDate" readonly="readonly"
														value='<s:property value="attendanceDate"/>'
														onchange="javascript:getStudentsByDate(this.value);"
														class="form-control">
													<span class="input-group-btn">
														<button type="button" class="btn default">
															<i class="fa fa-calendar"></i>
														</button> </span>
												</div>
												<span class="help-block">(YYYY-MM-DD)</span>
											</div>
										</div> --%>
										<div id="classDivSection">
											<div id="classFormGrp">
												<label class="control-label">
													<span class="required"> * </span>Select Class :
												</label>
												<s:select list="studyClassList" id="className"
													name="classId" listKey="id" listValue="classAndSection"
													headerKey="" headerValue="- Select Class -" theme="simple"
													cssClass="required form-control input-medium"
													onchange="javascript:getDoGetStudent(this.value);">
												</s:select>
											</div>
										</div>
									</div>
								</div>
							</s:form>
							<div id="createClassAssignmentDiv" style="display: none;">
								<jsp:include
									page="/WEB-INF/pages/common/classAssignment/ajaxViewAssignmentByClass.jsp"></jsp:include>
							</div>
						</s:if>
						<s:else>
							<div class="alert alert-info">
								Currently there are no classes assigned for you.
							</div>
						</s:else>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
changePageTitle("Manage Student");
$(document).ready(function() {
	FormComponents.init();
	/* $("div#assignmentDateSlectedClassStudents").hide(); //this div used in add classassignment
	var startDate = $('span#startDateSpan').attr("class");
	var endDate = $('span#endDateSpan').attr("class");
	var toDayDate = $('span#toDateSpan').attr("class");
	var chgStartDate = $('span#academicStartDateSpan').attr("class");
	var chgEndDate = $('span#academicEndDateSpan').attr("class");
	var studyClassId = $('#className').val();
	var attendanceDate = $('#attendanceDate').val(); */
	$('select#className').val("");
	/* var studyClassSize = '<s:property value="studyClassList.size"/>';
	if(studyClassSize!=0){
		getClassSubjectsDetails("");
	} */
	
});

function getDoGetStudent(studyClassId) {
	var attendanceDate = $('input#attendanceDate').val();
	var pars = "classSectionId=" + studyClassId + "&attendanceDate="+ attendanceDate;
	$("select#studySubjectId option:first").attr('selected','selected');
	if(isNonEmpty(studyClassId)){		
	$("#createClassAssignmentDiv") .html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
	var url = jQuery.url.getChatURL("/admin/ajaxViewAssignmentByClass.do");
	$.ajax( {
		url : url,
		cache : false,
		data : pars,
		success : function(html) {
			$("#createClassAssignmentDiv").html(html);
			$("#createClassAssignmentDiv").show();
			$("div#classDivSection").addClass("col-md-4");
			$("div#classFormGrp").addClass("form-group");
		}
	});
  }
  else{
   alert("!Oops select class");
   $("#createClassAssignmentDiv").hide();
   $("div#classDivSection").removeClass("col-md-4");
	$("div#classFormGrp").removeClass("form-group");
   return false;
  }
}
function getClassSubjectsDetails(subjectId) {
	var attendanceDate = $('input#attendanceDate').val();
	var studyClassId = $('select#className').val();
	var pars = "subjectId=" + subjectId + "&attendanceDate="+ attendanceDate+"&classSectionId=" + studyClassId;
	if(isNonEmpty(studyClassId)){		
	$("#createClassAssignmentDiv")
			.html(
					'<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
	var url = jQuery.url.getChatURL("/admin/ajaxViewAssignmentByClass.do");
	$.ajax( {
		url : url,
		cache : false,
		data : pars,
		success : function(html) {
			$("#createClassAssignmentDiv").html(html);
			
		}
	});
  }
  
}

function getStudentsByDate(attendanceDate) {
	var studyClassId = document.getElementById("className").value;
	var pars = "classSectionId=" + studyClassId + "&attendanceDate="
			+ attendanceDate;
	$("#createClassAssignmentDiv")
			.html(
					'<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
	var url = jQuery.url.getChatURL("/admin/ajaxViewAssignmentByClass.do");
	$.ajax( {
		url : url,
		cache : false,
		data : pars,
		success : function(html) {
			$("#createClassAssignmentDiv").html(html);
		}
	});

}
function clearText(field) {
	if (field.defaultValue == field.value)
		field.value = '';
	else if (field.value == '')
		field.value = field.defaultValue;

}
</script>
