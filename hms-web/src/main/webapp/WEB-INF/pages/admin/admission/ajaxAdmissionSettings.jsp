<%@ include file="/common/taglibs.jsp"%>
<s:if test='%{tempList != null && !tempList.isEmpty()}'>
	<table
		class="table table-striped table-bordered table-hover table-full-width" >
		<thead>
			<tr>
				<th>
					Academic Year
				</th>
				<th>
					Applications Opened Date
				</th>
				<th>
					Applications Closed Date
				</th>
				<th>
					Application Fee
				</th>
				<s:if test='%{(#session.previousYear == null || #session.previousYear.isEmpty()) || (#session.previousYear == "N")}'>
				<th>
						Edit
				</th>
				</s:if>
				<th>
					Delete
				</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="tempList">
				<tr>
					<td>
						<s:property value="academicYear.academicYear" />
					</td>
					<td>
						<s:property value="applicationsStartDateStr" />
					</td>
					<td>
						<s:property value="applicationsClosedDateStr" />
					</td>
					<td>
						<s:property value="admissionApplicationFee" />
					</td>
					<s:if
						test='%{(#session.previousYear == null || #session.previousYear.isEmpty()) || (#session.previousYear == "N")}'>
						<td>
							<s:url id="editAdmissionSettinds"
								action="manageAdmissionSettings" namespace="/admin"
								escapeAmp="false">
								<s:param name="anyId" value="id"></s:param>
							</s:url>
							<sj:a href="%{editAdmissionSettinds}" targets="admissionSettingsContentDiv" cssClass="btn btn-xs purple">
								<i class="fa fa-edit"></i>Edit</sj:a>
						</td>
					</s:if>
					<td>
						<s:url id="urlRemoveAdmissionSettings"
							action="ajaxRemoveClassAdmission" includeParams="all"
							escapeAmp="false" namespace="/admin">
							<s:param name="tempId" value="%{id}"></s:param>
							<s:param name="tempId1" value="%{academicYearId}"></s:param>
						</s:url>
						<s:div cssClass="btn btn-xs red"
							onclick="javascript:confirmDialogWithTarget(this,'mainContentDiv');"
							id='%{urlRemoveAdmissionSettings}' theme="simple"
							title="Delete this admission settings"><i class="fa fa-trash-o"></i> Delete
						</s:div>
					</td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
</s:if>
<s:else>
	<div class="alert alert-info">
		Currently there are no admission settings for selected academic year.
	</div>
</s:else>
<script type="text/javascript">
TableAdvanced.init();
</script>