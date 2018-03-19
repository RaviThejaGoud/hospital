<%@ include file="/common/taglibs.jsp"%>
<jsp:include page="/common/messages.jsp"></jsp:include>
<div id="commonTabContent" class="grid_13">
	<div id="commonTabWrapper">
		<div id="commonStep">
			<fieldset>
				<div class="grid_13">
					Between
					<FONT color="#008EE8"> <s:property
							value="scrapMessage.createdBy" /> </FONT> and You.
					<div class="grid_12 border"></div>
					<div>
						&nbsp;
					</div>
					<div id="studentReplyMessage">
						<s:if
							test="{replyScrapMessageSet != NULL & !replyScrapMessageSet.isEmpty()}">
							<s:iterator value="replyScrapMessageSet">
								<div class="grid_13">
									<div class="grid_2">
										<img
											src='<c:url value="${senderAccount.profileImage.adjustedAttachmentFilePath}"/>'
											alt='<s:property  value="senderAccount.person.fullPersonName" />'
											height="50px" width="50px" border="0" />
									</div>
									<div class="grid_8">
										<div class="grid_8">
											<b><s:property value="createdBy" /> </b>
										</div>
										<div class="grid_8">
											<div class="grid_1" style="width: 10px;">
												<span class="lertArrow"></span>&nbsp;
											</div>
											<span class="lertArrow">
											</span><s:property value="scrapDescription" />
										</div>
									</div>
									<div class="grid_3">
										<span class="hintMessage"><s:property
												value="scrapMessageDateStr" /> </span>
									</div>
									<div class="border grid_13">
										&nbsp;
									</div>
								</div>
							</s:iterator>
						</s:if>
						<s:if
							test='%{(#session.academicYear == null || #session.academicYear.isEmpty()) || (#session.previousYear=="N")}'>
							<s:form action="ajaxReplyNewScrapMessage" theme="css_xhtml"
								id="formStudentMessages">
								<s:hidden name="scrapMessage.id"></s:hidden>
								<s:hidden name="scrapMessage.senderAccount.id"></s:hidden>
								<s:hidden name="scrapMessage.receiverAccount.id"></s:hidden>
								<div class="grid_12">
									<div class="grid_11">
										<div class="grid_2">
											<span class="required"> * </span><b>Reply:</b>
										</div>
										<div class="grid_7">
											<sj:textarea name="replyScrapMessage.scrapDescription"
												cssStyle="height:30px;" value="" id="messageDescription"
												cssClass="required" rows="2" cols="40"></sj:textarea>
										</div>
										<div class="grid_2">
											<img  src="../img/bg/bigWaiting.gif" alt="Loading..." title="Loading..." id="indicator" style="display:none; " />
											<sj:submit cssClass="submit small" value="Reply" validate="true"
												indicator="indicator" targets="studentReplyMessage"
												onBeforeTopics="formValidationForStudentMessages" />
										</div>
									</div>
								</div>
							</s:form>
						</s:if>
					</div>
					<div style="display: none"
						id='studentReplyMessage<s:property value="id"/>' class='load'>
					</div>
				</div>
			</fieldset>
		</div>
	</div>
</div>
<script type="text/javascript">
changePageTitle('Read More Student Message');
document.title = 'SMS | View Student Messages';
$.subscribe('doInitReplyStudentMessage', function(event, data) {
	if ($('#' + data.id).is(":hidden")) {
		$('#' + data.id).show();
	} else {
		$('#' + data.id).hide();
	}
});
$(document).ready(function() {
	var validator;
	$.subscribe('formValidationForStudentMessages', function(event, data) {
		if ($('#formStudentMessages').valid())
			return true;
		else
			return false;
	});
});
</script> 