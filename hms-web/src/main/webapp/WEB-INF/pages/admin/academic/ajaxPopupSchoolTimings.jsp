<%@ include file="/common/taglibs.jsp"%> 
<div data-width="760" class="modal fade modal-overflow in" style="display: block; width: 760px; margin-left: -379px; margin-top: 150px;">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
		<h4 class="modal-title">View School Timings</h4>
	</div>
	<div class="modal-body">
		<s:if test="%{tempList2 != null || !tempList2.isEmpty && tempList2!= null || !tempList2.isEmpty}">
				  <div id="timingValues" class="grid_14">
				  <s:set var="classIds" value="-1"></s:set>
				  <s:set var="countNum" value="0"></s:set>
				   <s:iterator value="tempList2" status="itStatus">
				   <s:if test='%{status == "SD"}'>
				   <s:if test='%{#itStatus.count == 1}'>
				   <input type="hidden" name="hiddenVar" id="hiddenVar" value="SD"/>
				   <div class="grid_12 th" style="width: 400px;margin-left: -4px;">
					 <div class="grid_2">
					 Day
					 </div>
					 <div class="grid_2">
					  Start Time
					 </div>
					 <div class="grid_2">
					  End Time
					 </div>
					 </div>
					</s:if>
					<div class="grid_12" style="width: 400px;margin-left: -4px;">
					   <div class="grid_2" style="color: blue; font-weight: bold;">
					    <s:property value="weekName" /></div>
					    <div class="grid_2">
						<s:property value="startTime" /></div>
						<div class="grid_2">
						<s:property value="endTime" /></div>
				   </div>
					</s:if>
					<s:elseif test='%{status == "CD"}'>
					<s:if test='%{#itStatus.count == 1}'>
					<input type="hidden" name="hiddenVar" id="hiddenVar" value="CD"/>
					 <div class="grid_12 th" style="width: 1036px;margin-left: -4px;">
					   <div class="grid_2">
							Class/Day
						</div>
					    <s:iterator value="weekDayList">
						<div class="grid_2" style="width:115px;">
							<s:property value="dayName" />
						</div>
						</s:iterator>
						</div>
					 </s:if> 
					<s:if test="%{classId == #classIds}">
					<s:if test="%{weekDay == 1}">
					<div class="grid_2" style="width:115px;">
							<s:property value="startTime" />-<s:property value="endTime" /></div>
					</s:if>
					<s:elseif test="%{weekDay == 2}">
					 <div class="grid_2" style="width:115px;">
							<s:property value="startTime" />-<s:property value="endTime" /></div>
					</s:elseif>
				    <s:elseif test="%{weekDay == 3}">
				     <div class="grid_2" style="width:115px;">
							<s:property value="startTime" />-<s:property value="endTime" />
							</div>
					</s:elseif>
					<s:elseif test="%{weekDay == 4}">
					<div class="grid_2" style="width:115px;">
							<s:property value="startTime" />-<s:property value="endTime" />
					</div>
					</s:elseif>
					<s:elseif test="%{weekDay == 5}">
					<div class="grid_2" style="width:115px;">
							<s:property value="startTime" />-<s:property value="endTime" />
							</div>
					</s:elseif>
					<s:elseif test="%{weekDay == 6}">
					 <div class="grid_2" style="width:115px;">
							<s:property value="startTime" />-<s:property value="endTime" /></div>
					</s:elseif>
					<s:elseif test="%{weekDay == 7}">
					 <div class="grid_2" style="width:115px;">
							<s:property value="startTime" />-<s:property value="endTime" /></div>
					</s:elseif>
					</s:if>
					<s:else>
					<br/><br/><br/>
					<div class="grid_2 classNameShow" style="color: blue; font-weight: bold;">
					   <s:property value="className" />
					</div>
					<s:if test="%{weekDay == 1}">
					<div class="grid_2" style="width:115px;">
							<s:property value="startTime" />-<s:property value="endTime" />
					</div>
					</s:if>
					<s:elseif test="%{weekDay == 2}">
					 <div class="grid_2" style="width:115px;">
							<s:property value="startTime" />-<s:property value="endTime" /></div>
					</s:elseif>
					
				    <s:elseif test="%{weekDay == 3}">
				     <div class="grid_2" style="width:115px;">
							<s:property value="startTime" />-<s:property value="endTime" /></div>
					</s:elseif>
				
					<s:elseif test="%{weekDay == 4}">
					 <div class="grid_2" style="width:115px;">
							<s:property value="startTime" />-<s:property value="endTime" /></div>
					</s:elseif>
					
					<s:elseif test="%{weekDay == 5}">
					 <div class="grid_2" style="width:115px;">
							<s:property value="startTime" />-<s:property value="endTime" /></div>
					</s:elseif>
					
					<s:elseif test="%{weekDay == 6}">
					 <div class="grid_2" style="width:115px;">
							<s:property value="startTime" />-<s:property value="endTime" /></div>
					</s:elseif>
					
					<s:elseif test="%{weekDay == 7}">
					 <div class="grid_2" style="width:115px;">
							<s:property value="startTime" />-<s:property value="endTime" />
					 </div>
					</s:elseif>
					</s:else>
					<s:set var="classIds" value="%{classId}"></s:set>
					</s:elseif>
					<s:elseif test='%{status == "CT"}'>
					<s:if test='%{#itStatus.count == 1}'>
					<input type="hidden" name="hiddenVar" id="hiddenVar" value="CT"/>
					<div class="grid_12 th" style="width: 400px;margin-left: -4px;">
					 <div class="grid_2">
					 Class
					 </div>
					 <div class="grid_2">
					  Start Time
					 </div>
					 <div class="grid_2">
					  End Time
					 </div>
					 </div>
					</s:if>
					<div class="grid_12" style="width: 400px;margin-left: -4px;">
					   <div class="grid_2" style="color: blue; font-weight: bold;">
					    <s:property value="className" /></div>
					    <div class="grid_2">
						<s:property value="startTime" /></div>
						<div class="grid_2">
						<s:property value="endTime" /></div>
						</div>
					</s:elseif>
					<s:else>
					<input type="hidden" name="hiddenVar" id="hiddenVar" value="ST"/>
					<div class="grid_4" style="margin-left: 90px;">
				     <s:property value="startTime" />
					 -
					 <s:property value="endTime" /></div>
					</s:else>
					</s:iterator>
					</div>
					 <s:set var="classIds" value="%{classId}"></s:set>
				 </s:if>
	</div>
	<div class="modal-footer">
		<button type="button" data-dismiss="modal" class="btn default">Cancel</button>
	</div>
</div>
