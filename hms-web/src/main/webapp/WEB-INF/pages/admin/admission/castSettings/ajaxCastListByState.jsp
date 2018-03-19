<%@ include file="/common/taglibs.jsp"%>
<div class=" form-group">
	<label class="control-label col-md-4">
		Community :
	</label>
	<div class="col-md-6">
		<s:select id="castName" list="castSettingList" listKey="id"
			listValue="castName" cssClass="form-control input-medium"
			headerKey="0" headerValue="- Select -"
			name="onlineApplicationDetails.castId.id" theme="simple"
			onchange="javascript:getSubCastDetailsByCast(this.value);" />
	</div>
</div>
<script type="text/javascript">
var castId = '<s:property value="onlineApplicationDetails.castId.id" />';
var admissionId = '<s:property value="onlineApplicationDetails.id" />';
if(isNonEmpty(castId))
{
	getSubCastDetailsByCast1(castId,admissionId);
}

 function getSubCastDetailsByCast1(castId,admissionId) {
	//var castId = selectBox.value;
	var url = jQuery.url
			.getChatURL("/admin/ajaxGetSubCastDetailsByCast.do");
	if (castId.length == 0) {
		alert("!Oops select Cast.");
	} else {
		$("#resultsDiv2")
				.html(
						'<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		var pars = "onlineApplicationDetails.castId.id=" + castId+"&onlineApplicationDetails.id="+admissionId;
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
 
 function getSubCastDetailsByCast(castId) {
	//var castId = selectBox.value;
	var url = jQuery.url
			.getChatURL("/admin/ajaxGetSubCastDetailsByCast.do");
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