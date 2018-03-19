<%@ include file="/common/taglibs.jsp"%>
<s:if test='%{anyId == "admissionTerms"}'>
	<s:if test="%{schoolTermsList != null && !schoolTermsList.isEmpty()}">
		<table id="sample_2"
			class="table table-striped table-bordered table-hover table-full-width dataTable">
			<thead>
				<tr>
					<th>
						Term Name
					</th>
					<th>
						Start Month
					</th>
					<th>
						End Month
					</th>
					<th>
						Due Date
					</th>
					<th>
						Reminder
					</th>
					<th>
						Edit
					</th>
					<th>
						Delete
					</th>
				</tr>
				</thead>
				<tbody>
			<s:iterator value="schoolTermsList">
				<tr>
					<td>
						<s:property value="termName" />
					</td>
					<td>
						<s:property value="fromMonthName" />
					</td>
					<td>
						<s:property value="toMonthName" />
					</td>
					<td>
						<s:property value="dueDateStr" />
					</td>
					<td>
						<s:property value="noOfDays" />
					</td>
					<td>
						<s:url id="doEditSchooTerms" action="ajaxEditManageSchooTerms"
							includeParams="all" escapeAmp="false">
							<s:param name="schoolTermId" value="%{id}" />
							<s:param name="academicYearId" value="%{academicYearId}" />
							<s:param name="tempId" value="%{tempId}"></s:param>
							<s:param name="anyTitle">admissionTerms</s:param>
						</s:url>
						<sj:a href="%{doEditSchooTerms}" indicator="indicator"
							targets="admissionSettings5" cssClass="btn btn-xs purple"
							title="Edit">
							<i class="fa fa-edit"></i>Edit
						</sj:a>
					</td>
					<td>
						<s:url id="removeFeeTerms"
							action="ajaxRemoveFeeTermsForAdmissons" namespace="/schoolfee"
							includeParams="all" escapeAmp="false">
							<s:param name="schoolTerms.id" value="%{id}" />
							<s:param name="academicYearId" value="%{academicYear.id}" />
							<s:param name="tempId" value="%{tempId}"></s:param>
						</s:url>
						<s:div cssClass="btn btn-xs red"
							onclick="javascript:confirmDialogWithTarget(this,'feeSettingsContent');"
							id='%{removeFeeTerms}' theme="simple" title="Delete this term"><i class="fa fa-times">Delete</i>
						</s:div>
					</td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
	</s:if>
	<s:else>
		<div class="alert alert-info">
			Fee terms are not defined.
		</div>
	</s:else>
</s:if>
<s:else>
	<s:if test="%{schoolTermsList != null && !schoolTermsList.isEmpty()}">
		<table id="sample_3"
			class="table table-striped table-bordered table-hover table-full-width dataTable">
			<thead>
				<tr>
					<th>
						Term Name
					</th>
					<th>
						Start Month
					</th>
					<th>
						End Month
					</th>
					<th>
						Due Date
					</th>
					<th>
						Reminder
					</th>
					<th>
						Edit
					</th>
					<th>
						Delete
					</th>
				</tr>
				</thead>
				<tbody>
				<s:iterator value="schoolTermsList">
					<tr>
						<td>
							<s:property value="termName" />
						</td>
						<td>
							<s:property value="fromMonthName" />
						</td>
						<td>
							<s:property value="toMonthName" />
						</td>
						<td>
							<s:property value="dueDateStr" />
						</td>
						<td>
							<s:property value="noOfDays" />
						</td>
						<td>
							<s:if test='%{#session.previousYear == "N"}'>
								<s:url id="doEditSchooTerms" action="ajaxEditManageSchooTerms"
									includeParams="all" escapeAmp="false">
									<s:param name="schoolTermId" value="%{id}" />
									<s:param name="academicYearId" value="%{academicYearId}" />
									<s:param name="tempId" value="%{tempId}"></s:param>
									<s:param name="anyTitle">feeTerms</s:param>
								</s:url>
								<sj:a href="%{doEditSchooTerms}" indicator="indicator"
									targets="feeSettingsContent" cssClass="btn btn-xs purple"
									title="Edit">
									<i class="fa fa-edit"></i>Edit
							</sj:a>
						</s:if>
						</td>
						<td>
							<s:if test='%{#session.previousYear == "N"}'>
								<s:url id="removeFeeTerms" action="ajaxRemoveFeeTerms"
									includeParams="all" escapeAmp="false">
									<s:param name="schoolTerms.id" value="%{id}" />
									<s:param name="academicYearId" value="%{academicYear.id}" />
									<s:param name="tempId" value="%{tempId}"></s:param>
								</s:url>
								<s:div cssClass="btn btn-xs red"
									onclick="javascript:confirmDialogWithTarget(this,'feeSettingsContent');"
									id='%{removeFeeTerms}' theme="simple" title="Delete this term">
									<i class="fa fa-times"></i>Delete
							</s:div>
							</s:if>
						</td>
					</tr>
				</s:iterator>
			</tbody>	
		</table>
	</s:if>&nbsp;
	<s:else>
		<div class="alert alert-info">
			Fee terms are not defined.
		</div>
	</s:else>
</s:else>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/ems.js"> </script>
<script type="text/javascript">
$(document).ready(function() {
	TableAdvanced.init();	
});
</script>