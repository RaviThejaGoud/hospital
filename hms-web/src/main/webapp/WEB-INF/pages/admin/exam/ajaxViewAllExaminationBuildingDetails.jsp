<%@ include file="/common/taglibs.jsp"%>
<div class="grid_13">
	<%@ include file="/common/messages.jsp"%>
	<s:if test="%{(objectList != null && !objectList.isEmpty())}">
		<s:form id="allocationDetails" action="ajaxDoGetAllocationRoomDetails"
			method="post" theme="css_xhtml" namespace="/admin">
			<input type="hidden" id="hidInput" name="hidInput" value="" />
			<input type="hidden" id="totalCap" name="totalCap" value="" />
			<fieldset>
				<div class="grid_12">
					<s:if test="%{(tempList1 != null && !tempList1.isEmpty())}">
						<div class="grid_12">
							<div class="grid_3">
								<label>
									Select BuildingName
								</label>
							</div>
							<div class="grid_3">
								<s:select list="tempList1" listKey="buildingId" id="buildingId"
									listValue="buildingName" cssClass=" textfield"
									onchange="getRoomsAndFloorsByBuildingId(this.value,'building')"
									name="floorName" headerKey="" headerValue="- Select -"
									requiredposition="first">
								</s:select>
							</div>
						</div>
						<div class="grid_12">
							<div class="grid_4">
								&nbsp;
							</div>
							(OR)
						</div>
					</s:if>
					<div class="grid_12">
						<div class="grid_3">
							<label>
								Select FloorName
							</label>
						</div>
						<div class="grid_3">

							<s:select list="objectList" listKey="floorId" id="floorId"
								listValue="buildingFloorName" cssClass=" textfield"
								onchange="getRoomsAndFloorsByBuildingId(this.value,'floor')"
								name="floorName" headerKey="" headerValue="- Select -"
								requiredposition="first">
							</s:select>
						</div>
					</div>
					<div class="grid_13 border"></div>
					<div class="grid_9" id="allotedRoomNames"></div>
				</div>
				<div class="grid_12" id="showSubmitbuttons">
					<s:url id="urlViewExamRooms" action="ajaxGetNoOfExaminationRooms"
						includeParams="all" namespace="/admin"></s:url>
					<sj:a href="%{urlViewExamRooms}" targets="roomForExamination"
						cssClass="cancelButton" indicator="indicator">Cancel</sj:a>
					<sj:submit   targets="roomForExamination" value="Submit"
						onClickTopics="doAllocationDetails" cssClass="submit small"
						formIds="allocationDetails" id="urlExamBuildingDetails"
						indicator="indicator" validate="true" />
				</div>
			</fieldset>
		</s:form>
	</s:if>
</div>
<script type="text/javascript">
changePageTitle('Examination Rooms');
$(document).ready(function() {
	$('#showSubmitbuttons').hide();
	$.subscribe('doAllocationDetails', function(event, data) {
		var roomId = '';
		var noOfStudents = '';
		var totalRoomsCap = 0;
		var jsonObj = [];
		$('span.availableRoomsCont').each(function() {
			if ($(this).find('.selectedRooms').attr("checked")) {
				roomId = $(this).find('span.roomId').attr('id');
				noOfStudents = $(this).find('.noOfStudents').val();
				if (isNonEmpty(roomId)) {
					jsonObj.push( {
						"roomId" : roomId,
						"noOfStudents" : noOfStudents
					});
					totalRoomsCap += Number(noOfStudents);
				}
			}
		});
		$("#totalCap").val(totalRoomsCap);
		$("#hidInput").val(JSON.stringify(jsonObj));
		return true;
	});
});
function getRoomsAndFloorsByBuildingId(val, type) {
	var valOfBuildingId = $("#buildingId").val();
	var valOfFloor = $("#floorId").val();
	$('#showSubmitbuttons').show();
	var res = '';
	var req = '';
	if (type == 'building') {
		req = jQuery.url
				.getChatURL("/admin/ajaxGetNoOfExaminationRooms.do?tempId2="
						+ valOfBuildingId);
		$("#floorId").val('');
	} else {
		req = jQuery.url
				.getChatURL("/admin/ajaxGetNoOfExaminationRooms.do?tempId="
						+ valOfFloor);
		$("#buildingId").val('');
	}

	$.ajax( {
				url : req,
				cache : false,
				dataType : 'json',
				success : function(response) {
					if (isNonEmpty(response)) {
						var roomNames = response.objectList;
						res += '<table><div class="grid_9"><div class="grid_3"><label>Room No</label></div><div class="grid_3"><label> Capacity Of Rooms </label></div><div class="grid_3"><label> Select</label></div></div></table>';
						for (i = 0; i < roomNames.length; i++) {
							var nameno = roomNames[i].roomNumber;
							var capacity = roomNames[i].noOfSeets;
							var roomId = roomNames[i].roomId;
							if (!isNonEmpty(roomId)) {
								roomId = roomNames[i].id;
							}
							if (!isNonEmpty(capacity)) {
								capacity = 0;
							}
							if (isNonEmpty(capacity) && isNonEmpty(nameno)) {
								res += '<span class="availableRoomsCont"><span class="roomId" id="'
										+ roomId
										+ '"/> <div class="grid_9"><div class="grid_3"><input type="text" disabled="disabled" value="'
										+ nameno
										+ '" style="width:45px;padding:4px;" id="allocationRoomForExam'
										+ i
										+ '"class="roomName textfield"/></div><div class="grid_3"><input type="text" onclick="onlyNumbers(this.value);" disabled="disabled" maxlength="2" style="width:45px;padding:4px;" id="allocationRoomForExam'
										+ i
										+ '" value="'
										+ capacity
										+ '" class="noOfStudents textfield"/></div><div class="grid_3"><input type="checkbox" id="allocationRoomForExam" class="selectedRooms"/></div></div></spsan>';
							}
						}
						$('#allotedRoomNames').html(res);
					}
				}
			});
}

</script>