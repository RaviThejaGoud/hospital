<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/plugins/bootstrap-switch/static/js/bootstrap-switch.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/ems.js"> </script>
<s:form action="ajaxMasterSaveNewUrlCustomerDetails" id="signupForm" method="post" theme="simple" namespace="/signup" cssClass="form-horizontal">
	<%@ include file="/common/messages.jsp"%>
	<s:hidden name="packageId" value="%{customerEnrollmentRequest.packageDetails.id}"></s:hidden>
	<s:hidden name="anyId" cssClass="anyId"></s:hidden>
	<s:hidden name="anyTitle" cssClass="anyTitle"></s:hidden>
	<s:hidden name="tempString2" cssClass="tempString2"></s:hidden>
	<s:hidden name="customerEnrollmentRequest.id" value="%{customerEnrollmentRequest.id}"></s:hidden>
	<h4 class="pageTitle bold form-section">
		Personal Details
	</h4>
	<div class="row">
		<div class="col-md-6">
			<div class="form-group">
				<label class="control-label col-md-5">
					<span class="required">*</span>First Name :
				</label>
				<div class="col-md-7">
					<sj:textfield name="customer.firstName" id="firstName" value="%{customerEnrollmentRequest.firstName}"
						cssClass="required form-control input-medium as-input" size="50"
						maxlength="50"></sj:textfield>
				</div>
			</div>
		</div>
		<div class="col-md-6">
			<div class="form-group">
				<label class="control-label col-md-5">
					<span class="required">*</span>Last Name :
				</label>
				<div class="col-md-7">
					<sj:textfield name="customer.lastName" id="lastName" value="%{customerEnrollmentRequest.lastName}"
						cssClass="required form-control input-medium as-input"
						maxlength="50"></sj:textfield>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-md-6">
			<div class="form-group">
				<label class="control-label col-md-5">
					<span class="required">*</span>Customer Short Name :
				</label>
				<div class="col-md-7">
					<sj:textfield name="customer.customerShortName" id="sender" value="%{customerEnrollmentRequest.customerShortName}"
						cssClass="required form-control input-medium as-input custShortNumStr"
						maxlength="10"></sj:textfield>
				</div>
			</div>
		</div>
		<div class="col-md-6">
			<div class="form-group">
				<label class="control-label col-md-5">
					<span class="required">*</span>Street :
				</label>
				<div class="col-md-7">
					<sj:textfield name="customer.address.streetName" id="custStreet" value="%{customerEnrollmentRequest.address.streetName}"
						cssClass="required form-control input-medium as-input" size="130"
						maxlength="60"></sj:textfield>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-md-6">
			<div class="form-group">
				<label class="control-label col-md-5">
					<span class="required">*</span>City :
				</label>
				<div class="col-md-7">
					<sj:textfield name="customer.address.city" id="custCity" value="%{customerEnrollmentRequest.address.city}"
						cssClass="required form-control input-medium as-input"
						maxlength="30"></sj:textfield>
				</div>
			</div>
		</div>
		<div class="col-md-6">
			<div class="form-group">
				<label class="control-label col-md-5">
					<span class="required">*</span>State :
				</label>
				<div class="col-md-7">
					<s:select id="custState" list="statesList" value="%{customerEnrollmentRequest.address.state}"
						cssClass="required form-control input-medium as-input"
						listKey="stateCode" listValue="stateName"
						headerKey="" headerValue="- Select -"
						name="customer.address.state" theme="simple" />
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-md-6">
			<div class="form-group">
				<label class="control-label col-md-5">
					<span class="required">*</span>Pincode :
				</label>
				<div class="col-md-7">
					<sj:textfield name="customer.address.postalCode" size="6" value="%{customerEnrollmentRequest.address.postalCode}"
						maxlength="6" id="custZipcode"  
						cssClass="numeric required form-control input-medium as-input" />
				</div>
			</div>
		</div>
		<%-- <div class="col-md-6">
			<div class="form-group">
				<label class="control-label col-md-5">
					<span class="required">*</span>Modify Invoice Password :
				</label>
				<div class="col-md-7">
					<sj:textfield name="customer.modifyInvoicePassword" size="20"
						maxlength="20" id="mip"  
						cssClass="required form-control input-medium as-input" />
				</div>
			</div>
		</div> --%>
	</div>
	<%-- <div class="row">
		<div class="col-md-6">
			<div class="form-group">
				<label class="control-label col-md-5">
					<span class="required">*</span>Delete Invoice Password :
				</label>
				<div class="col-md-7">
					<sj:textfield name="customer.deleteInvoicePassword" size="18"
						maxlength="20" id="dip" 
						cssClass="required form-control input-medium as-input" />
				</div>
			</div>
		</div>
	</div> --%>
	<h4 class="pageTitle bold form-section">
		Organization Address
	</h4>
	<div class="row">
	
		<div class="col-md-6">
			<div class="form-group">
				<label class="control-label col-md-5">
					<span class="required">*</span>Organization Name :
				</label>
				<div class="col-md-7">
					<s:if test="%{objectList != null && !objectList.isEmpty()}">
						<s:select list="objectList" listKey="organizationName"
							listValue="organizationName" id="category" theme="simple"
							cssClass="form-control input-medium" name="organization"
							onchange="$('input#organizationName').val($(this).find('option:selected').text());"
							headerKey="" headerValue="" cssStyle="padding: 3px 10px;"></s:select>
						<sj:textfield id="organizationName"
							name="customer.organization"
							cssClass="form-control promoteClass required" value="%{customerEnrollmentRequest.organization}"
							cssStyle="float: left; margin-left: 1px; margin-top: -31px; width: 207px;background-color : #FFF;"></sj:textfield>
					</s:if>
					<s:else>
						<sj:textfield id="organizationName" name="customer.organization" cssClass="form-control input-medium promoteClass" value="%{customerEnrollmentRequest.organization}"></sj:textfield>
					</s:else>
				</div>
			</div>
		</div>
			
		<div class="col-md-6">
			<div class="form-group">
				<label class="control-label col-md-5">
					<span class="required">*</span>Organization Type :
				</label>
				<div class="col-md-7">
					<s:select id="organizationType" list="organizationTypesList" value="%{customerEnrollmentRequest.orgnizationTypeId}"
						cssClass="required form-control input-medium as-input"
						 listKey="id" listValue="organizationType"
						headerKey="" headerValue="- Select -" name="OrganizationTypes.id"
						onchange="javascript:getOrganizationSubTypes(this);" />
				</div>
			</div>
		</div>
	</div>
	<div class="row" id="govtState" style="display: none">
		<div class="col-md-6" id="resultsDiv2">
			<div class="form-group">
				<jsp:include
					page="/WEB-INF/pages/masterAdmin/ajaxGetOrganizationalSubTypes.jsp" /></div>
		</div>
		<s:if test='%{user.isDEO == "Y" || user.isBEO == "Y" || user.isSEO == "Y" || user.isCEO == "Y"}'>
			<div class="col-md-6" >
				<div class="form-group">
					<label class="control-label col-md-5">
						<span class="required">*</span>State :
					</label>
					<div class="col-md-7">
						<s:select id="state" list="statesList" disabled="true" theme="simple"  value="%{customerEnrollmentRequest.organizationAddress.stateId}"
							cssClass="required form-control input-medium as-input"
							listKey="id" listValue="stateName" headerKey=""
							headerValue="- Select -" name="state.id" />
							<s:hidden name="state.id"></s:hidden>
					</div>
				</div>
			</div>
		</s:if>
		<s:else>
		<div class="col-md-6" >
				<div class="form-group">
					<label class="control-label col-md-5">
						<span class="required">*</span>State :
					</label>
					<div class="col-md-7">
						<s:select id="state" list="statesList" value="%{customerEnrollmentRequest.organizationAddress.stateId}"
							cssClass="required form-control input-medium as-input" theme="simple" 
							listKey="id" listValue="stateName" headerKey=""
							headerValue="- Select -" name="state.id" />
					</div>
				</div>
			</div>
		</s:else>
		<%-- <s:if test="%{tempList != null && !tempList.isEmpty()}">
			<div class="col-md-6" >
				<div class="form-group">
					<!--<jsp:include page="/WEB-INF/pages/masterAdmin/ajaxGetDistricts.jsp" /> -->
					<label class="control-label col-md-5">
						<span class="required">*</span>Select District:
					</label>
					<div class="col-md-7">
						<s:select id="districtId" list="tempList"
							cssClass="required form-control input-medium as-input"
							listKey="id" listValue="districtName" headerKey=""
							headerValue="- Select -" name="district.id" 
							onchange="javascript:getMandals(this);" />
					</div>
				</div>
			</div>
		</s:if> --%>
	</div>
	<div class="row" id="pvrState"  style="display: none">
		<s:if test='%{user.isDEO == "Y" || user.isBEO == "Y" || user.isSEO == "Y" || user.isCEO == "Y"}'>
			<div class="col-md-6" >
				<div class="form-group">
					<label class="control-label col-md-5">
						<span class="required">*</span>State :
					</label>
					<div class="col-md-7">
						<s:select id="govtState1" list="statesList" disabled="true" theme="simple" 
							cssClass="required form-control input-medium as-input"
							listKey="id" listValue="stateName" headerKey=""
							headerValue="- Select -" name="stateId" value="%{customerEnrollmentRequest.organizationAddress.stateId}"
							onchange="javascript:getDistricts(this);" />
							<s:hidden name="stateId"></s:hidden>
					</div>
				</div>
			</div>
		</s:if>
		<s:else>
			<div class="col-md-6" >
				<div class="form-group">
					<label class="control-label col-md-5">
						<span class="required">*</span>State :
					</label>
					<div class="col-md-7">
						<s:select id="govtState1" list="statesList" value="%{customerEnrollmentRequest.organizationAddress.stateId}"
							cssClass="required form-control input-medium as-input" 
							listKey="id" listValue="stateName" headerKey=""
							headerValue="- Select -" name="stateId" theme="simple" 
							onchange="javascript:getDistricts(this);" />
					</div>
				</div>
			</div>
		</s:else>
		<s:if test="%{tempList != null && !tempList.isEmpty()}">
			<div class="col-md-6" >
				<div class="form-group">
					<!--<jsp:include page="/WEB-INF/pages/masterAdmin/ajaxGetDistricts.jsp" /> -->
					<label class="control-label col-md-5">
						<span class="required">*</span>Select District:
					</label>
					<div class="col-md-7">
						<s:select id="districtId" list="tempList"
							cssClass="required form-control input-medium as-input"
							listKey="id" listValue="districtName" headerKey=""
							headerValue="- Select -" name="district.id" theme="simple"
							onchange="javascript:getMandals(this);" />
					</div>
				</div>
			</div>
		</s:if>
	</div>
	<div class="row">
		<div class="col-md-6">
			<div class="form-group">
				<label class="control-label col-md-5">
					<span class="required">*</span> AddressLine1 :
				</label>
				<div class="col-md-7">
					<sj:textfield id="addressLine1" name="address.addressLine1" value="%{customerEnrollmentRequest.organizationAddress.addressLine1}"
						size="130" maxlength="60"  
						cssClass="required form-control input-medium as-input" />
				</div>
			</div>
		</div>
		<div class="col-md-6">
			<div class="form-group">
				<label class="control-label col-md-5">
					<span class="required">*</span>Street :
				</label>
				<div class="col-md-7">
					<sj:textfield id="street" name="address.streetName" size="130" value="%{customerEnrollmentRequest.organizationAddress.streetName}"
						maxlength="60" 
						cssClass="required form-control input-medium as-input" />
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-md-6">
			<div class="form-group">
				<label class="control-label col-md-5">
					<span class="required">*</span> City :
				</label>
				<div class="col-md-7">
					<sj:textfield id="city" name="address.city" size="130" value="%{customerEnrollmentRequest.organizationAddress.city}"
						maxlength="30" 
						cssClass="required form-control input-medium as-input" />
				</div>
			</div>
		</div>
		<div class="col-md-6">
			<div class="form-group">
				<label class="control-label col-md-5">
					<span class="required">*</span>Pincode :
				</label>
				<div class="col-md-7">
					<sj:textfield id="zipcode" name="address.postalCode" size="6" value="%{customerEnrollmentRequest.organizationAddress.postalCode}"
						maxlength="6" 
						cssClass="numeric required form-control input-medium as-input" />
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-md-6">
			<div class="form-group">
				<label class="control-label col-md-5">
					<span class="required">*</span> Phone Number :
				</label>
				<div class="col-md-7">
					<sj:textfield id="phoneNumber" name="customer.contactNumber" value="%{customerEnrollmentRequest.contactNumber}"
						size="40" maxlength="12" 
						cssClass="numeric required form-control input-medium as-input" />
				</div>
			</div>
		</div>
		<div class="col-md-6">
			<div class="form-group">
				<label class="control-label col-md-5">
					<span class="required">*</span>Mobile Number :
				</label>
				<div class="col-md-7">
					<sj:textfield id="mobileNumber" name="customer.mobileNumber" value="%{customerEnrollmentRequest.mobileNumber}"
						maxlength="14" onkeypress="return formatPhoneNumber(this,event);"
						cssClass="numeric required form-control input-medium as-input" />
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="form-group">
			<label class="control-label col-md-2">
				Vision :
			</label>
			<div class="col-md-9">
				<sj:textarea name="customer.customerVision" id="maxlength_textarea" value="%{customerEnrollmentRequest.customerVision}" cssClass="form-control" placeholder="This textarea has a limit of 2000 chars."   rows="4" cols="20" maxlength="2000"></sj:textarea>
				<span class="help-block"> Maxlength is 2000 chars. </span>
				<!--<sj:textarea name="customer.customerVision" id="vision"
					cssClass="word_count form-control" rows="4" cols="20"
					maxCharsData="2000"></sj:textarea>
				<span class="counter"></span>
			--></div>
		</div>
	</div>
	<div class="row">
		<div class="form-group">
			<label class="control-label col-md-2">
				Mission :
			</label>
			<div class="col-md-9">
				<sj:textarea name="customer.customerMission" id="maxlength_textarea1" value="%{customerEnrollmentRequest.customerMission}" cssClass="form-control" placeholder="This textarea has a limit of 2000 chars."   rows="4" cols="20" maxlength="2000"></sj:textarea>
				<span class="help-block"> Maxlength is 2000 chars. </span>
				<!--<s:textarea name="customer.customerMission" id="mission"
					cssClass="word_counter form-control" rows="4" cols="20"
					maxCharsData="2000"></s:textarea>
				<span class="counter1"></span>
			--></div>
		</div>
	</div>
	<div class="row">
		<div class="col-md-6">
			<div class="form-group">
				<label class="control-label col-md-4">
					<span class="required">*</span>Your Organization Is :
				</label>
				<div class="col-md-5">
				<div class="radio-list">
					<label class="radio-inline"> <input type="radio" id="organizationLevel" checked="checked" 
						value="S" name="customer.organizationLevel">School
					</label>
					<label class="radio-inline"> <input type="radio"
						value="C" name="customer.organizationLevel">College
					</label>
				</div>
				<!-- <div class="make-switch has-switch" data-id="S" data-value="C" style="width:120px" data-off="warning" data-on="success" data-off-label="College" data-on-label="School">
					<input type="radio" class="toggle" checked="checked" id="organizationLevel" tabindex="7">
					<input type="hidden" name="customer.organizationLevel" value="S">
				</div> -->
				</div>
			</div>
		</div>
		<div class="col-md-6">
			<div class="form-group">
				<label class="control-label col-md-5">
					<span class="required">*</span>Free SMS :
				</label>
				<div class="col-md-5">
					<sj:textfield id="allowedsms" name="academicYear.allotedsms"
						maxlength="6" cssClass="numeric required form-control input-medium as-input" />
				</div>
			</div>
		</div>
	</div>	
	<div class="row">
		<div class="col-md-6">
			<div class="form-group">
				<label class="control-label col-md-4">
					<span class="required">*</span>SMSServiceProviders :
				</label>
				<div class="col-md-7">
					<s:select id="custState" list="smsServiceProvidersList"
						cssClass="required form-control input-medium as-input"
						listKey="id" listValue="serviceProvider"
						headerKey="" headerValue="- Select -"
						name="customer.smsServiceProviderId" theme="simple" />
				</div>
			</div>
		</div>
		<div class="col-md-6">
			<div class="row form-group">
					<label class="col-md-5 control-label">
						Fee Receipt Model : </label>
					<div class="col-md-7">
						<div class="radio-list">
							<label class="radio-inline"> <input type="radio" checked="checked"
								value="General" name="customer.feeReceiptModel">General
							</label>
							<label class="radio-inline"> <input type="radio"
								value="A3" name="customer.feeReceiptModel">A3
							</label>
							<label class="radio-inline"> <input type="radio"
								value="A4" name="customer.feeReceiptModel">A4
							</label>
							<label class="radio-inline"> <input type="radio"
								value="A5" name="customer.feeReceiptModel">A5
							</label>
							<label class="radio-inline"> <input type="radio"
								value="A6" name="customer.feeReceiptModel">A6
							</label>
						</div>
					</div>
				</div>
			</div>
		</div>
	<h4 class="pageTitle bold form-section">
		Login Credentials
	</h4>
	<div class="row">
		<div class="col-md-6">
			<div class="form-group">
				<label class="control-label col-md-5">
					<span class="required">*</span>Email :
				</label>
				<div class="col-md-7">
					<sj:textfield id="email" size="30" maxlength="60"
						  name="customer.custEmail"  value="%{customerEnrollmentRequest.custEmail}"
						cssClass="required form-control input-medium as-input email" disabled="true"/>
				</div>
			</div>
		</div>
		<s:hidden name="customer.custEmail" value="%{customerEnrollmentRequest.custEmail}"></s:hidden>
		<s:hidden name="customer.password" value="%{customerEnrollmentRequest.password}"></s:hidden>
		<%-- <div class="col-md-6">
			<div class="form-group">
				<label class="control-label col-md-5">
					<span class="required">*</span>Password : 
				</label>
				<div class="col-md-7">
					<s:password id="password" name='customer.password' value='<s:property value="customerEnrollmentRequest.password"/>'
						cssClass="required form-control input-medium as-input"
						 cssStyle="width:70%"  />
					<div id="passwordStrength" class="strength0" style="display: none;"></div>
				</div>
			</div>
		</div> --%>
		
	</div>
	<div class="row">
		<div class="col-md-12">
			<div class="form-group">
				<label class="control-label col-md-2">
					<s:if test='%{customerEnrollmentRequest.hostelModuleStatus}'>
						<input type="checkbox" name="isHostel" id="yesCheck" class="checkbox" checked="checked" > 
					</s:if>
					<s:else> 
						<input type="checkbox" name="isHostel" id="yesCheck" class="checkbox">
					</s:else>
				</label>
				HMS
				<span class="hintMessage">(If
					you want to enable this type please check this check box.)</span>
			</div>
			<div class="form-group">
				<label class="control-label col-md-2">
					<s:if test='%{customerEnrollmentRequest.transportModuleStatus}'>
						<input type="checkbox" name="isTransport" id="yesCheck" checked="checked" class="checkbox">
					</s:if>
					<s:else>
						<input type="checkbox" name="isTransport" id="yesCheck" class="checkbox">
					</s:else>
				</label>
				TMS
				<span class="hintMessage">(If
					you want to enable this type please check this check box.)</span>
			</div>
		</div>
	</div>
	<jsp:include page="/WEB-INF/pages/masterAdmin/ajaxGetGeneralSubjectTypes.jsp" />
	<div id="displaySubjectTypes"></div>
	<div class="form-body">
		<div class="form-actions fluid">
			<div class="col-md-offset-2 col-md-6">
				<sj:submit targets="newCustomerDetailsDiv" value="Accept" formIds="signupForm"
					cssClass="btn blue"  validate="true" indicator="indicator" onclick="acceptFun();"
					onBeforeTopics="doCustomRegistartion" />
					
				<sj:submit targets="newCustomerDetailsDiv" value="Reject" formIds="signupForm"
					cssClass="btn blue"  validate="true" indicator="indicator" onclick="rejectFun();"
					onBeforeTopics="doCustomRegistartion" />
					
				<s:url id="doCancel" action="ajaxManageCustomerRegistrationDetails"
					includeParams="all" namespace="/masterAdmin"></s:url>
				<sj:a href="%{doCancel}" cssClass="btn default"
					 targets="mainContentDiv" button="false">Cancel</sj:a>
			</div>
		</div>
	</div>
</s:form>
<script type="text/javascript">
$(document).ready(function() {
FormComponents.init();
FormAdvanced.init();
$.destroyTopic('doCustomRegistartion');
$("input:checkbox, input:radio:not('.toggle')").uniform();  
	$("div#showHideGovtCustomers").hide();
	var validator;
	jQuery.validator.addMethod("password",function(value, element) {
		var result = this.optional(element)|| value.length >= 6 && /\d/.test(value)&& /[a-z]/i.test(value);
		if (!result) {
			var validator = this;
		}
		return result;
	},
	"Your password must be at least 6 characters long and contain at least one number and one character.");
	$.subscribe('doCustomRegistartion', function(event, data) {
		if($("input[name=chkBoxSelectedIds]:unchecked").length==$("input[name=chkBoxSelectedIds]").length){
			alert("Please select at least one syllabus type.");
			event.originalEvent.options.submit=false;
		}else{
			if ($('#' + data.id).is(":hidden")) {
				$('#' + data.id).show();
			} else {
				$('#' + data.id).show();
			}
			/* if ($("li#govtCustomersList").hasClass("selected")) {
				$("li#govtCustomersList a").click();
				$("li#govtCustomersList").removeClass("selected");
			} */
		}
		 var custShortNum=$('input.custShortNumStr').parents('div').next('div').find('p.word-taken').html();
	 		if(custShortNum=='Already taken!!!'){
	     	    event.originalEvent.options.submit=false;
	     	    $('input.custShortNumStr').val('');
	          }
	 		 var custShortNum=$('input.email').parents('div').next('div').find('p.word-taken').html();
		 		if(custShortNum=='Already taken!!!'){
		     	    event.originalEvent.options.submit=false;
		     	    $('input.email').val('');
		          }
	});
	document.title = 'Eazy School SIGN UP ';
	$("input#email").autoCheck("${pageContext.request.contextPath}/common/ajaxCheckEmailId.do",{
			minChars : 3,
			min : "no"
		});
	
	$("input#sender").autoCheck("${pageContext.request.contextPath}/common/ajaxCheckCustomerShortNameAvailableOrNot.do",
			{
				minChars : 1,
				min : "no",
			});
});

getOrganizationSubTypes("");
function getOrganizationSubTypes(selectBox) {
	var organizationId = $("select#organizationType").val();
	var customerEnrollmentRequestId = '<s:property value="customerEnrollmentRequest.id" />';
	var url = jQuery.url.getChatURL("/signup/ajaxGetOrganizationSubTypes.do");
	if (organizationId.length == 0) {
		alert("!Oops select Organization Type.");
	} else {
		if ($("select#organizationType option:selected").text() == "Govt") {
			$("li#govtCustomersList").addClass("selected");
			document.getElementById('govtState').style.display = "block";
			document.getElementById('pvrState').style.display = "none";
		} else {
			document.getElementById('pvrState').style.display = "block";
			document.getElementById('govtState').style.display = "none";
			$("li#govtCustomersList").removeClass("selected");
		}

		$("#resultsDiv2")
				.html(
						'<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		var pars = "subjectId=" + organizationId +"&customerEnrollmentRequest.id="+customerEnrollmentRequestId;
		$.ajax( {
			url : url,
			cache : false,
			data : pars,
			success : function(html) {
				$("#resultsDiv2").html(html);
				document.getElementById('resultsDiv2').style.display = "block";
				//document.getElementById('schoolBooksList').style.display="none";
		}
		});
	}
}
function getDistricts(selectBox) {
	var organizationId = $("select#govtState1").val();
	var url = jQuery.url.getChatURL("/signup/ajaxGetDistrictsByState.do");
	if (organizationId.length == 0) {
		alert("please select state.");
	} else {
		$("#districtDiv")
				.html(
						'<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		var pars = "student.categoryId=" + organizationId;
		$.ajax( {
			url : url,
			cache : false,
			data : pars,
			success : function(html) {
				$("#districtDiv").html(html);
				document.getElementById('districtDiv').style.display = "block";
				//document.getElementById('schoolBooksList').style.display="none";
		}
		});
	}
}

$('.numeric').numeric();
function formatPhoneNumber(object) {
	var phoneString = object.value;
	if (phoneString.length == 1) {
		phoneString = "+91-" + phoneString;
	}
	object.value = phoneString;
}

function getDistricts(selectBox) {
	var organizationId = $("select#govtState1").val();
	var url = jQuery.url.getChatURL("/signup/ajaxGetDistrictsByState.do");
	if (organizationId.length == 0) {
		alert("please select state.");
	} else {
		$("#districtDiv")
				.html(
						'<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		var pars = "student.categoryId=" + organizationId;
		$.ajax( {
			url : url,
			cache : false,
			data : pars,
			success : function(html) {
				$("#districtDiv").html(html);
				document.getElementById('districtDiv').style.display = "block";
				//document.getElementById('schoolBooksList').style.display="none";
		}
		});
	}
}

function acceptFun() {
	$('.tempString2').val('Accept');
}
function rejectFun() {
	$('.tempString2').val('Reject');
}


</script>
