<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/messages.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>
					<span class="hidden-481"> </span>
				</div>
			</div>
			<div class="portlet-body">
					<div class="tab-content">
					<span id="startDateSpan" class="<s:property value='startDate'/>"></span>
					<span id="endDateSpan" class="<s:property value='endDate'/>"></span>
						<s:form action="#" id="createAssignment" theme="simple"
							cssClass="form-horizontal">
							<s:if
								test='%{plTitle == "Daily Attendance" || plTitle == "Daily Assignment"}'>
								<s:hidden id="plTitle" name="plTitle" />
								<s:hidden name="attendanceDate" id="attendanceDate" />
								<div class="form-group">
									<label class="control-label col-md-4">
										<s:if test='%{plTitle == "Daily Attendance" }'>
											<span class="required">*</span>Select Date :
										</s:if>
										<s:elseif test='%{plTitle == "Daily Assignment"}'>
											<span class="required">*</span>Get Assignment to From Date, who have not done the assignment :
									</s:elseif>
									</label>
									<div class="col-md-5">
										<div 
											data-date-format="mm/dd/yyyy"
											class="input-group input-medium date date-picker">
											<input type="text" id="startDate" readonly="readonly"
												name="startDate"
												class="required form-control input-medium" />
											<span class="input-group-btn">
												<button type="button" class="btn default">
													<i class="fa fa-calendar"></i>
												</button> </span>
										</div>
										<span class="help-block">(MM/DD/YYYY)</span>
									</div>
								</div>
							</s:if>
						</s:form>
						<div id="createAttendenceDiv">
							<jsp:include
								page="/WEB-INF/pages/admin/reports/ajaxGetAttendanceByDate.jsp" />
						</div>
						<span id="loginRoleName" style="display: none;"><s:property
								value="lastName" />
						</span>
						<div id="schoolTermlist"></div>
					</div>
				</div>
			</div>
		</div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/ems.js"></script>
<script type="text/javascript">
var startDate = $('span#startDateSpan').attr("class");
//var endDate = $('span#endDateSpan').attr("class");
var toDate = $('span#toDateSpan').attr("class");
var endDate = new Date(toDate);
var title =$('#plTitle').val();
if('Daily Attendance' ==title){
	dateRestrictionWithinAcademicYear(startDate,endDate);
}else{
	dateRestrictionWithinAcademicYear(startDate,toDate);
}

FormComponents.init();

$('.date-picker').datepicker().on('changeDate', function(ev){
	var plTitle = $("#plTitle").val();
	var startDate = $('#startDate').val();
	var pars = "startDate=" + startDate + "&plTitle=" + plTitle + "&tempString=Student";
	$("#createAttendenceDiv").html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
	$.ajax( {
		url : "../reports/ajaxGetSchoolHoliday.do",
		cache : false,
		data : pars,
		success : function(response) {
			$('#createAttendenceDiv').html(response);
		}
	});
});
</script>