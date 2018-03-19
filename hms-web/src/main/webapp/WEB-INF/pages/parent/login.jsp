<%@ include file="/common/taglibs.jsp"%>
<%@ page
	import="org.springframework.security.web.authentication.AbstractProcessingFilter"%>
<%@ page
	import="org.springframework.security.web.authentication.AuthenticationProcessingFilter"%>
<%@ page
	import="org.springframework.security.core.AuthenticationException"%>
<head>
	<title>Login | Hyniva Consulting Services Application Management</title>
</head>
<body>
	<div id="hld">
		<div class="wrapper">
			<!-- wrapper begins -->
			<div class="block small center">
				<div class="block_head">
					<h2>
						Login
					</h2>
					<ul>
						<li>
							<a href="${pageContext.request.contextPath}/signup/registration.do" style="vertical-align: absmiddle;">Need an
								account?</a>
						</li>
					</ul>
				</div>
				<!-- .block_head ends -->
				<div class="block_content" style="background-color: #FFFFFF;">
					<c:if test="${param.login_error != null}">
                                       <div class="message errormsg"><p>Invalid User Name and/or Password, try again</p></div>
                    </c:if>
					<!--<form action="style-guide.html" method="post">-->
					<form name="f" action="<c:url value='j_spring_security_check'/>"
						method="POST" onsubmit="saveUsername(this,'<c:url value="/" />');">
						<p>
								<label>
									UserName:
								</label>
								<br />
								<input type='text' name='j_username' id="j_username"
									class="loginStyle text" maxlength="40" size="40"
									value='<c:if test="${not empty param.login_error}"><c:out value="${SPRING_SECURITY_LAST_USERNAME}"/></c:if>' />
						</p>	
						<p>
							<label>
								Password:
							</label>
								<br />						
							<input type='password' id="j_password" 	 name='j_password'
								class="loginStyle text" maxlength="40" size="40">
						</p>
						<div style="float: left;width: 90px;">
							<input type="submit" value="Login" class="submit" title="Continue" /> </div>
							&nbsp;<div style="float:left;padding-top: 14px;">
							<input type="checkbox" name="rememberMe" value="Remember Me" 
								class="checkbox" id="rememberMe" checked="checked" />
							<label for="rememberme"  >Remember Me	</label></div>
						
					</form>
					<div class="clear"></div>
					<div style="width: 500px;">
					<div style="float: left"><a href="${pageContext.request.contextPath}/signup/doForgotPassword.do">Forgot Password</a>&nbsp;<img style="vertical-align: middle;" src="${pageContext.request.contextPath}/images/phs.gif">&nbsp;</div><div style="float: left"><a href="http://support.uroomtech.com/upperroom_technologies/products/upperroom_technologies_groups_interactive" target="_new">Contact Support</a></div>
					</div>
				</div>
				<!-- .block_content ends -->
			</div>
			<!-- .login ends -->
		</div>
		<!-- wrapper ends -->
	</div>
	<!-- #hld ends -->
	<script type="text/javascript"> 
        function saveUsername(theForm,path) {
            var expires = new Date();
            //expires.setTime(expires.getTime() + 24 * 30 * 60 * 60 * 1000); // sets it for approx 30 days.
            //setCookie("username", theForm.j_username.value, expires,path,path);
            createCookie("rememberMe",theForm.rememberMe.checked,30);
            if(theForm.rememberMe.checked){
                createCookie("username", theForm.j_username.value,30);    
                createCookie("password",theForm.j_password.value,30);
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
        function createCookie(name,value,days) {
            if (days) {
                var date = new Date();
                date.setTime(date.getTime()+(days*24*60*60*1000));
                var expires = "; expires="+date.toGMTString();        
            }
            else var expires = "";    
            document.cookie = name+"="+value+expires+"; path=/";
        }
        
        function readCookie(name) {
            var nameEQ = name + "=";
            var ca = document.cookie.split(';');
            for(var i=0;i < ca.length;i++) {
                var c = ca[i];
                while (c.charAt(0)==' ') c = c.substring(1,c.length);
                if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length,c.length);
            }
            return null;
        }
        /* This function is used to get cookies */
         if (getCookie("rememberMe") != null && !getCookie("rememberMe").isEmpty) {
                if(getCookie("rememberMe")=='true'){
                   $("rememberMe").checked =true;
                   if (getCookie("username") != null && !getCookie("username").isEmpty) {
                        document.getElementById("j_username").value = getCookie("username");         
                        document.getElementById("j_password").value = getCookie("password");
                   }       
                }else{
                   document.getElementById("rememberMe").checked =false;           
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
        
            if (start==-1) {
                return null;
            }
            
            var end = document.cookie.indexOf(";", start+prefix.length) 
            if (end==-1) {
                end=document.cookie.length;
            }
        
            var value=document.cookie.substring(start+prefix.length, end) 
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