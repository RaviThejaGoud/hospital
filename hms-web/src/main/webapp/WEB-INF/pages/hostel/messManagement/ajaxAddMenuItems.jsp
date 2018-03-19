<%@ include file="/common/taglibs.jsp"%>
<s:if test="%{tempList2 != null && !tempList2.isEmpty()}">
	<div class="grid_6">
		<s:select list="tempList2" listKey="id" listValue="dayName"
			label="Select Day" cssClass="required textfield" theme="css_xhtml"
			required="true" id="dayId" name="anyId" requiredposition="first"
			onchange="javascript:getMenuItemsByDayIdAndBuildingId(this.value);">
		</s:select>
	</div>
</s:if>
<s:else>
	<div class="grid_12 th thb">
		 Currently there are no menu items.
	</div>
</s:else>
<script type="text/javascript">
$(document).ready(function() {
	getMenuItemsByDayIdAndBuildingId();
});
function getMenuItemsByDayIdAndBuildingId() {
	var dayId = $('#dayId').val();
	$('.dayId').val(dayId);
	var buildingId = $("#buldingId").val();
	if (isNonEmpty(dayId) && isNonEmpty(buildingId)) {
		$('#menuTypesContent')
				.html(
						'<div align="center" style="padding:30px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		var pars = "anyId=" +dayId+ "&selectedId=" +buildingId;
		$.ajax( {
			url : jQuery.url.getChatURL("/hostel/ajaxGetmenuItems.do"),
			cache : false,
			data : pars,
			success : function(response) {
				$('#menuTypesContent').html(response);
				$('#menuTypesContent').show();
			}
		});
	}
}
</script>
