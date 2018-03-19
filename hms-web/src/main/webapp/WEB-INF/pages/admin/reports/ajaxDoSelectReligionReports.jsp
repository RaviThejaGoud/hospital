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
							<s:if test='%{plTitle != "ViewStudentClassSectionDetails" && plTitle != "ClassWiseFineFeeDetails"}'>
							 <s:if test='%{tempString == "Student"}'>
								<div class="form-group" id="classWiseStuDetailsPdfDetails">
									<label class="control-label col-md-2">
										<span class="required">*</span>Select Students for :
									</label>
									<div class="col-md-7">
										<div class="make-switch has-switch" data-id="ROLE_STUDENT" data-value="ROLE_HOSTEL"
											style="width: 120px" data-off="warning" data-on="success"
											data-off-label="Hostel" data-on-label="School">
											<input type="radio" class="toggle" checked="checked"
												id="gender">
											<input type="hidden" name="selectedName" value="ROLE_STUDENT">
										</div>
									</div>
								</div>
								
								<div class="spaceDiv"></div>
								<br/>
								<!--<div class="form-group"  
									<label class="col-md-2 control-label">
										Select Students for :
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
							-->
							</s:if>
							</s:if>
						</s:if>
						<s:if test='%{plTitle == "ReligionDetails"}'>
							<s:if test="%{(tempList != null && !tempList.isEmpty())}">
								<s:form action="ajaxStudentsReligionWiseDetails" theme="simple"
									cssClass="form-horizontal"
									onsubmit="return generateStudentReligionIds();"
									id="classAndCommunity" method="post" namespace="/reports">
									<s:hidden name="tempString"></s:hidden>
									<s:hidden name="plTitle"></s:hidden>
									<s:hidden name="anyId" cssClass="anyId" value=""></s:hidden>
									<s:hidden id="religionNameIds" name="anyTitle" />
									<s:hidden id="roleName" name="username" />
									<div class="form-body">
										<div class="form-group">
											<label class="conLable col-md-3 control-label">
												<span class="required">*</span> Available Religions :
											</label>
											<div class="col-md-12">
												<div class="checkbox-list">
													<label class="checkbox-inline">
															<input type="checkbox" name=""
																	value="" onClick="checkallReligions()"
																	class="checkbox allReligions">
														All Religions
													</label>
												</div>
												<s:checkboxlist name="chkBoxSelectedIds" list="tempList"
													listKey="id" listValue="skillTypeName" theme="ems"
													cssClass="small" />
											</div>
										</div>
										<div class="form-actions fluid">
											<div class="col-md-6">
												<div class="col-md-offset-3 col-md-9">
													<s:submit type="submit small" value="Generate Excel"
														cssClass="submitBt btn blue long" title="generate report"
														onclick="reportFormate()">
													</s:submit>
													<s:submit type="submit small" value="Generate Pdf"
														onclick="reportType()" cssClass="submitBt btn blue long"
														title="generate report">
													</s:submit>
												</div>
											</div>
										</div>
									</div>
								</s:form>
							</s:if>
							<s:else>
								<div class="alert alert-info">
									Religions are not available.
								</div>
							</s:else>
						</s:if>
						<s:elseif
							test='%{plTitle == "CommunityDetails" && tempString == "Staff"}'>
							<s:if
								test="%{(castSettingList != null && !castSettingList.isEmpty())}">
								<s:form action="ajaxselectedTypeReports" theme="simple"
									cssClass="form-horizontal"
									onsubmit="return generateClassCommunityIds();"
									id="classAndCommunity" method="post" namespace="/reports">
									<s:hidden name="tempString"></s:hidden>
									<s:hidden name="plTitle"></s:hidden>
									<s:hidden name="anyId" cssClass="anyId" value=""></s:hidden>
									<s:hidden id="communityIds" name="anyTitle" />
									<s:hidden id="roleName" name="username" />
									<div class="form-body">
										<div class="form-group">
											<label class="conLable col-md-3 control-label">
												<span class="required">*</span> Available Communities :
											</label>
											<div class="col-md-12">
												<div class="checkbox-list">
													<label class="checkbox-inline">
															<input type="checkbox" name=""
																	value="" onClick="checkAllCommunities()"
																	class="checkbox allCommunities">
														All Communities
													</label>
												</div>
												<s:checkboxlist name="communityCheckBoxes"
													list="castSettingList" listKey="id" listValue="castName"
													theme="ems" cssClass="small" />
											</div>
										</div>
										<div class="form-actions fluid">
											<div class="col-md-6">
												<div class="col-md-offset-3 col-md-9">
													<s:submit type="submit small" value="Generate Excel"
														cssClass="submitBt btn blue long" title="generate report"
														onclick="reportFormate()">
													</s:submit>
													<s:submit type="submit small" value="Generate Pdf"
														onclick="reportType()" cssClass="submitBt btn blue long"
														title="generate report">
													</s:submit>
												</div>
											</div>
										</div>
									</div>
								</s:form>
							</s:if>
						</s:elseif>
						<s:elseif
							test='%{(plTitle == "CommunityDetails" && tempString == "Student") || (plTitle == "ClassWiseStudentDetails" && tempString == "Student") || (plTitle == "MediumWiseDetails" && tempString == "Student") || (plTitle == "GenderwiseDetails") || (plTitle == "ViewStudentClassSectionDetails" && tempString == "Student") || (plTitle == "ClassWiseFineFeeDetails")}'>
							<s:form action="ajaxselectedTypeReports" theme="simple"
								cssClass="form-horizontal" onsubmit="return generateClassIds();"
								id="classAndCommunity" method="post" namespace="/reports">
								<s:hidden name="tempString"></s:hidden>
								<s:hidden name="plTitle"></s:hidden>
								<s:hidden name="anyId" cssClass="anyId" value=""></s:hidden>
								<s:hidden id="classNameIds" name="SelectedId" />
								<s:hidden id="roleName" name="username" />
								<div class="form-body">
									<s:if	test='%{plTitle == "GenderwiseDetails" || plTitle == "ViewStudentClassSectionDetails" }'>
									 <s:if	test='%{plTitle == "GenderwiseDetails"}'>
										<h4 class="pageTitle bold">Download Student Strength Report</h4>
									</s:if>
									<s:elseif
										test='%{plTitle == "GenderwiseDetails" || plTitle == "ViewStudentClassSectionDetails" }'>
										<h4 class="pageTitle bold">Download student register details</h4>
									</s:elseif>
											<s:if test='%{studyClassList.size >0}'>
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
											<s:if test='%{studyClassList.size >0}'>
												<div class="form-group">
													<label class="conLable col-md-3 control-label">
														<span class="required">*</span> Class With Students :
													</label>
													<div class="col-md-12">
														<div class="checkbox-list">
															<s:checkboxlist name="chkBoxSelectedIds"
																list="studyClassList" listKey="id"
																listValue="classAndSection" theme="ems" cssClass="small" />
														</div>
													</div>
												</div>
											</s:if>
											<s:if
												test='%{tempList2.size == 0 && studyClassList.size == 0}'>
												<font style="color: red"> You have not created any
													Classes. You can able to create Class & Section.</font>
											</s:if>
									</s:if>
									<s:else>
											<s:if test='%{classList.size >0}'>
												<div class="form-group">
													<div class="col-md-12">
														<div class="checkbox-list">
															<label class="checkbox-inline">
																	<input type="checkbox" name=""
																			value="" onClick="checkAllClasses()"
																			class="checkbox allClasses">
																All Classes 
															</label>
														</div>
													</div>
												</div>
											</s:if>
											<s:if test='%{classList.size >0}'>
												<div class="form-group">
													<label class="conLable col-md-3 control-label">
														<span class="required">*</span> Classes With Students :
													</label>
													<div class="col-md-12">
														<div class="checkbox-list">
															<s:checkboxlist name="chkBoxSelectedIds" list="classList"
																listKey="id" listValue="className" theme="ems"
																cssClass="small" />
														</div>
													</div>
												</div>
											</s:if>
											<s:if test='%{classNameList.size >0}'>
												<div class="form-group">
													<label class="conLable col-md-3 control-label">
														<span class="required">*</span> Classes With Out Students :
													</label>
													<div class="col-md-12">
														<div class="checkbox-list">
															<s:checkboxlist name="chkBoxNotStudents"
																list="classNameList" listKey="id" listValue="className"
																theme="ems" cssClass="small" disabled="true" />
														</div>
													</div>
												</div>
											</s:if>
											<s:if
												test='%{classNameList.size == 0 && classList.size == 0}'>
												<div>
													<font style="color: red">You have not created any
														Classes. You can able to create Class & Section.</font>
												</div>
											</s:if>
											<div id="activeInactive" style="display: none;">
											<s:if test='%{plTitle != "ViewStudentClassSectionDetails"}'>
												<div class="form-group" id="classWiseStuDetailsPdfDetails">
													<div class="col-md-7">
														<div class="make-switch has-switch" data-id="Y"
															data-value="N" style="width: 120px" data-off="warning"
															data-on="success" data-off-label="Inactive"
															data-on-label="Active">
															<input type="radio" class="toggle" checked="checked"
																id="active">
															<input type="hidden" name="anyTitle" value="Y">
														</div>
													</div>
												</div>
											</s:if>
										</div>
									</s:else>
									<div class="form-actions fluid">
											<div class="col-md-offset-2 col-md-9">
											
													<s:submit type="submit" value="Generate Excel"
														  cssClass="submitBt btn blue" cssStyle="float:left;"
														title="generate report" onclick="reportFormate()">
													</s:submit>
											<%-- 	<div id="classWiseStuDetailsPdf">
													<s:if
														test='%{plTitle != "ViewStudentClassSectionDetails"}'>
														<s:submit type="submit" value="Generate Pdf" 
															onclick="reportType()" cssClass="submit btn blue"
															title="generate report" cssStyle="float:left;margin-left:10px;">
														</s:submit>
													</s:if>
												</div> --%>
										</div>
									</div>
								</div>
							</s:form>
							<s:if test='%{plTitle == "ViewStudentClassSectionDetails" }'>
								<jsp:include
									page="/WEB-INF/pages/admin/reports/importStudentRegisterDetails.jsp"></jsp:include>
							</s:if>
						</s:elseif>
					</div>
				</div>
			</div>
		</div>
	</div>
<span id="loginRoleName" style="display: none;"><s:property
		value="user.userRoleDescription" /> </span>
<script  type="text/javascript" src="${pageContext.request.contextPath}/plugins/bootstrap-switch/static/js/bootstrap-switch.min.js"></script>
<script  type="text/javascript">
$(document).ready(function() {
	FormAdvanced.init();
	$("input:checkbox, input:radio:not('.toggle')").uniform();  
			if ($('li#studentDetails').hasClass('active')) {
				$('#classWiseStuDetailsPdf').hide();
				$('#classWiseStuDetailsPdfDetails').hide();
				$('#activeInactive').show();
			}
			var str = $('.page-sidebar-menu li.active').find('li.active').find('ul.sub-menu').find('li.active').children('a').text().trim();
			if(isNonEmpty(str)){
				$('span.hidden-481').html($('.page-sidebar-menu li.active').find('li.active').children('a').children('span.title').text().trim() + "-->"
						+ $('.page-sidebar-menu li.active').find('li.active').find('ul.sub-menu').find('li.active').children('a').text().trim())
			}else{
				$('span.hidden-481').html($('.page-sidebar-menu li.active').find('li.active').children('a').children('span.title').text().trim())
			}
			/* $('span.hidden-481').html($('.page-sidebar-menu li.active').find('li.active').children('a').children('span.title').text().trim() + "-->"
							+ $('.page-sidebar-menu li.active').find('li.active').find('ul.sub-menu').find('li.active').children('a').text().trim()) */
			var title = $('.page-sidebar-menu li.active').find('li.active').find('ul.sub-menu').find('li.active').children('a').text().trim();
			changePageTitle(title);
			$("input[name=chkBoxSelectedIds]").click(function() {
				if ($("input[name=chkBoxSelectedIds]:unchecked").length > 0) {
				   $(".allReligions").parent('span').removeClass("checked");
					$(".allReligions").attr("checked", false);
				} else {
				    $(".allReligions").parent('span').addClass("checked");
					$(".allReligions").attr("checked", true);
				}
			});
			$("input[name=chkBoxSelectedIds]").click(function() {
				if ($("input[name=chkBoxSelectedIds]:unchecked").length > 0) {
				   $(".allClasses").parent('span').removeClass("checked");
					$(".allClasses").attr("checked", false);
				} else {
				    $(".allClasses").parent('span').addClass("checked");
					$(".allClasses").attr("checked", true);
				}
			});
			$("input[name=communityCheckBoxes]").click(function() {
				if ($("input[name=communityCheckBoxes]:unchecked").length > 0) {
				   $(".allCommunities").parent('span').removeClass("checked");
					$(".allCommunities").attr("checked", false);
				} else {
				    $(".allCommunities").parent('span').addClass("checked");
					$(".allCommunities").attr("checked", true);
				}
			});
			/*$("input[name=chkBoxExamTypeIds]").click(function() {
				if ($("input[name=chkBoxExamTypeIds]:unchecked").length > 0) {
				   $(".allExamTypes").parent('span').removeClass("checked");
					$(".allExamTypes").attr("checked", false);
				} else {
				    $(".allExamTypes").parent('span').addClass("checked");
					$(".allExamTypes").attr("checked", true);
				}
			});*/
		});
	$('html, body').animate({ scrollTop: $(document).height() - $(window).height() }, 10, function() {
	    $(this).animate({ scrollTop: 0 }, 10);
	});
	function reportFormate() {
		$('.anyId').val('Excel');
	}
	function reportType() {
		$('.anyId').val('PDF');
	}
	function generateStudentReligionIds() {
		if ($("input[name=chkBoxSelectedIds]:checked").length > 0) {
			var ReligionIds = $("input[name=chkBoxSelectedIds]:checked");
			var selectedRligionIds = '';
			if (ReligionIds.length > 0) {
				selectedRligionIds = '(';
				for ( var i = 0; i < ReligionIds.length; i++) {
					if (i == (ReligionIds.length - 1))
						selectedRligionIds += ReligionIds[i].value;
					else {
						selectedRligionIds += ReligionIds[i].value + ', ';
					}
				}
				selectedRligionIds += ')';
			}
			$("#religionNameIds").val(selectedRligionIds);
			var value = $("input[name='selectedName']").val();
			if (isNonEmpty(value)) {
				$("#roleName").val(value);
			}
			alert("Selected religions shown under their individual columns in the report, All other (non selected) religions shown under OTHERS column.");
			return true;
		} else if ($("input[name=chkBoxSelectedIds]:checked").length == 0) {
			alert("Please select at least one religion");
			return false;
		} else {
			return false;
		}
	}
	
	function checkallReligions() {
		if ($(".allReligions").is(':checked')){
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
	
	function checkAllCommunities() {
		if ($(".allCommunities").is(':checked')){
		    $("[name='communityCheckBoxes']").each(function(){
			   $(this).parent('span').addClass('checked');
			    $(this).attr("checked", "true");
			});
		}
		else{
		 $("[name='communityCheckBoxes']").each(function(){
			   $(this).parent('span').removeClass('checked');
			    $(this).removeAttr("checked");
			});
		}	
	}
	function generateClassCommunityIds() {
		if ($("input[name=communityCheckBoxes]:checked").length > 0) {
			var communityIds = $("input[name=communityCheckBoxes]:checked");
			var selectedCommunityIds = '';
			if (communityIds.length > 0) {
				selectedCommunityIds = '(';
				for ( var i = 0; i < communityIds.length; i++) {
					selectedCommunityIds += communityIds[i].value + ', ';
				}
				selectedCommunityIds += '0)';
			}
			$("#communityIds").val(selectedCommunityIds);
			var value = $("input[name='selectedName']").val();
			if (isNonEmpty(value)) {
				$("#roleName").val(value);
			} 
			return true;
		} else if ($("input[name=communityCheckBoxes]:checked").length == 0) {
			alert("Please select at least one community");
			return false;
		} else {
			return false;
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
	function generateClassIds() {
		if ($("input[name=chkBoxSelectedIds]:checked").length > 0) {
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
			var value = $("input[name='selectedName']").val();
			if (isNonEmpty(value)) {
				$("#roleName").val(value);
			} 
			return true;
		} else if ($("input[name=chkBoxSelectedIds]:checked").length == 0) {
			alert("Please select at least one Class");
			return false;
		} else {
			return false;
		}
	}
</script>