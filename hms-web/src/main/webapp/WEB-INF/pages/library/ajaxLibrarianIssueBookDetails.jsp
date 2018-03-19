<%@ include file="/common/taglibs.jsp"%>
	<jsp:include page="/common/messages.jsp" />
<div align="right">
	<s:url id="backToView" action="ajaxLibraryHome" includeParams="all"
		escapeAmp="false" namespace="/library">
	</s:url>
	<sj:a href="%{backToView}" cssClass="btn default" targets="mainContentDiv">
	  <i class="m-icon-swapleft"></i>Back To View
	</sj:a> 
</div>
<div class="spaceDiv"></div>
<div class="row-fluid">
	<div class="span12">
			<div class="portlet-body">
			<div class="tabbable tabbable-custom tabbable-full-width">
				<s:form action="ajaxPopupLibrianSearchBooks" id="addReturnBooks" method="post" theme="simple"
					cssClass="form-horizontal">
					<s:hidden name="bookId" value="%{tempId}"></s:hidden>
					<s:hidden name="username" id="selectedUserName"></s:hidden>
					<s:hidden name="anyId" id="bookNumber"></s:hidden>
					<table
						class="table table-striped table-bordered table-hover table-full-width">
						<tr>
							<td>
								<label class="bold">
									Book Name :
								</label>
								<s:property value="bookTitle.bookName" />
							</td>
							<td>
								<label class="bold">
									Author :
								</label>
								<s:property value="bookTitle.author" />
							</td>
							<td>
								<label class="bold">
									Publisher :
								</label>
								<s:property value="bookTitle.publisher" />
							</td>
						</tr>
					</table>
					<div class="form-body">
						<s:if test="%{objectList != null && !objectList.isEmpty()}">
							<span class="label label-danger"> NOTE! </span>&nbsp;Please select any book.
				            <div class="spaceDiv"></div>
				            <div class="spaceDiv"></div>
								<s:iterator value="objectList">
									<div class="col-md-6">
										<div class="col-md-4">
											<s:property value="lableCode" />
										</div>
										<div class="col-md-2">
											<input id="selectLabelCode" type="radio"
												name="labelCodeValue"
												value='<s:property value="lableCode" />' />
										</div>
									</div>
								</s:iterator>
						</s:if>
						<div class="spaceDiv"></div>
						<div class="spaceDiv"></div>
						<div class="row">
							<div class="col-md-12">
								<div class="form-group">
									<label class="control-label col-md-3">
										<span class="required">*</span>Enter Book Number :
									</label>
									<div class="col-md-3">
										<sj:textfield name="blockBookDetails.labelCode" id="bookNo"
											cssClass="required form-control input-medium" size="40"
											value="Please enter book number." readonly="true">
										</sj:textfield>
									</div>
								</div>
							</div>
						</div>
						<div class="row">
							<s:if test='%{customer.barcodeStatus=="Y"}'>
								<div class="col-md-12">
									<div class="form-group">
										<label class="col-md-3 control-label">Select Type :
										</label>
										<div class="col-md-9">
											<div class="make-switch has-switch" data-id="M" data-value="B"
												style="width: 250px" data-off="warning" data-on="success"
												data-off-label="Barcode Scanner" data-on-label="Manual Search">
												<input type="radio" class="toggle" checked="checked" id="barcodeTypeName"> 
												<input type="hidden" name="" value="M">
											</div>
										</div>
									</div>
								</div>
							</s:if>
							<div class="col-md-12" id="issueTypeDiv">
								<div class="form-group">
								<label class="col-md-3 control-label">
									Issue To :
								</label>
								<div class="col-md-9">
									<div class="radio-list">
										<label class="radio-inline">
											<input type="radio" id="checkAll" value="ST" class="selectToIssue"
												name="bookIssueType" checked>
											Student
										</label>
										<label class="radio-inline">
											<input type="radio" value="S" name="bookIssueType" class="selectToIssue"
												id="checkClass">
											Staff
										</label>
									</div>
								</div>
							</div>
							</div>
							<div class="col-md-12" id="manuvalDiv">
								 <div class="form-group">
										<label class="control-label col-md-3" id="labelForStudentStaff">
											<span class="required">*</span>Select Student :
										</label>
										<div class="col-md-3">
											<input type="hidden" id="select2_sample6"
												class="form-control select2 required">
										</div>
							 	 </div>
						  </div>
						  <div class="col-md-12" id="barcodeDiv" style="display: none;">
						 	 <div class="form-group">
									<label class="control-label col-md-3">
										<span class="required">*</span>Select Student / Staff Barcode:
									</label>
									<div class="col-md-3"> 
										<input  name="plTitle" class="form-control input-medium" id="barcodeId" value="">
									</div>
						 	 </div>
						  </div>
						</div>
						<div class="form-actions fluid" id="barcodeHideDiv">
								<div class="col-md-7">
									<div class="col-md-offset-5 col-md-9">
										<sj:submit type="submit" cssClass="submitBt btn blue barcodeSubDiv" onBeforeTopics="issuedBookToStudentStaff" 
											value="Issue Book"  validate="true"	targets="issueStudentStaffBooks" />
									</div>
								</div>
						</div>
					</div>
				</s:form></div>
			</div>
		</div>
	</div>
	<div id="issueStudentStaffBooks">
	
	</div>
	<script type="text/javascript" src="${pageContext.request.contextPath}/plugins/bootstrap-switch/static/js/bootstrap-switch.min.js"></script>
<script type="text/javascript">

$('div.make-switch').find("label[for='barcodeTypeName']").click(function(){
	if($(this).parent().hasClass('switch-on')==true){
		  $('input#barcodeId').css("background", "#f3f3f3");
		  $('input#barcodeId').val('');
		  $('div#barcodeDiv').show();
		  $('div#manuvalDiv').hide();
		  $('div#barcodeHideDiv').hide();
		  $('div#issueTypeDiv').hide();
	  }else{
		  $('input#plTitle').val('MP');
		  $('div#manuvalDiv').show();
		  $('div#barcodeDiv').hide();
		  $('div#barcodeHideDiv').show();
		  $('div#issueTypeDiv').show();
	  }
});


changePageTitle("Issue Book");
$("input:checkbox, input:radio:not('.toggle')") .uniform();
$(document).ready(function() {
	$.destroyTopic("issuedBookToStudentStaff");
	FormComponents.init();
	var bookNo = '<s:property value="anyId"/>';
	if (isNonEmpty(bookNo) && bookNo != "undefined"){
		$('input#bookNo').val(bookNo);
	    $('input#bookNumber').val(bookNo);
	}
	
	var inputLength = $('input#selectLabelCode').length;
	if (inputLength > 0) {
		$('input#selectLabelCode:first').attr("checked", true);
		$('input#selectLabelCode:first').parent('span').addClass("checked");
		$('input#bookNo').val($('input#selectLabelCode:first').val());
		$('input#bookNumber').val($('input#selectLabelCode:first').val());
	}
});

$('input.selectToIssue').click(function(){
	$('span.select2-chosen').html('Search for issue');
	$('div#issueStudentStaffBooks').empty();
  if($(this).val()=="S"){
	  $('label#labelForStudentStaff').html('<span class="required">*</span>Select Staff :');
      $('input#selectedUserName').val('');
  }
  else{
	  $('label#labelForStudentStaff').html('<span class="required">*</span>Select Student :');
	  $('input#selectedUserName').val('');
  }
});

$('input#selectLabelCode').click(function() {
	$('input#bookNo').val($(this).val());
	$('input#bookNumber').val($(this).val());
	$('div#issueStudentStaffBooks').empty();
})
 $.subscribe("issuedBookToStudentStaff",function(event,data){
	 if($("label[for='barcodeTypeName']").parent().hasClass('switch-off')==false){
		 if($('span.select2-chosen').html()=="Search for issue" || $('span.select2-chosen').html()==""){
			 alert("Please select student / staff to issue book.");
			 $('input#selectedUserName').val('');
			 event.originalEvent.options.submit=false;
		 }
	 }
 });
$('#barcodeId').change(function(){
	if($("label[for='barcodeTypeName']").parent().hasClass('switch-off')==true){
		$('#barcodeId').val($(this).val());
		$('input.barcodeSubDiv').click();
	}
})
</script>