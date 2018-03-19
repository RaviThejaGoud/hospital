<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>New Customer's Details
				</div>
			</div>
			<div class="portlet-body">
				<div class="tabbable tabbable-custom tabbable-full-width">
					<ul class="nav nav-tabs">
						 <li>
							<s:url id="doNewCustomersDetails"	action="ajaxDoAddNewCustomersDetails" escapeAmp="false"	namespace="/masterAdmin" />
							<sj:a id="doNewCustomersDetails" href="%{doNewCustomersDetails}" targets="newCustomerDetailsDiv" data-toggle="tab"> 
								Create New Customer</sj:a>
						</li>
						<li class="active">
							<s:url id="viewNewCustomersDetails" action="ajaxViewNewCustomersDetails" namespace="/masterAdmin">
							</s:url>
							<sj:a id="viewNewCustomersDetails" href="%{viewNewCustomersDetails}" targets="mainContentDiv" data-toggle="tab">View</sj:a> 	
						 </li>
					</ul>
					<div class="tab-content" id="newCustomerDetailsDiv">
					<%@ include file="/common/messages.jsp"%>
						<jsp:include
							page="/WEB-INF/pages/masterAdmin/ajaxViewNewCustomersDetails.jsp" />
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
</script>