<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Manage Team 
				</div> 
			</div>
			<div class="portlet-body">
				<div class="tabbable tabbable-custom tabbable-full-width">
					<ul class="nav nav-tabs">
						<s:if test='%{user.schoolAdmin || user.id == staff.account.id  && #session.previousYear == "N"}'>
							<li>
								<s:url id="urlAddTeam" action="ajaxGetSportsToTeam" namespace="/sports">
									<s:param name="team.id" value="0" /> 
								</s:url>
								<sj:a id="urlAddTeam" href="%{urlAddTeam}" targets="teamContentDiv" data-toggle="tab">
								Add Team</sj:a>
								
							</li>
						</s:if>
						<li class="active">
							<s:url id="urlViewTeam" action="ajaxTeamInformationHome" namespace="/sports"></s:url>
							<sj:a id="viewTeam" href="%{urlViewTeam}" targets="mainContentDiv" data-toggle="tab">
							View Team Details</sj:a>
						</li>
					</ul>
					<div id="teamContentDiv" class="tab-content">
						<%@ include file="/common/messages.jsp"%>
						<jsp:include page="/WEB-INF/pages/sports/ajaxViewSportsTeamList.jsp"></jsp:include>
					</div>
				</div>
			</div>
		</div>
	</div>          
</div>
<script type="text/javascript">
	$(document).ready(function() {
		changePageTitle("SportsTeam");
	});
	$('html, body').animate({ scrollTop: $(document).height() - $(window).height() }, 10, function() {
	    $(this).animate({ scrollTop: 0 }, 10);
	});
</script>