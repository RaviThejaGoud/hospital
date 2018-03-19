<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Online Appointment
				</div>
			</div>
			<div class="portlet-body">
				<div class="tabbable tabbable-custom tabbable-full-width">
					<ul class="nav nav-tabs">
						<li>
						<s:url id="doManageMultiBranchCust"	action="ajaxOnlineAppointment" escapeAmp="false"	namespace="/student" />
							<sj:a id="createBranchLink" href="%{doManageMultiBranchCust}" targets="contentDiv" data-toggle="tab"> 
								Create Appointment</sj:a>
						</li>
						<li class="active">
									<s:url id="urlParentOnlineAppointment" action="ajaxViewOnlineAppointment" namespace="/student" />
									<sj:a  href="%{urlParentOnlineAppointment}" targets="mainContentDiv" >My Appointments</sj:a>
					   </li>
					   <li>
						 	 <s:if test='%{user.isOnlySchoolHod == "Y" || user.isSchoolPrincipal=="Y" || user.isSchoolTeacher=="Y" || user.isSchoolDirector == "Y"}'>
						  	 	<s:url id="ajaxDoApproveOrRejectOnlineAppointment" action="ajaxApproveOrRejectOnlineAppointment" namespace="/student" >
						  	 	<s:param name="anyTitle">appointments</s:param>
						  	 	</s:url>
							 	<sj:a  href="%{ajaxDoApproveOrRejectOnlineAppointment}" targets="contentDiv" data-toggle="tab"> Parent Appointments View</sj:a>
							</s:if>
						</li>
					</ul>
					<div class="tab-content" id="contentDiv">
						<jsp:include page="/WEB-INF/pages/admin/appointments/ajaxViewOnlineAppointmentDetails.jsp" />
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function() {
	changePageTitle("Organization Customer Details")
});
</script>