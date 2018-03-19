<%@ include file="/common/taglibs.jsp"%>
<jsp:include page="/common/messages.jsp"></jsp:include>
<div class="form-body form-horizontal">
	<h4 class="pageTitle bold">
		Send SMS and E-mail 
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
						More than 160 character SMS content will be treated as two SMS and updated as 2 SMS count.
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
					SMS(s). Please contact eazyschool supporting team (080-46620999) to recharge your SMS(s).
			</div>
		</s:if>	
	</s:if>
  <s:form action="ajaxSendSchoolWideMessages" theme="simple" id="formForSchoolWideMessages" cssClass="form-horizontal" namespace="/common">
  		<input type="hidden" name="chkBoxSelectedIds" id="emailOrSms"/>
		<div class="form-group">
			<label class="control-label col-md-2"> To :</label>
			<div class="col-md-3">
				 <s:select name="messages.receiverType"
					 cssClass="form-control required input-medium"
					  id="msgReceiverType"
					list="#{'A':'Staff','O':'Other'}"
					onchange="javascript:enableTextBox(this.value);"></s:select>
			</div>
		</div>
		<div id="usersListDiv"></div>
		<div class="row">
			<div class="messageType col-md-6" >
				<div class="form-group">
					<label class="control-label col-md-4">
						<span class="required">*</span>Mobile Numbers :
					</label>
					<div class="col-md-6">
						<sj:textfield name="messages.messageType" id="mobileNumbers"
							size="80" cssClass="numeric form-control input-medium required"
							></sj:textfield>
						<span class='hintMessage'>(Don't add '0' in front of the
							number.Please separate mobile numbers by comma.)</span>
					</div>
				</div>
			</div>
			<div id="parentDiv"  class="col-md-6 browse">
			<div class="form-group">
				<a	href="${pageContext.request.contextPath}/userFiles/DownloadSmsTemplate/SendBulkSmsSheet.xls"
						class="col-md-2 btn btn-xs purple" title="Download"><i class="fa fa-edit"></i>Download</a>
					<label class="control-label col-md-3">
					<span class="required">*</span>Import Mobile Numbers (.xls) :
					</label>
					<div class="col-md-2">
						<s:file name="upload" id="uploadAttachmentses" cssClass="btn default required"
							multiple="multiple"></s:file>
					</div>
				</div>
			</div>
	 	</div>
		<div id="messageSalutation" >
			<div class="form-group">
				<label class="control-label col-md-2">
					<span class="required">*</span>Message Salutation :
				</label>
				<div class="col-md-3">
					<div id="staffText" style="display: block;">
						<sj:textfield name="" value="Dear Staff" id="messageDescription"
							rows="3" readonly="true"
							cssClass="form-control input-medium required"></sj:textfield>
					</div>
					<div id="otherMembersText" style="display: none;" >
						<div class="input-group">
							<span class="input-group-addon">
								Dear
							</span>
							<sj:textfield name="wishTitle" value=""
							id="messageDescription"
							cssClass="required form-control input-medium"></sj:textfield>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div id="smsContentDiv">
			<div class="form-group">
				<label class="control-label col-md-2">
					<span class="required">*</span>SMS Text :
				</label>
				<div class="col-md-6">
					<sj:textarea name="messages.messageDescription"
						id="messageDescription" rows="3" maxCharsData="1000" 
						cssClass="required smsWord_count form-control" cols="20"></sj:textarea>
						<span class="smsCounter"></span>
				</div>
			</div>
		</div>
		<div id="parentSignature" >
			<div class="form-group">
				<label class="control-label col-md-2">
					<span class="required">*</span>Message Signature :
				</label>
				<div class="col-md-3">
					<sj:textarea value="Thank you from" id="messageDescription"
						rows="3" readonly="true" cssClass="form-control required"></sj:textarea>
				</div>
			</div>
		</div>
		<div class="form-actions fluid" id="checkService">
			<div class="col-md-offset-2 col-md-5">
					<sj:submit cssClass="submitBt btn blue" value="Submit"
						targets="mainContentDiv" 
						formIds="formForSchoolWideMessages" validate="true"
						onBeforeTopics="formValidateForSendLoginCredentials" />
					<s:url id="urlInboxesDetails"
						action="ajaxDoGetChairMessagesList" namespace="/common"
						includeParams="all" escapeAmp="false">
						<s:param value="#session.academicYear" name="academicYearId" />
					</s:url>
					<sj:a href="%{urlInboxesDetails}" targets="mainContentDiv"
						cssClass="btn default">Cancel</sj:a>
			</div>
		</div>
	</s:form>
	</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/subscription/textcounter.js"></script>
<script language="JavaScript" type="text/javascript">
$(document).ready(function() {
	//var test =$('input[name=chkBoxSelectedAccountIds]').val();
	if((<s:property value="smsAlloted"/> != 0) && (<s:property value="smsAlloted"/>) <= (<s:property value="smsCnt"/>)){
		alert('You have been used all your allotted '+ <s:property value="smsAlloted" />+' SMS(s). Please contact eazyschool supporting team (080-46620999) to recharge your SMS(s).');
	}
$.destroyTopic('formValidateForSendLoginCredentials');
	$("input:checkbox, input:radio").uniform();
	var anyTitle=$('#msgReceiverType').val();
	enableTextBox(anyTitle);
	 var selectedValue ="S";
	$('#msgReceiverType').change(function() {
		  selectedValue = $(this).val();
	 });
	$.subscribe('formValidateForSendLoginCredentials', function(event, data) {
		if ($('#formForSchoolWideMessages').valid()) {
			 if(selectedValue == "S"){
				 if($("input[name='chkBoxSelectedAccountIds']:checked").length == 0){
				 		alert("Please Select at least one staff.");
				 		event.originalEvent.options.submit=false;
				 }else{
					 return true;
				 }
			  } 
		 } 
  });
	$('#emailOrSms').val('M');
});
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
function enableTextBox(anyTitle) {
	if(isNonEmpty(anyTitle && anyTitle != 0)){
		if(anyTitle!="O"){
			$("#usersListDiv").show();
			$('#otherMembersText').hide();
			$(".messageTo").hide();
			$(".messageType ").hide();
			$("#parentDiv").hide();
			$("#staffText").show();
		  var url = jQuery.url.getChatURL("/common/ajaxDoSendLoginCredentials.do");
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
		}else{
			$("#usersListDiv").hide();
			$('.messageTo').show();
			$('.messageSubject').show();
			$('#parentSignature').show();
			$('#otherMembersText').show();
			$("#parentDiv").show();
			$(".messageType ").show();
			$("#staffText").hide();
		}
	}
}
</script>
