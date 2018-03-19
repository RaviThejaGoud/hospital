<%@ include file="/common/taglibs.jsp"%>
<jsp:include page="/common/messages.jsp"></jsp:include>
<div id="rejectApplication">
	<div class="form-body">
		<span id="classSectionId"
			class="<s:property value='onlineApplicationDetailsView.classId'/>">
	</span>
	<s:form action="ajaxUpdateApplicationStatus" theme="simple"
		name="studentApplicationDetails" cssClass="form-horizontal"
		id="updateApplicationDetailsForm" namespace="/admin"
		enctype="multipart/form-data" method="post">
		<s:hidden name="empId"></s:hidden>
		<s:hidden name="status" value="%{status}"></s:hidden>
		<s:hidden name="classId" value="%{classId}" id="classId"></s:hidden>
		<s:hidden name="anyTitle" value="%{anyTitle}"></s:hidden>
		<s:hidden name="selectedId" value="%{selectedId}"></s:hidden>
		<s:hidden name="customerImage" id="customerImage"></s:hidden>
		<s:hidden name="onlineApplicationDetailsView.entranceExamPassMarks"></s:hidden>
		<h4 class="pageTitle bold form-section">STUDENT INFORMATION</h4>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4"> <span
						class="required">*</span>First Name :
					</label>
					<div class="col-md-6">
						<sj:textfield name="onlineApplicationDetailsView.firstName"
							id="firstName"
							cssClass="required form-control input-medium as-input"
							maxlength="60"></sj:textfield>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-md-4"> Last Name : </label>
					<div class="col-md-6">
						<sj:textfield name="onlineApplicationDetailsView.lastName"
							id="lastName" cssClass="form-control input-medium as-input"
							maxlength="60"></sj:textfield>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-md-4"> Date of Birth : </label>
					<div class="col-md-5">
						<div data-date-format="mm/dd/yyyy"
							class="input-group input-medium date date-picker"
							data-date-end-date="+0d">
							<input id="date0"
								name="onlineApplicationDetailsView.dateOfBirth" readonly="true"
								type="text" class="form-control"
								value='<s:property value="onlineApplicationDetailsView.dateOfBirthStr"/>'>
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
					<label class="control-label col-md-3"></label>
					<div id="browseImage">
						<img
							src='<c:url value="${onlineApplicationDetailsView.originalAttachmentFilePath}"/>'
							alt='' border="0" height="102px;" id="imageNotAvailableDiv" />
						<s:if test='%{onlineApplicationDetailsView.imageId != null}'>
							<s:if test='%{onlineApplicationDetailsView.imageId>0}'>
								<s:url id="removeImage" action="ajaxRemoveStudentAndStaffImage"
									includeParams="all" escapeAmp="false" namespace="/student">
									<s:param name="onlineApplicationDetailsView.imageId"
										value="onlineApplicationDetailsView.imageId" />
									<s:param name="onlineApplicationDetailsView.applicationId"
										value="onlineApplicationDetailsView.applicationId" />
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
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4"> <span
						class="required">*</span>Gender :
					</label>
					<div class="col-md-7">
						<div class="make-switch has-switch" data-id="M" data-value="F"
							style="width: 120px" data-off="warning" data-on="success"
							data-off-label="Female" data-on-label="Male">
							<s:if test='%{onlineApplicationDetailsView.gender =="M"}'>
								<input type="radio" class="toggle" checked="checked"
									id="gender">
							</s:if>
							<s:else>
								<input type="radio" class="toggle" id="gender">
							</s:else>
							<input type="hidden" name="onlineApplicationDetailsView.gender"
								value='<s:property value="onlineApplicationDetailsView.gender"/>'>
						</div>
					</div>
				</div>
			</div>
			<s:if test="%{customer.hostelModuleStatus}">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> Residence Type : </label>
						<div class="col-md-8">
							<div class="make-switch has-switch" data-id="D" data-value="H"
								style="width: 200px" data-off="warning" data-on="success"
								data-off-label="Hostler" data-on-label="Day Scholar">
								<s:if test='%{onlineApplicationDetailsView.hostelMode =="D"}'>
									<input type="radio" class="toggle" checked="checked"
										id="hostel">
									<input type="hidden"
										name="onlineApplicationDetailsView.hostelMode"
										value='<s:property value="onlineApplicationDetailsView.hostelMode"/>'>
								</s:if>
								<s:elseif
									test='%{onlineApplicationDetailsView.hostelMode =="H"}'>
									<input type="radio" class="toggle" id="hostel">
									<input type="hidden"
										name="onlineApplicationDetailsView.hostelMode"
										value='<s:property value="onlineApplicationDetailsView.hostelMode"/>'>
								</s:elseif>
								<s:else>
									<input type="radio" class="toggle" checked="checked"
										id="hostel">
									<input type="hidden"
										name="onlineApplicationDetailsView.hostelMode" value='D'>
								</s:else>
							</div>
						</div>
					</div>
				</div>
			</s:if>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4"> Student E-Mail : </label>
					<div class="col-md-8">
						<sj:textfield name="onlineApplicationDetailsView.studentEmail"
							id="studentEmailId"
							cssClass="form-control input-medium as-input email"
							maxlength="50"></sj:textfield>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4"> Student Mobile
						Number : </label>
					<div class="col-md-8">
						<sj:textfield name="onlineApplicationDetailsView.studentMobile" onblur="return validateStuMobNumber(this.value)"
							id="studentMobile" cssClass="form-control input-medium as-input numeric"
							maxlength="10"></sj:textfield>
					</div>
				</div>
			</div>
		</div>
		<div class="row">

			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4"> Student Unique
						Number : </label>
					<div class="col-md-7">
						<sj:textfield
							name="onlineApplicationDetailsView.studentUniqueNumber"
							id="studentUniqueNumber"
							cssClass="form-control input-medium as-input" maxlength="20"></sj:textfield>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4"> Aadhaar Card No : </label>
					<div class="col-md-8">
						<sj:textfield
							name="onlineApplicationDetailsView.aadharCardNumber"
							onchange="javascript:validateAadhaarCardNumber(this.value)"
							id="aadharCardNumber"
							cssClass="numeric form-control input-medium as-input"
							maxlength="12"></sj:textfield>
					</div>
				</div>
			</div>
		</div>
		<div class="row">

			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4"> Transfer
						Certificate No : </label>
					<div class="col-md-6">
						<sj:textfield
							name="onlineApplicationDetailsView.transferCertificateNo"
							id="transferCertificateNo"
							cssClass="form-control input-medium as-inputl" maxlength="60"></sj:textfield>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4"> Select Category : </label>
					<div class="col-md-6">
						<s:select id="categoryId" list="schoolCategoriesList"
							listKey="id" listValue="categoryName"
							cssClass="form-control input-medium"
							name="onlineApplicationDetailsView.categoryId" />
					</div>
				</div>
			</div>
		</div>
		<div class="row">

			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4"> Registration Fee :
					</label>
					<div class="col-md-8">
						<sj:textfield name="onlineApplicationDetailsView.registrationFee"
							disabled="true" id="registrationFee"
							cssClass="form-control input-medium as-input numeric"
							maxlength="7"></sj:textfield>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4"> Prospective Fee : </label>
					<div class="col-md-8">
						<sj:textfield name="onlineApplicationDetailsView.prospectiveFee"
							disabled="true" id="prospectiveFee"
							cssClass="form-control input-medium as-input numeric"
							maxlength="7"></sj:textfield>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<s:if test='%{customer.committedFeeStatus == "Y"}'>
				<div class="row" id="committedFeeDiv">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4"> Committed Fee : </label>
							<div class="col-md-8">
								<sj:textfield name="onlineApplicationDetailsView.committedFee"
									id="committedFee"
									cssClass="form-control input-medium as-input numeric"
									maxlength="7"></sj:textfield>
							</div>
						</div>
					</div>
				</div>
			</s:if>
		</div>
	<%-- <div class="row">
			<s:if test='%{customer.transportModuleStatus}'>
				<div class="col-md-6">
					<div class="form-group">
						<label class="col-md-4 control-label"> Transport Mode : </label>
						<div class="col-md-8">
							<div class="clearfix">
								<div data-toggle="buttons" class="btn-group">
									<s:if
										test='%{(onlineApplicationDetailsView.transportMode=="O") || (onlineApplicationDetailsView.transportMode =="P" ) || (onlineApplicationDetailsView.transportMode =="T")}'>
										<label class="btn blue radioMultiOption"
											id='<s:property value="onlineApplicationDetailsView.transportMode"/>'>
											<input type="radio" value="O" class="toggle"
											id="transportMode"
											name="onlineApplicationDetailsView.transportMode">
											Own
										</label>
										<label class="btn blue radioMultiOption"
											id='<s:property value="onlineApplicationDetailsView.transportMode"/>'>
											<input type="radio" class="toggle" value="P"
											id="transportMode"
											name="onlineApplicationDetailsView.transportMode">
											Private
										</label>
										<label class="btn blue radioMultiOption"
											id='<s:property value="onlineApplicationDetailsView.transportMode"/>'>
											<input type="radio" class="toggle" value="T"
											id="transportMode"
											name="onlineApplicationDetailsView.transportMode">
											School Transport
										</label>
									</s:if>
									<s:else>
										<label class="btn blue radioMultiOption active"
											id='<s:property value="onlineApplicationDetailsView.transportMode"/>'>
											<input type="radio" value="O" class="toggle"
											checked="checked" id="transportMode"
											name="onlineApplicationDetailsView.transportMode">
											Own
										</label>
										<label class="btn blue radioMultiOption"
											id='<s:property value="onlineApplicationDetailsView.transportMode"/>'>
											<input type="radio" class="toggle" value="P"
											id="transportMode"
											name="onlineApplicationDetailsView.transportMode">
											Private
										</label>
										<label class="btn blue radioMultiOption"
											id='<s:property value="onlineApplicationDetailsView.transportMode"/>'>
											<input type="radio" class="toggle" value="T"
											id="transportMode"
											name="onlineApplicationDetailsView.transportMode">
											School Transport
										</label>
									</s:else>
									<label class="btn blue radioMultiOption"
											id='<s:property value="onlineApplicationDetailsView.transportMode"/>'>
											<input type="radio" value="O"
											class="toggle radioMultiOption" id="transportMode"
											onclick="showTransportMode();"
											name="onlineApplicationDetailsView.transportMode"
											checked="checked"> Own
										</label> <label class="btn blue radioMultiOption"
											id='<s:property value="onlineApplicationDetailsView.transportMode"/>'>
											<input type="radio" class="toggle radioMultiOption"
											value="P" id="transportMode" onclick="showTransportMode();"
											name="onlineApplicationDetailsView.transportMode"
											checked="checked"> Private
										</label> <label class="btn blue radioMultiOption"
											id='<s:property value="onlineApplicationDetailsView.transportMode"/>'>
											<input type="radio" class="toggle radioMultiOption"
											value="T" id="transportMode" onclick="showTransportMode();"
											name="onlineApplicationDetailsView.transportMode"
											checked="checked"> School Transport
										</label> 
								</div>
							</div>
						</div>
					</div>
				</div>
			</s:if>--%>

			<s:if test='%{customer.transportModuleStatus}'>
				<div id="inputboxhideText" style="display: none;">
					<div class="row">
						<div class="col-md-12">
							<span class='studBoardingPoint'
								id='<s:property value="onlineApplicationDetailsView.boardingPointId"/>'></span>
							<span class='vehicleAcademicDetailsId'
								id='<s:property value="onlineApplicationDetailsView.vehicleAcademicDetailsId"/>'></span>
							<jsp:include
								page="/WEB-INF/pages/common/ajaxAddStudentTransportInformation.jsp" />
						</div>
					</div>
				</div>
			</s:if>
		<h4 class="pageTitle bold form-section">STUDENT EDUCATIONAL
			INFORMATION</h4>
		<div class="row">
			<div class="col-md-6">
				<s:if test="%{classList != null && !classList.isEmpty()}">
					<s:if test='%{onlineApplicationDetailsView.status == "P" }'>
						<div class="form-group">
							<label class="control-label col-md-4"> <span
								class="required">*</span>Select Joining Class :
							</label>
							<div class="col-md-6">
								<s:select list="classList" listKey="id" listValue="className"
									cssClass="required form-control input-medium"
									name="onlineApplicationDetailsView.classId" headerKey="0"
									headerValue="- Select -"
									onchange="javascript:checkCommittedFee(this.value);">
								</s:select>

							</div>
						</div>
					</s:if>
					<s:else>
						<div class="form-group">
							<label class="control-label col-md-4"> <span
								class="required">*</span>Select Joining Class :
							</label>
							<div class="col-md-6">
								<s:select list="classList" listKey="id" listValue="className"
									cssClass="required form-control input-medium"
									name="onlineApplicationDetailsView.classId" headerKey="0"
									headerValue="- Select -" disabled="true"
									onchange="javascript:checkCommittedFee(this.value);">
								</s:select>
								<s:hidden name="onlineApplicationDetailsView.classId"></s:hidden>
							</div>
						</div>
					</s:else>
				</s:if>
				<s:else>
					<div class="alert alert-info">No classes defined for this
						admissions.</div>
				</s:else>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4"> Date of Joining : </label>
					<div class="col-md-8">
						<div data-date-format="mm/dd/yyyy"
							class="input-group input-medium date date-picker">
							<input type="text" readonly="readonly" class="form-control"
								id="studentJoiningDate"
								name="onlineApplicationDetailsView.dateOfJoining"
								value='<s:property value="onlineApplicationDetailsView.dateOfJoiningStr"/>'
								onchange="verifyDate();"> <span class="input-group-btn">
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
					<label class="control-label col-md-4"> Last Class Studied
						: </label>
					<div class="col-md-6">
						<sj:textfield
							name="onlineApplicationDetailsView.lastClassAttended"
							id="lastClassAttended"
							cssClass="form-control input-medium as-inputl" maxlength="60"></sj:textfield>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4"> Last School Name :
					</label>
					<div class="col-md-6">
						<sj:textfield name="onlineApplicationDetailsView.lastSchool"
							id="lastSchool" cssClass="form-control input-medium as-inputl"
							maxlength="60"></sj:textfield>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4"> SSLC Number : </label>
					<div class="col-md-6">
						<sj:textfield name="onlineApplicationDetailsView.sslcNumber"
							id="sslcNumber" cssClass="form-control input-medium as-input"
							maxlength="12"></sj:textfield>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4"> TMR Number : </label>
					<div class="col-md-6">
						<sj:textfield name="onlineApplicationDetailsView.tmrNumber"
							id="tmrNumber" cssClass="form-control input-medium as-input"
							maxlength="12"></sj:textfield>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4"> Is RTE Student (Free Education)  : </label>
					<div class="col-md-6">
						<div class="make-switch has-switch" data-id="N" data-value="Y"
							style="width: 200px" data-off="warning" data-on="success"
							data-off-label="Yes" data-on-label="No">
							<s:if test='%{onlineApplicationDetailsView.rteStatus =="N"}'>
								<input type="radio" class="toggle" checked="checked"
									id="rteStatus">
								<input type="hidden" name="onlineApplicationDetailsView.rteStatus"
									value='<s:property value="onlineApplicationDetailsView.rteStatus"/>'>
							</s:if>
							<s:elseif test='%{onlineApplicationDetailsView.rteStatus =="Y"}'>
								<input type="radio" class="toggle" id="rteStatus">
								<input type="hidden" name="onlineApplicationDetailsView.rteStatus"
									value='<s:property value="onlineApplicationDetailsView.rteStatus"/>'>
							</s:elseif>
							<s:else>
								<input type="radio" class="toggle" checked="checked"
									id="rteStatus">
								<input type="hidden" name="onlineApplicationDetailsView.rteStatus" value='N'>
							</s:else>
						</div>
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
						class="required">*</span> Father / Guardian's Name :
					</label>
					<div class="col-md-6">
						<sj:textfield name="onlineApplicationDetailsView.fatherName"
							id="fatherName"
							cssClass="required form-control input-medium as-inputl"
							maxlength="50"></sj:textfield>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4"> Father / Guardian's
						Occupation : </label>
					<div class="col-md-6">
						<sj:textfield name="onlineApplicationDetailsView.occupation"
							id="occupation" cssClass="form-control input-medium as-inputl"
							maxlength="40"></sj:textfield>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4"> Mother Name : </label>
					<div class="col-md-6">
						<sj:textfield name="onlineApplicationDetailsView.motherName"
							id="motherName" cssClass="form-control input-medium as-inputl"
							maxlength="20"></sj:textfield>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4"> Mother
						Qualification : </label>
					<div class="col-md-6">
						<sj:textfield
							name="onlineApplicationDetailsView.motherQualification"
							id="motherQualification"
							cssClass="form-control input-medium as-inputl" maxlength="60"></sj:textfield>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4"> Father / Guardian's
						Designation : </label>
					<div class="col-md-6">
						<sj:textfield name="onlineApplicationDetailsView.designation"
							id="designation" cssClass="form-control input-medium as-inputl"
							maxlength="60"></sj:textfield>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4"> Father / Guardian's
						Qualification : </label>
					<div class="col-md-6">
						<sj:textfield
							name="onlineApplicationDetailsView.fatherQualification"
							id="fatherQualification"
							cssClass="form-control input-medium as-inputl" maxlength="20"></sj:textfield>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4"> Parent E-mail Id :
					</label>
					<div class="col-md-6">
						<sj:textfield name="onlineApplicationDetailsView.parentEmail"
							id="emailAddress" cssClass="form-control input-medium as-inputl"
							maxlength="40"></sj:textfield>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4"> Annual Income : </label>
					<div class="col-md-6">
						<sj:textfield name="onlineApplicationDetailsView.annualIncome"
							id="guardianQualifiction"
							cssClass="numeric form-control input-medium as-inputl"></sj:textfield>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4"> Place Of Birth : </label>
					<div class="col-md-8">
						<sj:textfield name="onlineApplicationDetailsView.placeOfBirth"
							id="placeOfBirth" cssClass="form-control input-medium as-input"
							maxlength="60"></sj:textfield>
					</div>
				</div>
			</div>
			<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4">Is Below Poverty Line (BPL) : </label>
						<div class="col-md-6">
							<div class="make-switch has-switch" data-id="N" data-value="Y"
							style="width: 200px" data-off="warning" data-on="success"
							data-off-label="Yes" data-on-label="No">
							<s:if test='%{onlineApplicationDetailsView.bplStatus =="N"}'>
								<input type="radio" class="toggle" checked="checked"
									id="bplStatus">
								<input type="hidden" name="onlineApplicationDetailsView.bplStatus"
									value='<s:property value="onlineApplicationDetailsView.bplStatus"/>'>
							</s:if>
							<s:elseif test='%{onlineApplicationDetailsView.bplStatus =="Y"}'>
								<input type="radio" class="toggle" id="bplStatus">
								<input type="hidden" name="onlineApplicationDetailsView.bplStatus"
									value='<s:property value="onlineApplicationDetailsView.bplStatus"/>'>
							</s:elseif>
							<s:else>
								<input type="radio" class="toggle" checked="checked"
									id="bplStatus">
								<input type="hidden" name="onlineApplicationDetailsView.bplStatus" value='N'>
							</s:else>
						</div>
						</div>
					</div>
				</div>
		</div>
		<h4 class="pageTitle bold form-section">CONTACT DETAILS</h4>
		<h5 class="pageTitle bold">RESIDENTIAL ADDRESS</h5>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4"> <span
						class="required">*</span>Parent Mobile Number :
					</label>
					<div class="col-md-6">
						<sj:textfield name="onlineApplicationDetailsView.mobileNumber"
							id="mobileNumber"
							cssClass="numeric required form-control input-medium as-inputl"
							maxlength="10" onblur="return validateMobNumber(this.value)"></sj:textfield>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4"> Phone Number : </label>
					<div class="col-md-6">
						<sj:textfield name="onlineApplicationDetailsView.phoneNumber"
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
					<label class="control-label col-md-4"> Street Name : </label>
					<div class="col-md-6">
						<sj:textfield name="onlineApplicationDetailsView.caddrStreetName"
							id="streetName" cssClass="form-control input-medium as-inputl"
							maxlength="200"></sj:textfield>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4"> City : </label>
					<div class="col-md-6">
						<sj:textfield name="onlineApplicationDetailsView.caddrCity"
							id="city" cssClass="form-control input-medium as-inputl"
							maxlength="20"></sj:textfield>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4"> State : </label>
					<div class="col-md-6">
						<s:select id="state" list="statesList"
							cssClass="form-control input-medium" listKey="stateCode"
							listValue="stateName" headerKey="" headerValue="- Select -"
							name="onlineApplicationDetailsView.caddrState" />
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4"> Pincode : </label>
					<div class="col-md-6">
						<sj:textfield name="onlineApplicationDetailsView.caddrPostalCode"
							id="pincode"
							cssClass="numeric form-control input-medium as-inputl"
							maxlength="6"></sj:textfield>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-6"> Same As Residential
						Address : </label>
					<div class="col-md-6">
						<div class="controls">
							<label class="checkbox"> <span><input
									type="checkbox" name="billingtoo" value=""
									onclick="FillBilling(this.form)" class="allClasses"> </span>
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
						<sj:textfield name="onlineApplicationDetailsView.tAddrStreetName"
							id="streetName1" cssClass="form-control input-medium as-inputl"></sj:textfield>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4"> City : </label>
					<div class="col-md-6">
						<sj:textfield name="onlineApplicationDetailsView.tAddrCity"
							id="city1" cssClass="form-control input-medium as-inputl"
							maxlength="20"></sj:textfield>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4"> State : </label>
					<div class="col-md-6">
						<s:select id="state1" list="statesList" listKey="stateCode"
							listValue="stateName" headerKey="" headerValue="- Select -"
							cssClass="form-control input-medium"
							name="onlineApplicationDetailsView.tAddrState" />
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4"> Pincode : </label>
					<div class="col-md-6">
						<sj:textfield name="onlineApplicationDetailsView.tAddrPostalCode"
							id="pincode1"
							cssClass="numeric form-control input-medium as-inputl"
							maxlength="6"></sj:textfield>
					</div>
				</div>
			</div>
		</div>
		<h4 class="pageTitle bold form-section">HEALTH INFORMATION</h4>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4"> Blood Group : </label>
					<div class="col-md-6">
						<s:select id="bGroup"
							list="#{'A+':'A+ve','A-':'A-ve','A1+':'A1+ve','A1-':'A1-ve','A1B+':'A1B+ve','A1B-':'A1B-ve','A2B+':'A2B+ve','A2B-':'A2B-ve','A2+':'A2+ve','A2-':'A2-ve','B+':'B+ve','B-':'B-ve','AB+':'AB+ve','AB-':'AB-ve','O+':'O+ve','O-':'O-ve'}"
							label="Blood Group" headerKey="" headerValue="- Select -"
							cssClass="form-control input-medium"
							name="onlineApplicationDetailsView.bloodGroup" />
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4"> Teeth : </label>
					<div class="col-md-8">
						<sj:textfield name="onlineApplicationDetailsView.teeth"
							id="teeth" cssClass="form-control input-medium as-input"
							maxlength="40"></sj:textfield>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4"> Vision (Left) : </label>
					<div class="col-md-8">
						<sj:textfield name="onlineApplicationDetailsView.visionL"
							id="visionLeft" cssClass="form-control input-medium as-input "
							maxlength="10"></sj:textfield>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4"> Vision (Right) : </label>
					<div class="col-md-8">
						<sj:textfield name="onlineApplicationDetailsView.visionR"
							id="visionRight" cssClass="form-control input-medium as-input "
							maxlength="10"></sj:textfield>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4"> Weight 1 : </label>
					<div class="col-md-8">
						<sj:textfield name="onlineApplicationDetailsView.weight"
							id="weight"
							cssClass="form-control input-medium as-input numericDot"
							maxlength="10"></sj:textfield>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4"> Height 1 (C.M): </label>
					<div class="col-md-8">
						<sj:textfield name="onlineApplicationDetailsView.height"
							id="height"
							cssClass="numericDot  form-control input-medium as-input"
							maxlength="10"></sj:textfield>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4"> Oral Hygiene : </label>
					<div class="col-md-8">
						<sj:textfield name="onlineApplicationDetailsView.oralHygiene"
							id="oralHYgiene" cssClass="form-control input-medium as-input"
							maxlength="40"></sj:textfield>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4"> Family Doctor : </label>
					<div class="col-md-8">
						<sj:textfield name="onlineApplicationDetailsView.familyDoctor"
							id="familyDoctor" cssClass="form-control input-medium as-input"
							maxlength="50"></sj:textfield>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4"> Preferred Hospital
						: </label>
					<div class="col-md-8">
						<sj:textfield
							name="onlineApplicationDetailsView.prefferedHospital"
							id="prefferedHospital"
							cssClass="form-control input-medium as-input" maxlength="50"></sj:textfield>
					</div>
				</div>
			</div>
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
							id="<s:property value='onlineApplicationDetailsView.phId'/>"></span>
					</div>
				</div>
			</div>
		</div>
		<div id="physicallyHandiCappedDivId" style="display: none;">
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4">
							Disability Details :</label>
						<div class="col-md-7">
							<sj:textarea rows="3" cols="20" name="onlineApplicationDetailsView.physicallyHandicappedDesc" maxCharsData="1000" tabindex="3" cssClass="form-control word_count"></sj:textarea>
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
							<s:file name="fileUpload" id="uploadDocs" cssClass="btn default"></s:file>
						<%-- <div class="upload upload-new" data-provides="upload">
								<div class="input-append">
									<span class="btn default"> <s:file name="upload"
											id="photoURL1s" cssClass="upload"></s:file>
									</span>
								</div>
							</div> --%>
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
									<td><a rel="nofollow" href='<c:url value='/student/downloadPhysicallyHandicappedDocuments.do'/>?tempId1=<s:property value="onlineApplicationDetailsView.applicationId" />&fileName=<s:property value="fileName" />'><s:property
												value="fileName" /> </a></td>
									<s:if test='%{#session.previousYear == "N"}'>
										<td><s:url id="removeFile"
												action="ajaxRemoveStudentDisabilityDocuments" namespace="/admin"
												escapeAmp="false" includeParams="all">
												<s:param name="tempId1" value="onlineApplicationDetailsView.applicationId"></s:param>
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
							href='<c:url value='/student/downloadPhysicallyHandicappedDocuments.do'/>?tempId1=<s:property value="onlineApplicationDetailsView.applicationId" />'
							class="btn blue" style="padding: 0 2px;">Download All</a>&nbsp;&nbsp;
						<s:if test='%{#session.previousYear == "N"}'> 	
							|&nbsp;&nbsp;
						<s:url id="removeDocuments"
								action="ajaxRemoveStudentDisabilityDocuments" namespace="/admin" escapeAmp="false" includeParams="all">
								<s:param name="tempId1" value="%{onlineApplicationDetailsView.applicationId}"></s:param>
								<s:param name="status" value="%{onlineApplicationDetailsView.status}"></s:param>
								<s:param name="eventId">deleteAll</s:param>
							</s:url>
							 <s:if test='%{onlineApplicationDetailsView.status == "P"}'>
								<sj:a id="removeDocumentsLink" href="%{removeDocuments}"
									targets="admissionsPendingContentAppli" cssClass="btn btn-xs red">Delete All</sj:a>
							</s:if>
							 <s:elseif test='%{onlineApplicationDetailsView.status == "S"}'>
							 	<sj:a id="removeDocumentsLink" href="%{removeDocuments}"
								targets="academicsApprovedContent" cssClass="btn btn-xs red">Delete All</sj:a>
							 </s:elseif>
							 <s:elseif test='%{onlineApplicationDetailsView.status == "R"}'>
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
		<h4 class="pageTitle bold form-section">CASTE AND COMMUNITY
			INFORMATION</h4>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4"> Nationality : </label>
					<div class="col-md-6">
						<sj:textfield name="onlineApplicationDetailsView.nationality"
							id="nationality" cssClass="form-control input-medium as-inputl"
							maxlength="50"></sj:textfield>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4"> Select Religion : </label>
					<div class="col-md-6">
						<s:select list="tempList1" listKey="id" listValue="skillTypeName"
							cssClass="form-control input-medium" id="religion"
							name="onlineApplicationDetailsView.religionId" headerKey="0"
							headerValue="- Select -">
						</s:select>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6" id="resultsDiv1">
				<jsp:include
					page="/WEB-INF/pages/admin/admission/castSettings/ajaxCastListByState.jsp" />
			</div>
			<div id="resultsDiv2" class="col-md-6">
				<jsp:include
					page="/WEB-INF/pages/admin/admission/castSettings/ajaxGetOnlineSubCastListByCastId.jsp" />
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4"> Community Number :
					</label>
					<div class="col-md-7">
						<sj:textfield name="onlineApplicationDetailsView.communityNumber"
							id="communityNumber"
							cssClass="form-control input-medium as-inpu" maxlength="50"></sj:textfield>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4"> Ration Card Number
						: </label>
					<div class="col-md-7">
						<sj:textfield
							name="onlineApplicationDetailsView.rationCardNumber"
							id="rationCardNumber"
							cssClass="form-control input-medium as-inpu" maxlength="50"></sj:textfield>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4"> Select Mother
						Tongue : </label>
					<div class="col-md-7">
						<s:select list="tempList2" listKey="id" listValue="name"
							cssClass="form-control input-medium" label="Select MotherToung"
							name="onlineApplicationDetailsView.motherToungId" headerKey=""
							headerValue="- Select -">
						</s:select>
					</div>
				</div>
			</div>
		</div>
		<h4 class="pageTitle bold form-section">STUDENT IDENTIFICATION</h4>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4"> Personal
						Identification1 : </label>
					<div class="col-md-7">
						<sj:textfield name="onlineApplicationDetailsView.identification1"
							id="identification1Id"
							cssClass="form-control input-medium as-inpu" maxlength="50"></sj:textfield>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4"> Personal
						Identification2 : </label>
					<div class="col-md-7">
						<sj:textfield name="onlineApplicationDetailsView.identification2"
							id="identification2Id"
							cssClass="form-control input-medium as-inpu" maxlength="50"></sj:textfield>
					</div>
				</div>
			</div>
		</div>
		<h4 class="pageTitle bold form-section">COLLECTED DOCUMENTS</h4>
		<div class="row">
			<!-- <div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> Upload Documents : </label>
						<div class="col-md-7">
							<input id="photoURL" class="btn default" type="file" value=""
								multiple="multiple" name="fileUpload" id="uploadScannedDocs">
						</div>
					</div>
				</div> -->
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4"> Summary : </label>
					<div class="col-md-7">
						<sj:textarea
							name="onlineApplicationDetailsView.collectedDocuments"
							id="collectedDocuments" cssClass="word_count form-control"
							placeholder="This textarea has a limit of 200 chars." rows="4"
							cols="20" maxlength="200"></sj:textarea>
						<span class="help-block"> Maxlength is 200 chars. </span>
					</div>
				</div>
			</div>
		</div>
		<h4 class="pageTitle bold form-section">SELF AWARENESS :</h4>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4"> Goals : </label>
					<div class="col-md-7">
						<sj:textarea name="onlineApplicationDetailsView.goals"
							id="maxlength_textarea1" cssClass="form-control"
							placeholder="This textarea has a limit of 200 chars." rows="4"
							cols="20" maxlength="200"></sj:textarea>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4"> Strengths : </label>
					<div class="col-md-7">
						<sj:textarea name="onlineApplicationDetailsView.strengths"
							id="maxlength_textarea2" cssClass="form-control"
							placeholder="This textarea has a limit of 200 chars." rows="4"
							cols="20" maxlength="200"></sj:textarea>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4"> Interests and
						Hobbies : </label>
					<div class="col-md-7">
						<sj:textarea
							name="onlineApplicationDetailsView.interestsAndHobbies"
							id="maxlength_textarea3" cssClass="form-control"
							placeholder="This textarea has a limit of 200 chars." rows="4"
							cols="20" maxlength="200"></sj:textarea>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4"> Responsibilities
						Discharged : </label>
					<div class="col-md-7">
						<sj:textarea name="onlineApplicationDetailsView.responsibilities"
							id="maxlength_textarea4" cssClass="form-control"
							placeholder="This textarea has a limit of 200 chars." rows="4"
							cols="20" maxlength="200"></sj:textarea>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4"> Exceptional
						Achievements : </label>
					<div class="col-md-7">
						<sj:textarea name="onlineApplicationDetailsView.achievements"
							id="maxlength_textarea5" cssClass="form-control"
							placeholder="This textarea has a limit of 2000 chars." rows="4"
							cols="20" maxlength="200"></sj:textarea>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<s:if test='%{onlineApplicationDetailsView.testConducted}'>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> Entrance Marks : </label>
						<div class="col-md-6">
							<sj:textfield name="onlineApplicationDetailsView.entranceMarks"
								id="entranceMarks"
								onchange="javascript:validateEntranceMarks(%{onlineApplicationDetailsView.entranceExamTotalMarks},this);"
								cssClass="numeric form-control input-medium as-inputl"
								maxlength="5"></sj:textfield>
						</div>
					</div>
				</div>
			</s:if>
			<s:if test='%{onlineApplicationDetailsView.status == "P"}'>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> Process
							Application </label>
						<div class="col-md-8">
							<div class="radio-list">
								<label class="radio-inline"> <s:if
										test='%{onlineApplicationDetailsView.status == "S"}'>
										<input type="radio" value="S" id="applicationStatus"
											name="onlineApplicationDetailsView.status"
											onclick="applicationProcessValidation();" checked="checked">
									</s:if> <s:else>
										<input type="radio" value="S" id="applicationStatus"
											name="onlineApplicationDetailsView.status"
											onclick="applicationProcessValidation();">
									</s:else> Short List
								</label> <label class="radio-inline"> <s:if
										test='%{onlineApplicationDetailsView.status == "R"}'>
										<input type="radio" value="R" id="applicationStatus"
											name="onlineApplicationDetailsView.status"
											onclick="applicationProcessValidation();" checked="checked">
									</s:if> <s:else>
										<input type="radio" value="R" id="applicationStatus"
											name="onlineApplicationDetailsView.status"
											onclick="applicationProcessValidation();">
									</s:else> Reject
								</label>
								<label class="radio-inline"> 
										<input type="radio" value="CS" id="applicationStatus"
											name="onlineApplicationDetailsView.status"
											onclick="applicationProcessValidation();">
									 		Confirm Student
								</label>
							</div>
						</div>
					</div>
				</div>
			</s:if>
			<s:elseif test='%{onlineApplicationDetailsView.status == "S"}'>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> Process
							Application </label>
						<div class="col-md-8">
							<div class="radio-list">
								<label class="radio-inline"> <s:if
										test='%{onlineApplicationDetailsView.status == "S"}'>
										<input type="radio" value="S" id="applicationStatus"
											name="onlineApplicationDetailsView.status"
											onclick="applicationProcessValidation();" checked="checked">
									</s:if> <s:else>
										<input type="radio" value="S" id="applicationStatus"
											name="onlineApplicationDetailsView.status"
											onclick="applicationProcessValidation();">
									</s:else> Short List
								</label> <label class="radio-inline"> <s:if
										test='%{onlineApplicationDetailsView.status == "R"}'>
										<input type="radio" value="R" id="applicationStatus"
											name="onlineApplicationDetailsView.status"
											onclick="applicationProcessValidation();" checked="checked">
									</s:if> <s:else>
										<input type="radio" value="R" id="applicationStatus"
											name="onlineApplicationDetailsView.status"
											onclick="applicationProcessValidation();">
									</s:else> Reject
								</label>
								<label class="radio-inline"> 
										<input type="radio" value="CS" id="applicationStatus"
											name="onlineApplicationDetailsView.status"
											onclick="applicationProcessValidation();">
									 		Confirm Student
								</label>
							</div>
						</div>
					</div>
				</div>
			</s:elseif>
			<s:elseif test='%{onlineApplicationDetailsView.status == "R"}'>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> Process
							Application </label>
						<div class="col-md-8">
							<div class="radio-list">
								<label class="radio-inline"> <s:if
										test='%{onlineApplicationDetailsView.status == "S"}'>
										<input type="radio" value="R" id="applicationStatus"
											name="onlineApplicationDetailsView.status">
									</s:if> <s:else>
										<input type="radio" value="S" id="applicationStatus"
											name="onlineApplicationDetailsView.status">
									</s:else> Short List
								</label> </label> <label class="radio-inline"> <s:if
										test='%{onlineApplicationDetailsView.status == "R"}'>
										<input type="radio" value="R" id="applicationStatus"
											name="onlineApplicationDetailsView.status"
											onclick="applicationProcessValidation();" checked="checked">
									</s:if> <s:else>
										<input type="radio" value="R" id="applicationStatus"
											name="onlineApplicationDetailsView.status"
											onclick="applicationProcessValidation();">
									</s:else> Reject
								</label>
								<label class="radio-inline"> 
										<input type="radio" value="CS" id="applicationStatus"
											name="onlineApplicationDetailsView.status"
											onclick="applicationProcessValidation();">
									 		Confirm Student
								</label>
							</div>
						</div>
					</div>
				</div>
			</s:elseif>
		</div>
		<div class="row">
			<s:if
				test='%{onlineApplicationDetailsView.status == "R" && onlineApplicationDetailsView.rejectApplicationDesc != null && !onlineApplicationDetailsView.rejectApplicationDesc.isEmpty()}'>
				<div class="col-md-6">
					<label class="control-label col-md-4"> Reason for reject
						application : </label> <br />
					<s:property
						value="onlineApplicationDetailsView.rejectApplicationDesc" />
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
							name="onlineApplicationDetailsView.rejectApplicationDesc"
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
						<s:if test='%{onlineApplicationDetailsView.admissionNumberAutoGenerationStatus == "Y"}'>
						<div class="col-md-6">
							<sj:textfield name="admissionNumber" id="studAdmissionNumber" cssClass="form-control input-medium as-input studAdmissionNumber"
								maxlength="20" readonly="true" ></sj:textfield>
						</div>
					</s:if><s:else>
						<div class="col-md-6">
							<sj:textfield name="admissionNumber" id="studAdmissionNumber" cssClass="form-control input-medium as-input studAdmissionNumber"
								maxlength="20" ></sj:textfield>
						</div>
					</s:else>
					
					<%-- <div class="col-md-6">
						<sj:textfield name="admissionNumber" id="studAdmissionNumber"  cssClass="form-control input-medium as-input studAdmissionNumber"
							maxlength="20" readonly="true"></sj:textfield>
					</div> --%>
				</div>
			</div>
		</div>
		<div class="row">
	       <s:if test='%{customerByCustId.getAlphaNumericRollNumber()=="Y"}'>
			<div class="col-md-6" style="display: none;" id="confirmStudentDivId1">
				<div class="form-group">
					<label class="control-label col-md-4"> Roll Number : </label>
					<div class="col-md-6">
						<sj:textfield name="onlineApplicationDetailsView.rollNumber" id="rollNumber"
							cssClass="form-control input-medium as-input"
							maxlength="15"></sj:textfield>
					</div>
				</div>
			</div>
		 </s:if> 
		</div>
		
		<s:if
			test='%{(#session.academicYear == null || #session.academicYear.isEmpty()) || (#session.previousYear=="N")}'>
			<div id="closeToRespectiveAdmissionDetails"
				class="form-actions fluid">
				<div class="col-md-offset-2 col-md-6">
					<sj:submit cssClass="btn blue" value="Submit"
						indicator="indicator" targets="admissionsPendingContentAppli"
						validate="true" onBeforeTopics="formValidation"
						formIds="updateApplicationDetailsForm" />
					<s:url id="doCancelAdmission" action="ajaxPendingApplications"
						includeParams="all" escapeAmp="false" namespace="/admin">
						<s:param name="academicYearId"
							value="%{onlineApplicationDetailsView.academicYearId}"></s:param>
					</s:url>
					<sj:a href="%{doCancelAdmission}" cssClass="btn default"
						targets="admissionsPendingContentAppli" button="false">Cancel</sj:a>
				</div>
			</div>
			<div id="closeToRespectiveShortList" class="form-actions fluid">
				<div class="col-md-offset-2 col-md-6">
					<sj:submit cssClass="btn blue" value="Submit"
						indicator="indicator" targets="academicsApprovedContent"
						validate="true" onBeforeTopics="formValidation"
						formIds="updateApplicationDetailsForm" />
					<s:url id="doCancelPending" action="ajaxApprovedApplications"
						includeParams="all" escapeAmp="false" namespace="/admin">
						<s:param name="academicYearId"
							value="%{onlineApplicationDetailsView.academicYearId}"></s:param>
					</s:url>
					<sj:a href="%{doCancelPending}" cssClass="btn default"
						targets="academicsApprovedContent" button="false">Cancel</sj:a>
				</div>
			</div>
			<div id="closeToRespectiveWaitingList" class="form-actions fluid">
				<div class="col-md-offset-2 col-md-9">
					<sj:submit cssClass="btn blue" value="Submit"
						indicator="indicator" targets="academicsRejectedContentDiv"
						validate="true" onBeforeTopics="formValidation"
						formIds="updateApplicationDetailsForm" />
					<!--<s:url id="doCancelRejected"
			action="ajaxRejectedApplicationsDetails" includeParams="all"
			escapeAmp="false">
			<s:param name="academicYearId"
				value="%{onlineApplicationDetailsView.academicYearId}"></s:param>
		</s:url>
		<sj:a href="%{doCancelRejected}" cssClass="btn default"
			indicator="indicator" targets="academicsRejectedContent"
			button="false">Cancel</sj:a>
	-->
					<s:url id="urlRejectedApplications"
						action="ajaxRejectedApplications" namespace="/admin" />
					<sj:a href="%{urlRejectedApplications}" targets="mainContentDiv"
						cssClass="btn default">Cancel</sj:a>
				</div>
			</div>
		</s:if>
	</s:form>
	</div>
</div>
<div id="studentsImgDiv"></div>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/plugins/bootstrap-switch/static/js/bootstrap-switch.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	 $('button.close').click();
	$('label.radioMultiOption').each(function(){
		 if($(this).attr('id')==$(this).children('input').val()){
			  $(this).addClass('active');
			  $(this).children('input').attr("checked","checked");
			  if($(this).children('input').val()=="T")
			  $('#inputboxhideText').show();
			  else
			  $('#inputboxhideText').hide();
		 }
	});
	var phidVal = '<s:property value="onlineApplicationDetailsView.phId"/>';
	if ("true" == phidVal)
		$('#physicallyHandiCappedDivId').show();
	var PhysicalStatus = $('span.phStatus').attr('id');
	if (isNonEmpty(PhysicalStatus)) {
		if (PhysicalStatus == 'true') {
			$('#phIdYes').parent('span').addClass('checked');
			$("#phIdYes").attr("checked", true);
		} else {
			$('#phIdNo').parent('span').addClass('checked');
			$("#phIdNo").attr("checked", true);
		}
	}
	
	UIExtendedModals.init();
	FormComponents.init();
	FormAdvanced.init();
	$.destroyTopic('formValidation');
	 $("input:checkbox, input:radio:not('.toggle')").uniform(); 
	var liSize = $('ul.sub-menu li.active');
	var downTag = liSize.find('a');
	showTransportInfo();
	if (downTag.attr('id') == 'admissionDetails') {
		$('#closeToRespectiveAdmissionDetails').show();
		$('#closeToRespectiveShortList').hide();
		$('#closeToRespectiveWaitingList').hide();
	} else if (downTag.attr('id') == 'urlPendingApplications') {
		$('#closeToRespectiveAdmissionDetails').hide();
		$('#closeToRespectiveShortList').show();
		$('#closeToRespectiveWaitingList').hide();
	} else if (downTag.attr('id') == 'urlRejectedApplications') {
		$('#closeToRespectiveAdmissionDetails').hide();
		$('#closeToRespectiveShortList').hide();
		$('#closeToRespectiveWaitingList').show();
	}
	$('.numeric').numeric();
	
	$.subscribe('formValidation', function(event, data) {
		
		var studAdmissionNumberStr = $('input.studAdmissionNumber').parents('div').next('div').find('p.word-taken').html();
 		if (studAdmissionNumberStr == 'Already taken!!!') {
 			event.originalEvent.options.submit = false;
 		}
 		var rollNumber = $("[name='onlineApplicationDetailsView.rollNumber']").val();
		if ($('p.word-taken').html() == 'Already taken!!!') {
			event.originalEvent.options.submit = false;
		}
		if($("#updateApplicationDetailsForm").valid()){
		var entrancepassmarks = '<s:property value="onlineApplicationDetailsView.entranceExamPassMarks"/>';
		var transportMode = $('input#transportMode').val();
		var studentMarks = $('input#entranceMarks').val();
		var status = '<s:property value="onlineApplicationDetailsView.status"/>';
		if(isNonEmpty(entrancepassmarks) && isNonEmpty(studentMarks)){
			if(status !='R'){
				 if ($("input[name='onlineApplicationDetailsView.status']:checked").val() == "S") {
					if((Number(studentMarks) < Number(entrancepassmarks))){
						 var answer = confirm("This applicant is fail in entrance examination,But you are selected this application to shortlist,Do you want to proceed ?");
						if(answer){
							event.originalEvent.options.submit = true;
						}else{
							event.originalEvent.options.submit = false;
						}
					}
				}
				 if ($("input[name='onlineApplicationDetailsView.status']:checked").val() == '' || $("input[name='onlineApplicationDetailsView.status']:checked").val() == null || $("input[name='onlineApplicationDetailsView.status']:checked").val() == undefined) {
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
				if ($("input[name='onlineApplicationDetailsView.status']:checked").val() == "S") {
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
		if(isNonEmpty($("input.fileName").val())) {
			var filename = $("input.fileName").val().toLowerCase();
			if(filename.lastIndexOf(".jpg") == -1 && filename.lastIndexOf(".png") == -1  &&  filename.lastIndexOf(".jpeg") == -1){
				alert("File not acceped. Please upload your file in jpg or jpeg or  png");
				event.originalEvent.options.submit = false;
			}
		}
		if(isNonEmpty(transportMode) && 'T' == transportMode){
			$('select#routeId').addClass('required');
			$('select#boardingId').addClass('required');
			$('select#vehicleId').addClass('required');
		}
		else{
			$('select#routeId').removeClass('required');
			$('select#boardingId').removeClass('required');
			$('select#vehicleId').removeClass('required');
		}
		}
		else{
			event.originalEvent.options.submit = false;
		}
	});
	var classSectionId = $('#classSectionId').attr('class');
	if(classSectionId !=0){
		checkCommittedFee(classSectionId);	
	}
});

function applicationProcessValidation(){
	var applStatus = $("input[name='onlineApplicationDetailsView.status']:checked").val();
	if (isNonEmpty(applStatus)) {
			if ($("input[name='onlineApplicationDetailsView.status']:checked").val() == "R") {
				$('#rejectAppicationsDescriptionCont').show();
				$('textarea#rejectApplDescription').addClass('required');
				
				$('#confirmStudentDivId').hide();
				$('#confirmStudentDivId1').hide();
				$('.studAdmissionNumber').removeClass('required');
				$('.rollNumber').removeClass('required');
				
			} 
			else if ($("input[name='onlineApplicationDetailsView.status']:checked").val() == "CS") {
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

$("label.radioMultiOption").click(function(){
	var transportMode = $(this).find("input").val();
	//alert(transportMode);
	if(isNonEmpty(transportMode) && 'T' == transportMode){
		$('#inputboxhideText').show();
	}else{
		$('#inputboxhideText').hide();
	}
});

function showTransportInfo(){
	var transportMode = '<s:property value="onlineApplicationDetailsView.transportMode"/>'
	//alert(transportMode);
	if(isNonEmpty(transportMode) && 'T' == transportMode){
		$('#inputboxhideText').show();
	}else{
		$('#inputboxhideText').hide();
	}
}

 function viewPopupStudensCapture(){
        $.ajax( {
	        url : jQuery.url.getChatURL("/admin/ajaxCapturePhoto.do"),
			cache : false,
			success : function(html) {
			$("#studentsImgDiv").html(html);
			}
		});
 }
function validateEntranceMarks(entranceExamTotalMarks, evnt) {
	var totalMarks = Number(entranceExamTotalMarks);
	var obtMarks = Number($(evnt).val());
	if (isNonEmpty(totalMarks) && isNonEmpty(obtMarks) && obtMarks > totalMarks) {
		alert("Entrance marks should be less than or equal to " + totalMarks
				+ ".");
		$(evnt).val('');
	}
}

function getCastDetailsByState(selectBox) {
	var stateName = selectBox.value;
	var url = jQuery.url.getChatURL("/admin/ajaxGetCastDetailsByState.do");
	if (stateName.length == 0) {
		alert("!Oops select state.");
	} else {
		$("#resultsDiv1")
				.html(
						'<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		var pars = "stateName=" + stateName;
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

function FillBilling(offlineApplicationForm){
	if (offlineApplicationForm.billingtoo.checked == true) {
		offlineApplicationForm.streetName1.value =offlineApplicationForm.streetName.value;
		offlineApplicationForm.city1.value = offlineApplicationForm.city.value;
		offlineApplicationForm.state1.value = offlineApplicationForm.state.value;
		offlineApplicationForm.pincode1.value = offlineApplicationForm.pincode.value;
	} else {
		offlineApplicationForm.streetName1.value = "";
		offlineApplicationForm.city1.value = "";
		offlineApplicationForm.state1.value = "";
		offlineApplicationForm.pincode1.value = "";
	}

}
/*this is used in chrome and IE*/
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
	var phoneNumber = ($(this).val());
	if (Math.floor(phoneNumber) != phoneNumber) {
		alert("Please enter numbers.");
		$("#phoneNumber").val('');
		return false;
	}
});
$("#pincode").change(function() {
	var pincode = ($(this).val());
	if (Math.floor(pincode) != pincode) {
		alert("Please enter numbers.");
		$("#pincode").val('');
		return false;
	}
});
function checkCommittedFee(studyClassId){
	var categoryId = $("#categoryId").val();
	var academicYearId = $("#academicYearId").val();
	if (categoryId != 0 && academicYearId !=0) {
		var feeURL = jQuery.url.getChatURL("/common/ajaxCheckClassWiseCommittedFee.do?studyClassId="+ studyClassId + "&categoryId=" + categoryId+"&academicYearId="+academicYearId+"&tempString=AD");
		$.ajax( {
			url : feeURL,
			cache : false,
			dataType : 'json',
			success : function(response) {
				if(isNonEmpty(response)){
				var isCommittedFee=response.isCommittedFee;
					if(isNonEmpty(isCommittedFee)){
						if(isCommittedFee="true")
							$('#committedFeeDiv').show();
						else{
							$('#committedFeeDiv').hide();
							$("#committedFee").val(0.0);
						}
					}else{
						$('#committedFeeDiv').hide();
						$("#committedFee").val(0.0);
					}
				}
			}
		});
	}else{
		alert("Please select category name and academic year.");
		$("#studyClassId").val('');
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

$(function() {
	if ($('div.emsFileRemove')) {
		$('div.emsFileRemove').unbind('click');
		var status= '<s:property value="onlineApplicationDetailsView.status"/>';
		$("div.emsFileRemove").click(function() {
			confirmDeleteDocument(this,status);
		});
	}
});
function confirmDeleteDocument(event,status) {
	thishref = $(event).attr('id');
	var filename = thishref.split("=");
	if ($(event).next('.question').length <= 0) {
		$(event).after('<div class="question">Are You Sure?<br/> <span class="yes">Yes</span><span class="cancel">Cancel</span></div>');
	}
	$(event).next('.question').animate({
		opacity : 1
	}, 300);
	$('.yes').unbind('click');
	$('.yes').bind('click', function() {
		var prdDiv = $(this).parents('.question');
		prdDiv.html('Processing...');
		$.ajax({
			url : thishref,
			cache : false,
			success : function(html) {
				// deleteFile(filename);
				prdDiv.parent().parent().remove();
				if(status=="P")
				$('#admissionsPendingContentAppli').html(html);
				else if(status == "S")
					$('#academicsApprovedContent').html(html);
				else
					$('#academicsRejectedContentDiv').html(html);
					
				//prdDiv.remove();
			}
		});
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
