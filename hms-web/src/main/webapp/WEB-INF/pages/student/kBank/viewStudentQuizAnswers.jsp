<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>
<jsp:include page="/common/messages.jsp"></jsp:include>
<div class="grid_12 commomnTabs">
	<div id="commonStep">
		<fieldset>
			<s:if
				test="%{studentQuestionAnswerList !=null && !studentQuestionAnswerList.isEmpty()}">
				<div class="grid_12 th">
					<div class="grid_4">
						Q No
					</div>
					<div class="grid_4">
						Question Name
					</div>
				</div>
				<div id="resultsPage">
					<%int i = 0; %>
					<s:iterator value="studentQuestionAnswerList">
						<div class="grid_12 row">
							<div class="grid_1">
								<%i++; %><%=i %>
							</div>
							<div class="grid_11">
								<span class="noteMassage"><b style=""><s:property value="questionName" /> </b></span>
							</div>
							<div class="grid_10">
								<div class="grid_5">
									<b>Student Answer:</b>
								</div>
								<div class="grid_5">
									<s:property value="studentAnswer" />
								</div>
							</div>
							<div class="grid_10">
								<div class="grid_5">
									<b>Correct Answer:</b>
								</div>
								<div class="grid_5">
									<s:property value="correctAnswer" />
								</div>
							</div>
							<div class="grid_10">
								<div class="grid_5">
									<b>No of student Correct Answer:</b>
								</div>
								<div class="grid_5">
									<s:property value="studentCorrectAnswer" />
								</div>
							</div>
							<div class="grid_10">
								<div class="grid_5">
									<b>No of student wrong Answer:</b>
								</div>
								<div class="grid_5">
									<s:property value="questionId" />
								</div>
							</div>
						</div>
					</s:iterator>
				</div>
			</s:if>
			<s:else>
				<div class="grid_12 thb">
					Currently there is no category question answers
				</div>
			</s:else>
		</fieldset>
	</div>
</div>

<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/subscription/paginator.js">
</script>
<script type="text/javascript">
$(function() {
	$("#resultsPage").pagination();
});
</script>
