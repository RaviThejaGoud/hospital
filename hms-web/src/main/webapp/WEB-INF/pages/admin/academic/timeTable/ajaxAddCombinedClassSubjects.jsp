<%@ include file="/common/taglibs.jsp"%>
<div class="form-body ">
	<jsp:include page="/common/messages.jsp" />
	<span id='<s:property value="classSectionId"/>' class='classSectionsSpan'></span>
	<span id='<s:property value="tempString"/>' class='staffIdSpan'></span>
	<s:if test="%{studySubjectList != null && !studySubjectList.isEmpty()}">
		<s:form action="ajaxSaveCombinedClassDetails" theme="simple" id="saveOrUpdateCombinedClassSubj" method="post" cssClass="form-horizontal" namespace="/admin">
			<s:hidden name="tempId" value="%{tempId}"></s:hidden>
			<s:hidden name="anyTitle" id="selectedClassSectionIds"></s:hidden>
			<s:hidden name="anyId" id="selectedTeacherIds"></s:hidden>
			<s:if test="%{tempId > 0}">
				<h4 class="pageTitle bold"> Update Combined Class Details</h4>
			</s:if>
			<s:else>
			<h4 class="pageTitle bold"> Create Combined Class Details </h4>
			</s:else>
			<div class="form-group">
				<label class="control-label col-md-2"><span class="required">*</span>Select Subject :</label>
				<div class="col-md-3">
				<s:select list="studySubjectList"  listKey="id" listValue="name" 
				cssClass="required form-control"  id="subjectId" name="subjectId"
					 headerKey="" headerValue="- Select Subject -"
					onchange="javascript:getSubjectClasses(this.value);"></s:select>
				</div>
			</div>
		 <div  id="combinedClassesCont"></div>
		 <div class="spaceDiv"></div>
			<div class="form-actions fluid">
				<div class="col-md-offset-2 col-md-9">
					<sj:submit   cssClass="submitBt btn blue" value="Submit" validate="true" onBeforeTopics="combinedClassSubjFormValidation"
							 targets="mainContentDiv" formIds="saveOrUpdateCombinedClassSubj" indicator="indicator"/>
	
					<s:url id="doViewCombinedClassDet" action="ajaxViewCombinedClassSubjects" includeParams="all" escapeAmp="false" namespace="/admin"> </s:url>
						<sj:a href="%{doViewCombinedClassDet}" cssClass="btn default" targets="mainContentDiv">Cancel</sj:a>
				</div>
			</div>
		</s:form>
	</s:if>
	<s:else>
		<div class="alert alert-info">
			No subjects found.
		</div>
	</s:else>
</div>
<script type="text/javascript">
$(document).ready(function() {
	$.destroyTopic('combinedClassSubjFormValidation');
 	var subjectId = $('#subjectId').val();
	if(isNonEmpty(subjectId))
		getSubjectClasses(subjectId);
		
	$.subscribe('combinedClassSubjFormValidation', function(event, data) {
					if (!isNonEmpty($('#subjectId').val())) {
						alert('Please select subject');
						event.originalEvent.options.submit=false;
					}
					else{
					  if($("input[name=chkBoxSelectedIds]:checked").length <= 0){
							alert('Please select classes.');
							event.originalEvent.options.submit=false;
						}
					}
					if($("input[name=chkBoxSelectedIds]:checked").length > 0){
						var classIds = $("input[name=chkBoxSelectedIds]:checked");
						var selectedClassIds = '(';
						for ( var i = 0; i < classIds.length; i++) {
							classSectionId = classIds[i].value.split('_')[0];
							selectedClassIds += classSectionId + ',';
						}
						selectedClassIds += '0)';
						$('#selectedClassSectionIds').val(selectedClassIds);
					}else
						$('#selectedClassSectionIds').val('(0)');
					
					if($("input[name=selectBoxIdList]:checked").length > 0){
						var staffIds = $("input[name=selectBoxIdList]:checked");
						var selectedTeacherId = '(';
						for ( var i = 0; i < staffIds.length; i++) {
							selectedTeacherId += (staffIds[i].value + ',');
						}
						selectedTeacherId += '0)';
						$('#selectedTeacherIds').val(selectedTeacherId);
					}else
						$('#selectedTeacherIds').val('(0)'); 
			});
	
});
function getSubjectClasses(subjectId){
	if(isNonEmpty(subjectId)){
		//var classesId = $('span.classesSpan').attr('id');
		var classesSectionId = $('span.classSectionsSpan').attr('id');
		var staffId = $('span.staffIdSpan').attr('id');
		var reqUrl = '';
		if(isNonEmpty(classesSectionId) && isNonEmpty(staffId))
			reqUrl = jQuery.url.getChatURL("/admin/ajaxGetAssignedClassSectionsBySubjectId.do?subjectId="+subjectId+"&classSectionId="+classesSectionId+"&tempString="+staffId);
		else
			reqUrl = jQuery.url.getChatURL("/admin/ajaxGetAssignedClassSectionsBySubjectId.do?subjectId="+subjectId);
		$('#combinedClassesCont').html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		$.ajax( {
			url : reqUrl,
			cache : true,
			success : function(response) {
				$('#combinedClassesCont').html(response);
			}
		});
	}else
		$('#combinedClassesCont').html('<div class="alert alert-info">Please select subject.</div>');
}
</script>