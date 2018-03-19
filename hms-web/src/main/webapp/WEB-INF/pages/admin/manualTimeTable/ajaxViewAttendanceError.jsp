<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/messages.jsp"%>
<s:if test="%{attendanceSubmittedCount > 0}">
	<div class="alert alert-info">
		Attendance Already submitted for this class, You can't change the timetable.
	</div>
</s:if>
