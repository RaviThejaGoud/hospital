<%@ include file="/common/taglibs.jsp"%>
<div id="commonTabContent" class="grid_11">
	<div id="commonTabWrapper">
		<div id="commonStep">
			<fieldset>
				<div class="grid_11">
					<div class="grid_6">
						<div class="grid_4">
							<b>
								View Leave Application
							</b>
						</div>
					</div>
					<div class="grid_10">
						<div class="grid_4">
							<label class="labelRight">Emp Id:</label>
						</div>
						<div class="grid_4">
							<s:property value="viewStaffLeaveDetails.username" />
						</div>
						<div class="grid_4">
						<label class="labelRight">Emp Name:</label>
						</div>
						<div class="grid_4">
							<s:property value="viewStaffLeaveDetails.personFullName" />
						</div>

						<div class="grid_4">
						<label class="labelRight">Leave Type:</label>
						</div>
						<div class="grid_4">
							<s:property value="viewStaffLeaveDetails.leaveStatusDesc" />
						</div>

						<div class="grid_4">
						<label class="labelRight">Start Date:</label>
						</div>
						<div class="grid_4" align="left">
							<s:property value="viewStaffLeaveDetails.startDateStr" />
						</div>

						<div class="grid_4">
						<label class="labelRight">End Date:</label>
						</div>
						<div class="grid_4">
							<s:property value="viewStaffLeaveDetails.endDateStr" />
						</div>

						<div class="grid_4">
						<label class="labelRight">Total Leaves Requested:</label>
						</div>
						<div class="grid_4">
							<s:property value="viewStaffLeaveDetails.days" />
							&nbsp;days
						</div>

						<div class="grid_4">
						<label class="labelRight">Leaves Available:</label>
						</div>
						<div class="grid_4">
							<s:if test="%{viewStaffLeaveDetails.leaveType == 'CL' }">
								<s:property value="classId" /> days
					</s:if>
							<s:elseif test="%{viewStaffLeaveDetails.leaveType == 'SL' }">
								<s:property value="className" /> days
					</s:elseif>
						</div>

						<div class="grid_4">
						<label class="labelRight">Comments:</label>
						</div>
						<div class="grid_4">
							<s:property value="viewStaffLeaveDetails.description" />
						</div>
						<div class="grid_4">
						<label class="labelRight">Leaves Taken Last 30days:</label>
						</div>
						<div class="grid_4">
							<s:property value="balance" />
							&nbsp;days
						</div>
					</div>
				</div>
			</fieldset>
		</div>
	</div>
</div>
