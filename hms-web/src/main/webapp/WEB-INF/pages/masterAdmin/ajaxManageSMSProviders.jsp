<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>SMS Providers Details
				</div>
			</div>
			<div class="portlet-body">
				<div class="tabbable tabbable-custom tabbable-full-width">
					<ul class="nav nav-tabs">
						<li>
							<s:url id="urlCreateSMSUrl" action="ajaxCreateSMSProvider"
								escapeAmp="false" namespace="/masterAdmin">
								<s:param name="smsServiceProvider.id" value="0"></s:param>
							</s:url>
							<sj:a id="createBranchLink" href="%{urlCreateSMSUrl}"
								targets="smsProvidersCont" data-toggle="tab"
								indicator="indicator">Create SMS Provider</sj:a>
						</li>
						<li class="active" id="smsProviderCountList">
							<s:url id="viewSMS" action="ajaxManageSMSProviders" namespace="/masterAdmin">
							</s:url>
							<sj:a id="viewSMS" href="%{viewSMS}" targets="mainContentDiv" data-toggle="tab">View</sj:a> 	
						 </li>
					</ul>
					<div class="tab-content" id="smsProvidersCont">
						<jsp:include
							page="/WEB-INF/pages/masterAdmin/ajaxSMSProvidersDetails.jsp"></jsp:include>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
$('#masterAdminHome').addClass('current');
changePageTitle("SMS Providers Details");
</script>