<%@ include file="/common/taglibs.jsp"%>
<style type="text/css">
@import	url("${pageContext.request.contextPath}/styles/calendar/dhtmlxscheduler.css");
@import	url("${pageContext.request.contextPath}/styles/calendar/dhtmlxscheduler_recurring.css");
@import	url("${pageContext.request.contextPath}/styles/calendar/dhtmlxscheduler_ext.css");
</style>
<script src="${pageContext.request.contextPath}/scripts/default/jQuery.url.js" type="text/javascript"></script>
<script type='text/javascript' src='${pageContext.request.contextPath}/scripts/calendar/dhtmlxscheduler.js'></script>
<script type='text/javascript' src='${pageContext.request.contextPath}/scripts/calendar/urtcalendar.js'></script>
<div class="grid_14">
	<div id="stepCalendar" class="commomnTabs">
		<div class="grid_14 omega">
			<s:url id="doAddEvent" action="ajaxViewWeekdayHostelCalendar"
				includeParams="all" escapeAmp="false" namespace="/hostel">
				<s:param name="type" value="%{'Today'}" />
			</s:url>
			<sj:a href="%{doAddEvent}" indicator="indicator"
				targets="stepCalendar" button="false" cssClass="linkRight">View All Events</sj:a>

			<s:url id="doAddHostelEvent" action="ajaxDoAddHostelEvent"
				includeParams="all" escapeAmp="false" namespace="/hostel">
				<s:param name="tempId" value="0" />
			</s:url>
			<sj:a href="%{doAddHostelEvent}" indicator="indicator"
				targets="stepEvents" button="false" cssClass="linkRight">Add Events&nbsp;|&nbsp;</sj:a>
		</div>
		<div class="grid_14">
			<div id="scheduler_here" class="dhx_cal_container">
				<div class="dhx_cal_navline">
					<%--<div class="dhx_cal_tab" name="day_tab" style="position: relative;z-index:1000;float: right;"></div>
					<div class="dhx_cal_tab" name="week_tab" style="position: relative;z-index:1000;float: right;"></div>--%>
					<div class="dhx_cal_tab" name="month_tab" style="position: relative;z-index:1000;float: right;"></div>
					<div class="dhx_cal_prev_button">&nbsp;</div>
					<div class="dhx_cal_next_button">&nbsp;</div>
					<div class="dhx_cal_today_button" style="float: left;"></div>
					<div class="dhx_cal_date" ></div>
				</div>
				<div class="dhx_cal_header">
				</div>
				<div class="dhx_cal_data" style="height: auto;">
				</div>
			</div>
		</div>
	</div>
</div>
 
<script Language="Javascript1.2" type="text/javascript">
	$(document).ready( function() {
		calendarAdminInit();
		 //$('#staffCalendar').addClass('current');
		 changePageTitle("Hostel Calendar");
		 //$('#staffHolidayCalendar').addClass('current');
	});
 </script>
 