<%@ include file="/common/taglibs.jsp"%>

<span id="startDateSpan" class="<s:property value='startDate'/>"></span>
<span id="endDateSpan" class="<s:property value='endDate'/>"></span>
<span id="toDateSpan" class="<s:property value='toDate'/>"></span>

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
						<s:if
							test="%{studyClassList != null && !studyClassList.isEmpty()}">
							<s:form action="ajaxGeneratedTCReport"
								cssClass="form-horizontal" theme="simple"
								onsubmit="return generateClassCommunityIds();"
								id="classAndCommunity" method="post" namespace="/reports">
								<s:hidden id="classNameIds" name="anyId" />
								<s:hidden id="genderTypes" name="tempString" />
								
								<div class="row" id="dateSelectionDivId">
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-3">
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
												<label class="control-label col-md-3">
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
														onClick="checkAllClasses()" class="checkbox allClasses">
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
												<s:checkboxlist name="chkBoxSelectedIds" list="studyClassList"
													listKey="id" listValue="classAndSection" theme="ems"
													cssClass="small" />
											</div>
										</div>
									</div>
					
								
								
								<div class="form-actions fluid">
									<div class="col-md-6">
										<div class="col-md-offset-3 col-md-9">
											<s:submit type="submit small" value="Generate Excel"
												cssClass="submitBt btn blue long" title="generate report"
												onclick="reportFormate()">
											</s:submit>
										</div>
									</div>
								</div>
								</div>
							</s:form>
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

var startDate = $('span#startDateSpan').attr("class");
var endDate = $('span#endDateSpan').attr("class");
dateRestrictionWithinAcademicYear(startDate,endDate);
FormComponents.init();

$(document).ready(
		function() {
		$("input:checkbox, input:radio").uniform();
		$('span.hidden-title').html($('.page-sidebar-menu li.active').find('li.active').children('a').children('span.title').text().trim() + "-->"
							+ $('.page-sidebar-menu li.active').find('li.active').find('ul.sub-menu').find('li.active').children('a').text().trim())
			var title = $('.page-sidebar-menu li.active').find('li.active').find('ul.sub-menu').find('li.active').children('a').text().trim();
							
			changePageTitle("Generated TC Report");
		});
function generateClassCommunityIds() {
	if ($("input[name=chkBoxSelectedIds]:checked").length > 0  && $('#startDate').val() !='' && $('#endDate').val() !='') {
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
		
		return true;
	}  else if ($("input[name=chkBoxSelectedIds]:checked").length == 0) {
		alert("Please select at least one class");
		return false;
	} 
	else if ($('#startDate').val() =='') {
		alert("Please select start date.");
		return false;
	}
	else if ($('#endDate').val() =='') {
		alert("Please select end date.");
		return false;
	}
	
	else {
		return false;
	}
}
function feeDatesValidation(){
	var startDate = $('#startDate').val();
	var endDate = $('#endDate').val();
	if (isNonEmpty(startDate) || isNonEmpty(endDate)) {
		startDate = Date.parse(startDate);
		endDate = Date.parse(endDate);
		if (endDate < startDate) {
			alert("Your end date is more than your start date.");
			$('#endDate').val("");
		}
	}
}


function checkAllClasses() {
	if ($(".allClasses").is(':checked')) {
		$("[name='chkBoxSelectedIds']").each(function() {
			$(this).parent('span').addClass('checked');
			$(this).attr("checked", "true");
		});
	} else {
			$(".allClasses").parent('span').removeClass('checked');
			$(".allClasses").removeAttr("checked");
		$("[name='chkBoxSelectedIds']").each(function() {
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


</script>