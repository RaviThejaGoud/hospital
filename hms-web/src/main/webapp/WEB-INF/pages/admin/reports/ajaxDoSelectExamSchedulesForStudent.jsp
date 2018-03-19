<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>
					<span class="hidden-481"> </span>
				</div>
			</div>
			<div class="portlet-body" id="studRegister">
					<div class="tab-content">
						<%@ include file="/common/messages.jsp"%>
						<s:if
							test='%{(user.isOnlySchoolAdmin=="Y" && customer.hostelModuleStatus) || (user.isSchoolPrincipal=="Y" || user.isSchoolDirector == "Y" && customer.hostelModuleStatus=="Y")}'>
							<s:if test='%{plTitle != "ViewStudentClassSectionDetails"}'>
								<div class="form-group">
									<label class="col-md-2 control-label">
										Select Students for	:
									</label> 
									<div class="radio-list">
										<label class="radio-inline">
											<input type="radio" id="student" value="ROLE_STUDENT"
												class="radio" name="selectedName" checked="checked">
											School
										</label>
										<label class="radio-inline">
											<input type="radio" id="staff" value="ROLE_HOSTEL"
												class="radio" name="selectedName">
											Hostel
										</label>
									</div>
								</div>
							</s:if>
						</s:if>
						<s:if
							test='%{(plTitle == "Exam Schedules" && tempString == "Student")}'>
							<s:if test='%{examTypeList.size >0}'>
								<s:form action="printExamSchedulesForMultipleClasses"
									 theme="simple" namespace="/student"
									cssClass="form-horizontal"
									onsubmit="return generateExamTypeIds();" id="classAndCommunity"
									method="post">
									<s:hidden name="tempString"></s:hidden>
									<s:hidden name="plTitle"></s:hidden>
									<s:hidden name="anyId" cssClass="anyId" value=""></s:hidden>
									<s:hidden id="classNameIds" name="SelectedId" />
									<s:hidden id="examTypeIds" name="examType" />
									<s:hidden id="roleName" name="username" />
									<s:hidden id="anyTitle" name="anyTitle" />
									<div class="form-body">
									<s:if test='%{user.isSchoolStudent=="N"}'>
											<s:if test='%{studyClassList.size >1}'>
												<div class="form-group">
													<div class="col-md-12">
														<div class="checkbox-list">
															<label class="checkbox-inline">
																	<input type="checkbox" name=""
																			value="" onClick="checkAllClasses()"
																			class="checkbox allClasses">
																All Class & Sections
															</label>
														</div>
													</div>
												</div>
											</s:if>
											<div class="form-group">
												<label class="conLable col-md-3 control-label">
													Class With Students :
												</label>
												<div class="col-md-12">
													<div class="checkbox-list">
														<s:checkboxlist name="chkBoxSelectedIds"
															list="studyClassList" listKey="id"
															listValue="classAndSection" theme="ems" cssClass="small" />
													</div>
												</div>
											</div>
											<s:if test='%{tempList2.size >0}'>
												<div class="form-group">
													<label class="conLable col-md-3 control-label">
														Class With Out Students :
													</label>
													<div class="col-md-12">
														<div class="checkbox-list">
															<s:checkboxlist name="chkBoxNotStudents"
																list="tempList2" listKey="id"
																listValue="classAndSection" theme="ems"
																cssClass="small" disabled="true" />
														</div>
													</div>
												</div>
											</s:if>
									</s:if>
									<s:else>
										<s:iterator value="studyClassList">
											<span id='<s:property value="id"/>' class='classSectionId'></span>
										</s:iterator>
									</s:else>
									<div>
										<h4 class="bold pageTitle">Exam Types</h4>
									</div>
									<s:if test='%{examTypeList.size >0}'>
										<div class="form-group">
											<div class="col-md-12">
												<div class="checkbox-list">
													<label class="checkbox-inline">
														<input type="checkbox" name=""
																value="" onClick="checkallExamTypes()"
																class="checkbox allExamTypes">
														All Exam Types
													</label>
												</div>
												<s:if test='%{(user.isOnlySchoolAdmin=="N" && user.isSchoolStudent=="N")}'>
													<s:checkboxlist name="chkBoxExamTypeIds" list="examTypeList"
														listKey="examTypeId" listValue="examTypeName" theme="ems"
														cssClass="small" />
												</s:if>
												<s:else>
													<s:checkboxlist name="chkBoxExamTypeIds" list="examTypeList"
													listKey="id" listValue="examType" theme="ems"
													cssClass="small" />
												</s:else>
											</div>
										</div>
									</s:if>
									<div class="form-group">
											<label class="col-md-2 control-label">
												Sort By :
											</label>
											<div class="col-md-5">
												<s:select id="OrderName"
													list="#{'SO':'Subject order','ED':'Exam Date'}"
													cssClass="form-control input-medium" headerKey=""
													name="OrderName" onchange="orderChange(this.value);" />
											</div>
										</div>
									<div class="form-actions fluid">
										<div class="col-md-offset-3 col-md-9">
											<s:submit type="submit small" value="Generate Pdf"
												onclick="reportType()" cssClass="submitBt btn blue long"
												title="generate report" />
										</div>
									</div>
									</div>
								</s:form>
							</s:if>
							<s:else>
								<div class="alert alert-info">
									Currently there are no examTypes assigned for classes.
								</div>
							</s:else>
						</s:if>
					</div>
				</div>
			</div>
		</div>
</div>
<span id="loginRoleName" style="display: none;"><s:property
		value="user.userRoleDescription" /> </span>
<script type="text/javascript">
	$(document).ready(function() {
		$("input:checkbox, input:radio").uniform();
		var title ='';
		 $('span.hidden-481').html($('.page-sidebar-menu li.active').find('li.active').children('a').children('span.title').text().trim() + "-->"
				+ $('.page-sidebar-menu li.active').find('li.active').find('ul.sub-menu').find('li.active').children('a').text().trim())
		 title = $('.page-sidebar-menu li.active').find('li.active').find('ul.sub-menu').find('li.active').children('a').text().trim();
		 if(!isNonEmpty(title)){//this is used to parent and student logns
			 $('span.hidden-481').html($('.navbar-nav li.active').children('a').children('span.title').text().trim() + "-->"
						+ $('.navbar-nav li.active').find('li.active').find('ul.dropdown-menu').find('li.active').children('a').text().trim())
		  title = $('.navbar-nav li.active').find('li.active').find('ul.dropdown-menu').find('li.active').children('a').text().trim();
		}
		changePageTitle(title);
			$("input[name=chkBoxSelectedIds]").click(function() {
				if ($("input[name=chkBoxSelectedIds]:unchecked").length > 0) {
				   $(".allClasses").parent('span').removeClass("checked");
					$(".allClasses").attr("checked", false);
				} else {
				    $(".allClasses").parent('span').addClass("checked");
					$(".allClasses").attr("checked", true);
				}
			});
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
	function reportType() {
		$('.anyId').val('PDF');
	}
	function generateExamTypeIds() {
		var loginName = $('span#loginRoleName').html();
		loginName = jQuery.trim(loginName);
		if (loginName == 'Student') {
			if ($("input[name=chkBoxExamTypeIds]:checked").length > 0) {
				var examType = $("input[name=chkBoxExamTypeIds]:checked");
				var selectedExamTypeIds = '';
				if (examType.length > 0) {
					selectedExamTypeIds = '(';
					for ( var i = 0; i < examType.length; i++) {
						selectedExamTypeIds += examType[i].value + ', ';
					}
					selectedExamTypeIds += '0)';
				}
				$("#examTypeIds").val(selectedExamTypeIds);
				$("#classNameIds").val($('span.classSectionId').attr('id'));
				var value = $("input[name='selectedName']:checked").val();
				if (isNonEmpty(value)) {
					$("#roleName").val(value);
				} else {
					$("#roleName").val('ROLE_STUDENT');
				}
				return true;
			} else if ($("input[name=chkBoxExamTypeIds]:checked").length == 0) {
				alert("Please select at least one examType");
				return false;
			}
		} else {
			//get classIds
			if (($("input[name=chkBoxSelectedIds]:checked").length > 0)	&& ($("input[name=chkBoxExamTypeIds]:checked").length > 0)) {
				var classIds = $("input[name=chkBoxSelectedIds]:checked");
				var selectedClassIds = '';
				if (classIds.length > 0) {
					selectedClassIds = '(';
					for ( var i = 0; i < classIds.length; i++) {
						selectedClassIds += classIds[i].value + ', ';
					}
					selectedClassIds += '0)';
				}
				$("#classNameIds").val(selectedClassIds);
	
				var examType = $("input[name=chkBoxExamTypeIds]:checked");
				var selectedExamTypeIds = '';
				if (examType.length > 0) {
					selectedExamTypeIds = '(';
					for ( var i = 0; i < examType.length; i++) {
						selectedExamTypeIds += examType[i].value + ', ';
					}
					selectedExamTypeIds += '0)';
				}
				$("#examTypeIds").val(selectedExamTypeIds);
	
				//getting role for reports(School OR Hostel)
				var value = $("input[name='selectedName']:checked").val();
				if (isNonEmpty(value)) {
					$("#roleName").val(value);
				} else {
					$("#roleName").val('ROLE_STUDENT');
				}
				return true;
			} else {
				if ($("input[name=chkBoxSelectedIds]:checked").length == 0) {
					alert("Please select at least one Class");
					return false;
				}
				if ($("input[name=chkBoxExamTypeIds]:checked").length == 0) {
					alert("Please select at least one examType");
					return false;
				}
			}
		}
	}
	function checkAllClasses() {
		if ($(".allClasses").is(':checked')){
		    $("[name='chkBoxSelectedIds']").each(function(){
			   $(this).parent('span').addClass('checked');
			    $(this).attr("checked", "true");
			});
		}
		else{
		 $("[name='chkBoxSelectedIds']").each(function(){
			   $(this).parent('span').removeClass('checked');
			    $(this).removeAttr("checked");
			});
		}	
	}
	function checkallExamTypes() {
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
	function orderChange(value) {
		$('input#anyTitle').val($('select#OrderName option:selected').val());
	}
</script>