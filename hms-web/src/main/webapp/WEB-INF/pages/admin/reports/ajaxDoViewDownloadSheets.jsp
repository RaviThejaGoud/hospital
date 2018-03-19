<%@ include file="/common/taglibs.jsp"%>
<div class="block_head">
	<div class="grid_12 ">
		<h2 id="AdminReports">
		</h2>
	</div>
</div>
<div id="allFeeReports" class="block_content">
	<div id="commonStep13">
		<s:if
			test="%{(classList != null && !classList.isEmpty()) || (studyClassList != null && !studyClassList.isEmpty())}">
			<s:form action="ajaxselectedTypeReports" theme="css_xhtml"
				onsubmit="javascript:return generateReportsWithClassIds();"
				id="classAndCommunity" method="post" namespace="/reports">
				<s:hidden id="classNameIds" name="selectedId" />
				<s:hidden name="tempString"></s:hidden>
				<s:hidden name="plTitle"></s:hidden>
				<s:hidden name="anyId" cssClass="anyId" value=""></s:hidden>
				<div class="grid_10 checkbox">
					<s:if test="%{(classList != null && !classList.isEmpty())}">
						<h1>
							<span class="required">*</span> Available Classes:
						</h1>
						<input type="checkbox" name="" value=""
							onClick="checkAllClassesForReports()" class="checkbox allClasses">
					All Classes
					<s:checkboxlist name="chkBoxSelectedIds" list="classList"
							listKey="id" listValue="className" theme="ems" cssClass="small" />
					</s:if>
					<s:else>
						<h1>
							<span class="required">*</span> All Class&Sections::
						</h1>
						<s:if test='%{plTitle == "TCGeneration"}'>
							<s:iterator value="studyClassList">
								<div class="grid_3">
									<s:radio id="classSectionId" theme="simple" name="tempId"
										list="#{id:classAndSection}" required="true" />
								</div>
							</s:iterator>
						</s:if>
						<s:else>
							<input type="checkbox" name="" value=""
								onClick="checkAllClassesForReports()"
								class="checkbox allClasses">
					       All Class&Sections
					      <s:checkboxlist name="chkBoxSelectedIds"
								list="studyClassList" listKey="id" listValue="classAndSection"
								theme="ems" cssClass="small" />
						</s:else>
					</s:else>
				</div>
				<div class="grid_10" style="margin-right: 26px;">
					<s:submit type="submit small" value="Excel" cssClass="submit small"
						title="generate report" onclick="reportFormate()"
						cssStyle="cursor:pointer">
					</s:submit>
					<s:submit type="submit small" value="Pdf" onclick="reportType()"
						cssClass="submit small" title="generate report"
						cssStyle="cursor:pointer">
					</s:submit>

				</div>
			</s:form>
		</s:if>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function() {
	$('#overalAndToday').show();
	$('h2#AdminReports').text($('div#sideMenu li.active a').text()+"-->"+$('div#sideSubMenu li.active a').text())
	changePageTitle("Marks Card Generation");
	$("input[name=chkBoxSelectedIds]").click(function() {
		if ($("input[name=chkBoxSelectedIds]:unchecked").length > 0) {
			//$(".allClasses").removeAttr("checked");
			$(".allClasses").attr("checked", false);
		} else {
			$(".allClasses").attr("checked", true);
		}
	});
});
function reportFormate() {
	$('.anyId').val('Excel');
}

	
function checkAllClassesForReports() {
	if ($(".allClasses").is(':checked'))
		$("input[name='chkBoxSelectedIds']").attr("checked", "true");
	else
		$("input[name='chkBoxSelectedIds']").removeAttr("checked");
}
function allornone() {
	if ($("input[name='chkBoxSelectedIds']").is(':checked'))
		$(".allClasses").attr("checked", "true");
	else
		$(".allClasses").removeAttr("checked");
}
function generateReportsWithClassIds() {
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
		return true;
	}
}
</script>
