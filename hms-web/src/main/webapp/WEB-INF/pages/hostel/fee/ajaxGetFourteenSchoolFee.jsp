<%@ include file="/common/taglibs.jsp"%>
<div class="grid_14">
	<a
		href='${pageContext.request.contextPath}/reports/ajaxPrintHostelStudentDefaulters.do?pdfId=pdf&tempString=HOSTEL'
		class="linkRight">Print Defaulters</a>
	<s:if test="%{objectList != null && !objectList.isEmpty() }">
	<div class="grid_14" align="right" data-target="paymentDefaulterCont">
		<jsp:include
			page="/WEB-INF/pages/common/ajaxViewPaginationOptions.jsp"></jsp:include>
	</div>
		<h1>
			Due Amount as of Today:
			<del>
				<b style="font-size: 14px;">&#2352;</b>
			</del>
			<c:set var="paymentAmt" value='${totalAmount}' />
				<fmt:formatNumber value="${paymentAmt}" type="currency"  pattern="##,##,###.00" var="paymentBalance"/>  
			     <c:out value="${paymentBalance}" /> No. of Students :
			<strong><s:property value="studentFee14List.size" /> </strong>
		</h1>
		<div class="grid_14 th" id="results">
			<div class="grid_10" style="float: left;">
				<div class="grid_1">
					R.No#
				</div>
				<div class="grid_3">
					Student Name
				</div>
				<div class="grid_2">
					Class&Section
				</div>
				<div class="grid_2">
					Parent Phone#
				</div>
				<div class="grid_2">
					Due Date
				</div>
			</div>
			<div class="grid_4" style="float: right;">
				<div class="grid_2">
					Amount
				</div>
				<div class="grid_2">
					Balance
				</div>
			</div>
		</div>
		<div id="paymentDefaulterCont">
			<%int i = 0; %>
			<s:iterator value="objectList">
				<s:if test="%{hostelTermName != #hostelTermsName}">
					<div class="grid_14 row">
						<LABEL><b><center><s:property value="hostelTermName" /></center></b></LABEL>
					</div>
				</s:if>
				<s:set name="rollNum" value="rollNumber" />
				<div class="grid_14 row" id="results">

					<div class="grid_10">
						<div class="grid_1">
							<s:property value="rollNumber" />
						</div>
						<div class="grid_3">
							<s:property value="firstName" />
						</div>
						<div class="grid_2">
							<s:property value="feeType" />
						</div>
						<div class="grid_2">
							<s:if test="%{phoneNumber != null && !phoneNumber.isEmpty() }">
								<s:property value="phoneNumber" />
							</s:if>
							<s:else>
								&nbsp;
							</s:else>
						</div>
						<div class="grid_2">
							<s:property value="dueDateStr" />
						</div>
					</div>
					<div class="grid_4" style="float: right;">
						<div class="grid_2">
							<c:set var="paymentAmt" value='${feeAmount}' />
							<fmt:formatNumber value="${paymentAmt}" type="currency"
								pattern="##,##,###.00" var="paymentBalance" />
							<c:out value="${paymentBalance}" />
						</div>
						<div class="grid_2">
							<c:set var="paymentAmt" value='${paymentAmount}' />
							<fmt:formatNumber value="${paymentAmt}" type="currency"
								pattern="##,##,###.00" var="paymentBalance" />
							<c:out value="${paymentBalance}" />
						</div>
					</div>
				</div>
				<s:set name="hostelTermsName" value="%{hostelTermName}"></s:set>
			</s:iterator>
		</div>
	</s:if>
</div>

<s:else>
	<br />
	<br />
	<div>
		There are no students fee defaulters.
	</div>
</s:else>
 
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/subscription/paginator_dev.js">
</script>
<script type="text/javascript">
changePageTitle("Payment Defaultes");
$('#paymentDefaulterCont').pagination();
</script>
