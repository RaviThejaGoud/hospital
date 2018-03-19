<%@ include file="/common/taglibs.jsp"%>
<jsp:include page="/common/messages.jsp" />
<%-- <s:if test="%{classAssignment.id != 0}">
	<div data-width="760" class="modal fade modal-overflow in"
		id="responsive"
		style="display: block; width: 760px; margin-left: -379px; margin-top: 200px;"
		aria-hidden="false">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true"></button>
			<h4 class="modal-title">Update Class Assignment</h4>
		</div>
		<div class="modal-body">
</s:if> --%>
<s:if test="%{(customer.contactEmail == null || customer.contactEmail.isEmpty())}">
	<div class="row">
		<div class="col-md-12">
			<div class="form-group">
				<div class="panel-body col-md-12">
					<div class="col-md-1">
						<span class="label label-danger"> ALERT : </span>
					</div>
					<div class="col-md-10" id="enableContactEmail">
						<div class="alert alert-danger">
							&nbsp;&nbsp; You have not configured from email address. So
							system will send emails from "messages@eazyschool.com".
							<s:url id="urlSendContactEmailLink"
								action="ajaxDoSchoolFromEmailInfo" namespace="/admin" />
							<sj:a href="%{urlSendContactEmailLink}" targets="mainContentDiv"
								cssClass="ajaxify title CFE ">  Click Here  </sj:a>
							to configure from email address if you wish to process email from
							your email address.
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</s:if>
<s:if test="%{(smsAlloted == 0) || (smsAlloted <= smsCnt)}">
	<div style="color: red;" class="alert alert-info">
		You have been used all your allotted
		<s:property value="smsAlloted" />
		SMS. Please contact EazySchool support team (080-46620999) to recharge
		your SMS.
	</div>
</s:if>
<span id="startDateSpan" class="<s:property value='startDate'/>"></span>
<span id="endDateSpan" class="<s:property value='endDate'/>"></span>
<span id="toDateSpan" class="<s:property value='toDate'/>"></span>
<span id="admissionAcademicyear"
	class="<s:property value='#session.admissionAcademicYearId'/>"></span>
<span id="usrAcadmicId" class="<s:property value='userAcademicYearId'/>"></span>


<s:form action="ajaxAddClassAssignment" id="createAssignment"
	enctype="multipart/form-data" method="post" theme="simple"
	cssClass="form-horizontal" namespace="/admin">

	<s:hidden id="classSubject" name="anyTitle"></s:hidden>
	<s:hidden name="classAssignment.id" id="assignmentId" />
	<s:hidden name="balance" id="anyId" value=""></s:hidden>
	<s:hidden id="classNameIds" name="selectedId" />
	<s:if test="%{classAssignment.id !=0}">
		<s:hidden name="classAssignment.subjectId" id="subjectId"></s:hidden>
		<s:hidden name="classAssignment.classSectionId" id="classSectionId"></s:hidden>
	</s:if>
	<div class="form-body">

		<s:if
			test='%{user.isOnlySchoolAdmin == "Y" || user.isSchoolPrincipal=="Y" || user.isSchoolDirector == "Y"}'>
			<s:if test="%{studyClassList != null && !studyClassList.isEmpty()}">
				<div class="form-group">
					<label class="control-label col-md-3"> <span
						class="required"> * </span>Select Class :
					</label>
					<div class="col-md-5">
						<s:select list="studyClassList"
							name="classAssignment.classSectionId" listKey="id"
							id="studyClassId" listValue="classAndSection" headerKey=""
							headerValue="- Select Class -" theme="simple"
							onchange="javascript:getClassSubjects(this.value);"
							cssClass="required form-control input-medium">
						</s:select>
					</div>
				</div>
			</s:if>
		</s:if>
		<s:else>
			<s:if
				test="%{objectList != null && !objectList.isEmpty() && classAssignment.id ==0}">
				<div class="form-group">
					<label class="control-label col-md-3"> <span
						class="required"> * </span>Select Class :
					</label>
					<div class="col-md-5">
						<s:select list="objectList" name="classAssignment.classSectionId"
							listKey="studyClassId" id="studyClassId" listValue="classSection"
							headerKey="" headerValue="- Select Class -" theme="simple"
							onchange="javascript:getClassSubjects(this.value);"
							cssClass="required form-control input-medium">
						</s:select>
					</div>
				</div>
			</s:if>
		</s:else>

		<div id="studyClassSubjectDiv"></div>


		<s:if test="%{classAssignment.id !=0}">
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-6"> Class Name : </label>
						<div class="col-md-6">
							<sj:textfield name="viewClassAssignmentDetails.classAndSection"
								cssClass="form-control input-medium" disabled="true" />
						</div>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-6"> Subject Name : </label>
						<div class="col-md-6">
							<sj:textfield name="viewClassAssignmentDetails.subjectName"
								cssClass="form-control input-medium" disabled="true" />
						</div>
					</div>
				</div>
			</div>

		</s:if>


		<%-- <div id="classSubjectDiv" style="display: none;">
			<s:if test="%{tempList2 != null && !tempList2.isEmpty()}">
				<div class="form-group">
					<label class="control-label col-md-3"> <span
						class="required"> * </span>Select Subject :
					</label>
					<div class="col-md-5">
						<s:select list="tempList2" name="classAssignment.subjectId"
							listKey="id" id="subjectIds" listValue="name" headerKey=""
							headerValue="- Select Subject -" theme="simple"
							onchange="javascript:getClassSubjects(this.value);"
							cssClass="required form-control input-medium">
						</s:select>
					</div>
				</div>
			</s:if>
			<div id="classSectionDiv" style="display: none">
				<jsp:include
					page="/WEB-INF/pages/common/classAssignment/ajaxGetClassSubjects.jsp" /></div>
		</div> --%>
		<div id="uploadAssignmentDiv">
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-6"> <span
							class="required">*</span>Completion Date :
						</label>
						<div class="col-md-5">
							<s:if test="%{classAssignment.id != 0}">
								<div data-date-start-date="+0d" data-date-format="mm/dd/yyyy"
									class="input-group input-medium date date-picker">
									<input id="startDate" name="classAssignment.assignmentDate"
										onchange="validateAssignmnetDate(this.value);"
										readonly="readonly" type="text"
										value='<s:property value="classAssignment.assignmentDateStr"/>'
										class="required form-control input-medium "> <span
										class="dateInput input-group-btn">
										<button type="button" class="btn default">
											<i class="fa fa-calendar"></i>
										</button>
									</span>
								</div>
								<span class="help-block">(MM/DD/YYYY)</span>
							</s:if>
							<s:else>
								<div data-date-start-date="+0d" data-date-format="mm/dd/yyyy"
									class="input-group input-medium date date-picker">
									<input id="startDate" name="classAssignment.assignmentDate"
										readonly="readonly" type="text"
										onchange="validateAssignmnetDate(this.value);"
										class="required form-control input-medium "> <span
										class="dateInput input-group-btn">
										<button type="button" class="btn default">
											<i class="fa fa-calendar"></i>
										</button>
									</span>
								</div>
								<span class="help-block">(MM/DD/YYYY)</span>
							</s:else>
						</div>
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-md-3"> Upload Documents : </label>
				<div class="col-md-6">
					<s:file name="fileUpload" id="uploadAndDownScannedDocs"
						label="Upload Documents" multiple="multiple" tabindex="2"></s:file>
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
										<th>Uploaded Documents</th>
										<th>Delete</th>
									</tr>
								</thead>
								<tbody>
									<s:iterator value="objectList">
										<tr>
											<td>
												<p class="form-control-static">
													<a target="_new" href="<s:property value="filePath" />"><s:property
															value="fileName" /> </a>
												</p>
											</td>
											<td><s:url id="removeClassAttachment"
													action="ajaxDeleteClassAttachment" includeParams="all"
													escapeAmp="false" namespace="/admin">
													<s:param name="tempId" value="%{id}"></s:param>
													<s:param name="tempId1" value="%{classAssignment.id}"></s:param>
												</s:url> <s:if
													test='%{(#session.academicYear == null || #session.academicYear.isEmpty()) || (#session.previousYear=="N")}'>
													<s:div cssClass="btn btn-xs red emsRemove"
														id='%{removeClassAttachment}'
														title="Delete this attachment">
														<i class="fa fa-times"></i>Delete</s:div>
												</s:if></td>
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
						<label class="control-label col-md-6">  <span
							class="required">*</span> Add Description : </label>
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
				<div class="col-md-7">
					<div class="col-md-offset-5 col-md-12">
						<s:if test="%{customer.checkAssignmentSmsService == true && customer.checkMobileService == true}">
							<s:if test="%{(smsAlloted != 0) && (smsAlloted > smsCnt)}">
								<sj:submit targets="mainContentDiv" value="Save & Send SMS"
									cssClass="submitBt btn green" indicator="indicator"
									onclick="submitType()" id="submitButton" validate="true"
									formIds="createAssignment" onBeforeTopics="addClassAssignment" />
							</s:if>
						</s:if>
						<sj:submit targets="mainContentDiv" value="Submit"
							cssClass="submitBt btn blue" indicator="indicator"
							id="submitButton1" validate="true" formIds="createAssignment"
							onBeforeTopics="addClassAssignment" />
						<%-- <s:if test="%{classAssignment.id != 0}">
							<button type="button" data-dismiss="modal" class="btn default">
								Cancel</button>
						</s:if>
						<s:else> --%>
							<s:url id="doCancelStudent" action="ajaxViewAllClassAssignment"
								includeParams="all" escapeAmp="false" namespace="/admin"></s:url>
							<sj:a href="%{doCancelStudent}" cssClass="btn default"
								targets="mainContentDiv" button="false">Cancel</sj:a>
						<%-- </s:else> --%>
					</div>
				</div>
			</div>
		</div>
	</div>
</s:form>
<%-- <s:if test="%{classAssignment.id != 0}">
	</div>
	</div>
</s:if> --%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js"></script>
<script type="text/javascript">
	changePageTitle('Create Class Assignment');
	var assignmentId = 0;
	$(document).ready(function() {
		$("div#uploadAssignmentDiv").show();
		var classAssignmentId = '<s:property value="classAssignment.id" />';
		/* if (subjectList == 0 && classAssignmentId == 0) {
			$('#submitButton1').attr('disabled', true);
			$('#submitButton').attr('disabled', true);
		} */
		if (classAssignmentId > 0) {
			$('select#subjectId').removeClass('required');
		} else {
			$("[name='classAssignment.subjectId']").val('');
			$("[name='classAssignment.description']").val('');
		}
		FormComponents.init();
		$.destroyTopic('addClassAssignment');
		assignmentId = $("#assignmentId").val();
		if (assignmentId > 0) {
			$('div#classSubjectDiv').empty();
		} else {
			$('div#classSubjectDiv').show();
			$("div#getSelectedClassStudents").hide();
			$("div#assignmentDateSlectedClassStudents").show();
		}

		var startDate = $('span#startDateSpan').attr("class");
		var endDate = $('span#endDateSpan').attr("class");
		dateRestrictionWithinAcademicYear(startDate, endDate);
		FormComponents.init();

	});
	function validateAssignmnetDate(assignmnetDate) {
		//var startDate = $('#startDate').val();
		//alert(assignmnetDate);
		var endDate = '<s:property value='plTitle'/>';
		if (isNonEmpty(assignmnetDate) || isNonEmpty(endDate)) {
			assignmnetDate = Date.parse(assignmnetDate);
			endDate = Date.parse(endDate);
			if (endDate < assignmnetDate) {
				alert("Assignment date not valid,please change assignment date.");
				$('#startDate').val("");
			}
		}
	}
	function submitType() {
		$('#anyId').val('SMS');
	}

	if ($('div.emsRemove')) {
		$('div.emsRemove').unbind('click');
		$("div.emsRemove").click(function() {
			confirmDialog(this);
		});
	}
	$.subscribe('addClassAssignment', function(event, data) {
		var subjectId = $('#subjectIds').val();
		var classAssignmentId = '<s:property value="classAssignment.id" />';
		var classIds = 0;
		if (isNonEmpty(subjectId) && subjectId > 0) {
			$("#classSubject").val(
					$("select[id='subjectId'] option:selected").text());
			if ($("input[name=chkBoxSelectedIds]:checked").length > 0) {
				classIds = $("input[name=chkBoxSelectedIds]:checked");
				var selectedClassIds = '';
				if ((classIds.length > 0) && isNonEmpty(classIds)) {
					selectedClassIds = '(';
					for (var i = 0; i < classIds.length; i++) {
						selectedClassIds += classIds[i].value + ', ';
					}
					selectedClassIds += '0)';
				}
				$("#classNameIds").val(selectedClassIds);
				var balance = document.getElementById("anyId").value;
				/* if($('#startDate').val()!=''){
					 if (balance == 'SMS') {
						 $("#imagDiv").html('<img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading');
						 $('.submitBt').attr("disabled", true);
					} else {
						$("#imagDiv").html('<img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading');
						$('.submitBt').attr("disabled", true);
					} 
				} */
			} else if (classAssignmentId == 0) {
				alert("Please select Class");
				event.originalEvent.options.submit = false;
			}
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
			$("#studyClassSubjectDiv")
					.html(
							'<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
			var pars = "tempId2=" + studyClassid+"&tempString=Y";
			$.ajax({
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
		$(event).next('.question').animate({
			opacity : 1
		}, 300);
		$('.yes').unbind('click');
		$('.yes')
				.bind(
						'click',
						function() {
							var prdDiv = $(this).parents('.question');
							prdDiv.html('Processing...');
							$
									.ajax({
										url : thishref,
										cache : false,
										success : function(response) {
											if (isNonEmpty(response.info)) {
												var classAttach = response.info;
												if (isNonEmpty(classAttach)) {
													prdDiv.remove();
												} else {
													prdDiv.parent().parent()
															.remove();
												}
											} else {
												prdDiv.parent().parent()
														.remove();
												$('div#attachmentDiv')
														.html(
																'<div class="alert alert-success"><strong>Attachment removed successfully.</strong><button class="close" data-dismiss="alert"></button></div>');
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
	$('div#enableContactEmail').click(function() {
		window.location.hash = "target=ES.ajaxify CFE";
		$('li#classAssignmentDiv').parents('li').removeClass('open active');
		$('li#classAssignmentDiv').removeClass('active');
		$('li#schoolSettingsDiv').addClass('open active');
		$('#contactEmail').addClass('active');
		$('li#urlSchoolFromEmailInfo a').click();
	});
</script>