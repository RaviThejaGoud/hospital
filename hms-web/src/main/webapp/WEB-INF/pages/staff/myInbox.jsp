<%@ include file="/common/taglibs.jsp"%>
<div class="grid_3 alpha">
	<!--<div>
<jsp:include page="/WEB-INF/pages/student/eventCalendar.jsp" />
</div>
-->
	<div style="width: 250px; margin-top: 0px;">
		<div class="block_head">
			<h2>
				My Inbox
			</h2>
			<div id="topMenu">
				<ul>
					
				</ul>
			</div>
		</div>
		<div class="block_content" id="sideMenu" style="padding: 10px;">
			<s:if test="%{messagesList != null && !messagesList.isEmpty()}">
				<div style="padding-top: 1px">
					<s:url id="doViewPostMessages" action="ajaxDoGetMyMessages"
						includeParams="all" escapeAmp="false">
					</s:url>
					<sj:a href="%{doViewPostMessages}" indicator="indicator"
						targets="studentContent">
						<s:if test="%{unReadMsgsList.size== 0}">	
				   Inbox
				   </s:if>
						<s:else>	 
				  You have
				  <s:property value="unReadMsgsList.size" />
				   New Messages
				   </s:else>
					</sj:a>
				</div>
			</s:if>
			<s:else>
			Your InBox is Empty.
   </s:else>
		</div>
	</div>
</div>