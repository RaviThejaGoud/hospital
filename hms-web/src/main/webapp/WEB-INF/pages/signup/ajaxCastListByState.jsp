<%@ include file="/common/taglibs.jsp"%>
<div class=" grid_12 alpha omega">
	<s:select id="castName" list="castSettingList" label="Community"
		listKey="id" cssStyle="width:200px;" tabindex="10"
		listValue="castName" headerKey=""
		headerValue="- Select -" name="onlineApplicationDetails.castId"
		theme="css_xhtml" onchange="javascript:getSubCastDetailsByCast(this);" />
</div>
<div id="resultsDiv2"></div>
<script type="text/javascript">
function getSubCastDetailsByCast(selectBox) {
	var castId = selectBox.value;
	var url = jQuery.url.getChatURL("/signup/ajaxGetSubCastDetailsByCast.do");
	if (castId.length == 0) {
		alert("!Oops select Cast.");
	} else {
		$("#resultsDiv2")
				.html(
						'<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		var pars = "onlineApplicationDetails.castId.id=" + castId;
		$.ajax( {
			url : url,
			cache : false,
			data : pars,
			success : function(html) {
				$("#resultsDiv2").html(html);
			}
		});
	}
}
</script>

