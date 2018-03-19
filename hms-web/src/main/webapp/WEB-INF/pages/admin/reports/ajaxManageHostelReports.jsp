<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>
<div class="block_head" id="topMenu">
	<h2>
		Hostel Reports
	</h2>
</div>
<div class="block_content" >
<div class="grid_12">
 <jsp:include page="/WEB-INF/pages/admin/reports/ajaxHostelReports.jsp"></jsp:include>
</div>
</div>
