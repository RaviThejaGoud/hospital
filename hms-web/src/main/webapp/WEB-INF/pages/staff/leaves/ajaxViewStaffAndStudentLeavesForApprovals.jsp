<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/messages.jsp"%>
<div>
	<s:if test='%{#session.previousYear=="N"}'>
	<div class="spaceDiv"></div>
	<s:if test="%{(smsAlloted == 0) || (smsAlloted <= smsCnt)}">
		<div style="color: red;" class="alert alert-info">
			You have been used all your allotted
			<s:property value="smsAlloted" />
			SMS. Please contact EazySchool support team (080-46620999) to recharge your SMS.
		</div>
	</s:if>
		<s:if test="%{classNameList != null && !classNameList.isEmpty()}">
		 <s:if test='%{user.onlySchoolAdmin || user.schoolPrincipal || user.isOnlySchoolHod == "Y" || user.isSchoolHostel == "Y" || user.isSchoolTeacher == "Y"}'>
			<table
				class="table table-striped table-bordered table-hover table-full-width"
				id="sample_2">
				<thead>
					<tr>
						<th>
							Name
						</th>
						<s:if test='%{tempString == "staff"}'>
							<th>
								Type
							</th>
						</s:if>
						<th>
							Action
						</th>
						<th>
							Start Date
						</th>
						<th>
							End Date
						</th>
						<th>
							Leave Status
						</th>
						<th>
							Total Leaves Taken
						</th>
						<th>
							Requested Days
						</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="classNameList">
						<s:if test='%{tempString == "staff"}'>
							<tr>
								<td>
									<s:property value="PersonFirstLastNameOnly" />
									(
									<s:property value="roleDescription" />
									)
								</td>
								<td>
									<s:property value="leaveStatusDesc" />
								</td>
								<td>
									<ul class="tooltipDiv" style="padding-left: 0px;">
										<li>
											<a href="#">Action</a>
											<ul class="tooltipSubDiv">
												<div class="popover bottom " style="display: none;">
													<div class="arrow"></div>
													<h3 class="popover-title">
														View Leave Description
													</h3>
													<div class="popover-content">
														<div class="row">
															<s:form action="ajaxApproveOrRejectLeave" theme="simple"
																id="approveLeave_%{leavesId}" namespace="/staff">
																<s:hidden name="anyId" value="%{leavesId}"></s:hidden>
																<s:hidden name="anyTitle" cssClass="anyTitleId" value=""></s:hidden>
																<s:hidden name="tempString" cssClass="tempStringId"
																	value=""></s:hidden>
																<div class="col-md-12">
																	<b>Description:</b>
																	<br />
																	<s:property value="description" />
																</div>
																<div class="col-md-12">
																	<sj:textarea name="leave.approvalsComment" cols="30"
																		rows="3" value="" label="Comments"
																		cssClass=" form-control"></sj:textarea>
																</div>
																
																<div class="col-md-12">
																<div class="spaceDiv"></div>
																	<sj:submit   cssClass="submitBt btn blue staffAcceptedClass" value="Approve" 
																		onBeforeTopics="loadStaffApproveFormDate"
																		indicator="indicator" targets="stepStaffLeavesDiv"
																		resetForm="true" formIds="approveLeave_%{leavesId}" />
																	<sj:submit   cssClass="submitBt btn red staffRejectedClass" value="Reject" 
																		onBeforeTopics="loadStaffRejectFormDate"
																		indicator="indicator" targets="stepStaffLeavesDiv"
																		resetForm="true" formIds="approveLeave_%{leavesId}" />
																</div>
																<div class="spaceDiv"></div>
															</s:form>
														</div>
													</div>
												</div>
											</ul>
										</li>
									</ul>
								</td>
								<td>
									<s:property value="userFormattedStartDate" />
								</td>

								<td>
									<s:property value="userFormattedEndDate" />
								</td>
								<td>
									<s:if test='%{leaveStatus == "P"}'>
										<span class="label label-sm label-info">
												Pending
											</span>
									</s:if>
									<s:elseif test='%{leaveStatus == "A"}'>
										<span class="label label-sm label-success">
												Approved
											</span>
									</s:elseif>
									<s:elseif test='%{leaveStatus == "R"}'>
										<span class="label label-sm label-danger">
												Rejected
											</span>
									</s:elseif>
								</td>
								<td>
									<ul class="tooltipDiv">
										<li>
											<a href="#"><s:property value="totalLeavesTaken" /> </a>
											<ul class="tooltipSubDiv">
												<div class="popover bottom " style="display: none;">
													<div class="arrow"></div>
													<h3 class="popover-title">
														Total Leaves
													</h3>
													<div class="popover-content">
														<s:if test='%{summary != null && summary != empty}'>
															<s:property value="summary" />
														</s:if>
														<s:else>
																No leaves taken.
															</s:else>
													</div>
												</div>
											</ul>
										</li>
									</ul>
								</td>
								<td>
									<s:property value="leavesCount" />
								</td>
							</tr>
						</s:if>
						<s:if test='%{tempString == "student"}'>
						<tr>
							<td>
								<s:property value="PersonFirstLastNameOnly" />
								(
								<s:property value="roleDescription" />
								)
							</td>
							<td>
								<ul class="tooltipDiv" style="padding-left: 0px;">
									<li>
										<a href="#">Action</a>
										<ul class="tooltipSubDiv">
											<div class="popover bottom " style="display: none;">
												<div class="arrow"></div>
												<h3 class="popover-title">
													View Leave Description
												</h3>
												<div class="popover-content">
													<s:form action="ajaxApproveOrRejectLeave" theme="simple"
														id="approveLeave_%{leavesId}" namespace="/staff">
														<s:hidden name="anyId" value="%{leavesId}"></s:hidden>
														<s:hidden name="viewStudentLeaveDetails.classSectionId"
															value="%{classSectionId}"></s:hidden>
														<s:hidden name="viewStudentLeaveDetails.classId"
															value="%{classId}"></s:hidden>
														<s:hidden name="anyTitle" cssClass="anyTitleId" value=""></s:hidden>
														<s:hidden name="tempString" cssClass="tempStringId"
															value=""></s:hidden>
														<div class="col-md-12">
															<b>Description:</b>
															<br />
															<s:property value="description" />
														</div>
														<div class="col-md-12">
															<sj:textarea name="leave.approvalsComment" cols="30"
																value="" label="Comments" cssClass=" form-control"></sj:textarea>
														</div>
														<div class="col-md-12">
														<div class="spaceDiv"></div>
															<sj:submit  cssClass="submitBt btn blue studentAcceptedClass" value="Approve" 
																onBeforeTopics="loadStudentApproveFormDate" indicator="indicator"
																targets="stepStaffLeavesDiv" resetForm="true"
																formIds="approveLeave_%{leavesId}" />
															<sj:submit cssClass="submitBt btn red studentRejectedClass" value="Reject" 
																onBeforeTopics="loadStudentRejectFormDate" indicator="indicator"
																targets="stepStaffLeavesDiv" resetForm="true"
																formIds="approveLeave_%{leavesId}" />
														</div>
													</s:form>
												</div>
												<div class="spaceDiv col-md-12"></div>
											</div>
										</ul>
									</li>
								</ul>
							</td>
							<td>
								<s:property value="userFormattedStartDate" />
							</td>
							<td>
								<s:property value="userFormattedEndDate" />
							</td>
							<td>
								<s:if test='%{leaveStatus == "P"}'>
									<span class="label label-sm label-info">
											Pending
										</span>
								</s:if>
								<s:elseif test='%{leaveStatus == "A"}'>
									<span class="label label-sm label-success">
											Approved
										</span>
								</s:elseif>
								<s:elseif test='%{leaveStatus == "R"}'>
									<span class="label label-sm label-danger">
											Rejected
										</span>
								</s:elseif>
							</td>
							<td>
								<s:property value="totalLeavesTaken" />
							</td>
							<td>
								<s:property value="leavesCount" />
							</td>
							</tr>
						</s:if>
					</s:iterator>
				</tbody>
			</table>
			</s:if>
			<s:elseif test='%{classTeacher !=null || user.isOnlySchoolHod == "Y"}'>
			  <table class="table table-striped table-bordered table-hover table-full-width"
				id="sample_4">
				<thead>
					<tr>
						<th>
							Name
						</th>
						<s:if test='%{tempString == "staff"}'>
							<th>
								Leave Type
							</th>
						</s:if>
						<th>
							Leave Status
						</th>
						<th>
							Start Date
						</th>
						<th>
							End Date
						</th>
						<th>
							Total Leaves Taken
						</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="classNameList">
						<s:if test='%{tempString == "staff"}'>
							<tr>
								<td>
									<s:property value="PersonFirstLastNameOnly" />
									(
									<s:property value="roleDescription" />
									)
								</td>
								<td>
									<s:property value="leaveStatusDesc" />
								</td>
								<td>
									<s:if test='%{leaveStatus == "P"}'>
										<span class="label label-sm label-info">
												Pending
											</span>
									</s:if>
									<s:elseif test='%{leaveStatus == "A"}'>
										<span class="label label-sm label-success">
												Approved
											</span>
									</s:elseif>
									<s:elseif test='%{leaveStatus == "R"}'>
										<span class="label label-sm label-danger">
												Rejected
											</span>
									</s:elseif>
								</td>
								<td>
									<s:property value="userFormattedStartDate" />
								</td>

								<td>
									<s:property value="userFormattedEndDate" />
								</td>
								<td>
									<s:property value="leavesCount" />
								</td>
							</tr>
						</s:if>
					</s:iterator>
				</tbody>
			</table>
		 </s:elseif>
		</s:if>
		<s:else>
			<div class="alert alert-info">
				Currently there are no leave requests.
			</div>
		</s:else>
	</s:if>
	<s:else>
		<div class="alert alert-info">
			You can't approve or reject leaves for old academic year.
		</div>
	</s:else>
</div>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/onload.js">
</script>
<script type="text/javascript">
$(document).ready(function() {
	TableAdvanced.init();
	$.subscribe('loadStaffApproveFormDate', function(event, data) {
		$(".staffAcceptedClass").val("Please Wait...").attr('disabled', 'disabled');
		
		$('.anyTitleId').val('A');
		$('.tempStringId').val('staff');
	});
	$.subscribe('loadStaffRejectFormDate', function(event, data) {
		$(".staffRejectedClass").val("Please Wait...").attr('disabled', 'disabled');
		
		$('.anyTitleId').val('R');
		$('.tempStringId').val('staff');
	});
	$.subscribe('loadStudentApproveFormDate', function(event, data) {
		$(".studentAcceptedClass").val("Please Wait...").attr('disabled', 'disabled');
		
		$('.anyTitleId').val('A');
		$('.tempStringId').val('student');
	});
	$.subscribe('loadStudentRejectFormDate', function(event, data) {
		$(".studentRejectedClass").val("Please Wait...").attr('disabled', 'disabled');
		
		$('.anyTitleId').val('R');
		$('.tempStringId').val('student');
	});
});
</script>
