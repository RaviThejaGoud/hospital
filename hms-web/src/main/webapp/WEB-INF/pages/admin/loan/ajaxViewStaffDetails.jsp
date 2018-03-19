<%@ include file="/common/taglibs.jsp"%>
<div class="grid_13">
	<div id="staffDetailsForAdmin" class="commomnTabs">
		<jsp:include page="/common/messages.jsp"></jsp:include>
	</div>
	<s:form id="selectStaffLoanForm2"  theme="css_xhtml" cssStyle="margin-left:18px;">
	<div class="grid_6">
		<div class="tableactions">
			<div class="grid_2 alpha">
				<label class="right">
					Select Role:
				</label>
			</div>
				<s:select list="staffRoles" id="staffRole"
				name="viewStaffPersonAccountDetails.roleName" theme="css_xhtml"
				onchange="javascript:getStaffByRoleId(this.value);" tabindex="1">
			</s:select>
		</div>
	</div>
   <div class="grid_6">
	<s:if test="%{staffsList != null && !staffsList.isEmpty()}">
		<div class="tableactions">
			<div class="grid_2 alpha">
				<label class="right">
					Select Staff:
				</label>
			</div>
				<s:select list="staffsList" id="staffName"
					name="viewStaffPersonAccountDetails.staffId" listKey="staffId"
					listValue="personFullName" headerKey="" headerValue="- Select -"
					theme="css_xhtml" tabindex="2"
					onchange="javascript:getStaffDetails(this.value);">
				</s:select>
		</div>
	</s:if>
</div>

<s:else>
		<div class="noteFont grid_12">
			<br />
			<div class="grid_1">
		 		<span class="noteMassage"> Note :</span>
		 	</div>
		 	<div class="grid_11">
		 		Currently there are no staff assign to this particular Role.
		 	</div>
		</div>
</s:else>
</s:form>
</div>
<div class="grid_13">&nbsp;</div>
<div class="grid_12 commomnTabs">
	<s:if test="%{viewStaffPersonAccountDetails != null}">
		<div id="tabWrapper13">
			<s:form action="ajaxAddPayrollSettingsForStaff" theme="css_xhtml"
				id="editStaffDetails" method="post" enctype="multipart/form-data" namespace="/admin">
				<s:hidden name="tempId"
					value="%{viewStaffPersonAccountDetails.staffId}" />
				<s:hidden name="tempString"
					value="%{viewStaffPersonAccountDetails.roleName}" />
				<div class="grid_12">
					<div class="left grid_6">
						<div class="grid_6">
							<div class="grid_6">
								<div class="grid_6">
									<label>
										Staff Name: <s:property value="viewStaffPersonAccountDetails.personFullName"/>
									</label>
								</div>
								
							</div>
							
						</div>
						<div class="grid_6">
							<div class="grid_6">
								<label>
									Gender: <s:property value="viewStaffPersonAccountDetails.genderStr"/>
								</label>
							</div>
						</div>
						<div class="grid_6">
							<div class="grid_6">
								<label>
									Date of Birth: <s:property value="viewStaffPersonAccountDetails.dateOfBirthStr"/>
								</label>
							</div>
						</div>
						<div class="grid_6">
							<div class="grid_6">
								<label>
									Date of Joining: <s:property value="viewStaffPersonAccountDetails.staffDateOfJoing"/>
								</label>
							</div>
						</div>
						
						<div class="grid_6">
							<img
								src='<c:url value="${viewStaffPersonAccountDetails.adjustedAttachmentFilePath}"/>'
								alt='<s:property  value="viewStaffPersonAccountDetails.personFullName" />'
								border="0" height="102px;" />
						</div>
						
					</div>
					
					<div id="commonStep13">
	<fieldset id="stepAttendance" >
		<div class="grid_14">
			<div id="staffDetailsId" class="grid_13">
					<s:if test="%{objectList!= null && !objectList.isEmpty()}">
							<div class="grid_12 th">
									<div class="grid_3">
										Check For %
									</div>
									<div class="grid_3">
										Allowance
									</div>
									<div class="grid_2">
										Allowance Value Info
									</div>
									
									
					         </div>
					         <s:iterator value="objectList">
					         
					         <s:hidden name="payrollName%{id}" value="%{payrollName}" />
					         	<div class="grid_12 row">
										
										<div class="grid_3" >
											<s:property value="payrollDescription" />
										</div>
										<div class="grid_3" >
										
										<s:checkbox name="chkBoxSelectedIds" fieldValue="%{id}" value="" theme="simple" />
											
											<s:property value="payrollName" />
										</div>
										<div class="grid_2">
											<sj:textfield name="percentage%{id}"  value="" tabindex="2" cssClass="textfield " maxlength="3"></sj:textfield>
										</div>
								</div>
							</s:iterator>
						</s:if>
						
						<s:if test="%{payrollTypesList!= null && !payrollTypesList.isEmpty()}">
							<div class="grid_12 th">
									<div class="grid_3">
										Check For %
									</div>
									<div class="grid_3">
										Deduction
									</div>
									<div class="grid_2">
										Deduction Value Info
									</div>
									
									
					         </div>
					         <s:iterator value="payrollTypesList">
					         <s:hidden name="payrollName%{id}" value="%{payrollName}" />
					         	<div class="grid_12 row">
										
										<div class="grid_3" >
											<s:property value="payrollDescription" />
										</div>
										<div class="grid_3" >
											<s:checkbox name="chkBoxSelectedIds" fieldValue="%{id}" value="" theme="simple" />
											<s:property value="payrollName" />
										</div>
										<div class="grid_2">
											<sj:textfield name="percentage%{id}" value="" tabindex="2" cssClass="textfield " maxlength="3" ></sj:textfield>
										</div>
								</div>
							</s:iterator>
						</s:if>
						</div>
						</div>
						</fieldset>
						</div>
					<div class="grid_6">
						
						
						
						

						<!--<s:checkboxlist name="chkBoxSelectedIds" required="true"
							cssClass="checkbox required" list="studySubjectList" listKey="id"
							theme="ems" listValue="name" />
							
						
						-->
						
						
						
						
						<div class="grid_6">
							&nbsp;
						</div>
						<div class="grid_6">
							<s:if test='%{(#session.academicYear == null || #session.academicYear.isEmpty()) || (#session.previousYear=="N")}'>
								<sj:submit   cssClass="submit small" value="Save"
									targets="staffLoanSettings" formIds="editStaffDetails"
									onBeforeTopics="changeStaffInfoFormValidation" />
							</s:if>
						</div>
					</div>
				</div>
			</s:form>
			
			
		</div>
	</s:if>
</div>
<script type="text/javascript">
$(document).ready(
		function() {
			commonLoadTab();
						
			if ($('#tabNavigation ul li').hasClass("selected").toString()) {
				genericAjaxCall($('#tabNavigation ul li a').attr('id'), $(
						'#tabNavigation ul li a').attr('type'), $(
						'#tabNavigation ul li a').attr('class'));
			}
			$.subscribe('changeStaffInfoFormValidation', function(event, data) {
			  /*// alert($('div.staffDetailsForAdmin').children('div.block').children('div.message success').find('span.close1').size());*/
			    $('#viewStaffPersondetails').hide();
				if ($('#editStaffDetails').valid()) {
					return true;
				} 
			 	else
					return false;
			});
			var eremove=$('#staffDetailsForAdmin').find('span:first');
			eremove.removeClass('close1');
		});
function getStaffByRoleId(roleName) {
	var params = "tempString=" + roleName;
	$('#staffLoanSettings')
			.html(
					'<div align="center"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
	$.ajax( {
		url : jQuery.url.getChatURL("/staff/ajaxDoGetStaffByRole.do"),
		cache : true,
		data : params,
		success : function(response) {
			$('#staffLoanSettings').html(response);
		}
	});
}
function getStaffDetails(staffId) {
	var role = $('#staffRole').val()
	if (isNonEmpty(role)) {
		var params = "tempString=" + role + "&tempId=" + staffId;
		$('#staffLoanSettings')
				.html(
						'<div align="center"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		$.ajax( {
			url : jQuery.url.getChatURL("/staff/ajaxDoGetStaffByRole.do"),
			cache : true,
			data : params,
			success : function(response) {
				$('#staffLoanSettings').html(response);
			}
		});
	}
}
function changeStaffStatus(type) {
     if($('input#checkedStaff').is(':checked')){
         $('#staffStatus').show(); 
     }
     else{
        $('#staffStatus').hide();
     }
}
</script>