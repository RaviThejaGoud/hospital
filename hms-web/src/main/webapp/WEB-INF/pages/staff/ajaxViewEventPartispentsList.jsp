<%@ include file="/common/taglibs.jsp"%>
<div id="commonTabContent" class="grid_10">
	<div id="commonTabWrapper">
		<div id="commonStep">
			<fieldset>
				<div class="grid_10 omega block" id="regId">
					<div>
						<s:if test="{calendarEvent != null}">
							<div class="grid_8 alpha omega">
								<div class="grid_8 alpha">
									<b>Event Details</b>
								</div>
								<div class="grid_3 alpha">
									<label class="labelRight">
										Event Name:
									</label>
								</div>
								<div class="grid_5 omega">
									<s:property value="calendarEvent.title" />
								</div>
								<div class="grid_3 alpha">
									<label class="labelRight">
										Event Type:
									</label>
								</div>
								<div class="grid_5 omega">
									<s:if test='%{calendarEvent.eventType == "R"}'>
						        Recurring Event
						</s:if>
									<s:else>
								One Time Event
						</s:else>
								</div>
								<div class="grid_3 alpha">
									<label class="labelRight">
										Occurence:
									</label>
								</div>
								<div class="grid_5 omega">
									<s:property value="calendarEvent.eventOccurence" />
								</div>
								<div class="grid_3 alpha">
									<label class="labelRight">
										Description:
									</label>
								</div>
								<div class="grid_5 omega">
									<s:property value="calendarEvent.message" />
									&nbsp;
								</div>
								<div class="grid_3 alpha">
									<label class="labelRight">
										Start Date:
									</label>
								</div>
								<div class="grid_5 omega">
									<s:property value="calendarEvent.startDateString" />
								</div>
								<div class="grid_3 alpha">
									<label class="labelRight">
										End Date:
									</label>
								</div>
								<div class="grid_5 omega">
									<s:property value="calendarEvent.endDateString" />
								</div>

								<div class="grid_3 alpha">
									<label class="labelRight">
										Start Time:
									</label>
								</div>
								<div class="grid_5 omega">
									<s:property value="calendarEvent.startTime" />
								</div>

								<div class="grid_3 alpha">
									<label class="labelRight">
										End Time:
									</label>
								</div>
								<div class="grid_5 omega">
									<s:property value="calendarEvent.endTime" />
								</div>

								<div class="grid_3 alpha">
									<label class="labelRight">
										Place:
									</label>
								</div>
								<div class="grid_5 omega">
									<s:property value="calendarEvent.place" />
								</div>

								<div class="grid_3 alpha">
									<label class="labelRight">
										Event For:
									</label>
								</div>
								<div class="grid_5 omega">
									<s:if test='%{calendarEvent.eventType == "R"}'>
						        Classes
						</s:if>
									<s:else>
								To All
						</s:else>
								</div>

								<div class="grid_3 alpha">
									<label class="labelRight">
										No Of Participants:
									</label>
								</div>
								<div class="grid_5 omega">
									<s:property value="calendarEvent.noOfPartispents" />
								</div>

								<div class="grid_3 alpha">
									<label class="labelRight">
										Availability
									</label>
								</div>
								<div class="grid_5 omega">
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
							</div>
						</s:if>
						<s:else>
							<div class="grid_5 omega">
								&nbsp;&nbsp;
							</div>
						</s:else>
					</div>
				</div>
					<div class="grid_8">
						<b>Student Details</b>
					</div>
					<s:if
						test="%{viewStudentPersonAccountDetailsList != null && !viewStudentPersonAccountDetailsList.isEmpty()}">
						<div class="grid_10 th" id="results">
							<div class="grid_2">
								Sno
							</div>
							<div class="grid_2">
								Student Id
							</div>
							<div class="grid_2">
								Student Name
							</div>
							<div class="grid_2">
								Class
							</div>
							<div class="grid_2">
								Action
							</div>
							<div id="resultsPage">
								<%
									int i = 0;
								%>
								<s:iterator value="viewStudentPersonAccountDetailsList">
									<div class="grid_10 row">
										<div class="grid_2 loaded">
											<%
												i++;
											%><%=i%>
										</div>
										<div class="grid_2">
											<s:property value="username" />
										</div>
										<div class="grid_2">
											<s:property value="personFirstLastNameOnly" />
										</div>
										<div class="grid_2">
											<s:property value="ClassAndSection" />
										</div>
										<div class="grid_2">
											<s:url id="removeStudentRegEvent"
												action="ajaxRemoveRegistrationStudentEvent"
												includeParams="all" escapeAmp="false">
												<s:param name="id" value="{id}" />
												<s:param name="calendarEvent.id" value="calendarEvent.id" />
											</s:url>
											<sj:a href="%{removeStudentRegEvent}"
												onBeforeTopics="cleanOpenRows" onCompleteTopics=""
												indicator="indicator" targets="regId">
										With Draw
									</sj:a>
										</div>
										<div id="showEventDetails<s:property value='id' />"
											style="display: none;" class="load">
										</div>
									</div>
								</s:iterator>
							</div>
						</div>
					</s:if>
					<s:else>
						<div class="grid_10">
							Currently there are no Participants.
						</div>
					</s:else>
				</fieldset>
			</div>
		</div>
	</div>
</div>
