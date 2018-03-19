<%@ include file="/common/taglibs.jsp"%>
<div class="form-body">
	<s:hidden name="tempString" cssClass="provistionItemsDataClass"></s:hidden>
	<table
		class="table table-striped table-bordered table-hover table-full-width getProvisionItemsToIssueTable"
		id="sample_2">
		<thead>
			<tr>
				<th>
					Item Name
				</th>
				<th>
					Available Qty
				</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="objectList">
			<tr>
				<td>
					<s:property value="itemName" />
				</td>
				<td>
					<s:property value="usedQuantity" />
				</td>
				
			</tr>
		</s:iterator>
		</tbody>
	</table>
</div>	
<script type="text/javascript">
$(document).ready(function(){
	FormComponents.init();
});
</script>