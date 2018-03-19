<%@ include file="/common/taglibs.jsp"%>
<s:if test="%{tempList != null && !tempList.isEmpty()}">
	<table
		class="table table-striped table-bordered table-hover table-full-width"
		id="sample_2">
		<thead>
			<tr>
				<th>
					Shift Name
				</th>
				<th>
					Start Time
				</th>
				<th>
					End Time
				</th>
				<th>
					View Staff
				</th>
				<th>
					Edit
				</th>
				<th>
					Delete
				</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="tempList">
				<tr>
					<td>
						<s:property value="shiftName" />
					</td>
					<td>
						<s:property value="startTime" />
					</td>
					<td>
						<s:property value="endTime" />
					</td>
					<td>
						<ul class="tooltipDiv">
							<li>
								<a href="#">View</a>
								<ul class="tooltipSubDiv">
									<div class="popover bottom " style="display: none;">
										<div class="arrow"></div>
										<h3 class="popover-title">
											View Staff
										</h3>
										<div class="popover-content">
											<s:if
												test="%{staffShiftInfo != null && !staffShiftInfo.isEmpty()}">
												<s:iterator value="staffShiftInfo">
													<li>
														<s:property value="fullPersonName"/>
													</li>
												</s:iterator>
											</s:if>
											<s:else>
												<li>
													Staff not assigned
												</li>
											</s:else>
										</div>
									</div>
								</ul>
							</li>
						</ul>
					</td>
					<td>
					<s:url id="doEdit"
							action="ajaxDoManageSchoolShiftInfo" includeParams="all"
							escapeAmp="false" namespace="/staff">
							<s:param name="schoolShiftInfo.id" value="%{id}"></s:param>
						</s:url>
						<sj:a href="%{doEdit}"
							targets="schoolShiftInfoDiv" cssClass="btn btn-xs purple"
							title="Edit">
							<i class="fa fa-edit"></i>Edit</sj:a>
					</td>
					<td>
						<s:url id="removeShiftInfo" action="ajaxRemoveShiftInfor"
							includeParams="all" escapeAmp="false" namespace="/staff">
							<s:param name="tempId" value="%{id}" />
						</s:url>
						<s:div cssClass="btn btn-xs red"
							onclick="javascript:confirmDialogWithTarget(this,'mainContentDiv');"
							id='%{removeShiftInfo}' theme="simple" title="Delete" cssStyle="width:60px;float:left;cursor:pointer;">
							<i class="fa fa-times"></i>Delete</s:div>
					</td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
</s:if>
<s:else>
	<div class="alert alert-info">
		You have not created any school shift information.
	</div>
</s:else>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/onload.js"> </script>
<script type="text/javascript">
$(document).ready(function() {
	TableAdvanced.init();
	changePageTitle("Manage Shift Information");
});
</script>
