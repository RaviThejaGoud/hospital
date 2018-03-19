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

		<!--<title>groupsinteractive.com&nbsp;|&nbsp;Groups Interactive
			&#8211; Small Group Ministry Made Simple</title>

		-->

		<title>Eazy School</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta name="generator" content="WordPress 3.0.1" />
		<!-- leave this for stats please -->
		<link rel="stylesheet" href="styles/public/typeselect.css"
			type="text/css" media="screen" />
		<!--<link rel="alternate" type="application/rss+xml" title="RSS 2.0"
			href="http://groupsinteractive.com/feed/" />
		-->
		<script type="text/javascript" src="styles/public/typeface-custom.js">
</script>
		<script type="text/javascript"
			src="styles/public/alexandriaflf_bold.js">
</script>

		<script type="text/javascript" src="styles/public/jquery-1.js">
</script>
		<script src="styles/public/coda-slider.js" type="text/javascript">
</script>
		<script type="text/javascript" src="styles/public/typeselect.js">
</script>
		<link rel="EditURI" type="application/rsd+xml" title="RSD"
			href="http://groupsinteractive.com/xmlrpc.php?rsd" />
		<link rel="wlwmanifest" type="application/wlwmanifest+xml"
			href="wp-includes/wlwmanifest.xml" />
		<link rel='index' title='eazyschool.in' href='http://eazyschool.in/' />
		<meta name="generator" content="WordPress 3.0.1" />

		<link href="styles/public/style.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/scripts/default/jQuery.url.js">
</script>
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/scripts/validation/jquery.validate.js">
</script>
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/scripts/group/group.js">
</script>
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/scripts/common/jquery.alphanumeric.js">
</script>
		<script type="text/javascript">

function getAjaxDoGetContactUs() {
	$("#contactUsContent")
			.html(
					'<div align="center" style="margin: 150px;"><img src="${pageContext.request.contextPath}/images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
	var url = jQuery.url.getChatURL("/user/ajaxDoGetContactUs.do");
	$.ajax( {
		url : url,
		cache : false,
		success : function(html) {
			$("#contactUsContent").html(html);
		}
	});
}
</script>
	</head>
	<body class="home blog" style="margin: 10px 0px 0px 0px">
		<div id="container">
			<div class="wrapper">
				<div>
					<div id="topnav" class="clearfloat">
						<h1 id="logo">
							<a href="http://eazyschool.in"><font color="#fff">eazySchool</font>
							</a>
						</h1>
						<ul id="main_navigation">
							<li class="current_page_item">
								<a href="http://eazyschool.in">Home</a>
							</li>
							<li class="page_item page-item-119">
								<a
									href="${pageContext.request.contextPath}/signup/ajaxOnlineApplicationForm.do"
									title="Admissions">Admissions</a>
							</li>
							<li class="page_item page-item-2">
								<a href="http://www.uroomtech.com" title="About">About</a>
							</li>
							<li class="page_item page-item-20">
								<a href="#" onclick="javascript:getAjaxDoGetContactUs();">Contact
									Us</a>
							</li>
							<li class="page_item page-item-119">
								<a href="http://eazyschool.in" title="Demo">Demo</a>
							</li>
						</ul>
					</div>
					<!-- #topnav -->
					<!-- Homepage Highlight -->
					<div id="highlight" class="clearfloat">
						<div>
							<img src="images/highlight.png" alt="" />
							<!--  <h1 class="select">
							eazySchool,Made Easy
						</h1> -->
						</div>
						<div style="float: left; width: 380px;">
							<p style="font-size: 16px;">

								eazySchool is powerful and simple collaboration platform for all
								schools.
							</p>
							<p style="font-size: 16px;">
								eazySchool is envisioned to evolve as a Category Killer
								resulting in a one-stop,one-click experience and
								Connect,Collaborate,Share platform which seamlessly connects
								with all stake holders schools,teachers,students and parents and
								service of the pivotal functions.
							</p>
						</div>


						<div id="buttons" style="margin: 0px -50px -150px 0px;"
							id="buttons">
							<a id="call_to_action1"
								href="http://groupsinteractive.com/lite/contact-us">
								<!--<em>Buy
									Now</em>  --> </a>
						</div>

					</div>

					<div id="highlight" style="margin: 0px 0px; float: right;"
						class="userRegistration">
						<div>
							<div>
								<h3 style="color: #ffffff; margin: 10px 0px 10px 0px;">
									Login
								</h3>
							</div>
							<!-- .block_head ends -->
							<div>
								<c:if test="${param.login_error != null}">
									<div class="message errormsg">
										<p>
											Invalid user name and/or Password,try again
										</p>
									</div>
								</c:if>
								<!--<form action="style-guide.html" method="post">-->
								<form name="f" action="<c:url value='j_spring_security_check'/>"
									method="post"
									onsubmit="saveUsername(this,'<c:url value="/" />');">
									<p>
										<label style="color: #ffffff">
											User name:
										</label>
										<br />
										<input type='text' name='j_username' id="j_username"
											class="loginStyle text" maxlength="40" size="20"
											value='<c:if test="${not empty param.login_error}"><c:out value="${SPRING_SECURITY_LAST_USERNAME}"/></c:if>' />
									</p>
									<p>
										<label style="color: #ffffff">
											Password:
										</label>
										<br />
										<input type='password' id="j_password" name='j_password'
											class="loginStyle text" maxlength="40" size="20">
									</p>
									<table style="margin: 0px 0px 0px -4px; font-size: 12px;">
										<tr>
											<td>
												<input type="submit" value="Login" class="submit"
													title="Continue" />
											</td>
											<td style="vertical-align: middle;">
												<input type="checkbox" name="rememberMe" value="Remember Me"
													class="checkbox" id="rememberMe" checked="checked" />
												<label for="rememberme" style="color: #ffffff">
													Remember Me
												</label>
											</td>
										</tr>
									</table>

								</form>
							</div>
							<!-- .block_content ends -->
							<!-- .login ends -->
						</div>
					</div>

					<!-- #highlight -->
				</div>
				<!-- #header -->
				<div id="contactUsContent">
					<div class="clearfloat"
						style="float: left; margin-top: 10px;">

						<div id="slider">


							<!-- #sidebar -->
							<div id="slides" style="float:left;width: 960px;">
							<div style="color:#000;padding:10px">
								<h3>Admission Information for 2011-12 </h3>
								<h4>Admissions has been opened for the  Academic Year 2011 - 12 					<a href="${pageContext.request.contextPath}/signup/onlineApplicationForm.do?customerId=1" target="_new">Click Here</a> to fill online.</h4>

</div>								

							</div>
						</div>
					</div>
					<!-- #slides -->

				</div>
				<!-- #content -->
			</div>
			<!-- #wrapper -->
		</div>
<div id="footer">
			<div class="wrapper">
				<p>
					2011 Copyright &copy; www.eazyschool.in
				</p>
			</div>
		</div>
		<!-- #footer -->

		<!--<script type="text/javascript">
	var gaJsHost = (("https:" == document.location.protocol) ? "https://ssl."
			: "http://www.");
	document
			.write(unescape("%3Cscript src='"
					+ gaJsHost
					+ "google-analytics.com/ga.js' type='text/javascript'%3E%3C/script%3E"));
</script>
		-->
		<script type="text/javascript">
try {
	var pageTracker = _gat._getTracker("UA-3390449-16");
	pageTracker._trackPageview();
} catch (err) {
}
</script>

		<script type="text/javascript">
function saveUsername(theForm, path) {
	var expires = new Date();
	//expires.setTime(expires.getTime() + 24 * 30 * 60 * 60 * 1000); // sets it for approx 30 days.
	//setCookie("username", theForm.j_username.value, expires,path,path);
	createCookie("rememberMe", theForm.rememberMe.checked, 30);
	if (theForm.rememberMe.checked) {
		createCookie("username", theForm.j_username.value, 30);
		createCookie("password", theForm.j_password.value, 30);
	}
}
/* This function is used to set cookies */
function setCookie(name, value, expires, path, domain, secure) {
	document.cookie = name + "=" + escape(value)
			+ ((expires) ? "; expires=" + expires.toGMTString() : "")
			+ ((path) ? "; path=" + path : "")
			+ ((domain) ? "; domain=" + domain : "")
			+ ((secure) ? "; secure" : "");
}
function createCookie(name, value, days) {
	if (days) {
		var date = new Date();
		date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));
		var expires = "; expires=" + date.toGMTString();
	} else
		var expires = "";
	document.cookie = name + "=" + value + expires + "; path=/";
}

function readCookie(name) {
	var nameEQ = name + "=";
	var ca = document.cookie.split(';');
	for ( var i = 0; i < ca.length; i++) {
		var c = ca[i];
		while (c.charAt(0) == ' ')
			c = c.substring(1, c.length);
		if (c.indexOf(nameEQ) == 0)
			return c.substring(nameEQ.length, c.length);
	}
	return null;
}
/* This function is used to get cookies */
if (getCookie("rememberMe") != null && !getCookie("rememberMe").isEmpty) {
	if (getCookie("rememberMe") == 'true') {
		$("rememberMe").checked = true;
		if (getCookie("username") != null && !getCookie("username").isEmpty) {
			document.getElementById("j_username").value = getCookie("username");
			document.getElementById("j_password").value = getCookie("password");
		}
	} else {
		document.getElementById("rememberMe").checked = false;
	}
}

/* This function is used to delete cookies */
function deleteCookie(name, path, domain) {
	if (getCookie(name)) {
		document.cookie = name + "=" + ((path) ? "; path=" + path : "")
				+ ((domain) ? "; domain=" + domain : "")
				+ "; expires=Thu, 01-Jan-70 00:00:01 GMT";
	}
}
/* This function is used to get cookies */
function getCookie(name) {
	var prefix = name + "="
	var start = document.cookie.indexOf(prefix)

	if (start == -1) {
		return null;
	}

	var end = document.cookie.indexOf(";", start + prefix.length)
	if (end == -1) {
		end = document.cookie.length;
	}

	var value = document.cookie.substring(start + prefix.length, end)
	return unescape(value);
}

/* This function is used to delete cookies */
function deleteCookie(name,path,domain) {
          if (getCookie(name)) {
            document.cookie = name + "=" +
              ((path) ? "; path=" + path : "") +
              ((domain) ? "; domain=" + domain : "") +
              "; expires=Thu, 01-Jan-70 00:00:01 GMT";
          }
        }
    </script>
	</body>

</html>