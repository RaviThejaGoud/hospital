<%@ include file="/common/taglibs.jsp"%>


<%-- <s:if test="%{user.parent}">
	<a id="showVisionMission" class="response" href="#responsiveVision" data-toggle="modal"> <jsp:include page="/WEB-INF/pages/student/ajaxViewStudentLatestMarks.jsp" />
</s:if> --%>

<%-- <s:if test='%{paymentType == "A"}'>
	<a id="showVisionMission" class="response" href="#responsiveVision" data-toggle="modal"> <jsp:include page="/WEB-INF/pages/student/ajaxViewStudentLatestMarks.jsp" />
		</a>
</s:if> --%>
	
			
			
<div class="row">
    <div class="col-lg-3 col-md-3 col-sm-6 col-xs-12" id="examScheduleDiv">
		<div class="dashboard-stat green">
			<div class="visual">
				<i class="fa fa-trophy"></i>
			</div>
			<div class="details">
				<div class="boxTemplate">
					<s:url id="urlStudentAcademics" action="ajaxManageStudentExamSchedulesAndResults" namespace="/exam" />
					<sj:a  href="%{urlStudentAcademics}" targets="mainContentDiv" cssClass="ajaxify ESR"> Exam Schedules & Results</sj:a></div>
			</div>
		</div>
	</div>
	 <div class="col-lg-3 col-md-3 col-sm-6 col-xs-12" id="scrapMessagesList">
		<div class="dashboard-stat red-inteBlue">
			<div class="visual">
				<i class="fa fa-envelope"></i>
			</div>
			<div class="details">
				<div class="boxTemplate">
					<s:url id="urlInboxesDetails" action="ajaxDoInboxGetScrapMessagesList" namespace="/common"></s:url>
					<sj:a href="%{urlInboxesDetails}" targets="mainContentDiv" cssClass='ajaxify'>Inbox</sj:a>
				</div>
			</div>
		</div>
	</div>
	 <div class="col-lg-3 col-md-3 col-sm-6 col-xs-12" id="commendationsDiv">
		<div class="dashboard-stat mediumPurple">
			<div class="visual">
				<i class="fa fa-signal"></i>
			</div>
			<div class="details">
				<div class="boxTemplate">	
					<s:url id="urlFeeDetails" action="ajaxViewMyRecommendations" namespace="/student" />
					<sj:a href="%{urlFeeDetails}" targets="mainContentDiv" cssClass="ajaxify PE"> Performance Evaluation</sj:a></div>
			</div>
		</div>
	</div>
	<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12" id="ClassMatesListDiv">
		<div class="dashboard-stat purple">
			<div class="visual">
				<i class="fa fa-user"></i>
			</div>
			<div class="details">
				<div class="boxTemplate">
				   <s:url id="urlclassMatesLink" action="ajaxGetClassMatesList" namespace="/student" />
				   <sj:a  href="%{urlclassMatesLink}" targets="mainContentDiv" cssClass="ajaxify CL"> Classmates</sj:a>
				</div>
			</div>
		</div>
	</div>
</div> 
<div class="row">
	<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12" id="LibrayHomeDiv">
		<div class="dashboard-stat red more">
			<div class="visual">
				<i class="fa fa-book"></i>
			</div>
			<div class="details">
				<div class="boxTemplate">	
				 <a href="${pageContext.request.contextPath}/library/getStudentLibrayHome.do?id=sLibrary"
								id="sLibrary">Library<span class="selected"></span> </a>
				</div>
			</div>
		</div>
	</div>
	<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12" id="timeTableDiv">
		<div class="dashboard-stat blueViolet">
			<div class="visual">
				<i class="fa fa-clock-o"></i>
			</div>
			<div class="details">
				<div class="boxTemplate">
				<s:url id="urlViewStudTimeTable" action="ajaxViewStudentTimeTable" namespace="/student" />
				<sj:a href="%{urlViewStudTimeTable}" targets="mainContentDiv" cssClass="ajaxify TT"> Timetable</sj:a></div>
			</div>
		</div>
	</div>
	<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12" id="leaveManageDiv">
		<div class="dashboard-stat blue">
			<div class="visual">
				<i class="fa fa-group"></i>
			</div>
			<div class="details">
				<div class="boxTemplate">
					<s:url id="urlStudentLeaveLink" action="ajaxDoGetLeaveDetailsForStudent" namespace="/student" />
					<sj:a  href="%{urlStudentLeaveLink}" targets="mainContentDiv" cssClass="ajaxify LM"> Leave Management</sj:a>
			</div>
		</div>
	  </div>
	</div>
	<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12" id="ClassesAndTodyDateDiv">
		<div class="dashboard-stat light-green">
			<div class="visual">
				<i class="fa fa-bar-chart-o"></i>
			</div>
			<div class="details">
				<div class="boxTemplate">
					<s:url id="dailyAttendance" action="ajaxDoViewClassesAndTodyDate" escapeAmp="false" namespace="/admin" includeParams="all">
						<s:param name="tempString">Student</s:param>
						<s:param name="plTitle">Daily Attendance</s:param>
					</s:url>
					<sj:a href="%{dailyAttendance}" targets="mainContentDiv" cssClass='ajaxify DA'>Attendance Report</sj:a>
				</div>
			</div>
		</div>
	</div>
</div>
<div class="row">
  <div class="col-md-12">
	<div class="col-md-6" style="padding-left: 0px;">
	
  		
		<%--  <div>
  			<jsp:include page="/WEB-INF/pages/student/studentLatestMarks.jsp" />
  		</div> --%>
  		<div>
  			<jsp:include page="/WEB-INF/pages/student/studentMarks.jsp" />
  		</div>
  		<div>
			<jsp:include page="/WEB-INF/pages/student/ajaxStudentAttendancePerformance.jsp"></jsp:include>
		</div>
     </div>
	<div class="col-md-6" style="padding-right: 0px;">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Alerts
				</div>
			</div>
			<div class="portlet-body">
				<div id="stId" class="tab-content">
					<jsp:include page="/WEB-INF/pages/student/studentQuiz.jsp"></jsp:include>
				</div>
			</div>
		</div>
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Upcoming Exam Schedules
				</div>
			</div>
			<div class="portlet-body">
				<div class="tab-content">
					<jsp:include
						page="/WEB-INF/pages/exam/ajaxManageUpcomingExamSchedules.jsp" />
				</div>
			</div>
		</div>
	</div>
  </div>
</div>

<script type="text/javascript">
	$(document).ready(function() {
		changePageTitle("Student Dashboard");
	});
	$('div#leaveManageDiv').click(function() {
		window.location.hash = "target=ESR.ajaxify LM";
		$('a#studentDashboard').parent('li').removeClass('start active');
		$('a#ESR').parent('li').addClass('start active');
		$('a#leaveLink').click();
	});
	
	$('div#StudentAttendanceDiv').click(function() {
		window.location.hash = "target=ESR.ajaxify AC";
		$('a#studentDashboard').parent('li').removeClass('start active');
		$('a#ESR').parent('li').addClass('start active');
		$('a#studentAttendanceLink').click();
	});
	$('div#scrapMessagesList').click(function() {
		window.location.hash = "target=HASE.ajaxify";
		$('a#studentDashboard').parent('li').removeClass('start active');
		$('a#HASE').parent('li').addClass('start active');
		$('a#inboxLink').click();
	});
	
	$('div#commendationsDiv').click(function() {
		window.location.hash = "target=ESR.ajaxify LM";
		$('a#studentDashboard').parent('li').removeClass('start active');
		$('a#ESR').parent('li').addClass('start active');
		$('a#urlPeDetail').click();
	});
	$('div#timeTableDiv').click(function() {
		window.location.hash = "target=ESR.ajaxify TT";
		$('a#studentDashboard').parent('li').removeClass('start active');
		$('a#ESR').parent('li').addClass('start active');
		$('a#timeTableUrlLink').click();
	});
	$('div#examScheduleDiv').click(function() {
		window.location.hash = "target=ESR.ajaxify LM";
		$('a#studentDashboard').parent('li').removeClass('start active');
		$('a#ESR').parent('li').addClass('start active');
		$('a#studentAcedemics').click();
	});
	$('div#ClassesAndTodyDateDiv').click(function() {
		window.location.hash = "target=SID.ajaxify DA";
		$('a#studentDashboard').parent('li').removeClass('start active');
		$('a#GR').parent('li').addClass('start active');
		$('a#atteReport').click();
	});
	$('div#ClassMatesListDiv').click(function() {
		window.location.hash = "target=ESR.ajaxify CL";
		$('a#studentDashboard').parent('li').removeClass('start active');
		$('a#ESR').parent('li').addClass('start active');
		$('a#classMatesLink').click();
	});
	$('div#LibrayHomeDiv').click(function() {
		var url = jQuery.url.getChatURL("/library/getStudentLibrayHome.do?id=sLibrary");
		window.location.href = url;
	});
</script>