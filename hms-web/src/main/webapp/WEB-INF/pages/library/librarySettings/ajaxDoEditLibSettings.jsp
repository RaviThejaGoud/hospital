<%@ include file="/common/taglibs.jsp"%>
<div data-width="760"  class="modal fade modal-overflow in" id="popupEditLibrarySettings" style="display: block; width: 760px; margin-left: -379px; margin-top: 200px;" aria-hidden="false">
	<s:form action="ajaxUpdateLibSettings" theme="simple" id="editSettingBooks" method="post" cssClass="form-horizontal" namespace="/library">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true"></button>
			<h4 class="bold pageTitle">
				Update Settings
			</h4>
		</div>
		<s:hidden name="tempId" value="%{librarySettings.id}"></s:hidden>
		<div class="modal-body">
			<div class="form-body">
				<div class="form-group">
					<label class="control-label col-md-3">
						<span class="required">*</span>Max Books Allowed/Student :
					</label>
					<div class="col-md-9">
						<sj:textfield name="librarySettings.noOfStudentIssuBooks"
							id="studentBooks"
							cssClass="numeric required form-control input-medium as-input"
							maxlength="2" />
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-md-3">
						<span class="required">*</span>Max Books Allowed/Staff :
					</label>
					<div class="col-md-9">
						<sj:textfield name="librarySettings.noOfStaffIssuBooks"
							id="staffBooks" cssClass="numeric form-control input-medium as-input"
							maxlength="2" />
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-md-3">
						<span class="required"></span>Reservation Valid / Days :
					</label>
					<div class="col-md-9">
						<sj:textfield name="librarySettings.limitDays" id="fine"
							cssClass="numeric required form-control input-medium as-input"
							maxlength="2" />
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-md-3">
						<span class="required"></span>Fine Amount Per Day :
					</label>
					<div class="col-md-9">
						<sj:textfield name="librarySettings.fineAmountPerDay" id="fine"
							cssClass="numeric required form-control input-medium as-input"
							maxlength="2" />
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-md-3">
						<span class="required"></span>No of Due Days For Return/Student :
					</label>
					<div class="col-md-9">
						<sj:textfield name="librarySettings.noOfDaysForReuturnStudents" id="returnStudent"
							cssClass="numeric required form-control input-medium as-input"
							maxlength="2" />
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-md-3">
						<span class="required"></span>No of Due Days For Return/Staff :
					</label>
					<div class="col-md-9">
						<sj:textfield name="librarySettings.noOfDaysForReuturnStaff" id="returnStaff"
							cssClass="numeric required form-control input-medium as-input"
							maxlength="2" />
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<div class="form-actions fluid">
					<div class="col-md-6">
						<div class="col-md-offset-3 col-md-9">
							<sj:submit   cssClass="submitBt btn blue" value="Submit"
								onBeforeTopics="formValidationForEditLibettings" validate="true"
								targets="mainContentDiv" formIds="editSettingBooks" />
							<button class="btn default" data-dismiss="modal" type="button"> Cancel </button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</s:form>
</div>
<script type="text/javascript">
changePageTitle("Library Settings");
$(document).ready(function() {
	$('.numeric').numeric();
	$.subscribe('formValidationForEditLibettings', function(event, data) {
		if ($('#editSettingBooks').valid())
		$('button.close').click();
		else
			event.originalEvent.options.submit=false;
	});
});
</script>
