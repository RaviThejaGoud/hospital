<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Staff Permission Requests
				</div>
			</div>
			<div class="portlet-body">
			<div class="tabbable tabbable-custom tabbable-full-width">
					<ul class="nav nav-tabs">
						<s:if test='%{#session.previousYear == "N"}'>
							<s:if test='%{user.isOnlySchoolHod=="Y"}'>
								<li>
									<s:url id="StaffRequest" action="ajaxAadminViewStaffRequest" namespace="/staff"> </s:url>
									<sj:a href="%{StaffRequest}" targets="permissionsDiv" data-toggle="tab"> View Staff Permissions Requests</sj:a>
								</li>
								<li>
									<s:url id="urlPermissions" action="ajaxAdminViewParentPermissions" namespace="/student">
									</s:url>
									<sj:a  href="%{urlPermissions}" targets="permissionsDiv"  data-toggle="tab"> View Parent Permissions Requests</sj:a>
								</li>
							</s:if>
							<s:if test='%{isClassTeacher == "isClassTeacher"}'>
								<li>
									<s:url id="urlClassTeacher" action="ajaxDoAddStaffPermissions" namespace="/staff" includeParams="all" escapeAmp="false">
										<s:param name="bankName">isClassTeacher</s:param>
									</s:url>										
									<sj:a href="%{urlClassTeacher}"
										targets="permissionsDiv" indicator="indicator" data-toggle="tab">Add Permission Request</sj:a>
								</li>
								<li class="active">
									<s:url id="StaffRequest" action="ajaxViewStaffRequest" namespace="/staff" includeParams="all" escapeAmp="false">
										<s:param name="bankName">isClassTeacher</s:param>
									 </s:url>
									<sj:a href="%{StaffRequest}" targets="mainContentDiv" data-toggle="tab"> View My Requests</sj:a>
								</li>
								<li>
									<s:url id="urlPermissions" action="ajaxAdminViewParentPermissions" namespace="/student" includeParams="all" escapeAmp="false">
									<s:param name="bankName">isClassTeacher</s:param>
									</s:url>
									<sj:a  href="%{urlPermissions}" targets="permissionsDiv" data-toggle="tab">View Parent Requests</sj:a>
								</li>
							</s:if>
							<s:else>
								<li>
									<s:url id="urlStaffPermissions" action="ajaxDoAddStaffPermissions" namespace="/staff"/>
									<sj:a href="%{urlStaffPermissions}"
										targets="permissionsDiv" indicator="indicator" data-toggle="tab">Add Permission Request</sj:a>
								</li>
								<li class="active">
									<s:url id="StaffRequest" action="ajaxViewStaffRequest" namespace="/staff" includeParams="all" escapeAmp="false">
									 </s:url>
									<sj:a href="%{StaffRequest}" targets="mainContentDiv" data-toggle="tab"> View My Requests</sj:a>
								</li>
							</s:else>
						</s:if>
					</ul>
					<div id="permissionsDiv" class="tab-content">
					<jsp:include
						page="/WEB-INF/pages/staff/permissions/ajaxViewStaffPermissionDetails.jsp"/>
				   </div>
				</div>
			</div>
		</div>
	</div>
</div>
