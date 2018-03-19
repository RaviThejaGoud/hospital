<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>
		<%@ include file="/common/messages.jsp"%>
<s:if test="%{objectList != null && !objectList.isEmpty()}">
	<table
		class="table table-striped table-bordered table-hover table-full-width"
		id="sample_2">
		<thead>
			<tr>
				<th>
					Admission Number
				</th>
				<th>
					Student Name
				</th>
				<th>
					Transfer School
				</th>
			</tr>
		</thead>
		<tbody>
			<s:set name="studyClassId" value=""></s:set>
			<s:iterator value="objectList">
				<tr>
					<td>
						<s:property value="account.admissionNumber" />
					</td>
					<td>
						<s:property value="account.person.firstName" />
						&nbsp;
						<s:property value="account.person.lastName" />
					</td>
					<td>
						<s:property value="newCustomer.organization" />
					</td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
</s:if>
<s:else>
	<div class="alert alert-info">
		There is no transferred student from this school.
	</div>
</s:else>
<script type="text/javascript">
TableAdvanced.init();
</script>
