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
	<%@ include file="/common/messages.jsp"%>
	<s:if test='%{(user.isOnlySchoolAdmin=="Y" && customer.hostelModuleStatus) || (user.isSchoolPrincipal=="Y" || user.isSchoolDirector == "Y" && customer.hostelModuleStatus=="Y")}'>
		<div class="grid_3 alpha">
			<label class="labelRight">
				Select Students for:
			</label>
		</div>
	  <div class="grid_5 alpha">
			<input type="radio" id="student" value="ROLE_STUDENT" class="radio"
					name="selectedName" checked="checked" onclick="ajaxGetStaffDeafaulters(this.value,'Hostel Fee Dues');">School</input>
			<input type="radio" id="staff" value="ROLE_HOSTEL" class="radio" onclick="ajaxGetStaffDeafaulters(this.value,'Hostel Fee Dues');"
					name="selectedName">Hostel</input>
	  </div>
	  </s:if>
		<s:form action="ajaxCommunityWiseMArks" theme="css_xhtml" name="buttonName"
			namespace="/reports" onsubmit="return communityWiseGenerateClassIds();"
			id="classAndTodate" method="post">
			<s:hidden name="tempString"></s:hidden>
			<s:hidden name="anyId" cssClass="anyId" value=""></s:hidden>
			<s:hidden id="plTitle" name="plTitle"></s:hidden>
			<s:hidden id="classNameIds" name="SelectedId" />
			<s:hidden id="examTypeIdsIds" name="examType" />
			<s:hidden id="communityTypeIds" name="anyTitle" />
			<span id="plTitle" class="<s:property value='plTitle'/>"></span>
			<s:hidden id="roleName" name="username" />
			<s:if test="%{(studyClassList != null && !studyClassList.isEmpty())}">
				<div class="grid_10">
					<h1>
						<span class="required">*</span> Available Class&Sections:
					</h1>
					<input type="checkbox" name="" value="" onClick="checkAllClasses()"
						class="checkbox allClasses">
					All Class&Sections
					<s:checkboxlist name="chkBoxSelectedIds" list="studyClassList"
						listKey="id" listValue="classAndSection" theme="ems"
						cssClass="small" />
				</div>
			</s:if>
			<s:if test='%{plTitle == "Community Wise Marks"}'>
				<s:if test="%{(castSettingList != null && !castSettingList.isEmpty())}">
					<div class="grid_10 checkbox">
							<h1>
								<span class="required">*</span> Available Communities:
							</h1>
							<input type="checkbox" name="" value=""
								onClick="checkAllCommunitieTypes()" class="checkbox allCommunities">
							All Communities
							<s:checkboxlist name="communityCheckBoxes" list="castSettingList"
								listKey="id" listValue="castName" theme="ems" cssClass="small" />
						</div>
				</s:if>
			</s:if>
			<s:if test='%{plTitle == "Class Wise Marks" || plTitle == "Community Wise Marks"}'>
				<s:if test="%{(examTypeList != null && !examTypeList.isEmpty())}">
					<div class="grid_10">
					 <h1>
						<span class="required">*</span> Available ExamTypes:
					 </h1>
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
			$("input[name=communityCheckBoxes]").click(function() {
				if ($("input[name=communityCheckBoxes]:unchecked").length > 0) {
					//$(".allReligions").removeAttr("checked");
					$(".allCommunities").attr("checked", false);
				} else {
					$(".allCommunities").attr("checked", true);
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
function checkAllCommunitieTypes() {
	if ($(".allCommunities").is(':checked'))
		$("input[name='communityCheckBoxes']").attr("checked", "true");
	else
		$("input[name='communityCheckBoxes']").removeAttr("checked");
}

function communityWiseGenerateClassIds() {
	var radioButtonSpanSize = $('span#plTitle').attr("class");
if("Class Wise Marks" == $('span#plTitle').attr("class") || "Community Wise Marks" == $('span#plTitle').attr("class")) {
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
  }
if("Community Wise Marks" == $('span#plTitle').attr("class")) {
         if ($("input[name=communityCheckBoxes]:checked").length > 0) {
         var cTypeIds = $("input[name=communityCheckBoxes]:checked");
         var selectedCommunityIds = '';
         if (cTypeIds.length > 0) {
			selectedCommunityIds = '(';
			for ( var i = 0; i < cTypeIds.length; i++) {
				selectedCommunityIds += cTypeIds[i].value + ',';
			}
			selectedCommunityIds += '0)';
		}
		$("#communityTypeIds").val(selectedCommunityIds);
         }else if ($("input[name=communityCheckBoxes]:checked").length == 0) {
		alert("Please select at least one Community type");
		return false;
	} else {
		return false;
	}
  }else{
     return false;
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
		var value = $("input[name='selectedName']:checked").val();
		    $("#roleName").val(value);
		return true;
	} else if ($("input[name=chkBoxSelectedIds]:checked").length == 0) {
		alert("Please select at least one Class");
		return false;
	} else {
		return false;
 }
}
</script>