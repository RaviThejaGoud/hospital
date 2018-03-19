<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/messages.jsp"%>
<div class="form-body">
	<s:form id="selectStudentForm" cssClass="form-horizontal"
		theme="simple">
		<s:if test="%{objectList != null && !objectList.isEmpty()}">
			<table
				class="table table-striped table-bordered table-hover table-full-width"
				id="sample_2">
				<thead>
					<tr>
						<th>Student Name</th>
						<th>Admission Number</th>
						<th>Class & Section</th>
						<th>Parent Name</th>
						<th>Mobile Number</th>
						<th>View</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="objectList">
						<tr>
							<td><s:property value="personFullName" /></td>
							<td><s:property value="admissionNumber" /></td>
							<td><s:property value="classAndSection" /></td>
							<td><s:property value="fatherName" /></td>
							<td><s:property value="mobileNumber" /></td>
							<td>
								<s:url id="deleteInVoiceId" namespace="/schoolfee" action="ajaxGetStudentByAdmissionNumberForDeleteInvoice" includeParams="all" escapeAmp="false">
									<s:param name="tempId" value="%{custId}" />
									<s:param name="anyId" value="%{studId}" />
									<s:param name="tempId1" value="%{academicYearId}" />
								</s:url> <sj:a href="%{deleteInVoiceId}" targets="viewDeleteInvoiceStudentDivId"> View </sj:a>
							</td>
						</tr>
					</s:iterator>
				</tbody>
			</table>
		</s:if>
		<s:else>
			<div class="alert alert-info">Currently there are no students.</div>
		</s:else>
	</s:form>
	
	<div id="viewDeleteInvoiceStudentDivId"></div>
</div>
