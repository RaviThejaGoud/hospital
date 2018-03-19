<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/taglibs.jsp"%>
<%
	String[] alphabet = { "A", "B", "C", "D", "E", "F", "G", "H", "I",
			"J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U",
			"V", "W", "X", "Y", "Z" };
%>
<%
	String[] color = { "Blue", "Blue" };
%>
<div style="width: 100%">
	<div class="invoice-logo-space">
		<s:if
			test="%{customer.customerLogo != null &&  customer.customerLogo != ''}">
			<img src='<c:url value="${customer.customerLogo}"/>'
				alt='<s:property  value="customer.customerLogo" />' height="90px"
				width="150px" border="0" style="float: left;" />&nbsp;
		   </s:if>
	</div>
	<div class="invoice-logo-space1" style="float:center; width:290px;">
		<b class="receptFontHeader"> <s:property
				value="customer.organization" />
		</b> <br /> <span class="receptFontSubHeader"> <s:if
				test="%{customer.address.addressLine1 != null && customer.address.addressLine1 != ''}">
				<b><s:property value="customer.address.addressLine1" /> </b>
			</s:if> <s:if
				test="%{customer.address.streetName != null && customer.address.streetName != ''}">
				<b><s:property value="customer.address.streetName" />, </b>
			</s:if> <br /> <s:if
				test="%{customer.address.city != null && customer.address.city != ''}">
				<b><s:property value="customer.address.city" /> -</b>
			</s:if> <s:if
				test="%{customer.address.postalCode != null && customer.address.postalCode != ''}">
				<b><s:property value="customer.address.postalCode" /> ,</b>
			</s:if> <br /> <s:if
				test="%{customer.contactNumber != null && customer.contactNumber != ''}">
				<b>Phone: <s:property value="customer.contactNumber" />,
				</b>
			</s:if> <s:if
				test="%{customer.mobileNumber != null && customer.mobileNumber != ''}">
				<b><s:property value="customer.mobileNumber" /> .</b>
			</s:if>
		</span><br /> <b style="text-align: center;font-size: 14px;">FEE RECEIPT-<s:property
value="viewStudentClassDetails.academicYear" /></b>
	</div>
</div>

<%
	int i = 0;
%>
<s:if test="%{tempList != null && !tempList.isEmpty()}">
<s:iterator value="tempList" begin="0" end="0">
	<table style="font-size: 10px;margin: 5px;" width="100%">
		<thead>
			<tr>
				<td colspan="2"><label style="text-align:right;width: 70px;">Name
						:</label> <b><s:property value="studentFullName" /> </b></td>
			</tr>
			<tr>
				<td><label style="text-align: right;width: 70px;">Class
						:</label> <b><s:property value="classAndSection" /> </b></td>
				<td><label style="text-align: right;width: 73px;">Receipt
						No :</label> <s:if
						test="%{customer.feeReceiptNoWithCustName != null &&  customer.feeReceiptNoWithCustName != ''}">
						<b><s:property
								value="customer.feeReceiptNoWithCustName.toUpperCase()" />&nbsp;/&nbsp;<s:property
								value="invoiceNumber" /></b>
					</s:if> <s:else>
						<b><s:property value="invoiceNumber" /></b>
					</s:else></td>
			</tr>
			<tr>
				<td><label style="text-align: right;width: 70px;">Adm
						No :</label> <b><s:property value="admissionNumber" /></b></td>
				<td><label style="text-align: right;width: 73px;">Payment
						Date :</label> <b><s:property value="paymentDateStr" /> </b></td>
			</tr>
			<tr>
				<td class="test1"><label style="text-align: right;width: 70px;">Father
						Name :</label> <b><s:property value="fatherName" /> </b></td>
				<%-- <td><label style="text-align: right;width: 70px;">Class
						:</label> <b><s:property value="classAndSection" /> </b></td> --%>
				<td class="test1"><label style="text-align: right;width: 73px;">Payment
						Type :</label> <b>Cash</b></td>
			</tr>
			<tr>
				<td><label style="text-align: right;width: 70px;">Mobile
						No :</label> <b><s:property value="mobileNumber" /> </b></td>
			</tr>
		</thead>
	</table>
</s:iterator>
</s:if>
<div class="invoice">
	<div class="row">
		<div class="col-xs-12">
			<table class="table table-striped table-hover">
				<thead>
					<tr class="trHeadding">
						<th>S.No</th>
						<th colspan="4" style="text-align: center;">Description</th>
						<th style="text-align: center;">Amount</th>
					</tr>
					<tr style=" border-bottom: 1px solid;"></tr>
				</thead>
				<tbody>
				<s:if test="%{tempList != null && !tempList.isEmpty()}">
					<s:iterator value="tempList">
						<%
							i++;
						%>
						<tr style="font-size: 12px;">
							<td
								style="text-align: right; border-bottom: 1px solid; border-left: none; border-right: 1px solid; width: 30px">
								<center>
									<b> <%=i%>
									</b>
								</center>
							</td>
							<td colspan="4"
								style="text-align: right; border: none; border-right: 1px solid; border-bottom: 1px solid;">
								<s:property value="description" />&nbsp;
							</td>
							<td
								style="text-align: left; border: none; border-bottom: 1px solid;">
								<center>
								<s:if test="%{totalAmount > 0}">
									<b> <s:set var="total" value="%{fineFeeAmount}" /> <c:set
											value="${total}" var="priceAmt" /> <fmt:formatNumber
											value="${total}" type="currency" pattern="##,##,###.00"
											var="balance" /> <s:property value="country.countryCurrency.currencySymbol" escapeHtml="false"/> <c:out value="${balance}" />
									</b>
									</s:if>
									<s:else>
									0.00
									</s:else>
								</center>
							</td>
						</tr>
					</s:iterator>
					</s:if>
					<tr>
					</tr>
					<tr style="font-size: 12px;">
						<td style="text-align: right; border-bottom: 0px solid; border-top: 1px solid; border-left: none; border-right: 1px solid;"
								colspan="5" class="borderBottom"><label> <b>Total
									Amount</b>&nbsp;
						</label></td>
						<td
							style="text-align: left; border-bottom: 0px solid; border-top: 1px solid; border-left: none;">
							<center>
							<s:if test="%{totalAmount > 0}">
								<b> <c:set value="${totalAmount}" var="totalAmount" /> <fmt:formatNumber
										value="${totalAmount}" type="currency" pattern="##,##,###.00"
										var="balance" /> <s:property value="country.countryCurrency.currencySymbol" escapeHtml="false"/> <c:out value="${balance}" /></b>
										</s:if>
										<s:else>
								0.00
								</s:else>
							</center>
						</td>
					</tr>
					<tr style="font-size: 12px;">
						<td class="borderBottom" style="text-align: right; border-bottom: 0px solid; border-top: 1px solid; border-left: none; border-right: 1px solid;"
								colspan="5"><label> <b>Paid Amount</b>&nbsp;
						</label></td>
						<td
							style="text-align: left; border-bottom: 1px solid; border-top: 1px solid; border-left: none;">
							<center>
							<s:if test="%{totalAmount > 0}">
								<b> <c:set value="${totalAmount}" var="totalAmount" /> <fmt:formatNumber
										value="${totalAmount}" type="currency" pattern="##,##,###.00"
										var="balance" /> <s:property value="country.countryCurrency.currencySymbol" escapeHtml="false"/> <c:out value="${balance}" />
								</b>
								</s:if>
								<s:else>
								0.00
								</s:else>
							</center>
						</td>
					</tr>
					<tr style="font-size: 12px;">
						<td
							style="text-align: left; border-bottom: 1px solid; border-top: 1px solid; border-left: none;"
							colspan="6">
							<center>
								<b>Total (in words)</b>&nbsp;
								<s:if test="%{totalAmount > 0}">
								<s:property value="plTitle" />&nbsp;Rupees Only
								</s:if>
								<s:else>
								Zero Rupees only
								</s:else>
							</center>
						</td>
					</tr>
			</table>
		</div>
	</div>
	<div>
		<div style="font-size: 10px;float:left;">
			<font color="red"><b>Note</b></font>: Fees will not be refunded at any circumstances.
		</div>
		<br/>
		<s:if test='%{customer.showAddreesInFeeReceipt =="Y"}'>
		<div class="col-xs-6">
			<div class="wells">
				<%-- <s:if
					test="%{ViewStudentFineFeeDetails.addressForStudent1 != null &&  !ViewStudentFineFeeDetails.addressForStudent1.isEmpty()}"> --%>
					<address style="font-size: 10px;min-height:55px;">
						<b><u>Address</u> </b> <br />
							<s:property value="fineFeeDetails.addressForStudent1" />
					</address>
				<%-- </s:if> --%>
			</div>
		</div>
		</s:if>
		<div class="col-xs-6" style="float:right;width:150px;">
			<b class="receptFontSubHeader"> Authorized Signature</b>
		</div>
		<br/><br/>
		<%-- <div style="float: right; width: 216px;">
			<b class="receptFontSubHeader"> Received by 
			<s:if test="%{tempList != null && !tempList.isEmpty()}">
				<s:iterator value="tempList" begin="0" end="0">
						<font color="green"> <s:property value="personFullName" /> </font>
				</s:iterator>
			</s:if>
			</b>
		</div> --%>
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
</style>