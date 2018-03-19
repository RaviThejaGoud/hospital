<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/messages.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i> Deleted Invoices
				</div>
			</div>
			<div class="portlet-body">
				<div class="tabbable tabbable-custom tabbable-full-width">
					<div id="contentDiv" class="tab-content">
						<s:if test="%{objectList != null && !objectList.isEmpty()}">
							<table
								class="table table-striped table-bordered table-hover table-full-width"
								id="sample_2">
								<thead>
									<tr>
										<th>S.No</th>
										<th>Admission No</th>
										<th>Student Name</th>
										<th>Class Name</th>
										<th>Invoice No</th>
										<th>Invoice Date</th>
										<th>Amount</th>
										<th>Request Person</th>
										<th>Remarks</th>
										<th>Deleted Date</th>
									</tr>
								</thead>
								<tbody>
									<%
									int i = 0;
									%>
									<s:iterator value="objectList">
										<tr>
											<td><%i++;%><%=i%></td>
											<td><s:property value="admissionNumber" /></td>
											<td><s:property value="studentFullName" /></td>
											<td><s:property value="classNameAndSection" /></td>
											<td><s:property value="invoiceNumber" /></td>
											<td><s:property value="paymentDateStr" /></td>
											<td><s:property value="paidAmount" /></td>
											<td><s:property value="reportedPerson"/></td>
											<td><s:property value="deleteRemarks" /></td>
											<td><s:property value="deleteDateStr"/></td>
										</tr>
									</s:iterator>
								</tbody>
							</table>
						</s:if>
						<s:else>
							<div class="alert alert-info">Currently there are no deleted invoices.</div>
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
</script>



