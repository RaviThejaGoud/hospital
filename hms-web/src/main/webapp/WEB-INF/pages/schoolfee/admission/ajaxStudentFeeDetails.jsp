<%@ include file="/common/taglibs.jsp"%>
<div data-width="760" class="modal fade modal-overflow in" data-focus-on="input:first"
	style="display: block; width: 760px; margin-left: -379px; margin-top: 100px;">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true"></button>
		<h4 class="modal-title">
			View Payment Receipts
		</h4>
	</div>
	<div class="modal-body">
		<s:if test="%{schoolFeeList!= null && !schoolFeeList.isEmpty()}">
			<h5 class="pagaeTitle bold">
				<strong>Payment Receipts</strong>
			</h5>
			<table class="table table-striped table-bordered table-hover table-full-width" id="sample_3">
				<thead>
					<tr>
						<th>
							Invoice Date
						</th>
						<th>
							Paid Amount
						</th>
						<s:if test="%{tempString2 != null}">
							<th>
								Excess Paid
							</th>
							<th>
								Excess Used
							</th>
						</s:if>
						<th>
							Download
						</th>
						<!-- <th>
							Delete
						</th> -->
					</tr>
				</thead>
				<tbody>
					<s:iterator value="schoolFeeList">
						<tr>
							<td>
								<s:property value="paymentDateStr" />
							</td>
							<td>
								<s:property value="paidAmount+fineAmount" />
							</td>
							
							<s:if test="%{tempString2 != null}">
								<td>
									<s:property value="excessAmount" />
								</td>
								<td>
									<s:property value="usedExcessAmount" />
								</td>
							</s:if>
							<td>
								<s:if test='%{invoiceNumberStr > 0 || invoiceNumberStr != null}'>
									<a style="cursor: pointer" id="noPrint"
										onClick="javascript:printAdmissionPreview('<s:property value='student.id'/>',
                                        '<s:property value='ctrPaymentDateStr'/>',
                                        '<s:property value='invoiceNumberStr'/>',<s:property value="paidAmount" />+<s:property value="fineAmount" />,'download','<s:property value="invoiceNumberStr"/>')"
										; target="_new"><img style="" src="../images/index.jpg">
										Invoice : <s:property value="invoiceNumberStr" /> </a>
								</s:if>
								<s:else>
								<a style="cursor: pointer" id="noPrint"
										onClick="javascript:printAdmissionPreview('<s:property value='student.id'/>',
                                        '<s:property value='ctrPaymentDateStr'/>',
                                        '<s:property value='invoiceNumberStr'/>',<s:property value="paidAmount" />+<s:property value="fineAmount" />,'download','<s:property value="invoiceNumberStr"/>')"
										; target="_new"><img style="" src="../images/index.jpg">
										Invoice : <s:property value="invoiceNumberStr" /> </a>
								</s:else>
								
							</td>
							<%-- <td>
									<a data-toggle="modal" href="#popupDeleteInvoiceDiv"
								class="btn btn-circle btn-xs red"
								onclick="javascript:getAjaxDeleteInvoice(<s:property value="studentId" />,<s:property value="invoiceNumber" />,<s:property value="academicYearId" />);"><i class="fa fa-trash-o"></i></i></a>
							</td> --%>
						</tr>
					</s:iterator>
				</tbody>
			</table>
		</s:if>
		<s:else>
			<div class="alert alert-info">
				This student do not have any invoice.
			</div>
		</s:else>
		<div class="spaceDiv"></div>
		<s:if test="%{tempList!= null && !tempList.isEmpty()}">
			<h5 class="pagaeTitle bold">
				<strong> Other Fee Payment Receipts</strong>
			</h5>
			<table class="table table-striped table-bordered table-hover table-full-width" id="sample_4">
				<thead>
					<tr>
						<th>
							Invoice Date   
						</th>
						<th>
							Paid Amount
						</th>
						<th>
							Download
						</th>
						<!-- <th>
							Delete
						</th> -->
					</tr>
				</thead>
				<tbody>
					<s:iterator value="tempList">
						<tr>
							<td>
								<s:property value="paymentDateStr" />
							</td>
							<td>
								<s:property value="fineFeeAmount" />
							</td>
							<td>
								<s:if test='%{invoiceNumber > 0}'>
									<a style="cursor: pointer;" id="noPrint"
										onClick="javascript:printStudentFineFeeInvoice('<s:property value='anyId'/>','<s:property value='invoiceNumber'/>',<s:property value="fineFeeAmount" />,'<s:property value="paymentDateStr" />')";
                                    target="_new"><img
											style="" src="../images/index.jpg"> Invoice : <s:property
											value="invoiceNumber" /> </a>
								</s:if>
							</td>
							<%-- <td>
									<a data-toggle="modal" href="#popupDeleteInvoiceDiv"
								class="btn btn-circle btn-xs red"
								onclick="javascript:getAjaxDeleteInvoice(<s:property value="studentId" />,<s:property value="invoiceNumber" />,<s:property value="academicYearId" />);"><i class="fa fa-trash-o"></i></i></a>
							</td> --%>
						</tr>
					</s:iterator>
				</tbody>
			</table>
		</s:if>
		<s:if test="%{objectList!= null && !objectList.isEmpty()}">
			<h5 class="pagaeTitle bold">
				<strong>Future Academic Payment Receipts</strong>
			</h5>
			<table
				class="table table-striped table-bordered table-hover table-full-width"
				id="sample_4">
				<thead>
					<tr>
						<td>
							Invoice Date
						</td>
						<td>
							Paid Amount
						</td>
						<td>
							Download
						</td>
						<!-- <td>
							Delete
						</td> -->
					</tr>
				</thead>
				<tbody>
					<s:iterator value="objectList">
						<tr>
							<td>
								<s:property value="paymentDateStr" />
							</td>
							<td>
								<s:property value="paidAmount+fineAmount" />
							</td>
							<td>
								<s:if test='%{invoiceNumber > 0}'>
									<a style="cursor: pointer" id="noPrint"
										onClick="javascript:printFutureAcademicPaymentPreview('<s:property value='studentId'/>',
                                        '<s:property value='ctrPaymentDateStr'/>',
                                        '<s:property value='invoiceNumber'/>','<s:property value="acdmcYearRange"/>','',<s:property value="paidAmount" />+<s:property value="fineAmount" />,'download')"
										; target="_new"><img style="" src="../images/index.jpg">
										Invoice : <s:property value="invoiceNumber" /> </a>
								</s:if>
							</td>
							<%-- <td>
									<a data-toggle="modal" href="#popupDeleteInvoiceDiv"
								class="btn btn-circle btn-xs red"
								onclick="javascript:getAjaxDeleteInvoice(<s:property value="studentId" />,<s:property value="invoiceNumber" />,<s:property value="academicYearId" />);"><i class="fa fa-trash-o"></i></i></a>
							</td> --%>
						</tr>
					</s:iterator>
				</tbody>
			</table>
		</s:if>
	</div>
</div>
<div id="popupDeleteInvoiceDiv"></div>
<script>
jQuery(document).ready(function() {       
   UIExtendedModals.init();
});
function getAjaxDeleteInvoice(studentId,invoiceNumber,academicYearId) 
{
	var r=confirm('Are you sure you want to delete invoice details ?');
	//alert(r);
	if (r==true)
	 {
	 $("#popupDeleteInvoiceDiv")
				.html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
	 		var pars = "studentNumber="+studentId+"&tempString="+invoiceNumber+"&academicYearId="+academicYearId;
			var url = jQuery.url.getChatURL("/schoolfee/ajaxDoDeleteInvoice.do");
			$.ajax( {
				url : url,
				cache : false,
				data : pars,
				success : function(html) {
				$("#popupDeleteInvoiceDiv").html(html);
					$("#popupDeleteInvoiceDiv").show();
				}
			});
	  }else
		  $("#popupDeleteInvoiceDiv").hide();
	
	} 
</script>
