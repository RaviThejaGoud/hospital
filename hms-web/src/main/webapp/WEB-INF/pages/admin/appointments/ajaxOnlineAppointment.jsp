<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Organization Customers Details
				</div>
			</div>
			<div class="portlet-body">
				<div class="tabbable tabbable-custom tabbable-full-width">
					<ul class="nav nav-tabs">
						<li>
						<s:url id="doOnlineAppoinment"	action="ajaxOranizationCustomerDetails" escapeAmp="false" namespace="/masterAdmin" />
							<sj:a id="createBranchLink" href="%{doOnlineAppoinment}" targets="contentDiv" data-toggle="tab"> 
								Create Organization Members</sj:a>
						</li>
						<li class="active">
							<s:url id="onlineAppoinment" action="ajaxOnlineAppoinment" namespace="/masterAdmin">
							</s:url>
							<sj:a id="onlineAppoinment" href="%{onlineAppoinment}" targets="mainContentDiv" data-toggle="tab">View</sj:a> 	
						 </li>
					</ul>
					<div class="tab-content" id="contentDiv">
						<jsp:include page="/WEB-INF/pages/masterAdmin/ajaxViewOrganizationCustomers.jsp" />
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function() {
	changePageTitle("Organization Customer Details")
});
</script>