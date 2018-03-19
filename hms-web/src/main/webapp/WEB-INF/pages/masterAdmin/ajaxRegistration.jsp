<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/plugins/bootstrap-switch/static/js/bootstrap-switch.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/ems.js"> </script>
<s:form action="ajaxMasterSaveCustomerDetails" id="signupForm"
	method="post" theme="simple" namespace="/signup"
	cssClass="form-horizontal">
	<%@ include file="/common/messages.jsp"%>
	<s:hidden name="packageId" value="%{tempString}"></s:hidden>
	<s:hidden name="anyId" cssClass="anyId"></s:hidden>
	<s:hidden name="anyTitle" cssClass="anyTitle"></s:hidden>
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
					<sj:textfield name="customer.firstName" id="firstName"
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
					<sj:textfield name="customer.lastName" id="lastName"
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
					<sj:textfield name="customer.customerShortName" id="sender"
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
					<sj:textfield name="customer.address.streetName" id="custStreet"
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
					<sj:textfield name="customer.address.city" id="custCity"
						cssClass="required form-control input-medium as-input"
						maxlength="30"></sj:textfield>
				</div>
			</div>
		</div>
			<div class="col-md-6" >
				<div class="form-group">
					<label class="control-label col-md-5">
						<span class="required">*</span>Country :
					</label>
					<div class="col-md-7">
						<s:select id="countryId" list="countryList"
							cssClass="required form-control input-medium as-input"
							listKey="countryCode" listValue="countryName" headerKey=""
							headerValue="- Select -" name="customer.address.country" theme="simple"
							onchange="javascript:getCountryState(this);" />
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
					<sj:textfield name="customer.address.postalCode" size="6"
						maxlength="6" id="custZipcode"  
						cssClass="numeric required form-control input-medium as-input" />
				</div>
			</div>
		</div>
		<div class="col-md-6" id="countryState" style="display: none">
			<div class="form-group">
				<label class="control-label col-md-5">
					<span class="required">*</span>State :
				</label>
				<div class="col-md-7">
					<s:select id="custState" list="statesList"
						cssClass="required form-control input-medium as-input"
						listKey="stateCode" listValue="stateName"
						headerKey="" headerValue="- Select -"
						name="customer.address.state" theme="simple" />
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
				<div class="col-md-5">
					<s:if test="%{objectList != null && !objectList.isEmpty()}">
						<sj:textfield  list="objectList" name="customer.organization" cssClass="form-control input-medium as-input required"/> 
							<datalist id="objectList">
								<s:iterator value="objectList">
								<option value='<s:property value="organizationName"/>'><s:property value="organizationName"/></option>
								</s:iterator>
							</datalist>
						<%-- <s:select list="objectList" listKey="organizationName"
							listValue="organizationName" id="category" theme="simple"
							cssClass="form-control input-medium" name="organization"
							onchange="$('input#organizationName').val($(this).find('option:selected').text());"
							headerKey="" headerValue="" cssStyle="padding: 3px 10px;"></s:select>
						<sj:textfield id="organizationName"
							name="customer.organization"
							cssClass="form-control promoteClass required"
							cssStyle="float: left; margin-left: 1px; margin-top: -31px; width: 207px;background-color : #FFF;"></sj:textfield> --%>
					</s:if>
					<s:else>
						<sj:textfield id="organizationName" name="customer.organization" cssClass="form-control input-medium promoteClass"></sj:textfield>
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
					<s:select id="organizationType" list="organizationTypesList"
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
	</div>
	<div class="row" id="govtCountry" style="display: none">
		<s:if test='%{user.isDEO == "Y" || user.isBEO == "Y" || user.isSEO == "Y" || user.isCEO == "Y"}'>
			<div class="col-md-6" >
				<div class="form-group">
					<label class="control-label col-md-5">
						<span class="required">*</span>Country :
					</label>
					<div class="col-md-7">
						<s:select id="govCountry" list="countryList" disabled="true" theme="simple" 
							cssClass="required form-control input-medium as-input"
							listKey="id" listValue="countryName" headerKey=""
							headerValue="- Select -" name="country.id" onchange="javascript:getCountryStates(this);"/>
							<s:hidden name="country.id"></s:hidden>
					</div>
				</div>
			</div>
		</s:if>
		<s:else>
		<div class="col-md-6" >
				<div class="form-group">
					<label class="control-label col-md-5">
						<span class="required">*</span>Country :
					</label>
					<div class="col-md-7">
						<s:select id="govCountry" list="countryList"
							cssClass="required form-control input-medium as-input" theme="simple" 
							listKey="id" listValue="countryName" headerKey=""
							headerValue="- Select -" name="country.id" onchange="javascript:getCountryStates(this);"/>
					</div>
				</div>
			</div>
		</s:else>
		<div id="pvrState"  style="display: none">
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
							headerValue="- Select -" name="stateId"
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
						<span class="required">*</span>state :
					</label>
					<div class="col-md-7">
						<s:select id="govtState1" list="statesList"
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
	</div>
	<div class="row">
		<div class="col-md-6">
			<div class="form-group">
				<label class="control-label col-md-5">
					<span class="required">*</span> AddressLine1 :
				</label>
				<div class="col-md-7">
					<sj:textfield id="addressLine1" name="address.addressLine1"
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
					<sj:textfield id="street" name="address.streetName" size="130"
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
					<sj:textfield id="city" name="address.city" size="130"
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
					<sj:textfield id="zipcode" name="address.postalCode" size="6"
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
					<sj:textfield id="phoneNumber" name="customer.contactNumber"
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
					<sj:textfield id="mobileNumber" name="customer.mobileNumber"
						maxlength="10" cssClass="numeric required form-control input-medium as-input" />
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
				<sj:textarea name="customer.customerVision" id="maxlength_textarea" cssClass="form-control" placeholder="This textarea has a limit of 2000 chars."   rows="4" cols="20" maxlength="2000"></sj:textarea>
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
				<sj:textarea name="customer.customerMission" id="maxlength_textarea1" cssClass="form-control" placeholder="This textarea has a limit of 2000 chars."   rows="4" cols="20" maxlength="2000"></sj:textarea>
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
				<s:select id="serviceProvider" list="smsServiceProvidersList"
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
		<div class="row">
			<div class="col-md-6">
			<div class="form-group">
				<label class="control-label col-md-4">
					<span class="required">*</span>Standard  Type :
				</label>
				<div class="col-md-7">
					<s:select id="standardType" headerKey="" headerValue="- Select -" name="customer.standardType"
						cssClass="required form-control input-medium"
						list="#{'P':'Pre School','L':'Lower and Higher','B':'Both'}" />
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
						  name="customer.custEmail" 
						cssClass="required form-control input-medium" />
				</div>
			</div>
		</div>
		<div class="col-md-6">
			<div class="form-group">
				<label class="control-label col-md-5">
					<span class="required">*</span>Password :
				</label>
				<div class="col-md-7">
					<s:password id="password" name='customer.password'
						cssClass="required form-control input-medium as-input"
						 cssStyle="width:70%"  />
					<div id="passwordStrength" class="strength0" style="display: none;"></div>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-md-12">
			<div class="form-group">
				<label class="control-label col-md-2">
					<input type="checkbox" name="isHostel" id="yesCheck"
						class="checkbox">
				</label>
				HMS
				<span class="hintMessage">(If
					you want to enable this type please check this check box.)</span>
			</div>
			<div class="form-group">
				<label class="control-label col-md-2">
					<input type="checkbox" name="isTransport" id="yesCheck"
						class="checkbox">
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
				<sj:submit   targets="mainContentDiv" value="Submit" formIds="signupForm"
					cssClass="btn blue"  validate="true" indicator="indicator"
					onBeforeTopics="doCustomRegistartion" />
				<s:url id="doCancel" action="ajaxDoAddNewCustomerRegister"
					includeParams="all" namespace="/signup"></s:url>
				<sj:a href="%{doCancel}" cssClass="btn default"
					 targets="mainContentDiv" button="false">Cancel</sj:a>
			</div>
		</div>
	</div>
</s:form>
<script language="JavaScript" type="text/javascript" src="${pageContext.request.contextPath}/plugins/bootstrap-switch/static/js/bootstrap-switch.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	FormComponents.init();
	FormAdvanced.init();
	UIExtendedModals.init();
	$("#email").autoCheck("${pageContext.request.contextPath}/masterAdmin/ajaxCheckEmailId.do",{
		minChars : 3,
		min : "no"
	});
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
			if ($("li#govtCustomersList").hasClass("selected")) {
				$("li#govtCustomersList a").click();
				$("li#govtCustomersList").removeClass("selected");
			}
		}
		 $('p.word-taken').each(function() {
			  if($(this).html()=='Already taken!!!'){
			     event.originalEvent.options.submit=false;
			   }
			 });
			 $('button.close').click();
		 /* var custShortNum=$('input.custShortNumStr').parents('div').next('div').find('p.word-taken').html();
	 		if(custShortNum=='Already taken!!!'){
	     	    event.originalEvent.options.submit=false;
	     	    $('input.custShortNumStr').val('');
	          } */
	 		/*  var custEmail=$('input.email').parents('div').next('div').find('p.word-taken').html();
		 		if(custEmail=='Already taken!!!'){
		     	    event.originalEvent.options.submit=false;
		     	    $('input.email').val('');
		          } */
	});
	document.title = 'Eazy School SIGN UP ';
	
	/* Customer short name validation removed because some of school asking same short name and we are not taking this short name now in username so for now not required to validate short name */
	/* $("input#sender").autoCheck("${pageContext.request.contextPath}/common/ajaxCheckCustomerShortNameAvailableOrNot.do",
			{
				minChars : 1,
				min : "no",
			}); */
	$("input.form-control").val('');
});

function getCountryStates(selectBox) {
	var countryId = $("select#govCountry").val();
	if(countryId==99){
		$("#pvrState").show();
		//document.getElementById('pvrState').style.display = "block";
	}else{
		$("#pvrState").hide();
		//document.getElementById('pvrState').style.display = "none";
	}
}
function getCountryState(selectBox) {
	var countryId = $("select#countryId").val();
	if(countryId=="IN"){
		$("#countryState").show();
		//document.getElementById('countryState').style.display = "block";
	}else{
		$("#countryState").hide();
		//document.getElementById('countryState').style.display = "none";
	}
}
function getOrganizationSubTypes(selectBox) {
	var organizationId = $("select#organizationType").val();
	var url = jQuery.url.getChatURL("/signup/ajaxGetOrganizationSubTypes.do");
	if (organizationId.length == 0) {
		alert("!Oops select Organization Type.");
	} else {
		if ($("select#organizationType option:selected").text() == "Govt") {
			$("li#govtCustomersList").addClass("selected");
			$("#govCountry").val('');
			$("#govtState1").val('');
			$("#govtState").show();
			$("#govtCountry").show();
			$("#pvtState").hide();
			
			/* document.getElementById('govtState').style.display = "block";
			document.getElementById('govtCountry').style.display = "block";
			document.getElementById('pvrState').style.display = "none"; */
		} else {
			$("#govtState").hide();
			$("#govtCountry").show();
			$("#pvtState").hide();
			/* document.getElementById('pvrState').style.display = "none";
			document.getElementById('govtCountry').style.display = "block";
			document.getElementById('govtState').style.display = "none"; */
			$("#govCountry").val('');
			$("#govtState1").val('');
			$("li#govtCustomersList").removeClass("selected");
		}

		$("#resultsDiv2")
				.html(
						'<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		var pars = "subjectId=" + organizationId;
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
				$("#districtDiv").show();
				//document.getElementById('districtDiv').style.display = "block";
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
				$("#districtDiv").show();
				//document.getElementById('districtDiv').style.display = "block";
				//document.getElementById('schoolBooksList').style.display="none";
		}
		});
	}
}
</script>
