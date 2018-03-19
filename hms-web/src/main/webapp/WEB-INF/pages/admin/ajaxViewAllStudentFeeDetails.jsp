<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
			<div class="portlet box blue">
				<div class="portlet-title">
					<div class="caption">Fee Information</div>
					<div class="tools">
						<a href="javascript:;" class="expand"></a>
						<a href="javascript:;" class="remove"></a>
					</div>
				</div>
				<div class="portlet-body">
				<s:if test="%{viewAllUsers.feesList != null && !viewAllUsers.feesList.isEmpty()}">
						<table
							class="table table-striped table-bordered table-hover table-full-width"
							id="sample_2">
							<thead>
								<tr>
									<th>
										Term Name
									</th>
									<th>
										Paid Amount
									</th>
									<th>
										Balance Amount
									</th>
									<th>
										Over Due Days
									</th>
								</tr>
							</thead>
							<tbody>
							<s:iterator value="viewAllUsers.feesList">
									<tr>
										<td>
											<s:property value="termName"  />
										</td>
										<td>
										   <s:property value="paidAmount"  />
										</td>
										<td>
											<s:property value="(balanceAmount)-(paidAmount)"  />
										</td>
										<td>
											<s:if test="%{dueDate > 0}">
												<s:property value="dueDate"  />
											</s:if>
											<s:else>
												0
											</s:else>
										</td>
									</tr>
								</s:iterator>
							</tbody>
						</table>
				</s:if>
				<s:else>
					<div class="alert alert-info">
						Fee data not found for this student.
					</div>
				</s:else>
			</div>
		</div>
	</div>
</div>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">Fee Refund Information</div>
				<div class="tools">
					<a href="javascript:;" class="expand"></a>
					<a href="javascript:;" class="remove"></a>
				</div>
			</div>
			<div class="portlet-body">
			<s:if test="%{refundedFeeDetails != null}">
				<table 
			 	   class="table table-striped table-bordered table-hover table-full-width"
				   id="sample_2">
				   <thead>
						<tr>
							<th>
								Paid Amount
							</th>
							<th>
							    Refunded Amount
							</th>
							<th>
								Refund Date
							</th>
							<th>
							    Refund Receipt
							</th>
							
						</tr>
				 </thead>
				 <tbody>
				  		<tr>
							<td>
								<s:property value="refundedFeeDetails.totalFeeAmount"  />
							</td>
							<td>
								<s:property value="refundedFeeDetails.refundAmount"  />
							</td>
							<td>
								<s:date name="refundedFeeDetails.refundDate" format="dd-MMM-yyyy" />
							</td>
							<td align="left">
								<s:if test="%{refundedFeeDetails.invoiceNumber != null}">
									<a style="cursor: pointer" id="noPrint"
									onClick="javascript:printFeeRefundPreview('<s:property value='refundedFeeDetails.studentId'/>','<s:property value='refundedFeeDetails.refundAmount'/>')"; target="_new"><img style="" src="../images/index.jpg">
									Invoice: <s:property value="refundedFeeDetails.invoiceNumber" />  
									</a>
								</s:if>
								<s:elseif test="%{refundedFeeDetails.invoiceString != null}">
									<a style="cursor: pointer" id="noPrint"
									onClick="javascript:printFeeRefundPreview('<s:property value='refundedFeeDetails.studentId'/>','<s:property value='refundedFeeDetails.refundAmount'/>')"; target="_new"><img style="" src="../images/index.jpg">
									Invoice: <s:property value="refundedFeeDetails.invoiceString" />  
								   </a>
								</s:elseif>
						  </td>
						</tr>
			   </tbody>
	      </table>
	    </s:if>
	   <s:else>
	   		<div class="alert alert-info">
				Fee Refund data not found for this student.
			</div>
	   </s:else>
		</div>
		</div>
	</div>
</div>
<div id="popupDownloadReceiptDiv"></div>
<div id="popupStudPaymentDiv"></div>
<script type="text/javascript">

	function printFeeRefundPreview(studentId,refundAmount){
		var paidAmountInwords = feeAmountInWords(refundAmount);
	    var url = jQuery.url.getChatURL("/reports/ajaxStudentFeeRefundPdfReport.do");
	    var pars = 'spId=' + studentId+"'&plTitle='"+paidAmountInwords;
	    $.ajax({
	        url : url,
	        cache : false,
	        data:pars,
	        success : function(data) {
	           prntPrvw(data,url,pars);
	        }
	     });
	    }	
	  //below script used to value to words
	 function feeAmountInWords(value) {
	    var fraction = Math.round(frac(value) * 100);
	    var f_text = "";

	    if (fraction > 0) {
	        f_text = "And " + convert_number(fraction) + " Paise";
	    }
	    return convert_number(value);
	}
		
</script>
