<%@ include file="/common/taglibs.jsp"%>
<div class="grid_12 omega block">
	<div class="block_head">
		<h2>
			My Profile
		</h2>
		<div id="topMenu">
			<!--<ul>
				<li>
					<s:url id="urlMyMessagesLink" action="ajaxDoSelectMyChildren" />
					<sj:a href="%{urlMyMessagesLink}" targets="myMessagesContent"
						indicator="indicator">Send Messages</sj:a>
				</li>
				<li>
					<s:url id="urlLeaveLink" action="ajaxDoGetTeacherMessagesForParent" />
					<sj:a href="%{urlLeaveLink}" targets="myMessagesContent"
						indicator="indicator">Teacher Messages</sj:a>
				</li>
			</ul>
		-->
		</div>
	</div>
	<div class="block_content" id="studentsProfileHome"
		style="padding: 20px">
		<s:if
			test="%{viewStudentPersonAccountDetailsList != null && !viewStudentPersonAccountDetailsList.isEmpty()}">
			<jsp:include page="/WEB-INF/pages/student/selectOneStudent.jsp"></jsp:include>
		</s:if>
		<s:elseif test="%{viewStudentPersonAccountDetails != null}">
			<jsp:include page="/WEB-INF/pages/student/ajaxStudentProfile.jsp"></jsp:include>
		</s:elseif>
		<s:else>
			Currently there are no Students.
		</s:else>

	</div>
</div>