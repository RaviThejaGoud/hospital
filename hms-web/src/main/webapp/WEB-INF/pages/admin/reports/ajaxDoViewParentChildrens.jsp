<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i> <span class="hidden-481"> </span>
				</div>
			</div>
			<div class="portlet-body">
				<div class="tabbable tabbable-custom tabbable-full-width">
					<div id="subjectsContentDiv" class="tab-content">
						<%@ include file="/common/messages.jsp"%>
						<s:form action="ajaxDailyAttendanceReports" theme="simple"
							cssClass="form-horizontal" name="buttonName" namespace="/reports"
							id="parentChildren"
							onsubmit="return getStudentIdAndExamTypeIds();" method="post">
							<s:hidden name="tempString"></s:hidden>
							<s:hidden name="anyId" cssClass="anyId" value=""></s:hidden>
							<s:hidden id="examTypeIds" name="examType" />
							<s:hidden id="plTitle" name="plTitle"></s:hidden>
							<s:hidden id="roleName" name="username" value="ROLE_STUDENT" />
							<span id="plTitle" class="<s:property value='plTitle'/>"></span>
							<div class="form-body">
								<s:if
									test="%{viewStudentPersonAccountDetailsList != null && !viewStudentPersonAccountDetailsList.isEmpty()}">
									<div class="form-group">
										<label class="control-label col-md-2"> <span
											class="required">*</span>Student Name :
										</label>
										<div class="col-md-3">
											<s:if test="%{viewStudentPersonAccountDetailsList.size>1}">
												<s:select id="studId"
													list="viewStudentPersonAccountDetailsList"
													listKey="studentId" cssClass="required form-control"
													listValue="idAndName" name="selectedId" theme="simple"
													headerKey="" headerValue="- Select -" />
											</s:if>
											<s:else>
												<p class="form-control-static">
													<s:iterator value="viewStudentPersonAccountDetailsList">
														<input type="hidden" id="studentId" name="selectedId"
															value="studentId" />
														<span class="studentClassId" style="display: none;"><s:property
																value="studentId" /> </span>
														<s:property value="idAndName" />
													</s:iterator>
												</p>
											</s:else>
										</div>
									</div>
									<s:if test='%{plTitle == "Daily Attendance"}'>
										<div class="row">
											<div class="col-md-6">
												<div class="form-group">
													<label class="control-label col-md-4"> <span
														class="required">*</span>From Date :
													</label>
													<div class="col-md-5">
														<div data-date-end-date="+0d"
															data-date-format="mm/dd/yyyy"
															class="input-group input-medium date date-picker">
															<input id="startDate" name="selectedDate" readonly=""
																showButtonPanel="true" type="text" class="form-control">
															<span class="input-group-btn">
																<button type="button" class="btn default">
																	<i class="fa fa-calendar"></i>
																</button>
															</span>
														</div>
														<span class="help-block">(MM/DD/YYYY))</span>
													</div>
												</div>
											</div>
										</div>
									</s:if>
									<s:if
										test='%{plTitle == "Exam Schedules" || plTitle == "Class Wise Marks"}'>
										<s:if
											test="%{(examTypeList != null && !examTypeList.isEmpty())}">
											<div class="form-group">
												<div class="col-md-12">
													<div class="checkbox-list">
														<label class="checkbox-inline"> <input
															type="checkbox" name="" value=""
															onClick="checkAllExamTypes()"
															class="checkbox allExamTypes"> All ExamTypes
														</label>
													</div>
												</div>
											</div>
											<s:checkboxlist name="chkBoxExamTypeIds" list="examTypeList"
												listKey="id" listValue="examType" theme="ems"
												cssClass="small" />
										</s:if>
										<s:else>
											<div class="alert alert-info">There is no Exam Types.</div>
										</s:else>
									</s:if>
								</s:if>
								<div class="form-actions fluid">
									<div class="col-md-offset-3 col-md-9">
										<s:submit type="submit small" value="Generate Pdf"
											onclick="getFormateType()" cssClass="submitBt btn blue long"
											title="generate report" cssStyle="cursor:pointer">
										</s:submit>
									</div>
								</div>
							</div>
						</s:form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
$(document).ready( function() {
	var title ='';
	FormComponents.init();
	 $('span.hidden-481').html($('.page-sidebar-menu li.active').find('li.active').children('a').children('span.title').text().trim() + "-->"
				+ $('.page-sidebar-menu li.active').find('li.active').find('ul.sub-menu').find('li.active').children('a').text().trim())
	  title = $('.page-sidebar-menu li.active').find('li.active').find('ul.sub-menu').find('li.active').children('a').text().trim();
	 if(!isNonEmpty(title)){//this is used to parent and student logns
		 $('span.hidden-481').html($('.navbar-nav li.active').children('a').children('span.title').text().trim() + "-->"
					+ $('.navbar-nav li.active').find('li.active').find('ul.dropdown-menu').find('li.active').children('a').text().trim())
	  title = $('.navbar-nav li.active').find('li.active').find('ul.dropdown-menu').find('li.active').children('a').text().trim();
	 }
		 
 	$("input:checkbox, input:radio").uniform();
 	$("input[name=chkBoxExamTypeIds]").click(function() {
				if ($("input[name=chkBoxExamTypeIds]:unchecked").length > 0) {
				   $(".allExamTypes").parent('span').removeClass("checked");
					$(".allExamTypes").attr("checked", false);
				} else {
				    $(".allExamTypes").parent('span').addClass("checked");
					$(".allExamTypes").attr("checked", true);
				}
			});
			
		});
function checkAllExamTypes() {
		if ($(".allExamTypes").is(':checked')){
		    $("[name='chkBoxExamTypeIds']").each(function(){
			   $(this).parent('span').addClass('checked');
			    $(this).attr("checked", "true");
			});
		}
		else{
		 $("[name='chkBoxExamTypeIds']").each(function(){
			   $(this).parent('span').removeClass('checked');
			    $(this).removeAttr("checked");
			});
		}	
	}

function getFormateType() {
	$('.anyId').val('PDF');
}
function getStudentIdAndExamTypeIds(studentId) {
	$("#studentId").val($("span.studentClassId").html());
	var studentId = $("select#studId").val();
	if (studentId == '') {
		alert("Please select Student Name.");
		return false;
	}
	$('.anyId').val('PDF');
if("Daily Attendance"=='<s:property value='plTitle'/>'){
	var startDate = $('#startDate').val();
  if (startDate == '' || studentId == '') {
			alert("Please select from date.");
			return false;
	}else{
	return true;
	}
}else if("Exam Schedules"=='<s:property value='plTitle'/>' || "Class Wise Marks"=='<s:property value='plTitle'/>'){
  if ($("input[name=chkBoxExamTypeIds]:checked").length > 0) {
         var typeIds = $("input[name=chkBoxExamTypeIds]:checked");
         var selectedExamTypeIds = '';
         if (typeIds.length > 0) {
			selectedExamTypeIds = '(';
			for ( var i = 0; i < typeIds.length; i++) {
				selectedExamTypeIds += typeIds[i].value + ',';
			}
			selectedExamTypeIds += '0)';
		}
		$("#examTypeIds").val(selectedExamTypeIds);
		return true;
         }else if ($("input[name=chkBoxExamTypeIds]:checked").length == 0) {
		alert("Please select at least one Exam type");
		return false;
	} else {
		return false;
	}
 }
}
</script>

