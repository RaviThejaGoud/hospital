<%@ include file="/common/taglibs.jsp"%>
<jsp:include page="/common/messages.jsp"></jsp:include>
<div data-width="760" class="modal fade modal-overflow in" id="categoryresponsive"
	style="display: block; width: 760px; margin-left: -379px; margin-top: 200px;"
	aria-hidden="false">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" 
			aria-hidden="true"></button>
			<h4 class="modal-title">View Student Pending Fee</h4> 
	</div>
	<s:if test="%{(tempList != null && !tempList.isEmpty()) || (studentTransportTermsList != null && !studentTransportTermsList.isEmpty())}">
	<s:set name="schoolTermsId" value=""></s:set>
	<s:set name="schoolFeeSettingId" value=""></s:set>
	<s:set name="feeParticularId" value=""></s:set>
	<table class="table table-striped table-bordered table-hover table-full-width dataTable" id="sample_2">
		<thead>
			<tr>
				<td colspan="3" align="center">
				<strong> Category Name</strong>
				</td>
				<s:if test="%{tempBoolean}">
					<td colspan="4" align="center">
				</s:if>	<s:else><td colspan="3" align="center"></s:else>
				
				<strong> <s:property value="schoolCategoryVO.categoryName"/>    </strong>
				</td>
				
			</tr>
			<tr>
				<td>
				<strong> Particular Name</strong>
				</td>
				<td>
				<strong> Total Amount</strong>
				</td>
				<td>
				<strong> Paid Amount</strong>
				</td>
				<td>
				<strong> Discount Amount</strong>
				</td>
				<s:if test="%{tempBoolean}">
				<td>
					<strong> Concession Amount</strong>
				</td>
				</s:if>
				<td>
				<strong>  Balance Amount </strong>
				</td>
				<td>
				<strong> Payment Status</strong>
				</td>
			</tr>
		</thead>
		<tbody>
		<s:if test="%{tempList != null && !tempList.isEmpty()}">
			<s:iterator value="tempList">
				<s:set name="schoolTermsId" value="%{schoolTermId}"></s:set>
				<tr>
					<td colspan="7">
						<s:if test="%{feeSettingId != #schoolFeeSettingId}">
							<div class="grid_12 row">
								<h4>
									<center>
										<s:property value="settingName" />
									</center>
								</h4>
							</div>
						</s:if>
					</td>
				</tr>
				<s:set name="termPayableAmount" value="payableAmount"></s:set>
				<tr>
					<td colspan="7">
						<center>
							<strong> <span id='<s:property value="schoolTermId" />'><s:property
										value="termName" /> </span> </strong>
						</center>
					</td>
				</tr>
				<s:iterator value="pendingStudentList">
					<tr>
						<td>
							<s:property value="feeType" />
						</td>
						<td>
							<s:property value="feeAmount" />
						</td>
						<td>
							<s:property value="paymentAmount" />
						</td>
						<td>
							<s:property value="discountAmount" />
						</td>
						<s:if test="%{tempBoolean}">
						<td>
							<s:property value="concessionAmount" />
						</td>
						</s:if>
						<td>
							<s:property value="payableAmount" />
						</td>
						
						<td>
							<s:if test='%{deleteStatus == "C"}'>
								<font color="red"><b>Not Paid</b> </font>
							</s:if>
							<s:elseif test="%{(paymentAmount+discountAmount+paymentConcessionAmount) == feeAmount}">
								<font color="green"><b>Paid</b> </font>
							</s:elseif>
							<s:elseif test="%{(paymentAmount > 0) && payableAmount > 0 }">
								<font color="green"><b>Partial Payment</b> </font>
							</s:elseif>
							<s:else>
								<font color="red"><b>Not Paid</b> </font>
							</s:else>
						</td>
					</tr>
				</s:iterator>
				<s:set name="schoolFeeSettingId" value="%{feeSettingId}"></s:set>
			</s:iterator>
			</s:if>
			<s:if test="%{studentTransportTermsList != null && !studentTransportTermsList.isEmpty()}">
			<s:iterator value="studentTransportTermsList">
				<s:set name="schoolTermsId" value="%{schoolTermId}"></s:set>
				<tr>
					<td colspan="7">
						<s:if test="%{feeSettingId != #schoolFeeSettingId}">
							<div class="grid_12 row">
								<h4>
									<center>
										<s:property value="settingName" />
									</center>
								</h4>
							</div>
						</s:if>
					</td>
				</tr>
				<s:set name="termPayableAmount" value="payableAmount"></s:set>
				<tr>
					<td colspan="7">
						<center>
							<strong> <span id='<s:property value="schoolTermId" />'><s:property
										value="termName" /> </span> </strong>
						</center>
					</td>
				</tr>
				<s:if test="%{studentTransportFeeList != null && !studentTransportFeeList.isEmpty()}">
				<s:iterator value="studentTransportFeeList">
					<tr>
						<td>
							<s:property value="feeType" />
						</td>
						<td>
							<s:property value="feeAmount" />
						</td>
						<td>
							<s:property value="paymentAmount" />
						</td>
						<td>
							<s:property value="discountAmount" />
						</td>
						<s:if test="%{tempBoolean}">
						<td>
							<s:property value="concessionAmount" />
						</td>
						</s:if>
						<td>
							<s:property value="payableAmount" />
						</td>
						
						<td>
							<s:if test="%{(paymentAmount+discountAmount+paymentConcessionAmount) == feeAmount}">
								<font color="green"><b>Paid</b> </font>
							</s:if>
							<s:elseif test="%{(paymentAmount > 0) && payableAmount > 0 }">
								<font color="green"><b>Partial Payment</b> </font>
							</s:elseif>
							<s:else>
								<font color="red"><b>Not Paid</b> </font>
							</s:else>
						</td>
					</tr>
				</s:iterator>
				</s:if>
				<s:set name="schoolFeeSettingId" value="%{feeSettingId}"></s:set>
			</s:iterator>
			</s:if>
	</s:if>
	<s:else>
	<div class="alert alert-info">
		No pending fee for this student.
	</div>
	</s:else>
</div>
