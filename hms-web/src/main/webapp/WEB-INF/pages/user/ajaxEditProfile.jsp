<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Edit Profile
				</div>
			</div>
			<div class="portlet-body">
			<div class="row profile" >
				<jsp:include page="/WEB-INF/pages/user/ajaxEditProfileDetails.jsp"></jsp:include>
			</div>
				</div>
		</div>
	</div>
</div>

<!--
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/livevalidation_standalone.js">
</script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>
<script type="text/javascript">
$(document).ready(
				function() {
				changePageTitle("Edit My Profile");
					var validator;
					jQuery.validator
							.addMethod(
									"password",
									function(value, element) {
										var result = this.optional(element)
												|| value.length >= 6
												&& /\d/.test(value)
												&& /[a-z]/i.test(value);
										if (!result) {
											var validator = this;
										}
										return result;
									},
									"Your password must be at least 6 characters long and contain at least one number and one character.");
					$.subscribe('formValidation', function(event, data) {
						if ($('#signupForm').valid()){
						}
						else
							 event.originalEvent.options.submit=false;
					});
				});
$('.numeric').numeric();
function formatPhoneNumber(object) {
	var phoneString = object.value;
	if (phoneString.length == 1) {
		phoneString = "+91-" + phoneString;
	}
	object.value = phoneString;
}
</script>
-->