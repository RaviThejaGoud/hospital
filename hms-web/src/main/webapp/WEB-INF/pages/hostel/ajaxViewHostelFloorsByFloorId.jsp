<%@ include file="/common/taglibs.jsp"%>
<div id="floorsContentDiv">
<%@ include file="/common/messages.jsp"%>
<s:if
	test="%{building.floorList!= null && !building.floorList.isEmpty()}">
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
					</td>
					<td>
						<s:property value="roomsCount" />
					</td>
					<td>
						<s:if test='%{#session.previousYear == "N"}'>
							<a data-toggle="modal"  href="#editFloorSettingsDiv"  class="btn btn-xs purple"
								onclick="javascript:PopupEditFloorSettings('<s:property value="%{hostelIdAndBuildingId}" />',<s:property value="%{id}" />);"><i class="fa fa-edit"></i>Edit
							</a>
						</s:if>
					</td>
					<td>
						<s:url id="removeFloor" action="ajaxDeleteFloor"
							includeParams="all" escapeAmp="false" namespace="/hostel">
							<s:param name="floor.id" value="id"></s:param>
						</s:url>
						<s:div cssClass="btn btn-xs red"
							id='%{removeFloor}' theme="simple"
							onclick="javascript:confirmDialogWithTarget(this,'floorsContentDiv');"
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
<div id="editFloorSettingsDiv"></div>
</div>
<script type="text/javascript">
$(document).ready(function() {
TableAdvanced.init();
	$.subscribe('feeFormValidation', function(event, data) {
		if ($('#addFee').valid()) {
			$('#stepHostel').show();
			return true;
		} else
			event.originalEvent.options.submit=false;
	});
	$.subscribe('doInitAddFee', function(event, data) {
		$('#stepHostel').show();
	});
	$.subscribe('doInitAddFee', function(event, data) {
		$('#stepHostel').show();
	});
});
function getFloorDetailsByBuilding(buldingId, anyTitle) {
	if (isNonEmpty(buldingId)) {
		$('#floorsContent')
				.html(
						'<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		var pars = "tempId2=" + buldingId + "&anyTitle=" + anyTitle;
		$.ajax( {
			url : jQuery.url
					.getChatURL("/hostel/ajaxLoadManageInfoByFloors.do"),
			cache : false,
			data : pars,
			success : function(response) {
				$('#floorsContent').html(response);
			}
		});
	} else
		$('#floorsContent')
				.html(
						'<div class="thb" style="margin-top:10px;">Please select Hostel & Building.</div>');
}
function getRoomsDetailsByFloor(id, type) {
	if (isNonEmpty(id)) {
		$('#hostelSettingContent')
				.html(
						'<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		var pars = "tempId1=" + id + "&anyTitle=" + type;
		$
				.ajax( {
					url : jQuery.url
							.getChatURL("/hostel/ajaxLoadManageInfoByRooms.do"),
					cache : false,
					data : pars,
					success : function(response) {
						$('#hostelSettingContent').html(response);
					}
				});
	} else
		$('#roomsContent')
				.html(
						'<div class="thb" style="margin-top:10px;">Please select Building & Floor.</div>');
}
function getBedDetailsByRoom(id, type) {
	if (isNonEmpty(id)) {
		$('#hostelSettingContent')
				.html(
						'<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		var pars = "tempId1=" + id + "&anyTitle=" + type;
		$.ajax( {
			url : jQuery.url.getChatURL("/hostel/ajaxLoadManageInfoByBeds.do"),
			cache : false,
			data : pars,
			success : function(response) {
				$('#hostelSettingContent').html(response);
			}
		});
	} else
		$('#bedsContent')
				.html(
						'<div class="thb" style="margin-top:10px;">Please select Floor & Room.</div>');
}

function PopupEditFloorSettings(tempString,id) {
	var url = jQuery.url.getChatURL("/hostel/ajaxDoAddFloorSettings.do");
	$('#editFloorSettingsDiv').html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
	$.ajax( {
			url : url,
			cache : false,
			data : "tempString=" + tempString+"&floor.id="+id,
			success : function(html) {
				$("#editFloorSettingsDiv").html(html);
			}
		});
	}
</script>