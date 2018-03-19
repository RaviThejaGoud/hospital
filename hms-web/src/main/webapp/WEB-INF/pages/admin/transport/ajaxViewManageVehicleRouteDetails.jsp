<%@ include file="/common/taglibs.jsp"%>
<s:if test="%{objectList != null && !objectList.isEmpty()}">
	<jsp:include page="/common/messages.jsp"></jsp:include>
	<span class="label label-danger"> NOTE : </span>&nbsp; Vehicle capacity is displayed based on current date.
	<div class="spaceDiv"></div>
	<div id="targetDiv"></div><!-- this div used for success message after delete-->
	<table
		class="table table-striped table-bordered table-hover table-full-width"
		id="sample_2">
		<thead>
			<tr>
				<th>
					Vehicle Name
				</th>
				<th>
					Route Name
				</th>
				<th>
					Pickup Capacity
				</th>
				<th>
					Drop Capacity
				</th>
				<th>
					Driver Name
				</th>
				<th>
					Timings
				</th>
				<s:if test='%{#session.previousYear == "N"}'>
				<th>
					Delete
				</th>
				</s:if>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="objectList">
				<tr id="transportVehicles<s:property value="vehicleAcademicDetailId" />">
					<td>
						<s:if test="name !=null">
							<s:property value="name" />
						</s:if>
						<s:else>
							 ---
							</s:else>
					</td>
					<td>
						<s:property value="routeName" />
					</td>
					<td>
						<s:property value="noOfSeats" />
						<b style="color: green;">(<s:property value="availablePickUp" />)</b>
					</td>
					<td>
						<s:property value="noOfSeats" />
						<b style="color: green;">(<s:property value="availableDrop" />)</b>
					</td>
					<td>
						<s:property value="driverName" />
					</td>
					<td>
						<s:property value="vehicleTiming" />
					</td>					
					<s:if test='%{#session.previousYear == "N"}'>
					<td>
						<s:url id="removeVehicle"
							action="ajaxGetVehicleWithRouteInformation" escapeAmp="false" namespace="/admin">
							<s:param name="vehicleAcademicDetails.id"
								value="%{vehicleAcademicDetailId}" />
							<s:param name="anyId" value="%{routeId}" />
						</s:url>
						<s:div cssClass="btn btn-xs red" id='%{removeVehicle}'
							onclick="javascript:confirmDeleteRoute(this,'transportVehicles%{vehicleAcademicDetailId}',%{vehicleAcademicDetailId},%{routeId});"
							theme="simple" title="Delete this vehicle">
							<i class="fa fa-times"></i>Delete</s:div>
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
<script type="text/javascript">
$(document).ready(function() {
	changePageTitle("Manage Vehicle Maintenance");
	TableAdvanced.init();
	UIExtendedModals.init();
	$('li#doAssignVehicle').removeClass('active');
});

function confirmDeleteRoute(event,target, vehicleId, routeId) {
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
						alert(vehicleDetails);
						prdDiv.remove();
						$('.question').remove();
					} else
						prdDiv.parent().parent().remove();
				} else {
					prdDiv.parent().parent().remove();
					$('div#targetDiv').html('<div class="alert alert-success"><strong>Vehicle with route deleted successfully.</strong><button class="close" data-dismiss="alert"></button></div>');
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

