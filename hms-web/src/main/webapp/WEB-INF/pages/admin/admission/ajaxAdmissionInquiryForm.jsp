<%@ include file="/common/taglibs.jsp"%>
<jsp:include page="/common/messages.jsp"></jsp:include>
<div  id="admissionsInquiryFormDivId">
<s:if test="%{academicYear != null && academicYear != empty}">
	<s:if test="%{classList != null && !classList.isEmpty()}">
		<s:form action="ajaxSaveAdmissionInquiryDetails" theme="simple" cssClass="form-horizontal" id="addStudentOfflineInfo" method="post" namespace="/admin">
			<s:hidden name="admissionSettings.id"></s:hidden>
			<h4 class="pageTitle bold form-section">ADMISSION ENQUIRY</h4>
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
					<div class="col-md-6" id="allClasses">
						<div class="form-group">
							<s:if test="%{classList != null && !classList.isEmpty()}">
								<label class="control-label col-md-4">
									<span class="required">*</span>Select Class :
								</label>
								<div class="col-md-6">
									<s:select list="classList" listKey="id" listValue="className"
										cssClass="required form-control input-medium"
										name="onlineApplicationDetails.classId.id" headerKey=""
										headerValue="- Select -" theme="simple">
									</s:select>
								</div>
							</s:if>
							<s:else>
								<div class="alert alert-info">
									No classes defined for this admissions.
								</div>
							</s:else>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4"> <span
								class="required">*</span>Student Name :
							</label>
							<div class="col-md-6">
								<sj:textfield name="admissionInquiry.studentName"
									id="firstName"
									cssClass="required form-control input-medium as-input"
									maxlength="60"></sj:textfield>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4"><span
								class="required">*</span> Parent Name : </label>
							<div class="col-md-6">
								<sj:textfield name="admissionInquiry.parentName"
									id="lastName" cssClass=" form-control input-medium as-input required"
									maxlength="60"></sj:textfield>
							</div>
						</div>
					</div>
				</div>
				
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4"> <span
								class="required">*</span>Parent Mobile Number : </label>
							<div class="col-md-6">
								<sj:textfield name="admissionInquiry.parentMobileNumber" onblur="return validateParentMobNumber(this.value)"
									id="mobileNumber" cssClass="form-control input-medium as-inputl numeric required"
									maxlength="10"></sj:textfield>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4"> Admission Amount : </label>
							<div class="col-md-6">
							<s:hidden name="admissionInquiry.applicationFee" value="%{admissionSettings.applicationFee}"></s:hidden>
								<sj:textfield name="applicationFee"
									value='%{admissionSettings.applicationFee}'
									id="applicationFee"
									cssClass="form-control input-medium as-inputl" maxlength="20" disabled="true"></sj:textfield>
							</div>
						</div>
					</div>
				</div>
				
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4"> <span
								class="required">*</span>Previous School Name :</label>
							<div class="col-md-6">
								<sj:textfield id="previousSchoolName" name="admissionInquiry.previousSchoolName"
									cssClass="required form-control input-medium as-input"
									maxlength="60"></sj:textfield>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4"><span
								class="required">*</span> Student Type : </label>
							<div class="col-md-6">
							<s:select name="admissionInquiry.studentType"
							cssClass="form-control required input-medium" id="studentType"
								list="#{'':'-Select-','D':'Day Scholar','H':'Hosteler'}"
								onchange="javascript:enableSalunationTextBox(this.value);"></s:select>
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
						<label class="control-label col-md-4"> <span
								class="required">*</span>State : </label>
						 <div class="col-md-8">
							<s:select id="state" list="statesList" label="State"
								cssClass="required form-control input-medium" listKey="stateCode"
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
			</div>
			<div class="form-actions fluid">
					<div class="col-md-offset-2 col-md-6">
						<sj:submit cssClass="btn blue" value="Save" indicator="indicator"
							validate="true" 
							formIds="addStudentOfflineInfo" targets="admissionsInquiryFormDivId" />
							
						<sj:submit cssClass="submitBt btn green long" value="Save & Print"
							indicator="indicator" targets="admissionsInquiryFormDivId"
							
							formIds="addStudentOfflineInfo"
							onCompleteTopics="doPrintStudentAppFee" validate="true" />
							
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

<hr/>

<div id="classHolidaysDiv"> 
 	<jsp:include page="/WEB-INF/pages/admin/admission/ajaxViewAdmissionInquiryList.jsp" />
 </div>
							  


<form method="post" id="printPreviewForAdmissionEnquiryId"
			action="javaScript:printPreviewForAdmissionEnquiry('<s:property value="tempId" />','<s:property value="admissionSettings.applicationFee" />')"
			style="display: none;">
		</form>
</div>
<script type="text/javascript">
	$(document).ready(function() {
		UIExtendedModals.init();
		FormComponents.init();
		FormAdvanced.init();
		
		changePageTitle("Admission Inquiry");
	
		$('.numeric').numeric();
     
     
     $.subscribe('doPrintStudentAppFee', function(event, data) {
 		$('#printPreviewForAdmissionEnquiryId').submit();
 		$.destroyTopic('doPrintStudentAppFee');
 	});
     
     
	});
	
	function validateParentMobNumber(mobileNumber) {
		 if (isNonEmpty(mobileNumber)) {
			if (!(mobileNumber.length == 10)){
				alert("Please enter 10 digits mobile number.");
				$("#mobileNumber").val("");
				$("#mobileNumber").focus();
				return false;
			 }
		}else if((mobileNumber.length == 10)) {
			return true;
		}
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
 
</script>