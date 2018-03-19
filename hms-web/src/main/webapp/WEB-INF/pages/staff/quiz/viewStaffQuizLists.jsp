<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/messages.jsp"%>
<div class="row">
	<div class="col-md-6">
		<div class="form-group">
			<label class="control-label col-md-5">
				<span class="required">*</span>Select Quiz Category :
			</label>
			<div class="col-md-5">
				<s:select list="quizList" listKey="id" listValue="title"
					cssClass="required form-control input-medium" name="quizId"
					headerKey="" headerValue="- Select -" theme="simple"
					onchange="javascript:getAjaxDoCategoryQuestion(this.value);">
				</s:select>

			</div>
		</div>
	</div>
</div>
<div class="spaceDiv"></div>
<div id="staffCategoryQuestions"></div>
<script type="text/javascript">
$(document).ready(function() {
changePageTitle('View Leaves');
$('ul.nav-tabs li').removeClass('active');
$('li#urlDoAddNewQuiz').addClass('active');
});
function getAjaxDoCategoryQuestion(quizId) {
	var pars = "anyId=" + quizId;
	$("#staffCategoryQuestions")
			.html(
					'<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
	var url = jQuery.url.getChatURL("/staff/ajaxDoGetCategoryQuestion.do");
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