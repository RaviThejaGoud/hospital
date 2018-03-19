<%@ include file="/common/taglibs.jsp"%>

	<jsp:include page="/common/messages.jsp" />
	
<s:form id="addStudentMarksFormId" action="ajaxAddStudentMarks" method="post" theme="simple" cssClass="form-horizontal" namespace="/exam">
	<div class="form-body">
	<s:if test="%{studyClassList != null && !studyClassList.isEmpty()}">
		<h4 class="bold pageTitle">
			Add Student Marks
				<div class="row" style="float:right;width:150px;margin-top:-10px;">
					<s:url id="doAddMarksBackLink" action="ajaxManageStudentMarks"
						includeParams="all" escapeAmp="false" namespace="/exam">
					</s:url>
					<sj:a href="%{doAddMarksBackLink}" cssClass="btn default"
						targets="mainContentDiv" indicator="indicator">
						<i class="fa fa-mail-reply"></i> Back</sj:a>
				</div>
			</h4>
		<div class="panel-body col-md-12">
			<div class="col-md-1">
				<span class="label label-danger"> NOTE : </span>
			</div>
			<div class="col-md-10">
				<ul>
					<li>
						Please select class then system shows exam types applicable for this class then select sub type of the exam type then it displays all the student details"."
					</li>
					<li>
						Disabled inputs are optional subjects selected according to students.You can enter marks only for optional subjects remaining optional subjects are disabled. 
					</li>
					<li>
						If a student doesn't have any Selected optional subjects all the optional subjects are enabled and you can enter marks only for any one of the optional subjects.
					</li>
					<li>
						Now you can add/update the marks and click on the submit button. 
					</li>
					
					<!-- <li>
						<font color="red">Please do not add new columns or delete
							the marked columns</font>. If you want add more columns, please contact
						EazySchool support team(support@eazyschool.com).
					</li> -->
				</ul>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-md-4">
				<span class="required">*</span>Select Class :
			</label>
			<div class="col-md-5">
				<s:select list="studyClassList" listKey="id"
					listValue="classAndSection" id="classSection"
					cssClass="form-control input-medium required"
					onchange="javascript:getClassExamTypes(this.value);"
					name="classId" headerKey="" headerValue="- Select -"
					theme="simple">
				</s:select>
			</div>
		</div>
		<div id="examTypesCont"> </div>
		
	</s:if>
	<s:else>
		<div class="alert alert-info">
			You are not handling any class sections.
		</div>
	</s:else>
	</div>
</s:form>
<script type="text/javascript">
function getClassExamTypes(classId) {
	if (isNonEmpty(classId)) {
		$('#examTypesCont') .html( '<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		var pars = "classId=" + classId;
		$.ajax( { 
				url : jQuery.url.getChatURL("/exam/ajaxGetClassExamTypesForAddMarks.do"),
				cache : true,
				data : pars,
				success : function(response) {
					$('#examTypesCont').html(response);
				}
			});
	}
}

function getSubmitErrors() {
	var classSectionId = $("#classSection").val();
	if (isNonEmpty(classSectionId)) {
		var className = $("#classSection  option[value=" + classSectionId + "]")
				.text();
		if (isNonEmpty(className))
			$("[name='anyTitle']").val(className);
	} else {
		alert("Please select class.");
		return false;
	}
	var examTypeId = $("#examTypeListId").val();
	if (isNonEmpty(examTypeId)) {
		$("[name='tempString']").val(
				$("#examTypeListId  option[value=" + examTypeId + "]").text());
	} else {
		alert("Please select exam type.");
		return false;
	}
	var admissionNumber = $('input[name=SelectType]:radio:checked').val();
	$("#admisnum").val(admissionNumber);
}
</script>