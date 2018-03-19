<%@ include file="/common/taglibs.jsp"%>
<div class="grid_3 alpha omega"
	style="width: 215px; margin-top: 14px; margin-left: 10px;">
	<div class="block_head">
		<h2>
			My Messages
		</h2>

	</div>
	<div class="block_content" id="sideMenu">

		<s:if test="%{messagesList != null && !messagesList.isEmpty()}">
			<div style="padding-top: 1px">
				<b>You have </b>
				<s:property value="messagesList.size()" />
				<b>Messages</b>
				<br />

				<s:url id="doRegisterStudentEvent" action="ajaxDoGetTeacherMessages"
					includeParams="all" escapeAmp="false">
				</s:url>
				<sj:a href="%{doRegisterStudentEvent}" indicator="indicator"
					targets="staffContect">
				View Messages
			</sj:a>

			</div>
		</s:if>
		<s:else>
	Currently there are no Messages.
</s:else>

	</div>

</div>