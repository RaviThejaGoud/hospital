<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>User Login Details
				</div>
			</div>
			<div class="portlet-body">
				<div class="tabbable tabbable-custom tabbable-full-width">
					<ul class="nav nav-tabs">
						<li class="active">
							<s:url id="urlViewUserIpAddressLocation" action="ajaxGetUserIpAddressLocationDetails" namespace="/masterAdmin"></s:url>
							<sj:a id="userIpAddressLocationId" href="%{urlViewUserIpAddressLocation}" targets="mainContentDiv" data-toggle="tab">
								View</sj:a> 	
						 </li>
					</ul>
					<div class="tab-content" id="mainContentDiv">
						<jsp:include page="/WEB-INF/pages/masterAdmin/ajaxUserIpAddressLocationList.jsp" />
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	$(document).ready(function(){
		changePageTitle("User Login Details");
	});
</script>