<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Class Assignment Information
				</div>
			</div>
			<div class="portlet-body">
				<div id="subjectsContentDiv" class="tab-content">


					<s:if test="%{tempList1 != null && !tempList1.isEmpty()}">
						<table
							class="table table-striped table-bordered table-hover table-full-width"
							id="sample_5" style="width: 100%;">

							<thead>
								<tr>
									<th style="display: none; width: 1%;">assignmentDate</th>
									<th style="width: 20%;">SubjectName</th>
									<th style="white-space: normal; width: 49%;">Description</th>
									<th style="width: 10%;">Completion Date</th>
									<th style="width: 10%;">Documents</th>
									<th style="width: 10%">Status</th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="tempList1">
									<tr>
										<td style="display: none;"><s:property
												value="assignmentDateFormat" /></td>
										<td><s:property value="subjectName" /></td>
										<td style="white-space: normal"><s:property
												value="description" /></td>
										<td><s:property value="assignmentDateFormat" /></td>
										<td><s:if test='%{isDocsUpload == "Y"}'>
												<a
													href="${pageContext.request.contextPath}/admin/ajaxDownloadClassAssignmentDocs.do?classAssignment.id=<s:property value="id" />"
													class="btn btn-xs purple" title="Download"><i
													class="fa fa-edit"></i>Download </a>
											</s:if> <s:else>No Documents.</s:else></td>
										<td><s:property value="completionStatus" /></td>
									</tr>
								</s:iterator>
							</tbody>
						</table>


					</s:if>
					<s:else>
						<div class="alert alert-info">No information Available.</div>
					</s:else>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/onload.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		changePageTitle("Class Assignment Information");
		TableAdvanced.init();
	});
</script>
