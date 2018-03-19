<%@ include file="/common/taglibs.jsp"%>
<div data-width="760" class="modal fade modal-overflow in"
	style="display: block; width: 760px; margin-left: -379px; margin-top: 150px;">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true"></button>
		<h4 class="modal-title">
			Edit Skill Type
		</h4>
	</div>
	<div class="modal-body form-horizontal">
		<div class="form-body">
	<s:form action="ajaxDoUpdateSkillType" theme="simple"
		id="updateSkillType" method="post" cssClass="form-horizontal">
		<s:hidden name="commonTypeId" value="%{commonType.id}"></s:hidden>
			<div class="form-group">
				<label class="control-label col-md-3">
					<span class="required">*</span>Skill Type :
				</label>
				<div class="col-md-4">
					<sj:textfield name="commonType.skillTypeName" id="skillTypeName"
						cssClass="form-control required" maxlength="60"></sj:textfield>
				</div>
			</div>
			<div class="form-actions fluid">
				<div class="col-md-offset-3 col-md-4">
					<sj:submit   cssClass="submitBt btn blue" value="Submit"
						targets="caseStudyView" validate="true"
						onBeforeTopics="SkillTypeUpdateSubject" />
					<button type="button" data-dismiss="modal" class="btn default">
						Cancel
					</button>
				</div>
		</div>
	</s:form>
		</div>
		</div>
</div>
<script type="text/javascript">
$(document).ready(function() {
	$.subscribe('SkillTypeUpdateSubject', function(event, data) {
		if ($('#updateSkillType').valid()) {
			$('button.close').click();
		} else {
			event.originalEvent.options.submit = false;
		}
	});
});
</script>

