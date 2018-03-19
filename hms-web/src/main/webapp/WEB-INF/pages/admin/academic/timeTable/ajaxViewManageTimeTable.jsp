<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Combined Classes
				</div>
			</div>
			<div class="portlet-body">
				<s:if test="%{smsCnt > 0 && numberOfDays > 0}">
					<s:if test="%{studyClassList != null && !studyClassList.isEmpty()}">
						<div class="tabbable tabbable-custom tabbable-full-width">
							<ul class="nav nav-tabs">
							<li class="dropdown">
									<a data-hover="dropdown" data-toggle="dropdown"
										class="dropdown-toggle js-activated" href="#">Non Assigned
										Class Subjects <b class="caret"></b> </a>
									<ul role="menu" class="dropdown-menu pull-right">
										<li>
											<s:url id="urlGetNonAssignedSubjs"
												action="ajaxViewNonAssignedSubjects" namespace="/admin">
											</s:url>
											<sj:a id="showNonAssignedSubjects"
												href="%{urlGetNonAssignedSubjs}"
												targets="sclViewTimeTableCont" data-toggle="tab">Non Assigned Class Subjects</sj:a>
										</li>
										<s:if test="%{tempBoolean}">
											<li>
												<s:url id="urlAssignNonAssignClassSubj" namespace="/admin"
													action="ajaxGenerateTimeTableForNonAssignedClassSubjects">
												</s:url>
												<sj:a id="generateNonAssignedSubjects"
													href="%{urlAssignNonAssignClassSubj}"
													targets="sclViewTimeTableCont" cssClass="labelRight"
													indicator="indicator">
													<h5>
														Assign Non Assigned Class Subjects
													</h5>
												</sj:a>
										</s:if>
									</ul>
								</li>
								<li>
									<s:url id="urlGetStaffTimeTable" namespace="/admin"
										action="ajaxDoViewStaffTimeTable">
									</s:url>
									<sj:a id="showStaffTimeTable" href="%{urlGetStaffTimeTable}"
										targets="sclViewTimeTableCont" data-toggle="tab">Staff Timetable</sj:a>
								</li>
								<s:if test='%{#session.previousYear=="N"}'>
									<li>
										<s:url id="urlGenerateTimeTable" namespace="/admin"
											action="ajaxGenerateTimeTable">
										</s:url>
										<sj:a id="genetaingTimeTable" href="%{urlGenerateTimeTable}"
											targets="mainContentDiv" data-toggle="tab">Generate Timetable</sj:a>
									</li>
								</s:if>
								<li class="active">
									<s:url id="viewCombined" action="ajaxViewManageTimeTable"
										namespace="/admin">
									</s:url>
									<sj:a href="%{viewCombined}" targets="mainContentDiv"
										data-toggle="tab">Class wise Timetable</sj:a>
								</li>
							</ul>
							<div id="sclViewTimeTableCont" class="tab-content">
								<%@ include file="/common/messages.jsp"%>
								<div class="form-group form-horizontal">
									<label class="control-label col-md-2">
										<span class="required">*</span> Select Class :
									</label>
									<div class="col-md-5">
										<s:select list="studyClassList" listKey="id"
											listValue="classAndSection"
											cssClass="required form-control input-medium"
											id="classSectionId" theme="simple" name="classId"
											onchange="javascript:getClassTimeTable(this.value);">
										</s:select>
									</div>
								</div>
								<div class="spaceDiv"></div><div class="spaceDiv"></div><div class="spaceDiv"></div>
								<div id="timeTableContent">
								<jsp:include
									page="/WEB-INF/pages/admin/academic/timeTable/ajaxViewClasswiseTimetable.jsp"></jsp:include>
								</div>
							</div>
						</div>
					</s:if>
					<s:else>
						<div class="alert alert-info">
							Classes are not available.
						</div>	
					</s:else>
				</s:if>
				<s:else>
					<div class="alert alert-info">
						You have not created periods or subjects settings. Please create
						them for generating time table.
					</div>
				</s:else>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
$(document).ready(function() {
	var classSectionId = $('#classSectionId').val();
	if(isNonEmpty(classSectionId))
		getClassTimeTable(classSectionId);
});
 function getClassTimeTable(classId) {
 	if(isNonEmpty(classId)){
		$('#timeTableContent').html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		var pars = "classId=" + classId;					
		$.ajax( {
			url : jQuery.url.getChatURL("/common/ajaxGetClassWiseTimeTable.do"),
			cache : true,
			data : pars,
			success : function(response) {
				$('#timeTableContent').html(response);
			}
		});
	}else{
		$('#timeTableContent').html("<div class='col-md-12 th'>Time table not generated for this class.</div>");
	}
}
</script>
 