<%@ include file="/common/taglibs.jsp"%>
<head>
	<title>SMS | Financial Reports</title>
</head>
<div class="grid_12">&nbsp;</div>
<div class="grid_12">
		<div class="grid_4" style="margin-right: 40px;">
			<form name="myform"
				action="${pageContext.request.contextPath}/reports/ajaxPrintViewStudentInvoiceDefaulters.do?pdfId=pdf" target="_new">
				<s:select label="Select Report Type " cssClass="required" required="true" 
				headerKey="S" headerValue="- Select Report Type -" onchange="ajaxAllFeeReprotsCollections(this.value);"
				list="collectionAndFeeDuesList" name="yourOptionValue" id="yourOptionValue" theme="css_xhtml"
				value="2" />
			</form>
		</div>
		<div id="schoolFeeReports" class="grid_8">
		</div>
</div>
<script type="text/javascript">
$(document).ready(function() {
	changePageTitle("Payment Reports");
	$('.blockHeader h2').html('Manage Academics');
});
function ajaxAllFeeReprotsCollections(yourOptionValue) {
	var pars = "yourOptionValue=" + yourOptionValue;
	if (yourOptionValue== "Collection")
	  {
	  	var url = jQuery.url.getChatURL("/admin/ajaxDoCollectionByFeeDetails.do");
		  	$.ajax( {
			url : url,
			cache : false,
			data : pars,
			success : function(html) {
					$("#schoolFeeReports").html(html);
			}
		});
	  }
	else if (yourOptionValue== "A")
	  {
	    var url = jQuery.url.getChatURL("/admin/ajaxDoAttendanceByAttendanceDetails.do");
		    $.ajax( {
			url : url,
			cache : false,
			data : pars,
			success : function(html) {
					$("#schoolFeeReports").html(html);
			}
		});
	  }
	else if (yourOptionValue== "Fee Dues")
	  {
	  	var url = jQuery.url.getChatURL("/admin/ajaxDoFeeDefaulters.do");
		  	$.ajax( {
			url : url,
			cache : false,
			data : pars,
			success : function(html) {
					$("#schoolFeeReports").html(html);
			}
		});
	   /*document.myform.submit();
	   $("#schoolFeeReports").html("");*/
	  }
	  else{
	  	alert("Please select at least one value.");
	    return false;
	  }
}
</script>