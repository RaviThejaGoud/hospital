<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>
<div class="grid_13 commomnTabs">
	&nbsp;<%@ include file="/common/messages.jsp"%>
	<s:form id="selectStaffLoanForm3"  theme="css_xhtml" >
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
		$("#staffLoanSettings").hide();
	} else {
		var pars = "tempString=" + staffRole;
		$("#staffLoanSettings")
				.html(
						'<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		var url = jQuery.url.getChatURL("/staff/ajaxDoGetStaffByRoleForLoan.do");
		$.ajax( {
			url : url,
			cache : false,
			data : pars,
			success : function(html) {
				$("#staffLoanSettings").html(html);
				$("#staffLoanSettings").show();
			}
		});
	}
}
</script>
