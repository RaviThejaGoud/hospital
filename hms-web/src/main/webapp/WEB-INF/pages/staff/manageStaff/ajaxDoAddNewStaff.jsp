<%@ include file="/common/taglibs.jsp"%>
<div id="teachingDiv">
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/scripts/common/textcounter.js">
	</script>
	<div class="form-body">
		<s:form action="ajaxAddStaff" enctype="multipart/form-data" theme="simple" id="addTeachingStaff" cssClass="form-horizontal" method="post" namespace="/staff">
			<input type="hidden" name="bankId" value="<s:property value="bankId"/>">
			<input type="hidden" name="teachingRoleName" value="<s:property value="teachingRoleName"/>">
			<input type="hidden" name="tempString" value="<s:property value="anyId"/>">
			<h4 class="pageTitle bold form-section">CONTACT INFORMATION</h4>
			<h5>RESIDENTIAL ADDRESS</h5>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> Street : </label>
						<div class="col-md-5">
							<sj:textfield name="addressVo.addressLine1" id="addressLines"
								tabindex="1" cssClass="form-control " maxlength="55"></sj:textfield>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group ">
						<label class="control-label col-md-4"> <span
							class="required">*</span>City :
						</label>
						<div class="col-md-5">
							<sj:textfield name="addressVo.city" id="city" tabindex="2"
								cssClass="required form-control " maxlength="40"></sj:textfield>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<%--<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> <span
							class="required">*</span>Country :
						</label>
						<div class="col-md-5">
							<s:select id="state" list="countryList" listKey="id"
								listValue="countryName" tabindex="3" headerKey=""
								headerValue="- Select -" name="addressVo.country" cssClass="form-control countryId required"
								onchange="getStatesListsByCountryId(this.value);" />
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div id="statesId">
							<jsp:include
								page="/WEB-INF/pages/staff/manageStaff/ajaxGetStatesListByCountryId.jsp" />
					</div>
				</div> --%>
				 <div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> <span
							class="required">*</span>State :
						</label>
						<div class="col-md-5">
							<s:select id="state" list="statesList" listKey="stateCode"
								listValue="stateName" tabindex="3" headerKey=""
								headerValue="- Select -" name="addressVo.state"
								cssClass="required form-control" />
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group ">
						<label class="control-label col-md-4"> Pincode : </label>
						<div class="col-md-5">
							<sj:textfield name="addressVo.postalCode" id="pincode"
								label="Pincode" tabindex="4" cssClass="numeric form-control"
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
										onclick="FillBillings(this.form)" class="allClasses">
								</span>
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
							<sj:textfield name="userVo.tempararyAddressVo.addressLine1"
								id="addressLines1" tabindex="1" cssClass="form-control "
								maxlength="55"></sj:textfield>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group ">
						<label class="control-label col-md-4"> City : </label>
						<div class="col-md-5">
							<sj:textfield name="userVo.tempararyAddressVo.city"
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
								name="userVo.tempararyAddressVo.state"
								cssClass="form-control" />
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group ">
						<label class="control-label col-md-4"> Pincode : </label>
						<div class="col-md-5">
							<sj:textfield name="userVo.tempararyAddressVo.postalCode"
								id="pincode1" label="Pincode" tabindex="4"
								cssClass="numeric form-control" maxlength="6"></sj:textfield>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> Phone Number : </label>
						<div class="col-md-5">
							<sj:textfield name="personVo.phoneNumber" id="phoneNumber"
								tabindex="5" cssClass="numeric form-control " maxlength="15"></sj:textfield>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group ">
						<label class="control-label col-md-4"> Office Number : </label>
						<div class="col-md-5">
							<sj:textfield name="personVo.officeNumber" id="officeNumber"
								tabindex="6" cssClass="numeric form-control " maxlength="11"></sj:textfield>
						</div>
					</div>
				</div>
			</div>
			<h4 class="pageTitle bold form-section">SALARY & BANK ACCOUNT
				INFORMATION</h4>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> Salary : </label>
						<div class="col-md-5">
							<sj:textfield name="salaryVo.salary" id="salaryIfo"
								cssClass="numeric form-control" maxlength="10" tabindex="7"></sj:textfield>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group ">
						<label class="control-label col-md-4"> Bank Account Number
							: </label>
						<div class="col-md-5">
							<sj:textfield name="personVo.bankAccountNumber"
								id="bankAccountNumber" tabindex="8"
								cssClass="numeric form-control" maxlength="20"></sj:textfield>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> Bank Name : </label>
						<div class="col-md-5">
							<sj:textfield name="personVo.bankName" id="bankName" tabindex="9"
								cssClass="form-control" maxlength="40"></sj:textfield>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group ">
						<label class="control-label col-md-4"> Bank Branch Name :
						</label>
						<div class="col-md-5">
							<sj:textfield name="personVo.bankBranchName" id="bankBranchName"
								tabindex="10" cssClass="form-control" maxlength="40"></sj:textfield>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> PAN Number : </label>
						<div class="col-md-5">
							<sj:textfield name="personVo.panNumber" id="panNumber"
								tabindex="11" cssClass="form-control " maxlength="10"></sj:textfield>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group ">
						<label class="control-label col-md-4"> GPF Number : </label>
						<div class="col-md-5">
							<sj:textfield name="personVo.gpfNumber" id="gpfNumber"
								tabindex="12" cssClass="form-control " maxlength="10"></sj:textfield>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> IFSC Code : </label>
						<div class="col-md-5">
							<sj:textfield name="personVo.ifscCode" id="ifscCode" tabindex="13"
								cssClass="form-control " maxlength="20"></sj:textfield>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> Upload Documents :
						</label>
						<div class="col-md-5">
							<s:file name="fileUpload" id="uploadDocs" multiple="multiple" 
								tabindex="14"></s:file>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> Do you have ESI : </label>
						<div class="col-md-5">
							<div id="esiradiobuttonId">
								<div class="checkbox-list">
									<label class="checkbox-inline"> <input type="checkbox"
										name="esiname" id="esicheckboxId" tabindex="15">
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
										name="pdname" id="pfcheckboxId" tabindex="17">
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
										<sj:textfield name="staffStatutoryVo.esiNo" id="esiNo"
											tabindex="19" cssClass="form-control alphanumeric"
											maxlength="17"></sj:textfield>
									</div>
								</div>
							</div>

						</div>
						<div class="row">
							<div class="col-md-12">
								<div class="form-group ">
									<label class="control-label col-md-4"> ESI Date Of Join
										: </label>
									<div class="col-md-5">
										<div data-date-format="mm/dd/yyyy"
											class="input-group input-medium date date-picker">
											<input type="text" id="esiDateofJoin" readonly=""
												class="form-control" onchange="verifyStaffDate();"
												tabindex="20" name="staffStatutoryVo.esiDateofJoin"> <span
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
									<label class="control-label col-md-4"> ESI Percentage :
									</label>
									<div class="col-md-5">
										<sj:textfield name="staffStatutoryVo.esiPercentage"
											id="esiPercentage" tabindex="21"
											cssClass="form-control numericDot" maxlength="2"></sj:textfield>
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
										<sj:textfield name="staffStatutoryVo.pfNo" id="pfNo"
											tabindex="22" cssClass="form-control alphanumeric"
											maxlength="20"></sj:textfield>
									</div>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-12">
								<div class="form-group ">
									<label class="control-label col-md-4"> PF Date Of Join
										: </label>
									<div class="col-md-5">
										<div data-date-format="mm/dd/yyyy"
											class="input-group input-medium date date-picker">
											<input type="text" id="pfDateofJoin" readonly=""
												class="form-control" tabindex="23"
												name="staffStatutoryVo.pfDateofJoin"> <span
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
										<sj:textfield name="staffStatutoryVo.pfPercentage"
											id="pfPercentage" tabindex="24"
											cssClass="form-control numericDot" maxlength="2"></sj:textfield>
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
						<label class="control-label col-md-4">Staff Payment Mode : </label>
						<div class="col-md-5">
							<s:select id="salaryPaymentMode" headerKey="" tabindex="23"
								headerValue="- Select -" cssClass="form-control"
								name="personVo.salaryPaymentMode"
								list="#{'CASH':'CASH','CHEQUE':'CHEQUE','NEFT/RTGS':'NEFT/RTGS','DD':'DD'}" />
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> Staff Grade :
						</label>
						<div class="col-md-5">
							<sj:textfield name="staffVo.staffGrade" id="staffGrade" tabindex="13"
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
								<sj:textfield name="personVo.staffLocation" id="staffLocation" tabindex="13"
								cssClass="form-control " maxlength="20"></sj:textfield>
						</div>
					</div>
				</div>
			</div>
			<h4 class="pageTitle bold form-section">STAFF HISTORY</h4>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4">Previous School Name
							:</label>
						<div class="col-md-5">
							<sj:textfield name="staffHistoryVo.schoolName" id="schoolName"
								tabindex="5" cssClass="form-control" maxlength="60"></sj:textfield>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group ">
						<label class="control-label col-md-4">Salary :</label>
						<div class="col-md-5">
							<sj:textfield name="staffHistoryVo.salary" id="salary" tabindex="6"
								cssClass="numericDot form-control" maxlength="10"></sj:textfield>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> Start Date :</label>
						<div class="col-md-5">
							<div data-date-format="mm/dd/yyyy"
								class="input-group input-medium date date-picker">
								<input type="text" id="contractStartDate" readonly=""
									class="form-control" tabindex="3" name="staffHistoryVo.startDate" onchange="verifyStaffSubmitForm();"
									value='<s:property value="staffHistoryVo.startDateStr"/>'>
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
						<label class="control-label col-md-4">End Date :</label>
						<div class="col-md-5">
							<div data-date-format="mm/dd/yyyy"
								class="input-group input-medium date date-picker">
								<input type="text" id="staffContractEndDate" readonly=""
									class="form-control" tabindex="4" name="staffHistoryVo.endDate" onchange="verifyStaffSubmitForm();"
									value='<s:property value="staffHistoryVo.endDateStr"/>'>
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
						<label class="control-label col-md-4">Other Experience :</label>
						<div class="col-md-5">
							<sj:textarea rows="3" cols="20"
								name="staffHistoryVo.otherExperience" maxCharsData="1000"
								tabindex="14" cssClass="form-control word_count"></sj:textarea>
							<span class="help-block">
								<div class="counter"></div>
							</span>
						</div>
					</div>
				</div>
			</div>
			<div class="form-actions fluid">
				<div class="col-md-6" style="float: right; width: 274px;">
					<div class="col-md-offset-0 col-md-10">
						<sj:submit cssClass="submitBt btn blue " value="Submit"
							targets="staffsContent" validate="true"
							formIds="addTeachingStaff" tabindex="25" />
						<s:url id="urlManageStaff" action="ajaxDoManageStaff"
							namespace="/staff" />
						<sj:a href="%{urlManageStaff}" tabindex="26"
							targets="mainContentDiv" cssClass="btn default">
								Cancel</sj:a>
					</div>
				</div>
				<div class="col-md-6" style="float:left;width:150px;">
					<div class="col-md-offset-2 col-md-5">
						<s:url id="doAddStaffBackLink" action="ajaxDoAddStaff"
							includeParams="all" escapeAmp="false" namespace="/staff">
						</s:url>
						<sj:a href="%{doAddStaffBackLink}" cssClass="btn default"
							targets="staffsContent" indicator="indicator">  Back</sj:a>
					</div>
				</div>
			</div>
		</s:form>
	</div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/plugins/bootstrap-switch/static/js/bootstrap-switch.min.js"></script>
<script type="text/javascript">
	changePageTitle("Add Staff Details");
	$('select#subCastName').attr("tabindex", 8);
	$('html, body').animate({ scrollTop: $(document).height() - $(window).height() }, 10, function() {
	    $(this).animate({ scrollTop: 0 }, 10);
	});
	/* $('li #doAddStaffBackLink').click( function() {
		window.history.back()
	}); */
	
	$(document)
			.ready(
					function() {
						$("input:checkbox, input:radio").uniform();
						FormComponents.init();
						$("input[name=empId]")
								.autoCheck(
										"${pageContext.request.contextPath}/admin/ajaxCheckUserId.do",
										{
											minChars : 3,
											min : "no"
										});
						$("#addressLines").focus();
						$('.numeric').numeric();
						$('.numericDot').numeric({
							allow : "."
						});
						$('.alphabet').alpha();
						$('.alphanumeric').alphanumeric();
						
						<%-- var countryId=$("select.countryId").val();
					    	if(isNonEmpty(countryId)){
					    		getStatesListsByCountryId(countryId);
					    	}  --%>
					});

	function changeQualification(staffType) {
		if (staffType == 'teaching') {
			$('#teachingDiv').show();
			$('#nonTeachingDiv').hide();
		} else {
			$('#teachingDiv').hide();
			$('#nonTeachingDiv').show();
		}
	}

	function ajaxShowInchargeList(staffType) {
		var pars = "staffType=" + staffType;
		//alert(staffType);
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
	$('input#pfcheckboxId').click(function() {
		if ($(this).is(":checked")) {
			$("#pfDivId").show();
			$('input#pfNo').addClass('required');
		} else {
			$('input#pfNo').removeClass('required');
			$("#pfDivId").hide();
			$('input#pfcheckboxId').val('');
			$('input#pfNo').val('');
			$('input#pfDateofJoin').val('');
			$('input#pfPercentage').val('');
			$('div#pfDivId').find('label.error').remove();
		}
	});
	$('input#esicheckboxId').click(function() {
		if ($(this).is(":checked")) {
			$("#esiDivId").show();
			$('input#esiNo').addClass('required');
		} else {
			$('input#esiNo').removeClass('required');
			$("#esiDivId").hide();
			$('input#esicheckboxId').val('');
			$('input#esiNo').val('');
			$('input#esiDateofJoin').val('');
			$('input#esiPercentage').val('');
			$('div#esiDivId').find('label.error').remove();
		}
	});
	function FillBillings(offlineApplicationForm) {
		if (offlineApplicationForm.billingtoo.checked == true) {
			offlineApplicationForm.addressLines1.value = offlineApplicationForm.addressLines.value;
			offlineApplicationForm.city1.value = offlineApplicationForm.city.value;
			offlineApplicationForm.state1.value = offlineApplicationForm.state.value;
			offlineApplicationForm.pincode1.value = offlineApplicationForm.pincode.value;
		} else {
			offlineApplicationForm.addressLines1.value = "";
			offlineApplicationForm.city1.value = "";
			offlineApplicationForm.state1.value = "";
			offlineApplicationForm.pincode1.value = "";
		}

	}
<%--	function getStatesListsByCountryId(countryId)
	{
		//alert('sfsfd');
		var stateId=$('input#stateValId').val();
		//alert("Hi:-"+stateId);
		var pars;
		if(isNonEmpty(stateId))
			 pars="country.id=" + countryId+"&tempId="+stateId;
		else
			 pars="country.id=" + countryId+"&tempId="+0;
		var url = jQuery.url.getChatURL("/common/ajaxGetStatesListByCountryId.do");
		$('#statesId')
				.html('<div align="center" style="margin: 150px;"><img src="../assets/layouts/layout2/img/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		$.ajax({
			url : url,
			cache : false,
			data : pars,
			success : function(html) {
				$("#statesId").html(html);
			}
		});
	}	--%>
	
	function verifyStaffSubmitForm() {
		var contractStartDate = $('#contractStartDate').val();
		var staffContractEndDate = $('#staffContractEndDate').val();
		if (isNonEmpty(contractStartDate) && isNonEmpty(staffContractEndDate)) {
			contractStartDate = new Date(contractStartDate);
			staffContractEndDate = new Date(staffContractEndDate);
			if(contractStartDate.getTime() == staffContractEndDate.getTime())
			{
				$('#staffContractEndDate').val('');
				alert("Start date and end date cannot be equal.");
				return false;
			}
			if (staffContractEndDate < contractStartDate) {
				$('#staffContractEndDate').val('');
				alert("End date must be greater than the start date.");
				return false;
			}
		}
	}
	
</script>