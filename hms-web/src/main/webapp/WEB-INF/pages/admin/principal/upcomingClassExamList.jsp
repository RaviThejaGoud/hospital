<%@ include file="/common/taglibs.jsp"%>
<s:if
	test="%{viewClassExamDetails != null && !viewClassExamDetails.isEmpty()}">
	<s:if test="%{examTypeList != null && !examTypeList.isEmpty()}">
			<table class="striped" width="100%" style="margin-bottom: 0;"
				cellpadding="1" cellspacing="1">
				<thead>
					<tr>
						<!--<th width="15%" class="head">
									Exam Type
								</th>-->
						<th width="15%" class="head">
							Class Name
						</th>
						<th width="25%" class="head">
							Subjects
						</th>
						<th class="head" width="15%">
							Exam Date
						</th>
						<th class="head" width="15%">
							Start Time
						</th>
						<th width="15%" class="head">
							End Time
						</th>
					</tr>
				</thead>
				<s:iterator value="examTypeList">
					<s:set name="examTypeName" value="examType"></s:set>
					<tr>
						<td colspan="5" style="text-align: center; background: #ccc">
							<label style="text-align: center">
								<b><s:property value="examTypeName" /> Schedules</b>
							</label>
						</td>
					</tr>
				<s:iterator value="viewClassExamDetails">
					<s:if test="%{examType == #examTypeName}">
						<tr class="loaded odd">
							<!--<td width="15%">
							<s:property value="examType" />
						</td>-->
							<td width="15%">
								<s:property value="className" />
							</td>
							<td width="25%">
								<s:property value="name" />
							</td>
							<td width="15%">
								<s:property value="examDateStr" />
							</td>
							<td width="15%">
								<s:property value="startTime" />
							</td>
							<td width="15%">
								<s:property value="endTime" />
							</td>
						</tr>

					</s:if>

				</s:iterator>
				</s:iterator>
			</table>
		
	</s:if>

</s:if>
<s:else>
	Exam Schedule not prepared
</s:else>