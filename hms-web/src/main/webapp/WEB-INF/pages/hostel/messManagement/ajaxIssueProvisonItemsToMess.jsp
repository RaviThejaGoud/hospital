<%@ include file="/common/taglibs.jsp"%>
<jsp:include page="/common/messages.jsp"></jsp:include>
<div class="form-body">
<s:form id="addProvisionItemsFormId"
	action="ajaxIssueProvisonItemsToMess" theme="simple" name="myform"
	cssClass="form-horizontal" namespace="/hostel" method="post">
	<s:hidden name="tempString" cssClass="provistionItemsDataClass"></s:hidden>
		<span class="label label-danger"> NOTE : </span>&nbsp;
				You can add provision items to store.
		<h4 class="bold pageTitle">
			Issue Provision Items to Mess
		</h4>
		<div class="form-group">
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
		</div>
		<div class="form-group">
			<label class="control-label col-md-2">
				<span class="required"> * </span>Issue Date :
			</label>
			<div class="col-md-3">
				<div class="input-group input-medium date date-picker">
					<input type="text" readonly="readonly" class="form-control required" value='<s:property value="attendanceDate"/>'
						id="date0" name="attendanceDate">
					<span class="input-group-btn">
						<button type="button" class="btn default">
							<i class="fa fa-calendar"></i>
						</button> </span>
				</div>
				<span class="help-block">(MM/DD/YYYY)</span>
			</div>
		</div>
		<div id="issueProvisionItemsToMessDivId"></div>
</s:form>
</div>
<script type="text/javascript">
$(document).ready(function(){
	FormComponents.init();
});

function getIssueProvisionItemsToMess(messId) {
	if(isNonEmpty(messId)){
		$('#issueProvisionItemsToMessDivId').html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		var pars = "messId=" + messId;
		$.ajax( {
			url : jQuery.url.getChatURL("/hostel/ajaxGetProvisonItemsForIssueItems.do"),
			cache : false,
			data : pars,
			success : function(response) {
				$('#issueProvisionItemsToMessDivId').html(response);
			}
		});
	}
}
</script>