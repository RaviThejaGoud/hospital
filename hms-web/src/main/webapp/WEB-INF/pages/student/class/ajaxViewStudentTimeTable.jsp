<%@ include file="/common/taglibs.jsp"%>
<s:if test="%{weekDayList != null && !weekDayList.isEmpty()}">
	<s:if test="%{user.Parent}">
		<s:if
			test="%{viewStudentPersonAccountDetailsList != null && !viewStudentPersonAccountDetailsList.isEmpty()}">
			<s:if test="%{viewStudentPersonAccountDetailsList.size > 1 }">
				<div id="studentListContent">
					<div class="form-group form-horizontal">
						<label class="control-label col-md-2">
							Student Name :
						</label>
						<div class="col-md-3">
							<s:select id="sectionId"
								list="viewStudentPersonAccountDetailsList"
								listKey="classSectionId" label="Student Name"
								listValue="idAndName" name="classId" cssClass="form-control"
								theme="simple"
								onchange="javascript:getStudentTimeTable(this.value);" />
						</div><div class="spaceDiv"></div><div class="spaceDiv"></div><div class="spaceDiv"></div>
					</div>
				</div>
			</s:if>
		</s:if>
	</s:if>
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
				<s:iterator value="objectList" status="stat">
					<th>
						<s:property value="#stat.count" />
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
					<s:iterator value="objectList">
						<td>
							<div
								id="dayId<s:property value='#weekDayId'/>_pName<s:property value='periodName'/>_pType<s:property value='periodType'/>">
							</div>
						</td>
					</s:iterator>
				</tr>
			</s:iterator>
		</tbody>
	</table>
	<div class="sclTimeTableDet"></div>
</s:if>
<s:else>
	<div class="alert alert-info">
		Timetable not generated for this class.
	</div>
</s:else>
<script type="text/javascript">
	$(document).ready(function() {
	changePageTitle("Timetable");
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
	function getStudentTimeTable(classId){
		if(isNonEmpty(classId)){
			$('#studentTimeTableContent').html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
			var pars = "classId=" + classId;					
			$.ajax( {
				url : jQuery.url.getChatURL("/student/ajaxViewStudentTimeTableByClassId.do"),
				cache : true,
				data : pars,
				success : function(response) {
					$('#studentTimeTableContent').html(response);
				}
			});
		}else{
			$('#studentTimeTableContent').html("<div class='alert alert-info'>Timetable not generated for this class.</div>");
		}
	}
</script>
