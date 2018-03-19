<%@ include file="/common/taglibs.jsp"%>
<s:if
	test="%{classStudentsList != null && !classStudentsList.isEmpty()}">
	<table
		class="table table-striped table-bordered table-hover table-full-width"
		id="sample_2">
		<thead>
			<tr>
				<th>
					Student Image
				</th>
				<th>
					Student No
				</th>
				<th>
					Student Name
				</th>
				<th>
					Date Of Birth
				</th>
				<th>
					Parent Mobile
				</th>
				<th>
					Parent Name
				</th>
				<th>
					Parent E-Mail
				</th>
				<th>
					Date Of Join
				</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="classStudentsList">
				<tr>
					<td>
						<img src='<c:url value="${adjustedAttachmentFilePath}"/>'
							alt='<s:property  value="viewStudentPersonAccountDetails.personFullName" />'
							align="left" height="25px" width="50px" border="0" />
					</td>
					<td>
						<s:property value="rollNumber" />
					</td>
					<td>
						<s:property value="firstName" />
						&nbsp;
						<s:property value="lastName" />
					</td>
					<td>
						<s:property value="dateOfBirthStr" />
					</td>
					<td>
						<s:property value="mobileNumber" />
					</td>
					<td>
						<s:property value="fatherName" />
					</td>
					<td>
						<s:property value="parentEmail" />
					</td>
					<td>
						<s:property value="dateOfJoiningStr" />
					</td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
</s:if>
<s:else>
	<div class="alert alert-info">
		Currently there are no Students.
	</div>
</s:else>
<script type="text/javascript">
TableAdvanced.init();
</script>
