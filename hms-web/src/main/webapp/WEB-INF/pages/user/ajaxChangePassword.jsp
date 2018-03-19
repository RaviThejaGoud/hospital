<%@ include file="/common/taglibs.jsp"%>
<div class="col-md-10">
<s:form id="campaignChangePassword" action="ajaxChangePassword"
	method="post" theme="simple" cssClass="form-horizontal" namespace="/user">
	<s:hidden name="user.id"></s:hidden>
	<div class="form-group">
		<label class="control-label">
			<span class="required">*</span>User Name
		</label>
		<s:if test="%{(user.person.mobileNumber) == (user.secondaryUsername)}">
			<sj:textfield name="user.secondaryUsername" id="user"  
			cssClass="form-control" maxlength="60" readonly="true"/>	
									</s:if>
									<s:else>
										<sj:textfield name="user.username" id="user"  
			cssClass="form-control" maxlength="60" readonly="true"/>	
									</s:else>
	</div>
	
	<div class="form-group">
		<label class="control-label">
			<span class="required">*</span>Current Password
		</label>
		<input type="password" class="form-control required"  maxlength="50" id="currPass" />
	</div>
	<div class="form-group">
		<label class="control-label">
			<span class="required">*</span>New Password
		</label>
		<s:password id="tempPassword" name='user.password' type="password"  maxlength="50"
			cssClass="required form-control required" />
	</div>
	<div class="form-group">
		<label class="control-label">
			<span class="required">*</span>Re-type New Password
		</label>
		<s:password id="confirmationPassword" name='confirmPassword'
			type="password" maxlength="50" cssClass="required form-control required" />

	</div>
	<div class="margin-top-10">
		<sj:submit   cssClass="btn green" targets="editProfileContntDiv" validate="true"
			value="Change Password"   onBeforeTopics="changeFormPassword" />
		<s:url id="EditProfileSettings" action="ajaxDoEditProfile"
			namespace="/user">
		</s:url>
		<sj:a href="%{EditProfileSettings}" targets="mainContentDiv"
			cssClass='btn default'>
								Cancel</sj:a>
	</div>
	</s:form>
</div>
<style type="text/css">
.profile p{
  color: #008000;
}
p.word-taken{
  color: #fa5555;
}
</style>
<script type="text/javascript">
$(document).ready(function() {
	$("#currPass").focus();
	$("#currPass").autoCheck("${pageContext.request.contextPath}/admin/ajaxCheckCurrentPasswordMatchedOrNot.do",
		{
			minChars : 1,
			min : "no",
		});
	$.destroyTopic('changeFormPassword');
	$.subscribe('changeFormPassword', function(event, data) {
		var minLength = 6;
		var pwd = $('#tempPassword').val();
		var conPwd = $('#confirmationPassword').val();
		var currPass = $("#currPass").val();
		if(currPass==""){
			alert('Please enter current password.');
			event.originalEvent.options.submit = false;
		}
		if (pwd == "" && conPwd == "") {
			alert('Please enter new password and Re-type password.');
			event.originalEvent.options.submit = false;
		} else if (conPwd == "") {
			alert('Please enter Re-type password.');
			event.originalEvent.options.submit = false;
		} else if (pwd.length < minLength) {
			alert('Your password must be at least ' + minLength + ' characters long. Try again.');
			event.originalEvent.options.submit = false;
		} else if (pwd != conPwd) {
			alert('New password and Re-type password are not matched. Please try again!');
			event.originalEvent.options.submit = false;
		} else if ($('p.word-taken').html() == 'Current Password not matched.') {
			event.originalEvent.options.submit = false;
		}
		else{
			return true;
		}
		
	});
});
/*function passwordStrength(password) {
	var desc = new Array();
	desc[0] = "Very Weak";
	desc[1] = "Weak";
	desc[2] = "Better";
	desc[3] = "Medium";
	desc[4] = "Strong";
	desc[5] = "Strongest";

	var score = 0;

	//if password bigger than 6 give 1 point
	if (password.length > 6)
		score++;

	//if password has both lower and uppercase characters give 1 point	
	if ((password.match(/[a-z]/)) && (password.match(/[A-Z]/)))
		score++;

	//if password has at least one number give 1 point
	if (password.match(/\d+/))
		score++;

	//if password has at least one special caracther give 1 point
	if (password.match(/.[!,@,#,$,%,^,&,*,?,_,~,-,(,)]/))
		score++;

	//if password bigger than 12 give another 1 point
	if (password.length > 8)
		score++;
	if (password.length >= 1) {
		$("#passwordDescription").show();
		$("#passwordDescription").html(desc[score]);
		$("#passwordStrength").show();
		$("#passwordStrength").attr('class', 'strength' + score);
	} else {
		$("#passwordDescription").hide();
		$("#passwordStrength").hide();
	}
}*/
</script>