<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Student Marks to Mobile
				</div>
			</div>
			<div class="portlet-body">
				<div class="tab-content form-body">
					<jsp:include page="/common/messages.jsp" />
					<div id="sendMarksDiv">
						<s:if test="%{(studyClass != null)}">
							<s:form action="ajaxDoSendMarksToMobile" theme="simple"
								namespace="/exam" id="marksToMobiles" method="post"
								cssClass="form-horizontal">
								<s:hidden name="tempId" id="dropDownId"></s:hidden>
								<s:hidden id="studentIds" name="anyId" />
								<div class="row">
								<div class="form-group">
									<label class="control-label col-md-2">
										ClassTeacher Class :
									</label>
									<div class="form-control-static">
										<s:property value="studyClass.classAndSection" />
									</div>
								</div>
								</div>
								<div class="form-group">
									<s:if
										test="%{(viewStudentMarksDetailsList != null && !viewStudentMarksDetailsList.isEmpty())}">
										<span id="overalGradesForcustomer" style="display: none;"><s:property
												value="anyTitle" /> </span>
										<div class="row">
										<div class="col-md-5">
										<label class="control-label col-md-4">
											<span class="required">*</span>Select ExamType :
										</label>
										<div class="col-md-6">
											<s:select list="viewStudentMarksDetailsList"
												listKey="examTypeId" listValue="examType" name="tempId1"
												cssClass="form-control input-medium required" onchange="javascript:getStudensForThisClassAndExamType(this.value);"
												id="examTypeId" theme="simple" headerKey="null"
												headerValue="- Select -">
											</s:select>
										</div>
										</div>
										<%-- <div class="col-md-6">
											<label class="control-label col-md-4">
												<span class="required">*</span>Select Type :
											</label>
											<div class="col-md-5">
												<s:select list="#{'G':'Marks With Grade','R':'Marks With Rank','N':'Only Marks'}"
													id="gradeType" cssClass="form-control input-medium "
													name="selectedId" headerKey="" headerValue="- Select -">
												</s:select>
											</div>
										</div> --%>
										<s:hidden id="gradeType" value="N" />
										</div>
									</s:if>
									<s:else>
										<div class="alert alert-info">
											Currently there are no marks assigned for this class.
										</div>
									</s:else>
									<br/>
									<div>
										<div id="resultsDiv3"></div>
									</div>
								
									
									
								</div>
								<s:if
									test='%{(#session.academicYear == null || #session.academicYear.isEmpty()) || (#session.previousYear=="N")}'>
									<div class="spaceDiv"></div>
									<div class="form-actions fluid">
										<div class="col-md-offset-3 col-md-9" id="hideSubmit" style="display: none;">
											<sj:submit type="submit" value="Send Marks"
												formIds="marksToMobiles" targets="mainContentDiv"
												cssClass="submitBt btn blue"
												onBeforeTopics="marksSmsToMobile" validate="true">
											</sj:submit>
										</div>
									</div>
								</s:if>
							</s:form>
						</s:if>
						<s:elseif
							test="%{(studyClassList != null && !studyClassList.isEmpty())}">
							<s:form action="ajaxDoSendMarksToMobile" theme="simple"
								id="marksToMobiles" method="post" cssClass="form-horizontal"
								namespace="/exam">
									<s:hidden id="studentIds" name="anyId" />
								<div class="panel-body col-md-12">
									<div class="col-md-1">
										<span class="label label-danger"> NOTE : </span>
									</div>
									<div class="col-md-10">
										<ul>
											<li> 
												Before sending marks please check whether you added marks to
												all the students for all subjects.
											</li>
											<li>
												You need to add  Exam Grades in Examination Section-> Grades & Exam Types -> Add exam grades
											</li>
										</ul>
									</div>
								</div>
								<s:if test="%{(smsAlloted == 0) || (smsAlloted <= smsCnt)}">
									<div style="color: red;" class="alert alert-info col-md-12">
										You have been used all your allotted
										<s:property value="smsAlloted" />
										SMS. Please contact EazySchool support team (080-46620999) to recharge your SMS.
									</div>
								</s:if>
								<div class="form-group">
									<label class="control-label col-md-2">
										<span class="required">*</span>Select ClassName :
									</label>
									<div class="col-md-3">
										<s:select list="studyClassList" listKey="id"
											listValue="classAndSection" id="dropDownId"
											cssClass="form-control input-medium required"
											onchange="javascript:getExamTypesForThisClass(this.value);"
											name="tempId" headerKey="0" headerValue="- Select -">
										</s:select>
									</div>
								</div>
								<div>
									<div id="resultsDiv2"></div>
								</div>
								<s:if
									test='%{(#session.academicYear == null || #session.academicYear.isEmpty()) || (#session.previousYear=="N")}'>
									<s:if test="%{(smsAlloted != 0) && (smsAlloted > smsCnt)}">
										<div class="form-actions fluid" id="hideSubmit" style="display: none;">
											<div class="col-md-offset-2 col-md-9">
												<sj:submit type="submit" value="Send Marks"
													formIds="marksToMobiles" validate="true" indicator="indicator"
													targets="mainContentDiv" cssClass="submitBt btn blue"
													onBeforeTopics="marksSmsToMobile">
												</sj:submit>
											</div>
										</div>
									</s:if>	
								</s:if>
							</s:form>
						</s:elseif>
						<s:else>
							<div class="alert alert-info">
								Currently there are no marks assigned for classes.
							</div>
						</s:else>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function() {
	$.destroyTopic('marksSmsToMobile');
	var selectBox = $('#dropDownId').val();
	getExamTypesForThisClass(selectBox);
	$.subscribe('marksSmsToMobile',function(event, data) {
		var studentsIds = $("input:checkbox[name=chkBoxSelectedAccountIds]:checked");
	    var selectedStudentsIds = '';
	    if (studentsIds.length > 0) {
	    	selectedStudentsIds = '(';
	        for ( var i = 0; i < studentsIds.length; i++) {
	        	selectedStudentsIds += studentsIds[i].value + ',';
	        }
	        selectedStudentsIds += ' 0 )';
	    }
	    $("#studentIds").val(selectedStudentsIds);
		var selectedChkCount = $("input[name=chkBoxSelectedAccountIds]:checked").length;
	if ($('#marksToMobiles').valid()) {
		if(selectedChkCount > 0){
			var gradeType = $('#gradeType').val();
			var grades = $('span#overalGradesForcustomer').html();
			grades = jQuery.trim(grades);
			if (isNonEmpty(grades)) {
				if (grades = 'OverAllGrades' && gradeType =="G") {
					alert("You dont have overal grades,Please create overal grades");
					event.originalEvent.options.submit = false;
				}
			}
			var tempId1 = $('#examTypeId').val();
			
			if (isNonEmpty(selectBox)) {
				if (isNonEmpty(tempId1) && isNonEmpty(selectBox) && isNonEmpty(gradeType)) {
					return true;
				} else if (!isNonEmpty(tempId1)) {
					alert("Please select examType");
					event.originalEvent.options.submit = false;
				} else if (!isNonEmpty(gradeType)) {
					alert("Please select grade type");
					event.originalEvent.options.submit = false;
				}
			} else {
				alert("Please select class");
				event.originalEvent.options.submit = false;
			}
		} else{
			alert('Please select at least one student.');
			event.originalEvent.options.submit = false;
		}
	} else {
		event.originalEvent.options.submit = false;
	}
});
});

function getExamTypesForThisClass(selectBox) {
	var classId = null;
	var url = jQuery.url.getChatURL("/exam/ajaxDoGetExamTypesForThisClass.do");
	if (isNonEmpty(selectBox)) {
		$('#hideSubmit').hide();
		var classId = selectBox;
		if(classId > 0){
			$("#resultsDiv2").html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
			var pars = "tempId=" + classId;
			$.ajax( {
				url : url,
				cache : false,
				data : pars,
				success : function(html) {
					$("#resultsDiv2").html(html);
					if (jQuery.trim($("#resultsDiv2").html(html).text()) == 'Currently there are no students in this class.'
							|| jQuery.trim($("#resultsDiv2").html(html)
									.text()) == 'Currently there are no marks assigned for this class.') {
						$('#hideSubmit').hide();
					} else {
						$('#hideSubmit').show();
					}
					
				}
			});
		}else{
			$('#resultsDiv2').html('<div class="alert alert-info">Please select class name in the drop down.</div>');
		}
	}
}
function getStudensForThisClassAndExamType(examTypeId) {
	var classId = $('#dropDownId').val();
	var url = jQuery.url.getChatURL("/exam/ajaxDoGetStudentsForThisClassExamTypes.do");
	if (isNonEmpty(classId)) {
		//$('#hideSubmit').hide();
		//var classId = selectBox;
		if(classId > 0 && examTypeId > 0){
			$("#resultsDiv3").html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
			var pars = "tempId=" + classId+ "&tempString=" +examTypeId;
			$.ajax( {
				url : url,
				cache : false,
				data : pars,
				success : function(html) {
					$("#resultsDiv3").html(html);
					/* if (jQuery.trim($("#resultsDiv3").html(html).text()) == 'Currently there are no marks assigned for any student. ') {
						$('#hideSubmit').hide();
					} else {
						$('#hideSubmit').show();
					} */
					
				}
			});
		}else{
			$('#resultsDiv3').html('<div class="alert alert-info">Please select Exam Type in the drop down.</div>');
		}
	}
}
</script>
