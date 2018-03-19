<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Online Payment
				</div>
			</div>
			<div class="portlet-body">
				<div class="tabbable tabbable-custom tabbable-full-width">
					<ul class="nav nav-tabs">
						<li>
							<a
								href='${pageContext.request.contextPath}/reports/ajaxPrintViewStudentInvoiceDefaulters.do?pdfId=pdf' target="_new">Print Defaulters</a>
						</li>
						<li class="active">
								<s:url id="urlExamSettings" action="ajaxAdminGetFourteenSchoolFee" namespace="/admin">
								</s:url>
								<sj:a href="%{urlExamSettings}" targets="mainContentDiv" data-toggle="tab">View Payment Defaulters</sj:a>
							</li>
					</ul>
					<div class="tab-content">
						<s:if
							test="%{studentsFeeTypeList != null && !studentsFeeTypeList.isEmpty() }">
							<div class="col-md-12">
								<h5 class="pageTitle bold form-section">
									Due Amount as of Today:
									<del>
										<b style="font-size: 14px;">&#2352;</b>
									</del>
									<c:set var="paymentAmt" value='${totalAmount}' />
									<fmt:formatNumber value="${paymentAmt}" type="currency"
										pattern="##,##,###.00" var="paymentBalance" />
									<c:out value="${paymentBalance}" />
									No. of Students :
									<strong><s:property value="displayAttendanceSet.size" />
									</strong>
								</h5>
							</div>
							<table
								class="table table-striped table-bordered table-hover table-full-width"
								id="sample_2">
								<thead>
									<tr>
										<th>
											R.No#
										</th>
										<th>
											Student Name
										</th>
										<th>
											Class & Section
										</th>
										<th>
											Term Name
										</th>
										<th>
											Parent Phone#
										</th>
										<th>
											Due Date
										</th>
										<th>
											Amount
										</th>
										<th>
											Balance
										</th>
									</tr>
								</thead>
								<tbody>
									<s:iterator value="studentsFeeTypeList">
										<tr>
											<td>
												<s:property value="rollNumber" />
											</td>
											<td>
												<s:property value="firstName" />
											</td>
											<td>
												<s:property value="feeType" />
											</td>
											<td>
												<s:property value="termName" /> 
											</td>
											<td>
												<s:property value="phoneNumber" />
											</td>
											<td>
												<s:property value="description" />
											</td>
											<td>
												<c:set var="paymentAmt" value='${feeAmount}' />
												<fmt:formatNumber value="${paymentAmt}" type="currency"
													pattern="##,##,###.00" var="paymentBalance" />
												<c:out value="${paymentBalance}" />
											</td>
											<td>
												<c:set var="paymentAmt" value='${paymentAmount}' />
												<fmt:formatNumber value="${paymentAmt}" type="currency"
													pattern="##,##,###.00" var="paymentBalance" />
												<c:out value="${paymentBalance}" />
											</td>
										</tr>
										<s:set name="termsName" value="%{termName}"></s:set>
									</s:iterator>
								</tbody>
							</table>
						</s:if>
						<s:else>
							<div class="alert alert-info">
								There are no students fee defaulters.
							</div>
						</s:else>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
TableAdvanced.init();
UIExtendedModals.init();
changePageTitle("Payment Defaultes");
</script>
