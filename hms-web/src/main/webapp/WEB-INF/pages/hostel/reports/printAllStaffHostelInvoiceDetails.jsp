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
	<s:if test="%{tempList2!= null || tempList2!= null}">
	   <s:iterator value="tempList2">
		<table class="striped" style="width: auto;">
			<thead>
				<tr>
					<td
						style="border: none;color: #0000FF;text-align: center;" colspan="2">
						
							<P style="text-align: center;">
								<b style="font-size: 16px;"><s:property value="hostel.hostelName" /></b><br/>
								<b style="font-size: 12px;"><s:property value="customer.Address.streetAddress" /></b>
							</P>
							
							<p style="font-size: 14px;text-align: center;">
								<b>Staff Fee Receipt</b>
							</p>
					</td>
				</tr>
				<tr>
					<td style="border: none;">
						&nbsp;Invoice Number :
						<b><s:property value="staffType" /></b>
					</td>

					<td style="border: none;">
						&nbsp;&nbsp;&nbsp;&nbsp;Date :
						<b><s:property value="anyTitle" /></b>
					</td>
				</tr>
				<tr>
					<td style="border: none;">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Staff Name :
						<b><s:property value="qualification2" /></b>
					</td>
				</tr>
				<tr>
					<td style="border: none;">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Qualification :
						<b><s:property value="qualification1" /></b>
					</td>
				</tr>
			</thead>
			<s:set name="hostelTermId" value=""></s:set>
			<s:if
				test="%{feeDetailsList!= null || feeDetailsList!= null}">

				<s:iterator value="feeDetailsList">
					<s:if test="%{hostelTermId != #hostelTermId}">
						<tr>
							<s:if test='%{#hostelTermId == ""}'>
								<td
									style="text-align: right; border-bottom: 1px solid; border-top: 1px solid; border-left: none; border-right: 1px solid;">
									FEES FOR THE MONTH OF :
									<b><s:property value="termName" />-(<s:property
											value="fromMonthName" />-<s:property value="toMonthName" />)</b>
								</td>
								<td
									style="text-align: left; border-bottom: 1px solid; border-top: 1px solid; border-left: none; border-right: none;">
									Rs.
								</td>
							</s:if>
							<s:else>
								<td
									style="text-align: right; border-bottom: 1px solid; border-top: 1px solid; border-left: none; border-right: 1px solid;">
									FEES FOR THE MONTH OF :
									<b><s:property value="termName" />-(<s:property
											value="fromMonthName" />-<s:property value="toMonthName" />)</b>
								</td>
								<td
									style="text-align: left; border-bottom: 1px solid; border-top: 1px solid; border-left: none; border-right: none;">
									Rs.
								</td>
							</s:else>

						</tr>
					</s:if>
					<tr>
						<s:if test="%{paymentAmount>0}">
						<td
							style="text-align: right; border: none; border-right: 1px solid;">
							<s:property value="hostelFeeType" />
						</td>

						<td style="text-align: left; border: none;">
							<b>
							<c:set value="${paymentAmount}" var="priceAmt"/>
								<fmt:formatNumber value="${priceAmt}" type="currency"  pattern="##,##,###.00" var="balance"/>  
							     <c:out value="${balance}" />
							</b>
						</td>
						</s:if>
					</tr>
					<s:set name="hostelTermId" value="%{hostelTermId}"></s:set>
				</s:iterator>
				<s:if test="%{custId > 0}">   <%-- here custid is a discount amount --%>
					<tr>
						<td
							style="text-align: right; border-bottom: 1px solid; border-top: 1px solid; border-left: none; border-right: 1px solid;">
							<s:property value="wishTitle" />
						</td>
						<td
							style="text-align: left; border-bottom: 1px solid; border-top: 1px solid; border-left: none;">
							<b>
							<c:set value="${custId}" var="priceAmt"/>
								<fmt:formatNumber value="${priceAmt}" type="currency"  pattern="##,##,###.00" var="balance"/>  
							     <c:out value="${balance}" />
							</b>
						</td>
					</tr>
				</s:if>
				<tr>
					<td
						style="text-align: right; border-bottom: 1px solid; border-top: 1px solid; border-left: none; border-right: 1px solid;">

						<label>
							Total Amount
						</label>
					</td>
					<td
						style="text-align: left; border-bottom: 1px solid; border-top: 1px solid; border-left: none;">
						<b>
						<c:set value="${CreatedById}" var="priceAmt"/> <%-- here CreatedById is total amount --%>
								<fmt:formatNumber value="${priceAmt}" type="currency"  pattern="##,##,###.00" var="balance"/>  
							     <c:out value="${balance}" />
						</b>
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
		</table>.
		</s:iterator>
	 </s:if>
	</div>
</div>

