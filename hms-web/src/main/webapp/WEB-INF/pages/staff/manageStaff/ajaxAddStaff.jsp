<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/messages.jsp"%>
<div class="form-body">
	<div class="row form-group">
		<label class="col-md-2 control-label" style="width: 12.667%">
			Select Staff Type : </label>
		<div class="col-md-10">
			<div class="radio-list">
				<label class="radio-inline"> <input type="radio"
					checked="checked" value="teaching"
					onclick="changeQualification(this.value);" name="addGroupLeader">
					Teaching
				</label> <label class="radio-inline"> <input type="radio"
					value="nonTeaching" onclick="changeQualification(this.value);"
					name="addGroupLeader"> Non Teaching
				</label>
				<label class="radio-inline"> <input type="radio"
					value="management" onclick="changeQualification(this.value);"
					name="addGroupLeader"> Management
				</label>
			</div>
		</div>
	</div>
	<hr />
	<div id="teachingDiv" style="display: block;">
		<s:form action="ajaxDoAddNewStaff" theme="simple"
			id="addTeachingStaff" method="post" cssClass="form-horizontal"
			 enctype="multipart/form-data" namespace="/staff">
			<s:hidden name="customerImage" id="customerImage"></s:hidden>
			<s:hidden id="classNameIds" name="anyId" ></s:hidden>
			<p>
				<span class="label label-danger"> NOTE : </span>&nbsp; You can add
				"Teaching" staff through this process.
			</p>
			<h4 class="pageTitle bold form-section">PRIMARY INFORMATION</h4>
			<div class="row">
				<div id="teachingList">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4"> <span
								class="required">*</span>Role :
							</label>
							<div class="col-md-5">
								<s:select id="teachStaffList" list="teachingRoleMap"
									tabindex="1" onchange="ajaxShowInchargeList(this.value);"
									name="teachingRoleName" cssClass="required form-control" />
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> Employment Type :
						</label>
						<div class="col-md-5">
							<s:select id="staffType2" headerKey=""
								cssClass="required form-control" tabindex="2"
								 name="staffVo.staffType"
								list="#{'P':'Permanent','C':'Temporary'}"
								onchange="javascript:changeContractDates(this.value)" />
						</div>
					</div>
				</div>
			</div>
			<div class="grid_12" id="contractDatesDiv" style="display: none;">
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4"> Start Date : </label>
							<div class="col-md-5">
								<div data-date-format="mm/dd/yyyy"
									class="input-group input-medium date date-picker">
									<input type="text" id="contractStartDate" readonly=""
										class="form-control" onchange="verifyStaffContactDate();"
										tabindex="3" name="personVo.contractStartDate"> <span
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
							<label class="control-label col-md-4"> End Date : </label>
							<div class="col-md-5">
								<div data-date-format="mm/dd/yyyy"
									class="input-group input-medium date date-picker">
									<input type="text" id="staffContractEndDate" readonly=""
										class="form-control" onchange="verifyStaffContactDate();"
										tabindex="4" name="personVo.contractEndDate"> <span
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
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> <span
							class="required">*</span>First Name :
						</label>
						<div class="col-md-5">
							<sj:textfield name="personVo.firstName" id="staffName" tabindex="5"
								cssClass="required form-control" maxlength="60"></sj:textfield>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group ">
						<label class="control-label col-md-4"> Last Name : </label>
						<div class="col-md-5">
							<sj:textfield name="personVo.lastName" id="staffLName" tabindex="6"
								cssClass="form-control" maxlength="40"></sj:textfield>
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
						<div class="col-md-5">
							<div class="make-switch has-switch" data-id="M" data-value="F"
								style="width: 120px" data-off="warning" data-on="success"
								data-off-label="Female" data-on-label="Male">
								<input type="radio" class="toggle" checked="checked"
									id="gender1" tabindex="7"> <input type="hidden"
									name="personVo.gender" value="M">
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group ">
						<label class="control-label col-md-4"> Date of Birth : </label>
						<div class="col-md-5">
							<div data-date-format="mm/dd/yyyy" data-date-end-date="+0d"
								class="input-group input-medium date date-picker">
								<input type="text" id="staffDate" readonly=""
									class="form-control" onchange="verifyStaffDate();" tabindex="8"
									name="personVo.dateOfBirth"> <span
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
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> <span
							class="required">*</span>Mobile Number :
						</label>
						<div class="col-md-5">
						<sj:textfield name="personVo.mobileNumber" id="mobileNumber"
								tabindex="9" cssClass="required form-control numeric mobileNumberStr"
								maxlength="10" onchange="javascript:validateMobNumber(this.value)"></sj:textfield>
								<span class="help-block">This can be used as staff username to login</span>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group ">
						<label class="control-label col-md-4"> E-Mail : </label>
						<div class="col-md-5">
							<sj:textfield name="addressVo.email" id="emailAddress"
								tabindex="10" cssClass="email form-control" maxlength="50"></sj:textfield>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> Designation : </label>
						<div class="col-md-5">
							<sj:textfield name="personVo.designation" id="designation"
								tabindex="11" cssClass="form-control" maxlength="20"></sj:textfield>
						</div>
					</div>
				</div>
			<!-- <div class="col-md-6">
					<div class="form-group ">
						<label class="control-label col-md-4"> Staff Number : </label>
						<div class="col-md-5">
							<sj:textfield name="userVo.staffNumber" id="staffNumber"
								tabindex="12" cssClass="form-control staffNumberStr" maxlength="20"></sj:textfield>
						</div>
					</div>
				</div> -->			
			
				<div class="col-md-6">
					<div class="form-group ">
						<label class="control-label col-md-4"> Staff Unique Number
							: </label>
						<div class="col-md-5">
							<sj:textfield name="personVo.staffUniqueNumber"
								id="staffUniqueNumber" tabindex="13" cssClass="form-control staffUniqueStr" 
								maxlength="20"></sj:textfield>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<s:if test="%{customer.hostelModuleStatus}">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4"> Select Hostel
								Category : </label>
							<div class="col-md-5">
								<div class="grid_6">
									<s:select id="hostelCategoryId" list="hostelCategoriesList"
										cssClass="form-control " tabindex="14" listKey="id"
										listValue="categoryName" headerKey=""
										headerValue="- Select Category-" name="staffVo.hostelCategoryId" />
								</div>
							</div>
						</div>
					</div>
				</s:if>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> Summary : </label>
						<div class="col-md-5">
							<sj:textarea rows="3" cols="20" name="personVo.summary"
								maxCharsData="1000" tabindex="14"
								cssClass="form-control word_count"></sj:textarea>
							<span class="help-block">
								<div class="counter"></div>
							</span>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"></label>
						<div class="col-md-6">
							<img id="image" border="0" height="102px;" style="display: none" />
							<div class="">
								<a data-toggle="modal" href="#staffImageDiv"
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
			<h4 class="pageTitle bold form-section">EDUCATION AND EXPERIENCE
			</h4>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> Qualification : </label>
						<div class="col-md-5">
							<sj:textfield name="staffVo.qualification1" id="qualification1"
								tabindex="16" cssClass="alphabet form-control" maxlength="40"></sj:textfield>
							<span class="hintMessage">(EX:MCA,BTech,BSc..)</span>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group ">
						<label class="control-label col-md-4"> Date of Joining : </label>
						<div class="col-md-5">
							<div data-date-format="mm/dd/yyyy"
								class="input-group input-medium date date-picker">
								<input type="text" id="staffJoiningDate" readonly=""
									class="form-control" onchange="verifyStaffDate();"
									tabindex="17" name="personVo.dateOfJoining"> <span
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
			</div>

			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> Experience (in
							Years) : </label>
						<div class="col-md-5">
							<sj:textfield name="personVo.experience" id="experience"
								tabindex="18" cssClass="numericDot form-control" maxlength="4"></sj:textfield>
						</div>
					</div>
				</div>
			</div>
			<h4 class="pageTitle bold form-section">PERSONAL INFORMATION</h4>

			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> Nationality : </label>
						<div class="col-md-5">
							<sj:textfield name="personVo.nationality" id="nationality"
								tabindex="19" cssClass="form-control " maxlength="50"></sj:textfield>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group ">
						<label class="control-label col-md-4"> Select Religion : </label>
						<div class="col-md-5">
							<s:select list="tempList1" listKey="id" tabindex="20"
								listValue="skillTypeName" cssClass="form-control"
								name="personVo.religionId" headerKey="" headerValue="- Select -">
							</s:select>
						</div>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> Community : </label>
						<div class="col-md-5">
							<s:select id="castName" list="castSettingList" listKey="id"
								tabindex="21" listValue="castName" headerKey=""
								headerValue="- Select -" name="personVo.castId" theme="simple"
								cssClass="form-control"
								onchange="javascript:getSubCastDetailsByCast(this.value);" />
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div id="resultsDiv2">
						<jsp:include
							page="/WEB-INF/pages/admin/admission/castSettings/ajaxGetSubCastListByCast.jsp" />
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> Blood Group : </label>
						<div class="col-md-5">
							<s:select id="bGroup" headerKey="" tabindex="23"
								headerValue="- Select -" cssClass="form-control"
								name="personVo.bloodGroup"
								list="#{'A+':'A+ve','A-':'A-ve','A1+':'A1+ve','A1-':'A1-ve','A1B+':'A1B+ve','A1B-':'A1B-ve','A2B+':'A2B+ve','A2B-':'A2B-ve','A2+':'A2+ve','A2-':'A2-ve','B+':'B+ve','B-':'B-ve','AB+':'AB+ve','AB-':'AB-ve','O+':'O+ve','O-':'O-ve'}" />
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> <span
							class="required">*</span>Marital Status :
						</label>
						<div class="col-md-5">
							<div class="make-switch has-switch" data-id="M" data-value="UN"
								style="width: 190px" data-off="warning" data-on="success"
								data-off-label="Un-married" data-on-label="Married">
								<input type="radio" class="toggle" id="maritalStatus"
									tabindex="24"> <input type="hidden"
									name="personVo.maritalStatus" value="UN">
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> Father/Spouse Name
							: </label>
						<div class="col-md-5">
							<sj:textfield name="personVo.fatherName"
								id="fatherName" tabindex="25" cssClass="form-control "
								maxlength="50"></sj:textfield>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group ">
						<label class="control-label col-md-4"> Father/Spouse Mobile number : </label>
						<div class="col-md-5">
							<sj:textfield name="personVo.fatherContactNumber" id="fatherContactNumber"
								tabindex="26" cssClass="numeric form-control" maxlength="10"></sj:textfield>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> Preferred Hospital
							: </label>
						<div class="col-md-5">
							<sj:textfield name="personVo.prefferedHospital"
								id="prefferedHospital" tabindex="25" cssClass="form-control "
								maxlength="50"></sj:textfield>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group ">
						<label class="control-label col-md-4"> Family Doctor : </label>
						<div class="col-md-5">
							<sj:textfield name="personVo.familyDoctor" id="familyDoctor"
								tabindex="26" cssClass="form-control " maxlength="50"></sj:textfield>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> Select Mother
							Tongue : </label>
						<div class="col-md-5">
							<s:select list="tempList" listKey="id" tabindex="27"
								listValue="name" cssClass="form-control"
								name="personVo.motherToungId" headerKey=""
								headerValue="- Select -">
							</s:select>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group ">
						<label class="control-label col-md-4"> BioMetric Id : </label>
						<div class="col-md-5">
							<sj:textfield name="userVo.bioMetricId" id="bioMetricId"
								tabindex="28" cssClass="form-control numeric staffbiometricStr" maxlength="5"></sj:textfield>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> Out Side School
							Duty : </label>
						<div class="col-md-5">
							<div class="make-switch has-switch" data-id="Y" data-value="N"
								style="width: 190px" data-off="warning" data-on="success"
								data-off-label="No" data-on-label="Yes">
								<input type="radio" class="toggle" id="outSideSchoolDuty"
									tabindex="24"> <input type="hidden"
									name="staffVo.outSideSchoolDuty" value="N">
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> Aadhaar Card No : </label>
						<div class="col-md-5">
							<sj:textfield name="personVo.aadharNumber" id="aadharCardNumber"  onchange="javascript:validateAadhaarCardNumber(this.value)"
								cssClass="numeric form-control as-input" maxlength="12"></sj:textfield>
						</div>
					</div>
				</div>
			</div>
			<div>
				<div id="staffTypeDiv"></div>
			</div>
			
			<s:if
				test='%{studySubjectList != null && !studySubjectList.isEmpty()}'>
				<div class="form-group">
					<label class="control-label col-md-2"> Eligible Subjects :
					</label>
					<div class="col-md-10">
						<s:checkboxlist name="objectList" tabindex="29" theme="ems"
							cssClass="checkbox required form-control" list="studySubjectList"
							listKey="id" listValue="name" />
					</div>
				</div>
			</s:if>
			<s:else>
				<div class="alert alert-info">Currently there are no subjects
					available.Please add subjects in ACADEMICS section.</div>
			</s:else>
			<div class="form-actions fluid">
				<div class="col-md-offset-2 col-md-9">
					<sj:submit cssClass="btn blue" value="Next" targets="staffsContent" onBeforeTopics="additionalNonStaffInformation"
						validate="true" formIds="addTeachingStaff" />
					(Click on 'Next' button to add Staff address and salary details.)
				</div>
			</div>
		</s:form>
	</div>
	<div id="nonTeachingDiv" style="display: none;">
		<p>
			<span class="label label-danger"> NOTE : </span>&nbsp; You can add
			"Non Teaching" staff through this process.
		</p>
		<s:form action="ajaxDoAddNewStaff" theme="simple"
			enctype="multipart/form-data" id="addNonTeachingStaff" cssClass="form-horizontal" method="post">
			<h4 class="pageTitle bold form-section">PRIMARY INFORMATION</h4>
			<div class="row">
				<div id="nonTeachingList">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4"> <span
								class="required">*</span>Role :
							</label>
							<div class="col-md-5">
								<s:select id="nonTeachStaffList" list="nonTeachingRoleMap"
									tabindex="1" cssClass="required form-control" onchange="ajaxNonteachingStaff(this.value);"
									name="teachingRoleName" />
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> <span
							class="required">*</span>Employment Type :
						</label>
						<div class="col-md-5">
							<s:select id="staffType1" cssClass="required form-control"
								headerKey="" tabindex="2" headerValue="- Select -"
								name="staffVo.staffType" list="#{'P':'Permanent','C':'Temporary'}"
								onchange="javascript:changeNonstaffContractDates(this.value)" />
						</div>
					</div>
				</div>
			</div>
			<div id="contractDatesDiv1" style="display: none;">
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4"> Start Date : </label>
							<div class="col-md-5">
								<div data-date-format="mm/dd/yyyy"
									class="input-group input-medium date date-picker">
									<input type="text" id="nonStaffContractStartDate" readonly=""
										class="form-control" onchange="verifyNonStaffContactDate();"
										tabindex="3" name="personVo.contractStartDate"> <span
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
							<label class="control-label col-md-4"> End Date : </label>
							<div class="col-md-5">
								<div data-date-format="mm/dd/yyyy"
									class="input-group input-medium date date-picker">
									<input type="text" id="nonStaffContractEndDate" readonly=""
										class="form-control" onchange="verifyNonStaffContactDate();"
										tabindex="4" name="personVo.contractEndDate"> <span
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
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> <span
							class="required">*</span>First Name :
						</label>
						<div class="col-md-5">
							<sj:textfield name="personVo.firstName" id="staffName" tabindex="5"
								cssClass="required form-control" maxlength="60"></sj:textfield>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> Last Name : </label>
						<div class="col-md-5">
							<sj:textfield name="personVo.lastName" id="staffLName" tabindex="6"
								cssClass="form-control" maxlength="40"></sj:textfield>
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
						<div class="col-md-5">
							<div class="make-switch has-switch" data-id="M" data-value="F"
								style="width: 120px" data-off="warning" data-on="success"
								data-off-label="Female" data-on-label="Male">
								<input type="radio" class="toggle" checked="checked" id="gender"
									tabindex="7"> <input type="hidden" name="personVo.gender"
									value="M">
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group ">
						<label class="control-label col-md-4"> Date of Birth : </label>
						<div class="col-md-5">
							<div data-date-format="mm/dd/yyyy" data-date-end-date="+0d"
								class="input-group input-medium date date-picker">
								<input type="text" id="date0" readonly="" class="form-control"
									onchange="verifyNonStaffDate();" tabindex="8"
									name="personVo.dateOfBirth"> <span
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
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> <span
							class="required" id="nonStaffMob">*</span>Mobile Number :
						</label>
						<div class="col-md-5">
							<sj:textfield name="personVo.mobileNumber" id="mobileNumber"
								tabindex="9" cssClass="form-control numeric mobileNumberStr"
								maxlength="10" onchange="javascript:validateMobNumber(this.value)"></sj:textfield>
								<span class="help-block">This can be used as staff username to login</span>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group ">
						<label class="control-label col-md-4"> E-Mail : </label>
						<div class="col-md-5">
							<sj:textfield name="addressVo.email" id="emailAddress"
								tabindex="10" cssClass="email form-control" maxlength="50"></sj:textfield>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> Designation : </label>
						<div class="col-md-5">
							<sj:textfield name="personVo.designation" id="designation"
								tabindex="11" cssClass="form-control" maxlength="20"></sj:textfield>
						</div>
					</div>
				</div>
			<!-- <div class="form-group ">
						<label class="control-label col-md-4"> Staff Number : </label>
						<div class="col-md-5">
							<sj:textfield name="userVo.staffNumber" id="staffNumber"
								tabindex="12" cssClass="form-control staffNumberStr" maxlength="20"></sj:textfield>
						</div>
					</div> -->		
				<div class="col-md-6">
					<div class="form-group ">
						<label class="control-label col-md-4"> Staff Unique Number
							: </label>
						<div class="col-md-5">
							<sj:textfield name="personVo.staffUniqueNumber"
								id="staffUniqueNumber" tabindex="13" cssClass="form-control staffUniqueStr"
								maxlength="20"></sj:textfield>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<s:if test="%{customer.hostelModuleStatus}">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4"> Select Hostel
								Category : </label>
							<div class="col-md-5">
								<div class="grid_6">
									<s:select id="hostelCategoryId" list="hostelCategoriesList"
										cssClass="form-control " tabindex="14" listKey="id"
										listValue="categoryName" headerKey=""
										headerValue="- Select Category-" name="staffVo.hostelCategoryId" />
								</div>
							</div>
						</div>
					</div>
				</s:if>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> Summary : </label>
						<div class="col-md-5">
							<sj:textarea rows="3" cols="20" name="personVo.summary"
								maxCharsData="1000" tabindex="15"
								cssClass="form-control word_count"></sj:textarea>
							<span class="help-block">
								<div class="counter"></div>
							</span>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group ">
						<label class="control-label col-md-4"> Upload Image : </label>
						<div class="col-md-5">
							<s:file name="upload" cssClass="btn default" id="photoURL1"
								tabindex="16"></s:file>
						</div>
					</div>
				</div>
			</div>
			<h4 class="pageTitle bold form-section">EDUCATION AND EXPERIENCE
			</h4>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> Qualification : </label>
						<div class="col-md-5">
							<sj:textfield name="staffVo.qualification1" id="qualification1"
								tabindex="17" cssClass="alphabet form-control" maxlength="40"></sj:textfield>
							<span class="hintMessage">(EX:MCA,BTech,BSc..)</span>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group ">
						<label class="control-label col-md-4"> Date of Joining : </label>
						<div class="col-md-5">
							<div data-date-format="mm/dd/yyyy"
								class="input-group input-medium date date-picker">
								<input type="text" id="nonStaffJoiningDate" readonly=""
									class="form-control" onchange="verifyNonStaffDate();"
									tabindex="18" name="personVo.dateOfJoining"> <span
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
			</div>

			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> Experience (in
							Years) : </label>
						<div class="col-md-5">
							<sj:textfield name="personVo.experience" id="experience"
								tabindex="19" cssClass="numericDot form-control" maxlength="4"></sj:textfield>
						</div>
					</div>
				</div>
			</div>
			<h4 class="pageTitle bold form-section">PERSONAL INFORMATION</h4>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> Nationality : </label>
						<div class="col-md-5">
							<sj:textfield name="personVo.nationality" id="nationality"
								tabindex="20" cssClass="form-control " maxlength="50"></sj:textfield>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group ">
						<label class="control-label col-md-4"> Select Religion : </label>
						<div class="col-md-5">
							<s:select list="tempList1" listKey="id" tabindex="21"
								listValue="skillTypeName" cssClass="form-control"
								name="personVo.religionId" headerKey="" headerValue="- Select -">
							</s:select>
						</div>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> Community : </label>
						<div class="col-md-5">
							<s:select id="castNameSub" list="castSettingList" listKey="id"
								tabindex="22" listValue="castName" headerKey=""
								headerValue="- Select -" name="personVo.castId" theme="simple"
								cssClass="form-control"
								onchange="javascript:getSubCastDetailsByCast(this.value);" />
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div id="resultsDiv1">
						<jsp:include
							page="/WEB-INF/pages/admin/admission/castSettings/ajaxGetSubCastListByCast.jsp" />
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> Blood Group : </label>
						<div class="col-md-5">
							<s:select id="bGroup" headerKey="" tabindex="24"
								headerValue="- Select -" name="personVo.bloodGroup"
								cssClass="form-control"
								list="#{'A+':'A+ve','A-':'A-ve','A1+':'A1+ve','A1-':'A1-ve','A1B+':'A1B+ve','A1B-':'A1B-ve','A2B+':'A2B+ve','A2B-':'A2B-ve','A2+':'A2+ve','A2-':'A2-ve','B+':'B+ve','B-':'B-ve','AB+':'AB+ve','AB-':'AB-ve','O+':'O+ve','O-':'O-ve'}" />
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> <span
							class="required">*</span>Marital Status :
						</label>
						<div class="col-md-5">
							<div class="make-switch has-switch" data-id="M" data-value="UN"
								style="width: 190px" data-off="warning" data-on="success"
								data-off-label="Un-married" data-on-label="Married">
								<input type="radio" class="toggle" id="maritalStatus"
									tabindex="25"> <input type="hidden"
									name="personVo.maritalStatus" value="UN">
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> Father/Spouse Name
							: </label>
						<div class="col-md-5">
							<sj:textfield name="personVo.fatherName"
								id="fatherName" tabindex="25" cssClass="form-control "
								maxlength="50"></sj:textfield>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group ">
						<label class="control-label col-md-4"> Father/Spouse Mobile number : </label>
						<div class="col-md-5">
							<sj:textfield name="personVo.fatherContactNumber" id="fatherContactNumber"
								tabindex="26" cssClass="form-control " maxlength="10"></sj:textfield>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> Preferred Hospital
							: </label>
						<div class="col-md-5">
							<sj:textfield name="personVo.prefferedHospital"
								id="prefferedHospital" tabindex="26" cssClass="form-control "
								maxlength="50"></sj:textfield>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group ">
						<label class="control-label col-md-4"> Family Doctor : </label>
						<div class="col-md-5">
							<sj:textfield name="personVo.familyDoctor" id="familyDoctor"
								tabindex="27" cssClass="form-control " maxlength="50"></sj:textfield>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> Select Mother
							Tongue : </label>
						<div class="col-md-5">
							<s:select list="tempList" listKey="id" tabindex="28"
								listValue="name" cssClass="form-control"
								name="personVo.motherToungId" headerKey=""
								headerValue="- Select -">
							</s:select>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group ">
						<label class="control-label col-md-4"> BioMetric Id : </label>
						<div class="col-md-5">
							<sj:textfield name="userVo.bioMetricId" id="bioMetricId"
								tabindex="29" cssClass="form-control numeric staffbiometricStr" maxlength="5"></sj:textfield>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> Aadhaar Card No : </label>
						<div class="col-md-5">
							<sj:textfield name="personVo.aadharNumber" id="aadharCardNumber"  onchange="javascript:validateAadhaarCardNumber(this.value)"
								cssClass="numeric form-control as-input" maxlength="12"></sj:textfield>
						</div>
					</div>
				</div>
				<%-- <div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> School Mess : </label>
						<div class="col-md-7">
						<div class="make-switch has-switch" data-id="Y" data-value="N"
								style="width: 190px" data-off="warning" data-on="success"
								data-off-label="No" data-on-label="Yes">
								<input type="radio" class="toggle"  id="schoolMess1">
								<input type="hidden" name="staffVo.schoolMess" value="N">
							</div>
						</div>
					</div>
				</div>--%>
			</div>
			<div class="form-actions fluid">
				<div class="col-md-offset-2 col-md-9">
					<sj:submit cssClass="btn blue" value="Next" targets="staffsContent"
						validate="true" formIds="addNonTeachingStaff"
						onBeforeTopics="additionalNonStaffInformation" />
					(Click on 'Next' button to add Staff address and salary details.)
				</div>
			</div>
		</s:form>
	</div>
	<div id="managementDiv" style="display: none;">
		<p>
			<span class="label label-danger"> NOTE : </span>&nbsp; You can add
			"Management" staff through this process.
		</p>
		<s:form action="ajaxDoAddNewStaff" theme="simple"
			enctype="multipart/form-data" id="addManagementStaff" cssClass="form-horizontal" method="post">
			<h4 class="pageTitle bold form-section">PRIMARY INFORMATION</h4>
			<div class="row">
				<div id="ManagementList">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4"> <span
								class="required">*</span>Role :
							</label>
							<div class="col-md-5">
								<s:select id="ManagementStaffList" list="managementRoleMap"
									tabindex="1" cssClass="required form-control"
									name="teachingRoleName" />
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> <span
							class="required">*</span>Employment Type :
						</label>
						<div class="col-md-5">
							<s:select id="staffType1" cssClass="required form-control"
								headerKey="" tabindex="2" headerValue="- Select -"
								name="staffVo.staffType" list="#{'P':'Permanent','C':'Temporary'}"
								onchange="javascript:changeManagementStaffContractDates(this.value)" />
						</div>
					</div>
				</div>
			</div>
			<div id="contractDateDiv" style="display: none;">
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4"> Start Date : </label>
							<div class="col-md-5">
								<div data-date-format="mm/dd/yyyy"
									class="input-group input-medium date date-picker">
									<input type="text" id="managementStaffContractStartDate" readonly=""
										class="form-control" onchange="verifyManagementStaffContactDate();"
										tabindex="3" name="personVo.contractStartDate"> <span
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
							<label class="control-label col-md-4"> End Date : </label>
							<div class="col-md-5">
								<div data-date-format="mm/dd/yyyy"
									class="input-group input-medium date date-picker">
									<input type="text" id="managementStaffContractEndDate" readonly=""
										class="form-control" onchange="verifyManagementStaffContactDate();"
										tabindex="4" name="personVo.contractEndDate"> <span
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
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> <span
							class="required">*</span>First Name :
						</label>
						<div class="col-md-5">
							<sj:textfield name="personVo.firstName" id="staffName" tabindex="5"
								cssClass="required form-control" maxlength="60"></sj:textfield>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> Last Name : </label>
						<div class="col-md-5">
							<sj:textfield name="personVo.lastName" id="staffLName" tabindex="6"
								cssClass="form-control" maxlength="40"></sj:textfield>
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
						<div class="col-md-5">
							<div class="make-switch has-switch" data-id="M" data-value="F"
								style="width: 120px" data-off="warning" data-on="success"
								data-off-label="Female" data-on-label="Male">
								<input type="radio" class="toggle" checked="checked" id="gender"
									tabindex="7"> <input type="hidden" name="personVo.gender"
									value="M">
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group ">
						<label class="control-label col-md-4"> Date of Birth : </label>
						<div class="col-md-5">
							<div data-date-format="mm/dd/yyyy" data-date-end-date="+0d"
								class="input-group input-medium date date-picker">
								<input type="text" id="dateOfBirth" readonly="" class="form-control"
									onchange="verifyManagementStaffDate();" tabindex="8"
									name="personVo.dateOfBirth"> <span
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
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> <span
							class="required">*</span>Mobile Number :
						</label>
						<div class="col-md-5">
						<sj:textfield name="personVo.mobileNumber" id="mobileNumber"
								tabindex="9" cssClass="required form-control numeric  "
								maxlength="10" onchange="javascript:validateMobNumber(this.value)"></sj:textfield>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group ">
						<label class="control-label col-md-4"> E-Mail : </label>
						<div class="col-md-5">
							<sj:textfield name="addressVo.email" id="emailAddress"
								tabindex="10" cssClass="email form-control" maxlength="50"></sj:textfield>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> Designation : </label>
						<div class="col-md-5">
							<sj:textfield name="personVo.designation" id="designation"
								tabindex="11" cssClass="form-control" maxlength="20"></sj:textfield>
						</div>
					</div>
				</div>
				<!-- <div class="form-group ">
						<label class="control-label col-md-4"> Staff Number : </label>
						<div class="col-md-5">
							<sj:textfield name="userVo.staffNumber" id="staffNumber"
								tabindex="12" cssClass="staffNumberStr form-control" maxlength="20"></sj:textfield>
						</div>
					</div> -->	
				<div class="col-md-6">
					<div class="form-group ">
						<label class="control-label col-md-4"> Staff Unique Number
							: </label>
						<div class="col-md-5">
							<sj:textfield name="personVo.staffUniqueNumber"
								id="staffUniqueNumber" tabindex="13" cssClass="form-control staffUniqueStr"
								maxlength="20"></sj:textfield>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<s:if test="%{customer.hostelModuleStatus}">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4"> Select Hostel
								Category : </label>
							<div class="col-md-5">
								<div class="grid_6">
									<s:select id="hostelCategoryId" list="hostelCategoriesList"
										cssClass="form-control " tabindex="14" listKey="id"
										listValue="categoryName" headerKey=""
										headerValue="- Select Category-" name="staffVo.hostelCategoryId" />
								</div>
							</div>
						</div>
					</div>
				</s:if>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> Summary : </label>
						<div class="col-md-5">
							<sj:textarea rows="3" cols="20" name="personVo.summary"
								maxCharsData="1000" tabindex="15"
								cssClass="form-control word_count"></sj:textarea>
							<span class="help-block">
								<div class="counter"></div>
							</span>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group ">
						<label class="control-label col-md-4"> Upload Image : </label>
						<div class="col-md-5">
							<s:file name="upload" cssClass="btn default" id="photoURL1"
								tabindex="16"></s:file>
						</div>
					</div>
				</div>
			</div>
			<h4 class="pageTitle bold form-section">EDUCATION AND EXPERIENCE
			</h4>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> Qualification : </label>
						<div class="col-md-5">
							<sj:textfield name="staffVo.qualification1" id="qualification1"
								tabindex="17" cssClass="alphabet form-control" maxlength="40"></sj:textfield>
							<span class="hintMessage">(EX:MCA,BTech,BSc..)</span>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group ">
						<label class="control-label col-md-4"> Date of Joining : </label>
						<div class="col-md-5">
							<div data-date-format="mm/dd/yyyy"
								class="input-group input-medium date date-picker">
								<input type="text" id="managementStaffJoiningDate" readonly=""
									class="form-control" onchange="verifyManagementStaffDate();"
									tabindex="18" name="personVo.dateOfJoining"> <span
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
			</div>

			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> Experience (in
							Years) : </label>
						<div class="col-md-5">
							<sj:textfield name="personVo.experience" id="experience"
								tabindex="19" cssClass="numericDot form-control" maxlength="4"></sj:textfield>
						</div>
					</div>
				</div>
			</div>
			<h4 class="pageTitle bold form-section">PERSONAL INFORMATION</h4>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> Nationality : </label>
						<div class="col-md-5">
							<sj:textfield name="personVo.nationality" id="nationality"
								tabindex="20" cssClass="form-control " maxlength="50"></sj:textfield>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group ">
						<label class="control-label col-md-4"> Select Religion : </label>
						<div class="col-md-5">
							<s:select list="tempList1" listKey="id" tabindex="21"
								listValue="skillTypeName" cssClass="form-control"
								name="personVo.religionId" headerKey="" headerValue="- Select -">
							</s:select>
						</div>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> Community : </label>
						<div class="col-md-5">
							<s:select id="castNameSub" list="castSettingList" listKey="id"
								tabindex="22" listValue="castName" headerKey=""
								headerValue="- Select -" name="personVo.castId" theme="simple"
								cssClass="form-control"
								onchange="javascript:getSubCastDetailsByCast(this.value);" />
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div id="resultsDiv1">
						<jsp:include
							page="/WEB-INF/pages/admin/admission/castSettings/ajaxGetSubCastListByCast.jsp" />
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> Blood Group : </label>
						<div class="col-md-5">
							<s:select id="bGroup" headerKey="" tabindex="24"
								headerValue="- Select -" name="personVo.bloodGroup"
								cssClass="form-control"
								list="#{'A+':'A+ve','A-':'A-ve','A1+':'A1+ve','A1-':'A1-ve','A1B+':'A1B+ve','A1B-':'A1B-ve','A2B+':'A2B+ve','A2B-':'A2B-ve','A2+':'A2+ve','A2-':'A2-ve','B+':'B+ve','B-':'B-ve','AB+':'AB+ve','AB-':'AB-ve','O+':'O+ve','O-':'O-ve'}" />
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> <span
							class="required">*</span>Marital Status :
						</label>
						<div class="col-md-5">
							<div class="make-switch has-switch" data-id="M" data-value="UN"
								style="width: 190px" data-off="warning" data-on="success"
								data-off-label="Un-married" data-on-label="Married">
								<input type="radio" class="toggle" id="maritalStatus"
									tabindex="25"> <input type="hidden"
									name="personVo.maritalStatus" value="UN">
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> Father/Spouse Name
							: </label>
						<div class="col-md-5">
							<sj:textfield name="personVo.fatherName"
								id="fatherName" tabindex="25" cssClass="form-control "
								maxlength="50"></sj:textfield>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group ">
						<label class="control-label col-md-4"> Father/Spouse Mobile number : </label>
						<div class="col-md-5">
							<sj:textfield name="personVo.fatherContactNumber" id="fatherContactNumber"
								tabindex="26" cssClass="form-control " maxlength="50"></sj:textfield>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> Preferred Hospital
							: </label>
						<div class="col-md-5">
							<sj:textfield name="personVo.prefferedHospital"
								id="prefferedHospital" tabindex="26" cssClass="form-control "
								maxlength="50"></sj:textfield>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group ">
						<label class="control-label col-md-4"> Family Doctor : </label>
						<div class="col-md-5">
							<sj:textfield name="personVo.familyDoctor" id="familyDoctor"
								tabindex="27" cssClass="form-control " maxlength="50"></sj:textfield>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> Select Mother
							Tongue : </label>
						<div class="col-md-5">
							<s:select list="tempList" listKey="id" tabindex="28"
								listValue="name" cssClass="form-control"
								name="personVo.motherToungId" headerKey=""
								headerValue="- Select -">
							</s:select>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group ">
						<label class="control-label col-md-4"> BioMetric Id : </label>
						<div class="col-md-5">
							<sj:textfield name="userVo.bioMetricId" id="bioMetricId"
								tabindex="29" cssClass="form-control numeric staffbiometricStr" maxlength="5"></sj:textfield>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> Aadhaar Card No : </label>
						<div class="col-md-5">
							<sj:textfield name="personVo.aadharNumber" id="aadharCardNumber"  onchange="javascript:validateAadhaarCardNumber(this.value)"
								cssClass="numeric form-control as-input" maxlength="12"></sj:textfield>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> School Mess : </label>
						<div class="col-md-7">
						<div class="make-switch has-switch" data-id="Y" data-value="N"
								style="width: 190px" data-off="warning" data-on="success"
								data-off-label="No" data-on-label="Yes">
								<input type="radio" class="toggle"  id="schoolMess1">
								<input type="hidden" name="staffVo.schoolMess" value="N">
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="form-actions fluid">
				<div class="col-md-offset-2 col-md-9">
					<sj:submit cssClass="btn blue" value="Next" targets="staffsContent"
						validate="true" formIds="addManagementStaff"
						onBeforeTopics="additionalNonStaffInformation" />
					(Click on 'Next' button to add Staff address and salary details.)
				</div>
			</div>
		</s:form>
	</div>
</div>
<div id="staffImageDiv"></div>
<script type="text/javascript"src="${pageContext.request.contextPath}/scripts/subscription/textcounter.js"></script>
<script  type="text/javascript" src="${pageContext.request.contextPath}/plugins/bootstrap-switch/static/js/bootstrap-switch.min.js"></script>
<script  type="text/javascript">
	changePageTitle("Add Staff Details");
	$(document).ready(function() {
		var staffType = $('select#teachStaffList').val();
		ajaxShowInchargeList(staffType);
		
		$('.numeric');
		FormComponents.init();
		FormAdvanced.init();
		$("input:checkbox, input:radio:not('.toggle')")
				.uniform();
		$("input[name=empId]").autoCheck( "${pageContext.request.contextPath}/common/ajaxCheckEmailId.do",
		{
			minChars : 3,
			min : "no"
		});
		$('.numeric').numeric();
		$('.alphabet').alpha({
			allow : "a-z,A-Z.?/~!@#)() "
		});
		$('.numericDot').numeric({
			allow : "."
		});
		$('.staffNumberStr').alphanumeric();
		$('.staffUniqueStr').alphanumeric();
		
		$("input#staffUniqueNumber").autoCheck("${pageContext.request.contextPath}/common/ajaxCheckStaffUniqueNumber.do",
			{
				minChars : 1,
				min : "no"
			});
		
		$("input#bioMetricId").autoCheck("${pageContext.request.contextPath}/common/ajaxCheckBioMetricId.do",
		{
			minChars : 1,
			min : "no"
		});
		$("input#mobileNumber").autoCheck("${pageContext.request.contextPath}/staff/ajaxSecondaryUsernameAvailableOrNot.do",
		{
			minChars : 1,
			min : "no",
		});
		var checkedTopics = $("input#classNameIds").val();
		if(isNonEmpty(checkedTopics)){
			var arr = checkedTopics.split(',');
			for(i=0; i <= arr.length; i++){
				if(isNonEmpty(arr[i])){
			    $("input[value="+arr[i]+"]").attr("checked",true);
			    $("input[value="+arr[i]+"]").parent("span").addClass("checked");
				}
		     }
		}
	});

	$.subscribe('additionalNonStaffInformation', function(event, data) {
		var staffUniNum=$('input.staffUniqueStr').parents('div').next('div').find('p.word-taken').html();
		if(staffUniNum=='Already taken!!!'){
    	    event.originalEvent.options.submit=false;
    	    $('input.staffUniqueStr').val('');
         }
         var staffBioNum=$('input.staffbiometricStr').parents('div').next('div').find('p.word-taken').html();
 		if(staffBioNum=='Already taken!!!'){
     	    event.originalEvent.options.submit=false;
     	    $('input.staffbiometricStr').val('');
          }
 		 var mobileNumberStr=$('input.mobileNumberStr').parents('div').next('div').find('p.word-taken').html();
 		if(mobileNumberStr=='Already taken!!!'){
     	    event.originalEvent.options.submit=false;
     	    $('input.mobileNumberStr').val('');
          }
 		var subjectIds = $("input[name=objectList]:checked");
        var selectedSubjectIds = '';
        if (subjectIds.length > 0) {
        	selectedSubjectIds = '';
            for ( var i = 0; i < subjectIds.length; i++) {
            	selectedSubjectIds += subjectIds[i].value + ',';
            }
            selectedSubjectIds += '';
        }
        $("#classNameIds").val(selectedSubjectIds);
	});
	
	function changeQualification(staffType) {
		if (staffType == 'teaching') {
			$('#teachingDiv').show();
			$('#nonTeachingDiv').hide();
			$('#managementDiv').hide();
			$('.close').click();
		}else if (staffType == 'nonTeaching') {
			$('.close').click();
			$('#teachingDiv').hide();
			$('#managementDiv').hide();
			$('#nonTeachingDiv').show();
		}else {
			$('.close').click();
			$('#teachingDiv').hide();
			$('#nonTeachingDiv').hide();
			$('#managementDiv').show();
		}
	}

	function changeContractDates(contrateType) {
		if (contrateType == 'C') {
			$('#contractDatesDiv').show();

		} else {
			$('#contractDatesDiv').hide();

		}
	}
	function changeNonstaffContractDates(contrateType) {
		if (contrateType == 'C') {
			$('#contractDatesDiv1').show();

		} else {
			$('#contractDatesDiv1').hide();

		}
	}
	function changeManagementStaffContractDates(contrateType) {
		if (contrateType == 'C') {
			$('#contractDateDiv').show();

		} else {
			$('#contractDateDiv').hide();

		}
	}
	function ajaxShowInchargeList(staffType) {
		var pars = "staffType=" + staffType;
		var url = jQuery.url.getChatURL("/admin/ajaxDoGetSupervisorList.do");
		$.ajax({
			url : url,
			cache : false,
			data : pars,
			success : function(html) {
				if ("ROLE_PRINCIPAL" == staffType) {
					$("#staffTypeDiv").html("");
				} else {
					$("#staffTypeDiv").html(html);
				}
			}
		});
	}

	function showInchargeList(staffType) {
		if (staffType == 'ROLE_TEACHER') {
			$('#staffTypeDiv').show();
			//$('#nonTeachingDiv').hide();
		} else {
			$('#staffTypeDiv').hide();
			//$('#nonTeachingDiv').show();
		}
	}
	function verifyStaffDate() {
		var date0 = $('#staffDate').val();
		var staffJoiningDate = $('#staffJoiningDate').val();
		if (isNonEmpty(staffJoiningDate) && isNonEmpty(date0)) {
			date0 = new Date(date0);
			staffJoiningDate = new Date(staffJoiningDate);
			var birthYear = new Date(date0.setYear(date0.getFullYear() + 2));
			//alert("staffJoiningDate="+staffJoiningDate+"_birthYear="+birthYear);
			if (staffJoiningDate < birthYear) {
				$('#staffJoiningDate').val('');
				alert("Date of joining should be after 2 years of birth date.");
			}
		}
	}
	function verifyNonStaffDate() {
		var date0 = $('#date0').val();
		var nonStaffJoiningDate = $('#nonStaffJoiningDate').val();
		if (isNonEmpty(nonStaffJoiningDate) && isNonEmpty(date0)) {
			date0 = new Date(date0);
			nonStaffJoiningDate = new Date(nonStaffJoiningDate);
			var birthYear = new Date(date0.setYear(date0.getFullYear() + 2));
			if (nonStaffJoiningDate < birthYear) {
				$('#nonStaffJoiningDate').val('');
				alert("Date of joining should be after 2 years of birth date.");
			}
		}
	}
	function verifyManagementStaffDate(){
		var date0 = $('#dateOfBirth').val();
		var managementStaffJoiningDate = $('#managementStaffJoiningDate').val();
		if (isNonEmpty(managementStaffJoiningDate) && isNonEmpty(date0)) {
			date0 = new Date(date0);
			managementStaffJoiningDate = new Date(managementStaffJoiningDate);
			var birthYear = new Date(date0.setYear(date0.getFullYear() + 2));
			if (managementStaffJoiningDate < birthYear) {
				$('#managementStaffJoiningDate').val('');
				alert("Date of joining should be after 2 years of birth date.");
			}
		}
	}

	function verifyStaffContactDate() {
		var contractStartDate = $('#contractStartDate').val();
		var staffContractEndDate = $('#staffContractEndDate').val();
		if (isNonEmpty(staffContractEndDate) && isNonEmpty(contractStartDate)) {
			contractStartDate = new Date(contractStartDate);
			staffContractEndDate = new Date(staffContractEndDate);
			if (staffContractEndDate < contractStartDate) {
				$('#staffContractEndDate').val('');
				alert("End Date should be more than start date.");
			}
		}
	}

	function verifyNonStaffContactDate() {
		var nonStaffContractStartDate = $('#nonStaffContractStartDate').val();
		var nonStaffContractEndDate = $('#nonStaffContractEndDate').val();
		if (isNonEmpty(nonStaffContractEndDate)
				&& isNonEmpty(nonStaffContractStartDate)) {
			nonStaffContractStartDate = new Date(nonStaffContractStartDate);
			nonStaffContractEndDate = new Date(nonStaffContractEndDate);
			if (nonStaffContractEndDate < nonStaffContractStartDate) {
				$('#nonStaffContractEndDate').val('');
				alert("End Date should be more than start date.");
			}
		}
	}
	function verifyManagementStaffContactDate() {
		var managementStaffContractStartDate = $('#managementStaffContractStartDate').val();
		var managementStaffContractEndDate = $('#managementStaffContractEndDate').val();
		//aler(managementStaffContractStartDate);
		//alert(managementStaffContractEndDate);
		if (isNonEmpty(managementStaffContractEndDate)
				&& isNonEmpty(managementStaffContractStartDate)) {
			managementStaffContractStartDate = new Date(managementStaffContractStartDate);
			managementStaffContractEndDate = new Date(managementStaffContractEndDate);
			if (managementStaffContractEndDate < managementStaffContractStartDate) {
				$('#managementStaffContractEndDate').val('');
				alert("End Date should be more than start date.");
			}
		}
	}

	function getSubCastDetailsByCast(selectBox) {
		var castId;
		if ($("select#castNameSub").val() > 0) {
			castId = $("select#castNameSub").val();
		} else {
			castId = $("select#castName").val();
		}
		var url = jQuery.url.getChatURL("/admin/ajaxGetStudentSubCastDetailsByCast.do");
		if (castId.length == 0) {
			alert("!Oops select Cast.");
		} else {
			$("#resultsDiv2").html( '<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
			var pars = "person.castId=" + castId;
			$.ajax({
						url : url,
						cache : false,
						data : pars,
						success : function(html) {
							$("#resultsDiv2").html(html);
							document.getElementById('resultsDiv2').style.display = "block";
							$("#resultsDiv1").html(html);
							document.getElementById('resultsDiv1').style.display = "block";
							$('select#subCastName').attr("tabindex", 8);
						}
					});
		}
	}
	
	function viewPopupStudensCapture() {
		$.ajax({
			url : jQuery.url.getChatURL("/admin/ajaxCapturePhoto.do"),
			cache : false,
			success : function(html) {
				$("#staffImageDiv").html(html);
			}
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
	function validateMobNumber(txtMobId) {
		if (isNonEmpty(txtMobId)) {
			if (!(txtMobId.length == 10)) {
				alert("Please enter 10 digits mobile number.");
				$("input#mobileNumber").val("");
				$("input#mobileNumber").focus();
				return false;
			}
		}else if((txtMobId.length == 10)) {
			return true;
		}
	}
	function ajaxNonteachingStaff(roleName){
		if("ROLE_AYAH"==roleName || "ROLE_HELPER"==roleName || "ROLE_DRIVER"==roleName || "ROLE_PEON"==roleName 
		|| "ROLE_CONDUCTOR"==roleName|| "ROLE_LABASST"==roleName || "ROLE_MANAGEMENTTRAINEE"==roleName || "ROLE_WATCHMAN"==roleName || "ROLE_TYPIST"==roleName 
		|| "ROLE_SWEEPER"==roleName || "ROLE_OTHER"==roleName || "ROLE_PUBLICRELATIONOFFICER"==roleName || "ROLE_MESS_MANAGER"==roleName 
		|| "ROLE_SALES_HEAD"==roleName || "ROLE_SALES_EXECUTIVE"==roleName || "ROLE_COORDINATOR"==roleName || "ROLE_BDM"==roleName || "ROLE_STAFF_NURSE"==roleName ){
			$('input.mobileNumberStr').removeClass("required");  
			$('#nonStaffMob').hide();
		}else{
			$('input.mobileNumberStr').addClass("required");
			$('#nonStaffMob').show();
		}
		
	}
</script>
 
