<%@ include file="/common/taglibs.jsp"%>
<div class="col-md-12"  id="editProfileContntDiv">
	<div class="tabbable tabbable-custom tabbable-full-width">
		<ul class="nav nav-tabs">
			<li id="accountTab">
				<a href="#tab_1_3" data-toggle="tab">Account</a>
			</li>
			<li class="active" id="profileTab">
				<a href="#tab_1_1" data-toggle="tab">Profile Info</a>
			</li>
		</ul>
		<div class="tab-content">
		<jsp:include page="/common/messages.jsp"></jsp:include>
			<div class="tab-pane active profile-classic row" id="tab_1_1">
				<div class="col-md-3">
				<div class="profile-userpic">
						<s:if test="%{user.profileImage.adjustedAttachmentFilePath != null &&  user.profileImage.adjustedAttachmentFilePath != ''}">
							<img
								src='<c:url value="${user.profileImage.adjustedAttachmentFilePath}"/>'
								alt='<s:property  value="user.fullPersonName" />' border="0" style="width: 180px;" id="userProfileDiv" class='imagePathNotFound'/>
						</s:if>
						<s:elseif test="%{student.profileImage.adjustedAttachmentFilePath != null &&  student.profileImage.adjustedAttachmentFilePath != ''}">
							<img
								src='<c:url value="${student.profileImage.adjustedAttachmentFilePath}"/>'
								alt='<s:property  value="user.fullPersonName" />' border="0" style="width: 180px;" id="studentProfileDiv"/>
						</s:elseif>
						<s:else>
							<img alt=""  src="../img/nophoto.jpg" style="width: 180px;100px;">
						</s:else>
					</div>
				</div>
				<div class="col-md-9">
					<div class="row">
						<ul>
							<li>
								<span>User Name :</span>
								<s:property value="user.username" />
							</li>
							<li>
								<span>Name :</span>
								<%-- <s:if test='%{user.isParent=="Y"}'>
									<s:property value="user.person.fatherName" />
								</s:if>
								<s:else> --%>
									<s:property value="user.person.firstName" />&nbsp;&nbsp;
									<s:property value="user.person.lastName" />
								<%-- </s:else> --%>
							</li>
							<li>
								<span>Birthday :</span>
								<s:property value="user.person.dateOfBirthStr" />
							</li>
							<s:elseif test='%{user.isSchoolStudent=="Y"}'>
								<li>
									<span>Father Name :</span>
									<s:property value="user.person.fatherName" />
								</li>
							</s:elseif>
							<s:if test='%{user.isSchoolTeacher=="Y"}'>
							<li>
									<span>Qualification :</span>
									<s:property value="staff.qualification1" />
								</li>
							</s:if>
							<li>
								<span>Country :</span>
								<s:property value="tempString"/>
							</li>
							<li>
							<span>Email :</span>
								<s:if test='%{user.isParent=="Y"}'>
									<s:property value="user.person.parentEmail" />
								</s:if>
								<s:if test='%{user.isSchoolStudent=="Y"}'>
									<s:property value="user.person.studentEmail" />
								</s:if>
								<s:else>
									<s:property value="user.primaryAddress.email" />
								</s:else>
							</li>
							<li>
								<span>Website Url :</span>
								<a href="#">http://eazyschool.in</a>
							</li>
							<li>
								<span>Mobile Number :</span>
								<s:if test='%{user.isSchoolStudent=="Y"}'>
									<s:property value="user.person.studentMobile" />
								</s:if>
								<s:else>
									<s:property value="user.person.mobileNumber" />
								</s:else>
							</li>
							<li>
								<span>About :</span>
								<s:property value="user.person.summary" />
							</li>
						</ul>
					</div>
				</div>
			</div>
			<div class="tab-pane" id="tab_1_3">
				<div class="row profile-account">
					<div class="col-md-3">
						<ul class="ver-inline-menu tabbable margin-bottom-10">
							<%-- <s:if test='%{user.isSchoolTeacher == "Y"}'>
								<li class="active">
									<a data-toggle="tab" href="#tab_3-3"><i class="fa fa-lock"></i>
										Change Password</a>
								</li>
							</s:if>
							<s:else> --%>
								<li class="active">
									<a data-toggle="tab" href="#tab_1-1"> <i class="fa fa-cog"></i>
										Personal info </a>
									<span class="after"> </span>
								</li>
								<li>
									<a data-toggle="tab" href="#tab_2-2"><i
										class="fa fa-picture-o"></i> Change Photo</a>
								</li>
								<li>
									<a data-toggle="tab" href="#tab_3-3"><i class="fa fa-lock"></i>
										Change Password</a>
								</li>
							<%-- </s:else> --%>
						</ul>
					</div>
					<div class="col-md-9">
						<div class="tab-content">
							<%-- <s:if test='%{user.isSchoolTeacher != "Y"}'> --%>
							 <div id="tab_1-1" class="tab-pane active">
								<s:form action="ajaxEditProfile" id="changePersonalDetailsForm"
									method="post" enctype="multipart/form-data" theme="simple"
									cssClass="form-horizontal" namespace="/user">
									<s:hidden name="user.id" />
									<h5 class="pageTitle bold form-section">
										PERSONAL INFORMATION
									</h5>
									<s:if
										test='%{user.isOnlySchoolAdmin=="Y" || user.isSchoolPrincipal=="Y" || user.isSchoolDirector == "Y"}'>
										<div class="row">
											<div class="col-md-6">
												<div class="form-group">
													<label class="control-label col-md-4">
														<span class="required">*</span>First Name :
													</label>
													<div class="col-md-7">
														<sj:textfield name="user.person.firstName" id="firstName"
															cssClass="required form-control input-medium as-input"
															maxlength="50"></sj:textfield>
													</div>
												</div>
											</div>
											<div class="col-md-6">
												<div class="form-group">
													<label class="control-label col-md-4">
														Last Name :
													</label>
													<div class="col-md-8">
														<sj:textfield name="user.person.lastName" id="lastName"
															cssClass="form-control input-medium as-input"
															maxlength="50"></sj:textfield>
													</div>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-md-6">
												<div class="form-group">
													<label class="control-label col-md-4">
														Date of Birth :
													</label>
													<div class="col-md-7">
													<s:if test='%{user.person.dateOfBirth==empty}'>
														<div class="input-group input-medium date date-picker">
															<input type="text" readonly="readonly"
																class="form-control fdate" id="staffDate"
																name="user.person.dateOfBirth">
															<span class="input-group-btn">
																<button type="button" class="btn default">
																	<i class="fa fa-calendar"></i>
																</button> </span>
														</div>
														<span class="help-block">(MM/DD/YYYY)</span>
														</s:if>
														<s:else>
															<div class="input-group input-medium date date-picker">
																<input type="text" readonly="readonly"
																	class="form-control fdate" id="staffDate"
																	name="user.person.dateOfBirth"
																	  value='<s:property value="user.person.dateOfBirthStr" />'>
																<span class="input-group-btn">
																	<button type="button" class="btn default">
																		<i class="fa fa-calendar"></i>
																	</button> </span>
															</div>
															<span class="help-block">(MM/DD/YYYY)</span>
														</s:else>
													</div>
												</div>
											</div>
											<div class="col-md-6">
												<div class="form-group">
													<label class="control-label col-md-4">
														Blood Group :
													</label>
													<div class="col-md-8">
														<s:select id="bGroup"
															list="#{'A+':'A+ve','A-':'A-ve','A1+':'A1+ve','A1-':'A1-ve','A1B+':'A1B+ve','A1B-':'A1B-ve','A2B+':'A2B+ve','A2V-':'A2B-ve','A2+':'A2+ve','A2-':'A2-ve','B+':'B+ve','B-':'B-ve','AB+':'AB+ve','AB-':'AB-ve','O+':'O+ve','O-':'O-ve'}"
															cssClass="form-control input-medium" headerKey=""
															headerValue="- Select -" name="user.person.bloodGroup" />
													</div>
												</div>
											</div>
										</div>
									</s:if>
									<s:else>
										<div class="row">
											<div class="col-md-6">
												<div class="form-group">
													<%-- <s:if test='%{user.isParent=="Y"}'>
														<label class="control-label col-md-4">
															<span class="required">*</span>First Name :
														</label>
														<div class="col-md-7">
															<sj:textfield name="user.person.fatherName"
																id="fatherName"
																cssClass="required form-control input-medium as-input"
																maxlength="50"></sj:textfield>
														</div>
													</s:if>
													<s:else> --%>
														<label class="control-label col-md-4">
															<span class="required">*</span>First Name :
														</label>
														<div class="col-md-7">
															<sj:textfield name="user.person.firstName"
																id="firstName1"
																cssClass="required form-control input-medium as-input"
																maxlength="50"></sj:textfield>
														</div>
													<%-- </s:else> --%>
												</div>
											</div>
											<div class="col-md-6">
												<div class="form-group">
													<label class="control-label col-md-4">
														Last Name :
													</label>
													<div class="col-md-8">
														<sj:textfield name="user.person.lastName" id="lastName1"
															cssClass="form-control input-medium as-input"
															maxlength="50"></sj:textfield>
													</div>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-md-6">
												<div class="form-group">
													<label class="control-label col-md-4">
														Date of Birth :
													</label>
													<div class="col-md-7">
													<s:if test='%{user.person.dateOfBirth==empty}'>
														<div class="input-group input-medium date date-picker">
															<input type="text" readonly="readonly"
																class="form-control fdate" id="dateOfBirth"
																name="user.person.dateOfBirth">
															<span class="input-group-btn">
																<button type="button" class="btn default">
																	<i class="fa fa-calendar"></i>
																</button> </span>
														</div>
														<span class="help-block">(MM/DD/YYYY)</span>
														</s:if>
														<s:else>
															<div class="input-group input-medium date date-picker">
																<input type="text" readonly="readonly"
																	class="form-control fdate" id="dateOfBirth"
																	name="user.person.dateOfBirthStr"
																	  value='<s:property value="user.person.dateOfBirthStr" />'>
																<span class="input-group-btn">
																	<button type="button" class="btn default">
																		<i class="fa fa-calendar"></i>
																	</button> </span>
															</div>
															<span class="help-block">(MM/DD/YYYY)</span>
														</s:else>
													</div>
												</div>
											</div>
											<div class="col-md-6">
												<div class="form-group">
													<label class="control-label col-md-4">
														Blood Group :
													</label>
													<div class="col-md-8">
														<s:select id="bGroup1"
															list="#{'A+':'A+ve','A-':'A-ve','A1+':'A1+ve','A1-':'A1-ve','A1B+':'A1B+ve','A1B-':'A1B-ve','A2B+':'A2B+ve','A2B-':'A2B-ve','A2+':'A2+ve','A2-':'A2-ve','B+':'B+ve','B-':'B-ve','AB+':'AB+ve','AB-':'AB-ve','O+':'O+ve','O-':'O-ve'}"
															cssClass="form-control input-medium" headerKey=""
															headerValue="- Select -" name="user.person.bloodGroup" />
													</div>
												</div>
											</div>
										</div>
									</s:else>
									<s:if test='%{user.isParent=="Y"}'>
										<div class="row">
											<div class="col-md-6">
												<div class="form-group">
													<label class="control-label col-md-4">
														<span class="required">*</span>Email :
													</label>
													<div class="col-md-7">
														<sj:textfield name="user.person.parentEmail"
															id="parentEmail"
															cssClass="required form-control input-medium as-input"
															maxlength="60"></sj:textfield>
													</div>
												</div>
											</div>
										</div>
									</s:if>
									<s:elseif test='%{user.isSchoolStudent=="Y"}'>
										<div class="row">
											<div class="col-md-6">
												<div class="form-group">
													<label class="control-label col-md-4">
														<span class="required">*</span>Father Name :
													</label>
													<div class="col-md-7">
														<sj:textfield name="user.person.fatherName"
															id="fatherName"
															cssClass="required form-control input-medium as-input"
															maxlength="60"></sj:textfield>
													</div>
												</div>
											</div>
											<div class="col-md-6">
												<div class="form-group">
													<label class="control-label col-md-4">
														<span class="required">*</span>Mother Name :
													</label>
													<div class="col-md-8">
														<sj:textfield name="user.person.motherName"
															id="motherName"
															cssClass="required form-control input-medium as-input"
															maxlength="60"></sj:textfield>
													</div>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-md-6">
												<div class="form-group">
													<label class="control-label col-md-4">
														Class Name :
													</label>
													<div class="col-md-7">
														<sj:textfield name="student.classNameClassId.className"
															id="studentClassSection"
															cssClass="form-control input-medium as-input"
															readonly="true" maxlength="40"></sj:textfield>
													</div>
												</div>
											</div>
											<div class="col-md-6">
												<div class="form-group">
													<label class="control-label col-md-4">
														Parent Email :
													</label>
													<div class="col-md-8">
														<sj:textfield name="user.person.parentEmail"
															id="parentEmail" labelposition="top" readonly="true"
															cssClass="form-control input-medium as-input"
															maxlength="60"></sj:textfield>
													</div>
												</div>
											</div>
											<div class="col-md-6">
												<div class="form-group">
													<label class="control-label col-md-4">
														Student Email :
													</label>
													<div class="col-md-8">
														<sj:textfield name="user.person.studentEmail"
															id="studentEmail" labelposition="top" readonly="true"
															cssClass="form-control input-medium as-input"
															maxlength="60"></sj:textfield>
													</div>
												</div>
											</div>
										</div>
									</s:elseif>
									<s:else>
										<div class="row">
											<div class="col-md-6">
												<div class="form-group">
													<label class="control-label col-md-4">
														Email :
													</label>
													<div class="col-md-7">
														<sj:textfield name="user.primaryAddress.email"
															id="emailAddress"
															cssClass="form-control input-medium as-input"
															maxlength="60"></sj:textfield>
													</div>
												</div>
											</div>
										</div>
									</s:else>
									<s:if test='%{user.isSchoolTeacher=="Y"}'>
										<div class="row">
											<div class="col-md-6">
												<div class="form-group">
													<s:if test='%{staff.qualification1!="null"}'>
														<label class="control-label col-md-4">
															Qualification :
														</label>
														<div class="col-md-7">
															<sj:textfield name="staff.qualification1"
																id="qualification2" readonly="true"
																cssClass="form-control input-medium as-input"
																maxlength="40"></sj:textfield>
														</div>
													</s:if>
													<s:else>
														<label class="control-label col-md-4">
															Qualification :
														</label>
														<div class="col-md-7">
															<sj:textfield name="staff.qualification2"
																id="qualification1" readonly="true"
																cssClass="form-control input-medium as-input"
																maxlength="40"></sj:textfield>
														</div>
													</s:else>
												</div>
											</div>
											<div class="col-md-6">
												<div class="form-group">
													<label class="control-label col-md-4">
														 Experience (months) :
													</label>
													<div class="col-md-8">
														<sj:textfield name="user.person.experience"
															id="experience" readonly="true"
															cssClass="numericDot form-control input-medium as-input"
															maxlength="4"></sj:textfield>
													</div>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-md-6">
												<div class="form-group">
													<label class="control-label col-md-4">
														<span class="required">*</span>Marital Status :
													</label>
													<div class="col-md-7">
														<sj:textfield name="user.person.maritalStatusDesc"
															id="maritalStatusDesc"
															cssClass="required form-control input-medium as-input"
															maxlength="40"></sj:textfield>
													</div>
												</div>
											</div>
										</div>
									</s:if>
									<div class='error' style='display: none;'>
									</div>
									<h5 class="pageTitle bold form-section">
										CONTACT DETAILS
									</h5>
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-4">
												<s:if test='%{user.isParent=="Y"}'>
													<span class="required">*</span>Mobile Number 1 :
												</s:if>
												<s:else>
													<span class="required">*</span> Mobile Number  :
												</s:else>
												</label>
												<div class="col-md-7">
													<s:if test='%{user.isSchoolStudent=="Y"}'>
														<sj:textfield name="user.person.studentMobile"
															id="mobileNumber"
															cssClass="form-control input-medium numeric "
															onblur="return validateMobNumber(this.value)"
															maxlength="10" />
													</s:if>
													<s:elseif test='%{user.isParent=="Y"}'>
													<sj:textfield name="user.person.mobileNumber"
															id="mobileNumber"
															cssClass="required form-control input-medium numeric mobileNumberStr"
															onblur="return validateMobNumber(this.value)"
															maxlength="10"/>
													</s:elseif>
													<s:elseif test='%{user.isOnlySchoolAdmin=="Y"}'>
													<sj:textfield name="user.person.mobileNumber"
															id="mobileNumber"
															cssClass="required form-control input-medium numeric mobileNumberStr"
															maxlength="10"/>
													</s:elseif>
													<s:else>
													<sj:textfield name="user.person.mobileNumber"
															id="staffMobileNumber"
															cssClass="required form-control input-medium numeric mobileNumberStr"
															onblur="return validateMobNumber(this.value)"
															maxlength="10" />
													</s:else>
												</div>
											</div>
										</div>
										<s:if test='%{user.isParent=="Y"}'>
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-4">
													<span class="required">*</span>Mobile Number 2 :
												</label>
												<div class="col-md-7">
													<sj:textfield name="user.person.secondaryMobileNumber"
														id="secondaryMobileNumber" cssClass="required form-control input-medium numeric"
														maxlength="10" />
												</div>
											</div>
										</div>
										</s:if>
										<s:else>
											<div class="col-md-6" id="staffOTP" style="display: none;">
											<div class="form-group">
												<label class="control-label col-md-4">
													<span class="required">*</span>Enter OTP :
												</label>
												<div class="col-md-7">
													<sj:textfield name="user.OTP"
														id="userOtp" cssClass="required form-control input-medium numeric"
														maxlength="6" />
												</div>
											</div>
										</div>
										</s:else>
									</div>
									<s:if test='%{user.isParent=="Y"}'>
										<div class="row">
											<div class="col-md-6">
												<div class="form-group">
													<label class="control-label col-md-4">
														<span class="required">*</span>Phone Number :
													</label>
													<div class="col-md-8">
														<sj:textfield name="user.person.phoneNumber"
															cssClass="required form-control input-medium numeric"
															maxlength="10"></sj:textfield>
													</div>
												</div>
											</div>
										</div>
									</s:if>
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-4">
													<s:if test='%{user.isParent=="Y"}'>
														<span class="required">*</span>Street :
													</s:if>
													<s:else>
														Street :
													</s:else>
												</label>
												<div class="col-md-7">
													<s:if test='%{user.isParent=="Y"}'>
														<sj:textfield name="user.primaryAddress.streetName"
														id="streetName"
														cssClass="required form-control input-medium as-input"
														maxlength="100" />
													</s:if>
													<s:else>
														<sj:textfield name="user.primaryAddress.streetName"
														id="streetName"
														cssClass="form-control input-medium as-input"
														maxlength="100" />
													</s:else>	
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-4">
													<s:if test='%{user.isParent=="Y"}'>
														<span class="required">*</span>City :
													</s:if>
													<s:else>
															City :
													</s:else>
												</label>
												<div class="col-md-8">
													<s:if test='%{user.isParent=="Y"}'>
														<sj:textfield name="user.primaryAddress.city" id="city"
															cssClass="required form-control input-medium as-input"
															maxlength="40" />
													</s:if>	
													<s:else>
														<sj:textfield name="user.primaryAddress.city" id="city"
															cssClass="form-control input-medium as-input"
															maxlength="40" />
													</s:else>
												</div>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-4">
												<s:if test='%{user.isParent=="Y"}'>
													<span class="required">*</span>State :
												</s:if>
												<s:else>
													State :
												</s:else>
												</label>
												<div class="col-md-7">
													<s:if test='%{user.isParent=="Y"}'>
														<s:select id="state" list="statesList"
															cssClass="required form-control input-medium as-input"
															listKey="stateCode" listValue="stateName"
															headerKey="" headerValue="- Select -"
															name="user.primaryAddress.state" />
													</s:if>
													<s:else>
														<s:select id="state" list="statesList"
															cssClass="form-control input-medium as-input"
															listKey="stateCode" listValue="stateName"
															headerKey="" headerValue="- Select -"
															name="user.primaryAddress.state" />
													</s:else>
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-4">
												<s:if test='%{user.isParent=="Y"}'>
													<span class="required">*</span>Pincode :
												</s:if>
												<s:else>
												 Pincode :
												</s:else>
												</label>
												<div class="col-md-8">
												<s:if test='%{user.isParent=="Y"}'>
													<sj:textfield name="user.primaryAddress.postalCode" id="pincode"
														cssClass="required numeric form-control input-medium as-input"
														maxlength="6" />
												</s:if>
												<s:else>
													<sj:textfield name="user.primaryAddress.postalCode"
														id="pincode"
														cssClass="numeric form-control input-medium as-input"
														maxlength="6" />
												</s:else>
												</div>
											</div>
										</div>
									</div>
									<div class="row">
											<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-4"> <s:if
														test='%{user.isParent=="Y"}'>
														<span class="required">*</span>Country :
												</s:if> <s:else>
													Country :
												</s:else>
												</label>
												<div class="col-md-7">
													<s:if test='%{user.isParent=="Y"}'>
														<s:select id="countryId" list="countryList"
															cssClass="required form-control input-medium as-input"
															listKey="countryCode" listValue="countryName" headerKey=""
															headerValue="- Select -" name="user.primaryAddress.country" />
													</s:if>
													<s:else>
														<s:select id="countryId" list="countryList"
															cssClass="form-control input-medium as-input"
															listKey="countryCode" listValue="countryName" headerKey=""
															headerValue="- Select -" name="user.primaryAddress.country" />
													</s:else>
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-4">
													Summary :
												</label>
												<div class="col-md-7">
													<sj:textarea rows="3" cols="20" name="user.person.summary"
														cssClass="form-control word_count"></sj:textarea>
													<div class="counter"></div>
												</div>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-9">
											<div class="col-md-offset-2 col-md-9">
												<sj:submit   targets="editProfileContntDiv" 
													value="Save Changes" cssClass="btn green" validate="true"
													formIds="changePersonalDetailsForm"
													onBeforeTopics="doValidatePersonalDetails" />
												<s:url id="EditProfileSettings" action="ajaxDoEditProfile"
													namespace="/user">
												</s:url>
												<sj:a href="%{EditProfileSettings}" targets="mainContentDiv"
													cssClass='btn default'>
													Cancel</sj:a>
											</div>
										</div>
									</div>
								</s:form>
							</div>
							<div id="tab_2-2" class="tab-pane">
								<jsp:include page="/WEB-INF/pages/user/addUserImage.jsp"></jsp:include>
							</div>
						   <%-- </s:if> --%>
						  	<s:if test='%{user.isSchoolTeacher == "Y"}'>
								<div id="tab_3-3" class="tab-pane">
							</s:if>
							<s:else>
								<div id="tab_3-3" class="tab-pane">
							</s:else>
								<jsp:include
									page="/WEB-INF/pages/user/ajaxChangePassword.jsp"></jsp:include>
							</div>
						</div>
					</div>
				</div>
			</div>

		</div>
	</div>
	<!--END TABS-->
</div>
<script type="text/javascript">
$(document).ready(function() {
$('.numeric').numeric();
$('.numericDot').numeric( {allow : "."});
$('#userOtp').val('');
if($("div.alert-danger").is(":visible")){
	$('a[href="#tab_1_3"]').tab('show');
	$('#staffOTP').show();
	$('#userOtp').val('');
}

FormComponents.init();
$(".imagePathNotFound").error(function(){
    $(this).attr('src', '../images/common/photo_notAvailable.jpg');
});
	
	 var userImagePath = '<s:property value="user.profileImage.adjustedAttachmentFilePath"/>';
		if(isNonEmpty(userImagePath) && userImagePath !=null && userImagePath != "/images/common/photo_notAvailable.jpg" && userImagePath !="../img/avatar.png" && userImagePath != "../img/nophoto.jpg"){
			$('#userProfileImageDiv').prop('src', userImagePath + '?' + Math.random());
			$('#userProfileDiv').prop('src', userImagePath + '?' + Math.random());
			$('#userImageDiv').prop('src', userImagePath + '?' + Math.random());
			$('#profileImagesDiv').prop('src', userImagePath + '?' + Math.random());			
		} else{
			 var studentImagePath = '<s:property value="student.profileImage.adjustedAttachmentFilePath"/>';
			 if(isNonEmpty(studentImagePath) && studentImagePath !=null && studentImagePath != "/images/common/photo_notAvailable.jpg" && studentImagePath !="../img/avatar.png" && studentImagePath != "../img/nophoto.jpg"){
					$('#studentProfileImageDiv').prop('src', studentImagePath + '?' + Math.random());
					$('#studentProfileDiv').prop('src', studentImagePath + '?' + Math.random());
					$('#studentImageDiv').prop('src', studentImagePath + '?' + Math.random());
					$('#profileImagesDiv').prop('src', userImagePath + '?' + Math.random());			
				} 
		}
		$("#staffMobileNumber").autoCheck("${pageContext.request.contextPath}/staff/ajaxSecondaryUsernameAvailableOrNot.do",
				{
					minChars : 10,
					min : "no",
				});
	$("input.mobileNumberStr").keyup(function() {
		 
		var staffMobilenumber = $(this).val();
		if (staffMobilenumber.length == 10) {
			var delay = (function(){
				  var timer = 0;
				  return function(callback, ms){
				    clearTimeout (timer);
				    timer = setTimeout(callback, ms);
				  };
				})();
			delay(function(){
			var oldMobileNumber ='<s:property value="user.username" />';
			oldMobileNumber = oldMobileNumber.replace('S','').replace("P","");
			var  mobileNumberStr = $('input.mobileNumberStr').parents('div').next('div').find('p.word-available').html();
			if (oldMobileNumber!=staffMobilenumber) {
				if (mobileNumberStr == 'Available') {
					$('#staffOTP').show();
					var feeURL = jQuery.url.getChatURL("/common/ajaxSendOTPToSecondaryusername.do?tempString="+ staffMobilenumber);
					$.ajax({
						url : feeURL,
						cache : false,
						dataType : 'json',
						success : function(response) {
							
						}
					});
				}else
					$('#staffOTP').hide();
				$('#userOtp').val('');
			} else {
				$('#staffOTP').hide();
				$('#userOtp').val('');
			}
			}, 2000 );
		}
		
	
	
	});
});
$.subscribe('doValidatePersonalDetails',function(event, data) {
	var staffMobileNumberStr = $('input.mobileNumberStr').parents('div').next('div').find('p.word-taken').html();
	var mobileNumberStr= $("input.mobileNumberStr").val();
	if(mobileNumberStr.length == 10){
		if (staffMobileNumberStr == 'Already taken!!!') {
			event.originalEvent.options.submit = false;
		}
	}else{
		alert("Please enter 10 digits mobile number.");
		event.originalEvent.options.submit = false;
	}
});
</script>