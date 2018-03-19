<%@ include file="/common/taglibs.jsp"%>
<jsp:include page="/common/messages.jsp"></jsp:include>
<div id="targetDiv"></div><!-- this div used for success message after delete-->
<s:if test="%{objectList != null && !objectList.isEmpty()}">
	<div class="form-group">
		<div class="panel-body col-md-12">
			<div class="col-md-1">
				<span class="label label-danger"> NOTE : </span>
			</div>
			<div class="col-md-10">
				<ul>
					<li>
						 You can view all vehicle from the below table.
					</li>
					<li>
						You can edit/update the vehicle information by clicking the edit button from the below table.
					</li>
					<li>
						You can add the vehicle maintenance cliking on Add button under the Maintenance column from the below table.
					</li>
				</ul>
			</div>
		</div>
	</div>
	<table
		class="table table-striped table-bordered table-hover table-full-width"
		id="sample_2">
		<thead>
			<tr>
				<th>
					Name
				</th>
				<th>
					Number
				</th>
				<th>
					Driver Info
				</th>
				<th>
					Maintenance Cost
				</th>
				<s:if test='%{#session.previousYear == "N"}'>
					<th>
						Edit
					</th>
					<th>
						Delete
					</th>
				</s:if>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="objectList">
				<tr>
					<td>
						<s:if test="vehicleName !=null">
							<s:property value="vehicleName" />
						</s:if>
						<s:else>
										 ---
									</s:else>
					</td>
					<td>
						<s:property value="vehicleNumber" />
					</td>
					<td>
						<s:property value="driverName" />
						<s:if test="%{mobileNumber != ''}"> 
										(
										<s:property value="mobileNumber" />
										)
									</s:if>
					</td>
					<td>
					<s:if test='%{#session.previousYear == "N"}'>
						<s:url id="doAddNewPetrolCost" action="ajaxDoAddNewPetrolCost"
							includeParams="all" escapeAmp="false" namespace="/admin">
							<s:param name="vehicleAcademicDetails.id" value="%{vehicleAcademicDetailId}"></s:param>
							<s:param name="vehicleAcademicDetails.vehicle.id" value="%{vehicleId}" />
						</s:url>
						<sj:a href="%{doAddNewPetrolCost}" indicator="indicatorLeader"
							cssClass="btn btn-xs green" targets="transportVehicles">
							<i class="fa fa-plus"></i>Add</sj:a>
						<s:url id="addPetrolCost" action="ajaxViewAllPetrolCost"
							includeParams="all" escapeAmp="false" namespace="/admin">
							<s:param name="vehicleAcademicDetails.id"
								value="%{vehicleAcademicDetailId}" />
						</s:url>
						/
						</s:if>
						<sj:a href="%{addPetrolCost}" indicator="indicator"
							cssClass="btn btn-xs yellow" targets="transportVehicles">
							<i class="fa fa-file-o"></i>View</sj:a>
					</td>
					<s:if test='%{#session.previousYear == "N"}'>
						<td>
							<s:url id="doEditvehicle" action="ajaxDoEditVehicle"
								includeParams="all" escapeAmp="false" namespace="/admin">
								<s:param name="vehicleAcademicDetails.id"
									value="%{vehicleAcademicDetailId}" />
							</s:url>
							<sj:a href="%{doEditvehicle}" indicator="indicator"
								targets="transportVehicles"
								cssClass="normalEdit btn btn-xs purple" title="Edit">
								<i class="fa fa-edit"></i>Edit
									</sj:a>
						</td>
						<td>
								<s:url id="removeVehicle" action="ajaxGetVehicleInformation" includeParams="all" escapeAmp="false" namespace="/admin">
									<s:param name="vehicleAcademicDetails.id" value="%{vehicleAcademicDetailId}" />
									<s:param name="vehicleAcademicDetails.vehicle.id" value="%{vehicleId}" />
								</s:url>
								<s:div cssClass="btn btn-xs red"
									onclick="javascript:confirmDeleteRoute(this,%{vehicleAcademicDetailId});"
									theme="simple" id='%{removeVehicle}' title="Delete this vehicle">
									<i class="fa fa-times"></i>Delete
								</s:div>
						</td>
					</s:if>
				</tr>
			</s:iterator>
		</tbody>
	</table>
</s:if>
<s:else>
	<div class="alert alert-info">
		Currently there are no vehicles.
	</div>
</s:else>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/ems.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	changePageTitle("Manage Vehicle Maintenance");
	TableAdvanced.init();
	$('ul.nav-tabs li').removeClass('active');
	$('li#doAddVehicles').addClass('active');
});
function confirmDeleteRoute(event, vehicleAcademicDetailId) {
	var thishref = $(event).attr('id');
	if ($(event).next('.question').length <= 0) {
		$(event)
				.after(
						'<div class="question">Are You Sure?<br/> <span class="yes">Yes</span><span class="cancel">Cancel</span></div>');
	}
	$(event).next('.question').animate( {
		opacity : 1
	}, 300);
	$('.yes').unbind('click');
	$('.yes').bind('click', function() {
		var prdDiv = $(this).parents('.question');
		prdDiv.html('Processing...');
		$.ajax( {
			url : thishref,
			cache : false,
			dataType : 'json',
			success : function(response) {
				if (isNonEmpty(response.info)) {
					var vehicleDetails = response.info;
					if (isNonEmpty(vehicleDetails)) {
					alert("NOTE: "+vehicleDetails);
						prdDiv.remove();
					} else
						prdDiv.parent().parent().remove();
				} else {
					prdDiv.parent().parent().remove();
					$('div#targetDiv').html('<div class="alert alert-success"><strong>Vehicle deleted successfully.</strong><button class="close" data-dismiss="alert"></button></div>');
				}
			}
		});
	});
	$('.cancel').unbind('click');
	$('.cancel').bind('click', function() {
		$(this).parents('.question').fadeOut(300, function() {
			$(this).remove();
		});
		return false;
	});
}
</script>

