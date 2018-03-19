<%@ include file="/common/taglibs.jsp"%>
<div class="col-md-12">
	<jsp:include page="/WEB-INF/pages/staff/staffPerformance.jsp" />
</div>
<div id="moreDetailsDiv">
	<a id="staffViewPerformance"
		href="${pageContext.request.contextPath}/staff/staffSubjectsPerformance.do"
		class="btn green btn-xs">More Details</a>
</div>
 