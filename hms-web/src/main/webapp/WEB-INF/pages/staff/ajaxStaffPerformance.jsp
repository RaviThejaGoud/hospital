<%@ include file="/common/taglibs.jsp"%>
<jsp:include page="/common/messages.jsp"></jsp:include>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Performance Evaluation
				</div>
			</div>
			<div class="portlet-body">
				<div class="tabbable tabbable-custom tabbable-full-width">
					<ul class="nav nav-tabs">
						<li>
							<s:url id="urlSubjectsComparison"
								action="ajaxMySubjectsComparison" escapeAmp="false"
								includeParams="all" namespace="/staff">
								<s:param name="staff.id" value="0"></s:param>
							</s:url>
							<sj:a href="%{urlSubjectsComparison}" targets="marksTemplate"
								indicator="indicator" data-toggle="tab">My Subjects Comparison</sj:a>
						</li>
						<li class="active">
							<s:url id="urlSubjectsPerformance"
								action="ajaxStaffPerformance" escapeAmp="false"
								includeParams="all" namespace="/staff">
							</s:url>
							<sj:a href="%{urlSubjectsPerformance}" targets="mainContentDiv"
								indicator="indicator" data-toggle="tab">View Performance</sj:a>
						</li>
					</ul>
					<div class="tab-content" id="marksTemplate">
						<jsp:include page="/WEB-INF/pages/staff/staffPerformance.jsp" />
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
 <script type="text/javascript">
 $(document).ready(function() {
 changePageTitle("Manage Staff Activities");
	$('ul.sub-menu li').removeClass('active');
	$("li#PerformanceDIV").addClass('active');
	});
</script>