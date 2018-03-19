<%@ include file="/common/taglibs.jsp"%>
<jsp:include page="/common/messages.jsp"></jsp:include>
<div class="form-body form-horizontal">
	<h4 class="pageTitle bold">
		Send Login Credentials
	</h4>
	<div class="form-group">
		<div class="panel-body col-md-12">
			<div class="col-md-1">
				<span class="label label-danger"> NOTE : </span>
			</div>
			<div class="col-md-10">
				<ul>
					<li>
						 You can send the updated credentials through Sms and Email at anytime.
					</li>
				</ul>
			</div>
		</div>
	</div>
	<s:if test='%{user.isSchoolAsstStaff=="N"}'>
		<s:if test="%{(smsAlloted == 0) || (smsAlloted <= smsCnt)}">
			<div style="color: red;" class="alert alert-info">
					You have been used all your allotted
					<s:property value="smsAlloted" />
					SMS. Please contact EazySchool support team (080-46620999) to recharge your SMS.
			</div>
		</s:if>	
	</s:if>
	
  <s:form action="ajaxSendLoginCredentialsDetails" theme="simple" id="formForSchoolWideMessages" cssClass="form-horizontal" namespace="/common">
		    <jsp:include page="/WEB-INF/pages/common/messages/selectMessageType.jsp" />
		<!--<s:if test='%{user.isSchoolAdmin == "Y"}'>
			<div class="form-group">
				<label class="control-label col-md-2">
					Resetting password :
				</label>
				<div class="col-md-3">
					<p class="form-control-static">
						<s:checkbox name="plTitle" id="resettingPassword" />
					</p>
				</div>
			</div>
		</s:if> -->
		
		<div class="form-group">
			<label class="control-label col-md-2"> To :</label>
			<div class="col-md-3">
				 <s:select name="messages.receiverType"
					 cssClass="form-control required input-medium"
					  id="msgReceiverType"
					list="#{'N':'Non-Teaching Staff','S':'Teaching Staff','P':'Parent','ST':'Student'}"
					onchange="javascript:enableTextBox(this.value);"></s:select>
			</div>
		</div>
		<div class="form-group">
				<label class="col-md-2 control-label">
					Select Type :
				</label>
				<div class="col-md-9">
					<div class="radio-list">
						<label class="radio-inline">
							<input type="radio" name="selectType" value="A"
								onclick="getSearchUsersList(this.value);"
								checked="checked">
							Select All Users
						</label>
						<label class="radio-inline">
							<input type="radio" name="selectType" value="SU"  id="checkSearchBox"
								onclick="getSearchUsersList(this.value);" class="radio">
							Search Users
						</label>
					</div>
				</div>
		</div>
		
		<div id="searchUsersResultDiv"></div>
		<div id="usersListDiv"></div>
		<div id="classListDiv"></div>
		
		<div id="errorMsgDivId"></div>
		
		<div class="form-actions fluid" id="checkService">
			<div class="col-md-offset-2 col-md-5">
				<s:if
					test='%{customer.checkMobileService == true || user.isSchoolAdmin == "Y"}'>
					<sj:submit cssClass="submitBt btn blue" value="Submit"
						targets="schoolWideMessagesHomeDiv" 
						formIds="formForSchoolWideMessages" validate="true"
						onBeforeTopics="formValidateForSendLoginCredentials" />
					<s:url id="urlInboxesDetails"
						action="ajaxDoGetSchoolWideMessagesList" namespace="/common"
						includeParams="all" escapeAmp="false">
						<s:param value="#session.academicYear" name="academicYearId" />
					</s:url>
					<sj:a href="%{urlInboxesDetails}" targets="schoolWideMessagesHomeDiv"
						cssClass="btn default">Cancel</sj:a>
						</s:if>
			</div>
		</div>
	</s:form>
	</div>
<script language="JavaScript" type="text/javascript">
$(document).ready(function() {
	if((<s:property value="smsAlloted"/> != 0) && (<s:property value="smsAlloted"/>) <= (<s:property value="smsCnt"/>)){
		alert('You have been used all your allotted '+ <s:property value="smsAlloted" />+' SMS. Please contact EazySchool support team (080-46620999) to recharge your SMS.');
	}
$.destroyTopic('formValidateForSendLoginCredentials');
	$("input:checkbox, input:radio").uniform();
	var anyTitle=$('#msgReceiverType').val();
	enableTextBox(anyTitle);
	$.subscribe('formValidateForSendLoginCredentials', function(event, data) {
		if($("input[name=selectType]:checked").val()=="A"){
		var selectedChkCount = $("input[name=chkBoxSelectedAccountIds]:checked").length;
		if(selectedChkCount > 0){
			return true;
		}
		else{
			alert('Please select at least one user.');
			event.originalEvent.options.submit=false;
		}
	}else if($("input[name=selectType]:checked").val()=="SU"){
		if($('.accountIds').val() != "[]"){
				return true;
		}else{
			alert("Please search and select at least one user.");
			 event.originalEvent.options.submit=false;	
		}
	}
  });
});
function getSearchUsersList(searchValue) {
if(searchValue=="SU"){
var anyTitle=$('#msgReceiverType').val();
		var pars = "plTitle="+anyTitle;
		$("#searchUsersResultDiv") .html( '<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		var url = jQuery.url.getChatURL("/common/ajaxUsersList.do");
		$.ajax( {
			url : url,
			cache : false,
			data : pars,
			success : function(html) {
				$("#usersListDiv").hide();
				$("#classListDiv").hide();
				$("#searchUsersResultDiv").html(html);
			}
		});
}else{
	enableTextBox(anyTitle);
	$("#searchUsersResultDiv").html('');
	$("#usersListDiv").show();
	$("#classListDiv").show();
   }
}
 
function enableTextBox(anyTitle) {
var url ='';
	if(isNonEmpty(anyTitle && anyTitle != 0)){
		if(anyTitle=="ST"){
		   url = jQuery.url.getChatURL("/student/ajaxStudentStudyClassList.do");
		}else{
		 	$("#classListDiv").hide();
		   url = jQuery.url.getChatURL("/common/ajaxDoSendLoginCredentials.do");
		}
		$("#usersListDiv") .html( '<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		var pars = "anyTitle=" + anyTitle;
			  	$.ajax( {
				url : url,
				cache : false,
				data : pars,
				success : function(html) {
					$("#usersListDiv").html(html);
				}
			});
	}
	if ($("input#checkSearchBox").is(':checked')){
		$("input#checkSearchBox").click();
	}
}
function checkMessageType(){
 if($("input[name=chkBoxSelectedIds]:checked").length == 0){
 	$("p#chkMobileEmailMsg").html('Please select at least one alert type to send sms or email.');
 	$('#checkService').hide();
 }
 else{
 $("p#chkMobileEmailMsg").html('');
 $('#checkService').show();
 }
}
</script>
