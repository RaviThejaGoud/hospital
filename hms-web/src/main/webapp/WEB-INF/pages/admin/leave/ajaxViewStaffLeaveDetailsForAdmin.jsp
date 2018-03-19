<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/messages.jsp"%>
<s:if
	test="%{viewStaffPersonAccountDetailsList != null && !viewStaffPersonAccountDetailsList.isEmpty()}">
	<p>
		<span class="label label-danger"> NOTE : </span>&nbsp;Today absentees
		are highlighted in red color.
	</p>
	<s:if test="%{(smsAlloted == 0) || (smsAlloted <= smsCnt)}">
		<div style="color: red;" class="alert alert-info">
			You have been used all your allotted
			<s:property value="smsAlloted" />
			SMS. Please contact EazySchool support team (080-46620999) to recharge your SMS.
		</div>
	</s:if>
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
					Earned Leaves
					<br />
					Total (Used)
				</th>
				<th>
					Loss of Pay leaves
				</th>
				<th>
					Leaves Summary
					<br />
					Total (Used)
				</th>
				<th>
					Add Leave
				</th>

			</tr>
		</thead>
		<tbody>
			<s:iterator value="viewStaffPersonAccountDetailsList">
				<tr>
					<td>
						<s:if test="%{present}">
							<font style="color: #FF0000;"><s:property
									value="personFirstLastNameOnly" />
							</font>
						</s:if>
						<s:else>
							<s:property value="personFirstLastNameOnly" />
						</s:else>
					</td>
					<td>
						<s:if test="%{present}">
							<font style="color: #FF0000;"><s:property
									value="roleDescription" />
							</font>
						</s:if>
						<s:else>
							<s:property value="roleDescription" />
						</s:else>
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
						<s:if
							test="%{leaveManagement.earnedLeaves != null && leaveManagement.earnedLeaves != empty}">
							<s:property value="leaveManagement.earnedLeaves" />
						</s:if>
						(
						<s:property value="earnedLeavesCount" />
						)
					</td>
					<td>
						<s:property value="payLeavesCount" />
					</td>
					<td>
						<s:if
							test="%{(leaveManagement.sickLeaves != null && leaveManagement.sickLeaves != empty) && (leaveManagement.casualLeaves != null && leaveManagement.casualLeaves != empty)}">
							<s:property
								value="(leaveManagement.sickLeaves + leaveManagement.casualLeaves + leaveManagement.earnedLeaves)" />
						</s:if>
						(
						<s:property value="(sickLeavesCount + casualLeavesCount + earnedLeavesCount)" />
						)
					</td>
					<td>
						<a data-toggle="modal" href="#popupApplyleaveDiv"
							onclick="javascript:popupApplyleave(<s:property value="accountId" />);">Add
							Leave </a>
					</td>
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
<div id="popupApplyleaveDiv"></div>
<script type="text/javascript">
changePageTitle('Staff Leave Details');
TableAdvanced.init();
function popupApplyleave(accountId) {
	var url = jQuery.url.getChatURL("/admin/ajaxDoApplyStaffLeave.do");
	$('#popupApplyleaveDiv')
			.html(
					'<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
	$.ajax( {
		url : url,
		cache : false,
		data : "tempId=" + accountId,
		success : function(html) {
			$("#popupApplyleaveDiv").html(html);
		}
	});
}
</script>
