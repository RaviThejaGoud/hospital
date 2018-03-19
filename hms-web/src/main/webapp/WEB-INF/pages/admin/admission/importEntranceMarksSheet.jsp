<%@ include file="/common/taglibs.jsp"%>
<div data-width="760"  class="modal fade modal-overflow in" id="responsive2" style="display: block; width: 760px; margin-left: -379px; margin-top: 200px;" aria-hidden="false">
<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
		<h4 class="bold pageTitle">
			Upload Entrance Marks
		</h4>
	</div>
	<div class="modal-body">
	<div class="form-body">
	<s:form action="ajaxImportEntranceMarks" id="importEntranceMarksSheet"
		method="post" theme="simple" enctype="multipart/form-data"
		cssClass="form-horizontal" namespace="/admin">
		<%--<s:hidden name="classId"/>
		--%>
		<s:hidden name="academicYearId" />
		<div class="row">
		<div class="col-md-6">
			<div class="form-group">
				<label class="control-label col-md-4">
					<span class="required">*</span>Import an Excel Sheet :
				</label>
				<div class="col-md-6">
					<s:file name="upload" id="photoURL" label="Import an Excel Sheet"
						cssClass="required btn default btn-file">
					</s:file>
				</div>
			</div>
		</div>
		</div>
			<div class="col-md-7">
				<div class="col-md-offset-3 col-md-9">
					<sj:submit  targets="admissionsPendingContentAppli" value="Submit"
						indicator="indicator" cssClass="submitBt btn blue" validate="true"
						onBeforeTopics="importEntranceMarksSheetValidation" formIds="importEntranceMarksSheet" />
					<button type="button" data-dismiss="modal" class="btn  cancel default">
							Cancel
					</button>
				</div>
			</div>
	</s:form></div></div>
	</div>
<!--<div class="row">
		<div class="col-md-7">
		<s:file name="upload" id="photoURL" label="Import an Excel Sheet"
				required="true" requiredposition="first"
				cssClass="submitBt btn blue required">
			</s:file>
		</div>
		</div>
		-->
		<!--<div class="grid_7">
			<s:file name="upload" id="photoURL" label="Import an Excel Sheet"
				required="true" requiredposition="first"
				cssClass="submitBt btn blue required">
			</s:file>
		</div>
		--><!--<div class="grid_6">
			&nbsp;
		</div>
		<div class="grid_11">
			<a
				onclick="javascript:showOrHideEntranceMarksForm(<s:property value='classId'/>);"
				class="cancelButton">Cancel</a>
			<sj:submit   targets="admissionsPendingContentAppli" value="Submit"
				indicator="indicator" cssClass="ubmitBt btn blue"
				onClickTopics="importEntranceMarksSheetValidation" />
		</div>
	-->
<script type="text/javascript">
	function showOrHideEntranceMarksForm(classId){
		$('#uploadMarks'+classId).hide();
	}
	$('button.close').click(function(){
		$("div#responsive2").hide();
		 $('button.close').click();
	})
	$('button.cancel').click(function(){
		$('button.close').click();
	})
	$.subscribe('importEntranceMarksSheetValidation', function(event, data) {
		if ($('#importEntranceMarksSheet').valid())
			 $('button.close').click();
		
/* 			 $('div#importsheetDiv').html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
 */	});
	
</script>


