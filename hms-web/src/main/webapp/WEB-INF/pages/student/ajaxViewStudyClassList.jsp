<%@ include file="/common/taglibs.jsp"%>
<s:form id="selectStudentForm" action="ajaxChangeStudentChangeSection" theme="simple" cssClass="form-horizontal" namespace="/student">
<jsp:include page="/common/messages.jsp" />
	<div class="form-group">
		<label class="col-md-2 control-label">
			Select Class :
		</label>
		<div class="col-md-7">
			<s:select list="studyClassList" id="className"
				cssClass="form-control input-medium"
				name="classSectionId" listKey="id"
				listValue="classAndSection" theme="simple" headerKey="" headerValue="- Select -"
				onchange="javascript:getAjaxDoGetStudent(this.value);">
			</s:select>
		</div>
	</div>
	
	<div id="searchStudentsList"></div>
</s:form>
<script type="text/javascript"	src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>
<script type="text/javascript">
changePageTitle("Change Class Section");
$(document).ready(function() {
	$("input:checkbox, input:radio").uniform();  
});

function getAjaxDoGetStudent(studyClassId)
{
	if(isNonEmpty(studyClassId))
	{
		var pars = "classId=" + studyClassId;
		$("#searchStudentsList").html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		var url = jQuery.url.getChatURL("/student/ajaxGetStudentDetailsForChangeClass.do");
		$.ajax( {
			url : url,
			cache : false,
			data : pars,
			success : function(html) {
				$("#searchStudentsList").html(html);
				$("#searchStudentsList").show();
			}
		});
	}
	else
	{
		$("#searchStudentsList").hide();
	}
		
}
</script>
