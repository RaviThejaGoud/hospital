
<%@page import="java.util.List"%><%@ include file="/common/taglibs.jsp"%>
<div class="grid_13">
	<div id="staffDetailsForAdmin" class="commomnTabs">
		<jsp:include page="/common/messages.jsp"></jsp:include>
	</div>
	<s:form id="selectStudentForm"  theme="css_xhtml" cssStyle="margin-left:18px;">
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
					<div class="left grid_8">
						<div class="grid_7">
							<div class="grid_3">
								<label class="labelRight">
									Staff Name:
								</label>
							</div>
							<div class="grid_4">
								<s:property value="viewStaffPersonAccountDetails.personFullName" />
							</div>
						</div>
						<div class="grid_6">
							<div class="grid_3">
								<label class="labelRight">
									Gender:
								</label>
							</div>
							<div class="grid_3">
								<s:property value="viewStaffPersonAccountDetails.genderStr" />
							</div>
						</div>
						
						<div class="grid_6">
							<div class="grid_3">
								<label class="labelRight">
									Date of Birth:
								</label>
							</div>
							<div class="grid_3">
								<s:property value="viewStaffPersonAccountDetails.dateOfBirthStr" />
							</div>
						</div>
						
						<div class="grid_6">
							<div class="grid_3">
								<label class="labelRight">
									Date of Joining:
								</label>
							</div>
							<div class="grid_3">
								<s:property value="viewStaffPersonAccountDetails.staffDateOfJoing" />
							</div>
						</div>
					</div>
					<div class="grid_4">
						<img
							src='<c:url value="${viewStaffPersonAccountDetails.adjustedAttachmentFilePath}"/>'
							alt='<s:property  value="viewStaffPersonAccountDetails.personFullName" />'
							border="0" height="102px;" />
					</div>
					
					<div id="commonStep13">
	<fieldset id="stepAttendance" >
		<div class="grid_14">
			<div id="editClassSubjects" class="grid_13">
						<s:if test="%{payrollTypesList!= null && !payrollTypesList.isEmpty()}">
					         <s:iterator value="payrollTypesList">
					         <s:hidden name="payrollName%{id}" value="%{payrollName}" />
					         	<div class="grid_12 row">
										<div class="grid_5" >
											<s:property value="payrollDescription" />
										</div>
										<!--<div class="grid_3" >
											<s:checkbox name="chkBoxSelectedIds" fieldValue="%{id}" value="" theme="simple" />
											<s:property value="payrollName" />
										</div>-->
										<div class="grid_4">
											
											<s:if test="%{payrollType != 'BS'}">
											<sj:textfield name="percentage%{id}" value="" tabindex="2" cssClass="textfield numeric" maxlength="10" ></sj:textfield>
											   <span class="hintMessage">(Enter %)</span>
											</s:if>
											<s:else>
												<sj:textfield name="basicSalary" value="" tabindex="2" cssClass="textfield numeric" maxlength="10" ></sj:textfield>
											</s:else>
										</div>
									</div>
							</s:iterator>
						</s:if>
						</div>
						</div>
						</fieldset>
						</div>
					<div class="grid_6">
											
						<div class="grid_6">
							&nbsp;
						</div>
						<div class="grid_6">
							<s:if test='%{(#session.academicYear == null || #session.academicYear.isEmpty()) || (#session.previousYear=="N")}'>
								<sj:submit   cssClass="submit small" value="Save"
									targets="payrollTypesSettings" indicator="indicator" formIds="editStaffDetails"/>
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
			$('.numeric').numeric();			
			if ($('#tabNavigation ul li').hasClass("selected").toString()) {
				genericAjaxCall($('#tabNavigation ul li a').attr('id'), $(
						'#tabNavigation ul li a').attr('type'), $(
						'#tabNavigation ul li a').attr('class'));
			}
			
			var eremove=$('#staffDetailsForAdmin').find('span:first');
			eremove.removeClass('close1');
			$('.numeric').numeric();	
			//calPercentage();			 	
		});
function getStaffByRoleId(roleName) {
	var params = "tempString=" + roleName;
	$('#payrollTypesSettings')
			.html(
					'<div align="center"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
	$.ajax( {
		url : jQuery.url.getChatURL("/staff/ajaxDoGetStaffByRoleForPayroll.do"),
		cache : true,
		data : params,
		success : function(response) {
			$('#payrollTypesSettings').html(response);
		}
	});
}
function getStaffDetails(staffId) {
	var role = $('#staffRole').val();
	if (isNonEmpty(role)) {
		var params = "tempString=" + role + "&tempId=" + staffId;
		$('#payrollTypesSettings')
				.html(
						'<div align="center"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		$.ajax( {
			url : jQuery.url.getChatURL("/staff/ajaxDoGetStaffByRoleForPayroll.do"),
			cache : true,
			data : params,
			success : function(response) {
				$('#payrollTypesSettings').html(response);
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
function calPercentage(percentage){
 var payrollId= $('.payrollId').val();
 alert(payrollId);
	$('#percentageId').keyup(function() {
	});
}
</script>