<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/messages.jsp"%>
<s:if test="%{buildingList!= null && !buildingList.isEmpty()}">
	<p>
		<span class="label label-danger"> NOTE : </span>&nbsp;You can
		edit/update existing building settings by clicking on edit pen icon in
		each row at right side.
	</p>
	<table
		class="table table-striped table-bordered table-hover table-full-width"
		id="sample_2">
		<thead>
			<tr>
				<th>
					Hostel Name
				</th>
				<th>
					Building Name
				</th>
				<th>
					Building Address
				</th>
				<th>
					Contact Number
				</th>
				<th>
					Edit
				</th>
				<th>
					Delete
				</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="buildingList">
				<tr>
					<td>
						<s:property value="hostelName" />
					</td>
					<td>
						<s:property value="buildingName" />
					</td>
					<td>
						<s:property value="streetName" />
					</td>
					<td>
						<s:property value="contactNumber" />
					</td>
					<td>
						<s:if test='%{#session.previousYear == "N"}'>
							<a data-toggle="modal" href="#editBuildingSettingsDiv"
								class="btn btn-xs purple"
								onclick="javascript:PopupEditBuildingSettings(<s:property value="%{buildingId}" />);"><i
								class="fa fa-edit"></i>Edit </a>
						</s:if>
					</td>
					<td>
						<s:url id="removeBuilding" action="ajaxDeleteBuilding"
							includeParams="all" escapeAmp="false" namespace="/hostel">
							<s:param name="tempId2" value="%{buildingId}" />
						</s:url>
						<s:div cssClass="btn btn-xs red" id='%{removeBuilding}'
							theme="simple"
							onclick="javascript:confirmDialogWithTarget(this,'hostelSettingContent');"
							title="Delete this Building">
							<i class="fa fa-times"></i> Delete</s:div>
					</td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
</s:if>
<s:else>
	<div class="alert alert-info">
		Currently there are no buildings.
	</div>
</s:else>
<s:if test="%{anyTitle == 'floors'}">
	<s:url id="doAddNewFloorList" action="ajaxDoAddFloorSettings"
		includeParams="all" escapeAmp="false" namespace="/hostel">
		<s:param name="floor.id" value="0" />
	</s:url>
	<sj:a href="%{doAddNewFloorList}" indicator="indicator"
		targets="hostelSettingContent" button="false" cssClass="linkRight">Insert Floor</sj:a>

	<h4 class="pageTitle bold">
		Current Floor Details
	</h4>
	<div class="form-group col-md-6">
		<label class="control-label col-md-6">
			<span class="required">*</span>Select Hostel & Building :
		</label>
		<div class="col-md-6">
			<s:select list="objectList" listKey="buildingId" theme="simple"
				listValue="hostelNameAndBuildingName" cssClass="form-control"
				onchange="javascript:getFloorDetailsByBuilding(this.value,'floors');"
				name="tempId2" headerKey="" headerValue="- Select -">
			</s:select>
		</div>
	</div>
	<div class="spaceDiv"></div>
	<br/>
	<div class="col-md-12">
	<p>
		<span class="label label-danger"> NOTE : </span>&nbsp;You can
		edit/update existing floor settings by clicking on edit pen icon in
		each row at right side.
	</p>
	</div>
	<s:if
		test="%{building.floorList!= null && !building.floorList.isEmpty()}">
		<table
			class="table table-striped table-bordered table-hover table-full-width"
			id="sample_3">
			<thead>
				<tr>
					<th>
						Floor Name
					</th>
					<th>
						No Of Rooms
					</th>
					<th>
						Edit
					</th>
					<th>
						Delete
					</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="building.floorList">
					<tr>
						<td>
							<s:property value="floorName" />
							<s:property value="floorLevel" />
						</td>
						<td>
							<s:property value="roomsCount" />
						</td>
						<td>
							<s:url id="doViewFloorList" action="ajaxDoAddFloorSettings"
								includeParams="all" escapeAmp="false" namespace="/hostel">
								<s:param name="tempString" value="%{hostelIdAndBuildingId}" />
								<s:param name="floor.id" value="%{id}" />
							</s:url>
							<sj:a href="%{doViewFloorList}" indicator="indicator"
								targets="hostelSettingContent" cssClass="btn btn-xs purple"
								title="Edit"><i
								class="fa fa-edit"></i>Edit
							</sj:a>
						</td>
						<td>
							<s:url id="removeFloor" action="ajaxDeleteFloor"
								includeParams="all" escapeAmp="false" namespace="/hostel">
								<s:param name="floor.id" value="id"></s:param>
							</s:url>
							<s:div cssStyle="margin-top:3px;" cssClass="btn btn-xs red"
								id='%{removeFloor}' theme="simple"
								onclick="javascript:confirmDialogWithTarget(this,'hostelSettingContent');"
								title="Delete this Floor"><i class="fa fa-times"></i> Delete</s:div>
						</td>
					</tr>
				</s:iterator>
			</tbody>
		</table>
	</s:if>
	<s:else>
		<div class="alert alert-info">
			You have not created floors, Creating floors is simple process and
			system would guide you.
		</div>
	</s:else>
</s:if>
<div id="editBuildingSettingsDiv"></div>
<script type="text/javascript">
$(document).ready(function() {
	TableAdvanced.init();
	UIExtendedModals.init();
});
$.subscribe('feeFormValidation', function(event, data) {
		if ($('#addFee').valid()) {
			$('#stepHostel').show();
			return true;
		} else
			return false;
	});
function getFloorDetailsByBuilding(buldingId, anyTitle) {
	if(isNonEmpty(buldingId)){
		$('#hostelSettingContent').html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		var pars = "tempId2=" + buldingId + "&anyTitle=" + anyTitle;
		$.ajax( {
			url : jQuery.url.getChatURL("/hostel/ajaxLoadManageInfoByFloors.do"),
			cache : false,
			data : pars,
			success : function(response) {
				$('#hostelSettingContent').html(response);
			}
		});
	}else
		$('#floorsContent').html('<div class="alert alert-info">Please select Hostel & Building.</div>');
}
function getRoomsDetailsByFloor(id, type) {
	if(isNonEmpty(id)){
		$('#hostelSettingContent').html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		var pars = "tempId1=" + id + "&anyTitle=" + type;
		$.ajax( {
			url : jQuery.url.getChatURL("/hostel/ajaxLoadManageInfoByRooms.do"),
			cache : false,
			data : pars,
			success : function(response) {
				$('#hostelSettingContent').html(response);
			}
		});
	}else
		$('#roomsContent').html('<div class="alert alert-info">Please select Building & Floor.</div>');
}
function getBedDetailsByRoom(id, type) {
	if(isNonEmpty(id)){
		$('#hostelSettingContent').html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		var pars = "tempId1=" + id + "&anyTitle=" + type;
		$.ajax( {
			url : jQuery.url.getChatURL("/hostel/ajaxLoadManageInfoByBeds.do"),
			cache : false,
			data : pars,
			success : function(response) {
				$('#hostelSettingContent').html(response);
			}
		});
	}else
		$('#bedsContent').html('<div class="alert alert-info">Please select Floor & Room.</div>');
}
function PopupEditBuildingSettings(tempId2) {
	var url = jQuery.url.getChatURL("/hostel/ajaxDoAddBuildingSettings.do");
	$('#editBuildingSettingsDiv').html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
	$.ajax( {
			url : url,
			cache : false,
			data : "tempId2=" + tempId2,
			success : function(html) {
				$("#editBuildingSettingsDiv").html(html);
			}
		});
	}
</script>