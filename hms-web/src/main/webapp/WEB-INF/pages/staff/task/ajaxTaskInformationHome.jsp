<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i> Manage Task Information 
				</div>
			</div>
			<div class="portlet-body">
				<div class="tabbable tabbable-custom tabbable-full-width">
					<ul class="nav nav-tabs">
					<s:if test='%{user.isOnlySchoolAdmin == "Y" || user.roleName=="ROLE_VICEPRINCIPAL" || user.isSchoolPrincipal=="Y" || tempBoolean || user.isOnlySchoolHod=="Y" || user.isSchoolDirector == "Y"}'>
						<li id="taskandReminder">
							<s:url id="urlAddTaskInfo" action="ajaxDoCreateTaskInformaion" namespace="/staff">
								<s:param name="taskDetailsVO.id" value="0" />
							</s:url>
							<sj:a id="addTaskInfo" href="%{urlAddTaskInfo}" targets="taskInfoContentDiv" data-toggle="tab">Create Task / Reminder</sj:a>
						</li>
						<li id="reminderActiveDiv">
							<s:url id="viewReminderIndo" action="ajaxReminderInformation" namespace="/staff"></s:url>
							<sj:a id="viewReminderIndo" href="%{viewReminderIndo}" targets="taskInfoContentDiv" data-toggle="tab">View Reminder</sj:a>
						</li>
						</s:if>
						<li class="active">
							<s:url id="viewTaskIndo" action="ajaxTaskInformationHome" namespace="/staff"></s:url>
							<sj:a id="viewTaskInfo" href="%{viewTaskIndo}" targets="mainContentDiv" data-toggle="tab">View Task</sj:a>
						</li>
					</ul>
					<div id="taskInfoContentDiv" class="tab-content">
						<%@ include file="/common/messages.jsp"%>
						<jsp:include page="/WEB-INF/pages/staff/task/ajaxViewTaskInformation.jsp"></jsp:include>
					</div>
				</div>
			</div>
		</div>
	</div>          
</div>
<script type="text/javascript">
$(document).ready(function() {
	changePageTitle("Task Info");
});
$('html, body').animate({ scrollTop: $(document).height() - $(window).height() }, 10, function() {
    $(this).animate({ scrollTop: 0 }, 10);
});

</script>
 