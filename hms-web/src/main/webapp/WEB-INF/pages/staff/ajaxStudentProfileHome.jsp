<%@ include file="/common/taglibs.jsp"%>
<div>
	<s:if
		test="%{viewStudentPersonAccountDetailsList != null && !viewStudentPersonAccountDetailsList.isEmpty()}">
		<jsp:include page="/WEB-INF/pages/staff/viewMyStudentsLists.jsp"></jsp:include>
	</s:if>
	<%-- <s:elseif test="%{viewStudentPersonAccountDetails != null}">
		<jsp:include page="/WEB-INF/pages/staff/ajaxStudentProfile.jsp"></jsp:include>
	</s:elseif> --%>
</div>
<!--<div class="grid_12 omega block">
	<div class="block_head">
		<h2>
			Student Profile
		</h2>
	</div>
	<div class="block_content" id="studentsProfileHome"
		style="padding: 0px">
		<div id="steps13">
			<s:if
				test="%{viewStudentPersonAccountDetailsList != null && !viewStudentPersonAccountDetailsList.isEmpty()}">
				<jsp:include page="/WEB-INF/pages/staff/viewMyStudentsLists.jsp"></jsp:include>
			</s:if>
			<s:elseif test="%{viewStudentPersonAccountDetails != null}">
				<jsp:include page="/WEB-INF/pages/staff/ajaxStudentProfile.jsp"></jsp:include>
			</s:elseif>
			<s:else>
			Currently there are no Students.
		</s:else>
		</div>
	</div>
</div>-->

