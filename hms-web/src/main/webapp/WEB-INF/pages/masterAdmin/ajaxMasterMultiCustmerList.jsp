<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Customer's Details
				</div>
			</div>
			<div class="portlet-body">
				<div class="tabbable tabbable-custom tabbable-full-width">
					<ul class="nav nav-tabs">
						<li>
							<s:url id="doManageMultiBranchCust"	action="ajaxEditMultiBranchCustomer" escapeAmp="false"	namespace="/masterAdmin" />
							<sj:a id="createBranchLink" href="%{doManageMultiBranchCust}" targets="contentDiv" data-toggle="tab"> 
								Create Multi Branch Customer</sj:a>
						</li>
						<li class="active">
							<s:url id="viewSMS" action="ajaxDoCreateMaster" namespace="/masterAdmin">
							</s:url>
							<sj:a id="viewSMS" href="%{viewSMS}" targets="mainContentDiv" data-toggle="tab">View</sj:a> 	
						 </li>
					</ul>
					<div class="tab-content" id="contentDiv">
					<%@ include file="/common/messages.jsp"%>
						<jsp:include
							page="/WEB-INF/pages/masterAdmin/ajaxViewCustomerDetails.jsp" />
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function() {
	$("div#govtCustomerDetails").hide();
});
</script>