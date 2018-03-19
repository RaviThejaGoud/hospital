<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/ems.js"></script>
<jsp:include page="/common/messages.jsp"></jsp:include>
<div class="grid_14">
	<div class="block_head">
			<h2>
				Manage Payroll Types
			</h2>
			<div id="topMenu" >
				<ul>
					<li>
						<s:url id="urlViewExamRooms" action="ajaxDoCreatePayrollTypes" includeParams="all" namespace="/admin">
						</s:url>
						<sj:a href="%{urlViewExamRooms}" targets="payrollTypesSettings">Create Payroll Types</sj:a>
					</li>
					<!--<li>
						<s:url id="urlStudentRoomDetails" action="ajaxDoCreatePayrollSettings" includeParams="all">
						</s:url>
						<sj:a href="%{urlStudentRoomDetails}" targets="payrollTypesSettings">Create Payroll Settings</sj:a>
					</li>
				--></ul>
			</div>
		</div>
	<div class="block_content" id="payrollTypesSettings">
		<jsp:include page="/WEB-INF/pages/admin/payroll/ajaxGetPayrollTypes.jsp"></jsp:include>
	</div>
</div>
<script type="text/javascript">
	changePageTitle("Manage Payroll Types");
</script>