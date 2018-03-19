<%@ include file="/common/taglibs.jsp"%>
<jsp:include page="/common/messages.jsp" />
<s:if test="%{(examTypeList != null && !examTypeList.isEmpty())}">
	<s:if test="%{(studyClassList != null && !studyClassList.isEmpty())}">
		<h4 class="pageTitle bold">
			Upload scorecard template
		</h4>
		<div class="panel-body col-md-12">
			<div class="col-md-1">
				<span class="label label-danger"> NOTE : </span>
			</div>
			<div class="col-md-10">
				<ul>
					<li>
						If you will not find any score card or need to prepare the scorecard please contact eazyschool supporting team at (support@eazyschool.com).
					</li>
					<li>
						Eazyschool supporting will prepare the scorecard with respect to school scorecard design and upload those score card after getting the approval from the school management.
					</li>
				</ul>
			</div>
		</div>
		<s:form action="ajaxUploadScoreCardTemplate" theme="simple"
			cssClass="form-horizontal" id="uploadScoreCardTemplates"
			method="post" namespace="/exam" enctype="multipart/form-data">
			<s:hidden id="classNames" name="anyId" />
			<s:hidden id="examTypeIds" name="tempString" />
			<div class="form-body">
				<jsp:include
					page="/WEB-INF/pages/admin/common/ajaxClassSectionsListForScorecard.jsp"></jsp:include>
				<!-- <div class="form-group">
					<label class="control-label col-md-5">
						<span class="required">*</span>Upload profile template (.docx) :
					</label>
					<div class="col-md-7">
						<input type='file' name="uploadList[1]"
							class="fileNames1 btn default" />
					</div>
				</div> -->
				<div class="form-group">
					<label class="control-label col-md-5">
						<span class="required">*</span>Upload scorecard template (.xlsx) :
					</label>
					<div class="col-md-7">
						<!-- <input type='file' name="uploadList[0]" onchange="validateExcelSheetXlsNdXLSX(this.val);"
							class="fileNames btn default" /> -->
							
							<s:file name="uploadList[0]" id="photoURL" onchange="validateExcelSheetXlsNdXLSX(this);"
							cssClass="btn default required fileNames">
						</s:file>
						
					</div>
				</div>
				<div class="form-actions fluid">
					<div class="col-md-offset-3 col-md-4">
						<sj:submit targets="mainContentDiv" value="Submit"
							formIds="uploadScoreCardTemplates" indicator="indicator"
							cssClass="submit small btn blue" validate="true"
							onBeforeTopics="validateScoreCardForms" />
					</div>
				</div>
			</div>
		</s:form>
		<div class="spaceDiv">
		</div>
		<div class="spaceDiv">
		</div>
		<div id="scoreCardTemplatesListId">
			<jsp:include
				page="/WEB-INF/pages/admin/common/ajaxScoreCardTemplatesList.jsp"></jsp:include>
		</div>
	</s:if>
	<s:else>
		<div class="alert alert-info">
			Classes are not available.
		</div>
	</s:else>
</s:if>
<s:else>
	<div class="alert alert-info">
		Classes are not available.
	</div>
</s:else>
<script type="text/javascript">
$("input:checkbox, input:radio").uniform();
$(document).ready(function() {
$.destroyTopic('validateScoreCardForms');
TableAdvanced.init();
	$.subscribe('validateScoreCardForms', function(event, data) {
		//if(!isNonEmpty($("input.fileNames").val()) && !isNonEmpty($("input.fileNames1").val())){
		if(!isNonEmpty($("input.fileNames").val())){
		 	alert("Please browse your file to upload.");
	        event.originalEvent.options.submit=false;
		}else{
			var boolExist=false;
			if(isNonEmpty($("input.fileNames").val())){ 
				var allowedExtensions = ['xlsx'];
				var res_field = $("input.fileNames").val();   
            	var extension = res_field.substr(res_field.lastIndexOf('.') + 1).toLowerCase();
				if (res_field.length > 0)
     			{
         		 if (allowedExtensions.indexOf(extension) === -1) 
           	      {
		              alert('Invalid file Format. Only ' + allowedExtensions.join(', ') + ' file are allowed.');
		              event.originalEvent.options.submit=false;
             		}
    			}
			}	
			/* else if(isNonEmpty($("input.fileNames1").val())){
				var res_field1 = $("input.fileNames1").val();   
            	var extension1 = res_field1.substr(res_field1.lastIndexOf('.') + 1).toLowerCase();
            	var allowedExtensions1 = ['docx'];
            	if (res_field1.length > 0)
     			{
         		 if (allowedExtensions1.indexOf(extension1) === -1) 
           	      {
		              alert('Invalid file Format. Only ' + allowedExtensions1.join(', ') + ' files are allowed.');
		              event.originalEvent.options.submit=false;
             		}
    			}
			  } */
             }
		var boolVal=prepareSelectedClassNames();
		if(boolVal==false)
		  event.originalEvent.options.submit=false;
	});
});
function prepareSelectedClassNames() {
	if ((($("input[name=chkBoxSelectedIds]:checked").length > 0) && ($("input[name=examTypesChkBoxes]:checked").length > 0))) {
		var classIds = $("input[name=chkBoxSelectedIds]:checked");
		var selectedClassNames = [];
		if (classIds.length > 0) {
		$(classIds).each(function(){
			if (isNonEmpty($(this).val())
					&& isNonEmpty($(this).parents('label').text())) {
				selectedClassNames.push($(this).parents('label').text().trim())
			}
		})
		}
		$("#classNames").val(selectedClassNames);
		var examTypeIds = $("input[name=examTypesChkBoxes]:checked");
			var selectedExamTypeIds = '';
			if (examTypeIds.length > 0) {
				selectedExamTypeIds = '(';
				for ( var i = 0; i < examTypeIds.length; i++) {
					selectedExamTypeIds += examTypeIds[i].value + ', ';
				}
				selectedExamTypeIds += '0)';
			}
			//alert(selectedExamTypeIds);
			$("#examTypeIds").val(selectedExamTypeIds);
		return true;
	}  else if ($("input[name=chkBoxSelectedIds]:checked").length == 0) {
			alert("Please select at least one Class");
			return false;
	} else if ($("input[name=examTypesChkBoxes]:checked").length == 0) {
		alert("Please select at least one exam type");
		return false;
	}
}
function manageScoreCardTemplate() {
	$('a#doScoreCardGen').click();
}
</script>