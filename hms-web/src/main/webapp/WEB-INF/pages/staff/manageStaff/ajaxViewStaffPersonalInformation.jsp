<%@ include file="/common/taglibs.jsp"%>
<div id="stepStaffPersonalInfo">
	<div class="form-body">
		<jsp:include page="/common/messages.jsp"></jsp:include>
		<s:form action="ajaxEditStaffPersonalInfo" theme="simple"
			id="editStaffPersonalDetails" method="post" name="/staff"
			cssClass="form-horizontal">
			<s:hidden name="tempId"
				value="%{viewStaffPersonAccountDetails.staffId}" />
			<s:hidden name="tempString"
				value="%{viewStaffPersonAccountDetails.roleName}" />
			<s:hidden name="tempString3" value="personalInfo" />
			
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> Date of Joining : </label>
						<div class="col-md-5">
							<div data-date-format="mm/dd/yyyy"
								class="input-group input-medium date date-picker">
								<input type="text" id="staffJoiningDate" readonly=""
									class="form-control fdate" tabindex="11"
									name="viewStaffPersonAccountDetails.dateofJoining"
									value='<s:property value="viewStaffPersonAccountDetails.staffDateOfJoing"/>'>
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
					<div class="form-group ">
						<label class="control-label col-md-4"> Qualification : </label>
						<div class="col-md-5">
							<sj:textfield name="viewStaffPersonAccountDetails.qualification1"
								id="qualification1" tabindex="12"
								cssClass="alphabet form-control" maxlength="40"></sj:textfield>
							<span class="hintMessage">(EX:MCA,BTech,BSc..)</span>
						</div>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> Employment Type : </label>
						<div class="col-md-5">
							<s:select id="staffType" headerKey=""
								cssClass="form-control small"
								name="viewStaffPersonAccountDetails.staffType"
								onchange="javascript:changeStaffContractDates(this.value)"
								list="#{'P':'Permanent','C':'Temporary'}" tabindex="13" />
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group ">
						<label class="control-label col-md-4"> Experience (in
							Years) : </label>
						<div class="col-md-5">
							<sj:textfield name="viewStaffPersonAccountDetails.experience"
								id="experience" cssClass="numericDot form-control" maxlength="4"
								tabindex="14"></sj:textfield>
						</div>
					</div>
				</div>
			</div>
			<div style="display: none" id="contractDatesDiv">
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4"> Start Date : </label>
							<div class="col-md-5">
								<div data-date-format="mm/dd/yyyy"
									class="input-group input-medium date date-picker">
									<input type="text" id="staffContractStartDate" readonly=""
										class="form-control" onchange="verifyNonStaffContactDate();"
										tabindex="15"
										name="viewStaffPersonAccountDetails.contractStartDate"
										value='<s:property value="viewStaffPersonAccountDetails.contractStartDateStr"/>'>
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
						<div class="form-group ">
							<label class="control-label col-md-4"> End Date : </label>
							<div class="col-md-5">
								<div data-date-format="mm/dd/yyyy"
									class="input-group input-medium date date-picker">
									<input type="text" id="staffContractEndDate" readonly=""
										class="form-control" onchange="verifyNonStaffContactDate();"
										tabindex="16"
										value='<s:property value="viewStaffPersonAccountDetails.contractEndDateStr"/>'
										name="viewStaffPersonAccountDetails.contractEndDate">
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
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> Email Id : </label>
						<div class="col-md-5">
							<sj:textfield name="viewStaffPersonAccountDetails.email"
								id="emailAddress" tabindex="15" cssClass="email form-control"
								maxlength="50"></sj:textfield>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group ">
						<label class="control-label col-md-4"> Blood Group : </label>
						<div class="col-md-5">
							<s:select id="bGroup" label="" headerKey=""
								headerValue="- Select -" tabindex="16" cssClass="form-control"
								name="viewStaffPersonAccountDetails.bloodGroup"
								list="#{'A+':'A+ve','A-':'A-ve','A1+':'A1+ve','A1-':'A1-ve','A1B+':'A1B+ve','A1B-':'A1B-ve','A2B+':'A2B+ve','A2B-':'A2B-ve','A2+':'A2+ve','A2-':'A2-ve','B+':'B+ve','B-':'B-ve','AB+':'AB+ve','AB-':'AB-ve','O+':'O+ve','O-':'O-ve'}" />
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> Nationality : </label>
						<div class="col-md-5">
							<sj:textfield name="viewStaffPersonAccountDetails.nationality"
								id="nationality" cssClass="form-control" maxlength="50"
								tabindex="17"></sj:textfield>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group ">
						<label class="control-label col-md-4"> Select Religion : </label>
						<div class="col-md-5">
							<s:select list="tempList1" listKey="id" listValue="skillTypeName"
								cssClass="form-control"
								name="viewStaffPersonAccountDetails.religionId" headerKey=""
								headerValue="- Select -" tabindex="18">
							</s:select>
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
							<s:select list="tempList2" listKey="id" listValue="name"
								cssClass="form-control"
								name="viewStaffPersonAccountDetails.motherToungId" headerKey=""
								headerValue="- Select -" tabindex="19">
							</s:select>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group ">
						<label class="control-label col-md-4"> PAN Number : </label>
						<div class="col-md-5">
							<sj:textfield name="viewStaffPersonAccountDetails.panNumber"
								id="panNumber" tabindex="20" cssClass="form-control "
								maxlength="10"></sj:textfield>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> GPF Number : </label>
						<div class="col-md-5">
							<sj:textfield name="viewStaffPersonAccountDetails.gpfNumber"
								id="gpfNumber" label="" tabindex="21" cssClass="form-control "
								maxlength="20"></sj:textfield>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group ">
						<label class="control-label col-md-4"> Office Number : </label>
						<div class="col-md-5">
							<sj:textfield name="viewStaffPersonAccountDetails.officeNumber"
								id="officeNumber" tabindex="22" cssClass="numeric form-control "
								maxlength="14"></sj:textfield>
						</div>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> Designation : </label>
						<div class="col-md-5">
							<sj:textfield name="viewStaffPersonAccountDetails.designation"
								id="designation" tabindex="23" cssClass="form-control"
								maxlength="20"></sj:textfield>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group ">
						<label class="control-label col-md-4"> Phone Number : </label>
						<div class="col-md-5">
							<sj:textfield name="viewStaffPersonAccountDetails.phoneNumber"
								id="phoneNumber" cssClass="numeric form-control"
								onkeypress="return onlyNumbers(this);" maxlength="15"
								tabindex="26"></sj:textfield>
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
							<sj:textfield
								name="viewStaffPersonAccountDetails.fatherName"
								id="preferedHosp" cssClass="form-control" maxlength="50"
								tabindex="25"></sj:textfield>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group ">
						<label class="control-label col-md-4"> Father/Spouse Mobile number : </label>
						<div class="col-md-5">
							<sj:textfield name="viewStaffPersonAccountDetails.fatherContactNumber"
								id="familyDoc" label="" cssClass="numeric form-control" maxlength="10"
								tabindex="24"></sj:textfield>
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
							<sj:textfield
								name="viewStaffPersonAccountDetails.prefferedHospital"
								id="preferedHosp" cssClass="form-control" maxlength="40"
								tabindex="25"></sj:textfield>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group ">
						<label class="control-label col-md-4"> Family Doctor : </label>
						<div class="col-md-5">
							<sj:textfield name="viewStaffPersonAccountDetails.familyDoctor"
								id="familyDoc" label="" cssClass="form-control" maxlength="30"
								tabindex="24"></sj:textfield>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group ">
						<label class="control-label col-md-4"> <span
							class="required">*</span>Mobile Number :
						</label>
						<div class="col-md-5">
							<sj:textfield name="viewStaffPersonAccountDetails.mobileNumber"
								id="mobileNumber" cssClass="numeric required form-control"
								maxlength="10" tabindex="27"
								onchange="javascript:validateMobNumbers(this.value)" />
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> Aadhaar Card No : </label>
						<div class="col-md-5">
							<sj:textfield name="viewStaffPersonAccountDetails.aadharNumber"
								id="aadharCardNumber" cssClass="numeric form-control as-input"
								maxlength="12" onchange="javascript:validateAadhaarCardNumber(this.value)"></sj:textfield>
						</div>
					</div>
				</div>
			</div>
			<h5>RESIDENTIAL ADDRESS</h5>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group ">
						<label class="control-label col-md-4"> Street : </label>
						<div class="col-md-5">
							<sj:textfield name="viewStaffPersonAccountDetails.addressLine1"
								tabindex="28" id="addressLine" cssClass="form-control"
								maxlength="250"></sj:textfield>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> <span
							class="required">*</span>City :
						</label>
						<div class="col-md-5">
							<sj:textfield name="viewStaffPersonAccountDetails.city" id="city"
								cssClass="required form-control" maxlength="29" tabindex="31"></sj:textfield>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group ">
						<label class="control-label col-md-4"> <span
							class="required">*</span> State :
						</label>
						<div class="col-md-5">
							<s:select id="state" list="statesList" listKey="stateCode"
								listValue="stateName" headerKey="" headerValue="- Select -"
								name="viewStaffPersonAccountDetails.state"
								cssClass="form-control required small" tabindex="30" />
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> Pincode : </label>
						<div class="col-md-5">
							<sj:textfield name="viewStaffPersonAccountDetails.postalCode"
								id="pincode" tabindex="31" cssClass="numeric form-control"
								maxlength="6"></sj:textfield>
						</div>
					</div>
				</div>
			</div>
			<div class="clearfix">&nbsp;</div>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> Same As Residential
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
			<h5>CORRESPONDENCE ADDRESS</h5>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> Street : </label>
						<div class="col-md-5">
							<sj:textfield name="viewStaffPersonAccountDetails.taddressLine1"
								id="addressLine1" tabindex="1" cssClass="form-control " 
								maxlength="250"></sj:textfield>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group ">
						<label class="control-label col-md-4"> City : </label>
						<div class="col-md-5">
							<sj:textfield name="viewStaffPersonAccountDetails.tcity"
								id="city1" tabindex="2" cssClass="form-control " maxlength="40"></sj:textfield>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> State : </label>
						<div class="col-md-5">
							<s:select id="state1" list="statesList" listKey="stateCode"
								listValue="stateName" tabindex="3" headerKey=""
								headerValue="- Select -"
								name="viewStaffPersonAccountDetails.tstate"
								cssClass="form-control" />
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group ">
						<label class="control-label col-md-4"> Pincode : </label>
						<div class="col-md-5">
							<sj:textfield name="viewStaffPersonAccountDetails.tpostalCode"
								id="pincode1" label="Pincode" tabindex="4"
								cssClass="numeric form-control " maxlength="6"></sj:textfield>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6" id="resultsDiv1">
					<div class="form-group">
						<jsp:include
							page="/WEB-INF/pages/admin/admission/castSettings/ajaxStudentCastByState.jsp" />
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div id="resultsDiv2">
						<jsp:include
							page="/WEB-INF/pages/admin/admission/castSettings/ajaxGetSubCastListByCast.jsp" /></div>
				</div>
				<div class="col-md-6">
					<div class="form-group ">
						<label class="control-label col-md-4"> Bank Name : </label>
						<div class="col-md-5">
							<sj:textfield name="viewStaffPersonAccountDetails.bankName"
								id="bankName" tabindex="34" cssClass="form-control "
								maxlength="40"></sj:textfield>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> Bank Account Number
							: </label>
						<div class="col-md-5">
							<sj:textfield
								name="viewStaffPersonAccountDetails.bankAccountNumber"
								id="bankAccountNumber" tabindex="35"
								cssClass="numeric form-control " maxlength="35"></sj:textfield>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group ">
						<label class="control-label col-md-4"> Bank Branch Name :
						</label>
						<div class="col-md-5">
							<sj:textfield name="viewStaffPersonAccountDetails.bankBranchName"
								id="bankBranchName" tabindex="36" cssClass="form-control"
								maxlength="40"></sj:textfield>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> Salary : </label>
						<div class="col-md-5">
							<sj:textfield name="viewStaffPersonAccountDetails.salary"
								id="salary" tabindex="37" cssClass="numeric form-control"
								maxlength="10"></sj:textfield>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group ">
						<label class="control-label col-md-4"> Do you have ESI : </label>
						<div class="col-md-5">
							<div id="esiradiobuttonId">
								<div class="checkbox-list">
									<label class="checkbox-inline"> <input type="checkbox"
										name="esiName" id="esicheckboxId" tabindex="38">
									</label>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> Do you have PF : </label>
						<div class="col-md-5">
							<div id="pfradiobuttonId">
								<div class="checkbox-list">
									<label class="checkbox-inline"> <input type="checkbox"
										name="pfname" id="pfcheckboxId" tabindex="40">
									</label>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div id="esiDivId" style="display: none">
						<div class="row">
							<div class="col-md-12">
								<div class="form-group">
									<label class="control-label col-md-4"> <span
										class="required">*</span> ESI No :
									</label>
									<div class="col-md-5">
										<sj:textfield name="viewStaffPersonAccountDetails.esiNo"
											id="esiNo" tabindex="42" cssClass="form-control alphanumeric"
											maxlength="20"></sj:textfield>
									</div>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-12">
								<div class="form-group ">
									<label class="control-label col-md-4"> Date Of Join : </label>
									<div class="col-md-5">
										<div data-date-format="mm/dd/yyyy"
											class="input-group input-medium date date-picker">
											<input type="text" id="esiDateofJoin" readonly=""
												class="form-control" tabindex="43"
												name="viewStaffPersonAccountDetails.esiDateofJoin"
												value='<s:property value="viewStaffPersonAccountDetails.esiDateofJoinStr"/>'>
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
							<div class="col-md-12">
								<div class="form-group ">
									<label class="control-label col-md-4"> ESI Percentage :
									</label>
									<div class="col-md-5">
										<sj:textfield
											name="viewStaffPersonAccountDetails.esiPercentage"
											id="esiPercentage" tabindex="44"
											cssClass="form-control numericDot" maxlength="4"></sj:textfield>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div id="pfDivId" style="display: none">
						<div class="row">
							<div class="col-md-12">
								<div class="form-group">
									<label class="control-label col-md-4"> <span
										class="required">*</span> PF No :
									</label>
									<div class="col-md-5">
										<sj:textfield name="viewStaffPersonAccountDetails.pfNo"
											id="pfNo" tabindex="45"
											cssClass="pfnumber form-control alphanumeric" maxlength="20"></sj:textfield>
									</div>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-12">
								<div class="form-group ">
									<label class="control-label col-md-4"> Date Of Join : </label>
									<div class="col-md-5">
										<div data-date-format="mm/dd/yyyy"
											class="input-group input-medium date date-picker">
											<input type="text" id="pfDateofJoin" readonly=""
												value='<s:property value="viewStaffPersonAccountDetails.pfDateofJoinStr"/>'
												class="form-control" tabindex="46"
												name="viewStaffPersonAccountDetails.pfDateofJoin"> <span
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
							<div class="col-md-12">
								<div class="form-group ">
									<label class="control-label col-md-4"> PF Percentage :
									</label>
									<div class="col-md-5">
										<sj:textfield
											name="viewStaffPersonAccountDetails.pfPercentage"
											id="pfPercentage" tabindex="47"
											cssClass="form-control numericDot" maxlength="4"></sj:textfield>
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
						<label class="control-label col-md-4"> IFSC Code : </label>
						<div class="col-md-5">
							<sj:textfield name="viewStaffPersonAccountDetails.ifscCode"
								id="ifscCode" tabindex="48" cssClass="form-control "
								maxlength="20"></sj:textfield>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> Outside School Duty
							: </label>
						<div class="col-md-5">
							<s:radio list="#{'Y':'Yes','N':'No'}"
								name="viewStaffPersonAccountDetails.outSideSchoolDuty"
								id="outSideSchoolDuty" />
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> Staff Payment Mode  : </label>
						<div class="col-md-5">
							<s:select id="salaryPaymentMode" headerKey="" tabindex="23"
								headerValue="- Select -" cssClass="form-control"
								name="viewStaffPersonAccountDetails.salaryPaymentMode"
								list="#{'CASH':'CASH','CHEQUE':'CHEQUE','NEFT/RTGS':'NEFT/RTGS','DD':'DD'}" />
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> Staff Grade :
						</label>
						<div class="col-md-5">
							<sj:textfield name="viewStaffPersonAccountDetails.staffGrade" id="staffGrade" tabindex="13"
								cssClass="form-control " maxlength="20"></sj:textfield>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> Staff Location : </label>
						<div class="col-md-5">
								<sj:textfield name="viewStaffPersonAccountDetails.staffLocation" id="staffLocation" tabindex="13"
								cssClass="form-control " maxlength="20"></sj:textfield>
						</div>
					</div>
				</div>
			</div>
			<div class="form-actions fluid">
				<div class="col-md-offset-2 col-md-9">
					<s:if test='%{#session.previousYear == "N"}'>
						<sj:submit cssClass="btn blue" value="Save"
							targets="stepStaffPersonalInfo"
							formIds="editStaffPersonalDetails"
							onBeforeTopics="changeStaffPersonalInfoFormValidation"
							validate="true" indicator="true" />
						<s:url id="doCancelStaff" action="ajaxDoManageStaff"
							includeParams="all" namespace="/staff"></s:url>
						<sj:a href="%{doCancelStaff}" cssClass="btn default"
							indicator="indicator" targets="mainContentDiv" button="false">Cancel</sj:a>
					</s:if>
				</div>
			</div>
		</s:form>
	</div>
</div>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/common/jquery.alphanumeric.js">
</script>
<script type="text/javascript">
$(document).ready(function() {
	$('.numericDot').numeric( {allow : "."});
	var pf = '<s:property value="viewStaffPersonAccountDetails.pfNo"/>';
	if(isNonEmpty(pf)){
		$('input#pfcheckboxId').parent('span').addClass('checked');
		$('input#pfcheckboxId').attr("checked", "true");
		$('#pfDivId').show();
	} 
	var esi = '<s:property value="viewStaffPersonAccountDetails.esiNo"/>';
	if(isNonEmpty(esi)){
		$('input#esicheckboxId').parent('span').addClass('checked');
		$('input#esicheckboxId').attr("checked", "true");
		$('#esiDivId').show();
	} 
	FormComponents.init(); 
	 $("input:checkbox, input:radio:not('.toggle')").uniform();
		if ("C" == '<s:property value='viewStaffPersonAccountDetails.staffType'/>') {
			$('#contractDatesDiv').show();
		}
		//$('select#subCastName').attr("tabindex", 35);
		$('.numeric').numeric();
		$('.alphabet').alpha( {
			allow : "a-z,A-Z.?/~!@#)() "
		});
});

function getCastDetailsByState(selectBox) {
	var stateName = selectBox.value;
	var url = jQuery.url
			.getChatURL("/admin/ajaxGetStudentCastDetailsByState.do");
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
function changeStaffContractDates(contrateType) {
	if (contrateType == 'C') {
		$('#contractDatesDiv').show();
	} else {
		$('#contractDatesDiv').hide();
	}
}
$('input#pfcheckboxId').click(function(){
	if($(this).is(":checked")){
		$("#pfDivId").show();
		$('input#pfNo').addClass('required');
	}	
	else{
	$('input#pfNo').removeClass('required');
	    $("#pfDivId").hide();
		$('input#pfcheckboxId').val('');
		$('input#pfNo').val('');
		$('input#pfDateofJoin').val('');
		$('input#pfPercentage').val('');
		$('div#pfDivId').find('label.error').remove();
	}
});
$('input#esicheckboxId').click(function(){
	if($(this).is(":checked")){
	 $('#esiNo').addClass('required');
		$("#esiDivId").show();
	}
	else{
	$('#esiNo').removeClass("required");
        $("#esiDivId").hide();
		$('input#esicheckboxId').val('');
		$('input#esiNo').val('');
		$('input#esiDateofJoin').val('');
		$('input#esiPercentage').val('');
		$('div#esiDivId').find('label.error').remove();
	}
});

$("#mobileNumber").change(function(){ 
	var text = $(this).val();
	var moble=text.replace('+','');
	var number=moble.replace('-','');
     if (Math.floor(number) != number) {
      alert("Please enter numbers.");
      $("#mobileNumber").val('');
      return  false;
     }
});
function validateMobNumbers(txtMobId) {
	/* var mob = /^(\+91-|\+91|0)?\d{10}$/;
	if (mob.test($.trim(txtMobId)) == false) {
		alert("Please enter valid mobile number.");
		$("#mobileNumber").val('');
		$("#mobileNumber").focus();
		return false;
	}else  */
	if (isNonEmpty(txtMobId)) {
		if(!(txtMobId.length == 10)){
			alert("Please enter 10 digits mobile number.");
			$("#mobileNumber").val("");
			$("#mobileNumber").focus();
			return false;
		}
	}else if((txtMobId.length == 10)) {
		return true;
	}
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
function FillBilling(offlineApplicationForm){
	if (offlineApplicationForm.billingtoo.checked == true) {
		offlineApplicationForm.addressLine1.value =offlineApplicationForm.addressLine.value;
		offlineApplicationForm.city1.value = offlineApplicationForm.city.value;
		offlineApplicationForm.state1.value = offlineApplicationForm.state.value;
		offlineApplicationForm.pincode1.value = offlineApplicationForm.pincode.value;
	} else {
		offlineApplicationForm.addressLine1.value = "";
		offlineApplicationForm.city1.value = "";
		offlineApplicationForm.state1.value = "";
		offlineApplicationForm.pincode1.value = "";
	}

}
</script>