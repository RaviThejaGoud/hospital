<%@ include file="/common/taglibs.jsp"%>
<div class="block grid_12">
	<div class="block_head">
		<h2>
			Manage Staff
		</h2>
		<ul>
	     	<li>
				<s:url id="doAddDriver" action="ajaxDoAddVehicleDriver" includeParams="all" escapeAmp="false"/>
				<sj:a href="%{doAddDriver}"  targets="vehicleDriversList">
						Add Driver
				</sj:a>
			</li>
				<li>
				<s:url id="doAddDriver" action="ajaxDoAddVehicleDriver" includeParams="all" escapeAmp="false"/>
				<sj:a href="%{doAddDriver}"  targets="vehicleDriversList">
						Add Helper
				</sj:a>
			</li>
		</ul>
	</div>
	<div class="block_content" id="vehicleDriversList"><!--
		<jsp:include page="/WEB-INF/pages/admin/transport/ajaxViewDriversList.jsp"/>
	--></div>
</div>

