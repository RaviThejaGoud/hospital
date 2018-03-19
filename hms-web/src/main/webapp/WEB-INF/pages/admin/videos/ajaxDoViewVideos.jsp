<%@ include file="/common/taglibs.jsp"%>
<div class="row"  >
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>View Videos
				</div>
			</div>
			<div class="portlet-body">
				<div class="tabbable tabbable-custom tabbable-full-width">
					<ul class="nav nav-tabs">
						<s:if test='%{#session.previousYear == "N"}'>
							<s:if test='%{user.isOnlySchoolAdmin == "Y"}'>
								<li>
									<s:url id="addVideosurl" action="ajaxDoAddVideos" namespace="/common"></s:url>
									<sj:a href="%{addVideosurl}" targets="videosContentDiv" indicator="indicator" data-toggle="tab">Add Videos</sj:a>
								</li>
							</s:if>
						</s:if>
						<li class="active">
							<s:url id="viewVideosurl" action="ajaxDoViewVideos" namespace="/common"></s:url>
								<sj:a href="%{viewVideosurl}" targets="mainContentDiv" indicator="indicator" data-toggle="tab"> View Videos</sj:a>
						</li>
					</ul>
					<div id="videosContentDiv" class="tab-content">
						<jsp:include page="/WEB-INF/pages/admin/videos/ajaxViewVideos.jsp"></jsp:include>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	$(document).ready(function(){
		changePageTitle("Stock Maintenance");
	});
	$('html, body').animate({ scrollTop: $(document).height() - $(window).height() }, 10, function() {
	    $(this).animate({ scrollTop: 0 }, 10);
	});
</script>
