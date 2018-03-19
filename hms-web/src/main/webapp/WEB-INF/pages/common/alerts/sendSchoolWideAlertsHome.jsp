<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Dashboard Alerts
				</div>
			</div>
			<div class="portlet-body">
				<div class="tabbable tabbable-custom tabbable-full-width">
					<ul class="nav nav-tabs">
						<li>
							<s:if test='%{user.IsSchoolAdmin=="Y" || user.isSchoolHostel=="Y" || user.isSchoolDirector == "Y"}'>
								<s:if
									test='%{(#session.academicYear == null || #session.academicYear.isEmpty()) || (#session.previousYear=="N")}'>
									<s:url id="urlSchoolWideAlertsLink"
										action="ajaxDoSendSchoolWideAlerts" namespace="/common" />
									<sj:a href="%{urlSchoolWideAlertsLink}"
										targets="schoolWideAlertsHome" data-toggle="tab">Add Alert</sj:a>
								</s:if>
							</s:if>
						</li>
						<li class="active" id="schoolWideMessage">
							<s:url id="viewAlert" action="ajaxDoGetSchoolWideAlertsList"
								namespace="/common">
							</s:url>
							<sj:a id="viewAlert" href="%{viewAlert}" targets="mainContentDiv"
								data-toggle="tab">View Alerts</sj:a>
						</li>
					</ul>
					<div id="schoolWideAlertsHome" class="tab-content">
						<jsp:include page="/common/messages.jsp" />
						<div id="autoCompleterRollText">
						<s:if test="%{alertsList != null && !alertsList.isEmpty()}">
							<div class="panel-body col-md-12">
								<div class="col-md-1">
									<span class="label label-danger"> NOTE : </span>
								</div>
								<div class="col-md-10">
									<ul>
										<li>
											You can view all alerts from the below table.
										</li>
										<s:if test='%{user.isSchoolAdmin=="Y"}'>
										<li>
											You cannot see the delete button for the past alerts
										</li>
										</s:if>
									</ul>
								</div>
							</div>
							</s:if>
							<s:if
								test='%{user.isSchoolStudent!="Y" && user.isParent!="Y" && user.isSchoolTeacher!="Y"}'>
								<jsp:include
									page="/WEB-INF/pages/common/alerts/sendSchoolWideAlertsList.jsp"></jsp:include>
							</s:if>
							<s:if test='%{user.IsSchoolStudent=="Y" || user.isParent=="Y"}'>
								<jsp:include
									page="/WEB-INF/pages/common/alerts/sendSchoolWideAlertsListforStudent.jsp"></jsp:include>
							</s:if>
							<s:if test='%{user.isSchoolTeacher=="Y"}'>
								<jsp:include
									page="/WEB-INF/pages/common/alerts/sendSchoolWideAlertsListforStaff.jsp"></jsp:include>
							</s:if>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function() {
	changePageTitle('Dashboard Messages ');
});
</script>
