<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Manage Tickets
				</div>
			</div>
			<div class="portlet-body">
				<div class="tabbable tabbable-custom tabbable-full-width">
				<s:if test='%{user.isOnlySchoolAdmin=="Y" || user.isSchoolPrincipal=="Y" || user.isSchoolDirector == "Y" || user.isOnlySchoolHod=="Y" || user.isOnlySchoolTeacher=="Y" || user.isSchoolAsstStaff=="Y" || #session.isClassTeacher}'>
					<ul class="nav nav-tabs">	
						<%-- <li>
							<s:url id="doAddStaffdocu" action="ajaxDisableStaffDocuments" includeParams="all" escapeAmp="false" namespace="/staff"/>
							<sj:a href="%{doAddStaffdocu}"  targets="staffsContent" data-toggle="tab">
									Staff Documents
							</sj:a>
						</li --%>				
						<li>							
							<s:url id="addInternalSoftwareTicketDetails" action="ajaxDoAddInternalSoftwareTicketDetails" namespace="/admin"/>
							<sj:a href="%{addInternalSoftwareTicketDetails}" targets="ticketContent" data-toggle="tab">Add Ticket</sj:a>
						</li>
						
						<li class="active">
							<s:url id="internalSoftwareTicketDetails" action="ajaxInternalSoftwareTicketDetails" namespace="/admin">
							</s:url>
							<sj:a id="internalSoftwareTicketDetails" href="%{internalSoftwareTicketDetails}" targets="mainContentDiv" data-toggle="tab">View Ticket</sj:a> 	
						</li>
					</ul>
					</s:if>
					<div id="ticketContent" class="tab-content">
							<jsp:include page="/WEB-INF/pages/admin/tickets/ajaxViewInternalSoftwareTicketDetails.jsp"/>
					</div>
				</div>
			</div>
		</div>
	</div>
</div> 
<script type="text/javascript">
	changePageTitle("Mange Tickets");
</script>
