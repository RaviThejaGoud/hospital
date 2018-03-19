<%@ include file="/common/taglibs.jsp"%>
<div id="steps13">
	<jsp:include page="/common/messages.jsp"></jsp:include>
	<div class="form-body">
		<s:form action="ajaxImportDownloadHolidaysSheet" id="importExcelSheet"
			method="post" theme="simple" cssClass="form-horizontal"
			namespace="/admin" enctype="multipart/form-data" name="">
			<h4 class="pageTitle bold">
				Download template to import holidays
			</h4>
			<div class="form-group">
				<div class="panel-body col-md-12">
					<div class="col-md-1">
						<span class="label label-danger"> NOTE : </span>
					</div>
					<div class="col-md-10">
						<ul>
							<li>
								Please download the holidays import excel sheet by clicking the
								"Download Template" button below.
							</li>
							<li>
								This excel sheet has marked with set of column names that are
								supported by the system.
							</li>
							<li>
								<font color="red">Please do not add new columns or delete
									the marked columns</font>. If you want add more columns, please
								contact EazySchool support team(support@eazyschool.com).
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
		
			<div class="form-actions fluid">
				<div class="col-md-offset-2 col-md-9">
					<s:submit type="submit" value="Download Template"
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
		<div class="spaceDiv"></div>
		<div class="spaceDiv"></div>
		<div class="spaceDiv"></div>
		<s:form action="ajaxUploadHolidaysData" id="importHolidaysExcelSheet"
			method="post" theme="simple" cssClass="form-horizontal"
			enctype="multipart/form-data" name="" namespace="/admin">
			<s:hidden name="tempString" value="Import"></s:hidden>
			<h4 class="pageTitle bold">
				Add holidays
			</h4>
			<div class="panel-body col-md-12">
				<div class="col-md-1">
					<span class="label label-danger"> NOTE : </span>
				</div>
				<div class="col-md-10">
					<ul>
						<li>
							The import excel sheet must be downloaded from the above
							"Download Template" option.
						</li>
						<li>
							Please ensure that required columns are filled with necessary
							data.
						</li>
						<li>
							The system will not able to process the import successfully
							<font color="red">if any columns are changed or inserted</font>
							in the template.
						</li>
						<li>
							The system will generate the import status if any rows are not
							proceed due to data errors.
						</li>
					</ul>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-md-3">
					<span class="required">*</span>Select Import Template (Excel) :
				</label>
				<div class="col-md-4">
					<input type="file" class="btn default required" id="photoURL" onchange="validateExcelSheet(this);"
						value="" name="upload">
				</div>
			</div>
			<div class="form-actions fluid">
				<div class="col-md-offset-2 col-md-9">
					<sj:submit targets="stepHolidays"
						value="Upload Template"
						formIds="importHolidaysExcelSheet" cssClass="submitBt btn blue"
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
</div>
<script type="text/javascript">
changePageTitle('Import Holidays Template');
function reportFormate() {
	$('.anyId').val('Excel');
}
</script>
