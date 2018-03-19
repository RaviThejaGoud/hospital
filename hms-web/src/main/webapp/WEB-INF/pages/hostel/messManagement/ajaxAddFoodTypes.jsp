<%@ include file="/common/taglibs.jsp"%>
<div class="grid_14 commomnTabs">
	<s:form id="addFoodType" action="ajaxSaveFoodTypes" method="post" theme="css_xhtml" namespace="/hostel">
		<s:hidden name="academicYearId" value="%{academicYearId}" cssClass='academicYearId' />
		<s:hidden name="type" value="%{'MessFoodTypes'}" />
		<s:hidden name="tempId1" />
		<s:hidden name="tempId2" />
		<h1>
			Create Food Types
		</h1>
		<fieldset>
			<div class="grid_13">
				<div class="grid_6">
					<div class="grid_6">
						<label style="width: 250px;">
							<span class="required">*</span>Select Hostel & Building Name:
						</label>
					</div>
					<div class="grid_6">
						<s:select list="objectList" listKey="buildingId"
							listValue="hostelNameAndBuildingName" disabled="true"
							cssClass="required textfield" theme="css_xhtml" required="true"
							id="buldingId" name="tempId2" requiredposition="first">
						</s:select>
					</div>
				</div>
			</div>
				<div class="grid_7">
					<sj:textfield name="foodTypes.foodTypeName" id="foodName"
						label="Food Type Name" required="true" tabindex="1"
						cssClass="required textfield small" maxlength="60"></sj:textfield>
				</div>
			<div class="grid_9">
				<span class="hintMessage" style="top: 0px;">like
					Veg,Non-Veg.... </span>
			</div>
			<div class="grid_12">
				<s:url id="doAddFoodList" action="ajaxLoadMessInfoByStatus"
					includeParams="all" escapeAmp="false" namespace="/hostel">
				</s:url>
				<sj:a href="%{doAddFoodList}" cssClass="cancelButton"
					indicator="indicator" targets="viewMessSteps">Cancel</sj:a>
					
				<sj:submit   cssClass="submit small" value="Submit" validate="true" 
					onClickTopics="addFoodValidation" indicator="indicator"
					targets="viewMessSteps" />
			</div>
		</fieldset>
	</s:form>
</div>
<script type="text/javascript">
$(document).ready(function() {
		changePageTitle("Create Food Types");
});
$.subscribe('addFoodValidation', function(event, data) {
	if ($('#addFoodType').valid())
		return true;
	else
		return false;
});
</script>