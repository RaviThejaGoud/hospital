<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Admin--->Transport Maintenance
				</div>
			</div>
			<div class="portlet-body">
				<div id="mainContentDiv" class="tab-content">
					<s:form action="ajaxGetTransportMaintenance" theme="simple"
						cssClass="form-horizontal" namespace="/reports"
						onsubmit="return generateMothIds();" id="classAndTodate"
						method="post">
						<s:hidden name="tempString"></s:hidden>
						<s:hidden name="anyId" cssClass="anyId" value=""></s:hidden>
						<s:hidden name="plTitle"></s:hidden>
						<s:hidden id="classNameIds" name="SelectedId" />
						<s:hidden id="monthNameIds" name="anyTitle" />
						<s:if test="%{monthNamesList != null && !monthNamesList.isEmpty()}">
						<div class="form-body">
							<div class="form-group">
								<label class="conLable col-md-3 control-label">
								</label>
								<div class="col-md-12">
									<div class="checkbox-list">
										<label class="checkbox-inline">
											<input type="checkbox" name="" value=""
												onClick="checkAllMonths()" class="checkbox allmonths">
											All Months
										</label>
									</div>
									<s:checkboxlist name="chkBoxMonthIds" list="monthNamesList"
										listValue="key" theme="ems" cssClass="small" />
								</div>
							</div>
							<div class="form-actions fluid">
								<div class="col-md-6">
									<div class="col-md-offset-3 col-md-9">
										<s:submit type="submit small" value="Generate"
											onclick="getFormateType()" cssClass="submitBt btn green"
											title="generate report">
										</s:submit>
									</div>
								</div>
							</div>
						</div>
						</s:if>
						<s:else>
							<div class="alert alert-info">Currently there are no months.
							</div>
						</s:else>
					</s:form>
				</div>
			</div>
		</div>
	</div>
</div>
<div id="schoolTermlist" class="grid_9"></div>
<script type="text/javascript">
$(document).ready(
		function() {
		 $("input:checkbox, input:radio").uniform();
			changePageTitle("Transport Maintenance");
			$("input[name=chkBoxMonthIds]").click(function() {
				if ($("input[name=chkBoxMonthIds]:unchecked").length > 0) {
					$(".allmonths").parent('span').removeClass("checked");
					$(".allmonths").attr("checked", false);
				} else {
					$(".allmonths").parent('span').addClass("checked");
					$(".allmonths").attr("checked", true);
				}
			});
		});
function getFormateType() {
	$('.anyId').val('PDF');
} 

function checkAllMonths() {
		if ($(".allmonths").is(':checked')){
		    $("[name='chkBoxMonthIds']").each(function(){
			   $(this).parent('span').addClass('checked');
			    $(this).attr("checked", "true");
			});
		}
		else{
		 $("[name='chkBoxMonthIds']").each(function(){
			   $(this).parent('span').removeClass('checked');
			    $(this).removeAttr("checked");
			});
		}	
	} 
function generateMothIds() {
	if ($("input[name=chkBoxMonthIds]:checked").length > 0) {
		var monthIds = $("input[name=chkBoxMonthIds]:checked");
	 	var selectedMonthIds = '';
		if (monthIds.length > 0) {
			selectedMonthIds = '';
			for ( var i = 0; i < monthIds.length; i++) {
				selectedMonthIds += monthIds[i].value + ',';
			}
			selectedMonthIds += '';
		}
		$("#monthNameIds").val(selectedMonthIds);
		return true;
	} else if ($("input[name=chkBoxSelectedIds]:checked").length == 0
			|| $("input[name=chkBoxMonthIds]:checked").length == 0) {
		if ($("input[name=chkBoxSelectedIds]:checked").length == 0) {
			alert("Please select at least one month");
		} else {
			alert("Please select at least one month");
		}
		return false;
	} else {
		return false;
	}
}
</script>