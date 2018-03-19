<%@ include file="/common/taglibs.jsp"%>

<s:if test="%{tempString != null}">
	<s:property value="tempString" escapeHtml="false"/>
</s:if>
<s:else>
	<jsp:include page="/WEB-INF/pages/admin/transport/ajaxDriversByRouteIdVehicleId.jsp"/>
</s:else>


