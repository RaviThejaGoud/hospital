<%@ include file="/common/taglibs.jsp"%>
<div id="deleteClassDetailsDiv">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>

<div role="progressbar" class="progress progress-striped" id="bar">
	<div class="progress-bar progress-bar-success" style="width: 32%;">
	</div>
</div>
	<%@ include file="/common/messages.jsp"%>
	<div id="addClassAndSectionDetails">
		<s:form id="addAdmissionClassDetailsFM"
			action="ajaxAddAdmissionClassDetails" theme="simple" name="myform" cssClass="form-horizontal" namespace="/admin">
			<s:hidden name="tempString" cssClass="admissionsData"></s:hidden>
					<h4 class="pageTitle bold">
						Class Details
					</h4>
				<div class="panel-body col-md-12">
					<div class="col-md-1"><span class="label label-danger"> NOTE: </span></div>
					<div class="col-md-10">
						<ul>
							<li>
								Below table contains the information about available seats in
								all classes for a selected academic year.
							</li>
							<li>
								 We can increase the class capacity from "Extend Class capacity
								column".
							</li>
						</ul>
						</div>
					</div>
				
				<s:if test="%{classNameList != null && !classNameList.isEmpty()}">
					<table
						class="table table-striped table-bordered table-hover table-full-width"
						id="sample_2">
						<thead>
							<tr>
								<th>
									Class Name
								</th>
								<th>
									Capacity
								</th>
								<th>
									Available Seats
								</th>
								<th>
									Extendable Class Capacity
								</th>
								<th>
									Admissions Open
								</th>
								<th>
									Delete
								</th>
							</tr>
						</thead>
						<tbody>
							<s:iterator value="classNameList">
								<tr class="classAdmissionsData" >
									<td class='classId' id='<s:property value='id'/>'>
										<s:property value="className" />
									</td>
									<td>
										<s:property value="absentiesCount" />
									</td>
									<td>
										<s:if test="%{(absentiesCount) - (noOfStudents) > 0}">
											<s:property value="%{(absentiesCount) - (noOfStudents)}" />
										</s:if>
										<s:else>
										  0 
										</s:else>
									</td>
									<td>
										<sj:textfield name="extendableClassCapacity"
											id="extendCapacity_%{id}"
											cssClass="form-control input-small extendableClassCapacity numeric"
											maxlength="4" cssStyle="width:35px;"></sj:textfield>
									</td>
									<td>
										<s:checkbox cssClass="checkbox admissionOpenStatus"
											theme="simple" id="admissionOpenStatus_%{id}"
											title="Admissions open for this class" name="admissionsOpen"></s:checkbox>
									</td>
									<td>
										<s:url id="removeAdmissionClassSection"
											action="ajaxRemoveClassesByClassNameId" includeParams="all"
											escapeAmp="false" namespace="/admin">
											<s:param name="classId" value="%{id}" />
										</s:url>
										<s:div cssClass="btn btn-xs red"
											onclick="javascript:confirmDialogWithTarget(this,'deleteClassDetailsDiv');"
											id='%{removeAdmissionClassSection}' theme="simple"
											title="Delete this class">
											<i class="fa fa-times"></i> Delete
										</s:div>


									</td>
								</tr>
							</s:iterator>
							<table class="table table-striped table-bordered table-hover table-full-width dataTable" id="admissionsClassSectionsCont">
							</table>
						</tbody>
					</table>
					<div class="col-md-12">
						<a href="javascript:void(0)"
							onclick="javascript:showAdmissionsClassCreationForm();"
							class="btn green "><i class="fa fa-plus"></i><b>Add Class</b>
						</a>
					</div>
					<div class="spaceDiv">&nbsp;</div>
				<div class="form-actions fluid">
					<div class="col-md-offset-2 col-md-9">
						<sj:submit   targets="admissionSettingsDiv" value="Next"
							cssClass="submitBt btn blue" formIds="addAdmissionClassDetailsFM" validate="true"
							onBeforeTopics="addclassAdmissionValidation" indicator="indicator"/>
						<s:url id="doAddAdmissionsBackLink"
							action="ajaxDoAddAdmissionSettings" includeParams="all"
							escapeAmp="false" namespace="/admin">
							<s:param name="admissionSettings.id"
								value="%{#session.admissionSettingId}"></s:param>
						</s:url>
						<sj:a href="%{doAddAdmissionsBackLink}" cssClass="btn default"
							targets="admissionSettingsDiv"> <i class="fa fa-mail-reply"></i> Back</sj:a>
					</div>
				</div>
			</s:if>
			<s:else>
			<table class="table table-striped table-bordered table-hover table-full-width dataTable" id="admissionsClassSectionsCont">
			</table>
			<div>
				<a href="javascript:void(0)"
					onclick="javascript:showAdmissionsClassCreationForm();"
					class="btn green"><i class="fa fa-plus"></i> <b>Add Class</b> </a>
			</div>
			<div class="spaceDiv">&nbsp;</div>
			<div class="form-actions fluid">
				<div class="col-md-offset-2 col-md-9">
					<sj:submit   targets="admissionSettingsDiv" value="Next"
						cssClass="submitBt btn blue" formIds="addAdmissionClassDetailsFM" validate="true"
						onBeforeTopics="addclassAdmissionValidation" indicator="indicator"/>
					<s:url id="doAddAdmissionsBackLink"
						action="ajaxDoAddAdmissionSettings" includeParams="all"
						escapeAmp="false" namespace="/admin">
						<s:param name="admissionSettings.id"
							value="%{#session.admissionSettingId}"></s:param>
					</s:url>
					<sj:a href="%{doAddAdmissionsBackLink}" cssClass="btn default"
						targets="admissionSettingsDiv">Back</sj:a>
				</div>
			</div>
			<div class="spaceDiv">&nbsp;</div>
			<div class="alert alert-info">
					You have not created any Classes. You can able to create Classes
					through + link.
			</div>
		</s:else>
		
	</s:form>
	</div>
	</div>
<script type="text/javascript">
$(document).ready(function() {
TableAdvanced.init();
	$('.numeric').numeric();
	$('div#admissionSettingDiv ul > li').removeClass('active');
	$('div#admissionSettingDiv ul > li#AdmissionSettings2').nextAll().removeClass('active');
	$('div#admissionSettingDiv ul > li#AdmissionSettings2').prevAll().addClass('done');	
	$("li#AdmissionSettings2").addClass('active');
	$("input:checkbox, input:radio").uniform();
    $.destroyTopic('addclassAdmissionValidation');
	$.subscribe('addRequiredFields', function(event, data) {
		$('#className').addClass("required");
		$('#noOfStudents').addClass("required");
	});
	$.subscribe('addclassAdmissionValidation', function(event, data) {
		var classId = 0;
		var extendableClassCapacity = '';
		var admissionsOpen = '';
		var className = '';
		var sectionNames = '';
		var jsonObj = [];
			if(($("[name='admissionsOpen']:checked").length) <= 0)
			{
				alert("Please select at least one class.");
				event.originalEvent.options.submit=false;
			}
			$('tr.classAdmissionsData').each(
			function() {
				classId = $(this).find("td.classId").attr("id");
				extendableClassCapacity = $(this).find(".extendableClassCapacity").val();
				admissionsOpen = $(this).find(".admissionOpenStatus").attr("checked");
				if(isNonEmpty(classId)){
						if(classId == 0){
							className = $(this).find('.admissionsClassName').val();
							sectionNames =$(this).find('.admissionSectionNames').val();
							jsonObj.push( {
								"className" : className,
								"sectionNames" : sectionNames,
								"classId" : classId,
								"extendableClassCapacity" : extendableClassCapacity,
								"admissionsOpen" : admissionsOpen
							});
						}else{
							jsonObj.push( {
								"className" : '',
								"sectionNames" : '',
								"classId" : classId,
								"extendableClassCapacity" : extendableClassCapacity,
								"admissionsOpen" : admissionsOpen
							});
						}
						
				}
			});
		$('.admissionsData').val(JSON.stringify(jsonObj));
			return true;
	});
});

var rowCount =1;
function showAdmissionsClassCreationForm() {
		$("table#admissionsClassSectionsCont").append('<tr class="classAdmissionsData" id="admiss'+rowCount+'"><td class="classId" id="0">'+
			'<label class="control-label"><span class="required">*</span>Class Name</label>'+
			'<input type="text" maxlength="45" class="form-control input-small required admissionsClassName" id="admisisonsClass'+rowCount+'" name="admissionsClassName'+rowCount+'"/></td>'+
			'<td><label>Section Name(s)</label><input type="text" class="form-control admissionSectionNames" id="admisisonsClassSections" name="admissionsClassSections"/>'+
			'<span class="hintMessage">Multiple section names should be seperated by comma.</span></td><td>'+
			'<label class="control-label"><span class="required">*</span>Class Capacity</label><input type="text" maxlength="4" style="width:35px; float:none" class="form-control input-small required numeric extendableClassCapacity" id="admisisonsClassCapacity'+rowCount+'" name="admissionsClassCapacity'+rowCount+'"/>'+
			'</td><td><label>Admissions Open</label><input type="checkbox" title="Admissions open for this class" class="checkbox admissionOpenStatus" id="admissionOpenStatus'+rowCount+'" name="admissionsOpen"/>'+
			'</td><td><a title="Delete" class="btn btn-xs red" id="removeClass" onclick="removeClassRow('+rowCount+')"><i class="fa fa-times"></i> Delete</a></td></tr>');
	
	/*$("table#admissionsClassSectionsCont").append('<span class="classAdmissionsData"><span class="classId" id="0"></span><div id="results" class="col-md-12">'+
										'<div class="col-md-12 note note-info">'+
										'<div class="col-md-2"><label class="control-label"><span class="required">*</span>Class Name</label><input type="text" maxlength="45" class="form-control input-small required admissionsClassName" id="admisisonsClass'+rowCount+'" name="admissionsClassName'+rowCount+'"/></div>'+
										'<div class="col-md-5"><label>Section Name(s)</label><input type="text" class="form-control admissionSectionNames" id="admisisonsClassSections" name="admissionsClassSections"/>'+
										'<span class="hintMessage">Multiple section names should be seperated by comma.</span></div>'+
										'<div class="col-md-2"><label class="control-label"><span class="required">*</span>Class Capacity</label><input type="text" maxlength="4" style="width:35px; float:none" class="form-control input-small required numeric extendableClassCapacity" id="admisisonsClassCapacity'+rowCount+'" name="admissionsClassCapacity'+rowCount+'"/></div>'+
										'<div class="col-md-2"><label>Admissions Open</label><input type="checkbox" title="Admissions open for this class" class="checkbox admissionOpenStatus" id="admissionOpenStatus'+rowCount+'" name="admissionsOpen"/></div>'+
										'<div class="col-md-1"><div class="col-md-1">&nbsp;</div><a title="Delete" class="btn btn-xs red" id="removeClass" onclick="removeClassRow(this)"><i class="fa fa-times"></i> Delete</a></div>'+
										'</div>'+
										'</div></span>');*/
										rowCount++;
			$('.numeric').numeric();
			$(".extendableClassCapacity").change(function(){
				var extendableClassCapacity=($(this).val());
				  if (Math.floor(extendableClassCapacity) != extendableClassCapacity) {
			      	alert("Please enter numbers.");
			      	 $("input#"+$(this).attr('id')).val('');
			     	event.originalEvent.options.submit=false;
			     }
			});
	}
	function removeClassRow(rowNum){
		$('#admiss'+rowNum).remove();
	}
/*this Script is used is chrome*/
$(".extendableClassCapacity").change(function(){
	var extendableClassCapacity=($(this).val());
	  if (Math.floor(extendableClassCapacity) != extendableClassCapacity) {
      	alert("Please enter numbers.");
      	 $("input#"+$(this).attr('id')).val('');
     	event.originalEvent.options.submit=false;
     }
});
</script>

