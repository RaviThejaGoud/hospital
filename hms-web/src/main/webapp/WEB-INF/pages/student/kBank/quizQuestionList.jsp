<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>
<jsp:include page="/common/messages.jsp"></jsp:include>
<div class="grid_13">
	<s:form action="ajaxStudentQuizQuestionAnswer" theme="css_xhtml"
		id="editStudentQuizQuestionAnswer">
		<s:if
			test="%{categoryQuestionList !=null && !categoryQuestionList.isEmpty()}">
			<s:hidden name="quizId" value="%{quizId}" />
			<div class="grid_12" style="width: 727px">
				<div id="commonStep">
					<fieldset>
						<div class="grid_12 th" id="results">
							<div class="grid_2">
								Q No
							</div>
							<div class="grid_10">
								Question Name
							</div>
						</div>
						<div id="resultsPage">
							<%int i = 0; %>
							<s:iterator value="categoryQuestionList">
								<div class="grid_12 row" id="results">
									<s:hidden name="questionAnswerId" value="%{id}"></s:hidden>
									<s:set name="questionName" value="questionName"></s:set>
									<div class="loaded">
										<div class="grid_2">
											<%i++; %><%=i %>
										</div>
										<div class="grid_10">
											<span class="noteMassage"><s:property value="questionName" /> </span>
										</div>
										<div class="grid_2">
											<label>
												<b>Answers :</b>
											</label>
										</div>
										<div class="grid_10">
											<s:if
												test="%{questionAnswerList !=null && !questionAnswerList.isEmpty()}">
												<s:iterator value="questionAnswerList">
													<s:if test="%{questionName == #questionName}">
														<s:hidden name="anserOptions%{id}" value="%{anserOptions}"></s:hidden>
														<input type="radio"
															value="<s:property value="questionAnswer" />"
															name="correctAnswer<s:property value="questionId" />"
															id="correctAnswer<s:property value="id" />" />
															<b><s:property value="anserOptions" />)&nbsp;</b>
															<s:property value="questionAnswer" />
													 </s:if>&nbsp;&nbsp;&nbsp;&nbsp;
												</s:iterator>
											</s:if>
										</div>
									</div>
									</div>
							</s:iterator>
							<div class="grid_12">
							<div class="grid_11">&nbsp;</div>
								<s:url id="cancelQuestionUrl" action="ajaxCancelQuiz"
									includeParams="all" escapeAmp="false">
								</s:url>
								<sj:a href="%{cancelQuestionUrl}" cssClass="cancelButton"
									indicator="indicator" targets="myKVideosContent" button="false"
									buttonIcon="ui-icon-plus">Cancel</sj:a>
								<sj:submit   cssClass="submit small" value="Submit"
									targets="myKVideosContent"
									onClickTopics="staffEditFormValidation" />
							</div>
						</div>
					</fieldset>
				</div>
			</div>
		</s:if>
		<s:else>
			<div class="grid_12 thb">
				Currently there is no category quiz.
			</div>
		</s:else>
	</s:form>
</div>
<script type="text/javascript">
$(document).ready(function() {
	$.subscribe('staffEditFormValidation', function(event, data) {
		if ($('#editStudentQuizQuestionAnswer').valid())
			return true;
		else
			return false;
	});
	$('.numeric').numeric();
	$('.alphabet').alpha();
});
</script>