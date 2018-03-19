<%@ include file="/common/taglibs.jsp"%>
<div class="form-body">
	<s:form id="storeitemDetailform" cssClass="form-horizontal"
		theme="simple">
		<input type="hidden" name="selectedStore" value="selectedStore"
			id="selectedStore" />
		<s:if test="%{issuedItemsList != null && !issuedItemsList.isEmpty()}">
			<div id="issuedItems">
				<table
					class="table table-striped table-bordered table-hover table-full-width"
					id="sample_3">
					<thead>
						<tr>
							<th align="center">Item Type</th>
							<th align="center">Item Name</th>
							<th align="center">Item Code</th>
							<th align="center">Issued Date</th>
							<th align="center">Issued Quantity</th>
							<th align="center">Issued By</th>
							<th align="center">Received By</th>
						</tr>
					</thead>
					<tbody>
						<s:iterator value="issuedItemsList">
							<tr>
								<td><s:property value="itemType" /></td>
								<td><s:property value="itemName" /></td>
								<td><s:property value="itemCode" /></td>
								<td><s:property value="issuedDate" /></td>
								<td align="center"><s:property value="quantity" /></td>
								<td><s:property value="issuerBy" /></td>
								<td><s:property value="recievedBy" /></td>
						</s:iterator>
					</tbody>
				</table>
			</div>
		</s:if>
		<s:else>
			<div class="alert alert-info">There are no issued items found
				for the selected store.</div>
		</s:else>
	</s:form>
</div>
