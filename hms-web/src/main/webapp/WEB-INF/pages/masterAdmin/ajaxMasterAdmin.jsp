<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>
<div class="tab_content" id="customerDetails">
	<jsp:include
		page="/WEB-INF/pages/masterAdmin/ajaxCustomerDetails.jsp"></jsp:include>
</div>
<script type="text/javascript">
	$(document).ready(function(){
		$('#masterAdminHome').addClass('current');
   		changePageTitle("Customer Details");
	});
</script>