<%@ include file="/common/taglibs.jsp"%>
<div data-width="760" class="modal fade modal-overflow in"
	style="display: block; width: 760px; margin-left: -379px; margin-top: 100px;"
	aria-hidden="false">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true"></button>
		<h4 class="modal-title">
			View Comments
		</h4>
	</div>
	<div class="modal-body">
		<jsp:include page="/common/messages.jsp"></jsp:include>
		<div style="height: 300px; overflow-y: scroll;min-height: 200px">
		<s:if test="%{tempList2 != null && !tempList2.isEmpty()}">
				<table class="table table-striped table-advance table-hover">
					<thead>
						<tr>
							<th>
								Name
							</th>
							<th style="width: 370px;">
								Comment
							</th>
							<th>
								Date
							</th>
						</tr>
					</thead>
					<tbody>
						<s:iterator value="tempList2">
							<tr>
								<td>
									<s:if
										test='%{adjustedAttachmentFilePath  != NULL && !adjustedAttachmentFilePath.isEmpty()}'>
										<img src='<c:url value="${adjustedAttachmentFilePath}"/>'
											style="height: 30px; width: 40px;" border="0" />
										<s:property value="firstName" />
									</s:if>
									<s:else>
										<img src="../img/Student-icon.png"
											style="height: 30px; width: 30px;">
									</s:else>
								</td>
								<td>
									<s:property value="messageContent" />
								</td>
								<td>
									<s:property value="commentsDateStr" />
								</td>
							</tr>
						</s:iterator>
					</tbody>
				</table>
			</s:if>
			<s:else>
				<div class="alert alert-info">
					No comments available
				</div>
			</s:else>
		</div>
		<div id="replayTargetDiv">
		<hr>
			<s:if test='%{#session.previousYear=="N"}'>
				<s:form action="ajaxAddCommentsSyllebusPlanner" method="post"
					theme="simple" id="formMessages" namespace="/staff">
					<s:hidden name="tempId"></s:hidden>
					<s:hidden name="tempId1"></s:hidden>
					<s:hidden name="tempId2"></s:hidden>
					<s:hidden name="selectedId"></s:hidden>					
					<div class="col-md-12">
						<div class="control-group">
							<label class="control-label col-md-1">
								<span class="required">*</span>Reply:
							</label>
							<div class="col-md-8">
								<sj:textarea name="syllabusPlannerComments.messageContent"
									value="" id="messageDescription"
									cssClass="required form-control word_count" rows="2" cols="40" maxCharsData="2000"></sj:textarea>
									<div class="counter"></div>
							</div>
						</div>
						<div class="col-md-2">
							<img src="../img/bg/bigWaiting.gif" alt="Loading..."
								title="Loading..." id="indicator"
								style="display: none; background-repeat: no-repeat; position: relative; z-index: 1000; bottom: 200px; margin-left: 240px;" />
							<sj:submit cssClass="submitBt btn blue" value="Send"
								targets="subjectPlannerViewDiv" formIds="formMessages"
								onBeforeTopics="formValidationForComments" validate="true" />
						</div>
					</div>
				</s:form>
			</s:if>
		</div>
	</div>
</div>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/subscription/textcounter.js">
</script>
<script type="text/javascript">
changePageTitle('Read More Student Message');
document.title = 'View Comments';
$.subscribe('formValidationForComments', function(event, data) {
	if ($('#formMessages').valid())
		$('button.close').click();
	else
		event.originalEvent.options.submit = false;
});
</script>
