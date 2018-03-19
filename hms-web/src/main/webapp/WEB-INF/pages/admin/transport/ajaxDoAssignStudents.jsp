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
				<div class="tab-content" id="transportVehicles">
					<s:if test='%{anyTitle=="Y"}'>
							<jsp:include
								page="/WEB-INF/pages/admin/transport/ajaxAssignStudentsVehicle.jsp" />
					</s:if>
					<s:if test='%{anyTitle=="N"}'>
						<div class="alert alert-info">
							You have not assigned vehicles to routes. Please assign vehicles
							to routes.
						</div>
					</s:if>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
changePageTitle('Assign Students to Vehicles');
</script>
