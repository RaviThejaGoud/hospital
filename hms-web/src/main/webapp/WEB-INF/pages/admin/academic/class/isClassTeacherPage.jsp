<%@ include file="/common/taglibs.jsp"%>
<div class="form-group">
	<label class="col-md-3 control-label">
		Class Teacher :
	</label>
	<div class="col-md-6">
		<s:select list="birthDaysListSet" listKey="staffId"
			cssClass="form-control input-medium" listValue="PersonFullName"
			name="isClassTeacher" headerKey="" id="classTeacher" onchange="javascript:getIsAlreadyClassTeacher()"
			headerValue="- Select Class Teacher -" theme="simple" />
	</div>
</div>
<div id="isClassTeacherDiv"></div>