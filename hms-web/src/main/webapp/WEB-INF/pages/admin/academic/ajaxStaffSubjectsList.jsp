<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/ems.js"></script>
<s:if
	test="%{teacherSubjectsList != null && !teacherSubjectsList.isEmpty()}">
<table class="stripped" width="100%" >
			<thead class="innerTable">
				<tr>
					<th>
						Class Name
					</th>
					<th >
						Subject
					</th>	
					<th >
						Class Teacher
					</th>	
					<th >
						Teacher Name
					</th>				
				</tr>
			</thead>
			<s:iterator value="teacherSubjectsList">
				<tr id="removeAttendance<s:property value='id' />" class="innerTable loaded">
					<td >
						<s:url id="removeAttendance" action="ajaxRemoveClassSubject"
							escapeAmp="false">
							<s:param name="id" value="id"></s:param>
						</s:url>
						<s:div cssClass="close emsRemove" id='%{removeAttendance}' theme="simple" title="Delete this group"></s:div>
						<s:property value="studyClass.className"/>-<s:property value="studyClass.section"/>
					</td>
					<td>
					   <s:property value="studySubject.name"/>
					</td>
					<td>
					   <s:property value="isClassTeacher"/>
					</td>
					<td>
					   <s:property value="staff.account.person.personFullName"/>
					</td>
					</tr>
			</s:iterator>
		</table>
		</s:if>