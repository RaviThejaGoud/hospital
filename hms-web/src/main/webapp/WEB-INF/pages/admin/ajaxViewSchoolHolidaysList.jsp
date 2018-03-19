<%@ include file="/common/taglibs.jsp"%>
<div  >
	<s:if test="%{holidayBoardMessagesList != null && !holidayBoardMessagesList.isEmpty()}">
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
	</s:if>
</div>





