<%@ include file="/common/taglibs.jsp"%>
	<s:if test="%{tempList != null && !tempList.isEmpty()}">
	<table class="table table-striped table-bordered table-hover table-full-width"
				id="sample_editable_3">
			<thead>
				<tr>
					<th>
						Book Number
					</th>
					<th>
						Book Name
					</th>
					<th>
						Admission Number
					</th>
					<th>
						Subject
					</th>
					<th>
						Issued Date
					</th>
					<th>
						Due Date
					</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="tempList">
					<tr>
						<td>
							<s:property value="lableCode" />
						</td>
						<td>
							<s:property value="bookName" />
						</td>
						<td>
							<s:property value="admissionNumber" />
						</td>
						<td>
							<s:if test="%{name != null}">
								<s:property value="name" /> &nbsp;
										</s:if>
							<s:else>
								<s:property value="otherSubjects" />
							</s:else>
						</td>
						<td>
							<s:property value="issuedDateStr" />
						</td>
						<td>
							<s:property value="dueDateStr" />
						</td>
					</tr>
				</s:iterator>
			</tbody>
		</table>
	</s:if>
	<s:else>
		<div class="alert alert-info">
		Currently there are no issued books for selected class.
		</div>
	</s:else>
<script type="text/javascript">
	TableEditable.init();
</script>
