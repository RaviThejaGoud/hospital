<td colspan="6">
	<%@ include file="/common/taglibs.jsp"%>
	<s:if
		test="%{classFeeList != null && !classFeeList.isEmpty() && schoolTermsList != null && !schoolTermsList.isEmpty()}">
		<s:form action="ajaxPayStudentPaymentFee" id="addStudentFee"
			method="post" theme="css_xhtml" name="myform">
			<s:hidden name="studentNumber" />
			<s:hidden name="selectedId" value="%{selectedId}" />
			<s:hidden name="firstName" value="%{firstName}" />
			<s:hidden name="lastName" value="%{lastName}" />
			<s:hidden name="className" value="%{className}" />
			<s:hidden name="section" value="%{section}" />
			<table class="striped" width="100%" style="margin-bottom: 0;" cellpadding="1" cellspacing="1" id="results">
				<thead>
					<tr>
						<s:if test="%{schoolFeeList!= null || schoolFeeList!= null}">
							<s:iterator value="schoolFeeList">
								<td width="3%" style="border: none;">
									<s:property value="createdDateStr" />
									<a
										href="<c:url value='/admin/ajaxStudentPaymentPdfReport.do?pdfId=pdf'/>&spId=<s:property value="studentPayment.studentId"/>&createdDate=<s:property value="createdDateStr"/>"
										target="_new"><img style="" src="../images/index.jpg">
									</a>
								</td>
							</s:iterator>
						</s:if>
					</tr>
					<tr style="background-color: #008E8E">
						<th width="25%" class="head">
							Fee Type
						</th>
						<th width="25%" class="head">
							Start Month
						</th>
						<th width="25%" class="head">
							End Month
						</th>
						<th width="25%" class="head">
							Due Date
						</th>
						
					</tr>
				</thead>
				<s:iterator value="schoolTermsList">
					<s:set name="schoolTermsId" value="%{id}"></s:set>
					<tr class='loaded' style="background-color: #DDDDDD">

						<td id="viewSyllabus" class="viewSyllabus<s:property value='id'/>"
							width="25%">
							<b> <a href="#"><s:property value="termName" />
							</a> </b>
						</td>
						<td width="25%">
							<s:property value="fromMonthName" />
						</td>
						<td width="25%">
							<s:property value="toMonthName" />
						</td>
						<td  width="25%">
							<s:property value="dueDateStr" />
						</td>
					</tr>
					<tr style="background-color: #ccc; width: 100%; display: none;"
						id="viewSyllabus<s:property value='id'/>" class="loading">
						<td colspan="6">
							<table width="100%">
								<thead style="background-color: #008E8E">
									<th width="25%" class="head">
										Fee Type
									</th>
									<th width="25%" class="head">
										Amount(
										<del>
											<b style="font-size: 10px;">&#2352;</b>
										</del>
										)
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
								</thead>
								<s:iterator value="classFeeList">
								
									<s:if test="%{schoolTermId == #schoolTermsId}">
											<tr>
												<td width="20%">
													<s:property value="feeType" />
												</td>
												<td width="20%">
													
													<div id="chkBoxFeeSelectedIds<s:property value="id"/>">
														<s:property value="feeAmount" />
													</div>
												</td>
												<td width="20%">
													<s:property value="feeDueDateStr" />
												</td>
												<td width="20%">
													<s:property value="paymentDateStr" />
												</td>
												<td width="20%">
													<s:if test='%{paymentStatus == "P"}'>
														Paid 
													</s:if>
													<s:else>
														<input type="checkbox" name="chkBoxSelectedIds" 
															id="admisionPayStatus" value="<s:property value="feeTypeId"/>" />
													</s:else>
													
													<!--<input type="checkbox" name="chkBoxFeeSelectedIds"
														id="admisionPayStatus"
														value="<s:property value="id"/>" />
	
												--></td>
												<td width="20%">
													<s:property value="paymentDateStr" />
												</td>
											</tr>
									</s:if>
								</s:iterator>
							</table>
						</td>
					</tr>
				</s:iterator>
			</table>
			<div class="grid_6" style="float: inherit;">
				<div class="grid_2">
					<label>
						Total Amount
					</label>
				</div>
				<div class="grid_4">
					<sj:textfield name="termTotalAmount" id="totalPayAmountDiv"
						cssClass="numeric required textfield small"
						cssStyle="width:200px;" maxlength="8"></sj:textfield>
				</div>
			</div>
			<!--<div class="grid_11" style="margin: 0px; padding: 0px;">
				<div class="grid_4 type-text" >
					<label>Total Payment :</label> 
				</div>
				<div class="grid_3">
					<div id="totalPayAmountDiv"></div>
				</div>
			</div>
			-->
			<div class="grid_2">
				&nbsp;
			</div>
			<div class="grid_11" style="">
				<div class="grid_4 type-text" >
					<label style="margin-top: 0px;">
						Payment Type :
					</label>
				</div>
				<div class="grid_6">
					<input type="radio" value="C" name="payType" onclick="javascript:paymentTypeMethodChange(this.value)"
						style="vertical-align: text-bottom;" checked>
					Cash
					<input type="radio" value="D" name="payType" id="ddForm" style="vertical-align: text-bottom;"
						onclick="javascript:paymentTypeMethodChange(this.value)">
					DD
				</div>
			</div>
			<div class="grid_2">
				&nbsp;
			</div>
			<div class="grid_11"
				style="margin: 0px; padding: 0px; display: none;"
				id="inputboxhideText">
				<div class="grid_4">
					<label>
						Enter DD Number :
					</label>
				</div>
				<div class="grid_6">
					<sj:textfield name="feeDDNumber" id="ddNumberFee"
						 value="Enter DD Number"
						 cssClass="numeric textfield"
						maxlength="15" required="true"></sj:textfield>
				</div>
			</div>
			<div class="grid_4 type-text" style="float: right;">
				<sj:submit   cssClass="submit small" value="Submit"
					indicator="indicator" button="true" targets="studentPayPayment"
					onClickTopics="studentFeeFormValidation" formIds="addStudentFee" />

				<s:url id="doCancelClassFee" action="ajaxAdminGetSchoolFee" includeParams="all">
				</s:url>
				<sj:a href="%{doCancelClassFee}" cssClass="cancelButton"
					indicator="indicator" targets="schoolContent" button="false">Cancel</sj:a>
			</div>
		</s:form>
	</s:if>
	<script language="JavaScript" type="text/javascript">
$(document).ready(function() {
	$.subscribe('studentFeeFormValidation', function(event, data) {
		if ($('#addStudentFee').valid()) {
			var ddNum = $("#ddNumberFee").val();
			var isDDSelected = $('#ddForm').attr('checked');
			if (isDDSelected) {
				if (ddNum != null && ddNum != "" && ddNum != "Enter DD Number")
					return true;
				else {
					alert("Enter DD Number");
					return false;
				}
			}
			return true;
		} else
			return false;
	});
	$('.numeric').numeric();
	$('.alphabet').alpha();
	$('.alphanumeric').alphanumeric();
});
$.subscribe('doInitEditInstallment', function(event, data) {
	if ($('#' + data.id).is(":hidden")) {
		$('#' + data.id).show();
	} else {
		$('#' + data.id).hide();
	}
});
function onClickToGetFeePayment(radio) {
	var paymentType = radio.value;
	var id = '<s:property value="selectedId"/>';
	var studentId = '<s:property value="studentNumber"/>';
	var firstName = '<s:property value="firstName"/>';
	var lastName = '<s:property value="lastName"/>';
	var className = '<s:property value="className"/>';
	var section = '<s:property value="section"/>';

	var pars = "studentId=" + studentId + "&paymentType=" + paymentType
			+ "&id=" + id + "&firstName=" + firstName + "&lastName=" + lastName
			+ "&className=" + className + "&section=" + section;
	var url = jQuery.url.getChatURL("/admin/ajaxEditStudentClassFee.do");
	$.ajax( {
		url : url,
		data : pars,
		cache : false,
		success : function(html) {
			$("#paymentTypehideText").html(html);
			$("#paymentTypehideText").show(html);
		}
	});
}
$("input:checkbox[name=chkBoxFeeSelectedIds]").change(function() {
	var levelId = document.getElementsByName("chkBoxFeeSelectedIds");
	var amount = 0;
	for (i = 0; i < levelId.length; i++) {
		if (levelId[i].checked) {
			var valueDiv = 'chkBoxFeeSelectedIds' + levelId[i].value;
			amount += parseFloat($('#' + valueDiv).html());
		}
	}
	document.getElementById('totalPayAmountDiv').value = amount;
	/*$('#totalPayAmountDiv').html(amount);*/

});
function paymentTypeMethodChange(clickButton) {
	var inputBox = clickButton;
	if (inputBox == 'D') {
		$("#inputboxhideText").show();
	} else {
		if (inputBox == 'C') {
			$("#inputboxhideText").hide();
		}
	}
}
$(document).ready(function() {
	$('tr td#viewSyllabus').click(function() {
		$("table.striped tr.loading").each(function(i, row) {
			$(row).hide();
		});
		var data = $(this).attr('class');
		if ($('#' + data).is(":hidden")) {
			$('#' + data).show();
		} else {
			$('#' + data).hide();
		}
	});
});
</script>