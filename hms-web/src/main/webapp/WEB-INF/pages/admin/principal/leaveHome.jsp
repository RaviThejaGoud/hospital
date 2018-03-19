<%@ include file="/common/taglibs.jsp"%>
<jsp:include page="/common/messages.jsp"></jsp:include>
<div class="block grid_17">
	<div class="block_head">
		<h2>
			Most leaves in last 30 days by Staff
		</h2>
	</div>
	<!--<div id="topMenu">
		<ul>
			<li>
				<s:url id="urlLeaveLink" action="ajaxDoGetLeaveDetailsForPrincipal" />
				<sj:a href="%{urlLeaveLink}" targets="applyLeave"
					indicator="indicator"></sj:a>
			</li>
		</ul>
	</div>
	-->
	<div class="block_content" id="staffLeaveApproval">
		<div class="pendingLeavesBody">
			<s:if
				test="%{viewStaffAccountDetails != null && !viewStaffAccountDetails.isEmpty()}">
				<div style="padding-top: 1px">
					<table class="striped" width="100%" style="margin-bottom: 0;"
						cellpadding="1" cellspacing="1" id="results">
						<thead>
							<tr>
								<th width="20%">
									Emp No
								</th>
								<th width="20%">
									Role
								</th>
								<th width="20%">
									Name
								</th>
								<th width="20%">
									Phone Number
								</th>
								<th width="20%">
									Leave Days
								</th>
							</tr>
						</thead>
					</table>
					<div id="resultsPage">
						<s:iterator value="viewStaffAccountDetails">

							<table class="striped" width="100%"
								style="margin-bottom: 0; border-width: 1px 1px 1px;"
								cellpadding="1" cellspacing="1">
								<tr class='loaded'>
									<td width="20%">
										<s:property value="username" />
									</td>
									<td width="20%">
										<s:property value="roleDescription" />
									</td>

									<td width="20%">
										<s:property value="personFullName" />
									</td>
									<td width="20%">
										<s:property value="mobileNumber" />
									</td>
									<td width="20%">
										<s:property value="leaveCount" />
									</td>

								</tr>
								<tr id="addLeader<s:property value='id' />"
									style="display: none;" class='load'>
								</tr>
							</table>
						</s:iterator>
					</div>

				</div>
			</s:if>
			<s:else>
				<div class="alert alert-info">
					Currently there are no staff Leaves.
				</div>
			</s:else>
		</div>
	</div>
	<div>
		&nbsp;
	</div>
	<div class="block_head">
		<h2>
			Most leaves in last 30 days by Student
		</h2>
	</div>
	<!--<div id="topMenu">
		<ul>
			<li>
				<s:url id="urlLeaveLink" action="ajaxDoGetLeaveDetailsForPrincipal" />
				<sj:a href="%{urlLeaveLink}" targets="applyLeave"
					indicator="indicator"></sj:a>
			</li>
		</ul>
	</div>
	-->
	<div class="block_content" id="staffLeaveApproval">
		<div class="pendingLeavesBody">
			<s:if
				test="%{viewStudentPersonAccountDetailsList != null && !viewStudentPersonAccountDetailsList.isEmpty()}">
				<div style="padding-top: 1px">
					<table class="striped" width="100%" style="margin-bottom: 0;"
						cellpadding="1" cellspacing="1" id="results">
						<thead>
							<tr>
								<th width="20%">
									Emp No
								</th>
								<th width="20%">
									Role
								</th>
								<th width="20%">
									Name
								</th>
								<th width="20%">
									Phone Number
								</th>
								<th width="20%">
									Leave Days
								</th>
							</tr>
						</thead>
					</table>
					<div id="resultsPage">
						<s:iterator value="viewStudentPersonAccountDetailsList">

							<table class="striped" width="100%"
								style="margin-bottom: 0; border-width: 1px 1px 1px;"
								cellpadding="1" cellspacing="1">
								<tr class='loaded'>
									<td width="20%">
										<s:property value="username" />
									</td>
									<td width="20%">
										<s:property value="roleDescription" />
									</td>

									<td width="20%">
										<s:property value="personFullName" />
									</td>
									<td width="20%">
										<s:property value="mobileNumber" />
									</td>
									<td width="20%">
										<s:property value="leaveCount" />
									</td>

								</tr>
								<tr id="addLeader<s:property value='id' />"
									style="display: none;" class='load'>
								</tr>
							</table>
						</s:iterator>
					</div>

				</div>
			</s:if>
			<s:else>
				<div class="alert alert-info">
					Currently there are no student Leaves.
				</div>
			</s:else>
		</div>
	</div>
</div>