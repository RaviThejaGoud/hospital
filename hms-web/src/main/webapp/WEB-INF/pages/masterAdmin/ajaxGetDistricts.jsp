<%@ include file="/common/taglibs.jsp"%>
<s:if test="%{tempList != null && !tempList.isEmpty()}">
	<div class="row">
		<div class="col-md-6">
			<div class="form-group">
				<label class="control-label col-md-5">
					<span class="required">*</span>Select District:
				</label>
				<div class="col-md-7">
					<s:select id="districtId" list="tempList"
						cssClass="required form-control input-medium as-input"
						listKey="id" listValue="districtName" headerKey=""
						headerValue="- Select -" name="district.id" theme="simple"
						onchange="javascript:getMandals(this);" />
				</div>
			</div>
		</div>
		<div class="col-md-6">
			<div id="mandalDiv" style="display: none">
				<jsp:include page="/WEB-INF/pages/masterAdmin/ajaxGetMandals.jsp" /></div>
		</div>
	</div>
</s:if>
<script type="text/javascript">
function getMandals(selectBox) {
	var organizationId = $("select#districtId").val();
	var url = jQuery.url.getChatURL("/signup/ajaxGetMandalsByDistrict.do");
	if (organizationId.length == 0) {
		alert("!Oops select Route.");
	} else {
		$("#mandalDiv")
				.html(
						'<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		var pars = "anyId=" + organizationId;
		$.ajax( {
			url : url,
			cache : false,
			data : pars,
			success : function(html) {
				$("#mandalDiv").html(html);
				document.getElementById('mandalDiv').style.display = "block";
				//document.getElementById('schoolBooksList').style.display="none";
		}
		});
	}
}
</script>



