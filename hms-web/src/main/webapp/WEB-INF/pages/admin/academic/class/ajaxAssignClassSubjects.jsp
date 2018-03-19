<%@ include file="/common/taglibs.jsp"%>
<jsp:include page="/common/messages.jsp" />
<div class="form-body">
<s:if test="%{(tempString != null && !tempString.isEmpty())}">
	<s:form action="ajaxAssignClassSubjects" theme="simple"
		id="assignStaffClassSubjForm" cssClass="form-horizontal" namespace="/admin">
		<s:hidden name="tempId" value="%{tempId}" />
		<span class="assignStaffId" id='<s:property value="tempId"/>'></span>
		<%-- <div class="form-group">
			<label class="control-label col-md-2">
				<span class="required"> * </span>Staff Name :
			</label>
			<div class="col-md-3">
				<p class="form-control-static">
					<s:property value="tempString" />
				</p>
			</div>
		</div> --%>
		<div class="form-group">
			<label class="control-label col-md-2">
				<span class="required"> * </span>Select Class :
			</label>
			<div class="col-md-3">
				<s:select list="studyClassList" listKey="id"
					listValue="classAndSection"
					cssClass="required form-control input-medium" id="classId"
					name="classSectionId"
					onchange="javascript:getSubjectsByClassSectionId(this.value);">
				</s:select>
			</div>
		</div>
		<div id="asignedClassSubjCont">
		</div>
		<%-- <div class="form-actions fluid">
			<div class="col-md-offset-2 col-md-9">
				<sj:submit   targets="mainContentDiv" value="Submit" validate="true"
					cssClass="submitBt btn blue" formIds="assignStaffClassSubjForm"
					onBeforeTopics="assignStaffSubjectsForm" />
				<s:url id="doViewCancelAssignClassSubjForm"
					action="ajaxViewTeacherSubjects" escapeAmp="false"
					includeParams="all" namespace="/admin">
					<s:param name="tempId" value="%{tempId}"></s:param>
				</s:url>
				<sj:a href="%{doViewCancelAssignClassSubjForm}"
					cssClass="btn default" targets="mainContentDiv">Cancel</sj:a>
			</div>
		</div> --%>
	</s:form>
	</s:if>
	<s:else>
		<div class="alert alert-info">
			Currently there are no staff.
		</div>
	</s:else>
</div>
<script type="text/javascript">
$(document).ready(function() {
 $("input:checkbox, input:radio").uniform();
	var classSectionId = $('#classId').val();
	if(isNonEmpty(classSectionId))
		getSubjectsByClassSectionId(classSectionId);
		$.subscribe('assignStaffSubjectsForm',function(event, data) {
		if ($('#assignStaffClassSubjForm').valid()) {
			 if($("input[name='chkBoxSelectedIds']:checked").length == 0){
			 		alert("Please Select at least one subject.");
					event.originalEvent.options.submit=false;

			 }
			// return true;
		} else
			event.originalEvent.options.submit=false;
		});
	});
	function getSubjectsByClassSectionId(classSectionId) {
		var staffId = $('span.assignStaffId').attr('id');
	 	if(isNonEmpty(classSectionId) ){ ///* && isNonEmpty(staffId) */ comment for remove the staffId
			$('#asignedClassSubjCont').html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
			var pars = "classId="+classSectionId;		
			//var pars = "classSectionId="+classSectionId+"&tempId="+staffId;		
			$.ajax({
				url : jQuery.url.getChatURL("/admin/ajaxGetStaffClassSubjects.do"),
				cache : true,
				data : pars,
				success : function(response) {
					if(isNonEmpty(response)){
						$('#asignedClassSubjCont').html(response);				
					}else{
						$('#asignedClassSubjCont').html("");
					}
				}
			});
		}else{
			$('#asignedClassSubjCont').html("<div>Please select class.</div>");
		}
	}
</script>