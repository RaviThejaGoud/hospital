<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Customer Details
				</div>
			</div>
			<div class="portlet-body">
				<div class="tabbable tabbable-custom tabbable-full-width">
					<ul class="nav nav-tabs">	
						<li>
							<s:url id="demoCustomerDetails" action="ajaxGetDemoCustomerDetails" namespace="/masterAdmin"/>
							<sj:a href="%{demoCustomerDetails}" targets="contentDiv" data-toggle="tab">Demo Customers </sj:a>
						</li>					
						<li>							
							<s:url id="DiscontinueCustomers" action="ajaxViewExpiredCustomers" namespace="/masterAdmin"/>
							<sj:a href="%{DiscontinueCustomers}" targets="contentDiv" data-toggle="tab">Inactive Customers</sj:a>
						</li>
						<li class="active">
								<s:url id="customerDetails" action="ajaxEditCustomerSettings"
									namespace="/masterAdmin" />
								<sj:a href="%{customerDetails}" targets="mainContentDiv" >Active Customers</sj:a>
							</li>
					</ul>
					<div id="contentDiv" class="tab-content">
						<jsp:include page="/WEB-INF/pages/masterAdmin/ajaxViewCustomerList.jsp"/>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
