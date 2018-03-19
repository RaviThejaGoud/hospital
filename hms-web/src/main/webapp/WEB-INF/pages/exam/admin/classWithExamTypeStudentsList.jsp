<%@ include file="/common/taglibs.jsp"%>
<div >&nbsp; </div>
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
						Admission Number
						</th>
						 <th>
							Mobile Number
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
					<s:iterator value="studentsList" status="stat">
						<tr>
							<td>
							<s:property value="studentsList[#stat.count-1][0]" />
							&nbsp;
							<s:property value="studentsList[#stat.count-1][1]" />
							</td>
							<td>
							<s:property value="studentsList[#stat.count-1][3]" />
							</td>
							<td>
								<s:if test="%{studentsList[#stat.count-1][5] != null && !studentsList[#stat.count-1][5].isEmpty()}">
									<s:property value="studentsList[#stat.count-1][5]" />
								</s:if>	
								<s:else>
									<strong> - </strong>
								</s:else>	
							</td>
							<td>
								<div class="checkbox">
									<label>
									<s:if test="%{studentsList[#stat.count-1][5] != null && !studentsList[#stat.count-1][5].isEmpty()}">
										<input type="checkbox" name="chkBoxSelectedAccountIds" class="stuCheckBox"
											value='<s:property value="studentsList[#stat.count-1][2]" />' id="checkbox" />
									</s:if>
									<s:else>
									<input type="checkbox" name="chkBoxSelectedAccountIds"
											value='<s:property value="studentsList[#stat.count-1][2]" />' id="checkbox" disabled="true"/>
									</s:else>
									</label>
								</div>
							</td>
						</tr>
					</s:iterator>
					</tbody>
				</table>
		</div>
	</div>
	
</s:if>
<s:else>
	<div class="alert alert-info">
		Currently there are no marks assigned for any student.
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
		  $("[name='chkBoxSelectedAccountIds']:not(':disabled')").each(function(){
			$(this).parent('span').addClass('checked');
			$(this).attr("checked", "true");
			
			validateSMS($(this));
		});
	} else {
		$("[name='chkBoxSelectedAccountIds']").each(function() {
			$(this).parent('span').removeClass('checked');
			$(this).removeAttr("checked");
			
			validateSMS($(this));
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

availableSMSCount = '<s:property value="availableSMSCount"/>';
$('.stuCheckBox').click(function()
{
	validateSMS($(this));
});
		
var i=0;		
function validateSMS(smsEvent)
{
	if (smsEvent.is(':checked')) 
    {
        i = i + 1;
        
        if(availableSMSCount < i)
        {
        	//alert(availableSMSCount + " : " + i);
        	var alertErrMsg= '<div class="alert alert-danger"><button data-dismiss="alert" class="close"></button><strong>Your available SMS count is '+availableSMSCount+' and your are trying to send '+i+' SMS.Please increase your SMS count by contacting EazySchool supporting team (080-46620999).</strong></div>';
        	$('#errorMsgDivId').show();
        	$('#errorMsgDivId').html(alertErrMsg);
        	$('.submitBt').attr('disabled', 'disabled');
        }
    }
    else
    {
	   	   i = i - 1;
	   	   if(availableSMSCount >= i)
	       {
	   			$('#errorMsgDivId').hide();
	       		$('.submitBt').removeAttr('disabled');
	       }
	  }
}

</script>