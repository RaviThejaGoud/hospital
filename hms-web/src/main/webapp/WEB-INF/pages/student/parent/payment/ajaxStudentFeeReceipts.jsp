<%@ include file="/common/taglibs.jsp"%>
<s:if test="%{schoolFeeList != null && !schoolFeeList.isEmpty()}">
	<h4 class="pageTitle bold">
		School Fee Receipts
	</h4>
	<s:if test='%{invoiceNumber > 0}'>
		<table
			class="table table-striped table-bordered table-hover table-full-width"
			id="sample_2">
			<thead>
				<tr>
					<th>
						SNo
					</th>
					<th>
						Student Name
					</th>
					<th>
						Paid Date
					</th>
					<th>
						invoiceNumber
					</th>
					<th>
						Action
					</th>
				</tr>
			</thead>
			<tbody>
				<%
					int i = 0;
				%>
				<s:iterator value="schoolFeeList">
					<tr>
						<td>
							<%
								i++;
							%><%=i%>
						</td>
						<td>
							<s:property value="personFullName" />
						</td>
						<td>
							<s:property value="createdDateStr" />
						</td>
						<td>
							<s:property value="invoiceNumber" />
						</td>
						<td>
							<a id="noPrint" style="cursor: pointer;"
								onClick="javascript:printPreview('<s:property value='studentId'/>',
                                            '<s:property value='createdDateStr'/>',
                                            '<s:property value='invoiceNumber'/>')"
								;
                                            target="_new"><img
									src="../images/index.jpg"> <s:property value='termName' />&nbsp;Invoice
								: <s:property value="invoiceNumber" /> </a>
						</td>
					</tr>
				</s:iterator>
			</tbody>
		</table>
	</s:if>
	<s:else>
		<div class="alert alert-info">
			Currently there are no school fee receipts.
		</div>
	</s:else>
</s:if>

<div>
	<s:if
		test="%{hostelFeeTypeList != null && !hostelFeeTypeList.isEmpty()}">
		<h4 class="pageTitle bold">
			Hostel Fee Receipts
		</h4>
		<s:if test='%{invoiceNumber > 0}'>
			<table
				class="table table-striped table-bordered table-hover table-full-width"
				id="sample_2">
				<thead>
					<tr>
						<th>
							SNo
						</th>
						<th>
							Student Name
						</th>
						<th>
							Paid Date
						</th>
						<th>
							invoiceNumber
						</th>
						<th>
							Action
						</th>
					</tr>
				</thead>
				<tbody>
					<%
						int i = 0;
					%>
					<s:iterator value="hostelFeeTypeList">
						<tr>
							<td>
								<%
									i++;
								%><%=i%>
							</td>
							<td>
								<s:property value="username" />
							</td>
							<td>
								<s:property value="personFirstLastNameOnly" />
							</td>
							<td>
								<s:property value="invoiceNumber" />
							</td>
							<td>
								<a id="noPrint"
									onClick="javascript:printPreview('<s:property value='studentId'/>',
                                            '<s:property value='createdDateStr'/>',
                                            '<s:property value='invoiceNumber'/>')"
									;
                                            target="_new"><img
										style="" src="../images/index.jpg"> Invoice : <s:property
										value="invoiceNumber" /> </a>
							</td>
						</tr>
					</s:iterator>
				</tbody>
			</table>
		</s:if>
		<s:else>
			<div class="alert alert-info">
				Currently there are no hostel fee receipts.
			</div>
		</s:else>
	</s:if>
</div>
<div>
	<s:if
		test="%{transportSchoolFeeList != null && !transportSchoolFeeList.isEmpty()}">
		<h4 class="pageTitle bold">
			Transport Fee Receipts
		</h4>
		<s:if test='%{invoiceNumber > 0}'>
			<table
				class="table table-striped table-bordered table-hover table-full-width"
				id="sample_2">
				<thead>
					<tr>
						<th>
							SNo
						</th>
						<th>
							Student Name
						</th>
						<th>
							Paid Date
						</th>
						<th>
							invoiceNumber
						</th>
						<th>
							Action
						</th>
					</tr>
				</thead>
				<tbody>
					<%
						int i = 0;
					%>
					<s:iterator value="transportSchoolFeeList">
						<tr>
							<td>
								<%
									i++;
								%><%=i%>
							</td>
							<td>
								<s:property value="personFullName" />
							</td>
							<td>
								<s:property value="createdDateStr" />
							</td>
							<td>
								<s:property value="invoiceNumber" />
							</td>
							<td>
								<a id="noPrint" style="cursor: pointer;"
									onClick="javascript:printPreview('<s:property value='studentId'/>',
                                            '<s:property value='createdDateStr'/>',
                                            '<s:property value='invoiceNumber'/>')"
									;
                                            target="_new"><img
										src="../images/index.jpg"> <s:property value='termName' />&nbsp;Invoice
									: <s:property value="invoiceNumber" /> </a>
							</td>
						</tr>
					</s:iterator>
				</tbody>
			</table>
		</s:if>
		<s:else>
			<div class="alert alert-info">
				Currently there are no transport fee receipts.
			</div>
		</s:else>
	</s:if>
</div>