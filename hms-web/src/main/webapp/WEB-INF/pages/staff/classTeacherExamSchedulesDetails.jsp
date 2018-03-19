<%@ include file="/common/taglibs.jsp"%>
<div class="block grid_12">
	<div class="block_head">
		<h2>
			Exam Schedule Details
		</h2>
		</div>
<div class="block_content">
		<s:if test="%{viewClassExamDetails != null && !viewClassExamDetails.isEmpty()}">
			<table width="100%">
				<thead>
					<tr>
						<th width="20%" class="head">
							Exam Type
						</th>
						<!-- <th width="11%" class="head">
							Class Name
						</th> -->
						<th width="22%" class="head">
							Subjects
						</th>
						<th class="head" width="10%">
							Exam Date
						</th>
						<th class="head" width="12%">
							Start Time
						</th>
						<th width="10%" class="head">
							End Time
						</th>
					</tr>
				</thead>
				<s:iterator value="viewClassExamDetails">
					<tr>
						<td>
							<s:property value="examType"/>
						</td>
						<!-- <td>
							<s:property value="className" />-<s:property value="section" />
						</td> -->
						<td>
							<s:property value="name" />
						</td>
						<td>
							<s:property value="examDateStr" />
						</td>
						<td>
							<s:property value="startTime" />
						</td>
						<td>
							<s:property value="endTime" />
						</td>
					</tr>
				</s:iterator>
			</table>
		</s:if>
		<s:else>
		Exam Schedule not prepared
	</s:else>
</div>
</div>
<script type="text/javascript">
	changePageTitle("Exam Schedules & Results");
  </script>