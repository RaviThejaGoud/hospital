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
						<s:form action="ajaxFailurePromoteStudentDetails" theme="simple"
								cssClass="form-horizontal" onsubmit="return generateClassIds();"
								id="failurePromoteStudentDetails" method="post" namespace="/reports">
							<s:hidden name="anyId" cssClass="anyId" value=""></s:hidden>
							<s:hidden id="classNameIds" name="SelectedId" />
							<s:hidden id="anyTitle" name="anyTitle" value="%{anyTitle}" />
							<div class="row">
							<div class="col-md-6">
								<div class="form-group">
									<label class="control-label col-md-4"> <span
										class="required">*</span>Select Academic Year :
									</label>
									<div class="col-md-5">
										<s:select id="examTypes" listKey="id" listValue="academicYear" list="academicYearList" required="true"
											tabindex="1" name="tempId" cssClass="required form-control" />
									</div>
								</div>
							</div>
							</div>
							<div class="form-body">
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
								<s:if test='%{tempList2.size >0}'>
									<div class="form-group">
										<label class="conLable col-md-3 control-label">
											<span class="required">*</span> Class With Out Students :
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
								<s:if
									test='%{tempList2.size == 0 && studyClassList.size == 0}'>
									<font style="color: red"> You have not created any
										Classes. You can able to create Class & Section.</font>
								</s:if>
								<div class="form-actions fluid">
									<div class="col-md-offset-2 col-md-9">
										<s:if test='%{anyTitle == "S" || anyTitle == "B"}'>
											<s:submit type="submit" value="Generate Excel"
												 cssClass="submitBt btn blue" cssStyle="float:left;"
												title="generate report" onclick="reportFormate()">
											</s:submit>
										</s:if>
										<div id="classWiseStuDetailsPdf">
											<s:submit type="submit" value="Generate Pdf" 
												onclick="reportType()" cssClass="submit btn blue"
												title="generate report" cssStyle="float:left;margin-left:10px;">
											</s:submit>
										</div>
									</div>
								</div>
							</div>
						</s:form>
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
			$('span.hidden-481').html($('.page-sidebar-menu li.active').find('li.active').children('a').children('span.title').text().trim() + "-->"
							+ $('.page-sidebar-menu li.active').find('li.active').find('ul.sub-menu').find('li.active').children('a').text().trim())
			var title = $('.page-sidebar-menu li.active').find('li.active').find('ul.sub-menu').find('li.active').children('a').text().trim();
			changePageTitle("Failure Promote Student Details");
			
			$("input[name=chkBoxSelectedIds]").click(function() {
				if ($("input[name=chkBoxSelectedIds]:unchecked").length > 0) {
				   $(".allClasses").parent('span').removeClass("checked");
					$(".allClasses").attr("checked", false);
				} else {
				    $(".allClasses").parent('span').addClass("checked");
					$(".allClasses").attr("checked", true);
				}
			});
		});
	function reportFormate() {
		$('.anyId').val('Excel');
	}
	function reportType() {
		$('.anyId').val('PDF');
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
				//selectedClassIds = '(';
				for ( var i = 0; i < classIds.length; i++) {
					selectedClassIds += classIds[i].value + ', ';
				}
				//selectedClassIds += '0)';
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