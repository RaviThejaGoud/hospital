<%@ include file="/common/taglibs.jsp"%>
<jsp:include page="/common/messages.jsp"></jsp:include>
<div class="wrapper container_16">
	<div class="block grid_12">
	<div class="block_head">
		<h2>
			KBank
		</h2>
		<div id="topMenu">
			<ul>
				<li>
					<s:url id="urlMyMessagesLink" action="" />
					<sj:a href="%{urlMyMessagesLink}" targets="staffContect"
						indicator="indicator">Search</sj:a>
				</li>
				<li>
					<s:url id="urlMyMessagesLink" action="" />
					<sj:a href="%{urlMyMessagesLink}" targets="staffContect"
						indicator="indicator">Upload New</sj:a>
				</li>
				<li>
					<s:url id="urlMyMessagesLink" action="" />
					<sj:a href="%{urlMyMessagesLink}" targets="staffContect"
						indicator="indicator">Updates</sj:a>
				</li>
				<li>
					<s:url id="urlMyMessagesLink" action="" />
					<sj:a href="%{urlMyMessagesLink}" targets="staffContect"
						indicator="indicator">My Favourites</sj:a>
				</li>
			</ul>
		</div>
	</div>
	<div class="block_content" id="myInboxHome">
		<jsp:include page="/WEB-INF/pages/staff/ajaxViewMyInboxList.jsp" />
	</div>
	</div>
	<div class="block grid_4">
		<jsp:include page="/WEB-INF/pages/staff/displayRssFeedMessages.jsp" /><br/>
	</div>
</div>
<script type="text/javascript">
	changePageTitle("Manage Student KBank");
    $('#KBank').addClass('current');
  </script>