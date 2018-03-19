<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/ems.js"></script>
<div class="grid_14">
	<div class="block_head">
			<h2>
				Staff Loan Details
			</h2>
			<div id="topMenu" >
				<ul>
					<li>
						<s:url id="urlViewLoandDetails" action="ajaxDoGetLoanForStaff" includeParams="all" namespace="/admin">
						</s:url>
						<sj:a href="%{urlViewLoandDetails}" targets="staffLoanSettings">Create Loan For Staff</sj:a>
					</li>
					<!--<li>
						<s:url id="urlStudentRoomDetails" action="ajaxDoCreatePayrollSettings" includeParams="all">
						</s:url>
						<sj:a href="%{urlStudentRoomDetails}" targets="payrollTypesSettings">Create Payroll Settings</sj:a>
					</li>
				--></ul>
			</div>
		</div>
		
	<div class="block_content" id="staffLoanSettings">
		<jsp:include page="/WEB-INF/pages/admin/loan/ajaxViewStaffList1.jsp"></jsp:include>
	</div>
</div>
<script type="text/javascript">
	changePageTitle("Manage Loan Details");
</script>