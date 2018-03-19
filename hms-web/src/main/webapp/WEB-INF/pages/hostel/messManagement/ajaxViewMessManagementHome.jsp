<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Mess Management
				</div>
			</div>
			<div class="portlet-body">
			<s:if test="%{(hostelList != null && !hostelList.isEmpty())}">
				<div class="tabbable tabbable-custom tabbable-full-width">
					<ul class="nav nav-tabs">
						<%-- <li>
							<s:url id="doAddNewSettingsTypeList"
								action="ajaxDoAddBuildingSettings" includeParams="all"
								namespace="/hostel" escapeAmp="false" >
								<s:param name="tempId2">0</s:param>
							</s:url>
							<sj:a href="%{doAddNewSettingsTypeList}" data-toggle="tab"
								targets="hostelSettingContent">Add Building</sj:a>
						</li> --%>
						
						<li class="dropdown" id="manageActive">
								<a data-hover="dropdown" data-toggle="dropdown"
									class="dropdown-toggle js-activated" href="#">Manage
									Mess Food Types<b class="caret"></b> </a>
								<ul role="menu" class="dropdown-menu pull-right">
									<li>
										<s:url id="urlViewAllMessFoodTypes"
											action="ajaxViewAllMessFoodTypes" includeParams="all"
											namespace="/hostel" escapeAmp="false">
										</s:url>
										<sj:a href="%{urlViewAllMessFoodTypes}"
											targets="messSettingContent" data-toggle="tab">View Mess Food Types</sj:a>
									</li>
									<s:if test="%{objectList!= null && !objectList.isEmpty()}">
									<li>
										<s:url id="urlAddMessFoodTypes"
											action="ajaxDoAddMessFoodTypes" includeParams="all"
											escapeAmp="false" namespace="/hostel">
										</s:url>
										<sj:a href="%{urlAddMessFoodTypes}"
											targets="messSettingContent" data-toggle="tab">Add Mess Food Types</sj:a>
									</li>
									</s:if>
								</ul>
							</li>
						
						<li class="dropdown" id="manageActive">
								<a data-hover="dropdown" data-toggle="dropdown"
									class="dropdown-toggle js-activated" href="#">Manage
									Provision Items<b class="caret"></b> </a>
								<ul role="menu" class="dropdown-menu pull-right">
									<li>
										<s:url id="urlViewAllProvisionItems"
											action="ajaxViewAllProvisionItems" includeParams="all"
											namespace="/hostel" escapeAmp="false">
										</s:url>
										<sj:a href="%{urlViewAllProvisionItems}"
											targets="messSettingContent" data-toggle="tab">Manage Provision Items</sj:a>
									</li>
									<li>
										<s:url id="urlAddProvisionItems"
											action="ajaxDoAddProvisionItems" includeParams="all"
											escapeAmp="false" namespace="/hostel">
										</s:url>
										<sj:a href="%{urlAddProvisionItems}"
											targets="messSettingContent" data-toggle="tab">Add Provision Items</sj:a>
									</li>
								</ul>
							</li>
							
						<li class="dropdown" id="manageActive">
								<a data-hover="dropdown" data-toggle="dropdown"
									class="dropdown-toggle js-activated" href="#">Manage
									Merchant<b class="caret"></b> </a>
								<ul role="menu" class="dropdown-menu pull-right">
									<li>
										<s:url id="urlViewAllMerchant"
											action="ajaxViewAllMerchants" includeParams="all"
											namespace="/hostel" escapeAmp="false">
										</s:url>
										<sj:a href="%{urlViewAllMerchant}"
											targets="messSettingContent" data-toggle="tab">Manage Merchant</sj:a>
									</li>
									<li>
										<s:url id="urlAddMerchant"
											action="ajaxDoAddMerchant" includeParams="all"
											escapeAmp="false" namespace="/hostel">
											<s:param name="merchant.id" value="0" />
										</s:url>
										<sj:a href="%{urlAddMerchant}"
											targets="messSettingContent" data-toggle="tab">Add Merchant</sj:a>
									</li>
								</ul>
							</li>
							
						<li class="dropdown active" id="manageActive">
								<a data-hover="dropdown" data-toggle="dropdown"
									class="dropdown-toggle js-activated" href="#">Manage
									Mess<b class="caret"></b> </a>
								<ul role="menu" class="dropdown-menu pull-right">
									<li class="active">
										<s:url id="urlViewMessManagementHome"
											action="ajaxViewMessManagementHome" includeParams="all"
											namespace="/hostel" escapeAmp="false">
										</s:url>
										<sj:a href="%{urlViewMessManagementHome}"
											targets="mainContentDiv" data-toggle="tab">Manage Mess</sj:a>
									</li>
									<li>
										<s:url id="urlAddMess"
											action="ajaxDoAddMess" includeParams="all"
											escapeAmp="false" namespace="/hostel">
										</s:url>
										<sj:a href="%{urlAddMess}"
											targets="messSettingContent" data-toggle="tab">Add Mess</sj:a>
									</li>
								</ul>
							</li>
					</ul>
					<div id="messSettingContent" class="tab-content">
						<jsp:include
						page="/WEB-INF/pages/hostel/messManagement/ajaxViewMessList.jsp"></jsp:include>
					</div>
				</div>
				</s:if>
				<s:else>
				<div class="alert alert-info">
					Hostels are not available.
				</div>
				</s:else>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function() {
	changePageTitle("Hostel Settings");
	$('.blockHeader h2').html('Manage Hostel');
});

$('html, body').animate({ scrollTop: $(document).height() - $(window).height() }, 10, function() {
	    $(this).animate({ scrollTop: 0 }, 10);
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