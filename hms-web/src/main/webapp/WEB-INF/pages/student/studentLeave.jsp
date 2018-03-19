<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/ems.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/common/textcounter.js"></script>
                
<s:form action="ajaxAddleaveReportForStudent" theme="css_xhtml" id="addLeaveReportForStudent">
	
	<s:hidden name="viewStudentPersonAccountDetails.personFullName"  />
    
	<div class="grid_11">
		<div class="grid_3" style="text-align: left;">
			<h2>
				Apply Leave
			</h2>
		</div>
	</div>
	<div class="grid_11">

		<div class="grid_6" style="text-align: left;">

			<sj:textfield readonly="true" label="Student Id" labelposition="top" name="viewStudentPersonAccountDetails.username"
				cssClass="required text small" maxlength="40"></sj:textfield>
			
			<!-- <s:property value="viewStudentPersonAccountDetails.studentId"/>-->
		
		</div>
	</div>
	<br />
	<div class="grid_11">
		<div class="grid_6" style="text-align: left;">
			<sj:textfield readonly="true" label="Student Name" labelposition="top" name="viewStudentPersonAccountDetails.personFullName"
				cssClass="required text small" maxlength="40"></sj:textfield>
		</div>
	</div>
	<div class="grid_11">
		<div class="grid_6" style="text-align: left;">
			<sj:textfield readonly="true" label="Date Applied" name="todayDate"
				labelposition="top" cssClass="required text small" maxlength="40"></sj:textfield>
		</div>
	</div>
	<div class="grid_11">
		&nbsp;
	</div>	
	<!--	
	<div class="grid_11">
		<div class="grid_3" style="text-align: left;">
			<b>Annual Leave Info:</b>
		</div>
	</div>
		<div class="grid_11">
	<div class="grid_3" style="text-align: left; width:35%">
	Total:<s:property value="viewStaffPersonAccountDetails.totalLeaves"/> &nbsp; 
			Taken:  <s:property value="anyId"/>   &nbsp; 
			Balance: <s:property value="balance"/>&nbsp; 
	</div>
	</div>
	     <div class="grid_7">
							&nbsp;
						</div>
	
	-->
	<div class="grid_11">
	<div class="grid_6" style="text-align: left;">
		<b>	Leave Type :</b>
		<s:select 
			list="#{'CL':'Causual Leave','SL':'Sick Leave'}"
			name="leave.leaveType" cssStyle="width:150px;height:25px"></s:select>
</div>
</div>
	
		   
	<div class="grid_11">
		<div class="grid_3" style="width: 100px;">
			<font style="margin-top: 30px;"><b
				style="vertical-align: sub;">Duration:</b>
			</font>
		</div>
		<div class="grid_4">
			<sj:datepicker id="date0" label="From" name="leave.startDate"
				cssClass="text small required" required="true"
				cssStyle="width:167px;height:20px;" readonly="true" />
		</div>
		<div class="grid_4">
			<sj:datepicker id="date1" label="To" name="leave.endDate"
				cssClass="text small required" required="true"
				cssStyle="width:167px;height:20px;" readonly="true" />
			&nbsp;&nbsp;&nbsp;&nbsp;
		</div>
	</div>

	           
	<!--<div class="grid_11">
		<div class="grid_3" style="text-align: left; width: 100px">
			<b><font style="color: red">*&nbsp;</font>Subject Line:</b>
		</div>
		<div class="grid_6">
			<sj:textfield size="40" name="leave.subject" maxlength="60" requiredposition="left"
				cssClass="text small required" required="true"></sj:textfield>
		</div>
	</div>

	--><div class="grid_11">
		<div class="grid_3" style="text-align: left; width: 100px">
			<b><font style="color: red">*&nbsp;</font>Comments:</b>
		</div>
		<div class="grid_6">
			<sj:textarea rows="3" cols="20" name="leave.description"
				cssClass="text small  word_count required" required="true" requiredposition="left"
				></sj:textarea>
			<div class="counter"></div>
		</div>
	</div>
	<sj:submit   cssClass="submit small" value="Submit" indicator="indicator"
		 targets="applyLeave" onClickTopics="formValidationForLeavesForStudent"/>
		
		<s:url id="doCancelLeave" action="ajaxDoCancelLeave" includeParams="all" ></s:url>
	<sj:a href="%{doCancelLeave}"  cssClass="cancelButton"
		indicator="indicator" targets="applyLeave">Cancel</sj:a>
</s:form>

<script type="text/javascript">
changePageTitle('Apply Leaves');
$(document).ready(
function() {
	var validator;
	$.subscribe('formValidationForLeavesForStudent', function(event, data) {
		  if ($('#addLeaveReportForStudent').valid())
             return true;
         else
             return false;
	});

});
</script>	
	
		