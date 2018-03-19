<%@ include file="/common/taglibs.jsp"%>
<s:if
	test="%{customer.checkMobileService == false}">
	<div class="grid_13 noteFont">
		<span class="label label-danger"> ALERT ! </span>&nbsp;
		Your SMS service is disabled 
		<s:if test='%{user.isSchoolAdmin == "Y"}'>
			 <s:url	id="urlManageSchool" action="ajaxDoSchoolInformation"
				namespace="/admin" />
			 <sj:a href="%{urlManageSchool}" cssClass="enableEmailService"
				targets="mainContentDiv">Click Here</sj:a>to enable services. 
		</s:if>
	</div>
</s:if>
<s:else>
	<s:form action="ajaxSendSchoolWideMessages" theme="simple" method="post"
		id="formForSchoolWideMessages" cssClass="form-horizontal" enctype="multipart/form-data"
		namespace="/common">
		<input type="hidden" name="anyTitle" id="checkStatus"/>
		<input type="hidden" name="chkBoxSelectedIds" id="emailOrSms"/>
		<s:if test='%{tempString == "stepTRMessage"}'>
			<input type="hidden" value="TR" name="status" />
		</s:if>
		<s:if test='%{plTitle != "TRMessages"}'>
			<s:if test='%{user.isOnlySchoolTeacher!="Y"}'>
				<%-- <div id="selectMsgType">
					<jsp:include
						page="/WEB-INF/pages/common/messages/selectMessageType.jsp"><jsp:param
							value="C" name="clsmsg" /></jsp:include>
				</div> --%>
				<div id="toDiv">
					<div class="form-group">
						<label class="control-label col-md-2">
							To :
						</label>
						<div class="col-md-3">
							<s:if test="%{tempList != null && !tempList.isEmpty()}">
								<s:select name="messages.receiverType"
								cssClass="form-control required input-medium"
								id="msgReceiverType"
								list="#{'A':'ALL','P':'Parents Only','S':'Staff Only','O':'Other','PTA':'PTA Only'}"
								onchange="javascript:enableTextBox(this.value);"></s:select><!-- checkAvailableSMSCount(this.value); -->
							</s:if>
							<s:else>
								<s:select name="messages.receiverType"
								cssClass="form-control required input-medium"
								id="msgReceiverType"
								list="#{'A':'ALL','P':'Parents Only','S':'Staff Only','O':'Other'}"
								onchange="javascript:enableTextBox(this.value);checkAvailableSMSCount(this.value);"></s:select><!-- checkAvailableSMSCount(this.value); -->
							</s:else>
						</div>
					</div>
				</div>
				<div class="messageTypeforStaff" style="display: none;">
					<div class="form-group">
						<label class="control-label col-md-2"></label>
						<div class="col-md-3">
							<s:select name="messages.staffType" onchange="javascript:staffTextChange(this.value);" cssClass="form-control required input-medium" id="msgStaffType"
								list="#{'A':'ALL','T':'Teaching','NT':'Non-Teaching','CT':'Class Teacher','VP':'Principal','MGT':'Management'}"></s:select>
						</div>
					</div>
					<div id="IndividualStaffs" style="display: none;">
						<jsp:include page="/WEB-INF/pages/common/messages/IndividualStaffsList.jsp"></jsp:include>
					</div>
				</div>
				<div class="messageTypeforOther" style="display: none;">
					<input type="hidden" value="" name="messages.otherType"
						class="otherId" id="otherId" />
					<div class="form-group">
						<label class="col-md-2 control-label"></label>
						<div class="col-md-9">
							<div class="radio-list">
								<s:if test='%{user.isOnlySchoolTeacher!="Y"}'>
									<label class="radio-inline">
										<input type="radio" value="Student" id="studentWideId"
											onclick="changeOtherQualification(this.value);"
											name="studentWidemsg" class="radio" checked="checked">
										Student
									</label>
								</s:if>
								<label class="radio-inline">
									<input type="radio" value="Staff" id="staffWideId"
										onclick="changeOtherQualification(this.value);"
										name="studentWidemsg" class="radio">
									Staff
								</label>
								<label class="radio-inline">
									<input type="radio" value="OtherMember" id="otherMemberWideId" 
										onclick="changeOtherQualification(this.value);"
										name="studentWidemsg" class="radio">
										Other Members
								</label>
							</div>
						</div>
					</div>
				</div>
				<div class="form-group" id="DaysScholarOrHostel" style="display: none;">
					<s:if test="%{customer.hostelModuleStatus}">
					<label class="col-md-2 control-label">
						Select :
					</label>
					<div class="col-md-9">
						<div class="checkbox-list">
								<div id="DaysScholar">
									<label class="checkbox-inline col-md-2">
										 <input type="checkbox" name="chkBoxStatus" id="chkBoxStatus" value="D"> 
										Days Scholar
									</label>
								 </div>
								<div  id="Hostler">
									<label class="checkbox-inline col-md-2">
										 <input type="checkbox" name="chkBoxStatus" id="chkBoxStatus" value="H" />  
										Hostler
									</label>
								</div>
							
						</div>
					</div>
					</s:if>
				</div>
				
				<div class="row">
				<div class="messageType col-md-6" style="display: none;">
					<div class="form-group">
						<label class="control-label col-md-4">
							<span class="required">*</span>Mobile Numbers :
						</label>
						<div class="col-md-6">
							<sj:textfield name="messages.messageType" id="mobileNumbers"
								size="80" cssClass="numeric form-control input-medium required"
								onchange="javascript:validateNumber(this.value)">
							</sj:textfield>
							<span class='hintMessage'>(Don't add '0' in front of the
								number.Please separate mobile numbers by comma.)</span>
						</div>
					</div>
				</div>
				<div id="parentDiv" style="display: none;" class="col-md-6 browse">
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
				<div class="messageTo" style="display: none;">
					<div class="form-group">
						<label class="control-label col-md-2">
							<span class="required">*</span>Email Address :
						</label>
						<div class="col-md-3">
							<sj:textfield name="messages.emailIds" id="email" size="40"
								cssClass="form-control input-medium required" maxlength="80"></sj:textfield>
							<span class='hintMessage'>(Please separate email's by
								comma.)</span>
						</div>
					</div>
				</div>
				<div id="messageSalutation" style="display: none;">
					<div class="form-group">
						<label class="control-label col-md-2">
							<span class="required">*</span>Message Salutation :
						</label>
						<div class="col-md-3">
							<div id="staffText" style="display: none;">
								<sj:textfield name="" value="Dear Staff" id="messageDescription"
									rows="3" readonly="true"
									cssClass="form-control input-medium required"></sj:textfield>
							</div>
							<div id="parentText" style="display: none;">
								<sj:textfield name="" value="Dear Parent"
									id="messageDescription" readonly="true"
									cssClass="form-control required input-medium"></sj:textfield>
							</div>
							<div id="principalText" style="display: none;">
								<sj:textfield name="" value="Dear Principal" id="messageDescription"
									rows="3" readonly="true"
									cssClass="form-control input-medium required"></sj:textfield>
							</div>
							<div id="managementText" style="display: none;">
								<sj:textfield name="" value="Dear Management" id="messageDescription"
									rows="3" readonly="true"
									cssClass="form-control input-medium required"></sj:textfield>
							</div>
							<div id="otherMembersText" style="display: none;">
								<div class="input-group">
									<span class="input-group-addon">
										Dear
									</span>
									<sj:textfield name="wishTitle" value=""
									id="messageDescription"
									cssClass="form-control required input-medium"></sj:textfield>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div id="smsContentDiv" style="display: block;">
					<div class="form-group">
						<label class="control-label col-md-2">
							<span class="required">*</span>SMS Text :
						</label>
						<div class="col-md-6">
							<sj:textarea name="messages.messageDescription"
								id="messageDescriptionDiv" rows="3"  maxCharsData="1000"
								cssClass="smsWord_count form-control required" cols="20"></sj:textarea>
								<span class="smsCounter"></span>
						</div>
					</div>
				</div>
				<div class="messageSubject" style="display: none;">
					<div class="form-group">
						<label class="control-label col-md-2">
							E-Mail Subject :
						</label>
						<div class="col-md-2">
							<sj:textfield name="messages.title" id="title" size="40"
								labelposition="top" cssClass="form-control input-medium"
								maxlength="80"></sj:textfield>
						</div>
					</div>
					<div class="form-group" id="messageContDiv">
						<label class="control-label col-md-2">
							<span class="required">*</span>E-Mail Content :
						</label>
						<div class="col-md-10">
							<sj:textarea name="messages.emailContent" id="emailDescription"
								rows="7" cssClass="wysihtml5 form-control required messagesArea1" cols="20"></sj:textarea>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-2">
							Upload Documents :
						</label>
						<div class="col-md-2">
							<s:file name="fileUpload" id="uploadAttachments"
								multiple="multiple"></s:file>
						</div>
					</div>
				</div>
				<div id="parentSignature" style="display: none;">
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
			</s:if>
			
			<div id="errorMsgDivId"></div>
			
			<div class="form-actions fluid">
				<div class="col-md-offset-2 col-md-5">
					<s:if test='%{selectedId == "classTeacher"}'>
						<a data-toggle="modal" href="#popupSMSPreviewDiv" class="submitBt btn blue messagDesc" id="popupMessageDiv"  onclick="javascript:popupSMSPreview(this.value);" > Preview </a>
						<sj:submit cssClass="submitBt btn blue" value="Submit"
							targets="schoolWideMessagesHomeDiv" indicator="indicator"
							formIds="formForSchoolWideMessages" validate="true"
							onBeforeTopics="formValidateForSchoolWideMessages" />
						<s:url id="urlInboxesDetails"
							action="ajaxDoGetClassWideMessagesList" namespace="/common"
							includeParams="all" escapeAmp="false">
							<s:param value="#session.academicYear" name="academicYearId" />
						</s:url>
						<sj:a href="%{urlInboxesDetails}" targets="schoolWideMessagesHomeDiv"
							cssClass="btn default">Cancel</sj:a>
					</s:if>
					<s:else>
						<s:if
							test='%{customer.checkMobileService == true || user.isSchoolAdmin == "Y"}'>
							<a data-toggle="modal" href="#popupSMSPreviewDiv" id="popupMessageDiv" class="submitBt btn blue messagDesc"  onclick="javascript:popupSMSPreview(this.value);"> Preview </a>
							<sj:submit cssClass="submitBt btn blue" value="Submit"
								targets="schoolWideMessagesHomeDiv" indicator="indicator"
								formIds="formForSchoolWideMessages" validate="true"
								onBeforeTopics="formValidateForSchoolWideMessages" />
						</s:if>
						<s:if
							test='%{user.isSchoolTransport=="N" && user.isOnlySchoolTeacher =="Y"}'>
							
							<s:url id="urlInboxesDetails"
								action="ajaxDoGetSchoolWideMessagesList" namespace="/common"
								includeParams="all" escapeAmp="false">
								<s:param value="#session.academicYear" name="academicYearId" />
							</s:url>
							<sj:a href="%{urlInboxesDetails}" targets="schoolWideMessagesHomeDiv"
								cssClass="btn default">
												Cancel</sj:a>
						</s:if>
						<s:else>
							<s:url id="urlInboxesDetails"
								action="ajaxDoGetSchoolWideMessagesList" namespace="/common"
								includeParams="all" escapeAmp="false">
								<s:param value="#session.academicYear" name="academicYearId" />
							</s:url>
							<sj:a href="%{urlInboxesDetails}" targets="schoolWideMessagesHomeDiv"
								cssClass="btn default">
								Cancel</sj:a>
						</s:else>
					</s:else>
				</div>
			</div>
		</s:if>
	</s:form>
	<div id="popupSMSPreviewDiv"></div>
</s:else>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/subscription/textcounter.js"></script>
<script language="JavaScript" type="text/javascript">

function validateNumber(mobileNo) {
	var  mb=mobileNo.split(",");
	for(var i=0;i<mb.length;i++){
		if((mb[i].length != 10)){
			    $('#mobileNumbers').val('');
			    alert("Please enter a valid mobile number.");
			    return false;
				}
		  }
	 }
		 
$(document).ready(function() {
	$("#popupMessageDiv").addClass('disabled');
	$('li#smsDiv').removeClass('active');
	$('li#trDiv').removeClass('active');
	$.destroyTopic('formValidateForSchoolWideMessages');
	$.subscribe('formValidateForSchoolWideMessages', function(event, data) {
		$('.submitBt').attr('disabled', 'disabled');
	var alertTypes = $('input[name="chkBoxSelectedAccountIds"]:checked').length;
	if (alertTypes == 0) {
		alert("Please Select at least one staff.asdfasf");
		$('.submitBt').removeAttr('disabled');
		event.originalEvent.options.submit=false;
	}

		var mobileNumbersList = $('#mobileNumbers').val();
		alert(mobileNumbersList);
		if(isNonEmpty(mobileNumbersList))
		{
			//mobileNumbersList.split(",")
			
			alert(mobileNumbersList.length);
			
			$('.submitBt').removeAttr('disabled');
			event.originalEvent.options.submit=false;
		}
		/* else
		{
			return true;
		} */
	
	 	if ($('input#selectedStudents').is(':checked'))
 		{
	 		return true;
 		}
		else
		{
			$('.submitBt').removeAttr('disabled');
			event.originalEvent.options.submit=false;
		}
		
	});
});
function getAjaxGetFeeSettingParticular(feeSettingId) {
	var tempId=null;
	var tempString="feeParticulars";
		if(isNonEmpty(feeSettingId)){
			var pars = "tempId=" + feeSettingId +"&tempString="+tempString;
		$("#allFeeSettingParticular").html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		var url = jQuery.url.getChatURL("/schoolfee/ajaxSchoolFeeSettingParticular.do");
		$.ajax( {
			url : url,
			cache : false,
			data : pars,
			success : function(html) {
				$("#allFeeSettingParticular").html(html);
			}
		});
		}
	}

$('.messagesArea1').wysihtml5({
    "font-styles": false, //Font styling, e.g. h1, h2, etc. Default true
    "emphasis": true, //Italics, bold, etc. Default true
    "lists": true, //(Un)ordered lists, e.g. Bullets, Numbers. Default true
   	"html": true, //Button which allows you to edit the generated HTML. Default false
    "link": true, //Button to insert a link. Default true
    "image": true, //Button to insert an image. Default true,
    "color": false //Button to change color of font  
});
$('a.enableEmailService').click(function(){
	window.location.hash="target=ES.ajaxify AMCS";
	window.location.reload();
});
</script>