<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/ems.js"></script>
	<s:if test="%{'Section Exit' == anyTitle}">
		<%@ include file="/common/messages.jsp"%>
			<s:url id="doAddNewExamTypeList" action="ajaxDoManageClassSections"
				includeParams="all" escapeAmp="false">
			</s:url>
			<sj:a href="%{doAddNewExamTypeList}" 
				indicator="indicator" targets="mainContentDiv">Click here</sj:a> to view Class & Sections.
	</s:if>
	<s:else>
	<div class="alert alert-info">
			Class details added successfully.
			<!--<a
				href='${pageContext.request.contextPath}/admin/doGenerateSubjectSyllabus.do?classId=<s:property value="studyClass.id"/>'>Download Syllabus Template</a>
			-->
		<br><s:url id="doAddNewExamTypeList"
					action="ajaxDoManageClassSections" includeParams="all"
					escapeAmp="false" namespace="/admin">
				</s:url>
				<sj:a href="%{doAddNewExamTypeList}" cssClass="btn green default"
					indicator="indicator" targets="mainContentDiv">I am done!</sj:a>
					</div>
		<div class="grid_13"><!--
			<s:form action="ajaxAddClassSyllabus" theme="css_xhtml"
				id="addClassSyllabus" enctype="multipart/form-data" method="post">
				<fieldset class="labelRight">
					<s:hidden name="classId" value="%{studyClass.id}" />
					<s:hidden name="classNameClassId"
						value="%{studyClass.classNameClassId.id}" />
					<div class="grid_7">
						<div class="grid_3">
							<label>
								Class Name:
							</label>
						</div>
						<div class="grid_4">
							<s:property value="studyClass.className" />
							<s:select name="classId"
									cssClass="select small required" required="true"
									list="studyClassList" listKey="id" listValue="classAndSection" cssStyle="width:200px;"
									headerKey="" headerValue="- Select -" /> 
						</div>
					</div>
					<div class="grid_7">
						<div class="grid_3">
							<label>
								Upload Syllabus:
							</label>
						</div>
						<div class="grid_4">
							<s:file name="upload" theme="simple"></s:file>
						</div>
					</div>
					<div class="grid_7">
						<sj:submit   cssClass="submit small" value="Finish"
							indicator="indicator" targets="stepClassSections"
							onClickTopics="studentFormValidation" formIds="addClassSyllabus" />
						<s:url id="doCancelStudent" action="ajaxDocancelStudent"
								includeParams="all"></s:url>
							<sj:a href="%{doCancelStudent}" cssClass="cancelButton"
								indicator="indicator" targets="studentsList" button="false">Cancel</sj:a>
							<img style="display: none; padding-top: 18px;" alt="Loading..."
								src="${pageContext.request.contextPath}/images/indicator.gif"
								id="indicator">
					</div>
				</fieldset>
			</s:form>
		--></div>
		</s:else>
<script type="text/javascript">
function onClassChange(serviceId) {
	var metricsAdminURL = jQuery.url
			.getChatURL("/admin/ajaxGetSyllabusSubjects.do");
	var id = serviceId;
	var pars = "classId=" + id;
	$.ajax( {
		type : "POST",
		url : metricsAdminURL,
		data : pars,
		cache : false,
		success : function(html) {
			$("#syllabusDetails").html(html);
			$("#syllabusDetails").show();

		}

	});
}
$(document).ready(function() {
	$.subscribe('studentFormValidation', function(event, data) {
		if ($('#addClassSyllabus').valid())
			return true;
		else
			return false;
	});
});
</script>
