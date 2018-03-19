<%@ include file="/common/taglibs.jsp"%>
	<h4><b>
		<s:property value="studyClass.classAndSection" />
		:: Assign Teacher to Subjects </b>
	</h4>
	<p>
	<span class="label label-danger">NOTE :</span>
	Select teachers for associate subjects and hit submit button,
	<u>Class Teacher must be one of the subject Teacher</u>.</p>
<%-- 	<s:hidden name="classId" value="%{studyClass.id}"  id="classSelectedId"/> --%>
	<div class="form-body">
		<s:if test="%{allUsersSet != null}">
			<span id="radioButtonSpan"
				class="<s:property value='allUsersSet.size'/>"></span>
			<div class="form-group">
				<label class="col-md-3 control-label">
				<span class="required">*</span>Syllabus Type :
				</label>
				<div class="col-md-5">
					<s:if test="%{allUsersSet.size == 1}">
						<s:iterator value="allUsersSet">
							<s:radio id="selectSyllabus" theme="simple" 
								cssClass="syllabusClassType" checked ="checked" 
								name="studyClass.syllabusType.id" list="#{id:syllabusTypeName}" />
						</s:iterator>
					</s:if>
					<s:else>
						<s:iterator value="allUsersSet">
							<s:radio id="selectSyllabus" theme="simple"
								cssClass="syllabusClassType"
								name="studyClass.syllabusType.id" list="#{id:syllabusTypeName}" />
						</s:iterator>
					</s:else>	
				</div>
			</div>
		</s:if>
		<s:else>
			<div class="alert alert-info" onclick="javascript:clickManageSchool('manageSchool');">
				Currently there are no syllabus types.
				<s:url id="urlForManageSchoolInfo" action="ajaxDoSchoolInformation" namespace="/admin">
				</s:url>
				<sj:a href="%{urlForManageSchoolInfo}" id="manageSchoolSetteings" 
					targets="mainContentDiv" data-toggle="tab" cssClass='btn default'  button="false">
					<font color="red"><b>Click here</b></font>
				</sj:a>
				to add syllabus types.
			</div> 
			<s:hidden name="studyClass.syllabusType.id" value="0"></s:hidden>
		</s:else>
		<div id="syllabusTypeSchoolCodeDivId"></div>
		<div class="form-group">
			<label class="col-md-3 control-label">
				Education Type : 
			</label>
			<div class="col-md-5">
				<div class="clearfix">
					<div data-toggle="buttons" class="btn-group" id="educationType">
						<label class="btn blue radioMultiOption" id='<s:property value="studyClass.educationType"/>'>
							<input type="radio" class="toggle" name="studyClass.educationType" value="">None</label>
						<label class="btn blue radioMultiOption"  id='<s:property value="studyClass.educationType"/>'>
							<input type="radio" class="toggle" name="studyClass.educationType" value="General Education">General</label>
						<label class="btn blue radioMultiOption"  id='<s:property value="studyClass.educationType"/>'>
							<input type="radio" class="toggle" name="studyClass.educationType" value="Vocational Education">Vocational</label>
					</div>
				</div>
			</div>
		</div>
		<s:if test="%{tempList != null && !tempList.isEmpty()}">
			<div class="form-group">
				<label class="col-md-3 control-label">
					<span class="required">*</span>Select Medium :
				</label>
				<div class="col-md-5">
					<s:select list="tempList" listKey="id" listValue="name"
						cssClass="required form-control input-medium"
						name="studyClass.mediumId.id" headerKey="" headerValue="- Select -" />
				</div>
			</div>
		</s:if>
		<s:else>
			<s:hidden name="studyClass.mediumId.id" value="0"></s:hidden>
		</s:else>
		<div class="form-group">
			<label class="col-md-3 control-label">
				GroupNumber :
			</label>
			<div class="col-md-5">
				<sj:textfield name="studyClass.groupNumber" id="groupNumber"
					cssClass="form-control input-medium" maxlength="10" />
			</div>
		</div>
		<s:if
			test="%{studyClass.subjects != null  && !studyClass.subjects.isEmpty() }">
			<s:iterator value="studyClass.subjects">
				<div class="subjectClassTeachers">
					<div class="form-group">
						<label class="col-md-3 control-label">
							<span id='<s:property value='id'/>' class='subjectId'></span>
							<s:property value="name" />
							:
						</label>
						<div class="col-md-5">
									<s:select list="teacherList" listKey="staffId"
									listValue="PersonFullName" name="subjectStaffId"
									id="staffSubjectId%{id}" cssClass="form-control input-medium"
									onchange="javascript:savingStaffIds(%{id});" headerKey=""
									headerValue="- Select Teacher -" theme="simple" />

							<s:if test="%{expecTeacherList != null  && !expecTeacherList.isEmpty() }">
								<s:iterator value="expecTeacherList">
									<div class="row smsRestrictAllowDisplayDiv<s:property value='id'/>" id="removeStaff<s:property value='id'/><s:property value='staffId'/>" title="<s:property value='staffId'/>" style="margin-left: 15px;"> 
										<s:url id="removeStudySubject"
												action="ajaxRemoveStudyClassSubjectAssignedTecher" includeParams="all"
												escapeAmp="false" namespace="/admin">
												<s:param name="tempId" value="%{staffId}"></s:param>
												<s:param name="subjectId" value="%{id}"></s:param>
												<s:param name="studyClassId" value="%{studyClass.id}"></s:param>
										</s:url>
										<s:div cssClass="btn btn-xs red"
												onclick="javascript:confirmDialogWithTarget1(this,%{staffId},%{id});"
												id='%{removeStudySubject}' title="Delete this subject">
													<i class="fa fa-times"></i>
												</s:div>
											<b class="teacherName"><label>&nbsp;</label><s:property value='PersonFullName'/> </b>
										<br/>
									</div>
										</s:iterator>
									<span id="smsRestrictAllowDisplayDiv<s:property value='id'/>"> </span>
							</s:if>
							<s:else>
								<span id='smsRestrictAllowDisplayDiv<s:property value='id'/>'  style="display: block;"></span>
							</s:else>
						</div>
					</div>
				</div>
			</s:iterator>
			<div id="isClassTeacherPage">
				<div class="form-group">
					<label class="col-md-3 control-label">
						Class Teacher :
					</label>
					<div class="col-md-5">
						<s:select list="birthDaysListSet" listKey="staffId"
							listValue="personFullName" name="isClassTeacher" headerKey=""
							cssClass="form-control input-medium"  onchange="javascript:getIsAlreadyClassTeacher()"
							headerValue="- Select Class Teacher -" id="classTeacher"
							theme="simple">
						</s:select>
					</div>
				</div>
			</div>
			<div id="isClassTeacherDiv"></div>
		</s:if>
		<div class="form-actions fluid">
			<div class="col-md-offset-3 col-md-12">
				<s:if test="%{tempBoolean==true}">
					<sj:submit  targets="classContentDiv" value="Submit"
					cssClass="submitBt btn blue" formIds="addNewClassSubject"
					indicator="indicator" validate="true" id="disableClassTechId"
					onBeforeTopics="addTeacherSubjectValidation" />
				</s:if>
				<s:else>
					<sj:submit  targets="mainContentDiv" value="Submit"
					cssClass="submitBt btn blue" formIds="addNewClassSubject"
					indicator="indicator" validate="true" id="disableClassTechId"
					onBeforeTopics="addTeacherSubjectValidation" />
				</s:else>
					</div>
		</div>
	</div>
<script language="JavaScript" type="text/javascript">
	function savingStaffIds(subjectId) {
		var teacherId = $('#staffSubjectId'+subjectId+' option:selected').val();
		var teacherName = $('#staffSubjectId'+subjectId+' option:selected').text();
		$("span#smsRestrictAllowDisplayDiv"+subjectId).append("<div class='row' id='removeStaff"+subjectId+teacherId+"' title="+teacherId+"><span id="+teacherId+" class='teacherId'></span><div class='control-label col-md-1'><a title='Delete' indicator='indicator' class='btn btn-xs red normalDelete' style='cursor: pointer;position: absolute;width:20px;'  id='removeValues' onclick='removeValue("+teacherId+","+subjectId+")' '><i class='fa fa-times'></i></a> </div><div id='Remove"+teacherId+"'  class='col-md-3'> <b class='teacherName'><label style='width: 5%;'>&nbsp;</label>"+teacherName+" </b> </div> </div> ");
		$("span#smsRestrictAllowDisplayDiv"+subjectId).show();
		$('#staffSubjectId'+subjectId+' option[value='+teacherId+']').remove();
		var classTeacher = $('#classTeacher').val();
		var metricsAdminURL = jQuery.url
				.getChatURL("/admin/ajaxPrepareSelectedStaffsListForClassTeacherAssignment.do");
		generateStaffSubjectsData();
		var tempString = $('.tempString').val();
		var pars = "tempString=" + tempString ;
		$.ajax( {
			type : "POST",
			url : metricsAdminURL,
			data : pars,
			cache : false,
			success : function(html) {
				$("#isClassTeacherPage").html(html);
				$("#isClassTeacherPage").show();
			}
		});
	}

	function removeValue(teacherId,subjectId){
		var url = jQuery.url.getChatURL("/admin/ajaxRemoveStudyClassSubjectAssignedTecher.do");
		var studyClassId = '<s:property value="classId"/>';
		var pars = "tempId=" + teacherId+"&subjectId="+subjectId+"&studyClassId="+studyClassId;
		$.ajax( {
			url : url,
			cache : false,
			data : pars,
			dataType : 'json',
			success : function(response) {
				var status = response.status;
				if(status === 'Y'){
					var teacherName=$("#Remove"+teacherId).find("b.teacherName").text();
					$("#staffSubjectId"+subjectId).append('<option value='+teacherId+'>'+teacherName+'</option>');
					$("div#removeStaff"+subjectId+teacherId).remove();
					var metricsAdminURL = jQuery.url.getChatURL("/admin/ajaxPrepareSelectedStaffsListForClassTeacherAssignment.do");
					generateStaffSubjectsData();
					var tempString = $('.tempString').val();
					var pars = "tempString=" + tempString ;
					$.ajax( {
							type : "POST",
							url : metricsAdminURL,
							data : pars,
							cache : false,
							success : function(html) {
								$("#isClassTeacherPage").html(html);
								$("#isClassTeacherPage").show();
							}
						});
				}else{
					alert("Can't remove staff for this subject. Please contact administrator.");
				}
			}
		});
	}
	$(document).ready(function() {
	$("input:checkbox, input:radio:not('.toggle')").uniform();
	$('label.radioMultiOption').each(function(){
		 if($(this).attr('id')==$(this).children('input').val()){
			 $(this).addClass('active');
		 }
		});
	$.destroyTopic('addTeacherSubjectValidation');
		$.subscribe('addTeacherSubjectValidation', function(event, data) {
			var selected =$("input[type='radio'].syllabusClassType:checked").val();
			if (selected > 0 && isNonEmpty(selected)){
				if(selected >0){
					generateStaffSubjectsData();
				}else{
					alert("Select at least one SyllabusType");
					event.originalEvent.options.submit=false;
					}
			}else{
				alert("Select at least one SyllabusType");
				event.originalEvent.options.submit=false;
			}
				/* var radioButtonSpanSize = $('span#radioButtonSpan').attr("class");
				if(isNonEmpty(radioButtonSpanSize)){
					if (radioButtonSpanSize > 1) {
						if ($("input.syllabusClassType:checked").val() > 0) {
							generateStaffSubjectsData();
						} else {
							alert("Select at least one SyllabusType");
							event.originalEvent.options.submit=false;
						}
					}
				} */
		});
		
		var syllabusTypelength = $('input.syllabusClassType').length;
		if(parseInt(syllabusTypelength) == 1)
		{
			$.ajax({
				url : jQuery.url.getChatURL("/admin/ajaxSyllabusTypeSchoolCode.do?syllabusType.id="+$('.syllabusClassType').val()),
				cache : false,
				success : function(html) {
					$("#syllabusTypeSchoolCodeDivId").html(html);
				}
			});
		}
		
		
	});
	function generateStaffSubjectsData() {
		var subjectId = '';
		var staffId = '';
		var jsonObj = [];
		var classTeacherId = $('#classTeacher').val();
		$('div.subjectClassTeachers').each(function() {
			subjectId = $(this).find("span.subjectId").attr('id');
			$(this).find("span#smsRestrictAllowDisplayDiv"+subjectId).find("div.row").each(function(){
				staffId = $(this).attr('title');
				if (isNonEmpty(subjectId) && isNonEmpty(staffId)){
					jsonObj.push( {
						"subjectId" : subjectId,
						"staffId" : staffId
					});
				}
			});
		});
		$('.tempString').val(JSON.stringify(jsonObj));
	}
	
	/*below method used in isClassTeacherPage.jsp*/
	function getIsAlreadyClassTeacher() {
		var classTeacher = $('#classTeacher').val();
		var classSecId = '<s:property value="classId"/>';
		if(isNonEmpty(classTeacher)){
		$('#isClassTeacherDiv').html('<div align="center" style="padding:30px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		var classTechURL = jQuery.url.getChatURL("/admin/ajaxCheckIsClassTeacher.do");
		var pars = "isClassTeacher=" + classTeacher+"&classId="+classSecId;
		$.ajax( {
			type : "POST",
			url : classTechURL,
			data : pars,
			cache : false,
			dataType : 'json',
			success : function(response) {
			if(isNonEmpty(response)){
			var isClassTeachers=response.isClassTeacher;
				if(isNonEmpty(isClassTeachers)){
					$('#isClassTeacherDiv').html('<div class="alert alert-danger"> <strong>'+isClassTeachers+'</strong></div>');
					$('input#disableClassTechId').attr("disabled", true);
				}
			}
			else{
					$('input#disableClassTechId').removeAttr("disabled");
					$('#isClassTeacherDiv').html('');
				}
			}
		});
	  }
	  else{
	     alert('Please select class teacher.');
		 return false;
	  }
	}
	function clickManageSchool(checkValue) {
		$('#schoolSettingsDiv').click();
		$('li#schoolSettingsDiv').addClass('active');
		$('li#academicsDiv').removeClass('active open');
		$('li#studyClassId').removeClass('active');
		$('li#manageSchool').addClass('active');
	}
	function confirmDialogWithTarget1(event,teacherId,subjectId) {
		var studyClassId = $('#classSelectedId').val();
		var pars = "tempId=" + teacherId+"&subjectId="+subjectId+"&studyClassId="+studyClassId;
		
		thishref = $(event).attr('id');
		if ($(event).next('.question').length <= 0) {
			$(event).after('<div class="question">Are You Sure?<br/> <span class="yes">Yes</span><span class="cancel">Cancel</span></div>');
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
				
				dataType : 'json',
				success : function(response) {
					var status = response.status;
					if(status === 'Y'){
						var teacherName=$("#removeStaff"+subjectId+teacherId).find("b.teacherName").text();
						$("#staffSubjectId"+subjectId).append('<option value='+teacherId+'>'+teacherName+'</option>');
						$("div#removeStaff"+subjectId+teacherId).remove();
						$(this).parents('.question').fadeOut(300, function() {
							$(this).remove();
						});
						generateStaffSubjectsData();
					}else{
						alert("Can't remove staff for this subject. Please contact administrator.");
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
	
$('.syllabusClassType').click(function(){
	$.ajax({
		url : jQuery.url.getChatURL("/admin/ajaxSyllabusTypeSchoolCode.do?syllabusType.id="+$(this).val()),
		cache : false,
		success : function(html) {
			$("#syllabusTypeSchoolCodeDivId").html(html);
		}
	});
	
	
		/* if($(this).is(":checked"))
		{
			$('#syllabus'+$(this).val()).show();
		}
		else
		{
			$('#syllabus'+$(this).val()).hide();
		} */
		
		
		//alert($(this).is(":checked") +"---"+$(this).val());
	});
</script>
