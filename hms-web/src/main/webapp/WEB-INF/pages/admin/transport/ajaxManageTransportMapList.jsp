<%@ include file="/common/taglibs.jsp"%>
<script src="http://maps.google.com/maps?file=api&amp;v=2&amp;sensor=false&amp;key=ABQIAAAA8CqyY6EJZVj7j--reWj9dBT58jzTd8XFdIIYCtzuwufA0TXApBTcF4I2-SOlMWHc4wXV9kUBvQj48g" type="text/javascript"></script>
<div class="grid_12">
	<div class="grid_4">
		<label class="labelRight">
		Select Route:
		</label>
	</div>
	<div class="grid_5">
		<s:if test="%{routeList!=null && !routeList.isEmpty() }">
		<s:select list="routeList" id="routeList" name="" listKey="id"
			listValue="routeName" headerKey=""
			headerValue="- Select Route -" theme="simple"
			onchange="javascript:getAjaxDoGetAssignedStudentsToRoute(this.value);"
			requiredposition="first">
		</s:select>
		</s:if>
	</div>
</div>
<div class="grid_12" id="assignedStundentsToRoute">
</div>
<script type="text/javascript">
changePageTitle('Manage Transport Route Map');
function getAjaxDoGetAssignedStudentsToRoute(routeId) {
$(this).attr('selected','selected');
	if (!routeId == "") {
		var pars = "routeId=" + routeId;
		$("div#assignedStundentsToRoute")
				.html(
						'<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		var url = jQuery.url.getChatURL("/admin/ajaxGetAssignedStundentsToRoute.do");
		$.ajax( {
			url : url,
			cache : false,
			data : pars,
			success : function(html) {
				$("div#assignedStundentsToRoute").html(html);
				$("div#assignedStundentsToRoute").show();
			}
		});
	}
}
</script>
