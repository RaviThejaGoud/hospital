<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/common/textcounter.js">
</script>
<jsp:include page="/common/messages.jsp"></jsp:include>
<s:form action="ajaxDoPostMaterial" theme="css_xhtml"
	id="DoPostMaterial">
	<fieldset>
		<s:hidden name="viewStaffPersonAccountDetails.personFullName" />
		<s:hidden name="selectedId" />

		<div class="grid_10" style="padding-bottom: 5px;">
			<div class="grid_6">
				<sj:textfield name="person.firstName" id="staffName" required="true"
					label="Material Name" cssClass="alphabet required textfield"
					maxlength="20"></sj:textfield>
			</div>
		</div>
		<br />
		<div class="grid_8">
			<div class="tableactions" style="padding-bottom: 0px;">
				<s:select list="classList" listKey="id" listValue="className"
					label="Select Class" cssClass="required" required="true"
					name="className" headerKey="" headerValue="- Select -"
					requiredposition="first"
					onchange="javascript:onClassChange(this.value);">

				</s:select>
			</div>
		</div>
		<div class="grid_9">
			<div class="grid_5" style="text-align: left;">
				<b><font style="color: red">*&nbsp;</font>Description:</b>
			</div>
			<div class="grid_6">
				<sj:textarea rows="3" cols="20" name="leave.description"
					cssStyle="width:88%" cssClass="text small  word_count required"
					required="true" requiredposition="left"
					></sj:textarea>
				<div class="counter"></div>
			</div>
		</div>
		<div class="grid_6">
			<sj:textfield name="person.firstName" id="staffName" required="true"
				label="Upload" cssClass="alphabet required textfield" maxlength="20"></sj:textfield>
		</div>


		<div class="grid_4" style="float: right;">
			<sj:submit   cssClass="submit small" value="Submit"
				indicator="indicator" targets="applyLeave"
				onClickTopics="formValidationForEditLeaves,displayDate" />

			<s:url id="doCancelLeave" action="ajaxDoCancelLeave"
				includeParams="all" namespace="/staff"></s:url>
			<sj:a href="%{doCancelLeave}" cssClass="cancelButton"
				indicator="indicator" targets="applyLeave">Cancel</sj:a>
		</div>
	</fieldset>
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
							diff = 0;
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
		diff = 0;
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

$.subscribe('removeLeaves', function(event, data) {
	if ($('#' + data.id).is(":hidden")) {
		$('#' + data.id).show();
	} else {
		$('#' + data.id).hide();
	}
});
</script>

