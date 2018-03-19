<%@ include file="/common/taglibs.jsp"%>
	<div class="form-body">
		<div class="form-group">
			<label class="control-label col-md-2">
				Select Mandal :
			</label>
			<div class="col-md-3">
				<s:select list="mandalList" listKey="id"
					listValue="mandalName" theme="simple" onchange="javascript:getVillageDetails(this.value);"
					cssClass="required form-control" name="mandalId"
					headerKey="S" headerValue="- Select -" cssStyle="width: 100%">
				</s:select>
			</div>
		</div>
	</div>
	<div id="villageListDiv"></div>
<script type="text/javascript">
	function getVillageDetails(mandalId){
		if (isNonEmpty(mandalId)) {
			$('#villageListDiv').html(
							'<div align="center" style="padding:30px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
			var pars = "mandalId=" + mandalId;
			$.ajax({
				url : jQuery.url.getChatURL("/fps/ajaxGetVillageList.do"),
				cache : false,
				data : pars,
				success : function(response) {
					$('#villageListDiv').html(response);
				}
			});
		}
	}
</script>
	