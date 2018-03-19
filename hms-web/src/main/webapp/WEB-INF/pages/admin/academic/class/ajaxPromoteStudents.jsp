<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/messages.jsp"%>
<s:form action="ajaxPromoteStudents" method="post" theme="simple" cssClass="form-horizontal" id="promoteStudentsForm" namespace="/admin">
	<s:hidden id="classIds" name="selectedId"></s:hidden>
	<s:hidden id="examTypesIds" name="anyId"></s:hidden>
	<s:hidden name="usrChgedAcademicId" value="%{usrChgedAcademicId}"></s:hidden>
		<p><span class="label label-danger"> NOTE : </span>&nbsp;Promote students to corresponding classes.</p>
		<s:if test="%{classList != null && !classList.isEmpty()}">
		<div class="form-body">
			<label class="conLable">
					Select promoting classes :
			</label><br/>
			<label class="control-label">
				<input type="checkbox" name="" value="" onClick="checkAll()"
						class="checkbox promoteClasses">
					Select all classes
			</label>
			<div class="form-group">
				<label class="col-md-2 control-label">
				</label>
				<div class="col-md-12">
					<s:checkboxlist list="classList" listKey="classSectionId" theme="ems"
					listValue="classAndSection" cssClass="required"
					name="objectList"></s:checkboxlist>
				</div>
			</div>
			<br/>
			<s:if test="%{classStudentsList != null && !classStudentsList.isEmpty()}">
			<label class="control-label">
					Promoted classes
			</label>
			<div class="form-group">
				<label class="col-md-2 control-label">
				</label>
				<div class="col-md-12">
					<s:checkboxlist list="classStudentsList" listKey="classSectionId" theme="ems" name="promoteClasses"
					listValue="classAndSection" disabled="true"></s:checkboxlist>
				</div>
			</div>
			</s:if>
			<div class="form-group">
				<label class="conLable col-md-6 control-label">
					Do you need to check following conditions before promoting students :
				</label>
				<div class="col-md-12">
					<s:checkboxlist list="teachingRoleMap" name="chkBoxSelectedIds" theme="ems"
						onclick="javascript:generateExamTypes(this);" />
						<span class="help-block">(Select above check boxes which will
					be applied while promoting the students.)</span>
				</div>
			</div>
			<s:if test="%{examTypeList != null && !examTypeList.isEmpty()}">
		<div class="form-group" id="examTypesCont" style="display: none;">
				<label class="conLable col-md-2 control-label">
					Select Exam Types :
				</label>
				<div class="col-md-12">
					<div class="checkbox-list">
						<s:checkboxlist list="examTypeList" name="tempList" listKey="id" theme="ems"
							listValue="examType" cssClass="checkbox small examTypes"></s:checkboxlist>
						<span class="help-block col-md-12">(Only selected exam
							types marks should be validated.)</span>
					</div>
				</div>
			</div>
		</s:if>
		<div class="form-group">
			<label class="conLable col-md-3 control-label">Do you need to view all students : </label>
			<div class="col-md-9" style="padding-top: 7px;">
				<input type="checkbox" name="title" class="checkbox">
				</div>
			<span class="help-block col-md-10">(Select above check box to show all students in the next page.  Otherwise it will show only non promoted students in the next page.)</span>
		</div>
		<div class="form-actions fluid">
			<div class="col-md-offset-3 col-md-4">
				<sj:submit   targets="promoteStudentsContent" value="Next" 
					onBeforeTopics="promoteStudFormValidationDetails1"  indicator="indicator"
					cssClass="submitBt btn blue small" formIds="promoteStudentsForm"
					 validate="true" />
				<s:url id="urlMngPromoteStuds" action="ajaxManagePromoteStudents"
					includeParams="all" escapeAmp="false" namespace="/admin">
				</s:url>
				<sj:a href="%{urlMngPromoteStuds}" cssClass="btn default"
					targets="mainContentDiv">Cancel</sj:a>
			</div>
	</div>
	</div>
	</s:if>
	<s:else>
		<div class="alert alert-info">
			Please add promotion class settings.
		</div>	
	</s:else>
	
	<s:if test="%{tempString == 'allPramoted'}">
		<li id="academicSettingsDiv">
			<s:url id="urlSchoolSettings"
				action="ajaxAcademicSchoolSettings" namespace="/admin">
				<s:param value='<s:property value="#session.academicYear" />'
					name="academicYearId">
				</s:param>
			</s:url>
			<sj:a id="urlSchoolSettings" href="%{urlSchoolSettings}"
				targets="mainContentDiv" cssClass="ajaxify AAP">
				Academic Planner</sj:a>
		</li>
	</s:if>
	
</s:form>
<script type="text/javascript">
	$(document).ready(function() {
		
	$.destroyTopic('promoteStudFormValidationDetails1');
	 $("input:checkbox, input:radio").uniform();
		$('input[name=tempList]').click(function(){
			if ($("input[name=tempList]:checked").length == 0) 
				$('input[name="chkBoxSelectedIds"][value="ES"]').attr("checked",false);
			else
				$('input[name="chkBoxSelectedIds"][value="ES"]').attr("checked",true);
		});
		$("input[name=objectList]").click(function() {
			if ($("input[name=objectList]:unchecked").length > 0) {
			   $(".promoteClasses").parent('span').removeClass("checked");
				$(".promoteClasses").attr("checked", false);
			} else {
			    $(".promoteClasses").parent('span').addClass("checked");
				$(".promoteClasses").attr("checked", true);
			}
		});
		$.subscribe('promoteStudFormValidationDetails1', function(event, data) {
				if($('input[name="chkBoxSelectedIds"][value="ES"]').is(':checked')){
					if(($("input[name=tempList]:checked").length) > 0){
						   var examTypeIds = $("input[name=tempList]:checked");
			               var selectedExamTypeIds = '';
		                       selectedExamTypeIds = '(';
		                       for ( var i = 0; i < examTypeIds.length; i++) {
		                               selectedExamTypeIds += examTypeIds[i].value + ', ';
		                       }
		                       selectedExamTypeIds += '0)';
			               $("#examTypesIds").val(selectedExamTypeIds);
					}
				}
				if(($("input[name=objectList]:checked").length) > 0){
				   var classIds = $("input[name=objectList]:checked");
	               var selectedClassIds = '';
	               if (classIds.length > 0) {
	                       selectedClassIds = '(';
	                       for ( var i = 0; i < classIds.length; i++) {
	                               selectedClassIds += classIds[i].value + ', ';
	                       }
	                       selectedClassIds += '0)';
	               }
	               $("#classIds").val(selectedClassIds);
				   return true;								
				}
				 else{
				 		alert("Please Select at least One Class");
						event.originalEvent.options.submit=false;
				 	}
		});
	});
	
	var allPromotted ='<s:property value="tempString" />';
	if(isNonEmpty(allPromotted)){
		if(confirm('Student promotion process completed. Do you want to switch to future academic year ?')){
			$("li#academicSettingsDiv a").click();
		}
	}
	function generateExamTypes(eve){
	if(eve.value == 'ES'){
			if ($(eve).is(':checked')){
			$('#examTypesCont').show();
		    $("[name='tempList']").each(function(){
			   $(this).parent('span').addClass('checked');
			    $(this).attr("checked", "true");
			});
		}else{
				 $("[name='tempList']").each(function(){
			   $(this).parent('span').removeClass('checked');
			    $(this).removeAttr("checked");
			    
			});
			$('#examTypesCont').hide();
		}		
		}		
	}
	function checkAll() {
		if ($(".promoteClasses").is(':checked')){
		    $("[name='objectList']").each(function(){
			   $(this).parent('span').addClass('checked');
			    $(this).attr("checked", "true");
			});
		}
		else{
		 $("[name='objectList']").each(function(){
			   $(this).parent('span').removeClass('checked');
			    $(this).removeAttr("checked");
			});
		}	
	}
</script>
