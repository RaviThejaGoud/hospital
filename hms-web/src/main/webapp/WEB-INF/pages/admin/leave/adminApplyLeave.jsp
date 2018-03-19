<%@ include file="/common/taglibs.jsp"%>
<div id="steps">
	<div id="tabWrapper13">
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>

		<script type="text/javascript"
			src="${pageContext.request.contextPath}/scripts/common/textcounter.js">
</script>
		<s:form action="ajaxAdminApplyLeave" theme="css_xhtml"
			id="adminApplyLeave">
			<fieldset>
				<s:hidden name="viewStaffPersonAccountDetails.personFullName" />
				<s:hidden name="viewStaffPersonAccountDetails.roleName" />
				<div class="grid_12">
					<div >
						<h2>
							Leave Application
						</h2>
					</div>
				</div>
				<div class="grid_12" >
					<div class="grid_6" >
						<label>
							Emp Id:
						</label>
						<s:property value="viewStaffPersonAccountDetails.username" />
						<label>
							Emp Name:
						</label>
						<s:property value="viewStaffPersonAccountDetails.personFullName" />
					</div>
					<div class="grid_6" >
						<label>
							Leave Type:
						</label>
						<s:select list="#{'CL':'Casual Leave','SL':'Sick Leave'}"
							cssClass="required" required="true" headerKey=""
							headerValue="- Select -" name="leave.leaveType"></s:select>

					</div>
				</div>
				<div class="grid_9">
					<div class="grid_6">
						<label>
							Duration:
						</label>
					</div>
				</div>

				<div class="grid_6">
					<div class="grid_4">
						<sj:datepicker id="date0" label="Start Date"
							name="leave.startDate" readonly="true"
							onCompleteTopics="displayDate" cssClass="text small required"
							required="true" minDate="0"  />
					</div>
					<div class="grid_2">
						<s:select list="#{'9AM':'9AM','1PM':'1PM'}" label="Start Time"
							cssClass="text small required" id="startTime"
							name="leave.startTime"
							onchange="javascript: changeEndTime();"></s:select>
					</div>
				</div>
				<div class="grid_6">
					<div class="grid_4">
						<sj:datepicker id="date1" label="End Date" name="leave.endDate"
							minDate="0" readonly="true" cssClass="text small required"
							required="true" onCompleteTopics="displayDate"
							 />
					</div>

					<div class="grid_2">
						<s:select list="#{'1PM':'1PM','4PM':'4PM'}" label="End Time"
							cssClass="text small required" id="endTime" name="leave.endTime"
							onchange="javascript: changeEndTime();"></s:select>
					</div>
				</div>
				<div id="dispaySelectedDate" class="grid_10"></div>


				<div class="grid_10">

					<div class="grid_6">
						<sj:textarea rows="3" cols="20" name="leave.description"
							label="Description" cssClass="text small  word_count required"
							required="true" requiredposition="left"></sj:textarea>
						<div class="counter"></div>
					</div>
				</div>


				<div class="grid_4" >
					<s:url id="doCancelLeaves" action="ajaxDoGetLeaveDetailsLeft"
						includeParams="all" namespace="/admin"></s:url>
					<sj:a href="%{doCancelLeaves}" cssClass="cancelButton"
						indicator="indicator" targets="staffContent" button="false">Cancel</sj:a>
					<sj:submit   cssClass="submit small" value="Submit"
						indicator="indicator" targets="applyLeave" validate="true" />

				</div>
			</fieldset>
		</s:form>
	</div>
</div>
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
					if ($('#adminApplyLeave').valid())
						return false;
				} else if (Date.parse(endDate) > Date.parse(startDate)) {
					if (startDate == 'null' || endDate == 'null') {
						document.getElementById("dispaySelectedDate").innerHTML = "<b>Total Leave Duration:</b>"
								+ myDate + " &nbsp;days";
						$("#dispaySelectedDate").show();
					} else if (startDate != 'null' && endDate != 'null') {
						var days = Date.parse(endDate) - Date.parse(startDate);
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
		alert(diff);
	} else if (startTime == '9AM' && endTime == '4PM') {
		diff = 1;
	} else if (startTime == '1PM' && endTime == '4PM') {
		diff = 0.5;
	} else if (startTime == '1PM' && endTime == '1PM') {
		alert("please select correct endTime");
	}
	myDate = myDate + diff;

	document.getElementById("dispaySelectedDate").innerHTML = "<b>Total Leave Duration:</b>"
			+ myDate + " &nbsp;days";
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

changePageTitle('Admin Apply Leaves');

$(document).ready(

function() {

	var validator;
});

function showTimings(date)

{

	//$("#showTimings").show();
}
</script>
