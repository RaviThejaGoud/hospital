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
			<div class="content" style="width: 500px;">
				<jsp:include page="/common/messages.jsp" />
			<div id="findPassword" style="display: none;">
				<h3>Find Your Password</h3>
				<p>In case you have forgotten your password,Please enter your
					login id you can get your temporary password instantly on your
					email address or mobile number and you can immediately set a new
					password online.</p>
			</div>
			<div id="findOTP" style="display: none;">
				<h3>Enter One Time Password</h3>
				<hr />
				<br />
				<p>
					One Time Password (OTP) has been sent to your mobile <s:property value="tempString2" />.
				</p>
			</div>
			</br>
				<s:form action="ajaxForgotPassword" id="form-login" method="post" cssClass="form-horizontal"
					theme="simple" namespace="/signup">
					<s:hidden name="anyId" id="anyId" value=""></s:hidden>
					<s:hidden name="tempString2" id="tempString2" value="%{tempString2}"></s:hidden>
					<div class="row" id="exterUsreNameText" style="display: none;">
						<div class="col-md-9">
							<div class="input-icon left">
								<i class="fa fa-envelope"></i>
								<sj:textfield name="username" id="loginId"
									cssClass="form-control loginId required" placeholder="Please enter your registered username"
									maxlength="90"></sj:textfield>
							</div>
						</div>
					</div>
					<div class="row" style="display: none;" id="enterOtpText">
						<div class="col-md-9">
							<div class="input-icon left">
								<i class="fa fa-envelope"></i>
								<sj:textfield name="otp" id="forgotOTP"
									cssClass="form-control forgotOTP" placeholder="Please enter your OTP"
									maxlength="90"></sj:textfield>
							</div>
						</div>
					</div>
					<br/>
					<div id="loading">
					<img   src="../img/bg/bigWaiting.gif" alt="Loading..." title="Loading..." id="myDefaultIndicator" style="display:none;background-repeat: no-repeat;position: fixed;z-index: 1000;top: 50%;left: 45%;text-align:center;" />
					</div>
					<div class="form-actions fluid">
						<sj:submit targets="forgetTagetDiv" value="Submit" id="submitButton"
							cssClass="submitBt btn blue" validate="true"
							onBeforeTopics="forgotFormValidation" formIds="form-login" />
						<a href="${pageContext.request.contextPath}/login.jsp"><button
								class="btn red" id="back-btn" type="button">
								<i class=" fa fa-arrow-circle-o-left "></i> Back
							</button> </a>
					</div>
				</s:form>
				<br/>
				<div class="row" id="findPassword1" style="display: none;">
					<div class="form-group">
						<div class="col-md-1">
							<span class="label label-sm label-danger"> Note </span>
						</div>
						<div class="col-md-11">
							<p>
								If you have not registered or not received the credentials from
								school admin then please contact your school for login account
								details.
							</p>
						</div>
					</div>
				</div>
			</div>
			<div class="copyright">
		2017 &copy; HYNIVA Consulting Services PVT Ltd.
	</div>
	</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/validation/jquery.validate.js"></script>	
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/ems.js"></script>
<script language="JavaScript" type="text/javascript">
jQuery(document).ready(function() {
	
	var enterOTP ='<s:property  value="tempString"/>';
	var mobileNo='<s:property  value="tempString2"/>';
	if(enterOTP == "O"){
		showLoadingImage();
		$('#exterUsreNameText').hide();
		$('#findPassword1').hide();
		$('#findPassword').hide();
		$('#anyId').val('OTP');
		$('#tempString2').val(mobileNo);
		$('#enterOtpText').show();
		$('#forgotOTP').addClass("required");
		$('#loginId').removeClass("required");
		$('#findOTP').show();
	}else{
		$('#findOTP').hide();
		$('#enterOtpText').hide();
		$('#forgotOTP').removeClass("required");
		$('#findPassword1').show();
		$('#findPassword').show();
		$('#exterUsreNameText').show();
		showLoadingImage();
	}
	function showLoadingImage() {
	    $('#form-login').append('<div id="loading-image"><img   src="../img/bg/bigWaiting.gif" alt="Loading..." title="Loading..." id="myDefaultIndicator" style="display:none;background-repeat: no-repeat;position: fixed;z-index: 1000;top: 50%;left: 45%;text-align:center;" /></div>');
	}
	function hideLoadingImage() {
	    $('#loading-image').remove();
	}
});
</script>
</body>
</html>