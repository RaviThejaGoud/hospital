<%@ include file="/common/taglibs.jsp"%>
<%
	String[] alphabet = { "A", "B", "C", "D", "E", "F", "G", "H", "I","J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U","V", "W", "X", "Y", "Z" };
%>
<%
	String[] color = { "Blue", "Blue" };
%>
		<div style="width: 100%;">
	<div class="row" style="position: relative;margin-left: 41%;">
		<s:if test="%{customer.customerLogo != null &&  customer.customerLogo != ''}">
			<img src='<c:url value="${customer.customerLogo}"/>'
				alt='<s:property  value="customer.customerLogo" />' height="50px"
				width="50px" border="0" style="float: left;" />&nbsp;
			   </s:if>
	</div>
	<div class="row" style="text-align: center;">
		<b class="orgHeader" style="font-size: 15px;"> <s:property value="customer.organization" /> </b>
		<br />
		<span class="receptFontSubHeader" style="font-size: 8px;"> 
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
			</s:if>
			<s:if test="%{customer.contactNumber != null && customer.contactNumber != ''}">
				<b>Phone: <s:property value="customer.contactNumber" />, </b>
			</s:if> 
			<s:if test="%{customer.mobileNumber != null && customer.mobileNumber != ''}">
				<b><s:property value="customer.mobileNumber" /> .</b>
			</s:if> </span>
		<br/>
		<b style="text-align: center; font-size: 9px;">FEE CHALLAN </b>
	</div>
</div>
<hr style="margin: 2px;" />

<table style="width: 95%;font-size: 11px;margin-left: 10px;">
		<tr>
			<td style="text-align: left ;width: 50%;"colspan="2"> Challan Number :
			 <b><s:property value="anyId" /> </b></td>
			<td style="text-align: left;width: 45%;" colspan="2">Date :<b>&nbsp; </b></td>
		</tr>
		<tr style="width: 100%;">
			<td style="width: 100%;text-align: left;" colspan="4" >Challan for fees structure for Term <b><s:property value="wishDescription"/></b></td>
			
		</tr>
		<tr style="width: 100%;">
			<td style="width: 100%;text-align: left;" colspan="4" >Please accept fees and credit to our</td>
			
		</tr>
		<tr style="width: 100%;">
			<td style="width: 100%;text-align: left;" colspan="4" ><b> <s:property value="bankAccountDetailsVO.bankName" /></b>,<s:property value="bankAccountDetailsVO.addressVO.city" />  School A/C No.<b><s:property value="bankAccountDetailsVO.accountNumber" /></b></td>
			
		</tr>
		
		<tr style="width: 100%;">
			<td style="width: 75%;text-align: left;" colspan="4">Name of the student (Full with initial In block letters)</td>
			
		</tr>
		<tr style="width: 100%;">
			<td style="text-align: left; border-bottom: 1px solid;width: 45%;text-align: left;text-transform: uppercase;" colspan="4"><s:property value="viewStudentClassDetails.fullName" /></td>
			
		</tr>
		</table>
		<table style="width: 95%;font-size: 11px;margin-left: 10px;">
		<tr>
			<td style="width:17%;">Adm. No. :</td>
			<td style="width:38%;border-bottom: 1px solid;"><s:property value="viewStudentClassDetails.admissionNumber" /></td>
			<td style="width:22%;border-bottom: 1px solid;text-decoration: none;border-bottom-color: #FFF;"> Class & Sec : </td>
			<td style="width:27%;border-bottom: 1px solid;"><s:property value="viewStudentClassDetails.classAndSection"/></td>
		</tr>
		</table>
		<table style="width: 95%;font-size: 11px;margin-left: 10px;">
		<tr>
			<td style="width: 30%;text-align: left;">Father's Name :</td>
			<td style="text-align: left; border-bottom: 1px solid;width: 70%;" colspan="3"><s:property value="viewStudentClassDetails.fatherName" /></td>
			
		</tr>
		</table>
		<table style="width: 95%;font-size: 11px;margin-left: 10px;">
		<tr>
			<td style="width: 20%;text-align: left;">Phone No:</td>
			<td style="text-align: left; border-bottom: 1px solid;width: 30%;"><s:property value="viewStudentClassDetails.mobileNumber" /></td>
			<td  style="width: 18%;text-align: left;"> Signature : </td>
			<td style="text-align:left; border-bottom: 1px solid;width: 32%;"></td>
		</tr>
		<tr>
			<td colspan="4">&nbsp;</td>
		</tr>
</table>


			 <table class="viewBody tabTotalTDClass" id="tabTotalTDClass" style="width: 95%;min-height: 280px;margin: 1px;margin-left: 9px;border-right: 1px solid;">
				<s:set name="schoolTermsId" value=""></s:set>
				<s:if test="%{studentPaymentList!= null || studentPaymentList!= null}">
					<% int i = 0; %>
					<% int j = 0; %>
					<% int k = 0; %>
					<thead>
						<tr class="tabTRClass">
							<th colspan="2" class="tabThClass">
								Terms & Particulars
							</th>
							<th  class="tabThAmount">
									Fee Amount
							</th>
						</tr>						
					</thead>
					<tbody>
						<s:set var="invoiceTotAmt" value="0" />
						<s:set var="invoiceTotDiscAmt" value="0" />
						<s:iterator value="studentPaymentList">
							<% i++; %>
							<s:if test="%{schoolTermId != #schoolTermsId}">
								<% i = 1; %>
								<tr class="tabTRClass">
									<td colspan="2" class="tabTDClass" style="border-right: 1px solid;border-left: 1px solid;">
										<font color="<%=color[(int) (2 * Math.random())]%>"><%=alphabet[j]%>
										<b><s:property value="termName" /> </b>
									</td>
									<td class="tabeAmountTd" style="text-align: right;border-left: 1px solid;">
						        	</td>
								</tr>
								<% j++; %>
							</s:if>
							<tr class="tabTRClass">
								<td colspan="2" class="tabTDClass" style="border-right: 1px solid;border-left: 1px solid;">
								 <%=i%> &nbsp;	<s:property value="feeType" />
								</td>
								<td class="tabeAmountTd" style="text-align: right;padding: 3px;border-right: 1px solid;border-left: 1px solid;"><!-- style="text-align: right; border: none;" -->
									<b style="font-size: 10px;"> 
										<s:set var="total" value="%{paymentAmount}" /> 
										<s:set var="totalDiscAmt" value="%{discountAmount}" />
										<c:set value="${total}" var="priceAmt" /> 
										<fmt:formatNumber value="${total}" type="currency" pattern="##,##,###.00" var="balance" /> <s:property value="country.countryCurrency.currencySymbol" escapeHtml="false"/> <c:out value="${balance}" /> 
									</b>
									<s:set var="invoiceTotAmt" value="%{#invoiceTotAmt + #total}" />
									<s:set var="invoiceTotDiscAmt" value="%{#invoiceTotDiscAmt + #totalDiscAmt}" />
								</td>
							</tr>
							<s:set name="schoolTermsId" value="%{schoolTermId}"></s:set>
							<% k++; %>
						</s:iterator>
						 
						<c:set var="size" value="${fn:length(studentPaymentList)+3 }"></c:set>
						
						<% String browser = request.getHeader("user-agent");
					 	   if (browser.indexOf("Chrome") != -1) { %>
							<c:set var="endSize" value="${2}"></c:set>
						<% } else { %>
							<c:set var="endSize" value="${12}"></c:set>
						<% } %>
						
						<s:if test="%{paymentAmount > 0}">
							<tr class="tabTRClass">
								<td class="tabTDClass" colspan="2" style="border-right: 1px solid;border-left: 1px solid;"><!--style="text-align: right; border-bottom: 0px solid; border-top: 1px solid; border-left: none; border-right: 1px solid;" -->
									<label>
										<b>Late Fee &nbsp;</b>
									</label>
								</td>
								<td class="tabeAmountTd"  style="text-align: right;padding: 3px;border-right: 1px solid;border-left: 1px solid; "> <!-- style="text-align: right; border-bottom: 0px solid; border-top: 1px solid; border-left: none;" -->
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
			<table style="width: 95%;font-size: 9px;margin-left: 8px;margin-top: -1px;">
			<tbody>
			<tr class="tabTRClass" style="text-align: right; border-bottom: 1px solid; border-top: 1px solid; border-left:0px solid; border-right: 1px solid;border-left: 1px solid;"> 
			<td  style="border-right: 1px solid;width:68.6%" colspan="2">
			<label style="float: right;"><b>Total Amount</b></label>
			</td>
			<td class="tabeAmountTd" style="text-align: right;padding: 3px;border-right: 1px solid;border-left: 1px solid; "><b> 
									<c:set value="${invoiceTotAmt}" var="priceAmt" /> 
									<fmt:formatNumber value="${priceAmt}" type="currency" pattern="##,##,###.00" var="balance" /> 
									<s:property value="country.countryCurrency.currencySymbol" escapeHtml="false"/> <c:out value="${balance}" /> 
								</b></td>
			</tr>
			<s:if test="%{invoiceTotDiscAmt >0}">
			<tr class="tabTRClass" style="widht:100%; text-align: right; border-bottom: 1px solid; border-top: 1px solid; border-left:0px solid; border-right: 1px solid;"> 
			<td  style="border-right: 1px solid;width:70%;border-left: 1px solid;" colspan="2">
			<label style="float: right;"><b>Discount Amount</b></label>
			</td>
			
			<td class="tabeAmountTd" style="text-align: right;padding: 3px;border-right: 1px solid;border-left: 1px solid;"><b> 
									<c:set value="${invoiceTotDiscAmt}" var="priceAmt" /> 
									<fmt:formatNumber value="${priceAmt}" type="currency" pattern="##,##,###.00" var="balance" /> 
									<s:property value="country.countryCurrency.currencySymbol" escapeHtml="false"/> <c:out value="${balance}" /> 
								</b></td>
			</tr>
			</s:if>
			<s:else>
				<tr><td colspan="4">&nbsp;</td></tr>
			</s:else>
			</tbody>
			</table>
	
<table style="width: 95%;font-size: 9px;margin-left: 10px;">
		
		<tr>
			<td style="width: 40%;">Paid by cash /cheque no</td>
			<td style="border-bottom: 1px solid;width: 60%;" colspan="3"></td>
			
		</tr>
		<!-- <tr><td colspan="4">&nbsp;</td></tr> -->
		</table>
		<table style="width: 95%;font-size: 9px;margin-left: 10px;">
		<tr>
			<td style="width: 15%;">Drawn on</td>
			<td style="text-align: center; border-bottom: 1px solid;width: 40%;"></td>
			<td  style="width: 10%;border-bottom: 1px solid;text-decoration: none;border-bottom-color: #FFF;"> Dated</td>
			<td style="text-align:center; border-bottom: 1px solid;width: 40%;"></td>
		</tr>
		
		<!-- <tr><td colspan="4">&nbsp;</td></tr> -->
		<tr>
			<td style="width: 95%;" colspan="4"><s:property value="country.countryCurrency.currencyWord" escapeHtml="false"/> (in words) : </td>
			
			
		</tr>
		<tr>
			
			<td style="text-align: left; border-bottom: 1px solid;width: 95%;" colspan="4"><b> <s:if test="%{totalAmount > 0}">
					<s:property value="plTitle" />
				</s:if>
				<s:else>
					Zero <s:property value="country.countryCurrency.currencyWord" escapeHtml="false"/> only
				</s:else></b></td>
			
		</tr>
		<tr><td colspan="4">&nbsp;</td></tr>
		<tr style="font-size: 11px;">
			<td style="text-align: center; border-bottom: 1px solid;" colspan="4"></td>
			
		</tr>
		<tr>
			<td style="width: 75%;text-align: center;" colspan="4">Space For Bank Seal And Challan No.<b> <s:property value="anyId" /> </b></td>
		</tr>
		<tr>
			<td style="width: 75%;text-align: center;" colspan="4"><textarea rows="7" cols="55" disabled="disabled"></textarea></b></td>
		</tr>
</table>


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
var loopLength=19-len;
for(var i=0;i<=loopLength;i++){
	 
	var new_row = x.rows[1].cloneNode(true);
	var tdData=new_row.getElementsByTagName("td")[0];
	var tdData1=new_row.getElementsByTagName("td")[1];	
	tdData.innerHTML="";
	tdData1.innerHTML="";	
	x.appendChild( new_row );	
 }
}

</script>