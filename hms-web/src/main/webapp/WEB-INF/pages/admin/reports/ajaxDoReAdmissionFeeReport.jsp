<%@ include file="/common/taglibs.jsp"%>

<s:form action="ajaxReAdmissionFeeReport" theme="simple"
	cssClass="form-horizontal"
	onsubmit="return generateClassCommunityIds();"
	id="notAdmittedStudentsReports" method="post" namespace="/reports">

	<s:hidden id="classNameIds" name="SelectedId" />
	<h4 class="pageTitle bold">Re Admission Fee Report</h4>
	<s:if
		test="%{admissionAcademicYears != null && !admissionAcademicYears.isEmpty()}">
		<s:hidden id="genderTypes" name="tempString" />
		<div class="form-body">

			<div class="row" id="schoolWiseHolidays">
				<div class="form-group">
					<label class="col-md-2 control-label"> <span
						class="required">*</span>Select Report Type :
					</label>
					<div class="col-md-9">
						<s:radio
							list="#{'PS':'Payment Summary','FS':'Fee Not Paid Students '}"
							onclick="javascript:selectReportType(this.value)"
							name="tempString3" cssClass="required" id="reportType" />
					</div>
				</div>
			</div>

			<div class="row" id="dateSelectionDivId" style="display: none;">
				<s:if test='%{studyClassList.size >0}'>
					<div class="form-group">
						<div class="col-md-12">
							<div class="checkbox-list">
								<label class="checkbox-inline"> <input type="checkbox"
									name="" value="" onClick="checkAllClasses()"
									class="checkbox allClasses"> All Class & Sections
								</label>
							</div>
						</div>
					</div>
				</s:if>
				<s:if test='%{studyClassList.size >0}'>
					<div class="form-group">
						<label class="conLable col-md-3 control-label"> <span
							class="required">*</span> Class With Students :
						</label>
						<div class="col-md-12">
							<div class="checkbox-list">
								<s:checkboxlist name="chkBoxSelectedIds" list="studyClassList"
									listKey="id" listValue="classAndSection" theme="ems"
									cssClass="small" />
							</div>
						</div>
					</div>
				</s:if>

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
		<div class="alert alert-info">Classes and communities are not
			available.</div>
	</s:else>
</s:form>
<div id="schoolTermlist"></div>
<script type="text/javascript">
FormComponents.init();

$(document).ready(
		function() {
		$("input:checkbox, input:radio").uniform();
		$('span.hidden-title').html($('.page-sidebar-menu li.active').find('li.active').children('a').children('span.title').text().trim() + "-->"
							+ $('.page-sidebar-menu li.active').find('li.active').find('ul.sub-menu').find('li.active').children('a').text().trim())
			var title = $('.page-sidebar-menu li.active').find('li.active').find('ul.sub-menu').find('li.active').children('a').text().trim();
							
			changePageTitle("Re-Admission Details");
			
			
			
		});

function generateClassCommunityIds() {
	
	var reportType = $("input[name=tempString3]:checked").val();
	
	if("FS" == reportType)
	{
		if($("input[name=chkBoxSelectedIds]:checked").length <= 0) 
		{
			alert("Please select one class.");
			return false;
		}
		else{
			
			if ($("input[name=chkBoxSelectedIds]:checked").length > 0) {
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
			}
		}
	}
	else if ($("input[name=tempString3]:checked").length == 0) {
		alert("Please select report type.");
		return false;
	}
	else {
		return true;
	}
}


function selectReportType(reportType) {
	
	if (reportType=='FS') {
		$("#dateSelectionDivId").show();
	}
	else {
		$("#dateSelectionDivId").hide();
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
</script>