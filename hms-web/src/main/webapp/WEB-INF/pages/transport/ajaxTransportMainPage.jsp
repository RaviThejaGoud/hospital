<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/ems.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/common/jquery.autoCheck.js"></script>
<div class="block grid_12">
	<div class="block_head">
		<h2>
			Vehicles
		</h2>
		<ul>
		<s:url id="doAddVehicles" action="ajaxDoAddVehicles"
					includeParams="all" escapeAmp="false" namespace="/admin">
				</s:url>
			<li>
				<s:url id="doAddVehicles" action="ajaxDoAddVehicles"
					includeParams="all" escapeAmp="false" namespace="/admin">
				</s:url>
				<sj:a href="%{doAddVehicles}" onCompleteTopics="doInitEditVehicle"
					indicator="indicator" targets="addTransport%{id}" 
					buttonIcon="ui-icon-plus">
						Add Vehicle
				</sj:a>
			</li>
		</ul>
	</div>
	<div class="block_content" id="transportContent">
		<jsp:include page="/WEB-INF/pages/transport/ajaxManageTransport.jsp"/>
	</div>
</div>

