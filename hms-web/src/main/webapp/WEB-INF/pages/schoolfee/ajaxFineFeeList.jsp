<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/messages.jsp"%>
	<s:if test="%{objectList != null && !objectList.isEmpty()}">
	<h4>
		Other fee collection history
	</h4>
		<table
			class="table table-striped table-bordered table-hover table-full-width"
			id="sample_4">
			<thead>
				<tr>
					<th>
						Invoice Number
					</th>
					<th>
						Payment Date
					</th>
					<th>
						Amount
					</th>
					<!-- <th>
						Description
					</th> -->
				</tr>
			</thead>
			<tbody>
				<s:iterator value="objectList">
					<s:set var="studentDetailsId" value="studId"></s:set>
					<tr>
						<td>
							<s:property value="invoiceNumber" />
						</td>
						<td>
							<s:property value="dateOfPaymentStr" />
						</td>
						<td>
							<s:property value="fineFeeAmount" />
						</td>
						<%-- <td>
							<s:property value="description" />
						</td> --%>
					</tr>
				</s:iterator>
			</tbody>
		</table>
	</s:if>
	<s:else>
		<div class="alert alert-info">
			This student do not have any invoice list.
		</div>
	</s:else>
<%-- <form method="post" id="printFineFeeReport"
	action="javaScript:printStudentFineFeeInvoice('<s:property value="anyId" />','<s:property value="tempId2" />','<s:property value="empId" />')"
	style="display: none;">
</form> --%>

<script type="text/javascript">
var invoiceNumber = '<s:property value="tempId" />';
$(".invoiceNumber").val(invoiceNumber);
$("#invoiceMaxVal").val(invoiceNumber);
</script>