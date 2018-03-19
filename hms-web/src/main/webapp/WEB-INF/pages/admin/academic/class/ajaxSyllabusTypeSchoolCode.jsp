<%@ include file="/common/taglibs.jsp"%>
	
	<div class="form-group">
		<label class="col-md-3 control-label" id="syllabusTypeListLabelId">
			Select School Code :
		</label>
		<div class="col-md-5">
			<s:select list="tempList1" listKey="id" listValue="schoolCode"
				cssClass="form-control input-medium"
				name="studyClass.syllabusTypeSchoolCode.id" headerKey="" headerValue="- Select -" />
		</div>
	</div>
		