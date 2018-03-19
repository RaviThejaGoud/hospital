<%@ include file="/common/taglibs.jsp"%>
<jsp:include page="/common/messages.jsp"></jsp:include>
<div class="row">
	<div class="col-md-12">
		<div class="inbox-header">
			<h4 class="pull-left">View Messages</h4>
		</div>
		<div class="inbox-content">
			<div class="inbox-view-info row">
				<div class="col-md-7">
					To &nbsp; <img src="../img/avatar.png"> <span> <s:property
							value="viewAllUsers.username" />&nbsp;(<s:property
							value="viewAllUsers.roleDescription" />)
					</span>
				</div>
				<div class="col-md-5 inbox-info-btn">
					<div class="btn-group">
						<button class="btn blue reply-btn">
							<i class="fa fa-reply"></i> Reply
						</button>
						<s:url id="scrapMessages" action="ajaxDoInboxGetScrapMessagesList"
							namespace="/common" />
						<sj:a href="%{scrapMessages}" targets="mainContentDiv"
							cssClass="btn default btn-xs" cssStyle="margin:6px 0px 0px 6px;">
							<i class=" fa fa-arrow-circle-o-left "></i> &nbsp;Back To Inbox</sj:a>
					</div>
				</div>
			</div>
			<div id="replayTargetDiv">
				<div class="inbox-view">
					<s:if
						test="{replyScrapMessageSet != NULL & !replyScrapMessageSet.isEmpty()}">
						<table class="table table-striped table-advance table-hover">
							<thead>
							</thead>
							<tbody>
								<s:iterator value="replyScrapMessageSet">
									<tr>
										<td><s:if
												test='%{senderAccount.profileImage.adjustedAttachmentFilePath  != NULL & !senderAccount.profileImage.adjustedAttachmentFilePath.isEmpty()}'>
												<img
													src='<c:url value="${senderAccount.profileImage.adjustedAttachmentFilePath}"/>'
													alt='<s:property  value="senderAccount.person.firstName" />'
													style="height:40px;width:40px;" border="0" />
											</s:if> <s:else>
												<img src="../img/Student-icon.png"
													style="height:30px; width: 30px;">
											</s:else></td>
										<td class="col-md-8"><b><s:property value="createdBy" />
										</b> <a href="#" class="reply-btn col-md-8"><s:property
													value="scrapDescription" /></a></td>
										<td><s:property value="scrapMessageDateStr" /></td>
									</tr>
								</s:iterator>
							</tbody>
						</table>
					</s:if>
				</div>
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
									<label class="control-label col-md-1"> <span
										class="required">*</span>Reply:
									</label>
									<div class="controls col-md-8">
										<sj:textarea name="replyScrapMessage.scrapDescription"
											value="" id="messageDescription"
											cssClass="required form-control" rows="2" cols="40"></sj:textarea>
									</div>
								</div>
								<div class="col-md-2">
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
	</div>
</div>
<script type="text/javascript">
	changePageTitle('Read More Student Message');
	document.title = 'SMS | View Student Messages';
	$(".reply-btn").click(function() {
		$('#replayMegDiv').show();
	});
	$(document).ready(function() {
		$.subscribe('formValidationForStudentMessages', function(event, data) {
			//event.originalEvent.options.submit=false;
		});
	});
</script>
