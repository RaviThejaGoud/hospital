<%@ include file="/common/taglibs.jsp"%>
<s:if test="%{room.id != 0}">
	<div data-width="760" class="modal fade modal-overflow in"
		style="display: block; width: 760px; margin-left: -379px; margin-top: 100px;">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true"></button>
			<h4 class="modal-title">
				Update Room
			</h4>
		</div>
		<div class="modal-body">
</s:if>
<div class="form-body">
	<jsp:include page="/common/messages.jsp" />
	<s:form action="ajaxCreateSchoolRooms" theme="simple"
		id="updateRoomtypes" method="post" cssClass="form-horizontal" namespace="/hostel">
		<s:hidden name="room.id" value="%{room.id}" />
		<s:hidden name="anyTitle"></s:hidden>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-6">
						<span class="required">*</span>Select Building & Floor :
					</label>
					<div class="col-md-5">
						<s:if test="%{room.id > 0}">
							<s:select list="objectList" listKey="floorId" disabled="true"
								listValue="buildingNameAndFloorName"
								cssClass="select2_category form-control required" name="tempId1"
								headerKey="" headerValue="- Select -">
							</s:select>
						</s:if>
						<s:else>
							<s:select list="objectList" listKey="floorId"
								listValue="buildingNameAndFloorName"
								cssClass="select2_category form-control required" name="tempId1"
								headerKey=""   headerValue="- Select -">
							</s:select>
						</s:else>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-6">
						<span class="required">*</span>Room Type :
					</label>
					<div class="col-md-5">
						<sj:textfield name="room.roomType" id="roomType"
							cssClass="form-control required"  maxlength="15"></sj:textfield>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-6">
						<span class="required">*</span>Room Name :
					</label>
					<div class="col-md-5">
						<sj:textfield name="room.roomName" id="roomName"  
							cssClass="form-control required" maxlength="40"></sj:textfield>
					</div>
				</div>
			</div>
			<s:if test='%{room == null}'>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-6">
							<span class="required">*</span>No.Of Beds :
						</label>
						<div class="col-md-5">
								<sj:textfield name="room.capacity" id="numberOfDays"
									onchange="javascript:showRoomName(this);"  
									cssClass="numeric form-control required" maxlength="2"></sj:textfield>
							
						</div>
					</div>
				</div>
			</s:if>
			<s:else>
				<s:if test='%{room.capacity > 0}'>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-6">
							<span class="required">*</span>Total Beds :
						</label>
						<div class="col-md-5">
						<s:hidden name="room.capacity" value="%{room.capacity}" />
							<sj:textfield name="room.capacity" id="numberOfDays"
								onchange="javascript:showRoomName(this);" disabled="true"
								cssClass="numeric form-control required" maxlength="2"></sj:textfield>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-6">
							<span class="required">*</span>New Beds :
						</label>
						<div class="col-md-5">
								<sj:textfield name="numberOfDays" id="aditiionalBeds" cssClass="numeric form-control" maxlength="2"></sj:textfield>
						</div>
					</div>
				</div>
				</s:if>
				<s:else>
					<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-6">
							<span class="required">*</span>No.Of Beds :
						</label>
						<div class="col-md-5">
								<sj:textfield name="room.capacity" id="numberOfDays"
									onchange="javascript:showRoomName(this);"  
									cssClass="numeric form-control required" maxlength="2"></sj:textfield>
							
						</div>
					</div>
				</div>
				</s:else>
			</s:else>
		</div>
 		<div class="form-actions fluid">
			<div class="col-md-offset-2 col-md-9">
			
					
				<sj:submit cssClass="submitBt btn blue" value="Submit" validate="true" targets="mainContentDiv" formIds="updateRoomtypes" indicator="indicator" onBeforeTopics="formValidation" />
				<s:if test="%{room.id != 0}">
					<button type="button" data-dismiss="modal" class="btn default">
						Cancel
					</button>
				</s:if>
				<s:else>
					<s:url id="doAddNewRoomsList" action="ajaxLoadRoomsBysStatus"
						includeParams="all" escapeAmp="false" namespace="/hostel">
						<s:param name="anyTitle">rooms</s:param>
						<s:param name="academicYearId" value="%{academicYearId}" />
					</s:url>
					<sj:a href="%{doAddNewRoomsList}" cssClass="btn default"
						 targets="mainContentDiv">Cancel</sj:a>
				</s:else>
			</div>
		</div>
	</s:form>
</div>
<s:if test="%{room.id != 0}">
	</div>
	</div>
</s:if>
<script type="text/javascript">
$(document).ready(function() {
	changePageTitle("Add Room");
	$.subscribe('formValidation', function(event, data) {
		var rooms = $('#numberOfDays').val();
		if (isNonEmpty(rooms)) {
			if (Number(rooms) <= 0) {
				alert("No.of Rooms should be more than 0.");
				event.originalEvent.options.submit = false;
			}  
		}
		$('button.close').click();
	});
	$('.numeric').numeric( {
		allow : "0-9"
	});
});
function showRoomName(eve) {
	if (eve.value > 0) {
		$('.roomNameDiv').show();
	} else
		$('.roomNameDiv').hide();
}
</script>