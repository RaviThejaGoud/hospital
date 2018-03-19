<%@ include file="/common/taglibs.jsp"%>
<s:if test="%{childFeeDetails != null && !childFeeDetails.isEmpty()}">
	<table
		class="table table-striped table-bordered table-hover table-full-width"
		id="sample_2">
		<thead>
			<tr>
				<th>
					Fee Type
				</th>
				<th>
					Amount
				</th>
				<th>
					Due Date
				</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="childFeeDetails">
				<tr>
					<td>
						<s:property value="termName" />
					</td>
					<td>
						<c:set var="paymentAmt" value='${feeAmount}' />
						<fmt:formatNumber value="${paymentAmt}" type="currency"
							pattern="##,##,###.00" var="paymentBalance" />
						<c:out value="${paymentBalance}" />
					</td>
					<td>
						<s:property value="fromMonthName" />
					</td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
	<div>
		<a
			href="${pageContext.request.contextPath}/student/viewMyChildFees.do" class="btn blue">More
			Details</a>
	</div>
</s:if>
<s:else>
	<div class="alert alert-info">
		Currently there are no upcoming payments.
	</div>
</s:else>

<s:if test='%{(customer.transportModuleStatus==true) }'>
	<s:if test="%{objectList != null && !objectList.isEmpty()}">
		<h4 class="pageTitle bold">
			Upcoming Transport Payments
		</h4>
		<table
			class="table table-striped table-bordered table-hover table-full-width"
			id="sample_2">
			<thead>
				<tr>
					<th>
						Fee Type
					</th>
					<th>
						Amount
					</th>
					<th>
						Due Date
					</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="objectList">
					<tr>
						<td>
							<s:property value="termName" />
						</td>
						<td>
							<c:set var="paymentAmt" value='${feeAmount}' />
							<fmt:formatNumber value="${paymentAmt}" type="currency"
								pattern="##,##,###.00" var="paymentBalance" />
							<c:out value="${paymentBalance}" />
						</td>
						<td>
							<s:property value="fromMonthName" />
						</td>
					</tr>
				</s:iterator>
			</tbody>
		</table>
		<div>
			<a
				href="${pageContext.request.contextPath}/student/viewMyChildFees.do" class="btn blue">More
				Details</a>
		</div>
	</s:if>
	<s:else>
		<div class="alert alert-info">
			Currently there are no upcoming transport payments.
		</div>
	</s:else>
</s:if>
<s:if test='%{(customer.hostelModuleStatus==true) }'>
	<s:if test="%{tempList != null && !tempList.isEmpty()}">
		<h4 class="pageTitle bold">
			Upcoming Hostel Payments
		</h4>
		<table
			class="table table-striped table-bordered table-hover table-full-width"
			id="sample_2">
			<thead>
				<tr>
					<th>
						Fee Type
					</th>
					<th>
						Amount
					</th>
					<th>
						Due Date
					</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="tempList">
					<tr>
						<td>
							<s:property value="termName" />
						</td>
						<td>
							<c:set var="paymentAmt" value='${feeAmount}' />
							<fmt:formatNumber value="${paymentAmt}" type="currency"
								pattern="##,##,###.00" var="paymentBalance" />
							<c:out value="${paymentBalance}" />
						</td>
						<td>
							<s:property value="fromMonthName" />
						</td>
					</tr>
				</s:iterator>
			</tbody>
		</table>
		<div>
			<a
				href="${pageContext.request.contextPath}/student/viewMyChildFees.do" class="btn blue">More
				Details</a>
		</div>
	</s:if>
	<s:else>
		<div class="alert alert-info">
			Currently there are no upcoming hostel payments.
		</div>
	</s:else>
</s:if>
