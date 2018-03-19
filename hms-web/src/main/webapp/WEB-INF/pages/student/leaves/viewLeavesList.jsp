<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>
<jsp:include page="/common/messages.jsp"></jsp:include>
<div id="stepStaffsLeaves" class="form-body">
<s:if test='%{viewStudentPersonAccountDetails.hostelMode == "H"}'>
	<div class="row">
	<div class="form-group">
		<label class="col-md-2 control-label">
			Select Leave Type :
		</label>
		<div class="col-md-9">
			<div class="" id="sectionsContent" >
				 <s:radio list="#{'SL':'School Leave','HL':'Hostel Leave'}" name="alertSendType" onclick="getStudentHostelOrSchoolLeaveDetails(this.value)" id="studentLeaveDetailsId" />
			</div>
		</div>
	</div>
	</div>
</s:if>
	<s:set name="isParent" value="%{user.isParent}" />
	<h4 class="pageTitle bold">
			Pending Leaves
		</h4>
	<s:if test="%{leavesList != null && !leavesList.isEmpty()}">
		<table
			class="table table-striped table-bordered table-hover table-full-width"
			id="sample_2">
			<thead>
				<tr>
					<th>
						Start Date
					</th>
					<th>
						End Date
					</th>
					<th>
						Total Days
					</th>
					<s:if test='%{"Y" == #isParent}'>
						<th>
							Edit
						</th>
						<th>
							Delete
						</th>
					</s:if>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="leavesList">
					<tr>
						<td>
							<s:property value="userFormattedStartDate" />
						</td>
						<td>
							<s:property value="userFormattedEndDate" />
						</td>
						<td>
							<s:property value="leavesCount" />
						</td>
						<s:if test='%{"Y" == #isParent}'>
							<td>
								<s:if test='%{"Y" == #isParent}'>
									<s:if test='%{compareTwoDates}'>
									<a data-toggle="modal" href="#popupEditLeaveDiv"
											class="btn btn-xs purple" onclick="javascript:popupEditLeaveDetails(<s:property value="id" />,<s:property value="academicYearId" />,<s:property value="custId" />);"><i
											class="fa fa-edit"></i>Edit </a>
									</s:if>
								</s:if>
							</td>
							<td>
								<s:if test='%{"Y" == #isParent}'>
									<s:url id="removeLeave" action="ajaxRemoveStudentPendingLeave"
										escapeAmp="false" includeParams="all" namespace="/student">
										<s:param name="tempId1" value="id"></s:param>
									</s:url>
									<s:div cssClass="btn btn-xs red "
										onclick="javascript:confirmDialogWithTarget(this,'parentLeaveContent');"
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
		<div class="alert alert-info">
			No Pending leaves.
		</div>
	</s:else>
</div>
<div>
<h4 class="pageTitle bold">
			Approved Leaves
		</h4>
	<s:if
		test="%{approvedLeavesList != null && !approvedLeavesList.isEmpty()}">
		
		<table
			class="table table-striped table-bordered table-hover table-full-width"
			id="sample_3">
			<thead>
				<tr>
					<th>
						Start Date
					</th>
					<th>
						End Date
					</th>
					<th>
						Comments
					</th>
					<th>
						Total Days
					</th>
					<th>
						Delete
					</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="approvedLeavesList">
					<tr>
						<td>
							<s:property value="userFormattedStartDate" />
						</td>
						<td>
							<s:property value="userFormattedEndDate" />
						</td>
						<td>
							<ul class="tooltipDiv">
								<li>
									<a href="#">View</a>
									<ul class="tooltipSubDiv">
										<div class="popover bottom " style="display: none;">
											<div class="arrow"></div>
											<h3 class="popover-title">
												Comments
											</h3>
											<div class="popover-content">
												<s:property value="approvalsComment" />
											</div>
										</div>
									</ul>
								</li>
							</ul>
						</td>
						<td>
							<s:property value="leavesCount" />
						</td>
						<td>
							<s:if
								test='%{(#session.academicYear == null || #session.academicYear.isEmpty()) || (#session.previousYear=="N")}'>
								<s:if test='%{startDate.compareTo(toDate) > 0}'>
									<s:url id="doCancelApprovedLeaves"
										action="ajaxDoStaffEditApprovedLeaves" includeParams="all"
										escapeAmp="false">
										<s:param name="anyId" value="%{id}" />
										<s:param name="tempId" value="%{accountId}" />
										<s:param name="anyTitle">
											<s:property value="startDateStr" />
										</s:param>
										<s:param name="tempString">
											<s:property value="endDateStr" />
										</s:param>
									</s:url>
									<s:div cssClass="btn btn-xs red emsRemove" 
										id='%{doCancelApprovedLeaves}' theme="simple"
										title="Delete this leave"> <i class="fa fa-times"></i> Delete</s:div>
							   </s:if>
							</s:if>
						</td>
					</tr>
				</s:iterator>
			</tbody>
		</table>
	</s:if>
	<s:else>
		<div class="alert alert-info">
			No Approved leaves.
		</div>
	</s:else>
</div>
<div>
<h4 class="pageTitle bold">
			Rejected Leaves
		</h4>
	<s:if
		test="%{rejectedLeavesList != null && !rejectedLeavesList.isEmpty()}">
		
		<table
			class="table table-striped table-bordered table-hover table-full-width"
			id="sample_4">
			<thead>
				<tr>
					<th>
						Start Date
					</th>
					<th>
						End Date
					</th>
					<th>
						View
					</th>
					<th>
						Total Days
					</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="rejectedLeavesList">
					<tr>
						<td>
							<s:property value="userFormattedStartDate" />
						</td>
						<td>
							<s:property value="userFormattedEndDate" />
						</td>
						<td>
							<ul class="tooltipDiv">
								<li>
									<a href="#">View</a>
									<ul class="tooltipSubDiv">
										<div class="popover bottom " style="display: none;">
											<div class="arrow"></div>
											<h3 class="popover-title">
												Comments
											</h3>
											<div class="popover-content">
												<s:property value="approvalsComment" />
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
				</s:iterator>
			</tbody>
		</table>
	</s:if>
	<s:else>
		<div class="alert alert-info">
			No Rejected leaves.
		</div>
	</s:else>
</div>
<div id="popupEditLeaveDiv"></div>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/onload.js">
</script>
<script type="text/javascript">
TableAdvanced.init();
function popupEditLeaveDetails(id,academicYearId,custId) {
	var url = jQuery.url.getChatURL("/student/ajaxDoLeaveForm.do");
	$('#popupEditLeaveDiv').html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
	$.ajax( {
			url : url,
			cache : false,
			data : "tempId1=" + id+"&academicYearId="+academicYearId+"&tempId2="+custId,
			success : function(html) {
				$("#popupEditLeaveDiv").html(html);
			}
		});
	}
</script>

