<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Transaction Details
				</div>
			</div>
			<div class="portlet-body">
				<div class="tabbable tabbable-custom tabbable-full-width">
						<div>
							<s:form id="selectDealerForm" action="#" theme="simple"
								cssClass="form-horizontal">
								<div class="col-md-12">
									<div class="form-group">
										<label class="col-md-2 control-label">
											<span class="required">*</span>Select District :
										</label>
										<div class="col-md-8">
											<s:select id="districtId" list="tempList"
												cssClass="required form-control input-medium" listKey="id"
												listValue="districtName" headerKey="" theme="simple"
												headerValue="- Select -" name="selectedId"
												onchange="javascript:getDealersListByDistrict(this.value);" />
										</div>
									</div>
								</div>
							</s:form>
						</div>
						<%@ include file="/common/messages.jsp"%>
						<div class="spaceDiv"></div>
						<div id="searchDealersList"></div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function() {
	changePageTitle("Transaction Details");
});
function getDealersListByDistrict(districtId) {
  	if (districtId == "") {
		$("#searchDealersList").hide();
	} else {
		var pars = "tempId=" + districtId;
		$("#searchDealersList").html( '<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		var url = jQuery.url.getChatURL("/fps/ajaxFpsTransctionsByDistrictId.do");
		$.ajax( {
			url : url,
			cache : false,
			data : pars,
			success : function(html) {
				$("#searchDealersList").html(html);
				$("#searchDealersList").show();
			}
		});
	}
}
</script>
 
 