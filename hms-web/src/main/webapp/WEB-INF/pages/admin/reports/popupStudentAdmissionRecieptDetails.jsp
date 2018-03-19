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
				alt='<s:property  value="customer.customerLogo" />' height="75px"
				width="70px" border="0" style="float: left;" />&nbsp;
			   </s:if>
	</div>
	<div class="invoice-logo-space1">
		<b class="receptFontHeader"> <s:property
				value="customer.organization" /> </b>
		<br />
		<span class="receptFontSubHeader"> <s:if
				test="%{customer.address.streetName != null && customer.address.streetName != ''}">
				<b><s:property value="customer.address.streetName" />, </b>
			</s:if> <br /> <s:if
				test="%{customer.address.addressLine1 != null && customer.address.addressLine1 != ''}">
				<b><s:property value="customer.address.addressLine1" /> </b>
			</s:if> <br /> <s:if
				test="%{customer.address.city != null && customer.address.city != ''}">
				<b><s:property value="customer.address.city" /> -</b>
			</s:if> <s:if
				test="%{customer.address.postalCode != null && customer.address.postalCode != ''}">
				<b><s:property value="customer.address.postalCode" /> ,</b>
			</s:if> <br /> <s:if
				test="%{customer.contactNumber != null && customer.contactNumber != ''}">
				<b>Phone: <s:property value="customer.contactNumber" /> </b>
			</s:if> <s:if
				test="%{customer.mobileNumber != null && customer.mobileNumber != ''}">
				<b>,<s:property value="customer.mobileNumber" /> .</b>
			</s:if> <s:else>
				.
				</s:else> </span>
		<br />
	</div>
</div>

<%
	int i = 0;
%>
<hr style="margin: 2px;" />
<table style="font-size: 10px; margin: 5px;" width="100%">
	<tr>
		<td>
			<label style="text-align: right; width: 84px;">
				Application No :
			</label>
			<b><s:property value="onlineApplicationDetails.applicationNumber" />
			</b>
		</td>
		<td>
			<label style="text-align: right; width: 84px;">
				Academic Year :
			</label>
			<b><s:property
					value="onlineApplicationDetails.academicYear.academicYear" /> </b>
		</td>
	</tr>
	<tr>
		<td class="test1">
			<label style="text-align: right; width: 84px;">
				Student Name :
			</label>
			<b><s:property
					value="onlineApplicationDetails.childrenFullPersonName" /> </b>
		</td>
		<td>
			<label style="text-align: right; width: 84px;">
				Class :
			</label>
			<b><s:property value="onlineApplicationDetails.classId.className" />
			</b>
		</td>
	</tr>
	<tr>
		<td class="test1">
			<label style="text-align: right; width: 84px;">
				Father Name :
			</label>
			<b><s:property value="onlineApplicationDetails.fatherName" /> </b>
		</td>
		<td>
			<label style="text-align: right; width: 84px;">
				Mobile Number :
			</label>
			<b><s:property value="onlineApplicationDetails.mobileNumber" />
			</b>
		</td>
	</tr>
	<tr>
		<td class="test1">
			<label style="text-align: right; width: 73px;">
				Receipt No :
			</label>
			<b><s:property
					value="onlineApplicationDetails.receiptNumber" /> </b>
		</td>
	</tr>
</table>
<div class="invoice">
	<div class="row">
		<div class="col-xs-12">
			<table class="table table-striped table-hover">
				<thead>
					<tr class="trHeadding">
						<th>
							S.No
						</th>
						<th colspan="4" style="text-align: center;">
							Terms & Particulars
						</th>
						<th style="text-align: center;">
							Amount
						</th>
					</tr>
					<tr style="border-bottom: 1px solid;"></tr>
				</thead>
				<tbody>
					<%
						i++;
					%>
					<s:if test="%{empId > 0}">
					<tr style="font-size: 12px;">
						<td
							style="text-align: right; border-bottom: 1px solid; border-left: none; border-right: 1px solid; width: 30px">
							<center>
								<b> <%=i%> </b>
							</center>
						</td>
						
						<td colspan="4"
							style="text-align: right; border: none; border-right: 1px solid; border-bottom: 1px solid;">
							RegistrationFee &nbsp;
						</td>
						<td
							style="text-align: left; border: none; border-bottom: 1px solid;">
							<center>
								<s:if test="%{empId > 0}">
									<b> <c:set value="${empId}" var="empId" /> <fmt:formatNumber
											value="${empId}" type="currency" pattern="##,##,###.00"
											var="balance" /> <s:property value="country.countryCurrency.currencySymbol" escapeHtml="false"/> <c:out value="${balance}" /> </b>
								</s:if>
								<s:else>
									0.00
									</s:else>
							</center>
						</td>
					</tr>
					<%
							i++;
						%>
						</s:if>
						<s:if test="%{bedId > 0}">
					<tr style="font-size: 12px;">
						
						<td
							style="text-align: right; border-bottom: 1px solid; border-left: none; border-right: 1px solid; width: 30px">
							<center>
								<b> <%=i%> </b>
							</center>
						</td>
						<td colspan="4"
							style="text-align: right; border: none; border-right: 1px solid; border-bottom: 1px solid;">
							ProspectiveFee &nbsp;
						</td>
						<td
							style="text-align: left; border: none; border-bottom: 1px solid;">
							<center>
								<s:if test="%{bedId > 0}">
									<b> <c:set value="${bedId}" var="bedId" /> <fmt:formatNumber
											value="${bedId}" type="currency" pattern="##,##,###.00"
											var="balance" /> <s:property value="country.countryCurrency.currencySymbol" escapeHtml="false"/> <c:out value="${balance}" /> </b>
								</s:if>
								<s:else>
									0.00
									</s:else>
							</center>
						</td>
					</tr>
					</s:if>
					<tr>
					</tr>
					<tr style="font-size: 12px;">
						<td
							style="text-align: right; border-bottom: 0px solid; border-top: 1px solid; border-left: none; border-right: 1px solid;"
							colspan="5" class="borderBottom">
							<label>
								<b>Total Amount</b>&nbsp;
							</label>
						</td>
						<td
							style="text-align: left; border-bottom: 0px solid; border-top: 1px solid; border-left: none;">
							<center>
								<s:if test="%{anyTitle > 0}">
									<b> <c:set value="${anyTitle}" var="anyTitle" /> <fmt:formatNumber
											value="${anyTitle}" type="currency" pattern="##,##,###.00"
											var="balance" /> <s:property value="country.countryCurrency.currencySymbol" escapeHtml="false"/> <c:out value="${balance}" /> </b>
								</s:if>
								<s:else>
								0.00
								</s:else>
							</center>
						</td>
					</tr>
					<tr style="font-size: 12px;">
						<td class="borderBottom"
							style="text-align: right; border-bottom: 0px solid; border-top: 1px solid; border-left: none; border-right: 1px solid;"
							colspan="5">
							<label>
								<b>Paid Amount</b>&nbsp;
							</label>
						</td>
						<td
							style="text-align: left; border-bottom: 1px solid; border-top: 1px solid; border-left: none;">
							<center>
								<s:if test="%{anyTitle > 0}">
									<b> <c:set value="${anyTitle}" var="anyTitle" /> <fmt:formatNumber
											value="${anyTitle}" type="currency" pattern="##,##,###.00"
											var="balance" /> <s:property value="country.countryCurrency.currencySymbol" escapeHtml="false"/> <c:out value="${balance}" /> </b>
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
								<s:if test="%{anyTitle > 0}">
									<s:property value="plTitle" /> <s:property value="country.countryCurrency.currencyWord" escapeHtml="false"/> Only
								</s:if>
								<s:else>
								Zero <s:property value="country.countryCurrency.currencyWord" escapeHtml="false"/> only
								</s:else>
							</center>
						</td>
					</tr>
			</table>
		</div>
	</div>
	<div>
		<div class="col-xs-6">
			<div class="wells">
				<%-- <s:if
					test="%{ViewStudentFineFeeDetails.addressForStudent1 != null &&  !ViewStudentFineFeeDetails.addressForStudent1.isEmpty()}"> --%>
				<address style="font-size: 10px; min-height: 55px;">
					<b><u>Address</u> </b>
					<br />
					<s:property value="onlineApplicationDetails.addressForStudent" />
				</address>
				<%-- </s:if> --%>
			</div>
		</div>
		<div class="col-xs-6" style="float: right; width: 150px;">
			<b class="receptFontSubHeader"> Authorized Signature</b>
		</div>
		<br />
		<br />
		<div style="float: right; width: 216px;">
			<b class="receptFontSubHeader"> Received by Mr. <s:if
					test='%{user.isParent=="Y"}'>
					<font color="green"> <s:property
							value="user.person.fatherName" /> </font>
				</s:if> <s:else>
					<font color="green"> <s:property
							value="user.person.firstName" />&nbsp;&nbsp; <s:property
							value="user.person.lastName" /> </font>
				</s:else> </b>
		</div>
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