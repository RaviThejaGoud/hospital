<%@ include file="/common/taglibs.jsp"%>
<div class="grid_11">
	<s:form action="ajaxEditStaffDetails" theme="css_xhtml" id="editStaff"
		method="post" enctype="multipart/form-data">
		<s:hidden name="staff.id" />
		<div class="grid_9">
			<div class="grid_5">
				<label>
					First Name
				</label>
				<sj:textfield name="person.firstName" id="staffName" required="true"
					cssClass="alphabet required textfield" maxlength="20"></sj:textfield>
				<label>
					Last Name
				</label>
				<sj:textfield name="person.lastName" id="staffLName" required="true"
					cssClass="alphabet required textfield" maxlength="20"></sj:textfield>
			</div>
			<div class="grid_3">
				<img
					src='<c:url value="${viewStaffPersonAccountDetails.adjustedAttachmentFilePath}"/>'
					alt='<s:property  value="viewTeacherAccountDetails.personName" />'
					align="left" height="115px" width="160px" border="0" />
			</div>
		</div>
		<div class="grid_9">
			&nbsp;
		</div>
		<div class="grid_9">
			<div class="grid_5">
				<label>
					Employee Number
				</label>
			</div>
			<div class="grid_4">
				<label>
					Staff Type
				</label>
			</div>
			<div class="grid_5">
				<sj:textfield name="newUser.username" id="roleNumber"
					cssClass="required textfield" required="true" maxlength="8"
					disabled="true"></sj:textfield>
			</div>
			<div class="grid_4">
				<s:select id="bGroup" cssClass="textfield required" required="true"
					headerKey="" headerValue="- Select -" name="staff.staffType"
					list="#{'P':'Permanent','C':'Temporary'}" />
			</div>
		</div>
		<div class="grid_9">
			&nbsp;
		</div>
		<div class="grid_9">
			<div class="grid_5">
				<label>
					Date Of Birth
				</label>
			</div>
			<div class="grid_4">
				<label>
					Date Of Joining
				</label>
			</div>
			<div class="grid_5">
				<sj:datepicker id="staffDate" name="person.dateOfBirth"
					readonly="true" cssClass="textfield required" required="true"
					changeMonth="true" changeYear="true" yearRange="1960" />
			</div>
			<div class="grid_4">
				<sj:datepicker id="staffJoiningDate" name="person.dateOfJoining"
					cssClass="textfield required" required="true" changeMonth="true"
					changeYear="true" yearRange="1960" readonly="true" />
			</div>
		</div>
		<div class="grid_10">
			&nbsp;
		</div>
		<div class="grid_10">
			<div class="grid_5">
				<label>
					Gender
				</label>
			</div>
			<div class="grid_4">
				<label>
					Marital Status
				</label>
			</div>
			<div class="grid_5">
				<input type="radio" value="M" name="person.gender" checked>
				Male
				<input type="radio" value="F" name="person.gender">
				Female
			</div>
			<div class="grid_4">
				<input type="radio" value="M" name="person.maritalStatus" checked>
				Married
				<input type="radio" value="UN" name="person.maritalStatus">
				UnMarried
			</div>
		</div>
		<div class="grid_11">
			&nbsp;
		</div>
		<div class="grid_11">
			<div class="grid_5">
				<label>
					Qualification
				</label>
			</div>
			<div class="grid_5">
				<label>
					Experience(in Months)
				</label>
			</div>
			<div class="grid_5">
				<sj:textfield name="staff.qualification1" id="qualification"
					required="true" cssClass="required textfield" maxlength="20"></sj:textfield>
			</div>
			<div class="grid_5">
				<sj:textfield name="person.experience" id="experience"
					required="true" cssClass="numericDot required textfield" maxlength="4"></sj:textfield>
			</div>
		</div>
		<!-- <div class="grid_12">&nbsp;</div>
		<div class="grid_12">
			<div class="grid_3" >
				<label>PG</label>
			</div>
			<div class="grid_4">
				<sj:textfield name="staff.qualification2" id="pg"
					cssClass="textfield" maxlength="20"></sj:textfield>
			</div> -->
		<div class="grid_11">
			&nbsp;
		</div>
		<div class="grid_11">
			<div class="grid_5">
				<label>
					Blood Group
				</label>
			</div>
			<div class="grid_5">
				<label>
					Email Id
				</label>
			</div>
			<div class="grid_5">
				<s:select id="bGroup"
					list="#{'A+':'A+ve','A-':'A-ve','A1+':'A1+ve','A1-':'A1-ve','A1B+':'A1B+ve','A1B-':'A1B-ve','A2B+':'A2B+ve','A2B-':'A2B-ve','A2+':'A2+ve','A2-':'A2-ve','B+':'B+ve','B-':'B-ve','AB+':'AB+ve','AB-':'AB-ve','O+':'O+ve','O-':'O-ve'}"
					cssClass="required textfield" required="true" headerKey=""
					headerValue="- Select -" name="person.bloodGroup" />
			</div>
			<div class="grid_5">
				<sj:textfield name="address.email" id="emailAddress"
					cssClass="required email textfield" required="true" maxlength="40"></sj:textfield>
			</div>
		</div>
		<div class="grid_11">
			&nbsp;
		</div>
		<div class="grid_11">
			<div class="grid_5">
				<label>
					Phone Number
				</label>
			</div>
			<div class="grid_5">
				<label>
					Mobile Number
				</label>
			</div>
			<div class="grid_5">
				<sj:textfield name="person.phoneNumber" id="phoneNumber"
					cssClass="numeric textfield" maxlength="13"></sj:textfield>
			</div>
			<div class="grid_5">
				<sj:textfield name="person.mobileNumber" id="mobileNumber"
					cssClass="textfield required" required="true" maxlength="14"
					onkeypress="return formatPhoneNumber(this,event);"
					onblur="return validateMobNumber(this.value)"></sj:textfield>
			</div>
		</div>
		<div class="grid_11">
			&nbsp;
		</div>
		<!--<div class="grid_12">
			<div class="grid_5" >
				<label>AddressLine1</label>
			</div>
			<div class="grid_5" >
				<label>AddressLine2</label>
			</div>
			<div class="grid_5">
				<sj:textfield name="address.addressLine1" id="addressLine1"
					cssClass="required textfield" required="true" maxlength="40"></sj:textfield>
			</div>
			<div class="grid_5">
				<sj:textfield name="address.addressLine2" id="addressLine2"
					cssClass="textfield" maxlength="40"></sj:textfield>
			</div>
		</div> -->
		<div class="grid_11">
			&nbsp;
		</div>
		<div class="grid_11">
			<div class="grid_5">
				<label>
					City
				</label>
			</div>
			<div class="grid_5">
				<label>
					State
				</label>
			</div>
			<div class="grid_5">
				<sj:textfield name="address.city" id="city"
					cssClass="required textfield" maxlength="20" required="true"></sj:textfield>
			</div>
			<div class="grid_5">
				<s:select id="state" list="statesList" cssClass="required textfield"
					required="true" listKey="stateCode" listValue="stateName"
					headerKey="" headerValue="- Select -" name="address.state" />
			</div>
		</div>
		<div class="grid_11">
			&nbsp;
		</div>
		<div class="grid_11">
			<div class="grid_5">
				<label>
					Pincode
				</label>
			</div>
			<div class="grid_5">
				<label>
					Upload Image:
				</label>
			</div>
			<div class="grid_5">
				<sj:textfield name="address.postalCode" id="pincode" required="true"
					cssClass="numeric textfield required" maxlength="6"></sj:textfield>
			</div>
			<div class="grid_5 ">
				<s:file name="upload" id="photoURL1"></s:file>
			</div>

		</div>
		<div class="grid_11">
			&nbsp;
		</div>
		<s:if test="%{selectboxMap != null && !selectboxMap.isEmpty()}">
			<div class="grid_11">
		</s:if>
		<s:else>
			<div class="grid_5">
		</s:else>
		<%-- <div class="grid_5">
			<s:if test="%{selectboxMap != null && !selectboxMap.isEmpty()}">
				<label>
					Reports To
				</label>
			</s:if>
		</div> --%>
		<div class="grid_5">
			<label>
				Summary:
			</label>
		</div>
		<div class="grid_5">
			<s:if test="%{selectboxMap != null && !selectboxMap.isEmpty()}">
				<!--<label class="label" for="pincode">
						<span class="required">*</span>Select Supervisor (
						<s:property value="emailBody" />
						) :
					</label>
					-->
				<!--<div class="grid_3" >
							<label>Reports To </label>
						</div>
						-->
				<s:select id="teachStaffList" list="selectboxMap" theme="simple"
					headerKey="" headerValue="- Select -" cssClass="textfield required"
					required="true" name="staff.supervisorId" />
			</s:if>
		</div>
		<div class="grid_5">
			<sj:textarea rows="3" cols="20" name="person.summary"
				cssClass="text small  word_count"></sj:textarea>
			<div class="counter"></div>
		</div>
</div>
<div class="grid_11">
	&nbsp;
</div>

<s:if test="%{!studySubjectList != null && !studySubjectList.isEmpty()}">
	<div class="grid_11">
		<div class="grid_3">
			<label>
				Eligible Subjects:
			</label>
		</div>
		<div class="grid_11">
			<s:checkboxlist name="chkBoxSelectedIds"
				cssClass="checkbox small required" required="true"
				list="studySubjectList" listKey="id" listValue="name" />
		</div>
	</div>
</s:if>

<div id="staffTypeDiv"></div>
<div class="grid_11">
	&nbsp;
</div>
<div class="grid_4">
	<sj:submit   cssClass="submit small" value="Submit" targets="staffList"
		onClickTopics="staffEditFormValidation" formIds="editStaff" />
	<s:url id="doCancelStaff" action="ajaxDocancelStaff"
		includeParams="all" namespace="/admin"></s:url>
	<sj:a href="%{doCancelStaff}" cssClass="cancelButton"
		onCompleteTopics="doInitEditStaff" indicator="indicator"
		targets="editStaffForm%{staff.account.id}" button="false">Cancel</sj:a>
</div>
</s:form>
</div>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/common/jquery.alphanumeric.js">
</script>
<script language="JavaScript" type="text/javascript">
changePageTitle("Edit Staff Details");
$(document).ready(function() {
	$('.numericDot').numeric( {allow : "."});
		$.subscribe('staffEditFormValidation', function(event, data) {
			if ($('#editStaff').valid())
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
		$('.alphabet').alpha ();
	});
	function formatPhoneNumber(object,e) {
			var keynum;
			var keychar;
			var numcheck;
			if(window.event) // IE
			{
		  		keynum = e.keyCode;
			}
			else if(e.which) // Netscape/Firefox/Opera
			{
				keynum = e.which;
			}
			if(keynum == 8)
			{
			  	return true;
			}
			else if (keynum < 47 || keynum > 57) {
				return false;
			}
			var phoneString = object.value;
			if (phoneString.length == 1) {
				phoneString = "+91-" + phoneString;
			}
			object.value = phoneString;
		}
</script>