<%@ include file="/common/taglibs.jsp"%>
<!--<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>
<div class="form-body">
	<s:form id="searchStaffName" action="ajaxSearchStaffByName"
		theme="simple" namespace="/staff" cssClass="form-horizontal">
		<div class="form-group">
			<label class="col-md-3 control-label">
				Search Staff :
			</label>
			<div class="col-md-4">
				<div class="input-group">
					<sj:textfield name="anyTitle" id="staffRole"
						value="Staff First or Last Name"
						cssClass="form-control defaultValue required"></sj:textfield>
					<span class="input-group-btn"> <sj:submit  
							targets="editStaffResultsDiv" value="Find Staff"
							cssClass="btn blue" onBeforeTopics="searchStaffForm"
							formIds="searchStaffName" resetForm="true" /> </span>
				</div>
				<span class="hintMessage">(Key at least 3 chars and hit
					submit to get closer match.)</span>
			</div>
		</div>
	</s:form>
</div>
--><div id="editStaffResultsDiv">
	<jsp:include
		page="/WEB-INF/pages/staff/manageStaff/ajaxViewEditStaffDetails.jsp" />
</div>
<script type="text/javascript">
$.subscribe('searchStaffForm', function(event, data) {
			var staffRole = $('#staffRole').val();
			if (staffRole == null || staffRole == '' || staffRole == 'Staff First or Last Name') {
				alert("Please enter first name or last name.");
				event.originalEvent.options.submit=false;
			} else if (staffRole.length < 3) {
				alert("Please enter minimum 3 characters.");
				$('#staffRole').val('Staff First or Last Name');
				event.originalEvent.options.submit=false;
			}  
			return true;
});
</script>
 