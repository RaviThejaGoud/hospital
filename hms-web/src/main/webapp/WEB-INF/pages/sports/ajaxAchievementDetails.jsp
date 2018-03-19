<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i> Sports Achievements
				</div>
			</div>
			<div class="portlet-body">
				<div class="tabbable tabbable-custom tabbable-full-width">
					<ul class="nav nav-tabs">
						<s:if test='%{user.schoolAdmin || user.id == staff.account.id && #session.previousYear == "N"}'>
							<li>
								<s:url id="urlAddAchievement" action="ajaxDoAddAchievements" namespace="/sports">
									<s:param name="Achievement.id" value="0" /> 
								</s:url>
								<sj:a id="addAchievement" href="%{urlAddAchievement}" targets="achievementContentDiv" data-toggle="tab">
								Add Achievements</sj:a>
							</li>
						</s:if>
						<li class="active">
							<s:url id="ViewAchievement" action="ajaxSportAchievementInfoHome" namespace="/sports"></s:url>
							<sj:a id="ViewAchievement" href="%{ViewAchievement}" targets="mainContentDiv" data-toggle="tab">
							View Achievements</sj:a>
						</li>
					</ul>
					<div id="achievementContentDiv" class="tab-content">
						<%@ include file="/common/messages.jsp"%>
						<jsp:include page="/WEB-INF/pages/sports/ajaxViewAchievementsList.jsp"></jsp:include>
					</div>
				</div>
			</div>
		</div>
	</div>          
</div>
<script type="text/javascript">
$(document).ready(function() {
	changePageTitle("Achievements");
});
$('html, body').animate({ scrollTop: $(document).height() - $(window).height() }, 10, function() {
    $(this).animate({ scrollTop: 0 }, 10);
});

</script>

