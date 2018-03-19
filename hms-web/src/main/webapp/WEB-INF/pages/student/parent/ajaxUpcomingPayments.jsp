<%@ include file="/common/taglibs.jsp"%>
<div class="row">
<div class="col-md-12">
	<s:if test="%{studentsList != null && !studentsList.isEmpty()}">
		<s:if test="%{studentsList.size > 1}">
			<div class="form-group form-horizontal">
				<label class="control-label col-md-3">
					Student Name :
				</label>
				<div class="col-md-5">
					<s:select id="SectionIdAcademicYearIdAndCustId" list="studentsList"
						listKey="accountIdClassSectionIdAcademicYearIdAndCustId" cssClass="form-control"
						listValue="studentNameAndUserName" name="anyId" theme="simple"
						onchange="javascript:getStudentUpcomingFeeDetails(this.value);" />
				</div>
			</div>
			<div class="spaceDiv">&nbsp;</div>
			<div class="spaceDiv"></div>
		</s:if>
	</s:if>
	<div id="upcomingFeeContent">
	<jsp:include
		page="/WEB-INF/pages/student/parent/ajaxUpcomingPaymentDetails.jsp"></jsp:include>
</div>
</div>
</div>
<script type="text/javascript">
$(document).ready(function() {
	var tempString=$("#SectionIdAcademicYearIdAndCustId option:selected").val();
      	if(isNonEmpty(tempString)){
      		getStudentUpcomingFeeDetails(tempString);
      	}else{
      		getStudentUpcomingFeeDetails('');
      	}
     });  		
function getStudentUpcomingFeeDetails(tempString) {
	var url;
	if (isNonEmpty(tempString)) {
		url = jQuery.url.getChatURL("/student/ajaxGetMyChildFeesDetails.do?tempString="+ tempString);
	} else {
		url = jQuery.url.getChatURL("/student/ajaxGetMyChildFeesDetails.do");
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