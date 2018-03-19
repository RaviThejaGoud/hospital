<%@ include file="/common/taglibs.jsp"%>
<td colspan="6" style="background-color: #CCC;">
	<div class="grid_6">
		<div class="grid_3" style="text-align: left;">
			<h2>
				View Leave Application
			</h2>
		</div>
	</div>
	<div class="grid_10">
		<div class="grid_4" >
			Emp Id:
		</div>
		<div class="grid_4">
			<s:property value="viewStaffLeaveDetails.username" />
		</div>
		<br />
		<br />
		<div class="grid_4" >
			Emp Name:
		</div>
		<div class="grid_4">
			<s:property value="viewStaffLeaveDetails.personFullName" />
		</div>
		<br />
		<br />
		<div class="grid_4" >
			Leave Type:
		</div>
		<div class="grid_4">
			<s:property value="viewStaffLeaveDetails.leaveStatusDesc" />
		</div>
		<br />
		<br />
		<div class="grid_4" >
			Start Date:
		</div>
		<div class="grid_4" align="left">
			<s:property value="viewStaffLeaveDetails.startDateStr" />
		</div>
		<br />
		<br />
		<div class="grid_4" >
			End Date:
		</div>
		<div class="grid_4">
			<s:property value="viewStaffLeaveDetails.endDateStr" />
		</div>
		<br />
		<br />
		<div class="grid_4" >
			Total Leaves Requested :
		</div>
		<div class="grid_4">
			<s:property value="viewStaffLeaveDetails.days" />
			&nbsp;days
		</div>
		<br />
		<br />
		<div class="grid_4" >
			Leaves Available:
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
			Comments:
		</div>
		<div class="grid_4">
			<s:property value="viewStaffLeaveDetails.description" />
		</div>
		<div class="grid_4" >
			Leaves Taken Last 30 days:
		</div>
		<div class="grid_4">
			<s:property value="balance" />
			&nbsp;days
		</div>
	</div>


</td>
