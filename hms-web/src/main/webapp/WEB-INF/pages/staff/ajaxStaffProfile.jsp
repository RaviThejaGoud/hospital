<%@ include file="/common/taglibs.jsp"%>
<s:if test="{viewStaffPersonAccountDetails != null}">
	<s:form action="#" method="post" cssClass="form-horizontal"
		theme="simple">
		<div class="form-body">
			<h4 class="pageTitle bold form-section">
				Person Info
			</h4>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4">
							<span class="required">*</span>First Name :
						</label>
						<div class="form-control-static">
							<b><s:property value="viewStaffPersonAccountDetails.firstName" /></b>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4">
							Last Name :
						</label>
						<div class="form-control-static">
							<b><s:property value="viewStaffPersonAccountDetails.lastName" /></b>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4">
							<span class="required">*</span>Employee ID :
						</label>
						<div class="form-control-static">
							<b><s:property value="viewStaffPersonAccountDetails.username" /></b>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4">
							Blood Group :
						</label>
						<div class="form-control-static">
							<b><s:property value="viewStaffPersonAccountDetails.bloodGroup" /></b>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4">
							<span class="required">*</span>Marital Status :
						</label>
						<div class="form-control-static">
								<s:if
									test="%{viewStaffPersonAccountDetails.maritalStatus != null}">
										<b><s:if
											test='%{viewStaffPersonAccountDetails.maritalStatus != "M"}'>UnMarried</s:if>
										<s:else>Married</s:else></b>
								</s:if>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4">
							Date of Birth :
						</label>
						<div class="form-control-static">
							<b><s:property value="viewStaffPersonAccountDetails.dateOfBirthStr" /></b>
						</div>
					</div>
				</div>
			</div>
			<h4 class="pageTitle bold form-section">
				Education Details
			</h4>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4">
							<span class="required">*</span>UG :
						</label>
						<div class="form-control-static">
							<b><s:property value="viewStaffPersonAccountDetails.qualification1" /></b>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-4">
							PG :
						</label>
						<div class="form-control-static">
							<s:if
								test="%{viewStaffPersonAccountDetails.qualification2 != null || viewStaffPersonAccountDetails.qualification2 != ''}">
								<div class="col-md-4">
									<b><s:property
										value="viewStaffPersonAccountDetails.qualification2" /></b>
								</div>
							</s:if>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-4">
							<span class="required">*</span>Experience :
						</label>
						<div class="form-control-static">
							<b><s:property value="viewStaffPersonAccountDetails.experience" /></b>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4">
							Image :
						</label>
						<div class="form-control-static">
							<img
								src='<c:url value="${viewStaffPersonAccountDetails.adjustedAttachmentFilePath}"/>'
								alt='<s:property  value="viewStaffPersonAccountDetails.personFullName" />'
								height="126px" width="126px" border="0" />
						</div>
					</div>
				</div>
			</div>
			<h4 class="pageTitle bold form-section">
				Contact Details
			</h4>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4">
							<span class="required">*</span>Address Line1 :
						</label>
						<div class="form-control-static">
								<s:if
									test="%{viewStaffPersonAccountDetails.addressLine1 != null}">
									<div class="grid_4 omega">
										<b><s:property value="viewStaffPersonAccountDetails.addressLine1" /></b>
									</div>
								</s:if>
							</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4">
							Address Line2 :
						</label>
							<div class="form-control-static">
								<s:if test="%{viewStaffPersonAccountDetails.addressLine2 == ''}">
									<div class="grid_4 omega">
										---
									</div>
								</s:if>
								<s:else>
									<div class="grid_4 omega">
										<b><s:property value="viewStaffPersonAccountDetails.addressLine2" /></b>
									</div>
								</s:else>
							</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4">
							City :
						</label>
						<div class="form-control-static">
							<b><s:property value="viewStaffPersonAccountDetails.city" /></b>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4">
							<span class="required">*</span>State :
						</label>
						<div class="form-control-static">
							<b><s:property value="viewStaffPersonAccountDetails.state" /></b>
						</div>
					</div>
				</div>

			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4">
							Pincode :
						</label>
						<div class="form-control-static">
							<b><s:property value="viewStaffPersonAccountDetails.postalCode" /></b>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4">
							<span class="required">*</span>Home Number :
						</label>
							<div class="form-control-static">
								<s:if test="%{viewStaffPersonAccountDetails.phoneNumber == ''}">
									<div class="col-md-4">
										&nbsp;
									</div>
								</s:if>
								<s:else>
									<div class="col-md-4">
										<b><s:property value="viewStaffPersonAccountDetails.phoneNumber" /></b>
									</div>
								</s:else>
							</div>
					</div>
				</div>

			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4">
							Mobile Number :
						</label>
						<div class="form-control-static">
							<b><s:property value="viewStaffPersonAccountDetails.mobileNumber" /></b>
						</div>
					</div>
				</div>

			</div>
		</div>
	</s:form>
</s:if>
