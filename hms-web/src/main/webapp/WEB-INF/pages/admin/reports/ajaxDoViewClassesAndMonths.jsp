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
						  <s:form action="ajaxStudentClassWiseMonthlyAttendance"
							theme="simple" namespace="/reports" cssClass="form-horizontal"
							onsubmit="return generateClassIds();" id="classAndTodate"
							method="post"> 
							<s:hidden name="tempString"></s:hidden>
							<s:hidden name="anyId" cssClass="anyId" value=""></s:hidden>
							<s:hidden name="plTitle"></s:hidden>
							<s:hidden name="bankName" value="%{bankName}" />
							<%-- <s:hidden id="classNameIds" name="SelectedId" /> --%>
							<s:hidden id="monthNameIds" name="monthNameIds" />
							<s:if test='%{studyClassList !=null && !studyClassList.isEmpty()}'>
							<div class="form-body">
								<div class="form-group">
										<label class="col-md-3 control-label"> Select Class :
										</label>
										<div class="col-md-9">
											<s:select list="studyClassList" id="className"
												cssClass="form-control input-medium" name="SelectedId"
												listKey="id" listValue="classAndSection" theme="simple">
											</s:select>
										</div>
									</div>
								<%-- <s:if test='%{studyClassList.size >1}'>
									<div class="form-group">
										<div class="col-md-12">
											<div class="checkbox-list">
												<label class="checkbox-inline">
													<input type="checkbox" name="" value=""
														onClick="checkAllClasses()" class="checkbox allClasses">
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
								</s:if> --%>
							<s:if test="%{monthNamesList != null && !monthNamesList.isEmpty()}">
								<div class="form-group">
									<label class="conLable col-md-3 control-label">
										<span class="required">*</span> Available Months : 
									</label>
									<div class="col-md-12">
										<div class="checkbox-list">
											<label class="checkbox-inline">
												<input type="checkbox" name="" value=""
													onClick="checkAllMonths()" class="checkbox allmonths">
												All Months
											</label>
										</div>
										<s:checkboxlist name="chkBoxMonthIds" list="monthNamesList"
											listValue="key" theme="ems" cssClass="small" />
									</div>
								</div>
								<div class="form-actions fluid">
									<div class="col-md-offset-3 col-md-9">
										<s:if test='%{plTitle != "Attendance Not Submitted Details"}'>
											<s:submit type="submit small" value="Generate Pdf"
											onclick="getFormateType()" cssClass="submitBt btn blue long"
											title="generate report" />
										</s:if>
										<s:if test='%{bankName == "Monthly"}'>
											<s:submit type="submit small" value="Generate Excel"
											onclick="getExcelFormateType()" cssClass="submitBt btn blue long"
											title="generate Excel" />
										</s:if>
									</div>
								</div>
								</s:if>
							<s:else>
								<div class="alert alert-info">
									Currently there are no months .
								</div>
							</s:else>
							</div>
							</s:if>
							<s:else>
								<div class="alert alert-info">
									Currently there are no classes assigned for you.
								</div>
							</s:else>
						</s:form>
					</div>
				</div>
			</div>
		</div>
</div>
<div id="schoolTermlist"></div>
<script type="text/javascript">
	$(document).ready(function() {
		FormComponents.init();
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
		 $("input[name=chkBoxMonthIds]").click(function() {
					if ($("input[name=chkBoxMonthIds]:unchecked").length > 0) {
					   $(".allmonths").parent('span').removeClass("checked");
						$(".allmonths").attr("checked", false);
					} else {
					    $(".allmonths").parent('span').addClass("checked");
						$(".allmonths").attr("checked", true);
					}
				}); 
	});
	function getFormateType() {
		$('.anyId').val('PDF');
	}
	function getExcelFormateType() {
		$('.anyId').val('Excel');
	}
/* 	function checkAllClasses() {
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
		} */
	function checkAllMonths() {
		if ($(".allmonths").is(':checked')){
		    $("[name='chkBoxMonthIds']").each(function(){
			   $(this).parent('span').addClass('checked');
			    $(this).attr("checked", "true");
			});
		}
		else{
		 $("[name='chkBoxMonthIds']").each(function(){
			   $(this).parent('span').removeClass('checked');
			    $(this).removeAttr("checked");
			});
		}	
	}
 	
function generateClassIds() {
	if( $("input[name=chkBoxMonthIds]:checked").length > 0) {
		var monthIds = $("input[name=chkBoxMonthIds]:checked");
		var selectedMonthIds = '';
		if (monthIds.length > 0) {
			selectedMonthIds = '';
			for ( var i = 0; i < monthIds.length; i++) {
				selectedMonthIds += monthIds[i].value + ',';
			}
			selectedMonthIds += '';
		}
		$("#monthNameIds").val(selectedMonthIds);
			return true;
	}else if ($("input[name=chkBoxMonthIds]:checked").length == 0) {
		alert("Please select at least one month");
		return false;
	}
	
	
	/* if ($("input[name=chkBoxSelectedIds]:checked").length > 0
			&& $("input[name=chkBoxMonthIds]:checked").length > 0) {
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
		var monthIds = $("input[name=chkBoxMonthIds]:checked");
		var selectedMonthIds = '';
		if (monthIds.length > 0) {
			selectedMonthIds = '';
			for ( var i = 0; i < monthIds.length; i++) {
				selectedMonthIds += monthIds[i].value + ',';
			}
			selectedMonthIds += '';
		}
		$("#monthNameIds").val(selectedMonthIds);
			return true;
	} else if ($("input[name=chkBoxSelectedIds]:checked").length == 0
			|| $("input[name=chkBoxMonthIds]:checked").length == 0) {
		if ($("input[name=chkBoxSelectedIds]:checked").length == 0) {
			alert("Please select at least one Class");
		}else {
			alert("Please select at least one month");
		}
		return false;
	} else {
		return false;
	} */
} 
</script>