<%@ include file="/common/taglibs.jsp"%>
<div>
	<s:form action="ajaxEditStudent" theme="css_xhtml" id="addEditStudent"
		method="post" enctype="multipart/form-data" namespace="/student">
		<s:hidden name="student.id" />

		<div class="grid_4">
			<img
				src='<c:url value="${newUser.profileImage.adjustedAttachmentFilePath}"/>'
				alt='<s:property  value="viewStaffPersonAccountDetails.personFullName" />'
				height="150px" width="150px" border="0" "/>
		</div>
		<div class="grid_6">
			<div class="grid_2" style="text-align: left;">
				<b>First Name</b>
			</div>
			<div class="grid_4">
				<sj:textfield name="person.firstName" id="driverfName"
					required="true" cssClass="alphabet required textfield"
					maxlength="20"></sj:textfield>
			</div>
		</div>

		<div class="grid_6">
			<div class="grid_2" style="text-align: left;">
				<b>Last Name</b>
			</div>
			<div class="grid_4">
				<sj:textfield name="person.lastName" id="driverlName"
					required="true" cssClass="alphabet required textfield"
					maxlength="20"></sj:textfield>
			</div>
		</div>
		<div class="grid_12">
			&nbsp;
		</div>
		<div class="grid_6">
			<div class="grid_2" style="text-align: left;">
				<b>Father Name</b>
			</div>
			<div class="grid_4">
				<sj:textfield name="person.fatherName" id="fatherName"
					required="true" cssClass="alphabet required textfield"
					maxlength="20"></sj:textfield>
			</div>
		</div>
		<div class="grid_6">
			<div class="grid_2" style="text-align: left;">
				<b>Mother Name</b>
			</div>
			<div class="grid_4">
				<sj:textfield name="person.motherName" id="motherName"
					cssClass="alphabet textfield" maxlength="20"></sj:textfield>
			</div>
		</div>
		<div class="grid_12">
			&nbsp;
		</div>
		<div class="grid_12">
			<div class="grid_2" style="text-align: left;">
				<b>Date Of Birth</b>
			</div>
			<div class="grid_4">
				<sj:datepicker id="date0" name="person.dateOfBirth" readonly="true"
					cssClass="textfield required" required="true" changeMonth="true"
					changeYear="true" yearRange="1960" cssStyle="width:140px;" />
			</div>
			<div class="grid_2" style="text-align: left;">
				<b>Date Of Joining</b>
			</div>
			<div class="grid_4">
				<sj:datepicker id="studentJoiningDate" name="person.dateOfJoining"
					cssClass="textfield required" required="true" changeMonth="true"
					changeYear="true" yearRange="1960" cssStyle="width:140px;"
					readonly="true" />
			</div>
		</div>
		<div class="grid_12">
			&nbsp;
		</div>
		<div class="grid_12">
			<div class="grid_2" style="text-align: left;">
				<b>Role Number</b>
			</div>
			<div class="grid_4">
				<sj:textfield name="student.rollNumber" id="roleNumber"
					cssClass="required textfield" required="true" maxlength="20"
					readonly="true">
				</sj:textfield>
			</div>
			<div class="grid_2" style="text-align: left;">
				<b>Gender</b>
			</div>
			<div class="grid_4">
				<input type="radio" value="M" name="person.gender"
					style="vertical-align: top;" checked>
				Male
				<input type="radio" value="F" name="person.gender"
					style="vertical-align: top;">
				Female
			</div>
		</div>
		<div class="grid_12">
			&nbsp;
		</div>
		<div class="grid_12">
			<div class="grid_2" style="text-align: left;">
				<b>Select Class</b>
			</div>
			<div class="grid_4">
				<s:select list="studyClassList" listKey="id"
					listValue="classAndSection" cssClass="required" required="true"
					name="studyClassId" headerKey="" headerValue="- Select -"
					requiredposition="first" cssStyle="width :191px;">
				</s:select>
			</div>
				<s:if test='%{customer.transportModuleStatus==true}'>
			<div class="grid_2" style="text-align: left;">
				<b>Transport Mode:</b>
			</div>
			<div class="grid_4">
				<input type="radio" value="O" name="student.transportMode"
					style="vertical-align: top;" checked>
				Own
				<input type="radio" value="P" name="student.transportMode"
					style="vertical-align: top;">
				Private
				<input type="radio" value="T" name="student.transportMode"
					style="vertical-align: top;">
				School Transport
			</div>
		</s:if>

			
		</div>
		<div class="grid_12">
			&nbsp;
		</div>
		<!--
		<div class="tableactions" style="padding-bottom: 0px;">
			<s:select id="classId" list="classList" listKey="id"
				listValue="className" label="Class" cssClass="required"
				required="true" headerKey="" headerValue="- Select -"
				name="student.classId" />
		</div>
		<div class="tableactions" style="padding-bottom: 0px;">
			<s:select id="sectionId" list="sectionList" listKey="id"
				listValue="section" label="Section" cssClass="required"
				required="true" headerKey="" headerValue="- Select -"
				name="student.sectionId" />
		</div>

		-->
		<div class="grid_12">
			<div class="grid_2" style="text-align: left;">
				<b>Blood Group</b>
			</div>
			<div class="grid_4">
				<s:select id="bGroup" cssStyle="width :191px;"
					list="#{'A+':'A+ve','A-':'A-ve','A1+':'A1+ve','A1-':'A1-ve','A1B+':'A1B+ve','A1B-':'A1B-ve','A2B+':'A2B+ve','A2B-':'A2B-ve','B2+':'A2+ve','A2-':'A2-ve','B+':'B+ve','B-':'B-ve','AB+':'AB+ve','AB-':'AB-ve','O+':'O+ve','O-':'O-ve'}"
					cssClass="required" required="true" headerKey=""
					headerValue="- Select -" name="person.bloodGroup" />
			</div>
			<div class="grid_2" style="text-align: left;">
				<b>Parent EmailId</b>
			</div>
			<div class="grid_4">
				<sj:textfield name="person.parentEmail" id="emailAddress"
					cssClass="required email textfield" required="true" maxlength="40"></sj:textfield>
			</div>
		</div>
		<div class="grid_12">
			&nbsp;
		</div>
		<div class="grid_12">
			<div class="grid_2" style="text-align: left;">
				<b>Phone Number</b>
			</div>
			<div class="grid_4">
				<sj:textfield name="person.phoneNumber" id="phoneNumber"
					cssClass="numeric textfield" maxlength="13"></sj:textfield>
			</div>
			<div class="grid_2" style="text-align: left;">
				<b>Mobile Number</b>
			</div>
			<div class="grid_4">
				<sj:textfield name="person.mobileNumber" id="mobileNumber"
					required="true" cssClass="textfield required" maxlength="14"
					onkeypress="return formatPhoneNumber(this);"
					onblur="return validateMobNumber(this.value)"></sj:textfield>
			</div>
		</div>
		<div class="grid_12">
			&nbsp;
		</div>
		<!--
		<div class="grid_12">
			<div class="grid_2" style="text-align: left;">
				<b>AddressLine1</b>
			</div>
			<div class="grid_4">
				<sj:textfield name="address.addressLine1" id="addressLine1"
					cssClass="required textfield" required="true" maxlength="40"></sj:textfield>
			</div>
			<div class="grid_2" style="text-align: left;">
				<b>AddressLine2</b>
			</div>
			<div class="grid_4">
				<sj:textfield name="address.addressLine2" id="addressLine2"
					cssClass="textfield" maxlength="40"></sj:textfield>
			</div>
		</div>
		-->
		<div class="grid_12">
			&nbsp;
		</div>
		<div class="grid_12">
			<div class="grid_2" style="text-align: left;">
				<b>City</b>
			</div>
			<div class="grid_4">
				<sj:textfield name="address.city" id="city"
					cssClass="required textfield" maxlength="20" required="true"></sj:textfield>
			</div>
			<div class="grid_2" style="text-align: left;">
				<b>State</b>
			</div>
			<div class="grid_4">
				<s:select id="state" list="statesList" cssClass="required"
					required="true" listKey="stateCode" listValue="stateName"
					headerKey="" headerValue="- Select -" name="address.state" />
			</div>
		</div>
		<div class="grid_12">
			&nbsp;
		</div>
		<div class="grid_12">
			<div class="grid_2" style="text-align: left;">
				<b>Pincode</b>
			</div>
			<div class="grid_4">
				<sj:textfield name="address.postalCode" id="pincode" required="true"
					cssClass="numeric textfield required" maxlength="6"></sj:textfield>
			</div>
			<div class="grid_2" style="text-align: left;">
				<b>Admission Number</b>
			</div>
			<div class="grid_4">
				<sj:textfield name="newUser.admissionNumber" id="admission"
					cssClass="numeric textfield" cssStyle="backGround :#E5E181"
					maxlength="25" readonly="true"></sj:textfield>
			</div>
		</div>
		<div class="grid_12">
			&nbsp;
		</div>
		<div class="grid_6">
			<div class="grid_2" style="text-align: left;">
				<b>Upload Image:</b>
			</div>
			<div class="grid_4">
				<s:file name="upload" id="photoURL1"></s:file>
			</div>
		</div>
		<div class="grid_12">
			&nbsp;
		</div>
		<div class="grid_11">
			<div class="grid_2" style="text-align: left;">
				<b>Summary:</b>
			</div>
			<div class="grid_6">
				<sj:textarea rows="3" cols="20" name="person.summary"
					cssStyle="width:130%" cssClass="textfield  word_count"></sj:textarea>
				<div class="counter"></div>
			</div>
		</div>
		<div class="grid_6" style="float: left;">
			<div class="grid_4" style="float: right;">
				<s:url id="doCancelStudent" action="ajaxDocancelStudent"
					includeParams="all"></s:url>
				<sj:a href="%{doCancelStudent}" cssClass="cancelButton"
					indicator="indicator" targets="stepStudent" button="false">Cancel</sj:a>
				<sj:submit   cssClass="submit small" value="Submit"
					indicator="indicator" targets="studentsList"
					onClickTopics="studentEditFormValidation" formIds="addEditStudent" />
			</div>
		</div>
	</s:form>
</div>
<script language="JavaScript" type="text/javascript">
$(document).ready(function() {
	$.subscribe('studentEditFormValidation', function(event, data) {
		if ($('#addEditStudent').valid())
			var mobnum = document.getElementById("mobileNumber").value;
			if (mobnum == 0) {
				alert("Please enter mobile number.");
				return false;
			} else {
				return validateMobNumber(mobnum);
				return true;
			}
		else
			return false;
	});
	$('.numeric').numeric();
	$('.alphabet').alpha();
	$("#inputboxhideText").hide();
});
function formatPhoneNumber(object) {
       var phoneString = object.value;
       if (phoneString.length == 1) {
               phoneString = "+91-" + phoneString;
       }
       object.value = phoneString;
}
</script>
<script type="text/javascript">
$(document).ready(
		function() {
			var errMsg = $('.block .message');
			if (errMsg) {
				$('.block .message').hide().append(
						'<span class="close" title="Click to Close"></span>')
						.fadeIn('slow');
				$('.block .message .close').hover(function() {
					$(this).addClass('hover');
				}, function() {
					$(this).removeClass('hover');
				});

				$('.block .message .close').click(function() {
					$(this).parent().fadeOut('slow', function() {
						$(this).remove();
					});
				});
			}
		});
</script>