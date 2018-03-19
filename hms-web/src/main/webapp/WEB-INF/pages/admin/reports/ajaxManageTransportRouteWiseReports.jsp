<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Student-->Route Wise Report
				</div>
			</div>
			<div class="portlet-body">
				<div  class="tab-content">
					<s:if test="%{tempList != null && !tempList.isEmpty()}">
						<s:form action="ajaxRouteWiseDetails" theme="simple"
							namespace="/reports" onsubmit="return generateRouteIds();" cssClass="form-horizontal"
							id="classAndCommunity" method="post">
							<s:hidden name="tempString" cssClass="tempString" value=""></s:hidden>
							<s:hidden id="routeNameIds" name="anyTitle" />
							<div class="form-body">
								<div class="form-group">
									<label class="conLable col-md-3 control-label">
										<span class="required">*</span> Available Routes :
									</label>
									<div class="col-md-12">
										<div class="checkbox-list">
											<label class="checkbox-inline">
												<input type="checkbox" name="" value=""
													onClick="checkallRoutes()" class="checkbox allRoutes">
												All Routes
											</label>
										</div>
										<s:checkboxlist name="chkBoxSelectedIds" list="tempList"
											listKey="id" listValue="routeName" theme="ems" />
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
							Routes are not available.
						</div>
					</s:else>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function() {
 changePageTitle("Manage Route Wise Reports");
 $("input:checkbox, input:radio:not('.toggle')").uniform();  
 $("input[name=chkBoxSelectedIds]").click(function() {
		if ($("input[name=chkBoxSelectedIds]:unchecked").length > 0) {
		   $(".allRoutes").parent('span').removeClass("checked");
			$(".allRoutes").attr("checked", false);
		} else {
		    $(".allRoutes").parent('span').addClass("checked");
			$(".allRoutes").attr("checked", true);
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
		alert("Please select at least one route");
		return false;
	} else {
		return false;
	}
}
function checkallRoutes() {
		if ($(".allRoutes").is(':checked')){
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


