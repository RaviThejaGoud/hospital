<%@ include file="/common/taglibs.jsp"%>
<fieldset id="stepAttendance">
	<s:if test="%{objectList!= null && !objectList.isEmpty()}">
		<table
			class="table table-striped table-bordered table-hover table-full-width"
			id="sample_2">
			<thead>
				<tr>
					<th>
						Month Name
					</th>
					<th>
						Total Working Days
					</th>
					<th>
						Attendance Submitted Days
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
			</thead>
			<tbody>
				<s:iterator value="objectList">
					<tr>
						<td>
							<s:property value="monthName" />
						</td>
						<td>
							<s:property value="totalWorkingDays" />
						</td>
						<td>
							<s:property value="totalAttendancePercentage" />
						</td>
						<td>
							<s:if test="%{totalPresentDays > 0}">
									<s:property value="totalPresentDays" />
							</s:if>
							<s:elseif test="%{attTotalPresentDays > 0.0}">
									<s:property value="attTotalPresentDays" />
							</s:elseif>
							<s:else>
								0
							</s:else>
						</td>
						<td>
							<s:if test="%{totalAbsentDays > 0}">
								<s:property value="totalAbsentDays" />
							</s:if>
							<s:elseif test="%{attTotalAbsentDays > 0.0}">
									<s:property value="attTotalAbsentDays" />
							</s:elseif>
							<s:else>
								0
							</s:else>
						</td>
						<td>
							<s:property value="attendancePercentage" />
						</td>
					</tr>
				</s:iterator>
			</tbody>
		</table>
	</s:if>
	<s:else>
		<div class="examTabBorder">
			<div class="alert alert-info">
				Attendance data not found for this student.
			</div>
		</div>
	</s:else>
</fieldset>
<script type="text/javascript">
TableAdvanced.init();
</script>