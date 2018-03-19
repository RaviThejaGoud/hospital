<%@ include file="/common/taglibs.jsp"%>
<s:if test="%{tempList != null && !tempList.isEmpty()}">
	<div class="form-group">
		<label class="control-label col-md-5">
			<span class="required">*</span>Select District :
		</label>
		<div class="col-md-7">
			<s:select id="districtId" list="tempList"
				cssClass="required form-control input-medium required"
				listKey="id" listValue="districtName" headerKey=""
				headerValue="- Select -" name="selectedId" theme="simple"
				onchange="javascript:getMandals(this);" />
		</div>
	</div>
</s:if>

<script type="text/javascript">
function getMandals(selectBox) {
	var organizationId = $("select#districtId").val();
	var roleName = $("select#roleName option:selected").val();
	if (roleName == "ROLE_CEO" || roleName == "ROLE_BEO") {
		var url = jQuery.url.getChatURL("/govt/ajaxGovtMandalsByDistrict.do");
		if (organizationId.length == 0) {
			alert("!Oops select District.");
		} else {
			$("#mandalDiv")
					.html(
							'<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
			var pars = "anyId=" + organizationId;
			$
					.ajax( {
						url : url,
						cache : false,
						data : pars,
						success : function(html) {
							$("#mandalDiv").html(html);
							document.getElementById('mandalDiv').style.display = "block";
						}
					});
		}
	} else {
		$("div#mandalDiv").hide();
	}
}
</script>

