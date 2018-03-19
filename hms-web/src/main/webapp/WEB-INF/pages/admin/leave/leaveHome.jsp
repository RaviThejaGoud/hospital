<%@ include file="/common/taglibs.jsp"%>
<jsp:include page="/common/messages.jsp"></jsp:include>
<div>
	<div class="block_head">
		<h2>
			Leave Details
		</h2>
		<div id="topMenu">
			<ul>
				<li>
					<s:url id="urlLeaveLink" action="ajaxDoAdminApplyLeave" namespace="/admin"/>
					<sj:a href="%{urlLeaveLink}" targets="applyLeave"
						indicator="indicator">Apply Leave</sj:a>
				</li>

				<!--<li>
					<s:url id="urlApprovalLeaveLink"
						action="ajaxDoAdminLeaveApprovalsAsApprover" />
					<sj:a href="%{urlApprovalLeaveLink}" targets="applyLeave"
						indicator="indicator">Leave Approvals</sj:a>
				</li>
				--><li>
					<s:url id="urlMyLeaveLink" action="ajaxDoGetLeaveDetails" namespace="/staff"/>
					<sj:a href="%{urlMyLeaveLink}" targets="applyLeave"
						indicator="indicator">My Leaves</sj:a>
				</li>
			</ul>
		</div>
	</div>
	<div class="block_content" id="applyLeave">
		<jsp:include page="/WEB-INF/pages/admin/leave/adminLeavesList.jsp" />

	</div>
</div>
<script type="text/javascript">
	changePageTitle("Manage Leaves");
</script>