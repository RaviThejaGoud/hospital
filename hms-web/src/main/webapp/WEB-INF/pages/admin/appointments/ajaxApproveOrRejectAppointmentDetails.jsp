<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/messages.jsp"%>
  <s:if test='%{user.isOnlySchoolHod == "Y" || user.isSchoolPrincipal=="Y" || user.isSchoolAdmin=="Y" || user.isSchoolDirector == "Y"}'>
  <s:if test='%{plTitle != "A"}'>
  <div class="row">
		<div class="col-md-12">
			<div class="portlet box blue">
				<div class="portlet-title">
					<div class="caption">
						<i class="fa fa-globe"></i>Appointments
					</div>
				</div>
				<div class="portlet-body">
					<div class="tabbable tabbable-custom tabbable-full-width">
						<ul class="nav nav-tabs">
						
						
						<li>
						 	 <s:if test='%{user.isSchoolAdmin == "Y" || user.isSchoolDirector == "Y"}'>
						  	 	<s:url id="ajaxViewStaffOnlineAppointment" action="ajaxViewStaffOnlineAppointment" namespace="/student" >
						  	 	<s:param name="tempString">staff</s:param>
						  	 	</s:url>
							 	<sj:a  href="%{ajaxViewStaffOnlineAppointment}" targets="contentDiv" data-toggle="tab"> Staff Appointments View</sj:a>
							</s:if>
						</li>
						<li>
						 	 <s:if test='%{user.isSchoolAdmin == "Y" || user.isSchoolDirector == "Y"}'>
						  	 	<s:url id="ajaxViewParentOnlineAppointment" action="ajaxViewParentOnlineAppointment" namespace="/student" >
						  	 	<s:param name="tempString">parents</s:param>
						  	 	</s:url>
							 	<sj:a  href="%{ajaxViewParentOnlineAppointment}" targets="contentDiv" data-toggle="tab"> Parent Appointments View</sj:a>
							</s:if>
						</li>
							<li class="active"><s:url
									action="ajaxApproveOrRejectOnlineAppointment"
									id="ajaxDoApproveOrRejectOnlineAppointment" namespace="/student"
									includeParams="all" escapeAmp="false">
								</s:url> <sj:a href="%{ajaxDoApproveOrRejectOnlineAppointment}"
									targets="mainContentDiv">Online Appointments</sj:a></li>
						</ul>
						<div class="tab-content" id="contentDiv">
							<jsp:include
								page="/WEB-INF/pages/admin/appointments/ajaxViewApproveOrRejectAppointmentDetails.jsp" />
						</div>
					</div>
				</div>
			</div>
	
		</div>
	</div>
  </s:if>
  <s:else>
 <jsp:include
		page="/WEB-INF/pages/admin/appointments/ajaxViewApproveOrRejectAppointmentDetails.jsp" />
  </s:else>
 	
  </s:if>
  <s:else>
    <div id="approveContentDiv">
		<jsp:include
		page="/WEB-INF/pages/admin/appointments/ajaxViewApproveOrRejectAppointmentDetails.jsp" />
	</div>
	
  </s:else>

