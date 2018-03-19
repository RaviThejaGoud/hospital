<%@ include file="/common/taglibs.jsp"%>
<s:form action="ajaxImportStudentsRegisterNumber"
	id="importStudRegSheet" cssClass="form-horizontal" method="post"
	theme="simple" enctype="multipart/form-data" namespace="/reports">
	<s:hidden name="tempString" value="Student"></s:hidden>
	<s:hidden name="plTitle" value="ViewStudentClassSectionDetails"></s:hidden>
	<div class="form-body">
		<div class="form-group">
			<label class="control-label col-md-3">
				<span class="required">*</span>Import Student Register Sheet :
			</label>
			<div class="col-md-4">
				<input type="file" class="btn default required" id="photoURL"
					value="" name="upload">
			</div>
		</div>
		<div class="form-actions fluid">
			<div class="col-md-6">
				<div class="col-md-offset-4 col-md-12">
					<sj:submit  targets="mainContentDiv" value="Submit" validate="true"
						indicator="indicator" cssClass="submitBt btn blue small"
						onBeforeTopics="importStudRegSheetValidation" />
					<%-- <s:url id="viewStudentCommunityClass"
						action="ajaxDoSelectStudyClasses" namespace="/reports"
						includeParams="all" escapeAmp="false">
						<s:param name="tempString">Student</s:param>
						<s:param name="plTitle">ViewStudentClassSectionDetails</s:param>
					</s:url>
					<sj:a id="cancelImportTemplate" cssClass="btn default"
						href="%{viewStudentCommunityClass}" targets="mainContentDiv"
						indicator="indicator">Cancel
					</sj:a> --%>
				</div>
			</div>
		</div>
	</div>
</s:form>


