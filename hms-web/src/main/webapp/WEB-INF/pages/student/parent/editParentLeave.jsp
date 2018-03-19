<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/common/textcounter.js">
</script>

<s:form action="ajaxEditleaveReportForParent" theme="css_xhtml" id="editLeaveReportForParent">
	<s:hidden name="viewStudentPersonAccountDetails.personFullName" />
	<s:hidden name="selectedId" />
	<div class="grid_11">
		<div class="grid_10" style="text-align: left;">
			<h2>
				Edit Leave Application
			</h2>
		</div>
	</div>
	<div class="grid_12" style="padding-bottom: 5px;">
		<div class="grid_6" style="text-align: left;">
			<b>Student Id&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:</b>
			<s:property value="viewStudentPersonAccountDetails.username" />
		</div>
	</div>
	<div class="grid_12" style="padding-bottom: 5px;">

		<div class="grid_6" style="text-align: left;">
			<b>Student Name:</b>
			<s:property value="viewStudentPersonAccountDetails.personFullName" />
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
	--><div class="grid_12">
		<div class="grid_6" style="text-align: left;">
			<b> Leave Type:</b>
			<s:select list="#{'CL':'Causual Leave','SL':'Sick Leave'}" headerKey="" headerValue="- Select -"
				name="leave.leaveType" cssStyle="width:150px;height:30px"></s:select>
		</div>
	</div>
	<div class="grid_12">&nbsp;</div>
	<div class="grid_12" style="padding-bottom: 5px;">
		<div class="grid_6" style="text-align: left;">
			<b>Duration:</b>
		</div>
	</div>
	<div class="grid_11">
		<div class="grid_2"  style="text-align: left;"><b>Start Date</b></div>
		<div class="grid_3">
			<sj:datepicker id="date0"  name="leave.startDate" onCompleteTopics="displayDate" cssClass="textfield required" readonly="true" 
				required="true" cssStyle="width:100px;height:15px;" />
		</div>
		<div class="grid_2"  style="text-align: left;"><b>Start Time</b></div>
		<div class="grid_3">
			<s:select list="#{'9AM':'9AM','1PM':'1PM'}" cssClass="textfield required" id="startTime" name="leave.startTime"
				cssStyle="width:150px;height:30px" onchange="javascript: changeEndTime();"></s:select>
		</div>
	</div>
	<div class="grid_11">&nbsp;</div>
	<div class="grid_11">
		<div class="grid_2"  style="text-align: left;"><b>End Date</b></div>
		<div class="grid_3">
			<sj:datepicker id="date1" name="leave.endDate" readonly="true" 
				cssClass="text small required" required="true"
				onCompleteTopics="displayDate" cssStyle="width:100px;height:15px;" />
		</div>
		<div class="grid_2"  style="text-align: left;"><b>End Time</b></div>
		<div class="grid_3">
			<s:select list="#{'1PM':'1PM','4PM':'4PM'}" cssClass="text small required" id="endTime" name="leave.endTime"
				cssStyle="width:150px;height:30px" onchange="javascript: changeEndTime();"></s:select>
		</div>
	</div>
	<div id="dispaySelectedDate" style="display: none; padding-left: 10px;"
		class="grid_11">
		<b>Total Leave Duration:</b>
		<s:property value="leave.days" />
		days
	</div>
	<div class="grid_12">&nbsp;</div>
	<div class="grid_12">
		<div class="grid_2" style="text-align: left;">
			<b><font style="color: red">*&nbsp;</font>Description:</b>
		</div>
		<div class="grid_6">
			<sj:textarea rows="3" cols="20" name="leave.description"
				cssStyle="width:130%" cssClass="text small  word_count required"
				required="true" requiredposition="left"
				></sj:textarea>
			<div class="counter"></div>
		</div>
	</div>
	<div class="grid_11">
		<div class="grid_6">
			<sj:submit   cssClass="submit small" value="Submit"
				indicator="indicator" targets="applyLeave"
				onClickTopics="formValidationForEditLeaves,displayDate" />

			<s:url id="doCancelLeave" action="ajaxDoCancelLeaveForParent"
				includeParams="all"></s:url>
			<sj:a href="%{doCancelLeave}" cssClass="cancelButton"
				indicator="indicator" targets="applyLeave">Cancel</sj:a>
			<tr class='loaded'>
				<td width="40%">
					<s:url id="removeLeave" action="ajaxDoCancelLeaveForParent"
						includeParams="all" escapeAmp="false">
						<s:param name="leaveId" value="leave.id"></s:param>
					</s:url>
					<s:div cssClass="cancelButton emsRemove" id='%{removeLeave}'
						theme="simple" title="Delete this Pending Leave">With Draw</s:div>
				</td>
			</tr>
		</div>
	</div>
</s:form>

<script type="text/javascript">
$(document).ready(function() {

	if ($('div.emsRemove')) {
		$('div.emsRemove').unbind('click');
		$("div.emsRemove").click(function() {
			confirmDialog(this);
		});
	}

});
function confirmDialog(event) {
	thishref = $(event).attr('id');
	if ($(event).next('.question').length <= 0) {
		$(event)
				.after(
						'<div class="question">Are You Sure?<br/> <span class="yes">Yes</span><span class="cancel">Cancel</span></div>');
	}
	$(event).next('.question').animate( {
		opacity : 1
	}, 300);
	$('.yes').unbind('click');
	$('.yes').bind('click', function() {
		var prdDiv = $(this).parents('.question');
		prdDiv.html('Processing...');
		$.ajax( {
			url : thishref,
			cache : false,
			success : function(html) {
				$("#applyLeave").html(html);
			}
		});
	});
	$('.cancel').unbind('click');
	$('.cancel').bind('click', function() {
		$(this).parents('.question').fadeOut(300, function() {
			$(this).remove();
		});
		return false;
	});
}
</script>

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
		if ($('#editLeaveReportForParent').valid())
			return true;
		else
			return false;
	});

});
</script>

