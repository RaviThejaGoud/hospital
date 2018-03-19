<%@ include file="/common/taglibs.jsp"%>
<td colspan="6" style="background-color: #CCC;">
	<s:form action="ajaxUpdateLeave" theme="css_xhtml"
		id="formApprovalLeaves">
		<s:hidden name="viewStaffPersonAccountDetails.roleName" />
		<s:if test="%{viewStaffPersonAccountDetails.roleName == 'ROLE_HOD' }">
			<s:if test="%{viewStaffLeaveDetails != null}">
				<s:hidden name="viewStaffLeaveDetails.leavesId" />
			<div class="grid_8">
				<div class="grid_3" style="text-align: left;">
					<h2>
						Leave Application
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
				<!--<label>
						Availability:
					</label>
					<br/>
					<s:if test="%{viewStaffLeaveDetails.leaveType == 'CL' }">
						Using Casual Leaves: <s:property value="casualLeave" /> days <br/>
						Remaining Casual Leaves: <s:property value="classId" /> days
					</s:if>
					<s:elseif test="%{viewStaffLeaveDetails.leaveType == 'SL' }">
						Using Sick Leaves: <s:property value="sickLeave" /> days <br/>
						Remaining Sick Leaves: <s:property value="className" /> days
					</s:elseif>
					-->
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
			</div>
			</s:if>
			<s:elseif test="%{viewStudentLeaveDetails != null}">
				<s:hidden name="viewStudentLeaveDetails.leavesId" />
			<s:hidden name="viewStudentLeaveDetails.classId" />
			<s:hidden name="viewStudentLeaveDetails.accountId" />
			<s:hidden name="viewStudentLeaveDetails.startDate" />
			<s:hidden name="viewStudentLeaveDetails.endDate" />
			<div class="grid_6">
				<div class="grid_3" style="text-align: left;">
					<h2>
						Apply Leave
					</h2>
				</div>
			</div>
			<div class="grid_10">
				<div class="grid_6" style="text-align: left;">
					<div class="grid_4" >
						Student Id:
					</div>
					<s:property value="viewStudentLeaveDetails.rollNumber" />
				</div>
				<br />
				<br />
				<div class="grid_4" >
					Student Name:
				</div>
				<div class="grid_4">
					<s:property value="viewStudentLeaveDetails.personFullName" />
				</div>
				<br />
				<br />
				<div class="grid_4" >
					Student Class:
				</div>
				<div class="grid_4">
					<s:property value="viewStudentLeaveDetails.classAndSection" />
				</div>
				<br />
				<br />
				<div class="grid_4" >
					Leave Type:
				</div>
				<div class="grid_4">
					<s:property value="viewStudentLeaveDetails.leaveStatusDesc" />
				</div>
				<br />
				<br />
				<div class="grid_4" >
					Start Date:
				</div>
				<div class="grid_4">
					<s:property value="viewStudentLeaveDetails.startDateStr" />
				</div>
				<br />
				<br />
				<div class="grid_4" >
					End Date:
				</div>
				<div class="grid_4">
					<s:property value="viewStudentLeaveDetails.endDateStr" />
				</div>
				<br />
				<br />
				<div class="grid_4" >
					Comments:
				</div>
				<div class="grid_4">
					<s:if test='%{leave.leaveStatus == "AS" }'>
						<s:property value="leave.approvalsComment" />
					</s:if>
					<s:else>
						<s:property value="viewStudentLeaveDetails.description" />
					</s:else>
				</div>
				
			</div>
			</s:elseif>
		</s:if>
		<s:elseif test="%{viewStaffPersonAccountDetails.roleName == 'ROLE_PRINCIPAL' }">
				<s:hidden name="viewStaffLeaveDetails.leavesId" />
			<div class="grid_8">
				<div class="grid_3" style="text-align: left;">
					<h2>
						Leave Application
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
				<!--<label>
						Availability:
					</label>
					<br/>
					<s:if test="%{viewStaffLeaveDetails.leaveType == 'CL' }">
						Using Casual Leaves: <s:property value="casualLeave" /> days <br/>
						Remaining Casual Leaves: <s:property value="classId" /> days
					</s:if>
					<s:elseif test="%{viewStaffLeaveDetails.leaveType == 'SL' }">
						Using Sick Leaves: <s:property value="sickLeave" /> days <br/>
						Remaining Sick Leaves: <s:property value="className" /> days
					</s:elseif>
					-->
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
			</div>
		</s:elseif>
		<s:elseif
			test="%{viewStaffPersonAccountDetails.roleName == 'ROLE_TEACHER' }">
			<s:hidden name="viewStudentLeaveDetails.leavesId" />
			<s:hidden name="viewStudentLeaveDetails.classId" />
			<s:hidden name="viewStudentLeaveDetails.accountId" />
			<s:hidden name="viewStudentLeaveDetails.startDate" />
			<s:hidden name="viewStudentLeaveDetails.endDate" />
			<div class="grid_6">
				<div class="grid_3" style="text-align: left;">
					<h2>
						Apply Leave
					</h2>
				</div>
			</div>
			<div class="grid_10">
				<div class="grid_6" style="text-align: left;">
					<div class="grid_4" >
						Student Id:
					</div>
					<s:property value="viewStudentLeaveDetails.rollNumber" />
				</div>
				<br />
				<br />
				<div class="grid_4" >
					Student Name:
				</div>
				<div class="grid_4">
					<s:property value="viewStudentLeaveDetails.personFullName" />
				</div>
				<br />
				<br />
				<div class="grid_4" >
					Student Class:
				</div>
				<div class="grid_4">
					<s:property value="viewStudentLeaveDetails.classAndSection" />
				</div>
				<br />
				<br />
				<div class="grid_4" >
					Leave Type:
				</div>
				<div class="grid_4">
					<s:property value="viewStudentLeaveDetails.leaveStatusDesc" />
				</div>
				<br />
				<br />
				<div class="grid_4" >
					Start Date:
				</div>
				<div class="grid_4">
					<s:property value="viewStudentLeaveDetails.startDateStr" />
				</div>
				<br />
				<br />
				<div class="grid_4" >
					End Date:
				</div>
				<div class="grid_4">
					<s:property value="viewStudentLeaveDetails.endDateStr" />
				</div>
				<br />
				<br />
				<div class="grid_4" >
					Comments:
				</div>
				<div class="grid_4">
					<s:if test='%{leave.leaveStatus == "AS" }'>
						<s:property value="leave.approvalsComment" />
					</s:if>
					<s:else>
						<s:property value="viewStudentLeaveDetails.description" />
					</s:else>
				</div>
				
			</div>
			
		</s:elseif>
		<br />
		<div class="grid_10">
			<div class="grid_4" >
				Leaves Taken Last 30 days:
			</div>
			<div class="grid_4">
				<s:property value="balance" />
				&nbsp;days
			</div>
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
			<s:if test='%{(#session.academicYear == null || #session.academicYear.isEmpty()) || (#session.previousYear=="N")}'>
			<div class="grid_6">
				<div class="grid_4" style="text-align: left;">
					<sj:submit   cssClass="submit small" value="Submit"
						indicator="indicator" targets="applyLeave"
						onClickTopics="formValidationForApprovalLeaves" />

					<s:url id="doCancelLeave" action="ajaxDoCancelLeave"
						includeParams="all"></s:url>
					<sj:a href="%{doCancelLeave}" cssClass="cancelButton"
						indicator="indicator" targets="applyLeave">Cancel</sj:a>
				</div>
			</div>
			</s:if>
		</s:if>
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
