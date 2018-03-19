<%@ include file="/common/taglibs.jsp"%>
<jsp:include page="/common/messages.jsp"></jsp:include>
<div id="permissionsDiv">
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
				<th>Comments</th>
				<th>Permission Timings</th>
				<s:if test='%{user.isOnlySchoolAdmin == "N" && user.isSchoolPrincipal=="N" && user.isSchoolDirector == "N"}'>
					<th>Delete</th>
				</s:if>
			</tr>
		</thead>
		<tbody>
			<s:set name="permissions" value=""></s:set>
			<s:iterator value="tempList">
				<s:if test="%{permissionsId != #permissions}">
					<tr>
						<td><s:property value="fullName" /></td>
						<td><s:property value="classNameAndSection" /></td>
						<td><s:property value="formattedStartDate" /></td>
						<td><s:property value="formattedEndDate" /></td>
						<td><span class="label label-sm label-info"> Pending </span></td>
				</s:if>
				<td><s:property value="comments" /></td> 
				<td>
					<div>
						<ul class="tooltipDiv">
							<li><span class="btn btn-xs purple"> <a
									href="#" style="color: #fff;">View Timings <i class="fa fa-share"></i>
								</a>
							</span>
								<ul class="tooltipSubDiv">
									<div class="popover bottom " style="display: none;">
										<div class="arrow"></div>
										<h3 class="popover-title">View Student Permissions</h3>
										<div class="popover-content">
											<li>
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
											</li>										
										</div>
									</div>
								</ul></li>
						</ul>
					</div>
				</td>
				<s:if test='%{user.isOnlySchoolAdmin == "N" && user.isSchoolPrincipal=="N" && user.isSchoolDirector == "N"}'>
					<td><s:if test='%{attendanceDate.compareTo(permissionDateStr) < 0}'>
						<s:url id="removeLeave"
								action="ajaxRemoveParentPermissions" escapeAmp="false"
								includeParams="all" namespace="/student">
								<s:param name="tempId1" value="%{permissionsId}"></s:param>
							</s:url> <s:div cssClass="btn btn-xs red "
								onclick="javascript:confirmDialogWithTarget(this,'permissionsDiv');"
								id='%{removeLeave}' title="Delete this leave">
								<i class="fa fa-times"></i>Delete</s:div>
					</s:if>
					</td>
				</s:if>
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
						<td>
						<div>
							<ul class="tooltipDiv">
									<li><span class="btn btn-xs purple"> <a href="#"
											style="color: #fff;">View Timings <i class="fa fa-share"></i>
										</a>
									</span>
										<ul class="tooltipSubDiv">
											<div class="popover bottom " style="display: none;">
												<div class="arrow"></div>
												<h3 class="popover-title">View Student Permissions</h3>
												<div class="popover-content">
													<li>
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
													</li>
												</div>
											</div>
										</ul></li>
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
						<td>
							<div>
								<ul class="tooltipDiv">
									<li><span class="btn btn-xs purple"> <a
											href="#" style="color: #fff;">View Timings <i class="fa fa-share"></i>
										</a>
									</span>
										<ul class="tooltipSubDiv">
											<div class="popover bottom " style="display: none;">
												<div class="arrow"></div>
												<h3 class="popover-title">View Student Permissions</h3>
												<div class="popover-content">
												 <li>
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
													</li>
												</div>
											</div>
										</ul></li>
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
TableAdvanced.init();
</script>

