<%@ include file="/common/taglibs.jsp"%>
<s:form id="editClassSubjects" action="ajaxEditSaveSubjects" cssClass="form-horizontal"
	method="post" theme="simple" namespace="/admin">
	<s:hidden name="classId" value="%{classId}" />
	<s:hidden name="subject" id="subNames"></s:hidden>
	<s:hidden name="anyTitle" id="syllabusTypeId"></s:hidden>
	<h4 class="bold pageTitle">
		Update Class Subjects
	</h4>
	<div class="panel-body col-md-12">
		<div class="col-md-1">
			<span class="label label-danger"> NOTE: </span>
		</div>
		<div class="col-md-10">
			<ul>
				 <li>Changing of class name should not be same as other class name in the school.</li>
				 <li>Changing of Section name should not be same as other section name in that class.</li>
				 <li>Changing section capacity should be more than actual section capacity.</li>
				 <li>Cannot remove the subjects from the section if the subject contains the exam schedule or marks.</li>
			</ul>
		</div>
	</div>
	<div class="form-body">
		<div class="row">
		<div class="col-md-6">
			<div class="form-group">
				<label class="col-md-4 control-label">
					Class Name :
				</label>
				<div class="col-md-7">
					<sj:textfield name="studyClass.className" id="className"
						cssClass="required form-control input-medium" />
					<span class="help-block">(Changing this class name will
						effect to all sections of class name.)</span>
				</div>
			</div>
		</div>
		<div class="col-md-6">
			<div class="form-group">
				<label class="col-md-4 control-label">
					Section Name :
				</label>
				<div class="col-md-7">
					<sj:textfield name="studyClass.section" id="sectionName"
						cssClass="form-control input-medium"
						onchange="selectSectionRadioButton(this.value)" />
					<span class="help-block">(Select section name from the below
						section list or type the new section name.)</span>
				</div>
			</div>
		</div>
	</div>
		<s:if test="%{sectionList != null && !sectionList.isEmpty()}">
			<div class="form-group">
				<label class="col-md-2 control-label">
					Select Section :
				</label>
					<div class="col-md-9">
						<s:select list="sectionList" listKey="section" listValue="section"
							id="selectedSectionName" name="sectionName"
							headerKey="" cssClass="form-control input-medium" onchange="displaySectionName(this.value)"
							headerValue="- Select Selction -" theme="simple" />
				</div>
			</div>
		</s:if>
		<div class="row">
		<div class="col-md-6">
			<div class="form-group">
				<label class="col-md-4 control-label">
					Section Capacity :
				</label>
				<div class="col-md-5">
					<sj:textfield name="studyClass.sectionCapacity" id="noOfStudents"
						cssClass="required form-control input-medium" maxlength="3" />
				</div>
			</div>
		</div>
		<div class="col-md-6">
			<div class="form-group">
				<label class="col-md-4 control-label">
					Group Number :
				</label>
				<div class="col-md-5">
					<sj:textfield name="studyClass.groupNumber" id="groupNumber"
						cssClass="form-control input-medium" />
				</div>
			</div>
		</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
				<label class="col-md-4 control-label">
					Is Higher class :
				</label>
				<div class="col-md-8">
					<p class="form-control-static">
					<s:if test="%{studyClass.classNameClassId.higherStandard == true}">
						<s:checkbox name="studyClass.classNameClassId.higherStandard" fieldValue="true"/>
					</s:if>
					<s:else>
						<s:checkbox name="studyClass.classNameClassId.higherStandard" />
					</s:else>
						
						<span class="help-block">(Please select this field if this
							class is above than 10th class.)</span>
					</p>
				</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="col-md-4 control-label">
						Education Type : 
					</label>
					<div class="col-md-8">
							<div class="clearfix">
								<div data-toggle="buttons" class="btn-group" id="educationType">
									<label class="btn blue radioMultiOption" id='<s:property value="studyClass.educationType"/>'>
										<input type="radio" class="toggle" name="studyClass.educationType" value="">None</label>
									<label class="btn blue radioMultiOption"  id='<s:property value="studyClass.educationType"/>'>
										<input type="radio" class="toggle" name="studyClass.educationType" value="General Education">General</label>
									<label class="btn blue radioMultiOption"  id='<s:property value="studyClass.educationType"/>'>
										<input type="radio" class="toggle" name="studyClass.educationType" value="Vocational Education">Vocational</label>
								</div>
							</div>
					</div>
				</div>
			</div>
		</div>
		<s:if test="%{allUsersSet != null && !allUsersSet.isEmpty()}">
			<div class="row">
				<div class="col-md-12">
					<div class="form-group">
						<label class="col-md-2 control-label">
							<span class="required">*</span> Syllabus Type : 
						</label>
						<div class="col-md-10">
							<div class="radio-list" id='<s:property value="studyClass.syllabusType.id"/>'>
								<s:radio list="allUsersSet" listKey="id" theme="ems" cssClass="syllabsMultiOption"
									listValue="syllabusTypeName" name="syllabusId" id="SyllabusType" />
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
			<div class="col-md-6">
				<div id="syllabusTypeSchoolCodeDivId">
						<div class="form-group">
							<label class="col-md-4 control-label">
							Select School Code :
							</label>
							<div class="col-md-5">
								<s:select list="tempList1" listKey="id" listValue="schoolCode" id="schoolCodeSeleId"
								cssClass="form-control input-medium"
								name="studyClass.syllabusTypeSchoolCode.id" headerKey="" headerValue="- Select -" theme="simple"/>
							</div>
						</div>
					
				</div>
			</div>
			</div>
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
		<div class="row">
			<s:if test="%{tempList != null && !tempList.isEmpty()}">
			<div class="col-md-6">
				<div class="form-group">
					<label class="col-md-4 control-label">
					<span class="required">*</span>Select Medium :
					</label>
					<div class="col-md-5">
						<s:select list="tempList" listKey="id" listValue="name"
							id="studyClassMediumId" name="studyClass.mediumId.id"
							headerKey="" cssClass="required form-control input-medium"
							headerValue="- Select Medium -" theme="simple" />
					</div>
				</div>
			</div>
		</s:if>
			<s:else>
					<s:hidden name="studyClass.mediumId.id" value="0"></s:hidden>
			</s:else>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">
				<span class="required">*</span>Select Subjects :
			</label>
			<div class="col-md-9">
				<div class="checkbox-list">
					<s:checkboxlist name="objectList" list="studySubjectList"
						listKey="id" listValue="name" theme="ems"></s:checkboxlist>
				</div>
			</div>
		</div>
		<s:if test='%{#session.previousYear == "N"}'>
		<div class="form-actions fluid">
			<div class="col-md-6">
				<div class="col-md-offset-3 col-md-12">
					<sj:submit   targets="classContentDiv" value="Submit"
						cssClass="submitBt btn blue" formIds="editClassSubjects"
						onBeforeTopics="editClassValidation" indicator="indicator1"
						validate="true" />
				</div>
			</div>
		</div>
	</s:if>
 </div>	
</s:form>
<div class="spaceDiv"></div>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/plugins/bootstrap-switch/static/js/bootstrap-switch.min.js">
</script>
<script type="text/javascript">
$.destroyTopic('editClassValidation'); 
	 $.subscribe('editClassValidation',function(event, data) {
			var selectedChkCount = $("input[name=objectList]:checked").length;
			var syllabusIdCount = $("input[name=syllabusId]:checked").length;
			if (selectedChkCount > 0){
			//alert(selectedChkCount);
					var subjectIds = [];
					$("input:checkbox[name=objectList]:checked").each(function()
					{
					    subjectIds.push($(this).val());
					});
					$('#subNames').val(subjectIds);
				var syllabusTypeId = $('input[name=syllabusId]:checked').val();
					if(isNonEmpty(syllabusTypeId)){
						$('#syllabusTypeId').val(syllabusTypeId);	
					}else{
						$('#syllabusTypeId').val(0);
					} 
				//	return true;	
			}
			else {
				alert("Please select at least one subject.");
				event.originalEvent.options.submit=false;
			}
			if(syllabusIdCount==0){
				alert("Please select at least one syllabusType.");
				event.originalEvent.options.submit=false;
			}
	});

	$(document).ready(function() {
		
		var schoolCode ='<s:property value="studyClass.syllabusTypeSchoolCode.id"/>';
		var syllabusTypelength = $('input.syllabsMultiOption').length;
		if(parseInt(syllabusTypelength) == 1)
		{
			var tempString3 = '<s:property value="tempString3"/>'; 
			if(tempString3 == "Y")
			{
				$('#syllabusTypeSchoolCodeDivId').show();
				$("input[name=syllabusId]").attr('disabled', true);
			}
			else
			{
				$('#syllabusTypeSchoolCodeDivId').hide();
			}
		}
		var syllabusTypeId = '<s:property value="studyClass.syllabusType.id"/>';
		if(isNonEmpty(syllabusTypeId)){
			$('input.syllabsMultiOption').each(function(){
				  if($(this).parents('div').attr('id')==$(this).val()){
					$(this).parent('span').addClass('checked');
				    $(this).attr("checked", "true");
				 } 
			});
		}
		$('input[name=sectionName]').parents('label').css({width:'90px'});
		$('input[name=syllabusId]').parents('label').css({width:'100px'});
		$('label.radioMultiOption').each(function(){
		 if($(this).attr('id')==$(this).children('input').val()){
			 $(this).addClass('active');
		 }
		});
		FormComponents.init();
		FormAdvanced.init();
		$("input:checkbox, input:radio:not('.toggle')").uniform();
		var sectionName=$('#sectionName').val();
		selectSectionRadioButton(sectionName);
		$('#noOfStudents').numeric();
		//$('.alphabet').alpha();
		//$('.alphanumeric').alphanumeric();
	});
	function displaySectionName(sectionName){
		$('#sectionName').val(sectionName);
	}
	function selectSectionRadioButton(sectionName){
		$("div#sectionsContent  label").removeClass('ui-state-active');
		if(isNonEmpty(sectionName)){
			sectionName=sectionName.toUpperCase();
			$("div#sectionsContent label[for='selectedSectionName"+sectionName+"']").addClass('ui-state-active');
		}
	}
	function clickManageSchool(checkValue) {
		$('#schoolSettingsDiv').click();
		$('li#schoolSettingsDiv').addClass('active');
		$('li#academicsDiv').removeClass('active open');
		$('li#studyClassId').removeClass('active');
		$('li#manageSchool').addClass('active');
	}
	
	
	$('.syllabsMultiOption').click(function(){
		//alert("test"+$(this).val());
		$.ajax({
			url : jQuery.url.getChatURL("/admin/ajaxSyllabusTypeSchoolCode.do?syllabusType.id="+$(this).val()),
			cache : false,
			success : function(html) {
				$("#syllabusTypeSchoolCodeDivId").show();
				$("#syllabusTypeSchoolCodeDivId").html(html);
				
				
				$('#syllabusTypeListLabelId').removeClass('col-md-3');
				$('#syllabusTypeListLabelId').addClass('col-md-4');
				
				
			}
		});
			//alert($(this).is(":checked") +"---"+$(this).val());
	});
	
	
	
	
	
		
</script>