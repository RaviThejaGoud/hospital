<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/ems.js"></script>
<div class="grid_12">
	<div class="block_head">
		<h2>
			Manage Package Details
		</h2>
	</div>
	<div class="block_content" id="schoolInformationContent">
		<jsp:include page="/WEB-INF/pages/admin/ajaxSchoolPackageDetails.jsp"></jsp:include>
	</div>
</div>
<script type="text/javascript">
	$(document).ready(function() {
				changePageTitle('Manage Package Details');
				$.subscribe('doEditPackageDetails', function(event, data) {
						if ($('#' + data.id).is(":hidden")) {
							$('#' + data.id).show();
						} else {
							$('#' + data.id).hide();
						}
				});
				/*$.subscribe('schoolInfoFormValidation', function(event, data) {
					if ($('#addOrganization').valid())
						return true;
					else
						return false;
					});*/
				});
</script>
