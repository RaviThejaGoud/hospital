<%@ include file="/common/taglibs.jsp"%>
<div data-width="760" class="modal fade modal-overflow in" style="display: block; width: 760px; margin-left: -379px; margin-top: 150px;" aria-hidden="false">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true"></button>
		<h4 class="bold pageTitle">
			Update Sender Id
		</h4>
	</div>
	<div class="modal-body">
		<div class="form-body">
			<jsp:include page="/common/messages.jsp" />
			 <s:if test='%{!customer.checkMobileService && !customer.checkEmailService}'>
			 <div class="alert alert-danger">
						<strong>ALERT : </strong> Customer SMS and E-Mail Services are disabled
					</div>
			 </s:if>
			<s:elseif test='%{!(customer.checkMobileService )}'>
				<div class="alert alert-danger">
						<strong>ALERT : </strong> Customer SMS service disabled
					</div>
			</s:elseif>
			<s:elseif test='%{!(customer.checkEmailService)}'>
				<div class="alert alert-danger">
						<strong>ALERT : </strong>Customer Email service disabled
					</div>
			</s:elseif>
			
			<script type="text/javascript"src="${pageContext.request.contextPath}/scripts/subscription/textcounter.js"></script>
			<s:form id="senderIdNameValue" action="ajaxUpadateSenderIdName" method="post" theme="simple" cssClass="form-horizontal" namespace="/masterAdmin">
				<s:hidden name="customer.id" value="%{customer.id}"></s:hidden>
				<div class="form-group">
					<label class="control-label col-md-3">
						<span class="required">*</span> Sender Id :
					</label>
					<div class="col-md-9">
						<sj:textfield id="senderId" name="customer.sender"
							maxlength="6" cssClass="required form-control input-medium as-input" />
					</div>
				</div>
					<div class="form-group">
						<label class="control-label col-md-3">
							<span class="required">*</span> Description :</label>
						<div class="col-md-9">
							<sj:textarea rows="3" cols="20" id="senderIdDesc" name="customer.senderIdDesc" maxCharsData="500" tabindex="3" cssClass="required form-control word_count"></sj:textarea>
							<span class="help-block">
								<div class="counter"></div> </span>
						</div>
				 </div>
				<div class="form-actions fluid">
					<div class="col-md-offset-3 col-md-9">
						<sj:submit   cssClass="submitBt btn blue" value="Submit"
							onBeforeTopics="senderNameValidation" validate="true"
							indicator="indicator" targets="mainContentDiv" formIds="senderIdNameValue" />
						<button type="button" data-dismiss="modal" class="btn default">
							Cancel
						</button>
					</div>
				</div> 
			</s:form>
		</div>
	</div>
</div>

<script  type="text/javascript">
$(document).ready(function() {
	$.destroyTopic('senderNameValidation');
});
$.subscribe('senderNameValidation', function(event, data) {
	var senderIdName = '<s:property value="customer.sender"/>';
	var sIdName = $('#senderId').val();
	var desc =$('#senderIdDesc').val();
	if(senderIdName == sIdName){
		alert("Please change the sender Id name.");
		event.originalEvent.options.submit = false;
	}else if(sIdName.length<6){
		alert("Sender Id shoud be 6 characters.");
		event.originalEvent.options.submit = false;
	}else if(desc.length==0){
		alert("Please give Sender Id description.");
		event.originalEvent.options.submit = false;
	}else{
		$('button.close').click();
		return true;
	} 
});
$("#senderId").on("input", function(){
	  var regexp = /[^a-zA-Z]/g;
	  if($(this).val().match(regexp)){
	    $(this).val( $(this).val().replace(regexp,'') );
	  }
});
</script>
