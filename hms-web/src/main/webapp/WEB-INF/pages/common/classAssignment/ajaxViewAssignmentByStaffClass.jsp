<%@ include file="/common/taglibs.jsp"%>
<div class="col-md-4">
	<div class="form-group">
		<label class="control-label">
			<span class="required"> * </span>Select Subject :
		</label>
		<s:select list="subjectsList" id="studySubjectId" name="subjectId"
			listKey="subjectId" listValue="subjectName" headerKey=""
			headerValue="- Select Subject -" theme="simple"
			onchange="javascript:getClassSubjectsDetails(this.value);"
			cssClass="required form-control input-medium selectSubject">
		</s:select>
	</div>
</div>
<div class="row">
	<div class="col-md-12">
		<jsp:include page="/common/messages.jsp"></jsp:include>
		<s:if test="%{studentsList != null && !studentsList.isEmpty()}">
			<table
				class="table table-striped table-bordered table-hover table-full-width"
				id="sample_8">
				<thead>
					<tr>
						<th style="display: none;">
							assignmentDate
						</th>
						<th>
							Subject Name
						</th>
						<th>
							Assignment Description
						</th>
						
						<th>
							Assigned By
						</th>
						<th>
							Documents
						</th>
						<th title="Student Completion Status">
							Completion Status
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
								<s:property value="createdBy" />
							</td>
							<td>
								<s:if test='%{isDocsUpload == "Y"}'>
									<a
										href="${pageContext.request.contextPath}/admin/ajaxDownloadClassAssignmentDocs.do?classAssignment.id=<s:property value="assignmentId" />"
										class="btn btn-xs purple" title="Download"><i
										class="fa fa-edit"></i>Download </a>
								</s:if>
								<s:else>No Documents.</s:else>
							</td>
							<td>
							<s:if test='%{assignmentStatus == "C"}'>
									Submitted by all students.
								</s:if>
								<s:else> 
									<s:url id="doSendSms" action="ajaxGetStudentAssInfoDetails"
										includeParams="all" escapeAmp="false" namespace="/admin">
										<s:param name="eventId" value="%{assignmentId}"></s:param>
										<s:param name="classId" value="%{classSectionId}"></s:param>
									</s:url>
									<sj:a href="%{doSendSms}" cssClass="btn btn-xs purple"
										targets="stepImportMarkSheetDiv">
										<i class="fa fa-edit"></i> Action
										</sj:a>
								</s:else>
							</td>
							<td>
								<a data-toggle="modal" href="#manageClassAssignmentDiv"
									class="btn btn-xs purple"
									onclick="javascript:PopupManageClassAssignment(<s:property value="%{assignmentId}" />,<s:property value="%{classSectionId}" />);"><i
									class="fa fa-edit"></i>Edit </a>

							</td>
							<td>
								<s:url id="removeClassAssignment" namespace="/admin"
									action="ajaxDeleteAssignment" escapeAmp="false">
									<s:param name="tempId" value="%{assignmentId}"></s:param>
									<s:param name="classId" value="%{classSectionId}"></s:param>
								</s:url>
								<s:div cssClass="btn btn-xs red "
									onclick="javascript:confirmDialogWithTarget(this,'createAttendenceDiv');"
									id='%{removeClassAssignment}' title="Delete Class Assignment">
									<i class="fa fa-times"></i>Delete</s:div>
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
<div id="manageClassAssignmentDiv"></div>
<script type="text/javascript">
$(document).ready(function() {
	//TableAdvanced.init();
	$('#classAssignment').addClass('current');
});
function PopupManageClassAssignment(assignmentId,classSectionId) {
	var url = jQuery.url.getChatURL("/admin/ajaxDoEditStaffClassAssignment.do");
	$('#manageClassAssignmentDiv')
			.html(
					'<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
	$.ajax( {
		url : url,
		cache : false,
		data : "tempId=" + assignmentId +"&classSectionId="+classSectionId,
		success : function(html) {
			$("#manageClassAssignmentDiv").html(html);
		}
	});
}
</script>
