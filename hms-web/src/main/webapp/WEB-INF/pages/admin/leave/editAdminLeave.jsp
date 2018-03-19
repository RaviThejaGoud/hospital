<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/common/textcounter.js"></script>

<s:form action="ajaxEditAdminleaveReport" theme="css_xhtml"
	id="editLeaveReport">

	<s:hidden name="viewStaffPersonAccountDetails.personFullName" />
	<s:hidden name="selectedId" />
	<div class="grid_11">
		<div class="grid_3" style="text-align: left;">
			<h2>
				Leave Application
			</h2>
		</div>
	</div>
	<div class="grid_11">
		<div class="grid_6" style="text-align: left;">
			<b> Emp Id:</b>
			<s:property value="viewStaffPersonAccountDetails.username" />
		</div>
	</div>
	<br />
	<div class="grid_11">
		<!--<div class="grid_6" style="text-align: left;">
			<sj:textfield label="Emp Name" labelposition="top"
				name="viewStaffPersonAccountDetails.personFullName"
				cssClass="required text small" maxlength="40"></sj:textfield>
		</div>
	-->
		<div class="grid_6" style="text-align: left;">
			<b> Emp Name:</b>
			<s:property value="viewStaffPersonAccountDetails.personFullName" />
		</div>
	</div>
	<div class="grid_11">
		<div class="grid_6" style="text-align: left;">
			<sj:textfield readonly="true" label="Date Applied" name="todayDate"
				labelposition="top" cssClass="required text small" maxlength="40"></sj:textfield>
		</div>
	</div>
	<div class="grid_11">
		<div class="grid_6" style="text-align: left;">
			<b> Leave Type:</b>
			<s:select list="#{'CL':'Casual Leave','SL':'Sick Leave'}"
				name="leave.leaveType" cssStyle="width:150px;height:30px"></s:select>
		</div>
	</div>
	<div class="grid_11">

		<div class="grid_6" style="text-align: left;">
			<b>Duration:</b>
		</div>
	</div>
	<div class="grid_11">
		<div class="grid_4">
			<sj:datepicker id="date0" label="Start Date" name="leave.startDate" readonly="true" 
				onCompleteTopics="displayDate" cssClass="text small required"
				required="true" cssStyle="width:167px;height:15px;" />
		</div>
		<div class="grid_4">
			<s:select list="#{'9AM':'9AM','1PM':'1PM'}" label="Start Time"
				cssClass="text small required" id="startTime" name="leave.startTime"
				cssStyle="width:80px;height:30px"
				onchange="javascript: changeEndTime();"></s:select>
		</div>
	</div>

	<div class="grid_11">
		<div class="grid_4">
			<sj:datepicker id="date1" label="End Date" name="leave.endDate"
				cssClass="text small required" required="true" readonly="true" 
				onCompleteTopics="displayDate" cssStyle="width:167px;height:15px;" />
		</div>

		<div class="grid_4">
			<s:select list="#{'1PM':'1PM','4PM':'4PM'}" label="End Time"
				cssClass="text small required" id="endTime" name="leave.endTime"
				cssStyle="width:80px;height:30px"
				onchange="javascript: changeEndTime();"></s:select>
		</div>
	</div>
	<div id="dispaySelectedDate" style="display: none;padding-left: 10px;" class="grid_11">
		<b>Total Leave Duration:</b>
		<s:property value="leave.days" />
		days
	</div>
 
 <div class="grid_11">
		<div class="grid_7" style="text-align: left;">
			<b><font style="color: red">*&nbsp;</font>Description:</b>
		</div>
		<div class="grid_6">
			<sj:textarea rows="3" cols="20" name="leave.description" cssStyle="width:130%"
				cssClass="text small  word_count required" required="true" requiredposition="left"
				></sj:textarea>
			<div class="counter"></div>
		</div>
	</div>
	<!--<sj:textarea name="leave.description" id="leaveDescription"
		label="Comments" cssClass="word_count required" required="true"
		rows="5" cols="40"></sj:textarea>

	-->
	<div class="grid_11" style="float: right;">
	<div class="grid_4" style="float: right;">
	<sj:submit   cssClass="submit small" value="Submit" indicator="indicator"
		targets="applyLeave"
		onClickTopics="formValidationForEditLeaves,displayDate" />

	<s:url id="doCancelLeave" action="ajaxDoEditCancelLeave"
		includeParams="all"></s:url>
	<sj:a href="%{doCancelLeave}" cssClass="cancelButton"
		indicator="indicator" targets="applyLeave">Cancel</sj:a>
		</div>
		</div>
</s:form>

<script type="text/javascript">
	$
			.subscribe('displayDate', function(event, data) {
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
						if ($('#editLeaveReport').valid())
							return false;
					} else if (Date.parse(endDate) > Date.parse(startDate)) {
						if (startDate == 'null' || endDate == 'null') {
							document.getElementById("dispaySelectedDate").innerHTML = "<b>Total Leave Duration:</b>"
									+ myDate + " &nbsp;days";
							$("#dispaySelectedDate").show();
						} else if (startDate != 'null' && endDate != 'null') {
							var days = Date.parse(endDate)
									- Date.parse(startDate);
							var seconds = days / 1000;

							var minutes = seconds / 60;
							var hours = minutes / 60;
							myDate = hours / 24;

							if (startTime == '9AM' && endTime == '1PM') {
								diff = 0.5;
								//alert(diff);
							} else if (startTime == '9AM' && endTime == '4PM') {
								diff = 1;
							} else if (startTime == '1PM' && endTime == '4PM') {
								diff = 0.5;
							} else if (startTime == '1PM' && endTime == '1PM') {
								alert('please select correct endTime');
								return false;
							}
							myDate = myDate + diff;
							if (startDate != 'null' && endDate != 'null') {
								document.getElementById("dispaySelectedDate").innerHTML = "<b>Total Leave Duration:</b>"
										+ myDate + " &nbsp;days";
								$("#dispaySelectedDate").show();
							}
						}
					}
				});
	function changeEndTime() {
		var startDate = document.getElementById("date0").value;
		var endDate = document.getElementById("date1").value;
		var startTime = document.getElementById("startTime").value;
		var endTime = document.getElementById("endTime").value;
		//alert(startTime);
		var diff;
		var days = Date.parse(endDate) - Date.parse(startDate);
		var seconds = days / 1000;

		var minutes = seconds / 60;
		var hours = minutes / 60;
		var myDate = hours / 24;
		if (startTime == '9AM' && endTime == '1PM') {
			diff = 0.5;
			//alert(diff);
		} else if (startTime == '9AM' && endTime == '4PM') {
			diff = 1;
		} else if (startTime == '1PM' && endTime == '4PM') {
			diff = 0.5;
		} else if (startTime == '1PM' && endTime == '1PM') {
			alert("please select correct endTime");
		}
		myDate = myDate + diff;

		document.getElementById("dispaySelectedDate").innerHTML = "<b>Total Leave Duration:</b>"
				+ myDate;
		$("#dispaySelectedDate").show();

		//alert(days);
		//alert(myDate);
		//alert(startDate);
		//alert(endDate);
		if (Date.parse(endDate) < Date.parse(startDate)) {
			alert("Invalid Date Range!\nStart Date cannot be after End Date!")
			return false;
		} else {

		}
	}

	changePageTitle('Edit Leaves');
	$(document).ready(function() {
		var validator;
		$.subscribe('formValidationForEditLeaves', function(event, data) {
			if ($('#editLeaveReport').valid())
				return true;
			else
				return false;
		});

	});
</script>

