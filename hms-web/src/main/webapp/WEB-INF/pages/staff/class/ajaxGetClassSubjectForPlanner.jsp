<%@ include file="/common/taglibs.jsp"%>
<s:if test="%{(tempList1 != null && !tempList1.isEmpty()) || (tempList2 != null && !tempList2.isEmpty())}">
<s:if test='%{anyTitle == "ViewPlanner"}'>
	<s:if test='%{tempList1.size >0}'>
		<div class="form-group">
			<label class="col-md-3 control-label" style="padding-left:0px;margin-left:0px;">
				<span class="required">*</span> Class With Out Subject Planner :
			</label>
				<div class="radio-list col-md-9">
				 <s:radio list="tempList1" name="staffSyllabusPlanner.studySubjectId" listKey="studySubjectId" disabled="true" 
				 	id="selectedSectionName" listValue="subjectName" theme="ems"></s:radio>
				</div>
		</div>
	</s:if>
	<s:if test='%{tempList2.size >0}'>
		<div class="form-group">
			<label class="col-md-3 control-label" style="padding-left:0px;margin-left:0px;">
			<span class="required">*</span>Class With Subject Planner :
			</label>
			<div class="radio-list col-md-9">
			 <s:radio list="tempList2" name="staffSyllabusPlanner.studySubjectId" listKey="studySubjectId" 
			 	id="selectedSectionName" listValue="subjectName" onclick="javascript:getSubjectPlannerDetails(this.value);" 
				 theme="ems" cssClass="required form-control input-medium classSelectedSubject"></s:radio>
			</div>
		</div>
	</s:if>
	<s:else>
		<div class="alert alert-info">
			Currently there are no subjects planner created for this class
		</div>	
	</s:else>
</s:if>
<s:else>
	<div class="form-group">
		<label class="control-label col-md-4">
			<span class="required">*</span>Select Subject :
		</label>
		<div class="col-md-5">
			<s:select list="tempList1" id="studySubjectId"
				name="staffSyllabusPlanner.studySubjectId" listKey="studySubjectId"
				listValue="studySubject.name" headerKey="" 
				headerValue="- Select Subject -" theme="simple"
				cssClass="required form-control input-medium">
			</s:select>
		</div>
	</div>
</s:else>
</s:if>
<s:else>
<div class="alert alert-info">
	Currently there are no subjects planner created for this class
</div>	
</s:else>
<script type="text/javascript">
 $(document).ready(function() {
 var subjectId ='<s:property value="quizId"/>';
	$("input:checkbox, input:radio:not('.toggle')").uniform();
});
</script>