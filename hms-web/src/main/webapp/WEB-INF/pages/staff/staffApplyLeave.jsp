<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js"></script>

<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/common/textcounter.js"></script>
<s:form action="ajaxStaffApplyLeave" theme="css_xhtml"
	id="staffApplyLeave">
	<s:hidden name="viewStudentPersonAccountDetails.personFullName" />
	<div class="grid_11">
		<div class="grid_3" style="text-align: left;">
			<h2>
				Leave Application
			</h2>
		</div>
	</div>
	<div class="grid_11">
		<div class="grid_6" style="text-align: left;">
			<sj:textfield  label="Emp Id" labelposition="top"
				name="viewStudentPersonAccountDetails.username" readonly="true"
				cssClass="required text small" maxlength="40"></sj:textfield>
		</div>
	</div>
	<br />
	<div class="grid_11">
		<div class="grid_6" style="text-align: left;">
			<sj:textfield label="Emp Name" labelposition="top" readonly="true"
				name="viewStudentPersonAccountDetails.personFullName"
				cssClass="required text small" maxlength="40"></sj:textfield>
		</div>
	</div>
	<div class="grid_11">
		<div class="grid_6" style="text-align: left;">
			<b> Leave Type:</b>
			<s:select list="#{'CL':'Casual Leave','SL':'Sick Leave'}"
				name="leave.leaveType" cssStyle="width:150px;height:25px"></s:select>
		</div>
	</div>

	<div class="grid_11">

		<div class="grid_6" style="text-align: left;">
			<b>Duration:</b>
		</div>
	</div>
	<div class="grid_11">
		<div class="grid_4">
			<sj:datepicker id="date0" label="Start Date" name="leave.startDate" onCompleteTopics="displayDate"
				cssClass="text small required" required="true" readonly="true" 
				cssStyle="width:167px;height:15px;" />
		</div>
		<div class="grid_4">
			<s:select list="#{'9AM':'9AM','1PM':'1PM'}" label="Start Time" cssClass="text small required"
			id="startTime" name="leave.startTime" cssStyle="width:80px;height:25px" onchange="javascript: changeEndTime();"></s:select>
		</div>
	</div>

	<div class="grid_11">
		<div class="grid_4">
			<sj:datepicker id="date1" label="End Date" name="leave.endDate" readonly="true" 
				cssClass="text small required" required="true" onCompleteTopics="displayDate"
				cssStyle="width:167px;height:15px;" />
		</div>
			
		<div class="grid_4">
			<s:select list="#{'1PM':'1PM','4PM':'4PM'}" label="End Time"  cssClass="text small required"
				id="endTime" name="leave.endTime" cssStyle="width:80px;height:25px" onchange="javascript: changeEndTime();"></s:select>
		</div>
	</div>
	<div id="dispaySelectedDate" style="display: none;padding-left: 10px;" class="grid_11"></div>
			
	<div class="grid_11">
		<div class="grid_5" style="text-align: left;">
			<b><font style="color: red">*&nbsp;</font>Description:</b>
		</div>
		<div class="grid_6">
			<sj:textarea rows="3" cols="20" name="leave.description" cssStyle="width:130%"
				cssClass="text small  word_count required" required="true" requiredposition="left"
				></sj:textarea>
			<div class="counter"></div>
		</div>
	</div>
	<div class="grid_9">
		<div class="grid_7">
	<sj:submit   cssClass="submit small" value="Submit" indicator="indicator"
		targets="applyLeave" onClickTopics="formValidationForStaffApplyLeave,displayDate" />
	<s:url id="doCancelLeave" action="ajaxDoCancelLeave"
		includeParams="all"></s:url>
	<sj:a href="%{doCancelLeave}" cssClass="cancelButton"
		indicator="indicator" targets="applyLeave">Cancel</sj:a>
		</div>
		</div>
</s:form>

<script type="text/javascript">

	$.subscribe('displayDate', function(event, data) {
		var myDate = 0;
		var startDate = document.getElementById("date0").value;
		var endDate = document.getElementById("date1").value;
		//alert(endDate);
		var startTime = document.getElementById("startTime").value;
		var endTime = document.getElementById("endTime").value;
		//alert(startTime);
		var diff;
		if (Date.parse(endDate) < Date.parse(startDate)) {
			alert("Invalid Date Range!\nStart Date cannot be after End Date!")
			if ($('#adminApplyLeave').valid())
				return false;
		}
		else if (Date.parse(endDate) > Date.parse(startDate))
		{
			if(startDate == 'null' || endDate == 'null')
			{
				document.getElementById("dispaySelectedDate").innerHTML="<b>Total Leave Duration:</b>" + myDate + " &nbsp;days";
				$("#dispaySelectedDate").show();
			}
			else if(startDate != 'null' && endDate != 'null')
			{
				var days=Date.parse(endDate) - Date.parse(startDate);
				var seconds = days / 1000;
		
				var minutes = seconds / 60;
				var hours = minutes / 60;
				myDate = hours / 24;
			
				if(startTime == '9AM' && endTime=='1PM')
				{
					diff=0.5;
					//alert(diff);
				}
				else if(startTime == '9AM' && endTime=='4PM')
				{
					diff=1;
				}
				else if(startTime == '1PM' && endTime=='4PM')
				{
					diff=0.5;
				}
				else if(startTime == '1PM' && endTime=='1PM')
				{
					alert('please select correct endTime');
					return false;
				}
				myDate = myDate + diff;
				if(startDate != 'null' && endDate != 'null')
				{
					document.getElementById("dispaySelectedDate").innerHTML="<b>Total Leave Duration:</b>" + myDate + " &nbsp;days";
					$("#dispaySelectedDate").show();
				}
			}
		}
	});
	function changeEndTime()
	{
		var startDate = document.getElementById("date0").value;
		var endDate = document.getElementById("date1").value;
		var startTime = document.getElementById("startTime").value;
		var endTime = document.getElementById("endTime").value;
		//alert(startTime);
		var diff;
		var days=Date.parse(endDate) - Date.parse(startDate);
		var seconds = days / 1000;

		var minutes = seconds / 60;
		var hours = minutes / 60;
		var myDate = hours / 24;
		if(startTime == '9AM' && endTime=='1PM')
		{
			diff=0.5;
			alert(diff);
		}
		else if(startTime == '9AM' && endTime=='4PM')
		{
			diff=1;
		}
		else if(startTime == '1PM' && endTime=='4PM')
		{
			diff=0.5;
		}
		else if(startTime == '1PM' && endTime=='1PM')
		{
			alert("please select correct endTime");
		}
		myDate = myDate+diff;

		document.getElementById("dispaySelectedDate").innerHTML="<b>Total Leave Duration:</b>" + myDate +" &nbsp;days";
		$("#dispaySelectedDate").show();

		//alert(days);
		//alert(myDate);
		//alert(startDate);
		//alert(endDate);
		if (Date.parse(endDate) < Date.parse(startDate)) {
			alert("Invalid Date Range!\nStart Date cannot be after End Date!")
			return false;
		}
		else
		{
			
		}
	}

	changePageTitle('Apply Leaves');

	$(document).ready(

	function() {

		var validator;

		$.subscribe('formValidationForStaffApplyLeave', function(event, data) {

			if ($('#adminApplyLeave').valid())

				return true;

			else

				return false;

		});

	});

	function showTimings(date)

	{

		//$("#showTimings").show();
	}
</script>
