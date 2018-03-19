<%@ include file="/common/taglibs.jsp"%>
<s:if test="%{feeType.id != 0}">
	<div data-width="760" class="modal fade modal-overflow in"
		id="feeParticularDiv"
		style="display: block; width: 780px; margin-left: -379px; margin-top: 200px;"
		aria-hidden="false">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true"></button>
			<h4 class="modal-title">Update fee particulars</h4>
		</div>
		<div class="modal-body">
</s:if>
<s:form action="ajaxAddSchoolFeeSettings" id="addFeeParticular"
	method="post" cssClass="form-horizontal" theme="simple">
	<s:hidden name="feeType.id" value="%{feeType.id}" id="feeTypeId"></s:hidden>
	<s:hidden name="tempString"></s:hidden>
	<s:hidden name="feeType.committedFeeStatus" id="status"></s:hidden>
	<div class="form-body">
		<h4 class="bold pageTitle">
			<s:if test="%{feeType == null}">
			Add fee particulars
			<hr />
			</s:if>
		</h4>
		<div class="panel-body col-md-12">
			<div class="col-md-1">
				<span class="label label-danger"> NOTE : </span>
			</div>
			<div class="col-md-10">
				<ul>
					<li>Please select fee setting type.</li>
					<li>Enter the fee particular name then hit submit.</li>
					<li>If you assign class fee to any particular. You can not change fee setting  type.</li>
				</ul>
			</div>
		</div>
		<div class="spaceDiv"></div>
		<div class="form-group">
			<label class="control-label col-md-3"> <span class="required">*</span>Select
				Fee Setting Type :
			</label>
			<div class="col-md-5">
			<s:property value=""/>
				<s:if test="%{classFeeCountList != null && !classFeeCountList.isEmpty()}">
					<s:select list="objectList" listKey="id" listValue="settingName"
						id="settingFeeId" name="tempId" theme="simple" headerKey="" 
						cssClass="required form-control input-medium"
						headerValue="-Select Fee Setting Type-" disabled="true">
					</s:select>
					<s:hidden name="tempId" value="%{feeType.feeSettingId}"></s:hidden>
				</s:if>
				<s:else>
					<s:select list="objectList" listKey="id" listValue="settingName"
						id="settingFeeId" name="tempId" theme="simple" headerKey="" 
						cssClass="required form-control input-medium"
						headerValue="-Select Fee Setting Type-">
					</s:select>
				</s:else>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-md-3"> <span class="required">*</span>Fee
				Particular Name :
			</label>
			<div class="col-md-5">
				<sj:textfield name="feeType.feeType" id="feeTypeName" minlength="3"
					cssClass="required form-control input-medium" maxlength="120">
				</sj:textfield>
			</div>
		</div>
		<s:if test='%{customer.committedFeeStatus == "Y"}'>
			<s:if test="%{(feeType.id != 0) && (tempBoolean==false) && (feeType.priorityPosition !=0)}">
				<div class="form-group">
					<div class="checkbox">
						<label class="control-label col-md-3"> Committed fee is applicable : </label>
						<lable class="checkbox chkapplicable"> <input name="committedFeeStatus" value="Y"
							onclick="jascript:selectCommitment(this.value);" type="checkbox"
							id="applicable" disabled="disabled"></input> </lable>
					</div>
				</div>
				<div class="form-group">
					<div id="priority" style="display: none;">
						<label class="control-label col-md-3"> <span
							class="required">*</span>Priority :
						</label>
						<div class="col-md-5">
							<sj:textfield name="feeType.priorityPosition" id="priorityValue"
								 cssClass="numeric form-control input-medium as-input required" maxlength="1" readonly="true">
							</sj:textfield>
							<span class="helptext">(Priority values must be between 1 to 9)</span>
						</div>
						<!-- <input type="number" id="spinner5" maxlength="2" value="0" min="0" max="100" step="5" /> -->
					</div>
				</div>
			</s:if>
			<s:else>
				<div class="form-group">
					<div class="checkbox">
						<label class="control-label col-md-3"> Committed fee is applicable :</label>
						<lable class="checkbox chkapplicable"> <input name="committedFeeStatus" value="Y"
							onclick="jascript:selectCommitment(this.value);" type="checkbox"
							id="applicable"></input> </lable>
					</div>
				</div>
				<div class="form-group">
					<div id="priority" style="display: none;">
						<label class="control-label col-md-3"> <span
							class="required">*</span>Priority :
						</label>
						<div class="col-md-5">
							<sj:textfield name="feeType.priorityPosition" id="priorityValue"
								 cssClass="numeric form-control input-medium as-input required" maxlength="1">
							</sj:textfield>
							<span class="helptext">(Priority values must be between 1 to 9)</span>
						</div>
						<!-- <input type="number" id="spinner5" maxlength="2" value="0" min="0" max="100" step="5" /> -->
					</div>
				</div>
			</s:else>
		</s:if>
		
		
		<div class="form-actions fluid">
			<div class="col-md-offset-3 col-md-9">
				<sj:submit cssClass="btn blue" value="Submit" validate="true"
					formIds="addFeeParticular" targets="feeSettingsContent"
					onBeforeTopics="formValidation" />
				<s:if test="%{feeType.id != 0}">
					<button type="button" data-dismiss="modal" class="btn default">
						Cancel</button>
				</s:if>
				<s:else>
					<s:url id="doCancelFee" action="ajaxViewSelectedFeeSettings"
						includeParams="all" escapeAmp="false">
						<s:set name="tempId" value="feeType.feeSettingId"></s:set>
						<s:param name="tempString">feeParticulars</s:param>
					</s:url>
					<sj:a href="%{doCancelFee}" cssClass="btn default"
						targets="feeSettingsContent">Cancel</sj:a>
				</s:else>
			</div>
		</div>
	</div>
</s:form>
<s:if test="%{feeType.id != 0}">
	</div>
	</div>
</s:if>
<span class="particularId" id="<s:property value='feeType.id'/>"></span>
<script type="text/javascript">
$(document).ready(function() {
	$("input:checkbox, input:radio").uniform();
	$('select#settingFeeId').val($('select#settingFee option:selected').val());
	$(window).keydown(function(event){
	    if(event.keyCode == 13) {
	    if($('p.word-taken').html()=='Already taken!!!'){
	      event.preventDefault();
	    }
	  }
	 });
	changePageTitle("Create Fee Particulars");
	$('.blockHeader h2').html('Manage Academics');
	var positionNum = $("input#priorityValue").val();
	if(Number(positionNum) == 0){
		$("input#priorityValue").val('');
	}else if(Number(positionNum) >0){
		$('input#applicable').attr('checked',true);
		$('input#applicable').parent('span').addClass('checked');
		$('#priority').show();
	}
	var selectedStudent = $("select#select2_sample4").val();

	/*$.subscribe('doInitAddFee', function(event, data) {
		$('#stepFees').show();
	});
$.subscribe('subjFormValidation',function(event, data) {
	 $('button.close').click();
	});*/
	$("#feeTypeName").autoCheck("${pageContext.request.contextPath}/admin/ajaxCheckFeeParticularType.do",
			{
				minChars : 3,
				min : "no"
	});
	$("#priorityValue").autoCheck("${pageContext.request.contextPath}/admin/ajaxCheckFeeParticularType.do?anyId="+$('span.particularId').attr('id'),{
				minChars : 1,
				min : "no"
	});
	$('.numeric').numeric();
	$('.numericDot').numeric( {
		allow : "."
	});
});
$.subscribe('formValidation',function(event, data) {
	 $('p.word-taken').each(function() {
	  if($(this).html()=='Already taken!!!'){
	     event.originalEvent.options.submit=false;
	   }
	 });
	 if($('#addFeeParticular').valid()){
		 $('button.close').click();
	 }

});
	
function selectCommitment() {
	if($('input#applicable').prop("checked") == false){
		$('#priority').hide();
		var positionNum = '<s:property value="feeType.priorityPosition "/>';
		//if(Number(positionNum) >0){
			$("#status").val('N');
		//}
	}
	else if($('input#applicable').prop("checked") == true){
		$('#priority').show();
		$("#status").val('Y');
	}
	
}
</script>