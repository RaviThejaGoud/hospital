<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>
<jsp:include page="/common/messages.jsp"></jsp:include>
<div id="steps13">
	<fieldset id="stepAttendance" class="step13">
		<div class="grid_14">
			<div class="grid_12 th">
				<div class="grid_3">
					Type
				</div>
				<div class="grid_3">
					Total
				</div>
				<div class="grid_3">
					Used
				</div>
				<div class="grid_3">
					Remaining
				</div>
			</div>
			<div class="grid_12 row">
				<div class="grid_3">
					Sick
				</div>
				<div class="grid_3">
					<s:property value="viewStaffPersonAccountDetails.sickLeaves" />
				</div>
				<div class="grid_3">
					<s:property value="sickLeave" />
				</div>
				<div class="grid_3">
					<s:property value="anyId" />
				</div>
			</div>
			<div class="grid_12 row">
				<div class="grid_3">
					Casual
				</div>
				<div class="grid_3">
					<s:property value="viewStaffPersonAccountDetails.casualLeaves" />
				</div>
				<div class="grid_3">
					<s:property value="casualLeave" />
				</div>
				<div class="grid_3">
					<s:property value="classId" />
				</div>
			</div>
			<div class="grid_12 row">
				<div class="grid_3">
					Total
				</div>
				<div class="grid_3">
					<s:property
						value="viewStaffPersonAccountDetails.displayTotalLeaves" />
				</div>
				<div class="grid_3">
					<s:property value="subjectId" />
				</div>
				<div class="grid_3">
					<s:property value="balance" />
				</div>
			</div>
		</div>

		<div class="grid_14">
			<h6>
				My Leaves:
			</h6>
		</div>
		<div class="pendingLeaves grid_14">
			<h6>
				<a href="#">Pending(<s:property value="leavesList.size()" />):</a>
			</h6>
		</div>
		<div class="pendingLeavesBody">
			<s:if test="%{leavesList != null && !leavesList.isEmpty()}">
				<div class="grid_12 th">
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
						Edit
					</div>
				</div>
				<div id="resultsPage">
					<s:iterator value="leavesList">
						<div class="grid_12 row">
							<div class='loaded'>
								<!--<td width="30%">
								<s:url id="removeLeave" action="ajaxDeleteLeave"
									includeParams="all" escapeAmp="false">
									<s:param name="id" value="id"></s:param>
								</s:url>
								<s:div cssClass="close emsRemove" id='%{removeLeave}'
									theme="simple" title="Delete this Pending Leave"></s:div>
								<s:property value="leaveStatusDesc" />
							</td>
							-->
								<div class="grid_3">
									<s:property value="leaveStatusDesc" />
								</div>
								<div class="grid_3">
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
									<s:url id="editGroup" action="ajaxDoEditAdminleave"
										includeParams="all" escapeAmp="false">
										<s:param name="id" value="{id}" />
									</s:url>
									<sj:a href="%{editGroup}" targets="applyLeave"
										indicator="indicator" button="false"
										buttonIcon="ui-icon-pencil"  cssClass="normalEdit" title="Edit">
									</sj:a>
								</div>
								<div class="grid_3">
									<div id="addLeader<s:property value='id' />"
										style="display: none;" class='load'>
									</div>
								</div>
							</div>
						</div>
					</s:iterator>
				</div>
			</s:if>
			<s:else>
				<!--<br/>
	Currently there are no Pending Leaves.
-->
			</s:else>
		</div>

		<div class="approvedLeaves grid_14">
			<h6>
				<a href="#">Approval(<s:property
						value="approvedLeavesList.size()" />):</a>
			</h6>
		</div>
		<div class="approvedLeavesBody">
			<s:if
				test="%{approvedLeavesList != null && !approvedLeavesList.isEmpty()}">
				<div class="grid_12 th">
					<div class="grid_3">
						Type Start Date
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
					<s:iterator value="approvedLeavesList">
						<div class="grid_12 row">
							<div class='loaded'>
								<!--<td width="30%">
								<s:url id="removeGroup" action="ajaxDeleteLeave"
									includeParams="all" escapeAmp="false">
									<s:param name="id" value="id"></s:param>
								</s:url>
								<s:div cssClass="close emsRemove" id='%{removeGroup}'
									theme="simple" title="Delete this group"></s:div>
								<s:property value="leaveStatusDesc" />
							</td>
							-->
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
										action="ajaxDoSelfApprovalsLeavesReport" includeParams="all"
										escapeAmp="false">
										<s:param name="id" value="{id}" />
									</s:url>
									<sj:a href="%{addGroupMember}"
										onCompleteTopics="doInitAddGroupMember"
										onBeforeTopics="cleanOpenRows" indicator="indicator"
										targets="addLeader%{id}">View</sj:a>
								</div>
								<div id="addLeader<s:property value='id' />"
									style="display: none;" class='load'>
								</div>
							</div>
						</div>
					</s:iterator>
				</div>
			</s:if>
			<s:else>
				<!--<br/>
		Currently there are no Apporoved Leaves.
	-->
			</s:else>
		</div>
		<br />
		<div class="rejectedLeaves grid_14" style="width: 50px;">
			<h6>
				<a href="#">Rejected(<s:property
						value="rejectedLeavesList.size()" />):</a>
			</h6>
		</div>
		<div class="rejectedLeavesBody">
			<s:if
				test="%{rejectedLeavesList != null && !rejectedLeavesList.isEmpty()}">

				<div style="padding-top: 1px">
					<div class="grid_12 th">
						<div class="grid_3">
							<!--<th width="30%">
						Subject
					</th>
					<th width="18%">
						Leave Type
					</th>
					-->

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
						<s:iterator value="rejectedLeavesList">
							<div class="grid_12 th">
								<div class='loaded'>

									<!--<td width="30%">
								<s:url id="removeGroup" action="ajaxDeleteLeave"
									includeParams="all" escapeAmp="false">
									<s:param name="id" value="id"></s:param>
								</s:url>
								<s:div cssClass="close emsRemove" id='%{removeGroup}'
									theme="simple" title="Delete this group"></s:div>
								<s:property value="leaveStatusDesc" />
							</td>
							-->

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
											action="ajaxDoSelfApprovalsLeavesReport" includeParams="all"
											escapeAmp="false">
											<s:param name="id" value="{id}" />
										</s:url>
										<sj:a href="%{addGroupMember}"
											onCompleteTopics="doInitAddGroupMember"
											onBeforeTopics="cleanOpenRows" indicator="indicator"
											targets="addLeader%{id}">View</sj:a>
									</div>
									<div id="addLeader<s:property value='id' />"
										style="display: none;" class='load'>
									</div>
									</div>
									</div>
									
						</s:iterator>
					</div>

				</div>
			</s:if>
			<s:else>
				<!--<br/>
		Currently there are no Rejected Leaves.
	-->
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
<script language="JavaScript" type="text/javascript">
changePageTitle('My Leaves');

$(document).ready(function() {

	$(".pendingLeaves").click(function()

	{

		$(".pendingLeavesBody").slideToggle(600);

	});
	$(".pendingLeavesBody").hide();

});

$(document).ready(function() {

	$(".approvedLeaves").click(function()

	{

		$(".approvedLeavesBody").slideToggle(600);

	});
	$(".approvedLeavesBody").hide();

});
$(document).ready(function() {

	$(".rejectedLeaves").click(function()

	{

		$(".rejectedLeavesBody").slideToggle(600);

	});
	$(".approvedLeavesBody").hide();

});
</script>
