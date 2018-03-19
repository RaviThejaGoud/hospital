<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/subscription/textcounter.js">
</script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>
<div class="grid_14">
	<s:if test="%{studentOutList != null && !studentOutList.isEmpty()}">
		<s:iterator value="studentOutList">
		<div class="outStudent"> 
		   <span id="studentStartDateSpan" class="<s:property value='anyId'/>"></span>
		    <span id="studentEndDateSpan" class="<s:property value='selectedDate'/>"></span>
		</div>
		</s:iterator>
	</s:if>
						
	<s:form action="ajaxCreateNewStudent" theme="css_xhtml" method="post"namespace="/hostel">
		<s:hidden name="student.account.id" value="%{selectedId}"></s:hidden>
		 <h1>
			Create Student 
		</h1>
		<fieldset>
			<div class="grid_13">
				<div class="grid_6 left">
					<div class="grid_6">
						<sj:textfield name="studentOut.visitorName" id="visitorName" required="true"
							label="Visitor Name" cssClass="textfield required" tabindex="1"
							maxlength="50"></sj:textfield>
							<span class="hintMessage">(Type "self" in the above text field if the student is taking permission without visitor.)</span>
					</div>
					<div class="grid_6">
						<sj:datepicker id="outDate" readonly="true" minDate="0" required="true"
							name="studentOut.outDate" label="Out Date" changeYear="true" onchange="javascript:verifyDate(this.value,this);"
							tabindex="3" cssClass="textfield required" changeMonth="true" 
							onCompleteTopics="displayDate"/>
					</div>
					<div class="grid_6">
						<s:select name="studentOut.outTime" label="Out Time" id="outTime"
							cssClass="textfield required" required="true" tabindex="5"
							onchange="checkOutTimeInTImeValidation()" headerKey=""
							headerValue="-Select-"
							list="#{'05:00 AM':'05:00 AM','05:15 AM':'05:15 AM','05:30 AM':'05:30 AM','05:45 AM':'05:45 AM', '06:00 AM':'06:00 AM', '06:15 AM':'06:15 AM','06:30 AM':'06:30 AM','06:45 AM':'06:45 AM','07:00 AM':'07:00 AM','07:15 AM':'07:15 AM','07:30 AM':'07:30 AM','07:45 AM':'07:45 AM','08:00 AM':'08:00 AM','08:15 AM':'08:15 AM','08:30 AM':'08:30 AM','08:45 AM':'08:45 AM','09:00 AM':'09:00 AM','09:15 AM':'09:15 AM','09:30 AM':'09:30 AM','09:45 AM':'09:45 AM','10:00 AM':'10:00 AM','10:15 AM':'10:15 AM','10:30 AM':'10:30 AM','10:45 AM':'10:45 AM','11:00 AM':'11:00 AM','11:15 AM':'11:15 AM','11:30 AM':'11:30 AM','11:45 AM':'11:45 AM','12:00 AM':'12:00 AM','12:15 AM':'12:15 AM','12:30 AM':'12:30 AM','12:45 AM':'12:45 AM','01:00 PM':'01:00 PM','01:15 PM':'01:15 PM','01:30 PM':'01:30 PM','01:45 PM':'01:45 PM','02:00 PM':'02:00 PM','02:15 PM':'02:15 PM','02:30 PM':'02:30 PM','02:45 PM':'02:45 PM','03:00 PM':'03:00 PM','03:15 PM':'03:15 PM','03:30 PM':'03:30 PM','03:45 PM':'03:45 PM','04:00 PM':'04:00 PM','04:15 PM':'04:15 PM','04:30 PM':'04:30 PM','04:45 PM':'04:45 PM','05:00 PM':'05:00 PM','05:15 PM':'05:15 PM','05:30 PM':'05:30 PM','05:45 PM':'05:45 PM','06:00 PM':'06:00 PM','06:15 PM':'06:15 PM','06:30 PM':'06:30 PM','06:45 PM':'06:45 PM','07:00 PM':'07:00 PM','07:15 PM':'07:15 PM','07:30 PM':'07:30 PM','07:45 PM':'07:45 PM','08:00 PM':'08:00 PM','08:15 PM':'08:15 PM','08:30 PM':'08:30 PM','08:45 PM':'08:45 PM','09:00 PM':'09:00 PM','09:15 PM':'09:15 PM','09:30 PM':'09:30 PM','09:45 PM':'09:45 PM','10:00 PM':'10:00 PM','10:15 PM':'10:15 PM','10:30 PM':'10:30 PM','10:45 PM':'10:45 PM','11:00 PM':'11:00 PM','11:15 PM':'11:15 PM','11:30 PM':'11:30 PM','11:45 PM':'11:45 PM'}"></s:select>
					</div>
					<div class="grid_10">
						<div class="grid_6">
							<sj:textarea rows="3" cols="20" name="studentOut.reasonForOuting"
								label="Reason For Outing" cssStyle="300"
								cssClass="text small  word_count required " required="true"   
								requiredposition="left" tabindex="7"></sj:textarea>
							<div class="counter"></div>
						</div>
					</div>
				</div>
				<div class="grid_6">
					<div class="grid_6">
						<sj:textfield name="studentOut.visitorRelation"
							id="visitorRelation" label="Visitor Relation"
							cssClass="textfield" tabindex="2" maxlength="50"></sj:textfield>
					</div>
					<div class="grid_6">
						<sj:datepicker id="inDate" readonly="true" minDate="0" required="true"
							name="studentOut.expectedInDate" label="In Date" changeMonth="true"
							tabindex="4" cssClass="textfield required"  onchange="javascript:verifyDate(this.value,this);ajaxStudentOutDates()"
							changeYear="true" onCompleteTopics="displayDate"/>
					</div>
					<div class="grid_6">
						<s:select name="studentOut.exceptedInTime" label="In Time" id="exceptedInTime"
							cssClass="textfield required" required="true" tabindex="6"  
							onchange="checkOutTimeInTImeValidation()" headerKey=""
							headerValue="-Select-"
							list="#{'05:00 AM':'05:00 AM','05:15 AM':'05:15 AM','05:30 AM':'05:30 AM','05:45 AM':'05:45 AM', '06:00 AM':'06:00 AM', '06:15 AM':'06:15 AM','06:30 AM':'06:30 AM','06:45 AM':'06:45 AM','07:00 AM':'07:00 AM','07:15 AM':'07:15 AM','07:30 AM':'07:30 AM','07:45 AM':'07:45 AM','08:00 AM':'08:00 AM','08:15 AM':'08:15 AM','08:30 AM':'08:30 AM','08:45 AM':'08:45 AM','09:00 AM':'09:00 AM','09:15 AM':'09:15 AM','09:30 AM':'09:30 AM','09:45 AM':'09:45 AM','10:00 AM':'10:00 AM','10:15 AM':'10:15 AM','10:30 AM':'10:30 AM','10:45 AM':'10:45 AM','11:00 AM':'11:00 AM','11:15 AM':'11:15 AM','11:30 AM':'11:30 AM','11:45 AM':'11:45 AM','12:00 AM':'12:00 AM','12:15 AM':'12:15 AM','12:30 AM':'12:30 AM','12:45 AM':'12:45 AM','01:00 PM':'01:00 PM','01:15 PM':'01:15 PM','01:30 PM':'01:30 PM','01:45 PM':'01:45 PM','02:00 PM':'02:00 PM','02:15 PM':'02:15 PM','02:30 PM':'02:30 PM','02:45 PM':'02:45 PM','03:00 PM':'03:00 PM','03:15 PM':'03:15 PM','03:30 PM':'03:30 PM','03:45 PM':'03:45 PM','04:00 PM':'04:00 PM','04:15 PM':'04:15 PM','04:30 PM':'04:30 PM','04:45 PM':'04:45 PM','05:00 PM':'05:00 PM','05:15 PM':'05:15 PM','05:30 PM':'05:30 PM','05:45 PM':'05:45 PM','06:00 PM':'06:00 PM','06:15 PM':'06:15 PM','06:30 PM':'06:30 PM','06:45 PM':'06:45 PM','07:00 PM':'07:00 PM','07:15 PM':'07:15 PM','07:30 PM':'07:30 PM','07:45 PM':'07:45 PM','08:00 PM':'08:00 PM','08:15 PM':'08:15 PM','08:30 PM':'08:30 PM','08:45 PM':'08:45 PM','09:00 PM':'09:00 PM','09:15 PM':'09:15 PM','09:30 PM':'09:30 PM','09:45 PM':'09:45 PM','10:00 PM':'10:00 PM','10:15 PM':'10:15 PM','10:30 PM':'10:30 PM','10:45 PM':'10:45 PM','11:00 PM':'11:00 PM','11:15 PM':'11:15 PM','11:30 PM':'11:30 PM','11:45 PM':'11:45 PM'}"></s:select>
					</div>
				</div>
			</div>
			<div class="grid_12">
				<s:url id="doAddNewStudentList"
					action="ajaxDoManageStudentOut" includeParams="all"
					escapeAmp="false" namespace="/hostel">
					<s:param name="academicYearId" value="%{academicYearId}" />
				</s:url>
				<sj:a href="%{doAddNewStudentList}" cssClass="cancelButton"
					indicator="indicator" targets="stepHostelStudentInAndOut">Cancel</sj:a>
				<sj:submit   cssClass="submit small" value="Submit"
					onClickTopics="formValidation" validate="true"
					indicator="indicator" targets="stepHostelInOutRegister" />
			</div>
		</fieldset>
	</s:form>
</div>
<script type="text/javascript">
$(document).ready(function() {
	});
	changePageTitle("Add Student");
	$('.numeric').numeric();
	$('.alphabet').alpha();
$.subscribe('displayDate', function(event, data) {
var outDate = $('#outDate').val();
var inDate = $('#inDate').val();
if (isNonEmpty(outDate) && isNonEmpty(inDate)) {
	outDate = Date.parse(outDate);
	inDate = Date.parse(inDate);
	if (inDate < outDate) {
	alert("Out date should be equal/More than in date.");
	$('#inDate').val("");
} else {
	return true;
}
}
});
function checkOutTimeInTImeValidation(){
	var outDate = $('#outDate').val();
	var inDate = $('#inDate').val();
	if(isNonEmpty(outDate) && isNonEmpty(inDate)){
		var outTime = $('#outTime').val();
		var inTime= $('#exceptedInTime').val();
		if(isNonEmpty(outTime) && isNonEmpty(inTime)){
			var outDate = new Date(outDate +' '+ outTime);
		   	var inDate=new Date(inDate +' '+ inTime);
			if(inDate <= outDate){
			     alert("In time should be more than out time.");
			     $('#exceptedInTime').val('');
			}
		}	
	}
}
function verifyDate(date,event) {
	var startDate = $("#outDate").val();
	var endDate = $("#inDate").val();
	
$('div.outStudent').each(function() {
		 var studentStartDateSpan=$(this).children($('span#studentStartDateSpan')).attr("class");
	     var studentEndDateSpan=$(this).children($('span#studentStartDateSpan')).next($('span#studentEndDateSpan')).attr("class");
	     //alert("studentStartDateSpan="+studentStartDateSpan+"&studentEndDateSpan="+studentEndDateSpan);
	     var studentStartDate = new Date(studentStartDateSpan);
	     var studentEndDate = new Date(studentEndDateSpan);
         var selectedStartDate = new Date(startDate);
         var selectedEndDate = new Date(endDate);
        // alert(selectedStartDate+"----"+studentEndDate);
        if(!(selectedStartDate>=studentEndDate && selectedStartDate<=studentEndDate) ){
	        if((selectedStartDate<studentStartDate || selectedStartDate>studentEndDate) && (selectedEndDate<studentStartDate || selectedEndDate>studentEndDate)){
	        	return true;
	        }
        }else{
         	alert("From "+studentStartDateSpan+ " To "+studentEndDateSpan+ " This dates already applied permission.Please select the different dates.");
				$('input#outDate').val('');
				$('input#inDate').val('');
			    return false;
        }
	 });
}
function ajaxStudentOutDates() {
	$('#exceptedInTime').val('');
}
</script>