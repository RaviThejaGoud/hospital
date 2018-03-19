<%@ include file="/common/taglibs.jsp"%>
<s:if test="%{objectList != null && !objectList.isEmpty()}">
	<table class="table table-striped table-bordered table-hover table-full-width" id="sample_2">
		<thead>
			<tr>
				<th>Account Name</th>
				<th>Category Name</th>
				<th>Statement Name</th>
				<th>Transaction Type</th>
				<th>Balance</th>
				<th>Edit</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="objectList">
				<tr>
					<td><s:property value="accountName" /></td>
					<td><s:property value="cartegoryName" /></td>
					<td><s:property value="statementName" /></td>
					<td><s:property value="transactionTypeName" /></td>

					<td><s:property value="balanceAmount" /></td>
					<td><s:property value="edit" /> 
					<s:url id="doEditFinAccountDetails" action="ajaxDoEditFinAccountDetails" includeParams="all" escapeAmp="false">
							<s:param name="financialAccountDetailsVO.id" value="%{accountId}" />
					</s:url> <sj:a href="%{doEditFinAccountDetails}" indicator="indicator" targets="accountMasterContentDiv" cssClass="btn btn-xs purple" title="Edit">
							<i class="fa fa-edit"></i>
						</sj:a></td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
</s:if>
<s:else>
	<div class="alert alert-info">Currently there there no financial
		account details.</div>
</s:else>
<script type="text/javascript">
	TableAdvanced.init();
</script>