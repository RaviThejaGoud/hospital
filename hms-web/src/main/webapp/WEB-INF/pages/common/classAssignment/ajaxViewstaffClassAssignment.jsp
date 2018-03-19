<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/json2.js">
</script>
<div class="grid_13">
	<div id="commonStep13">
		<s:if test="%{studentsList != null && !studentsList.isEmpty()}">
			<fieldset>
				<div class="grid_12" align="right"
					data-target="stuendentsAttendanceContent">
					<jsp:include
						page="/WEB-INF/pages/common/ajaxViewPaginationOptions.jsp"></jsp:include>
				</div>
				<div class="grid_12 th">
					<div class="grid_2">
						Subject Name
					</div>
					<div class="grid_4">
						Assignment Description
					</div>
					<div class="grid_2">
						Assigned By
					</div>
					<div class="grid_2">
						Send SMS to Parents
					</div>
					<div class="grid_1">
						Edit
					</div>
					<div class="grid_1">
						Delete
					</div>
				</div>
				<div id="stuendentsAttendanceContent">
					<s:iterator value="studentsList">
						<div class="grid_12 row">
							<div class="grid_2">
								<s:property value="subjectName" />
							</div>
							<div class="grid_4">
								<s:property value="assignmentDescription" />
							</div>
							<div class="grid_2">
								<s:property value="createdBy" />
							</div>
							<div class="grid_2">
								<s:url id="doSendSms" action="ajaxDoGetStudentDetails"
									includeParams="all" escapeAmp="false">
									<s:param name="eventId" value="%{assignmentId}"></s:param>
									<s:param name="classId" value="%{classSectionId}"></s:param>
								</s:url>
								<sj:a href="%{doSendSms}" onCompleteTopics="doSendSms1"
									onBeforeTopics="cleanOpenDivs" indicator="indicator"
									targets="studentDetails%{assignmentId}"> Send SMS
									</sj:a>
							</div>
							<div class="grid_1">
								<s:url id="doEditClassAssignment"
									action="ajaxDoEditClassAssignment" includeParams="all"
									escapeAmp="false">
									<s:param name="tempId" value="%{assignmentId}"></s:param>
								</s:url>
								<sj:a href="%{doEditClassAssignment}"
									onCompleteTopics="doEditClassAssignment" indicator="indicator"
									targets="editClassAssignment%{assignmentId}"
									onBeforeTopics="cleanOpenDivs" cssClass="normalEdit"
									title="Edit">
								</sj:a>
								&nbsp;
							</div>
							<div class="grid_1">
								<s:url id="removeClassAssignment" action="ajaxDeleteAssignment"
									escapeAmp="false">
									<s:param name="tempId" value="%{assignmentId}"></s:param>
								</s:url>
								<s:div cssClass="close emsRemove" id='%{removeClassAssignment}'
									theme="simple" title="Delete Class Assignment"></s:div>
							</div>
							&nbsp;
							<div id="studentDetails<s:property value='assignmentId' />" style="display: none" class='load' />
								<div id="editClassAssignment<s:property value='assignmentId' />"
									style="display: none" class='load' />
								</div>
					</s:iterator>
				</div>
			</fieldset>
		</s:if>
	</div>
</div>

<s:else>
	No Assignments are added for this class and date.
</s:else>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/subscription/paginator_dev.js">
</script>
<script type="text/javascript">
$(document).ready(function() {
	$('#classAssignment').addClass('current');

});

$(function() {
	$("#stuendentsAttendanceContent").pagination();
	$.subscribe('doEditClassAssignment', function(event, data) {

		if ($('#' + data.id).is(":hidden")) {
			$('#' + data.id).show();
		} else {
			$('#' + data.id).hide();
		}
	});

	$.subscribe('doSendSms1', function(event, data) {
		if ($('#' + data.id).is(":hidden")) {
			$('#' + data.id).show();
		} else {
			$('#' + data.id).hide();
		}
	});
});
</script>
<style type="text/css">
@import
	url("${pageContext.request.contextPath}/styles/default/paginationStyles.css")
	;
</style>
