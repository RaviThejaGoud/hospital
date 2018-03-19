<%@ include file="/common/taglibs.jsp"%>
<%@ page
	import="org.springframework.security.web.authentication.AbstractProcessingFilter"%>
<%@ page
	import="org.springframework.security.web.authentication.AuthenticationProcessingFilter"%>
<%@ page
	import="org.springframework.security.core.AuthenticationException"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head profile="http://gmpg.org/xfn/11">
		<title>Eazy School :: Empowering School Administration</title>
<link rel="shortcut icon" href="${pageContext.request.contextPath}/images/favlogo.ico"/>
<sj:head debug="false" compressed="true" jquerytheme="dark-hive"
			loadFromGoogle="%{google}" ajaxhistory="%{ajaxhistory}"
			defaultIndicator="myDefaultIndicator"
			defaultLoadingText="Loading ..." />
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
	<div class="logo">
		<img src="img/bg/logo.png" alt="" /> 
	</div>
	<div class="content">
		<form  class="form-vertical login-form"  name="f" action="<c:url value='j_spring_security_check'/>" method="post" onsubmit="saveUsername(this);">
		   	<jsp:include page="/common/messages.jsp" />
			<c:if test="${param.login_error != null}">
				<div class="alert alert-error">
					<button data-dismiss="alert" class="close"></button>
					<c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
						<span>Your login attempt was not successful due to 
							<c:choose>
								<c:when test="${SPRING_SECURITY_LAST_EXCEPTION.message == 'Maximum sessions of 1 for this principal exceeded'}">
									${fn:replace(SPRING_SECURITY_LAST_EXCEPTION.message, 'Maximum sessions of 1 for this principal exceeded', 'an active session with same login details. Please logout from that session and re login.')}
								</c:when>
								<c:otherwise>
									<c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}" /></span>
								</c:otherwise>
							</c:choose>
						</span>
					</c:if>
				</div>
			</c:if>
			
			<s:if test="%{#session.firstName != null &&  #session.firstName != ''}">
		
				<%-- <a href="<c:url value="/j_spring_security_logout"/>"><i class="icon-key"></i>Logout</a> --%>
			<%
						
						 HttpSession sessieon = request.getSession( false ) ;
						   if ( sessieon != null ) sessieon.invalidate(); 
						    /* RequestDispatcher requestDispatcher ;
						   requestDispatcher = request.getRequestDispatcher("/login.jsp" ) ;
						   requestDispatcher.forward( request, response ) ;  */
			   
			%>
		</s:if>
		
			<h3 class="form-title">Sign In</h3>
			<div class="alert alert-error hide">
				<button class="close" data-dismiss="alert"></button>
				<span>Enter any username and password.</span>
			</div>
			<div class="form-group">
				<!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->
				<label class="control-label visible-ie8 visible-ie9">Username</label>
				<div class="controls">
					<div class="input-icon left">
						<i class="fa fa-user"></i>
							<input type='text' name='j_username' id="j_username" class="form-control form-control-solid placeholder-no-fix" placeholder="Username" maxlength="40" size="20" />
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label visible-ie8 visible-ie9">Password</label>
				<div class="controls">
					<div class="input-icon left">
						<i class="fa fa-lock"></i>
						<input type='password' id="j_password" name='j_password' maxlength="40" size="20" placeholder="Password"  class="form-control form-control-solid placeholder-no-fix"/>
					</div>
				</div>
			</div>
			<div class="form-actions">
				<button type="submit" class="btn btn-success uppercase">Login&nbsp;<i class="m-icon-swapright m-icon-white"></i></button><span class="dotLine"></span>
				<label class="rememberme check">
					<input type='checkbox' name='rememberMe' id="rememberMe" value="Remember Me" />Remember
				 </label><span class="dotLine"></span>
				 <a href="${pageContext.request.contextPath}/signup/doForgotPassword.do"  id="forget-password"  class="forget-password">Forgot Password?</a> 
		   </div>
			<div class="login-options">
				<ul class="social-icons">
					<li style="text-indent: 0;width: 90px;">
						 <a target="_NEW" href="https://play.google.com/store/apps/details?id=com.urt.android.asms&amp;hl=en" style="margin: 0px;"><img style="border: 0px none;height: 34px;width: 88px;" alt="Download App" src="images/en_app_rgb_wo_45.png"></a>
					</li>
					<li style="text-indent: 0">
						<img style="margin: 0px;width: 110px;height: 33px;" src="https://seal.godaddy.com/images/3/en/siteseal_gd_3_h_l_m.gif" alt="Security Seal" id="seal-img">
					</li>
					<li style="text-indent: 0">
						<span id="cdSiteSeal1">
							<script type="text/javascript" src="//tracedseals.starfieldtech.com/siteseal/get?scriptId=cdSiteSeal1&amp;cdSealType=Seal1&amp;sealId=55e4ye7y7mb731739a7de84205f673jp6u9y7mb7355e4ye74be917f57c3c7518"></script>
						</span>
					</li>
				</ul>
				<br/>
				<div class="row">
					<div class="form-group">
						<div class="col-md-12">
							<span class="label label-sm label-danger"> Note for parent login : </span>
								&nbsp;If you are not able to login with your old login id. Please login with your registered mobile number as username.
						</div>
					</div>
				</div>
			</div>
			<%-- <div class="create-account">
				<p>
					<span style="font-size: 11px;">Parent Don't have an account yet ?</span>  
					<a href="${pageContext.request.contextPath}/signup/doParentRegistration.do"  id="register-btn" class="uppercase">Create an account</a>
				</p>
			</div> --%>
		</form>
	</div>	 
	<div class="copyright">
		2017 &copy; HYNIVA Consulting Services PVT Ltd. <br/>
         <a target="_NEW" style="color: #7a8ca5;" href='<c:url value="privacyPolicy.jsp"/>'> Privacy Policy</a>
            
		<%-- <a target="_blank" href='<c:url value="privacyPolicy.jsp"/>' style="margin: 0px;">Privacy Policy</a>
	</div>
	
	<%-- <jsp:include page="privacyPolicy.jsp" /> --%>
  
<script src="${pageContext.request.contextPath}/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/scripts/newScripts/jquery.cookie.min.js" type="text/javascript" ></script>
<script src="${pageContext.request.contextPath}/plugins/uniform/jquery.uniform.min.js" type="text/javascript" ></script>	
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/validation/jquery.validate.js"></script>					 
<script  src="${pageContext.request.contextPath}/plugins/select2/select2.min.js" type="text/javascript" ></script>					
<script type="text/javascript"  src="${pageContext.request.contextPath}/scripts/newScripts/app.js"></script>  
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/newScripts/login.js"></script>
<script type="text/javascript">
	function saveUsername(theForm) {
		if (theForm.rememberMe.checked) {
			$.cookie("username", document.getElementById("j_username").value, {
				expires : 30
		});
		$.cookie("password", document.getElementById("j_password").value, {
			expires : 30
		});
	} else {
		$.cookie("username", null);
		$.cookie("password", null);
		$.cookie("username", document.getElementById("j_username").value, {
			expires : 30
		});
	}
	$.cookie("rememberMe", theForm.rememberMe.checked, {
		expires : 30
	});
	return true;
}
$(document).ready(function() {
	var urlHref= window.location.href;
	 if (urlHref.lastIndexOf(".do") >=0){
			document.location.href="";
	}
		 App.init();
		  Login.init();			
	$("#j_username").focus();
	/* This function is used to get cookies */
	if ($.cookie('rememberMe') == 'true') {
		$('#rememberMe').parent('span').addClass('checked');
		if ($.cookie("username") != null) {
			document.getElementById("j_username").value = $.cookie("username");
			document.getElementById("j_password").value = $.cookie("password");
		}
	} else {
		$('#rememberMe').parent('span').removeClass('checked');
		if ($.cookie('username') != null && $.cookie('password') != null) {
			document.getElementById("j_username").value ="";
			document.getElementById("j_password").value ="";
		}/*else{
			document.getElementById("j_username").value = $.cookie("username");
			document.getElementById("j_password").value = $.cookie("password");	
		}*/
	}
});
</script>	
</body>
</html>