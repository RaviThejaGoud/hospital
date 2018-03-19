<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/ems.js"></script>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
				<i class="fa fa-globe"></i>
					Grades & Exam Types
				</div>
			</div>
			<div class="portlet-body">
				<div class="tabbable tabbable-custom tabbable-full-width">
					<ul class="nav nav-tabs">
					<%-- <s:if test='%{academicYear.isDefaultExamTypeStatus=="N"}'>
						<li>
							<s:url id="doCreateDefaultExamtypes" action="ajaxDoCreateExamTypesAlongWithClassesAndExamSubTypes"
								includeParams="all" escapeAmp="false" namespace="/exam">
							</s:url>
							<sj:a href="%{doCreateDefaultExamtypes}" indicator="indicator"
								targets="examContentInfo" button="false" data-toggle="tab">Default Create Exam Types </sj:a>
						</li>
					</s:if> --%>
					<li class="dropdown">
							<a data-hover="dropdown" data-toggle="dropdown"
								class="dropdown-toggle js-activated" href="#"> View Exam Grades <b class="caret"></b>
							</a>
							<ul role="menu" class="dropdown-menu pull-right">
								<li class="active">
									<s:url id="doManageOverAllGrades"
										action="ajaxViewExamTypesAndGrades" includeParams="all"
										escapeAmp="false" namespace="/exam">
										<s:param name="tempString">overAllGrades</s:param>
									</s:url>
									<sj:a href="%{doManageOverAllGrades}" targets="examContentInfo"
										data-toggle="tab">View Exam Grades</sj:a>
								</li>
								<s:if test='%{#session.previousYear=="N"}'>
									<li>
										<s:url id="doAddNewOverAllGradesTypeList"
											action="ajaxDoAddOverAllGrades" includeParams="all" namespace="/exam"
											escapeAmp="false">
											<s:param name="overAllGrades.id" value="0"></s:param>
										</s:url>
										<sj:a href="%{doAddNewOverAllGradesTypeList}"
											indicator="indicator" targets="examContentInfo"
											button="false" >Add Exam Grades</sj:a>
									</li>
								</s:if>
							</ul>
						</li>
						<li class="dropdown">
						<a data-hover="dropdown" data-toggle="dropdown"
								class="dropdown-toggle js-activated" href="#"> View
								Subject Grades <b class="caret"></b>
							</a>
							<ul role="menu" class="dropdown-menu pull-right">
								<li>
									<s:url id="doManageGrades" action="ajaxViewExamTypesAndGrades"
										includeParams="all" escapeAmp="false" namespace="/exam">
										<s:param name="tempString">grades</s:param>
									</s:url>
									<sj:a href="%{doManageGrades}" targets="examContentInfo"
										data-toggle="tab">View Subject Grades</sj:a>
								</li>
								<s:if test='%{#session.previousYear=="N"}'>
								<li>
									<s:url id="doAddNewGradesTypeList"
										action="ajaxDoAddSchoolGrades" includeParams="all" namespace="/exam"
										escapeAmp="false">
										<s:param name="schoolGrades.id" value="0"></s:param>
									</s:url>
									<sj:a href="%{doAddNewGradesTypeList}" indicator="indicator"
										targets="examContentInfo" button="false">Add Subject Grades</sj:a>
								</li>	
							</s:if>	
							</ul>
						</li>
						
						<li class="dropdown">
							<a data-hover="dropdown" data-toggle="dropdown"
								class="dropdown-toggle js-activated" href="#"> View Exam
								Types <b class="caret"></b>
							</a>
							<ul role="menu" class="dropdown-menu pull-right">
								<li>
									<s:url id="doManageExamtypes"
										action="ajaxViewExamTypesAndGrades" includeParams="all"
										escapeAmp="false">
										<s:param name="tempString">examTypes</s:param>
									</s:url>
									<sj:a href="%{doManageExamtypes}" targets="examContentInfo"
										data-toggle="tab">View Exam Types</sj:a>
								</li>
								<s:if test='%{#session.previousYear=="N"}'>
									<li>
										<s:url id="doAddNewExamTypeList" action="ajaxDoAddExamType"
											includeParams="all" escapeAmp="false">
											<s:param name="examTypes.id" value="0" />
										</s:url>
										<sj:a href="%{doAddNewExamTypeList}" indicator="indicator"
											targets="examContentInfo" button="false">Add Exam Types</sj:a>
									</li>
									<li>
										<s:url id="doChgExamtypesOrder"
											action="ajaxChangeExamTypesOrder" escapeAmp="false">
										</s:url>
										<sj:a href="%{doChgExamtypesOrder}" indicator="indicator"
											targets="examContentInfo" button="false">Change Exam Types Order</sj:a>
									</li>
								</s:if>
							</ul>
						</li>
						<li class="dropdown active">
							<a data-hover="dropdown" data-toggle="dropdown"
								class="dropdown-toggle js-activated" href="#"> View Exam
								Subtypes <b class="caret"></b>
							</a>
							<ul role="menu" class="dropdown-menu pull-right">
								<li>
									<s:url id="doManageSubTypes"
										action="ajaxManageExamSettings" includeParams="all"
										escapeAmp="false" namespace="/exam">
										<s:param name="tempString">subTypes</s:param>
									</s:url>
									<sj:a href="%{doManageSubTypes}" targets="mainContentDiv"
										data-toggle="tab">View Exam Subtypes</sj:a>
								</li>
								<s:if test='%{#session.previousYear=="N"}'>
									<li>
										<s:url id="doAddNewSubTypes" action="ajaxDoAddSubTypesList"
											includeParams="all" escapeAmp="false" namespace="/exam">
											<s:param name="subType.id">0</s:param>
										</s:url>
										<sj:a href="%{doAddNewSubTypes}" indicator="indicator"
											targets="examContentInfo" button="false">Add Exam Subtype</sj:a>
									</li>
									<li>
										<s:url id="doChgSubtypesOrder"
											action="ajaxChangeSubTypesOrder" escapeAmp="false" namespace="/exam">
										</s:url>
										<sj:a href="%{doChgSubtypesOrder}" indicator="indicator"
											targets="examContentInfo" button="false">Change Subtypes Order</sj:a>
									</li>
								</s:if>
							</ul>
						</li>
				</ul> 
				<div id="examContentInfo" class="tab-content">
					<jsp:include page="/WEB-INF/pages/exam/ajaxViewExamTypesAndGrades.jsp"></jsp:include>
				</div>
			</div>
		</div>
	</div>
</div>
</div>
<script type="text/javascript">
$(document).ready(function() {
		changePageTitle("Grades & Exam Types");
		 $('.js-activated').dropdownHover().dropdown();
		$('.blockHeader h2').html('Manage Academics');
		// alert(getUrlVars()["id"]);
	});
$('html, body').animate({ scrollTop: $(document).height() - $(window).height() }, 10, function() {
	    $(this).animate({ scrollTop: 0 }, 10);
	});
</script>
