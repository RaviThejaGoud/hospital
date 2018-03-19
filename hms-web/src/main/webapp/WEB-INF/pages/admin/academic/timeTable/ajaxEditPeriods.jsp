<%@ include file="/common/taglibs.jsp"%>
<div class="form-body">
	<jsp:include page="/common/messages.jsp" />
	<s:if test="%{objectList != null && !objectList.isEmpty()}">
		<s:form action="ajaxUpdateClassWisePeriods" theme="simple" id="updateClassPeriods" method="post" cssClass="form-horizontal" namespace="/admin">
		  <s:hidden name="studyClassId" value="%{studyClassId}" cssClass="classId"></s:hidden> <!-- classSectionIdId -->
			<h4 class="pageTitle bold">
				Update Periods 
			</h4>
			<span class="label label-danger"> NOTE : </span>&nbsp;You can update morning session and afternoon session periods count.
				<div class="form-group">
					<label class="control-label col-md-3">Class Name :</label>
					<div class="col-md-2">
						<p class="form-control-static"> <s:property value="tempString" /> </p>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-md-3"><span class="required">*</span> Day Name :</label>
					<div class="col-md-2">
						<s:select list="objectList" listKey="id" cssClass="form-control" listValue="dayName" name="anyId" onchange="javascript:getNumberOfmrngAndAftrnoonDays(this.value);"></s:select>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-md-3">Number of Morning Periods :</label>
					<div class="col-md-2">
						<sj:textfield name="tempId1" id="mrngNumOfPeriods"
						onkeypress="return onlyNumbers(event);"
						cssClass="numeric form-control" maxlength="2"></sj:textfield>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-md-3"> Number of Afternoon Periods :</label>
					<div class="col-md-2">
						<sj:textfield name="tempId2" id="aftrNumOfPeriods"
						onkeypress="return onlyNumbers(event);"
						cssClass="numeric form-control" maxlength="2"></sj:textfield>
					</div>
				</div>
			   <div class="form-group">
					<label class="col-md-3 control-label">Copy number of periods to all working days :</label>
					<div class="col-md-8">
						<div class="checkbox-list">
							<label class="checkbox-inline">
						<input type="checkbox"  name="status"  class="copyPeriodsCount" id="inlineCheckbox21"></label>
							Please select this check box for copying number of morning periods count and afternoon periods count to all working days of this class.
						</div>
					</div>
			  </div>
				<div class="form-actions fluid">
					<div class="col-md-offset-3 col-md-9">
					<sj:submit   cssClass="submitBt btn blue" value="Submit" validate="true" onBeforeTopics="periodUpdateFormValidation"
						indicator="indicator" targets="mainContentDiv" formIds="updateClassPeriods" />
						<s:url id="doViewPeriods" action="ajaxGetSchoolPeriods" includeParams="all" escapeAmp="false" namespace="/admin"></s:url>
						<sj:a href="%{doViewPeriods}" cssClass="btn default" targets="mainContentDiv">Cancel</sj:a>
				    </div>
		       </div>
		</s:form>
	</s:if>
	<s:else>
		<div class="alert alert-info">
			Working days not available. Please create working days.
		</div> 
	</s:else>
</div>
<script type="text/javascript">
$(document).ready(function() {
$("input:checkbox, input:radio").uniform();
$.destroyTopic('periodUpdateFormValidation');
		$.subscribe('periodUpdateFormValidation',function(event, data) {
			if ($('#updateClassPeriods').valid()) {
			     var mrngPeriodsCount= $('#mrngNumOfPeriods').val();
			     var aftrPeriodsCount= $('#aftrNumOfPeriods').val();
				if(!isNonEmpty(mrngPeriodsCount) && !isNonEmpty(aftrPeriodsCount)){
				 		alert("Please type Number of Morning Periods or Number of Afternoon Periods.");
						event.originalEvent.options.submit=false;
				 } else if((mrngPeriodsCount == 0 && aftrPeriodsCount == 0) || (mrngPeriodsCount == 0 && !isNonEmpty(aftrPeriodsCount)) || (!isNonEmpty(mrngPeriodsCount) && aftrPeriodsCount == 0)){
				 		alert("Number of Morning Periods or Number of Afternoon Periods should be greater than '0'.");
						event.originalEvent.options.submit=false;
				 } 
			} else
				event.originalEvent.options.submit=false;
	});
});
function getNumberOfmrngAndAftrnoonDays(dayId){
	var classId = $('.classId').val();
	if(isNonEmpty(dayId) && isNonEmpty(classId)){
		$.ajax( {
				url : jQuery.url.getChatURL("/admin/ajaxGetTimitableMrngAndAfterNoonPeriodsCount.do?studyClassId="+ classId+ "&anyId="+ dayId),
				cache : false,
				dataType : 'json',
				success : function(response) {
					if (isNonEmpty(response)) {
						if(isNonEmpty(response.mrngPeriodsCount)){
							$('#mrngNumOfPeriods').val(response.mrngPeriodsCount);
						}else{
							$('#mrngNumOfPeriods').val('0');
						}
						if(isNonEmpty(response.afterNoonPeriodsCount)){
							$('#aftrNumOfPeriods').val(response.afterNoonPeriodsCount);
						}else{
							$('#aftrNumOfPeriods').val('0');
						}
					}else{
						$('#mrngNumOfPeriods').val('0');
						$('#aftrNumOfPeriods').val('0');
					}
				}
			});
	}
}
</script>