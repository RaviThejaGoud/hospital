<%@ include file="/common/taglibs.jsp"%>
<s:if test="%{weekDayList != null && !weekDayList.isEmpty()}">
		<div id="sclStaffTimeTableDet">
		<span class="timeOfStaffId" id="<s:property value='tempId'/>"></span>
		<table class="table table-striped table-bordered table-hover table-full-width" id="sample_2">
		<thead>
			<tr>
				<th>
					Day
				</th>
				<s:iterator value="objectList" status="stat">
				<th>
					<s:if test="#stat.odd == true ">
						<div style="float: left; width:<s:property value='#periodsWidth'/>px;font-weight : bold;">
							<s:property value="#stat.count"/>
						</div>
					</s:if>
					<s:else>
						<div style="float: left; width:<s:property value='#periodsWidth'/>">
							<s:property value="#stat.count"/>
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
								<s:property value="dayName"/> 
					</td>
					<s:iterator value="objectList"  status="stats">
					<td>
						<s:if test="#stats.odd == true ">
							<div style="float: left; width:<s:property value='#periodsWidth'/>px;font-weight : bold;" id="dayId<s:property value='#weekDayId'/>_pName<s:property value='periodName'/>_pType<s:property value='periodType'/>">&nbsp;</div>
						</s:if>
						<s:else>
							<div style="float: left; width:<s:property value='#periodsWidth'/>px;" id="dayId<s:property value='#weekDayId'/>_pName<s:property value='periodName'/>_pType<s:property value='periodType'/>">&nbsp;</div>
						</s:else>
						</td>
						</s:iterator>
				</tr>
			</s:iterator>
		</tbody>
	</table>
			<!--<div class="grid_14 th">
				<div class="grid_2">
					Day
				</div>
				<s:set name="totalPeriods" value="%{objectList.size}"/>
				<s:set name="periodsWidth" value="%{700/#totalPeriods}"/>
				<s:iterator value="objectList" status="stat">
					<s:if test="#stat.odd == true ">
						<div style="float: left; width:<s:property value='#periodsWidth'/>px;font-weight : bold;">
							<s:property value="#stat.count"/>
						</div>
					</s:if>
					<s:else>
						<div style="float: left; width:<s:property value='#periodsWidth'/>">
							<s:property value="#stat.count"/>
						</div>
					</s:else>
				</s:iterator>
			</div>
				<s:iterator value="weekDayList">
					<s:set name="weekDayId" value="%{dayId}"></s:set>
					<div class="grid_14 row">
						<div class="grid_2">
								<s:property value="dayName"/> 
						</div>
						<s:iterator value="objectList"  status="stats">
						<s:if test="#stats.odd == true ">
							<div style="float: left; width:<s:property value='#periodsWidth'/>px;font-weight : bold;" id="dayId<s:property value='#weekDayId'/>_pName<s:property value='periodName'/>_pType<s:property value='periodType'/>">&nbsp;</div>
						</s:if>
						<s:else>
							<div style="float: left; width:<s:property value='#periodsWidth'/>px;" id="dayId<s:property value='#weekDayId'/>_pName<s:property value='periodName'/>_pType<s:property value='periodType'/>">&nbsp;</div>
						</s:else>
						</s:iterator>
						</div>
				</s:iterator>
				<div class="sclStaffTimeTableDet paginator grid_14 thb"></div>
			--></div>
		</s:if>
		<s:else>
			<div class="alert alert-info">
				Time table not generated for this staff classes.
			</div>
		</s:else>
<script type="text/javascript">
	$(document).ready(function() {
	TableAdvanced.init();
		var staffId = $('span.timeOfStaffId').attr('id');
		if(isNonEmpty(staffId)){
			var url = jQuery.url.getChatURL("/common/ajaxGetStaffTimeTableDetails.do?tempId="+staffId);
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
				var periodDetails = '';
				if(timeTableList){
						for ( var i = 0; i < timeTableList.length; i++) {
								weekDayId = timeTableList[i].dayId;
								weekPeriodName = timeTableList[i].periodName;
								weekPeriodType = timeTableList[i].periodType;
								if(isNonEmpty(timeTableList[i].subjectName)){
									periodDetails = $('#dayId'+weekDayId+"_pName"+weekPeriodName+"_pType"+weekPeriodType).html();
									if(isNonEmpty(periodDetails) && '&nbsp;' != periodDetails){
										$('#dayId'+weekDayId+"_pName"+weekPeriodName+"_pType"+weekPeriodType).html(periodDetails+',<br/>'+timeTableList[i].classAndSection+' : '+timeTableList[i].subjectName);
									}else
										$('#dayId'+weekDayId+"_pName"+weekPeriodName+"_pType"+weekPeriodType).html(timeTableList[i].classAndSection+' : '+timeTableList[i].subjectName);
								}
								else
									$('#dayId'+weekDayId+"_pName"+weekPeriodName+"_pType"+weekPeriodType).html("-");
						}
					}
				}
			});
		}
	});
</script>
