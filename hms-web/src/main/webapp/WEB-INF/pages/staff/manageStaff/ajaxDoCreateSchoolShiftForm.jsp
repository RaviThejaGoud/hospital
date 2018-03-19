<%@ include file="/common/taglibs.jsp"%>
<div class="form-body">
<s:form action="ajaxAddSchoolShiftInfo" theme="simple" cssClass="form-horizontal" id="staffBatchInfor" method="post" namespace="/staff">
	<s:hidden id="selectStaffShiftInfo" name="empId" />
	<s:hidden id="tempId" name="schoolShiftInfo.id" />
	<s:hidden id="isStaffOrNonStaff" name="schoolShiftInfo.isStaffOrNonStaff" />
	<div class="row">
	<s:if test="%{schoolShiftInfo == null}">
		<h4 class="pageTitle bold">
			Add School Shift Info
		</h4>
		<hr />
	</s:if>
	
	<s:else>
		<h4 class="modal-title">
				Update School Shift Info
			</h4>
	</s:else>
		<div class="col-md-6">
			<div class="form-group">
				<label class="control-label col-md-4"> <span
					class="required">*</span>Shift Name :
				</label>
				<div class="col-md-5">
					<sj:textfield name="schoolShiftInfo.shiftName" id="shiftName" tabindex="1"
						cssClass="required form-control" maxlength="60"></sj:textfield>
				</div>
			</div>
		</div>
		<div class="col-md-6">
			<div class="form-group timeEntry">
				<label class="control-label col-md-4"> <span
					class="required">*</span>Start Time :
				</label>
				<div class="col-md-5">
					<sj:textfield name="schoolShiftInfo.startTime" id='startTime' tabindex="2"
						cssClass="required form-control input-small startTime timeChange" maxlength="10"></sj:textfield>
						<span class="hintMessage">Time format 11:30:15</span>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-md-6">
			<div class="form-group timeEntry">
				<label class="control-label col-md-4"> <span
					class="required">*</span>End Time :
				</label>
				<div class="col-md-5">
					<sj:textfield name="schoolShiftInfo.endTime" id='endTime' tabindex="2"
						cssClass="required form-control input-small endTime timeChange" maxlength="10"></sj:textfield>
						<span class="hintMessage">Time format 11:30:15</span>
				</div>
			</div>
		</div>
	</div>
	<!-- <div class="row">
		<div class="col-md-6">
			<div class="form-group">
				<label class="col-md-3 control-label"> <span
					class="required">*</span>Applicable Staff Members :
				</label>
			</div>
		</div>
	</div> -->
	<div class="row form-group">
		<label class="col-md-2 control-label" style="width: 12.667%">
			Select Staff Type : </label>
		<div class="col-md-10">
			<div class="radio-list">
				<label class="radio-inline teachingStaff"> <input type="radio"
					checked="checked" value="S"
					onchange="javaScript:getStaffDetails(this.value);" name="tempString" id="teachingStaff">
					Teaching
				</label> <label class="radio-inline nonTeachingStaff"> <input type="radio"
					value="N" onchange="javaScript:getStaffDetails(this.value);"
					name="tempString"  id="nonTeachingStaff"> Non Teaching
				</label>
			</div>
		</div>
	</div>
	<div id="teachingOrNonTeachingStaffInfoDiv"></div>
	<div class="form-actions fluid">
		<div class="row">
			<div class="col-md-6">
				<div class="col-md-offset-3 col-md-12">
					<sj:submit cssClass="submitBt btn blue" value="Submit" formIds="staffBatchInfor"
						indicator="indicator" targets="mainContentDiv" validate="true"
						onBeforeTopics="staffCreateBatchInfos" />
					<s:url id="doCancelStaffBatch"
						action="ajaxViewSchoolShiftInfo" includeParams="all"
						escapeAmp="false" namespace="/staff">
					</s:url>
					<sj:a href="%{doCancelStaffBatch}" cssClass="btn default" targets="mainContentDiv">Cancel</sj:a>
				</div>
			</div>
		</div>
	</div>
</s:form> 
</div>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/timeEntry/jquery.timeentry.js">
</script>
<script type="text/javascript">
$(document).ready(function() {
	$.destroyTopic('staffCreateBatchInfos');
	$('.timeChange').timeEntry( { disableFocus: true, showInputs: false, showSeconds: true, show24Hours: true, defaultValue: '12:45:30' } );
	FormComponents.init();
	FormAdvanced.init();
	var isStaffOrNonStaff=$("#isStaffOrNonStaff").val();
	if(isNonEmpty(isStaffOrNonStaff) && isStaffOrNonStaff =='N'){
		$("#teachingStaff").attr('checked', false);
		$("#nonTeachingStaff").attr('checked', true);
	}else{
		isStaffOrNonStaff ='S';
		$("#teachingStaff").attr('checked', true);
		$("#nonTeachingStaff").attr('checked', false);
	}
	getStaffDetails(isStaffOrNonStaff);
	$.subscribe('staffCreateBatchInfos', function(event, data) {
		var startTime=$('input#startTime').val();
		var endTime=$('input#endTime').val();
		//alert(startTime.length+'----'+endTime.length);
		if(startTime.length<8 && endTime.length<8){
			alert('Please enter valied start time and end time.');
			event.originalEvent.options.submit=false;
		}else if(startTime.length<8){
			alert('Please enter valied start time.');
			event.originalEvent.options.submit=false;
		}else if(endTime.length<8){
			alert('Please enter valied end time.');
			event.originalEvent.options.submit=false;
		}
		var selectedIds=[];
		if (($('input[name=chkBoxSelectedAccountIds]:checked').length) > 0){
			$('input:checkbox[name=chkBoxSelectedAccountIds]:checked').each(function()
			{
				selectedIds.push($(this).val());
			});
			$('#selectStaffShiftInfo').val(selectedIds);	
		}
		else {
			alert("Please select at least one staff member.");
			event.originalEvent.options.submit=false;
		}
		if ($('#staffBatchInfor').valid()){
			return true;
		}
	});
});
function getStaffDetails(staffType) {
	if(isNonEmpty(staffType)){
		var schoolShiftId =0;
		schoolShiftId ='<s:property value="schoolShiftInfo.id"/>';
		if(schoolShiftId==0){
			$("#shiftName").val('');
			$("#startTime").val('');
			$("#endTime").val('');
		}
		if(staffType =='N'){
			$("#teachingStaff").attr('checked', false);
			$("#nonTeachingStaff").attr('checked', true);
			
		}else{
			$("#teachingStaff").attr('checked', true);
			$("#nonTeachingStaff").attr('checked', false);
		}
		$('#teachingOrNonTeachingStaffInfoDiv').html('<div align="center" style="padding:30px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		var url = jQuery.url.getChatURL("/common/ajaxDoSendLoginCredentials.do");
		var pars = "anyTitle="+staffType+"&tempId="+$("#tempId").val();
		$.ajax( {
			type : "POST",
			url : url,
			data : pars,
			cache : false,
			success : function(responce) {
				$("#teachingOrNonTeachingStaffInfoDiv").html(responce);
			}
		});
	  }
}
</script>
