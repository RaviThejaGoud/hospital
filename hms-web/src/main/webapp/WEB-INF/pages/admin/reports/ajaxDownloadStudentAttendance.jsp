<%@ include file="/common/taglibs.jsp"%>
<div class="block_head">
	<div class="grid_12 ">
		<h2 id="AdminReports">
		</h2>
	</div>
</div>
<div class="block_content" id="studImportAttCont">
	<div id="commonStep13">
	<s:url id="doImportStudentAttendanceTemplate"
					action="ajaxDoImportStudentAttendanceTemplate"
					namespace="/reports">
				</s:url>
				<sj:a id="importStudAttTemplate" cssClass="labelRight"
					href="%{doImportStudentAttendanceTemplate}" targets="studImportAttCont"
					indicator="indicator">
					Import Student Attendance
	</sj:a>
	<s:if test="%{studyClassList != null && !studyClassList.isEmpty()}">
		<s:form action="ajaxDownloadStudentsAttendanceTemplate"
			theme="css_xhtml" onsubmit="return generateClassIds();"
			id="downloadStudAtteTemplate" method="post" namespace="/reports">
			<s:hidden id="classNameIds" name="selectedId" />
			<s:hidden id="classNames" name="tempString" />
			<div class="grid_10 checkbox">
						<input type="checkbox" name="" value=""
							onClick="checkAllClasses()" class="checkbox allClasses">
						All Class&Sections
						<h4>
							Class With Students:
						</h4>
						<s:checkboxlist name="chkBoxSelectedIds" list="studyClassList"
							listKey="id" listValue="classAndSection" theme="ems"
							cssClass="small" />
						<s:if test='%{tempList2.size >0}'>
							<h3>
								Class With Out Students:
							</h3>
							<s:checkboxlist name="chkBoxNotStudents" list="tempList2"
								listKey="id" listValue="classAndSection" theme="ems"
								cssClass="small" disabled="true"/>
						</s:if>
			
				<!--<h1>
					<span class="required">*</span> Available Classes:
				</h1>
				<input type="checkbox" name="" value="" onClick="checkAllClasses()"
					class="checkbox allClasses">
				All Classes
				<s:checkboxlist name="chkBoxSelectedIds" list="%{classList}" listKey="id" listValue="classAndSection" 
					cssClass="small" theme="ems" />
			--></div>
			<div class="grid_10" style="margin-right: 26px;">
				<s:submit type="submit small" value="Download Template"
					onclick="reportType()" cssClass="long submit"
					title="generate report" cssStyle="cursor:pointer">
				</s:submit>
			</div>
		</s:form>
		</s:if>
		<s:else>
			Classes are not available.
		</s:else>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function() {
changePageTitle("Manage Student Attendance"); 											  									
$('h2#AdminReports').text(
					$('div#sideMenu li.active a').text() + "-->"
							+ $('div#sideSubMenu li.active a').text())
	$("input[name=chkBoxSelectedIds]").click(function() {
		if ($("input[name=chkBoxSelectedIds]:unchecked").length > 0) {
			$(".allClasses").attr("checked", false);
		} else {
			$(".allClasses").attr("checked", true);
		}
	});
});
function checkAllClasses() {
	if ($(".allClasses").is(':checked'))
		$("input[name='chkBoxSelectedIds']").attr("checked", "true");
	else
		$("input[name='chkBoxSelectedIds']").removeAttr("checked");
}
function generateClassIds() {
	if ($("input[name=chkBoxSelectedIds]:checked").length > 0) {
		var classIds = $("input[name=chkBoxSelectedIds]:checked");
		var selectedClassIds = '';
		var selectedClassNames = '';
		if (classIds.length > 0) {
			selectedClassIds = '(';
			$(classIds).each(function(){
				selectedClassIds += $(this).val() + ', ';
				if(isNonEmpty($(this).parents('label').text()))
				    selectedClassNames +=  $(this).parents('label').text().replace(/^\s+|\s+$/g, ' '); 
			});
			selectedClassIds += '0)';
		}
		$("#classNameIds").val(selectedClassIds);
		$("#classNames").val(selectedClassNames);
		return true;
	} else if ($("input[name=chkBoxSelectedIds]:checked").length == 0) {
		alert("Please select at least one Class");
		return false;
	} else {
		return false;
	}
}
</script>