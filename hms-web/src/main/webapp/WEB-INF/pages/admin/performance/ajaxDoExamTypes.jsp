<%@ include file="/common/taglibs.jsp"%>
<div class="row">
<s:if test="%{examTypeList != null && !examTypeList.isEmpty() }">
	<div class="form-group" id ="examTypeListDiv">
		<label class="col-md-2 control-label">
			<span class="required">*</span>ExamType :
		</label>
		<div class="col-md-9">
			<div class="checkbox-list">
			<label class="checkbox-inline">
				<input type="checkbox" name="" value=""
						onClick="checkAllExamTypes()" class="checkbox allExamTypes">
					All Exam Types
				</label>
			</div>
			<s:checkboxlist name="examTypesChkBoxes" list="examTypeList"
				listKey="id" listValue="examType" theme="ems"  />
		</div>
	</div>
</s:if>
<s:elseif test="%{studyClassSubjectsList != null && !studyClassSubjectsList.isEmpty()}">
	<div class="form-group" id="subjectListDiv">
		<label class="col-md-2 control-label">
			<span class="required">*</span>Study Subject :
		</label>
		<div class="col-md-9">
			<div class="checkbox-list">
			<label class="checkbox-inline">
				<input type="checkbox" name="" value=""
						onClick="selectAllSubjects()" class="checkbox allSubjects">
					All Subjects
				</label>
			</div>
			<s:checkboxlist name="subjectChkBoxes" list="studyClassSubjectsList"
				listKey="subjectId" listValue="subjectName" theme="ems" />
		</div>
	</div>
</s:elseif>
<s:else>
    <div class="alert alert-info">
			Currently no exam was completed for selected class.
		</div>	
</s:else>
</div>
