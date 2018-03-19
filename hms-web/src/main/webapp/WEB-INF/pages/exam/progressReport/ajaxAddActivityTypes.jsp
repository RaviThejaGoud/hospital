<%@ include file="/common/taglibs.jsp"%>
<s:if test="%{studentActivityTypes.id != 0}">
	<div data-width="760" class="modal fade modal-overflow in"
		style="display: block; width: 760px; margin-left: -379px; margin-top: 100px;">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true"></button>
			<h4 class="modal-title">
				Update sub activity
			</h4>
		</div>
		<div class="modal-body">
</s:if>
<s:else>
	<h4>
		Add sub activity
	</h4>
</s:else>
<div class="form-body">
	<s:form id="addRockForm" action="ajaxAddStudentActivityTypes"
		method="post" theme="simple" cssClass="form-horizontal"
		namespace="/exam">
		<s:hidden name="studentActivities.id"></s:hidden>
		<s:hidden name="studentActivityTypes.id"
			value="%{studentActivityTypes.id}"></s:hidden>
		<s:hidden name="studentActivityTypes.selectedClassIds"
			id="selectedClassIds"></s:hidden>
		<div class="form-group">
			<label class="control-label col-md-3">
				<span class="required">*</span> Sub Activity Name :
			</label>
			<div class="col-md-3">
				<s:textfield name="studentActivityTypes.activityTypeName"
					id="rackName" cssClass="form-control input-medium required"
					maxlength="250"></s:textfield>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-md-3">
				Description :
			</label>
			<div class="col-md-3">
				<sj:textarea name="studentActivityTypes.activityTypeDescription"
					id="other" cssClass="form-control input-medium"></sj:textarea>
			</div>
		</div>
		<s:if test="%{studyClassList != null && !studyClassList.isEmpty()}">
			<label class="control-label">
				<span class="required">*</span>Applicable Classes & Sections :
			</label>
			<div class="form-group">
				<label class="control-label col-md-3">
					All Classes & Sections :
				</label>
				<div class="col-md-3">
				<div class="checkbox">
					<input type="checkbox" name="" value="" onClick="checkAll()"
						class="checkbox activityApplicableClasses">
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="col-md-3 control-label">
					Select Classes & Sections :
				</label>
				<div class="col-md-9" id="activityClasses">
					<s:checkboxlist
						name="studentActivityTypes.activityTypeClassNameIdsList"
						list="studyClassList" listKey="id" listValue="classAndSection"
						theme="ems" cssClass="form-control input-medium" />
				</div>
			</div>
		</s:if>
		<s:else>
			<div class="alert alert-info">
				Classes are not available.
			</div>
		</s:else>
		<div class="form-actions fluid">
			<div class="col-md-offset-3 col-md-9">
				<s:if test='%{#session.previousYear=="N"}'>
					<sj:submit targets="studentsActivitiesContent"
						formIds="addRockForm" value="Submit" cssClass="submitBt btn blue"
						onBeforeTopics="addRackFormValidation1" validate="true" />
				</s:if>
				<s:if test="%{studentActivityTypes.id != 0}">
					<button type="button" data-dismiss="modal" class="btn default">
						Cancel
					</button>
				</s:if>
				<s:else>
					<s:url id="doStudActivitiesSettings"
						action="ajaxManageStudentActivities" namespace="/exam" />
					<sj:a href="%{doStudActivitiesSettings}" targets="mainContentDiv"
						cssClass="btn default"> Cancel</sj:a>
				</s:else>
			</div>
		</div>
	</s:form>
</div>
<s:if test="%{studentActivityTypes.id != 0}">
	</div>
	</div>
</s:if>
<script type="text/javascript">
$(document).ready(function() {
	$("input:checkbox, input:radio").uniform();
	$.destroyTopic('addRackFormValidation1');
	activitypesForm();
});
$.subscribe('addRackFormValidation1', function(event, data) {
	var selectedClassIds1 = [];
	if (($("input:checkbox:not('.activityApplicableClasses'):checked").length) > 0) {
		$("input:checkbox:not('.activityApplicableClasses'):checked").each(function() {
			selectedClassIds1.push($(this).val());
		});
		$('#selectedClassIds').val(selectedClassIds1);
		 $('button.close').click();
	} else {
		alert("Please select at least one class");
		event.originalEvent.options.submit = false;
	}
});
function activitypesForm(){
if ($("input:checkbox:checked:not('.activityApplicableClasses')").length==$("input:checkbox:not('.activityApplicableClasses')").length){
		$(".activityApplicableClasses").parent('span').addClass("checked");
		$(".activityApplicableClasses").attr("checked", true);
	}
}

$("input:checkbox:not('.activityApplicableClasses')").click(function() {
	if ($("input:checkbox:unchecked").length > 0) {
		$(".activityApplicableClasses").parent('span').removeClass("checked");
		$(".activityApplicableClasses").attr("checked", false);
	} else {
		$(".activityApplicableClasses").parent('span').addClass("checked");
		$(".activityApplicableClasses").attr("checked", true);
	}
	activitypesForm();
});
function checkAll() {
	if ($(".activityApplicableClasses").is(':checked')) {
		$('div#activityClasses').find('label').each(function() {
			$(this).find("input:checkbox").parent('span').addClass('checked');
			$(this).find("input:checkbox").attr("checked", "true");
		});
	}else {
		$('div#activityClasses').find('label').each(function() {
			$(this).find("input:checkbox").parent('span').removeClass('checked');
			$(this).find("input:checkbox").removeAttr("checked");
		});
	}
}
</script>