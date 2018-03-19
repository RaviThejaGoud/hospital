<%@ include file="/common/taglibs.jsp"%>
<div id="createAttendenceDiv">
	<%@ include file="/common/messages.jsp"%>
	<s:if test='%{anyTitle == "Yes"}'>
		<s:form action="ajaxDailyAttendanceReports" theme="simple"
			cssClass="form-horizontal" name="buttonName"
			namespace="/reports" onsubmit="return generateClassIds();"
			id="classAndTodate" method="post">
			<div class="form-body">
			<s:hidden id="classNameIds" name="selectedId" />
			<s:hidden id="examTypeIdsIds" name="examType" />
			<s:hidden id="plTitle" name="plTitle" />
			<span id="plTitle" class="<s:property value='plTitle'/>"></span>
			<s:hidden id="roleName" name="username" />
			<s:hidden id="selectedDate" name="selectedDate" />
			<s:if
					test="%{(studyClassList != null && !studyClassList.isEmpty())}">
			<s:if test='%{plTitle == "Daily Attendance"}'>
				<div class="form-group">
					<label class="col-md-2 control-label" style="width: 105px;">
						Select Status :
					</label>
					<div class="col-md-9">
						<div class="checkbox-list">
							<div id="Attendance">
								<label class="checkbox-inline col-md-2">
									<input type="checkbox" name="presentVal" id="chkBoxClassIdsforattendance"
										value="P" class="mbc checkByValue">
									Present
								</label>
							</div>
							<div id="MobileEmail">
								<label class="checkbox-inline col-md-2">
									<input type="checkbox" name="absentVal" id="chkBoxClassIdsforattendance"
										value="A" class="mec checkByValue" />

									Absent
								</label>
							</div>
						</div>
					</div>
				</div>
				</s:if>
				<s:if test='%{user.isSchoolStudent=="N"}'>
				<s:if
					test="%{(studyClassList != null && !studyClassList.isEmpty())}">
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
									<s:checkboxlist name="chkBoxSelectedIds" list="studyClassList"
										listKey="id" listValue="classAndSection" theme="ems"
										cssClass="small" />
								</div>
							</div>
						</div>
						<s:if test='%{tempList1.size >0}'>
							<div class="form-group">
								<label class="conLable col-md-3 control-label">
									Class With Holiday :
								</label>
								<div class="col-md-12">
									<div class="checkbox-list">
										<s:checkboxlist name="chkBoxNotStudents" list="tempList1"
											listKey="id" listValue="classAndSection" theme="ems"
											cssClass="small" disabled="true" />
									</div>
								</div>
							</div>
						</s:if>
						<s:if test='%{tempList2.size >0}'>
							<div class="form-group">
								<label class="conLable col-md-3 control-label">
									Class With Out Students :
								</label>
								<div class="col-md-12">
									<div class="checkbox-list">
										<s:checkboxlist name="chkBoxNotStudents" list="tempList2"
											listKey="id" listValue="classAndSection" theme="ems"
											cssClass="small" disabled="true" />
									</div>
								</div>
							</div>
						</s:if>
				</s:if>
				<s:else>
					<div class="alert alert-info">
						<font color="red">Currently there are no class with
							students.</font>
					</div>
				</s:else>
			</s:if>
			</s:if>
			<s:elseif test="%{studyClassList.isEmpty()}">
				<s:if test='%{tempList1.size >0}'>
					<div class="alert alert-danger">
						All class have holiday.
					 </div>
				</s:if>
				<s:else>
					<div class="alert alert-info">
						Currently there are no classes assigned for you.
					</div>
				</s:else>
			</s:elseif>
			<s:else>
				<s:iterator value="studyClassList">
					<span id='<s:property value="id"/>' class='classSectionId'></span>
				</s:iterator>
			</s:else>
			<s:if test='%{plTitle == "Class Wise Marks"}'>
				<s:if test="%{(examTypeList != null && !examTypeList.isEmpty())}">
					<div class="form-group">
						<label class="col-md-3 control-label">
							<span class="required">*</span> Available Communities :
						</label>
						<div class="col-md-12">
							<div class="checkbox-list">
								<label class="checkbox-inline">
									<input type="checkbox" name="" value=""
											onClick="checkAllExamTypes()" class="checkbox allExamTypes">
									<font color="#1AB7EA">All ExamTypes</font>
								</label>
							</div>
							<s:checkboxlist name="chkBoxExamTypeIds" list="examTypeList"
								listKey="id" listValue="examType" theme="ems" cssClass="small" />
						</div>
					</div>
				</s:if>
				<s:else>
					<div class="alert alert-info">
						<font color="red">Currently there are no examTypes.</font>
					</div>
				</s:else>
			</s:if>
			<span id="loginRoleName" style="display: none;"><s:property
					value="lastName" /> </span>
			<div id="schoolTermlist"></div>
			<div class="form-actions fluid">
				<div class="col-md-offset-3 col-md-9">
					<s:submit type="submit small" value="Generate Pdf"
						cssClass="submitBt btn blue long" title="generate report" />
				</div>
			</div>
			</div>
		</s:form>
	</s:if>
</div>
<script type="text/javascript">
	$(document).ready(function() {
	 $("input:checkbox, input:radio").uniform();
	 var title ='';
	 $('span.hidden-481').html($('.page-sidebar-menu li.active').find('li.active').children('a').children('span.title').text().trim() + "-->"
							+ $('.page-sidebar-menu li.active').find('li.active').find('ul.sub-menu').find('li.active').children('a').text().trim())
		title = $('.page-sidebar-menu li.active').find('li.active').find('ul.sub-menu').find('li.active').children('a').text().trim();
	 if(!isNonEmpty(title)){ //this is used to parent and student logns
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

	function generateOnlyClassIds() {
		if ($("input[name=chkBoxSelectedIds]:checked").length > 0) {
			var classIds = $("input[name=chkBoxSelectedIds]:checked");
			var selectedClassIds = '';
			if (classIds.length > 0) {
				selectedClassIds = '(';
				for ( var i = 0; i < classIds.length; i++) {
					selectedClassIds += classIds[i].value + ',';
				}
				selectedClassIds += '0)';
			}
			$("#classNameIds").val(selectedClassIds);
			var value = $("input[name='selectedName']:checked").val();
			$("#roleName").val(value);
			return true;
		} else if ($("input[name=chkBoxSelectedIds]:checked").length == 0) {
			alert("Please select at least one Class");
			return false;
		} else {
			return false;
		}
	}

	function generateClassIds() {
		var loginName = $('span#loginRoleName').html();
		loginName = jQuery.trim(loginName);
		var radioButtonSpanSize = $('span#plTitle').attr("class");
		if ("Class Wise Marks" == $('span#plTitle').attr("class")) {
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
				$("#examTypeIdsIds").val(selectedExamTypeIds);

				//Genarate classIDs 
				if (loginName == 'Student') {
					$("#classNameIds").val($('span.classSectionId').attr('id'));
					return true;
				} else {
					if ($("input[name=chkBoxSelectedIds]:checked").length == 0) {
						alert("Please select at least one Class");
						return false;
					} else {
						if ($("input[name=chkBoxSelectedIds]:checked").length > 0)
							generateOnlyClassIds();
					}
				}
			} else if ($("input[name=chkBoxExamTypeIds]:checked").length == 0) {
				alert("Please select at least one Exam type");
				return false;
			} else {
				return false;
			}
		} else {
			var startDate = $('#startDate').val();
			if (startDate == '') {
				alert("Please select from date.");
				return false;
			}
			if (loginName == 'Student') {
				//only student login 
				$("#classNameIds").val($('span.classSectionId').attr('id'));
				return true;
			} else {
				if ($("input[name=chkBoxSelectedIds]:checked").length == 0) {
					alert("Please select at least one Class");
					return false;
				}
				 else if ($("#chkBoxClassIdsforattendance:checked").length == 0) {
				  if ("Daily Attendance" == $('span#plTitle').attr("class")) {
					alert("Please select at least one attendance status");
					return false;
				}
				else {
					if ($("input[name=chkBoxSelectedIds]:checked").length > 0)
						generateOnlyClassIds();
				}
				}
				 else {
					if ($("input[name=chkBoxSelectedIds]:checked").length > 0)
						generateOnlyClassIds();
				}
			}
		}
	}
</script>