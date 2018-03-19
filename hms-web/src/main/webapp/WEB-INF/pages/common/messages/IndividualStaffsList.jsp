<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/subscription/textcounter.js"></script>
<s:if test="%{objectList != null && !objectList.isEmpty()}">
	<div class="col-md-12">
		<div class="col-md-3" style="width: 14%;">&nbsp;</div>
		<div class="col-md-9" style="overflow-y:scroll;height: 350px;">
			<table class="table table-striped table-bordered">
				<thead>
					<tr>
						<th>
							Staff Name
						</th>
						<s:if test="%{eventId != null && !eventId.isEmpty()}">
						 <th>
							Mobile Number
						</th>
						</s:if>
						<s:else>
						 <th>
							Email
						</th>
						</s:else>
						<th>
							Role
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
					<s:iterator value="objectList" status="stat">
						<tr>
							<td>
								<s:property value="objectList[#stat.count-1][0]" />
								&nbsp;
								<s:property value="objectList[#stat.count-1][1]" />
							</td>
							<s:if test="%{eventId != null && !eventId.isEmpty()}">
							<td>
								<s:if test="%{objectList[#stat.count-1][2] != null && !objectList[#stat.count-1][2].isEmpty()}">
									<s:property value="objectList[#stat.count-1][2]" />
								</s:if>	
								<s:else>
									<strong> - </strong>
								</s:else>	
							</td>
							</s:if>
							<s:else>
							<td>
							<s:if test="%{objectList[#stat.count-1][4] != null && !objectList[#stat.count-1][4].isEmpty()}">
									<s:property value="objectList[#stat.count-1][4]" />
								</s:if>	
								<s:else>
									<strong> - </strong>
								</s:else>
							</td>
							</s:else>
							<td>
								<s:property value="objectList[#stat.count-1][5]" />
							</td>
							<td>
								<div class="checkbox">
									<label>
										<input type="checkbox" name="chkBoxSelectedAccountIds" class="smsValidationClass"
											value='<s:property value="objectList[#stat.count-1][3]" />' id="checkbox">
									</label>
								</div>
							</td>
						</tr>
					</s:iterator>
					<div class="space10"></div>
				</tbody>
			</table>
		</div>
	</div>
</s:if>
<s:else>
	<div class="alert alert-info">
		Oops! No staff found in the system.
	</div>
</s:else>
<script type="text/javascript">
changePageTitle("Send Class Wide Message");
$(document).ready(function() {
		FormAdvanced.init();
		FormComponents.init();
	$("input:checkbox, input:radio").uniform();
});
function checkAll() {
	if ($(".allClasses").is(':checked')) {
		$("[name='chkBoxSelectedAccountIds']").each(function() {
			$(this).parent('span').addClass('checked');
			$(this).attr("checked", "true");
			validateSMS($(this))
		});
	} else {
		$("[name='chkBoxSelectedAccountIds']").each(function() {
			$(this).parent('span').removeClass('checked');
			$(this).removeAttr("checked");
			validateSMS($(this))
		});
	}
}

$("input[name=chkBoxSelectedAccountIds]").click(function() {
	if ($("input[name=chkBoxSelectedAccountIds]:checked").length == 0) {
		alert("PLease select at least one staff.");
		return false;
	}else{
		return true;
	}
	if ($("input[name=chkBoxSelectedAccountIds]:unchecked").length > 0) {
		$(".allClasses").parent('span').removeClass("checked");
		$(".allClasses").attr("checked", false);
	} else {
		$(".allClasses").parent('span').addClass("checked");
		$(".allClasses").attr("checked", true);
	}
});


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
	if ($(eve).is(':checked'))
	{
		i = i + 1;
        if(availableSMSCount < i)
        {
        	var alertErrMsg= '<div class="alert alert-danger"><button data-dismiss="alert" class="close"></button><strong>Your available SMS count is '+availableSMSCount+' and your are trying to send '+i+' SMS.Please increase your SMS count by contacting EazySchool supporting team (080-46620999).</strong></div>';
        	$('#errorMsgDivId').show();
        	$('#errorMsgDivId').html(alertErrMsg);
        	//$("#chkBoxMobileIds").removeClass('checked');
        	$("#chkBoxMobileIds").parent('span').removeClass("checked");
        	$('.submitBt').attr('disabled', 'disabled');
        }
	}else{
			i = i - 1;
	   	   if(availableSMSCount >= i)
	       {
	   			$("#chkBoxMobileIds").parent('span').addClass("checked");
	   			$('#errorMsgDivId').hide();
	       		$('.submitBt').removeAttr('disabled');
	       }
	}		
			
}
</script>