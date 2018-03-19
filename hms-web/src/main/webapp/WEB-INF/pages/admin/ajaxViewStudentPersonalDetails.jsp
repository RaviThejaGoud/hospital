<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">Student Information</div>
				<div class="tools">
					<a href="javascript:;" class="expand"></a>
					<a href="javascript:;" class="remove"></a>
				</div>
			</div>
			<div class="portlet-body">
				<div class="form-body">
					<div class="tab-content">
						<div class="tab-pane active profile-classic" id="tab_1_1">
						<div class="row">
						<div class="col-md-9">
							<div class="form-group">
								<label class="control-label col-md-3"> Name : </label>
								<div class="col-md-8">
									<p class="form-control-static">
										<s:property value="viewAllUsers.firstName" />&nbsp;<s:property value="viewAllUsers.lastName"/>
									</p>
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-md-3"> Class & Section : </label>
								<div class="col-md-8">
									<p class="form-control-static">
										<s:property value="viewAllUsers.classAndSection" />
									</p>
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-md-3"> Admission Number : </label>
								<div class="col-md-8">
									<p class="form-control-static">
									<s:if test="%{viewAllUsers.admissionNumber != null && !viewAllUsers.admissionNumber.isEmpty()}">
											<s:property value="viewAllUsers.admissionNumber" />
										</s:if>
										<s:else>
										  NA
										</s:else>
									</p>
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-md-3"> Father Name  : </label>
								<div class="col-md-8">
									<p class="form-control-static">
										<s:if test="%{viewAllUsers.fatherName != null && !viewAllUsers.fatherName.isEmpty()}">
												<s:property value="viewAllUsers.fatherName" />
										</s:if>
										<s:else>
											NA
										</s:else>
									</p>
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-md-3"> Mobile Number  : </label>
								<div class="col-md-8">
									<p class="form-control-static">
										<s:if test="%{viewAllUsers.mobileNumber != null && !viewAllUsers.mobileNumber.isEmpty()}">
												<s:property value="viewAllUsers.mobileNumber" />
											</s:if>
											<s:else>
												NA
											</s:else>
									</p>
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-md-3"> Parent Email :  </label>
								<div class="col-md-8">
									<p class="form-control-static">
										<s:if test="%{viewAllUsers.parentEmail != null && !viewAllUsers.parentEmail.isEmpty()}">
											<s:property value="viewAllUsers.parentEmail" />
										</s:if>
										<s:else>
											NA
										</s:else>
									</p>
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-md-3"> Address :  </label>
								<div class="col-md-8">
									<p class="form-control-static">
										<s:if test='%{viewAllUsers.stateName!=null && !viewAllUsers.stateName.isEmpty()}'>
											<s:property value="viewAllUsers.stateName" />
										</s:if>
										<s:else>
											NA
										</s:else>
										<s:if test='%{viewAllUsers.city!=null && !viewAllUsers.city.isEmpty()}'>
											,<s:property value="viewAllUsers.city" />
										</s:if>
										<s:else>
											,NA
										</s:else>
										<s:if test='%{viewAllUsers.postalCode!=null && !viewAllUsers.postalCode.isEmpty()}'>
											,<s:property value="viewAllUsers.postalCode" />
										</s:if>
										<s:else>
											,NA
										</s:else>
									</p>
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-md-3"> Status :  </label>
								<div class="col-md-8">
									<p class="form-control-static">
										<s:if test='%{viewAllUsers.status=="Y"}'>
											Active
										</s:if>
										<s:elseif test='%{viewAllUsers.status=="N"}'>
											Inactive
										</s:elseif>
										<s:elseif  test='%{viewAllUsers.status=="B"}'>
											Blacklist
										</s:elseif>
										<s:else>
										 	Suspend
										</s:else>
									</p>
								</div>
							</div>
						</div>
						<div class="col-md-3">
							<div class="form-group">
								<div id="browseImage">
									<s:if test="%{viewAllUsers.profileImage != null && !viewAllUsers.profileImage.isEmpty()}">
										<img
											src='<c:url value="${viewAllUsers.profileImage}"/>'
											alt='<s:property  value="personFullName" />'
											align="left" height="130px" width="120px" border="0" />
									</s:if>
									<s:else>
										<img height="102px" width="110px" alt="" src="../images/common/photo_notAvailable.jpg" />
									</s:else>
								</div>
							</div>
						</div>
					</div>
					</div>
		 		</div>
			</div>
		</div>
		</div>
	</div>
</div>
<jsp:include page="/WEB-INF/pages/admin/ajaxViewAllStudentsAttendanceDetails.jsp" />
<jsp:include page="/WEB-INF/pages/admin/ajaxViewAllStudentFeeDetails.jsp" />
<jsp:include page="/WEB-INF/pages/admin/ajaxViewAllStudentsMarksDetails.jsp" />
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">Assignment Information</div>
				<div class="tools">
					<a href="javascript:;" class="expand"></a>
					<a href="javascript:;" class="remove"></a>
				</div>
			</div>
			<div class="portlet-body">
			<s:if test="%{viewAllUsers.classAssignmentsList!= null && !viewAllUsers.classAssignmentsList.isEmpty()}">
					<table
						class="table table-striped table-bordered table-hover table-full-width"
						id="sample_2">
						<thead>
							<tr>
								<th>
									 Subject Name
								</th>
								<th>
									Assignment Date
								</th>
								<th>
									 Status
								</th>
							</tr>
						</thead>
						<tbody>
							<s:iterator value="viewAllUsers.classAssignmentsList">
								<tr>
									<td>
										<s:property value="subjectName" />
									</td>
									<td>
										<s:property value="assignmentDateStr" />
									</td>
									<td>
										<s:property value="status" />
									</td>
								</tr>
							</s:iterator>
						</tbody>
					</table>
				</s:if>
				<s:else>
					<div class="alert alert-info">
						Assignments are not found for this student.
					</div>
				</s:else>
			</div>
		</div>
	</div>
</div>				

