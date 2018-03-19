<%@ include file="/common/taglibs.jsp"%>
<div id="studRegister">
	<%@ include file="/common/messages.jsp"%>
	<s:form action="ajaxTeachNonTeachStaffAttendReport" theme="simple"
		cssClass="form-horizontal" onsubmit="return generateStaffRoleIds();"
		id="teachingAndNonTeaching" method="post" namespace="/reports">
		<s:hidden id="teachingRoleNameIds" name="selectedId" />
		<s:hidden name="plTitle" />
		<s:hidden id="monthNameIds" name="monthNameIds" />
		<s:hidden value="%{startDate}" name="startDate" />
		<s:hidden value="bankName" name="bankName" />
		<s:hidden name="anyId" cssClass="anyId" value=""></s:hidden>
		<div class="form-body">
		<%-- <s:if test='%{plTitle != "StaffDailyAttendance(Daily)"}'> --%>
		<s:if test='%{bankName != "Monthly"}'>
		
		
			<div class="form-group">
				<label class="control-label col-md-2"  style="text-align: left;">
				<span class="required">*</span> Select Status : 
				</label>
				<div class="col-md-10">
					<div class="checkbox-list" >
						<div id="Attendance" style="text-align: left;">
							<label class="checkbox-inline col-md-2">
								<input type="checkbox" name="presentVal" id="chkBoxClassIdsforattendance" value="P" class="mbc checkByValue"/>Present
							 </label>
						</div>
						<div id="MobileEmail">
							<label class="checkbox-inline col-md-2">
								<input type="checkbox" name="absentVal" id="chkBoxClassIdsforattendance" value="A" class="mec checkByValue" />Absent
							</label>
						</div>
					</div>
				</div>
			</div>
		</s:if>
		<div class="form-group">
			<label class="conLable col-md-2 control-label">
				<span class="required">*</span> Teaching Roles :
			</label>
			<div class="col-md-12">
				<div class="checkbox-list">
					<label class="checkbox-inline">
						<input type="checkbox" name="" value=""
								onClick="checkAllTeachingRole()" class="checkbox TeachingRole">
						All Teaching Roles
					</label>
				</div>
				<s:checkboxlist name="teachingRole" list="teachingRoleMap"
					theme="ems" cssClass="small" />
			</div>
		</div>
		<div class="form-group">
			<label class="conLable col-md-3 control-label">
				<span class="required">*</span> Non Teaching Roles :
			</label>
			<div class="col-md-12">
				<div class="checkbox-list">
					<label class="checkbox-inline">
						<input type="checkbox" name="" value=""
								onClick="checkAllNonTeachingRole()" class="checkbox NonTeachin">
						All Non Teaching Roles
					</label>
				</div>
				<s:checkboxlist name="nonTeachingRole" list="nonTeachingRoleMap"
					theme="ems" cssClass="small" />
			</div>
		</div>
		<s:if test='%{plTitle == "StaffAttendanceMonthly(Monthly)"}'>
			<div class="form-group" id="monthslist">
				<label class="conLable col-md-2 control-label">
					<span class="required">*</span> All Months :
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
		</s:if>
		<div class="form-actions fluid">
			<div class="col-md-6">
				<div class="col-md-offset-3 col-md-9">
						<s:if test='%{bankName == "Monthly"}'>
							<s:submit type="submit small" value="Generate Excel"
							onclick="getExcelFormateType()" cssClass="submitBt btn blue long"
							title="generate Excel" />
					    </s:if>
					    <s:else>
					   	 <s:submit type="submit small" value="Generate Pdf"
							onclick="reportType()" cssClass="submitBt btn blue long"
							title="generate report" />
					    </s:else>
				</div>
			</div>
		</div></div>
	</s:form>
</div>
<span id="loginRoleName" style="display: none;"><s:property
		value="user.userRoleDescription" /> </span>
<script type="text/javascript">
	$(document).ready(function() {
		 $("input:checkbox, input:radio").uniform();
		 changePageTitle("Staff Monthly Attendance Details");
		$('span.hidden-title').html($('.page-sidebar-menu li.active').find('li.active').children('a').children('span.title').text().trim() + "-->"
			+ $('.page-sidebar-menu li.active').find('li.active').find('ul.sub-menu').find('li.active').children('a').text().trim())
		var title = $('.page-sidebar-menu li.active').find('li.active').find('ul.sub-menu').find('li.active').children('a').text().trim();
		
		$("input[name=nonTeachingRole]").click(function() {
				if ($("input[name=nonTeachingRole]:unchecked").length > 0) {
				   $(".NonTeachin").parent('span').removeClass("checked");
					$(".NonTeachin").attr("checked", false);
				} else {
				    $(".NonTeachin").parent('span').addClass("checked");
					$(".NonTeachin").attr("checked", true);
				}
			});
			$("input[name=teachingRole]").click(function() {
				if ($("input[name=teachingRole]:unchecked").length > 0) {
				   $(".TeachingRole").parent('span').removeClass("checked");
					$(".TeachingRole").attr("checked", false);
				} else {
				    $(".TeachingRole").parent('span').addClass("checked");
					$(".TeachingRole").attr("checked", true);
				}
			});
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
	function reportType() {
		$('.anyId').val('PDF');
	}
	function getExcelFormateType() {
		$('.anyId').val('Excel');
	}
	function checkAllTeachingRole() {
		if ($(".TeachingRole").is(':checked')){
		    $("[name='teachingRole']").each(function(){
			   $(this).parent('span').addClass('checked');
			    $(this).attr("checked", "true");
			});
		}
		else{
		 $("[name='teachingRole']").each(function(){
			   $(this).parent('span').removeClass('checked');
			    $(this).removeAttr("checked");
			});
		}	
	}
	function checkAllNonTeachingRole() {
		if ($(".NonTeachin").is(':checked')){
		    $("[name='nonTeachingRole']").each(function(){
			   $(this).parent('span').addClass('checked');
			    $(this).attr("checked", "true");
			});
		}
		else{
		 $("[name='nonTeachingRole']").each(function(){
			   $(this).parent('span').removeClass('checked');
			    $(this).removeAttr("checked");
			});
		}	
	}
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
	function generateStaffRoleIds() {
		
		if((($('.anyId').val() == 'PDF' && $("#chkBoxClassIdsforattendance:checked").length >  0) &&($("input[name=teachingRole]:checked").length > 0|| $("input[name=nonTeachingRole]:checked").length > 0))
			||(($('.anyId').val() == 'Excel')  &&($("input[name=teachingRole]:checked").length > 0	|| $("input[name=nonTeachingRole]:checked").length > 0))){
			
		//Teaching
			var teachingRoleIds = $("input[name=teachingRole]:checked");
			var selectedteachingRoleIds = '';
			selectedteachingRoleIds = '(';
			if (teachingRoleIds.length > 0) {
				for ( var i = 0; i < teachingRoleIds.length; i++) {
					selectedteachingRoleIds += "'"+teachingRoleIds[i].value +"'"+ ',';
				}
			}
			// Non - Teaching
			var nonTeachingRoleIds = $("input[name=nonTeachingRole]:checked");
			if (nonTeachingRoleIds.length > 0) {
				for ( var i = 0; i < nonTeachingRoleIds.length; i++) {
					selectedteachingRoleIds +="'"+ nonTeachingRoleIds[i].value+"'"+ ',';
				}
			}
			selectedteachingRoleIds += '0)';
			$("#teachingRoleNameIds").val(selectedteachingRoleIds);
			
			if ($("input[name=chkBoxMonthIds]:checked").length > 0) {
				var monthIds = $("input[name=chkBoxMonthIds]:checked");
				var selectedMonthIds = '';
				if (monthIds.length > 0) {
					selectedMonthIds = '(';
					for ( var i = 0; i < monthIds.length; i++) {
						selectedMonthIds +="'"+monthIds[i].value +"'"+ ',';
					}
					selectedMonthIds += '0)';
				}
				$("#monthNameIds").val(selectedMonthIds);
				return true;
			} else {
			if ($('div#monthslist').is(':visible')) {
			    alert("Please select atleast one Month");
				return false;
				}
			}
	
		}else if ($('.anyId').val() == 'PDF' &&  $("#chkBoxClassIdsforattendance:checked").length == 0) {
			alert("Please select at least one attendance status");
			return false;
		} else if ($("input[name=teachingRole]:checked").length == 0 && $("input[name=nonTeachingRole]:checked").length == 0) {
			if ($("input[name=teachingRole]:checked").length == 0) {
				alert("Please select atleast one Role");
			}
			return false;
		}
		
	}
</script>