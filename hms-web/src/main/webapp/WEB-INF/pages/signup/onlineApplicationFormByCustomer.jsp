<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="en" class="no-js">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1.0" name="viewport" />
<meta name="MobileOptimized" content="320"> 
	<head>
 <sj:head debug="true" compressed="false" defaultIndicator="myDefaultIndicator"  jqueryui="false" />
<style type="text/css" media="all">
			@import url("${pageContext.request.contextPath}/plugins/font-awesome/css/font-awesome.min.css");		
			@import url("${pageContext.request.contextPath}/plugins/bootstrap/css/bootstrap.min.css");
			@import url("${pageContext.request.contextPath}/plugins/uniform/css/uniform.default.css");
			@import url("${pageContext.request.contextPath}/plugins/select2/select2_metro.css");
			@import url("${pageContext.request.contextPath}/plugins/data-tables/DT_bootstrap.css");
			@import url("${pageContext.request.contextPath}/styles/newCss/style-metronic.css");
			@import url("${pageContext.request.contextPath}/styles/newCss/style.css");
			@import url("${pageContext.request.contextPath}/styles/newCss/style-responsive.css");	
			@import url("${pageContext.request.contextPath}/plugins/bootstrap-datepicker/css/datepicker.css");
		    @import url("${pageContext.request.contextPath}/plugins/fullcalendar/fullcalendar/fullcalendar.css");
			@import url("${pageContext.request.contextPath}/plugins/bootstrap-colorpicker/css/colorpicker.css");	
			@import url("${pageContext.request.contextPath}/plugins/bootstrap-switch/static/stylesheets/bootstrap-switch-metro.css");
          </style>
          <link rel="stylesheet" type="text/css" id="style_color" href="${pageContext.request.contextPath}/styles/newCss/themes/default.css" />
</style>  
	<title>Eazy School :: Online Application Form</title>						
 <body class="page-boxed page-header-fixed" style="background:linear-gradient(#6b9570, #49694f) repeat fixed 0 0 #6b9570;">
	<div class="header navbar navbar-inverse navbar-fixed-top">
		<div class="header-inner container">
			<div class="header-inner">
				<a class="navbar-brand" href="#"> <img
					src='<s:property  value="customer.customerLogo"/>' border="0"
					alt="logo" class="logoHeader" />
				</a> <span style="color: #fff;">
					<h3>
						<s:property value="customer.organization" />
					</h3> <s:property value="customer.customerFormattedAddress" />
				</span>

			</div>
		</div>
	</div>
	<div class="container">
		<div class="page-container">
			<div class="page-sidebar-wrapper">
				<div class="page-sidebar navbar-collapse collapse"></div>
				<div class="spaceDiv">&nbsp;&nbsp;</div>
				<div class="clearfix">&nbsp;</div>
				<div class="col-md-12">
				<div class="portlet box blue">
				<s:if test="%{academicYear != null && academicYear != empty}">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-globe"></i>Online Admissions Form For <s:property value="academicYear.academicYear"/>
						</div>
					</div>
					<div class="portlet-body">
						<div id="onlineAppCont" style="background: #fff;">
							<%@ include file="/common/taglibs.jsp"%>
							<script type="text/javascript"
								src="${pageContext.request.contextPath}/scripts/subscription/textcounter.js"></script>
								<s:if test="%{classList != null && !classList.isEmpty()}">
									<s:form action="ajaxOnlineAdmisisonsApplicationForm" theme="simple"
										cssClass="form-horizontal" id="addStudentOfflineInfo"
										method="post" namespace="/admin" enctype="multipart/form-data">
										<s:hidden name="custId" value="%{admissionSettings.custId}"></s:hidden>
										<s:hidden name="customerImage" id="customerImage"></s:hidden>
										<s:hidden name="admissionSettings.applicationFee" id="applicationFee"></s:hidden>
										<s:hidden name="admissionSettings.registrationFee" id="registrationFee"></s:hidden>
										<s:hidden name="admissionSettings.prospectiveFee" id="prospectiveFee"></s:hidden>
										<h4 class="pageTitle bold form-section">Student Information</h4>
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
																listValue="academicYear" headerKey=""
																headerValue="- Select -" name="academicYearId" />
														</div>
													</div>
												</div>
												<div class="col-md-6">
													<div class="form-group">
														<label class="control-label col-md-4"></label>
														<div class="col-md-6">
															<img id="image" border="0" height="102px;"
																style="display: none" />
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
												</div>
											</div>
											<div class="row">
												<div class="col-md-6">
													<div class="form-group">
														<label class="control-label col-md-4"> <span
															class="required">*</span>First Name :
														</label>
														<div class="col-md-6">
															<sj:textfield name="onlineApplicationDetails.firstName"
																id="firstName"
																cssClass="required form-control input-medium as-input"
																maxlength="60"></sj:textfield>
														</div>
													</div>
												</div>
												<div class="col-md-6">
													<div class="form-group">
														<label class="control-label col-md-4"> Last Name : </label>
														<div class="col-md-6">
															<sj:textfield name="onlineApplicationDetails.lastName"
																id="lastName"
																cssClass=" form-control input-medium as-input"
																maxlength="60"></sj:textfield>
														</div>
													</div>
												</div>
											</div>
											<div class="row">
												<div class="col-md-6">
													<div class="form-group ">
														<label class="control-label col-md-4"> Date of Birth
															: </label>
														<div class="col-md-6">
															<div class="input-group input-medium date date-picker" data-date-end-date="+0d">
																<input type="text" id="date0" readonly="readonly"
																	class="form-control"
																	name="onlineApplicationDetails.dateOfBirth"> <span
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
															<label class="control-label col-md-4"> <span
																class="required">*</span>Gender :
															</label>
															<div class="col-md-6">
																<div class="radio-list">
																	
																		<label class="radio-inline">
																			<input type="radio" id="genderId" value="F" name="onlineApplicationDetails.gender"
																				> Female
																		</label> <label class="radio-inline"> <input type="radio"
																				name="onlineApplicationDetails.gender" id="genderId" value="M" checked="checked" 
																				> Male
																			</label>									
																	
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
															<div class="make-switch has-switch" data-id="M"
																data-value="F" style="width: 120px" data-off="warning"
																data-on="success" data-off-label="Female"
																data-on-label="Male">
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
														<label class="control-label col-md-4"> Select
															Religion : </label>
														<div class="col-md-6">
															<s:select list="tempList1" listKey="id"
																listValue="skillTypeName"
																cssClass="form-control input-medium" id="religion"
																name="onlineApplicationDetails.religionId.id"
																headerKey="0" headerValue="- Select -">
															</s:select>
														</div>
													</div>
												</div>
												<div class="col-md-6">
													<div class="form-group">
														<label class="control-label col-md-4"> Nationality :
														</label>
														<div class="col-md-6">
															<sj:textfield name="onlineApplicationDetails.nationality"
																id="nationality"
																cssClass="form-control input-medium as-inputl"
																maxlength="50"></sj:textfield>
														</div>
													</div>
												</div>
											</div>
											<div class="row">
												<div class="col-md-6">
													<div class="form-group">
														<label class="control-label col-md-4"> Last School
															Name : </label>
														<div class="col-md-6">
															<sj:textfield name="onlineApplicationDetails.lastSchool"
																id="lastSchool"
																cssClass="form-control input-medium as-inputl"
																maxlength="80"></sj:textfield>
														</div>
													</div>
												</div>
												<div class=" col-md-6">
													<div class="form-group">
														<label class="control-label col-md-4"> Blood Group :
														</label>
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
												<div class="col-md-6">
													<div class="form-group">
														<label class="control-label col-md-4"> Last Class
															Studied : </label>
														<div class="col-md-6">
															<sj:textfield
																name="onlineApplicationDetails.lastClassAttended"
																id="lastClassAttended"
																cssClass="form-control input-medium as-inputl"
																maxlength="60"></sj:textfield>
														</div>
													</div>
												</div>
											</div>
											<div class="row">
												<div class="col-md-6">
												 <div class="form-group">
														<label class="control-label col-md-4"> Community : </label>
														<div class="col-md-6">
															<s:select id="castName" list="castSettingList" listKey="id"
																listValue="castName" cssClass="form-control input-medium"
																headerKey="" headerValue="- Select -" name="onlineApplicationDetails.castId.id"
																theme="simple"
																onchange="javascript:getSubCastDetailsByCast(this);" />
														</div>
													</div>
												</div> 
												<div class="col-md-6" id="resultsDiv2">
													<jsp:include page="/WEB-INF/pages/admin/admission/castSettings/ajaxGetOnlineSubCastListByCastId.jsp" />
												</div>
											</div>
											<div class="row">
												<div class="col-md-6">
													<div class="form-group">
														<label class="control-label col-md-4"> Transfer
															Certificate No : </label>
														<div class="col-md-6">
															<sj:textfield
																name="onlineApplicationDetails.transferCertificateNo"
																id="transferCertificateNo"
																cssClass="form-control input-medium as-inputl"
																maxlength="40"></sj:textfield>
														</div>
													</div>
												</div>
												<div class="col-md-6">
													<div class="form-group">
														<label class="control-label col-md-4"> Place Of
															Birth : </label>
														<div class="col-md-8">
															<sj:textfield name="onlineApplicationDetails.placeOfBirth"
																id="placeOfBirth"
																cssClass="form-control input-medium as-input"
																maxlength="50"></sj:textfield>
														</div>
													</div>
												</div>
											</div>
											<div class="row">
												<s:if test="%{customer.hostelModuleStatus}">
												
												<div class="col-md-6">
														<div class="form-group">
															<label class="control-label col-md-4"> <span
																class="required">*</span>Residence Type :
															</label>
															<div class="col-md-6">
																<div class="radio-list">
																	
																		<label class="radio-inline">
																			<input type="radio" id="residenceTypeId" value="H" name="onlineApplicationDetails.hostelMode"
																				>Hostler
																		</label> <label class="radio-inline"> <input type="radio"
																				name="onlineApplicationDetails.hostelMode" id="residenceTypeId" value="D" checked="checked" 
																				> Day Scholar
																			</label>									
																	
																</div>
															</div>
														</div>
													</div>
													
													<!-- <div class="col-md-6">
														<div class="form-group">
															<label class="control-label col-md-4"> Residence
																Type : </label>
															<div class="col-md-6">
																<div class="make-switch has-switch" data-id="D"
																	data-value="H" style="width: 200px" data-off="warning"
																	data-on="success" data-off-label="Hostler"
																	data-on-label="Day Scholar">
																	<input type="radio" class="toggle" checked="checked"
																		id="hostlerMode"> <input type="hidden"
																		name="onlineApplicationDetails.hostelMode" value="D">
																</div>
															</div>
														</div>
													</div> -->
												</s:if>
												<div class="col-md-6">
													<div class="form-group">
														<label class="control-label col-md-4"> Aadhar Card
															No : </label>
														<div class="col-md-8">
															<sj:textfield
																name="onlineApplicationDetails.aadharCardNumber"
																id="aadharCardNumber"
																cssClass="form-control input-medium as-input"
																maxlength="20"></sj:textfield>
														</div>
													</div>
												</div>
											</div>
											<div class="row">
												<div class="col-md-6">
													<div class="form-group">
														<label class="control-label col-md-4"> Registration
															Fee : </label>
														<div class="col-md-8">
															<sj:textfield name="admissionSettings.registrationFee"
																id="registrationFee" disabled="true"
																cssClass="form-control input-medium as-input numeric"
																maxlength="7"></sj:textfield>
														</div>
													</div>
												</div>
												<div class="col-md-6">
													<div class="form-group">
														<label class="control-label col-md-4"> Prospective
															Fee : </label>
														<div class="col-md-8">
															<sj:textfield name="admissionSettings.prospectiveFee"
																id="prospectiveFee" disabled="true"
																cssClass="form-control input-medium as-input numeric"
																maxlength="7"></sj:textfield>
														</div>
													</div>
												</div>
											</div>
											<div class="row">
												<s:if test='%{customer.transportModuleStatus}'>
													<div class="col-md-6">
														<div class="form-group">
															<label class="col-md-4 control-label"> Transport
																Mode : </label>
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
											</div>
											<div class="clearfix">&nbsp;</div>
											<h4 class="pageTitle bold form-section">Parent /
												Guardian's information</h4>
											<div class="row">
												<div class="col-md-6">
													<div class="form-group">
														<label class="control-label col-md-4"> <span
															class="required">*</span>Father / Guardian's Name :
														</label>
														<div class="col-md-6">
															<sj:textfield name="onlineApplicationDetails.fatherName"
																id="fatherName"
																cssClass="required form-control input-medium as-inputl"
																maxlength="60"></sj:textfield>
														</div>
													</div>
												</div>
												<div class="col-md-6">
													<div class="form-group">
														<label class="control-label col-md-4"> Father /
															Guardian's Occupation : </label>
														<div class="col-md-6">
															<sj:textfield name="onlineApplicationDetails.occupation"
																id="occupation"
																cssClass="form-control input-medium as-inputl"
																maxlength="40"></sj:textfield>
														</div>
													</div>
												</div>
											</div>
											<div class="row">
												<div class="col-md-6">
													<div class="form-group">
														<label class="control-label col-md-4"> Mother Name :
														</label>
														<div class="col-md-6">
															<sj:textfield name="onlineApplicationDetails.motherName"
																id="motherName"
																cssClass="form-control input-medium as-inputl"
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
																cssClass="form-control input-medium as-inputl"
																maxlength="20"></sj:textfield>
														</div>
													</div>
												</div>
											</div>
											<div class="row">
												<div class="col-md-6">
													<div class="form-group">
														<label class="control-label col-md-4"> Father /
															Guardian's Designation : </label>
														<div class="col-md-6">
															<sj:textfield name="onlineApplicationDetails.designation"
																id="designation"
																cssClass="form-control input-medium as-inputl"
																maxlength="60"></sj:textfield>
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
																cssClass="form-control input-medium as-inputl"
																maxlength="20"></sj:textfield>
														</div>
													</div>
												</div>
											</div>
											<div class="row">
												<div class="col-md-6">
													<div class="form-group">
														<label class="control-label col-md-4"> Parent E-mail
															Id : </label>
														<div class="col-md-6">
															<sj:textfield name="onlineApplicationDetails.parentEmail"
																id="emailAddress"
																cssClass="form-control input-medium as-inputl email"
																maxlength="60"></sj:textfield>
														</div>
													</div>
												</div>
												<div class="col-md-6">
													<div class="form-group">
														<label class="control-label col-md-4"> Annual Income
															: </label>
														<div class="col-md-6">
															<sj:textfield name="onlineApplicationDetails.annualIncome"
																id="guardianQualifiction"
																cssClass="form-control input-medium as-inputl numeric"></sj:textfield>
														</div>
													</div>
												</div>
											</div>
											<div class="clearfix">&nbsp;</div>
											<h4 class="pageTitle bold form-section">Contact details</h4>
											<h4>Residential address</h4>
											<div class="row">
												<div class="col-md-6">
													<div class="form-group">
														<label class="control-label col-md-4"> Street Name :
														</label>
														<div class="col-md-6">
															<sj:textfield
																name="onlineApplicationDetails.primaryAddress.streetName"
																id="streetName"
																cssClass="form-control input-medium as-inputl"
																maxlength="200"></sj:textfield>
														</div>
													</div>
												</div>
												<div class="col-md-6">
													<div class="form-group">
														<label class="control-label col-md-4"> City : </label>
														<div class="col-md-6">
															<sj:textfield
																name="onlineApplicationDetails.primaryAddress.city"
																id="city" cssClass="form-control input-medium as-inputl"
																maxlength="40"></sj:textfield>
														</div>
													</div>
												</div>
											</div>
											<div class="row">
												<div class="col-md-6">
													<div class="form-group">
														<label class="control-label col-md-4"> State : </label>
														<div class="col-md-6">
															<s:select id="state" list="statesList" listKey="stateCode"
																listValue="stateName" headerKey=""
																cssClass="form-control input-medium"
																headerValue="- Select -"
																name="onlineApplicationDetails.primaryAddress.state" />
														</div>
													</div>
												</div>
												<div class="col-md-6">
													<div class="form-group">
														<label class="control-label col-md-4"> Pincode : </label>
														<div class="col-md-6">
															<sj:textfield
																name="onlineApplicationDetails.primaryAddress.postalCode"
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
														<label class="control-label col-md-4"> Phone Number
															: </label>
														<div class="col-md-6">
															<sj:textfield name="onlineApplicationDetails.phoneNumber"
																id="phoneNumber"
																cssClass="numeric form-control input-medium as-inputl"
																maxlength="14"></sj:textfield>
														</div>
													</div>
												</div>
												<div class="col-md-6">
													<div class="form-group">
														<label class="control-label col-md-4"> <span
															class="required">*</span>Parent Mobile Number :
														</label>
														<div class="col-md-6">
															<sj:textfield name="onlineApplicationDetails.mobileNumber"
																id="mobileNumber"
																cssClass="numeric required form-control input-medium as-inputl "
																maxlength="14"
																onkeypress="return formatMobileNumber(this);"
																onblur="return validateMobNumber(this.value)"></sj:textfield>
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
											<h4>Correspondence address</h4>
											<div class="row">
												<div class="col-md-6">
													<div class="form-group">
														<label class="control-label col-md-4"> Street Name :
														</label>
														<div class="col-md-6">
															<sj:textfield
																name="onlineApplicationDetails.tempararyAddress.streetName"
																id="streetName1"
																cssClass="form-control input-medium as-inputl"
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
														<label class="control-label col-md-4"> State : </label>
														<div class="col-md-6">
															<s:select id="state1" list="statesList"
																cssClass="form-control input-medium" listKey="stateCode"
																listValue="stateName" headerKey=""
																headerValue="- Select -"
																name="onlineApplicationDetails.tempararyAddress.state" />
														</div>
													</div>
												</div>
												<div class=" col-md-6">
													<div class="form-group">
														<label class="control-label col-md-4"> Pincode : </label>
														<div class="col-md-6">
															<sj:textfield
																name="onlineApplicationDetails.tempararyAddress.postalCode" id="pincode1"
																cssClass="numeric form-control input-medium as-inputl" maxlength="6"></sj:textfield>
														</div>
													</div>
												</div>
											</div>
											<div class="clearfix">&nbsp;</div>
											<div class="form-actions fluid">
												<div class="col-md-offset-5 col-md-6">
													<sj:submit cssClass="btn blue " value="Register" 
														indicator="indicator" validate="true" onBeforeTopics="applicationInformation"
														formIds="addStudentOfflineInfo" targets="onlineAppCont" />
												</div>
											</div>
										</div>
									</s:form>
								</s:if>
								<s:else>
									<div class="alert alert-info">Currently there are no
										admissions opened for classes.</div>
								</s:else>
							</s:if>
							<s:else>
								<div class="alert alert-info">Currently there are no
									admissions opened.</div>
							</s:else>
							<div id="studentsImgDiv"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
<script src="${pageContext.request.contextPath}/scripts/newScripts/jquery-ui-1.10.4.custom.js" type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/formElementScript.js"></script>
<script src="${pageContext.request.contextPath}/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script type="text/javascript"	src="${pageContext.request.contextPath}/scripts/newScripts/form-components.js"></script>
<script  type="text/javascript" src="${pageContext.request.contextPath}/plugins/bootstrap/bootstrap-maxlength/boostrap-maxlength.min.js"></script> 
<script src="${pageContext.request.contextPath}/scripts/newScripts/form-wizard.js" type="text/javascript"></script>
<script type="text/javascript"  src="${pageContext.request.contextPath}/scripts/newScripts/app.js"></script>
<script  src="${pageContext.request.contextPath}/plugins/select2/select2.min.js" type="text/javascript" ></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/plugins/uniform/jquery.uniform.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/plugins/bootstrap-switch/static/js/bootstrap-switch.js" type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/common/jquery.autoCheck.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/validation/jquery.validate.js"></script>
<script type="text/javascript"	src="${pageContext.request.contextPath}/scripts/common/jquery.alphanumeric.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/jQuery.url.js"></script>
 
 <div class="footer">
		<jsp:include page="/common/footer.jsp" />
</div>
</body> 
	
<script type="text/javascript">
$(document).ready(function() {
	FormComponents.init();
	FormAdvanced.init();
$("input:checkbox, input:radio:not('.toggle')").uniform();
$('.numeric').numeric();
$.destroyTopic('applicationInformation');
$.subscribe('applicationInformation',function(event, data) {
	var transportMode = $("input[name='onlineApplicationDetails.transportMode']:checked").val();
	if (isNonEmpty($('input.fileName').val())) {
		var filename = $('input.fileName').val().toLowerCase();
		if (filename.lastIndexOf(".jpg") == -1 && filename.lastIndexOf(".png") == -1 && filename.lastIndexOf(".jpeg") == -1) {
			alert("File not acceped. Please upload your file in jpg or jpeg or  png");
			event.originalEvent.options.submit = false;
		}
	}
	if (isNonEmpty(transportMode) && 'T' == transportMode) {
		$('select#routeId').addClass('required');
		$('select#boardingId').addClass('required');
		$('select#vehicleId').addClass('required');
	} else {
		$('select#routeId').removeClass('required');
		$('select#boardingId').removeClass('required');
		$('select#vehicleId').removeClass('required');
	}
});

});
function getSubCastDetailsByCast(selectBox) {
	var castId = selectBox.value;
	var url = jQuery.url.getChatURL("/admin/ajaxStudentSubCastDetailsByCastDetails.do");
	if (castId.length == 0) {
		alert("!Oops select Cast.");
	} else {
		$("#resultsDiv2").html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		var pars = "personVo.castId=" + castId;
		$.ajax({
			url : url,
			cache : false,
			data : pars,
			success : function(html) {
				$("#resultsDiv2").html(html);
				document.getElementById('resultsDiv2').style.display = "block";
				$('select#subCastName').attr("tabindex", 13);
			}
		});
	}
}
function viewPopupStudensCapture() {
	$.ajax({
		url : jQuery.url.getChatURL("/signup/ajaxCapturePhotoForAdmissions.do"),
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
function validateMobNumber(txtMobId) {
	var mob = /^(\+91-|\+91|0)?\d{10}$/;
	
	if(isNonEmpty(txtMobId))
	{
		if (mob.test($.trim(txtMobId)) == false) {
			alert("Please enter valid mobile number.");
			$("#mobileNumber").val('');
			$("#mobileNumber").focus();
			return false;
		}else if(txtMobId.length == 10){
			$("#mobileNumber").val("+91-"+txtMobId);
			return true;
		}else if (!(txtMobId.length == 14)) {
			alert("The phone number is the wrong length. \nPlease enter 10 digit mobile no.");
			$("#mobileNumber").val("");
			$("#mobileNumber").focus();
			return false;
		}else if((txtMobId.length == 14)) {
			return true;
		}
	}
}
function formatMobileNumber(object) {
    var phoneString = object.value;
    if (phoneString.length == 1) {
            phoneString = "+91-" + phoneString;
    }
    object.value = phoneString;
}

$('.numericDot').numeric({
	allow : "."
});
function FillBilling(offlineApplicationForm) {
	if (offlineApplicationForm.billingtoo.checked == true) {
		offlineApplicationForm.streetName1.value = offlineApplicationForm.streetName.value;
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
$.subscribe('doPrintStudentAppFee', function(event, data) {
	$('#stuAppFeePrintReport').submit();
	$.destroyTopic('doPrintStudentAppFee');
});
function isNonEmpty(str) {
    if (typeof str == 'undefined' || str == '' || str == null || str == 'null') {
        return false;
    }
    return true;
}


</script>
</html>