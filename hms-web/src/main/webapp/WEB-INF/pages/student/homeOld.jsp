<%@ include file="/common/taglibs.jsp"%>
<div class="block grid_8 omega">
	<div id="stId" class="block grid_4 omega">
		<jsp:include
			page="/WEB-INF/pages/student/parent/studentExamDetails.jsp" />
	</div>

<div class="block grid_4 omega">
	<div id="stId">
		<jsp:include
			page="/WEB-INF/pages/student/parent/studentMarksDetails.jsp" />
	</div>
</div>
</div>
<div class="block grid_8 omega">
	<div id="stId" class="block grid_4 omega" style="margin-left: -20px;">
		<jsp:include
			page="/WEB-INF/pages/student/parent/myMessagesCount.jsp" />
	</div>
<div class="block grid_4 omega" style="margin-left: 40px;">
	<div id="stId">
		<s:if test='%{user.isParent=="Y" && user.isSchoolStudent=="N"}'>
			<jsp:include page="parent/myMessagesCount.jsp"></jsp:include>
		</s:if>
	</div>
</div>
</div>
<div class="grid_8 alpha" style="margin-left: 5px;margin-top: -49px;">
		<!--<div id="stId">
			<jsp:include
				page="/WEB-INF/pages/student/parent/ajaxStudentFeeDetails.jsp" />
		</div>
	--></div>	