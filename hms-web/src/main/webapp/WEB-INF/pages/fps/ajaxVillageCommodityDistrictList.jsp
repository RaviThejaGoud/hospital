<%@ include file="/common/taglibs.jsp"%>
<s:form action="ajaxGetVillageCommodityDetails" theme="simple" id="villageCommodityDetails" 
	method="post" cssClass="form-horizontal" namespace="/fps" >
	<div class="form-body">
		<div class="form-group">
			<label class="control-label col-md-2">
				Select District :
			</label>
			<div class="col-md-3">
				<s:select list="tempList1" listKey="id"
					listValue="districtName" theme="simple" onchange="javascript:getMandalDetails(this.value);"
					cssClass="required form-control" name="districtId"
					headerKey="S" headerValue="- Select -" cssStyle="width: 100%">
				</s:select>
			</div>
		</div>
		<div id="mandalListDiv"></div>
		<div class="form-actions fluid">
			<div class="col-md-offset-2 col-md-9">
				<sj:submit targets="villageTransactions" value="Submit" 
				indicator="indicator" cssClass="submitBt btn blue" validate="true" />
			</div>
		</div>
	</div>
</s:form>
<div id="villageTransactions">
	<jsp:include page="/WEB-INF/pages/fps/ajaxVillageCommodityList.jsp"></jsp:include>
</div>
<script type="text/javascript">
	function getMandalDetails(districtId){
		if (isNonEmpty(districtId)) {
			$('#mandalListDiv').html(
							'<div align="center" style="padding:30px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
			var pars = "districtId=" + districtId;
			$.ajax({
				url : jQuery.url.getChatURL("/fps/ajaxGetMandalList.do"),
				cache : false,
				data : pars,
				success : function(response) {
					$('#mandalListDiv').html(response);
				}
			});
		}
	}
</script>
