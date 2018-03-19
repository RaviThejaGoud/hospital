<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>
<div class="grid_14 commomnTabs">
		<div class="grid_14 subMenus">
			<ul>
				<li class="active" style="background-image: none;">
					<s:url id="doLoadManageMessFood" action="ajaxLoadMessInfoByStatus"
						includeParams="all" escapeAmp="false" namespace="/hostel">
						<s:param name="type" value="%{'MessFoodTypes'}" />
						<s:param name="building.id" value="%{building.id}"></s:param>
					</s:url>
					<sj:a href="%{doLoadManageMessFood}" indicator="indicator"
						id="addFoodTypes" targets="viewMessSteps">Manage Food Types</sj:a>
				</li>
				<li>
					<s:url id="doLoadManageMessTimings" action="ajaxLoadMessInfoByStatus"
						includeParams="all" escapeAmp="false" namespace="/hostel">
						<s:param name="type" value="%{'MessTimings'}" />
						<s:param name="building.id" value="%{building.id}"></s:param>
					</s:url>
					<sj:a href="%{doLoadManageMessTimings}" indicator="indicator"
						id="addMessTimes" targets="viewMessSteps">Manage Mess Timings</sj:a>
				</li>
				<li>
					<s:url id="doLoadManageMessItems" action="ajaxLoadMessInfoByStatus"
						includeParams="all" escapeAmp="false" namespace="/hostel">
						<s:param name="type" value="%{'MessFoodItems'}" />
						<s:param name="building.id" value="%{building.id}"></s:param>
					</s:url>
					<sj:a href="%{doLoadManageMessItems}"  targets="viewMessSteps">Manage Food Items </sj:a>
				</li>
			</ul>
		</div>
		<div id="viewMessSteps" class="grid_14">
			<jsp:include
				page="/WEB-INF/pages/hostel/messManagement/ajaxViewMessSettingsTypesList.jsp"></jsp:include>
		</div>
</div>
<script type="text/javascript">
$(document).ready(function() {
	changePageTitle("Mess Settings");
});
</script>