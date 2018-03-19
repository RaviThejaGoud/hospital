<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>
<div class="grid_14 commomnTabs">
		<div class="grid_14 subMenus">
			<ul>
				<%--<li class="active" style="background-image: none;">
					<s:url id="doToday" action="ajaxLoadEventStatus"
						includeParams="all" escapeAmp="false">
						<s:param name="type" value="%{'Today'}" />
					</s:url>
					<sj:a href="%{doToday}" indicator="indicator"
						id="addFoodTypes" targets="viewEventSteps">Today Events</sj:a>
				</li>
				--%><li class="active" style="background-image: none;">
					<s:url id="doWeekly" action="ajaxLoadEventStatus"
						includeParams="all" escapeAmp="false" namespace="/hostel">
						<s:param name="type" value="%{'Weekly'}" />
					</s:url>
					<sj:a href="%{doWeekly}" indicator="indicator"
						id="addMessTimes" targets="viewEventSteps">Weekly Events</sj:a>
				</li>
				<%--<li>
					<s:url id="doMonthly" action="ajaxLoadEventStatus"
						includeParams="all" escapeAmp="false">
						<s:param name="type" value="%{'Monthly'}" />
					</s:url>
					<sj:a href="%{doMonthly}"  targets="viewEventSteps">Monthly Events</sj:a>
				</li>
			--%></ul>
		</div>
		<div id="viewEventSteps" class="grid_14">
			<jsp:include
				page="/WEB-INF/pages/hostel/hostelEvents/ajaxViewAllEventsList.jsp"></jsp:include>
		</div>
</div>



