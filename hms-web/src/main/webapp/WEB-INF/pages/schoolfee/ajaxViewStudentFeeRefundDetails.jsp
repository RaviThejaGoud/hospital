<%@ include file="/common/taglibs.jsp"%>
<jsp:include page="/common/messages.jsp" />
<s:if test="%{studentsList != null && !studentsList.isEmpty()}">
	<s:form id="studentFeeRefundExcel" method="post" theme="simple" cssClass="form-horizontal" 
		action="ajaxGenerateStudentFeeRefundExcel" namespace="/reports">
		<div align="right">
			<s:submit value="Download Fee Refund Details" cssClass="btn green"
				cssStyle="float:none;" />
		</div>
	</s:form>
	<table class="table table-striped table-bordered table-hover table-full-width" id="sample_2">
		<thead>
			<tr>
				<th>
					#Admission
				</th>
				<th>
					Student Name
				</th>
				<th>
				 	Class & Section
				</th>
				<th>
				 	Total Paid Amount
				</th>
				<th>
					Refunded Amount
				</th>
				<th>
					Refunded Date
				</th>
				<th>
					Receipt 
				</th>
			</tr>
		</thead>
		<tbody>
		<s:set var="classNameId" value=""></s:set>
			<s:iterator value="studentsList" status="stat">
				<s:set var="studentDetailsId" value="studId"></s:set>
				<tr>
					<td>
						<s:property value="admissionNumber" />
					</td>
					<td>
						<s:property value="firstName" /> <s:property value="lastName" />
					</td>
					<td>	
						<s:property value="classNameAndSection" />
					</td>
					<td>
						<s:property value="totalFeeAmount" />
					</td>
					<td>
						<s:property value="refundAmount" />
					</td>
					<td>
						<s:property value="refundDateStr" />
					</td>
					<%-- <td>
						<s:property value="studentsList[#stat.count-1][8]" />
					</td> --%>
					<%-- <td align="center">
						 <s:url id="editPayment" namespace="/schoolfee" action="ajaxFeeSettingInvoice" includeParams="all" escapeAmp="false">
							<s:param name="id" value="%{studentsList[#stat.count-1][7]}" />
							<s:param name="paymentStatus" value="%{paymentStatus}" />
							<s:param name="description">prevStudInvPaymt</s:param>
						</s:url>
						<sj:a href="%{editPayment}" targets="searchStudentsForm112"> Pay </sj:a> / 
						<a data-toggle="modal"  href="#popupStudPaymentDiv"  
								onclick="javascript:popupViewFeePayments(<s:property value="%{studentsList[#stat.count-1][7]}" />,'P',null);"> View
						</a>
					</td> --%>
					<td align="center">
								<%--  <s:url id="refundDownload" namespace="/reports" action="ajaxStudentFeeRefundPdfReport" includeParams="all" escapeAmp="false">
									<s:param name="spId" value="%{studentsList[#stat.count-1][0]}" />
									<s:param name="paymentStatus" value="%{paymentStatus}" />
								</s:url>
						<sj:a href="%{refundDownload}" targets="searchStudentsForm112" cssClass="btn btn-xs blue"> Download </sj:a> --%> 
						<s:if test="%{invoiceNumber != null}">
							<a style="cursor: pointer" id="noPrint"
								onClick="javascript:printFeeRefundPreview('<s:property value='studentId'/>','<s:property value='refundAmount'/>')"; target="_new"><img style="" src="../images/index.jpg">
								Invoice: <s:property value="invoiceNumber" />  
							</a>
						</s:if>
						<s:elseif test="%{invoiceString != null}">
							<a style="cursor: pointer" id="noPrint"
							onClick="javascript:printFeeRefundPreview('<s:property value='studentId'/>','<s:property value='refundAmount'/>')"; target="_new"><img style="" src="../images/index.jpg">
							Invoice: <s:property value="invoiceString" />  
							</a>
						</s:elseif>
						
										
					<%-- <a data-toggle="modal"  href="#popupDownloadReceiptDiv" class="btn btn-xs blue" onclick="javascript:PopupStudentDownloadReceipt(<s:property value="studentsList[#stat.count-1][7]" />,<s:property value="academicYearId" />);"> Download </a> --%>
					</td>
					
					</tr>
				<s:set var="classNameId" value="classId"></s:set>
			</s:iterator>
		</tbody>
	</table>
</s:if>
<s:else>
	<div class="alert alert-info">
		Currently there are no Fee refund details available 
	</div>
</s:else>
<s:if test='%{studentPayment.paymentType!="CL" }'>
	<form method="post" id="printReport"
		action="javaScript:printPreview('<s:property value="studentNumber" />','<s:property value="todayDate"/>','<s:property value="quizId" />','<s:property value="empId" />','payandprint','<s:property value="tempId1" />','<s:property value="alertSendType"/>')"
		style="display: none;"></form>
</s:if>
<s:if test='%{studentPayment.paymentType=="CL" }'>
	<form method="post" id="printChallana"
		action="javaScript:printChallanaPreview('<s:property value="studentNumber" />','<s:property value="todayDate"/>','<s:property value="quizId" />','<s:property value="empId" />','payandprint','<s:property value="tempId1" />','<s:property value="alertSendType"/>')"
		style="display: none;"></form>
</s:if>
<div id="popupDownloadReceiptDiv"></div>
<!-- <div id="popupFinePaymentDiv"></div> -->
<div id="popupStudPaymentDiv"></div>
<script type="text/javascript">
$(document).ready(function() {
	/* var selected =$('input[name=SelectType]:checked').val();
	if(selected == 'Select Class'){
		$("div.alert-info").html('Currently there are no students found to selected class.');
	} */
});
	 TableAdvanced.init();
	UIExtendedModals.init();
	
	
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

	    //return convert_number(value) + " Rupees " + f_text + " Only";
	    return convert_number(value);
	}
		
</script>
