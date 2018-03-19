<%@ include file="/common/taglibs.jsp"%>
<jsp:include page="/common/messages.jsp"></jsp:include>
<div id="deletePerContentDiv">
<h4 class="pageTitle bold">Pending Permissions</h4>
<s:if test="%{tempList != null && !tempList.isEmpty()}">
	<table class="table table-striped table-bordered table-hover table-full-width" id="sample_2">
		<thead>
			<tr>
			    <th>Staff Name</th>
				<th>Permission Date</th>
				<th>Start Time</th>
				<th>End Time</th>
				<th>Status</th>
				<s:if test='%{user.isSchoolPrincipal=="Y" || user.isSchoolDirector == "Y"}'>
					<th>Action</th>
				</s:if>
				<th>Comments</th>
				<s:if test='%{user.isOnlySchoolAdmin == "N" && user.isSchoolPrincipal=="N" && user.isSchoolDirector == "N"}'>
					<th>Delete</th>
				</s:if>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="tempList">
				<tr>
					<td><s:property value="fullName" /></td>
					<td><s:property value="formattedStartDate" /></td>
					<td><s:property value="startTime" /></td>
					<td><s:property value="endTime" /></td>
					<td><span class="label label-sm label-info"> Pending </span></td>
					<s:if test='%{user.isSchoolPrincipal=="Y" || user.isSchoolDirector == "Y"}'>
						<td>
							<a data-toggle="modal" href="#DatePermissionsDiv" onclick="javascript:checkPermissionsDetails(<s:property value="staffId" />,'<s:property value="permissionDateStr" />',<s:property value="permissionRequestId" />);">Action</a>
						</td>
					</s:if>
				<td><s:property value="comments" /></td> 
				<s:if test='%{user.isOnlySchoolAdmin == "N" && user.isSchoolPrincipal=="N" && user.isSchoolDirector == "N"}'>
				 <td>
					<s:if test='%{attendanceDate.compareTo(permissionDateStr) < 0}'>
						<s:if test='%{#session.previousYear=="N"}'>
							<s:url id="removeRequest" action="ajaxDeleteStaffPermissionRequests" includeParams="all" escapeAmp="false" namespace="/staff">
								<s:param name="selectedId" value="%{permissionRequestId}"></s:param>
							</s:url>
							<s:div cssClass="btn btn-xs red" id='%{removeRequest}' theme="simple" title="Delete this Event" onclick="javascript:confirmDialogWithTarget(this,'permissionsDiv');">
								<i class="fa fa-times"></i>Delete</s:div>
						</s:if>
					</s:if>
				 </td>
				 </s:if>
				</tr>
			</s:iterator>
		</tbody>
	</table>
</s:if>
<s:else>
	<div class="alert alert-info">No Pending Permissions.</div>
</s:else>
</div>
<div>
	<h4 class="pageTitle bold">Approved Permissions</h4>
	<s:if test="%{tempList1 != null && !tempList1.isEmpty()}">
		<table
			class="table table-striped table-bordered table-hover table-full-width"
			id="sample_3">
			<thead>
				<tr>
				<th>Staff Name</th>
				<th>Permission Date</th>
				<th>Start Date</th>
				<th>End Date</th>
				<th>Status</th>
				<th>Comments</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="tempList1">
					<tr>
					<td><s:property value="fullName" /></td>
					<td><s:property value="formattedStartDate" /></td>
					<td><s:property value="startTime" /></td>
					<td><s:property value="endTime" /></td>
					<td><span class="label label-success"> Approved </span></td>
					<td><s:property value="approvalsComment" /></td> 
				</tr>
				</s:iterator>
			</tbody>
		</table>
	</s:if>
	<s:else>
		<div class="alert alert-info">No Approved Permissions.</div>
	</s:else>
</div>
<div>
	<h4 class="pageTitle bold">Rejected Permissions</h4>
	<s:if test="%{tempList2 != null && !tempList2.isEmpty()}">
		<table
			class="table table-striped table-bordered table-hover table-full-width"
			id="sample_4">
			<thead>
				<tr>
					<th>Staff Name</th>
				<th>Permission Date</th>
				<th>Start Date</th>
				<th>End Date</th>
				<th>Status</th>
				<th>Comments</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="tempList2">
					<tr>
					<td><s:property value="fullName" /></td>
					<td><s:property value="formattedStartDate" /></td>
					<td><s:property value="startTime" /></td>
					<td><s:property value="endTime" /></td>
					<td><span class="label label-danger"> Rejected </span></td>
					<td><s:property value="approvalsComment" /></td> 
				</tr>
				</s:iterator>
			</tbody>
		</table>
	</s:if>
	<s:else>
		<div class="alert alert-info">No Rejected Permissions.</div>
	</s:else>
</div>
<div id="DatePermissionsDiv"></div>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/onload.js">
</script>
<script type="text/javascript">
$(document).ready(function() {
	TableAdvanced.init();
});	
function checkPermissionsDetails(staffId,permissionDate,permissionRequestId){
	if(isNonEmpty(staffId)){
	  var pars = "tempId1="+staffId+"&anyId="+permissionDate+"&tempId2="+permissionRequestId;
	  //alert(pars);
        $.ajax( {
	        url : jQuery.url.getChatURL("/staff/ajaxDoPermisssionApproveOrReject.do"),
			cache : true,
			data : pars,
			success : function(html) {
			$("#DatePermissionsDiv").html(html);
			}
		});
	}
}
</script>

