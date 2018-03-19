<%@ include file="/common/taglibs.jsp"%>
	<s:if test="%{objectList != null && !objectList.isEmpty()}">
		<table
			class="table table-striped table-bordered table-hover table-full-width"
			id="sample_3">
			<thead>
				<tr>
					<th>
						Book Number
					</th>
					<th>
						Book Name
					</th>
					<th>
						Staff ID
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
				<s:iterator value="objectList">
					<tr>
						<td>
							<s:property value="lableCode" />
						</td>
						<td>
							<s:property value="bookName" />
						</td>
						<td>
							<s:property value="username" />
						</td>
						<td>
							<s:if test="%{subjectId > 0}">
								<s:property value="name" />
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
		Currently there are no issued books.
	</div>
</s:else>
<script type="text/javascript">
	TableAdvanced.init();
</script>