<%@ include file="/common/taglibs.jsp"%>
<jsp:include page="/common/messages.jsp"></jsp:include>
<s:if test="%{studyClassList != null && !studyClassList.isEmpty()}">
<div class="form-body form-horizontal">
		<h4 class="pageTitle bold">
			<s:if test='%{user.isSchoolTransport=="N" && user.isSchoolTeacher =="Y"}'>
				Create class wide E-mail 
			</s:if>
			<s:else>
				Send E-mail
			</s:else>
		</h4>
		 <div class="form-group">
				<div class="panel-body col-md-12">
					<div class="col-md-1">
						<span class="label label-danger"> NOTE : </span>
					</div>
					<div class="col-md-10">
						<ul>
							<li>
								 You can send a text message via Email.
							</li>
							<li>
								You can attach a document to emails.
							</li>
						</ul>
					</div>
				</div>
			</div>
			<s:if test="%{(customer.contactEmail == null || customer.contactEmail.isEmpty())}"> 
				<div class="form-group">
					<div class="panel-body col-md-12">
						<div class="col-md-1">
							<span class="label label-danger"> ALERT : </span>
						</div>
						<div class="col-md-10" id="enableContactEmail">
							<div class="alert alert-danger">&nbsp;&nbsp; You have not configured from email address. So system will send emails from "messages@eazyschool.com".
							<s:if test='%{user.isSchoolAdmin == "Y"}'>
								<s:url id="urlSendContactEmailLink" action="ajaxDoSchoolFromEmailInfo" namespace="/admin" />
								<sj:a href="%{urlSendContactEmailLink}" targets="mainContentDiv" cssClass="ajaxify title CFE ">  Click Here  </sj:a> to configure from email address if you wish to process email from your email address.
							</s:if>
							<s:else>
								Please contact admin to configure from Email id.
							</s:else>
							</div>
						</div>
					</div>
				</div>
			</s:if>
	<div id="messageTypeDiv">
		<div class="form-group">
			<s:if test='%{user.isSchoolAdmin == "Y" || user.isReceptionist == "Y"}'>
			<label class="col-md-2 control-label">
				Select Type :
			</label>
			</s:if>
			<div class="col-md-8">
				<div class="radio-list">
					<s:if test='%{plTitle != "TRMessages"}'>
						<s:if
							test='%{user.isOnlySchoolTeacher!="Y" && user.isSchoolTransport=="N" && user.isOnlySchoolHod=="N"}'>
							<label class="radio-inline">
								<input type="radio" value="SchoolWideMessages" id="schoolWideId"
									onclick="changeQualification(this.value);" name="checkMessages"
									class="radio" checked="checked">
								School Wide Emails
							</label>
						</s:if>
						<s:if
							test='%{customer.checkEmailService == true || user.isSchoolAdmin == "Y"}'>
								<label class="radio-inline">
									<input type="radio" value="ClassWideMessages" id="classWideId"
										onclick="changeQualification(this.value);"
										name="checkMessages" class="radio">
									Class Wide Emails
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
							<%-- <s:else>
								<span class="label label-danger"> ALERT : </span>&nbsp;
								<b>SMS services disabled, Contact Admin to enable services</b>
							</s:else> --%>
						</s:else>
					</s:if>
					<%-- <s:if test="%{customer.transportModuleStatus == true}">
						<s:if
							test='%{user.isSchoolAdmin == "Y" || user.isSchoolTransport=="Y"  || user.isSchoolClerk=="Y" || user.isSchoolPrincipal=="Y"}'>
							<label class="radio-inline">
								<input type="radio" value="TransportMessages"
									id="transportWideId" onclick="changeQualification(this.value);"
									name="checkMessages" class="radio">
								Transport Messages
							</label>
						</s:if>
					</s:if> --%>
				</div>
			</div>
		</div>
	</div>
	<s:if test='%{user.isSchoolTransport=="N"}'>
		<div id="schoolWideDiv" style="display: block;">
			<jsp:include
				page="/WEB-INF/pages/common/messages/email/ajaxSchoolWideMessages.jsp"></jsp:include>
		</div>
	</s:if>
	
	<div id="classWideDiv" style="display: none;">
		<jsp:include
			page="/WEB-INF/pages/common/messages/email/ajaxClassWideMessages.jsp"></jsp:include>
	</div>
	<%-- <s:if test='%{user.isSchoolTransport=="Y" }'>
		<div id="transportWideDiv">
	</s:if>
	<s:elseif test='%{user.isOnlySchoolAdmin=="Y"}'>
		<div id="transportWideDiv" style="display: none;">
	</s:elseif>
	<s:else>
		<div id="transportWideDiv" style="display: none;">
	</s:else>
	<jsp:include
		page="/WEB-INF/pages/common/messages/email/ajaxTransportWideMessages.jsp"></jsp:include>
     </div>  --%>
</div>
</s:if>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>

<script type="text/javascript">
changePageTitle("Send School Wide Message");
$(document).ready(function() {
	if ($("input[name=chkBoxClassWiseStatus]:checked").length == 1) {
		$('input:checkbox[name=chkBoxClassWiseStatus]:checked').each(function() 
			{
				$('#chkBoxClassWiseStatus').val(this.value);
			});
	}		
	if($('li#adminSmsDiv').hasClass('active')){
		$('.messageSubject').hide();
	}else{
		$('.messageSubject').show();
	}
$("input:checkbox, input:radio").uniform();
$('#DaysScholarOrHostel').hide();
var isClassTeacher='<s:property value="selectedId"/>';
if(isNonEmpty(isClassTeacher)&& isClassTeacher=="classTeacher"){
	$('input#schoolWideId').removeAttr('checked');
	$('input#classWideId').attr("checked", 'checked');
	$('input#classWideId').click();
}
 $.destroyTopic('classWideStudentsMsgs');
$.subscribe('classWideStudentsMsgs', function(event, data) {
		 if($('input#classStudents').is(':checked')){
			 $('#title2').attr('disabled', 'true');
			 if(($("input[name='chkBoxClassSelectedIds']:checked").length == 0) && ($('input#classStudents').is(':checked'))){
			 		alert("Please Select at least one Class.");
					event.originalEvent.options.submit=false;
			 }else{
				 //generateOnlyClassIds();
				 return true;
			 }
			}else{
				  $('#title1').attr('disabled', 'true');
				var alertTypes = $('input[name="chkBoxSelectedAccountIds"]:checked').length;
				if (alertTypes == 0) {
					alert("Please Select at least one student.");
					event.originalEvent.options.submit=false;
				}
				 if ($('input#selectedStudents').is(':checked'))
					return true;
				else
					event.originalEvent.options.submit=false;
			}
	});
});
$('#emailOrSms').val('E');
$('#classWideemailOrSms').val('E');
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
		$('#messageDescriptionDiv').show();
		$('#messageDescriptionDiv').val("");
		$('#title').val("");
		$("body.wysihtml5").html("");
		$("iframe.wysihtml5-sandbox").contents().find("body").html('');

	} else if (staffType == 'ClassWideMessages') {
		$('#emailSubject').val("");
		$('#DaysScholarOrHostelCw').show();	
	    $("input#selectedStudents").click();
	    $('#classWideemailOrSms').val('E');
		$('#schoolWideDiv').hide();
		$('#classWideDiv').show();
		$('#clsDiv').show();
		$('textarea#messageDescriptionDiv').hide();
		$("iframe.wysihtml5-sandbox").contents().find("body").html('');
		}
	/* else if (staffType == 'TransportMessages') {
	    $('#schoolWideDiv').hide();
	    $('#classWideDiv').hide();
	    $('#DaysScholarOrHostelCw').hide();	
		$('#transportWideDiv').show();
		$('#smsContentDiv').hide();
		$('textarea#messageDescriptionDiv').hide();
		transportListByType($('#tansportTypeId').val());
	} */
	
}

/* function changeSelectedType(selectedType) {
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
	 */
	
	
function changeSelectedType(selectedType) {
	$('#DaysScholarOrHostelCw').show();	
	if (selectedType == 'SingleClassStudents') {
		$('#classesWithChkBoxes').hide();
		$('#classesInSelectBox').show();
		$('#studentList').show();
		$("#messageDescription1").val("");
	//	$("#title1").val("");
		//$("#messageSubject1").hide();
	GetSelectedListChanged();
	} else if (selectedType == 'MultipleclassStudents') {	
		$("#messageDescription1").val("");
		//$("#title1").val("");
		$('#classesInSelectBox').hide();
		$('#studentList').hide();
		$('#classesWithChkBoxes').show();
	}
}
function changeOtherQualification(type) {
	if (type == 'Student') {
		$('#messageSalutation').hide();	
		$('#parentSignature').hide();
		$('#parentDiv').show();
		$('#parentDiv').hide();
		$('#parentText').show();
		$('#staffText').hide();
		$('#principalText').hide();
		$('#otherId').val(type);
		$('#otherMembersText').hide();
	} else if (type == 'Staff') {
		$('#messageSalutation').hide();	
		$('#parentSignature').hide();
		$('#parentText').hide();
		$('#staffText').show();
		$('#otherId').val(type);
		$('#otherMembersText').hide();
	}else if (type == 'OtherMember') {
		$('#messageSalutation').hide();	
		$('#parentSignature').hide();
		$('#parentText').hide();
		$('#staffText').hide();
		$('#otherMembersText').show();
		$('#otherId').val(type);
	} else {
		$('.messageTypeforOther').hide();
	}
}
$(document).ready(function() {
	$('#chkBoxSelectedIds-1').attr('checked', 'checked');
	$.destroyTopic('formValidateForSchoolWideMessages');
	$.subscribe('formValidateForSchoolWideMessages', function(event, data) {
	if ($('#formForSchoolWideMessages').valid()){
		$('li#CreateSMSEmail').removeClass('active');
		$('li#sendSchoolwideEmailDiv').removeClass('active');
		$('li#smsDiv').addClass('active');
	}else{
		 if ('O' == $('#msgReceiverType').val()){
		var sEmail=$('#email').val();
		if(isNonEmpty(sEmail)){
			var emailList=sEmail.split(',');
			for ( var k = 0; k < emailList.length; k++) {
			var valueType = emailList[k];
			if ($.trim(valueType).length == 0) {
	            alert('Please enter email address');
	            event.originalEvent.options.submit=false;
	           // e.preventDefault();
	        }
	        if (validateEmail(valueType)) {
	            //alert('Email is valid');
	        }
	        else {
	            alert(valueType+' is an Invalid Email Address');
	           	event.originalEvent.options.submit=false;
	           // e.preventDefault();
	        }
			}
		}else{
			alert('Please enter email address');
			event.originalEvent.options.submit=false;
		 }
		} 
		else if('O' == $('#msgReceiverType').val()){
		var sEmail=$('#email').val();
		if(isNonEmpty(sEmail)){
			var emailList=sEmail.split(',');
			for ( var k = 0; k < emailList.length; k++) {
				var valueType = emailList[k];
				if ($.trim(valueType).length == 0) {
		            alert('Please enter email address');
		            event.originalEvent.options.submit=false;
		        }
		        if (validateEmail(valueType)) {
		        }
		        else {
			        if(isNonEmpty(valueType)){
			         	alert(valueType+' is an Invalid Email Address');
			         	event.originalEvent.options.submit=false;
			        }else{
			            alert('Do not give empty space in between 2 comma(s)');
			            event.originalEvent.options.submit=false;
			           }
		        }
			}
		}else{
			alert('Please enter email address');
			event.originalEvent.options.submit=false;
		 }
		}
		
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
});

function validateEmail(sEmail) {
    var filter = /^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
    if (filter.test(sEmail)) {
        return true;
    }
    else {
        event.originalEvent.options.submit=false;
    }
}
/* $('textarea#messageDescription').keypress(function(event) {
	var keycode = (event.keyCode ? event.keyCode : event.which);
	if (keycode == '13') {
	}
	event.stopPropagation();
}); */

$(document) .keypress( function(event) {
	var keycode = (event.keyCode ? event.keyCode : event.which);
	if (keycode == '13') {
		alert('You pressed a "enter" key in somewhere.Please click on submit button to send messages.');
	}
});
 
function staffTextChange(stafTYpe) {
	if('VP' == stafTYpe){
		$('#principalText').show();
		$('#staffText').hide();
	}else{
	if('A' != stafTYpe){
		$('#IndividualStaffs').show();
		$('#staffText').show();
		$('#principalText').hide();
	}else{
		$('#staffText').show();
		$('#principalText').hide();
		$('#IndividualStaffs').hide();
	}
	}
	if('A'!= stafTYpe){
	var pars = "stafTYpe=" + stafTYpe;
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
		$('#messageSalutation').hide();
		$('#parentSignature').hide();
		$('#DaysScholarOrHostel').show();
		$('#parentText').show();
		$('#staffText').hide();
		$('#otherMembersText').hide();
		$('#principalText').hide();
		$('.messageTypeforOther').hide();
		$('.messageTypeforStaff').hide();
	} else if ('S' == val) {
		$('#messageSalutation').hide();
		$('#parentSignature').hide();
		$('#DaysScholarOrHostel').hide();
		$('#staffText').show();
		$('#parentText').hide();
		$('#otherMembersText').hide();
		$('#DaysScholarOrHostel').hide();
		$('.messageTypeforOther').hide();
		$('.messageTypeforStaff').show();
	} else {
		//$('#messageSalutation').hide();
		$('#staffText').hide();
		$('#principalText').hide();
		$('#parentSignature').hide();
		$('#parentText').hide();
		$('.messageTypeforOther').hide();
		$('.messageTypeforStaff').hide();
		$('#DaysScholarOrHostel').hide();
	}
	if ('O' == val) {
		$('.messageTypeforOther').show();
		$('.messageTypeforStaff').hide();
		$('.messageTo').show();
		$('.messageType').hide();
		$('.messageType').attr('required', 'false');
		$('#parentDiv').hide();
		changeOtherQualification('Student');
	} else {
		$('.messageType').hide();
		$('.messageTo').hide();
		$('#parentDiv').hide();
	}
}
/* $("input#mobileNumbers").click(function(){
	  $("input#uploadAttachmentses").val("");
	  $("input#uploadAttachmentses").removeClass("required");
	  $(this).addClass("required");
 });

 $("input#uploadAttachmentses").click(function(){
	  $("input#mobileNumbers").val("");
	  $("input#mobileNumbers").removeClass("required");
	   $(this).addClass("required");
 });
 */
 
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
$('div#enableContactEmail').click(function() {
	window.location.hash = "target=ASMS.ajaxify ASMEL";
	$('li#adminSms').parents('li').removeClass('open active');
	$('li#adminSms').removeClass('active');
	$('li#schoolSettingsDiv').addClass('open active');
	$('#contactEmail').addClass('active');
	$('li#urlSchoolFromEmailInfo a').click();
});
/* function transportListByType(type) {
	var url="";	
	if (type == "") {
		$("#transportList").hide();
		$("#vehicletransportList").hide();
	} else {
	//if (!type == "") {
		var pars = "type=" + type + "&tempString="+'<s:property value="tempString"/>';
		if (type == "Route") {
		$("div#transportList").html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
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
} */
</script>
