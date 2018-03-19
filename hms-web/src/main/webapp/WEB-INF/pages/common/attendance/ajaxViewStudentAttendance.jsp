<%@ include file="/common/taglibs.jsp"%>
<jsp:include page="/common/messages.jsp"></jsp:include>
<div id="commonContent">
			<s:if test="%{studentAttendanceStatusList != null && !studentAttendanceStatusList.isEmpty()}">
				  <table class="striped" width="100%" cellpadding="1" cellspacing="1">
					<thead id="attendanceHead">
					<s:if test='%{autoCheck == "Y"}'>
						<tr >
						 <th width="60%" class="head">
								Date
							</th>
						    <th width="60%" class="head">
								Day
							</th>
							<th width="60%" class="head">
								Description
							</th>
							<th></th>
							<th></th>
							<th></th>
							<th></th>
							
						</tr>
						</s:if>
						<s:else>
							<tr>
						 <th class="head">
								Attendance
							</th>
						    <th class="head">
								Class
							</th>
							<th width="21%" class="head">
								Total Students
							</th>
							<th width="10%" class="head">
								 Present
							</th>
							<th class="head" width="10%">
								 Absent
							</th>
							<s:if test='%{(#session.academicYear == null || #session.academicYear.isEmpty()) || (#session.previousYear=="N")}'>
							<th class="head">
								Edit
							</th>
							</s:if>
						</tr>
						</s:else>
					</thead>
					<s:iterator value="studentAttendanceStatusList">
					
					
					<s:if test='%{holiday == "Y"}'>
					  
					<tr style="color: red;">
					<td>
					<s:property value="attendanceDateStr"/>
					</td>
					<td>
					<s:property value="holidayDescription"/>
					</td>
					<td>
					is Holiday
					</td>
					<td></td>
					<td></td>
					<td></td>
					</tr>
					</s:if>
					<s:elseif test='%{fullPresent == "Y"}'>
					 <tr class="loaded"> 
						    <td>
								<s:property value="attendanceDateStr"/>
							</td>
							<td>
								<s:property value="anyTitle"/>-<s:property value="classSection"/>
							</td>
							<td>
								<s:property value="studentSize" />
							</td>
							<s:if test="%{absentSize==0}">
							<td>
								<s:property value="studentSize" />
							</td>
							</s:if>
							<s:else>
							<td>
							<s:property value="presentSize" />
							</td>
							</s:else>
							<td>
							<s:property value="absentSize" />
							</td>
							<s:if test='%{(#session.academicYear == null || #session.academicYear.isEmpty()) || (#session.previousYear=="N")}'>
							<td>
							<s:if test="%{absentSize!=0}">
							
								<s:url id="editAttendance" action="ajaxEditStudentAttendance"
									includeParams="all" escapeAmp="false">
									<s:param name="selectedId" value="%{studyClass.id}" />
									<s:param name="selectedDate" value="%{attendanceDateStr}" />
								</s:url>
								<sj:a href="%{editAttendance}"
									onCompleteTopics="ajaxStudentAttendance" indicator="indicator"
									targets="editFullPresentStudents%{attendanceDateStr}"  cssClass="normalEdit" title="Edit" onBeforeTopics="cleanOpenRows">
								</sj:a>
								</s:if>
								<s:else>
								<s:url id="editAttendance" action="ajaxEditFullPresentStudentAttendance"
									includeParams="all" escapeAmp="false">
									<s:param name="selectedId" value="%{studyClass.id}" />
									<s:param name="selectedDate" value="%{attendanceDateStr}" />
								</s:url>
								<sj:a href="%{editAttendance}"
									onCompleteTopics="ajaxStudentAttendance" indicator="indicator"
									targets="editFullPresentStudents%{attendanceDateStr}"  cssClass="normalEdit" title="Edit" onBeforeTopics="cleanOpenRows">
								</sj:a>
								</s:else>
							</td>
							</s:if>
						</tr>
						<tr style="display: none" id='editFullPresentStudents<s:property value="attendanceDateStr"/>'	class='load'>
						</tr>
					</s:elseif>
					<s:else>
						<tr class="loaded"> 
						    <td>
								<s:property value="attendanceDateStr"/>
							</td>
							<td>
								<s:property value="anyTitle"/>-<s:property value="classSection"/>
							</td>
							<td>
								<s:property value="studentSize" />
							</td>
							<s:if test="%{absentSize==0}">
							<td>
								<s:property value="studentSize" />
							</td>
							</s:if>
							<s:else>
							<td>
							<s:property value="presentSize" />
							</td>
							</s:else>
							<td>
								<s:property value="absentSize" />
								<s:if test="%{absentSize>0}">
								<s:url id="doViewAbsentAttendance" action="ajaxViewAbsentStudentAttendance"
									includeParams="all" escapeAmp="false">
									<s:param name="classId" value="%{studyClass.id}" />
									<s:param name="selectedId" value="%{selectedId}" />
									<s:param name="selectedDate" value="%{attendanceDateStr}" />
								</s:url>
								<sj:a href="%{doViewAbsentAttendance}"
									onCompleteTopics="ajaxStudentAttendance" indicator="indicator"
									targets="dynamicFormCancel%{id}" onBeforeTopics="cleanOpenRows">
									View
								</sj:a>
								</s:if>
							</td>
							<td>
								<s:url id="editAttendance" action="ajaxEditStudentAttendance"
									includeParams="all" escapeAmp="false">
									<s:param name="classId" value="%{studyClass.id}" />
									<s:param name="selectedId" value="%{selectedId}" />
									<s:param name="selectedDate" value="%{attendanceDateStr}" />
								</s:url>
								<sj:a href="%{editAttendance}"
									onCompleteTopics="ajaxStudentAttendance" indicator="indicator"
									targets="dynamicFormCancel%{id}" onBeforeTopics="cleanOpenRows"  cssClass="normalEdit" title="Edit">
								</sj:a>
							</td>
						</tr>
						</s:else>
				       <tr style="display: none" id='dynamicFormCancel<s:property value="id"/>'	class='load'>
						</tr>
						</s:iterator>
				</table>
		</s:if>
		<s:else>
		There is no attendance for student.Please create the attendance.
		</s:else>
  </div>
<script type="text/javascript">
	document.title = 'Manage Student Attendance';
	$.subscribe('ajaxStudentAttendance', function(event, data) {
		if ($('#' + data.id).is(":hidden")) {
			$('#' + data.id).show();
		} else {
			$('#' + data.id).hide();
		}
	});
</script>