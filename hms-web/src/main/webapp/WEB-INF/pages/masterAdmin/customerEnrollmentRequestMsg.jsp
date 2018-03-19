<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head profile="http://gmpg.org/xfn/11">
		<title>eazySchool :: Empowering School Administration</title>
		<sj:head debug="true" compressed="false" defaultIndicator="myDefaultIndicator"  jqueryui="false" />
 		<style type="text/css" media="all">
			@import url("${pageContext.request.contextPath}/plugins/bootstrap/css/bootstrap.min.css");
			@import url("${pageContext.request.contextPath}/plugins/font-awesome/css/font-awesome.min.css");
			@import url("${pageContext.request.contextPath}/styles/newCss/style-metronic.css");
			@import url("${pageContext.request.contextPath}/styles/newCss/style.css");	
			@import url("${pageContext.request.contextPath}/styles/newCss/style-responsive.css");			
			@import url("${pageContext.request.contextPath}/plugins/uniform/css/uniform.default.css");
  			@import url("${pageContext.request.contextPath}/styles/newCss/pages/login.css");
          </style>
	</head>
  <body class="login">
  <div id="forgetTagetDiv">
	<div class="logo">
		<img src="../img/bg/logo.png" alt="EazySchool" />
	</div>
			<div class="content" style="width: 1000px;">
				<jsp:include page="/common/messages.jsp" />
				<h3>
					Customer Registration Form
				</h3>
				  
				<s:if test="%{customerEnrollmentRequest != null}">
					<p>
						Your account creation process is completed and submitted for Eazyschool Administrator Review.
					</p>
				</s:if>
				<s:else>
				Problem for account creation process.
			</s:else>
				<br/>
				
			</div>
			<div class="copyright">
		2015 &copy; HYNIVA Consulting Services PVT Ltd.
	</div>
	</div>
	
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/jQuery.url.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/plugins/bootstrap-switch/static/js/bootstrap-switch.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/ems.js"> </script>
<script type="text/javascript"	src="${pageContext.request.contextPath}/scripts/common/jquery.alphanumeric.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/validation/jquery.validate.js"></script>	
<script type="text/javascript"  src="${pageContext.request.contextPath}/scripts/newScripts/app.js"></script>   
<script  src="${pageContext.request.contextPath}/plugins/select2/select2.min.js" type="text/javascript" ></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/common/jquery.autoCheck.js"></script>
<script type="text/javascript"	src="${pageContext.request.contextPath}/scripts/newScripts/form-components.js"></script>
<script  type="text/javascript" src="${pageContext.request.contextPath}/plugins/bootstrap/bootstrap-maxlength/boostrap-maxlength.min.js"></script> 

<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/formElementScript.js"></script>
<script type="text/javascript"  src="${pageContext.request.contextPath}/plugins/uniform/jquery.uniform.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js" type="text/javascript"></script>
<script type="text/javascript"  src="${pageContext.request.contextPath}/plugins/bootstrap-timepicker/js/bootstrap-timepicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/newScripts/bootstrap-datetimepicker.min.js"> </script>

<script type="text/javascript">
$(document).ready(function() {
FormComponents.init();
FormAdvanced.init();
$.destroyTopic('doCustomRegistartion');
$("input:checkbox, input:radio:not('.toggle')").uniform();  
	$("div#showHideGovtCustomers").hide();
	var validator;
	jQuery.validator.addMethod("password",function(value, element) {
		var result = this.optional(element)|| value.length >= 6 && /\d/.test(value)&& /[a-z]/i.test(value);
		if (!result) {
			var validator = this;
		}
		return result;
	},
	"Your password must be at least 6 characters long and contain at least one number and one character.");
	$.subscribe('doCustomRegistartion', function(event, data) {
		if($("input[name=chkBoxSelectedIds]:unchecked").length==$("input[name=chkBoxSelectedIds]").length){
			alert("Please select at least one syllabus type.");
			event.originalEvent.options.submit=false;
		}else{
			if ($('#' + data.id).is(":hidden")) {
				$('#' + data.id).show();
			} else {
				$('#' + data.id).show();
			}
			if ($("li#govtCustomersList").hasClass("selected")) {
				$("li#govtCustomersList a").click();
				$("li#govtCustomersList").removeClass("selected");
			}
		}
		 var custShortNum=$('input.custShortNumStr').parents('div').next('div').find('p.word-taken').html();
	 		if(custShortNum=='Already taken!!!'){
	     	    event.originalEvent.options.submit=false;
	     	    $('input.custShortNumStr').val('');
	          }
	 		 var custShortNum=$('input.email').parents('div').next('div').find('p.word-taken').html();
		 		if(custShortNum=='Already taken!!!'){
		     	    event.originalEvent.options.submit=false;
		     	    $('input.email').val('');
		          }
	});
	document.title = 'Eazy School SIGN UP ';
	$("input#email").autoCheck("${pageContext.request.contextPath}/common/ajaxCheckEmailId.do",{
			minChars : 3,
			min : "no"
		});
	
	$("input#sender").autoCheck("${pageContext.request.contextPath}/common/ajaxCheckCustomerShortNameAvailableOrNot.do",
			{
				minChars : 1,
				min : "no",
			});
	$("input.form-control").val('');
});


function getOrganizationSubTypes(selectBox) {
	var organizationId = $("select#organizationType").val();
	var url = jQuery.url.getChatURL("/signup/ajaxGetOrganizationSubTypes.do");
	if (organizationId.length == 0) {
		alert("!Oops select Organization Type.");
	} else {
		if ($("select#organizationType option:selected").text() == "Govt") {
			$("li#govtCustomersList").addClass("selected");
			document.getElementById('govtState').style.display = "block";
			document.getElementById('pvrState').style.display = "none";
		} else {
			document.getElementById('pvrState').style.display = "block";
			document.getElementById('govtState').style.display = "none";
			$("li#govtCustomersList").removeClass("selected");
		}

		$("#resultsDiv2")
				.html(
						'<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		var pars = "subjectId=" + organizationId;
		$.ajax( {
			url : url,
			cache : false,
			data : pars,
			success : function(html) {
				$("#resultsDiv2").html(html);
				document.getElementById('resultsDiv2').style.display = "block";
				//document.getElementById('schoolBooksList').style.display="none";
		}
		});
	}
}
function getDistricts(selectBox) {
	var organizationId = $("select#govtState1").val();
	var url = jQuery.url.getChatURL("/signup/ajaxGetDistrictsByState.do");
	if (organizationId.length == 0) {
		alert("please select state.");
	} else {
		$("#districtDiv")
				.html(
						'<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		var pars = "student.categoryId=" + organizationId;
		$.ajax( {
			url : url,
			cache : false,
			data : pars,
			success : function(html) {
				$("#districtDiv").html(html);
				document.getElementById('districtDiv').style.display = "block";
				//document.getElementById('schoolBooksList').style.display="none";
		}
		});
	}
}

$('.numeric').numeric();
function formatPhoneNumber(object) {
	var phoneString = object.value;
	if (phoneString.length == 1) {
		phoneString = "+91-" + phoneString;
	}
	object.value = phoneString;
}

function getDistricts(selectBox) {
	var organizationId = $("select#govtState1").val();
	var url = jQuery.url.getChatURL("/signup/ajaxGetDistrictsByState.do");
	if (organizationId.length == 0) {
		alert("please select state.");
	} else {
		$("#districtDiv")
				.html(
						'<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		var pars = "student.categoryId=" + organizationId;
		$.ajax( {
			url : url,
			cache : false,
			data : pars,
			success : function(html) {
				$("#districtDiv").html(html);
				document.getElementById('districtDiv').style.display = "block";
				//document.getElementById('schoolBooksList').style.display="none";
		}
		});
	}
}
</script></body>
</html>