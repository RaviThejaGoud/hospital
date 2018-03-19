<%@ include file="/common/taglibs.jsp"%>
 <s:if test="%{studentPaymentList != null && !studentPaymentList.isEmpty()}">
	<div class="row">
		<div class="col-md-12">
				<div class="col-md-6">&nbsp; </div>
				<div class="col-md-6">	Total Amount&nbsp;:&nbsp;<b><s:property value="totalAmount" /></b></div>
		</div>
	</div>	
	<table
		class="table table-striped table-bordered table-hover table-full-width" id="sample_2">
		<thead>
			<tr>
				<th>
					Paid Date
				</th>
				<th>
					Invoice Number
				</th>
				<th>
					Student Name
				</th>
				<th>
					Class
				</th>
				<th>
					Admission Number
				</th>
				<th>
					Paid Amount
				</th>
				<th>
					Discount Amount
				</th>
				<!-- <th>
					Due Date
				</th> -->
			</tr>
		</thead>
		<tbody>
			<s:iterator value="studentPaymentList" status="stat">
				<tr>
					<td>
						<s:property value="paymentDateStr" />
					</td>	
					<td>
						<s:property value="invoiceNumberStr" />
					</td>				
					<td>
						<s:property value="fullPersonName" />
					</td>
					<td>
						<s:property value="classAndSection" />
					</td>
					<td>
						<s:property value="admissionNumber" />
					</td>
					<td>
						<s:property value="paidAmount" />
					</td>
					<td>
						<s:property value="discountAmount" />
					</td>
					<%-- <td>
						<s:property value="studentPaymentList[#stat.count-1][6]" />
					</td> --%>
				</tr>
			</s:iterator>
		</tbody>
	</table>
</s:if>

<s:if test="%{classFeeCountList != null && !classFeeCountList.isEmpty()}">
	<div class="row">
		<div class="col-md-12">
				<div class="col-md-6"><h4>Other Fee Details</h4> </div>
				<div class="col-md-6">Total Other Fee&nbsp;:&nbsp; <b><s:property value="thirtyTotalAmount" /></b></div>
		</div>
	</div>
	<table class="table table-striped table-bordered table-hover table-full-width" id="sample_3">
		<thead>
			<tr>
				<th>
					Paid Date
				</th>
				<th>
					Invoice Number
				</th>
				<th>
					Student Name
				</th>
				<th>
					Class
				</th>
				<th>
					Admission Number
				</th>
				<th>
					Paid Amount
				</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="classFeeCountList" status="stat">
				<tr>
					<td>
						<s:property value="classFeeCountList[#stat.count-1][0]" />
					</td>	
					<td>
						<s:property value="classFeeCountList[#stat.count-1][5]" />
					</td>				
					<td>
						<s:property value="classFeeCountList[#stat.count-1][1]" />
					</td>
					<td>
						<s:property value="classFeeCountList[#stat.count-1][2]" />
					</td>
					<td>
						<s:property value="classFeeCountList[#stat.count-1][3]" />
					</td>
					<td>
						<s:property value="classFeeCountList[#stat.count-1][4]" />
					</td>
					
					
				</tr>
			</s:iterator>
		</tbody>
	</table>
</s:if>
<s:if test="%{(studentPaymentList == null || studentPaymentList.isEmpty()) && (classFeeCountList == null || classFeeCountList.isEmpty())}">
	<div class="alert alert-info">
		Currently there are no fee collections for selected date.
	</div>
</s:if>
<script type="text/javascript">
	$(document).ready(function(){
		changePageTitle("Day Wise Fee Collections");
		TableAdvanced.init();
	});
</script>
