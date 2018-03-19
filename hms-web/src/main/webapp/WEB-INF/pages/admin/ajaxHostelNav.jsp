<%@ include file="/common/taglibs.jsp"%>
<div class="grid_14 commomnTabs">
	<jsp:include page="/common/messages.jsp" />
	<div class="grid_14 omega">
		<s:url id="doAddHostel" action="ajaxDoAddHostelDetails"
			includeParams="all" escapeAmp="false" namespace="/admin">
			<s:param name="tempId" value="0" />
		</s:url>
		<sj:a href="%{doAddHostel}" indicator="indicator"
			targets="stepSchoolDetails" button="false" cssClass="linkRight">Add Hostel Details</sj:a>
		Currently there are no Hostel Details.
	</div>
</div>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>