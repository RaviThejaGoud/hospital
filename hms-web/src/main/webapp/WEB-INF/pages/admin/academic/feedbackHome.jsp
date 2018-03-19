<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Feedback Details
				</div>
			</div>
			<div class="portlet-body">
				<div class="tabbable tabbable-custom tabbable-full-width">
						<ul class="nav nav-tabs">
							<s:if test='%{(#session.academicYear == null || #session.academicYear.isEmpty()) || (#session.previousYear=="N")}'>
								<li id="feedbackQuestion">
									<s:url id="urlPostFeedback" action="ajaxDoPostFeedback"
										namespace="/admin" includeParams="all" escapeAmp="false">
										<s:param name="tempId" value="0" />
									</s:url>
									<sj:a href="%{urlPostFeedback}" targets="feedbackContent"
										data-toggle="tab">Create Question</sj:a>
								</li>
							</s:if>
							<li id="teacherDiv">
								<s:url id="viewStaffFeedbackList" action="ajaxViewFeedbackList"
									namespace="/admin" includeParams="all" escapeAmp="false">
									<s:param name="tempString">Teacher Feedback Questions</s:param>
								</s:url>
								<sj:a href="%{viewStaffFeedbackList}" targets="mainContentDiv"
									data-toggle="tab">View Staff Feedback</sj:a>
							</li>
							<li  id="schoolDiv">
								<s:url id="viewSchoolFeedbackList" action="ajaxViewFeedbackList"
									namespace="/admin" includeParams="all" escapeAmp="false">
									<s:param name="tempString">School Feedback Questions</s:param>
								</s:url>	
								<sj:a href="%{viewSchoolFeedbackList}" targets="mainContentDiv"
									data-toggle="tab">View School Feedback</sj:a>
							</li>
						</ul>
					<div id="feedbackContent" class="tab-content">
						<jsp:include
							page="/WEB-INF/pages/admin/academic/selectChildForFeedback.jsp"></jsp:include>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
changePageTitle('Feedback');
	var feedCheck1='<s:property value="tempString"/>';
	if(feedCheck1 == "School Feedback Questions"){
		$('li#schoolDiv').addClass('active');
		$('li#teacherDiv').removeClass('active');
	}else{
		$('li#teacherDiv').addClass('active');
		$('li#schoolDiv').removeClass('active');
	}	
</script>


