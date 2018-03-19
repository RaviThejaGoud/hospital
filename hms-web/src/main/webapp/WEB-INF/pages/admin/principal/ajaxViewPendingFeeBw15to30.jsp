<%@ include file="/common/taglibs.jsp"%>
<div class="block grid_17" id="pendingStudentsFeeList">
	<div id="pendingStudentsFeeList">
		<jsp:include page="/WEB-INF/pages/schoolfee/ajaxViewStudentPendingFeeList.jsp" />
	</div>
	<div class="block_content">
		<s:if
		test="%{studentFeeAbove15List != null && !studentFeeAbove15List.isEmpty() && studentsList !=null && !studentsList.isEmpty()}">
		<div class="grid_17">
			<h6>
				<a href="#">Pending 15-30 days(<del>
						<b style="font-size: 14px;">&#2352;</b>
					</del>---<s:property value="totalAmount" />)--(<s:property value="studentFeeAbove15List.size" />)</a>
			</h6>
			<br />
			<table class="striped" width="100%" cellpadding="1"
				style="margin-bottom: 0;" cellspacing="1" id="results">
				<thead>
					<tr>
						<!--<th width="8%" class="head">
								S.No
							</th>-->
						<th class="head" width="20%">
							Roll NO
						</th>
						<th class="head" width="20%">
							Student Name
						</th>
						<th class="head" width="20%">
							Parent Phone Number
						</th>
						<th class="head" width="15%">
							Fee Type
						</th>
						<th class="head" width="12%">
							Amount
						</th>
						<th class="head">
							Due Date
						</th>
					</tr>
				</thead>
			</table>
			<div id="resultsPage">
				<%int i = 0; %>
				<s:iterator value="studentFeeAbove15List">
					<s:set name="rollNum" value="rollNumber" />
					<table class="striped" width="100%" cellpadding="1" style="margin-bottom: 0; padding: 5px;border-width: 1px 1px 1px;" cellspacing="1" id="results">
						<tr class="loaded">
							<!--<td  width="8%">
									<%i++; %><%=i %>
								</td>-->
							<td width="20%">
								<s:property value="rollNumber" />
							</td>
							<td width="20%">
								<s:property value="firstName" />
							</td>
							<td width="20%">
								<s:property value="mobileNumber" />
							</td>
							<td colspan="3" width="50%">
								<s:if test="%{studentsList !=null && !studentsList.isEmpty()}">
									<s:iterator value="studentsList">
										<s:if test="%{rollNumber == #rollNum}">
											<table>
												<tr>
													<td width="42%" style="border-bottom: none;">
														<s:property value="feeType" />
													</td>
													<td width="32%" style="border-bottom: none;">
														<s:property value="feeAmount" />
													</td>
													<td style="border-bottom: none;">
														<s:property value="feeDueDateStr" />
													</td>
												</tr>
											</table>
										</s:if>
									</s:iterator>
								</s:if>
							</td>
						</tr>
					</table>
				</s:iterator>
			</div>
		</div>
	</s:if>
		<s:else>
         	There are no students fee pending for 15-30 days.
        </s:else>
	</div>
</div>
<style type="text/css">
.active {
	color: #0033CC;
	text-decoration: none;
}

.inactive {
	font-weight: bold;
	text-decoration: underline;
	cursor: default;
}

.paginator {
	text-align: center;
	color: #FFF;
}
</style>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/subscription/paginator.js">
</script>
<script type="text/javascript">
$(function() {
	$("#resultsPage").pagination();
});
</script>