<%@ include file="/common/taglibs.jsp"%>
<s:form id="addLibrarySettings" action="ajaxSaveLibrarySettings"
	method="post" theme="simple" cssClass="form-horizontal" namespace="/library">
	<div class="form-body">
		<h4 class="bold pageTitle">
			Create Library Settings
		</h4>
		<div class="row">
			<div class="col-md-9">
				<div class="form-group">
					<label class="control-label col-md-3">
						<span class="required">*</span>Max books allowed / Student :
					</label>
					<div class="col-md-9">
						<sj:textfield name="librarySettings.noOfStudentIssuBooks"
							cssStyle="width:270px;" id="studentBooks" 
							cssClass="required form-control input-medium " maxlength="2"></sj:textfield>
					</div>
				</div>
			</div>
			<div class="col-md-9">
				<div class="form-group">
					<label class="control-label col-md-3">
						<span class="required">*</span>Max books allowed / Staff :
					</label>
					<div class="col-md-9">
						<sj:textfield name="librarySettings.noOfStaffIssuBooks"
							id="staffBooks"  
							cssClass="required form-control input-medium"
							cssStyle="width:270px;" maxlength="2"></sj:textfield>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-9">
				<div class="form-group">
					<label class="control-label col-md-3">
						<span class="required"></span>Reservation Valid / Days :
					</label>
					<div class="col-md-9">
						<sj:textfield name="librarySettings.limitDays" id="fine"
							cssClass="form-control input-medium" cssStyle="width:270px;"
							maxlength="2"></sj:textfield>
					</div>
				</div>
			</div>
			<div class="col-md-9">
				<div class="form-group">
					<label class="control-label col-md-3">
						<span class="required"></span>Fine Amount Per Day :
					</label>
					<div class="col-md-9">
						<sj:textfield name="librarySettings.fineAmountPerDay" id="fine"
							cssClass="form-control input-medium" cssStyle="width:270px;"
							maxlength="2"></sj:textfield>
					</div>
				</div>
			</div>
		</div>
		<div class="form-actions fluid">
			<div class="col-md-6">
				<div class="col-md-offset-4 col-md-9">
					<sj:submit   cssClass="submitBt btn blue" value="Submit"
						onBeforeTopics="formValidationForSettings"
						targets="mainContentDiv" formIds="addLibrarySettings"
						validate="true" />
					<s:url id="doCancelBook" action="ajaxViewLibrarySettings" namespace="/library"/>
					<sj:a href="%{doCancelBook}" cssClass="btn default"
						targets="mainContentDiv">Cancel
						</sj:a>
				</div>
			</div>
		</div>
	</div>
</s:form>
<script type="text/javascript">
changePageTitle("Library Settings");
$(document).ready(function() {
	$.subscribe('formValidationForSettings', function(event, data) {
		if ($('#addLibrarySettings').valid())
			 $('button.close').click();
		else
			event.originalEvent.options.submit=false;
	});
	$('.numeric').numeric();
});
</script>
