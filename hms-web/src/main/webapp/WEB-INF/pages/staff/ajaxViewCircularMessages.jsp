<%@ include file="/common/taglibs.jsp"%>
<s:if test="%{objectList != null && !objectList.isEmpty()}">
	<table
		class="table table-striped table-bordered table-hover table-full-width" id="sample_2">
		<thead>
			<tr>
				<th>
					Circular Description
				</th>
				<th>
					Message Date
				</th>
				</tr>
		</thead>
		<tbody>
			<s:iterator value="objectList" status="stat">
				<tr>
					<td>
						<s:property value="objectList[#stat.count-1][0]"/>
					</td>
					<td>
						<s:property value="objectList[#stat.count-1][1]" />
					</td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
</s:if>
<s:else>
	<div class="alert alert-info">
		Currently there are no circular messages.
	</div>
</s:else>
<div id="alertInfoDiv"></div>
<script language="JavaScript" type="text/javascript">
TableAdvanced.init();
</script>
