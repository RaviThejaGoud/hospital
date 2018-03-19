<%@ include file="/common/taglibs.jsp"%>
<td colspan="6">
	<input type="radio" value="exam"
		onclick="changeAcademics(this.value);" name="addGroupLeader"class="radio" checked="checked" style="vertical-align: text-bottom;"><b>Exam Results</b>
	<input type="radio" value="attendance" onclick="changeAcademics(this.value);" name="addGroupLeader" class="radio" style="vertical-align: text-bottom;"><b>Attendance</b>

<br/><br/>
<div id="examResultsDiv" class="grid_10">
	<s:if test="%{(studentMarksList != null && !studentMarksList.isEmpty()) && (examTypeList != null && !examTypeList.isEmpty()) && (teacherSubjectsList != null && !teacherSubjectsList.isEmpty())}">
		     <div id="viewClassresults" class="viewClassExam<s:property value='id'/>Results">
			</div>
			<div  id="viewClassExam<s:property value='id'/>Results" style="">
					<table class="striped" width="100%" style="margin-bottom: 0;" cellpadding="1" cellspacing="1">
						<thead>
							<tr>
								<th width="15%">
									Exam Types
								</th>
								<s:if test="%{teacherSubjectsList != null && !teacherSubjectsList.isEmpty()}">
									<s:iterator value="teacherSubjectsList">
										<th width="15%" class="head">
											<s:property value="name" />
										</th>
									</s:iterator>
								</s:if>
								<th>
									Total(%)
								</th>
								</tr>
						</thead>
						 <s:iterator value="examTypeList">
		     				<s:set name="examTypeName" value="examType"></s:set>
						<tr>
							<td width="15%" align="left">
								<b> <a href="#"><s:property value="examTypeName"/></a></b>
							</td>
							<s:set var="total" value="%{0}"  />
							<s:set var="persent" value="%{0}"/>
							<s:if test="%{studentMarksList != null && !studentMarksList.isEmpty()}">
							<s:iterator value="teacherSubjectsList">
								<s:set name="subId" value="%{id}"/>
								<td width="15%" style="text-align: center;">
								<s:iterator value="studentMarksList">
									<s:if test="%{examType == #examTypeName}">
										    <s:if test='%{#subId == subjectId}'>
										           <s:if test="%{present}">
													    <s:if test="%{obtainedMarks < minMarks}">
															<font color="red"><b><s:property value="obtainedMarks" /></b>
															</font>
														</s:if>
														<s:else>
															<s:property value="obtainedMarks" />
														</s:else>
													</s:if>
													<s:else>
													  <font color="red"><b>AB</b></font>
													</s:else>
												<s:set var="total" value="%{obtainedMarks + #total}" />
										    </s:if>
									</s:if>
								</s:iterator>
								</td>
							</s:iterator>
							</s:if>
							<td width="10%" align="left"><!--
						  		   <s:property value="%{'' + #total}"/>
						  		   <s:property value="%{'' + #totalMaxMarks}"/>  
						  		   --><s:set var="persent" value="%{(#total / (teacherSubjectsList.size*maxMarks))*100}" />
						  		   <s:property value="%{#persent}"/>
						  	 </td>
						</tr>
						 </s:iterator>
					</table>
				</div>
	</s:if>
	<s:else>
		Student marks are not uploaded
	</s:else>
</div>
<div id="attendanceDiv" style="display: none;">
	<s:if test="%{absentList != null && !absentList.isEmpty()}">
		<table class="striped" width="100%" style="margin-bottom: 0;"
			cellpadding="1" cellspacing="1">
			<tr>
				<th>
					Month Name
				</th>
				<th>
					Total working days
				</th>
				<th>
					Present
				</th>
				<th>
					Absent
				</th>
				<th>
				  %(Percentage)
				</th>
				</tr>
				<s:iterator value="absentList">
				<s:if test='%{anyId == examTypeId && selectedId == currentYear}'>
					<tr style="color: #4AA02C;">
					<td>
					<s:property value="startTime" />
					</td>
					<td>
						<s:property value="currentYear"/>
					</td>
					<td>
						<s:property value="syllabus"/>
					</td>
					<td>
						<s:property value="lastUpdatedBy"/>
					</td>
					<td>
						<s:property value="attendancePercentage"/>%
					</td>
					</tr>
					</s:if>
					<s:elseif test="%{anyId != examTypeId}">
					<tr class="loaded">
					<td>
					<s:property value="startTime" />
					</td>
					<td>
						<s:property value="currentYear"/>
					</td>
					<td>
						<s:property value="syllabus"/>
					</td>
					<td>
						<s:property value="lastUpdatedBy"/>
					</td>
					<td>
						<s:property value="attendancePercentage"/>%
					</td>
					</tr>
					</s:elseif>
				 </s:iterator>
				<tr style="color: blue;">
					<td>
						Total Attendance Percentage: 
					</td>
					<td>
						<!--<s:property value="examSchedules.totalWorkingDays"/>
					--></td>
					<td>
						<!--<s:property value="examSchedules.totalPresentDays"/>
					--></td>
					<td>
						<!--<s:property value="examSchedules.totalAbsentDays"/>
					--></td>
					<td>
						<s:property value="examSchedules.totalAttendancePercentage"/>%
					</td>
				</tr>
		</table>
 		</s:if>
 		<s:else>
 		  	Contact Administrator.
 		</s:else>
</div>
<script type="text/javascript">
function changeAcademics(staffType) {
	if (staffType == 'exam') {
		$('#examResultsDiv').show();
		$('#attendanceDiv').hide();
	} else {
		$('#examResultsDiv').hide();
		$('#attendanceDiv').show();
	}
}
</script>
</td>