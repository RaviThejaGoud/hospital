<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>
					Student Transport Request Form
				</div>
			</div>
			<div class="portlet-body">
				<div class="tabbable tabbable-custom tabbable-full-width" id="settingsDiv">
					<ul class="nav nav-tabs">
						<li>
							<s:url id="urlDoTransportApplicationSettings" action="ajaxTransportApplicationSettings" namespace="/admin"> </s:url>
							<sj:a id="transportRequestSettings" href="%{urlDoTransportApplicationSettings}"
							targets="transportApplicationDiv" indicator="indicator" data-toggle="tab">Request Form Settings</sj:a>
						</li>
						<li class="active">
							<s:url id="urlDowloadApplications" action="ajaxStudentTransportApplication" namespace="/admin"> </s:url>
							<sj:a id="urlDowloadApplicationDetails" href="%{urlDowloadApplications}"
							targets="mainContentDiv" indicator="indicator" data-toggle="tab">Download Transport Request Form</sj:a>
						</li>
					</ul>
					<div class="tab-content" id="transportApplicationDiv">
						<jsp:include
							page="/WEB-INF/pages/admin/transport/ajaxStudentTransportRequestForm.jsp" />
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>
<script type="text/javascript">
$(document).ready(function() {
changePageTitle('Download Student Transport Application');
});
</script>