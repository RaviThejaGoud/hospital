<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js"></script>
<div class="grid_9">
	<div class="block_head">
		<h2>
			Event Details
		</h2>

	</div>
	<div class="block_content" id="staffId">
		<s:if test="%{eventListByDate != null && !eventListByDate.isEmpty()}">
			<table>
				<s:iterator value="eventListByDate">
					<tr>
						<td>
							<b>Title:</b>
							<s:property value="title" />
						</td>
					</tr>
					<tr>
						<td>
							<b>Description:</b>
							<s:property value="message" />
						</td>
					</tr>
					<tr>
						<td>
							<b>Event Type:</b>
							<s:if test='%{eventType == "R"}'>
						        Recurring Event
						</s:if>
							<s:else>
								One Time Event
						</s:else>
						</td>
					</tr>
					<tr>
						<td>
							<b>Occurence:</b>
							<s:property value="eventOccurence" />
						</td>
					</tr>
					<tr>
						<td>
							<b>Start Date:</b>
							<s:property value="eventStartDateStr" />
						</td>
					</tr>
					<tr>
						<td>
							<b>End Date:</b>
							<s:property value="eventEndDateAndTimeStr" />
						</td>
					</tr>
					<tr>
						<td>
							<b>Place:</b>
							<s:property value="place" />
						</td>
					</tr>
					<tr>
						<td>
							<b>Organizer:</b>
							<s:property value="organizerId" />
						</td>
					</tr>
					<tr>
						<td>
							<b>No Of Participants:</b>
							<s:property value="noOfPartispents" />
						</td>
					</tr>
					<tr>
						<td>
							======================
						</td>

					</tr>
				</s:iterator>
			</table>
		</s:if>
		<s:else>
	Currently there are no events.
	</s:else>
	</div>
</div>




