<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Manage Timetable Documents
				</div>
			</div>
			<div class="portlet-body">
				<div class="tabbable tabbable-custom tabbable-full-width">
				<s:if test='%{user.isOnlySchoolAdmin=="Y" || user.isSchoolPrincipal=="Y" || user.isSchoolDirector == "Y"}'>
					<ul class="nav nav-tabs">						
						<li class="active">
						
						<s:url id="urlFETTimetable" action="ajaxUploadTimetableDocuments"
									namespace="/staff" includeParams="all" escapeAmp="false">
									<s:param name="tempString">uploadTimetable</s:param>
									<s:param name="plTitle">Staff Details</s:param>
								</s:url>
								<sj:a href="%{urlFETTimetable}" targets="mainContentDiv"
									cssClass="ajaxify MTFET">
							Download Staff wise Timetable</sj:a>
							
						
							<%-- <s:url id="downloadStaffExcelSheet" action="ajaxUploadTimetableDocuments" includeParams="all" escapeAmp="false" namespace="/staff">
								<s:param name="plTitle">Staff Details</s:param></s:url>
								<sj:a href="%{downloadStaffExcelSheet}" targets="staffsContent" data-toggle="tab">Edit Staff Details</sj:a>
								
							<s:url id="viewSub" action="ajaxDoManageStaff" namespace="/staff">
							</s:url>
							<sj:a id="viewSub" href="%{viewSub}" targets="mainContentDiv" data-toggle="tab">View Staff Details</sj:a> 	 --%>
						</li>
					</ul>
					</s:if>
					<div id="staffsContent" class="tab-content">
							<jsp:include page="/WEB-INF/pages/admin/academic/timeTable/ajaxUploadTimetableDocuments.jsp"/>
					</div>
				</div>
			</div>
		</div>
	</div>
</div> 
<script type="text/javascript">
	changePageTitle("Manage Timetable Documents");
</script>
