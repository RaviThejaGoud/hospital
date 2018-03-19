<%@ include file="/common/taglibs.jsp"%>
<jsp:include page="/common/messages.jsp" />
<s:if test="%{studyClassList != null && !studyClassList.isEmpty()}">
<div id="deleteExType">
	<s:form id="addNewExamSchedules" action="ajaxEditClassExamSchedules"
		cssClass="form-horizontal" method="post" theme="simple"
		namespace="/exam">
		<s:hidden id="classNameIds" name="anyId" />
		<s:hidden name="examTypeId" value="%{examType}" cssClass='examTypeId' />
		<s:hidden name="tempString" cssClass='tempString' />
		<h4 class="bold pageTitle">
			Add or Update Exam Schedule
		</h4>
		<div class="form-group">
			<div class="panel-body col-md-12">
				<div class="col-md-1">
					<span class="label label-danger"> NOTE : </span>
				</div>
				<div class="col-md-10">
					<ul>
						<li>
							Select applicable class-sections and exam type then system would
							display input form for all the available subjects.
						</li>
						<li>
							Split the maximum marks to all exam sub types if exam sub types are
							involved in the schedule.
						</li>
					</ul>
				</div>
			</div>
		</div>
		<div class="form-body">
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-6">
							<span class="required">*</span> Select Class :
						</label>
						<div class="col-md-5">
							<s:select list="studyClassList" listKey="id +'_'+classNameClassId.id"
								listValue="classAndSection"
								cssClass="required form-control input-medium"
								id="classSectionId" name="classId" headerKey=""
								headerValue="- Select Class -"
								onchange="javascript:onClassChange(this.value);" />
						</div>
					</div>
				</div>
			</div>
			<div id="examTypesContent">
			</div>
			<div id="examScheduleSubjects"></div>
			<s:if test='%{#session.previousYear == "N" && toDate.compareTo(endDate) <= 0 }'>
 				<div class="form-actions fluid examScheduleSubjectsButtons">
					<div class="col-md-offset-2 col-md-5">
						<s:if test='%{user.isOnlySchoolAdmin == "Y" || user.isSchoolPrincipal=="Y" || user.isSchoolDirector == "Y"}'>
							<sj:submit targets="classContentDiv" value="Submit"
								cssClass="submitBt btn blue" indicator="indicator"
								formIds="addNewExamSchedules" validate="true"
								onBeforeTopics="addClassExamValidation" />
							<s:url id="manageAcademicsUrl" action="ajaxDoExamShedules"
								includeParams="all" escapeAmp="false" namespace="/exam">
							</s:url>
							<sj:a href="%{manageAcademicsUrl}" cssClass="btn default"
								targets="mainContentDiv" button="false">Cancel</sj:a>
						</s:if>
						<s:else>
							<sj:submit targets="classContentDiv" value="Submit"
								cssClass="submitBt btn blue" indicator="indicator"
								formIds="addNewExamSchedules" validate="true"
								onBeforeTopics="addClassExamValidation" />
							<s:url id="manageAcademicsUrl" action="ajaxClassTeacherExamSchedules"
								includeParams="all" escapeAmp="false" namespace="/exam">
							</s:url>
							<sj:a href="%{manageAcademicsUrl}" cssClass="btn default"
								targets="classContentDiv" button="false">Cancel</sj:a>
						</s:else>
					</div>
				</div>
 			</s:if>
		</div>
	</s:form>
</div>
</s:if>
<s:else>
	<div class="alert alert-info">
		No classes found for adding exam schedules.
	</div>
</s:else>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/json2.js">
</script><!-- this is used in ie browser -->
<script type="text/javascript">
changePageTitle('Add Exam Schedules');
$(document).ready(
		function() {
			$.destroyTopic('addClassExamValidation');
			
			//It is for getting ExamTypes list on page load if classSectionId and examTypeId are available. 
			 
			var classSectionId = $('#classSectionId').val();
			if (isNonEmpty(classSectionId)) {
				onClassChange(classSectionId);
			}
			//It prepares schedule dates and times when user submits form.
			$.subscribe('addClassExamValidation', function(event, data) {
				var selectType =$("input[name=copyExamSchedules]").val();
				var classIds = $("input[name=chkBoxSelectedIds]:checked");
				var endDate=$('span#endDateSpan').attr("class");
				var toDayDate=$('span#toDateSpan').attr("class");
				var selectedClassIds = '';
				if (classIds.length > 0) {
					selectedClassIds = '(';
					for ( var i = 0; i < classIds.length; i++) {
						selectedClassIds += classIds[i].value + ', ';
					}
					selectedClassIds += '0)';
				}
				$("#classNameIds").val(selectedClassIds);
				if($('#classSectionId').val() ==""){
					//alert("Please select class.");
					 //event.originalEvent.options.submit=false;	
				}
				else if(($("[name='scheduled']:checked").length) <= 0)
					{
						alert("Please select at least one subType");
						 event.originalEvent.options.submit=false;	
					}
					var scheduleId = '';
					var subjectId = '';
					var examDate = '';
					var startTime = '';
					var endTime = '';
					var subTypeId = '';
					var maxMarks = '';
					var jsonObj = [];
					var unSelectedSchIds='(';
					var i=0;
					var validate=true;
					var showHT='';
					$('tr.scheduleData').each(function() {
						if($(this).find("td#selectedDateDiv").find("input.examDate").val()!=""){
								//var syllabus = '';
								scheduleId = $(this).find("td.scheduleId").attr('id');
								subjectId = $(this).find("td.subjectId").attr('id');
								examDate = $(this).find(".examDate").val();
								startTime = $(this).find(".startTime").val();
								endTime = $(this).find(".endTime").val();
								subTypeId= $(this).find("td.subTypeId").attr('id');
								maxMarks = $(this).find(".maxMarks").val();
								if(isNonEmpty(subjectId) && isNonEmpty(subTypeId)){
									if($('#subTypesContent_'+subTypeId+'_'+subjectId).attr("checked")){
										/*alert($('#showHt_'+subTypeId+'_'+subjectId).attr("checked"))
										 if(!isNonEmpty($('#showHt_'+subTypeId+'_'+subjectId).attr("checked")))
											showHT="Y";
										else
											showHT ="N"; */
										if($('label#LableexamDateSchedule'+subjectId+"_"+subTypeId).length > 0){
											if(!isNonEmpty(examDate)){
												validate=false;
												$('label#LableexamDateSchedule'+subjectId+"_"+subTypeId).show();
											}											
										}
										if(!isNonEmpty(maxMarks)){
											validate=false;
											$('label#examTypeLabelmaxMarks_'+subjectId+"_"+subTypeId).show();
										}
										if($('.copySchedules').attr("checked")){
											jsonObj.push( {
												"scheduleId" : scheduleId,
												"subjectId" : subjectId,
												"examDate" : examDate,
												"startTime" : startTime,
												"endTime" : endTime,
												"subTypeId" : subTypeId,
												"maxMarks" : maxMarks/* ,
												"showHT" : showHT */
											});		
										}else{
											if(!$('#subTypesContent_'+subTypeId+'_'+subjectId).attr("disabled")){
											jsonObj.push( {
												"scheduleId" : scheduleId,
												"subjectId" : subjectId,
												"examDate" : examDate,
												"startTime" : startTime,
												"endTime" : endTime,
												"subTypeId" : subTypeId,
												"maxMarks" : maxMarks/* ,
												"showHT" : showHT */
											});											
										}											
									  }
									}else{
										if(scheduleId != 0){
											unSelectedSchIds+=scheduleId+",";
										}
									}
								}
					          }
							});
					
							unSelectedSchIds+='0)';
							jsonObj.push( {"unSelectedSchIds" : unSelectedSchIds});
					$('.tempString').val(JSON.stringify(jsonObj));
					
					//var response=$('.tempString').val();
					if(validate)
						return true;
					else
						event.originalEvent.options.submit=false;	
			});
		});
		//it is for getting class wide Examtypes on classChange.
function onClassChange(classSectionId) {
			/* $("div.alert-success").remove();
			$("div.alert-danger").remove(); */
	var examTypeId = $('.examTypeId').val();
		$('#examTypesContent').html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		var pars='';
		if(isNonEmpty(examTypeId) && isNonEmpty(classSectionId)){
			//classSectionId=classSectionId.split("_")[0];
			pars = "classId=" + classSectionId+"&examType="+examTypeId;
		}else if(isNonEmpty(classSectionId)){
			//classSectionId=classSectionId.split("_")[0];
			pars = "classId=" + classSectionId;					
		}
		$.ajax( {
			url : jQuery.url.getChatURL("/exam/ajaxGetClassExamTypes.do"),
			cache : true,
			data : pars,
			success : function(response) {
				$('#examTypesContent').html(response);
				if(isNonEmpty($("div#examTypesContent").find("div.alert-info").html())){
					$("div.examScheduleSubjectsButtons").find("input.submitBt").attr("disabled",true);
				}
				else{
					$("div.examScheduleSubjectsButtons").find("input.submitBt").attr("disabled",false);
				}
			}
		});
}

</script>