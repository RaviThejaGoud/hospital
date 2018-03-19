<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
				<i class="fa fa-globe"></i>
					Question Bank
				</div>
			</div>
			<div class="portlet-body">
				<div class="tabbable tabbable-custom tabbable-full-width">
					<ul class="nav nav-tabs">
						<s:if test='%{#session.previousYear == "N"}'>
						<s:if test='%{user.isSchoolStudent!="Y"}'>
							 <li>
								<s:url id="urlAddQuestionBankMaterial" action="ajaxDoAddQuestionBankMaterial" namespace="/exam">
								</s:url>
								<sj:a id="addSub" href="%{urlAddQuestionBankMaterial}" targets="classContentDiv" data-toggle="tab">Add Question Bank</sj:a> 	
							 </li>
						 </s:if>
						 </s:if> 
						 <li class="active">
							<s:url id="urlviewQuestionBankMaterials" action="ajaxViewQuestionBankMaterialList" namespace="/exam">
							</s:url>
							<sj:a id="viewSub" href="%{urlviewQuestionBankMaterials}" targets="mainContentDiv" data-toggle="tab">View</sj:a> 	
						 </li>
					</ul>
					<div id="classContentDiv" class="tab-content">
					<%@ include file="/common/messages.jsp"%>
					<s:if test="%{studyClassList != null && !studyClassList.isEmpty()}">
					<s:form id="selectStudentForm" action="#" theme="simple" cssClass="form-horizontal">
						<div class="row">
							<div id="getSelectedClassStudents">
							<div class="col-md-6">
								<div class="form-group">
									<label class="control-label col-md-3">
										<span class="required"> * </span>Select Class :
									</label>
									<div class="col-md-3">
									<s:select list="studyClassList" id="className"
										name="classId" listKey="id" listValue="classAndSection" headerValue="- Select Class-"
										headerKey="" theme="simple"
										cssClass="required form-control input-medium"
										onchange="javascript:getDoGetStudyClasses(this.value);">
									</s:select>
									</div>
								</div>
							</div>
							</div>
							<div id="createClassAssignmentDiv"></div>
						</div>
						</s:form>
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
<script type="text/javascript">
changePageTitle("Manage Student");
$(document).ready(function() {
	var classId = $("#className option:first").val();
	if(isNonEmpty(classId)){
		getDoGetStudyClasses(classId);
	}
});
function getDoGetStudyClasses(classSectionId) {
	var typeId=1;
	var pars = "tempId1=" + typeId+"&classSectionNameId="+classSectionId;
	if(isNonEmpty(classSectionId)){		
	$("#createClassAssignmentDiv").html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
	  var url = jQuery.url.getChatURL("/exam/ajaxGetAllStudySubjects.do?");
	$.ajax( {
		url : url,
		cache : false,
		data : pars,
		success : function(html) {
			$("#createClassAssignmentDiv").html(html);
		}
	});
  }
  else{
   alert("Please select Class");
   return false;
  }
} 
</script>
