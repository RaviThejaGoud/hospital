<%@ include file="/common/taglibs.jsp"%>
<jsp:include page="/common/messages.jsp"></jsp:include>
<s:form action="ajaxAddStudentPocketMoney" theme="simple"
	id="studentform" cssClass="form-horizontal" method="post"
	namespace="/schoolfee">
	<div class="form-body">
	<span class="label label-danger"> NOTE : </span>&nbsp; Pocket money is applicable only for hostel students.
	<div class="spaceDiv"></div>
	<s:if test="%{(smsAlloted == 0) || (smsAlloted <= smsCnt)}">
		<div style="color: red;" class="alert alert-info">
			You have been used all your allotted
			<s:property value="smsAlloted" />
			SMS. Please contact EazySchool support team (080-46620999) to recharge your SMS.
		</div>
	</s:if>
	<s:if test="%{studentsList != null && !studentsList.isEmpty()}">
			<s:if test="%{customer.checkMobileService == false}">
          	<div id="enableEmailService">
				<span class="label label-danger">ALERT :</span>&nbsp;&nbsp; SMS service disabled, enable service.<s:url id="urlSendSmsLink" action="ajaxDoSchoolInformation" namespace="/admin" />
				<sj:a href="%{urlSendSmsLink}" targets="mainContentDiv" cssClass="ajaxify title AMCA ">  Enable Service </sj:a>
			</div>
				<div class="spaceDiv"></div>
			</s:if>
			<div class="form-group">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-5"> <span
						class="required">*</span>Transaction Mode :
					</label>
					<div class="col-md-6">
						<div class="radio-list">
							<label class="radio-inline"> <input type="radio" id="depositOption"
								class="radio selectType" value="D" onclick="checkTransactionType('D');"
								name="studentPocketMoney.status" checked="checked">
								Deposit
							</label> <label class="radio-inline"> <input type="radio" id="withDrawalOption"
								class="radio selectType" value="W" onclick="checkTransactionType('W');"
								name="studentPocketMoney.status"> Withdraw
							</label>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="form-group">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-5"> <span
						class="required">*</span> Student Name (Admission No) :
					</label>
					<div class="col-md-6">
						<select name="studNameAndAdmiNum" id="select2_sample4"
							class="required form-control select2" onchange="javascript:selectedTransactionResults(this.value);">
							<s:iterator value="studentsList">
								<option value='<s:property />'>
									<s:property />
								</option>
							</s:iterator>
						</select>
						<%--<select name="tempId" id="select2_sample4"
							class="required form-control select2">
							<s:iterator value="studentsList">
								<option value='<s:property value="id"/>'>
									<s:property value="studentNameAndAdmissionNumber" />
								</option>
							</s:iterator>
						</select>
					--%>
					</div>
				</div>
			</div>
		</div>
		<div class="form-group">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-5"> <span
						class="required">*</span> Select Date :
					</label>
					<div class="col-md-7">
						<div data-date-start-date="" data-date-format="mm/dd/yyyy" class="input-group input-medium date date-picker">
							<input type="text" readonly="readonly"
								class="form-control fdate" id="transactionDate"
								name="studentPocketMoney.transactionDate"> <span
								class="input-group-btn">
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
		<div id="depositDiv">
			<div class="form-group">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-5"> <span
							class="required">*</span> Deposit Amount :
						</label>
						<div class="col-md-7">
							<sj:textfield name="studentPocketMoney.amount" id="depositAmount"
								cssClass="numeric form-control input-medium as-input"
								maxlength="7"></sj:textfield>
						</div>
					</div>
				</div>
			</div>
			<div class="form-group">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-5">Depositor Name : </label>
						<div class="col-md-7">
							<sj:textfield name="studentPocketMoney.depositorName"
								id="depositorName" cssClass="form-control input-medium as-input"
								maxlength="50"></sj:textfield>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div id="withdrawalDiv" style="display: none">
			<div class="form-group">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-5"> Available Amount :
						</label>
						<div class="col-md-7">
							<sj:textfield name="tempString" id="availableBalance"
								cssClass="form-control input-medium as-input" maxlength="50"></sj:textfield>
						</div>
					</div>
				</div>
			</div>
			<div class="form-group">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-5"> <span
							class="required">*</span> Withdrawal Amount :
						</label>
						<div class="col-md-7">
							<sj:textfield name="anyId" id="withdrawalAmount"
								cssClass="numeric form-control input-medium as-input"
								maxlength="7"></sj:textfield>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="form-actions fluid">
			<div class="col-md-offset-4 col-md-9">
				<sj:submit cssClass="btn blue" value="Submit" indicator="indicator"
					formIds="studentform" targets="searchStudentsForm112" onBeforeTopics="validateWithdrawForm"
					validate="true" />
			</div>
		</div>
		<div>&nbsp;</div>
		<div>&nbsp;</div>
		<div id="transactionsHistoryDiv">
			<jsp:include
				page="/WEB-INF/pages/schoolfee/ajaxGetStudentTransactionHistory.jsp"></jsp:include>
		</div>
	</s:if>
	</div>
	<s:else>
		<div class="alert alert-info">Currently there are no students with hostel mode.</div>
	</s:else>
</s:form>
<script language="JavaScript" type="text/javascript">
$(document).ready(function() {
	FormComponents.init();
	$('.numeric').numeric();
	$("input:checkbox, input:radio:not('.toggle')").uniform();  
	 $.destroyTopic("validateWithdrawForm");
	 var type = '<s:property value="anyTitle"/>';
	 if(isNonEmpty(type) && type =="W"){
			 $("input#withDrawalOption").parent('span').addClass('checked');
			 $("input#withDrawalOption").attr("checked",true);
			 $("input#depositOption").parent('span').removeClass('checked');
			 checkTransactionType(type);
	 }else{
		 checkTransactionType('D');
	 }
	
});

function checkTransactionType(type){
	var selectedStudent = $("select#select2_sample4").val();
	var formattedDate = $.datepicker.formatDate('mm/dd/yy',new Date());
	$("#transactionDate").val(formattedDate);
	if(type =="W"){
		$("div#withdrawalDiv").show();
		$("input#withdrawalAmount").val('');
		$("div#depositDiv").hide();
		$("input#availableBalance").attr('disabled','disabled');
		$("input#depositedby").attr('disabled','disabled');
		selectedTransactionResults(selectedStudent,type);
	}else{
		$("div#withdrawalDiv").hide();
		$("div#depositDiv").show();
		$("input#depositAmount").val('');
		$("input#depositorName").val('');
		$("input#availableBalance").removeAttr('disabled');
		selectedTransactionResults(selectedStudent,type);
	}
}

$.subscribe("validateWithdrawForm",function(event,data){
	var availableBalance = $('input#availableBalance').val();
	var withdrawalamnt = $('input#withdrawalAmount').val();
	var depositAmount = $('input#depositAmount').val();
	var selectedOption = $('input.selectType:radio:checked').val();
	var transactionDate = $('input#transactionDate').val();
	if(isNonEmpty(transactionDate)){
	if(selectedOption == "W"){
	 if(Number(availableBalance) == 0){
		 alert("You do not have credit amount.")
		 event.originalEvent.options.submit=false;
	 }
	 else if(Number(withdrawalamnt) == 0 || Number(availableBalance) < Number(withdrawalamnt) ){
		 alert("With drawn amount should be lesser than or equal to available(deposited) amount.");
		event.originalEvent.options.submit=false;
	 }
	 else{
		 event.originalEvent.options.submit=true;
	 }
	}
	else{
		if(Number(depositAmount) == 0){
			alert("Please enter the credit amount.");
			event.originalEvent.options.submit=false;
		}else{
			event.originalEvent.options.submit=true;
		}
	}
	}
	else{
		 alert("Please select the date.")
		 event.originalEvent.options.submit=false;
	}
});

function selectedTransactionResults(data,type){
	var selectedValue = data;
	if(type ==undefined){
		type = $('input.selectType:radio:checked').val();
	}
	if(isNonEmpty(selectedValue) && isNonEmpty(type)){		
		var pars = "studentName=" + selectedValue +"&status="+type;
		$("#transactionsHistoryDiv")
				.html(
						'<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		var url = jQuery.url.getChatURL("/schoolfee/ajaxGetStudentTransactionHistory.do");
		$.ajax( {
			url : url,
			cache : false,
			data : pars,
			success : function(html) {
				$("#transactionsHistoryDiv").html(html);
			}
		});
	  }

}
$('div#enableEmailService').click(function() {
	window.location.hash = "target=ES.ajaxify AMCS";
	$('li#studentInvoiceNav').parent('li').removeClass('active');
	$('li#schoolSettingsDiv').addClass('open active');
	$('li#manageSchool').addClass('active');
	$('li#manageSchool a').click();
});

/* $('a.enableEmailService').click(function(){
	window.location.hash="target=ES.ajaxify AMCS";
	window.location.reload();
}); */
</script>