<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Send new SMS features to all customers
				</div>
			</div>
			<div class="portlet-body">
				<div id="studentLibraryContentDiv" class="tab-content">
				<%@ include file="/common/messages.jsp"%>
				
				<div id="deleteNotification">
					<s:if test="%{objectList.size()==0}">
						<s:form id="messagesValidationInfo" action="ajaxSendSMSNewFeaturesToAllCustomers" method="post" theme="simple" cssClass="form-horizontal" namespace="/masterAdmin">
							<div class="form-body form-horizontal">
								<div class="row">
									<div class="col-md-10">
										<div class="form-group">
											<label class="control-label col-md-3"><span class="required">*</span> New Features Message : </label>
											<div class="col-md-9">
												<sj:textarea rows="10" cols="20" name="sendNotifications.description" maxCharsData="2000" 
														cssClass="required form-control word_count"></sj:textarea>
														<span class="counter"></span>
											</div>
										</div>
									</div>
								</div>
								<div class="form-actions fluid">
									<div class="col-md-offset-3 col-md-6">
										<sj:submit cssClass="submitBt btn green" value="Submit"
											indicator="indicator" targets="mainContentDiv"
											onBeforeTopics="messagesValidation"
											formIds="messagesValidationInfo"  validate="true" />
									</div>
								</div>
							</div>
						</s:form>
						</s:if>
						<s:if test="%{objectList != null && !objectList.isEmpty()}">
							<div class="spaceDiv">&nbsp;</div>
							<table class="table table-striped table-bordered table-hover table-full-width">
								<thead>
									<tr>
										<th>
											Date
										</th>
										<th>
											Message
										</th>
										<th>
											Edit
										</th>
										<th>
											Delete
										</th>
									</tr>
								</thead>
								<tbody>
									<s:iterator value="objectList">
										<tr>
											<td>
												<s:property value="createdDateStr" />
											</td>
											<td>
												<s:property value="description" />
											</td>
										    <td>
												<a data-toggle="modal" href="#popupEditNotification"
													class="btn btn-xs purple"
													onclick="javascript:popupNotificationDetails(<s:property value="id" />);"><i
													class="fa fa-edit"></i>Edit </a>
											</td>
											<td>
												<s:url id="removeFeedback" action="ajaxDeleteNotification"
													escapeAmp="false" namespace="/masterAdmin">
													<s:param name="tempId1" value="id"></s:param>
												</s:url>
												<s:div cssClass="btn btn-xs red"
													onclick="javascript:confirmDialogWithTarget(this,'mainContentDiv');"
													id='%{removeFeedback}' title="Delete this class">
													<i class="fa fa-times"></i>Delete
																</s:div>
											</td>
										</tr>
									</s:iterator>
								</tbody>
							</table>
						</s:if>
				</div>
				</div>
			</div>
		</div>
	</div>
</div>
<div id="popupEditNotification"></div>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/subscription/textcounter.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	changePageTitle('Send new features');
	$.subscribe('messagesValidation', function(event, data) {
		if ($('#messagesValidationInfo').valid())
			return true;
		else
			event.originalEvent.options.submit = false;
	});
});
function popupNotificationDetails(id) {
	var url = jQuery.url.getChatURL("/masterAdmin/ajaxEditNotifications.do");
	$('#popupEditNotification').html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
	$.ajax( {
			url : url,
			cache : false,
			data : "tempId2="+id,
			success : function(html) {
				$("#popupEditNotification").html(html);
			}
		});
	}
</script>
