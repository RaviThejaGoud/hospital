<%@ include file="/common/taglibs.jsp"%>

<span id="startDateSpan" class="<s:property value='startDate'/>"></span>
<span id="endDateSpan" class="<s:property value='endDate'/>"></span>
<span id="toDateSpan" class="<s:property value='toDate'/>"></span>
	
<s:form action="ajaxViewMessageHistory" theme="simple"
	id="viewSmsHistory" method="post" cssClass="form-horizontal"
	namespace="/staff">
	<s:hidden name="plTitle"></s:hidden>
	<s:hidden name="anyTitle"></s:hidden>
	<s:hidden name="empId" value="smsHistory"></s:hidden>
	<span class="label label-danger">NOTE :</span>&nbsp; Your date selection should be less than or equal to 60 days. For Ex: April 1st - June 1st. 
	<div class="spacediv"></div>
	<div class="form-body">
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-3">
						<span class="required">*</span>From Date :
					</label>
					<div class="col-md-8">
						<div data-date-start-date="" data-date-end-date="+0d"
							data-date-format="mm/dd/yyyy"
							class="input-group input-medium date date-picker">
							<input type="text" id="startDate" name="startDate"
								readonly="readonly" 
								onchange="javascript:getStudents();"
								class="form-control required">
							<span class="dateInput input-group-btn">
								<button type="button" class="btn default">
									<i class="fa fa-calendar"></i>
								</button> </span>
						</div>
						<span class="help-block">(MM/DD/YYYY)</span>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-2">
						<span class="required">*</span>To Date :
					</label>
					<div class="col-md-8">
						<div data-date-start-date="" data-date-end-date="+0d"
							data-date-format="mm/dd/yyyy"
							class="input-group input-medium date date-picker">
							<input type="text" id="endDate" name="endDate"
								readonly="readonly" 
								onchange="javascript:getStudents();"
								class="form-control required">
							<span class="dateInput input-group-btn">
								<button type="button" class="btn default">
									<i class="fa fa-calendar"></i>
								</button> </span>
						</div>
						<span class="help-block">(MM/DD/YYYY)</span>
					</div>
				</div>
			</div>
		</div>
		<s:if test="%{plTitle != null && !plTitle.isEmpty() && !plTitle != 'TRMessages'}">
		<div class="form-actions fluid">
			<div class="col-md-6">
				<div class="col-md-offset-3 col-md-12">
					<sj:submit cssClass="submitBt btn blue" value="Submit"
						targets="classTeacherMessagesHomeDiv" validate="true" formIds="viewSmsHistory"
						onBeforeTopics="smsHistoryFormValidation" />
						<s:url id="doCancelSmsHistorys" action="ajaxDoGetClassWideMessagesList" 
							includeParams="all" escapeAmp="false" namespace="/common">
							<s:param name="anyId">M</s:param>
							<s:param name="tempBoolean" value="true"></s:param>
							</s:url>
						<sj:a href="%{doCancelSmsHistorys}" cssClass="btn default"
							targets="classTeacherMessagesHomeDiv" button="false">Cancel</sj:a>
				</div>
			</div>
		</div>
		</s:if>
		<s:else>
		<div class="form-actions fluid">
			<div class="col-md-6">
				<div class="col-md-offset-3 col-md-12">
				<s:if test="%{anyTitle != null && !anyTitle.isEmpty()}">
					 <sj:submit cssClass="submitBt btn blue" value="Submit"
						targets="schoolWideMessagesHomeDiv" validate="true" formIds="viewSmsHistory"
						onBeforeTopics="smsHistoryFormValidation" />
						<s:url id="urlCancelSmsHistory" action="ajaxDoGetSchoolWideMessagesList" 
							includeParams="all" escapeAmp="false" namespace="/common">
							<s:param name="status">TR</s:param>
							</s:url>
						<sj:a href="%{urlCancelSmsHistory}" cssClass="btn default"
							targets="mainContentDiv" button="false">Cancel</sj:a>
				</s:if>
				<s:else>
					<sj:submit cssClass="submitBt btn blue" value="Submit"
					targets="schoolWideMessagesHomeDiv" validate="true" formIds="viewSmsHistory"
					onBeforeTopics="smsHistoryFormValidation" />
					<s:url id="doCancelSmsHistory" action="ajaxDoSendSchoolWideMessages"
				         includeParams="all" namespace="/common"></s:url>
					<sj:a href="%{doCancelSmsHistory}" cssClass="btn default"
						targets="mainContentDiv" button="false">Cancel</sj:a>
				</s:else>
				</div>
			</div>
		</div>
		</s:else>
	</div>
	</s:form>
<script type="text/javascript">
	$(document).ready(function(){
	var startDate = $('span#startDateSpan').attr("class");
	var endDate = $('span#endDateSpan').attr("class");
	dateRestrictionWithinAcademicYear(startDate,endDate);
	FormComponents.init();
	$.destroyTopic('smsHistoryFormValidation');
    });
	function getStudents(){
		var startDate = $('#startDate').val();
		var endDate = $('#endDate').val();
		if (isNonEmpty(startDate) || isNonEmpty(endDate)) {
		    startDate = new Date(startDate);
			endDate = new Date(endDate);
		    if (startDate > endDate) {
		     alert('To date has to be greater than From date.');
		     $('#endDate').val("");
		     return false;	
			}
			var startDateOfSms = new Date(startDate.setMonth(startDate.getMonth() + 2));
			if (endDate > startDateOfSms) {
				alert("Your date selection should be less than or equal to 60 days.");
				$('#endDate').val("");
				 return false;	
			}
		}
		return true;
	}
</script>