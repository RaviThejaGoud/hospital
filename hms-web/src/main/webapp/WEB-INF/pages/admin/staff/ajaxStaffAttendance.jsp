<%@ include file="/common/taglibs.jsp"%>
<s:if test="%{objectList!= null && !objectList.isEmpty()}">
	<table class="table table-striped table-bordered table-hover table-full-width" id="sample_2">
		<thead>
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
	<div class="alert alert-info">
		 Attendance data not found for this Staff.
	</div>
</s:else>
<script type="text/javascript">
TableAdvanced.init();
</script>
