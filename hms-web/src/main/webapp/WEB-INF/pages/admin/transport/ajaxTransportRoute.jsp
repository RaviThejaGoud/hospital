<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Manage Routes
				</div>
			</div>
			<div class="portlet-body">
				<div class="tabbable tabbable-custom tabbable-full-width">
					<ul class="nav nav-tabs">
						<s:if test='%{(#session.previousYear=="N")}'>
							<li>
								<s:url id="doAddRoutes" action="ajaxDoAddRoutes"
									includeParams="all" escapeAmp="false" namespace="/admin">
								</s:url>
								<sj:a href="%{doAddRoutes}" targets="routeContent" data-toggle="tab">
									Add Route
								</sj:a>
							</li>
							<li class="active" id="doAddRoutes">
								<s:url id="urlManageRoute" action="ajaxManageRoutes"
									namespace="/admin" />
								<sj:a href="%{urlManageRoute}" targets="mainContentDiv"
									data-toggle="tab">
								 View Routes</sj:a>
							</li>
						</s:if>
					</ul>
					<div class="tab-content" id="routeContent">
						<jsp:include
							page="/WEB-INF/pages/admin/transport/ajaxRouteDetails.jsp" />
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function() {
	changePageTitle('Manage Vehicle Maintenance');
});
$('html, body').animate({ scrollTop: $(document).height() - $(window).height() }, 10, function() {
	    $(this).animate({ scrollTop: 0 }, 10);
	});
</script>
