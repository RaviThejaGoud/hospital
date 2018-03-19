<%@ include file="/common/taglibs.jsp"%>
<s:if test='%{budgetRequest != null && !budgetRequest == ""}'>
	<s:iterator value="objectList">
		<font size="3px;"><s:property value="particularTypeName"/></font>
		<s:set var="particularTypeId" value="%{id}"></s:set>
		<div class="row">
			<s:iterator value="particulars">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-6"><s:property value="particularName"/> :</label>
							<%-- <s:property value="#particularTypeId"/> --%>
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
	
					<input type="text" placeholder="Total Amount" class="form-control input-sm" name="budgetRequest.totalBudgetAmount" value='<s:property value="budgetRequest.totalBudgetAmount"/>' id="totalBudgetAmount1" disabled="disabled">
				</div>
			</div>
		</div>
	</div>
	<%-- <div class="form-actions fluid">
					<div class="row">
						<div class="col-md-6">
							<div class="col-md-offset-3 col-md-9">
								<sj:submit cssClass="submitBt btn blue" value="Submit" indicator="indicator" onBeforeTopics="getApproveStatus" validate="true"
									targets="mainContentDiv" formIds="addAnnualBudget" />
									<s:url id="doSecretaryDashboards" action="ajaxManagerBudgetRequestDetails" 
										includeParams="all" escapeAmp="false" namespace="/admin">
									</s:url>
									<sj:a href="%{doSecretaryDashboards}" cssClass="btn default"
										indicator="indicator" targets="mainContentDiv">Clos dsdsdd e</sj:a>
							</div>
						</div>
					</div>
				</div> --%>
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
											$("input#"+ data[i].PARTICULARID).val(data[i].AMOUNT);
										} 
										
									});
								}
							}
						}
					});
		});
</script>	
	
