<%@ include file="/common/taglibs.jsp"%>
<s:form id="addNewQuizQuestion1" action="ajaxAddQuizQuestion"
	namespace="/staff" method="post" theme="simple"
	cssClass="form-horizontal">
	<div class="form-body">
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4">
						<span class="required">*</span>Select Category :
					</label>
					<div class="col-md-5">
						<s:select list="quizList" listKey="id" listValue="title"
							cssClass="required form-control input-medium" name="tempId"
							headerKey="" headerValue="- Select -" theme="simple">
						</s:select>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4">
						Quiz Question :
					</label>
					<div class="col-md-6">
						<sj:textarea rows="3" cols="30" id="quizQuestion" name="bankName"
							cssClass="form-control">
						</sj:textarea>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4">
						<span class="required">*</span>Start Date :
					</label>
					<div class="col-md-5">
						<div data-date-start-date="+0d" data-date-format="mm/dd/yyyy"
							class="input-group input-medium date date-picker">
							<input id="sDate" name="startDate" readonly=""
								onchange="verifyQuizDate();" type="text"
								class="required form-control">
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
							<input id="eDate" name="endDate" readonly=""
								onchange="verifyQuizDate();" type="text"
								class="required form-control">
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
						Answers
					</label>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4">
						<span class="required">*</span>A :
					</label>
					<div class="col-md-5">
						<sj:textfield name="selectedId" id="quizA" maxlength="120"
							size="5" cssClass="required numeric form-control input-medium" />
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4">
						<span class="required">*</span>B :
					</label>
					<div class="col-md-5">
						<sj:textfield name="tempString" id="quizB" maxlength="120"
							size="5" cssClass="required numeric form-control input-medium" />
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4">
						<span class="required">*</span>C :
					</label>
					<div class="col-md-5">
						<sj:textfield name="balance" id="quizC" maxlength="120" size="5"
							cssClass="required numeric form-control input-medium" />
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4">
						<span class="required">*</span>D :
					</label>
					<div class="col-md-5">
						<sj:textfield name="subject" id="quizD" maxlength="120" size="5"
							cssClass="required numeric form-control input-medium" />
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4">
						Correct Answers :
					</label>
					<div class="col-md-5">
						<input type="radio" value="A" name="anyTitle" checked>
						A&nbsp;&nbsp;
						<input type="radio" value="B" name="anyTitle" />
						B&nbsp;&nbsp;
						<input type="radio" value="C" name="anyTitle">
						C&nbsp;&nbsp;
						<input type="radio" value="D" name="anyTitle" />
						D&nbsp;&nbsp;
					</div>
				</div>
			</div>
		</div>
		<div class="form-actions fluid">
			<div class="row">
				<div class="col-md-6">
					<div class="col-md-offset-4 col-md-9">
						<sj:submit   targets="quizResultsDiv" value="Submit"
							formIds="addNewQuizQuestion1" cssClass="submitBt btn blue"
							validate="true" onBeforeTopics="quizQuestionFormValidation" />
						<s:url id="cancelQuestionUrl" action="ajaxDoGetCreateQuiz"
							includeParams="all" escapeAmp="false" namespace="/staff">
						</s:url>
						<sj:a href="%{cancelQuestionUrl}" cssClass="btn default"
						  targets="mainContentDiv">Cancel</sj:a>
					</div>
				</div>
			</div>
		</div>
	</div>
</s:form>

<script type="text/javascript">
FormComponents.init();
$("input:checkbox, input:radio").uniform();
changePageTitle("Create New Quiz Question");
 
function verifyQuizDate() {
	var startDate = $('#sDate').val();
	var endDate = $('#eDate').val();
	if (isNonEmpty(endDate) && isNonEmpty(startDate)) {
		startDate = new Date(startDate);
		endDate = new Date(endDate);
		if (endDate < startDate) {
			$('#eDate').val('');
			alert("End date should be after start date.");
		}
	}
}
</script>