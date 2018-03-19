<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/messages.jsp"%>
<s:form action="ajaxAddorUpdateTournament" theme="simple" id="addTournment" method="post" cssClass="form-horizontal" namespace="/sports">
	<s:hidden name="tournamentVO.tournamentId" value="%{tournamentVO.tournamentId}" id="tournamentId" />
	<div class="form-body">
		<h4 class="pageTitle bold form-section">TOURNAMENT INFORMATION</h4>
			<div class="row">	
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4">
							<span class="required">*</span>Tournament Name :
						</label>
						<div class="col-md-5">
							<sj:textfield name="tournamentVO.tournamentName" id="tournamentName"
								cssClass="required form-control input-medium" maxlength="128" />
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label class="col-md-4 control-label"> <span
							class="required">*</span>Select Team :
						</label>
						<div class="col-md-6">
							<s:select list="teamList" listKey="id" id="teamId" listValue="teamWithSport" theme="simple" cssClass="required form-control input-medium"
								name="tournamentVO.teamVO.id" headerKey="" headerValue="- Select Team -" >
							</s:select>
						</div>
					</div>
				</div>
				</div>
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4"><span
							class="required">*</span> Start Date : </label>
							<div class="col-md-5">
								<div data-date-format="mm/dd/yyyy"
									class="input-group input-medium date date-picker required">
									<input type="text" id="contractStartDate" readonly="" class="form-control required" onchange="verifyTourContactDate();" value='<s:property value="tournamentVO.startDateStr"/>' 
									tabindex="3" name="tournamentVO.startDate">
									 <span class="input-group-btn">
										<button type="button" class="btn default">
											<i class="fa fa-calendar"></i>
										</button>
									</span>
								</div>
								<span class="help-block">(MM/DD/YYYY)</span>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4"><span
							class="required">*</span> End Date : </label>
							<div class="col-md-6">
								<div data-date-format="mm/dd/yyyy"
									class="input-group input-medium date date-picker">
									<input type="text" id="contractEndDate" readonly="" class="form-control required" onchange="verifyTourContactDate();" tabindex="4" name="tournamentVO.endDate" 
									value='<s:property value="tournamentVO.endDateStr"/>' > <span class="input-group-btn">
										<button type="button" class="btn default">
											<i class="fa fa-calendar"></i>
										</button>
									</span>
								</div>
								<span class="help-block">(MM/DD/YYYY)</span>
							</div>
						</div>
					</div>
				</div>
				<h4 class="pageTitle bold form-section">LOCATION</h4>
				<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> Address Line1 : </label>
						<div class="col-md-8">
							<sj:textfield name="tournamentVO.addressVO.addressLine1" id="addressLine1"
								cssClass="form-control input-medium as-input" maxlength="255"></sj:textfield>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> Address Line2 : </label>
						<div class="col-md-8">
							<sj:textfield name="tournamentVO.addressVO.addressLine2" id="addressLine2"
								cssClass="form-control input-medium as-input" maxlength="255"></sj:textfield>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> City : </label>
						<div class="col-md-8">
							<sj:textfield name="tournamentVO.addressVO.city" id="city" cssClass="form-control input-medium as-input" maxlength="22"></sj:textfield>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> State : </label>
						<div class="col-md-8">
							<s:select id="state" list="statesList"  cssClass="form-control input-medium" listKey="stateCode"
								listValue="stateName" headerKey="" headerValue="- Select -" name="tournamentVO.addressVO.state" />  <!--  onchange="javascript:getCastDetailsByState(this);"  -->
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> Pincode : </label>
						<div class="col-md-8">
							<sj:textfield name="tournamentVO.addressVO.postalCode" id="pincode"  cssClass="numeric form-control input-medium " maxlength="6" >
							</sj:textfield>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> 
						Attach Schedule :
						</label>
						<div class="col-md-8">
							<span class="btn default"><s:file name="tournamentVO.fileUpload" id="enrollmentForm" onchange=""  cssClass="fileName" multiple="multiple" >
							</s:file></span>
						</div>
					</div>	
					<table>
						<tbody>
							<s:iterator value="tournamentVO.attachmentsVO">
								<tr class="template-upload fade in">
									<div class="row">
										<div class="col-md-9">
											<div class="col-md-11">
												<div class="form-group" style="float:right ; width:276px;">
													<a target="_new" href="<s:property value="filePath" />"><s:property value="fileName" /> </a>
												</div>
											</div>
										</div>
										<s:url id="removeAttachmentSchedule" action="ajaxRemoveTournamentAttachments" includeParams="all" escapeAmp="false" namespace="/sports">
											<s:param name="attachmentId" value="%{id}" />
										</s:url>
										 <s:div cssClass="btn btn-xs red emsFileRemove"  id='%{removeAttachmentSchedule}' title="Delete this Attachment"  targets="tournamentContentDiv">
												<i class="fa fa-trash-o"></i>
										</s:div> 
									</div>
								</tr>
							</s:iterator>
						</tbody>
					</table>
				</div>
			</div>
		<div class="form-actions fluid">
			<div class="col-md-6">
				<div class="col-md-offset-4 col-md-9">
					<sj:submit cssClass="submitBt btn blue" value="Submit"  id="clickSubmit"  validate="true" indicator="indicator" targets="mainContentDiv" formIds="addTournment" />
					<s:url id="doViewTournament" action="ajaxTournamentInformationHome" includeParams="all" escapeAmp="false" namespace="/sports">
					</s:url>
					<sj:a href="%{doViewTournament}" cssClass="btn default" indicator="indicator" targets="mainContentDiv">Cancel</sj:a>
				</div>
			</div>
		</div>
	</div>
</s:form>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/subscription/textcounter.js"></script>
<script  type="text/javascript" src="${pageContext.request.contextPath}/plugins/bootstrap-switch/static/js/bootstrap-switch.min.js"></script>	
<script type="text/javascript">
$(document).ready(function() {
	$('.numeric').numeric();
/* 	$('.numeric').numeric( {allow : "."}); */
	FormComponents.init();
	FormAdvanced.init();
	
});

$(function() {
	if ($('div.emsFileRemove')) {
		$('div.emsFileRemove').unbind('click');
		$("div.emsFileRemove").click(function() {
			confirmDeleteDocument(this);
		});
	}
});
function confirmDeleteDocument(event) {
	thishref = $(event).attr('id');
	var filename = thishref.split("=");
	if ($(event).next('.question').length <= 0) {
		$(event).after('<div class="question">Are You Sure?<br/> <span class="yes">Yes</span><span class="cancel">Cancel</span></div>');
	}
	$(event).next('.question').animate({
		opacity : 1
	}, 300);
	$('.yes').unbind('click');
	$('.yes').bind('click', function() {
		var prdDiv = $(this).parents('.question');
		prdDiv.html('Processing...');
		$.ajax({
			url : thishref,
			cache : false,
			success : function(html) {
				// deleteFile(filename);
				prdDiv.parent().parent().remove();
				$('#tournamentContentDiv').html(html);
				//prdDiv.remove();
			}
		});
	});
	$('.cancel').unbind('click');
	$('.cancel').bind('click', function() {
		$(this).parents('.question').fadeOut(300, function() {
			$(this).remove();
		});
		return false;
	});
}
function verifyTourContactDate() {
	var contractStartDate = $('#contractStartDate').val();
	var contractEndDate = $('#contractEndDate').val();
	if (isNonEmpty(contractEndDate) && isNonEmpty(contractStartDate)) {
		contractStartDate = new Date(contractStartDate);
		contractEndDate = new Date(contractEndDate);
		if (contractEndDate < contractStartDate) {
			$('#contractEndDate').val('');
			alert("End Date should be more than start date.");
		}
	}
}

</script>
