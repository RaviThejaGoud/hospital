<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i> Manage Tournament
				</div>
			</div>
			<div class="portlet-body">
				<div class="tabbable tabbable-custom tabbable-full-width">
					<ul class="nav nav-tabs">
						<s:if test='%{user.schoolAdmin || user.id == staff.account.id  && #session.previousYear == "N"}'>
							<li>
								<s:url id="urlAddTournament" action="ajaxDoAddTournament" namespace="/sports">
									<s:param name="tournamentVO.tournamentId" value="0" /> 
								</s:url>
								<sj:a id="addTournaments" href="%{urlAddTournament}" targets="tournamentContentDiv" data-toggle="tab">
								Add Tournament</sj:a>
							</li>
						</s:if>
						<li class="active">
							<s:url id="urlViewTournament" action="ajaxTournamentInformationHome" namespace="/sports"></s:url>
							<sj:a id="urlViewTournament" href="%{urlViewTournament}" targets="mainContentDiv" data-toggle="tab">
							View Tournament </sj:a>
						</li>
					</ul>
					<div id="tournamentContentDiv" class="tab-content">
						<%@ include file="/common/messages.jsp"%>
						<jsp:include
							page="/WEB-INF/pages/sports/ajaxViewTorunamentList.jsp"></jsp:include>
					</div>
				</div>
			</div>
		</div>
	</div>          
</div>
<script type="text/javascript">
$(document).ready(function() {
	changePageTitle("Tournament");
});
$('html, body').animate({ scrollTop: $(document).height() - $(window).height() }, 10, function() {
    $(this).animate({ scrollTop: 0 }, 10);
});
</script>
 