<%@ include file="/common/taglibs.jsp"%>
<div id="parentPasswordDiv">
	<div data-width="560"  class="modal fade modal-overflow in" id="responsivePassword" style="display: block; width: 560px; margin-left: -379px; margin-top: 50px;" aria-hidden="true">
		<div class="modal-header">
			<button type="button" class="close changeParentPassword" data-dismiss="modal" aria-hidden="true"></button>
			<h4 class="modal-title">
				Change Password
			</h4>
		</div>
		<div class="modal-body">
	<div class="form-body">
		<%@ include file="/common/messages.jsp"%>
		<s:form id="changePasswordId" action="ajaxChangeParentPassword" namespace="/user" method="post" theme="simple" cssClass="form-horizontal">
			<s:hidden name="user.id" />
			<s:hidden name="anyTitle" value="user login first time"/>
			<div class="form-body">
			<div class="form-group">
				<label class="control-label">
					<span class="required">*</span>User Name
				</label>
				<sj:textfield name="user.username" id="user" disabled="true"
					cssClass="form-control" maxlength="40" readonly="true"/>
			  	
			</div>
			<div class="form-group">
				<label class="control-label">
					<span class="required">*</span>Current Password
				</label>
			  <input type="password" class="m-wrap form-control" maxlength="50" id="currPass">
			</div>
			<div class="form-group">
				<label class="control-label">
					<span class="required">*</span>New Password
				</label>
			  <s:password id="tempPasswords" name='user.password'  maxlength="50"
				cssClass="form-control " />
			</div>
			
			<div class="form-group">
				<label class="control-label">
					<span class="required">*</span>Re-type New Password
				</label>
				<s:password id="confirmationPasswords" name='confirmPassword'
				maxlength="50" cssClass="m-wrap form-control " />
			</div>
			</div>
				<div class="form-actions">
						<div class="col-md-offset-0 col-md-9">
							<div class="submit-btn">
							<sj:submit  cssClass="btn green" targets="parentPasswordDiv" validate="true" formIds="changePasswordId"
									value="Submit"  onBeforeTopics="changeFormPasswordScreen" />
								<button type="button" data-dismiss="modal" class="btn default changeParentPassword">Cancel</button>
					</div>
				</div>
		 </div>
		</s:form>
	</div>
	</div>
</div>
</div>
<script type="text/javascript">
$(document).ready(function() {
	$.destroyTopic('changeFormPasswordScreen');
	$("#currPass").focus();
	$("#currPass").autoCheck("${pageContext.request.contextPath}/admin/ajaxCheckCurrentPasswordMatchedOrNot.do",
		{
			minChars : 1,
			min : "no",
		});
	$.subscribe('changeFormPasswordScreen', function(event, data) {
		//if ($('#changePasswordId').valid()){
			var minLength = 6;
			var pwd = $('#tempPasswords').val();
			var conPwd = $('#confirmationPasswords').val();
			//var password = $('#currPass').val();
			if (pwd == "" && conPwd == "") {
				alert('Please enter required fields.');
				event.originalEvent.options.submit = false;
			} else if (conPwd == "") {
				alert('Please enter Re-type New password.');
				event.originalEvent.options.submit = false;
			} else if (pwd == "") {
				alert('Please enter New password.');
				event.originalEvent.options.submit = false;
			}else if (pwd.length < minLength || conPwd.length < minLength) {
				alert('Your password must be at least ' + minLength + ' characters long. Try again.');
				event.originalEvent.options.submit = false;
			} else if (pwd != conPwd) {
				alert('New Password and Re-type New Password are not matched.Please try again!');
				event.originalEvent.options.submit = false;
			} else if ($('p.word-taken').html() == 'Current Password not matched.') {
					event.originalEvent.options.submit = false;
			}
			else{
				$('button.close').click();
		}
	});
	 $('div.modal-header').click();
	 if($("div#responsivePassword:hidden")){
	  $('a#showChangePassword').removeAttr('href');
	 }
	$('button.changeParentPassword').click(function(){
		$("div#responsivePassword").hide();
	});
});

</script>