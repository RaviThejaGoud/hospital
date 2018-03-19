<%@ include file="/common/taglibs.jsp"%>
<div class="form-body">
<span id="staffNmaeSpan" class="<s:property value='plTitle'/>"></span>
	<s:form id="selectStudentForm" theme="simple"
		cssClass="form-horizontal">
		<div class="row">
			<div id="staffDetailsForAdmin">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> Select Role : </label>
						<div class="col-md-5">
							<s:select list="staffRoles" id="staffRole"
								name="viewStaffPersonAccountDetails.roleName" theme="simple"
								onchange="javascript:getStaffByRoleId(this.value,'N');" tabindex="1"
								cssClass="form-control">
							</s:select>
						</div>
					</div>
				</div>
			</div>
			<s:if test="%{staffsList != null && !staffsList.isEmpty()}">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> Select Staff : </label>
						<div class="col-md-5">
							<s:select list="staffsList" id="staffName"
								name="viewStaffPersonAccountDetails.staffId" listKey="staffId"
								listValue="personFullName" headerKey="" headerValue="- Select -"
								tabindex="2" cssClass="form-control" theme="simple"
								onchange="javascript:getStaffDetails(this.value);">
							</s:select>
						</div>
					</div>
				</div>
			</s:if>
			<s:else>
				<span class="label label-danger"> NOTE : </span>&nbsp;Currently there are no staff assign to this particular Role.
		</s:else>
		</div>
	</s:form>
	<hr />
	<div>
	 <div id="removeImageDiv"></div>
		<s:if test='%{plTitle != ""}'>
			<jsp:include page="/common/messages.jsp"></jsp:include>
		</s:if>
		<div id="messageDiv"> </div>
		<s:if test="%{viewStaffPersonAccountDetails != null}">
			<s:form action="ajaxEditStaff" theme="simple"
				id="editStaffDetailsForm" method="post"
				enctype="multipart/form-data" namespace="/staff"
				cssClass="form-horizontal">
				<s:hidden name="tempId" value="%{viewStaffPersonAccountDetails.staffId}" />
				<s:hidden name="tempString" value="%{viewStaffPersonAccountDetails.roleName}" />
				<s:hidden name="customerImage" id="customerImage"></s:hidden>

				<s:if
					test='%{viewStaffPersonAccountDetails.roleName != "ROLE_ADMIN" && !(viewStaffPersonAccountDetails.roleName == "ROLE_STOREKEEPER" && storeAssigned )}'>
					<div class="row">
						<div class="col-md-6">
							<div class="form-group">
								<label class="control-label col-md-4"> Change Role : </label>
								 <div class="col-md-5">
									<s:if test="%{nonTeachingRoleMap != null && !nonTeachingRoleMap.isEmpty()}">
										<s:select id="nonTeachStaffList" list="nonTeachingRoleMap" name="teachingRoleName" value="viewStaffPersonAccountDetails.roleName" cssClass="form-control"/>
									</s:if>
									<s:elseif test="%{managementRoleMap != null && !managementRoleMap.isEmpty()}">
										<s:select id="managementRoleMapStaffList" list="managementRoleMap" name="teachingRoleName" value="viewStaffPersonAccountDetails.roleName" cssClass="form-control" />
									</s:elseif>
									<s:else>
										<s:select id="staffRoles" list="staffRoles" name="teachingRoleName" value="viewStaffPersonAccountDetails.roleName" cssClass="form-control" />
									</s:else>								
								</div> 
							</div>
						</div>
					</div>
				</s:if>
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4"> <span
								class="required">*</span>First Name :
							</label>
							<div class="col-md-5">
								<sj:textfield name="viewStaffPersonAccountDetails.firstName"
									id="staffFName" tabindex="3"
									cssClass="required form-control" maxlength="30"></sj:textfield>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group ">
							<label class="control-label col-md-4"> Last Name : </label>
							<div class="col-md-5">
								<sj:textfield name="viewStaffPersonAccountDetails.lastName"
									tabindex="4" id="staffLName" cssClass="form-control"
									maxlength="40"></sj:textfield>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<!--<c:if
						test="${viewStaffPersonAccountDetails.roleName == 'ROLE_PRINCIPAL' || viewStaffPersonAccountDetails.roleName == 'ROLE_HOD' || viewStaffPersonAccountDetails.roleName == 'ROLE_TEACHER' || viewStaffPersonAccountDetails.roleName == 'ROLE_FINANCE' || viewStaffPersonAccountDetails.roleName == 'ROLE_TRANSPORT' || viewStaffPersonAccountDetails.roleName == 'ROLE_TRANSPORTFINANCE' || viewStaffPersonAccountDetails.roleName == 'ROLE_LIBRARIAN' || viewStaffPersonAccountDetails.roleName == 'ROLE_HOSTEL' || viewStaffPersonAccountDetails.roleName == 'ROLE_HOSTELFINANCE'}">
						</c:if>
						-->
					<s:if test='%{viewStaffPersonAccountDetails.roleName != "ROLE_AYAH" && viewStaffPersonAccountDetails.roleName != "ROLE_MANAGEMENTTRAINEE" && viewStaffPersonAccountDetails.roleName != "ROLE_DRIVER" && viewStaffPersonAccountDetails.roleName != "ROLE_HELPER" && viewStaffPersonAccountDetails.roleName != "ROLE_CONDUCTOR"
					            && viewStaffPersonAccountDetails.roleName != "ROLE_LABASST" && viewStaffPersonAccountDetails.roleName != "ROLE_PEON"  &&  viewStaffPersonAccountDetails.roleName != "ROLE_PUBLICRELATIONOFFICER" &&  viewStaffPersonAccountDetails.roleName != "ROLE_SWEEPER" && viewStaffPersonAccountDetails.roleName != "ROLE_TYPIST"
					            && viewStaffPersonAccountDetails.roleName != "ROLE_WATCHMAN" && viewStaffPersonAccountDetails.roleName != "ROLE_SYSTEMADMINISTRATOR" && viewStaffPersonAccountDetails.roleName != "ROLE_OTHER" && viewStaffPersonAccountDetails.roleName != "ROLE_DIRECTOR" && viewStaffPersonAccountDetails.roleName != "ROLE_SALES_HEAD"
					            && viewStaffPersonAccountDetails.roleName != "ROLE_SALES_EXECUTIVE" && viewStaffPersonAccountDetails.roleName != "ROLE_COORDINATOR" && viewStaffPersonAccountDetails.roleName != "ROLE_BDM" && viewStaffPersonAccountDetails.roleName != "ROLE_CORRESPONDENCE" && viewStaffPersonAccountDetails.roleName != "ROLE_STAFF_NURSE"  }'>
						<div class="col-md-6">
							<div class="form-group">
								<label class="control-label col-md-4"> User Name : </label>
								<div class="col-md-5">
									<sj:textfield name="viewStaffPersonAccountDetails.username" id="user" disabled="true"
									cssClass="form-control" maxlength="40" readonly="true" tabindex="5"/>
								</div>
							</div>
						</div>
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4"> Date of Birth : </label>
							<div class="col-md-6">
								<div data-date-format="mm/dd/yyyy"
									class="input-group input-medium date date-picker">
									<input type="text" id="date0" readonly=""
										class="form-control fdate" tabindex="6"
										name="viewStaffPersonAccountDetails.dateOfBirth"
										value='<s:property value="viewStaffPersonAccountDetails.dateOfBirthStr"/>' />
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
					</s:if>
				</div>
					<!--  <div class="col-md-6">
						<div class="form-group ">
							<label class="control-label col-md-4"> Staff Number : </label>
							<div class="col-md-5">
								<sj:textfield name="viewStaffPersonAccountDetails.staffNumber"
									id="staffNumberId" tabindex="6" cssClass="form-control staffNumber"
									maxlength="20"></sj:textfield>
							</div>
						</div>
					</div>-->
				
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
									<s:if test='%{viewStaffPersonAccountDetails.gender =="M"}'>
										<input type="radio" class="toggle" checked="checked"
											id="genderStaff" tabindex="7">
										<input type="hidden"
											name="viewStaffPersonAccountDetails.gender"
											value='<s:property value="viewStaffPersonAccountDetails.gender"/>'>
									</s:if>
									<s:elseif test='%{viewStaffPersonAccountDetails.gender=="F"}'>
										<input type="radio" class="toggle" id="genderStaff"
											tabindex="7">
										<input type="hidden"
											name="viewStaffPersonAccountDetails.gender"
											value='<s:property value="viewStaffPersonAccountDetails.gender"/>'>
									</s:elseif>
									<s:else>
										<input type="radio" class="toggle" checked="checked"
											id="genderStaff" tabindex="7">
										<input type="hidden"
											name="viewStaffPersonAccountDetails.gender" value='M'>
									</s:else>
								</div>
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
									style="width: 180px" data-off="warning" data-on="success"
									data-off-label="Un-Married" data-on-label="Married">
									<s:if
										test='%{viewStaffPersonAccountDetails.maritalStatus =="M"}'>
										<input type="radio" class="toggle" checked="checked"
											id="marital" tabindex="8">
										<input type="hidden"
											name="viewStaffPersonAccountDetails.maritalStatus"
											value='<s:property value="viewStaffPersonAccountDetails.maritalStatus"/>'>
									</s:if>
									<s:elseif
										test='%{viewStaffPersonAccountDetails.maritalStatus=="UN"}'>
										<input type="radio" class="toggle" id="marital" tabindex="8">
										<input type="hidden"
											name="viewStaffPersonAccountDetails.maritalStatus"
											value='<s:property value="viewStaffPersonAccountDetails.maritalStatus"/>'>
									</s:elseif>
									<s:else>
										<input type="radio" class="toggle" checked="checked"
											id="marital" tabindex="8">
										<input type="hidden"
											name="viewStaffPersonAccountDetails.maritalStatus" value='M'>
									</s:else>

								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6">
						<s:if test="%{customer.hostelModuleStatus}">
							<div class="col-md-6" id="hostelInputBox">
								<div class="form-group ">
									<label class="control-label col-md-4"> <span
										class="required"></span>Select Hostel Category :
									</label>
									<div class="col-md-5">
										<s:select list="hostelCategoriesList" id="hostelCategoryName"
											name="viewStaffPersonAccountDetails.hostelCategoryId"
											listKey="id" tabindex="11" listValue="categoryName"
											headerKey="" headerValue="- Select Hostel Category -"
											theme="simple">
										</s:select>
									</div>
								</div>
							</div>
						</s:if>
						<div class="form-group">
							<label class="control-label col-md-4"> BioMetric Id : </label>
							<div class="col-md-5" id="bId">
								<sj:textfield name="viewStaffPersonAccountDetails.bioMetricId"
									id="staffBioMetricId" tabindex="12"
									cssClass="numeric form-control staffbiometricStr" maxlength="30"></sj:textfield>
							</div>
						</div>
						<div class="form-group ">
							<label class="control-label col-md-4"> Staff Unique
								Number : </label>
							<div class="col-md-5" id="sNo">
								<sj:textfield
									name="viewStaffPersonAccountDetails.staffUniqueNumber"
									id="staffUniqueNumber" tabindex="13" cssClass="form-control staffUniqueStr"
									maxlength="20"></sj:textfield>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-5"> Upload Staff Image
								: </label>
							<div id="browseImage">
								<img
									src='<c:url value="${viewStaffPersonAccountDetails.adjustedAttachmentFilePath}"/>'
									alt='<s:property  value="viewStaffPersonAccountDetails.personFullName" />'
									border="0" height="102px;" id="imageNotAvailableDiv" />
								<s:if test='%{viewStaffPersonAccountDetails.imageId>0}'>
									<s:url id="removeImage" action="ajaxRemoveStudentAndStaffImage"
										includeParams="all" escapeAmp="false" namespace="/student">
										<s:param name="viewStaffPersonAccountDetails.imageId"
											value="viewStaffPersonAccountDetails.imageId" />
										<s:param name="viewStaffPersonAccountDetails.accountId"
											value="viewStaffPersonAccountDetails.accountId" />
									</s:url>
									<s:div cssClass="btn red"
										cssStyle="   margin-left: -14px; position: absolute;top: 1px;padding:2px;"
										onclick="javascript:confirmToRemoveImage(this,'imageNotAvailableDiv');"
										id='%{removeImage}' title="Delete this image">
										<i class="fa fa-times"></i>
									</s:div>
								</s:if>
							</div>
							<img id="image" border="0" height="102px;" style="display: none" />
							<div class="spaceDiv"></div>
							<div class="col-md-2"></div>
							<div class="col-md-2">
								<a data-toggle="modal" href="#staffImageDiv"
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
				<%-- <div class="row">
					<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4">School Mess :
						</label>
						<div class="col-md-7">
							<div class="make-switch has-switch" data-id="Y" data-value="N"
								style="width: 200px" data-off="warning" data-on="success"
								data-off-label="No" data-on-label="Yes">
								<s:if test='%{staffVo.schoolMess =="Y"}'>
									<input type="radio" class="toggle" checked="checked"
										id="schoolMessId">
									<input type="hidden" name="staffVo.schoolMess"
										value='<s:property value="staffVo.schoolMess"/>'>
								</s:if>
								<s:elseif test='%{staffVo.schoolMess =="N"}'>
									<input type="radio" class="toggle" id="schoolMessId">
									<input type="hidden" name="staffVo.schoolMess"
										value='<s:property value="staffVo.schoolMess"/>'>
								</s:elseif>
								<s:else>
									<input type="radio" class="toggle" checked="checked"
										id="schoolMessId">
									<input type="hidden" name="staffVo.schoolMess" value='N'>
								</s:else>
							</div>
						</div>
						</div>
					</div>
				</div>--%>
				<div class="form-actions fluid">
					<div class="col-md-offset-2 col-md-9">
						<s:if test='%{#session.previousYear == "N"}'>
							<sj:submit cssClass="submitBt btn blue" value="Save"
								targets="staffsContent" formIds="editStaffDetailsForm"
								validate="true" onBeforeTopics="changeStaffInfoFormValidation" />
							<s:url id="doCancelStaff" action="ajaxDoManageStaff"
								includeParams="all" namespace="/staff"></s:url>
							<sj:a href="%{doCancelStaff}" cssClass="btn default"
								targets="mainContentDiv" button="false">Cancel</sj:a>
						</s:if>
					</div>
				</div>
			</s:form>
			<div class="clearfix">&nbsp;</div>
			<div class="clearfix">&nbsp;</div>
			<div class="tabbable tabbable-custom tabbable-full-width">
				<ul class="nav nav-tabs">
					<s:if
						test='%{viewStaffPersonAccountDetails.roleName == "ROLE_TEACHER" || viewStaffPersonAccountDetails.roleName == "ROLE_HOD" || viewStaffPersonAccountDetails.roleName == "ROLE_ADMIN_COORDINATOR"
						|| viewStaffPersonAccountDetails.roleName == "ROLE_PRINCIPAL" || viewStaffPersonAccountDetails.roleName == "ROLE_VICEPRINCIPAL" 
						|| viewStaffPersonAccountDetails.roleName == "ROLE_ASST_TEACHER"}'>
						<li><s:url id="stepEditStaffHistory"
								action="ajaxDoEditStaffHistory" namespace="/staff"
								includeParams="all" escapeAmp="false">
								<s:param value="viewStaffPersonAccountDetails.accountId"
									name="tempId"></s:param>
							</s:url> <sj:a id="stepEditStaffHistory" href="%{stepEditStaffHistory}"
								targets="staffEditContentDiv" data-toggle="tab">Staff History</sj:a>
						</li>

						<li><s:url id="stepStaffClasses"
								action="ajaxStaffClassesAndSubjects" namespace="/admin"
								includeParams="all" escapeAmp="false">
								<s:param value="viewStaffPersonAccountDetails.staffId"
									name="tempId"></s:param>
								<s:param value="viewStaffPersonAccountDetails.roleName"	name="anyId"></s:param>	
							</s:url> <sj:a id="stepStaffClasses" href="%{stepStaffClasses}"
								targets="staffEditContentDiv" data-toggle="tab">Class &amp; Subjects </sj:a>
						</li>
						<li><s:url id="stepStaffPerformance"
								action="ajaxStaffPerformance" namespace="/admin"
								includeParams="all" escapeAmp="false">
								<s:param value="viewStaffPersonAccountDetails.staffId"
									name="tempId"></s:param>
							</s:url> <sj:a id="stepStaffPerformance" href="%{stepStaffPerformance}"
								targets="staffEditContentDiv" data-toggle="tab">Performance</sj:a>
						</li>
					</s:if>
					<%-- <s:else>
						<li><s:url id="stepStaffAttendance"
								action="ajaxGetStaffAttendanceDetails" namespace="/admin">
							</s:url> <sj:a id="stepStaffAttendance" href="%{stepStaffAttendance}"
								targets="staffEditContentDiv" data-toggle="tab">Attendance</sj:a>
						</li>
					</s:else> --%>
					<li><s:url id="viewComplaints" action="ajaxDoSendEmailToStaff"
							namespace="/admin" includeParams="all" escapeAmp="false">
							<s:param value="viewStaffPersonAccountDetails.accountId"
								name="tempId"></s:param>
						</s:url> <sj:a id="viewComplaints" href="%{viewComplaints}"
							targets="staffEditContentDiv" data-toggle="tab">Complaints</sj:a>
					</li>
					<li id="uploadDownloadEvent"><s:url id="urlUploadDownloadDocs"
							action="ajaxDoGetUploadDownloadDocs" namespace="/staff"
							includeParams="all" escapeAmp="false">
							<s:param value="viewStaffPersonAccountDetails.staffId"
								name="tempId"></s:param>
						</s:url> <sj:a href="%{urlUploadDownloadDocs}"
							targets="staffEditContentDiv" cssClass='ajaxify'
							data-toggle="tab">Upload / Download Documents</sj:a></li>
					<li class="active" id="staffpersonalInfo"><s:url
							id="stepPersonal" action="ajaxStaffPersonalInfo"
							namespace="/staff" includeParams="all" escapeAmp="false">
							<s:param value="viewStaffPersonAccountDetails.roleName"
								name="tempString"></s:param>
							<s:param value="viewStaffPersonAccountDetails.staffId"
								name="tempId"></s:param>
						</s:url> <sj:a id="stepPersonal" href="%{stepPersonal}"
							targets="staffEditContentDiv" data-toggle="tab">Personal Info</sj:a>
					</li>
				</ul>
				<div class="tab-content" id="staffEditContentDiv"></div>
			</div>
		</s:if>
	</div>
	<span class="personId" id="<s:property value='anyId'/>"></span>
</div>
<div id="staffImageDiv"></div>
<script type="text/javascript" src="${pageContext.request.contextPath}/plugins/bootstrap-switch/static/js/bootstrap-switch.min.js"></script>
<script type="text/javascript">
$(document).ready(function() { 
	$.destroyTopic('changeStaffInfoFormValidation');
	$('#messageDiv').html("");
	FormComponents.init();
	FormAdvanced.init();
	$("input:checkbox, input:radio:not('.toggle')").uniform();
	
	var userId = '<s:property  value="user.id"/>';
	var accountId = '<s:property  value="viewStaffPersonAccountDetails.accountId"/>';
	
	var imagePath = '<s:property  value="viewStaffPersonAccountDetails.imagePath"/>';
	
	if(userId == accountId)
	{
		if(isNonEmpty(imagePath))
		{
			var imagePath = '<s:property  value="viewStaffPersonAccountDetails.imagePath"/>';
			$('img#userProfileImageDiv').prop('src',"");
			$('img#userProfileImageDiv').prop('src', imagePath+ '?' + Math.random())
			
			$('img#profileImagesDiv').prop('src',"");
			$('img#profileImagesDiv').prop('src', imagePath+ '?' + Math.random())
		}
		else
		{
			var imagePath = '../img/avatar.png';
			$('img#userProfileImageDiv').prop('src',"");
			$('img#userProfileImageDiv').prop('src', imagePath+ '?' + Math.random())
			
			$('img#profileImagesDiv').prop('src',"");
			$('img#profileImagesDiv').prop('src', imagePath+ '?' + Math.random())
			
		}
		/* var userProfileImageDiv =  $('img#userProfileImageDiv').attr("src");
		if(isNonEmpty(userProfileImageDiv) && userProfileImageDiv !=null && userProfileImageDiv != "/images/common/photo_notAvailable.jpg" && userProfileImageDiv != "../img/avatar.png"){
			var imagePath = '<s:property  value="viewStaffPersonAccountDetails.imagePath"/>';
			$('img#userProfileImageDiv').prop('src',"");
			$('img#userProfileImageDiv').prop('src', imagePath+ '?' + Math.random())
		} 
		
		var profileImagesDiv =  $('img#profileImagesDiv').attr("src");
		if(isNonEmpty(profileImagesDiv) && profileImagesDiv !=null && profileImagesDiv != "/images/common/photo_notAvailable.jpg" && profileImagesDiv != "../img/avatar.png"){
			
			
			$('img#profileImagesDiv').prop('src',"");
			$('img#profileImagesDiv').prop('src', imagePath+ '?' + Math.random())
		} */ 
	}
	
	
	$('li#staffpersonalInfo a').click();
	/*$.subscribe('closeExtraForm', function(event, data) {
			$("div#editStaffDetailsInfo").find("div.grid_13").empty();
	});*/
	
	$("#staffUniqueNumber").autoCheck("${pageContext.request.contextPath}/common/ajaxCheckStaffUniqueNumber.do?anyId="+$('span.personId').attr('id'),{
		minChars : 1,
		min : "no",
	});
	
	$("#staffBioMetricId").autoCheck("${pageContext.request.contextPath}/common/ajaxCheckBioMetricId.do?anyId="+$('span.personId').attr('id'),{
		minChars : 1,
		min : "no",
	});
	
	  var imagePath = '<s:property value="viewStaffPersonAccountDetails.adjustedAttachmentFilePath"/>';
		if(isNonEmpty(imagePath) && imagePath !=null && imagePath != "/images/common/photo_notAvailable.jpg"){
			$('#imageNotAvailableDiv').prop('src', '<c:url value="${viewStaffPersonAccountDetails.adjustedAttachmentFilePath}"/>' + '?' + Math.random())
		} 
		
	var selectedStaff=$("span#staffNmaeSpan").attr("class");
	if(selectedStaff !=""){
		getStaffByRoleId(selectedStaff,"Y");
		
	}
	$('.staffUniqueStr').alphanumeric();
	$('.staffNumber').alphanumeric();
});
$.subscribe('changeStaffInfoFormValidation', function(event, data) {
		var filename = $('input.fileName').val().toLowerCase();
		if (isNonEmpty($("input.fileName").val())){
			if(filename.lastIndexOf(".jpg") == -1 && filename.lastIndexOf(".png") == -1  &&  filename.lastIndexOf(".jpeg") == -1){
				alert("File not acceped. Please upload your file in jpg or jpeg or  png");
				event.originalEvent.options.submit = false;
			}
		}
		var staffUniNum=$('input.staffUniqueStr').parent('div').parent('div').parent('div#sNo').find('p.word-taken').html();
		//alert(staffUniNum);
		if(staffUniNum=='Already taken!!!'){
    	    event.originalEvent.options.submit=false;
    	    $('input.staffUniqueStr').val('');
         }
         var staffBioNum=$('input.staffbiometricStr').parent('div').parent('div').parent('div#bId').find('p.word-taken').html();
 		if(staffBioNum=='Already taken!!!'){
     	    event.originalEvent.options.submit=false;
     	    $('input.staffbiometricStr').val('');
          }
	});
	
	
function getStaffByRoleId(roleName,isFrom) {
	var params = "tempString=" + roleName;
	$('#staffsContent')
			.html(
					'<div align="center"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
	$.ajax( {
		url : jQuery.url.getChatURL("/staff/ajaxDoGetStaffByRole.do"),
		cache : true,
		data : params,
		success : function(response) {
			$('#staffsContent').html(response);
			if(isFrom=="Y"){
				$('#messageDiv').html("<div class='alert alert-success'><button class='close' data-dismiss='alert'></button> <strong>Staff details updated successfully<strong>.</div>");	
			}
		}
	});
}
function getStaffDetails(staffId) {
	var role = $('#staffRole').val()
	if (isNonEmpty(role)) {
		var params = "tempString=" + role + "&tempId=" + staffId;
		$('#staffsContent')
				.html(
						'<div align="center"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		$.ajax( {
			url : jQuery.url.getChatURL("/staff/ajaxDoGetStaffByRole.do"),
			cache : true,
			data : params,
			success : function(response) {
				$('#staffsContent').html(response);
			}
		});
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
						
						var userId = '<s:property  value="user.id"/>';
						var accountId = '<s:property  value="viewStaffPersonAccountDetails.accountId"/>';
						
						if(userId == accountId)
						{
							var profileImagePath = jQuery.url.getChatURL("/img/avatar.png")
							$('img#userProfileImageDiv').prop('src',"");
							$('img#userProfileImageDiv').prop('src', profileImagePath+ '?' + Math.random())
							
							$('img#profileImagesDiv').prop('src',"");
							$('img#profileImagesDiv').prop('src', profileImagePath+ '?' + Math.random())
						}
						
						
						$('.red').remove();
						$('button.close').click();
						$('#removeImageDiv').html("<div class='alert alert-success'><button class='close' data-dismiss='alert'></button> <strong>Image removed successfully.<strong>.</div>");
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
function viewPopupStudensCapture() {
	$.ajax({
		url : jQuery.url.getChatURL("/admin/ajaxCapturePhoto.do"),
		cache : false,
		success : function(html) {
			$("#staffImageDiv").html(html);
		}
	});
}
</script>
