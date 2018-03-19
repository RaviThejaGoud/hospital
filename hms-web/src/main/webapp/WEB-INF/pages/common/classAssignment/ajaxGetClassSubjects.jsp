<%@ include file="/common/taglibs.jsp"%>
<s:if
	test='%{user.isOnlySchoolAdmin == "Y" || user.isSchoolPrincipal=="Y" || user.isSchoolDirector == "Y"}'>
	<span id="tempListSize"
		class='<s:property value="classAssignment.id"/>'
		style="display: none;"></span>
	<s:if test="%{tempList1!=null && !tempList1.isEmpty()}">
		<div class="form-group">
			<label class="col-md-3 control-label"> <span class="required">*</span>Select
				Class :
			</label>
			<div class="col-md-9">
				<s:checkboxlist name="chkBoxSelectedIds" list="tempList1"
					id="classNameClass" listKey="id" listValue="classAndSection"
					theme="ems" />

			</div>
		</div>
	</s:if>
	<s:else>
		<div class="alert alert-info">Currently no class assigned to above subject.</div>
	</s:else>
</s:if>
<s:else>
	<s:if test="%{tempList!=null && !tempList.isEmpty()}">
		<div class="form-group">
			<label class="col-md-3 control-label"> <span class="required">*</span>Select
				Class :
			</label>
			<div class="col-md-9">
				<s:checkboxlist name="chkBoxSelectedIds" list="tempList"
					id="classNameClass" listKey="studyClassId" listValue="classSection"
					theme="ems" />

			</div>
		</div>
	</s:if>
	<s:else>
		<div class="alert alert-info">Currently no class assigned to above subject.</div>
	</s:else>
</s:else>
<script language="JavaScript" type="text/javascript">
$(document).ready(function() {
	var classCunt = '<s:property value="tempList1.size"/>';
	var clsCunt = '<s:property value="tempList.size"/>';
	if(classCunt == 0 && clsCunt == 0){
		$("div#uploadAssignmentDiv").hide();
	}else{
		$("div#uploadAssignmentDiv").show();
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
