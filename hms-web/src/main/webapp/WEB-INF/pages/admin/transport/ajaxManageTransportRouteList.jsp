<%@ include file="/common/taglibs.jsp"%>
<script src="http://maps.google.com/maps?file=api&amp;v=2&amp;sensor=false&amp;key=ABQIAAAA8CqyY6EJZVj7j--reWj9dBT58jzTd8XFdIIYCtzuwufA0TXApBTcF4I2-SOlMWHc4wXV9kUBvQj48g" type="text/javascript"></script>
<s:if test="%{routeNamesList!=null && !routeNamesList.isEmpty() }">
	<div class="form-group">
		<label class="control-label col-md-2">
			Select Route :
		</label>
		<div class="col-md-2">
			<s:select list="routeNamesList" id="routeNamesList"  
				listKey="key" cssClass="form-control input-medium" listValue="value" headerKey=""
				headerValue="- Select Route -" theme="simple"
				onchange="javascript:getAjaxDoGetAssignedStudentsToRoute(this.value);">
			</s:select>
		</div>
	</div>
</s:if>
<s:else>
	<div class="alert alert-info">
		There is no route assigned.
	</div>
</s:else>
<div id="assignedVehiclesToRoute">
</div>
<script type="text/javascript">
changePageTitle('Manage Transport Route');
function getAjaxDoGetAssignedStudentsToRoute(routeId) {
if (routeId == "") {
		$("#assignedVehiclesToRoute").hide();
}else{
	$(this).attr('selected','selected');
	//if (!routeId == "") {
		var pars = "routeId=" + routeId + "&tempString="+'<s:property value="tempString"/>'; 
		$("div#assignedVehiclesToRoute")
				.html(
						'<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		var url = jQuery.url.getChatURL("/admin/ajaxGetTransportVehicleList.do");
		$.ajax( {
			url : url,
			cache : false,
			data : pars,
			success : function(html) {
				$("div#assignedVehiclesToRoute").html(html);
				$("div#assignedVehiclesToRoute").show();
			}
		});
	}
  //}
}
</script>
