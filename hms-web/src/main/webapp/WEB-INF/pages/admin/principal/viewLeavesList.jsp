<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js"></script>
<jsp:include page="/common/messages.jsp"></jsp:include>
<div class="pendingLeavesBody">
	<s:if test="%{viewStudentPersonAccountDetailsList != null && !viewStudentPersonAccountDetailsList.isEmpty()}">
		<div style="padding-top: 1px">
			<table class="striped" width="85%" style="margin-bottom: 0;"
				cellpadding="1" cellspacing="1" id="results">
				<thead>
					<tr>
						<th width="30%">
							Emp No
						</th>
						<th width="12%">
							Role
						</th>
						<th width="12%">
							Name
						</th>
						<th width="12%">
							Phone Number
						</th>
						<th width="36%">
							Leave Days
						</th>
					</tr>
				</thead>
			</table>
			<div id="resultsPage">
				<s:iterator value="viewStudentPersonAccountDetailsList">
				
					<table class="striped" width="100%" style="margin-bottom: 0;"
						cellpadding="1" cellspacing="1">
						<tr class='loaded'>
							<td width="30%">
								<s:property value="username" />
							</td>
							<td width="12%">
								<s:property value="roleDescription" />
							</td>
							
							<td width="12%">
								<s:property value="personFullName" />
							</td>
							<td width="12%">
								<s:property value="mobileNumber" />
							</td>
							<td width="36%">
								<s:property value="leaveCount" />
							</td>
							
						</tr>
						<tr id="addLeader<s:property value='id' />" style="display: none;"
							class='load'>
						</tr>
					</table>
				</s:iterator>
			</div>

		</div>
	</s:if>
	<s:else>
		<!--<br/>
	Currently there are no Pending Leaves.
-->
	</s:else>
</div>
<br />