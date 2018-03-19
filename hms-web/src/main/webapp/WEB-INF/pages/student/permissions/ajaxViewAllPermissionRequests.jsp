<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Permission Requests
				</div>
			</div>
			<div class="portlet-body">
			<div class="tabbable tabbable-custom tabbable-full-width">
					<ul class="nav nav-tabs">
						<s:if test='%{#session.previousYear == "N"}'>
							<li>
								<s:url id="StaffRequest" action="ajaxAadminViewStaffRequest" namespace="/staff"> </s:url>
								<sj:a href="%{StaffRequest}" targets="permissionsDiv" data-toggle="tab"> View Staff Permissions Requests</sj:a>
							</li>
							<li class="active">
								<s:url id="urlPermissions" action="ajaxAdminViewParentPermissions" namespace="/student">
								</s:url>
								<sj:a  href="%{urlPermissions}" targets="permissionsDiv"  data-toggle="tab"> View Parent Permissions Requests</sj:a>
							</li>
						</s:if>
					</ul>
					<div id="permissionsDiv" class="tab-content">
					<jsp:include
						page="/WEB-INF/pages/student/permissions/ajaxViewAllPermissionDetails.jsp"/>
				   </div>
				</div>
			</div>
		</div>
	</div>
</div>
