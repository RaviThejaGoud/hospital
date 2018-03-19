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
	<div id="changeForgetTagetDiv">
		<div class="logo">
			<img src="../img/bg/logo.png" alt="EazySchool" />
		</div>
		<div class="content" style="width: 500px;">
			<h3>Update Password </h3>
			<jsp:include page="/common/messages.jsp" />
			<s:form action="ajaxChangeForgotPassword" id="form-changeforgot"
				method="post" cssClass="form-horizontal" theme="simple"
				namespace="/signup">
				<s:hidden name="customerId" value="%{tempId}"></s:hidden>

				<div class="row">
					<div class="col-md-9">
						<div class="input-icon left">
							<i class="fa fa-lock"></i>
							<s:password name="password" id="password"
								cssClass="form-control forgotOtp required" type="password"
								placeholder="Enter New Password" maxlength="10">
							</s:password>
						</div>
					</div>
				</div>
				<br />
				<div class="row">
					<div class="col-md-9">
						<div class="input-icon left">
							<i class="fa fa-lock"></i>
							<s:password name="conformPassword" id="conformPassword"
								type="password" cssClass="form-control forgotOtp required"
								placeholder="Conform Password" maxlength="10">
							</s:password>
						</div>
					</div>
				</div>
				<br />
				<div class="form-actions fluid">
					<sj:submit targets="changeForgetTagetDiv" value="Submit"
						cssClass="submitBt btn blue" validate="true"
						onBeforeTopics="chanegForgotFormValidation"
						formIds="form-changeforgot" />
					<img src="../img/bg/bigWaiting.gif" alt="Loading..."
						title="Loading..." id="myDefaultIndicator"
						style="display: none; background-repeat: no-repeat; position: fixed; z-index: 1000; top: 50%; left: 45%; text-align: center;" />
					<a href="${pageContext.request.contextPath}/login.jsp"><button
							class="btn red" id="back-btn" type="button">
							<i class=" fa fa-arrow-circle-o-left "></i> Back
						</button> </a>
				</div>
			</s:form>
		</div>
		<div class="copyright">2017 &copy; HYNIVA Consulting Services
			PVT Ltd.</div>
	</div>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/scripts/validation/jquery.validate.js"></script>
</body>
<script type="text/javascript">
$(document).ready(function() {
	var tempString ='<s:property value="tempString3" />';
	if(tempString == "R"){
		$("#back-btn").trigger("click"); 
	}
	$.destroyTopic('chanegForgotFormValidation');
	$.subscribe('chanegForgotFormValidation', function(event, data) {
			var pwd = $('#password').val();
			var conPwd = $('#conformPassword').val();
			if (pwd == "" && conPwd == "") {
				alert('Please enter required fields.');
				event.originalEvent.options.submit = false;
			} else if (conPwd == "") {
				alert('Please enter re-type new conform  password.');
				event.originalEvent.options.submit = false;
			} else if (pwd == "") {
				alert('Please enter new password.');
				event.originalEvent.options.submit = false;
			} else if (pwd != conPwd) {
				alert('New password and confirm password are not matched. Please try again.');
				$('#conformPassword').val("");
				event.originalEvent.options.submit = false;
			}
	});
});
 </script>
</html>