<%@ include file="/common/taglibs.jsp"%>
<div data-width="760" class="modal fade modal-overflow in"
	style="display: block; width: 760px; margin-left: -360px; margin-top: 150px;">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true"></button>
		<h4 class="modal-title">
			 Student Details
		</h4>
	</div>
	<div class="modal-body form-horizontal">
		<s:if test="%{student != null}">
			<div>
				<div class="col-md-12">
					<div class="col-md-3">
							 <div class="col-md-2">
								<img
									src='<c:url value="${student.studProfileOrAdminImageFilePath}"/>'
									alt='<s:property  value="student.account.person.personFullName" />'
									height="100px" width="100px" />
							</div>
					</div>
					<div class="col-md-8">
						<div class="form-group">
							<label class="control-label col-md-5">
								<b> Student Id : </b> 
							</label>
							<div class="col-md-6">
								<p class="form-control-static">
									<s:property value="student.rollNumber" />
								</p>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-5">
								<b> Student Name : </b>
							</label>
							<div class="col-md-6">
								<p class="form-control-static">
									<s:property value="student.account.person.personFullName" />
								</p>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-5">
								<b> Date of Birth : </b>
							</label>
							<div class="col-md-6">
								<p class="form-control-static">
									<s:property value="student.account.person.dateOfBirthStr" />
								</p>
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-md-5">
								<b> Date of Joining : </b>
							</label>
							<div class="col-md-6">
								<p class="form-control-static">
									<s:property value="student.account.person.dateOfJoiningStr" />
								</p>
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-md-5">
								<b> Parent Name : </b>
							</label>
							<div class="col-md-6">
								<p class="form-control-static">
									<s:property value="student.account.person.fatherName" />
								</p>
							</div>
						</div>
						<s:if test="%{tempBoolean}">
						<div class="form-group">
							<label class="control-label col-md-5">
								<b> Parent EmailId : </b>
							</label>
							<div class="col-md-6">
								<p class="form-control-static">
									<s:property value="student.account.person.parentEmail" />
								</p>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-5">
								<b> Parent Mobile No : </b>
							</label>
							<div class="col-md-6">
								<p class="form-control-static">
									<s:property value="student.account.person.mobileNumber" />
								</p>
							</div>
						</div>
						</s:if>
						<div class="form-group">
							<label class="control-label col-md-5">
								<b> Summary : </b>
							</label>
							<div class="col-md-6">
								<p class="form-control-static">
									<s:property value="student.account.person.summary" />
								</p>
							</div>
						</div>
					</div>
					</div>
			</div>
		</s:if>
		<s:else>
			<div class="alert alert-info">
				Currently there are no student details.
			</div>
		</s:else>
	</div>
</div>
