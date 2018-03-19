<%@ include file="/common/taglibs.jsp"%>


<td colspan="5" style="background-color: #CCC;">
	<s:form action="ajaxUpdateLeave" theme="css_xhtml"
		id="formApprovalLeaves">

		<s:hidden name="viewStaffLeaveDetails.leavesId" />
		<div class="grid_11">
			<div class="grid_3" style="text-align: left;">
				<h2>
					Apply Leave
				</h2>
			</div>
		</div>

		<div class="grid_10">
			<div class="grid_4" >
				<b>Emp Id:</b>
			</div>
			<div class="grid_4">
				<s:property value="viewStaffLeaveDetails.username" />
			</div>
			<br />
			<br />
			<div class="grid_4" >
				<b>Emp Name:</b>
			</div>
			<div class="grid_4">
				<s:property value="viewStaffLeaveDetails.personFullName" />
			</div>
			<br />
			<br />
			<div class="grid_4" >
				<b>Leave Type:</b>
			</div>
			<div class="grid_4">
				<s:property value="viewStaffLeaveDetails.leaveStatusDesc" />
			</div>
			<br />
			<br />
			<div class="grid_4" >
				<b>Start Date:</b>
			</div>
			<div class="grid_4" align="left">
				<s:property value="viewStaffLeaveDetails.startDateStr" />
			</div>
			<br />
			<br />
			<div class="grid_4" >
				<b>End Date:</b>
			</div>
			<div class="grid_4">
				<s:property value="viewStaffLeaveDetails.endDateStr" />
			</div>
			<br />
			<br />
			<div class="grid_4" >
				<b>Total Leaves Requested :</b>
			</div>
			<div class="grid_4">
				<s:property value="viewStaffLeaveDetails.days" />
				<b>&nbsp;days</b>
			</div>
			<br />
			<br />
			<div class="grid_4" >
				<b>Leaves Available:</b>
			</div>
			<div class="grid_4">
				<s:if test="%{viewStaffLeaveDetails.leaveType == 'CL' }">
					<s:property value="classId" /> days
					</s:if>
				<s:elseif test="%{viewStaffLeaveDetails.leaveType == 'SL' }">
					<s:property value="className" /> days
					</s:elseif>
			</div>
			<br />
			<br />
			<div class="grid_4" >
				<b>Comments:</b>
			</div>
			<div class="grid_4">
				<s:property value="viewStaffLeaveDetails.description" />
			</div>
			<div class="grid_4" >
				<b>Leaves Taken Last 30 days:</b>
			</div>
			<div class="grid_4">
				<s:property value="fileName" />
				&nbsp;days
			</div>
			
			
			<s:if test='%{leave.leaveStatus == "P" || leave.leaveStatus == "AS" }'>
			<div class="grid_8">
				<div class="grid_4" >
					&nbsp;
				</div>
				<div class="grid_4" style="text-align: left;">
					<input type="radio" value="A" name="leave.leaveStatus" style="vertical-align: top;" checked>Accept
					<input type="radio" value="R" name="leave.leaveStatus" style="vertical-align: top;">Reject
				</div>
			</div>
			<s:if test='%{leave.leaveStatus == "AS" }'>
				<div class="grid_6">
					<div class="grid_4" style="text-align: left;">
						<sj:textarea name="leave.description" id="groupDescription"
							label="Description" cssClass="required" required="true" rows="5"
							cols="40"></sj:textarea>
					</div>
				</div>
			</s:if>
			<s:elseif test='%{leave.leaveStatus == "P" }'>
				<div class="grid_8">
					<div class="grid_4" >
						Description:
					</div>
					<div class="grid_4" style="text-align: left;">
						<sj:textarea name="leave.approvalsComment" id="groupDescription"
							cssClass="required" required="true" rows="5"
							cols="40"></sj:textarea>
					</div>
				</div>
			</s:elseif>
			<div class="grid_6" >
					<sj:submit   cssClass="submit small" value="Submit"
						indicator="indicator" targets="applyLeave"
						onClickTopics="formValidationForApprovalLeaves" />
					<s:url id="doCancelLeave" action="ajaxDoCancelLeave"
						includeParams="all"></s:url>
					<sj:a href="%{doCancelLeave}" cssClass="cancelButton"
						indicator="indicator" targets="applyLeave">Cancel</sj:a>
				</div>
		</s:if>
		</div>

	</s:form>


</td>
<script type="text/javascript">
	$(document).ready(function() {
		var validator;
		$.subscribe('formValidationForApprovalLeaves', function(event, data) {
			if ($('#formApprovalLeaves').valid())
				return true;
			else
				return false;
		});
	});
</script>


