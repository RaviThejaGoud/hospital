<%@ include file="/common/taglibs.jsp"%>
<s:if test="%{studentsList != null && !studentsList.isEmpty()}">
<s:hidden value="%{selectedId}" name="selectedId"></s:hidden>
	<div class="col-md-12">
		<div class="col-md-3" style="width: 14%;">&nbsp;</div>
		<div class="col-md-9" style="overflow-y:scroll;height: 350px;border: 1px solid ActiveCaption;margin-left: 18px;">
			<table class="table table-striped table-bordered">
				<thead>
					<tr>
						<th>
							Student Name 
						</th>
						 <th>
							Mobile Number
						</th>
						<th>
							Email Id
						</th>
						<th>
							<div class="checkbox">
								Select All
								<label>
									<input type="checkbox" name="selectAll" value=''
										class="allClasses" id="selectAllIds" onclick="checkAll();" />
								</label>
							</div>
						</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="studentsList">
						<tr>
							<td>
								<s:property value="firstName" />
								&nbsp;
								<s:property value="lastName" />
							</td>
							<td>
								<s:if test="%{mobileNumber != null && !mobileNumber.isEmpty()}">
									<s:property value="mobileNumber" />
								</s:if>	
								<s:else>
									<strong> - </strong>
								</s:else>	
							</td>
							<td>
								<s:if test="%{parentEmail != null && !parentEmail.isEmpty()}">
									<s:property value="parentEmail" />
								</s:if>
								<s:else>
									<strong> - </strong>
								</s:else>	
							</td> 
							<td>
								<div class="checkbox">
									<label>
										<input type="checkbox" name="chkBoxSelectedAccountIds"
											value='<s:property value="accountId" />' id="checkbox">
									</label>
								</div>
							</td>
						</tr>
					</s:iterator>
					</tbody>
				</table>
		</div>
	</div>
	&nbsp;
	&nbsp;
	<%-- <div class="messageSubject2" id="sendEmailDiv" style="display: none;">
		<div class="form-group">
			<label class="control-label col-md-2">
				<span class="required">*</span>Email Subject :
			</label>
			<div class="col-md-3">
				<sj:textfield name="messages.title" id="title1" size="40"
					labelposition="left" cssClass="required form-control "
					maxlength="80"></sj:textfield>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-md-2">
				<span class="required">*</span>E-Mail Content :
			</label>
			<div class="col-md-9">
				<sj:textarea name="messages.emailContent" id="messageDescriptions"
					cssClass="wysihtml5 form-control required messagesArea2" rows="7"
					cols="20"></sj:textarea>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-md-2">
				Upload Documents :
			</label>
			<div class="col-md-9">
				<s:file type="file" name="fileUpload" cssClass="multi"></s:file>
			</div>
		</div>
	</div> --%>
	<div class="clearfix"></div>
	<%-- <div class="form-group" id="smsContentDiv">
		<label class="control-label col-md-2">
			<span class="required">*</span>SMS Text :
		</label>
		<div class="col-md-6">
			<sj:textarea name="messages.messageDescription"
				id="messageDescription2" rows="3" maxCharsData="1000" 
				cssClass="smsWord_count form-control required " cols="20"></sj:textarea>
				<span class="smsCounter"></span>
		</div>
	</div>
	<div class="clearfix"></div>
	<div class="form-actions fluid">
		<div class="col-md-offset-2 col-md-5">
			<s:if test='%{selectedId == "classTeacher"}'>
			<a data-toggle="modal" href="#popupSMSPreviewDiv1" class="submitBt btn blue" id="popupMesage" onclick="javascript:popupClassWiseSMSPreview(this.value);" > Preview </a>
				 <sj:submit cssClass="submitBt btn blue" value="Submit"
					targets="mainContentDiv" onBeforeTopics="classWideStudentsMsgs" formIds="addClassWideMessage"
					validate="true" />
				<s:url id="classTeacherDetail"
					action="ajaxDoGetClassWideMessagesList" namespace="/common"
					includeParams="all" escapeAmp="false">
					<s:param value="#session.academicYear" name="academicYearId" />
				</s:url>
				<sj:a href="%{classTeacherDetail}" targets="mainContentDiv"
					cssClass="btn default"> Cancel </sj:a>
			</s:if>
			<s:else>
			<span id="smsPreviewDiv">
				<a data-toggle="modal" href="#popupSMSPreviewDiv1" class="submitBt btn blue" id="popupMesage" onclick="javascript:popupClassWiseSMSPreview(this.value);" > Preview </a>
			</span>
				<sj:submit cssClass="submitBt btn blue" value="Submit"
					targets="schoolWideMessagesHomeDiv"
					onBeforeTopics="classWideStudentsMsgs" formIds="addClassWideMessage"
					validate="true" />
			<s:if test='%{user.isOnlySchoolTeacher =="Y"}'>
			<a data-toggle="modal" href="#popupSMSPreviewDiv1" class="submitBt btn blue" id="popupMesage" onclick="javascript:popupClassWiseSMSPreview(this.value);" > Preview </a>
				<s:url id="urlInboxesDetails"
					action="ajaxDoGetSchoolWideMessagesList" namespace="/common"
					includeParams="all" escapeAmp="false">
					<s:param value="#session.academicYear" name="academicYearId" />
				</s:url>
				<sj:a href="%{urlInboxesDetails}" targets="mainContentDiv"
					cssClass="btn default">Cancel</sj:a>
			</s:if>
			<s:else>
				<s:url id="urlInboxesDetails"
					action="ajaxDoGetSchoolWideMessagesList" namespace="/common"
					includeParams="all" escapeAmp="false">
					<s:param value="#session.academicYear" name="academicYearId" />
				</s:url>
				<sj:a href="%{urlInboxesDetails}" targets="mainContentDiv"
					cssClass="btn default">Cancel</sj:a>
			</s:else>
			</s:else>
		</div>
	</div> --%>
</s:if>
<s:else>
	<div class="alert alert-info">
		Oops! No Students found for the selected class & section in the system.
	</div>
</s:else>
<script type="text/javascript">
changePageTitle("Send Class Wide Message");
$(document).ready(function() {
	$("input[type=file].multi").MultiFile();
	FormAdvanced.init();
	FormComponents.init();
	$("input:checkbox, input:radio").uniform();
	if($('li#classTeacherEmailDiv').hasClass('active')){
		$('div#sendEmailDiv').show();
		$('div#smsContentDiv').hide();
		
	}
	if($('li#classTeacherSmsDiv').hasClass('active')){
		$('div#sendEmailDiv').hide();
		$('div#smsContentDiv').show();
		$('.messageSubject1').hide();
		$('#classWideemailOrSms').val('M');
	}
	if($('li#adminSmsDiv').hasClass('active')){
		$('#smsContentDiv').show();
		$('.messageSubject1').hide();
		
	}
	if($('li#sendSchoolwideEmailDiv').hasClass('active')){
		$('div#smsContentDiv').hide();
		$('div#sendEmailDiv').show();
		$('#smsPreviewDiv').hide();
	} 
});
function checkAll() {
	if ($(".allClasses").is(':checked')) {
		$("[name='chkBoxSelectedAccountIds']").each(function() {
			$(this).parent('span').addClass('checked');
			$(this).attr("checked", "true");
		});
	} else {
		$("[name='chkBoxSelectedAccountIds']").each(function() {
			$(this).parent('span').removeClass('checked');
			$(this).removeAttr("checked");
		});
	}
}
$("input[name=chkBoxSelectedAccountIds]").click(function() {
	if ($("input[name=chkBoxSelectedAccountIds]:unchecked").length > 0) {
		$(".allClasses").parent('span').removeClass("checked");
		$(".allClasses").attr("checked", false);
	} else {
		$(".allClasses").parent('span').addClass("checked");
		$(".allClasses").attr("checked", true);
	}
});

</script>