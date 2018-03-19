<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/subscription/textcounter.js">
</script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>
<div class="grid_14">
	<s:form action="ajaxDogetVisitorDetails" theme="css_xhtml" method="post" namespace="/hostel">
		<s:hidden name="student.account.id" value="%{selectedId}"></s:hidden>
		<s:hidden name="tempString" value="Visitors"></s:hidden>
		<h1>
			Visitor Details
		</h1>
		<fieldset>
			<div class="grid_13">
				<div class="grid_6 left">
					<div class="grid_4">
						<sj:textfield name="studentOut.visitorName" id="visitorName" required="true"
							label="Visitor Name" cssClass="textfield required" tabindex="1"
							maxlength="50"></sj:textfield>
							<span class="hintMessage">(Type "self" in the above text field if the student is taking permission without visitor.)</span>
					</div>
					<div class="grid_6">
						<sj:datepicker id="visitorInDate" readonly="true" name="visitors.inDate"
							label="In Date" changeMonth="true" tabindex="3" minDate="0" maxDate="0"
							cssClass="textfield required" maxlength="20" required="true" onCompleteTopics="visitorDisplayDate"/>
					</div>
					<div class="grid_6">
						<s:select name="visitors.inTime" label="In Time" id="inTime"
							cssClass="textfield required" required="true" tabindex="5"
							onchange="checkOutVisitorTimeInTImeValidation()" headerKey=""
							headerValue="-Select-"
							list="#{'05:00 AM':'05:00 AM','05:15 AM':'05:15 AM','05:30 AM':'05:30 AM','05:45 AM':'05:45 AM', '06:00 AM':'06:00 AM', '06:15 AM':'06:15 AM','06:30 AM':'06:30 AM','06:45 AM':'06:45 AM','07:00 AM':'07:00 AM','07:15 AM':'07:15 AM','07:30 AM':'07:30 AM','07:45 AM':'07:45 AM','08:00 AM':'08:00 AM','08:15 AM':'08:15 AM','08:30 AM':'08:30 AM','08:45 AM':'08:45 AM','09:00 AM':'09:00 AM','09:15 AM':'09:15 AM','09:30 AM':'09:30 AM','09:45 AM':'09:45 AM','10:00 AM':'10:00 AM','10:15 AM':'10:15 AM','10:30 AM':'10:30 AM','10:45 AM':'10:45 AM','11:00 AM':'11:00 AM','11:15 AM':'11:15 AM','11:30 AM':'11:30 AM','11:45 AM':'11:45 AM','12:00 AM':'12:00 AM','12:15 AM':'12:15 AM','12:30 AM':'12:30 AM','12:45 AM':'12:45 AM','01:00 PM':'01:00 PM','01:15 PM':'01:15 PM','01:30 PM':'01:30 PM','01:45 PM':'01:45 PM','02:00 PM':'02:00 PM','02:15 PM':'02:15 PM','02:30 PM':'02:30 PM','02:45 PM':'02:45 PM','03:00 PM':'03:00 PM','03:15 PM':'03:15 PM','03:30 PM':'03:30 PM','03:45 PM':'03:45 PM','04:00 PM':'04:00 PM','04:15 PM':'04:15 PM','04:30 PM':'04:30 PM','04:45 PM':'04:45 PM','05:00 PM':'05:00 PM','05:15 PM':'05:15 PM','05:30 PM':'05:30 PM','05:45 PM':'05:45 PM','06:00 PM':'06:00 PM','06:15 PM':'06:15 PM','06:30 PM':'06:30 PM','06:45 PM':'06:45 PM','07:00 PM':'07:00 PM','07:15 PM':'07:15 PM','07:30 PM':'07:30 PM','07:45 PM':'07:45 PM','08:00 PM':'08:00 PM','08:15 PM':'08:15 PM','08:30 PM':'08:30 PM','08:45 PM':'08:45 PM','09:00 PM':'09:00 PM','09:15 PM':'09:15 PM','09:30 PM':'09:30 PM','09:45 PM':'09:45 PM','10:00 PM':'10:00 PM','10:15 PM':'10:15 PM','10:30 PM':'10:30 PM','10:45 PM':'10:45 PM','11:00 PM':'11:00 PM','11:15 PM':'11:15 PM','11:30 PM':'11:30 PM','11:45 PM':'11:45 PM'}"></s:select>
					</div>
				</div>
				<div class="grid_6">
					<div class="grid_6">
						<sj:textfield name="visitors.relation" id="relation"
							label="Visitor Relation" cssClass="textfield  " tabindex="2"
							maxlength="50"></sj:textfield>
					</div>
					<div class="grid_6">
						<sj:datepicker id="visitorOutDate" readonly="true" required="true"
							name="visitors.outDate" label="Out Date" changeMonth="true"
							tabindex="4" cssClass="textfield required" maxlength="20"
							changeYear="true" yearRange="1960"  minDate="0" maxDate="0" onCompleteTopics="visitorDisplayDate"/>
					</div>
					<div class="grid_6">
						<s:select name="visitors.outTime" label="Out Time" id="outTime"
							cssClass="textfield required" required="true" tabindex="6"
							onchange="checkOutVisitorTimeInTImeValidation()" headerKey=""
							headerValue="-Select-"
							list="#{'05:00 AM':'05:00 AM','05:15 AM':'05:15 AM','05:30 AM':'05:30 AM','05:45 AM':'05:45 AM', '06:00 AM':'06:00 AM', '06:15 AM':'06:15 AM','06:30 AM':'06:30 AM','06:45 AM':'06:45 AM','07:00 AM':'07:00 AM','07:15 AM':'07:15 AM','07:30 AM':'07:30 AM','07:45 AM':'07:45 AM','08:00 AM':'08:00 AM','08:15 AM':'08:15 AM','08:30 AM':'08:30 AM','08:45 AM':'08:45 AM','09:00 AM':'09:00 AM','09:15 AM':'09:15 AM','09:30 AM':'09:30 AM','09:45 AM':'09:45 AM','10:00 AM':'10:00 AM','10:15 AM':'10:15 AM','10:30 AM':'10:30 AM','10:45 AM':'10:45 AM','11:00 AM':'11:00 AM','11:15 AM':'11:15 AM','11:30 AM':'11:30 AM','11:45 AM':'11:45 AM','12:00 AM':'12:00 AM','12:15 AM':'12:15 AM','12:30 AM':'12:30 AM','12:45 AM':'12:45 AM','01:00 PM':'01:00 PM','01:15 PM':'01:15 PM','01:30 PM':'01:30 PM','01:45 PM':'01:45 PM','02:00 PM':'02:00 PM','02:15 PM':'02:15 PM','02:30 PM':'02:30 PM','02:45 PM':'02:45 PM','03:00 PM':'03:00 PM','03:15 PM':'03:15 PM','03:30 PM':'03:30 PM','03:45 PM':'03:45 PM','04:00 PM':'04:00 PM','04:15 PM':'04:15 PM','04:30 PM':'04:30 PM','04:45 PM':'04:45 PM','05:00 PM':'05:00 PM','05:15 PM':'05:15 PM','05:30 PM':'05:30 PM','05:45 PM':'05:45 PM','06:00 PM':'06:00 PM','06:15 PM':'06:15 PM','06:30 PM':'06:30 PM','06:45 PM':'06:45 PM','07:00 PM':'07:00 PM','07:15 PM':'07:15 PM','07:30 PM':'07:30 PM','07:45 PM':'07:45 PM','08:00 PM':'08:00 PM','08:15 PM':'08:15 PM','08:30 PM':'08:30 PM','08:45 PM':'08:45 PM','09:00 PM':'09:00 PM','09:15 PM':'09:15 PM','09:30 PM':'09:30 PM','09:45 PM':'09:45 PM','10:00 PM':'10:00 PM','10:15 PM':'10:15 PM','10:30 PM':'10:30 PM','10:45 PM':'10:45 PM','11:00 PM':'11:00 PM','11:15 PM':'11:15 PM','11:30 PM':'11:30 PM','11:45 PM':'11:45 PM'}"></s:select>
					</div>
				</div>
				<div class="grid_13" style="display: block;">
					<div class="grid_6">
						<input type="checkbox" value="studentOutWithVisitor"
							id="outWithVisitor"
							name="studentOutWithVisitor" class="radio">
						Student Out With Visitor
					</div>
				</div>
				<div class="grid_13" id="outStuDetails">
				<div class="grid_13">&nbsp;</div>
				<div class="grid_13">
						<h1>
					      Out Student Details:
				      </h1>
				</div>
					<div class="grid_6 left">
						<div class="grid_6">
							<sj:datepicker id="studentOutDate" readonly="true"
								name="studentOut.outDate" label="Out Date" changeMonth="true"
								tabindex="3" cssClass="textfield" maxlength="20" minDate="0" required="true"
								 onchange="ajaxFeeDeafaulters('eventEndTime');" onCompleteTopics="displayDate"/>
						</div>
						<div class="grid_6">
							<s:select name="studentOut.outTime" label="Out Time" id="studentOutTime"
								cssClass="textfield "  tabindex="5" required="true"
								onchange="checkOutTimeInTImeValidation()" headerKey=""
								headerValue="-Select-"
								list="#{'05:00 AM':'05:00 AM','05:15 AM':'05:15 AM','05:30 AM':'05:30 AM','05:45 AM':'05:45 AM', '06:00 AM':'06:00 AM', '06:15 AM':'06:15 AM','06:30 AM':'06:30 AM','06:45 AM':'06:45 AM','07:00 AM':'07:00 AM','07:15 AM':'07:15 AM','07:30 AM':'07:30 AM','07:45 AM':'07:45 AM','08:00 AM':'08:00 AM','08:15 AM':'08:15 AM','08:30 AM':'08:30 AM','08:45 AM':'08:45 AM','09:00 AM':'09:00 AM','09:15 AM':'09:15 AM','09:30 AM':'09:30 AM','09:45 AM':'09:45 AM','10:00 AM':'10:00 AM','10:15 AM':'10:15 AM','10:30 AM':'10:30 AM','10:45 AM':'10:45 AM','11:00 AM':'11:00 AM','11:15 AM':'11:15 AM','11:30 AM':'11:30 AM','11:45 AM':'11:45 AM','12:00 AM':'12:00 AM','12:15 AM':'12:15 AM','12:30 AM':'12:30 AM','12:45 AM':'12:45 AM','01:00 PM':'01:00 PM','01:15 PM':'01:15 PM','01:30 PM':'01:30 PM','01:45 PM':'01:45 PM','02:00 PM':'02:00 PM','02:15 PM':'02:15 PM','02:30 PM':'02:30 PM','02:45 PM':'02:45 PM','03:00 PM':'03:00 PM','03:15 PM':'03:15 PM','03:30 PM':'03:30 PM','03:45 PM':'03:45 PM','04:00 PM':'04:00 PM','04:15 PM':'04:15 PM','04:30 PM':'04:30 PM','04:45 PM':'04:45 PM','05:00 PM':'05:00 PM','05:15 PM':'05:15 PM','05:30 PM':'05:30 PM','05:45 PM':'05:45 PM','06:00 PM':'06:00 PM','06:15 PM':'06:15 PM','06:30 PM':'06:30 PM','06:45 PM':'06:45 PM','07:00 PM':'07:00 PM','07:15 PM':'07:15 PM','07:30 PM':'07:30 PM','07:45 PM':'07:45 PM','08:00 PM':'08:00 PM','08:15 PM':'08:15 PM','08:30 PM':'08:30 PM','08:45 PM':'08:45 PM','09:00 PM':'09:00 PM','09:15 PM':'09:15 PM','09:30 PM':'09:30 PM','09:45 PM':'09:45 PM','10:00 PM':'10:00 PM','10:15 PM':'10:15 PM','10:30 PM':'10:30 PM','10:45 PM':'10:45 PM','11:00 PM':'11:00 PM','11:15 PM':'11:15 PM','11:30 PM':'11:30 PM','11:45 PM':'11:45 PM'}"></s:select>
						</div>
					</div>
					<div class="grid_6">
						<div class="grid_6">
							<sj:datepicker id="expectedInDate" readonly="true"
								name="studentOut.expectedInDate" label="In Date" changeMonth="true"
								tabindex="3" cssClass="textfield " maxlength="20" minDate="0" required="true"
								 onchange="ajaxStudentOutDates();"  onCompleteTopics="displayDate"/>
						</div>
						<div class="grid_6">
							<s:select name="studentOut.exceptedInTime" label="In Time" id="exceptedInTime"
								cssClass="textfield "  tabindex="6" required="true"
								onchange="checkOutTimeInTImeValidation()" headerKey=""
								headerValue="-Select-"
								list="#{'05:00 AM':'05:00 AM','05:15 AM':'05:15 AM','05:30 AM':'05:30 AM','05:45 AM':'05:45 AM', '06:00 AM':'06:00 AM', '06:15 AM':'06:15 AM','06:30 AM':'06:30 AM','06:45 AM':'06:45 AM','07:00 AM':'07:00 AM','07:15 AM':'07:15 AM','07:30 AM':'07:30 AM','07:45 AM':'07:45 AM','08:00 AM':'08:00 AM','08:15 AM':'08:15 AM','08:30 AM':'08:30 AM','08:45 AM':'08:45 AM','09:00 AM':'09:00 AM','09:15 AM':'09:15 AM','09:30 AM':'09:30 AM','09:45 AM':'09:45 AM','10:00 AM':'10:00 AM','10:15 AM':'10:15 AM','10:30 AM':'10:30 AM','10:45 AM':'10:45 AM','11:00 AM':'11:00 AM','11:15 AM':'11:15 AM','11:30 AM':'11:30 AM','11:45 AM':'11:45 AM','12:00 AM':'12:00 AM','12:15 AM':'12:15 AM','12:30 AM':'12:30 AM','12:45 AM':'12:45 AM','01:00 PM':'01:00 PM','01:15 PM':'01:15 PM','01:30 PM':'01:30 PM','01:45 PM':'01:45 PM','02:00 PM':'02:00 PM','02:15 PM':'02:15 PM','02:30 PM':'02:30 PM','02:45 PM':'02:45 PM','03:00 PM':'03:00 PM','03:15 PM':'03:15 PM','03:30 PM':'03:30 PM','03:45 PM':'03:45 PM','04:00 PM':'04:00 PM','04:15 PM':'04:15 PM','04:30 PM':'04:30 PM','04:45 PM':'04:45 PM','05:00 PM':'05:00 PM','05:15 PM':'05:15 PM','05:30 PM':'05:30 PM','05:45 PM':'05:45 PM','06:00 PM':'06:00 PM','06:15 PM':'06:15 PM','06:30 PM':'06:30 PM','06:45 PM':'06:45 PM','07:00 PM':'07:00 PM','07:15 PM':'07:15 PM','07:30 PM':'07:30 PM','07:45 PM':'07:45 PM','08:00 PM':'08:00 PM','08:15 PM':'08:15 PM','08:30 PM':'08:30 PM','08:45 PM':'08:45 PM','09:00 PM':'09:00 PM','09:15 PM':'09:15 PM','09:30 PM':'09:30 PM','09:45 PM':'09:45 PM','10:00 PM':'10:00 PM','10:15 PM':'10:15 PM','10:30 PM':'10:30 PM','10:45 PM':'10:45 PM','11:00 PM':'11:00 PM','11:15 PM':'11:15 PM','11:30 PM':'11:30 PM','11:45 PM':'11:45 PM'}"></s:select>
						</div>
					</div>
					<div class="grid_6">
					<sj:textarea rows="3" cols="20" name="visitors.reason"
						label="Reason For Outing" id="reason" cssStyle="300"
						cssClass="text small  word_count"  required="true"
						requiredposition="left" tabindex="7"></sj:textarea>
					<div class="counter"></div>
				</div>
				</div>
			</div>
			<div class="grid_12">
				<s:url id="doAddNewStudentList" action="ajaxSearchVisitorsIn"
					includeParams="all" escapeAmp="false" namespace="/hostel">
					<s:param name="academicYearId" value="%{academicYearId}" />
				</s:url>
				<sj:a href="%{doAddNewStudentList}" cssClass="cancelButton"
					indicator="indicator" targets="stepHostelInOutRegister">Cancel</sj:a>
				<sj:submit   cssClass="submit small" value="Submit"
					onClickTopics="formValidation" validate="true"
					indicator="indicator" targets="stepHostelInOutRegister" />
			</div>
		</fieldset>
	</s:form>
</div>
<script type="text/javascript">
$(document).ready(function() {
$('#outStuDetails').hide();
	$('input#outWithVisitor').click( function() {
	if($('input#outWithVisitor').is(':checked')){
	  $('#outStuDetails').show();
	 // $('#studentOutDate').attr('required','true');
	  $('#studentOutDate').addClass('required','true')
	  
	  ///$('#studentOutTime').attr('required','true');
	  $('#studentOutTime').addClass('required','true')
	  
	 / $('#expectedInDate').attr('required','true');
	  $('#expectedInDate').addClass('required','true')
	  
	 // $('#exceptedInTime').attr('required','true');
	  $('#exceptedInTime').addClass('required','true')
	  
	  //$('#reason').attr('required','true');
	  $('#reason').addClass('required','true')
	}
	else{
	$('#outStuDetails').hide();
	//$('#studentOutDate').removeAttr("required");
	$('#studentOutDate').removeClass("required");
	//$('#studentOutTime').removeAttr("required");
	$('#studentOutTime').removeClass("required");
	  
	///$('#expectedInDate').removeAttr("required");
	$('#expectedInDate').removeClass("required");
	  
	//$('#exceptedInTime').removeAttr("required");
	$('#exceptedInTime').removeClass("required");
	  
	//$('#reason').removeAttr("required");
	$('#reason').removeClass("required");
	
	}
	});
	changePageTitle("Add Student");
	
	function ajaxStudentOutDates() {
	alert('tike');
		$('#exceptedInTime').val('');
	}
	$.subscribe('displayDate', function(event, data) {
		var outDate = $('#studentOutDate').val();
		var inDate = $('#expectedInDate').val();
		if (isNonEmpty(outDate) && isNonEmpty(inDate)) {
			outDate = Date.parse(outDate);
			inDate = Date.parse(inDate);
			if (inDate < outDate) {
				alert("out date should be equal/More then in date.");
				$('#expectedInDate').val("");
				return false;
			} else {
				return true;
			}
		}
	});
	$.subscribe('visitorDisplayDate', function(event, data) {
		var outDate = $('#visitorOutDate').val();
		var inDate = $('#visitorInDate').val();
		if (isNonEmpty(outDate) && isNonEmpty(inDate)) {
			outDate = Date.parse(outDate);
			inDate = Date.parse(inDate);
			if (inDate < outDate) {
				alert("In date should be equal/More then out date.");
				$('#visitorInDate').val("");
				return false;
			} else {
				return true;
			}
		}
	});
});
$('.numeric').numeric();
$('.alphabet').alpha();
function checkOutTimeInTImeValidation() {
	var outDate = $('#studentOutDate').val();
	var inDate = $('#expectedInDate').val();
	if (isNonEmpty(outDate) && isNonEmpty(inDate)) {
		var outTime = $('#studentOutTime').val();
		var inTime = $('#exceptedInTime').val();
		if (isNonEmpty(outTime) && isNonEmpty(inTime)) {
			var outDate = new Date(outDate + ' ' + outTime);
			var inDate = new Date(inDate + ' ' + inTime);
			if (inDate <= outDate) {
				alert("In time should be more than out time.");
				$('#exceptedInTime').val('');
			}
		}
	}
}

function checkOutVisitorTimeInTImeValidation() {
	var outDate = $('#visitorOutDate').val();
	var inDate = $('#visitorInDate').val();
	if (isNonEmpty(outDate) && isNonEmpty(inDate)) {
		var outTime = $('#outTime').val();
		var inTime = $('#inTime').val();
		if (isNonEmpty(outTime) && isNonEmpty(inTime)) {
			var outDate = new Date(outDate + ' ' + outTime);
			var inDate = new Date(inDate + ' ' + inTime);
			if (outDate <= inDate) {
				alert("Out time should be more than in time.");
				$('#outTime').val('');
			}
		}
	}
}

function ajaxStudentOutDates() {
	$('#exceptedInTime').val('');
}
</script>
