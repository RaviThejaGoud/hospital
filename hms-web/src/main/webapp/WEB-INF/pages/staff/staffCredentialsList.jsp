<%@ include file="/common/taglibs.jsp"%>
<s:if test="%{objectList != null && !objectList.isEmpty()}">
	<div class="form-group">
		<label class="col-md-2 control-label">
			<span class="required">*</span> Active <span id="type"></span>
			<s:if test='%{anyTitle=="P"}'>
			(s)	
			</s:if>
			 :
		</label>
		<div class="col-md-10">
			<div class="checkbox-list">
				<label class="checkbox-inline">
					<input type="checkbox" name="" value="" onClick="allRoleNames()"
						class="checkbox userRoleNames">
					<b>Select All <span id="type"></span><s:if test='%{anyTitle=="P"}'>s</s:if> </b>
				</label>
			</div>
			<s:checkboxlist name="chkBoxSelectedAccountIds" list="objectList"
				listKey="acountId" listValue="personNameWithRoleDesc" theme="ems"
				onclick="validateSMSCount(this)" />
		</div>
	</div>
</s:if>
<s:elseif
	test="%{viewStudentPersonAccountDetailsList != null && !viewStudentPersonAccountDetailsList.isEmpty()}">
	<div class="form-group">
		<label class="col-md-2 control-label">
			<span class="required">*</span> Active <span id="type"></span>(s):
		</label>
		<div class="col-md-10">
			<div class="checkbox-list">
				<label class="checkbox-inline">
					<input type="checkbox" name="" value="" onClick="allRoleNames()"
						class="checkbox userRoleNames">
					<b>Select All Students</b>
				</label>
			</div>
			<s:checkboxlist name="chkBoxSelectedAccountIds"
				list="viewStudentPersonAccountDetailsList" listKey="accountId"
				listValue="personFullName" theme="ems" onclick="validateSMSCount(this)"/>
		</div>
	</div>
</s:elseif>
<s:else>
	<div class="alert-info">
		<s:if test='%{anyTitle=="S" || anyTitle=="N"}'>
		Currently there is no staff.
		</s:if>
		<s:else>
			<s:if test='%{anyTitle=="P"}'>
				Currently there is no parents.
			</s:if>
			<s:else>
				Currently there is no students.
			</s:else>
		</s:else>
	</div>
</s:else>

<div class="spaceDiv"></div>
<script type="text/javascript">
	$(document).ready(function() {
		var msgType=$("#msgReceiverType option:selected").text();
		$('span#type').html(msgType);
		 $("input:checkbox, input:radio").uniform();
		 $("input[name=chkBoxSelectedAccountIds]").click(function() {
			if ($("input[name=chkBoxSelectedAccountIds]:unchecked").length > 0) {
			   $(".userRoleNames").parent('span').removeClass("checked");
				$(".userRoleNames").attr("checked", false);
			} else {
			    $(".userRoleNames").parent('span').addClass("checked");
				$(".userRoleNames").attr("checked", true);
			}
		});
		 
		$("#chkBoxMobileIds").parent('span').addClass("checked");
		$('#errorMsgDivId').hide();
   		$('.submitBt').removeAttr('disabled');
    		
	 });
	function allRoleNames() {
		if ($(".userRoleNames").is(':checked')){
		    $("[name='chkBoxSelectedAccountIds']").each(function(){
			   $(this).parent('span').addClass('checked');
			    $(this).attr("checked", "true");
			    validateSMS($(this));
			});
		}
		else{
		$(".userRoleNames").parent('span').removeClass('checked');
			  $(".userRoleNames").removeAttr("checked");
		 $("[name='chkBoxSelectedAccountIds']").each(function(){
			   $(this).parent('span').removeClass('checked');
			    $(this).removeAttr("checked");
			    validateSMS($(this));
			});
		}	
	}
	
	availableSMSCount = '<s:property value="availableSMSCount"/>';
	$('.smsValidationClass').click(function()
	{
		validateSMS($(this));
	});
			
	var i=0;		
	function validateSMS(smsEvent)
	{
		validateSMSCount(smsEvent);
	}
	
	function validateSMSCount(eve)
	{
		var alertType=$("input[name=chkBoxSelectedIds]:checked").val();
		if ($(eve).is(':checked'))
		{
			i = i + 1;
	        if(availableSMSCount < i && alertType == 'M')
	        {
	        	var alertErrMsg= '<div class="alert alert-danger"><button data-dismiss="alert" class="close"></button><strong>Your available SMS count is '+availableSMSCount+' and your are trying to send '+i+' SMS.Please increase your SMS count by contacting EazySchool supporting team (080-46620999).</strong></div>';
	        	$('#errorMsgDivId').show();
	        	$('#errorMsgDivId').html(alertErrMsg);
	        	$("#chkBoxMobileIds").removeClass('checked');
	        	$("#chkBoxMobileIds").parent('span').removeClass("checked");
	        	$('.submitBt').attr('disabled', 'disabled');
	        }
		}else{
				i = i - 1;
		   	   if(availableSMSCount >= i && alertType == 'M')
		       {
		   			$("#chkBoxMobileIds").parent('span').addClass("checked");
		   			$('#errorMsgDivId').hide();
		       		$('.submitBt').removeAttr('disabled');
		       }
		}		
				
	}
	</script>