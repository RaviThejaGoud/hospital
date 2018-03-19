<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/validation/jquery.validate.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js"></script>
<div class="grid_14">
	<h1>
		Reports
	</h1>
	<div class="grid_13">Class & Sections</div>
	<div class="grid_13">Exam Types</div>
	<div class="grid_13">Exam Schedules</div>
	<div class="grid_13">Term Fees Particulars </div>
	<div class="grid_13">Term Fees by Class</div>
</div>
<script Language="Javascript1.2" type="text/javascript">
	$(document).ready(function() {
		changePageTitle("Exam Schedules");
		$('.blockHeader h2').html('Manage Exam Schedules');
	});
</script>