<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/messages.jsp"%>
<s:if test="%{className != null}">
	<s:form id="addNewClass" action="ajaxDoAddAnotherSection" method="post"
		theme="simple" cssClass="form-horizontal" namespace="/admin">
		<div class="row">
			<div class="col-md-6">
				<h4 class="bold pageTitle">
					Add sections
				</h4>
			</div>
			<!--<div class="col-md-6">
				<s:url id="urlViewClassSection" action="ajaxDoManageClassSections" />
				<sj:a id="viewClassSec" href="%{urlViewClassSection}"
					cssClass="btn default button-previous"
					cssStyle="text-align:right;float:right;" targets="mainContentDiv"
					indicator="indicator">
					<i class="m-icon-swapleft"></i>&nbsp;Back to Class &amp; Sections</sj:a>
			</div>
		-->
		</div>
		<p>
			<span class="label label-danger">NOTE :</span>&nbsp; Create Class &amp;
			Sections with Subjects, Assign Class Teacher &amp; Teacher for each
			subject and Syllabus.
		</p>
		<div class="form-body"></div>
		<div class="form-body">
			<s:hidden name="className.className"></s:hidden>
			<s:hidden name="className.id" value="%{className.id}"
				id="classNameId"></s:hidden>
			<s:hidden name="section" id="secNames"></s:hidden>
			<s:hidden name="subject" id="subNames"></s:hidden>
			<div class="row">
				<div class="col-md-5">
					<div class="form-group">
						<label class="col-md-5 control-label">
							<span class="required">*</span>Class Name :
						</label>
						<div class="col-md-6">
							<sj:textfield name="className.className" id="className"
								cssClass="required form-control input-medium alphanumeric" maxlength="45"
								readonly="true" disabled="true" />
						</div>
					</div>
				</div>
				<div class="col-md-5">
					<div class="form-group">
						<label class="col-md-5 control-label">
							Section Name :
						</label>
						<div class="col-md-6">
							<sj:textfield name="sectionName" id="sectionNames" value=""
								cssClass="form-control input-medium" />
							<span class="help-block">(Enter section name with comma
								separator.)</span>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-5">
					<div class="form-group">
						<label class="col-md-5 control-label">
							<span class="required">*</span>Each Section Capacity :
						</label>
						<div class="col-md-6">
							<sj:textfield name="className.noOfStudents" id="noOfStudents"
								value="" cssClass="required form-control numeric input-medium"
								maxlength="3"></sj:textfield>
							<span class="help-block">(Section capacity can be edited
								from section edit.)</span>
						</div>
					</div>
				</div>
			</div>
			<s:if test="%{studySubjectList!=null && !studySubjectList.isEmpty()}">
				<div class="form-group">
					<label class="col-md-2 control-label">
						<span class="required">*</span>Select Subjects :
					</label>
					<div class="col-md-9">
						<s:checkboxlist name="objectList" list="studySubjectList"
							listKey="id" listValue="name" theme="ems" />
					</div>
				</div>
			</s:if>
			<s:else>
				<div class="alert alert-info">
					There are no subjects available. Please add Subjects.
					<!-- Please add <a href=#>Subjects</a>-->
				</div>
			</s:else>
			<s:if test="%{objectList!=null && !objectList.isEmpty()}">
				<div class="form-group">
					<label class="col-md-2 control-label">
						<span class="required">*</span>Select Sections :
					</label>
					<div class="col-md-9">
						<s:checkboxlist name="tempList" list="objectList"
							listKey="section" listValue="section" theme="ems" />
					</div>
				</div>
			</s:if>
			<s:else>
				<div class="alert alert-info">
					There are no sections available.
				</div>
			</s:else>
			<div class="form-actions fluid">
				<div class="col-md-offset-2 col-md-10">
					<sj:submit targets="classContentDiv" value="Next"
						cssClass="submitBt btn blue" formIds="addNewClass" validate="true"
						indicator="indicator" onBeforeTopics="addClassValidation" />
					<span class="help-block">By clicking next button, system
						creates Class and Sections and in next step, you can assign Class
						& Teacher for each subjects.</span>
				</div>
			</div>
		</div>
	</s:form>
</s:if>
<s:else>
	<s:form id="addNewClass" action="ajaxAddClass" method="post"
		cssClass="form-horizontal" theme="simple" namespace="/admin">
		<div class="row">
			<div class="col-md-6">
				<h4 class="bold pageTitle">
					Add class &amp; sections
				</h4>
			</div>
			<!--<div class="col-md-6">
						<s:url id="urlViewClassSection" action="ajaxDoManageClassSections" />
						<sj:a id="viewClassSec" href="%{urlViewClassSection}" cssClass="btn default button-previous"  cssStyle="text-align:right;float:right;"
							targets="mainContentDiv" indicator="indicator"><i class="m-icon-swapleft"></i>&nbsp;Back to Class &amp; Sections</sj:a>
					</div>
					-->
		</div>
		<p>
			<span class="label label-danger">NOTE :</span>&nbsp; You can create
			class & section with subjects in this page. In the next page you can
			assign teacher for each subject and class teacher for this class
			<!--  and Syllabus -->
		<div class="form-body"></div>
		<div class="form-body">
			<s:hidden name="section" id="secNames"></s:hidden>
			<s:hidden name="subject" id="subNames"></s:hidden>
			<s:hidden name="className.id" value="0" id="classNameId"></s:hidden>
			<s:if test="%{studySubjectList!=null && !studySubjectList.isEmpty()}">
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="col-md-4 control-label">
								<span class="required">*</span>Class Name :
							</label>
							<div class="col-md-5">
								<sj:textfield name="className.className" id="className"
									cssClass="required form-control input-medium" maxlength="45" />
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label class="col-md-4 control-label">
								Section Name :
							</label>
							<div class="col-md-6">
								<sj:textfield name="sectionName" id="sectionNames"
									cssClass="form-control input-medium" />
								<span class="help-block">(Enter multiple section names by
									using comma(,).)</span>
							</div>
						</div>
					</div>
				</div>
				<s:if test="%{objectList!=null && !objectList.isEmpty()}">
					<div class="form-group">
						<label class="col-md-2 control-label">
							Select Sections :
						</label>
						<div class="col-md-9">
							<s:checkboxlist name="tempList" list="objectList"
									listKey="section" listValue="section" theme="ems" />
						</div>
					</div>
				</s:if>
				<s:else>
					<div class="alert alert-info">
						There are no sections available.
					</div>
				</s:else>
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="col-md-4 control-label">
								<span class="required">*</span>Each Section Capacity :
							</label>
							<div class="col-md-6">
								<sj:textfield name="className.noOfStudents" id="noOfStudents"
									cssClass="required form-control numeric input-medium"
									maxlength="3"></sj:textfield>
								<span class="help-block">(Section capacity can be edited
									from Class &amp; Section edit.)</span>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label class="col-md-4 control-label">
								Is Higher class :
							</label>
							<div class="col-md-6">
								<p class="form-control-static">
									<s:checkbox name="className.higherStandard" />
									<span class="help-block">(Please select this field if
										this class is above than 10th class.)</span>
								</p>
							</div>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label">
						<span class="required">*</span>Select Subjects :
					</label>
					<div class="col-md-9">
						<s:checkboxlist name="objectList" list="studySubjectList"
							listKey="id" listValue="name" theme="ems" />
					</div>
				</div>
				<div class="form-actions fluid">
					<div class="col-md-offset-2 col-md-10">
						<sj:submit targets="classContentDiv" value="Next"
							cssClass="submitBt btn blue" formIds="addNewClass"
							validate="true" indicator="indicator"
							onBeforeTopics="addClassValidation" />
						<!-- <span class="help-block">(By clicking next button, system
							creates class and sections and in next step you can assign
							teacher for each subject and class teacher for this class and
							sections.)</span> -->
					</div>
				</div>
			</s:if>
			<s:else>
				<div class="alert alert-info">
					There are no subjects available. Please add Subjects. Then create
					classes.
				</div>
			</s:else>
		</div>
	</s:form>
</s:else>
<script type="text/javascript">
	$(document).ready(function() {
	$('input[name=tempList]').parents('label').css({width:'110px'});
   $.destroyTopic('addClassValidation');
	$("input:checkbox, input:radio").uniform();
		changePageTitle("Add Class");
		$("#className").focus();
		$('.blockHeader h2').html('Manage Academics');
		$.subscribe('addClassValidation', function(event, data) {
				var selectedChkCount = $("input[name=objectList]:checked").length;
				var classNameId= $('#classNameId').val();
				if (selectedChkCount > 0){
					var selectedSecNames = [];
					if(isNonEmpty($('#sectionNames').val())){
						var sections = $('#sectionNames').val().split(',');
						$.each(sections, function(){
							if(isNonEmpty(this.replace(/^\s+|\s+$/g,''))){
								selectedSecNames.push(this.replace(/^\s+|\s+$/g,''));
							}
						});
					}
					$("input:checkbox[name=tempList]:checked").each(function()
					{
					    selectedSecNames.push($(this).val());
					});
					$('#secNames').val(selectedSecNames);
					if(!isNonEmpty(selectedSecNames) && isNonEmpty(classNameId) && classNameId > 0){
						alert('Please select at least one section.');
						event.originalEvent.options.submit=false;
					}
					var subjectIds = [];
					$("input:checkbox[name=objectList]:checked").each(function()
					{
					    subjectIds.push($(this).val());
					});
					$('#subNames').val(subjectIds);	
				}
				else {
					alert("Please select at least one subject.");
					event.originalEvent.options.submit=false;
				}
		});
		$('.numeric').numeric();
		$('.alphabet').alpha();
		$('.alphanumeric').alphanumeric();
		$('.alphanumeric').alphanumeric();
		$('#sectionNames').alphanumeric( {
			allow : ","
		});
	});
</script>