<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12" id="stepStaffLeavesEditDiv">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Manage Leaves
				</div>
			</div>
			<div class="portlet-body">
				<div class="tabbable tabbable-custom tabbable-full-width">
					<ul class="nav nav-tabs">
					<s:if test='%{user.isSchoolPrincipal == "Y" || user.isSchoolAdmin == "Y" || user.isSchoolDirector == "Y"}'>
							<li><s:if test='%{#session.previousYear == "N"}'>
									<s:url id="urlApplyLeave" action="ajaxDoGetleaveReport"
										namespace="/staff" />
									<sj:a href="%{urlApplyLeave}" targets="stepStaffLeavesDiv"
										data-toggle="tab">Apply leave</sj:a>
								</s:if></li>
							<li id="princLeaves"><s:url id="stepMyLeaves"
									action="ajaxViewStaffLeaves" namespace="/staff"
									includeParams="all" escapeAmp="false">
									<s:param name="studySubject.id" value="0" />
								</s:url> <sj:a href="%{stepMyLeaves}" targets="stepStaffLeavesDiv"
									data-toggle="tab">My Leaves</sj:a></li>
									
									<%-- <s:if test='%{#session.previousYear == "N"}'>
										<li>
											<s:url id="stepStudentLeaves"
												action="ajaxViewStudentAndStaffLeaves" includeParams="all"
												escapeAmp="false" namespace="/staff">
												<s:param name="tempString">staff</s:param>
											</s:url>
											<sj:a href="%{stepStudentLeaves}" targets="stepStaffLeavesDiv"
												data-toggle="tab">Staff Leaves</sj:a>
									 </li>
							</s:if> --%>
						</s:if>
						<li>
							<s:url id="urlStaffTotalLeaveDetails"
								action="ajaxViewAllStaffLeaveDetails" namespace="/admin" />
							<sj:a id="urlStaffTotalLeaveDetails"
								href="%{urlStaffTotalLeaveDetails}" targets="stepStaffLeavesDiv"
								data-toggle="tab">
								Staff Leaves Management </sj:a>
						</li>
						<s:if test='%{#session.previousYear == "N"}'>
						
							
							<%-- <li>
								<s:url id="urlLeaveLink1" action="ajaxDoGetLeaveDetailsLeft"
									namespace="/staff" />
								<sj:a href="%{urlLeaveLink1}" targets="stepStaffLeavesDiv"
									data-toggle="tab">Leave Requests</sj:a>
							</li> --%>
							<li>
								<s:url id="urlLeaveLink" action="ajaxDoManageLeaves"
									namespace="/admin" />
								<sj:a id="addLeaveSetting" href="%{urlLeaveLink}" targets="stepStaffLeavesDiv" 
									data-toggle="tab">Add Leave Settings</sj:a>
							</li>
						</s:if>
						<li class="active" id="staffLeaveDetails">
							<s:url id="viewLeaves" action="ajaxViewAllManageLeaves"
								namespace="/admin">
							</s:url>
							<sj:a href="%{viewLeaves}" targets="mainContentDiv"
								data-toggle="tab">View Leave Settings</sj:a>
						</li>
					</ul>
					<div id="stepStaffLeavesDiv" class="tab-content">
						<%@ include file="/common/messages.jsp"%>
						<jsp:include
							page="/WEB-INF/pages/admin/staff/leaves/ajaxViewManageLeavesList.jsp" />
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function() {
	changePageTitle('Leave Management');
	$('#teachingDiv').show();
	$('#nonTeachingDiv').hide();
});
</script>
