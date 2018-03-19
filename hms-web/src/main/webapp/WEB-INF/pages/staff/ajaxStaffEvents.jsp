<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/ems.js"></script>
<div class="block grid_14 omega" id="eventsResults">
	<jsp:include page="viewStaffEventsLists.jsp"></jsp:include>
</div>
<script type="text/javascript">
changePageTitle("Events");
</script>