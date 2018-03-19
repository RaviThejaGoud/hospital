<%@ include file="/common/taglibs.jsp"%>
<div class="wrapper container_16">
	<div class="block grid_4">
		<div class="block_head">
			<h2>
				Personal
			</h2>

		</div>
		<div class="block_content" id="sideMenu" >
			<ul>
				<s:if test='%{user.isParent=="Y" && user.isSchoolStudent=="N"}'>
					<li>
						<s:url id="urlStudentAttendanceLink" action="ajaxDoGetMyChildrenAttendance" />
						<sj:a id="studentAttendanceLink" href="%{urlStudentAttendanceLink}" targets="studentContent"
							indicator="indicator">Attendance</sj:a>
					</li>
					<li>
						<s:url id="urlStudentAcademics" action="ajaxStudentExamSchedules" />
						<sj:a id="studentAcedemics" href="%{urlStudentAcademics}"
							targets="studentContent" indicator="indicator">Exam Schedules & Results</sj:a>
					</li>
					<li>
						<s:url id="urlFeeDetails" action="ajaxViewMyRecommendations" />
						<sj:a id="urlFeeDetails1" href="%{urlFeeDetails}"
							targets="studentContent" indicator="indicator">Evaluation Performance</sj:a>
					</li>
					<li>
						<s:url id="urlLeaveLink" action="ajaxDoGetLeaveDetailsForParent" />
						<sj:a id="leaveLink" href="%{urlLeaveLink}"
							targets="studentContent" indicator="indicator">Leave Management</sj:a>
					</li>
				   </s:if>
				    <s:else>
					<li>
						<s:url id="urlStudentAttendanceLink" action="ajaxStudentAttendance" />
						<sj:a id="studentAttendanceLink"
							href="%{urlStudentAttendanceLink}" targets="studentContent"
							indicator="indicator">Attendance</sj:a>
					</li>
					<li class="active">
						<s:url id="urlStudentAcademics" action="ajaxStudentExamSchedules" />
						<sj:a id="studentAcedemics" href="%{urlStudentAcademics}"
							targets="studentContent" indicator="indicator">Exam Schedules & Results</sj:a>
					</li>
					<li>
						<s:url id="urlFeeDetails" action="ajaxViewMyRecommendations" />
						<sj:a id="urlFeeDetails1" href="%{urlFeeDetails}"
							targets="studentContent" indicator="indicator">Evaluation Performance</sj:a>
					</li>
					<li>
						<s:url id="urlLeaveLink" action="ajaxDoGetLeaveDetailsForStudent" />
						<sj:a id="leaveLink" href="%{urlLeaveLink}"
							targets="studentContent" indicator="indicator">Leave Management</sj:a>
					</li>
					<li>
						<s:url id="urlDoRegisterStudentEventLink" action="ajaxStudentEvents" />
						<sj:a id="registerStudentEventLink" href="%{urlDoRegisterStudentEventLink}"
							targets="studentContent" indicator="indicator">Events</sj:a>
					</li>
				</s:else>
			<li>
				<s:url id="urlClassMatesLink" action="ajaxGetClassMatesList" />
				<sj:a id="classMatesLink" href="%{urlClassMatesLink}"
					targets="studentContent" indicator="indicator">Classmates</sj:a>
			</li>
			<li>
			<s:url id="urlTransportLink" action="ajaxLoadTransportByStatus" />
			<sj:a id="transportLink" href="%{urlTransportLink}"
				targets="studentContent" indicator="indicator">Transport</sj:a>
			</li>
			<s:if test='%{customerByCustId.preferences.visibleFeeInfoToParent=="Y"}'>
				<s:if test='%{user.isParent=="N" && user.isSchoolStudent=="Y"}'>
				<li>
					<s:url id="urlDoGetMyFeeDetailsLink" action="ajaxDoGetMyFeeDetails" />
					<sj:a id="doGetMyFeeDetailsLink" href="%{urlDoGetMyFeeDetailsLink}"
						targets="studentContent" indicator="indicator">Payment & Receipts</sj:a>
				</li>
				</s:if>
				<s:else>
					<li  class="active">
					<s:url id="urlDoGetChildsFeesLink" action="ajaxGetMyChildFees" />
					<sj:a id="doGetChildsDetailsLink" href="%{urlDoGetChildsFeesLink}"
						targets="studentContent" indicator="indicator">Upcoming Payments</sj:a>
				</li>
				</s:else>
			 </s:if>
			</ul>
		</div>
	</div>
	<div id="studentContent">
		<jsp:include page="/WEB-INF/pages/student/parent/ajaxStudentFeeDetails.jsp"></jsp:include>
	</div>
</div>
<script Language="Javascript1.2" type="text/javascript">
	$(document).ready(function() {
	changePageTitle("Student Activities");
		$('#studentActivities').addClass('current');
	});
</script>