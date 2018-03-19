<%@ include file="/common/taglibs.jsp"%>
		<script type='text/javascript' src='${pageContext.request.contextPath}/scripts/calendar/dhtmlxscheduler.js'></script>
	    <script type='text/javascript' src='${pageContext.request.contextPath}/scripts/calendar/dhtmlxscheduler_recurring.js'></script>
	    <script type='text/javascript' src='${pageContext.request.contextPath}/scripts/calendar/dhtmlxscheduler_year_view.js'></script>
		<script type='text/javascript' src='${pageContext.request.contextPath}/scripts/calendar/dhtmlxscheduler_units.js'></script>
<script Language="Javascript1.2" type="text/javascript">
        $(document).ready(function() {	     
	     $.ajax( {
	url : jQuery.url.getChatURL("/calendar/ajaxCategory.do"),
	cache : false,
	dataType : 'json',
	success : function(response) {
		var categories = response.categories;			
		if (categories.length >= 1) {
			for ( var i = 0; i < categories.length; i++) {
				catObj={key:categories[i].id, label:categories[i].name};
				sections.push(catObj);
			}				
		}		
		var statesList = response.statesLt;
		if (statesList.length >= 1) {
			for ( var i = 1; i <= statesList.length; i++) {					
				states[i]={key:statesList[i-1].stateCode, label:statesList[i-1].stateName};
			}				
		}
		calendarInit();
	}
});
	});
</script>
<div id="eventsHome"></div>
	<div id="scheduleCalendar"><div  ><a style="cursor: pointer" onclick="javascript:onchangeOptions();">MANAGE EVENTS</a></div>
	<div id="scheduler_here" class="dhx_cal_container"  >
		<div class="dhx_cal_navline">
			<div class="dhx_cal_prev_button">&nbsp;</div>
			<div class="dhx_cal_next_button">&nbsp;</div>
			<div class="dhx_cal_tab" style="right:386px; top:0;" ><a href="${pageContext.request.contextPath}/calendar/ajaxICal.do?custid=<s:property value="userCustId"/>" ><strong>ICal</strong></a></div>
			<div class="dhx_cal_today_button"></div>
			<div class="dhx_cal_date"></div>
			<div class="dhx_cal_tab" name="day_tab" style="right:260px; top:0;"></div>
			<div class="dhx_cal_tab" name="week_tab" style="right:196px; top:0;"></div>
			<div class="dhx_cal_tab" name="year_tab" style="right:70px; top:0;"></div>
			<div class="dhx_cal_tab" name="month_tab" style="right:133px; top:0;"></div>
			<div class="dhx_cal_tab" name="unit_tab" style="right:323px; top:0;"></div>
			
		</div>
		<div class="dhx_cal_header">
		</div>
		<div class="dhx_cal_data" style="height: auto;">
		</div>		
	</div>
	</div>
	
<script type="text/javascript">
	function onchangeOptions() {
	var eventDetailsURL = jQuery.url.getChatURL("/calendar/ajaxEventDashboard.do");
			$.ajax( {
				url : eventDetailsURL,
				cache : false,
				success : function(message) {
					$("#scheduleCalendar").html(message);
				}
			});
		}
</script>