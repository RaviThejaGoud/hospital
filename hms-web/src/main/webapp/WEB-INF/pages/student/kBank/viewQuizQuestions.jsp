<%@ include file="/common/taglibs.jsp"%>
<div class="grid_14 omega block">
	<div class="block_head">
		<h2>
			Quiz
		</h2>
		<div id="topMenu">
			<ul>
				<li>
					<s:url id="urlStudentQuizResultsLink" action="ajaxDoStudentQuizResults" />
					<sj:a href="%{urlStudentQuizResultsLink}" targets="myKVideosContent"
						indicator="indicator">Results</sj:a>
				</li>
			</ul>
		</div>
	</div>

	<div class="block_content" id="myKVideosContent">
		<jsp:include page="/WEB-INF/pages/student/kBank/ajaxViewCategory.jsp"></jsp:include>
	</div>
</div>
<script type="text/javascript">
changePageTitle('Student Quiz');

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
	$("#staffCategoryQuestions")
			.html(
					'<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
	var url = jQuery.url.getChatURL("/student/ajaxDoGetCategoryQuestion.do");
	$.ajax( {
		url : url,
		cache : false,
		data : pars,
		success : function(html) {
			$("#staffCategoryQuestions").html(html);
		}
	});
}
</script>