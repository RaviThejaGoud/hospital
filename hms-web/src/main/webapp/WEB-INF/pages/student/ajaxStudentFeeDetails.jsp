<%@ include file="/common/taglibs.jsp"%>
<br/>
<strong>Due Payments </strong>
<div class="spaceDiv"></div>
<s:if test="%{feeTypeList !=null && !feeTypeList.isEmpty()}">
	<p>
		<span class="label label-danger">NOTE :</span>The student has not paid
		this term fees, you can find due amount in balance amount.
	</p>
	<table
		class="table table-striped table-bordered table-hover table-full-width"
		id="sample_2">
		<thead>
			<tr>
					<th style="text-align: center;">
						 Type
					</th>
					<th style="text-align: center;">
						Term Name
					</th>
					<th style="text-align: center;">
						Due Date
					</th>
					<th style="text-align: center; width:10%;">
						Total
					</th>
					<th style="text-align: center; width:10%;">
						Paid
					</th>
					<th style="text-align: center; width:10%;">
						Discount
					</th>
					<th style="text-align: center; width:10%;">
						Concession
					</th>
					<th style="text-align: center; width:10%;">
						Balance
					</th>
			</tr>
		</thead>
		<tbody>
				<s:set var="feeSettingsId" value=""></s:set>
				<s:iterator value="feeTypeList">
						<s:set name="rollNum" value="rollNumber" />
						<tr>
						<s:if test="%{feeSettingId != #feeSettingsId}">
							<td colspan="1" and rowspan='<s:property value="numberOfTerms"/>' style="text-align:center; vertical-align: middle;">
		 						<s:property value="settingName" />
							</td>
						</s:if>
							<td align="center">
								<s:property value="termName" />
							</td>
							<td align="center">
								<s:property value="dueDateStr" />
							</td>
							<td align="center">
								<c:set var="paymentAmt" value='${feeAmount}' />
								<fmt:formatNumber value="${paymentAmt}" type="currency"
									pattern="##,##,##0.00" var="paymentBalance" />
								<c:out value="${paymentBalance}" />
							</td>
							<td align="center">
								<s:property value="payableAmount" />
							</td>
							<td align="center">
								<s:property value="discountAmount" />
							</td>
							<td align="center">
								<s:property value="concessionAmount" />
							</td>
							<td align="center">
								<c:set var="paymentAmt" value='${paymentAmount}' />
								<fmt:formatNumber value="${paymentAmt}" type="currency"
									pattern="##,##,##0.00" var="paymentBalance" />
								<c:out value="${paymentBalance}" />
							</td>
						</tr>
						<s:set var="feeSettingsId" value="feeSettingId"></s:set>
				</s:iterator>
		</tbody>
			<tr>
			 	<td colspan="6">
			 		<td colspan="6"  align="center">
			 			<label style="text-align: center; padding-right: 35%;"><b>TOTAL</b></label>
			 			<label style="text-align: center; padding-left: -5%;"><b><s:property value="balanceAmount"></s:property></b></label>
				 	</td>
			   </td>
		 </tr>
	</table>
</s:if>
<s:else>
	<div class="alert alert-info">
		This student do not have any un-paid amount.
	</div>
</s:else>

<s:if test="%{(allStudentsList !=null && !allStudentsList.isEmpty()) || (tempList!= null && !tempList.isEmpty())}">
<strong>Payment History</strong>
<div class="spaceDiv"></div>
		<table
			class="table table-striped table-bordered table-hover table-full-width"
			id="sample_2">
			<thead>
				<tr>
					<th>
						Payment Date
					</th>
					<th>
						Receipt Number
					</th>
					<th>
						Paid Amount
					</th>
					<th>
						Discount
					</th>
					<th>
						Receipt
					</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="allStudentsList">
					<tr>
						<td>
							<s:property value="paymentDateStr" />
						</td>
						<td>
							<s:property value="invoiceNumber" />
						</td>
						<td>
							<s:property value="paidAmount" />
						</td>
						<td>
							<s:property value="discountAmount" />
						</td>
						<td>
						<s:if test='%{invoiceNumber > 0}'>
									<a style="cursor: pointer" id="noPrint"
										onClick="javascript:printAdmissionPreview('<s:property value='student.id'/>',
                                        '<s:property value='ctrPaymentDateStr'/>',
                                        '<s:property value='invoiceNumber'/>',<s:property value="paidAmount" />,'download')"
										; target="_new"><img style="" src="../images/index.jpg">
										Invoice : <s:property value="invoiceNumber" /> </a>
						</s:if>
						<s:else>
							-
						</s:else>
						</td>
					</tr>
				</s:iterator>
				<s:iterator value="tempList">
					<tr>
						<td>
							<s:property value="paymentDateStr" />
						</td>
						<td>
							<s:property value="invoiceNumber" />
						</td>
						<td>
							<s:property value="fineFeeAmount" />
						</td>
						<td>
							<s:property value="discountAmount" />
						</td>
						<td>
							<s:if test='%{invoiceNumber > 0}'>
								<a style="cursor: pointer;" id="noPrint"
									onClick="javascript:printStudentFineFeeInvoice('<s:property value='anyId'/>','<s:property value='invoiceNumber'/>',<s:property value="fineFeeAmount" />,'<s:property value="paymentDateStr" />')";
                                   target="_new"><img
										style="" src="../images/index.jpg"> Invoice : <s:property
										value="invoiceNumber" /> </a>
							</s:if>
						</td>
					</tr>
				</s:iterator>
			</tbody>
	</table>
</s:if>
<s:else>
		<div class="alert alert-info">
			This student do not have any invoice.
		</div>
</s:else>
