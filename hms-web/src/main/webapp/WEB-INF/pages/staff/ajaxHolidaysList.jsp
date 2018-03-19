<%@ include file="/common/taglibs.jsp"%>
<div class="block_head">
	<h2>
		Holidays List
	</h2>
</div>
<div class="block_content" style="background: #EFEDE2">
	<s:if
		test="%{holidayBoardMessagesList == null || holidayBoardMessagesList.isEmpty()}">
		<div style="padding: 20px">
			Currently there are no Holidays.
		</div>
	</s:if>
	<s:elseif
		test="%{holidayBoardMessagesList != null && !holidayBoardMessagesList.isEmpty()}">
		<div>
			<table id="results">
				<thead>
				</thead>
			</table>
			<div id="resultsPage">
				<s:iterator value="holidayBoardMessagesList" status="status">
					<table id="results">
						<tr>
							<td>
								<s:property value="startDateStr" />
							</td>
						</tr>
						<tr>
							<td>
								<b><s:property value="description" /> </b>
							</td>
						</tr>
					</table>
				</s:iterator>
			</div>
		</div>
	</s:elseif>
</div>
<style type="text/css">
.active {
	color: #0033CC;
	text-decoration: none;
}

.inactive {
	font-weight: bold;
	text-decoration: underline;
	cursor: default;
}

.paginator {
	text-align: center;
	color: #FFF;
}
</style>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/subscription/paginator.js">
</script>
<script type="text/javascript">
$(function() {
	$("#resultsPage").pagination();
});
</script>