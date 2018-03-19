<%@ include file="/common/taglibs.jsp"%>
<%
	String[] alphabet = { "A", "B", "C", "D", "E", "F", "G", "H", "I","J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U","V", "W", "X", "Y", "Z" };
%>
<%
	String[] color = { "Blue", "Blue" };
%>
<div style="width: 100%;">
	<div class="invoice-logo-space" style="height: 2cm;">
			&nbsp;
	</div>
	
</div>
<hr style="margin: 2px;" />
<table style="font-size: 9px; margin: 5px;height: 2cm;" width="100%">
	
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
		<td>
			<label >
				Payment Date&nbsp;:
			</label>
			<b><s:property value="anyTitle" /> </b>
		</td>
		<td>
			<label >
				Adm No&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:
			</label>
			<b><s:property value="viewStudentClassDetails.admissionNumber" /></b>
		</td>
		
	</tr>
	
	<tr>
		<td >
			<label >
				Name&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:
			</label>
			<b><s:property value="viewStudentClassDetails.fullName" /> </b>
		</td>
		<td>
			<label>
				Mobile&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:
			</label>
			<b><s:property value="viewStudentClassDetails.mobileNumber" /> </b>
		</td>
		<td class="test2">
			<label >
				Father Name&nbsp;&nbsp;:
			</label>
			<b><s:property value="viewStudentClassDetails.fatherName" />
			</b>
		</td>
	</tr>
	
	<tr>
		
		
	</tr>
	<s:if test="%{(studentPaymentList != null && !studentPaymentList.isEmpty()) || (studentTransportTermsList != null && !studentTransportTermsList.isEmpty())}">
		<s:set name="banksId" value=""></s:set>
		<s:if test="%{studentPaymentList != null && !studentPaymentList.isEmpty()}">
		<s:iterator value="studentPaymentList" begin="0" end="0">
			<%-- <s:if test="%{bankId != #banksId}"> --%>
				<tr>
					<td>
						<label >
							Class&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:
						</label>
						<b><s:property value="viewStudentClassDetails.classAndSection" />
						</b>
					</td>
					<td class="test2">
						<label >
							Payment Type&nbsp;:
						</label>
						
						<s:if test='%{paymentType == "D"}'>
							<b>DD</b>
						</s:if>
						<s:elseif test='%{paymentType == "CH"}'>
							<b> Cheque </b>
						</s:elseif>
						<s:elseif test='%{paymentType == "NEFT"}'>
							<b> NEFT </b>
						</s:elseif>
						<s:elseif test='%{paymentType == "CS"}'>
							<b> Card Swipe </b>
						</s:elseif>
						<s:else>
							<b>Cash </b>
						</s:else>
					</td>
					<s:if test='%{paymentType != "C"}'>
					<s:if test="%{ddNumber != null &&  ddNumber!=''}">
						<td>
							<label >
								DD Number :
							</label>
							<b><s:property value="ddNumber" /> </b>
						</td>
					</s:if>
					<s:if test="%{transactionNumber != null &&  transactionNumber!=''}">
						<s:if test="%{transactionNumber > 0}">
							<td>
								<label style="text-align: right; width: 73px;">
									Transaction Number :
								</label>
								<b><s:property value="transactionNumber" /> </b>
							</td>
						</s:if>
					</s:if>
					<s:if test="%{chequeNumber != null &&  chequeNumber!=''}">
						<td>
							<label >
								Cheque No&nbsp;&nbsp;&nbsp;&nbsp;:
							</label>
							<b><s:property value="chequeNumber" /> </b>
						</td>
					</s:if>
					</s:if>
				</tr>
				<tr>
					<s:if test='%{paymentType != "C"}'>
				<tr>
					
					<s:if test="%{bankName != null &&  bankName!='' &&  bankName!='--Select Bank Name--'}">
						<td>
							<label >
								Bank Name&nbsp;&nbsp;&nbsp;&nbsp;:
							</label>
							<b><s:property value="bankName" /> </b>
						</td>
					</s:if>
					<s:if test="%{branchName != null &&  branchName!=''}">
						<td class="test2">
							<label >
								Branch Name&nbsp;:
							</label>
							<b><s:property value="branchName" /> </b>
						</td>
					</s:if>
				</tr>
				
				
				</s:if>
					
				</tr>
				
			<%-- </s:if>
			<s:set name="banksId" value="%{bankId}"></s:set> --%>
		</s:iterator>
		</s:if>
		<s:else>
			<s:iterator value="studentTransportTermsList" begin="0" end="0">
			<%-- <s:if test="%{bankId != #banksId}"> --%>
				<tr>
					<td>
						<label >
							Class&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:
						</label>
						<b><s:property value="viewStudentClassDetails.classAndSection" />
						</b>
					</td>
					<td class="test2">
						<label >
							Payment Type&nbsp;:
						</label>
						
						<s:if test='%{paymentType == "D"}'>
							<b>DD</b>
						</s:if>
						<s:elseif test='%{paymentType == "CH"}'>
							<b> Cheque </b>
						</s:elseif>
						<s:elseif test='%{paymentType == "NEFT"}'>
							<b> NEFT </b>
						</s:elseif>
						<s:elseif test='%{paymentType == "CS"}'>
							<b> Card Swipe </b>
						</s:elseif>
						<s:else>
							<b>Cash </b>
						</s:else>
					</td>
					<s:if test='%{paymentType != "C"}'>
					<s:if test="%{ddNumber != null &&  ddNumber!=''}">
						<td>
							<label >
								DD Number :
							</label>
							<b><s:property value="ddNumber" /> </b>
						</td>
					</s:if>
					<s:if test="%{transactionNumber != null &&  transactionNumber!=''}">
						<s:if test="%{transactionNumber > 0}">
							<td>
								<label style="text-align: right; width: 73px;">
									Transaction Number :
								</label>
								<b><s:property value="transactionNumber" /> </b>
							</td>
						</s:if>
					</s:if>
					<s:if test="%{chequeNumber != null &&  chequeNumber!=''}">
						<td>
							<label >
								Cheque No&nbsp;&nbsp;&nbsp;&nbsp;:
							</label>
							<b><s:property value="chequeNumber" /> </b>
						</td>
					</s:if>
					</s:if>
				</tr>
				<tr>
					<s:if test='%{paymentType != "C"}'>
				<tr>
					
					<s:if test="%{bankName != null &&  bankName!='' &&  bankName!='--Select Bank Name--'}">
						<td>
							<label >
								Bank Name&nbsp;&nbsp;&nbsp;&nbsp;:
							</label>
							<b><s:property value="bankName" /> </b>
						</td>
					</s:if>
					<s:if test="%{branchName != null &&  branchName!=''}">
						<td class="test2">
							<label >
								Branch Name&nbsp;:
							</label>
							<b><s:property value="branchName" /> </b>
						</td>
					</s:if>
				</tr>
				
				
				</s:if>
					
				</tr>
				
			<%-- </s:if>
			<s:set name="banksId" value="%{bankId}"></s:set> --%>
		</s:iterator>
		</s:else>
	</s:if>
</table>
<div class="invoice" style="height: 8cm;">
	<div class="row">
		<div class="col-xs-12">
			<table class="table table-striped table-hover">
				<s:set name="schoolTermsId" value=""></s:set>
				<s:if test="%{(studentPaymentList != null && !studentPaymentList.isEmpty()) || (studentTransportTermsList != null && !studentTransportTermsList.isEmpty())}">
					<% int i = 0; %>
					<% int j = 0; %>
					<thead>
						<tr class="trHeadding">
							<th style="border: 1px solid #222;border-right: 1px solid;">
								&nbsp;S.No
							</th>
							<th colspan="4" style="text-align: center;border: 1px solid #222;border-right: 1px solid;">
								Terms & Particulars
							</th>
							<th style="text-align: right;border: 1px solid #222;border-right: 1px solid;">
									Fee Amount&nbsp;
							</th>
						</tr>
					</thead>
					<tbody>
						<s:set var="invoiceTotAmt" value="0" />
						<s:if test="%{studentPaymentList != null && !studentPaymentList.isEmpty()}">
						<s:iterator value="studentPaymentList">
							<% i++; %>
							<s:if test="%{schoolTermId != #schoolTermsId}">
								<% i = 1; %>
								<tr>
									<td style="text-align: right; border-bottom: 1px solid #222; border-top: 1px solid  #222; border-left: none; border-right: 1px solid; width: 30px">
										<b style="font-size: 11px;">
											<center>
												<font color="<%=color[(int) (2 * Math.random())]%>"><%=alphabet[j]%>
											</center> 
										</b>
									</td>
									<td
										style="text-align: center; border-bottom: 0px solid; border-top: 0px solid; border-left: none; border-right: 1px solid; font-size: 10px;">
										<s:if test='%{settingName == "Non Term Fee"}'>
											 FEES FOR THE YEAR OF :
										</s:if>
										<s:else>
											FEES FOR THE MONTH OF :
										</s:else>
										<b><s:property value="termName" /> </b>
									</td>
								</tr>
								<% j++; %>
							</s:if>
							<tr>
								<td style="text-align: right; border-bottom: 1px solid #222; border-left: none; border-right: 1px solid #222; width: 30px">
									<b style="font-size: 10px;">
										<center> <%=i%> </center> 
									</b>
								</td>
								<td colspan="4" style="text-align: right; border: none; border-right: 1px solid; font-size: 10px;">
									<s:property value="feeType" />
									&nbsp;
								</td>
								<td style="text-align: right; border: none;">
									<b style="font-size: 10px;"> 
										<s:set var="total" value="%{paymentAmount + discountAmount}" /> 
										<c:set value="${total}" var="priceAmt" /> 
										<fmt:formatNumber value="${total}" type="currency" pattern="##,##,###.00" var="balance" /> <c:out value="${balance}" /> 
									</b>
									<s:set var="invoiceTotAmt" value="%{#invoiceTotAmt + #total}" />
								</td>
							</tr>
							<s:set name="schoolTermsId" value="%{schoolTermId}"></s:set>
						</s:iterator>
						</s:if>
						<s:if test="%{studentTransportTermsList != null && !studentTransportTermsList.isEmpty()}">
						<s:iterator value="studentTransportTermsList">
							<% i++; %>
							<s:if test="%{schoolTermId != #schoolTermsId}">
								<% i = 1; %>
								<tr>
									<td style="text-align: right; border-bottom: 1px solid #222; border-top: 1px solid  #222; border-left: none; border-right: 1px solid; width: 30px">
										<b style="font-size: 11px;">
											<center>
												<font color="<%=color[(int) (2 * Math.random())]%>"><%=alphabet[j]%>
											</center> 
										</b>
									</td>
									<td
										style="text-align: center; border-bottom: 0px solid; border-top: 0px solid; border-left: none; border-right: 1px solid; font-size: 10px;">
										<s:if test='%{settingName == "Non Term Fee"}'>
											 FEES FOR THE YEAR OF :
										</s:if>
										<s:else>
											FEES FOR THE MONTH OF :
										</s:else>
										<b><s:property value="termName" /> </b>
									</td>
								</tr>
								<% j++; %>
							</s:if>
							<tr>
								<td style="text-align: right; border-bottom: 1px solid #222; border-left: none; border-right: 1px solid #222; width: 30px">
									<b style="font-size: 10px;">
										<center> <%=i%> </center> 
									</b>
								</td>
								<td colspan="4" style="text-align: right; border: none; border-right: 1px solid; font-size: 10px;">
									<s:property value="feeType" />
									&nbsp;
								</td>
								<td style="text-align: right; border: none;">
									<b style="font-size: 10px;"> 
										<s:set var="total" value="%{paymentAmount + discountAmount}" /> 
										<c:set value="${total}" var="priceAmt" /> 
										<fmt:formatNumber value="${total}" type="currency" pattern="##,##,###.00" var="balance" /> <c:out value="${balance}" /> 
									</b>
									<s:set var="invoiceTotAmt" value="%{#invoiceTotAmt + #total}" />
								</td>
							</tr>
							<s:set name="schoolTermsId" value="%{schoolTermId}"></s:set>
						</s:iterator>
						</s:if>
						<s:if test="%{paymentAmount > 0}">
							<tr style="font-size: 10px;">
								<td style="text-align: right; border-bottom: 0px solid; border-top: 1px solid; border-left: none; border-right: 1px solid;" colspan="5">
									<label>
										<b>Late Fee &nbsp;</b>
									</label>
								</td>
								<td style="text-align: right; border-bottom: 0px solid; border-top: 1px solid; border-left: none;">
									<b> 
										<s:set var="invoiceTotAmt" value="%{#invoiceTotAmt + paymentAmount}" />
										<c:set value="${paymentAmount}" var="fineAmount" /> 
										<fmt:formatNumber value="${fineAmount}" type="currency" pattern="##,##,###.00" var="fineAmount" /> 
										<c:out value="${fineAmount}" /> 
									</b>
								</td>
							</tr>
						</s:if>
						<tr style="font-size: 10px;">
							<td style="text-align: right; border-bottom: 0px solid; border-top: 1px solid; border-left: none; border-right: 1px solid;" colspan="5">
								<label>
									<b>Total Amount&nbsp;</b>
								</label>
							</td>
							<td style="text-align: right; border-bottom: 0px solid; border-top: 1px solid; border-left: none;">
								<b> 
									<c:set value="${invoiceTotAmt}" var="priceAmt" /> 
									<fmt:formatNumber value="${priceAmt}" type="currency" pattern="##,##,###.00" var="balance" /> 
									<c:out value="${balance}" /> 
								</b>
							</td>
						</tr>
						<s:if test="%{wishTitle != null}">
							<tr style="font-size: 10px;">
								<td style="text-align: right; border-bottom: 0px solid; border-top: 1px solid; border-left: none; border-right: 1px solid;" colspan="5">
									<label>
										<b>Excess Amount&nbsp;</b>
									</label>
								</td>
								<td style="text-align: right; border-bottom: 0px solid; border-top: 1px solid; border-left: none;">
									<b> 
										<c:set value="${wishTitle}" var="priceAmt" /> 
										<fmt:formatNumber value="${priceAmt}" type="currency" pattern="##,##,###.00" var="balance" /> 
										<c:out value="${balance}" /> </b>
								</td>
							</tr>
						</s:if>
						<s:if test="%{description != null}">
							<tr style="font-size: 10px;">
								<td style="text-align: right; border-bottom: 0px solid; border-top: 1px solid; border-left: none; border-right: 1px solid; font-size: 10px;" colspan="5">
									<label>
										<b>Used Excess Amount&nbsp;</b>
									</label>
								</td>
								<td style="text-align: right; border-bottom: 0px solid; border-top: 1px solid; border-left: none;">
									<b> 
										<c:set value="${description}" var="priceAmt" /> 
										<fmt:formatNumber value="${priceAmt}" type="currency" pattern="##,##,###.00" var="balance" /> 
										<c:out value="${balance}" /> 
									</b>
								</td>
							</tr>
						</s:if>
						<s:if test="%{thirtyTotalAmount > 0}">
							<tr style="font-size: 10px;">
								<td style="text-align: right; border-bottom: 0px solid; border-top: 0px solid; border-left: none; border-right: 1px solid;" colspan="5">
									<label>
										<b>Discount Amount&nbsp;</b>
									</label>
								</td>
								<td style="text-align: right; border-bottom: 0px solid; border-top: 0px solid; border-left: none;">
									<b> 
										<c:set value="${thirtyTotalAmount}" var="priceAmt" />
										<fmt:formatNumber value="${priceAmt}" type="currency" pattern="##,##,###.00" var="balance" /> 
										<c:out value="${balance}" /> 
									</b>
								</td>
							</tr>
						</s:if>
						<tr style="font-size: 10px;">
							<td style="text-align: right; border-bottom: 1px solid; border-top: 1px solid; border-left: none; border-right: 1px solid; font-size: 10px;" colspan="5">
								<label>
									<b>Paid Amount&nbsp;</b>
								</label>
							</td>
							<td style="text-align: right; border-bottom: 1px solid; border-top: 1px solid; border-left: none;">
								<b> 
									<s:if test="%{totalAmount > 0}">
										<c:set value="${totalAmount}" var="priceAmt" />
										<fmt:formatNumber value="${priceAmt}" type="currency" pattern="##,##,###.00" var="balance" />
										<c:out value="${balance}" />
									</s:if> 
									<s:else>
										0.00
									</s:else> 
								</b>
							</td>
						</tr>
						<tr style="font-size: 10px;">
							<td style="text-align: right; border-bottom: 1px solid; border-top: 1px solid; border-left: none; border-right: 1px solid; font-size: 10px;width: 80%;" colspan="5">
								<label>
									<b>Balance Amount&nbsp;</b>
								</label>
							</td>
							<td style="text-align: right; border-bottom: 1px solid; border-top: 1px solid; border-left: none;width: 20%;">
								<b> 
									<s:if test="%{plSubTopic > 0}">
										<c:set value="${plSubTopic}" var="invoiceTermAmt" />
										<fmt:formatNumber value="${invoiceTermAmt}" type="currency" pattern="##,##,###.00" var="balanceAmt" />
										<c:out value="${balanceAmt}" />
									</s:if> 
									<s:else>
										0.00
									</s:else> 
								</b>
							</td>
						</tr>
						<s:if test='%{customer.showBalanceAmountInFeeReceipt =="Y"}'>
							<tr style="font-size: 10px;">
							<td style="text-align: right; border-bottom: 1px solid; border-top: 1px solid; border-left: none; border-right: 1px solid; font-size: 10px;" colspan="5">
								<label>
									<b>Total Balance Amount&nbsp;</b>
								</label>
							</td>
							<td style="text-align: right; border-bottom: 1px solid; border-top: 1px solid; border-left: none;">
								<b> 
									<s:if test="%{tempString3 > 0}">
										<c:set value="${tempString3}" var="priceAmt" />
										<fmt:formatNumber value="${priceAmt}" type="currency" pattern="##,##,###.00" var="balance" />
										<c:out value="${balance}" />
									</s:if> 
									<s:else>
										0.00
									</s:else> 
								</b>
							</td>
						</tr>
						</s:if>
						<tr style="font-size: 10px;">
							<td style="text-align: left; border-bottom: 1px solid; border-top: 1px solid; border-left: none;" colspan="6">
								<center>
									<b>Total (in words)</b>&nbsp;
									<s:if test="%{totalAmount > 0}">
										<s:property value="plTitle" />
									</s:if>
									<s:else>
										Zero Rupees only
									</s:else>
								</center>
							</td>
						</tr>
					</tbody>
				</s:if>
			</table>
		</div>
	</div>
	<div>
		<div style="font-size: 10px; text-align: left; padding: 0px 5px 0px 5px">
			<s:if test="%{queryString !='' && queryString !=null}">
				<div>
					<font class="discount"><b>Reason For Discount
					</font>&nbsp;:&nbsp;
					</b>
					<s:property value="queryString" />
				</div>
			</s:if>
			<div>
				<font color="red"><b>Note</b>
				</font> : Fees will not be refunded at any circumstances.
			</div>
			
			<div><s:if test="%{tempString1 !='' && tempString1 !=null}"><b>Excess paid:</b>&nbsp;<s:property value="tempString1"/>&nbsp;&nbsp;&nbsp; </s:if><s:if test="%{tempString2 !='' && tempString2 !=null}"><b>Excess used:</b>&nbsp;<s:property value="tempString2"/></s:if></div>
		</div>
		<br />
		<s:if test='%{customer.showAddreesInFeeReceipt =="Y"}'>
			<div class="col-xs-6">
			<%-- <s:if test="%{viewStudentClassDetails.addressForStudent != null &&  !viewStudentClassDetails.addressForStudent.isEmpty()}"> --%>
			<div class="wells">
				<address style="font-size: 10px; min-height: 55px;">
					<b><u>Address</u> </b>
					<br />
					<s:property value="viewStudentClassDetails.addressForStudent" />
				</address>
			</div>
			<%-- </s:if> --%>
		</div>
		</s:if>
	</div>
</div>
<style>
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
	font-size: 10px;
 	margin: 5px;
}
@media print 
{
  a[href]:after { content: none !important; }
  img[src]:after { content: none !important; }
}
</style>