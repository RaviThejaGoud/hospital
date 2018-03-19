<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>View Discussions
				</div>
			</div>
			<div class="portlet-body">
				<div class="tabbable tabbable-custom tabbable-full-width">
					<ul class="nav nav-tabs">
							<li>
								<s:url id="urlDiscussions" action="ajaxDoAddDiscussions" namespace="/alumnee" /> 
								<sj:a href="%{urlDiscussions}" targets="discussionsDiv" indicator="indicator" data-toggle="tab">New Discussions</sj:a>
							</li>
							<li>
								<s:url id="viewDiscussions" action="ajaxMySocialDiscussions" namespace="/alumnee">
								</s:url> <sj:a href="%{viewDiscussions}" targets="discussionsDiv" data-toggle="tab">My Discussions</sj:a>
							</li>
							<li class="active">
								<s:url id="urlViewDiscussions" action="ajaxDiscussionsHome" namespace="/alumnee" /> <sj:a
									href="%{urlViewDiscussions}" targets="mainContentDiv" indicator="indicator" data-toggle="tab">View Discussions</sj:a>
						    </li>
					  </ul>
					<div id="discussionsDiv" class="tab-content">
							<jsp:include page="/common/messages.jsp" />
							<jsp:include page="/WEB-INF/pages/alumnee/viewDiscussions.jsp"></jsp:include>
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
