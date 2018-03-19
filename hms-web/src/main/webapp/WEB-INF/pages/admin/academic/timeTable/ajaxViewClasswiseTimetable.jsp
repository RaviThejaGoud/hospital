<%@ include file="/common/taglibs.jsp"%>
<s:if test="%{weekDayList != null && !weekDayList.isEmpty()}">
		<h4 class="text-primary">
			Timetable
		</h4>
		<span class='selectedClassId' id='<s:property value="classId"/>'></span>
		<table
			class="table table-striped table-bordered table-hover table-full-width"
			id="sample_2">
			<thead>
				<tr>
					<th>
						Day
					</th>
					<s:set name="totalPeriods" value="%{objectList.size}" />
					<s:set name="periodsWidth" value="%{700/#totalPeriods}" />
					<s:iterator value="objectList" status="stat">
					 <th>
						<s:if test="#stat.odd == true">
							<div
								style="float: left; width:<s:property value='#periodsWidth'/>px;font-weight : bold;">
								<s:property value="#stat.count" />
							</div>
						</s:if>
						<s:else>
							<div
								style="float: left; width:<s:property value='#periodsWidth'/>px;">
								<s:property value="#stat.count" />
							</div>
						</s:else>
                      </th>
					</s:iterator>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="weekDayList">
					<s:set name="weekDayId" value="%{dayId}"></s:set>
					<tr>
						<td>
							<s:property value="dayName" />
						</td>
						<s:iterator value="objectList" status="stats">
						<td>
							<s:if test="#stats.odd == true ">
								<div style="float: left;" id="dayId<s:property value='#weekDayId'/>_pName<s:property value='periodName'/>_pType<s:property value='periodType'/>">&nbsp;
								</div>
							</s:if>
							<s:else>
								<div style="float: left;" id="dayId<s:property value='#weekDayId'/>_pName<s:property value='periodName'/>_pType<s:property value='periodType'/>">&nbsp;
								</div>
							</s:else>
							<%--<s:set name="ttPeriodName" value="%{periodName}"></s:set>	
							<s:set name="ttPeriodType" value="%{periodType}"></s:set>
							<s:if test="%{dayId == #weekDayId &&  periodName == #ttPeriodName && periodType == #ttPeriodType}">
								<div style="float: left; width:<s:property value='#periodsWidth'/>px;" id="dayId<s:property value='dayId'/>_pName<s:property value='periodName'/>_pType<s:property value='periodType'/>">
								</div>
							</s:if>
							--%></td>
						</s:iterator>
					</tr>
				</s:iterator>
			</tbody>
		</table>
</s:if>
<s:else>
	<div class="alert alert-info">
		Timetable not generated for this class.
	</div>
</s:else>
<%--<div class="grid_14">&nbsp;</div>
		<h4 class="text-primary">
			Weekly subjects count details
		</h4>
	<s:if test="%{viewClassSubjectsSettings != null && !viewClassSubjectsSettings.isEmpty()}">
	<table
			class="table table-striped table-bordered table-hover table-full-width"
			id="sample_2">
			<thead>
				<tr>
					<th>
						Subject
					</th>
					<th>
						Max. periods count
					</th>
					<th>
						Assigned periods count
					</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="viewClassSubjectsSettings">
					<s:set name="weekDayId" value="%{dayId}"></s:set>
					<tr>
					<s:if test="%{periodsPerWeek > 0}">
					<tr>
						<td>
								<s:property value="subjectName"/>
						</td>
						<td>
								<s:property value="periodsPerWeek"/>
						</td>
						<td>
								<s:property value="totalAssignedSubjectPeriodsCount"/>
						</td>
				  </tr>
				  </s:if>
				</tr>
				</s:iterator>
			</tbody>
		</table>
		</s:if>
	<s:else>
	<div class="alert alert-info">
				No subjects found for this class.
			</div>
	</s:else>
--%><script type="text/javascript">
	$(document).ready(function() {
	TableAdvanced.init();
		var classSectionId = $('span.selectedClassId').attr('id');
		if(isNonEmpty(classSectionId)){
			var url = jQuery.url.getChatURL("/admin/ajaxGetClassTimeTable.do?classSectionId="+classSectionId);
			$.ajax({
			url : url,
			cache : false,
			dataType : 'json',
			success : function(response) {
				var timeTableList=response.objectList;
				var weekDayId='';
				var weekPeriodName='';
				var weekPeriodType='';
				var total=0;
				if(timeTableList){
						for ( var i = 0; i < timeTableList.length; i++) {
								weekDayId = timeTableList[i].dayId;
								weekPeriodName = timeTableList[i].periodName;
								weekPeriodType = timeTableList[i].periodType;
								// alert("weekDayId...:"+weekDayId+"---weekPeriodName...:"+weekPeriodName+'...weekPeriodType..:'+weekPeriodType);
								if(isNonEmpty(timeTableList[i].subjectName))
									$('#dayId'+weekDayId+"_pName"+weekPeriodName+"_pType"+weekPeriodType).html(timeTableList[i].subjectName);
								else
									$('#dayId'+weekDayId+"_pName"+weekPeriodName+"_pType"+weekPeriodType).html("-");
						}
					}
				}
			});
		}
	});
</script>
