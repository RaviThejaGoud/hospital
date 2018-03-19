<%@ include file="/common/taglibs.jsp"%>
<s:if test="%{subCastSettings.id != 0}">
	<div data-width="760" class="modal fade modal-overflow in"
		style="display: block; width: 800px; margin-left: -379px; margin-top: 100px;">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true"></button>
			<h4 class="pageTitle bold">
				Update caste
			</h4>
		</div>
		<div class="modal-body">
		<span class="label label-danger"> NOTE : </span>&nbsp;You can change/update the caste name.
</s:if>
<s:form id="addSubCastForm" action="ajaxAddSubCaste" method="post"
	theme="simple" cssClass="form-horizontal" namespace="/admin">
	<s:hidden name="quizId"></s:hidden>
	<s:hidden name="subCastSettings.id" value="%{subCastSettings.id}"></s:hidden>
	<div class="form-body">
		<s:if test="%{subCastSettings == null}">
			<h4 class="pageTitle bold">
				Add caste
			</h4>
			<hr/>
			<span class="label label-danger"> NOTE : </span>&nbsp; You can add the caste with respective to community.
			<div>&nbsp;</div>
		</s:if>
		<div class="form-group">
			<label class="col-md-3 control-label">
				Caste :
			</label>
			<div class="col-md-4">
				<div class="input-group">
					<sj:textfield name="subCastSettings.subCastName" id="subCastName"
						maxlength="60" size="5"
						cssClass="required  form-control input-medium" />
				</div>
			</div>
		</div>
		<div class="form-actions fluid">
			<div class="col-md-7">
				<div class="col-md-offset-5 col-md-9">

					<sj:submit targets="academicsContent" value="Submit"
						cssClass="submitBt btn blue" validate="true"
						formIds="addSubCastForm" onBeforeTopics="addRackFormValidation1"
						indicator="indicator" />
					<s:if test="%{subCastSettings.id != 0}">
						<button type="button" data-dismiss="modal" class="btn default">
							Cancel
						</button>
					</s:if>
					<s:else>
						<s:url id="urlGetCastSettings" action="ajaxCastSettingsHome"
							namespace="/admin" />
						<sj:a href="%{urlGetCastSettings}" targets="mainContentDiv"
							cssClass="btn default">Cancel</sj:a>
					</s:else>

				</div>
			</div>
		</div>
	</div>
</s:form>
<s:if test="%{subCastSettings.id != 0}">
	</div>
	</div>
</s:if>
<script type="text/javascript">
$(document).ready(function() {
	$.subscribe('addRackFormValidation1', function(event, data) {
		$('button.close').click();
		//$("div.modal-overflow").hide();
		 //$('#subCastName').focus();
	});
});

/*$('button.default').click(function(){
	$("div.modal-overflow").hide();
});
function toggleActivitypesForm(activityId){
	//$('#addBlockRacks'+activityId).hide();
	$("div.modal-overflow").hide();
}*/
/*
function onChangeGetOtherBooks(selectBox) {
	if (selectBox == " ") {
		$("#otherSubject").show();
	} else
		$("#otherSubject").hide();
}*/
</script>
