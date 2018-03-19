<%@ include file="/common/taglibs.jsp"%>
<%
	String[] alphabet = { "A", "B", "C", "D", "E", "F", "G", "H", "I","J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U","V", "W", "X", "Y", "Z" };
%>
<%
	String[] color = { "Blue", "Blue" };
%>
		<div class="header">
		<div style="float: right;font-size: 14px;padding-right: 5px;width: 100%;text-align: right;">
			<b > <s:property value="bankAccountDetailsVO.bankName" />
		</b>
		</div>
		<br/>
		<div style="float: right;">
			<s:if test="%{bankAccountDetailsVO.addressVO.addressLine1 != null && bankAccountDetailsVO.addressVO.addressLine1 != ''}">
			<s:property value="bankAccountDetailsVO.addressVO.addressLine1" />,
		</s:if>
			<s:if test="%{bankAccountDetailsVO.addressVO.city != null && bankAccountDetailsVO.addressVO.city != ''}">
				<s:property value="bankAccountDetailsVO.addressVO.city" /> 
				<s:if test="%{bankAccountDetailsVO.addressVO.postalCode != null && bankAccountDetailsVO.addressVO.postalCode != ''}">
					-<s:property value="bankAccountDetailsVO.addressVO.postalCode" />
				</s:if> ,
			</s:if>
			 </div>
			 <br/>
			<div style="float: right;width: 100%;text-align: right;">
			<s:if test="%{bankAccountDetailsVO.mobileNumber != null && bankAccountDetailsVO.mobileNumber != ''}">
				
					Ph No :<s:property value="bankAccountDetailsVO.mobileNumber" />,
			</s:if>
			<s:if test="%{bankAccountDetailsVO.addressVO.email != null && bankAccountDetailsVO.addressVO.email != ''}">
					Email :<s:property value="bankAccountDetailsVO.addressVO.email" />
			</s:if>
			</div>
			<br/>
			<div style="width: 100%;"><b>&nbsp;	FEE CHALLAN</b></div>
			<hr style="width: 100%;margin: 2px;" />
			</div>
		

 <table style="font-size: 10px; margin: 5px;" width="100%">
	<tr>
		<td colspan="2">
			<label style="text-align: right; width: 120px;">
				<b>No  :</b>
			</label>
			<s:property value="anyId"/> 
		</td>
		<td style="padding-right: 10px;text-align: right;">
			<label style="text-align: right; width: 90px;">
				<b>Date :</b>
			</label>
			<s:property value="anyTitle"/>
		</td>
	</tr>
	
	<tr>
		<td colspan="2">
			<label style="text-align: right; width: 120px; font-size: 12px">
				<b>Paid to the credit of  :</b>
			</label>
				<s:property value="bankAccountDetailsVO.accountName" />
		</td>
	</tr>
	
	<tr>
		<td colspan="2">
			<label style="text-align: right; width:120px;">
				<b>SB. A/C No  :</b>
			</label>
			<s:property value="bankAccountDetailsVO.accountNumber" />
		</td> 
	</tr>
	
	<tr>
		<td colspan="2">
			<label style="text-align: right; width: 120px;">
				<b>Student's Name  :</b>
			</label>
			<s:property value="viewStudentClassDetails.fullName"/> 
		</td>
	</tr> 
	
	<tr>
		<td colspan="2">
			<label style="text-align: right; width: 120px;">
				<b>Class & Section  :</b>
			</label>
				<s:property value="viewStudentClassDetails.classAndSection" />
		</td> 
	</tr>
</table>
<div class="invoice">
	<div class="row">
		<div class="col-xs-12">
			 <table class="viewBody tabTotalTDClass" id="tabTotalTDClass" style="width: 100%;min-height: 280px;margin: 1px;">
				<s:set name="schoolTermsId" value=""></s:set>
				<s:if test="%{studentPaymentList!= null || studentPaymentList!= null}">
					<% int i = 0; %>
					<% int j = 0; %>
					<% int k = 0; %>
					<thead>
						<tr class="tabTRClass">
							<th colspan="3" class="tabThClass">
								Terms & Particulars
							</th>
							<th  class="tabThAmount" colspan="1">
									Fee Amount
							</th>
						</tr>						
					</thead>
					<tbody>
						<s:set var="invoiceTotAmt" value="0" />
						<s:iterator value="studentPaymentList">
							<% i++; %>
							<s:if test="%{schoolTermId != #schoolTermsId}">
								<% i = 1; %>
								<tr class="tabTRClass">
									<td colspan="3" class="tabTDClass">
										<font color="<%=color[(int) (2 * Math.random())]%>"><%=alphabet[j]%>
										<b><s:property value="termName" /> </b>
									</td>
									<td colspan="1" class="tabeAmountTd" style="text-align: right;">
						        	</td>
								</tr>
								<% j++; %>
							</s:if>
							<tr class="tabTRClass">
								<td colspan="3" class="tabTDClass">
								 <%=i%> &nbsp;	<s:property value="feeType" />
								</td>
								<td colspan="1" class="tabeAmountTd" style="text-align: right;padding: 3px;"><!-- style="text-align: right; border: none;" -->
									<b style="font-size: 10px;"> 
										<s:set var="total" value="%{paymentAmount + discountAmount}" /> 
										<c:set value="${total}" var="priceAmt" /> 
										<fmt:formatNumber value="${total}" type="currency" pattern="##,##,###.00" var="balance" /> <s:property value="country.countryCurrency.currencySymbol" escapeHtml="false"/> <c:out value="${balance}" /> 
									</b>
									<s:set var="invoiceTotAmt" value="%{#invoiceTotAmt + #total}" />
								</td>
							</tr>
							<s:set name="schoolTermsId" value="%{schoolTermId}"></s:set>
							<% k++; %>
						</s:iterator>
						 
						<c:set var="size" value="${fn:length(studentPaymentList)+3 }"></c:set>
						
						<% String browser = request.getHeader("user-agent");
					 	   if (browser.indexOf("Chrome") != -1) { %>
							<c:set var="endSize" value="${21}"></c:set>
						<% } else { %>
							<c:set var="endSize" value="${12}"></c:set>
						<% } %>
						<%-- <c:forEach begin="${size}" end="${endSize}" varStatus="no">
					        <tr class="tabTRClass">
					        	<td class="tabTDClass" colspan="1">
					        		&nbsp;
					        	</td>
					        	<td class="tabeAmountTd" >
					        		&nbsp;
					        	</td>
					        </tr>
					    </c:forEach> --%>
						<s:if test="%{paymentAmount > 0}">
							<tr class="tabTRClass">
								<td class="tabTDClass" colspan="3"><!--style="text-align: right; border-bottom: 0px solid; border-top: 1px solid; border-left: none; border-right: 1px solid;" -->
									<label>
										<b>Late Fee &nbsp;</b>
									</label>
								</td>
								<td class="tabeAmountTd" colspan="1" style="text-align: right;padding: 3px;"> <!-- style="text-align: right; border-bottom: 0px solid; border-top: 1px solid; border-left: none;" -->
									<b> 
										<s:set var="invoiceTotAmt" value="%{#invoiceTotAmt + paymentAmount}" />
										<c:set value="${paymentAmount}" var="fineAmount" /> 
										<fmt:formatNumber value="${fineAmount}" type="currency" pattern="##,##,###.00" var="fineAmount" /> 
										<s:property value="country.countryCurrency.currencySymbol" escapeHtml="false"/> <c:out value="${fineAmount}" /> 
									</b>
								</td>
							</tr>
						</s:if> 
					</tbody>
				</s:if>
			</table>
			<table style="width: 100%;margin: 1px;">
			<tbody>
			<tr class="tabTRClass" style="widht:100%; text-align: right; border-bottom: 1px solid; border-top: 1px solid; border-left:0px solid; border-right: 1px solid;"> 
			<td  style="border-right: 1px solid;width:70%"  colspan="2">
			<label style="float: right;"><b>Total Amount</b></label>
			</td>
			<td class="tabeAmountTd" colspan="1" style="text-align: right;padding: 3px;"><b> 
									<c:set value="${invoiceTotAmt}" var="priceAmt" /> 
									<fmt:formatNumber value="${priceAmt}" type="currency" pattern="##,##,###.00" var="balance" /> 
									<s:property value="country.countryCurrency.currencySymbol" escapeHtml="false"/> <c:out value="${balance}" /> 
								</b></td>
			</tr>
			</tbody>
			</table>
		</div>
	</div></div>
	<div class="viewFooter" style="height: 14%;">
	
	<table class="viewBody footerTable" style="width: 100%;">
			<tbody>
			<tr class="tabTRClass"> 
			<td  style="width:100%"  colspan="2">
			<center>
				<b>Total (in words)</b>&nbsp;
				<s:if test="%{totalAmount > 0}">
					<s:property value="plTitle" /> <s:property value="country.countryCurrency.currencyWord" escapeHtml="false"/> Only
				</s:if>
				<s:else>
					Zero <s:property value="country.countryCurrency.currencyWord" escapeHtml="false"/> only
				</s:else>
			</center>
			</td>
			<td></td>
			</tr>
			<tr class="tabTRClass"> 
			<td  style="width:40%"  colspan="2">
			<b> by Cash / Cheque / DD</b>&nbsp;
			</td>
			<td></td>
			</tr>
			
			<tr class="tabTRClass"> 
			<td  style="width:40%"  colspan="2">
			 Cheque / DD NO :</b>&nbsp;
			</td>
			<td></td>
			</tr>
			<tr class="tabTRClass">
					<td style="width: 50%;">
						<label style="float: left;"><b> &nbsp;</b></label></td>
					<td class="tabeAmountTd" style="width: 50%;">
						<label style="float: right;">&nbsp;</label>
					</td>
				</tr>
			<tr class="tabTRClass">
					<td style="width: 50%;">
						<label style="float: left;"><b>  Officer Signature</b></label></td>
					<td class="tabeAmountTd" style="width: 50%;">
						<label style="float: right;"> Remitter Signature</label>
					</td>
				</tr>
			</tbody>
			</table> 
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
.header{
	width:100%;
	font-size: 9px;
	padding-right: 3px;
}
.challanDiv{
  	text-align: left;
  	font-size: 10px;
  	width: 20%;
  }
@media print 
{
  a[href]:after { content: none !important; }
  img[src]:after { content: none !important; }
  
  .challanDiv{
  	text-align: left;
  	font-size: 10px;
  	width: 20%;
  }
  .header{
	width:100%;
	font-size: 9px;
	padding-right: 3px;
}
}
</style>

<script type="text/javascript">

for(var j=0;j<3;j++){
var x = document.getElementsByClassName("tabTotalTDClass")[j];

var len = x.rows.length;
var loopLength=16-len;
for(var i=0;i<=loopLength;i++){
	 
	var new_row = x.rows[1].cloneNode(true);
	var tdData=new_row.getElementsByTagName("td")[0];
	var tdData1=new_row.getElementsByTagName("td")[1];	
	tdData.innerHTML="";
	tdData1.innerHTML="";	
	x.appendChild( new_row );	
 }
 
/* for(var k=0;k<=3;k++){
	var y=document.getElementsByClassName("footerTable")[k];
	var footer_row = y.rows[1].cloneNode(true);
	var tdFooter1=footer_row.getElementsByTagName("td")[0];
	var tdFooter2=footer_row.getElementsByTagName("td")[1];	
	tdFooter1.innerHTML="Ganesh";
	tdFooter2.innerHTML="Cherivi";	
	y.appendChild( footer_row );	
 } */
}
</script>