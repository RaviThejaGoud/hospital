<%@ include file="/common/taglibs.jsp"%>
<jsp:include page="/common/messages.jsp"></jsp:include>
<s:if
	test="%{objectList != null && !objectList.isEmpty()}">
	<table
		class="table table-striped table-bordered table-hover table-full-width"
		id="sample_2">
		<thead>
			<tr>
				<th>
					Name 
				</th>
				<th>
					Mobile
				</th>
				<th>
					E-mail
				</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="objectList">
				<tr>
					<td>
					<s:if test='%{(firstName != null && !firstName.isEmpty()) || (lastName != null && !lastName.isEmpty()) }'>
						<s:property  value="firstName" />&nbsp;&nbsp;<s:property  value="lastName" />
					</s:if>
					<s:else>
					 -
					</s:else>
						
					</td>
					<td>
						<s:if test='%{(roleDescription=="Student") && (studentMobile != null && !studentMobile.isEmpty()) }'>
							<s:property value="studentMobile" />&nbsp;&nbsp;
								<s:url id="urlSchoolcustomersSMSIds"
									action="ajaxSendLoginCredetialsfromSupport" namespace="/masterAdmin"
									includeParams="all" escapeAmp="false">
									<s:param name="anyTitle" value="%{accountId}"></s:param>
									<s:param name="tempString">S</s:param>
									<s:param name="anyId" value="%{studentMobile}"></s:param>
								</s:url> 
								<sj:a href="%{urlSchoolcustomersSMSIds}" targets="viewSchoolInfoDiv" cssClass="btn btn-xs green">
									Send SMS </sj:a>
						</s:if>
						<s:elseif test='%{(roleDescription!="Student") && (mobileNumber != null && !mobileNumber.isEmpty()) }'>
							<s:property value="mobileNumber" />&nbsp;&nbsp;
								<s:url id="urlSchoolcustomersSMSIds"
									action="ajaxSendLoginCredetialsfromSupport" namespace="/masterAdmin"
									includeParams="all" escapeAmp="false">
									<s:param name="anyTitle" value="%{accountId}"></s:param>
									<s:param name="tempString">S</s:param>
									<s:param name="anyId" value="%{mobileNumber}"></s:param>
								</s:url> 
								<sj:a href="%{urlSchoolcustomersSMSIds}" targets="viewSchoolInfoDiv" cssClass="btn btn-xs green">
									Send SMS </sj:a>
						</s:elseif>
						<s:else>
							-
						</s:else>
					</td>
						<td>
						<s:if test='%{(roleDescription=="Student") && (studentEmail != null && !studentEmail.isEmpty()) }'>
							<s:property value="studentEmail" />&nbsp;&nbsp;
								<s:url id="urlSchoolcustomersEmailIds"
									action="ajaxSendLoginCredetialsfromSupport" namespace="/masterAdmin"
									includeParams="all" escapeAmp="false">
									<s:param name="anyTitle" value="%{accountId}"></s:param>
									<s:param name="tempString">E</s:param>
									<s:param name="anyId" value="%{studentEmail}"></s:param>
								</s:url> 
								<sj:a href="%{urlSchoolcustomersEmailIds}" targets="viewSchoolInfoDiv" cssClass="btn btn-xs green">
									Send E-mail</sj:a>
						</s:if>
						<s:elseif test='%{(roleDescription=="Parent") && (parentEmail != null && !parentEmail.isEmpty())}'>
							<s:property value="parentEmail" />&nbsp;&nbsp;
								<s:url id="urlSchoolcustomersEmailIds"
									action="ajaxSendLoginCredetialsfromSupport" namespace="/masterAdmin"
									includeParams="all" escapeAmp="false">
									<s:param name="anyTitle" value="%{accountId}"></s:param>
									<s:param name="tempString">E</s:param>
									<s:param name="anyId" value="%{parentEmail}"></s:param>
								</s:url> 
								<sj:a href="%{urlSchoolcustomersEmailIds}" targets="viewSchoolInfoDiv" cssClass="btn btn-xs green">
									Send E-mail</sj:a>
						</s:elseif>
						<s:elseif test='%{(roleDescription!="Parent" && roleDescription!="Student") && (staffEmail != null && !staffEmail.isEmpty())}'>
							<s:property value="staffEmail" />&nbsp;&nbsp;
							<s:url id="urlSchoolcustomersEmailIds"
									action="ajaxSendLoginCredetialsfromSupport" namespace="/masterAdmin"
									includeParams="all" escapeAmp="false">
									<s:param name="anyTitle" value="%{accountId}"></s:param>
									<s:param name="tempString">E</s:param>
									<s:param name="anyId" value="%{staffEmail}"></s:param>
								</s:url> 
								<sj:a href="%{urlSchoolcustomersEmailIds}" targets="viewSchoolInfoDiv" cssClass="btn btn-xs green">
									Send E-mail</sj:a>
						</s:elseif>
						<s:else>
							-
						</s:else>
						</td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
</s:if>
<s:else>
	<div class="alert alert-info">
		Currently there are no details.
	</div>
</s:else>


