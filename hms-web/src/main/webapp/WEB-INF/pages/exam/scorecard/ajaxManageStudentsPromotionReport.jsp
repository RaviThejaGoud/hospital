<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Manage Promotion reports
				</div>
			</div>
			<div class="portlet-body">
				<div class="tabbable tabbable-custom tabbable-full-width">
		    <ul class="nav nav-tabs">
						<li>
							<s:url id="urlDoPromotionReportSettings"
								action="ajaxDoUploadPromotionReportTemplate" namespace="/exam">
							</s:url>
							<sj:a id="promotionTemplate"
								href="%{urlDoPromotionReportSettings}"
								targets="promotionReportContDiv" data-toggle="tab">Upload Promotion Report Template</sj:a>
						</li>
						<li class="active">
							<s:url id="promotionReportNav"
								action="ajaxManagePromotionReports" namespace="/exam" />
							<sj:a  href="%{promotionReportNav}"
								targets="mainContentDiv" data-toggle="tab" >
								 View Promotion Reports</sj:a>
						</li>
					</ul>
		<div class="tab-content" id="promotionReportContDiv">
		<jsp:include page="/WEB-INF/pages/exam/scorecard/ajaxGeneratePromotionReport.jsp" />
    </div>
	</div>
</div>
</div></div></div>
<script type="text/javascript">
	$(document).ready(function() {
		changePageTitle('Promotion Report Genaration');
	});
</script>