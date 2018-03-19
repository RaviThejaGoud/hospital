<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>
					<span class="hidden-title"> </span>
				</div>
			</div>
			<div class="portlet-body">
				<div class="tabbable tabbable-custom tabbable-full-width">
					<ul class="nav nav-tabs">
						<li class="dropdown active">
							<a data-hover="dropdown" data-toggle="dropdown"
								class="dropdown-toggle js-activated" href="#"> Class wise Timetable <b class="caret"></b> </a>
							<ul role="menu" class="dropdown-menu pull-right">
								<li class="active">
									<s:url id="urlStaffTimeTable"
										action="ajaxDoDownloadTimeTableReport" includeParams="all"
										escapeAmp="false"  namespace="/reports"/>
									<sj:a href="%{urlStaffTimeTable}" targets="mainContentDiv"
										data-toggle="tab">Class wise Timetable</sj:a>
								</li>
								<s:if test='%{#session.previousYear=="N"}'>
									<li>
										<s:url id="urlClassTimeTable"
											action="ajaxDoGenerateStaffTimeTable" includeParams="all"
											escapeAmp="false" namespace="/reports">
										</s:url>
										<sj:a href="%{urlClassTimeTable}" indicator="indicator"
											targets="examContentInfo" button="false">Staff wise Timetable </sj:a>
									</li>
								</s:if>
							</ul>
						</li>
					</ul>
					<div id="examContentInfo" class="tab-content">
						<jsp:include
							page="/WEB-INF/pages/admin/reports/ajaxGenerateTimeTableReportList.jsp"></jsp:include>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function() {
	$('.js-activated').dropdownHover().dropdown();
});
</script>