<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>
					<span class="hidden-title"> Student And Staff Login Ids
						Reports</span>
				</div>
			</div>
			<div class="portlet-body">
					<div class="tab-content">
						<s:form name="myformIdCards" theme="simple" namespace="/reports"
							id="idCardGenForm" cssClass="form-horizontal"
							action="ajaxStudentAndStaffLoginIdsReports"
							onsubmit="javascript: return getClassIdsAndRoleId();">
							<input type="hidden" name="pdfId" value="pdf" />
							<input type="hidden" name="selectedId" id="classNameIds" />
							<input type="hidden" name="anyId" id="selectedRoleNames" />
							<div class="form-body">
								<div class="form-group" id="classWiseStuDetailsPdfDetails">
									<label class="conLable col-md-1 control-label">
										<strong>Select : </strong>
									</label>
									<div class="radio-list">
										<label class="radio-inline">
											<input type="radio" name="SelectType"
												value="classSectionWise"
												onclick="getSelectedValue(this.value);" checked="checked">
											Student Login Ids Class Wise
										</label>
										<label class="radio-inline">
											<input type="radio" name="SelectType" value="roleWise"
												onclick="getSelectedValue(this.value);">
											Staff login Ids Role Wise
										</label>
									</div>
								</div>
								<div id="classAndSection">
									<s:if
										test="%{studyClassList != null && !studyClassList.isEmpty()}">
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
									</s:if>
									<s:else>
										<div class="alert alert-info">Currently there are no class and sections.
										</div>
									</s:else>
								</div>
								<div class="form-group" id="staffRoles">
								<s:if  test="%{objectList != null && !objectList.isEmpty()}">
									<label class="conLable col-md-3 control-label">
										<span class="required">*</span> Available Roles :
									</label>
									<div class="col-md-12">
										<div class="checkbox-list">
											<label class="checkbox-inline">
												<input type="checkbox" name=""
														value="" onClick="allRoleNames()"
														class="checkbox userRoleNames">
												All Role Names
											</label>
										</div>
										<s:checkboxlist name="roleNameChkBoxSelectedIds"
											list="objectList" listKey="roleId"
											listValue="roleDescription" theme="ems" cssClass="small" />
									</div>
									</s:if>
									<s:else>
										<div class="alert alert-info">Currently there are no roles.
										</div>
									</s:else>
								</div>
								<div class="form-actions fluid">
									<div class="col-md-6">
										<div class="col-md-offset-3 col-md-9">
											<s:submit type="submit small" value="Generate Pdf"
												onclick="reportType()" cssClass="submitBt btn blue long"
												title="generate report">
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
<script type="text/javascript">
	$(document).ready(function() {
	 $("input:checkbox, input:radio").uniform();
		var admissionNumber = "";
	 	changePageTitle("Student And Staff Login Ids");	
		var selected = $('input[name=SelectType]:radio:checked').val();	
		getSelectedValue(selected);	
		$('span.hidden-title').html($('.page-sidebar-menu li.active').find('li.active').children('a').children('span.title').text().trim() + "-->"
							+ $('.page-sidebar-menu li.active').find('li.active').find('ul.sub-menu').find('li.active').children('a').text().trim())
			var title = $('.page-sidebar-menu li.active').find('li.active').find('ul.sub-menu').find('li.active').children('a').text().trim();	 
	});
	function getSelectedValue(value) {			    
		if (isNonEmpty(value)) {				
			 if (value == 'classSectionWise' ) {
			checkAllClasses();
			 	$(".userRoleNames").removeAttr("checked");  
		   		$("input[name='roleNameChkBoxSelectedIds']").removeAttr("checked");   		
				$('#classAndSection').show();
				$("#staffRoles").hide();
				$("#selectedRoleNames").hide();			
			} else {
			allRoleNames();			
				$("input[name='chkBoxSelectedIds']").removeAttr("checked");
			 	$(".allClasses").removeAttr("checked");
				$('#classAndSection').hide();
				$("#staffRoles").show();
				$("#classNameIds").val(null);
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
		 $(".allClasses").parent('span').removeClass('checked');
		 $(".allClasses").removeAttr("checked");
		 $("[name='chkBoxSelectedIds']").each(function(){
			   $(this).parent('span').removeClass('checked');
			    $(this).removeAttr("checked");
			});
		}	
	}	
	$("input[name=chkBoxSelectedIds]").click(function() {
		if ($("input[name=chkBoxSelectedIds]:unchecked").length > 0) {
		   $(".allClasses").parent('span').removeClass("checked");
			$(".allClasses").attr("checked", false);
		} else {
		    $(".allClasses").parent('span').addClass("checked");
			$(".allClasses").attr("checked", true);
		}
	});
	function allRoleNames() {
		if ($(".userRoleNames").is(':checked')){
		    $("[name='roleNameChkBoxSelectedIds']").each(function(){
			   $(this).parent('span').addClass('checked');
			    $(this).attr("checked", "true");
			});
		}
		else{
		$(".userRoleNames").parent('span').removeClass('checked');
			  $(".userRoleNames").removeAttr("checked");
		 $("[name='roleNameChkBoxSelectedIds']").each(function(){
			   $(this).parent('span').removeClass('checked');
			    $(this).removeAttr("checked");
			});
		}	
	}	
	$("input[name=roleNameChkBoxSelectedIds]").click(function() {
		if ($("input[name=roleNameChkBoxSelectedIds]:unchecked").length > 0) {
		   $(".userRoleNames").parent('span').removeClass("checked");
			$(".userRoleNames").attr("checked", false);
		} else {
		    $(".userRoleNames").parent('span').addClass("checked");
			$(".userRoleNames").attr("checked", true);
		}
	});
	function getClassIdsAndRoleId() {	  
	   var selected = $('input[name=SelectType]:radio:checked').val();
	  // alert('selected:'+selected)
	   if (selected == 'classSectionWise') {	 
			if ($("input[name=chkBoxSelectedIds]:checked").length > 0) {
				var classIds = $("input[name=chkBoxSelectedIds]:checked");		
				var selectedClassNames = [];
				var selectedClassIds = [];
				if (classIds.length > 0) {
					$(classIds).each(function(){
						if (isNonEmpty($(this).val())
								&& isNonEmpty($(this).parents('label').text())) {
							selectedClassIds.push($(this).val());
							selectedClassNames.push($(this).parents('label').text().trim())
						}
					})
				}
				$("#classNameIds").val("(" + selectedClassIds + ")");
				$("#classNames").val(selectedClassNames);
				return true;
			} else {			
				alert("Please select at least one class.");
				return false;
			}
		}
		 else {	 
		 	if ($("input[name=roleNameChkBoxSelectedIds]:checked").length >0) {
		 		var roleNameIds = $("input[name=roleNameChkBoxSelectedIds]:checked");
				var selectedRoleIds = [];
				if (roleNameIds.length > 0) {
				selectedRoleIds.push('(0');
					for ( var i = 0; i < roleNameIds.length; i++) {
						if (isNonEmpty(roleNameIds[i].value))
							selectedRoleIds.push(roleNameIds[i].value.trim());
					}
					selectedRoleIds.push('0)');
				}
				$("#selectedRoleNames").val(selectedRoleIds);
				return true;
			}		
			 else {
				 alert("Please select at least one role.");
				 return false;
			}	 
		}
	}	
</script>
