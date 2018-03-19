<%@ include file="/common/taglibs.jsp"%>
	<jsp:include page="/common/messages.jsp" />
	<s:if test="%{studyClassList != null && !studyClassList.isEmpty()}">
	<s:form id="addNewExamSyllabus" action="ajaxEditClassExamSyllabus" cssClass="form-horizontal"
		method="post" theme="simple" namespace="/exam">
		<s:hidden name="examTypeId" value="%{examType}" cssClass='examTypeId' />
		<s:hidden name="tempString" cssClass='tempString' />
		<h4 class="bold pageTitle">
			Add syllabus for exam schedule
		</h4>
		<p>
		<span class="label label-danger">NOTE :</span>&nbsp;
			Select applicable class-sections and exam type then system would display input form for all the available subjects. </p>
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
									cssClass="required form-control input-medium" id="classSectionId"
									name="classId" headerKey="" headerValue="- Select Class -"
									onchange="javascript:onClassChange(this.value);" />
							</div>
						</div>
					</div>
				</div>
				<div id="examTypesContent">
				</div>
				<div id="examScheduleSubjects"></div>
				<s:if test='%{#session.previousYear == "N"}'>
				<div class="form-actions fluid sectionExamTypeButtons">
					<div class="col-md-offset-2 col-md-5">
						<s:if test='%{user.isOnlySchoolAdmin == "Y" || user.isSchoolPrincipal=="Y" || user.isSchoolDirector == "Y"}'>
							<sj:submit  targets="classContentDiv" value="Submit"
								cssClass="submitBt btn blue" indicator="indicator"
								formIds="addNewExamSyllabus" validate="true"
								onBeforeTopics="addClassExamSyllabusValidation" />
							<s:url id="manageAcademicsUrl" action="ajaxDoExamShedules"
								includeParams="all" escapeAmp="false" namespace="/exam">
							</s:url>
							<sj:a href="%{manageAcademicsUrl}" cssClass="btn default"
								 targets="mainContentDiv" button="false"
								buttonIcon="ui-icon-plus">Cancel</sj:a>
						</s:if>
						<s:else>
							<sj:submit  targets="classContentDiv" value="Submit"
								cssClass="submitBt btn blue" indicator="indicator"
								formIds="addNewExamSyllabus" validate="true"
								onBeforeTopics="addClassExamSyllabusValidation" />
							<s:url id="manageAcademicsUrls" action="ajaxClassTeacherExamSchedules"
								includeParams="all" escapeAmp="false" namespace="/exam">
							</s:url>
							<sj:a href="%{manageAcademicsUrls}" cssClass="btn default"
								targets="classContentDiv" button="false"
								buttonIcon="ui-icon-plus">Cancel </sj:a>
						</s:else>
					</div>
				</div>
			</s:if>
		</div>
</s:form>
</s:if>
<s:else>
	<div class="alert alert-info">
		No classes found for adding exam syllabus.
	</div>
</s:else>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/json2.js"></script>
<!-- this is used in ie browser -->
<script type="text/javascript">
	changePageTitle('Add Syllabus for Exam Schedules');
	$(document).ready(
			function() {
				$.destroyTopic('addClassExamSyllabusValidation');

				
				//It is for getting ExamTypes list on page load if classSectionId and examTypeId are available. 
				var classSectionId = $('#classSectionId').val();
				if (isNonEmpty(classSectionId)) {
					onClassChange(classSectionId);
				}
				//It prepares schedule dates and times when user submits form.
				$.subscribe('addClassExamSyllabusValidation', function(event, data) {
				 		var scheduleId = '';
						var syllabus='';
						var subTypeId='';
						var subjectId='';
						var jsonObj = [];
					 	var i=0;
						//var validate=true;
						var validateSyllabus = false;
					 	$('span.scheduleData').each(
								function() {
									//var syllabus = '';
									scheduleId = $(this).find("span.scheduleId").attr('id');
									subTypeId= 	$(this).find("span.subTypeId").attr('id');
									subjectId= 	$(this).find("span.classSubjectId").attr('id');
									syllabus = 	$("#syllabus_"+subjectId+"_"+subTypeId).val();
									if(!validateSyllabus)
									{
										if(isNonEmpty(syllabus))
										 {
											 validateSyllabus = true;
										 }
									}
									jsonObj.push( {"scheduleId" : scheduleId,	"syllabus" : syllabus,"subTypeId":subTypeId,"subjectId":subjectId});	 
								});
					 	if(validateSyllabus)
				 		{
					 		$('.tempString').val(JSON.stringify(jsonObj));
							//var response=$('.tempString').val();
							return true;
				 		}
					 	else if($('#classSectionId').val() ==""){
							// Nothing to show	
						}
					 	else
				 		{
				 			alert("please add at least one subject syllabus.")
				 			event.originalEvent.options.submit = false;
				 		}
				});
			});
			//it is for getting class wide Examtypes on classChange.
	function onClassChange(classSectionId) {
		var examTypeId = $('.examTypeId').val();
			$('#examTypesContent').html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
			var pars='';
			if(isNonEmpty(examTypeId) && isNonEmpty(classSectionId)){
				pars = "classId=" + classSectionId+"&examType="+examTypeId+"&anyTitle=S";
			}else if(isNonEmpty(classSectionId)){
				pars = "classId=" + classSectionId+"&anyTitle=S";					
			}
			$.ajax( {
				url : jQuery.url.getChatURL("/exam/ajaxGetClassExamTypes.do"),
				cache : true,
				data : pars,
				success : function(response) {
					$('#examTypesContent').html(response);
					 
					if(isNonEmpty($("div#examTypesContent").find("div.alert-info").html())){
						$("div.sectionExamTypeButtons").find("input.submitBt").attr("disabled",true);
					}
					else{
						$("div.sectionExamTypeButtons").find("input.submitBt").attr("disabled",false);
					}
				}
			});
	}
</script>