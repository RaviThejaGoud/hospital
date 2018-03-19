<%@ include file="/common/taglibs.jsp"%>
<head>
	<title>SMS | School Fee Details</title>
</head>
<div class="grid_8">
	<div class="grid_4" style="margin-right: 40px;">
		<form name="myform2" action="${pageContext.request.contextPath}/reports/ajaxPrintViewStudentInvoiceDefaulters.do?pdfId=pdf" target="_new">
			<input type="hidden" name="pdfId" value="pdf" />
			<s:select label="Select Type" cssClass="required" required="true" 
				headerKey="S" headerValue="-Select Option-" onchange="ajaxFeeReprots(this.value);"
				list="#{'TD':'Total Defaulters','CD':'Class Wise Defaulters'}" 
				name="yourOptionValue" id="yourOptionValue" theme="css_xhtml"
				value="2" />
		</form>
	</div>
		<div id="schoolTermlist" class="grid_3"></div>
</div>
<script type="text/javascript">
function ajaxFeeReprots(yourOptionValue) {
var pars = "yourOptionValue=" + yourOptionValue;
	if (yourOptionValue== "CD")
	  {
	  	var url = jQuery.url.getChatURL("/admin/ajaxDoClassWiseDefaulters.do");
		  	$.ajax( {
			url : url,
			cache : false,
			data : pars,
			success : function(html) {
					$("#schoolTermlist").html(html);
			}
		});
	  }
	else if (yourOptionValue== "TD")
	{
		document.myform2.submit();
		$("#schoolTermlist").html("");
	}
	else if(yourOptionValue == 'S')
	{
		alert("Please select at least one value.");
	    return false;
	}
}
</script>