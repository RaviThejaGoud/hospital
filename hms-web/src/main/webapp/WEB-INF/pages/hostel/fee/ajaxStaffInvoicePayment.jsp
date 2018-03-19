<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/messages.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/ems.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/map/jquery.listnav-2.1.js"></script>
<script Language="Javascript1.2" type="text/javascript">
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
<div id="searchStudentsForm112" class="commonFormTabs">
	<div class="grid_15">
		<div class="grid_11">
			<h1>
				<s:property value="staff.fullPersonName" /> (<s:property value="staff.account.userRoleDescription" /> )
			</h1>
		</div>
	</div>
	<div id="tabWrapper">
		<s:form action="ajaxPayStaffPaymentFee" id="addStaffPaymentFee" method="post" theme="css_xhtml" name="myform" namespace="/hostel">
			<input type="hidden" name="staffNumber" value='<s:property value="staff.id" />'/>
			<s:hidden id="termIdsIds" name="anyId"/>
			<s:set name="hostelTermsId" value=""></s:set>
			<s:set name="hostelFeeTypeId" value=""></s:set>
			<s:if test='%{anyTitle == "N"}'>
			<fieldset>
				<div class="grid_15">
					<div class="grid_15">
						<div class="grid_5">&nbsp;</div>
								<input type="radio" name="termType" id="checkAllTurms" value="allTermIds" onClick="checkAll()" class="radio">All Terms&nbsp;
								<input type="radio" name="termType" value="currentTurmsIds" onClick="checkCurrentForm()" class="radio">Current Term
					</div>
					<div class="grid_14">&nbsp;</div>
					<div class="grid_15">
						<div class="grid_5">
							<label class="labelRight">
								Due Fee:
							</label>
						</div>
						<div class="grid_8">
							<sj:textfield name="paymentAmount" id="totalPayAmountDiv"
								cssClass="numeric required textfield" readonly="true"></sj:textfield>
						</div>
						<div class="grid_2">
							&nbsp;
						</div>
					</div>
					<div class="grid_15 &nbsp;"></div>
					<div class="grid_15">
						<div class="grid_5">
							<label class="labelRight">
								Discount:
							</label>
						</div>
						<div class="grid_5">
							<sj:textfield name="discountAmount" id="termDescountAmount"
								cssClass="numeric textfield"
								onchange="javascript:onChangeDiscount(this.value);"></sj:textfield>
						</div>
						<div class="grid_5">
							&nbsp;
						</div>
					</div>
					<div class="grid_15 &nbsp;"></div>
					<div class="grid_15">
						<div class="grid_5">
							<label class="labelRight">
								Total Fee:
							</label>
						</div>
						<div class="grid_5">
							<sj:textfield name="totalAmount"
								id="totalPayAmountAfterDiscount" cssClass="numeric textfield"
								onchange="javascript:onChangeTotal(this.value);"></sj:textfield>
						</div>
						<div class="grid_5">
							&nbsp;
						</div>
					</div>
					<div class="grid_15">&nbsp;</div>
					<div class="grid_15">
						<div class="grid_5">
							<label class="labelRight">
								Payment Type:
							</label>
						</div>
							<input type="radio" value="C" name="payType" onclick="javascript:paymentTypeMethodChange(this.value)" checked> Cash&nbsp;&nbsp;
							<input type="radio" value="D" name="payType" id="ddForm" onclick="javascript:paymentTypeMethodChange(this.value)"> DD&nbsp;&nbsp;
							<input type="radio" value="CH" name="payType" id="chForm" onclick="javascript:paymentTypeMethodChange(this.value)"> Cheque
					</div>
					<div class="grid_15">&nbsp;</div>
					<div class="grid_15" style="display: none;" id="inputboxhideText">
						<div class="grid_5">
							<label class='labelRight'>
								Enter DD Number:
							</label>
						</div>
						<div class="grid_8">
							<sj:textfield name="feeDDNumber" id="ddNumberFee"
								value="DD Number"  
								cssClass="numeric textfield defaultValue" maxlength="12"></sj:textfield>
						</div>
					</div>
					<div class="grid_15" style="display: none;" id="checkinputboxhideText">
						<div class="grid_5">
							<label class='labelRight'>
								Enter Cheque Number:
							</label>
						</div>
						<div class="grid_8">
							<sj:textfield name="chequeNumber" id="chequeNumber"
								value="Number" 
								cssClass="numeric textfield defaultValue" maxlength="6"></sj:textfield>
						</div>
					</div>
					<div class="grid_15 &nbsp;"></div>
					<div class="grid_15" style="display: none;" id="bankinputboxhideText">
						<div class="grid_15 &nbsp;"></div>
						<div class="grid_5">
							<label class='labelRight'>
								Select Bank Name:
							</label>
						</div>
						<div class="grid_8">
							<s:select list="objectList" id="bankName" name="bankId"
								theme="simple" listValue="bankName" listKey="id" cssClass="required" required="true">
							</s:select>
						</div>
							<div class="grid_15 &nbsp;"></div>
						<div class="grid_5">
							<label class='labelRight'>
								Bank Branch Name:
							</label>
						</div>
						<div class="grid_8">
							<sj:textfield name="bankName" id="bankName"
								value="Enter Bank Branch Name"
								cssClass="textfield defaultValue" maxlength="25"></sj:textfield>
						</div>
					</div>
					<div class="grid_15">&nbsp;</div>
						<div class="grid_13">
							<s:url id="doCancelStaff" 
								action="ajaxSearchStaffByCriteria" namespace="/hostel"
								includeParams="all" escapeAmp="false">
								<s:param name="id" value="%{id}" />
							</s:url>
							<sj:a href="%{doCancelStaff}" cssClass="cancelButton"
								indicator="indicator" targets="searchStaffList">Cancel</sj:a>
							<sj:submit   cssClass="submit long" value="Pay & Print"
								indicator="indicator" targets="searchStudentsForm112"
								onClickTopics="staffPaymentFormValidation" onCompleteTopics="doPrintStaffInvoice" validate="true"/>
							<sj:submit   cssClass="submit small" value="Pay"
								indicator="indicator" targets="searchStudentsForm112"
								onClickTopics="staffPaymentFormValidation" validate="true"
								formIds="addStaffPaymentFee" />
						</div>
				</div>
			</fieldset>
			</s:if>
			<div id="tabNavigation" style="display: none;" >
				<ul>
				</ul>
			</div>
			<div id="steps">
				 <s:iterator value="classFeeList">
					<input type="hidden" id="divInnerHtml<s:property value="hostelStaffPaymentId"/>" value="<s:property value="paymentTypeStr" />"></input>
					<s:if test="%{hostelTermId != #hostelTermsId}">
						<s:if test='%{#hostelTermsId == ""}'>
							<fieldset class="step">
						</s:if>
						<s:else>
							</fieldset>
							<fieldset class="step">
						</s:else>
							<script type="text/javascript">
								addNewTab2Nav(
										'<s:property value="hostelTermName" />',
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
                                            onClick="javascript:printSraffHostelPreview('<s:property value='staffId'/>',
                                            '<s:property value='paymentDateStr'/>',
                                            '<s:property value='invoiceNumber'/>')";
                                            target="_new"><img style="" src="../images/index.jpg">
                                            Invoice: <s:property value="invoiceNumber" /> </a>
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
								<s:property value="hostelFeeType" />
							</div>
							<div class="grid_2">
								<del>
									<b style="float: left;">&#2352;</b>
								</del>
								<c:set value="${hostelFeeTotal}" var="priceAmt" />
								<fmt:formatNumber value="${priceAmt}" type="currency" pattern="##,##,##0.00" var="balance" />
								<c:out value="${balance}" />

							</div>
						</div>
						<div class="grid_2">
							<s:if test='%{paymentTotalAmount > 0 || paymentTotalAmount == null}'>
								<c:set value="${paymentTotalAmount}" var="priceAmt" />
								<fmt:formatNumber value="${priceAmt}" type="currency" pattern="##,##,###.00" var="balance" />
								<del>
									<b style="float: left;">&#2352;</b>
								</del>
								<c:out value="${balance}" />
							</s:if>
							<s:else>
								&nbsp;
							</s:else>
					</div>
					<div class="grid_3">
						<s:if test='%{totalDiscountAmount > 0 || totalDiscountAmount == null}'>
							<del>
								<b style="float: left;">&#2352;</b>
							</del>
							<c:set value="${totalDiscountAmount}" var="priceAmt" />
							<fmt:formatNumber value="${priceAmt}" type="currency" pattern="##,##,###.00" var="balance" />
							<c:out value="${balance}" />
						</s:if>
						<s:else>
							&nbsp;
						</s:else>
					</div>
					<div id="chkBoxFeeSelectedIds<s:property value="staffHostelFeeId"/>" style="display: none;">
					
						<s:if test='%{paymentTotalAmount == 0 || paymentTotalAmount == null}'>
								<s:property value="hostelFeeTotal"/>
							
						</s:if>
						<s:else>
							<s:property value="staffParticularTotal" />
						</s:else>
					</div>
					<div class="grid_1">
						<s:if test='%{paymentStatus == "P"}'>
							Paid 
						</s:if>
						<s:else>
							<input type="checkbox" name="chkBoxFeeSelectedIds" class="admisionPayStatus<s:property value='hostelTermId'/>"
								value="<s:property value="staffHostelFeeId"/>" />
						</s:else>
					</div>
					<div class="grid_2">
						<div
							id="divInnerHtmli<s:property value='hostelStaffPaymentId'/>"
							width="27%" class="head" style="text-align: left;">
							<script Language="Javascript1.2" type="text/javascript">
								var strInputCode=document.getElementById('divInnerHtml'+<s:property value='hostelStaffPaymentId'/>).value;
						 		strInputCode = strInputCode.replace(/&(lt|gt);/g, function (strMatch, p1){
						 		 	return (p1 == "lt")? "<" : ">";
						 		});
						 		strInputCode=strInputCode.wordWrap(6, "\n", true);
						 		document.getElementById("divInnerHtmli"+<s:property value='hostelStaffPaymentId'/>).innerHTML = strInputCode;
						 	</script>
						</div>
					</div>
					<div class="grid_2">
						<s:if test='%{paymentDateStr == "" || paymentDateStr == null}'>
							&nbsp;
						</s:if>
						<s:else>
							<s:property value="paymentDateStr" />
						</s:else>
					</div>
				</div>
					<s:set name="hostelFeeTypeId" value="%{hostelFeeTypeId}"></s:set>
					<s:set name="hostelTermsId" value="%{hostelTermId}"></s:set>
			 </s:iterator>
  	   </div>
	</s:form>
	</div>
	</s:if>
<s:else>
 	Fees or hostel category is not configured for this student.
 </s:else>
 </div>

 
<script language="JavaScript" type="text/javascript">
function onChangeDiscount(serviceId) {
	var totalAmount = $('#totalPayAmountDiv').val();
	if(serviceId!=0 || serviceId!=''){
	  $('#totalPayAmountAfterDiscount').attr('readonly','true');
	}
	else{
	  $('#totalPayAmountAfterDiscount').removeAttr('readonly','false');
	}
	if(parseFloat(totalAmount) <= parseFloat(serviceId)){
		$('#termDescountAmount').val('');
		alert("Discount amount sholud be less than total amount.");
	}else
	$('#totalPayAmountAfterDiscount').val(totalAmount - serviceId);
}
function onChangeTotal(serviceId) {
	var totalAmount = $('#totalPayAmountDiv').val();
	if(parseFloat(totalAmount) <= parseFloat(serviceId)){
		$('#totalPayAmountAfterDiscount').val(totalAmount);
		alert("Total amount sholud be less than due amount.");
	}
}
$(document).ready(function() {
	commonLoadTab();
	$.subscribe('staffPaymentFormValidation', function(event, data) {
		if ($('#addStaffPaymentFee').valid()) {
			var ddNum = $("#ddNumberFee").val();
			var chequeNum = $("#chequeNumber").val();
			var isDDSelected = $('#ddForm').attr('checked');
			var isChequeNumSelected = $('#chForm').attr('checked');
			var termIds=$("input[name=chkBoxFeeSelectedIds]:checked");
			var selectedClassIds='';
	        if(termIds.length > 0 ){
				 selectedClassIds = '(';
				 for(var i=0; i< termIds.length;i++ )
	        	   {
						selectedClassIds += termIds[i].value + ', ';
			        }
			        selectedClassIds += '0)';
			}else{
					alert("Please select at least one particular.");
					return false;	
			}
			$("#termIdsIds").val(selectedClassIds);
			
			if (isDDSelected) {
       			if (isNonEmpty(ddNum) && ddNum != "DD Number"){
               		if (isNonEmpty(bankName) && bankName != "0"){
                    	if (isNonEmpty(branchName) && branchName != "Enter Bank Branch Name"){
                    		return true;
                    	}
	               		else{
	                  		alert("Enter bank branch name.");
	                        return false;
	               		}
       				}else {
		          		alert("Please select bank Name.");
		         		return false;
             		}
             	}
                else {
                   alert("Please enter dd number.");
                   return false;
                }
            }
			else if(isChequeNumSelected){
				if (chequeNum != null && chequeNum != "" && chequeNum != "Number"){
					if (isNonEmpty(bankName) && bankName != "0"){
                    	if (isNonEmpty(branchName) && branchName != "Enter Bank Branch Name"){
                    		return true;
                    	}
	               		else{
	                  		alert("Please enter bank branch name");
	                        return false;
	               		}
       				}else {
		          		alert("Please select bank Name.");
		         		return false;
             		}
             	}else {
					alert("Enter Cheque Number");
					return false;
				}
			}else if($('#totalPayAmountAfterDiscount').val()==0){
					 alert("Total fee sholud be some amount not 0 value.");
					return false;
			}
			return true;
		} else
			return false;
	});
	$('.numeric').numeric();
	$('.alphabet').alpha();
	$('.alphanumeric').alphanumeric();
});
$.subscribe('doPrintStaffInvoice', function(event, data) {
	 $('#staffPrintReport').submit();
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
}
function paymentTypeMethodChange(clickButton) {
	var inputBox = clickButton;
	if (inputBox == 'D') {
		$("#inputboxhideText").show();
		$("#bankinputboxhideText").show();
		$("#checkinputboxhideText").hide();
	} else if(inputBox == 'CH'){
		$("#checkinputboxhideText").show();
		$("#bankinputboxhideText").show();
		$("#inputboxhideText").hide();
		}
		else if(inputBox == 'C'){
			$("#inputboxhideText").hide();
			$("#checkinputboxhideText").hide();
			$("#bankinputboxhideText").hide();
		}
	}

function checkAll() {
     $("input[name='chkBoxFeeSelectedIds']").attr("checked","true");
	 getTermsTotalAmount();
}
function checkCurrentForm(id) {
		var curmonth =(new Date()).getMonth();
		$("input[name='chkBoxFeeSelectedIds']").removeAttr("checked");
		if ($('#tabNavigation ul li a  div').hasClass("ribbon").toString()) {
				var feeTab=$('#tabNavigation ul li a div.ribbon').attr('id');
				$('div#feeTab'+ feeTab.substring(6)).click();
				if(isNonEmpty(feeTab))
					$('.admisionPayStatus'+(feeTab.substring(6))).attr("checked","checked");
			}
		getTermsTotalAmount();
}
function getStaffInvoicePrint(id) {
		$("#acedemicId").val(id);
		document.myform.submit();
}
</script>