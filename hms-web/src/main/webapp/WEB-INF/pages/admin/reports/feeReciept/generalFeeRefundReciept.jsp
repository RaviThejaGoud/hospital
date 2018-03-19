<%@ include file="/common/taglibs.jsp"%>
<%
	String[] alphabet = { "A", "B", "C", "D", "E", "F", "G", "H", "I","J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U","V", "W", "X", "Y", "Z" };
%>
<%
	String[] color = { "Blue", "Blue" };
%>
<div style="width: 100%;">
	<div class="invoice-logo-space">
		<s:if test="%{customer.customerLogo != null &&  customer.customerLogo != ''}">
			<img src='<c:url value="${customer.customerLogo}"/>'
				alt='<s:property  value="customer.customerLogo" />' height="64px"
				width="90px" border="0" style="float: left;" />&nbsp;
			   </s:if>
	</div>
	<div>
		<b class="orgHeader"> <s:property value="customer.organization" /> </b>
		<br />
		<span class="receptFontSubHeader" style="font-size: 9px;"> 
			<s:if test="%{customer.address.streetName != null && customer.address.streetName != ''}">
				<b><s:property value="customer.address.streetName" />, </b>
			</s:if> 
			<s:if test="%{customer.address.addressLine1 != null && customer.address.addressLine1 != ''}">
				<b><s:property value="customer.address.addressLine1" /> </b>
			</s:if> 
			<s:if test="%{customer.address.city != null && customer.address.city != ''}">
				<b><s:property value="customer.address.city" /> -</b>
			</s:if> 
			<s:if test="%{customer.address.postalCode != null && customer.address.postalCode != ''}">
				<b><s:property value="customer.address.postalCode" /> ,</b>
			</s:if> <br /> 
			<s:if test="%{customer.contactNumber != null && customer.contactNumber != ''}">
				<b>Phone: <s:property value="customer.contactNumber" />, </b>
			</s:if> 
			<s:if test="%{customer.mobileNumber != null && customer.mobileNumber != ''}">
				<b><s:property value="customer.mobileNumber" /> .</b>
			</s:if> </span>
		<br />
		<b style="text-align: center; font-size: 12px;">REFUND FEE RECEIPT - <s:property value="viewStudentClassDetails.academicYear" /> </b>
	</div>
</div>
<hr style="margin: 2px;" />
<table style="font-size: 9px; margin: 5px;" width="100%">
	
	<tr>
		<td>
			<label >
				Receipt No&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:
			</label>
			<s:if test="%{customer.feeReceiptNoWithCustName != null &&  customer.feeReceiptNoWithCustName != ''}">
				<b><s:property value="customer.feeReceiptNoWithCustName.toUpperCase()" /> &nbsp;/&nbsp;<s:property value="anyId" />
				</b>
			</s:if>
			<s:elseif test='%{academicYear.receiptGenerationType=="M"}'>
				<b><s:property value="alertSendType" /> </b>
			</s:elseif>
			<s:else>
				<b><s:property value="anyId" /> </b>
			</s:else>
		</td>
		<s:if test="%{studentFeeRefund != null}">
		<td>
			<label >
				Refund Date&nbsp;:
			</label>
			<b><s:property value="studentFeeRefund.refundDateStr" /> </b>
		</td>
		</s:if>
		
	</tr>
	
	<tr>
		<td colspan="2">
			<label >
				Name&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:
			</label>
			<b><s:property value="viewStudentClassDetails.fullName" /> </b>
		</td>
	</tr>
	
	<tr>
		<td>
			<label >
				Adm No&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:
			</label>
			<b><s:property value="viewStudentClassDetails.admissionNumber" /></b>
		</td>
		<td class="test2">
			<label >
				Father Name&nbsp;&nbsp;:
			</label>
			<b><s:property value="viewStudentClassDetails.fatherName" />
			</b>
		</td>
	</tr>
	
</table>
<div class="invoice">
	<div class="row">
		<div class="col-xs-12">
		<s:if test="%{studentFeeRefund != null}">
			<table class="table table-striped table-hover ">
				<s:set name="schoolTermsId" value=""></s:set>
				
					<% int i = 0; %>
					<% int j = 0; %>
					<thead>
						<tr class="trHeadding">
							<th style="border: 1px solid #222;border-right: 1px solid;width: 10%;"text-align: center;">
								&nbsp;S.No
							</th>
							<th colspan="4" style="text-align: center;border: 1px solid #222;border-right: 1px solid;width: 75%;">
								Particulars
							</th>
							<th style="text-align: center;border: 1px solid #222;border-right: 1px solid;width: 40%;">
									Fee Amount&nbsp;
							</th>
						</tr>
					</thead>
						<table class="table table-striped table-hover">
						<tbody>
						<tr style="font-size: 10px;">
							<td style="text-align: right; border-bottom: 1px solid #222; border-top: 1px solid  #222; border-left: none; border-right: 1px solid;width: 10%;">
								<b style="font-size: 9px;">
									<center>
										<font color="<%=color[(int) (2 * Math.random())]%>">1
									</center> 
								</b>
							</td>
							<td style="text-align: right; border-bottom: 0px solid; border-top: 1px solid; border-left: none; border-right: 1px solid;width: 75%;" colspan="4">
								<label>
									<b>Total Paid Amount&nbsp;</b>
								</label>
							</td>
							<td style="text-align: right; border-bottom: 0px solid; border-top: 1px solid; border-left: none;width: 40%;">
								<b> 
									<c:set value="${studentFeeRefund.totalFeeAmount}" var="priceAmt" /> 
									<fmt:formatNumber value="${priceAmt}" type="currency" pattern="##,##,###.00" var="balance" /> 
									<s:property value="country.countryCurrency.currencySymbol" escapeHtml="false"/> 
										<c:out value="${balance}" />
								</b>
							</td>
						</tr>
						<tr style="font-size: 10px;">
							<td style="text-align: right; border-bottom: 1px solid #222; border-top: 1px solid  #222; border-left: none; border-right: 1px solid;width: 10%;">
								<b style="font-size: 9px;">
									<center>
										<font color="<%=color[(int) (2 * Math.random())]%>">2
									</center> 
								</b>
							</td>
							<td style="text-align: right; border-bottom: 1px solid; border-top: 1px solid; border-left: none; border-right: 1px solid; font-size: 10px;width: 75%;" colspan="4">
								<label>
									<b>Refund Amount&nbsp;</b>
								</label>
							</td>
							<td style="text-align: right; border-bottom: 1px solid; border-top: 1px solid; border-left: none;width: 40%;">
								<b> 
									<s:if test="%{studentFeeRefund.refundAmount > 0}">
										<c:set value="${studentFeeRefund.refundAmount}" var="priceAmt" />
										<fmt:formatNumber value="${priceAmt}" type="currency" pattern="##,##,###.00" var="balance" />
										<s:property value="country.countryCurrency.currencySymbol" escapeHtml="false"/> <c:out value="${balance}" />
									</s:if> 
									<s:else>
										0.00
									</s:else> 
								</b>
							</td>
						</tr>
						
						<%-- <s:if test='%{customer.showBalanceAmountInFeeReceipt =="Y"}'>
							<tr style="font-size: 10px;">
								<td style="text-align: right; border-bottom: 1px solid; border-top: 1px solid; border-left: none; border-right: 1px solid; font-size: 10px;width: 80%;" colspan="5">
									<label>
										<b>Total Balance Amount&nbsp;</b>
									</label>
								</td>
								<td style="text-align: right; border-bottom: 1px solid; border-top: 1px solid; border-left: none;width: 20%;">
									<b> 
										<s:if test="%{tempString3 > 0}">
											<c:set value="${tempString3}" var="priceAmt" />
											<fmt:formatNumber value="${priceAmt}" type="currency" pattern="##,##,###.00" var="balance" />
											<s:property value="country.countryCurrency.currencySymbol" escapeHtml="false"/> <c:out value="${balance}" />
										</s:if> 
										<s:else>
											0.00
										</s:else> 
									</b>
								</td>
							</tr>
						</s:if> --%>
						<tr style="font-size: 10px;">
							<td style="text-align: left; border-bottom: 1px solid; border-top: 1px solid; border-left: none;" colspan="6">
								<center>
									<b>Total (in words)</b>&nbsp;
									<s:if test="%{studentFeeRefund.refundAmount > 0}">
										<s:property value="plTitle" /> <s:property value="country.countryCurrency.currencyWord" escapeHtml="false"/> Only
									</s:if>
									<s:else>
										Zero <s:property value="country.countryCurrency.currencyWord" escapeHtml="false"/> only
									</s:else>
								</center>
							</td>
						</tr>
					</tbody>
			</table>
			</s:if>
		</div>
	</div>
	<!-- <div class="row">
		<div class="col-xs-8 smallFonts" style="float:left;text-align: left;">
			<font color="red"><b>Note</b> </font> : Fees will not be refunded at
			any circumstances.
		</div>
		<div class="col-xs-4 smallFonts">
			&nbsp;
		</div>
	</div> -->
	<div class="row">
		<div class="col-xs-7 smallFonts" style="float:left;text-align: left;">
			&nbsp;
		</div>
		<div class="col-xs-4 smallFonts" style="margin-top: 40px;">
			<b class="receptFontSubHeaderRow"> Authorized Signature</b>
		</div>
		
	</div>
</div>
<style>
.invoice table {
  margin:0px 0 0px;
}
.test1 {
	width: 25em;
	border: 0px solid #000000;
	word-break: keep-all;	
}

.test2 {
	border: 0 solid #000000;
	width: 24em;
	word-break: normal;
}
.smallFonts{
	font-size: 9px;
 	margin: 5px;
}
@media print 
{
	page[size="A5"] {
  width: 21cm;
  height: 10cm;  
  size: landscape;
}
  a[href]:after { content: none !important; }
  img[src]:after { content: none !important; }
}
</style>
<script  type="text/javascript">
for(var j=0;j<3;j++){
	var x = document.getElementsByClassName("tabTotalTDClass")[j];

	var len = x.rows.length;
	var loopLength=6-len;
	for(var i=0;i<=loopLength;i++){
		var new_row = x.rows[1].cloneNode(true);
		var tdData=new_row.getElementsByTagName("td")[0];
		var tdData1=new_row.getElementsByTagName("td")[1];
		var tdData2=new_row.getElementsByTagName("td")[2];	
		//alert(tdData.innerHTML+"-"+tdData1.innerHTML+"-"+tdData2.innerHTML);
		tdData.innerHTML="&nbsp;";
		tdData1.innerHTML="&nbsp;";
		tdData2.innerHTML="&nbsp;";
		x.appendChild(new_row);	
	 }
}
</script>