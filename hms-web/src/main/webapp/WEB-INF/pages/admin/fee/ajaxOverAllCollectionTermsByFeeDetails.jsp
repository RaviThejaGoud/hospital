<%@ include file="/common/taglibs.jsp"%>
<s:if test="%{schoolTermsList != null && !schoolTermsList.isEmpty()}">
	<h1>Available Terms</h1>
	<div class="grid_14 checkbox">
		<s:checkboxlist name="subjectName" list="schoolTermsList" listKey="id"
			listValue="name" theme="css_xhtml" cssClass="small"
			onclick="javascript:displaySubjectInfo(this.value);" />
	</div>
</s:if>
<s:else>
	You have not created any subjects. This is critical to operate Classes and Performance Evaluation. This is very simple process and system would guide you through. Just type subject in above text box and hit submit. 
</s:else>
<div id="subjectsContent" class="grid_14">
</div>

<script type="text/javascript">
function displaySubjectInfo(subjectId) {
	$('#subjectsContent')
			.html(
					'<img src="../../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">');
	$.ajax( {
		url : "ajaxViewSubjectDetails.do?subjectId=" + subjectId,
		cache : false,
		success : function(html) {
			$('#subjectsContent').html(html);
		}
	});
}
</script>