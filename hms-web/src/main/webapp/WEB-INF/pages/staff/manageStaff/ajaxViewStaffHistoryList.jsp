<%@ include file="/common/taglibs.jsp"%>
<div class="grid_14 commomnTabs">
	<s:if test="%{newUser.staffHistory != null && !newUser.staffHistory.isEmpty()}">
		<table class="table table-striped table-bordered table-hover table-full-width" id="sample_2">
				<thead>
					<tr>
						<th>
							School Name
						</th>
						<th>
							Salary
						</th>
						<th>
							Start Date
						</th>
						<th>
							End Date
						</th>
						<th>
							Experience
						</th>
						<th>
							Other Experience
						</th>
						<th>
							Edit
						</th>
					</tr>
				</thead>
				<tbody>
				<s:iterator value="newUser.staffHistory">
					<tr>
						<td>
							<s:property value="schoolName" />
						</td>
						<td>
							<s:property value="salary" />
						</td>
						<td>
							<s:property value="startDateStr" />
						</td>
						<td>
							<s:property value="endDateStr" />
						</td>
						<td>
							<s:property value="experience" />
						</td>
						<td>
							<s:property value="otherExperience" />
						</td>
						<td>
							<s:url id="editStaff" action="ajaxDoEditStaffHistory" includeParams="all" escapeAmp="false" namespace="/staff">
								<s:param name="tempId1" value="%{id}" />
								<s:param name="tempId" value="%{newUser.id}" />
							</s:url>
							<sj:a href="%{editStaff}" targets="staffEditContentDiv"  cssClass="btn btn-xs purple" title="Edit"><i class="fa fa-edit"></i>Edit
							</sj:a>
						</td>
					
				</s:iterator>
				</tbody>
			</table>
	</s:if>
	<s:else>
		<div class="alert alert-info">
			Currently there are no staff history.
		</div>
	</s:else>
</div>