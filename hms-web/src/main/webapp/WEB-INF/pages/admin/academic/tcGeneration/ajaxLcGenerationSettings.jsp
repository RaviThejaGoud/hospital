<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>LC Generation
				</div>
			</div>
			<div class="portlet-body">
				<div class="tabbable tabbable-custom tabbable-full-width">
					<ul class="nav nav-tabs">

						<s:if test='%{user.isSchoolClerk != "Y"}'>
							<li>
								<s:url id="urllctemplate"
									action="ajaxDoLCTemplateAndBookSettings" includeParams="all"
									escapeAmp="false" namespace="/admin">
									<s:param name="tempString">LC</s:param>
								</s:url>
								<sj:a href="%{urllctemplate}" targets="viewLcContent"
									id="doLcSettings" data-toggle="tab">Upload Templates</sj:a>
							</li>
							<li>
								<s:url id="urlLcBookSettings" includeParams="all"
									escapeAmp="false" action="ajaxDoLCTemplateAndBookSettings"
									namespace="/admin">
									<s:param name="tempString">lcbookSettings</s:param>
								</s:url>
								<sj:a href="%{urlLcBookSettings}" targets="viewLcContent"
									data-toggle="tab">LC Book Settings</sj:a>
							</li>
						</s:if>
						<li class="active">
							<s:url id="urlDoLcGeneration" action="ajaxDoGetLcGeneration"
								includeParams="all" escapeAmp="false" namespace="/admin">
								<s:param name="tempString">transferCertificate</s:param>
							</s:url>
							<sj:a href="%{urlDoLcGeneration}" targets="mainContentDiv"
								data-toggle="tab">Generate Certificate</sj:a>
						</li>
					</ul>
					<div class="tab-content" id="viewLcContent">
						<jsp:include
							page="/WEB-INF/pages/admin/academic/tcGeneration/ajaxDoLcGeneration.jsp" />
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function() {
	changePageTitle('Manage LC Settings');
});
</script>