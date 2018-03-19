<%@ include file="/common/taglibs.jsp"%>
<label class="control-label col-md-4">
	Community :
</label>
<div class="col-md-5">
<s:select id="castName" list="castSettingList"  listKey="id"   tabindex="32" cssClass="form-control"
    	listValue="castName" headerKey="" headerValue="- Select -" name="person.castId" theme="simple" onchange="javascript:getSubCastDetailsByCast(this);" />
</div>


 <script type="text/javascript">
 function getSubCastDetailsByCast(selectBox) {
	var castId = selectBox.value;
	var url = jQuery.url
			.getChatURL("/admin/ajaxGetStudentSubCastDetailsByCast.do");
	if (castId.length == 0) {
		alert("!Oops select Cast.");
	} else {
		$("#resultsDiv2")
				.html(
						'<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		var pars = "person.castId=" + castId;
		$.ajax( {
			url : url,
			cache : false,
			data : pars,
			success : function(html) {
				$("#resultsDiv2").html(html);
				document.getElementById('resultsDiv2').style.display = "block";
				 $('select#subCastName').attr("tabindex", 35);
				//document.getElementById('schoolBooksList').style.display="none";
		}
		});
	}
}
</script>