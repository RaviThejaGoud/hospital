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
	<s:if test="%{objectList!= null || objectList!= null}">
	   <s:iterator value="objectList">
		<table class="striped" style="width: auto;border-collapse: separate;">
			<thead>
				<tr>
					<td
						style="border: none;color: #0000FF;text-align: center;" colspan="2">
						
							<P style="text-align: center;">
								<b style="font-size: 16px;"><s:property value="hostel.hostelName" /></b><br/>
								<b style="font-size: 12px;"><s:property value="customer.Address.streetAddress" /></b>
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
						<b><s:property value="tempString" /></b>
					</td>

					<td style="border: none;">
						&nbsp;&nbsp;&nbsp;&nbsp;Date :
						<b><s:property value="anyTitle" /></b>
					</td>

				</tr>
				<tr>
					<td style="border: none;">
						&nbsp;&nbsp;Student Name :
						<b><s:property value="description" /></b>
					</td>
				</tr>
				<tr>
					<td style="border: none;">
						Class / Section :
						<b><s:property value="classAndSection" /></b>
					</td>
					<td style="border: none;">
						Roll No :
						<b><s:property value="rollNumber" /></b>
					</td>
				</tr>
			</thead>

			<s:set name="hostelTermId" value=""></s:set>
			<s:if
				test="%{feeDetailsList!= null || feeDetailsList!= null}">
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
				<s:iterator value="feeDetailsList">
					<s:if test="%{hostelTermId != #hostelTermId}">
						<tr>

							<s:if test='%{#hostelTermId == ""}'>
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
							<s:set var="total" value="%{hoastelFeeTotal}" />
							<c:set value="${total}" var="priceAmt"/>
								<fmt:formatNumber value="${total}" type="currency"  pattern="##,##,###.00" var="balance"/>  
							     <c:out value="${balance}" />
							</b></center>
						</td>
					</tr>
					<s:set name="hostelTermId" value="%{hostelTermId}"></s:set>
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
						<c:set value="${categoryId}" var="priceAmt"/>
								<fmt:formatNumber value="${priceAmt}" type="currency"  pattern="##,##,###.00" var="balance"/>  
							     <c:out value="${balance}" />
						</b></center>
					</td>
				</tr>
				
				<s:if test="%{custId > 0}">
					<tr>
						<td
							style="text-align: right;border-bottom: 0px solid; border-top: 0px solid; border-left: none; border-right: 1px solid;">
							<s:property value="wishTitle" />
						</td>
						<td
							style="text-align: left;border-bottom: 0px solid; border-top: 0px solid; border-left: none;">
							<center><b>
							<c:set value="${custId}" var="priceAmt"/>
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
						<c:set value="${transportCategoryId}" var="priceAmt"/>
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
		</s:iterator>
		</s:if>
	</div>
</div>

