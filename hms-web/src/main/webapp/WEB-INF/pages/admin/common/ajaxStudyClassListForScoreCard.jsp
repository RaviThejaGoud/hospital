<%@ include file="/common/taglibs.jsp"%>
<s:if test='%{studyClassList != null && !studyClassList.isEmpty()}'>
<div class="form-group">
		<label class="control-label col-md-4" style="width:38.333%;">
			Select Class :
		</label>
		<div class="col-md-4">
			<s:select list="studyClassList"
				listKey="classNameAndSectionWithClassSectionId" onchange="javascript:getClassStudentDetails(this.value);"
				listValue="classAndSection" id="selectedClassName" name="tempString"
				headerKey="" headerValue="- Select -"
				cssClass="form-control input-medium">
			</s:select>
		</div>
</div>
</s:if>
<s:else>
	<div class="alert alert-info">
		You have not upload marks for any class above examtype.
	</div>
</s:else>
