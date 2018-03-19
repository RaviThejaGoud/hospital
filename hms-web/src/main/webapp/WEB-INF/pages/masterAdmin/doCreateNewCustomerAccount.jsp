<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head profile="http://gmpg.org/xfn/11">
		<title>eazySchool :: Empowering School Administration</title>
		<sj:head debug="true" compressed="false" defaultIndicator="myDefaultIndicator"  jqueryui="false" />
 		<style type="text/css" media="all">
			@import url("${pageContext.request.contextPath}/plugins/bootstrap/css/bootstrap.min.css");
			@import url("${pageContext.request.contextPath}/plugins/font-awesome/css/font-awesome.min.css");
			@import url("${pageContext.request.contextPath}/styles/newCss/style-metronic.css");
			@import url("${pageContext.request.contextPath}/styles/newCss/style.css");	
			@import url("${pageContext.request.contextPath}/styles/newCss/style-responsive.css");			
			@import url("${pageContext.request.contextPath}/plugins/uniform/css/uniform.default.css");
  			@import url("${pageContext.request.contextPath}/styles/newCss/pages/login.css");
          </style>
	</head>
  <body class="login">
  <div id="forgetTagetDiv">
	<div class="logo">
		<img src="../img/bg/logo.png" alt="EazySchool" />
	</div>
			<div class="content" style="width: 1000px;">
				<jsp:include page="/common/messages.jsp" />
				<h3>
					Customer Registration Form
				</h3>
				<!-- <p>
					In case you have forgotten your  password,Please enter your login id  
					you can get your temporary password instantly on
					your email address or  mobile number and you can immediately set a new
					password online.
				</p> -->
				<s:if test="%{inviteCustomerEnrollment != null && customerEnrollmentRequest == null}">
					<s:form action="urlSaveNewCustomerDetails" id="signupForm"
						method="post" theme="simple" namespace="/signup"
						cssClass="form-horizontal">
						<%@ include file="/common/messages.jsp"%>
						<s:hidden name="packageId" value="%{inviteCustomerEnrollment.packageDetails.id}"></s:hidden>
						<s:hidden name="inviteCustomerEnrollment.id" value="%{inviteCustomerEnrollment.id}"></s:hidden>
						<s:hidden name="customerEnrollmentRequest.id" value="%{customerEnrollmentRequest.id}"></s:hidden>
						<s:hidden name="anyId" cssClass="anyId"></s:hidden>
						<s:hidden name="anyTitle" cssClass="anyTitle"></s:hidden>
						<s:hidden name="tempString2" value="urlCustomerRegistration"></s:hidden>
						<s:hidden name="customerEnrollmentRequest.organizationLevel" value="S"></s:hidden>
						<s:hidden name="customerEnrollmentRequest.feeReceiptModel" value="General"></s:hidden>
						
						
						
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
										<sj:textfield name="customerEnrollmentRequest.firstName" id="firstName"
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
										<sj:textfield name="customerEnrollmentRequest.lastName" id="lastName"
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
										<sj:textfield name="customerEnrollmentRequest.customerShortName" id="sender"
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
										<sj:textfield name="customerEnrollmentRequest.address.streetName" id="custStreet"
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
										<sj:textfield name="customerEnrollmentRequest.address.city" id="custCity"
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
										<s:select id="custState" list="statesList"
											cssClass="required form-control input-medium as-input"
											listKey="stateCode" listValue="stateName"
											headerKey="" headerValue="- Select -"
											name="customerEnrollmentRequest.address.state" theme="simple" />
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
										<sj:textfield name="customerEnrollmentRequest.address.postalCode" size="6"
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
										<sj:textfield name="customerEnrollmentRequest.modifyInvoicePassword" size="20"
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
										<sj:textfield name="customerEnrollmentRequest.deleteInvoicePassword" size="18"
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
										<%-- <s:if test="%{objectList != null && !objectList.isEmpty()}">
											<s:select list="objectList" listKey="organizationName"
												listValue="organizationName" id="category" theme="simple"
												cssClass="form-control input-medium" name="organization"
												onchange="$('input#organizationName').val($(this).find('option:selected').text());"
												headerKey="" headerValue="" cssStyle="padding: 3px 10px;"></s:select>
											<sj:textfield id="organizationName"
												name="customerEnrollmentRequest.organization" value="%{inviteCustomerEnrollment.schoolName}"
												cssClass="form-control promoteClass required"
												cssStyle="float: left; margin-left: 1px; margin-top: -43px; width: 218px;background-color : #FFF;"></sj:textfield>
										</s:if>
										<s:else>
											<sj:textfield id="organizationName" name="customerEnrollmentRequest.organization" value="%{inviteCustomerEnrollment.schoolName}" cssClass="form-control input-medium promoteClass"></sj:textfield>
										</s:else> --%>
										
										<sj:textfield id="organizationName" name="customerEnrollmentRequest.organization" value="%{inviteCustomerEnrollment.schoolName}" cssClass="form-control input-medium promoteClass"></sj:textfield>
										
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
											headerKey="" headerValue="- Select -" name="customerEnrollmentRequest.orgnizationTypeId"
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
											<s:select id="state" list="statesList" disabled="true" theme="simple" 
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
											<s:select id="state" list="statesList"
												cssClass="required form-control input-medium as-input" theme="simple" value="customerEnrollmentRequest.organizationAddress.stateId"
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
												headerValue="- Select -" name="customerEnrollmentRequest.organizationAddress.stateId"
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
											<s:select id="govtState1" list="statesList"
												cssClass="required form-control input-medium as-input"
												listKey="id" listValue="stateName" headerKey=""
												headerValue="- Select -" name="customerEnrollmentRequest.organizationAddress.stateId" theme="simple" 
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
										<sj:textfield id="addressLine1" name="customerEnrollmentRequest.organizationAddress.addressLine1"
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
										<sj:textfield id="street" name="customerEnrollmentRequest.organizationAddress.streetName" size="130"
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
										<sj:textfield id="city" name="customerEnrollmentRequest.organizationAddress.city" size="130"
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
										<sj:textfield id="zipcode" name="customerEnrollmentRequest.organizationAddress.postalCode" size="6"
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
										<sj:textfield id="phoneNumber" name="customerEnrollmentRequest.contactNumber"
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
										<sj:textfield id="mobileNumber" name="customerEnrollmentRequest.mobileNumber"
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
									<sj:textarea name="customerEnrollmentRequest.customerVision" id="maxlength_textarea" cssClass="form-control" placeholder="This textarea has a limit of 2000 chars."   rows="4" cols="20" maxlength="2000"></sj:textarea>
									<span class="help-block"> Maxlength is 2000 chars. </span>
									<!--<sj:textarea name="customerEnrollmentRequest.customerVision" id="vision"
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
									<sj:textarea name="customerEnrollmentRequest.customerMission" id="maxlength_textarea1" cssClass="form-control" placeholder="This textarea has a limit of 2000 chars."   rows="4" cols="20" maxlength="2000"></sj:textarea>
									<span class="help-block"> Maxlength is 2000 chars. </span>
									<!--<s:textarea name="customerEnrollmentRequest.customerMission" id="mission"
										cssClass="word_counter form-control" rows="4" cols="20"
										maxCharsData="2000"></s:textarea>
									<span class="counter1"></span>
								--></div>
							</div>
						</div>
						<!-- <div class="row">
							<div class="col-md-6">
								<div class="form-group">
									<label class="control-label col-md-4">
										<span class="required">*</span>Your Organization Is :
									</label>
									<div class="col-md-5">
									<div class="radio-list">
										<label class="radio-inline"> <input type="radio" id="organizationLevel" checked="checked"
											value="S" name="customerEnrollmentRequest.organizationLevel">School
										</label>
										<label class="radio-inline"> <input type="radio"
											value="C" name="customerEnrollmentRequest.organizationLevel">College
										</label>
									</div>
									<div class="make-switch has-switch" data-id="S" data-value="C" style="width:120px" data-off="warning" data-on="success" data-off-label="College" data-on-label="School">
										<input type="radio" class="toggle" checked="checked" id="organizationLevel" tabindex="7">
										<input type="hidden" name="customerEnrollmentRequest.organizationLevel" value="S">
									</div>
									</div>
								</div>
							</div>
						</div> -->	
						<!-- <div class="row">
							
							<div class="col-md-6">
								<div class="row form-group">
										<label class="col-md-5 control-label">
											Fee Receipt Model : </label>
										<div class="col-md-7">
											<div class="radio-list">
												<label class="radio-inline"> <input type="radio" checked="checked"
													value="General" name="customerEnrollmentRequest.feeReceiptModel">General
												</label>
												<label class="radio-inline"> <input type="radio"
													value="A3" name="customerEnrollmentRequest.feeReceiptModel">A3
												</label>
												<label class="radio-inline"> <input type="radio"
													value="A4" name="customerEnrollmentRequest.feeReceiptModel">A4
												</label>
												<label class="radio-inline"> <input type="radio"
													value="A5" name="customerEnrollmentRequest.feeReceiptModel">A5
												</label>
												<label class="radio-inline"> <input type="radio"
													value="A6" name="customerEnrollmentRequest.feeReceiptModel">A6
												</label>
											</div>
										</div>
									</div>
								</div>
							</div> -->
							<h4 class="pageTitle bold form-section">
								Aditional Features
							</h4>
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
											  name="customerEnrollmentRequest.custEmail"  
											cssClass="required form-control input-medium as-input email" /><!-- value="%{inviteCustomerEnrollment.email}" -->
									</div>
								</div>
							</div>
							
							<div class="col-md-6">
								<div class="form-group">
									<label class="control-label col-md-5">
										<span class="required">*</span>Password :
									</label>
									<div class="col-md-7">
										<s:password id="password" name='customerEnrollmentRequest.password'
											cssClass="required form-control input-medium as-input"
											 cssStyle="width:70%"  />
										<div id="passwordStrength" class="strength0" style="display: none;"></div>
									</div>
								</div>
							</div>
							
						</div>
						
						<div id="displaySubjectTypes"></div>
						
						<div class="row">
							<div class="col-md-12">
								<div class="form-group">
									<label class="control-label col-md-2" id="agreeId">
										<input type="checkbox" name="termsConditions" id="termsConditions" class="checkbox">
									</label>
									Please accept the terms and conditions
									<!-- <span class="hintMessage">(If
										you want to enable this type please check this check box.)</span> -->
								</div>
								<!-- <div class="form-group">
									<label class="control-label col-md-2">
										<input type="checkbox" name="isTransport" id="yesCheck"
											class="checkbox">
									</label>
									TMS
									<span class="hintMessage">(If
										you want to enable this type please check this check box.)</span>
								</div> -->
							</div>
						</div>
						
						
						<div class="form-body">
							<div class="form-actions fluid">
								<div class="col-md-offset-2 col-md-6">
								
									 <sj:submit   targets="forgetTagetDiv" value="Submit" formIds="signupForm"
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
				</s:if>
				<s:else>
					<p>You already submitted the request, we will submitted for Eazyschool Administrator Review.</p>
			</s:else>
				<br/>
				<!-- <div class="row">
					<div class="form-group">
						<div class="col-md-1">
							<span class="label label-sm label-danger"> Note </span>
						</div>
						<div class="col-md-11">
							<p>
								If you have not registered or not received the credentials from
								school admin then please contact your school for login account
								details.
							</p>
						</div>
					</div>
				</div> -->
			</div>
			<div class="copyright">
		2015 &copy; HYNIVA Consulting Services PVT Ltd.
	</div>
	</div>
	
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/jQuery.url.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/plugins/bootstrap-switch/static/js/bootstrap-switch.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/ems.js"> </script>
<script type="text/javascript"	src="${pageContext.request.contextPath}/scripts/common/jquery.alphanumeric.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/validation/jquery.validate.js"></script>	
<script type="text/javascript"  src="${pageContext.request.contextPath}/scripts/newScripts/app.js"></script>   
<script  src="${pageContext.request.contextPath}/plugins/select2/select2.min.js" type="text/javascript" ></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/common/jquery.autoCheck.js"></script>
<script type="text/javascript"	src="${pageContext.request.contextPath}/scripts/newScripts/form-components.js"></script>
<script  type="text/javascript" src="${pageContext.request.contextPath}/plugins/bootstrap/bootstrap-maxlength/boostrap-maxlength.min.js"></script> 

<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/formElementScript.js"></script>
<script type="text/javascript"  src="${pageContext.request.contextPath}/plugins/uniform/jquery.uniform.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js" type="text/javascript"></script>
<script type="text/javascript"  src="${pageContext.request.contextPath}/plugins/bootstrap-timepicker/js/bootstrap-timepicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/newScripts/bootstrap-datetimepicker.min.js"> </script>

<script type="text/javascript">
$(document).ready(function() {
FormComponents.init();
FormAdvanced.init();
//$.destroyTopic('doCustomRegistartion');
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
		}
		 else if($("input[name=termsConditions]:unchecked").length==$("input[name=termsConditions]").length){
			alert("Please select terms and conditions.");
			event.originalEvent.options.submit=false;
		}
		else{
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
	document.title = 'Customer Registration Form ';
	$("input#email").autoCheck("${pageContext.request.contextPath}/common/ajaxCheckEmailId.do",{
			minChars : 3,
			min : "no"
		});
	
	$("input#sender").autoCheck("${pageContext.request.contextPath}/common/ajaxCheckCustomerShortNameAvailableOrNot.do",
			{
				minChars : 1,
				min : "no",
			});
	//$("input.form-control").val('');
	
	//getOrganizationSubTypes("");
});


function getOrganizationSubTypes(selectBox) {
	var organizationId = $("select#organizationType").val();
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
</script></body>
</html>