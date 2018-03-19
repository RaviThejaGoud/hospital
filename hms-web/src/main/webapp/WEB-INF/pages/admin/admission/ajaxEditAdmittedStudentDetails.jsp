<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/subscription/textcounter.js">
</script>
<!--<div id="steps" style="width: 760px; padding-left: 0px">
	-->
<div class="form-body">
	<%@ include file="/common/messages.jsp"%>
	<s:form action="ajaxUpdateAdmittedStudentDetails" theme="simple"
		name="editAdmittedStudentForm" id="updatedStudentDetailsForm"
		method="post" cssClass="form-horizontal" namespace="/admin"
		enctype="multipart/form-data">
		<s:hidden name="newUser.id" value="%{newUser.id}"></s:hidden>
		<s:hidden name="classId" value="%{classId}"></s:hidden>
		<s:hidden name="anyTitle" value="%{anyTitle}"></s:hidden>
		<s:hidden name="selectedId" value="%{selectedId}"></s:hidden>
		<s:hidden name="customerImage" id="customerImage"></s:hidden>
		<s:hidden name="alertSendType" id="transport"></s:hidden>
		<s:hidden name="student.academicYear.id" id="academicYearId"
			value="%{academicYearId}"></s:hidden>
		<h4 class="pageTitle bold form-section">STUDENT INFORMATION</h4>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4"> <span
						class="required">*</span>First Name :
					</label>
					<div class="col-md-5">
						<sj:textfield name="newUser.person.firstName" id="driverfName"
							cssClass="required form-control input-medium" maxlength="60"></sj:textfield>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-md-4"> Last Name : </label>
					<div class="col-md-5">
						<sj:textfield name="newUser.person.lastName" id="driverlName"
							cssClass="form-control input-medium" maxlength="60"></sj:textfield>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-md-4"> Date of Birth : </label>
					<div class="col-md-5">
						<div data-date-format="mm/dd/yyyy"
							class="input-group input-medium date date-picker"
							data-date-end-date="+0d">
							<input id="date0" name="newUser.person.dateOfBirth" readonly=""
								type="text" class="form-control"
								value='<s:property value="newUser.person.dateOfBirthStr"/>'>
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
					<label class="control-label col-md-3"> </label>
					<div id="browseImage">
						<img src='<c:url value="${student.studentImage}"/>' alt=''
							border="0" height="102px;" id="imageNotAvailableDiv" />
						<s:if
							test="%{student.studentImage != null && student.studentImage != empty}">
							<s:if test='%{newUser.profileImage.id>0}'>
								<s:url id="removeImage" action="ajaxRemoveStudentAndStaffImage"
									includeParams="all" escapeAmp="false" namespace="/student">
									<s:param name="newUser.id" value="newUser.id" />
									<s:param name="newUser.profileImage.id"
										value="newUser.profileImage.id" />
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
						<a data-toggle="modal" href="#browseImageDiv"
							class="capturePhoto btn default video-button"
							id="<s:property value='tempId' />"
							onclick="javascript:viewPopupStudensCapture(<s:property value='tempId' />);"><i
							class="fa fa-camera"></i>Capture </a>
					</div>
					<div class="col-md-1">
						<strong>&nbsp;OR</strong>
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
							<s:if test='%{newUser.person.gender =="M"}'>
								<input type="radio" class="toggle" checked="checked" id="gender">
							</s:if>
							<s:else>
								<input type="radio" class="toggle" id="gender">
							</s:else>
							<input type="hidden" name="newUser.person.gender"
								value='<s:property value="newUser.person.gender"/>'>
						</div>
					</div>
				</div>
			</div>
			<s:if test="%{customer.hostelModuleStatus}">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> Residence Type : </label>
						<div class="col-md-7">
							<div class="make-switch has-switch" data-id="D" data-value="H"
								style="width: 200px" data-off="warning" data-on="success"
								data-off-label="Hostler" data-on-label="Day Scholar">
								<s:if test='%{student.hostelMode =="D"}'>
									<input type="radio" class="toggle" checked="checked"
										id="hostel">
									<input type="hidden" name="student.hostelMode"
										value='<s:property value="student.hostelMode"/>'>
								</s:if>
								<s:elseif test='%{student.hostelMode =="H"}'>
									<input type="radio" class="toggle" id="hostel">
									<input type="hidden" name="student.hostelMode"
										value='<s:property value="student.hostelMode"/>'>
								</s:elseif>
								<s:else>
									<input type="radio" class="toggle" checked="checked"
										id="hostel">
									<input type="hidden" name="student.hostelMode" value='D'>
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
					<div class="col-md-5">
						<sj:textfield name="newUser.person.studentEmail"
							id="studentEmailId"
							cssClass="form-control input-medium as-input email"
							maxlength="90"></sj:textfield>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4"> Student Mobile
						Number : </label>
					<div class="col-md-5">
						<sj:textfield name="newUser.person.studentMobile"
							id="studentMobile"
							 onblur="return validateStuMobNumber(this.value)"
							cssClass="form-control input-medium as-input numeric"
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
						<sj:textfield name="newUser.person.studentUniqueNumber"
							id="studentUniqueNumber"
							cssClass="form-control input-medium as-input" maxlength="20"></sj:textfield>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4"> Aadhaar Card No : </label>
					<div class="col-md-8">
						<sj:textfield name="newUser.person.aadharNumber"
							id="aadharCardNumber"
							cssClass="form-control input-medium as-input" maxlength="20"></sj:textfield>
					</div>
				</div>
			</div>
		</div>
		<div class="row">

			<div class="col-md-6">
				<div class="form-group ">
					<label class="control-label col-md-4"> Transfer Certificate
						No : </label>
					<div class="col-md-5">
						<sj:textfield name="newUser.person.transferCertificateNo"
							id="transferCertificateNo"
							cssClass=" numeric form-control input-medium" maxlength="60"></sj:textfield>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4"> Select Category : </label>
					<div class="col-md-5">
						<s:if
							test='%{student.feePaidStatus =="N" || student.feePaidStatus =="C"}'>
							<s:select list="schoolCategoriesList" id="categoryId"
								name="student.categoryId" listKey="id" listValue="categoryName"
								headerKey="" headerValue="- Select Category -" theme="simple"
								cssClass="required form-control input-medium">
							</s:select>
						</s:if>
						<s:else>
							<s:select list="schoolCategoriesList" id="categoryId"
								name="student.categoryId" listKey="id" listValue="categoryName"
								headerKey="" headerValue="- Select Category -" theme="css_xhtml"
								cssClass="required form-control input-medium" disabled="true">
							</s:select>
						</s:else>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<s:if test='%{customer.committedFeeStatus == "Y"}'>
				<div class="col-md-6" id="committedFeeDiv">
					<div class="form-group">
						<label class="control-label col-md-4"> Committed Fee : </label>
						<div class="col-md-5">
							<s:if
								test='%{student.feePaidStatus =="N" || student.feePaidStatus =="C"}'>
								<sj:textfield name="student.committedFee" id="committedFee"
									cssClass="numericDot form-control input-medium as-input"
									maxlength="7"></sj:textfield>
							</s:if>
							<s:else>
								<sj:textfield name="student.committedFee" id="committedFee"
									cssClass="numericDot form-control input-medium as-input"
									maxlength="7" disabled="true"></sj:textfield>
							</s:else>
						</div>
					</div>
				</div>
			</s:if>
		</div>
		<%--<s:if test='%{customer.transportModuleStatus==true}'>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="col-md-4 control-label"> Transport Mode : </label>
						<div class="col-md-8">
							<div class="clearfix">
								<div data-toggle="buttons" class="btn-group" id="transportDiv">
									<s:if
										test='%{(student.transportMode =="O") || (student.transportMode =="P" ) || (student.transportMode =="T")}'>
										<label class="btn blue radioMultiOption"
											id='<s:property value="student.transportMode"/>'> <input
											type="radio" value="O" class="toggle" id="transportMode"
											name="student.transportMode"> Own
										</label>
										<label class="btn blue radioMultiOption"
											id='<s:property value="student.transportMode"/>'> <input
											type="radio" class="toggle" value="P" id="transportMode"
											name="student.transportMode"> Private
										</label>
										<label class="btn blue radioMultiOption"
											id='<s:property value="student.transportMode"/>'> <input
											type="radio" class="toggle" value="T" id="transportMode"
											name="student.transportMode"> School Transport
										</label>
									</s:if>
									<s:else>
										<label class="btn blue radioMultiOption active"
											id='<s:property value="student.transportMode"/>'> <input
											type="radio" value="O" class="toggle" checked="checked"
											id="transportMode" name="student.transportMode"> Own
										</label>
										<label class="btn blue radioMultiOption"
											id='<s:property value="student.transportMode"/>'> <input
											type="radio" class="toggle" value="P" id="transportMode"
											name="student.transportMode"> Private
										</label>
										<label class="btn blue radioMultiOption"
											id='<s:property value="student.transportMode"/>'> <input
											type="radio" class="toggle" value="T" id="transportMode"
											name="student.transportMode"> School Transport
										</label>
									</s:else>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</s:if>  --%>
		<%-- <s:if test='%{customer.transportModuleStatus==true}'>
			<div class="row">
				<div class="col-md-10">
					<div class="form-group">
						<label class="col-md-3 control-label">
							Transport Mode :
						</label>
						<div class="radio-list col-md-8" id="transportId">
							<label class="radio-inline">
								<input type="radio" id="transportMode" value="O"
									onchange="showTransportInfo(this.value);"
									name="student.transportMode">
								Own
							</label>
							<label class="radio-inline">
								<input type="radio" value="P"
									onchange="showTransportInfo(this.value);"
									name="student.transportMode" id="transportMode">
								Private
							</label>
							<label class="radio-inline">
								<input type="radio" id="transportMode" value="T"
									onchange="showTransportInfo(this.value);"
									name="student.transportMode">
								School Transport
							</label>
						</div>
					</div>
				</div>
			</div>
			<!--<div class="col-md-6">
				<div class="form-group ">
					<label class="control-label col-md-5">
						Transport Mode :
					</label>
					<div class="col-md-5">
						<s:radio list="#{'O':'Own','P':'Private','T':'School Transport'}"
							name="student.transportMode" onchange="showTransportInfo();"
							 id="transportMode" />
					</div>
				</div>
			</div>
		--></s:if> --%>
		<%-- <div class="row">
			<div class="col-md-12">
				<div id="inputboxhideText" style="display: none;">
					<span class='studBoardingPoint'
						id='<s:property value="student.studentBoardingPointId"/>'></span>
					<span class='vehicleAcademicDetailsId'
						id='<s:property value="student.vehicleAcademicDetailsId"/>'></span>
					<%@ include
						file="/WEB-INF/pages/common/ajaxAddStudentTransportInformation.jsp"%>
				</div>
			</div>
		</div>--%>
		<h4 class="pageTitle bold form-section">STUDENT EDUCATIONAL
			INFORMATION</h4>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4"> <span
						class="required">*</span>Select Joining Class :
					</label>
					<div class="col-md-5">
						<s:select list="classList" listKey="id" listValue="className"
							cssClass="required form-control input-medium"
							name="student.classNameClassId.id" headerKey="0"
							headerValue="- Select -" disabled="true"
							onchange="javascript:checkCommittedFee(this.value);">
						</s:select>
						<s:hidden name="student.classNameClassId.id" id="classId"></s:hidden>
						<s:hidden name="student.classSection.id" id="classSectionId"></s:hidden>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4"> Date of Joining : </label>
					<div class="col-md-8">
						<div data-date-format="mm/dd/yyyy"
							class="input-group input-medium date date-picker">
							<input type="text" readonly="readonly" class="form-control"
								id="studentJoiningDate" name="newUser.person.dateOfJoining"
								value='<s:property value="newUser.person.dateOfJoiningStr"/>'
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
					<label class="control-label col-md-4"> Last Class Studied :
					</label>
					<div class="col-md-5">
						<sj:textfield name="newUser.person.classJoined"
							id="lastClassAttended"
							cssClass="form-control input-medium as-inputl" maxlength="60"></sj:textfield>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4"> Last School Name : </label>
					<div class="col-md-5">
						<sj:textfield name="newUser.person.lastSchool" id="lastSchool"
							cssClass="form-control input-medium as-inputl" maxlength="80"></sj:textfield>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4"> SSLC Number : </label>
					<div class="col-md-6">
						<sj:textfield name="newUser.person.sslcNumber" id="sslcNumber"
							cssClass="form-control input-medium as-input" maxlength="12"></sj:textfield>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4"> TMR Number : </label>
					<div class="col-md-6">
						<sj:textfield name="newUser.person.tmrNumber" id="tmrNumber"
							cssClass="form-control input-medium as-input" maxlength="12"></sj:textfield>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
							<label class="control-label col-md-4">  Is RTE Student (Free Education)  : </label>
							<div class="col-md-7">
							<div class="make-switch has-switch" data-id="N" data-value="Y"
								style="width: 200px" data-off="warning" data-on="success"
								data-off-label="Yes" data-on-label="No">
								<s:if test='%{student.rteStatus =="N"}'>
									<input type="radio" class="toggle" checked="checked"
										id="rteStatus">
									<input type="hidden" name="student.rteStatus"
										value='<s:property value="student.rteStatus"/>'>
								</s:if>
								<s:elseif test='%{student.rteStatus =="Y"}'>
									<input type="radio" class="toggle" id="rteStatus">
									<input type="hidden" name="student.rteStatus"
										value='<s:property value="student.rteStatus"/>'>
								</s:elseif>
								<s:else>
									<input type="radio" class="toggle" checked="checked"
										id="rteStatus">
									<input type="hidden" name="student.rteStatus" value='N'>
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
						class="required">*</span>Father / Guardian's Name :
					</label>
					<div class="col-md-5">
						<sj:textfield name="newUser.person.fatherName" id="fatherName"
							cssClass="required form-control input-medium" maxlength="70"></sj:textfield>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group ">
					<label class="control-label col-md-4"> Occupation : </label>
					<div class="col-md-5">
						<sj:textfield name="newUser.person.occupation" id="occupation"
							cssClass="form-control input-medium" maxlength="50"></sj:textfield>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4"> Mother Name : </label>
					<div class="col-md-5">
						<sj:textfield name="newUser.person.motherName" id="motherName"
							cssClass="alphabet form-control input-medium" maxlength="20"></sj:textfield>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group ">
					<label class="control-label col-md-4"> Mother Qualification
						: </label>
					<div class="col-md-5">
						<sj:textfield name="newUser.person.motherQualification"
							id="motherQualification" cssClass="form-control input-medium"
							maxlength="60"></sj:textfield>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4"> Father/Guardian
						Qualification : </label>
					<div class="col-md-5">
						<sj:textfield name="newUser.person.fatherQualification"
							id="fatherQualification" cssClass="form-control input-medium"
							maxlength="60"></sj:textfield>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group ">
					<label class="control-label col-md-4"> Designation : </label>
					<div class="col-md-5">
						<sj:textfield name="newUser.person.designation" id="designation"
							cssClass="form-control input-medium" maxlength="60"></sj:textfield>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4"> Parent E-Mail : </label>
					<div class="col-md-5">
						<sj:textfield name="newUser.person.parentEmail" id="emailAddress"
							cssClass="email form-control input-medium" maxlength="40"></sj:textfield>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group ">
					<label class="control-label col-md-4"> Annual Income : </label>
					<div class="col-md-5">
						<sj:textfield name="newUser.person.annualIncome" id="annualIncome"
							cssClass="numeric form-control input-medium"></sj:textfield>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4"> Place Of Birth : </label>
					<div class="col-md-5">
						<sj:textfield name="newUser.person.placeOfBirth" id="placeOfBirth"
							cssClass="form-control input-medium as-input" maxlength="50"></sj:textfield>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4">Is Below Poverty Line (BPL) : </label>
					<div class="col-md-7">
						<div class="make-switch has-switch" data-id="N" data-value="Y"
							style="width: 200px" data-off="warning" data-on="success"
							data-off-label="Yes" data-on-label="No">
							<s:if test='%{student.bplStatus =="N"}'>
								<input type="radio" class="toggle" checked="checked"
									id="bplStatus">
								<input type="hidden" name="student.bplStatus"
									value='<s:property value="student.bplStatus"/>'>
							</s:if>
							<s:elseif test='%{student.bplStatus =="Y"}'>
								<input type="radio" class="toggle" id="bplStatus">
								<input type="hidden" name="student.bplStatus"
									value='<s:property value="student.bplStatus"/>'>
							</s:elseif>
							<s:else>
								<input type="radio" class="toggle" checked="checked"
									id="bplStatus">
								<input type="hidden" name="student.bplStatus" value='N'>
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
				<div class="form-group ">
					<label class="control-label col-md-4"> <span
						class="required">*</span>Parent Mobile Number :
					</label>
					<div class="col-md-5">
						<sj:textfield name="newUser.person.mobileNumber" id="mobileNumber"
							cssClass="required  numeric form-control input-medium"
							maxlength="10"
							onblur="return validateMobNumber(this.value)"></sj:textfield>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4"> Phone Number : </label>
					<div class="col-md-6">
						<sj:textfield name="newUser.person.phoneNumber" id="phoneNumber"
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
					<div class="col-md-5">
						<sj:textfield name="newUser.primaryAddress.streetName"
							id="streetName" cssClass="form-control input-medium"
							maxlength="200"></sj:textfield>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group ">
					<label class="control-label col-md-4"> City : </label>
					<div class="col-md-5">
						<sj:textfield name="newUser.primaryAddress.city" id="city"
							cssClass="form-control input-medium" maxlength="20"></sj:textfield>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group ">
					<label class="control-label col-md-4"> State : </label>
					<div class="col-md-5">
						<s:select id="state" list="statesList" listKey="stateCode"
							cssClass="form-control input-medium" listValue="stateName"
							headerKey="" headerValue="- Select -"
							name="newUser.primaryAddress.state" />
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4"> Pincode : </label>
					<div class="col-md-5">
						<sj:textfield name="newUser.primaryAddress.postalCode"
							id="pincode" cssClass="numeric form-control input-medium"
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
		<h4 class="pageTitle bold">Correspondence address</h4>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4"> Street Name : </label>
					<div class="col-md-5">
						<sj:textfield name="newUser.tempararyAddress.streetName"
							id="streetName1" cssClass="form-control input-medium"
							maxlength="200"></sj:textfield>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group ">
					<label class="control-label col-md-4"> City : </label>
					<div class="col-md-5">
						<sj:textfield name="newUser.tempararyAddress.city" id="city1"
							cssClass="form-control input-medium" maxlength="20"></sj:textfield>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group ">
					<label class="control-label col-md-4"> State : </label>
					<div class="col-md-5">
						<s:select id="state1" list="statesList" listKey="stateCode"
							cssClass="form-control input-medium" listValue="stateName"
							headerKey="" headerValue="- Select State -"
							name="newUser.tempararyAddress.state" />
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4"> Pincode : </label>
					<div class="col-md-5">
						<sj:textfield name="newUser.tempararyAddress.postalCode"
							id="pincode1" cssClass="form-control input-medium" maxlength="6"></sj:textfield>
					</div>
				</div>
			</div>
		</div>
		<h4 class="pageTitle bold form-section">HEALTH INFORMATION</h4>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4"> Blood Group : </label>
					<div class="col-md-5">
						<s:select id="bGroup" listKey="id" theme="simple"
							list="#{'A+':'A+ve','A-':'A-ve','A1+':'A1+ve','A1-':'A1-ve','A1B+':'A1B+ve','A1B-':'A1B-ve','A2B+':'A2B+ve','A2B-':'A2B-ve','A2+':'A2+ve','A2-':'A2-ve','B+':'B+ve','B-':'B-ve','AB+':'AB+ve','AB-':'AB-ve','O+':'O+ve','O-':'O-ve'}"
							cssClass="form-control input-medium"
							name="newUser.person.bloodGroup" headerKey=""
							headerValue="- Select Blood Group-" />
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4"> Teeth : </label>
					<div class="col-md-8">
						<sj:textfield name="newUser.person.teeth" id="teeth"
							cssClass="form-control input-medium as-input" maxlength="40"></sj:textfield>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4"> Vision (Left) : </label>
					<div class="col-md-8">
						<sj:textfield name="newUser.person.visionL" id="visionLeft"
							cssClass="form-control input-medium as-input " maxlength="10"></sj:textfield>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4"> Vision (Right) : </label>
					<div class="col-md-8">
						<sj:textfield name="newUser.person.visionR" id="visionRight"
							cssClass="form-control input-medium as-input " maxlength="10"></sj:textfield>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4"> Weight 1 : </label>
					<div class="col-md-8">
						<sj:textfield name="newUser.person.weight" id="weight"
							cssClass="form-control input-medium as-input numericDot"
							maxlength="10"></sj:textfield>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4"> Height 1 (C.M): </label>
					<div class="col-md-8">
						<sj:textfield name="newUser.person.height" id="height"
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
						<sj:textfield name="newUser.person.oralHygiene" id="oralHYgiene"
							cssClass="form-control input-medium as-input" maxlength="40"></sj:textfield>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4"> Family Doctor : </label>
					<div class="col-md-8">
						<sj:textfield name="newUser.person.familyDoctor" id="familyDoctor"
							cssClass="form-control input-medium as-input" maxlength="50"></sj:textfield>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4"> Preferred Hospital :
					</label>
					<div class="col-md-8">
						<sj:textfield name="newUser.person.prefferedHospital"
							id="prefferedHospital"
							cssClass="form-control input-medium as-input" maxlength="50"></sj:textfield>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4"> <span
						class="required">*</span>Student Disability :
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
								id="<s:property value='newUser.person.phId'/>"></span>
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
								<sj:textarea rows="3" cols="20" name="newUser.person.physicallyHandicappedDesc" maxCharsData="1000" tabindex="3" cssClass="form-control word_count"></sj:textarea>
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
								<s:file name="fileUpload" id="uploadDocs" multiple="multiple" cssClass="btn default"></s:file>
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
										<td><a rel="nofollow" href='<c:url value='/student/downloadPhysicallyHandicappedDocuments.do'/>?tempId=<s:property value="student.id" />&fileName=<s:property value="fileName" />'><s:property
													value="fileName" /> </a></td>
										<s:if test='%{#session.previousYear == "N"}'>
											<td><s:url id="removeFile"
													action="ajaxRemoveStudentDisabilityDocuments" namespace="/admin"
													escapeAmp="false" includeParams="all">
													<s:param name="tempId1" value="student.id"></s:param>
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
								href='<c:url value='/student/downloadPhysicallyHandicappedDocuments.do'/>?tempId=<s:property value="student.id" />'
								class="btn blue" style="padding: 0 2px;">Download All</a>&nbsp;&nbsp;
							<s:if test='%{#session.previousYear == "N"}'> 	
								|&nbsp;&nbsp;
							<s:url id="removeDocuments"
									action="ajaxRemoveStudentDisabilityDocuments" namespace="/admin" escapeAmp="false" includeParams="all">
									<s:param name="tempId1" value="%{student.id}"></s:param>
									<s:param name="eventId">deleteAll</s:param>
									<s:param name="quizId" value="%{student.accountId}"></s:param>
								</s:url>
									<sj:a id="removeDocumentsLink" href="%{removeDocuments}"
										targets="academicSettingsContent" cssClass="btn btn-xs red">Delete All</sj:a>
								 
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
				<div class="form-group ">
					<label class="control-label col-md-4"> Nationality : </label>
					<div class="col-md-5">
						<sj:textfield name="newUser.person.nationality" id="nationality"
							cssClass="form-control input-medium" maxlength="60"></sj:textfield>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4"> Select Religion : </label>
					<div class="col-md-5">
						<s:select list="presentList" listKey="id" theme="simple"
							listValue="skillTypeName" cssClass=" form-control input-medium"
							name="newUser.person.religionId" headerKey=""
							headerValue="- Select -" />
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group ">
					<label class="control-label col-md-4"> Community : </label>
					<div class="col-md-5">
						<s:select id="castName" list="castSettingList" listKey="id"
							cssClass="form-control input-medium" listValue="castName"
							headerKey="" headerValue="- Select  Community-"
							name="newUser.person.castId" theme="simple"
							onchange="javascript:getSubCastDetailsByCast(this);" />
					</div>

				</div>
			</div>
			<div class="col-md-6">
				<div id="resultsDiv2">
					<jsp:include
						page="/WEB-INF/pages/admin/student/ajaxEditAdmittedStudentSubCastListByCast.jsp" />
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4"> Community Number : </label>
					<div class="col-md-7">
						<sj:textfield name="newUser.person.communityNumber"
							id="communityNumber" cssClass="form-control input-medium as-inpu"
							maxlength="50"></sj:textfield>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4"> Ration Card Number :
					</label>
					<div class="col-md-7">
						<sj:textfield name="newUser.person.rationCardNumber"
							id="rationCardNumber"
							cssClass="form-control input-medium as-inpu" maxlength="50"></sj:textfield>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4"> Select Mother Tongue
						: </label>
					<div class="col-md-7">
						<s:select list="tempList2" listKey="id" listValue="name"
							cssClass="form-control input-medium" label="Select MotherToung"
							name="newUser.person.motherToungId" headerKey=""
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
						<sj:textfield name="newUser.person.identification1"
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
						<sj:textfield name="newUser.person.identification2"
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
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4"> Summary : </label>
					<div class="col-md-7">
						<sj:textarea name="newUser.person.summary" id="collectedDocuments"
							cssClass="word_count form-control"
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
						<sj:textarea name="student.goals" id="maxlength_textarea1"
							cssClass="form-control"
							placeholder="This textarea has a limit of 200 chars." rows="4"
							cols="20" maxlength="200"></sj:textarea>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4"> Strengths : </label>
					<div class="col-md-7">
						<sj:textarea name="student.strengths" id="maxlength_textarea2"
							cssClass="form-control"
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
						<sj:textarea name="student.interestsAndHobbies"
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
						<sj:textarea name="student.responsibilities"
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
						<sj:textarea name="student.achievements" id="maxlength_textarea5"
							cssClass="form-control"
							placeholder="This textarea has a limit of 2000 chars." rows="4"
							cols="20" maxlength="200"></sj:textarea>
					</div>
				</div>
			</div>
		</div>
		<s:if
			test='%{(#session.academicYear == null || #session.academicYear.isEmpty()) || (#session.previousYear=="N")}'>
			<div class="form-actions fluid">
				<div class="col-md-offset-2 col-md-5">
					<sj:submit cssClass="submitBt btn blue" value="Submit"
						indicator="indicator" targets="admittedStudentDiv1"
						formIds="updatedStudentDetailsForm" validate="true"
						onBeforeTopics="updatedFormValidation" />
					<s:url id="urlGetAdmittedStudents"
						action="ajaxViewAdmittedStudents" includeParams="all"
						escapeAmp="false" namespace="/admin" />
					<sj:a href="%{urlGetAdmittedStudents}" targets="mainContentDiv"
						cssClass="btn default">Cancel</sj:a>
				</div>
			</div>
		</s:if>
	</s:form>
</div>
<div id="browseImageDiv"></div>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/plugins/bootstrap-switch/static/js/bootstrap-switch.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
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
		var phidVal = '<s:property value="newUser.person.phId"/>';
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
	//	var selectedMode='<s:property value='student.transportMode'/>';
		$('.numeric').numeric();
		//showTransportInfo(selectedMode);
	 	FormComponents.init();
		FormAdvanced.init();
		$.destroyTopic('updatedFormValidation');
		$("input:checkbox, input:radio:not('.toggle')").uniform(); 
		$.subscribe('updatedFormValidation', function(event, data) {
			if(isNonEmpty($('input.fileName').val())){
				var filename = $('input.fileName').val().toLowerCase();
				if(filename.lastIndexOf(".jpg") == -1 && filename.lastIndexOf(".png") == -1  &&  filename.lastIndexOf(".jpeg") == -1){
					alert("File not acceped. Please upload your file in jpg or jpeg or  png");
					event.originalEvent.options.submit = false;
				}
			}	
			if ($("input#transportModeT").is(":checked")) {
				$("#routeId").removeClass('required');
				$("#boardingId1").removeClass('required');
				$("#vehicleNumber").removeClass('required');
			} else {
				$("#routeId").removeClass('required');
				$("#boardingId1").removeClass('required');
				$("#vehicleNumber").removeClass('required');
			}
			if ($("#boardingId").is(":hidden")) {
				$("#boardingId").removeClass('required');
			} else {
				$("#boardingId").addClass('required');
			}
			if ($("#vehicleId").is(":hidden")) {
				$("#vehicleId").removeClass('required');
			} else {
				$("#vehicleId").addClass('required');
			}
		});
	
		
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
		var classSectionId = $("#classSectionId").val();
		if(classSectionId != 0)
			checkCommittedFee(classSectionId);
	
	});
	
	/* function showTransportInfo(value) {
	//alert(value);
		var transportMode =value;
	//$('input[value='+value+']').click(); or
	$('input[value='+value+']').attr("checked",true);
	$('input[value='+value+']').parent('span').addClass('checked');
		if (isNonEmpty(transportMode) && 'T' == transportMode) {
			$('#inputboxhideText').show();
		} else {
			$('#inputboxhideText').hide();
		}
		$('input[name="alertSendType"]').val(transportMode)
	} */
	 $("label.radioMultiOption").click(function(){
			var transportMode = $(this).find("input").val();
			$('input[name="alertSendType"]').val(transportMode)
			if(isNonEmpty(transportMode) && 'T' == transportMode){
				$('#inputboxhideText').show();
			}else{
				$('#inputboxhideText').hide();
			}
		});
	
	function getSubCastDetailsByCast(selectBox) {
		var castId = selectBox.value;
		var url = jQuery.url
				.getChatURL("/admin/ajaxStudentSubCastDetailsByCastDetails.do");
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
				}
			});
		}
	}
	function viewPopupStudensCapture(id){
		if(isNonEmpty(id)){
		  var pars = "id=" + id;
	        $.ajax( {
		        url : jQuery.url.getChatURL("/admin/ajaxCapturePhoto.do"),
				cache : true,
				data : pars,
				success : function(html) {
				$("#browseImageDiv").html(html);
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
	function checkCommittedFee(studyClassId){
		var categoryId = $("#categoryId").val();
		var academicYearId = $("#academicYearId").val();
		var classId = $("#classId").val();
		if (categoryId != 0 && academicYearId !=0 && classId !=0) {
			var feeURL = jQuery.url.getChatURL("/common/ajaxCheckClassWiseCommittedFee.do?classId="+classId+"&studyClassId="+ studyClassId + "&categoryId=" + categoryId+"&academicYearId="+academicYearId+"&tempString=AD");
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
			$("div.emsFileRemove").click(function() {
				confirmDeleteDocument(this);
			});
		}
	});
	function confirmDeleteDocument(event) {
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
					$('#academicSettingsContent').html(html);
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
</script>