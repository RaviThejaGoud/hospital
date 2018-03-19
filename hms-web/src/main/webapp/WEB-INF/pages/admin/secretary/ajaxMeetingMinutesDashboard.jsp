<div class="portlet box blue">
	<div class="portlet-title">
		<div class="caption">
			<i class="fa fa-globe"></i>Upcoming Meetings
		</div>
	</div>
	<div class="portlet-body">
			<div id="secretaryMeetingDetailsDiv" class="tab-content">
				<%@ include file="/common/messages.jsp"%>
				<jsp:include page="/WEB-INF/pages/admin/secretary/ajaxViewSecretaryMeetingMinutes.jsp" />
			</div>
	</div>
</div>