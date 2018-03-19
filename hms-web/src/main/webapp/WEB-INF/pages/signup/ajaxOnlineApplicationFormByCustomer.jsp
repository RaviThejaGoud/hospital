<%@ include file="/common/taglibs.jsp"%>
<head>
	<style type="text/css" media="all">
@import
	url("${pageContext.request.contextPath}/styles/default/reset.css");

@import url("${pageContext.request.contextPath}/styles/960/960.css");

@import
	url("${pageContext.request.contextPath}/styles/default/style.css");

@import
	url("${pageContext.request.contextPath}/styles/default/style1.css");

@import
	url("${pageContext.request.contextPath}/styles/default/resetStyle.css")
	;
</style>


	<sj:head debug="true" jquerytheme="dark-hive" compressed="false"
		defaultIndicator="myDefaultIndicator" defaultLoadingText="Loading ..." />
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/scripts/default/jQuery.url.js">
</script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/scripts/jQuery/fancybox/jquery.fancybox-1.3.1.js">
</script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/scripts/common/jquery.autoCheck.js">
</script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/scripts/validation/jquery.validate.js">
</script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/scripts/default/jquery.sortElements.js">
</script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/scripts/common/jquery.alphanumeric.js">
</script>
	<link rel="stylesheet" type="text/css"
		href="${pageContext.request.contextPath}/scripts/jQuery/fancybox/jquery.fancybox-1.3.1.css" />
	<!--[if  gt IE 7]>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/default/ie.css" />	
		<![endif]-->
	<!--[if lt IE 8]>	
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/default/ie.css" />	
		<![endif]-->
	<title>Eazy School :: Online Application Form</title>
</head>

<div class="wrapper container_16">
		<!-- wrapper begins -->
		<div class="wrapper">
				<div class="grid_16 block grid_18MarginLeft">
					<div class="grid_16 omega">
						<div id="header_top">
								<div id="topnav" class="clearfloat">
									<span style="float: left; min-height: 66px;padding-left: 10px;"> <a id=""
										href="#"> <img src='<s:property  value="customer.customerLogo"/>'
												alt='<s:property  value="customer.customerLogo" />' height="60px"; width="230px;" border="0" /> </a> </span>
								</div>
								<div class="grid_1">&nbsp;</div>
								<div class="grid_12">
									<h1><s:property value="customer.organization"/></h1>
										<s:property value="customer.customerFormattedAddress"/>
								</div>
								<div class="clear"></div>
						</div>
						<div class="block_head">
					<h2>
						Online Application For 
						<s:property value="admissionSettings.academicYear.academicYear" />
					</h2>
				</div>
				<!-- .block_head ends -->
				<div class="block_content">
				<div id="onlineAppCont">
					<div id="steps">
						<fieldset>
							<s:form action="ajaxOnlineAdmisisonsApplicationForm" theme="css_xhtml"
							id="addStudentOnlineInfo" method="post" namespace="/admin">
							<s:hidden name="custId" value="%{admissionSettings.custId}"></s:hidden>
							<s:hidden name="academicYearId" value="%{admissionSettings.academicYearId}"></s:hidden>
							<s:hidden name="tempId" value="%{admissionSettings.id}"></s:hidden>
							<s:hidden name="onlineApplicationDetails.className" id="applClassName"></s:hidden>
							<s:hidden name="onlineApplicationDetails.appliedThroughOnline" value="true"></s:hidden>
							<fieldset>
								<h2>
									STUDENT INFORMATION
								</h2>
								<p>
									Please fill the form and submit.
								</p>
								<div class="grid_12">
								<div class="grid_12">
											<label>
												Upload Image:
											</label>
									</div>
										<div class="grid_6" style="padding-bottom: 18px">
											<s:file name="upload" id="photoURL1" tabindex="2"></s:file>
										</div>
								</div>
								<div class="grid_12">
									<div class="grid_6 alpha omega">
										<sj:textfield name="onlineApplicationDetails.firstName"
											tabindex="2" id="firstName" required="true"
											label="First Name" labelposition="top"
											cssClass="alphabet required text small" maxlength="60"></sj:textfield>
									</div>
									<div class="grid_6 alpha omega">
										<sj:textfield name="onlineApplicationDetails.lastName"
											tabindex="3" id="lastName" required="true" label="Last Name"
											labelpisition="top" cssClass="alphabet required text small"
											maxlength="60"></sj:textfield>
									</div>
								</div>
								<div class="grid_12">
									<div class="grid_6 alpha omega">
										<sj:datepicker id="date0" label="Date of Birth"
											name="onlineApplicationDetails.dateOfBirth" tabindex="4"
											cssClass="text small" readonly="true" changeMonth="true"
											changeYear="true" yearRange="1960" />
									</div>
									<div class="grid_4">
										<sj:radio list="#{'M':'Male','F':'Female'}" tabindex="5"
											name="onlineApplicationDetails.gender" required="true"
											id="gender" label="Gender" />
									</div>
								</div>
								<div class="grid_12">
									<div class="grid_6 alpha omega">
										<s:select list="tempList1" listKey="id"
											listValue="skillTypeName" cssClass="textfield small"
											label="Select Religion" id="religion" tabindex="6"
											name="onlineApplicationDetails.religionId.id" headerKey="0"
											headerValue="- Select -">
										</s:select>
									</div>
									<div class="grid_6 alpha omega">
										<sj:textfield name="onlineApplicationDetails.nationality"
											id="nationality" tabindex="7" label="Nationality"
											cssClass="textfield small" maxlength="50"></sj:textfield>
									</div>
								</div>
								<div class="grid_12">
									<div class="grid_6 alpha omega">
										<div class=" grid_6">
											<sj:textfield name="onlineApplicationDetails.lastSchool"
												tabindex="8" label="Name of the last school" id="lastSchool"
												cssClass="alphabet text small" maxlength="80"></sj:textfield>
										</div>
									</div>
									<div class="grid_6 alpha omega">
										<div class=" grid_6">
											<s:select id="bGroup"
												list="#{'A+':'A+ve','A-':'A-ve','A1+':'A1+ve','A1-':'A1-ve','A1B+':'A1B+ve','A1B-':'A1B-ve','A2B+':'A2B+ve','A2B-':'A2B-ve','A2+':'A2+ve','A2-':'A2-ve','B+':'B+ve','B-':'B-ve','AB+':'AB+ve','AB-':'AB-ve','O+':'O+ve','O-':'O-ve'}"
												label="Blood Group" headerKey="" headerValue="- Select -"
												tabindex="9" name="onlineApplicationDetails.bloodGroup" />
										</div>
									</div>
								</div>
								<div class="grid_12">
									<div class="grid_6 alpha omega">
										<div id="allClasses">
											<jsp:include
												page="/WEB-INF/pages/admin/admission/ajaxClassList.jsp"></jsp:include>
										</div>
									</div>
									<div class="grid_6 alpha omega">
										<div class="grid_6">
											<label style="width: 206px;">
												Name of the last class studied:
											</label>
											<sj:textfield
												name="onlineApplicationDetails.lastClassAttended"
												tabindex="11" id="lastClassAttended"
												cssClass="alphabet text small" maxlength="60"></sj:textfield>
										</div>
									</div>
								</div>
								<div class="grid_12">
									<div class="grid_6 alpha omega">
										<s:select id="castName" list="castSettingList"
											label="Community" listKey="id" cssStyle="width:200px;"
											listValue="castName" headerKey="" headerValue="- Select -"
											name="person.castId" theme="css_xhtml" tabindex="12"
											onchange="javascript:getSubCastDetailsByCast(this);" />
									</div>
									<div class="grid_6 alpha omega">
										<div id="resultsDiv2">
											<jsp:include
												page="/WEB-INF/pages/admin/student/ajaxEditAdmittedStudentSubCastListByCast.jsp"></jsp:include>
										</div>

									</div>
								</div>
								<div class="grid_12">
										<div class="grid_6 alpha omega">
											<sj:textfield
												name="onlineApplicationDetails.transferCertificateNo"
												tabindex="15" id="transferCertificateNo"
												label="Transfer Certificate No" cssClass="text small"
												maxlength="40"></sj:textfield>
										</div>
										<div class="grid_6 alpha omega">
											<s:select id="categor" list="schoolCategoriesList" label="Fee Category"
											 listKey="id" listValue="categoryName"
											name="onlineApplicationDetails.categoryId" />
										</div>
									</div>
									<s:if test='%{customer.transportModuleStatus==true}'>
										<div class="grid_12">
											<div class="grid_6 alpha omega">
												<sj:radio
													list="#{'O':'Own','P':'Private','T':'School Transport'}"
													name="onlineApplicationDetails.transportMode" tabindex="14"
													label="Transport Mode" id="transportMode" />
											</div>
										</div>
									</s:if>
								<div class="grid_12">
									<h2>
										PARENT / GUARDIAN'S INFORMATION
									</h2>
								</div>
								<div class="grid_12">
									<div class="grid_6 alpha omega">
										<sj:textfield name="onlineApplicationDetails.fatherName"
											tabindex="16" id="fatherName" required="true"
											label="Father / Guardian's Name" labelposition="top"
											cssClass="alphabet required text small" maxlength="60"></sj:textfield>
									</div>
									<div class="grid_6 alpha omega">
										<div class="grid_3">
											<label style="width: 220px;">
												Father / Guardian's Occupation
											</label>
										</div>
										<sj:textfield name="onlineApplicationDetails.occupation"
											tabindex="17" id="occupation" labelposition="top"
											cssClass="text small" maxlength="40"></sj:textfield>
									</div>
								</div>
								<div class="grid_12">
									<div class="grid_6 alpha omega">
										<sj:textfield name="onlineApplicationDetails.motherName"
											tabindex="18" id="motherName" label="Mother Name"
											labelposition="top" cssClass="alphabet text small"
											maxlength="50"></sj:textfield>
									</div>
									<div class="grid_6 alpha omega">
										<sj:textfield
											name="onlineApplicationDetails.motherQualification"
											tabindex="19" id="motherQualification"
											label="Mother Qualification" cssClass="text small"
											maxlength="20"></sj:textfield>
									</div>
								</div>
								<div class="grid_12">
									<div class="grid_6 alpha omega">
										<div class="grid_3">
											<label style="width: 220px;">
												Father / Guardian's Designation
											</label>
										</div>
										<sj:textfield name="onlineApplicationDetails.designation"
											tabindex="20" id="designation" cssClass="text small"
											maxlength="60"></sj:textfield>
									</div>
									<div class="grid_3">
										<label style="width: 220px;">
											Father / Guardian's Qualification
										</label>
									</div>
									<div class="grid_6">
										<sj:textfield
											name="onlineApplicationDetails.fatherQualification"
											tabindex="21" id="fatherQualification" cssClass="text small"
											maxlength="20"></sj:textfield>
									</div>
								</div>
								<div class="grid_12">
									<div class="grid_6 alpha omega">
										<sj:textfield name="onlineApplicationDetails.parentEmail"
											id="emailAddress" label="Parent E-Mail Id"
											labelposition="top" cssClass="email text small" tabindex="22"
											maxlength="60"></sj:textfield>
									</div>
									<div class="grid_6">
										<sj:textfield name="onlineApplicationDetails.annualIncome"
											tabindex="23" id="guardianQualifiction" label="Annual Income"
											cssClass="text small numeric" maxlength="7"></sj:textfield>
									</div>
								</div>
								<div class="grid_12">
									<h2>
										CONTACT DETAILS
									</h2>
								</div>
								<div class="grid_12">
									<h1>
										RESIDENTIAL ADDRESS
									</h1>
								</div>
								<div class="grid_12">
									<div class="grid_6 alpha omega">
										<sj:textfield name="onlineApplicationDetails.streetName"
											tabindex="25" id="streetName" cssClass="text small"
											label="Street Name" labelposition="top" maxlength="200"></sj:textfield>
									</div>
									<div class="grid_6 alpha omega">
										<sj:textfield name="onlineApplicationDetails.city" id="city"
											tabindex="26" label="City" labelposition="top"
											cssClass="text small" maxlength="40"></sj:textfield>
									</div>
								</div>
								<div class="grid_12">
									<div class=" grid_6 alpha omega">
										<s:select id="state" list="statesList" label="State"
											tabindex="27" listKey="stateCode" listValue="stateName"
											headerKey="" headerValue="- Select -"
											name="onlineApplicationDetails.state" />
									</div>
									<div class="grid_6 alpha omega">
										<sj:textfield name="onlineApplicationDetails.postalCode"
											id="pincode" label="Pincode" labelposition="top"
											tabindex="28" cssClass="numeric text small" maxlength="6"></sj:textfield>
									</div>
								</div>
								<div class="grid_12">
									<div class="grid_6 alpha omega">
										<sj:textfield name="onlineApplicationDetails.phoneNumber"
											id="phoneNumber" label="Phone Number" tabindex="29"
											cssClass="numeric text small" maxlength="14"></sj:textfield>
									</div>
									<div class="grid_6 alpha omega">
										<sj:textfield name="onlineApplicationDetails.mobileNumber"
											id="mobileNumber" required="true" label="Mobile Number"
											cssClass="numeric text required small" maxlength="14"
											tabindex="30" onkeypress="return formatMobileNumber(this);"
											onblur="return validateMobNumber(this.value)"></sj:textfield>
									</div>
								</div>
								<div class="grid_12">
									&nbsp;
								</div>
								
								<div class="grid_12">
									<h1>
										CORRESPONDENCE ADDRESS
									</h1>
								</div>
								<div class="grid_12">
									<div class="grid_12">
										<div class="grid_6 alpha omega">
											<sj:textfield name="address.streetName" tabindex="31"
												id="streetName" cssClass="text small" label="Street Name"
												labelposition="top" maxlength="200"></sj:textfield>
										</div>
										<div class="grid_6 alpha omega">
											<sj:textfield name="address.city" id="city" tabindex="32"
												label="City" labelposition="top" cssClass="text small"
												maxlength="60"></sj:textfield>
										</div>
									</div>
									<div class="grid_12">
										<div class=" grid_6 alpha omega">
											<s:select id="state" list="statesList" label="State"
												tabindex="33" cssClass="" listKey="stateCode"
												listValue="stateName" headerKey="" headerValue="- Select -"
												name="address.state" />
										</div>
										<div class="grid_6 alpha omega">
											<sj:textfield name="address.postalCode" id="pincode"
												label="Pincode" labelposition="top" tabindex="34"
												cssClass="numeric text small" maxlength="6"></sj:textfield>
										</div>
									</div>
								</div>
								
								
								
								
								
								
								<div class="grid_12">
									&nbsp;
								</div>
								<div class="clear"></div>
								<div class="grid_12">
									<sj:submit   cssClass="submit small" value="Submit" 
										indicator="indicator" validate="true" tabindex="35"
										formIds="addStudentOnlineInfo"
										targets="onlineAppCont"
										onClickTopics="onlineApplFormVald" />
								</div>
							</fieldset>
						</s:form>
						</fieldset>
					</div>
					</div>
				</div>
					</div>
			</div>
		</div>
	</div>
<script type="text/javascript">
$(document).ready(function() {
	$('.numeric').numeric();
	
	$('#applicationClass').change(function() {
		var className = $('#applicationClass option:selected').text();
		if(className == '- Select -')
			$('#applClassName').val('');
		else
			$('#applClassName').val(className);
		});
	
	$.subscribe('onlineApplFormVald', function(event, data) {
	if ($('#addStudentOnlineInfo').valid()) {
		var mobnum = document.getElementById("mobileNumber").value;
		var gender = "";
		$("#gender").children().each(function() {
			if ($(this).attr("aria-pressed") == "true")
				gender = $(this).children().html();
		});

		if (gender == "") {
			alert("!Oops enter gender.");
			return false;
		}
		if (mobnum == 0) {
			alert("Please select mobile number.");
			return false;
		} else {
			return validateMobNumber(mobnum);
			return true;
		}
	}
});
});
function getSubCastDetailsByCast(selectBox) {
	var castId = selectBox.value;
	var url = jQuery.url
			.getChatURL("/admin/ajaxOnlineAdmisisonsSubCastDetailsByCastDetails.do");
	if (castId.length == 0) {
		alert("!Oops select Cast.");
	} else {
		$("#resultsDiv2")
				.html(
						'<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		var pars = "person.castId=" + castId;
		$.ajax( {
			url : url,
			cache : false,
			data : pars,
			success : function(html) {
				$("#resultsDiv2").html(html);
				document.getElementById('resultsDiv2').style.display = "block";
				$('select#subCastName').attr("tabindex", 13);
				//document.getElementById('schoolBooksList').style.display="none";
		}
		});
	}
}

</script>