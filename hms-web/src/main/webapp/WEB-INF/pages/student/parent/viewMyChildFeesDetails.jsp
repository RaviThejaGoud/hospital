<%@ include file="/common/taglibs.jsp"%>
	<div class="wrapper container_18">
		<!-- wrapper begins -->
		<div class="wrapper">
			<div class="grid_18 block grid_18MarginLeft">
				<jsp:include page="/WEB-INF/pages/student/class/studentLeftNav.jsp" />
					<div id="studentContent">
					<jsp:include page="/WEB-INF/pages/student/parent/ajaxStudentFeeDetails.jsp"></jsp:include>
				</div>
			</div>
		</div>
	</div>
	<script Language="Javascript1.2" type="text/javascript">
	$(document).ready(function() {
		changePageTitle("Student Activities");
		$('#studentActivities').addClass('current');
		$('ul.activities > li').removeClass('active');
		$('#studentPayments').addClass('active');
	 });
	 
</script>