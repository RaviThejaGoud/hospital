<%@ include file="/common/taglibs.jsp"%>
<div class="row">
<s:if test="%{tempList1 != null && !tempList1.isEmpty()}">
<br/>
	<div class="col-md-6">
		<div class="form-group">
			<label class="control-label col-md-3">
					<!-- <span class="required">*</span> -->Select Vehicle : 
			 </label>
			 <div class="col-md-9">
				<s:select id="vehicleId" list="tempList1" 
					listKey="id" tabindex="22" listValue="name" headerKey=""
					headerValue="- Select -" name="eventId" theme="simple" cssClass="form-control input-medium" onchange="javascript:getDriversByVehicleRoute(this.value);" />
			</div>
		</div>
	</div>
	<div class="col-md-6">
		<div class="form-group">
		</div>
	</div>
</s:if>
<s:else>
	<div class="alert alert-info">
		<b>There are no vehicles for this route,please add vehicle for this route.</b>
	</div>
</s:else>

</div>

	<div id="driversInfoDivId"></div>
	
	<br/><br/><br/><br/>

<script type="text/javascript">

$(document).ready(function() {
	vehicleId = '<s:property value="eventId"/>';
	if (isNonEmpty(vehicleId)) {
		getDriversByVehicleRoute(vehicleId);
	}
});
			
			
function getDriversByVehicleRoute(vehicleId) {
	
	arrivalTimeInput = $( "#arrivalTimeInput" ).val();
	departureTimeInput = $( "#boardingPointStatTime1" ).val();
	 var url = jQuery.url.getChatURL("/admin/ajaxGetDriverDetatilsByVehicleId.do?tempId2="+vehicleId+"&route.routePointEndTime=" + arrivalTimeInput+"&route.routePointStartTime="+departureTimeInput);
	if (vehicleId.length == 0) {
		alert("!Oops select Vehicle.");
		$('#driversInfoDivId').html("");
	} else {
		$("#driversInfoDivId").html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
	 	
		$.ajax( {
			url : url,
			cache : false,
			success : function(html) {
				$("#driversInfoDivId").html(html);
				$('#routeDriverDivId').css({"margin-right":"514px"});
			}
		});
		
	}
}
</script>