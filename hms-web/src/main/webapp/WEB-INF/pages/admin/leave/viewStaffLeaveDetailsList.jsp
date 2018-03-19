<%@ include file="/common/taglibs.jsp"%>
<s:if
	test="%{viewStaffPersonAccountDetailsList != null && !viewStaffPersonAccountDetailsList.isEmpty()}">
	<div class="panel-body col-md-12">
		<div class="col-md-1">
			<span class="label label-danger"> NOTE : </span>
		</div>
		<div class="col-md-10">
			<ul>
				<li>
					With out apply leave today absentees are highlighted in red color.
				</li>
				<li>
					Apply leave today absentees are highlighted in green color.
				</li>
			</ul>
		</div>
		<div class="col-md-1">
		<a href="${pageContext.request.contextPath}/admin/managePrincipal.do"
			class="right"><h4 class="pagetitle bold">Back</h4></a></div>
		</div>						
	<table
		class="table table-striped table-bordered table-hover table-full-width"
		id="sample_2">
		<thead>
			<tr>
				<th>
					Staff Name
				</th>
				<th>
					Role
				</th>
				<th>
					User Name
				</th>
				<th>
					Sick Leaves
					<br />
					Total (Used)
				</th>
				<th>
					Casual Leaves
					<br />
					Total (Used)
				</th>
				<th>
					Loss of Pay leaves
				</th>
				<th>
					Leaves Count
					<br />
					Total (Used)
				</th>

			</tr>
		</thead>
		<tbody>
			<s:iterator value="viewStaffPersonAccountDetailsList">
				<s:if test="%{leaveRequest == 'A'}">
					<font style="color: green">
				</s:if>
				<s:elseif test="%{leaveRequest == 'L'}">
					<font style="color: #FF0000;">
				</s:elseif>
				<tr>
					<td>
						<s:property value="personFirstLastNameOnly" />
					</td>
					<td>
						<s:property value="roleDescription" />
					</td>
					<td>
						<s:property value="username" />
					</td>
					<td>
						<s:if
							test="%{leaveManagement.sickLeaves != null && leaveManagement.sickLeaves != empty}">
							<s:property value="leaveManagement.sickLeaves" />
						</s:if>
						(
						<s:property value="sickLeavesCount" />
						)
					</td>
					<td>
						<s:if
							test="%{leaveManagement.casualLeaves != null && leaveManagement.casualLeaves != empty}">
							<s:property value="leaveManagement.casualLeaves" />
						</s:if>
						(
						<s:property value="casualLeavesCount" />
						)
					</td>
					<td>
						<s:property value="payLeavesCount" />
					</td>

					<td>
						<s:if
							test="%{(leaveManagement.sickLeaves != null && leaveManagement.sickLeaves != empty) && (leaveManagement.casualLeaves != null && leaveManagement.casualLeaves != empty)}">
							<s:property
								value="(leaveManagement.sickLeaves + leaveManagement.casualLeaves)" />
						</s:if>
						(
						<s:property value="(sickLeavesCount + casualLeavesCount)" />
						)
					</td>
					</font>
				</tr>
			</s:iterator>
		</tbody>
	</table>
</s:if>
<s:else>
	<div class="alert alert-info">
		Currently there are no staff leaves.
	</div>
</s:else>

<script type="text/javascript">
changePageTitle('Staff Leave Details');
$(document).ready(function() {
TableAdvanced.init();
});
</script>
 