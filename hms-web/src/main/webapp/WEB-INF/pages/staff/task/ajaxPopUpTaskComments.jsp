<%@ include file="/common/taglibs.jsp"%>
<div data-width="760" class="modal fade modal-overflow in"
	style="display: block; width: 660px; margin-left: -279px; margin-top: 100px;"
	aria-hidden="false">
	<div class="modal-body">
		<div class="form-body">
		SIze : <s:property value="taskDetailsVO.taskHistoriesVO.size()"/>
			<s:form action="#" method="post" theme="simple" namespace=""
				cssClass="form-horizontal">
				<s:hidden name="tempString" value="%{tempString}"></s:hidden>
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true"></button>
					<h4 class="modal-title">
						<b>Task Comments</b>
					</h4>
				</div>
				<div class="col-md-12">
					<div class="row">
						<div class="col-md-12">
							<div class="form-group">
								<label class="control-label col-md-2"><b>Task Name :</b>
								</label>
								<div class="col-md-9" style="margin-top: 6px;">
									<s:property value="taskDetailsVO.taskName" />
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<div class="form-group">
								<label class="control-label col-md-2"><b>Description
										:</b> </label>
								<div class="col-md-9" style="margin-top: 6px;">
									<s:property value="taskDetailsVO.description" />
								</div>
							</div>
						</div>
					</div>
				</div>
				<div style="overflow: scroll; height: 300px; width: 100%; overflow: auto;">

					<table class="table table-striped table-bordered table-hover table-full-width"
						id="sample_2">
						<tr>
						</tr>
						<s:if test="%{taskDetailsVO.taskHistoriesVO != null && !taskDetailsVO.taskHistoriesVO.isEmpty()}">
							<tbody style="position: absolute;">
								<s:iterator value="taskDetailsVO.taskHistoriesVO"> 
									<tr>
										<td style="width: 614px;">
										<font color="#800080"><s:property value="accountVO.fullName" /></font> (<s:property
												value="taskHistoryDateStr" /> , <s:if
												test='%{status != null && !status.isEmpty()}'>
												<s:if test='%{status =="O"}'>
													<font color="#800080"><b><s:property value="statusStr" /></b></font>
												</s:if>
												<s:elseif test='%{status =="I"}'>
													<font color="#229954"><b><s:property value="statusStr" /></b></font>
												</s:elseif>
												<s:elseif test='%{status =="H"}'>
													<font color="#800000"><b><s:property value="statusStr" /></b></font>
												</s:elseif>
												<s:elseif test='%{status =="C"}'>
													<font color="#808080"><b><s:property value="statusStr" /></b></font>
												</s:elseif>
											</s:if>)
											<br/><p style="max-width: 614px;"><s:property value="comments" /></p></td>
									</tr>
								</s:iterator>
							</tbody>
						</s:if>
						<s:else>
							<div class="alert alert-info">Currently there are no
								comments.</div>
						</s:else>
					</table>

				</div>
			</s:form>
		</div>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function() {
	UIExtendedModals.init();
});
</script>
