<%@ include file="/common/taglibs.jsp"%>
<s:if test="%{objectList != null && !objectList.isEmpty()}">
	<table class="table table-striped table-bordered table-hover table-full-width" id="sample_2">
		<thead>
			<tr>
				<th>
					Organization
				</th>
				<th>
					Customer Name
				</th>
				<th>
					Email
				</th>
				<th>
					Contact Number
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
						<s:property value="organization" />
					</td>
					<td>
						<s:property value="firstName" />
						<s:property value="lastName" />
					</td>
					<td>
						<s:property value="custEmail" />
					</td>
					<td>
						<s:property value="mobileNumber" />
					</td>
					<td>
						<s:url id="doMultiBranchCust" action="ajaxEditMultiBranchCustomer"
							includeParams="all" escapeAmp="false" namespace="/masterAdmin">
							<s:param name="tempId" value="%{id}" />
						</s:url>
						<sj:a href="%{doMultiBranchCust}" indicator="indicator"
							targets="contentDiv" title="Edit" button="false"
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



