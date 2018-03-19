<%@ include file="/common/taglibs.jsp"%>
	<s:if test="%{dayBook.id != 0}">
		<div data-width="760"  class="modal fade modal-overflow in" id="responsive" style="display: block; width: 860px; margin-left: -379px; margin-top: 50px;" aria-hidden="false">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
			<h4 class="modal-title">
				Update Voucher
			</h4>
		</div>
		<div class="modal-body">
	</s:if>
<s:form action="ajaxAddDayBooks" theme="simple" id="addDayBookForm"
	cssClass="form-horizontal form-body" namespace="/admin" method="post">
	<s:hidden name="dayBook.id" value="%{dayBook.id}"></s:hidden>
	<h4 class="bold pageTitle">
		<s:if test="%{dayBook.id != 0}">
		</s:if>
		<s:else>
				Add Voucher
			</s:else>
	</h4>
	<div class="row">
		<div class="col-md-12">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4"> <span
						class="required">*</span>Name :
					</label>
					<div class="col-md-5">
						<sj:textfield id="name" name="dayBook.name" maxlength="50"
							cssClass="required form-control input-medium" />
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4"> <span
						class="required">*</span>Select Date :
					</label>
					<div class="col-md-5">
						<div data-date-end-date="+0d" data-date-format="mm/dd/yyyy"
							class="input-group input-medium date date-picker">
							<input type="text" id="entryDate" readonly="readonly"
								name="dayBook.entryDate"
								value='<s:property value="dayBook.entryDateStr"/>'
								class="required form-control input-medium" /> <span
								class="dateInput input-group-btn">
								<button type="button" class="btn default">
									<i class="fa fa-calendar"></i>
								</button>
							</span>
						</div>
						<span class="help-block">(MM/DD/YYYY)</span>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
	<div class="col-md-12">
		<div class="col-md-6">
			<div class="form-group">
				<label class="control-label col-md-4"> <span
					class="required">*</span>Select Type :
				</label>
				<div class="col-md-7">
					<div class="make-switch has-switch" data-id="I" data-value="E"
						style="width: 180px" data-off="warning" data-on="success"
						data-off-label="Expenditure" data-on-label="Income">
						<s:if test='%{dayBook.id ==0}'>
							<input type="radio" class="toggle" checked="checked" id="income">
							<input type="hidden" name="dayBook.type" value="I">
						</s:if>
						<s:else>
							<s:if test='%{dayBook.type=="I"}'>
								<input type="radio" class="toggle" checked="checked" id="income">
							</s:if>
							<s:else>
								<input type="radio" class="toggle" id="income">
							</s:else>
							<input type="hidden" name="dayBook.type"
								value='<s:property value="dayBook.type"/>'>
						</s:else>
					</div>
				</div>
			</div>
		</div>
		<div class="col-md-6">
			<div class="form-group" id="invoiceNumberDiv" style="display: none;">
				<label class="control-label col-md-4"> <span
					class="required">*</span> Receipt Number :
				</label>
				<div class="col-md-8">
					<s:if test="%{dayBook.id != 0}">
						<sj:textfield name="dayBook.invoiceNumber" disabled="true"
							cssClass="numeric form-control input-medium" maxlength="10"></sj:textfield>
					</s:if>
					<s:else>
						<sj:textfield name="dayBook.invoiceNumber"
							id="daybookReceiptNumber"
							cssClass="numeric form-control input-medium" maxlength="10"></sj:textfield>
						<span class="help-block">(Please provide the receipt
							numbers in the above text box. Otherwise system will generate and
							assign the receipt number.)</span>
					</s:else>
				</div>
			</div>
		</div>
	</div>
	</div>
	<div class="row">
	<div class="col-md-12">
		<div class="col-md-6">
			<div class="form-group">
				<label class="control-label col-md-4"> <span
					class="required">*</span>Description :
				</label>
				<div class="col-md-5">
					<s:textarea cols="55" rows="3" id="description"
						name="dayBook.Description" maxCharsData="150"
						cssClass="required form-control input-large word_count" />
					<div class="counter"></div>
				</div>
			</div>
		</div>
	</div>
	</div>
	<div class="row">
	<div class="col-md-12">
			<div class="col-md-6">
				<div class="form-group">
					<label class="col-md-4 control-label"> Payment Type : </label>
					<div class="radio-list">
						<label class="radio-inline"> <input type="radio" value="C"
							name="dayBook.paymentType" id="cashForm"
							onclick="javascript:paymentTypeMethodChange(this.value)">
							Cash
						</label> <label class="radio-inline"> <input type="radio"
							value="D" name="dayBook.paymentType" id="ddForm"
							onclick="javascript:paymentTypeMethodChange(this.value)">
							DD
						</label> <label class="radio-inline"> <input type="radio"
							value="CH" name="dayBook.paymentType" id="chForm"
							onclick="javascript:paymentTypeMethodChange(this.value)">
							Cheque
						</label>
					</div>
				</div>
			</div>
		</div>
		</div>
		<div class="row">
		<div class="col-md-12" style="display: none;" id="inputboxhideText">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4"> <span
						class="required">*</span> Enter DD Number :
					</label>
					<div class="col-md-6">
						<s:if test="%{dayBook.id != 0}">
							<sj:textfield name="dayBook.ddNumber" id="ddNumberFee"
								cssClass="required numeric form-control input-medium defaultValue"
								maxlength="15"></sj:textfield>
						</s:if>
						<s:else>
							<sj:textfield name="dayBook.ddNumber" id="ddNumberFee"
								value=""
								cssClass="required numeric form-control input-medium defaultValue"
								maxlength="15"></sj:textfield>
						</s:else>
					</div>
				</div>
			</div>
		</div>
		</div>
		<div class="row">
		<div class="col-md-12" style="display: none;" id="checkinputboxhideText">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4"> <span
						class="required">*</span> Enter Cheque Number :
					</label>
					<div class="col-md-6">
						<s:if test="%{dayBook.id != 0}">
							<sj:textfield name="dayBook.chequeNumber" id="chequeNumber"
								cssClass="required numeric form-control input-medium defaultValue"
								maxlength="15"></sj:textfield>
						</s:if>
						<s:else>
							<sj:textfield name="dayBook.chequeNumber" id="chequeNumber"
								value=""
								cssClass="required numeric form-control input-medium defaultValue"
								maxlength="15"></sj:textfield>
						</s:else>
					</div>
				</div>
			</div>
		</div>
		</div>
		<div class="row">
		<div class="col-md-12" style="display: none;" id="bankinputboxhideText">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4"> <!--<span class="required">*</span>-->
						Select Bank Name :
					</label>
					<div class="col-md-6">
						<s:select list="objectList" id="bankName"
							name="dayBook.bankMaster.id" theme="simple" listValue="bankName"
							listKey="id" cssClass="required form-control input-medium" />
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4"> <!--<span class="required">*</span>-->
						Bank Branch Name :
					</label>
					<div class="col-md-6">
						<s:if test="%{dayBook.id != 0}">
							<sj:textfield name="dayBook.branchName" id="branchName"
								cssClass="form-control input-medium defaultValue" maxlength="25"></sj:textfield>
						</s:if>
						<s:else>
							<sj:textfield name="dayBook.branchName" id="branchName"
								value="Enter Bank Branch Name"
								cssClass="form-control input-medium defaultValue" maxlength="25"></sj:textfield>
						</s:else>
					</div>
				</div>
			</div>
		</div>
		</div>
		<div class="row">
		<div class="col-md-12">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4"> <span class="required">*</span>Amount
						:
					</label>
					<s:if test="%{dayBook.amount !=0}">
						<div class="col-md-5">
							<s:textfield name="dayBook.amount" id="amount" maxlength="7"
								cssClass="form-control numericDot input-medium"></s:textfield>
						</div>
					</s:if>
					<s:else>
						<div class="col-md-5">
							<s:textfield name="dayBook.amount" id="amount" value=""
								maxlength="7"
								cssClass="form-control input-medium numericDot"></s:textfield>
						</div>
					</s:else>
				</div>
			</div>
		</div>
		</div>
		<div class="row">
		<div class="col-md-12">
			<div class="form-actions fluid">
				<div class="col-md-6">
					<div class="col-md-offset-3 col-md-12">
						<sj:submit cssClass="submitBt btn blue" value="Submit"
							targets="mainContentDiv" validate="true" formIds="addDayBookForm"
							onBeforeTopics="dayBookFormValidation" />
						<span id="printDiv" style="display: none;"> <sj:submit
								cssClass="submitBt btn green" targets="mainContentDiv"
								value="Submit & Print" validate="true" formIds="addDayBookForm"
								onclick="submitBookDetails()"
								onBeforeTopics="dayBookFormValidation"
								onCompleteTopics="addPrintDayBookDetails" />
						</span>
						<s:if test="%{dayBook.id != 0}">
							<button type="button" data-dismiss="modal" class="btn default">
								Cancel</button>
						</s:if>
						<s:else>
							<s:url id="doViewRecords" action="ajaxDoGetDayBookDetails"
								includeParams="all" escapeAmp="false" namespace="/admin">
							</s:url>
							<sj:a href="%{doViewRecords}" cssClass="btn default"
								targets="mainContentDiv">Cancel</sj:a>
						</s:else>
					</div>
				</div>
			</div>
		</div>
		</div>
</s:form>
<s:if test="%{dayBook.id != 0}">
	</div>
	</div>
</s:if>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/subscription/textcounter.js"> </script>
<script type="text/javascript" src="${pageContext.request.contextPath}/plugins/bootstrap-switch/static/js/bootstrap-switch.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
      /*  $('.numericDot').keypress(function (event) {
           return isNumber(event, this)
       }); */
       $('.numericDot').numeric( {allow : "."});
       if(isNonEmpty($("input#amount").val())){
    	 $("input#amount").val(parseFloat($("input#amount").val()));
       }else{
    	   $("input#amount").val("0");
       }
       
	$.destroyTopic('dayBookFormValidation');
	  var type='<s:property value="dayBook.type"/>';
	  if(type=="E"){
		  $('div#invoiceNumberDiv').show();
		  $('span#printDiv').show();
	  }else{
		  $('div#invoiceNumberDiv').hide();
		  $('span#printDiv').hide();
	  }
	
	 $("input:checkbox, input:radio:not('.toggle')").uniform();  
		FormComponents.init();
		FormAdvanced.init();
		changePageTitle("Add DayBook");
		var booktype = '<s:property value="dayBook.type"/>';
		if (booktype == 'I') {
			$('#income').attr("checked", 'checked');
		} else if (booktype == 'E') {
			$('#expend').attr("checked", 'checked');
		}else {
			$('#income').attr("checked", 'checked');
		}
		$.subscribe('dayBookFormValidation', function(event, data) {
			/*var desc = $('#description').val();	
			 if ((desc =="") || (desc ==null)) {
				alert('Please enter description.');
				 event.originalEvent.options.submit=false;	
			}  */
			var amount = $('#amount').val();
			if ((amount =="") || (amount ==null)) {
				alert('Please enter amount.');
				 event.originalEvent.options.submit=false;	
			} 
			/* if ($('#ddForm').is(':checked')) {
				var ddNum = $("#ddNumberFee").val();
				if (!isNonEmpty(ddNum) || ddNum == "DD Number") {
					alert("Please enter dd number.");
					event.originalEvent.options.submit=false;
				}
			} else if ($('#chForm').is(':checked')) {
				var chequeNum = $("#chequeNumber").val();
				if (chequeNum == null || chequeNum == "" || chequeNum == "Number") {
					alert("Enter Cheque Number");
					event.originalEvent.options.submit=false;
				}
			} */
			if ($('#addDayBookForm').valid()){
				$('button.close').click();
				return true;
			}
		});
		var paymenttypes = '<s:property value="dayBook.paymentType"/>';
		//alert(paymenttypes);
		paymentTypeMethodChange(paymenttypes);
	});
	function isNumber(evt, element) {
        var charCode = (evt.which) ? evt.which : event.keyCode
        if ((charCode != 45 || $(element).val().indexOf('-') != -1) &&  (charCode != 46 || $(element).val().indexOf('.') != -1) &&  (charCode > 57))
            return false;
        return true;
    } 
	function paymentTypeMethodChange(clickButton) {
		/* $('input:text').click( function(){
		        $(this).val('');
		    }); */
		//alert(clickButton);
		if (clickButton == 'D') {
			$("#chequeNumber").val('');
			$("#inputboxhideText").show();
			$("#checkinputboxhideText").hide();
			$("#bankinputboxhideText").show();
			//$('#ddForm').attr("checked", 'checked');
			$("#ddForm").parent('span').addClass("checked");
			$("#ddForm").attr("checked", true);
		} else if (clickButton == 'CH') {
			$("#ddNumberFee").val('');
			$("#checkinputboxhideText").show();
			$("#inputboxhideText").hide();
			$("#bankinputboxhideText").show();
			$("#chForm").parent('span').addClass("checked");
			$("#chForm").attr("checked", true);
		} else if (clickButton == 'C') {
			$("#chequeNumber").val('');
			$("#ddNumberFee").val('');
			$("#inputboxhideText").hide();
			$("#checkinputboxhideText").hide();
			$("#bankinputboxhideText").hide();
			$("#cashForm").parent('span').addClass("checked");
			$("#cashForm").attr("checked", true);
		}else{
			$("#cashForm").parent('span').addClass("checked");
			$("#cashForm").attr("checked", true);
		}
	}
	 
	  function submitBookDetails() {
		$('#paybtn').val('Print');
	}  
	$("#daybookReceiptNumber").autoCheck("${pageContext.request.contextPath}/admin/ajaxCheckDayBookReceiptNumber.do",
			{
				minChars : 1,
				min : "no"
	});
	 
	$('div.make-switch').find("label[for='income']").click(function(){
		  if($(this).parent().hasClass('switch-on')==true){
		   $('div#invoiceNumberDiv').show();
		   $('span#printDiv').show();
		  } else{
		     $('div#invoiceNumberDiv').hide();
		     $('span#printDiv').hide();
		  }
	});
	$("#amount").change(function(){
		if($("#amount").val()=='' || $("#amount").val()==0){
			alert("Pelase enter amount grater then 0 value.");
			$("#amount").val('');
		}
	}); 
	$.subscribe('addPrintDayBookDetails', function(event, data) {
		$('#printDaybookReport').submit();
		$.destroyTopic('addPrintDayBookDetails');
	});
</script>