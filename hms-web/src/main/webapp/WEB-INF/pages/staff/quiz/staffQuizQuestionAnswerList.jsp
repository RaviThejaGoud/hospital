<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>
<jsp:include page="/common/messages.jsp"></jsp:include>
<s:if test="%{categoryQuestionList != null && !categoryQuestionList.isEmpty()}">
	<table
		class="table table-striped table-bordered table-hover table-full-width"
		id="sample_2">
		<thead>
			<tr>
				<th>
					Q No
				</th>
				<th>
					Question Name
				</th>
			</tr>
		</thead>
		<tbody>
			<%int i = 0; %>
			<s:iterator value="categoryQuestionList">
				<tr>
					<s:set name="questionId" value="%{id}"></s:set>
					<td>
						<%i++; %><%=i %>
					</td>
					<td>
						<s:property value="questionName" />
					</td>
					<s:if
						test="%{studentQuestionAnswerList != null && !studentQuestionAnswerList.isEmpty()}">
						<s:iterator value="studentQuestionAnswerList">
							<s:if test="questionId == #questionId">
								<td>
									<b>No of student correct answer:</b>
									<s:property value="custId" />
								</td>
								<td>
									<b>No of student wrong answer:</b>
									<s:property value="studentId" />
								</td>
							</s:if>
						</s:iterator>
					</s:if>
				</tr>
			</s:iterator>
		</tbody>
	</table>
</s:if>
<s:else>
	<div class="alert alert-info">
		Questions are not created seleted category.
	</div>
</s:else>

<script type="text/javascript">
TableAdvanced.init();
$(document).ready(function() {
	$.subscribe('doInitEditQuestionAnswers', function(event, data) {
		if ($('#' + data.id).is(":hidden")) {
			$('#' + data.id).show();
		} else {
			$('#' + data.id).hide();
		}
	});
});
</script>