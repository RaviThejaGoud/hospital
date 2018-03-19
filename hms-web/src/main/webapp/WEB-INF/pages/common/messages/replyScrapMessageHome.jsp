<%@ include file="/common/taglibs.jsp"%>
	<div id="applyLeave">
		<s:if test="{viewAllUsers != NULL}">
			<jsp:include
				page="/WEB-INF/pages/common/messages/replySendScrapMessage.jsp" />
		</s:if>
		<s:else>
			<jsp:include
				page="/WEB-INF/pages/common/messages/replyScrapMessage.jsp" />
		</s:else>
	</div>
<script type="text/javascript">
changePageTitle("Reply Message");
</script>