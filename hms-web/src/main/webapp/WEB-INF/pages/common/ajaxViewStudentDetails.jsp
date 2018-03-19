<%@ include file="/common/taglibs.jsp"%>
<s:if
	test="%{(studentVo != null && studentVo != empty) }">
	<div id="studentInfoContent">
		<jsp:include
			page="/WEB-INF/pages/common/ajaxViewStudentAndStaffInfoDetails.jsp" />
	</div>
	<div class="form-body">
		&nbsp;
	</div>
	<div class="tabbable tabbable-custom tabbable-full-width">
		<ul class="nav nav-tabs">
			<%-- <s:if test='%{(student.transportMode=="T")}'>
				<li>
					<s:url id="studentVehicleInfo" action="ajaxViewStudentVehicleInfo"
						namespace="/common" includeParams="all" escapeAmp="false">
						<s:param value="student.id" name="tempId"></s:param>
						<s:param value="student.academicYearId" name="academicYearId"></s:param>
					</s:url>
					<sj:a id="studentVehicleInfo" href="%{studentVehicleInfo}"
						targets="studentEditContentDiv" data-toggle="tab">Vehicle Info</sj:a>
				</li>
			</s:if> --%>
			<li>
				<s:url id="studentstatusInfo" action="ajaxViewStudentStatus"
						namespace="/student" includeParams="all" escapeAmp="false">
						<s:param value="studentVo.id" name="tempId"></s:param>
						<s:param value="studentVo.studyClassVo.id" name="bedId"></s:param>
					</s:url>
					<sj:a id="studentstatusInfo" href="%{studentstatusInfo}"
						targets="studentEditContentDiv" data-toggle="tab">Student Status</sj:a>
			</li>
			<li>
				<s:url id="viewStudentCurricularActivities" action="ajaxViewStudentCurricularActivities"
						namespace="/student" includeParams="all" escapeAmp="false">
						<s:param value="studentVo.id" name="tempId1"></s:param>
						<s:param value="studentVo.account.id" name="tempId"></s:param>
					</s:url>
					<sj:a id="viewStudentCurricularActivities" href="%{viewStudentCurricularActivities}"
						targets="studentEditContentDiv" data-toggle="tab">Activities</sj:a>
			</li>
			<li>
				<s:url id="studentSiblingsInfo" action="ajaxDoAddStudentSiblings"
						namespace="/student" includeParams="all" escapeAmp="false">
						<s:param value="studentVo.id" name="tempId1"></s:param>
						<s:param value="studentVo.account.id" name="tempId"></s:param>
					</s:url>
					<sj:a id="studentSiblingsInfo" href="%{studentSiblingsInfo}"
						targets="studentEditContentDiv" data-toggle="tab">Student Siblings</sj:a>
			</li>
			<li>
				<s:url id="studentDocumentsInfo" action="ajaxViewMyDocumentsInfo"
						namespace="/student" includeParams="all" escapeAmp="false">
						<s:param value="studentVo.id" name="tempId"></s:param>
					</s:url>
					<sj:a id="studentDocumentsInfo" href="%{studentDocumentsInfo}"
						targets="studentEditContentDiv" data-toggle="tab">Documents</sj:a>
			</li>
			<li>
				<s:url id="stepAttendance" action="ajaxGetBaseStudentAttendance"
					namespace="/student" includeParams="all" escapeAmp="false">
					<s:param value="studentVo.account.id" name="tempId"></s:param>
				</s:url>
				<sj:a id="stepAttendance" href="%{stepAttendance}" targets="studentEditContentDiv"
					data-toggle="tab">Attendance</sj:a>
			</li>
			<li>
				<s:url id="stepFee" action="ajaxDoGetMyFeeDetails"
					namespace="/student" includeParams="all" escapeAmp="false">
					<s:param value="studentVo.id" name="tempId"></s:param>
				</s:url>
				<sj:a id="stepFee" href="%{stepFee}"
					targets="studentEditContentDiv" data-toggle="tab">Fee</sj:a>
			</li>
			<li>
				<s:url id="stepExam" action="ajaxExamSchedulesandMarks"
					namespace="/common" includeParams="all" escapeAmp="false">
					<s:param value="studentVo.classNameVo.id" name="student.classNameClassId.id"></s:param>
					<s:param value="studentVo.studyClassVo.id" name="student.classSection.id"></s:param>
					<s:param value="studentVo.id" name="tempId"></s:param>
					<s:param value="studentVo.id" name="studentVo.id"></s:param>
				</s:url>
				<sj:a id="stepExam" href="%{stepExam}" targets="studentEditContentDiv"
					data-toggle="tab">Exam Results</sj:a>
			</li>
			<li class="active" id="attendanceDiv">
				<s:url id="stepStudentPersonalInfo" action="ajaxViewMyPersonalInfo"
					namespace="/student" includeParams="all" escapeAmp="false">
					<s:param value="studentVo.id" name="tempId"></s:param>
				</s:url>
				<sj:a id="stepStudentPersonalInfo" href="%{stepStudentPersonalInfo}"
					targets="studentEditContentDiv" data-toggle="tab">Personal Info</sj:a>
			</li>
		</ul>
		<div class="tab-content">
			<div id="studentEditContentDiv"></div>
		</div>
	</div>
</s:if>
<script type="text/javascript">
$(document).ready(function() {
	$('li#attendanceDiv  a').click();
});
</script>