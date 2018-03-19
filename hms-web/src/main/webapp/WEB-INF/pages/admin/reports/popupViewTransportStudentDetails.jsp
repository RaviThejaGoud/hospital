<%@ include file="/common/taglibs.jsp"%>
<style type="text/css">
@import url("${pageContext.request.contextPath}/styles/960/960.css");

@import
	url("${pageContext.request.contextPath}/styles/default/style.css");

body {
	background: #fff;
}
</style>
<div class="wrapper container_10">
	<div class="wrapper">
		<table class="striped" style="width: auto;border-collapse: separate;">
			<thead>
				<tr>
					<td
						style="border: none;color: #0000FF;text-align: center;" colspan="2">
						
							<P style="text-align: center;">
								<b style="font-size: 16px;"><s:property value="customer.organization" /></b><br/>
								<b style="font-size: 12px;"><s:property value="customer.customerAddress" /></b>
							</P>
							
							<p style="font-size: 14px;text-align: center;">
								<b>Fee Receipt</b>
							</p>
						
					</td>


				</tr>
				<%--<tr>
					<td style="text-align: left;border: none;float: right;">
						<CENTER></CENTER>
						</td>
				</tr>
				<tr>
				<td style="text-align: left;border: none;float: right;">
						<center></center>
						</td>
				</tr>
				--%>
				<tr>
					<td style="border: none;">
						&nbsp;Invoice Number :
						<b><s:property value="anyId" /></b>
					</td>

					<td style="border: none;">
						&nbsp;&nbsp;&nbsp;&nbsp;Date :
						<b><s:property value="anyTitle" /></b>
					</td>

				</tr>
				<tr>
					<td style="border: none;">
						&nbsp;&nbsp;Student Name :
						<b><s:property value="student.studentName" /></b>
					</td>
				</tr>
				<tr>
					<td style="border: none;">
						Class / Section :
						<b><s:property value="student.classSection.classAndSection" /></b>
					</td>
					<td style="border: none;">
						Roll No :
						<b><s:property value="student.rollNumber" /></b>
					</td>
				</tr>
			</thead>

			<s:set name="schoolTermsId" value=""></s:set>
			<s:if
				test="%{studentPaymentList!= null || studentPaymentList!= null}">
					<tr>
						<td
							style="text-align: right; border-bottom: 0px solid; border-top: 1px solid; border-left: none; border-right: 1px solid;">
							
							<b><center>Terms & Particulars</center></b>
						</td>
						<td
							style="text-align: left; border-bottom: 1px solid; border-top: 1px solid; border-left: none; border-right: none;">
							<b><center>Rs.</center></b>
						</td>
							
					</tr>
				<s:iterator value="studentPaymentList">
					<s:if test="%{schoolTermId != #schoolTermsId}">
						<tr>

							<s:if test='%{#schoolTermsId == ""}'>
								<td
									style="text-align: right;border-bottom: 0px solid;  border-top: 1px solid; border-left: none; border-right: 1px solid;">
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
								
							</s:else>

						</tr>
					</s:if>
					<tr>
						<td
							style="text-align: right; border: none; border-right: 1px solid;">
							<s:property value="feeType" />
						</td>

						<td style="text-align: left; border: none;">
							<center><b>
							<s:set var="total" value="%{paymentAmount + discountAmount}" />
							<c:set value="${total}" var="priceAmt"/>
								<fmt:formatNumber value="${total}" type="currency"  pattern="##,##,###.00" var="balance"/>  
							     <c:out value="${balance}" />
							</b></center>
						</td>
					</tr>
					<s:set name="schoolTermsId" value="%{schoolTermId}"></s:set>
				</s:iterator>
				<tr>
					<td
						style="text-align: right;border-bottom: 0px solid; border-top: 1px solid; border-left: none; border-right: 1px solid;">

						<label>
							Total Amount
						</label>
					</td>
					<td
						style="text-align: left; border-bottom: 0px solid;border-top: 1px solid; border-left: none;">
						<center><b>
						<c:set value="${fourteenTotalAmount}" var="priceAmt"/>
								<fmt:formatNumber value="${priceAmt}" type="currency"  pattern="##,##,###.00" var="balance"/>  
							     <c:out value="${balance}" />
						</b></center>
					</td>
				</tr>
				
				<s:if test="%{thirtyTotalAmount > 0}">
					<tr>
						<td
							style="text-align: right;border-bottom: 0px solid; border-top: 0px solid; border-left: none; border-right: 1px solid;">
							<s:property value="wishTitle" />
						</td>
						<td
							style="text-align: left;border-bottom: 0px solid; border-top: 0px solid; border-left: none;">
							<center><b>
							<c:set value="${thirtyTotalAmount}" var="priceAmt"/>
								<fmt:formatNumber value="${priceAmt}" type="currency"  pattern="##,##,###.00" var="balance"/>  
							     <c:out value="${balance}" />
							</b></center>
						</td>
					</tr>
				</s:if>
				<tr>
					<td
						style="text-align: right; border-bottom: 1px solid; border-top: 1px solid; border-left: none; border-right: 1px solid;">

						<label>
							Paid Amount
						</label>
					</td>
					<td
						style="text-align: left; border-bottom: 1px solid; border-top: 1px solid; border-left: none;">
						<center><b>
						<c:set value="${totalAmount}" var="priceAmt"/>
								<fmt:formatNumber value="${priceAmt}" type="currency"  pattern="##,##,###.00" var="balance"/>  
							     <c:out value="${balance}" />
						</b></center>
					</td>
				</tr>
			</s:if>
			<tr>
				<br />
				<td style="text-align: right; border: none;" colspan="2">
					<label>
						For
						<b><s:property value="customer.organization" />
						</b>
					</label>
				</td>
			</tr>
			<tr>
				<td style="text-align: left; border: none; border-bottom: 1px;">
					<label>
						Note: Fees once paid will not be refunded under any circumstances
					</label>
				</td>
			</tr>
		</table>
	</div>
</div>

