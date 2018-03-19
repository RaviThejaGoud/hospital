<%@ include file="/common/taglibs.jsp"%>
<div class="block grid_9" id="studentContent">
	<div class="block_head">
		<h2>
			My Fee Details
		</h2>
	</div>
	<div class="block_content" id="studentDetails">
		<s:if test="%{feeStructure!= null && studentPayment!=null}">

			<s:if test='%{studentPayment.paymentType == "F"}'>
				<div class="grid_4 type-text" style="font-weight: bold;">
					Payment Selected :
				</div>
				<div class="grid_3">
					Full Payment
				</div>
				<table class="striped" width="100%" style="margin-bottom: 0;"
					cellpadding="1" cellspacing="1" id="results">
					<thead>
						<tr>
							<th width="" class="head">
								Fee Type
							</th>
							<th width="" class="head">
								Amount
							</th>
							<th width="" class="head">
								Last Payment Date
							</th>
							<th width="" class="head">
								Payment Date
							</th>
							<th width="" class="head">
								Status
							</th>
						</tr>
					</thead>
					<tr class='loaded'>
						<td width="">
							Admission Fee
						</td>
						<td width="">
							<s:property value="feeStructure.admissionFee" />
						</td>
						<td width="">
							<s:property value="feeStructure.admissionDateStr" />
						</td>
						<td width="">
							<s:property value="studentPayment.admisionPayDateStr" />
						</td>
						<td width="">
							<s:if test='%{studentPayment.admisionPayStatus=="P"}'>
							Paid
						</s:if>
							<s:else>
							Not Paid
						</s:else>
						</td>
					</tr>
					<tr class='loaded'>
						<td width="">
							Total Tuition Fee
						</td>
						<td width="">
							<s:property value="feeStructure.totalTermFee" />
						</td>
						<td>
							<s:property value="feeStructure.term1DateStr" />
						</td>
						<td width="">
							<s:property value="studentPayment.term1PayDateStr" />
						</td>
						<td width="">
							<s:if
								test='%{studentPayment.term1PayStatus=="P" || studentPayment.term2PayStatus=="P" || studentPayment.term3PayStatus=="P"}'>
							Paid
						</s:if>
							<s:else>
							Not Paid
						</s:else>
						</td>
					</tr>
					<tr class='loaded'>
						<td width="">
							Examination Fee
						</td>
						<td width="">
							<s:property value="feeStructure.examinationFee" />
						</td>
						<td>
							<s:property value="feeStructure.examDateStr" />
						</td>
						<td>
							<s:property value="studentPayment.examPayDateStr" />
						</td>
						<td width="">
							<s:if test='%{studentPayment.examPayStatus=="P"}'>
							Paid
						</s:if>
							<s:else>
							Not Paid
						</s:else>
						</td>
					</tr>
				</table>
			</s:if>
			<s:elseif test='%{studentPayment.paymentType == "T"}'>
				<div class="grid_4 type-text" style="font-weight: bold;">
					Payment Selected :
				</div>
				<div class="grid_3">
					Term Payment
				</div>
				<table class="striped" width="100%" style="margin-bottom: 0;"
					cellpadding="1" cellspacing="1" id="results">
					<thead>
						<tr>
							<th width="29%" class="head">
								Fee Type
							</th>
							<th width="" class="head">
								Amount
							</th>
							<th width="27%" class="head">
								Last Payment Date
							</th>
							<th width="27%" class="head">
								Payment Date
							</th>
							<th width="" class="head">
								Status
							</th>
						</tr>
					</thead>
					<tr class='loaded'>
						<td width="">
							Admission Fee
						</td>
						<td width="">
							<s:property value="feeStructure.admissionFee" />
						</td>
						<td width="">
							<s:property value="feeStructure.admissionDateStr" />
						</td>
						<td width="">
							<s:property value="studentPayment.admisionPayDateStr" />
						</td>
						<td width="">
							<s:if test='%{studentPayment.admisionPayStatus=="P"}'>
							Paid
						</s:if>
							<s:else>
							Not Paid
						</s:else>
						</td>
					</tr>
					<tr class='loaded'>
						<td width="">
							Term 1 Fee :
						</td>
						<td width="">
							<s:property value="feeStructure.term1Amount" />
						</td>
						<td>
							<s:property value="feeStructure.term1DateStr" />
						</td>
						<td width="">
							<s:property value="studentPayment.term1PayDateStr" />
						</td>
						<td width="">
							<s:if test='%{studentPayment.term1PayStatus=="P"}'>
							Paid
						</s:if>
							<s:else>
							Not Paid
						</s:else>
						</td>
					</tr>
					<tr class='loaded'>
						<td width="">
							Term 2 Fee :
						</td>
						<td width="">
							<s:property value="feeStructure.term2Amount" />
						</td>
						<td>
							<s:property value="feeStructure.term2DateStr" />
						</td>
						<td width="">
							<s:property value="studentPayment.term2PayDateStr" />
						</td>
						<td width="">
							<s:if test='%{studentPayment.term2PayStatus=="P"}'>
							Paid
						</s:if>
							<s:else>
							Not Paid
						</s:else>
						</td>
					</tr>
					<tr class='loaded'>
						<td width="">
							Term 3 Fee :
						</td>
						<td width="">
							<s:property value="feeStructure.term3Amount" />
						</td>
						<td>
							<s:property value="feeStructure.term3DateStr" />
						</td>
						<td width="">
							<s:property value="studentPayment.term3PayDateStr" />
						</td>
						<td width="">
							<s:if test='%{studentPayment.term3PayStatus=="P"}'>
							Paid
						</s:if>
							<s:else>
							Not Paid
						</s:else>
						</td>
					</tr>
					<tr class='loaded'>
						<td width="">
							Examination Fee
						</td>
						<td width="">
							<s:property value="feeStructure.examinationFee" />
						</td>
						<td>
							<s:property value="feeStructure.examDateStr" />
						</td>
						<td>
							<s:property value="studentPayment.examPayDateStr" />
						</td>
						<td width="">
							<s:if test='%{studentPayment.examPayStatus=="P"}'>
							Paid
						</s:if>
							<s:else>
							Not Paid
						</s:else>
						</td>
					</tr>
				</table>
			</s:elseif>

		</s:if>
		<s:else>
			There are no fee details select payment type.
	</s:else>
	</div>
</div>
