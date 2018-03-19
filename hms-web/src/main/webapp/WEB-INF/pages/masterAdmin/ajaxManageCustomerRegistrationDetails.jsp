<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>New Customer's Registration Details
				</div>
			</div>
			<div class="portlet-body">
				<div class="tabbable tabbable-custom tabbable-full-width">
					<ul class="nav nav-tabs">
						 <li>
							<s:url id="doALlmanageCustomerRegistrationDetails"	action="ajaxViewAllCustomerRegistrationDetails" namespace="/masterAdmin"  includeParams="all" escapeAmp="false">
								<s:param name="tempString2" >all</s:param>
							</s:url>
							<sj:a id="doALlmanageCustomerRegistrationDetails" href="%{doALlmanageCustomerRegistrationDetails}" targets="newCustomerDetailsDiv" data-toggle="tab"> 
								Accept/Reject Requests</sj:a>
						</li>
						<li class="active">
							<s:url id="manageCustomerRegistrationDetails" action="ajaxManageCustomerRegistrationDetails" namespace="/masterAdmin">
							</s:url>
							<sj:a id="manageCustomerRegistrationDetails" href="%{manageCustomerRegistrationDetails}" targets="mainContentDiv" data-toggle="tab">View Pending Requests</sj:a> 	
						 </li>
					</ul>
					<div class="tab-content" id="newCustomerDetailsDiv">
						<jsp:include page="/WEB-INF/pages/masterAdmin/ajaxViewCustomerRegistrationDetails.jsp" />
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function() {
	//$("div#govtCustomerDetails").hide();
});

document.title = "New Customer's Registration Details";

</script>