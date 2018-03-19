<%@ include file="/common/taglibs.jsp"%>
<style type="text/css">
@import url("${pageContext.request.contextPath}/styles/960/960.css");

@import
	url("${pageContext.request.contextPath}/styles/default/style.css");

body {
	background: #fff;
}
@media print {
.header, .hide { visibility: hidden }
}
table.feeTable {
    border-color: #DDDDDD #DDDDDD #A5A5A5;
    border-style: solid;
    border-width: 1px 1px 4px;
    font-size: 18px;
    color: #454545;
}
table.feeTable td {
    line-height: normal;
    padding: 0px;
}
table.feeTable th {
    border-bottom: 3px solid #A5A5A5;
    color: #3A3A3A;
    font-size: 1.1em;
    padding: 10px;
    text-align: left;
}
.borderLeft{
 text-align: right; 
 border-left: 1px; 
 border-bottom: 1px solid;
 border-top: 1px solid; 
 border-left: none; 
 border-right: 1px solid; 
 width: 30px; 
 background: #F2F2F2;
}
.borderRight{
  text-align: right;  
  border-bottom: 1px solid;
  border-top: 1px solid; 
  border-left: none; 
  border-right: 1px solid;
  background: #F2F2F2;
}
.borderTop{
   text-align: left;
   border-bottom: 1px solid; 
   border-top: 1px solid;
   border-left: none; 
   border-right: 1px; 
   background: #F2F2F2;
}
.borderBottom{
   text-align: right; 
   border-bottom: 0px solid; 
   border-top: 1px solid;
   border-left: none; 
   border-right: 1px solid;
}
.borderUpper{
	text-align: right; 
	border-bottom: 0px solid;
 	border-top: 0px solid; 
 	border-left: none;
 	 border-right: 1px solid;
}
</style>
<div class="wrapper container_10">
	<div class="wrapper">
	<s:if test="%{objectList!= null || objectList!= null}">
	   <s:iterator value="objectList">
		<table class="feeTable" style="width: 70%; border-collapse: separate; border: 1px solid;margin-top: 20px;">
			<thead>
				<tr>
				<td style="border: none; color: #0000FF; text-align: center;" colspan="6">
						<s:if
							test="%{customer.customerLogo != null &&  customer.customerLogo != ''}">
							<img src='<c:url value="${customer.customerLogo}"/>'
								alt='<s:property  value="customer.customerLogo" />'
								height="100px" width="160px" border="0" style="float: left;" />&nbsp;
					   </s:if>
						<span style="text-align: center;"> <b
							style="font-size: 16px;"><s:property
									value="customer.organization" /> </b> <br /> <s:if
								test="%{customer.address.addressLine1 != null && customer.address.addressLine1 != ''}">
								<b ><s:property
										value="customer.address.addressLine1" /> </b>
							</s:if> <s:if test="%{customer.address.streetName != null && customer.address.streetName != ''}">
								<b ><s:property
										value="customer.address.streetName" />, </b>
							</s:if> <br /> <s:if test="%{customer.address.city != null && customer.address.city != ''}">
								<b ><s:property
										value="customer.address.city" /> -</b>
							</s:if> <s:if test="%{customer.address.postalCode != null && customer.address.postalCode != ''}">
								<b ><s:property
										value="customer.address.postalCode" /> ,</b>
							</s:if> <br /> <s:if test="%{customer.contactNumber != null && customer.contactNumber != ''}">
								<b >Phone: <s:property
										value="customer.contactNumber" />, </b>
							</s:if> <s:if test="%{customer.mobileNumber != null && customer.mobileNumber != ''}">
								<b ><s:property
										value="customer.mobileNumber" /> .</b>
							</s:if> </span><br/>
							<p style="padding-top:10px; text-align: center;"> <b>FEE
								RECEIPT</b> </p>
					</td>
				</tr>
				<tr>
					<td style="border: none;" colspan="5">
						<span style="text-align: right; float: left;width: 133px;">Student Name :</span>
						<b>&nbsp;<s:property value="description" /> </b><br/>
						<span style="text-align: right;float: left; width: 133px;">RollNumber :</span>
						<b>&nbsp;<s:property value="rollNumber" /> </b>
					</td>
					 
					<td style="border: none;width: 253px;">
						<span style="text-align: right;float: left; width: 109px;">Date :</span>
						<b>&nbsp;<s:property value="anyTitle" /> </b><br/>
						<span style="text-align: right;float: left; width: 109px">Mobile No :</span>
						<b>&nbsp;<s:property value="registerNumber" /> </b>
						
					</td>
				</tr>
				<tr>
					<td style="border: none;" colspan="5">
						<span style="float: left;text-align: right;width: 137px;">Class / Section :&nbsp;</span>
						<b><s:property value="classAndSection" /> </b>
					</td>
 					<td style="border: none;width: 201px;">
						<span style="text-align: right;float: left; width: 109px;">Receipt No :</span>
						<b style="font-size: 20;">&nbsp;<s:property value="tempString" /> </b>
					</td>
				</tr>
			</thead>
			<s:set name="schoolTermsId" value=""></s:set>
			<s:if
				test="%{feeDetailsList!= null || feeDetailsList!= null}">
				<%
					int i = 0;
				%>
				<tr>
					<td class="borderLeft">
						<center>
							<b> S.No </b>
						</center>
					</td>
					<td colspan="4" class="borderRight">
						<center>
							<b> Terms & Particulars</b>
						</center>
					</td>
					<td class="borderTop">
						<center>
							<b>Amount</b>
						</center>
					</td>
				</tr>
				<s:iterator value="feeDetailsList">
					<%
						i++;
					%>
					<s:if test="%{schoolTermId != #schoolTermsId}">
						<tr>
							<td   class="borderUpper" style="width: 30px">
								<b> </b>
							</td>
							<td class="borderUpper" style="text-align: center;">
								<b> <s:property value="termName" />  </b>
							</td>
							<!--<s:if test='%{#schoolTermsId == ""}'>
								<td style="text-align: right;border-bottom: 0px solid;  border-top: 1px solid; border-left: none; border-right: 1px solid;">
									FEES FOR THE MONTH OF :
									<b><s:property value="termName" />-(<s:property
											value="fromMonthName" />-<s:property value="toMonthName" />)</b>
								</td>
							</s:if>
							<s:else>
								<td
									style="text-align: right;border-bottom: 0px solid; border-top: 1px solid; border-left: none; border-right: 1px solid;">
									FEES FOR THE MONTH OF :
									<b><s:property value="termName" />-(<s:property
											value="fromMonthName" />-<s:property value="toMonthName" />)</b>
								</td>
							</s:else> -->
						</tr>
					</s:if>
					<tr>
						<td  style="text-align: right; border-bottom: 0px solid; border-left: none; border-right: 1px solid; width: 30px">
							<center><b>
									<%=i%>
								</b></center> 
						</td>
						<td colspan="4"
							style="text-align: right; border: none; border-right: 1px solid;">
							<s:property value="feeType" />&nbsp;
						</td>
						<td style="text-align: left; border: none;">
							<center>
								<b> <s:set var="total" value="%{feeAmount}" />
							<c:set value="${total}" var="priceAmt"/>
								<fmt:formatNumber value="${total}" type="currency"  pattern="##,##,###.00" var="balance"/>  
							     <c:out value="${balance}" /> </b>
							</center>
						</td>
					</tr>
					<s:set name="schoolTermsId" value="%{schoolTermId}"></s:set>
				</s:iterator>
				<tr>
					<td class="borderBottom" colspan="5">
						<label>
							<b>Total Amount</b>&nbsp;
						</label>
					</td>
					<td
						style="text-align: left; border-bottom: 0px solid; border-top: 1px solid; border-left: none;">
						<center>
							<b> <c:set value="${categoryId}" var="priceAmt"/>
								<fmt:formatNumber value="${priceAmt}" type="currency"  pattern="##,##,###.00" var="balance"/>  
							     <c:out value="${balance}" /></b>
						</center>
					</td>
				</tr>
				<s:if test="%{custId > 0}">
				<tr>
						<td class="borderUpper" colspan="5">
							<b><s:property value="wishTitle" />&nbsp;
							</b>
						</td>
						<td
							style="text-align: left; border-bottom: 0px solid; border-top: 0px solid; border-left: none;">
							<center><b>
							<c:set value="${custId}" var="priceAmt"/>
								<fmt:formatNumber value="${priceAmt}" type="currency"  pattern="##,##,###.00" var="balance"/>  
							     <c:out value="${balance}" /></b>
							</center>
						</td>
					</tr>
				</s:if>
				<tr>
					<td class="borderBottom" style="border-bottom: 1px solid;" colspan="5">
						<label>
							<b>Paid Amount</b>&nbsp;
						</label>
					</td>
					<td
						style="text-align: left; border-bottom: 1px solid; border-top: 1px solid; border-left: none;">
						<center>
							<b> <c:set value="${transportCategoryId}" var="priceAmt"/>
								<fmt:formatNumber value="${priceAmt}" type="currency"  pattern="##,##,###.00" var="balance"/>  
							     <c:out value="${balance}" /> </b>
						</center>
					</td>
				</tr>
			</s:if>
			<tr>
				<td style="text-align: right; border: none; padding-top: 70px;" colspan="6">
					<label>
						<!--<b>For<s:property value="customer.organization" /> </b> -->
						<b> Authorized Signature</b>
					</label>
				</td>
			</tr>
			<tr>
				<td style="text-align: left; border: none; border-bottom: 1px;font-size: 13px;"
					colspan="6">
					<label>
						<b>Note:</b> Fees will not be refunded at any circumstances.
					</label>
				</td>
			</tr>
		</table>
		</s:iterator>
	 </s:if>
	</div>
</div>

