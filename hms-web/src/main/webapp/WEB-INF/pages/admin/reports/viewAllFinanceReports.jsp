<%@ include file="/common/taglibs.jsp"%>
<!--<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/validation/jquery.validate.js">
</script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>
-->
<head>
	<title>SMS | School Library Details</title>
</head>
<body />
	<div class="block_head">
		<div class="grid_14 ">
			<h2>
				Reports
			</h2>
		</div>
	</div>
	<div class="block_content">
		<div id="commonStep13" class="commomnTabs">
			<div class="grid_12" style="">
				<div class="grid_3">
					<label class="labelRight">
						Select a Report Type :
					</label>
				</div>
				<div class="grid_5 ">
					<s:select cssClass="required campaignText" required="true" headerKey="S"
						headerValue="- Select Report Type -"
						onchange="ajaxAllFeeReprots(this.value);" list="smsFeeReports"
						name="yourOptionValue" id="yourOptionValue" theme="css_xhtml" />
				</div>
			</div>
			<div class="grid_12">&nbsp;</div>
		</div>
		<div class="grid_12"></div>
		<div id="SMSReports" class="grid_8">
		</div>
	</div>
	<script type="text/javascript">
changePageTitle("Payment Reports");
function ajaxAllFeeReprots(yourOptionValue) {
	var pars = "yourOptionValue=" + yourOptionValue;
	if (yourOptionValue == "School Reports") {
		var url = jQuery.url
				.getChatURL("/admin/ajaxAllAdminSchoolFeeReports.do");
		$.ajax( {
			url : url,
			cache : false,
			data : pars,
			success : function(html) {
				$("#SMSReports").html(html);
			}
		});
	} else if (yourOptionValue == "Hostel Reports") {
		var url = jQuery.url
				.getChatURL("/hostel/ajaxAllAdminSchoolFeeReports.do");
		$.ajax( {
			url : url,
			cache : false,
			data : pars,
			success : function(html) {
				$("#SMSReports").html(html);
			}
		});
	} else if (yourOptionValue == "Finance Reports") {
		var url = jQuery.url
				.getChatURL("/admin/ajaxAllAdminTransportSchoolFeeReports.do");
		$.ajax( {
			url : url,
			cache : false,
			data : pars,
			success : function(html) {
				$("#SMSReports").html(html);
			}
		});
		/*document.myform.submit();
		$("#schoolFeeReports").html("");*/
	} else {
		alert("Please select at least one value.");
		return false;
	}
}
</script>