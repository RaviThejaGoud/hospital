<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>View Events
				</div>
			</div>
			<div class="portlet-body">
				<div class="tabbable tabbable-custom tabbable-full-width">
					<ul class="nav nav-tabs">
						<s:if test='%{#session.previousYear == "N"}'>
							<li>
								<s:url id="urlEvents" action="ajaxDoAddEvents" namespace="/alumnee" /> 
								<sj:a href="%{urlEvents}" targets="eventsDiv" indicator="indicator" data-toggle="tab">Add Events</sj:a>
							</li>
							<li>
								<s:url id="viewEvents" action="ajaxMyEvents" namespace="/alumnee">
								</s:url> <sj:a href="%{viewEvents}" targets="eventsDiv" data-toggle="tab">My Events</sj:a>
							</li>
							<li class="active">
								<s:url id="urlViewEvents" action="ajaxViewEvents" namespace="/alumnee" /> <sj:a
									href="%{urlViewEvents}" targets="mainContentDiv" indicator="indicator" data-toggle="tab">View Events</sj:a>
						    </li>
						  </s:if>
					  </ul>
					<div id="eventsDiv" class="tab-content">
						<jsp:include page="/common/messages.jsp" />
							<jsp:include page="/WEB-INF/pages/alumnee/viewEvents.jsp"></jsp:include>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function() {
	changePageTitle("Performance Evaluation");
});
</script>
