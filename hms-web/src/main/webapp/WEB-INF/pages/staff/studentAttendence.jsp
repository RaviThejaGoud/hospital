<%@ include file="/common/taglibs.jsp"%>
<div class="block_head" style="width: 240PX;">
	<h2>
		View Attendance
	</h2>
</div>
<div class="block_content" id="sideMenu" style="height: 200px;width: 241px;">
	<s:if test="%{messagesList != null && !messagesList.isEmpty()}">
		<div style="padding-top: 1px">
		<s:if test="%{classNameList.size() != null }">
			<b>You have </b>
			<s:property value="classNameList.size()" />
          <div><a href="doClassAttendance.do">Attendance reports.</a></div></s:if>
			<s:else>
	       		 Currently there are no Attendance reports.
			</s:else>
		</div>
	</s:if>
	<s:else>
	        Currently there are no Attendance reports.
</s:else>
</div>
