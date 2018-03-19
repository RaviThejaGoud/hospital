<%@ include file="/common/taglibs.jsp"%>
<jsp:include page="/common/messages.jsp"></jsp:include>
<div class="form-body">
<s:if test="%{(objectList != null && !objectList.isEmpty())}">
		<h4 class="bold pageTitle">
			Available quantity in store
		</h4>
		<%-- <div class="form-group">
			<label class="control-label col-md-2">
				<span class="required"> * </span>Select Mess :
			</label>
			<div class="col-md-3">
				<s:select list="tempList" listKey="id" headerKey="" headerValue="- Select -" requiredposition="first"
					listValue="messName"
					cssClass="required form-control input-medium" id="selectedMessId" onchange="javascript:getIssueProvisionItemsToMess(this.value);"
					name="messId">
				</s:select>
			</div>
		</div> --%>
	<jsp:include page="/WEB-INF/pages/hostel/messManagement/ajaxGetAvailableItemsListInMess.jsp"></jsp:include>
	<div id="issueProvisionItemsToMessDivId"></div>
	</s:if>
		<s:else>
			<div class="alert alert-info">
				Currently there is no available quantity in store.
			</div>
		</s:else>
</div>
<script type="text/javascript">
$(document).ready(function(){
	FormComponents.init();
});

function getIssueProvisionItemsToMess(messId) {
	if(isNonEmpty(messId)){
		$('#issueProvisionItemsToMessDivId').html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		var pars = "tempId=" + messId;
		$.ajax( {
			url : jQuery.url.getChatURL("/hostel/ajaxGetAvailableItemsListInMess.do"),
			cache : false,
			data : pars,
			success : function(response) {
				$('#issueProvisionItemsToMessDivId').html(response);
			}
		});
	}else
		$('#issueProvisionItemsToMessDivId').html('<div class="alert alert-info">Please select Floor & Room.</div>');
}
</script>