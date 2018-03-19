<%@ include file="/common/taglibs.jsp"%>
<div class="form-body">
	<%@ include file="/common/messages.jsp"%>
	<s:form id="selectStudentForm" cssClass="form-horizontal"
		theme="simple">
		<s:if test="%{objectList != null && !objectList.isEmpty()}">
			<table
				class="table table-striped table-bordered table-hover table-full-width"
				id="sample_2">
				<thead>
					<tr><th style="display: none">Created Date</th>
					<th>Ticket Number</th>
					<th style="word-wrap: break-word;min-width: 260px;max-width: 300px;white-space:normal;">Description</th>
						<th>Reported By</th>
						<th>Created Date</th>
						<th>Assigned To</th>
						<th>Exp Completion Date</th>
						<th>Status</th>
					</tr>
				</thead>
				<tbody>
					<!--<s:set var="roleName" value=""/> -->
					<s:iterator value="objectList" status="stat">
						<tr>
								<td style="display: none"><s:property value="issueDate" /></td>
								<td><s:property value="refNumber" /></td>
								<td style="word-wrap: break-word;min-width: 260px;max-width: 300px;white-space:normal;"><s:property value="issue" /></td>
								<td><s:property value="reportedBy" /></td>
								<td><s:property value="issueDate" /></td>
								<td><s:property value="assignedTo" /></td>
								<td><s:property value="expCompletionDate" /></td>
								<td><s:property value="status" /></td>
						</tr>
					</s:iterator>
				</tbody>
			</table>
		</s:if>
		<s:else>
			<div class="alert alert-info">Currently there are no tickets.</div>
		</s:else>
	</s:form>
</div>
<div id="inactiveStaffDiv"></div>
<div id="showStaffProfileDiv"></div>
<script type="text/javascript">
	$(document).ready(function(){
	$('').addClass('active');
	$('ul.nav-tabs li').removeClass('active');
	$('a#internalSoftwareTicketDetails').parent('li').addClass('active');
	TableAdvanced.init();
	})

 	
</script>