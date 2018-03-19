<%@ include file="/common/taglibs.jsp"%>
<s:if test="%{floor.id != 0}">
	<div data-width="760" class="modal fade modal-overflow in" style="display: block; width: 800px; margin-left: -379px;margin-top: 100px;">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true"></button>
			<h4 class="modal-title">
				Update Floor
			</h4>
		</div>
		<div class="modal-body">
	</s:if>
	<div class="form-body">
	<jsp:include page="/common/messages.jsp" />
	<s:form action="ajaxCreateSchoolFloors" theme="simple"
		id="updateFloortypes" method="post" namespace="/hostel" cssClass="form-horizontal">
		<s:hidden name="floor.id" id="floorId" />
		<s:hidden name="room.id" />
		<s:hidden name="anyTitle"></s:hidden>
		<div class="form-group">
			<label class="control-label col-md-3">
				<span class="required">*</span>Select Hostel & Building :
			</label>
			<div class="col-md-3">
				<s:select list="objectList" listKey="hostelIdAndBuildingId"
					onchange="getBuildingList(this.value);"
					listValue="hostelNameAndBuildingName" tabindex="1"
					cssClass="required form-control" label="" name="tempString"
					headerKey="" id="buildingId" headerValue="- Select -">
				</s:select>
			</div>
		</div>
		<s:if test="%{floor.id != 0}">
			<div class="form-group">
				<label class="control-label col-md-3">
					Current Rooms :
				</label>
				<div class="col-md-3">
					<p class="form-control-static"><s:property value="quizId"/></p>
				</div>
			</div>
		</s:if>
		<div class="form-group">
			<label class="control-label col-md-3">
				<s:if test="%{floor.id != 0}">
					<span class="required">*</span>Extra Rooms :
				</s:if>
				<s:else>
					<span class="required">*</span>No.of Rooms :
				</s:else>
				
			</label>
			<div class="col-md-3">
				<sj:textfield name="numberOfDays" id="noOfRoomsInEachFloor"
					  onchange="javascript:showRoomName(this);"
					cssClass="numeric form-control  " maxlength="3"></sj:textfield>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-md-3">
				<span class="required">*</span>Floor Name :
			</label>
			<div class="col-md-3">
				<sj:textfield name="floor.floorName" label=""
					cssClass="form-control required" maxlength="42"></sj:textfield>
			</div>
		</div>
		<s:if test="%{floor.id == 0}">
			<div class="grid_6" style="display: none" id="roomNameDiv">
				<div class="form-group">
					<label class="control-label col-md-3">
						<span class="required">*</span>Room Name :
					</label>
					<div class="col-md-3">
						<sj:textfield name="room.roomName" id="noOfRoomsInEachFloor"
							cssClass="form-control required" maxlength="20"  ></sj:textfield>

					</div>
				</div>
			</div>
		</s:if>
		<s:else>
			<div class="grid_6">
				<div class="form-group">
					<label class="control-label col-md-3">
						<span class="required">*</span>Room Name :
					</label>
					<div class="col-md-3">
						<sj:textfield name="room.roomName" 
							cssClass="form-control required" maxlength="20"></sj:textfield>
					</div>
				</div>
			</div>
			</s:else>
		<div id="genderCont">
		</div>
		<div class="form-actions fluid">
			<div class="col-md-offset-3 col-md-6">
				<sj:submit   cssClass="submitBt btn blue" value="Submit" onBeforeTopics="formValidation"
					validate="true" indicator="indicator" targets="mainContentDiv"
					formIds="updateFloortypes" />
				<s:if test="%{floor.id != 0}">
					<button type="button" data-dismiss="modal" class="btn default">
						Cancel
					</button>
				</s:if>
				<s:else>
					<s:url id="doAddNewFloorList" action="ajaxLoadManageInfoByStatus"
						includeParams="all" escapeAmp="false" namespace="/hostel">
						<s:param name="academicYearId" value="%{academicYearId}" />
					</s:url>
					<sj:a href="%{doAddNewFloorList}" cssClass="btn default"
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
<script type="text/javascript">
$(document).ready(function() {
var buildingId=$('#buildingId').val();
getBuildingList(buildingId);

var floarId = '<s:property value="floor.id" />';
if(floarId > 0)
{
	$('#roomNameDiv').show();
}
	changePageTitle("Add Floor");
	$.subscribe('formValidation', function(event, data) {
		var rooms = Number($('#noOfRooms').val());
		if (rooms <= 0) {
			alert("No.of Rooms should be more than 0.");
			event.originalEvent.options.submit=false;
		} else
			$('button.close').click();
	});
 
$('.blockHeader h2').html('Manage Floor');
	
	$('.numeric').numeric( {
		allow : "0-9"
	});
});
function showRoomName(eve) {
	if (eve.value > 0) {
		$('#roomNameDiv').show();
	} else
		$('#roomNameDiv').hide();
}
function getBuildingList(buildingId) {
	var floorId=$('#floorId').val();
	var pars = "tempString=" + buildingId+"&anyId="+floorId;
	var url = jQuery.url.getChatURL("/hostel/ajaxDoGetBuildingList.do");
	$('#genderCont').html('<div align="center" style="margin: 50px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
	$.ajax( {
		url : url,
		cache : false,
		data : pars,
		success : function(response) {
		$('#genderCont').html(response);
	 }
	});
}
</script>