<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>My Events
				</div>
			</div>
			<div class="portlet-body">
				<div class="tabbable tabbable-custom tabbable-full-width">
					<ul class="nav nav-tabs">
						<li><s:url id="viewEventPhotos" action="ajaxViewEventPhotos"
								namespace="/admin">
							</s:url> <sj:a href="%{viewEventPhotos}" targets="eventsContentDiv"
								data-toggle="tab">View Photos</sj:a></li>
						<li class="active"><s:url id="viewEvents"
								action="ajaxViewEvents" namespace="/admin">
							</s:url> <sj:a href="%{viewEvents}" targets="mainContentDiv"
								data-toggle="tab">View Events</sj:a></li>
					</ul>
					<div id="eventsContentDiv" class="tab-content">
						<jsp:include
							page="/WEB-INF/pages/student/viewStudentAcceptedEventsLists.jsp"></jsp:include>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	$(document).ready(function() {
		changePageTitle("Events");
	});
</script>