<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Timetable
				</div>
			</div>
			<div class="portlet-body">
			<div class="tabbable tabbable-custom tabbable-full-width">
					<ul class="nav nav-tabs">
						<s:if test='%{!anyTitle.isEmpty()}'>
							<li>
								<a href="../<s:property value="anyTitle" />"
									target="_new">My Timetable</a>
							</li>
							
						</s:if>
						<li class="active">
							<s:url id="StaffTimeTable" action="ajaxDoViewStaffTimeTable"
								namespace="/staff">
							</s:url>
							<sj:a href="%{StaffTimeTable}" targets="mainContentDiv"
								data-toggle="tab">
									View weekly Timetable</sj:a>
						</li>
					</ul>
					<div id="studentLibraryContentDiv" class="tab-content">
					<s:if test="%{weekDayList != null && !weekDayList.isEmpty()}">
						<div id="clsStaffTimeTableDet">
							<span class="timeOfStaffId" id="<s:property value='tempId'/>"></span>
							<table
								class="table table-striped table-bordered table-hover table-full-width"
								id="sample_2">
								<thead>
									<tr>
										<th>
											Day
										</th>
										<s:set name="totalPeriods" value="%{objectList.size}" />
										<s:set name="periodsWidth" value="%{580/#totalPeriods}" />
										<s:iterator value="objectList" status="stat">
											<s:if test="#stat.odd == true ">
												<!--<div
															style="float: left; width:<s:property value='#periodsWidth'/>px;font-weight : bold;">
															<s:property value="#stat.count" />
														</div>
													-->
												<th>
													<s:property value="#stat.count" />
												</th>
											</s:if>
											<s:else>
												<th>
													<s:property value="#stat.count" />
												</th>
											</s:else>
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
												<s:if test="#stats.odd == true ">
													<td>
														<s:property value='periodType' />
													</td>
												</s:if>
												<s:else>
													<td>
														<s:property value='#periodsWidth' />
													</td>
												</s:else>
											</s:iterator>
										</tr>
									</s:iterator>
								</tbody>
							</table>
							</div>
							<table class="table table-striped table-bordered table-advance table-hover">
							  <tbody>
								<tr>
									<td class="hidden-xs">
										<label>
											M : Morning Time
										</label>
									</td>
									<td>
										<label>
											A : Afternoon Time
										</label>
									</td>
								</tr>
								 
							</tbody>
						</table>
					</s:if>
					<s:else>
						<div class="alert alert-info">
							Timetable not generated for this staff classes.
						</div>
					</s:else>
					</div>
				</div>
			</div>
		</div>
	</div>
</div> 
<script type="text/javascript">
changePageTitle("Timetable");
	$(document).ready(function() {
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