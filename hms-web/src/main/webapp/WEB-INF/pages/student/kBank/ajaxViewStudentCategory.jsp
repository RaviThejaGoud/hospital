<%@ include file="/common/taglibs.jsp"%>
<div class="grid_10 commonFormTabs">
	<div class="grid_4">
		<label>
			<b>Select Quiz Category Results:</b>
		</label>
	</div>
	<div class="grid_6">
		<s:select list="quizList" listKey="id" listValue="title"
			cssClass="required" required="true" name="quizId" headerKey=""
			headerValue="- Select -" theme="simple"
			onchange="javascript:getAjaxDoCategoryQuestion(this.value);">
		</s:select>
	</div>
</div>
<div class="grid_6"></div>
	<div id="studentQuestionAnswers"></div>
<script type="text/javascript">
changePageTitle('Student Quiz Results');

$(document).ready(function() {
	var validator;
	$.subscribe('formValidationForLeavesForStudent', function(event, data) {
		if ($('#addLeaveReportForStudent').valid())
			return true;
		else
			return false;
	});
});
function getAjaxDoCategoryQuestion(quizId) {
	var pars = "quizId=" + quizId;
	$("#studentQuestionAnswers")
			.html(
					'<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
	var url = jQuery.url.getChatURL("/student/ajaxGetStudentQuestionAnswer.do");
	$.ajax( {
		url : url,
		cache : false,
		data : pars,
		success : function(html) {
			$("#studentQuestionAnswers").html(html);
		}
	});
}
</script>