<%@ include file="/common/taglibs.jsp"%>
<jsp:include page="/common/messages.jsp"></jsp:include>
<head>

</head>
<body />
	<div class="wrapper container_16">
		<!-- wrapper begins -->
		<div class="block grid_4">
			<div class="block_head">
				<h2>
					Inbox
				</h2>
			</div>
			<div class="block_content" id="sideMenu" style="padding-left: 0px; padding-right: 0px; padding-top: 0px;">
				<ul style="padding-left: 0px;">
					<li>
						<s:url id="ajaxClassAttendencelink" action="ajaxDoGetTeacherMessagesForParent" namespace="/student" />
						<sj:a href="%{ajaxClassAttendencelink}" targets="parentMessageContent"
							indicator="indicator">Inbox</sj:a>
					</li>
					<li class="active">
						<s:url id="urlMarkTempLink" action="ajaxDoGetTeacherMessagesForParent"  namespace="/student"/>
						<sj:a href="%{urlMarkTempLink}" targets="parentMessageContent"
							indicator="indicator">Sent</sj:a>
					</li>
					<li>
					<s:url id="urlMyMessagesLink" action="ajaxDoSelectMyChildren" />
					<sj:a href="%{urlMyMessagesLink}" targets="parentMessageContent"
						indicator="indicator">Compose New Message</sj:a>
				</li>
					
				<!--<li>
					<s:url id="urlLeaveLink" action="ajaxDoGetTeacherMessagesForParent" />
					<sj:a href="%{urlLeaveLink}" targets="parentMessageContent"
						indicator="indicator">Teacher Messages</sj:a>
				</li>
					-->
					<!--
					<li>
						<s:url id="urlMyMessagesLink" action="ajaxDoSendMyMessagesToParent" />
						<sj:a  href="%{urlMyMessagesLink}"
							targets="studentContent" indicator="indicator" onClickTopics="myInboxDetails">Compose Messages</sj:a>
					</li>
					
				--></ul>
			</div>
			<s:if test='%{user.isParent=="N" && user.isSchoolStudent=="Y"}'>
			<jsp:include page="/WEB-INF/pages/student/birthDayCalendar.jsp"></jsp:include>
			</s:if>
		</div>
		
		<div id="parentMessageContent" class="block_content">
			<jsp:include page="/WEB-INF/pages/student/parent/viewMyMessagesHome.jsp"></jsp:include>
		</div>
	</div>
	<script type="text/javascript">
changePageTitle("Manage Student Inbox");
$('#studentInbox').addClass('current');
</script>