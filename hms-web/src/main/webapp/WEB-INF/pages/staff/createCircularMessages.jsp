<%@ include file="/common/taglibs.jsp"%>
<jsp:include page="/common/messages.jsp"></jsp:include>
<div class="form-body form-horizontal">
	<div class="">
		<h4 class="pageTitle bold">
			Send Circular Messages via SMS and E-mail
		</h4>
		<div class="form-group">
		<div class="panel-body col-md-12">
			<div class="col-md-1">
				<span class="label label-danger"> NOTE : </span>
			</div>
			<div class="col-md-10">
				<ul>
					<li>
						 You have an option to send out a message via SMS or Email.
					</li>
					<li>
						Make sure your Mobile SMS content is less than 160 character otherwise it delivered as 2 SMS.
					</li>
				</ul>
			</div>
		</div>
	</div>
	</div>	
	<s:if test="%{(customer.contactEmail == null || customer.contactEmail.isEmpty())}"> 
		<div class="form-group">
			<div class="panel-body col-md-12">
				<div class="col-md-1">
					<span class="label label-danger"> ALERT : </span>
				</div>
				<div class="col-md-10">
					<div id="enableContactEmail">
						<div class="alert alert-danger">&nbsp;&nbsp; You have not configured from email address. So system will send emails from "messages@eazyschool.com".<s:url id="urlSendContactEmailLink" action="ajaxDoSchoolFromEmailInfo" namespace="/admin" />
						<sj:a href="%{urlSendContactEmailLink}" targets="mainContentDiv" cssClass="ajaxify title CFE ">  Click Here  </sj:a> to configure from email address if you wish to process email from your email address.</div>
					</div>
				</div>
			</div>
		</div>
	</s:if>
	<s:if test="%{(smsAlloted == 0) || (smsAlloted <= smsCnt)}">
		<div style="color: red;" class="alert alert-info">
			You have been used all your allotted
			<s:property value="smsAlloted" />
			SMS. Please contact EazySchool support team (080-46620999) to recharge your SMS.
		</div>
	</s:if>
  <s:form action="ajaxSaveCircularMessagesDetails" theme="simple" id="formForSchoolWideMessages" cssClass="form-horizontal" namespace="/common">
		    <jsp:include page="/WEB-INF/pages/common/messages/selectMessageType.jsp" />
		<s:hidden name="selectedId" id="smsAndEmail"></s:hidden>
		<s:hidden name="tempString" id="checkAllOrNotCircularMsg"></s:hidden> <!-- Status to know All Or Individual  -->
		<div class="form-group">
			<label class="control-label col-md-2"> To :</label>
			<div class="col-md-3">
				 <s:select name="messages.receiverType"
					 cssClass="form-control required input-medium"
					  id="msgReceiverType"
					list="#{'AN':'Non-Teaching Staff','AS':'Teaching Staff','AP':'Parent','AC':'Student'}"
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
		<div class="form-group">
			<label class="control-label col-md-2">
				<span class="required">*</span>Circular Description :
			</label>
			<div class="col-md-5">
				<sj:textarea name="messages.messageDescription"
					id="messageDescription" maxCharsData="1000"
					cssClass="form-control smsWord_count required" cssStyle="1045"
					 rows="3" cols="40"></sj:textarea>
				<div class="smsCounter"></div>
			</div>
		</div>
		<div class="form-actions fluid" id="checkService">
			<div class="col-md-offset-2 col-md-5">
				<s:if
					test='%{customer.checkMobileService == true || user.isSchoolAdmin == "Y"}'>
					<sj:submit cssClass="submitBt btn blue" value="Submit"
						targets="mainContentDiv" 
						formIds="formForSchoolWideMessages" validate="true"
						onBeforeTopics="formValidateForCircularMessages" />
					<s:url id="urlInboxesDetails"
						action="ajaxDoGetCircularMessagesList" namespace="/common"
						includeParams="all" escapeAmp="false">
						<s:param value="#session.academicYear" name="academicYearId" />
					</s:url>
					<sj:a href="%{urlInboxesDetails}" targets="mainContentDiv"
						cssClass="btn default">Cancel</sj:a>
						</s:if>
			</div>
		</div>
	</s:form>
	</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/subscription/textcounter.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		if((<s:property value="smsAlloted"/> != 0) && (<s:property value="smsAlloted"/>) <= (<s:property value="smsCnt"/>)){
			alert('You have been used all your allotted '+ <s:property value="smsAlloted" />+' SMS. Please contact EazySchool support team (080-46620999) to recharge your SMS.');
		}
	$('input[name=chkBoxSelectedIds]:checked').each(function(){
	  $(this).removeAttr("checked");
	  $(this).parent('span').removeClass("checked");
	});
	$.destroyTopic('formValidateForCircularMessages');
		$("input:checkbox, input:radio").uniform();
		var anyTitle=$('#msgReceiverType').val();
		enableTextBox(anyTitle);
		$.subscribe('formValidateForCircularMessages', function(event, data) {
			
		   var selectedCount=0;
			$('input[name=chkBoxSelectedIds]:checked').each(function(){
				 selectedCount++;
			 if(selectedCount > 1)
				 $('input#smsAndEmail').val('SE');
			 else
				 $('input#smsAndEmail').val($(this).val());
			});
			if(selectedCount==0){
				alert('Please select at least one alert type.');
				event.originalEvent.options.submit=false;
				//$('input#smsAndEmail').val('C');
			}
			//var atLeastOneIsChecked = $('#checkArray:checkbox:checked').length > 0;
			var checkAllOrIndicidual= $('.userRoleNames:checkbox:checked').length;  //$('input[name=chkBoxSelectedIds]:checked') 
			if(checkAllOrIndicidual >0){
				$('input#checkAllOrNotCircularMsg').val($('#msgReceiverType').val());
			}else{
				$('input#checkAllOrNotCircularMsg').val('I');
			}
			
			if($("input[name=selectType]:checked").val()=="A"){
			var selectedChkCount = $("input[name=chkBoxSelectedAccountIds]:checked").length;
			if(selectedChkCount > 0){
				// event.originalEvent.options.submit=false;	
				return true;
			}
			else{
				alert('Please select at least one user.');
				event.originalEvent.options.submit=false;
			}
		}else if($("input[name=selectType]:checked").val()=="SU"){
			if($('.accountIds').val() != "[]"){
				// event.originalEvent.options.submit=false;		
				return true;
			}else{
				alert("Please search and select at least one user.");
				 event.originalEvent.options.submit=false;	
			}
		}
	  });
	});
	function getSearchUsersList(searchValue) {
		$("[name='chkBoxSelectedAccountIds']").each(function(){
		   $(this).parent('span').removeClass('checked');
		   $(this).removeAttr("checked");
		   $(".userRoleNames").parent('span').removeClass("checked");
		   $(".userRoleNames").attr("checked", false);
		});
		$('input#checkAllOrNotCircularMsg').val('I');
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
			if(anyTitle=="AC"){
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
	function checkMessageType(value) {
		// $('#title').focus();
	} 
	$('div#enableContactEmail').click(function() {
		window.location.hash = "target=AMAS.ajaxify ACM";
		$('li#adminInboxDiv').parents('li').removeClass('open active');
		$('li#adminInboxDiv').removeClass('active');
		$('li#schoolSettingsDiv').addClass('open active');
		$('#contactEmail').addClass('active');
		$('li#urlSchoolFromEmailInfo a').click();
	});
</script>
