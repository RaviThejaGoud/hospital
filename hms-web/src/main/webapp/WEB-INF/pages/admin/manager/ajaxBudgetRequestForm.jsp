<%@ include file="/common/taglibs.jsp"%>
<div class="portlet-body">
				<div class="row profile">
					<div class="col-md-12">
					<s:form action="ajaxAddSchoolAnnualBudget" theme="simple" id="addAnnualBudget" method="post" cssClass="form-horizontal" namespace="/admin">
					<s:hidden name="financialYear.id"></s:hidden>
					<s:hidden name="tempString" cssClass="tempString"></s:hidden>
							<div class="form-body">
								<div class="form-group">
										<label class="col-md-2 control-label">Select Month :</label>
										<div class="col-md-3">
											<s:select list="weekDaysList" cssClass="form-control" name="budgetRequest.requestedMonth"></s:select>
										</div>
									</div>
								<s:iterator value="objectList">
									<%-- <h3 class="form-section"><s:property value="particularTypeName"/></h3> --%>
									<font  size="3px"><s:property value="particularTypeName"/></font>
									<s:set var="particularTypeId" value="%{id}"></s:set>
									<div class="row">
										<s:iterator value="particulars">
												<div class="col-md-6">
													<div class="form-group">
														<label class="control-label col-md-6"><s:property value="particularName"/> :</label>
														<%-- <s:property value="#particularTypeId"/> --%>
														<div class="col-md-3">
																<input type="text" placeholder="Enter Amount" class="numericDot required form-control input-sm perticualrDetailsClass" id="<s:property value="id"/>" name="perticualrDetailsClass" onkeyup="javascript:calculateTotalBudgetAmount()">
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
											<label class="control-label col-md-6"><b>Total :</b></label>
												<div class="col-md-3">
												<s:hidden name="totalAmount" id="totalBudgetAmount"></s:hidden>
												<input type="text" placeholder="Total Amount" class="form-control input-sm" name="totalAmount1" id="totalBudgetAmount1" disabled="disabled">
											</div>
										</div>
									</div>
								</div>
								<h3 class="form-section"><b></b></h3>
								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											<label class="control-label col-md-6"><b>Comments :</b></label>
												<div class="col-md-5">
												<sj:textarea rows="3" cols="20" name="comments" maxCharsData="1000" tabindex="14" cssClass="form-control word_count"></sj:textarea>
												<span class="help-block"><div class="counter"></div></span>
											</div>
										</div>
									</div>
								</div>
			
								<div class="form-actions fluid">
									<div class="row">
										<div class="col-md-6">
											<div class="col-md-offset-3 col-md-9">
												<sj:submit cssClass="submitBt btn blue" value="Submit" indicator="indicator" onBeforeTopics="getApproveStatus" validate="true"
													targets="mainContentDiv" formIds="addAnnualBudget" />
												<%-- <sj:submit cssClass="submitBt btn red" value="Reject" validate="true" indicator="indicator" onBeforeTopics="getRejectStatus"
													targets="mainContentDiv" formIds="addAnnualBudget" /> --%>
													<s:url id="doSecretaryDashboards" action="ajaxManagerBudgetRequestDetails" 
														includeParams="all" escapeAmp="false" namespace="/admin">
													</s:url>
													<sj:a href="%{doSecretaryDashboards}" cssClass="btn default"
														indicator="indicator" targets="mainContentDiv">Cancel</sj:a>
											</div>
										</div>
									</div>
								</div>
							</div>
						</s:form>
					  </div>
					</div>
				</div>
	<script>
	$(document).ready(function() {
		$('.numericDot').numeric( {
			allow : "."
		});
		
		$.subscribe('getApproveStatus',function(event, data) {
		var perticularAmount = '';
		var jsonObj = [];
		var perticularId;
		$('input[name="perticualrDetailsClass"]').each(function() {
			perticularAmount = $(this).val();
			if (!isNonEmpty(perticularAmount)) {
				perticularAmount = "0";
			}
			perticularId = $(this).attr('id');
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
	
	
	function calculateTotalBudgetAmount() 
	{
		 $('#totalBudgetAmount').val('');
		var totalBudgetAmount = 0;
		var perticularAmount = 0;
		$('input[name="perticualrDetailsClass"]').each(function() {
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
	
	</script>
