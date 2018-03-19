<%@ include file="/common/taglibs.jsp"%>
<div data-width="760" class="modal fade modal-overflow in"style="display: block; width: 660px; margin-left: -278px; margin-top: 100px;"
aria-hidden="false">
<div class="modal-body">
	<div class="form-body">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true"></button>
			<h4 class="modal-title">
				<b>Fee Summary</b>
			</h4>
		</div>
		<table class="table table-striped table-bordered table-hover table-full-width dataTable" id="sample_2" style="font-size: 15px;">
			<thead>
				<tr>
	
					<th>
						Description
					</th>
					<th>
						Amount
					</th>
				</tr>
			</thead>
			<tbody>
			
			<s:iterator value="schoolFeeList" status="stat">
				<tr>
					<td>Total Amount</td>
					<td>
						<b> <s:property value="schoolFeeList[#stat.count-1][0]" /> </b>
					</td>
				</tr>
				<tr>
					<td>Collected Amount</td>
					<td> <b><s:property
								value="schoolFeeList[#stat.count-1][1]" /></b></td>
				</tr>
				<tr>
					<td>Balance Amount</td>
					<td> <b><s:property
								value="schoolFeeList[#stat.count-1][6]" /></b></td>
				</tr>
				<tr>
					<td>Today Collection</td>
					<td> <b><s:property
								value="schoolFeeList[#stat.count-1][5]" /></b></td>
				</tr>

				<tr>
					<td>Today Other Fee</td>
					<td> <b><s:property
								value="schoolFeeList[#stat.count-1][7]" /></b></td>
				</tr>
				<tr>
					<td>Discount Amount</td>
					<td> <b><s:property
								value="schoolFeeList[#stat.count-1][2]" /></b></td>
				</tr>
				<tr>
					<td>Other Fee Collected</td>
					<td> <b><s:property
								value="schoolFeeList[#stat.count-1][3]" /></b></td>
				</tr>
				<tr>
					<td>Concession Amount</td>
					<td> <b><s:property
								value="schoolFeeList[#stat.count-1][4]" /></b></td>
				</tr>
				<s:if test='%{customer.preferences.feeRefund==true}'>
				<tr>
					<td>Fee Refund Amount</td>
					<td> <b><s:property
								value="schoolFeeList[#stat.count-1][8]" /></b></td>
				</tr>
				</s:if>
				<tr>
					<td>Library Fine Fee</td>
					<td> <b><s:property value="schoolFeeList[#stat.count-1][9]" /></b></td>
				</tr>
			</s:iterator>
				</tbody>
			</table>
		</div>
	</div>
</div>
