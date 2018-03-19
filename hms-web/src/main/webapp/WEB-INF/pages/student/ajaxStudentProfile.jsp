<%@ include file="/common/taglibs.jsp"%>
<div id="commonTabContent" class="grid_10">
	<div id="commonTabWrapper">
		<div id="commonStep" style="background: #EBF4FB;margin: 20px;">
			<fieldset>
			<div class="grid_10">
				<s:if test="{viewStudentPersonAccountDetails != null}">
					<div class="grid_3">
							<label class="labelRight">
								<b> Student Details </b>
							</label>
						</div>
				 
					<div class="grid_10">
						<div class="grid_7">
							<div class="grid_7">
								<div class="grid_3">
									<label class="labelRight">
										First Name:
									</label>
								</div>
								<div class="grid_4 omega">
									<s:property value="viewStudentPersonAccountDetails.firstName" />
								</div>
							</div>
							<div class="grid_7">
								<div class="grid_3">
									<label class="labelRight">
										Last Name:
									</label>
								</div>
								<div class="grid_4 omega">
									<s:property value="viewStudentPersonAccountDetails.lastName" />
								</div>
							</div>
							<div class="grid_7">
								<div class="grid_3">
									<label class="labelRight">
										Date of Birth:
									</label>
								</div>
								<div class="grid_4 omega">
									<s:property value="viewStudentPersonAccountDetails.dateOfBirth" />
								</div>
							</div>
							<div class="grid_7">
								<div class="grid_3">
									<label class="labelRight">
										Blood Group:
									</label>
								</div>
								<div class="grid_4 omega">
									<s:property value="viewStudentPersonAccountDetails.bloodGroup" />
								</div>
							</div>
							<div class="grid_7">
								<div class="grid_3">
									<label class="labelRight">
										Class:
									</label>
								</div>
								<div class="grid_4 omega">
									<s:property value="viewStudentPersonAccountDetails.className" />
									-
									<s:property value="viewStudentPersonAccountDetails.section" />
								</div>
							</div>
						</div>
						<div class="grid_3 alpha">
							<img
								src='<c:url value="${viewStudentPersonAccountDetails.adjustedAttachmentFilePath}"/>'
								alt='<s:property  value="viewStudentPersonAccountDetails.personFullName" />'
								height="150px" width="150px" border="0" />
						</div>
					</div>
					<div class="grid_10 border"></div>
						<div class="grid_7">
							<div class="grid_3">
								<label class="labelRight">
									<b>Student Address</b>
								</label>
							</div>
						</div>
						<div class="grid_7">
							<div class="grid_3">
								<label class="labelRight">
									Adress Line1:
								</label>
							</div>
							<div class="grid_4 omega">
								<s:if
									test="%{viewStudentPersonAccountDetails.addressLine1 != null}">
									<div class="grid_4 omega">
										<s:property
											value="viewStudentPersonAccountDetails.addressLine1" />
									</div>
								</s:if>
								<s:else>
									<div class="grid_4 omega">
										&nbsp;
									</div>
								</s:else>

							</div>
						</div>
						<div class="grid_7">
							<div class="grid_3">
								<label class="labelRight">
									Adress Line2:
								</label>
							</div>
							<div class="grid_4 omega">
								<s:if
									test="%{viewStudentPersonAccountDetails.addressLine2 == ''}">
									<div class="grid_4 omega">
										&nbsp;
									</div>
								</s:if>
								<s:else>
									<div class="grid_4 omega">
										<s:property
											value="viewStudentPersonAccountDetails.addressLine2" />
									</div>
								</s:else>
							</div>
						</div>
						<div class="grid_7">
							<div class="grid_3">
								<label class="labelRight">
									City:
								</label>
							</div>
							<div class="grid_4 omega">
								<s:property value="viewStudentPersonAccountDetails.city" />
							</div>
						</div>
						<div class="grid_7">
							<div class="grid_3">
								<label class="labelRight">
									State:
								</label>
							</div>
							<div class="grid_4 omega">
								<s:property value="viewStudentPersonAccountDetails.state" />
							</div>
						</div>
						<div class="grid_7">
							<div class="grid_3">
								<label class="labelRight">
									Pincode:
								</label>
							</div>
							<div class="grid_4 omega">
								<s:property value="viewStudentPersonAccountDetails.postalCode" />
							</div>
						</div>
						<div class="grid_10 border"></div>
						<div class="grid_7">
							<div class="grid_3">
								<label class="labelRight">
									<b>Parent Contact Details</b>
								</label>
							</div>
						</div>
						<div class="grid_7">
							<div class="grid_3">
								<label class="labelRight">
									Father Name:
								</label>
							</div>
							<div class="grid_4 omega">
								<s:if
									test="%{viewStudentPersonAccountDetails.fatherName != null}">
									<div class="grid_4 omega">
										<s:property value="viewStudentPersonAccountDetails.fatherName" />
									</div>
								</s:if>
								<s:else>
									<div class="grid_4 omega">
										&nbsp;
									</div>
								</s:else>
							</div>
						</div>
						<div class="grid_7">
							<div class="grid_3">
								<label class="labelRight">
									Mother Name:
								</label>
							</div>
							<div class="grid_4 omega">
								<s:if
									test="%{viewStudentPersonAccountDetails.motherName != null}">
									<div class="grid_4 omega">
										<s:property value="viewStudentPersonAccountDetails.motherName" />
									</div>
								</s:if>
								<s:else>
									<div class="grid_4 omega">
										&nbsp;
									</div>
								</s:else>
							</div>
						</div>
						<div class="grid_7">
							<div class="grid_3">
								<label class="labelRight">
									Home Number:
								</label>
							</div>
							<div class="grid_4 omega">
								<s:if
									test="%{viewStudentPersonAccountDetails.phoneNumber == ''}">
									<div class="grid_4 omega">
										&nbsp;
									</div>
								</s:if>
								<s:else>
									<div class="grid_4 omega">
										<s:property
											value="viewStudentPersonAccountDetails.phoneNumber" />
									</div>
								</s:else>
							</div>
						</div>
						<div class="grid_7">
							<div class="grid_3">
								<label class="labelRight">
									Mobile Number:
								</label>
							</div>
							<div class="grid_4 omega">
								<s:property value="viewStudentPersonAccountDetails.mobileNumber" />
							</div>
						</div>
						<div class="grid_7">
							<div class="grid_3">
								<label class="labelRight">
									Pincode:
								</label>
							</div>
							<div class="grid_4 omega">
								<s:property value="viewStudentPersonAccountDetails.postalCode" />
							</div>
						</div>
				</s:if></div>
			</fieldset>
		</div>
	</div>
</div>
