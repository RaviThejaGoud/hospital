<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Manage Students Activities
				</div>
			</div>
			<div class="portlet-body">
				<div class="tabbable tabbable-custom tabbable-full-width">
					<ul class="nav nav-tabs">
						<s:if test='%{#session.previousYear=="N"}'>
							<li class="dropdown" id="manageActive">
								<a data-hover="dropdown" data-toggle="dropdown"
									class="dropdown-toggle js-activated" href="#">Manage
									Assessments<b class="caret"></b> </a>
								<ul role="menu" class="dropdown-menu pull-right">
									<li>
										<s:url id="urlViewStudentsAssessments"
											action="ajaxManageStudentAssessments" includeParams="all"
											namespace="/exam" escapeAmp="false">
										</s:url>
										<sj:a href="%{urlViewStudentsAssessments}"
											targets="studentsActivitiesContent" data-toggle="tab">Manage Assessments</sj:a>
									</li>
									<li>
										<s:url id="urlAddStudentAssessmentReport"
											action="ajaxDoAddStudentAssessments" includeParams="all"
											escapeAmp="false" namespace="/exam">
											<s:param name="studentsAssessments.id" value="0"></s:param>
										</s:url>
										<sj:a href="%{urlAddStudentAssessmentReport}"
											targets="studentsActivitiesContent" data-toggle="tab">Add Assessment</sj:a>
									</li>
								</ul>
							</li>
									<li>
										<s:url id="urlAddStudentActivityGrades"
											action="ajaxManageStudentActivitiesGrades"
											includeParams="all" escapeAmp="false" namespace="/exam">
											<s:param name="studentActivities.id" value="0"></s:param>
										</s:url>
										<sj:a href="%{urlAddStudentActivityGrades}"
											targets="studentsActivitiesContent" data-toggle="tab">Manage Activities Grades</sj:a>
									</li>
						</s:if>
						<li class="dropdown">
							<a data-hover="dropdown" data-toggle="dropdown"
								class="dropdown-toggle js-activated" href="#">Download /
								Upload Activities Results<b class="caret"></b> </a>
							<ul role="menu" class="dropdown-menu pull-right">
								<li>
									<s:url id="urlDoManageActivityMarks"
										action="ajaxDownloadActivitiesTemplate" includeParams="all"
										escapeAmp="false" namespace="/exam" />
									<sj:a href="%{urlDoManageActivityMarks}"
										targets="studentsActivitiesContent" data-toggle="tab">Download / Upload Activities Template</sj:a>
								</li>
								<li>
									<s:url id="urlUploadActMarksSheet"
										action="ajaxDoUploadStudentsActivitiesMarks"
										includeParams="all" escapeAmp="false" namespace="/exam">
									</s:url>
									<sj:a href="%{urlUploadActMarksSheet}" indicator="indicator"
										targets="studentsActivitiesContent" data-toggle="tab">Upload Students Activities Marks</sj:a>
								</li>
							</ul>
						</li>
						<li class="dropdown active">
							<a data-hover="dropdown" data-toggle="dropdown"
								class="dropdown-toggle js-activated" href="#">View Activities<b class="caret"></b> </a>
							<ul role="menu" class="dropdown-menu pull-right">	
						<li  id="studentActive">
							<s:url id="urlAddStudentActivity"
								action="ajaxManageStudentActivities" includeParams="all"
								namespace="/exam" escapeAmp="false">
							</s:url>
							<sj:a href="%{urlAddStudentActivity}" targets="mainContentDiv"
								data-toggle="tab" id="viewAllCategoriesId">View Activities</sj:a>
						</li>
						<li>
							<s:url id="urlAddStudentActivityReport"
								action="ajaxDoAddStudentActivities" includeParams="all"
								escapeAmp="false" namespace="/exam">
								<s:param name="studentActivities.id" value="0"></s:param>
							</s:url>
							<sj:a href="%{urlAddStudentActivityReport}" id="addStudentActivity"
								targets="studentsActivitiesContent" data-toggle="tab">Add Activity</sj:a>
						</li>
						</ul>
						</li>
					</ul>
					<div class="tab-content" id="studentsActivitiesContent">
						<jsp:include
							page="/WEB-INF/pages/exam/admin/ajaxViewStudentActivities.jsp"></jsp:include>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
