<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>
<div class="block grid_12">
	<div class="block_head">
		<h2>
		Caste Details
		</h2>
		<div id="topMenu">
			<ul>
				<li>
					<s:url id="addCastLink" action="ajaxDoAddCastDetails"  namespace="/admin"/>
					<sj:a href="%{addCastLink}" targets="admissionContentDetails" indicator="indicator">Add Caste Details</sj:a>
				</li>

			</ul>
		</div>
	</div>
	<div class="block_content" id="academicsContent">
		<jsp:include page="/WEB-INF/pages/admin/admission/ajaxRejectedApplication.jsp"></jsp:include>
	</div>
</div>
<script type="text/javascript">
changePageTitle('Waiting List Applications');
	$.subscribe('doInitClassDetails', function(event, data) {
		if ($('#' + data.id).is(":hidden")) {
			$('#' + data.id).show();
		} else {
			$('#' + data.id).hide();
		}
	});
</script>
