<%@ include file="/common/taglibs.jsp"%>
<jsp:include page="/common/messages.jsp"></jsp:include>
<div class="form-body">
	<s:form action="ajaxImportDownloadHolidaysSheet" theme="simple"
		cssClass="form-horizontal" id="schoolHolidays" method="post" onsubmit="javascript: return getSchoolHolidayType();"
		namespace="/admin">
		<s:hidden name="anyTitle" value="update"></s:hidden>
		<h4 class="pageTitle bold">
			Download holidays 
		</h4>
		<div class="form-group">
			<div class="panel-body col-md-12">
				<div class="col-md-1">
					<span class="label label-danger"> NOTE : </span>
				</div>
				<div class="col-md-10">
					<ul>
						<li>
							Please download the holidays details by clicking the "Download
							Holidays" button below. The downloaded excel sheet contains all
							holidays details in the system.
						</li>
						<li>
							<font color="red">Please do not add new columns or delete
								the marked columns</font>. If you want add more columns, please contact
							EazySchool support team(support@eazyschool.com).
						</li>
					</ul>
				</div>
			</div>
		</div>
		<s:if test="%{(studyClassList != null && !studyClassList.isEmpty())}">
			<div class="form-group">
				<label class="control-label col-md-2">
					<span class="required">*</span>Select ClassName :
				</label>
				<div class="col-md-3">
					<s:select list="studyClassList" listKey="id"
						listValue="classAndSection" id="dropDownId"
						cssClass="form-control input-medium required"
						name="selectedId" headerKey="A" headerValue="All">
					</s:select>
				</div>
			</div>
		</s:if>
		
		<div class="form-group">
			<label class="col-md-2 control-label">
				<span class="required">*</span> Select Holiday Type :
			</label>
			<div class="col-md-9">
				<div class="checkbox-list">
					<div id="Attendance">
						<label class="checkbox-inline col-md-2">
							<input type="checkbox" name="govtHoliday" id="schoolHolidayType"
								value="H" class="mbc checkByValue">
							Govt Holidays
						</label>
					</div>
					<div id="MobileEmail">
						<label class="checkbox-inline col-md-2">
							<input type="checkbox" name="weekHoliday" id="schoolHolidayType"
								value="W" class="mec checkByValue">
							Week Holidays
						</label>
					</div>
				</div>
			</div>
		</div>
				
		<div class="form-actions fluid">
			<div class="col-md-offset-2 col-md-9">
				<s:submit type="submit" value="Download Holidays"
					cssClass="submitBt btn blue" title="generate report"
					onclick="reportFormate()" cssStyle="cursor:pointer">
				</s:submit>
				<s:url id="viewHolidays" action="ajaxViewSchoolSettingsHolidays"
					namespace="/admin">
				</s:url>
				<sj:a href="%{viewHolidays}" targets="mainContentDiv"
					cssClass="btn default">Cancel</sj:a>
			</div>
		</div>
	</s:form>

	<div class="spaceDiv">
		<p></p>
	</div>
	<s:if test='%{#session.previousYear == "N"}'>
		<div class="grid_12 commomnTabs">
			<s:form action="ajaxUploadHolidaysData" id="editHolidaysExcelSheet"
				cssClass="form-horizontal" method="post" theme="simple"
				enctype="multipart/form-data" namespace="/admin">
				<s:hidden name="tempString" value="Edit"></s:hidden>
				<h4 class="pageTitle bold">
					Upload holidays 
				</h4>
				<div class="panel-body col-md-12">
					<div class="col-md-1">
						<span class="label label-danger"> NOTE : </span>
					</div>
					<div class="col-md-10">
						<ul>
							<li>
								The upload excel sheet must be downloaded from the above
								"Download Holidays" option.
							</li>
							<li>
								The system will not able to process the upload successfully
								<font color="red">if any columns are changed or inserted</font>
								in the template.
							</li>
							<li>
								The system will generate the update status if any rows are not
								proceed due to data errors.
							</li>
						</ul>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-md-3">
						<span class="required">*</span>Select upload Template (Excel) :
					</label>
					<div class="col-md-4">
						<input type="file" class="btn default required" id="photoURL" onchange="validateExcelSheet(this);"
							value="" name="upload">
					</div>
				</div>
				<div class="form-actions fluid">
					<div class="col-md-offset-2 col-md-9">
						<sj:submit targets="stepHolidays" value="Upload Holidays"
							cssClass="submitBt btn blue" formIds="editHolidaysExcelSheet"
							validate="true" />
						<s:url id="viewHolidays" action="ajaxViewSchoolSettingsHolidays"
							namespace="/admin">
						</s:url>
						<sj:a href="%{viewHolidays}" targets="mainContentDiv"
							cssClass="btn default">Cancel</sj:a>
					</div>
				</div>
			</s:form>
		</div>
	</s:if>
</div>
<script type="text/javascript">
$(document).ready(function() {
	changePageTitle('Download HolidaysList Details');
	$("input:checkbox, input:radio").uniform();
	$("#searchStudentsList").hide();
});
function reportFormate() {
	$('.anyId').val('Excel');
}
function getSchoolHolidayType() {
	var selected = $('input.checkByValue:checked').length;
	if (selected > 0 ) {
		return true;
	} else {
		alert("Please select at least one Holiday Type.");
		return false;
	}
}


</script>