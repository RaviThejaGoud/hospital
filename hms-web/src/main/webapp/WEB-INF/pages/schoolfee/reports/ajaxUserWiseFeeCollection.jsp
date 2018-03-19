<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div id="paidAndUnpaid">
			<div class="portlet-body">
				<div id="allFeeReports">
					<span id="startDateSpan" class="<s:property value='startDate'/>"></span>
					<span id="endDateSpan" class="<s:property value='endDate'/>"></span>
					<s:if test="%{(objectList != null && !objectList.isEmpty())}">
						<s:if test="%{(studyClassList != null && !studyClassList.isEmpty())}">
						<s:form action="ajaxUserWiseFeeCollection" theme="simple"
							id="teachingAndNonTeaching" method="post"
							cssClass="form-horizontal"
							onsubmit="javascript:return userCollectedFeeType();"
							namespace="/reports">
							<s:hidden id="classNameIds" name="tempString" />
							<s:hidden id="financeStaffUserIds" name="anyId" />
							<div class="form-body">
								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											<label class="control-label col-md-2">
												<span class="required">*</span> From Date :
											</label>
											<div class="col-md-5">
												<div 
													data-date-format="mm/dd/yyyy"
													class="input-group input-medium date date-picker">
													<input type="text" id="startDate" readonly="readonly"
														name="startDate" onchange="feeDatesValidation()"
														class="required form-control input-medium" />
													<span class="input-group-btn">
														<button type="button" class="btn default">
															<i class="fa fa-calendar"></i>
														</button> </span>
												</div>
												<span class="help-block">(MM/DD/YYYY)</span>
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											<label class="control-label col-md-2">
												<span class="required">*</span> To Date :
											</label>
											<div class="col-md-5">
												<div
													data-date-format="mm/dd/yyyy"
													class="input-group input-medium date date-picker">
													<input type="text" id="endDate" readonly="readonly"
														name="endDate" onchange="feeDatesValidation()"
														class="required form-control input-medium" />
													<span class="input-group-btn">
														<button type="button" class="btn default">
															<i class="fa fa-calendar"></i>
														</button> </span>
												</div>
												<span class="help-block">(MM/DD/YYYY)</span>
											</div>
										</div>
									</div>
								</div>
								
								<div class="form-body">
											<div class="form-group">
												<div class="col-md-12">
													<div class="checkbox-list">
														<label class="checkbox-inline">
															<input type="checkbox" name="" value=""
																onClick="checkAllFinanceStaff()" class="checkbox allFinanceStaff">
															Select All Finance Staff
														</label>
													</div>
												</div>
											</div>
										<div class="form-group">
											<label class="conLable col-md-3 control-label">
												<span class="required">*</span> Finance Staff :
											</label>
											<div class="col-md-12">
												<div class="checkbox-list">
													<s:checkboxlist name="chkFinanceStaff"
														list="objectList" listKey="accountId"
														listValue="fullName" theme="ems" cssClass="small" />
												</div>
											</div>
										</div>
										
									</div>
									
									<div class="form-body">
											<div class="form-group">
												<div class="col-md-12">
													<div class="checkbox-list">
														<label class="checkbox-inline">
															<input type="checkbox" name="" value=""
																onClick="checkAllClasses()" class="checkbox allClasses">
															Available Class & Sections
														</label>
													</div>
												</div>
											</div>
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
										<s:if test='%{tempList2.size >0}'>
											<div class="form-group">
												<label class="conLable control-label col-md-3">
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
									</div>
								<div class="form-actions fluid">
									<div class="col-md-offset-3 col-md-12">
										<s:submit type="submit small" value="Generate Excel"
											onclick="reportType()" cssClass="submitBt btn blue long"
											title="generate report" />
									</div>
								</div>
							</div>
						</s:form>
						</s:if>
						<s:else>
							<div class="alert alert-info">
								Currently there is no class and Sections.
							</div>
						</s:else>
					</s:if>
					<s:else>
						<div class="alert alert-info">
							Currently there is no finance staff.
						</div>
					</s:else>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/ems.js"></script>
<script type="text/javascript">
/* var startDate = $('span#startDateSpan').attr("class");
var endDate = $('span#endDateSpan').attr("class");
dateRestrictionWithinAcademicYear(startDate,endDate);  */
FormComponents.init();
$(document).ready(function() {
	changePageTitle("User wise Fee Details");
	$("input:checkbox, input:radio").uniform();
});
/* $('input.generateReport').click(function() {
	if ($(this).hasClass('PDF')) {
		$('.anyId').val('PDF');
	} else {
		$('.anyId').val('Excel');
	}
}); */
function userCollectedFeeType() {
	if ($("input[name=chkFinanceStaff]:checked").length == 0) {
		alert("Please select at least one finance staff");
		return false;
	} 	
	if ($("input[name=chkBoxSelectedIds]:checked").length == 0) {
		alert("Please select at least one class");
		return false;
	} 	
	else {
		generateReportsWithTermIds();
		return true;
	}
}
function checkAllClasses() {
	if ($(".allClasses").is(':checked')) {
		$("[name='chkBoxSelectedIds']").each(function() {
			$(this).parent('span').addClass('checked');
			$(this).attr("checked", "true");
		});
	} else {
		$("[name='chkBoxSelectedIds']").each(function() {
			$(this).parent('span').removeClass('checked');
			$(this).removeAttr("checked");
		});
	}
}

function checkAllFinanceStaff() {
	if ($(".allFinanceStaff").is(':checked')) {
		$("[name='chkFinanceStaff']").each(function() {
			$(this).parent('span').addClass('checked');
			$(this).attr("checked", "true");
		});
	} else {
		$("[name='chkFinanceStaff']").each(function() {
			$(this).parent('span').removeClass('checked');
			$(this).removeAttr("checked");
		});
	}
}



$("input[name=chkFinanceStaff]").click(function() {
	if ($("input[name=chkFinanceStaff]:unchecked").length > 0) {
		$(".allFinanceStaff").parent('span').removeClass("checked");
		$(".allFinanceStaff").attr("checked", false);
	} else {
		$(".allFinanceStaff").parent('span').addClass("checked");
		$(".allFinanceStaff").attr("checked", true);
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
function generateReportsWithTermIds() 
{
	if ($("input[name=chkBoxSelectedIds]:checked").length > 0  && $("input[name=chkFinanceStaff]:checked").length > 0 ) {
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
		
		
		var userIds = $("input[name=chkFinanceStaff]:checked");
		
		var selectedUserIds = '';
		if (userIds.length > 0) {
			selectedUserIds = '';
			for ( var i = 0; i < userIds.length; i++) {
				selectedUserIds += userIds[i].value + ', ';
			}
			//selectedUserIds += '0';
		}
		
		$("#financeStaffUserIds").val(selectedUserIds);
		
		return true;
	}
}
</script>