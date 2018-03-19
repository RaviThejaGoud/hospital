<%@ include file="/common/taglibs.jsp"%>

<s:if test='%{user.isSchoolHostel == "Y"}'>
	<div class="row">
		<div class="col-md-12">
			<div class="portlet box blue">
				<div class="portlet-title">
					<div class="caption">
						<i class="fa fa-globe"></i>Leave Details
					</div>
				</div>
				<div class="portlet-body">
					<div class="tabbable tabbable-custom tabbable-full-width">
						<ul class="nav nav-tabs">
							<s:if test='%{#session.previousYear == "N"}'>
								<li>
								
									<s:url id="urlHostelLeaveLink" action="ajaxViewStudentAndStaffLeaves"
									namespace="/staff" includeParams="all" escapeAmp="false">
									<s:param name="tempString" value="'student'" />
									</s:url>
									<sj:a href="%{urlHostelLeaveLink}" targets="stepStaffLeavesDiv"
										data-toggle="tab">Student Leaves To Approve</sj:a>
									
									
								</li>
							</s:if>
							<s:if test='%{#session.previousYear == "N"}'>
								<li>
								
									<s:url id="urlHostelLeaveLink" action="ajaxViewStudentAndStaffLeaves"
									namespace="/staff" includeParams="all" escapeAmp="false">
									<s:param name="tempString" value="'staff'" />
									</s:url>
									<sj:a href="%{urlHostelLeaveLink}" targets="stepStaffLeavesDiv"
										data-toggle="tab">Staff Leaves To Approve</sj:a>
									
									
								</li>
							</s:if>
							<li>
								<s:if
									test='%{(#session.academicYear == null || #session.academicYear.isEmpty()) || (#session.previousYear=="N")}'>
									<s:url id="urlApplyLeave" action="ajaxDoGetleaveReport" namespace="/staff"/>
									<sj:a href="%{urlApplyLeave}" targets="stepStaffLeavesDiv"
										data-toggle="tab">Apply leave</sj:a>
								</s:if>
							</li>
							<li class="active" id="staffLeavesId">
								<s:url id="stepMyLeaves" action="ajaxViewStaffLeaves"
									namespace="/staff" includeParams="all" escapeAmp="false">
									<s:param name="studySubject.id" value="0" />
								</s:url>
								<sj:a href="%{stepMyLeaves}" targets="stepStaffLeavesDiv"
									data-toggle="tab">My Leaves</sj:a>
							</li>
						</ul>
						<div id="stepStaffLeavesDiv" class="tab-content">
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</s:if>
<script type="text/javascript">
$(document).ready(function() {
	$('li#staffLeavesId a').click();
});
</script>

<script type="text/javascript">
$(document).ready(function() {
	$("input:checkbox, input:radio").uniform();
	var selected = $('input[name=SelectType]:radio:checked').val();
	getAjaxDoGetStaffLeaves(selected);
});
function getAjaxDoGetStaffLeaves(selected) {
	var pars = "tempString=" + selected;
	$("#adminLeavesViewDiv")
			.html(
					'<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
	var url = jQuery.url.getChatURL("/staff/ajaxViewStudentAndStaffLeaves.do");
	$.ajax( {
		url : url,
		cache : false,
		data : pars,
		success : function(html) {
			$("#adminLeavesViewDiv").html(html);
		}
	});
}
</script>


<!-- <div id="tabContent13" class="grid_13">
	<div id="tabWrapper13" style="margin-bottom: 100px;">
		<div id="tabNavigation">
			<ul>
				<s:if test='%{!(user.onlySchoolAdmin)}'>
					<li class="selected">
						<a href="#" id='/staff/ajaxViewStaffLeaves.do' type='stepMyLeaves'>My Leaves</a>
					</li>
				</s:if>
				<s:if test='%{(user.onlySchoolAdmin) || (user.schoolPrincipal) || (user.onlySchoolHod) || (user.schoolHostel) || (user.schoolTransport)}'>
					<s:if test='%{(user.onlySchoolAdmin)}'>
						<li class="selected">
					</s:if>
					<s:else>
						<li>
					</s:else>
							<a href="#" id='/staff/ajaxViewStudentAndStaffLeaves.do'
								class='tempString=staff' type='stepStaffLeaves'>Staff
								Leaves </a>
						</li>
				</s:if>
					<s:if test='%{(classTeacher !=null) || (user.onlySchoolAdmin) || (user.schoolPrincipal)}'>
						<li >
						<a href="#" id='/staff/ajaxViewStudentAndStaffLeaves.do'
							class='tempString=student' type='stepStudentLeaves'>Student	Leaves </a>
					</li>
					</s:if>
			</ul>
		</div>
		<div id="steps13" style="margin-bottom: 100px;">
			<s:if test='%{!(user.onlySchoolAdmin)}'>
				<fieldset class="step13" id='stepMyLeaves'>
				</fieldset>
			</s:if>
			<s:if test='%{!(user.onlySchoolTeacher) && !(user.schoolStudent)}'>
				<fieldset id='stepStaffLeaves' class="step13">
					</fieldset>
			</s:if>
			<s:if test='%{(classTeacher !=null) || (user.onlySchoolAdmin) || (user.schoolPrincipal)}'>
				<fieldset id='stepStudentLeaves' class="step13">
				</fieldset>
			</s:if>
		</div>
	</div>
</div>
<script Language="Javascript1.2" type="text/javascript">
$(document).ready(function() {
	commonLoadTab();
	if($('#tabNavigation ul li').hasClass("selected").toString()){
			genericAjaxCall($('#tabNavigation ul li a').attr('id'),$('#tabNavigation ul li a').attr('type'), $('#tabNavigation ul li a').attr('class'));
	}
});
</script>
-->