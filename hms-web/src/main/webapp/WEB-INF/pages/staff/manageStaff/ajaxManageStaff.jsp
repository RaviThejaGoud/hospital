<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Manage Staff
				</div>
			</div>
			<div class="portlet-body">
				<div class="tabbable tabbable-custom tabbable-full-width">
				<s:if test='%{user.isOnlySchoolAdmin=="Y" || user.isSchoolPrincipal=="Y" || user.isSchoolDirector == "Y"}'>
					<ul class="nav nav-tabs">	
						<%-- <li>
							<s:url id="doAddStaffdocu" action="ajaxDisableStaffDocuments" includeParams="all" escapeAmp="false" namespace="/staff"/>
							<sj:a href="%{doAddStaffdocu}"  targets="staffsContent" data-toggle="tab">
									Staff Documents
							</sj:a>
						</li --%>				
						<li>							
							<s:url id="DiscontinueStaffs" action="ajaxViewExpiredStaffs" namespace="/staff"/>
							<sj:a href="%{DiscontinueStaffs}" targets="staffsContent" data-toggle="tab">Inactive Staff</sj:a>
						</li>
						<s:if test='%{#session.previousYear == "N"}'>  				       	
							<li>							
								<s:url id="uploadDownloadStaffDocuments" action="ajaxDoUploadAndDownloadDocs" includeParams="all" escapeAmp="false" namespace="/staff">
								<s:param name="tempString">uploadStaffDoc</s:param></s:url>
								<sj:a href="%{uploadDownloadStaffDocuments}" targets="staffsContent" data-toggle="tab">Upload / Download Documents</sj:a>
								
							</li>
							<li>							
								<s:url id="downloadStaffExcelSheet" action="ajaxDoViewStaffRoles" includeParams="all" escapeAmp="false" namespace="/reports">
								<s:param name="plTitle">Staff Details</s:param></s:url>
								<sj:a href="%{downloadStaffExcelSheet}" targets="staffsContent" data-toggle="tab">Edit Staff Details</sj:a>
							</li>
							<li>							
								<s:url id="importStaffExcelSheet" action="ajaxDoImportStaffExcelSheet" namespace="/staff"/>
								<sj:a href="%{importStaffExcelSheet}" targets="staffsContent" data-toggle="tab">Import Staff</sj:a>
							</li>
							<li>
								<s:url id="doAddStaff" action="ajaxDoAddStaff" includeParams="all" escapeAmp="false" namespace="/staff"/>
								<sj:a href="%{doAddStaff}"  targets="staffsContent" data-toggle="tab">
										Add Staff
								</sj:a>
							</li>
						</s:if>
						<li class="active">
							<s:url id="viewSub" action="ajaxDoManageStaff" namespace="/staff">
							</s:url>
							<sj:a id="viewSub" href="%{viewSub}" targets="mainContentDiv" data-toggle="tab">View Staff Details</sj:a> 	
						</li>
					</ul>
					</s:if>
					<div id="staffsContent" class="tab-content">
							<jsp:include page="/WEB-INF/pages/staff/manageStaff/ajaxViewStaffList.jsp"/>
					</div>
				</div>
			</div>
		</div>
	</div>
</div> 
<script type="text/javascript">
	changePageTitle("Mange Staff");
</script>
