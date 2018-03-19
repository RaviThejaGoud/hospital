<%@ include file="/common/taglibs.jsp"%>
<head>
	<title>SMS | School Fee Details</title>
</head>
	<div class="grid_4" style="margin-right: 40px;">
		<form name="myform2" action="${pageContext.request.contextPath}/reports/ajaxHostelStudentsDayOrOverAllFeeReceipt.do" target="_new">
			<input type="hidden" name="pdfId" value="pdf" />
			<s:select label="Select Type" cssClass="required" required="true" 
				headerKey="S" headerValue="-Select Option-" onchange="ajaxFeeReprots(this.value);"
				list="#{'T':'To Day','DB':'Days Between ', 'TW':'Term Wise ', 'O':'Over All'}" 
				name="yourOptionValue" id="yourOptionValue" theme="css_xhtml"
				value="2" />
		</form>
	</div>
	<div id="hostelTermlist" class="grid_3"></div>
<script type="text/javascript">
function ajaxFeeReprots(yourOptionValue) {
var pars = "yourOptionValue=" + yourOptionValue;
	if (yourOptionValue== "TW")
	  {
	  	var url = jQuery.url.getChatURL("/hostel/ajaxCollectionTermsByFeeDetails.do");
		  	$.ajax( {
			url : url,
			cache : false,
			data : pars,
			success : function(html) {
					$("#hostelTermlist").html(html);
			}
		});
	  }
	else if (yourOptionValue == 'DB'){
		var url = jQuery.url.getChatURL("/hostel/ajaxCollectionDaysBetweenByFeeDetails.do");
		  	$.ajax( {
			url : url,
			cache : false,
			data : pars,
			success : function(html) {
					$("#hostelTermlist").html(html);
			}
		});
	}
	else if (yourOptionValue== "T" || yourOptionValue== "O")
	{
		document.myform2.submit();
		$("#hostelTermlist").html("");
	}
	else if(yourOptionValue == 'S')
	{
		alert("Please select at least one value.");
	    return false;
	}
}
</script>