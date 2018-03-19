<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Student Payments
				</div>
			</div>
			<div class="portlet-body tab-content">
				<div id="searchStudentsForm112">
					<%@include  file="/WEB-INF/pages/schoolfee/ajaxFeeSettingStudentInvoiceList.jsp" %>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function() {
		changePageTitle('Make Payment');
});
function onChangeDiscount(serviceId) {
	var totalAmount = $('#totalPayAmountDiv').val();
	if (serviceId != 0 || serviceId != '') {
		$('#totalPayAmountAfterDiscount').attr('readonly', 'true');
	} else {
		$('#totalPayAmountAfterDiscount').removeAttr('readonly', 'false');
	}
	if (parseFloat(totalAmount) <= parseFloat(serviceId)) {
		$('#termDescountAmount').val('');
		alert("Discount amount sholud be less than total amount.");
	} else
		$('#totalPayAmountAfterDiscount').val(totalAmount - serviceId);

}
function onChangeTotal(serviceId) {
	var totalAmount = $('#totalPayAmountDiv').val();
	if (parseFloat(totalAmount) <= parseFloat(serviceId)) {
		$('#totalPayAmountAfterDiscount').val(totalAmount);
		alert("Total amount sholud be less than due amount.");
	} else if (serviceId == 0) {
		alert("Total fee sholud be some amount not 0 value.");
		return false;
	}
}

$.subscribe('doPrintStudentInvoice', function(event, data) {
	//alert($("#spId").val());
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
		} else {
			$("input#checkAllTurms").removeAttr("checked");
		}
	}
	document.getElementById('totalPayAmountDiv').value = amount;
	document.getElementById('totalPayAmountAfterDiscount').value = amount;
	document.getElementById('termDescountAmount').value = 0.0;
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
	if (status == 'N') {
		amount = 0.0;
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
		$("#bankinputboxhideText").show();
	} else if (inputBox == 'CH') {
		$("#checkinputboxhideText").show();
		$("#inputboxhideText").hide();
		$("#bankinputboxhideText").show();
	} else if (inputBox == 'C') {
		$("#inputboxhideText").hide();
		$("#checkinputboxhideText").hide();
		$("#bankinputboxhideText").hide();
	}
}

function checkAll() {
	$("input[name='chkBoxFeeSelectedIds']").attr("checked", "true");
	getTermsTotalAmount('Y');
}
function checkCurrentForm(id) {
	var curmonth = (new Date()).getMonth();
	$("input[name='chkBoxFeeSelectedIds']").removeAttr("checked");
	if ($('#tabNavigation ul li a  div').hasClass("ribbon").toString()) {
		var feeTab = $('#tabNavigation ul li a div.ribbon').attr('id');
		$('div#feeTab' + feeTab.substring(6)).click();
		//var feeTab=$('#tabNavigation ul li a div').attr('id');

		if (isNonEmpty(feeTab)) {
			$('.admisionPayStatus' + (feeTab.substring(6))).attr("checked", "checked");
		}
		getTermsTotalAmount('Y');
	} else {
		getTermsTotalAmount('N');
	}
}

function getStudentInvoicePrint(id) {
	$("#acedemicId").val(id);
	document.myform.submit();
}
$("#termDescountAmount").change(function() {
	var total = $("#totalPayAmountDiv").val();
	var termDescountAmount = ($(this).val()).replace('.', '');
	if (Math.floor(termDescountAmount) != termDescountAmount) {
		alert("Please enter numbers.");
		$("#termDescountAmount").val('');
		$("#totalPayAmountAfterDiscount").val(total);
		return false;
	}
});
</script>