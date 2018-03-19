<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Payment & Receipts
				</div>
			</div>
			<div class="portlet-body">
				<div id="subjectsContentDiv" class="tab-content">
					<%@ include file="/common/messages.jsp"%>
					<s:if
						test='%{viewStudentPersonAccountDetailsList != null && !viewStudentPersonAccountDetailsList.isEmpty()}'>
						<span id="tempIdSpan"
							class="<s:property value='viewStudentPersonAccountDetails.studentId'/>"></span>
						<s:if test='%{viewStudentPersonAccountDetailsList.size > 1}'>
						<div class="form-group form-horizontal">
								<label class="control-label col-md-2">
									Student Name :
								</label>
								<div class="col-md-3">
									<s:select id="sectionId"
									list="viewStudentPersonAccountDetailsList" listKey="studentId"
									label="Student Name" cssClass="form-control" 
									listValue="idAndName" name="tempId1" theme="simple"
									onchange="javascript:getStudentUpcomingFeeDetails(this.value);" />
								</div>
							</div>
							<br/>
						</s:if>
					</s:if>
					<div  id="upcomingFeeContent">
						<jsp:include
							page="/WEB-INF/pages/student/ajaxStudentFeeDetails.jsp"></jsp:include>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
$(document).ready(function() {
	var tempId = $('span#tempIdSpan').attr("class");
	if (isNonEmpty(tempId))
		getStudentUpcomingFeeDetails(tempId);
	else
		getStudentUpcomingFeeDetails('');
});
changePageTitle("Student Payments & Receipts");
function getStudentUpcomingFeeDetails(studentId) {
	var url;
	if (isNonEmpty(studentId)) {
		url = jQuery.url.getChatURL("/student/ajaxDoGetMyFeeDetails.do?tempId="+ studentId);
	} else {
		url = jQuery.url.getChatURL("/student/ajaxDoGetMyFeeDetails.do");
	}
	
	$('#upcomingFeeContent')
			.html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
	$.ajax( {
		url : url,
		cache : true,
		success : function(response) {
			if (isNonEmpty(response)) {
				$('#upcomingFeeContent').html(response);
			}
		}
	});
}
</script>
