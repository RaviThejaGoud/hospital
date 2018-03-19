<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Leave Management
				</div>
			</div>
			<div class="portlet-body">
				<s:if test='%{user.parent}'>
					<div class="tabbable tabbable-custom tabbable-full-width">
						<ul class="nav nav-tabs">
							<li>
								<s:url id="urlLeaveLink" action="ajaxDoLeaveForm"  namespace="/student"/>
								<sj:a href="%{urlLeaveLink}" targets="parentLeaveContent" data-toggle="tab">Apply Leave</sj:a>
							</li>
							<s:if test='%{viewStudentPersonAccountDetails.hostelMode == "H"}'>
								<li>
									<s:url id="leaveHostelDetails" action="ajaxDoGetStudentLeaveDetails" escapeAmp="false" includeParams="all"
										namespace="/student">
										<s:param name="alertSendType" value="hostel"></s:param>
									</s:url>
									<sj:a id="leaveHostelDetails"   href="%{leaveHostelDetails}" targets="mainContentDiv" 
										data-toggle="tab">View Hostel Leave Details</sj:a>
								</li>
							</s:if>
							
							<li class="active">
								<s:url id="leaveDetails" action="ajaxDoGetStudentLeaveDetails"
									namespace="/student">
								</s:url>
								<sj:a id="leaveDetails"   href="%{leaveDetails}" targets="mainContentDiv"
									data-toggle="tab">View Leave Details</sj:a>
							</li>
						</ul>
				</s:if>
				<div id="parentLeaveContent" class="tab-content">
					<s:if
						test='%{viewStudentPersonAccountDetailsList != null && !viewStudentPersonAccountDetailsList.isEmpty()}'>
						<s:if test='%{viewStudentPersonAccountDetailsList.size > 1}'>
							<div class="form-group form-horizontal">
								<label class="control-label col-md-3">
									Student Name :
								</label>
								<div class="col-md-3">
									<s:select id="sectionId"
										list="viewStudentPersonAccountDetailsList" listKey="accountIdClassSectionIdAcademicYearIdAndCustId"
										label="Student Name" cssClass="form-control"
										listValue="idAndName" name="anyId" theme="simple"
										onchange="javascript:getStudentLeaveDetails(this.value);" />
								</div>
							</div>
						</s:if>
					</s:if>
					<div id="leavesContent">
						<jsp:include
							page="/WEB-INF/pages/student/leaves/viewLeavesList.jsp" />
					</div>
				</div>
				<s:if test='%{user.parent}'>
				</div>
			 </s:if>
		</div>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function() {
	changePageTitle("Leave Management");
});
function getStudentLeaveDetails(accountId) 
{
	if (isNonEmpty(accountId)) {
		$('#leavesContent').html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		$
				.ajax( {
					url : jQuery.url.getChatURL("/student/ajaxGetStudentLeaveDetails.do?tempString=" + accountId+"&alertSendType=SL"),
					cache : true,
					success : function(response) {
						if (isNonEmpty(response)) {
							$('#leavesContent').html(response);
						}
					}
				});
	}
}

function getStudentHostelOrSchoolLeaveDetails(leaveType) {

var accountId = $('#sectionId').val();
	if (isNonEmpty(leaveType) && isNonEmpty(accountId)) {
		$('#leavesContent').html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		$
				.ajax( {
					url : jQuery.url.getChatURL("/student/ajaxGetStudentLeaveDetails.do?tempString=" + accountId+"&alertSendType="+leaveType),
					cache : true,
					success : function(response) {
						if (isNonEmpty(response)) {
							$('#leavesContent').html(response);
						}
					}
				});
	}
}
</script>