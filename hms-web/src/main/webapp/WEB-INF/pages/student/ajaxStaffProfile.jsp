<%@ include file="/common/taglibs.jsp"%>
<div class="grid_12 omega block">
	<div class="block_head">
		<h2>
			My Profile
		</h2>
	</div>
	<div class="block_content" id="metricsContent">
		<div id="commonTabContent" class="grid_11">
			<div id="commonTabWrapper" style="background: #EBF4FB;">
				<div id="commonStep" >
					<fieldset>
						<s:if test="{viewStaffPersonAccountDetails != null}">
							<div class="grid_11">
								<b> Personal Details </b>
							</div>
							<div class="grid_11">
								<div class="grid_7">
									<div class="grid_7">
										<div class="grid_3">
											<label class="labelRight">
												First Name:
											</label>
										</div>
										<div class="grid_4 omega">
											<s:property value="viewStaffPersonAccountDetails.firstName" />
										</div>
									</div>

									<div class="grid_7">
										<div class="grid_3">
											<label class="labelRight">
												Last Name:
											</label>
										</div>
										<div class="grid_4 omega">
											<s:property value="viewStaffPersonAccountDetails.lastName" />
										</div>
									</div>

									<div class="grid_7">
										<div class="grid_3">
											<label class="labelRight">
												Employee ID:
											</label>
										</div>
										<div class="grid_4 omega">
											<s:property value="viewStaffPersonAccountDetails.username" />
										</div>
									</div>

									<div class="grid_7">
										<div class="grid_3">
											<label class="labelRight">
												Blood Group:
											</label>
										</div>
										<div class="grid_4 omega">
											<span style="text-transform: uppercase;"><s:property
													value="viewStaffPersonAccountDetails.bloodGroup" /> </span>
										</div>
									</div>
									<div class="grid_7">
										<div class="grid_3">
											<label class="labelRight">
												Marital Status:
											</label>
										</div>
										<div class="grid_4 omega">
											<s:if
												test="%{viewStaffPersonAccountDetails.maritalStatus != null}">
												<div class="grid_4 omega">
													<s:if
														test='%{viewStaffPersonAccountDetails.maritalStatus != "M"}'>UnMarried</s:if>
													<s:else>Married</s:else>
												</div>
											</s:if>
											<s:else>
												<div class="grid_4 omega">
													---
												</div>
											</s:else>
										</div>
									</div>
									<div class="grid_7">
										<div class="grid_3">
											<label class="labelRight">
												Date of Birth:
											</label>
										</div>
										<div class="grid_4 omega">
											<s:property value="viewStaffPersonAccountDetails.dateOfBirth" />
										</div>
									</div>


									<div class="grid_7">
										<div class="grid_3">
											<label class="labelRight">
												Date of Joining:
											</label>
										</div>
										<div class="grid_4 omega">
											<s:property
												value="viewStaffPersonAccountDetails.dateOfJoining" />
										</div>
									</div>
									<div class="grid_7">
										<div class="grid_3">
											<label class="labelRight">
												Summary:
											</label>
										</div>
										<div class="grid_4 omega">
											<s:if test="%{viewStaffPersonAccountDetails.summary != null}">
												<div class="grid_4 omega">
													<s:property value="viewStaffPersonAccountDetails.summary" />
												</div>
											</s:if>
											<s:else>
												<div class="grid_4 omega">
													---
												</div>
											</s:else>
										</div>
									</div>
								</div>
								<div class="grid_3 alpha">
									<img
										src='<c:url value="${viewStaffPersonAccountDetails.adjustedAttachmentFilePath}"/>'
										alt='<s:property  value="viewStaffPersonAccountDetails.personFullName" />'
										height="150px" width="150px" border="0" />
								</div>

							</div>
							<div class="grid_11 border"></div>
							<div class="grid_7">
								<div class="grid_7">
									<b> Education Details: </b>
								</div>
							</div>
							<div class="grid_10">
								<div class="grid_3">
									<label class="labelRight">
										UG:
									</label>
								</div>
								<div class="grid_6 omega">
									<s:property
										value="viewStaffPersonAccountDetails.qualification1" />
								</div>
								<div class="grid_3">
									<label class="labelRight">
										PG:
									</label>
								</div>
								<div class="grid_6 omega">
									<s:if
										test="%{viewStaffPersonAccountDetails.qualification2 != null || viewStaffPersonAccountDetails.qualification2 != ''}">
										<div class="grid_4 omega">
											<s:property
												value="viewStaffPersonAccountDetails.qualification2" />
										</div>
									</s:if>
									<s:else>
										<div class="grid_4 omega">
											----
										</div>
									</s:else>
								</div>
							</div>
							<div class="grid_7">
								<div class="grid_3">
									<label class="labelRight">
										Experience:
									</label>
								</div>
								<div class="grid_4 omega">
									<s:property value="viewStaffPersonAccountDetails.experience" />
								</div>
							</div>
							<div class="grid_11 border"></div>
							<div class="grid_7">
								<div class="grid_7">
									<b>Contact Details </b>
								</div>
							</div>
							<div class="grid_7">
								<div class="grid_3">
									<label class="labelRight">
										Address Line1:
									</label>
								</div>
								<div class="grid_4 omega">
									<s:if
										test="%{viewStaffPersonAccountDetails.addressLine1 != null}">
										<div class="grid_4 omega">
											<s:property
												value="viewStaffPersonAccountDetails.addressLine1" />
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
										Address Line2:
									</label>
								</div>
								<div class="grid_4 omega">
									<s:if
										test="%{viewStaffPersonAccountDetails.addressLine2 == ''}">
										<div class="grid_4 omega">
											---
										</div>
									</s:if>
									<s:else>
										<div class="grid_4 omega">
											<s:property
												value="viewStaffPersonAccountDetails.addressLine2" />
										</div>
									</s:else>
								</div>
								<div class="grid_7">
									<div class="grid_3">
										<label class="labelRight">
											City:
										</label>
									</div>
									<div class="grid_4 omega">
										<s:property value="viewStaffPersonAccountDetails.city" />
									</div>
								</div>
								<div class="grid_7">
									<div class="grid_3">
										<label class="labelRight">
											State:
										</label>
									</div>
									<div class="grid_4 omega">
										<s:property value="viewStaffPersonAccountDetails.state" />
									</div>
								</div>
								<div class="grid_7">
									<div class="grid_3">
										<label class="labelRight">
											Pincode:
										</label>
									</div>
									<div class="grid_4 omega">
										<s:property value="viewStaffPersonAccountDetails.postalCode" />
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
											test="%{viewStaffPersonAccountDetails.phoneNumber == ''}">
											<div class="grid_4 omega">
												&nbsp;
											</div>
										</s:if>
										<s:else>
											<div class="grid_4 omega">
												<s:property
													value="viewStaffPersonAccountDetails.phoneNumber" />
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
										<div class="grid_4 omega">
											<s:property
												value="viewStaffPersonAccountDetails.mobileNumber" />
										</div>
									</div>
								</div>
						</s:if>
					</fieldset>
				</div>
			</div>
		</div>
	</div>
</div>




