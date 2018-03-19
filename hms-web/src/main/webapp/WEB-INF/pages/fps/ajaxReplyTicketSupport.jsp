<%@ include file="/common/taglibs.jsp"%>
<jsp:include page="/common/messages.jsp"></jsp:include>
<div class="row">
	<div class="col-md-12">
		<div class="inbox-header">
			<h4 class="pull-left">View Tickets</h4>
		</div>
		<div class="inbox-content">
			<div class="inbox-view-info row">
				<div class="col-md-7">
					To &nbsp; <img src="../img/avatar.png"> <span> <s:property value="selectedId" />
					</span>
				</div>
				<div class="col-md-5 inbox-info-btn">
					<div class="btn-group">
						<button class="btn blue reply-btn">
							<i class="fa fa-reply"></i> Reply
						</button>
						<s:url id="viewTitckets" action="ajaxDoTicketForm"
							namespace="/fps" />
						<sj:a href="%{viewTitckets}" targets="mainContentDiv"
							cssClass="btn default btn-xs" cssStyle="margin:6px 0px 0px 6px;">
							<i class=" fa fa-arrow-circle-o-left "></i> &nbsp;Back To Tickets</sj:a>
					</div>
				</div>
			</div>
			<div id="replayTargetDiv">
				<div class="inbox-view">
					<s:if
						test="{replyScrapMessageSet != null}">
						<table class="table table-striped table-advance table-hover">
							<thead>
							</thead>
							<tbody>
								<s:iterator value="replyScrapMessageSet">
									<tr>
										<td>
											<!--<s:if
												test='%{senderAccount.profileImage.adjustedAttachmentFilePath  != NULL & !senderAccount.profileImage.adjustedAttachmentFilePath.isEmpty()}'>
												<img
													src='<c:url value="${senderAccount.profileImage.adjustedAttachmentFilePath}"/>'  
													style="height:40px;width:40px;" border="0" />
											</s:if>
											 <s:else>
												<img src="../img/Student-icon.png"
													style="height:30px; width: 30px;">
											</s:else>
										-->
										<img src="../img/Student-icon.png"
													style="height:30px; width: 30px;">
										</td>
										<td class="col-md-8"><b><s:property value="createdBy" />
										</b> <a href="#" class="reply-btn col-md-8"><s:property
													value="requestDescription" /></a></td>
										<td><s:property value="requestedDateStr" /></td>
									</tr>
								</s:iterator>
							</tbody>
						</table>
					</s:if>
				</div>
				<hr>
				<div id="replayMegDiv" style="display: none;">
						<s:form action="ajaxReplyNewTicket" method="post"
							theme="simple" id="formStudentMessages" namespace="/fps">
							<s:hidden name="fpsDealerSupport.id" value="%{fpsDealerSupport.id}"></s:hidden>
							<s:hidden name="fpsDealerSupport.dealerId" value="%{fpsDealerSupport.dealerId}"></s:hidden>
							<s:hidden name="fpsDealerSupport.createdById" value="%{fpsDealerSupport.createdById}"></s:hidden>
							<div class="col-md-12">
								<div class="control-group">
									<label class="control-label col-md-1"> <span
										class="required">*</span>Reply:
									</label>
									<div class="controls col-md-8">
										<sj:textarea name="fpsReplyDealerSupport.responseDescription"
											value="" id="messageDescription"
											cssClass="required form-control" rows="2" cols="40"></sj:textarea>
									</div>
								</div>
								<div class="col-md-2">
									<sj:submit cssClass="submitBt btn blue" value="Send"
										targets="replayTargetDiv" formIds="formStudentMessages"
										validate="true" />
								</div>
							</div>
						</s:form>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	changePageTitle('Replay Tickets');
	$(".reply-btn").click(function() {
		$('#replayMegDiv').show();
	});
</script>
