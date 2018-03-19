<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Exam Schedules & Results
				</div>
			</div>
			<div class="portlet-body">
				<div class="tabbable tabbable-custom tabbable-full-width">
					<ul class="nav nav-tabs">
						<li>
							<s:url id="stepStudentResults" action="ajaxStudentMarksDetails" namespace="/student">
							</s:url>
							<sj:a id="stepStudentResults" href="%{stepStudentResults}" targets="examsContentDiv" data-toggle="tab">Results</sj:a> 
						</li>
						 <li class="active" id="examSchedulesDiv">
							<s:url id="stepStudentExamSchedules" action="ajaxStudentExamSchedules" namespace="/exam">
							</s:url>
							<sj:a id="stepStudentExamSchedules" href="%{stepStudentExamSchedules}" targets="examsContentDiv" data-toggle="tab">Exam schedules</sj:a>
						 </li>
					</ul>
					<div class="tab-content" id="examsContentDiv">
						<jsp:include page="/WEB-INF/pages/exam/student/ajaxViewStudentExamSchedules.jsp"></jsp:include>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function() {
	changePageTitle("Student Schedules & Results");
	$('li#examSchedulesDiv a').click();
});
</script>
