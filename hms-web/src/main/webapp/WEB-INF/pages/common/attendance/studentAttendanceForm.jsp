<%@ include file="/common/taglibs.jsp"%>
<div id="successMsg"></div>
<div id="errorMsg"></div>
<s:if test='%{academicYear.useBiometricForStudent== "N"}'>
	<s:if test='%{#session.previousYear=="N"}'>
		<s:if test='%{startDate != null && endDate != empty}'>
		<s:if test='%{tempString == "S"}'>
			<div class="alert alert-info">
				You can record the attendance only from school start date.
			</div>
		</s:if>
		<s:elseif test='%{plTitle == "E"}'>
		  <div class="alert alert-danger">
				You can't submit attendance after school end date.
		  </div>
		</s:elseif>
		<s:else>
			<s:if test="%{studyClassList != null && !studyClassList.isEmpty()}">
				<span id="startDateSpan" class="<s:property value='startDate'/>"></span>
				<span id="endDateSpan" class="<s:property value='endDate'/>"></span>
				<span id="toDateSpan" class="<s:property value='toDate'/>"></span>
				<span id="academicStartDateSpan"
					class="<s:property value='fromDate'/>"></span>
				<span id="academicEndDateSpan" class="<s:property value='anyTitle'/>"></span>
				<div class="tab-content">
						<!-- only ie perpose for add s:form -->
						<div class="form-body">
							<div class="row">
								<div class="col-md-6">
									<div class="form-group">
										<label class="control-label col-md-4">
											Attendance Date :
										</label>
										<div class="col-md-8">
											<div data-date-start-date="" data-date-end-date="0d" data-date-format="yyyy-mm-dd"
												class="input-group input-medium date date-picker">
												<input type="text" id="attendanceDate" name="attendanceDate" 
														readonly="readonly" value='<s:property value="attendanceDate"/>'
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
								<div class="col-md-6">
									<div class="form-group">
										<label class="control-label col-md-3">
											Select Class :
										</label>
										<div class="col-md-9">
											<s:select id="classId" name="classId"
												headerValue="- Select Class & Section -"
												onchange="javascript:getstudentAttendanceData();"
												list="StudyClassList" listKey="id"
												cssClass="form-control input-medium"
												listValue="classAndSection" headerKey="" theme="simple" />
										</div>
									</div>
								</div>
							</div>
						</div>
					<div id="createAttendenceDiv">
						<s:hidden name="eventId" value="%{eventId}" id="eventId"></s:hidden>
						<jsp:include
							page="/WEB-INF/pages/common/attendance/ajaxEditStudentAttendanceForm.jsp"></jsp:include>
					</div>
				</div>
				</s:if>
				<s:else>
					<div class="alert alert-info">
						Currently there are no classes assigned for you.
					</div>
				</s:else>
			</s:else>
		</s:if>
		<s:else>
			<s:if test="%{ 'ROLE_ADMIN' == user.userRole  || 'ROLE_ADMINOFFICER' == user.userRole}">
				<div class="alert alert-info">
					Please add academic planner details. Then only you can submit
					attendance for staff and student.
					<s:url id="urlSendSmsLink" action="ajaxAcademicSchoolSettings"
						includeParams="all" namespace="/admin" />
					<sj:a href="%{urlSendSmsLink}" targets="mainContentDiv"
						indicator="indicator" cssClass="academicPlannerId">Click here</sj:a>
					to add academic planner details.
				</div>
			</s:if>
			<s:else>
				<div class="alert alert-info">
					Academic year not created. Please contact your school
					administrator.
				</div>
			</s:else>
		</s:else>
	</s:if>
	<s:else>
		<div class="alert alert-info">
			You can not submit attendance for old academic year.
		</div>
	</s:else>
</s:if>
<s:else>
	<div class="alert alert-info">
		You can not submit attendance because you have selected biometric
		process.
	</div>
</s:else>
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
    		if($.browser.version >=49){
    			yyyy = stDate.getFullYear();
        		tyyyy = toDayDate.getFullYear();
    		}else{
    			yyyy = stDate.getFullYear()+100;
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
	// alert(attendanceDate);
	 //alert(tday);
	//var attendanceDate = $('#attendanceDate').val(); 
	var sectionId = $('#classId').val();
	getstudentAttendanceData(attendanceDate,sectionId);
	//alert(attendanceDate+"=="+sectionId);
	//getAttendanceStuds();
});
changePageTitle("Create Student Attendance");
$('.date-picker').datepicker().on('changeDate', function(ev){
	getstudentAttendanceData();
	$('.datepicker').hide();
});
function getstudentAttendanceData(){
	var sectionId = $('select#classId option:selected').val();
	var attendanceDate = $('#attendanceDate').val(); 
	//alert(attendanceDate+"=="+sectionId);
	if(isNonEmpty(attendanceDate) && isNonEmpty(sectionId)){
		var pars = "classId=" + sectionId + "&attendanceDate=" + attendanceDate;
		$("#createAttendenceDiv").html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		$.ajax( {
			url : jQuery.url.getChatURL("/admin/ajaxViewAttendanceForm.do"),
			cache : false,
			data : pars,
			success : function(response) {
				$('#createAttendenceDiv').html(response);
				//disableStudents();
			}
		});
	}else{
		if (isNonEmpty(attendanceDate)) 
			$('#createAttendenceDiv').html("<p><span class='label label-danger'> NOTE : </span> &nbsp;&nbsp;Please select Class & Section.</p>");
		else
			$('#createAttendenceDiv').html("<p><span class='label label-danger'> NOTE : </span> &nbsp;&nbsp;Please select attendance date.</p>");
	}
}
$('a.academicPlannerId').click(function() {
	window.location.hash = "target=ES.ajaxify AAP";
	window.location.reload();
}); 

</script>