<%@ include file="/common/taglibs.jsp"%>
<div data-width="760" class="modal fade modal-overflow in"
	style="display: block; width: 600px; margin-left: -279px; margin-top: 150px;">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true"></button>
		<h4 class="modal-title">
			Edit Leave Settings
		</h4>
	</div>
	<div class="modal-body">
		<div class="form-body">
			<s:form action="ajaxEditManageLeaves" theme="simple"
				cssClass="form-horizontal" id="editTeachingStaff" namespace="/admin">
				<s:hidden name="leaveManagement.id" />
				<div class="form-group">
					<label class="control-label col-md-3">
						<span class="required">*</span>Sick Leaves :
					</label>
					<div class="col-md-5">
						<sj:textfield name="leaveManagement.sickLeaves" id="sickLeaves"
							label="" labelposition="top"
							cssClass="numeric required form-control" maxlength="2"></sj:textfield>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-md-3">
						<span class="required">*</span>Casual Leaves :
					</label>
					<div class="col-md-5">
						<sj:textfield name="leaveManagement.casualLeaves"
							id="casualLeaves" labelposition="top"
							cssClass="numeric required form-control" maxlength="2"></sj:textfield>
					</div>
				</div>
					<div class="form-actions fluid">
						<div class="col-md-offset-3 col-md-9">
							<sj:submit   cssClass="submitBt btn blue" value="Submit"
								targets="stepStaffLeavesDiv"
								onBeforeTopics="teachingStaffFormValidation1"
								formIds="editTeachingStaff" />
							<button type="button" data-dismiss="modal" class="btn default">
								Cancel
							</button>
					</div>
				</div>
			</s:form>
		</div>
	</div>
</div>
<script language="JavaScript" type="text/javascript">
changePageTitle('Edit Manage Leaves');
$(document).ready(function() {
	$('.numeric').numeric( {
		allow : "0-9"
	});
	$.subscribe('teachingStaffFormValidation1', function(event, data) {
		if ($('#editTeachingStaff').valid()) {
			$('button.close').click();
		} else {
			event.originalEvent.options.submit = false;
		}
	});
});
</script>