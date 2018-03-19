<%@ include file="/common/taglibs.jsp"%>
       <s:form action="parentRegStep2" method="post" cssClass="form-vertical"  theme="simple" enctype="multipart/form-data">
			<s:if
				test="%{viewStudentPersonAccountDetails.parentEmail != null && !viewStudentPersonAccountDetails.parentEmail.isEmpty()}">
				<div class="grid_10 commonText">
					<div> This email address ( <font color="#77A2443"><s:property value="viewStudentPersonAccountDetails.parentEmail" /> </font>) is already associated with [
						<font color="#77A2443"><s:property value="viewStudentPersonAccountDetails.firstName" />(<s:property value="viewStudentPersonAccountDetails.classAndSection" />)</font>]
						student. </div> <br />
					<div> Please <a href="${pageContext.request.contextPath}/login.jsp">click here</a> to login to system. </div> <br />
					<div> Having trouble to login to system use <a href="${pageContext.request.contextPath}/signup/doForgotPassword.do">Forgot
							Password</a> option to generate new password and login to system.
					</div>
				</div>
			</s:if>
			<s:elseif test="%{viewStudentPersonAccountDetails.parentEmail.isEmpty()}">
			<div class="row">
				<div class="col-md-12">
					<div class="col-md-3">
					  <div style="padding-bottom: 10px;">
							<s:if
								test="%{adjustedAttachmentFilePath != null &&  adjustedAttachmentFilePath != empty}">
								<img src='<c:url value="${adjustedAttachmentFilePath}"/>'
									alt='<s:property  value="user.fullPersonName" />' border="0" />&nbsp;
							</s:if>
							<s:else>
								<img src='../images/nophoto.jpg' />
							</s:else>
						</div>
						<div>
							<s:url id="saveParentRegStep" action="parentRegStep2"
								includeParams="all" escapeAmp="false" namespace="/signup">
								<s:param name="accountId"
									value="%{viewStudentPersonAccountDetails.accountId}" />
							</s:url>
							<sj:a  href="%{saveParentRegStep}" targets="parentRegSteps" 
								cssClass="purple btn" cssStyle="font-size:11px;padding:2px 3px;">Add To My Profile</sj:a>
						</div>
					</div>
					<div class="col-md-9">
						<table>
							<tr>
								<td class="right">
									<strong>Role Number:</strong> 
								</td>
								<td>
									<s:property
										value="viewStudentPersonAccountDetails.rollNumber" />
								</td>
							</tr>
							<tr>
								<td class="right">
									<strong>Student Name:</strong> 
								</td>
								<td>
									<s:property
										value="viewStudentPersonAccountDetails.personFullName" />
								</td>
							</tr>
							<tr>
								<td class="right">
									<strong>Father Name:</strong> 
								</td>
								<td>
									<s:property
										value="viewStudentPersonAccountDetails.fatherName" />
								</td>
							</tr>
							<tr>
								<td class="right">
									<strong>Class &amp; Section:</strong> 
								</td>
								<td>
									<s:property
										value="viewStudentPersonAccountDetails.classAndSection" />
								</td>
							</tr>
							<tr>
								<td class="right">
									<strong>AdmissionNumber:</strong> 
								</td>
								<td>
									<s:property
										value="viewStudentPersonAccountDetails.admissionNumber" />
								</td>
							</tr>
							<tr>
								<td class="right">
									<strong>Mobile Number:</strong> 
								</td>
								<td>
									<s:property
										value="viewStudentPersonAccountDetails.mobileNumber" />
								</td>
							</tr>
						</table>
					</div>
				</div>
				</div>
			</s:elseif>
			<s:else>
				<div class="alert alert-info">
					Oops! No student details found.Please try again.
				</div>
				<div>
					<a class="linkRight" href="${pageContext.request.contextPath}/login.jsp">
					<button class="btn" type="button" id="register-back-btn">
						<i class="m-icon-swapleft"></i>Back to Login</button></a>
					
				</div>
			</s:else>
</s:form>
<style type="text/css">
.login .content {
    width: 480px;
}
.right{
text-align: right;
}
</style>
