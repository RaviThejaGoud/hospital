<%@ include file="/common/taglibs.jsp"%>
	<div class="block_head">
			<b>Inbox</b>

	</div>
	<div class="block_content" id="sideMenu" >
		<ul style="padding-left: 0px;">
			<li>
				<s:url id="urlStaffCalendarLink" action="" />
				<sj:a id="staffCalendarLink" href="%{urlStaffCalendarLink}"
					targets="staffContect" indicator="indicator">Inbox</sj:a>
			</li>
			<li>
				<s:url id="urlStaffActivitiesLink" action="" />
				<sj:a id="staffActivitiesLink" href="%{urlStaffActivitiesLink}"
					targets="staffContect" indicator="indicator">Sent</sj:a>
			</li>
		</ul>
	</div>