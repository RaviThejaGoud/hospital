<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/messages.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i> <span class="hidden-title">Master Accounts Report</span>
				</div>
			</div>
			<div class="portlet-body">
				<div class="dropdown tabbable tabbable-custom tabbable-full-width">

					<div class="tab-content" id="FeeCollectionDetailsDiv">
						<s:form action="ajaxDownloadAccountReportCategory" theme="simple" id="allAccountReportCategory" 
							method="post" cssClass="form-horizontal"  namespace="/account">
							<div class="form-body">
								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											<label class="control-label col-md-4"> <span
												class="required">*</span>Select Report Type :
											</label>
											<div class="col-md-5">
												<s:select id="bGroup" cssClass="form-control input-medium" headerKey="" headerValue="- Select -" name="tempString"
												list="#{'A':'All Accounts','AC':'Account By Category','AS':'Account By Statement Type','AV':'All Vendors','VC':'Vendor Category'}" />
											</div>
										</div>
									</div>									
								</div>
								
								<div class="form-actions fluid">
									<div class="col-md-offset-3 col-md-9">
										<s:submit type="submit small" value="Generate Excel"
											onclick="reportType()" cssClass="submitBt btn blue long"
											title="generate report" />
									</div>
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
changePageTitle("Account Reports");
</script>