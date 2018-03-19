<%@ include file="/common/taglibs.jsp"%>
<div class="grid_9">
	<div class="block_head">
		<h2>
			Event Registration
		</h2>
	</div>
	<div class="block_content">
		<jsp:include page="/common/messages.jsp"></jsp:include>
		<s:form id="registerStudentEvent" action="ajaxRegisterStudentEvent"
			method="post" theme="css_xhtml">
			<s:hidden name="anyId" />
			<table  class="editEventTable">
				<tr>
					<td>
						<b>Title:</b>
						<s:property value="calendarEvent.title" />
					</td>
				</tr>
				<tr>
					<td>
						<b>Description:</b>
						<s:property value="calendarEvent.message" />
					</td>
				</tr>
				<tr>
					<td>
						<b>Start Date:</b>
						<s:property value="calendarEvent.eventStartDateStr" />
					</td>
				</tr>
				<tr>
					<td>
						<b>End Date:</b>
						<s:property value="calendarEvent.eventEndDateAndTimeStr" />
					</td>
				</tr>
				<tr>
					<td>
						<b>Place:</b>
						<s:property value="calendarEvent.place" />
					</td>
				</tr>
				<tr>
					<td>
						<b>Organizar:</b>
						<s:property value="viewStaffPersonAccountDetails.personFullName" />
					</td>
				</tr>
				<tr>
					<td>
						<b>No of Participants:</b>
						<s:property value="calendarEvent.noOfPartispents" />
					</td>
				</tr>

				<tr>
					<td>
						<b>Available:</b>
						<s:property value="casualLeave" />
					</td>
				</tr>
				<!--<tr>
					<td>
						<s:textfield name="userName" id="userName" label="Enter Student Id"
		labelposition="top" cssClass="text small required" required="true" maxlength="80" requiredposition="first"></s:textfield>
					</td>
				</tr>
				
				
				-->
				<tr>
					<td>

						<sj:autocompleter id="allStudentsList" name="username"
							list="%{classNameList}" selectBox="true" loadMinimumCount="1"
							label="Search Student" required="true" requiredposition="left"
							cssClass="text small required" />
						<sj:submit   id="submitFormAutocomplete" targets="staffContect"
							cssClass="submit small" value="Submit" indicator="indicator"
							onClickTopics="registerStudentEventFormValidation" />
					</td>
				</tr>
			</table>
		</s:form>
	</div>
</div>

<script type="text/javascript">
	changePageTitle('Invitation Details');
	$(document).ready(
			function() {
				$.subscribe('registerStudentEventFormValidation', function(
						event, data) {
					if ($('#registerStudentEvent').valid())
						return true;
					else
						return false;
				});
			});

	$.subscribe('cancelRegistration', function(event, data) {
		if ($('#' + data.id).is(":hidden")) {
			$('#' + data.id).show();
		} else {
			$('#' + data.id).hide();
		}
	});

	function viewIntersetedList(eventId) {
		if (document.getElementById("interestedInvitesDiv" + eventId).style.display == 'block') {
			document.getElementById("interestedInvitesDiv" + eventId).style.display = "none";
			document.getElementById("test" + eventId).style.display = "block";
		} else {
			document.getElementById("interestedInvitesDiv" + eventId).style.display = "block";
			document.getElementById("test" + eventId).style.display = "none";
		}
	}
</script>