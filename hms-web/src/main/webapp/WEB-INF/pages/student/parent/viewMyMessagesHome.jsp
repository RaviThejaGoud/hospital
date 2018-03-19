<%@ include file="/common/taglibs.jsp"%>
<div class="grid_12 omega block">
	<div class="block_head">
		<h2>
			My Messages
		</h2>
		<div id="topMenu">
			<ul>
				<!--<li>
					<s:url id="urlMyMessagesLink" action="ajaxDoSelectMyChildren" />
					<sj:a href="%{urlMyMessagesLink}" targets="myMessagesContent"
						indicator="indicator">Send Messages</sj:a>
				</li>
				<div class="block_content" id="myMessagesContent" style="padding: 0px">
		<jsp:include page="viewMyMessagesChildAbsentLeavesList.jsp"></jsp:include>
	</div>

				<li>
					<s:url id="urlLeaveLink" action="ajaxDoGetTeacherMessagesForParent" />
					<sj:a href="%{urlLeaveLink}" targets="myMessagesContent"
						indicator="indicator">Teacher Messages</sj:a>
				</li>-->
			</ul>
		</div>
	</div>
	
<div class="block_content" id="myMessagesContent" style="padding: 0px">
		<jsp:include page="viewTeacherMessagesList.jsp"></jsp:include>
	</div>
</div>