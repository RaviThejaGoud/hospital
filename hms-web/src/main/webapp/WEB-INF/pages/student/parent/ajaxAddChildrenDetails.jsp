<%@ include file="/common/taglibs.jsp"%>
<%-- <div class="row">
	<div class="col-md-6">
		<h4 class="pageTitle bold">
			Add My Children
		</h4>
	</div>
	<div class="col-md-5">
		<s:url id="urlAddChildren" action="ajaxDoAddChildren" namespace="/student"/>
		<sj:a href="%{urlAddChildren}" targets="addChildDiv"
			indicator="indicator">
			<h4 class="pageTitle bold">
				Back To Add My Children
			</h4>
		</sj:a>
	</div>
</div> --%>
<s:form id="addMychild" action="#" method="post" theme="simple"
	cssClass="form-horizontal">
	<div class="form-body">
		<s:if
			test="%{viewStudentPersonAccountDetails.parentEmail != null && !viewStudentPersonAccountDetails.parentEmail.isEmpty()}">
			<div class="alert alert-info">
				This username (
				<font color="#77A2443"><s:property
						value="viewStudentPersonAccountDetails.username" /> [ <s:property
						value="viewStudentPersonAccountDetails.firstName" />( <s:property
						value="viewStudentPersonAccountDetails.classAndSection" /> </font>) ] is
				already added with this parent [
				<font color="#77A2443"><s:property
						value="viewStudentPersonAccountDetails.parentEmail" /> </font> ].
				<br />
			</div>
		</s:if>
		<s:elseif test="%{viewStudentPersonAccountDetails != null}">
			<div class="col-md-10">
				<div class="col-md-4 left">
					<div style="padding-left: 14px;">
						<s:if
							test="%{adjustedAttachmentFilePath != null &&  adjustedAttachmentFilePath != empty}">
							<img src='<c:url value="${adjustedAttachmentFilePath}"/>'
								alt='<s:property  value="user.fullPersonName" />' height="160px"
								width="135px" border="0" />
						</s:if>
						<s:else>
							<img src='../images/nophoto.jpg' />
						</s:else>
					</div>
					<br/>
					<div>
						<s:url id="saveChildren" action="ajaxSaveChildren"
							includeParams="all" escapeAmp="false" namespace="/student">
							<s:param name="tempId"
								value="%{viewStudentPersonAccountDetails.accountId}" />
						</s:url>
						<sj:a id="parentRegContent" href="%{saveChildren}"
							targets="myStudentsAttendancediv" indicator="indicator"
							cssClass="btn blue" cssStyle="width: 160px;">Add My Children</sj:a>
					</div>
				</div>
			</div>
			<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4">
						<strong>Role Number :</strong>
					</label>
					<div class="col-md-5">
						<p class="form-control-static">
							<s:property value="viewStudentPersonAccountDetails.rollNumber" />
						</p>
					</div>
				</div>
			</div>
			</div>
			<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4">
						<strong>Student Name :</strong>
					</label>
					<div class="col-md-5">
						<p class="form-control-static">
							<s:property
								value="viewStudentPersonAccountDetails.personFullName" />
						</p>
					</div>
				</div>
			</div>
			</div>
			<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4">
						<strong>Father Name :</strong>
					</label>
					<div class="col-md-5">
						<p class="form-control-static">
							<s:property value="viewStudentPersonAccountDetails.fatherName" />
						</p>
					</div>
				</div>
			</div>
			</div>
			<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4">
						<strong>Class &amp; Section :</strong>
					</label>
					<div class="col-md-5">
						<p class="form-control-static">
							<s:property	value="viewStudentPersonAccountDetails.classAndSection" />
						</p>
					</div>
				</div>
			</div>
			</div>
			<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4">
						<strong>AdmissionNumber :</strong>
					</label>
					<div class="col-md-5">
						<p class="form-control-static">
							<s:property	value="viewStudentPersonAccountDetails.admissionNumber" />
						</p>
					</div>
				</div>
			</div>
			</div>
			<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4">
						<strong>Mobile Number :</strong>
					</label>
					<div class="col-md-5">
						<p class="form-control-static">
							<s:property value="viewStudentPersonAccountDetails.mobileNumber" />
						</p>
					</div>
				</div>
			</div>
			</div>
			<!--<div class="form-group">
				<label class="col-md-4 control-label">
					<strong>Role Number :</strong>
				</label>
				<div class="col-md-4">
					<div class="input-group col-md-6">
						<s:property value="viewStudentPersonAccountDetails.rollNumber" />
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="col-md-4 control-label">
					<strong>Student Name :</strong>
				</label>
				<div class="col-md-6">
					<div class="input-group col-md-6">
						<s:property value="viewStudentPersonAccountDetails.personFullName" />
					</div>
				</div>
			</div>
			
			
			<div class="form-group">
				<label class="col-md-4 control-label">
					<strong>Father Name :</strong>
				</label>
				<div class="col-md-6">
					<div class="input-group col-md-6">
						<s:property value="viewStudentPersonAccountDetails.fatherName" />
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="col-md-4 control-label">
					<strong>Class &amp; Section :</strong>
				</label>
				<div class="col-md-6">
					<div class="input-group col-md-6">
						<s:property value="viewStudentPersonAccountDetails.classAndSection" />
					</div>
				</div>
			</div>
			
			
			<div class="form-group">
				<label class="col-md-4 control-label">
					<strong>AdmissionNumber :</strong>
				</label>
				<div class="col-md-6">
					<div class="input-group col-md-6">
						<s:property value="viewStudentPersonAccountDetails.admissionNumber" />
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="col-md-4 control-label">
					<strong>Mobile Number :</strong>
				</label>
				<div class="col-md-6">
					<div class="input-group col-md-6">
						<s:property value="viewStudentPersonAccountDetails.mobileNumber" />
					</div>
				</div>
			</div>-->
		</s:elseif>
		<s:else>
			<div class="alert alert-info">
				No student details found. Please try again.
			</div>
		</s:else>
	</div>
</s:form>
