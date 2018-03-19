<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Manage Sports
				</div>
			</div>
			<div class="portlet-body">
				<div class="tabbable tabbable-custom tabbable-full-width">
					<ul class="nav nav-tabs">
						<li>
							<s:if test='%{user.schoolAdmin}'>
								<s:url id="urlAddSports" action="ajaxGetCoachToSports" namespace="/sports">
									<s:param name="sportsVO.id" value="0" />
								</s:url>
								<sj:a id="addSprts" href="%{urlAddSports}" targets="sportsContentDiv" data-toggle="tab">Add Sports</sj:a>
							</s:if>
						</li>
						<li class="active">
							<s:url id="viewSports" action="ajaxSportsInformationHome" namespace="/sports"></s:url>
							<sj:a id="viewSports" href="%{viewSports}" targets="mainContentDiv" data-toggle="tab">View Sports Details</sj:a>
						</li>
					</ul>
					<div id="sportsContentDiv" class="tab-content">
						<%@ include file="/common/messages.jsp"%>
						<jsp:include page="/WEB-INF/pages/sports/ajaxViewSprotsList.jsp"></jsp:include>
					</div>
				</div>
			</div>
		</div>
	</div>          
</div>
<script type="text/javascript">
	$(document).ready(function() {
		changePageTitle("Sports");
	});
	$('html, body').animate({ scrollTop: $(document).height() - $(window).height() }, 10, function() {
	    $(this).animate({ scrollTop: 0 }, 10);
	});
</script>