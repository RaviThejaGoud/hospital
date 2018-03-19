<%@ include file="/common/taglibs.jsp"%>
<jsp:include page="/common/messages.jsp"></jsp:include>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/subscription/textcounter.js"></script>
<s:if test="%{academicYear != null && academicYear != empty}">
	<s:if test="%{classList != null && !classList.isEmpty()}">
		<s:form action="ajaxSubmitOfflineRegistration" theme="simple"
			cssClass="form-horizontal" id="addStudentOfflineInfo" method="post"
			namespace="/admin" enctype="multipart/form-data">
			
			<s:hidden name="onlineApplicationDetails.id" id="onlineApplicationDetailsId"></s:hidden>
			<s:hidden name="admissionInquiry.id" value="%{tempId}"  id="admissionInquiryId"></s:hidden>
			
			<s:hidden name="custId"></s:hidden>
			<s:hidden name="customerImage" id="customerImage"></s:hidden>
			<s:hidden name="admissionSettings.id" id="admissionSettingsId"></s:hidden>
			<s:hidden name="admissionSettings.applicationFee" id="applicationFee"></s:hidden>
			<s:hidden name="admissionSettings.registrationFee"
				id="registrationFee"></s:hidden>
			<s:hidden name="admissionSettings.prospectiveFee" id="prospectiveFee"></s:hidden>
			<h4 class="pageTitle bold form-section">STUDENT INFORMATION</h4>
			<div class="form-body">
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4"> <span
								class="required">*</span>Select Academic Year :
							</label>
							<div class="col-md-6">
								<s:select id="academicYearId" list="academicYear"
									cssClass="required form-control input-medium" listKey="id"
									listValue="academicYear" headerKey="" headerValue="- Select -"
									onchange="javascript:getAjaxAdmissionAcademicYear(this.value);"
									name="academicYearId" />
							</div>
						</div>
					</div>
					
					<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-3"></label>
						<div id="browseImage">
						
						<s:if test='%{onlineApplicationDetails != null}'>
							<img
								src='<c:url value="${onlineApplicationDetails.originalAttachmentFilePath}"/>'
								alt='' border="0" height="102px;" id="imageNotAvailableDiv" />
						</s:if>
							<s:if test='%{onlineApplicationDetails.profileImage != null}'>
								<s:if test='%{onlineApplicationDetails.profileImage.id > 0}'>
									<s:url id="removeImage" action="ajaxRemoveStudentAndStaffImage"
										includeParams="all" escapeAmp="false" namespace="/student">
										<s:param name="onlineApplicationDetails.profileImage.id"
											value="onlineApplicationDetails.profileImage.id" />
										<s:param name="onlineApplicationDetails.id" value="onlineApplicationDetails.id" />
									</s:url>
									<s:div cssClass="btn red"
										cssStyle="   margin-left: -14px; position: absolute;top: 1px;padding:2px;"
										onclick="javascript:confirmToRemoveImage(this,'imageNotAvailableDiv');"
										id='%{removeImage}' title="Delete this image">
										<i class="fa fa-times"></i>
									</s:div>
								</s:if>
							</s:if>
						</div>
						<img id="image" border="0" height="102px;" style="display: none" />
						<div class="spaceDiv"></div>
						<div class="col-md-2"></div>
						<div class="col-md-2">
							<a data-toggle="modal" href="#studentsImgDiv"
								class="capturePhoto btn default"
								onclick="javascript:viewPopupStudensCapture();"><i
								class="fa fa-camera"></i>Capture </a>
						</div>
						<div class="col-md-1">
							<strong>OR</strong>
						</div>
						<div class="col-md-5">
							<div class="fileupload fileupload-new" data-provides="fileupload">
								<div class="input-append">
									<span class="btn default"> <s:file name="upload"
											id="photoURL1" cssClass="fileName"></s:file>
									</span>
								</div>
							</div>
						</div>
					</div>
				</div>
				
				
					<%-- <div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4"></label>
							<div class="col-md-6">
								<img id="image" border="0" height="102px;" style="display: none" />
								<div class="">
									<a data-toggle="modal" href="#studentsImgDiv"
										class="capturePhoto btn default"
										onclick="javascript:viewPopupStudensCapture();"><i
										class="fa fa-camera"></i>Capture </a>
								</div>
								<div class="">
									<strong>OR</strong>
								</div>
								<div class="">
									<label class=""> Upload Image : </label>
									<div class="fileupload fileupload-new"
										data-provides="fileupload">
										<div class="input-append">
											<span class="btn default"> <s:file name="upload"
													id="photoURL1" cssClass="fileName"></s:file>
											</span>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div> --%>
				</div>
				
				
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4"> <span
								class="required">*</span>First Name :
							</label>
							<div class="col-md-6">
							
							<s:if test='%{admissionInquiry.studentName !=null}'>
								<sj:textfield name="onlineApplicationDetails.firstName" value="%{admissionInquiry.studentName}"
									id="firstName"
									cssClass="required form-control input-medium as-input"
									maxlength="60"></sj:textfield>
							</s:if>
							<s:else>
								<sj:textfield name="onlineApplicationDetails.firstName"
									id="firstName"
									cssClass="required form-control input-medium as-input"
									maxlength="60"></sj:textfield>
							</s:else>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4"> Last Name : </label>
							<div class="col-md-6">
								<sj:textfield name="onlineApplicationDetails.lastName"
									id="lastName" cssClass=" form-control input-medium as-input"
									maxlength="60"></sj:textfield>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4"> Date of Birth : </label>
							<div class="col-md-5">
								<div data-date-format="mm/dd/yyyy"
									class="input-group input-medium date date-picker"
									data-date-end-date="+0d">
									<input id="date0"
										name="onlineApplicationDetails.dateOfBirth" readonly="true"
										type="text" class="form-control"
										value='<s:property value="onlineApplicationDetails.dateOfBirthStr"/>'>
									<span class="input-group-btn">
										<button type="button" class="btn default">
											<i class="fa fa-calendar"></i>
										</button>
									</span>
								</div>
								<span class="help-block">(MM/DD/YYYY)</span>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4"> Age : </label>
							<div class="col-md-6">
								<sj:textfield name="onlineApplicationDetails.age"
									id="lastName" cssClass=" form-control input-medium as-input"
									maxlength="60"></sj:textfield>
							</div>
						</div>
					</div>
				</div>
				
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4"> Age Appropriation :
							</label>
							<div class="col-md-6">
								<sj:textfield name="onlineApplicationDetails.ageAppropriation"
									id="firstName"
									cssClass="form-control input-medium as-input"
									maxlength="60"></sj:textfield>
							</div>
						</div>
					</div>
					
					<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> <span
							class="required">*</span>Gender :
						</label>
						<div class="col-md-7">
							<div class="make-switch has-switch" data-id="M" data-value="F"
								style="width: 120px" data-off="warning" data-on="success"
								data-off-label="Female" data-on-label="Male">
								<s:if test='%{onlineApplicationDetails.gender =="M" || onlineApplicationDetails.gender =="F" }'>
									<input type="radio" class="toggle" checked="checked" id="gender">
									<input type="hidden" name="onlineApplicationDetails.gender" value='<s:property value="onlineApplicationDetails.gender"/>'>
								</s:if>
								<s:else>
									<input type="radio" class="toggle" id="gender" checked="checked">
									<input type="hidden" name="onlineApplicationDetails.gender" value='M'>
								</s:else>
								 
							</div>
						</div>
					</div>
				</div>
				
				
					<!-- <div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4"> <span
								class="required">*</span>Gender :
							</label>
							<div class="col-md-6">
								<div class="make-switch has-switch" data-id="M" data-value="F"
									style="width: 120px" data-off="warning" data-on="success"
									data-off-label="Female" data-on-label="Male">
									<input type="radio" class="toggle" checked="checked"
										id="gender"> <input type="hidden"
										name="onlineApplicationDetails.gender" value="M">
								</div>
							</div>
						</div>
					</div> -->
				</div>
				
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4"> Transfer
								Certificate No : </label>
							<div class="col-md-8">
								<sj:textfield
									name="onlineApplicationDetails.transferCertificateNo"
									id="transferCertificateNo"
									cssClass="form-control input-medium as-inputl" maxlength="40"></sj:textfield>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4">Transfer
									Certificate Date : </label>
							<div class="col-md-6">
								<div data-date-format="mm/dd/yyyy"
									class="input-group input-medium date date-picker"
									data-date-end-date="+0d">
									<input id="date0"
										name="onlineApplicationDetails.transferCertificateDate" readonly="true"
										type="text" class="form-control"
										value='<s:property value="onlineApplicationDetails.transferCertificateDateStr"/>'>
									<span class="input-group-btn">
										<button type="button" class="btn default">
											<i class="fa fa-calendar"></i>
										</button>
									</span>
								</div>
								<span class="help-block">(MM/DD/YYYY)</span>
							</div>
						</div>
					</div>
				</div>
				
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4"> Aadhaar Card No : </label>
							<div class="col-md-8">
								<sj:textfield name="onlineApplicationDetails.aadharCardNumber" onchange="javascript:validateAadhaarCardNumber(this.value)"
									id="aadharCardNumber"
									cssClass="numeric form-control input-medium as-input" maxlength="12" minlength="10"></sj:textfield>
							</div>
						</div>
					</div>
					
					<%-- <div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4">Student Caste Certificate : </label>
							<div class="col-md-8">
								<sj:textfield
									name="onlineApplicationDetails.studentCasteCertificate"
									id="studentCasteCertificate"
									cssClass="form-control input-medium as-inputl" maxlength="40"></sj:textfield>
							</div>
						</div>
					</div> --%>
				</div>
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4"> Student Mobile
								Number : </label>
							<div class="col-md-8">
								<sj:textfield name="onlineApplicationDetails.studentMobile"  onblur="return validateStuMobNumber(this.value)"
									id="studentMobile" cssClass="form-control input-medium as-input numeric"
									maxlength="10"></sj:textfield>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4"> Student E-Mail : </label>
							<div class="col-md-8">
								<sj:textfield name="onlineApplicationDetails.studentEmail"
									id="studentEmailId"
									cssClass="form-control input-medium as-input email"
									maxlength="50"></sj:textfield>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
				
				<s:if test="%{customer.hostelModuleStatus}">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4"> Residence Type : </label>
							<div class="col-md-8">
								<div class="make-switch has-switch" data-id="D" data-value="H"
									style="width: 200px" data-off="warning" data-on="success"
									data-off-label="Hostler" data-on-label="Day Scholar">
									<s:if test='%{onlineApplicationDetails.hostelMode =="D"}'>
										<input type="radio" class="toggle" checked="checked"
											id="hostel">
										<input type="hidden"
											name="onlineApplicationDetails.hostelMode"
											value='<s:property value="onlineApplicationDetails.hostelMode"/>'>
									</s:if>
									<s:elseif
										test='%{onlineApplicationDetails.hostelMode =="H"}'>
										<input type="radio" class="toggle" id="hostel">
										<input type="hidden"
											name="onlineApplicationDetailsView.hostelMode"
											value='<s:property value="onlineApplicationDetails.hostelMode"/>'>
									</s:elseif>
									<s:else>
										<input type="radio" class="toggle" checked="checked"
											id="hostel">
										<input type="hidden"
											name="onlineApplicationDetails.hostelMode" value='D'>
									</s:else>
								</div>
							</div>
						</div>
					</div>
				</s:if>
				
				
					<%-- <s:if test="%{customer.hostelModuleStatus}">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4"> Residence Type :
							</label>
							<div class="col-md-6">
								<div class="make-switch has-switch" data-id="D" data-value="H"
									style="width: 200px" data-off="warning" data-on="success"
									data-off-label="Hostler" data-on-label="Day Scholar">
									<input type="radio" class="toggle" checked="checked"
										id="hostlerMode"> <input type="hidden"
										name="onlineApplicationDetails.hostelMode" value="D">
								</div>
							</div>
						</div>
					</div>
					</s:if> --%>
					
				</div>
				
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4"> Select Category : </label>
							<div class="col-md-6">
								<s:select id="categoryId" list="schoolCategoriesList"
									listKey="id" listValue="categoryName"
									cssClass="form-control input-medium"
									name="onlineApplicationDetails.categoryId" />
							</div>
						</div>
					</div>
					
				</div>
				
			<%-- 	<div class="row">
					<s:if test='%{customer.transportModuleStatus}'>
						<div class="col-md-6">
							<div class="form-group">
								<label class="col-md-4 control-label"> Transport Mode :
								</label>
								<div class="col-md-8">
									<div class="clearfix">
										<div data-toggle="buttons" class="btn-group">
											<label class="btn blue active transportMode"> <input
												type="radio" value="O" class="toggle transportMode"
												id="transportMode"
												name="onlineApplicationDetails.transportMode"
												checked="checked"> Own
											</label> <label class="btn blue transportMode"> <input
												type="radio" class="toggle transportMode" value="P"
												id="transportMode"
												name="onlineApplicationDetails.transportMode">
												Private
											</label> <label class="btn blue transportMode"> <input
												type="radio" class="toggle transportMode" value="T"
												id="transportMode"
												name="onlineApplicationDetails.transportMode">
												School Transport
											</label>
										</div>
									</div>
								</div>
							</div>
						</div> 
						<div id="inputboxhideText" style="display: none;">
							<div class="row">
								<div class="col-md-12">
									<jsp:include
										page="/WEB-INF/pages/common/ajaxAddStudentTransportInformation.jsp" />
								</div>
							</div>
						</div>
					</s:if>
				</div>--%>
				<h4 class="pageTitle bold form-section">STUDENT EDUCATIONAL INFORMATION</h4>
				<h5 class="pageTitle bold">CURRENT ADMISSION DETAILS</h5>
				<div class="row">
					<div class="col-md-6" id="allClasses">
						<jsp:include
							page="/WEB-INF/pages/admin/admission/ajaxClassList.jsp"></jsp:include>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4"> Medium Of Instruction :
							</label>
							<div class="col-md-6">
								<sj:textfield name="onlineApplicationDetails.mediumOfInstruction"
									id="lastSchool" cssClass="form-control input-medium as-inputl"
									maxlength="80"></sj:textfield>
							</div>
						</div>
					</div>
				</div>
				
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4"> Date of Joining : </label>
							<div class="col-md-5">
								<div data-date-format="mm/dd/yyyy"
									class="input-group input-medium date date-picker"
									data-date-end-date="+0d">
									<input id="date0"
										name="onlineApplicationDetails.dateOfJoining" readonly="true"
										type="text" class="form-control"
										value='<s:property value="onlineApplicationDetails.dateOfJoiningStr"/>'>
									<span class="input-group-btn">
										<button type="button" class="btn default">
											<i class="fa fa-calendar"></i>
										</button>
									</span>
								</div>
								<span class="help-block">(MM/DD/YYYY)</span>
							</div>
						</div>
					</div>
					
					<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> Is RTE Student (Free Education)  : </label>
						<div class="col-md-6">
							<div class="make-switch has-switch" data-id="N" data-value="Y"
								style="width: 200px" data-off="warning" data-on="success"
								data-off-label="Yes" data-on-label="No">
								<s:if test='%{onlineApplicationDetails.rteStatus =="N"}'>
									<input type="radio" class="toggle" checked="checked"
										id="rteStatus">
									<input type="hidden" name="onlineApplicationDetails.rteStatus"
										value='<s:property value="onlineApplicationDetails.rteStatus"/>'>
								</s:if>
								<s:elseif test='%{onlineApplicationDetails.rteStatus =="Y"}'>
									<input type="radio" class="toggle" id="rteStatus">
									<input type="hidden" name="onlineApplicationDetails.rteStatus"
										value='<s:property value="onlineApplicationDetails.rteStatus"/>'>
								</s:elseif>
								<s:else>
									<input type="radio" class="toggle" checked="checked"
										id="rteStatus">
									<input type="hidden" name="onlineApplicationDetails.rteStatus" value='N'>
								</s:else>
							</div>
						</div>
					</div>
				</div>
				
				
					<!-- <div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4"> Is RTE Student (Free Education)  : </label>
							<div class="col-md-7">
								<div class="make-switch has-switch" data-id="N" data-value="Y"
									style="width: 200px" data-off="warning" data-on="success"
									data-off-label="Yes" data-on-label="No">
									<input type="radio" class="toggle" checked="checked"
										id="rteStatus"> <input type="hidden"
										name="onlineApplicationDetails.rteStatus" value="N">
								</div>
							</div>
						</div>
					</div> -->
				</div>
			<h4 class="pageTitle bold form-section">PREVIOUS SCHOOL INFORMATION</h4>
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4"> Class Studied
								: </label>
							<div class="col-md-6">
								<sj:textfield name="onlineApplicationDetails.lastClassAttended"
									id="lastClassAttended"
									cssClass="form-control input-medium as-inputl" maxlength="60"></sj:textfield>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4"> School Name :
							</label>
							<div class="col-md-6">
								<sj:textfield name="onlineApplicationDetails.lastSchool" value="%{admissionInquiry.previousSchoolName}" 
									id="lastSchool" cssClass="form-control input-medium as-inputl"
									maxlength="80"></sj:textfield>
							</div>
						</div>
					</div>
				</div>
				
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4">Previous School Type
								: </label>
							<div class="col-md-6">
								<sj:textfield name="onlineApplicationDetails.previousSchoolType"
									id="lastClassAttended"
									cssClass="form-control input-medium as-inputl" maxlength="60"></sj:textfield>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4"> Previous School Affiliation :
							</label>
							<div class="col-md-6">
								<sj:textfield name="onlineApplicationDetails.previousSchoolAffiliation"
									id="lastSchool" cssClass="form-control input-medium as-inputl"
									maxlength="80"></sj:textfield>
							</div>
						</div>
					</div>
				</div>
				<h5 class="pageTitle bold">SCHOOL ADDRESS</h5>
				
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4"> Street Name : </label>
							<div class="col-md-6">
								<sj:textfield
									name="onlineApplicationDetails.previousSchoolAddress.streetName"
									id="previousStreetName" cssClass="form-control input-medium as-inputl"
									maxlength="200"></sj:textfield>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4"> City/Village/Town : </label>
							<div class="col-md-6">
								<sj:textfield
									name="onlineApplicationDetails.previousSchoolAddress.city" id="previousCity"
									cssClass="form-control input-medium as-inputl" maxlength="40"></sj:textfield>
							</div>
						</div>
					</div>
				</div>
				
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4"> Taluk : </label>
							<div class="col-md-6">
								<sj:textfield
									name="onlineApplicationDetails.previousSchoolAddress.taluka"
									id="previousTaluka" cssClass="form-control input-medium as-inputl"
									maxlength="200"></sj:textfield>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4"> District : </label>
							<div class="col-md-6">
								<sj:textfield
									name="onlineApplicationDetails.previousSchoolAddress.districtName" id="previousDistrictName"
									cssClass="form-control input-medium as-inputl" maxlength="40"></sj:textfield>
							</div>
						</div>
					</div>
				</div>
				
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4"> State : </label>
							<div class="col-md-6">
								<s:select id="previousState" list="statesList" listKey="stateCode"
									listValue="stateName" headerKey=""
									cssClass="form-control input-medium" headerValue="- Select -"
									name="onlineApplicationDetails.previousSchoolAddress.state" />
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4"> Pincode : </label>
							<div class="col-md-6">
								<sj:textfield
									name="onlineApplicationDetails.previousSchoolAddress.postalCode"
									id="previousPincode"
									cssClass="numeric form-control input-medium as-inputl"
									maxlength="6"></sj:textfield>
							</div>
						</div>
					</div>
				</div>
				
			
			<h4 class="pageTitle bold form-section">PARENT / GUARDIAN'S
				INFORMATION</h4>
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4"> <span
								class="required">*</span>Father / Guardian's Name :
							</label>
							<div class="col-md-6">
								<s:if test='%{admissionInquiry.parentName !=null}'>
									<sj:textfield name="onlineApplicationDetails.fatherName" value="%{admissionInquiry.parentName}" 
									id="fatherName"
									cssClass="required form-control input-medium as-inputl"
									maxlength="60"></sj:textfield>
								</s:if>
								<s:else>
									<sj:textfield name="onlineApplicationDetails.fatherName"
									id="fatherName"
									cssClass="required form-control input-medium as-inputl"
									maxlength="60"></sj:textfield>
								</s:else>
								
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4"> Father /
								Guardian's Qualification : </label>
							<div class="col-md-6">
								<sj:textfield
									name="onlineApplicationDetails.fatherQualification"
									id="fatherQualification"
									cssClass="form-control input-medium as-inputl" maxlength="20"></sj:textfield>
							</div>
						</div>
					</div>
				</div>
				
				
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4"> Father /
								Guardian's Occupation : </label>
							<div class="col-md-6">
								<sj:textfield name="onlineApplicationDetails.occupation"
									id="occupation" cssClass="form-control input-medium as-inputl"
									maxlength="40"></sj:textfield>
							</div>
						</div>
					</div>
					
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4"> Father / Guardian's Aadhar No :
							</label>
							<div class="col-md-6">
								<sj:textfield name="onlineApplicationDetails.fatherAadharNumber"
									id="fatherName"
									cssClass="form-control input-medium as-inputl"
									maxlength="12" minlength="10"></sj:textfield>
							</div>
						</div>
					</div>
					
				</div>
				
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4"> Mother Name : </label>
							<div class="col-md-6">
								<sj:textfield name="onlineApplicationDetails.motherName"
									id="motherName" cssClass="form-control input-medium as-inputl"
									maxlength="50"></sj:textfield>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4"> Mother
								Qualification : </label>
							<div class="col-md-6">
								<sj:textfield
									name="onlineApplicationDetails.motherQualification"
									id="motherQualification"
									cssClass="form-control input-medium as-inputl" maxlength="20"></sj:textfield>
							</div>
						</div>
					</div>
				</div>
			 <div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4"> Mother Occupation : </label>
							<div class="col-md-6">
								<sj:textfield name="onlineApplicationDetails.motherOccupation"
									id="motherOccupation" cssClass="form-control input-medium as-inputl"
									maxlength="60"></sj:textfield>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4"> Mother Aadhar No : </label>
							<div class="col-md-6">
								<sj:textfield name="onlineApplicationDetails.motherAadharNumber"
									id="motherOccupation" cssClass="form-control input-medium as-inputl"
									maxlength="12" minlength="10"></sj:textfield>
							</div>
						</div>
					</div>
					
				</div>
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4"> Monthly Income : </label>
							<div class="col-md-6">
								<sj:textfield name="onlineApplicationDetails.monthlyIncome"
									id="monthlyIncome"
									cssClass="form-control input-medium as-inputl numeric"></sj:textfield>
							</div>
						</div>
					</div>
					
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4"> Annual Income : </label>
							<div class="col-md-6">
								<sj:textfield name="onlineApplicationDetails.annualIncome"
									id="annualIncome"
									cssClass="form-control input-medium as-inputl numeric"></sj:textfield>
							</div>
						</div>
					</div>
				</div>
				
				<div class="row">
					<!-- <div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4">Is Below Poverty Line (BPL) : </label>
							<div class="col-md-7">
								<div class="make-switch has-switch" data-id="N" data-value="Y"
									style="width: 200px" data-off="warning" data-on="success"
									data-off-label="Yes" data-on-label="No">
									<input type="radio" class="toggle" checked="checked" class="bplStatusClass"
										id="bplStatus"> <input type="hidden"
										name="onlineApplicationDetails.bplStatus" value="N">
								</div>
							</div>
						</div>
					</div> -->
					
					
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4"> <span
								class="required">*</span>Is Below Poverty Line (BPL) :
							</label>
							<div class="col-md-7">
								<div class="radio-list">
								
									<s:if test='%{onlineApplicationDetails.bplStatus == "Y"}'>
										<label class="radio-inline">
											<input type="radio" id="bplStatusYes" value="Y" name="onlineApplicationDetails.bplStatus" class="bplStatusRadioClass" checked="checked"
												onclick="javascript: checkBPLStatus(this.value)"> Yes
										</label> <label class="radio-inline"> <input type="radio"
												name="onlineApplicationDetails.bplStatus" id="bplStatusNo" value="N"  class="bplStatusRadioClass"
												onclick="javascript: checkBPLStatus(this.value)"> No
											</label>
									</s:if>
									<s:elseif test='%{onlineApplicationDetails.bplStatus == "N"}'>
										<label class="radio-inline">
											<input type="radio" id="bplStatusYes" value="Y" name="onlineApplicationDetails.bplStatus" class="bplStatusRadioClass"
												onclick="javascript: checkBPLStatus(this.value)"> Yes
										</label> <label class="radio-inline"> <input type="radio"
												name="onlineApplicationDetails.bplStatus" id="bplStatusNo" value="N" class="bplStatusRadioClass" checked="checked"
												onclick="javascript: checkBPLStatus(this.value)"> No
											</label>
									</s:elseif>
									<s:else>
										<label class="radio-inline">
											<input type="radio" id="bplStatusYes" value="Y" name="onlineApplicationDetails.bplStatus" class="bplStatusRadioClass"
												onclick="javascript: checkBPLStatus(this.value)"> Yes
										</label> <label class="radio-inline"> <input type="radio"
												name="onlineApplicationDetails.bplStatus" id="bplStatusNo" value="N" checked="checked" class="bplStatusRadioClass"
												onclick="javascript: checkBPLStatus(this.value)"> No
											</label>									
									</s:else>
									
								</div>
								<span class="bplStatusClass" id="<s:property value='studentVo.bplStatus'/>"></span>
							</div>
						</div>
					</div>
			
					<div class="col-md-6" id="bplNumberDivId" style="display: none;">
						<div class="form-group">
							<label class="control-label col-md-4"> Below Poverty Line Number : </label>
							<div class="col-md-8">
								<sj:textfield name="onlineApplicationDetails.bplNumber" id="bplNumber"
									cssClass="form-control input-medium as-input" maxlength="50"></sj:textfield>
							</div>
						</div>
					</div>
				</div>
				
				
				<h4 class="pageTitle bold form-section">CONTACT DETAILS</h4>
				
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4"> <span
								class="required">*</span>Father Mobile Number :
							</label>
							<div class="col-md-6">
								<s:if test='%{admissionInquiry.parentMobileNumber !=null}'>
									<sj:textfield name="onlineApplicationDetails.mobileNumber" value="%{admissionInquiry.parentMobileNumber}" 
									id="mobileNumber"
									cssClass="numeric required form-control input-medium as-inputl "
									maxlength="10" 	onblur="return validateMobNumber(this.value)"></sj:textfield>
								</s:if>
								<s:else>
									<sj:textfield name="onlineApplicationDetails.mobileNumber"
									id="mobileNumber"
									cssClass="numeric required form-control input-medium as-inputl "
									maxlength="10" 	onblur="return validateMobNumber(this.value)"></sj:textfield>
								</s:else>
								
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4"> Mother Mobile Number : </label>
							<div class="col-md-6">
								<sj:textfield name="onlineApplicationDetails.motherMobileNumber"
									id="phoneNumber"
									cssClass="numeric form-control input-medium as-inputl"
									maxlength="10"></sj:textfield>
							</div>
						</div>
					</div>
				</div>
				
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4"> Phone Number : </label>
							<div class="col-md-6">
								<sj:textfield name="onlineApplicationDetails.phoneNumber"
									id="phoneNumber"
									cssClass="numeric form-control input-medium as-inputl"
									maxlength="10"></sj:textfield>
							</div>
						</div>
					</div>
					
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4"> Father Email Address :
							</label>
							<div class="col-md-6">
								<sj:textfield name="onlineApplicationDetails.parentEmail"
									id="emailAddress"
									cssClass="form-control input-medium as-inputl email"
									maxlength="60"></sj:textfield>
							</div>
						</div>
					</div>
				</div>
				
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4"> Mother Email Address :
							</label>
							<div class="col-md-6">
								<sj:textfield name="onlineApplicationDetails.motherEmailAddress"
									id="motherEmailAddress"
									cssClass="form-control input-medium as-inputl email"
									maxlength="60"></sj:textfield>
							</div>
						</div>
					</div>
				</div>
				
				<h5 class="pageTitle bold">RESIDENTIAL ADDRESS</h5>
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4"> Street Name : </label>
							<div class="col-md-6">
								<sj:textfield
									name="onlineApplicationDetails.primaryAddress.streetName" value="%{address.addressLine1}"
									id="streetName" cssClass="form-control input-medium as-inputl"
									maxlength="200"></sj:textfield>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4"> City : </label>
							<div class="col-md-6">
								<sj:textfield
									name="onlineApplicationDetails.primaryAddress.city" id="city" value="%{address.addressLine2}"
									cssClass="form-control input-medium as-inputl" maxlength="40"></sj:textfield>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4"> Taluk : </label>
							<div class="col-md-6">
								<sj:textfield
									name="onlineApplicationDetails.primaryAddress.taluka"
									id="taluka" cssClass="form-control input-medium as-inputl"
									maxlength="200"></sj:textfield>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4"> District : </label>
							<div class="col-md-6">
								<sj:textfield
									name="onlineApplicationDetails.primaryAddress.districtName" id="districtName"
									cssClass="form-control input-medium as-inputl" maxlength="40"></sj:textfield>
							</div>
						</div>
					</div>
				</div>
				
				
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4"> State : </label>
							<div class="col-md-6">
								<s:select id="state" list="statesList" listKey="stateCode" value="%{address.state}"
									listValue="stateName" headerKey=""
									cssClass="form-control input-medium" headerValue="- Select -"
									name="onlineApplicationDetails.primaryAddress.state" />
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4"> Pincode : </label>
							<div class="col-md-6">
								<sj:textfield
									name="onlineApplicationDetails.primaryAddress.postalCode" value="%{address.postalCode}"
									id="pincode"
									cssClass="numeric form-control input-medium as-inputl"
									maxlength="6"></sj:textfield>
							</div>
						</div>
					</div>
				</div>
				<div class="clearfix">&nbsp;</div>
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-6"> Same As
								Residential Address : </label>
							<div class="col-md-6">
								<div class="controls">
									<label class="checkbox"> <span><input
											type="checkbox" name="billingtoo" value=""
											onclick="FillBilling(this.form)" class="allClasses">
									</span>
									</label>
								</div>
							</div>
						</div>
					</div>
				</div>
				<h5 class="pageTitle bold">CORRESPONDENCE ADDRESS</h5>
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4"> Street Name : </label>
							<div class="col-md-6">
								<sj:textfield
									name="onlineApplicationDetails.tempararyAddress.streetName"
									id="streetName1" cssClass="form-control input-medium as-inputl"
									maxlength="200"></sj:textfield>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4"> City : </label>
							<div class="col-md-6">
								<sj:textfield
									name="onlineApplicationDetails.tempararyAddress.city"
									id="city1" cssClass="form-control input-medium as-inputl"
									maxlength="60"></sj:textfield>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4"> Taluk : </label>
							<div class="col-md-6">
								<sj:textfield
									name="onlineApplicationDetails.tempararyAddress.taluka"
									id="taluka1" cssClass="form-control input-medium as-inputl"
									maxlength="200"></sj:textfield>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4"> District : </label>
							<div class="col-md-6">
								<sj:textfield
									name="onlineApplicationDetails.tempararyAddress.districtName" id="districtName1"
									cssClass="form-control input-medium as-inputl" maxlength="40"></sj:textfield>
							</div>
						</div>
					</div>
				</div>
				
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4"> State : </label>
							<div class="col-md-6">
								<s:select id="state1" list="statesList"
									cssClass="form-control input-medium" listKey="stateCode"
									listValue="stateName" headerKey="" headerValue="- Select -"
									name="onlineApplicationDetails.tempararyAddress.state" />
							</div>
						</div>
					</div>
					<div class=" col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4"> Pincode : </label>
							<div class="col-md-6">
								<sj:textfield
									name="onlineApplicationDetails.tempararyAddress.postalCode"
									id="pincode1"
									cssClass="numeric form-control input-medium as-inputl"
									maxlength="6"></sj:textfield>
							</div>
						</div>
					</div>
				</div>
				
				
				
				
				<h4 class="pageTitle bold form-section">CASTE AND COMMUNITY	INFORMATION</h4>
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4"> Nationality : </label>
							<div class="col-md-6">
								<sj:textfield name="onlineApplicationDetails.nationality"
									id="nationality" cssClass="form-control input-medium as-inputl"
									maxlength="50"></sj:textfield>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4"> Select Religion :
							</label>
							<div class="col-md-6">
								<s:select list="tempList1" listKey="id"
									listValue="skillTypeName" cssClass="form-control input-medium"
									id="religion" name="onlineApplicationDetails.religionId.id"
									headerKey="0" headerValue="- Select -">
								</s:select>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6">
						<%-- <div class="form-group">
							<label class="control-label col-md-4">Student Community : </label>
							<div class="col-md-6">
								<s:select id="castName" list="castSettingList" listKey="id"
									listValue="castName" cssClass="form-control input-medium"
									headerKey="" headerValue="- Select -" name="person.castId"
									theme="simple"
									onchange="javascript:getSubCastDetailsByCast(this);" />
							</div>
						</div> --%>
						
						<jsp:include
						page="/WEB-INF/pages/admin/admission/castSettings/ajaxCastListByState.jsp" />
						
					</div>
					<div class="col-md-6" id="resultsDiv2">
							<jsp:include
						page="/WEB-INF/pages/admin/admission/castSettings/ajaxGetOnlineSubCastListByCastId.jsp" />
					</div>
				</div>
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4"> Community Number : </label>
							<div class="col-md-7">
								<sj:textfield name="onlineApplicationDetails.communityNumber" id="communityNumber"
									cssClass="form-control input-medium as-inpu" maxlength="50"></sj:textfield>
							</div>
						</div>
					</div>
					
					
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4"> Student Caste Certificate No :
							</label>
							<div class="col-md-7">
								<sj:textfield name="onlineApplicationDetails.studentCasteCertificate" id="studentCasteCertificate"
									cssClass="form-control input-medium as-inpu" maxlength="50"></sj:textfield>
							</div>
						</div>
					</div>
					
					
					<%-- <div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4"> Ration Card Number :
							</label>
							<div class="col-md-7">
								<sj:textfield name="onlineApplicationDetails.rationCardNumber" id="rationCardNumber"
									cssClass="form-control input-medium as-inpu" maxlength="50"></sj:textfield>
							</div>
						</div>
					</div> --%>
				</div>
				
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4"> Father Caste : </label>
							<div class="col-md-6">
								<sj:textfield name="onlineApplicationDetails.fatherCaste"
									id="fatherCaste" cssClass="form-control input-medium as-inputl"
									maxlength="50"></sj:textfield>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4"> Father Caste Certificate No :
							</label>
							<div class="col-md-6">
								<sj:textfield name="onlineApplicationDetails.fatherCasteCertificate"
									id="fatherCasteCertificate" cssClass="form-control input-medium as-inputl"
									maxlength="50"></sj:textfield>
							</div>
						</div>
					</div>
				</div>
				
				
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4"> Mother Caste : </label>
							<div class="col-md-6">
								<sj:textfield name="onlineApplicationDetails.motherCaste"
									id="motherCaste" cssClass="form-control input-medium as-inputl"
									maxlength="50"></sj:textfield>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4"> Mother Caste Certificate No :
							</label>
							<div class="col-md-6">
								<sj:textfield name="onlineApplicationDetails.motherCasteCertificate"
									id="motherCasteCertificate" cssClass="form-control input-medium as-inputl"
									maxlength="50"></sj:textfield>
							</div>
						</div>
					</div>
				</div>
				
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4"> Social Category : </label>
							<div class="col-md-6">
								<sj:textfield name="onlineApplicationDetails.socialCategory"
									id="socialCategory" cssClass="form-control input-medium as-inputl"
									maxlength="50"></sj:textfield>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4"> Special/Other :
							</label>
							<div class="col-md-6">
								<sj:textfield name="onlineApplicationDetails.special"
									id="special" cssClass="form-control input-medium as-inputl"
									maxlength="50"></sj:textfield>
							</div>
						</div>
					</div>
				</div>
				
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4"> Bhagyalakshmi Bond No : </label>
							<div class="col-md-6">
								<sj:textfield name="onlineApplicationDetails.bhagyalakshmiBondNo"
									id="bhagyalakshmiBondNo" cssClass="form-control input-medium as-inputl"
									maxlength="50"></sj:textfield>
							</div>
						</div>
					</div>
					
				</div>
				
				
				<h4 class="pageTitle bold form-section">STUDENT ADDITIONAL INFORMATION</h4>
				<h5 class="pageTitle bold">SIBLINGS</h5>
				
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4"> No. Of Elder Brothers: </label>
							<div class="col-md-6">
								<sj:textfield name="onlineApplicationDetails.noOfElder"
									id="noOfElder" cssClass="form-control input-medium as-inputl"
									maxlength="50"></sj:textfield>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4"> No. of Younger Brothers:
							</label>
							<div class="col-md-6">
								<sj:textfield name="onlineApplicationDetails.noOfYounger"
									id="noOfYounger" cssClass="form-control input-medium as-inputl"
									maxlength="50"></sj:textfield>
							</div>
						</div>
					</div>
				</div>
				<%-- <div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4"> No. Of Brothers : </label>
							<div class="col-md-6">
								<sj:textfield name="onlineApplicationDetails.noOfBrother"
									id="noOfBrother" cssClass="form-control input-medium as-inputl"
									maxlength="50"></sj:textfield>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4"> No. of Sisters :
							</label>
							<div class="col-md-6">
								<sj:textfield name="onlineApplicationDetails.noOfSisters"
									id="noOfSisters" cssClass="form-control input-medium as-inputl"
									maxlength="50"></sj:textfield>
							</div>
						</div>
					</div>
				</div> --%>
				
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4"> No. Of Elder Sisters : </label>
							<div class="col-md-6">
								<sj:textfield name="onlineApplicationDetails.noOfElderSisters"
									id="noOfBrother" cssClass="form-control input-medium as-inputl"
									maxlength="50"></sj:textfield>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4"> No. of Younger Sisters :
							</label>
							<div class="col-md-6">
								<sj:textfield name="onlineApplicationDetails.noOfYoungerSisters"
									id="noOfSisters" cssClass="form-control input-medium as-inputl"
									maxlength="50"></sj:textfield>
							</div>
						</div>
					</div>
				</div>
				
				<h4 class="pageTitle bold form-section">OTHER INFORMATION</h4>
				
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4"> Place Of Birth : </label>
							<div class="col-md-8">
								<sj:textfield name="onlineApplicationDetails.placeOfBirth"
									id="placeOfBirth" cssClass="form-control input-medium as-input"
									maxlength="50"></sj:textfield>
							</div>
						</div>
					</div>
					
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4"> Select Mother Tongue
								: </label>
							<div class="col-md-7">
								<s:select list="objectList" listKey="id" listValue="name"
									cssClass="form-control input-medium" label="Select MotherToung"
									name="onlineApplicationDetails.motherToungId" headerKey="" headerValue="- Select -">
								</s:select>
							</div>
						</div>
					</div>
				</div>
				
				
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4"> Personal
								Identification1 : </label>
							<div class="col-md-7">
								<sj:textfield name="onlineApplicationDetails.identification1" id="identification1Id"
									cssClass="form-control input-medium as-inpu" maxlength="50"></sj:textfield>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4"> Personal
								Identification2 : </label>
							<div class="col-md-7">
								<sj:textfield name="onlineApplicationDetails.identification2" id="identification2Id"
									cssClass="form-control input-medium as-inpu" maxlength="50"></sj:textfield>
							</div>
						</div>
					</div>
				</div>
				
				
				<div class="row">
			
			
			<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> <span
							class="required">*</span>Student Disability  :
						</label>
						<div class="col-md-6">
						<div class="radio-list">
								<label class="radio-inline"> <input type="radio"
									id="phIdYes" value="true" name="plTitle"
									onclick="javascript: displayPHCDiv(this.value)"> Yes
								</label> <label class="radio-inline"> <input type="radio"
									name="plTitle" id="phIdNo" value="false"
									onclick="javascript: displayPHCDiv(this.value)"> No
								</label>
							</div>
							<span class="phStatus"
								id="<s:property value='onlineApplicationDetails.phId'/>"></span>
						</div>
					</div>
				</div>
				
				
				<!-- <div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> <span
							class="required">*</span>Student Disability :
						</label>
						<div class="col-md-6">
							<div class="make-switch has-switch" data-id="Y" data-value="N"
								id="personPhidDivId" style="width: 120px" data-off="warning"
								data-on="success" data-off-label="No" data-on-label="Yes">
								<input type="radio" class="toggle" id="stPhId"> <input
									type="hidden" name="plTitle" value="N" id="personPhid">
							</div>
						</div>
					</div>
				</div> -->
				</div>
				<div class="row" id="physicallyHandiCappedDivId" style="display: none;">
					<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4">
								Disability Details :</label>
							<div class="col-md-7">
								<sj:textarea rows="3" cols="20" name="onlineApplicationDetails.physicallyHandicappedDesc" maxCharsData="1000" tabindex="3" cssClass="form-control word_count"></sj:textarea>
								<span class="help-block">
									<div class="counter"></div> </span>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4">
								Disability Documents : 
							</label>
							<div class="col-md-7">
							<div class="fileUpload fileupload-new" data-provides="fileUpload">
									<div class="input-append">
										<span class="btn default"> <s:file name="fileUpload"
												id="photoURL1s" cssClass="fileUpload"></s:file>
										</span>
									</div>
								</div>
								<!-- <input type="file" class="btn default browseButton" name="fileUpload" /> -->
								<%-- <s:file name="upload" id="uploadDocs" multiple="multiple" cssClass="btn default"></s:file> --%>
							</div>
						</div>
					</div>
				</div>
				
				<div class="row">
				 <div class="col-md-12">
				  <s:if test="%{tempList != null && !tempList.isEmpty()}">
					 <div class="col-md-2" style="width: 15.0%"></div>
					 <div class="col-md-4">
						<table class="table table-striped table-bordered table-hover table-full-width">
							<thead>
								<tr>
									<th>Name</th>
									<s:if test='%{#session.previousYear == "N"}'>
										<th>Delete</th>
									</s:if>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="tempList">
									<tr>
										<td><a rel="nofollow" href='<c:url value='/student/downloadPhysicallyHandicappedDocuments.do'/>?tempId1=<s:property value="onlineApplicationDetails.id" />&fileName=<s:property value="fileName" />'><s:property
													value="fileName" /> </a></td>
										<s:if test='%{#session.previousYear == "N"}'>
											<td><s:url id="removeFile"
													action="ajaxRemoveStudentDisabilityDocuments" namespace="/admin"
													escapeAmp="false" includeParams="all">
													<s:param name="tempId1" value="onlineApplicationDetails.id"></s:param>
													<s:param name="fileName" value="fileName"></s:param>
												</s:url>
												 	 <s:div cssClass="btn btn-xs red emsFileRemove"
														id='%{removeFile}' theme="simple" title="Delete this File">
														<i class="fa fa-trash-o"></i>
													</s:div>
												</td>
										</s:if>
									</tr>
								</s:iterator>
							</tbody>
						</table>
					</div>
					<div class="col-md-3">
						<div class="linkRight" align="right">
							<a rel="nofollow"
								href='<c:url value='/student/downloadPhysicallyHandicappedDocuments.do'/>?tempId1=<s:property value="onlineApplicationDetails.id" />'
								class="btn blue" style="padding: 0 2px;">Download All</a>&nbsp;&nbsp;
							<s:if test='%{#session.previousYear == "N"}'> 	
								|&nbsp;&nbsp;
							<s:url id="removeDocuments"
									action="ajaxRemoveStudentDisabilityDocuments" namespace="/admin" escapeAmp="false" includeParams="all">
									<s:param name="tempId1" value="%{onlineApplicationDetails.id}"></s:param>
									<s:param name="status" value="%{onlineApplicationDetails.status}"></s:param>
									<s:param name="eventId">deleteAll</s:param>
								</s:url>
								 <s:if test='%{onlineApplicationDetails.status == "P"}'>
									<sj:a id="removeDocumentsLink" href="%{removeDocuments}"
										targets="admissionsPendingContentAppli" cssClass="btn btn-xs red">Delete All</sj:a>
								</s:if>
								 <s:elseif test='%{onlineApplicationDetails.status == "S"}'>
								 	<sj:a id="removeDocumentsLink" href="%{removeDocuments}"
									targets="academicsApprovedContent" cssClass="btn btn-xs red">Delete All</sj:a>
								 </s:elseif>
								 <s:elseif test='%{onlineApplicationDetails.status == "R"}'>
								 	<sj:a id="removeDocumentsLink" href="%{removeDocuments}"
									targets="academicsRejectedContentDiv" cssClass="btn btn-xs red">Delete All</sj:a>
								 </s:elseif>
								 <s:else>
								 	<sj:a id="removeDocumentsLink" href="%{removeDocuments}"
									targets="admissionsPendingContentAppli" cssClass="btn btn-xs red">Delete All</sj:a>
								 </s:else>
							</s:if>
						</div>
					</div>
					</s:if>
					<s:else>
						<div class="alert alert-info"> Currently there are no files. Please upload files for student disability.</div>
					</s:else>
					</div>
				</div>
				
			</div>
			
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> Any Health Diseases : </label>
						<div class="col-md-8">
							<sj:textfield name="onlineApplicationDetails.anyHealthDiseases" id="anyHealthDiseases"
								cssClass="form-control input-medium as-input"
								maxlength="10"></sj:textfield>
						</div>
					</div>
				</div>
				
				<div class=" col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> Blood Group : </label>
						<div class="col-md-6">
							<s:select id="bGroup" headerKey="" headerValue="- Select -"
								list="#{'A+':'A+ve','A-':'A-ve','A1+':'A1+ve','A1-':'A1-ve','A1B+':'A1B+ve','A1B-':'A1B-ve','A2B+':'A2B+ve','A2B-':'A2B-ve','A2+':'A2+ve','A2-':'A2-ve','B+':'B+ve','B-':'B-ve','AB+':'AB+ve','AB-':'AB-ve','O+':'O+ve','O-':'O-ve'}"
								name="onlineApplicationDetails.bloodGroup"
								cssClass="form-control input-medium" />
						</div>
					</div>
				</div>
				
			</div>
				
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> Height  (C.M) : </label>
						<div class="col-md-8">
							<sj:textfield name="onlineApplicationDetails.height" id="height"
								cssClass="numericDot  form-control input-medium as-input"
								maxlength="10"></sj:textfield>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> Weight : </label>
						<div class="col-md-8">
							<sj:textfield name="onlineApplicationDetails.weight" id="weight"
								cssClass="form-control input-medium as-input numericDot"
								maxlength="10"></sj:textfield>
						</div>
					</div>
				</div>
				
			</div>
			
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> Urban/Rural : </label>
						<div class="col-md-8">
							<sj:textfield name="onlineApplicationDetails.urbanOrRural" id="urbanOrRural"
								cssClass="form-control input-medium as-input"
								maxlength="10"></sj:textfield>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> Any Other Language Spoken : </label>
						<div class="col-md-8">
							<sj:textfield name="onlineApplicationDetails.anyOtherLanguageSpoken" id="anyOtherLanguageSpoken"
								cssClass="form-control input-medium as-input"
								maxlength="50"></sj:textfield>
						</div>
					</div>
				</div>
				
			</div>
			
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> Hobbies : </label>
						<div class="col-md-7">
							<sj:textarea name="onlineApplicationDetails.interestsAndHobbies"
								id="maxlength_textarea3" cssClass="form-control"
								placeholder="This textarea has a limit of 200 chars." rows="4"
								cols="20" maxlength="200"></sj:textarea>
						</div>
					</div>
				</div>
				
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> Special Interest : </label>
						<div class="col-md-7">
							<sj:textarea name="onlineApplicationDetails.specialInterest"
								id="maxlength_textarea3" cssClass="form-control"
								placeholder="This textarea has a limit of 200 chars." rows="4"
								cols="20" maxlength="200"></sj:textarea>
						</div>
					</div>
				</div>
			</div>
			
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> Specific Point to be Noted : </label>
						<div class="col-md-8">
							<sj:textfield name="onlineApplicationDetails.specificPointNoted" id="specificPointNoted"
								cssClass="form-control input-medium as-input"
								maxlength="50"></sj:textfield>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> Student/Parent's Bank Account Name : </label>
						<div class="col-md-8">
							<sj:textfield name="onlineApplicationDetails.studentOrParentBankAccountName" id="studentOrParentBankAccountName"
								cssClass="form-control input-medium as-input"
								maxlength="10"></sj:textfield>
						</div>
					</div>
				</div>
				
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4">Student/Parent's Bank Account Number : </label>
						<div class="col-md-8">
							<sj:textfield name="onlineApplicationDetails.studentOrParentBankAccountNumber" id="studentOrParentBankAccountNumber"
								cssClass="numeric  form-control input-medium as-input"
								maxlength="25"></sj:textfield>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> Bank IFSC Code : </label>
						<div class="col-md-8">
							<sj:textfield name="onlineApplicationDetails.bankIFSCCode" id="bankIFSCCode"
								cssClass="form-control input-medium as-input"
								maxlength="20"></sj:textfield>
						</div>
					</div>
				</div>
				
			</div>
				
				<h4 class="pageTitle bold form-section">COLLECTED DOCUMENTS</h4>
				
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4"> Summary : </label>
							<div class="col-md-7">
								<sj:textarea name="onlineApplicationDetails.collectedDocuments" id="collectedDocuments"
									cssClass="word_count form-control"
									placeholder="This textarea has a limit of 200 chars." rows="4"
									cols="20" maxlength="200"></sj:textarea>
								<span class="help-block"> Maxlength is 200 chars. </span>
							</div>
						</div>
					</div>
				</div>
				
				
				<div class="row">
				
				<s:if test='%{admissionSettings.testConducted}'>
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4"> Entrance Marks : </label>
							<div class="col-md-6">
								<sj:textfield name="onlineApplicationDetails.entranceMarks"
									id="entranceMarks"
									onchange="javascript:validateEntranceMarks(%{admissionSettings.entranceExamTotalMarks},this);" cssClass="numeric form-control input-medium as-inputl"
									maxlength="5"></sj:textfield>
							</div>
						</div>
					</div>
				</s:if>
				
				<s:if test='%{onlineApplicationDetails.status == "P"}'>
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4"> Process
								Application </label>
							<div class="col-md-8">
								<div class="radio-list">
									<label class="radio-inline"> 
										<s:if
											test='%{onlineApplicationDetails.status == "S"}'>
											<input type="radio" value="S" id="applicationStatus"
												name="onlineApplicationDetails.status"
												onclick="applicationProcessValidation();" checked="checked">
										</s:if> 
										<s:else>
											<input type="radio" value="S" id="applicationStatus"
												name="onlineApplicationDetails.status"
												onclick="applicationProcessValidation();">
										</s:else> Short List
									</label> 
									<label class="radio-inline"> 
										<s:if test='%{onlineApplicationDetails.status == "R"}'>
											<input type="radio" value="R" id="applicationStatus"
												name="onlineApplicationDetails.status"
												onclick="applicationProcessValidation();" checked="checked">
										</s:if> 
										<s:else>
											<input type="radio" value="R" id="applicationStatus"
												name="onlineApplicationDetails.status"
												onclick="applicationProcessValidation();">
										</s:else> Reject
									</label>
									<label class="radio-inline"> 
											<input type="radio" value="CS" id="applicationStatus"
												name="onlineApplicationDetails.status"
												onclick="applicationProcessValidation();">
										 		Confirm Student
									</label>
								</div>
							</div>
						</div>
					</div>
				</s:if>
				<s:elseif test='%{onlineApplicationDetails.status == "S"}'>
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4"> Process
								Application </label>
							<div class="col-md-8">
								<div class="radio-list">
									<label class="radio-inline"> <s:if
											test='%{onlineApplicationDetails.status == "S"}'>
											<input type="radio" value="S" id="applicationStatus"
												name="onlineApplicationDetails.status"
												onclick="applicationProcessValidation();" checked="checked">
										</s:if> <s:else>
											<input type="radio" value="S" id="applicationStatus"
												name="onlineApplicationDetails.status"
												onclick="applicationProcessValidation();">
										</s:else> Short List
									</label> <label class="radio-inline"> <s:if
											test='%{onlineApplicationDetails.status == "R"}'>
											<input type="radio" value="R" id="applicationStatus"
												name="onlineApplicationDetails.status"
												onclick="applicationProcessValidation();" checked="checked">
										</s:if> <s:else>
											<input type="radio" value="R" id="applicationStatus"
												name="onlineApplicationDetails.status"
												onclick="applicationProcessValidation();">
										</s:else> Reject
									</label>
									<label class="radio-inline"> 
											<input type="radio" value="CS" id="applicationStatus"
												name="onlineApplicationDetails.status"
												onclick="applicationProcessValidation();">
										 		Confirm Student
									</label>
								</div>
							</div>
						</div>
					</div>
				</s:elseif>
				<s:elseif test='%{onlineApplicationDetails.status == "R"}'>
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4"> Process
								Application </label>
							<div class="col-md-8">
								<div class="radio-list">
									<label class="radio-inline"> <s:if
											test='%{onlineApplicationDetails.status == "S"}'>
											<input type="radio" value="R" id="applicationStatus"
												name="onlineApplicationDetails.status">
										</s:if> <s:else>
											<input type="radio" value="S" id="applicationStatus"
												name="onlineApplicationDetails.status">
										</s:else> Short List
									</label> 
									<label class="radio-inline"> 
										<s:if
											test='%{onlineApplicationDetails.status == "R"}'>
											<input type="radio" value="R" id="applicationStatus"
												name="onlineApplicationDetails.status"
												onclick="applicationProcessValidation();" checked="checked">
										</s:if> 
										<s:else>
											<input type="radio" value="R" id="applicationStatus"
												name="onlineApplicationDetails.status"
												onclick="applicationProcessValidation();">
										</s:else> Reject
									</label>
									<label class="radio-inline"> 
											<input type="radio" value="CS" id="applicationStatus"
												name="onlineApplicationDetails.status"
												onclick="applicationProcessValidation();">
										 		Confirm Admission
									</label>
								</div>
							</div>
						</div>
					</div>
				</s:elseif>
			</div>
			<div class="row">
				<s:if
					test='%{onlineApplicationDetails.status == "R" && onlineApplicationDetails.rejectApplicationDesc != null && !onlineApplicationDetails.rejectApplicationDesc.isEmpty()}'>
					<div class="col-md-6">
						<label class="control-label col-md-4"> Reason for reject
							application : </label> <br />
						<s:property
							value="onlineApplicationDetails.rejectApplicationDesc" />
					</div>
				</s:if>
				<div class="col-md-6" style="display: none;"
					id="rejectAppicationsDescriptionCont">
					<div class="form-group">
						<label class="control-label col-md-4"> <span
							class="required">*</span> Reason for reject application :
						</label>
						<div class="col-md-6">
							<s:textarea cols="3" rows="2" id="rejectApplDescription"
								name="onlineApplicationDetails.rejectApplicationDesc"
								cssClass="form-control input-xlarge word_count1"
								maxCharsData="255"></s:textarea>
							<span class="counter1"></span>
						</div>
					</div>
				</div>
			</div>
			
			<div class="row">
				<div class="col-md-6" style="display: none;" id="confirmStudentDivId">
					<div class="form-group">
						<label class="control-label col-md-4"> <span
							class="required">*</span> Admission Number :
						</label>
						<s:if test="%{admissionSettings.atuoGenerationAdmissionNumberStatus}">
							<div class="col-md-6">
								<s:if test="%{admissionNumber != null}">
									<sj:textfield name="admissionNumber" id="studAdmissionNumber" cssClass="form-control input-medium as-input studAdmissionNumber"
									maxlength="20" readonly="true" ></sj:textfield>
								</s:if>
								<s:else>
									<sj:textfield name="admissionNumber" id="studAdmissionNumber" cssClass="form-control input-medium as-input studAdmissionNumber"
									maxlength="20" ></sj:textfield>
								</s:else>
							</div>
						</s:if><s:else>
							<div class="col-md-6">
								<sj:textfield name="admissionNumber" id="studAdmissionNumber" cssClass="form-control input-medium as-input studAdmissionNumber"
									maxlength="20" ></sj:textfield>
							</div>
						</s:else>
						
					</div>
				</div>
			</div>
			<div class="row">
		       <s:if test='%{customerByCustId.getAlphaNumericRollNumber()=="Y"}'>
				<div class="col-md-6" style="display: none;" id="confirmStudentDivId1">
					<div class="form-group">
						<label class="control-label col-md-4"> Roll Number : </label>
						<div class="col-md-6">
							<sj:textfield name="onlineApplicationDetails.rollNumber" id="rollNumber"
								cssClass="form-control input-medium as-input"
								maxlength="15"></sj:textfield>
						</div>
					</div>
				</div>
			 </s:if> 
			</div>
						
				<div class="form-actions fluid">
					<div class="col-md-offset-2 col-md-6">
						<sj:submit cssClass="btn blue" value="Save" indicator="indicator"
							validate="true" onBeforeTopics="applicationInformation"
							formIds="addStudentOfflineInfo" targets="mainContentDiv" />
						<%-- <sj:submit cssClass="submitBt btn green long" value="Save & Print"
							indicator="indicator" targets="mainContentDiv"
							onBeforeTopics="applicationInformation"
							formIds="addStudentOfflineInfo"
							onCompleteTopics="doPrintStudentAppFee" validate="true" /> --%>
						<s:url id="doCancelSettings" action="ajaxGetOnlineAdmissions"
							includeParams="all" escapeAmp="false" namespace="/admin">
						</s:url>
						<sj:a href="%{doCancelSettings}" cssClass="btn default"
							indicator="indicator" targets="mainContentDiv" button="false"
							buttonIcon="ui-icon-plus">Cancel</sj:a>
					</div>
				</div>
			</div>
		</s:form>
	</s:if>
	<s:else>
		<div class="alert alert-info">Currently there are no admissions
			opened for classes.</div>
	</s:else>
</s:if>
<s:else>
	<div class="alert alert-info">Currently there are no admissions
		opened.</div>
</s:else>
<div id="studentsImgDiv"></div>
<span class="receiptNumber" id="<s:property value='tempId'/>"></span>
<script language="JavaScript" type="text/javascript" src="${pageContext.request.contextPath}/plugins/bootstrap-switch/static/js/bootstrap-switch.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		UIExtendedModals.init();
		FormComponents.init();
		FormAdvanced.init();
		showTransportInfo();
		
		$("input:checkbox, input:radio:not('.toggle')")
				.uniform();
		changePageTitle("Add Application");
		$('#pendingApplications').hide();
		$('#createApplicationForm').show();
		$("#admissionReceiptNumber").val(
				$('span.receiptNumber').attr('id'));
		$('#manageOnlinApplication').hide();
		$("#admissionReceiptNumber")
				.autoCheck(
						"${pageContext.request.contextPath}/admin/ajaxCheckStudentAdmissionReceiptNumber.do",
						{
							minChars : 1,
							min : "no"
						});
		$('.numeric').numeric();
     $.destroyTopic('applicationInformation');
     $ .subscribe('applicationInformation',function(event, data) {
    	 
    	var studAdmissionNumberStr = $('input.studAdmissionNumber').parents('div').next('div').find('p.word-taken').html();
 		if (studAdmissionNumberStr == 'Already taken!!!') {
 			event.originalEvent.options.submit = false;
 		}
 		
 		var rollNumber = $("[name='onlineApplicationDetails.rollNumber']").val();
		if ($('p.word-taken').html() == 'Already taken!!!') {
			event.originalEvent.options.submit = false;
		}

		var transportMode = $("input[name='onlineApplicationDetails.transportMode']:checked").val();
		if (isNonEmpty($('input.fileName').val())) {
			var filename = $('input.fileName').val().toLowerCase();
			if (filename .lastIndexOf(".jpg") == -1 && filename.lastIndexOf(".png") == -1 && filename.lastIndexOf(".jpeg") == -1) {
				alert("File not acceped. Please upload your file in jpg or jpeg or  png");
				event.originalEvent.options.submit = false;
			}
		}
		if (isNonEmpty(transportMode) && 'T' == transportMode) {
				$('select#routeId').addClass('required');
				$('select#boardingId').addClass('required');
				$('select#vehicleId').addClass('required');
			} else {
				$('select#routeId')
						.removeClass('required');
				$('select#boardingId')
						.removeClass('required');
				$('select#vehicleId')
						.removeClass('required');
			}
		
		bplStatus = $("input.bplStatusClass:checked").val();
		if(bplStatus == "Y")
		{
			bplNumber = $('#bplNumber').val();
			if(!isNonEmpty(bplNumber))
			{
				alert("please enter Below Poverty Line Number.");
				event.originalEvent.options.submit = false;
			}
		}
		
		var entrancepassmarks = '<s:property value="onlineApplicationDetails.entranceExamPassMarks"/>';
		var studentMarks = $('input#entranceMarks').val();
		var status = '<s:property value="onlineApplicationDetails.status"/>';
		if(isNonEmpty(entrancepassmarks) && isNonEmpty(studentMarks)){
			if(status !='R'){
				 if ($("input[name='onlineApplicationDetails.status']:checked").val() == "S") {
					if((Number(studentMarks) < Number(entrancepassmarks))){
						 var answer = confirm("This applicant is fail in entrance examination,But you are selected this application to shortlist,Do you want to proceed ?");
						if(answer){
							event.originalEvent.options.submit = true;
						}else{
							event.originalEvent.options.submit = false;
						}
					}
				}
				 if ($("input[name='onlineApplicationDetails.status']:checked").val() == '' || $("input[name='onlineApplicationDetails.status']:checked").val() == null || $("input[name='onlineApplicationDetails.status']:checked").val() == undefined) {
					if((Number(studentMarks) < Number(entrancepassmarks))){
						 var answer = confirm("This applicant is fail in entrance examination,So this application will reject,Do you want to proceed ?");
						if(answer){
							event.originalEvent.options.submit = true;
						}else{
							event.originalEvent.options.submit = false;
						}
					}
					else if((Number(studentMarks) >= Number(entrancepassmarks))){
						 var answer = confirm("This applicant is pass in entrance examination,So this application will shortlist,Do you want to proceed ?");
							if(answer){
								event.originalEvent.options.submit = true;
							}else{
								event.originalEvent.options.submit = false;
							}
						}
				}
			}else{
				if ($("input[name='onlineApplicationDetails.status']:checked").val() == "S") {
				if((Number(studentMarks) < Number(entrancepassmarks))){
					 var answer = confirm("This applicant is fail in entrance examination,But you are selected this application to shortlist,Do you want to proceed ?");
					if(answer){
						event.originalEvent.options.submit = true;
					}else{
						event.originalEvent.options.submit = false;
					}
				}
				}
			}
			
		}
		
		});

	});
	/* function getSubCastDetailsByCast(selectBox) {
		var castId = selectBox.value;
		var url = jQuery.url
				.getChatURL("/admin/ajaxStudentSubCastDetailsByCastDetails.do");
		if (castId.length == 0) {
			alert("!Oops select Cast.");
		} else {
			$("#resultsDiv2")
					.html(
							'<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
			var pars = "personVo.castId=" + castId;
			$
					.ajax({
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
	} */
	function viewPopupStudensCapture() {
		$.ajax({
			url : jQuery.url.getChatURL("/admin/ajaxCapturePhoto.do"),
			cache : false,
			success : function(html) {
				$("#studentsImgDiv").html(html);
			}
		});
	}
	function getAjaxAdmissionAcademicYear(academicYearId) {
		if (isNonEmpty(academicYearId)) {
			var pars = "academicYearId=" + academicYearId;
			$("#allClasses")
					.html(
							'<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
			var url = jQuery.url
					.getChatURL("/admin/ajaxGetAdmissionsOpendClasses.do");
			$.ajax({
				url : url,
				cache : false,
				data : pars,
				success : function(html) {
					$("#allClasses").html(html);
				}
			});
		}
	}

	$("label.transportMode").click(function() {
		var transportMode = $(this).find("input").val();
		if (isNonEmpty(transportMode) && 'T' == transportMode) {
			$('#inputboxhideText').show();
		} else {
			$('#inputboxhideText').hide();
		}
	});

	$('.numericDot').numeric({
		allow : "."
	});
	function FillBilling(offlineApplicationForm) {
		
		if (offlineApplicationForm.billingtoo.checked == true) {
			offlineApplicationForm.streetName1.value = offlineApplicationForm.streetName.value;
			offlineApplicationForm.city1.value = offlineApplicationForm.city.value;
			offlineApplicationForm.taluka1.value = offlineApplicationForm.taluka.value;
			offlineApplicationForm.districtName1.value = offlineApplicationForm.districtName.value;
			offlineApplicationForm.state1.value = offlineApplicationForm.state.value;
			offlineApplicationForm.pincode1.value = offlineApplicationForm.pincode.value;
		} else {
			offlineApplicationForm.streetName1.value = "";
			offlineApplicationForm.city1.value = "";
			offlineApplicationForm.taluka1.value = "";
			offlineApplicationForm.districtName1.value = "";
			offlineApplicationForm.state1.value = "";
			offlineApplicationForm.pincode1.value = "";
		}

	}
	$.subscribe('doPrintStudentAppFee', function(event, data) {
		$('#stuAppFeePrintReport').submit();
		$.destroyTopic('doPrintStudentAppFee');
	});
	function checkCommittedFee(studyClassId) {
	var CommittedFeeStatus ='<s:property  value="customer.committedFeeStatus" />';
	if(isNonEmpty(CommittedFeeStatus) && CommittedFeeStatus == "Y"){
		var categoryId = $("#categoryId").val();
		var academicYearId = $("#academicYearId").val();
		if (categoryId != 0 && academicYearId != 0) {
			var feeURL = jQuery.url
					.getChatURL("/common/ajaxCheckClassWiseCommittedFee.do?studyClassId="
							+ studyClassId
							+ "&categoryId="
							+ categoryId
							+ "&academicYearId="
							+ academicYearId
							+ "&tempString=AD");
			$.ajax({
				url : feeURL,
				cache : false,
				dataType : 'json',
				success : function(response) {
					if (isNonEmpty(response)) {
						var isCommittedFee = response.isCommittedFee;
						if (isNonEmpty(isCommittedFee)) {
							if (isCommittedFee = "true")
								$('#committedFeeDiv').show();
							else {
								$('#committedFeeDiv').hide();
								$("#committedFee").val(0.0);
							}
						} else {
							$('#committedFeeDiv').hide();
							$("#committedFee").val(0.0);
						}
					}
				}
			});
		} else {
			alert("Please select category name and academic year.");
			$("#studyClassId").val('');
		}
     }
	}
	function verifyDate() {
		var date0 = $('#date0').val();
		var studentJoiningDate = $('#studentJoiningDate').val();
		if (isNonEmpty(studentJoiningDate) && isNonEmpty(date0)) {
			date0 = new Date(date0);
			studentJoiningDate = new Date(studentJoiningDate);
			var birthYear = new Date(date0.setYear(date0.getFullYear() + 2));
			if (studentJoiningDate <= birthYear) {
				$('#studentJoiningDate').val('');
				alert("Date of joining should be after 2 years of birth date.");
			}
			studentJoiningDate = new Date(studentJoiningDate);
			studentJoiningDate.setDate(studentJoiningDate.getDate() + 1);
			$('#nextYrDate')
					.datepicker("option", 'minDate', studentJoiningDate);
		}
	}
	$("#height").change(function() {
		var heightText = ($(this).val()).replace('.', '');
		if (Math.floor(heightText) != heightText) {
			alert("Please enter numbers.");
			$("#height").val('');
			return false;
		}
	});
	$("#weight").change(function() {
		var weightText = ($(this).val()).replace('.', '');
		if (Math.floor(weightText) != weightText) {
			alert("Please enter numbers.");
			$("#weight").val('');
			return false;
		}
	});
	$("#annualIncome").change(function() {
		var annualIncome = ($(this).val()).replace('.', '');
		if (Math.floor(annualIncome) != annualIncome) {
			alert("Please enter numbers.");
			$("#annualIncome").val('');
			return false;
		}
	});
	$("#mobileNumber").change(function() {
		var text = $(this).val();
		var moble = text.replace('+', '');
		var number = moble.replace('-', '');
		if (Math.floor(number) != number) {
			alert("Please enter numbers.");
			$("#mobileNumber").val('');
			return false;
		}
	});
	$("#phoneNumber").change(function() {
		var text = $(this).val();
		if (Math.floor(text) != text) {
			alert("Please enter numbers.");
			$("#phoneNumber").val('');
			return false;
		}
	});
	$("#pincode").change(function() {
		var text = $(this).val();
		if (Math.floor(text) != text) {
			alert("Please enter numbers.");
			$("#pincode").val('');
			return false;
		}
	});
	function validateAadhaarCardNumber(txtAddhaarId) {
		var aadhaar = /^(\+91-|\+91|0)?\d{12}$/;
		if (aadhaar.test($.trim(txtAddhaarId)) == false) {
			alert("Please enter valid Aadhaar card number.");
			$("#aadharCardNumber").val('');
			$("#aadharCardNumber").focus();
			return false;
		}else if((txtAddhaarId.length == 12)) {
			return true;
		}
	}
	
	function validateStuMobNumber(txtMobId) {
		if (isNonEmpty(txtMobId)) {
			if (!(txtMobId.length == 10)) {
				alert("Please enter 10 digits mobile number.");
				$("#studentMobile").val("");
				$("#studentMobile").focus();
				return false;
			}	
		}else if((txtMobId.length == 10)) {
			return true;
		}
	}
	function displayPHCDiv(phcVal) {
		if (phcVal == 'true')
			$('#physicallyHandiCappedDivId').show();
		else
			$('#physicallyHandiCappedDivId').hide();
	}

	$("#personPhidDivId").change(function() {
	 
	 displayPHCDiv($('input#personPhid').val());
	 
	});  
	
	function checkBPLStatus(bplStatus)
	{
		if(bplStatus == "Y")
		{
			$('#bplNumberDivId').show();
		}
		else
		{
			$('#bplNumberDivId').hide();
		}
	}
	function applicationProcessValidation(){
		var applStatus = $("input[name='onlineApplicationDetails.status']:checked").val();
		if (isNonEmpty(applStatus)) {
				if ($("input[name='onlineApplicationDetails.status']:checked").val() == "R") {
					$('#rejectAppicationsDescriptionCont').show();
					$('textarea#rejectApplDescription').addClass('required');
					
					$('#confirmStudentDivId').hide();
					$('#confirmStudentDivId1').hide();
					$('.studAdmissionNumber').removeClass('required');
					$('.rollNumber').removeClass('required');
				} 
				else if ($("input[name='onlineApplicationDetails.status']:checked").val() == "CS") {
					
					$('#confirmStudentDivId').show();
					$('#confirmStudentDivId1').show();
					$('.studAdmissionNumber').addClass('required');
					$('.rollNumber').addClass('required');
					$('textarea#rejectApplDescription').removeClass('required');
					$('#rejectAppicationsDescriptionCont').hide();
					
					
				}
				else {
					$('textarea#rejectApplDescription').removeClass('required');
					$('#rejectAppicationsDescriptionCont').hide();
					
					$('#confirmStudentDivId').hide();
					$('#confirmStudentDivId1').hide();
					$('.studAdmissionNumber').removeClass('required');
					$('.rollNumber').removeClass('required');
				}
			}
	}
	
	var phidVal = '<s:property value="onlineApplicationDetails.phId"/>';
	if ("true" == phidVal)
		$('#physicallyHandiCappedDivId').show();
	var PhysicalStatus = $('span.phStatus').attr('id');
	if (isNonEmpty(PhysicalStatus)) {
		if (PhysicalStatus == 'true') {
			$('#phIdYes').parent('span').addClass('checked');
			$("#phIdYes").attr("checked", true);
		} 
		else{
			$('#phIdNo').parent('span').addClass('checked');
			$("#phIdNo").attr("checked", true);
		}
	}
	else
		{
		$('#phIdNo').parent('span').addClass('checked');
		$("#phIdNo").attr("checked", true);
		}
	
	function showTransportInfo(){
		var transportMode = '<s:property value="onlineApplicationDetails.transportMode"/>';
		//alert(transportMode);
		if(isNonEmpty(transportMode) && 'T' == transportMode){
			$('#inputboxhideText').show();
		}else{
			$('#inputboxhideText').hide();
		}
	}
	
	var bplStatus = '<s:property value="onlineApplicationDetails.bplStatus"/>';
	
	if(isNonEmpty(bplStatus))
	{
		checkBPLStatus(bplStatus);
	}
	
	function confirmToRemoveImage(event,target) {
		 thishref = $(event).attr('id');
			if ($(event).next('.question').length <= 0) {
				$(event).after('<div class="question" style="z-index:1000">Are You Sure?<br/> <span class="yes">Yes</span><span class="cancel">Cancel</span></div>');
			}
			$(event).next('.question').animate( {
				opacity : 1
			}, 300);
			$('.yes').unbind('click');
			$('.yes').bind('click', function() {
				var prdDiv = $(this).parents('.question');
				prdDiv.html('Processing...');
				$.ajax( {
					url : thishref,
					cache : false,
					success : function(html) {
						$('#'+target).html(html);
							$('#imageNotAvailableDiv').attr("src",jQuery.url.getChatURL("/images/common/photo_notAvailable.jpg"));
							$('.red').remove();
					}
				
				});prdDiv.remove();
				
			});
		        $('.cancel').unbind('click');
		        $('.cancel').bind('click', function() {
		        $(this).parents('.question').fadeOut(300, function() {
		            $(this).remove();
		        });
		        return false;
		    });
		  } 
	
	var applicationId = '<s:property value="onlineApplicationDetails.classId.id"/>';
	if(!isNonEmpty(applicationId))
		applicationId = 0;
	$(".studAdmissionNumber").autoCheck("${pageContext.request.contextPath}/admin/ajaxCheckAdmissionNumberAvailableOrNotForAdmissions.do?selectedId="+applicationId,
			{
				minChars : 1,
				min : "no",
			});
	$("#rollNumber").unbind('#rollNumber').autoCheck("${pageContext.request.contextPath}/admin/ajaxCheckStudentRollNumberAvailableOrNotForAdmissions.do?selectedId="+applicationId,
			{
				minChars : 1,
				min : "no"
			});
</script>