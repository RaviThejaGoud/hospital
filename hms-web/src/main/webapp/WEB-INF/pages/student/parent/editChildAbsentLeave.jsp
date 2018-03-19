<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/common/textcounter.js">
</script>

<s:form action="ajaxEditChildAbsentLeaveDetailsForParent" theme="css_xhtml" id="editLeaveReportForParent" namespace="/student">
	<s:hidden name="viewStudentPersonAccountDetails.personFullName" />
	<s:hidden name="selectedId" />
	<div class="grid_9" style="padding-bottom: 5px;">
		<div class="grid_6" style="text-align: left;">
			<b>Student Id:</b>
			<s:property value="viewStudentPersonAccountDetails.username" />
		</div>
	</div>
	<div class="grid_9" style="padding-bottom: 5px;">

		<div class="grid_6" style="text-align: left;">
			<b>Student Name:</b>
			<s:property value="viewStudentPersonAccountDetails.personFullName" />
		</div>
	</div>
	<div class="grid_9" style="padding-bottom: 5px;">
		<div class="grid_6" style="text-align: left;">
			<b>Leave Taken:</b>
			<s:property value="leave.startDateStr" />
		</div>
	</div>
	<div class="grid_9" style="padding-bottom: 5px;">

		<div class="grid_6" style="text-align: left;">
			<b>Teacher Comment:</b>
			<s:property value="leave.description" />
		</div>
	</div>
	<!--<div class="grid_11">
		<div class="grid_3" style="text-align: left; width: 100px">
			<b>Date Applied:</b>
		</div>
		<div class="grid_6 ">
			<s:property value="todayDate" />
		</div>
	</div>
	--><div class="grid_11">
		<div class="grid_6" style="text-align: left;">
			<b> Leave Type:</b>
			<s:select list="#{'CL':'Causual Leave','SL':'Sick Leave'}"
				name="leave.leaveType" cssStyle="width:150px;height:25px"></s:select>
		</div>
	</div>
	<!--<div class="grid_11" style="padding-bottom: 5px;">
		<div class="grid_6" style="text-align: left;">
			<b>Duration:</b>
		</div>
	</div>
	-->
	<!--<div class="grid_11">
		<div class="grid_4">
			<sj:datepicker id="date0" label="Start Date" name="leave.startDate"
				onCompleteTopics="displayDate" cssClass="text small required"
				required="true" cssStyle="width:167px;height:15px;" />
		</div>
		<div class="grid_4">
			<s:select list="#{'9AM':'9AM','1PM':'1PM'}" label="Start Time"
				cssClass="text small required" id="startTime" name="leave.startTime"
				cssStyle="width:80px;height:25px"
				onchange="javascript: changeEndTime();"></s:select>
		</div>
	</div>
	-->
	<!--<div class="grid_11">
		<div class="grid_4">
			<sj:datepicker id="date1" label="End Date" name="leave.endDate"
				cssClass="text small required" required="true"
				onCompleteTopics="displayDate" cssStyle="width:167px;height:15px;" />
		</div>
		<div class="grid_4">
			<s:select list="#{'1PM':'1PM','4PM':'4PM'}" label="End Time"
				cssClass="text small required" id="endTime" name="leave.endTime"
				cssStyle="width:80px;height:25px"
				onchange="javascript: changeEndTime();"></s:select>
		</div>
	</div>
	-->
	<div id="dispaySelectedDate" style="display: none; padding-left: 10px;"
		class="grid_11">
		<b>Total Leave Duration:</b>
		<s:property value="leave.days" />
		days
	</div>
	<div class="grid_11">
		<div class="grid_5" style="text-align: left;">
			<b><font style="color: red">*&nbsp;</font>Parent ApprovalsComment:</b>
		</div>
		<div class="grid_6">
			<sj:textarea rows="3" cols="20" name="leave.approvalsComment"
				cssStyle="width:130%" cssClass="text small  word_count required"
				required="true" requiredposition="left"
				></sj:textarea>
			<!--<div class="counter"></div>
		--></div>
	</div>
	<div class="grid_11">
		<div class="grid_6">
			<sj:submit   cssClass="submit small" value="Submit"
				indicator="indicator" targets="applyLeave"
				onClickTopics="formValidationForEditLeaves,displayDate" />

			<s:url id="doCancelLeave" action="ajaxDoCancelChildAbsentLeaveDetailsForParent"
				includeParams="all" namespace="/student"></s:url>
			<sj:a href="%{doCancelLeave}" cssClass="cancelButton"
				indicator="indicator" targets="applyLeave">Cancel</sj:a>
			<!--<tr class='loaded'>
				<td width="40%">
					<s:url id="removeLeave" action="ajaxDoCancelLeaveForParent"
						includeParams="all" escapeAmp="false">
						<s:param name="leave.id" value="leave.id"></s:param>
					</s:url>
					<s:div cssClass="cancelButton emsRemove" id='%{removeLeave}'
						theme="simple" title="Delete this Pending Leave">With Draw</s:div>
				</td>
			</tr>
		--></div>
	</div>
</s:form>

<script type="text/javascript">

changePageTitle('Edit Leaves');
$(document).ready(function() {
	var validator;
	$.subscribe('formValidationForEditLeaves', function(event, data) {
		if ($('#editLeaveReportForParent').valid())
			return true;
		else
			return false;
	});

});
</script>

