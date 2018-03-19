<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i> <span class="hidden-title">Configure
						Auto Reports</span>
				</div>
			</div>
			<div class="portlet-body">
				<div class="dropdown tabbable tabbable-custom tabbable-full-width">
					<div class="tab-content">
						<%@ include file="/common/messages.jsp"%>
						<s:form action="ajaxConfigureAutoReportsForm" theme="simple" id="autoReportsForm" method="post"
							cssClass="form-horizontal">
							<s:hidden id="userReportData" name="tempString"></s:hidden>
							<s:iterator value="objectList">
								<div class="form-group">
									<label class="col-md-1 control-label"> 
										<s:if test='%{status == "Y"}'>
											<input type="checkbox" name="chkBoxSelectedIds" value="<s:property value='%{autoReportsTypes.id}'/>" class="checkbox"  checked="checked"
													id="autoReportType<s:property value='%{autoReportsTypes.id}'/>" title="<s:property value='autoReportsTypes.id'/>-<s:property value='id'/>">
										</s:if>
										<s:else>
											<input type="checkbox" name="chkBoxSelectedIds" value="<s:property value='%{autoReportsTypes.id}'/>" class="checkbox" 
												id="autoReportType<s:property value='%{autoReportsTypes.id}'/>" title="<s:property value='autoReportsTypes.id'/>-<s:property value='id'/>">
										</s:else>
										 
										
									</label>
									<div class="col-md-10">
										<label class="control-label"> 
											<s:property value="autoReportsTypes.reportName" />
										</label> 
										<span class="help-block">[<s:property value="autoReportsTypes.reportDescription" />]</span>
									</div>
								</div>
							</s:iterator>
							<div class="form-actions fluid">
								<div class="col-md-offset-2 col-md-9">
									
									<sj:submit cssClass="submitBt btn blue" value="Submit"
										indicator="indicator" targets="mainContentDiv"
										onBeforeTopics="autoReportsFormValidation" validate="true" 
										formIds="autoReportsForm" />
								</div>
							</div>
						</s:form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	changePageTitle("Configure Auto Reports");
	$(document).ready(function() {
		$("input:checkbox, input:radio:not('.toggle')").uniform();

	});
	$.subscribe('autoReportsFormValidation', function(event, data) {
		var jsonObj = [];
		$('input[name=chkBoxSelectedIds]').each(function() {
			var status = null;
			var checked = $('#autoReportType'+ $(this).val()).is(":checked");
			var ids = $(this).attr('title').split('-');
			if (checked){
				status = 'Y';
			}else{
				status = 'N';
			}
			jsonObj.push({
				"reportTypeId" : ids[0],
				"userReportId" : ids[1],
				"status" : status
			});
		});
		$("#userReportData").val(JSON.stringify(jsonObj));
	});
</script>