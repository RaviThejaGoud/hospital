<%@ include file="/common/taglibs.jsp"%>
<s:if test="%{viewStudentPersonAccountDetails != null}">
	<div data-width="760" class="modal fade modal-overflow in"
		id="responsive"
		style="display: block; width: 760px;height:500px; margin-left: -379px; margin-top: 200px;"
		aria-hidden="false">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true"></button>
			<h4 class="modal-title">Student Details</h4>
		</div>
		<div class="modal-body">
			<div class="form-body form-horizontal">
				<div class="col-md-3">
					<div class="profile-userpic">
						<s:if
							test="%{viewStudentPersonAccountDetails.adjustedAttachmentFilePath != null &&  viewStudentPersonAccountDetails.adjustedAttachmentFilePath != ''}">
							<img
								src='<c:url value="${viewStudentPersonAccountDetails.adjustedAttachmentFilePath}"/>'
								alt='<s:property  value="viewStudentPersonAccountDetails.personFullName" />'
								border="0" style="width: 180px;" id="userProfileDiv"
								class='imagePathNotFound' />
						</s:if>
						<s:else>
							<img alt="" src="../img/nophoto.jpg" style="width: 180px;100px;">
						</s:else>
					</div>
				</div>
				<div class="col-md-9">
					<div class="row">
						<ul>
							<li><span>Student Roll No. :</span> <s:property
									value="viewStudentPersonAccountDetails.rollNumber" /></li><br/>
							<li><span>Student Name :</span> <s:property
									value="viewStudentPersonAccountDetails.personFullName" /></li><br/>
							<li><span>Date of Birth :</span> <s:property
									value="viewStudentPersonAccountDetails.dateOfBirth" /></li><br/>
							<li><span>Date of Joining :</span> <s:property
									value="viewStudentPersonAccountDetails.dateOfJoining" /></li><br/>
							<li><span>Class : </span>
							<s:property
									value="viewStudentPersonAccountDetails.classAndSection" /></li><br/>
							<li><span>Parent Name :</span>
							<s:property value="viewStudentPersonAccountDetails.fatherName" /></li><br/>
							<li><span>Parent E-Mail :</span>
							<s:property value="viewStudentPersonAccountDetails.parentEmail" /></li><br/>
							<li><span>Parent Phone No :</span>
							<s:property value="viewStudentPersonAccountDetails.phoneNumber" /></li><br/>
							<li><span>Parent Mobile No :</span>
							<s:property value="viewStudentPersonAccountDetails.mobileNumber" /></li><br/>
							<li><span>Summary :</span>
							<s:property value="viewStudentPersonAccountDetails.summary" /></li><br/>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
</s:if>
