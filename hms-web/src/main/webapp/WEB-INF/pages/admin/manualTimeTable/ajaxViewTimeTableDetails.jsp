<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/ems.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/json2.js"></script>
<s:if test='%{timeTableDetailsCount==0}'>
	<div class="alert alert-info">Timetable not added for this class.</div>
</s:if>
<s:else>
<s:if test="%{weekDayList != null && !weekDayList.isEmpty()}">
	<s:if test="%{timeTablePeriodList != null && !timeTablePeriodList.isEmpty()}">
		<div class="table-scrollable">
			<table class="table table-hover table-bordered viewTableDateDetails" id="sample_editable_1 myTable">
				<thead>
					<tr>
						<th>
							<p style="text-align: center;">Day / Time</p>
						</th>
						<s:iterator value="timeTablePeriodList" status="stat">
							<th>
								<p style="text-align: center;"><s:property value='timeTablePeriodList[#stat.count-1][1]' /><br><s:property value='timeTablePeriodList[#stat.count-1][2]' />-<s:property value='timeTablePeriodList[#stat.count-1][3]' /></p>
							</th>
						</s:iterator>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="weekDayList">
						<s:set name="weekId" value='%{id}'></s:set>
						<tr id="monthId<s:property value="id"/>" class="dayViewSubjectPeriod" >
							<td>
								<s:property value="%{dayName}" />
							</td>
							<s:iterator value="timeTablePeriodList" status="stat">
								<s:set name="periodId" value='%{timeTablePeriodList[#stat.count-1][0]}'></s:set>
								<s:if test="%{timeTableSettings.shortBreakAfterNoOfPeriodsMorningSession>0 && #stat.count==timeTableSettings.shortBreakAfterNoOfPeriodsMorningSession+1}">
									<td id="mergeColumns" rowspan="1"  class="addRowSpanVal">
								  		<span id="subjectValue" cssStyle="width:45%">Short break</span> 
									</td>
							   </s:if>
								<s:elseif test="%{timeTableSettings.lunchBreakAfterNoOfPeriods>0 && #stat.count==timeTableSettings.lunchBreakAfterNoOfPeriods+(timeTableSettings.shortBreakAfterNoOfPeriodsMorningSession>0?1:0)+1}">
									<td id="mergeColumns" rowspan="1"  class="addRowSpanVal">
								  		<span id="subjectValue" cssStyle="width:45%">Lunch break</span> 
									</td>
							  	</s:elseif>
								<s:elseif test="%{timeTableSettings.shortBreakAfterNoOfPeriodsAfternoonSession>0 && #stat.count==timeTableSettings.shortBreakAfterNoOfPeriodsAfternoonSession+(timeTableSettings.shortBreakAfterNoOfPeriodsMorningSession>0?1:0)+(timeTableSettings.lunchBreakAfterNoOfPeriods>0?1:0)+1}">
									<td id="mergeColumns" rowspan="1"  class="addRowSpanVal">
								  		<span id="subjectValue" cssStyle="width:45%">Short break</span> 
									</td>
							   </s:elseif>
								<s:else>
									<td id="monthPeriodSubjectViewId<s:property value='%{#weekId}'/><s:property value='%{#periodId}'/>" rowspan="1" class="addRowSpanVal">
										<span id="subjectValue" cssStyle="width:45%"></span> 
									    <!-- <span id="isSubjectPracticalCount" cssStyle="width:45%" hidden="true"></span> --> 
									    <span id="isSubjectPractical" cssStyle="width:45%" hidden="true"></span>
									    <span id="isSubjectType" cssStyle="width:45%" hidden="true"></span>
									</td>
								</s:else>
							</s:iterator>
						</tr>
					</s:iterator>
				</tbody>
			</table>
			</div>
	</s:if>
	<s:else>
		<div class="alert alert-info">
			Time-Table settings are not provided for the selected subject Please provide timetable settings.(settings->Timetable->Assign subjects to periods).
		</div>
	</s:else>
</s:if>
<s:else>
	<div class="alert alert-info">
		No Working days are selected, Please select working days in academic planner settings(Settings->Academic planner).
	</div>
</s:else>
<div class="span12"> </div>
<div class="tabbable tabbable-custom">
<h4><b>Subject With Faculty Information</b></h4>
	<s:if test="%{subjectsList != null && !subjectsList.isEmpty()}">
		<table class="table table-striped table-bordered table-hover table-full-width" id="sample_2">
			<thead>
				<tr>
					<th>Subject</th>
					<th>Faculty Name</th>
				</tr>
			</thead>
			<tbody>
			<s:iterator value="subjectsList" status="stat">
				<tr>
					<td>
						<s:property value="name" />
						<s:if test='%{"Y"==subjectType}'>
							(Optional)
						</s:if>
						<s:elseif test='%{language}'>
							(Language)
						</s:elseif>
					</td>
					<td>
						<s:property value="tempHashMap1[id]" />
					</td>
				</tr>
				</s:iterator>
			</tbody>
		</table>
	</s:if>
	<s:else>
		<div class="alert alert-info">
			Subjects are not assigned staff.
		</div>
	</s:else>
</div>
</s:else>
<script type="text/javascript">
	$(document).ready(function() {
		//var studyClassId=$('#studyClassId').val();
		//alert("home child: "+$('#studyClassId').val());
		var studyClassId=$('select#studyClassId>option:selected').val();
		var timeTableDetailsCount = '<s:property value="timeTableDetailsCount" />';
		if(studyClassId>0 && timeTableDetailsCount>0){
			var dataURL = jQuery.url.getChatURL("/timeTable/ajaxGetSubjectPeriodDayWiseDetails.do?studyClassId="+studyClassId+"&anyTitle=Y");
			$.ajax( {
				url : dataURL,
				cache : false,
				dataType : 'json',
				contentType:'application/x-www-form-urlencoded',
				success : function(response) {
					if(isNonEmpty(response)){
						if (response.dayPeriodSubjectSettingsData) {
							var data = response.dayPeriodSubjectSettingsData;
							//var tdPracticleCountVal=1;
							//var tdRowSpanStartVal;
							//var tdRowSpanEndVal;
							if (data.length > 0) {
								for ( var i = 0; i < data.length; i++){
									$('tr.dayViewSubjectPeriod:visible').each(function(){
										 $(this).find("td#monthPeriodSubjectViewId"+ data[i].DAYID+ data[i].PERIODID).each(function() {
												$(this).find('span#subjectValue').text(data[i].INPUTVALUE.replace(/\,/g,' / '));
												//$(this).find('span#isSubjectPracticalCount').text(data[i].NOOFPERIODSID);
												$(this).find('span#isSubjectPractical').text(data[i].PRACTICALVAL);
												$(this).find('span#isSubjectType').text(data[i].SUBJECTTYPE);
										  });
										//tdRowSpanStartVal;tdRowSpanEndVal;tdPracticleCountVal=0;
									});
								}
							}
							//var inputVal;
							//var nextVal;
							$('tr.dayViewSubjectPeriod:visible').each(function(){ 
								$(this).find('td').each(function(){
									if($(this).hasClass('addRowSpanVal')){
										if(isNonEmpty($(this).find('span#subjectValue').text()) && isNonEmpty($(this).find('span#subjectValue').next().text())){
											//var inputPeriodCount=$(this).find('span#isSubjectPracticalCount').text();
											var inputPeriodStatus=$(this).find('span#isSubjectPractical').text();
											var inputSubjectType=$(this).find('span#isSubjectType').text();
											Current_td = $(this);
											if("Y"==inputSubjectType){
												Current_td.css({'background-color' : '#F5FFFA','text-align' : 'center'});
											}else if("Y"==inputPeriodStatus){
												Current_td.css({'background-color' : '#FFF5EE','text-align' : 'center'});
											}else
												Current_td.css({'text-align' : 'center'});
											var colSpan=1; 
											while( $(this).text() == $(this).next().text() )
											{
												$(this).next().remove(); colSpan++; 
											}
											$(this).attr('colSpan',colSpan);
										}
									}
								});
							}); 
						}
					}
				}
			});
		}
		//$('tr.dayViewSubjectPeriod:visible').each(function() {$(this).find("td[rowspan='1']:first").attr('rowspan','6') });
		//$('tr.dayViewSubjectPeriod').find("td[rowspan='1'] ").attr('rowspan','6');
	});
</script>