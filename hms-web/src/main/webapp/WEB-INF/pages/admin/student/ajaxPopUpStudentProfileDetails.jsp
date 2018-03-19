<%@ include file="/common/taglibs.jsp"%>
<div data-width="760" class="modal fade modal-overflow in"
	style="display: block; width: 760px; margin-left: -379px; margin-top: 150px;">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true"></button>
		<h4 class="modal-title">
			Student Profile Details
		</h4>
	</div>
	<div class="modal-body">
		<div class="form-body">
			<s:form action="#" theme="simple" cssClass="form-horizontal" >
					<div class="tab-pane active profile-classic row" id="tab_1_1">
						<div class="col-md-3">
						<div class="profile-userpic">
								<s:if test="%{viewStudentPersonAccountDetails.adjustedAttachmentFilePath != null &&  viewStudentPersonAccountDetails.adjustedAttachmentFilePath != ''}">
									<img
										src='<c:url value="${viewStudentPersonAccountDetails.adjustedAttachmentFilePath}"/>'
										alt='<s:property  value="viewStudentPersonAccountDetails.firstName" />' border="0" style="width: 180px;" id="studentProfileDiv"/>
								</s:if>
								<s:else>
									<img alt=""  src="../img/nophoto.jpg" style="width: 180px;100px;">
								</s:else>
							</div>
						</div>
						<div class="col-md-9">
							<div class="row">
								<ul>
									<li>
										<span>First Name :</span>
										<s:property value="viewStudentPersonAccountDetails.firstName" />
									</li>
									<li>
										<span>Last Name :</span>
										<s:property value="viewStudentPersonAccountDetails.lastName" />
									</li>
									<li>
										<span>Class & Section :</span>
										<s:property value="viewStudentPersonAccountDetails.classNameAndSection" />
									</li>
									<li>
										<span>Father Name :</span>
										<s:property value="viewStudentPersonAccountDetails.fatherName" />
									</li>
									
									<s:if test='%{#session.parentMobileNoVisibleToTeacher == "Y" )}'>
									<li>
										<span>Mobile Number (Primary) :</span>
											<s:property value="viewStudentPersonAccountDetails.mobileNumber" />
									</li>
									<li>
										<span>Mobile Number (Secondary) :</span>
										<s:property value="viewStudentPersonAccountDetails.secondaryMobileNumber" />
									</li>
									</s:if>
									<li>
										<span>Parent Email :</span>
										<s:property value="viewStudentPersonAccountDetails.parentEmail" />
									</li>
									<li>
										<span>Contact Address :</span>
										<s:property value="viewStudentPersonAccountDetails.addressDesc" />
									</li>
									<s:if test='%{viewStudentPersonAccountDetails.status == "N"}'>
										<li>
											<span>Reason for Leaving :</span>
											<s:property value="viewStudentPersonAccountDetails.description" />
										</li>
									</s:if>
								</ul>
							</div>
						</div>
					</div>
			</s:form>
		</div>
  </div>
</div>
<script type="text/javascript">
$(document).ready(function() {
	UIExtendedModals.init();
});
</script>
