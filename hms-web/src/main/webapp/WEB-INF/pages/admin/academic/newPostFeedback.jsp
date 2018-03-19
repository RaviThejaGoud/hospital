<%@ include file="/common/taglibs.jsp"%>
<s:if test="%{feedbackQuestions.id != 0}">
	<div data-width="760" class="modal fade modal-overflow in"
		style="display: block; width: 760px; margin-left: -379px; margin-top: 200px;"
		aria-hidden="false">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true"></button>
			<h4 class="modal-title">
				Update Feedback 
			</h4>
		</div>
		<div class="modal-body">
</s:if>
<s:form id="addNewFeedbackQuizQuestion" action="ajaxAddFeedbackQuestion"
	method="post" theme="simple" cssClass="form-horizontal" namespace="/admin">
	<s:hidden name="tempId" value="%{feedbackQuestions.id}"></s:hidden>
	<s:param name="tempString">Teacher Feedback Questions</s:param>
	<div class="form-body">
		<div class="form-group">
			<label class="col-md-3 control-label">
				Is feedback for staff :
			</label>
			<div class="col-md-7 feedCheck">
				<div class="checkbox-list">
					<label class="checkbox-inline">
						<s:if test="%{feedbackQuestions.roleDescription == 'Teacher'}">
							<input type="checkbox" id="feedbackForStaff" tabindex="2" class="checkbox"
							  name="feedbackQuestions.roleDescription"
								value="Teacher">
						</s:if>
						<s:else>
							<input type="checkbox" id="feedbackForStaff" tabindex="2" class="checkbox"
								name="feedbackQuestions.roleDescription" value="Teacher">
						</s:else>
					</label>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-md-3">
				<span class="required">*</span>Feedback Question :
			</label>
			<div class="col-md-7">
				<sj:textarea name="feedbackQuestions.description" id="quizQuestion"
					cssClass="form-control required"></sj:textarea>
			</div>
		</div>
		<div class="form-actions fluid">
			<div class="col-md-offset-3 col-md-">
				<sj:submit   targets="feedbackContent" value="Submit"
					onBeforeTopics="feedFormValidation" cssClass="submitBt btn blue"
					formIds="addNewFeedbackQuizQuestion" validate="true" />
				<s:if test="%{feedbackQuestions.id != 0}">
					<button type="button" data-dismiss="modal" class="btn default">
						Cancel
					</button>
				</s:if>
				<s:else>
					<s:url id="cancelQuestionUrl" action="ajaxViewFeedbackList"
						includeParams="all" escapeAmp="false">
						<s:param name="tempString">Teacher Feedback Questions</s:param>
					</s:url>
					<sj:a href="%{cancelQuestionUrl}" cssClass="default btn"
						targets="mainContentDiv" buttonIcon="ui-icon-plus">Cancel</sj:a>
				</s:else>
			</div>
		</div>
	</div>
</s:form>
<s:if test="%{feedbackQuestions.id != 0}">
	</div>
	</div>
</s:if>
<script type="text/javascript">
$(document).ready(function() {
	var feedCheck1='<s:property value="feedbackQuestions.roleDescription"/>';
	if(feedCheck1 == "Teacher Feedback Questions"){
		$('input.checkbox').parent('span').addClass('checked'); 
		$('input.checkbox').attr("checked",true);
	}	
		FormComponents.init();
		changePageTitle("Create New Feedback Question");
		$("input:checkbox, input:radio").uniform();
	});
$.subscribe('feedFormValidation', function(event, data) {
	$('button.close').click();
});
</script>
