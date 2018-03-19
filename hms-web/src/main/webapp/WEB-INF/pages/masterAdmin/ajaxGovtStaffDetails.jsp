<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Government Staff Registration
				</div>
			</div>
			<div class="portlet-body">
				<div class="tabbable tabbable-custom tabbable-full-width">
					<ul class="nav nav-tabs">
						<!--<li>
							<s:url id="urlAddPackageLink" action="ajaxDoAddPackage" namespace="/masterAdmin"/>
							<sj:a href="%{urlAddPackageLink}" targets="packageDetailsDiv" data-toggle="tab">Add Package</sj:a>
						</li>
						--><li class="active">
							<s:url id="addGovtStaffRegisterLink" action="ajaxAddGovtStaffRegister" namespace="/signup">
							</s:url>
							<sj:a href="%{addGovtStaffRegisterLink}" targets="packageDetailsDiv" data-toggle="tab">Gov Staff Registration</sj:a> 	
						 </li>
					</ul>
						<div class="tab-content" id="govStaffDetails">
							<jsp:include page="/WEB-INF/pages/masterAdmin/ajaxGovtCustomerRegistration.jsp"></jsp:include>
						</div>
				</div>
			</div>
			
		</div>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function() {
	
});


</script>