<%@ include file="/common/taglibs.jsp"%>
<s:if test="%{objectList != null && !objectList.isEmpty()}">
	<table class="table table-striped table-bordered table-hover table-full-width" id="sample_2">
		<thead>
			<tr>
				<th>
					School Name
				</th>
				<th>
					Total Students
				</th>
				<th>
					Email
				</th>
				<th>
					Account Type
				</th>
				<th>
					Status
				</th>
				<th>
					Edit
				</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="objectList">
				<tr>
					<td>
						<s:property value="schoolName" />
					</td>
					<td>
						<s:property value="packageDetails.studentsRange" />
					</td>
					<td>
						<s:property value="email" />
					</td>
					<td>
						<s:property value="accountType" />
					</td>
					<td>
						<s:property value="statusStr" />
					</td>
					<td>
						<s:url id="doAddNewCustomersDetails" action="ajaxDoAddNewCustomersDetails"
							includeParams="all" escapeAmp="false" namespace="/masterAdmin">
							<s:param name="inviteCustomerEnrollment.id" value="%{id}" />
						</s:url>
						<sj:a href="%{doAddNewCustomersDetails}" indicator="indicator"
							targets="newCustomerDetailsDiv" title="Edit" button="false"
							cssClass="btn btn-xs purple"><i class="fa fa-edit"></i>Edit
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



