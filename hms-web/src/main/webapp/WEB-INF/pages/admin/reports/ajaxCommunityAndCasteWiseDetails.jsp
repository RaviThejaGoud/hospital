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
							test='%{(plTitle == "CommunityAndCasteDetails" && tempString == "Student")}'>
							<s:form action="ajaxselectedTypeReports" theme="simple"
								cssClass="form-horizontal" onsubmit="return generateClassIds();"
								id="classAndCommunity" method="post" namespace="/reports">
								<s:hidden name="tempString"></s:hidden>
								<s:hidden name="plTitle"></s:hidden>
								<s:hidden name="anyId" cssClass="anyId" value=""></s:hidden>
								<s:hidden id="classNameIds" name="SelectedId" />
								<s:hidden id="roleName" name="username" />
								<div class="form-body">
											<s:if test='%{studyClassList.size >0}'>
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
											<s:if test='%{studyClassList.size >0}'>
												<div class="form-group">
													<label class="conLable col-md-3 control-label">
														<span class="required">*</span> Classes With Students :
													</label>
													<div class="col-md-12">
														<div class="checkbox-list">
															<s:checkboxlist name="chkBoxSelectedIds" list="studyClassList"
																listKey="id" listValue="classAndSection" theme="ems"
																cssClass="small" />
														</div>
													</div>
												</div>
											</s:if>
											<%-- <s:if test='%{studyClassList.size >0}'>
												<div class="form-group">
													<label class="conLable col-md-3 control-label">
														<span class="required">*</span> Classes With Out Students :
													</label>
													<div class="col-md-12">
														<div class="checkbox-list">
															<s:checkboxlist name="chkBoxNotStudents"
																list="tempList2" listKey="id" listValue="classAndSection"
																theme="ems" cssClass="small" disabled="true" />
														</div>
													</div>
												</div>
											</s:if> --%>
											<s:if
												test='%{classNameList.size == 0 && classList.size == 0}'>
												<div>
													<font style="color: red">You have not created any
														Classes. You can able to create Class & Section.</font>
												</div>
											</s:if>
										<div class="form-actions fluid">
												<div class="col-md-offset-2 col-md-9">
													<s:if test='%{plTitle != "GenderwiseDetails"}'>
														<s:submit type="submit" value="Generate Excel"
															  cssClass="submitBt btn blue" cssStyle="float:left;"
															title="generate report" onclick="reportFormate()">
														</s:submit>
													</s:if>
													<%-- <div id="classWiseStuDetailsPdf">
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
						</s:if>
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
				$('#classWiseFineFeePdf').hide();
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