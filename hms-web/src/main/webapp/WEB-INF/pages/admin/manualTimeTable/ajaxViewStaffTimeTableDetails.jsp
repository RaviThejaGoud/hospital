<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/ems.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/json2.js"></script>
<s:if test='%{timeTableSettings != null}'>
<s:if test='%{timeTableDetailsCount==0}'>
	<div class="alert alert-info">Timetable not assigned to you.</div>
</s:if>
<s:else>
	<s:if test="%{weekDayList != null && !weekDayList.isEmpty()}">
		<s:if test="%{timeTablePeriodList != null && !timeTablePeriodList.isEmpty()}">
			<p style="float: left"><span class="label label-important">NOTE!</span></p>
			<span class="foo blue">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Theory</span>
			 <span class="foo purple">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Language</span>
			 <span class="foo wine" style="float: left;margin: 40px; margin-top: 2px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Optional</span>
			<div class="table-scrollable"> 
				<table class="table table-hover table-bordered viewTableDateDetails" id="sample_editable_1 myTable" border="" style="border: 2px solid grey;" >
					<thead>
						<tr>
							<th style="background-color: #efefe7">
								<p style="text-align: center;"><b>Day / Time <s:property value="academicYearsIds"/></b></p>
							</th>
							<s:iterator value="timeTablePeriodList" status="stat">
								<th style="background-color: #efefe7">
									<p style="text-align: center;"><s:property value='timeTablePeriodList[#stat.count-1][1]' /><br><s:property value='timeTablePeriodList[#stat.count-1][2]' />-<s:property value='timeTablePeriodList[#stat.count-1][3]' /></p>
								</th>
							</s:iterator>
						</tr>
					</thead>
					<tbody>
						<s:iterator value="weekDayList">
							<s:set name="weekId" value='%{id}'></s:set>
							<tr id="monthId<s:property value="id"/>" class="dayViewSubjectPeriod" >
								<td style="background-color: #efefe7">
									 <B><s:property value="%{dayName}" /></B>
								</td>
								<s:iterator value="timeTablePeriodList" status="stat">
									<s:set name="periodId" value='%{timeTablePeriodList[#stat.count-1][0]}'></s:set>
									<s:if test="%{timeTableSettings.shortBreakAfterNoOfPeriodsMorningSession>0 && #stat.count==timeTableSettings.shortBreakAfterNoOfPeriodsMorningSession+1}">
										<td id="mergeColumns" rowspan="1"  class="addRowSpanVal" style="background-color: #efefe7">
									  		<span id="subjectValue" cssStyle="width:45%">Short Break</span> 
										</td>
								   </s:if>
									<s:elseif test="%{timeTableSettings.lunchBreakAfterNoOfPeriods>0 && #stat.count==timeTableSettings.lunchBreakAfterNoOfPeriods+(timeTableSettings.shortBreakAfterNoOfPeriodsMorningSession>0?1:0)+1}">
										<td id="mergeColumns" rowspan="1"  class="addRowSpanVal" style="background-color: #efefe7">
									  		<span id="subjectValue" cssStyle="width:45%">Lunch Break</span> 
										</td>
								  	</s:elseif>
									<s:elseif test="%{timeTableSettings.shortBreakAfterNoOfPeriodsAfternoonSession>0 && #stat.count==timeTableSettings.shortBreakAfterNoOfPeriodsAfternoonSession+(timeTableSettings.shortBreakAfterNoOfPeriodsMorningSession>0?1:0)+(timeTableSettings.lunchBreakAfterNoOfPeriods>0?1:0)+1}">
										<td id="mergeColumns" rowspan="1"  class="addRowSpanVal" style="background-color: #efefe7">
									  		<span id="subjectValue" cssStyle="width:45%;">Short Break</span> 
										</td>
								   </s:elseif>
									<s:else>
										<td style="border: 1px solid #efefe7" id="monthPeriodSubjectViewId<s:property value='%{#weekId}'/><s:property value='%{#periodId}'/>" rowspan="1" class="addRowSpanVal">
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
</s:else>
</s:if>
<s:else>
	<div class="alert alert-info">
		Currently there are no timetable settings.
	</div>
</s:else>
<div class="span12"> </div>
<style type="text/css">
	.foo {
	  float: left;
	  width: 15px;
	  height: 15px;
	  margin: 35px;
	  margin-top: 2px;
	  border: 1px solid rgba(0, 0, 0, .2);
	}
	.blue {
	  background: #5789bc;
	}
	.purple {
	  background: #93c86d;
	}
	.wine {
	  background: #1eb5e5;
	}
</style>
<script type="text/javascript">
	$(document).ready(function() {
		$('.numeric').numeric();
		var accountId= '<s:property value="user.id"/>';
		var timeTableDetailsCount = '<s:property value="timeTableDetailsCount" />';
		if(accountId>0 && timeTableDetailsCount>0){
			var dataURL = jQuery.url.getChatURL("/timeTable/ajaxGetSubjectPeriodDayWiseDetails.do?tempId="+accountId+"&anyTitle=Y");
			$.ajax( {
				url : dataURL,
				cache : false,
				dataType : 'json',
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
							var inputVal;
							var nextVal;
							$('tr.dayViewSubjectPeriod:visible').each(function(){ 
								$(this).find('td').each(function(){
									if($(this).hasClass('addRowSpanVal')){
										if(isNonEmpty($(this).find('span#subjectValue').text()) && isNonEmpty($(this).find('span#subjectValue').next().text())){
											//var inputPeriodCount=$(this).find('span#isSubjectPracticalCount').text();
											var inputPeriodStatus=$(this).find('span#isSubjectPractical').text();
											var inputSubjectType=$(this).find('span#isSubjectType').text();
											Current_td = $(this);
											if("Y"==inputSubjectType){
												Current_td.css({'background-color' : '#1eb5e5','text-align' : 'center'});
											}else if("Y"==inputPeriodStatus){
												Current_td.css({'background-color' : '#93c86d','text-align' : 'center'});
											}else
												Current_td.css({'background-color' : '#5789bc','text-align' : 'center'});
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
	});
</script>