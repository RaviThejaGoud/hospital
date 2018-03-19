<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/messages.jsp"%>
<div>
	<div class="spaceDiv"></div>
		<s:if test="%{objectList != null && !objectList.isEmpty()}">
			<table class="table table-striped table-bordered table-hover table-full-width"
				id="sample_4">
				<thead>
					<tr>
						<th>
							Email Sent
						</th>
						<th>
							Successfull Email Sent
						</th>
						
						
						<th>
							Bounces Email Count
						</th>
						<th>
							Unique Opens
						</th>
						<!-- <th>
							unsubscribes
						</th> -->
					</tr>
				</thead>
				<tbody>
					<s:iterator value="objectList">
							<tr>
							
								<td>
									<s:property value="emails_sent" />
								</td>
								<td>
									<s:property value="tempId1" />
								</td>
								
								
								<td>
									<s:property value="tempId" />
								</td>
								
								<td>
									<s:property value="unique_opens" />
								</td>

								<%-- <td>
									<s:property value="soft_bounces" />
								</td> --%>
								<%-- <td>
									<s:property value="unsubscribes" />
								</td> --%>
							</tr>
					</s:iterator>
				</tbody>
			</table>
			
		</s:if>
		<s:else>
			<div class="alert alert-info">
				Not found the Campaign Stats
			</div>
		</s:else>

</div>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/onload.js">
</script>
<script type="text/javascript">
$(document).ready(function() {
	TableAdvanced.init();
});
</script>
