<%@ include file="/common/taglibs.jsp"%>
<jsp:include page="/common/messages.jsp"></jsp:include>
<div class="wrapper container_16">
	<div class="block grid_4">
		<div class="block_head">
			<h2>
				Inbox
			</h2>

		</div>
		<div class="block_content" id="sideMenu" >
			<ul style="padding-left: 0px;">
				 	<!--<li>
					<s:url id="urlStaffSentLink" action="ajaxMySentMessages" />
					<sj:a id="staffActivitiesLink" href="%{urlStaffSentLink}"
						targets="myInboxHome" indicator="indicator1" onClickTopics="myInboxDetails">Sent Alerts</sj:a>
				</li>
				-->
				<li>
					<s:url id="urlComposeScrapMsgLink"
						action="ajaxDoComposeScrapMessage" />
					<sj:a href="%{urlComposeScrapMsgLink}" targets="inboxHome"
						indicator="indicator1" onClickTopics="myInboxDetails">Compose Messages</sj:a>
				</li>
				<li>
					<s:url id="urlInboxScrapMessagesLink"
						action="ajaxDoInboxGetScrapMessagesList" />
					<sj:a href="%{urlInboxScrapMessagesLink}" targets="inboxHome"
						indicator="indicator1" onClickTopics="myInboxDetails">Inbox</sj:a>
				</li>
				<s:if test='%{user.IsSchoolStudent=="Y"}'>
					<li class="active">
						<s:url id="urlSchoolWideMessagesLink"
							action="ajaxDoGetSchoolWideAlertsList" />
						<sj:a href="%{urlSchoolWideMessagesLink}" targets="inboxHome"
							indicator="indicator1" onClickTopics="myInboxDetails">Alerts</sj:a>
					</li>
				</s:if>
				<!--<li>
						<s:url id="urlSchoolWideMessagesLink"
							action="readMoreAlerts" />
						<sj:a href="%{urlSchoolWideMessagesLink}" targets="inboxHome"
							indicator="indicator1" onClickTopics="myInboxDetails">Alerts</sj:a>
					</li>
				-->
				<s:if test='%{user.IsSchoolAdmin=="Y"}'>
					<li>
						<s:url id="urlSchoolWideMessagesLink" namespace="/common" action="ajaxDoGetSchoolWideMessagesList" />
						<sj:a href="%{urlSchoolWideMessagesLink}" targets="inboxHome" indicator="indicator1" onClickTopics="myInboxDetails">School wide Messages</sj:a>
					</li>

					<li>
						<s:url id="urlSchoolWideMessagesLink"
							action="ajaxDoGetSchoolWideAlertsList" />
						<sj:a href="%{urlSchoolWideMessagesLink}" targets="inboxHome"
							indicator="indicator1" onClickTopics="myInboxDetails">Alerts</sj:a>
					</li>

				</s:if>
				<!--<li>
					<s:url id="urlSentBoxScrapMessagesLink" action="ajaxDoSentBoxGetScrapMessagesList" />
					<sj:a  href="%{urlSentBoxScrapMessagesLink}"
						targets="inboxHome" indicator="indicator1" onClickTopics="myInboxDetails">Sent</sj:a>
				</li>
				-->
				<s:if test='%{user.isSchoolTeacher=="Y"}'>
					<li class="active">
						<s:url id="urlSchoolWideMessagesLink"
							action="ajaxDoGetSchoolWideAlertsList" />
						<sj:a href="%{urlSchoolWideMessagesLink}" targets="inboxHome"
							indicator="indicator1" onClickTopics="myInboxDetails">Alerts</sj:a>
					</li>
					<!--<li>
						<s:url id="urlStaffMessagesLink" action="ajaxGetMyInbox" />
						<sj:a id="staffMessagesLink" href="%{urlStaffMessagesLink}"
							targets="inboxHome" indicator="indicator1"
							onClickTopics="myInboxDetails">Alerts</sj:a>
					</li>
					-->
					<!--<li>
						<s:url id="urlParentAlertMessageLink"
							action="/staff/ajaxDoGetParentMessages" />
						<sj:a href="%{urlParentAlertMessageLink}" targets="myInboxHome"
							indicator="indicator1" onClickTopics="myInboxDetails">Parent Messages</sj:a>
					</li>
				-->
				</s:if>
				<s:elseif test='%{user.isParent=="Y"}'>
					<li>
						<s:url id="ajaxClassAttendencelink"
							action="/student/ajaxDoGetTeacherMessagesForParent" />
						<sj:a href="%{ajaxClassAttendencelink}" targets="inboxHome"
							indicator="indicator1">Alerts</sj:a>
					</li>
				</s:elseif>
			</ul>
		</div>
		<br />
		<s:if test='%{user.isSchoolTeacher=="Y"}'>
			<jsp:include
				page="/WEB-INF/pages/staff/ajaxStaffBirthDayCalendar.jsp" />
		</s:if>
	</div>
	<div class="block grid_12">
		<div id="inboxHome">
			<jsp:include
				page="/WEB-INF/pages/common/alerts/sendSchoolWideAlertsHome.jsp"></jsp:include>

			<div id=myInboxHome>

			</div>
		</div>
		<s:if test='%{user.isSchoolTeacher=="Y"}'>
			<div class="" id=myBirthDayHome style="display: none;">
				<!--<jsp:include page="/WEB-INF/pages/staff/ajaxStaffBirthDayWishes.jsp" />
			--></div>
		</s:if>
	</div>
</div>
<script type="text/javascript">
$('#studentInbox').addClass('current');
$(document).ready(function() {
	$("a.newMessage").fancybox( {
		'width' : '45%',
		'height' : '66%',
		'autoScale' : false,
		'transitionIn' : 'none',
		'transitionOut' : 'none',
		'autoDimensions' : false,
		'showCloseButton' : true

	});
});

$('#staffInbox').addClass('current');
$(document).ready(function() {
	$.subscribe('staffBirthDayDetails', function(event, data) {
		$("#myBirthDayHome").show();
		$("#inboxHome").hide();
	})
});
$(document).ready(function() {
	$.subscribe('myInboxDetails', function(event, data) {
		$("#myBirthDayHome").hide();
		$("#inboxHome").show();
	})
});
</script>
