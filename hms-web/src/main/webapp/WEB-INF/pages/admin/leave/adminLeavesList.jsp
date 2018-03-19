<%@ include file="/common/taglibs.jsp"%>
<div id="steps13">
	<fieldset id="stepAttendance" class="step13">
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>
		<s:if test="%{viewStaffPersonAccountDetails.roleName == 'ROLE_HOD' }">
			<div>
				<b>Staff Leave Approvals As Approver:</b>
			</div>
		</s:if>
		<s:elseif
			test="%{viewStaffPersonAccountDetails.roleName == 'ROLE_TEACHER' }">
			<div>
				<b> Student Leave Approvals As Class Teacher:</b>
			</div>
		</s:elseif>
		<s:elseif
			test="%{viewStaffPersonAccountDetails.roleName == 'ROLE_PRINCIPAL' }">
			<div>
				<b> HOD Leave Approvals As Approver:</b>
			</div>
		</s:elseif>
		<div class="pendingApprovalsLeaves">
			<a href="#">Pending(<s:property value="classNameList.size()" />):</a>
		</div>
		<div class="pendingApprovalsLeavesBody">
			<s:if test="%{classNameList != null && !classNameList.isEmpty()}">
				<div class="grid_14">
					<div style="padding-top: 1px">
						<div class="grid_12 th">
							<div class="grid_3">
								Name
							</div>
							<div class="grid_3">
								Type
							</div>
							<div class="grid_3">
								Start Date
							</div>
							<div class="grid_3">
								End Date
							</div>
							<div class="grid_3">
								Total Days
							</div>
							<div class="grid_3">
								Action
							</div>
						</div>
						<div id="resultsPage">
							<s:iterator value="classNameList">
								<div class="grid_12 row">
									<div class="loaded">
										<div class="grid_3">
											<s:property value="PersonFirstLastNameOnly" />
										</div>
										<div class="grid_3">
											<s:property value="leaveStatusDesc" />
										</div>
										<div class="grid_3">
											<s:property value="startDateStr" />
										</div>
										<div class="grid_3">
											<s:property value="endDateStr" />
										</div>
										<div class="grid_3">
											<s:property value="days" />
										</div>
										<div class="grid_3">
											<s:url id="addGroupMember"
												action="ajaxDoApprovalsLeavesReport" includeParams="all"
												escapeAmp="false">
												<s:param name="leavesId" value="%{leavesId}" />
											</s:url>
											<sj:a href="%{addGroupMember}"
												onCompleteTopics="doInitAddGroupMember"
												onBeforeTopics="cleanOpenRows" indicator="indicator"
												targets="addLeader%{leavesId}">Action</sj:a>
										</div>
									</div>
									<div id="addLeader<s:property value='leavesId' />"
										style="display: none;" class='load'>
									</div>
								</div>
							</s:iterator>
						</div>
					</div>
				</div>
			</s:if>
			<s:else>

			</s:else>
		</div>
		<br />
		<div class="acceptApprovalsLeaves">
			<a href="#">Approval(<s:property value="absentList.size()" />):</a>
		</div>
		<div class="acceptApprovalsLeavesBody">
			<s:if test="%{absentList != null && !absentList.isEmpty()}">
				<div class="grid_12 th">
					<div class="grid_3">
						Name
					</div>
					<div class="grid_3">
						Type
					</div>
					<div class="grid_3">
						Start Date
					</div>
					<div class="grid_3">
						End Date
					</div>
					<div class="grid_3">
						Total Days
					</div>
					<div class="grid_3">
						View
					</div>
				</div>

				<div id="resultsPage">
					<s:iterator value="absentList">
						<div class="grid_12 row">
							<div class="loaded">
								<div class="grid_3">
									<s:property value="PersonFirstLastNameOnly" />
								</div>
								<div class="grid_3">
									<s:property value="leaveStatusDesc" />
								</div>
								<div class="grid_3">
									<s:property value="startDateStr" />
								</div>
								<div class="grid_3">
									<s:property value="endDateStr" />
								</div>
								<div class="grid_3">
									<s:property value="days" />
								</div>
								<div class="grid_3">

									<s:url id="addGroupMember" action="ajaxDoApprovalsLeavesReport"
										includeParams="all" escapeAmp="false">
										<s:param name="leavesId" value="%{leavesId}" />
									</s:url>
									<sj:a href="%{addGroupMember}"
										onCompleteTopics="doInitAddGroupMember"
										onBeforeTopics="cleanOpenRows" indicator="indicator"
										targets="addLeader%{leavesId}">View</sj:a>
								</div>
							</div>
							<div id="addLeader<s:property value='leavesId' />"
								style="display: none;" class='load'>
							</div>
						</div>
					</s:iterator>

				</div>
			</s:if>
			<s:else>
			</s:else>
		</div>
		<br />
		<div class="rejectApprovalsLeaves">
			<a href="#">Rejected(<s:property value="classList.size()" />):</a>
		</div>
		<div class="rejectApprovalsLeavesBody">
			<s:if test="%{classList != null && !classList.isEmpty()}">
				<div style="padding-top: 1px">
					<div class="grid_12 th">
						<div class="grid_3">
							Name
						</div>
						<div class="grid_3">
							Type
						</div>
						<div class="grid_3">
							Start Date
						</div>
						<div class="grid_3">
							End Date
						</div>
						<div class="grid_3">
							Total Days
						</div>
						<div class="grid_3">
							View
						</div>
					</div>
					<div id="resultsPage">
						<s:iterator value="classList">
							<div class="grid_12 row">
								<div class="grid_3">
									<s:property value="PersonFirstLastNameOnly" />
								</div>
								<div class="grid_3">
									<s:property value="leaveStatusDesc" />
								</div>
								<div class="grid_3">
									<s:property value="startDateStr" />
								</div>
								<div class="grid_3">
									<s:property value="endDateStr" />
								</div>
								<div class="grid_3">
									<s:property value="days" />
								</div>
								<div class="grid_3">
									<s:url id="addGroupMember" action="ajaxDoApprovalsLeavesReport"
										includeParams="all" escapeAmp="false">
										<s:param name="leavesId" value="%{leavesId}" />
									</s:url>
									<sj:a href="%{addGroupMember}"
										onCompleteTopics="doInitAddGroupMember"
										onBeforeTopics="cleanOpenRows" indicator="indicator"
										targets="addLeader%{leavesId}">View</sj:a>
								</div>
								<div id="addLeader<s:property value='leavesId' />"
									style="display: none;" class='load'>
								</div>
							</div>
						</s:iterator>
					</div>
				</div>
			</s:if>
			<s:else>
			</s:else>
		</div>
		<s:if test="%{viewStaffPersonAccountDetails.roleName == 'ROLE_HOD' }">
			<div>
				<b> Student Leave Approvals As Class Teacher:</b>
			</div>
			<div class="pendingStudentApprovalsLeaves">
				<a href="#">Pending(<s:property
						value="studentFeeAbove15List.size()" />):</a>
			</div>
			<div class="pendingStudentApprovalsLeavesBody">
				<s:if
					test="%{studentFeeAbove15List != null && !studentFeeAbove15List.isEmpty()}">
					<div class="grid_12 th">
						<div class="grid_3">
							Name
						</div>
						<div class="grid_3">
							Type
						</div>
						<div class="grid_3">
							Start Date
						</div>
						<div class="grid_3">
							End Date
						</div>
						<div class="grid_3">
							Total Days
						</div>
						<div class="grid_3">
							Action
						</div>
					</div>
					<div id="resultsPage">
						<s:iterator value="studentFeeAbove15List">
							<div class="grid_12 row">
								<div class="grid_3">
									<s:property value="PersonFirstLastNameOnly" />
								</div>
								<div class="grid_3">
									<s:property value="leaveStatusDesc" />
								</div>
								<div class="grid_3">
									<s:property value="startDateStr" />
								</div>
								<div class="grid_3">
									<s:property value="endDateStr" />
								</div>
								<div class="grid_3">
									<s:property value="days" />
								</div>
								<div class="grid_3">

									<s:url id="addGroupMember" action="ajaxDoApprovalsLeavesReport"
										includeParams="all" escapeAmp="false">
										<s:param name="leavesId" value="%{leavesId}" />
									</s:url>
									<sj:a href="%{addGroupMember}"
										onCompleteTopics="doInitAddGroupMember"
										onBeforeTopics="cleanOpenRows" indicator="indicator"
										targets="addLeader%{leavesId}">Action</sj:a>
								</div>
								<div id="addLeader<s:property value='leavesId' />"
									style="display: none;" class='load'>
								</div>
							</div>
						</s:iterator>
					</div>
				</s:if>
				<s:else>
				</s:else>
			</div>

			<div class="acceptStudentApprovalsLeaves">
				<a href="#">Approval(<s:property
						value="studentPaymentList.size()" />):</a>
			</div>
			<div class="acceptStudentApprovalsLeavesBody">
				<s:if
					test="%{studentPaymentList != null && !studentPaymentList.isEmpty()}">
					<div style="padding-top: 1px">
						<div class="grid_12 th">
							<div class="grid_3">
								Name
							</div>
							<div class="grid_3">
								Type
							</div>
							<div class="grid_3">
								Start Date
							</div>
							<div class="grid_3">
								End Date
							</div>
							<div class="grid_3">
								Total Days
							</div>
							<div class="grid_3">
								View
							</div>
						</div>
						<div id="resultsPage">
							<s:iterator value="studentPaymentList">

								<div class="grid_12 row">
									<div class="loaded">
										<div class="grid_3">
											<s:property value="PersonFirstLastNameOnly" />
										</div>
										<div class="grid_3">
											<s:property value="leaveStatusDesc" />
										</div>
										<div class="grid_3">
											<s:property value="startDateStr" />
										</div>
										<div class="grid_3">
											<s:property value="endDateStr" />
										</div>
										<div class="grid_3">
											<s:property value="days" />
										</div>
										<div class="grid_3">

											<s:url id="addGroupMember"
												action="ajaxDoApprovalsLeavesReport" includeParams="all"
												escapeAmp="false">
												<s:param name="leavesId" value="%{leavesId}" />
											</s:url>
											<sj:a href="%{addGroupMember}"
												onCompleteTopics="doInitAddGroupMember"
												onBeforeTopics="cleanOpenRows" indicator="indicator"
												targets="addLeader%{leavesId}">View</sj:a>
										</div>
									</div>
									<div id="addLeader<s:property value='leavesId' />"
										style="display: none;" class='load'>
									</div>
								</div>
							</s:iterator>
						</div>

					</div>
				</s:if>
				<s:else>
				</s:else>
			</div>
			<br />
			<div class="rejectStudentApprovalsLeaves">
				<a href="#">Rejected(<s:property value="classFeeList.size()" />):</a>
			</div>
			<div class="rejectStudentApprovalsLeavesBody">
				<s:if test="%{classFeeList != null && !classFeeList.isEmpty()}">
					<div class="grid_12 th">
						<div class="grid_3">
							Name
						</div>
						<div class="grid_3">
							Type
						</div>
						<div class="grid_3">
							Start Date
						</div>
						<div class="grid_3">
							End Date
						</div>
						<div class="grid_3">
							Total Days
						</div>
						<div class="grid_3">
							View
						</div>
					</div>
					<div id="resultsPage">
						<s:iterator value="classFeeList">
							<div class="grid_12 row">
								<div class="grid_3">
									<s:property value="PersonFirstLastNameOnly" />
								</div>
								<div class="grid_3">
									<s:property value="leaveStatusDesc" />
								</div>
								<div class="grid_3">
									<s:property value="startDateStr" />
								</div>
								<div class="grid_3">
									<s:property value="endDateStr" />
								</div>
								<div class="grid_3">
									<s:property value="days" />
								</div>
								<div class="grid_3">
									<s:url id="addGroupMember" action="ajaxDoApprovalsLeavesReport"
										includeParams="all" escapeAmp="false">
										<s:param name="leavesId" value="%{leavesId}" />
									</s:url>
									<sj:a href="%{addGroupMember}"
										onCompleteTopics="doInitAddGroupMember"
										onBeforeTopics="cleanOpenRows" indicator="indicator"
										targets="addLeader%{leavesId}">View</sj:a>
								</div>
								<div id="addLeader<s:property value='leavesId' />"
									style="display: none;" class='load'>
								</div>
							</div>
						</s:iterator>
					</div>
				</s:if>
				<s:else>
				</s:else>
			</div>
		</s:if>

		<div>
			<b> Parent Applying Explanation Leave Approvals As Class Teacher:</b>
		</div>
		<br />
		<div class="parentPendingApprovalsLeaves">
			<a href="#">Pending(<s:property value="leavesList.size()" />):</a>
		</div>
		<div class="parentPendingApprovalsLeavesBody">
			<s:if test="%{leavesList != null && !leavesList.isEmpty()}">
				<div style="padding-top: 1px">
					<div class="grid_12 th">
						<div class="grid_3">
							Name
						</div>
						<div class="grid_3">
							Type
						</div>
						<div class="grid_3">
							Start Date
						</div>
						<div class="grid_3">
							End Date
						</div>
						<div class="grid_3">
							Total Days
						</div>
						<div class="grid_3">
							Action
						</div>
					</div>
					<div id="resultsPage">
						<s:iterator value="leavesList">
							<div class="grid_12 th">
								<div class="loaded">
									<div class="grid_3">
										<s:property value="PersonFirstLastNameOnly" />
									</div>
									<div class="grid_3">
										<s:property value="leaveStatusDesc" />
									</div>
									<div class="grid_3">
										<s:property value="startDateStr" />
									</div>
									<div class="grid_3">
										<s:property value="endDateStr" />
									</div>
									<div class="grid_3">
										<s:property value="days" />
									</div>
									<div class="grid_3">

										<s:url id="addGroupMember"
											action="ajaxDoApprovalsLeavesReport" includeParams="all"
											escapeAmp="false">
											<s:param name="leavesId" value="%{leavesId}" />
										</s:url>
										<sj:a href="%{addGroupMember}"
											onCompleteTopics="doInitAddGroupMember"
											onBeforeTopics="cleanOpenRows" indicator="indicator"
											targets="addLeader%{leavesId}">Action</sj:a>
									</div>
								</div>
								<div id="addLeader<s:property value='leavesId' />"
									style="display: none;" class='load'>
								</div>
							</div>
						</s:iterator>
					</div>
				</div>
			</s:if>
			<s:else>

			</s:else>
		</div>
	</fieldset>
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
<script type="text/javascript">
changePageTitle("Leave Approvals");
$.subscribe('doInitAddGroupMember', function(event, data) {
	var rowObj = $('#' + data.id);
	if (rowObj.is(":hidden")) {
		rowObj.show();
	} else {
		rowObj.hide();
	}
});

$(document).ready(function() {

	$(".pendingApprovalsLeaves").click(function()

	{

		$(".pendingApprovalsLeavesBody").slideToggle(600);

	});
	$(".pendingApprovalsLeavesBody").hide();

});

$(document).ready(function() {

	$(".acceptApprovalsLeaves").click(function()

	{
		$(".acceptApprovalsLeavesBody").slideToggle(600);

	});
	$(".acceptApprovalsLeavesBody").hide();

});
$(document).ready(function() {

	$(".rejectApprovalsLeaves").click(function()

	{
		$(".rejectApprovalsLeavesBody").slideToggle(600);

	});
	$(".rejectApprovalsLeavesBody").hide();

});

$(document).ready(function() {

	$(".pendingStudentApprovalsLeaves").click(function()

	{

		$(".pendingStudentApprovalsLeavesBody").slideToggle(600);

	});
	$(".pendingStudentApprovalsLeavesBody").hide();

});

$(document).ready(function() {

	$(".acceptStudentApprovalsLeaves").click(function()

	{
		$(".acceptStudentApprovalsLeavesBody").slideToggle(600);

	});
	$(".acceptStudentApprovalsLeavesBody").hide();

});
$(document).ready(function() {

	$(".rejectStudentApprovalsLeaves").click(function()

	{
		$(".rejectStudentApprovalsLeavesBody").slideToggle(600);

	});
	$(".rejectStudentApprovalsLeavesBody").hide();

});

$(document).ready(function() {

	$(".parentPendingApprovalsLeaves").click(function()

	{
		$(".parentPendingApprovalsLeavesBody").slideToggle(600);

	});
	$(".parentPendingApprovalsLeavesBody").hide();

});
</script>
