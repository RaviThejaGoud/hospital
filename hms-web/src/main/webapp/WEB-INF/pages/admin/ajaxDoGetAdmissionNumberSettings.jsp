<%@ include file="/common/taglibs.jsp"%>
<s:if test='%{admissionSettings != null && !admissionSettings == ""}'>

<%@ include file="/common/messages.jsp"%>

<s:hidden name="admissionSettings.id"></s:hidden>

<div class="form-body">
	<div class="row">
		<div class="col-md-9">
			<div class="form-group">
				<label class="control-label col-md-5">
					Generate admission number sequentially : 
				</label>
				<div class="col-md-3">
					<s:if test="%{admissionSettings.atuoGenerationAdmissionNumberStatus}">
						<input type="checkbox" id="atuoGenerationAdmissionNumber" name="atuoGenerationAdmissionNumberStatus" disabled="disabled" value="true" checked="checked">
						<s:hidden  name="admissionSettings.atuoGenerationAdmissionNumberStatus"></s:hidden>
					</s:if>
					<s:else>
						<input type="checkbox" id="atuoGenerationAdmissionNumber" name="admissionSettings.atuoGenerationAdmissionNumberStatus">
					</s:else>
				</div>
			</div>
		</div>
	</div>
	<div id="schoolClassWideOptionDivId" style="display: none;">
		<div class="row">
			
				<div class="row">
					<div class="col-md-9">
						<div class="form-group">
							<label class="control-label col-md-3">
								Prefix :
							</label>
							<div class="col-md-3">
								<sj:textfield name="admissionNumberSettings.prefix" id="prefix"  cssClass="form-control input-medium" maxlength="40"></sj:textfield>
							</div>
						</div>
					</div>
				</div>
				
				<div class="row">
					<div class="col-md-9">
						<div class="form-group">
							<label class="control-label col-md-3">
								<span class="required">*</span>Sequence Number :
							</label>
							<div class="col-md-3">
								<sj:textfield name="admissionNumberSettings.sequenceNumber" id="sequenceNumber"  cssClass="required form-control input-medium numeric" maxlength="40"></sj:textfield>
							</div>
						</div>
					</div>
				</div>
				
				<div class="row">
					<div class="col-md-9">
						<div class="form-group">
							<label class="control-label col-md-3">
								Postfix :
							</label>
							<div class="col-md-3">
								<sj:textfield name="admissionNumberSettings.postfix" id="postfix"  cssClass="form-control input-medium" maxlength="40"></sj:textfield>
							</div>
						</div>
					</div>
				</div>
			
				<div class="row">
						<div class="col-md-9">
					<div class="row form-group">
						<label class="col-md-3 control-label">
							Admission Number Type : </label>
						<div class="col-md-9">
							<div class="radio-list">
							
							<s:hidden name="admissionNumberSettings.admissionNumberType" id="admissionNumberTypeId"></s:hidden>
							
							<s:if test='%{admissionNumberSettings.admissionNumberType == "SW"}'>
								<label class="radio-inline"> <input type="radio" checked="checked"
									value="SW" name="admissionNumberType" class="admissionNumberTypeClass">School Wide
								</label>
							</s:if>
							<s:else>
								<label class="radio-inline"> <input type="radio"
									value="SW" name="admissionNumberType" class="admissionNumberTypeClass">School Wide
								</label>
							</s:else>
							<s:if test='%{admissionNumberSettings.admissionNumberType == "CW"}'>
								<label class="radio-inline"> <input type="radio" checked="checked"
									value="CW" name="admissionNumberType" class="admissionNumberTypeClass">Class Wide
								</label>
							</s:if>
							<s:else>
								<label class="radio-inline"> <input type="radio"
									value="CW" name="admissionNumberType" class="admissionNumberTypeClass">Class Wide
								</label>
							</s:else>
							</div>
						</div>
					</div>
				</div>
			</div>						
		<div id="classesDivId" style="display: none;">				
			<s:if test='%{classList.size >0}'>
				<div class="form-group">
					<div class="col-md-12">
						<div class="checkbox-list">
							<label class="checkbox-inline">
									<input type="checkbox" name=""
											value="" onClick="checkAllClasses()"
											class="checkbox allClasses">
								All Classes 
							</label>
						</div>
					</div>
				</div>
			
				<div class="form-group">
					<label class="conLable col-md-3 control-label">
						<span class="required">*</span> Class With Students :
					</label>
					<div class="col-md-12">
						<div class="checkbox-list">
							<s:checkboxlist name="chkBoxSelectedIds"
								list="classList" listKey="id"
								listValue="className" theme="ems" cssClass="small required" />
						</div>
					</div>
				</div>
			</s:if>
		</div>	
			<p><b>Ex:</b> Prefix is 'SCH'  , postfix is /17-18 ,  and the sequence number is 1, then the admission number would be  SCH1/17-18</p>						
			
				<div class="form-actions fluid">
					<div class="col-md-offset-2 col-md-9">
						<sj:submit cssClass="submitBt btn blue" value="Submit" targets="academicSettingsContent" validate="true" formIds="addAdmissionNumberSettings" onBeforeTopics="autoAdmissionNumberFormValidation"  />
					</div>
				</div>	
		</div>		
	</div>	
	</div>		
	<hr/>
	
	<s:if test="%{objectList != null && !objectList.isEmpty()}">
			
			<table
				class="table table-striped table-bordered table-hover table-full-width"
				id="sample_7">
				<thead>
					<tr>
						<th style="width: 50px;">
							Class Name
						</th>
						<th>
							Prefix
						</th>
						<th>
							Sequence Number
						</th>
						<th>
							Postfix
						</th>
						<th>
							Delete
						</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="objectList">
						<tr>
							<td>
								<s:property value="className" />
							</td>
							<td>
								<s:property value="prefix" />
							</td>
							<td>
								<s:property value="sequenceNumber" />
							</td>
							<td>
								<s:property value="postfix" />
							</td>
							<td>
								<s:if test='%{#session.previousYear=="N"}'>
									<s:if test='%{!studentsAvailable}'>
										<s:url id="deleteSchoolHolidays"
											action="ajaxDeleteAutoAcademicNumberClass" escapeAmp="false"
											includeParams="all" namespace="/admin">
											<s:param name="classNameAutoAcademicNumberVO.admissionNumberSettingsId" value="%{admissionNumberSettingsId}"></s:param>
											<s:param name="classNameAutoAcademicNumberVO.classId" value="%{classId}"></s:param>
											<s:param name="classNameAutoAcademicNumberVO.admissionSettingId" value="%{admissionSettingId}"></s:param>
											<s:param name="classNameAutoAcademicNumberVO.academicYearId" value="%{academicYearId}"></s:param>
										</s:url>
										<s:div id='%{deleteSchoolHolidays}'
											cssClass="btn btn-xs red" title="Delete this Setting"
											onclick="javascript:confirmDialogWithTarget(this,'academicSettingsContent');">
											<i class="fa fa-times"></i>Delete</s:div>
									</s:if>
									<s:else>
										--
									</s:else>
								</s:if>
						</td>
						</tr>
					</s:iterator>
				</tbody>
			</table>
			
		</s:if>
		<%-- <s:else>
			<div class="alert alert-info">
				Oops! Currently there are no academic number settings.
			</div>
		</s:else> --%>
		
		
		
</s:if>
<s:else>
	<div class="alert alert-info">
		Currently there are no admission settings for selected academic year.
	</div>
</s:else>
<script type="text/javascript">

$(document).ready(function() {
	TableAdvanced.init();
	FormAdvanced.init();
	$('.numeric').numeric();
	$("input:checkbox, input:radio").uniform();
	
	var admissionNumberTypeVal = '<s:property value="admissionNumberSettings.admissionNumberType"/>';
	if(isNonEmpty(admissionNumberTypeVal))
	{
		$('.admissionNumberTypeClass').attr('disabled', 'disabled');
		if("SW" == admissionNumberTypeVal){
			$("#classesDivId").hide();
			$('.submitBt').attr('disabled', 'disabled');
		}else{
			$("#classesDivId").show();
		}
	}
	
});
$.destroyTopic('autoAdmissionNumberFormValidation');
$.subscribe('autoAdmissionNumberFormValidation',function(event, data) {
	
	var admissionNumberType = $('#admissionNumberTypeId').val();
	
	if(isNonEmpty(admissionNumberType))
	{
		if("CW" == admissionNumberType)
		{
			if ($('input[name=chkBoxSelectedIds]:checked').length <= 0) {
				alert('Please select at least one class.');
				event.originalEvent.options.submit=false;
			}
		}
	}
	else
	{
		alert('Please select Admission Number Type.');
		event.originalEvent.options.submit=false;
	}
	
});
var atuoGenerationAdmissionNumberStatus = '<s:property value="admissionSettings.atuoGenerationAdmissionNumberStatus" />';
if(isNonEmpty(atuoGenerationAdmissionNumberStatus)){
	if(atuoGenerationAdmissionNumberStatus == 'true' || atuoGenerationAdmissionNumberStatus == 'Y'){
		$('#atuoGenerationAdmissionNumber').attr('checked','checked');
		$('#atuoGenerationAdmissionNumber').parent('span').addClass('checked');
		$('#atuoGenerationAdmissionNumber').val('true');
		
		$('#schoolClassWideOptionDivId').show();
	}else{
		$('#atuoGenerationAdmissionNumber').removeAttr('checked');
		$('#atuoGenerationAdmissionNumber').val('false');
		$('#schoolClassWideOptionDivId').hide();
	}	
}

$('#atuoGenerationAdmissionNumber').click(function()
{
	if ($(this).is(':checked')) 
    {
		$('#schoolClassWideOptionDivId').show();
    }
	else
	{
		$('#schoolClassWideOptionDivId').hide();
	}
});


function checkAllClasses() {
	if ($(".allClasses").is(':checked')){
	    $("[name='chkBoxSelectedIds']").each(function(){
		   $(this).parent('span').addClass('checked');
		    $(this).attr("checked", "true");
		});
	}
	else{
	 $("[name='chkBoxSelectedIds']").each(function(){
		   $(this).parent('span').removeClass('checked');
		    $(this).removeAttr("checked");
		});
	}	
}


$("div input#atuoGenerationAdmissionNumber").click(function() {
	
	if($(this).is(':checked')){
		$(this).val('true');
	}else{
		$(this).val('false');
	}				
});



$(".admissionNumberTypeClass").click(function() {
	
	var admissionNumberType = $('input:radio[name=admissionNumberType]:checked').val();
	$('#admissionNumberTypeId').val(admissionNumberType);
	if("SW" == admissionNumberType){
		$("#classesDivId").hide();
	}else{
		$("#classesDivId").show();
	}			
});


</script>