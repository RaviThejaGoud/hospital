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
				width="140px" border="0" style="float: left;" />&nbsp;
			   </s:if>
	</div>
	<div>
		<b class="orgHeader"> <s:property value="customer.organization" /> </b>
		<br />
		<span class="receptFontSubHeader"> 
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
		<b style="text-align: center; font-size: 14px;">REGISTRATION FEE - <s:property value="admissionInquiry.academicYear.academicYear" /> </b>
	</div>
</div>
<hr style="margin: 2px;" />
<table style="font-size: 10px; margin: 5px;" width="100%">
	<tr>
		<td>
			<label style="text-align: right; width: 73px;">
				<b>Name :</b>
			</label>
			<b><s:property value="admissionInquiry.studentName" /> </b>
		</td>
		<td>
			<label style="text-align: right; width: 73px;">
				<b>Class :</b>
			</label>
			<b><s:property value="admissionInquiry.classId.className" />
			</b>
		</td>
		<td>
			<label style="text-align: right; width: 120px;">
				<b>Mobile Number :</b>
			</label>
			<b><s:property value="admissionInquiry.parentMobileNumber" /> </b>
		</td>
		
	</tr>
	<tr>
	<td>
		<label style="text-align: right; width: 75px;">
				<b>Student Type :</b>
			</label>
			<s:if test='%{admissionInquiry.hostelMode =="D"}'>
			<b> Day Scholar </b></s:if>
			<s:else><b>Hosteler</b>
			</s:else>
		</td>
		<td>
			<label style="text-align: right; width: 75px;">
				<b>Receipt No :</b>
			</label>
			<b><s:property value="admissionInquiry.recepitNumber" /> </b>
		</td>
		
		<td>
			<label style="text-align: right; width: 120px;">
				<b>Payment Date :</b>
			</label>
			<b><s:property value="admissionInquiry.createdDateStr" /> </b>
		</td>
	</tr>
	
			
				<tr>
					<td class="test2">
						<label style="text-align: right; width: 73px;">
							<b>Parent Name :</b>
						</label>
						<b><s:property value="admissionInquiry.parentName" />
						</b>
					</td>
					<td class="test2">
						<label style="text-align: right; width: 73px;">
							<b>Payment Type :</b>
						</label>
						
						<s:if test="%{ddNumber != null &&  ddNumber!=''}">
							<b>DD</b>
						</s:if>
						<s:elseif test="%{chequeNumber != null &&  chequeNumber!=''}">
							<b> Cheque </b>
						</s:elseif>
						<s:else>
							<b>Cash</b>
						</s:else>
					</td>
				<td>
					<label style="text-align: right; width: 120px;">
						<b>Previous School Name :</b>
					</label>
						<b><s:property value="admissionInquiry.previousSchoolName"/></b>
				</td>
			</tr>
				<tr>
					<s:if test="%{ddNumber != null &&  ddNumber!=''}">
						<td>
							<label style="text-align: right; width: 50px;">
								<b>DD Number :</b>
							</label>
							<b><s:property value="ddNumber" /> </b>
						</td>
					</s:if>
					<s:if test="%{chequeNumber != null &&  chequeNumber!=''}">
						<td>
							<label style="text-align: right; width: 50px;">
								<b>Cheque No :</b>
							</label>
							<b><s:property value="chequeNumber" /> </b>
						</td>
					</s:if>
				</tr>
				<tr>
					<td></td>
					<s:if test="%{bankName != null &&  bankName!='' &&  bankName!='--Select Bank Name--'}">
						<td>
							<label style="text-align: right; width: 50px;">
								<b>Bank Name :</b>
							</label>
							<b><s:property value="bankName" /> </b>
						</td>
					</s:if>
				</tr>
				<tr>
					<td></td>
					<s:if test="%{branchName != null &&  branchName!=''}">
						<td class="test2">
							<label style="text-align: right; width: 50px;">
								<b>Branch Name :</b>
							</label>
							<b><s:property value="branchName" /> </b>
						</td>
					</s:if>
				</tr>
			
		
</table>
<div class="invoice">
	<div class="row">
		<div class="col-xs-12">
			<table class="table table-striped table-hover">
				<s:set name="schoolTermsId" value=""></s:set>
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
									Application Fee&nbsp;
							</th>
						</tr>
					</thead>
					<tbody>
						<s:set var="invoiceTotAmt" value="0" />
						
						<tr style="font-size: 12px;">
							<td style="text-align: right; border-bottom: 0px solid; border-top: 1px solid; border-left: none; border-right: 1px solid;" colspan="5">
								<label>
									<b>Total Amount&nbsp;</b>
								</label>
							</td>
							<td style="text-align: right; border-bottom: 0px solid; border-top: 1px solid; border-left: none;">
								<b> 
									<c:set value="${admissionInquiry.applicationFee}" var="priceAmt" /> 
									<fmt:formatNumber value="${priceAmt}" type="currency" pattern="##,##,###.00" var="balance" /> 
									<s:property value="country.countryCurrency.currencySymbol" escapeHtml="false"/> <c:out value="${balance}" /> 
								</b>
							</td>
						</tr>
						
						<tr style="font-size: 12px;">
							<td style="text-align: right; border-bottom: 1px solid; border-top: 1px solid; border-left: none; border-right: 1px solid; font-size: 12px;" colspan="5">
								<label>
									<b>Paid Amount&nbsp;</b>
								</label>
							</td>
							<td style="text-align: right; border-bottom: 1px solid; border-top: 1px solid; border-left: none;">
								<b> 
									<s:if test="%{admissionInquiry.applicationFee > 0}">
										<c:set value="${admissionInquiry.applicationFee}" var="priceAmt" />
										<fmt:formatNumber value="${priceAmt}" type="currency" pattern="##,##,###.00" var="balance" />
										<c:out value="${balance}" />
									</s:if> 
									<s:else>
										0.00
									</s:else> 
								</b>
							</td>
						</tr>
						
						<tr style="font-size: 12px;">
							<td style="text-align: left; border-bottom: 1px solid; border-top: 1px solid; border-left: none;" colspan="6">
								<center>
									<b>Total (in words)</b>&nbsp;
									<s:if test="%{admissionInquiry.applicationFee > 0}">
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
		</div>
		<br />
		
		
		<div class="col-xs-6" style="float: right; width: 150px;">
			<b class="receptFontSubHeader"> Authorized Signature</b>
		</div>
		<br />
		<!-- <div style="float: right; width: 216px;">
			<b class="receptFontSubHeader"> Received by 
			
			</b>
		</div> -->
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