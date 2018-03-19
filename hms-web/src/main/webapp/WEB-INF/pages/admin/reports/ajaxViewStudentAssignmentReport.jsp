<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
	<jsp:include page="/common/messages.jsp" />
		<s:if test="%{studentsList != null && !studentsList.isEmpty()}">
			<table
				class="table table-striped table-bordered table-hover table-full-width"
				id="sample_5">
				<thead>
					<tr>
					
						<th style="display: none;">
							assignmentDate
						</th>
						<th>
							Subject Name
						</th>
						<th>
							Description
						</th>
						<th>
							Completion Date
						</th>
						<th>
							Download
						</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="studentsList">
						<tr>
							<td style="display: none;">
								<s:property value="assignmentDate" />
							</td>
							<td>
								<s:property value="subjectName" />
							</td>
							<td>
								<s:property value="assignmentDescription" />
							</td>
							<td>
								<s:property value="assignmentDateStr" />
							</td>
							<td>
							
							
							<a href="${pageContext.request.contextPath}/reports/ajaxDownloadStudentAssignmentReport.do?viewClassAssignmentDetails.classSectionId=<s:property value="classSectionId" />&viewClassAssignmentDetails.subjectId=<s:property value="subjectId" />&viewClassAssignmentDetails.assignmentId=<s:property value="assignmentId" />&plTitle=Daily Assignment"
								class="btn btn-xs purple" title="Download"><i
								class="fa fa-edit"></i>Download </a>
							
							
							<%-- <a href="${pageContext.request.contextPath}/reports/ajaxDownloadStudentAssignmentReport.do?viewClassAssignmentDetails.assignmentId=<s:property value="assignmentId" />&plTitle=Daily Assignment"
								class="btn btn-xs purple" title="Download"><i
								class="fa fa-edit"></i>Download </a> --%>
									
							</td>
						</tr>
					</s:iterator>
				</tbody>
			</table>
		</s:if>
		<s:else>
			<div class="alert alert-info">
				No Assignments are added for this class.
			</div>
		</s:else>
	</div>
</div>
<div id="responsive"></div>
<script type="text/javascript">
$(document).ready(function() {
	TableAdvanced.init();
	UIExtendedModals.init();
});
</script> 
