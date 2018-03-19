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
						<s:url id="ajaxClassAttendencelink" action="ajaxDoGetMyMessages" namespace="/student"/>
						<sj:a href="%{ajaxClassAttendencelink}" targets="studentContent"
							indicator="indicator">Inbox</sj:a>
					</li>
					<li class="active">
						<s:url id="urlMarkTempLink" action="ajaxDoGetMyMessages" namespace="/student"/>
						<sj:a href="%{urlMarkTempLink}" targets="studentContent"
							indicator="indicator">Sent</sj:a>
					</li><!--
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
		
		<div id="studentContent" class="block_content">
			<jsp:include page="/WEB-INF/pages/student/ajaxViewMyMessages.jsp"></jsp:include>
		</div>
	</div>
	<script type="text/javascript">
changePageTitle("Manage Student Inbox");
$('#studentInbox').addClass('current');
</script>