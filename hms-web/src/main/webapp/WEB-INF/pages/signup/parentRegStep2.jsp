<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/livevalidation_standalone.js"> </script>
<style type="text/css" media="all">
			@import url("${pageContext.request.contextPath}/plugins/bootstrap/css/bootstrap.min.css");
			@import url("${pageContext.request.contextPath}/plugins/font-awesome/css/font-awesome.min.css");
			@import url("${pageContext.request.contextPath}/styles/newCss/style-metronic.css");
			@import url("${pageContext.request.contextPath}/styles/newCss/style.css");	
			@import url("${pageContext.request.contextPath}/styles/newCss/style-responsive.css");			
			@import url("${pageContext.request.contextPath}/plugins/uniform/css/uniform.default.css");
	        @import url("${pageContext.request.contextPath}/plugins/select2/select2_metro.css");
	        @import url("${pageContext.request.contextPath}/plugins/bootstrap-datepicker/css/datepicker.css");
  			@import url("${pageContext.request.contextPath}/styles/newCss/pages/login-soft.css");
  			
          </style>
		 <link rel="stylesheet" type="text/css" id="style_color" href="${pageContext.request.contextPath}/styles/newCss/themes/default.css" />	 
<div id="loginTraining">
	<div id="formbox-login">
			<jsp:include page="/common/messages.jsp"></jsp:include>
		<div>
			<s:form action="ajaxParentRegStep3" theme="simple" id="parentReg" method="post" cssClass="form-horizontal">
				<s:hidden name="tempId"></s:hidden>
					<div class="form-group">
						<label class="control-label col-md-5"><span class="required">*</span>Parent First Name :</label>
						<div class="col-md-5">
							<sj:textfield name="viewStudentPersonAccountDetails.fatherName" id="firstName" 
							cssClass="required form-control" maxlength="60"></sj:textfield>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-5">Parent Last Name :</label>
						<div class="col-md-5">
							<sj:textfield name="lastName" id="lastName" 
							cssClass="form-control" maxlength="60"></sj:textfield>
						</div>
					</div>
					 <div class="form-group">
						<label class="control-label col-md-5">Parent Date Of Birth :</label>
						<div class="col-md-5">
							<div data-date-format="mm/dd/yyyy" class="input-group input-medium date date-picker">
									<input type="text"  id="parentDate"  readonly="" class="form-control"  name="startDate" >
									<span class="input-group-btn">
										<button type="button" class="btn default">
											<i class="fa fa-calendar"></i>
										</button>
									  </span>
								</div>
								<span class="help-block">(MM/DD/YYYY)</span>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-5"><span class="required">*</span>Parent Email :</label>
						<div class="col-md-5">
							<sj:textfield name="viewStudentPersonAccountDetails.parentEmail" id="email" 
							cssClass="form-control required email" maxlength="60"></sj:textfield>
						</div>
					</div>
					<div><span style="font-size: 11px;">If you
							don't have E-mail please </span><a href="https://www.gmail.com">create
							an account</a></div>
					<div class="form-group">
						<label class="control-label col-md-5"><span class="required">*</span>Password :</label>
						<div class="col-md-5">
								<s:password name="password" id="password"
								cssClass="form-control required" maxlength="60"></s:password>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-5"><span class="required">*</span>Confirm Password :</label>
						<div class="col-md-5">
							<s:password name="confirmPassword" id="confirmationPassword"
								cssClass="form-control  required"  maxlength="60"></s:password>
								<script type="text/javascript">
									var confirmPassword = new LiveValidation('confirmationPassword');
									confirmPassword.add(Validate.Confirmation, {
										match : 'password'
									});
								</script>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-5">Parent Mobile :</label>
						<div class="col-md-5">
							<sj:textfield name="viewStudentPersonAccountDetails.mobileNumber" id="mobile" 
							cssClass="form-control numeric required" onkeypress="return formatPhoneNumber(this);"  maxlength="14"></sj:textfield>
						</div>
					</div>
				<div class="form-actions fluid" style="background: #ccc;">
					<div class="col-md-offset-2 col-md-9" style="margin-top: 20px;">
						<sj:submit   targets="parentRegSteps" value="Continue"
							cssClass="btn blue" resetForm="true"
							onBeforeTopics="parentRegValidation" validate="true"
							formIds="parentReg" indicator="indicator"/>
						<a class="linkRight"
							href="${pageContext.request.contextPath}/login.jsp">
							<button class="btn" type="button" id="register-back-btn">
								<i class="m-icon-swapleft"></i>Back to Login
							</button>
						</a>
					</div>
				</div>
			</s:form>
				</div>
		</div>
	 </div>
	 <script src="${pageContext.request.contextPath}/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js" type="text/javascript"></script>
<script type="text/javascript"	src="${pageContext.request.contextPath}/scripts/common/jquery.alphanumeric.js"></script>
<script type="text/javascript"	src="${pageContext.request.contextPath}/scripts/newScripts/form-components.js"></script>
<script type="text/javascript">
$(document).ready(function() {
FormComponents.init(); 
	$.subscribe('parentRegValidation', function(event, data) {
		if ($('#parentReg').valid()){
		var minLength = 6;
		  var pwd = $('#password').val();
	      var conPwd=$('#confirmationPassword').val();
		       if(pwd=="" && conPwd==""){
		         alert('Please enter new password and confirm Password.');
		         return false;
		       }
		       else if(conPwd==""){
		          alert('Please enter confirm Password.');
		          return false;
		       }
		       else if (pwd.length < minLength) {
				alert('Your password must be at least ' + minLength + ' characters long. Try again.');
				return false;
			   }
		       else if(pwd!=conPwd){
		         alert('new Password and Confirm Password are not matched.Please try again!');
		         return false;
		       }else{
		    	   return true;	
		       }
		      } else{
		         return false;
		     }
			
	});
	$('.numeric').numeric();
});
function formatPhoneNumber(object) {
	var phoneString = object.value;
	if (phoneString.length == 1) {
		phoneString = "+91-" + phoneString;
	}
	object.value = phoneString;
}
</script>
<style type="text/css">
.login .content{
 width:410px;
}
.LV_invalid {
    color: #CC0000;
}
</style>
