<%@ include file="/common/taglibs.jsp"%>
<s:if test='%{feeModuleUsege == "W" }'>
	<div class="portlet-body form">
		<s:form id="addNewFineFee" action="ajaxAddOrUpdateFineFeePayment" namespace="/schoolfee" method="post" theme="simple" cssClass="form-horizontal">
			<s:hidden id="tempId2" name="anyId"></s:hidden>
			<s:hidden id="invoiceMaxVal" name="tempId"></s:hidden>
			<s:hidden name="tempString" cssClass='tempString' />
			<s:hidden name="empId" cssClass="totalOtherFee"></s:hidden>
			<div class="form-body">
				<h4 class="form-section">Add Other Fee</h4>
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-3">First Name:</label>
							<div class="col-md-9">
								<p class="form-control-static  bold pageTitle">
										<s:property value="anyTitle" />
								</p>
							</div>
						</div>
					</div>
					<div class="col-md-6">
					     <div class="form-group">
						<label class="control-label col-md-3">Admission Number:</label>
							<div class="col-md-9">
								<p class="form-control-static  bold pageTitle">
										<s:property value="admissionNumber"/>
								</p>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-3">Payment Date</label>
							<div class="col-md-9">
								<div data-date-start-date="" data-date-end-date="0d" data-date-format="mm/dd/yyyy"
									class="input-group input-medium date date-picker">
									<input type="text" id="entryDate" readonly="readonly" name="fineFee.paymentDate"
										class="required form-control input-medium" /> 
										<span class="input-group-btn">
										<button type="button" class="btn default"><i class="fa fa-calendar"></i></button>
									</span>
								</div>
								<span class="help-block">(MM/DD/YYYY)</span>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-3">Invoice Number</label>
							<div class="col-md-9">
								<sj:textfield maxlength="12" name="fineFee.invoiceNumber" id="invoiceNumber" placeholder="Invoice Number"
									cssClass="numeric form-control input-medium invoiceNumber" />
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="form-body">
				<h4 class="form-section">Particulars</h4>
				<div id='TextBoxesGroup'>
					<div id="TextBoxDiv1" class="otherFeesData">
						<span id='' class='otherFeeId1 otherIds'></span>
						<div class="row">
							<div class="col-md-5">
								<label> Narration : </label>
								<sj:textfield name="fineFee.description"  id='otherFeeName1'
									cssClass="otherFee required form-control input-large" maxlength="40"></sj:textfield>
							</div>
							<div class="col-md-2 timeEntry">
								<label> Quantity : </label>
								<sj:textfield name="fineFee.quantity" id='otherFeeQuantity1' cssClass="numeric form-control quantity"
									maxlength="10" cssStyle="width :65px;"></sj:textfield>
							</div>

							<div class="col-md-2">
								<label> Amount : </label>
								<sj:textfield name="fineFee.fineFeeAmount" id='otherFeeAmount1'
									cssClass="numericDot required form-control input-small otherFeeAmount" maxlength="10" onkeyup="javascript:onkeyUp(this)"></sj:textfield>
							</div>
							<div class="col-md-2" style="margin-top: 25px;">
								<a title="Add Particular" style="cursor: pointer; width: 78px;" id="insertValues" class="normalAdd btn btn-xs green"
									onclick="insertValue(1,'isNew','<s:property value="tempBoolean"/>')" >  <i class="fa fa-plus"></i>Add New </a>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-7">
						&nbsp;
					</div>

					<div class="col-md-2">
						<label> Total : </label>
						<sj:textfield name="empId" 
							cssClass="numericDot required form-control input-small totalOtherFee" maxlength="10" disabled="true"></sj:textfield>
					</div>
					
				</div>
			</div>
	</div>
	<div class="form-actions fluid">
		<div class="col-md-6">
			<div class="col-md-offset-6 col-md-12">
				<sj:submit cssClass="submitBt btn blue" value="Pay" targets="mainContentDiv" onCompleteTopics="doClosePopup"
					onBeforeTopics="studentOtherFeeFormValidation" validate="true" formIds="addNewFineFee" />

				<sj:submit cssClass="submitBt btn green long" value="Pay & Print" validate="true" targets="mainContentDiv"
					onCompleteTopics="doPrintStudentFineFeeInvoice" onBeforeTopics="studentOtherFeeFormValidation" />
				<s:url id="doCancelStudent" action="ajaxSearchStudentsForMakePayment" namespace="/schoolfee" includeParams="all" escapeAmp="false">
					<s:param name="academicYearId" value="%{0}"></s:param>
					<s:param name="anyTitle" value="%{anyTitle}"></s:param>
					<s:param name="classSectionId" value="%{classSectionId}"></s:param>
				</s:url>
				<sj:a href="%{doCancelStudent}" cssClass="btn default" targets="searchStudentsForm112">Cancel</sj:a>
			</div>
		</div>
	</div>
</s:form>
</s:if>
	<div class="spaceDiv"></div>
	<div class="spaceDiv"></div>
	<div id="fineFeeListCont">
		<jsp:include page="/WEB-INF/pages/schoolfee/ajaxFineFeeList.jsp"></jsp:include>
	</div>



<script type="text/javascript">
$(document).ready(function() {
	$.destroyTopic('studentFineFeeFormValidation');
	FormComponents.init();
	var formattedDate = $.datepicker.formatDate('mm/dd/yy',new Date());
	$("#entryDate").val(formattedDate);
	$("#invoiceNumber").val($('#invoiceMaxVal').val());
	$('.numeric').numeric();
	$('.numericDot').numeric({
		allow : "."
	});
	$("#invoiceNumber").autoCheck("${pageContext.request.contextPath}/admin/ajaxCheckStudentInvoiceNumber.do",
	{
		minChars : 1,
		min : "no"
	});
						
	$.subscribe('studentOtherFeeFormValidation',function(event, data) {
		var otherFeeId = '';
		var quantity = 0;
		var otherFeeName = '';
		var otherFeeAmount = '';
		var jsonObj = [];
		var unSelectedSchIds = '(';
		var i = 0;
		$('div.otherFeesData').each(function() {
			otherFeeName = $(this).find('.otherFee').val();
			quantity = $(this).find('.quantity').val();
			otherFeeId = $(this).find("span.otherIds").attr('id');
			otherFeeAmount = $(this).find('.otherFeeAmount').val();
			if (isNonEmpty(otherFeeId)) {
				unSelectedSchIds += otherFeeId+ ",";
			}
			if (isNonEmpty(otherFeeName)) {
				jsonObj.push({
					"otherFeeId" : otherFeeId,
					"otherFeeName" : otherFeeName,
					"quantity" : quantity,
					"otherFeeAmount" : otherFeeAmount
				});
			}
			quantity = 0;
		});
		unSelectedSchIds += '0)';
		$('.tempString').val(JSON.stringify(jsonObj));
		$('.otherFeeIds').val(unSelectedSchIds);
		var response = $('.tempString').val();
		if (response == '[]') {
			//alert("Please create at least one boarding point.");
			event.originalEvent.options.submit = false;
		}

		/* var otherFeeQuantity1 = $("#otherFeeQuantity1").val();
		var arrivalTimeInput = $("#arrivalTimeInput").val();
		var beginningTime = moment(otherFeeQuantity1, 'h:mma'); */

		$('ul.nav-tabs li').removeClass('active');
		$('li#doAddRoutes').addClass('active');
	});
	$.subscribe('doPrintStudentFineFeeInvoice', function(event, data) {
		$('#printFineFeeReport').submit();
		$.destroyTopic('doPrintStudentFineFeeInvoice');
	});
});
function onkeyUp(){
	var totalOtherFee=0;
	var feeAmount=0;
	$('div.otherFeesData').each(function() {
		feeAmount = $(this).find('.otherFeeAmount').val();
		if(isNonEmpty(feeAmount))
		totalOtherFee += parseFloat(feeAmount);
	});
	$('.totalOtherFee').val(totalOtherFee);
}
function insertValue(counter, status, property) {
		var isNew = 808080;
		var countLength = $(".otherFee").length;
		if (status == 'isExist') {
			countLength = counter;
		}
		countLength = countLength + 1;
		var newTextBoxDiv = $(document.createElement('div')).attr("id",'TextBoxDiv' + countLength);
		newTextBoxDiv.addClass('otherFeesData');
		property = "'" + $("span.feeSetting").attr("id") + "'";
		newTextBoxDiv.html('<div class="spaceDiv"></div><div class="row"><span id="" class="otherFeeId'
							+ countLength
							+ ' otherIds"></span><div class="col-md-5"><label>Narration : </label>'
						+ '<input type="text" style="width:200px;padding:4px;" name="fineFee.description'
							+ countLength
							+ '" id="otherFeeName'
							+ countLength
							+ '" value=""  class="otherFee required form-control input-large"/> </div>'
						+ '<div class="col-md-2 timeEntry"><label>Quantity : </label>'
						+ '<input type="text"  name="fineFee.quantiry'
							+ countLength
							+ '" id="otherFeeQuantity'
							+ countLength
							+ '"   style="width:65px;padding:4px;margin-bottom:10px;" class="numeric form-control quantity"></input> </div>'
						+ '<div class="col-md-2"> <label>Amount : </label>'
						+ '<input type="text" style="width:65px;padding:4px;" name="fineFee.fineFeeAmount'
							+ countLength
							+ '" id="otherFeeAmount'
							+ countLength
							+ '" value="" class="numericDot otherFeeAmount required form-control input-small" onkeyup="javascript:onkeyUp(this)" /></div>'
						+ '<div class="col-md-2" style="margin-top:25px;"><a title="Add Particular" style="cursor: pointer; width: 78px;" id="insertValues" class="normalAdd btn btn-xs green" onclick="insertValue('
						+ countLength
						+ ','
						+ isNew
						+ ','
						+ property
						+ ')"><i class="fa fa-plus"></i>Add New</a></div>'
						+ '<div class="col-md-1" style="margin-top:25px;"><a title="Delete" indicator="indicator" style="cursor: pointer;position: absolute;width:60px;" id="removeValues" class="btn btn-xs red normalDelete"  onclick="removeValue('
						+ countLength
						+ ',this)"><i class="fa fa-times"></i>Delete</a> </div>'
						+ '</div><div class="spaceDiv"></div>');

		if (status == 'isExist') {
			newTextBoxDiv.insertAfter("#TextBoxDiv" + counter);
			counter++;
			return counter;
		} else {
			newTextBoxDiv.insertAfter("#TextBoxDiv" + counter);
		}
		$('.numericDot').numeric({
			allow : "."
		});
		$('.numeric').numeric();
	}
	function removeValue(counter, event) {
		var countLength = $(".otherFee").length;
		if (counter <= 1) {
			alert("You cann't remove this departure point.Update this departure point details.");
			return false;
		}
		var otherFeeId = $('span.otherFeeId' + counter).attr('id');
		if ($(event).next('.question').length <= 0) {
			$(event).after('<div class="question" style="margin-left:-6em">Are You Sure?<br/> <span class="yes">Yes</span><span class="cancel">Cancel</span></div>');
		}
		$(event).next('.question').animate({
			opacity : 1
		}, 300);
		$('.yes').unbind('click');
		$('.yes').bind('click',function() {
			var prdDiv = $(this).parents('.question');
			prdDiv.html('Processing...');
			if (isNonEmpty(otherFeeId)) {
				$.ajax({
					url : jQuery.url.getChatURL("/admin/ajaxCheckotherFeeAssignedStatus.do?tempId2="+ otherFeeId),
					cache : false,
					success : function(response) {
						if (isNonEmpty(response)) {
							if (response.tempBoolean)
								$("#TextBoxDiv"+ counter).remove();
							else {
								otherFeeErrorMsg();
								return false;
							}
						} else {
							otherFeeErrorMsg();
							return false
						}
					}
				});
			} else {
				$("#TextBoxDiv" + counter).remove();
			}
			onkeyUp();
		});
		$('.cancel').unbind('click');
		$('.cancel').bind('click', function() {
			$(this).parents('.question').fadeOut(300, function() {
				$(this).remove();
			});
			return false;
		});
		
	}
</script>