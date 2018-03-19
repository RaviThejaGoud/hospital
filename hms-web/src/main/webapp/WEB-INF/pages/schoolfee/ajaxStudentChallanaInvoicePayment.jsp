<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/onload.js"> </script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/ems.js"> </script>

<s:if test="%{objectList != null && !objectList.isEmpty()}">
	<s:form action="ajaxPayStudentChallanaPaymentFee" id="addStudentPaymentFee" cssClass="form-horizontal" method="post" theme="simple" name="myform" namespace="/schoolfee">
	
	<s:hidden id="feeData" name="anyId" />
	<s:hidden name="tempId1" value="%{studentPayment.academicYear.id}" />
	<s:hidden name="description" value="studInvPaymt" />
	<s:hidden name="paymentType" value="P"></s:hidden>
	<s:hidden id="paybtn" name="plSubTopic"></s:hidden>
	
	
	<div class="form-body form-horizontal">
		<s:iterator value="objectList" begin="0" end="0">
		<s:hidden name="studentPayment.id" value="%{studentPaymentId}"></s:hidden>
		<s:hidden name="studentNumber" value='%{studentId}' />
		<s:hidden name="challanaPayment.id" value="%{challanaId}"></s:hidden>
		<s:hidden name=""></s:hidden>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4">
						 Receipt Number :
					</label>
					<div class="col-md-6">
					<label class="col-md-5" style="margin-top: 5px;">
						<s:property value="invoiceNumber"/>
						<s:hidden name="studentPayment.invoiceNumber" value="%{invoiceNumber}"></s:hidden>
					</label>
				</div>
			</div>
			</div>
			
			<div class="col-md-6">
				<div class="form-group">
						<label class="control-label col-md-4"> Challana Number :
						</label>
						<div class="col-md-5">
							<label class="col-md-4" style="margin-top: 5px;">
							<s:property value="challanaNumber"/></label> 
						<%-- <s:hidden name="challanaPayment.challanaNumber" value="%{tempId2}"></s:hidden>--%>
							
						</div>
					</div>
				
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
				<label class="control-label col-md-4">  Payment Date :
				</label>
				<div class="col-md-6">
					<label class="col-md-5" style="margin-top: 5px;">
					 <s:property value="todayDateStr"/></label>
				</div>
			</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
				<label class="control-label col-md-4">  Challana Date :
				</label>
				<div class="col-md-6">
					<label class="col-md-5" style="margin-top: 5px;">
					 <s:property value="challanaDateStr"/></label>
				</div>
			</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4">
						Payable Amount :
					</label>
					<div class="col-md-6">
					<label class="col-md-5" style="margin-top: 5px;">
						<s:property value="paidAmount"/>
						<s:hidden name="studentPayment.paidAmount" value="%{paidAmount}"></s:hidden>
					</label>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4">
						Discount :
					</label>
					<div class="col-md-5">
					<label class="col-md-4" style="margin-top: 5px;">
						<s:property value="discountAmount"/>
						<s:hidden name="studentPayment.discountAmount" value="%{discountAmount}"></s:hidden>
					</label>
					</div>
				</div>
			</div>
		</div>
		</s:iterator>
		<div class="row">
			
		</div>
		
		<s:if test='%{#session.previousYear=="N"}'>
			<s:if test="%{description == 'studInvPaymt'}">
				<div class="form-actions fluid">
					<div class="col-md-6">
						<div class="col-md-offset-4 col-md-12">
							<sj:submit cssClass="submitBt btn blue" value="Verify" indicator="indicator" targets="searchStudentsForm112"
								onBeforeTopics="studentPaymentFormValidation" validate="true" onclick="payfees()" formIds="addStudentPaymentFee" />
							
							<sj:submit cssClass="submitBt btn green long" value="Verify & Print" indicator="indicator" onclick="payfee()"
							targets="searchStudentsForm112" onBeforeTopics="studentPaymentFormValidation" formIds="addStudentPaymentFee"
							onCompleteTopics="doPrintStudentInvoice" validate="true" />				
							
							<s:url id="doCancelStudent" action="ajaxManageGeneratChallana" namespace="/schoolfee" includeParams="all" escapeAmp="false">
								<s:param name="academicYearId" value="%{0}"></s:param>
								<s:param name="anyTitle" value="%{anyTitle}"></s:param>
								<s:param name="classSectionId" value="%{classSectionId}"></s:param>
							</s:url>
							<sj:a href="%{doCancelStudent}" cssClass="btn default" targets="searchStudentsForm112">Cancel</sj:a>
						</div>
					</div>
				</div>
			</s:if>
		</s:if>
	</div>
	<div class="spaceDiv"></div>
	<s:set name="schoolTermsId" value=""></s:set>
	<s:set name="schoolFeeSettingId" value=""></s:set>
	<s:set name="feeParticularId" value=""></s:set>
	<table class="table table-bordered table-hover" id="sample_2">
		<thead>
			<th>
				Particular Name							
			</th>
			<th>
				Total Amount
			</th>
			<th>
				Payment Amount
			</th>
			<th>
				Discount Amount
			</th>
			
		</thead>
		<tbody>
			<s:set name="schoolTermsId" value=""></s:set>
			<s:set name="schoolFeeSettingId" value=""></s:set>
			<s:set name="feeParticularId" value=""></s:set>
			<s:iterator value="objectList">
				
				
					<s:if test="%{feeSettingId != #schoolFeeSettingId}">
						<tr>
							<td colspan="4">
								<h5>
									<center>
										<s:property value="settingName" />
									</center>
								</h5>
							</td>
						</tr>
					</s:if>
					<s:if test="%{schoolTermId != #schoolTermsId}">
						<tr>
							<td colspan="4">
								<center>
									<strong> 
										Term Name :
										<span id='<s:property value="schoolTermId" />'>
											
										</span> 
									</strong>
									<s:property value="termName" />   
									<strong>   Due date :</strong> <s:property value="dueDateStr"/> <strong><s:if test="%{daysPending != 0}"> Over Due Days :</strong> <s:property value="daysPending"/></s:if>
								</center>
							</td>
						</tr>
					</s:if>
					<tr id="termAndParticularDetails">
						<td>
							<span class="schoolTermId" id="<s:property value='schoolTermId'/>"></span>
							<span class="feeTypeId" id="<s:property value='feeTypeId'/>"></span>
							<s:property value="feeType"/>
						</td>
						<td>
							<span class="feeAmount" id="<s:property value='feeAmount'/>"></span>
							<s:property value="feeAmount"/>
						</td>
						<td>
							<span class="paymentAmount" id="<s:property value='paymentAmount'/>"></span>
							<s:property value="paymentAmount"/>
						</td>
						<td>
							<span class="discountAmount" id="<s:property value='discountAmount'/>"></span>
							<s:property value="discountAmount"/>
						</td>
					</tr>
					<s:set name="schoolTermsId" value="%{schoolTermId}"></s:set>
				<s:set name="schoolFeeSettingId" value="%{feeSettingId}"></s:set>
			</s:iterator>
		</tbody>
	</table>
	</s:form>
</s:if>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/subscription/textcounter.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	$("#studChallanaNumber").val($('span.challanaNumber').attr('id'));
	$.destroyTopic('studentPaymentFormValidation');
	$('div#reasonForDiscount').hide();
	FormComponents.init(); 
	$('.numeric').numeric();
});
$.subscribe('studentPaymentFormValidation',function(event, data) {
	$('.submitBt').attr('disabled', 'disabled');
	var jsonObj = [];
	var ids = '';
	var schoolTermId=null;
	var feeTypeId=null;
	var feeAmount=0;
	var paymentAmount=0;
	var discountAmount=0;
	var concessionAmount=0;
	var paymentType = $('#feePaymentType').attr('class');
	$('tr#termAndParticularDetails').each(function(){
		schoolTermId = $(this).find("span.schoolTermId").attr('id');
		feeTypeId = $(this).find("span.feeTypeId").attr('id');
		feeAmount = $(this).find("span.feeAmount").attr('id');
		paymentAmount = $(this).find("span.paymentAmount").attr('id');
		discountAmount = $(this).find("span.discountAmount").attr('id');
		concessionAmount = $(this).find("span.concessionAmount").attr('id');
		if (discountAmount != 0 || paymentAmount != 0) {
			jsonObj.push( {
				"termId" : schoolTermId,
				"feeTypeId" : feeTypeId,
				"totalAmount" : feeAmount,
				"payableAmount" : paymentAmount,
				"discountAmount" : discountAmount,
				"concessionAmount" : concessionAmount
			});
		}
	});
	//alert(JSON.stringify(jsonObj))
	$("#feeData").val(JSON.stringify(jsonObj));
});
$.subscribe('doPrintStudentInvoice', function(event, data) {
	$('#printReport').submit();
	$.destroyTopic('doPrintStudentInvoice');
});
function payfees() {
	$('#paybtn').val('Pay');
}
function payfee() {
	$('#paybtn').val('Print');
}
</script>