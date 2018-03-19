<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>TC Generation
				</div>
			</div>
			<div class="portlet-body">
				<div class="tabbable tabbable-custom tabbable-full-width">
					<ul class="nav nav-tabs">
						<li>
							<s:url id="urlDoTchistoryGeneration"
								action="ajaxDoGetTcHistoryGeneration" namespace="/admin">
							</s:url>
							<sj:a href="%{urlDoTchistoryGeneration}" targets="viewTcContent"
								data-toggle="tab">TC History</sj:a>
						</li>
						<s:if test='%{user.isSchoolClerk != "Y"}'>
							<li>
								<s:url id="urlAddClass" action="ajaxDoTCTemplateAndBookSettings"
									namespace="/admin">
									<s:param name="tempString">templateSettings</s:param>
								</s:url>
								<sj:a id="doTcSettings" href="%{urlAddClass}"
									targets="viewTcContent" data-toggle="tab">Upload Templates</sj:a>
							</li>
							<li>
								<s:url id="urlAddSection"
									action="ajaxDoTCTemplateAndBookSettings" namespace="/admin">
									<s:param name="tempString">bookSettings</s:param>
								</s:url>
								<sj:a id="addSection" href="%{urlAddSection}"
									targets="viewTcContent" data-toggle="tab">TC Book Settings</sj:a>
							</li>
						</s:if>

						<li class="active">
							<s:url id="urlDoTcGeneration" action="ajaxDoGetTcGeneration"
								includeParams="all" escapeAmp="false" namespace="/admin">
								<s:param name="tempString">transferCertificate</s:param>
							</s:url>
							<sj:a href="%{urlDoTcGeneration}" targets="mainContentDiv"
								data-toggle="tab">Generate Certificate</sj:a>
						</li>
					</ul>
					<div class="tab-content" id="viewTcContent">
						<jsp:include
							page="/WEB-INF/pages/admin/academic/tcGeneration/ajaxDoTcGeneration.jsp" />
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function() {
	changePageTitle('Manage TC Settings');
});
</script>