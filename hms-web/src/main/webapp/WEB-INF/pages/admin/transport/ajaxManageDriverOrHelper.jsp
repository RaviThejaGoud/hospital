<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Manage Driver / Helper
				</div>
			</div>
			<span id='respectedDriverOrHelper' style="display: none;"><s:property
					value='anyTitle' />
			</span>
			<div class="portlet-body">
				<div class="tabbable tabbable-custom tabbable-full-width">
					<ul class="nav nav-tabs">
						<li class="dropdown">
							<a data-hover="dropdown" data-toggle="dropdown"
								class="dropdown-toggle js-activated" href="#"> View Helper
								<b class="caret"></b> </a>
							<ul role="menu" class="dropdown-menu pull-right">
								<li id="roleHelper">
									<s:url id="doManageHelpers"
										action="ajaxLoadDriverOrHelperByRoleName" namespace="/admin"
										includeParams="all" escapeAmp="false">
										<s:param name="anyTitleName">ROLE_HELPER</s:param>
									</s:url>
									<sj:a href="%{doManageHelpers}"
										targets="transportDriverOrHelper"
										cssClass='type=type=ROLE_HELPER' type="helperInfo"
										data-toggle="tab">View Helpers</sj:a>
								</li>
								<s:if test='%{#session.previousYear == "N"}'>
									<li id="roleHelper">
										<s:url id="doAddHelper"
											action="ajaxDoAddVehicleDriverOrHelper" includeParams="all"
											escapeAmp="false" namespace="/admin">
											<s:param name="tempString">helper</s:param>
										</s:url>
										<sj:a href="%{doAddHelper}" indicator="indicator"
											targets="transportDriverOrHelper" data-toggle="tab">
											Add Helper
										</sj:a>
									</li>
								</s:if>
							</ul>
						</li>
						<li class="dropdown active">
							<a data-hover="dropdown" data-toggle="dropdown"
								class="dropdown-toggle js-activated" href="#"> Manage Driver
								<b class="caret"></b> </a>
							<ul role="menu" class="dropdown-menu pull-right">
								<li class="active" id="roleDriverActive">
									<s:url id="doManageDrivers"
										action="ajaxLoadDriverOrHelperByRoleName" namespace="/admin"
										includeParams="all" escapeAmp="false">
										<s:param name="anyTitleName">ROLE_DRIVER</s:param>
									</s:url>
									<sj:a href="%{doManageDrivers}"
										targets="transportDriverOrHelper" cssClass='type=ROLE_DRIVER'
										type="driverInfo" data-toggle="tab">View Drivers</sj:a>
								</li>
								<li id="roleDriver">
									<s:url id="doAddDrivers"
										action="ajaxDoAddVehicleDriverOrHelper" namespace="/admin"
										includeParams="all" escapeAmp="false">
										<s:param name="tempString">driver</s:param>
									</s:url>
									<sj:a href="%{doAddDrivers}" targets="transportDriverOrHelper"
										data-toggle="tab">Add Driver</sj:a>
								</li>
							</ul>
						</li>
					</ul>
					<div class="tab-content" id="transportDriverOrHelper">
						<jsp:include
							page="/WEB-INF/pages/admin/transport/ajaxManageDriverDetails.jsp" />
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function() {
	changePageTitle("Manage Driver/Helper");
	$('.js-activated').dropdownHover().dropdown();
});
</script>
