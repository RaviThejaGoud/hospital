<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/messages.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>
					<span class="hidden-481"></span> 
				</div>
			</div>
			<div class="portlet-body">
					<div class="tab-content">
						<s:form action="ajaxDailyAttendanceReports" theme="simple"
							cssClass="form-horizontal"  name="buttonName"
							namespace="/reports" onsubmit="return generateClassIds();"
							id="classAndTodate" method="post">
							<s:hidden name="tempString"></s:hidden>
							<s:hidden id="plTitle" name="plTitle"></s:hidden>
							<s:hidden id="classNameIds" name="selectedId" />
							<s:hidden id="examTypeIdsIds" name="examType" />
							<s:hidden name="anyId" cssClass="anyId"></s:hidden>							
							<span id="plTitle" class="<s:property value='plTitle'/>"></span>
							<s:hidden id="roleName" name="username" />
							<s:hidden id="subTypeAndExamTypeId" name="subject" />
							
							<s:if test='%{studyClassList !=null && !studyClassList.isEmpty()}'>
							<div class="form-body">
							<s:if test='%{plTitle == "Daily Attendance"}'>
								<div class="form-group">
									<label class="control-label col-md-2">
										<span class="required">*</span>From Date :
									</label>
									<div class="col-md-5">
										<div data-date-end-date="+0d" data-date-format="mm/dd/yyyy"
											class="input-group input-medium date date-picker">
											<input type="text" id="startDate" readonly="readonly"
												name="selectedDate"
												value='<s:property value="todayDate"/>'
												class="required form-control input-medium" />
											<span class="input-group-btn">
												<button type="button" class="btn default">
													<i class="fa fa-calendar"></i>
												</button> </span>
										</div>
										<span class="help-block">(MM/DD/YYYY)</span>
									</div>
								</div>
							<s:if test='%{user.isSchoolStudent=="N"}'>
								<s:if
									test="%{(studyClassList != null && !studyClassList.isEmpty())}">
									<div class="grid_10">
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
														<s:checkboxlist name="chkBoxNotStudents" list="tempList2"
															listKey="id" listValue="classAndSection" theme="ems"
															cssClass="small" disabled="true" />
													</div>
												</div>
											</div>
										</s:if>
									</div>
								</s:if>
								<s:else>
									<div class="alert alert-info">
										<font color="red">Currently there are no class with
											students.</font>
									</div>
								</s:else>
							</s:if>
							</s:if>
							<s:else>
								<s:iterator value="studyClassList">
									<span id='<s:property value="id"/>' class='classSectionId'></span>
								</s:iterator>
							</s:else>
							<s:if test='%{plTitle == "Class Wise Marks"}'>
								<s:if test="%{(studyClassList != null && !studyClassList.isEmpty())}">
								<s:if test="%{(examTypeList != null && !examTypeList.isEmpty())}">
								<span class="label label-danger">NOTE :</span> Select the class and examtype to generate term wise marks report.
									<div>&nbsp;</div>
									<div class="form-group">
										<label class="control-label col-md-2" style="width: 106px;"> <span
											class="required">*</span>Select Class :
										</label>
										<div class="col-md-5">
											<s:select list="studyClassList" listKey="id"
												listValue="classAndSection" id="classSection"
												cssClass="form-control input-medium required"
												onchange="javascript:getClassExamTypes(this.value);"
												name="classId" headerKey="" headerValue="- Select -"
												theme="simple">
											</s:select>
										</div>
									</div>
									<div id="examTypesListDiv">
									</div>
								</s:if>
								<s:else>
									<div class="alert alert-info">
										<font color="red">Currently there are no examTypes.</font>
									</div>
								</s:else>
								</s:if>
								<s:else>
									<div class="alert alert-info">
										<font color="red">Currently there are no class with
											students.</font>
									</div>
								</s:else>
							</s:if>
							<div class="form-actions fluid">
								<div class="col-md-offset-3 col-md-9">
									<s:if test='%{plTitle == "Class Wise Marks"}'>
										<s:submit type="submit small" value="Generate Excel"
										cssClass="submitBt btn blue long Excel generateReport" title="generate report" />
									</s:if>
									<s:else>
										<s:submit type="submit small" value="Generate Pdf" cssClass="submitBt btn blue long PDF generateReport" title="generate report" /> 
									</s:else>
								</div>
							</div>
							</div>
							</s:if>
							<s:else>
								<div class="alert alert-info">
									Currently there are no classes assigned for you.
								</div>
							</s:else>
						</s:form>
					</div>
					<span id="loginRoleName" style="display: none;"><s:property
						value="lastName" /> </span>
				</div>
			</div>
		</div>
	</div>
<div id="schoolTermlist"></div>
<script type="text/javascript">
	$(document).ready(function() {
		FormComponents.init();
		$("div#examTypesListDiv").hide();
		var title='';
		 $('span.hidden-481').html($('.page-sidebar-menu li.active').find('li.active').children('a').children('span.title').text().trim() + "-->"
				+ $('.page-sidebar-menu li.active').find('li.active').find('ul.sub-menu').find('li.active').children('a').text().trim())
			 title = $('.page-sidebar-menu li.active').find('li.active').find('ul.sub-menu').find('li.active').children('a').text().trim();
		 if(!isNonEmpty(title)){
			 $('span.hidden-481').html($('.navbar-nav li.active').children('a').children('span.title').text().trim() + "-->"
						+ $('.navbar-nav li.active').find('li.active').find('ul.dropdown-menu').find('li.active').children('a').text().trim())
		  title = $('.navbar-nav li.active').find('li.active').find('ul.dropdown-menu').find('li.active').children('a').text().trim();
		 }
				changePageTitle(title);
				$("input:checkbox, input:radio").uniform();
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
	$('input.generateReport').click(function() {
		if ($(this).hasClass('PDF')) {
			$('.anyId').val('PDF');
		} else if ($(this).hasClass('Excel')) {
			$('.anyId').val('Excel');
		}
	});
	
	function generateClassIds() {
		var selectedTitle = '<s:property value="plTitle"/>';
		var loginName = $('span#loginRoleName').html();
		loginName = jQuery.trim(loginName);
		var radioButtonSpanSize = $('span#plTitle').attr("class");
		if ("Class Wise Marks" == $('span#plTitle').attr("class")) {
			var selectedClass = $("#classSection").val();
			if(isNonEmpty(selectedClass)){
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
				
			var subTypesAndExamTypes=[] ;
			var subCount=0;
			$("input[name=subtypeAndExamId]").each(function() {
				subCount+=parseInt(subCount)+ parseInt($(this).val());
			});
			//alert(subCount);
			if(subCount==0){
				alert("Please enter the percentage value for the respected subtypes.");
				return false;
			} 
			else if ($("input[name=chkBoxExamTypeIds]:checked").length == 0) {
				alert("Please select at least one Exam type");
				return false;
			}
			else if (($("input[name=subtypeAndExamId]").length) > 0){
					$("input[name=subtypeAndExamId]").each(function() {
						if($(this).val() !=0){
							subTypesAndExamTypes.push($(this).attr('id')+"_"+$(this).val());
						}
					});
					//alert(subTypesAndExamTypes);
					$("#subTypeAndExamTypeId").val(subTypesAndExamTypes);
				}
			  } else{
				  alert("Please select at least one Exam Type");
					return false;
			  }
			}
			else{
				alert("Please select at least one Class");
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
				$("#classNameIds").val(
						'(' + $('span.classSectionId').attr('id') + ',0)');
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
		}
	}
	function getClassExamTypes(classId) {
		if (isNonEmpty(classId)) {
			var plTitle = "Class Wise Marks";
			$('#examTypesListDiv') .html( '<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
			var pars = "classId=" + classId +"&plTitle="+plTitle;
			$.ajax( { 
					url : jQuery.url.getChatURL("/exam/ajaxGetClassExamTypesForMarksTemplate.do"),
					cache : true,
					data : pars,
					success : function(response) {
						$('#examTypesListDiv').html(response);
						$("div#examTypesListDiv").show();
					}
				});
		}
	}
</script>