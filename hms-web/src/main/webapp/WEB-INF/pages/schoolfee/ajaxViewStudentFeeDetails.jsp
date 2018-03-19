<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/json2.js"></script>
<div class="row">
	<div class="col-md-12">
		<s:if test="%{(tempList != null && !tempList.isEmpty()) || (studentTransportTermsList != null && !studentTransportTermsList.isEmpty())}">
		<s:form action="ajaxAddStudentConcessionFee" id="studentFeeConcession" method="post" name="studentFeeConcessionDetails" cssClass="form-horizontal" theme="simple" namespace="/schoolfee">
		<s:hidden name="studentNumber" value="%{studentNumber}" id="studentNumber"></s:hidden>
		<s:hidden name="tempString" cssClass='tempString' />
		<jsp:include page="/common/messages.jsp" />
			<table class="table table-bordered table-hover" id="sample_2">
				<thead>
					<th>Particular Name</th>
					<th>Total Amount</th>
					<th>Concession %</th>
					<th>Concession Amount</th>

				</thead>
				<tbody>
				<s:if test="%{tempList != null && !tempList.isEmpty()}">
					<s:iterator value="tempList">
							<s:set name="schoolTermsId" value="%{schoolTermId}"></s:set>
							<s:if test="%{feeSettingId != #schoolFeeSettingId}">
								<tr>
									<td colspan="4">
										<h5>
											<center>
												<s:property value="settingName" />
											</center>
										</h5>
									</td>
								</tr>
							</s:if>
							<tr>
								<td colspan="4">
									<center>
										<strong> 
											 <s:property value="termName" />
										</strong>
									</center>
								</td>
							</tr>
							<s:iterator value="pendingStudentList">
									<tr>
										<td><s:property value="feeType" /></td>
										<td><s:property value="feeAmount" /> </td>
										<td>
											<div class="col-md-2">
												<sj:textfield name="concessionPercentage" id="percentage_%{feeId}"  title="%{feeAmount}"
													cssClass="form-control input-small numeric"  maxlength="3" onchange="calcDiscAmtForTrmsAndPrtclsByAmt(%{schoolTermId},%{feeId},%{feeAmount},%{concessionAmount},this.value)"></sj:textfield>
											</div>
										</td>
										<td>
											<div class="col-md-2">
												<sj:textfield name="concessionAmount"  id="%{schoolTermId}F%{feeId}F%{feeSettingId}" title="%{feeAmount}" 
													cssClass="form-control input-small numeric %{feeId}" ></sj:textfield>
											</div>
										</td>

									</tr>
							</s:iterator>

							<s:set name="schoolFeeSettingId" value="%{feeSettingId}"></s:set>
					</s:iterator>
					</s:if>
					<s:if test="%{studentTransportTermsList != null && !studentTransportTermsList.isEmpty()}">
					<s:iterator value="studentTransportTermsList">
							<s:set name="schoolTermsId" value="%{schoolTermId}"></s:set>
							<s:if test="%{feeSettingId != #schoolFeeSettingId}">
								<tr>
									<td colspan="4">
										<h5>
											<center>
												<s:property value="settingName" />
											</center>
										</h5>
									</td>
								</tr>
							</s:if>
							<tr>
								<td colspan="4">
									<center>
										<strong> 
											 <s:property value="termName" />
										</strong>
									</center>
								</td>
							</tr>
							<s:if test="%{studentTransportFeeList != null && !studentTransportFeeList.isEmpty()}">
							<s:iterator value="studentTransportFeeList">
									<tr>
										<td><s:property value="feeType" /></td>
										<td><s:property value="feeAmount" /> </td>
										<td>
											<div class="col-md-2">
												<sj:textfield name="concessionPercentage" id="percentage_%{transportFeeId}"  title="%{feeAmount}"
													cssClass="form-control input-small numeric"  maxlength="3" onchange="calcDiscAmtForTrmsAndPrtclsByAmt(%{schoolTermId},%{transportFeeId},%{feeAmount},%{concessionAmount},this.value)"></sj:textfield>
											</div>
										</td>
										<td>
											<div class="col-md-2">
												<sj:textfield name="concessionAmount"  id="%{schoolTermId}F%{transportFeeId}F%{feeSettingId}" title="%{feeAmount}" 
													cssClass="form-control input-small numeric %{transportFeeId}" ></sj:textfield>
											</div>
										</td>

									</tr>
							</s:iterator>
							</s:if>
							<s:set name="schoolFeeSettingId" value="%{feeSettingId}"></s:set>
					</s:iterator>
					</s:if>
				</tbody>
			</table>
			<div class="form-actions fluid">
					<div class="col-md-12">
						<div class="col-md-offset-6 col-md-12">
							<sj:submit value="Submit" id="submitButtonMainContent"
								targets="viewStudentFeeDetails" indicator="indicator"
								cssClass="submitBt btn blue" formIds="studentFeeConcession"
								onBeforeTopics="studentConcessionFormValidation" />
						</div>
					</div>
				</div>
		</s:form>
		</s:if>
		<s:else>
			<div class="alert alert-info">
				Fee not configured respective student class.
			</div>
		</s:else>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function() {
	$('.numeric').numeric();
	var studentId = $('#studentNumber').val();
	var feeURL = jQuery.url.getChatURL("/schoolfee/ajaxEditStudentFeeConession.do?studentNumber="+studentId);
	$.ajax( {
		url : feeURL,
		cache : false,
		dataType : 'json',
		success : function(response) {
			if (response.data) {
				var data = response.data;
				var concessionId;
				var concessionAmount;
				var feeId;
				var schoolTermId;
				var studentPaymentId;
				var feeSettingId;
				var inputObj;
				var feeAmount;
				if (data.length >= 1) {
					for ( var i = 0; i < data.length; i++) {
						concessionId = data[i].concessionId;
						concessionAmount = data[i].amount;
						feeId = data[i].feeId;
						schoolTermId=data[i].schoolTermId;
						studentPaymentId = data[i].studentPaymentId;
						feeAmount = data[i].feeAmount;
						feeSettingId = data[i].feeSettingId
						inputObj = $('input[id=' + schoolTermId + 'F' + feeId + 'F'+feeSettingId+']');
						if (inputObj) {
							inputObj.val(concessionAmount);
							inputObj.attr('id',schoolTermId + 'F' + feeId + 'F' + concessionId+'F'+feeSettingId);
							if (studentPaymentId != 0) {
								inputObj.attr('disabled',true);
								$('#percentage_' +feeId).attr('readonly', 'true');
							}
							
						}
						if(concessionAmount >0){
							partlrPerc = (concessionAmount / parseFloat(feeAmount) * 100);
							$('#percentage_' +feeId).val(Math.round(partlrPerc));
						}
						feeId=null;
						inputObj=null;
					}
				}
			}
		}
	});
	
	$.subscribe('studentConcessionFormValidation',function(event, data) {
	var fieldErrorString = '';
	var feeAmount = '';
	var jsonObj = [];
	var objIds;
	var allids;
	$('input[name="concessionAmount"]').each(function() {
		feeAmount = $(this).val();
		if (!isNonEmpty(feeAmount)) {
			feeAmount = "0";
		}else{
			if(feeAmount == 0.0)
				feeAmount = "0";
		}
		objIds = $(this).attr('id');
		if (isNonEmpty(objIds)) {
			allids = objIds.split('F');
			if (allids.length > 2) {
				jsonObj.push( {
						"feeId" : allids[1],
						"concessionId" : allids[2],
						"concessionAmount" : feeAmount,
						"feeSettingId" : allids[3]
				});
			} else {
				jsonObj.push( {
					"concessionId" : "0",
					"feeId" : allids[1],
					"concessionAmount" : feeAmount,
					"feeSettingId" : allids[3]
				});
			}
			objIds=null;
		}
		$('#submitButtonMainContent').val('Saving...');
		$('#submitButtonClassFee').val('Saving...');
	});
	
		var jsono = { "data" : jsonObj
		}
		if (jsonObj.length > 0) {
			//alert("jsono "+JSON.stringify(jsono))
			$('input[name=tempString]').val(JSON.stringify(jsono));
		} else {
			event.originalEvent.options.submit = false;
			alert('Please assign student concession amount.');
		}
	});
	/* function onChangeTotal(amount){
		var feeAmount = $(this).attr('title');
		alert($(this).val()+"-"+feeAmount+"-"+amount)
		var termTotAmt = parseFloat(termIdAndAmt[1]);
		 if (termTotAmt < parseFloat($(this).val())) {
			$(this).val(termTotAmt);
			alert('Amount Payable should be less than or equal to ' + termTotAmt);
		} 
	} */
	$("input").keyup(function(){
		var concessionAmount = $(this).attr('title');
		var ids = $(this).attr('id').split('F');
		 if (concessionAmount < parseFloat($(this).val())) {
			$(this).val(0);
			$('#percentage_' +ids[1]).val('');
			alert('Concession amount should be less than or equal to ' + concessionAmount);
		}else{
			partlrPerc = ($(this).val() / parseFloat(concessionAmount) * 100);
			$('#percentage_' +ids[1]).val(Math.round(partlrPerc));
		} 
    });
});
function calcDiscAmtForTrmsAndPrtclsByAmt(schoolTermId,feeId, feeAmount,concessionAmount, concessionPerc) {
	var partlrPerc = 0;
	//alert(schoolTermId+"-"+feeId+"-"+feeAmount+"-"+concessionPerc);
	if (isNonEmpty(concessionPerc)) {
			if(concessionPerc <=100){
				if (feeAmount >= concessionPerc) {
					partlrPerc = (feeAmount * parseFloat(concessionPerc) / 100);
					$('#percentage_' +feeId).val(concessionPerc);
					$('.'+feeId).val(Math.round(partlrPerc));
				}
			}else{
				if(concessionAmount>0)
					partlrPerc = (concessionAmount / parseFloat(feeAmount) * 100);
				else
					partlrPerc=0;
				$('#percentage_' +feeId).val(Math.round(partlrPerc));
				alert("You can not give more than 100%.")
			}
	}
	
}
</script>