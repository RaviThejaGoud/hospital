<%@ include file="/common/taglibs.jsp"%>
<jsp:include page="/common/messages.jsp"></jsp:include>
<s:if test="%{viewStudentClassDetails!= null}">
	<div class="portlet-body form">
		<form class="form-horizontal">
			<div class="form-body">
				<h3 class="form-section">Student Info</h3>

				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-3">Student Name : </label>
							<div class="col-md-9">
								<p class="form-control-static">
									<s:property value="viewStudentClassDetails.personFullName" />
								</p>
							</div>
						</div>
					</div>

					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4">Admission Number : </label>
							<div class="col-md-8">
								<p class="form-control-static">
									<s:property value="viewStudentClassDetails.admissionNumber" />
								</p>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-3">Class & Section : </label>
							<div class="col-md-9">
								<p class="form-control-static">
									<s:property value="viewStudentClassDetails.classAndSection" />
								</p>
							</div>
						</div>
					</div>

					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4">Parent Name : </label>
							<div class="col-md-8">
								<p class="form-control-static">
									<s:property value="viewStudentClassDetails.fatherName" />
								</p>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-3">Mobile Number : </label>
							<div class="col-md-9">
								<p class="form-control-static">
									<s:property value="viewStudentClassDetails.mobileNumber" />
								</p>
							</div>
						</div>
					</div>

					<div class="col-md-6">
						&nbsp;
					</div>
				</div>
			</div>
		</form>
	</div>

	<div class="modal-header">

		<h4 class="modal-title">View Payment Receipts</h4>
	</div>
	<div class="modal-body">
		<s:if test="%{schoolFeeList!= null && !schoolFeeList.isEmpty()}">
			<h5 class="pagaeTitle bold">
				<strong>Payment Receipts</strong>
			</h5>
			<table
				class="table table-striped table-bordered table-hover table-full-width"
				id="sample_3">
				<thead>
					<tr>
						<th>Invoice Date</th>
						<th>Paid Amount</th>
						<th>Invoice Number</th>
						<th>Delete</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="schoolFeeList">
						<tr>
							<td><s:property value="paymentDateStr" /></td>
							<td><s:property value="paidAmount+fineAmount" /></td>
							<td><s:if test='%{invoiceNumberStr > 0 || invoiceNumberStr != null}'>
										<s:property value="invoiceNumberStr" />
								</s:if> <s:else>
										<s:property value="invoiceNumber" />
								</s:else></td>
							<td><a data-toggle="modal" href="#popupDeleteInvoiceDiv"
								class="btn btn-circle btn-xs red"
								onclick="javascript:getAjaxDeleteInvoice(<s:property value="student.id" />,<s:property value="custId" />,<s:property value="academicYear.id" />,<s:property value="id" />,'S');"><i
									class="fa fa-trash-o"></i></i></a></td>
						</tr>
					</s:iterator>
				</tbody>
			</table>
		</s:if>
		<s:else>
			<div class="alert alert-info">This student do not have any
				invoice.</div>
		</s:else>
		<div class="spaceDiv"></div>
		<s:if test="%{tempList!= null && !tempList.isEmpty()}">
			<h5 class="pagaeTitle bold">
				<strong> Other Fee Payment Receipts</strong>
			</h5>
			<table
				class="table table-striped table-bordered table-hover table-full-width"
				id="sample_4">
				<thead>
					<tr>
						<th>Invoice Date</th>
						<th>Paid Amount</th>
						<th>Invoice Number</th>
						<th>Delete</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="tempList">
						<tr>
							<td><s:property value="paymentDateStr" /></td>
							<td><s:property value="fineFeeAmount" /></td>
							<td><s:if test='%{invoiceNumber > 0}'>
									<%-- <a style="cursor: pointer;" id="noPrint"
										onClick="javascript:printStudentFineFeeInvoice('<s:property value='anyId'/>','<s:property value='invoiceNumber'/>',<s:property value="fineFeeAmount" />)"
										;
                                    target="_new"><img
										style="" src="../images/index.jpg"> Invoice :  </a> --%>
										<s:property value="invoiceNumber" />
								</s:if></td>
							<td><a data-toggle="modal" href="#popupDeleteInvoiceDiv"
								class="btn btn-circle btn-xs red"
								onclick="javascript:getAjaxDeleteInvoice(<s:property value="viewStudentClassDetails.studId" />,<s:property value="custId" />,<s:property value="academicYearId"/>,<s:property value="invoiceNumber" />,'O');"><i
									class="fa fa-trash-o"></i></i></a></td>
						</tr>
					</s:iterator>
				</tbody>
			</table>
		</s:if>
	</div>
</s:if>
<%-- <s:else>
		Please search student exart student admission number.
	</s:else> --%>

<div id="popupDeleteInvoiceDiv"></div>

<script>
jQuery(document).ready(function() {       
   UIExtendedModals.init();
});
function getAjaxDeleteInvoice(studentId,custId,academicYearId,studentPaymentId,deleteType) 
{
	var r=confirm('Are you sure you want to delete invoice details ?');
	//alert(r);
	if (r==true)
	 {
	 $("#popupDeleteInvoiceDiv").html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
	 		var pars = "studentNumber="+studentId+"&tempString="+custId+"&academicYearId="+academicYearId+"&selectedId="+studentPaymentId+"&tempString1="+deleteType;
			var url = jQuery.url.getChatURL("/schoolfee/ajaxDoDeleteInvoice.do");
			//alert(url+pars)
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

