<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>
<div class="grid_14 commomnTabs">
	<jsp:include page="/common/messages.jsp" />
	<div class="grid_14 omega">
		 	<s:url id="urlAdminGetSchoolCalendarLink" action="ajaxGetAdminCalendar"
				escapeAmp="false" includeParams="all" namespace="/admin">
			</s:url>
	    	<sj:a id="adminGetSchoolCalendarLink" href="%{urlAdminGetSchoolCalendarLink}"
				targets="stepCalendar" indicator="indicator" button="false"
				cssClass="linkRight">Back</sj:a>
			<s:if test="%{objectList != null && !objectList.isEmpty()}">
			<h1>
				This Week Events
			</h1>
			<div class="grid_14 th">
				<div class="grid_4">
					Event Name
				</div>
				<div class="grid_4">
					Start Date
				</div>
				<div class="grid_3">
					End Date
				</div>
			</div>
			<div id="resultsPage">
				<s:iterator value="objectList">
					<div class="grid_14 row">
						<div class="grid_4">
							<s:property value="eventName" />
						</div>
						<div class="grid_4">
							<s:property value="eventStartDateStr" />
						</div>
						<div class="grid_3">
							<s:property value="eventEndDateStr" />
						</div>
					</div>
				</s:iterator>
			</div>
		</s:if>
		<s:else>
			 Currently there are no upcoming week events.
	</s:else>
	</div>
	<div class="grid_4">&nbsp;</div>
	<div class="grid_14 omega">
		<s:if test="%{holidayBoardMessagesList!= null && !holidayBoardMessagesList.isEmpty()}">
			<h1>
				This Week Holidays
			</h1>
			<div class="grid_14 th">
				<div class="grid_4">
					Description
				</div>
				<div class="grid_4">
					Holiday(s)
					<b>From</b>
				</div>
				<div class="grid_3">
					<b>To</b>
				</div>
			</div>
			<s:iterator value="holidayBoardMessagesList">
				<div class="grid_14 row ">
					<div class="grid_4">
						<s:property value="description" />
					</div>
					<div class="grid_4">
						<s:property value="startDateStr" />
					</div>
					<div class="grid_3">
						<s:property value="endDateStr" />
					</div>
				</div>
			</s:iterator>
		</s:if>
		<s:else>
			 Currently there are no upcoming week holidays.
	</s:else>
	</div>
</div>
 
 
 
