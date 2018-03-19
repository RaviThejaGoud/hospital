<%@ include file="/common/taglibs.jsp"%>
<%-- <s:if test="{selectboxMap != NULL && !selectboxMap.isEmpty()}">
		<div class="form-group">
			<label class="control-label col-md-2">
				Reports To :
			</label>
			<div class="col-md-3" id="hodsList">
				<s:select id="teachStaffList" list="selectboxMap" theme="simple"
					headerKey="" headerValue="- Select -" tabindex="21" cssClass="form-control"
					name="staff.supervisorId"/>
			</div>
	</div>
</s:if> --%>
<s:if test='%{tempString == "ROLE_HOD" || tempString == "ROLE_ADMIN_COORDINATOR"}'>
	<s:if
		test='%{studyClassList != null && !studyClassList.isEmpty()}'>
		<div class="form-group">
			<label class="control-label col-md-2">
				Assign Classes :
			</label>
			<div class="col-md-10">
				<s:checkboxlist name="tempList2" tabindex="29" theme="ems"
					cssClass="checkbox required form-control" list="studyClassList"
					listKey="id" listValue="classAndSection" />
			</div>
		</div>
	</s:if>
	<s:else>
		<div class="alert alert-info">
			Currently there are no study classes available.Please add study classes in
			ACADEMICS section.
		</div>
	</s:else>
</s:if>
<script language="JavaScript" type="text/javascript">
$("input:checkbox, input:radio:not('.toggle')").uniform();  
</script>

