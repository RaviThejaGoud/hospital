<%@ include file="/common/taglibs.jsp"%>
<s:if test="%{syllabusPlannerList != null && !syllabusPlannerList.isEmpty()}">
	<div class="form-group" id="lessonSelectDiv">
		<label class="control-label col-md-2">
			Select Lesson :
		</label>
		<s:if test="%{lessonId > 0}">
			<div class="col-md-3">
				<s:select list="syllabusPlannerList" listKey="id" value="lessonId"
					listValue="chapterName" theme="simple" id="editSyllabusId" headerKey="" headerValue="-Select Lession-"
					cssClass="required form-control input-medium" name="questionPaperBankVO.staffSyllabusPlannerVo.id"
					onchange="javascript:getLessonsType(this.value);">
				</s:select>
			</div>
		</s:if>
		<s:else>
			<div class="col-md-3">
				<s:select list="syllabusPlannerList" listKey="id"
					listValue="chapterName" theme="simple" id="syllabusId" headerKey="" headerValue="-Select Lession-"
					cssClass="required form-control input-medium" name="questionPaperBankVO.staffSyllabusPlannerVo.id"
					onchange="javascript:getLessonsType(this.value);">
				</s:select>
			</div>
		</s:else>
	</div>
</s:if>
<s:else>
	<div class="alert alert-info">
		There are no Lessons.
	</div>
</s:else>
	<div class="row" id="questionDiv" style="width: 110%;">
		<div class="form-group">
			<label class="control-label col-md-2">
				<span class="required">*</span>Question Bank Name :
			</label>
			<div class="col-md-6">
				<sj:textfield id="materialName"
					name="questionPaperBankVO.materialName"
					cssClass="required form-control input-medium" />
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-md-2">
				Description :
			</label>
			<div class="col-md-5">
				<s:textarea cols="55" rows="3" id="description"
					name="questionPaperBankVO.Description"
					cssClass="form-control input-large" />
			</div>
		</div>
	</div>
	<s:if test="%{questionBankId > 0}">
		<div class="form-group" id="uploadDiv" style="width: 104%;">
			<label class="control-label col-md-2"> Upload Documents : </label> 
			<div class="col-md-4">
				<s:file name="questionPaperBankVO.fileUpload" id="uploadAndDownScannedDocs" cssClass="multi required" multiple="multiple"></s:file>
			</div>
		</div>
	</s:if>
	<s:else>
		<div class="form-group" id="uploadDiv" style="width: 104%;">
			<label class="control-label col-md-2"> <span class="required">*</span> Upload Documents : </label> 
			<div class="col-md-4">
				<s:file name="questionPaperBankVO.fileUpload" id="uploadAndDownScannedDocs" cssClass="multi required" multiple="multiple"></s:file>
			</div>
		</div>
	</s:else>
	<div id="attachmentDiv" class="col-md-12"></div>
	<s:if test="%{questionPaperBankVO.attachmentVOs != null && !questionPaperBankVO.attachmentVOs.isEmpty()}">
			<div class="col-md-12">
				<div class="col-md-3" style="width: 14%;"></div>
					<div class="col-md-8">
						<table
							class="table table-striped table-bordered table-hover table-full-width"
							id="sample_2" style="width: 50%;">
							<thead>
								<tr>
									<th>Uploaded Documents</th>
									<th>Delete</th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="questionPaperBankVO.attachmentVOs">
									<tr>
										<td>
											<p class="form-control-static">
												<a target="_new" href="<s:property value="filePath" />"><s:property
														value="fileName" /> </a>
											</p>
										</td>
										<td>
											<s:url id="removeQuestionBank" action="ajaxDeleteQuestionBankAttachment"
												includeParams="all" escapeAmp="false" namespace="/exam">
												<s:param name="questionPaperBankVO.attachmentVOs.id" value="%{id}" />
												<s:param name="questionPaperBankVO.id" value="%{questionPaperBankVO.id}" />
											</s:url>
											<s:div cssClass="btn btn-xs red emsRemove"
												onclick="javascript:confirmDialogWithTarget(this,'classContentDiv');"
												id='%{removeQuestionBank}' title="Delete this Question Bank">
												<i class="fa fa-times"></i>Delete
											</s:div>
										</td>
									</tr>
							 </s:iterator>
						</tbody>
				 </table>
			</div>
		</div>
	</s:if>
	<div class="form-actions fluid" id="submitDiv">
		<div class="col-md-6">
			<div class="col-md-offset-3 col-md-12">
				<sj:submit  cssClass="submitBt btn blue" value="Submit"
					targets="mainContentDiv" validate="true" formIds="addQuestionBankForm"
					onBeforeTopics="questionBankFormValidation" />
					<s:url id="doViewRecords" action="ajaxViewQuestionBankMaterialList"
						includeParams="all" escapeAmp="false" namespace="/exam">
					</s:url>
					<sj:a href="%{doViewRecords}" cssClass="btn default"
						targets="mainContentDiv">Cancel</sj:a>
			</div>
		</div>
	</div> 
<script type="text/javascript">
$(document).ready(function() {
	$("input[type=file].multi").MultiFile();
	$("div#questionDiv").hide();
	$("div#submitDiv").hide();
	$("div#uploadDiv").hide();
	var selectedVal = $('select#editSyllabusId').val();
	if(isNonEmpty(selectedVal)){
		getLessonsType(selectedVal);
		document.getElementById('editSyllabusId').disabled = true;
		$('#uploadAndDownScannedDocs').removeClass('required');
	}
});
if ($('div.emsRemove')) {
	$('div.emsRemove').unbind('click');
	$("div.emsRemove").click(function() {
		confirmDialog(this);
	});
}
function confirmDialog(event) {
	thishref = $(event).attr('id');
	if ($(event).next('.question').length <= 0) {
		$(event)
				.after(
						'<div class="question">Are You Sure?<br/> <span class="yes">Yes</span><span class="cancel">Cancel</span></div>');
	}
	$(event).next('.question').animate({
		opacity : 1
	}, 300);
	$('.yes').unbind('click');
	$('.yes')
			.bind(
					'click',
					function() {
						var prdDiv = $(this).parents('.question');
						prdDiv.html('Processing...');
						$
								.ajax({
									url : thishref,
									cache : false,
									success : function(response) {
										if (isNonEmpty(response.info)) {
											var classAttach = response.info;
											if (isNonEmpty(classAttach)) {
												prdDiv.remove();
											} else {
												prdDiv.parent().parent()
														.remove();
											}
										} else {
											prdDiv.parent().parent()
													.remove();
											$('div#attachmentDiv')
													.html(
															'<div class="alert alert-success"><strong>Attachment removed successfully.</strong><button class="close" data-dismiss="alert"></button></div>');
										}
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
function getLessonsType(lessonType){
	if(isNonEmpty(lessonType)){
		 $("div#questionDiv").show();
		 $("div#uploadDiv").show();
		 $("div#submitDiv").show();
	}else{
		 $("div#questionDiv").hide();
		 $("div#submitDiv").hide();
		 $("div#uploadDiv").hide();
	}
}
</script>
