<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js"></script>
<jsp:include page="/common/messages.jsp"></jsp:include>
<!--<div class="block grid_12">
	       <div class="block_head">
		<b>Self</b>
</div>
--><div class="block_content">
	<table class="striped" width="100%" style="margin-bottom: 0;"
		cellpadding="1" cellspacing="1">
		<tr>
			<th>
				Type
			</th>
			<th>
				Total
			</th>
			<th>
				Used
			</th>
			<th>
				Remaining
			</th>
		</tr>
		<tr>
			<td>
				Sick
			</td>
			<td>
				<s:property value="viewStaffPersonAccountDetails.sickLeaves" />
			</td>
			<td>
				<s:property value="sickLeave" />
			</td>
			<td>
				<s:property value="anyId" />
			</td>
		</tr>
		<tr>
			<td>
				Casual
			</td>
			<td>
				<s:property value="viewStaffPersonAccountDetails.casualLeaves" />
			</td>
			<td>
				<s:property value="casualLeave" />
			</td>
			<td>
				<s:property value="classId" />
			</td>
		</tr>
		<tr>
			<td>
				Total
			</td>
			<td>
				<s:property value="viewStaffPersonAccountDetails.displayTotalLeaves" />
			</td>
			<td>
				<s:property value="subjectId" />
			</td>
			<td>
				<s:property value="balance" />
			</td>
		</tr>
	</table>

	<div>
	&nbsp;
	<br />
</div>

<div>
		<b>Status:</b>
</div>
<div class="pendingLeaves" style="width: 50px;">
		<a href="#">Pending(<s:property value="leavesList.size()" />):</a>
</div>
<div class="pendingLeavesBody">
	<s:if test="%{leavesList != null && !leavesList.isEmpty()}">
		<div style="padding-top: 1px">
			<table class="striped" width="100%" style="margin-bottom: 0;"
				cellpadding="1" cellspacing="1">
				<thead>
					<tr>
						<th width="20%" class="head">
							Type
						</th>
						<th width="20%" class="head">
							Start Date
						</th>
						<th width="20%" class="head">
							End Date
						</th>
						<th width="20%" class="head">
							Total Days
						</th>
						<s:if test='%{(#session.academicYear == null || #session.academicYear.isEmpty()) || (#session.previousYear=="N")}'>
						<th width="" class="head">
							Edit
						</th>
						</s:if>
						
					</tr>
				</thead>
			</table>
			<div id="resultsPage">
				<s:iterator value="leavesList">
					<table class="striped" width="100%" style="margin-bottom: 0;border-width: 1px 1px 1px;"
						cellpadding="1" cellspacing="1" id="results">
						<tr class='loaded'>
							<td width="20%">
								<s:property value="leaveStatusDesc" />
							</td>
							<!--<td style="width: 100px;" class="head">
								<s:url id="removeGroup" action="ajaxDeleteLeave"
									includeParams="all" escapeAmp="false">
									<s:param name="id" value="id"></s:param>
								</s:url>
								<s:div cssClass="close emsRemove" id='%{removeGroup}'
									theme="simple" title="Delete this group"></s:div>
								<s:property value="leaveStatusDesc" />
							</td>
							--><td width="20%" class="head">
								<s:property value="startDateStr" />
							</td>
							<td width="20%" class="head">
								<s:property value="endDateStr" />
							</td>
							<td width="20%" class="head">
								<s:property value="days" />
							</td>
							<s:if test='%{(#session.academicYear == null || #session.academicYear.isEmpty()) || (#session.previousYear=="N")}'>
							<td width="20%" class="head">
								<s:url id="editGroup" action="ajaxDoEditleaveReport"
									includeParams="all" escapeAmp="false">
									<s:param name="id" value="{id}" />
								</s:url>
								<sj:a href="%{editGroup}" targets="applyLeave"
									indicator="indicator" button="false"
									buttonIcon="ui-icon-pencil"  cssClass="normalEdit" title="Edit">
									<a href="#" title="Edit" class="normalEdit"></a>
								</sj:a>
							</td>
							</s:if>
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
	</s:else>
</div>
<br/>
<div class="approvedLeaves" style="width: 50px;">
	<a href="#">Approval(<s:property value="approvedLeavesList.size()" />):</a>
</div>
<div class="approvedLeavesBody">
	<s:if
		test="%{approvedLeavesList != null && !approvedLeavesList.isEmpty()}">

		<div style="padding-top: 1px">
			<table class="striped" width="100%" style="margin-bottom: 0;"
				cellpadding="1" cellspacing="1">
				<thead>
					<tr>
						<th width="20%">
							Type
						</th>
						<th width="20%">
							Start Date
						</th>
						<th width="20%">
							End Date
						</th>
						<th width="20%">
							Total Days
						</th>
						<th>
						View
					</th>
				
					</tr>
				</thead>
			</table>
			<div id="resultsPage">
				<s:iterator value="approvedLeavesList">
					<table class="striped" width="100%" style="margin-bottom: 0;border-width: 1px 1px 1px;"
						cellpadding="1" cellspacing="1">
						<tr class='loaded'>
							<td width="20%">
								<s:property value="leaveStatusDesc" />
							</td>
							<!--<td width="30%">
								<s:url id="removeGroup" action="ajaxDeleteLeave"
									includeParams="all" escapeAmp="false">
									<s:param name="id" value="id"></s:param>
								</s:url>
								<s:div cssClass="close emsRemove" id='%{removeGroup}'
									theme="simple" title="Delete this group"></s:div>
								<s:property value="leaveStatusDesc" />
							</td>
							
							--><td width="20%">
								<s:property value="startDateStr" />
							</td>
							<td width="20%">
								<s:property value="endDateStr" />
							</td>
							<td width="20%">
								<s:property value="days" />
							</td>
							
							<td width="20%" class="head">

								<s:url id="addGroupMember" action="ajaxDoSelfApprovalsLeavesReport"
									includeParams="all" escapeAmp="false">
									<s:param name="id" value="{id}" />
								</s:url>
								<sj:a href="%{addGroupMember}"
									onCompleteTopics="doSelfApprovalLeaves"
									onBeforeTopics="cleanOpenRows" indicator="indicator"
									targets="addLeader%{id}">View</sj:a>
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

	</s:else>
</div>
<br/>
<div class="rejectedLeaves" style="width: 50px;">
		<a href="#">Rejected(<s:property value="rejectedLeavesList.size()" />):</a>
</div>
<div class="rejectedLeavesBody">
	<s:if
		test="%{rejectedLeavesList != null && !rejectedLeavesList.isEmpty()}">

		<div style="padding-top: 1px">
			<table class="striped" width="100%" style="margin-bottom: 0;"
				cellpadding="1" cellspacing="1">
				<thead>
					<tr>
						<!--<th width="30%">
						Subject
					</th>
					<th width="18%">
						Leave Type
					</th>
					-->
						<th width="20%">
							Type
						</th>
						<th width="20%">
							Start Date
						</th>
						<th width="20%">
							End Date
						</th>
						<th width="20%">
							Total Days
						</th>
						<th>
							View
						</th>
				
					</tr>
				</thead>
			</table>
			<div id="resultsPage">
				<s:iterator value="rejectedLeavesList">
					<table class="striped" width="100%" style="margin-bottom: 0;border-width: 1px 1px 1px;"
						cellpadding="1" cellspacing="1">
						<tr class='loaded'>
							<td width="20%">
								<s:property value="leaveStatusDesc" />
							</td>
							<!--<td width="30%">
								<s:url id="removeGroup" action="ajaxDeleteLeave"
									includeParams="all" escapeAmp="false">
									<s:param name="id" value="id"></s:param>
								</s:url>
								<s:div cssClass="close emsRemove" id='%{removeGroup}'
									theme="simple" title="Delete this group"></s:div>
								<s:property value="leaveStatusDesc" />
							</td>

							--><td width="20%">
								<s:property value="startDateStr" />
							</td>
							<td width="20%">
								<s:property value="endDateStr" />
							</td>
							<td width="20%">
								<s:property value="days" />
							</td>
							<td width="" class="head">
								<s:url id="addGroupMember" action="ajaxDoSelfApprovalsLeavesReport"
									includeParams="all" escapeAmp="false">
									<s:param name="id" value="{id}" />
								</s:url>
								<sj:a href="%{addGroupMember}"
									onCompleteTopics="doSelfApprovalLeaves"
									onBeforeTopics="cleanOpenRows" indicator="indicator"
									targets="addLeader%{id}">View</sj:a>
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

	</s:else>
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
	$.subscribe('doSelfApprovalLeaves', function(event, data) {
		var rowObj = $('#' + data.id);
		if (rowObj.is(":hidden")) {
			rowObj.show();
		} else {
			rowObj.hide();
		}
	});
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
		$(".rejectedLeavesBody").hide();

	});
</script>
