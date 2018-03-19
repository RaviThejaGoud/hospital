<%@ include file="/common/taglibs.jsp"%>
<s:if test="{viewStudentPersonAccountDetails != null}">
<div class="form-horizontal">
<h4 class="pageTitle bold">Student Person Info</h4>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-3">
						First Name :
					</label>
					<div class="form-control-static">
						<b><s:property value="viewStudentPersonAccountDetails.firstName" /></b>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-md-3">
						Last Name :
					</label>
					<div class="form-control-static">
						<b><s:property value="viewStudentPersonAccountDetails.lastName" /></b>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-md-3">
						Date of Birth :
					</label>
					<div class="form-control-static">
						<b><s:property value="viewStudentPersonAccountDetails.dateOfBirthStr" /></b>
					</div>
				</div>

				<div class="form-group">
					<label class="control-label col-md-3">
						Blood Group :
					</label>
					<div class="form-control-static">
						<b><s:property value="viewStudentPersonAccountDetails.bloodGroup" /></b>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-md-3">
						Class :
					</label>
					<div class="form-control-static">
						<b><s:property value="viewStudentPersonAccountDetails.className" />
						-
						<s:property value="viewStudentPersonAccountDetails.section" /></b>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-3">
						Image :
					</label>
					<div class="form-control-static">
						<img
							src='<c:url value="${viewStudentPersonAccountDetails.adjustedAttachmentFilePath}"/>'
							alt='<s:property  value="viewStudentPersonAccountDetails.personFullName" />'
							height="150px" width="150px" border="0" />
					</div>
				</div>
			</div>
		</div>
		<h4 class="pageTitle bold form-section">
		Student Address
	</h4>
	<div class="row">
		<div class="col-md-6">
			<div class="form-group">
				<label class="control-label col-md-3">
					Address Line1 :
				</label>
				<div class="form-control-static">
						<s:if
							test="%{viewStudentPersonAccountDetails.addressLine1 != null}">
							<div class="col-md-4 omega">
								<b><s:property value="viewStudentPersonAccountDetails.addressLine1" /></b>
							</div>
						</s:if>
				</div>
			</div>
		</div>
		<div class="col-md-6">
			<div class="form-group">
				<label class="control-label col-md-3">
					Address Line2 :
				</label>
				<div class="form-control-static">
						<s:if test="%{viewStudentPersonAccountDetails.addressLine2 == ''}">
							<div class="col-md-4 omega">
								&nbsp;
							</div>
						</s:if>
						<s:else>
							<div class="col-md-4 omega">
								<b><s:property value="viewStudentPersonAccountDetails.addressLine2" /></b>
							</div>
						</s:else>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-md-6">
			<div class="form-group">
				<label class="control-label col-md-3">
					City :
				</label>
				<div class="form-control-static">
						<b><s:property value="viewStudentPersonAccountDetails.city" /></b>
				</div>
			</div>
		</div>
		<div class="col-md-6">
			<div class="form-group">
				<label class="control-label col-md-3">
					State : 
				</label>
				<div class="form-control-static">
						<b><s:property value="viewStudentPersonAccountDetails.state" /></b>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-md-6">
			<div class="form-group">
				<label class="control-label col-md-3">
					Pincode :
				</label>
				<div class="form-control-static">
						<b><s:property value="viewStudentPersonAccountDetails.postalCode" /></b>
				</div>
			</div>
		</div>
	</div>
	<h4 class="pageTitle bold form-section">
		Parent Contact Details
	</h4>

	<div class="row">
		<div class="col-md-6">
			<div class="form-group">
				<label class="control-label col-md-3">
					Father Name :
				</label>
				<div class="form-control-static">
						<s:if test="%{viewStudentPersonAccountDetails.fatherName != null}">
							<div class="col-md-4 omega">
								<b><s:property value="viewStudentPersonAccountDetails.fatherName" /></b>
							</div>
						</s:if>
				</div>
			</div>
		</div>
		<div class="col-md-6">
			<div class="form-group">
				<label class="control-label col-md-3">
					Mother Name :
				</label>
				<div class="form-control-static">
						<s:if test="%{viewStudentPersonAccountDetails.motherName != null}">
							<div class="col-md-4 omega">
								<b><s:property value="viewStudentPersonAccountDetails.motherName" /></b>
							</div>
						</s:if>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-md-6">
			<div class="form-group">
				<label class="control-label col-md-3">
					Home Number :
				</label>
				<div class="form-control-static">
						<s:if test="%{viewStudentPersonAccountDetails.phoneNumber == ''}">
							<div class="col-md-4 omega">
								&nbsp;
							</div>
						</s:if>
						<s:else>
							<div class="col-md-4 omega">
								<b><s:property value="viewStudentPersonAccountDetails.phoneNumber" /></b>
							</div>
						</s:else>
				</div>
			</div>
		</div>
		<div class="col-md-6">
			<div class="form-group">
				<label class="control-label col-md-3">
					Mobile Number :
				</label>
				<div class="form-control-static">
						<b><s:property value="viewStudentPersonAccountDetails.mobileNumber" /></b>
				</div>
			</div>
		</div>
		<div class="col-md-6">
			<div class="form-group">
				<label class="control-label col-md-3">
					Pincode :
				</label>
				<div class="form-control-static">
						<b><s:property value="viewStudentPersonAccountDetails.postalCode" /></b>
				</div>
			</div>
		</div>
	</div>
	</div>
</s:if>