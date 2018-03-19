	<%@ include file="/common/taglibs.jsp"%>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/scripts/default/ems.js"></script>
	 <s:if test="%{feeStructure!= null || studentPayment!= null || student!= null}">
		<s:if test='%{studentPayment.paymentType == "F"}'>
		<s:form action="ajaxPayStudentPaymentFee" id="addStudentFee"
				method="post" theme="css_xhtml" name="myform">
				<s:hidden name="studentNumber" />
				<s:hidden name="selectedId" value="%{selectedId}" />
				<s:hidden name="firstName" value="%{firstName}" />
				<s:hidden name="lastName" value="%{lastName}" />
				<s:hidden name="section" value="%{section}" />
			<table class="striped" width="100%" style="margin-bottom: 0;"
				cellpadding="1" cellspacing="1" id="results" >
				<thead>
					<tr class='loaded'  style="background-color: #DDDDDD"><!--
						<td colspan="5">
							<div class="grid_4 type-text" style="font-weight: bold;">
								Payment Selected :
							</div>
							<div class="grid_6">
									Full Payment
							</div>

						</td>
					--></tr>
					<tr>
					 <s:if test="%{schoolFeeList!= null || schoolFeeList!= null}">
				     <s:iterator value="schoolFeeList">
				     <td width="3%">
				     <s:property value="createdDateStr"/>
				     <a href="<c:url value='/admin/ajaxStudentPaymentPdfReport.do?pdfId=pdf'/>&spId=<s:property value="studentPayment.id"/>&createdDate=<s:property value="createdDateStr"/>" target="_new"><img style="" 
							src="../images/index.jpg"></a>
				    </td>
				    </s:iterator>
			        </s:if> 
					</tr>
					<tr>
						<th width="25%" class="head">
							Fee Type
						</th>
						<th width="25%" class="head">
							Amount(<del><b style="font-size:10px;">&#2352;</b></del>)
						</th>
						<th width="20%" class="head">
							Last Payment Date
						</th>
						<th width="" class="head">
							Payment Date
						</th>
						<th width="" class="head">
							Status
						</th>
						<th width="" class="head">
							paymentType
						</th>
					</tr>
				</thead>
				<tr class='loaded'  style="background-color: #DDDDDD">
					<td width="25%">
						Admission Fee
					</td>
					<td width="25%">
						<div id="chkBoxSelectedIdsA"><s:property value="feeStructure.admissionFee" /></div>
					</td>
					<td width="25%">
						<s:property value="feeStructure.admissionDateStr" />
					</td>
					<td width="25%">
						<s:property value="studentPayment.admisionPayDateStr" />
					</td>
					<td>
					 <s:if test='%{studentPayment.admisionPayStatus== "P"}'>
							Paid 
						</s:if>
						<s:else>
							<input type="checkbox" name="chkBoxSelectedIds" id="admisionPayStatus" value="A"/>
						</s:else>
					</td>
					<td>
					 <s:if test='%{studentPayment.admisionPayType== "D"}'>
					 DD 
					 </s:if>
					<s:elseif test='%{studentPayment.admisionPayType== "C"}'>
					 Cash 
					</s:elseif>
					</td>
				</tr>
				<tr class='loaded'  style="background-color: #DDDDDD">
					<td width="">
						Total Tuition Fee
					</td>
					<td width="">
						<div id="chkBoxSelectedIdsF"><s:property value="feeStructure.totalTermFee" /></div>
					</td>
					<td>
						<s:property value="feeStructure.totalTermStr" />
					</td>
					<td width="">
						<s:property value="studentPayment.totalTermPayDateStr" />
					</td>
					<td>
					 <s:if test='%{studentPayment.term1PayStatus == "P" || studentPayment.term2PayStatus == "P" || studentPayment.term3PayStatus == "P"}'>
							Paid 
						</s:if>
						<s:else>
							<input type="checkbox" name="chkBoxSelectedIds"
								id="totalPayStatus" value="F"/>
						</s:else>
					</td>
					<td>
					  <s:if test='%{studentPayment.totalTermPayType== "D"}'>
					 DD 
					 </s:if>
					<s:elseif test='%{studentPayment.totalTermPayType== "C"}'>
					 Cash 
					</s:elseif>
					</td>
				</tr>
				<tr class='loaded'  style="background-color: #DDDDDD">
					<td width="">
						Examination Fee
					</td>
					<td width="">
						<div id="chkBoxSelectedIdsE"><s:property value="feeStructure.examinationFee" /></div>
					</td>
					<td>
						<s:property value="feeStructure.examDateStr" />
					</td>
					<td>
						<s:property value="studentPayment.examPayDateStr" />
					</td>
					<td>
					<s:if test='%{studentPayment.examPayStatus== "P"}'>
							Paid 
						</s:if>
						<s:else>
							<input type="checkbox" name="chkBoxSelectedIds" id="examPayStatus"	value="E"/>
						</s:else>
					</td>
					<td>
					  <s:if test='%{studentPayment.examPayType== "D"}'>
					 DD 
					 </s:if>
					<s:elseif test='%{studentPayment.examPayType== "C"}'>
					 Cash 
					</s:elseif>
					</td>
				</tr>
				<s:if test='%{student.transportMode =="ST"}'> 
				 <s:if test='%{feeStructure.transportFee != null}'>
				<tr class='loaded'  style="background-color: #DDDDDD">
					<td width="">
						Transport Fee
					</td>
					<td width="">
						<div id="chkBoxSelectedIdsT4"><s:property value="feeStructure.transportFee" /></div>
					</td>
					<td>
						<s:property value="feeStructure.transportFeeDateStr" />
					</td>
					<td>
						<s:property value="studentPayment.transportFeeDateStr" />
					</td>
					<td>
					<s:if test='%{studentPayment.transportPayStatus== "P"}'>
							Paid  
						</s:if>
						<s:else>
							<input type="checkbox" name="chkBoxSelectedIds" id="examPayStatus"	value="T4"/>
						</s:else>
					</td>
				  <td>
				   <s:if test='%{studentPayment.transportPayType== "D"}'>
					 DD 
					 </s:if>
					<s:elseif test='%{studentPayment.transportPayType== "C"}'>
					 Cash 
					</s:elseif>
					</td>	
				</tr>
				</s:if>
				</s:if>
			</table>
			
			<s:if test='%{studentPayment.admisionPayStatus != "P" || studentPayment.examPayStatus != "P" || studentPayment.term1PayStatus != "P" || studentPayment.term2PayStatus != "P" || studentPayment.term3PayStatus != "P" ||studentPayment.transportPayStatus!= "P"}'>
			<div class="grid_2">&nbsp;</div>
			<div class="grid_11" style="margin: 0px;padding: 0px;">
					<div class="grid_4 type-text" >
						Total payment :
					</div>
					<div class="grid_3">
						<div id="totalPayAmountDiv"></div>
					</div>
				</div>
				<div class="grid_2">&nbsp;</div>
			<div class="grid_11" style="margin: 0px;padding: 0px;">
					<div class="grid_4 type-text" >
						Payment Type :
					</div>
					<div class="grid_6">
					<div>
						<s:radio id="paymentMode" list="#{'C':'Cash','D':'DD'}"
							name="payType" required="true" cssClass="radio" onclick="javascript:paymentTypeMethodChange(this.value)"/>
					</div>
					<div >
					<div id="inputboxhideText" style="display: none;">
					<sj:textfield name="feeDDNumber" id="ddNumberFee"  value="Enter DD Value" 
					cssClass="textfield" maxlength="40" required="true"></sj:textfield>
					</div>
					</div>
					</div>
				</div>
				<div class="grid_8 type-text" style="float: right;">
					<sj:submit   cssClass="submit small" value="Submit"
						indicator="indicator" button="true" targets="studentPayPayment"
						onClickTopics="studentFeeFormValidation"
						formIds="addStudentFee" />
						
					<s:url id="doCancelStudent" action="ajaxSearchStudentPaymentListByRollNumber"
						includeParams="all">
						<s:param name="id" value="%{id}" />
						</s:url>
					<sj:a href="%{doCancelStudent}" cssClass="cancelButton"
						indicator="indicator" targets="studentPayPayment" button="false">Cancel</sj:a>
				</div>
				</s:if>
			</s:form>
		</s:if>
		<s:elseif test='%{studentPayment.paymentType == "T"}'>
			  <s:form action="ajaxPayStudentPaymentFee" id="addStudentTermFee"
				method="post" theme="css_xhtml" name="myform">
				<s:hidden name="studentNumber" />
				<s:hidden name="student.paymentType" value="T"></s:hidden>
				<s:hidden name="selectedId" value="%{selectedId}" />
				<s:hidden name="firstName" value="%{firstName}" />
				<s:hidden name="lastName" value="%{lastName}" />
				<s:hidden name="className" value="%{className}" />
				<s:hidden name="section" value="%{section}" />
			   <table class="striped" width="100%" style="margin-bottom: 0;"
				cellpadding="1" cellspacing="1" id="results">
				<thead>
					<!--<tr class='loaded'>
						<td colspan="5">
							<div class="grid_4 type-text" style="font-weight: bold;">
								Payment Selected :
							</div>
							<div class="grid_6">
									Term Payment
							</div>

						</td>
					</tr>
					-->
					<tr>
					 <s:if test="%{schoolFeeList!= null || schoolFeeList!= null}">
				     <s:iterator value="schoolFeeList">
				     <td width="3%">
				     <s:property value="createdDateStr"/>
				     <a href="<c:url value='/admin/ajaxStudentPaymentPdfReport.do?pdfId=pdf'/>&spId=<s:property value="studentPayment.id"/>&createdDate=<s:property value="createdDateStr"/>" target="_new"><img style="" 
							src="../images/index.jpg"></a>
				    </td>
				    </s:iterator>
			        </s:if> 
					</tr>
					<tr>
						<th width="25%" class="head">
							Fee Type
						</th>
						<th width="25%" class="head">
							Amount(<del><b style="font-size:14px;">&#2352;</b></del>)
						</th>
						<th width="20%" class="head">
							Last Payment Date
						</th>
						<th width="" class="head">
							Payment Date
						</th>
						<th width="" class="head">
							Status
						</th>
						<th width="" class="head">
							paymentType
						</th>
					</tr>
				</thead>
				<tr class='loaded' style="background-color: #DDDDDD">
					<td width="25%">
						Admission Fee
					</td>
					<td width="25%">
						<div id="chkBoxSelectedIdsA"><s:property value="feeStructure.admissionFee" /></div>
					</td>
					<td width="25%">
						<s:property value="feeStructure.admissionDateStr" />
					</td>
					<td width="25%">
						<s:property value="studentPayment.admisionPayDateStr" />
					</td>
					<td>
						<s:if test='%{studentPayment.admisionPayStatus== "P"}'>
							Paid 
						</s:if>
						<s:else>
							<input type="checkbox" name="chkBoxSelectedIds" value="A"/>
						</s:else>
					</td>
					<td>
					 <s:if test='%{studentPayment.admisionPayType== "D"}'>
					 DD 
					 </s:if>
					<s:elseif test='%{studentPayment.admisionPayType== "C"}'>
					 Cash 
					</s:elseif>
					</td>
				</tr>
				<tr class='loaded'  style="background-color: #DDDDDD">
					<td width="">
						Term 1 Fee 
					</td>
					<td width="">
						<div id="chkBoxSelectedIdsT1"><s:property value="feeStructure.term1Amount" /></div>
					</td>
					<td>
						<s:property value="feeStructure.term1DateStr" />
					</td>
					<td width="">
						<s:property value="studentPayment.term1PayDateStr" />
					</td>
					<td>
					 <s:if test='%{studentPayment.term1PayStatus== "P"}'>
							Paid 
						</s:if>
						<s:else>
							<input type="checkbox" name="chkBoxSelectedIds" value="T1"/>
						</s:else>
					</td>
					<td>
					 <s:if test='%{studentPayment.term1PayType== "D"}'>
					 DD 
					 </s:if>
					<s:elseif test='%{studentPayment.term1PayType== "C"}'>
					 Cash 
					</s:elseif>
					</td>
				</tr>
				<tr class='loaded'  style="background-color: #DDDDDD">
					<td width="">
						Term 2 Fee 
					</td>
					<td width="">
						<div id="chkBoxSelectedIdsT2"><s:property value="feeStructure.term2Amount" /></div>
					</td>
					<td>
						<s:property value="feeStructure.term2DateStr" />
					</td>
					<td width="">
						<s:property value="studentPayment.term2PayDateStr" />
					</td>
					<td>
						<s:if test='%{studentPayment.term2PayStatus== "P"}'>
							Paid 
						</s:if>
						<s:else>
							<input type="checkbox" name="chkBoxSelectedIds" value="T2"/>
						</s:else>
					</td>
					<td>
					 <s:if test='%{studentPayment.term2PayType== "D"}'>
					 DD 
					 </s:if>
					<s:elseif test='%{studentPayment.term2PayType== "C"}'>
					 Cash 
					</s:elseif>
					</td>
				</tr>
				<tr class='loaded'  style="background-color: #DDDDDD">
					<td width="">
						Term 3 Fee 
					</td>
					<td width="">
						<div id="chkBoxSelectedIdsT3"><s:property value="feeStructure.term3Amount" /></div>
					</td>
					<td>
						<s:property value="feeStructure.term3DateStr" />
					</td>
					<td width="">
						<s:property value="studentPayment.term3PayDateStr" />
					</td>
					<td>
						<s:if test='%{studentPayment.term3PayStatus== "P"}'>
							Paid 
						</s:if>
						<s:else>
							<input type="checkbox" name="chkBoxSelectedIds" value="T3"/>
						</s:else>
					</td>
					<td>
					 <s:if test='%{studentPayment.term3PayType== "D"}'>
					 DD 
					 </s:if>
					<s:elseif test='%{studentPayment.term3PayType== "C"}'>
					 Cash 
					</s:elseif>
					</td>
				</tr>
				<tr class='loaded'  style="background-color: #DDDDDD">
					<td width="">
						Examination Fee
					</td>
					<td width="">
						<div id="chkBoxSelectedIdsE"><s:property value="feeStructure.examinationFee" /></div>
					</td>
					<td>
						<s:property value="feeStructure.examDateStr" />
					</td>
					<td>
						<s:property value="studentPayment.examPayDateStr" />
					</td>
					<td>
					 <s:if test='%{studentPayment.examPayStatus== "P"}'>
							Paid 
						</s:if>
						<s:else>
							<input type="checkbox" name="chkBoxSelectedIds" value="E"/>
						</s:else>
					</td>
					<td>
					 <s:if test='%{studentPayment.examPayType== "D"}'>
					 DD 
					 </s:if>
					<s:elseif test='%{studentPayment.examPayType== "C"}'>
					 Cash 
					</s:elseif>
					</td>
				</tr>
				<s:if test='%{student.transportMode =="ST"}'> 
				<s:if test='%{feeStructure.transportFee != null}'>
				<tr class='loaded'  style="background-color: #DDDDDD">
					<td width="">
						Transport Fee
					</td>
					<td width="">
						<div id="chkBoxSelectedIdsT4"><s:property value="feeStructure.transportFee" /></div>
					</td>
					<td>
						<s:property value="feeStructure.transportFeeDateStr" />
					</td>
					<td>
						<s:property value="studentPayment.transportFeeDateStr" />
					</td>
					<td>
					<s:if test='%{studentPayment.transportPayStatus== "P"}'>
							Paid 
						</s:if>
						<s:else>
							<input type="checkbox" name="chkBoxSelectedIds" id="examPayStatus"	value="T4"/>
						</s:else>
					</td>
					<td>
					 <s:if test='%{studentPayment.transportPayType== "D"}'>
					 DD  
					 </s:if>
					<s:elseif test='%{studentPayment.transportPayType== "C"}'>
					 Cash 
					</s:elseif>
					</td>
				</tr>
				</s:if>
				</s:if>
			</table>
			<s:if test='%{studentPayment.admisionPayStatus !="P" || studentPayment.examPayStatus != "P" || studentPayment.term1PayStatus != "P" || studentPayment.term2PayStatus != "P" || studentPayment.term3PayStatus != "P" ||studentPayment.transportPayStatus != "P"}'>
			<div class="grid_2">&nbsp;</div>
			<div class="grid_11" style="margin: 0px;padding: 0px;">
					<div class="grid_4 type-text" >
						Total payment (<del><b style="font-size:14px;">&#2352;</b></del>):
					</div>
					<div class="grid_3">
						<div id="totalPayAmountDiv"></div>
					</div>
				</div>
				<div class="grid_2">&nbsp;</div>
			<div class="grid_11" style="margin: 0px;padding: 0px;">
					<div class="grid_4 type-text" >
						Payment Type :
					</div>
					<div class="grid_6">
					<div class="grid_3">
						<s:radio id="paymentMode" list="#{'C':'Cash','D':'DD'}"  name="payType"   required="true" cssClass="radio" onclick="javascript:paymentTypeMethodChange(this.value)" cssStyle="float:left"/>
					</div>
					<div class="grid_2">
					<div id="inputboxhideText" style="display: none;">
				    <sj:textfield name="feeDDNumber" id="ddNumberFee"  value="Enter DD Value"
					cssClass="textfield" maxlength="40" required="true"></sj:textfield>
					</div>
					</div>
				</div>
					</div>
				<div class="grid_8 type-text" style="float: right;">
					<sj:submit   cssClass="submit small" value="Submit"
						indicator="indicator" button="true" targets="studentPayPayment"
						onClickTopics="studentTermFeeFormValidation"
						formIds="addStudentTermFee" />
						
					<s:url id="doCancelStudent" action="ajaxSearchStudentPaymentListByRollNumber"
						includeParams="all">
						<s:param name="id" value="%{id}" />
						</s:url>
					<sj:a href="%{doCancelStudent}" cssClass="cancelButton"
						indicator="indicator" targets="studentPayPayment" button="false">Cancel</sj:a>
				</div>
				</s:if>
			</s:form>
		</s:elseif>
	  </s:if>
	 <s:else>
		Pay student fee
	</s:else>
	</td>
<script language="JavaScript" type="text/javascript">
$(document).ready(function() {
		$.subscribe('studentFeeFormValidation', function(event, data) {
		var frequencyStatus= $("input:checkbox[name=chkBoxSelectedIds]:checked").val()
		var ddNumberFee = $('#ddNumberFee').val();
			var levelId = document.getElementsByName("chkBoxSelectedIds");
			var fieldErrorString ='';
			if(frequencyStatus!='')
			{
			var isSelected = false;
			for (i = 0; i < levelId.length; i++) {
				if (levelId[i].checked == true) {
				    isSelected = true;
				}
			}
			if ((!isSelected)||(ddNumberFee == null || ddNumberFee == ''|| ddNumberFee == 'Enter DD Number')) {
			     if((!isSelected)&&(ddNumberFee == null || ddNumberFee == ''|| ddNumberFee == 'Enter DD Number')){
			     		alert(fieldErrorString+"Please Select at least One Payment");
						alert("Please enter DD Number");
						return false;
				 }
				 if(!isSelected){
				 alert(fieldErrorString+"Please Select at least One Payment");
				 return false;
				 }	
				 if(ddNumberFee == null || ddNumberFee == ''|| ddNumberFee == 'Enter DD Number'){
				 alert("Please enter DD Number");
				   return false;
				 }	
			}
				else{
				return true;
				}
			}
			if ($('#addStudentFee').valid())
				return true;
			else
				return false;
		});
		$("input#pincode").mask("99999");

	});
	
	$(document).ready(function() {
		    $.subscribe('studentTermFeeFormValidation', function(event, data) {
			var frequencyStatus= $("input:checkbox[name=chkBoxSelectedIds]:checked").val()
			
			var studentId ='<s:property value="studentPayment.transportPayType"/>';
			
		   
			var ddNumberFee = $('#ddNumberFee').val();
			var levelId = document.getElementsByName("chkBoxSelectedIds");
			var fieldErrorString ='';
			if(frequencyStatus!='')
			{
			var isSelected = false;
			
			for (i = 0; i < levelId.length; i++) {
				if (levelId[i].checked == true) {
				    isSelected = true;
				}
			}
			if (!isSelected) {
			    alert(fieldErrorString+"Please Select at least One Payment");
				return false;
				}
				if ((!isSelected)||(ddNumberFee == null || ddNumberFee == ''|| ddNumberFee == 'Enter DD Number')) {
			     if((!isSelected)&&(ddNumberFee == null || ddNumberFee == ''|| ddNumberFee == 'Enter DD Number')){
			     		alert(fieldErrorString+"Please Select at least One Payment");
						alert("Please enter DD Number");
						return false;
				 }
				 if(!isSelected){
				 alert(fieldErrorString+"Please Select at least One Payment");
				 return false;
				 }	
				 if(ddNumberFee == null || ddNumberFee == ''|| ddNumberFee == 'Enter DD Number'){
				 alert("Please enter DD Number");
				   return false;
				 }	
			}
				else{
				return true;
				}
			}
			 if ($('#addStudentTermFee').valid())
         	 return true;
          else
          	 return false;
		});
		$("input#pincode").mask("99999");

	});
	function paymentTypeMethodChange(clickButton) {
		var inputBox=clickButton;
		document.getElementById("ddNumberFee").value='Enter DD Number';
		if (inputBox=='D') {
		$("#inputboxhideText").show();
		}
		else {
		if(inputBox=='C') {
		$("#inputboxhideText").hide();
		}
		}
	}
	
   $("input:checkbox[name=chkBoxSelectedIds]").change(function() {
		var levelId = document.getElementsByName("chkBoxSelectedIds");
		var amount = 0;
		for (i = 0; i < levelId.length; i++) {
			if (levelId[i].checked) {
				var valueDiv = 'chkBoxSelectedIds' + levelId[i].value;
				amount += parseFloat($('#' + valueDiv).html());
			}
		}
		$('#totalPayAmountDiv').html(amount);

	});
	function clearTextField(field){
    	if (field.defaultValue == field.value) field.value = '';
    	else if (field.value == '') field.value = field.defaultValue;

	}
</script>