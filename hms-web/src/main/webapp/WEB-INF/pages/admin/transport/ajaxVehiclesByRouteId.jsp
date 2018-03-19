<%@ include file="/common/taglibs.jsp"%>
<s:if test="%{tempList1 != null && !tempList1.isEmpty()}">
	<div class="form-group">
		<label class="control-label col-md-3">
				<span class="required">*</span>Select Vehicle : 
		 </label>
		 <div class="col-md-9">
		<s:select id="vehicleId" list="tempList1" 
			listKey="id" tabindex="22" listValue="name" headerKey=""
			headerValue="- Select -" name="eventId" theme="simple" cssClass="required form-control input-medium" onchange="javascript:getDriversByVehicleRoute(this.value);" />
	</div>
	</div>
</s:if>
<s:else>
	<div class="alert alert-info">
		<b>There are no vehicles for this route,please add vehicle for this route.</b>
	</div>
</s:else>

<script type="text/javascript">
function getDriversByVehicleRoute(vehicleId) {
	var routeId = $("select#routeId").val();
	 var url = jQuery.url
			.getChatURL("/admin/ajaxGetDriversByVehicleRoute.do");
	if (routeId.length == 0) {
		alert("!Oops select Route.");
	} else {
		$("#resultsDiv3").html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		var pars = "anyId=" + routeId+"&tempId2="+vehicleId;
	 	$.ajax( {
			url : url,
			cache : false,
			data : pars,
			success : function(html) {
				$("#resultsDiv3").html(html);
				$('#resultsDiv3').show();
		}
		});
	}
}
</script>