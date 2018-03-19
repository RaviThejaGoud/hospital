<%@ include file="/common/taglibs.jsp"%>
<td colspan="6">
	<jsp:include page="/common/messages.jsp"></jsp:include>
		<div class="grid_7 omega block" >
			<div class="grid_3 alpha" >
				<b>Title:</b>
			</div>
			<div class="grid_4 omega">
				<s:property value="calendarEvent.title" />
			</div>
			<div class="grid_7 alpha">&nbsp;</div>
			<div class="grid_3 alpha" >
				<b>Description:</b>
			</div>
			<div class="grid_4 omega">
				<s:property value="calendarEvent.message" />
			</div>
			<div class="grid_7 alpha">&nbsp;</div>
			<div class="grid_3 alpha" >
				<b>Event Type:</b>
			</div>
			<div class="grid_4 omega">
				<s:if test='%{calendarEvent.eventType == "R"}'>
					  Recurring Event
				</s:if>
				<s:else>
			   		 One Time Event
				</s:else>
			</div>
			<div class="grid_7 alpha">&nbsp;</div>
			<div class="grid_3 alpha" >
				<b>Occurence:</b>
			</div>
			<div class="grid_4 omega">
				<s:property value="calendarEvent.eventOccurence" />
			</div>
			<div class="grid_7 alpha">&nbsp;</div>
			<div class="grid_3 alpha" >
				<b>Start Date:</b>
			</div>
			<div class="grid_4 omega">
				<s:property value="calendarEvent.startDateString" />
			</div>
			<div class="grid_7 alpha">&nbsp;</div>
			<div class="grid_3 alpha" >
				<b>End Date:</b>
			</div>
			<div class="grid_4 omega">
				<s:property value="calendarEvent.endDateString" />
			</div>
			<div class="grid_7 alpha">&nbsp;</div>
			<div class="grid_3 alpha" >
				<b>Start Time:</b>
			</div>
			<div class="grid_4 omega">
				<s:property value="calendarEvent.startTime" />
			</div>
			<div class="grid_7 alpha">&nbsp;</div>
			<div class="grid_3 alpha" >
				<b>End Time:</b>
			</div>
			<div class="grid_4 omega">
				<s:property value="calendarEvent.endTime" />
			</div>
			<div class="grid_7 alpha">&nbsp;</div>
			<div class="grid_3 alpha" >
				<b>Place:</b>
			</div>
			<div class="grid_4 omega">
				<s:property value="calendarEvent.place" />
			</div>
			<div class="grid_7 alpha">&nbsp;</div>
			<div class="grid_3 alpha" >
				<b>Organizar:</b>
			</div>
			<div class="grid_4 omega">
				<s:property value="calendarEvent.organizerId" />
			</div>
			<div class="grid_7 alpha">&nbsp;</div>
			<div class="grid_3 alpha" >
				<b>No Of Participants:</b>
			</div>
			<div class="grid_4 omega">
				<s:property value="calendarEvent.noOfPartispents" />
			</div>
			<div class="grid_7 alpha">&nbsp;</div>
			<div class="grid_3 alpha" >
				<b>Available:</b>
			</div>
			<div class="grid_4 omega">
				<s:if
					test="{calendarEvent.invitedUsers != null && !calendarEvent.invitedUsers.isEmpty()}">
					<s:set name="invitedCount"
						value="%{calendarEvent.invitedUsers.size}" />
					<s:set name="maxInviteesCount"
						value="%{calendarEvent.noOfPartispents}" />
					<s:set name="availableCount"
						value="%{#maxInviteesCount-#invitedCount}" />
					<s:property value='%{"" +#availableCount+ ""}' />
				</s:if>
				<s:else>
					<s:property value="calendarEvent.noOfPartispents" />
				</s:else>
			</div>
			<div class="grid_7 alpha">&nbsp;</div>
			<div class="grid_7" >
				<s:form id="registerStudentEvent" action="ajaxFindStudent"
					method="post" theme="css_xhtml">
					<s:hidden name="anyId" value="%{calendarEvent.id}"/>
					<div class="grid_3">
						<sj:autocompleter id="allStudentsList" name="username"
							list="%{classNameList}" selectBox="true" loadMinimumCount="1"
							label="Search Student" required="true" requiredposition="left"
							cssClass="textfield required" cssStyle="width :160px" />
					</div>
					<div class="grid_3" style="margin-top: 13px; margin-left: 23px;">
						<sj:submit   id="submitFormAutocomplete" targets="studentDivId"
							cssClass="submit small" value="Find" indicator="indicator"
							onClickTopics="registerStudentEventFormValidation" />
					</div>
				</s:form>
			</div>
		</div>
     <div class="grid_7 alpha" id="searchByLeaderName"></div>
  <div class="grid_7 alpha" id="studentDivId" class='load' style="width: 440px;"> </div>   
</td>
<script type="text/javascript">
	changePageTitle('Add Student Registration');
	$(document).ready(function() {
		$.subscribe('registerStudentEventFormValidation', function(event, data) {
			if ($('#registerStudentEvent').valid())
				return true;
			else
				return false;
		});
	});
</script>