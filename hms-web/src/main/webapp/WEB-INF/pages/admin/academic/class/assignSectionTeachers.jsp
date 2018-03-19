<%@ include file="/common/taglibs.jsp"%>
<div>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/ems.js"></script>
	<%@ include file="/common/messages.jsp"%>
	<s:form action="ajaxAddStaffSubjects" theme="simple" id="addNewClassSubject" cssClass="form-horizontal" namespace="/admin">
		<input type="hidden" name="classId"
			value="<s:property value="classId"/>">
			<s:hidden name="tempString" cssClass='tempString' />
			<s:hidden name="classSectionId" value="%{classSectionId}" />
				<jsp:include page="/WEB-INF/pages/admin/academic/class/ajaxViewClassSubjects.jsp"></jsp:include>
	</s:form>
</div>
<script>
$('html, body').animate({ scrollTop: $(document).height() - $(window).height() }, 10, function() {
    $(this).animate({ scrollTop: 0 }, 10);
});
</script>