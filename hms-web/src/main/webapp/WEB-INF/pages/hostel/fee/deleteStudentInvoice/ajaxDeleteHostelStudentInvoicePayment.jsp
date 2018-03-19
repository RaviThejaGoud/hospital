<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/messages.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/ems.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/map/jquery.listnav-2.1.js"></script>
<script Language="Javascript1.2" type="text/javascript">
//+ Jonas Raoni Soares Silva
//@ http://jsfromhell.com/string/wordwrap [v1.0]

String.prototype.wordWrap = function(m, b, c) {
	var i, j, l, s, r;
	if (m < 1)
		return this;
	for (i = -1, l = (r = this.split("\n")).length; ++i < l; r[i] += s)
		for (s = r[i], r[i] = ""; s.length > m; r[i] += s.slice(0, j)
				+ ((s = s.slice(j)).length ? b : ""))
			j = c == 2 || (j = s.slice(0, m + 1).match(/\S*(\s)?$/))[1] ? m
					: j.input.length - j[0].length || c == 1 && m
							|| j.input.length
							+ (j = s.slice(m).match(/^\S*/)).input.length;
	return r.join("\n");
};
</script>
<s:if test="%{classFeeList != null && !classFeeList.isEmpty()}">
<span id="payType" class="<s:property value='paymentType'/>"/>
<div id="searchStudentsForm112" class="commonFormTabs">
	<div id="tabWrapper">
		<s:form action="ajaxDeleteHostelStudentInvoicePayment" id="deletePaymentFee" method="post" theme="css_xhtml" name="myform">
			<input type="hidden" name="studentNumber" value='<s:property value="student.id" />'/>
			<s:hidden id="termIdsIds" name="invoiceNumber"  value="%{hostelStudentPayment.invoiceNumber}" />
			<s:set name="schoolTermsId" value=""></s:set>
			<s:set name="feeTypeId" value=""></s:set>
			<fieldset>
				<div class="grid_15">
					<div class="grid_15">&nbsp;</div>
					<div class="grid_15">
								<div class="grid_7">
									<label class='labelRight'>
										Paid Discount :
									</label>
								</div>
								<div class="grid_8">
									<s:property value="hostelStudentPayment.paidAmount" />
								</div>
							</div>
							<div class="grid_15">
								<div class="grid_7">
									<label class='labelRight'>
										Discount Discount :
									</label>
								</div>
								<div class="grid_8">
									<s:property value="hostelStudentPayment.discountAmount" />
								</div>
							</div>
							<div class="grid_15">
								<div class="grid_7">
									<label class='labelRight'>
										Invoice Number :
									</label>
								</div>
								<div class="grid_8">
									<s:property value="hostelStudentPayment.invoiceNumber" />
								</div>
							</div>
							<div class="grid_15">
								<div class="grid_7">
									<label class='labelRight'>
										Payment Type :
									</label>
								</div>
								<div class="grid_8">
									<s:if test='%{hostelStudentPayment.paymentType == "D"}'>
										DD
									</s:if>
									<s:elseif test='%{hostelStudentPayment.paymentType == "CH"}'>
										Cheque
									</s:elseif>
									<s:else>
										Cash
									</s:else>
								</div>
							</div>
							<div class="grid_15">
								<s:if test='%{hostelStudentPayment.paymentType == "D"}'>
									<div class="grid_7">
										<label class='labelRight'>
											DD Number :
										</label>
									</div>
									<div class="grid_8">
										<s:property value="hostelStudentPayment.ddNumber" />
									</div>
									<div class="grid_15 &nbsp;"></div>
									<div class="grid_7">
										<label class='labelRight'>
											Bank Name :
										</label>
									</div>
									<div class="grid_8">
										<s:property value="hostelStudentPayment.bankMaster.bankName" />
									</div>
									<div class="grid_15 &nbsp;"></div>
									<div class="grid_7">
										<label class='labelRight'>
											Bank Branch Name :
										</label>
									</div>
									<div class="grid_8">
										<s:property value="hostelStudentPayment.branchName" />
									</div>
								</s:if>
								<s:elseif test='%{hostelStudentPayment.paymentType == "CH"}'>
									<div class="grid_7">
										<label class='labelRight'>
											Cheque Number :
										</label>
									</div>
									<div class="grid_8">
										<s:property value="hostelStudentPayment.chequeNumber" />
									</div>
									<div class="grid_15 &nbsp;"></div>
									<div class="grid_7">
										<label class='labelRight'>
											Bank Name :
										</label>
									</div>
									<div class="grid_8">
										<s:property value="hostelStudentPayment.bankMaster.bankName" />
									</div>
									<div class="grid_15 &nbsp;"></div>
									<div class="grid_7">
										<label class='labelRight'>
											Bank Branch Name :
										</label>
									</div>
									<div class="grid_8">
										<s:property value="hostelStudentPayment.branchName" />
									</div>
								
								</s:elseif>
								</div>
					<div class="grid_15">
						<div class="grid_7">
							<label class='labelRight'>
								<span class="required">*</span>Description For Delete :
							</label>
						</div>
						<div class="grid_8">
						<sj:textarea name="description" id="deleteDiscription"   required="true"
								cssClass="textfield required"  cols="3" rows="3"></sj:textarea>
						</div>
					</div>
					<div class="grid_13">
						<s:url id="doCancelSInvoice" action="ajaxViewHostelStudentInvoiceModifyers"
							namespace="/hostel" includeParams="all" escapeAmp="false">
							<s:param name="id" value="%{id}" />
						</s:url>
						<sj:a href="%{doCancelSInvoice}" cssClass="cancelButton"
							indicator="indicator" targets="searchStudentsForm112">Cancel</sj:a>
							
							<sj:submit   cssClass="submit small" value="Delete"
								indicator="indicator" targets="searchStudentsForm112"
								onClickTopics="deleteFormValidation" validate="true"
								formIds="deletePaymentFee" />
					</div>
				</div>
			</fieldset>
			<div id="tabNavigation" style="display: none;" >
				<ul>
				</ul>
			</div>
			<div id="steps">
				<s:iterator value="classFeeList">
					<input type="hidden" id="divInnerHtml<s:property value="studentPaymentId"/>" value="<s:property value="paymentTypeStr" />"></input>
					<s:if test="%{hostelTermId != #hostelTermId}">
						<s:if test='%{#hostelTermId == ""}'>
							<fieldset class="step">
						</s:if>
						<s:else>
							</fieldset>
							<fieldset class="step">
						</s:else>
							<script type="text/javascript">
								addNewTab2Nav(
										'<s:property value="termName" />',
										'<s:property value="fromMonthName" />',
										'<s:property value="toMonthName" />','<s:property value="hostelTermId" />');
							</script>
						<s:set var="paymentStatus" value="P" />
						<div class="grid_14">
							<b style="color: #806517;">Due Date: <s:property
									value="dueDateStr" /> </b>
							<div style="border: none; float: right;">
								<s:if test="%{hostelFeeTypeList!= null || hostelFeeTypeList!= null}">
									<s:iterator value="hostelFeeTypeList">
									 <s:if test='%{invoiceNumber > 0}'>
										<a id="noPrint"
                                            onClick="javascript:printPreview('<s:property value='studentId'/>',
                                            '<s:property value='createdDateStr'/>',
                                            '<s:property value='invoiceNumber'/>')";
                                            target="_new"><img style="" src="../images/index.jpg">
                                            Invoice : <s:property value="invoiceNumber" /> </a>
									 </s:if>
									</s:iterator>
								</s:if>
							</div>
						</div>
						<div class="grid_14 th">
							<div class="grid_5">
								Particulars
							</div>
							<div class="grid_2">
								Amount(
								<del>
									<b style="font-size: 10px;">&#2352;</b>
								</del>
								)
							</div>

							<div class="grid_2">
								Paid Amount
							</div>
							<div class="grid_3">
								Discount Amount
							</div>
							<div class="grid_1">
								Status
							</div>
							<!--<div class="grid_2">
								Payment Type
							</div>
							<div class="grid_2">
								Payment Date
							</div>-->
						</div>
					</s:if>
					<s:if test='%{paymentStatus == "P"}'>
						<div class="grid_14 green row">
					</s:if>
					<s:else>
						<div class="grid_14 normal row">
						<script type="text/javascript">
								highLightFeeDueTab('<s:property value="hostelTermId" />','<s:property value="fromMonthName" />','<s:property value="toMonthName" />');
							</script>
					</s:else>
					<div class="grid_7">
						<div class="grid_5">
							<s:property value="feeType" />
						</div>

						<div class="grid_2">
							<del>
								<b style="float: left;">&#2352;</b>
							</del>
							<c:set value="${hoastelFeeTotal}" var="priceAmt" />
							<fmt:formatNumber value="${priceAmt}" type="currency" pattern="##,##,###.00" var="balance" />
							<c:out value="${balance}" />
						</div>
					</div>
					<div class="grid_2">
						<s:if test='%{paidAmount > 0 || paidAmount == null}'>
							<del>
								<b style="float: left;">&#2352;</b>
							</del>
							<c:set value="${paidAmount}" var="priceAmt" />
							<fmt:formatNumber value="${priceAmt}" type="currency" pattern="##,##,###.00" var="balance" />
							<c:out value="${balance}" />
						</s:if>
						<s:else>
							&nbsp;
						</s:else>
					</div>
					<div class="grid_3">
						<s:if test='%{discountAmount > 0 || discountAmount == null}'>
							<del>
								<b style="float: left;">&#2352;</b>
							</del>
							<c:set value="${discountAmount}" var="priceAmt" />
							<fmt:formatNumber value="${priceAmt}" type="currency" pattern="##,##,###.00" var="balance" />
							<c:out value="${balance}" />
						</s:if>
						<s:else>
							&nbsp;
						</s:else>
					</div>
					<div id="chkBoxFeeSelectedIds<s:property value="feeId"/>" style="display: none;">
						<s:if test='%{paymentTotalAmount == 0 || paymentTotalAmount == null}'>
								<s:property value="hoastelFeeTotal"/>
								</s:if>
								<s:else>
									<s:property value="studentParticularTotal"/>
								</s:else>
					</div>
					<div class="grid_1">
						<s:if test='%{paymentStatus == "P"}'>
							Paid 
						</s:if>
						<s:else>
							<input type="checkbox" name="chkBoxFeeSelectedIds" class="admisionPayStatus<s:property value='hostelTermId'/>" value="<s:property value="feeId"/>" />
						</s:else>
					</div>
					<div class="grid_13">&nbsp;</div>
			  </div>
			<s:set name="feeTypeId" value="%{feeTypeId}"></s:set>
			<s:set name="schoolTermsId" value="%{hostelTermId}"></s:set>
			</s:iterator>
  	   </div>
	</s:form>
	</div>
</s:if>
<s:else>
 	Fees not configured for this student class.
 </s:else>
 </div>
<script language="JavaScript" type="text/javascript">
function onChangeTotal(serviceId) {
	var totalAmount = $('#totalPayAmountDiv').val();
	if(parseFloat(totalAmount) <= parseFloat(serviceId)){
		$('#totalPayAmountAfterDiscount').val(totalAmount);
		alert("Total amount sholud be less than due amount.");
	}
}
$(document).ready(function() {
	commonLoadTab();
	var paymentType = $('span#payType').attr('class');
	if(isNonEmpty(paymentType)){
		//$('input[name=paymentType]').removeAttr('checked');
		if(paymentType == 'CH')
			$('#chForm').attr('checked','checked');
		else if(paymentType == 'C')
			$('#cashForm').attr('checked','checked');
		else
			$('#ddForm').attr('checked','checked');
		paymentTypeMethodChange(paymentType);
	}
});
 
$.subscribe('deleteFormValidation', function(event, data) {
	if ($('#deletePaymentFee').valid())
		return true;
	else
		return false;
});
 
$.subscribe('doPrintStudentInvoice', function(event, data) {
	 $('#printReport').submit();
	//document.studentInvoice.submit();
});
$("input:checkbox[name=chkBoxFeeSelectedIds]").change(function() {
	var levelId = document.getElementsByName("chkBoxFeeSelectedIds");
	var amount = 0;
	for (i = 0; i < levelId.length; i++) {
		if (levelId[i].checked) {
			var valueDiv = 'chkBoxFeeSelectedIds' + levelId[i].value;
			amount += parseFloat($('#' + valueDiv).html());
		 }
		else{
		    $("input#checkAllTurms").removeAttr("checked");
		}
	}
	document.getElementById('totalPayAmountDiv').value = amount;
	document.getElementById('totalPayAmountAfterDiscount').value = amount;
	/*$('#totalPayAmountDiv').html(amount);*/

});

function getTermsTotalAmount() {
	var levelId = document.getElementsByName("chkBoxFeeSelectedIds");
	var amount = 0;
		for (i = 0; i < levelId.length; i++) {
			if (levelId[i].checked) {
			var valueDiv = 'chkBoxFeeSelectedIds' + levelId[i].value;
			amount += parseFloat($('#' + valueDiv).html());
		}
	}
	document.getElementById('totalPayAmountDiv').value = amount;
	document.getElementById('totalPayAmountAfterDiscount').value = amount;
	/*$('#totalPayAmountDiv').html(amount);*/

}
function paymentTypeMethodChange(clickButton) {
	var inputBox = clickButton;
	if (inputBox == 'D') {
		$("#inputboxhideText").show();
		$("#checkinputboxhideText").hide();
	} else if(inputBox == 'CH'){
		$("#checkinputboxhideText").show();
		$("#inputboxhideText").hide();
		}
		else if(inputBox == 'C'){
			$("#inputboxhideText").hide();
			$("#checkinputboxhideText").hide();
		}
	}

function checkAll() {
     $("input[name='chkBoxFeeSelectedIds']").attr("checked","true");
	 getTermsTotalAmount();
}
/**/
function getStudentInvoicePrint(id) {
		$("#acedemicId").val(id);
		document.myform.submit();
}
</script>