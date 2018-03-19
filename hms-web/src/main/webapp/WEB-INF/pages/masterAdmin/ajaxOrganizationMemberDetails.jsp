<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Organization Members Details
				</div>
			</div>
			<div class="portlet-body">
				<div class="tabbable tabbable-custom tabbable-full-width">
					<ul class="nav nav-tabs">
					<s:if test='%{user.isSecretary=="Y" || user.isMasterAdmin == "Y"}'>
						<li>
							<s:url id="doManageMultiBranchCust"	action="ajaxDoCreateOrganizationMembers" escapeAmp="false"	namespace="/masterAdmin" />
							<sj:a id="createBranchLink" href="%{doManageMultiBranchCust}" targets="contentDiv" data-toggle="tab"> 
								Create Trust Members</sj:a>
						</li>
					</s:if>
						
						<li class="active">
							<s:url id="trustMemberDetails" action="ajaxOrganizationMemberDetails" namespace="/masterAdmin">
							</s:url>
							<sj:a id="trustMemberDetails" href="%{trustMemberDetails}" targets="mainContentDiv" data-toggle="tab">View</sj:a> 	
						 </li>
					</ul>
					<div class="tab-content" id="contentDiv">
					<%@ include file="/common/messages.jsp"%>
						<jsp:include page="/WEB-INF/pages/masterAdmin/ajaxViewOrganizationMembers.jsp" />
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function() {
	changePageTitle("Organization Members Details")
});
</script>