<%@ include file="/common/taglibs.jsp"%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/default/calendar.css" />
<script type="text/javascript"
			src="${pageContext.request.contextPath}/scripts/calendar/calendar.js"></script>
<div class="grid_4 alpha">
	<div id="sideMenu" >
		<span style="vertical-align:top;">
		<div style="width:211px;padding:5px;padding: 0px; height: 214px; background-color:#A4A4A4;" class="cal_labelcell">
				<input type="hidden" name="tester2" id="tester2" value=""/>				
				<select name="tester2_day" id="tester2_day"  >
					<option value="1">1</option>
					<option value="2">2</option>
					<option value="3">3</option>
					<option value="4">4</option>
					<option value="5">5</option>
					<option value="6">6</option>
					<option value="7">7</option>
					<option value="8">8</option>
					<option value="9">9</option>
					<option value="10">10</option>
					<option value="11">11</option>
					<option value="12">12</option>
					<option value="13">13</option>
					<option value="14">14</option>
					<option value="15">15</option>
					<option value="16">16</option>
					<option value="17">17</option>
					<option value="18">18</option>
					<option value="19">19</option>
					<option value="20">20</option>
					<option value="21">21</option>
					<option value="22">22</option>
					<option value="23">23</option>
					<option value="24">24</option>
					<option value="25">25</option>
					<option value="26">26</option>
					<option value="27">27</option>
					<option value="28">28</option>
					<option value="29">29</option>
					<option value="30">30</option>
					<option value="31">31</option>
				</select>
				
				<select name="tester2_month" id="tester2_month"style="width:90px;">
					<option value="1">January</option>
					<option value="2">February</option>
					<option value="3">March</option>
					<option value="4">April</option>
					<option value="5">May</option>
					<option value="6">June</option>
					<option value="7">July</option>
					<option value="8">August</option>
					<option value="9">September</option>
					<option value="10">October</option>
					<option value="11">November</option>
					<option value="12">December</option>
				</select>
				<select name="tester2_year" id="tester2_year" >
					<option value="2000" >2000</option>
					<option value="2001">2001</option>
					<option value="2002">2002</option>
					<option value="2003">2003</option>
					<option value="2004">2004</option>
					<option value="2005">2005</option>
					<option value="2006">2006</option>
					<option value="2007">2007</option>
					<option value="2008">2008</option>
					<option value="2009">2009</option>
					<option value="2010">2010</option>
					<option value="2011">2011</option>
					<option value="2012">2012</option>
					<option value="2013">2013</option>
					<option value="2014">2014</option>
					<option value="2015">2015</option>
					<option value="2016">2016</option>
					<option value="2017">2017</option>
					<option value="2018">2018</option>
					<option value="2019">2019</option>
					<option value="2020">2020</option>
					
				</select>
				
				<div id="cal_tester2_display"></div>
			</div>
	</span>

<style type="text/css">
.active {
	color: #0033CC;
	text-decoration: none;
}

.inactive {
	font-weight: bold;
	text-decoration: underline;
	cursor: default;
}

.paginator {
	text-align: center;
	color: #FFF;
}
</style> 
<script type="text/javascript"
				src="${pageContext.request.contextPath}/scripts/group/groupPaginator_dev.js"></script>
<script type="text/javascript">
$(function(){ $("#resultsPage").pagination(); });

changePageTitle('Staff Events');
	$.subscribe('doRegisterEvent', function(event, data) {
		if ($('#' + data.id).is(":hidden")) {
			$('#' + data.id).show();
		} else {
			$('#' + data.id).hide();
		}
	});
	$(document).ready(function() {		
	    $.ajax( {
			url : jQuery.url.getChatURL("/staff/ajaxGetStaffEventDates.do"),
			cache : false,
			dataType : 'json',
			success : function(response) {
				var staffEvents=[];
				var eventDates=[];
				staffEvents = response.eventDatesSet;	
				cal2 = new Calendar ("cal2", "tester2", new Date());
				cal2.scrolling = false;
				if (staffEvents.length >= 1) {
				    var eventlength=staffEvents.length;
				    var eventDateArray=new Array();
					for ( var i = 0; i < eventlength; i++) {
					   var pdate = new Date(staffEvents[i]);
					   var stringdate=pdate.getFullYear()+"/"+(pdate.getMonth()+1)+"/"+pdate.getDate();
					   eventDateArray[i]= new Array(stringdate,stringdate);
					}	
					cal2.eventDates = eventDateArray;
					cal2.selectEvent = function(eventDate) {
						 $.ajax( {
							url : jQuery.url.getChatURL("/staff/ajaxGetStaffEventDetails.do?eventDate="+eventDate),
							cache : false,
							success : function(response) {
							     $('#staffContect').html(response);
							}
						});
					};							
				 }
				 renderCalendar (cal2);		
			}
		});
  });	
</script>
</div>
</div>