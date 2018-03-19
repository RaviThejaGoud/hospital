<%@ include file="/common/taglibs.jsp"%>
<div  style="width: 250px; margin-top: -15px; margin-left: 36px;">
	<div class="block_head">
		<h2>
			Inbox
		</h2>
	</div>
	<div class="block_content" id="sideMenu" style="padding: 10px;">

		<s:if test="%{messagesList != null && !messagesList.isEmpty()}">
			<div style="padding-top: 1px">
				<b>You have </b>
				<s:property value="messagesList.size()" />
				<b>Messages</b>
				<br />

				<s:url id="doRegisterStudentEvent" action="ajaxDoGetTeacherMessagesCountForParent"
					includeParams="all" escapeAmp="false" namespace="/student">
				</s:url>
				<sj:a href="%{doRegisterStudentEvent}" indicator="indicator"
					targets="studentContent">
				View Messages
			</sj:a>

			</div>
		</s:if>
		<s:else>
	Currently there are no Messages.
</s:else>

	</div>

</div>