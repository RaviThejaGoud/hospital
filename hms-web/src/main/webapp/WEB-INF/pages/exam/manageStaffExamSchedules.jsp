<%@ include file="/common/taglibs.jsp"%> 
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Exam Schedules 
				</div>
			</div>
			<div class="portlet-body">
				<div class="tabbable tabbable-custom tabbable-full-width">
				<s:if test="%{studyClassList != null && !studyClassList.isEmpty()}">
					<ul class="nav nav-tabs">
						<li>
							<s:url id="urlAddmarksScreen" action="ajaxDoAddMarks"
								namespace="/exam" />
							<sj:a id="addmarksScreen" href="%{urlAddmarksScreen}" button="false" data-toggle="tab"
								targets="classContentDiv">Add Marks</sj:a>
						</li>
						<li class="dropdown">
						 <a data-hover="dropdown" data-toggle="dropdown"
								class="dropdown-toggle js-activated" href="#"> Import Marks Sheet <b class="caret"></b> </a>
						 	<ul role="menu" class="dropdown-menu pull-right">
								<li>
									<s:url id="stepImportMarkSheet" action="ajaxDownloadAndUploadMarksSheet" namespace="/exam">
									</s:url>
									<sj:a href="%{stepImportMarkSheet}" targets="classContentDiv" data-toggle="tab">Import Marks Sheet</sj:a> 
								</li>
								<li>
									<s:url id="stepDownLoadMarkSheet" action="ajaxGetStaffHandleClassSections" namespace="/exam">
									</s:url>
									<sj:a href="%{stepDownLoadMarkSheet}" targets="classContentDiv" data-toggle="tab">Download Marks Sheet</sj:a> 
								</li>
							</ul>
						 </li>
						 <s:if test='%{(tempBoolean || user.isOnlySchoolHod=="Y")}'>
						 <li class="dropdown active">
							<a data-hover="dropdown" data-toggle="dropdown"
								class="dropdown-toggle js-activated" href="#">My Class Exam Schedules <b class="caret"></b> </a>
							<ul role="menu" class="dropdown-menu pull-right">
								<li class="active">
									<s:url id="stepExamSchedules" namespace="/exam"
										action="ajaxClassTeacherExamSchedules" includeParams="all"
										escapeAmp="false">
									</s:url>
									<sj:a href="%{stepExamSchedules}" targets="classContentDiv"
										data-toggle="tab">My Class Exam Schedules</sj:a>
								</li>
								<li>
									<s:url id="urlAddExamSyllabus" action="ajaxDoAddExamSyllabus"
										namespace="/exam" />
									<sj:a id="addExamSyllabus" href="%{urlAddExamSyllabus}"
										targets="classContentDiv">Add Exam Syllabus</sj:a>
								</li>
								<li>
									<s:url id="urlAddExamSchedules" action="ajaxDoAddExamSchedules"
										namespace="/exam" />
									<sj:a id="addExamSchedules" href="%{urlAddExamSchedules}"
										targets="classContentDiv">Add Exam Schedule</sj:a>
								</li>
							</ul>
						</li>
						</s:if>
						<s:else>
							<li class="active">
							<s:url id="stepExamSchedules" namespace="/exam"
								action="ajaxClassTeacherExamSchedules" includeParams="all"
								escapeAmp="false">
							</s:url>
							<sj:a href="%{stepExamSchedules}"
								targets="classContentDiv" data-toggle="tab">My Class Exam Schedules</sj:a>
						</li>
						</s:else>
					</ul>
					<div id="classContentDiv" class="tab-content">
						<jsp:include page="/WEB-INF/pages/exam/ajaxClassTeacherExamSchedules.jsp"></jsp:include>
					</div>
					</s:if>
					<s:else>
						<div class="alert alert-info">
							Currently there are no classes assigned for you.
						</div>
					</s:else>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function() {
	changePageTitle("Exams & Results");
	$('.js-activated').dropdownHover().dropdown();
	$('.blockHeader h2').html('Manage Academics');
	});
</script>
