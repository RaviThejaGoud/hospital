<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/ems.js"></script>
<jsp:include page="/common/messages.jsp"></jsp:include>
<div class="grid_14">
	<div class="block_head">
			<h2>
				Generate Payroll
			</h2>
			<div id="topMenu" >
				<ul>
					<li>
						<s:url id="urlViewRecentGeneratedPayroll" action="ajaxDoViewRecentGeneratedPayroll" includeParams="all" namespace="/admin">
						</s:url>
						<sj:a href="%{urlViewRecentGeneratedPayroll}" targets="payrollGeneration">View Recent Generated Payroll</sj:a>
					</li>
					<li>
						<s:url id="urlViewOldGeneratedPayroll" action="ajaxDoViewOldGeneratedPayroll" includeParams="all" namespace="/admin">
						</s:url>
						<sj:a href="%{urlViewOldGeneratedPayroll}" targets="payrollGeneration">View Old Generated Payroll</sj:a>
					</li>
				</ul>
			</div>
		</div>
	<div class="block_content" id="payrollGeneration">
		<jsp:include page="/WEB-INF/pages/admin/payroll/ajaxDoGeneratePayroll.jsp"></jsp:include>
	</div>
</div>
<script type="text/javascript">
	changePageTitle("Generate  Payroll");
</script>