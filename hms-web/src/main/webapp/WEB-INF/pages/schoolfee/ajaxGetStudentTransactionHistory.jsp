<%@ include file="/common/taglibs.jsp"%>
<h4>Student pocket money history</h4>
	<s:if test="%{tempList != null && !tempList.isEmpty()}">
	<s:if test="%{anyTitle != null && !anyTitle.isEmpty()}">
		<label>Total deposit amount : &nbsp;Rs <span class="totaldepositamount"></span></label>
	</s:if>
	<s:else>
		<label>Total available amount : &nbsp;Rs <span class="totalavailableamount"></span></label><br/>
		<label>Total withdrawal amount : &nbsp;Rs <span class="totalwithdrawalamount"></span></label>
	</s:else>
	<div>&nbsp;</div>
	<div id="history">
		<table
			class="table table-bordered table-striped table-condensed flip-content">
			<thead class="flip-content">
				<tr>
					<th>
					<s:if test="%{anyTitle != null && !anyTitle.isEmpty()}">
						Deposited Date
					</s:if>
					<s:else>
						Withdrawn Date
					</s:else>
					</th>
				
					<th>
						Amount
					</th>
					<s:if test="%{anyTitle != null && !anyTitle.isEmpty()}">
						<th>
							Depositor Name
						</th>
					</s:if>
				</tr>
			</thead>
				<s:iterator value="tempList" status="stat">
				<tr class="totalDepositAmount" style="display: none;" id="<s:property value="tempList[#stat.count-1][3]" />"></tr>
				<tr class="totalTakenAmount" style="display: none;" id="<s:property value="tempList[#stat.count-1][4]" />"></tr>
				<tr>
					<td>
						<s:property value="tempList[#stat.count-1][0]" />
					</td>
					<td>
						<s:property value="tempList[#stat.count-1][2]" />
					</td>
					<s:if test='%{anyTitle =="D"}'>
					<td>
						<s:property value="tempList[#stat.count-1][4]" />
					</td>
					</s:if>
				</tr>
			</s:iterator>
		</table>
		</div>
	</s:if>
<s:else>
<div class="alert alert-info">
	<s:if test="%{anyTitle != null && !anyTitle.isEmpty()}">
		Currently there is no deposited information.
	</s:if>
	<s:else>
		Currently there is no withdrawn information.
	</s:else>
</div>
</s:else>
<script type="text/javascript">
$(document).ready(function() {
	var depositstatus = '<s:property value="anyTitle"/>';
	if(isNonEmpty(depositstatus) && depositstatus == "D"){
		$('.totaldepositamount').html($('tr.totalDepositAmount').attr('id'));
	}else{
		var totalDepositAmount = $('tr.totalDepositAmount').attr('id');
		var totalTakenAmount = $('tr.totalTakenAmount').attr('id');
	if( (Number(totalTakenAmount) == 0 && Number(totalDepositAmount)  == 0 ) || (totalDepositAmount == undefined && totalTakenAmount == undefined)){
		$('.totalavailableamount').html(0);
		$('.totalwithdrawalamount').html(0);
		$('input#availableBalance').val(0);
	}
	if(Number(totalDepositAmount) >= Number(totalTakenAmount)){
		var totalAvailable = totalDepositAmount - totalTakenAmount;
		$('.totalavailableamount').html(totalAvailable);
		if(totalTakenAmount == ""){
			$("#history").html('<div class="alert alert-info">Currently there are transactions.</div>');
			$('.totalwithdrawalamount').html(0);
		}	else{
			$('.totalwithdrawalamount').html(totalTakenAmount);
		}
		$('input#availableBalance').val(totalAvailable);
	}
	}
});
</script>
	
