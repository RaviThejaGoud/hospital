<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Assign Students to Vehicles
				</div>
			</div>
			<div class="portlet-body">
				<div class="tabbable tabbable-custom tabbable-full-width">
					<jsp:include page="/WEB-INF/pages/admin/transport/ajaxDoAssignStudentsVehicleTermWise.jsp" />
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
changePageTitle('Assign Students to Vehicles');
</script>
