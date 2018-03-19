<%@ include file="/common/taglibs.jsp"%>
<div>
	<s:form action="ajaxImportStudentsAttendance" id="importStudAttSheet"
		method="post" theme="css_xhtml" enctype="multipart/form-data" namespace="/reports">
		<s:file name="upload" id="photoURL" label="Import Student Attendance Sheet"
			required="true" requiredposition="first"
			cssClass="text small required">
		</s:file>
		<div class="grid_10">&nbsp;</div>
		<s:url id="doCancelImportAttendanceTemplate" action="ajaxDoDownloadStudAttendanceTemplate" namespace="/reports">
											</s:url>
		<sj:a id="cancelImportTemplate" cssClass="cancelButton"
			href="%{doCancelImportAttendanceTemplate}" targets="reportsList"
			indicator="indicator">Cancel
		</sj:a>
		<sj:submit   targets="studAttCont" value="Submit"
			indicator="indicator" cssClass="submit small"
			onClickTopics="importStudAttSheetValidation" />
	</s:form>
</div>
<script type="text/javascript">
	$(document).ready(function() {
		$.subscribe('importStudAttSheetValidation', function(event, data) {
			if ($('#importStudAttSheet').valid())
				return true;
			else
				return false;
		});
	});
</script>


