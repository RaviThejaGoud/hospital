<%@ include file="/common/taglibs.jsp"%>
<div class="spaceDiv">&nbsp;</div>
<span id="feePriorityIds" class='<s:property value="tempString"/>'> </span>
<s:if test='%{anyId == "admissionParticulars"}'>
<jsp:include page="/common/messages.jsp"></jsp:include>
	<s:if test="%{feeTypeList != null && !feeTypeList.isEmpty()}">
		<div id="feeTypesCont">
			<table id="sample_2"
				class="table table-striped table-bordered table-hover table-full-width dataTable">
				<thead>
					<tr>
						<th>
							Fee Particulars 
						</th>
						<s:if test='%{customer.committedFeeStatus == "Y"}'>
							<th>Is committed fee applicable</th>
							<th>Priority</th>
						</s:if>
						<th>
							Delete
						</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="feeTypeList">
						<tr  class="admissionParticularsData">
							<td id="<s:property value='id'/>" class="particularId">
								<span id='<s:property value="id"/>' class="feeTypeId"></span>
								<s:if test='%{feeTypeName == "Tuition Fee" || feeTypeName == "Transport Fee" || feeTypeName == "Hostel Fee"}'>
									<sj:textfield name="feeTypeName" id="feeType_%{id}"
										disabled="true"
										cssClass="form-control required feeParticularName"></sj:textfield>
								</s:if>
								<s:else>
									<sj:textfield name="feeTypeName" id="feeType_%{id}"
										cssClass="form-control  required feeParticularName"></sj:textfield>
								</s:else>
							</td>
							<s:if test='%{customer.committedFeeStatus == "Y"}'>
								<s:if test='%{committedFeeStatus == "Y"}'>
									<s:if test="%{tempBoolean==false}">
										<td>
											<span id='<s:property value="id"/>' class="feeType"></span>
											<input name="committedFeeStatus" value="Y"  type="checkbox" id='<s:property value="id"/>' class="committedStatus" checked="checked" disabled="disabled"></input>
											<!-- <input name="feeType.committedFeeStatus" value='<s:property value="feeType.committedFeeStatus"/>' onclick="enablePriority()" type="checkbox" id="committedFeeStatus%{id}" ></input> -->
										</td>
										<td>
											<sj:textfield name="priorityPosition" id="priorityValue%{id}" cssClass="numeric form-control input-medium as-input priorityVal" maxlength="1" readonly="true" data-id="%{priorityPosition}"></sj:textfield>
										</td>
									</s:if>
									<s:else>
										<td>
											<span id='<s:property value="id"/>' class="feeType"></span>
											<input name="committedFeeStatus" value="Y"  type="checkbox" id='<s:property value="id"/>' class="committedStatus" checked="checked"></input>
											<!-- <input name="feeType.committedFeeStatus" value='<s:property value="feeType.committedFeeStatus"/>' onclick="enablePriority()" type="checkbox" id="committedFeeStatus%{id}" ></input> -->
										</td>
										<td>
											<sj:textfield name="priorityPosition" id="priorityValue%{id}" cssClass="numeric form-control input-medium as-input priorityVal" maxlength="1" data-id="%{priorityPosition}"></sj:textfield>
										</td>
									</s:else>
								</s:if>
								<s:else>
									<td>
										<span id='<s:property value="id"/>' class="feeType"></span>
										<input name="committedFeeStatus" value="Y"  type="checkbox" id='<s:property value="id"/>' class="committedStatus"></input>
									</td>
									<td>
										<sj:textfield name="priorityPosition" id="priorityValue%{id}" cssClass="numeric form-control input-medium as-input priorityVal" maxlength="1" readonly="true" data-id="%{priorityPosition}"></sj:textfield>
									</td>
								</s:else>
							</s:if>
							<td>
								<s:if test='%{feeTypeName == "Tuition Fee" ||  feeTypeName == "Transport Fee" || feeTypeName == "Hostel Fee"}'>
									<div class="grid_1"></div>
								</s:if>
								<s:else>
									<div class="grid_1">
										<s:url id="removeFeeParticular"
											action="ajaxRemoveFeeParticular" includeParams="all"
											escapeAmp="false" namespace="/schoolfee">
											<s:param name="feeType.id" value="%{id}" />
											<s:param name="targetString">creatAdmissionParticulars</s:param>
											<s:param name="anyId" value="%{anyId}"/>
										</s:url>
										<s:div cssClass="btn btn-xs red"
											onclick="javascript:confirmDialogWithTarget(this,'allFeeSettingParticular');"
											id='%{removeFeeParticular}' theme="simple"
											title="Delete this particular">
											<i class="fa fa-times"></i> Delete
										</s:div>
									</div>
								</s:else>
							</td>
						</tr>
					</s:iterator>
					<table class="table table-striped table-bordered table-hover table-full-width dataTable" id="feeTypesCont">
					</table>
				</tbody>
			</table>
		</div>
		<div class="">
			<a href="javascript:void(0)"
				onclick="javascript:showAdmissionsParticularsCreationForm();"
				class="btn green"><i class="fa fa-plus"></i> <b>Add
					Particulars</b> </a>
		</div>
		<div class="spaceDiv">
			&nbsp;
		</div>
		<div class="form-actions fluid">
			<div class="col-md-6">
				<div class="col-md-offset-3 col-md-9">
					<sj:submit   targets="admissionSettingsDiv" value="Next"
						cssClass="submitBt btn blue"
						formIds="addAdmissionFeeParticularsFM" validate="true"
						onBeforeTopics="addAdmissionsFeeParticularValidation" />
					<s:url id="doAddAdmissionsCategoriesLink"
						action="ajaxGetSettingsSteps" includeParams="all"
						escapeAmp="false">
					</s:url>
					<sj:a href="%{doAddAdmissionsCategoriesLink}"
						cssClass="btn default" targets="admissionSettingsDiv">
						<i class="fa fa-mail-reply"></i> Back</sj:a>
				</div>
			</div>
		</div>
	</s:if>
	<s:else>
		<table class="table table-striped table-bordered table-hover table-full-width dataTable" id="feeTypesCont">
		</table>
		<div class="">
			<a href="javascript:void(0)"
				onclick="javascript:showAdmissionsParticularsCreationForm();"
				class="btn green"><i class="fa fa-plus"></i> <b>Add
					Particulars</b> </a>
		</div>
		<div class="spaceDiv">
			&nbsp;
		</div>
		<div class="form-actions fluid">
			<div class="col-md-6">
				<div class="col-md-offset-3 col-md-9">
					
					<sj:submit   targets="admissionSettingsDiv" value="Next"
						cssClass="submitBt btn blue"
						formIds="addAdmissionFeeParticularsFM" validate="true" indicator="indicator"
						onBeforeTopics="addAdmissionsFeeParticularValidation" />
					<s:url id="doAddAdmissionsCategoriesLink"
						action="ajaxGetSettingsSteps" includeParams="all"
						escapeAmp="false">
					</s:url>
					<sj:a href="%{doAddAdmissionsCategoriesLink}"
						cssClass="btn default" targets="admissionSettingsDiv">Back</sj:a>
				</div>
			</div>
			<div class="spaceDiv">
				&nbsp;
			</div>
		</div>
		<div class="alert alert-info">
			Fee particulars are not created. You can able to create particulars
			through + link.
		</div>
	</s:else>
</s:if>
<s:else>
	<s:if test="%{feeTypeList != null && !feeTypeList.isEmpty()}">
		<div class="spacediv"></div>
		<table
			class="table table-striped table-bordered table-hover table-full-width"
			id="sample_4">
			<thead>
				<tr>
					<th>
						Fee Particulars
					</th>
					<th>
						Priority
					</th>
					<th>
						Edit
					</th>
					<th>
						Delete
					</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="feeTypeList">
					<tr>
						<td>
							<s:property value="feeType" />
						</td>
						<td>
							<s:if test='%{priorityPosition >0}'>
								<s:property value="priorityPosition" />
							</s:if>
						</td>
							<td>
							<s:if test='%{feeType == "Tuition Fee" ||  feeType == "Transport Fee" || feeType == "Hostel Fee"}'>
								-
							</s:if>
							<s:else>
								<s:if test='%{#session.previousYear == "N"}'>
									<a data-toggle="modal" href="#feeParticularDiv"
										class="btn btn-xs purple"
										onclick="javascript:PopupFeeParticularsDetials(<s:property value="%{id}" />,<s:property value="%{academicYearId}" />);"><i
										class="fa fa-edit"></i>Edit </a>
								</s:if>
								<s:else>
									-
								</s:else>
								</s:else>
							</td>
							<td>
								<s:if test='%{feeType == "Tuition Fee" ||  feeType == "Transport Fee" || feeType == "Hostel Fee"}'>
								-
							</s:if>
							<s:else>
								<s:if test='%{#session.previousYear == "N"}'>
									<s:url id="removeFeeParticular"
										action="ajaxRemoveFeeParticular" includeParams="all"
										escapeAmp="false">
										<s:param name="feeType.id" value="%{id}" />
									</s:url>
									<s:div cssClass="btn btn-xs red" onclick="javascript:confirmDialogWithTarget(this,'feeSettingsContent');"
										id='%{removeFeeParticular}' theme="simple"
										title="Delete this particular">
										<i class="fa fa-times"></i>Delete</s:div>
								</s:if>
								<s:else>
									-
								</s:else>
								</s:else>
							</td>
					</tr>
				</s:iterator>
			</tbody>
		</table>
	</s:if>
	<s:else>
		<div class="alert alert-info">
			Fee particulars are not defined.
		</div>
	</s:else>
</s:else>
<div id="feeParticularDiv"></div>
<script type="text/javascript">
$(document).ready(function() {
	TableAdvanced.init();
	UIExtendedModals.init();
	$("input:checkbox, input:radio:not('.toggle')").uniform();
	$.subscribe('addAdmissionsFeeParticularValidation', function(event, data) {
		var particularId = 0;
		var particularName = '';
		var committedStatus ='';
		var priority = 0;
		var jsonObj = [];
		var returnType = true;
		if($('#addAdmissionFeeParticularsFM').valid()){
			$('tr.admissionParticularsData').each(function() {
				//if(!$(this).find('.feeParticularName').is(":disabled")){
					particularId = $(this).find("span.feeTypeId").attr("id");
					particularName = $(this).find('.feeParticularName').val();
					if(!isNonEmpty($(this).find('.committedStatus').prop("checked")) || $(this).find('.committedStatus').prop("checked")==false){
						committedStatus =  "N";
					}else{
						committedStatus =  "Y";
						if(isNonEmpty($(this).find('.priorityVal').val()))
							priority = $(this).find('.priorityVal').val();
						if(priority == 0){
							alert(particularName+" Priority should not be 0 .");
							returnType = false;
						}
					}
					if(isNonEmpty(particularId)){
						jsonObj.push( {
							"particularName" : particularName,
							"particularId" : particularId,
							"committedStatus" : committedStatus,
							"priority" : priority
						});
							
					}
				//}
				priority=0;
				committedStatus='';
			});
		//alert(JSON.stringify(jsonObj));
		if(returnType == true){
			$('.admissionsParticularsData').val(JSON.stringify(jsonObj));
			event.originalEvent.options.submit=true;
		}else
			event.originalEvent.options.submit=false;
		}else
			event.originalEvent.options.submit=false;
	});
});
var rowCount =1;
function showAdmissionsParticularsCreationForm(){
	$("table#feeTypesCont").append('<tr class="admissionParticularsData" id="admiss'+rowCount+'"><td class="particularId" id="0"><span id="0" class="feeTypeId"></span>'+
			'<label class="control-label"><span class="required">*</span>Fee Particulars</label>'+
			'<input type="text" class="form-control input-medium required feeParticularName" id="feeType'+rowCount+'" name="feeTypeName"/></td>'+
			'</td><td><a title="Delete" class="btn btn-xs red" id="removeParicular" onclick="removeParticularRow('+rowCount+')"><i class="fa fa-times"></i> Delete</a></td></tr>');
		rowCount++;
}

function removeParticularRow(rowNum){
	$('#admiss'+rowNum).remove();
}
function PopupFeeParticularsDetials(id, academicYearId) {
	var pars = "feeType.id=" + id + "&academicYearId=" + academicYearId;
	var url = jQuery.url.getChatURL("/schoolfee/ajaxDoCreateParticular.do");
	$('#feeParticularDiv').html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
	$.ajax( {
		url : url,
		cache : false,
		data : pars,
		success : function(html) {
			$("#feeParticularDiv").html(html);
		}
	});
}

$(".committedStatus").click(function(){
	feeTypeId = $(this).attr('id');
	if($(this).prop("checked") == false){
		$("#priorityValue"+feeTypeId).attr('readonly', 'true')
		$("#priorityValue"+feeTypeId).val(0);
	}
	else if($(this).prop("checked") == true){
		$("#priorityValue"+feeTypeId).removeAttr('readonly');
		if($("#priorityValue"+feeTypeId) ==0)
			$("#priorityValue"+feeTypeId).attr("required");
	}
});

$(".priorityVal").keyup(function(){
	var prorityIds = [];
	var feePriorityIds = [];
	var currentPriority = $(this).val();
	var curValue = $(this).attr("data-id");
	var existingIds = $('#feePriorityIds').attr('class').replace("[","").replace("]","");
	var curentId = $(this).attr('id');
	var existingPriorityLength = existingIds.split(",").length;
	var existingPriority = existingIds.split(",");
	
	if (Number(existingPriorityLength) > 0) {
		for ( var i = 0; i < Number(existingPriorityLength); i++) {
			if(Number(curValue) != Number(existingPriority[i])){
				if(Number(currentPriority) == Number(existingPriority[i])){
					alert("Already priority defined. Please enter another priority.")
					$("#"+$(this).attr('id')).val(curValue);
				}
				feePriorityIds.push(Number(existingPriority[i]));
			}
		}
	}
	
	if($('#addAdmissionFeeParticularsFM').valid()){
		$('tr.admissionParticularsData').each(function() {
			if(isNonEmpty($(this).find('.priorityVal').val()))
				priority = $(this).find('.priorityVal').val();
			if(priority>0){
				if(curentId != $(this).find('.priorityVal').attr('id')){
					prorityIds.push(priority);
				}
			}
		});
	}
	var prorityIdsLength = prorityIds.length;
	if (Number(prorityIdsLength) > 0) {
		for ( var i = 0; i < Number(prorityIdsLength); i++) {
			if(Number(curValue) != Number(prorityIds[i])){
				if(Number(currentPriority) == Number(prorityIds[i])){
					alert("Already priority defined. Please enter another priority.");
					$("#"+$(this).attr('id')).val(curValue);
				}
			}
		}
	}
});
</script>

