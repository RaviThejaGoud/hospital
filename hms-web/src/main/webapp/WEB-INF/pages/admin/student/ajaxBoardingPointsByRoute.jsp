<%@ include file="/common/taglibs.jsp"%>
<s:if test="%{tempList1 != null && !tempList1.isEmpty()}">
		<div class="form-group">
			<label class="control-label col-md-2">
				<span class="required">*</span>Select Boarding Point :
			</label>
			<div class="col-md-3">
				<s:select id="boardingId" list="tempList1"  listKey="id"
					listValue="boardingPointName" headerKey="" headerValue="- Select -"
					cssClass="required form-control input-medium" name="anyId"
					theme="simple" onchange="javascript:getVehiclesByBoardingPoint();" />
			</div>
		</div>
</s:if>
<s:else>
	<div class="alert alert-info col-md-6">
		Boarding points not found for this route.
	</div>
</s:else>
<script type="text/javascript">
$(document).ready(function() {
	getVehiclesByBoardingPoint();
});
function getVehiclesByBoardingPoint() {
	var boardingPointId = $("select#boardingId").val();
	var studentId = $("#studentName").val();
	var vehicleAcademicDetailsId = $("span.vehicleAcademicDetailsId").attr("id");
	 var url = jQuery.url.getChatURL("/admin/ajaxGetVehicleByBoardingId.do");
	 if(isNonEmpty(boardingPointId) && boardingPointId != 0){
	 	$("#vehicleBoardingPoints").html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		var pars = "student.categoryId=" + boardingPointId+"&eventId="+vehicleAcademicDetailsId + "&student.id=" + studentId;
	 	$.ajax( {
				url : url,
				cache : false,
				data : pars,
				success : function(html) {
					$("#vehicleBoardingPoints").html(html);
					$('#vehicleBoardingPoints').show();
			}
		});
	 }else{
	 	$("#vehicleBoardingPoints").html('<div class="alert alert-info col-md-12"> Please select boarding point.</div>');
		$('#vehicleBoardingPoints').show();
	 }
}  
</script>		
	
	 
	