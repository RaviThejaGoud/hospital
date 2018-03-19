<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/messages.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>
<s:if test="%{tempList != null && !tempList.isEmpty()}">
<table class="table table-striped table-bordered table-hover table-full-width" id="sample_2">
		<thead>
			<tr>
				
				<th>
					Organization Name
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
			<s:iterator value="tempList">
				<tr>
					<td>
						<s:property value="organizationName" />
					</td>
					<td>
						<s:property value="mobileNumber" />
					</td>
					<td>
						<s:url id="doOrgCust" action="ajaxOranizationCustomerDetails"
							includeParams="all" escapeAmp="false" namespace="/masterAdmin">
							<s:param name="tempId" value="%{id}" />
						</s:url>
						<sj:a href="%{doOrgCust}" indicator="indicator"
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
	<div class="alert alert-info">Currently There are no Customers.</div>
</s:else>
<script type="text/javascript">
$(document).ready(function() {
	TableAdvanced.init();
});
</script>