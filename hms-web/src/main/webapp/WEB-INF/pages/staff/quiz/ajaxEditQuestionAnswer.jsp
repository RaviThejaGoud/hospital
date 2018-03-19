<%@ include file="/common/taglibs.jsp"%>
<div data-width="760" class="modal fade modal-overflow in"
	style="display: block; width: 800px; margin-left: -379px; margin-top: 100px;">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true"></button>
		<h4 class="modal-title">
			Edit Quiz
		</h4>
	</div>
	<div class="modal-body">
		<div class="form-body">
			<s:if test="%{quizQuestion.answerList != null}">
				<s:form action="ajaxEditQuizQuestion" theme="simple"
					id="editQuizQuestion" method="post" cssClass="form-horizontal" namespace="/staff">
					<s:hidden name="quizQuestion.id" />
					<s:hidden name="quizQuestion.quizId" />

					<div class="row">
						<div class="col-md-6">
							<div class="form-group">
								<label class="control-label col-md-4">
									<span class="required">*</span>Start Date :
								</label>
								<div class="col-md-5">
									<div data-date-start-date="+0d" data-date-format="mm/dd/yyyy"
										class="input-group input-medium date date-picker">
										<input id="date0" name="startDate" readonly=""
											onchange="verifyQuizDate();" type="text"
											class="required form-control fdate"
											value='<s:property value="quizQuestion.startDateStr"/>'>
										<span class="input-group-btn">
											<button type="button" class="btn default">
												<i class="fa fa-calendar"></i>
											</button> </span>
									</div>
									<span class="help-block">(MM/DD/YYYY)</span>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label class="control-label col-md-4">
									<span class="required">*</span>End Date :
								</label>
								<div class="col-md-5">
									<div data-date-start-date="+0d" data-date-format="mm/dd/yyyy"
										class="input-group input-medium date date-picker">
										<input id="date1" name="endDate" readonly=""
											onchange="verifyQuizDate();"
											value='<s:property value="quizQuestion.endDateStr"/>'
											type="text" class="required form-control fdate">
										<span class="input-group-btn">
											<button type="button" class="btn default">
												<i class="fa fa-calendar"></i>
											</button> </span>
									</div>
									<span class="help-block">(MM/DD/YYYY)</span>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-6">
							<div class="form-group">
								<label class="control-label col-md-4">
									Quiz Question :
								</label>
								<div class="col-md-6">
									<sj:textarea rows="2" cols="30" id="quizQuestion"
										name="quizQuestion.questionName" cssClass="form-control"
										value="%{quizQuestion.questionName}">
									</sj:textarea>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-6">
							<div class="form-group">
								<label class="control-label col-md-4">
									Answers
								</label>
							</div>
						</div>
					</div>
					<div class="row">
						<s:iterator value="quizQuestion.answerList">
							<s:hidden name="questionAnswerId" value="%{id}"></s:hidden>
							<s:if test='%{correctAnswer == "Y"}'>
								<s:set name="questionAns" value="correctAnswer"></s:set>
								<s:set name="anserOps" value="anserOptions"></s:set>
							</s:if>
							<div class="col-md-6">
								<div class="form-group">
									<s:hidden name="anserOptions%{id}" value="%{anserOptions}"></s:hidden>
									<label class="control-label col-md-4">
										<s:property value="%{anserOptions}" />
										:
									</label>
									<div class="col-md-5">
										<sj:textfield name="questionAnswer%{anserOptions}"
											id="quiz%{anserOptions}"
											cssClass="required numeric form-control input-medium"
											maxlength="40" value="%{questionAnswer}"></sj:textfield>
									</div>
								</div>
							</div>
						</s:iterator>
					</div>
					<div class="row">
						<div class="col-md-6">
							<div class="form-group">
								<label class="control-label col-md-4">
									Correct Answers :
								</label>
								<div class="col-md-5">
									<s:if test='%{ #questionAns == "Y" && #anserOps =="A"}'>
										<input type="radio" value="A" name="anyTitle"
											checked="checked">A&nbsp;&nbsp;
						</s:if>
									<s:else>
										<input type="radio" value="A" name="anyTitle">A&nbsp;&nbsp;
						</s:else>
									<s:if test='%{ #questionAns == "Y" && #anserOps =="B"}'>
										<input type="radio" value="B" name="anyTitle"
											checked="checked">B&nbsp;&nbsp;
						</s:if>
									<s:else>
										<input type="radio" value="B" name="anyTitle">B&nbsp;&nbsp;
						</s:else>
									<s:if test='%{ #questionAns == "Y" && #anserOps =="C"}'>
										<input type="radio" value="C" name="anyTitle"
											checked="checked">C&nbsp;&nbsp; 
						</s:if>
									<s:else>
										<input type="radio" value="C" name="anyTitle">C&nbsp;&nbsp;
						</s:else>
									<s:if test='%{ #questionAns == "Y" && #anserOps =="D"}'>
										<input type="radio" value="D" name="anyTitle"
											checked="checked">D&nbsp;&nbsp;
						</s:if>
									<s:else>
										<input type="radio" value="D" name="anyTitle">D&nbsp;&nbsp;
						</s:else>
								</div>
							</div>
						</div>
					</div>
					<div class="form-actions fluid">
						<div class="col-md-offset-2 col-md-9">
							<sj:submit   targets="staffCategoryQuestions" value="Submit"
								formIds="editQuizQuestion" cssClass="submitBt btn blue"
								validate="true" onBeforeTopics="editQuestionFormValidation" />
							<s:url id="cancelQuestionUrl" action="ajaxEditCancelQuestion"
								includeParams="all" escapeAmp="false">
							</s:url>
							<button type="button" data-dismiss="modal" class="btn default">
								Cancel
							</button>
						</div>
					</div>
				</s:form>
			</s:if>
		</div>
	</div>
</div>
<script type="text/javascript">
changePageTitle("Create New Quiz Question");
FormComponents.init();
UIExtendedModals.init();
$("input:checkbox, input:radio").uniform();
$.subscribe('editQuestionFormValidation', function(event, data) {
	if ($('#editQuizQuestion').valid()) {
		$('button.close').click();
	} else {
		event.originalEvent.options.submit = false;
	}
});
function verifyQuizDate() {
	var startDate = $('#date0').val();
	var endDate = $('#date1').val();
	if (isNonEmpty(endDate) && isNonEmpty(startDate)) {
		startDate = new Date(startDate);
		endDate = new Date(endDate);
		if (endDate < startDate) {
			$('#date1').val('');
			alert("End date should be after start date.");
		}
	}
}
</script>