<%@ include file="/common/taglibs.jsp"%>
<jsp:include page="/common/messages.jsp"></jsp:include>
<div  id="staffLeaveContent">
<s:if test='%{user.isSchoolHostel == "Y"}'>
<jsp:include page="/WEB-INF/pages/hostel/ajaxHostelStudentLeavesList.jsp" />
</s:if>
<s:else>
	<jsp:include page="/WEB-INF/pages/staff/leaves/staffLeavesList.jsp" />
</s:else>
	
</div>


