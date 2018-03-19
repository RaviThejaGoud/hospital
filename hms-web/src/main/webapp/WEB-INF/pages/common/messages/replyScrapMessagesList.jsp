<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/messages.jsp"%>
<div class="row">
	<div class="col-md-12">
		<s:if
			test="{replyScrapMessageSet != NULL & !replyScrapMessageSet.isEmpty()}">
			<table class="table table-striped table-advance table-hover">
				<thead>
				</thead>
				<tbody>
					<s:iterator value="replyScrapMessageSet">
						<tr>
							<td>
								<s:if
									test='%{senderAccount.profileImage.adjustedAttachmentFilePath  != NULL & !senderAccount.profileImage.adjustedAttachmentFilePath.isEmpty()}'>
									<img
										src='<c:url value="${senderAccount.profileImage.adjustedAttachmentFilePath}"/>'
										alt='<s:property  value="senderAccount.person.firstName" />'
										style="height: 30px; width: 30px;" border="0" />
								</s:if>
								<s:else>
									<img src="../img/Student-icon.png" style="height: 30px; width: 30px;">
								</s:else>
							</td>
							<td class="col-md-8">
								<b><s:property value="createdBy" /> </b>
								<a href="#" class="reply-btn col-md-8"><s:property value="scrapDescription" /></a>
							</td>
							<td>
								<s:property value="scrapMessageDateStr" />
							</td>
						</tr>
					</s:iterator>
				</tbody>
			</table>
		</s:if>
		<hr>
		<div id="replayMegDiv" style="display: none;">
			<s:if test='%{#session.previousYear=="N"}'>
				<s:form action="ajaxReplyNewScrapMessage" method="post"
					theme="simple" id="formStudentMessages" namespace="/common">
					<s:hidden name="scrapMessage.id"></s:hidden>
					<s:hidden name="scrapMessage.senderAccount.id"></s:hidden>
					<s:hidden name="scrapMessage.receiverAccount.id"></s:hidden>
					<div class="col-md-12">
						<div class="control-group">
							<label class="control-label col-md-1">
								<span class="required">*</span>Reply:
							</label>
							<div class="controls col-md-8">
								<sj:textarea name="replyScrapMessage.scrapDescription" value=""
									id="messageDescription" cssClass="required form-control"
									rows="2" cols="40"></sj:textarea>
							</div>
						</div>
						<div class="col-md-2">
							<img src="../img/bg/bigWaiting.gif" alt="Loading..."
								title="Loading..." id="indicator"
								style="display: none; background-repeat: no-repeat; position: relative; z-index: 1000; bottom: 200px; margin-left: 240px;" />
							<sj:submit cssClass="submitBt btn blue" value="Send"
								targets="replayTargetDiv" formIds="formStudentMessages"
								onBeforeTopics="formValidationForStudentMessages"
								validate="true" />
						</div>
					</div>
				</s:form>
			</s:if>
		</div>
	</div>
</div>
<script type="text/javascript">
changePageTitle('Read More Student Message');
$( ".reply-btn" ).click(function() {
	$('#replayMegDiv').show();
});
</script>