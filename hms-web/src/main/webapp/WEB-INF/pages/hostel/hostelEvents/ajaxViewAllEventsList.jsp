<%@ include file="/common/taglibs.jsp"%>
<s:url id="urlAdminGetSchoolCalendarLink" action="ajaxGetAdminCalendar"
	escapeAmp="false" includeParams="all" namespace="/hostel">
</s:url>
<sj:a id="adminGetSchoolCalendarLink"
	href="%{urlAdminGetSchoolCalendarLink}" targets="stepCalendar"
	indicator="indicator" button="false" cssClass="linkRight">Back</sj:a>
<s:if test="%{anyTitle == 'Today'}">
	<div class="grid_14">
		<s:if test="%{objectList != null && !objectList.isEmpty()}">
			<div class="grid_14">
				<div class="grid_3">
					<h1>
						This Today Events
					</h1>
				</div>
				<div class="grid_11" align="right" data-target="viewTodayEvents">
					<jsp:include
						page="/WEB-INF/pages/common/ajaxViewPaginationOptions.jsp"></jsp:include>
				</div>
			</div>
			<div class="grid_14 th">
				<div class="grid_5">
					Event Name
				</div>
				<div class="grid_5">
					Start Date
				</div>
				<div class="grid_4">
					End Date
				</div>
			</div>
			<div id="viewTodayEvents">
				<s:iterator value="objectList">
					<div class="grid_14 row">
						<div class="grid_5">
							<s:property value="eventName" />
						</div>
						<div class="grid_5">
							<s:property value="eventStartDateStr" />
						</div>
						<div class="grid_4">
							<s:property value="eventEndDateStr" />
						</div>
					</div>
				</s:iterator>
			</div>
		</s:if>
		<s:else>
			 Currently there are no Today events.
	</s:else>
	</div>
</s:if>
<s:elseif test="%{anyTitle == 'Weekly'}">
	<div class="grid_14 omega">
		<s:if test="%{tempList != null && !tempList.isEmpty()}">
			<div class="grid_14">
				<div class="grid_3">
					<h1>
						This Week Events
					</h1>
				</div>
				<div class="grid_11" align="right" data-target="viewWeeklyEvents">
					<jsp:include
						page="/WEB-INF/pages/common/ajaxViewPaginationOptions.jsp"></jsp:include>
				</div>
			</div>
			<div class="grid_14 th">
				<div class="grid_5">
					Event Name
				</div>
				<div class="grid_5">
					Start Date
				</div>
				<div class="grid_4">
					End Date
				</div>
			</div>
			<div id="viewWeeklyEvents">
				<s:iterator value="tempList">
					<div class="grid_14 row">
						<div class="grid_5">
							<s:property value="eventName" />
						</div>
						<div class="grid_5">
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
			 Currently there are no week events.
	</s:else>
	</div>
</s:elseif>
<s:elseif test="%{anyTitle == 'Monthly'}">
	<div class="grid_14 omega">
		<s:if test="%{tempList1 != null && !tempList1.isEmpty()}">
		   <div class="grid_14">
				<div class="grid_3">
					<h1>
						This Monthly Events
					</h1>
				</div>
				<div class="grid_11" align="right" data-target="viewMonthlyEvents">
					<jsp:include
						page="/WEB-INF/pages/common/ajaxViewPaginationOptions.jsp"></jsp:include>
				</div>
			</div>
			<div class="grid_14 th">
				<div class="grid_5">
					Event Name
				</div>
				<div class="grid_5">
					Start Date
				</div>
				<div class="grid_4">
					End Date
				</div>
			</div>
			<div id="viewMonthlyEvents">
				<s:iterator value="tempList1">
					<div class="grid_14 row">
						<div class="grid_5">
							<s:property value="eventName" />
						</div>
						<div class="grid_5">
							<s:property value="eventStartDateStr" />
						</div>
						<div class="grid_4">
							<s:property value="eventEndDateStr" />
						</div>
					</div>
				</s:iterator>
			</div>
		</s:if>
		<s:else>
			 Currently there are no month events.
	</s:else>
	</div>
</s:elseif>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/subscription/paginator_dev.js">
</script>
<script type="text/javascript">
$(document).ready(function() {
	$('#viewTodayEvents').pagination();
	$('#viewWeeklyEvents').pagination();
	$('#viewMonthlyEvents').pagination();
});
</script>