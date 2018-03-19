<%@ include file="/common/taglibs.jsp"%>
<span id="startDateSpan" class="<s:property value='startDate'/>"></span>
<span id="endDateSpan" class="<s:property value='endDate'/>"></span>
<s:form id="editStudentDisable" action="ajaxDisableStudent"	method="post" theme="simple" namespace="/student" cssClass="form-horizontal">
	<s:if test='%{user.isOnlySchoolAdmin=="Y" || user.isSchoolPrincipal=="Y" || user.isSchoolClerk=="Y" || user.isSchoolDirector == "Y"}'>
		<s:hidden name="tempId" value="%{tempId}" />
		<s:hidden name="bedId" value="%{selectedId}" />
		<s:hidden name="student.status" value="%{plTitle}" />
		<div class="form-group">
			<label class="control-label col-md-3">
				<span class="required">*</span> Cause/Reason :
			</label>
			<div class="col-md-9">
				<s:textarea cols="5" rows="5" id="messageDescription"
					maxCharsData="1000" cssClass="required form-control word_count"
					name="student.description"></s:textarea>
				<span class="counter"></span>
			</div>
		</div>
		<div class="form-actions fluid">
			<div class="col-md-offset-3 col-md-6">
				<s:if test='%{#session.previousYear == "N"}'>
					<img src="../img/bg/bigWaiting.gif" alt="Loading..."  title="Loading..." id="indicator"
						style="display: none; background-repeat: no-repeat; position: relative; z-index: 1000; bottom: 200px; margin-left: 240px;" />
					<sj:submit cssClass="submitBt btn blue" value="Save" id="submitButton1" indicator="indicator" 
						targets="searchStudentsList" formIds="editStudentDisable" validate="true"
						onBeforeTopics="changeStudentDisableValidation" resetForm="true" />
					 	<button type="button" data-dismiss="modal" class="btn default">
							Cancel
						</button>
				</s:if>
			</div>
		</div>
	</s:if>
</s:form>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/subscription/textcounter.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/ems.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	$.destroyTopic('changeStudentDisableValidation');
	FormComponents.init();
	$.subscribe('changeStudentDisableValidation', function(event, data) {
		var msgdesValueActive = $('#messageDescription').val();
		if(isNonEmpty(msgdesValueActive))
			$('button.close').click();
		else
			event.originalEvent.options.submit=false;
	});
});
var startDate = $('span#startDateSpan').attr("class");
var endDate = $('span#endDateSpan').attr("class");
dateRestrictionWithinAcademicYear(startDate,endDate);

$('.date-picker').datepicker().on('changeDate', function(ev){
	var startDate = $("#startDate").val();
	var endDate = $("#endDate").val();
	$('#dispaySelectedDate').hide();
	if (isNonEmpty(startDate) && isNonEmpty(endDate)) {
		if (Date.parse(endDate) < Date.parse(startDate)) {
			$('#dispaySelectedDate').html("<div class='alert alert-info'><div style='display: block'>Invalid Date Range!\nStart Date cannot be after End Date!</div></div>");
			$('#dispaySelectedDate').show();
			$('input#suspendDaysCount').val("");
			$("#endDate").val('');
			return false;
		} else {
			var custId=$('span#custIdSpan').attr("class");
			var daysDiff =0;
			$('input#halfdayleaveId').click( function(){
				if ($(this).is(':checked')){ 
					  daysDiff = (Date.parse(endDate) - Date.parse(startDate)) / 1000 / 60 / 60 / 24 + 1-0.5;
				 }else{
					  daysDiff = (Date.parse(endDate) - Date.parse(startDate)) / 1000 / 60 / 60 / 24 + 1;
				}
				$('input#suspendDaysCount').val(daysDiff);
			});
			  daysDiff = (Date.parse(endDate) - Date.parse(startDate)) / 1000 / 60 / 60 / 24 + 1;
			  $('input#suspendDaysCount').val(daysDiff);
		}
	}
});

</script>