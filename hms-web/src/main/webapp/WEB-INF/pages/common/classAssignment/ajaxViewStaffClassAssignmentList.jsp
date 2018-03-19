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
						<s:if
							test='%{user.isSchoolPrincipal=="Y" || user.isOnlySchoolHod=="Y" || user.isOnlySchoolTeacher=="Y" || user.isSchoolAsstStaff=="Y" || user.isAdminCoordinator=="Y" || user.isSchoolDirector == "Y"}'>
							<s:if test='%{#session.previousYear == "N"}'>
								<s:if
									test="%{objectList != null && !objectList.isEmpty()}">
									<li><s:url id="doAddAssignment"
											action="ajaxDoAddStaffAssignment" includeParams="all"
											escapeAmp="false" namespace="/admin">
											<s:param name="classAssignment.id" value="0"></s:param>
										</s:url> <sj:a href="%{doAddAssignment}"
											targets="stepImportMarkSheetDiv" data-toggle="tab">Add Class Assignment</sj:a>
									</li>
								</s:if>
							</s:if>
						</s:if>
						<li class="active"><s:url id="studentAssignment"
								action="ajaxViewStaffClassAssignment" namespace="/admin" /> <sj:a
								href="%{studentAssignment}" targets="mainContentDiv"
								cssClass="ajaxify PVENT" data-toggle="tab">View</sj:a>
						</li>
					</ul>
					<div id="stepImportMarkSheetDiv" class="tab-content">
					
					<jsp:include page="/common/messages.jsp"></jsp:include>
					
						<s:if
							test="%{objectList != null && !objectList.isEmpty()}">
							<s:form id="selectStudentForm" action="#" theme="simple"
								cssClass="form-horizontal">
								<div>
									<%-- <div class="col-md-4">
										<div class="form-group">
											<label class="control-label"> <span class="required">*</span>Assignment Date :</label>
											<div data-date-start-date="+0d" data-date-format="mm/dd/yyyy"
												class="input-group input-medium date date-picker">
												<input type="text" id="attendanceDate" name="attendanceDate"
													readonly="readonly"
													value='<s:property value="attendanceDate"/>'
													onchange="javascript:getStudentsByDate(this.value);"
													class="form-control"> <span class="input-group-btn">
													<button type="button" class="btn default">
														<i class="fa fa-calendar"></i>
													</button>
												</span>
											</div>
											<span class="help-block">(YYYY-MM-DD)</span>
										</div>
									</div> --%>
									<div class="col-md-4">
										<div class="form-group">
											<label class="control-label"> <span class="required">* </span>Select Class :</label>
											<s:select list="objectList" id="className" name="classId"
												listKey="studyClassId" listValue="classSection" headerKey=""
												headerValue="- Select Class -" theme="simple"
												cssClass="required form-control input-medium"
												onchange="javascript:getDoGetStudent(this.value);">
											</s:select>
										</div>
									</div>
									<!--<div class="col-md-4">
											<div class="form-group">
												<label class="control-label">
													<span class="required"> * </span>Select Subject :
												</label>
												<s:select list="subjectsList" id="studySubjectId"
													name="subjectId" listKey="subjectId"
													listValue="subjectName" headerKey=""
													headerValue="- Select Subject -" theme="simple"
													onchange="javascript:getClassSubjectsDetails(this.value);"
													cssClass="required form-control input-medium selectSubject">
												</s:select>
											</div>
										</div>
								-->
								</div>
							</s:form>
							<div id="createAttendenceDiv">
								<jsp:include
									page="/WEB-INF/pages/common/classAssignment/ajaxViewAssignmentByStaffClass.jsp"></jsp:include>
							</div>
						</s:if>
						<s:else>
							<div class="alert alert-info">Currently there are no
								classes assigned for you.</div>
						</s:else>
					</div>

				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	changePageTitle("Manage Student Assignment");
	$(document).ready(function() {
		FormComponents.init();
		$("div#assignmentDateDiv").hide();
		var startDate = $('span#startDateSpan').attr("class");
		var endDate = $('span#endDateSpan').attr("class");
		var toDayDate = $('span#toDateSpan').attr("class");
		var chgStartDate = $('span#academicStartDateSpan').attr("class");
		var chgEndDate = $('span#academicEndDateSpan').attr("class");
		var studyClassId = $('#className').val();
		var attendanceDate = $('#attendanceDate').val();
		
		getDoGetStudent(studyClassId);
	});

	function getDoGetStudent(studyClassId) {
		//var attendanceDate = document.getElementById("attendanceDate").value;
		var pars = "classSectionId=" + studyClassId+"&tempId2="+studyClassId;
		$("#createAttendenceDiv").html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		var url = jQuery.url
				.getChatURL("/admin/ajaxViewAssignmentByStaffClass.do");
		$.ajax({
			url : url,
			cache : false,
			data : pars,
			success : function(html) {
				$("#createAttendenceDiv").html(html);
			}
		});

	}
	function getStudentsByDate(attendanceDate) {
		var studyClassId = document.getElementById("className").value;
		var pars = "classSectionId=" + studyClassId + "&attendanceDate="
				+ attendanceDate;
		$("#createAttendenceDiv").html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		var url = jQuery.url
				.getChatURL("/admin/ajaxViewAssignmentByStaffClass.do");
		$.ajax({
			url : url,
			cache : false,
			data : pars,
			success : function(html) {
				$("#createAttendenceDiv").html(html);
			}
		});
	}
	function getClassSubjectsDetails(subjectId) {
		
		var attendanceDate = $('input#attendanceDate').val();
		var studyClassId = $('select#className').val();
		var pars = "subjectId=" + subjectId + "&attendanceDate="
				+ attendanceDate + "&classSectionId=" + studyClassId;
		if (isNonEmpty(studyClassId) && isNonEmpty(studyClassId)) {
			$("#createAttendenceDiv").html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
			//var url = jQuery.url.getChatURL("/admin/ajaxViewAssignmentByClass.do");
			var url = jQuery.url.getChatURL("/admin/ajaxViewAssignmentByStaffClass.do");
			$.ajax({
				url : url,
				cache : false,
				data : pars,
				success : function(html) {
					$("#createAttendenceDiv").html(html);
				}
			});
		} else {
			alert("!Oops select class");
			return false;
		}
		
	}

	function clearText(field) {
		if (field.defaultValue == field.value)
			field.value = '';
		else if (field.value == '')
			field.value = field.defaultValue;

	}
</script>
