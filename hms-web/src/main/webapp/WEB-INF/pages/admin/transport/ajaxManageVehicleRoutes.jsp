<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Manage Vehicle Routes
				</div>
			</div>
			<div class="portlet-body">
				<div class="tabbable tabbable-custom tabbable-full-width">
					<ul class="nav nav-tabs">
						<s:if test='%{(#session.previousYear=="N")}'>
							<li>
								<s:url id="doAddVehicles" action="ajaxDoAssignVehicle" includeParams="all" escapeAmp="false" namespace="/admin">
									<s:param name="anyVehicleType" value="%{anyVehicleType}"></s:param>
								</s:url>
								<sj:a href="%{doAddVehicles}" targets="transportVehicles" buttonIcon="ui-icon-plus" data-toggle="tab">
									Assign Vehicle to Route
								</sj:a>
							</li>
							<li class="active">
								<s:url id="doManageVehicleRoutes" action="ajaxManageVehicleRoutes" namespace="/admin" />
								<sj:a  href="%{doManageVehicleRoutes}" targets="mainContentDiv" data-toggle="tab">
									 View Vehicle Routes
								</sj:a>
							</li>
						</s:if>
					</ul>
					<div class="tab-content" id="transportVehicles">
						<jsp:include
							page="/WEB-INF/pages/admin/transport/ajaxViewManageVehicleRouteDetails.jsp" />
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
changePageTitle('Manage Vehicle Routes');
</script>