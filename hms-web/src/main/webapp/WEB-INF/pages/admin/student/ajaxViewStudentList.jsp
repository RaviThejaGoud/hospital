<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/ems.js"> </script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/subscription/paginator.js"> </script>
<s:if test="%{classStudentsList != null && !classStudentsList.isEmpty()}">
<div><a href='${pageContext.request.contextPath}/common/printAllStudentAttendanceAndExamResults.do?studyClassId=<s:property value="studyClassId"/>'><img
				alt="Print" title="Print All Students Score Card" src="${pageContext.request.contextPath}/images/common/printer.png" align="right"></a></div>
<div class="grid_13">	
		<div class="grid_14 header" id="results">
				<div class="grid_2">
					Image
				</div>
				<div class="grid_3">
					Student Name
				</div>
				<div class="grid_3">
					Father Name
				</div>
				<div class="grid_1">
					Class
				</div>
				<div class="grid_3">
					Contact Number
				</div>
				<div class="grid_1">
					Email
				</div>
				<div class="grid_1">
					View/Edit
				</div>
         </div>
         <s:iterator value="classStudentsList">
        	 <div class="grid_14 normal" id="results">
					<div class="grid_2" >
						<s:if
							test="%{newUser.profileImage.adjustedAttachmentFilePath != null && !newUser.profileImage.adjustedAttachmentFilePath.isEmpty()}">
							<img
								src='<c:url value="${newUser.profileImage.adjustedAttachmentFilePath}"/>'
								alt='<s:property  value="ViewStudentPersonAccountDetails.personFullName" />'
								align="left" height="25px" width="50px" border="0" />
						</s:if>
						<s:else>
							<img src='<c:url value="${adjustedAttachmentFilePath}"/>'
								alt='<s:property  value="viewStudentDetails.personName" />'
								align="left" height="25px" width="50px" border="0" />
						</s:else>
					</div>
					<div class="grid_3" >
						<s:property value="firstName" />
							&nbsp;
						<s:property value="lastName" />
					</div>
					<div class="grid_3">
						<s:property value="fatherName" />
					</div>
					<div class="grid_1">
						 <s:property value="className" />
							-
						<s:property value="section" />
					</div>
					<div class="grid_3">
						<s:property value="mobileNumber" />
					</div>
					<div class="grid_1">
						<s:url id="editStaff" action="ajaxDoSendEmail"
								includeParams="all" escapeAmp="false" namespace="/staff">
							<s:param name="id" value="{id}" />
							<s:param name="parentEmail" value="%{parentEmail}" />
						</s:url>
						<sj:a href="%{editStaff}" targets="editStudentForm%{id}"
							onCompleteTopics="doInitEditStudent" indicator="indicator"
							onBeforeTopics="cleanOpenRows">
							<img src="${pageContext.request.contextPath}/images/email.jpeg" />
						</sj:a>
					</div>
					<div class="grid_1">
						 <s:url id="doViewStudent"
								action="ajaxViewStudentAcademics" includeParams="all"
								escapeAmp="false" namespace="%{pageContext.request.contextPath}/student/">
								<s:param name="tempId" value="%{studentId}" />
								<s:param name="classId" value="%{classId}" />
							</s:url>
							<sj:a href="%{doViewStudent}" targets="staffList" cssClass="normalEdit" title="Edit" >
							</sj:a>
					</div>
			</div>
		</s:iterator>
		</div>
	</s:if>
	<s:else>
		<div class="tabBorder">
				There are no Students for this Name.
		</div>
	</s:else>
 <script type="text/javascript">
changePageTitle('Manage Students');
</script>
