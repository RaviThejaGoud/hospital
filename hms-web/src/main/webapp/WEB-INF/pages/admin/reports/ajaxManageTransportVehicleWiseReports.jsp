<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i> Student-->Vehicle Wise Report
				</div>
			</div>
			<div class="portlet-body">
				<div class="tab-content">
					<s:if test="%{(tempList != null && !tempList.isEmpty())}">
						<s:form action="ajaxVehicleWiseStudentDetails" theme="simple"
							namespace="/reports" cssClass="form-horizontal"
							onsubmit="return generateRouteIds();" id="classAndCommunity"
							method="post">
							<s:hidden name="tempString" cssClass="tempString" value=""></s:hidden>
							<s:hidden id="routeNameIds" name="anyTitle" />
							<div class="form-body">
								<div class="form-group">
									<label class="conLable col-md-3 control-label">
										<span class="required">*</span> Available Vehicles :
									</label>
									<div class="col-md-12">
										<div class="checkbox-list">
											<label class="checkbox-inline">
												<input type="checkbox" name="" value=""
													onClick="checkallVehicles()" class="checkbox allVehicles">
												All Vehicles
											</label>
										</div>
										<s:checkboxlist name="chkBoxSelectedIds" list="tempList"
											theme="ems" listKey="id"
											listValue="vehicleNameAndVehicleNumber"
											cssClass="checkbox small" />
									</div>
								</div>
								<div class="form-actions fluid">
									<div class="col-md-offset-2 col-md-9">
										<s:submit type="submit" value="Generate Pdf"
											cssClass="submitBt btn blue long" onclick="reportType()"
											title="generate report">
										</s:submit>
									</div>
								</div>
							</div>
						</s:form>
					</s:if>
					<s:else>
						<div class="alert alert-info">
							Students assigned vehicles not found.
						</div>
					</s:else>
				</div>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/ems.js"></script>
  
<script type="text/javascript">
$(document).ready(function() {
changePageTitle("Manage Vehicle Wise Reports");
$("input:checkbox, input:radio:not('.toggle')").uniform();  
$("input[name=chkBoxSelectedIds]").click(function() {
		if ($("input[name=chkBoxSelectedIds]:unchecked").length > 0) {
		   $(".allVehicles").parent('span').removeClass("checked");
			$(".allVehicles").attr("checked", false);
		} else {
		    $(".allVehicles").parent('span').addClass("checked");
			$(".allVehicles").attr("checked", true);
		}
	});
});
function generateRouteIds() {
	if ($("input[name=chkBoxSelectedIds]:checked").length > 0) {
		var routeIds = $("input[name=chkBoxSelectedIds]:checked");
		var selectedRouteIds = '';
		if (routeIds.length > 0) {
			selectedRouteIds = '(';
			for ( var i = 0; i < routeIds.length; i++) {
				if (i == (routeIds.length - 1))
					selectedRouteIds += routeIds[i].value;
				else {
					selectedRouteIds += routeIds[i].value + ', ';
				}
			}
			selectedRouteIds += ')';
		}
		$("#routeNameIds").val(selectedRouteIds);
		return true;
	} else if ($("input[name=chkBoxSelectedIds]:checked").length == 0) {
		alert("Please select at least one vehicle");
		return false;
	} else {
		return false;
	}
}
function checkallVehicles() {
	if ($(".allVehicles").is(':checked')){
	    $("[name='chkBoxSelectedIds']").each(function(){
		   $(this).parent('span').addClass('checked');
		    $(this).attr("checked", "true");
		});
	}
	else{
	 $("[name='chkBoxSelectedIds']").each(function(){
		   $(this).parent('span').removeClass('checked');
		    $(this).removeAttr("checked");
		});
	}	
}
function reportFormate() {
		$('.tempString').val('Excel');
	}
function reportType() {
		$('.tempString').val('PDF');
	}
</script>


