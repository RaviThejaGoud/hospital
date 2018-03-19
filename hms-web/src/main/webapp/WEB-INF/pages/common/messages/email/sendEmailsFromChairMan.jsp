<%@ include file="/common/taglibs.jsp"%>
<jsp:include page="/common/messages.jsp"></jsp:include>
<div class="form-body form-horizontal">
	<h4 class="pageTitle bold">
		Send E-mail
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
	<s:if test='%{user.isSchoolAsstStaff=="N"}'>
		<s:if test="%{(smsAlloted == 0) || (smsAlloted <= smsCnt)}">
			<div style="color: red;" class="alert alert-info">
					You have been used all your allotted
					<s:property value="smsAlloted" />
					SMS. Please contact EazySchool support team (080-46620999) to recharge your SMS.
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
					list="#{'S':'Staff','O':'Other'}"
					onchange="javascript:enableTextBox(this.value);"></s:select>
			</div>
		</div>
		<div id="usersListDiv"></div>
		<div class="messageTo" >
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
		<div class="messageSubject" >
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
			<div class="form-group">
				<label class="control-label col-md-2">
					<span class="required">*</span>E-Mail Content :
				</label>
				<div class="col-md-6">
					<sj:textarea name="messages.emailContent" id="emailDescription"
						rows="7" cssClass="wysihtml5 form-control required messagesArea1" cols="20"></sj:textarea>
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
	//var test =$('input[name=chkBoxSelectedIds]').val();
	if((<s:property value="smsAlloted"/> != 0) && (<s:property value="smsAlloted"/>) <= (<s:property value="smsCnt"/>)){
		alert('You have been used all your allotted '+ <s:property value="smsAlloted" />+' SMS(s). Please contact eazyschool support team (080-46620999) to recharge your SMS(s).');
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
			 if(selectedValue=="S"){
				 if($("input[name='chkBoxSelectedAccountIds']:checked").length == 0){
				 		alert("Please Select at least one staff.");
				 		event.originalEvent.options.submit=false;
				 }else{
					 return true;
				 }
			} 
		 }
  });
	$('#emailOrSms').val('E');

});

function enableTextBox(anyTitle) {
	if(isNonEmpty(anyTitle && anyTitle != 0)){
		if(anyTitle!="O"){
			$("#usersListDiv").show();
			$(".messageTo").hide();
			
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
			
		}
		
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
