<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>
<br />
<div>
	<h6>
		Leave Approvals:
	</h6>
</div>
<br />
<s:if test="%{classNameList != null && !classNameList.isEmpty()}">
	<div style="padding-top: 1px">
		<table class="striped" width="600px" style="margin-bottom: 0;"
			cellpadding="1" cellspacing="1" id="results">
			<thead>
				<tr>
					<th width="22%" class="head">
						Name
					</th>
					<th width="13%" class="head">
						Type
					</th>
					<th width="21%" class="head">
						Start Date
					</th>
					<th width="21%" class="head">
						End Date
					</th>
					<th width="21%" class="head">
						Total Days
					</th>
					<th width="13%" class="head">
						View
					</th>
				</tr>
			</thead>
		</table>
		<div id="resultsPage">
			<s:iterator value="classNameList">
				<table class="striped" width="100%" style="margin-bottom: 0;" cellpadding="1" cellspacing="1" id="results">
					<tr class="loaded">
						<td width="22%" class="head">
							<s:url id="removeLeave" action="ajaxDeleteLeave"
								includeParams="all" escapeAmp="false">
								<s:param name="leavesId" value="{leavesId}" />
							</s:url>
							<s:div cssClass="close emsRemove" id='%{removeLeave}'
								theme="simple" title="Delete this Pending Leave"></s:div>
							<s:property value="PersonFirstLastNameOnly" />
						</td>
						<td width="13%" class="head">
							<s:property value="leaveStatusDesc" />
						</td>
						<td width="15%" class="head">
							<s:property value="startDateStr" />
						</td>
						<td width="17%" class="head">
							<s:property value="endDateStr" />
						</td>
						<td width="19%" class="head">
							<s:property value="days" />
						</td>
						<td width="13%">
							<s:url id="addGroupMember" action="ajaxDoEditStaffleaveReport"
								includeParams="all" escapeAmp="false">
								<s:param name="leavesId" value="{leavesId}" />
							</s:url>
							<sj:a href="%{addGroupMember}"
								onCompleteTopics="doInitAddGroupMember"
								onBeforeTopics="cleanOpenRows" indicator="indicator"
								targets="addLeader%{leavesId}">View</sj:a>
						</td>
					</tr>
					<tr id="addLeader<s:property value='leavesId' />"
						style="display: none;" class='load'>
					</tr>
				</table>
			</s:iterator>
		</div>
	</div>
</s:if>
<s:else>
	<br />
	Currently there are no Pending Leaves.
</s:else>


<style type="text/css">
.active {
	color: #0033CC;
	text-decoration: none;
}

.inactive {
	font-weight: bold;
	text-decoration: underline;
	cursor: default;
}
.paginator {
	text-align: center;
	color: #FFF;
}
</style>
<script type="text/javascript">
	$.subscribe('doInitAddGroupMember', function(event, data) {
		var rowObj = $('#' + data.id);
		if (rowObj.is(":hidden")) {
			rowObj.show();
		} else {
			rowObj.hide();
		}
	});
</script>
