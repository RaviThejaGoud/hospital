<%@ include file="/common/taglibs.jsp"%>
<jsp:include page="/common/messages.jsp" />
<s:if test="%{roomList!= null && !roomList.isEmpty()}">
	<s:if test='%{anyTitle=="Y"}'>
		<div>
			<jsp:include
				page="/WEB-INF/pages/hostel/ajaxAssignStudentsBeds.jsp" />
		</div>
	</s:if>
	<s:if test='%{anyTitle=="N"}'>
		<div class="alert alert-info">
				There are some issues in configuring Buildings. Please recheck with
				your configuration or call to eazyschool support at (91) 80-46620999 for quick help.
		</div>
	</s:if>
</s:if>
<s:else>
	<div class="alert alert-info">
			Rooms are not created. Please create rooms and try to assign
			students.
	</div>
</s:else>

