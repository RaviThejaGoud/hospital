<%@ include file="/common/taglibs.jsp"%>
<jsp:include page="/common/messages.jsp"></jsp:include>
<div id="applyLeaveDiv">
	<table
		class="table table-striped table-bordered table-hover table-full-width"
		id="sample_2">
		<thead>
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
		</thead>
		<tbody>
			<tr>
				<td>
					Sick Leave
				</td>
				<td>
					<s:property value="leaveManagement.sickLeaves" />
				</td>
				<td>
					<s:property value="sickLeave" />
				</td>
				<td>
					<s:property value="(leaveManagement.sickLeaves - sickLeave)" />
				</td>
			</tr>
			<tr>
				<td>
					Casual Leave
				</td>
				<td>
					<s:property value="leaveManagement.casualLeaves" />
				</td>
				<td>
					<s:property value="casualLeave" />
				</td>
				<td>
					<s:property value="(leaveManagement.casualLeaves - casualLeave)" />
				</td>
			</tr>
			<tr>
				<td>
					Earned Leave
				</td>
				<td>
					<s:property value="leaveManagement.earnedLeaves" />
				</td>
				<td>
					<s:property value="earnedLeave" />
				</td>
				<td>
					<s:property value="(leaveManagement.earnedLeaves - earnedLeave)" />
				</td>
			</tr>
			<tr>
				<td>
					Total

				</td>
				<td>
					<s:property
						value="(leaveManagement.sickLeaves + leaveManagement.casualLeaves + leaveManagement.earnedLeaves)" />
				</td>
				<td>
					<s:property value="(sickLeave + casualLeave + earnedLeave)" />
				</td>
				<td>
					<s:property
						value="((leaveManagement.sickLeaves - sickLeave) + (leaveManagement.casualLeaves - casualLeave) + (leaveManagement.earnedLeaves - earnedLeave))" />
				</td>
			</tr>
			<s:if test='%{payLeaves > 0}'>
				<tr>
					<td>
						Pay Leave
					</td>
					<td>
						&nbsp;
					</td>
					<td>
						<s:property value="payLeaves" />
					</td>
					<td>
						&nbsp;
					</td>
				</tr>
			</s:if>
		</tbody>
	</table>
	<div>
		<h4 class="pageTitle bold">
			Pending Leaves
		</h4>
		<s:if test="%{leavesList != null && !leavesList.isEmpty()}">
			<table
				class="table table-striped table-bordered table-hover table-full-width"
				id="sample_3">
				<thead>
					<tr>
						<th>
							Type
						</th>
						<th>
							Start Date
						</th>
						<th>
							End Date
						</th>
						<th>
							Total Days
						</th>
						<s:if
							test='%{(#session.academicYear == null || #session.academicYear.isEmpty()) || (#session.previousYear=="N")}'>
							<th>
								Edit
							</th>
						</s:if>
						<th>
							Delete
						</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="leavesList">
						<tr>
							<td>
								<s:property value="leaveStatusDesc" />
							</td>
							<td>
								<s:property value="userFormattedStartDate" />
							</td>
							<td>
								<s:property value="userFormattedEndDate" />
							</td>
							<td>
								<s:property value="leavesCount" />
							</td>
							<td>
								<s:if test='%{compareTwoDates}'>
									<a data-toggle="modal" href="#popupPendingLeavesDiv"
										class="btn btn-xs purple"
										onclick="javascript:popupEidtPendingLeaves(<s:property value="id" />);"><i
										class="fa fa-edit"></i>Edit </a>
								</s:if>
							</td>
							<td>
								<s:url id="removeLeave" action="ajaxRemoveStaffPendingLeave"
									includeParams="all" escapeAmp="false" namespace="/staff">
									<s:param name="anyId" value="id"></s:param>
								</s:url>
								<s:div cssClass="btn btn-xs red "
									onclick="javascript:confirmDialogWithTarget(this,'stepStaffLeavesDiv');"
									id='%{removeLeave}' title="Delete this leave">
									<i class="fa fa-times"></i>Delete</s:div>
							</td>
						</tr>
					</s:iterator>
				</tbody>
			</table>
		</s:if>
		<s:else>
			<div class="alert alert-info">
				No Pending leaves.
			</div>
		</s:else>
	</div>
	<div>
		<h4 class="pageTitle bold">
			Approved Leaves
		</h4>
		<s:if
			test="%{approvedLeavesList != null && !approvedLeavesList.isEmpty()}">
			<table
				class="table table-striped table-bordered table-hover table-full-width"
				id="sample_4">
				<thead>
					<tr>
						<th>
							Type
						</th>
						<th>
							Start Date
						</th>
						<th>
							End Date
						</th>
						<th>
							Total Days
						</th>
						<th>
							Comments
						</th>
						<s:if
							test='%{(#session.academicYear == null || #session.academicYear.isEmpty()) || (#session.previousYear=="N")}'>
							<th>
								Delete
							</th>
						</s:if>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="approvedLeavesList">
						<tr>
							<td>
								<s:property value="leaveStatusDesc" />
							</td>
							<td>
								<s:property value="userFormattedStartDate" />
							</td>
							<td>
								<s:property value="userFormattedEndDate" />
							</td>
							<td>
								<s:property value="leavesCount" />
							</td>
							<td>
								<ul class="tooltipDiv">
									<li>
										<a href="#">View</a>
										<ul class="tooltipSubDiv">
											<div class="popover bottom " style="display: none;">
												<div class="arrow"></div>
												<h3 class="popover-title">
													Comments
												</h3>
												<div class="popover-content">
													<s:property value="approvalsComment" />
												</div>
											</div>
										</ul>
									</li>
								</ul>
							</td>
							<td>
								<s:if
									test='%{(#session.academicYear == null || #session.academicYear.isEmpty()) || (#session.previousYear=="N")}'>
									<s:if test='%{startDate.compareTo(toDate) > 0}'>
										<s:url id="doCancelApprovedLeaves"
											action="ajaxDoStaffEditApprovedLeaves" includeParams="all" namespace="/common"
											escapeAmp="false">
											<s:param name="anyId" value="%{id}" />
											<s:param name="tempId" value="%{accountId}" />
											<s:param name="anyTitle">
												<s:property value="startDateStr" />
											</s:param>
											<s:param name="tempString">
												<s:property value="endDateStr" />
											</s:param>
										</s:url>
										<s:div cssClass="btn btn-xs red"
											id='%{doCancelApprovedLeaves}' theme="simple"
											onclick="javascript:confirmDialogWithTarget(this,'stepStaffLeavesDiv');"
											title="Delete this leave" >
											<i class="fa fa-times"></i>&nbsp;&nbsp;Delete</s:div>
									</s:if>
								</s:if>
							</td>
						</tr>
					</s:iterator>
				</tbody>
			</table>
		</s:if>
		<s:else>
			<div class="alert alert-info">
				No Approved leaves.
			</div>
		</s:else>
	</div>
	<div>
		<h4 class="pageTitle bold">
			Rejected Leaves
		</h4>
		<s:if
			test="%{rejectedLeavesList != null && !rejectedLeavesList.isEmpty()}">
			<table
				class="table table-striped table-bordered table-hover table-full-width"
				id="sample_editable_3">
				<thead>
					<tr>
						<th>
							Type
						</th>
						<th>
							Start Date
						</th>
						<th>
							End Date
						</th>
						<th>
							View
						</th>
						<th>
							Total Days
						</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="rejectedLeavesList">
						<tr>
							<td>
								<s:property value="leaveStatusDesc" />
							</td>
							<td>
								<s:property value="userFormattedStartDate" />
							</td>
							<td>
								<s:property value="userFormattedEndDate" />
							</td>
							<td>
								<ul class="tooltipDiv">
									<li>
										<a href="#">View</a>
										<ul class="tooltipSubDiv">
											<div class="popover bottom " style="display: none;">
												<div class="arrow"></div>
												<h3 class="popover-title">
													Comments
												</h3>
												<div class="popover-content">
													<s:property value="approvalsComment" />
												</div>
											</div>
										</ul>
									</li>
								</ul>
							</td>
							<td>
								<s:property value="leavesCount" />
							</td>
						</tr>
					</s:iterator>
				</tbody>
			</table>
		</s:if>
		<s:else>
			<div class="alert alert-info">
				No Rejected leaves.
			</div>
		</s:else>
	</div>
</div>
<div id="popupPendingLeavesDiv"></div>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/onload.js">
</script>
<script language="JavaScript" type="text/javascript">
$(document).ready(function() {
	TableAdvanced.init();
	TableEditable.init();
	UIExtendedModals.init();
changePageTitle('View Leaves');
$('ul.nav-tabs li').removeClass('active');
$('li#staffLeavesId').addClass('active');
$('li#princLeaves').addClass('active');

});
function popupEidtPendingLeaves(id) {
	var url = jQuery.url.getChatURL("/staff/ajaxDoGetleaveReport.do");
	$('#popupPendingLeavesDiv')
			.html(
					'<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
	$.ajax( {
		url : url,
		cache : false,
		data : "anyId=" + id,
		success : function(html) {
			$("#popupPendingLeavesDiv").html(html);
		}
	});
}
</script>
