<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Manage Vehicle Maintenance
				</div>
			</div>
			<div class="portlet-body">
				<div class="tabbable tabbable-custom tabbable-full-width">
					<ul class="nav nav-tabs">
						<s:if test='%{#session.previousYear == "N"}'>
							<li>
								<s:url id="doAddVehicles" action="ajaxDoAddVehicles"
									includeParams="all" escapeAmp="false" namespace="/admin">
									<s:param name="anyVehicleType" value="%{anyVehicleType}"></s:param>
								</s:url>
								<sj:a href="%{doAddVehicles}"
									onCompleteTopics="doInitEditVehicle" indicator="indicator"
									targets="transportVehicles" buttonIcon="ui-icon-plus" data-toggle="tab">
										Add Vehicle
								</sj:a>
							</li>
							<li class="active" id="doAddVehicles">
								<s:url id="doVehicleMaintenance"
									action="ajaxManageTransportVehicles" namespace="/admin" />
								<sj:a href="%{doVehicleMaintenance}" targets="mainContentDiv"
									data-toggle="tab">
								View Vehicles</sj:a>
							</li>
						</s:if>
					</ul>
					<div class="tab-content" id="transportVehicles">
						<jsp:include
							page="/WEB-INF/pages/admin/transport/ajaxViewTransportVehicleDetails.jsp" />
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
   changePageTitle('Manage Vehicle Maintenance');
   $('li#doAddVehicle').click(function(){
   $(this).addClass('active');
});
</script>
