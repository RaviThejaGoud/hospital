<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/messages.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>
<h4 class="pageTitle bold">
	Pending  Appointments
</h4>
<s:if test="%{tempList != null && !tempList.isEmpty()}">
<table class="table table-striped table-bordered table-hover table-full-width" id="sample_2">
		<thead>
			<tr>
				<th>
					Subject
				</th>
				<th>
					Schedule Date (MM/DD/YYYY)
				</th>
				<th>
					Appointment To
				</th>
				<th>
					Status
				</th>
				<th>
					Description
				</th>
				<th>
					Edit
				</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="tempList">
				<tr>
					<td>
						<s:property value="subject" />
					</td>
					<td>
						<s:property value="scheduleDateWithTimeStr" />
					</td>
					<td>
						<s:property value="personNameWithRoleDesc" />
					</td>
					<td> 
					<span class="label label-sm label-info"> Pending </span>
					</td>
					<td>
						<s:property value="description"/>
					</td>
					<td>
						<s:if test='%{status == "P"}'>
							<s:url id="doOrgCust" action="ajaxOnlineAppointment"
								includeParams="all" escapeAmp="false" namespace="/student">
								<s:param name="tempId" value="%{appointmentId}" />
							</s:url>
							<sj:a href="%{doOrgCust}" indicator="indicator"
								targets="contentDiv" title="Edit" button="false"
								cssClass="btn btn-xs purple">
								<i class="fa fa-edit"></i>Edit
							</sj:a>
						</s:if>
						<s:else>
						--
						</s:else>
						
					</td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
</s:if>
<s:else>
	<div class="alert alert-info">Currently there are no pending appointments.</div>
</s:else>
<h4 class="pageTitle bold">
	Approved  Appointments
</h4>
<s:if test="%{tempList1 != null && !tempList1.isEmpty()}">
<table class="table table-striped table-bordered table-hover table-full-width" id="sample_3">
		<thead>
			<tr>
				<th>
					Subject
				</th>
				<th>
					Schedule Date (MM/DD/YYYY)
				</th>
				<th>
					Appointment To
				</th>
				<th>
					Status
				</th>
				<th>
					Description
				</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="tempList1">
				<tr>
					<td>
						<s:property value="subject" />
					</td>
					<td>
						<s:property value="scheduleDateWithTimeStr" />
					</td>
					<td>
						<s:property value="personNameWithRoleDesc" />
					</td>
					<td> <span class="label label-sm label-success"> Approved </span>
					</td>
					<td>
						<s:property value="apporveDescription"/>
					</td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
</s:if>
<s:else>
	<div class="alert alert-info">Currently there are no accepted appointments.</div>
</s:else>
<h4 class="pageTitle bold">
	Rejected  Appointments
</h4>
<s:if test="%{tempList2 != null && !tempList2.isEmpty()}">
<table class="table table-striped table-bordered table-hover table-full-width" id="sample_4">
		<thead>
			<tr>
				<th>
					Subject
				</th>
				<th>
					Schedule Date (MM/DD/YYYY)
				</th>
				<th>
					Appointment To
				</th>
				<th>
					Status
				</th>
				<th>
					Reason for Reject
				</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="tempList2">
				<tr>
					<td>
						<s:property value="subject" />
					</td>
					<td>
						<s:property value="scheduleDateWithTimeStr" />
					</td>
					<td>
						<s:property value="personNameWithRoleDesc" />
					</td>
					<td> <span class="label label-sm label-danger"> Rejected </span>
					</td>
					<td>
					<s:if test='%{status == "R"}'>
						<s:property value="apporveDescription"/>
					</s:if>
					</td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
</s:if>
<s:else>
	<div class="alert alert-info">Currently there are no rejected  appointments.</div>
</s:else>

<script type="text/javascript">
$(document).ready(function() {
	TableAdvanced.init();
});
</script>