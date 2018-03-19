<%@ include file="/common/taglibs.jsp"%>
<jsp:include page="/common/messages.jsp"></jsp:include>
<h4 class="pageTitle bold">Pending Permissions</h4>
<s:if test="%{tempList != null && !tempList.isEmpty()}">
	<table class="table table-striped table-bordered table-hover table-full-width" id="sample_2">
		<thead>
			<tr>
				<th>Student Name</th>
			     <th>Class Name</th>
				<th>Start Date</th>
				<th>End Date</th>
				<th>Status</th>
				<s:if test='%{isClassTeacher =="isClassTeacher"}'>
					<th>Action</th>
				</s:if>
				<th>Comments</th>
				<th>Permission Timings</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="tempList">
				<tr>
					<td><s:property value="fullName" /></td>
					<td><s:property value="classNameAndSection" /></td>
					<td><s:property value="formattedStartDate" /></td>
					<td><s:property value="formattedEndDate" /></td>
					<td><span class="label label-sm label-info"> Pending </span></td>
					<s:if test='%{isClassTeacher =="isClassTeacher"}'>
					<td>
						<ul class="tooltipDiv">
						<li>
							<a href="#">Action</a>
							<ul class="tooltipSubDiv">
								<div class="popover bottom " style="display: none;">
									<div class="arrow"></div>
									<h3 class="popover-title">
										View Permission 
									</h3>
									<div class="popover-content">
										<div class="row">
											<s:form action="ajaxApproveOrRejectPermisssion" theme="simple"
												id="approve_%{permissionsId}" namespace="/student">
												<s:hidden name="tempId2" value="%{permissionsId}"></s:hidden>
												<s:hidden name="bankName" value="isClassTeacher"></s:hidden>
												<s:hidden name="anyTitle" cssClass="anyTitleId" value=""></s:hidden>
													<div class="col-md-12">
													<b>Description:</b>
													<br />
													<s:property value="comments" />
												</div>
												<div class="col-md-12">
													<sj:textarea name="PlTitle" cols="30"
														rows="3" value="" label="Comments"
														cssClass=" form-control"></sj:textarea>
												</div>
												<div class="col-md-12">
													<div class="spaceDiv"></div>
													<sj:submit   cssClass="submitBt btn blue" value="Approve"
														onBeforeTopics="staffApproveFormDate"
														indicator="indicator" targets="permissionsDiv"
														resetForm="true" formIds="approve_%{permissionsId}" />
													<sj:submit   cssClass="submitBt btn red" value="Reject"
														onBeforeTopics="staffRejectFormDate"
														indicator="indicator" targets="permissionsDiv"
														resetForm="true" formIds="approve_%{permissionsId}" />
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
				</s:if>
				<td><s:property value="comments" /></td> 
				<td>
					<div>
						<ul class="tooltipDiv">
							<li><span class="btn btn-xs purple"> <a
									href="#" style="color: #fff;">  View Timings <i class="fa fa-share"></i>
								</a>
							</span>
								<ul class="tooltipSubDiv">
									<div class="popover bottom " style="display: none;">
										<div class="arrow"></div>
										<h3 class="popover-title">View Student Permissions</h3>
										<div class="popover-content">
											 <table class="table table-bordered table-hover">
													<thead>
														<tr>
															<th>Day</th>
															<th>Start Time</th>
															<th>End Time</th>
														</tr>
													</thead>
													<tbody>
													</tbody>
													<s:iterator value="permissionTimesList">
														<tr>
															 <td class="success"><s:property value="days" /></td>
															 <td class="warning"><s:if test="%{startTime != ''}"> <s:property value="startTime" /> </s:if> <s:else>-</s:else> </td>
															 <td class="danger"><s:if test="%{endTime != ''}"> <s:property value="endTime" /></s:if> <s:else>-</s:else></td>
														</tr>
													</s:iterator>
												</table>
										</div>
									</div>
								</ul>
						   </li>
						</ul>
					</div>
				</td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
</s:if>
<s:else>
	<div class="alert alert-info">No Pending Permissions.</div>
</s:else>
<div>
	<h4 class="pageTitle bold">Approved Permissions</h4>
	<s:if test="%{tempList1 != null && !tempList1.isEmpty()}">
		<table
			class="table table-striped table-bordered table-hover table-full-width"
			id="sample_3">
			<thead>
				<tr>
				<th>Student Name</th>
			     <th>Class Name</th>
				<th>Start Date</th>
				<th>End Date</th>
				<th>Status</th>
				<th>Comment</th>
				<th>Permission Timings</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="tempList1">
					<tr>
					<td><s:property value="fullName" /></td>
					<td><s:property value="classNameAndSection" /></td>
					<td><s:property value="formattedStartDate" /></td>
					<td><s:property value="formattedEndDate" /></td>
					<td><span class="label label-success"> Approved </span></td>
					<td><s:property value="approvalsComment" /></td> 
				<td>
					<div>
						<ul class="tooltipDiv">
							<li><span class="btn btn-xs purple"> <a
									href="#" style="color: #fff;">  View Timings <i class="fa fa-share"></i>
								</a>
							</span>
								<ul class="tooltipSubDiv">
									<div class="popover bottom " style="display: none;">
										<div class="arrow"></div>
										<h3 class="popover-title">View Student Permissions</h3>
										<div class="popover-content">
											<table class="table table-bordered table-hover">
													<thead>
														<tr>
															<th>Day</th>
															<th>Start Time</th>
															<th>End Time</th>
														</tr>
													</thead>
													<tbody>
													</tbody>
													<s:iterator value="permissionTimesList">
														<tr>
															 <td class="success"><s:property value="days" /></td>
															 <td class="warning"><s:if test="%{startTime != ''}"> <s:property value="startTime" /> </s:if> <s:else>-</s:else> </td>
															 <td class="danger"><s:if test="%{endTime != ''}"> <s:property value="endTime" /> </s:if> <s:else>-</s:else> </td>
														</tr>
													</s:iterator>
												</table>
										</div>
									</div>
								</ul>
						   </li>
						</ul>
					</div>
				</td>
				</tr>
				</s:iterator>
			</tbody>
		</table>
	</s:if>
	<s:else>
		<div class="alert alert-info">No Approved Permissions.</div>
	</s:else>
</div>
<div>
	<h4 class="pageTitle bold">Rejected Permissions</h4>
	<s:if test="%{tempList2 != null && !tempList2.isEmpty()}">
		<table
			class="table table-striped table-bordered table-hover table-full-width"
			id="sample_4">
			<thead>
				<tr>
					<th>Student Name</th>
				     <th>Class Name</th>
					<th>Start Date</th>
					<th>End Date</th>
					<th>Status</th>
					<th>Comment</th>
					<th>Permission Timings</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="tempList2">
					<tr>
					<td><s:property value="fullName" /></td>
					<td><s:property value="classNameAndSection" /></td>
					<td><s:property value="formattedStartDate" /></td>
					<td><s:property value="formattedEndDate" /></td>
					<td><span class="label label-danger"> Rejected </span></td>
					<td><s:property value="approvalsComment" /></td> 
				   <td>
					<div>
						<ul class="tooltipDiv">
							<li><span class="btn btn-xs purple"> <a
									href="#" style="color: #fff;">  View Timings <i class="fa fa-share"></i>
								</a>
							</span>
								<ul class="tooltipSubDiv">
									<div class="popover bottom " style="display: none;">
										<div class="arrow"></div>
										<h3 class="popover-title">View Student Permissions</h3>
										<div class="popover-content">
											<table class="table table-bordered table-hover">
													<thead>
														<tr>
															<th>Day</th>
															<th>Start Time</th>
															<th>End Time</th>
														</tr>
													</thead>
													<tbody>
													</tbody>
													<s:iterator value="permissionTimesList">
														<tr>
															 <td class="success"><s:property value="days" /></td>
															 <td class="warning"><s:if test="%{startTime != ''}"> <s:property value="startTime" /> </s:if> <s:else>-</s:else> </td>
															 <td class="danger"><s:if test="%{endTime != ''}"> <s:property value="endTime" /> </s:if> <s:else>-</s:else> </td>
														</tr>
													</s:iterator>
												</table>
										</div>
									</div>
								</ul>
						   </li>
						</ul>
					</div>
				</td>
				</tr>
				</s:iterator>
			</tbody>
		</table>
	</s:if>
	<s:else>
		<div class="alert alert-info">No Rejected Permissions.</div>
	</s:else>
</div>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/onload.js">
</script>
<script type="text/javascript">
$(document).ready(function() {
	TableAdvanced.init();
	$.subscribe('staffApproveFormDate', function(event, data) {
		$('.anyTitleId').val('approve');
	});
	$.subscribe('staffRejectFormDate', function(event, data) {
		$('.anyTitleId').val('reject');
	});
});	
</script>

