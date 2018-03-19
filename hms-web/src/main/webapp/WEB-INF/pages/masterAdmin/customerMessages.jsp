<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript"
		src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>
<jsp:include page="/common/messages.jsp"></jsp:include>
<div data-width="760" class="modal fade modal-overflow in"
	style="display: block; width: 760px; margin-left: -379px; margin-top: 150px;">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true"></button>
	</div>
	<div class="modal-body">
		<s:if
			test="%{supportTicketMessagesList != null && !supportTicketMessagesList.isEmpty()}">
			<table
				class="table table-striped table-bordered table-hover table-full-width"
				id="sample_2">
				<thead>
					<tr>
						<th>
							Issue description
						</th>
						<th>
							Priority
						</th>
						<th>
							Assign to
						</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="supportTicketMessagesList">
						<tr>
							<td>
								<b><s:property value="{description}" /> :</b>&nbsp;
							</td>
							<td>
								<s:property value="priority" />
								&nbsp;
							</td>
							<td>
								<s:form action="ajaxAssignUserForIssue" method="post"
									theme="simple" id="assignTask%{id}" namespace="/masterAdmin">
									<s:hidden name="tempId" value="%{id}"></s:hidden>
									<div class="tableactions" style="padding-bottom: 0px;">
										<s:select id="firstName" list="developersList"
											cssClass="required form-control input-medium"
											cssStyle="width:150px;" required="true" listKey="id"
											listValue="firstName" headerKey="" headerValue="- Select -"
											name="assignedUserId" theme="simple" />
									</div>
									<sj:submit   cssClass="submitBt btn blue"
										formIds="assignTask%{id}" value="Submit"
										targets="feedBackContent" />
								</s:form>
							</td>
						</tr>
					</s:iterator>
				</tbody>
			</table>
		</s:if>
		<s:else>
			<div class="alert alert-info">
				Currently there are no Support tickets.
			</div>
		</s:else>
	</div>
</div>
