<%@ include file="/common/taglibs.jsp"%>
<jsp:include page="/common/messages.jsp"></jsp:include>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/ems.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/common/jquery.autoCheck.js"></script>
<div >
	<div id="addTransport<s:property value='id' />" style="display: none;"
		style="margin: 0px 0px 0px 3px;display: none;">
	</div>
</div>
<div>
	<s:if test="%{vehicles != null && !vehicles.isEmpty()}">
		<table class="striped" width="100%" cellpadding="1" cellspacing="1">
			<thead>
				<tr>
					<th>
						Vehicle Name
					</th>
					<th>
						Vehicle Number 
					</th>
					<th>
						Seating Capacity
					</th>
					<th>
						 Insurance Number 
					</th>
					<!--<th>
						 Vehicle Image
					</th>
					--><th>
						Edit
					</th>
				</tr>
			</thead>
			<s:iterator value="vehicles">
				<tr class="loaded" id="turnStatusOff<s:property value='id' />" style="vertical-align: top;">
					<td id="removeVehicle<s:property value='id' />">
						<s:url id="removeCampus" action="ajaxRemoveCampus"
							escapeAmp="false">
							<s:param name="id" value="id"></s:param>
						</s:url>
						<s:div cssClass="close emsRemove" id='%{removeCampus}'
							theme="simple" title="Delete this group"></s:div>
						<s:property value="vehicleType" />
					</td>
					<td>
						<s:property value="vehicleNumber" />
					</td>
					<td>
						<s:property value="noOfSeats" />
					</td>
					<td>
						<s:property value="insuranceNumber" />
					</td>
					<!--<td>
						<s:property value="vehicleType" />
					</td>
					
					--><td>
					<a href="#"
					onclick="javascript:getAjaxDeactiveCampus('<s:property value="id"/>','<s:property value="name"/>')">
								<s:property value="activeStatus" /></a>
					</td>
					
					<td style="vertical-align: top;">
						<s:property value="createdBy" />
					</td>
					<td>
						<s:url id="doEditvehicle" action="ajaxDoEditVehicle"
							includeParams="all" escapeAmp="false" namespace="/admin">
							<s:param name="id" value="{id}" />
						</s:url>
						<sj:a href="%{doEditvehicle}" onCompleteTopics="doInitEditVehicle"
							indicator="indicator" targets="editVehicle%{id}"
							onBeforeTopics="cleanOpenRows"  cssClass="normalEdit" title="Edit">
						</sj:a>
					</td>
				</tr>
				<tr id="editVehicle<s:property value='id' />" style="display: none;"
					class='load'>
				</tr>
			</s:iterator>
		</table>
		</s:if>
	<s:else>
		<table class="striped" width="100%" cellpadding="1" cellspacing="1">
			<tr>
				<td>
					There are no Campuses available.
				</td>
			</tr>
		</table>
	</s:else>
</div>

<script type="text/javascript">
	$.subscribe('doInitEditVehicle', function(event, data) {
		if ($('#' + data.id).is(":hidden")) {
			$('#' + data.id).show();
		} else {
			$('#' + data.id).hide();
		}
	});

	$.subscribe('removeVehicle', function(event, data) {
		if ($('#' + data.id).is(":hidden")) {
			alert('heoo: ' + data.id);
			$('#' + data.id).show();
		} else {
			alert('hee: ' + data.id);
			$('#' + data.id).hide();
		}
	});

	$.subscribe('addTransport', function(event, data) {
		if ($('#' + data.id).is(":hidden")) {
			$('#' + data.id).show();
		} else {
			$('#' + data.id).hide();
		}
	});
</script>
