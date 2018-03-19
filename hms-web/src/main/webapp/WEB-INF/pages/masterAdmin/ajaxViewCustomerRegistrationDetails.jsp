<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/messages.jsp"%>
<s:if test="%{objectList != null && !objectList.isEmpty()}">
	<table class="table table-striped table-bordered table-hover table-full-width" id="sample_2">
		<thead>
			<tr>
				<th>
					School Name
				</th>
				<th>
					Full Name
				</th>
				<th>
					Email
				</th>
				<th>
					Mobile Number
				</th>
				<th>
					Account Type
				</th>
				<th>
					Status
				</th>
				<th>
					View
				</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="objectList">
				<tr>
					<td>
						<s:property value="organization" />
					</td>
					<td>
						<s:property value="customerFullPersonName" />
					</td>
					<td>
						<s:property value="custEmail" />
					</td>
					<td>
						<s:property value="mobileNumber" />
					</td>
					<td>
						<s:property value="inviteCustomerEnrollment.accountType" />
					</td>
					<td>
						<s:property value="statusStr" />
					</td>
					<td>
						<s:url id="doAddNewCustomersDetails" action="ajaxDoApproveRejectCustomerRegistration"
							includeParams="all" escapeAmp="false" namespace="/signup">
							<s:param name="customerEnrollmentRequest.id" value="%{id}" />
						</s:url>
						<sj:a href="%{doAddNewCustomersDetails}" indicator="indicator"
							targets="newCustomerDetailsDiv" title="Edit" button="false"
							cssClass="btn btn-xs purple"><i class="fa fa-edit"></i>View
							</sj:a>
					</td>
				</tr>
			</s:iterator>

		</tbody>
	</table>
</s:if>
<s:else>
	<div class="alert alert-info">
		Currently There are no Customers.
	</div>
</s:else>
<script type="text/javascript">
	TableAdvanced.init();
</script>



