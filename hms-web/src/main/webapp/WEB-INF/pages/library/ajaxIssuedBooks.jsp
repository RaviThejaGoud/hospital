<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript">
$(document).ready(function() {
	FormAdvanced.init();
	$("input:checkbox, input:radio:not('.toggle')") .uniform();
	$("#bookNo").focus();
	$('input#bookNo').val('');
	$('input#userName').val('');
});
</script>
<div id="returnBookDiv">
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Issue / Return Books
				</div>
			</div>
			<div class="portlet-body">
				<div class="tabbable tabbable-custom tabbable-full-width">
					<ul class="nav nav-tabs">
						<li>
							<s:url id="urlSchoolWideBooksLink"
								action="ajaxDoViewAllIssuedBooks" includeParams="all"
								escapeAmp="false" namespace="/library">
							</s:url>
							<sj:a href="%{urlSchoolWideBooksLink}"
								targets="subjectsContentDiv" data-toggle="tab">View Issued Books</sj:a>
						</li>
						<li class="active">
							<s:url id="urlSchoolWideIssueLink"
								action="ajaxDoIssuedBookDetail" includeParams="all"
								escapeAmp="false" namespace="/library">
							</s:url>
							<sj:a href="%{urlSchoolWideIssueLink}" targets="mainContentDiv"
								data-toggle="tab">Issue / Return Books</sj:a>
						</li>
					</ul>
					<div id="subjectsContentDiv" class="tab-content">
						<jsp:include page="/common/messages.jsp"></jsp:include>
						<div class="form-body form-horizontal">
							<s:if test='%{customer.barcodeStatus=="Y"}'>
								<div class="row">
									<div class="form-group">
										<label class="col-md-3 control-label">Select Type :
										</label>
										<div class="col-md-9">
											<div class="make-switch has-switch" data-id="M" data-value="B"
												style="width: 250px" data-off="warning" data-on="success"
												data-off-label="Barcode Scanner" data-on-label="Manual Process">
												<input type="radio" class="toggle" checked="checked" id="barcodeType"> 
												<input type="hidden" name="" value="M">
											</div>
										</div>
									</div>
								</div>
							</s:if>
							<div class="row">
								<div class="form-group">
									<label class="col-md-3 control-label">Select Applicable Type :
									</label>
									<div class="col-md-9">
										<div class="make-switch has-switch" data-id="IB" data-value="RB"
											style="width: 250px" data-off="warning" data-on="success"
											data-off-label="Return Book" data-on-label="Issue Book">
											<input type="radio" class="toggle" checked="checked" id="bookType">
												 <input type="hidden" name="bookIssueType" value="IB">
										</div>
									</div>
								</div>
							</div>
							<!-- <div class="row">
								<div class="form-group">
									<label class="col-md-3 control-label">
										Select Applicable Type :
									</label>
									<div class="col-md-9">
										<div class="radio-list">
											<label class="radio-inline">
												<input type="radio" id="checkAll" value="IB"
													name="bookIssueType" checked>
												Issue Book
											</label>
											<label class="radio-inline">
												<input type="radio" value="RB" name="bookIssueType"
													id="checkClass">
												Return Book
											</label>
										</div>
									</div>
								</div>
							</div> -->
							
							<s:form id="issuedBooksDetails"
								action="ajaxLibrainIssuedBookDetails" method="post"
								theme="simple" cssClass="form-horizontal" namespace="/library">
								<s:hidden name="anyTitle" id="anyTitle"></s:hidden>
								<s:hidden name="plTitle" id="plTitle"></s:hidden>
								<div id="bookNumberId">
									<div class="form-group">
										<label class="control-label col-md-3">
											<span class="required">*</span>Book Number :
										</label>
										<div class="col-md-9">
											<sj:textfield name="bookNumber" id="bookNo"
												cssClass="required form-control input-medium" maxlength="60"></sj:textfield>
										</div>
									</div>
								</div>
								<div id="studentStaffId">
									<div class="form-group">
										<label class="control-label col-md-3">
											<span class="required">*</span>Student Admission Number / Staff ID :
										</label>
										<div class="col-md-9">
											<sj:textfield name="username" id="userName" maxlength="35"
												cssClass="required numeric form-control input-medium" />
										</div>
									</div>
								</div>
								<div id="returnBooksContent" style="display: none;">
									<br />
									</br/>
										<s:if test="%{plTitle != 'I'}">
											<jsp:include
												page="/WEB-INF/pages/library/ajaxSubmitReturnsBooks.jsp"></jsp:include>
										</s:if>
									</div>
								<br />
								<s:if
									test='%{(#session.previousYear == null || #session.previousYear.isEmpty()) || (#session.previousYear == "N")}'>
									<div class="form-actions fluid" id="hideBarcodeDiv">
										<div class="col-md-offset-3 col-md-9">
											<div id="submitDiv">
												<sj:submit cssClass="submitBt btn blue"
													formIds="issuedBooksDetails" id="submitResult"
													value="Submit" indicator="indicator" validate="true"
													targets="mainContentDiv"
													onBeforeTopics="addIssuedBookDetails" />
												</div>
											<div id="searchDiv1">
												<sj:submit cssClass="submitBt btn blue"
												formIds="issuedBooksDetails" id="issueResultDiv"
												value="Search" indicator="indicator" validate="true"
												cssStyle="display:none;" targets="returnBookDiv"
												onBeforeTopics="addIssuedBookDetails" />
											</div>
											<div id="searchDiv2">
												<sj:submit cssClass="submitBt btn blue"
												formIds="issuedBooksDetails" id="searchResult"
												value="Search" indicator="indicator" validate="true"
												cssStyle="display:none;" targets="returnBooksContent"
												onBeforeTopics="addIssuedBookDetails" />
											</div>
										</div>
									</div>
								</s:if>
							</s:form>
						</div>
						<div id="takenBooksDiv">
							<s:if test="%{bookTitleList != null && !bookTitleList.isEmpty()}">
								<table
									class="table table-striped table-bordered table-hover table-full-width"
									id="sample_2">
									<thead>
										<tr>
											<th>
												Book Number
											</th>
											<th>
												Book Name
											</th>
											<th>
												Author Name
											</th>
											<th>
												issued Date
											</th>
											<th>
												due Date
											</th>
										</tr>
									</thead>
									<tbody>
										<s:iterator value="bookTitleList">
											<tr>
												<td>
													<s:property value="bookLable.lableCode" />
												</td>
												<td>
													<s:property value="bookTitle.bookName" />
												</td>
												<td>
													<s:property value="bookTitle.author" />
												</td>
												<td>
													<s:property value="issuedDateStr" />
												</td>
												<td>
													<s:property value="dueDateStr" />
												</td>
											</tr>
										</s:iterator>
									</tbody>
								</table>
							</s:if>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/plugins/bootstrap-switch/static/js/bootstrap-switch.min.js"></script>
<script type="text/javascript">
$.destroyTopic('addIssuedBookDetails');
changePageTitle("Librarian Issue Books");
 	$("#issuedBooksDetails").keypress(function (e) {   
	    var charCode = e.charCode || e.keyCode || e.which;
	    if (charCode  == 13) {
	    	$("#issuedBooksDetails").submit();
	        return false;
	    }
	});
$.subscribe('addIssuedBookDetails', function(event, data) {
		if($('#issuedBooksDetails').valid()){
			if($("label[for='bookType']").parent().hasClass('switch-on')){
			 	$('input#anyTitle').val('IB');
			}
			else{
				 $('input#anyTitle').val('RB');
			 	$('div#returnBooksContent').hide();
			}
		}
		 else{
			event.originalEvent.options.submit=false;
		}
}); 
$('div#returnBooksContent').hide();

$('div.make-switch').find("label[for='barcodeType']").click(function(){
	if($(this).parent().hasClass('switch-on')){
		  $('input#plTitle').val('BC');
		  $('input#userName').css("background", "#f3f3f3");
		  $('input#userName').val('');
		  $('div#hideBarcodeDiv').hide();
	  }else{
		  $('input#plTitle').val('MP');
		  $('input#userName').css("background", "#fff");
		  $('div#hideBarcodeDiv').show();
	  }
});

$('div.make-switch').find("label[for='bookType']").click(function(){	
  $('span.close1').click();
  if($(this).parent().hasClass('switch-on')){
 	$('input#userName').val('');
  	$('input#bookNo').val('');
  	 $('input#anyTitle').val('RB');
    $('input#bookNo').removeClass('required');
    $('div#bookNumberId').hide();
    $('input#searchResult').show();
    $('div#takenBooksDiv').hide();
   $('div#studentStaffId').find('label.error').remove();
   if($("label[for='barcodeType']").parent().hasClass('switch-on')){
	   $('div#submitDiv').hide();
	   $('div#searchDiv2').show();
	   $('div#searchDiv1').hide();
   } else{
	   $('div#submitDiv').hide();
	   $('div#searchDiv2').show();
	   $('div#searchDiv1').hide();
   } 
  }else{
  $('input#userName').val('');
  if($("label[for='barcodeType']").parent().hasClass('switch-off')){
 	 $('div#submitDiv').show();
 	 $('div#searchDiv2').hide();
 	 $('div#searchDiv1').hide();
  } 
  $('div#submitDiv').show();
  $('input#anyTitle').val('IB');
    $('input#bookNo').addClass('required');
    $('div#bookNumberId').show();
    $('input#searchResult').hide();
    $('input#submitResult').show();
    $('div#returnBooksContent').hide();
    $('div#bookNumberId').find('label.error').remove();
    $('div#studentStaffId').find('label.error').remove();
  }
});
  
$('#userName').change(function(){
	if($("label[for='barcodeType']").parent().hasClass('switch-off')){
		if($("label[for='bookType']").parent().hasClass('switch-on')){
			if($('input#bookNo').val()==""){
				$('#userName').val('');
				alert("Plase enter book number.");
			} else{
				$('#userName').val($(this).val());
				$('input#issueResultDiv').click();
			}
		}else{
			$('#userName').val($(this).val());
			$('input#searchResult').click();
		}
	}
})

</script>