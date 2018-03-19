<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>
<div class="grid_12" id="hideRackSuccess">
	<jsp:include page="/common/messages.jsp"></jsp:include>
</div>
<div class="row">
	<s:url id="doVehicleMaintenance" action="ajaxManageTransportVehicles"
		namespace="/admin" />
	<sj:a href="%{doVehicleMaintenance}" targets="mainContentDiv"
		cssClass="btn default" cssStyle="float:right;margin-right:20px;">
		<i class="fa fa-mail-reply"></i> Back To Vehicle Maintenance</sj:a>
</div>
<div class="spaceDiv"></div>
<s:if
	test="%{transportMaintenanceList != null && !transportMaintenanceList.isEmpty()}">
	<table
		class="table table-striped table-bordered table-hover table-full-width"
		id="sample_2">
		<thead>
			<tr>
				<th>Month Name</th>
				<th>Opening Reading</th>
				<th>Closing Reading</th>
				<th>No. Of Liters Purchased</th>
				<th>Oil Cost</th>
				<th>Edit</th>
				<th>Delete</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="transportMaintenanceList">
				<tr>
					<td><s:property value="monthName" /></td>
					<td><s:property value="openingReading" /></td>
					<td><s:property value="closingReading" /></td>
					<td><s:property value="oilPurchased" /></td>
					<td><s:property value="oilCost" /></td>
					<td><a data-toggle="modal" href="#transportMaintenanceDiv"
						class="btn btn-xs purple"
						onclick="javascript:PopupTransportMaintenance(<s:property value="%{vehicleAcademicDetails.id}" />,<s:property value="%{id}" />);"><i
							class="fa fa-edit"></i>Edit </a></td>
					<td><s:url id="removeTMSMaintanece"
							action="ajaxRemoveTMSMaintanence" includeParams="all"
							escapeAmp="false" namespace="/admin">
							<s:param name="vehicleAcademicDetails.id"
								value="%{vehicleAcademicDetails.id}"></s:param>
							<s:param name="transportMaintenance.id" value="%{id}" />
						</s:url> <s:div cssClass="btn btn-xs red"
							onclick="javascript:confirmDialogWithTarget(this,'transportVehicles');"
							id='%{removeTMSMaintanece}' theme="simple"
							title="Delete this maintenance cost">
							<i class="fa fa-times"></i> Delete
										</s:div></td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
</s:if>
<s:else>
	<div class="alert alert-info">Currently there are no Maintenance
		cost for this transport vehicle.</div>
</s:else>
<div id="transportMaintenanceDiv"></div>

<!--<s:url id="doAddNewPetrolCost" action="ajaxEditPetrolCost"
							includeParams="all" escapeAmp="false">
							<s:param name="vehicleAcademicDetails.id"
								value="%{vehicleAcademicDetails.id}"></s:param>
							<s:param name="transportMaintenance.id" value="%{id}"></s:param>
						</s:url>
						<sj:a href="%{doAddNewPetrolCost}" cssClass="normalEdit btn btn-xs purple"
							title="Edit" onCompleteTopics="doInitAddPetrolCost"
							onBeforeTopics="cleanOpenDivsEdit"
							targets="addPetrolCostEdit%{monthId}" indicator="indicator"><i class="fa fa-edit"></i>Edit
						</sj:a>
					-->
<!--
						<s:if test='%{#session.previousYear == "N"}'>
							<s:url id="removeStudySubject" action="ajaxRemoveSubject"
								includeParams="all" escapeAmp="false">
								<s:param name="studySubject.id" value="%{id}" />
							</s:url>
							<s:div cssClass="btn btn-xs red"
								onclick="javascript:confirmDialogWithTarget(this,'mainContentDiv');"
								id='%{removeStudySubject}' title="Delete this subject"><i class="fa fa-times"></i>Delete
							</s:div>
						</s:if>
					-->

<!--
						<div class="grid_12 th">
							<div class="grid_9">
								<div class="grid_2">
									Month Name
								</div> 
								<div class="grid_2">
									Opening Reading
								</div>
								<div class="grid_2">
									Closing Reading
								</div>
								<div class="grid_2">
									No. Of Liters Purchased
								</div>
								<div class="grid_1">
									Oil Cost
								</div>
							</div>
							<div class="grid_2">
								<div class="grid_1">
									Edit
								</div>
								<div class="grid_1">
									Delete
								</div>
							</div>
						</div>
						<s:iterator value="transportMaintenanceList">
							<div class="grid_12 row">
								<div class="grid_9">
									<div class="grid_2">
										<s:property value="monthName" />
									</div>
									<div class="grid_2">
										<s:property value="openingReading" />
									</div>
									<div class="grid_2">
										<s:property value="closingReading" />
									</div>
									<div class="grid_2">
										<s:property value="oilPurchased" />
									</div>
									<div class="grid_1">
										<s:property value="oilCost" />
									</div>
								</div>
								<div class="grid_2">
									<div class="grid_1">
										<s:url id="doAddNewPetrolCost" action="ajaxEditPetrolCost"
											includeParams="all" escapeAmp="false">
											<s:param name="vehicleAcademicDetails.id" value="%{vehicleAcademicDetails.id}"></s:param>
											<s:param name="transportMaintenance.id" value="%{id}"></s:param></s:url>
										<sj:a href="%{doAddNewPetrolCost}" cssClass="normalEdit"
											title="Edit" onCompleteTopics="doInitAddPetrolCost" 
											 onBeforeTopics="cleanOpenDivsEdit" 
											targets="addPetrolCostEdit%{monthId}" indicator="indicator">
										</sj:a>
									</div>
									<div class="grid_1" style="float: right;">
										<s:url id="removeTMSMaintanece"
											action="ajaxRemoveTMSMaintanence" includeParams="all"
											escapeAmp="false" >
											<s:param name="vehicleAcademicDetails.id" value="%{vehicleAcademicDetails.id}"></s:param>
											<s:param name="transportMaintenance.id" value="%{id}" />
										</s:url>
										<s:div cssClass="close"
											onclick="javascript:confirmDialogWithTarget(this,'addPetrolCostRacks%{vehicleAcademicDetails.id}');"
											id='%{removeTMSMaintanece}' theme="simple"
											title="Delete this maintenance cost">
										</s:div>
									</div>
								</div>
								<div id="addPetrolCostEdit<s:property value='%{monthId}' />" style="display: none;" class='loading'> </div>
							</div>
						</s:iterator>
					-->

<script type="text/javascript">
TableAdvanced.init();
function PopupTransportMaintenance(vehicleId,academicDetailsId) {
		var url = jQuery.url.getChatURL("/admin/ajaxEditPetrolCost.do");
		$('#transportMaintenanceDiv').html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		$.ajax( {
				url : url,
				cache : false,
				data : "vehicleAcademicDetails.id="+ vehicleId+"&transportMaintenance.id="+academicDetailsId,
				success : function(html) {
					$("#transportMaintenanceDiv").html(html);
				}
			});
		} 
	$.subscribe('cleanOpenDivsEdit', function(event, data) {
			$("div.loading").each(function(i, row) {
				$(row).find('div').remove();
				$(row).hide();
			});
	});
	function toggleActivitPetrolCont(activityId) 
	{
		$('#addPetrolCostRacks' + activityId).hide();
	}
	
	$.subscribe('doInitAddPetrolCost', function(event, data) {
	
			if ($('#' + data.id).is(":hidden")) 
			{
				$('#' + data.id).show();		
			}
			 else {
			 	$('#' + data.id).hide();
			}
	});
</script>