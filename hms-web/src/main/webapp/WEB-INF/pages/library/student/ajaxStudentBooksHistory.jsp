<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>
<div>
	<s:if test="%{objectList != null && !objectList.isEmpty()}">
		<table class="table table-striped table-bordered table-hover table-full-width" id="sample_editable_3">
			<thead>
				<tr>
					<th>
						Book Number
					</th>
					<th>
						Book Name
					</th>
					<th>
						Author Name
					</th>
					<th>
						Publisher
					</th>
					<th>
						Subject
					</th>
					<th>
						Issued Date
					</th>
					<th>
						Expiry Date
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
							<s:property value="author" />
						</td>
						<td>
							<s:property value="publisher" />
						</td>
						<td>
							<s:if test="%{name!= null}">
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
			Currently there are no taken books.
		</div>
	</s:else>
</div>
<script type="text/javascript">
$(document).ready(function() {
	changePageTitle("Staff Booked History");
	TableEditable.init();
});
</script>