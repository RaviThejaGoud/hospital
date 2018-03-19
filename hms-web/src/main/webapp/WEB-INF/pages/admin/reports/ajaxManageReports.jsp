<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>
<div class="grid_4" style="margin-right: 40px;">
	<form name="myform"
		action="${pageContext.request.contextPath}/reports/ajaxPrintViewStudentInvoiceDefaulters.do?pdfId=pdf"
		target="_new">
		<s:select label="Select a Report Type " cssClass="required"
			required="true" headerKey="S" headerValue="- Select Report Type -"
			onchange="ajaxAllHostelFeeReprots(this.value);"
			list="#{'C':'Collection', 'F':'Fee Dues','HR':'Hostel Reports'}"
			name="yourOptionValue" id="yourOptionValue" theme="css_xhtml"
			value="2" />
	</form>
</div>
<div id="HostelFeeReports"></div>
<script type="text/javascript">
$(document).ready(function() {
	changePageTitle("Reports");
	$('.blockHeader h2').html('Manage Hostel Repots');
});
function getAjaxHostelWiseReport(yourOptionValue) {
	var pars = "yourOptionValue=" + yourOptionValue;
	var url = '';
}
function ajaxAllHostelFeeReprots(yourOptionValue) {
	$("#HostelFeeReports").removeClass("grid_14");
	$("#HostelFeeReports").addClass("grid_10");
	var pars = "yourOptionValue=" + yourOptionValue;
	if (yourOptionValue == "C") {
		var url = jQuery.url
				.getChatURL("/hostel/ajaxDoCollectionByFeeDetails.do");
		$.ajax( {
			url : url,
			cache : false,
			data : pars,
			success : function(html) {
				$("#HostelFeeReports").html(html);
			}
		});
	} else if (yourOptionValue == "A") {
		var url = jQuery.url
				.getChatURL("/hostel/ajaxDoAttendanceByAttendanceDetails.do");
		$.ajax( {
			url : url,
			cache : false,
			data : pars,
			success : function(html) {
				$("#HostelFeeReports").html(html);
			}
		});
	} else if (yourOptionValue == "F") {
		var url = jQuery.url.getChatURL("/hostel/ajaxDoFeeDefaulters.do");
		$.ajax( {
			url : url,
			cache : false,
			data : pars,
			success : function(html) {
				$("#HostelFeeReports").html(html);
			}
		});
	} else if (yourOptionValue == "HR") {
		$("#HostelFeeReports").hide();
		url = jQuery.url
				.getChatURL("/reports/ajaxSelectGenerateHostelReports.do");
		$.ajax( {
			url : url,
			cache : false,
			data : pars,
			success : function(html) {
				$("#HostelFeeReports").addClass("grid_14");
				$("#HostelFeeReports").html(html);
				$("#HostelFeeReports").show();
			}
		});
		document.myform1.submit();
	} else {
		alert("Please select at least one value.");
		return false;
	}
}
</script>
