<%@ include file="/common/taglibs.jsp"%>
<s:if test="%{syllabusList != null && !syllabusList.isEmpty()}">
<div class="grid_12 checkbox">
		<s:checkboxlist name="chkBoxSelectedIds" list="syllabusList" listKey="id"
			listValue="chapterName" theme="css_xhtml" cssClass="small"
			onclick="javascript:displaySubjectInfo(this.value);" title="subjectSyllabuses%{subjectId}" />
	</div>
</s:if>
<s:else>
	No syllabus found for this subject.
</s:else>