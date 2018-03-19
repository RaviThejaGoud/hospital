<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>
<div class="grid_13 commomnTabs">
	&nbsp;<%@ include file="/common/messages.jsp"%>
	<s:form id="selectStudentForm"  theme="css_xhtml" >
	<div class="tableactions grid_12">
		<div class="grid_2 alpha">
			<label class="right">
				Select Role:
			</label>
		</div>
		<s:if test="%{ staffRoles != null && !staffRoles.isEmpty()}">
			<s:select list="staffRoles" id="className" name="staffRoleName"
				theme="simple" onchange="javascript:getStaffByRole(this.value);">
			</s:select>
		</s:if>
	</div>
	</s:form>
</div>
<script type="text/javascript">
function getStaffByRole(staffRole) {
	if (staffRole == "") {
		$("#payrollTypesSettings").hide();
	} else {
		var pars = "tempString=" + staffRole;
		$("#payrollTypesSettings")
				.html(
						'<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		var url = jQuery.url.getChatURL("/staff/ajaxGetStaffByRoleForPayrollSettings.do");
		$.ajax( {
			url : url,
			cache : false,
			data : pars,
			success : function(html) {
				$("#payrollTypesSettings").html(html);
				$("#payrollTypesSettings").show();
			}
		});
	}
}
</script>
