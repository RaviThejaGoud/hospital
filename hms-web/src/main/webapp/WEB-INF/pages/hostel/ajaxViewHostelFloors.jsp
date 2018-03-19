<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Hostel Floors
				</div>
			</div>
			<div class="portlet-body">
				<div class="tabbable tabbable-custom tabbable-full-width">
					<ul class="nav nav-tabs">
						<li>
							<s:url id="doAddNewFloorList" action="ajaxDoAddFloorSettings"
								includeParams="all" escapeAmp="false" namespace="/hostel">
								<s:param name="floor.id" value="0" />
							</s:url>
							<sj:a href="%{doAddNewFloorList}"  targets="hostelFloorSettingContentDiv"  
								data-toggle="tab">Add Floor</sj:a>
						</li>
						<li class="active">
							<s:url id="urlManageFloors" action="ajaxLoadManageInfoByStatus"
								namespace="/hostel" includeParams="all" escapeAmp="false">
								<s:param name="anyTitle">floors</s:param>
							</s:url>
							<sj:a href="%{urlManageFloors}" targets="mainContentDiv"
								data-toggle="tab">View Floors</sj:a>
						</li>
					</ul>
					<div id="hostelFloorSettingContentDiv" class="tab-content">
						<div class="form-group form-horizontal">
							<label class="control-label col-md-3"><span class="required">*</span>Select Hostel & Building :</label>
							<div class="col-md-3">
							<s:select list="objectList" listKey="buildingId" theme="simple"
								listValue="hostelNameAndBuildingName"   cssClass="form-control"
								onchange="javascript:getFloorDetailsByBuilding(this.value,'floors');"
								name="tempId2" headerKey="" headerValue="- Select -" >
							</s:select>
							</div>
						</div>
						<div class="form-body">&nbsp;</div>
						<div id="floorsContent">
							<jsp:include page="ajaxViewHostelFloorsByFloorId.jsp"></jsp:include>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
$(document).ready(function() {
	$.subscribe('feeFormValidation', function(event, data) {
		if ($('#addFee').valid()) {
			$('#stepHostel').show();
			return true;
		} else
			return false;
	});
	$.subscribe('doInitAddFee', function(event, data) {
		$('#stepHostel').show();
	});
	$.subscribe('doInitAddFee', function(event, data) {
		$('#stepHostel').show();
	});
});
function getFloorDetailsByBuilding(buldingId, anyTitle) {
	if(isNonEmpty(buldingId)){
		$('#floorsContent').html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		var pars = "tempId2=" + buldingId + "&anyTitle=" + anyTitle;
		$.ajax( {
			url : jQuery.url.getChatURL("/hostel/ajaxLoadManageInfoByFloors.do"),
			cache : false,
			data : pars,
			success : function(response) {
				$('#floorsContent').html(response);
			}
		});
	}else
		$('#floorsContent').html('<div class="thb" style="margin-top:10px;">Please select Hostel & Building.</div>');
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
		$('#roomsContent').html('<div class="thb" style="margin-top:10px;">Please select Building & Floor.</div>');
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
		$('#bedsContent').html('<div class="thb" style="margin-top:10px;">Please select Floor & Room.</div>');
} 
</script>