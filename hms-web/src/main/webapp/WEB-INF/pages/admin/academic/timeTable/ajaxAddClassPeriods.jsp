<%@ include file="/common/taglibs.jsp"%>
	<s:if test="%{objectList != null && !objectList.isEmpty()}">
		<jsp:include page="/common/messages.jsp" />
		<span id='settingsCreatedsubjectsIds'
			class='<s:property value="anyTitle"/>'></span>
		<s:form action="ajaxSaveSingleClassWisePeriods" theme="simple"
			id="saveClassPeriods" cssClass="form-horizontal" method="post"
			namespace="/admin">
<s:hidden name="tempString" id="tempString" value="%{studyClass.id}"></s:hidden>
			<div class="form-body">
			
				<div class="form-group">
					<label class="col-md-2 control-label" for="inputEmail1">
						Class Name :
					</label>
					<div class="col-md-4">
						<div class="checkbox-list">
							<label class="checkbox-inline">
								<b><s:property value="studyClass.classAndSection"/></b>
							</label>
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
<script type="text/javascript">
$(document).ready(function() {
	$.destroyTopic('periodFormValidation');
		$("input:checkbox, input:radio").uniform();
				 var settingSudjectIds = $("span#settingsCreatedsubjectsIds").attr('class');
				if (isNonEmpty(settingSudjectIds)) {
					var subjectsIds = settingSudjectIds.split(',');
					for ( var i = 0; i < subjectsIds.length; i++) {
						$('input:checkbox[name="selectBoxIdList"][value=' + subjectsIds[i] + ']').attr('disabled', true);
						$('input:checkbox[name="selectBoxIdList"][value=' + subjectsIds [i] + ']').parent('span').parent('div').addClass('disabled');
					}
				}
		$("input[name=selectBoxIdList]").click(function() {
				if ($("input[name=selectBoxIdList]:unchecked").length > 0) {
				   $(".wrkgDays").parent('span').removeClass("checked");
					$(".wrkgDays").attr("checked", false);
				} else {
				    $(".wrkgDays").parent('span').addClass("checked");
					$(".wrkgDays").attr("checked", true);
				}
		})
});
function checkAllDays() {
	if ($(".wrkgDays").is(':checked')){
	    $("[name='selectBoxIdList']:not(':disabled')").each(function(){
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