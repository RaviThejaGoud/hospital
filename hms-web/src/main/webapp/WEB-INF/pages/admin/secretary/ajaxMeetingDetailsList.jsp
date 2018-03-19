<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>
<div id="stepStaffsLeaves">
	<!-- <h4 class="pageTitle bold">
			Meeting Details
		</h4> -->
	<s:if test="%{objectList != null && !objectList.isEmpty()}">
		<table
			class="table table-striped table-bordered table-hover table-full-width"
			id="sample_2">
			<thead>
				<tr>
					<th>
						School Name
					</th>
					<th>
						Agenda
					</th>
					<th>
						venue
					</th>
					<th>
						Date- Time
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
							<s:property value="meetingAgenda" />
						</td>
						<td>
							<s:property value="place" />
						</td>
						<td>
							<s:property value="meetingDateTime" />
						</td>
					</tr>
				</s:iterator>
			</tbody>
		</table>
	</s:if>
	<s:else>
		<div class="alert alert-info">
			No Meeting Details found.
		</div>
	</s:else>
</div>

<script type="text/javascript">
TableAdvanced.init();
</script>

