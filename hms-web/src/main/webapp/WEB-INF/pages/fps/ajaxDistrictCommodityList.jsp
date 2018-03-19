<%@ include file="/common/taglibs.jsp"%>
<s:if test="%{tempList1 != null && !tempList.isEmpty() && tempList != null && !tempList.isEmpty()}">
	<hr />
	<table cellspacing="0" cellpadding="0" class="table table-striped table-bordered table-hover table-full-width" id="sample_2">
		<thead>
			<tr>
			    <th rowspan="3">District</th>
			    <!--<th class="stubhead" rowspan="2"></th>
			--></tr>
			<!-- tempList contains commodity names -->
			<s:if test="%{tempList != null && !tempList.isEmpty()}">
				<tr>
					<s:iterator value="tempList">
						<th colspan="3" style="text-align: center;">
							<s:property value="name" />
						</th>
					</s:iterator>
				</tr>
				<tr>
					<s:iterator value="tempList">
						<th>I</th>
				    	<th>C</th>
				    	<th>A</th>
					</s:iterator>	
				</tr>
			</s:if>
		</thead>
		<tbody>
		<!-- tempList1 contains district names -->
		<s:if test="%{tempList1 != null && !tempList1.isEmpty()}">
			<s:iterator value="tempList1">
				<tr>
					<th><p class="dataCell"><s:property value="districtName" /></p></th>
					<!-- stockItemTransactionsList contains commodity values -->
					<s:if test="%{stockItemTransactionsList != null && !stockItemTransactionsList.isEmpty()}">
						<s:iterator value="stockItemTransactionsList" status="">
							<td class="success"><p><s:property value="issuedQuantity" /></p></td>
							<td class="success"><p><s:property value="consumedQuantity" /></p></td>
							<td class="alert-danger"><p><s:property value="availableQuantity" /></p></td>
						</s:iterator>
					</s:if>
				</tr>
			</s:iterator>
		</s:if>
		</tbody>
	</table>
</s:if>
<s:else>
	<hr />
	There are no Commodity Details.
</s:else>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/newScripts/table-advanced1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/newScripts/dataTables.tableTools.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/newScripts/dataTables.bootstrap.js"></script>
<script type="text/javascript">
	TableAdvanced.init();
</script>
<style>
	.form-control{
	    width: 0%;
	    display:inline-block;
	}
</style>
