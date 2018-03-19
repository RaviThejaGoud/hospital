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
			<div class="portlet-body">
					<div class="tab-content">
						<s:if
							test='%{(user.isOnlySchoolAdmin=="Y" && customer.hostelModuleStatus) || (user.isSchoolPrincipal=="Y" || user.isSchoolDirector == "Y" && customer.hostelModuleStatus=="Y")}'>
							<div class="form-group">
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
						</s:if>
						<s:if
							test="%{(tempList != null && !tempList.isEmpty()) && (studyClassList != null && !studyClassList.isEmpty())}">
							<s:form action="ajaxCommunityWiseMArks" theme="simple"
								cssClass="form-horizontal"
								onsubmit="return generateClassCommunityIds();"
								id="classAndCommunityMarks" method="post" namespace="/reports">
								<s:hidden id="classNameIds" name="selectedId" />
								<s:hidden name="plTitle" />
								<s:hidden id="communityIds" name="anyTitle" />
								<s:hidden id="roleName" name="username" />
								<div class="form-body">
									<s:if test='%{tempString == "Student"}'>
										<div class="form-group">
											<div class="col-md-12">
												<div class="checkbox-list">
													<label class="checkbox-inline">
														<input type="checkbox" name=""
																value="" onClick="checkAllClasses()"
																class="checkbox allClasses"> 
														All Class&Sections
													</label>
												</div>
											</div>
										</div>
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
									</s:if>
										<s:if test='%{plTitle == "ReligionWiseMarks"}'>
											<div class="form-group">
												<label class="conLable col-md-3 control-label">
													<span class="required">*</span> Available Religions :
												</label>
												<div class="col-md-12">
													<div class="checkbox-list">
														<label class="checkbox-inline">
																<input type="checkbox" name=""
																		value="" onClick="checkAllCommunities()"
																		class="checkbox allCommunities">
															All Religions
														</label>
													</div>
													<s:checkboxlist name="communityCheckBoxes" list="tempList"
														listKey="id" listValue="skillTypeName" theme="ems"
														cssClass="small" />
												</div>
											</div>
										</s:if>
										<s:else>
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
													<s:checkboxlist name="communityCheckBoxes" list="tempList"
														listKey="id" listValue="castName" theme="ems"
														cssClass="small" />
												</div>
											</div>
										</s:else>
									<div class="form-actions fluid">
										<div class="col-md-offset-3 col-md-9">
											<s:submit type="submit small" value="Generate Pdf"
												cssClass="submitBt btn blue" title="generate report" />
										</div>
									</div>
								</div>
							</s:form>
						</s:if>
						<s:else>
							<div class="alert alert-info">
						 		Classes and communities are not available.
						 	</div>	
						</s:else>
					</div>
				</div>
			</div>
		</div>
	</div>
<div id="schoolTermlist"></div>
<script type="text/javascript">
	$(document).ready(function() {
			$("input:checkbox, input:radio").uniform();
			var title = '';
		 $('span.hidden-481').html($('.page-sidebar-menu li.active').find('li.active').children('a').children('span.title').text().trim() + "-->"
				+ $('.page-sidebar-menu li.active').find('li.active').find('ul.sub-menu').find('li.active').children('a').text().trim())
				 title = $('.page-sidebar-menu li.active').find('li.active').find('ul.sub-menu').find('li.active').children('a').text().trim();

				 if(!isNonEmpty(title)){
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
			$("input[name=communityCheckBoxes]").click(function() {
				if ($("input[name=communityCheckBoxes]:unchecked").length > 0) {
				   $(".allCommunities").parent('span').removeClass("checked");
					$(".allCommunities").attr("checked", false);
				} else {
				    $(".allCommunities").parent('span').addClass("checked");
					$(".allCommunities").attr("checked", true);
				}
			});
		});
	function generateClassCommunityIds() {
		if (($("input[name=chkBoxSelectedIds]:checked").length > 0)
				&& ($("input[name=communityCheckBoxes]:checked").length > 0)) {
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
			var value = $("input[name='selectedName']:checked").val();
			if (isNonEmpty(value)) {
				$("#roleName").val(value);
			} else {
				$("#roleName").val('ROLE_STUDENT');
			}
			return true;
		} else if (($("input[name=chkBoxSelectedIds]:checked").length == 0)
				&& ($("input[name=communityCheckBoxes]:checked").length > 0)) {
			alert("Please select at least one Class");
			return false;
		} else if (($("input[name=chkBoxSelectedIds]:checked").length > 0)
				&& ($("input[name=communityCheckBoxes]:checked").length == 0)) {
			alert("Please select at least one community");
			return false;
		} else if (($("input[name=chkBoxSelectedIds]:checked").length == 0)
				&& ($("input[name=communityCheckBoxes]:checked").length == 0)) {
			alert("Please select at least one class and community");
			return false;
		} else {
			return false;
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
	function allornone() {
		if ($("input[name='chkBoxSelectedIds']").is(':checked'))
			$(".allClasses").attr("checked", "true");
		else
			$(".allClasses").removeAttr("checked");
	
	}
</script>