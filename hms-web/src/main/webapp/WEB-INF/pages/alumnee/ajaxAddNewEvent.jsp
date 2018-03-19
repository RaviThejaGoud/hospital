<%@ include file="/common/taglibs.jsp"%>
<div class="form-body">
	<s:form id="socialNewEvent" action="ajaxAddNewEvent" method="post" theme="simple" cssClass="form-horizontal" namespace="/alumnee">
	<s:hidden name="tempString" value="event"></s:hidden>
	<s:hidden name="tempId2" value="%{shareUserActivitiesVO.id}"></s:hidden>
			<div class="form-group">
				<label class="control-label col-md-2"><span class="required">*</span>Event Name :</label>
				<div class="col-md-4">
				<sj:textfield name="shareUserActivitiesVO.eventName" id="title"
					 tabindex="2" cssClass="form-control required"
					 maxlength="40"></sj:textfield>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-md-2">
					<span class="required">*</span>Start Date :
				</label>
				<div class="col-md-4">
					<div class="input-group date form_meridian_datetime">   
						<s:if test="%{shareUserActivitiesVO.id != 0}">                                    
							<input id="startDate" type="text" size="16" readonly class="form-control" name="shareUserActivitiesVO.startDateTime"  value='<s:property value="shareUserActivitiesVO.startDateTime"/>' onchange="verifyDate();">
						</s:if>
						<s:else>
							<input id="startDate"  type="text" size="16" readonly class="form-control" name="shareUserActivitiesVO.startDateTime"  value="" onchange="verifyDate();">
						</s:else>
						<span class="input-group-btn">
						<button class="btn default date-reset" type="button"><i class="fa fa-times"></i></button>
						</span>
						<span class="input-group-btn">
						<button class="btn default date-set" type="button"><i class="fa fa-calendar"></i></button>
						</span>
					</div>
				</div>
		     </div>
		     <div class="form-group">
				<label class="control-label col-md-2">
					<span class="required">*</span>End Date :
				</label>
				<div class="col-md-4">
					<div class="input-group date form_meridian_datetime">       
						<s:if test="%{shareUserActivitiesVO.id != 0}">                                    
							<input id="endDate" type="text" size="16" readonly class="form-control" name="shareUserActivitiesVO.endDateTime"  value='<s:property value="shareUserActivitiesVO.endDateTime"/>' onchange="verifyDate();">
						</s:if>
						<s:else>
							<input id="endDate" type="text" size="16" readonly class="form-control" name="shareUserActivitiesVO.endDateTime"  value="" onchange="verifyDate();">
						</s:else>
						<span class="input-group-btn">
						<button class="btn default date-reset" type="button"><i class="fa fa-times"></i></button>
						</span>
						<span class="input-group-btn">
						<button class="btn default date-set" type="button"><i class="fa fa-calendar"></i></button>
						</span>
					</div>
				</div>
		     </div>
		     <div class="form-group">
				<label class="control-label col-md-2"> <span class="required">*</span>Location:</label>
				<div class="col-md-4">
					<sj:textfield name="shareUserActivitiesVO.eventLocation" id="eventLocation" 
								cssClass="form-control required"></sj:textfield>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-md-2">Description:</label>
				<div class="col-md-8">
					<sj:textarea name="shareUserActivitiesVO.description"  placeholder="This textarea has a limit of 1020 chars." cols="20" maxlength="1020"  id="maxlength_textarea4" 
						rows="5" cssClass="form-control required" ></sj:textarea>
				</div>
			</div>
		   <div class="form-actions fluid">
			<div class="col-md-offset-2 col-md-9">
				<sj:submit value="Submit" cssClass="submitBt btn blue" onBeforeTopics="socialNewEventForm" resetForm="true"
					formIds="socialNewEvent" targets="mainContentDiv" indicator="indicator" validate="true"/>
					<s:url id="eventDetails" action="ajaxViewEvents" namespace="/alumnee" />
					<sj:a  href="%{eventDetails}" targets="mainContentDiv" cssClass="btn default">Cancel</sj:a>
			</div>
		</div>
	</s:form>
 </div>
<script>
$(document).ready( function() {
	FormComponents.init();
	changePageTitle("View Friends");
}); 

$.subscribe('socialNewEventForm', function(event, data) {
	if ($('#socialNewEvent').valid()){
		return true;
	}else{
		event.originalEvent.options.submit=false;
	}
});
 
 function verifyDate() {
		var startDate = $('#startDate').val();
		var endDate =  $('#endDate').val();
		//alert(startDate+"--"+endDate);
		if (isNonEmpty(startDate) && isNonEmpty(endDate)) {
			if (endDate < startDate) {
				alert("Your end date is equal or more than your start date.");
				$('#endDate').val("");
			}
		}
	}
</script>