<%@ include file="/common/taglibs.jsp"%>

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
	<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12" id="commendationsDiv">
		<div class="dashboard-stat red-inteBlue">
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
	<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12" id="scrapMessagesList">
		<div class="dashboard-stat blue">
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
	<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12" id="leaveManageDiv">
		<div class="dashboard-stat yellow">
			<div class="visual">
				<i class="fa fa-group"></i>
			</div>
			<div class="details">
				<div class="boxTemplate">
					<s:url id="urlParentLeaveLink" action="ajaxDoGetStudentLeaveDetails" namespace="/student" />
					<sj:a href="%{urlParentLeaveLink}" targets="mainContentDiv" cssClass="ajaxify LM">
						Leave Management</sj:a></div>
			</div>
		</div>
	</div>
</div> 
<div class="row" id="myStudentsAttendancediv">
  <div class="col-md-12">
  <div class="col-md-6" style="padding-left: 0px;">
  		<div>
  		<%-- 	<jsp:include page="/WEB-INF/pages/student/studentMarks.jsp" /> --%>
  			<jsp:include page="/WEB-INF/pages/student/studentAssignments.jsp" />
  			
  		</div>
  		<div>
			<jsp:include page="/WEB-INF/pages/student/ajaxStudentAttendancePerformance.jsp"></jsp:include>
		</div>
  </div>
  <div class="col-md-6" style="padding-right: 0px;">
  <s:if test='%{customerByCustId.preferences.visibleFeeInfoToParent=="Y"}'>
    <div class="portlet box blue">
		<div class="portlet-title">
			<div class="caption">
				<i class="fa fa-globe"></i>Upcoming Payments 
			</div>
		</div>
		<div class="portlet-body">
			<div id="performanceContentDiv" class="tab-content">
				<div id="stId">
					<jsp:include page="/WEB-INF/pages/student/parent/ajaxUpcomingPayments.jsp"/>
				</div>
			</div>
		</div>
	</div>
	</s:if>
	<div class="portlet box blue">
		<div class="portlet-title">
			<div class="caption">
				<i class="fa fa-globe"></i>Upcoming Exam Schedules 
			</div>
		</div>
		<div class="portlet-body">
			<div id="upcomingExamSchedulesDiv" class="tab-content">
				<jsp:include page="/WEB-INF/pages/exam/ajaxManageUpcomingExamSchedules.jsp" />
			</div>
		</div>
	</div>
   </div>
  </div>
</div>
 
 
<script type="text/javascript">
	$(document).ready(function() {
		changePageTitle("Parent Dashboard");
	});
	
	$('div#leaveManageDiv').click(function() {
		window.location.hash = "target=ESR.ajaxify LM";
		$('a#studentDashboard').parent('li').removeClass('start active');
		$('a#ESR').parent('li').addClass('start active');
		$('a#leaveLink').click();
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
	$('div#examScheduleDiv').click(function() {
		window.location.hash = "target=ESR.ajaxify LM";
		$('a#studentDashboard').parent('li').removeClass('start active');
		$('a#ESR').parent('li').addClass('start active');
		$('a#studentAcedemics').click();
	});
</script>
