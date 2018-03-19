<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/messages.jsp"%>
<div>
	
	<div class="spaceDiv"></div>
	<h4 class="pageTitle bold">Pending Mobile Requests</h4>
		<s:if test="%{tempList != null && !tempList.isEmpty()}">
			<table
				class="table table-striped table-bordered table-hover table-full-width"
				id="sample_2">
				<thead>
					<tr>
						<th>
							Parent Name
						</th>
						<th>
							Old Mobile Number
						</th>
						<th>
							New Mobile Number
						</th>
						<th>
							Requested Date
						</th>
						<th>
							Action
						</th>
						<th>
							Request Status
						</th>
						
					</tr>
				</thead>
				<tbody>
					<s:iterator value="tempList">
							<tr>
								<td>
									<s:property value="parentAccount.person.fullPersonName" />
									
								</td>
								<td>
									<s:property value="parentAccount.person.mobileNumber" />
								</td>
								<td>
									<s:property value="mobileNumber" />
								</td>
								<td>
									<s:property value="requestDateStr" />
								</td>
								
								<td>
									<ul class="tooltipDiv" style="padding-left: 0px;">
										<li>
											<a href="#">Action</a>
											<ul class="tooltipSubDiv">
												<div class="popover bottom " style="display: none;">
													<div class="arrow"></div>
													<!-- <h3 class="popover-title">
														View Leave Description
													</h3> -->
													<div class="popover-content">
														<div class="row">
															<s:form action="ajaxApproveOrRejectMobieleRequest" theme="simple"
																id="approveLeave_%{id}" namespace="/admin">
																<s:hidden name="anyId" value="%{id}"></s:hidden>
																<s:hidden name="anyTitle" cssClass="anyTitleId" value=""></s:hidden>
																<s:hidden name="tempString" cssClass="tempStringId"
																	value=""></s:hidden>
																<div class="col-md-12">
																	<b>Reason:</b>
																	<br />
																	<s:property value="description" />
																</div>
																<div class="col-md-12">
																	<sj:textarea name="mobileRequests.reason" cols="30"
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
									<s:if test='%{requestStatus == "P"}'>
										<span class="label label-sm label-info">
												Pending
											</span>
									</s:if>
									<s:elseif test='%{requestStatus == "A"}'>
										<span class="label label-sm label-success">
												Approved
											</span>
									</s:elseif>
									<s:elseif test='%{requestStatus == "R"}'>
										<span class="label label-sm label-danger">
												Rejected
											</span>
									</s:elseif>
								</td>
								
							</tr>
						
						
					</s:iterator>
				</tbody>
			</table>
			</s:if>
		<s:else>
			<div class="alert alert-info">
				Currently there are no mobile change requests.
			</div>
		</s:else>
		
		<div class="spaceDiv"></div>
	<h4 class="pageTitle bold">Pending Drop/Pickup points Requests</h4>
		<s:if test="%{tempList2 != null && !tempList1.isEmpty()}">
			<table
				class="table table-striped table-bordered table-hover table-full-width"
				id="sample_2">
				<thead>
					<tr>
						<th>
							Parent Name
						</th>
						<th>
							Student Name
						</th>
						<th>
							Route Name
						</th>
						<th>
							Boarding Point
						</th>
						<th>
							Vehicle Name
						</th>
						<th>
							Requested Date
						</th>
						<th>
							Action
						</th>
						<th>
							Request Status
						</th>
						
					</tr>
				</thead>
				<tbody>
					<s:iterator value="tempList2">
							<tr>
								<td>
									<s:property value="parentAccount.person.fullPersonName" />
									
								</td>
								<td>
									<s:property value="studentAccount.person.fullPersonName" />
								</td>
								<td>
									<s:property value="routeName" />
								</td>
								<td>
									<s:property value="boardingPointName" />
								</td>
								<td>
									<s:property value="vehicleName" />
								</td>
								<td>
									<s:property value="requestDateStr" />
								</td>
								
								<td>
									<ul class="tooltipDiv" style="padding-left: 0px;">
										<li>
											<a href="#">Action</a>
											<ul class="tooltipSubDiv">
												<div class="popover bottom " style="display: none;">
													<div class="arrow"></div>
													<!-- <h3 class="popover-title">
														View Leave Description
													</h3> -->
													<div class="popover-content">
														<div class="row">
															<s:form action="ajaxApproveOrRejectMobieleRequest" theme="simple"
																id="approveLeave_%{id}" namespace="/admin">
																<s:hidden name="anyId" value="%{id}"></s:hidden>
																<s:hidden name="anyTitle" cssClass="anyTitleId" value=""></s:hidden>
																<s:hidden name="tempString" cssClass="tempStringId"
																	value=""></s:hidden>
																<div class="col-md-12">
																	<b>Reason:</b>
																	<br />
																	<s:property value="description" />
																</div>
																<div class="col-md-12">
																	<sj:textarea name="mobileRequests.reason" cols="30"
																		rows="3" value="" label="Comments"
																		cssClass=" form-control"></sj:textarea>
																</div>
																
																<div class="col-md-12">
																<div class="spaceDiv"></div>
																	<sj:submit cssClass="submitBt btn blue staffAcceptedClass" value="Approve" 
																		onBeforeTopics="loadStaffApproveFormDate"
																		indicator="indicator" targets="stepStaffLeavesDiv"
																		resetForm="true" formIds="approveLeave_%{id}" />
																	<sj:submit   cssClass="submitBt btn red staffRejectedClass" value="Reject" 
																		onBeforeTopics="loadStaffRejectFormDate"
																		indicator="indicator" targets="stepStaffLeavesDiv"
																		resetForm="true" formIds="approveLeave_%{id}" />
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
									<s:if test='%{requestStatus == "P"}'>
										<span class="label label-sm label-info">
												Pending
											</span>
									</s:if>
									<s:elseif test='%{requestStatus == "A"}'>
										<span class="label label-sm label-success">
												Approved
											</span>
									</s:elseif>
									<s:elseif test='%{requestStatus == "R"}'>
										<span class="label label-sm label-danger">
												Rejected
											</span>
									</s:elseif>
								</td>
								
							</tr>
						
						
					</s:iterator>
				</tbody>
			</table>
			</s:if>
		<s:else>
			<div class="alert alert-info">
				Currently there are no mobile change requests.
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
		
		$('.anyTitleId').val('approve');
		$('.tempStringId').val('staff');
	});
	$.subscribe('loadStaffRejectFormDate', function(event, data) {
		$(".staffRejectedClass").val("Please Wait...").attr('disabled', 'disabled');
		
		$('.anyTitleId').val('reject');
		$('.tempStringId').val('staff');
	});
	$.subscribe('loadStudentApproveFormDate', function(event, data) {
		$(".studentAcceptedClass").val("Please Wait...").attr('disabled', 'disabled');
		
		$('.anyTitleId').val('approve');
		$('.tempStringId').val('student');
	});
	$.subscribe('loadStudentRejectFormDate', function(event, data) {
		$(".studentRejectedClass").val("Please Wait...").attr('disabled', 'disabled');
		
		$('.anyTitleId').val('reject');
		$('.tempStringId').val('student');
	});
});
</script>
