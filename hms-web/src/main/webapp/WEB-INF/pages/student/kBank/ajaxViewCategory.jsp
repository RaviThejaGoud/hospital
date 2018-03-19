<%@ include file="/common/taglibs.jsp"%>
<jsp:include page="/common/messages.jsp"></jsp:include>
<div class="grid_10">
	<div class="grid_3">
		<label>
			<b>Select Quiz Category:</b>
		</label>
	</div>
	<div class="grid_7">
		<s:select list="quizList" listKey="id" listValue="title"
			cssClass="required" required="true" name="quizId" headerKey=""
			headerValue="- Select -" theme="simple"
			onchange="javascript:getAjaxDoCategoryQuestion(this.value);">
		</s:select>
	</div>
	<div class="grid_7">&nbsp;</div>
	<div id="staffCategoryQuestions"></div>
</div>