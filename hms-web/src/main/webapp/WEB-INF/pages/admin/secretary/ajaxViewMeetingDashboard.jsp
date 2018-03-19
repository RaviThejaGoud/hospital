<%@ include file="/common/taglibs.jsp"%>
<div class="portlet box blue">
	<div class="portlet-title">
		<div class="caption">
			<i class="fa fa-globe"></i>Meeting Minutes
		</div>
	</div>
	<div class="portlet-body">
		<div id="secretaryMeetingDetailsDiv" class="tab-content">
			<%@ include file="/common/messages.jsp"%>
			<jsp:include page="/WEB-INF/pages/admin/secretary/ajaxViewMeetingDetails.jsp" />
		</div>
	</div>
</div>
