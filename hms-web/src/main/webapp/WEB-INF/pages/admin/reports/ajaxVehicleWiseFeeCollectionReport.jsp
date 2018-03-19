/pages/admin/reports/ajaxVehicleWiseFeeCollectionReport.jsp
<!--
<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/ems.js"></script>
<div class="block grid_12">
<div class="block_head">
	<h2 id="AdminReports">
		</h2>
	<ul>
		<s:if test='%{user.isOnlySchoolAdmin=="Y"}'>
			 <li>
			 	<a id="adminStaffAndStudent" href="${pageContext.request.contextPath}/admin/adminManageStaffAndStudent.do" class="current">Back To Admin</a>
			</li>
		</s:if>
	</ul>
</div>
<div class="block_content">
<div class="grid_11"> 
	<s:if test="%{(vehicleList != null && !vehicleList.isEmpty())}">
		<s:form action="ajaxVehicleFeeCollectionWiseReport" theme="css_xhtml" namespace="/reports" target="_new"
			onsubmit="return generateVehicleIds();" id="classAndCommunity"
			method="post">
				<s:hidden name="tempString" cssClass="tempString" value=""></s:hidden>
				<s:hidden id="routeNameIds" name="anyTitle" />
				<div class="grid_10 checkbox">
					<h1>
						<span class="required">*</span> Available Vehicles:
					</h1>
					<input type="checkbox" name="" value=""
						onClick="checkallVehicles()" class="checkbox allVehicles">
					All Vehicles
					<s:checkboxlist name="chkBoxSelectedIds" list="vehicleList" theme="ems"
						listKey="id" listValue="vehicleTypeAndVehicleNumber" 
						cssClass="checkbox small" />
				</div>
				<div class="grid_10" style="margin-right: 33px;">
					<s:submit type="submit small" value="Pdf" onclick="reportType()"
						cssClass="submit small" title="generate report"
						cssStyle="cursor:pointer">
					</s:submit>
				</div>
			</s:form>
	</s:if>
	<s:else>
 Routes are not available.
</s:else>
</div>
</div>
</div>
<script type="text/javascript">
$(document).ready(function() {
$('h2#AdminReports').text($('div#sideMenu li.active a').text()+""+$('div#sideSubMenu li.active a').text());
var title1=$('div#sideSubMenu ul li.active a').text();
		  changePageTitle(title1);
	$("input[name=chkBoxSelectedIds]").click(function() {
		if ($("input[name=chkBoxSelectedIds]:unchecked").length > 0) {
			//$(".allVehicles").removeAttr("checked");
			$(".allVehicles").attr("checked", false);
		} else {
			$(".allVehicles").attr("checked", true);
		}
	});
});
function generateVehicleIds() {
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
		alert("Please select at least one religion");
		return false;
	} else {
		return false;
	}
}
function checkallVehicles() {
	if ($(".allVehicles").is(':checked'))
		$("input[name='chkBoxSelectedIds']").attr("checked", "true");
	else
		$("input[name='chkBoxSelectedIds']").removeAttr("checked");
}

function reportFormate() {
		$('.tempString').val('Excel');
	}
function reportType() {
		$('.tempString').val('pdf');
	}
</script>


-->