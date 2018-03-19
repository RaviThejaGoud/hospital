<%@ include file="/common/taglibs.jsp"%>
<div id="steps13">
<fieldset id="stepAttendance" class="step13"> 
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/ems.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/group/paginator10.js"></script>
<div style="padding-top: 1px" id="editClassSubjects">
	<s:if test="%{tempList1 != null && !tempList1.isEmpty()}">
		<div class="teacherAccountDetails">
				<!--<a href="#"> Teachers List(<s:property value="viewTeacherAccountDetails.size()" />)</a>
		--></div>
		<div class="grid_12 th">
				<div class="grid_2">
						Image
					</div>
					<div class="grid_3">
						Name
					</div>
					<div class="grid_2">
						Emp No
					</div>
					<div class="grid_1">
						DOJ
					</div>
					<div class="grid_2">
						Mobile 
					</div>
					<div class="grid_1">
						Email 
					</div>
					<div class="grid_1">
						Edit
					</div>
					<div class="grid_1">
						Role
					</div></div>
			<div id="resultsPage">
			<s:iterator value="tempList1">
			<div class="grid_12 row">
				<div class='loaded'>
					<div class="grid_2">
						<img src='<c:url value="${adjustedAttachmentFilePath}"/>'
							alt='<s:property  value="account.person.personFullName" />'
							align="left" height="25px" width="50px" border="0" />
						</div>
						<div class="grid_3">
						<s:property value="account.person.personFullName" /> 
				</div>
						<div class="grid_2">
						<s:property value="account.username" />
				</div>
						<div class="grid_1">
						<s:property value="account.person.dateOfJoining" />
					</div>
						<div class="grid_2">
						<s:property value="account.person.mobileNumber" />
					</div>
						<div class="grid_1">
							<s:url id="editStaff" action="ajaxDoSendEmailToStaff" includeParams="all"
								escapeAmp="false" namespace="/admin">
								<s:param name="id" value="{account.id}" />
								<s:param name="parentEmail" value="{account.username}" />
							</s:url>
							<sj:a href="%{editStaff}" targets="editStaffForm%{id}"
								onCompleteTopics="doInitEditStaff" indicator="indicator"
								onBeforeTopics="cleanOpenRows">
								<img src="../images/email.jpeg" />
							</sj:a>
						</div>
						<div class="grid_1">
						<s:url id="editStaff" action="ajaxDoEditStaffDetails" includeParams="all"
							escapeAmp="false" namespace="/admin">
							<s:param name="staffId" value="id" />
						</s:url>
						<sj:a href="%{editStaff}" targets="editStaffForm%{id}" onCompleteTopics="doInitEditStaff"
							indicator="indicator" onBeforeTopics="cleanOpenRows"  cssClass="normalEdit" title="Edit">
						</sj:a>
						</div>
						<div class="grid_1">
						<s:url id="editRoleStaff" action="ajaxDoEditStaffRoleDetails" includeParams="all"
							escapeAmp="false" namespace="/admin">
							<s:param name="userId" value="account.id" />
						</s:url>
						<sj:a href="%{editRoleStaff}" targets="editStaffRoleForm%{id}" onCompleteTopics="doInitEditStaffRole1"
							indicator="indicator" onBeforeTopics="cleanOpenRows"  cssClass="normalEdit" title="Edit">
						</sj:a>
					</div>
				</div>
						<div  id="editStaffForm<s:property value='id' />" style="display: none;" 	class='load'> </div>
				<div id="editStaffRoleForm<s:property value='id' />" style="display: none;" 	class='load'> </div>
			</div>
			</s:iterator>
		</div>
	</s:if>
	<s:else>
		 Teachers (<s:property value="tempList1.size()" />)
</s:else>
</div>
</fieldset>
</div>
<style type="text/css">
.active {
	color: #0033CC;
	text-decoration: none;
}

.inactive {
	font-weight: bold;
	text-decoration: underline;
	cursor: default;
}

.paginator {
	text-align: center;
	color: #FFF;
}
</style>
<script type="text/javascript">
  $(document).ready(function() {
	$.subscribe('doInitEditStaff', function(event, data) {
	 	if ($('#' + data.id).is(":hidden")) {
			$('#' + data.id).show();
		} else {
			$('#' + data.id).hide();
		 }
		});
		$.subscribe('doInitEditStaffRole1', function(event, data) {
	 	if ($('#' + data.id).is(":hidden")) {
			$('#' + data.id).show();
		} else {
			$('#' + data.id).hide();
		 }
		});
	 });
	 $(function() {
		$("#resultsPage").pagination();
	});			
</script>