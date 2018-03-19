<%@ include file="/common/taglibs.jsp"%>
<jsp:include page="/common/messages.jsp"></jsp:include>
<s:if test="%{studyClassList != null && !studyClassList.isEmpty()}">
<div class="form-body form-horizontal">
		<s:if test='%{plTitle == "TRMessages" || anyTitle == "TR"}'>
		<h4 class="pageTitle bold">
				Send SMS 
		</h4>
		 <div class="form-group">
				<div class="panel-body col-md-12">
					<div class="col-md-1">
						<span class="label label-danger"> NOTE : </span>
					</div>
					<div class="col-md-10">
						<ul>
							<li>
								 You can send a text message via SMS.
							</li>
							<li>
								Sent SMS Count depend on No.of characters present in the message content.160 Characters of message is treated as 1SMS.
							</li>
						</ul>
					</div>
				</div>
			</div>
		</s:if>
		<s:else>
		 <div class="form-group">
				<div class="panel-body col-md-12">
					<div class="col-md-1">
						<span class="label label-danger"> NOTE : </span>
					</div>
					<div class="col-md-10">
						<ul>
							<li>
								 You can send a text message via SMS.
							</li>
							<li>
								Sent SMS Count depend on No.of characters present in the message content.160 Characters of message is treated as 1SMS.
							</li>
							<li>
								PTA means Parent Teacher Association.
							</li>
						</ul>
					</div>
				</div>
			</div>
		</s:else>
		<s:if test='%{user.isSchoolAsstStaff=="N"}'>
			<s:if test="%{(smsAlloted == 0) || (smsAlloted <= smsCnt)}">
				<div style="color: red;" class="alert alert-info">
					You have been used all your allotted
					<s:property value="smsAlloted" />
					SMS. Please contact EazySchool support team (080-46620999) to recharge your SMS.
				</div>
			</s:if>
		</s:if>
 <div id="smsAllottedDiv">
	<div id="messageTypeDiv">
		<div class="form-group">
			<label class="col-md-2 control-label">
				Select Type :
			</label>
			<div class="col-md-8">
				<div class="radio-list">
					<s:if test='%{plTitle != "TRMessages"}'>
						<s:if
							test='%{user.isOnlySchoolTeacher!="Y" && user.isSchoolTransport=="N" && user.isOnlySchoolHod=="N" && user.isSchoolAsstStaff=="N"}'>
							<label class="radio-inline">
								<input type="radio" value="SchoolWideMessages" id="schoolWideId"
									onclick="changeQualification(this.value);" name="checkMessages"
									class="radio" checked="checked">
								School Wide Messages
							</label>
						</s:if>
						<s:if
							test='%{customer.checkMobileService == true || user.isSchoolAdmin == "Y"}'>
								<label class="radio-inline">
									<input type="radio" value="ClassWideMessages" id="classWideId"
										onclick="changeQualification(this.value);"
										name="checkMessages" class="radio">
									Class Wide Messages
								</label>
							</s:if>
						<s:else>
							<s:if test='%{user.isSchoolAdmin == "Y"}'>
								<span class="label label-danger"> ALERT : </span>&nbsp;
								<b> SMS services are disabled, enable services. <s:url
										id="urlManageSchool" action="schoolSettings"
										namespace="/admin" /> <sj:a href="%{urlManageSchool}"
										targets="mainContentDiv">
												Enable Service</sj:a> </b>
							</s:if>
							<s:else>
								<span class="label label-danger"> ALERT : </span>&nbsp;
								<b>SMS services disabled, Contact Admin to enable services</b>
							</s:else>
						</s:else>
					</s:if>
					<s:if test="%{customer.transportModuleStatus == true}">
						<s:if test='%{user.isSchoolAdmin == "Y" || user.isSchoolTransport=="Y"  || user.isSchoolClerk=="Y" || user.isSchoolPrincipal=="Y" || user.isSchoolDirector == "Y"}'>
							<label class="radio-inline">
								<input type="radio" value="TransportMessages"
									id="transportWideId" onclick="changeQualification(this.value);"
									name="checkMessages" class="radio">
								Transport Messages
							</label>
						</s:if>
					</s:if>
					<s:if test='%{plTitle != "TRMessages" && customer.standardType !="P"}'>
						<s:if test='%{user.isOnlySchoolTeacher!="Y" && user.isSchoolTransport=="N" && user.isOnlySchoolHod=="N" && user.isSchoolAsstStaff=="N"}'>
							<s:if test='%{plTitle == "absenteesMessages"}'>
								<label class="radio-inline">
									<input type="radio" value="absenteesMessages" id="absenteesId"
										onclick="changeQualification(this.value);" name="checkMessages"
										class="radio" checked="checked">
									 	Today Absentees
								</label>
							</s:if>
							<s:else>
								<label class="radio-inline">
									<input type="radio" value="absenteesMessages" id="absenteesId"
										onclick="changeQualification(this.value);" name="checkMessages"
										class="radio">
									 	Today Absentees
								</label>
							</s:else>
						</s:if>
					</s:if>
				</div>
			</div>
		</div>
	</div>
	 <s:if test='%{user.isSchoolTransport=="N" && user.isSchoolAsstStaff == "N"}'>
		<div id="schoolWideDiv" style="display: block;">
			<jsp:include page="/WEB-INF/pages/common/messages/schoolWideMessages.jsp"></jsp:include>
		</div>
	</s:if>
	<div id="classWideDiv" style="display: none;">
		<jsp:include
			page="/WEB-INF/pages/common/messages/classWideMessages.jsp"></jsp:include>
	</div>
	<s:if test='%{user.isSchoolTransport=="Y" }'>
		<div id="transportWideDiv">
	</s:if>
	<s:elseif test='%{user.isOnlySchoolAdmin=="Y"}'>
		<div id="transportWideDiv" style="display: none;">
	</s:elseif>                                                                                           
	<s:else>
		<div id="transportWideDiv" style="display: none;">
	</s:else>
	<jsp:include
		page="/WEB-INF/pages/common/messages/transportWideMessages.jsp"></jsp:include></div>
	</div>	
	<s:if test='%{user.isSchoolTransport=="N" && user.isSchoolAsstStaff == "N" && customer.standardType !="P"}'>
		<div id="absenteesDiv" style="display: none;">
			<jsp:include page="/WEB-INF/pages/common/messages/absentStudentAndStaffMessages.jsp"></jsp:include>
		</div>
	</s:if>
</s:if>
<s:else>
	<div class="alert alert-info">
		Classes are not assigned to this staff.
	</div>
</s:else>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>
<script  type="text/javascript">
changePageTitle("Send School Wide Message");
$('#classesWithChkBoxes').hide();
$(document).ready(function() 
{
	$('input[name="chkBoxSelectedAccountIds"]').click(function(){
	      $("#reloadMessageDiv").html("result reloaded successfully");
	   });
	if ($("input[name=chkBoxClassWiseStatus]:checked").length == 1) {
		$('input:checkbox[name=chkBoxClassWiseStatus]:checked').each(function() 
			{
				$('#chkBoxClassWiseStatus').val(this.value);
			});
	}	

$("input:checkbox, input:radio").uniform();
	if((<s:property value="smsAlloted"/> == 0) || (<s:property value="smsAlloted"/>) <= (<s:property value="smsCnt"/>)){
		//alert('You have been used all your allotted '+ <s:property value="smsAlloted" />+' SMS(s). Please contact eazyschool supporting team (080-46620999) to recharge your SMS(s).');
		$('#smsAllottedDiv').hide();
	}
$('#DaysScholarOrHostel').hide();
var isClassTeacher='<s:property value="selectedId"/>';
if(isNonEmpty(isClassTeacher)&& isClassTeacher=="classTeacher"){
	$('input#schoolWideId').removeAttr('checked');
	$('input#classWideId').attr("checked", 'checked');
	$('input#classWideId').click();
}
$.destroyTopic('classWideStudentsMsgs');
$.subscribe('classWideStudentsMsgs', function(event, data) {
	$('.submitBt').attr('disabled', 'disabled');
		 if($('input#classStudents').is(':checked')){
			 $('#title2').attr('disabled', 'true');
			 if(($("input[name='chkBoxClassSelectedIds']:checked").length == 0) && ($('input#classStudents').is(':checked'))){
			 		alert("Please Select at least one Class.");
			 		$('.submitBt').removeAttr('disabled');
					event.originalEvent.options.submit=false;
			 }else{
				 $('li#CreateSMSEmail').removeClass('active');
				 return true;
			 }
			}else{
				  $('#title1').attr('disabled', 'true');
				var alertTypes = $('input[name="chkBoxSelectedAccountIds"]:checked').length;
				if (alertTypes == 0) {
					$('.submitBt').removeAttr('disabled');
					alert("Please Select at least one student.");
					event.originalEvent.options.submit=false;
				}
				if(($("input[name='chkBoxSelectedAccountIds']:checked").length == 0) && ($('input#selectedStudents').is(':checked'))){
					$('.submitBt').removeAttr('disabled');
					event.originalEvent.options.submit=false;
				 }else{
					 $('li#CreateSMSEmail').removeClass('active');
					return true;
				 }
			}
	});
	
//checkAvailableSMSCount('A');

	var asstTeacherRole = '<s:property value="user.isSchoolAsstStaff"/>';

	if("Y" == asstTeacherRole)
	{
		$('#classWideDiv').show();	
		$('li#smsDiv').removeClass('active');

		
		//changeQualification("ClassWideMessages");
		
		$('input#classWideId').attr("checked", 'checked');
		$('input#classWideId').click();
	}

});


$.destroyTopic('formValidateForSchoolWideMessages');
$.subscribe('formValidateForSchoolWideMessages', function(event, data) {
	//$('.submitBt').attr('disabled', 'disabled');
	var staffTypes = $("#msgStaffType").val();
	var staffTypess = $("#msgReceiverType").val();
	var absentMessageType = $("#msgReceiverTypeId").val();
	var messageDescription = $("#messageDescriptionDivId").val();
	
	if((staffTypes == "MGT" || staffTypes == "T" || staffTypes == "NT" || staffTypes == "CT" || staffTypes == "VP") && (staffTypess=="S")){
		if($("input[name='chkBoxSelectedAccountIds']:checked").length == 0){
			alert("Please Select at least one staff.");
			$('.submitBt').removeAttr('disabled');
			event.originalEvent.options.submit=false;
		}
	}
	if(absentMessageType == "S"){
		if($("input[name='chkBoxSelectedAccountIds']:checked").length == 0){
			alert("Please Select at least one staff.");
			$('.submitBt').removeAttr('disabled');
			event.originalEvent.options.submit=false;
		}
	}
	if(absentMessageType == "P"){
		if($("input[name='chkBoxSelectedAccountIds']:checked").length == 0){
			alert("Please Select at least one student.");
			$('.submitBt').removeAttr('disabled');
			event.originalEvent.options.submit=false;
		}
	}
	
	if ($('#formForSchoolWideMessages').valid()){
		$('li#CreateSMSEmail').removeClass('active');
		return true;
	}
	else
	{
		$('.submitBt').removeAttr('disabled');
		event.originalEvent.options.submit=false;
	}
});


$('#emailOrSms').val('M');
$("input[name=chkBoxStatus]").click(function() {
	if ($("input[name=chkBoxStatus]:checked").length == 1) {
		$('input:checkbox[name=chkBoxStatus]:checked').each(function() 
			{
				$('#checkStatus').val(this.value);
			});
	} else {
			$('#checkStatus').val('');
	}
});

$("input[name=chkBoxClassWiseStatus]").click(function() {
	if ($("input[name=chkBoxClassWiseStatus]:checked").length == 1) {
		$('input:checkbox[name=chkBoxClassWiseStatus]:checked').each(function() 
			{
				$('#chkBoxClassWiseStatus').val(this.value);
				GetSelectedListChanged();
			});
	} else {
		$('#chkBoxClassWiseStatus').val('');
		GetSelectedListChanged();
	}
});
$("input[name=chkBoxClassSelectedIds]").click(function() {
	if ($("input[name=chkBoxClassSelectedIds]:unchecked").length > 0) {
	   $(".allClasses").parent('span').removeClass("checked");
		$(".allClasses").attr("checked", false);
	} else {
	    $(".allClasses").parent('span').addClass("checked");
		$(".allClasses").attr("checked", true);
	}
});
 
$("input[name=chkBxSelectedIds]").click(function() {
	if ($("input[name=chkBxSelectedIds]:unchecked").length > 0) {
	   $(".selectAllStudents").parent('span').removeClass("checked");
		$(".selectAllStudents").attr("checked", false);
	} else {
	    $(".selectAllStudents").parent('span').addClass("checked");
		$(".selectAllStudents").attr("checked", true);
	}
});
	
function checkAllClasses() {
	if ($(".allClasses").is(':checked')){
	    $("[name='chkBoxClassSelectedIds']").each(function(){
		   $(this).parent('span').addClass('checked');
		    $(this).attr("checked", "true");
		});
	}
	else{
	 $("[name='chkBoxClassSelectedIds']").each(function(){
		   $(this).parent('span').removeClass('checked');
		    $(this).removeAttr("checked");
		});
	}	
}


function GetSelectedListChanged(){
$('select#className option').each(
	    function() {
	        if($(this).val()!=""){
	          $(this).attr('selected','selected');
	          getAjaxDoGetStudent($(this).val());
	       	  return false;
	       	//  event.originalEvent.options.submit=false;
	        }
	    }
	);
}
function changeQualification(staffType) {
	if (staffType == 'SchoolWideMessages') {
		$('select#msgReceiverType').val('A');
		$('select#msgStaffType').val('A');
		$('#staffText').show();
		$('#messageSalutation').hide();
		$('.messageTypeforStaff').hide();
		$('#schoolWideDiv').show();
		$('#classWideDiv').hide();	
		$('#transportWideDiv').hide();
		$('#smsContentDiv').show();
		$('#messageDescriptionDiv').show();
		$('#messageDescriptionDiv').val("");
		$('#absenteesDiv').hide();
	} else if (staffType == 'ClassWideMessages') {
		$('#DaysScholarOrHostelCw').show();	
	    $("input#selectedStudents").click();
	    $('#classWideemailOrSms').val('M');
		$('#schoolWideDiv').hide();
		$('#classWideDiv').show();
		$('#smsContentDiv').hide();
		$('#clsDiv').show();
		$('#transportWideDiv').hide();
		$('textarea#messageDescriptionDiv').hide();
		$('#absenteesDiv').hide();
		  //GetSelectedListChanged();
	}
	else if (staffType == 'TransportMessages') {
	    $('#schoolWideDiv').hide();
	    $('#classWideDiv').hide();
	    $('#absenteesDiv').hide();
	    $('#DaysScholarOrHostelCw').hide();	
		$('#transportWideDiv').show();
		$('#smsContentDiv').hide();
		$('textarea#messageDescriptionDiv').hide();
		transportListByType($('#tansportTypeId').val());
	}
	else if (staffType == 'absenteesMessages') {
		 $('#schoolWideDiv').hide();
		 $('#classWideDiv').hide();
		 $('#DaysScholarOrHostelCw').hide();
		 $('#transportWideDiv').hide();
		 $('#absenteesDiv').show();
		 $('#toDivId').show();
		 $('#submitId').hide();
		 $('#msgReceiverTypeId').val('');
		 $('#messageSalutationId').hide();
		//$('#smsContentDivId').hide();
		 $('#absenteesList').hide();
	}
}
function changeSelectedType(selectedType) {
//checkMessageType();
	$('#DaysScholarOrHostelCw').show();	
	if (selectedType == 'SingleClassStudents') {
		$('#messageDesc').val("");
	$('#classesWithChkBoxes').hide();
	$('#classesInSelectBox').show();
	GetSelectedListChanged();
	} else if (selectedType == 'MultipleclassStudents') {
		$('#messageDesc').val("");
	$("div#studentList").empty();
	$(".allClasses").parent('span').removeClass('checked');
	$(".allClasses").removeAttr("checked");
	 $("[name='chkBoxClassSelectedIds']").each(function(){
		   $(this).parent('span').removeClass('checked');
		    $(this).removeAttr("checked");
	});
	$('#classesInSelectBox').hide();
	$('#studentList').hide();
	$('#classesWithChkBoxes').show();
	}
}
function changeOtherQualification(type) {
	if (type == 'Student') {
		$('#messageSalutation').show();	
		$('#parentSignature').show();
		$('#parentText').show();
		$('#staffText').hide();
		$('#principalText').hide();
		$('#otherId').val(type);
		$('#otherMembersText').hide(); 
	} else if (type == 'Staff') { 
			$('#messageSalutation').show();	
			$('#parentSignature').show();
		//}
		$('#parentText').hide();
		$('#staffText').show();
		$('#otherId').val(type);
		$('#otherMembersText').hide();
	}else if (type == 'OtherMember') {
		$('#messageSalutation').show();	
		$('#parentSignature').show();
		$('#parentText').hide();
		$('#staffText').hide();
		$('#otherMembersText').show();
		$('#otherId').val(type);
	} else {
		$('.messageTypeforOther').hide();
	}
}
/* $(document).ready(function() {
	$('#chkBoxSelectedIds-1').attr('checked', 'checked');
	$.destroyTopic('formValidateForSchoolWideMessages');
	//alert($('#msgReceiverType').val());
	$.subscribe('formValidateForSchoolWideMessages', function(event, data) {
		
	if ($('#formForSchoolWideMessages').valid()){
		$('li#CreateSMSEmail').removeClass('active');
		$('li#sendSchoolwideEmailDiv').removeClass('active');
		$('li#smsDiv').addClass('active');
	} 
	});
	$.subscribe('sendSChoolMessage', function(event, data) {
		if ($('#' + data.id).is(":hidden")) {
			$('#' + data.id).show();
		} else {
			$('#' + data.id).hide();
		}
	});
	$('.numeric').numeric( {
		allow : ","
	});
	$('.alphabet').alpha();
}); */

function validateEmail(sEmail) {
    var filter = /^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
    if (filter.test(sEmail)) {
        return true;
    }
    else {
        event.originalEvent.options.submit=false;
    }                                                                                                                                                                                                                                                            
}
$('textarea#messageDescription').keypress(function(event) {
	var keycode = (event.keyCode ? event.keyCode : event.which);
	if (keycode == '13') {
		//alert('You pressed a "enter" key in textbox');	
	}
	event.stopPropagation();
});

$(document).keypress(function(event) {
	var keycode = (event.keyCode ? event.keyCode : event.which);
	if (keycode == '13') {
		alert('You pressed a "enter" key in somewhere.Please click on submit button to send messages.');
	}
});
function staffTextChange(stafTYpe) {
	
	$('#errorMsgDivId').hide();
	$('.submitBt').removeAttr('disabled');
	
	if('VP' == stafTYpe){
		$('#principalText').show();
		$('#staffText').hide();
		$('#managementText').hide();
	}else{
	if('A' != stafTYpe && 'MGT'!= stafTYpe){
		$('#IndividualStaffs').show();
		$('#staffText').show();
		$('#principalText').hide();
		$('#managementText').hide();
	}else if('MGT'== stafTYpe){
		$('#managementText').show();
		$('#staffText').hide();
		$('#principalText').hide();
		$('#IndividualStaffs').hide();
	}else{
		$('#staffText').show();
		$('#principalText').hide();
		$('#IndividualStaffs').hide();
		$('#managementText').hide();
	}
	}
	if('A'!= stafTYpe){
	var pars = "stafTYpe=" + stafTYpe+"&eventId="+'M';
	$("div#IndividualStaffs").html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
	var url = jQuery.url.getChatURL("/common/ajaxDoSendSmsForIndividualStaff.do");
	$.ajax( {
		url : url,
		cache : false,
		data : pars,
		success : function(html) {
		$("div#IndividualStaffs").html(html);
		$("div#IndividualStaffs").show();
	}
	});
	}
}
function enableTextBox(val) {
	if ('P' == val) {
		$('#messageSalutation').show();
		$('#parentSignature').show();
		$('#DaysScholarOrHostel').show();
		$('#parentText').show();
		$('#staffText').hide();
		$('#otherMembersText').hide();
		$('#principalText').hide();
		$('.messageTypeforOther').hide();
		$('.messageTypeforStaff').hide();
		$('#parentDiv').hide();
	} else if ('S' == val) {
		$('#messageSalutation').show();
		$('#parentSignature').show();
		$('#DaysScholarOrHostel').hide();
		$('#staffText').show();
		$('#parentText').hide();
		$('#otherMembersText').hide();
		$('#DaysScholarOrHostel').hide();
		$('.messageTypeforOther').hide();
		$('.messageTypeforStaff').show();
		$('#parentDiv').hide();
	} else {
		$('#messageSalutation').hide();
		$('#staffText').hide();
		$('#principalText').hide();
		$('#parentSignature').hide();
		$('#parentText').hide();
		$('.messageTypeforOther').hide();
		$('.messageTypeforStaff').hide();
		$('#DaysScholarOrHostel').hide();
		$('#parentDiv').hide();
	}
	if ('O' == val) {
		$('.messageTypeforOther').show();
		$('.messageTypeforStaff').hide();
		$('.messageType').show();
		$('.messageType').attr('required', 'true');
		$('#parentDiv').show();
		$('#DaysScholarOrHostel').show();
		 
		changeOtherQualification('Student');
	}  
	else {
		$('.messageType').hide();
		$('.messageTo').hide();
		//$('#parentDiv').hide();
	}
	/* if ('PTA' == val) {
		$('#messageSalutation').hide();
		$('#staffText').hide();
		$('#principalText').hide();
		$('#parentSignature').hide();
		$('#parentText').hide();
		$('.messageTypeforOther').hide();
		$('.messageTypeforStaff').hide();
		$('#DaysScholarOrHostel').hide();
		$('#parentDiv').hide();
	} */  
}
$("input#mobileNumbers").click(function(){
	  $("input#uploadAttachmentses").val("");
	  $("input#uploadAttachmentses").removeClass("required");
	  $(this).addClass("required");
 });
 
 $("input#uploadAttachmentses").click(function(){
	  $("input#mobileNumbers").val("");
	  $("input#mobileNumbers").removeClass("required");
	   $(this).addClass("required");
 });
 
function getAjaxDoGetStudent(studyClassId) {
	if (studyClassId == "") {
		$("#studentList").hide();
		alert("Please select class.");
		return false;
	} else {
		var hostelStatus=$('#chkBoxClassWiseStatus').val();
		var pars = "classId=" + studyClassId+"&tempString="+'<s:property value="tempString"/>'+"&selectedId="+'<s:property value="selectedId"/>'+"&wishTitle="+hostelStatus; //selectedId means classteacher string
		//alert(pars);
		$("div#studentList").html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		var url = jQuery.url.getChatURL("/common/ajaxGetStudentListForm.do");
		$.ajax( {
			url : url,
			cache : false,
			data : pars,
			success : function(html) {
				$("div#studentList").html(html);
				$("div#studentList").show();
			}
		});
	}
}
function onlyNumbers(evt) {
	var e = evt; // for trans-browser compatibility	
	var charCode = e.which || e.keyCode;
	if(charCode = 44){
	  return true;
	}
	else if (charCode > 31 && (charCode < 48 || charCode > 57)){
		event.originalEvent.options.submit=false;
	}
}
function transportListByType(type) {
	var url="";	
	if (type == "") {
		$("#transportList").hide();
		$("#vehicletransportList").hide();
	} else {
	//if (!type == "") {
		var pars = "type=" + type + "&tempString="+'<s:property value="tempString"/>';
		if (type == "Route") {
		$("div#transportList") .html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		url = jQuery.url.getChatURL("/admin/ajaxGetTransportRouteList.do");
		$.ajax( {
			url : url,
			cache : false,
			data : pars,
			success : function(html) {
				$("div#transportList").html(html);
				$("div#transportList").show();
				$("div#vehicletransportList").html('');
			}
		});
		}else{
		$("div#vehicletransportList").html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		  url = jQuery.url.getChatURL("/admin/ajaxGetTransportVehicleList.do");
		  $.ajax( {
			url : url,
			cache : false,
			data : pars,
			success : function(html) {
				$("div#vehicletransportList").html(html);
				$("div#vehicletransportList").show();
				$("div#transportList").html('');
			}
		});
		}
	}
}
$('#messageDescriptionDiv').keyup(function() {
	var messDescription = $('#messageDescriptionDiv').val();
	if(messDescription.length > 0 ){
		$("#popupMessageDiv").removeClass('disabled');
	}else {
		$("#popupMessageDiv").addClass('disabled');
	}
});
function popupSMSPreview(id){
	var desc = $('#messageDescriptionDiv').val();
	if(isNonEmpty(desc)){
	  var pars = "tempString=" + desc;
      $.ajax( {
	        url : jQuery.url.getChatURL("/common/ajaxDoPopUpSmsPreview.do"),
			cache : true,
			data : pars,
			success : function(html) {
			$("#popupSMSPreviewDiv").html(html);
			}
		});
	}
}


/* function checkAvailableSMSCount(val) 
{
	
	$('#errorMsgDivId').hide();
	$('.submitBt').removeAttr('disabled');

	if('S' == val)
	{
		var staffVal = $('#msgStaffType').val();
		if("A" == staffVal)
		{
			val = 'SA'; // Staff All
		}
	}
	
	if('A' == val || 'P' == val || 'SA' == val)
	{
		var url = jQuery.url.getChatURL("/common/ajaxDoCheckAllSMSCount.do?tempString="+val);
		$.ajax( {
			url : url,
			cache : false,
			dataType : 'json',
				success : function(response) {
				var data= response.ErrorMsg;
				if(isNonEmpty(data)) {
					$('#errorMsgDivId').show();
		        	$('#errorMsgDivId').html(data);
		        	$('.submitBt').attr('disabled', 'disabled');
			     }
			}
		});
	}
	
} */
</script>
