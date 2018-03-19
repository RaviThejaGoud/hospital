<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Hostel Rooms
				</div>
			</div>
			<div class="portlet-body">
				<div class="tabbable tabbable-custom tabbable-full-width">
					<ul class="nav nav-tabs">
						<li>
							<s:url id="doAddNewRooms" action="ajaxDoAddRoomSettings"
								includeParams="all" escapeAmp="false" namespace="/hostel">
								<s:param name="academicYearId" value="%{#session.academicYear}" />
								<s:param name="room.id">0</s:param>
							</s:url>
							<sj:a href="%{doAddNewRooms}" indicator="indicator"
								targets="hostelRoomSettingDiv" button="false" data-toggle="tab">Add Room</sj:a>
						</li>
						<li class="active">
							<s:url id="urlManageRooms" action="ajaxLoadRoomsBysStatus"
								namespace="/hostel" includeParams="all" escapeAmp="false">
								<s:param name="anyTitle">rooms</s:param>
							</s:url>
							<sj:a  href="%{urlManageRooms}"
								targets="mainContentDiv" data-toggle="tab">
									View Hostel Rooms</sj:a>
						</li>
					</ul>
					<div class="tab-content" id="hostelRoomSettingDiv">						
						<div class="row">
							<div class="col-md-12">
								<div class="form-group form-horizontal">
									<label class="control-label col-md-3">
										<span class="required">*</span>Select Building & Floor :
									</label>
									<div class="col-md-3">
										<s:select list="objectList" listKey="floorId" theme="simple"
											headerValue="- Select -" headerKey=""
											cssClass="select2_category form-control"
											onchange="javascript:getRoomsDetailsByFloor(this.value,'rooms');"
											listValue="buildingNameAndFloorName" name="tempId1">
										</s:select>
									</div>
								</div>
							</div>
						</div>
						<div class="spaceDiv"></div>
						<div id="roomsContent">
							<jsp:include page="ajaxViewHostelRoomsByRoomId.jsp"></jsp:include>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function() {
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
		$('#roomsContent')
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
						$('#roomsContent').html(response);
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
</script>