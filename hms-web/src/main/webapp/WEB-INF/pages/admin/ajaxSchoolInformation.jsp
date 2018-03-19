<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>School Settings
				</div>
			</div>
			<div class="portlet-body">
				<div class="tabbable tabbable-custom tabbable-full-width">
					<ul class="nav nav-tabs">
					
						<li><s:url id="doAdmissionNumberSettings"
								action="ajaxDoAdmissionNumberSettings" includeParams="all"
								escapeAmp="false" namespace="/admin">
							</s:url> <sj:a href="%{doAdmissionNumberSettings}" indicator="indicator"
								targets="changeSchoolInfoContent" data-toggle="tab">Admission Number Settings</sj:a>
						</li>
						<li><s:url id="doStaffRoom"
								action="ajaxDoAddStaffRoomDetails" includeParams="all"
								escapeAmp="false" namespace="/admin">
							</s:url> <sj:a href="%{doStaffRoom}" indicator="indicator"
								targets="changeSchoolInfoContent" data-toggle="tab">Staff Room</sj:a>
						</li>
						<li class="active"><s:url id="schoolSettingsId"
								action="ajaxDoSchoolInformation" namespace="/admin">
							</s:url> <sj:a id="schoolSettingsId" href="%{schoolSettingsId}"
								targets="mainContentDiv" data-toggle="tab">School Settings</sj:a>
						</li>
					</ul>
					<div id="changeSchoolInfoContent" class="tab-content">
						<%@ include file="/common/messages.jsp"%>
						<s:if test='%{customer != null && !customer == ""}'>
							<s:if
								test='%{(#session.previousYear == null || #session.previousYear.isEmpty()) || (#session.previousYear == "N")}'>
								<div class="row">
									<div class="form-group col-md-6">
										<label class="col-md-6 control-label"> Email & Mobile
											Services On/Off : </label>
										<div class="col-md-8">
											<label class="checkbox-inline"> <span id="eservice"><input
													type="checkbox" class="changeServicesStatus serviceReload" value="Emails"
													name="customer.checkEmailService" id="emailService"
													data="${pageContext.request.contextPath}/admin/ajaxChangeEmailServiceStatus" />
											</span> Email Service 
											</label>
										</div>
										<s:if test='%{!(customer.standardType =="p" || customer.standardType =="P")}'>
											<div class="col-md-12">
												 <label class="checkbox-inline"> <span id="eservice"><input
														type="checkbox" class="changeServicesStatus"
														name="customer.checkAttendanceEmailService"
														id="emailAttendanceService"
														data="${pageContext.request.contextPath}/admin/ajaxChangeAttendanceEmailServiceStatus" />
												</span> Attendance Email
												</label>
											</div>	
										</s:if>
										<div class="col-md-8">
											 <label class="checkbox-inline"> <span id="mservice"><input
													type="checkbox" class="changeServicesStatus serviceReload"
													name="customer.checkMobileService" id="messagingService" value="SMS"
													data="${pageContext.request.contextPath}/admin/ajaxChangeMobileServiceStatus" />
											</span> Mobile Service
											</label>
										</div>
										<div class="col-md-12">
											 <label class="checkbox-inline"> <span id="mservice"><input
													type="checkbox" class="changeServicesStatus"
													name="customer.checkMobilePaymentService"
													id="messagingPaymentService"
													data="${pageContext.request.contextPath}/common/ajaxChangePaymentMobileServiceStatus" />
											</span> Payment SMS Service  &nbsp;&nbsp;&nbsp;&nbsp;(Applicable for Fee Payments)
											</label>
											<div style="float: right;width:200px;">
												
											</div>
											<label class="checkbox-inline"> <span id="mservice"><input
													type="checkbox" class="changeServicesStatus"
													name="customer.checkParentSmsService"
													id="messagingParentService"
													data="${pageContext.request.contextPath}/common/ajaxChangeParentRegistrationMobileServiceStatus" />
											</span> Parent Registration SMS Service
											</label><div style="float: right;width:200px;"/>
											<s:if test='%{#session.customerTransportStauts==true}'>
												<label class="checkbox-inline" style="margin-left:0"> <span id="mservice"><input
														type="checkbox" class="changeServicesStatus"
														name="customer.checkTransportService" id="transportService"
														data="${pageContext.request.contextPath}/admin/ajaxChangeTransportServiceStatus" />
												</span> Transport SMS Service
												</label>
											</s:if><div style="float: right;width:200px;"/>
											<label class="checkbox-inline"> <span id="mservice"><input
													type="checkbox" class="changeServicesStatus"
													name="customer.checkAssignmentSmsService"
													id="messagingAssignmentService"
													data="${pageContext.request.contextPath}/common/ajaxChangeAssignmentMobileServiceStatus" />
											</span> Assignment SMS Service
											</label>
										</div>
									</div>
									<div class="col-md-6">
										<jsp:include page="/WEB-INF/pages/admin/addCustomerImage.jsp"></jsp:include>
									</div>
								</div>
							</s:if>
							<div class="form-body">
								<s:form action="ajaxUpdateSchoolDetails" theme="simple"
									cssClass="form-horizontal" id="changeSchoolInfo" method="post"
									enctype="multipart/form-data" namespace="/admin">
									<s:hidden name="customer.showTotalOrPaidAmount"></s:hidden>
									<h4 class="pageTitle bold form-section">School Information
									</h4>
									<p>
										<span class="label label-danger"> NOTE : </span>&nbsp; Update
										school information by click on 'Submit' button.
									</p>
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-4"> <span
													class="required">*</span>School Name :
												</label>
												<div class="col-md-5">
													<sj:textfield name="customer.organization"
														id="customerOrgName"
														cssClass="form-control required input-medium"
														maxlength="60"></sj:textfield>
												</div>
											</div>
										</div>
										<div class="col-md-6">
										    <div class="form-group">
										        <label class="control-label col-md-4"> Trust/Society :
										        </label>
										        <div class="col-md-5">
													<sj:textfield name="customer.societyName"
														id="customersocietyName"
														cssClass="form-control input-medium"
														maxlength="60"></sj:textfield>
											    </div>
									        </div>
								        </div>
									</div>
									<div class="row">
									    <div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-4"> Website URL :
												</label>
												<div class="col-md-5">
													<sj:textfield name="customer.webSiteUrl" id="url"
														cssClass="form-control input-medium" maxlength="60"></sj:textfield>
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-4"> <span
													class="required">*</span>Email Address :
												</label>
												<div class="col-md-5">
													<sj:textfield name="customer.custEmail" id="addressEmail"
														cssClass="form-control input-medium required email"
														maxlength="40" readonly="true"></sj:textfield>
												</div>
											</div>
										</div>
									</div>
									<div class="row">
									    <div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-4"> Mobile Number
													: </label>
												<div class="col-md-5">
													<sj:textfield name="customer.mobileNumber"
														id="mobileNumber"
														cssClass="numeric form-control input-medium"
														maxlength="10"></sj:textfield>
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-4"> Allowed Sms :
												</label>
												<div class="col-md-5">
													<sj:textfield name="customer.allowedTotalSms"
														id="allowedTotalSms" readonly="true"
														cssClass="form-control input-medium numeric"
														maxlength="40"></sj:textfield>
												</div>
											</div>
										</div>
									</div>
									<div class="row">
									    <div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-4"> <span
													class="required">*</span>Revenue District :
												</label>
												<div class="col-md-5">
													<sj:textfield name="customer.revenueDistrict" id="sender"
														cssClass="form-control required input-medium"></sj:textfield>
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-4"> <span
													class="required">*</span>School code :
												</label>
												<div class="col-md-5">
													<sj:textfield name="customer.schoolCode" id="schoolCode"
														maxlength="20"
														cssClass="required form-control input-medium"></sj:textfield>
												</div>
											</div>
										</div>
									</div>
									
									<%-- <div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-4"> Dice code :
												</label>
												<div class="col-md-5">
													<sj:textfield name="customer.diceCode" id="diceCode"
														maxlength="10"
														cssClass="form-control input-medium"></sj:textfield>
												</div>
											</div>
										</div>
									</div> --%>
									
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-4"> <span
													class="required">*</span>Educational District :
												</label>
												<div class="col-md-5">
													<sj:textfield name="customer.educationalDistrict"
														id="educationalDistrict"
														cssClass="form-control input-medium required"
														maxlength="60"></sj:textfield>
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-4"> <span
													class="required">*</span>Board Of Education :
												</label>
												<div class="col-md-5">
													<sj:textfield name="customer.boardOfEducation"
														id="boardOfEducation"
														cssClass="form-control input-medium required"></sj:textfield>
												</div>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-4"> <span
													class="required">*</span>Recognized By :
												</label>
												<div class="col-md-5">
													<sj:textfield name="customer.recognisedBy"
														id="recognisedBy" label=""
														cssClass="required input-medium form-control"></sj:textfield>
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-4"> <span
													class="required">*</span>AddressLine1 :
												</label>
												<div class="col-md-5">
													<sj:textfield id="street"
														name="customer.address.addressLine1" size="130"
														maxlength="60"
														cssClass="required form-control input-medium" />
												</div>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-4"> <span
													class="required">*</span>Street :
												</label>
												<div class="col-md-5">
													<sj:textfield id="streetName"
														name="customer.address.streetName" size="130"
														maxlength="60"
														cssClass="required form-control input-medium" />
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-4"> <span
													class="required">*</span>City :
												</label>
												<div class="col-md-5">
													<sj:textfield id="city" name="customer.address.city"
														maxlength="30"
														cssClass="required form-control input-medium" />
												</div>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-4"> <span
													class="required">*</span>Country :
												</label>
												<div class="col-md-5">
													<s:select id="country" list="countryList"
														cssClass="required form-control input-medium countrySelect"
														listKey="countryCode" listValue="countryName" headerKey=""
														headerValue="- Select -" name="customer.address.country" onchange="javascript:getCountryState(this);"/>
												</div>
											</div>
										</div>
										<div class="col-md-6" id="countryState" style="display: none">
											<div class="form-group">
												<label class="control-label col-md-4"> <span
													class="required">*</span>State :
												</label>
												<div class="col-md-5">
													<s:select id="state" list="statesList"
														cssClass="required form-control input-medium stateSelect"
														listKey="stateCode" listValue="stateName" headerKey=""
														headerValue="- Select -" name="customer.address.state" />
												</div>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-4"> <span
													class="required">*</span>Pincode :
												</label>
												<div class="col-md-5">
													<sj:textfield name="customer.address.postalCode" size="6"
														maxlength="6" id="zipcode"
														cssClass="required form-control input-medium numeric text_right" />
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-4"> <span
													class="required">*</span>Customer ShortName :
												</label>
												<div class="col-md-5">
													<sj:textfield name="customer.customerShortName"
														id="customerShortName" readonly="true" disabled="true"
														cssClass="required form-control input-medium"
														maxlength="40"></sj:textfield>
												</div>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-4"> <span
													class="required">*</span>Phone Number :
												</label>
												<div class="col-md-5">
													<sj:textfield name="customer.contactNumber"
														id="contactNumber"
														cssClass="form-control input-medium required "
														maxlength="10"></sj:textfield>
														<span class="help-block">Please add '-' symbol after STD code. </span>
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-4"> Affiliation
													No : </label>
												<div class="col-md-5">
													<sj:textfield name="customer.affiliationNumber" size="20"
														maxlength="20" id="mip"
														cssClass="form-control text_right input-medium" />
												</div>
											</div>
										</div>
									</div>

									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-4"> No of
													WashRooms For Boys : </label>
												<div class="col-md-5">
													<sj:textfield name="customer.washRoom.noofWashRoomsForBoys"
														size="20" maxlength="2" id="mip"
														cssClass="form-control text_right input-medium numeric" />
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-4"> No of
													WashRooms For Girls : </label>
												<div class="col-md-5">
													<sj:textfield
														name="customer.washRoom.noofWashRoomsForGirls"
														id="customerShortName"
														cssClass="form-control text_right input-medium numeric"
														maxlength="2"></sj:textfield>
												</div>
											</div>
										</div>
									</div>
									<div class="row">
									    <div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-4"> No of
													WashRooms For HeadMasters : </label>
												<div class="col-md-5">
													<sj:textfield
														name="customer.washRoom.noofWashRoomsForHeadMasters"
														id="customerShortName"
														cssClass="form-control text_right input-medium numeric"
														maxlength="2"></sj:textfield>
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-4"> No of
													WashRooms For Teachers : </label>
												<div class="col-md-5">
													<sj:textfield
														name="customer.washRoom.noofWashRoomsForTeachers"
														size="20" maxlength="2" id="mip"
														cssClass="form-control text_right input-medium numeric" />
												</div>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group">
											<label class="control-label col-md-2"> Vision : </label>
											<div class="col-md-9">
												<sj:textarea name="customer.customerVision"
													id="maxlength_textarea" cssClass="form-control"
													placeholder="This textarea has a limit of 2000 chars."
													rows="4" cols="20" maxlength="2000"></sj:textarea>
												<span class="help-block"> Maxlength is 2000 chars. </span>
												<!--<s:textarea name="customer.customerVision" id="vision"
												cssClass="word_count form-control" rows="4" cols="20"
												maxCharsData="2000"></s:textarea>
											<span class="counter"></span>
										-->
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group">
											<label class="control-label col-md-2"> Mission : </label>
											<div class="col-md-9">
												<sj:textarea name="customer.customerMission"
													id="maxlength_textarea1" cssClass="form-control"
													placeholder="This textarea has a limit of 2000 chars."
													rows="4" cols="20" maxlength="2000"></sj:textarea>
												<span class="help-block"> Maxlength is 2000 chars. </span>
												<!--<s:textarea name="customer.customerMission" id="mission"
												cssClass="word_counter form-control" rows="4" cols="20"
												maxCharsData="2000"></s:textarea>
											<span class="counter1"></span>
										-->
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-4"> <span
													class="required">*</span>Your Organization Is:
												</label>
												<div class="col-md-5">
													<div class="make-switch has-switch" data-id="S"
														data-value="C" style="width: 120px" data-off="warning"
														data-on="success" data-off-label="College"
														data-on-label="School">
														<s:if test='%{customer.organizationLevel =="S"}'>
															<input type="radio" class="toggle" checked="checked"
																id="organizationLevel" tabindex="7">
														</s:if>
														<s:else>
															<input type="radio" class="toggle" id="gender">
														</s:else>
														<input type="hidden" name="customer.organizationLevel"
															value='<s:property value="customer.organizationLevel"/>'>
													</div>
												</div>
											</div>
										</div>
									</div>
									
									<s:if test="%{syllabusList!= null && !syllabusList.isEmpty()}">
									<div class="row">
											<div class="col-md-6">
												<div class="form-group">
													<label class="control-label col-md-4">
													 <span class="required">*</span>Select Syllabus Type : </label>
													<div class="col-md-7">
														<s:checkboxlist name="objectList" cssClass="checkbox"
															list="syllabusList" listKey="id"
															listValue="syllabusTypeName" theme="ems"
															cssStyle="width:100px;" />
													</div>
												</div>
											</div>
									</div>
									<s:iterator value="syllabusList">
										<div class="row" id="syllabus<s:property value="id" />" style="display: none;">
											<div class="col-md-6">
												<div class="form-group">
													<label class="control-label col-md-4"> <s:property value="syllabusTypeName" /> Primary School Code : </label>
													<div class="col-md-5">
														<sj:textfield name="schoolCodePrimary%{id}" id="schoolCodePrimary%{id}"
															size="20" maxlength="25"
															cssClass="form-control text_right input-medium" />
													</div>
												</div>
											</div>
											<div class="col-md-6">
												<div class="form-group">
													<label class="control-label col-md-4"> <s:property value="syllabusTypeName" /> High School Code : </label>
													<div class="col-md-5">
														<sj:textfield
															name="schoolCodeHigher%{id}"
															id="schoolCodeHigher%{id}"
															cssClass="form-control text_right input-medium"
															maxlength="25"></sj:textfield>
													</div>
												</div>
											</div>
										</div>
									</s:iterator>
									
									<s:iterator value="objectList">
										<script type="text/javascript">
											var syllabusId = '<s:property />';
											$('#syllabus'+syllabusId).show();
											
											var dataURL = jQuery.url.getChatURL("/admin/ajaxGetSyllabusTypeSchoolCodes.do?syllabusType.id=" + syllabusId);
											//$('#loadingImage').html( '<div align="center" style="margin:150px 0px 4px 500px;position:absolute;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
											$.ajax( {
														url : dataURL,
														cache : false,
														dataType : 'json',
														success : function(response) {
															if (response.schoolCodesData) {
																//$('#loadingImage').hide();
																var data = response.schoolCodesData;
																if (data.length > 0) {
																	for ( var i = 0; i < data.length; i++) 
																	{
																		
																		var syllabusTypeId = data[i].SYLLABUSTYPEID
																		
																		if(isNonEmpty(data[i].PRIMARYSCHOOLCODE))
																		{
																			$('#schoolCodePrimary'+syllabusTypeId).val(data[i].PRIMARYSCHOOLCODE);
																		}
																		else if(isNonEmpty(data[i].HIGHERSCHOOLCODE))
																		{
																			$('#schoolCodeHigher'+syllabusTypeId).val(data[i].HIGHERSCHOOLCODE);
																		}
																		
																		
																		
																		/* $( 'tr.subjectClassMonths:visible') .each( function() {
																							$(this) .find( "td#subjMonth" + data[i].STUDENTID  + data[i].SUBJECTID) .each( function() {
																								
																								$(this) .find('input[id^=' + data[i].STUDENTID + 'SM' +  data[i].SUBJECTID +'SM' +  data[i].EXAMSCHEDULEID +']').val(data[i].INPUTVALUE);
																							});
																						}); */
																	}
																}
																//$('#divId').html(null);
															}
														}
													});
											
										</script>
									</s:iterator>
									</s:if>
									
									<div class="row">
										<div class="form-bordered form-row-stripped"
											id="addStudentsSameAdmissionNumberDivId">
											<h4 class="pageTitle bold form-section">Student Admission Number</h4>
											<div class="form-group">
												<label class="control-label col-md-3"> 
													<span class="required">*</span>Add students with same admission number :
												</label>
												<div class="col-md-3">
													<div class="make-switch has-switch" data-id="true" data-value="false" style="width: 180px" data-off="warning"
														data-on="success" data-off-label="No" data-on-label="Yes">
														<s:if test='%{customer.addStudentsSameAdmissionNumber}'>
															<input type="radio" class="toggle" id="addStudentsSameAdmissionNumber" checked="checked" disabled="disabled" />
														</s:if>
														<s:else>
															<input type="radio" class="toggle" id="addStudentsSameAdmissionNumber" disabled="disabled"/>
														</s:else>
														<input type="hidden" name="customer.addStudentsSameAdmissionNumber" value="<s:property value="customer.addStudentsSameAdmissionNumber"/>">
													</div>
												</div>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-bordered form-row-stripped"
											id="alphaNumericRollNumberDivId">
											<h4 class="pageTitle bold form-section">Student Roll Number</h4>
											<div class="form-group">
												<label class="control-label col-md-3"> 
													<span class="required">*</span>Generate student roll number with alphanumeric values :
												</label>
												<div class="col-md-3">
												<s:if test='%{customer.alphaNumericRollNumber == "" || customer.alphaNumericRollNumber == null}'>
													<div class="make-switch has-switch" data-id="Y" data-value="N" style="width: 180px" data-off="warning"
														data-on="success" data-off-label="No" data-on-label="Yes">
															<input type="radio" class="toggle" id="alphaNumericRollNumber"/>
															<input type="hidden" name="customer.alphaNumericRollNumber" value="N">
													</div>
											  </s:if>
											  <s:else>
											         <div class="make-switch has-switch" data-id="Y" data-value="N" style="width: 180px" data-off="warning"
														data-on="success" data-off-label="No" data-on-label="Yes">
														<s:if test='%{customer.alphaNumericRollNumber == "Y"}'>
															<input type="radio" class="toggle" id="alphaNumericRollNumber"  checked="checked" disabled="disabled" />
														</s:if>
														<s:else>
															<input type="radio" class="toggle" id="alphaNumericRollNumber" disabled="disabled" />
														</s:else>
														<input type="hidden" name="customer.alphaNumericRollNumber" value="<s:property value="customer.alphaNumericRollNumber"/>">
													</div>
											</s:else>
												</div>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-bordered form-row-stripped">
											<h4 class="pageTitle bold form-section">ID Card Settings</h4>
												<div class="form-group">
													<label class="control-label col-md-3">Do you want to show barcode in ID cards : </label>
													 <div class="col-md-3">
														<s:if test='%{customer.barcodeStatus == "N" || customer.barcodeStatus =="" || customer.barcodeStatus =="null"}'>
															<div class="make-switch has-switch" data-id="Y" data-value="N" style="width: 120px" 
																data-off="warning" data-on="success" data-off-label="No" data-on-label="Yes">
																<input type="radio" class="toggle" id="barcodeStatus">
																<input type="hidden" name="customer.barcodeStatus" value="N">
															</div>
														</s:if>
														<s:else>
															<div class="make-switch has-switch" data-id="Y" data-value="N" style="width: 180px" 
																data-off="warning" data-on="success" data-off-label="No" data-on-label="Yes">
																<s:if test='%{customer.barcodeStatus=="Y"}'>
																	<input type="radio" class="toggle" id="barcodeStatus" disabled="disabled" checked="checked">
																</s:if>
																<s:else>
																	<input type="radio" class="toggle" id="barcodeStatus" disabled="disabled">
																</s:else>
																<input type="hidden" name="customer.barcodeStatus" value="<s:property value="customer.barcodeStatus"/>">
															</div>
														</s:else>
													</div>
												</div>
												
												<div class="form-group">
													<label class="control-label col-md-3">Do you want to show blood group in ID cards : </label>
													<div class="col-md-7">
														<div class="make-switch has-switch" data-id="Y"
																data-value="N" style="width: 120px" data-off="warning"
																data-on="success" data-off-label="No" data-on-label="Yes">
																<s:if test='%{customer.displayBloogGroupInStudentIdCards == "Y"}'>
																	<input type="radio" class="toggle" checked="checked" id="displayBloogGroupInStudentIdCards">
																</s:if>
																<s:else>
																	<input type="radio" class="toggle" id="displayBloogGroupInStudentIdCards">
																</s:else>
																<input type="hidden" name="customer.displayBloogGroupInStudentIdCards" value='<s:property value="customer.displayBloogGroupInStudentIdCards"/>'>
															</div>
													</div>
											</div>
										
										</div>
									</div>
									<div class="form-bordered form-row-stripped">
											<h4 class="pageTitle bold form-section">Account Module</h4>
												<div class="form-group">
													<label class="control-label col-md-3">Is Account Module Using :</label>
													 <div class="col-md-3">
														<s:if test='%{customer.accountModuleUsing == "N"}'>
															<div class="make-switch has-switch" data-id="Y" data-value="N"
																style="width: 120px" data-off="warning" data-on="success"
																data-off-label="No" data-on-label="Yes">
																<input type="radio" class="toggle" id="accountModuleUsing">
																<input type="hidden" name="customer.accountModuleUsing" value="N">
															</div>
														</s:if>
														<s:else>
															<div class="make-switch has-switch" data-id="Y" data-value="N"
																style="width: 180px" data-off="warning" data-on="success"
																data-off-label="No" data-on-label="Yes">
																<s:if test='%{customer.accountModuleUsing=="Y"}'>
																	<input type="radio" class="toggle" id="accountModuleUsing"
																		disabled="disabled" checked="checked">
																</s:if>
																<s:else>
																	<input type="radio" class="toggle" id="accountModuleUsing" disabled="disabled">
																</s:else>
																<input type="hidden" name="customer.accountModuleUsing" value="<s:property value="customer.accountModuleUsing"/>">
															</div>
														</s:else>
													</div>
												</div>
										</div>
									<div class="form-bordered form-row-stripped">
										<h4 class="pageTitle bold form-section">Fee Settings</h4>
										<div class="form-group">
													<label class="control-label col-md-3">Allow Fee Discount option for Finance Role :</label>
													 <div class="col-md-7" id="feeDiscount">
														<s:if test='%{customer.allowDiscountOptOnOtherRoles == "N"}'>
															<div class="make-switch has-switch" data-id="Y" data-value="N"
																style="width: 120px" data-off="warning" data-on="success"
																data-off-label="No" data-on-label="Yes">
																<input type="radio" class="toggle" id="allowDiscountOptOnOtherRoles" >
																<input type="hidden" name="customer.allowDiscountOptOnOtherRoles" value="N" id="feeDiscountFinaceRole">
															</div>
														</s:if>
														<s:else>
															<div class="make-switch has-switch" data-id="Y" data-value="N"
																style="width: 180px" data-off="warning" data-on="success"
																data-off-label="No" data-on-label="Yes">
																<s:if test='%{customer.allowDiscountOptOnOtherRoles=="Y"}'>
																	<input type="radio" class="toggle" id="allowDiscountOptOnOtherRoles"
																		 checked="checked" >
																</s:if>
																<input type="hidden" name="customer.allowDiscountOptOnOtherRoles" value="<s:property value="customer.allowDiscountOptOnOtherRoles"/>" id="feeDiscountFinaceRole">
															</div>
														</s:else>
													<span class="help-block">(If you select Yes, system we will allow to give discount other than 'Admin' also.)</span>
													</div>	
										</div>
										<div class="form-group">
													<label class="control-label col-md-3">Allow past dates for payments :</label>
													 <div class="col-md-7" id="paymentPastDates">
														<s:if test='%{customer.allowPastDatesForPayments == "N"}'>
															<div class="make-switch has-switch" data-id="Y" data-value="N"
																style="width: 120px" data-off="warning" data-on="success"
																data-off-label="No" data-on-label="Yes">
																<input type="radio" class="toggle" id="allowPastDatesForPayments" >
																<input type="hidden" name="customer.allowPastDatesForPayments" value="N" id="pastDatesForPayments">
															</div>
														</s:if>
														<s:else>
															<div class="make-switch has-switch" data-id="Y" data-value="N"
																style="width: 180px" data-off="warning" data-on="success"
																data-off-label="No" data-on-label="Yes">
																<s:if test='%{customer.allowPastDatesForPayments=="Y"}'>
																	<input type="radio" class="toggle" id="allowPastDatesForPayments"
																		 checked="checked" >
																</s:if>
																<input type="hidden" name="customer.allowPastDatesForPayments" value="<s:property value="customer.allowPastDatesForPayments"/>" id="pastDatesForPayments">
															</div>
														</s:else>
													<span class="help-block">(If you select Yes, system will allow to collect fee for previous days)</span>
													</div>	
										</div>	
										<div class="form-group" id="paymentDatesId">
											<label class="control-label col-md-3"> No of days : </label>
											<div class="col-md-3">
												<sj:textfield name="customer.allowedPastDatesForPayments"
													id="countOfPaymentsDates" cssClass="form-control input-medium" maxlength="3"></sj:textfield>
											</div>
										</div>
										<div class="form-group">
											<label class="control-label col-md-3"> Fee receipt short code : </label>
											<div class="col-md-3">
												<sj:textfield name="customer.feeReceiptNoWithCustName"
													id="feeReceiptNo" cssClass="form-control input-medium" maxlength="6"></sj:textfield>
											</div>
										</div>
										<s:if test="%{customer.startInvoiceNumber != 0}">
											<div class="form-group">
												<label class="control-label col-md-3">Customer Invoice Number :</label>
												<div class="col-md-3">
													<sj:textfield name="customer.startInvoiceNumber" id="startInvoiceNumber" tabindex="24" cssClass="numeric form-control input-medium" maxlength="20" disabled="true"></sj:textfield>
												</div>
											</div>
										</s:if>
										<s:else>
											<div class="form-group">
												<label class="control-label col-md-3">Customer Invoice Number :</label>
												<div class="col-md-3">
													<sj:textfield name="customer.startInvoiceNumber" id="startInvoiceNumber" tabindex="24" cssClass="numeric form-control input-medium" maxlength="20"></sj:textfield>
												</div>
											</div>
									  </s:else>
									 <!--  @Ganesh - This removed from because we are getting questions from customer about term and particualr wise payment and as per analization and testing team inputs we are disabling this future and default we will enable particular wise payment
									 <div class="form-group">
											<label class="control-label col-md-3">Payment Type :</label>
											<div class="col-md-5">
												<div class="radio-list">
													<label class="radio-inline"> <input type="radio"
														value="T" id="custPaytype1" name="customer.paymentType">
														Term wise
													</label> <label class="radio-inline"> <input type="radio"
														value="P" id="custPaytype" name="customer.paymentType">
														Particular wise
													</label>
												</div>
											</div>
										</div> -->
										 <div class="form-group">
											<label class="control-label col-md-3">Is committed fee applicable :</label>
											<div class="col-md-3">
												<div class="make-switch has-switch" data-id="Y"
													data-value="N" style="width: 120px" data-off="warning"
													data-on="success" data-off-label="No" data-on-label="Yes">
													<s:if test="%{(tempBoolean==false)}">
														<s:if test='%{customer.committedFeeStatus == "Y"}'>
															<input type="radio" class="toggle" checked="checked"
																id="committedFeeStatus" disabled="disabled">
														</s:if>
														<s:else>
															<input type="radio" class="toggle"
																id="committedFeeStatus" disabled="disabled">
														</s:else>
													</s:if>
													<s:else>
														<s:if test='%{customer.committedFeeStatus == "Y"}'>
															<input type="radio" class="toggle" checked="checked"
																id="committedFeeStatus">
														</s:if>
														<s:else>
															<input type="radio" class="toggle"
																id="committedFeeStatus">
														</s:else>
													</s:else>
	
													<input type="hidden" name="customer.committedFeeStatus"
														value='<s:property value="customer.committedFeeStatus"/>'>
												</div>
											</div>
										</div>
										<div class="form-group">
												<label class="control-label col-md-3">Generate Fee receipt number by academic year wise :</label>
												<div class="col-md-7">
													<div class="make-switch has-switch" data-id="true"
															data-value="false" style="width: 120px" data-off="warning"
															data-on="success" data-off-label="No" data-on-label="Yes">
															<s:if test='%{customer.academicWiseFeeReceipt}'>
																<input type="radio" class="toggle" checked="checked"
																	id="FeeReceipt">
															</s:if>
															<s:else>
																<input type="radio" class="toggle" id="FeeReceipt">
															</s:else>
															<input type="hidden"
																name="customer.academicWiseFeeReceipt"
																value='<s:property value="customer.academicWiseFeeReceipt"/>'>
														</div>
														<span class="help-block">(If you select academic
															year wise fee receipt number system will take starting fee
															receipt number as 1.)</span>
												</div>
										</div>	
										
										<div class="row form-group">
											<label class="col-md-3 control-label">
												Fee Receipt Model : </label>
											<div class="col-md-9">
												<div class="radio-list">
												<s:if test='%{customer.feeReceiptModel == "General"}'>
													<label class="radio-inline"> <input type="radio" checked="checked"
														value="General" name="customer.feeReceiptModel">General
													</label>
												</s:if>
												<s:else>
													<label class="radio-inline"> <input type="radio"
														value="General" name="customer.feeReceiptModel">General
													</label>
												</s:else>
												<s:if test='%{customer.feeReceiptModel == "A3"}'>
													<label class="radio-inline"> <input type="radio" checked="checked"
														value="A3" name="customer.feeReceiptModel">A3
													</label>
												</s:if>
												<s:else>
													<label class="radio-inline"> <input type="radio"
														value="A3" name="customer.feeReceiptModel">A3
													</label>
												</s:else>
												<s:if test='%{customer.feeReceiptModel == "A4"}'>
													<label class="radio-inline"> <input type="radio" checked="checked"
														value="A4" name="customer.feeReceiptModel">A4
													</label>
												</s:if>
												<s:else>
													<label class="radio-inline"> <input type="radio"
														value="A4" name="customer.feeReceiptModel">A4
													</label>
												</s:else>
												<s:if test='%{customer.feeReceiptModel == "A5"}'>
													<label class="radio-inline"> <input type="radio" checked="checked"
														value="A5" name="customer.feeReceiptModel">A5
													</label>
												</s:if>
												<s:else>
													<label class="radio-inline"> <input type="radio"
														value="A5" name="customer.feeReceiptModel">A5
													</label>
												</s:else>
												<s:if test='%{customer.feeReceiptModel == "A6"}'>
													<label class="radio-inline"> <input type="radio" checked="checked"
														value="A6" name="customer.feeReceiptModel">A6
													</label>
												</s:if>
												<s:else>
													<label class="radio-inline"> <input type="radio"
														value="A6" name="customer.feeReceiptModel">A6
													</label>
												</s:else>
												</div>
											</div>
										</div>
										<div class="form-group">
												<label class="control-label col-md-3">Generate Fee receipt With Address : </label>
												<div class="col-md-7">
													<div class="make-switch has-switch" data-id="Y"
															data-value="N" style="width: 120px" data-off="warning"
															data-on="success" data-off-label="No" data-on-label="Yes">
															<s:if test='%{customer.showAddreesInFeeReceipt =="Y"}'>
																<input type="radio" class="toggle" checked="checked" id="showAddreesInFeeReceipt">
															</s:if>
															<s:else>
																<input type="radio" class="toggle" id="showAddreesInFeeReceipt">
															</s:else>
															<input type="hidden"
																name="customer.showAddreesInFeeReceipt"
																value='<s:property value="customer.showAddreesInFeeReceipt"/>'>
														</div>
														<span class="help-block">(As per this selection, address will be shown in a fee receipt.)</span>
												</div>
										</div>
										<div class="form-group">
											 <label class="control-label col-md-3">Generate fee receipt with term wise balance amount :</label>
													 <div class="col-md-7">
														<s:if test='%{customer.preferences.visibleTermWiseBalanceAmount == "N"}'>
															<div class="make-switch has-switch" data-id="Y" data-value="N"
																style="width: 120px" data-off="warning" data-on="success"
																data-off-label="No" data-on-label="Yes">
																<input type="radio" class="toggle" id="visibleTermWiseBalanceAmount">
																<input type="hidden" name="customer.preferences.visibleTermWiseBalanceAmount" value="N">
															</div>
														</s:if>
														<s:else>
															<div class="make-switch has-switch" data-id="Y" data-value="N"
																style="width: 180px" data-off="warning" data-on="success"
																data-off-label="No" data-on-label="Yes">
																<s:if test='%{customer.preferences.visibleTermWiseBalanceAmount=="Y"}'>
																	<input type="radio" class="toggle" id="visibleTermWiseBalanceAmount"
																		 checked="checked">
																</s:if>
																<input type="hidden" name="customer.preferences.visibleTermWiseBalanceAmount" value="<s:property value="customer.preferences.visibleTermWiseBalanceAmount"/>">
															</div>
														</s:else>
														<span class="help-block">(As per this selection, term wise balance will be shown in a fee receipt.)</span>
												</div>
										</div>	
										<div class="form-group">
												<label class="control-label col-md-3">Generate fee receipt with total balance amount :</label>
												<div class="col-md-7">
													<div class="make-switch has-switch" data-id="Y"
															data-value="N" style="width: 120px" data-off="warning"
															data-on="success" data-off-label="No" data-on-label="Yes">
															<s:if test='%{customer.showBalanceAmountInFeeReceipt == "Y"}'>
																<input type="radio" class="toggle" checked="checked" id="showBalanceAmountInFeeReceipt">
															</s:if>
															<s:else>
																<input type="radio" class="toggle" id="showBalanceAmountInFeeReceipt">
															</s:else>
															<input type="hidden"
																name="customer.showBalanceAmountInFeeReceipt"
																value='<s:property value="customer.showBalanceAmountInFeeReceipt"/>'>
														</div>
														<span class="help-block">(As per this selection, total balance will be shown in a fee receipt.)</span>
												</div>
										</div>
										<div class="form-group">
												<label class="control-label col-md-3">Generate Chalana In Student Payment :</label>
												<div class="col-md-7">
													<div class="make-switch has-switch" data-id="Y"
															data-value="N" style="width: 120px" data-off="warning"
															data-on="success" data-off-label="No" data-on-label="Yes">
															<s:if test='%{customer.chalanaGenerationStatus == "Y"}'>
																<input type="radio" class="toggle" checked="checked" id="chalanaGenerationStatus">
															</s:if>
															<s:else>
																<input type="radio" class="toggle" id="chalanaGenerationStatus">
															</s:else>
															<input type="hidden" name="customer.chalanaGenerationStatus" value='<s:property value="customer.chalanaGenerationStatus"/>'>
														</div>
														<span class="help-block">(If you select Yes we will show generate chalana option in student invoice pay other wise we will not show in payment screen.)</span>
												</div>
										</div>
										<div id="feeRefund">
											<div class="form-group">
												<label class="control-label col-md-3"> Allow Refund
													:
												</label>
												<div class="col-md-3">
														<s:if test="%{(customer.preferences.feeRefund)}">
															<div class="make-switch has-switch" data-id="true"
																data-value="false" style="width: 180px" data-off="warning"
																data-on="success" data-off-label="No" data-on-label="Yes">
																<input type="radio" class="toggle" id="feeRefund"
																	checked="checked"> <input type="hidden"
																	name="customer.preferences.feeRefund" value="true">
															</div>
														</s:if>
														<s:else>
															<div class="make-switch has-switch" data-id="true"
																data-value="false" style="width: 180px" data-off="warning"
																data-on="success" data-off-label="No" data-on-label="Yes">
																<s:if test='%{customer.preferences.feeRefund=="true"}'>
																	<input type="radio" class="toggle" id="feeRefund"
																		checked="checked"  />
																</s:if>
																<s:else>
																	<input type="radio" class="toggle" id="feeRefund" />
																</s:else>
																<input type="hidden"
																	name="customer.preferences.feeRefund"
																	value="<s:property value="customer.preferences.feeRefund"/>">
															</div>
														</s:else>
													</div>
											</div>
										</div>
									</div>
									<div class="form-bordered form-row-stripped">
											<h4 class="pageTitle bold form-section">Permission Settings</h4>
												<div class="form-group">
													<label class="control-label col-md-3">Student / Parent Permissions :</label>
													 <div class="col-md-3">
														<s:if test='%{customer.parentPermissionStatus == "N"}'>
															<div class="make-switch has-switch" data-id="Y" data-value="N"
																style="width: 120px" data-off="warning" data-on="success"
																data-off-label="No" data-on-label="Yes">
																<input type="radio" class="toggle" id="parentPermissionStatus">
																<input type="hidden" name="customer.parentPermissionStatus" value="N">
															</div>
														</s:if>
														<s:else>
															<div class="make-switch has-switch" data-id="Y" data-value="N"
																style="width: 180px" data-off="warning" data-on="success"
																data-off-label="No" data-on-label="Yes">
																<s:if test='%{customer.parentPermissionStatus=="Y"}'>
																	<input type="radio" class="toggle" id="parentPermissionStatus"
																		 checked="checked">
																</s:if>
																<s:else>
																	<input type="radio" class="toggle" id="parentPermissionStatus">
																</s:else>
																<input type="hidden" name="customer.parentPermissionStatus" value="<s:property value="customer.parentPermissionStatus"/>">
															</div>
														</s:else>
													</div>
												</div>
												<div class="form-group">
													<label class="control-label col-md-3">Staff Permissions :</label>
													 <div class="col-md-3">
														<s:if test='%{customer.staffPermissionStatus == "N"}'>
															<div class="make-switch has-switch" data-id="Y" data-value="N"
																style="width: 120px" data-off="warning" data-on="success"
																data-off-label="No" data-on-label="Yes">
																<input type="radio" class="toggle" id="staffPermissionStatus">
																<input type="hidden" name="customer.staffPermissionStatus" value="N">
															</div>
														</s:if>
														<s:else>
															<div class="make-switch has-switch" data-id="Y" data-value="N"
																style="width: 180px" data-off="warning" data-on="success"
																data-off-label="No" data-on-label="Yes">
																<s:if test='%{customer.staffPermissionStatus=="Y"}'>
																	<input type="radio" class="toggle" id="staffPermissionStatus"
																		 checked="checked">
																</s:if>
																<s:else>
																	<input type="radio" class="toggle" id="staffPermissionStatus">
																</s:else>
																<input type="hidden" name="customer.staffPermissionStatus" value="<s:property value="customer.staffPermissionStatus"/>">
															</div>
														</s:else>
													</div>
												</div>
										</div>
										<div class="form-bordered form-row-stripped">
											<h4 class="pageTitle bold form-section">Preferences</h4>
												<div class="form-group">
													<label class="control-label col-md-3">Parent Mobile Number Visible To Teacher/Class Teacher :</label>
													 <div class="col-md-3">
													 
														<s:if test='%{customer.preferences.parentMobileNoVisibleToTeacher == "N"}'>
															<div class="make-switch has-switch" data-id="Y" data-value="N"
																style="width: 120px" data-off="warning" data-on="success"
																data-off-label="No" data-on-label="Yes">
																<input type="radio" class="toggle" id="parentMobileNoVisibleToTeacher">
																<input type="hidden" name="customer.preferences.parentMobileNoVisibleToTeacher" value="N">
															</div>
														</s:if>
														<s:else>
															<div class="make-switch has-switch" data-id="Y" data-value="N"
																style="width: 180px" data-off="warning" data-on="success"
																data-off-label="No" data-on-label="Yes">
																<s:if test='%{customer.preferences.parentMobileNoVisibleToTeacher=="Y"}'>
																	<input type="radio" class="toggle" id="parentMobileNoVisibleToTeacher"
																		 checked="checked">
																</s:if>
																<input type="hidden" name="customer.preferences.parentMobileNoVisibleToTeacher" value="<s:property value="customer.preferences.parentMobileNoVisibleToTeacher"/>">
															</div>
														</s:else>
														
													</div>
												</div>
												<div class="form-group">
													<label class="control-label col-md-3">Show Fee Balance Amount In Payment SMS :</label>
													 <div class="col-md-3">
													 
														<s:if test='%{customer.preferences.feeBalanceAmountInPaymnetSMS == "N"}'>
															<div class="make-switch has-switch" data-id="Y" data-value="N"
																style="width: 120px" data-off="warning" data-on="success"
																data-off-label="No" data-on-label="Yes">
																<input type="radio" class="toggle" id="feeBalanceAmountInPaymnetSMS">
																<input type="hidden" name="customer.preferences.feeBalanceAmountInPaymnetSMS" value="N">
															</div>
														</s:if>
														<s:else>
															<div class="make-switch has-switch" data-id="Y" data-value="N"
																style="width: 180px" data-off="warning" data-on="success"
																data-off-label="No" data-on-label="Yes">
																<s:if test='%{customer.preferences.feeBalanceAmountInPaymnetSMS=="Y"}'>
																	<input type="radio" class="toggle" id="feeBalanceAmountInPaymnetSMS"
																		 checked="checked">
																</s:if>
																<input type="hidden" name="customer.preferences.feeBalanceAmountInPaymnetSMS" value="<s:property value="customer.preferences.feeBalanceAmountInPaymnetSMS"/>">
															</div>
														</s:else>
														
													</div>
												</div>
												<div class="form-group">
													<label class="control-label col-md-3">Show Fee Information in Parent/Student Login :</label>
													 <div class="col-md-3">
													 
														<s:if test='%{customer.preferences.visibleFeeInfoToParent == "N"}'>
															<div class="make-switch has-switch" data-id="Y" data-value="N"
																style="width: 120px" data-off="warning" data-on="success"
																data-off-label="No" data-on-label="Yes">
																<input type="radio" class="toggle" id="visibleFeeInfoToParent">
																<input type="hidden" name="customer.preferences.visibleFeeInfoToParent" value="N">
															</div>
														</s:if>
														<s:else>
															<div class="make-switch has-switch" data-id="Y" data-value="N"
																style="width: 180px" data-off="warning" data-on="success"
																data-off-label="No" data-on-label="Yes">
																<s:if test='%{customer.preferences.visibleFeeInfoToParent=="Y"}'>
																	<input type="radio" class="toggle" id="visibleFeeInfoToParent"
																		 checked="checked">
																</s:if>
																<input type="hidden" name="customer.preferences.visibleFeeInfoToParent" value="<s:property value="customer.preferences.visibleFeeInfoToParent"/>">
															</div>
														</s:else>
														
													</div>
												</div>												
												<div class="form-group">
													<label class="control-label col-md-3">Fee Payment Notifications :</label>
													 <div class="col-md-3" id="paymentNotification">
														<s:if test='%{customer.preferences.feePaymentNotificationToManagement == "N"}'>
															<div class="make-switch has-switch" data-id="Y" data-value="N"
																style="width: 120px" data-off="warning" data-on="success"
																data-off-label="No" data-on-label="Yes">
																<input type="radio" class="toggle" id="feePaymentNotificationToManagement" >
																<input type="hidden" name="customer.preferences.feePaymentNotificationToManagement" value="N" id="feePaymentNotification">
															</div>
														</s:if>
														<s:else>
															<div class="make-switch has-switch" data-id="Y" data-value="N"
																style="width: 180px" data-off="warning" data-on="success"
																data-off-label="No" data-on-label="Yes">
																<s:if test='%{customer.preferences.feePaymentNotificationToManagement=="Y"}'>
																	<input type="radio" class="toggle" id="feePaymentNotificationToManagement"
																		 checked="checked" >
																</s:if>
																<input type="hidden" name="customer.preferences.feePaymentNotificationToManagement" value="<s:property value="customer.preferences.feePaymentNotificationToManagement"/>" id="feePaymentNotification">
															</div>
														</s:else>
													</div>	
												</div>	
												<div class="form-group" id="paymentManagemntRoleId">
												<label class="col-md-2 control-label">
														<span class="required">*</span>Select Roles : 
												</label>
														<div class="col-md-3" >
														<s:checkboxlist name="selectedRolesList" list="rolesList"
																	listKey="id" listValue="name" theme="ems" />
														</div>
													</div>
													
												<div class="form-group">
													<label class="control-label col-md-3">Administrator Approval Required for Class Teacher Created SMS :</label>
													 <div class="col-md-3" id="approvalRequiredForClassTeacherCreatedSMS">
														<s:if test='%{customer.preferences.approvalRequiredForClassTeacherCreatedSMS == "N"}'>
															<div class="make-switch has-switch" data-id="Y" data-value="N"
																style="width: 120px" data-off="warning" data-on="success"
																data-off-label="No" data-on-label="Yes">
																<input type="radio" class="toggle" id="approvalRequiredForClassTeacherCreatedSMS" >
																<input type="hidden" name="customer.preferences.approvalRequiredForClassTeacherCreatedSMS" value="N" id="approvalRequiredForClassTeacherCreatedSMS">
															</div>
														</s:if>
														<s:else>
															<div class="make-switch has-switch" data-id="Y" data-value="N"
																style="width: 180px" data-off="warning" data-on="success"
																data-off-label="No" data-on-label="Yes">
																<s:if test='%{customer.preferences.approvalRequiredForClassTeacherCreatedSMS=="Y"}'>
																	<input type="radio" class="toggle" id="approvalRequiredForClassTeacherCreatedSMS"
																		 checked="checked" >
																</s:if>
																<input type="hidden" name="customer.preferences.approvalRequiredForClassTeacherCreatedSMS" value="<s:property value="customer.preferences.approvalRequiredForClassTeacherCreatedSMS"/>" id="approvalRequiredForClassTeacherCreatedSMS">
															</div>
														</s:else>
													</div>	
												</div>			
													
									
									<s:if
										test='%{(#session.previousYear == null || #session.previousYear.isEmpty()) || (#session.previousYear == "N")}'>
										<div class="form-actions fluid">
											<div class="col-md-offset-2 col-md-9">
												<sj:submit cssClass="btn blue" value="Submit" onBeforeTopics="addSchoolSettingsValidation" 
													targets="mainContentDiv" validate="true" />
											</div>
										</div>

									</s:if>
								</s:form>
							</div>
						</s:if>
						<s:else>
							<div class="alert alert-info">No information found for this
								customer.</div>
						</s:else>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/plugins/bootstrap-switch/static/js/bootstrap-switch.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("a#organizationName").html($("input#customerOrgName").val());
		$.destroyTopic('addSchoolSettingsValidation');
	changePageTitle('Manage School Details');
	FormAdvanced.init();
	FormComponents.init();
	$.subscribe('addSchoolSettingsValidation', function(event, data) {
		if (($('input[name=objectList]:checked').length) == 0){
			alert("Please select at least one syllabus Type");
			event.originalEvent.options.submit=false;	
		}
		var feePaymentNotification = $('#feePaymentNotification').val();
		if ("Y" == feePaymentNotification) {
		
			if (($('input[name=selectedRolesList]:checked').length) == 0){
				alert("Please select at least one role");
				event.originalEvent.options.submit=false;	
			}
		}
	});
	var booktype = '<s:property value="customer.paymentType"/>';
	if (booktype == 'T') {
		$('#custPaytype1').attr("checked", 'checked');
	} else if (booktype == 'P') {
		$('#custPaytype').attr("checked", 'checked');
	}
	$('.numeric').numeric();
	$("input:checkbox, input:radio:not('.toggle')").uniform();

	//For setting checkbox values 
	if (<s:property value='customer.checkEmailService'/>) {
		$('#emailService').parent('span').addClass('checked');
		$('#emailService').attr("checked", true);
	} else {
		$('#emailService').parent('span').removeClass('checked');
		$('#emailService').attr("checked", false);
	}
	if (<s:property value='customer.checkAttendanceEmailService'/>) {
		$('#emailAttendanceService').parent('span').addClass('checked');
		$('#emailAttendanceService').attr("checked", true);
	} else {
		$('#emailAttendanceService').parent('span').removeClass('checked');
		$('#emailAttendanceService').attr("checked", false);
	}
	
	if (<s:property value='customer.checkMobileService'/>) {
		$('#messagingService').parent('span').addClass('checked');
		$('#messagingService').attr("checked", true);
	} else {
		$('#messagingService').parent('span').removeClass('checked');
		$('#messagingService').attr("checked", false);
		//$('#messagingService').val("false");
	}
	if (<s:property value='customer.checkMobilePaymentService'/>) {
		$('#messagingPaymentService').parent('span').addClass('checked');
		$('#messagingPaymentService').attr("checked", true);
	} else {
		$('#messagingPaymentService').parent('span').removeClass('checked');
		$('#messagingPaymentService').attr("checked", false);
		//$('#messagingService').val("false");
	}
	
	if (<s:property value='customer.checkParentSmsService'/>) {
		$('#messagingParentService').parent('span').addClass('checked');
		$('#messagingParentService').attr("checked", true);
	} else {
		$('#messagingParentService').parent('span').removeClass('checked');
		$('#messagingParentService').attr("checked", false);
		//$('#messagingService').val("false");
	}
	if (<s:property value='customer.checkTransportService'/>) {
			$('#transportService').parent('span').addClass('checked');
			$('#transportService').attr("checked", true);
		} else {
			$('#transportService').parent('span').removeClass('checked');
			$('#transportService').attr("checked", false);
		}
	if (<s:property value='customer.checkAssignmentSmsService'/>) {
		$('#messagingAssignmentService').parent('span').addClass('checked');
		$('#messagingAssignmentService').attr("checked", true);
	} else {
		$('#messagingAssignmentService').parent('span').removeClass('checked');
		$('#messagingAssignmentService').attr("checked", false);
		//$('#messagingService').val("false");
	}
	//for generating confirm box
	//if ($('div input.changeServicesStatus')) {
	//$('div input.changeServicesStatus').unbind('click');
	$("div input.changeServicesStatus").click(function() {
		var smsOrEmailsService = $(this).val();
		var checkedService = ''; 
		var answer = '';
		if("SMS"==smsOrEmailsService){
			if($(this). prop("checked") == true){
				checkedService = " Are you sure you want to enable SMS service?";
			}else
				checkedService = "Are you sure you want to disable SMS service?if you click on OK you cannot send any SMS from your account. ";
		}else if("Emails"==smsOrEmailsService){
			if($(this). prop("checked") == true){
				checkedService = " Are you sure you want to enable Email service?";
			}else
				checkedService = "Are you sure you want to disable Email service?if you click on OK you cannot send any Email from your account. ";
		}
			if(isNonEmpty(checkedService) && checkedService !=null)
				 answer = confirm(checkedService);
			else
				answer ='undefined';//Here if we have set this value also this is coming but condition is not satisfied so i have set this value
				if(answer==true || answer=='undefined'){
					var thishref = $(this).attr('data');
					//var url = jQuery.url.getChatURL("/schoolfee/ajaxStudentPaymentPdfFineFeeReportPopup.do");
					$.ajax({
						url : thishref + ".do",
						cache : false,
						dataType : 'json',
						success : function(data) {
							// Nothing to do here.
							window.location.reload(); //this is used to reload decarator for the alert message.
						}
					});
				}
				else{
					if("SMS"==smsOrEmailsService){
						if (<s:property value='customer.checkMobileService'/>) {
							$('#messagingService').parent('span').addClass('checked');
							$('#messagingService').attr("checked", true);
						} else {
							$('#messagingService').parent('span').removeClass('checked');
							$('#messagingService').attr("checked", false);
							//$('#messagingService').val("false");
						}
						if (<s:property value='customer.checkMobilePaymentService'/>) {
							$('#messagingPaymentService').parent('span').addClass('checked');
							$('#messagingPaymentService').attr("checked", true);
						} else {
							$('#messagingPaymentService').parent('span').removeClass('checked');
							$('#messagingPaymentService').attr("checked", false);
							//$('#messagingService').val("false");
						}
						
						if (<s:property value='customer.checkParentSmsService'/>) {
							$('#messagingParentService').parent('span').addClass('checked');
							$('#messagingParentService').attr("checked", true);
						} else {
							$('#messagingParentService').parent('span').removeClass('checked');
							$('#messagingParentService').attr("checked", false);
							//$('#messagingService').val("false");
						}
						if (<s:property value='customer.checkTransportService'/>) {
								$('#transportService').parent('span').addClass('checked');
								$('#transportService').attr("checked", true);
							} else {
								$('#transportService').parent('span').removeClass('checked');
								$('#transportService').attr("checked", false);
							}
						if (<s:property value='customer.checkAssignmentSmsService'/>) {
							$('#messagingAssignmentService').parent('span').addClass('checked');
							$('#messagingAssignmentService').attr("checked", true);
						} else {
							$('#messagingAssignmentService').parent('span').removeClass('checked');
							$('#messagingAssignmentService').attr("checked", false);
							//$('#messagingService').val("false");
						}
					}else{
						if (<s:property value='customer.checkEmailService'/>) {
							$('#emailService').parent('span').addClass('checked');
							$('#emailService').attr("checked", true);
						} else {
							$('#emailService').parent('span').removeClass('checked');
							$('#emailService').attr("checked", false);
						}
						if (<s:property value='customer.checkAttendanceEmailService'/>) {
							$('#emailAttendanceService').parent('span').addClass('checked');
							$('#emailAttendanceService').attr("checked", true);
						} else {
							$('#emailAttendanceService').parent('span').removeClass('checked');
							$('#emailAttendanceService').attr("checked", false);
						}
						
					}
					return false;
				}
			//}
		
	});
	
	$('input[name="objectList"]').click(function(){
		
		if($(this).is(":checked"))
		{
			$('#syllabus'+$(this).val()).show();
		}
		else
		{
			$('#syllabus'+$(this).val()).hide();
		}
		//alert($(this).is(":checked") +"---"+$(this).val());
	});
	
	$("#messagingService").click(function() {
			if (!this.checked) {
				$('#messagingPaymentService').parent('span').removeClass('checked');
				$('#messagingPaymentService').attr('disabled', 'disabled');
				$('#messagingPaymentService').attr('checked', false);
				
				$('#messagingParentService').parent('span').removeClass('checked');
				$('#messagingParentService').attr('disabled', 'disabled');
				$('#messagingParentService').attr('checked', false);
				
				$('#transportService').parent('span').removeClass('checked');
				$('#transportService').attr('disabled', 'disabled');
				$('#transportService').attr('checked', false);
				
				$('#messagingAssignmentService').parent('span').removeClass('checked');
				$('#messagingAssignmentService').attr('disabled', 'disabled');
				$('#messagingAssignmentService').attr('checked', false);
				
				
			} else{
				$('#messagingPaymentService').removeAttr('disabled');
				$('#messagingParentService').removeAttr('disabled');
				$('#transportService').removeAttr('disabled');
				$('#messagingAssignmentService').removeAttr('disabled');
			}
	});
	if (!$("#messagingService").parent('span').hasClass('checked')) {
		$('#messagingPaymentService').parent('span').removeClass('checked');
		$('#messagingPaymentService').attr('disabled','disabled');
		
		$('#messagingParentService').parent('span').removeClass('checked');
		$('#messagingParentService').attr('disabled','disabled');
		
		$('#transportService').parent('span').removeClass('checked');
		$('#transportService').attr('disabled','disabled');
		
		$('#messagingAssignmentService').parent('span').removeClass('checked');
		$('#messagingAssignmentService').attr('disabled','disabled');
	}
	
	$("#emailService").click(function() {
		if (!this.checked) {
			$('#emailAttendanceService').parent('span').removeClass('checked');
			$('#emailAttendanceService').attr('disabled', 'disabled');
			$('#emailAttendanceService').attr('checked', false);
						
		} else{
			$('#emailAttendanceService').removeAttr('disabled');
			}
});
if (!$("#emailService").parent('span').hasClass('checked')) {
	$('#emailAttendanceService').parent('span').removeClass('checked');
	$('#emailAttendanceService').attr('disabled','disabled');
		
}

	  var imagePath = '<s:property value="customer.customerLogo"/>';
		if(isNonEmpty(imagePath) && imagePath !=null && imagePath != "/images/common/photo_notAvailable.jpg"){
			$('#customerLogoDiv').prop('src', imagePath + '?' + Math.random())
		}  
		var countryId = $("select#country").val();
		if(countryId=="IN"){
			$("#countryState").show();
		}else{
			$('#state').val('');
		}
		var feePaymentNotification = $('#feePaymentNotification').val();
		if ("Y" == feePaymentNotification) {
			getFeePaymentNotificationDetails(false);
		}else{
			getFeePaymentNotificationDetails(true);
			
		}
		var allowPastDatesForPayments = $('#pastDatesForPayments').val();
		if ("Y" == allowPastDatesForPayments) {
			getAllowedPaymentDates(false);
		}else{
			getAllowedPaymentDates(true);
		}
		
});
	function getFeePaymentNotificationDetails(status){
		if(status == true){
			$("#paymentManagemntRoleId").hide();
		}else{
			$("#paymentManagemntRoleId").show();
		}
	}
	function getAllowedPaymentDates(status){
		if(status == true){
			$("#paymentDatesId").hide();
		}else{
			$("#paymentDatesId").show();
		}
	}
	function getCountryState(selectBox) {
		var countryId = $("select#country").val();
		if(countryId=="IN"){
			$("#countryState").show();
		}else{
			$("#countryState").hide();
			$('#state').val('');
		}
	}
	$('#contactNumber').bind('input', function() {
		  $(this).val($(this).val().replace(/[^0-9-]/gi, ''));
		});
	
	$("div#paymentNotification").find('div.switch-animate').click(function(){
		if($(this).hasClass("switch-off")==true){
			getFeePaymentNotificationDetails(true);
			}else{
				getFeePaymentNotificationDetails(false);
			}
	});
	$("div#paymentNotification").find('div.switch-animate').find("label").click(function(){
		if($(this).parent("div").hasClass("switch-off")==true){
			getFeePaymentNotificationDetails(false);
			}else{
				getFeePaymentNotificationDetails(true);
			}
	});
	$("div#paymentPastDates").find('div.switch-animate').click(function(){
		if($(this).hasClass("switch-off")==true){
			getAllowedPaymentDates(true);
			}else{
				getAllowedPaymentDates(false);
			}
	});
	$("div#paymentPastDates").find('div.switch-animate').find("label").click(function(){
		if($(this).parent("div").hasClass("switch-off")==true){
			getAllowedPaymentDates(false);
			}else{
				getAllowedPaymentDates(true);
			}
	});
</script>
