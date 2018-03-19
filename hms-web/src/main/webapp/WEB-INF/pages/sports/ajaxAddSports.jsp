<%@ include file="/common/taglibs.jsp"%>
<s:form action="ajaxAddSports" theme="simple" id="addSports" method="post" cssClass="form-horizontal" namespace="/sports">
	<s:hidden name="sportsVO.id" value="%{sportsVO.id}" id="SportsId" />
	<div class="form-body">
		
		<h4 class="pageTitle bold form-section">SPORTS INFORMATION</h4>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4"> <span
						class="required">*</span> Sports Name :
					</label>
					<div class="col-md-7">
						<sj:textfield name="sportsVO.sportName" id="spName" cssClass="required form-control input-medium" maxlength="40" />
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4"><span
						class="required">*</span> No.of Players : </label>
					<div class="col-md-5">
						<sj:textfield name="sportsVO.numberOfPlayers" id="playersNum" cssClass="numeric form-control input-medium required" maxlength="10" />
					</div>
				</div>
			</div>

		</div>
		<div class="row">
			<div class="col-md-12">
				<div class="form-group">
					<label class="col-md-2 control-label"> Select Coach : </label>
					<div class="col-md-9">
						<s:checkboxlist name="sportsVO.coachIds" list="objectList" listKey="staffId" listValue="fullName" theme="ems" />
					</div>
				</div>
			</div>
		</div>
		<div class="form-actions fluid">
			<div class="col-md-6">
				<div class="col-md-offset-4 col-md-9">
					<sj:submit cssClass="submitBt btn blue" value="Submit" id="clickSubmit" onBeforeTopics="sportsFormValidation"
						validate="true" indicator="indicator" targets="mainContentDiv"
						formIds="addSports" />
					<!--  onBeforeTopics="sportsFormValidation" -->

						<s:url id="doViewSports" action="ajaxSportsInformationHome" includeParams="all" escapeAmp="false" namespace="/sports">
						</s:url>
						<sj:a href="%{doViewSports}" cssClass="btn default" indicator="indicator" targets="mainContentDiv">
						Cancel</sj:a>
				</div>
			</div>
		</div>
	</div>
</s:form>
<script type="text/javascript">
$(document).ready(function() {
	$("input:checkbox, input:radio").uniform();
	$('.numeric').numeric();
	$.destroyTopic('sportsFormValidation');
	$("input#spName").autoCheck("${pageContext.request.contextPath}/sports/ajaxCheckSportNameAvailableOrNot.do",
		{
			minChars : 3,
			min : "no"
		});
});
$.subscribe('sportsFormValidation', function(event, data) {

	var notValid = "No";
	$('p.word-taken').each(function() {
		if ($(this).html() == 'Already taken!!!') {
			notValid = "Yes";
			event.originalEvent.options.submit = false;
		}
	});

	$('p.word-available').each(function() {
		if ($(this).html() == 'Available') {
			$('button.close').click();
			return true;
		}
	});
});
</script>
