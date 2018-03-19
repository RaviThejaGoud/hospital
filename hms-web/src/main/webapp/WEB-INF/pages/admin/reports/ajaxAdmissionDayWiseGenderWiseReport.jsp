<%@ include file="/common/taglibs.jsp"%>

<span id="startDateSpan" class="<s:property value='startDate'/>"></span>
<span id="endDateSpan" class="<s:property value='endDate'/>"></span>
<span id="toDateSpan" class="<s:property value='toDate'/>"></span>
								
								
<s:if
							test="%{admissionAcademicYears != null && !admissionAcademicYears.isEmpty()}">
							<s:form action="ajaxAdmissionDayWiseGenderWiseReport"
								cssClass="form-horizontal" theme="simple"
								onsubmit="return generateClassCommunityIds();"
								id="classAndCommunity" method="post" namespace="/reports">
								<s:hidden id="genderTypes" name="tempString" />
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
							</s:form>
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
							
			changePageTitle("Class Wise Community Details");
			
			
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
	if ($("input[name=genderName]:checked").length > 0) 
	{
		var genderTypes = $("input[name=genderName]:checked");
		var selectedGenderTypes = '';
		if (genderTypes.length > 0) {
			selectedGenderTypes = '(';
			for ( var i = 0; i < genderTypes.length; i++) {
				selectedGenderTypes += "'" + genderTypes[i].value + "', ";
			}
			selectedGenderTypes += "' ')";
		}
		$("#genderTypes").val(selectedGenderTypes);
		return true;
	} 
	else if ($("input[name=genderName]:checked").length == 0) {
		alert("Please select gender.");
		return false;
	}  else {
		return false;
	}
}
function ajaxComunityReprots(yourOptionValue) {
	var pars = "yourOptionValue=" + yourOptionValue;
	if (yourOptionValue == "CSC") {
		var url = jQuery.url
				.getChatURL("/reports/ajaxDoClassWiseDefaulters.do");
		$.ajax( {
			url : url,
			cache : false,
			data : pars,
			success : function(html) {
				$("#schoolTermlist").html(html);
			}
		});
	} else if (yourOptionValue == "SSC") {
		document.myform2.submit();
		$("#schoolTermlist").html("");
	} else if (yourOptionValue == 'S') {
		alert("Please select at least one value.");
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
</script>