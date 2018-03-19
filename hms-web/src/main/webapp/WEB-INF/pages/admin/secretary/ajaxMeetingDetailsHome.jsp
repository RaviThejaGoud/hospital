<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12" id="stepStaffLeavesEditDiv">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Manage Meetings
				</div>
			</div>
			<div class="portlet-body">
				<div class="tabbable tabbable-custom tabbable-full-width">
					<ul class="nav nav-tabs">
						<s:if test='%{user.isSecretaryPA == "Y"}'>
							<li>
								<s:url id="DoAddMeetingDetails" action="ajaxDoAddMeetingDetails" namespace="/admin"> </s:url> 
								<sj:a href="%{DoAddMeetingDetails}" targets="meetingDetailsDivId"
									data-toggle="tab">Add Meetings</sj:a>
							</li>
							
							<li>
								<s:url id="DoUploadMeetingMinutes"
									action="ajaxDoUploadMeetingMinutes" namespace="/admin"> </s:url> 
								<sj:a href="%{DoUploadMeetingMinutes}" targets="meetingDetailsDivId"
									data-toggle="tab">Add Meeting Minutes</sj:a>
							</li>

						</s:if>
						
						<li class="active" id="staffLeaveDetails">
							<s:url id="viewLeaves" action="ajaxMeetingDetailsHome"
								namespace="/admin">
							</s:url>
							<sj:a href="%{viewLeaves}" targets="mainContentDiv"
								data-toggle="tab">Upcoming Meetings</sj:a>
						</li>
					</ul>
					<div id="meetingDetailsDivId" class="tab-content">
						<%@ include file="/common/messages.jsp"%>
						<jsp:include
							page="/WEB-INF/pages/admin/secretary/ajaxMeetingDetailsList.jsp" />
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function() {
	changePageTitle('Meeting Details');
});
</script>
