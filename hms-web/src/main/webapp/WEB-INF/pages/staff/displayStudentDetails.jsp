<%@ include file="/common/taglibs.jsp"%>


<td colspan="6" style="background-color: #CCC;" >
		<s:form action="ajaxUpdateLeave" theme="css_xhtml" id="editLeaveReport">

		<s:hidden name="classId" value="%{classId}"/>
		<div class="grid_11">
		<div class="grid_3" style="text-align: left;">
			<h2>
				Apply Leave
			</h2>
		</div>
	</div>
	<div class="grid_11">

		<div class="grid_6" style="text-align: left;">
		
		
		<label>Emp Id:</label><s:property value="viewStudentLeaveDetails.staffId"/><br/>
		<label>Emp Name:</label><s:property value="viewStudentLeaveDetails.personFullName"/><br/>
		<label>Start Date:</label><s:property value="viewStudentLeaveDetails.startDateStr" /><br/>
		<label>End Date:</label><s:property value="viewStudentLeaveDetails.endDateStr"/><br/>
		<label>Total Days:</label><s:property value="viewStudentLeaveDetails.days"/> &nbsp;days<br/>
		<label>Comments:</label><s:property value="viewStudentLeaveDetails.description"/>
		</div>
	</div>
	<div class="grid_11">
	<div class="grid_4" style="text-align: left;">
		<input type="radio" value="A" 
			name="leave.leaveStatus" class="radio" checked="checked">
		Accept
		<input type="radio" value="R"
			name="leave.leaveStatus" class="radio">
		Reject
	</div>
	</div>
	
	<br />
	<sj:submit   cssClass="submit small" value="Submit" indicator="indicator" onClickTopics="updateLeave"
		 targets="applyLeave"/>
		
		<s:url id="doCancelLeave" action="ajaxDoCancelLeave" includeParams="all" ></s:url>
	<sj:a href="%{doCancelLeave}"  cssClass="cancelButton"
		indicator="indicator" targets="applyLeave">Cancel</sj:a>
</s:form>


	</td>

<script language="JavaScript" type="text/javascript">
$(document).ready(function() {
		$.subscribe('updateLeave', function(event, data) {
			if ($('#editLeaveReport').valid())
				return true;
			else
				return false;
		});
	});
</script>
	
		