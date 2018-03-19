<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Scorecard
				</div>
			</div>
			<div class="portlet-body">
				<div class="tabbable tabbable-custom tabbable-full-width">
					<s:if test="%{(anyTitle !='Y')}">
						<ul class="nav nav-tabs">
							<%-- <li>
								<s:url id="urlDoProgressReport"
									action="ajaxDoGetStudentProgressReport" namespace="/exam"/>
								<sj:a id="doProgressReport" href="%{urlDoProgressReport}"
									title="It generates predefined score card."
									targets="scoreCardCont"  data-toggle="tab">Default Scorecard</sj:a>
							</li> --%>
							<li>
								<s:url id="urlDoScorecardSettings"
									action="ajaxDoUploadScoreCardTemplate" namespace="/exam">
								</s:url>
								<sj:a id="doScoreCard" href="%{urlDoScorecardSettings}"
									targets="scoreCardCont" data-toggle="tab">Upload Templates</sj:a>
							</li>
							<li class="active">
								<s:url id="urlDoScoreCard"
									action="ajaxManageScorecardGeneration" namespace="/exam" />
								<sj:a  href="%{urlDoScoreCard}"
									targets="mainContentDiv" data-toggle="tab">Generate Scorecard</sj:a>
							</li>
						</ul>
					</s:if>
					<div class="tab-content" id="scoreCardCont">
			         <jsp:include page="/WEB-INF/pages/exam/scorecard/ajaxGenerateScoreCard.jsp" />
	              </div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
$("input:checkbox, input:radio").uniform();
	$(document).ready(function() {
	$("input:checkbox, input:radio").uniform();
		changePageTitle('Score Card Genaration');
		FormAdvanced.init();
	});
</script>