<%@ include file="/common/taglibs.jsp"%>
<head>
	<title>SMS | School Student Community Details</title>
</head>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>
<div class="block_head">
	<div class="grid_12 ">
		<h2 id="AdminReports">
		</h2>
	</div>
</div>
<div class="block_content">
	<div id="commonStep13">
		<s:form action="ajaxViewClassWiseMarksDetails" theme="css_xhtml"
			namespace="/reports" onsubmit="return generateClassIds();"
			id="classAndTodate" method="post">
			<s:hidden name="tempString"></s:hidden>
			<s:hidden name="anyId" cssClass="anyId" value=""></s:hidden>
			<s:hidden id="plTitle" name="plTitle"></s:hidden>
			<s:hidden id="classNameIds" name="SelectedId" />
			<s:hidden id="examTypeIdsIds" name="examType" />
			<span id="plTitle" class="<s:property value='plTitle'/>"></span>
			<s:if test='%{plTitle != "Class Wise Marks"}'>
				<div class="grid_10">
					<div class="grid_2">
						<label>
							<span class="required">*</span>From Date:
						</label>
					</div>
					<div class="grid_4">
						<sj:datepicker id="startDate" name="startDate" maxDate="0"
							showButtonPanel="true"  required="true" />
					</div>
				</div>
			</s:if>
			<s:if test="%{(studyClassList != null && !studyClassList.isEmpty())}">
				<div class="grid_10">
					<input type="checkbox" name="" value="" onClick="checkAllClasses()"
						class="checkbox allClasses">
					All Class&Sections
					<s:checkboxlist name="chkBoxSelectedIds" list="studyClassList"
						listKey="id" listValue="classAndSection" theme="ems"
						cssClass="small" />
				</div>
			</s:if>

			<s:if test='%{plTitle == "Class Wise Marks"}'>
				<s:if test="%{(examTypeList != null && !examTypeList.isEmpty())}">
					<div class="grid_10">
						<input type="checkbox" name="" value=""
							onClick="checkAllExamTypes()" class="checkbox allExamTypes">
						All ExamTypes
						<s:checkboxlist name="chkBoxExamTypeIds" list="examTypeList"
							listKey="id" listValue="examType" theme="ems" cssClass="small" />
					</div>
				</s:if>
			</s:if>
			<div class="grid_10" style="margin-right: 26px;">
				<s:submit type="submit small" value="Generate Pdf"
					onclick="getFormateType()" cssClass="long submit"
					title="generate report" cssStyle="cursor:pointer">
				</s:submit>
			</div>
		</s:form>
	</div>
</div>
<div id="schoolTermlist" class="grid_9"></div>
<script type="text/javascript">
$(document).ready(
		function() {
			$('h2#AdminReports').text(
					$('div#sideMenu li.active a').text() + "-->"
							+ $('div#sideSubMenu li.active a').text())
			$("input[name=chkBoxSelectedIds]").click(function() {
				if ($("input[name=chkBoxSelectedIds]:unchecked").length > 0) {
					//$(".allReligions").removeAttr("checked");
					$(".allClasses").attr("checked", false);
				} else {
					$(".allClasses").attr("checked", true);
				}
			});
			$("input[name=chkBoxExamTypeIds]").click(function() {
				if ($("input[name=chkBoxExamTypeIds]:unchecked").length > 0) {
					//$(".allReligions").removeAttr("checked");
					$(".allExamTypes").attr("checked", false);
				} else {
					$(".allExamTypes").attr("checked", true);
				}
			});
		});
function getFormateType() {
	$('.anyId').val('PDF');
}
function checkAllClasses() {
	if ($(".allClasses").is(':checked'))
		$("input[name='chkBoxSelectedIds']").attr("checked", "true");
	else
		$("input[name='chkBoxSelectedIds']").removeAttr("checked");
}
function checkAllExamTypes() {
	if ($(".allExamTypes").is(':checked'))
		$("input[name='chkBoxExamTypeIds']").attr("checked", "true");
	else
		$("input[name='chkBoxExamTypeIds']").removeAttr("checked");
}
function generateClassIds() {
	var radioButtonSpanSize = $('span#plTitle').attr("class");
if("Class Wise Marks" == $('span#plTitle').attr("class")) {
         if ($("input[name=chkBoxExamTypeIds]:checked").length > 0) {
         var typeIds = $("input[name=chkBoxExamTypeIds]:checked");
         var selectedExamTypeIds = '';
         if (typeIds.length > 0) {
			selectedExamTypeIds = '(';
			for ( var i = 0; i < typeIds.length; i++) {
				selectedExamTypeIds += typeIds[i].value + ',';
			}
			selectedExamTypeIds += '0)';
		}
		$("#examTypeIdsIds").val(selectedExamTypeIds);
         }else if ($("input[name=chkBoxExamTypeIds]:checked").length == 0) {
		alert("Please select at least one Exam type");
		return false;
	} else {
		return false;
	}
  }else{
  var startDate = $('#startDate').val();
  if (startDate == '') {
			alert("Please select from date.");
			return false;
	}
  }
	if ($("input[name=chkBoxSelectedIds]:checked").length > 0) {
		var classIds = $("input[name=chkBoxSelectedIds]:checked");
		var selectedClassIds = '';
		if (classIds.length > 0) {
			selectedClassIds = '(';
			for ( var i = 0; i < classIds.length; i++) {
				selectedClassIds += classIds[i].value + ',';
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