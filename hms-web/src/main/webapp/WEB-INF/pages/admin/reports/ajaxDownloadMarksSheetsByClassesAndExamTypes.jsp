<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>
					<span class="hidden-title"> </span>
				</div>
			</div>
			<div class="portlet-body">
				<div class="tab-content">
					<s:if test="%{classList != null && !classList.isEmpty()}">
						<s:if test="%{examTypeList != null && !examTypeList.isEmpty()}">
							<s:form action="ajaxDownloadStudentsPromotionList"
								namespace="/reports" theme="simple" cssClass="form-horizontal"
								onsubmit="return generateClassExamTypeIds();"
								id="classAndCommunity" method="post">
								<s:hidden id="classNameIds" name="anyId" />
								<s:hidden id="examTypeIds" name="anyTitle" />
									<div class="form-body">
										<div class="form-group">
											<div class="col-md-12">
												<div class="checkbox-list">
													<label class="checkbox-inline">
														<input type="checkbox" name="" value=""
															onClick="checkAllClasses()" class="checkbox allClasses">
														All Classes
													</label>
												</div>
											</div>
										</div>
										<div class="form-group">
											<label class="conLable col-md-3 control-label">
												<span class="required">*</span> Classes With Students :
											</label>
											<div class="col-md-12">
												<div class="checkbox-list">
													<s:checkboxlist name="chkBoxSelectedIds"
														cssClass="checkbox small" list="classList" listKey="id"
														listValue="className" theme="ems" />
												</div>
											</div>
										</div>
										<s:if
										test="%{classNameList != null && !classNameList.isEmpty() && classNameList.size >0}">
										<div class="form-group">
											<label class="conLable col-md-3 control-label">
												Classes With Out Students :
											</label>
											<div class="col-md-12">
												<div class="checkbox-list">
													<s:checkboxlist name="chkBoxNotStudents"
														cssClass="checkbox small" list="classNameList"
														listKey="id" listValue="className" theme="ems"
														disabled="true" />
												</div>
											</div>
										</div>
										</s:if>
										<div class="form-group">
											<label class="conLable col-md-3 control-label">
												<span class="required">*</span> Available ExamTypes :
											</label>
											<div class="col-md-12">
												<div class="checkbox-list">
													<label class="checkbox-inline">
														<input type="checkbox" name="" value=""
															onClick="checkAllExamTypes()"
															class="checkbox allExamTypes">
														All Exam Types
													</label>
												</div>
												<s:checkboxlist name="examTypesChkBoxes" list="examTypeList"
													listKey="id" listValue="examType" theme="ems"
													cssClass="small" />
											</div>
										</div>
										<div class="form-group">
											<label class="conLable col-md-3 control-label">
												Rounding off student marks :
											</label>
											<div class="col-md-5">
												<input type="checkbox" name="tempString" class="checkbox" />
											</div>
										</div>
										<div class="spaceDiv"></div>
										<p>
											<span class="label label-danger"> NOTE : </span>&nbsp;&nbsp;&nbsp;If
											you don't want decimals please select above check box.
										</p>
										<div class="spaceDiv"></div>
										<div class="row">
											<div class="col-md-6">
												<label class="conLable col-md-3 control-label">
													Sort By :
												</label>
												<div class="col-md-5">
													<s:select id="sortOpt"
														list="#{'firstName,lastName':'Student Name Wise','registerNumber':'Register Number Wise'}"
														cssClass="form-control input-medium" headerKey=""
														headerValue="- Select -" name="queryString" />
												</div>
											</div>
											<div class="col-md-6">
												<label class="conLable col-md-4 control-label">
													Report Type :
												</label>
												<div class="col-md-5">
													<s:select id="sortOpt"
														list="#{'PromotionReport':'Students Promotion Report','scholastic':'Students Scholastic Report'}"
														cssClass="form-control input-medium" name="alertSendType" />
												</div>
											</div>
										</div>
										<div class="spaceDiv"></div>
										<div class="form-actions fluid">
										<div class="col-md-7">
											<div class="col-md-offset-4 col-md-12">
												<s:submit type="submit small" value="Generate Excel"
													cssClass="submitBt btn blue long" title="generate report"
													onclick="reportFormate()" />
											</div>
										</div>
									</div>
								</div>
							</s:form>
						</s:if>
						<s:else>
							<div class="alert alert-info">
								Exam types are not available.
							</div>
						</s:else>
					</s:if>
					<s:else>
						<div class="alert alert-info">
							Classes are not available.
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
				$('span.hidden-title').html($('.page-sidebar-menu li.active').find('li.active').children('a').children('span.title').text().trim() + "-->"
								+ $('.page-sidebar-menu li.active').find('li.active').find('ul.sub-menu').find('li.active').children('a').text().trim())
				var title = $('.page-sidebar-menu li.active').find('li.active').find('ul.sub-menu').find('li.active').children('a').text().trim();
				changePageTitle("Student Promotion Report");
				
				$("input[name=chkBoxSelectedIds]").click(function() {
					if ($("input[name=chkBoxSelectedIds]:unchecked").length > 0) {
					   $(".allClasses").parent('span').removeClass("checked");
						$(".allClasses").attr("checked", false);
					} else {
					    $(".allClasses").parent('span').addClass("checked");
						$(".allClasses").attr("checked", true);
					}
				});
				$("input[name=examTypesChkBoxes]").click(function() {
					if ($("input[name=examTypesChkBoxes]:unchecked").length > 0) {
					   $(".allExamTypes").parent('span').removeClass("checked");
						$(".allExamTypes").attr("checked", false);
					} else {
					    $(".allExamTypes").parent('span').addClass("checked");
						$(".allExamTypes").attr("checked", true);
					}
				});
	});
	function reportFormate() {
		$('.anyId').val('Excel');
	}
	function generateClassExamTypeIds() {
		if ((($("input[name=chkBoxSelectedIds]:checked").length > 0) && ($("input[name=examTypesChkBoxes]:checked").length > 0))) {
			var classIds = $("input[name=chkBoxSelectedIds]:checked");
			var selectedClassIds = '';
			if (classIds.length > 0) {
				selectedClassIds = '(';
				for ( var i = 0; i < classIds.length; i++) {
					selectedClassIds += classIds[i].value + ', ';
				}
				selectedClassIds += '0)';
			}
			//alert(selectedClassIds);
			$("#classNameIds").val(selectedClassIds);
			var examTypeIds = $("input[name=examTypesChkBoxes]:checked");
			var selectedExamTypeIds = '';
			if (examTypeIds.length > 0) {
				selectedExamTypeIds = '(';
				for ( var i = 0; i < examTypeIds.length; i++) {
					selectedExamTypeIds += examTypeIds[i].value + ', ';
				}
				selectedExamTypeIds += '0)';
			}
			//alert(selectedExamTypeIds);
			$("#examTypeIds").val(selectedExamTypeIds);
			//var genderTypes=$("input[name=genderName]:checked");
			/*var selectedGenderTypes='';
			if(genderTypes.length > 0 ){
				 selectedGenderTypes = '(';
				 for(var i=0; i< genderTypes.length;i++ )
				   {
						selectedGenderTypes += "'"+genderTypes[i].value + "', " ;
			        }
			        selectedGenderTypes += "' ')";
			}
			$("#genderTypes").val(selectedGenderTypes); */
			return true;
		} else if ($("input[name=chkBoxSelectedIds]:checked").length == 0) {
			alert("Please select at least one Class");
			return false;
		} else if ($("input[name=examTypesChkBoxes]:checked").length == 0) {
			alert("Please select at least one exam type");
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
	function checkAllExamTypes() {
		if ($(".allExamTypes").is(':checked')){
		    $("[name='examTypesChkBoxes']").each(function(){
			   $(this).parent('span').addClass('checked');
			    $(this).attr("checked", "true");
			});
		}
		else{
		 $("[name='examTypesChkBoxes']").each(function(){
			   $(this).parent('span').removeClass('checked');
			    $(this).removeAttr("checked");
			});
		}	
	}
	
	/*function checkAllGenders() {
	 if ($(".allGenders").is(':checked'))
	 $("input[name='genderName']").attr("checked", "true");
	 else
	 $("input[name='genderName']").removeAttr("checked");
	 }
	 function allornone() {
	 if ($("input[name='chkBoxSelectedIds']").is(':checked'))
	 $(".allClasses").attr("checked", "true");
	 else
	 $(".allClasses").removeAttr("checked");
	
	 }*/
</script>