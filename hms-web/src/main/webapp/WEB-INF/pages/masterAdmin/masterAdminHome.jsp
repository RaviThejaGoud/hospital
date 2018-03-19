<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Package Details
				</div>
			</div>
			<div class="portlet-body">
				<div class="tabbable tabbable-custom tabbable-full-width">
					<ul class="nav nav-tabs">
						<li>
							<s:url id="urlAddPackageLink" action="ajaxDoAddPackage" namespace="/masterAdmin"/>
							<sj:a href="%{urlAddPackageLink}" targets="packageDetailsDiv" data-toggle="tab">Add Package</sj:a>
						</li>
						<li class="active">
							<s:url id="viewPackages" action="ajaxViewPackageDetails" namespace="/masterAdmin">
							</s:url>
							<sj:a id="viewPackages" href="%{viewPackages}" targets="packageDetailsDiv" data-toggle="tab">View</sj:a> 	
						 </li>
					</ul>
						<div class="tab-content" id="packageDetailsDiv">
							<jsp:include
								page="/WEB-INF/pages/masterAdmin/ajaxViewPackages.jsp"></jsp:include>
						</div>
				</div>
			</div>
		</div>
	</div>
</div>
