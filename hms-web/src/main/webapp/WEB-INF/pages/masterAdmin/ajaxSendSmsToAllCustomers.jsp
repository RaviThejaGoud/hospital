<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/ems.js"> </script>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Send SMS To All Customers
				</div>
			</div>
			<div class="portlet-body">
				<div class="form-body form-horizontal">
					<div class="tab-content" id="sendToSmsDiv">
					<%@ include file="/common/messages.jsp"%>
					<s:form id="addSendSMSToAllCustomer" action="ajaxSendSMSToAllCustomers" method="post" theme="simple" cssClass="form-horizontal" namespace="/masterAdmin"  enctype="multipart/form-data">
					<div id="selectMsgType">
						<div class="form-group">
							<label class="col-md-2 control-label">
								<span class="required">*</span>Alert Type :
							</label>
							<div class="col-md-9">
								<div class="checkbox-list">
									<label class="checkbox-inline col-md-2">
										 <input type="checkbox" name="chkBoxSelectedIds"
												id="mobileChkBoxId"   value="M" checked="checked"
												 class="mbc checkByValue"
												> 
										Mobile
									</label>
									<label class="checkbox-inline col-md-2">
										 <input type="checkbox" name="chkBoxSelectedIds" id="mailCheckBoxId"
												    value="E"  checked="checked"  
													class="mec checkByValue" />  
										
										E-Mail
									</label>
								</div>
								<p id="chkMobileEmailMsg" style="margin-right: 587px;color: red;"></p>
							</div>
						</div>
						</div>
						<div class="form-group">
							<label class="col-md-2 control-label">Select Customers</label>
							<div class="col-md-3">
								<s:select list="objectList" id="id"
									name="anyTitle" listKey="id" listValue="organization"
									headerKey="AA" cssClass="required  form-control"
									headerValue="- Send SMS To All Customers -"></s:select>
							</div>
						</div>
						<div class="form-group" id="selectedCustomers">
							<label class="col-md-2 control-label">Select Staff</label>
								<div class="col-md-9">
								<div class="checkbox-list">
									<label class="checkbox-inline">
									<div class="checker"><span class="all"><input type="checkbox" value="AD" name="eventId" id="Administrators" checked="checked"></span></div> All Administrators
									</label>
									<label class="checkbox-inline">
									<div class="checker"><span class="all"><input type="checkbox" value="AP" name="plTitle" id="Principal" checked="checked"></span></div> All Principal
									</label>
									<label class="checkbox-inline">
									<div class="checker"><span class="all"><input type="checkbox" name="selectedId" value="AS" id="Staff" checked="checked"></span></div> All Staff
									</label>
									<label class="checkbox-inline">
									<div class="checker"><span class="all"><input type="checkbox" name="plSubjectName" value="ST" id="Students" checked="checked"></span></div> All Students
									</label>  
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-2 control-label"><span class="required">*</span>Password</label>
							<div class="col-md-3">
								<div class="input-group">
									<input type="password" placeholder="Password" class="form-control" id="password" required="required" value="" maxlength="8">
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
									cssClass="smsWord_count form-control required" cols="20"></sj:textarea>
									<span class="smsCounter"></span>
							</div>
						</div>
					</div>
					<div class="messageSubject">
						<div class="form-group">
							<label class="control-label col-md-2">
								<span class="required">*</span>E-Mail Subject :
							</label>
							<div class="col-md-2">
								<sj:textfield name="messages.title" id="title" size="40"
									labelposition="top" cssClass="form-control input-medium required"
									maxlength="80"></sj:textfield>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-2">
								<span class="required">*</span>E-Mail Content :
							</label>
							<div class="col-md-6">
								<sj:textarea name="messages.emailContent" id="emailDescription"
									rows="3" cssClass="word_count form-control required " cols="20"
									maxCharsData="1000"></sj:textarea>
								<span class="counter"></span>
							</div>
						</div>
								<div class="form-group">
									<label class="control-label col-md-2"> Upload Documents
										: </label>
									<div class="col-md-9">
										<s:file name="fileUpload" id="uploadAttachments"
											cssClass="multi"></s:file>
									</div>
								</div>
							</div>
					<div>&nbsp;</div>
						<div class="form-actions fluid">
							<div class="col-md-12">
								<div class="col-md-offset-2 col-md-6">
									<sj:submit cssClass="submitBt btn blue"
										formIds="addSendSMSToAllCustomer" value="Submit" indicator="indicator" targets="mainContentDiv" onBeforeTopics="checkValiedsendSMS" validate="true"/>
								</div>
							</div>
						</div>
						</s:form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/subscription/textcounter.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	$("input[type=file].multi").MultiFile();
	 $("input:checkbox, input:radio").uniform();
	$.destroyTopic('checkValiedsendSMS');
	$('span.all').addClass('checked');
});
	$.subscribe('checkValiedsendSMS', function(event, data) {
		var password=$('#password').val();
		if(password !="2VsnHJG9"){
			alert("Please give correct password.");
			event.originalEvent.options.submit=false;
			$('#password').val('');
		}else if($("input[name=chkBoxSelectedIds]:checked").length==0){
			alert("Please Select at least one alert type.");
			event.originalEvent.options.submit=false;
	}
	});
	
	$("input[name=eventId]").click(function() {
		if (this.checked) {
			$("#Administrators").parent('span').addClass("checked");
			$("#Administrators").attr("checked", true);
		} else {
			$("#Administrators").parent('span').removeClass("checked");
			$("#Administrators").attr("checked", false);
		}
	})
	$("input[name=plTitle]").click(function() {
		if (this.checked) {
			$("#Principal").parent('span').addClass("checked");
			$("#Principal").attr("checked", true);
		} else {
			$("#Principal").parent('span').removeClass("checked");
			$("#Principal").attr("checked", false);
		}
	})
	$("input[name=selectedId]").click(function() {
		if (this.checked) {
			$("#Staff").parent('span').addClass("checked");
			$("#Staff").attr("checked", true);
		} else {
			$("#Staff").parent('span').removeClass("checked");
			$("#Staff").attr("checked", false);
		}
	})
	$("input[name=plSubjectName]").click(function() {
		if (this.checked) {
			$("#Students").parent('span').addClass("checked");
			$("#Students").attr("checked", true);
		} else {
			$("#Students").parent('span').removeClass("checked");
			$("#Students").attr("checked", false);
		}
	})
	$('input[name=chkBoxSelectedIds]').click(function(){
		if($(this).attr("id")=="mobileChkBoxId"){
		$('#smsContentDiv').toggle();
		}
		if($(this).attr("id")=="mailCheckBoxId"){
		$('.messageSubject').toggle();
		}
	})
	/* function checkMessageType(type) {
		  var checked = $("input[name=chkBoxSelectedIds]:checked").length;
		  if (checked != 0) {
			  $("p#chkMobileEmailMsg").hide();
			  if($("M"==type)){
				  var mobileId = $("input[id=mobileChkBoxId]:checked").length;
				  if(mobileId !="0"){
					  $("p#MobileEmailMsg").html('You have an option to send out a message via SMS.');
				  	$('#smsContentDiv').show();
				  }
				  else{
					  $('#smsContentDiv').hide();
				  }
			  }
			  if($("E"==type)){
				  var emailId = $("input[id=mailCheckBoxId]:checked").length;
				  if(emailId!="0"){
					  $("p#MobileEmailMsg").html('You have an option to send out a message via SMS.');
					  $('.messageSubject').show();
				  } else{
					  $('.messageSubject').hide();
				  }
			  }
		  } else if(checked == 0) {
			  $('#smsContentDiv').hide();
			  $('.messageSubject').hide();
			  $("p#chkMobileEmailMsg").html('Please select at least one alert type to send sms or email.');
		}
	} */
</script>