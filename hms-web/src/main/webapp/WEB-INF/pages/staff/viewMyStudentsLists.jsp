<%@ include file="/common/taglibs.jsp"%>
<jsp:include page="/common/messages.jsp"></jsp:include>
<div id="sendEmailDiv">
<s:if
	test="%{viewStudentPersonAccountDetailsList != null && !viewStudentPersonAccountDetailsList.isEmpty()}">
	<s:if test="%{(smsAlloted == 0) || (smsAlloted <= smsCnt)}">
		<div style="color: red;" class="alert alert-info">
			You have been used all your allotted
			<s:property value="smsAlloted" />
			SMS. Please contact EazySchool support team (080-46620999) to recharge your SMS.
		</div>
	</s:if>
	<table
		class="table table-striped table-bordered table-hover table-full-width"
		id="sample_2">
		<thead>
			<tr>
				<th>
					Student 
				</th>
				<th>
					Student Roll
				</th>
				<th>
					Student Name
				</th>
			<s:if test='%{!(user.isSchoolTeacher=="Y"  && #session.parentMobileNoVisibleToTeacher == "N" )}'>
				<s:if test="%{customer.checkMobileService == true}">
					<th>
						Parent Mobile
					</th>
				</s:if>
				</s:if>
				<s:if test="%{customer.checkEmailService == true}">
					<th>
						E-mail
					</th>
				</s:if>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="viewStudentPersonAccountDetailsList">
				<tr>
					<td>
						<s:if
							test="%{newUser.profileImage.adjustedAttachmentFilePath != null && !newUser.profileImage.adjustedAttachmentFilePath.isEmpty()}">
							<img
								src='<c:url value="${newUser.profileImage.adjustedAttachmentFilePath}"/>'
								alt='<s:property  value="ViewStudentPersonAccountDetails.personFullName" />'
								align="left" height="25px" width="50px" border="0" />
						</s:if>
						<s:else>
							<img src='<c:url value="${adjustedAttachmentFilePath}"/>'
								alt='<s:property  value="viewStudentDetails.personName" />'
								align="left" height="25px" width="50px" border="0" />
						</s:else>
					</td>
					<td>
						<s:property value="rollNumber" />
					</td>
					<td>
						<a data-toggle="modal" href="#responsive"
							onclick="javascript:PopupStudentNameDetials(<s:property value="%{accountId}" />,<s:property value="tempId1"/>,'<s:property value="anyTitle"/>');"><s:property
								value="firstName" />&nbsp;<s:property value="lastName" /></a>
					</td>
					<s:if test='%{!(user.isSchoolTeacher=="Y"  && #session.parentMobileNoVisibleToTeacher == "N" )}'>
					<s:if test="%{customer.checkMobileService == true}">
					<td>
					<s:if test="%{mobileNumber != null && !mobileNumber.isEmpty()}">
						<s:property value="mobileNumber" />&nbsp;&nbsp;
						<s:if test='%{(tempBoolean == true || user.isOnlySchoolHod =="Y") && (smsAlloted != 0) && (smsAlloted > smsCnt) }'>
							<a data-toggle="modal" href="#PopupSendSmsDiv" class="btn green btn-xs"
								onclick="javascript:PopupSendSms(<s:property value="accountId" />,'<s:property value="mobileNumber" />',<s:property value="tempId1"/>,'<s:property
									value="firstName" />','<s:property value="anyTitle"/>');">
								Send SMS </a>
						</s:if>
						</s:if>
						<s:else>
							-
						</s:else>
					</td>
					</s:if>
					</s:if>
					<s:if test="%{customer.checkEmailService == true}">
						<td>
						<s:if test="%{parentEmail != null && !parentEmail.isEmpty()}">
							<s:property value="parentEmail" />&nbsp;&nbsp;
							<s:if test='%{(tempBoolean == true || user.isOnlySchoolHod =="Y")}'> 
								<a data-toggle="modal" href="#PopupSendEmailDiv" class="btn green btn-xs"
									onclick="javascript:PopupSendEmail(<s:property value="accountId" />,'<s:property value="parentEmail" />',<s:property value="tempId1"/>,'<s:property
										value="firstName" />','<s:property value="anyTitle"/>');"> Send E-mail</a>
							</s:if>
						</s:if>
						<s:else>
							-
						</s:else>
						</td>
					</s:if>
				</tr>
			</s:iterator>
		</tbody>
	</table>
</s:if>
<s:else>
 <!-- <div class="alert alert-success">
  <button class="close" data-dismiss="alert"></button>
  <strong>Message sent successfully. </strong>
 </div>	
 -->
 <div class="alert alert-info">
      Currently there are no Students
 </div>
</s:else>
<s:elseif
	test="%{classStudentsList != null && !classStudentsList.isEmpty()}">
	<table
		class="table table-striped table-bordered table-hover table-full-width"
		id="sample_4">
		<thead>
			<tr>
				<th>
					Student Image
				</th>
				<th>
					Student 
				</th>
				<th>
					Student Name
				</th>
				<s:if test="%{customer.checkMobileService == true}">
				<th>
					Parent Mobile
				</th>
				</s:if>
				<s:if test="%{customer.checkEmailService == true}">
				<th>
					E-mail
				</th>
				</s:if>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="classStudentsList">
				<tr>
					<td>
						<s:if
							test="%{newUser.profileImage.adjustedAttachmentFilePath != null && !newUser.profileImage.adjustedAttachmentFilePath.isEmpty()}">
							<img
								src='<c:url value="${newUser.profileImage.adjustedAttachmentFilePath}"/>'
								alt='<s:property  value="ViewStudentPersonAccountDetails.personFullName" />'
								align="left" height="25px" width="50px" border="0" />
						</s:if>
						<s:else>
							<img src='<c:url value="${adjustedAttachmentFilePath}"/>'
								alt='<s:property  value="viewStudentDetails.personName" />'
								align="left" height="25px" width="50px" border="0" />
						</s:else>
					</td>
					<td>
						<s:property value="rollNumber" />
					</td>
					<td>
						<a data-toggle="modal" href="#responsive"
							onclick="javascript:PopupStudentNameDetials(<s:property value="%{accountId}" />,<s:property value="tempId1"/>,'<s:property value="anyTitle"/>');"><s:property
								value="firstName" />&nbsp;<s:property value="lastName" /> </a>
					</td>
					<s:if test="%{customer.checkMobileService == true}">
					<td>
					<s:if test="%{mobileNumber != null && !mobileNumber.isEmpty()}">
						<s:property value="mobileNumber" />
						<s:if test='%{tempBoolean == true || user.isOnlySchoolHod =="Y"}'>
							<a data-toggle="modal" href="#PopupSendSmsDiv" class="btn green btn-xs"
								onclick="javascript:PopupSendSms(<s:property value="accountId" />,'<s:property value="mobileNumber" />',<s:property value="tempId1"/>,'<s:property
									value="firstName" />','<s:property value="anyTitle"/>');">
								Send SMS </a>
						</s:if>
						</s:if>
						<s:else>
							-
						</s:else>
					</td>
					</s:if>
					<s:if test="%{customer.checkEmailService == true}">
						<td>
						<s:if test="%{parentEmail != null && !parentEmail.isEmpty()}">
							<s:property value="parentEmail" />&nbsp;&nbsp;
							<s:if test='%{(tempBoolean == true || user.isOnlySchoolHod =="Y")}'> 
								<a data-toggle="modal" href="#PopupSendEmailDiv" class="btn green btn-xs"
									onclick="javascript:PopupSendEmail(<s:property value="accountId" />,'<s:property value="parentEmail" />',<s:property value="tempId1"/>,'<s:property
										value="firstName" />','<s:property value="anyTitle"/>');"> Send E-mail</a>
							</s:if>
						</s:if>
						<s:else>
							-
						</s:else>
						</td>
					</s:if>
				</tr>
			</s:iterator>
		</tbody>
	</table>
</s:elseif>
<s:else>
	<div class="alert alert-info">
		Currently there are no Students.
	</div>
</s:else>
</div>
<div id="responsive"></div>
<div id="PopupSendEmailDiv"></div>
<div id="PopupSendSmsDiv"></div>
<script type="text/javascript">
$(document).ready(function() { 
	TableAdvanced.init();
});
function PopupStudentNameDetials(id,studyClassId,anyTitle) {
	var url = jQuery.url.getChatURL("/staff/ajaxDoViewStudentDetails.do");
	if(isNonEmpty(anyTitle)){
		params = "tempId=" + id +"&anyTitle="+anyTitle;
	}else{
		params = "tempId=" + id + "&tempId2="+studyClassId;
	}	
	$('#responsive')
			.html(
					'<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
	$.ajax( {
		url : url,
		cache : false,
		data : params,
		success : function(html) {
			$("#responsive").html(html);
		}
	});
}

function PopupSendEmail(tempId,tempString,studyClassId,stuFirstName,anyTitle) {
	var url = jQuery.url.getChatURL("/staff/ajaxDoSendEmail.do");
	$('#PopupSendEmailDiv')
			.html(
					'<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
	$.ajax( {
		url : url,
		cache : false,
		data : "tempId=" + tempId + "&tempString=" + tempString + "&tempId1="+studyClassId + "&stuFirstName="+stuFirstName+"&anyTitle="+anyTitle,
		success : function(html) {
			$("#PopupSendEmailDiv").html(html);
		}
	});
}

function PopupSendSms(tempId,tempString,studyClassId,stuFirstName,anyTitle) {
	var url = jQuery.url.getChatURL("/staff/ajaxDoSendSms.do");
	$('#PopupSendSmsDiv')
			.html(
					'<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
	$.ajax( {
		url : url,
		cache : false,
		data : "tempId=" + tempId + "&tempString=" + tempString + "&tempId1="+studyClassId + "&stuFirstName="+stuFirstName+"&anyTitle="+anyTitle,
		success : function(html) {
			$("#PopupSendSmsDiv").html(html);
		}
	});
}
</script>

