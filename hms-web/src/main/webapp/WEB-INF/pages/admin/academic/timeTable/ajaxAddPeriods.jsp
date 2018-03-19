<%@ include file="/common/taglibs.jsp"%>
<s:if test="%{studyClassList != null && !studyClassList.isEmpty()}">
	<s:if test="%{objectList != null && !objectList.isEmpty()}">
		<jsp:include page="/common/messages.jsp" />
		<%-- <span id='settingsCreatedClassIds'
			class='<s:property value="anyTitle"/>'></span> --%>
		<s:form action="ajaxSaveClassWisePeriods" theme="simple"
			id="saveClassPeriods" cssClass="form-horizontal" method="post"
			namespace="/admin">
			<div class="form-body">
				<div class="panel-body col-md-12">
					<div class="col-md-1">
						<span class="label label-danger"> NOTE : </span>
					</div>
					<div class="col-md-10">
						<ul>
							<li>
								If school has only morning session please add number of periods
								count in 'Number of Morning Periods' place.
							</li>
							<li>
								If periods are created for any classes those classes are
								disabled.
							</li>
						</ul>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label" for="inputEmail1">
						Select all Classes :
					</label>
					<div class="col-md-4">
						<div class="checkbox-list">
							<label class="checkbox-inline">
								<input type="checkbox" name="" value="" onClick="checkAll()"
									class="checkbox classes">
							</label>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label">
						<span class="required"> * </span>Available Classes :
					</label>
					<div class="col-md-9">
						<div class="checkbox-list">
							<s:iterator value="studyClassList">
								<label class="checkbox checkbox-inline"
									style="width: 270px; margin-left: 0px;">
									<s:if test="%{timeTablePeriodsDetails.size() == numberOfDays}">
										<input type="checkbox" class="studyClass<s:property value="id" />" value="<s:property value="id" />" name="chkBoxSelectedIds"
											disabled="disabled" />
									</s:if>
									<s:else>
										<input type="checkbox" class="studyClass<s:property value="id" />" value="<s:property value="id" />" name="chkBoxSelectedIds" />
									</s:else>
									<s:property value="classAndSection" />
								</label>
							</s:iterator>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label" for="inputEmail1">
						Select All Days :
					</label>
					<div class="col-md-4">
						<div class="checkbox-list">
							<label class="checkbox-inline">
								<input type="checkbox" name="" value="" onClick="checkAllDays()"
									class="wrkgDays">
							</label>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label">
						<span class="required"> * </span>Select Days :
					</label>
					<div class="col-md-9">
						<s:checkboxlist list="objectList" name="selectBoxIdList"
							id="workingDays" listKey="id" listValue="dayName" theme="ems"></s:checkboxlist>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4">
								Number of Morning Periods :
							</label>
							<div class="col-md-7">
								<sj:textfield name="tempId1" id="mrngNumOfPeriods"
									onkeypress="return onlyNumbers(event);"
									cssClass="form-control numeric input-medium" maxlength="2"></sj:textfield>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-6">
								Number of Afternoon Periods :
							</label>
							<div class="col-md-6">
								<sj:textfield name="tempId2" id="aftrNumOfPeriods"
									onkeypress="return onlyNumbers(event);"
									cssClass="numeric form-control input-medium" maxlength="2"></sj:textfield>
							</div>
						</div>
					</div>
				</div>
				<div class="form-actions fluid">
					<div class="col-md-offset-2 col-md-9">
						<sj:submit   cssClass="submitBt btn blue" value="Submit"
							validate="true" onBeforeTopics="periodFormValidation"
							targets="mainContentDiv" formIds="saveClassPeriods"/>
						<s:url id="doViewCancelEditFormPeriods"
							action="ajaxGetSchoolPeriods" includeParams="all"
							escapeAmp="false" namespace="/admin"></s:url>
						<sj:a href="%{doViewCancelEditFormPeriods}" cssClass="btn default"
							targets="mainContentDiv">Cancel</sj:a>
					</div>
				</div>
			</div>
		</s:form>
	</s:if>
	<s:else>
		<div class="alert alert-info">
			Working days are not available. Please create academic planner.
			<s:url id="urlSendSmsLink" 
					action="ajaxAcademicSchoolSettings" includeParams="all" namespace="/admin" />
				<sj:a href="%{urlSendSmsLink}" targets="mainContentDiv"
					 indicator="indicator" cssClass="academicPlannerId">Click
				here</sj:a> to add academic planner details.
		</div>
	</s:else>
</s:if>
<s:else>
	<div class="alert alert-info">
		You have not created any classes. Please add classes.
	</div>
</s:else>
<script type="text/javascript">
$(document).ready(function() {
	$.destroyTopic('periodFormValidation');
$("input:checkbox, input:radio").uniform();
		/* var settingClassIds = $("span#settingsCreatedClassIds").attr('class');
		if (isNonEmpty(settingClassIds)) {
			var classIds = settingClassIds.split(',');
			for ( var i = 0; i < classIds.length; i++) {
				$('input:checkbox[name="chkBoxSelectedIds"][value=' + classIds[i] + ']').attr('disabled', true);
				$('input:checkbox[name="chkBoxSelectedIds"][value=' + classIds[i] + ']').parent('span').parent('div').addClass('disabled');
			}
		} */
		$.subscribe('periodFormValidation',function(event, data) {
			     var mrngPeriodsCount= $('#mrngNumOfPeriods').val();
			     var aftrPeriodsCount= $('#aftrNumOfPeriods').val();
			     var totalClassesCount = $("input[name='chkBoxSelectedIds']").length;
			     var disabledClassesCount = $("input[name='chkBoxSelectedIds']:disabled").length;
			     if(Number(totalClassesCount) == Number(disabledClassesCount)){
				 		alert("You have already created periods for all classes. You can change periods details of classes in 'Class wise Periods Details' section.");
						event.originalEvent.options.submit=false;
				 }else if($("input[name='chkBoxSelectedIds']:checked").length == 0){
				 		alert("Please Select at least one class.");
						event.originalEvent.options.submit=false;
				 }else if($("input[name='selectBoxIdList']:checked").length == 0){
				 		alert("Please Select at least one day.");
						event.originalEvent.options.submit=false;
				 }else if(!isNonEmpty(mrngPeriodsCount) && !isNonEmpty(aftrPeriodsCount)){
				 		alert("Please type Number of Morning Periods or Number of Afternoon Periods.");
						event.originalEvent.options.submit=false;
				 } else if((mrngPeriodsCount == 0 && aftrPeriodsCount == 0) || (mrngPeriodsCount == 0 && !isNonEmpty(aftrPeriodsCount)) || (!isNonEmpty(mrngPeriodsCount) && aftrPeriodsCount == 0)){
				 		alert("Number of Morning Periods or Number of Afternoon Periods should be greater than '0'.");
						event.originalEvent.options.submit=false;
				 } 
	});
$("input[name=selectBoxIdList]").click(function() {
		if ($("input[name=selectBoxIdList]:unchecked").length > 0) {
		   $(".wrkgDays").parent('span').removeClass("checked");
			$(".wrkgDays").attr("checked", false);
		} else {
		    $(".wrkgDays").parent('span').addClass("checked");
			$(".wrkgDays").attr("checked", true);
		}
	})
	
$("input[name=chkBoxSelectedIds]").click(function() {
		if ($("input[name=chkBoxSelectedIds]:unchecked").length > 0) {
		   $(".classes").parent('span').removeClass("checked");
			$(".classes").attr("checked", false);
		} else {
		    $(".classes").parent('span').addClass("checked");
			$(".classes").attr("checked", true);
		}
	})
	
});

 
function checkAll() {
	if ($(".classes").is(':checked')){
	    $("[name='chkBoxSelectedIds']:not(':disabled')").each(function(){
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

function checkAllDays() {
	if ($(".wrkgDays").is(':checked')){
	    $("[name='selectBoxIdList']").each(function(){
		   $(this).parent('span').addClass('checked');
		    $(this).attr("checked", "true");
		});
	}
	else{
	 $("[name='selectBoxIdList']").each(function(){
		   $(this).parent('span').removeClass('checked');
		    $(this).removeAttr("checked");
		});
	}	
}
$('a.academicPlannerId').click(function(){
	window.location.hash="target=ES.ajaxify AAP";
	window.location.reload();
})
 
</script>