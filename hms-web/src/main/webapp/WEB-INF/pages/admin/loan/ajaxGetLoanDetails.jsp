<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/common/jquery.alphanumeric.js">
</script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/subscription/textcounter.js">
</script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>
<%@ include file="/common/taglibs.jsp"%>
<jsp:include page="/WEB-INF/pages/admin/loan/ajaxViewStaffList.jsp"></jsp:include>
<div id="commonStep13">
	<fieldset id="stepAttendance" >
		<div class="grid_14">
			<div id="loanEmiId" class="grid_13">
	<s:if test="%{payrollTypesList!= null && !payrollTypesList.isEmpty()}">
		<div class="grid_12 th">
				<div class="grid_3">
					Name
				</div>
				<div class="grid_3">
					Description
				</div>
				<div class="grid_2">
					Type
				</div>
				
				
         </div>
         <s:iterator value="payrollTypesList">
         <div class="grid_12 row">
					<div class="grid_3" >
						<s:property value="payrollName" />
					</div>
					<div class="grid_3" >
						<s:property value="payrollDescription" />
					</div>
					<div class="grid_2">
						<s:property value="payrollTypeDesc" />
					</div>
		</div>
		</s:iterator>
	</s:if>
	<s:else>
		<div class="examTabBorder">
				 Loan details not found for this Staff.
		</div>
	</s:else>
</div>
</div>
</fieldset>
</div>



<script type="text/javascript">
	changePageTitle('Get Loan Details');
</script>