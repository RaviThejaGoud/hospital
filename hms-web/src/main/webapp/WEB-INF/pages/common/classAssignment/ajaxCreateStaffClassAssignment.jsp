<%@ include file="/common/taglibs.jsp"%>
<s:if test="%{classAssignment.id !=0}">
<div data-width="760" class="modal fade modal-overflow in"
	style="display: block; width: 900px; margin-left: -379px; margin-top: 150px;">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true"></button>
		<h4 class="modal-title">
		     Edit Class Assignment
		</h4>
	</div>
	<div class="modal-body">
	</s:if>
<div class="form-body">

<span id="startDateSpan" class="<s:property value='startDate'/>"></span>
<span id="endDateSpan" class="<s:property value='endDate'/>"></span>
<span id="toDateSpan" class="<s:property value='toDate'/>"></span>
<span id="admissionAcademicyear" class="<s:property value='#session.admissionAcademicYearId'/>"></span>
<span id="usrAcadmicId" class="<s:property value='userAcademicYearId'/>"></span>


	<s:form action="ajaxAddStaffClassAssignment"
		id="createStaffAssignment" method="post" theme="simple" namespace="/admin" enctype="multipart/form-data"
		cssClass="form-horizontal">
		<s:hidden id="classSubject" name="anyTitle"></s:hidden>
		<s:hidden name="classAssignment.id" id="assignmentId" />
		<s:hidden name="balance" id="anyId" value=""></s:hidden>
		<s:if test="%{classAssignment.id !=0}">
			<s:hidden name="chkBoxSelectedIds" value="%{classSectionId}"></s:hidden>
			<s:hidden name="classAssignment.subjectId" id="subjectId"></s:hidden>
		</s:if>
		<div id="classSubjectDiv" style="display: none;">
		<%-- <s:if test='%{user.isOnlySchoolAdmin == "Y" || user.isSchoolPrincipal=="Y"}'>
			<s:if test="%{tempList2 != null && !tempList2.isEmpty()}">
				<div class="form-group">
					<label class="control-label col-md-3">
						<span class="required"> * </span>Select Subject :
					</label>
					<div class="col-md-5">
						<s:select list="tempList2"
							name="classAssignment.subjectId" listKey="id" id="subjectId"
							listValue="name" headerKey=""
							headerValue="- Select Subject -" theme="simple" onchange="javascript:getClassSubjects(this.value);"
							cssClass="required form-control input-medium">
						</s:select>
					</div>
				</div>
			</s:if>
			<s:else>
				<div class="alert alert-info">
				Currently no class assigned above subject.
				</div>
			</s:else>
		</s:if>
		<s:else>
		<s:if test="%{(smsAlloted == 0) || (smsAlloted <= smsCnt)}">
			<div style="color: red;" class="alert alert-info">
					You have been used all your allotted
					<s:property value="smsAlloted" />
					SMS. Please contact EazySchool support team (080-46620999) to recharge your SMS.
			</div>
		</s:if>	
			<s:if test="%{tempList1 != null && !tempList1.isEmpty()}">
				<div class="form-group">
					<label class="control-label col-md-3">
						<span class="required"> * </span>Select Subject :
					</label>
					<div class="col-md-5">
						<s:select list="tempList1" id="subjectId"
							name="classAssignment.subjectId" listKey="id"
							listValue="name" headerKey=""
							headerValue="- Select Subject -" theme="simple" onchange="javascript:getClassSubjects(this.value);"
							cssClass=" required form-control input-medium">
						</s:select>
					</div>
				</div>
			</s:if>
			<s:else>
				<div class="alert alert-info">
				Currently no subject.
				</div>
			</s:else>
	   </s:else> --%>
		<%-- <div id="classSectionDiv" style="display: none">
				<jsp:include
					page="/WEB-INF/pages/common/classAssignment/ajaxGetClassSubjects.jsp" />
		</div> --%>
		
		<s:if test='%{user.isOnlySchoolAdmin == "Y" || user.isSchoolPrincipal=="Y" || user.isAdminCoordinator=="Y" || user.isSchoolDirector == "Y"}'>
			<s:if test="%{studyClassList != null && !studyClassList.isEmpty()}">
				<div class="form-group">
					<label class="control-label col-md-3"> <span
						class="required"> * </span>Select Class :
					</label>
					<div class="col-md-5">
						<s:select list="studyClassList" name="classAssignment.classSectionId"
							listKey="id" id="studyClassId" listValue="classAndSection" headerKey=""
							headerValue="- Select Class -" theme="simple"
							onchange="javascript:getClassSubjects(this.value);"
							cssClass="required form-control input-medium">
						</s:select>
					</div>
				</div>
			</s:if>
		</s:if>	
		<s:else>
			<s:if test="%{objectList != null && !objectList.isEmpty()}">
				<div class="form-group">
					<label class="control-label col-md-3"> <span
						class="required"> * </span>Select Class :
					</label>
					<div class="col-md-5">
						<s:select list="objectList" name="classAssignment.classSectionId"
							listKey="studyClassId" id="studyClassId" listValue="classSection" headerKey=""
							headerValue="- Select Class -" theme="simple"
							onchange="javascript:getClassSubjects(this.value);"
							cssClass="required form-control input-medium">
						</s:select>
					</div>
				</div>
			</s:if>
		</s:else>
		
		<div id="studyClassSubjectDiv"></div>
		
			<div class="form-group">
				<label class="control-label col-md-3">
					<span class="required">*</span>Assignment Completion Date :
				</label>
				<div class="col-md-5">
					<div data-date-format="mm/dd/yyyy" data-date-start-date="+0d"
									class="input-group input-medium date date-picker">
						<input id="startDate" name="classAssignment.assignmentDate"
							readonly="" type="text" class="required form-control">
						<span class="dateInput input-group-btn">
							<button type="button" class="btn default">
								<i class="fa fa-calendar"></i>
							</button> </span>
					</div>
					<span class="help-block">(MM/DD/YYYY)</span>
				</div>
			</div>
		</div>
		<s:if test="%{classAssignment.id !=0}">
				<%-- <div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-6"> Class Name : </label>
							<div class="col-md-6">
								<sj:textfield name="classAssignment.classAndSection"
									cssClass="form-control input-medium" disabled="true" />
							</div>
						</div>
					</div>
				</div> --%>
	
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-6"> Subject Name : </label>
							<div class="col-md-6">
								<sj:textfield name="classAssignment.subjectName"
									cssClass="form-control input-medium" disabled="true" />
							</div>
						</div>
					</div>
				</div>
	
			<div class="form-group">
				<label class="control-label col-md-3">
					<span class="required">*</span>Assignment Completion Date :
				</label>
				<div class="col-md-5">
				<div data-date-format="mm/dd/yyyy" data-date-start-date="+0d"
									class="input-group input-medium date date-picker">
									
						<input id="startDate1" name="classAssignment.assignmentDate"
							readonly="true" type="text" minDate="1" class="required form-control fdate"
							value='<s:property value="classAssignment.assignmentDateStr"/>'>
						<span class="dateInput input-group-btn">
							<button type="button" class="btn default">
								<i class="fa fa-calendar"></i>
							</button> </span>
					</div>
					<span class="help-block">(MM/DD/YYYY)</span>
				</div>
			</div>
		</s:if>
		<div class="form-group">
			<label class="control-label col-md-3">
				Upload Documents :
			</label>
			<div class="col-md-5">
				<s:file name="fileUpload" id="uploadAndDownScannedDocs" cssClass="btn default"
			  multiple="multiple" tabindex="2"></s:file>
			</div>
		</div>
		<div id="attachmentDiv" class="col-md-12"></div>
		<s:if test="%{classAssignment.id != 0}">
			<s:if test="%{objectList != null && !objectList.isEmpty()}">
				<div class="col-md-12">
					<div class="col-md-3" style="width: 22%;"></div>
					<div class="col-md-8">
						<table
							class="table table-striped table-bordered table-hover table-full-width"
							id="sample_2" style="width: 50%;">
							<thead>
								<tr>
									<th>
										Uploaded Documents
									</th>
									<th>
										Delete
									</th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="objectList">
									<tr>
										<td>
											<p class="form-control-static">
												<a target="_new"
													href="../<s:property value="filePath" /><s:property value="fileName" />"><s:property
														value="fileName" /> </a>
											</p>
										</td>
										<td>
											<s:url id="removeClassAttachment"
												action="ajaxDeleteClassAttachment" includeParams="all"
												escapeAmp="false" namespace="/admin">
												<s:param name="tempId" value="%{id}"></s:param>
												<s:param name="tempId1" value="%{classAssignment.id}"></s:param>
											</s:url>
											<s:if
												test='%{(#session.academicYear == null || #session.academicYear.isEmpty()) || (#session.previousYear=="N")}'>
												<s:div cssClass="btn btn-xs red emsRemove"
													id='%{removeClassAttachment}'
													title="Delete this attachment">
													<i class="fa fa-times"></i>Delete</s:div>
											</s:if>
										</td>
									</tr>
								</s:iterator>
							</tbody>
						</table>
					</div>
				</div>
			</s:if>
		</s:if>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-6">
						<span
							class="required">*</span> Add Assignment Description :
					</label>
					<div class="col-md-6">
						<sj:textarea rows="3" cols="30"
							name="classAssignment.description" 
							cssClass="required form-control input-medium">
						</sj:textarea>
					</div>
				</div>
			</div>
		</div>
		<div class="form-actions fluid">
			<div class="col-md-offset-3 col-md-9">
				<s:if test="%{customer.checkAssignmentSmsService == true && customer.checkMobileService == true && (smsAlloted != 0) && (smsAlloted > smsCnt)}">
					<sj:submit  targets="mainContentDiv" value="Send SMS & Save"
					cssClass="submitBt btn green" indicator="indicator"
					onclick="submitType()" id="submitButton3" validate="true"
					formIds="createStaffAssignment"
					onBeforeTopics="addstaffClassAssignment" resetForm="true" />
				</s:if>	
				<sj:submit  targets="mainContentDiv" value="Submit"
					 id="submitButton2" validate="true"
					formIds="createStaffAssignment" cssClass="submitBt btn blue" indicator="indicator"
					onBeforeTopics="addstaffClassAssignment" resetForm="true" />
				<s:if test="%{classAssignment.id !=0}">
					<button type="button" data-dismiss="modal" class="btn default">
						Cancel
					</button>
				</s:if>
				<s:else>
					<s:url id="doCancelStudentAssignment"
						action="ajaxViewStaffClassAssignment" includeParams="all"
						escapeAmp="false" namespace="/admin"></s:url>
					<sj:a href="%{doCancelStudentAssignment}" cssClass="btn default"
						 targets="mainContentDiv" button="false">Cancel</sj:a>
				</s:else>
			</div>
		</div>
	</s:form>
</div>
<s:if test="%{classAssignment.id !=0}">
</div>
</div>
</s:if>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/ems.js"></script>
<script type="text/javascript">
changePageTitle('Create Class Assignment');
$(document).ready(function() {
	var startDate = $('span#startDateSpan').attr("class");
	var endDate = $('span#endDateSpan').attr("class");
	dateRestrictionWithinAcademicYear(startDate,endDate);
	FormComponents.init();
var classAssignmentId='<s:property value="classAssignment.id" />';
if(classAssignmentId > 0){
	$('select#subjectId').removeClass('required');
}else{
	$("[name='classAssignment.subjectId']").val('');
	$("[name='classAssignment.description']").val('');
}
$.destroyTopic('addstaffClassAssignment');
	FormComponents.init();
	var assignmentId = $("#assignmentId").val();
	if (assignmentId > 0) {
		$('div#classSubjectDiv').empty();

	} else {
		$('div#classSubjectDiv').show();
	}
	
});

 if ($('div.emsRemove')) {
	$('div.emsRemove').unbind('click');
	$("div.emsRemove").click(function() {
		confirmDialog(this);
	});
} 
function submitType() {
	$('#anyId').val('SMS');
}
 $.subscribe('addstaffClassAssignment', function(event, data) {
	var subjectId = $('#subjectId').val();
	var classAssignmentId='<s:property value="classAssignment.id" />';
	var classIds = 0;
	if (isNonEmpty(subjectId)) {
		$("#classSubject").val($("select[id='subjectId'] option:selected").text());
		  if ($("input[name=chkBoxSelectedIds]:checked").length > 0) {
			  classIds = $("input[name=chkBoxSelectedIds]:checked");
			var selectedClassIds = '';
			if (classIds.length > 0 && isNonEmpty(classIds)) {
				selectedClassIds = '(';
				for ( var i = 0; i < classIds.length; i++) {
					selectedClassIds += classIds[i].value + ', ';
				}
				selectedClassIds += '0)';
			}
			$("#classNameIds").val(selectedClassIds);
			var balance = document.getElementById("anyId").value;
			if (balance == 'SMS') {
				$('#submitButton').val('Saving...');
			} else {
				$('#submitButton1').val('Saving...');
			}
		 }/* else if(classAssignmentId==0){
			 alert("Please select Class");
			 event.originalEvent.options.submit = false;
		 } */
	 } 
	 $('button.close').click();
	 return true;
});


function getClassSubjects(studyClassid) {
	//var subjectId = $("select#subjectId").val();
	var url = jQuery.url.getChatURL("/admin/ajaxGetClassSubjects.do");
	if (studyClassid.length == 0) {
		alert("!Oops select class");
		return false;
	} else {
		$("#studyClassSubjectDiv").html( '<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		var pars = "tempId2=" + studyClassid+"&tempString=Y";
		$.ajax( {
			url : url,
			cache : false,
			data : pars,
			success : function(html) {
				$("#studyClassSubjectDiv").html(html);
				//document.getElementById('studyClassSubjectDiv').style.display = "block";
			}
		});
	}
}


function confirmDialog(event) {
	thishref = $(event).attr('id');
	if ($(event).next('.question').length <= 0) {
		$(event)
				.after(
						'<div class="question">Are You Sure?<br/> <span class="yes">Yes</span><span class="cancel">Cancel</span></div>');
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
				success : function(response) {
				if (isNonEmpty(response.info)) {
					var classAttach = response.info;
					if (isNonEmpty(classAttach)) {
						prdDiv.remove();
					} else{
					prdDiv.parent().parent().remove();
					}
				} else {
					prdDiv.parent().parent().remove();
					$('div#attachmentDiv').html('<div class="alert alert-success"><strong>Attachment removed successfully.</strong><button class="close" data-dismiss="alert"></button></div>');
				}
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