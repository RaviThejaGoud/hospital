<%@ include file="/common/taglibs.jsp"%>

<div>
<jsp:include page="/common/messages.jsp" />

	<s:if test="%{financialYearList != null && !financialYearList.isEmpty()}">
		<s:form id="selectStudentForm" action="#" theme="simple" cssClass="form-horizontal">
			<div class="form-group">
				<label class="col-md-3 control-label"> Select Financial Year : </label>
				<div class="col-md-9">
					<s:select list="financialYearList" id="finYearId" cssClass="form-control input-medium" name="financialYearVO.id" listKey="id"
						listValue="yearName" headerKey="" headerValue="- Select Financial Year -" theme="simple" onchange="javascript:getFinancialYear(this.value,'S');" />
				</div>
			</div>
		</s:form>
	</s:if>
	<div id="finYearAccountDetails"></div>
</div>
<script type="text/javascript">
TableAdvanced.init();
$(document).ready(function() {
	var finYearId = $('#finYearId').val();
	if (isNonEmpty(finYearId)) {
		getFinancialYear(finYearId,"L");
	} /* else {
		$('#finYearAccountDetails').html('<div class="alert alert-info">Please select financial year.</div>');
		$("#finYearAccountDetails").show();
	} */
});
function getFinancialYear(finYearId,loadVal) {
	if (finYearId != 0) {
		var pars = "financialYearVO.id=" + finYearId;
		$("#finYearAccountDetails").html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		if(loadVal=='S')
		$('div.alert-success').hide();
		var url = jQuery.url.getChatURL("/account/ajaxGetFinYearAccountDetails.do");
		$.ajax( {
			url : url,
			cache : false,
			data : pars,
			success : function(html) {
				$("#finYearAccountDetails").html(html);
				$("#finYearAccountDetails").show();
			}
		});
	  }
	  else {
			$('#finYearAccountDetails').html('<div class="alert alert-info">Please select financial year.</div>');
		}
	}
 </script>