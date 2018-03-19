<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/messages.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>
<h4 class="pageTitle bold">
			Pending Appointments
</h4>
<s:if test="%{tempList != null && !tempList.isEmpty()}">
	<table
		class="table table-striped table-bordered table-hover table-full-width"
		id="sample_2">
		<thead>
			<tr>
				<th>Subject</th>
				<th>Schedule Date (MM/DD/YYYY)</th>
				<th>Action</th>
				<th>Email Address</th>
				<th>Contact Number</th>
				<th>Status</th>
				<th>Description</th>
				<th>Request From</th>

			</tr>
		</thead>
		<tbody>
			<s:iterator value="tempList">
				<tr>
					<td><s:property value="subject" /></td>
					<td><s:property value="scheduleDateWithTimeStr" /></td>
					<td>
						<ul class="tooltipDiv">
							<li><a href="#">Action</a>
								<ul class="tooltipSubDiv">
									<div class="popover bottom " style="display: none;">
										<div class="arrow"></div>
										<h3 class="popover-title">View Appointment</h3>
										<div class="popover-content">
											<div class="row">
												<s:form action="ajaxApproveOrRejectAppointment"
													theme="simple" id="approveAppointment_%{appointmentId}"
													namespace="/student">
													<s:hidden name="anyId" value="%{appointmentId}"></s:hidden>
													<s:hidden name="anyTitle" cssClass="anyTitleId" value=""></s:hidden>
													<s:hidden name="tempString" cssClass="tempStringId"
														value=""></s:hidden>
													<div class="col-md-12">
														<b>Description:</b> <br />
														<s:property value="description" />
													</div>
													<div class="col-md-12">
														<sj:textarea name="appointment.apporveDescription"
															cols="30" rows="3" value="" label="Comments"
															cssClass=" form-control"></sj:textarea>
													</div>

													<div class="col-md-12">
														<div class="spaceDiv"></div>
														<sj:submit cssClass="submitBt btn blue" value="Approve"
															onBeforeTopics="loadAppointmentApproveForm"
															indicator="indicator" targets="contentDiv"
															resetForm="true"
															formIds="approveAppointment_%{appointmentId}" />
														<sj:submit cssClass="submitBt btn red" value="Reject"
															onBeforeTopics="loadAppointmentRejectForm"
															indicator="indicator" targets="contentDiv"
															resetForm="true"
															formIds="approveAppointment_%{appointmentId}" />
													</div>
													<div class="spaceDiv"></div>
												</s:form>
											</div>
										</div>
									</div>
								</ul></li>
						</ul>
					</td>
					<td><s:property value="email" /></td>
					<td><s:property value="mobileNumber" /></td>
					<td><s:if test='%{status == "P"}'>
							<span class="label label-sm label-info"> Pending </span>
						</s:if> <s:elseif test='%{status == "A"}'>
							<span class="label label-sm label-success"> Approved </span>
						</s:elseif> <s:elseif test='%{status == "R"}'>
							<span class="label label-sm label-danger"> Rejected </span>
						</s:elseif></td>
					<td><s:property value="description" /></td>
					<td><s:property value="requestPersonNameWithRoleDesc" /></td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
</s:if>
<s:else>
	<div class="alert alert-info">Currently there are no pending appointments.</div>
</s:else>
<h4 class="pageTitle bold">
	Approved Appointments
</h4>
<s:if test="%{tempList1 != null && !tempList1.isEmpty()}">
	<table
		class="table table-striped table-bordered table-hover table-full-width"
		id="sample_3">
		<thead>
			<tr>
				<th>Subject</th>
				<th>Schedule Date (MM/DD/YYYY)</th>
				<th>Email Address</th>
				<th>Contact Number</th>
				<th>Status</th>
				<th>Description</th>
				<th>Request From</th>

			</tr>
		</thead>
		<tbody>
			<s:iterator value="tempList1">
				<tr>
					<td><s:property value="subject" /></td>
					<td><s:property value="scheduleDateWithTimeStr" /></td>
					<td><s:property value="email" /></td>
					<td><s:property value="mobileNumber" /></td>
					<td>
					 <span class="label label-sm label-success"> Approved </span>
					 </td>
					<td><s:property value="description" /></td>
					<td><s:property value="requestPersonNameWithRoleDesc" /></td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
</s:if>
<s:else>
	<div class="alert alert-info">Currently there are no accepted appointments.</div>
</s:else>
<h4 class="pageTitle bold">
	Rejected  Appointments
</h4>
<s:if test="%{tempList2 != null && !tempList2.isEmpty()}">
	<table
		class="table table-striped table-bordered table-hover table-full-width"
		id="sample_4">
		<thead>
			<tr>
				<th>Subject</th>
				<th>Schedule Date (MM/DD/YYYY)</th>
				<th>Email Address</th>
				<th>Contact Number</th>
				<th>Status</th>
				<th>Description</th>
				<th>Request From</th>

			</tr>
		</thead>
		<tbody>
			<s:iterator value="tempList2">
				<tr>
					<td><s:property value="subject" /></td>
					<td><s:property value="scheduleDateWithTimeStr" /></td>
					<td><s:property value="email" /></td>
					<td><s:property value="mobileNumber" /></td>
					<td> 
					<span class="label label-sm label-danger"> Rejected </span>
					</td>
					<td><s:property value="description" /></td>
					<td><s:property value="requestPersonNameWithRoleDesc" /></td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
</s:if>
<s:else>
	<div class="alert alert-info">Currently there are no rejected appointments.</div>
</s:else>
<script type="text/javascript">
	$(document).ready(function() {
		TableAdvanced.init();
		$.subscribe('loadAppointmentApproveForm', function(event, data) {
			$('.anyTitleId').val('approve');
			$('.tempStringId').val('staff');
		});
		$.subscribe('loadAppointmentRejectForm', function(event, data) {
			$('.anyTitleId').val('reject');
			$('.tempStringId').val('staff');
		});
	});
</script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/onload.js">
</script>
