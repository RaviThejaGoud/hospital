<%@ include file="/common/taglibs.jsp"%>
<div class="tab-content">
	<s:if test='%{tempString == "S"}'>
		<div class="alert alert-info"> You can record the attendance only from school start date.  </div>
	</s:if>
	<s:elseif test='%{plTitle == "E"}'>
	 	<div class="alert alert-danger"> You can't record the attendance after school end date </div>
	</s:elseif>
	<s:else>
	<span id="startDateSpan" class="<s:property value='startDate'/>"></span>
	<span id="endDateSpan" class="<s:property value='endDate'/>"></span>
	<span id="toDateSpan" class="<s:property value='toDate'/>"></span>
	<span id="academicStartDateSpan"
		class="<s:property value='fromDate'/>"></span>
	<span id="academicEndDateSpan"
		class="<s:property value='anyTitle'/>"></span>
	<div class="tab-content">
		<div class="form-body">
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4">
							Attendance Date :
						</label>
						<div class="col-md-8">
							<div data-date-start-date="" data-date-end-date="+0d" data-date-format="yyyy-mm-dd"
								class="input-group input-medium date date-picker">
								<input type="text" id="attendanceDate" readonly="readonly"
									name="attendanceDate"
									value='<s:property value="attendanceDate"/>'
									class="form-control">
								<span class="input-group-btn">
									<button type="button" class="btn default">
										<i class="fa fa-calendar"></i>
									</button> </span>
							</div>
							<span class="help-block">(YYYY-MM-DD)</span>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div id="studentAttendanceResults">
			<jsp:include
				page="/WEB-INF/pages/common/staffAttendance/ajaxViewStaffAttendanceForm.jsp"></jsp:include>
		</div>
	</div>
	</s:else>
</div>
<script type="text/javascript">
$(document).ready(function() {
	var startDate = $('span#startDateSpan').attr("class");
	//var endDate = $('span#endDateSpan').attr("class");
	var stDate = new Date(startDate);
	//var enDate = new Date(endDate);
	var toDate = $('span#toDateSpan').attr("class");
	var toDayDate = new Date(toDate);
	var yyyy ='';
	//var ayyyy ='';	
	var tyyyy ='';
	//alert(stDate+"===="+enDate);
	var dd = stDate.getDate();
    var mm = stDate.getMonth()+1; //January is 0!
    //var edd = enDate.getDate();
    //var emm = enDate.getMonth()+1; //January is 0!
    var tdd = toDayDate.getDate();
    var tmm = toDayDate.getMonth()+1; //January is 0!
    $.browser.chrome = /chrom(e|ium)/.test(navigator.userAgent.toLowerCase()); 
    if($.browser.chrome) {
    		yyyy = stDate.getFullYear();
    		//ayyyy = enDate.getFullYear();
    		tyyyy = toDayDate.getFullYear();
    	} else if ($.browser.mozilla) {
    		//alert(yyyy+"===="+ayyyy);
    		if($.browser.version >=49){
    			yyyy = stDate.getFullYear();
        		//ayyyy = enDate.getFullYear()+100;
        		tyyyy = toDayDate.getFullYear();
    		}else{
    			yyyy = stDate.getFullYear()+100;
        		//ayyyy = enDate.getFullYear()+100;
        		tyyyy = toDayDate.getFullYear()+100;
    		}
    	} else if ($.browser.msie) {
    		yyyy = stDate.getFullYear()+100;
    		//ayyyy = enDate.getFullYear()+100;
    		tyyyy = toDayDate.getFullYear()+100;
    	}
    var sday = yyyy+'-'+mm+'-'+dd;
    if(dd<10){
        dd='0'+dd
    } 
    if(mm<10){
        mm='0'+mm
    } 
   /*  if(edd<10){
        edd='0'+edd
    } 
    if(emm<10){
        emm='0'+emm
    }  */
    if(tdd<10){
        tdd='0'+tdd
    } 
    if(tmm<10){
        tmm='0'+tmm
    } 
    var attendanceDate = $('#attendanceDate').val(); 
   // var eday = ayyyy+'-'+emm+'-'+edd;
    var tday = tyyyy+'-'+tmm+'-'+tdd;
    $('div.date-picker').datepicker({
       startDate: sday,
       //endDate: eday
        toDate: tday
    });
	getStaffAttendanceData(attendanceDate);
});
$('.date-picker').datepicker().on('changeDate', function(ev){
	var attendanceDate = $('#attendanceDate').val(); 
	getStaffAttendanceData(attendanceDate);
	$('.datepicker').hide();
});
function getStaffAttendanceData(attendanceDate) {
	if(isNonEmpty(attendanceDate)){
		var pars = "attendanceDate=" + attendanceDate;
		$("#studentAttendanceResults").html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		$.ajax( {
			url : jQuery.url.getChatURL("/admin/ajaxViewStaffAttendanceForm.do"),
			cache : false,
			data : pars,
			success : function(response) {
				$('#studentAttendanceResults').html(response);
			}
		});	
	}else{
		$('#studentAttendanceResults').html('Please select Attendance date.');
	}
}

$('a.academicPlannerId').click(function() {
	window.location.hash = "target=ES.ajaxify AAP";
	window.location.reload();
});
</script>
