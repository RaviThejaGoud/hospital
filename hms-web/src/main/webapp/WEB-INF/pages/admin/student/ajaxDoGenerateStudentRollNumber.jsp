<%@ include file="/common/taglibs.jsp"%>
<div id="contentDiv">
	<%@ include file="/common/messages.jsp"%>
		
		<s:form action="ajaxGenerateStudentRollNumber" theme="simple" id="generateStudentRollNumber"
			cssClass="form-horizontal" 
			method="post" namespace="/admin">
		<s:hidden id="genderWiseStudent"/>
		<div class="form-group">
			<label class="col-md-3 control-label"><span
						class="required">*</span> Select Class : </label>
			<div class="col-md-9">
				<s:select list="studyClassList" id="classSectionId"
					cssClass="form-control input-medium required" name="classSectionId"
					listKey="id" listValue="classAndSection" headerKey=""
					headerValue="- Select Class -" theme="simple"
					/>
			</div>
		</div>
		
		
		<div class="row" id="schoolWiseHolidays">
				<div class="form-group">
					<label class="col-md-3 control-label"> <span
						class="required">*</span>Roll Number Generation :
					</label>
					<div class="col-md-9">
						<s:radio
							list="#{'RA':'Student name Alphabetical order','RG':'Gender wise and Student name Alphabetical order'}"
							onclick="javascript:selectReportType(this.value)"
							name="tempString2" cssClass="required" id="reportType" />
					</div>
				</div>
			</div>
			
			<div class="row" id="dateSelectionDivId" style="display: none;">
				
				<div class="form-group">
					<label class="col-md-3 control-label"> 
					</label>
					<div class="col-md-9">
						<s:radio
							list="#{'RM':'Gender Male need to consider starting sequence number','RF':'Gender Female need to consider Starting sequence number'}"
							name="tempString3" />
					</div>
				</div>
				

			</div>
			
			
			<div class="form-actions fluid">
				<div class="col-md-offset-2 col-md-9">
					<s:if
						test='%{(#session.previousYear == null || #session.previousYear.isEmpty()) || (#session.previousYear == "N")}'>
						<sj:submit cssClass="btn blue" value="Submit" indicator="indicator"
							onBeforeTopics="validateStudentRollNumber" targets="mainContentDiv"
							validate="true" />
					</s:if>
				</div>
			</div>
		
		
	</s:form>
	
</div>
<script type="text/javascript">
	$(document).ready(function(){
		
		changePageTitle("Manage Student Roll Numbers");
		
		/* var reportType = $("input[name=tempString2]").val();
		if(isNonEmpty(reportType))
		{
			selectReportType(reportType);
		} */
		$.destroyTopic('validateStudentRollNumber');
	});
	
	$.subscribe('validateStudentRollNumber',function(event, data) {
		
		if ($("input[name=tempString2]:checked").length == 0) {
			alert("Please select at least one generation setting option.");
			event.originalEvent.options.submit = false;
		}
		else
		{
			var settingType = $("#genderWiseStudent").val();
			if("RG" == settingType)
			{
				if ($("input[name=tempString3]:checked").length == 0) {
					alert("Please select at least one option.");
					event.originalEvent.options.submit = false;
				}
			}
		}
	});
	
	function selectReportType(reportType) {
		
		if (reportType=='RG') {
			$("#dateSelectionDivId").show();
			$('#genderWiseStudent').val('RG');
		}
		else {
			$("#dateSelectionDivId").hide();
			$('#genderWiseStudent').val('RA');
		}
	}
	
</script>