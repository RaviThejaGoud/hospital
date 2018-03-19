<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
				<i class="fa fa-globe"></i>
					Exam Schedules
				</div>
			</div>
			<div class="portlet-body">
				<div class="tabbable tabbable-custom tabbable-full-width">
					<ul class="nav nav-tabs">
						 <li>
							<s:url id="urlAddExamSyllabus" action="ajaxDoAddExamSyllabus" namespace="/exam">
								<!--<s:param name="classId" value="0" />
							--></s:url>
							<sj:a id="addExamSyllabus" href="%{urlAddExamSyllabus}" targets="classContentDiv" data-toggle="tab">Add Exam Syllabus</sj:a> 	
						 </li>
						 <li>
							<s:url id="urlAddExamSchedules" action="ajaxDoAddExamSchedules" namespace="/exam">
								<!--<s:param name="classId" value="0" />-->
							</s:url>
							<sj:a id="addExamSchedules" href="%{urlAddExamSchedules}" targets="classContentDiv" data-toggle="tab">Add Exam Schedule</sj:a> 	
						 </li>
						 <li class="active">
							<s:url id="urlviewSub" action="ajaxDoExamShedules" namespace="/exam">
							</s:url>
							<sj:a id="viewSub" href="%{urlviewSub}" targets="mainContentDiv" data-toggle="tab">View Exam Schedules</sj:a> 	
						 </li>
					</ul>
					<div id="classContentDiv" class="tab-content">
						<jsp:include page="/WEB-INF/pages/exam/ajaxClassExamScheduleDetails.jsp" />
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function() {
	changePageTitle("Exam Schedules");
});
</script>
