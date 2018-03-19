<%@ include file="/common/taglibs.jsp"%>
<s:if test='%{user.isOnlySchoolAdmin == "Y" || user.isSchoolPrincipal == "Y" || user.isSchoolDirector == "Y"}'>
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
							<li>
								<s:url id="stepStaffLeavesRequest" action="ajaxViewStudentAndStaffLeaves" includeParams="all"
										escapeAmp="false" namespace="/staff">
										<s:param name="tempString">staff</s:param>
									</s:url>
								 <sj:a href="%{stepStaffLeavesRequest}" targets="stepStaffLeavesDiv"
										data-toggle="tab">Staff Leave Request</sj:a>
							</li>
							<li class="active">
								<s:url action="ajaxDoGetStaffAndStudenTLeaveReq"
									id="ajaxDoGetLeaveDetailsLeftLink" namespace="/staff"
									includeParams="all" escapeAmp="false">
									<s:param name="tempString">student</s:param>
								</s:url>
								<sj:a href="%{ajaxDoGetLeaveDetailsLeftLink}" targets="mainContentDiv"
								data-toggle="tab">Student Leave Request</sj:a>
							</li>
						</ul>
						<div id="stepStaffLeavesDiv" class="tab-content">
							<jsp:include
								page="/WEB-INF/pages/staff/leaves/ajaxViewStaffAndStudentLeavesForApprovals.jsp" />
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%-- <div class="form-body">
		<div class="row">
			<div class="col-md-12 form-horizontal">
				<div class="form-group">
					<label class="col-md-3 control-label">
						Approve Leave For :
					</label>
					<div class="col-md-9">
						<div class="radio-list">

							<label class="radio-inline">
								<input type="radio" name="SelectType" value="staff"
									onclick="getAjaxDoGetStaffLeaves(this.value);"
									checked="checked">
								Staff Leaves
							</label>
							<label class="radio-inline">
								<input type="radio" value="student" name="SelectType"
									onclick="getAjaxDoGetStaffLeaves(this.value);" class="radio">
								Student Leaves
							</label>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div id="adminLeavesViewDiv">
		<jsp:include
			page="/WEB-INF/pages/staff/leaves/ajaxViewStaffAndStudentLeavesForApprovals.jsp" />
	</div> --%>
</s:if>
<s:if
	test='%{user.isOnlySchoolHod=="Y" || user.isSchoolTeacher == "Y" || user.isSchoolTransport == "Y" || user.schoolHostel}'>
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
							<li>
								<s:if
									test='%{(#session.academicYear == null || #session.academicYear.isEmpty()) || (#session.previousYear=="N")}'>
									<s:url id="urlApplyLeave" action="ajaxDoGetleaveReport" />
									<sj:a href="%{urlApplyLeave}" targets="stepStaffLeavesDiv"
										data-toggle="tab">Apply leave</sj:a>
								</s:if>
							</li>
							<s:if test='%{(user.onlySchoolAdmin) || (user.schoolPrincipal) || user.isOnlySchoolHod=="Y" || classTeacher!=null}'>
								<s:if test='%{classTeacher==null}'>
									<li>
										<s:url id="stepStudentLeaves"
											action="ajaxViewStudentAndStaffLeaves" includeParams="all"
											escapeAmp="false" namespace="/staff">
											<s:param name="tempString">staff</s:param>
										</s:url>
										<sj:a href="%{stepStudentLeaves}" targets="stepStaffLeavesDiv"
											data-toggle="tab">Staff Leaves</sj:a>
									 </li>
								 </s:if>
								<li>
									<s:url id="stepStudentLeaves"
										action="ajaxViewStudentAndStaffLeaves" includeParams="all"
										escapeAmp="false" namespace="/staff">
										<s:param name="tempString">student</s:param>
									</s:url>
									<sj:a href="%{stepStudentLeaves}" targets="stepStaffLeavesDiv"
										data-toggle="tab">Student Leaves</sj:a>
								</li>
							</s:if>
							<li class="active" id="staffLeavesId">
								<s:url id="stepMyLeaves" action="ajaxViewStaffLeaves"
									namespace="/staff" includeParams="all" escapeAmp="false">
									<s:param name="studySubject.id" value="0" />
								</s:url>
								<sj:a href="%{stepMyLeaves}" targets="stepStaffLeavesDiv" indicator="indicator"
									data-toggle="tab">My Leaves</sj:a>
							</li>
						</ul>
						<div id="stepStaffLeavesDiv" class="tab-content">
							<jsp:include page="/WEB-INF/pages/staff/leaves/ajaxViewStaffLeaves.jsp"></jsp:include>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</s:if>
<s:if
	test='%{user.isSchoolFinance=="Y" || user.isTransportFinance=="Y" || user.isHostelFinance=="Y" || user.isSchoolLibrarian == "Y" || user.isSchoolClerk=="Y" || user.isSchoolAsstStaff=="Y" || user.isMessManager=="Y" || user.isStoreKeeper=="Y" || user.isReceptionist=="Y"}'>
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
	if(isNonEmpty(selected) && selected!="undefined"){
	//alert(selected);
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