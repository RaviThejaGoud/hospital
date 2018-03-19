<!--<%@ include file="/common/taglibs.jsp"%>
<td colspan="3">
<div>
	<s:form action="ajaxImportStudentsToClass" id="importEntranceMarksSheet"
		method="post" theme="simple" enctype="multipart/form-data" namespace="/admin">
		<s:hidden name="anyId"/>
		<s:file name="upload" id="photoURL" label="Import an Excel Sheet"
			 requiredposition="first"
			cssClass="text small required">
		</s:file>
		<sj:submit   targets="academicsContent" value="Submit"
			indicator="indicator" cssClass="submit small" validate="true"
			onBeforeTopics="importEntranceMarksSheetValidation" />
	</s:form>
</div>
</td>
<script type="text/javascript">
	$(document).ready(function() {
		$.subscribe('importEntranceMarksSheetValidation', function(event, data) {
			if ($('#ajaxImportStudentsToClass').valid())
				return true;
			else
				event.originalEvent.options.submit=false;
		});
	});
	changePageTitle('Import Staff from Excel');
</script>


-->