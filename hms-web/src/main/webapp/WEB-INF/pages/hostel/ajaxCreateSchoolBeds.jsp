<%@ include file="/common/taglibs.jsp"%>
<div  class="grid_10" id="steps">
	<jsp:include page="/common/messages.jsp" />
	<s:form action="ajaxCreateSchoolBeds" theme="css_xhtml" id="updateBedtypes" method="post" namespace="/hostel">
		<s:hidden name="bed.id" />
		<s:hidden name="tempId1"/>
		<s:hidden name="anyTitle"></s:hidden>
		<h1>
			<s:if test="%{bed.id != 0}">
				Update Beds
			</s:if>
			<s:else>
				Create Bed
			</s:else>
		</h1>
		<fieldset>
			<div class="grid_10">
				<div class="grid_5 left">
					<div class="grid_4">
					<sj:textfield name="bed.bedCost" id="bedCost" tabindex="2" label="Each Bed Cost" 
								cssClass="numeric textfield" maxlength="5"></sj:textfield>
					</div>		
				</div>
				<div class="grid_5 left">
					<div class="grid_4">
						<sj:textfield name="bed.bedName" id="bedName" required="true"
							label="Bed Name" tabindex="1" cssClass="textfield required"
							maxlength="40"></sj:textfield>
					</div>
				</div>
			</div>
			<div class="grid_10">
				<s:url id="doAddNewBedList" action="ajaxLoadManageInfoByRooms"
					includeParams="all" escapeAmp="false" namespace="/hostel">
					<s:param name="academicYearId" value="%{academicYearId}" />
				</s:url>
				<sj:a href="%{doAddNewBedList}" cssClass="cancelButton"
					indicator="indicator" targets="hostelSettingContent">Cancel</sj:a>
				<sj:submit   cssClass="submit small" value="Submit" validate="true"
					indicator="indicator" targets="hostelSettingContent" resetForm="true"/>
			</div>
		</fieldset>
	</s:form>
</div>
<script type="text/javascript">
$(document).ready(function() {
	changePageTitle("Add Bed");
	$('.blockHeader h2').html('Manage Academics');
	function onlyNumbers(evt) {
		var e = evt; // for trans-browser compatibility	
		var charCode = e.which || e.keyCode;
		if (charCode > 31 && (charCode < 48 || charCode > 57))
			return false;
		return true;
	}
	$('.numeric').numeric( {
		allow : "0-9"
	});
});
</script>