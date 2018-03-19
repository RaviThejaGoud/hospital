<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>
<div class="grid_13 commomnTabs">
	&nbsp;<%@ include file="/common/messages.jsp"%>
	<s:form action="ajaxGeneratePayroll" theme="css_xhtml" id="generatePayroll" method="post" namespace="/admin">
	
		<div class="tableactions grid_12">
			<div class="grid_2 alpha">
				<label class="right">
					Select Role:
				</label>
			</div>
			<s:if test="%{ staffRoles != null && !staffRoles.isEmpty()}">
				<s:select list="staffRoles" id="className" name="staffRoleName" headerKey="all" headerValue="All" 
					theme="simple">
				</s:select>
			</s:if>
		</div>
		<div class="grid_12" >
				<s:submit type="submit small" value="Generate Payroll" cssClass="submit long" title="generate Payroll" cssStyle="cursor:pointer"></s:submit>
		</div>
	</s:form>
</div>

<!--<div class="block_content" id="payrollTypesSettings">
		<jsp:include page="/WEB-INF/pages/admin/payroll/ajaxGetPayrollSettingsForStaff.jsp"></jsp:include>
	</div>
-->
<script type="text/javascript">
function getStaffByRole(staffRole) {
	if (staffRole == "") {
		$("#staffsContent").hide();
	} else {
		var pars = "tempString=" + staffRole;
		$("#staffsContent")
				.html(
						'<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		var url = jQuery.url.getChatURL("/staff/ajaxDoGetStaffByRoleForPayroll.do");
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