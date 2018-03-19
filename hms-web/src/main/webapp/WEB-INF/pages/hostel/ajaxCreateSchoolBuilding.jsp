<%@ include file="/common/taglibs.jsp"%>
<s:if test="%{tempId2 != 0}">
	<div data-width="760" class="modal fade modal-overflow in" style="display: block; width: 852px; margin-left: -379px;margin-top: 100px;">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true"></button>
			<h4 class="modal-title">
				Update Building
			</h4>
		</div>
		<div class="modal-body">
	</s:if>
	<div class="form-body">
	<s:form action="ajaxCreateSchoolBuilding" theme="simple"
		id="updateBuildingtypes" method="post" cssClass="form-horizontal" namespace="/hostel">
		<s:hidden name="tempId2" />
		<s:hidden name="anyTitle"></s:hidden>
			<div class="panel-body col-md-12">
				<div class="col-md-1" style="width: 4.333%;">
					<span class="label label-danger"> NOTE : </span>
				</div>
				<div class="col-md-10">
					<ul>
						<li>
							option while creating the building avoids you to select the gender
							again in floor creation.
						</li>
						<li>
							If no gender option is selected in building creation, then you
							have to select the gender option in floor creation process.
						</li>
					</ul>
				</div>
			</div>
		<div class="row">
			<div class="col-md-6">
				<s:if test="%{hostelList!= null && !hostelList.isEmpty()}">
					<div class="form-group">
						<label class="control-label col-md-5">
							Select Hostel :
						</label>
						<div class="col-md-5">
							<s:if test="%{tempId2 == 0}">
								<s:select list="hostelList" label="Select Hostel" listKey="id"
									listValue="hostelName" name="hostel.id" headerKey=""
									cssClass="form-control" headerValue="- Select -">
								</s:select>
							</s:if>
							<s:else>
								<s:select list="hostelList" label="Select Hostel" listKey="id"
									listValue="hostelName" name="hostel.id" headerKey=""
									disabled="true" cssClass="form-control"
									headerValue="- Select -">
								</s:select>
							</s:else>
							<s:else>
								<div class="alert alert-info">
									Hostels are not available. Please create hostels.
								</div>
							</s:else>
						</div>
					</div>
				</s:if>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-5">
						<span class="required">*</span> Building Name :
					</label>
					<div class="col-md-5">
						<sj:textfield name="building.buildingName" id="buildingName"
							cssClass="form-control  required" maxlength="40"></sj:textfield>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-5">
						<span class="required">*</span> Building Short Name :
					</label>
					<div class="col-md-5">
						<sj:textfield name="building.buildingShortName"
							id="buildingShortName" cssClass="required form-control"
							maxlength="10"></sj:textfield>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-5">
						<span class="required">*</span>Contact Number :
					</label>
					<div class="col-md-5">
						<sj:textfield name="building.contactNumber" id="contactNumber"
							cssClass="form-control  required numeric" maxlength="14"
							onkeypress="return formatMobileNumber(this,event);"></sj:textfield>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-5">
							<span class="required">*</span> Street :
						</label>
						<div class="col-md-5">
							<sj:textfield name="address.streetName" id="streetName"
								cssClass="form-control required" maxlength="255"></sj:textfield>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-5">
							<span class="required">*</span>City :
						</label>
						<div class="col-md-5">
							<sj:textfield name="address.city" id="city"
								cssClass="form-control required" maxlength="20"></sj:textfield>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-5">
							State :
						</label>
						<div class="col-md-5">
							<s:select id="state" list="statesList" cssClass="form-control"
								listKey="stateCode" listValue="stateName" headerKey=""
								headerValue="- Select -" name="address.state" />
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-5">
							<span class="required">*</span>Pincode :
						</label>
						<div class="col-md-5">
							<sj:textfield name="address.postalCode" id="pincode"
								cssClass="numeric form-control required" maxlength="6"></sj:textfield>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<s:if test="%{tempId2 == 0}">
						<div class="form-group">
							<label class="control-label col-md-5">
								No.of Floors :
							</label>
							<div class="col-md-5">
								<sj:textfield name="numberOfDays" id="noOfFloors"
									onchange="javascript:showFloorName(this);"
									cssClass="numeric form-control" maxlength="2"></sj:textfield>
							</div>
						</div>
					</s:if>
					<s:if test="%{tempId2 == 0}">
						<div class="grid_6" style="display: none" id="floorNameDiv">
							<div class="form-group">
								<label class="control-label col-md-5">
									Floor Name :
								</label>
								<div class="col-md-5">
									<sj:textfield name="floor.floorName" id=""
										cssClass="form-control" maxlength="42"></sj:textfield>
								</div>
							</div>
						</div>
					</s:if>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-5">
							<span class="required">*</span>Gender :
						</label>
						<div class="col-md-5">
							<div class="make-switch has-switch" data-id="M" data-value="F"
								style="width: 120px" data-off="warning" data-on="success"
								data-off-label="Female" data-on-label="Male">
								<input type="radio" class="toggle" checked="checked" id="gender">
								<input type="hidden" name="building.gender" value="M">
							</div>
						</div>
					</div>
				</div>
			</div>
		<div class="form-actions fluid">
			<div class="col-md-offset-3 col-md-9">
				<sj:submit   cssClass="submitBt btn blue" value="Submit"
					onBeforeTopics="formValidation" validate="true"
					formIds="updateBuildingtypes" indicator="indicator"
					targets="hostelSettingContent" />
					<s:if test="%{tempId2 != 0}">
						<button type="button" data-dismiss="modal" class="btn default">Cancel</button>
					</s:if>
					<s:else>
						<s:url id="doAddNewBuildingList"
							action="ajaxViewSchoolBuildingSettings" includeParams="all"
							escapeAmp="false" namespace="/hostel">
							<s:param name="academicYearId" value="%{academicYearId}" />
						</s:url>
						<sj:a href="%{doAddNewBuildingList}" cssClass="btn default"
							 targets="mainContentDiv">Cancel</sj:a>
					</s:else>
			</div>
		</div>
	</s:form>
	</div>
	<s:if test="%{tempId2 != 0}">
	</div>
	</div>
</s:if>
<script language="JavaScript" type="text/javascript"
	src="${pageContext.request.contextPath}/plugins/bootstrap-switch/static/js/bootstrap-switch.min.js">
</script>
<script type="text/javascript">
$(document).ready(function() {
	FormComponents.init();
	FormAdvanced.init();
	changePageTitle("Add Building Settings");
	$.subscribe('formValidation', function(event, data) {
		var floors = Number($('#noOfFloors').val());
		if (isNonEmpty(floors)) {
			if (floors <= 0) {
				alert("No.of Floors should be more than 0.");
				event.originalEvent.options.submit=false;
			} else{
				$('button.close').click();
			}
		} else
			return true;
	});
	$('.numeric').numeric( {
		allow : "0-9"
	});
});
function showFloorName(eve) {
	if (eve.value > 0) {
		$('#floorNameDiv').show();
	} else
		$('#floorNameDiv').hide();
}
</script>