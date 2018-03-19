<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/common/textcounter.js"></script>
</script>
<div class="form-body">
	<div data-width="760" class="modal fade modal-overflow in"
		style="display: block; width: 660px; margin-left: -379px; margin-top: 100px;"
		aria-hidden="false">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true"></button>
			<h4 class="modal-title"></h4>
		</div>
		<div class="modal-body">
			<s:form id="updateTaskHistory"
				action="ajaxUpdateInprogressOrCompleteTaskDetails" method="post"
				theme="simple" cssClass="form-horizontal" namespace="/staff">
				<s:hidden name="taskId" value="%{taskDetailsVO.id}" />
				<s:hidden name="taskHistoryVO.accountVO.id" value="%{taskDetailsVO.latestTaskHistoryVO.accountVO.id}" />
				<s:hidden name="taskHistoryVO.taskDate" value="%{taskDetailsVO.latestTaskHistoryVO.taskDateStr}" />
				<div class="form-body">
					<div class="row">
						<div class="col-md-12">
							<div class="form-group">
								<label class="col-md-3 control-label">Select Type : </label>
								<div class="col-md-6">
									<div class="radio-list">
										<label class="radio-inline"> <input type="radio"
											value="I" checked="checked"
											name="taskHistoryVO.status" class="radio">
											In-Progress
										</label>
										<label class="radio-inline"> <input type="radio"
											value="H"  name="taskHistoryVO.status"
											class="radio"> Hold
										</label>
										<label class="radio-inline"> <input type="radio"
											value="C"  name="taskHistoryVO.status"
											class="radio"> Complete
										</label>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="span10"></div>
					<div class="row">
						<div class="col-md-12">
							<div class="form-group">
								<label class="control-label col-md-3"> <span
									class="required">*</span> Comments :
								</label>
								<div class="col-md-6">
								<sj:textarea rows="3" cols="50" name="taskHistoryVO.comments" id="maxlength_textarea2" 
											 cssClass="required form-control word_count" maxlength="1024"></sj:textarea>
									<span class="help-block"> </span>
								</div>
							</div>
						</div>
					</div>
					<div class="form-actions fluid">
						<div class="col-md-offset-3 col-md-6">
							<sj:submit value="Submit" cssClass="submitBt btn blue"
								formIds="updateTaskHistory" targets="mainContentDiv" indicator="indicator" onBeforeTopics="formValidationTask" validate="true" />
							<button type="button" data-dismiss="modal" class="btn default"> Cancel</button>
						</div>
					</div>
				</div>
			</s:form>
		</div>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function() {
	$("input:checkbox, input:radio:not('.toggle')").uniform();
	FormComponents.init();
	FormAdvanced.init();
	UIExtendedModals.init();
});
$.subscribe('formValidationTask', function(event, data) {
	if ($('#updateTaskHistory').valid()){
		$('button.close').click();
	} else
		 event.originalEvent.options.submit=false;	
}); 
</script>
