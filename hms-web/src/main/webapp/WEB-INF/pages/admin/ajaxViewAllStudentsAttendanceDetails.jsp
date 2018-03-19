<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
			<div class="portlet box blue">
				<div class="portlet-title">
					<div class="caption">Attendance Information</div>
					<div class="tools">
						<a href="javascript:;" class="expand"></a>
						<a href="javascript:;" class="remove"></a>
					</div>
				</div>
				<div class="portlet-body">
					<s:if test="%{viewAllUsers.attendanceList!= null && !viewAllUsers.attendanceList.isEmpty()}">
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
								<s:iterator value="viewAllUsers.attendanceList">
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
											<s:property value="totalPresentDays" />
										</td>
										<td>
											<s:property value="totalAbsentDays" />
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
				</div>
			</div>
		</div>
	</div>
