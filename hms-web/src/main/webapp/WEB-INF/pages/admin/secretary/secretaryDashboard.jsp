<%@ include file="/common/taglibs.jsp"%> 
<div class="row">
	 <div class="col-lg-3 col-md-3  col-xs-12" id="upcommingMetingsDiv">
		<div class="dashboard-stat green">
			<div class="visual">
				<i class="fa fa-calendar"></i>
			</div>
			<div class="details">
				<div class="boxTemplate">	
					<s:url id="upcommingMetings" action="ajaxUpcommingMetingsDasboard" namespace="/admin" />
					<sj:a href="%{upcommingMetings}" targets="mainContentDiv" cssClass="ajaxify PUMD"> Upcoming Meetings</sj:a> : <s:property value="objectList.size"/></div>
			</div>
		</div>
	</div>
	 <div class="col-lg-3 col-md-3  col-xs-12" id="budgetRequestDiv">
		<div class="dashboard-stat purple">
			<div class="visual">
				<i class="fa fa-sitemap"></i>
			</div>
			<div class="details">
				<div class="boxTemplate">
					<s:url id="budgetRequest" action="ajaxBudgetRequestDashboard" namespace="/admin" />
					<sj:a  href="%{budgetRequest}" targets="mainContentDiv" cssClass="ajaxify PABRD">Budget Accepts/Rejects</sj:a>:<s:property value="tempList.size"/>/<s:property value="tempList2.size"/></div>
			</div>
		</div>
	</div>
	<div class="col-lg-3 col-md-3  col-xs-12" id="requestsBudgetDiv">
		<div class="dashboard-stat red-intense">
			<div class="visual">
				<i class="fa fa-money"></i>
			</div>
			<div class="details">
				<div class="boxTemplate">
					<s:url id="requestsBudget" action="ajaxDoBudgetRequestDashboard" namespace="/admin"></s:url>
					<sj:a href="%{requestsBudget}" targets="mainContentDiv" cssClass='ajaxify PBRD'>Budget Requests</sj:a> : <s:property value="tempList1.size"/>
				</div>
			</div>
		</div>
	</div>
	<div class="col-lg-3 col-md-3  col-xs-12" id="meetingMinuDiv">
		<div class="dashboard-stat blue">
			<div class="visual">
				<i class="fa fa-group"></i>
			</div>
			<div class="details">
				<div class="boxTemplate">
					<s:url id="meetingMinu" action="ajaxDoGetMeetingMinutesDashboard" namespace="/admin" />
					<sj:a href="%{meetingMinu}" targets="mainContentDiv" cssClass="ajaxify PMMD"> Meeting Minutes</sj:a>  : <s:property value="objectList.size"/> </div>
			</div>
		</div>
	</div>
</div> 
<div class="row">
  <div class="col-md-12">
	<div class="col-md-6" style="padding-left: 0px;">
		<jsp:include page="/WEB-INF/pages/admin/secretary/ajaxMeetingMinutesDashboard.jsp" />
		<jsp:include page="/WEB-INF/pages/admin/secretary/ajaxViewMeetingDashboard.jsp" />
		<jsp:include page="/WEB-INF/pages/admin/secretary/ajaxViewBudgetRequestDashboard.jsp" />
	</div>
	<div class="col-md-6" style="padding-right: 0px;">
		  <jsp:include page="/WEB-INF/pages/admin/secretary/ajaxBudgetAcceptsRejectsDashboard.jsp" />
		  <jsp:include page="/WEB-INF/pages/admin/secretary/ajaxYearBudgetGraphDashboard.jsp" />
	  </div>
	</div>
</div>
<script type="text/javascript">
	$(document).ready(function() {
		changePageTitle("Secretary Dashboard");
		TableAdvanced.init();
	});
$('div#upcommingMetingsDiv').click(function() {
	window.location.hash = "target=SMD.ajaxify PUMD";
	$('a#secretary').parent('li').removeClass('start active');
	$('a#SMD').parent('li').addClass('start active');
	$('a#urlupcommingMetingsDiv').click();
});

$('div#budgetRequestDiv').click(function() {
	window.location.hash = "target=SMD.ajaxify PABRD";
	$('a#secretary').parent('li').removeClass('start active');
	$('a#SMD').parent('li').addClass('start active');
	$('a#urlbudgetRequestDiv').click();
});

$('div#requestsBudgetDiv').click(function() {
	window.location.hash = "target=SMD.ajaxify PBRD";
	$('a#secretary').parent('li').removeClass('start active');
	$('a#SMD').parent('li').addClass('start active');
	$('a#urlrequestsBudgetDiv').click();
});
$('div#meetingMinuDiv').click(function() {
	window.location.hash = "target=SMD.ajaxify PMMD";
	$('a#secretary').parent('li').removeClass('start active');
	$('a#SMD').parent('li').addClass('start active');
	$('a#urlmeetingMinuDiv').click();
});
</script>
