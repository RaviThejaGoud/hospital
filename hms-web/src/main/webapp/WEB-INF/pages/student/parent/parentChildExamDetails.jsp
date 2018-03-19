<%@ include file="/common/taglibs.jsp"%>
<s:if test="%{examScheduleList != null && !examScheduleList.isEmpty()}">
	<s:iterator value="examScheduleList">
		<h3 style="margin-top: 0px;">
					<s:property value="classId.classAndSection" />
					:
					<s:property value="examType.examType" />
		</h3>
		<div>
			Start Date:
			<s:property value="startTime" />
		</div>
		<div>
			End Date:
			<s:property value="endTime" />
		</div>
	</s:iterator>
	<div>
		<a href="doStudentActivitiesHome.do">More Details</a>
	</div>
</s:if>
<s:else>
		Currently there are no up coming exam schedules.
</s:else>