<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/ems.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/common/jquery.autoCheck.js"></script>
<div class="block grid_14" id="transportContent">
	<div class="block_head">
		<h2>
			Assign Vehicles to Route 
		</h2>
		<ul>
			<s:if test='%{user.isOnlySchoolAdmin=="Y"}'>
				 <li>
				 	<a id="adminStaffAndStudent" href="${pageContext.request.contextPath}/admin/adminManageStaffAndStudent.do" class="current">Back To Admin</a>
				</li>
			</s:if>
		</ul>
	</div>
	<div class="block_content" id="transportVehicles">
		<jsp:include page="/WEB-INF/pages/admin/transport/ajaxAssignVehiclesToRoute.jsp"/>
	</div>
</div>
<script type="text/javascript">
   changePageTitle('Assign Students to Vehicles');
</script>
