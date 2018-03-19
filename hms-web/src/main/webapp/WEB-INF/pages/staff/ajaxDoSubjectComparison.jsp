<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/FusionCharts.js">
</script>
<div id="addSubjectFieldErrors">
</div>
<s:form action="ajaxSubjectPerformance" theme="simple"
	cssClass="form-horizontal" id="subjectPerformnce" namespace="/staff">
	<span id='<s:property value="staff.id"/>' class="staffId"></span>
	<span id='<s:property value="staff.account.id"/>'
		class="staffAccountId"></span>
	<s:if test="%{studyClassesMap != null & !studyClassesMap.isEmpty()}">
		<div class="form-group">
			<label class="control-label col-md-2">
				<span class="required">*</span>Select Class :
			</label>
			<div class="col-md-3">
				<s:select id="className" list="studyClassesMap" theme="simple"
					cssClass="form-control" label="Select Class" headerKey=""
					headerValue="- Select -" 
					onchange="javascript:getExamTypesAndSubjects(this.value);"
					name="classId" />
			</div>
		</div>
	</s:if>
	<s:else>
		<div class="alert alert-info">
			No subjects assigned for you.
		</div>
	</s:else>
	<div id="teacherSubjectsAndExamTypes">
	</div>
</s:form>
	<div id="subjectPerformanceAcrossSectionWise"> </div>
<script type="text/javascript">
$(document).ready(function() {
changePageTitle("My Subjects Comparison");
	var classId = $("#className").val();
	if (isNonEmpty(classId)) {
		getExamTypesAndSubjects(classId);
	}
});
function getExamTypesAndSubjects(classId) {
	if (isNonEmpty(classId)) {
		var pars = "className.id=" + classId;
		$("#teacherSubjectsAndExamTypes")
				.html(
						'<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		var url = jQuery.url
				.getChatURL("/staff/ajaxGetExamTypesAndStaffSubjects.do");
		$.ajax( {
			url : url,
			cache : false,
			data : pars,
			success : function(html) {
				$("#teacherSubjectsAndExamTypes").html(html);
			}
		});
	}
}
</script>
