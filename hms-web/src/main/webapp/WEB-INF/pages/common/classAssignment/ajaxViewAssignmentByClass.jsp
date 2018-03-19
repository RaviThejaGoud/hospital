<%@ include file="/common/taglibs.jsp"%>
<div class="col-md-4">
	<div class="form-group">
		<label class="control-label">
			<span class="required"> * </span>Select Subject :
		</label>
		<s:select list="subjectsList" id="studySubjectId" name="subjectId"
			listKey="subjectId" listValue="subjectName" headerKey=""
			headerValue="- All Subject -" theme="simple"
			onchange="javascript:getClassSubjectsDetails(this.value);"
			cssClass="required form-control input-medium selectSubject">
		</s:select>
	</div>
</div>
<div class="row">
	<div class="col-md-12">
	<jsp:include page="/common/messages.jsp" />
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
							Description
						</th>
						
						<th>
							Completion Date
						</th>
						<th>
							Created By
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
								<s:property value="assignmentDateYYMMStr" />
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
									<s:if test='%{user.isParent=="Y" || user.isSchoolTeacher=="Y" || user.isSchoolAsstStaff=="Y" || user.isSchoolStudent=="Y"}'>
										<s:url id="doSendSms" action="ajaxDoGetStudentDetails"
											includeParams="all" escapeAmp="false" namespace="/admin">
											<s:param name="eventId" value="%{assignmentId}"></s:param>
											<s:param name="classId" value="%{classSectionId}"></s:param>
										</s:url>
										<sj:a href="%{doSendSms}" onCompleteTopics="doSendSms1"
											cssClass="btn btn-xs purple" onBeforeTopics="cleanOpenDivs"
											indicator="indicator" targets="stepImportMarkSheetDiv">
											<i class="fa fa-edit"></i>Action
											</sj:a>
									</s:if>
									<s:else>
										<s:url id="doSendSms" action="ajaxDoGetStudentDetails"
											includeParams="all" escapeAmp="false" namespace="/admin">
											<s:param name="eventId" value="%{assignmentId}"></s:param>
											<s:param name="classId" value="%{classSectionId}"></s:param>
											
										</s:url>
										<sj:a href="%{doSendSms}" onCompleteTopics="doSendSms1"
											cssClass="btn btn-xs purple" onBeforeTopics="cleanOpenDivs"
											indicator="indicator" targets="addClassAssignmentDiv">
											<i class="fa fa-edit"></i>Action
											</sj:a>
									</s:else>
								 </s:else>	 
							</td>
							<td>
								<s:url id="editStudySubject" action="ajaxDoEditClassAssignment" includeParams="all" escapeAmp="false" namespace="/admin">
										<s:param name="tempId" value="%{assignmentId}"></s:param>
										<s:param name="classSectionId" value="%{classSectionId}"></s:param>
										<s:param name="plTitle" value="%{plTitle}"></s:param>
								</s:url>
								<sj:a href="%{editStudySubject}" targets="addClassAssignmentDiv" cssClass="btn btn-xs purple" title="Edit"> 
									<i class="fa fa-edit"></i>Edit
								</sj:a>
								<%-- <a data-toggle="modal" href="#responsive"
									class="btn btn-xs purple"
									onclick="javascript:PopupClasssAssignmentDetials(<s:property value="%{assignmentId}" />,<s:property value="%{classSectionId}" />,'<s:property value="%{plTitle}" />');"><i
									class="fa fa-edit"></i>Edit </a> --%>
							</td>
							<td>
								<s:if test='%{#session.previousYear == "N"}'>
									<s:url id="removeStudySubject" action="ajaxDeleteAssignment"
										includeParams="all" escapeAmp="false" namespace="/admin">
										<s:param name="tempId" value="%{assignmentId}"></s:param>
										<s:param name="classId" value="%{classSectionId}"></s:param>
									</s:url>
									<s:div cssClass="btn btn-xs red"
										onclick="javascript:confirmDialogWithTarget(this,'createClassAssignmentDiv');"
										id='%{removeStudySubject}' title="Delete this subject">
										<i class="fa fa-times"></i>Delete
							</s:div>
								</s:if>
							</td>
						</tr>
					</s:iterator>
				</tbody>
			</table>
		</s:if>
		<s:else>
			<div class="alert alert-info">
				No assignments are added for this class and subjects.
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
function PopupClasssAssignmentDetials(id,classSectionId,title) {
var url = jQuery.url.getChatURL("/admin/ajaxDoEditClassAssignment.do");
$('#responsive').html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
$.ajax( {
		url : url,
		cache : false,
		data : "tempId=" + id+"&classSectionId="+classSectionId+"&plTitle="+title,
		success : function(html) {
			$("#responsive").html(html);
		}
	});
}
</script> 
