<%@ include file="/common/taglibs.jsp"%>
<s:if
	test='%{user.isOnlySchoolAdmin == "Y" || user.isSchoolPrincipal=="Y" || user.isAdminCoordinator=="Y" || user.isSchoolDirector == "Y"}'>
		<s:if test="%{viewClassSubjectsSettings != null && !viewClassSubjectsSettings.isEmpty()}">
				<div class="form-group">
					<label class="control-label col-md-3"> <span
						class="required"> * </span>Select Subject :
					</label>
					<div class="col-md-5">
						<s:select list="viewClassSubjectsSettings" name="classAssignment.subjectId"
							listKey="subjectId" id="subjectId" listValue="subjectName" headerKey=""
							headerValue="- Select Subject -" theme="simple"
							cssClass="required form-control input-medium">
						</s:select>
					</div>
				</div>
			</s:if>
	<s:else>
		<div class="alert alert-info">Currently no subjects assigned to above class.</div>
	</s:else>
</s:if>
<s:else>
	<s:if test="%{tempList1 != null && !tempList1.isEmpty()}">
				<div class="form-group">
					<label class="control-label col-md-3"> <span
						class="required"> * </span>Select Subject :
					</label>
					<div class="col-md-5">
						<s:select list="tempList1" id="studySubjectId"
							name="classAssignment.subjectId" listKey="studySubjectId"
							listValue="studySubject.name" headerKey="" 
							headerValue="- Select Subject -" theme="simple"
							cssClass="required form-control input-medium">
						</s:select>
					</div>
				</div>
			</s:if>
	<s:else>
		<div class="alert alert-info">Currently no subjects assigned to above class.</div>
	</s:else>
</s:else>
<script language="JavaScript" type="text/javascript">
$(document).ready(function() {
	var classCunt = '<s:property value="viewClassSubjectsSettings.size"/>';
	//var clsCunt = '<s:property value="tempList.size"/>';
	if(classCunt == 0){
		$("div#uploadAssignmentDiv").hide();
	}else{
		$("div#uploadAssignmentDiv").show();
	}
	
	  
	var subjectList = '<s:property value="tempList1.size" />';
	if (subjectList == 0 && classCunt ==0) {
		$('#submitButton1').attr('disabled', true);
		$('#submitButton').attr('disabled', true);
	}
/*var classId=$("span#tempListSize").attr("class");
if(classId > 0){
	$('#uploadAssignmentDiv').show();	
}else{
	$('#uploadAssignmentDiv').hide();	
}*/
	FormComponents.init();
	$("input:checkbox, input:radio:not('.toggle')").uniform();
});
</script>
