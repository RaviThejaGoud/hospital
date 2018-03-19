<%@ include file="/common/taglibs.jsp"%>
<s:if test="%{studentFee14List != null && !studentFee14List.isEmpty() }">
	<div class="grid_14" align="right">
		<jsp:include
			page="/WEB-INF/pages/common/ajaxViewPaginationOptions.jsp"></jsp:include>
	</div>
	<div class="grid_15">
		<h6>
			Due Amount as of Today: 
					<del><b style="font-size: 14px;">&#2352;</b></del><s:property value="totalAmount" />0  
		</h6>
		<h6>No. of Students : <strong><s:property value="studentFee14List.size"/></strong></h6>
		<table class="striped" width="100%" cellpadding="1"
			style="margin-bottom: 0;" cellspacing="1" id="results">
			<thead>
				<tr>
					<th width="15%" class="head">
						Roll NO
					</th>
					<th class="head">
						Student Name
					</th>
					<th class="head" width="15%">
						Parent Phone#
					</th>
					<th class="head" width="15%">
						Fee Type
					</th>
					<th class="head" width="10%">
						Due Date
					</th>
					<th class="head" width="10%">
						Fee Amount
					</th>
					<th class="head" width="10%">
						Balance
					</th>
				</tr>
			</thead>
		</table>
		<div id="resultsPage">
			<%int i = 0; %>
			<s:iterator value="studentFee14List">
				<s:set name="rollNum" value="rollNumber" />
				<table class="striped" width="100%" cellpadding="1"
					style="margin-bottom: 0; border-width: 1px 1px 1px;"
					cellspacing="1" id="results">
					<tr class="loaded">
						<!--<td  width="8%">
									<%i++; %><%=i %>
								</td>-->
						<td width="15%">
							<s:property value="rollNumber" />
						</td>
						<td>
							<s:property value="firstName" /> 
						</td>
						<td width="15%">
							<s:property value="phoneNumber" />
						</td>
						<td width="15%">
							<s:property value="termName" />
						</td>
						<td width="10%">
							<s:property value="dueDateStr" />
						</td>
						<td width="10%">
							<s:property value="feeAmount" />0
						</td>
						<td width="10%">
							<s:property value="paymentAmount" />0
						</td>
					</tr>
				</table>
			</s:iterator>
		</div>
	</div>
</s:if>
<s:else>
	There are no students fee pending for 0-14 days.
</s:else>
 <script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/subscription/paginator_dev.js">
</script>
 
