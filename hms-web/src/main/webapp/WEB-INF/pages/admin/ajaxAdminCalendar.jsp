<%@ include file="/common/taglibs.jsp"%>
<div class="row">
<div class="col-md-12">
	<div class="portlet box blue calendar">
		<div class="portlet-title">
			<div class="caption">
				<i class="fa fa-reorder"></i>Calendar
			</div>
		</div>
		<div class="portlet-body light-grey">
			<div class="row">
				<div class="col-md-3 col-sm-12">
					<h3 class="event-form-title">Draggable Events</h3>
					<div id="external-events">
						<form class="inline-form">
							<input type="text" value="" class="form-control" placeholder="Event Title..." id="event_title"/><br/>
							<a href="javascript:;" id="event_add" class="btn green">Add Event</a>
						</form>
						<hr/>
						<div id="event_box">
						</div>
						<label for="drop-remove">
						<input type="checkbox" id="drop-remove"/>remove after drop </label>
						<hr class="visible-xs"/>
					</div>
				</div>
				<div class="col-md-9 col-sm-9">
					<div id="calendar" class="has-toolbar">
					</div>
				</div>
			</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	jQuery(document).ready(function() {       
	   Calendar.init();
	   changePageTitle("Calendar");
	   $("input:checkbox, input:radio:not('.toggle')").uniform(); 
	});
</script>
<!--<style type="text/css">
@import	url("${pageContext.request.contextPath}/styles/calendar/dhtmlxscheduler.css");
@import	url("${pageContext.request.contextPath}/styles/calendar/dhtmlxscheduler_recurring.css");
@import	url("${pageContext.request.contextPath}/styles/calendar/dhtmlxscheduler_ext.css");
</style>
<script src="${pageContext.request.contextPath}/scripts/default/jQuery.url.js" type="text/javascript"></script>
<script type='text/javascript' src='${pageContext.request.contextPath}/scripts/calendar/dhtmlxscheduler.js'></script>
<script type='text/javascript' src='${pageContext.request.contextPath}/scripts/calendar/dhtmlxscheduler_recurring.js'></script>
<script type='text/javascript' src='${pageContext.request.contextPath}/scripts/calendar/urtcalendar.js'></script>
<script type='text/javascript' src='${pageContext.request.contextPath}/scripts/calendar/dhtmlxscheduler_year_view.js'></script>
<div class="grid_14">
	<div id="stepCalendar" class="commomnTabs">
				<div class="grid_14 omega">
					<s:url id="doAddEvent" action="ajaxViewWeekdayCalendar" includeParams="all"></s:url>
					<sj:a href="%{doAddEvent}" indicator="indicator" targets="stepCalendar"
						button="false" cssClass="linkRight">Week</sj:a>
				</div>
		<div class="grid_14">
			<div id="scheduler_here" class="dhx_cal_container">
				<div class="dhx_cal_navline">
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
          $(document).ready(function() {
          		changePageTitle("Calendar");
			     $('#staffHolidayCalendar').addClass('current');
			     calendarAdminInit();
			});
</script>
-->