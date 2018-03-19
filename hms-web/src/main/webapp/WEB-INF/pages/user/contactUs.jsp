<%@ include file="/common/taglibs.jsp"%>
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
<div id="" class="userContactUs">
	<div>
		<div style="margin: 0px 0px -10px;">
			<h3 style="color: #ffffff">
				Contact Us
			</h3>
		</div>
		<div>
			<!--<form action="style-guide.html" method="post">-->
			<s:form action="/user/ajaxAddContactUs.do" theme="xhtml"
				id="addContactUs">
				<sj:textfield name="contactUs.name" id="driverfName" required="true"
					label="Name" requiredposition="first"
					cssClass="required text small" maxlength="20"></sj:textfield>
				<sj:textfield name="contactUs.email" id="emailAddress"
					requiredposition="first" label="E-Mail" labelposition="first"
					cssClass="required email text small" required="true" maxlength="40"></sj:textfield>
				<sj:textfield name="contactUs.mobileNumber" id="mobileNumber"
					required="true" label="Mobile Number" requiredposition="first"
					cssClass="numeric text required small" maxlength="14"
					onblur="return validateMobNumber(this.value)"
					onkeypress="return formatPhoneNumber(this,event);"></sj:textfield>
				<sj:textarea rows="3" cols="20" name="contactUs.description"
					label="Description" requiredposition="first" cssStyle="width:130%"
					cssClass="text small required"></sj:textarea>


				<sj:submit   cssClass="submit small" value="Submit"
					indicator="indicator" targets="contactUsContent"
					onClickTopics="addContactUsFormValidation" formIds="addStudent" />

				<div id="buttons" style="margin: 0px 1000px 83px 0px;">
					<a id="call_to_action1"
						href="${pageContext.request.contextPath}/user/ajaxCancelContactUs.do">Cancel
					</a>
				</div>
			</s:form>
		</div>
	</div>
</div>
<script language="JavaScript" type="text/javascript">
$(document).ready(function() {

	$.subscribe('addContactUsFormValidation', function(event, data) {
		if ($('#addContactUs').valid())
			return true;
		else
			return false;
	});
	$('.numeric').numeric();
	$('.alphabet').alpha();
});

</script>
