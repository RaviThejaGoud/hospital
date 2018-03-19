<%@ include file="/common/taglibs.jsp"%>
<jsp:include page="/common/messages.jsp" />
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/common/jquery.alphanumeric.js"></script>
<div class="form-body">
	<s:if test="%{studyClassList != null && !studyClassList.isEmpty()}">
		<s:form action="ajaxDoAddNewStudent" theme="simple" id="addStudent"
			cssClass="form-horizontal" enctype="multipart/form-data"
			method="post" namespace="/student">
			<s:hidden name="customerImage" id="customerImage"></s:hidden>
			<s:hidden name="studentName" id="studentName" value="0"></s:hidden>
			<s:hidden name="studyClassIdVal" id="studyClassHiddId"></s:hidden>
			<span class="label label-danger">NOTE :</span> You are adding new student into the system. Please provide the
				mandatory data
		<h4 class="pageTitle bold form-section">STUDENT INFORMATION</h4>
		
		<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="col-md-4 control-label"> <span
							class="required">*</span>Select Class :
						</label>
						<div class="col-md-8">
							<s:select list="studyClassList" listKey="id" id="studyClassId"
								listValue="classAndSection" theme="simple"
								cssClass="required form-control input-medium"
								name="studyClassId" headerKey="" headerValue="- Select -" onchange="showAdmissionNumber(this.value);">
							</s:select>
						</div>
					</div>
				</div>
				
				<div  id="admissionDivId" class="admissionDivId"></div>
				
			</div>
			
			
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> <span
							class="required">*</span>First Name :
						</label>
						<div class="col-md-8">
							<sj:textfield name="personVo.firstName" id="driverfName"
								cssClass="required form-control input-medium as-input"
								maxlength="50"></sj:textfield>
						</div>
					</div>
				</div>
				
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> Last Name : </label>
						<div class="col-md-7">
							<sj:textfield name="personVo.lastName" id="driverlName"
								cssClass="form-control input-medium as-input" maxlength="40"></sj:textfield>
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
								<input type="radio" class="toggle" checked="checked" id="gender">
								<input type="hidden" name="personVo.gender" value="M">
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> Date of Birth : </label>
						<div class="col-md-8">
							<div class="input-group input-medium date date-picker"
								data-date-end-date="+0d">
								<input type="text" readonly="readonly" class="form-control"
									id="date0" name="personVo.dateOfBirth" onchange="verifyDate();">
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
						<label class="control-label col-md-4"> <span
							class="required">*</span>Select Category :
						</label>
						<div class="col-md-7">
							<s:select id="categoryId" list="schoolCategoriesList"
								cssClass="required form-control input-medium" listKey="id"
								listValue="categoryName" theme="simple" headerKey=""
								headerValue="- Select Category-" name="studentVo.categoryId" />
						</div>
					</div>
				</div>
				<div class="col-md-6">
				    <div class="form-group">
				        <label class="control-label col-md-4">Register Number: </label>
				        <div class="col-md-6">
				            <sj:textfield name="studentVo.registerNumber" id=""
				                cssClass="form-control input-medium as-input"
				                maxlength="15" ></sj:textfield>
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
							<sj:textfield name="personVo.studentUniqueNumber"
								id="studentUniqueNumber"
								cssClass="form-control input-medium as-input" maxlength="20"></sj:textfield>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> STS Number : </label>
						<div class="col-md-7">
							<sj:textfield name="personVo.stsNumber"
								id=""
								cssClass="form-control input-medium as-input" maxlength="15"></sj:textfield>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> Student E-Mail : </label>
						<div class="col-md-7">
							<sj:textfield name="personVo.studentEmail" id="studentEmailId"
								cssClass="form-control input-medium as-input email"
								maxlength="90"></sj:textfield>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> Student Mobile
							Number : </label>
						<div class="col-md-6">
							<sj:textfield name="personVo.studentMobile" id="studentMobile"
								cssClass="form-control input-medium as-input numeric"  onblur="return validateStuMobNumber(this.value)"
								maxlength="10"></sj:textfield>
						</div>
					</div>
				</div>
			</div>

			<div class="row">
				<s:if test="%{customer.hostelModuleStatus}">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4"> Residence Type : </label>
							<div class="col-md-7">
								<div class="make-switch has-switch" data-id="D" data-value="H"
									style="width: 200px" data-off="warning" data-on="success"
									data-off-label="Hostler" data-on-label="Day Scholar">
									<input type="radio" class="toggle" checked="checked"
										id="hostlerMode"> <input type="hidden"
										name="studentVo.hostelMode" value="D">
								</div>
							</div>
						</div>
					</div>
				</s:if>
				<div class="col-md-6">
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
							<div>
								<strong>OR</strong>
							</div>
							<div>
								<label> Upload Image : </label>
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
			</div>
			
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> Out Side School
							Duty : </label>
						<div class="col-md-7">
							<div class="make-switch has-switch" data-id="N" data-value="Y"
								style="width: 120px" data-off="warning" data-on="success"
								data-off-label="Yes" data-on-label="No">
								<input type="radio" class="toggle" checked="checked" id="outSideDuty">
								<input type="hidden" name="studentVo.outSideSchoolDuty" value="N">
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> School Mess : </label>
						<div class="col-md-7">
							<div class="make-switch has-switch" data-id="N" data-value="Y"
								style="width: 120px" data-off="warning" data-on="success"
								data-off-label="Yes" data-on-label="No">
								<input type="radio" class="toggle" checked="checked" id="schoolMess">
								<input type="hidden" name="studentVo.schoolMess" value="N">
							</div>
						</div>
					</div>
				</div>
			</div>
		
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> Aadhaar Card No : </label>
						<div class="col-md-7">
							<sj:textfield name="personVo.aadharNumber" id="aadharCardNumber"
								cssClass="numeric form-control input-medium as-input" onchange="javascript:validateAadhaarCardNumber(this.value)"
								maxlength="12" minlength="10"></sj:textfield>
						</div>
					</div>
				</div>
				<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4"> Student BankId : </label>
							<div class="col-md-6">
								<sj:textfield name="personVo.studentBankId" id="studentBankId"
									cssClass="form-control input-medium as-input"
									maxlength="15"></sj:textfield>
							</div>
						</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> Student Enrollment Code : </label>
						<div class="col-md-6">
							<sj:textfield name="userVo.enrollmentCode" id="enrollmentCode"
								cssClass="form-control input-medium as-input"
								maxlength="15"></sj:textfield>
						</div>
					</div>
				</div>
				<!--  <div  id="rollNumberDivId" class="rollNumberDivId"></div> -->
				<s:if test='%{customer.committedFeeStatus == "Y"}'>
					<div class="row" id="committedFeeDiv">
						<div class="col-md-6">
							<div class="form-group">
								<label class="control-label col-md-4"> Committed Fee : </label>
								<div class="col-md-7">
									<sj:textfield name="studentVo.committedFee" id="committedFee"
										cssClass="numericDot form-control input-medium as-input"
										maxlength="7"></sj:textfield>
								</div>
							</div>
						</div>
					</div>
				</s:if>
			</div>
			<div class="row">
				
		       <s:if test='%{customerByCustId.getAlphaNumericRollNumber()=="Y"}'>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> Roll Number : </label>
						<div class="col-md-6">
							<sj:textfield name="studentVo.rollNumber" id="rollNumber"
								cssClass="form-control input-medium as-input"
								maxlength="15"></sj:textfield>
						</div>
					</div>
				</div>
				</s:if>
			</div>
			<h4 class="pageTitle bold form-section">STUDENT EDUCATIONAL
				INFORMATION</h4>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> Date of Joining : </label>
						<div class="col-md-8">
							<div class="input-group input-medium date date-picker">
								<input type="text" readonly="readonly" class="form-control"
									id="studentJoiningDate" name="personVo.dateOfJoining"
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
				
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> Is RTE Student (Free Education)  : </label>
						<div class="col-md-7">
							<div class="make-switch has-switch" data-id="N" data-value="Y"
								style="width: 200px" data-off="warning" data-on="success"
								data-off-label="Yes" data-on-label="No">
								<input type="radio" class="toggle" checked="checked"
									id="rteStatus"> <input type="hidden"
									name="studentVo.rteStatus" value="N">
							</div>
						</div>
					</div>
				</div>
				
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> Admitted Class Name :
							 </label>
						<div class="col-md-8">
							<sj:textfield name="personVo.classJoined" id="classJoined"
								cssClass="form-control input-medium as-input" maxlength="30"></sj:textfield>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> Last School Name :
						</label>
						<div class="col-md-6">
							<sj:textfield name="personVo.lastSchool" id="lastSchool"
								cssClass="form-control input-medium as-inputl" maxlength="60"></sj:textfield>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> SSLC Number : </label>
						<div class="col-md-8">
							<sj:textfield name="personVo.sslcNumber" id="sslcNumber"
								cssClass="form-control input-medium as-input" maxlength="12"></sj:textfield>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> TMR Number : </label>
						<div class="col-md-8">
							<sj:textfield name="personVo.tmrNumber" id="tmrNumber"
								cssClass="form-control input-medium as-input" maxlength="12"></sj:textfield>
						</div>
					</div>
				</div>
			</div>
			
			<h4 class="pageTitle bold form-section">PARENT / GUARDIAN'S
				INFORMATION</h4>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> Father Name : </label>
						<div class="col-md-8">
							<sj:textfield name="personVo.fatherName" id="fatherName"
								cssClass="form-control input-medium as-input" maxlength="50"></sj:textfield>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> Occupation : </label>
						<div class="col-md-8">
							<sj:textfield name="personVo.occupation" id="occupation"
								cssClass="form-control input-medium as-input" maxlength="60"></sj:textfield>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> Mother Name : </label>
						<div class="col-md-8">
							<sj:textfield name="personVo.motherName" id="motherName"
								cssClass="form-control input-medium as-input" maxlength="50"></sj:textfield>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> Mother Occupation :
						</label>
						<div class="col-md-8">
							<sj:textfield name="personVo.motherOccupation"
								id="motherOccupation"
								cssClass="form-control input-medium as-input" maxlength="60"></sj:textfield>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> Annual Income : </label>
						<div class="col-md-8">
							<sj:textfield name="personVo.annualIncome" id="annualIncome"
								cssClass="form-control input-medium as-input"></sj:textfield>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> Place Of Birth : </label>
						<div class="col-md-8">
							<sj:textfield name="personVo.placeOfBirth" id="placeOfBirth"
								cssClass="form-control input-medium as-input" maxlength="50"></sj:textfield>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4">Address For Communication : </label>
						<div class="col-md-7">
							<div class="make-switch has-switch" data-id="R" data-value="C"
								style="width: 237px" data-off="warning" data-on="success"
								data-off-label="Correspondence" data-on-label="Residential">
								<input type="radio" class="toggle" checked="checked"
									id="addressType"> <input type="hidden"
									name="personVo.addressType" value="R">
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> <span
							class="required">*</span>PTA (Parent Teacher Association) :
						</label>
						<div class="col-md-7">
							<div class="make-switch has-switch" data-id="N" data-value="Y"
								style="width: 200px" data-off="warning" data-on="success"
								data-off-label="Yes" data-on-label="No">
								<input type="radio" class="toggle" checked="checked"
									id="ptaStatus"> <input type="hidden"
									name="studentVo.ptaStatus" value="N">
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> Father Aadhaar Card No : </label>
						<div class="col-md-8">
							<sj:textfield name="personVo.fatherAadharNumber" id="fatherAadharNumber"
								cssClass="form-control input-medium as-input" maxlength="14" minlength="12"></sj:textfield>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> Mother Aadhaar Card No : </label>
						<div class="col-md-8">
							<sj:textfield name="personVo.motherAadharNumber" id="motherAadharNumber"
								cssClass="form-control input-medium as-input" maxlength="14" minlength="12"></sj:textfield>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4"> Below Poverty Line (BPL) :
					</label>
					<div class="col-md-7">
						<div class="radio-list">
							<label class="radio-inline"> 
							<input type="radio" id="bplStatusYes" value="Y" name="studentVo.bplStatus" class="bplStatusClass"
								onclick="javascript: checkBPLStatus(this.value)"> Yes
							</label> <label class="radio-inline"> <input type="radio"
								name="studentVo.bplStatus" id="bplStatusNo" value="N" checked="checked" class="bplStatusClass"
								onclick="javascript: checkBPLStatus(this.value)"> No
							</label>
						</div>
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
			<h4 class="pageTitle bold form-section">CONTACT DETAILS</h4>
			<h4>RESIDENTIAL ADDRESS</h4>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> Primary Mobile Number:
						</label>
						<div class="col-md-8">
							<sj:textfield name="personVo.mobileNumber" id="stumobileNumber"
								cssClass="numeric form-control input-medium as-input"
								maxlength="10"
								onchange="javascript:validateMobNumbers(this.value)"></sj:textfield>
								<span class="help-block">(This is considered as Parent Login Id)</span>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4">Secondary Mobile Number :
						</label>
						<div class="col-md-8">
							<sj:textfield name="personVo.secondaryMobileNumber" id="stumobileNumbers"
								cssClass="numeric form-control input-medium as-input"
								maxlength="10"
								onchange="javascript:validateMobNumbers(this.value)"></sj:textfield>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> Parent E-Mail : </label>
						<div class="col-md-8">
							<sj:textfield name="personVo.parentEmail" id="emailAddress"
								cssClass="form-control input-medium as-input email" maxlength="50"></sj:textfield>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> Phone Number : </label>
						<div class="col-md-8">
							<sj:textfield name="personVo.phoneNumber" id="phoneNumber"
								cssClass="form-control input-medium as-input" maxlength="13"></sj:textfield>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> Address Line1 : </label>
						<div class="col-md-8">
							<sj:textfield name="addressVo.addressLine1" id="addressLine1"
								cssClass="form-control input-medium as-input" maxlength="255"></sj:textfield>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> Address Line2 : </label>
						<div class="col-md-8">
							<sj:textfield name="addressVo.addressLine2" id="addressLine2"
								cssClass="form-control input-medium as-input" maxlength="255"></sj:textfield>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> City : </label>
						<div class="col-md-8">
							<sj:textfield name="addressVo.city" id="city"
								cssClass="form-control input-medium as-input" maxlength="22"></sj:textfield>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> State : </label>
						<div class="col-md-8">
							<s:select id="state" list="statesList" label="State"
								cssClass="form-control input-medium" listKey="stateCode"
								listValue="stateName" headerKey="" headerValue="- Select -"
								name="addressVo.state"
								onchange="javascript:getCastDetailsByState(this);" />
						</div>
					</div>
				</div>
			</div>
			
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> Pincode : </label>
						<div class="col-md-8">
							<sj:textfield name="addressVo.postalCode" id="pincode"
								cssClass="numeric form-control input-medium as-input"
								maxlength="6"></sj:textfield>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> House Type : </label>
						<div class="col-md-8">
							<s:select id="id" list="houseTypeList"
							cssClass="form-control input-medium" listKey="id"
							listValue="type" headerKey="" headerValue="- Select -"
							name="addressVo.houseTypeId" />			
						</div>
					</div>
				</div>
			</div>
			
			<div class="clearfix">&nbsp;</div>
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
			<h4>CORRESPONDENCE ADDRESS</h4>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> Primary Mobile Number :
						</label>
						<div class="col-md-8">
							<sj:textfield name="personVo.anotherMobileNumber" id="tstumobileNumber"
								cssClass="numeric form-control input-medium as-input"
								maxlength="10"
								onchange="javascript:validateMobNumbers(this.value)"></sj:textfield>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4">Secondary Mobile Number :
						</label>
						<div class="col-md-8">
							<sj:textfield name="personVo.anotherSecondaryMobileNumber" id="tstumobileNumbers"
								cssClass="numeric form-control input-medium as-input"
								maxlength="10"
								onchange="javascript:validateMobNumbers(this.value)"></sj:textfield>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4">Contact E-Mail : </label>
					<div class="col-md-8">
						<sj:textfield name="personVo.anotherParentEmail" id="temailAddress"
							cssClass="form-control input-medium as-input email" maxlength="50"></sj:textfield>
					</div>
				</div>
			</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> Address Line1 : </label>
						<div class="col-md-8">
							<sj:textfield name="studentVo.account.tempararyAddressVo.addressLine1"
								id="addressLine11" cssClass="form-control input-medium as-input"
								maxlength="255"></sj:textfield>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> Address Line2 : </label>
						<div class="col-md-8">
							<sj:textfield name="studentVo.account.tempararyAddressVo.addressLine2"
								id="addressLine21" cssClass="form-control input-medium as-input"
								maxlength="255"></sj:textfield>
						</div>
					</div>
				</div>
				
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> City : </label>
						<div class="col-md-8">
							<sj:textfield name="studentVo.account.tempararyAddressVo.city"
								id="city1" cssClass="form-control input-medium as-input"
								maxlength="22"></sj:textfield>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> State : </label>
						<div class="col-md-8">
							<s:select id="state1" list="statesList" label="State"
								cssClass="form-control input-medium" listKey="stateCode"
								listValue="stateName" headerKey="" headerValue="- Select -"
								name="studentVo.account.tempararyAddressVo.state"
								onchange="javascript:getCastDetailsByState(this);" />
						</div>
					</div>
				</div>
			</div>
			
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> Pincode : </label>
						<div class="col-md-8">
							<sj:textfield name="studentVo.account.tempararyAddressVo.postalCode"
								id="pincode1"
								cssClass="numeric form-control input-medium as-input"
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
						<div class="col-md-8">
							<s:select id="bGroup"
								list="#{'A+':'A+ve','A-':'A-ve','A1+':'A1+ve','A1-':'A1-ve','A1B+':'A1B+ve','A1B-':'A1B-ve','A2B+':'A2B+ve','A2B-':'A2B-ve','A2+':'A2+ve','A2-':'A2-ve','B+':'B+ve','B-':'B-ve','AB+':'AB+ve','AB-':'AB-ve','O+':'O+ve','O-':'O-ve'}"
								cssClass="form-control input-medium" headerKey=""
								headerValue="- Select -" name="personVo.bloodGroup" />
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> Teeth : </label>
						<div class="col-md-8">
							<sj:textfield name="personVo.teeth" id="teeth"
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
							<sj:textfield name="personVo.visionL" id="visionLeft"
								cssClass="form-control input-medium as-input " maxlength="10"></sj:textfield>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> Vision (Right) : </label>
						<div class="col-md-8">
							<sj:textfield name="personVo.visionR" id="visionRight"
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
							<sj:textfield name="personVo.weight" id="weight"
								cssClass="form-control input-medium as-input numericDot"
								maxlength="10"></sj:textfield>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> Height 1 (C.M): </label>
						<div class="col-md-8">
							<sj:textfield name="personVo.height" id="height"
								cssClass="numericDot  form-control input-medium as-input"
								maxlength="10"></sj:textfield>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> Weight 2 : </label>
						<div class="col-md-8">
							<sj:textfield name="personVo.weight2" id="weight"
								cssClass="form-control input-medium as-input numericDot"
								maxlength="10"></sj:textfield>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> Height 2 (C.M): </label>
						<div class="col-md-8">
							<sj:textfield name="personVo.height2" id="height"
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
							<sj:textfield name="personVo.oralHygiene" id="oralHYgiene"
								cssClass="form-control input-medium as-input" maxlength="40"></sj:textfield>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> Family Doctor : </label>
						<div class="col-md-8">
							<sj:textfield name="personVo.familyDoctor" id="familyDoctor"
								cssClass="form-control input-medium as-input" maxlength="50"></sj:textfield>
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
							<sj:textfield name="personVo.prefferedHospital"
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
							<!-- <div class="make-switch has-switch" data-id="Y" data-value="N"
								id="personPhidDivId" style="width: 120px" data-off="warning"
								data-on="success" data-off-label="No" data-on-label="Yes">
								<input type="radio" class="toggle" id="stPhId"> <input
									type="hidden" name="plTitle" value="N" id="personPhid">
							</div> -->
							<div class="make-switch has-switch" data-id="Y" data-value="N"
								id="personPhidDivId" style="width: 120px" data-off="warning"
								data-on="success" data-off-label="No" data-on-label="Yes">
								<input type="radio" class="toggle" id="stPhId" onclick="javascript:displayPHCDiv(this.value)"> <input
									type="hidden" name="plTitle" value="N" id="personPhid">
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="row" id="physicallyHandiCappedDivId" style="display: none;">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4">
							Disability Details :</label>
						<div class="col-md-7">
							<sj:textarea id="disabilityDes" rows="3" cols="20" name="personVo.physicallyHandicappedDesc" maxCharsData="1000" tabindex="3" cssClass="form-control word_count"></sj:textarea>
							<span class="help-block">
								<div class="counter"></div> </span>
						</div>
					</div>
				</div>
				<%-- <div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4">
							Disability Documents : 
						</label>
						<div class="col-md-7">
							<!-- <input type="file" class="btn default browseButton" name="fileUpload" /> -->
							<s:file name="upload" id="uploadDocs" multiple="multiple" cssClass="btn default"></s:file>
							<div class="phFileUpload sphFileUpload-new" data-provides="phFileUpload">
								<div class="input-append">
									<span class="btn default"> <s:file name="phFileUpload"
											id="photoURL1s" cssClass="a"></s:file>
									</span>
								</div>
							</div>
						</div>
					</div>
				</div> --%>
			</div>
			<div class="form-actions fluid">
				<div class="col-md-offset-2 col-md-9">
					<s:if
						test='%{(#session.previousYear == null || #session.previousYear.isEmpty()) || (#session.previousYear == "N")}'>
						<sj:submit cssClass="btn blue" value="Next" indicator="indicator"
							onBeforeTopics="validateImageFormat" targets="staffList"
							validate="true" />
					</s:if>
					(Click on 'Next' button, to add more student details.)
				</div>
			</div>
		</s:form>
	</s:if>
	<s:else>
		<div class="alert alert-info">Please Add classes in ACADEMICS
			Section.</div>
	</s:else>
</div>
<div id="studentsImgDiv"></div>
<script language="JavaScript" type="text/javascript" src="${pageContext.request.contextPath}/plugins/bootstrap-switch/static/js/bootstrap-switch.min.js"></script>
<script language="JavaScript" type="text/javascript">
/* $('#admission').bind('input', function() {
	  $(this).val($(this).val().replace(/[ \, :, ? ,<, > ,|,/]/, ''));
	}); */
	changePageTitle('Add Student Details');
	$(document)	.ready(	function() {
		
		FormComponents.init();
		FormAdvanced.init();
		UIExtendedModals.init();
		$("input:checkbox, input:radio:not('.toggle')")
				.uniform();

		$("#admission").focus();
		//$("select#addStudent_studyClassId").val('');
		/*Remove admission number validation because few schools are asking to allow duplicate admission number*/
		 
		$("input#studentUniqueNumber").autoCheck("${pageContext.request.contextPath}/common/ajaxCheckStudentUniqueNumber.do",
			{
				minChars : 1,
				min : "no"
			});
		$('.numeric').numeric();
		$("#searchStudentsList").hide();
		$.destroyTopic('validateImageFormat');
		
		
	});
	$('.numeric').numeric();
	$('.numericDot').numeric({
		allow : "."
	});
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
	function validateMobNumbers(txtMobId) {
		 if (isNonEmpty(txtMobId) || !txtMobId.length == 10) {
			///var stuMob = $("#studentMobile").val();
			var ParresMob1 = $("#stumobileNumber").val();
			var ParresMob2 = $("#stumobileNumbers").val();
			var PartempMob1 = $("#tstumobileNumber").val();
			var PartempMob2 = $("#tstumobileNumbers").val();
			
			/* if (!(stuMob.length == 10)){
				$("#studentMobile").val('');
				$("#studentMobile").focus();
			} */
			if(isNonEmpty(ParresMob1)){
				 if (!(ParresMob1.length == 10)){
					alert("Please enter 10 digits mobile number.");
					$("#stumobileNumber").val('');
					$("#stumobileNumber").focus();
				 }
			}
			if(isNonEmpty(ParresMob2)){
				 if (!(ParresMob2.length == 10)){
					 alert("Please enter 10 digits mobile number.");
					$("#stumobileNumbers").val('');
					$("#stumobileNumbers").focus();
				 }
				
			}
			if(isNonEmpty(PartempMob1)){
				 if (!(PartempMob1.length == 10)){
					alert("Please enter 10 digits mobile number.");
					$("#tstumobileNumber").val('');
					$("#tstumobileNumber").focus();
				 }
				
			}
			if(isNonEmpty(PartempMob2)){
				if (!(PartempMob2.length == 10)){
					alert("Please enter 10 digits mobile number.");
					$("#tstumobileNumbers").val('');
					$("#tstumobileNumbers").focus();
				}
				
			}
			
			//$("#mobileNumber").val("");
			//$("#mobileNumber").focus();
			return false;
		} else if ((txtMobId.length == 10)) {
			return true;
		}
	}
	function validateStuMobNumber(txtMobId) {
		 if (isNonEmpty(txtMobId)) {
			if (!(txtMobId.length == 10)){
				alert("Please enter 10 digits mobile number.");
				$("#studentMobile").val("");
				$("#studentMobile").focus();
				return false;
			 }
		}else if((txtMobId.length == 10)) {
			return true;
		}
	}
	$.subscribe('validateImageFormat',function(event, data) {
		
		var filename = $("input.fileName").val().toLowerCase();
		var admissionnumber = $("[name='userVo.admissionNumber']").val();
		var rollNumber = $("[name='studentVo.rollNumber']").val();
		var categoryId = $("#categoryId").val();
		bplStatus = $("input.bplStatusClass:checked").val();
		if (isNonEmpty(admissionnumber)) {
			if (admissionnumber.length < 1) {
				alert('Please provide at least one character for admission number.');
				event.originalEvent.options.submit = false;
			}
			if ($('p.word-taken').html() == 'Already taken!!!') {
				event.originalEvent.options.submit = false;
			}
		}else if (isNonEmpty(rollNumber)) {
			if ($('p.word-taken').html() == 'Already taken!!!') {
				event.originalEvent.options.submit = false;
			}
		}else if (isNonEmpty($("input.fileName").val())
				&& (filename.lastIndexOf(".jpg") == -1
						&& filename.lastIndexOf(".png") == -1 && filename
						.lastIndexOf(".jpeg") == -1)) {
			alert("File not acceped. Please upload your file in jpg or jpeg or  png");
			event.originalEvent.options.submit = false;
		}else if (isNonEmpty(categoryId)|| categoryId == 0 ) {
			event.originalEvent.options.submit = false;
		}else if(bplStatus == "Y")
			{
				bplNumber = $('#bplNumber').val();
				if(!isNonEmpty(bplNumber))
				{
					alert("please enter Below Poverty Line Number.");
					event.originalEvent.options.submit = false;
				}
			}else{
				event.originalEvent.options.submit = true;
			}
		
	});
	function viewPopupStudensCapture() {
		$.ajax({
			url : jQuery.url.getChatURL("/admin/ajaxCapturePhoto.do"),
			cache : false,
			success : function(html) {
				$("#studentsImgDiv").html(html);
			}
		});
	}
	$("label.transportMode").click(function() {
		var transportMode = $(this).find("input").val();
		if (isNonEmpty(transportMode) && 'T' == transportMode) {
			$('#inputboxhideText').show();
		} else {
			$('#inputboxhideText').hide();
		}
	});
	function FillBilling(offlineApplicationForm) {
		if (offlineApplicationForm.billingtoo.checked == true) {
			offlineApplicationForm.addressLine11.value = offlineApplicationForm.addressLine1.value;
			offlineApplicationForm.addressLine21.value = offlineApplicationForm.addressLine2.value;
			offlineApplicationForm.city1.value = offlineApplicationForm.city.value;
			offlineApplicationForm.state1.value = offlineApplicationForm.state.value;
			offlineApplicationForm.pincode1.value = offlineApplicationForm.pincode.value;
			offlineApplicationForm.temailAddress.value = offlineApplicationForm.emailAddress.value;
			offlineApplicationForm.tstumobileNumbers.value = offlineApplicationForm.stumobileNumbers.value;
			offlineApplicationForm.tstumobileNumber.value = offlineApplicationForm.stumobileNumber.value;
			
		} else {
			offlineApplicationForm.addressLine11.value = "";
			offlineApplicationForm.addressLine21.value = "";
			offlineApplicationForm.city1.value = "";
			offlineApplicationForm.state1.value = "";
			offlineApplicationForm.pincode1.value = "";
			offlineApplicationForm.temailAddress.value = "";
			offlineApplicationForm.tstumobileNumbers.value = "";
			offlineApplicationForm.tstumobileNumber.value = "";
				
		}

	}

	function displayPHCDiv(phcVal) {
		if (phcVal == 'Y'){
			$('#physicallyHandiCappedDivId').show();
		}else{
			$('#physicallyHandiCappedDivId').hide();
			$('#disabilityDes').val('');
		}
	}

	$("#personPhidDivId").change(function() {
	 
	 displayPHCDiv($('input#personPhid').val());
	 
	});   
	$('select[name="studyClassId"]').change(function() {
		var studyClassId = $(this).val();
		var categoryId = $("#categoryId").val();
		if (categoryId != 0) {
			var feeURL = jQuery.url.getChatURL("/common/ajaxCheckClassWiseCommittedFee.do?studyClassId="+ studyClassId+ "&categoryId="+ categoryId);
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
								$("#committedFee").value(0.0);
							}
						} else {
							$('#committedFeeDiv').hide();
							$("#committedFee").value(0.0);
						}
					} else {
						$('#committedFeeDiv').hide();
						$("#committedFee").value(0.0);
					}
				}
			});
		} /* else {
			alert("Please 
 category");
			$("#studyClassId").val('');
		} */
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
	
	function showAdmissionNumber(studyClassVal)
	{
		if(isNonEmpty(studyClassVal))
		{
			$('#studyClassHiddId').val(studyClassVal);
		}
		else
		{
			$('#studyClassHiddId').val(0);
		}
		
		$.ajax( {
			type : "POST",
			url : jQuery.url.getChatURL("/admin/ajaxAdmissionNumber.do"),
			cache : false,
			success : function(html) {
				$("#admissionDivId").html(html);
				
				var admissionNumber = '<s:property value="userVo.admissionNumber"/>';
				if(isNonEmpty(admissionNumber))
				{
					$(".admissionNumber").val(admissionNumber);
				}
			}
		});
		
		$("#rollNumber").unbind('#rollNumber').autoCheck("${pageContext.request.contextPath}/admin/ajaxCheckStudentRoleNumber.do?selectedId="+$('#studyClassHiddId').val(),
				{
					minChars : 1,
					min : "no"
				});
		
		 $.ajax( {
			type : "POST",
			url : jQuery.url.getChatURL("/admin/ajaxRoleNumber.do"),
			cache : false,
			success : function(html) {
				$("#rollNumberDivId").html(html);
				
				var rollNumber = '<s:property value="studentVo.rollNumber"/>';
				if(isNonEmpty(rollNumber))
				{
					$(".rollNumber").val(rollNumber);
				}
			}
		});
		 
		
	}
	
	var studyClassId = '<s:property value="studyClassId"/>';
	if(studyClassId > 0)
	{
		 showAdmissionNumber(studyClassId);
	}
	
</script>