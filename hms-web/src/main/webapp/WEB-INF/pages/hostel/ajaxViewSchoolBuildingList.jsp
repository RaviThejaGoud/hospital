<%@ include file="/common/taglibs.jsp"%>
<div class="grid_14 commomnTabs">
	<s:if test="%{buildingList != null && !buildingList.isEmpty()}">
		<div class="grid_14 th">
			<div class="grid_3">
				No.of Buildings
			</div>
			<div class="grid_2">
				Building Start Series From
			</div>
			<div class="grid_3">
				Building Start From
			</div>
			<div class="grid_2">
				Edit
			</div>
		</div>
		<s:iterator value="buildingList">
			<div class="grid_14 row">
				<div class="grid_3">
					<s:property value="hostel.noOfBuildings" />
				</div>
				<div class="grid_2">
					<s:property value="hostel.buildingStartSeriesFrom" />
				</div>
				<div class="grid_3">
					<s:property value="hostel.buildingStartFrom" />
				</div>
				<div class="grid_2">
					<s:url id="doViewBuildingList" action="ajaxDoAddBuildingSettings"
						includeParams="all" escapeAmp="false" namespace="/hostel">
						<s:param name="tempId" value="%{id}" />
					</s:url>
					<sj:a href="%{doViewBuildingList}" indicator="indicator"
						targets="stepHostel" cssClass="normalEdit" title="Edit">
					</sj:a>
					&nbsp;
				</div>
			</div>
		</s:iterator>
	</s:if>
	<s:else>
			No Buildings found.
		</s:else>
</div>