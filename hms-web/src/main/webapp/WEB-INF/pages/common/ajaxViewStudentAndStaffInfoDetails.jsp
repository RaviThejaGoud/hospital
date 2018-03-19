<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/common/jquery.alphanumeric.js">
</script>
<%-- <div id="studentAndStafffInfo">
	<%@ include file="/common/messages.jsp"%>
</div> --%>
<hr />
<div class="form-body">
	<div class="tab-content">
	<div id="removeImageDiv"></div>
		<s:if test="%{studentVo != null && studentVo != empty}">
			<span id="classSectionId"
				class="<s:property value='studentVo.classSection.id'/>"> </span>
			<s:form action="ajaxEditStudent" theme="simple" namespace="/student"
				cssClass="form-horizontal" id="editStudentDetails" method="post"
				enctype="multipart/form-data">
				<s:hidden name="studentVo.id" />
				<s:hidden name="studentVo.dateOfJoiningStu" id="dateOfJoining1" />
				<s:hidden name="customerImage" id="customerImage"></s:hidden>
				<s:hidden name ="studentVo.custId" id="studentVo.custId"></s:hidden>
				<s:hidden name ="studentVo.academicYearVo.id" id="studentVo.academicYearVo.id"></s:hidden>
				<s:hidden name="studentVo.account.id"></s:hidden>
				<%-- <s:hidden name ="studentVo.studyClassVo.id" id="studentVo.academicYearVo.id"></s:hidden> --%>
				
				<div class="row">
					<div class="col-md-6">
						<%-- <div class="form-group">
							<label class="control-label col-md-4"> Roll Number : </label>
							<div class="col-md-8">
								<p class="form-control-static">
									<s:property value="studentVo.rollNumber" />
								</p>
							</div>
						</div> --%>
						<div class="form-group">
							<label class="control-label col-md-4"> Name : </label>
							<div class="col-md-8">
								<p class="form-control-static">
									<s:property value="studentVo.studentName" />
								</p>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-4"> Class &amp;
								Section : </label>
							<div class="col-md-8">
								<p class="form-control-static">
									<s:property value="studentVo.classAndSection" />
								</p>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-5"> Upload Student
								Image : </label>
							<div id="browseImage">
								<img src='<c:url value="${studentVo.studentAacademicImage}"/>'
									alt='' border="0" height="102px;" width="110px;"
									id="imageNotAvailableDiv" />

								<s:if
									test="%{studentVo.profileImage != null && studentVo.profileImage != empty}">
									<s:if test='%{studentVo.profileImage.id>0}'>
										<s:url id="removeImage"
											action="ajaxRemoveStudentAndStaffImage" includeParams="all"
											escapeAmp="false" namespace="/student">
											<s:param name="studentVo.profileImage.id"
												value="studentVo.profileImage.id" />
											<s:param name="studentVo.id" value="studentVo.id" />
										</s:url>
										<s:div cssClass="btn red"
											cssStyle="margin-left: -14px; position: absolute;top: 1px;padding:2px;"
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
								<strong>&nbsp;OR</strong>
							</div>
							<div class="col-md-5">
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
				</div>
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4"> <span
								class="required">*</span>First Name :
							</label>
							<div class="col-md-8">
								<sj:textfield name="studentVo.account.personVo.firstName"
									id="driverfName"
									cssClass="required form-control input-medium as-input"
									maxlength="50"></sj:textfield>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4"> Last Name : </label>
							<div class="col-md-8">
								<sj:textfield name="studentVo.account.personVo.lastName"
									id="driverlName" cssClass="form-control input-medium as-input"
									maxlength="40"></sj:textfield>
							</div>
						</div>
					</div>
				</div>
				
				<div class="row">
					<div class="col-md-6">
						<s:if test='%{user.isOnlySchoolAdmin=="Y"}'>
							<s:if test='%{studyClassList.size()>1}'>
								<div class="form-group">
									<label class="control-label col-md-4"> <span
								class="required">*</span>Change Class :
									</label>
									<div class="col-md-8">
									
									<s:select list="studyClassList" id="changeClassId" name="studentVo.studyClassVo.id"
										listKey="id" listValue="classAndSection" headerKey=""
										headerValue="- Select Class -"
										cssClass="form-control input-medium required" theme="simple"
										onchange="javascript:checkStudentFeepaymentAndMarks(this.value);checkCommittedFee(this.value);">
									</s:select>									
									</div>
								</div>
							</s:if>
							<s:else>
								<s:hidden name="studentVo.studyClassVo.id"></s:hidden>
								<s:hidden name="studentVo.classNameVo.id"></s:hidden>
							</s:else>
						</s:if>
						<s:else>
							<s:hidden name ="studentVo.studyClassVo.id" id="studentVo.studyClassVo.id"></s:hidden>
							<s:hidden name="studentVo.classNameVo.id"></s:hidden>
						</s:else>
					</div>
					
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4"> <span
								class="required">*</span>Admission Number : </label>
							<div class="col-md-8">
								<sj:textfield name="studentVo.account.admissionNumber"
									id="admissionNumber"
									cssClass="form-control input-medium as-input admissionNumber required"
									maxlength="50"></sj:textfield>
								<span class="help-block">(Enter at least one character
									for admissionnumber.)</span>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
		            <s:if test='%{customerByCustId.getAlphaNumericRollNumber()=="Y"}'>
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4"> Roll Number :
							</label>
							<div class="col-md-6">
								<sj:textfield name="studentVo.rollNumber"
									id="lastSchool" cssClass="form-control input-medium as-input" 
									maxlength="15"></sj:textfield>
							</div>
						</div>
					</div>
					</s:if>
					<%-- <div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4"> Place Of Birth : </label>
							<div class="col-md-6">
								<sj:textfield name="studentVo.account.personVo.placeOfBirth"
									id="placeOfBirth"
									cssClass="form-control input-medium as-inputl" maxlength="60"></sj:textfield>
							</div>
						</div>
					</div> --%>
					<s:else>
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4"> Roll Number :
							</label>
							<div class="col-md-6">
								<sj:textfield name="studentVo.rollNumber"
									id="lastSchool" cssClass="form-control input-medium as-input numeric" 
									maxlength="15"></sj:textfield>
							</div>
						</div>
				   </div>
				  </s:else>
				  <div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4"> STS Number : </label>
							<div class="col-md-6">
								<sj:textfield name="studentVo.account.personVo.stsNumber"
									id=""
									cssClass="form-control input-medium as-inputl studentstsstr" maxlength="15"></sj:textfield>
							</div>
						</div>
					</div>
				</div>
		
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4"> Date Of Joining : </label>
							<div class="col-md-8">
								<div class="input-group input-medium date date-picker"
									data-date-format="mm/dd/yyyy">
									<input type="text" readonly="readonly" class="form-control fdate"
										name="studentVo.account.personVo.dateOfJoining"
										value='<s:property value="studentVo.account.personVo.dateOfJoiningStr"/>'
										onchange="verifyDate();" id="studentJoiningDate"> <span
										class="input-group-btn">
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
							<label class="control-label col-md-4"> Phone Number : </label>
							<div class="col-md-8">
								<sj:textfield name="studentVo.account.personVo.phoneNumber"
									onkeypress="return onlyNumbers(event);" id="phoneNumber"
									cssClass="form-control input-medium as-input" maxlength="14"></sj:textfield>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4"> Login Id : </label>
							<div class="col-md-8">
								<sj:textfield name="studentVo.account.username" disabled="true"
									id="username" cssClass="form-control input-medium as-input"
									maxlength="25"></sj:textfield>
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
									<s:if test='%{studentVo.account.personVo.gender =="M"}'>
										<input type="radio" class="toggle" checked="checked"
											id="genderStaff">
										<input type="hidden" name="studentVo.account.personVo.gender"
											value='<s:property value="studentVo.account.personVo.gender"/>'>
									</s:if>
									<s:elseif test='%{studentVo.account.personVo.gender =="F"}'>
										<input type="radio" class="toggle" id="genderStaff">
										<input type="hidden" name="studentVo.account.personVo.gender"
											value='<s:property value="studentVo.account.personVo.gender"/>'>
									</s:elseif>
									<s:else>
										<input type="radio" class="toggle" checked="checked"
											id="genderStaff">
										<input type="hidden" name="studentVo.account.personVo.gender"
											value='M'>

									</s:else>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4"> Date of Birth : </label>
							<div class="col-md-8">
								<div class="input-group input-medium date date-picker"
									data-date-format="mm/dd/yyyy">
									<input type="text" readonly="readonly"
										class="form-control fdate" id="date0"
										value='<s:property value="studentVo.account.personVo.dateOfBirthStr"/>'
										name="studentVo.account.personVo.dateOfBirth"
										onchange="verifyStudentOfDate();"> <span
										class="input-group-btn">
										<button type="button" class="btn default">
											<i class="fa fa-calendar"></i>
										</button>
									</span>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4"> Admitted Class Name :
								</label>
							<div class="col-md-8">
								<sj:textfield name="studentVo.account.personVo.classJoined"
									id="classJoined" cssClass="form-control input-medium as-input"
									maxlength="20"></sj:textfield>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4"> <span
								class="required">*</span>Select Category :
							</label>
							<div class="col-md-8">
								<s:if
									test='%{studentVo.feePaidStatus =="N" || studentVo.feePaidStatus =="C"}'>
									<s:select list="schoolCategoriesList" id="categoryName"
										name="studentVo.categoryId" listKey="id"
										listValue="categoryName" headerKey=""
										headerValue="- Select Category -" theme="simple"
										cssClass="required form-control input-medium">
									</s:select>
								</s:if>
								<s:else>
									<s:select list="schoolCategoriesList" id="categoryName"
										name="studentVo.categoryId" listKey="id"
										listValue="categoryName" headerKey=""
										headerValue="- Select Category -" theme="css_xhtml"
										cssClass="required form-control input-medium" disabled="true">
									</s:select>
								</s:else>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4"> Register Number : </label>
							<div class="col-md-6">
								<sj:textfield name="studentVo.registerNumber"
									id="" cssClass="form-control input-medium as-inputl numeric"
									maxlength="15"></sj:textfield>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4"> Last School Name :
							</label>
							<div class="col-md-6">
								<sj:textfield name="studentVo.account.personVo.lastSchool"
									id="lastSchool" cssClass="form-control input-medium as-inputl"
									maxlength="60"></sj:textfield>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4"> Place Of Birth : </label>
							<div class="col-md-6">
								<sj:textfield name="studentVo.account.personVo.placeOfBirth"
									id="placeOfBirth"
									cssClass="form-control input-medium as-inputl" maxlength="60"></sj:textfield>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<%-- <s:if test='%{customer.transportModuleStatus==true}'>
						<div class="col-md-6">
							<div class="form-group">
								<label class="col-md-4 control-label"> Transport Mode :
								</label>
								<div class="col-md-8">
									<div class="clearfix">
										<div data-toggle="buttons" class="btn-group" id="transportDiv">
											<s:if
												test='%{(studentVo.transportMode =="O") || (studentVo.transportMode =="P" ) || (studentVo.transportMode =="T")}'>
												<label class="btn blue radioMultiOption"
													id='<s:property value="studentVo.transportMode"/>'> <input
													type="radio" value="O" class="toggle" id="transportMode"
													name="studentVo.transportMode"> Own
												</label>
												<label class="btn blue radioMultiOption"
													id='<s:property value="studentVo.transportMode"/>'> <input
													type="radio" class="toggle" value="P" id="transportMode"
													name="studentVo.transportMode"> Private
												</label>
												<label class="btn blue radioMultiOption"
													id='<s:property value="studentVo.transportMode"/>'> <input
													type="radio" class="toggle" value="T" id="transportMode"
													name="studentVo.transportMode"> School Transport
												</label>
											</s:if>
											<s:else>
												<label class="btn blue radioMultiOption active"
													id='<s:property value="studentVo.transportMode"/>'> <input
													type="radio" value="O" class="toggle" checked="checked"
													id="transportMode" name="studentVo.transportMode">
													Own
												</label>
												<label class="btn blue radioMultiOption"
													id='<s:property value="studentVo.transportMode"/>'> <input
													type="radio" class="toggle" value="P" id="transportMode"
													name="studentVo.transportMode"> Private
												</label>
												<label class="btn blue radioMultiOption"
													id='<s:property value="studentVo.transportMode"/>'> <input
													type="radio" class="toggle" value="T" id="transportMode"
													name="studentVo.transportMode"> School Transport
												</label>
											</s:else>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div id="inputboxhideText" style="display: none;">
							<div class="col-md-12">
								<span class='studBoardingPoint'
									id='<s:property value="studentVo.routeBoardingPointsVO.id"/>'></span>
								<span class='vehicleAcademicDetailsId' 
									id='<s:property value="studentVo.vehicleAcademicDetailsId"/>'></span>
								<jsp:include
									page="/WEB-INF/pages/common/ajaxAddStudentTransportInformation.jsp" />
							</div>
						</div>
					</s:if> --%>
					<s:if test="%{customer.hostelModuleStatus}">
						<div class="col-md-6">
							<div class="form-group">
								<label class="control-label col-md-4"> Residence Type :
								</label>
								<div class="col-md-8">
									<div class="make-switch has-switch" data-id="D" data-value="H"
										style="width: 200px" data-off="warning" data-on="success"
										data-off-label="Hostler" data-on-label="Day Scholar">
										<s:if test='%{studentVo.hostelMode =="D"}'>
											<input type="radio" class="toggle" checked="checked"
												id="hostel">
											<input type="hidden" name="studentVo.hostelMode"
												value='<s:property value="studentVo.hostelMode"/>'>
										</s:if>
										<s:elseif test='%{studentVo.hostelMode =="H"}'>
											<input type="radio" class="toggle" id="hostel">
											<input type="hidden" name="studentVo.hostelMode"
												value='<s:property value="studentVo.hostelMode"/>'>
										</s:elseif>
										<s:else>
											<input type="radio" class="toggle" checked="checked"
												id="hostel">
											<input type="hidden" name="studentVo.hostelMode" value='D'>
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
							<div class="col-md-6">
								<sj:textfield name="studentVo.account.personVo.studentEmail"
									id="studentEmail"
									cssClass="form-control input-medium as-inputl email"
									maxlength="90"></sj:textfield>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4"> Student Mobile
								Number : </label>
							<div class="col-md-7">
								<sj:textfield name="studentVo.account.personVo.studentMobile"
									id="studentMobile"
									cssClass="form-control input-medium as-input numeric"  onblur="return validateStuMobNumber(this.value)"
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
							<div class="col-md-6">
								<sj:textfield name="studentVo.account.personVo.studentUniqueNumber"
									id="studentUniqueNumber"
									cssClass="form-control input-medium as-inputl studentUniqueStr" maxlength="20"></sj:textfield>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4"> Aadhaar Card No : </label>
							<div class="col-md-8">
								<sj:textfield name="studentVo.account.personVo.aadharNumber" onchange="javascript:validateAadhaarCardNumber(this.value)"
									id="aadharCardNumber"
									cssClass="numeric form-control input-medium as-input" 
									maxlength="12"></sj:textfield>
							</div>
						</div>
					</div>
				</div>
				<s:if test='%{customer.committedFeeStatus == "Y"}'>
					<div class="row" id="committedFeeDiv">
						<div class="col-md-6">
							<div class="form-group">
								<label class="control-label col-md-4"> Committed Fee : </label>
								<div class="col-md-7">
									<s:if
										test='%{studentVo.feePaidStatus =="N" || studentVo.feePaidStatus =="C"}'>
										<sj:textfield name="studentVo.committedFee" id="committedFee"
											cssClass="numericDot form-control input-medium as-input"
											maxlength="7"></sj:textfield>
									</s:if>
									<s:else>
										<sj:textfield name="studentVo.committedFee" id="committedFee"
											cssClass="numericDot form-control input-medium as-input"
											maxlength="7" disabled="true"></sj:textfield>
									</s:else>
								</div>
							</div>
						</div>
					</div>
				</s:if>
				<div class="row">
					<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4">School Mess :
						</label>
						<div class="col-md-7">
							<div class="make-switch has-switch" data-id="N" data-value="Y"
								style="width: 200px" data-off="warning" data-on="success"
								data-off-label="Yes" data-on-label="No">
								<s:if test='%{studentVo.schoolMess =="N"}'>
									<input type="radio" class="toggle" checked="checked"
										id="schoolMessId">
									<input type="hidden" name="studentVo.schoolMess"
										value='<s:property value="studentVo.schoolMess"/>'>
								</s:if>
								<s:elseif test='%{studentVo.schoolMess =="Y"}'>
									<input type="radio" class="toggle" id="schoolMessId">
									<input type="hidden" name="studentVo.schoolMess"
										value='<s:property value="studentVo.schoolMess"/>'>
								</s:elseif>
								<s:else>
									<input type="radio" class="toggle" checked="checked"
										id="schoolMessId">
									<input type="hidden" name="studentVo.schoolMess" value='N'>
								</s:else>
							</div>
						</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4"> Student Enrollment Code : </label>
							<div class="col-md-8">
								<sj:textfield name="studentVo.account.enrollmentCode"
									id="enrollmentCode"
									cssClass="form-control input-medium as-input"
									maxlength="14"></sj:textfield>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
				
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4"> <span
								class="required">*</span>Is Below Poverty Line (BPL) :
							</label>
							<div class="col-md-7">
								<div class="radio-list">
									<label class="radio-inline"> 
									<input type="radio" id="bplStatusYes" value="Y" name="studentVo.bplStatus" class="bplStatusRadioClass"
										onclick="javascript: checkBPLStatus(this.value)"> Yes
									</label> <label class="radio-inline"> <input type="radio"
										name="studentVo.bplStatus" id="bplStatusNo" value="N" checked="checked" class="bplStatusRadioClass"
										onclick="javascript: checkBPLStatus(this.value)"> No
									</label>
								</div>
								<span class="bplStatusClass" id="<s:property value='studentVo.bplStatus'/>"></span>
							</div>
						</div>
					</div>
			
			
					<%-- <div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4">Is Below Poverty Line (BPL) : </label>
						<div class="col-md-7">
							<div class="make-switch has-switch" data-id="N" data-value="Y"
								style="width: 200px" data-off="warning" data-on="success"
								data-off-label="Yes" data-on-label="No">
								<s:if test='%{studentVo.bplStatus =="N"}'>
									<input type="radio" class="toggle" checked="checked"
										id="bplStatus">
									<input type="hidden" name="studentVo.bplStatus"
										value='<s:property value="studentVo.bplStatus"/>'>
								</s:if>
								<s:elseif test='%{studentVo.bplStatus =="Y"}'>
									<input type="radio" class="toggle" id="bplStatus">
									<input type="hidden" name="studentVo.bplStatus"
										value='<s:property value="studentVo.bplStatus"/>'>
								</s:elseif>
								<s:else>
									<input type="radio" class="toggle" checked="checked"
										id="bplStatus">
									<input type="hidden" name="studentVo.bplStatus" value='N'>
								</s:else>
							</div>
						</div>
						</div>
					</div> --%>
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4">  Is RTE Student (Free Education)  : </label>
							<div class="col-md-7">
							<div class="make-switch has-switch" data-id="N" data-value="Y"
								style="width: 200px" data-off="warning" data-on="success"
								data-off-label="Yes" data-on-label="No">
								<s:if test='%{studentVo.rteStatus =="N"}'>
									<input type="radio" class="toggle" checked="checked"
										id="rteStatus">
									<input type="hidden" name="studentVo.rteStatus"
										value='<s:property value="studentVo.rteStatus"/>'>
								</s:if>
								<s:elseif test='%{studentVo.rteStatus =="Y"}'>
									<input type="radio" class="toggle" id="rteStatus">
									<input type="hidden" name="studentVo.rteStatus"
										value='<s:property value="studentVo.rteStatus"/>'>
								</s:elseif>
								<s:else>
									<input type="radio" class="toggle" checked="checked"
										id="rteStatus">
									<input type="hidden" name="studentVo.rteStatus" value='N'>
								</s:else>
							</div>
						</div>
						</div>
					</div>
				</div>
				
				<div class="row">
					<div class="col-md-6">
							<div class="form-group">
								<label class="control-label col-md-4"> Student BankId :    </label>
								<div class="col-md-6">
									<sj:textfield name="studentVo.account.personVo.studentBankId" id="studentBankId"
										cssClass="form-control input-medium as-input"
										maxlength="15"></sj:textfield>
								</div>
							</div>
					</div>
					<div class="col-md-6" id="bplNumberDivId" style="display: none;">
					<div class="form-group">
						<label class="control-label col-md-4"> Below Poverty Line Number : </label>
						<div class="col-md-8">
							<sj:textfield name="studentVo.bplNumber" id="bplNumber"
								cssClass="form-control input-medium as-input" maxlength="50"></sj:textfield>
						</div>
					</div>
				</div>
				</div>
				<div class="form-actions fluid">
					<div class="col-md-offset-2 col-md-9">
						<s:if
							test='%{(#session.previousYear == null || #session.previousYear.isEmpty()) || (#session.previousYear == "N")}'>
							<sj:submit cssClass="submitBt btn blue" value="Save" id="studentSubmitDiv"
								targets="staffList" formIds="editStudentDetails"
								indicator="indicator"
								onBeforeTopics="changeStudentInfoFormValidation" validate="true" />
						</s:if>
						<s:url id="urlMyStudentsLink" action="ajaxGetStudyClassList"
							namespace="/student">
							<s:param name="staff.id" value="0">
							</s:param>
						</s:url>
						<sj:a href="%{urlMyStudentsLink}" targets="mainContentDiv"
							cssClass="btn default"> Cancel</sj:a>
					</div>
				</div>
			</s:form>
		</s:if>
	</div>
</div>
<div id="studentsImgDiv"></div>
<span class="personId" id="<s:property value='studentVo.account.personVo.id'/>"></span>
<script  type="text/javascript" src="${pageContext.request.contextPath}/plugins/bootstrap-switch/static/js/bootstrap-switch.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	
	$('#changeClassId').val($('#classId').val());
	
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
  var imagePath = '<s:property value="studentVo.studentAacademicImage"/>';
	if(isNonEmpty(imagePath) && imagePath !=null && imagePath != "/images/common/photo_notAvailable.jpg"){
		$('#imageNotAvailableDiv').prop('src', imagePath + '?' + Math.random())
	}  
	$.destroyTopic('changeStudentInfoFormValidation');
	FormComponents.init(); 
 	FormAdvanced.init();
 	
 	var bplStatus = '<s:property value="studentVo.bplStatus"/>';
	if ("Y" == bplStatus)
		$('#bplNumberDivId').show();
	
	
	$("input:checkbox, input:radio:not('.toggle')").uniform(); 
	
	$("input#studentUniqueNumber").autoCheck("${pageContext.request.contextPath}/common/ajaxCheckStudentUniqueNumber.do?anyId="+$('span.personId').attr('id'),{
		minChars : 1,
		min : "no",
	});
	
	$.subscribe('changeStudentInfoFormValidation', function(event, data) {
	$('#studentPersonalDetails').hide();
		var filename = $('input.fileName').val().toLowerCase();
		if (isNonEmpty($("input.fileName").val())){
			if(filename.lastIndexOf(".jpg") == -1 && filename.lastIndexOf(".png") == -1  &&  filename.lastIndexOf(".jpeg") == -1){
				alert("File not acceped. Please upload your file in jpg or jpeg or  png");
				event.originalEvent.options.submit = false;
			}
		}
		var studUniNum=$('input.studentUniqueStr').parents('div').next('div').find('p.word-taken').html();
		if(studUniNum=='Already taken!!!'){
    	    event.originalEvent.options.submit=false;
    	    $('input.studentUniqueStr').val('');
         }
		if ($('input#checkedStudent').is(':checked')) {
			$('textarea#messageDescription').addClass('required');
			$('#editStudentDetails').validate();
		} else {
			$('textarea#messageDescription').removeClass('required');
		}
		
		bplStatus = $("input.bplStatusRadioClass:checked").val();
		if(bplStatus == "Y")
		{
			bplNumber = $('#bplNumber').val();
			if(!isNonEmpty(bplNumber))
			{
				alert("please enter Below Poverty Line Number.");
				event.originalEvent.options.submit = false;
			}
		}
		
	});
	var classSectionId = $('#classSectionId').attr('class');
	if(classSectionId !=0){
		checkCommittedFee(classSectionId);	
	}
	
	
	var bplStatusClassStatus = $('span.bplStatusClass').attr('id');
	if (isNonEmpty(bplStatusClassStatus)) {
		if (bplStatusClassStatus == 'Y') {
			$('#bplStatusYes').parent('span').addClass('checked');
			$("#bplStatusYes").attr("checked", true);
		} else {
			$('#bplStatusNo').parent('span').addClass('checked');
			$("#bplStatusNo").attr("checked", true);
		}
	}
	
	
});
$('.numericDot').numeric( {
	allow : "."
});
$('.numeric').numeric();
function verifyStudentOfDate() {
	var joiningValue = $('#studentJoiningDate').val();
	var studentJoiningDate = "";
	if (isNonEmpty(joiningValue)) {
		studentJoiningDate = joiningValue;
	} else {
		studentJoiningDate = $('input#dateOfJoining1').val();
	}
	var date0 = $('#date0').val();
	if (isNonEmpty(studentJoiningDate) && isNonEmpty(date0)) {
		date0 = new Date(date0);
		studentJoiningDate = new Date(studentJoiningDate);
		var birthYear = new Date(date0.setYear(date0.getFullYear() + 2));
		if (studentJoiningDate < birthYear) {
			$('#date0').val('');
			alert("Date of joining should be after 2 years of date of birth date.");
		}
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
 
 $("label.radioMultiOption").click(function(){
	var transportMode = $(this).find("input").val();
	if(isNonEmpty(transportMode) && 'T' == transportMode){
		$('#inputboxhideText').show();
	}else{
		$('#inputboxhideText').hide();
	}
});
 function checkCommittedFee(studyClassId){ 
		var categoryId = $("#categoryName").val();
		if (categoryId != 0) {
			var feeURL = jQuery.url.getChatURL("/common/ajaxCheckClassWiseCommittedFee.do?studyClassId="+ studyClassId + "&categoryId=" + categoryId);
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
								$("#committedFee").value(0.0);
							}
						}else{
							$('#committedFeeDiv').hide();
							$("#committedFee").value(0.0);
						}
					}
				}
			});
		}else{
			alert("Please select category");
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
						$('button.close').click();
						$('#removeImageDiv').html("<div class='alert alert-success'><button class='close' data-dismiss='alert'></button> <strong>Image removed successfully.<strong>.</div>");
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
		if(isNonEmpty(txtMobId)){
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
	
</script>