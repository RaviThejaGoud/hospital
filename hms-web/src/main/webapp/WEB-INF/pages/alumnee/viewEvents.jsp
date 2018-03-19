<%@ include file="/common/taglibs.jsp"%>
<s:if test="%{objectList != null && !objectList.isEmpty()}">
	<jsp:include page="/WEB-INF/pages/alumnee/ajaxViewAllSocialFriendsStatus.jsp"></jsp:include>
</s:if>
<s:else>
  <div class="alert alert-info">Currently there are no events.</div>
 </s:else>
	
