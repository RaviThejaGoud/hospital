<%@ include file="/common/taglibs.jsp"%>
<s:form id="addNewQuiz" action="ajaxAddQuiz" method="post"
	theme="simple" cssClass="form-horizontal" namespace="/staff">
	<div class="form-body">
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4">
						<span class="required">*</span>Quiz Title :
					</label>
					<div class="col-md-5">
						<sj:textfield name="title" id="quizName"
							cssClass="form-control required">
						</sj:textfield>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4">
						Quiz Description :
					</label>
					<div class="col-md-6">
						<sj:textarea rows="3" cols="30" name="description"
							id="quizDescription" cssClass="form-control">
						</sj:textarea>
					</div>
				</div>
			</div>
		</div>
		<div class="form-actions fluid">
			<div class="row">
				<div class="col-md-6">
					<div class="col-md-offset-4 col-md-9">
						<sj:submit   targets="quizResultsDiv" value="Submit" type="submit"
							cssClass="submitBt btn blue" formIds="addNewQuiz"
							indicator="indicator" onBeforeTopics="quizFormValidation" />
						<s:url id="cancelQuizUrl" action="ajaxDoGetCreateQuiz"
							includeParams="all" escapeAmp="false">
						</s:url>
						<sj:a href="%{cancelQuizUrl}" cssClass="btn default"
							targets="mainContentDiv">Cancel</sj:a>
					</div>
				</div>
			</div>
		</div>
	</div>
</s:form>

<script type="text/javascript">
changePageTitle("Create New Quiz");
$("#quizName").focus();
$.subscribe('quizFormValidation', function(event, data) {
	if ($('#addNewQuiz').valid()) {
	} else
		event.originalEvent.options.submit = false;
});
</script>