<%@ include file="/common/taglibs.jsp"%>
<div class="grid_9">
	<div class="block_head">
		<h2>
			Event Registration
		</h2>
	</div>

	<div class="block_content">
		<jsp:include page="/common/messages.jsp"></jsp:include>
		<s:form id="editGroupType" action="ajaxRegisterStudentEvent" method="post" theme="css_xhtml" namespace="/student">
		<s:hidden name="anyId"/>
			
			<table>
				<tr>
					<td>
						<b>Title:</b> <s:property value="calendarEvent.title" />
					</td>
				</tr>
				<tr>
					<td>
						<b>Description:</b> <s:property value="calendarEvent.message" />
					</td>
				</tr>
				<tr>
					<td>
						<b>Start Date:</b> <s:property value="calendarEvent.eventStartDateStr" />
					</td>
				</tr>
				<tr>
					<td>
						<b>End Date:</b> <s:property value="calendarEvent.eventEndDateAndTimeStr" />
					</td>
				</tr>
				<tr>
					<td>
						<b>Place:</b> <s:property value="calendarEvent.place" />
					</td>
				</tr>
				<tr>
					<td>
						<b>Organizar:</b> <s:property value="fileName" />
					</td>
				</tr>
				<tr>
					<td>
						<b>No of Participants:</b> <s:property value="calendarEvent.noOfPartispents" />
					</td>
				</tr>
				
				<tr>
					<td>
						<b>Available:</b> <s:property value="casualLeave" />
					</td>
				</tr>
				<s:if test="%{eventInvitedUser == null || eventInvitedUser.isEmpty()}">
			<tr>
				<td >
					<div>
						<sj:submit   targets="studentContent" value="Confirm" onClickTopics="editGroupTypeFormValidation"
				cssClass="submit small" indicator="indicator" formIds="editGroupType" />
					</div>
				</td>
				<!--<td>
					<s:url id="doEditGroupType" action="ajaxCancelRegistration"
				includeParams="all" escapeAmp="false">
				<s:param name="anyId" value="{anyId}" />
			</s:url>
			<sj:a href="%{doEditGroupType}" onCompleteTopics="cancelRegistration" cssClass="cancelButton"
				indicator="indicator" targets="registerEvent%{anyId}">
				Cancel
			</sj:a>
				</td>
			--></tr>
			</s:if>
			</table>
		</s:form>
		</div>
		</div>
		 
<script type="text/javascript">
changePageTitle('Edit Group Type');
$(document).ready(
function() {
	$.subscribe('editGroupTypeFormValidation', function(event, data) {
		  if ($('#editGroupType').valid())
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

function viewIntersetedList(eventId)
	{		
		if(document.getElementById("interestedInvitesDiv"+eventId).style.display == 'block'){
			document.getElementById("interestedInvitesDiv"+eventId).style.display="none";
			document.getElementById("test"+eventId).style.display="block";	
		}else{
			document.getElementById("interestedInvitesDiv"+eventId).style.display="block";	
			document.getElementById("test"+eventId).style.display="none";		
		}		
	}	
</script>