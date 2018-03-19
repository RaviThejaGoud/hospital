<%@ include file="/common/taglibs.jsp"%>
<div class="block_head">
	<div class="grid_12 ">
		<h2 id="AdminReports">
		</h2>
	</div>
</div>
<div class="block_content" id="studAttCont">
	<div id="commonStep13">
		<s:if test="%{classList != null && !classList.isEmpty()}">
			<!--<s:url id="doImportAttendanceTemplate" action="ajaxDoImportAttendanceTemplate" namespace="/reports">
											</s:url>
		<sj:a id="importTemplate" cssClass="labelRight"
			href="%{doImportAttendanceTemplate}" targets="studAttCont"
			indicator="indicator">
			Import Attendance
		</sj:a>
		-->
			<s:form action="ajaxMonthwiseStudentAttendanceTemplate"
				theme="css_xhtml" onsubmit="return generateClassIds();"
				id="classAndCommunity" method="post" namespace="/reports">
				<s:hidden id="classNameIds" name="SelectedId" />
				<div class="grid_10 ">
					<input type="checkbox" name="" value="" onClick="checkAllClasses()"
						class="checkbox allClasses">
					All Classes
					<h3>
						Class With Students:
					</h3>
					<s:checkboxlist name="chkBoxSelectedIds" list="classList"
						listKey="id" listValue="className" theme="ems" cssClass="small" />
					<s:if test='%{classNameList.size >0}'>
						<h3>
							Classes With Out Students:
						</h3>
						<s:checkboxlist name="chkBoxSelectedIds" list="classNameList"
							listKey="id" listValue="className" theme="ems" cssClass="small" />
					</s:if>
				</div>
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
$(document).ready(
		function() {
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
		if (classIds.length > 0) {
			selectedClassIds = '(';
			for ( var i = 0; i < classIds.length; i++) {
				selectedClassIds += classIds[i].value + ', ';
			}
			selectedClassIds += '0)';
		}
		$("#classNameIds").val(selectedClassIds);
		return true;
	} else if ($("input[name=chkBoxSelectedIds]:checked").length == 0) {
		alert("Please select at least one Class");
		return false;
	} else {
		return false;
	}
}
</script>