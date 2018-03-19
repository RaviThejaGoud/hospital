<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Manage Student
				</div>
			</div>
			<div class="portlet-body">
				<div class="tabbable tabbable-custom tabbable-full-width">
					<s:if
						test='%{user.isOnlySchoolAdmin == "Y" || user.isSchoolPrincipal=="Y" || user.isSchoolClerk=="Y" || user.isSchoolDirector == "Y"}'>
						<ul class="nav nav-tabs">
							<li>
								<s:url id="DiscontinueStudents" action="ajaxViewExpiredStudents"
									namespace="/student" />
								<sj:a href="%{DiscontinueStudents}" targets="staffList"
									data-toggle="tab">Inactive Students</sj:a>
							</li>
							<s:if test='%{#session.previousYear == "N"}'>
								
								<li>
									<s:url id="studentChangeSection" action="ajaxDoStudentChangeSection"
										namespace="/student" />
									<sj:a href="%{studentChangeSection}" targets="staffList"
										data-toggle="tab">Change Section</sj:a>
								</li>
								
								<li>
									<s:url id="urlDownloadStudentDetails"
										action="ajaxDownloadStudentDetails" namespace="/reports">
									</s:url>
									<sj:a href="%{urlDownloadStudentDetails}" targets="staffList"
										data-toggle="tab">Edit Students Details</sj:a>
								</li>
								<s:if test="%{studyClassList != null && !studyClassList.isEmpty()}">
									<li>
										<s:url id="importGroupExcelSheet" namespace="/student"
											action="ajaxDoImportStudentExcelSheet" />
										<sj:a href="%{importGroupExcelSheet}" targets="staffList"
											data-toggle="tab">Import Students</sj:a>
									</li>
								</s:if>
								<li>
									<s:url id="doAddStudent" action="ajaxDoAddStudent"
										namespace="/student" includeParams="all" escapeAmp="false" >
										<s:param name="studyClassId" value="0"></s:param>
										</s:url>
									<sj:a href="%{doAddStudent}" targets="staffList" id="addStudentSetting"
										data-toggle="tab">
										Add Student
									</sj:a>
								</li>

							</s:if>
							<li class="active">
								<s:url id="viewSub" action="ajaxGetStudyClassList"
									namespace="/student">
								</s:url>
								<sj:a id="viewSub" href="%{viewSub}" targets="mainContentDiv"
									data-toggle="tab">View Student Details</sj:a>
							</li>
						</ul>
					</s:if>
					<div class="tab-content">
						<div class="searchDiv">
							<div class="hideSearchStudentBody">
								<div id="staffList">
									<div>
										<jsp:include page="/common/messages.jsp" />
									</div>
									<s:if
										test="%{studyClassList != null && !studyClassList.isEmpty()}">
										<div class="form-group">
											<label class="col-md-2" style="margin-right:0%">
												Search Student By :
											</label>
											<div class="col-md-10 radio-list">
												<label class="col-md-3 radio-inline">
													<input type="radio" name="SelectType" value="Select Class"
														onclick="handleClick(this.value);" checked>
													Class & Section
												</label>
												<label class="col-md-3 radio-inline">
													<input type="radio" name="SelectType"
														value="Search Student" onclick="handleClick(this.value);">
													Student Name
												</label>
												<label class="col-md-3 radio-inline">
													<input type="radio" name="SelectType"
														value="Search Admission Number"
														onclick="handleClick(this.value);">
													Admission Number
												</label>
												<s:if test='%{user.isOnlySchoolTeacher !="Y" && user.isSchoolAsstStaff !="Y"}'>
													<label class=" col-md-3 radio-inline">
														<input type="radio" name="SelectType" value="StudentImage"
															onclick="handleClick(this.value);">
														Upload Student Images
													</label>
												</s:if>
											</div>
										</div>
										<br />
										<br/>
										<div class="searchDiv" id="selectserchClass">
											<s:form id="selectStudentForm" action="#" theme="simple"
												cssClass="form-horizontal">
												<div class="form-group">
													<label class="col-md-2 control-label">
														Select Class :
													</label>
													<div class="col-md-7">
														<s:select list="studyClassList" id="className"
															cssClass="form-control input-medium"
															name="classSectionId" listKey="id"
															listValue="classAndSection" theme="simple"
															onchange="javascript:getAjaxDoGetStudent(this.value);">
														</s:select>
													</div>
												</div>
											</s:form>
										</div>
										<div class="searchDiv" id="searchStudent" style="display: none;">
											<s:form id="searchStudentByNumber"
												action="ajaxSearchStudentByCriteriaDetails"
												cssClass="form-horizontal" theme="simple"
												namespace="/student">
												<s:hidden name="staff.id"></s:hidden>
												<div class="form-group">
													<div class="col-md-2 ">
														&nbsp;
													</div>
													<div class="col-md-5">
														<div class="input-group">
															<sj:textfield name="anyTitle" id="rollNumber"
																placeholder="Student First or Last Name"
																cssClass="form-control  medium">
															</sj:textfield>
															<span class="input-group-btn"> <sj:submit
																	targets="searchStudentsList" value="Find Student"
																	cssClass="btn blue long" indicator="indicator"
																	cssStyle="float:none;"
																	onBeforeTopics="searchStudentForm" validate="true"
																	formIds="searchStudentByNumber" /> </span>
														</div>
														<span class="help-block">(Key at least 3 chars and
															hit 'Find Student' to get closer match.) </span>
													</div>
												</div>
											</s:form>
										</div>
										<div class="searchDiv" id="searchAdmissionNumber" style="display: none;">
											<s:form id="searchStudentByAdmissionNumber"
												cssClass="form-horizontal"
												action="ajaxSearchStudentByCriteriaDetails" theme="simple"
												namespace="/student">
												<s:hidden name="staff.id"></s:hidden>
												<!--@Ganesh Here we need to show previous year student admission number details also because while entering student number we are showing admission number taken but we are not showing previous year inactive student details for that one I need to remove academic year wise checking in this screen to show old year inactive student details -->
												<s:hidden name="tempString3" value="manageStudent" />
												<div class="form-group">
													<div class="col-md-2 ">
														&nbsp;
													</div>
													<div class="col-md-5">
														<div class="input-group">
															<sj:textfield name="selectedId" id="admissionNumber"
																cssClass="form-control"
																placeholder="Student Admission Number">
															</sj:textfield>
															<span class="input-group-btn"> <sj:submit
																	targets="searchStudentsList" value="Find Student"
																	cssClass="btn blue long" indicator="indicator"
																	cssStyle="float:none;" validate="true"
																	onBeforeTopics="searchStudentAdmissionForm"
																	formIds="searchStudentByAdmissionNumber" />
															</span>
														</div>
														<span class="help-block">(Type admission number and
															hit 'Find Student.) </span>
													</div>
												</div>
											</s:form>
										</div>
									</s:if>
									<s:else>
										<div class="alert alert-info">
										<!-- 
											 Currently there are no students. Please <a href="#" onclick="javascript:addStudents()"> <b>Click here</b> </a> to add students. -->
											 
											 Currently there are no classes available. contact administrator to add Class. 
												<s:url id="urlViewClassSectionInfo" action="ajaxDoManageClassSections"
													namespace="/admin"></s:url>
												<sj:a id="viewClassSecInfo" href="%{urlViewClassSectionInfo}"
													targets="mainContentDiv" data-toggle="tab">
													<!--  <font color="red"><b>Add class</b></font>-->
												</sj:a>
										</div>
									</s:else>
									<div id="searchStudentsList"></div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript"	src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>
<script type="text/javascript">
changePageTitle("Manage Student");
$(document).ready(function() {
$("input:checkbox, input:radio").uniform();  
$.destroyTopic('searchStudentForm');
$.destroyTopic('searchStudentAdmissionForm');

var studyClassId=$('#className').val();
	if(isNonEmpty(studyClassId)){
		// getAjaxDoGetStudent(studyClassId);
	}
	$('#searchStudentsList').show();
	$('#searchAdmissionNumber').hide();
	$('#searchStudent').hide();
	//$('#ui-datepicker-div').hide();
	$(".hideSearchStudentBody").show();//hide the all of the element with class msg_body
		var selected = $('input[name=SelectType]:radio:checked').val();
		handleClick(selected);
		$.subscribe('searchStudentForm', function(event, data) {
			var rollNumber = $('#rollNumber').val();
			if (rollNumber == null || rollNumber == ''
					|| rollNumber == 'Student Admission Number'
					|| rollNumber == 'Student First or Last Name') {
				alert("Please enter first name or last name.");
				event.originalEvent.options.submit=false;

			} else if (rollNumber.length < 3) {
				alert("Please enter minimum 3 characters.");
				//$('#rollNumber').val('Student First or Last Name');
				event.originalEvent.options.submit=false;
			} else{
				$(".hideSearchStudentBody").show();
			    $('#searchStudentsList').show();
			    return true;
			}
		});
		$.subscribe('searchStudentAdmissionForm', function(event, data) {
			$('#makePayment').hide();
			var admissionNumber = $('#admissionNumber').val();
			if (admissionNumber == null || admissionNumber == ''
					|| admissionNumber == 'Student Admission Number') {
				alert("Please enter student admission number.");
				event.originalEvent.options.submit=false;

			} else if (admissionNumber.length < 1) {
				alert("Please enter minimum 1 character.");
				//$('#admissionNumber').val('Student Admission Number');
				event.originalEvent.options.submit=false;
			} else{
				$(".hideSearchStudentBody").show()
			    $('#searchStudentsList').show();
				return true;
				}
		});
	});


function addStudents(){
	$('a#addStudentSetting').click();
}

function getAjaxDoGetStudent(studyClassId) {
  if($('input[value="StudentImage"]').is(':checked')){
	doGetStudentForUploadImage(studyClassId);
	return true;
   }
  	if (studyClassId == "") {
		$("#searchStudentsList").hide();
	} else {
		var pars = "classId=" + studyClassId;
		$("#searchStudentsList")
				.html(
						'<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		var url = jQuery.url.getChatURL("/student/ajaxSearchStudentByCriteriaDetails.do");
		$.ajax( {
			url : url,
			cache : false,
			data : pars,
			success : function(html) {
				$("#searchStudentsList").html(html);
				$("#searchStudentsList").show();
			}
		});
	}
}

 function doGetStudentForUploadImage(studyClassId) {
	if (studyClassId == "") {
		$("#searchStudentsList").hide();
	} else {
		var pars = "classId="+studyClassId+"&anyId=Upload";
		$("#searchStudentsList")
				.html(
						'<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		var url = jQuery.url.getChatURL("/student/ajaxGetNoImageStudents.do");
		$.ajax( {
			url : url,
			cache : false,
			data : pars,
			success : function(html) {
				$("#searchStudentsList").html(html);
				$("#searchStudentsList").show();
				$("th.DisplayClass,td.DisplayClass").hide();
			}
		});
	}
} 

function handleClick(value) {
	if (isNonEmpty(value)) {
		if ("Select Class" == value) {
		 var studyClassId=$('select#className').val();
		 getAjaxDoGetStudent(studyClassId);
			$('#searchAdmissionNumber').hide();
			$('#searchStudentsList').show();
			$('#searchStudent').hide();
			$('#selectserchClass').show();
		} else if ("Search Student" == value) {
			//$("input#rollNumber").val("Student First or Last Name");
			$('input#rollNumber').val("");
			$('#searchAdmissionNumber').hide();
			$('#searchStudentsList').hide();
			$('#selectserchClass').hide();
			$('#searchStudent').show();
		} else if ("Search Admission Number" == value) {
			//$('input#admissionNumber').val("Student Admission Number");
			$('input#admissionNumber').val("");
			$('#selectserchClass').hide();
			$('#searchStudentsList').hide();
			$('#searchStudent').hide();
			$('#searchAdmissionNumber').show();
		}
		 else if("StudentImage"==value){
		    var studyClassId=$('select#className').val();
		    doGetStudentForUploadImage(studyClassId);
			$('#searchAdmissionNumber').hide();
			$('#searchStudentsList').show();
			$('#searchStudent').hide();
			$('#selectserchClass').show();
		 } 
	}
}
//$(".go-top").click();
$('html, body').animate({ scrollTop: $(document).height() - $(window).height() }, 10, function() {
	    $(this).animate({ scrollTop: 0 }, 10);
	});
</script>
