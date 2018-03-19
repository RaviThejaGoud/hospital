<%@ include file="/common/taglibs.jsp"%>
<s:if test="%{tempList2 != null && !tempList2.isEmpty() && tempList != null && !tempList.isEmpty()}">
	<hr />
	<table cellspacing="0" cellpadding="0" class="table table-striped table-bordered table-hover table-full-width" id="sample_2">
		<thead>
			<tr><th rowspan="3">Mandal</th></tr>
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
		<s:if test="%{tempList2 != null && !tempList2.isEmpty()}">
			<s:iterator value="tempList2">
				<tr>
					<th><p class="dataCell"><s:property value="mandalName" /></p></th>
					<!-- stockItemTransactionsList contains commodity values -->
					<s:if test="%{stockItemTransactionsList != null && !stockItemTransactionsList.isEmpty()}">
						<s:iterator value="stockItemTransactionsList">
							<td><p class="dataCell"><s:property value="issuedQuantity" /></p></td>
							<td><p class="dataCell"><s:property value="consumedQuantity" /></p></td>
							<td><p class="dataCell"><s:property value="availableQuantity" /></p></td>
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
<script type="text/javascript">
	TableAdvanced.init();
</script>
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