<%@ include file="/common/taglibs.jsp"%>
<s:if test='%{budgetRequest != null && !budgetRequest == ""}'>
	<s:iterator value="objectList">
		<h3 class="form-section"><s:property value="particularTypeName"/></h3>
		<s:set var="particularTypeId" value="%{id}"></s:set>
		<div class="row">
			<s:iterator value="particulars">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-6"><s:property value="particularName"/> :</label>
							<%-- <s:property value="#particularTypeId"/> --%>
							<div class="col-md-3">
									<input type="text" placeholder="Enter Amount" class="numericDot required form-control input-sm perticualrDetailsClassByPA" id="secretary<s:property value="id"/>" name="perticualrDetailsClassByPA" onkeyup="javascript:calculateTotalBudgetAmountBySec()">
							</div>
							<div class="col-md-3">
									<input type="text" placeholder="Enter Amount" class="numericDot required form-control input-sm perticualrDetailsClass" id="<s:property value="id"/>" name="perticualrDetailsClass" onkeyup="javascript:calculateTotalBudgetAmount()" disabled="disabled">
							</div>
						</div>
					</div>
			</s:iterator>
		</div>
	 </s:iterator>			
	
	<h3 class="form-section"><b></b></h3>
	<div class="row">
		<div class="col-md-6">
			<div class="form-group">
				<label class="control-label col-md-4"><b>Total :</b></label>
					<div class="col-md-3">
							<s:hidden name="totalAmount" id="totalBudgetAmount"></s:hidden>
							<input type="text" placeholder="Total Amount" class="form-control input-sm" name="totalAmount1" value='<s:property value="budgetRequest.totalBudgetAmount"/>' id="totalBudgetAmount1" disabled="disabled">
				</div>
			</div>
		</div>
		<div class="col-md-6">
			<div class="form-group">
				<label class="control-label col-md-4"><b>Total Requested Budget :</b></label>
					<div class="col-md-3">
					<input type="text" placeholder="Total Amount" class="form-control input-sm" name="budgetRequest.totalBudgetAmount" value='<s:property value="budgetRequest.totalBudgetAmount"/>' id="totalBudgetAmount2" disabled="disabled">
				</div>
			</div>
		</div>
	</div>
	
	<h3 class="form-section"><b></b></h3>
	<div class="row">
		<div class="col-md-6">
			<div class="form-group">
				<label class="control-label col-md-4"><b>Comments :</b></label>
					<div class="col-md-5">
					<sj:textarea rows="3" cols="20" name="budgetRequest.comments" maxCharsData="1000" tabindex="14" cssClass="form-control word_count"></sj:textarea>
					<span class="help-block"><div class="counter"></div></span>
				</div>
			</div>
		</div>
	</div>
								
	<div class="form-actions fluid">
			<div class="row">
				<div class="col-md-6">
					<div class="col-md-offset-3 col-md-9">
						<sj:submit cssClass="submitBt btn blue" value="Approve" validate="true" indicator="indicator" onBeforeTopics="getApproveStatus"
							targets="mainContentDiv" formIds="addAnnualBudget" />
						<sj:submit cssClass="submitBt btn red" value="Reject" validate="true" indicator="indicator" onBeforeTopics="getRejectStatus"
							targets="mainContentDiv" formIds="addAnnualBudget" />
							
							<s:if test='%{tempString == "dashboard"}'>
								<s:url id="doSecretaryDashboards" action="ajaxSecretaryDashboard" 
									includeParams="all" escapeAmp="false" namespace="/admin">
								</s:url>
								<sj:a href="%{doSecretaryDashboards}" cssClass="btn default"
									indicator="indicator" targets="mainContentDiv">Close</sj:a>
							</s:if>
							<s:else>
								<s:url id="doSecretaryDashboards" action="ajaxViewSchoolDetailsHome" 
									includeParams="all" escapeAmp="false" namespace="/admin">
								</s:url>
								<sj:a href="%{doSecretaryDashboards}" cssClass="btn default"
									indicator="indicator" targets="mainContentDiv">Close</sj:a>
							</s:else>
							
							
					</div>
				</div>
			</div>
		</div>	
				
</s:if>
<s:else>
	<div class="alert alert-info">
		No budget information found for this month.
	</div>
</s:else>
	
<script>
$(document)
.ready(
		function() {
			
			$('.numericDot').numeric( {
				allow : "."
			});
			
			//alert( $('#budgetRequestId').val());
			var dataURL = jQuery.url.getChatURL("/admin/ajaxGetajaxViewBudgetJsonRes.do?budgetRequest.id="+ $('#budgetRequestId').val());
			$.ajax( {
						url : dataURL,
						cache : false,
						dataType : 'json',
						success : function(response) {
							if (response.budgetRequestData) {
								
								var data = response.budgetRequestData;
								if (data.length > 0) {
									$('.perticualrDetailsClass').each(function() 
									{
										//$(""+ data[0].PARTICULARID).val(data[1].AMOUNT);
										for ( var i = 0; i < data.length; i++) 
										{
											$("input#secretary"+ data[i].PARTICULARID).val(data[i].AMOUNT);
											$("input#"+ data[i].PARTICULARID).val(data[i].AMOUNT);
										} 
										
									});
								}
								var data = response.budgetRequestDataForSP;
								if (data.length > 0) {
									$('.perticualrDetailsClassByPA').each(function() 
									{
										//$(""+ data[0].PARTICULARID).val(data[1].AMOUNT);
										for ( var i = 0; i < data.length; i++) 
										{
											$("input#secretary"+ data[i].PARTICULARID).val(data[i].AMOUNT);
										} 
										
									});
								}
							}
							
							calculateTotalBudgetAmount();
							calculateTotalBudgetAmountBySec();
						}
					});
			
			$.subscribe('getApproveStatus',function(event, data) {
				
				$('.anyId').val("A");
				
				var perticularAmount = '';
				var jsonObj = [];
				var perticularId;
				$('input[name="perticualrDetailsClassByPA"]').each(function() {
					perticularAmount = $(this).val();
					if (!isNonEmpty(perticularAmount)) {
						perticularAmount = "0";
					}
					perticularId = $(this).attr('id');
					perticularId = perticularId.replace("secretary", "");
					if (isNonEmpty(perticularId)) {
						if (perticularId > 0) {
							jsonObj.push( {
									"perticularId" : perticularId,
									"perticularAmount" : perticularAmount
							});
						} else {
							jsonObj.push( {
								"perticularId" : "0",
								"perticularAmount" : perticularAmount
							});
						}
					}
				});
				
				$('.tempString').val(JSON.stringify(jsonObj));
				
				return true;
				});
			
		});
	
$.subscribe('getRejectStatus',function(event, data) {
	
	$('.anyId').val("R");
	var perticularAmount = '';
	var jsonObj = [];
	var perticularId;
	$('input[name="perticualrDetailsClassByPA"]').each(function() {
		perticularAmount = $(this).val();
		if (!isNonEmpty(perticularAmount)) {
			perticularAmount = "0";
		}
		perticularId = $(this).attr('id');
		perticularId = perticularId.replace("secretary", "");
		if (isNonEmpty(perticularId)) {
			if (perticularId > 0) {
				jsonObj.push( {
						"perticularId" : perticularId,
						"perticularAmount" : perticularAmount
				});
			} else {
				jsonObj.push( {
					"perticularId" : "0",
					"perticularAmount" : perticularAmount
				});
			}
		}
	});
	
	$('.tempString').val(JSON.stringify(jsonObj));
	
	return true;
	});
	


function calculateTotalBudgetAmountBySec() 
{
	 $('#totalBudgetAmount1').val('');
	var totalBudgetAmount = 0;
	var perticularAmount = 0;
	$('input[name="perticualrDetailsClassByPA"]').each(function() {
		perticularAmount = $(this).val();
		if (isNonEmpty(perticularAmount)) 
		{
			totalBudgetAmount += parseFloat(perticularAmount);
		}
	});
	
	
	$('#totalBudgetAmount').val(totalBudgetAmount);
	 $('#totalBudgetAmount1').val(totalBudgetAmount);
	  return true;
}

	function calculateTotalBudgetAmount() 
	{
		 $('#totalBudgetAmount2').val('');
		var totalBudgetAmount = 0;
		var perticularAmount = 0;
		$('input[name="perticualrDetailsClass"]').each(function() {
			perticularAmount = $(this).val();
			if (isNonEmpty(perticularAmount)) 
			{
				totalBudgetAmount += parseFloat(perticularAmount);
			}
		});
		 $('#totalBudgetAmount2').val(totalBudgetAmount);
		  return true;
	}
	
	</script>
