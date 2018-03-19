<%@ include file="/common/taglibs.jsp"%>
<div class="form-body">
	<s:form action="ajaxEditStudentPersonlInformation" theme="simple"
		cssClass="form-horizontal" id="editStudentPersonalInfo" method="post"
		enctype="multipart/form-data" namespace="/student">
		<div id="studentPersonalDetails">
			<jsp:include page="/common/messages.jsp"></jsp:include>
		</div>
		<s:hidden name="studentVo.id" />
		<span id="studentDateOfBirth" class="<s:property value='selectedId'/>"></span>
		<span class="genderValue" id="<s:property value='studentVo.account.personVo.gender'/>"></span>
		<span class="transportValue" id="<s:property value='studentVo.transportMode'/>"></span>
		<span class="studentAccountId" id="<s:property value='studentVo.account.id'/>"></span>
		<%-- <s:hidden name="anyTitle" id="newAdmissionNumber" value="%{studentVo.account.admissionNumber}" /> --%>
		<s:hidden name="title" id="parentOldEmailId" value="%{studentVo.account.personVo.parentEmail}" />
		
		<s:hidden name="studentVo.account.id" />
		<s:hidden name="studentVo.studyClassVo.id" id="studyClassVoId" />
		<s:hidden name="tempString3" value="personalInfo" />
		
		
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-5"> Father Name : </label>
					<div class="col-md-7">
						<sj:textfield name="studentVo.account.personVo.fatherName"
							cssClass="form-control input-medium as-input" id="fatherName"></sj:textfield>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-5"> Occupation : </label>
					<div class="col-md-7">
						<sj:textfield name="studentVo.account.personVo.occupation"
							cssClass="form-control input-medium as-input" id="occupation"
							maxlength="65">
						</sj:textfield>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-5"> Mother Name : </label>
					<div class="col-md-7">
						<sj:textfield name="studentVo.account.personVo.motherName"
							id="motherName" cssClass="form-control input-medium as-input"></sj:textfield>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-5"> Mother Occupation :
					</label>
					<div class="col-md-7">
						<sj:textfield name="studentVo.account.personVo.motherOccupation"
							id="motherOccupation" maxlength="65"
							cssClass="form-control input-medium as-input">
						</sj:textfield>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-5"> Annual Income : </label>
					<div class="col-md-7">
						<sj:textfield name="studentVo.account.personVo.annualIncome"
							id="annualIncome" onkeypress="return onlyNumbers(event);"
							cssClass="numeric form-control input-medium as-input"></sj:textfield>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-5"> Community : </label>
					<div class="col-md-7">
						<s:select id="castName" list="castSettingList"
							cssClass="form-control input-medium" listKey="id"
							listValue="castName" headerKey="" headerValue="- Select -"
							name="studentVo.account.personVo.castId" theme="simple"
							onchange="javascript:getSubCastDetailsByCast(this);" />
					</div>
				</div>
			</div>
			<div id="resultsDiv2" class="col-md-6">
				<jsp:include
					page="/WEB-INF/pages/admin/student/ajaxStudentSubCastListByCast.jsp" />
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-5"> Blood Group : </label>
					<div class="col-md-7">
						<s:select id="bGroup" cssClass="form-control input-medium"
							list="#{'A+':'A+ve','A-':'A-ve','A1+':'A1+ve','A1-':'A1-ve','A1B+':'A1B+ve','A1B-':'A1B-ve','A2B+':'A2B+ve','A2B-':'A2B-ve','A2+':'A2+ve','A2-':'A2-ve','B+':'B+ve','B-':'B-ve','AB+':'AB+ve','AB-':'AB-ve','O+':'O+ve','O-':'O-ve'}"
							headerKey="" headerValue="- Select -"
							name="studentVo.account.personVo.bloodGroup" />
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-5"> Preferred Hospital :
					</label>
					<div class="col-md-7">
						<sj:textfield name="studentVo.account.personVo.prefferedHospital"
							id="preferedHosp" cssClass="form-control input-medium as-input"
							maxlength="40"></sj:textfield>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-5"> Family Doctor : </label>
					<div class="col-md-7">
						<sj:textfield name="studentVo.account.personVo.familyDoctor"
							id="familyDoc" cssClass="form-control input-medium as-input"
							maxlength="30"></sj:textfield>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-5"> Nationality : </label>
					<div class="col-md-7">
						<sj:textfield name="studentVo.account.personVo.nationality"
							id="nationality" cssClass="form-control input-medium as-input"
							maxlength="50"></sj:textfield>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-5"> Select Religion : </label>
					<div class="col-md-7">
						<s:select list="tempList1" listKey="id" listValue="skillTypeName"
							cssClass="form-control input-medium"
							name="studentVo.account.personVo.religionId" headerKey=""
							headerValue="- Select -">
						</s:select>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-5"> Select Mother Tongue
						: </label>
					<div class="col-md-7">
						<s:select list="tempList2" listKey="id" listValue="name"
							cssClass="form-control input-medium"
							name="studentVo.account.personVo.motherToungId" headerKey=""
							headerValue="- Select -">
						</s:select>
					</div>
				</div>
			</div>
		</div>
		
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-5"> Father Aadhaar Card No : </label>
					<div class="col-md-7">
						<sj:textfield name="studentVo.account.personVo.fatherAadharNumber"
							id="fatherAadharNumber" cssClass="form-control input-medium as-input" maxlength="14" minlength="12"></sj:textfield>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-5"> Mother Aadhaar Card No :
					</label>
					<div class="col-md-7">
						<sj:textfield name="studentVo.account.personVo.motherAadharNumber"
							id="motherAadharNumber" maxlength="14" minlength="12"
							cssClass="form-control input-medium as-input">
						</sj:textfield>
					</div>
				</div>
			</div>
		</div>
		
		
		
		<div class="row">
		
		<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-5">Address For Communication : </label>
					<div class="col-md-7">
						<s:radio list="#{'R':'Residential','C':'Correspondence'}"
							name="studentVo.account.personVo.addressType" id="residence" />
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-5"> PTA(Parent Teacher Association):</label>
					<div class="col-md-7">
						<s:radio list="#{'Y':'Yes','N':'No'}"
							name="studentVo.ptaStatus" id="residence" />
					</div>
				</div>
			</div>
			<%-- <div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4">
						</label>
						<div class="col-md-7">
						<s:radio list="#{'Y':'Yes','N':'No'}"
							name="studentVo.account.personVo.addressType" id="residence" />
					</div>
						<div class="col-md-7">
							<div class="make-switch has-switch" data-id="N" data-value="Y"
								style="width: 200px" data-off="warning" data-on="success"
								data-off-label="Yes" data-on-label="No">
								<s:if test='%{studentVo.ptaStatus =="N"}'>
									<input type="radio" class="toggle" checked="checked"
										id="PTAStatusId">
									<input type="hidden" name="studentVo.ptaStatus"
										value='<s:property value="studentVo.ptaStatus"/>'>
								</s:if>
								<s:elseif test='%{studentVo.ptaStatus =="Y"}'>
									<input type="radio" class="toggle" id="PTAStatusId">
									<input type="hidden" name="studentVo.ptaStatus"
										value='<s:property value="studentVo.ptaStatus"/>'>
								</s:elseif>
								<s:else>
									<input type="radio" class="toggle" checked="checked"
										id="PTAStatusId">
									<input type="hidden" name="studentVo.ptaStatus" value='N'>
								</s:else>
							</div>
						</div>
					</div>
				</div> --%>
			<%-- <div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-5">Address For Communication : </label>
					<div class="col-md-7">
						<div class="make-switch has-switch" data-id="R" data-value="C"
							style="width: 237px" data-off="warning" data-on="success"
							data-off-label="Correspondence" data-on-label="Residential">
							<s:if test='%{studentVo.account.personVo.addressType =="R"}'>
								<input type="radio" class="toggle" checked="checked"
									id="residence">
								<input type="hidden" name="studentVo.account.personVo.addressType"
									value='<s:property value="studentVo.account.personVo.addressType"/>'>
							</s:if>
							<s:elseif test='%{studentVo.account.personVo.addressType =="C"}'>
								<input type="radio" class="toggle" id="residence">
								<input type="hidden" name="studentVo.account.personVo.addressType"
									value='<s:property value="studentVo.account.personVo.addressType"/>'>
							</s:elseif>
							<s:else>
								<input type="radio" class="toggle" checked="checked"
									id="residence">
								<input type="hidden" name="studentVo.account.personVo.addressType" value='R'>
							</s:else>
						</div>
					</div>
				</div>
			</div> --%>
		</div>
		<h5 class="control-label col-md-3">RESIDENTIAL ADDRESS</h5>
		<div class="clearfix">&nbsp;</div>
		<div class="clearfix">&nbsp;</div>
		<s:if test='%{!(user.isSchoolTeacher=="Y"  && #session.parentMobileNoVisibleToTeacher == "N" )}'>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-5">  Primary Mobile Number :
					</label>
					<div class="col-md-7">
						<sj:textfield name="studentVo.account.personVo.mobileNumber"
							id="mobileNumber"
							cssClass="numeric form-control input-medium as-input" onchange="javascript:validateMobNumbers(this.value)"
							maxlength="10" >
						</sj:textfield>
							<span class="help-block">(This is considered as Parent Login Id)</span>
					</div>
				</div>
			</div>
			<div class="col-md-6">
			<div class="form-group">
				<label class="control-label col-md-5">Secondary Mobile Number :
				</label>
				<div class="col-md-7">
					<sj:textfield name="studentVo.account.personVo.secondaryMobileNumber" id="secondmobileNumber"
						cssClass="numeric form-control input-medium as-input" onchange="javascript:validateMobNumbers(this.value)"
						maxlength="10" >
					</sj:textfield>
				</div>
			</div> 
		</div>
		</div>
		</s:if>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-5"> Parent E-Mail : </label>
					<div class="col-md-7">
						<sj:textfield name="studentVo.account.personVo.parentEmail"
							id="email" label="Parent E-Mail " maxlength="60"
							cssClass="form-control input-medium as-input email">
						</sj:textfield>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-5"> Address Line1 : </label>
					<div class="col-md-7">
						<sj:textfield name="studentVo.account.primaryAddressVo.addressLine1"
							cssClass="form-control input-medium as-input" id="addressLine1"
							maxlength="200"></sj:textfield>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-5"> Address Line2 : </label>
					<div class="col-md-7">
						<sj:textfield name="studentVo.account.primaryAddressVo.addressLine2"
							cssClass="form-control input-medium as-input" id="addressLine2"
							maxlength="200"></sj:textfield>
					</div>
				</div>
			</div>
			
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-5"> City : </label>
					<div class="col-md-7">
						<sj:textfield name="studentVo.account.primaryAddressVo.city" id="city"
							cssClass="form-control input-medium as-input" maxlength="20"></sj:textfield>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-5"> State : </label>
					<div class="col-md-7">
						<s:select id="state" list="statesList"
							cssClass="form-control input-medium" listKey="stateCode"
							listValue="stateName" headerKey="" headerValue="- Select -"
							name="studentVo.account.primaryAddressVo.state" tabindex="33"
							/>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-5"> Pincode : </label>
					<div class="col-md-7">
						<sj:textfield name="studentVo.account.primaryAddressVo.postalCode"
							id="pincode" tabindex="34"
							cssClass="numeric form-control input-medium as-input"
							maxlength="6"></sj:textfield>
					</div>
				</div>
			</div>
			<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-5"> House Type : </label>
						<div class="col-md-7">
							<s:select id="houseTypeId" list="houseTypeList"
							cssClass="form-control input-medium" listKey="id"
							listValue="type" headerKey="" headerValue="- Select -"
							name="studentVo.account.primaryAddressVo.houseTypeId" tabindex="35"/>
						</div>
					</div>
				</div>
			
		</div>
		
		<div class="clearfix">&nbsp;</div>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-5">Same As Residential
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
		<h5 class="control-label col-md-3">CORRESPONDENCE ADDRESS</h5>
		<div class="clearfix">&nbsp;</div>
		<div class="clearfix">&nbsp;</div>
		<s:if test='%{!(user.isSchoolTeacher=="Y"  && #session.parentMobileNoVisibleToTeacher == "N" )}'>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-5"> Primary Mobile Number :
					</label>
					<div class="col-md-7">
						<sj:textfield name="studentVo.account.personVo.anotherMobileNumber" id="tstumobileNumber"
							cssClass="numeric form-control input-medium as-input" onchange="javascript:validateMobNumbers(this.value)"
							maxlength="10" >
						</sj:textfield>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-5">Secondary Mobile Number :
					</label>
					<div class="col-md-7">
						<sj:textfield name="studentVo.account.personVo.anotherSecondaryMobileNumber" id="tstumobileNumbers"
							cssClass="numeric form-control input-medium as-input" onchange="javascript:validateMobNumbers(this.value)"
							maxlength="10"/>
					</div>
				</div>
			</div>
		</div>
		</s:if>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-5">Contact E-Mail : </label>
					<div class="col-md-7">
						<sj:textfield name="studentVo.account.personVo.anotherParentEmail" id="temailAddress"
							cssClass="form-control input-medium as-input" maxlength="50"></sj:textfield>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-5"> Address Line1 : </label>
					<div class="col-md-7">
						<sj:textfield name="studentVo.account.tempararyAddressVo.addressLine1"
							id="addressLine11" cssClass="form-control input-medium as-input"
							maxlength="255"></sj:textfield>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-5"> Address Line2 : </label>
					<div class="col-md-7">
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
					<label class="control-label col-md-5"> City : </label>
					<div class="col-md-7">
						<sj:textfield name="studentVo.account.tempararyAddressVo.city"
							id="city1" cssClass="form-control input-medium as-input"
							maxlength="22"></sj:textfield>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-5"> State : </label>
					<div class="col-md-7">
						<s:select id="state1" list="statesList" label="State"
							cssClass="form-control input-medium" listKey="stateCode"
							listValue="stateName" headerKey="" headerValue="- Select -"
							name="studentVo.account.tempararyAddressVo.state" />
					</div>
				</div>
			</div>
		</div>
		
		<div class="row">
			
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-5"> Pincode : </label>
					<div class="col-md-7">
						<sj:textfield name="studentVo.account.tempararyAddressVo.postalCode"
							id="pincode1"
							cssClass="numeric form-control input-medium as-input"
							maxlength="6"></sj:textfield>
					</div>
				</div>
			</div>
		</div>
		
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-5"> Personal
						Identification1 : </label>
					<div class="col-md-7">
						<sj:textfield name="studentVo.account.personVo.identification1"
							id="identification1Id"
							cssClass="form-control input-medium as-input" maxlength="50"></sj:textfield>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-5"> Personal
						Identification2 : </label>
					<div class="col-md-7">
						<sj:textfield name="studentVo.account.personVo.identification2"
							id="identification2Id"
							cssClass="form-control input-medium as-input" maxlength="50"></sj:textfield>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-5"> Ration Card Number :
					</label>
					<div class="col-md-7">
						<sj:textfield name="studentVo.account.personVo.rationCardNumber"
							id="rationCardNumber"
							cssClass="form-control input-medium as-input" maxlength="50"></sj:textfield>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-5"> Community Number : </label>
					<div class="col-md-7">
						<sj:textfield name="studentVo.account.personVo.communityNumber"
							id="communityNumber"
							cssClass="form-control input-medium as-input" maxlength="50"></sj:textfield>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-5"> SSLC Number : </label>
					<div class="col-md-7">
						<sj:textfield name="studentVo.account.personVo.sslcNumber"
							id="sslcNumber" cssClass="form-control input-medium as-input"
							maxlength="12"></sj:textfield>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-5"> TMR Number : </label>
					<div class="col-md-7">
						<sj:textfield name="studentVo.account.personVo.tmrNumber"
							id="tmrNumber" cssClass="form-control input-medium as-input"
							maxlength="12"></sj:textfield>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-5"> Teeth : </label>
					<div class="col-md-7">
						<sj:textfield name="studentVo.account.personVo.teeth" id="teeth"
							cssClass="form-control input-medium as-input" maxlength="40"></sj:textfield>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-5"> Oral Hygiene : </label>
					<div class="col-md-7">
						<sj:textfield name="studentVo.account.personVo.oralHygiene"
							id="oralHYgiene" cssClass="form-control input-medium as-input"
							maxlength="30"></sj:textfield>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-5"> Height 1 : </label>
					<div class="col-md-7">
						<sj:textfield name="studentVo.account.personVo.height" id="height"
							cssClass="numericDot form-control input-medium as-input"
							maxlength="10"></sj:textfield>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-5"> Weight 1 : </label>
					<div class="col-md-7">
						<sj:textfield name="studentVo.account.personVo.weight" id="weight"
							cssClass="form-control input-medium as-input" maxlength="10"></sj:textfield>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-5"> Height 2 : </label>
					<div class="col-md-7">
						<sj:textfield name="studentVo.account.personVo.height2" id="height2"
							cssClass="numericDot form-control input-medium as-input"
							maxlength="10"></sj:textfield>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-5"> Weight 2 : </label>
					<div class="col-md-7">
						<sj:textfield name="studentVo.account.personVo.weight2" id="weight2"
							cssClass="form-control input-medium as-input" maxlength="10"></sj:textfield>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-5"> Vision (Left) : </label>
					<div class="col-md-7">
						<sj:textfield name="studentVo.account.personVo.visionL"
							id="visionLeft" cssClass="form-control input-medium as-input"
							maxlength="10"></sj:textfield>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-5"> Vision (Right) : </label>
					<div class="col-md-7">
						<sj:textfield name="studentVo.account.personVo.visionR"
							id="visionRight" cssClass="form-control input-medium as-input"
							maxlength="10"></sj:textfield>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-5"> Outside School Duty
						: </label>
					<div class="col-md-7">
						<s:radio list="#{'Y':'Yes','N':'No'}"
							name="studentVo.outSideSchoolDuty" id="outSideSchoolDuty" />
					</div>
				</div>
			</div>

		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-5"> <span
						class="required">*</span>Student Disability :
					</label>
					<div class="col-md-7">
						<div class="radio-list">
							<label class="radio-inline"> <input type="radio"
								id="phIdYes" value="true" name="studentVo.account.personVo.phId"
								onclick="javascript: displayPHCDiv(this.value)"> Yes
							</label> <label class="radio-inline"> <input type="radio"
								name="studentVo.account.personVo.phId" id="phIdNo" value="false"
								onclick="javascript: displayPHCDiv(this.value)"> No
							</label>
						</div>
						<span class="phStatus"
							id="<s:property value='studentVo.account.personVo.phId'/>"></span>
					</div>
				</div>
			</div>
		</div>
		<div id="physicallyHandiCappedDivId" style="display: none;">
			<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-5"> Disability Details : </label>
					<div class="col-md-7">
						<sj:textarea rows="3" cols="20"
							name="studentVo.account.personVo.physicallyHandicappedDesc"
							maxCharsData="1000" tabindex="3"
							cssClass="form-control word_count"></sj:textarea>
						<span class="help-block">
							<div class="counter"></div>
						</span>
					</div>
				</div>
			</div>
			<%-- <div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-5"> Disability Documents : </label>
					<div class="col-md-7">
						<s:file name="fileUpload" id="uploadDocs" multiple="multiple" cssClass="btn default"></s:file>
					</div>
				</div>
			</div> --%>
			</div>
			<%-- <div class="row">
			 <div class="col-md-12">
			  <s:if test="%{tempList != null && !tempList.isEmpty()}">
				 <div class="col-md-2" style="width: 19.9%"></div>
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
									<td><a rel="nofollow" href='<c:url value='/student/downloadPhysicallyHandicappedDocuments.do'/>?tempId=<s:property value="studentVo.id" />&fileName=<s:property value="fileName" />'><s:property
												value="fileName" /> </a></td>
									<s:if test='%{#session.previousYear == "N"}'>
										<td><s:url id="removeFile"
												action="ajaxRemovePhysicallyHandicappedDocuments"
												escapeAmp="false" includeParams="all">
												<s:param name="tempId" value="studentVo.id"></s:param>
												<s:param name="tempString"
													value="studentVo.account.admissionNumber"></s:param>
												<s:param name="fileName" value="fileName"></s:param>
											</s:url> <s:div cssClass="btn btn-xs red emsFileRemove"
												id='%{removeFile}' theme="simple" title="Delete this File">
												<i class="fa fa-trash-o"></i>
											</s:div></td>
									</s:if>
								</tr>
							</s:iterator>
						</tbody>
					</table>
				</div>
				<div class="col-md-3">
					<div class="linkRight" align="right">
						<a rel="nofollow"
							href='<c:url value='/student/downloadPhysicallyHandicappedDocuments.do'/>?tempId=<s:property value="studentVo.id" />'
							class="btn blue" style="padding: 0 2px;">Download All</a>&nbsp;&nbsp;
						<s:if test='%{#session.previousYear == "N"}'> 	
							|&nbsp;&nbsp;
						<s:url id="removeDocuments"
								action="ajaxRemovePhysicallyHandicappedDocuments" namespace="/student" escapeAmp="false" includeParams="all">
								<s:param name="tempId" value="%{studentVo.id}"></s:param>
								<s:param name="eventId">deleteAll</s:param>
							</s:url>
							<sj:a id="removeDocumentsLink" href="%{removeDocuments}"
								targets="studentEditContentDiv" cssClass="btn btn-xs red">Delete All</sj:a>
						</s:if>
					</div>
				</div>
				</s:if>
				<s:else>
					<div class="alert alert-info"> Currently there are no files. Please upload files for student disability.</div>
				</s:else>
				</div>
			</div> --%>
		</div>
		<h4 class="pageTitle bold form-section">SELF AWARENESS :</h4>
		<div class="row">
		<div class="col-md-6">
			<div class="form-group">
				<label class="control-label col-md-5">
					Goals :
				</label>
				<div class="col-md-7">
					<sj:textarea  name="studentVo.goals" id="maxlength_textarea1" cssClass="form-control" placeholder="This textarea has a limit of 200 chars."  rows="4" cols="20" maxlength="200"></sj:textarea>
				</div>
			</div>
		</div>
		<div class="col-md-6">
			<div class="form-group">
				<label class="control-label col-md-5">
					Strengths :
				</label>
				<div class="col-md-7">
				<sj:textarea  name="studentVo.strengths" id="maxlength_textarea2" cssClass="form-control" placeholder="This textarea has a limit of 200 chars."  rows="4" cols="20" maxlength="200"></sj:textarea>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-md-6">
			<div class="form-group">
				<label class="control-label col-md-5">
					Interests and Hobbies :
				</label>
				<div class="col-md-7">
				<sj:textarea  name="studentVo.interestsAndHobbies" id="maxlength_textarea3" cssClass="form-control" placeholder="This textarea has a limit of 200 chars."  rows="4" cols="20" maxlength="200"></sj:textarea>
				</div>
			</div>
		</div>
		<div class="col-md-6">
			<div class="form-group">
				<label class="control-label col-md-5">
					Responsibilities Discharged :
				</label>
				<div class="col-md-7">
				<sj:textarea  name="studentVo.responsibilities" id="maxlength_textarea4" cssClass="form-control" placeholder="This textarea has a limit of 200 chars."  rows="4" cols="20" maxlength="200"></sj:textarea>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-md-6">
			<div class="form-group">
				<label class="control-label col-md-5">
					Exceptional Achievements :
				</label>
				<div class="col-md-7">
				<sj:textarea  name="studentVo.achievements" id="maxlength_textarea5" cssClass="form-control" placeholder="This textarea has a limit of 2000 chars."  rows="4" cols="20" maxlength="200"></sj:textarea>
				</div>
			</div>
		</div>
		<div class="col-md-6">
			<div class="form-group">
				<label class="control-label col-md-5">
					Remarks :
				</label>
				<div class="col-md-7">
				<sj:textarea  name="studentVo.remarks" id="maxlength_textarea4" cssClass="form-control" placeholder="This textarea has a limit of 200 chars."  rows="4" cols="20" maxlength="200"></sj:textarea>
				</div>
			</div>
		</div>
	</div>
		<div class="clearfix">&nbsp;</div>

		<s:if
			test='%{(#session.previousYear == null || #session.previousYear.isEmpty()) || (#session.previousYear == "N")}'>
			<div class="form-actions fluid">
				<div class="col-md-offset-3 col-md-9">
					<sj:submit cssClass="btn blue" value="Save" validate="true"
						indicator="indicator" targets="studentEditContentDiv"
						onBeforeTopics="validateStudentPersonalInfo"
						formIds="editStudentPersonalInfo" />
				</div>
			</div>
		</s:if>
	</s:form>
</div>
<script type="text/javascript">
/* $('#admissionNumber').bind('input', function() {
	 $(this).val($(this).val().replace(/[ \, :, ? ,<, > ,|,/]/, ''));
	}); */
	$(document).ready(function() {
		FormComponents.init();
		$.destroyTopic('validateStudentPersonalInfo');
		var phidVal = '<s:property value="studentVo.account.personVo.phId"/>';
		if ("true" == phidVal)
			$('#physicallyHandiCappedDivId').show();
	
		$('.numeric').numeric();
		$("input:checkbox, input:radio:not('.toggle')") .uniform();
		/* $("#admissionNumber").autoCheck("${pageContext.request.contextPath}/admin/ajaxCheckAdmissionNumberAvailableOrNot.do?anyId="+ $('span.studentAccountId').attr('id'),
			{
				minChars : 1,
				min : "no",
			}); */
	
		$('#studentAndStafffInfo').hide();
		var transportValue1 = $('span.transportValue').attr(
				'id');
		if (isNonEmpty(transportValue1)) {
			if (transportValue1 == 'T') {
				$('#SchoolT').parent('span')
						.addClass('checked');
			} else if (transportValue1 == 'P') {
				$('#Private').parent('span')
						.addClass('checked');
			} else {
				$('#Own').parent('span').addClass('checked');
			}
		}
		var genderValue1 = $('span.genderValue').attr('id');
		if (isNonEmpty(genderValue1)) {
			if (genderValue1 == 'M') {
				$('#Male').parent('span').addClass('checked');
				$("#Male").attr("checked", true);
			} else {
				$('#Female').parent('span').addClass('checked');
				$("#Female").attr("checked", true);
			}
		}
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
	
	});

	function getSubCastDetailsByCast(selectBox) {
		var castId = $("select#castName").val();
		var url = jQuery.url
				.getChatURL("/admin/ajaxStudentSubCastDetailsByCast.do");
		if (castId.length == 0) {
			alert("!Oops select Cast.");
		} else {
			$("#resultsDiv2")
					.html(
							'<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
			var pars = "personVo.castId=" + castId;
			$.ajax({
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

	function verifyDate() {
		var date0 = $('span#studentDateOfBirth').attr("class");
		var studentJoiningDate = $('#studentJoiningDate').val();
		//alert(date0+'.......'+studentJoiningDate)
		if (isNonEmpty(studentJoiningDate) && isNonEmpty(date0)) {
			date0 = new Date(date0);
			studentJoiningDate = new Date(studentJoiningDate);
			var birthYear = new Date(date0.setYear(date0.getFullYear() + 2));
			if (studentJoiningDate < birthYear) {
				$('#studentJoiningDate').val('');
				alert("Date of joining should be after 2 years of birth date.");
			}
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
	/*this script only for chrome*/
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
		 if (!(txtMobId.length == 10)) {
			//var stuMob = $("#studentMobile").val();
			var ParresMob1 = $("#mobileNumber").val();
			var ParresMob2 = $("#secondmobileNumber").val();
			var PartempMob1 = $("#tstumobileNumber").val();
			var PartempMob2 = $("#tstumobileNumbers").val();
			/* if (!(stuMob.length == 10)){
				$("#studentMobile").val('');
				$("#studentMobile").focus();
			} */
			
			if(isNonEmpty(ParresMob1)){
				if(!(ParresMob1.length == 10)){
					alert("Please enter 10 digits mobile number.");
					$("#mobileNumber").val('');
					$("#mobileNumber").focus();
				}
			}
			if(isNonEmpty(ParresMob2)){
				if(!(ParresMob2.length == 10)){
					alert("Please enter 10 digits mobile number.");
					$("#secondmobileNumber").val('');
					$("#secondmobileNumber").focus();
				}
			}
			if(isNonEmpty(PartempMob1)){
				if(!(PartempMob1.length == 10)){
					alert("Please enter 10 digits mobile number.");
					$("#tstumobileNumber").val('');
					$("#tstumobileNumbers").focus();
				}
			}
			if(isNonEmpty(PartempMob2)){
				if(!(PartempMob2.length == 10)){
					alert("Please enter 10 digits mobile number.");
						$("#tstumobileNumbers").val('');
						$("#tstumobileNumbers").focus();
				}
			}
			return false;
		} else if ((txtMobId.length == 10)) {
			return true;
		}
	}
	$.subscribe('validateStudentPersonalInfo',function(event, data) {
		var admissionnumber = $(
				"[name='studentVo.account.admissionNumber']")
				.val();
		if (isNonEmpty(admissionnumber)) {
			if (admissionnumber.length < 1) {
				alert('Please provide at least one characters for admission number.');
				event.originalEvent.options.submit = false;
			}
			if ($('p.word-taken').html() == 'Already taken!!!') {
				event.originalEvent.options.submit = false;
			}
			$("input#newAdmissionNumber").val(admissionnumber);
		}
	});
	function FillBilling(offlineApplicationForm) {
		if (offlineApplicationForm.billingtoo.checked == true) {
			offlineApplicationForm.addressLine11.value = offlineApplicationForm.addressLine1.value;
			offlineApplicationForm.addressLine21.value = offlineApplicationForm.addressLine2.value;
			offlineApplicationForm.city1.value = offlineApplicationForm.city.value;
			offlineApplicationForm.state1.value = offlineApplicationForm.state.value;
			offlineApplicationForm.pincode1.value = offlineApplicationForm.pincode.value;
			offlineApplicationForm.temailAddress.value = offlineApplicationForm.email.value;
			offlineApplicationForm.tstumobileNumber.value = offlineApplicationForm.mobileNumber.value;
			offlineApplicationForm.tstumobileNumbers.value = offlineApplicationForm.secondmobileNumber.value;
			
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
		if (phcVal == 'true')
			$('#physicallyHandiCappedDivId').show();
		else
			$('#physicallyHandiCappedDivId').hide();
	}

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
					$('#studentEditContentDiv').html(html);
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