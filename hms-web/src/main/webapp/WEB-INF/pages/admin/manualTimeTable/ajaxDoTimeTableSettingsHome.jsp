<%@ include file="/common/taglibs.jsp"%>
<div class="row-fluid">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Timetable
				</div>
			</div>
			<div class="portlet-body">
				<div class="tabbable tabbable-custom tabbable-full-width">
					<ul class="nav nav-tabs">
						<s:if test='%{#session.previousYear == "N"}'>
							<li>
								<s:url id="collegeDoTimingsId" action="ajaxDoTimeTableSettingsInformation" namespace="/timeTable"/>
								<sj:a href="%{collegeDoTimingsId}" targets="assignSubjectsToPeriods" data-toggle="tab">Timetable Settings</sj:a>
							</li>
							<li>
								<s:url id="assignDoSubjectsToPeriodsId" action="ajaxDoGetStudyClassList" namespace="/timeTable"/>
								<sj:a href="%{assignDoSubjectsToPeriodsId}" targets="assignSubjectsToPeriods" data-toggle="tab">Add/Update Timetable</sj:a>
							</li>
						</s:if>
						<li class="active">
							<s:url id="viewTimeTableDetails" action="ajaxViewTimeTableDetails" namespace="/timeTable"/>
							<sj:a href="%{viewTimeTableDetails}" targets="mainContentDiv" data-toggle="tab">View</sj:a>
						</li>
					</ul>
					<div class="tab-content" id="assignSubjectsToPeriods">
						<jsp:include page="/WEB-INF/pages/admin/manualTimeTable/ajaxViewStudyClassListForViewTimeTable.jsp"/>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	$(document).ready(function() {
		changePageTitle("Timetable");
	});
</script>