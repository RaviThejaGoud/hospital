<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Manage Subject Planner
				</div>
			</div>
			<div class="portlet-body">
				<div class="tabbable tabbable-custom tabbable-full-width">
					<ul class="nav nav-tabs">
						<s:if 
							test='%{(user.isOnlySchoolTeacher=="Y" || user.isOnlySchoolHod=="Y" || user.isSchoolPrincipal=="Y" || user.isSchoolDirector == "Y") && (#session.previousYear == "N")}'>
							<s:if test="%{teacherList.size() > 0}">
								<li>
									<s:url id="urlAddSubPlanner" action="ajaxDoAddSubPlanner"
										includeParams="all" escapeAmp="false" namespace="/staff">
										<s:param name="tempId2" value="0"></s:param>
									</s:url>
									<sj:a  href="%{urlAddSubPlanner}" targets="staffSubPlannerList" data-toggle="tab" >Add Subject Planner</sj:a>
								</li>
							</s:if>
						</s:if>
						<li class="active">
							<s:url id="viewSubjectPlanner" action="ajaxViewSubPlannerDetails"
								namespace="/staff">
							</s:url>
							<sj:a href="%{viewSubjectPlanner}" targets="mainContentDiv"
								data-toggle="tab">View Subject Planner</sj:a>
						</li>
					</ul>
					<div id="staffSubPlannerList" class="tab-content">
						<s:if
							test="%{studyClassList != null && !studyClassList.isEmpty()}">
							<s:form id="selectStudentForm" action="#" theme="simple"
								cssClass="form-horizontal">
								<s:hidden name="staffSyllabusPlanner.id" id="plannerId" />
								<jsp:include page="/common/messages.jsp" />
								<div id="classSubjectDiv" style="display: none;">
								<div class="panel-body col-md-12">
									<div class="col-md-1">
										<span class="label label-danger"> NOTE : </span>
									</div>
									<div class="col-md-10">
										<ul>
											<li>
												Subject planner will be created by the staff.
											</li>
											<li>
												You can view the subject planner by selecting class and subject.
											</li>
										</ul>
									</div>
								</div>
								 <div class="spaceDiv"></div>
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-3">
													<span class="required">*</span>Select Class :
												</label>
												<div class="col-md-5">
													<s:select list="studyClassList" id="className"
														name="staffSyllabusPlanner.studyClassId" listKey="id"
														listValue="classAndSection" headerKey="" theme="simple"
														cssClass="form-control input-medium"
														onchange="javascript:getClassSubjects(this.value);">
													</s:select>
												</div>
											</div>
										</div>
									</div>
									<div id="subjectViewDiv" style="display: none"> </div>
								</div>
							</s:form>
							<div id="subjectPlannerViewDiv"> </div>
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
</div>
<span id="staffIdSelected" data-id='<s:property value="staff.id"/>'></span>
<script type="text/javascript">
changePageTitle("Manage Subject Planners");
var subjectId =0;
$(document).ready(function() {
	$('div#subjectViewDiv').hide();
	var plannerId = $("#plannerId").val();
	if (plannerId > 0) {
		$('div#classSubjectDiv').empty();
	} else {
		$('div#classSubjectDiv').show();
		$('div#subjectViewDiv').show();
	}
	var classId = $("select#className").val();
	if(isNonEmpty(classId)){
		getClassSubjects(classId);
	}
	subjectId ='<s:property value="quizId"/>';
});

function getClassSubjects(selectBox) {
	var organizationId = $("select#className").val();
	var value = "ViewPlanner";
	var url = jQuery.url.getChatURL("/staff/ajaxGetClassPlannerSubject.do");
	if (organizationId.length == 0) {
		alert("!Oops select Class");
	} else {
		$("#subjectViewDiv").html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		var pars = "student.categoryId=" + organizationId + "&anyTitle="+ value;
		$.ajax( {
					url : url,
					cache : false,
					data : pars,
					success : function(html) {
						$("#subjectViewDiv").html(html);
						$('div#subjectViewDiv').show();
						$('div#subjectPlannerViewDiv').hide();	
						if(isNonEmpty(subjectId) && subjectId!=0){
		                 getSubjectPlannerDetails(subjectId);
	                    }					
					}
				});
	}
}

function getSubjectPlannerDetails(studySubjectId) {
	var studyClassId = $("select#className").val();	
	var url = jQuery.url.getChatURL("/staff/ajaxSubjectPlannerViewDetails.do");
	if (studyClassId == 0) {
		alert("!Oops select Subject.");
	} else {
	$('div#subjectPlannerViewDiv').show();
		$("#subjectPlannerViewDiv") .html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		var pars = "tempId=" + studyClassId + "&tempId1=" + studySubjectId+"&selectedId="+ $('span#staffIdSelected').attr("data-id");
		$.ajax( {
					url : url,
					cache : false,
					data : pars,
					success : function(html) {
					    $('select.classSelectedSubject option[value='+studySubjectId+']').attr('selected','selected');
						$("#subjectPlannerViewDiv").html(html);
						$('div#subjectPlannerViewDiv').show();						
					}
				});
	}
}
</script>
