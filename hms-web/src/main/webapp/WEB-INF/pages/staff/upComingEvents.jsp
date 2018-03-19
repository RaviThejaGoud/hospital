<%@ include file="/common/taglibs.jsp"%>
<!--<div>
<jsp:include page="/WEB-INF/pages/staff/eventCalendar.jsp" />
</div>
			-->
			<div class="grid_3 alpha omega" style="width: 215px;">
	<div class="block_head">
		<h2>
			UpComing Events
		</h2>

	</div>
	<div class="block_content" id="sideMenu" style="padding: 10px;">

		<s:if test="%{tempList != null && !tempList.isEmpty()}">
			<marquee DIRECTION="up" scrollamount="2" onmouseover="stop();" onmouseout="start();">
				<div style="padding-top: 1px">
					<s:iterator value="tempList">
						<b>Title:</b>
						<br />
						<s:property value="title" />
						<br />
						<!--<b>Message:</b><br/>
			<s:property value="message" />
			<br/>
			-->
						<b>Start Date:</b>
						<br />
						<s:property value="eventStartDateStr" />
						<br />
						<!--<b>End Date:</b><br/>
			<s:property value="eventEndDateAndTimeStr" /><br/>
			<b>Venue:</b>
			<br/>
			<s:property value="place" />
			-->
						<br />

						<s:url id="doRegisterStudentEvent"
							action="ajaxDoRegisterStudentEvent" includeParams="all"
							escapeAmp="false">
							<s:param name="id" value="{id}" />
						</s:url>
						<sj:a href="%{doRegisterStudentEvent}" indicator="indicator"
							targets="staffContect">
				register
			</sj:a>
			======================<br />

					</s:iterator>
				</div>
			</marquee>
		</s:if>
		<s:else>
	Currently there are no Upcoming Events.
</s:else>

	</div>

</div>