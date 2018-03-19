<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/ems.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/common/textcounter.js"></script>
                
<s:form action="ajaxEditleaveReport" theme="css_xhtml" id="editLeaveReport">
	
	<s:hidden name="viewStudentPersonAccountDetails.personFullName"  />
	<s:hidden name="selectedId" />    
	<div class="grid_11">
		<div class="grid_3" style="text-align: left; width: 100px">
			<b>Student Id:</b>
		</div>
		<div class="grid_6" style="text-align: left;">
			<s:property value="viewStudentPersonAccountDetails.username"/>
		</div>
	</div>
	<div class="grid_11">
		&nbsp;
	</div>
	<div class="grid_11">
		<div class="grid_3" style="text-align: left; width: 100px">
			<b>Student Name:</b>
		</div>
		<div class="grid_6" style="text-align: left;">
			<s:property value="viewStudentPersonAccountDetails.personFullName"/>
		</div>
	</div>
	<!--<div class="grid_11">
		&nbsp;
	</div>		
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
	</div>-->
	
	     <div class="grid_7">
							&nbsp;
						</div>
	
	<div class="grid_11">
		<div class="grid_3" style="text-align: left; width: 100px">
			<b>Date Applied:</b>
		</div>
		<div class="grid_6">
			<s:property value="todayDate"/>
		</div>
	</div>
	
	<div class="grid_11" style="height:35px;">
    		<div class="grid_3" style="width: 100px;">
			<label>Leave Type :</label>
		</div>
		<div class="grid_6">
	 		<s:select list="#{'CL':'Causual Leave','SL':'Sick Leave'}" name="leave.leaveType" cssStyle="width:150px;height:25px"></s:select>
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

	-->
	<div class="grid_11">
		<div class="grid_3" style="text-align: left; width: 100px">
			<b><font style="color: red">*&nbsp;</font>Description:</b>
		</div>
		<div class="grid_6">
			<sj:textarea rows="3" cols="20" name="leave.description"
				cssClass="text small  word_count required" required="true" requiredposition="left"
				></sj:textarea>
			<div class="counter"></div>
		</div>
	</div>
	<sj:submit   cssClass="submit small" value="Submit" indicator="indicator"
		 targets="applyLeave" onClickTopics="formValidationForEditLeaves"/>
		
		<s:url id="doCancelLeave" action="ajaxDoCancelLeave" includeParams="all" ></s:url>
	<sj:a href="%{doCancelLeave}"  cssClass="cancelButton"
		indicator="indicator" targets="applyLeave">Cancel</sj:a>
</s:form>

<script type="text/javascript">
changePageTitle('Edit Leaves');
$(document).ready(
function() {
	var validator;
	$.subscribe('formValidationForEditLeaves', function(event, data) {
		  if ($('#editLeaveReport').valid())
             return true;
         else
             return false;
	});

});
</script>	
	
		