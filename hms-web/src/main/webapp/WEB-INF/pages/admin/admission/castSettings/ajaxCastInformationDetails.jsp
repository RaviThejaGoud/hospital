<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Manage Community & Caste
				</div>
			</div>
			<div class="portlet-body">
				<div class="tabbable tabbable-custom tabbable-full-width">
					<ul class="nav nav-tabs">
					<li>
							<s:url id="addCastLink" action="ajaxDoAddCastDetails"
								namespace="/admin" />
							<sj:a href="%{addCastLink}" targets="academicsContent"
								indicator="indicator" data-toggle="tab">Add Community</sj:a>
						</li>
						<li class="active">
							<s:url id="urlGetCastSettings" action="ajaxCastSettingsHome"
								namespace="/admin" />
							<sj:a href="%{urlGetCastSettings}" targets="mainContentDiv"
								data-toggle="tab">View Communities</sj:a>
						</li>
					</ul>
					<div class="tab-content" id="academicsContent">
						<jsp:include
							page="/WEB-INF/pages/admin/admission/castSettings/ajaxViewCastDetails.jsp"></jsp:include>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
changePageTitle('Community & Caste Settings');
$.subscribe('doInitClassDetails', function(event, data) {
	if ($('#' + data.id).is(":hidden")) {
		$('#' + data.id).show();
	} else {
		$('#' + data.id).hide();
	}
});
</script>
