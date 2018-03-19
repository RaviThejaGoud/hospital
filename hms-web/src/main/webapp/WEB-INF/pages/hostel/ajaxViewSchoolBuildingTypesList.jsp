<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Hostel Buildings
				</div>
			</div>
			<div class="portlet-body">
				<div class="tabbable tabbable-custom tabbable-full-width">
					<ul class="nav nav-tabs">
						<li>
							<s:url id="doAddNewSettingsTypeList"
								action="ajaxDoAddBuildingSettings" includeParams="all"
								namespace="/hostel" escapeAmp="false" >
								<s:param name="tempId2">0</s:param>
							</s:url>
							<sj:a href="%{doAddNewSettingsTypeList}" data-toggle="tab"
								targets="hostelSettingContent">Add Building</sj:a>
						</li>
						<li class="active">
							<s:url id="manageBuilding"
								action="ajaxViewSchoolBuildingSettings" namespace="/hostel">
							</s:url>
							<sj:a id="manageBuilding" href="%{manageBuilding}"
								targets="mainContentDiv" data-toggle="tab">View Buildings</sj:a>
						</li>
					</ul>
					<div id="hostelSettingContent" class="tab-content">
						<jsp:include
						page="/WEB-INF/pages/hostel/ajaxViewHostelSettingsTypesList.jsp"></jsp:include>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function() {
	changePageTitle("Hostel Settings");
	$('.blockHeader h2').html('Manage Hostel');
	});

function getBuildingView(id) {
	var pars = "anyId=" + id;
	$.ajax( {
		url : jQuery.url.getChatURL("/hostel/ajaxViewBuildingInformation.do"),
		cache : true,
		data : pars,
		success : function(response) {
			$('#buildingsContent').html(response);
		}
	});
}
function getRoomsDetailsByFloor(id, type) {
	var pars = "tempId1=" + id + "&type=" + type;
	$.ajax( {
		url : jQuery.url.getChatURL("/hostel/ajaxLoadManageInfoByStatus.do"),
		cache : false,
		data : pars,
		success : function(response) {
			$('#stepHostel').html(response);
		}
	});
}
</script>