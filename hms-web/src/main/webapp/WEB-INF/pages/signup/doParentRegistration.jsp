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
	<body class="login"  id="parentRegSteps">
		<div class="logo">
			<img src="../img/bg/logo.png" alt="Eazy School" />
		</div>
		<div class="content">
		<div id="forgotPasswordDiv">
			<jsp:include page="/common/messages.jsp" />
			<s:if test='%{plTitle != null}'>
					<div class="alert alert-success">Congrats, your account is active.
					<s:url id="forgotPasswordLink" action="sendPassWordToUsers" namespace="/signup" escapeAmp="false" includeParams="all">
						<s:param name="tempString" value="%{tempString}"> </s:param>
						<s:param name="plTitle" value="%{plTitle}"> </s:param>
					</s:url>
					<sj:a href="%{forgotPasswordLink}"
						targets="forgotPasswordDiv" cssClass="ajaxify FGP"> Click here 
					</sj:a>
					&nbsp;to resend your login information.</div>
			</s:if>
			<s:form action="parentRegStep1" method="post"
					cssClass="form-vertical" theme="simple"
					enctype="multipart/form-data" id="parent" namespace="/signup">
					<h3>
						Parent Registration
					</h3>
					<p>
						Enter your personal details below:
					</p>
					<div class="form-group">
						<!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->
						<label class="control-label visible-ie8 visible-ie9">Username</label>
						<div class="input-icon">
							<i class="fa fa-mobile"></i>
							<input type="text" name="anyId" maxlength="10" placeholder="Mobile Number"  id="mobile" class="form-control placeholder-no-fix">
						</div>
					</div>
					<div align="center" style='color: lightslategray;padding-bottom:8px;'>(OR)</div>
					<div class="form-group">
						<!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->
						<label class="control-label visible-ie8 visible-ie9">Email</label>
						<div class="input-icon">
							<i class="fa fa-envelope"></i>
							<input type="text" name="anyTitle"  maxlength="90" placeholder="Email"  id="emailAdd" class="form-control placeholder-no-fix email">
						</div>
					</div>
					<div class="form-actions">
						<sj:submit targets="parentRegSteps" value="Search"
							cssClass="submitBt btn blue" validate="true"
							onBeforeTopics="parentFormValidation" formIds="parent" />
						<a href="${pageContext.request.contextPath}/login.jsp"
							class="btn default">Cancel</a>
					</div>
				</s:form>
			</div>
		</div>
		<div class="copyright">
			2015 &copy; HYNIVA Consulting Services PVT Ltd.
		</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/validation/jquery.validate.js"></script>	
<script  src="${pageContext.request.contextPath}/plugins/select2/select2.min.js" type="text/javascript" ></script>	
<script type="text/javascript"  src="${pageContext.request.contextPath}/scripts/newScripts/app.js"></script>   
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/newScripts/login.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	 Login.init();	
});

$.subscribe('parentFormValidation', function(event, data) {
	var mobileNumber = $('#mobile').val();
	var emailAddress = $('#emailAdd').val();
	if ((mobileNumber == '' || mobileNumber == 'Mobile Number') && (emailAddress == '' || emailAddress == 'Email')) {
		alert("Please enter mobile / email.");
		event.originalEvent.options.submit=false;
	} else{
		return true;
	}
});
</script>
	</body>
</html>