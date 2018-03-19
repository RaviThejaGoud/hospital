<%@ include file="/common/taglibs.jsp"%>
<div data-width="760" class="modal fade modal-overflow in"
	style="display: block; width: 760px; margin-left: -379px; margin-top: 150px;">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true"></button>
		<h4 class="modal-title">Staff Profile Details</h4>
	</div>
	<div class="modal-body">
		<div class="form-body">
			<s:form action="#" theme="simple" cssClass="form-horizontal">
				<div class="tab-pane active profile-classic row" id="tab_1_1">
					<div class="col-md-3">
						<div class="profile-userpic">
							<s:if
								test="%{viewStaffPersonAccountDetails.adjustedAttachmentFilePath != null &&  viewStaffPersonAccountDetails.adjustedAttachmentFilePath != ''}">
								<img
									src='<c:url value="${viewStaffPersonAccountDetails.adjustedAttachmentFilePath}"/>'
									alt='<s:property  value="viewStaffPersonAccountDetails.firstName" />'
									border="0" style="width: 180px;" id="studentProfileDiv" />
							</s:if>
							<s:else>
								<img alt="" src="../img/nophoto.jpg" style="width: 180px;100px;">
							</s:else>
						</div>
					</div>
					<div class="col-md-9">
						<div class="row">
							<ul>
								<li><span>First Name :</span> <s:property
										value="viewStaffPersonAccountDetails.firstName" /></li>
								<li><span>Last Name :</span> <s:property
										value="viewStaffPersonAccountDetails.lastName" /></li>
								<li><span>Mobile Number :</span> <s:property
										value="viewStaffPersonAccountDetails.mobileNumber" /></li>
								<li><span>Email :</span> <s:property
										value="viewStaffPersonAccountDetails.email" /></li>
								<li><span>Qualification :</span> <s:property
										value="viewStaffPersonAccountDetails.qualification1" /></li>
								<li><span>Role :</span> <s:property
										value="viewStaffPersonAccountDetails.roleDescription" /></li>
								<li><span>Contact Address :</span> <s:property
										value="viewStaffPersonAccountDetails.parentAddress" /></li>
								<s:if test='%{viewStaffPersonAccountDetails.status == "N"}'>
									<li><span>Reason for Leaving :</span> <s:property
											value="viewStaffPersonAccountDetails.description" /></li>
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
