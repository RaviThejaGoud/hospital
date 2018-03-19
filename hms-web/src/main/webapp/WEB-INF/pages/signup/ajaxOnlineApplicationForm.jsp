<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>
<head>
	<title>Eazy School :: Online Application Form</title>
</head>
<div id="signupStep1" class="wrapper">
	<div class="grid_14">
		<div class="block grid_14">
			<div class="block_head">
				<h2>
					Online Application Form For (
					<s:property value="admissionSettings.academicYear.academicYear" />
					)
				</h2>
			</div>
			<!-- .block_head ends -->
			<div class="block_content">
				<div id="steps" style="width: 760px; padding-left: 10px;">
					<fieldset>
						<s:form action="ajaxOnlineRegistration" theme="css_xhtml"
							id="addStudent" method="post">
							<h1>
								Student Information
							</h1>
							<s:hidden name="custId"></s:hidden>
							<s:hidden name="admissionSettings.academicYear.id"></s:hidden>
							<div class="grid_12">
								<div class="grid_6 alpha omega">
									<sj:textfield name="onlineApplicationDetails.firstName"
										id="driverfName" required="true" label="First Name"
										labelposition="top" cssClass=" required text small"
										maxlength="50"></sj:textfield>
								</div>
								<div class="grid_6 alpha omega">
									<sj:textfield name="onlineApplicationDetails.lastName"
										id="driverlName" required="true" label="Last Name"
										labelposition="top" cssClass=" required text small"
										maxlength="50"></sj:textfield>
								</div>
							</div>
							<div class="grid_12">
								<div class="grid_6 alpha omega">
									<sj:datepicker id="date0" label="Date Of Birth" readonly="true"
										name="onlineApplicationDetails.dateOfBirth"
										cssClass="required text small" required="true"
										changeMonth="true" changeYear="true" yearRange="1960" />
								</div>
								<div class="grid_6 alpha omega">
									<sj:radio list="#{'M':'Male','F':'Female'}"
										name="onlineApplicationDetails.gender"
										cssClass="radio required" tabindex="7" required="true"
										label="Gender" />
								</div>
							</div>
							<div class="grid_12">
								<div class="grid_6 alpha omega">
									<sj:textfield name="onlineApplicationDetails.nationality"
										id="nationality" tabindex="10" label="Nationality"
										cssClass="textfield small" maxlength="50"></sj:textfield>
								</div>
								<div class="grid_6 alpha omega">
									<sj:textfield name="onlineApplicationDetails.religion"
										id="religion" tabindex="10" label="Religion"
										cssClass="textfield small" maxlength="30"></sj:textfield>
								</div>
							</div>
							<div class="grid_12">
								<div class="grid_6 alpha omega">
									<div class="tableactions grid_6" style="padding-bottom: 0px;">
										<s:select id="bGroup"
											list="#{'A+':'A+ve','A-':'A-ve','A1+':'A1+ve','A1-':'A1-ve','A1B+':'A1B+ve','A1B-':'A1B-ve','A2B+':'A2B+ve','A2B-':'A2B-ve','A2+':'A2+ve','A2-':'A2-ve','B+':'B+ve','B-':'B-ve','AB+':'AB+ve','AB-':'AB-ve','O+':'O+ve','O-':'O-ve'}"
											label="Blood Group" headerKey="" headerValue="- Select -"
											name="onlineApplicationDetails.bloodGroup" />
									</div>
								</div>
								<div class="grid_6 alpha omega">
									<div class="tableactions grid_6" style="padding-bottom: 0px;">
										<s:select list="classList" listKey="id"
											listValue="description" label="Class applied for"
											cssClass="required" required="true"
											name="onlineApplicationDetails.className" headerKey=""
											headerValue="- Select -" requiredposition="first">
										</s:select>
									</div>
								</div>
							</div>
							<div class="grid_12">
								<div class="grid_6 alpha omega">
									<div class="tableactions grid_6" style="padding-bottom: 0px;">
										<s:select list="tempList" listKey="id" listValue="description"
											label="Class last attended"
											name="onlineApplicationDetails.lastClassAttended"
											headerKey="" headerValue="- Select -"
											requiredposition="first">
										</s:select>
									</div>
								</div>
								<div class="grid_6 alpha omega">
									<div class="tableactions grid_6" style="padding-bottom: 0px;">
										<sj:textfield name="onlineApplicationDetails.lastSchool"
											id="driverlName" label="Name of the last school attended"
											labelposition="top" cssClass=" text small" maxlength="80"></sj:textfield>
									</div>
								</div>
							</div>
							<div class="grid_12">
								<h1>
									Parent / Guardian's Information
								</h1>
							</div>
							<div class="grid_12">
								<div class="grid_6 alpha omega">
									<sj:textfield name="onlineApplicationDetails.fatherName"
										id="fatherName" required="true"
										label="Father / Guardian's Name" labelposition="top"
										cssClass=" required text small" maxlength="50"></sj:textfield>
								</div>
								<div class="grid_6 alpha omega">
									<sj:textfield name="onlineApplicationDetails.motherName"
										id="motherName" label="Mother Name" labelposition="top"
										cssClass=" text small" maxlength="50"></sj:textfield>
								</div>
							</div>
							<div class="grid_12">
								<div class="grid_6 alpha omega">
									<sj:textfield name="onlineApplicationDetails.parentEmail"
										id="emailAddress" label="Parent E-Mail Id" labelposition="top"
										cssClass="email text small" maxlength="40"></sj:textfield>
								</div>
								<div class="grid_6 alpha omega">
									<sj:textfield name="onlineApplicationDetails.occupation"
										id="occupation" label="Occupation" labelposition="top"
										cssClass="text small" maxlength="40"></sj:textfield>
								</div>
							</div>
							<div class="grid_12">
								<h1>
									Contact Details
								</h1>
							</div>
							<div class="grid_12">
								<div class="grid_6 alpha omega">
									<sj:textfield name="onlineApplicationDetails.phoneNumber"
										id="phoneNumber" label="Phone Number"
										cssClass="numeric textfield small" maxlength="14"></sj:textfield>
								</div>
								<div class="grid_6 alpha omega">
									<sj:textfield name="onlineApplicationDetails.mobileNumber"
										id="mobileNumber" required="true" label="Mobile Number"
										cssClass="numeric required textfield small" maxlength="14"
										onblur="return validateMobNumber(this.value)"
										onkeypress="return formatMobileNumber(this,event);"></sj:textfield>
								</div>
							</div>
							<div class="grid_12">
								<div class="grid_6 alpha omega">
									<sj:textfield name="onlineApplicationDetails.streetName"
										id="streetName" cssClass="text small" label="Street Name"
										labelposition="top" maxlength="30"></sj:textfield>
								</div>
								<div class="grid_6 alpha omega">
									<sj:textfield name="onlineApplicationDetails.city" id="city"
										label="City" labelposition="top"
										cssClass="required text small" maxlength="50" required="true"></sj:textfield>
								</div>
							</div>
							<div class="grid_12">
								<div class="grid_6 alpha omega">
									<div class="tableactions grid_6 alpha omega"
										style="padding-bottom: 0px;">
										<s:select id="state" list="statesList" label="State"
											cssClass="required" required="true" listKey="stateCode"
											listValue="stateName" headerKey="" headerValue="- Select -"
											name="onlineApplicationDetails.state"
											onchange="javascript:getCastDetailsByState(this);" />
									</div>
								</div>
								<div class="grid_6 alpha omega">
									<sj:textfield name="onlineApplicationDetails.postalCode"
										id="pincode" label="Pincode" labelposition="top"
										required="true" cssClass="numeric required text small"
										maxlength="6"></sj:textfield>
								</div>
							</div>
							<div class="grid_12 ">
								<div class="grid_6 alpha omega">
									<div id="resultsDiv1"></div>
								</div>
								<div class="grid_5 alpha omega">
									<div id="resultsDiv2"></div>
								</div>
							</div>
							<!--
	  		 <s:select id="castName" list="castSettingList" label="Caste Name" cssClass="required"  listKey="castName" cssStyle="width:500px;"
 						listValue="castName" headerKey="" headerValue="- Select -" name="onlineApplicationDetails.castName" theme="css_xhtml" />
	   -->


							<div class="clear"></div>
							<div class="grid_12">
								<img align="center" style="display: none; float: right;"
									alt="Loading..."
									src="${pageContext.request.contextPath}/images/icons/bigWaiting.gif"
									id="indicator">
								<a
									href="${pageContext.request.contextPath}/signup/admissionDetails.do?id=1"
									class="cancelButton">Cancel</a>
								<sj:submit   cssClass="submit small" value="Submit"
									indicator="indicator" validate="true" formIds="addStudent"
									targets="signupStep1" />
							</div>
						</s:form>
					</fieldset>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function() {
	$('.numeric').numeric();
});
function getCastDetailsByState(selectBox) {
	var stateName = selectBox.value;
	var custId = '<s:property value="custId"/>';
	var url = jQuery.url.getChatURL("/signup/ajaxOnlineCastDetailsByState.do");
	if (stateName.length == 0) {
		alert("!Oops select state.");
	} else {
		$("#resultsDiv1")
				.html(
						'<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		var pars = "stateName=" + stateName + "&custId=" + custId;
		$.ajax( {
			url : url,
			cache : false,
			data : pars,
			success : function(html) {
				$("#resultsDiv1").html(html);
				document.getElementById('resultsDiv1').style.display = "block";
				//document.getElementById('schoolBooksList').style.display="none";
		}
		});
	}
}
</script>