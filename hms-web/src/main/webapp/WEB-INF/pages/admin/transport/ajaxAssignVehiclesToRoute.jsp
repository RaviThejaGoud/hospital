<%@ include file="/common/taglibs.jsp"%> 
	<s:form action="ajaxSaveVehicleToRoute" theme="simple"
		id="editVehicleRouteInfo" method="post" cssClass="form-horizontal" namespace="/admin">
			<div class="form-body">
			  <jsp:include page="/common/messages.jsp"></jsp:include>
			   <span class="label label-danger"> NOTE : </span>&nbsp; You can assign vehicles to route.
			  <div class="spaceDiv"></div>
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-3">
								<span class="required">*</span>Select Route :
							</label>
							<div class="col-md-9">
								<s:select id="routeId" list="routeList" listKey="id" tabindex="1"
									cssClass="required form-control input-medium" listValue="routeName"
									headerKey="" headerValue="- Select -" theme="simple" name="anyId"
									onchange="javascript:getVehicleByRoute(this);" />
							</div>
						</div>
					</div>
					<div id="resultsDiv2" style="display: none;" class="col-md-6">
						<jsp:include
							page="/WEB-INF/pages/admin/transport/ajaxVehiclesByRouteId.jsp" />
					</div>
				</div>
				
				<div id="resultsDiv3"></div>
				
				<div class="form-actions fluid">
			<div class="col-md-6">
			<div class="col-md-offset-3 col-md-9">
			<s:if test='%{(#session.academicYear == null || #session.academicYear.isEmpty()) || (#session.previousYear=="N")}'>
				<sj:submit   cssClass="submit small btn blue" value="Save" indicator="personalInfo" validate="true"
					targets="mainContentDiv" formIds="editVehicleRouteInfo" />
			</s:if>
			</div></div>
			</div>
		</div>
	</s:form>
<script type="text/javascript">
changePageTitle('Assign Vehicle to Routes');
function getVehicleByRoute(selectBox) {
	$('#resultsDiv3').hide();
	var routeId = $("select#routeId").val();
	 var url = jQuery.url
			.getChatURL("/admin/ajaxGetVehicleByRouteId.do");
	if (routeId.length == 0) {
		alert("!Oops select Route.");
		$('#resultsDiv3').hide();
		$("#resultsDiv2").hide();
	} else {
		$("#resultsDiv2").html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		var pars = "anyId=" + routeId;
	 	$.ajax( {
			url : url,
			cache : false,
			data : pars,
			success : function(html) {
				$("#resultsDiv2").html(html);
				document.getElementById('resultsDiv2').style.display = "block"; 
		}
		});
	}
}
</script>
