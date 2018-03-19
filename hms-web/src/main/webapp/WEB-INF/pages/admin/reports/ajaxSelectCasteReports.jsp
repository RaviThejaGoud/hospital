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
						<s:if
							test="%{(castSettingList != null && !castSettingList.isEmpty()) && (classList != null && !classList.isEmpty())}">
							<s:form action="ajaxPrintStudentSchoolWideCasteDetails"
								cssClass="form-horizontal" theme="simple"
								onsubmit="return generateClassCommunityIds();"
								id="classAndCommunity" method="post" namespace="/reports">
								<s:hidden id="classNameIds" name="anyId" />
								<s:hidden id="genderTypes" name="tempString" />
								<div class="form-body">
								<s:if test='%{tempString == "Student"}'>
									<%@ include
										file="/WEB-INF/pages/common/ajaxClassNamesChkBoxList.jsp"%>
								</s:if>
								
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
								Classes and communities are not available.
							</div>
						</s:else>
					</div>
				</div>
			</div>
		</div>
	</div>
<div id="schoolTermlist"></div>
<script type="text/javascript">
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
	if ((($("input[name=chkBoxSelectedIds]:checked").length > 0)
			&&  ($("input[name=genderName]:checked").length > 0))) {
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
	} else if ((($("input[name=chkBoxSelectedIds]:checked").length == 0)
			&& ($("input[name=communityCheckBoxes]:checked").length > 0) && ($("input[name=genderName]:checked").length > 0))) {
		alert("Please select at least one Class");
		return false;
	}  else if ((($("input[name=chkBoxSelectedIds]:checked").length > 0) && ($("input[name=genderName]:checked").length == 0))) {
		alert("Please select gender.");
		return false;
	}   else if ((($("input[name=chkBoxSelectedIds]:checked").length == 0) && ($("input[name=genderName]:checked").length == 0))) {
		alert("Please select at least one class and gender");
		return false;
	} else if ((($("input[name=chkBoxSelectedIds]:checked").length == 0) && ($("input[name=genderName]:checked").length == 0))) {
		alert("Please select at least one class and gender");
		return false;
	} else {
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