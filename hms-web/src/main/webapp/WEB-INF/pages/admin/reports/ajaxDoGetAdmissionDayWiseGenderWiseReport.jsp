<%@ include file="/common/taglibs.jsp"%>

<span id="startDateSpan" class="<s:property value='startDate'/>"></span>
<span id="endDateSpan" class="<s:property value='endDate'/>"></span>
<span id="toDateSpan" class="<s:property value='toDate'/>"></span>
								
								
<s:if
							test="%{admissionAcademicYears != null && !admissionAcademicYears.isEmpty()}">
								<s:hidden id="genderTypes" name="tempString" />
								<div class="form-body">
								
								
								<div class="row" id="schoolWiseHolidays">
									<div class="form-group">
										<label class="col-md-2 control-label">
											<span class="required">*</span>Select Admission Type :
										</label>
										<div class="col-md-9">
											<s:checkboxlist list="#{'NA':'New Admission','RA':'Re -Admission'}" name="chkBoxSelectedIds" cssClass="required" theme="ems"></s:checkboxlist>
										</div>
									</div>
								</div>
				
				<div class="row" id="schoolWiseHolidays">
									<div class="form-group">
										<label class="col-md-2 control-label">
											<span class="required">*</span>Select Report Type :
										</label>
										<div class="col-md-9">
											<s:radio list="#{'DR':'Day wise/Periodic Report','OR':'Overall Admitted Report'}" onclick="javascript:selectReportType(this.value)"
												name="tempString3" cssClass="required"
												id="reportType"/>
										</div>
									</div>
								</div>
				
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
								
					
					
								
								<div class="form-group">
									<label class="conLable col-md-3 control-label">
										<span class="required">*</span> Select Gender :
									</label>
									<div class="col-md-12">
										<div class="checkbox-list">
											<label class="checkbox-inline">
												<input type="checkbox" value=""
															onClick="checkAllGenders()" class="checkbox allGenders">
												All Gender
											</label>
										</div>
										<input type="checkbox" name="genderName" value="M">
										Male
										<input type="checkbox" name="genderName" value="F">
										Female
									</div>
								</div>
								
								<div class="form-actions fluid">
									<div class="col-md-6">
										<div class="col-md-offset-3 col-md-9">
											<s:submit type="submit small" value="Generate Pdf"
												cssClass="submitBt btn blue long" title="generate report"
												onclick="reportFormate()">
											</s:submit>
										</div>
									</div>
								</div>
								</div>
						</s:if>
						<s:else>
							<div class="alert alert-info">
								Classes and communities are not available.
							</div>
						</s:else>
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
							
			changePageTitle("Day wise report gender wise Details");
			
			$("input[name=genderName]").click(function() {
				if ($("input[name=genderName]:unchecked").length > 0) {
				   $(".allGenders").parent('span').removeClass("checked");
					$(".allGenders").attr("checked", false);
				} else {
				    $(".allGenders").parent('span').addClass("checked");
					$(".allGenders").attr("checked", true);
				}
			});
			
		});

function generateClassCommunityIds() {
	
	var reportType = $("input[name=tempString3]:checked").val();
	
	if($("input[name=genderName]:checked").length > 0 && $("input[name=chkBoxSelectedIds]:checked").length > 0 && $("input[name=tempString3]:checked").length >0) 
	{
		
		var genderTypes = $("input[name=genderName]:checked");
		var selectedGenderTypes = '';
		if (genderTypes.length > 0) {
			selectedGenderTypes = '';
			for ( var i = 0; i < genderTypes.length; i++) {
				selectedGenderTypes += "" + genderTypes[i].value + ", ";
			}
			//selectedGenderTypes += "' '";
		}
		$("#genderTypes").val(selectedGenderTypes);
		if("OR" == reportType)
		{
			return true;
		}
		
		if ($('#startDate').val() =='' ) {
			alert("Please select start date.");
			return false;
		}
		else if ($('#endDate').val() =='') {
			alert("Please select end date.");
			return false;
		}
		
		return true;
	}
	else if ($("input[name=tempString3]:checked").length == 0) {
		alert("Please select report type.");
		return false;
	}
	
	else if ($("input[name=genderName]:checked").length == 0) {
		alert("Please select gender.");
		return false;
	}
	else if ($("input[name=chkBoxSelectedIds]:checked").length == 0) {
		alert("Please Admission Type.");
		return false;
	}
	else {
		return false;
	}
}

function checkAllGenders() {
	if ($(".allGenders").is(':checked')){
	    $("[name='genderName']").each(function(){
		   $(this).parent('span').addClass('checked');
		    $(this).attr("checked", "true");
		});
	}
	else{
	 $("[name='genderName']").each(function(){
		   $(this).parent('span').removeClass('checked');
		    $(this).removeAttr("checked");
		});
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

function selectReportType(reportType) {
	
	if (reportType=='DR') {
		$("#dateSelectionDivId").show();
	}
	else {
		$("#dateSelectionDivId").hide();
	}
}
</script>