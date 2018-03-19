<%@ include file="/common/taglibs.jsp"%>
<div class="grid_11">
	<s:form action="ajaxAddStaffRoles" theme="css_xhtml" id="editStaffRole1" method="post" namespace="/staff">
		<s:hidden name="selectedId" />
		<s:if test="%{!objectList != null && !objectList.isEmpty()}">
			<div class="grid_11">
				<div class="grid_3" style="text-align: Left;">
					<b>Eligible Roles:</b>
				</div>
				<div class="grid_11">
					<s:checkboxlist name="chkBoxSelectedIds" cssClass="checkbox small required" required="true" 
						list="objectList" listKey="id" listValue="description" cssStyle="width:330px;" />
				</div>
			</div>
		</s:if>
		<s:else>
			<div class="tabBorder">
				Currently there are no roles.
			</div>
		</s:else>
		<div class="grid_4" >
			<sj:submit   cssClass="submit" value="Submit" targets="staffList"
				onClickTopics="staffRoleEditFormValidation" formIds="editStaffRole1" />
			<s:url id="doCancelStaff"  action="ajaxDocancelStaff"
				includeParams="all" namespace="/admin"></s:url>
			<sj:a href="%{doCancelStaff}" cssClass="cancelButton"  onCompleteTopics="doInitRoleEditStaff1" 
				indicator="indicator" targets="editRoleStaffForm%{selectedId}" button="false">Cancel</sj:a>
		</div>
	</s:form>
 <script language="JavaScript" type="text/javascript">
changePageTitle("Edit Staff Role Details");
	$(document).ready(function() {
		$.subscribe('staffRoleEditFormValidation', function(event, data) {
			if ($('#editStaffRole1').valid()){
			var selectedChkCount = $("input[name=chkBoxSelectedIds]:checked").length;
			if(selectedChkCount > 0 )
					return true;
				else{
				    alert("Please select Role.");
					return false;
				}
		}
		else
			return false;
		});
	});
</script>

