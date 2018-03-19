<%@ include file="/common/taglibs.jsp"%>
	<s:form action="ajaxEditStaffSubjects" theme="simple" cssClass="form-horizontal"
		id="editStaffSubjects" namespace="/admin">
		<s:hidden name="classId" value="%{studyClass.id}"  id="classSelectedId"/>
		<s:hidden name="academicYearId" value="%{studyClass.academicYear.id}" />
		<s:hidden name="tempString" cssClass='tempString' />
		<div class="form-body"></div>
		<h4 class="bold pageTitle">
			Update Subject - Teacher
		</h4>
		<p>
		<span class="label label-danger">NOTE :</span>
			Select teachers for associate subjects and hit submit button.</p>
			<div class="form-body">
				<s:if test="%{studyClass.subjects != null  && !studyClass.subjects.isEmpty() }">
					
					<s:iterator value="studyClass.subjects">
						<div class="subjectClassTeachers">
									<div class="form-group">
										<label class="control-label col-md-3">
											<span id='<s:property value='id'/>' class='subjectId'></span>
											<s:property value="name" />
											:
										</label>
										<div class="col-md-6" >
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
					<div class="row">
					<div class="col-md-6">
						<div class="form-group">
								<label class="control-label col-md-5">
									Class Teacher :
								</label>
							<div class="col-md-6">
								<s:select list="birthDaysListSet" listKey="staffId" cssClass="form-control input-medium" 
									listValue="PersonFullName" name="isClassTeacher" headerKey="" 
									headerValue="- Select Class Teacher -" id="classTeacher"
									theme="simple">
								</s:select>
							</div>
						</div>
					</div>
				</div>	
				</div>
				</s:if>
				<s:else>
					<div class="alert alert-info">Subjects are not assigned for this class.</div>
				</s:else>
				<div class="spaceDiv"></div>
				<s:if test='%{#session.previousYear == "N"}'>
				<div class="form-actions fluid">
					<div class="col-md-offset-3 col-md-12">
						<sj:submit   targets="classContentDiv" value="Submit"
							cssClass="submitBt btn blue" formIds="editStaffSubjects" id="classTechId"
							validate="true" indicator="indicator"
							onBeforeTopics="editStaffSubjectValidation" />
					</div>
				</div>
		</s:if>
			</div>
</body>
	</s:form>
<script type="text/javascript">
	$(document).ready(function() {
		getClassSectionSubjectWiseStaffs();
		$.subscribe('editStaffSubjectValidation', function(event, data) {
			generateStaffSubjectsData();
			return true;
		});
		
		
	});
	function savingStaffIds(subjectId) {
		//alert('test');
		var teacherId = $('#staffSubjectId'+subjectId+' option:selected').val();
		var teacherName = $('#staffSubjectId'+subjectId+' option:selected').text();
		$("span#smsRestrictAllowDisplayDiv"+subjectId).append("<div class='row' id='removeStaff"+subjectId+teacherId+"' title="+teacherId+"><span id="+teacherId+" class='teacherId'></span><div class='control-label col-md-1'><a title='Delete' indicator='indicator' class='btn btn-xs red normalDelete' style='cursor: pointer;position: absolute;width:20px;'  id='removeValues' onclick='removeValue("+teacherId+","+subjectId+")' '><i class='fa fa-times'></i></a> </div><div id='Remove"+teacherId+"'  class='col-md-3'> <b class='teacherName'><label style='width: 5%;'>&nbsp;</label>"+teacherName+" </b> </div> </div> ");
		$("span#smsRestrictAllowDisplayDiv"+subjectId).show();
		$('#staffSubjectId'+subjectId+' option[value='+teacherId+']').remove();
		var classTeacher = $('#classTeacher').val();
		var metricsAdminURL = jQuery.url.getChatURL("/admin/ajaxPrepareSelectedStaffsListForClassTeacherAssignment.do");
		generateStaffSubjectsDataForClassTeacher();
		var tempString = $('.tempString').val();
		var pars = "tempString=" + tempString;
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
		var studyClassId = $('#classSelectedId').val();
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
					generateStaffSubjectsData();
				}else{
					alert("Can't remove staff for this subject. Please contact administrator.");
				}
			}
		});
	}
	function getClassSectionSubjectWiseStaffs() {
		//alert('1');
		var subjStaffsUrl = jQuery.url.getChatURL("/admin/ajaxPrepareClassSectionSubjectsWiseStaffsAndClassTeacher.do");
		generateStaffsData();
		var tempString = $('.tempString').val();
		//alert(tempString);
		var classSecId = $('#classSelectedId').val();
		var pars = "tempString=" + tempString+"&classId="+classSecId;
		$.ajax( {
			type : "POST",
			url : subjStaffsUrl,
			data : pars,
			cache : false,
			success : function(html) {
				$("#isClassTeacherPage").html(html);
				$("#isClassTeacherPage").show();
			}
		});
	}
	function generateStaffsData() {
		var staffId = '';
		var jsonObj = [];
		$('div.subjectClassTeachers').each(function() {
			subjectId = $(this).find("span.subjectId").attr('id');
			//alert("subject Id:"+ subjectId);
			 $(this).find(".smsRestrictAllowDisplayDiv"+subjectId).each(function(){
				staffId = $(this).attr('title');
				//alert("staff Id:" + staffId);
				if (isNonEmpty(subjectId) && isNonEmpty(staffId)){
					jsonObj.push( {
						"staffId" : staffId
					});
				}
			});
		});
		$('.tempString').val(JSON.stringify(jsonObj));
	}
	function generateStaffSubjectsData() {
		var subjectId = '';
		var staffId = '';
		var jsonObj = [];
		var classTeacherId = $('#classTeacher').val();
		$('div.subjectClassTeachers').each(function() {
			subjectId = $(this).find("span.subjectId").attr('id');
			//alert(subjectId);
			//$(this).find(".smsRestrictAllowDisplayDiv"+subjectId).each(function(){
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
		//alert(JSON.stringify(jsonObj));
		$('.tempString').val(JSON.stringify(jsonObj));
	}
	
	/*below method used in isClassTeacherPage.jsp*/
	function getIsAlreadyClassTeacher() {
		var classTeacher = $('#classTeacher').val();
		var classSecId = $('#classSelectedId').val();
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
					$('input#classTechId').attr("disabled", true);
				}
			}
			else{
					$('input#classTechId').removeAttr("disabled");
					$('#isClassTeacherDiv').html('');
				}
			}
		});
		}
		else{
		 /* alert('Please select class teacher.'); */
		 $('#isClassTeacherDiv').html('');
		  return false;
		}
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
						getClassSectionSubjectWiseStaffs();
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
	
	
	function generateStaffSubjectsDataForClassTeacher() {
		var subjectId = '';
		var staffId = '';
		var jsonObj = [];
		var classTeacherId = $('#classTeacher').val();
		$('div.subjectClassTeachers').each(function() {
			subjectId = $(this).find("span.subjectId").attr('id');
			//alert(subjectId);
			$(this).find(".smsRestrictAllowDisplayDiv"+subjectId).each(function(){
				staffId = $(this).attr('title');
				if (isNonEmpty(subjectId) && isNonEmpty(staffId)){
					jsonObj.push( {
						"subjectId" : subjectId,
						"staffId" : staffId
					});
				}
			});
			
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
		//alert(JSON.stringify(jsonObj));
		$('.tempString').val(JSON.stringify(jsonObj));
	}
</script>