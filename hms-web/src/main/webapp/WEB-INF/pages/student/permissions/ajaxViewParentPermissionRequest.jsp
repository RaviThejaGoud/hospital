<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Permission Request
				</div>
			</div>
			<div class="portlet-body">
				<div class="tabbable tabbable-custom tabbable-full-width">
					<ul class="nav nav-tabs">
						<s:if test='%{#session.previousYear == "N"}'>
							<li>
							<s:url id="urlSchoolEditBooksLink" action="ajaxDoAddParentPermissionsRequest" namespace="/student" /> 
								<sj:a href="%{urlSchoolEditBooksLink}" targets="permissionsDiv" indicator="indicator"
									data-toggle="tab">Add Permission Request</sj:a></li>
							<li class="active">
								<s:url id="urlPermissions" action="ajaxViewParentPermissions"  namespace="/student"/>
									<sj:a cssClass="ajaxify PPFR" href="%{urlPermissions}" targets="mainContentDiv">Permission Request</sj:a>
							</li>
						</s:if>
					</ul>
					<div id="permissionsDiv" class="tab-content">
						<jsp:include
							page="/WEB-INF/pages/student/permissions/ajaxViewParentPermissionDetails.jsp"/>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
