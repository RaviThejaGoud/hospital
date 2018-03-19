<%@ include file="/common/taglibs.jsp"%>
<s:if test="%{tempList1 != null && !tempList1.isEmpty()}">
	<div class="row">
		<div class="col-md-6">
			<div class="form-group">
				<label class="control-label col-md-5">
					<span class="required">*</span>Select Mandal:
				</label>
				<div class="col-md-7">
					<s:select id="mandalId" list="tempList1"
						cssClass="required form-control input-medium as-input"
						required="true" listKey="id" listValue="mandalName" headerKey=""
						headerValue="- Select -" name="mandal.id" theme="simple"
						onchange="javascript:getVillages(this);" />
				</div>
				<div id="villageDiv" style="display: none">
					<jsp:include
						page="/WEB-INF/pages/masterAdmin/ajaxGetVillages.jsp" /></div>
			</div>
		</div>
	</div>
</s:if>


<script type="text/javascript">
function getVillages(selectBox) {
	var organizationId = $("select#mandalId").val();
	var url = jQuery.url.getChatURL("/signup/ajaxGetVillagesByMandal.do");
	if (organizationId.length == 0) {
		alert("!Oops select Route.");
	} else {
		$("#villageDiv")
				.html(
						'<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		var pars = "eventId=" + organizationId;
		$.ajax( {
			url : url,
			cache : false,
			data : pars,
			success : function(html) {
				$("#villageDiv").html(html);
				document.getElementById('villageDiv').style.display = "block";
			}
		});
	}
}
</script>





