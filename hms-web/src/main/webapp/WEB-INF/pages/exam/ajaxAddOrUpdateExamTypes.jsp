<%@ include file="/common/taglibs.jsp"%>
	<jsp:include page="/common/messages.jsp" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/common/jquery.autoCheck.js"></script>
<s:form action="ajaxCreateSchoolExamTypes" theme="simple"
	cssClass="form-horizontal" id="updateExamTypes" method="post"
	namespace="/exam">
	<s:hidden name="examTypes.id" value="%{examTypes.id}"></s:hidden>
	<s:hidden name="tempString" value="examTypes"></s:hidden>
	<s:hidden name="examTypes.selectedClassIds" id="selectedClassIds"></s:hidden>
	<s:hidden name="examTypes.selectedExamSubTypeIds"
		id="selectedExamSubTypeIds"></s:hidden>
	<h4 class="bold pageTitle">
		<s:if test="%{examTypes.id != 0}">
				Update exam type
			</s:if>
		<s:else>
				Add exam type		
			</s:else>
	</h4>
	<p>
		<span class="label label-danger">NOTE :</span>&nbsp; You can give exam
		type name like term1,unit test 1,half yearly...etc.
	</p>
	<div class="form-body">
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="col-md-4 control-label"> <span
						class="required">*</span>Exam Type :
					</label>
					<div class="col-md-5">
						<sj:textfield name="examTypes.examType" id="examType"
							cssClass="required form-control input-medium" maxlength="40"></sj:textfield>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="col-md-5 control-label"> <span
						class="required">*</span>Pass Marks :
					</label>
					<div class="col-md-5">
						<sj:textfield name="examTypes.minMarks" id="minMarks"
							onkeypress="return onlyNumbers(event);"
							onblur="validateMarks(this);"
							cssClass="required numeric form-control input-medium"
							maxlength="5"></sj:textfield>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="col-md-4 control-label"> <span
						class="required">*</span>Max Marks :
					</label>
					<div class="col-md-5">
						<sj:textfield name="examTypes.maxMarks" id="maxMarks"
							onkeypress="return onlyNumbers(event);"
							onblur="validateMarks(this);"
							cssClass="required numeric form-control input-medium"
							maxlength="5"></sj:textfield>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="col-md-5 control-label"> <span
						class="required">*</span>Send Reminder Before No. of Days :
					</label>
					<div class="col-md-5">
						<sj:textfield size="5" name="examTypes.noOfDays" maxlength="2"
							cssClass="required form-control input-medium"
							onkeypress="return onlyNumbers(event);"></sj:textfield>
						<span class="help-block">(Reminder for SMS/E-mail)</span>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="col-md-4 control-label"> Content for Email
						reminder : </label>
					<div class="col-md-6">
						<sj:textarea rows="3" cols="30" name="examTypes.mailContentDesc"
							cssClass="form-control input-xlarge" readonly="true"
							value="Dear Parent,The <examination Type> exams are starting from <start date> get the timetable from website,Please help your son/daughter to score better marks."></sj:textarea>
						<span class="help-block">(Do not remove <strong><
								></strong>type variables)
						</span>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="col-md-4 control-label"> Content for SMS
						reminder : </label>
					<div class="col-md-6">
						<sj:textarea rows="3" cols="30" name="examTypes.mobileContentDesc"
							cssClass="form-control input-xlarge" readonly="true"
							value="Dear Parent <examination> examination is going to start from  <date>  for your children. Thank You From <school name>"></sj:textarea>
						<span class="help-block">(Do not remove <strong><
								></strong>type variables)
						</span>
					</div>
				</div>
			</div>
		</div>
		<s:if test="%{allUsersSet != null && !allUsersSet.isEmpty()}">
			<div class="form-group">
				<label class="col-md-2 control-label">
					<span class="required">*</span>Select Syllabus Type :
				</label>
				<div class="col-md-5" id="syllabusType">
					<s:checkboxlist name="chkBoxSelectedAccountIds" list="allUsersSet" onchange="javascript:getStudyClassListBasedOnSyllabusType()"
					listKey="id" listValue="syllabusTypeName" theme="ems" />
				</div>
			</div>
			<div id="displayStudyClassList"></div>
		</s:if>
		<s:else>
			<div class="alert alert-info" onclick="javascript:clickManageSchool('manageSchool');">
				Currently there are no syllabus types.
				<s:url id="urlForManageSchoolInfo" action="ajaxDoSchoolInformation" namespace="/admin">
				</s:url>
				<sj:a href="%{urlForManageSchoolInfo}" targets="mainContentDiv" data-toggle="tab" cssClass='btn default'  button="false">
					<font color="red"><b>Click here</b></font>
				</sj:a>
				to add syllabus types.
			</div> 
		</s:else>
		<%-- <div class="form-group">
			<label class="col-md-3 control-label">
			<span class="required">*</span>Syllabus Type :
			</label>
			<div class="col-md-5">
				<s:iterator value="allUsersSet">
					<s:radio id="selectSyllabus" theme="simple" onchange="javascript:getStudyClassListBasedOnSyllabusType(this.value)"
						cssClass="syllabusClassType"
						name="tempId2" list="#{id:syllabusTypeName}" />
				</s:iterator>
			</div>
		</div> --%>
		<%-- <div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="col-md-3 control-label"> <span
						class="required">*</span>Applicable Classes :
					</label>
				</div>
			</div>
		</div>
		<div class="form-group">
			<div class="col-md-11">
				<div class="checkbox-list">
					<label class="checkbox-inline"> <input type="checkbox"
						name="" value="" onClick="checkAll()" class="checkbox examTypes">
						All Classes
					</label>
				</div>
			</div>
		</div>
		<div class="form-group">
			<div class="col-md-11">
				<s:checkboxlist name="chkBoxSelectedIds" list="classList"
					listKey="id" listValue="className" theme="ems" />
			</div>
		</div> --%>
		<s:if test="%{objectList != null && !objectList.isEmpty() || (tempList != null && !tempList.isEmpty()) }">
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="conLable col-md-5 control-label"> <span
							class="required">*</span>Applicable Exam Sub Types :
						</label>
					</div>
				</div>
			</div>
			<div class="form-group">
				<div class="col-md-11">
					<div class="checkbox-list">
						<label class="checkbox-inline"> <input type="checkbox"
							name="" value="" onClick="checkAllExamSubTypes()"
							class="checkbox examTypesAndSubType"> All Exam Sub Types
						</label>
					</div>
				</div>
			</div>
			<div class="form-group">
				<div class="col-md-11">
					<s:if
						test="%{(tempList != null && !tempList.isEmpty()) && (objectList != null && !objectList.isEmpty()) }">
						<div class="panel-body col-md-12">
							<div class="col-md-1">
								<span class="label label-danger"> NOTE : </span>
							</div>
							<div class="col-md-10">
								<ul>
									<li>You can not edit or update SubTypes which have been
										already scheduled for exam.</li>
								</ul>
							</div>
						</div>
					</s:if>
					<s:if test="%{tempList != null && !tempList.isEmpty()}">
						<s:checkboxlist name="chkBoxClassSelectedIds" list="tempList"
							disabled="true" listKey="id" listValue="subTypeName" theme="ems" />
					</s:if>
					<s:if test="%{objectList != null && !objectList.isEmpty()}">
						<s:checkboxlist name="chkBoxClassSelectedIds" list="objectList"
							listKey="id" listValue="subTypeName" theme="ems" />
					</s:if>
				</div>
			</div>
		</s:if>
		<s:else>
			<div class="alert alert-info">Currently there are no exam
				subtype available.</div>
		</s:else>
		<div class="form-actions fluid">
			<div class="row">
				<div class="col-md-6">
					<div class="col-md-offset-3 col-md-12">
						<s:if test='%{#session.previousYear=="N"}'>
							<sj:submit cssClass="submitBt btn blue" value="Submit"
								indicator="indicator" targets="examContentInfo" validate="true"
								onBeforeTopics="schoolUpdateExamTypes" />
						</s:if>
						<s:url id="doAddNewExamTypeList"
							action="ajaxViewExamTypesAndGrades" includeParams="all"
							escapeAmp="false" namespace="/exam">
							<s:param name="tempString">examTypes</s:param>
						</s:url>
						<sj:a href="%{doAddNewExamTypeList}" cssClass="btn default"
							targets="examContentInfo">Cancel</sj:a>
					</div>
				</div>
			</div>
		</div>
	</div>
</s:form>
<script type="text/javascript">
$(document).ready(function() {
	 $.destroyTopic('schoolUpdateExamTypes');
	 $("input:checkbox, input:radio").uniform();
		 $("#examType").focus();
		
		 if($("input[name=chkBoxSelectedIds]:checked").length==$("input[name=chkBoxSelectedIds]").length){
		 	 $(".examTypes").parent('span').addClass("checked");
				$(".examTypes").attr("checked", true);
		 }
		 if($("input[name=chkBoxClassSelectedIds]:checked").length==$("input[name=chkBoxClassSelectedIds]").length){
		 	 $(".examTypesAndSubType").parent('span').addClass("checked");
				$(".examTypesAndSubType").attr("checked", true);
		 }
		$.subscribe('schoolUpdateExamTypes', function(event, data) {
			var selectedClassIds = [];
			if (($("input[name=chkBoxSelectedIds]:checked").length) > 0){
				$("input:checkbox[name=chkBoxSelectedIds]:checked").each(function()
				{
				    selectedClassIds.push($(this).val());
				});
				$('#selectedClassIds').val(selectedClassIds);		
				//return true;		
			}
			else {
				alert("Please select at least one class.");
				event.originalEvent.options.submit=false;
			}
			
			var selectedExamSubTypeIds = [];
			if (($("input[name=chkBoxClassSelectedIds]:checked").length) > 0){
				$("input:checkbox[name=chkBoxClassSelectedIds]:checked").each(function()
				{
					selectedExamSubTypeIds.push($(this).val());
				});
				$('#selectedExamSubTypeIds').val(selectedExamSubTypeIds);		
				//return true;		
			}
			else {
				alert("Please select at least one exam sub type.");
				event.originalEvent.options.submit=false;
			}
			if (($("input[name=chkBoxSelectedAccountIds]:checked").length) == 0){
				alert("Please select at least one syllabus type.");
				event.originalEvent.options.submit=false;
			}
		});
		 getStudyClassListBasedOnSyllabusType();
	});	
	function clickManageSchool(checkValue) {
		$('#schoolSettingsDiv').click();
		$('li#schoolSettingsDiv').addClass('active');
		$('li#ExaminationId').removeClass('active open');
		$('li#gradesAndExamTypes').removeClass('active');
		$('li#manageSchool').addClass('active');
	}
	$("input[name=chkBoxClassSelectedIds]").click(function() {
		if ($("input[name=chkBoxClassSelectedIds]:unchecked").length > 0) {
			$(".examTypesAndSubType").parent('span').removeClass("checked");
			$(".examTypesAndSubType").attr("checked", false);
		} else {
			$(".examTypesAndSubType").parent('span').addClass("checked");
			$(".examTypesAndSubType").attr("checked", true);
		}
	});
	function validateMarks(evt) {
		var minMarks = $('#minMarks').val();
		var maxMarks = $('#maxMarks').val();
		if (isNonEmpty(minMarks) && isNonEmpty(maxMarks)) {
			if (Number(minMarks) > Number(maxMarks)) {
				alert('Exam type pass marks should be less than max marks.');
				$(evt).val('');
			}
		}
	}
	function checkAll() {
		if ($(".examTypes").is(':checked')) {
			$("[name='chkBoxSelectedIds']").each(function() {
				$(this).parent('span').addClass('checked');
				$(this).attr("checked", "true");
			});
		} else {
			$("[name='chkBoxSelectedIds']").each(function() {
				$(this).parent('span').removeClass('checked');
				$(this).removeAttr("checked");
			});
		}
	}

	function checkAllExamSubTypes() {
		if ($(".examTypesAndSubType").is(':checked')) {
			$("[name='chkBoxClassSelectedIds']").each(function() {
				$(this).parent('span').addClass('checked');
				$(this).attr("checked", "true");
			});
		} else {
			$("[name='chkBoxClassSelectedIds']").each(function() {
				if (!this.disabled) {
					$(this).parent('span').removeClass('checked');
					$(this).removeAttr("checked");
				}
			});
		}
	}
	$('a#urlViewClassSectionInfo').click(function() {
		window.location.hash = "#target=ADM.ajaxify YARS";
		window.location.reload();
	});
 
function getStudyClassListBasedOnSyllabusType() {
		var selectedStudyClassIds=[] ;
		if (($("input[name=chkBoxSelectedAccountIds]:checked").length) > 0){
			$("input:checkbox[name=chkBoxSelectedAccountIds]:checked").each(function()
			{
				selectedStudyClassIds.push($(this).val());
			});
		}
		var examTypeId = '<s:property value="examTypes.id "/>';
		if (!(typeof selectedStudyClassIds == 'undefined' || selectedStudyClassIds == '' || selectedStudyClassIds == null || selectedStudyClassIds == 'null')) {
		$('#displayStudyClassList').html('<div align="center" style="padding:30px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		var url = jQuery.url.getChatURL("/exam/ajaxGetStudyClassListBySyllabusType.do");
		var pars = "chkBoxSelectedAccountIds="+selectedStudyClassIds+"&tempId1="+examTypeId;
		$.ajax( {
			type : "POST",
			url : url,
			data : pars,
			cache : false,
			success : function(html) {
				$("#displayStudyClassList").html(html);
				$("#displayStudyClassList").show();
			}
		});
	  }
	  else{
	     alert('Please select atleat one syllabus type.');
		 return false;
	  }
	}
</script>
<script type="text/javascript">
$(document).ready(function() {
	changePageTitle(" Add Exam Types");
	$('.blockHeader h2').html('Manage Academics');
	$("#examType")
		.autoCheck(
				"${pageContext.request.contextPath}/exam/ajaxCheckExamTypeAvailableOrNot.do",
				{
					minChars : 3,
					min : "no"
				});
});
</script>